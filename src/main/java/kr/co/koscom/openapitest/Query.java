/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.co.koscom.openapitest;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.koscom.openapitest.oauth.OAuthValueContainer;

/**
 *
 * @author heungjae
 */
public class Query extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        HashMap<String, OAuthValueContainer> oAuthMap;
        oAuthMap = (HashMap<String, OAuthValueContainer>) this.getServletConfig().getServletContext().getAttribute("oAuthMap");

        OAuthValueContainer oAuthValueContainer = oAuthMap.get(session.toString());

        String securitiesCompany = request.getParameter("company");
        String virtualAccountNo = request.getParameter("account");
        String[] accountCi = virtualAccountNo.split("\\:");
        String querytype = request.getParameter("querytype");
        String querycondition = request.getParameter("querycondition");
        String assettype = request.getParameter("assettype");
        String pageno = request.getParameter("pageno");

        System.out.println("securitiesCompany =[" + securitiesCompany + "],virtualAccountNo =[" + virtualAccountNo + "], querytype=[" + querytype + "], querycondition =[" + querycondition + "], assettype= [" + assettype + "], pageno= ["+pageno+"]");
       
        ExecuteQuery2 executeQuery = new ExecuteQuery2( securitiesCompany,  querytype, assettype, querycondition, oAuthValueContainer,  accountCi[0],  accountCi[1], pageno);
        ResultSet resultSet = executeQuery.ExecuteQuery();       
        // display response entity
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(resultSet.getStatus());
        response.getWriter().println();
        response.getWriter().println(resultSet.getMessage());
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
