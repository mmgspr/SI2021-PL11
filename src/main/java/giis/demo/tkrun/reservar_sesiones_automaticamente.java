package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.awt.event.ActionEvent;

public class reservar_sesiones_automaticamente {

	private JFrame frmReservarSesionesAutomticamente;
	
	private SesionesModel modeloSesiones = new SesionesModel();
	private ActividadesModel modeloActividades = new ActividadesModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservar_sesiones_automaticamente window = new reservar_sesiones_automaticamente();
					window.frmReservarSesionesAutomticamente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public reservar_sesiones_automaticamente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservarSesionesAutomticamente = new JFrame();
		frmReservarSesionesAutomticamente.setTitle("Reservar Automáticamente");
		frmReservarSesionesAutomticamente.setBounds(100, 100, 350, 200);
		frmReservarSesionesAutomticamente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmReservarSesionesAutomticamente.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//obtener todas las instalaciones
		List<Object[]> listaActividades=modeloActividades.getActividades();
				
		String[] actividades=new String[listaActividades.size()];
				
		Iterator<Object[]> iteradorActividades = listaActividades.iterator();
				
		int i=0;
		while(iteradorActividades.hasNext()) {
			actividades[i]=iteradorActividades.next()[0].toString();
			i++;
		}
		
		JLabel lblActividad = new JLabel("• Actividad:");
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActividad.setBounds(10, 10, 79, 17);
		panel.add(lblActividad);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(actividades));
		comboBox.setBounds(99, 10, 160, 21);
		panel.add(comboBox);
		
		JButton btnNewButton = new JButton("Reservar automáticamente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Reservar sesiones
				/*
				List<Object[]> sesiones = modeloSesiones.getSesionesActividad(Long.toString(modeloActividades.getIdActividad((String)comboBox.getSelectedItem())));
				String diaSemana = getDayString(modeloActividades.getFechaIniActividad((String)comboBox.getSelectedItem()), new Locale("es", "ES"));
				System.out.println(diaSemana);
				Iterator<Object[]> it = sesiones.iterator();
				Object sesion[];
				boolean encontrado = false;
				while(it.hasNext()) {
					sesion = (Object[])it.next();
					if (sesion[0].equals(diaSemana)) {
						diaHora=sdf.format(dateChooser_1_1.getDate())+" "+ sesion[1];
						encontrado = true;
					}
				}
				if (encontrado == true){
					//diaHora=sdf.format(dateChooser_1_1.getDate())+" 20:00:00";
					int cliente = modeloReservas.comprobarDisponibilidadActividad(id, diaHora);
					if (cliente==-1) {
						JOptionPane.showMessageDialog(frmReservaParaActividad,
							    "Está ocupado por otra actividad.",
							    "Error al reservar",
							    JOptionPane.ERROR_MESSAGE);
					}
					else if (cliente == 0){
						modeloReservas.nuevaReserva(0, Integer.parseInt(id), sdf.format(currentDate), diaHora, "0", modeloActividades.siguienteIdActividad());
						JOptionPane.showMessageDialog(frmReservaParaActividad, "Reservado.\n");
					}
					else {
						modeloReservas.eliminarReserva(Integer.parseInt(id), diaHora);
						modeloReservas.nuevaReserva(0, Integer.parseInt(id), sdf.format(currentDate), diaHora, "0", modeloActividades.siguienteIdActividad());
						JOptionPane.showMessageDialog(frmReservaParaActividad, "Estaba reservado por un cliente pero tienes prioridad.\n");
					}
				}
				else JOptionPane.showMessageDialog(frmReservaParaActividad, "La actividad elegida no tiene una sesión en el día especificado.\n");
				*/
			}
		});
		btnNewButton.setBounds(75, 132, 195, 21);
		panel.add(btnNewButton);
		
		
	}
	
	public static String getDayString(Date date, Locale locale) {
		DateFormat formatter = new SimpleDateFormat("EEEE", locale);
		return formatter.format(date);
	}
}
