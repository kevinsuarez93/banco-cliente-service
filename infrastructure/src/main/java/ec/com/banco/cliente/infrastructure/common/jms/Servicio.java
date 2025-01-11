package ec.com.banco.cliente.infrastructure.common.jms;

import com.fasterxml.jackson.core.JsonProcessingException;

import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;

public interface Servicio {
    String execute(TextMessage textMessage) throws JsonProcessingException, JMSException, EntidadNoEncontradaException;
}