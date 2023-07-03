package infrastructure.main;

import java.awt.EventQueue;

import domain.modelo.ImplementacionEstacionEsHoy;
import infrastructure.data.DesdeArchivoCargarVentas;
import infrastructure.data.EnArchivoDeTextoPlanoRegistrarCompra;
import infrastructure.ui.Lista;

public class MainListarDesdeArchivo {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lista frame = new Lista(
							new ImplementacionEstacionEsHoy(new EnArchivoDeTextoPlanoRegistrarCompra("registro"),
									new DesdeArchivoCargarVentas("registro")));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
