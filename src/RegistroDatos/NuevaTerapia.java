package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import MetodosBBDD.ConnMySQL;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NuevaTerapia extends JDialog {
	/*

    Esta clase sirve para crear Terapias/Actividades, como os habrán dicho en la asociación los usuarios se apuntan a estas actividades y como cabe la posibilidad de que añadan terapias a lo largo del tiempo
    es necesario dejar esta clase para que puedan crearlas en caso de ser necesario. 
    Por tanto esta clase solamente cuenta con los campos minimos necesarios y una sentencia para insertarlos en la BBDD


    */

	private final JPanel contentPanel = new JPanel();
	private JTextField textNomTer;
	private JTextField textPrecio;
	private JTextArea textReglas;
	private int idTerapia=-1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevaTerapia dialog = new NuevaTerapia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevaTerapia() {
		
		Border border = new LineBorder(Color.black, 3);
		
		
		
		setBounds(100, 100, 871, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombreTerapeuta = new JLabel("Nombre Terapia");
			lblNombreTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNombreTerapeuta.setBounds(40, 30, 155, 33);
			contentPanel.add(lblNombreTerapeuta);
		}
		{
			JLabel lblTelefonoTerapeuta = new JLabel("Reglas");
			lblTelefonoTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTelefonoTerapeuta.setBounds(104, 164, 66, 33);
			contentPanel.add(lblTelefonoTerapeuta);
		}
		{
			textNomTer = new JTextField();
			textNomTer.setFont(new Font("Tahoma", Font.BOLD, 14));
			textNomTer.setColumns(10);
			textNomTer.setBounds(181, 30, 219, 33);
			contentPanel.add(textNomTer);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					InsertarDatos();
				}
			});
			okButton.setForeground(Color.BLACK);
			okButton.setFont(new Font("Tahoma", Font.BOLD, 14));
			okButton.setBackground(new Color(51, 255, 102));
			okButton.setActionCommand("OK");
			okButton.setBounds(666, 386, 66, 45);
			okButton.setBorder(border);
			contentPanel.add(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			cancelButton.setForeground(Color.BLACK);
			cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
			cancelButton.setBackground(new Color(255, 0, 51));
			cancelButton.setActionCommand("Cancel");
			cancelButton.setBounds(754, 386, 87, 45);
			cancelButton.setBorder(border);
			contentPanel.add(cancelButton);
		}
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(104, 93, 66, 33);
		contentPanel.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPrecio.setColumns(10);
		textPrecio.setBounds(181, 93, 219, 33);
		contentPanel.add(textPrecio);
		
		textReglas = new JTextArea();
		textReglas.setBounds(182, 175, 285, 206);
		contentPanel.add(textReglas);
		textReglas.setColumns(10);
		
		
		
		setLocationRelativeTo(null);
	}
	
	private void InsertarDatos() {
		String sSelect="INSERT INTO Terapia (nombre_terap, precio, reglas) VALUES (" 
                + "'" + textNomTer.getText() + "',"
                +  textPrecio.getText() + ","
                + "'" + textReglas.getText() + "')";
		
		System.out.println(sSelect);
        ConnMySQL.EjecutarSQL(sSelect);
	}
}
