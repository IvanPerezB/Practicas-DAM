package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import MetodosBBDD.ConnMySQL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

public class Socio_colaborador extends JDialog { // aqui se crea un nuevo socio colaborador

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtApellido2;
	private JTextField txtDNI;
	private JTextField txtNumCuenta;
	private JTextField txtDistrito;
	private JTextField txtProvincia;
	private JTextField txtPoblacion;
	private JDateChooser f_alta;
	private JTextField txtTelef;
	private JTextField txtEmail;
	private JTextField txtCP;
	private boolean rsSocioCol;
	private JTextField txtDireccion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Socio_colaborador dialog = new Socio_colaborador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Socio_colaborador() {
		setBounds(100, 100, 980, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(51, 153, 255));
		panel_1_1.setBounds(10, 11, 466, 288);
		contentPanel.add(panel_1_1);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNombre.setBounds(122, 11, 205, 30);
		panel_1_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 73, 30);
		panel_1_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido1");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 52, 88, 22);
		panel_1_1.add(lblNewLabel_2);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtApellido.setBounds(122, 52, 205, 30);
		panel_1_1.add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido 2");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(10, 96, 88, 22);
		panel_1_1.add(lblNewLabel_3);
		
		txtApellido2 = new JTextField();
		txtApellido2.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtApellido2.setBounds(122, 93, 205, 30);
		panel_1_1.add(txtApellido2);
		txtApellido2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("DNI");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 134, 56, 22);
		panel_1_1.add(lblNewLabel_4);
		
		txtDNI = new JTextField();
		txtDNI.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtDNI.setBounds(122, 134, 205, 30);
		panel_1_1.add(txtDNI);
		txtDNI.setColumns(10);
		
		txtTelef = new JTextField();
		txtTelef.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTelef.setColumns(10);
		txtTelef.setBounds(91, 196, 247, 27);
		panel_1_1.add(txtTelef);
		
		JLabel lblTelefono_1 = new JLabel("Telefono:");
		lblTelefono_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono_1.setBounds(10, 191, 89, 37);
		panel_1_1.add(lblTelefono_1);
		
		JLabel lblEmail_1 = new JLabel("Email: ");
		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail_1.setBounds(10, 230, 89, 37);
		panel_1_1.add(lblEmail_1);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(70, 232, 268, 27);
		panel_1_1.add(txtEmail);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBackground(new Color(51, 153, 255));
		panel_1_2_1.setBounds(10, 310, 466, 119);
		contentPanel.add(panel_1_2_1);
		
		JLabel lblDatosUsuario_1_1 = new JLabel("NUMERO DE CUENTA");
		lblDatosUsuario_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario_1_1.setBounds(124, 11, 205, 37);
		panel_1_2_1.add(lblDatosUsuario_1_1);
		
		txtNumCuenta = new JTextField();
		txtNumCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNumCuenta.setBounds(10, 59, 446, 37);
		panel_1_2_1.add(txtNumCuenta);
		txtNumCuenta.setColumns(10);
		
		JPanel panel_1_2_2_1 = new JPanel();
		panel_1_2_2_1.setLayout(null);
		panel_1_2_2_1.setBackground(new Color(51, 153, 255));
		panel_1_2_2_1.setBounds(490, 11, 466, 288);
		contentPanel.add(panel_1_2_2_1);
		
		JLabel lblEmail_1_2 = new JLabel("Distrito:");
		lblEmail_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail_1_2.setBounds(10, 48, 76, 37);
		panel_1_2_2_1.add(lblEmail_1_2);
		
		JLabel lblEmail_1_2_1 = new JLabel("Poblaci\u00F3n:");
		lblEmail_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail_1_2_1.setBounds(10, 90, 97, 37);
		panel_1_2_2_1.add(lblEmail_1_2_1);
		
		JLabel lblEmail_1_2_2 = new JLabel("Provincia:");
		lblEmail_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail_1_2_2.setBounds(10, 138, 97, 37);
		panel_1_2_2_1.add(lblEmail_1_2_2);
		
		txtDistrito = new JTextField();
		txtDistrito.setBounds(95, 57, 152, 22);
		panel_1_2_2_1.add(txtDistrito);
		txtDistrito.setColumns(10);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(129, 147, 152, 22);
		panel_1_2_2_1.add(txtProvincia);
		txtProvincia.setColumns(10);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setBounds(117, 99, 152, 22);
		panel_1_2_2_1.add(txtPoblacion);
		txtPoblacion.setColumns(10);
		
		java.util.Date today = Calendar.getInstance().getTime();
        f_alta = new JDateChooser();
        f_alta.setBounds(129, 196, 152, 22);
        f_alta.setDate(today);
		panel_1_2_2_1.add(f_alta);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha de alta");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 186, 126, 35);
		panel_1_2_2_1.add(lblNewLabel_9);
		
		txtCP = new JTextField();
		txtCP.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCP.setColumns(10);
		txtCP.setBounds(43, 234, 247, 27);
		panel_1_2_2_1.add(txtCP);
		
		JLabel lblTelefono_1_3 = new JLabel("CP:");
		lblTelefono_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono_1_3.setBounds(10, 229, 37, 37);
		panel_1_2_2_1.add(lblTelefono_1_3);
		
		JLabel lblEmail_1_2_3 = new JLabel("Direccion:");
		lblEmail_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail_1_2_3.setBounds(10, 7, 107, 37);
		panel_1_2_2_1.add(lblEmail_1_2_3);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(98, 15, 270, 22);
		panel_1_2_2_1.add(txtDireccion);
		
		JPanel panel_1_2_1_1 = new JPanel();
		panel_1_2_1_1.setLayout(null);
		panel_1_2_1_1.setBackground(new Color(51, 153, 255));
		panel_1_2_1_1.setBounds(490, 310, 466, 119);
		contentPanel.add(panel_1_2_1_1);
		
		JButton btnDarAlta = new JButton("Dar de Alta\r\n");
		btnDarAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertarDatos();
			}
		});
		btnDarAlta.setBounds(248, 39, 208, 47);
		panel_1_2_1_1.add(btnDarAlta);
		btnDarAlta.setForeground(Color.BLACK);
		btnDarAlta.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDarAlta.setBackground(new Color(51, 255, 102));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(10, 39, 208, 47);
		panel_1_2_1_1.add(btnVolver);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnVolver.setBackground(new Color(255, 0, 51));
	}
	
	private void InsertarDatos() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sFechaAlta = String.valueOf(sdf.format(f_alta.getDate()));


        String sSelect = "INSERT INTO socio_colaborador (nomb_c, apellido1_c, apellido2_c, dni_c,f_altac,tlf_c,emailc,direccion,cp_c, distritoc,"
                        + " poblacionc, provinciac,num_cuentac) VALUES (" + 
                         "'" + txtNombre.getText() + "'," +
                         "'" + txtApellido.getText() + "'," +
                         "'" + txtApellido2.getText() + "'," +
                         "'" + txtDNI.getText() + "'," +
                         "'" + sFechaAlta + "'," +
                         "'" + txtTelef.getText() + "'," +
                         "'" + txtEmail.getText() + "'," +
                         "'" + txtDireccion.getText() + "'," +
                         "'" + txtCP.getText() + "'," +
                         "'" + txtDistrito.getText() + "'," +
                         "'" + txtPoblacion.getText() + "'," +
                         "'" + txtProvincia.getText() + "'," +
                         "'" + txtNumCuenta.getText() + "')";
        
       
        
        rsSocioCol = ConnMySQL.EjecutarSQL(sSelect); 
    }
}
