package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/**  Connection *. */

	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "1234";

	/**
	 * Connect.
	 *
	 * @return the connection
	 */
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

	/**
	 * Test conection.
	 */
	public void testConection() {
		try {
			Connection con = connect();
			con.close();
		} catch (Exception e) {
			System.out.println();
		}
	}

	/**
	 * Insert contact.
	 *
	 * @param contact the contact
	 */
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
	
	/**
	 * List contacts.
	 *
	 * @return the array list
	 */
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
	
	
	/**
	 * Gets the contact.
	 *
	 * @param contact the contact
	 * @return the contact
	 */
	public void getContact(JavaBeans contact) {
		
		String query = "SELECT * FROM contatos WHERE id = ?";
		
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, contact.getId());
			
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
	
	/**
	 * Update contact.
	 *
	 * @param contact the contact
	 */
	public void updateContact(JavaBeans contact) {
		String query = "UPDATE contatos SET name = ?, phone = ?, email = ? WHERE id = ?";
		
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, contact.getName());
			pst.setString(2, contact.getPhone());
			pst.setString(3, contact.getEmail());
			pst.setInt(4, Integer.parseInt(contact.getId()));
			
			pst.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("FAIL ON METHOD 'updateContact'");
		}
	}
	
	/**
	 * Delete contact.
	 *
	 * @param contact the contact
	 */
	public void deleteContact(JavaBeans contact) {
		String query = "DELETE FROM contatos WHERE id = ?";
		
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(query);

			pst.setInt(1, Integer.parseInt(contact.getId()));
			
			pst.executeUpdate();
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("FAIL ON METHOD 'deleteContact'");
		}
	}
}