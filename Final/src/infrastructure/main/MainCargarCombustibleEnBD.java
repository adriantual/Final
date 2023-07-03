package infrastructure.main;

import java.awt.EventQueue;

import domain.modelo.ImplementacionEstacionEsHoy;
import infrastructure.data.DesdeArchivoCargarVentas;
import infrastructure.data.EnBaseDeDatosRegistrarCompra;
import infrastructure.ui.CargaDeCombustible;

public class MainCargarCombustibleEnBD {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CargaDeCombustible frame = new CargaDeCombustible(new ImplementacionEstacionEsHoy(
							new EnBaseDeDatosRegistrarCompra("jdbc:mysql://localhost:3306/final_objetos"),
							new DesdeArchivoCargarVentas("jdbc:mysql://localhost:3306/final_objetos")));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
