package Frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Backend.ProyectoGeneral;
import Backend.Usuario;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProyectosGenerales extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JList list;
	private JLabel label;
	private JTextArea textArea;
	private JScrollPane panel;
	private ProyectoGeneral seleccionado;
	private Usuario user;
	private JButton btnVolverAlMen;
	private JButton btnVerProyecto;
	
	private void actualizarVista(ProyectoGeneral p) {
		textField.setText(p.getCoordinador().getUsuario());
		label.setText(p.getNombre());
		textArea.setText(p.getDescripcion());
	}
	
	public ProyectosGenerales(Usuario u) {
		user = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		ProyectoGeneral[] proyectos = ProyectoGeneral.ListaProyectosGenerales();
		String[] pro = new String[proyectos.length];
		int i = 0;
		for(ProyectoGeneral p : proyectos) {
				pro[i] = p.getNombre();
				i++;

		}
		list = new JList(pro);
		panel = new JScrollPane(list);
		panel.setBounds(61, 51, 507, 124);
		list.setBounds(61, 51, 507, 124);
		contentPane.add(panel);
		
		JLabel lblDatos = new JLabel("Datos:");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDatos.setBounds(57, 192, 69, 20);
		contentPane.add(lblDatos);
		
		JLabel lblCoordinador = new JLabel("Coordinador:");
		lblCoordinador.setBounds(61, 228, 99, 20);
		contentPane.add(lblCoordinador);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(61, 286, 69, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n:");
		lblNewLabel_1.setBounds(61, 346, 99, 20);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(61, 255, 146, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("");
		label.setBounds(61, 310, 150, 26);
		contentPane.add(label);
		
		textArea = new JTextArea();
		textArea.setBounds(61, 382, 243, 92);
		contentPane.add(textArea);
		
		JButton btnActualizar = new JButton("Cambiar coordinador");
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.getRol().getNivel() == 3) {
						if (seleccionado != null) {
							Usuario u;
							try {
								u = new Usuario(textField.getText());
								if (u.getRol().getRolName().equals("CoordinadorProyectoH")) {
									seleccionado.setCoordinador(u);
								}else {
									JOptionPane.showMessageDialog(null, "Escoja un proyecto");
								}
							} catch (Backend.Error e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
						}
				}else {
					JOptionPane.showMessageDialog(null, "Su rol no tiene permiso para realizar esta acci�n");
				}
				
			}
		});
		btnActualizar.setBounds(437, 311, 195, 29);
		contentPane.add(btnActualizar);
		
		JButton btnNuevoProyecto = new JButton("Nuevo proyecto");
		btnNuevoProyecto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevoProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if (user.getRol().getNivel() == 3) {
						NuevoProyectoGeneralBien np = new NuevoProyectoGeneralBien(user);
						np.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "No tiene permiso para crear proyectos.");
					}
			}
		});
		btnNuevoProyecto.setBounds(437, 367, 195, 29);
		contentPane.add(btnNuevoProyecto);
		
		btnVolverAlMen = new JButton("Volver al men\u00FA");
		btnVolverAlMen.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolverAlMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					InicioHonduras inicio = new InicioHonduras(user);
					inicio.setVisible(true);
					dispose();
				
			}
		});
		btnVolverAlMen.setBounds(437, 440, 195, 23);
		contentPane.add(btnVolverAlMen);
		
		btnVerProyecto = new JButton("Ver proyecto");
		btnVerProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						if (seleccionado == null)
							throw new Backend.Error("Seleccione un proyecto.");
						ProyectosLocales p = new ProyectosLocales(seleccionado,user);
						p.setVisible(true);
						dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
			}
		});
		btnVerProyecto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVerProyecto.setBounds(437, 257, 195, 23);
		contentPane.add(btnVerProyecto);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				seleccionado = new ProyectoGeneral((String) list.getSelectedValue());
				actualizarVista(seleccionado);
			}
		});
	}
}
