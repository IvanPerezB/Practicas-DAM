package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ElegirNuevo extends JDialog { // Esta clase sirve para elegir entre servicio y técnico, para crearlos posteriormente

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ElegirNuevo dialog = new ElegirNuevo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ElegirNuevo() {
		setBounds(100, 100, 599, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		Border border = new LineBorder(Color.BLACK, 3);
		
		
		{
			JLabel lblquQuieresCrear = new JLabel("\u00BFQu\u00E9 quieres a\u00F1adir?");
			lblquQuieresCrear.setHorizontalAlignment(SwingConstants.CENTER);
			lblquQuieresCrear.setForeground(new Color(51, 153, 255));
			lblquQuieresCrear.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblquQuieresCrear.setBounds(127, 32, 323, 45);
			contentPanel.add(lblquQuieresCrear);
		}
		{
			JButton btnNuevoTerapeuta = new JButton("Nuevo Técnico");
			btnNuevoTerapeuta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new NuevoTerapeuta().setVisible(true);
					dispose();
				}
			});
			btnNuevoTerapeuta.setForeground(Color.BLACK);
			btnNuevoTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnNuevoTerapeuta.setBackground(new Color(51, 255, 102));
			btnNuevoTerapeuta.setBounds(34, 132, 222, 47);
			btnNuevoTerapeuta.setBorder(border);
			contentPanel.add(btnNuevoTerapeuta);
		}
		{
			JButton btnNuevaTerapia = new JButton("Nuevo Servicio");
			btnNuevaTerapia.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new NuevaTerapia().setVisible(true);
				}
			});
			btnNuevaTerapia.setForeground(Color.BLACK);
			btnNuevaTerapia.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnNuevaTerapia.setBackground(new Color(51, 255, 102));
			btnNuevaTerapia.setBounds(306, 132, 222, 47);
			btnNuevaTerapia.setBorder(border);
			contentPanel.add(btnNuevaTerapia);
		}
		{
			JButton btnVolver = new JButton("Volver");
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					dispose();
				}
			});
			btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnVolver.setBounds(459, 214, 110, 36);
			contentPanel.add(btnVolver);
		}
		setLocationRelativeTo(null);
	}

}
