package control;

import httpServer.WWW;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        int port = 8080;
        //String ip = "localhost";
        String ip = "100.85.100.41";

        if (args.length == 2) {
            port = Integer.parseInt(args[0]);
            ip = (args[1]);
        }

        WWW service = new WWW(port, ip);

        service.start();
        System.out.println("clean & build");

        /* ***************** for tjeck! 
         EntityManagerFactory emf;
         emf = Persistence.createEntityManagerFactory("AuthenticationServerPU");
         EntityManager em = emf.createEntityManager();
        
         *****************/
    }

}
