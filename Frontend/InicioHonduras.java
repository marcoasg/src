package Frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Backend.Usuario;

public class InicioHonduras extends JFrame {


	Usuario user;
	
	public InicioHonduras(Usuario u) {
		this.user = u;
		setForeground(Color.BLACK);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("ACOES Honduras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 272);
		
		JLabel label = new JLabel("");
		label.setBounds(109, 55, 315, 14);
		getContentPane().add(label);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setBounds(77, 92, 104, 23);
		getContentPane().add(btnUsuarios);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					Usuarios usuarios = new Usuarios(user);
					usuarios.setVisible(true);
					dispose();
			}
		});
		
		JButton btnCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btnCerrarSesin.setBounds(214, 0, 128, 23);
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnCerrarSesin);
		if (user.getRol().getNivel() >= 2) getContentPane().add(btnUsuarios);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Perfil p = new Perfil(user);
				p.setVisible(true);
				dispose();
			}
		});
		btnPerfil.setBounds(77, 58, 104, 23);
		getContentPane().add(btnPerfil);
		
		JLabel lblMenu = new JLabel("Men\u00FA");
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMenu.setBounds(103, 9, 46, 14);
		getContentPane().add(lblMenu);
		
		JButton btnP = new JButton("Proyectos");
		btnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						ProyectosGenerales pr = new ProyectosGenerales(user);
						pr.setVisible(true);
						dispose();


			}
		});
		btnP.setBounds(77, 126, 104, 23);
		getContentPane().add(btnP);
		
		JButton btnNios = new JButton("Ni\u00F1os");
		btnNios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ni�os n = new Ni�os(user);
				n.setVisible(true);
				dispose();
			}
		});
		btnNios.setBounds(77, 164, 104, 23);
		getContentPane().add(btnNios);
		
		JButton btnValidarGastos = new JButton("Validar Gastos");
		if(user.getRol().getRolName().equals("CoordinadorGeneralH")) {
			btnValidarGastos.setVisible(true);
		}else {
			btnValidarGastos.setVisible(false);
		}
		btnValidarGastos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ValidarGastos vg = new ValidarGastos(u);
				vg.setVisible(true);
				dispose();
				
			}
		});
		btnValidarGastos.setBounds(64, 198, 127, 23);
		getContentPane().add(btnValidarGastos);
		
	}
}
