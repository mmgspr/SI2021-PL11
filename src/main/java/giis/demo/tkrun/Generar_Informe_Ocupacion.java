package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Generar_Informe_Ocupacion {

	private JFrame frame;
	private JComboBox comboBox;
	private InstalacionesModel modeloInstalaciones = new InstalacionesModel();
	private ReservasModel modeloReservas = new ReservasModel();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Generar_Informe_Ocupacion window = new Generar_Informe_Ocupacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Generar_Informe_Ocupacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDateChooser dateChooserFin = new JDateChooser();
		
		JLabel lblNewLabel = new JLabel("Fin de Periodo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setToolTipText("");
		
		JLabel LabelPeriodo = new JLabel("Inicio de Periodo:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona una instalacion:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox();
		rellenaCombo();
		
		JButton btnNewButton = new JButton("Generar informe");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date ini = dateChooserInicio.getDate();
				Date fin = dateChooserFin.getDate();
				
				if(ini == null || fin == null || ini.after(fin)) {
					JOptionPane.showMessageDialog(frame,
						    "El periodo introducido es erroneo.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else {
					long id = (long) modeloInstalaciones.getIdInstalacion(comboBox.getSelectedItem().toString()).get(0)[0];
					Object[] vector= calcula(id, ini, fin);
					String fecha_ini = sdf2.format(ini);
					String fecha_fin = sdf2.format(fin);
					try {
						String ruta = "src/main/resources/InformeInstalación_"+id+"_del_"+fecha_ini+"_al_"+fecha_fin;
			            String titulo = "Informe sobre la ocupación de la instalación '"+comboBox.getSelectedItem().toString() +"'\n";
			            String contenido = "El porcentaje de ocupación es de: "+ (double) vector[2] * 100 + "%.\n" + "Total horas: "+ (long) vector[1]+ " Horas ocupadas: "+ (long) vector[0];
			            
			            File file = new File(ruta);
			            // Si el archivo no existe es creado
			            if (!file.exists()) {
			                file.createNewFile();
			            }
			            FileWriter fw = new FileWriter(file);
			            BufferedWriter bw = new BufferedWriter(fw);
			            bw.write(titulo);
			            bw.write(contenido);
					    bw.close();
					    
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(44)
									.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(LabelPeriodo, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(dateChooserInicio, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, 0, 259, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
									.addGap(51)
									.addComponent(dateChooserFin, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(241)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
							.addGap(127)))
					.addGap(139))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(dateChooserFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(LabelPeriodo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooserInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(64))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void rellenaCombo() {
		List<Object[]> lista = modeloInstalaciones.getInstalaciones();
		for(int i = 0; i<lista.size(); i++) {
			comboBox.addItem(lista.get(i)[0]);
		}
	}
	public Object[] calcula(long id, Date ini, Date fin) {
		double porcentaje =0.0;
		Object[] vector = new Object[3];
		String fecha_ini = sdf.format(ini);
		String fecha_fin = sdf.format(fin);
		long reservas =modeloReservas.getTotalReservasInstalacion(id, fecha_ini, fecha_fin);
		long diferencia = fin.getTime() - ini.getTime();
		TimeUnit time = TimeUnit.DAYS;
		long total = time.convert(diferencia, TimeUnit.MILLISECONDS);
		
		porcentaje = reservas*1.0/(total*13);

		vector[0]=reservas;
		vector[1]=total*13;
		vector[2]=porcentaje;
		
		return vector;
	}
}
