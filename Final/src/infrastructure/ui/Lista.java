package infrastructure.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.portsin.EstacionServicioEsHoy;
import domain.portsin.VentasRecord;

public class Lista extends JFrame {

	private JTable ventasTable;
	private EstacionServicioEsHoy miEstacion;

	public Lista(EstacionServicioEsHoy miEstacion) {

		this.miEstacion = miEstacion;

		setTitle("Lista de Ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Fecha Venta");
		model.addColumn("Total Litros Cargados");
		model.addColumn("Monto Facturado");
		model.addColumn("Es Importante");
		List<VentasRecord> lista = miEstacion.ventas();

		for (VentasRecord venta : lista) {
			Object[] rowData = { venta.fecha(), venta.litros(), venta.total(), venta.importancia() };
			model.addRow(rowData);
		}

		ventasTable = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(ventasTable);
		add(scrollPane, BorderLayout.CENTER);
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes\\images.png"));
		this.setTitle("LISTADO DE VENTAS");

	}
}
