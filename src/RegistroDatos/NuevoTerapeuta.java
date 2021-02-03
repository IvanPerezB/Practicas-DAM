package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//import org.omg.PortableServer.ID_UNIQUENESS_POLICY_ID;

import MetodosBBDD.ConnMySQL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class NuevoTerapeuta extends JDialog { //Esta clase sirve para crear un nuevo terapeuta

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombreTerapeuta;
	private JTextField txtTelefonoTerapeuta;
	private JTextField txtApellido;
	private int ID_UNIQUENESS_POLICY_ID;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoTerapeuta dialog = new NuevoTerapeuta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoTerapeuta() {
		
		setTitle("Nuevo Terapeuta");
		setBounds(100, 100, 664, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Border border = new LineBorder(Color.black, 3);
		
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					GuardarTerapeuta();
					new Principal().setVisible(true);
					dispose();
				}
			});
			okButton.setForeground(new Color(0, 0, 0));
			okButton.setBounds(449, 350, 66, 45);
			contentPanel.add(okButton);
			okButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			okButton.setBackground(new Color(51, 255, 102));
			okButton.setActionCommand("OK");
			okButton.setBorder(border);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setForeground(new Color(0, 0, 0));
			cancelButton.setBounds(548, 350, 66, 45);
			contentPanel.add(cancelButton);
			cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
			cancelButton.setBackground(new Color(255, 0, 51));
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
			cancelButton.setBorder(border);
		}
		
		txtNombreTerapeuta = new JTextField();
		txtNombreTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombreTerapeuta.setBounds(226, 48, 219, 33);
		contentPanel.add(txtNombreTerapeuta);
		txtNombreTerapeuta.setColumns(10);
		
		JLabel lblNombreTerapeuta = new JLabel("Nombre Terapeuta");
		lblNombreTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreTerapeuta.setBounds(61, 48, 155, 33);
		contentPanel.add(lblNombreTerapeuta);
		
		JLabel lblTelefonoTerapeuta = new JLabel("Telefono Terapeuta");
		lblTelefonoTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefonoTerapeuta.setBounds(61, 198, 155, 33);
		contentPanel.add(lblTelefonoTerapeuta);
		
		txtTelefonoTerapeuta = new JTextField();
		txtTelefonoTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTelefonoTerapeuta.setColumns(10);
		txtTelefonoTerapeuta.setBounds(226, 198, 219, 33);
		contentPanel.add(txtTelefonoTerapeuta);
		
		JLabel lblApellido = new JLabel("Apellido ");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellido.setBounds(61, 136, 133, 16);
		contentPanel.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(226, 123, 219, 33);
		contentPanel.add(txtApellido);
		txtApellido.setColumns(10);
		
		
		setLocationRelativeTo(null);
	}
	public void GuardarTerapeuta() { //Este método sirve para insertar los datos del terapeuta en la base de datos
	
		String sSelect ="INSERT INTO Terapeuta (id_terapeuta, nombre_terape, apellido1_terape, telefono_terape) VALUES (id_terapeuta," +	
				      				   "'" + txtNombreTerapeuta.getText() + "'," +
				      				   "'" + txtApellido.getText() + "'," +
				      				   "'" + txtTelefonoTerapeuta.getText() + "')"  ;
		System.out.println(sSelect);
        ConnMySQL.EjecutarSQL(sSelect);
		
	}
}