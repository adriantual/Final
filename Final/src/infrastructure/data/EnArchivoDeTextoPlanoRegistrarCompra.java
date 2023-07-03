package infrastructure.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import domain.portsout.ExcepcionesPortsOut;
import domain.portsout.RegistrarCompra;

public class EnArchivoDeTextoPlanoRegistrarCompra implements RegistrarCompra {
	private File direccion;
	private FileWriter escribir;
	private PrintWriter lineaEscritura;
	private static final String SEPARADOR = " | ";

	public EnArchivoDeTextoPlanoRegistrarCompra(String direccion) {
		super();
		this.direccion = new File(direccion);
	}

	@Override
	public void registro(LocalDateTime fecha, int litros, double monto) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaTexto = fecha.format(formatter);

		String registroDeCompra = fechaTexto.substring(0, 19) + SEPARADOR + Integer.toString(litros) + SEPARADOR
				+ Double.toString(monto);

		try {
			if (this.direccion.exists())
				this.direccion.createNewFile();

			this.escribir = new FileWriter(direccion, true);
			this.lineaEscritura = new PrintWriter(this.escribir);
			this.lineaEscritura.println(registroDeCompra);

		} catch (IOException e) {
			throw new ExcepcionesPortsOut("No se pudo registrar el participante en el archivo.", e);
		} finally {
			try {
				this.escribir.close();
				this.lineaEscritura.close();
			} catch (IOException e) {
				throw new ExcepcionesPortsOut("Error al cerrar el archivo de registro.", e);
			}

		}

	}

}
