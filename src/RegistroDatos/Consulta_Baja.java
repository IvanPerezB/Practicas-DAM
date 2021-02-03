package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import MetodosBBDD.CargarJTable;
import MetodosBBDD.ConnMySQL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Consulta_Baja extends JDialog { // para dar de baja a un usuario
	/*

    Esta es una ventana para consultas. El tipo de consulta es el que indica el propio titulo de la clase. Por lo general estas ventanas mostrarán una tabla para las busquedas y unos
    campos rellenables, los cuales sirven para hacer filtros en la busqueda.

    En estas clases hay dos metodos diferenciados, uno para llenar la tabla segun se accede a la ventana con todos los datos, pues es la consulta por defecto mostrará todos. El segundo metodo
    sirve para aplicar los filtros en la busqueda y despues actualizará automaticamente la tabla.

    Hay más metodos auxiliares, como el de limpiar los filtros para que los usuarios no pierdan el tiempo en ello. 

    Realmente lo complicado de estas clases son las sentencias, pero las que hay ahora mismo funcionan todas por lo que a menos que hallais realizado cambios en la bbdd



*/

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumSocio;
	private JTextField txtNombre;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private static JTable tBaja;
	private JDateChooser f_baja;
	private static ResultSet rsBaja;
	private String sWhere = "";
	
	
	static ConnMySQL conexionMySQL = new ConnMySQL();
	static CargarJTable cargarTabla = new CargarJTable();
	
	

	public static void main(String[] args) {
		try {
			Consulta_Baja dialog = new Consulta_Baja();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setUndecorated(true);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	public Consulta_Baja() {
		
		
		setBounds(100, 100, 1171, 696);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		

				JButton btnVolver = new JButton("Volver");
				btnVolver.setBounds(996, 612, 149, 34);
				btnVolver.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new Principal().setVisible(true);
						dispose();
					}
				});
				
				contentPanel.setLayout(null);
				btnVolver.setBorder(new RoundedBorder(20));
				btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
				contentPanel.add(btnVolver);
				
				
				

				JButton btnConfirmar = new JButton("Confirmar");
				btnConfirmar.setBounds(996, 567, 149, 34);
				btnConfirmar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						dispose();
					}
				});
				btnConfirmar.setBorder(new RoundedBorder(20));
				btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 16));
				contentPanel.add(btnConfirmar);
				
				JPanel panel_1_1 = new JPanel();
				panel_1_1.setBounds(0, 0, 1364, 252);
				panel_1_1.setLayout(null);
				panel_1_1.setBackground(new Color(51, 153, 255));
				contentPanel.add(panel_1_1);
				
				JPanel panel_1_1_1 = new JPanel();
				panel_1_1_1.setLayout(null);
				panel_1_1_1.setBackground(Color.WHITE);
				panel_1_1_1.setBounds(10, 21, 326, 51);
				panel_1_1.add(panel_1_1_1);
				
				JLabel lblNSocio = new JLabel("N\u00BA SOCIO");
				lblNSocio.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNSocio.setBounds(10, 11, 87, 27);
				panel_1_1_1.add(lblNSocio);
				
				txtNumSocio = new JTextField();
				txtNumSocio.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtNumSocio.setColumns(10);
				txtNumSocio.setBounds(107, 11, 204, 27);
				panel_1_1_1.add(txtNumSocio);
				
				JLabel lblBuscarPor = new JLabel("BUSCAR POR:");
				lblBuscarPor.setBounds(10, 0, 134, 20);
				panel_1_1.add(lblBuscarPor);
				lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JPanel panel_1_1_1_1 = new JPanel();
				panel_1_1_1_1.setLayout(null);
				panel_1_1_1_1.setBackground(Color.WHITE);
				panel_1_1_1_1.setBounds(10, 83, 326, 164);
				panel_1_1.add(panel_1_1_1_1);
				
				JLabel lblNombre = new JLabel("NOMBRE");
				lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNombre.setBounds(10, 11, 87, 27);
				panel_1_1_1_1.add(lblNombre);
				
				txtNombre = new JTextField();
				txtNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtNombre.setColumns(10);
				txtNombre.setBounds(107, 11, 204, 27);
				panel_1_1_1_1.add(txtNombre);
				
				JLabel lblNSocio_1_1 = new JLabel("APELLIDO\r\n");
				lblNSocio_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNSocio_1_1.setBounds(10, 60, 87, 27);
				panel_1_1_1_1.add(lblNSocio_1_1);
				
				txtApellido1 = new JTextField();
				txtApellido1.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtApellido1.setColumns(10);
				txtApellido1.setBounds(107, 60, 204, 27);
				panel_1_1_1_1.add(txtApellido1);
				
				JLabel lblNSocio_1_1_1 = new JLabel("APELLIDO 2\r\n");
				lblNSocio_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				lblNSocio_1_1_1.setBounds(10, 112, 87, 27);
				panel_1_1_1_1.add(lblNSocio_1_1_1);
				
				txtApellido2 = new JTextField();
				txtApellido2.setFont(new Font("Tahoma", Font.BOLD, 14));
				txtApellido2.setColumns(10);
				txtApellido2.setBounds(107, 112, 204, 27);
				panel_1_1_1_1.add(txtApellido2);
				
				JPanel panel_1_1_1_2 = new JPanel();
				panel_1_1_1_2.setLayout(null);
				panel_1_1_1_2.setBackground(Color.WHITE);
				panel_1_1_1_2.setBounds(346, 21, 326, 103);
				panel_1_1.add(panel_1_1_1_2);
				
				JLabel lblFechaDeBaja = new JLabel("FECHA DE BAJA");
				lblFechaDeBaja.setBounds(89, 11, 138, 20);
				panel_1_1_1_2.add(lblFechaDeBaja);
				lblFechaDeBaja.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				f_baja = new JDateChooser();
				f_baja.setBounds(53, 42, 204, 27);
				panel_1_1_1_2.add(f_baja);
				
				JPanel panel_1_1_1_2_2 = new JPanel();
				panel_1_1_1_2_2.setLayout(null);
				panel_1_1_1_2_2.setBackground(Color.WHITE);
				panel_1_1_1_2_2.setBounds(346, 144, 326, 103);
				panel_1_1.add(panel_1_1_1_2_2);
				
				JButton btnBuscar = new JButton("BUSCAR");
				btnBuscar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						BuscarRegistros();
					}
				});
				btnBuscar.setForeground(Color.BLACK);
				btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnBuscar.setBackground(Color.RED);
				btnBuscar.setBounds(40, 28, 244, 47);
				panel_1_1_1_2_2.add(btnBuscar);
				
				JPanel panel_1_1_1_2_2_1 = new JPanel();
				panel_1_1_1_2_2_1.setLayout(null);
				panel_1_1_1_2_2_1.setBackground(Color.WHITE);
				panel_1_1_1_2_2_1.setBounds(767, 83, 326, 103);
				panel_1_1.add(panel_1_1_1_2_2_1);
				
				JButton btnLimpiar = new JButton("LIMPIAR");
				btnLimpiar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						Limpiar();
						LlenarTablaBaja("");
					}
				});
				btnLimpiar.setForeground(Color.BLACK);
				btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 18));
				btnLimpiar.setBackground(Color.RED);
				btnLimpiar.setBounds(40, 28, 244, 47);
				panel_1_1_1_2_2_1.add(btnLimpiar);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(33, 300, 899, 282);
				contentPanel.add(scrollPane);
				
				tBaja = new JTable();
				LlenarTablaBaja("");
				
				scrollPane.setViewportView(tBaja);
				
				setLocationRelativeTo(null);
				
				
				 
				

	}

	

	

	private  void LlenarTablaBaja(String s) {
		
		String sSelect = "SELECT usuario.id, socio.id_socio2, socio.id_socio, usuario.nomb_usu, usuario.apellido1_usu, usuario.apellido2_usu, usuario.f_baja" + 
                " FROM usuario" + 
                " LEFT JOIN socio ON socio.id = usuario.id "+
                " WHERE f_baja IS NOT NULL ";
		
		sSelect= sSelect + sWhere + " ORDER BY f_baja";
		
		rsBaja = ConnMySQL.sSQL(sSelect);
		
		try{
			
			cargarTabla.LlenarTabla(tBaja, sSelect);
			maquearTabla();
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
		
	}
	
	
	private static void maquearTabla() {
		
		
		DefaultTableModel ModelBaja = (DefaultTableModel) tBaja.getModel();
        Object[] titulos= new Object[7];
        titulos[0]="id";	
        titulos[1]="idSocio";
        titulos[2]="NºSocio";
        titulos[3]="Nombre";
        titulos[4]="Apellido 1";
        titulos[5]="Apellido 2";
        titulos[6]="Fecha Baja";
		

      

        tBaja.getColumnModel().getColumn(0).setMaxWidth(0);
        tBaja.getColumnModel().getColumn(0).setMinWidth(0);
        tBaja.getColumnModel().getColumn(0).setPreferredWidth(0);
        tBaja.getColumnModel().getColumn(0).setResizable(false);
        tBaja.getColumnModel().getColumn(1).setMaxWidth(0);
        tBaja.getColumnModel().getColumn(1).setMinWidth(0);
        tBaja.getColumnModel().getColumn(1).setPreferredWidth(0);
        tBaja.getColumnModel().getColumn(1).setResizable(false);
       
        tBaja.getColumnModel().getColumn(2).setMaxWidth(1000);
        tBaja.getColumnModel().getColumn(3).setMaxWidth(1000);
        tBaja.getColumnModel().getColumn(4).setMaxWidth(1000);
        tBaja.getColumnModel().getColumn(5).setMaxWidth(1000);
        tBaja.getColumnModel().getColumn(6).setMaxWidth(1000);
        

       
        tBaja.getColumnModel().getColumn(2).setHeaderValue(titulos[2]);
        tBaja.getColumnModel().getColumn(3).setHeaderValue(titulos[3]);
        tBaja.getColumnModel().getColumn(4).setHeaderValue(titulos[4]);
        tBaja.getColumnModel().getColumn(4).setHeaderValue(titulos[5]);
        tBaja.getColumnModel().getColumn(4).setHeaderValue(titulos[6]);
       


        tBaja.setModel(ModelBaja);
        ModelBaja.fireTableDataChanged();
		
		
	}
	
	private void BuscarRegistros() {
		
		sWhere = "";
		
        
        
		
	
        if ( txtNumSocio.getText() != null && !txtNumSocio.getText().contentEquals("")  ) {
			
			sWhere =  " AND id_socio LIKE  '%" + txtNumSocio.getText() + "%'";
		}
        if ( txtNombre.getText() != null && !txtNombre.getText().contentEquals("")  ) {
			
			sWhere =  " AND nomb_usu LIKE '" + txtNombre.getText() + "%'";
		}
        if ( txtApellido1.getText() != null && !txtApellido1.getText().contentEquals("")  ) {
			
			sWhere =  " AND apellido1_usu LIKE '" + txtApellido1.getText() + "%'";
		}
        if ( txtApellido2.getText() != null && !txtApellido2.getText().contentEquals("")  ) {
			
			sWhere =  " AND apellido2_usu LIKE '" + txtApellido2.getText() + "%'";
		}
		
		if ( f_baja.getDate() != null ) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String sFechaBaja = String.valueOf(sdf.format(f_baja.getDate()));
           
            sWhere =  " AND  f_baja = '" + sFechaBaja + "'" ;
		}
		
		
		
		
				
		System.out.println(sWhere);
		
		LlenarTablaBaja(sWhere);

	}
	
	
	private static class RoundedBorder implements Border {

		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {

			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

			g.drawRoundRect(x, y, width - 1, height - 1, radius + 5, radius + 5);
			// g.fillRoundRect(x, y, width, height, radius+5, radius+5);

		}

	}
	
	private void Limpiar() {
		
		txtNumSocio.setText("");
		txtNombre.setText("");
		txtApellido1.setText("");
		txtApellido2.setText("");
		f_baja.setDate(null);
		
		
	}
}
