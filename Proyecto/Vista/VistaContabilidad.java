package giis.proyecto.Vista;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;



public class VistaContabilidad {

	public JFrame frameContabilidad;
	public JTable tablaContabilidad;
	private JScrollPane scrollPane;
	public JLabel LabelPagado;
	public JLabel LabelPendiente;
	public JLabel LabelTotal;
	public JButton btnSalir;
	public JButton btnRealizarCobrosPendientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaContabilidad window = new VistaContabilidad();
					window.frameContabilidad.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaContabilidad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameContabilidad = new JFrame();
		frameContabilidad.setTitle("Contabilidad\r\n");
		frameContabilidad.setBounds(100, 100, 688, 543);
		frameContabilidad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		tablaContabilidad = new JTable();
		tablaContabilidad.setBounds(0, 0, 622, 16);
		//frameReservasSocios.getContentPane().add(tablaReservasSocios);
		tablaContabilidad.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id Pago", "Usuario", "Fecha", "Descripcion", "Importe", "Estado"
			}
		));
		frameContabilidad.getContentPane().setLayout(null);
		tablaContabilidad.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaContabilidad.setBackground(Color.LIGHT_GRAY);
		
		scrollPane = new JScrollPane(tablaContabilidad);
		scrollPane.setBounds(10, 0, 652, 396);
		frameContabilidad.getContentPane().add(scrollPane);
		
		LabelPagado = new JLabel("Ingresos pagados:\r\n");
		LabelPagado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelPagado.setBounds(10, 407, 222, 20);
		frameContabilidad.getContentPane().add(LabelPagado);
		
		LabelPendiente = new JLabel("Ingresos pendientes:\r\n");
		LabelPendiente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		LabelPendiente.setBounds(10, 435, 222, 20);
		frameContabilidad.getContentPane().add(LabelPendiente);
		
		LabelTotal = new JLabel("Ingresos Totales:\r\n");
		LabelTotal.setFont(new Font("Tahoma", Font.BOLD, 17));
		LabelTotal.setBounds(10, 465, 222, 20);
		frameContabilidad.getContentPane().add(LabelTotal);
		
		btnRealizarCobrosPendientes = new JButton("Realizar Cobros Pendientes");
		
		btnRealizarCobrosPendientes.setBounds(269, 409, 207, 78);
		frameContabilidad.getContentPane().add(btnRealizarCobrosPendientes);
		
		btnSalir = new JButton("Salir\r\n");
		
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalir.setBounds(486, 409, 175, 78);
		frameContabilidad.getContentPane().add(btnSalir);
	}
}
