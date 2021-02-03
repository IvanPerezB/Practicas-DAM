package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JLabel;

public class ListasEspera extends JDialog { // aqui esta las personas que no estan apuntadas a ninguna terapia, ni estan ejerciendo ningun pago( es decir, usuarios y socios que cumplen estas condiciones)
	
	
	
	

	private final JPanel contentPanel = new JPanel();
	public static String actividad = "";
	public static int numerosocio = 0;
	private static ResultSet rsListasEsp;
	
	
	static ConnMySQL conexionMySQL = new ConnMySQL();
	static CargarJTable cargarTabla = new CargarJTable();
	private  ResultSet rsLista;
	
	private String sWhere = "";
	private JTable tLista;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListasEspera dialog = new ListasEspera();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListasEspera() {
		setTitle("Listas de Espera");
		setBounds(100, 100, 1243, 673);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 137, 1199, 474);
		contentPanel.add(scrollPane);
		
		tLista = new JTable();
		LlenarTablaLista("");
		scrollPane.setViewportView(tLista);
		DefaultTableModel ModeltLista = (DefaultTableModel)tLista.getModel();
		
		tLista.setModel(ModeltLista);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(1114, 13, 97, 25);
		contentPanel.add(btnVolver);
		ModeltLista.fireTableDataChanged();
		
		setLocationRelativeTo(null);
	}
	
private void maquearTabla() {

		
		Object[] titulos = new Object[11];
		titulos[0] = "id";
		titulos[1] = "id_socio2";
		titulos[2] = "id_pagos";
		titulos[3] = "Nº Socio";
		titulos[4] = "Nombre";
		titulos[5] = "Apellido 1";
		titulos[6] = "Apellido 2";
		titulos[7] = "DNI";
		titulos[8] = "Fecha Nacimiento";
		titulos[9] = "Telefono";
		titulos[10] = "Email";
		
		
		

		tLista.getColumnModel().getColumn(0).setMaxWidth(0);
		tLista.getColumnModel().getColumn(0).setMinWidth(0);
		tLista.getColumnModel().getColumn(0).setPreferredWidth(0);
		tLista.getColumnModel().getColumn(0).setResizable(false);
		
		tLista.getColumnModel().getColumn(1).setMaxWidth(0);
		tLista.getColumnModel().getColumn(1).setMinWidth(0);
		tLista.getColumnModel().getColumn(1).setPreferredWidth(0);
		tLista.getColumnModel().getColumn(1).setResizable(false);
		
		tLista.getColumnModel().getColumn(2).setMaxWidth(0);
		tLista.getColumnModel().getColumn(2).setMinWidth(0);
		tLista.getColumnModel().getColumn(2).setPreferredWidth(0);
		tLista.getColumnModel().getColumn(2).setResizable(false);
		
		tLista.getColumnModel().getColumn(3).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(4).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(5).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(6).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(7).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(8).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(9).setMaxWidth(1000);
		tLista.getColumnModel().getColumn(10).setMaxWidth(1000);
		
		
		

		tLista.getColumnModel().getColumn(0).setHeaderValue(titulos[0]);
		tLista.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tLista.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tLista.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tLista.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tLista.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tLista.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tLista.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		tLista.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);
		tLista.getColumnModel().getColumn(9).setHeaderValue(titulos[9]);
		tLista.getColumnModel().getColumn(10).setHeaderValue(titulos[10]);
		


	}
	
	private void LlenarTablaLista(String sWhere) {
		
		String sSelect = "SELECT `usuario`.`id`, `socio`.`id_socio2`, `pagos`.`id_pagos`, `socio`.`id_socio`, " + 
				"		 `usuario`.`nomb_usu`, `usuario`.`apellido1_usu`, `usuario`.`apellido2_usu`, `usuario`.`dni_usu`, " + 
				"		 `usuario`.`f_nac_usu`, `usuario`.`tlf_usu`, `usuario`.`email_usu`  " + 
				"		 FROM `usuario` " + 
				"		 LEFT JOIN `socio` ON `socio`.`id` = `usuario`.`id` " + 
				"		 LEFT JOIN `pagos` ON `pagos`.`id` = `usuario`.`id` " + 
				"		 WHERE    `pagos`.`total_pagos` IS  NULL ";
		
		sSelect= sSelect + sWhere + " ORDER BY f_nac_usu ASC";
		
		rsLista = ConnMySQL.sSQL(sSelect);
		
		try{
			
			cargarTabla.LlenarTabla(tLista, sSelect);
			maquearTabla();
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	}
}
