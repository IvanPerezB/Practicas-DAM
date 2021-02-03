package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.ResultSet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Consultar_Terapia_Terapeuta extends JDialog {// consulta de terapias y terapeutas, desde aqui se va a asignar una terapia a un terapeuta

	private static final DefaultTableModel ModeltTerapia = null;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaTerapia;
	private JTable tablaTerapeuta;
	private static ResultSet rsTerapia;
	private static ResultSet rsTerapeuta;
	CargarJTable cargarTabla = new CargarJTable();
	static ConnMySQL conexionMySQL = new ConnMySQL();
	Asignar asign = new Asignar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consultar_Terapia_Terapeuta dialog = new Consultar_Terapia_Terapeuta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consultar_Terapia_Terapeuta() {
		setBounds(100, 100, 1076, 632);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1_2_2_2 = new JPanel();
		panel_1_2_2_2.setLayout(null);
		panel_1_2_2_2.setBackground(new Color(51, 153, 255));
		panel_1_2_2_2.setBounds(10, 11, 1038, 278);
		contentPanel.add(panel_1_2_2_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 828, 256);
		panel_1_2_2_2.add(scrollPane);
		
		tablaTerapia = new JTable();
		
		
	
		
		
		JPanel panel_1_2_2_2_1 = new JPanel();
		panel_1_2_2_2_1.setLayout(null);
		panel_1_2_2_2_1.setBackground(new Color(51, 153, 255));
		panel_1_2_2_2_1.setBounds(10, 300, 1038, 278);
		contentPanel.add(panel_1_2_2_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 831, 256);
		panel_1_2_2_2_1.add(scrollPane_1);
		
		tablaTerapeuta = new JTable();
		
		
		
		
		
		
		
		
		
		
		LlenarTablaTerapia();
		LlenarTablaTerapeuta();		
		
		scrollPane_1.setViewportView(tablaTerapeuta);
		scrollPane.setViewportView(tablaTerapia);
		
		

		DefaultTableModel ModeltTerapeuta= (DefaultTableModel) tablaTerapeuta.getModel();
		DefaultTableModel ModeltTerapia= (DefaultTableModel) tablaTerapia.getModel();
		

		Object[] Terapia= new Object[4];
		Terapia[0]="id";
		Terapia[1]="Nombre";
		Terapia[2]="Precio";
		Terapia[3]="Reglas";
		
		
		Object[] Terapeuta= new Object[5];
		Terapeuta[0]="id";
		Terapeuta[1]="Terapia";
		Terapeuta[2]="Nombre";
		Terapeuta[3]="Apellido";
		Terapeuta[4]="Telefono";
		

		
		tablaTerapia.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaTerapia.getColumnModel().getColumn(0).setMinWidth(0);
		tablaTerapia.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaTerapia.getColumnModel().getColumn(0).setResizable(false);
		
		
		tablaTerapia.getColumnModel().getColumn(1).setHeaderValue(Terapia[1]);
		tablaTerapia.getColumnModel().getColumn(2).setHeaderValue(Terapia[2]);
		tablaTerapia.getColumnModel().getColumn(3).setHeaderValue(Terapia[3]);
		
		
		tablaTerapeuta.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaTerapeuta.getColumnModel().getColumn(0).setMinWidth(0);
		tablaTerapeuta.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaTerapeuta.getColumnModel().getColumn(0).setResizable(false);
		
		
		tablaTerapeuta.getColumnModel().getColumn(1).setHeaderValue(Terapeuta[1]);
		tablaTerapeuta.getColumnModel().getColumn(2).setHeaderValue(Terapeuta[2]);
		tablaTerapeuta.getColumnModel().getColumn(3).setHeaderValue(Terapeuta[3]);
		tablaTerapeuta.getColumnModel().getColumn(4).setHeaderValue(Terapeuta[4]);
		
		
		tablaTerapeuta.setModel(ModeltTerapeuta);
		
		JButton btnNewButton = new JButton("Asignar una terapia");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tablaTerapeuta.getSelectedRow()>=0) {
					
					try {
						String id = String.valueOf(ModeltTerapeuta.getValueAt(tablaTerapeuta.getSelectedRow(), 0));
						String Nombre = String.valueOf(ModeltTerapeuta.getValueAt(tablaTerapeuta.getSelectedRow(), 2));
						String Apellido1 = String.valueOf(ModeltTerapeuta.getValueAt(tablaTerapeuta.getSelectedRow(), 3));
						String Telefono = String.valueOf(ModeltTerapeuta.getValueAt(tablaTerapeuta.getSelectedRow(), 4));
						
						Apuntar miApunte =new Apuntar();
						
						asign.textField_3.setText(id);
						asign.textField.setText(Nombre);
						asign.textField_1.setText(Apellido1);
						asign.textField_2.setText(Telefono);
						asign.setVisible(true);
					
					}catch (Exception es) {
						JOptionPane.showInputDialog(this, "Esta vacio");
					}
				}
				else {
					JOptionPane.showInputDialog(this, "Debe seleccionar una fila");
				}
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(876, 99, 152, 90);
		panel_1_2_2_2_1.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(918, 231, 110, 36);
		panel_1_2_2_2_1.add(btnVolver);
		tablaTerapia.setModel(ModeltTerapia);
		
		
	}
	
	private void LlenarTablaTerapia() {
		
		
			String sSelect = "SELECT id_tera, nombre_terap,precio,reglas FROM terapia WHERE borrado = false";
		
		
				
		rsTerapia = conexionMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tablaTerapia, sSelect);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
private void LlenarTablaTerapeuta() {
		
		String sSelect = "SELECT id_terapeuta, (SELECT nombre_terap FROM terapia WHERE id_tera = (SELECT id_tera FROM terapeuta)),  nombre_terape, apellido1_terape, telefono_terape FROM terapeuta WHERE borrado = false";
		rsTerapeuta=ConnMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tablaTerapeuta, sSelect);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}
}