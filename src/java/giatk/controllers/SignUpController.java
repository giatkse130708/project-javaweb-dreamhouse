/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.controllers;

import giatk.daos.UserDAO;
import giatk.dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kha Gia
 */
public class SignUpController extends HttpServlet {

    private static final String REQUEST_URL = "signupAccount.jsp";
    
    private final String EMAIL_REGULAR_EXPRESSION = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; 
    //Java email validation permitted by RFC 5322
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = REQUEST_URL;
        try {
            boolean isValid = true;
            String userID = request.getParameter("userID");
            request.setAttribute("userID", userID);
            String password = request.getParameter("password");
            request.setAttribute("password", password);
            String rePassword = request.getParameter("rePassword");
            request.setAttribute("rePassword", rePassword);
            String fullname = request.getParameter("fullName");
            request.setAttribute("fullName", fullname);
            String phoneNumber = request.getParameter("phoneNumber");
            request.setAttribute("phoneNumber", phoneNumber);
            String identifyCard = request.getParameter("identifyCard");
            request.setAttribute("identifyCard", identifyCard);
            String email = request.getParameter("email");
            request.setAttribute("email", email);
            if(userID.isEmpty()){
                request.setAttribute("ERROR_MESSAGE", "Username cannot empty!");
                isValid = false;
            }
            if(password.isEmpty()){
                request.setAttribute("ERROR_MESSAGE", "Password cannot empty!");
                isValid = false;
            }
            if(rePassword.isEmpty()){
                request.setAttribute("ERROR_MESSAGE", "Confirm password cannot empty!");
                isValid = false;
            }
            if(!password.equals(rePassword)){
                request.setAttribute("ERROR_MESSAGE", "Confirm password is incorect!");
                isValid = false;
            }
            if(fullname.isEmpty()){
                request.setAttribute("ERROR_MESSAGE", "Fullname cannot empty!");
                isValid = false;
            }
            try {
                if(Long.parseLong(phoneNumber) <= 0) {
                    request.setAttribute("ERROR_MESSAGE", "Phone number is invalid!");
                    isValid = false;
                }
            } catch (Exception e) {
                request.setAttribute("ERROR_MESSAGE", "Phone number is invalid!");
                isValid = false;
            }
            if(phoneNumber.length() < 9 || phoneNumber.length() > 12){
                request.setAttribute("ERROR_MESSAGE", "Phone number is 9 - 12 digits");
                isValid = false;
            }
            try {
                if(Long.parseLong(identifyCard) <= 0) {
                    request.setAttribute("ERROR_MESSAGE", "Identify card is invalid!");
                    isValid = false;
                }
            } catch (Exception e) {
                request.setAttribute("ERROR_MESSAGE", "Identify card is invalid!");
                isValid = false;
            }
            if(identifyCard.length() < 9 || identifyCard.length() > 12){
                request.setAttribute("ERROR_MESSAGE", "Identify card is 9 - 12 digits");
                isValid = false;
            }
            if(!email.matches(EMAIL_REGULAR_EXPRESSION)){
                request.setAttribute("ERROR_MESSAGE", "Email is invalid! Email validation permitted by RFC 5322");
                isValid = false;
            }
            
            if(isValid){
                UserDTO newUser = new UserDTO();
                newUser.setUserID(userID);
                newUser.setPassword(password);
                newUser.setPhoneNumber(phoneNumber);
                newUser.setEmail(email);
                newUser.setIdentifyCard(identifyCard);
                newUser.setUsername(fullname);
                if(UserDAO.addUser(newUser)){
                    request.setAttribute("SUCCESSFUL_MESSAGE", "Sign up is successful!");
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Username is existed!");
                }
            }
            url = REQUEST_URL;
        } catch (Exception e) {
            log("Error at SignUpController");
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
