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
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.proyecto.Modelo.InscripcionesModel;
import giis.proyecto.Modelo.ModeloCreacionActividades;
import giis.proyecto.Modelo.ModeloModificarActividad;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.CreacionInscripcionesView;
import giis.proyecto.Vista.VistaCreacionActividades;
import giis.proyecto.Vista.VistaModificarActividad;

public class ControladorModificarActividad {

	private VistaModificarActividad VMA;
	
	//Constructor
	public ControladorModificarActividad(VistaModificarActividad view) {
		this.VMA=view;
		this.addListenerVMA();
		this.initView();
	}
	
	private void addListenerVMA() {
		VMA.JButtonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VMA.frmCrearActividad.setVisible(false);
			}
		});
		VMA.JButtonCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date=Util.isoStringToDate(VMA.datePickerFechaInicio.getJFormattedTextField().getText());
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(date);
				int i=0;

				ModeloModificarActividad.delete((int) VMA.CBIDActividades.getSelectedItem());
				ModeloModificarActividad.deleteR((int) VMA.CBIDActividades.getSelectedItem());
				
				while((date.before(Util.isoStringToDate((VMA.datePickerFechaFin.getJFormattedTextField().getText())))||date.equals(Util.isoStringToDate((VMA.datePickerFechaFin.getJFormattedTextField().getText()))))&&i<7) {
					System.out.println("Entra en el while");
					System.out.println(calendar.getTime());
					System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
					if(VMA.CBLunes.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==2) {
						System.out.println("L");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBLunes.getText(), (String) VMA.CBDesdeLunes.getSelectedItem(), (String)  VMA.CBHastaLunes.getSelectedItem());
					}
					if(VMA.CBMartes.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==3) {
						System.out.println("M");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBMartes.getText(),(String) VMA.CBDesdeMartes.getSelectedItem(),(String) VMA.CBHastaMartes.getSelectedItem());
					}
					if(VMA.CBMiercoles.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==4) {
						System.out.println("X");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBMiercoles.getText(),(String) VMA.CBDesdeMiercoles.getSelectedItem(),(String) VMA.CBHastaMiercoles.getSelectedItem());
					}
					if(VMA.CBJueves.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==5) {
						System.out.println("J");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBJueves.getText(),(String) VMA.CBDesdeJueves.getSelectedItem(),(String) VMA.CBHastaJueves.getSelectedItem());
					}
					if(VMA.CBViernes.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==6) {
						System.out.println("V");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBViernes.getText(),(String) VMA.CBDesdeViernes.getSelectedItem(),(String) VMA.CBHastaViernes.getSelectedItem());
					}
					if(VMA.CBSabado.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==7) {
						System.out.println("S");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBSabado.getText(),(String) VMA.CBDesdeSabado.getSelectedItem(),(String) VMA.CBHastaSabado.getSelectedItem());
					}
					if(VMA.CBDomingo.isSelected()&&calendar.get(Calendar.DAY_OF_WEEK)==1) {
						System.out.println("D");
						ModeloModificarActividad.setNuevoHorario((int) VMA.CBIDActividades.getSelectedItem(), VMA.CBDomingo.getText(),(String) VMA.CBDesdeDomingo.getSelectedItem(),(String) VMA.CBHastaDomingo.getSelectedItem());
					}
					calendar.add(Calendar.DAY_OF_YEAR, +1);
					date=calendar.getTime();
					i++;

				}

				ModeloModificarActividad.update(VMA.TFieldNombre.getText(), ModeloModificarActividad.ObtenerIdInstalacion((String) VMA.CBInstalacion.getSelectedItem()),(int) VMA.SpinnerPlazas.getValue(),
						VMA.TFieldPrecioSocios.getText(), VMA.TFieldPrecioNoSocios.getText(), VMA.datePickerFechaInicio.getJFormattedTextField().getText(), VMA.datePickerFechaFin.getJFormattedTextField().getText(), 
						ModeloModificarActividad.ObtenerIdInscripcion((String) VMA.CBInscripcion.getSelectedItem()),
						(int) VMA.CBIDActividades.getSelectedItem());
			}
		});
		
		VMA.CBLunes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VMA.CBLunes.isSelected()) {
					VMA.LDesdeLunes.setEnabled(true);
					VMA.LHastaLunes.setEnabled(true);
					VMA.CBDesdeLunes.setEnabled(true);
					VMA.CBHastaLunes.setEnabled(true);
				}
				else {
					VMA.LDesdeLunes.setEnabled(false);
					VMA.LHastaLunes.setEnabled(false);
					VMA.CBDesdeLunes.setEnabled(false);
					VMA.CBHastaLunes.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		VMA.CBMartes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VMA.CBMartes.isSelected()) {
					VMA.LDesdeMartes.setEnabled(true);
					VMA.LHastaMartes.setEnabled(true);
					VMA.CBDesdeMartes.setEnabled(true);
					VMA.CBHastaMartes.setEnabled(true);
				}
				else {
					VMA.LDesdeMartes.setEnabled(false);
					VMA.LHastaMartes.setEnabled(false);
					VMA.CBDesdeMartes.setEnabled(false);
					VMA.CBHastaMartes.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VMA.CBMiercoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VMA.CBMiercoles.isSelected()) {
					VMA.LDesdeMiercoles.setEnabled(true);
					VMA.LHastaMiercoles.setEnabled(true);
					VMA.CBDesdeMiercoles.setEnabled(true);
					VMA.CBHastaMiercoles.setEnabled(true);
				}
				else {
					VMA.LDesdeMiercoles.setEnabled(false);
					VMA.LHastaMiercoles.setEnabled(false);
					VMA.CBDesdeMiercoles.setEnabled(false);
					VMA.CBHastaMiercoles.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VMA.CBJueves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VMA.CBJueves.isSelected()) {
					VMA.LDesdeJueves.setEnabled(true);
					VMA.LHastaJueves.setEnabled(true);
					VMA.CBDesdeJueves.setEnabled(true);
					VMA.CBHastaJueves.setEnabled(true);
				}
				else {
					VMA.LDesdeJueves.setEnabled(false);
					VMA.LHastaJueves.setEnabled(false);
					VMA.CBDesdeJueves.setEnabled(false);
					VMA.CBHastaJueves.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VMA.CBViernes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VMA.CBViernes.isSelected()) {
					VMA.LDesdeViernes.setEnabled(true);
					VMA.LHastaViernes.setEnabled(true);
					VMA.CBDesdeViernes.setEnabled(true);
					VMA.CBHastaViernes.setEnabled(true);
				}
				else {
					VMA.LDesdeViernes.setEnabled(false);
					VMA.LHastaViernes.setEnabled(false);
					VMA.CBDesdeViernes.setEnabled(false);
					VMA.CBHastaViernes.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&
						(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())
						&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())
								||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VMA.CBSabado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VMA.CBSabado.isSelected()) {
					VMA.LDesdeSabado.setEnabled(true);
					VMA.LHastaSabado.setEnabled(true);
					VMA.CBDesdeSabado.setEnabled(true);
					VMA.CBHastaSabado.setEnabled(true);
				}
				else {
					VMA.LDesdeSabado.setEnabled(false);
					VMA.LHastaSabado.setEnabled(false);
					VMA.CBDesdeSabado.setEnabled(false);
					VMA.CBHastaSabado.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VMA.CBDomingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VMA.CBDomingo.isSelected()) {
					VMA.LDesdeDomingo.setEnabled(true);
					VMA.LHastaDomingo.setEnabled(true);
					VMA.CBDesdeDomingo.setEnabled(true);
					VMA.CBHastaDomingo.setEnabled(true);
				}
				else {
					VMA.LDesdeDomingo.setEnabled(false);
					VMA.LHastaDomingo.setEnabled(false);
					VMA.CBDesdeDomingo.setEnabled(false);
					VMA.CBHastaDomingo.setEnabled(false);
				}
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VMA.datePickerFechaInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VMA.datePickerFechaFin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VMA.TFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VMA.TFieldPrecioSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VMA.TFieldPrecioNoSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VMA.CBInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VMA.CBInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(VMA.CBInstalacion.getSelectedIndex());
				if ((!VMA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VMA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VMA.TFieldNombre.getText().isEmpty())&&(!VMA.TFieldPrecioSocios.getText().isEmpty())&&(!VMA.TFieldPrecioNoSocios.getText().isEmpty())&&(VMA.CBInstalacion.getSelectedIndex()>0)&&(VMA.CBInscripcion.getSelectedIndex()>0)
						&&((VMA.CBLunes.isSelected())||(VMA.CBMartes.isSelected())||(VMA.CBMiercoles.isSelected())||(VMA.CBJueves.isSelected())||(VMA.CBViernes.isSelected())||(VMA.CBSabado.isSelected())||(VMA.CBDomingo.isSelected()))) {
					VMA.JButtonCrear.setEnabled(true);
				}
				else {
					VMA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		
		
		
	}
	
	public void initView() {
		ModeloModificarActividad.ObtenerIDActividades(VMA.CBIDActividades);
		ModeloModificarActividad.ObtenerInstalaciones(VMA.CBInstalacion);
		ModeloModificarActividad.ObtenerInscripciones(VMA.CBInscripcion);
		VMA.frmCrearActividad.setVisible(true);
	}
}
