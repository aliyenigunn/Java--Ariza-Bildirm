package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JOptionPane;
import javax.swing.text.TabExpander;

import DBconnection.baglanti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controlleradmin {

	@FXML
    private TableView<uyegetset> tbbilet;
	
	@FXML
    private TableView<firmagetset> tbfirma;
	
	@FXML
	private TableView<kullanicilargetset> tbuser;
	  
    @FXML
    private TableColumn<uyegetset, String> cbiletid;

    @FXML
    private TableColumn<uyegetset, String> cdepartman;

    @FXML
    private TableColumn<uyegetset, String> cdurum;

    @FXML
    private TableColumn<uyegetset, String> caciklama;

    @FXML
    private TableColumn<uyegetset, String> ctarih;

    @FXML
    private TableColumn<firmagetset, String> cfirmaid;

    @FXML
    private TableColumn<firmagetset, String> cfirmakodu;

    @FXML
    private TableColumn<firmagetset, String> cfirmaadi;

    @FXML
    private TableColumn<firmagetset, String> cyetkili;

    @FXML
    private TableColumn<firmagetset, String> ctelefon;

    @FXML
    private TableColumn<firmagetset, String> cmail;

    @FXML
    private TableColumn<firmagetset, String> cadres;

    @FXML
    private TableColumn<kullanicilargetset, String> kulid;

    @FXML
    private TableColumn<kullanicilargetset, String> kuladi;

    @FXML
    private TableColumn<kullanicilargetset, String> kulsifre;

    @FXML
    private Button biletsil;

    @FXML
    private Button firmasil;

    @FXML
    private Button biletgor;

    @FXML
    private Button firmagor;

    @FXML
    private Button usergor;

    @FXML
    private Button userekle;

    @FXML
    private Button usersil;
    
    @FXML
    private TextField txtkullaniciadi;

    @FXML
    private TextField txtpass;


    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    String sql;

    public Controlleradmin() {
    	con=baglanti.Baglan();
    	
   }
    ObservableList<uyegetset> biletlist= FXCollections.observableArrayList();
    ObservableList<firmagetset> firmalist= FXCollections.observableArrayList();
    ObservableList<kullanicilargetset> userlist= FXCollections.observableArrayList();
    Integer bid,fid,kid;
    public void biletlistele(TableView bilet)
    {
    	biletlist.clear();
    	sql="select * from biletler";
    	try {
    		ps= con.prepareStatement(sql);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			
    			biletlist.add(new uyegetset(rs.getInt("biletID"), rs.getString("departman"), rs.getString("durum"), rs.getString("aciklama"), rs.getString("tarih")));
    			bid=rs.getInt("biletID");
    			
    		}
    	}
    	catch(Exception e) 
    	{
    		
    	}
    	cbiletid.setCellValueFactory(new PropertyValueFactory<>("id"));
		cdepartman.setCellValueFactory(new PropertyValueFactory<>("departman"));
		cdurum.setCellValueFactory(new PropertyValueFactory<>("durum"));
		caciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
		ctarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
		tbbilet.setItems(biletlist);
		
    }
    
    public void firmalistele(TableView firma)
    {
    	firmalist.clear();
    	sql="select * from firmalar";
    	try {
    		ps= con.prepareStatement(sql);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			
    			firmalist.add(new firmagetset(rs.getInt("firmaID"), rs.getInt("firmakodu"), rs.getString("firmaadi"), rs.getString("yetkili"), rs.getString("telefon"),rs.getString("mail"),rs.getString("adres")));
    			fid=rs.getInt("firmaID");
    			System.out.print(fid);
    		}
    	}
    	catch(Exception e) 
    	{
    		JOptionPane.showMessageDialog(null, e);
    	}
    	cfirmaid.setCellValueFactory(new PropertyValueFactory<>("id"));
		cfirmakodu.setCellValueFactory(new PropertyValueFactory<>("firmakodu"));
		cfirmaadi.setCellValueFactory(new PropertyValueFactory<>("firmadi"));
		cyetkili.setCellValueFactory(new PropertyValueFactory<>("yetkili"));
		ctelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		cmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		cadres.setCellValueFactory(new PropertyValueFactory<>("adres"));
		tbfirma.setItems(firmalist);
    }
    
    public void userlistele(TableView user)
    {
    	userlist.clear();
    	sql="select * from kullanicilar";
    	try {
    		ps= con.prepareStatement(sql);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			
    			userlist.add(new kullanicilargetset(rs.getInt("kullaniciID"), rs.getString("kullaniciad"), rs.getString("kullanicisifre")));
    			kid=rs.getInt("kullaniciID");
    			
    		}
    	}
    	catch(Exception e) 
    	{
    		JOptionPane.showMessageDialog(null, e);
    	}
    	kulid.setCellValueFactory(new PropertyValueFactory<>("id"));
		kuladi.setCellValueFactory(new PropertyValueFactory<>("ad"));
		kulsifre.setCellValueFactory(new PropertyValueFactory<>("sifre"));
		
		tbuser.setItems(userlist);
		
    }

    @FXML
    void biletgoruntule(ActionEvent event) {
    	biletlistele(tbbilet);
    	
    }

    @FXML
    void biletsilme(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Kayýt Silme Ýþlemi");
		alert.setHeaderText("Seçili satýrý silmek istediðinizden emin misiniz?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			sql="delete from biletler where biletID= '"+bid+"'";
			
			try {
				ps=con.prepareStatement(sql);
				ps.executeUpdate();
				biletlistele(tbbilet);
				
				
				
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Kayýt Silme Ýþlemi ");
				alert1.setHeaderText("Kayýt Baþarýyla Silinmiþtir");
				alert1.show();
				
			
			} catch (SQLException e) {
			
			}
		
		}
		else {
			Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("Kayýt Silme Ýþlemi ");
			alert2.setHeaderText("Kayýt Silme Ýþleminden Vazgeçildi");
			alert2.show();
		}

    }

    @FXML
    void firmagoruntule(ActionEvent event) {

    	firmalistele(tbfirma);
    }

    @FXML
    void firmasilme(ActionEvent event) {

    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Kayýt Silme Ýþlemi");
		alert.setHeaderText("Seçili satýrý silmek istediðinizden emin misiniz?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			sql="delete from firmalar where firmaID= '"+fid+"'";
			
			try {
				ps=con.prepareStatement(sql);
				ps.executeUpdate();
				firmalistele(tbfirma);		
				
				
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Kayýt Silme Ýþlemi ");
				alert1.setHeaderText("Kayýt Baþarýyla Silinmiþtir");
				alert1.show();
				
			
			} catch (SQLException e) {
			
			}
		
		}
		else {
			Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("Kayýt Silme Ýþlemi ");
			alert2.setHeaderText("Kayýt Silme Ýþleminden Vazgeçildi");
			alert2.show();
		}
    }

    @FXML
    void kulanicigoruntule(ActionEvent event) {
    	userlistele(tbuser);
    }

    @FXML
    void kullaniciekle(ActionEvent event) {
    	sql="insert into kullanicilar (kullaniciad,kullanicisifre) values (?,?)";
    	try {
			ps=con.prepareStatement(sql);
			ps.setString(1, txtkullaniciadi.getText().trim());
			ps.setString(2, txtpass.getText().trim());
			ps.executeUpdate();
			
			txtkullaniciadi.setText("");
			txtpass.setText("");
			
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Bilgi Ekraný");
    		alert.setHeaderText("Kayýt ekleme iþlemi baþarýlý");
    		alert.showAndWait();
		} catch (SQLException e) {
			
		}
    	
    }

    @FXML
    void kullanicisilme(ActionEvent event) {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Kayýt Silme Ýþlemi");
		alert.setHeaderText("Seçili satýrý silmek istediðinizden emin misiniz?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			sql="delete from kullanicilar where kullaniciID= '"+kid+"'";
			
			try {
				ps=con.prepareStatement(sql);
				ps.executeUpdate();
				userlistele(tbuser);			
				
				
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setTitle("Kayýt Silme Ýþlemi ");
				alert1.setHeaderText("Kayýt Baþarýyla Silinmiþtir");
				alert1.show();
				
			
			} catch (SQLException e) {
			
			}
		
		}
		else {
			Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("Kayýt Silme Ýþlemi ");
			alert2.setHeaderText("Kayýt Silme Ýþleminden Vazgeçildi");
			alert2.show();
		}

    }

}
