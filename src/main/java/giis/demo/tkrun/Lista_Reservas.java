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
		
		JPanel panel = new JPanel();
		frmListaReservas.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JDateChooser dateChooserFin = new JDateChooser();
		dateChooserFin.setBounds(307, 21, 116, 19);
		panel.add(dateChooserFin);
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(113, 21, 103, 19);
		panel.add(dateChooserInicio);
		
		JLabel lblNewLabel = new JLabel("Fecha Inicio:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(30, 21, 73, 19);
		panel.add(lblNewLabel);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaFin.setBounds(241, 21, 73, 19);
		panel.add(lblFechaFin);
		
		table = new JTable();
		table.setBounds(30, 61, 526, 221);
		panel.add(table);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmListaReservas.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(249, 301, 103, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Comprobar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Date DateIniSoc = dateChooserInicio.getDate();
				 Date DateFinSoc = dateChooserFin.getDate();
				 
				
				 if((DateIniSoc==null) || (DateFinSoc==null)) {
					 
						JOptionPane.showMessageDialog(frmListaReservas,"No se ha podido mostrar la Lista de Reservas. \nIntroduce una fecha inicial y una fecha final.","Error",JOptionPane.ERROR_MESSAGE);
					}
				 else {
					 if(DateFinSoc.getTime()-DateIniSoc.getTime()<0) {
						 JOptionPane.showMessageDialog(frmListaReservas,"No se ha podido crear la Lista de Reservas. \n La fecha final no puede ser menor a la inicial.","Error",JOptionPane.ERROR_MESSAGE); 
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
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(448, 21, 108, 21);
		panel.add(btnNewButton_1);
	}
	public Window getFrmListaReservas() {
		// TODO Auto-generated method stub
		return this.frmListaReservas;
	}
public void RellenarTablas(JTable tabla, String Inicio, String Fin) {
		
		
		List<Object[]> listaActividades=modeloReservas.getActividadPeriodo2(Inicio, Fin, id_socio);	
	
		Object[][] matriz = new Object[listaActividades.size()][6];					
		Iterator<Object[]> iterador = listaActividades.iterator();				
		int i=0;
		while(iterador.hasNext()) {
			Object[] vector = new Object[6]; 
			vector=iterador.next();
			
			for(int j=0;j<6;j++) {
				
			  matriz[i][j]= vector[j];
			
		}
			i++;
		}
		table.setModel(new DefaultTableModel(
				
				matriz
				
				
				,
				new String[] {
					"id_reserva", "Instalacion", "Fecha de Reserva","Fecha Reservada", "Precio","Actividad"
				}
				
			));
		
	}
}
