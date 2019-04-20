import Proveedores;
import LineaPedido;
import Pedido;

public class Pedido

{
    /** Attributes */
    /**
     * 
     */
    public Proveedores Proveedor;
    /**
     * 
     */
    private Date FechaPedido = new Date(0);
    /**
     * 
     */
    private ArrayList Linea = <LineaPedido>;
    /**
     * Operation crearLinea
     *
     * @param lin - 
     * @return boolean
     */
    public boolean crearLinea ( LineaPedido lin ){}
    /**
     * Operation bajaPedido
     *
     * @param Ped - 
     */
    public void bajaPedido ( Pedido Ped ){}
    /**
     * Operation crearPedido
     *
     * @param Ped - 
     */
    public void crearPedido ( Pedido Ped ){}
    /**
     * Operation visualizarPedido
     *
     */
    public void visualizarPedido (  ){}
}

