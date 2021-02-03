package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import MetodosBBDD.ConnMySQL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;


public class DemandaDiagnostico extends JDialog { // esta clase sirve para cuando quermos solicitar un diagnostico, que esto va antes de primera entrevista y hacerse socio

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCentroEducativo;
	private JTextField txtTelefono;
	private JTextField txtMail;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtApellido2;
	private JTextField txtTelefonoPag;
	private JTextField txtMailPag;
	private JTextField txtTelefonoFijoPag;
	private JTextField txtNombrePag;
	private JTextField txtApellidoPag;
	private JTextField txtApellido2Pag;
	private JTextField txtRelacion;
	private JTextField txtCP;
	private JDateChooser fNacimiento;
	private JDateChooser fPeticion;
	private JComboBox cmbGenero;
	private JTextArea txtObservaciones;
	boolean rsDiagnostico ;
	private JTextField txtDNI;
	private JTextField txtDNIPag;
	private JTextField txtProfesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DemandaDiagnostico dialog = new DemandaDiagnostico();
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
	public DemandaDiagnostico() { // clase para demandar un diagnostico, esto se tiene que hacer antes de una primera entrevista o de hacerse socio
		setBounds(100, 100, 1136, 582);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBackground(new Color(51, 153, 255));
			panel_1.setBounds(10, 143, 466, 388);
			contentPanel.add(panel_1);
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNombre.setBounds(10, 102, 104, 37);
				panel_1.add(lblNombre);
			}
			{
				JLabel lblApellido = new JLabel("1\u1D49\u02B3 Apellido: ");
				lblApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblApellido.setBounds(10, 133, 117, 37);
				panel_1.add(lblApellido);
			}
			{
				JLabel lblApellido2 = new JLabel("2\u00BA Apellido: ");
				lblApellido2.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblApellido2.setBounds(10, 164, 104, 37);
				panel_1.add(lblApellido2);
			}
			{
				JLabel lblTelefono = new JLabel("Telefono:");
				lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblTelefono.setBounds(10, 31, 89, 37);
				panel_1.add(lblTelefono);
			}
			{
				JLabel lblEmail = new JLabel("Email: ");
				lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblEmail.setBounds(10, 69, 89, 37);
				panel_1.add(lblEmail);
			}
			{
				JLabel lblDatosUsuario = new JLabel("DATOS USUARIO");
				lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblDatosUsuario.setBounds(145, 0, 179, 37);
				panel_1.add(lblDatosUsuario);
			}
			{
				fNacimiento = new JDateChooser();
				fNacimiento.setBounds(183, 206, 152, 29);
				panel_1.add(fNacimiento);
			}
			{
				JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
				lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblFechaDeNacimiento.setBounds(10, 206, 179, 29);
				panel_1.add(lblFechaDeNacimiento);
			}
			{
				JLabel lblGenero_1 = new JLabel("Genero:");
				lblGenero_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblGenero_1.setBounds(10, 246, 78, 20);
				panel_1.add(lblGenero_1);
			}
			{
				cmbGenero = new JComboBox();
				cmbGenero.setModel(new DefaultComboBoxModel(new String[] {"", "", "FEMENINO", "MASCULINO", "INTERGENERO"}));
				cmbGenero.setFont(new Font("Tahoma", Font.BOLD, 14));
				cmbGenero.setBounds(85, 242, 152, 29);
				panel_1.add(cmbGenero);
			}
			{
				JLabel lblNombreDelCentro_1 = new JLabel("Nombre del Centro Educativo:");
				lblNombreDelCentro_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNombreDelCentro_1.setBounds(10, 311, 252, 33);
				panel_1.add(lblNombreDelCentro_1);
			}
			{
				txtCentroEducativo = new JTextField();
				txtCentroEducativo.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtCentroEducativo.setColumns(10);
				txtCentroEducativo.setBounds(10, 346, 446, 29);
				panel_1.add(txtCentroEducativo);
			}
			
			txtTelefono = new JTextField();
			txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtTelefono.setBounds(98, 38, 208, 27);
			panel_1.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			txtMail = new JTextField();
			txtMail.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtMail.setColumns(10);
			txtMail.setBounds(73, 74, 208, 27);
			panel_1.add(txtMail);
			
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtNombre.setColumns(10);
			txtNombre.setBounds(84, 107, 208, 27);
			panel_1.add(txtNombre);
			
