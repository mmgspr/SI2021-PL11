package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

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
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date dateHoy = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dateIni = dateChooser.getDate();
				Date dateFin = dateChooser_1.getDate();
				if(dateIni==null || dateFin==null) {
					JOptionPane.showMessageDialog(frmVerPagos,"Introduce una fecha inicial y final.","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(dateIni.getTime()-dateFin.getTime()>0) {
					JOptionPane.showMessageDialog(frmVerPagos,"La fecha final no puede ser anterior a la inicial.","Error",JOptionPane.ERROR_MESSAGE);
				}
				String ini=sdf.format(dateIni);
				String fin=sdf.format(dateFin);
				RellenarTabla(table,ini,fin);
			}
		});
		btnNewButton.setBounds(341, 38, 85, 21);
		panel.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(10, 251, 416, -167);
		panel.add(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmVerPagos.dispose();
			}
		});
		btnVolver.setBounds(173, 229, 85, 21);
		panel.add(btnVolver);
		
		
	}
	
	
	public void RellenarTabla(JTable tabla, String Inicio, String Fin) {
		
		
		
		String[][] datosTabla=new String[listaReservas.size()][4];
		listaReservas=modeloReservas.getReservasSocioTodo(id_socio, Inicio, Fin);
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
			System.out.println("FOR\n");
			
		}
		
		
		table.setModel(new DefaultTableModel(
				
				matriz,
				new String[] {
					"Cantidad", "Fecha", "Motivo", "Estado"
				}
				
			));
		
	}
	
	public Window getFrmVerPagos() {
		return this.frmVerPagos;
	}
	
}
