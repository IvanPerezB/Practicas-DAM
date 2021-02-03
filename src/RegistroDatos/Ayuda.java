package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class Ayuda extends JDialog { // esto es en realidad la configuracion para conectarse a la base de datos, donde pondremos al contraseña y password ademas del nombre de la base de datos
	

	private final JPanel contentPanel = new JPanel();
	int xx, xy;
	private JTextField txtDireccionBBDD;
	private JTextField txtUsuarioBBDD;
	private JTextField txtPasswordBBDD;
	private JTextField txtNombreBBDD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Ayuda dialog = new Ayuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setUndecorated(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		
		 //setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Imagenes/logoAM.png")));
	 
		
		
		setBounds(100, 100, 1025, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1030, 997);
		contentPanel.add(panel);
		panel.setLayout(null);

		
				
				JLabel label = new JLabel("");
				label.setBounds(606, -62, 595, 604);
				panel.add(label);
				
						label.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
				
								xx = e.getX();
								xy = e.getY();
							}
						});
						label.addMouseMotionListener(new MouseMotionAdapter() {
							@Override
							public void mouseDragged(MouseEvent arg0) {

								int x = arg0.getXOnScreen();
								int y = arg0.getYOnScreen();
								Ayuda.this.setLocation(x - xx, y - xy);
							}
						});
						label.setVerticalAlignment(SwingConstants.TOP);
						label.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/fondo.png")));
						
						JLabel lblNewLabel_2 = new JLabel("Servidor de la Base de Datos:");
						lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_2.setBounds(34, 89, 381, 20);
						panel.add(lblNewLabel_2);
						
						txtDireccionBBDD = new JTextField();
						txtDireccionBBDD.setFont(new Font("Tahoma", Font.BOLD, 16));
						txtDireccionBBDD.setColumns(10);
						txtDireccionBBDD.setBounds(34, 120, 502, 30);
						panel.add(txtDireccionBBDD);
						
						txtUsuarioBBDD = new JTextField();
						txtUsuarioBBDD.setFont(new Font("Tahoma", Font.BOLD, 16));
						txtUsuarioBBDD.setColumns(10);
						txtUsuarioBBDD.setBounds(34, 325, 502, 30);
						panel.add(txtUsuarioBBDD);
						
						JLabel lblNewLabel_2_1 = new JLabel("Usuario Base de Datos:");
						lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_2_1.setBounds(34, 276, 381, 20);
						panel.add(lblNewLabel_2_1);
						
						JLabel lblNewLabel_2_1_1 = new JLabel("Contrase\u00F1a Base de Datos:");
						lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_2_1_1.setBounds(34, 380, 381, 20);
						panel.add(lblNewLabel_2_1_1);
						
						txtPasswordBBDD = new JTextField();
						txtPasswordBBDD.setFont(new Font("Tahoma", Font.BOLD, 16));
						txtPasswordBBDD.setColumns(10);
						txtPasswordBBDD.setBounds(34, 429, 502, 30);
						panel.add(txtPasswordBBDD);
						
						JLabel lblNewLabel_2_2 = new JLabel("Opciones avanzadas de la aplicaci\u00F3n");
						lblNewLabel_2_2.setForeground(Color.RED);
						lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 25));
						lblNewLabel_2_2.setBounds(34, 11, 523, 50);
						panel.add(lblNewLabel_2_2);
						
						Border border = new LineBorder(Color.BLACK, 3);
						
						JButton btnConfirmar = new JButton("Confirmar");
						btnConfirmar.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								//CambiarDireccion();
								CambiarConexion();
							}
						});
						btnConfirmar.setForeground(Color.BLACK);
						btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
						btnConfirmar.setBackground(Color.RED);
						btnConfirmar.setBorder(border);
						btnConfirmar.setBounds(167, 480, 192, 47);
						panel.add(btnConfirmar);
						
						JLabel lblNewLabel_2_1_2 = new JLabel("Nombre Base de Datos:");
						lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
						lblNewLabel_2_1_2.setBounds(34, 186, 381, 20);
						panel.add(lblNewLabel_2_1_2);
						
						txtNombreBBDD = new JTextField();
						txtNombreBBDD.setFont(new Font("Tahoma", Font.BOLD, 16));
						txtNombreBBDD.setColumns(10);
						txtNombreBBDD.setBounds(34, 235, 502, 30);
						panel.add(txtNombreBBDD);
						


		
		
		
		setLocationRelativeTo(null);

	
		
		
		
	}
	private void CambiarConexion() {
		File file = new File("conexionBBDDaspergermadrid.txt");
		try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            String contents = txtDireccionBBDD.getText().toString() + 
                System.getProperty("line.separator") + txtNombreBBDD.getText().toString() +
                System.getProperty("line.separator") + txtUsuarioBBDD.getText().toString()+
                System.getProperty("line.separator") + txtPasswordBBDD.getText().toString();

            writer.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
		MetodosBBDD.ConnMySQL.Conectar();
		
	

		
		
		
	}
	
	private void CambiarDireccion() {
		MetodosBBDD.ConnMySQL.url= txtDireccionBBDD.getText().toString();
		MetodosBBDD.ConnMySQL.nombrebbdd = txtNombreBBDD.getText().toString();
		MetodosBBDD.ConnMySQL.usuario = txtUsuarioBBDD.getText().toString();
		MetodosBBDD.ConnMySQL.password = txtPasswordBBDD.getText().toString();
		
		System.out.println(MetodosBBDD.ConnMySQL.url);
		
	}
	

}
