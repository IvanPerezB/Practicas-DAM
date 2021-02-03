package MetodosBBDD;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class CargarJTable {
	
	public void LlenarTabla(JTable tabla, String sSelect){

		ResultSet rs = null;
		
		try {   
			rs = ConnMySQL.sSQL(sSelect);
			tabla.setModel(DbUtils.resultSetToTableModel(rs));
	
	    } catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
