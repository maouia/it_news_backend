package tn.projetdemo.demo.dto;
import lombok.Data;

@Data
public class LoginDto {
	  private String username;
	  private String pass_ut;
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginDto(String username, String pass_ut) {
		super();
		this.username = username;
		this.pass_ut = pass_ut;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass_ut() {
		return pass_ut;
	}
	public void setPass_ut(String pass_ut) {
		this.pass_ut = pass_ut;
	}
	@Override
	public String toString() {
		return "LoginDto [username=" + username + ", pass_ut=" + pass_ut + "]";
	}
		
	    

}
