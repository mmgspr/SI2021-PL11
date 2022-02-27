package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.DateFormat;
import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import giis.demo.tkrun.InstalacionesModel;
import giis.demo.util.Database;
public class visualizarReservasA {

	private JFrame frmVisualizarReservas;
	private JTable table;
	private InstalacionesModel modelo = new InstalacionesModel();
	Database db = new Database();
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
	
	/**
	 * Initialize the contents of the frame.
	 */
	public static final String SQL_TODAS_INSTALACIONES = "SELECT nombre FROM instalaciones";
	public List<Object[]> getInstalaciones(){
		
		return db.executeQueryArray(SQL_TODAS_INSTALACIONES );
	}
	private void initialize() {
		frmVisualizarReservas = new JFrame();
		frmVisualizarReservas.setTitle("Visualizar Reservas");
		frmVisualizarReservas.setBounds(100, 100, 700, 500);
		frmVisualizarReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		
//		Connection con;
//		try {
//			String url = "jdbc:mysql://localhost:3306/db_test?useSSL=false";
//            String user = "uo275943";
//            String pass = "a@sZMj3U$hr7%X";
//            
//            con= DriverManager.getConnection(url, user, pass);
//		}
//		
//		catch (SQLException ex){
//			 con = null ;
//		}
//		
		
		
		
		
		JPanel panel = new JPanel();
		
		JSeparator separator = new JSeparator();
		
		JLabel LabelPeriodo = new JLabel("Instalación:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		db.createDatabase(false);
		db.loadDatabase();
		
		
		List<Object[]> willyrex=modelo.getInstalaciones();
		
		String[] instalaciones=new String[willyrex.size()];
		
		Iterator<Object[]> iterador = willyrex.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			instalaciones[i]=iterador.next()[0].toString();
			i++;
		}
		
		JComboBox comboBoxPeriodo = new JComboBox();
		comboBoxPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxPeriodo.setModel(new DefaultComboBoxModel(instalaciones));
		
		JButton ButtonCancelar = new JButton("Cancelar");
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton ButtonGuardar = new JButton("Guardar");
		ButtonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		
		table = new JTable();
		Object[][] contenidos=generaContenido();
		String[] titulos=generaTitulos();
		DefaultTableModel tableModel = new DefaultTableModel(
				contenidos, titulos) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
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
							.addComponent(ButtonCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 489, Short.MAX_VALUE)
							.addComponent(ButtonGuardar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(comboBoxPeriodo, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
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
					.addGap(15)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ButtonCancelar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(ButtonGuardar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		frmVisualizarReservas.getContentPane().setLayout(groupLayout);
	}
	//Hay que hacerlo con los datos de la base de datos y demás
	//de momento solo genera las horas
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
