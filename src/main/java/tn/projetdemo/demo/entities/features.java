package tn.projetdemo.demo.entities;


public class features {
	private String date ;
	private String type ;
	public features() {
		super();
		// TODO Auto-generated constructor stub
	}
	public features(String date, String type) {
		super();
		this.date = date;
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "features [date=" + date + ", type=" + type + "]";
	}
	

}
