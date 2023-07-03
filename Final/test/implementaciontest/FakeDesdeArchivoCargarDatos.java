package implementaciontest;

import java.util.ArrayList;
import java.util.List;

import domain.portsout.CargarVentas;
import domain.portsout.VentasRecordPortOut;

public class FakeDesdeArchivoCargarDatos implements CargarVentas {

	private List<VentasRecordPortOut> lista;

	@Override
	public List<VentasRecordPortOut> cargarVentas() {

		this.llenarLista();

		return this.lista;
	}

	private void llenarLista() {
		this.lista = new ArrayList<VentasRecordPortOut>();
		int cantidadVentas = 5;
		String lunes = "2023-06-12T10:30:15";
		while (cantidadVentas > 0) {

			VentasRecordPortOut unaVenta = new VentasRecordPortOut(lunes, "10", "3900.00");

			this.lista.add(unaVenta);
			cantidadVentas -= 1;

		}
	}

}
