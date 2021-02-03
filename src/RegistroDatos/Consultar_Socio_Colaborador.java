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
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.Font;

public class Consultar_Socio_Colaborador extends JDialog {
	/*

    Esta es una ventana para consultas. El tipo de consulta es el que indica el propio titulo de la clase. Por lo general estas ventanas mostrarán una tabla para las busquedas y unos
    campos rellenables, los cuales sirven para hacer filtros en la busqueda.

    En estas clases hay dos metodos diferenciados, uno para llenar la tabla segun se accede a la ventana con todos los datos, pues es la consulta por defecto mostrará todos. El segundo metodo
    sirve para aplicar los filtros en la busqueda y despues actualizará automaticamente la tabla.

    Hay más metodos auxiliares, como el de limpiar los filtros para que los usuarios no pierdan el tiempo en ello. 

    Realmente lo complicado de estas clases son las sentencias, pero las que hay ahora mismo funcionan todas por lo que a menos que hallais realizado cambios en la bbdd



*/

	private final JPanel contentPanel = new JPanel();
	private JTable tUsuarios;
	private static ResultSet rsUsuarios;
	CargarJTable cargarTabla = new CargarJTable();
	private int regSelect;
	public static int idPedidoSelect = -1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_11;
	private JTextField textField_12;
	private String sWhere ="";
	private JDateChooser dateChooser;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consultar_Socio_Colaborador dialog = new Consultar_Socio_Colaborador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consultar_Socio_Colaborador() {
		setBounds(100, 100, 1164, 758);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 1126, 524);
		contentPanel.add(scrollPane);
		
		tUsuarios = new JTable();
		
		
		LlenarTablaUsuarios("");
		
		
		scrollPane.setViewportView(tUsuarios);
		DefaultTableModel ModeltUsuarios= (DefaultTableModel) tUsuarios.getModel();
		
		
		
		
		
		Object[] titulos= new Object[14];
		titulos[0]="id";
		titulos[1]="Nombre Socio Colaborador";
		titulos[2]="Apellido 1";
		titulos[3]="Apellido 2";
		titulos[4]="DNI";
		titulos[5]="Fecha Alta";
		titulos[6]="Telefono";
		titulos[7]="Email";
		titulos[8]="Direccion";
		titulos[9]="Poblacion";
		titulos[10]="Provincia";
		titulos[11]="Numero de Cuenta";
		
		
		
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
		tUsuarios.getColumnModel().getColumn(9).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(10).setMaxWidth(1000);
		tUsuarios.getColumnModel().getColumn(11).setMaxWidth(1000);
		
		
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
		
		tUsuarios.setModel(ModeltUsuarios);
		
		JLabel lblIdTerapia = new JLabel("Nombre");
		lblIdTerapia.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdTerapia.setBounds(49, 31, 74, 14);
		contentPanel.add(lblIdTerapia);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(115, 28, 116, 23);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDniUsuario = new JLabel("Telefono");
		lblDniUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDniUsuario.setBounds(49, 79, 74, 14);
		contentPanel.add(lblDniUsuario);
		
		JLabel lblIdSocio = new JLabel("Apellido 1");
		lblIdSocio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdSocio.setBounds(236, 31, 74, 14);
		contentPanel.add(lblIdSocio);
		
		JLabel lblNombreUsuario = new JLabel("Apellido 2");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreUsuario.setBounds(424, 31, 86, 14);
		contentPanel.add(lblNombreUsuario);
		
