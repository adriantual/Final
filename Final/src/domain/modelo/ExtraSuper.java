package domain.modelo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ExtraSuper extends Nafta {
	private static final double PRECIO = 390;
	public static final double DESCUENTO = 0.9;

	public ExtraSuper() {
		super("Extra Super");

	}

	@Override
	public double calcularPrecio(int cantidadLitros, LocalDateTime fecha) {

		double total = 0;

		return total = ExtraSuper.PRECIO * cantidadLitros * esDomingo(fecha);

	}

	private double esDomingo(LocalDateTime fecha) {
		if (fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return ExtraSuper.DESCUENTO;

		}
		return 1;
	}

}
