package giis.demo.tkrun;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
		btnNewButton.setBounds(10, 147, 207, 23);
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
		btnNewButton_1_5.setBounds(305, 147, 181, 23);
		panel.add(btnNewButton_1_5);
		
		JButton btnNewButton_1_6 = new JButton("Reserva para Actividad\r\n\r\n");
		btnNewButton_1_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vReservaActividad = new reserva_actividad_admin();
				vReservaActividad.getFrmReservaActividad().setVisible(true);
			}
		});
		btnNewButton_1_6.setBounds(305, 238, 181, 23);
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
		btnNewButton_1_7.setBounds(10, 238, 207, 23);
		panel.add(btnNewButton_1_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 517, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Administración");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 114, 24);
		panel.add(lblNewLabel);
		

		JButton buttonGenerar = new JButton("Generar txt cuotas");
		buttonGenerar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//dia de hoy
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
	            String[] vector1=date.split("-"); 
			    int dia=Integer.parseInt(vector1[2].split("-")[0]);
			   System.out.println(dia);
			    int dia_comprobar=23;
			    
			    try {
		            String ruta = "src/main/resources/Doc.txt";
		            String contenido = "Contenido de ejemplo";
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
		    			bw.write(vector[0]+"debe pagar"+vector[1]+"\n");
		    		}
		          
				    
				    bw.close();
				    
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
			    
			    
			  
			    
			    
			    if(dia == dia_comprobar) {
			    	int j=modeloReservas.nuevaCuota1().size();
			    	
			    	List<Object[]> lista=modeloReservas.nuevaCuota1();
					String[] nombre=new String[lista.size()];
					Iterator<Object[]> iterador = lista.iterator();
					
					int i=0;
					while(iterador.hasNext()) {
						nombre[i]=iterador.next()[0].toString();
						i++;
					}
			    }
			    
				
				
				
			}
		});
		buttonGenerar.setBounds(149, 281, 170, 35);
		panel.add(buttonGenerar);

		JButton nuevoSocioBtn = new JButton("Nuevo cliente");
		nuevoSocioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vRegistrarCliente = new RegistrarCliente(vLogin);
				vRegistrarCliente.getFrmRegistrarCliente().setVisible(true);
			}
		});
		nuevoSocioBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nuevoSocioBtn.setBounds(10, 292, 207, 23);
		panel.add(nuevoSocioBtn);

	}
}
