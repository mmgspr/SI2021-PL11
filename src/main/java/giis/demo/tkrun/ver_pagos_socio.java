package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ver_pagos_socio {

	private JFrame frmVerPagos;
	private JTable table;
	private Login vLogin;
	private ReservasModel modeloReservas = new ReservasModel();
	private PagosModel modeloPagos = new PagosModel();
	private ClientesModel modeloClientes = new ClientesModel();
	private List<Object[]> listaReservas;
	private List<Object[]> listaPagos;
	private String[] arrayPagos;
	private Vector<String> reservasPagadas;
	
	int id_socio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ver_pagos_socio window = new ver_pagos_socio();
					window.frmVerPagos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ver_pagos_socio() {
		initialize();
	}
	
	public ver_pagos_socio(Login login) {
		initialize();
		this.vLogin = login;
		this.id_socio=this.vLogin.getId_socio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVerPagos = new JFrame();
		frmVerPagos.setTitle("Ver Pagos");
		frmVerPagos.setBounds(100, 100, 450, 300);
		frmVerPagos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmVerPagos.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblFechaInicial = new JLabel("• Fecha inicial:");
		lblFechaInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaInicial.setBounds(10, 10, 91, 14);
		panel.add(lblFechaInicial);
		
		JLabel lblFechaFinal = new JLabel("• Fecha final:");
		lblFechaFinal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaFinal.setBounds(10, 39, 91, 14);
		panel.add(lblFechaFinal);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 10, 135, 19);
		panel.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(111, 39, 135, 19);
		panel.add(dateChooser_1);
		
		JButton btnNewButton = new JButton("Ver");
		btnNewButton.setBounds(341, 38, 85, 21);
		panel.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(10, 251, 416, -167);
		panel.add(table);
		
		
	}
	
	
	public void RellenarTablas(JTable tabla, String Inicio, String Fin) {
		
		/*
		List<Object[]> listaActividades=modeloReservas.getActividadPeriodo(Inicio, Fin);	
		Object[][] matriz = new Object[listaActividades.size()][3];					
		Iterator<Object[]> iterador = listaActividades.iterator();				
		int i=0;
		while(iterador.hasNext()) {
			Object[] vector = new Object[3]; 
			vector=iterador.next();
			for(int j=0;j<3;j++) {	
			  matriz[i][j]= vector[j];
		}
			i++;
		}
		*/
		String[][] datosTabla=new String[listaReservas.size()][4];
		listaReservas=modeloReservas.getReservasSocioTodo(id_socio);
		Object[][] matriz = new Object[listaReservas.size()][7];
		Iterator<Object[]> iterador = listaReservas.iterator();				
		int i=0;
		while(iterador.hasNext()) {
			Object[] vector = new Object[7]; 
			vector=iterador.next();
			for(int j=0;j<7;j++) {	
			  matriz[i][j]= vector[j];
		}
			i++;
		}
		for(int k=0;k<i;k++) {
			datosTabla[k][0]=matriz[k][5].toString();
			datosTabla[k][1]=matriz[k][3].toString();
			
		}
		
		/*
		table.setModel(new DefaultTableModel(
				
				matriz,
				new String[] {
					"Cantidad", "Fecha", "Motivo", "Estado"
				}
				
			));
		*/
	}
	
}
