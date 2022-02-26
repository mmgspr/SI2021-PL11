package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import giis.demo.tkrun.CarreraDisplayDTO;
import giis.demo.tkrun.CarreraEntity;
import giis.demo.tkrun.CarrerasModel;
import giis.demo.tkrun.CarrerasView;
import giis.proyecto.Modelo.ApplicationException;
import giis.proyecto.Modelo.ModeloCreacionActividades;
import giis.proyecto.Modelo.ModeloInscripcionSocio;
import giis.proyecto.Modelo.SwingUtil;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaInscripcionSocio;

public class ControladorInscripcionSocio {
		private ModeloInscripcionSocio model;
		private VistaInscripcionSocio view;
		private String lastSelectedKey=""; //recuerda la ultima fila seleccionada para restaurarla cuando cambie la tabla de carreras

		public ControladorInscripcionSocio(VistaInscripcionSocio v) {
			this.view = v;
			//no hay inicializacion especifica del modelo, solo de la vista
			this.addListener();
			this.initView();
		}
		
		private void addListener() {
			view.JButtonCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					view.frmInscripcionS.setVisible(false);

				}
			});
			view.JButtonCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Date fechaActual= Util.isoStringToDate(ModeloInscripcionSocio.getFechaActual());
					Date fechaInicioS= Util.isoStringToDate(ModeloInscripcionSocio.fechaIniInscripcion(ModeloInscripcionSocio.idInscripcion((String) view.CBActividad.getSelectedItem())));
					Date fechaFinS=Util.isoStringToDate(ModeloInscripcionSocio.fechaFinInscripcion(ModeloInscripcionSocio.idInscripcion((String) view.CBActividad.getSelectedItem())));
					if(ModeloInscripcionSocio.existeSocio(view.TFieldDNI.getText())>0) {
					if(fechaActual.before(fechaFinS)&&fechaActual.after(fechaInicioS)||fechaActual.equals(fechaFinS)||fechaActual.equals(fechaInicioS)) {
						if(ModeloInscripcionSocio.plazasLibres(ModeloInscripcionSocio.idActividad((String)view.CBActividad.getSelectedItem()))
								<ModeloInscripcionSocio.plazas(ModeloInscripcionSocio.idActividad((String)view.CBActividad.getSelectedItem()))) {
							if(ModeloInscripcionSocio.estaInscrito(ModeloInscripcionSocio.idSocio(view.TFieldDNI.getText()))==0) {
								ModeloInscripcionSocio.inscripcionSocio(ModeloInscripcionSocio.idInscripcion((String) view.CBActividad.getSelectedItem()), 
										ModeloInscripcionSocio.idActividad((String)view.CBActividad.getSelectedItem()), ModeloInscripcionSocio.idSocio(view.TFieldDNI.getText()), 0);
								JOptionPane.showMessageDialog(null, "Socio inscrito con éxito","Inscrito",JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null, "El socio ya esta inscrito para esa actividad","Error", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Se ha introducido a la lista de espera","Error", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Debe estar en plazo de inscripcion","Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "No existe el socio asociado a ese DNI","Error", JOptionPane.ERROR_MESSAGE);
				}
				}
			});
		}
		
		public void initView() {
			ModeloInscripcionSocio.ObtenerActividades(view.CBActividad);
			view.frmInscripcionS.setVisible(true);
		}
}
