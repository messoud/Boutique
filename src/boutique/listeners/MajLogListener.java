package boutique.listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import boutique.controlers.Dispach;

/**
 * Application Lifecycle Listener implementation class MajLog
 *
 */
@WebListener
public class MajLogListener implements HttpSessionListener, HttpSessionAttributeListener , ServletContextListener{

    /**
     * Default constructor. 
     */
    public MajLogListener() {
        // TODO Auto-generated constructor stub
    }
    String text="";
    public void contextDestroyed(ServletContextEvent event) {
        try {
        	System.out.println("ici");
        	String logName = event.getServletContext().getRealPath("/log.txt");
        	System.out.println(logName);
            File file = new File(logName);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file,true);
            
            SimpleDateFormat formater = null;
            Date aujourdhui = new Date();
            formater = new SimpleDateFormat("'\nle' dd/MM/yyyy 'à' hh:mm:ss");
            text+="\nArrêt de l'application.";
            text+=formater.format(aujourdhui);
            fos.write(text.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
 
    }
 
    public void contextInitialized(ServletContextEvent event) {
        text+="Le ServletContext a été initialisé !\n";
        event.getServletContext().setAttribute("Test", "Coucou !");
        System.out.println("init");
        SimpleDateFormat formater = null;

        Date aujourdhui = new Date();

        formater = new SimpleDateFormat("'\nle' dd/MM/yyyy 'à' hh:mm:ss");
        text+=formater.format(aujourdhui);
        
       	String logName = event.getServletContext().getRealPath("/log.txt");
    	System.out.println(logName);
    }
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
  
			text+="Nouvelle session \n";
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    	text+="Arrêtt session \n";
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	text+="Ajout attribut : "+arg0.getName()+ " = "+arg0.getValue()+ "\n";
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	text+="Suppr attribut : "+arg0.getName()+ " = "+arg0.getValue()+ "\n";
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    	text+="Remplace attribut : "+arg0.getName()+ " = "+arg0.getValue()+ "\n";
    }
	
}
