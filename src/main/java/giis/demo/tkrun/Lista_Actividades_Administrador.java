package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Lista_Actividades_Administrador {

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
		frmListaDeActividades.setBounds(100, 100, 541, 362);
		frmListaDeActividades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmListaDeActividades.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel LabelPeriodo = new JLabel("Periodo:");
		LabelPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LabelPeriodo.setBounds(10, 20, 57, 20);
		panel.add(LabelPeriodo);
		
		JComboBox comboBoxPeriodo = new JComboBox();
		comboBoxPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxPeriodo.setModel(new DefaultComboBoxModel(new String[] {"<selecciona uno>", "Primavera", "Verano", "Otoño", "Invierno"}));
		comboBoxPeriodo.setBounds(69, 20, 148, 21);
		panel.add(comboBoxPeriodo);
		
		JButton ButtonCancelar = new JButton("Cancelar");
		ButtonCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonCancelar.setBounds(10, 286, 101, 29);
		panel.add(ButtonCancelar);
		
		JButton ButtonGuardar = new JButton("Guardar");
		ButtonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ButtonGuardar.setBounds(416, 286, 101, 29);
		panel.add(ButtonGuardar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 507, 218);
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
	}
}
