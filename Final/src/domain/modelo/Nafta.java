package domain.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Nafta {
	protected String nombre;

	public Nafta(String nombre) {
		super();
		Objects.requireNonNull(nombre);
		this.nombre = nombre;
	}

	public abstract double calcularPrecio(int cantidadLitros, LocalDateTime fecha);

	public abstract String tipo();

	public boolean esDeEsteTipo(String tipo) {

		if (tipo == this.nombre) {
			return true;
		}
		return false;

	}

}
