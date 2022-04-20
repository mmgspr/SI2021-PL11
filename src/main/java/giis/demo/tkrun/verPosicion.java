package giis.demo.tkrun;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

public class verPosicion {

	private JFrame frame;
	private JTable table;
	private JLabel SinInscripcionesLabel;
	
	private InscripcionesModel modeloInscripciones = new InscripcionesModel();
	private EsperasModel modeloEsperas = new EsperasModel();
	private ClientesModel modeloClientes = new ClientesModel();
	
	private int id_socio;
	private Login principal;
	private String hoy;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Object[][] matriz;
	private Object[][] matriz2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					verPosicion window = new verPosicion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public verPosicion() {
		initialize();
	}
	public verPosicion(Login p) {
		principal = p;
		this.id_socio=this.principal.getId_socio();
		Calendar cal=Calendar.getInstance();
		hoy = sdf.format(cal.getTime());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 683, 194);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		SinInscripcionesLabel = new JLabel("Usted no tiene ninguna incripción para comprobar.");
		SinInscripcionesLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SinInscripcionesLabel.setBounds(10, 226, 416, 14);
		SinInscripcionesLabel.setVisible(false);
		panel.add(SinInscripcionesLabel);
		
		RellenarTabla(table);
	}
	
	public void RellenarTabla(JTable tabla) {
		String dni = modeloClientes.getDNI(Integer.toString(id_socio));
		//Obtenemos una lista con las inscripciones del socio
		List<Object[]> listaInscripciones = modeloInscripciones.getTodasInscripcionesSocio2(dni, hoy);	
		List<Object[]> listaEsperas = modeloEsperas.getTodasEsperasSocio2(dni, hoy);	
			
		//si la lista no está vacia, mostramos los elementos
		if(listaInscripciones != null && listaEsperas != null) {
			matriz = new Object[listaInscripciones.size()+listaEsperas.size()][4];					
			Iterator<Object[]> it = listaInscripciones.iterator();	
			int i=0;
			Object[] vector = new Object[2];
			//bucle para recorrer las inscripciones
			while(it.hasNext()) {
				//el vector es el siguiente elemento de la lista (una inscripcion en concreto del cliente)
				vector=it.next();
				//bucle para recorer el vector
				for(int j=0;j<2;j++) matriz[i][j]= vector[j];	
				matriz[i][2] = "Sí"; 
				matriz[i][3] = "-----";
				
				i++;
			}
			//bucle para recorrer las esperas
			Iterator<Object[]> it2 = listaEsperas.iterator();
			while(it2.hasNext()) {
				//el vector es el siguiente elemento de la lista (una inscripcion en concreto del cliente)
				vector=it2.next();
				
				matriz[i][0]= "-----";
				matriz[i][1]= vector[1];	
				matriz[i][2] = "No";
				GestionColas.inicializa();
				//GestionColas.ver();
				//System.out.println("prueba "+ Long.toString((long)vector[1]));
				String pos = GestionColas.posicion(Long.toString((long)vector[1]), ""+id_socio) + " personas delante en la cola";
				matriz[i][3] = pos;
								
				i++;
			}
			
			//copia de las primeras i filas de matriz a matriz2 para que no queden filas en blanco
			
			matriz2 = new String[i][4];
			for(int j = 0; j<i; j++) {
				for(int k = 0; k<4; k++) {
					matriz2[j][k] = matriz[j][k].toString();
				}
			}
			
		}
		//sino, indicamos que no hay reservas
		else {
			SinInscripcionesLabel.setVisible(true);
		}
		
		//Modelo de la tabla
				table.setModel(new DefaultTableModel(matriz,new String[] {
						"Id inscripción", 
						"Actividad", 
						"Admitido/a", 
						"Posición lista de espera",  
						}));
		
		
		
	}

	public Window getFrmInscripcinActividadSocio() {
		// TODO Auto-generated method stub
		return this.frame;
	}
}


