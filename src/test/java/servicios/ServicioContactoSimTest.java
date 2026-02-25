package servicios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import modelo.DatosSolicitud;

class ServicioContactoSimTest {

    @Test
    void solicitarSimulation_devuelveTokenYAlmacena() {
    	
    	//Para este test he buscado y el LoggerFactory no es mala clase para este tipo de tests
        Logger logger = LoggerFactory.getLogger(ServicioContactoSim.class);
        ServicioContactoSim servicio = new ServicioContactoSim(logger);

        //Creoun Map válido porque el constructor de DatosSolicitud me lo pide
        Map<Integer, Integer> datos = new HashMap<>();
        datos.put(1, 10);
        datos.put(2, 20);

        DatosSolicitud ds = new DatosSolicitud(datos);
        
        //Obtengo el token, que lo había generado con el generadorToken en ServicioContactoSim
        int token = servicio.solicitarSimulation(ds);

        assertTrue(token > 0, "El token debe ser mayor que 0");
        assertEquals(1, servicio.countSolicitudes(), "Se debe haber almacenado una solicitud");
    }

    @Test
    void getEntities_devuelveTresEntidades() {

        Logger logger = LoggerFactory.getLogger(ServicioContactoSim.class);
        ServicioContactoSim servicio = new ServicioContactoSim(logger);
        
        //Compruebo que el getEntities no me lo da nulo, ya que se supone que el que me he inventado tiene 3 entidades de momento
        assertNotNull(servicio.getEntities());
        assertEquals(3, servicio.getEntities().size(), "Deben haber 3 entidades");

        //Compruebo que la entidad de id=1 existe
        boolean existe = servicio.getEntities().stream().anyMatch(e -> e.getId() == 1);
        assertTrue(existe, "Debe existir la entidad con id 1");
    }

    @Test
    void isValidEntityId_devuelveTrue_siHayEntidades() {

        Logger logger = LoggerFactory.getLogger(ServicioContactoSim.class);
        ServicioContactoSim servicio = new ServicioContactoSim(logger);
        
        //Este método es el que me chirriaba, no sé de momento lo dejo así
        assertTrue(servicio.isValidEntityId(), "Debería devolver true porque la lista no está vacía");
    }
}