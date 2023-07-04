package domain.modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Super extends Nafta {
	private static final double PRECIO = 270;
	public static final double DESCUENTO_HORARIO = 0.95;
	public static final double DESCUENTO_SABADOS = 0.88;

	public Super() {
		super("Super");

	}

	@Override
	public double calcularPrecio(int cantidadLitros, LocalDateTime fecha) {

		// String fecha = "2023-06-24T09:30:15";
		// LocalDateTime hoy = LocalDateTime.parse(fecha);
		double total = 0;
		// int cantidadLitros = 100;

		total = Super.PRECIO * cantidadLitros * esSabado(fecha, cantidadLitros) * horarioDescuento(fecha);

		// System.out.println(" " + total);
		return total;
	}

	public Double horarioDescuento(LocalDateTime fecha) {
		LocalTime inicioDescuento = LocalTime.of(8, 0);
		LocalTime finDescuento = LocalTime.of(10, 0);

		LocalTime horaFecha = fecha.toLocalTime();

		if (horaFecha.isAfter(inicioDescuento) && horaFecha.isBefore(finDescuento)) {
			return DESCUENTO_HORARIO;
		}
		return 1.0;
	}

	public double esSabado(LocalDateTime fecha, int cantidadLitros) {
		double total = 0;
		if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY && cantidadLitros > 20) {
			return total = DESCUENTO_SABADOS;
		}
		return 1;
	}

}
