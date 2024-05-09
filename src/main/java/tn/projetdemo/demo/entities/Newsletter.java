package tn.projetdemo.demo.entities;


public class Newsletter {
	private String date_inscrit ;

	public Newsletter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Newsletter(String date_inscrit) {
		super();
		this.date_inscrit = date_inscrit;
	}

	public String getDate_inscrit() {
		return date_inscrit;
	}

	public void setDate_inscrit(String date_inscrit) {
		this.date_inscrit = date_inscrit;
	}

	@Override
	public String toString() {
		return "Newsletter [date_inscrit=" + date_inscrit + "]";
	}
	

}
