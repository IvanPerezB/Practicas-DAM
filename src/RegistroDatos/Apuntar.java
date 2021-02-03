package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import MetodosBBDD.CargarCombo;
import MetodosBBDD.CargarJTable;
import MetodosBBDD.Item;
import MetodosBBDD.ConnMySQL;

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
import java.text.ParseException;
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

public class Apuntar extends JDialog { // en esta clase apuntamos a un usuario cualquiera o socio a una terapia  y tambien le podremos ajustar una beca que se aplicara a los pagos

	private final JPanel contentPanel = new JPanel();

	
	private static JDateChooser dcFechaPedido = new JDateChooser();
	
	private static JComboBox cmbTerapia = new JComboBox();

	
	
	private static  JTable tApuntar;
	
	private static DefaultTableModel modelTApuntar = new DefaultTableModel() {
		@Override 
		public boolean isCellEditable(int row, int column) { 
			return false; 
		}
	};

	
	private int regSelect;
	
	
	// Utilizaremos esta variable para considerar si estamos en un nuevo pedido o modificando uno existente:
	//  -1 --> Nuevo,    >0 --> Existente
	private static int idPedidoSelect = -1; 
	
	
	CargarCombo cargarCboTerapias = new CargarCombo();
	static CargarJTable cargarTabla = new CargarJTable();
	static ResultSet rsTerapias = null;
	boolean rsTotal ;
	
	
	static ConnMySQL conexionMySQL = new ConnMySQL();
	String sWhere = "";
	JTextField txtNombre;
	JTextField txtApellido1;
	JTextField txtDNI;
	private JSpinner spinner;
	private JTextField textPrecio;
	
	private JButton btnGuardar;
	private JButton btnEliminar;
	JTextField txtTotal;
	JTextField txtId;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Apuntar dialog = new Apuntar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Apuntar() {
		setBounds(100, 100, 940, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		JLabel lblProductos = new JLabel("Pedidos");
		lblProductos.setForeground(new Color(255, 102, 51));
		lblProductos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblProductos.setBounds(679, 11, 174, 30);
		
		
		JLabel lblTitulo = new JLabel("Brico Plym. ");
		lblTitulo.setBounds(10, 10, 245, 39);
		lblTitulo.setForeground(new Color(255, 204, 51));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));

		
		JButton btnNuevo = new JButton("");
		btnNuevo.setBounds(750, 65, 19, 19);

				
		
