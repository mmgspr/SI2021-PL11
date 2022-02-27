package giis.proyecto.Controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import giis.proyecto.Modelo.ModeloCreacionActividades;

//import giis.proyecto.Vista.ActividadesView;

import giis.proyecto.Vista.ListarActividadesAdmin;

import giis.proyecto.Vista.VistaCreacionActividades;
import giis.proyecto.Vista.VistaCreacionActividades;

public class ControladorCreacionActividades {
	private VistaCreacionActividades VCA;
	

	//Constructor
	public ControladorCreacionActividades(VistaCreacionActividades v) {
		this.VCA=v;
		this.addListenerVCA();
		this.initView();
	}
	


	private void addListenerVCA(){

		VCA.JButtonCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ModeloCreacionActividades.setNuevaActividad(VCA.TFieldNombre.getText(), Integer.parseInt(VCA.TFieldIDInstalacion.getText()), (int)VCA.SpinnerPlazas.getValue(), VCA.TFieldPrecioSocios.getText(), VCA.TFieldPrecioNoSocios.getText(), VCA.TFieldInicioInscripcionS.getText(), VCA.TFieldCierreInscripcionS.getText(), VCA.TFieldCierreInscripcionNS.getText(), VCA.TFieldFechaInicio.getText(),VCA.TFieldFechaCierre.getText(), VCA.TFieldHoraInicio.getText(),VCA.TFieldHoraFin.getText());
				//ModeloCreacionActividades.setNuevaReserva(Integer.parseInt(VCA.TFieldIDInstalacion.getText()),0,0,VCA.TFieldFechaInicio.getText(),VCA.TFieldFechaCierre.getText(),VCA.TFieldHoraInicio.getText(),VCA.TFieldHoraFin.getText());
				JOptionPane.showMessageDialog(null, "Actividad creada con Exito!","Correcto",JOptionPane.INFORMATION_MESSAGE);
				VCA.frmCrearActividad.setVisible(false);
				
			}
		});
		
		VCA.JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VCA.frmCrearActividad.setVisible(false);
				
			}
				
		});
		
		VCA.CBLunes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VCA.CBLunes.isSelected()) {
					VCA.LDesdeLunes.setEnabled(true);
					VCA.LHastaLunes.setEnabled(true);
					VCA.CBDesdeLunes.setEnabled(true);
					VCA.CBHastaLunes.setEnabled(true);
				}
				else {
					VCA.LDesdeLunes.setEnabled(false);
					VCA.LHastaLunes.setEnabled(false);
					VCA.CBDesdeLunes.setEnabled(false);
					VCA.CBHastaLunes.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		VCA.CBMartes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VCA.CBMartes.isSelected()) {
					VCA.LDesdeMartes.setEnabled(true);
					VCA.LHastaMartes.setEnabled(true);
					VCA.CBDesdeMartes.setEnabled(true);
					VCA.CBHastaMartes.setEnabled(true);
				}
				else {
					VCA.LDesdeMartes.setEnabled(false);
					VCA.LHastaMartes.setEnabled(false);
					VCA.CBDesdeMartes.setEnabled(false);
					VCA.CBHastaMartes.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VCA.CBMiercoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VCA.CBMiercoles.isSelected()) {
					VCA.LDesdeMiercoles.setEnabled(true);
					VCA.LHastaMiercoles.setEnabled(true);
					VCA.CBDesdeMiercoles.setEnabled(true);
					VCA.CBHastaMiercoles.setEnabled(true);
				}
				else {
					VCA.LDesdeMiercoles.setEnabled(false);
					VCA.LHastaMiercoles.setEnabled(false);
					VCA.CBDesdeMiercoles.setEnabled(false);
					VCA.CBHastaMiercoles.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VCA.CBJueves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VCA.CBJueves.isSelected()) {
					VCA.LDesdeJueves.setEnabled(true);
					VCA.LHastaJueves.setEnabled(true);
					VCA.CBDesdeJueves.setEnabled(true);
					VCA.CBHastaJueves.setEnabled(true);
				}
				else {
					VCA.LDesdeJueves.setEnabled(false);
					VCA.LHastaJueves.setEnabled(false);
					VCA.CBDesdeJueves.setEnabled(false);
					VCA.CBHastaJueves.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VCA.CBViernes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VCA.CBViernes.isSelected()) {
					VCA.LDesdeViernes.setEnabled(true);
					VCA.LHastaViernes.setEnabled(true);
					VCA.CBDesdeViernes.setEnabled(true);
					VCA.CBHastaViernes.setEnabled(true);
				}
				else {
					VCA.LDesdeViernes.setEnabled(false);
					VCA.LHastaViernes.setEnabled(false);
					VCA.CBDesdeViernes.setEnabled(false);
					VCA.CBHastaViernes.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VCA.CBSabado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VCA.CBSabado.isSelected()) {
					VCA.LDesdeSabado.setEnabled(true);
					VCA.LHastaSabado.setEnabled(true);
					VCA.CBDesdeSabado.setEnabled(true);
					VCA.CBHastaSabado.setEnabled(true);
				}
				else {
					VCA.LDesdeSabado.setEnabled(false);
					VCA.LHastaSabado.setEnabled(false);
					VCA.CBDesdeSabado.setEnabled(false);
					VCA.CBHastaSabado.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VCA.CBDomingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VCA.CBDomingo.isSelected()) {
					VCA.LDesdeDomingo.setEnabled(true);
					VCA.LHastaDomingo.setEnabled(true);
					VCA.CBDesdeDomingo.setEnabled(true);
					VCA.CBHastaDomingo.setEnabled(true);
				}
				else {
					VCA.LDesdeDomingo.setEnabled(false);
					VCA.LHastaDomingo.setEnabled(false);
					VCA.CBDesdeDomingo.setEnabled(false);
					VCA.CBHastaDomingo.setEnabled(false);
				}
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VCA.datePickerFechaInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
				
			}
		});
		
		VCA.datePickerFechaFin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VCA.TFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VCA.TFieldPrecioSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VCA.TFieldPrecioNoSocios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VCA.CBInstalacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		VCA.CBInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(VCA.CBInstalacion.getSelectedIndex());
				if ((!VCA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(!VCA.datePickerFechaFin.getJFormattedTextField().getText().isEmpty())&&(!VCA.TFieldNombre.getText().isEmpty())&&(!VCA.TFieldPrecioSocios.getText().isEmpty())&&(!VCA.TFieldPrecioNoSocios.getText().isEmpty())&&(VCA.CBInstalacion.getSelectedIndex()>0)&&(VCA.CBInscripcion.getSelectedIndex()>0)
						&&((VCA.CBLunes.isSelected())||(VCA.CBMartes.isSelected())||(VCA.CBMiercoles.isSelected())||(VCA.CBJueves.isSelected())||(VCA.CBViernes.isSelected())||(VCA.CBSabado.isSelected())||(VCA.CBDomingo.isSelected()))) {
					VCA.JButtonCrear.setEnabled(true);
				}
				else {
					VCA.JButtonCrear.setEnabled(false);
				}
			}
		});
		
		
		
		
	}
	
	
	public void initView() {
		ModeloCreacionActividades.ObtenerInstalaciones(VCA.CBInstalacion);
		ModeloCreacionActividades.ObtenerInscripciones(VCA.CBInscripcion);
		VCA.frmCrearActividad.setVisible(true);
	}
	
	












}
