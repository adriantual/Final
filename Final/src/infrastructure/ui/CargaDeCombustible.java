package infrastructure.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.portsin.EstacionServicioEsHoy;
import domain.portsin.ExcepcionesPortsIn;
import domain.portsin.NaftaRecord;

public class CargaDeCombustible extends JFrame {

	private JPanel contentPane;
	private JTextField cantidadDeLitros;
	private EstacionServicioEsHoy miEstacion;

	public CargaDeCombustible(EstacionServicioEsHoy miEstacion) {
		this.miEstacion = miEstacion;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("¿CUANTOS LITROS DESEA CARGAR?");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel.setBounds(34, 114, 218, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("TIPO DE NAFTA");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(73, 40, 109, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox<String> tipoDeNafta = new JComboBox<String>();
		tipoDeNafta.setBounds(268, 36, 118, 22);
		contentPane.add(tipoDeNafta);
		cargarComboBox(tipoDeNafta);

		cantidadDeLitros = new JTextField();
		cantidadDeLitros.setBounds(268, 111, 118, 20);
		contentPane.add(cantidadDeLitros);
		cantidadDeLitros.setColumns(10);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\images.png"));
		this.setTitle("CARGAR NAFTA");

		JButton consultarPrecio = new JButton("CONSULTAR PRECIO");
		consultarPrecio.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		consultarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPrecio.setEnabled(false);
				try {
					String tipo = (String) tipoDeNafta.getSelectedItem();
					double precio = miEstacion.calcularPrecio(tipo, cantidadDeLitros.getText(), LocalDateTime.now());
					JOptionPane.showMessageDialog(null, precio);
				} catch (ExcepcionesPortsIn e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}

				consultarPrecio.setEnabled(true);
			}
		});
		consultarPrecio.setBounds(55, 203, 148, 47);
		contentPane.add(consultarPrecio);

		JButton confirmarCompra = new JButton("CONFIRMAR COMPRA");
		confirmarCompra.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		confirmarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarCompra.setEnabled(false);

				String tipo = (String) tipoDeNafta.getSelectedItem();
				try {
					miEstacion.confirmarCompra(tipo, cantidadDeLitros.getText(), LocalDateTime.now());
					JOptionPane.showMessageDialog(null, "La compra se ha realizado correctamente.");
				} catch (ExcepcionesPortsIn e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());

				}

				confirmarCompra.setEnabled(true);
			}
		});
		confirmarCompra.setBounds(247, 203, 148, 47);
		contentPane.add(confirmarCompra);
	}

	private void cargarComboBox(JComboBox<String> comboBox) {

		List<NaftaRecord> tipos = new ArrayList<NaftaRecord>();

		tipos = miEstacion.tiposDeNafta();

		for (NaftaRecord unTipo : tipos) {
			comboBox.addItem(unTipo.tipo());
		}
	}
}
