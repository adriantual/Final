package domain.portsout;

public class ExcepcionesPortsOut extends RuntimeException {

	public ExcepcionesPortsOut(String msg, Exception e) {
		super(msg, e);
	}
}
