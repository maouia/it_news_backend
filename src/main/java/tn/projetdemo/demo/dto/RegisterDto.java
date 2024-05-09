package tn.projetdemo.demo.dto;
import lombok.Data;


@Data
public class RegisterDto {
	 private String prenom_ut;

	    private String nom_ut;

	    private String username;

	    private String email;

	    private String pass_ut;
	    private String rolename;
		public RegisterDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public RegisterDto(String prenom_ut, String nom_ut, String username, String email, String pass_ut,
				String rolename) {
			super();
			this.prenom_ut = prenom_ut;
			this.nom_ut = nom_ut;
			this.username = username;
			this.email = email;
			this.pass_ut = pass_ut;
			this.rolename = rolename;
		}
		public String getPrenom_ut() {
			return prenom_ut;
		}
		public void setPrenom_ut(String prenom_ut) {
			this.prenom_ut = prenom_ut;
		}
		public String getNom_ut() {
			return nom_ut;
		}
		public void setNom_ut(String nom_ut) {
			this.nom_ut = nom_ut;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPass_ut() {
			return pass_ut;
		}
		public void setPass_ut(String pass_ut) {
			this.pass_ut = pass_ut;
		}
		public String getRolename() {
			return rolename;
		}
		public void setRolename(String rolename) {
			this.rolename = rolename;
		}
		@Override
		public String toString() {
			return "RegisterDto [prenom_ut=" + prenom_ut + ", nom_ut=" + nom_ut + ", username=" + username + ", email="
					+ email + ", pass_ut=" + pass_ut + ", rolename=" + rolename + "]";
		}
		
		
		
		

	
	    
	    

}
