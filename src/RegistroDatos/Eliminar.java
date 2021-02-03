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

public class Eliminar extends JDialog { //Esta clase sirve para eliminar los terapeutas y las terapias ya creadas.

	private static final DefaultTableModel ModeltTerapia = null;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaTerapia;
	private JTable tablaTerapeuta;
	private static ResultSet rsTerapia;
	private static ResultSet rsTerapeuta;
	CargarJTable cargarTabla = new CargarJTable();
	static ConnMySQL conexionMySQL = new ConnMySQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Eliminar dialog = new Eliminar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Eliminar() {
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
		
		
		JButton btnEliminarTerapia = new JButton("ELIMINAR");
		btnEliminarTerapia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EliminarTerapia();
			}
		});
		btnEliminarTerapia.setForeground(Color.BLACK);
		btnEliminarTerapia.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEliminarTerapia.setBackground(new Color(204, 0, 51));
		btnEliminarTerapia.setBounds(864, 113, 164, 47);
		panel_1_2_2_2.add(btnEliminarTerapia);
		
		JPanel panel_1_2_2_2_1 = new JPanel();
		panel_1_2_2_2_1.setLayout(null);
		panel_1_2_2_2_1.setBackground(new Color(51, 153, 255));
		panel_1_2_2_2_1.setBounds(10, 300, 1038, 278);
		contentPanel.add(panel_1_2_2_2_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 831, 256);
		panel_1_2_2_2_1.add(scrollPane_1);
		
		tablaTerapeuta = new JTable();
		
		
		
		
		JButton btnEliminarTerapeuta = new JButton("ELIMINAR");
		btnEliminarTerapeuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EliminarTerapeuta();
			}
		});
		btnEliminarTerapeuta.setForeground(Color.BLACK);
		btnEliminarTerapeuta.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEliminarTerapeuta.setBackground(new Color(204, 0, 51));
		btnEliminarTerapeuta.setBounds(864, 109, 164, 47);
		panel_1_2_2_2_1.add(btnEliminarTerapeuta);
		
		
		
		
		LlenarTablaTerapia();
		LlenarTablaTerapeuta();		
		
		scrollPane_1.setViewportView(tablaTerapeuta);
		scrollPane.setViewportView(tablaTerapia);
		
		

		DefaultTableModel ModeltTerapeuta= (DefaultTableModel) tablaTerapeuta.getModel();
		DefaultTableModel ModeltTerapia= (DefaultTableModel) tablaTerapia.getModel();
		

		Object[] Terapia= new Object[5];
		Terapia[0]="id";
		Terapia[1]="Nombre";
		Terapia[2]="Precio";
		Terapia[3]="Reglas";
		Terapia[4]="Borrado";
		
		Object[] Terapeuta= new Object[4];
		Terapeuta[0]="id";
		Terapeuta[1]="Nombre";
		Terapeuta[2]="Apellido";
		Terapeuta[3]="Telefono";
		

		
		tablaTerapia.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaTerapia.getColumnModel().getColumn(0).setMinWidth(0);
		tablaTerapia.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaTerapia.getColumnModel().getColumn(0).setResizable(false);
		
		
		tablaTerapia.getColumnModel().getColumn(1).setHeaderValue(Terapia[1]);
		tablaTerapia.getColumnModel().getColumn(2).setHeaderValue(Terapia[2]);
		tablaTerapia.getColumnModel().getColumn(3).setHeaderValue(Terapia[3]);
		tablaTerapia.getColumnModel().getColumn(4).setHeaderValue(Terapia[4]);
		
		
		tablaTerapeuta.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaTerapeuta.getColumnModel().getColumn(0).setMinWidth(0);
		tablaTerapeuta.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaTerapeuta.getColumnModel().getColumn(0).setResizable(false);
		
		
		tablaTerapeuta.getColumnModel().getColumn(1).setHeaderValue(Terapeuta[1]);
		tablaTerapeuta.getColumnModel().getColumn(2).setHeaderValue(Terapeuta[2]);
		tablaTerapeuta.getColumnModel().getColumn(3).setHeaderValue(Terapeuta[3]);
		
		
		
		tablaTerapeuta.setModel(ModeltTerapeuta);
		
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
		
		
			String sSelect = "SELECT id_tera, nombre_terap,precio,reglas, borrado FROM terapia WHERE borrado = false";
		
		
				
		rsTerapia = conexionMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tablaTerapia, sSelect);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
private void LlenarTablaTerapeuta() {
		
		String sSelect = "SELECT id_terapeuta, nombre_terape, apellido1_terape, telefono_terape FROM terapeuta WHERE borrado = false";
		rsTerapeuta=ConnMySQL.sSQL(sSelect);
		
		try {
			cargarTabla.LlenarTabla(tablaTerapeuta, sSelect);
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}

private void EliminarTerapia() {


    if (tablaTerapia.getSelectedRow() <0) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un servicio");
        return;
    }

    int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que quieres borrar el servicio seleccionado?");
    
    
    if (resp == 0) {
    	
        String sSentencia = "UPDATE  Terapia "
        		+ "set borrado = true "
                + " WHERE id_tera = " + String.valueOf(tablaTerapia.getValueAt(tablaTerapia.getSelectedRow(),0));

        System.out.println(sSentencia);
        ConnMySQL.EjecutarSQL(sSentencia);
        LlenarTablaTerapia();
        
    }



}
	
private void EliminarTerapeuta() {
		
	if (tablaTerapeuta.getSelectedRow() <0) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un servicio");
        return;
    }

    int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que quieres borrar el servicio seleccionado?");
    
    
    if (resp == 0) {
    	
        String sSentencia = "UPDATE  Terapeuta "
        		+ "set borrado = true "
                + " WHERE id = " + String.valueOf(tablaTerapeuta.getValueAt(tablaTerapeuta.getSelectedRow(),0));

        System.out.println(sSentencia);
        ConnMySQL.EjecutarSQL(sSentencia);
        LlenarTablaTerapeuta();
        
    }
	
	
	}











}
