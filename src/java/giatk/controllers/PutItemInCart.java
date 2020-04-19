/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.controllers;

import giatk.daos.CartDAO;
import giatk.daos.ItemDAO;
import giatk.dtos.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kha Gia
 */
public class PutItemInCart extends HttpServlet {

    private final String SUCCESS = "GetDetailItemController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            int itemID = Integer.parseInt(request.getParameter("itemID"));
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            int cardID;

            boolean hasCart = (CartDAO.getCartID(user.getUserID())) != -1;
            if (!hasCart) {
                if (CartDAO.insertNewCard(user.getUserID())) {
                    hasCart = true;
                } else {
                    hasCart = false;
                }
            } //Nếu đã có giỏ hàng thì add sản phẩm vào giỏ hàng, cập nhật statusItem
            if (hasCart) {
                cardID = CartDAO.getCartID(user.getUserID());
                if (CartDAO.hasItemInCart(itemID, cardID)) {
                    request.setAttribute("MESSAGE", "This item is already in your cart!");
                } else if ((ItemDAO.isAvailable(itemID) == false)) {
                    request.setAttribute("MESSAGE", "Someone has already put it!");
                } else if (CartDAO.putItemInCart(itemID, cardID)) {
                    ItemDAO.updateIsAvailableItem(itemID, false); //Cập nhật lại status, false nghĩa mình đã lấy sản phẩm này, ko ai lấy đc nữa
                    request.setAttribute("MESSAGE", "Successful!");
                } else {
                    request.setAttribute("MESSAGE", "Sorry! Cannot put it in your cart!");
                }
            } else {
                request.setAttribute("MESSAGE", "Sorry! We cannot make your cart, please contact with coder!");
            }

            url = SUCCESS + "?itemID=" + itemID;
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            log("Error at PutItemInCart: " + e.toString());
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
