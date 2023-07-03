package infrastructure.ui;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cantidad de litros que desea cargar");
		lblNewLabel.setBounds(46, 150, 182, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Tipo de nafta");
		lblNewLabel_1.setBounds(46, 80, 94, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox<String> tipoDeNafta = new JComboBox<String>();
		tipoDeNafta.setBounds(268, 64, 118, 22);
		contentPane.add(tipoDeNafta);
		cargarComboBox(tipoDeNafta);

		cantidadDeLitros = new JTextField();
		cantidadDeLitros.setBounds(284, 147, 86, 20);
		contentPane.add(cantidadDeLitros);
		cantidadDeLitros.setColumns(10);

		JButton consultarPrecio = new JButton("Consultar Precio");
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
		consultarPrecio.setBounds(55, 203, 127, 47);
		contentPane.add(consultarPrecio);

		JButton confirmarCompra = new JButton("Confirmar compra");
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
		confirmarCompra.setBounds(268, 203, 127, 47);
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
