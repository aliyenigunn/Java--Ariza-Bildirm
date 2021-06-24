package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import DBconnection.baglanti;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Controlleruye implements Initializable {

    @FXML
    private Button btnbiletekle;

    @FXML
    private Button btnbiletsil;

    @FXML
    private Button btnbiletguncelle;
    
    @FXML
    private Button cikis;

    @FXML
    private Button btncikis;

    @FXML
    private TableView<uyegetset> tbview;
    
    @FXML
    private TableColumn<uyegetset, String> colbiletid;

    @FXML
    private TableColumn<uyegetset, String> coldepartman;

    @FXML
    private TableColumn<uyegetset, String> colaciliyet;

    @FXML
    private TableColumn<uyegetset, String> colaciklama;

    @FXML
    private TableColumn<uyegetset, String> coltarih;
    
    @FXML
    private TextField txtdepartman;

    @FXML
    private TextField txtdurum;

    @FXML
    private TextArea txtaciklama;

    @FXML
    private DatePicker date;

    
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    String sql,sql1,sql2;

    public Controlleruye() {
    	con=baglanti.Baglan();
    	
   }
    
	ObservableList<uyegetset> oblist= FXCollections.observableArrayList();
	Integer bid;
    @FXML
    void btnadd(ActionEvent event)  {
    	oblist.clear();
    	LocalDate trh = date.getValue();
    	sql="insert into biletler (departman,durum,aciklama,tarih) values (?,?,?,?)"; //üye ekranýndan bilet ekle sil güncelleme iþlerini yapmaktadýr
    	try {
			ps=con.prepareStatement(sql);
			ps.setString(1, txtdepartman.getText().trim());
			ps.setString(2, txtdurum.getText().trim());
			ps.setString(3, txtaciklama.getText().trim());
			ps.setString(4, trh.toString());
			ps.executeUpdate();
			doldur(tbview);
			txtaciklama.setText("");
			txtdepartman.setText("");
			txtdurum.setText("");
			date.setValue(null);
			Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Bilgi Ekraný");
    		alert.setHeaderText("Bilet ekleme iþlemi baþarýlý");
    		alert.showAndWait();
    		
		} catch (Exception e) {
			
		}
    }

    @FXML
    void biletguncelle(ActionEvent event) {
    	sql2="update biletler set departman=?,durum=?,aciklama=?,tarih=? where biletID='"+bid+"'";
    	try {
    		ps=con.prepareStatement(sql2);
    		ps.setString(1 ,txtdepartman.getText());
    		ps.setString(2, txtdurum.getText());
    		ps.setString(3, txtaciklama.getText());
    		ps.setDate(4, Date.valueOf(date.getValue()));
    		ps.executeUpdate();
    		doldur(tbview);
    		txtaciklama.setText("");
			txtdepartman.setText("");
			txtdurum.setText("");
			date.setValue(null);
    	}catch(Exception e) {JOptionPane.showMessageDialog(null, e);}

    }

  
    @FXML
    void biletsil(ActionEvent event) {
    	
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Kayýt Silme Ýþlemi");
		alert.setHeaderText("Seçili satýrý silmek istediðinizden emin misiniz?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			sql1="delete from biletler where biletID= '"+bid+"'";
			
			try {
				ps=con.prepareStatement(sql1);
				ps.executeUpdate();
				doldur(tbview);
				
				
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

    
    public void doldur(TableView tb)
    {
    	oblist.clear();
    	sql="select * from biletler";
    	try {
    		ps= con.prepareStatement(sql);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			
    			oblist.add(new uyegetset(rs.getInt("biletID"), rs.getString("departman"), rs.getString("durum"), rs.getString("aciklama"), rs.getString("tarih")));
    			bid=rs.getInt("biletID");
    			
    		}
    	}
    	catch(Exception e) 
    	{
    		
    	}
    	colbiletid.setCellValueFactory(new PropertyValueFactory<>("id"));
		coldepartman.setCellValueFactory(new PropertyValueFactory<>("departman"));
		colaciliyet.setCellValueFactory(new PropertyValueFactory<>("durum"));
		colaciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
		coltarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
		tbview.setItems(oblist);
    	
    }
    @FXML
    public void tbtiklama(MouseEvent event) 
    {
    	uyegetset bilet =new uyegetset();
    	bilet=(uyegetset) tbview.getItems().get(tbview.getSelectionModel().getSelectedIndex());
    	txtdepartman.setText(bilet.getDepartman());
    	txtdurum.setText(bilet.getDurum());
    	txtaciklama.setText(bilet.getAciklama());
    	date.setStyle(bilet.getTarih());
    }
    Stage stageAnket=new Stage();
    @FXML
    void btncikis(ActionEvent event) { 

    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Anket");
		alert.setHeaderText("Anketimize katýlmak ister misiniz?");
		ButtonType btnevet=new ButtonType("EVET");
		ButtonType btnhayir=new ButtonType("HAYIR" , ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(btnevet,btnhayir);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == btnevet) {
			
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("anket.fxml"));
				Scene scene = new Scene(root,1000,870);
	 			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	 			stageAnket.setScene(scene);
	 			stageAnket.show();
	 			
	 			Stage stage = (Stage) cikis.getScene().getWindow();
		        stage.close();
			} catch (IOException e) {
				
			}
		}
		else
		{
			Stage stage = (Stage) cikis.getScene().getWindow();
	        stage.close();
		}

    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
    	doldur(tbview);
    	
	}
   

}
