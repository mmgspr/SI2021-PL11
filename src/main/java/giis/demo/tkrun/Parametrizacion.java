package giis.demo.tkrun;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Parametrizacion {

	private JFrame frmParametrizacion;
	private Login vLogin;
	
	private JSpinner spDiasComp;
	private JSpinner spDiaAnte;
	private JSpinner spHoraMax;
	private JSpinner spHoraDiaMax;
	private JSpinner spHoraPeriodoMax;
	private JButton btnActualizarParametros;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Parametrizacion window = new Parametrizacion();
					window.frmParametrizacion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Parametrizacion() {
		initialize();
	}
	public Parametrizacion(Login l) {
		this.vLogin=l;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmParametrizacion = new JFrame();
		frmParametrizacion.setTitle("Parametrizacion");
		frmParametrizacion.setBounds(100, 100, 351, 543);
		frmParametrizacion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Dias Comproba");
		lblNewLabel.setBounds(58, 79, 122, 14);
		
		spDiasComp = new JSpinner();
		spDiasComp.setBounds(214, 76, 49, 20);
		frmParametrizacion.getContentPane().add(spDiasComp);
		spDiasComp.setValue(vLogin.getDia_comprobar());
		
		
		
		frmParametrizacion.getContentPane().setLayout(null);
		frmParametrizacion.getContentPane().add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Dias antelacion");
		lblNewLabel_1.setBounds(58, 134, 122, 14);
		frmParametrizacion.getContentPane().add(lblNewLabel_1);
		
		
		spDiaAnte = new JSpinner();
		spDiaAnte.setBounds(214, 131, 49, 20);
		frmParametrizacion.getContentPane().add(spDiaAnte);
		spDiaAnte.setValue(vLogin.getDiasAntelacion());
		
		
		JLabel lblNewLabel_2 = new JLabel("Hora Max");
		lblNewLabel_2.setBounds(58, 189, 122, 14);
		frmParametrizacion.getContentPane().add(lblNewLabel_2);
		
		spHoraMax = new JSpinner();
		spHoraMax.setBounds(214, 186, 49, 20);
		frmParametrizacion.getContentPane().add(spHoraMax);
		spHoraMax.setValue(vLogin.getHora_Max());
		
		JLabel lblNewLabel_3 = new JLabel("Horas por dia max");
		lblNewLabel_3.setBounds(58, 249, 122, 14);
		frmParametrizacion.getContentPane().add(lblNewLabel_3);
		
		spHoraDiaMax = new JSpinner();
		spHoraDiaMax.setBounds(214, 246, 49, 20);
		frmParametrizacion.getContentPane().add(spHoraDiaMax);
		spHoraDiaMax.setValue(vLogin.getHorasDiaMax());
		
		JLabel lblNewLabel_4 = new JLabel("Horas periodo max");
		lblNewLabel_4.setBounds(58, 314, 122, 14);
		frmParametrizacion.getContentPane().add(lblNewLabel_4);
		
		spHoraPeriodoMax = new JSpinner();
		spHoraPeriodoMax.setBounds(214, 311, 49, 20);
		frmParametrizacion.getContentPane().add(spHoraPeriodoMax);
		spHoraPeriodoMax.setValue(vLogin.getHorasPeriodoMax());
		
		btnActualizarParametros = new JButton("Actualizar parametros");
		btnActualizarParametros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setParametros();
			}
		});
		btnActualizarParametros.setBounds(83, 429, 157, 23);
		frmParametrizacion.getContentPane().add(btnActualizarParametros);
	}
	private void setParametros() {
		vLogin.setDia_comprobar((int)spDiasComp.getValue());
		vLogin.setDiasAntelacion((int)spDiaAnte.getValue());
		vLogin.setHora_Max((int)spHoraMax.getValue());
		vLogin.setHorasDiaMax((int)spHoraDiaMax.getValue());
		vLogin.setHorasPeriodoMax((int)spHoraPeriodoMax.getValue());
		
	}

	public JFrame getFrmParametrizacion() {
		return frmParametrizacion;
	}

	public void setFrmParametrizacion(JFrame frmParametrizacion) {
		this.frmParametrizacion = frmParametrizacion;
	}
}
