package domain.portsout;

import java.time.LocalDateTime;

public interface RegistrarCompra {
	public void registro(LocalDateTime fecha, int litros, double monto) throws ExcepcionesPortsOut;
}
