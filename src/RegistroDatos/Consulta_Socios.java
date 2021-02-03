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
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;

public class Consulta_Socios extends JDialog {
	/*

    Esta es una ventana para consultas. El tipo de consulta es el que indica el propio titulo de la clase. Por lo general estas ventanas mostrarán una tabla para las busquedas y unos
    campos rellenables, los cuales sirven para hacer filtros en la busqueda.

    En estas clases hay dos metodos diferenciados, uno para llenar la tabla segun se accede a la ventana con todos los datos, pues es la consulta por defecto mostrará todos. El segundo metodo
    sirve para aplicar los filtros en la busqueda y despues actualizará automaticamente la tabla.

    Hay más metodos auxiliares, como el de limpiar los filtros para que los usuarios no pierdan el tiempo en ello. 

    Realmente lo complicado de estas clases son las sentencias, pero las que hay ahora mismo funcionan todas por lo que a menos que hallais realizado cambios en la bbdd



*/

	private final JPanel contentPanel = new JPanel();
	private JTable tSocios;
	private static ResultSet rsUsuarios;
	CargarJTable cargarTabla = new CargarJTable();
	private int regSelect;
	public static int idPedidoSelect = -1;
	private JTextField txtNombreF;
	private JTextField txtApellido1F;
	private JTextField txtApellido2F;
	private JTextField txtDNIF;
	private JTextField txtTlfF;
	private JTextField txtEmailF;
	private JTextField txtGeneroF;
	private String sWhere;
	private JDateChooser f_naci_F ;
	private JButton btnBuscar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consulta_Socios dialog = new Consulta_Socios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consulta_Socios() {
		setBounds(100, 100, 959, 539);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 95, 921, 381);
		contentPanel.add(scrollPane);
		
		tSocios = new JTable();
		
		
		
		LlenarTablaSocios("");
		
		scrollPane.setViewportView(tSocios);
		
		DefaultTableModel ModeltSocios= (DefaultTableModel) tSocios.getModel();
		
		
		
		
		
		
		
		tSocios.setModel(ModeltSocios);
		
		txtNombreF = new JTextField();
		txtNombreF.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNombreF.setBounds(12, 60, 116, 22);
		contentPanel.add(txtNombreF);
		txtNombreF.setColumns(10);
		
		txtApellido1F = new JTextField();
		txtApellido1F.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtApellido1F.setColumns(10);
		txtApellido1F.setBounds(127, 60, 116, 22);
		contentPanel.add(txtApellido1F);
		
		txtApellido2F = new JTextField();
		txtApellido2F.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtApellido2F.setColumns(10);
		txtApellido2F.setBounds(244, 60, 116, 22);
		contentPanel.add(txtApellido2F);
		
		txtDNIF = new JTextField();
		txtDNIF.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDNIF.setColumns(10);
		txtDNIF.setBounds(359, 60, 116, 22);
		contentPanel.add(txtDNIF);
		
		txtTlfF = new JTextField();
		txtTlfF.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTlfF.setColumns(10);
		txtTlfF.setBounds(586, 60, 116, 22);
		contentPanel.add(txtTlfF);
		
		txtEmailF = new JTextField();
		txtEmailF.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtEmailF.setColumns(10);
		txtEmailF.setBounds(702, 60, 116, 22);
		contentPanel.add(txtEmailF);
		
		txtGeneroF = new JTextField();
		txtGeneroF.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtGeneroF.setColumns(10);
		txtGeneroF.setBounds(817, 60, 116, 22);
		contentPanel.add(txtGeneroF);
		
