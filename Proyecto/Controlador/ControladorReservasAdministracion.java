package giis.proyecto.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.List;

//import javax.swing.JComboBox;
import javax.swing.JOptionPane;
//import javax.swing.table.TableModel;

//import giis.proyecto.Modelo.ActividadDisplayDTO;
//import giis.proyecto.Modelo.InscripcionesModel;
//import giis.proyecto.Modelo.ModeloNuevaReserva;
import giis.proyecto.Modelo.ModeloReservaAsociacion;
//import giis.proyecto.Modelo.ReservaDisplayDTO;
import giis.proyecto.Modelo.ModeloReservasAdministracion;
//import giis.proyecto.Modelo.SwingUtil;
import giis.proyecto.Modelo.Util;
//import giis.proyecto.Vista.VistaNuevaReserva;
import giis.proyecto.Vista.VistaCreacionReservasAdministracion;
import giis.proyecto.Vista.VistaPagoAdministracion;

public class ControladorReservasAdministracion {
	private VistaCreacionReservasAdministracion VRA;

	@SuppressWarnings("unused")
	private ModeloReservaAsociacion MRA;

	//Constructor
	public ControladorReservasAdministracion(ModeloReservaAsociacion m, VistaCreacionReservasAdministracion v) {
		this.VRA=v;
		this.MRA=m;
		this.addListenerCRA();
		this.initView();
	}


	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		return formateador.format(ahora);
	}

	public void addListenerCRA() {

		VRA.CBInstalaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VRA.CBInstalaciones.getSelectedIndex()>0) {
					int precio= Integer.parseInt(ModeloReservasAdministracion.ObtenerPrecioReserva((String)VRA.CBInstalaciones.getSelectedItem()));
					int horas= (VRA.CBHasta.getSelectedIndex()-VRA.CBDesde.getSelectedIndex())+1;
					VRA.LPrecio.setText(horas*precio+" €");
					VRA.CBDesde.setEnabled(true);
					VRA.CBHasta.setEnabled(true);
					VRA.lblDesde.setEnabled(true);
					VRA.lblHasta.setEnabled(true);
				}
				else {
					VRA.CBDesde.setEnabled(false);
					VRA.CBHasta.setEnabled(false);
					VRA.lblDesde.setEnabled(false);
					VRA.lblHasta.setEnabled(false);
				}



				if ((!VRA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(VRA.CBInstalaciones.getSelectedIndex()>0)&&(!VRA.TFieldDni.getText().isEmpty())) {
					VRA.JButtonCrear.setEnabled(true);
				}
				else {
					VRA.JButtonCrear.setEnabled(false);
				}

			}
		});

		VRA.datePickerFechaInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((!VRA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(VRA.CBInstalaciones.getSelectedIndex()>0)&&(!VRA.TFieldDni.getText().isEmpty())) {
					VRA.JButtonCrear.setEnabled(true);
				}
				else {
					VRA.JButtonCrear.setEnabled(false);
				}
			}
		});

		VRA.CBDesde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int HorasMaximas=ModeloReservasAdministracion.ObtenerHorasMaximasReserva((String)VRA.CBInstalaciones.getSelectedItem());



				if ((VRA.CBHasta.getSelectedIndex()+1)-VRA.CBDesde.getSelectedIndex()>HorasMaximas) {
					JOptionPane.showMessageDialog(null, "No se puede reservar mas de "+ModeloReservasAdministracion.ObtenerHorasMaximasReserva((String)VRA.CBInstalaciones.getSelectedItem())+"h esta instalacion","Error",JOptionPane.ERROR_MESSAGE);
					VRA.CBHasta.setSelectedIndex(VRA.CBDesde.getSelectedIndex());
					int precio= Integer.parseInt(ModeloReservasAdministracion.ObtenerPrecioReserva((String)VRA.CBInstalaciones.getSelectedItem()));
					int horas= (VRA.CBHasta.getSelectedIndex()-VRA.CBDesde.getSelectedIndex())+1;
					VRA.LPrecio.setText(horas*precio+" €");
				}
				if(VRA.CBHasta.getSelectedIndex()<=VRA.CBDesde.getSelectedIndex()) {
					VRA.CBHasta.setSelectedIndex(VRA.CBDesde.getSelectedIndex());
				}
				int precio= Integer.parseInt(ModeloReservasAdministracion.ObtenerPrecioReserva((String)VRA.CBInstalaciones.getSelectedItem()));
				int horas= (VRA.CBHasta.getSelectedIndex()-VRA.CBDesde.getSelectedIndex())+1;
				VRA.LPrecio.setText(horas*precio+" €");

			}
		});

		VRA.CBHasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DateFormat dateFormat = new SimpleDateFormat ("kk:mm");

					String horaInicio=(String)VRA.CBDesde.getSelectedItem();
					String horaFin=(String)VRA.CBHasta.getSelectedItem();
					int HorasMaximas=ModeloReservasAdministracion.ObtenerHorasMaximasReserva((String)VRA.CBInstalaciones.getSelectedItem());


					Date date1, date2;
					date1 = dateFormat.parse(horaInicio);
					date2 = dateFormat.parse(horaFin);

					//System.out.println(date1);
					//System.out.println(date2);

					if (date1.compareTo(date2)>=0){
						JOptionPane.showMessageDialog(null, "Por favor, seleccione una hora posterior a la de inicio","Error",JOptionPane.ERROR_MESSAGE);
						VRA.CBHasta.setSelectedIndex(VRA.CBDesde.getSelectedIndex());

					}
					else {
						if ((VRA.CBHasta.getSelectedIndex()+1)-VRA.CBDesde.getSelectedIndex()>HorasMaximas) {
							JOptionPane.showMessageDialog(null, "No se puede reservar mas de "+ModeloReservasAdministracion.ObtenerHorasMaximasReserva((String)VRA.CBInstalaciones.getSelectedItem())+"h esta instalacion","Error",JOptionPane.ERROR_MESSAGE);
							VRA.CBHasta.setSelectedIndex(VRA.CBDesde.getSelectedIndex());

						}
					}
					int precio= Integer.parseInt(ModeloReservasAdministracion.ObtenerPrecioReserva((String)VRA.CBInstalaciones.getSelectedItem()));
					int horas= (VRA.CBHasta.getSelectedIndex()-VRA.CBDesde.getSelectedIndex())+1;
					VRA.LPrecio.setText(horas*precio+" €");


				} catch (ParseException parseException){
					parseException.printStackTrace();
				}

			}
		});

		VRA.TFieldDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char charecter = e.getKeyChar();
				if (Character.isLowerCase(charecter)) {
					e.setKeyChar(Character.toUpperCase(charecter));
				}
			}
		});

		VRA.TFieldDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				if ((!VRA.datePickerFechaInicio.getJFormattedTextField().getText().isEmpty())&&(VRA.CBInstalaciones.getSelectedIndex()>0)&&(!VRA.TFieldDni.getText().isEmpty())) {
					VRA.JButtonCrear.setEnabled(true);
				}
				else {
					VRA.JButtonCrear.setEnabled(false);
				}

			}
		});



		VRA.JButtonCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date fechaActual= Util.isoStringToDate(getFechaActual());
				Date fechaInicioS= Util.isoStringToDate(VRA.datePickerFechaInicio.getJFormattedTextField().getText());
				int idSocio=0;
				int existeSocio=ModeloReservasAdministracion.existeSocio(VRA.TFieldDni.getText());

				if(existeSocio>=1) {
					idSocio=ModeloReservasAdministracion.ObtenerIdSocio(VRA.TFieldDni.getText());
					if(fechaInicioS.equals(fechaActual)||(fechaInicioS.after(fechaActual))) {
						if(ModeloReservasAdministracion.comprobarPlazoMaximo((String)VRA.CBInstalaciones.getSelectedItem(),VRA.datePickerFechaInicio.getJFormattedTextField().getText())) {
							int conflicto=ModeloReservasAdministracion.comprobarConflictoReservas(ModeloReservasAdministracion.ObtenerIdInstalacion((String)VRA.CBInstalaciones.getSelectedItem()),VRA.datePickerFechaInicio.getJFormattedTextField().getText(),VRA.datePickerFechaInicio.getJFormattedTextField().getText(),(String)VRA.CBDesde.getSelectedItem(),(String)VRA.CBHasta.getSelectedItem());
							if(conflicto>=1) {
								JOptionPane.showMessageDialog(null, "La instalacion esta ocupada para esa fecha", "Error",JOptionPane.ERROR_MESSAGE);
							}
							else {
								int idInstalacion=ModeloReservasAdministracion.ObtenerIdInstalacion((String)VRA.CBInstalaciones.getSelectedItem());
								String nombreSocio=ModeloReservasAdministracion.obtenerNombreSocio(VRA.TFieldDni.getText());
								String apellidosSocio=ModeloReservasAdministracion.obtenerApellidosSocio(VRA.TFieldDni.getText());
								String[] options= {"Añadir a la cuota Mensual","Pagar ahora"};
								int pago=JOptionPane.showOptionDialog(null,"Como desea realizar el pago","Pago",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null,options,null);

								if (pago==0) {
									//Añadir pago al numero de cuenta del socio y crear reserva
									System.out.print(VRA.datePickerFechaInicio.getJFormattedTextField().getText());
									ModeloReservasAdministracion.setNuevaReserva(idInstalacion,idSocio,VRA.datePickerFechaInicio.getJFormattedTextField().getText(),(String)VRA.CBDesde.getSelectedItem(),(String)VRA.CBHasta.getSelectedItem());
									ModeloReservasAdministracion.CrearResguardo(VRA.TFieldDni.getText(),(String)VRA.CBInstalaciones.getSelectedItem(),VRA.datePickerFechaInicio.getJFormattedTextField().getText(),(String)VRA.CBDesde.getSelectedItem(), (String)VRA.CBHasta.getSelectedItem(), nombreSocio, apellidosSocio);
									JOptionPane.showMessageDialog(null, "Reserva creada con exito","Correcto",JOptionPane.INFORMATION_MESSAGE);
									VRA.frmReservaAsociacion.dispose();

								}
								else if (pago==1) {
									VRA.frmReservaAsociacion.setEnabled(false);
									int precio= Integer.parseInt(ModeloReservasAdministracion.ObtenerPrecioReserva((String)VRA.CBInstalaciones.getSelectedItem()));
									int horas= (VRA.CBHasta.getSelectedIndex()-VRA.CBDesde.getSelectedIndex())+1;
									VistaPagoAdministracion VPA=new VistaPagoAdministracion();
									@SuppressWarnings("unused")
									ControladorPagoAdministracion CPA= new ControladorPagoAdministracion(VPA, VRA);
									VPA.LInstalacion.setText((String)VRA.CBInstalaciones.getSelectedItem());
									VPA.LPrecio.setText(precio+" €");
									VPA.LHoras.setText(horas+"");
									VPA.LCosteTotal.setText(precio*horas+" €");
									VPA.frmPago.setVisible(true);
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"No se puede reservar con tanta antelacion (30 dias maximo)","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Por favor, selecione una fecha posterior a la actual","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"No existe ningun socio con el DNI:"+VRA.TFieldDni.getText()+".","Error",JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		VRA.JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VRA.frmReservaAsociacion.dispose();
			}
		});
	}

	public void CreaReserva() {
		int idSocio=ModeloReservasAdministracion.ObtenerIdSocio(VRA.TFieldDni.getText());
		int idInstalacion=ModeloReservasAdministracion.ObtenerIdInstalacion((String)VRA.CBInstalaciones.getSelectedItem());
		String nombreSocio=ModeloReservasAdministracion.obtenerNombreSocio(VRA.TFieldDni.getText());
		String apellidosSocio=ModeloReservasAdministracion.obtenerApellidosSocio(VRA.TFieldDni.getText());
		ModeloReservasAdministracion.setNuevaReserva(idInstalacion,idSocio,VRA.datePickerFechaInicio.getJFormattedTextField().getText(),(String)VRA.CBDesde.getSelectedItem(),(String)VRA.CBHasta.getSelectedItem());
		ModeloReservasAdministracion.CrearResguardo(VRA.TFieldDni.getText(),(String)VRA.CBInstalaciones.getSelectedItem(),VRA.datePickerFechaInicio.getJFormattedTextField().getText(),(String)VRA.CBDesde.getSelectedItem(), (String)VRA.CBHasta.getSelectedItem(), nombreSocio, apellidosSocio);
		JOptionPane.showMessageDialog(null, "Reserva creada con exito","Correcto",JOptionPane.INFORMATION_MESSAGE);
	}

	public void initController() {
		//ActionListener define solo un metodo actionPerformed(), es una interfaz funcional que se puede invocar de la siguiente forma:
		//view.getBtnTablaActividades().addActionListener(e -> getListaActividades());
		//ademas invoco el metodo que responde al listener en el exceptionWrapper para que se encargue de las excepciones
		//VRA.getBtnTablaReservas().addActionListener(e -> SwingUtil.exceptionWrapper(() -> getListaReservas()));

		/*
		//En el caso del mouse listener (para detectar seleccion de una fila) no es un interfaz funcional puesto que tiene varios metodos
		//ver discusion: https://stackoverflow.com/questions/21833537/java-8-lambda-expressions-what-about-multiple-methods-in-nested-class
		view.getTablaActividades().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//no usa mouseClicked porque al establecer seleccion simple en la tabla de actividades
				//el usuario podria arrastrar el raton por varias filas e interesa solo la ultima
				SwingUtil.exceptionWrapper(() -> updateDetail());
			}
		});
		 */
	}

	public void initView() {
		//VRA.setFInicio(ModeloNuevaReserva.getFechaActual());
		ModeloReservasAdministracion.ObtenerInstalaciones(VRA.CBInstalaciones);

		VRA.getFrameR().setVisible(true);
	}



	/*public void getListaReservas() {
		List<ReservaDisplayDTO> reservas = ReservasModel.getListaReservas(Integer.parseInt(VRA.getIDInstalacion()), Util.isoStringToDate(VRA.getFInicio()), Util.isoStringToDate(VRA.getFFinal()));
		TableModel tmodel = SwingUtil.getTableModelFromPojos(reservas, new String[] {"id_reserva", "id_instalacion", "id_actividad", "id_socio", 
				"fecha_inicioReserva", "fecha_finReserva", "hora_inicioReserva", "hora_finReserva"});
		VRA.getTablaReservas().setModel(tmodel);
		SwingUtil.autoAdjustColumns(VRA.getTablaReservas());

	}*/
}

