package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private InscripcionesModel modeloInscripciones = new InscripcionesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();
	private List<Object[]> listaReservas;
	private List<Object[]> listaInscripciones;
	private List<Object[]> listaPagos;
	private String[] arrayPagos;
	private Vector<String> reservasPagadas;
	private DefaultTableModel tableModel;
	
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
				else {
					String ini=sdf.format(dateIni);
					String fin=sdf.format(dateFin);
					System.out.println(ini);
					System.out.println(fin);
					RellenarTabla(table,ini,fin);
				}
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
		
		listaReservas=modeloReservas.getReservasSocioTodo(id_socio, Inicio, Fin);
		String ids=""+id_socio;
		//System.out.println(ids);
		String dni=modeloClientes.getDNI(ids);
		listaInscripciones=modeloInscripciones.getTodasInscripcionesSocio(dni, Inicio, Fin);
		//System.out.println("Entra");
		String[][] datosTabla=new String[listaReservas.size()+listaInscripciones.size()][4];
		Object[][] mRes = new Object[listaReservas.size()][7];
		Iterator<Object[]> iRes = listaReservas.iterator();	
		Object[][] mIns = new Object[listaInscripciones.size()][4];
		Iterator<Object[]> iIns = listaInscripciones.iterator();
		String instal;
		boolean pagado=false;
		int i=0;
		while(iRes.hasNext()) {
			Object[] vector = new Object[7]; 
			vector=iRes.next();
			for(int j=0;j<7;j++) {	
			  mRes[i][j]= vector[j];
			}
			i++;
		}
		int y=0;
		while(iIns.hasNext()) {
			Object[] vector = new Object[4]; 
			vector=iIns.next();
			for(int j=0;j<4;j++) {	
			  mIns[y][j]= vector[j];
			}
			y++;
		}
		for(int k=0;k<i;k++) {
			//Precio
			datosTabla[k][0]=mRes[k][5].toString();
			//Fecha
			datosTabla[k][1]=mRes[k][3].toString();
			
			//Instalacion
			instal=mRes[k][2].toString();
			//System.out.println(instal);
			String instalacion="Reserva instalación: "+modeloInstalaciones.getNombreInstalacion(instal);
			datosTabla[k][2]=instalacion;
			
			//Estado Comprobar si hay pago
			
			pagado=modeloPagos.getPagoReserva(mRes[k][0].toString());
			if(pagado) {
				datosTabla[k][3]="Pagado";
			}
			else {
				datosTabla[k][3]="Pendiente";
			}
			System.out.println(datosTabla[k][3]);
			System.out.println("FOR 1");
		}
		for(int k=i;k<y+i;k++) {
			//Precio Sacar precio ins socio
			//datosTabla[k][0]=mIns[k][5].toString();
			//Fecha
			datosTabla[k][1]=mIns[k][3].toString();
			
			//Actividad Sacar nombre actividad
			String actividad="Inscripción actividad: ";
			datosTabla[k][2]=actividad;
			
			//Estado Comprobar si hay pago
			//datosTabla[k][3]=matriz[k][3].toString();
			System.out.println("FOR 2");
		}
		
		
		table.setModel(new DefaultTableModel(
				
				datosTabla,
				new String[] {
					"Cantidad", "Fecha", "Motivo", "Estado"
				}
				
			));
		
	}
	
	public Window getFrmVerPagos() {
		return this.frmVerPagos;
	}
	
}
