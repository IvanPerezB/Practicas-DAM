package MetodosBBDD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import RegistroDatos.AccesoEliminar;
import RegistroDatos.AccesoPagos;
import RegistroDatos.Eliminar;
import RegistroDatos.Login;
import RegistroDatos.Pagos;
import RegistroDatos.Principal;

public class ConnMySQL {

	public static String direccionbbdd="localhost:3306";
    public static String nombrebbdd="aspergerbbdd";
    public static String usuariobbdd="root";
    public static String passwordbbdd="";

    public static String url = "jdbc:mysql://"+direccionbbdd+"/"+nombrebbdd+"?verifyServerCertificate=false&useSSL=true";
    public static String usuario = usuariobbdd;
    public static String password = passwordbbdd;
    
	static Connection conn = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet rs = null;
		
	
		
	public static void Desconectar(Connection conn) {
		try {
			conn.close(); 
				
		} catch (SQLException e) {
			System.out.println("Error al conectar con MySQL" + e.getMessage());
		}
	}
	
	public static boolean  EjecutarSQL(String sentencia) {
		Conectar();
		try {
			preparedStatement = conn.prepareStatement(sentencia);
			System.out.println(sentencia);	
			int i =preparedStatement.executeUpdate();
				
			if ( i > 0) {
				JOptionPane.showMessageDialog(null, "Los cambios en la BBDD se han realizado con éxito");
				conn.close();
				return true;
					
			} else {
				JOptionPane.showMessageDialog(null, "¡Error. El registro NO se añadió/modificó!");
				return false;
					
			}
							
		} catch ( SQLException e ) {
			JOptionPane.showMessageDialog(null, "¡Error de conexión!: " + e.getMessage());
				
		}
		return false;
	}
		
	public static ResultSet  sSQL(String sentencia) {
		Conectar();
		try {
			preparedStatement = conn.prepareStatement(sentencia);
				
			rs =preparedStatement.executeQuery();
				
							
		} catch ( SQLException e ) {
			JOptionPane.showMessageDialog(null, "¡Error de conexión!: " + e.getMessage());	
		}
			
		return rs;
	}
	
	public static boolean  EjecutarSQL_TRANSACT(Connection conn2, String sentencia) {
		
		try {
			preparedStatement = conn2.prepareStatement(sentencia);
			System.out.println(sentencia);	
			int i =preparedStatement.executeUpdate();
				
			if ( i > 0) {	
				return true;				
			} else {			
				return false;			
			}							
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return false;
	}
		
	public static ResultSet  sSQL_TRANSACT(Connection conn2, String sentencia) {		
		try {
			preparedStatement = conn2.prepareStatement(sentencia);
			rs =preparedStatement.executeQuery();									
		} catch ( SQLException e ) {
			e.printStackTrace();
		}	
		return rs;
	}
	
	public static ResultSet ConfirmarLogin(String sentencia, String txtUser, String txtPassword) {
		Conectar();
		try {
			preparedStatement = conn.prepareStatement(sentencia);
			preparedStatement.setString(1, txtUser);
			preparedStatement.setString(2, txtPassword);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "El usuario y la contrasena son correctos");

				new Login().setVisible(false);
				new Principal().setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "El usuario y la contrasena son incorrectos");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion: " + e.getMessage());
		}

