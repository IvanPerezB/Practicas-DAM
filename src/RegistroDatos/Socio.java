package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import MetodosBBDD.CargarCombo;
import MetodosBBDD.Item;
import MetodosBBDD.ConnMySQL;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Socio extends JDialog { // en esta clase se meten los datos de un nuevo socio

	private final JPanel contentPanel = new JPanel();
	private JTextField 	NomASocFam1Pag;
	private JTextField Apell1AsocFam1Pag;
	private JTextField Apell2AsocFam1Pag;
	private JTextField DNIAsocFam;
	private JTextField ProAsocFam1;
	private JTextField Telef1Domic;
	private JTextField Movil1AsocFam1;
	private JTextField Movil2AsocFam1;
	private JTextField EmailAsocFam1;
	private JTextField RelaAsocFam1Usu;
	private JTextField NomAsocFam2;
	private JTextField Apell1AsocFam2;
	private JTextField Apell2AsocFam2;
	private JTextField ProAsocFam2;
	private JTextField EmailAsocFam2;
	private JTextField Telef2Domic;
	private JTextField RelAsocFam2Usu;
	private JTextField NomUsuAsocNum;
	private JTextField Apell1UsuAsocNum;
	private JTextField Apell2UsuAsocNum;
	private JTextField DNIUsuAsocNum;
	private JTextField TeleUsuAsocNum;
	private JTextField EmailUsuAsocNum;
	private JTextField CentrDiag;
	private JTextField NomCentEdu;
	private JTextField ProfDiag;
	private JTextField Direccion;
	private JTextField CP;
	private JTextField Distrito;
	private JTextField Pobla;
	private JTextField Provincia;
	private JTextField NumBanco;
	private String est ;
	private JRadioButton RBDiag;
	private JRadioButton RBAuto;
	

	
	private JSpinner spinnerSocio;
	private JDateChooser FNaciUsuAsocNum;
	private JDateChooser FDiag;
	private JComboBox Genero;
	private JComboBox EtiqDiag;
	static String nombreApuntar;
	static String Apellido1Apuntar;
	static String Apellido2Apuntar;
	static String DNIApuntar;
	static Date FNacimientoApuntar;
	static String TlfnApuntar;
	static String EmailApuntar;
	static String GeneroApuntar;
	
	 static int Socio;
	 static String Nomb;
	 static String Apell1;
	
	int idtera=0; 
	boolean rsSocio ;
	ResultSet rsSocio2;
	
	Item item ;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Socio dialog = new Socio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Socio() { // creamos un nuevo socio insertando sus datos
		setBounds(100, 100, 1456, 950);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(51, 153, 255));
		panel_1_1.setBounds(10, 11, 466, 96);
		contentPanel.add(panel_1_1);
		
		JLabel lblRecogidaDeDatos = new JLabel("N\u00BA SOCIO:");
		lblRecogidaDeDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRecogidaDeDatos.setBounds(25, 31, 121, 20);
		panel_1_1.add(lblRecogidaDeDatos);
		
		spinnerSocio = new JSpinner();
		spinnerSocio.setBounds(143, 33, 82, 20);
		panel_1_1.add(spinnerSocio);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 153, 255));
		panel_1.setBounds(10, 118, 466, 786);
		contentPanel.add(panel_1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(10, 102, 104, 37);
		panel_1.add(lblNombre);
		
		JLabel lblApellido = new JLabel("1\u1D49\u02B3 Apellido: ");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido.setBounds(10, 133, 117, 37);
		panel_1.add(lblApellido);
		
		JLabel lblApellido2 = new JLabel("2\u00BA Apellido: ");
		lblApellido2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido2.setBounds(10, 164, 104, 37);
		panel_1.add(lblApellido2);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono.setBounds(10, 31, 89, 37);
		panel_1.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(10, 64, 89, 37);
		panel_1.add(lblEmail);
		
		JLabel lblDatosUsuario = new JLabel("DATOS USUARIO");
		lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario.setBounds(145, 0, 179, 37);
		panel_1.add(lblDatosUsuario);
		
		JLabel lblFechaDeNacimiento_1 = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFechaDeNacimiento_1.setBounds(10, 240, 179, 29);
		panel_1.add(lblFechaDeNacimiento_1);
		
		JLabel lblGenero_1 = new JLabel("Genero:");
		lblGenero_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenero_1.setBounds(10, 284, 78, 20);
		panel_1.add(lblGenero_1);
		
		JLabel lblNombreDelCentro_1 = new JLabel("Nombre del Centro Educativo:");
		lblNombreDelCentro_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreDelCentro_1.setBounds(10, 305, 252, 33);
		panel_1.add(lblNombreDelCentro_1);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDni.setBounds(10, 204, 44, 37);
		panel_1.add(lblDni);
		
		JLabel lblNomCentEdunstico_1 = new JLabel("CENTRO DIAGN\u00D3STICO");
		lblNomCentEdunstico_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomCentEdunstico_1.setBounds(20, 481, 216, 20);
		panel_1.add(lblNomCentEdunstico_1);
		
		JLabel lblProfesionalDiagnstico_1 = new JLabel("PROFESIONAL DIAGN\u00D3STICO");
		lblProfesionalDiagnstico_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProfesionalDiagnstico_1.setBounds(20, 524, 242, 29);
		panel_1.add(lblProfesionalDiagnstico_1);
		
		JLabel lblFechaDiagnstico_1 = new JLabel("FECHA DIAGN\u00D3STICO");
		lblFechaDiagnstico_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFechaDiagnstico_1.setBounds(20, 574, 202, 20);
		panel_1.add(lblFechaDiagnstico_1);
		
		JLabel lblEtiquetaDiagnstica_1 = new JLabel("ETIQUETA DIAGN\u00D3STICO");
		lblEtiquetaDiagnstica_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEtiquetaDiagnstica_1.setBounds(20, 418, 242, 27);
		panel_1.add(lblEtiquetaDiagnstica_1);
		
		JLabel lblAutorizacinImagenes_1 = new JLabel("Autorizaci\u00F3n imagenes");
		lblAutorizacinImagenes_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAutorizacinImagenes_1.setBounds(52, 377, 222, 30);
		panel_1.add(lblAutorizacinImagenes_1);
		
		NomUsuAsocNum = new JTextField();
		NomUsuAsocNum.setBounds(94, 112, 142, 20);
		panel_1.add(NomUsuAsocNum);
		NomUsuAsocNum.setColumns(10);
		
		Apell1UsuAsocNum = new JTextField();
		Apell1UsuAsocNum.setBounds(115, 143, 159, 20);
		panel_1.add(Apell1UsuAsocNum);
		Apell1UsuAsocNum.setColumns(10);
		
		Apell2UsuAsocNum = new JTextField();
		Apell2UsuAsocNum.setBounds(115, 174, 159, 20);
		panel_1.add(Apell2UsuAsocNum);
		Apell2UsuAsocNum.setColumns(10);
		
		DNIUsuAsocNum = new JTextField();
		DNIUsuAsocNum.setBounds(52, 212, 124, 20);
		panel_1.add(DNIUsuAsocNum);
		DNIUsuAsocNum.setColumns(10);
		
		FNaciUsuAsocNum = new JDateChooser();
		FNaciUsuAsocNum.setBounds(187, 249, 95, 20);
		panel_1.add(FNaciUsuAsocNum);
		
		TeleUsuAsocNum = new JTextField();
		TeleUsuAsocNum.setBounds(94, 41, 128, 20);
		panel_1.add(TeleUsuAsocNum);
		TeleUsuAsocNum.setColumns(10);
		
		EmailUsuAsocNum = new JTextField();
		EmailUsuAsocNum.setBounds(70, 71, 202, 20);
		panel_1.add(EmailUsuAsocNum);
		EmailUsuAsocNum.setColumns(10);
		
		Genero = new JComboBox();
		Genero.setBounds(81, 286, 95, 20);
		panel_1.add(Genero);
		Genero.setModel(new DefaultComboBoxModel(new String[] {"", "FEMENINO", "MASCULINO", "INTERGENERO"}));
		
		NomCentEdu = new JTextField();
		NomCentEdu.setBounds(19, 337, 95, 20);
		panel_1.add(NomCentEdu);
		NomCentEdu.setColumns(10);
		
		 RBAuto = new JRadioButton("");
		 RBAuto.setBounds(10, 377, 29, 23);
		 panel_1.add(RBAuto);
		 RBAuto.setContentAreaFilled(false);
		 
		 JLabel lblDiagnstico = new JLabel("DIAGN\u00D3STICO");
		 lblDiagnstico.setBounds(270, 387, 95, 14);
		 panel_1.add(lblDiagnstico);
		 
		  RBDiag = new JRadioButton("");
		  RBDiag.setBounds(380, 384, 29, 23);
		  panel_1.add(RBDiag);
		  RBDiag.setDoubleBuffered(true);
		  RBDiag.setContentAreaFilled(false);
		  
		  EtiqDiag = new JComboBox();
		  EtiqDiag.setBounds(256, 423, 95, 20);
		  panel_1.add(EtiqDiag);
		  EtiqDiag.setModel(new DefaultComboBoxModel(new String[] {"", "TEA DSM-V", "SA DSM-IV o CIE10", "TGD", "TGDNos"}));
		  
		  CentrDiag = new JTextField();
		  CentrDiag.setBounds(229, 483, 95, 20);
		  panel_1.add(CentrDiag);
		  CentrDiag.setColumns(10);
		  
		  		
		  		ProfDiag = new JTextField();
		  		ProfDiag.setBounds(270, 530, 95, 20);
		  		panel_1.add(ProfDiag);
		  		ProfDiag.setColumns(10);
		  		
		  		FDiag = new JDateChooser();
		  		FDiag.setBounds(217, 574, 95, 20);
		  		panel_1.add(FDiag);
		  		
		  		JPanel panel_1_2_2_1 = new JPanel();
		  		panel_1_2_2_1.setLayout(null);
		  		panel_1_2_2_1.setBackground(new Color(51, 153, 255));
		  		panel_1_2_2_1.setBounds(962, 11, 466, 374);
		  		contentPanel.add(panel_1_2_2_1);
		  		
		  		JLabel lblDatosUsuario_1_2_1 = new JLabel("DATOS ADICIONALES");
		  		lblDatosUsuario_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblDatosUsuario_1_2_1.setBounds(133, 0, 323, 37);
		  		panel_1_2_2_1.add(lblDatosUsuario_1_2_1);
		  		
		  		JLabel lblDiagnosticosAnteriores_1_1 = new JLabel("Direcci\u00F3n:");
		  		lblDiagnosticosAnteriores_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblDiagnosticosAnteriores_1_1.setBounds(10, 36, 122, 20);
		  		panel_1_2_2_1.add(lblDiagnosticosAnteriores_1_1);
		  		
		  		JLabel lblTelefono_1_3 = new JLabel("CP:");
		  		lblTelefono_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblTelefono_1_3.setBounds(10, 141, 37, 37);
		  		panel_1_2_2_1.add(lblTelefono_1_3);
		  		
		  		JLabel lblEmail_1_2 = new JLabel("Distrito:");
		  		lblEmail_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblEmail_1_2.setBounds(10, 189, 76, 37);
		  		panel_1_2_2_1.add(lblEmail_1_2);
		  		
		  		JLabel lblEmail_1_2_1 = new JLabel("Poblaci\u00F3n:");
		  		lblEmail_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblEmail_1_2_1.setBounds(10, 237, 97, 37);
		  		panel_1_2_2_1.add(lblEmail_1_2_1);
		  		
		  		JLabel lblEmail_1_2_2 = new JLabel("Provincia:");
		  		lblEmail_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblEmail_1_2_2.setBounds(10, 285, 97, 37);
		  		panel_1_2_2_1.add(lblEmail_1_2_2);
		  		
		  		Direccion = new JTextField();
		  		Direccion.setBounds(33, 72, 323, 20);
		  		panel_1_2_2_1.add(Direccion);
		  		Direccion.setColumns(10);
		  		
		  		CP = new JTextField();
		  		CP.setBounds(56, 151, 107, 20);
		  		panel_1_2_2_1.add(CP);
		  		CP.setColumns(10);
		  		
		  		Distrito = new JTextField();
		  		Distrito.setBounds(96, 199, 134, 20);
		  		panel_1_2_2_1.add(Distrito);
		  		Distrito.setColumns(10);
		  		
		  		Pobla = new JTextField();
		  		Pobla.setBounds(116, 247, 146, 20);
		  		panel_1_2_2_1.add(Pobla);
		  		Pobla.setColumns(10);
		  		
		  		Provincia = new JTextField();
		  		Provincia.setBounds(119, 294, 143, 20);
		  		panel_1_2_2_1.add(Provincia);
		  		Provincia.setColumns(10);
		  		
		  		JPanel panel_1_2_1 = new JPanel();
		  		panel_1_2_1.setLayout(null);
		  		panel_1_2_1.setBackground(new Color(51, 153, 255));
		  		panel_1_2_1.setBounds(486, 11, 466, 119);
		  		contentPanel.add(panel_1_2_1);
		  		
		  		JLabel lblDatosUsuario_1_1 = new JLabel("NUMERO DE CUENTA");
		  		lblDatosUsuario_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblDatosUsuario_1_1.setBounds(124, 11, 205, 37);
		  		panel_1_2_1.add(lblDatosUsuario_1_1);
		  		
		  		NumBanco = new JTextField();
		  		NumBanco.setBounds(75, 61, 293, 20);
		  		panel_1_2_1.add(NumBanco);
		  		NumBanco.setColumns(10);
		  		
		  		JPanel panel_1_2 = new JPanel();
		  		panel_1_2.setLayout(null);
		  		panel_1_2.setBackground(new Color(51, 153, 255));
		  		panel_1_2.setBounds(486, 141, 466, 448);
		  		contentPanel.add(panel_1_2);
		  		
		  		JLabel lblNombre_1 = new JLabel("Nombre:");
		  		lblNombre_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblNombre_1.setBounds(10, 223, 104, 37);
		  		panel_1_2.add(lblNombre_1);
		  		
		  		JLabel lblApellido_1 = new JLabel("1\u1D49\u02B3 Apellido: ");
		  		lblApellido_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblApellido_1.setBounds(10, 255, 116, 37);
		  		panel_1_2.add(lblApellido_1);
		  		
		  		JLabel lblApellido2_1 = new JLabel("2\u00BA Apellido: ");
		  		lblApellido2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblApellido2_1.setBounds(10, 284, 104, 37);
		  		panel_1_2.add(lblApellido2_1);
		  		
		  		JLabel lblTelefono_1 = new JLabel("Telefono:");
		  		lblTelefono_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblTelefono_1.setBounds(10, 68, 89, 37);
		  		panel_1_2.add(lblTelefono_1);
		  		
		  		JLabel lblEmail_1 = new JLabel("Email: ");
		  		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblEmail_1.setBounds(10, 183, 89, 37);
		  		panel_1_2.add(lblEmail_1);
		  		
		  		JLabel lblNombreAsociado_1 = new JLabel("Profesi\u00F3n:\r\n");
		  		lblNombreAsociado_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblNombreAsociado_1.setBounds(10, 321, 89, 37);
		  		panel_1_2.add(lblNombreAsociado_1);
		  		
		  		JLabel lblDatosUsuario_1 = new JLabel("DATOS 1\u1D49\u02B3 FAMILIAR (PAGADOR)");
		  		lblDatosUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblDatosUsuario_1.setBounds(70, 0, 323, 37);
		  		panel_1_2.add(lblDatosUsuario_1);
		  		
		  		JLabel lblTelefono_1_2 = new JLabel("El usuario es el pagador (Autocompletar)");
		  		lblTelefono_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblTelefono_1_2.setBounds(10, 36, 341, 37);
		  		panel_1_2.add(lblTelefono_1_2);
		  		
		  		JButton btnNewButton = new JButton("Autocomp.");
		  		btnNewButton.addMouseListener(new MouseAdapter() {
		  			@Override
		  			public void mouseClicked(MouseEvent arg0) {
		  				 autocompletar();
		  			}
		  		});
		  		btnNewButton.setBounds(367, 36, 89, 37);
		  		panel_1_2.add(btnNewButton);
		  		
		  		JLabel lblDni_1 = new JLabel("DNI:");
		  		lblDni_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblDni_1.setBounds(10, 357, 44, 37);
		  		panel_1_2.add(lblDni_1);
		  		
		  		JLabel lblNombreAsociado_1_1 = new JLabel("Relaci\u00F3n:");
		  		lblNombreAsociado_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblNombreAsociado_1_1.setBounds(10, 400, 89, 37);
		  		panel_1_2.add(lblNombreAsociado_1_1);
		  		
		  		JLabel Movil1AsocFam1232131 = new JLabel("Movil 1:");
		  		Movil1AsocFam1232131.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		Movil1AsocFam1232131.setBounds(10, 104, 89, 37);
		  		panel_1_2.add(Movil1AsocFam1232131);
		  		
		  		JLabel Movil2As321ocFam1 = new JLabel("Movil 2:");
		  		Movil2As321ocFam1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		Movil2As321ocFam1.setBounds(10, 147, 89, 37);
		  		panel_1_2.add(Movil2As321ocFam1);
		  		{
		  			NomASocFam1Pag = new JTextField();
		  			NomASocFam1Pag.setBounds(101, 233, 122, 20);
		  			panel_1_2.add(NomASocFam1Pag);
		  			NomASocFam1Pag.setColumns(10);
		  		}
		  		{
		  			Apell1AsocFam1Pag = new JTextField();
		  			Apell1AsocFam1Pag.setBounds(124, 265, 158, 20);
		  			panel_1_2.add(Apell1AsocFam1Pag);
		  			Apell1AsocFam1Pag.setColumns(10);
		  		}
		  		{
		  			Apell2AsocFam1Pag = new JTextField();
		  			Apell2AsocFam1Pag.setBounds(124, 294, 158, 20);
		  			panel_1_2.add(Apell2AsocFam1Pag);
		  			Apell2AsocFam1Pag.setColumns(10);
		  		}
		  		{
		  			DNIAsocFam = new JTextField();
		  			DNIAsocFam.setBounds(48, 367, 86, 20);
		  			panel_1_2.add(DNIAsocFam);
		  			DNIAsocFam.setColumns(10);
		  		}
		  		{
		  			ProAsocFam1 = new JTextField();
		  			ProAsocFam1.setBounds(109, 332, 86, 20);
		  			panel_1_2.add(ProAsocFam1);
		  			ProAsocFam1.setColumns(10);
		  		}
		  		{
		  			Telef1Domic = new JTextField();
		  			Telef1Domic.setBounds(89, 78, 86, 20);
		  			panel_1_2.add(Telef1Domic);
		  			Telef1Domic.setColumns(10);
		  		}
		  		{
		  			Movil1AsocFam1 = new JTextField();
		  			Movil1AsocFam1.setBounds(89, 114, 86, 20);
		  			panel_1_2.add(Movil1AsocFam1);
		  			Movil1AsocFam1.setColumns(10);
		  		}
		  		{
		  			Movil2AsocFam1 = new JTextField();
		  			Movil2AsocFam1.setBounds(89, 157, 86, 20);
		  			panel_1_2.add(Movil2AsocFam1);
		  			Movil2AsocFam1.setColumns(10);
		  		}
		  		{
		  			EmailAsocFam1 = new JTextField();
		  			EmailAsocFam1.setBounds(70, 192, 185, 20);
		  			panel_1_2.add(EmailAsocFam1);
		  			EmailAsocFam1.setColumns(10);
		  		}
		  		{
		  			RelaAsocFam1Usu = new JTextField();
		  			RelaAsocFam1Usu.setBounds(89, 410, 86, 20);
		  			panel_1_2.add(RelaAsocFam1Usu);
		  			RelaAsocFam1Usu.setColumns(10);
		  		}
		  		
		  		JPanel panel_1_2_2 = new JPanel();
		  		panel_1_2_2.setLayout(null);
		  		panel_1_2_2.setBackground(new Color(51, 153, 255));
		  		panel_1_2_2.setBounds(486, 600, 466, 304);
		  		contentPanel.add(panel_1_2_2);
		  		
		  		JLabel lblNombre_1_1 = new JLabel("Nombre:");
		  		lblNombre_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblNombre_1_1.setBounds(10, 109, 104, 37);
		  		panel_1_2_2.add(lblNombre_1_1);
		  		
		  		JLabel lblApellido_1_1 = new JLabel("1\u1D49\u02B3 Apellido: ");
		  		lblApellido_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblApellido_1_1.setBounds(10, 141, 117, 37);
		  		panel_1_2_2.add(lblApellido_1_1);
		  		
		  		JLabel lblApellido2_1_1 = new JLabel("2\u00BA Apellido: ");
		  		lblApellido2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblApellido2_1_1.setBounds(10, 170, 104, 37);
		  		panel_1_2_2.add(lblApellido2_1_1);
		  		
		  		JLabel lblTelefono_1_1 = new JLabel("Telefono:");
		  		lblTelefono_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblTelefono_1_1.setBounds(10, 34, 89, 37);
		  		panel_1_2_2.add(lblTelefono_1_1);
		  		
		  		JLabel lblEmail_1_1 = new JLabel("Email: ");
		  		lblEmail_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblEmail_1_1.setBounds(10, 67, 89, 37);
		  		panel_1_2_2.add(lblEmail_1_1);
		  		
		  		JLabel lblDatosUsuario_1_2 = new JLabel("DATOS 2\u00BA FAMILIAR (OPCIONAL)");
		  		lblDatosUsuario_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblDatosUsuario_1_2.setBounds(110, 0, 323, 37);
		  		panel_1_2_2.add(lblDatosUsuario_1_2);
		  		
		  		JLabel lblNombreAsociado_1_2 = new JLabel("Profesi\u00F3n:\r\n");
		  		lblNombreAsociado_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblNombreAsociado_1_2.setBounds(10, 206, 89, 37);
		  		panel_1_2_2.add(lblNombreAsociado_1_2);
		  		
		  		JLabel lblNombreAsociado_1_1_1 = new JLabel("Relaci\u00F3n:");
		  		lblNombreAsociado_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		lblNombreAsociado_1_1_1.setBounds(10, 249, 89, 37);
		  		panel_1_2_2.add(lblNombreAsociado_1_1_1);
		  		{
		  			NomAsocFam2 = new JTextField();
		  			NomAsocFam2.setBounds(110, 119, 86, 20);
		  			panel_1_2_2.add(NomAsocFam2);
		  			NomAsocFam2.setColumns(10);
		  		}
		  		{
		  			Apell1AsocFam2 = new JTextField();
		  			Apell1AsocFam2.setBounds(120, 151, 86, 20);
		  			panel_1_2_2.add(Apell1AsocFam2);
		  			Apell1AsocFam2.setColumns(10);
		  		}
		  		{
		  			Apell2AsocFam2 = new JTextField();
		  			Apell2AsocFam2.setBounds(110, 180, 86, 20);
		  			panel_1_2_2.add(Apell2AsocFam2);
		  			Apell2AsocFam2.setColumns(10);
		  		}
		  		{
		  			ProAsocFam2 = new JTextField();
		  			ProAsocFam2.setBounds(110, 216, 86, 20);
		  			panel_1_2_2.add(ProAsocFam2);
		  			ProAsocFam2.setColumns(10);
		  		}
		  		{
		  			EmailAsocFam2 = new JTextField();
		  			EmailAsocFam2.setBounds(73, 78, 86, 20);
		  			panel_1_2_2.add(EmailAsocFam2);
		  			EmailAsocFam2.setColumns(10);
		  		}
		  		{
		  			Telef2Domic = new JTextField();
		  			Telef2Domic.setBounds(92, 44, 86, 20);
		  			panel_1_2_2.add(Telef2Domic);
		  			Telef2Domic.setColumns(10);
		  		}
		  		{
		  			RelAsocFam2Usu = new JTextField();
		  			RelAsocFam2Usu.setBounds(92, 259, 86, 20);
		  			panel_1_2_2.add(RelAsocFam2Usu);
		  			RelAsocFam2Usu.setColumns(10);
		  		}
		  		
		  		JPanel panel_1_2_2_2 = new JPanel();
		  		panel_1_2_2_2.setLayout(null);
		  		panel_1_2_2_2.setBackground(new Color(51, 153, 255));
		  		panel_1_2_2_2.setBounds(962, 396, 466, 105);
		  		contentPanel.add(panel_1_2_2_2);
		  		JButton btnVolver = new JButton("Volver");
		  		btnVolver.setBounds(34, 30, 149, 34);
		  		panel_1_2_2_2.add(btnVolver);
		  		btnVolver.setBackground(new Color(51, 153, 255));
		  		btnVolver.addMouseListener(new MouseAdapter() {
		  			@Override
		  			public void mouseClicked(MouseEvent e) {
		  				
		  				dispose();
		  			}
		  		});
		  		btnVolver.setBorder(new RoundedBorder(20));
		  		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		  		
		  		
		  		JButton btnConfirmar = new JButton("Confirmar");
		  		btnConfirmar.setBackground(new Color(51, 153, 255));
		  		btnConfirmar.setBounds(238, 30, 149, 34);
		  		panel_1_2_2_2.add(btnConfirmar);
		  		btnConfirmar.addMouseListener(new MouseAdapter() {
		  			@Override
		  			public void mouseClicked(MouseEvent e) {
		  				Tabla_Usuarios tUsu = new Tabla_Usuarios();
		  				

		  				tUsu.LlenarTablaUsuarios("");
		  				
		  				try {
							InsertarDatos();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		  				new Tabla_Usuarios().setVisible(true);
		  				dispose();
		  			}
		  		});
		  		btnConfirmar.setBorder(new RoundedBorder(20));
		  		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		setLocationRelativeTo(null);
	}
	
	
	private static class RoundedBorder implements Border {

		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {

			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

			g.drawRoundRect(x, y, width - 1, height - 1, radius + 5, radius + 5);
			// g.fillRoundRect(x, y, width, height, radius+5, radius+5);

		}

	}
	private void InsertarDatos() throws ParseException {
		

		String Diagnostico="";
		String Autorizacion ="";
		
		if(DNIUsuAsocNum.equals("")) {
			JOptionPane.showMessageDialog(null, "El DNI es obligatorio");
		}else if(NumBanco.equals("")){
			JOptionPane.showMessageDialog(null, "El numero de cuenta es obligatorio");
			
		}
		else if(NomUsuAsocNum.equals("")) {
			JOptionPane.showMessageDialog(null, "El nombre de usuario es obligatorio");
		}
		else {
		if(RBDiag.isSelected() == true) {
			Diagnostico ="1";
		}else if(RBDiag.isSelected() == false) {
			Diagnostico ="0";
		}
		
		if(RBAuto.isSelected() == true) {
			Autorizacion="1";
		}else if(RBAuto.isSelected() == false) {
			Autorizacion ="0";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sFechaNaci = String.valueOf(sdf.format(FNaciUsuAsocNum.getDate()));
		String sFechaDiag = String.valueOf(sdf.format(FDiag.getDate()));
		
		 
		 nombreApuntar= NomUsuAsocNum.getText() ;
		 Apellido1Apuntar = Apell1UsuAsocNum.getText();
		 Apellido2Apuntar = Apell2UsuAsocNum.getText();
		 DNIApuntar = DNIUsuAsocNum.getText();
		 //FNacimientoApuntar =  sFechaNaci;
		 TlfnApuntar = TeleUsuAsocNum.getText() ;
		 EmailApuntar = EmailUsuAsocNum.getText();
		 GeneroApuntar =  Genero.getSelectedItem().toString();
		 

		   Socio =  (int) spinnerSocio.getValue();
		   Nomb =  NomUsuAsocNum.getText();
		   Apell1 = Apell1UsuAsocNum.getText();
		   
		
	    String sSelect4 ="INSERT INTO Usuario (id,nomb_usu," + 
						 "apellido1_usu, apellido2_usu, dni_usu, f_nac_usu,f_pet_terap, tlf_usu," + 
						 "email_usu,genero) VALUES (id," +
						 "'" + NomUsuAsocNum.getText() + "'," +
						 "'" + Apell1UsuAsocNum.getText() + "'," +
						 "'" + Apell2UsuAsocNum.getText() + "'," +
						 "'" + DNIUsuAsocNum.getText() + "'," +
						 "'" + sFechaNaci+ "'," +
						 "CURTIME() ,"+
						 "'" + TeleUsuAsocNum.getText() + "'," +
						 "'" + EmailUsuAsocNum.getText() + "',"+
						 "'" + Genero.getSelectedItem().toString() + "')" ;
	    rsSocio = ConnMySQL.EjecutarSQL(sSelect4); 
		
		
		
		String sSelect = "INSERT INTO socio ( id_socio2, id, id_socio, f_alta, etiq_diag, nom_centro, diagnostico, autorizacion_img, centro_diag, prof_diag , f_diag, " + 
				         "tlf_domic, direccion, cp, distrito, poblacion, provincia, num_cuenta) VALUES (id_socio2, (SELECT MAX(id) FROM usuario)," + 
		        		 "'" + spinnerSocio.getValue() + "'," +
		        		 " CURTIME()," +
		        		 "'" + EtiqDiag.getSelectedItem().toString() + "'," +
		        		 "'" + NomCentEdu.getText() + "'," +
		        		 "'" + Integer.parseInt(Diagnostico) + "'," +
		        		 "'" + Integer.parseInt(Autorizacion) + "'," +
		        		 "'" + CentrDiag.getText() + "'," +
		        		 "'" + ProfDiag.getText() + "'," +
		        		 "'" + sFechaDiag + "'," +
		        		 "'" + Telef1Domic.getText() + "'," +
		        		 "'" + Direccion.getText() + "'," +
		        		 "'" + CP.getText() + "'," +
		        		 "'" + Distrito.getText() + "'," +
		        		 "'" + Pobla.getText() + "'," +
		        		 "'" + Provincia.getText() + "'," +
		        		 "'" + NumBanco.getText() + "')";
        
		
		
		String sSelect2 = "INSERT INTO AsociadoPagador ( id_asoc,id,id_socio2,nom_asoc_fam1_pag," + 
						 "apellido1_asoc_fam1_pag, apellido2_asoc_fam1_pag, dni_asoc_fam1_pag, profe_asoc_fam1_pag, movil1_asoc_fam1_pag," + 
						 "movil2_asoc_fam1_pag, email_asoc_fam1_pag, relac_asoc_fam1_pag) VALUES (id_asoc,(SELECT MAX(id) FROM usuario),(SELECT MAX(id_socio2) FROM socio),"+
						 "'" + NomASocFam1Pag.getText() + "'," +
     				     "'" + Apell1AsocFam1Pag.getText() + "'," +
     				     "'" + Apell2AsocFam1Pag.getText() + "'," +
     				     "'" + DNIAsocFam.getText() + "'," +
     				     "'" + ProAsocFam1.getText() + "'," +
     				     "'" + Movil1AsocFam1.getText() + "'," +
     				     "'" + Movil2AsocFam1.getText() + "'," +
     				     "'" + EmailAsocFam1.getText() + "'," +
     				     "'" + RelaAsocFam1Usu.getText() + "')";
		
		String sSelect3 = "INSERT INTO AsociadoFamiliar ( id_asoc_fam,id,id_socio2 ,nom_asoc_fam2, apellido1_asoc_fam2," + 
						 "apellido2_asoc_fam2, profe_asoc_fam2, email_asoc_fam2, movil1_asoc_fam2, relac_asoc_fam2) VALUES (id_asoc_fam,(SELECT MAX(id) FROM usuario),(SELECT MAX(id_socio2) FROM socio)," +
						 "'" + NomAsocFam2.getText() + "'," +
						 "'" + Apell1AsocFam2.getText() + "'," +
						 "'" + Apell2AsocFam2.getText() + "'," +
						 "'" + ProAsocFam2.getText() + "'," +
						 "'" + EmailAsocFam2.getText() + "'," +
						 "'" + Telef2Domic.getText() + "'," +
      				   	 "'" + RelAsocFam2Usu.getText() + "')";
		
		
		
				
		
		
		
        rsSocio = ConnMySQL.EjecutarSQL(sSelect); 
        rsSocio = ConnMySQL.EjecutarSQL(sSelect2); 
        rsSocio = ConnMySQL.EjecutarSQL(sSelect3); 
        
		}
    }
	private void autocompletar() {

        Telef1Domic.setText(TeleUsuAsocNum.getText());
        EmailAsocFam1.setText(EmailUsuAsocNum.getText());
        NomASocFam1Pag.setText(NomUsuAsocNum.getText());
        Apell1AsocFam1Pag.setText(Apell1UsuAsocNum.getText());
        Apell2AsocFam1Pag.setText(Apell2UsuAsocNum.getText());
        DNIAsocFam.setText(DNIUsuAsocNum.getText());
    }
	
	/*private boolean ComprobarUsuarioExiste() {
		String sSelect = "SELECT id, nomb_usu FROM usuario WHERE " + " dni_usu = '" + DNIUsuAsocNum.getText()
				+ "'";

		System.out.println(sSelect);
		rsSocio2 = ConnMySQL.sSQL(sSelect);

		try {
			if (rsSocio2.next()) {
				JOptionPane.showMessageDialog(null,
						"El usuario " + NomUsuAsocNum.getText() + " ya está registrado en la BBDD");
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Se registrará el usuario " + NomUsuAsocNum.getText() + " en la BBDD");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}*/
	
	
	/*private boolean ComprobarSocioExiste() {
		String sSelect = "SELECT id_socio2 FROM socio WHERE " + " num_cuenta = '" + NumBanco.getText()
				+ "'";

		System.out.println(sSelect);
		rsSocio2 = ConnMySQL.sSQL(sSelect);

		try {
			if (rsSocio2.next()) {
				JOptionPane.showMessageDialog(null,
						"El socio " + NomUsuAsocNum.getText() + " ya está registrado en la BBDD");
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Se registrará el socio " + NomUsuAsocNum.getText() + " en la BBDD");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}*/
}