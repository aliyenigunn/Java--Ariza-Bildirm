package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DBconnection.baglanti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import DBconnection.baglanti;

public class Controlleranket  implements Initializable{

    @FXML
    private ComboBox<String> cbeh;

    @FXML
    private RadioButton rbevet;

    @FXML
    private RadioButton rbhayir;

    @FXML
    private ToggleGroup tavsiye;

    @FXML
    private TextArea txtoneri;

    @FXML
    private Slider sldeger;

    @FXML
    private TextField txtadi;

    @FXML
    private TextField txtmail;

    @FXML
    private Button btngonder;

    @FXML
    private Button btnsonuc;
    
    @FXML
    private Label labeldeger;

    Connection con=null;
    PreparedStatement ps=null;
    String sql;

    public Controlleranket() {
   con=baglanti.Baglan();
   }
    String rbtext;
    @FXML
    void gonder(ActionEvent event) {
    	sql="insert into anketler (kullanim,tavsiye,öneri,memnuniyet,musteriad,musterimail) values (?,?,?,?,?,?)";
    	try {
    		ps=con.prepareStatement(sql);
    		ps.setString(1, cbeh.getValue().trim());
    		ps.setString(2, rbtext );
    		ps.setString(3, txtoneri.getText().trim());
    		ps.setString(4, labeldeger.getText().trim());
    		ps.setString(5, txtadi.getText().trim());
    		ps.setString(6, txtmail.getText().trim());
    		ps.executeUpdate();
    		
    		cbeh.setValue("Seçiniz");
    		rbevet.setSelected(false);
    		rbhayir.setSelected(false);
    		txtoneri.setText("");
    		txtadi.setText("");
    		txtmail.setText("");
    		sldeger.setValue(0);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Bilgi Ekraný");
    		alert.setHeaderText("Anket baþarýyla eklenmiþtir. Teþekkür ederiz");
    		alert.showAndWait();
        
    	}
    	catch(Exception e) {JOptionPane.showMessageDialog(null, e);}
    }
    Stage grafStage=new Stage();
    @FXML
    void sonuclar(ActionEvent event) {

    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("grafik.fxml"));
			Scene scene = new Scene(root,1300,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			grafStage.setScene(scene);
			grafStage.show();
		} catch (IOException e) {
		
		}
		
    }
   
    public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
    	cbeh.getItems().addAll("EVET","HAYIR");
    	ToggleGroup grup=new ToggleGroup();
    	rbevet.setToggleGroup(grup);
    	rbevet.setOnAction(e ->{
    		rbtext=rbevet.getText();
    	});
    	rbhayir.setToggleGroup(grup);
    	rbhayir.setOnAction(e ->{
    		rbtext=rbhayir.getText();
    	});

    	sldeger.valueProperty().addListener((obs, OldValue, newValue) -> {
    		
    		Integer i = newValue.intValue();
			labeldeger.setText(Integer.toString(i));
		});
    	
	}
}
