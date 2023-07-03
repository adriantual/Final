package infrastructure.main;

import java.awt.EventQueue;

import domain.modelo.ImplementacionEstacionEsHoy;
import infrastructure.data.DesdeBaseDeDatosCargarVentas;
import infrastructure.data.EnBaseDeDatosRegistrarCompra;
import infrastructure.ui.Lista;

public class MainListarDesdeBD {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lista frame = new Lista(new ImplementacionEstacionEsHoy(
							new EnBaseDeDatosRegistrarCompra("jdbc:mysql://localhost:3306/final_objetos"),
							new DesdeBaseDeDatosCargarVentas("jdbc:mysql://localhost:3306/final_objetos")));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