		return rs;
	}
	
	public static ResultSet ConfirmarPagos(String sentencia, String txtUser, String txtPassword) {
		Conectar();
		try {
			preparedStatement = conn.prepareStatement(sentencia);
			preparedStatement.setString(1, txtUser);
			preparedStatement.setString(2, txtPassword);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "Contraseña correcta");

				new AccesoPagos().setVisible(false);
				new Pagos().setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion: " + e.getMessage());
		}

		return rs;
	}
	
	public static ResultSet ConfirmarEliminar(String sentencia, String txtUser, String txtPassword) {
		Conectar();
		try {
			preparedStatement = conn.prepareStatement(sentencia);
			preparedStatement.setString(1, txtUser);
			preparedStatement.setString(2, txtPassword);
			rs = preparedStatement.executeQuery();

			if (rs.next()) {

				JOptionPane.showMessageDialog(null, "Contraseña correcta");

				new AccesoEliminar().setVisible(false);
				new Eliminar().setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion: " + e.getMessage());
		}

		return rs;
	}
	
	public static String conectacion() {
		String link="";
		int contador=0;
		String[] direccionBBDDtxt;
		direccionBBDDtxt = new String[4];
		try  
		{  
		//el archivo creado
		FileInputStream fis=new FileInputStream("conexionBBDDaspergermadrid.txt");       
		Scanner sc=new Scanner(fis);    //lo escaneamos
		//da true si hay mas lineas
		while(sc.hasNextLine())  
		{  
		String linea = sc.nextLine();
			
		System.out.println(contador+"-"+linea); 
		direccionBBDDtxt[contador]=linea;
		System.out.println(direccionBBDDtxt[contador]);
		contador = contador+1;
		}  
		sc.close();     //cerramos scanner 
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  
		
		
		if(direccionBBDDtxt[0].equals("") && direccionBBDDtxt[1].equals("")){
			link=url;
		}else {
			link="jdbc:mysql://" + direccionBBDDtxt[0].toString() + "/" + direccionBBDDtxt[1].toString() + "?verifyServerCertificate=false&useSSL=true";
		}
		
		return link;
	}
	public static String getUser() {
		String getusu="";
		
		int contador=0;
		String[] direccionBBDDtxt;
		direccionBBDDtxt = new String[4];
		try  
		{  
		//el archivo creado
		FileInputStream fis=new FileInputStream("conexionBBDDaspergermadrid.txt");       
		Scanner sc=new Scanner(fis);    //lo escaneamos
		//da true si hay mas lineas
		while(sc.hasNextLine())  
		{  
		String linea = sc.nextLine();
			
		System.out.println(contador+"-"+linea); 
		direccionBBDDtxt[contador]=linea;
		System.out.println(direccionBBDDtxt[contador]);
		contador = contador+1;
		}  
		sc.close();     //cerramos scanner 
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  
		
		
		if(direccionBBDDtxt[0].equals("") && direccionBBDDtxt[1].equals("")){
			getusu=usuariobbdd;
		}else {
			getusu = direccionBBDDtxt[2].toString();
		}
		
		
		
		return getusu;
	}
	public static String getPass() {
		String getpass="";
		
		int contador=0;
		String[] direccionBBDDtxt;
		direccionBBDDtxt = new String[4];
		try  
		{  
		//el archivo creado
		FileInputStream fis=new FileInputStream("conexionBBDDaspergermadrid.txt");       
		Scanner sc=new Scanner(fis);    //lo escaneamos
		//da true si hay mas lineas
		while(sc.hasNextLine())  
		{  
		String linea = sc.nextLine();
			
		System.out.println(contador+"-"+linea); 
		direccionBBDDtxt[contador]=linea;
		System.out.println(direccionBBDDtxt[contador]);
		contador = contador+1;
		}  
		sc.close();     //cerramos scanner 
		}  
		catch(IOException e)  
		{  
		e.printStackTrace();  
		}  
		
		
		if(direccionBBDDtxt[0].equals("") && direccionBBDDtxt[1].equals("")){
			getpass=passwordbbdd;
		}else {
			getpass = direccionBBDDtxt[3].toString();
		}
		
		
		
		return getpass;
	}
	
	
	public static Connection Conectar() {	
		
		try {
			conn = DriverManager.getConnection ( conectacion(), getUser(), getPass() );
				
		} catch (SQLException e) {
			System.out.println("Error al conectar con MySQL" + e.getMessage());
		}
		return conn;
	}
	
}
