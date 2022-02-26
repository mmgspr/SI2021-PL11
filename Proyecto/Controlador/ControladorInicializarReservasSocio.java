package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.TableModel;

import giis.proyecto.Modelo.ModeloReservaAsociacion;
import giis.proyecto.Modelo.ReservaDisplayDTO;
import giis.proyecto.Modelo.SwingUtil;
import giis.proyecto.Modelo.Util;
import giis.proyecto.Vista.VistaCreacionReservasSocios;
import giis.proyecto.Vista.VistaReservaSocios;

public class ControladorInicializarReservasSocio {

	VistaReservaSocios VRS;
	ModeloReservaAsociacion MRA;
	VistaCreacionReservasSocios VCRS;

	//Constructor
	public ControladorInicializarReservasSocio(VistaReservaSocios v) {
		this.VRS=v;
		this.addListenerCLRS();

	}


	public void addListenerCLRS() {
		VRS.JBCrearReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VistaCreacionReservasSocios VCRS= new VistaCreacionReservasSocios();
				
				ControladorReservasSocios CRS= new ControladorReservasSocios(MRA, VCRS);
				VCRS.frmReservaAsociacion.setVisible(true);


			}
		});
	}
}
