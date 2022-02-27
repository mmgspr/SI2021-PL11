package giis.proyecto.Controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import giis.proyecto.Modelo.ModeloReservasAdministracion;

import giis.proyecto.Vista.VistaCreacionReservasSocios;

import giis.proyecto.Vista.VistaPagoSocios;

public class ControladorPagoSocio {
	
	private VistaPagoSocios VPA;
	private VistaCreacionReservasSocios VRA;
	
	//Constructor
		public ControladorPagoSocio(VistaPagoSocios vp, VistaCreacionReservasSocios vc) {
			this.VPA=vp;
			this.VRA=vc;
			this.addListenerVPA();
			this.initView();
			
		}
		
		public void addListenerVPA() {
			VPA.rdbtnTarjeta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(VPA.rdbtnTarjeta.isSelected()) {
						VPA.numeroTarjeta.setEnabled(true);
						VPA.btnNewButton.setEnabled(false);
					}
					else {
						VPA.numeroTarjeta.setEnabled(false);
					}
				}
			});
			VPA.rdbtnEfectivo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(VPA.rdbtnTarjeta.isSelected()) {
						VPA.numeroTarjeta.setEnabled(true);
					}
					else {
						VPA.numeroTarjeta.setEnabled(false);
					}
				}
			});
			
			VPA.numeroTarjeta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char car1 = arg0.getKeyChar();
					System.out.print(car1);
					if((VPA.numeroTarjeta.getText().length()>15)||(car1<'0' || car1>'9')) {
						arg0.consume();
					}
					if (VPA.numeroTarjeta.getText().length()==15) {
						VPA.btnNewButton.setEnabled(true);
					}
				}
			});
			
			VPA.btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int idInstalacion=ModeloReservasAdministracion.ObtenerIdInstalacion((String)VRA.CBInstalaciones.getSelectedItem());
					int idSocio=ModeloReservasAdministracion.ObtenerIdSocio(VRA.TFieldDni.getText());
					String nombreSocio=ModeloReservasAdministracion.obtenerNombreSocio(VRA.TFieldDni.getText());
					String apellidosSocio=ModeloReservasAdministracion.obtenerApellidosSocio(VRA.TFieldDni.getText());
					System.out.println((String)VRA.CBInstalaciones.getSelectedItem());
					System.out.println(idInstalacion);
					System.out.println(idSocio);
					ModeloReservasAdministracion.setNuevaReserva(idInstalacion,idSocio,VRA.getDatePickerFechaIniS(),(String)VRA.CBDesde.getSelectedItem(),(String)VRA.CBHasta.getSelectedItem());
					ModeloReservasAdministracion.CrearResguardo(VRA.TFieldDni.getText(),(String)VRA.CBInstalaciones.getSelectedItem(),VRA.getDatePickerFechaIniS(),(String)VRA.CBDesde.getSelectedItem(), (String)VRA.CBHasta.getSelectedItem(), nombreSocio, apellidosSocio);
					JOptionPane.showMessageDialog(null, "Reserva creada con exito","Correcto",JOptionPane.INFORMATION_MESSAGE);
					VPA.frmPago.dispose();
					VRA.frmReservaAsociacion.dispose();
					
					
				}
			});
		}
		
		public void initView() {
			
			VPA.frmPago.setVisible(true);
		}
		
}
