package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import interfaces.InterfazContactoSim;
import modelo.DatosSimulation;
import modelo.DatosSolicitud;
import modelo.Entidad;

@Service
public class ServicioContactoSim implements InterfazContactoSim {

    private final Logger logger;

    //De momento solo tengo que almacenar provisionalmente los datos de la solicitudes
    private final ConcurrentHashMap<Integer, DatosSolicitud> solicitudes = new ConcurrentHashMap<>();

    //Esta es la lista de entidades que me tengo que inventar de momento
    private final List<Entidad> entidades = new ArrayList<>();

    //Para el generador simple de tokens he pillado esta clase
    private final AtomicInteger generadorToken = new AtomicInteger(1);

    public ServicioContactoSim(Logger logger) {
        this.logger = logger;
        inicializarEntidades(); //Método que de momento me inventa la lista de entidades
    }
    
    //Método que mencionaba para inventarme la lista
    private void inicializarEntidades() {
        Entidad e1 = new Entidad();
        e1.setId(1);
        e1.setName("Entidad A");
        e1.setDescripcion("Descripción A");

        Entidad e2 = new Entidad();
        e2.setId(2);
        e2.setName("Entidad B");
        e2.setDescripcion("Descripción B");

        Entidad e3 = new Entidad();
        e3.setId(3);
        e3.setName("Entidad C");
        e3.setDescripcion("Descripción C");

        entidades.add(e1);
        entidades.add(e2);
        entidades.add(e3);
    }
    
    //De momento simplemente genero un token aleatorio y lo almaceno y lo devuelvo
    @Override
    public int solicitarSimulation(DatosSolicitud sol) {
        int token = generadorToken.getAndIncrement(); //lo genero
        solicitudes.put(token, sol); //lo almaceno
        logger.info("Solicitud almacenada con el token {}", token); //actualizo la auditoría del logger
        return token;
    }
    
    //De momento no la implemento, solo actualizo el logger
    @Override
    public DatosSimulation descargarDatos(int ticket) {
        logger.info("Petición descargarDatos para ticket {}", ticket);
        return null;
    }
    
    //Implemento devolviendo una copia siempre, nunca la original
    @Override
    public List<Entidad> getEntities() {
        return new ArrayList<>(entidades);
    }

    //Para este método no lo tengo muy claro la verdad. Preguntar al profesor.
    @Override
    public boolean isValidEntityId() {
    	 return !entidades.isEmpty();
    }

    // Esto es para mis tests
    public int countSolicitudes() {
        return solicitudes.size();
    }
}