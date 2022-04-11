package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class Generar_Informe_Actividades {

	private JFrame frmGenerarInformeActividades;
	private JTable table;
	private ReservasModel modeloReservas = new ReservasModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Generar_Informe_Actividades window = new Generar_Informe_Actividades();
					window.frmGenerarInformeActividades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Generar_Informe_Actividades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGenerarInformeActividades = new JFrame();
		frmGenerarInformeActividades.setTitle("Generar Informe Actividades");
		frmGenerarInformeActividades.setBounds(100, 100, 583, 370);
		frmGenerarInformeActividades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmGenerarInformeActividades.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel LabelPeriodo = new JLabel("Inicio de Periodo:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setToolTipText("");
		
		JLabel lblNewLabel = new JLabel("Fin de Periodo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JDateChooser dateChooserFin = new JDateChooser();
		
		JButton ButtonComprobar = new JButton("Comprobar");
		ButtonComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 Date DateIniSoc = dateChooserInicio.getDate();
				 Date DateFinSoc = dateChooserFin.getDate();
				 
				 if((DateIniSoc==null) || (DateFinSoc==null)) {
					 
						JOptionPane.showMessageDialog(frmGenerarInformeActividades,"No se ha podido mostrar la Lista de Actividades. \nIntroduce una fecha inicial y una fecha final.","Error",JOptionPane.ERROR_MESSAGE);
					}
				 else {
					 if(DateFinSoc.getTime()-DateIniSoc.getTime()<0) {
						 JOptionPane.showMessageDialog(frmGenerarInformeActividades,"No se ha podido crear la Lista de Actividades. \n La fecha final no puede ser menor a la inicial.","Error",JOptionPane.ERROR_MESSAGE); 
					 }
					 else {
				//fechaInicio
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateInicio = sdf.format(dateChooserInicio.getDate());
				String Inicio = dateInicio;
				
				//FechaFin
			    SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd");
			    String dateFin = sdfk.format(dateChooserFin.getDate());
				String Fin = dateFin;
																
				RellenarTablas(table, Inicio, Fin);
				
					 }
					 
			}
			}
			
		});
		ButtonComprobar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("Generar Informe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(LabelPeriodo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ButtonComprobar))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(312)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(ButtonComprobar, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Actividades", "Fecha Inicio", "Fecha Fin", "Instalaci\u00F3n", "ID", "Aforo"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
	}
	
	public Window getfrmGenerarInformeActividades() {
		// TODO Auto-generated method stub
		return this.frmGenerarInformeActividades;
	}
	public void RellenarTablas(JTable tabla, String Inicio, String Fin) {
		
		
		List<Object[]> listaActividades=modeloReservas.getActividades(Inicio, Fin);	
	  
		Object[][] matriz = new Object[listaActividades.size()][6];					
		Iterator<Object[]> iterador = listaActividades.iterator();	
		while(iterador.hasNext()) {
			Object[] vector = new Object[5]; 
			vector=iterador.next();
		    int i=0;
		    String Aforo;
		    
		    int id=  Math.toIntExact((long)vector[4]);
		    Aforo=modeloReservas.getAforoActividades(id);
			
			
			for(int j=0;j<5;j++) {
				
			  matriz[i][j]= vector[j];
			  matriz[i][5]=Aforo;
		
			i++;
		}
		}
		table.setModel(new DefaultTableModel(
				
				matriz
				
				
				,
				new String[] {
					"Actividad", "Fecha Inicio", "Fecha Fin", "Instalacion", "ID"
				}
				
			));
		
	}
	
	
	
}
