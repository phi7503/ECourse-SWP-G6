/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.TestContent;

import DAO.*;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.Scanner;
import java.util.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
/**
 *
 * @author hi2ot
 */
public class ImportQuestion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int CourseID = -1;
        int LessonID = -1;
        int QuizID = -1;

        try {
            CourseID = Integer.parseInt(request.getParameter("CourseID"));
            LessonID = Integer.parseInt(request.getParameter("LessonID"));
            QuizID = Integer.parseInt(request.getParameter("QuizID"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/404.html");
            return;
        }

        Course curCourse = CourseDAO.INS.getCourseById(CourseID);
        Lesson curLesson = LessonDAO.INS.getLessonByID(CourseID, LessonID);
        Quiz curQuiz = QuizDAO.INS.getQuiz(CourseID, LessonID, QuizID);

        request.setAttribute("curCourse", curCourse);
        request.setAttribute("curLesson", curLesson);
        request.setAttribute("curQuiz", curQuiz);
        request.getRequestDispatcher("/Web/ImportQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("filename");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = "E:\\PRJ301\\JPECourse\\web\\importfile";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        File file = new File(uploadPath + File.separator + fileName);
        filePart.write(file.getAbsolutePath());

        Scanner sc = new Scanner(file);

        int CourseID = Integer.parseInt(request.getParameter("CourseID"));
        int LessonID = Integer.parseInt(request.getParameter("LessonID"));
        int QuizID = Integer.parseInt(request.getParameter("QuizID"));

        List<Question> QuestionList = new ArrayList<>();
        List<Answer> AnswerList = new ArrayList<>();        
        int cnt = 0;
        try {
            while (sc.hasNextLine()) {                
                cnt++;
                String Question = sc.nextLine();                
                String Answer1 = sc.nextLine();                
                String Answer2 = sc.nextLine();
                String Answer3 = sc.nextLine();
                String Answer4 = sc.nextLine();
                int Correct = Integer.parseInt(sc.nextLine());
                String Explannation = sc.nextLine();
                int QuestionID = QuestionDAO.INS.getQuestionList(CourseID, LessonID, QuizID).size() + cnt;
                QuestionList.add(new Question(CourseID, LessonID, QuizID, QuestionID, Question, Explannation, 1));
                AnswerList.add(new Answer(CourseID, LessonID, QuizID, QuestionID, 1, Answer1, (Correct == 1) ? 2 : 1));
                AnswerList.add(new Answer(CourseID, LessonID, QuizID, QuestionID, 2, Answer2, (Correct == 2) ? 2 : 1));
                AnswerList.add(new Answer(CourseID, LessonID, QuizID, QuestionID, 3, Answer3, (Correct == 3) ? 2 : 1));
                AnswerList.add(new Answer(CourseID, LessonID, QuizID, QuestionID, 4, Answer4, (Correct == 4) ? 2 : 1));
            }
        } catch (Exception e) {
            request.setAttribute("inform", "Wrong Format!");
            doGet(request, response);
            return;
        }
        sc.close();
        PrintWriter out = response.getWriter();
        for (Question ques : QuestionList) {
            QuestionDAO.INS.ImportQuestion(CourseID, LessonID, QuizID, ques.getQuestionID(), ques.getQuestion(), ques.getExplaination());                        
        }
        for (Answer ans : AnswerList) {
            QuestionDAO.INS.ImportAnswer(CourseID, LessonID, QuizID, ans.getQuestionID(), ans.getAnswerID(), ans.getDescription(), ans.getRole());
        }
        request.setAttribute("CourseID", CourseID);
        request.setAttribute("LessonID", LessonID);
        request.setAttribute("QuizID", QuizID);
        request.getRequestDispatcher("/QuestionList").forward(request, response);
    }
}
