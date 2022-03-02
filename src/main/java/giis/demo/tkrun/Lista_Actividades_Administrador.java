package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import giis.demo.util.SwingMain;

import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Lista_Actividades_Administrador {

	private ReservasModel modeloReservas = new ReservasModel();
	private InstalacionesModel modelo = new InstalacionesModel();
	private JFrame frmListaDeActividades;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lista_Actividades_Administrador window = new Lista_Actividades_Administrador();
					window.frmListaDeActividades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lista_Actividades_Administrador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaDeActividades = new JFrame();
		frmListaDeActividades.setTitle("Lista de Actividades");
		frmListaDeActividades.setBounds(100, 100, 722, 367);
		frmListaDeActividades.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmListaDeActividades.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelPeriodo = new JLabel("Inicio de Periodo:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelPeriodo.setBounds(23, 20, 111, 20);
		panel.add(LabelPeriodo);
		
		JButton ButtonCancelar = new JButton("Aceptar");
		ButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmListaDeActividades.dispose();
			}
		});
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonCancelar.setBounds(302, 302, 101, 29);
		panel.add(ButtonCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 668, 234);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Actividad", "Tipo de Actividad", "Fecha Inicio", "Fecha Fin", "N\u00BA Plazas", "Precio Socios", "Precio No Socios"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Fin de Periodo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(283, 19, 95, 22);
		panel.add(lblNewLabel);
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setToolTipText("");
		dateChooserInicio.setBounds(144, 21, 95, 19);
		panel.add(dateChooserInicio);
				
	
			
		
		JDateChooser dateChooserFin = new JDateChooser();
		dateChooserFin.setBounds(385, 21, 101, 19);
		panel.add(dateChooserFin);
		
		
		
			
		
		JButton ButtonComprobar = new JButton("Comprobar");
		ButtonComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//fechaInicio
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dateInicio = sdf.format(dateChooserInicio.getDate());
				String Inicio = dateInicio;
				
				//FechaFin
			    SimpleDateFormat sdfk = new SimpleDateFormat("yyyy-MM-dd");
			    String dateFin = sdfk.format(dateChooserFin.getDate());
				String Fin = dateFin;
								
				;
				
				//id_instalacion
				String Actividad;
				
				List<Object[]> lista=modeloReservas.getActividadPeriodo(Inicio, Fin);
				//Lita donde se guardan todas las actividades
				String[] nombre=new String[lista.size()];
				//Para obtener el número de actividades de esa lista
				Iterator<Object[]> iterador = lista.iterator();				
				int i=0;
				while(iterador.hasNext()) {
					nombre[i]=iterador.next()[0].toString();
					i++;
				}
				
				// BORRAR AL ACABAR:PARA COMPROBAR QUE FUNCIONA getActividadPeriodo
				while(i > 0) {
					i--;
				Actividad = nombre[i];
				System.out.println(Actividad);
			
				}
				
				
				
				
				
			}
		});
		ButtonComprobar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonComprobar.setBounds(537, 22, 121, 21);
		panel.add(ButtonComprobar);
		
	}

	public Window getFrmListaDeActividades() {
		// TODO Auto-generated method stub
		return this.frmListaDeActividades;
	}
}
