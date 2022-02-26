package giis.proyecto.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ComboBoxModel;

import javax.swing.JFrame;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.SwingUtil;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Modelo.ActividadesModel;
import giis.proyecto.Modelo.ActividadDisplayDTO;

import giis.proyecto.Modelo.ActividadEntity;
//import giis.proyecto.Vista.ActividadesView;
import giis.proyecto.Vista.VistaMainAdmin;
import giis.proyecto.Vista.VistaMainSocio;

//import giis.proyecto.Vista.ActividadesView;

/**
 * Controlador para la funcionalidad de visualizacion de actividades para la inscripcion.
 * Es el punto de entrada de esta pantalla que se invocará:
 * -instanciando el controlador con la vista y el modelo
 * -ejecutando initController que instalara los manejadores de eventos
 */
public class ActividadesController {
	
	private ActividadesModel model;
	private ActividadesView view;
	private String lastSelectedKey = "";	//recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla de actividades

	
	public ActividadesController(ActividadesModel m, ActividadesView v) {
		this.model = m;
		this.view = v;

		//no hay inicializacion especifica del modelo, solo de la vista
		this.initView();
	}

	// MÉTODOS PARA PODER CONTROLAR LA VENTANA EN LA QUE SE INTRODUJERE LA TABLA DE ACTIVIDADES

	public ActividadesController(ActividadesModel m, ActividadesView v, JFrame inFrame, int user_id) {
		this.model = m;
		this.view = v;
		this.inFrame = inFrame;
		this.user_id = user_id;
		this.initView();
	}

	/**
	 * Inicializacion del controlador: anyade los manejadores de eventos a los objetos del UI.
	 * Cada manejador de eventos se instancia de la misma forma, para que invoque un metodo privado
	 * de este controlador, encerrado en un manejador de excepciones generico para mostrar ventanas
	 * emergentes cuando ocurra algun problema o excepcion controlada.
	 */
	public void initController() {
		//ActionListener define solo un metodo actionPerformed(), es una interfaz funcional que se puede invocar de la siguiente forma:
		//view.getBtnTablaActividades().addActionListener(e -> getListaActividades());
		//ademas invoco el metodo que responde al listener en el exceptionWrapper para que se encargue de las excepciones
		view.getBtnTablaActividades().addActionListener(e -> SwingUtil.exceptionWrapper(() -> getListaActividades()));
	

		
		//En el caso del mouse listener (para detectar seleccion de una fila) no es un interfaz funcional puesto que tiene varios metodos
		//ver discusion: https://stackoverflow.com/questions/21833537/java-8-lambda-expressions-what-about-multiple-methods-in-nested-class
		view.getTablaActividades().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de actividades
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		

		/*
		//En el caso del mouse listener (para detectar seleccion de una fila) no es un interfaz funcional puesto que tiene varios metodos
		//ver discusion: https://stackoverflow.com/questions/21833537/java-8-lambda-expressions-what-about-multiple-methods-in-nested-class
		view.getTablaActividades().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de actividades
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		*/

	}
	

	public void initView() {
		//Inicializa la fecha de hoy a un valor que permitira mostrar actividades en diferentes fases 
		//y actualiza los datos de la vista
		view.setDatePickerFechaIni("2019-04-01");
		view.setDatePickerFechaFin("2019-04-30");
		
		this.getListaActividades();

		resizeWindow();
		
		//Abre la ventana (sustituye al main generado por WindowBuilder)
		//view.getFrame().setVisible(true); 

	}
	
	
	/**
	 * La obtencion de la lista de actividades solo necesita obtener la lista de objetos del modelo 
	 * y usar metodo de SwingUtil para crear un tablemodel que se asigna finalmente a la tabla.
	 */

