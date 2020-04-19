/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.filters;

import giatk.dtos.UserDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kha Gia
 */
public class AuthFilter implements Filter {
    
    private static final boolean debug = true;
    private final List<String> userDecentralization;
    private final List<String> adminDecentralization;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AuthFilter() {
        userDecentralization = new ArrayList<>();
        userDecentralization.add("index.jsp");
        userDecentralization.add("GetItemController");
        userDecentralization.add("GetDetailItemController");
        userDecentralization.add("LogoutController");
        userDecentralization.add("login.jsp");
        userDecentralization.add("LoginController");
        userDecentralization.add("details.jsp");
        userDecentralization.add("SearchItemController");
        userDecentralization.add("error.jsp");
        
        userDecentralization.add("cart.jsp");
        userDecentralization.add("ShowItemInCartController");
        userDecentralization.add("PutItemInCart");
        userDecentralization.add("RemoveItemInCart");
        
        adminDecentralization = new ArrayList<>();
        adminDecentralization.add("index.jsp");
        adminDecentralization.add("GetItemController");
        adminDecentralization.add("GetDetailItemController");
        adminDecentralization.add("LogoutController");
        adminDecentralization.add("login.jsp");
        adminDecentralization.add("LoginController");
        adminDecentralization.add("AdminController");
        adminDecentralization.add("addNewItem.jsp");
        adminDecentralization.add("InsertItemController");
        adminDecentralization.add("editItem.jsp");
        adminDecentralization.add("editImageItem.jsp");
        adminDecentralization.add("UpdateItemController");  
        adminDecentralization.add("UpdateImageController");
        adminDecentralization.add("SearchItemController");
        adminDecentralization.add("error.jsp");
        
        adminDecentralization.add("cart.jsp");
        adminDecentralization.add("ShowItemInCartController");
        adminDecentralization.add("PutItemInCart");
        adminDecentralization.add("RemoveItemInCart");
        
        
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }
    }


    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();

            log("URI: " + uri);
            if ((uri.contains(".js") || uri.contains(".jpg") || uri.contains(".gif") ||
                    uri.contains(".jpeg") || uri.contains(".png") || uri.contains(".css")) && !uri.contains(".jsp")) {
                chain.doFilter(request, response); //những định dạng khác .jsp thì cho qua.
                return;
            } else {
                if (uri.contains("login.jsp") || uri.contains("LoginController") ||
                        uri.contains("signupAccount.jsp") || uri.contains("SignUpController")) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            int index = uri.lastIndexOf("/");
            String resource = uri.substring(index + 1);
            HttpSession session = req.getSession();
            if (session == null || session.getAttribute("USER") == null) {
                res.sendRedirect("login.jsp");
            } else {
                UserDTO dto = (UserDTO) session.getAttribute("USER");
                String role = (String) dto.getRole();
                if (role.equals("admin") && adminDecentralization.contains(resource)) {
                    chain.doFilter(request, response);
                } else if (!role.equals("admin") && userDecentralization.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("LogoutController");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
