package giis.proyecto.Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import giis.proyecto.Modelo.Database;
import giis.proyecto.Modelo.ModeloReservaAsociacion;
import giis.proyecto.Modelo.ReservasModel;
import giis.proyecto.Controlador.ActividadesController;
import giis.proyecto.Controlador.ControladorContabilidad;
import giis.proyecto.Controlador.ControladorInformeActividad;
import giis.proyecto.Controlador.ControladorInformeInstalacion;
import giis.proyecto.Controlador.ControladorInicializarReservasSocio;
import giis.proyecto.Controlador.ControladorListaReservasSocio;
import giis.proyecto.Controlador.ControladorLoginSocio;
import giis.proyecto.Controlador.ControladorModificarActividad;
import giis.proyecto.Controlador.ControladorReservasAdministracion;
import giis.proyecto.Controlador.ControladorReservasSocios;
import giis.proyecto.Controlador.InscripcionController;

//import giis.proyecto.Controlador.ControladorReservasAsociacion;

//import giis.proyecto.Controlador.ReservasController;
import giis.proyecto.Modelo.ActividadesModel;
import giis.demo.tkrun.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class VistaLogin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //NOSONAR codigo autogenerado
			public void run() {
				try {
					//UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
					VistaLogin window = new VistaLogin();
					window.frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace(); //NOSONAR codigo autogenerado
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		
		//---Para centrar la ventana en la pantalla---
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		// Calcular posición de la ventana
		int width = 320;
		int height = 210;
		int x = (screenSize.width - width) / 2;  
		int y = (screenSize.height - height) / 2;
		// Posicionar la ventana en la pantalla
		frame.setBounds(x, y, width, height);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container borderLayout = frame.getContentPane();
		borderLayout.setLayout(new BorderLayout());
		
		// PARTE SUPERIOR (menú desplegable)
		JMenuBar toolbar = new JMenuBar();
		JMenu menuBD = new JMenu("Base de datos");
		
		JMenuItem inicializarBD = new JMenuItem();
		inicializarBD.setText("Inicializar Base de Datos en Blanco");
		inicializarBD.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
			}
		});
		JMenuItem datosInicialesBD = new JMenuItem();
		datosInicialesBD.setText("Cargar Datos Iniciales para Pruebas");
		datosInicialesBD.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				Database db=new Database();
				db.createDatabase(false);
				db.loadDatabase();
			}
		});
		

		
		
		menuBD.add(inicializarBD);
		menuBD.add(datosInicialesBD);
		toolbar.add(menuBD);
		
		JMenuItem Modificar = new JMenuItem();
		Modificar.setText("Modificar actividad");
		Modificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaModificarActividad VMA= new VistaModificarActividad();
				ControladorModificarActividad CMA=new ControladorModificarActividad(VMA);
				
				VMA.frmCrearActividad.setVisible(true);
			}
		});
		
		JMenuItem informe = new JMenuItem();
		informe.setText("Generar informe de actividades");
		informe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaInformeActividad VIA=new VistaInformeActividad();
				ControladorInformeActividad CIA=new ControladorInformeActividad(VIA);
				VIA.frmInforme.setVisible(true);
				
			}
		});
		
		JMenuItem ReservasSocio = new JMenuItem();
		ReservasSocio.setText("Socio Ver Reservas (H5519)");
		ReservasSocio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaLoginSocio VLS= new VistaLoginSocio();
				@SuppressWarnings("unused")
				ControladorLoginSocio CLS= new ControladorLoginSocio(VLS);
				VLS.frmInicioDeSesion.setVisible(true);
				
			}
		});
		
		JMenuItem VentanaInscripciones = new JMenuItem();
		VentanaInscripciones.setText("Crear Periodo de Inscripcion (H5507)");
		VentanaInscripciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreacionInscripcionesView CIV= new CreacionInscripcionesView();
				
				//InscripcionController CRS= new InscripcionController(CIV);
				
				CIV.frmNuevaInscripcion.setVisible(true);
				
			}
		});
		
		JMenuItem VentanaContabilidad = new JMenuItem();
		VentanaContabilidad.setText("Realizar contabilidad (H5730)");
		VentanaContabilidad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaContabilidad VC= new VistaContabilidad();
				@SuppressWarnings("unused")
				ControladorContabilidad CC= new ControladorContabilidad(VC);
				
				VC.frameContabilidad.setVisible(true);
				
			}
		});
		
		JMenuItem VentanaInforme = new JMenuItem();
		VentanaInforme.setText("Realizar informe Instalacion (H5731)");
		VentanaInforme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VistaInfromeInstalacion VII= new VistaInfromeInstalacion();
				@SuppressWarnings("unused")
				ControladorInformeInstalacion CII= new ControladorInformeInstalacion(VII);
			}
		});
		
		menuBD.add(informe);
		borderLayout.add(toolbar, BorderLayout.NORTH);
		

		menuBD.add(inicializarBD);
		menuBD.add(datosInicialesBD);

		menuBD.add(ReservasSocio);
		menuBD.add(VentanaInscripciones);

		toolbar.add(menuBD);
		
		
		
		
		// PARTE CENTRAL
		JPanel login_card = new JPanel(new CardLayout(width/4, height/4-10));
		JPanel login_grid = new JPanel(new MigLayout("", "[grow]", "[grow][grow]"));
		
		
		JButton btnEjecutarReservas = new JButton("Socios");
		btnEjecutarReservas.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				VistaMainSocio vms = new VistaMainSocio();
				vms.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		login_grid.add(btnEjecutarReservas, "cell 0 0, alignx center, grow");

		
		JButton btnEjecutarActividades = new JButton("Administración");
		btnEjecutarActividades.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				VistaMainAdmin vma = new VistaMainAdmin();
				vma.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		login_grid.add(btnEjecutarActividades, "cell 0 1, alignx center, grow");
		
		login_card.add(login_grid);
		borderLayout.add(login_card, BorderLayout.CENTER);
		
		
	}
	
	
	public JFrame getFrame() { 
		return this.frame;
	}

	
}
