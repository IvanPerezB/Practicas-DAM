package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Becados extends JDialog { // Esta clase sirve para asignar una beca a un usuario, viene de la clase Apuntar.

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPorcentaje;
	private static ResultSet rsUsuarios;
	CargarJTable cargarTabla = new CargarJTable();
	private JRadioButton rdbtnAdecco;
	private JRadioButton rdbtnDependencia;
	private JRadioButton rdbtnBecado;
	private JButton btnConfirmar;
	private boolean rsBecado;
	private boolean rsBecaTotal;
	
	
	private DefaultTableModel Modeltable = new DefaultTableModel();
	JTextField txtNombreB;
	JTextField txtDNIB;
	JTextField txtApellido1B;
	JTextField txtTotalB;
	JTextField txtIdB;
	JTextField txtIdSocioB;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Becados dialog = new Becados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Becados() {
		setTitle("Becados");
		setBounds(100, 100, 964, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1_1_3_1 = new JPanel();
		panel_1_1_3_1.setLayout(null);
		panel_1_1_3_1.setBackground(new Color(51, 153, 255));
		panel_1_1_3_1.setBounds(10, 11, 926, 118);
		contentPanel.add(panel_1_1_3_1);
	
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 11, 84, 20);
		panel_1_1_3_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("DNI:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2_1.setBounds(10, 42, 55, 20);
		panel_1_1_3_1.add(lblNewLabel_2_1);
		
		txtNombreB = new JTextField();
		txtNombreB.setBounds(104, 13, 133, 20);
		panel_1_1_3_1.add(txtNombreB);
		txtNombreB.setColumns(10);
		
		txtDNIB = new JTextField();
		txtDNIB.setColumns(10);
		txtDNIB.setBounds(98, 42, 139, 20);
		panel_1_1_3_1.add(txtDNIB);
		
		JLabel lblPrimerApellido = new JLabel("Primer apellido");
		lblPrimerApellido.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrimerApellido.setBounds(288, 11, 198, 20);
		panel_1_1_3_1.add(lblPrimerApellido);
		
		txtApellido1B = new JTextField();
		txtApellido1B.setColumns(10);
		txtApellido1B.setBounds(496, 13, 133, 20);
		panel_1_1_3_1.add(txtApellido1B);
		Apuntar miApuntar= new Apuntar();
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar");
		lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalAPagar.setBounds(288, 42, 133, 20);
		panel_1_1_3_1.add(lblTotalAPagar);
		
		txtTotalB = new JTextField();
		txtTotalB.setColumns(10);
		txtTotalB.setBounds(496, 43, 133, 20);
		panel_1_1_3_1.add(txtTotalB);
		
		txtIdB = new JTextField();
		txtIdB.setBounds(707, 42, 48, 22);
		panel_1_1_3_1.add(txtIdB);
		txtIdB.setColumns(10);
		txtIdB.setVisible(true);
		
		txtIdSocioB = new JTextField();
		txtIdSocioB.setBounds(707, 83, 55, 22);
		panel_1_1_3_1.add(txtIdSocioB);
		txtIdSocioB.setColumns(10);
		txtIdSocioB.setVisible(false);
		
		JPanel panel_1_1_3_2 = new JPanel();
		panel_1_1_3_2.setBackground(new Color(51, 153, 255));
		panel_1_1_3_2.setBounds(507, 140, 429, 282);
		contentPanel.add(panel_1_1_3_2);
		panel_1_1_3_2.setLayout(null);
		
		Border border = new LineBorder(Color.BLACK, 3);
		//btnEliminar.setBorder(border);
		
	    btnConfirmar = new JButton("Confirmar");
	    btnConfirmar.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		
	    		Becar();
	    	}
	    });
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirmar.setBackground(new Color(51, 255, 102));
		btnConfirmar.setBounds(227, 119, 192, 47);
		btnConfirmar.setBorder(border);
		panel_1_1_3_2.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancelar.setBackground(new Color(204, 0, 0));
		btnCancelar.setBounds(10, 119, 192, 47);
		btnCancelar.setBorder(border);
		panel_1_1_3_2.add(btnCancelar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBackground(new Color(51, 153, 255));
		btnVolver.setBounds(296, 235, 123, 34);
		panel_1_1_3_2.add(btnVolver);
		
		JPanel panel_1_1_3 = new JPanel();
		panel_1_1_3.setBounds(10, 140, 487, 282);
		contentPanel.add(panel_1_1_3);
		panel_1_1_3.setLayout(null);
		panel_1_1_3.setBackground(new Color(51, 153, 255));
		
		JPanel panel_1_1_3_2_1 = new JPanel();
		panel_1_1_3_2_1.setLayout(null);
		panel_1_1_3_2_1.setBackground(Color.WHITE);
		panel_1_1_3_2_1.setBounds(10, 84, 227, 109);
		panel_1_1_3.add(panel_1_1_3_2_1);
		
		 rdbtnBecado = new JRadioButton("Becado por asociaci\u00F3n");
		rdbtnBecado.setBackground(Color.WHITE);
		rdbtnBecado.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnBecado.setBounds(6, 7, 215, 23);
		panel_1_1_3_2_1.add(rdbtnBecado);
		
		txtPorcentaje = new JTextField();
		txtPorcentaje.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPorcentaje.setBounds(55, 68, 61, 30);
		panel_1_1_3_2_1.add(txtPorcentaje);
		txtPorcentaje.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Porcentaje:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(45, 43, 105, 23);
		panel_1_1_3_2_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(118, 68, 49, 30);
		panel_1_1_3_2_1.add(lblNewLabel_1);
		
		JPanel panel_1_1_3_2_1_1 = new JPanel();
		panel_1_1_3_2_1_1.setLayout(null);
		panel_1_1_3_2_1_1.setBackground(Color.WHITE);
		panel_1_1_3_2_1_1.setBounds(247, 84, 227, 109);
		panel_1_1_3.add(panel_1_1_3_2_1_1);
		
		JLabel lblBecadoExterno = new JLabel("Becado externo:");
		lblBecadoExterno.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBecadoExterno.setBounds(29, 11, 153, 23);
		panel_1_1_3_2_1_1.add(lblBecadoExterno);
		
		 rdbtnAdecco = new JRadioButton("Adecco\r\n");
		rdbtnAdecco.setBackground(Color.WHITE);
		rdbtnAdecco.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnAdecco.setBounds(39, 39, 122, 23);
		panel_1_1_3_2_1_1.add(rdbtnAdecco);
		
		 rdbtnDependencia = new JRadioButton("Dependencia");
		rdbtnDependencia.setBackground(Color.WHITE);
		rdbtnDependencia.setFont(new Font("Tahoma", Font.BOLD, 16));
		rdbtnDependencia.setBounds(39, 67, 140, 23);
		panel_1_1_3_2_1_1.add(rdbtnDependencia);
		
		setLocationRelativeTo(null);

		
		
	
	}
	
	private void Becar() {  //Sentencias SQL para asignarlas. Según los RadioButtons que estan seleccionados.
			
			
			
			String becado, becado2;
		
			if(rdbtnBecado.isSelected()==true && rdbtnDependencia.isSelected()==false && rdbtnAdecco.isSelected()==false ) {
				
				becado="Asociacion";
				
				Double e= Double.parseDouble(txtPorcentaje.getText());
				
				String sSelect ="UPDATE usuario SET becado = "+ e +", tipo_beca = 'Asociación' WHERE id = "+ txtIdB.getText();
				
				
				rsBecado = ConnMySQL.EjecutarSQL(sSelect); 
				JOptionPane.showMessageDialog(null," El usuario "+ txtNombreB.getText() + " ha sido becado en " +becado + " con un " +e+ "% de descuento");
				
				if(txtPorcentaje.getText() != null) {
					Double total = Double.parseDouble(txtTotalB.getText());
					String sSelect2 = "UPDATE Pagos SET total_pagos =" + ((total*e.doubleValue())/100) + " WHERE (SELECT id FROM usuario LIMIT 1) = " +txtIdB.getText();
					rsBecaTotal=ConnMySQL.EjecutarSQL(sSelect2);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe introducir un porcentaje de beca");
				}
				
			}else {
			
			if(rdbtnAdecco.isSelected()==true && rdbtnDependencia.isSelected()==false && rdbtnBecado.isSelected()==false) {
				
				becado="Adecco";			
				int i=0;			
				
				String sSelect ="UPDATE usuario SET becado = "
						+ i + ","
						+ " tipo_beca = '" + becado + "' "
						+ " WHERE id = "+ txtIdB.getText();
				
				
				rsBecado = ConnMySQL.EjecutarSQL(sSelect); 
				JOptionPane.showMessageDialog(null," El usuario "+ txtNombreB.getText()+ " ha sido becado en " +becado );
				
			
			}
			
			if(rdbtnDependencia.isSelected()==true && rdbtnBecado.isSelected()== false && rdbtnBecado.isSelected()==false) {
			
				
				becado ="Dependencia";
				int i=0;
				
				String sSelect ="UPDATE usuario SET becado = "
						+ i + ","
						+" tipo_beca = '" + becado + "' "
						+ " WHERE id = "+ txtIdB.getText();
				
				rsBecado = ConnMySQL.EjecutarSQL(sSelect); 
				JOptionPane.showMessageDialog(null," El usuario "+ txtNombreB.getText()+ " ha sido becado en " +becado );
				
			}
			
		
			
			if(rdbtnDependencia.isSelected()==true && rdbtnAdecco.isSelected()==true && rdbtnBecado.isSelected()==false) {
				becado="Adecco";
				becado2 ="Dependencia";
				int i=0;			
				
				String sSelect ="UPDATE usuario SET becado = "
						+ i + ","
						+"tipo_beca =  CONCAT('"+ becado +"', '" + becado2 + "') "
						+ " WHERE id = "+ txtIdB.getText();
				
				rsBecado = ConnMySQL.EjecutarSQL(sSelect);
				JOptionPane.showMessageDialog(null," El usuario "+ txtNombreB.getText()+ " ha sido becado en " + becado +" y en " + becado2 );
				
			}
			
			
			}	
		}
}


