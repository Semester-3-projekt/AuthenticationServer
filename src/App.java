
import httpServer.WWW;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class App {
        public static void main(String[] args) throws IOException {
        
        WWW service = new WWW(8080);
        service.start();
        System.out.println("clean & build");
      
        
        /* ***************** for tjeck! 
       EntityManagerFactory emf;
       emf = Persistence.createEntityManagerFactory("AuthenticationServerPU");
       EntityManager em = emf.createEntityManager();
        
        *****************/
    
    }
    
}
