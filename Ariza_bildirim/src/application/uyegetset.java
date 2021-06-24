package application;

public class uyegetset {

	Integer id;
	String departman;
	String durum;
	String aciklama;
	String  tarih;
	

	public uyegetset(Integer id ,String departman, String durum, String aciklama, String tarih) {
		super();
		this.id=id;
		this.departman = departman;
		this.durum = durum;
		this.aciklama = aciklama;
		this.tarih = tarih;
	}
	
	public uyegetset()
	{
		
	}
	
	
	public uyegetset(int int1, String string, String string2, String string3, String string4, String string5,
			String string6) {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartman() {
		return departman;
	}
	public void setDepartman(String departman) {
		this.departman = departman;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	
	
}
