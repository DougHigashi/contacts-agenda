package model;

public class JavaBeans {
	private String idcon;
	private String name;
	private String fone;
	private String email;
	
	public JavaBeans() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JavaBeans(String idcon, String name, String fone, String email) {
		super();
		this.idcon = idcon;
		this.name = name;
		this.fone = fone;
		this.email = email;
	}
	
	public String getIdcon() {
		return idcon;
	}
	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return fone;
	}
	public void setPhone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