		JLabel lblApellido = new JLabel("DNI");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellido.setBounds(634, 31, 74, 14);
		contentPanel.add(lblApellido);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(51, 153, 255));
		panel.setBounds(0, 0, 1148, 719);
		contentPanel.add(panel);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_2.setBounds(306, 27, 112, 23);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		 dateChooser = new JDateChooser();
		 dateChooser.setBounds(949, 27, 95, 20);
		 panel.add(dateChooser);
		 
		 JLabel lblFechaNacimiento = new JLabel("Fecha de alta");
		 lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblFechaNacimiento.setBounds(832, 30, 106, 14);
		 panel.add(lblFechaNacimiento);
		 
		 JLabel lblTelefonoUsuario = new JLabel("Direccion");
		 lblTelefonoUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblTelefonoUsuario.setBounds(485, 79, 86, 14);
		 panel.add(lblTelefonoUsuario);
		 
		 textField_8 = new JTextField();
		 textField_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_8.setBounds(549, 76, 195, 23);
		 panel.add(textField_8);
		 textField_8.setColumns(10);
		 
		 textField_9 = new JTextField();
		 textField_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_9.setBounds(283, 76, 192, 23);
		 panel.add(textField_9);
		 textField_9.setColumns(10);
		 
		 JLabel lblEmail = new JLabel("Email");
		 lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblEmail.setBounds(240, 79, 46, 14);
		 panel.add(lblEmail);
		 
		 JLabel lblBecado = new JLabel("Codigo postal");
		 lblBecado.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblBecado.setBounds(754, 78, 106, 17);
		 panel.add(lblBecado);
		 
		 textField_11 = new JTextField();
		 textField_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_11.setBounds(858, 75, 100, 23);
		 panel.add(textField_11);
		 textField_11.setColumns(10);
		 
		 JLabel lblTipoDeBeca = new JLabel("Distrito\r\n");
		 lblTipoDeBeca.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblTipoDeBeca.setBounds(47, 140, 74, 14);
		 panel.add(lblTipoDeBeca);
		 
		 textField_12 = new JTextField();
		 textField_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_12.setBounds(99, 137, 118, 23);
		 panel.add(textField_12);
		 textField_12.setColumns(10);
		 
		 textField_1 = new JTextField();
		 textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_1.setBounds(115, 76, 115, 23);
		 panel.add(textField_1);
		 textField_1.setColumns(10);
		 
		 textField_3 = new JTextField();
		 textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_3.setColumns(10);
		 textField_3.setBounds(295, 134, 112, 26);
		 panel.add(textField_3);
		 
		 JLabel lblPoblacion = new JLabel("Poblacion");
		 lblPoblacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblPoblacion.setBounds(227, 137, 74, 14);
		 panel.add(lblPoblacion);
		 
		 textField_6 = new JTextField();
		 textField_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_6.setColumns(10);
		 textField_6.setBounds(485, 137, 86, 23);
		 panel.add(textField_6);
		 
		 JLabel lblProvincia = new JLabel("Provincia");
		 lblProvincia.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblProvincia.setBounds(417, 140, 74, 14);
		 panel.add(lblProvincia);
		 
		 textField_7 = new JTextField();
		 textField_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		 textField_7.setColumns(10);
		 textField_7.setBounds(665, 137, 293, 23);
		 panel.add(textField_7);
		 
		 JLabel lblNCuenta = new JLabel("N\u00BA Cuenta");
		 lblNCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		 lblNCuenta.setBounds(581, 140, 74, 14);
		 panel.add(lblNCuenta);
		 
		 textField_4 = new JTextField();
		 textField_4.setBounds(499, 28, 126, 22);
		 panel.add(textField_4);
		 textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_4.setColumns(10);
		 
		 textField_5 = new JTextField();
		 textField_5.setBounds(665, 28, 157, 22);
		 panel.add(textField_5);
		 textField_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		 textField_5.setColumns(10);
		 
		 JButton btnNewButton = new JButton("Buscar");
		 btnNewButton.setBounds(987, 60, 149, 47);
		 panel.add(btnNewButton);
		 btnNewButton.setBackground(new Color(51, 255, 102));
		 
		 JButton btnVolver = new JButton("Volver");
		 btnVolver.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		
		 		dispose();
		 	}
		 });
		 btnVolver.setBounds(987, 120, 149, 40);
		 panel.add(btnVolver);
		 btnNewButton.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		Filtros();
		 	}
		 });
		ModeltUsuarios.fireTableDataChanged();
		
		
		
		
		
	}
	
	public void LlenarTablaUsuarios(String sWhere) {
		
		String sSelect = "SELECT id_c, nomb_c, apellido1_c, apellido2_c, dni_c, f_altac, tlf_c, emailc, direccion, cp_c, "
				+ "distritoc, poblacionc, provinciac, num_cuentac FROM socio_colaborador ";
		
		sSelect= sSelect + sWhere +" ORDER BY nomb_c";
		
		rsUsuarios=ConnMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tUsuarios, sSelect);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
		
	}
	
	public void Filtros() {
	sWhere = "";
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String sFechaAlta = String.valueOf(sdf.format(dateChooser.getDate()));
		
		if ( textField.getText() != null && !textField.getText().contentEquals("")  ) {
				sWhere = sWhere + " nomb_c LIKE ( \"" + textField.getText() + "%\")" ;
		}
		
		if ( textField_1.getText() != null && !textField_1.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " tlf_c LIKE ( \"" + textField_1.getText() + "%\")";
		}
		
		if ( textField_2.getText() != null && !textField_2.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido1_c LIKE ( \"" + textField_2.getText() + "%\")";
		}
		
		if ( dateChooser.getDate() != null ) {
            if ( sWhere != null && !sWhere.contentEquals("")  ) {
                sWhere = sWhere + " AND ";
            }
			sWhere = sWhere + " f_altac = '" + sFechaAlta +"'";
		}
		
		if ( textField_4.getText() != null && !textField_4.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido2_c LIKE ( \"" + textField_4.getText() + "%\")";
		}
		if ( textField_5.getText() != null && !textField_5.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " dni_c LIKE ( \"" + textField_5.getText() + "%\")";
		}
		if ( textField_6.getText() != null && !textField_6.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " provinciac LIKE ( \"" + textField_6.getText() + "%\")";
		}
		
		if ( textField_8.getText() != null && !textField_8.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " direccion LIKE ( \"" + textField_8.getText() + "%\")";
		}
		if ( textField_9.getText() != null && !textField_9.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " emailc LIKE ( \"" + textField_9.getText() + "%\")";
		}
		
		if ( textField_11.getText() != null && !textField_11.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " cp_c LIKE ( \"" + textField_11.getText() + "%\")";
		}
		if ( textField_12.getText() != null && !textField_12.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " distritoc LIKE ( \"" + textField_12.getText() + "%\")";
		}
		if ( textField_3.getText() != null && !textField_3.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " poblacionc LIKE ( \"" + textField_3.getText() + "%\")";
		}
		if ( textField_6.getText() != null && !textField_6.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " provinciac LIKE ( \"" + textField_6.getText() + "%\")";
		}
		if ( textField_7.getText() != null && !textField_7.getText().contentEquals("")  ) {
			if ( sWhere != null && !sWhere.contentEquals("")  ) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " num_cuentac LIKE ( \"" + textField_7.getText() + "%\")";
		}
		
		if ( sWhere != null && !sWhere.contentEquals("")  ) {
			sWhere = " WHERE " + sWhere;
		}
		
				
		System.out.println(sWhere);
		
		LlenarTablaUsuarios(sWhere);
	}
}