		f_naci_F = new JDateChooser();
		f_naci_F.getCalendarButton().setFont(new Font("Tahoma", Font.BOLD, 13));
		f_naci_F.setBounds(475, 60, 110, 22);
		contentPanel.add(f_naci_F);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Filtros();
			}
		});
		btnBuscar.setBounds(823, 13, 110, 36);
		contentPanel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LimpiarFiltros();
			}
		});
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiar.setBounds(684, 13, 110, 36);
		contentPanel.add(btnLimpiar);
		ModeltSocios.fireTableDataChanged();
	}
	
	private void Maquear() {
		Object[] titulos= new Object[10];
		titulos[0]="id";
		titulos[1]="Nombre";
		titulos[2]="Primer Apellido";
		titulos[3]="Segundo Apellido";
		titulos[4]="DNI";
		titulos[5]="Fecha de Nacimiento";
		titulos[6]="Telefono";
		titulos[7]="Email";
		titulos[8]="Genero";
		titulos[9] = "idSocio2";
		
		
		tSocios.getColumnModel().getColumn(0).setMaxWidth(0);
		tSocios.getColumnModel().getColumn(0).setMinWidth(0);
		tSocios.getColumnModel().getColumn(0).setPreferredWidth(0);
		tSocios.getColumnModel().getColumn(0).setResizable(false);
		tSocios.getColumnModel().getColumn(9).setMaxWidth(0);
		tSocios.getColumnModel().getColumn(9).setMinWidth(0);
		tSocios.getColumnModel().getColumn(9).setPreferredWidth(0);
		tSocios.getColumnModel().getColumn(9).setResizable(false);
		tSocios.getColumnModel().getColumn(1).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(2).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(3).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(4).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(5).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(6).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(7).setMaxWidth(1000);
		tSocios.getColumnModel().getColumn(8).setMaxWidth(1000);
		
		tSocios.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tSocios.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tSocios.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tSocios.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tSocios.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tSocios.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tSocios.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		tSocios.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);
	}
	
	public void LlenarTablaSocios(String sWhere) {
		
		String sSelect = "SELECT `usuario`.`id`, `usuario`.`nomb_usu`, `usuario`.`apellido1_usu`, `usuario`.`apellido2_usu`, "+
				"`usuario`.`dni_usu`, `usuario`.`f_nac_usu`, `usuario`.`tlf_usu`, `usuario`.`email_usu`, `usuario`.`genero`,`socio`.`id_socio2`"+
				" FROM `usuario` "+ 
				"LEFT JOIN `socio` ON `socio`.`id` = `usuario`.`id` WHERE `socio`.`id_socio2` IS NOT NULL";
		sSelect = sSelect + sWhere;
		rsUsuarios=ConnMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tSocios, sSelect);
			Maquear();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	
	private void Filtros() {
		sWhere = "";
			
		
	    
		if ( txtNombreF.getText() != null && !txtNombreF.getText().contentEquals("")  ) {
		
			sWhere = " AND  `usuario`.`nomb_usu` LIKE '" + txtNombreF.getText() + "%'" ;
	}
			
			if ( txtApellido1F.getText() != null && !txtApellido1F.getText().contentEquals("")  ) {
				
				sWhere =" AND `usuario`.`apellido1_usu` LIKE '" + txtApellido1F.getText() + "%'";
			}
			
			if ( txtApellido2F.getText() != null && !txtApellido2F.getText().contentEquals("")  ) {
				
				sWhere = " AND `usuario`.`apellido2_usu` LIKE '" + txtApellido2F.getText() + "%'";
			}
	
			if ( txtDNIF.getText() != null && !txtDNIF.getText().contentEquals("")  ) {
				
				sWhere =" AND `usuario`.`dni_usu` LIKE '" + txtDNIF.getText() + "%'";
			}
			if ( f_naci_F.getDate() != null ) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String sFechaNac = String.valueOf(sdf.format(f_naci_F.getDate()));
				
				sWhere = " AND `usuario`.`f_nac_usu` = '" + sFechaNac + "'" ;
				}
			
			if ( txtTlfF.getText() != null && !txtTlfF.getText().contentEquals("")  ) {
				
				sWhere =" AND `usuario`.`tlf_usu` LIKE '" + txtTlfF.getText() + "%'";
			}
			
			if ( txtEmailF.getText() != null && !txtEmailF.getText().contentEquals("")  ) {
				
				sWhere = " AND `usuario`.`email_usu` LIKE '" + txtEmailF.getText() + "%'";
			}
			
				
			
			if ( txtGeneroF.getText() != null && !txtGeneroF.getText().contentEquals("")  ) {
				
				sWhere = " AND `usuario`.`genero` LIKE '" + txtGeneroF.getText() + "%'";
			}
			
	
					
			System.out.println(sWhere);
			
			LlenarTablaSocios(sWhere);
		}
	
	protected void LimpiarFiltros() {
		txtNombreF.setText("");
		txtApellido1F.setText("");
		txtApellido2F.setText("");
		txtDNIF.setText("");
		txtTlfF.setText("");
		txtEmailF.setText("");
		txtGeneroF.setText("");			
		f_naci_F.setDate(null);
		
		LlenarTablaSocios("");
	}
}
