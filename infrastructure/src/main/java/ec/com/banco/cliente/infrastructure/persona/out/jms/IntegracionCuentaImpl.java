package ec.com.banco.cliente.infrastructure.persona.out.jms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.banco.cliente.domain.common.exception.EntidadNoEncontradaException;
import ec.com.banco.cliente.domain.persona.jms.IntegracionCuenta;
import ec.com.banco.cliente.infrastructure.common.exceptions.RemoteExecutionException;
import ec.com.banco.cliente.infrastructure.common.jms.JmsClient;
import ec.com.banco.cuenta.share.cuenta.dto.CuentaDto;
import ec.com.banco.cuenta.share.cuenta.dto.FiltroDto;
import ec.com.banco.cuenta.share.ejemplo.enums.Operacion;
import jakarta.jms.JMSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@EnableConfigurationProperties(JmsCuentaProperties.class)
public class IntegracionCuentaImpl  implements IntegracionCuenta {



    private JmsClient jmsClient;
    private JmsCuentaProperties jmsCuentaProperties;

    public IntegracionCuentaImpl(JmsClient jmsClient, JmsCuentaProperties jmsCuentaProperties) {
        this.jmsClient = jmsClient;
        this.jmsCuentaProperties = jmsCuentaProperties;

    }



    @Override
    public List<CuentaDto> obtenerCuentas(Long clienteId, Date fechaInicio, Date fechaFinal) throws EntidadNoEncontradaException {
        try {
            FiltroDto filtroDto = new FiltroDto();
            filtroDto.setFechaInicio(fechaInicio);
            filtroDto.setFechaFinal(fechaFinal);
            filtroDto.setClienteId(clienteId);

            // 📌 Recibir la respuesta como `String`
            String jsonResponse = jmsClient.sendAndWaitForResponse(
                    filtroDto,
                    String.class,  // 📌 Recibimos JSON como String
                    jmsCuentaProperties.getRequestqueue(),
                    jmsCuentaProperties.getReplyqueue(),
                    Optional.empty(),
                    Operacion.GET_CUENTA_POR_ID.name()
            );

            // 📌 Convertir la respuesta JSON a `List<CuentaDto>`
            ObjectMapper objectMapper = new ObjectMapper();
            List<CuentaDto> cuentas = objectMapper.readValue(jsonResponse, new TypeReference<List<CuentaDto>>() {});

            // 📌 Validar si la lista está vacía
            if (cuentas == null || cuentas.isEmpty()) {
                throw new EntidadNoEncontradaException("No se encontraron cuentas para el cliente.");
            }

            return cuentas;
        } catch (JsonProcessingException | RemoteExecutionException | JMSException e) {
            throw new EntidadNoEncontradaException("Error al obtener cuentas: " + e.getMessage());
        }
    }
}
