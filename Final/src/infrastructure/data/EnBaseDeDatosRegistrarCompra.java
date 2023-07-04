package infrastructure.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import domain.portsout.ExcepcionesPortsOut;
import domain.portsout.RegistrarCompra;

public class EnBaseDeDatosRegistrarCompra implements RegistrarCompra {

	private Conn connStr;

	public EnBaseDeDatosRegistrarCompra(String connStr) {

		this.connStr = new Conn(connStr);
	}

	@Override
	public void registro(LocalDateTime fecha, int litros, double monto) throws ExcepcionesPortsOut {

		String consultaRegistro = "insert into registro_compra(fecha, cantidadLitros, monto) values(?,?,?)";
		try (Connection dbconn = this.connStr.open();
				PreparedStatement statement = dbconn.prepareStatement(consultaRegistro)) {

			Timestamp timestamp = Timestamp.valueOf(fecha);
			statement.setTimestamp(1, timestamp);
			statement.setLong(2, litros);
			statement.setLong(3, (long) monto);
			statement.executeUpdate();
			statement.close();
		} catch (

		SQLException e) {

			throw new ExcepcionesPortsOut("hubo un error en la consulta a la base de datos" + e.getMessage(), e);

		}
	}
}