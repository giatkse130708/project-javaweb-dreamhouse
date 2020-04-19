/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.controllers;

import giatk.daos.CartDAO;
import giatk.daos.ItemDAO;
import giatk.dtos.ItemDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kha Gia
 */
public class AdminController extends HttpServlet {

    private final String RELOAD = "admin.jsp";
    private final String EDIT   = "editItem.jsp";
    private final String ERROR  = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ArrayList<ItemDTO> listItems = ItemDAO.getListItem();
            request.setAttribute("LIST_ITEM", listItems);
            url = RELOAD;
            String action = request.getParameter("action");
            if (action != null) {
                int itemID = Integer.parseInt(request.getParameter("itemID"));
                if (action.equals("Delete")) {
                    if (ItemDAO.deleteItem(itemID)) { //Nếu xóa thành công thì cập nhật lại danh sách item.
                        CartDAO.removeItemInCart(itemID); //Item nếu bị xóa, cũng sẻ xóa vĩnh viễn khỏi cart của người dùng
                        listItems = ItemDAO.getListItem();
                        request.setAttribute("LIST_ITEM", listItems);
                        url = RELOAD;
                    }
                } else if (action.equals("Edit")) {
                    ItemDTO item = ItemDAO.getItem(itemID);
                    HttpSession session = request.getSession();
                    session.setAttribute("CURRENT_ITEM", item);
                    url = EDIT;
                }
            }

        } catch (Exception e) {
            log("Error at AdminController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
