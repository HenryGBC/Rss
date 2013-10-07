/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author henrygbc
 */
public class SampleController implements Initializable {
    
    @FXML private ChoiceBox chbUrl;
    @FXML private Button btnFeed;
    @FXML private Label lblNoticias;
    @FXML private ToggleButton tbtnProxy;
    @FXML private Label lblUsuario;  
    @FXML private Label lblClave;  
    @FXML private TextField txfUsuario;
    @FXML private TextField pswfClave;
    @FXML private Label lblProxy;  
    @FXML private Label lblPuerto;  
    @FXML private TextField txfProxy;
    @FXML private TextField txfPuerto;
    @FXML private Button btnConectar;
    @FXML private Pane panelProxy;
    @FXML private Text txProxyAct;    
    @FXML private ScrollPane scrllNoticias;
    @FXML private TextField txfNoticias;
    @FXML private TextArea txaNoticias;
    @FXML private ScrollPane scrollPanel;
    @FXML private Label lblInfo; 
    @FXML private Button btnPrueba;
      

    Rss rss;
    Conexion conexion;
    
    String puerto=null;
    String proxy=null;
    String usuario=null;
    String clave=null;
      String nota="";
     ArrayList<String> vectorNoticias=null;
    boolean conProxy=false;
    

    @FXML  private void HacerFeed(ActionEvent evento){
       Conexion conexion = new Conexion();
       String Pagina = null;
       System.out.println(chbUrl.getValue());
      
       if(chbUrl.getValue()=="CNN")
       {
           Pagina = "http://rss.cnn.com/rss/edition.rss";
          
       }
         if(chbUrl.getValue()=="Deportivotachira.com")
       {
           Pagina = "http://deportivotachira.com/feed/";
          
       }
    
       if(conProxy==true){
             
       vectorNoticias= conexion.conProxy(Pagina, proxy, puerto, usuario, clave);
           for (int i = 0; i < vectorNoticias.size(); i++) {
               //System.out.println(vectorNoticias.get(i));
               nota +=vectorNoticias.get(i)+"\n\n";
           }
            
           imprimir();
       }else{
           
          
           vectorNoticias= conexion.sinProxy(Pagina);
           for (int i = 0; i < vectorNoticias.size(); i++) {
               //System.out.println(vectorNoticias.get(i));
               nota +=vectorNoticias.get(i)+"\n\n";
           }
            
           imprimir();
          
          
       }
        
    }
       public void imprimir(){
         lblInfo.setText(nota);
         lblInfo.setLayoutY(10*nota.length());
         scrollPanel.setContent(lblInfo);
       }
       @FXML  private void ActivaProxy(ActionEvent evento) throws Exception{
           panelProxy.setVisible(true);
           Stage stage = null;
          
         
           
       }
       @FXML  private void conectarProxy(ActionEvent evento){
           
            proxy = txfProxy.getText();
            puerto = txfPuerto.getText();
            usuario = txfUsuario.getText();
            clave = pswfClave.getText();
            
            
            System.out.println(proxy+" "+puerto+" "+usuario+" "+" "+clave);
            panelProxy.setVisible(false);
            conProxy=true;
            txProxyAct.setVisible(true);
            
       }
      @FXML private void Probar(ActionEvent evento){
          System.out.println("PRobóooooo");
      }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      chbUrl.getItems().addAll("CNN", "Yahoo", "UNET","Deportivotachira.com");
    
    }    
}
