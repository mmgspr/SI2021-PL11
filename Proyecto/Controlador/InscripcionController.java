package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.proyecto.Modelo.InscripcionesModel;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.CreacionInscripcionesView;

public class InscripcionController {
	private CreacionInscripcionesView CIV;

	//Constructor
	public InscripcionController(CreacionInscripcionesView civ) {
		this.CIV=civ;
		this.addListenerCIV();

	}
	
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}

	private void addListenerCIV() {
		CIV.JButtonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CIV.frmNuevaInscripcion.setVisible(false);

			}
		});

		CIV.JButtonCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date fechaActual= Util.isoStringToDate(getFechaActual());
				Date fechaInicioS= Util.isoStringToDate(CIV.datePickerFechaInicio.getJFormattedTextField().getText());
				Date fechaFinS=Util.isoStringToDate(CIV.datePickerFechaFin.getJFormattedTextField().getText());
				Date fechaFinNS=Util.isoStringToDate(CIV.datePickerFechaFinNS.getJFormattedTextField().getText());
				
				if(fechaInicioS.equals(fechaActual)||(fechaInicioS.after(fechaActual))) {
					if(fechaFinS.after(fechaInicioS)) {
						if(fechaFinNS.after(fechaFinS)) {
							InscripcionesModel.nuevaInscripcion(CIV.TFieldEtiqueta.getText(), CIV.getDatePickerFechaIniS(), CIV.getDatePickerFechaFinS(),CIV.getDatePickerFechaFinNS());
							JOptionPane.showMessageDialog(null, "Plazo de Inscripcion creado con exito","Creado",JOptionPane.INFORMATION_MESSAGE);
							CIV.frmNuevaInscripcion.setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(null, "La fecha de cierre para No Socios debe de ser posterior a la fecha de cierre para Socios","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "La fecha de cierre para Socios debe de ser posterior a la fecha de apertura","Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null,"La fecha de apertura de la inscripcion no puede ser anterior a la fecha actual","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				
			}
		});
		
		
		CIV.datePickerFechaInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.printf(CIV.getDatePickerFechaIniS());
				if ((!CIV.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFinNS.getJFormattedTextField().getText().isEmpty())&&(!CIV.TFieldEtiqueta.getText().isEmpty())) {
					CIV.JButtonCrear.setEnabled(true);
				}
				else {
					CIV.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		CIV.datePickerFechaFin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if ((!CIV.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFinNS.getJFormattedTextField().getText().isEmpty())&&(!CIV.TFieldEtiqueta.getText().isEmpty())) {
					CIV.JButtonCrear.setEnabled(true);
				}
				else {
					CIV.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		CIV.datePickerFechaFinNS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((!CIV.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFinNS.getJFormattedTextField().getText().isEmpty())&&(!CIV.TFieldEtiqueta.getText().isEmpty())) {
					CIV.JButtonCrear.setEnabled(true);
				}
				else {
					CIV.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		CIV.TFieldEtiqueta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((!CIV.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!CIV.datePickerFechaFinNS.getJFormattedTextField().getText().isEmpty())&&(!CIV.TFieldEtiqueta.getText().isEmpty())) {
					CIV.JButtonCrear.setEnabled(true);
				}
				else {
					CIV.JButtonCrear.setEnabled(false);
				}
			}
		});

	}

}


