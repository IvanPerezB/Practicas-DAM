package MetodosBBDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class CargarCombo {

	public static void LlenarCombo(JComboBox<Item> cbo, String sSelect){

		ResultSet rs = null;
			
		

		try {   
			rs = ConnMySQL.sSQL(sSelect);
			
			
	   
			cbo.addItem(new Item(0,"Seleccione una opción")); 
			while(rs.next()){
				int iId = Integer. parseInt (rs.getString(1));
				String sValor = rs.getString(2);
				Item datosCombo = new Item(iId,sValor);
				cbo.addItem(datosCombo);
				System.out.println(datosCombo.getId() + " " + datosCombo.getDescription());
			}
	    } catch (SQLException e) {
	    	JOptionPane.showMessageDialog(null, e);
	    }
	}
	    
}
	
	

	

