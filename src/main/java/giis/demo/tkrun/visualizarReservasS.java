package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
public class visualizarReservasS {

	private JFrame frmVisualizarReservas;
	private JTable table;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visualizarReservasS window = new visualizarReservasS();
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
	public visualizarReservasS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisualizarReservas = new JFrame();
		frmVisualizarReservas.setTitle("Visualizar Reservas");
		frmVisualizarReservas.setBounds(100, 100, 700, 500);
		frmVisualizarReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JLabel LabelPeriodo = new JLabel("Instalación:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JComboBox comboBoxPeriodo = new JComboBox();
		comboBoxPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxPeriodo.setModel(new DefaultComboBoxModel(new String[] {""}));
		
		JButton ButtonCancelar = new JButton("Cancelar");
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton ButtonGuardar = new JButton("Guardar");
		ButtonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
		table = new JTable();
		Object[][] contenidos=generaContenido();
		String[] titulos=generaTitulos();
		table.setModel(new DefaultTableModel(
			contenidos, titulos));
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
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(ButtonCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 478, Short.MAX_VALUE)
							.addComponent(ButtonGuardar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(20))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(comboBoxPeriodo, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
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
							.addComponent(comboBoxPeriodo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(ButtonCancelar, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(ButtonGuardar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		frmVisualizarReservas.getContentPane().setLayout(groupLayout);
	}
	//Hay que hacerlo con los datos de la base de datos y demás
	//de momento solo genera las horas
	
	//ADEMAS EN ESTA CLASE HAY QUE CAMBIAR EL NUMERO DE SOCIO POR "SOCIO" 
	public Object[][] generaContenido() {
		Object[][] contenido= new Object[13][31];
		for(int i=0; i<13; i++) {
			if(i==1 || i==0)contenido[i][0]="0"+Integer.toString(i+9) + ":00";
			contenido[i][0]=Integer.toString(i+9) + ":00";
		}
		
		for(int i=1; i<13;i++) {
			for(int j=1; j<31; j++) {
				contenido[i][j]=null;
			}
			
		}
		return contenido;
	}
	
	public String[] generaTitulos(){
		String[] titulos = new String[31];
		titulos[0]="Horas";
		Calendar cal=Calendar.getInstance();
		titulos[1]=new SimpleDateFormat("dd-MM").format(cal.getTime());
		for(int i=2; i<titulos.length;i++) {
			cal.add(cal.DATE, 1);
			titulos[i]=new SimpleDateFormat("dd-MM").format(cal.getTime());
		}
		return titulos;
	}
}
