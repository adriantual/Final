package domain.portsin;

import java.time.LocalDateTime;
import java.util.List;

public interface EstacionServicioEsHoy {
	public double calcularPrecio(String tipoNafta, String cantidadLitros, LocalDateTime unaFecha)
			throws ExcepcionesPortsIn;

	public void confirmarCompra(String tipoNafta, String cantidadLitros, LocalDateTime unaFecha)
			throws ExcepcionesPortsIn;

	public List<NaftaRecord> tiposDeNafta();

	public List<VentasRecord> ventas() throws ExcepcionesPortsIn;

}
