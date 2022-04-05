package giis.demo.tkrun;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giis.demo.util.SwingMain;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class inicialAdmin {

	private JFrame frmIndex;
	private Login vLogin;
	private ReservasModel modeloReservas = new ReservasModel();
	private visualizarReservasA vVisualizarReservasA;
	private reserva_admin_cliente vReservaAdmin;
	private crear_actividad vCrearActividad;
	private Lista_Actividades_Administrador vListaActividadesA;
	private crear_periodo_inscripcion vCrearPeriodoInscripcion;
	private reserva_actividad_admin vReservaActividad;
	private RegistrarCliente vRegistrarCliente;
	private reservar_sesiones_automaticamente vReservarSesionesAutomaticamente;
	
	// Variables para parametrizacion
	private Parametrizacion vParametrizacion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicialAdmin window = new inicialAdmin();
					window.frmIndex.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inicialAdmin() {

       	initialize();
   
	   
	}

	
	public inicialAdmin(Login vLogin) {
		this.vLogin=vLogin;
		initialize();
		
	}
	
	public JFrame getFrmIndex() {
		return this.frmIndex;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIndex = new JFrame();
		frmIndex.setTitle("Menú inicial");
		frmIndex.setBounds(100, 100, 531, 363);
		frmIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmIndex.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Crear Periodo de Inscripcion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCrearPeriodoInscripcion = new crear_periodo_inscripcion();
				vCrearPeriodoInscripcion.getFrmCrearActividad().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 125, 207, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1_1 = new JButton("Reservar Instalación\r\n\r\n");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lleva a reserva_admin_cliente
				vReservaAdmin = new reserva_admin_cliente();
				vReservaAdmin.getFrmReservaAdmin().setVisible(true);
			
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(10, 57, 207, 23);
		panel.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_4 = new JButton("Visualizar Reservas\r\n\r\n");
		btnNewButton_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVisualizarReservasA = new visualizarReservasA();
				vVisualizarReservasA.getFrmVisualizarReservas().setVisible(true);
			}
		});
		btnNewButton_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_4.setBounds(305, 57, 181, 23);
		panel.add(btnNewButton_1_4);
		
		JButton btnNewButton_1_5 = new JButton("Planificar Actividad\r\n");
		btnNewButton_1_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCrearActividad = new crear_actividad();
				vCrearActividad.getFrmCrearActividad().setVisible(true);
			}
		});
		btnNewButton_1_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_5.setBounds(305, 91, 181, 23);
		panel.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_6 = new JButton("Reserva para Actividad\r\n\r\n");
		btnNewButton_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vReservaActividad = new reserva_actividad_admin();
				vReservaActividad.getFrmReservaActividad().setVisible(true);
			}
		});
		btnNewButton_1_6.setBounds(305, 125, 181, 23);
		panel.add(btnNewButton_1_6);
		
		JButton btnNewButton_1_7 = new JButton("Lista Actividades\r\n\r\n");
		btnNewButton_1_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lleva a Lista_Actividades_Administrador
				vListaActividadesA = new Lista_Actividades_Administrador();
				vListaActividadesA.getFrmListaDeActividades().setVisible(true);
			
			}
		});
		btnNewButton_1_7.setBounds(10, 159, 207, 23);
		panel.add(btnNewButton_1_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 517, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Administración");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 114, 24);
		panel.add(lblNewLabel);
		

		JButton buttonGenerar = new JButton("Generar Txt Cuotas");
		buttonGenerar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//dia de hoy
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	            String[] vector1=date.split("-"); 
			    int dia=Integer.parseInt(vector1[2].split("-")[0]);
			   // System.out.println(dia);
			   
			    
			    
			    
			    try {

			    	 if(dia==vLogin.getDia_comprobar()) {
			  		  // System.out.println("Holaaa");
			         	modeloReservas.añadeacuota1();
			         	modeloReservas.añadeacuota2();
			  	   }
			    	

		            	String ruta = "src/main/resources/Contabilidad.txt";
			            String contenido = "Cuotas a pasar a cada socio \n";
			            File file = new File(ruta);
			            // Si el archivo no existe es creado
			            if (!file.exists()) {
			                file.createNewFile();
			            }
			            FileWriter fw = new FileWriter(file);
			            BufferedWriter bw = new BufferedWriter(fw);
			            bw.write(contenido);
			            
		            List<Object[]> listaPagos=modeloReservas.nuevaCuota1();	
		            Iterator<Object[]> iterador = listaPagos.iterator();
		            
		    		while(iterador.hasNext()) {
		    			Object[] vector = iterador.next();		    	
		    			bw.write("El socio "+vector[0]+" con el id: "+vector[1]+" ,debe pagar la cuota: "+vector[2]+
		    					"$ junto con el coste de reservas: "+vector[3]+"$ y el coste de las actividades: "+vector[4]+"$\n");
		    		}
		    		
		            
		            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		            final Runnable runnable = new Runnable() {
		                int countdownStarter = 86400;

		                public void run() {

		                    
		                    countdownStarter--;

		                    if (countdownStarter < 0) {		                     		                    	
									try {
										BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
										bw.write("");
										bw.close();
									} catch (IOException e) {
									
										e.printStackTrace();
									}
																	 
		                        scheduler.shutdown();
		                    }
		                }
		            };
		            scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);		                  				    
				    bw.close();
			       
		            
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
			    
			    
			  
			    
			    
			    
				
				
				
			}
		});
		buttonGenerar.setBounds(305, 159, 181, 24);
		panel.add(buttonGenerar);

		JButton nuevoSocioBtn = new JButton("Nuevo cliente");
		nuevoSocioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vRegistrarCliente = new RegistrarCliente(vLogin);
				vRegistrarCliente.getFrmRegistrarCliente().setVisible(true);
			}
		});
		nuevoSocioBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nuevoSocioBtn.setBounds(10, 193, 207, 23);
		panel.add(nuevoSocioBtn);
		
		JButton btnNewButton_1_5_1 = new JButton("Reservar sesiones auto.");
		btnNewButton_1_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vReservarSesionesAutomaticamente = new reservar_sesiones_automaticamente();
				vReservarSesionesAutomaticamente.getFrmReservarSesionesAutomticamente().setVisible(true);
			}
		});
		btnNewButton_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_5_1.setBounds(10, 91, 207, 23);
		panel.add(btnNewButton_1_5_1);
		Parametrizacion vParametrizacion = new Parametrizacion(vLogin);
		JButton btnParametros = new JButton("Cambiar Parametros");
		btnParametros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vParametrizacion.getFrmParametrizacion().setVisible(true);
			}
		});
		btnParametros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnParametros.setBounds(305, 193, 181, 24);
		panel.add(btnParametros);

	}

	
	
}
