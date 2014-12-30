package com.TheLongestJourney;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


@WebServlet(name = "uploadServlet",urlPatterns = {"/uploads/*", "/uploadServlet"})
@MultipartConfig(
fileSizeThreshold=1024*1024*10, 	// 10 MB 
maxFileSize=1024*1024*50,      	// 50 MB
maxRequestSize=1024*1024*100)  //100 MB
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public uploadServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String filePath = request.getRequestURI();
		 
		    File file = new File(System.getenv("OPENSHIFT_DATA_DIR") + filePath);
		    InputStream input = new FileInputStream(file);
		 
		    response.setContentLength((int) file.length());
		    response.setContentType(new MimetypesFileTypeMap().getContentType(file));
		 
		    OutputStream output = response.getOutputStream();
		    byte[] bytes = new byte[4096];
		    int read = 0;
		    while ((read = input.read(bytes, 0, 4096)) != -1) {
		        output.write(bytes, 0, read);
		        output.flush();
		    }
		 
		    input.close();
		    output.close();
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

////
			Part filepart = request.getPart("file");
			String filePath=System.getenv("OPENSHIFT_DATA_DIR")+File.separator+"uploads"+File.separator+"users"+File.separator+filepart.getName();
			filepart.write(filePath);
			String user = getUserName(request);
			updateUserData(user,filepart.getName());
			File sourceimage = new File(filePath);
			BufferedImage image = ImageIO.read(sourceimage);
			image = resizeImage(image,image.getType(),128);
			ImageIO.write(image, "jpg", new File(filePath));
			
			response.sendRedirect("index");

		
	}
//////
	
	public String getUserName(HttpServletRequest request) {
		String user = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			user = (String) session.getAttribute("user");
		}
		return user;
	}

	   public static BufferedImage resizeImage(BufferedImage originalImage, int type, int height){
			
		   int originalHeight = originalImage.getHeight();
		   int originalWidth = originalImage.getWidth();
		   double scaleSize=(double)height/originalHeight;
		   BufferedImage resizedImage = null;
		   resizedImage = new BufferedImage((int)((float)originalWidth*scaleSize), height, type);
		   
		   Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, (int)((float)originalWidth*scaleSize), height, null);
			g.dispose();
		 
			return resizedImage;
	   }
	   
	   public static void resizeAndSave(HttpServletRequest request, File file, int height, String folder, String fileName) throws IOException{
	   
		BufferedImage image = ImageIO.read(file);
		image = resizeImage(image,image.getType(), height);
		ImageIO.write(image, "jpg", new File(System.getenv("OPENSHIFT_DATA_DIR")
				+ File.separator
				+ "uploads"
				+ File.separator
				+ folder + File.separator + fileName));
	   }
	
	
public void updateUserData(String user, String photo){
		
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		
		List<User> JourneyList = journeyServlet.myQuery("from User where user= ? ",new String[]{user});
		User item = JourneyList.get(0);
		
		UserData data = (UserData)s.get(UserData.class, item.getUser_id());
		data.setPhoto(photo);
		
		s.update(data);
		s.flush();
		s.close();
		
	}

}
