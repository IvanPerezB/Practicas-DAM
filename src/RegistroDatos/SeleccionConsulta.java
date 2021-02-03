package RegistroDatos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeleccionConsulta extends JDialog { // desde esta clase se accede a las tablas de consulta que se deseen ver

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionConsulta dialog = new SeleccionConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionConsulta() {
		setBounds(100, 100, 539, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(51, 153, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnConsultas = new JButton("Consulta Asociado Fam");
			btnConsultas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Consulta_Asociado_Familiar().setVisible(true);
					dispose();
				}
			});
			btnConsultas.setForeground(Color.BLACK);
			btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnConsultas.setBackground(new Color(51, 255, 102));
			btnConsultas.setBounds(10, 69, 244, 47);
			contentPanel.add(btnConsultas);
		}
		{
			JButton btnConsultas = new JButton("Consulta Socio Colab");
			btnConsultas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Consultar_Socio_Colaborador().setVisible(true);
					dispose();
				}
			});
			btnConsultas.setForeground(Color.BLACK);
			btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnConsultas.setBackground(new Color(51, 255, 102));
			btnConsultas.setBounds(264, 13, 244, 47);
			contentPanel.add(btnConsultas);
		}
		{
			JButton btnConsultas = new JButton("Consulta Asociado Pag");
			btnConsultas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Tabla_Asociado_pagador().setVisible(true);
					dispose();
				}
			});
			btnConsultas.setForeground(Color.BLACK);
			btnConsultas.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnConsultas.setBackground(new Color(51, 255, 102));
			btnConsultas.setBounds(264, 69, 244, 47);
			contentPanel.add(btnConsultas);
		}
		
		JButton btnConsultaNoSocio = new JButton("Consulta No Socio");
		btnConsultaNoSocio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Consulta_No_Socios().setVisible(true);
			}
		});
		btnConsultaNoSocio.setForeground(Color.BLACK);
		btnConsultaNoSocio.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConsultaNoSocio.setBackground(new Color(51, 255, 102));
		btnConsultaNoSocio.setBounds(10, 13, 244, 47);
		contentPanel.add(btnConsultaNoSocio);
		
		JButton btnConsultaSocio = new JButton("Consulta Socio");
		btnConsultaSocio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Consulta_Socios().setVisible(true);
			}
		});
		btnConsultaSocio.setForeground(Color.BLACK);
		btnConsultaSocio.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConsultaSocio.setBackground(new Color(51, 255, 102));
		btnConsultaSocio.setBounds(140, 127, 244, 47);
		contentPanel.add(btnConsultaSocio);
		
		setLocationRelativeTo(null);
	}
}
