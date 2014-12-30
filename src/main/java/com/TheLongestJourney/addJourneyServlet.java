package com.TheLongestJourney;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.joda.time.DateTime;
//
@WebServlet("/addJourneyServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 50, // 50 MB
maxRequestSize = 1024 * 1024 * 100)
// 100 MB
public class addJourneyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public addJourneyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		saveJourney(request);
		response.sendRedirect("index");
	}

	public synchronized void addJourneyToDB(String[] parameters, ArrayList<String> images, DateTime czas, Place place, DateTime czasPodrozy, Sports sport, boolean saveOrUpdate, String id)
			throws IOException, ServletException {
		Configuration cfg = new Configuration();
		cfg.configure("Hibernate.cfg.xml");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).build();

		SessionFactory sf = cfg.buildSessionFactory(serviceRegistry);
		Session s = sf.openSession();
	    Transaction tx = s.beginTransaction();
		
	    Journey joy1 = null;
	    
		if(saveOrUpdate==true){
			joy1 = new Journey();

		}
		else{
			List<Journey> JourneyList = journeyServlet.myQuery("from Journey where id=? ",new Integer[]{Integer.valueOf(id)});
			Journey item = JourneyList.get(0);
			
			joy1 = (Journey)s.get(Journey.class, item.getId());
			place.setId(joy1.getPlaceObject().getId());
			sport.setSport_id(joy1.getSports().getSport_id());
		}
		
		joy1.setCzas(czasPodrozy);
		joy1.setNazwa(parameters[1]);
		joy1.setKoszt(parameters[2]);
		joy1.setLudzie(Integer.parseInt(parameters[3]));
		joy1.setOpis(parameters[4]);
		joy1.setOrganizator(parameters[5]);
		joy1.setSprzet(parameters[7]);
		joy1.setTrasa(parameters[8]);
		joy1.setTrudnosc(parameters[9]);
		if(!parameters[10].equals("")){
			joy1.setZdjecie(parameters[10]);
		}
		joy1.setData(czas);

		joy1.setPlaceObject(place);
		joy1.setSports(sport);
		joy1.setStempel(DateTime.now());

		if(saveOrUpdate==false){
			s.merge(joy1);
			s.merge(place);
			s.merge(sport);
		}
		else{
			s.save(joy1);
			s.save(place);
			s.save(sport);
		}
		s.flush();
		

			if(!images.get(0).equals("")&&joy1.getJourneyPhotos()!=null){
				if(saveOrUpdate==false){
					journeyServlet.myDbUpdate("delete from JourneyPhotos where id_journey =" + id);
				}
				for(int a=0; a<images.size(); a++){
					JourneyPhotos jou = new JourneyPhotos();
					jou.setPhoto(images.get(a));
					jou.setJourney(joy1);
					joy1.getJourneyPhotos().add(jou);
					s.save(jou);
				}
			}
		
		

		s.flush();
		tx.commit();
		s.close();
		
	}

	public List<FileItem> openApacheComonsUpload(HttpServletRequest request) {
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletContext servletContext = this.getServletConfig()
				.getServletContext();
		File repository = (File) servletContext
				.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return items;
	}

	public void saveJourney(HttpServletRequest request) throws IOException,
			ServletException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {

			List<FileItem> items = openApacheComonsUpload(request);

			String values[] = new String[14];
			ArrayList<String> images = new ArrayList<String>();
			Place place = new Place();
			String data = null;
			String godzina = null;
			DateTime czasPodrozy = null;
			boolean saveOrUpdate = true;
			String id = null;
			Sports sport = new Sports();
			
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();

				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					
					if(fieldName.equals("daySpinner"))
						values[11] = item.getString("UTF-8");
					if(fieldName.equals("hourSpinner"))
						values[12] = item.getString("UTF-8");
					if(fieldName.equals("minuteSpinner"))
						values[13] = item.getString("UTF-8");
					if(fieldName.equals("nazwa"))
						values[1] = item.getString("UTF-8");
					if(fieldName.equals("koszt")){
						values[2] = item.getString("UTF-8");
						if(values[2]==null){
							values[2]="";
						}
					}
					if(fieldName.equals("miejsce"))
						place.setPlace(item.getString("UTF-8"));
					if(fieldName.equals("opis"))
						values[4] = item.getString("UTF-8");
					if(fieldName.equals("organizator"))
						values[5] = item.getString("UTF-8");
					if(fieldName.equals("rodzaj")){
						values[6] = item.getString("UTF-8");
						if(values[6]==null){
							values[6]="";
						}
					}
					if(fieldName.equals("sprzet")){
						values[7] = item.getString("UTF-8");
						if(values[7]==null){
							values[7]="";
						}
					}
					if(fieldName.equals("trasa")){
						values[8] = item.getString("UTF-8");
						if(values[8]==null){
							values[8]="";
						}
					}

					if(fieldName.equals("trudnosc"))
						values[9] = item.getString("UTF-8");
					if(fieldName.equals("ludzie"))
						values[3] = item.getString("UTF-8");
					if(fieldName.equals("lat"))
						place.setLatitude(Double.parseDouble(item.getString("UTF-8")));
					if(fieldName.equals("lng"))
						place.setLongitude(Double.parseDouble(item.getString("UTF-8")));
					if(fieldName.equals("data"))
						data = item.getString("UTF-8");
					if(fieldName.equals("time"))
						godzina = item.getString("UTF-8");
					if(fieldName.equals("saveOrUpdate"))
						saveOrUpdate = Boolean.valueOf(item.getString("UTF-8"));
					if(fieldName.equals("id"))
						id = item.getString();
					if(fieldName.equals("babington"))
						sport.setBabington(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("jazda_konna"))
						sport.setJazda_konna(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("lucznictwo"))
						sport.setLucznictwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("baseball"))
						sport.setBaseball(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("bieganie"))
						sport.setBieganie(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("koszykowka"))
						sport.setKoszykowka(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("jazda_rowerem"))
						sport.setJazda_rowerem(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("kajakarstwo"))
						sport.setKajakarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("wspinaczka"))
						sport.setWspinaczka(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("taniec"))
						sport.setTaniec(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("nurkowanie"))
						sport.setNurkowanie(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("szermierka"))
						sport.setSzermierka(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("wedkarstwo"))
						sport.setWedkarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("pilka_nozna"))
						sport.setPilka_nozna(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("geocaching"))
						sport.setGeocaching(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("golf"))
						sport.setGolf(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("hokey"))
						sport.setHokey(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("lyzwiarstwo"))
						sport.setLyzwiarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("sztuki_walki"))
						sport.setSztuki_walki(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("medytacja"))
						sport.setMedytacja(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("spadochroniarstwo"))
						sport.setSpadochroniarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("polo"))
						sport.setPolo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("wioslarstwo"))
						sport.setWioslarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("strzelectwo"))
						sport.setStrzelectwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("skateboarding"))
						sport.setSkateboarding(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("rolkarstwo"))
						sport.setRolkarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("narciarstwo"))
						sport.setNarciarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("snowboarding"))
						sport.setSnowboarding(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("surfing"))
						sport.setSurfing(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("plywanie"))
						sport.setPlywanie(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("tennis"))
						sport.setTennis(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("trekking"))
						sport.setTrekking(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("volleyball"))
						sport.setVolleyball(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("chodzenie"))
						sport.setChodzenie(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("podnoszenie"))
						sport.setPodnoszenie(Boolean.parseBoolean(item.getString("UTF-8")));
					if(fieldName.equals("zeglarstwo"))
						sport.setZeglarstwo(Boolean.parseBoolean(item.getString("UTF-8")));
				}
