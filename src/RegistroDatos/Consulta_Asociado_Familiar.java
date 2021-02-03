package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class Consulta_Asociado_Familiar extends JDialog {
	/*

    Esta es una ventana para consultas. El tipo de consulta es el que indica el propio titulo de la clase. Por lo general estas ventanas mostrarán una tabla para las busquedas y unos
    campos rellenables, los cuales sirven para hacer filtros en la busqueda.

    En estas clases hay dos metodos diferenciados, uno para llenar la tabla segun se accede a la ventana con todos los datos, pues es la consulta por defecto mostrará todos. El segundo metodo
    sirve para aplicar los filtros en la busqueda y despues actualizará automaticamente la tabla.

    Hay más metodos auxiliares, como el de limpiar los filtros para que los usuarios no pierdan el tiempo en ello. 

    Realmente lo complicado de estas clases son las sentencias, pero las que hay ahora mismo funcionan todas por lo que a menos que hallais realizado cambios en la bbdd



*/

	private final JPanel contentPanel = new JPanel();
	private JTable tAsociadoFamiliar;
	CargarJTable cargarTabla = new CargarJTable();
	private static ResultSet rsAsociadoFamiliar;
	String sWhere ="";
	private JTextField txtNombeAF;
	private JTextField txtApellido1AF;
	private JTextField txtApellido2AF;
	private JTextField txtProfesionAF;
	private JTextField txtEmailAF;
	private JTextField txtMovil1AF;
	private JTextField txtRelacionAF;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Consulta_Asociado_Familiar dialog = new Consulta_Asociado_Familiar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Consulta_Asociado_Familiar() {
		setBounds(100, 100, 934, 636);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 899, 413);
		contentPanel.add(scrollPane);
		
		tAsociadoFamiliar = new JTable();
		LlenarTablaAsociadoFamiliar("");
		scrollPane.setViewportView(tAsociadoFamiliar);
		
		txtNombeAF = new JTextField();
		txtNombeAF.setColumns(10);
		txtNombeAF.setBounds(10, 64, 128, 22);
		contentPanel.add(txtNombeAF);
		
		txtApellido1AF = new JTextField();
		txtApellido1AF.setColumns(10);
		txtApellido1AF.setBounds(137, 64, 128, 22);
		contentPanel.add(txtApellido1AF);
		
		txtApellido2AF = new JTextField();
		txtApellido2AF.setColumns(10);
		txtApellido2AF.setBounds(266, 64, 128, 22);
		contentPanel.add(txtApellido2AF);
		
		txtProfesionAF = new JTextField();
		txtProfesionAF.setColumns(10);
		txtProfesionAF.setBounds(393, 64, 128, 22);
		contentPanel.add(txtProfesionAF);
		
		txtEmailAF = new JTextField();
		txtEmailAF.setColumns(10);
		txtEmailAF.setBounds(520, 64, 128, 22);
		contentPanel.add(txtEmailAF);
		
		txtMovil1AF = new JTextField();
		txtMovil1AF.setColumns(10);
		txtMovil1AF.setBounds(648, 64, 128, 22);
		contentPanel.add(txtMovil1AF);
		
		txtRelacionAF = new JTextField();
		txtRelacionAF.setColumns(10);
		txtRelacionAF.setBounds(776, 64, 128, 22);
		contentPanel.add(txtRelacionAF);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(new Color(51, 255, 102));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarRegistros();
			}
		});
		btnBuscar.setBounds(776, 11, 128, 25);
		contentPanel.add(btnBuscar);
		
		JButton btnLimpiar = new JButton("Limpiar\r\n");
		btnLimpiar.setBackground(new Color(51, 255, 102));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LimpiarCampos();
			}
		});
		btnLimpiar.setBounds(638, 11, 128, 25);
		contentPanel.add(btnLimpiar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBackground(new Color(51, 153, 255));
		btnVolver.setBounds(776, 543, 123, 34);
		contentPanel.add(btnVolver);
	}
	
	public void LlenarTablaAsociadoFamiliar(String sWhere) {

		String sSelect = "SELECT id, nom_asoc_fam2, apellido1_asoc_fam2, apellido2_asoc_fam2,"
				+ "profe_asoc_fam2, email_asoc_fam2, movil1_asoc_fam2,"
				+ "relac_asoc_fam2 FROM AsociadoFamiliar";
		sSelect = sSelect + sWhere + " ORDER BY nom_asoc_fam2 ";
		rsAsociadoFamiliar = ConnMySQL.sSQL(sSelect);

		try {
			cargarTabla.LlenarTabla(tAsociadoFamiliar, sSelect);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		MaquearTabla();

	}
	
	private void MaquearTabla() {
		DefaultTableModel ModeltAsociadoFamiliar= (DefaultTableModel) tAsociadoFamiliar.getModel();
			
		
		Object[] titulos= new Object[8];
		titulos[0]="id";
		titulos[1]="Nombre A.F.";
		titulos[2]="Primer Apellido A.F.";
		titulos[3]="Segundo Apellido A.F.";
		titulos[4]="Profesion A.F.";
		titulos[5]="Email A.F.";
		titulos[6]="Movil 1 A.F.";
		titulos[7]="Relacion A.F.";
		
		
		
		tAsociadoFamiliar.getColumnModel().getColumn(0).setMaxWidth(0);
		tAsociadoFamiliar.getColumnModel().getColumn(0).setMinWidth(0);
		tAsociadoFamiliar.getColumnModel().getColumn(0).setPreferredWidth(0);
		tAsociadoFamiliar.getColumnModel().getColumn(0).setResizable(false);
		tAsociadoFamiliar.getColumnModel().getColumn(1).setMaxWidth(1000);
		tAsociadoFamiliar.getColumnModel().getColumn(2).setMaxWidth(1000);
		tAsociadoFamiliar.getColumnModel().getColumn(3).setMaxWidth(1000);
		tAsociadoFamiliar.getColumnModel().getColumn(4).setMaxWidth(1000);
		tAsociadoFamiliar.getColumnModel().getColumn(5).setMaxWidth(1000);
		tAsociadoFamiliar.getColumnModel().getColumn(6).setMaxWidth(1000);
		tAsociadoFamiliar.getColumnModel().getColumn(7).setMaxWidth(1000);
		
		
		tAsociadoFamiliar.getColumnModel().getColumn(1).setHeaderValue(titulos[1]);
		tAsociadoFamiliar.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
		tAsociadoFamiliar.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
		tAsociadoFamiliar.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
		tAsociadoFamiliar.getColumnModel().getColumn(5).setHeaderValue(titulos[5]);
		tAsociadoFamiliar.getColumnModel().getColumn(6).setHeaderValue(titulos[6]);
		tAsociadoFamiliar.getColumnModel().getColumn(7).setHeaderValue(titulos[7]);
		
		
		
		tAsociadoFamiliar.setModel(ModeltAsociadoFamiliar);
	}
	
	private void BuscarRegistros() {

		sWhere="";
		
		if (txtNombeAF.getText() != null && !txtNombeAF.getText().contentEquals("")) {
			sWhere = sWhere + " nom_asoc_fam2 LIKE ( \"" + txtNombeAF.getText() + "%\")";
		}

		if (txtApellido1AF.getText() != null && !txtApellido1AF.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido1_asoc_fam2 LIKE ( \"" + txtApellido1AF.getText() + "%\")";
		}

		if (txtApellido2AF.getText() != null && !txtApellido2AF.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " apellido2_asoc_fam2 LIKE ( \"" + txtApellido2AF.getText() + "%\")";
		}

		if (txtProfesionAF.getText() != null && !txtProfesionAF.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " profe_asoc_fam2 LIKE ( \"" + txtProfesionAF.getText() + "%\")";
		}
		
		if (txtEmailAF.getText() != null && !txtEmailAF.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " email_asoc_fam2 LIKE ( \"" + txtEmailAF.getText() + "%\")";
		}
		
		if (txtMovil1AF.getText() != null && !txtMovil1AF.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " movil1_asoc_fam2 LIKE ( \"" + txtMovil1AF.getText() + "%\")";
		}		
		
		
		if (txtRelacionAF.getText() != null && !txtRelacionAF.getText().contentEquals("")) {
			if (sWhere != null && !sWhere.contentEquals("")) {
				sWhere = sWhere + " AND ";
			}
			sWhere = sWhere + " relac_asoc_fam2 LIKE ( \"" + txtRelacionAF.getText() + "%\")";
		}

		if (sWhere != null && !sWhere.contentEquals("")) {
			sWhere = " WHERE " + sWhere;
		}

		System.out.println(sWhere);

		LlenarTablaAsociadoFamiliar(sWhere);

	}
	
	protected void LimpiarCampos() {

        txtNombeAF.setText("");
        txtApellido1AF.setText("");
        txtApellido2AF.setText("");
        txtProfesionAF.setText("");
        txtEmailAF.setText("");
        txtMovil1AF.setText("");
        txtRelacionAF.setText("");
        
        LlenarTablaAsociadoFamiliar("");

    }
}
