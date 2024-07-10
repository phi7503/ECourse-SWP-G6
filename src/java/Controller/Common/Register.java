/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Common;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.*;
import DAO.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author hi2ot
 */
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {       
        SEQuestionDAO.INS.load();
        request.setAttribute("SQuestionList", SEQuestionDAO.INS.getQel());
        request.getRequestDispatcher("/Web/Register.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String Username = request.getParameter("username");
            String Password = request.getParameter("pass");
            String Confirm = request.getParameter("confirm");
            String Mail = request.getParameter("mail");
            String name = request.getParameter("fullname");
            String dob = request.getParameter("dob");
            int SQ = Integer.parseInt(request.getParameter("SQ"));
            String Answer = request.getParameter("answer");
            int Role = Integer.parseInt(request.getParameter("role"));
            
            if (!Password.equals(Confirm)) {
                request.setAttribute("err", "Two password is not the same!");
                doGet(request, response);
                return;
            }
            
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);            
            KeySpec spec = new PBEKeySpec(Password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();       
            Base64.Encoder enc = Base64.getEncoder();
                        
            UserDAO.INS.addUser(Username, enc.encodeToString(hash), enc.encodeToString(salt), Mail, name, dob, SQ, Answer, Role);
            
            request.getRequestDispatcher("Web/Login.jsp").forward(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
