package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Lista_Reservas {

	private JFrame frmListaReservas;
	private ReservasModel modeloReservas = new ReservasModel();
	private Login vLogin;
	private int id_socio;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lista_Reservas window = new Lista_Reservas();
					window.frmListaReservas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lista_Reservas() {
		initialize();
	}
	public Lista_Reservas(Login login) {
		initialize();
		this.vLogin= login;
		this.id_socio=this.vLogin.getId_socio();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaReservas = new JFrame();
		frmListaReservas.setTitle("Lista Reservas");
		frmListaReservas.setBounds(100, 100, 596, 377);
		frmListaReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
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
					 
						JOptionPane.showMessageDialog(frmListaReservas,"No se ha podido mostrar la Lista de Actividades. \nIntroduce una fecha inicial y una fecha final.","Error",JOptionPane.ERROR_MESSAGE);
					}
				 else {
					 if(DateFinSoc.getTime()-DateIniSoc.getTime()<0) {
						 JOptionPane.showMessageDialog(frmListaReservas,"No se ha podido crear la Lista de Actividades. \n La fecha final no puede ser menor a la inicial.","Error",JOptionPane.ERROR_MESSAGE); 
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
		
		JButton ButtonCancelar = new JButton("Aceptar");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmListaReservas.dispose();
			}
		});
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(frmListaReservas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(256, Short.MAX_VALUE)
					.addComponent(ButtonCancelar, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(231))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 566, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ButtonComprobar, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(ButtonComprobar)
							.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(ButtonCancelar, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Reserva", "ID Socio", "Instalacion", "Fecha", "Fecha Reserva", "Precio", "Actividad"
			}
		));
		scrollPane.setViewportView(table);
		frmListaReservas.getContentPane().setLayout(groupLayout);
		//scrollPane.setViewportView(table_1);
	}
	public Window getFrmListaReservas() {
		// TODO Auto-generated method stub
		return this.frmListaReservas;
	}
	
public void RellenarTablas(JTable tabla, String Inicio, String Fin) {
		
		
		List<Object[]> listaActividades=modeloReservas.getReservasSocioTodo(id_socio, Inicio, Fin);	
	
		Object[][] matriz = new Object[listaActividades.size()][7];					
		Iterator<Object[]> iterador = listaActividades.iterator();				
		int i=0;
		while(iterador.hasNext()) {
			Object[] vector = new Object[7]; 
			vector=iterador.next();
			
			for(int j=0;j<7;j++) {
				
			  matriz[i][j]= vector[j];
			
		}
			i++;
		}
		table.setModel(new DefaultTableModel(
				
				matriz
				
				
				,
				new String[] {
						"ID Reserva", "ID Socio", "Instalacion", "Fecha", "Fecha Reserva", "Precio", "Actividad"
				}
				
			));
		
	}
}
