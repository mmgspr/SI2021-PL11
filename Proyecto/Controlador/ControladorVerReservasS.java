package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.InstalacionesModel;
import giis.proyecto.Modelo.ModeloReservaAsociacion;
import giis.proyecto.Modelo.ModeloVerReservasS;
import giis.proyecto.Modelo.ReservaDisplayDTO;
import giis.proyecto.Modelo.ReservasModel;
import giis.proyecto.Modelo.SwingUtil;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaVerReservasS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import giis.proyecto.Modelo.ReservaDisplayDTO;

public class ControladorVerReservasS {
	private VistaVerReservasS VRA;

	//Constructor
	public ControladorVerReservasS(VistaVerReservasS v) {
		this.VRA=v;
		this.initView();
		this.addListenerCVR();
		


	}


	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	
	public void addListenerCVR() {
			
		VRA.rdbtnInstalacin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(VRA.rdbtnInstalacin.isSelected()) {
					VRA.rdbtnInstalacion.setEnabled(false);
				}else {
					VRA.rdbtnInstalacion.setEnabled(true);
				}
	
			}
		});
		
		VRA.rdbtnInstalacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(VRA.rdbtnInstalacion.isSelected()) {
					VRA.rdbtnInstalacin.setEnabled(false);
				}else {
					VRA.rdbtnInstalacin.setEnabled(true);
				}
	
			}
		});

		VRA.datePickerFechaIni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!VRA.datePickerFechaIni.getJFormattedTextField().getText().isEmpty())&&(!VRA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())) {
					VRA.JButtonCrear.setEnabled(true);
				}
				else {
					VRA.JButtonCrear.setEnabled(false);
				}
			}
		});

		VRA.datePickerFechaFin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!VRA.datePickerFechaIni.getJFormattedTextField().getText().isEmpty())&&(!VRA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())) {
					VRA.JButtonCrear.setEnabled(true);
				}
				else {
					VRA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VRA.JButtonCrear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e){
				
				Date fechaInicioS= Util.isoStringToDate(VRA.datePickerFechaIni.getJFormattedTextField().getText());
				Date fechaFinS= Util.isoStringToDate(VRA.datePickerFechaFin.getJFormattedTextField().getText());
				
				if(fechaFinS.after(fechaInicioS)){
					if(VRA.rdbtnInstalacion.isSelected()) {
						reiniciarJTable();
						show_reservas();
					}else {
					reiniciarJTable();
					show_reservasI();
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Por favor, selecione una fecha de fin posterior a la de inicio","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	
	
	public void show_reservas() {

		int idSocio=ModeloVerReservasS.ObtenerIdSocio((String)VRA.CBSocios.getSelectedItem());
		String fechaInicio= VRA.datePickerFechaIni.getJFormattedTextField().getText();
		String fechaFin= VRA.datePickerFechaFin.getJFormattedTextField().getText();

		Date fi=Util.isoStringToDate(fechaInicio);
		Date ff=Util.isoStringToDate(fechaFin);

		ArrayList<ReservaDisplayDTO> list= ModeloVerReservasS.muestraReservas(idSocio, fi, ff);
		DefaultTableModel model= (DefaultTableModel)VRA.tablaReservas.getModel();

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
	
	public void show_reservasI() {

		int idSocio=ModeloVerReservasS.ObtenerIdSocio((String)VRA.CBSocios.getSelectedItem());
		String fechaInicio= VRA.datePickerFechaIni.getJFormattedTextField().getText();
		String fechaFin= VRA.datePickerFechaFin.getJFormattedTextField().getText();

		Date fi=Util.isoStringToDate(fechaInicio);
		Date ff=Util.isoStringToDate(fechaFin);

		ArrayList<ReservaDisplayDTO> list= ModeloVerReservasS.muestraReservasI(idSocio,ModeloVerReservasS.ObtenerIdInstalacion((String) VRA.CBInstalaciones.getSelectedItem()), fi, ff);
		DefaultTableModel model= (DefaultTableModel)VRA.tablaReservas.getModel();

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
		DefaultTableModel modelo = (DefaultTableModel) VRA.tablaReservas.getModel();
		while(modelo.getRowCount()>0)modelo.removeRow(0);

	}


	public void initView() {
		//VRA.setFInicio(ModeloNuevaReserva.getFechaActual());
		ModeloVerReservasS.ObtenerInstalaciones(VRA.CBInstalaciones);
		ModeloVerReservasS.ObtenerSocios(VRA.CBSocios);

		VRA.frmVerReservasS.setVisible(true);
	}
	
	/*public void getListaReservas() {
		if (VRA.CBInstalaciones.getSelectedIndex() > 0) {
			List<ReservaDisplayDTO> reservas = ModeloVerReservasS.getListaReservas((String)VRA.CBInstalaciones.getSelectedItem(), Util.isoStringToDate(VRA.getDatePickerFecha()));
			TableModel tmodel = SwingUtil.getTableModelFromPojos(reservas, new String[] {"id_reserva", "fecha_inicioReserva", "fecha_finReserva", "hora_inicioReserva", "hora_finReserva"});
			VRA.getTablaReservas().setModel(tmodel);
			SwingUtil.autoAdjustColumns(VRA.getTablaReservas());
		}
		
		// Tras actualizar la tabla, se actualiza también la info detallada 
		//this.restoreDetail();
		// Tras ajustar el tamaño de las columnas, establecer el tamaño de la ventana para que se ajuste a la tabla
		//resizeWindow(); //dentro de restoreDetail
	}


	/*public void getListaReservas() {
		List<ReservaDisplayDTO> reservas = ReservasModel.getListaReservas(Integer.parseInt(VRA.getIDInstalacion()), Util.isoStringToDate(VRA.getFInicio()), Util.isoStringToDate(VRA.getFFinal()));
		TableModel tmodel = SwingUtil.getTableModelFromPojos(reservas, new String[] {"id_reserva", "id_instalacion", "id_actividad", "id_socio", 
				"fecha_inicioReserva", "fecha_finReserva", "hora_inicioReserva", "hora_finReserva"});
		VRA.getTablaReservas().setModel(tmodel);
		SwingUtil.autoAdjustColumns(VRA.getTablaReservas());

	}*/
}
