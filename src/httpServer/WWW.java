package httpServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.CredentialJpaController;

public class WWW {

    private static EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("FinalDb"); //AuthenticationServerPU er test databasen, FinalDb er den på Oracle

    private final HttpServer server;
    
    public WWW(int port, String ip) throws IOException {
        
        server = HttpServer.create();
        server.bind(new InetSocketAddress(ip, port), 0);
        server.createContext("/connect", createContext());

//        server.createContext("/getExternalJson", JsonRedirect());   // kald efter extern json
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    private HttpHandler createContext() {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange he) throws IOException {
                String response = "";
                int status = 200;
                String method = he.getRequestMethod().toUpperCase();
                System.out.println(method);

                switch (method) {
                    case "GET":
                        System.out.println("tester1");
                        try {
                            String path = he.getRequestURI().getPath();
                            int lastIndex = path.lastIndexOf("/");
                            System.out.println("tester2");
                            if (lastIndex > 0) {
                                System.out.println("entering if(lastIndex > 0)");
                                String idStr = path.substring(lastIndex + 1);
                                System.out.println(idStr);  // kurt+xyz ; Virker hertil!    

                                String[] arr = idStr.split("\\+");
                                System.out.println("hej");
                                String user = arr[0];
                                System.out.println("test; " + user);
                                String pass = arr[1];
                                System.out.println("test: " + pass);

                                CredentialJpaController ctrl = new CredentialJpaController(emf);  // Kald til DB
                                response = ctrl.findCredential(user, pass);

                                System.out.println(response);  // hvis nummer mindre end 0, så printes tom "response" = mellemrum

                            } else {
                                //  response = CredentialManager.getUsersAsJSON();   // flere bruger som Json

                                System.out.println(response);
                                System.out.println("tester4");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("tester5");
                            response = "Id is not a number";
                            System.out.println(response);
                            status = 404;
                            nfe.getMessage();
                        }
                        //                    catch (NotFoundException nfe) {
                        //                        response = nfe.getMessage();
                        //                        status = 404;
                        //                    }
                        break;
                    /*
                 
                     case "POST":
                     try {
                     InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
                     BufferedReader br = new BufferedReader(isr);
                     String jsonQuery = br.readLine();
                        
                     Credential p = CredentialManager.addUserFromGson(jsonQuery);
                        
                     response = new Gson().toJson(p);
                     } catch(IllegalArgumentException iae) {
                     status = 400;
                     response = iae.getMessage();
                     }
                     catch(IOException e){
                     status = 500;
                     response = "Internal Server Problem";
                     }
                     break;
                 
                     case "DELETE":
                     try {
                     String path = he.getRequestURI().getPath();
                     int lastIndex = path.lastIndexOf("/");
                     if (lastIndex > 0) {
                     int id = Integer.parseInt(path.substring(lastIndex + 1));
                     Credential pDeleted = CredentialManager.delete(id);
                     response = new Gson().toJson(pDeleted);
                     }
                     else{
                     status = 400;
                     response = "<h1>Bad Request</h1>No id supplied with request";
                     }
                 
                     }
                     //                catch(NotFoundException nfe) {
                     //                    status = 404;
                     //                    response = nfe.getMessage();
                     //                }
                     catch (NumberFormatException nfe) {
                     response = "Id is not a number";
                     status = 404;
                     }
                     break;
                     }
                 
                     */

                    }
                
                he.getResponseHeaders().add("Content-Type", "application/json");
                he.sendResponseHeaders(status, 0);
                try (OutputStream os = he.getResponseBody()) {
                    // os.write(response.getBytes());
                    Gson gson = new Gson();
                    os.write(gson.toJson(response).getBytes());    // Json array sendes af hele player array 
                }
            }

        };
    }

}

/*
 private HttpHandler JsonRedirect() {

 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 }
 */
