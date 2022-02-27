package giis.proyecto.Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import giis.proyecto.Modelo.Database;
import giis.proyecto.Modelo.ModeloReservaAsociacion;
import giis.proyecto.Modelo.ReservasModel;
import giis.proyecto.Controlador.ActividadesController;
//import giis.proyecto.Controlador.ControladorReservasAsociacion;
//import giis.proyecto.Controlador.ReservasController;
import giis.proyecto.Modelo.ActividadesModel;
import net.miginfocom.swing.MigLayout;

/**
 * Punto de entrada principal que incluye botones para la ejecucion de las pantallas 
 * de las aplicaciones de ejemplo
 * y acciones de inicializacion de la base de datos.
 * No sigue MVC pues es solamente temporal para que durante el desarrollo se tenga posibilidad
 * de realizar acciones de inicializacion
 */
public class VistaMainAdmin {

	private JFrame frame;
	private JPanel cards;

	/**
	 * Create the application.
	 */
	public VistaMainAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Administración");
		
		//---Para centrar la ventana en la pantalla---
		Toolkit toolkit = Toolkit.getDefaultToolkit();  
		Dimension screenSize = toolkit.getScreenSize(); 
		// Calcular posición de la ventana
		int ancho = 800;
		int alto = 300;
		int x = (screenSize.width - ancho) / 2;  
		int y = (screenSize.height - alto) / 2;
		// Posicionar la ventana en la pantalla
		frame.setBounds(x, y, ancho, alto);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container borderLayout = frame.getContentPane();
		borderLayout.setLayout(new BorderLayout());
		
		
		// PARTE SUPERIOR
		// Menú desplegable "Base de datos"
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

		
		// PARTE CENTRAL
		// Inicializar controladores y panel donde se visualizarán
		cards = new JPanel(new CardLayout(12,5));
		JPanel card_actividades = new JPanel(new CardLayout());
		JPanel card_reservas = new JPanel(new CardLayout());
		


		ListarReservas reservas = new ListarReservas();
		ReservasController reservas_controller = new ReservasController(new ReservasModel(), reservas, frame, 0);
		reservas_controller.initController();
		
		ActividadesView actividades = new ActividadesView();
		ActividadesController actividades_controller = new ActividadesController(new ActividadesModel(), actividades, frame, 0);

		ActividadesView actividades = new ActividadesView();
		ActividadesController actividades_controller = new ActividadesController(new ActividadesModel(), actividades);

		actividades_controller.initController();
		

		ListarReservas reservas = new ListarReservas();
		ReservasController reservas_controller = new ReservasController(new ReservasModel(), reservas);
		reservas_controller.initController();

		
		// Añadir al panel las vistas, inicializadas con los controladores 
		// (con un nombre asignado para poder mostrarlas posteriormente)
		card_actividades.add(actividades.getFrame());
		card_reservas.add(reservas.getFrame());
		cards.add(card_actividades, "actividades");
		cards.add(card_reservas, "reservas");
		borderLayout.add(cards, BorderLayout.CENTER);
		

		// Botones para abrir las funcionalidades	(PARTE SUPERIOR)
		JButton btnAbrirActividades = new JButton("Actividades");
		btnAbrirActividades.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, "actividades");
			    frame.setBounds(x, y, 800, alto);
				//TODO: funciona raro vvv
			    //frame.setBounds(x, y, actividades.getFrame().getWidth(), actividades.getFrame().getHeight());
			}
		});
		toolbar.add(btnAbrirActividades);
		
		JButton btnAbrirReservas = new JButton("Reservas");
		btnAbrirReservas.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
			    cl.show(cards, "reservas");
			    frame.setBounds(x, y, 800, alto);
			    //frame.setBounds(x, y, reservas.getFrame().getWidth(), reservas.getFrame().getHeight());
			}
		});
		toolbar.add(btnAbrirReservas);
		
		borderLayout.add(toolbar, BorderLayout.NORTH);
		
		
		/*
		JPanel form_card = new JPanel(new CardLayout(25, 5));
		JPanel form_grid = new JPanel();
		form_grid.setLayout(new MigLayout("", "", "[25px][25px]"));
		
		JButton btnEjecutarReservas = new JButton("Reservas");
		btnEjecutarReservas.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ReservasController reservas_controller = new ReservasController(new ReservasModel(), new ListarReservas());
				reservas_controller.initController();
			}
		});
		form_grid.add(btnEjecutarReservas, "cell 0 0, alignx center, grow");

		
		JButton btnEjecutarActividades = new JButton("Actividades");
		btnEjecutarActividades.addActionListener(new ActionListener() { //NOSONAR codigo autogenerado
			public void actionPerformed(ActionEvent e) {
				ActividadesController actividades_controller = new ActividadesController(new ActividadesModel(), new ActividadesView());
				actividades_controller.initController();
			}
		});
		form_grid.add(btnEjecutarActividades, "cell 0 1, alignx center, grow");
		
		form_card.add(form_grid);
		borderLayout.add(form_card, BorderLayout.CENTER);
		*/
	}

	
	public JFrame getFrame() { 
		return this.frame;
	}
	
}
