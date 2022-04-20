package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class CancelarActividad {

	private JFrame frmCancelarActividad;
	private JComboBox comboBox;
	private ActividadesModel modeloActividades=new ActividadesModel();
	private ReservasModel modeloReservas=new ReservasModel();
	private SesionesModel modeloSesiones=new SesionesModel();
	private InscripcionesModel modeloInscripciones=new InscripcionesModel();
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelarActividad window = new CancelarActividad();
					window.frmCancelarActividad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CancelarActividad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCancelarActividad = new JFrame();
		frmCancelarActividad.setTitle("Cancelar Actividad");
		frmCancelarActividad.setBounds(100, 100, 700, 500);
		frmCancelarActividad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCancelarActividad.setVisible(false);
			}
		});
		
		JButton btnCancelarActividad = new JButton("Cancelar Actividad");
		btnCancelarActividad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avisar();
				cancelarActividad();
			}
		});
		
		textArea= new JTextArea();
		
		JLabel lblMensaje = new JLabel("Mensaje:");
		
		JLabel lblNewLabel = new JLabel("Selecciona una actividad:");
		
		comboBox = new JComboBox();
		rellenaComboBox();
		
		GroupLayout groupLayout = new GroupLayout(frmCancelarActividad.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMensaje, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
					.addComponent(btnCancelarActividad)
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(205)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(260, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(95)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(119, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(lblMensaje)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelarActividad)
						.addComponent(btnNewButton))
					.addGap(19))
		);
		frmCancelarActividad.getContentPane().setLayout(groupLayout);
	}
	
	public void rellenaComboBox() {
		List<Object[]> lista=modeloActividades.getActividades();
		
		String[] actividades=new String[lista.size()];
		
		Iterator<Object[]> iterador = lista.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			actividades[i]=iterador.next()[0].toString();
			i++;
		}
		
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(actividades));
	}
	public void cancelarActividad(){
		String actividad=comboBox.getSelectedItem().toString();
		long id_actividad=modeloActividades.getIdActividad(actividad);
		//modeloSesiones.eliminarSesiones(id_actividad);
		//modeloInscripciones.eliminarInscripciones(id_actividad);
		modeloReservas.eliminarReservaActividad(id_actividad);
		modeloActividades.eliminarActividad(id_actividad);
		generaTXT(actividad, id_actividad);
		textArea.setText("");
		JOptionPane.showMessageDialog(frmCancelarActividad, "Se ha eliminado la actividad '"+ actividad + "'.");
		rellenaComboBox();
	}
	
	public void avisar() {
		String actividad=comboBox.getSelectedItem().toString();
		long id_actividad=modeloActividades.getIdActividad(actividad);
		List<Object[]> lista = modeloInscripciones.getPersonasActividad(id_actividad);
		String mensaje = "";
		for(int i=0; i<lista.size(); i++) {
			mensaje+=lista.get(i)[0].toString() + "\n";
		}
		JOptionPane.showMessageDialog(frmCancelarActividad,
			    mensaje,
			    "Clientes previamente inscritos",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
	public JFrame getFrmCancelarActividad() {
		return frmCancelarActividad;
	}
	public void generaTXT(String actividad, long id_actividad) {
		try {
            String ruta = "src/main/resources/CancelActividad"+id_actividad;
            String contenido = "Se ha cancelado la actividad: "+ id_actividad +"\nMotivo:\n"+textArea.getText();
            File file = new File(ruta);
            // Si el archivo no existe es creado
            //if (!file.exists()) {
                file.createNewFile();
            //}
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
		    bw.close();
		    
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	}
}
