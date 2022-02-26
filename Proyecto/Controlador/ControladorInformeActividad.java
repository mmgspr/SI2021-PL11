package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;

import giis.proyecto.Modelo.ModeloCreacionActividades;
import giis.proyecto.Modelo.ModeloInformeActividad;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaCreacionActividades;
import giis.proyecto.Vista.VistaInformeActividad;

public class ControladorInformeActividad {
	private VistaInformeActividad VIA;
	

	//Constructor
	public ControladorInformeActividad(VistaInformeActividad v) {
		this.VIA=v;
		this.addListenerVIA();
		this.initView();
	}
	


	private void addListenerVIA(){

		VIA.JButtonGen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		 
				java.util.Date date=Util.isoStringToDate(VIA.datePickerFechaInicio.getJFormattedTextField().getText());
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(date);

				/*try {
		            String ruta = "C:\\Users\\adriv\\OneDrive\\informe.txt";
		            String contenido = "Contenido de ejemplo";
		            File file = new File(ruta);
		            // Si el archivo no existe es creado
		            if (!file.exists()) {
		                file.createNewFile();
		            }
		            FileWriter fw = new FileWriter(file);
		            BufferedWriter bw = new BufferedWriter(fw);
		            bw.write(contenido);
		            bw.close();
		        } catch (Exception a) {
		            a.printStackTrace();
		        }
		    }*/
				
				
				try {
					PrintWriter writer = new PrintWriter("C:/Users/adriv/Desktop/informe.txt", "UTF-8");
					writer.println("Informe sobre actividades generado para las fechas: "+VIA.datePickerFechaInicio.getJFormattedTextField().getText()+" - "+VIA.datePickerFechaFin.getJFormattedTextField().getText());
					int contS=0;
					int contNS=0;
					int contT=0;
					int max=0;
					String aux="";
					int lim=ModeloInformeActividad.actividadesCount((VIA.datePickerFechaInicio.getJFormattedTextField().getText()), VIA.datePickerFechaFin.getJFormattedTextField().getText());
					System.out.println(lim);
					//while((date.before(Util.isoStringToDate(VIA.datePickerFechaFin.getJFormattedTextField().getText()))||date.equals(Util.isoStringToDate(VIA.datePickerFechaFin.getJFormattedTextField().getText())))) {
						for(int i=0; i<lim; i++){
							int actividad=ModeloInformeActividad.actividadesP((VIA.datePickerFechaInicio.getJFormattedTextField().getText()), VIA.datePickerFechaFin.getJFormattedTextField().getText(),i);
							double porcentajeS=0.0;
							double porcentajeNS=0.0;
							if(ModeloInformeActividad.inscritos(actividad)>0) {
								porcentajeS=100*ModeloInformeActividad.sociosInscritos(actividad)/ModeloInformeActividad.inscritos(actividad);
								porcentajeNS=100*ModeloInformeActividad.noSociosInscritos(actividad)/ModeloInformeActividad.inscritos(actividad);
							}else {
								porcentajeS=0;
								porcentajeNS=0;		
							}
							System.out.println("Entra for");
							System.out.println(ModeloInformeActividad.actividadesCount((VIA.datePickerFechaInicio.getJFormattedTextField().getText()), VIA.datePickerFechaFin.getJFormattedTextField().getText()));
							
							writer.println("\n ID: " + actividad);
							writer.println("La actividad "+ ModeloInformeActividad.nombreA(actividad) + " tiene un total de " + ModeloInformeActividad.inscritos(actividad) + " personas inscritas para un aforo de "
									+ ModeloInformeActividad.aforoA(actividad)+" personas");
							if(ModeloInformeActividad.aforoA(actividad)-ModeloInformeActividad.inscritos(actividad)==0) {
								writer.println("Habiendo "+ 0 +" socios y "+ 0 +" no socios en la lista de espera.");
							}
							writer.println("De esos, " + ModeloInformeActividad.sociosInscritos(actividad)+ " son socios y " + ModeloInformeActividad.noSociosInscritos(actividad) + " son no socios.");
							writer.println("Con un porcentaje de " + porcentajeS + "% de socios y " + porcentajeNS + "% de no socios");
							contS+=ModeloInformeActividad.sociosInscritos(actividad);
							contNS+=ModeloInformeActividad.noSociosInscritos(actividad);
							contT+=ModeloInformeActividad.inscritos(actividad);
							if(max<ModeloInformeActividad.inscritos(actividad)+0) {
								max=ModeloInformeActividad.inscritos(actividad)+0;
								aux=ModeloInformeActividad.nombreA(actividad);
								
							}
							
						}
						writer.println("\n\n Para las actividades en este periodo ha habido un total de "+ contT +" inscripciones, de las cuales "+contS+" son de socios y "+contNS+" de no socios.");
						writer.println("Esto sería un porcentaje de socios del "+ 100*contS/contT +" % y de no socios del "+ 100*contNS/contT +" %.");
						writer.println("La actividad favorita es "+aux+" con un total de "+max+" inscritos, contando los que están en la lista de espera.");
						
						
						
						//calendar.add(Calendar.DAY_OF_YEAR, +1);
						//date=calendar.getTime();

					//}
						
					writer.close();
				} catch (Exception a) {
					a.printStackTrace();
				}
				
			}
		});
		
		
	}
	
	
	public void initView() {

		VIA.frmInforme.setVisible(true);
	}
	
}
