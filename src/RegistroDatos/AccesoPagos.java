package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MetodosBBDD.ConnMySQL;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccesoPagos extends JDialog { // clase en la que habra que poner una contraseņa para acceder a la clase Pagos

	private final JPanel contentPanel = new JPanel();
	private JPasswordField txtPassword;
	private String password = "cocaina";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccesoPagos dialog = new AccesoPagos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccesoPagos() {
		setTitle("Contrase\u00F1a Pagos");
		setBounds(100, 100, 414, 261);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 255, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPassword = new JLabel("CONTRASE\u00D1A");
		lblPassword.setForeground(new Color(51, 153, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(129, 37, 139, 36);
		contentPanel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// metodo para entrar
					confirmarPagos();
					
				}
			}
		});
		txtPassword.setBounds(57, 103, 283, 36);
		contentPanel.add(txtPassword);
		
		Button button = new Button("Entrar");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmarPagos();
				
			}
		});
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// metodo para entrar
					confirmarPagos();
					
				}
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBackground(new Color(51, 153, 255));
		button.setBounds(57, 158, 283, 36);
		contentPanel.add(button);
		
		
		setLocationRelativeTo(null);
	}
	private void confirmarPagos() { // confirmamos los pagos con los insert que pusimos en la base de datos con sus respectivas contraseņas

		
		String sentencia = "SELECT * FROM Login WHERE user=? AND password=? ";

		System.out.println(sentencia);

		try {

			ConnMySQL.ConfirmarPagos(sentencia, "pagos", txtPassword.getText());
			dispose();

		} catch (Exception e) {

			System.out.println("Error en el usuario o la contrasena");
		}
	}
}
