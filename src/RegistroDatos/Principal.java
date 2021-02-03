package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	// Variables
	private JTextField textField;
	private JPasswordField passwordField;

	int xx, xy;

	private JPanel contentPane;
	private ResultSet rsReparaciones = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					// frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	// Metodo principal
	public Principal() {

		/*
		 * 1º entrevista demanda servicios baja asociacion registro servicios demanda
		 * diagnostico socios
		 * 
		 */

		setUndecorated(true);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 780);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 402, 997);
		contentPane.add(panel);
		panel.setLayout(null);
		// -----------------------------------------------------------------------------------------------------------------------
		// -----------------------------------------------------------------------------------------------------------------------

		// -----------------------------------
		// Etiquetas-JLabel
		// -----------------------------------

		// Es la imagen del lateral izquierdo. Al mantener el clic sobre ella, se mueve
		// toda la ventana de la aplicación.
		
		JLabel label = new JLabel("");

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
				Principal.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(-128, 40, 595, 689);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Login.class.getResource("")));
		panel.add(label);

		JLabel lblNewLabel = new JLabel("aspergermadrid.org");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(51, 153, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(62, 700, 247, 27);
		panel.add(lblNewLabel);

		JLabel lblWeGotYou = new JLabel("91 786 27 28 / 619 877 626");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(51, 153, 255));
		lblWeGotYou.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWeGotYou.setBounds(78, 738, 231, 27);
		panel.add(lblWeGotYou);

		Border border = new LineBorder(Color.BLACK, 3);

		// Boton Buscar
		JButton btnConfig = new JButton("Buscar");
		btnConfig.setBounds(1126, 720, 153, 34);
		contentPane.add(btnConfig);
		btnConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Ayuda().setVisible(true);
				
			}
		});
		btnConfig.setBackground(new Color(51, 153, 255));
		btnConfig.setBorder(new RoundedBorder(20));
		btnConfig.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConfig.setText("Configuracion");

		// -----------------------------------
		// Botones-JButton
		// -----------------------------------

	
		JButton btnPrimeraEntrevista = new JButton("1\u00AA Entrevista");
		btnPrimeraEntrevista.setBounds(583, 152, 244, 47);
		contentPane.add(btnPrimeraEntrevista);
		btnPrimeraEntrevista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				new PrimeraEntrevista().setVisible(true);
				

			}
		});
		btnPrimeraEntrevista.setBackground(new Color(51, 255, 102));
		btnPrimeraEntrevista.setForeground(Color.BLACK);
		btnPrimeraEntrevista.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrimeraEntrevista.setBorder(border);

		
		JButton btnBajaAsociacion = new JButton("Baja Asociaci\u00F3n");
		btnBajaAsociacion.setBounds(583, 258, 244, 47);
		contentPane.add(btnBajaAsociacion);
		btnBajaAsociacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//new Baja().setUndecorated(true);
				new DarDeBaja().setVisible(true);
				
			}
		});
		btnBajaAsociacion.setForeground(Color.BLACK);
		btnBajaAsociacion.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBajaAsociacion.setBackground(new Color(51, 255, 102));
		btnBajaAsociacion.setBorder(border);

		JButton btnSocios = new JButton("Socios");
		btnSocios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Socio().setVisible(true);
				
			}
		});
		btnSocios.setForeground(Color.BLACK);
		btnSocios.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSocios.setBackground(new Color(51, 255, 102));
		btnSocios.setBounds(955, 258, 244, 47);
		btnSocios.setBorder(border);
		contentPane.add(btnSocios);

		JButton btnDemandaDiagnostico = new JButton("Demanda Diagnostico");
		btnDemandaDiagnostico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new DemandaDiagnostico().setVisible(true);
				
			}
		});
		btnDemandaDiagnostico.setForeground(Color.BLACK);
		btnDemandaDiagnostico.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDemandaDiagnostico.setBackground(new Color(51, 255, 102));
		btnDemandaDiagnostico.setBounds(955, 152, 244, 47);
		btnDemandaDiagnostico.setBorder(border);
		contentPane.add(btnDemandaDiagnostico);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 934, 27);
		contentPane.add(panel_1);
		JLabel label2 = new JLabel("");

		label2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		label2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				Principal.this.setLocation(x - xx, y - xy);
			}
		});
		label2.setBounds(-96, 0, 595, 689);
		label2.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/fondo.png")));
		panel_1.add(label2);
		
		JLabel lblNewLabel_1 = new JLabel("aspergermadrid.org");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(51, 153, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(62, 700, 247, 27);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblWeGotYou_1 = new JLabel("91 786 27 28 / 619 877 626");
		lblWeGotYou_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou_1.setForeground(new Color(51, 153, 255));
		lblWeGotYou_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWeGotYou_1.setBounds(78, 738, 231, 27);
		panel_1.add(lblWeGotYou_1);
		
				// Etiqueta X (Para cerrar la ventana de la aplicacion)
				JLabel lbl_close = new JLabel("X");
				lbl_close.setBounds(868, 0, 37, 27);
				panel_1.add(lbl_close);
				lbl_close.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {

						System.exit(0);
					}
				});
				lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
				lbl_close.setForeground(new Color(241, 57, 83));
				lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				JButton btnAvanzado = new JButton("Nuevo Servicio/T\u00E9cnico");
				btnAvanzado.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new ElegirNuevo().setVisible(true);
						
					}
				});
				btnAvanzado.setForeground(Color.BLACK);
				btnAvanzado.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnAvanzado.setBackground(new Color(51, 255, 102));
				btnAvanzado.setBounds(955, 361, 244, 47);
				btnAvanzado.setBorder(border);
				contentPane.add(btnAvanzado);
				
				JButton btnPagos = new JButton("Pagos");
				
				btnPagos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new AccesoPagos().setVisible(true);
						
						
					}
				});
				btnPagos.setForeground(Color.BLACK);
				btnPagos.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnPagos.setBackground(new Color(51, 255, 102));
				btnPagos.setBounds(583, 361, 244, 47);
				btnPagos.setBorder(border);
				contentPane.add(btnPagos);
				
				JButton btnEliminar = new JButton("Eliminar\r\n");
				btnEliminar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new AccesoEliminar().setVisible(true);
						
					}
				});
				btnEliminar.setForeground(Color.BLACK);
				btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnEliminar.setBackground(new Color(51, 255, 102));
				btnEliminar.setBounds(955, 453, 244, 47);
				btnEliminar.setBorder(border);
				contentPane.add(btnEliminar);
				
				JButton btnListasDeEspera = new JButton("Listas de espera");
				btnListasDeEspera.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						new ListasEspera().setVisible(true);
						
					}
				});
				btnListasDeEspera.setForeground(Color.BLACK);
				btnListasDeEspera.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnListasDeEspera.setBackground(new Color(51, 255, 102));
				btnListasDeEspera.setBounds(583, 459, 244, 47);
				btnListasDeEspera.setBorder(border);
				contentPane.add(btnListasDeEspera);
				
				JButton btnApuntar = new JButton("Apuntar");
				btnApuntar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						new Tabla_Usuarios().setVisible(true);
						
					}
				});
				btnApuntar.setForeground(Color.BLACK);
				btnApuntar.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnApuntar.setBackground(new Color(51, 255, 102));
				btnApuntar.setBounds(583, 551, 244, 47);
				btnApuntar.setBorder(border);
				contentPane.add(btnApuntar);
				
				JButton btnConsultas = new JButton("Consultas\r\n");
				btnConsultas.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						new SeleccionConsulta().setVisible(true);
						
					}
				});
				btnConsultas.setForeground(Color.BLACK);
				btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnConsultas.setBackground(new Color(51, 255, 102));
				btnConsultas.setBounds(955, 551, 244, 47);
				btnConsultas.setBorder(border);
				contentPane.add(btnConsultas);
				
				JButton btnAsignar = new JButton("Asignar Servicio");
				btnAsignar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new Consultar_Terapia_Terapeuta().setVisible(true);
						
					}
				});
				btnAsignar.setForeground(Color.BLACK);
				btnAsignar.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnAsignar.setBackground(new Color(51, 255, 102));
				btnAsignar.setBounds(583, 650, 244, 47);
				btnAsignar.setBorder(border);
				contentPane.add(btnAsignar);

		// llamamos al metodo para llenar la tabla
		// LlenarTabla();

				
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
}