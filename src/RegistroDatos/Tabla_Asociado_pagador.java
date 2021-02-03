package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.OrderedBidiMap;

import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Tabla_Asociado_pagador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tAsociadoPagador;
	CargarJTable cargarTabla = new CargarJTable();
	private static ResultSet rsAsociadoPagador;
	String sWhere ="";
	private JTextField txtNombre;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtDNI;
	private JTextField txtProfesion;
	private JTextField txtMovil1;
	private JTextField txtMovil2;
	private JTextField txtEmail;
	private JTextField txtRelacion;
	private JButton btnLimpiar;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Tabla_Asociado_pagador dialog = new Tabla_Asociado_pagador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Tabla_Asociado_pagador() {
		setBounds(100, 100, 1208, 666);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 124, 1153, 435);
		contentPanel.add(scrollPane);
		
		tAsociadoPagador = new JTable();
		LlenarTablaAsociadoPagador("");
		scrollPane.setViewportView(tAsociadoPagador);
		
		
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(23, 89, 128, 22);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido1 = new JTextField();
		txtApellido1.setColumns(10);
		txtApellido1.setBounds(150, 89, 128, 22);
		contentPanel.add(txtApellido1);
		
		txtApellido2 = new JTextField();
		txtApellido2.setColumns(10);
		txtApellido2.setBounds(279, 89, 128, 22);
		contentPanel.add(txtApellido2);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(406, 89, 128, 22);
		contentPanel.add(txtDNI);
		
		txtProfesion = new JTextField();
		txtProfesion.setColumns(10);
		txtProfesion.setBounds(533, 89, 128, 22);
		contentPanel.add(txtProfesion);
		
		txtMovil1 = new JTextField();
		txtMovil1.setColumns(10);
		txtMovil1.setBounds(661, 89, 128, 22);
		contentPanel.add(txtMovil1);
		
		txtMovil2 = new JTextField();
		txtMovil2.setColumns(10);
		txtMovil2.setBounds(789, 89, 128, 22);
		contentPanel.add(txtMovil2);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(916, 89, 128, 22);
		contentPanel.add(txtEmail);
		
		txtRelacion = new JTextField();
		txtRelacion.setColumns(10);
		txtRelacion.setBounds(1042, 89, 134, 22);
		contentPanel.add(txtRelacion);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(51, 255, 102));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarRegistros();
			}
		});
		btnBuscar.setBounds(1042, 30, 134, 35);
		contentPanel.add(btnBuscar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(51, 255, 102));
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LimpiarCampos();
			}
		});
		btnLimpiar.setBounds(898, 30, 134, 35);
		contentPanel.add(btnLimpiar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(1068, 581, 108, 25);
		contentPanel.add(btnVolver);
		
	}
	
	
	public void LlenarTablaAsociadoPagador(String sWhere) {

		String sSelect = "SELECT id,nom_asoc_fam1_pag,apellido1_asoc_fam1_pag,apellido2_asoc_fam1_pag,"
				+ "dni_asoc_fam1_pag,profe_asoc_fam1_pag,movil1_asoc_fam1_pag,movil2_asoc_fam1_pag,"
				+ "email_asoc_fam1_pag,relac_asoc_fam1_pag FROM AsociadoPagador";
		sSelect = sSelect + sWhere + " ORDER BY nom_asoc_fam1_pag ";
		rsAsociadoPagador = ConnMySQL.sSQL(sSelect);

		try {
			cargarTabla.LlenarTabla(tAsociadoPagador, sSelect);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		MaquearTabla();

	}
	
	private void MaquearTabla() {
		DefaultTableModel ModeltAsociadoPagador= (DefaultTableModel) tAsociadoPagador.getModel();
		
		
		
		
		
		Object[] titulos= new Object[10];
		titulos[0]="id";
		titulos[1]="Nombre A.P.";
		titulos[2]="Primer Apellido A.P.";
		titulos[3]="Segundo Apellido A.P.";
		titulos[4]="DNI A.P.";
		titulos[5]="Profesion A.P.";
		titulos[6]="Movil 1 A.P.";
		titulos[7]="Movil 2 A.P.";
		titulos[8]="Email A.P.";
		titulos[9]="Relacion A.P.";
		
		
		tAsociadoPagador.getColumnModel().getColumn(0).setMaxWidth(0);
		tAsociadoPagador.getColumnModel().getColumn(0).setMinWidth(0);
		tAsociadoPagador.getColumnModel().getColumn(0).setPreferredWidth(0);
		tAsociadoPagador.getColumnModel().getColumn(0).setResizable(false);
		tAsociadoPagador.getColumnModel().getColumn(1).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(2).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(3).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(4).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(5).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(6).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(7).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(8).setMaxWidth(1000);
		tAsociadoPagador.getColumnModel().getColumn(9).setMaxWidth(1000);
		
		tAsociadoPagador.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tAsociadoPagador.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tAsociadoPagador.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tAsociadoPagador.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tAsociadoPagador.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tAsociadoPagador.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tAsociadoPagador.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		tAsociadoPagador.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);
		tAsociadoPagador.getColumnModel().getColumn(9).setHeaderValue(titulos[9]);
		
		tAsociadoPagador.setModel(ModeltAsociadoPagador);
	}
	
	private void BuscarRegistros() {

		sWhere="";
		
		if (txtNombre.getText() != null && !txtNombre.getText().contentEquals("")) {
			sWhere = sWhere + " nom_asoc_fam1_pag LIKE ( \"" + txtNombre.getText() + "%\")";
		}

		if (txtApellido1.getText() != null && !txtApellido1.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido1_asoc_fam1_pag LIKE ( \"" + txtApellido1.getText() + "%\")";
		}

		if (txtApellido2.getText() != null && !txtApellido2.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido2_asoc_fam1_pag LIKE ( \"" + txtApellido2.getText() + "%\")";
		}
		if (txtDNI.getText() != null && !txtDNI.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " dni_asoc_fam1_pag LIKE ( \"" + txtDNI.getText() + "%\")";
		}

		if (txtProfesion.getText() != null && !txtProfesion.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " profe_asoc_fam1_pag LIKE ( \"" + txtProfesion.getText() + "%\")";
		}
		if (txtMovil1.getText() != null && !txtMovil1.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " movil1_asoc_fam1_pag LIKE ( \"" + txtMovil1.getText() + "%\")";
		}

		if (txtMovil2.getText() != null && !txtMovil2.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " movil2_asoc_fam1_pag LIKE ( \"" + txtMovil2.getText() + "%\")";
		}
		
		if (txtEmail.getText() != null && !txtEmail.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " email_asoc_fam1_pag LIKE ( \"" + txtEmail.getText() + "%\")";
		}
		
		if (txtRelacion.getText() != null && !txtRelacion.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " relac_asoc_fam1_pag LIKE ( \"" + txtRelacion.getText() + "%\")";
		}

		if (sWhere != null && !sWhere.contentEquals("")) {
			sWhere = " WHERE " + sWhere;
		}

		System.out.println(sWhere);

		LlenarTablaAsociadoPagador(sWhere);

	}
	
	protected void LimpiarCampos() {

        txtNombre.setText("");
        txtApellido1.setText("");
        txtApellido2.setText("");
        txtDNI.setText("");
        txtProfesion.setText("");
        txtMovil1.setText("");
        txtMovil2.setText("");
        txtEmail.setText("");
        txtRelacion.setText("");
        
        LlenarTablaAsociadoPagador("");

    }
}
