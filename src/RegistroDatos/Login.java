package RegistroDatos;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import MetodosBBDD.ConnMySQL;
import javax.swing.JButton;


/*
Esta clase es la primera que se abre al ejecutar el programa.
Es un login en el que, de forma segura, se pone un usuario y contraseña (está encriptada)



IMPORTANTE: esta ventana y la de principal no tienen la parte superior para cerrar y minimizar, tiene una "X" artificial hecha por nosotros. Para mover la ventana en caso de ser necesario se hace manteniendo pulsado
en la imagen con el logo de la asociación. Es un recurso visual pero como podeis ver queda muy resultón. (No deberia daros problemas ya que de aquí no hay que tocar nada)
* 
*/

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;

	int xx, xy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					// quitamos los bordes
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------------

	public Login() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 346, 490);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("aspergermadrid.org");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setForeground(new Color(51, 153, 255));
		lblNewLabel.setBounds(39, 293, 247, 27);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("");
		// hacemos que la pestaña se pueda mover manteniendo el click sobre la imagen
		// del taller
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
				Login.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(0, 0, 420, 294);
		label.setVerticalAlignment(SwingConstants.TOP);
		/*
		ImageIcon imageIcon = new ImageIcon("/Imagenes/descarga.png");
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);  
		 */
		label.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/logoAM.png")));
		
		panel.add(label);

		JLabel lblWeGotYou = new JLabel("91 786 27 28 / 619 877 626");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(51, 153, 255));
		lblWeGotYou.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWeGotYou.setBounds(53, 331, 231, 27);
		panel.add(lblWeGotYou);

		Button button = new Button("Entrar");
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//metodo para entrar
				confirmarUsuario();
				
			}
		});
		button.setForeground(Color.BLACK);
		button.setBackground(new Color(51, 153, 255));
		button.setBounds(395, 301, 283, 36);
		contentPane.add(button);

		txtUser = new JTextField();
		txtUser.setBounds(395, 140, 283, 36);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		JLabel lblUsername = new JLabel("USUARIO");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setForeground(new Color(51, 153, 255));
		lblUsername.setBounds(395, 115, 128, 27);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("CONTRASE\u00D1A");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setForeground(new Color(51, 153, 255));
		lblPassword.setBounds(395, 204, 128, 27);
		contentPane.add(lblPassword);

		txtPassword = new JPasswordField();
		// Este key listener es para ejecutar el metodo de entrar al pulsar la tecla
		// enter
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// metodo para entrar
					confirmarUsuario();
					
				}
			}
		});
		txtPassword.setBounds(395, 229, 283, 36);
		contentPane.add(txtPassword);

		// La "x" para cerrar
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);
		
		JButton btnConfig = new JButton("Configuracion");
		btnConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Ayuda().setVisible(true);
			}
		});
		btnConfig.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConfig.setBorder(new RoundedBorder(20));
		btnConfig.setBackground(new Color(102, 204, 51));
		btnConfig.setBounds(546, 382, 153, 34);
		contentPane.add(btnConfig);
		
		
		
		setLocationRelativeTo(null);
	}

	// -----------------------------------------------------------------------------------------------------------------------


	private void confirmarUsuario() {

		String sentencia = "SELECT * FROM Login WHERE user=? AND password=? ";

		System.out.println(sentencia);

		try {

			ConnMySQL.ConfirmarLogin(sentencia, txtUser.getText(), txtPassword.getText());
			dispose();

		} catch (Exception e) {

			System.out.println("Error en el usuario o la contrasena");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------
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

}
