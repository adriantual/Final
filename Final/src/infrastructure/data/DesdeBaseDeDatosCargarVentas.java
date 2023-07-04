package infrastructure.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.portsout.CargarVentas;
import domain.portsout.ExcepcionesPortsOut;
import domain.portsout.VentasRecordPortOut;

public class DesdeBaseDeDatosCargarVentas implements CargarVentas {

	private Conn connStr;

	public DesdeBaseDeDatosCargarVentas(String connStr) {

		this.connStr = new Conn(connStr);
	}

	@Override
	public List<VentasRecordPortOut> cargarVentas() {
		String consultaRegistro = "select * from registro_compra";
		List<VentasRecordPortOut> unaLista = new ArrayList<VentasRecordPortOut>();
		try (Connection dbconn = this.connStr.open(); Statement statement = dbconn.createStatement()) {

			ResultSet rs = statement.executeQuery(consultaRegistro);
			while (rs.next()) {

				VentasRecordPortOut unaVenta = new VentasRecordPortOut(rs.getString("fecha"),
						rs.getString("cantidadLitros"), rs.getString("monto"));

				unaLista.add(unaVenta);
			}
			return unaLista;

		} catch (

		SQLException e) {

			throw new ExcepcionesPortsOut("hubo un error en la consulta a la base de datos" + e.getMessage(), e);

		}

	}

}
