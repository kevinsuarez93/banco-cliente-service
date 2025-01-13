package ec.com.banco.cliente.domain.persona.services;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.models.Reporte;

import java.util.Date;
import java.util.List;

public interface ReporteService {


    Reporte generarReporte(Long clienteId, Date fechaInicio, Date fechaFinal) throws EntidadNoEncontradaException;
}
