package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Window;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cancelarSocio {

	private ReservasModel modeloReservas = new ReservasModel();
	private PagosModel modeloPagos = new PagosModel();
	private ClientesModel modeloClientes = new ClientesModel();

	private JFrame frmCancelarReservas;
	private JTable table;
	private Login principal;
	private int id_socio;
	private String dni;
	private JLabel sinReservasLabel;
	
	private List<Object[]> listaReservas;
	private List<Object[]> listaPagos;
	private String[] arrayPagos;
	private Vector<String> reservasPagadas;
	private Vector<String> reservasCancelables;
	private Object[][] matriz;
	private String[][] matriz2;
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
		this.dni = modeloClientes.getDNI(Integer.toString(id_socio));
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
		
		JButton EliminarBtn = new JButton("Eliminar");
		EliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idReserva;
				if(!idTxtField.getText().equals("")) {
					idReserva = idTxtField.getText();
					//se comprueba que el id introducido es válido
					try {
						if((modeloReservas.existeReserva(Integer.parseInt(idReserva))) 
						&& (modeloReservas.getCliente(idReserva).toString().equals(Integer.toString(id_socio)))
						&& (reservasCancelables.contains(idReserva))) {
							if(reservasPagadas.contains(idReserva)) {
													
								double cuota = modeloReservas.nuevaCuota(id_socio);
								double devolver = modeloReservas.getPrecio(Integer.parseInt(idReserva));
								modeloReservas.añadeacuota(cuota-devolver, id_socio);
								//Eliminar pago
								modeloPagos.eliminarPagoReserva(idReserva);
								//Eliminar reserva
								modeloReservas.eliminarReserva(idReserva);
								RellenarTabla(table);
								
								JOptionPane.showMessageDialog(frmCancelarReservas,
									    "Reserva eliminada, y se restarán "+devolver+" euros de su cuota de fin de mes.");
							}
							else {
								//Se elimina la reserva sin devolver el dinero
								modeloReservas.eliminarReserva(idReserva);
								RellenarTabla(table);
							}
						}
						else {
							JOptionPane.showMessageDialog(frmCancelarReservas,
								    "Introduzca un id de reserva válido.",
								    "Error de id",
								    JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton AceptarBtn = new JButton("Aceptar");
		AceptarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCancelarReservas.dispose();
			}
		});
		
		JLabel ReservasLbl = new JLabel("Reservas activas:");
		ReservasLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		sinReservasLabel = new JLabel("Parece que no tienes ninguna reserva para cancelar...");
		sinReservasLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sinReservasLabel.setVisible(false);
		
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
		idTxtField.setColumns(10);
		
		JLabel idLbl = new JLabel("Id de reserva:");
		GroupLayout groupLayout = new GroupLayout(frmCancelarReservas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addComponent(ReservasLbl, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(141)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(idLbl, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addComponent(idTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(EliminarBtn, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addComponent(sinReservasLabel, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(439, Short.MAX_VALUE)
					.addComponent(AceptarBtn, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(15)
							.addComponent(ReservasLbl, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(idLbl)
							.addComponent(idTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(13, Short.MAX_VALUE)
							.addComponent(EliminarBtn)))
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(sinReservasLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(AceptarBtn)
					.addGap(11))
		);
		panel.setLayout(gl_panel);
		frmCancelarReservas.getContentPane().setLayout(groupLayout);
		
		
		
		
	}
	
	public void RellenarTabla(JTable tabla) {
		//Obtenemos una lista con los pagos del socio
		listaPagos = modeloPagos.getPagosCliente(dni);
		//pasamos esa lista a un array
		arrayPagos = new String[listaPagos.size()];
		reservasPagadas = new Vector<String>();
		reservasCancelables = new Vector<String>();
		for (int i=0;i<listaPagos.size();i++) {
			arrayPagos[i] = listaPagos.get(i)[0].toString();
			reservasPagadas.add(modeloPagos.getReserva(arrayPagos[i]));
		}
		
		
				
		//Obtenemos una lista con las reservas del socio
		listaReservas=modeloReservas.todasReservasSocio(id_socio);	
		//si la lista no está vacia, mostramos los elementos
		if(!listaReservas.isEmpty()) {
			matriz = new Object[listaReservas.size()][5];					
			it = listaReservas.iterator();				
			i=0;
			while(it.hasNext()) {
				//el vector es el siguiente elemento de la lista (una reserva en concreto del cliente)
				vector=it.next();
				try {
					if (hoy+(principal.getDiasAntelacion()*24*60*60*1000) < sdf.parse(vector[1].toString()).getTime()) {
						reservasCancelables.add(vector[0].toString());
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
						if(reservasPagadas.contains(vector[0].toString())) 
							matriz[i][4] = "Sí";
						else
							matriz[i][4] = "No";
						//modificamos el índice para movernos por las filas de la matriz
						i++;
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//copia de las primeras i filas de matriz a matriz2 para que no queden filas en blanco
			matriz2 = new String[i][5];
			for(int j = 0; j<i; j++) {
				for(int k = 0; k<5; k++) {
					matriz2[j][k] = matriz[j][k].toString();
				}
			}
			
		}
		//sino, indicamos que no hay reservas
		else {
			sinReservasLabel.setVisible(true);
		}
		
		//Modelo de la tabla
				table.setModel(new DefaultTableModel(matriz2,new String[] {
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
