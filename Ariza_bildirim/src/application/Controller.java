package application;

import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DBconnection.baglanti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private TextField txtkulad;

    @FXML
    private Button btngiris;

    @FXML
    private Button btnkayit;

    @FXML
    private PasswordField txtsifre;
    

    @FXML
    private Label lbldurum;
    
   
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    String sql;
   Integer kid;
    //veritabaný baðlantýsýný DBconnection paketinden çekiyor
    
    public Controller() {
   con=baglanti.Baglan();
   }

    Stage uyeStage =new Stage ();
    Stage adminStage =new Stage ();
    @FXML
    void giris(ActionEvent event) throws SQLException {
    	String ad=txtkulad.getText();
    	String sifre=txtsifre.getText();
    	
    	if(ad.equals("admin") && sifre.equals("mysql")) 
    	{
    		
    		try {
    			
    			Parent root=FXMLLoader.load(getClass().getResource("adminpaneli.fxml"));
    			Scene scene = new Scene(root,1300,1000);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
    			adminStage.setScene(scene);
    			adminStage.show();
    			lbldurum.setText("Giriþ Baþarýlý");
    			
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else {
    	
    	if(ad.equals("") || sifre.equals(""))
    	{
    		lbldurum.setText("Lütfen alanlarý boþ býrakmayýnýz");
    	}
    	else  {
    	sql ="select * from kullanicilar where kullaniciad=? and kullanicisifre=?"; //
    	
    	try {
    	 ps=con.prepareStatement(sql);
    	 ps.setString(1, txtkulad.getText().trim());
    	 ps.setString(2, txtsifre.getText().trim());
    	 rs=ps.executeQuery();
    	 
    	 if(rs.next())
    	 {
    		 kid=rs.getInt("kullaniciID");
    		 
    		 lbldurum.setText("Giriþ Baþarýlý");
    		 Parent root=FXMLLoader.load(getClass().getResource("uyeekrani.fxml"));
 			Scene scene = new Scene(root,1300,900);
 			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 			uyeStage.setScene(scene);
 			uyeStage.show();
    	 }
    	 
    	 else
    	 {
    		 lbldurum.setText("Kullanýcý adý veya þifre yanlýþ");
    	 }
    	 
    	 
    }
    	 catch (Exception e){ JOptionPane.showMessageDialog(null, e);}
    	
    	
    	}}
    }
   
  

    Stage kayitStage = new Stage ();
    @FXML
     void kaydol(ActionEvent event) { 
    	
				
    	
    	try {
					Parent root =FXMLLoader.load(getClass().getResource("kaydol.fxml"));
					Scene scene = new Scene(root,600,685);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					kayitStage.setTitle("Kayýt Ekle");
					Image img = new Image("/images/add.png");
					kayitStage.getIcons().add(img);
					kayitStage.setResizable(false);
					kayitStage.setScene(scene);
					kayitStage.show();
				} catch(Exception e) {
					e.printStackTrace();
				
			}
		
    	
    }
    	}
    	
    	



