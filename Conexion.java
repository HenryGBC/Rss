/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author henrygbc
 */
public class Conexion {
 SampleController controlador = new SampleController();
  
    
    public ArrayList<String> conProxy(String pagina, String prox, String puerto, final String usuario, final String clave){
         try{ 
              // Autentificacion de usuario (si es HTTPS)
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("henry.bravo", "Hbravo89".toCharArray());
            }
        });
 
        // Si necesitamos conectar mediante proxy
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("150.187.65.3",3128));
        URL url = new URL(pagina);
        URLConnection con = url.openConnection(proxy);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        
        int i=0;
		String line;
                    
                    ArrayList<String> sourceCode = new ArrayList<String>();
		while((line = in.readLine())!=null){
			if(line.contains("<title>")){
                            
				int firstPos = line.indexOf("<title>");
				String temp = line.substring(firstPos);
				temp = temp.replace("<title>", "");
				int lastPos = temp.indexOf("</title>");
				temp = temp.substring(0,lastPos);
                                  sourceCode.add(temp);
                             
                               //System.out.println(sourceCode.get(i)+"\n"); 
                               i++;
			}
		}
		in.close();
		      return sourceCode;
    } catch(MalformedURLException murle) {
        // Control excepciones
    } catch (IOException ioe){
        // Control excepciones
    }
        return null;
    }
    
    
    
    public ArrayList<String> sinProxy(String pagina){
       try{ 
        URL url = new URL(pagina);
        URLConnection con = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
		String line;
                    ArrayList<String> sourceCode = new ArrayList<String>();
		while((line = in.readLine())!=null){
			if(line.contains("<title>")){
				int firstPos = line.indexOf("<title>");
				String temp = line.substring(firstPos);
				temp = temp.replace("<title>", "");
				int lastPos = temp.indexOf("</title>");
				temp = temp.substring(0,lastPos);
				sourceCode.add(temp);
                                
			}
		}
		in.close();
                return sourceCode;
               //System.out.println(sourceCode);   
		      
    } catch(MalformedURLException murle) {
        // Control excepciones
    } catch (IOException ioe){
        // Control excepciones
    }
        return null;
    }
}
