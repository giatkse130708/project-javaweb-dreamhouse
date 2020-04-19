/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.controllers;

import giatk.daos.ItemDAO;
import giatk.dtos.ItemDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kha Gia
 */
public class UpdateItemController extends HttpServlet {

    private final String SUCCESS = "AdminController";
    private final String ERROR = "editItem.jsp";

    public UpdateItemController() {
        super();
    }

    //get Data -> valid data -> false: show err_message + return old data -> true: insert data -> show successful_message
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int ID = Integer.parseInt(request.getParameter("itemID"));
            boolean itemValid = true;
            String title = request.getParameter("title");
            long price = 0;
            float square = 0;
            String address = request.getParameter("address");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            try {
                price = Long.parseLong(request.getParameter("price"));
            } catch (Exception e) {
                itemValid = false;
                request.setAttribute("ERROR_MESSAGE", "Price is digit!");
            }
            try {
                square = Float.parseFloat(request.getParameter("square"));
            } catch (Exception e) {
                itemValid = false;
                request.setAttribute("ERROR_MESSAGE", "Square is digit!");
            }

            if (itemValid) {
                if (title.isEmpty()) {
                    itemValid = false;
                    request.setAttribute("ERROR_MESSAGE", "Title is not empty!");
                } else if (price <= 0) {
                    itemValid = false;
                    request.setAttribute("ERROR_MESSAGE", "Price is positive number!");
                } else if (square <= 0) {
                    itemValid = false;
                    request.setAttribute("ERROR_MESSAGE", "Square is positive number!");
                } else if (address.isEmpty()) {
                    itemValid = false;
                    request.setAttribute("ERROR_MESSAGE", "Address is not empty!");
                }
            }

            if (itemValid) {
                ItemDTO item = new ItemDTO();
                item.setName(title);
                item.setPrice(price);
                item.setSquare(square);
                item.setAddress(address);
                item.setCategory(category);
                item.setDescription(description);
                if (ItemDAO.updateItem(item, ID)) {
                    url = SUCCESS + "?itemID=" + ID + "&action=Edit";
                    request.setAttribute("MESSAGE", "Successful!");
                }
            } else {
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at UpdateItemController: " + e.toString());
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
