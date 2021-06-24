package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import DBconnection.baglanti;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class Controllergrafik implements Initializable {

    @FXML
    private BarChart<Double, Double> denemechart;

    @FXML
    private CategoryAxis ogrenciler;

    @FXML
    private NumberAxis ihtimal;

    Connection con=null;
    ResultSet rs=null;
    PreparedStatement ps=null;
    String sql;

    public Controllergrafik() {
   con=baglanti.Baglan();
   }
    XYChart.Series series=new XYChart.Series(); 
	@Override
	public void initialize(URL location, ResourceBundle resources) { 
		/*sql="SELECT AVG(memnuniyet) from anketler";
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			Integer a;
			Integer b;
			a=rs.getInt(6);
			b=rs.getInt(5);
			
			while(rs.next())
			{
				series.getData().add(new XYChart.Data<>(a,b));
			}
			denemechart.getData().add((series);
		} catch (SQLException e) {
			
		}*/
		// XYChart.Series series=new XYChart.Series();
		 series.getData().add(new XYChart.Data("Deneme",20));
		 denemechart.getData().add(series);
		
	}

}
