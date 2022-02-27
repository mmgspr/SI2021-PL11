package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import giis.proyecto.Modelo.ModeloLoginSocio;
import giis.proyecto.Modelo.ModeloReservaAsociacion;
import giis.proyecto.Vista.VistaLoginSocio;
import giis.proyecto.Vista.VistaReservaSocios;



public class ControladorLoginSocio {
	VistaLoginSocio VLS;
	ModeloReservaAsociacion MRA;

	public ControladorLoginSocio(VistaLoginSocio view){
		this.VLS=view;
		this.addListenerCLS();

	}


	public void addListenerCLS(){
		VLS.btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int existeSocio=ModeloLoginSocio.CompruebaSocio(VLS.TFieldUsuario.getText());
				System.out.println(existeSocio);
				if (existeSocio>=1) {
					String contraseña=ModeloLoginSocio.ObtenerContraseñaSocio(VLS.TFieldUsuario.getText());
					char[] pass= contraseña.toCharArray();
					if (Arrays.equals(pass,VLS.PField.getPassword())){
						VistaReservaSocios VRS= new VistaReservaSocios();
						@SuppressWarnings("unused")
						ControladorInicializarReservasSocio CIRS= new ControladorInicializarReservasSocio(VRS);
						@SuppressWarnings("unused")
						ControladorListaReservasSocio CLRS= new ControladorListaReservasSocio(VRS, VLS.TFieldUsuario.getText());
						VRS.frameReservasSocios.setVisible(true);
						VLS.frmInicioDeSesion.dispose();
					}
					else {
						VLS.LabelError.setText("Contraseña Incorrecta.");
					}
				}
				else {
					VLS.LabelError.setText("Usuario Incorrecto.");
				}

			}
		});

		VLS.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VLS.frmInicioDeSesion.dispose();
			}
		});

		VLS.TFieldUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if ((!VLS.TFieldUsuario.getText().isEmpty())&&(VLS.PField.getPassword().length!=0)) {
					VLS.btnIniciarSesion.setEnabled(true);
				}
				else {
					VLS.btnIniciarSesion.setEnabled(false);
				}

			}
		});

		VLS.PField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if ((!VLS.TFieldUsuario.getText().isEmpty())&&(VLS.PField.getPassword().length!=0)) {
					VLS.btnIniciarSesion.setEnabled(true);
				}
				else {
					VLS.btnIniciarSesion.setEnabled(false);
				}

			}
		});



	}

}
