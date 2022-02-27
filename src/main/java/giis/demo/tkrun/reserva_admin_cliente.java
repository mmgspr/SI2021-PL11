package giis.demo.tkrun;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import giis.demo.util.SwingMain;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import com.toedter.calendar.JCalendar;

public class reserva_admin_cliente {

	private JFrame frmReservaInstalacion;
	private JTextField textField;
	private InstalacionesModel modelo = new InstalacionesModel();
	private SwingMain principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reserva_admin_cliente window = new reserva_admin_cliente();
					window.frmReservaInstalacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public reserva_admin_cliente() {
		initialize();
	}
	public reserva_admin_cliente(SwingMain principal) {
		initialize();
		this.principal = principal;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaInstalacion = new JFrame();
		frmReservaInstalacion.setTitle("RESERVA INSTALACION");
		frmReservaInstalacion.setBounds(100, 100, 700, 500);
		frmReservaInstalacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//obtener todas las instalaciones
		List<Object[]> lista=modelo.getInstalaciones();
		
		String[] instalaciones=new String[lista.size()];
		
		Iterator<Object[]> iterador = lista.iterator();
		
		int i=0;
		while(iterador.hasNext()) {
			instalaciones[i]=iterador.next()[0].toString();
			i++;
		}
		
		
		JPanel panel = new JPanel();
		frmReservaInstalacion.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione instalaci\u00F3n:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(42, 37, 178, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSeleccioneFechaY = new JLabel("Introduzca día:");
		lblSeleccioneFechaY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneFechaY.setBounds(113, 124, 107, 14);
		panel.add(lblSeleccioneFechaY);
		
		JLabel lblNDeSocio = new JLabel("N\u00BA de socio:");
		lblNDeSocio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNDeSocio.setBounds(42, 291, 96, 14);
		panel.add(lblNDeSocio);
		
		JButton btnNewButton = new JButton("Reservar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(538, 398, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//borrar la ventana
				frmReservaInstalacion.dispose();
			}
		});
		btnCancelar.setBounds(42, 398, 89, 23);
		panel.add(btnCancelar);
		
		textField = new JTextField();
		textField.setBounds(148, 290, 96, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		//array para introducir todos 
//		String[] instalaciones = new String[10];
//		for(int i=0; i<instalaciones.length; i++) {
//			instalaciones[i]="HOla"; //cambiar hola por el elemento de la lista del modelo que toque
//		}
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(instalaciones));
		comboBox.setBounds(205, 35, 169, 22);
		panel.add(comboBox);
		
		JLabel lblIntroduzcaMes = new JLabel("Introduzca mes:");
		lblIntroduzcaMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntroduzcaMes.setBounds(113, 149, 107, 14);
		panel.add(lblIntroduzcaMes);
		
		JLabel lblIntroduzcaAo = new JLabel("Introduzca año:");
		lblIntroduzcaAo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntroduzcaAo.setBounds(113, 174, 107, 14);
		panel.add(lblIntroduzcaAo);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha de la reserva:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(40, 99, 157, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hora de la reserva:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(40, 236, 157, 14);
		panel.add(lblNewLabel_1_1);
		
		JComboBox<Integer> comboBoxDia = new JComboBox<Integer>();
		comboBoxDia.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31}));
		comboBoxDia.setBounds(260, 122, 114, 22);
		panel.add(comboBoxDia);
		
		JComboBox<Integer> comboBoxMes = new JComboBox<Integer>();
		comboBoxMes.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}));
		comboBoxMes.setBounds(260, 147, 114, 22);
		panel.add(comboBoxMes);
		
		JComboBox<Integer> comboBoxAño = new JComboBox<Integer>();
		comboBoxAño.setModel(new DefaultComboBoxModel(new Integer[] {dateGetYear(), dateGetYear()+1}));
		comboBoxAño.setBounds(260, 172, 114, 22);
		panel.add(comboBoxAño);
		
		JComboBox comboBox_1_3 = new JComboBox();
		comboBox_1_3.setBounds(173, 234, 114, 22);
		panel.add(comboBox_1_3);
	}

	public Window getFrmReservaAdmin() {
		// TODO Auto-generated method stub
		return this.frmReservaInstalacion;
	}
	
	public int dateGetYear(){
	        Date date = new Date();
	        ZoneId timeZone = ZoneId.systemDefault();
	        LocalDate getLocalDate = date.toInstant().atZone(timeZone).toLocalDate();
	        return getLocalDate.getYear();
    
	}
}
