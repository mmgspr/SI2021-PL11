package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.proyecto.Modelo.ContabilidadDisplayDTO;
import giis.proyecto.Modelo.ModeloContabilidad;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaContabilidad;


public class ControladorContabilidad {
	
	private VistaContabilidad view;
	@SuppressWarnings("unused")
	private ModeloContabilidad model;
	
	public ControladorContabilidad(VistaContabilidad v) {
		this.view=v;
		initView();
		addListenerCC();
		
	}
	
	
	public void addListenerCC() {
		view.btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.frameContabilidad.dispose();
			}
		});
		
		view.btnRealizarCobrosPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fi= getFechaActual();
				Date fechaInicio=Util.isoStringToDate(fi);
				
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(fechaInicio); // Configuramos la fecha que se recibe
		        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
		        Date fechaFin = calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
				@SuppressWarnings("unused")
				String ff=Util.dateToIsoString(fechaFin);
				
				ModeloContabilidad.RealizaCobros(fechaInicio, fechaFin);
				reiniciarJTable();
				show_Contabilidad();
				mostrarPagado();
				mostrarPendiente();
				mostrarTotal();
				JOptionPane.showMessageDialog(null, "Cobros pendientes realizados con exito","Correcto",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
	}
	
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	
	public void show_Contabilidad() {
		
		String fa= getFechaActual();
		Date fechaactual=Util.isoStringToDate(fa);
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaactual); // Configuramos la fecha que se recibe
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
        Date fechaFin = calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
        calendar.setTime(fechaactual); // Configuramos la fecha que se recibe
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE)); 
        Date fechaInicio=calendar.getTime();
        
        String fi= Util.dateToIsoString(fechaInicio);
        String ff=Util.dateToIsoString(fechaFin);
        
        System.out.println(fi);
		System.out.print(ff);

		

		ArrayList<ContabilidadDisplayDTO> list= ModeloContabilidad.muestraContabilidad(fechaInicio, fechaFin);
		DefaultTableModel model= (DefaultTableModel)view.tablaContabilidad.getModel();

		Object [] row = new Object[6];
		for (int i=0;i<list.size();i++) {

			row[0]=list.get(i).getId_contabilidad();
			row[1]=list.get(i).getUsuario();
			row[2]=list.get(i).getFecha();
			row[3]=list.get(i).getDescripcion();
			row[4]=list.get(i).getImporte();
			row[5]=list.get(i).getPendiente();

			model.addRow(row);

		}
	}

	public  void reiniciarJTable (){
		DefaultTableModel modelo = (DefaultTableModel) view.tablaContabilidad.getModel();
		while(modelo.getRowCount()>0)modelo.removeRow(0);

	}
	
	public void mostrarPagado() {
		
		String fi= getFechaActual();
		Date fechaInicio=Util.isoStringToDate(fi);
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Configuramos la fecha que se recibe
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
        Date fechaFin = calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
		@SuppressWarnings("unused")
		String ff=Util.dateToIsoString(fechaFin);
		
		int Pagado=ModeloContabilidad.CalculaPagado(fechaInicio, fechaFin);
		view.LabelPagado.setText("Ingresos Pagados: "+Pagado+" €");
		
	}
	
	public void mostrarPendiente() {
		
		String fi= getFechaActual();
		Date fechaInicio=Util.isoStringToDate(fi);
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Configuramos la fecha que se recibe
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
        Date fechaFin = calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
		@SuppressWarnings("unused")
		String ff=Util.dateToIsoString(fechaFin);
		
		int Pendiente=ModeloContabilidad.CalculaPendiente(fechaInicio, fechaFin);
		view.LabelPendiente.setText("Ingresos Pendientes: "+Pendiente+" €");
		
	}	
	
	public void mostrarTotal() {
		
		String fi= getFechaActual();
		Date fechaInicio=Util.isoStringToDate(fi);
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio); // Configuramos la fecha que se recibe
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
        Date fechaFin = calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
		@SuppressWarnings("unused")
		String ff=Util.dateToIsoString(fechaFin);
		
		int Total=ModeloContabilidad.CalculaTotal(fechaInicio, fechaFin);
		view.LabelTotal.setText("Ingresos Totales: "+Total+" €");
		
	}	
	
	
	
	public void initView() {
		show_Contabilidad();
		mostrarPagado();
		mostrarPendiente();
		mostrarTotal();
	}

}
