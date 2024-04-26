package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	/** Connection **/

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	private String user = "root";
	private String password = "1234";

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void testConection() {
		try {
			Connection con = connect();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println();
		}
	}

	public void insertContact(JavaBeans contact) {
		String query = "INSERT INTO CONTATOS (name, phone, email) values (?,?,?)";

		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1, contact.getName());
			pst.setString(2, contact.getPhone());
			pst.setString(3, contact.getEmail());

			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("FAIL ON METHOD 'insertContact'");
		}
	}
	
	public  ArrayList<JavaBeans> listContacts() {
		
		ArrayList<JavaBeans> contacts = new ArrayList<>();
		
		String query = "SELECT * FROM contatos ORDER BY name";
		
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String idcon = rs.getString(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				contacts.add(new JavaBeans(idcon, name, phone, email));
			}
			
			con.close();
			
			return contacts;
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("FAIL ON METHOD 'listContacts'");
			return null;
		}
	}
	
	
	public void getContact(JavaBeans contact) {
		
		String query = "SELECT * FROM contatos WHERE id = ?";
		
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			System.out.println(pst);
			
			pst.setString(1, contact.getId());
			
			System.out.println("Running query: " + pst);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				contact.setId(rs.getString(1));
				contact.setName(rs.getString(2));
				contact.setPhone(rs.getString(3));
				contact.setEmail(rs.getString(4));
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("FAIL ON METHOD 'getContact'");
		}
	}
	
	public void updateContact(JavaBeans contact) {
		String query = "UPDATE contatos SET name = ?, phone = ?, email = ? WHERE id = ?";
		
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getPhone());
			pst.setString(3, contact.getEmail());
			pst.setInt(4, Integer.parseInt(contact.getId()));
			
			System.out.println("Running query: " + pst);
			
			pst.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("FAIL ON METHOD 'updateContact'");
		}
	}
}