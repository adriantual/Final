package implementaciontest;

import java.time.LocalDateTime;

import domain.portsout.ExcepcionesPortsOut;
import domain.portsout.RegistrarCompra;

public class FakeEnBaseDeDatosRegistrar implements RegistrarCompra {

	private Integer litrosFake;
	private Double montoFake;

	@Override
	public void registro(LocalDateTime fecha, int litros, double monto) throws ExcepcionesPortsOut {
		this.litrosFake = litros;
		this.montoFake = monto;
	}

	public boolean seRegistroCorrectamente(int litros, double total) {

		if (this.litrosFake.equals(litros) && this.montoFake.equals(total)) {
			return true;
		}
		return false;
	}

}
