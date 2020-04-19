package giatk.controllers;

import giatk.daos.ItemDAO;
import giatk.dtos.ItemDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpdateImageController
 */
@WebServlet("/uploadFile")
public class UpdateImageController extends HttpServlet {

    private static final String SUCCESS = "AdminController";
    private static final String ERROR = "editImageItem.jsp";
    private static final String INVALID_IMAGE = "Format image is invalid. Valid format: JPG, PNG, JPEG or GIF.";
    private static final String EMPTY_IMAGE = "Please update image!";

    private static final String SAVE_DIRECTORY = "resources/items";

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateImageController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        boolean insertSuccessful = true;
        int imgQuantity = 0;
        ArrayList<String> linkImageList = new ArrayList<>();
        HttpSession session = request.getSession();
        int itemID = ((ItemDTO) session.getAttribute("CURRENT_ITEM")).getID();
        String url = ERROR;

        // xử lý upload file khi người dùng nhấn nút thực hiện
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    // xử lý file
                    String nameimg = fileItem.getName();
                    if (!nameimg.isEmpty()) {
                        StringTokenizer st = new StringTokenizer(nameimg, ".");

                        String formatFile = st.nextToken();
                        formatFile = st.nextToken();

                        if (!(formatFile.equalsIgnoreCase("JPG") || formatFile.equalsIgnoreCase("PNG")
                                || formatFile.equalsIgnoreCase("GIF") || formatFile.equalsIgnoreCase("JPEG"))) {
                            insertSuccessful = false;
                        }
                    }
                    if (!nameimg.equals("")) {
                        String dirUrl = request.getServletContext()
                                .getRealPath("") + File.separator + SAVE_DIRECTORY;
                        File dir = new File(dirUrl);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String fileImg = dirUrl + File.separator + nameimg;

                        String urlImage = SAVE_DIRECTORY + "/" + nameimg;
                        linkImageList.add(urlImage);

                        File file = new File(fileImg);
                        try {
                            fileItem.write(file);
                            imgQuantity++;
                        } catch (Exception ex) {
                            insertSuccessful = false;
                            log("Error at UploadImage: " + ex.getMessage());
                        }
                    }
                }
            }

            if (insertSuccessful && imgQuantity > 0) {
                ItemDAO.deleteLinkImageItem(itemID);
                for (int i = 0; i < linkImageList.size(); i++) {
                    ItemDAO.insertLinkImageItem(linkImageList.get(i), itemID);
                }
                url = SUCCESS;
            } else if (imgQuantity == 0) {
                request.setAttribute("ERROR_MESSAGE", EMPTY_IMAGE);
            } else {
                request.setAttribute("ERROR_MESSAGE", INVALID_IMAGE);
            }

        } catch (FileUploadException | SQLException | ClassNotFoundException ex) {
            log("Error at UploadImage: " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

}
