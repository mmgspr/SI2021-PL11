package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class cancelarSocio {

	private ReservasModel modeloReservas = new ReservasModel();
	private JFrame frmCancelarReservas;
	private JTable table;
	private Login principal;
	private int id_socio;
	private JLabel sinReservasLabel;
	
	private List<Object[]> listaReservas;
	private Object[][] matriz;
	private Iterator<Object[]> it;
	private int i;
	private Object[] vector = new Object[3];
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField idTxtField;
	private long hoy;
	

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
		principal = l;
		this.id_socio=this.principal.getId_socio();
		System.out.println(id_socio);
		Calendar cal=Calendar.getInstance();
		hoy = cal.getTime().getTime();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarReservas = new JFrame();
		frmCancelarReservas.setTitle("Cancelar reservas");
		frmCancelarReservas.setBounds(100, 100, 578, 300);
		frmCancelarReservas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmCancelarReservas.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton EliminarBtn = new JButton("Eliminar");
		EliminarBtn.setBounds(439, 13, 115, 23);
		panel.add(EliminarBtn);
		
		JButton CancelarBtn = new JButton("Cancelar");
		CancelarBtn.setBounds(10, 229, 115, 23);
		panel.add(CancelarBtn);
		
		JButton AceptarBtn = new JButton("Aceptar");
		AceptarBtn.setBounds(439, 229, 115, 23);
		panel.add(AceptarBtn);
		
		JLabel ReservasLbl = new JLabel("Reservas activas:");
		ReservasLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ReservasLbl.setBounds(26, 15, 150, 14);
		panel.add(ReservasLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 45, 528, 146);
		panel.add(scrollPane);
		
		sinReservasLabel = new JLabel("Parece que no tienes ninguna reserva para cancelar...");
		sinReservasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sinReservasLabel.setBounds(26, 202, 346, 14);
		sinReservasLabel.setVisible(false);
		panel.add(sinReservasLabel);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		RellenarTabla(table);
		table.setEnabled(false);
		
		idTxtField = new JTextField();
		idTxtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() < '0' || e.getKeyChar() > '9') {
					e.setKeyChar((char)127);
				}
			}
		});
		idTxtField.setBounds(317, 14, 96, 20);
		panel.add(idTxtField);
		idTxtField.setColumns(10);
		
		JLabel idLbl = new JLabel("Id de reserva:");
		idLbl.setBounds(317, 0, 96, 14);
		panel.add(idLbl);
		
		
		
		
	}
	
	public void RellenarTabla(JTable tabla) {
		
		//Obtenemos una lista con las reservas del socio
		listaReservas=modeloReservas.todasReservasSocio(id_socio);	
		System.out.println(listaReservas.size());
		//si la lista no está vacia, mostramos los elementos
		if(!listaReservas.isEmpty()) {
			matriz = new Object[listaReservas.size()][4];					
			it = listaReservas.iterator();				
			i=0;
			while(it.hasNext()) {
				//el vector es el siguiente elemento de la lista (una reserva en concreto del cliente)
				vector=it.next();
				try {
					if (hoy+432000000 < sdf.parse(vector[1].toString()).getTime()) {
						//bucle para recorer el vector
						for(int j=0;j<3;j++) {
							if(j==0)//si es el id
								matriz[i][j]= vector[j];
							else if (j==1) {//la fecha hay que separarla
								String[] a = (vector[j].toString()).split("T");
								matriz[i][j] = a[0];
								matriz[i][j+1]=a[1];
							}
							else//ahora la j va atrasada
								matriz[i][j+1]= vector[j];
						}
						//modificamos el índice para movernos por las filas de la matriz
						i++;
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//sino, indicamos que no hay reservas
		else {
			sinReservasLabel.setVisible(true);
		}
		
		//Modelo de la tabla
				table.setModel(new DefaultTableModel(matriz	,new String[] {
						"Id", 
						"Fecha", 
						"Hora", 
						"Instalación"
						//,"Pagada",  
						}));
		
		
		
	}
	
	public Window getFrmCancelarSocio() {
		// TODO Auto-generated method stub
		return this.frmCancelarReservas;
	}
}
