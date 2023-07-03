package domain.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.portsin.EstacionServicioEsHoy;
import domain.portsin.ExcepcionesPortsIn;
import domain.portsin.NaftaRecord;
import domain.portsin.VentasRecord;
import domain.portsout.CargarVentas;
import domain.portsout.ExcepcionesPortsOut;
import domain.portsout.RegistrarCompra;
import domain.portsout.VentasRecordPortOut;

public class ImplementacionEstacionEsHoy implements EstacionServicioEsHoy {
	private List<Nafta> naftas;
	private RegistrarCompra registrarCompra;
	private CargarVentas cargarVentas;

	public ImplementacionEstacionEsHoy(RegistrarCompra registrarCompra, CargarVentas cargarVentas) {
		super();
		this.naftas = new ArrayList<Nafta>();
		this.CargarNaftas();
		this.registrarCompra = registrarCompra;
		this.cargarVentas = cargarVentas;
	}

	private void CargarNaftas() {
		Nafta naftasuper = new ExtraSuper();
		Nafta naftacomun = new Super();
		naftas.add(naftasuper);
		naftas.add(naftacomun);

	}

	// CONSULTAR COMO PONER EXCEPCION SI ENTRA VACIO
	private void validarLitros(String cantidadLitros) throws ExcepcionesPortsIn {
		try {
			if (Integer.parseInt(cantidadLitros) < 0)
				throw new ExcepcionesPortsIn("la cantidad de litros debe ser mayor a 0");
		} catch (NumberFormatException e) {
			throw new ExcepcionesPortsIn("el caracter ingresado debe ser un numero");
		}
	}

	@Override
	public double calcularPrecio(String tipoNafta, String cantidadLitros, LocalDateTime unaFecha)
			throws ExcepcionesPortsIn {
		Objects.requireNonNull(cantidadLitros);
		this.validarLitros(cantidadLitros);
		double total = 0;
		for (Nafta nafta : naftas) {
			total = buscarYcalcularPrecio(tipoNafta, cantidadLitros, total, nafta, unaFecha);
		}
		return total;
	}

	private double buscarYcalcularPrecio(String tipoNafta, String cantidadLitros, double total, Nafta nafta,
			LocalDateTime unaFecha) {
		if (nafta.esDeEsteTipo(tipoNafta)) {
			total = nafta.calcularPrecio(Integer.parseInt(cantidadLitros), unaFecha);
		}
		return total;
	}

	@Override
	public void confirmarCompra(String tipoNafta, String cantidadLitros, LocalDateTime unaFecha)
			throws ExcepcionesPortsIn {
		double total = this.calcularPrecio(tipoNafta, cantidadLitros, unaFecha);
		try {
			registrarCompra.registro(unaFecha, Integer.parseInt(cantidadLitros), total);
		} catch (ExcepcionesPortsOut e) {
			throw new ExcepcionesPortsIn(e.getMessage());
		}
	}

	@Override
	public List<NaftaRecord> tiposDeNafta() {
		List<NaftaRecord> tipos = new ArrayList<NaftaRecord>();
		for (Nafta nafta : naftas) {
			tipos.add(new NaftaRecord(nafta.tipo()));
		}
		return tipos;
	}

	@Override
	public List<VentasRecord> ventas() throws ExcepcionesPortsIn {

		List<Ventas> ventas = cargarVentas();
		List<VentasRecord> lista = new ArrayList<VentasRecord>();
		try {
			for (Ventas unaVenta : ventas) {
				VentasRecord venta = new VentasRecord(unaVenta.fecha(), unaVenta.CantidadLitros(), unaVenta.total(),
						unaVenta.esImportante());
				lista.add(venta);

			}

		} catch (Exception e) {
			throw new ExcepcionesPortsIn(e.getMessage());
		}

		return lista;
	}

	private List<Ventas> cargarVentas() {
		List<VentasRecordPortOut> ventasRecord = cargarVentas.cargarVentas();
		List<Ventas> ventas = new ArrayList<Ventas>();
		for (VentasRecordPortOut unaVenta : ventasRecord) {
			String fechaVenta = unaVenta.fecha();
			String nombre = unaVenta.litros();
			String duracion = unaVenta.total();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime fecha = LocalDateTime.parse(fechaVenta, formatter);

			Ventas venta = new Ventas(fecha, Integer.parseInt(nombre), Double.parseDouble(duracion));
			ventas.add(venta);

		}
		return ventas;
	}

}
