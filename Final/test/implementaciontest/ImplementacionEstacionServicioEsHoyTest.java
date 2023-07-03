package implementaciontest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.modelo.ImplementacionEstacionEsHoy;
import domain.portsin.NaftaRecord;
import domain.portsin.VentasRecord;

class ImplementacionEstacionServicioEsHoyTest {

	public static final String FECHA_LUNES = "2023-06-12T10:30:15";
	private static final LocalDateTime LUNES = LocalDateTime.parse(FECHA_LUNES);

	ImplementacionEstacionEsHoy miEstacion = new ImplementacionEstacionEsHoy(new FakeEnBaseDeDatosRegistrar(),
			new FakeDesdeArchivoCargarDatos());

	// TEST: PRECIOS EXTRA SUPER

	@Test
	void calcularPrecioExtraSuperSinDescuento() {

		assertEquals(3900.00, miEstacion.calcularPrecio("Extra Super", "10", LUNES));

	}

	@Test
	void calcularPrecioExtraSuperConDescuento() {

		String fechaDomingo = "2023-06-11T10:30:15";
		LocalDateTime domingo = LocalDateTime.parse(fechaDomingo);

		assertEquals(3510.00, miEstacion.calcularPrecio("Extra Super", "10", domingo));

	}

////	 TEST: PREPCIOS SUPER

	@Test
	void calcularPrecioSuperSinDescuento() {

		assertEquals(2700.00, miEstacion.calcularPrecio("Super", "10", LUNES));

	}

	@Test
	void calcularPrecioSuperConDescuentoPorHoraYPorDiaSabadoMas20Litros() {

		String fechaSabadoALaManiana = "2023-06-24T09:00:00";
		LocalDateTime sabadoALaManiana = LocalDateTime.parse(fechaSabadoALaManiana);

		assertEquals(11286.00, miEstacion.calcularPrecio("Super", "50", sabadoALaManiana));

	}

	@Test
	void calcularPrecioSuperConDescuentoPorHora() {

		String fechaLunesALaManiana = "2023-06-12T09:00:00";
		LocalDateTime lunesALaManiana = LocalDateTime.parse(fechaLunesALaManiana);

		assertEquals(2565.00, miEstacion.calcularPrecio("Super", "10", lunesALaManiana));

	}

	@Test
	void calcularPrecioSuperConDescuentoPorDiaSabado() {

		String fechaSabado = "2023-06-10T15:00:00";
		LocalDateTime sabado = LocalDateTime.parse(fechaSabado);

		assertEquals(11880.00, miEstacion.calcularPrecio("Super", "50", sabado));

	}

	@Test
	void calcularPrecioSuperSinDescuentoPorDiaSabadoYMenos20Litros() {

		String fechaSabado = "2023-06-10T15:00:00";
		LocalDateTime sabado = LocalDateTime.parse(fechaSabado);

		assertEquals(5130.00, miEstacion.calcularPrecio("Super", "19", sabado));

	}

	// TEST: INTERFAZ REGISTRO EN BASE DE DATOS O ARCHIVO DE TEXTO PLANO.

	@Test
	void interfazRegistrar() {

		FakeEnBaseDeDatosRegistrar EnBaseDeDatosFake = new FakeEnBaseDeDatosRegistrar();

		ImplementacionEstacionEsHoy miEstacionHoy = new ImplementacionEstacionEsHoy(EnBaseDeDatosFake,
				new FakeDesdeArchivoCargarDatos());

		miEstacionHoy.confirmarCompra("Extra Super", "10", LUNES);
		assertTrue(EnBaseDeDatosFake.seRegistroCorrectamente(10, 3900.00));

	}

	// TEST: INTERFAZ CARGAR DATOS DESDE BASE DE DATOS O ARCHIVO DE TEXTO PLANO.

	@Test
	void interfazCargarDatos() {

		// SE INICIALIZA UNA INSTANCIA DE LA INTERFAZ CUYO METODO SE COMPONE
		// DE OTRO METODO LLAMADO "LLENAR LISTA" QUE CREA 5 OBJETOS DE VENTASRECORD
		// Y LOS AGREGA A UNA LISTA QUE RETORNA PARA UTILIZAR EN EL MODELO.
		// SE HACE UN ASSERTEQUALS PARA SABER SI SE CARGARON LAS 5 VENTAS
		// HARCODEADAS MANUALMENTE EN LA INTERFAZ FAKE
		FakeDesdeArchivoCargarDatos cargarDatos = new FakeDesdeArchivoCargarDatos();

		ImplementacionEstacionEsHoy miEstacionHoy = new ImplementacionEstacionEsHoy(new FakeEnBaseDeDatosRegistrar(),
				cargarDatos);

		List<VentasRecord> lista = new ArrayList<VentasRecord>();
		lista = miEstacionHoy.ventas();

		assertEquals(5, lista.size());

	}

	// TEST: CANTIDAD Y TIPOS DE NAFTA

	@Test
	void tiposYCantidadDeNafta() {

		NaftaRecord superr = new NaftaRecord("super");
		NaftaRecord extraSuper = new NaftaRecord("Extra super");
		assertEquals(2, miEstacion.tiposDeNafta().size());
		assertTrue(miEstacion.tiposDeNafta().contains(superr));
		assertTrue(miEstacion.tiposDeNafta().contains(extraSuper));

	}

}
