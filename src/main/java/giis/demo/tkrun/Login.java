package giis.demo.tkrun;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import giis.demo.util.SwingMain;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmLogin;
	private JTextField txtId_usuario;
	private JPasswordField txtContraseña;
	private ClientesModel modelClientes = new ClientesModel();
	private int id_socio;
	private inicialAdmin vInicialAdmin;
	private inicialSocio vInicialSocio;
	private SwingMain vSwing;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 302, 224);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		vInicialAdmin= new inicialAdmin(this);
		vInicialSocio= new inicialSocio(this);
		vSwing = new SwingMain(this);
		
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
		txtContraseña.setBounds(168, 83, 86, 20);
		frmLogin.getContentPane().add(txtContraseña);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass;
				if(!txtId_usuario.getText().equals("") && !Arrays.equals(txtContraseña.getPassword(), "".toCharArray())) {
					//entrar como admin
					if(txtId_usuario.getText().equals("admin") && Arrays.equals(txtContraseña.getPassword(), "admin".toCharArray())){
						vSwing.getFrmIndex().setVisible(true);
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
}
