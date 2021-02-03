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

public class Tabla_Usuarios extends JDialog { // desde esta tabla se recogen los datos de los usuarios y los socios, y se apunta a una terapia ademas de asignarle una beca

	private final JPanel contentPanel = new JPanel();
	private JTable tUsuarios;
	private static ResultSet rsUsuarios;
	CargarJTable cargarTabla = new CargarJTable();
	private int regSelect;
	public static int idPedidoSelect = -1;
	private JTextField txtGeneroF;
	private JTextField txtEmailF;
	private JTextField txtTlfF;
	private JTextField txtDNIF;
	private JTextField txtApellido2F;
	private JTextField txtApellido1F;
	private JTextField txtNombreF;
	private JDateChooser f_nacim_F;
	private String sWhere ="";
	private JButton btnLimpiar;
	private JButton btnVolver;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Tabla_Usuarios dialog = new Tabla_Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Tabla_Usuarios() {
		setBounds(100, 100, 1302, 711);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 95, 1259, 499);
		contentPanel.add(scrollPane);
		
		tUsuarios = new JTable();
		
		
		LlenarTablaUsuarios("");
		
		
		scrollPane.setViewportView(tUsuarios);
		DefaultTableModel ModeltUsuarios= (DefaultTableModel) tUsuarios.getModel();
		
		
		tUsuarios.setModel(ModeltUsuarios);
		ModeltUsuarios.fireTableDataChanged();
		
		
		
		
		
