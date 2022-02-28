package giis.demo.util;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import giis.demo.tkrun.*;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class SwingMain {

	private JFrame frmVentanaDeCarga;
	private inicial vInicial;
	public Database db;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
			public void run() {
				try {
					SwingMain window = new SwingMain();
					window.frmVentanaDeCarga.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public SwingMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.aaa
	 */
	private void initialize() {
		frmVentanaDeCarga = new JFrame();
		frmVentanaDeCarga.setTitle("Ventana de carga");
		frmVentanaDeCarga.setBounds(0, 0, 287, 185);
		frmVentanaDeCarga.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		vInicial= new inicial(this);
		JButton btnEjecutarTkrun = new JButton("Ejecutar sistema");
		btnEjecutarTkrun.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				//CarrerasController controller=new CarrerasController(new CarrerasModel(), new CarrerasView());
				//controller.initController();
				vInicial.getFrmIndex().setVisible(true);
				
			}
		});
		frmVentanaDeCarga.getContentPane().setLayout(new BoxLayout(frmVentanaDeCarga.getContentPane(), BoxLayout.Y_AXIS));
		frmVentanaDeCarga.getContentPane().add(btnEjecutarTkrun);
		
			
		JButton btnInicializarBaseDeDatos = new JButton("Inicializar Base de Datos en Blanco");
		btnInicializarBaseDeDatos.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				db=new Database();
				db.createDatabase(false);
			}
		});
		frmVentanaDeCarga.getContentPane().add(btnInicializarBaseDeDatos);
			
		JButton btnCargarDatosIniciales = new JButton("Cargar Datos Iniciales para Pruebas");
		btnCargarDatosIniciales.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		frmVentanaDeCarga.getContentPane().add(btnCargarDatosIniciales);
	}

	public JFrame getFrame() { return this.frmVentanaDeCarga; }
	
}
