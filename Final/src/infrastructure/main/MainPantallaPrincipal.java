package infrastructure.main;

import java.awt.EventQueue;

import domain.modelo.ImplementacionEstacionEsHoy;
import infrastructure.data.DesdeArchivoCargarVentas;
import infrastructure.data.EnArchivoDeTextoPlanoRegistrarCompra;
import infrastructure.ui.PantallaPrincipal;

public class MainPantallaPrincipal {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal(
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