		JButton btnApuntar = new JButton("Apuntar a una terapia");
		btnApuntar.setBackground(new Color(51, 255, 153));
		btnApuntar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnApuntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tUsuarios.getSelectedRow()>=0) {
					
					try {
						
					
						String Id = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 0));
						String Nombre = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 1));
						String Apellido1 = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 2));
						String DNI = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 4));
						//String NumSocio = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 9));
						//String Cuenta = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 10));
						//String IdSocio = String.valueOf(ModeltUsuarios.getValueAt(tUsuarios.getSelectedRow(), 11));
						
						Apuntar miApunte =new Apuntar();
						miApunte.txtNombre.setText(Nombre);
						miApunte.txtApellido1.setText(Apellido1);
						miApunte.txtDNI.setText(DNI);
						miApunte.txtId.setText(Id);
						//miApunte.txtNumSocioB.setText(NumSocio);
						//miApunte.txtCuentaBancB.setText(Cuenta);
						//miApunte.txtIdSocio.setText(IdSocio);
						miApunte.setVisible(true);
						
					
					}catch (Exception e) {
						JOptionPane.showInputDialog(this, "Esta vacio");
					}
				}
				else {
					JOptionPane.showInputDialog(this, "Debe seleccionar una fila");
				}
			}
		});
		
		btnApuntar.setBounds(700, 13, 229, 36);
		contentPanel.add(btnApuntar);
		
		txtGeneroF = new JTextField();
		txtGeneroF.setColumns(10);
		txtGeneroF.setBounds(1104, 60, 166, 22);
		contentPanel.add(txtGeneroF);
		
		txtEmailF = new JTextField();
		txtEmailF.setColumns(10);
		txtEmailF.setBounds(961, 60, 142, 22);
		contentPanel.add(txtEmailF);
		
		txtTlfF = new JTextField();
		txtTlfF.setColumns(10);
		txtTlfF.setBounds(806, 60, 154, 22);
		contentPanel.add(txtTlfF);
		
		txtDNIF = new JTextField();
		txtDNIF.setColumns(10);
		txtDNIF.setBounds(487, 60, 154, 22);
		contentPanel.add(txtDNIF);
		
		txtApellido2F = new JTextField();
		txtApellido2F.setColumns(10);
		txtApellido2F.setBounds(329, 60, 160, 22);
		contentPanel.add(txtApellido2F);
		
		txtApellido1F = new JTextField();
		txtApellido1F.setColumns(10);
		txtApellido1F.setBounds(170, 60, 160, 22);
		contentPanel.add(txtApellido1F);
		
		txtNombreF = new JTextField();
		txtNombreF.setColumns(10);
		txtNombreF.setBounds(12, 60, 160, 22);
		contentPanel.add(txtNombreF);
		
		f_nacim_F = new JDateChooser();
		f_nacim_F.setBounds(641, 60, 166, 22);
		contentPanel.add(f_nacim_F);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(51, 255, 153));
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Filtros();
			}
		});
		btnBuscar.setBounds(1145, 13, 126, 36);
		contentPanel.add(btnBuscar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(51, 255, 153));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LimpiarFiltros();
			}
		});
		btnLimpiar.setBounds(983, 13, 126, 36);
		contentPanel.add(btnLimpiar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(1174, 626, 97, 25);
		contentPanel.add(btnVolver);
	}
	
	protected void LimpiarFiltros() {
		txtNombreF.setText("");
		txtApellido1F.setText("");
		txtApellido2F.setText("");
		txtDNIF.setText("");
		txtTlfF.setText("");
		txtEmailF.setText("");
		txtGeneroF.setText("");			
		f_nacim_F.setDate(null);
		
		LlenarTablaUsuarios("");
		
	}
	
	private void Maquear() {
		Object[] titulos= new Object[12];
		titulos[0]="id";
		titulos[1]="Nombre";
		titulos[2]="Primer Apellido";
		titulos[3]="Segundo Apellido";
		titulos[4]="DNI";
		titulos[5]="Fecha de Nacimiento";
		titulos[6]="Telefono";
		titulos[7]="Email";
		titulos[8]="Genero";
		titulos[9]="id_socio2";
		
		
		
		tUsuarios.getColumnModel().getColumn(0).setMaxWidth(0);
		tUsuarios.getColumnModel().getColumn(0).setMinWidth(0);
		tUsuarios.getColumnModel().getColumn(0).setPreferredWidth(0);
		tUsuarios.getColumnModel().getColumn(0).setResizable(false);
		tUsuarios.getColumnModel().getColumn(1).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(2).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(3).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(4).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(5).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(6).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(7).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(8).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(9).setMaxWidth(0);
		tUsuarios.getColumnModel().getColumn(9).setMinWidth(0);
		tUsuarios.getColumnModel().getColumn(9).setPreferredWidth(0);
		tUsuarios.getColumnModel().getColumn(9).setResizable(false);
		
		tUsuarios.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tUsuarios.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tUsuarios.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tUsuarios.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tUsuarios.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tUsuarios.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tUsuarios.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		tUsuarios.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);
		
		
		
	}
	
	public void LlenarTablaUsuarios(String sWhere) {
		
		String sSelect = "SELECT `usuario`.`id`, `usuario`.`nomb_usu`, `usuario`.`apellido1_usu`, `usuario`.`apellido2_usu`, `usuario`.`dni_usu`, `usuario`.`f_nac_usu`, `usuario`.`tlf_usu`, `usuario`.`email_usu`, `usuario`.`genero`, `socio`.`id_socio2`" + 
				" FROM `usuario` " + 
				"	LEFT JOIN `socio` ON `socio`.`id` = `usuario`.`id` ";
		sSelect= sSelect + sWhere;
		rsUsuarios=ConnMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tUsuarios, sSelect);
			Maquear();
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	
	private void Filtros() {
		sWhere = "";
			
		
	    
		if ( txtNombreF.getText() != null && !txtNombreF.getText().contentEquals("")  ) {
		
			sWhere = sWhere + "  `usuario`.`nomb_usu` LIKE '" + txtNombreF.getText() + "%'" ;
	}
			
			if ( txtApellido1F.getText() != null && !txtApellido1F.getText().contentEquals("")  ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					sWhere = sWhere + " AND ";
				}
				sWhere = sWhere + " `usuario`.`apellido1_usu` LIKE '" + txtApellido1F.getText() + "%'";
			}
			
			if ( txtApellido2F.getText() != null && !txtApellido2F.getText().contentEquals("")  ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					sWhere = sWhere + " AND ";
				}
				sWhere = sWhere + " `usuario`.`apellido2_usu` LIKE '" + txtApellido2F.getText() + "%'";
			}
	
			if ( txtDNIF.getText() != null && !txtDNIF.getText().contentEquals("")  ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					sWhere = sWhere + " AND ";
				}
				sWhere = sWhere + " `usuario`.`dni_usu` LIKE '" + txtDNIF.getText() + "%'";
			}
			if ( f_nacim_F.getDate() != null ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					//		sWhere = sWhere + " AND ";
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    String sFechaNac = String.valueOf(sdf.format(f_nacim_F.getDate()));
				
				sWhere = sWhere + " `usuario`.`f_nac_usu` = '" + sFechaNac + "'" ;
				}
			
			if ( txtTlfF.getText() != null && !txtTlfF.getText().contentEquals("")  ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					sWhere = sWhere + " AND ";
				}
				sWhere = sWhere + " `usuario`.`tlf_usu` LIKE '" + txtTlfF.getText() + "%'";
			}
			
			if ( txtEmailF.getText() != null && !txtEmailF.getText().contentEquals("")  ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					sWhere = sWhere + " AND ";
				}
				sWhere = sWhere + " `usuario`.`email_usu` LIKE '" + txtEmailF.getText() + "%'";
			}
			
				
			
			if ( txtGeneroF.getText() != null && !txtGeneroF.getText().contentEquals("")  ) {
				if ( sWhere != null && !sWhere.contentEquals("")  ) {
					sWhere = sWhere + " AND ";
				}
				sWhere = sWhere + " `usuario`.`genero` LIKE '" + txtGeneroF.getText() + "%'";
			}
			
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = " WHERE " + sWhere;
			}
			
			
			
			
					
			System.out.println(sWhere);
			
			LlenarTablaUsuarios(sWhere);
		}
}
