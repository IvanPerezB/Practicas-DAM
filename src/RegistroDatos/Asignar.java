package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MetodosBBDD.CargarCombo;
import MetodosBBDD.ConnMySQL;
import MetodosBBDD.Item;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class Asignar extends JDialog { // aqui asignamos una terapia a un terapeuta

	private final JPanel contentPanel = new JPanel();
	 JTextField textField;
	 JTextField textField_1;
	 JTextField textField_2;
	private JComboBox comboBox;
	ResultSet rsAsignar;
	boolean rsAsignar2;
	 JTextField textField_3;
	 private JButton btnVolver;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Asignar dialog = new Asignar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Asignar() {
		setBounds(100, 100, 636, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNombre.setBounds(67, 59, 79, 18);
			contentPanel.add(lblNombre);
		}
		
		JLabel lblNewLabel = new JLabel("Apellido");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(67, 149, 79, 17);
		contentPanel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Asignar Terapia");
		btnNewButton.setBackground(new Color(51, 255, 102));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AsignarUnaTerapia();
			}
		});
		btnNewButton.setBounds(432, 217, 159, 37);
		contentPanel.add(btnNewButton);
		
		 comboBox = new JComboBox();
		 comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		 comboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		SeleccionarTerapia();
		 	}
		 });
		 CargarComboTerapias();
		comboBox.setBounds(402, 55, 147, 22);
		contentPanel.add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(156, 56, 96, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_1.setBounds(156, 146, 96, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(67, 259, 79, 19);
		contentPanel.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_2.setBounds(156, 256, 96, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField_3.setBounds(288, 200, 96, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBackground(new Color(51, 255, 102));
		btnVolver.setBounds(432, 269, 159, 37);
		contentPanel.add(btnVolver);
		textField_3.setVisible(false);
	}
	protected  void SeleccionarTerapia() { //Sirve

        if (comboBox.getSelectedIndex() != 0) {
        	

            Item item = (Item) comboBox.getSelectedItem();
            System.out.println(item.getId() + " " + item.getDescription());

            String sSelect = "SELECT id_tera,nombre_terap, precio FROM Terapia WHERE id_tera = "
                    + item.getId();
            rsAsignar = ConnMySQL.sSQL(sSelect);
            
            
        }
        
    }

	public void CargarComboTerapias() { //Sirve
        String sSelect = "SELECT id_tera, nombre_terap,precio,reglas FROM Terapia WHERE borrado = false ORDER BY nombre_terap";
        CargarCombo.LlenarCombo(comboBox, sSelect);
       
    }
	
	private void AsignarUnaTerapia() {
		
		Item item= (Item) comboBox.getSelectedItem();
		
		String sSelect ="UPDATE Terapeuta SET id_tera = " +item.getId()+" WHERE (SELECT id_terapeuta FROM Terapeuta WHERE id_terapeuta = " 
		+ textField_3.getText()+")";
				
		
		rsAsignar2 = ConnMySQL.EjecutarSQL(sSelect);
		
	}
	
	
	
	
	
	
	
}
