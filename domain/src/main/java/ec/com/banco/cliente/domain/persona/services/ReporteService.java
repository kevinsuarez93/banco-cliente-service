package ec.com.banco.cliente.domain.persona.services;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Reporte;

import java.util.List;

public interface ReporteService {


    List<Reporte> generarReporte(Long clienteId) throws EntidadNoEncontradaException;
}