	private void getListaActividades() {
		List<ActividadDisplayDTO> actividades = model.getListaActividades(Util.isoStringToDate(view.getDatePickerFechaIni()), Util.isoStringToDate(view.getDatePickerFechaFin()));
		TableModel tmodel = SwingUtil.getTableModelFromPojos(actividades, new String[] {"id_actividad", "nombre", "fechaIniActividad", "aforo"});
		view.getTablaActividades().setModel(tmodel);

		// Tras hacer el modelo, se cambian los nombres de las columnas para que sean más naturales
		view.getTablaActividades().getColumnModel().getColumn(0).setHeaderValue("ID Actividad");
		view.getTablaActividades().getColumnModel().getColumn(1).setHeaderValue("Nombre");
		view.getTablaActividades().getColumnModel().getColumn(2).setHeaderValue("Fecha");
		view.getTablaActividades().getColumnModel().getColumn(3).setHeaderValue("Aforo");
		//SwingUtil.autoAdjustColumns(view.getTablaActividades());

		// Tras actualizar la tabla, se actualiza también la info detallada 
		this.restoreDetail();


	public void getListaActividades() {
		List<ActividadDisplayDTO> actividades = model.getListaActividades(Util.isoStringToDate(view.getDatePickerFechaIni()), Util.isoStringToDate(view.getDatePickerFechaFin()));
		TableModel tmodel = SwingUtil.getTableModelFromPojos(actividades, new String[] {"id_actividad", "id_instalacion", "nombre", "aforo", "cuota_socio",
				"cuota_no_socio", "fechaIniActividad", "fechaFinActividad"});
		view.getTablaActividades().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getTablaActividades());
		

		// Tras ajustar el tamaño de las columnas, establecer el tamaño de la ventana para que se ajuste a la tabla

		//resizeWindow();
	}	
	
	
	public void resizeWindow() {
		int x = inFrame.getX();
		int y = inFrame.getY();
		int alto = inFrame.getHeight();
		int ancho = 520;

		String ancho_text = view.getPeriodo().getText();
		if (!(ancho_text == null || ancho_text.equals(""))) 
			ancho += 100;
		
		inFrame.setBounds(x, y, ancho, alto);

		int ancho = 13 + view.getTablaActividades().getPreferredSize().width;
		int alto = 300;
		view.getFrame().setBounds(0, 0, ancho, alto);

	}
	
	
	/**
	 * Restaura la informacion del detalle de la actividad para visualizar los valores correspondientes
	 * a la ultima clave almacenada.
	 */
	/*
	public void restoreDetail() {
		//Utiliza la ultimo valor de la clave (que se reiniciara si ya no existe en la tabla)
		this.lastSelectedKey=SwingUtil.selectAndGetSelectedKey(view.getTablaActividades(), this.lastSelectedKey);
		//Si hay clave para seleccionar en la tabla muestra el detalle, si no, lo reinicia
		if ("".equals(this.lastSelectedKey)) { 
<<<<<<< HEAD
			view.setInstalacion("");
			view.setCuota_socio("");
			view.setCuota_no_socio("");
			view.setPeriodo("");
			view.getPeriodo_ins_act().setVisible(false);
			
			resizeWindow();
		} 
		else {
			this.updateDetail();
		}
	}
=======
			view.setDescuentoNoAplicable();
			view.getDetalleActividad().setModel(new DefaultTableModel());		
		} else {
			this.updateDetail();
		}
	}*/
	

	
	/**
	 * Al seleccionar un item de la tabla muestra el detalle con el valor del porcentaje de descuento
	 * de la actividad seleccinada y los valores de esta entidad
	 */
	/*
	public void updateDetail() {
		//Obtiene la clave seleccinada y la guarda para recordar la seleccion en futuras interacciones
		this.lastSelectedKey = SwingUtil.getSelectedKey(view.getTablaActividades());

		int id_actividad = Integer.parseInt(this.lastSelectedKey);

		int idActividad = Integer.parseInt(this.lastSelectedKey);
		
		//Detalle de descuento/recargo:
		//Controla excepcion porque el modelo causa excepcion cuando no se puede calcular el descuento
		//y debe indicarse esto en la vista para evitar mostrar datos falsos que se veian antes
		try { 
			int descuento = model.getDescuentoRecargo(idActividad, Util.isoStringToDate(view.getFechaHoy()));
			view.setDescuento(String.valueOf(descuento));
		} catch (ApplicationException e) {
			view.setDescuentoNoAplicable();
		}

		//Detalle de descuento/recargo:
		//Controla excepcion porque el modelo causa excepcion cuando no se puede calcular el descuento
		//y debe indicarse esto en la vista para evitar mostrar datos falsos que se veian antes
		try { 
			ActividadEntity act = model.getActividad(id_actividad);
			
			view.setInstalacion(inst_model.getNombreInstalacion(act.getId_instalacion()));
			view.setCuota_socio(act.getCuota_socio());
			view.setCuota_no_socio(act.getCuota_no_socio());
			
			String f_ini = act.getFechaIniActividad();
			String f_fin = act.getFechaFinActividad();
			view.setPeriodo(f_ini + " - " + f_fin);
			
			InscripcionEntity ins = insc_model.getInscripcion(act.getId_inscripcion());
			if (ins != null) {
				view.setIni_ins_s(ins.getFechaInicioSocios());
				view.setFin_ins_s(ins.getFechaFinSocios());
				view.setFin_ins_ns(ins.getFechaFinNSocios());
			}
			view.getPeriodo_ins_act().setVisible(true);
			
			resizeWindow();
		} 
		catch (ApplicationException e) {

			resizeWindow();
		}
		
		/*

		//Detalles de la actividad seleccionada
		ActividadEntity actividad = model.getActividad(idActividad);
		TableModel tmodel = SwingUtil.getRecordModelFromPojo(actividad, new String[] {"id_actividad", "id_monitor", "nombre", "aforo", "cuota_socio", 
				"cuota_no_socio", "fechaIniS_ins", "fechaFinS_ins", "fechaFinNS_ins", "fechaIniActividad", "fechaFinActividad"});
		view.getDetalleActividad().setModel(tmodel);
		SwingUtil.autoAdjustColumns(view.getDetalleActividad());
	}*/

}
