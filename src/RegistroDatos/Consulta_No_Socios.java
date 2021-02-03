package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class Consulta_No_Socios extends JDialog {
	/*

    Esta es una ventana para consultas. El tipo de consulta es el que indica el propio titulo de la clase. Por lo general estas ventanas mostrarán una tabla para las busquedas y unos
    campos rellenables, los cuales sirven para hacer filtros en la busqueda.

    En estas clases hay dos metodos diferenciados, uno para llenar la tabla segun se accede a la ventana con todos los datos, pues es la consulta por defecto mostrará todos. El segundo metodo
    sirve para aplicar los filtros en la busqueda y despues actualizará automaticamente la tabla.

    Hay más metodos auxiliares, como el de limpiar los filtros para que los usuarios no pierdan el tiempo en ello. 

    Realmente lo complicado de estas clases son las sentencias, pero las que hay ahora mismo funcionan todas por lo que a menos que hallais realizado cambios en la bbdd



*/

	private final JPanel contentPanel = new JPanel();
	private JTable tNoSocios;
	private static ResultSet rsUsuarios;
	CargarJTable cargarTabla = new CargarJTable();
	private int regSelect;
	public static int idPedidoSelect = -1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JDateChooser f_nacim_F;
	private String sWhere ="";
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consulta_No_Socios dialog = new Consulta_No_Socios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consulta_No_Socios() {
		setBounds(100, 100, 939, 504);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 95, 897, 349);
		contentPanel.add(scrollPane);
		
		tNoSocios = new JTable();
		
		
		LlenarTablaNoSocios("");
		
		
		scrollPane.setViewportView(tNoSocios);
		
		DefaultTableModel ModeltNoSocios = (DefaultTableModel) tNoSocios.getModel();
		tNoSocios.setModel(ModeltNoSocios);
		ModeltNoSocios.fireTableDataChanged();
		
		JLabel lblNewLabel_2 = new JLabel("Lista de usuarios que no son socios:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(12, 26, 381, 20);
		contentPanel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(12, 64, 114, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(126, 64, 114, 20);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(240, 64, 108, 20);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(347, 64, 114, 20);
		contentPanel.add(textField_3);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(573, 64, 114, 20);
		contentPanel.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(688, 64, 108, 20);
		contentPanel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(795, 64, 114, 20);
		contentPanel.add(textField_7);
		
		f_nacim_F = new JDateChooser();
		f_nacim_F.setBounds(461, 64, 114, 20);
		contentPanel.add(f_nacim_F);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Filtros();
			}
		});
		btnNewButton.setBounds(820, 11, 89, 23);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton_1.setBounds(707, 11, 89, 23);
		contentPanel.add(btnNewButton_1);
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
			}
		});
		btnVolver.setBounds(590, 9, 97, 25);
		contentPanel.add(btnVolver);
		
	}
	
	public void LlenarTablaNoSocios(String sWhere) {
		
		String sSelect = "SELECT `usuario`.`id`, `usuario`.`nomb_usu`, `usuario`.`apellido1_usu`, `usuario`.`apellido2_usu`, "+
				"`usuario`.`dni_usu`, `usuario`.`f_nac_usu`, `usuario`.`tlf_usu`, `usuario`.`email_usu`, `usuario`.`genero`,`socio`.`id_socio2`"+
				" FROM `usuario` "+ 
				" LEFT JOIN `socio` ON `socio`.`id` = `usuario`.`id` WHERE `socio`.`id_socio2` IS NULL  ";
		sSelect = sSelect + sWhere;
		rsUsuarios=ConnMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tNoSocios, sSelect);
			MaquearTabla();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	
	private void MaquearTabla() {
		
		

		Object[] titulos = new Object[10];
		titulos[0] = "id";
		titulos[1] = "Nombre";
		titulos[2] = "Primer Apellido";
		titulos[3] = "Segundo Apellido";
		titulos[4] = "DNI";
		titulos[5] = "Fecha de Nacimiento";
		titulos[6] = "Telefono";
		titulos[7] = "Email";
		titulos[8] = "Genero";
		titulos[9] = "idSocio2";

		tNoSocios.getColumnModel().getColumn(0).setMaxWidth(0);
		tNoSocios.getColumnModel().getColumn(0).setMinWidth(0);
		tNoSocios.getColumnModel().getColumn(0).setPreferredWidth(0);
		tNoSocios.getColumnModel().getColumn(0).setResizable(false);
		tNoSocios.getColumnModel().getColumn(9).setMaxWidth(0);
		tNoSocios.getColumnModel().getColumn(9).setMinWidth(0);
		tNoSocios.getColumnModel().getColumn(9).setPreferredWidth(0);
		tNoSocios.getColumnModel().getColumn(9).setResizable(false);

		tNoSocios.getColumnModel().getColumn(1).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(2).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(3).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(4).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(5).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(6).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(7).setMaxWidth(1000);
		tNoSocios.getColumnModel().getColumn(8).setMaxWidth(1000);

		tNoSocios.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tNoSocios.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tNoSocios.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tNoSocios.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tNoSocios.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tNoSocios.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tNoSocios.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		tNoSocios.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);

		tNoSocios.getColumnModel().getColumn(9).setMaxWidth(0);
		tNoSocios.getColumnModel().getColumn(9).setMinWidth(0);
		tNoSocios.getColumnModel().getColumn(9).setPreferredWidth(0);
		tNoSocios.getColumnModel().getColumn(9).setResizable(false);

		
	}
	
	private void Filtros() {
		
		sWhere = "";
		
		
	    
		if ( textField.getText() != null && !textField.getText().contentEquals("")  ) {
				sWhere =  " AND `usuario`.`nomb_usu` LIKE '" + textField.getText() + "%\'" ;
		}
		
		if ( textField_1.getText() != null && !textField_1.getText().contentEquals("")  ) {
			
			sWhere = sWhere + " AND `usuario`.`apellido1_usu` LIKE '" + textField_1.getText() + "%\'";
		}
		
		if ( textField_2.getText() != null && !textField_2.getText().contentEquals("")  ) {
			
			sWhere = sWhere + " AND `usuario`.`apellido2_usu` LIKE '" + textField_2.getText() + "%\'";
		}

		if ( textField_3.getText() != null && !textField_3.getText().contentEquals("")  ) {
			
			
			sWhere = sWhere + " AND dni_usu LIKE '" + textField_3.getText() + "%\'";
		}
		
		
		
		if ( f_nacim_F.getDate() != null ) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String sFechaNac = String.valueOf(sdf.format(f_nacim_F.getDate()));
			
			sWhere = sWhere + " AND f_nac_usu = '" + sFechaNac + "'" ;
			}
		if ( textField_5.getText() != null && !textField_5.getText().contentEquals("")  ) {
			
			sWhere = sWhere + " AND tlf_usu LIKE '" + textField_5.getText() + "%\'";
		}
		
			
		
		if ( textField_6.getText() != null && !textField_6.getText().contentEquals("")  ) {
			
			sWhere = sWhere + " AND email_usu LIKE '" + textField_6.getText() + "%\'";
		}
		if ( textField_7.getText() != null && !textField_7.getText().contentEquals("")  ) {
			
			sWhere = sWhere + " AND genero LIKE '" + textField_7.getText() + "%\'";
		}

		
		
		
				
		System.out.println(sWhere);
		
		LlenarTablaNoSocios(sWhere);
		
	}
	
	private void LimpiarFiltros() {
        textField.setText("");
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        f_nacim_F.setDate(null);
        textField_5.setText("");
        textField_6.setText("");
        textField_7.setText("");

        LlenarTablaNoSocios("");
    }
}
