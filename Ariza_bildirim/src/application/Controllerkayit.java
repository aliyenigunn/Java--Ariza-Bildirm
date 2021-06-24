package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import DBconnection.baglanti;

public class Controllerkayit {
	@FXML
	private TextField txtfirmakod;
	
	@FXML
    private TextField txtfirmaad;

    @FXML
    private TextField txtno;

    @FXML
    private TextField txtmail;

    @FXML
    private TextField txtadres;

    @FXML
    private Button btnkayitekle;

    @FXML
    private Button btntemizle;

    @FXML
    private TextField txtyetkili;
    
  
    
    Connection con=null;
    PreparedStatement ps=null;
    String sql1,sql2;

    public Controllerkayit() {
   con=baglanti.Baglan();
   }

    
   
    @FXML
    void kayitekle(ActionEvent event) {
    	
    	if(txtfirmaad.getText().equals("")||txtfirmaad.getText().equals("")||txtyetkili.getText().equals("")||txtno.getText().equals("")||txtmail.getText().equals("")||txtadres.getText().equals(""))
    	{
    		Alert alert1=new Alert(AlertType.WARNING);
    		alert1.setTitle("Uyarý");
    		alert1.setHeaderText("Lütfen alanlarý boþ býrakmayýnýz");
    		alert1.showAndWait();
    	}
    	else {
    	sql1="insert into firmalar (firmakodu,firmaadi,yetkili,telefon,mail,adres) values (?,?,?,?,?,?)";
    	//sql2="insert into biletler(firmaID) values (IDENT_CURRENT('firmalar')";
    	try {
    		ps=con.prepareStatement(sql1);
    		//ps=con.prepareStatement(sql2);
    		ps.setString(1, txtfirmakod.getText().trim());
    		ps.setString(2, txtfirmaad.getText().trim());
    		ps.setString(3, txtyetkili.getText().trim());
    		ps.setString(4, txtno.getText().trim());
    		ps.setString(5, txtmail.getText().trim());
    		ps.setString(6, txtadres.getText().trim());
    		ps.executeUpdate();
    		
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Bilgi Ekraný");
    		alert.setHeaderText("Kayýt ekleme iþlemi baþarýlý");
    		alert.showAndWait();
    	
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, e);
    	}

    }}

    @FXML
    void temizle(ActionEvent event) {
    	txtfirmakod.setText("");
    	txtfirmaad.setText("");
    	txtyetkili.setText("");
    	txtno.setText("");
    	txtmail.clear();
    	txtadres.clear();

    }
   
  }
    


