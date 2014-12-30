package com.TheLongestJourney;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sendMailServlet")
public class sendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private sendMail mail;

	
    public sendMailServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		mail = new sendMail();
		
		String to = "pan-towarzysz@wp.pl";
		String name = request.getParameter("imie"); 
		String mailAdress = request.getParameter("mail"); 
		String subject = request.getParameter("temat");
		String body = request.getParameter("tresc");
		mail.sendEmail(to,name,mailAdress, subject, body);
		
		request.getRequestDispatcher("/WEB-INF/jsp/view/contact.jsp").forward(request, response);
		
	}

}