//////
				else {

					try {
					if (item.getFieldName().equals("zdjecie")) {
						String fileName = item.getName();
						values[10] = fileName;
						File file = new File(System.getenv("OPENSHIFT_DATA_DIR")
								+ File.separator
								+ "uploads"
								+ File.separator
								+ "journeyProfile" + File.separator + fileName);
						try {
							item.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
						uploadServlet.resizeAndSave(request, file, 500, "journeyProfile", fileName);
						uploadServlet.resizeAndSave(request, file, 250, "journeyProfileMini", fileName);
					} else {
						String fileName = item.getName();
						images.add(fileName);
						File file = new File(System.getenv("OPENSHIFT_DATA_DIR")
								+ File.separator
								+ "uploads"
								+ File.separator
								+ "journeyImages" + File.separator + fileName);
						try {
							item.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
						uploadServlet.resizeAndSave(request, file, 200, "journeyImagesMini", fileName);
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
			}
			
			
			int indeks = godzina.indexOf(":");
			int hour = Integer.parseInt(godzina.substring(0,indeks));
			int min = Integer.parseInt(godzina.substring(indeks+1));
			
			indeks = data.indexOf("-");
			int year = Integer.parseInt(data.substring(0,indeks));
			data = data.substring(indeks+1);
			indeks = data.indexOf("-");
			int month = Integer.parseInt(data.substring(0,indeks));
			data = data.substring(indeks+1);
			int day = Integer.parseInt(data);
			
			DateTime czas = new DateTime(year, month, day, hour, min);
			czasPodrozy = new DateTime(year, month, day, hour, min);
			czasPodrozy = czasPodrozy.plusDays(Integer.valueOf(values[11]));
			czasPodrozy = czasPodrozy.plusHours(Integer.valueOf(values[12]));
			czasPodrozy = czasPodrozy.plusMinutes(Integer.valueOf(values[13]));
			
			addJourneyToDB(values, images, czas,place,czasPodrozy,sport,saveOrUpdate, id);
			
		}
	}

	
}
