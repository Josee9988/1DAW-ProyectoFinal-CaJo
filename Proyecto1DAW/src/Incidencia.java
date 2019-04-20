import Persona;
import Ubicacion;
import Incidencia;
import BandejaMensajes;

public class Incidencia

{
    /** Attributes */
    /**
     * 
     */
    private Persona Pers;
    /**
     * 
     */
    private ArrayList material = <MaterialInformatico>;
    /**
     * 
     */
    private ArrayList Mensajes = <BandejaMensajes>;
    /**
     * 
     */
    private Ubicacion Ubica;
    /**
     * 
     */
    private String Descripcion = "";
    /**
     * alta media baja o indiferente
     */
    private String NivelUrgencia = "";
    /**
     * Hardware o software u otra
     */
    private String Categoria = "";
    /**
     * 4 estados
    posibles: pendiente (estado de la incidencia cuando es registrada), e
     */
    private String EstadoIncidencia = "";
    /**
     * 
     */
    private Date FechaIncidencia = new Date(0);
    /**
     * 
     */
    private Date FechaResolucion = new Date(0);
    /**
     * observaciones relativas a dicha resolución. Esta
    observaciones no serán oblig
     */
    private String ObservacionesResolucion = "";
    /**
     * Operation AltaIncidencia
     *
     * @param Inci - 
     * @return boolean
     */
    public boolean AltaIncidencia ( Incidencia Inci ){}
    /**
     * Operation consultarIncidencia
     *
     * @param ID - 
     * @return boolean
     */
    public boolean consultarIncidencia ( String ID ){}
    /**
     * Operation resolverIncidencias
     *
     * @param ID - 
     * @return boolean
     */
    public boolean resolverIncidencias ( String ID ){}
    /**
     * Operation agregarMensajes
     *
     * @param Mens - 
     * @return boolean
     */
    public boolean agregarMensajes ( BandejaMensajes Mens ){}
    /**
     * Operation cambiarEstado
     *
     * @param Estado - 
     * @return boolean
     */
    public boolean cambiarEstado ( String Estado ){}
}

