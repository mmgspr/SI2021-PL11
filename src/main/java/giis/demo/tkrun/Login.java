package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import giis.demo.util.SwingMain;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Login {

	private JFrame frmLogin;
	private JTextField txtId_usuario;
	private JPasswordField txtContraseña;
	private ClientesModel modelClientes = new ClientesModel();
	private int id_socio;
	private inicialAdmin vInicialAdmin;
	private inicialSocio vInicialSocio;
	private SwingMain vSwing;
	

	
	//Variables parametrizacion
	private int diasAntelacion;
	private int dia_comprobar;
	private int Hora_Max;
	private int horasDiaMax;
  	private int horasPeriodoMax;

  	public static final String delimiter = ";";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	public Login(SwingMain vSwing) {
		this.vSwing=vSwing;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cargaParametros("src/main/resources/Parametros.csv");
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 302, 224);
		frmLogin.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		vInicialAdmin= new inicialAdmin(this);
		vInicialSocio= new inicialSocio(this);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(51, 43, 69, 14);
		frmLogin.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(51, 86, 69, 14);
		frmLogin.getContentPane().add(lblContrasea);
		
		txtId_usuario = new JTextField();
		txtId_usuario.setBounds(168, 40, 86, 20);
		frmLogin.getContentPane().add(txtId_usuario);
		txtId_usuario.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar()==KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		txtContraseña.setBounds(168, 83, 86, 20);
		frmLogin.getContentPane().add(txtContraseña);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnEntrar.setBounds(102, 151, 89, 23);
		frmLogin.getContentPane().add(btnEntrar);
		
	}

	public inicialAdmin getvInicialAdmin() {
		return vInicialAdmin;
	}

	public void setvInicialAdmin(inicialAdmin vInicialAdmin) {
		this.vInicialAdmin = vInicialAdmin;
	}

	public ClientesModel getModelClientes() {
		return modelClientes;
	}

	public int getId_socio() {
		return id_socio;
	}


	public void setId_socio(int id_socio) {
		this.id_socio = id_socio;
	}

	public Window getFrmIndex() {
		// TODO Auto-generated method stub
		return frmLogin;
	}
	public void login() {
		String pass;
		if(!txtId_usuario.getText().equals("") && !Arrays.equals(txtContraseña.getPassword(), "".toCharArray())) {
			//entrar como admin
			if(txtId_usuario.getText().equals("admin") && Arrays.equals(txtContraseña.getPassword(), "admin".toCharArray())){
				vInicialAdmin.getFrmIndex().setVisible(true);
				frmLogin.setVisible(false);
			}
			else { //entrar como socio
				try {
					setId_socio(Integer.parseInt(txtId_usuario.getText()));
					List<Object[]> lista = modelClientes.getContraseña(Integer.toString(id_socio));
					pass=lista.get(0)[0].toString();
					if(Arrays.equals(txtContraseña.getPassword(), pass.toCharArray())) {
						vInicialSocio.generaLbl();
						vInicialSocio.getFrmIndex().setVisible(true);
						frmLogin.setVisible(false);
						
					}
					else {
						JOptionPane.showMessageDialog(frmLogin,
							    "Las credenciales introducidas son erroneas.",
							    "Error de credenciales",
							    JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frmLogin,
						    "El usuario ha de ser su número de socio.",
						    "Error de credenciales",
						    JOptionPane.ERROR_MESSAGE);
				}
				}
			}
		else {
			JOptionPane.showMessageDialog(frmLogin,
				    "Las credenciales introducidas son erroneas.",
				    "Error de credenciales",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	

	
	
	
	public int getDiasAntelacion() {
		return diasAntelacion;
	}

	public void setDiasAntelacion(int diasAntelacion) {
		this.diasAntelacion = diasAntelacion;
	}

	public int getDia_comprobar() {
		return dia_comprobar;
	}

	public void setDia_comprobar(int dia_comprobar) {
		this.dia_comprobar = dia_comprobar;
	}

	public int getHora_Max() {
		return Hora_Max;
	}

	public void setHora_Max(int hora_Max) {
		Hora_Max = hora_Max;
	}

	public int getHorasDiaMax() {
		return horasDiaMax;
	}

	public void setHorasDiaMax(int horasDiaMax) {
		this.horasDiaMax = horasDiaMax;
	}

	public int getHorasPeriodoMax() {
		return horasPeriodoMax;
	}

	public void setHorasPeriodoMax(int horasPeriodoMax) {
		this.horasPeriodoMax = horasPeriodoMax;
	}
	
	public void cargaParametros(String ruta) {
		try{
			File file = new File(ruta);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		    String line = " ";
		    String[] tempArr;
		    int i=0;
		    while ((line = br.readLine()) != null) {
		        tempArr = line.split(delimiter);
		        for (String tempStr: tempArr) {
		        	switch(i) {
		        	
			        case 0: dia_comprobar=Integer.parseInt(tempStr); break;
					case 1: diasAntelacion=Integer.parseInt(tempStr); break;
					case 2: Hora_Max=Integer.parseInt(tempStr); break;
					case 3: horasDiaMax=Integer.parseInt(tempStr); break;
					case 4: horasPeriodoMax=Integer.parseInt(tempStr); break;
		        	}
		        	i++;
		          
		        }
		        System.out.println();
		      }
		      br.close();
		    }
		    catch(IOException ioe) {
		      ioe.printStackTrace();
		    }
				
	}
}