			txtApellido = new JTextField();
			txtApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtApellido.setColumns(10);
			txtApellido.setBounds(116, 138, 208, 27);
			panel_1.add(txtApellido);
			
			txtApellido2 = new JTextField();
			txtApellido2.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtApellido2.setColumns(10);
			txtApellido2.setBounds(116, 174, 208, 27);
			panel_1.add(txtApellido2);
			
			JLabel lblDni = new JLabel("DNI:");
			lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblDni.setBounds(10, 279, 71, 37);
			panel_1.add(lblDni);
			
			txtDNI = new JTextField();
			txtDNI.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtDNI.setColumns(10);
			txtDNI.setBounds(85, 284, 208, 27);
			panel_1.add(txtDNI);
		}
		{
			JPanel panel_1_2 = new JPanel();
			panel_1_2.setLayout(null);
			panel_1_2.setBackground(new Color(51, 153, 255));
			panel_1_2.setBounds(486, 11, 624, 378);
			contentPanel.add(panel_1_2);
			{
				JLabel lblNombre_1 = new JLabel("Nombre:");
				lblNombre_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNombre_1.setBounds(10, 176, 104, 37);
				panel_1_2.add(lblNombre_1);
			}
			{
				JLabel lblApellido_1 = new JLabel("1\u1D49\u02B3 Apellido: ");
				lblApellido_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblApellido_1.setBounds(10, 208, 116, 37);
				panel_1_2.add(lblApellido_1);
			}
			{
				JLabel lblApellido2_1 = new JLabel("2\u00BA Apellido: ");
				lblApellido2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblApellido2_1.setBounds(10, 237, 104, 37);
				panel_1_2.add(lblApellido2_1);
			}
			{
				JLabel lblTelefono_1 = new JLabel("Telefono:");
				lblTelefono_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblTelefono_1.setBounds(10, 68, 89, 37);
				panel_1_2.add(lblTelefono_1);
			}
			{
				JLabel lblEmail_1 = new JLabel("Email: ");
				lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblEmail_1.setBounds(10, 136, 89, 37);
				panel_1_2.add(lblEmail_1);
			}
			{
				JLabel lblNombreAsociado_1 = new JLabel("Relaci\u00F3n:\r\n");
				lblNombreAsociado_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNombreAsociado_1.setBounds(10, 274, 89, 37);
				panel_1_2.add(lblNombreAsociado_1);
			}
			{
				JLabel lblDatosUsuario_1 = new JLabel("DATOS 1\u1D49\u02B3 FAMILIAR (PAGADOR)");
				lblDatosUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblDatosUsuario_1.setBounds(164, 0, 323, 37);
				panel_1_2.add(lblDatosUsuario_1);
			}
			{
				JLabel lblTelefono_1_2 = new JLabel("El usuario es el pagador (Autocompletar)");
				lblTelefono_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblTelefono_1_2.setBounds(10, 36, 341, 37);
				panel_1_2.add(lblTelefono_1_2);
			}
			{
				JButton btnAutocompletar = new JButton("Autocompletar");
				btnAutocompletar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						autocompletar();
					}
				});
				btnAutocompletar.setBounds(367, 36, 170, 37);
				panel_1_2.add(btnAutocompletar);
			}
			{
				JLabel lblTelefono_1 = new JLabel("Telefono fijo:");
				lblTelefono_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblTelefono_1.setBounds(10, 101, 116, 37);
				panel_1_2.add(lblTelefono_1);
			}
			{
				JLabel lblNombreAsociado_1 = new JLabel("CP:\r\n");
				lblNombreAsociado_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblNombreAsociado_1.setBounds(10, 322, 33, 37);
				panel_1_2.add(lblNombreAsociado_1);
			}
			
			txtTelefonoPag = new JTextField();
			txtTelefonoPag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtTelefonoPag.setColumns(10);
			txtTelefonoPag.setBounds(94, 73, 208, 27);
			panel_1_2.add(txtTelefonoPag);
			
			txtMailPag = new JTextField();
			txtMailPag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtMailPag.setColumns(10);
			txtMailPag.setBounds(73, 141, 363, 27);
			panel_1_2.add(txtMailPag);
			
			txtTelefonoFijoPag = new JTextField();
			txtTelefonoFijoPag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtTelefonoFijoPag.setColumns(10);
			txtTelefonoFijoPag.setBounds(123, 106, 208, 27);
			panel_1_2.add(txtTelefonoFijoPag);
			
			txtNombrePag = new JTextField();
			txtNombrePag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtNombrePag.setColumns(10);
			txtNombrePag.setBounds(91, 180, 208, 27);
			panel_1_2.add(txtNombrePag);
			
			txtApellidoPag = new JTextField();
			txtApellidoPag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtApellidoPag.setColumns(10);
			txtApellidoPag.setBounds(123, 211, 208, 27);
			panel_1_2.add(txtApellidoPag);
			
			txtApellido2Pag = new JTextField();
			txtApellido2Pag.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtApellido2Pag.setColumns(10);
			txtApellido2Pag.setBounds(123, 247, 208, 27);
			panel_1_2.add(txtApellido2Pag);
			
			txtRelacion = new JTextField();
			txtRelacion.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtRelacion.setColumns(10);
			txtRelacion.setBounds(94, 279, 208, 27);
			panel_1_2.add(txtRelacion);
			
			txtCP = new JTextField();
			txtCP.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtCP.setColumns(10);
			txtCP.setBounds(53, 327, 208, 27);
			panel_1_2.add(txtCP);
			{
				JLabel lblDni = new JLabel("DNI:");
				lblDni.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblDni.setBounds(331, 274, 71, 37);
				panel_1_2.add(lblDni);
			}
			{
				txtDNIPag = new JTextField();
				txtDNIPag.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtDNIPag.setColumns(10);
				txtDNIPag.setBounds(388, 279, 208, 27);
				panel_1_2.add(txtDNIPag);
			}
			{
				JLabel lblProfesion = new JLabel("Profesion");
				lblProfesion.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblProfesion.setBounds(280, 319, 89, 37);
				panel_1_2.add(lblProfesion);
			}
			{
				txtProfesion = new JTextField();
				txtProfesion.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtProfesion.setColumns(10);
				txtProfesion.setBounds(388, 327, 208, 27);
				panel_1_2.add(txtProfesion);
			}
		}
		{
			JPanel panel_1_1 = new JPanel();
			panel_1_1.setLayout(null);
			panel_1_1.setBackground(new Color(51, 153, 255));
			panel_1_1.setBounds(10, 11, 466, 119);
			contentPanel.add(panel_1_1);
			{
				JLabel lblRecogidaDeDatos = new JLabel("RECOGIDA DE DATOS");
				lblRecogidaDeDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblRecogidaDeDatos.setBounds(25, 31, 167, 20);
				panel_1_1.add(lblRecogidaDeDatos);
			}
			{
				JLabel lblFechaPeticinDe = new JLabel("FECHA PETICI\u00D3N DE DIAGNOSTICO");
				lblFechaPeticinDe.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblFechaPeticinDe.setBounds(25, 74, 269, 20);
				panel_1_1.add(lblFechaPeticinDe);
			}
			{
				fPeticion = new JDateChooser();
				fPeticion.setBounds(293, 74, 149, 27);
				panel_1_1.add(fPeticion);
			}
			{
				JComboBox cmbRecogida = new JComboBox();
				cmbRecogida.setModel(new DefaultComboBoxModel(new String[] {"", "Primera Entrevista", "Llamada telefonica", "Email", "Demanda de socio", "Formulario", "En persona"}));
				cmbRecogida.setFont(new Font("Tahoma", Font.BOLD, 14));
				cmbRecogida.setBounds(238, 28, 204, 27);
				panel_1_1.add(cmbRecogida);
			}
		}
		{
			JPanel panel_1_1 = new JPanel();
			panel_1_1.setLayout(null);
			panel_1_1.setBackground(new Color(51, 153, 255));
			panel_1_1.setBounds(485, 397, 383, 134);
			contentPanel.add(panel_1_1);
			{
				JLabel lblObservaciones_1 = new JLabel("Observaciones:");
				lblObservaciones_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblObservaciones_1.setBounds(10, 0, 131, 38);
				panel_1_1.add(lblObservaciones_1);
			}
			{
				txtObservaciones = new JTextArea();
				txtObservaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtObservaciones.setColumns(10);
				txtObservaciones.setBounds(10, 36, 360, 87);
				panel_1_1.add(txtObservaciones);
			}
		}
		{
			JPanel panel_1_1 = new JPanel();
			panel_1_1.setLayout(null);
			panel_1_1.setBackground(new Color(51, 153, 255));
			panel_1_1.setBounds(878, 397, 232, 134);
			contentPanel.add(panel_1_1);
			
			
			JButton btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setBounds(40, 25, 149, 34);
			panel_1_1.add(btnConfirmar);
			btnConfirmar.setBackground(new Color(51, 153, 255));
			btnConfirmar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Principal().setVisible(true);
					InsertarPeticionDiagnostico();
					dispose();
				}
			});
			btnConfirmar.setBorder(new RoundedBorder(20));
			btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 16));
			
			JButton btnVolver = new JButton("Volver");
			btnVolver.setBounds(40, 78, 149, 34);
			panel_1_1.add(btnVolver);
			btnVolver.setBackground(new Color(51, 153, 255));
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					dispose();
				}
			});
			btnVolver.setBorder(new RoundedBorder(20));
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		
		
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
	private void autocompletar() {

        txtTelefonoPag.setText(txtTelefono.getText());
        txtMailPag.setText(txtMail.getText());
        txtNombrePag.setText(txtNombre.getText());
        txtApellidoPag.setText(txtApellido.getText());
        txtApellido2Pag.setText(txtApellido2.getText());
        txtDNIPag.setText(txtDNI.getText());
    }
	
	private void InsertarPeticionDiagnostico() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sFechaPeticion = String.valueOf(sdf.format(fPeticion.getDate()));
		String sFechaNacimiento = String.valueOf(sdf.format(fNacimiento.getDate()));
		
		
		String sSelect = "INSERT INTO diagnostico ( cp,nom_centro,obs_pet_diag,f_pet_diag) VALUES (" +
		        		 "'" + txtCP.getText() + "'," +
		        		 "'" + txtCentroEducativo.getText() + "'," +
		        		 "'" + txtObservaciones.getText()+ "'," +
		        		 "'" + sFechaPeticion +"')";
		
		String sSelect2 = "INSERT INTO Usuario (nomb_usu," + 
						"apellido1_usu, apellido2_usu, dni_usu, f_nac_usu, tlf_usu," + 
						"email_usu,genero) VALUES (" +
						"'" + txtNombre.getText() + "'," +
						"'" + txtApellido.getText() + "'," +
						"'" + txtApellido2.getText() + "'," +
						"'" + txtDNI.getText() + "'," +
						"'" + sFechaNacimiento+ "'," +
						"'" + txtTelefono.getText() + "'," +
						"'" + txtMail.getText() + "',"+
						"'" + cmbGenero.getSelectedItem().toString() + "')" ;
		
		String sSelect3 = "INSERT INTO AsociadoPagador ( nom_asoc_fam1_pag," + 
						 "apellido1_asoc_fam1_pag, apellido2_asoc_fam1_pag, dni_asoc_fam1_pag, profe_asoc_fam1_pag, movil1_asoc_fam1_pag," + 
						 "movil2_asoc_fam1_pag, email_asoc_fam1_pag, relac_asoc_fam1_pag) VALUES ("+
						 "'" + txtNombrePag.getText() + "'," +
					     "'" + txtApellidoPag.getText() + "'," +
					     "'" + txtApellido2Pag.getText() + "'," +
					     "'" + txtDNIPag.getText() + "'," +
					     "'" + txtProfesion.getText() + "'," +
					     "'" + txtTelefonoPag.getText() + "'," +
					     "'" + txtTelefonoFijoPag.getText() + "'," +
					     "'" + txtMailPag.getText() + "'," +
					     "'" + txtRelacion.getText() + "')";
		
		
		rsDiagnostico  = ConnMySQL.EjecutarSQL(sSelect);
		rsDiagnostico  = ConnMySQL.EjecutarSQL(sSelect2); 
		rsDiagnostico  = ConnMySQL.EjecutarSQL(sSelect3); 
		
		
		
	}
}
