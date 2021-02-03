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
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.lowagie.toolbox.plugins.Txt2Pdf;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Font;

public class DarDeBaja extends JDialog { //Esta clase sirve para dar de baja a un usuario, introduciendo de forma automatica la fecha actual.

	
	private final JPanel contentPanel = new JPanel();
	private JTable tUsuarios;
	
	private  ResultSet rsUsuarios;
	private  boolean rsUsu;
	CargarJTable cargarTabla = new CargarJTable();
	private int regSelect;
	public  int idPedidoSelect = -1;
	private JTextField txtDNIF;
	private JTextField txtNumSocioF;
	private JTextField txtNombreF;
	private JTextField txtApellido1F;
	private JTextField txtApellido2F;
	private JTextField txtTlfF;
	private JTextField txtEmailF;
	private JTextField txtBecadoF;
	private JTextField txtTipoBecaF;
	private String sWhere ="";
	private JDateChooser fNacimiento;
	private JDateChooser fpeticion;
	private JComboBox comboBox;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DarDeBaja dialog = new DarDeBaja();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DarDeBaja() {
		setBounds(100, 100, 1164, 758);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(12, 184, 1126, 524);
		contentPanel.add(scrollPane);
		
		tUsuarios = new JTable();
		
		
		LlenarTabla("");
		
		
		scrollPane.setViewportView(tUsuarios);
		
		DefaultTableModel ModeltUsuarios = (DefaultTableModel) tUsuarios.getModel();
		
		
		
		tUsuarios.setModel(ModeltUsuarios);
		
		JLabel lblDniUsuario = new JLabel("DNI ");
		lblDniUsuario.setBounds(49, 79, 74, 14);
		contentPanel.add(lblDniUsuario);
		
		JLabel lblTelefonoUsuario = new JLabel("Telefono ");
		lblTelefonoUsuario.setBounds(723, 79, 98, 14);
		contentPanel.add(lblTelefonoUsuario);
		
		txtEmailF = new JTextField();
		txtEmailF.setBounds(115, 133, 86, 20);
		contentPanel.add(txtEmailF);
		txtEmailF.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(49, 136, 46, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(236, 136, 46, 14);
		contentPanel.add(lblGenero);
		
		txtBecadoF = new JTextField();
		txtBecadoF.setBounds(520, 133, 86, 20);
		contentPanel.add(txtBecadoF);
		txtBecadoF.setColumns(10);
		
		JLabel lblBecado = new JLabel("Becado");
		lblBecado.setBounds(443, 136, 46, 14);
		contentPanel.add(lblBecado);
		
		JLabel lblTipoDeBeca = new JLabel("Tipo de beca");
		lblTipoDeBeca.setBounds(662, 136, 74, 14);
		contentPanel.add(lblTipoDeBeca);
		
		txtTipoBecaF = new JTextField();
		txtTipoBecaF.setBounds(748, 133, 86, 20);
		contentPanel.add(txtTipoBecaF);
		txtTipoBecaF.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.GREEN);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Filtros();
			}
		});
		btnBuscar.setBounds(969, 83, 149, 70);
		contentPanel.add(btnBuscar);
		
		 fNacimiento = new JDateChooser();
		fNacimiento.setBounds(336, 76, 95, 20);
		contentPanel.add(fNacimiento);
		
		 fpeticion = new JDateChooser();
		fpeticion.setBounds(616, 76, 95, 20);
		contentPanel.add(fpeticion);
		
		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Masculino", "Femenino", "Intergenero"}));
		comboBox.setBounds(309, 133, 124, 20);
		contentPanel.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(51, 153, 255));
		panel.setBounds(0, 0, 1148, 719);
		contentPanel.add(panel);
		
		txtNumSocioF = new JTextField();
		txtNumSocioF.setBounds(99, 24, 86, 20);
		panel.add(txtNumSocioF);
		txtNumSocioF.setColumns(10);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(197, 27, 102, 14);
		panel.add(lblNombreUsuario);
		
		txtNombreF = new JTextField();
		txtNombreF.setBounds(311, 24, 86, 20);
		panel.add(txtNombreF);
		txtNombreF.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido 1 ");
		lblApellido.setBounds(407, 27, 74, 14);
		panel.add(lblApellido);
		
		txtApellido1F = new JTextField();
		txtApellido1F.setBounds(491, 24, 86, 20);
		panel.add(txtApellido1F);
		txtApellido1F.setColumns(10);
		
		JLabel lblApellido_1 = new JLabel("Apellido 2");
		lblApellido_1.setBounds(598, 27, 74, 14);
		panel.add(lblApellido_1);
		
		txtApellido2F = new JTextField();
		txtApellido2F.setBounds(667, 24, 86, 20);
		panel.add(txtApellido2F);
		txtApellido2F.setColumns(10);
		
		JButton btnDarDeBaja = new JButton("Dar de baja");
		btnDarDeBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 0));
				//String idRegistro = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 1));
				//String nombre = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 3));
				//String idSocio = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 2));
				//String Dni = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 6));
				
				
				
				
				String sSelect2 =" UPDATE usuario SET f_baja = CURTIME() WHERE id="+id; //Sentencia SQL que introduce la fecha actual con CURTIME
				rsUsu = ConnMySQL.EjecutarSQL(sSelect2); 
						 
				
				
				
			}
		});
		btnDarDeBaja.setBackground(Color.RED);
		btnDarDeBaja.setBounds(969, 11, 149, 70);
		panel.add(btnDarDeBaja);
		
		JLabel lblIdSocio = new JLabel("N\u00BA Socio");
		lblIdSocio.setBounds(47, 27, 74, 14);
		panel.add(lblIdSocio);
		
		JLabel lblFechaPeticionDe = new JLabel("Fecha peticion de la terapia");
		lblFechaPeticionDe.setBounds(441, 81, 171, 14);
		panel.add(lblFechaPeticionDe);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento");
		lblFechaNacimiento.setBounds(213, 81, 109, 14);
		panel.add(lblFechaNacimiento);
		
		txtDNIF = new JTextField();
		txtDNIF.setBounds(80, 78, 86, 20);
		panel.add(txtDNIF);
		txtDNIF.setColumns(10);
		
		txtTlfF = new JTextField();
		txtTlfF.setBounds(805, 78, 86, 20);
		panel.add(txtTlfF);
		txtTlfF.setColumns(10);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LimpiarFiltros();
			}
		});
		btnLimpiar.setBounds(862, 11, 86, 25);
		panel.add(btnLimpiar);
		ModeltUsuarios.fireTableDataChanged();
		
		
				
	}
	
	private void Maquear() {  // Este método crea la tabla personalizada
		Object[] titulos= new Object[15];
		titulos[0]="id";
		titulos[1]="id Socio";
		titulos[2]="id terapias";
		titulos[3]="Nº de Socio";
		titulos[4]="Nombre";
		titulos[5]="Primer Apellido";
		titulos[6]="Segundo Apellido";
		titulos[7]="DNI";
		titulos[8]="Fecha de Nacimiento";
		titulos[9]="Fecha peticion terapia";
		titulos[10]="Telefono";
		titulos[11]="Email";
		titulos[12]="Genero";
		titulos[13]="Becado";
		titulos[14]="Tipo de Beca";
		
		
		
		tUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
		tUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
		tUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
		tUsuarios.getColumnModel().getColumn(0).setResizable(false);
		
		tUsuarios.getColumnModel().getColumn(1).setMaxWidth(0);
		tUsuarios.getColumnModel().getColumn(1).setMinWidth(0);
		tUsuarios.getColumnModel().getColumn(1).setPreferredWidth(0);
		tUsuarios.getColumnModel().getColumn(1).setResizable(false);
		
		tUsuarios.getColumnModel().getColumn(2).setMaxWidth(0);
		tUsuarios.getColumnModel().getColumn(2).setMinWidth(0);
		tUsuarios.getColumnModel().getColumn(2).setPreferredWidth(0);
		tUsuarios.getColumnModel().getColumn(2).setResizable(false);
		
		tUsuarios.getColumnModel().getColumn(3).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(4).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(5).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(6).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(7).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(8).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(9).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(10).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(11).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(12).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(13).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(14).setMaxWidth(1000);
		
		
		tUsuarios.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tUsuarios.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tUsuarios.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tUsuarios.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tUsuarios.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tUsuarios.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tUsuarios.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		tUsuarios.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);
		tUsuarios.getColumnModel().getColumn(9).setHeaderValue(titulos[9]);
		tUsuarios.getColumnModel().getColumn(10).setHeaderValue(titulos[10]);
		tUsuarios.getColumnModel().getColumn(11).setHeaderValue(titulos[11]);
		tUsuarios.getColumnModel().getColumn(12).setHeaderValue(titulos[12]);
		tUsuarios.getColumnModel().getColumn(13).setHeaderValue(titulos[13]);
		tUsuarios.getColumnModel().getColumn(14).setHeaderValue(titulos[14]);
	}
	
	private void LlenarTabla(String sWhere) {  //Este método llena la tabla tUsuarios
		
		String sSelect = "SELECT `usuario`.`id`, `socio`.`id_socio2`, `terapia`.`id_tera`, `socio`.`id_socio`, `usuario`.`nomb_usu`, `usuario`.`apellido1_usu`, `usuario`.`apellido2_usu`, `usuario`.`dni_usu`, `usuario`.`f_nac_usu`, `usuario`.`f_pet_terap`, `usuario`.`tlf_usu`, `usuario`.`email_usu`, `usuario`.`genero`, `usuario`.`becado`, `usuario`.`tipo_beca`" + 
				" FROM `usuario` " + 
				" LEFT JOIN `socio` ON `socio`.`id` = `usuario`.`id`" + 
				"	, `terapia` ";
		
		sSelect= sSelect + sWhere +" ORDER BY nomb_usu";
		
		rsUsuarios=ConnMySQL.sSQL(sSelect); // Ejecuta la sentencia SQL, llamando al método ConnMySQL de MetodosBBDD
		
		try {
			cargarTabla.LlenarTabla(tUsuarios, sSelect);
			Maquear();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	
	
	
	
	
	private void Filtros() { //Este método se utiliza para filtrar la tabla 
	sWhere = "";
		
	
    
		
		
		if ( txtDNIF.getText() != null && !txtDNIF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " dni_usu LIKE ( " + txtDNIF.getText() + ")";
		}
		
		if ( txtNumSocioF.getText() != null && !txtNumSocioF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " id_socio LIKE ( \"" + txtNumSocioF.getText() + "%\")";
		}
		
	
		
		if ( fNacimiento.getDate() != null ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				//		sWhere = sWhere + " AND ";
			}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String sFechaNac = String.valueOf(sdf.format(fNacimiento.getDate()));
			
			sWhere = sWhere + " f_nac_usu = '" + sFechaNac + "'" ;
			}
		
		if ( txtNombreF.getText() != null && !txtNombreF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " nomb_usu LIKE ( \"" + txtNombreF.getText() + "%\")";
		}
		
		if ( txtApellido1F.getText() != null && !txtApellido1F.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido1_usu LIKE ( \"" + txtApellido1F.getText() + "%\")";
		}
		
		if ( txtApellido2F.getText() != null && !txtApellido2F.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido2_usu LIKE ( \"" + txtApellido2F.getText() + "%\")";
		}
		
			if ( fpeticion.getDate() != null ) {
		  if ( sWhere != null && !sWhere.contentEquals("")  ) {
		          sWhere = sWhere + " AND ";
		     }
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		    String sFechaPet = String.valueOf(s.format(fNacimiento.getDate()));
            	sWhere = sWhere + " f_pet_terap  = '" + sFechaPet + "'";
			}
		
		if ( txtTlfF.getText() != null && !txtTlfF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " tlf_usu LIKE ( \"" + txtTlfF.getText() + "%\")";
		}
		
		if ( txtEmailF.getText() != null && !txtEmailF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " email_usu LIKE ( \"" + txtEmailF.getText() + "%\")";
		}
		
		if ( comboBox.getSelectedItem().toString() != null && !comboBox.getSelectedItem().toString().equals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " genero LIKE ( \"" + comboBox.getSelectedItem().toString() + "%\")";
		}
		
		if ( txtBecadoF.getText() != null && !txtBecadoF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " becado LIKE ( \"" + txtBecadoF.getText() + "%\")";
		}
		
		if ( txtTipoBecaF.getText() != null && !txtTipoBecaF.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " tipo_beca LIKE ( \"" + txtTipoBecaF.getText() + "%\")";
		}
		
		if ( sWhere != null && !sWhere.contentEquals("")  ) {
			sWhere = " WHERE " + sWhere;
		}
		
				
		System.out.println(sWhere);
		
		LlenarTabla(sWhere);
	}
	
	protected void LimpiarFiltros() { //Este método sirve para limpiar los campos que estan llenos y recargar la tabla.
		
		txtNombreF.setText("");
		txtApellido1F.setText("");
		txtApellido2F.setText("");
		txtDNIF.setText("");
		txtTlfF.setText("");
		txtEmailF.setText("");
		txtNumSocioF.setText("");
		comboBox.setSelectedIndex(0);
				
		fNacimiento.setDate(null);
		fpeticion.setDate(null);
		txtBecadoF.setText("");
		txtTipoBecaF.setText("");
		
		
		LlenarTabla("");
	}
}

