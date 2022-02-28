package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.sql.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import giis.demo.tkrun.InstalacionesModel;
import giis.demo.util.Database;
import giis.demo.util.SwingMain;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class visualizarReservasA {

	private JFrame frmVisualizarReservas;
	private JTable table;
	private InstalacionesModel modeloInstal = new InstalacionesModel();
	private ReservasModel modeloReserv=new ReservasModel();
	private SwingMain principal;
	private DefaultTableModel tableModel;
	private Object[][] contenidos;
	
	Calendar cal=Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	final long tiempo_actual=cal.getTime().getTime();
	
	/**
	 * Launch the application a
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visualizarReservasA window = new visualizarReservasA();
					window.frmVisualizarReservas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public visualizarReservasA() {
		initialize();
	}
	public visualizarReservasA(SwingMain principal) {
		initialize();
		this.principal = principal;
	}
	
	
	public JFrame getFrmVisualizarReservas() {
		return this.frmVisualizarReservas;
	}
	
	
	private void initialize() {
		frmVisualizarReservas = new JFrame();
		frmVisualizarReservas.setTitle("Visualizar Reservas");
		frmVisualizarReservas.setBounds(100, 100, 700, 500);
		frmVisualizarReservas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		JPanel panel = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JLabel LabelPeriodo = new JLabel("Instalación:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		//principal.db.createDatabase(false);
		//principal.db.loadDatabase();
		
		
		List<Object[]> lista=modeloInstal.getInstalaciones();
		
		String[] instalaciones=new String[lista.size()];
		
		Iterator<Object[]> iterador = lista.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			instalaciones[i]=iterador.next()[0].toString();
			i++;
		}
		
		JComboBox comboBoxInstalacion = new JComboBox();
		
		comboBoxInstalacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxInstalacion.setModel(new DefaultComboBoxModel(instalaciones));
		
		JButton ButtonCancelar = new JButton("Salir");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmVisualizarReservas.dispose();
			}
		});
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
		table = new JTable();
		contenidos=generaContenido(comboBoxInstalacion.getSelectedItem().toString());
		String[] titulos=generaTitulos();
		tableModel = new DefaultTableModel(contenidos, titulos) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }};
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setColumnHeaderView(scrollBar);
		GroupLayout groupLayout = new GroupLayout(frmVisualizarReservas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(ButtonCancelar))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(comboBoxInstalacion, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxInstalacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addComponent(ButtonCancelar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		frmVisualizarReservas.getContentPane().setLayout(groupLayout);
	
		comboBoxInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contenidos=generaContenido(comboBoxInstalacion.getSelectedItem().toString());
				
				tableModel= new DefaultTableModel(contenidos, titulos) {
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }};
				table.setModel(tableModel);
			}
		});
	
	}//FIN INITIALIZE
	
	
	
	
	/* 																*/
	/* 																*/
	/* FALTA QUE IDENTIFIQUE SI ES ACTIVIDAD O EL SOCIO EN CUESTION	*/
	/* 																*/
	/* 																*/
	public Object[][] generaContenido(String instalacion) {
		Object[][] contenido= new Object[13][31];
		for(int i=0; i<13; i++) {
			if(i==1 || i==0)contenido[i][0]="0"+Integer.toString(i+9) + ":00";
			contenido[i][0]=Integer.toString(i+9) + ":00";
		}
		
		for(int i=0; i<13;i++) {
			for(int j=1; j<31; j++) {
				contenido[i][j]="Libre";
			}
		}
		
		List<Object[]> lista = modeloInstal.getIdInstalacion(instalacion);
		long id_instalacion=(long)lista.get(0)[0];
		
		
		lista= modeloReserv.getReservasManu(id_instalacion);
		
		
		Iterator<Object[]> iterador = lista.iterator();
		Object vector[];
		String[] fechayhora;
		String diaTotal, horaTotal, nombre_actividad="prueba";
		Date fecha_reserva;
		int dias,dia_afectado;
		long persona=0, actividad;
		
		while(iterador.hasNext()) {
			vector=iterador.next();
			if(vector[0]!=null)persona=(long)vector[0];
			actividad= (long)vector[2];
			boolean esActividad=false;
			if(actividad!=0) {
				nombre_actividad=modeloReserv.getActividad(actividad).get(0)[0].toString();
				esActividad=true;
			}
			fechayhora=vector[1].toString().split("T");
			diaTotal=fechayhora[0];
			horaTotal=fechayhora[1];
			try {
				fecha_reserva = sdf.parse(diaTotal);
				long diferencia= fecha_reserva.getTime()-tiempo_actual;
				dias = (int)Math.ceil(diferencia*1.0/(1000*3600*24));
				if(dias <0) continue;
				else {
					dia_afectado = dias+1;
				}
				if(esActividad) {
					switch(horaTotal) {
					case "9:00":
						contenido[0][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "10:00":
						contenido[1][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "11:00":
						contenido[2][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "12:00":
						contenido[3][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "13:00":
						contenido[4][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "14:00":
						contenido[5][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "15:00":
						contenido[6][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "16:00":
						contenido[7][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "17:00":
						contenido[8][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "18:00":
						contenido[9][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "19:00":
						contenido[10][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "20:00":
						contenido[11][dia_afectado]="Res("+nombre_actividad+")"; break;
					case "21:00":
						contenido[12][dia_afectado]="Res("+nombre_actividad+")"; break;
					}
				}
				else {
					switch(horaTotal) {
					case "9:00":
						contenido[0][dia_afectado]="Res("+persona+")"; break;
					case "10:00":
						contenido[1][dia_afectado]="Res("+persona+")"; break;
					case "11:00":
						contenido[2][dia_afectado]="Res("+persona+")"; break;
					case "12:00":
						contenido[3][dia_afectado]="Res("+persona+")"; break;
					case "13:00":
						contenido[4][dia_afectado]="Res("+persona+")"; break;
					case "14:00":
						contenido[5][dia_afectado]="Res("+persona+")"; break;
					case "15:00":
						contenido[6][dia_afectado]="Res("+persona+")"; break;
					case "16:00":
						contenido[7][dia_afectado]="Res("+persona+")"; break;
					case "17:00":
						contenido[8][dia_afectado]="Res("+persona+")"; break;
					case "18:00":
						contenido[9][dia_afectado]="Res("+persona+")"; break;
					case "19:00":
						contenido[10][dia_afectado]="Res("+persona+")"; break;
					case "20:00":
						contenido[11][dia_afectado]="Res("+persona+")"; break;
					case "21:00":
						contenido[12][dia_afectado]="Res("+persona+")"; break;
					}
				}
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} //fin while iterador
		return contenido;
	}
	
	public String[] generaTitulos(){
		String[] titulos = new String[31];
		titulos[0]="Horas";
		titulos[1]=new SimpleDateFormat("dd-MM").format(cal.getTime());
		for(int i=2; i<titulos.length;i++) {
			cal.add(cal.DATE, 1);
			titulos[i]=new SimpleDateFormat("dd-MM").format(cal.getTime());
		}
		return titulos;
	}

	
}
