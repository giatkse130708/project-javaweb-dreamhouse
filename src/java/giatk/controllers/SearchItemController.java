/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.controllers;

import giatk.daos.ItemDAO;
import giatk.dtos.ItemDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kha Gia
 */
public class SearchItemController extends HttpServlet {

    private final String SUCCESS = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            ArrayList<ItemDTO> listItemSearch;
            String city = request.getParameter("city");
            long minPrice = 0;
            long maxPrice = 0;
            String category = request.getParameter("category");

            try {
                minPrice = Long.parseLong(request.getParameter("minPrice"));
                if(minPrice < 0){
                    request.setAttribute("ERROR_MESSAGE", "Note: Price is larger than 0");
                }
            } catch (Exception e) {
            }

            try {
                maxPrice = Long.parseLong(request.getParameter("maxPrice"));
                if(maxPrice < 0){
                    request.setAttribute("ERROR_MESSAGE", "Note: Price is larger than 0");
                }
            } catch (Exception e) {
            }

            request.setAttribute("CITY", city);
            request.setAttribute("CATEGORY", category);
            if (!request.getParameter("minPrice").isEmpty()) {
                request.setAttribute("MIN_PRICE", minPrice);
            }
            if (!request.getParameter("maxPrice").isEmpty()) {
                request.setAttribute("MAX_PRICE", maxPrice);
            }
            listItemSearch = ItemDAO.searchItem(city, minPrice, maxPrice, category);
            request.setAttribute("LIST_ITEM", listItemSearch);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at SearchItemController: " + e.toString());
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
