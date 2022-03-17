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
	
	private List<Object[]> listaReservas;
	private Object[][] matriz;
	private Iterator<Object[]> it;
	private int i;
	Object[] vector = new Object[3];

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
		
		RellenarTabla(table, "2022-01-01", "2022-06-01");
	}
	
	public void RellenarTabla(JTable tabla, String Inicio, String Fin) {
		//Modelo de la tabla
		table.setModel(new DefaultTableModel(matriz	,new String[] {
				"Id", 
				"Fecha", 
				"Hora", 
				"Instalación"
				//,"Pagada",  
				}));
		//Obtenemos una lista con las reservas del socio
		listaReservas=modeloReservas.todasReservasSocio(principal.getId_socio());	
		//si la lista no está vacia, mostramos los elementos
		if(!listaReservas.isEmpty()) {
			matriz = new Object[listaReservas.size()][4];					
			it = listaReservas.iterator();				
			i=0;
			while(it.hasNext()) {
				//el vector es el siguiente elemento de la lista (una reserva en concreto del cliente)
				vector=it.next();
				//bucle para recorer el vector
				for(int j=0;j<3;j++) {
					if(j==0)//si es el id
						matriz[i][j]= vector[j];
					else if (j==1) {//la fecha hay que separarla
						String[] a = ((String)vector[j]).split("T");
						matriz[i][j] = a[0];
						matriz[i][j+1]=a[1];
					}
					else//ahora la j va atrasada
						matriz[i][j+1]= vector[j];
				}
				 
				//modificamos el índice para movernos por las filas de la matriz
				i++;
			}
		}
		//sino, indicamos que no hay reservas
		else {
			
		}
		
		
		
	}
	
	public Window getFrmCancelarSocio() {
		// TODO Auto-generated method stub
		return this.frmCancelarReservas;
	}
}
