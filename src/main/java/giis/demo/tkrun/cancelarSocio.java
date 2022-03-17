package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class cancelarSocio {

	private ReservasModel modeloReservas = new ReservasModel();
	private JFrame frmCancelarReservas;
	private JTable table;
	private Login principal;
	
	private List<Object[]> listaActividades;
	private Object[][] matriz;
	private Iterator<Object[]> it;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cancelarSocio window = new cancelarSocio();
					window.frmCancelarReservas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cancelarSocio() {
		initialize();
	}
	public cancelarSocio(Login l) {
		initialize();
		principal = l;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarReservas = new JFrame();
		frmCancelarReservas.setTitle("Cancelar reservas");
		frmCancelarReservas.setBounds(100, 100, 450, 300);
		frmCancelarReservas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCancelarReservas.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton EliminarBtn = new JButton("Eliminar");
		EliminarBtn.setBounds(311, 11, 115, 23);
		panel.add(EliminarBtn);
		
		JButton CancelarBtn = new JButton("Cancelar");
		CancelarBtn.setBounds(10, 229, 115, 23);
		panel.add(CancelarBtn);
		
		JButton AceptarBtn = new JButton("Aceptar");
		AceptarBtn.setBounds(311, 229, 115, 23);
		panel.add(AceptarBtn);
		
		JLabel ReservasLbl = new JLabel("Reservas activas:");
		ReservasLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ReservasLbl.setBounds(26, 15, 150, 14);
		panel.add(ReservasLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 45, 376, 146);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//RellenarTabla(table, "2022-01-01", "2022-06-01");
	}
	
	public void RellenarTabla(JTable tabla, String Inicio, String Fin) {
		
		
		listaActividades=modeloReservas.todasReservasSocio(principal.getId_socio());	
	
		matriz = new Object[listaActividades.size()][5];					
		it = listaActividades.iterator();				
		int i=0;
		while(it.hasNext()) {
			Object[] vector = new Object[5]; 
			vector=it.next();
			
			for(int j=0;j<5;j++) {
				
			  matriz[i][j]= vector[j];
			
		}
			i++;
		}
		table.setModel(new DefaultTableModel(matriz	,new String[] {
					"Id", 
					"Fecha", 
					"Hora", 
					"Instalación", 
					"Pagada",  
					}));
		
	}
	
	public Window getFrmCancelarSocio() {
		// TODO Auto-generated method stub
		return this.frmCancelarReservas;
	}
}
