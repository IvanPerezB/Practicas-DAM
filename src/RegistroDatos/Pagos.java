package RegistroDatos;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import com.toedter.calendar.JDateChooser;

import MetodosBBDD.CargarCombo;
import MetodosBBDD.ConnMySQL;
import MetodosBBDD.Item;
import MetodosBBDD.CargarJTable;

import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import javax.swing.plaf.TextUI;

public class Pagos extends JDialog { //Esta clase sirve para visualizar en una tabla la gente que tiene que realizar Pagos, en la sentencia se ve que lo hacemos cogiendo aquellos donde el total de pagos es nulo 

	
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	ResultSet rsTerapias = null;
	ResultSet rsTerapeutas = null;
	ResultSet rsPagos = null;
	private JTextField txtNSocios;
	private JTextField txtNombUsu;
	private String sWhere;
	private int id =-1;
	ConnMySQL conexionMySQL = new ConnMySQL();
    static CargarJTable cargarTabla = new CargarJTable();
    
    
	
	
	// Pedido
	//private Pedidos pedido;

	private JTable tPagos;
	



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Pagos dialog = new Pagos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Pagos() {
		setTitle("Pagos");

		setBounds(100, 100, 919, 537);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		Date fHoy = new Date();

		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(12, 74, 874, 360);
		contentPanel.add(scrollPaneProductos);

		tPagos = new JTable();
		

		LlenarTablaPagos("");
		DefaultTableModel ModeltPagos= (DefaultTableModel) tPagos.getModel();
		scrollPaneProductos.setViewportView(tPagos);
		tPagos.setModel(ModeltPagos);
		
		
		JLabel lblNSocio = new JLabel("N\u00BA SOCIO");
		lblNSocio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNSocio.setBounds(12, 17, 86, 19);
		contentPanel.add(lblNSocio);
		
		txtNSocios = new JTextField();
		txtNSocios.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNSocios.setBounds(94, 13, 99, 23);
		contentPanel.add(txtNSocios);
		txtNSocios.setColumns(10);
		
		JLabel lblNombreDeUsuario = new JLabel("NOMBRE DE USUARIO");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreDeUsuario.setBounds(232, 15, 172, 23);
		contentPanel.add(lblNombreDeUsuario);
		
		txtNombUsu = new JTextField();
		txtNombUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNombUsu.setBounds(401, 16, 136, 22);
		contentPanel.add(txtNombUsu);
		txtNombUsu.setColumns(10);
		 
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarRegistros();
			}
		});
		btnBuscar.setBounds(776, 15, 110, 23);
		contentPanel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LimpiarCampos();
			}
		});
		btnLimpiar.setBounds(659, 15, 97, 25);
		contentPanel.add(btnLimpiar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVolver.setBounds(704, 445, 182, 23);
		contentPanel.add(btnVolver);
		
		
		setLocationRelativeTo(null);
	}

	

	private void MaquearTablaPagos() {

	
	Object[] titulos= new Object[10];
	titulos[0]="id";
	titulos[1]="id_socio2";
	titulos[2]="id_pagos";
	titulos[3]="Nº de Socio";
	titulos[4]="Nombre";
	titulos[5]="Porcentaje de Beca";
	titulos[6]="Tipo de Beca";
	titulos[7]="Cuenta Bancaria";
	titulos[8]="Total Pagos";
	titulos[9]="id_registro";
	
	
	
	
	tPagos.getColumnModel().getColumn(0).setMaxWidth(0);
	tPagos.getColumnModel().getColumn(0).setMinWidth(0);
	tPagos.getColumnModel().getColumn(0).setPreferredWidth(0);
	tPagos.getColumnModel().getColumn(0).setResizable(false);
	
	tPagos.getColumnModel().getColumn(1).setMaxWidth(0);
	tPagos.getColumnModel().getColumn(1).setMinWidth(0);
	tPagos.getColumnModel().getColumn(1).setPreferredWidth(0);
	tPagos.getColumnModel().getColumn(1).setResizable(false);
	
	tPagos.getColumnModel().getColumn(2).setMaxWidth(0);
	tPagos.getColumnModel().getColumn(2).setMinWidth(0);
	tPagos.getColumnModel().getColumn(2).setPreferredWidth(0);
	tPagos.getColumnModel().getColumn(2).setResizable(false);
	
	
	tPagos.getColumnModel().getColumn(3).setMaxWidth(1000);
	tPagos.getColumnModel().getColumn(4).setMaxWidth(1000);
	tPagos.getColumnModel().getColumn(5).setMaxWidth(1000);
	tPagos.getColumnModel().getColumn(6).setMaxWidth(1000);
	tPagos.getColumnModel().getColumn(7).setMaxWidth(1000);
	tPagos.getColumnModel().getColumn(8).setMaxWidth(1000);
	
	tPagos.getColumnModel().getColumn(9).setMaxWidth(0);
	tPagos.getColumnModel().getColumn(9).setMinWidth(0);
	tPagos.getColumnModel().getColumn(9).setPreferredWidth(0);
	tPagos.getColumnModel().getColumn(9).setResizable(false);
	
	
	
	
	tPagos.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
	tPagos.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
	tPagos.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
	tPagos.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
	tPagos.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
	tPagos.getColumnModel().getColumn(8).setHeaderValue(titulos[8]);
	

	
    
}
	private void LlenarTablaPagos(String sWhere) {
	   
	    
	    String sSelect2 ="SELECT `usuario`.`id`, `socio`.`id_socio2`, `pagos`.`id_pagos`, `socio`.`id_socio`, `usuario`.`nomb_usu`, `usuario`.`becado`, `usuario`.`tipo_beca`, `socio`.`num_cuenta`, `pagos`.`total_pagos`, `registro_servicio`.`id_registro_s`" + 
	    		" FROM `usuario` " + 
	    		"	LEFT JOIN `socio` ON `socio`.`id` = `usuario`.`id` " + 
	    		"	LEFT JOIN `pagos` ON `pagos`.`id` = `usuario`.`id` " + 
	    		"	LEFT JOIN `registro_servicio` ON `pagos`.`id_registro_s` = `registro_servicio`.`id_registro_s`";
	   sSelect2=sSelect2 + sWhere;
	    rsPagos=ConnMySQL.sSQL(sSelect2);
	    
	    System.out.println(sSelect2);
	    try {
	        cargarTabla.LlenarTabla(tPagos, sSelect2);
	        MaquearTablaPagos();
	        
	
	    } catch (Exception e) {
	        e.printStackTrace();
	
	    }



	}
	
	private void BuscarRegistros() { //Este método sirve para buscar a un socio con algun servicio contratado

        sWhere = "";

        if ( txtNSocios.getText() != null && !txtNSocios.getText().contentEquals("")  ) {
                sWhere = sWhere + " socio.id_socio LIKE '" + txtNSocios.getText() + "%'" ;
        }

        if ( txtNombUsu.getText() != null && !txtNombUsu.getText().contentEquals("")  ) {
            if ( sWhere != null && !sWhere.contentEquals("")  ) {
                sWhere = sWhere + " AND ";
            }
            sWhere = sWhere + " usuario.nomb_usu LIKE '" + txtNombUsu.getText() + "%'";
        }



        if ( sWhere != null && !sWhere.contentEquals("")  ) {
            sWhere = " WHERE " + sWhere;
        }


        System.out.println(sWhere);

        LlenarTablaPagos(sWhere);

    }
	
	private void LimpiarCampos() {
        txtNSocios.setText("");
        txtNombUsu.setText("");
        LlenarTablaPagos("");
    }
}
