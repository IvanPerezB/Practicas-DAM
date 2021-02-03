package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import MetodosBBDD.ConnMySQL;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Component;

public class PrimeraEntrevista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDistrito;
	private JTextField txtNomCentro;
	private JTextArea txtDiagnosticosAnteriores;
	private JTextField txtCentroDiagnostico;
	private JTextField txtProfeDiagnostico;
	private JTextField txtGradoDisc;
	private JTextField txtGradoNivelDep;
	private JTextField txtNumUsu;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField txtTlfUsu;
	private JTextField txtMailUsu;
	private JTextField txtNombreUsu;
	private JTextField txt1ApellidoUsu;
	private JTextField txt2ApellidoUsu;
	private JTextField txtTelfFam1;
	private JTextField txtMailFam1;
	private JTextField txtNombreFam1;
	private JTextField txtApellidoFam1Pag;
	private JTextField txtApellido2Fam1Pag;
	private JTextField txtProfesionAsocfam1;
	private JTextField txtTlffam2;
	private JTextField txtMailfam2;
	private JTextField txtNom2fam;
	private JTextField txtApellido1fam2;
	private JTextField txtApellido2Fam2;
	private JDateChooser f_1entrevista;
	private JDateChooser fNacimUsu;
	private JDateChooser f_sol_eval_dep;
	private JCheckBox checkDiagnostico;
	private JTextArea txtObservDep;
	private JTextArea txtObservDerivTerap;
	private JCheckBox checkVaTerap;
	private JTextArea txtGenograma;
	private JCheckBox chckbxAcnecesidadEducativa_1;
	private JCheckBox checkAmigos;
	private JTextArea txtObsAdicionales;
	private JTextArea txtPrincDiffam;
	private JCheckBox checkLOPD;
	private JTextField txtDNIusu;
	private JComboBox cmbGenero; 
	
	boolean rsEntrevista;
	private JTextField txtDNIAsocFam1Pag;
	private JTextField txtProfAsocfam2;
	private int id =-1 ;
	private int idsocio2 =-1;
	private int id_registro_s =-1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PrimeraEntrevista dialog = new PrimeraEntrevista();
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
	public PrimeraEntrevista() {
		/*

	    Este metodo corresponde con uno de los formularios de la asociación que utilizan cuando un usuario tiene un primer contacto con ellos.
	    Es importante que tengais en cuenta los campos que son obligatorios y cuales no (en funcion de como esta hecho en la bbdd)
	    La clase es simple, cuenta con campos rellenables y al darle al botón continuar hará los inserts correspondientes.
	    Por ultimo esta clase cuenta con un botón de autocompletar que es un recurso para que los usuarios no tengan que volver a escribir en caso de que el usuario tambiensea el pagador.


	    */
		setBounds(100, 100, 1451, 1028);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2_1.setLayout(null);
		panel_1_2_1.setBackground(new Color(51, 153, 255));
		panel_1_2_1.setBounds(486, 11, 466, 970);
		contentPanel.add(panel_1_2_1);
		
		JLabel lblDatosUsuario_1_1 = new JLabel("DIAGNOSTICO");
		lblDatosUsuario_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario_1_1.setBounds(164, 0, 179, 37);
		panel_1_2_1.add(lblDatosUsuario_1_1);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:\r\n");
		lblDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiagnostico.setBounds(10, 48, 104, 29);
		panel_1_2_1.add(lblDiagnostico);
		
		checkDiagnostico = new JCheckBox("");
		checkDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkDiagnostico.setBackground(new Color(51, 153, 255));
		checkDiagnostico.setBounds(120, 48, 47, 29);
		panel_1_2_1.add(checkDiagnostico);
		
		JLabel lblEtiquetaDiagnostico_1 = new JLabel("Etiqueta diagnostico:");
		lblEtiquetaDiagnostico_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEtiquetaDiagnostico_1.setBounds(10, 88, 179, 29);
		panel_1_2_1.add(lblEtiquetaDiagnostico_1);
		
		JComboBox cmbEtiquetaDiagnostico = new JComboBox();
		cmbEtiquetaDiagnostico.setModel(new DefaultComboBoxModel(new String[] {"", "TEA DSM-V", "SA DSM-IV o CIE10", "TGD", "TGD Nos"}));
		cmbEtiquetaDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbEtiquetaDiagnostico.setBounds(187, 92, 222, 25);
		panel_1_2_1.add(cmbEtiquetaDiagnostico);
		
		JLabel lblTrastornosAsociados_1 = new JLabel("Trastornos Asociados:");
		lblTrastornosAsociados_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTrastornosAsociados_1.setBounds(10, 142, 187, 29);
		panel_1_2_1.add(lblTrastornosAsociados_1);
		
		JLabel lblDiagnosticosAnteriores_1 = new JLabel("Diagnosticos Anteriores:");
		lblDiagnosticosAnteriores_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiagnosticosAnteriores_1.setBounds(10, 201, 208, 20);
		panel_1_2_1.add(lblDiagnosticosAnteriores_1);
		
		JLabel lblCentroDeDiagnostico = new JLabel("Centro de diagnostico:");
		lblCentroDeDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCentroDeDiagnostico.setBounds(10, 353, 195, 29);
		panel_1_2_1.add(lblCentroDeDiagnostico);
		
		JLabel lblProfesionalDiagnostico = new JLabel("Profesional diagnostico:");
		lblProfesionalDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProfesionalDiagnostico.setBounds(10, 409, 208, 25);
		panel_1_2_1.add(lblProfesionalDiagnostico);
		
		JLabel lblFechaSolicitudEvaluacin = new JLabel("Fecha Solicitud Evaluaci\u00F3n Dependencia:");
		lblFechaSolicitudEvaluacin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFechaSolicitudEvaluacin.setBounds(10, 482, 333, 29);
		panel_1_2_1.add(lblFechaSolicitudEvaluacin);
		
		JLabel lblGradoNivelDependencia = new JLabel("Grado Nivel Dependencia:");
		lblGradoNivelDependencia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGradoNivelDependencia.setBounds(10, 521, 222, 29);
		panel_1_2_1.add(lblGradoNivelDependencia);
		
		JLabel lblAsociadoDeNumerousuario = new JLabel("Asociado de Numero/Usuario:");
		lblAsociadoDeNumerousuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAsociadoDeNumerousuario.setBounds(10, 561, 247, 25);
		panel_1_2_1.add(lblAsociadoDeNumerousuario);
		
		JLabel lblObservacionesDeDependencia = new JLabel("Observaciones de dependencia:");
		lblObservacionesDeDependencia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblObservacionesDeDependencia.setBounds(10, 597, 275, 25);
		panel_1_2_1.add(lblObservacionesDeDependencia);
		
		txtDiagnosticosAnteriores = new JTextArea();
		txtDiagnosticosAnteriores.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDiagnosticosAnteriores.setColumns(10);
		txtDiagnosticosAnteriores.setBounds(10, 232, 446, 103);
		panel_1_2_1.add(txtDiagnosticosAnteriores);
		
		JComboBox cmbTrastornosAsociados = new JComboBox();
		cmbTrastornosAsociados.setModel(new DefaultComboBoxModel(new String[] {"", "TDA", "TDAH", "TOC", "Transtorno de Ansiedad", "Depresi\u00F3n"}));
		cmbTrastornosAsociados.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbTrastornosAsociados.setBounds(199, 147, 222, 25);
		panel_1_2_1.add(cmbTrastornosAsociados);
		
		txtCentroDiagnostico = new JTextField();
		txtCentroDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCentroDiagnostico.setColumns(10);
		txtCentroDiagnostico.setBounds(199, 354, 257, 27);
		panel_1_2_1.add(txtCentroDiagnostico);
		
		txtProfeDiagnostico = new JTextField();
		txtProfeDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtProfeDiagnostico.setColumns(10);
		txtProfeDiagnostico.setBounds(209, 408, 247, 27);
		panel_1_2_1.add(txtProfeDiagnostico);
		
		txtGradoDisc = new JTextField();
		txtGradoDisc.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGradoDisc.setColumns(10);
		txtGradoDisc.setBounds(277, 445, 179, 27);
		panel_1_2_1.add(txtGradoDisc);
		
		f_sol_eval_dep = new JDateChooser();
		f_sol_eval_dep.setBounds(341, 482, 115, 27);
		panel_1_2_1.add(f_sol_eval_dep);
		
		txtGradoNivelDep = new JTextField();
		txtGradoNivelDep.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGradoNivelDep.setColumns(10);
		txtGradoNivelDep.setBounds(230, 522, 226, 27);
		panel_1_2_1.add(txtGradoNivelDep);
		
		txtNumUsu = new JTextField();
		txtNumUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNumUsu.setColumns(10);
		txtNumUsu.setBounds(261, 560, 195, 27);
		panel_1_2_1.add(txtNumUsu);
		
		txtObservDep = new JTextArea();
		txtObservDep.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtObservDep.setColumns(10);
		txtObservDep.setBounds(10, 626, 446, 103);
		panel_1_2_1.add(txtObservDep);
		
		JLabel lblHaIdoA_1 = new JLabel("Ha ido a terapia:");
		lblHaIdoA_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblHaIdoA_1.setBounds(10, 740, 147, 25);
		panel_1_2_1.add(lblHaIdoA_1);
		
		checkVaTerap = new JCheckBox("");
		checkVaTerap.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkVaTerap.setBackground(new Color(51, 153, 255));
		checkVaTerap.setBounds(149, 736, 40, 37);
		panel_1_2_1.add(checkVaTerap);
		
		JLabel lblObservaciones_1 = new JLabel("Observaciones derivacion/terapia:");
		lblObservaciones_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblObservaciones_1.setBounds(10, 776, 295, 25);
		panel_1_2_1.add(lblObservaciones_1);
		
		txtObservDerivTerap = new JTextArea();
		txtObservDerivTerap.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtObservDerivTerap.setColumns(10);
		txtObservDerivTerap.setBounds(10, 812, 446, 103);
		panel_1_2_1.add(txtObservDerivTerap);
		
		JLabel lblGradoDiscapacidadReconocido_1 = new JLabel("Grado Discapacidad Reconocido:");
		lblGradoDiscapacidadReconocido_1.setBounds(10, 446, 275, 25);
		panel_1_2_1.add(lblGradoDiscapacidadReconocido_1);
		lblGradoDiscapacidadReconocido_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(51, 153, 255));
		panel_1.setBounds(10, 204, 466, 449);
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
		
		fNacimUsu = new JDateChooser();
		fNacimUsu.setBounds(216, 265, 152, 29);
		panel_1.add(fNacimUsu);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFechaDeNacimiento.setBounds(10, 265, 179, 29);
		panel_1.add(lblFechaDeNacimiento);
		
		JLabel lblGenero_1 = new JLabel("Genero:");
		lblGenero_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenero_1.setBounds(10, 309, 78, 20);
		panel_1.add(lblGenero_1);
		
		cmbGenero = new JComboBox();
		cmbGenero.setModel(new DefaultComboBoxModel(new String[] {"", "FEMENINO", "MASCULINO", "INTERGENERO"}));
		cmbGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbGenero.setBounds(84, 305, 152, 29);
		panel_1.add(cmbGenero);
		
		JLabel lblNombreDelCentro_1 = new JLabel("Nombre del Centro Educativo:");
		lblNombreDelCentro_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreDelCentro_1.setBounds(10, 335, 252, 33);
		panel_1.add(lblNombreDelCentro_1);
		
		txtNomCentro = new JTextField();
		txtNomCentro.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNomCentro.setColumns(10);
		txtNomCentro.setBounds(10, 367, 446, 29);
		panel_1.add(txtNomCentro);
		
		JLabel lblApoyos = new JLabel("Apoyos:");
		lblApoyos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApoyos.setBounds(10, 407, 78, 29);
		panel_1.add(lblApoyos);
		
		JComboBox cmbApoyos = new JComboBox();
		cmbApoyos.setModel(new DefaultComboBoxModel(new String[] {"", "Ordinario", "Ord. Apoyo PT y Al", "Preferentes sin Aula", "Preferentes con Aula"}));
		cmbApoyos.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbApoyos.setBounds(84, 409, 152, 25);
		panel_1.add(cmbApoyos);
		
		txtTlfUsu = new JTextField();
		txtTlfUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTlfUsu.setColumns(10);
		txtTlfUsu.setBounds(95, 41, 247, 27);
		panel_1.add(txtTlfUsu);
		
		txtMailUsu = new JTextField();
		txtMailUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMailUsu.setColumns(10);
		txtMailUsu.setBounds(64, 74, 247, 27);
		panel_1.add(txtMailUsu);
		
		txtNombreUsu = new JTextField();
		txtNombreUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombreUsu.setColumns(10);
		txtNombreUsu.setBounds(84, 112, 247, 27);
		panel_1.add(txtNombreUsu);
		
		txt1ApellidoUsu = new JTextField();
		txt1ApellidoUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt1ApellidoUsu.setColumns(10);
		txt1ApellidoUsu.setBounds(121, 143, 247, 27);
		panel_1.add(txt1ApellidoUsu);
		
		txt2ApellidoUsu = new JTextField();
		txt2ApellidoUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt2ApellidoUsu.setColumns(10);
		txt2ApellidoUsu.setBounds(121, 174, 247, 27);
		panel_1.add(txt2ApellidoUsu);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDni.setBounds(39, 204, 65, 37);
		panel_1.add(lblDni);
		
		txtDNIusu = new JTextField();
		txtDNIusu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDNIusu.setColumns(10);
		txtDNIusu.setBounds(121, 209, 247, 27);
		panel_1.add(txtDNIusu);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(51, 153, 255));
		panel_1_1.setBounds(10, 11, 466, 180);
		contentPanel.add(panel_1_1);
		
		JLabel lblRecogidaDeDatos = new JLabel("RECOGIDA DE DATOS");
		lblRecogidaDeDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRecogidaDeDatos.setBounds(25, 16, 167, 20);
		panel_1_1.add(lblRecogidaDeDatos);
		
		JLabel lblFechaEntrevista = new JLabel("FECHA 1\u00AA ENTREVISTA");
		lblFechaEntrevista.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaEntrevista.setBounds(25, 49, 178, 20);
		panel_1_1.add(lblFechaEntrevista);
		
		JLabel lblDistrito = new JLabel("DISTRITO");
		lblDistrito.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistrito.setBounds(25, 82, 87, 27);
		panel_1_1.add(lblDistrito);
		
		JLabel lblDerivacion = new JLabel("DERIVACION");
		lblDerivacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDerivacion.setBounds(25, 127, 134, 20);
		panel_1_1.add(lblDerivacion);
		
		JComboBox cmbDerivacion = new JComboBox();
		cmbDerivacion.setModel(new DefaultComboBoxModel(new String[] {"", "Dervi. P. Sanitario", "Deriv. P. Educativo", "Web", "M. Comunicaci\u00F3n", "Amigo/ familiar"}));
		cmbDerivacion.setBounds(238, 129, 204, 27);
		panel_1_1.add(cmbDerivacion);
		
		txtDistrito = new JTextField();
		txtDistrito.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDistrito.setColumns(10);
		txtDistrito.setBounds(238, 87, 204, 27);
		panel_1_1.add(txtDistrito);
		
		f_1entrevista = new JDateChooser();
		f_1entrevista.setBounds(238, 49, 204, 27);
		panel_1_1.add(f_1entrevista);
		
		JComboBox cmbRecogidaDatos = new JComboBox();
		cmbRecogidaDatos.setModel(new DefaultComboBoxModel(new String[] {"", "Primera Entrevista", "Llamada telefonica", "Email", "Demanda de socio", "Formulario", "En persona"}));
		cmbRecogidaDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbRecogidaDatos.setBounds(238, 13, 204, 27);
		panel_1_1.add(cmbRecogidaDatos);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(51, 153, 255));
		panel_1_2.setBounds(10, 664, 466, 317);
		contentPanel.add(panel_1_2);
		
		JLabel lblNombre_1 = new JLabel("Nombre:");
		lblNombre_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre_1.setBounds(10, 141, 104, 37);
		panel_1_2.add(lblNombre_1);
		
		JLabel lblApellido_1 = new JLabel("1\u1D49\u02B3 Apellido: ");
		lblApellido_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido_1.setBounds(10, 173, 116, 37);
		panel_1_2.add(lblApellido_1);
		
		JLabel lblApellido2_1 = new JLabel("2\u00BA Apellido: ");
		lblApellido2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido2_1.setBounds(10, 202, 104, 37);
		panel_1_2.add(lblApellido2_1);
		
		JLabel lblTelefono_1 = new JLabel("Telefono:");
		lblTelefono_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono_1.setBounds(10, 68, 89, 37);
		panel_1_2.add(lblTelefono_1);
		
		JLabel lblEmail_1 = new JLabel("Email: ");
		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail_1.setBounds(10, 101, 89, 37);
		panel_1_2.add(lblEmail_1);
		
		JLabel lblNombreAsociado_1 = new JLabel("Profesi\u00F3n:\r\n");
		lblNombreAsociado_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreAsociado_1.setBounds(13, 270, 89, 37);
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
		
		txtTelfFam1 = new JTextField();
		txtTelfFam1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTelfFam1.setColumns(10);
		txtTelfFam1.setBounds(91, 73, 247, 27);
		panel_1_2.add(txtTelfFam1);
		
		txtMailFam1 = new JTextField();
		txtMailFam1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMailFam1.setColumns(10);
		txtMailFam1.setBounds(70, 103, 247, 27);
		panel_1_2.add(txtMailFam1);
		
		txtNombreFam1 = new JTextField();
		txtNombreFam1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombreFam1.setColumns(10);
		txtNombreFam1.setBounds(91, 146, 247, 27);
		panel_1_2.add(txtNombreFam1);
		
		txtApellidoFam1Pag = new JTextField();
		txtApellidoFam1Pag.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtApellidoFam1Pag.setColumns(10);
		txtApellidoFam1Pag.setBounds(116, 178, 247, 27);
		panel_1_2.add(txtApellidoFam1Pag);
		
		txtApellido2Fam1Pag = new JTextField();
		txtApellido2Fam1Pag.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtApellido2Fam1Pag.setColumns(10);
		txtApellido2Fam1Pag.setBounds(116, 207, 247, 27);
		panel_1_2.add(txtApellido2Fam1Pag);
		
		txtProfesionAsocfam1 = new JTextField();
		txtProfesionAsocfam1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtProfesionAsocfam1.setColumns(10);
		txtProfesionAsocfam1.setBounds(107, 275, 247, 27);
		panel_1_2.add(txtProfesionAsocfam1);
		
		JLabel lblNombreAsociado_1_1 = new JLabel("DNI:");
		lblNombreAsociado_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreAsociado_1_1.setBounds(37, 234, 62, 37);
		panel_1_2.add(lblNombreAsociado_1_1);
		
		txtDNIAsocFam1Pag = new JTextField();
		txtDNIAsocFam1Pag.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDNIAsocFam1Pag.setColumns(10);
		txtDNIAsocFam1Pag.setBounds(106, 239, 247, 27);
		panel_1_2.add(txtDNIAsocFam1Pag);
		
		JPanel panel_1_2_2 = new JPanel();
		panel_1_2_2.setLayout(null);
		panel_1_2_2.setBackground(new Color(51, 153, 255));
		panel_1_2_2.setBounds(962, 11, 466, 248);
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
		lblDatosUsuario_1_2.setBounds(109, 0, 323, 37);
		panel_1_2_2.add(lblDatosUsuario_1_2);
		
		txtTlffam2 = new JTextField();
		txtTlffam2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTlffam2.setColumns(10);
		txtTlffam2.setBounds(96, 39, 247, 27);
		panel_1_2_2.add(txtTlffam2);
		
		txtMailfam2 = new JTextField();
		txtMailfam2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMailfam2.setColumns(10);
		txtMailfam2.setBounds(66, 71, 247, 27);
		panel_1_2_2.add(txtMailfam2);
		
		txtNom2fam = new JTextField();
		txtNom2fam.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNom2fam.setColumns(10);
		txtNom2fam.setBounds(96, 114, 247, 27);
		panel_1_2_2.add(txtNom2fam);
		
		txtApellido1fam2 = new JTextField();
		txtApellido1fam2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtApellido1fam2.setColumns(10);
		txtApellido1fam2.setBounds(124, 146, 247, 27);
		panel_1_2_2.add(txtApellido1fam2);
		
		txtApellido2Fam2 = new JTextField();
		txtApellido2Fam2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtApellido2Fam2.setColumns(10);
		txtApellido2Fam2.setBounds(124, 175, 247, 27);
		panel_1_2_2.add(txtApellido2Fam2);
		
		JLabel lblApellido2_1_1_2 = new JLabel("Profesion:");
		lblApellido2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido2_1_1_2.setBounds(25, 206, 89, 37);
		panel_1_2_2.add(lblApellido2_1_1_2);
		
		txtProfAsocfam2 = new JTextField();
		txtProfAsocfam2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtProfAsocfam2.setColumns(10);
		txtProfAsocfam2.setBounds(124, 211, 247, 27);
		panel_1_2_2.add(txtProfAsocfam2);
		
		JPanel panel_1_2_2_1 = new JPanel();
		panel_1_2_2_1.setLayout(null);
		panel_1_2_2_1.setBackground(new Color(51, 153, 255));
		panel_1_2_2_1.setBounds(962, 272, 466, 602);
		contentPanel.add(panel_1_2_2_1);
		
		JLabel lblNombre_1_1_1 = new JLabel("Tiene amigos:");
		lblNombre_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre_1_1_1.setBounds(10, 247, 122, 37);
		panel_1_2_2_1.add(lblNombre_1_1_1);
		
		JLabel lblApellido2_1_1_1 = new JLabel("LOPD:");
		lblApellido2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblApellido2_1_1_1.setBounds(10, 554, 57, 37);
		panel_1_2_2_1.add(lblApellido2_1_1_1);
		
		JLabel lblDatosUsuario_1_2_1 = new JLabel("DATOS ADICIONALES");
		lblDatosUsuario_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDatosUsuario_1_2_1.setBounds(133, 0, 323, 37);
		panel_1_2_2_1.add(lblDatosUsuario_1_2_1);
		
		txtGenograma = new JTextArea();
		txtGenograma.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtGenograma.setColumns(10);
		txtGenograma.setBounds(10, 67, 446, 103);
		panel_1_2_2_1.add(txtGenograma);
		
		JLabel lblDiagnosticosAnteriores_1_1 = new JLabel("Genograma:");
		lblDiagnosticosAnteriores_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiagnosticosAnteriores_1_1.setBounds(10, 36, 122, 20);
		panel_1_2_2_1.add(lblDiagnosticosAnteriores_1_1);
		
		JComboBox cmbApoyosAdicional = new JComboBox();
		cmbApoyosAdicional.setModel(new DefaultComboBoxModel(new String[] {"", "Ordinario", "Ord. Apoyo PT y Al", "Preferentes sin Aula", "Preferentes con Aula"}));
		cmbApoyosAdicional.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbApoyosAdicional.setBounds(83, 207, 174, 29);
		panel_1_2_2_1.add(cmbApoyosAdicional);
		
		JLabel lblGenero_1_1 = new JLabel("Apoyos:");
		lblGenero_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGenero_1_1.setBounds(10, 211, 78, 20);
		panel_1_2_2_1.add(lblGenero_1_1);
		
		chckbxAcnecesidadEducativa_1 = new JCheckBox("ACNecesidad Educativa");
		chckbxAcnecesidadEducativa_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxAcnecesidadEducativa_1.setBackground(new Color(51, 153, 255));
		chckbxAcnecesidadEducativa_1.setBounds(63, 177, 174, 23);
		panel_1_2_2_1.add(chckbxAcnecesidadEducativa_1);
		
		checkAmigos = new JCheckBox("");
		checkAmigos.setBackground(new Color(51, 153, 255));
		checkAmigos.setBounds(138, 247, 36, 37);
		panel_1_2_2_1.add(checkAmigos);
		
		JLabel lblDiagnosticosAnteriores_1_1_1 = new JLabel("Observaciones:");
		lblDiagnosticosAnteriores_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiagnosticosAnteriores_1_1_1.setBounds(10, 291, 130, 20);
		panel_1_2_2_1.add(lblDiagnosticosAnteriores_1_1_1);
		
		txtObsAdicionales = new JTextArea();
		txtObsAdicionales.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtObsAdicionales.setColumns(10);
		txtObsAdicionales.setBounds(10, 311, 446, 103);
		panel_1_2_2_1.add(txtObsAdicionales);
		
		JLabel lblDiagnosticosAnteriores_1_1_1_1 = new JLabel("Principales dificultades referidas por la familia:");
		lblDiagnosticosAnteriores_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDiagnosticosAnteriores_1_1_1_1.setBounds(10, 425, 446, 20);
		panel_1_2_2_1.add(lblDiagnosticosAnteriores_1_1_1_1);
		
		txtPrincDiffam = new JTextArea();
		txtPrincDiffam.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPrincDiffam.setColumns(10);
		txtPrincDiffam.setBounds(10, 445, 446, 103);
		panel_1_2_2_1.add(txtPrincDiffam);
		
		checkLOPD = new JCheckBox("");
		checkLOPD.setBackground(new Color(51, 153, 255));
		checkLOPD.setBounds(63, 554, 36, 37);
		panel_1_2_2_1.add(checkLOPD);
		
		JPanel panel_1_2_2_2 = new JPanel();
		panel_1_2_2_2.setLayout(null);
		panel_1_2_2_2.setBackground(new Color(51, 153, 255));
		panel_1_2_2_2.setBounds(962, 887, 466, 94);
		contentPanel.add(panel_1_2_2_2);
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(new Color(51, 153, 255));
		btnConfirmar.setBounds(243, 31, 123, 34);
		panel_1_2_2_2.add(btnConfirmar);
		btnConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertarPeticionDiagnostico();
			
			}
		});
		btnConfirmar.setBorder(new RoundedBorder(20));
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnVolver = new JButton("Cancelar\r\n");
		btnVolver.setBounds(120, 31, 113, 34);
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
		
		
		setLocationRelativeTo(null);
	}
	
	private void autocompletar() {

		txtTelfFam1.setText(txtTlfUsu.getText());
		txtMailFam1.setText(txtMailUsu.getText());
		txtNombreFam1.setText(txtNombreUsu.getText());
		txtApellidoFam1Pag.setText(txt1ApellidoUsu.getText());
		txtApellido2Fam1Pag.setText(txt2ApellidoUsu.getText());
		txtDNIAsocFam1Pag.setText(txtDNIusu.getText());
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
	
	private void InsertarPeticionDiagnostico() {
		
		String AcnecesidadEducativa_1="";
		String Amigos ="";
		String LOPD ="";
		String Diagnostico ="";
		String AsistTerap ="";
			
			if(checkDiagnostico.isSelected() == true) {
				Diagnostico ="1";
			}else if(checkDiagnostico.isSelected() == false) {
				Diagnostico ="0";
			}
			
			if(chckbxAcnecesidadEducativa_1.isSelected() == true) {
				AcnecesidadEducativa_1 ="1";
			}else if(chckbxAcnecesidadEducativa_1.isSelected() == false) {
				AcnecesidadEducativa_1 ="0";
			}
			
			if(checkAmigos.isSelected() == true) {
				Amigos ="1";
			}else if(checkAmigos.isSelected() == false) {
				Amigos ="0";
			}
			if(checkVaTerap.isSelected() == true) {
				AsistTerap ="1";
			}else if(checkVaTerap.isSelected() == false) {
				AsistTerap ="0";
			}
			
			if(checkLOPD.isSelected() == true) {
				LOPD ="1";
			}else if(checkLOPD.isSelected() == false) {
				LOPD ="0";
			}
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sFecha1Entrevista = String.valueOf(sdf.format(f_1entrevista.getDate()));
		String sFechaNacimiento = String.valueOf(sdf.format(fNacimUsu.getDate()));
		String sFechaSolEval = String.valueOf(sdf.format(f_sol_eval_dep.getDate()));
		
		
		String sSelect = "INSERT INTO Usuario (id,nomb_usu," + 
				"apellido1_usu, apellido2_usu, dni_usu, f_nac_usu,f_pet_terap,tlf_usu," + 
				"email_usu,genero) VALUES (id,"+
				"'" + txtNombreUsu.getText() + "'," +
				"'" + txt1ApellidoUsu.getText() + "'," +
				"'" + txt2ApellidoUsu.getText() + "'," +
				"'" + txtDNIusu.getText() + "'," +
				"'" + sFechaNacimiento + "'," +
				"CURTIME() ,"+
				"'" + txtTlfUsu.getText() + "'," +
				"'" + txtMailUsu.getText() + "',"+
				"'" + cmbGenero.getSelectedItem().toString() + "')" ;
		
		
		String sSelect2 = "INSERT INTO P_Entrevista ( id_entre,id,f_entrevista,distrito,nom_centro,diagnostico,diagnostico_anterior,"
				+ "centro_diagnostico,profesional_diagnostico,grado_disc,f_sol_eval_dep,nivel_dep_usu,observaciones_dep,"
				+ "asist_terapia,obser_deriv_terapia,genograma,AC_nece_educ,amigos,observaciones,dific_ref_fam,LOPD) VALUES (id_entre,(SELECT MAX(id) FROM usuario),"+
		        		 "'" + sFecha1Entrevista + "'," +
		        		 "'" + txtDistrito.getText() + "'," +
		        		 "'" + txtNomCentro.getText()+ "'," +
		        		 "'" + Diagnostico + "'," +
		        		 "'" + txtDiagnosticosAnteriores.getText()+ "'," +
		        		 "'" + txtCentroDiagnostico.getText()+ "'," +
		        		 "'" + txtProfeDiagnostico.getText()+ "'," +
		        		 "'" + txtGradoDisc.getText()+ "'," +
		        		 "'" + sFechaSolEval + "'," +
		        		 "'" + txtGradoNivelDep.getText()+ "'," +
		        		 "'" + txtObservDep.getText()+ "'," +
		        		 "'" + AsistTerap + "'," +
		        		 "'" + txtObservDerivTerap.getText()+ "'," +
		        		 "'" + txtGenograma.getText()+ "'," +
		        		 "'" + AcnecesidadEducativa_1 + "'," +
		        		 "'" + Amigos + "'," +
		        		 "'" + txtObsAdicionales.getText()+ "'," +
		        		 "'" + txtPrincDiffam.getText()+ "'," +
		        		 "'" + LOPD +"')";
		
		
		
		String sSelect3 = "INSERT INTO AsociadoPagador (id_asoc,id_entre,id,nom_asoc_fam1_pag," + 
						 "apellido1_asoc_fam1_pag, apellido2_asoc_fam1_pag, dni_asoc_fam1_pag, profe_asoc_fam1_pag, movil1_asoc_fam1_pag," + 
						 "email_asoc_fam1_pag) VALUES (id_asoc,(SELECT MAX(id_entre) FROM P_Entrevista),(SELECT MAX(id) FROM Usuario),"+
						 "'" + txtNombreFam1.getText() + "'," +
					     "'" + txtApellidoFam1Pag.getText() + "'," +
					     "'" + txtApellido2Fam1Pag.getText() + "'," +
					     "'" + txtDNIAsocFam1Pag.getText() + "'," +
					     "'" + txtProfesionAsocfam1.getText() + "'," +
					     "'" + txtTelfFam1.getText() + "'," +
					     "'" + txtMailFam1.getText() + "')";
		
		
					     
		String sSelect4 = "INSERT INTO AsociadoFamiliar ( id_asoc_fam,id_entre,id,nom_asoc_fam2, apellido1_asoc_fam2," + 
						 "apellido2_asoc_fam2, profe_asoc_fam2, email_asoc_fam2, movil1_asoc_fam2) VALUES (id_asoc_fam,(SELECT MAX(id_entre) FROM P_Entrevista),(SELECT MAX(id) FROM Usuario),"+
						 "'" + txtNom2fam.getText() + "'," +
						 "'" + txtApellido1fam2.getText() + "'," +
						 "'" + txtApellido2Fam2.getText() + "'," +
						 "'" + txtProfAsocfam2.getText() + "'," +
						 "'" + txtMailfam2.getText() + "'," +
						 "'" + txtTlffam2.getText() + "')";
						 
		
		
		rsEntrevista  = ConnMySQL.EjecutarSQL(sSelect); 
		rsEntrevista  = ConnMySQL.EjecutarSQL(sSelect2);
		rsEntrevista  = ConnMySQL.EjecutarSQL(sSelect3); 
		rsEntrevista  = ConnMySQL.EjecutarSQL(sSelect4); 
		
		
		
	}
}
