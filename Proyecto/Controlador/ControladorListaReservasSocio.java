package giis.proyecto.Controlador;

import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.View;

import giis.proyecto.Modelo.ActividadDisplayDTO;
import giis.proyecto.Modelo.ModeloListaReservaSocio;
import giis.proyecto.Modelo.ModeloReservasAdministracion;
import giis.proyecto.Modelo.ReservaDisplayDTO;
import giis.proyecto.Modelo.SwingUtil;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaReservaSocios;

public class ControladorListaReservasSocio {



	@SuppressWarnings("unused")
	private ModeloListaReservaSocio model;
	private VistaReservaSocios view;

	public ControladorListaReservasSocio(VistaReservaSocios v,String socio) {
		this.view=v;
		this.initView(socio);
		this.addListenerCLRS(socio);



	}

	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}

	public void addListenerCLRS(String socio) {
		view.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Date fechaInicioS= Util.isoStringToDate(view.datePickerFechaInicio.getJFormattedTextField().getText());
				Date fechaFinS= Util.isoStringToDate(view.datePickerFechaFin.getJFormattedTextField().getText());

				if(fechaFinS.after(fechaInicioS)){
					reiniciarJTable();
					show_reservas(socio);

				}
				else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione una fecha de fin posterior a la de Inicio","Error",JOptionPane.ERROR_MESSAGE);
				}

			}

		});





		view.datePickerFechaInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!view.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!view.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())) {
					view.btnNewButton.setEnabled(true);
				}
				else {
					view.btnNewButton.setEnabled(false);
				}
			}
		});

		view.datePickerFechaFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!view.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!view.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())) {
					view.btnNewButton.setEnabled(true);
				}
				else {
					view.btnNewButton.setEnabled(false);
				}
			}
		});

	}



	public void show_reservas(String socio) {

		int idSocio= ModeloListaReservaSocio.ObtenerIdSocio(socio);
		String fechaInicio= view.datePickerFechaInicio.getJFormattedTextField().getText();
		String fechaFin= view.datePickerFechaFin.getJFormattedTextField().getText();

		Date fi=Util.isoStringToDate(fechaInicio);
		Date ff=Util.isoStringToDate(fechaFin);

		ArrayList<ReservaDisplayDTO> list= ModeloListaReservaSocio.muestraReservas(idSocio, fi, ff);
		DefaultTableModel model= (DefaultTableModel)view.tablaReservasSocios.getModel();

		Object [] row = new Object[5];
		for (int i=0;i<list.size();i++) {

			row[0]=list.get(i).getId_reserva();
			row[1]=list.get(i).getNombreInstalacion();
			row[2]=list.get(i).getFecha_inicioReserva();
			row[3]=list.get(i).getHora_inicioReserva();
			row[4]=list.get(i).getHora_finReserva();

			model.addRow(row);

		}
	}

	public  void reiniciarJTable (){
		DefaultTableModel modelo = (DefaultTableModel) view.tablaReservasSocios.getModel();
		while(modelo.getRowCount()>0)modelo.removeRow(0);

	}





	public void initView(String socio) {


		String fechaActual= getFechaActual();
		Date nuevaFecha = new Date();
		Date actual= Util.isoStringToDate(fechaActual);

		Calendar cal = Calendar.getInstance(); 
		cal.setTime(actual); 
		cal.add(Calendar.DATE, 30);
		nuevaFecha = cal.getTime();

		String fecha= Util.dateToIsoString(nuevaFecha);
		view.datePickerFechaFin.getJFormattedTextField().setText(fecha);

		view.LabelBienvenido.setText("Bienvenido/a: "+ModeloListaReservaSocio.ObtenerNombre(socio)+" "+ModeloListaReservaSocio.obtenerApellidos(socio));

		show_reservas(socio);
	}



}