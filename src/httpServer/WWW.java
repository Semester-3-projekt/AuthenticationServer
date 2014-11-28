package httpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import control.CredentialManager;
import java.io.IOException;
import java.net.InetSocketAddress;
import model.CredentialJpaController;

public class WWW {

    private final HttpServer server;

    public WWW(int port) throws IOException {
        server = HttpServer.create();
        server.bind(new InetSocketAddress(port), 0);
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
                 String idStr = path.substring(lastIndex + 1);
                 String user = idStr.split("+")[0];
                 String pass = idStr.split("+")[1];
                 //   int id = Integer.parseInt(idStr);
                 //CredentialJpaController cont  = new CredentialJpaController();    //  skal der laves en ny instans af denne først ?? 
                 // response =  CredentialJpaController.findCredential(user,pass); 
                 
                 System.out.println(response);  // hvis nummer mindre end 0, så printes tom "response" = mellemrum
                 System.out.println("tester3");
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
                 he.getResponseHeaders().add("Content-Type", "application/json");
                 he.sendResponseHeaders(status, 0);
                 try (OutputStream os = he.getResponseBody()) {
                 os.write(response.getBytes());
                 }
                 }
                 }
                     */
                 
            }
        };
    };
}
    
}
    
                
/*
            private HttpHandler JsonRedirect() {

                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
*/
