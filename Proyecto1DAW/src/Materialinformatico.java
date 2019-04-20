import MaterialInventariable;
import MaterialNoInventariable;

public abstract class Materialinformatico

{
    /** Attributes */
    /**
     * 
     */
    protected Proveedor Prove;
    /**
     * 
     */
    protected String Codigo = "";
    /**
     * 
     */
    protected String Nombre = "";
    /**
     * 
     */
    protected String Descripcion = "";
    /**
     * 
     */
    protected Date FechaAlta = new Date(0);
    /**
     * 
     */
    protected Date FechaBaja = new Date(0);
    /**
     * 
     */
    protected boolean Baja = false;
    /**
     * 
     */
    protected String MotivoBaja = "";
    /**
     * 
     */
    protected float Precio = 0.0F;
    /**
     * 
     */
    protected int Stock = 0;
    /**
     * 
     */
    protected double Pvp = 0.0D;
    /**
     * 
     */
    protected String Categoria = "";
    /**
     * Operation darBaja
     *
     * @param Material - 
     * @return boolean
     */
    public boolean darBaja ( MaterialInventariable Material );

    /**
     * Operation darAlta
     *
     * @param Material - 
     * @return boolean
     */
    public boolean darAlta ( MaterialInventariable Material );

    /**
     * Operation darAlta
     *
     * @param Material - 
     * @return boolean
     */
    public boolean darAlta ( MaterialNoInventariable Material );

    /**
     * Operation darBaja
     *
     * @param Material - 
     * @return boolean
     */
    public boolean darBaja ( MaterialNoInventariable Material );

}

