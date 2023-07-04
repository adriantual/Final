package infrastructure.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.portsin.EstacionServicioEsHoy;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;

	private EstacionServicioEsHoy miEstacion;

	public PantallaPrincipal(EstacionServicioEsHoy miEstacion) {
		this.miEstacion = miEstacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\images.png"));
		this.setTitle("Ventana Principal");
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		this.setResizable(false);
		JButton cargarCombustible = new JButton("CARGAR COMBUSTIBLE");
		cargarCombustible.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cargarCombustible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CargaDeCombustible cargar = new CargaDeCombustible(miEstacion);
				cargar.setVisible(true);

			}
		});
		cargarCombustible.setBounds(256, 157, 168, 52);
		contentPane.add(cargarCombustible);

		JButton cosultarVentas = new JButton("CONSULTAR VENTAS");
		cosultarVentas.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cosultarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lista unaLista = new Lista(miEstacion);

				unaLista.setVisible(true);

			}
		});
		cosultarVentas.setBounds(20, 160, 168, 47);

		contentPane.add(cosultarVentas);

		JLabel lblNewLabel = new JLabel("BIENVENIDO AL SISTEMA DE ESTACION \"ES HOY\"");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel.setBounds(80, 25, 344, 35);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("¿QUE DESEA HACER?");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(159, 99, 131, 47);
		contentPane.add(lblNewLabel_1);

	}
}
