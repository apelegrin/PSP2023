package ut2_03_telnet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Telnet {

	private JFrame frame;
	private JTextField txtConexion;
	private JButton btnLogout;
	private JLabel lblRecibido;
	private JTextArea txtRecibido;
	private JLabel lblEnviar;
	private JTextField txtEnviar;
	private JButton btnEnviar;
	private JButton btnConectar;
	private JTextField txtStatus;
	
	private String serverIP = "127.0.0.1";
	private int serverPort = 8888;
	private Socket s;
	private InetAddress host;
	private PrintWriter flujoS;
	private boolean conectado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telnet window = new Telnet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Telnet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//btnConectar
		btnConectar = new JButton("CONECTAR");
		btnConectar.setBounds(280, 17, 117, 25);
		frame.getContentPane().add(btnConectar);
		
		//btnLogut
		btnLogout = new JButton("LOGOUT");
		btnLogout.setBounds(409, 17, 117, 25);
		frame.getContentPane().add(btnLogout);
		btnLogout.setEnabled(false);
		
		//btnEnviar
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(480, 279, 80, 25);
		btnEnviar.setEnabled(false);
		frame.getContentPane().add(btnEnviar);
		
		//lblURL
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(32, 22, 70, 15);
		frame.getContentPane().add(lblUrl);
		
		//lblRecibido
		lblRecibido = new JLabel("RECIBIDO");
		lblRecibido.setBounds(32, 73, 70, 15);
		frame.getContentPane().add(lblRecibido);
		
		//lblEnviar
		lblEnviar = new JLabel("ENVIAR");
		lblEnviar.setBounds(32, 284, 70, 15);
		frame.getContentPane().add(lblEnviar);
		
		//txtConexion
		txtConexion = new JTextField();
		txtConexion.setBounds(85, 20, 159, 19);
		frame.getContentPane().add(txtConexion);
		txtConexion.setColumns(20);
		txtConexion.setText(serverIP);

		//txtRecibido
		txtRecibido = new JTextArea();
		txtRecibido.setBounds(32, 100, 518, 150);
		txtRecibido.setBackground(new Color(230, 230, 250));
		txtRecibido.setWrapStyleWord(true);
		frame.getContentPane().add(txtRecibido);

		//txtEnviar
		txtEnviar = new JTextField();
		txtEnviar.setBounds(85, 282, 380, 19);
		frame.getContentPane().add(txtEnviar);
		txtEnviar.setColumns(10);
		txtEnviar.setEditable(false);

		//txtStatus
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setBounds(32, 339, 450, 19);
		frame.getContentPane().add(txtStatus);
		txtStatus.setColumns(10);
		txtStatus.setText("Desconectado");

		/* Acciones */
		
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!conectado) {
					try {
						host = InetAddress.getByName(txtConexion.getText());
						s = new Socket(host,serverPort);
						conectado = true;
						txtStatus.setText("conectado a "+txtConexion.getText()+":"+serverPort);
						txtConexion.setEditable(false);
						txtEnviar.grabFocus();
						btnEnviar.setEnabled(true);
						txtEnviar.setEditable(true);
						btnLogout.setEnabled(true);
						btnConectar.setEnabled(false);
						flujoS = new PrintWriter(s.getOutputStream());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						txtStatus.setText("Error de conexión");
					}
				}
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conectado = false;
					btnEnviar.setEnabled(false);
					txtEnviar.setEditable(false);
					txtEnviar.setText("");
					txtStatus.setText("Desconectado");
					btnConectar.setEnabled(true);
					btnLogout.setEnabled(false);
					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
