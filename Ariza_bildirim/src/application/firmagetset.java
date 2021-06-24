package application;

public class firmagetset {
	Integer id;
	Integer firmakodu;
	String firmadi;
	String yetkili;
	String telefon;
	String mail;
	String adres;
	
	public firmagetset()
	{
		
	}
	
	public firmagetset(Integer id, Integer firmakodu, String firmadi, String yetkili, String telefon, String mail,
			String adres) {
		super();
		this.id = id;
		this.firmakodu = firmakodu;
		this.firmadi = firmadi;
		this.yetkili = yetkili;
		this.telefon = telefon;
		this.mail = mail;
		this.adres = adres;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFirmakodu() {
		return firmakodu;
	}
	public void setFirmakodu(Integer firmakodu) {
		this.firmakodu = firmakodu;
	}
	public String getFirmadi() {
		return firmadi;
	}
	public void setFirmadi(String firmadi) {
		this.firmadi = firmadi;
	}
	public String getYetkili() {
		return yetkili;
	}
	public void setYetkili(String yetkili) {
		this.yetkili = yetkili;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	

}
