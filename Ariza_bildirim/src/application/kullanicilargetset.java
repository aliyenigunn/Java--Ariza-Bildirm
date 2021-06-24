package application;


public class kullanicilargetset {
	Integer id;
	String ad;
	String sifre;
	
	
	public kullanicilargetset()
	{}
	
	


	public kullanicilargetset(Integer id, String ad, String sifre) {
		super();
		this.id = id;
		this.ad = ad;
		this.sifre = sifre;
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAd() {
		return ad;
	}


	public void setAd(String ad) {
		this.ad = ad;
	}


	public String getSifre() {
		return sifre;
	}


	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	
	
	
	
	


}
