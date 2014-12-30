package com.TheLongestJourney;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet(
name="journeyServlet",
urlPatterns={"/index","/journeys","/about","/account","/contact","/map","/addJourney","/login","/register", "/journey","/deleteJourney","/editJourney","" })


public class journeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public journeyServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String action = null;
	action = request.getParameter("action");
	if(action==null || action.equals("journeys")){
		
		displayJourney("from Journey",request,response);
		
	}
	else if(action.equals("journey")){
		
		
		Integer id[] = {Integer.valueOf(request.getParameter("id"))};
		
		List<Journey> JourneyList = myQuery("from Journey where id= ?", id);
		
		Journey item = JourneyList.get(0);
		
		request.setAttribute("item", item);
		
		request.getRequestDispatcher("/WEB-INF/jsp/view/journey.jsp").forward(request, response);
		
	}
	
	else if(action.equals("addJourney")){
		String user = getUserName(request);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/jsp/view/addJourney.jsp").forward(request, response);

	}
	else if(action.equals("map")){
		
		List<Journey> PlaceList = myQuery("from Journey");
		Gson gson = new GsonBuilder().setExclusionStrategies(new mapExclusionStrategy()).create();
		String json = gson.toJson(PlaceList); 
		request.setAttribute("places", json);
		
		request.getRequestDispatcher("/WEB-INF/jsp/view/map.jsp").forward(request, response);
		
	}
	else if(action.equals("contact")){
		request.getRequestDispatcher("/WEB-INF/jsp/view/contact.jsp").forward(request, response);
		
	}
	else if(action.equals("about")){
		request.getRequestDispatcher("/WEB-INF/jsp/view/about.jsp").forward(request, response);
		
	}
	else if(action.equals("login")){
		request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
		
	}
	else if(action.equals("register")){
		request.getRequestDispatcher("/WEB-INF/jsp/view/register.jsp").forward(request, response);
		
	}
	else if(action.equals("account")){
		String user = getUserName(request);
		if(user==null){
			request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
		}
		else{
		
			List<UserData> userData = myQuery("from UserData where user= ?", new String[]{user});
			request.setAttribute("userData", userData.get(0));
			request.getRequestDispatcher("/WEB-INF/jsp/view/account.jsp").forward(request, response);
		}
	}
	

	
	else if(action.equals("deleteCookie")){

	/*	Cookie logedInCookie = new Cookie("user", URLEncoder.encode("", "UTF-8"));
		logedInCookie.setMaxAge(0);
		response.addCookie(logedInCookie);*/
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("index");
	}
	
	else if(action.equals("editJourney")){
		int id = Integer.valueOf(request.getParameter("id"));
		List<Journey> journeyList= myQuery("from Journey where id = ?",new Integer[]{id});
		
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-M-dd");
		String data = journeyList.get(0).getData().toString(fmt);
		
		DateTimeFormatter fmt2 = DateTimeFormat.forPattern("H:mm");
		String godzina = journeyList.get(0).getData().toString(fmt2);
		

		  int dni = Days.daysBetween(journeyList.get(0).getData(), journeyList.get(0).getCzas()).getDays();
		  int godziny = Hours.hoursBetween(journeyList.get(0).getData(), journeyList.get(0).getCzas()).getHours() % 24;
		  int minuty = Minutes.minutesBetween(journeyList.get(0).getData(), journeyList.get(0).getCzas()).getMinutes() % 60;
		  
		
		
		
		request.setAttribute("journey",journeyList.get(0));
		request.setAttribute("data", data);
		request.setAttribute("godzina", godzina);
		request.setAttribute("dni", dni);
		request.setAttribute("godziny", godziny);
		request.setAttribute("minuty", minuty);
		request.getRequestDispatcher("WEB-INF/jsp/view/editJourney.jsp").forward(request, response);
	}
	
	else if(action.equals("deleteJourney")){
		int id = Integer.valueOf(request.getParameter("id"));
		myDbUpdate("delete from Journey where id='" + id +"'");
		response.sendRedirect("index");
	}
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = null;
		action = request.getParameter("option");
		if(action.equals("letter")){
			String mail = request.getParameter("email");
			List<Newsletter> newsletterList = myQuery("from Newsletter where email= ? ",new String[]{mail});
			if(newsletterList.size()==1){
				request.setAttribute("sucess", false);
				displayJourney("from Journey",request,response);
			}
			else{
				
				addNewsletterToDB(request);
				request.setAttribute("sucess", true);
				displayJourney("from Journey",request,response);
			}
			
		}
		else if(action.equals("register")){
			String user = request.getParameter("login");
			String mail = request.getParameter("email");
			
			List<User> userList = myQuery("from User where user=? ",new String[]{user});
			List<User> mailList = myQuery("from User where email=? ",new String[]{mail});
			
			if(userList.size()==0 && mailList.size()==0){
				addUserToDB(request);
				request.getRequestDispatcher("/WEB-INF/jsp/view/registerSucess.jsp").forward(request, response);
			}
			else{
				if(userList.size()>0){
					request.setAttribute("suces", false);
				}
				else{
					request.setAttribute("suces", true);
				}
				if(mailList.size()>0){
					request.setAttribute("sucess2", false);
				}
				else{
					request.setAttribute("sucess2", true);
				}
				request.getRequestDispatcher("/WEB-INF/jsp/view/register.jsp").forward(request, response);
				
			}
			
			
		}
		else if(action.equals("login")){
			String user = request.getParameter("login");
			String password = request.getParameter("haslo");
			

			
			List<User> users = myQuery("from User where user= ? ",new String[]{user});
			
			if(users.size()<=0){
				request.setAttribute("sucess", false);
				request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
				
			}
			else{
				if(BCrypt.checkpw(password, users.get(0).getPassword())){
				//	Cookie logedInCookie = new Cookie("user", URLEncoder.encode(user, "UTF-8"));
					//response.addCookie(logedInCookie);
				//	response.sendRedirect("index");
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					response.sendRedirect("index");
				}
				else{
					request.setAttribute("sucess", false);
					request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
					
				}
			} ;
			
		}
		else if(action.equals("editUser")){
			String user = getUserName(request);
			
			UserData newData = new UserData();
			
			newData.setAbout(request.getParameter("about"));
			newData.setAge(Integer.valueOf(request.getParameter("age")));
			newData.setGender(Boolean.parseBoolean(request.getParameter("plec")));
			newData.setName(request.getParameter("name"));
			newData.setPhone(request.getParameter("phone"));
			newData.setSurname(request.getParameter("surename"));
			
			
			Sports sport = new Sports();
			sport.setBabington(Boolean.parseBoolean(request.getParameter("babington")));
			sport.setJazda_konna(Boolean.parseBoolean(request.getParameter("jazda_konna")));
			sport.setLucznictwo(Boolean.parseBoolean(request.getParameter("lucznictwo")));
			sport.setBaseball(Boolean.parseBoolean(request.getParameter("baseball")));
			sport.setBieganie(Boolean.parseBoolean(request.getParameter("bieganie")));
			sport.setKoszykowka(Boolean.parseBoolean(request.getParameter("koszykowka")));
			sport.setJazda_rowerem(Boolean.parseBoolean(request.getParameter("jazda_rowerem")));
			sport.setKajakarstwo(Boolean.parseBoolean(request.getParameter("kajakarstwo")));
			sport.setWspinaczka(Boolean.parseBoolean(request.getParameter("wspinaczka")));
			sport.setTaniec(Boolean.parseBoolean(request.getParameter("taniec")));
			sport.setNurkowanie(Boolean.parseBoolean(request.getParameter("nurkowanie")));
			sport.setSzermierka(Boolean.parseBoolean(request.getParameter("szermierka")));
			sport.setWedkarstwo(Boolean.parseBoolean(request.getParameter("wedkarstwo")));
			sport.setPilka_nozna(Boolean.parseBoolean(request.getParameter("pilka_nozna")));
			sport.setGeocaching(Boolean.parseBoolean(request.getParameter("geocaching")));
			sport.setGolf(Boolean.parseBoolean(request.getParameter("golf")));
			sport.setHokey(Boolean.parseBoolean(request.getParameter("hokey")));
			sport.setLyzwiarstwo(Boolean.parseBoolean(request.getParameter("lyzwiarstwo")));
			sport.setSztuki_walki(Boolean.parseBoolean(request.getParameter("sztuki_walki")));
			sport.setMedytacja(Boolean.parseBoolean(request.getParameter("medytacja")));
			sport.setSpadochroniarstwo(Boolean.parseBoolean(request.getParameter("spadochroniarstwo")));
			sport.setPolo(Boolean.parseBoolean(request.getParameter("polo")));
			sport.setWioslarstwo(Boolean.parseBoolean(request.getParameter("wioslarstwo")));
			sport.setStrzelectwo(Boolean.parseBoolean(request.getParameter("strzelectwo")));
			sport.setSkateboarding(Boolean.parseBoolean(request.getParameter("skateboarding")));
			sport.setRolkarstwo(Boolean.parseBoolean(request.getParameter("rolkarstwo")));
			sport.setNarciarstwo(Boolean.parseBoolean(request.getParameter("narciarstwo")));
			sport.setSnowboarding(Boolean.parseBoolean(request.getParameter("snowboarding")));
			sport.setSurfing(Boolean.parseBoolean(request.getParameter("surfing")));
			sport.setPlywanie(Boolean.parseBoolean(request.getParameter("plywanie")));
			sport.setTennis(Boolean.parseBoolean(request.getParameter("tennis")));
			sport.setTrekking(Boolean.parseBoolean(request.getParameter("trekking")));
			sport.setVolleyball(Boolean.parseBoolean(request.getParameter("volleyball")));
			sport.setChodzenie(Boolean.parseBoolean(request.getParameter("chodzenie")));
			sport.setPodnoszenie(Boolean.parseBoolean(request.getParameter("podnoszenie")));
			sport.setZeglarstwo(Boolean.parseBoolean(request.getParameter("zeglarstwo")));
			
			newData.setSport(sport);
			updateUserData(user,newData);
			
			
		}	
		else if(action.equals("search")){
			
			String search = "from Journey as journey";	
			
			search+= " where nazwa like '%"+request.getParameter("searching")+"%'";
			if(request.getParameter("zawansowaneSzukanie")!= null){

				
				
				if(!request.getParameter("state").equals("wszystkie")){
					
					if(request.getParameter("state").equals("oczekuje")){
						search+=" and data > now()";
					}
					else if(request.getParameter("state").equals("w trakcie")){
						search+=" and now() between data and czas";
					}
					else if(request.getParameter("state").equals("ukonczony")){
						search+=" and czas < now()";
					}
					
				}
				
				if(!request.getParameter("rodzaj").equals("wszystkie")){
					search+=" and journey.sports."+request.getParameter("rodzaj")+"=1";
				}
			
				
				if(request.getParameter("wedlugDaty")!= null && request.getParameter("alfabetycznie")!= null){
						search+= " order by data,nazwa";
						
					}
				
				else{
					if(request.getParameter("wedlugDaty")!= null){
						search+= " order by data";
					}
				
					if(request.getParameter("alfabetycznie")!= null ){
						search+=" order by nazwa";
					}
				}
				
			}
			displayJourney(search, request, response);
		}
	}

	
	public String getUserName(HttpServletRequest request){
		String user = null;
		/*try {
			Cookie[] users = request.getCookies();
			for (Cookie ck : users) {
				if (ck.getName().equals("user")) {
					try {
						user = URLDecoder.decode(ck.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (NullPointerException e) {
			user = "none";
		}*/

		HttpSession session = request.getSession(false);
		if(session!=null){  
		user = (String) session.getAttribute("user");
		}
	

		return user;
	}
	
	
	
	
	public synchronized void addUserToDB(HttpServletRequest request){
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		
		Transaction tx = s.beginTransaction();
		User use1 = new User();
		UserData usedat1 = new UserData();
		Sports sport1 = new Sports();
		
		use1.setUser(request.getParameter("login"));
		use1.setEmail(request.getParameter("email"));
		use1.setStempel(DateTime.now());
		usedat1.setEmail(request.getParameter("email"));
		usedat1.setUser(request.getParameter("login"));
		usedat1.setPhoto("map_app.png");

		
		use1.setUserDataObject(usedat1);
		usedat1.setSport(sport1);
		usedat1.setUserObject(use1);
		
		
		String encrypction = BCrypt.hashpw(request.getParameter("haslo"), BCrypt.gensalt());
		use1.setPassword(encrypction);
		
		s.save(use1);
		s.save(sport1);
		s.save(usedat1);
		
		
		
		
		s.flush();
		tx.commit();
		
		s.close();
		
		
	}
	
	public synchronized void addNewsletterToDB(HttpServletRequest request){
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Newsletter news1 = new Newsletter();
		news1.setEmail(request.getParameter("email"));
		news1.setStempel(DateTime.now());
		
		s.save(news1);
		s.flush();
		tx.commit();
		s.close();
		
	}
	
	
	public static <T> List<T> myQuery(String query, Object[] parameters){
		
		
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		
		Query q = s.createQuery(query);
		for(int a=0; a<parameters.length; a++){
			q.setParameter(a, parameters[a]);
		}
		
		
		List myList = q.list();
		
		s.close();
		return myList;
		
	}	
	
	public static <T> List<T> myQuery(String query){
		
		
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		
		Query q = s.createQuery(query);
		List myList = q.list();
		
		s.close();
		return myList;
		
	}
	
	public synchronized static void myDbUpdate(String action){
		
		
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		
		Query q = s.createQuery(action);
		q.executeUpdate();
		s.close();
		
	}	
	
	
	public synchronized void updateUserData(String user, UserData newData){
		
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistry =  new StandardServiceRegistryBuilder().applySettings(
	            cfg.getProperties()).build();
		
		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
		
		List<User> UserList = myQuery("from User where user=? ",new String[]{user});
		User item = UserList.get(0);
		
		UserData data = (UserData)s.get(UserData.class, item.getUser_id());

		s.beginTransaction();
		newData.getSport().setSport_id((data.getSport().getSport_id()));
		data.setSport(newData.getSport());
		
		data.setAbout(newData.getAbout());
		data.setAge(newData.getAge());
		data.setGender(newData.getGender());
		data.setName(newData.getName());
		data.setPhone(newData.getPhone());
		data.setSurname(newData.getSurname());

		s.merge(data);
		s.flush();

		s.getTransaction().commit();
		s.close();
		
		
	}
	
	public void displayJourney(String query, HttpServletRequest request , HttpServletResponse response){
		List<Journey> journeyList = myQuery(query);

		int size = journeyList.size();
		int pageNumbers = (int) Math.ceil(size/10.0);
		
		String page = request.getParameter("page");
		if(page==null){
			page="1";
		}
		
		String user = getUserName(request);
		/////////////
		request.setAttribute("user", user);
		request.setAttribute("journeyList", journeyList);
		request.setAttribute("size", size);
		request.setAttribute("page", page);
		request.setAttribute("pageNumbers", pageNumbers);
		request.setAttribute("currentTime", System.currentTimeMillis());
		request.setAttribute("env",System.getenv("OPENSHIFT_DATA_DIR")
				+ File.separator
				+ "images"
				+ File.separator
				+ "journeyProfile" + File.separator + "fileName");
		try {
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    public class mapExclusionStrategy implements ExclusionStrategy {

		public boolean shouldSkipClass(Class<?> arg0) {
			return false;
		}

		public boolean shouldSkipField(FieldAttributes field) {
			
			return (field.getDeclaringClass() == Journey.class && field.getName().equals("journeyPhotos"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("sports"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("stempel"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("data"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("opis"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("trasa"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("trudnosc"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("czas"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("sprzet"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("ludzie"))||
					(field.getDeclaringClass() == Journey.class && field.getName().equals("koszt"));
		}

    
    }
    
    
	
	
	

}

