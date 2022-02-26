package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.proyecto.Modelo.ModeloInformeInstalacion;

import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaInfromeInstalacion;

public class ControladorInformeInstalacion {
	private VistaInfromeInstalacion VII;
	
	public ControladorInformeInstalacion(VistaInfromeInstalacion v) {
		this.VII=v;
		this.addListenerCII();
		this.initView();
		
	}
	
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}
	
	public void addListenerCII() {
		VII.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModeloInformeInstalacion.CrearInforme((String)VII.CBInstalaciones.getSelectedItem(),calculaNumeroReservas((String)VII.CBInstalaciones.getSelectedItem()),calculaNumeroActividades((String)VII.CBInstalaciones.getSelectedItem()),calculaHReservas((String)VII.CBInstalaciones.getSelectedItem()),calculaHActividades((String)VII.CBInstalaciones.getSelectedItem()),calculaPReservas((String)VII.CBInstalaciones.getSelectedItem()),calculaPActividades((String)VII.CBInstalaciones.getSelectedItem()),calculaImporteReservas((String)VII.CBInstalaciones.getSelectedItem()),calculaImporteActividades((String)VII.CBInstalaciones.getSelectedItem()),calculaImporteTotal((String)VII.CBInstalaciones.getSelectedItem()));
				JOptionPane.showMessageDialog(null, "Informe creado con exito","Correcto",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		VII.CBInstalaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VII.CBInstalaciones.getSelectedIndex()>0) {
					VII.btnNewButton.setEnabled(true);
				}
				else {
					VII.btnNewButton.setEnabled(false);
				}
			}
		});
		
		VII.btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VII.frame.dispose();
			}
		});
		
	}
	
	public String calculaImporteTotal(String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerIngresosR(id);
		int n1=Integer.parseInt(num);
		String num2= ModeloInformeInstalacion.ObtenerIngresosA(id);
		int n2=Integer.parseInt(num2);
		int total=n1+n2;
		String t=""+total;
		return t;
	}
	
	public String calculaImporteReservas(String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerIngresosR(id);
		
		return num;
		
	}
	
	public String calculaImporteActividades(String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerIngresosA(id);
		
		return num;
		
	}
	
	public String calculaPReservas(String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerHorasReservas(id);
		
		int horas= Integer.parseInt(num);
		int P= (horas*100)/392;
		
		String PR=""+P;
		return PR;
		
		
	}
	
	public String calculaPActividades(String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerHorasActividades(id);
		
		int horas= Integer.parseInt(num);
		int P= (horas*100)/392;
		
		String PA=""+P;
		return PA;
		
		
	}
	
	public String calculaNumeroActividades(String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerNumeroActividades(id);
		
		
		return num;
	}
	
	public String calculaHReservas (String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerHorasReservas(id);
		
		
		return num;
		
	}
	
	public String calculaHActividades (String Instalacion) {
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		String num= ModeloInformeInstalacion.ObtenerHorasActividades(id);
		
		
		return num;
		
	}
	
	public String calculaNumeroReservas(String Instalacion) {
		
		String fa= getFechaActual();
		Date fechaActual=Util.isoStringToDate(fa);
		
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual); // Configuramos la fecha que se recibe
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
        Date fechaFin = calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
		calendar.setTime(fechaActual);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		Date fechaInicio= calendar.getTime();
        @SuppressWarnings("unused")
        String fi=Util.dateToIsoString(fechaInicio);
		String ff=Util.dateToIsoString(fechaFin);
		
		int id=ModeloInformeInstalacion.ObtenerIdInstalacion(Instalacion);
		int num= ModeloInformeInstalacion.ObtenerNumeroReservas(id, fi, ff);
		
		String numero=""+num;
		return numero;
		
	}
	

	
	
	public void initView() {
		//VRA.setFInicio(ModeloNuevaReserva.getFechaActual());
		ModeloInformeInstalacion.ObtenerInstalaciones(VII.CBInstalaciones);

		VII.frame.setVisible(true);
	}

}