		JButton btnBorrar = new JButton("");
		btnBorrar.setBounds(770, 65, 19, 19);
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BorrarPedido();
			}
		});
						

		JLabel lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(278, 54, 111, 13);
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setForeground(new Color(255, 255, 255));
		lblDireccion.setHorizontalAlignment(SwingConstants.LEFT);

		
		JLabel lblObservaciones = new JLabel("Observaciones");
		lblObservaciones.setBounds(33, 137, 131, 19);
		lblObservaciones.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaciones.setForeground(new Color(255, 255, 255));
		lblObservaciones.setHorizontalAlignment(SwingConstants.LEFT);
		{
			JLabel lblNombreCliente = new JLabel("Cliente");
			lblNombreCliente.setBounds(33, 54, 62, 13);
			lblNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNombreCliente.setForeground(new Color(255, 255, 255));
			lblNombreCliente.setHorizontalAlignment(SwingConstants.LEFT);
		}
		
		JLabel lblCP = new JLabel("C.P.");
		lblCP.setHorizontalAlignment(SwingConstants.LEFT);
		lblCP.setForeground(Color.WHITE);
		lblCP.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCP.setBounds(524, 54, 53, 13);

		
		JLabel lblTipoPago = new JLabel("Tipo pago");
		lblTipoPago.setBounds(278, 113, 98, 13);
		lblTipoPago.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipoPago.setForeground(new Color(255, 255, 255));
		lblTipoPago.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblNif = new JLabel("N.I.F./DNI");
		lblNif.setHorizontalAlignment(SwingConstants.LEFT);
		lblNif.setForeground(Color.WHITE);
		lblNif.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNif.setBounds(33, 96, 62, 13);
		

		
		dcFechaPedido.setBounds(699, 131, 131, 19);
		Date fHoy = new Date();
		dcFechaPedido.setDate(fHoy);
		
		JLabel lblFechaPedido = new JLabel("Fecha Pedido");
		lblFechaPedido.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaPedido.setForeground(Color.WHITE);
		lblFechaPedido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaPedido.setBounds(605, 137, 84, 13);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 255));
		panel.setBounds(26, 13, 874, 167);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblEligeunServicio = new JLabel("Elige un servicio");
		lblEligeunServicio.setBounds(25, 101, 131, 19);
		panel.add(lblEligeunServicio);
		lblEligeunServicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblEligeunServicio.setForeground(new Color(0, 0, 0));
		lblEligeunServicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbTerapia.setBounds(166, 99, 195, 21);
		panel.add(cmbTerapia);
		cmbTerapia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SeleccionarTerapia();
			}
		});
		
		
		CargarComboTerapias();
		
		JLabel lblNumSocio = new JLabel("");
		lblNumSocio.setBounds(159, 173, 113, 16);
		panel.add(lblNumSocio);
		
		 spinner = new JSpinner();
		spinner.setBounds(777, 101, 30, 22);
		panel.add(spinner);
		
		JLabel lblNDeSesiones = new JLabel("N\u00BA de Sesiones");
		lblNDeSesiones.setHorizontalAlignment(SwingConstants.LEFT);
		lblNDeSesiones.setForeground(Color.BLACK);
		lblNDeSesiones.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNDeSesiones.setBounds(662, 101, 131, 19);
		panel.add(lblNDeSesiones);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(108, 13, 119, 19);
		panel.add(txtNombre);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(23, 16, 98, 13);
		panel.add(lblNombre);
		
		txtApellido1 = new JTextField();
		txtApellido1.setEditable(false);
		txtApellido1.setColumns(10);
		txtApellido1.setBounds(392, 16, 119, 19);
		panel.add(txtApellido1);
		
		JLabel lblPrimerApellido = new JLabel("Primer Apellido");
		lblPrimerApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrimerApellido.setForeground(Color.BLACK);
		lblPrimerApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrimerApellido.setBounds(255, 16, 125, 13);
		panel.add(lblPrimerApellido);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setColumns(10);
		txtDNI.setBounds(635, 16, 119, 19);
		panel.add(txtDNI);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.LEFT);
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDni.setBounds(569, 19, 98, 13);
		panel.add(lblDni);
		
		JButton btnAgregar = new JButton("A\u00F1adir");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AgregarTerapia();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAgregar.setBounds(747, 64, 97, 25);
		panel.add(btnAgregar);
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setColumns(10);
		textPrecio.setBounds(492, 101, 119, 19);
		panel.add(textPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecio.setBounds(407, 104, 98, 13);
		panel.add(lblPrecio);
		
		 btnGuardar = new JButton("Guardar");
		 btnGuardar.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		try {
					GuardarRegistro();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 	}
		 });
		btnGuardar.setBounds(648, 65, 89, 23);
		panel.add(btnGuardar);
		
		 btnEliminar = new JButton("Eliminar");
		 btnEliminar.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		BorrarRegistro();
		 	}
		 });
		btnEliminar.setBounds(537, 65, 89, 23);
		panel.add(btnEliminar);
		
		JButton btnBecar = new JButton("Becar a un usuario");
		btnBecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tApuntar.getSelectedRow()>=0) { // aqui pasamos los datos de la tabla a la hora de clickar en esta
					
					try {
						String Id = String.valueOf(txtId.getText());
						String Nombre = String.valueOf(txtNombre.getText());
						String PrimerApellido = String.valueOf(txtApellido1.getText());
						String DNI = String.valueOf(txtDNI.getText());
						String Total = String.valueOf(txtTotal.getText());
						
						
						
						Becados miBeca =new Becados();
						
						miBeca.txtNombreB.setText(Nombre);
						miBeca.txtApellido1B.setText(PrimerApellido);
						miBeca.txtDNIB.setText(DNI);
						miBeca.txtTotalB.setText(Total);
						miBeca.txtIdB.setText(Id);
						miBeca.setVisible(true);
					
					}catch (Exception e) {
						JOptionPane.showInputDialog(this, "Esta vacio");
					}
				}
				else {
					JOptionPane.showInputDialog(this, "Debe seleccionar una fila");
				}
			}
		});
		btnBecar.setBounds(648, 133, 188, 25);
		panel.add(btnBecar);
		
		txtId = new JTextField();
		txtId.setBounds(140, 138, 116, 22);
		panel.add(txtId);
		txtId.setColumns(10);
		txtId.setVisible(false);
		

		
		
		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(26, 193, 874, 299);
		contentPanel.add(scrollPaneProductos);
		
		//RecogerDatos();
		tApuntar = new JTable();
		scrollPaneProductos.setViewportView(tApuntar);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(751, 523, 149, 22);
		contentPanel.add(txtTotal);
		txtTotal.setColumns(10);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(627, 523, 98, 22);
		contentPanel.add(lblTotal);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(803, 565, 97, 25);
		contentPanel.add(btnVolver);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Principal().setVisible(true);
				
			}
		});
		
		modelTApuntar = new DefaultTableModel();
		
		
		
		MaquearTabla();
		
	
	}
	


	protected  void MaquearTabla() { //Sirve
		
		tApuntar.setModel(modelTApuntar);
		
		modelTApuntar.addColumn("IdTerapia");
		modelTApuntar.addColumn("Nombre");		
		modelTApuntar.addColumn("Primer Apellido");	
		modelTApuntar.addColumn("DNI");	
		modelTApuntar.addColumn("Terapia");
		modelTApuntar.addColumn("Nº de Seiones");
		modelTApuntar.addColumn("Precio");
		
		
		tApuntar.getColumnModel().getColumn(0).setMaxWidth(0);
		tApuntar.getColumnModel().getColumn(0).setMinWidth(0);
		tApuntar.getColumnModel().getColumn(0).setPreferredWidth(0);
		tApuntar.getColumnModel().getColumn(0).setResizable(false);
		
		tApuntar.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		tApuntar.getColumnModel().getColumn(2).setPreferredWidth(100);
	
		tApuntar.getColumnModel().getColumn(3).setPreferredWidth(80);
		
		tApuntar.getColumnModel().getColumn(4).setPreferredWidth(30);
		
		tApuntar.getColumnModel().getColumn(5).setPreferredWidth(20);
		
		tApuntar.getColumnModel().getColumn(6).setPreferredWidth(10);
		
		
	}

	


	protected  void SeleccionarTerapia() { //Sirve

        if (cmbTerapia.getSelectedIndex() != 0) {
        	

            Item item = (Item) cmbTerapia.getSelectedItem();
            System.out.println(item.getId() + " " + item.getDescription());

            String sSelect = "SELECT id_tera,nombre_terap, precio FROM Terapia WHERE id_tera = "
                    + item.getId();
            rsTerapias = ConnMySQL.sSQL(sSelect);
            
            try {
				while (rsTerapias.next()) {
					
					textPrecio.setText(rsTerapias.getString(3));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        
    }

	private void CargarComboTerapias() { //Sirve
        String sSelect = "SELECT id_tera, nombre_terap,precio,reglas FROM Terapia WHERE borrado = false ORDER BY nombre_terap";
        CargarCombo.LlenarCombo(cmbTerapia, sSelect);
       
    }

	
	

	protected void AgregarTerapia() throws SQLException { //Sirve
	
		
		Item item = (Item) cmbTerapia.getSelectedItem();
		System.out.println(item.getId() + " " + item.getDescription());

		float fPrecio = Float.parseFloat(textPrecio.getText());
		int fCantidad = (int) spinner.getValue();
		
		int  PTotalTerapia= (int) (fPrecio *fCantidad); // Sale el precio de la terapia en funcion de las sesiones escogidas

		if(fCantidad == 0) {
			JOptionPane.showMessageDialog(null, "Debe de introducir un minimo de número de sesiones");
		}else {
		
		Object[] object = new Object[7];
		object[0] = item.getId();
		object[1] = txtNombre.getText();
		object[2] = txtApellido1.getText();
		object[3] = txtDNI.getText();
		object[4] = item.getDescription();
		object[5] = spinner.getValue();
		object[6] = PTotalTerapia;
		
		 
		modelTApuntar.addRow(object);
		
		ObtenerTotalPedido();

		}
			
	}
	
	
	
	
	protected void GuardarRegistro() throws SQLException {  //Hay que insertar los datos
		
		Item item = (Item) cmbTerapia.getSelectedItem();
		String sSentencia = "INSERT INTO Registro_Servicio(id_tera,ini_terap,tarif_mens,num_sesio_mens) VALUES("+"'" + item.getId()+ "',"+"CURTIME(),"+"'" + txtTotal.getText()+ "'," +"'" + spinner.getValue()+ "')" ;
        rsTotal = ConnMySQL.EjecutarSQL(sSentencia); 
        
        String sSelect4="INSERT INTO pagos (id_pagos,id,total_pagos) VALUES (id_pagos, (SELECT id from usuario WHERE id = " + txtId.getText() + "),'" + txtTotal.getText() +"')";

        rsTotal = ConnMySQL.EjecutarSQL(sSelect4);
		
		
	}
	
	protected void BorrarRegistro() {  // Hay que mirar como borrar todo bien
		
		Item item = null;
		if (item.getId() > 0) {
			
			
			int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que quieres borrar el servicio seleccionado?");

			if (resp == 0) {
				String sSentencia = "DELETE FROM usuario " + " WHERE id_tera = " + item.getId() ;

				System.out.println(sSentencia);
				ConnMySQL.EjecutarSQL(sSentencia);

			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar el servicio que desea borrar.");
		}

	}
	
	public double ObtenerTotalPedido() {

        double dTotal = 0;
        for (int i = 0; i <= tApuntar.getRowCount() - 1; i++) {
            dTotal = dTotal + Double.parseDouble(String.valueOf(tApuntar.getValueAt(i, 6)));
        }
        txtTotal.setText(String.valueOf(dTotal));
		return dTotal;
          
    }
}
