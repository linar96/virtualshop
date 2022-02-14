
package modelo;



public class Devolucion {
    
    // Base de datos

    private int idDevolucion;
    private String fechaDevolucion;
    private String motivoDevolucion;
    private String tipoDevolucion;
    private int idFactura;
    private int idPQRS;
    
    public Devolucion () {
    }
    
    //Contructor para crear

    public Devolucion(int idDevolucion, String fechaDevolucion, String motivoDevolucion, String tipoDevolucion, int idFactura, int idPQRS) {
        this.idDevolucion = idDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.motivoDevolucion = motivoDevolucion;
        this.tipoDevolucion = tipoDevolucion;
        this.idFactura = idFactura;
        this.idPQRS = idPQRS;
    }
    
    //Constructor para buscar

    public Devolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }
    
    //Constructor para actualizar

    public Devolucion(String fechaDevolucion, String motivoDevolucion, String tipoDevolucion, int idFactura, int idPQRS) {
        this.fechaDevolucion = fechaDevolucion;
        this.motivoDevolucion = motivoDevolucion;
        this.tipoDevolucion = tipoDevolucion;
        this.idFactura = idFactura;
        this.idPQRS = idPQRS;
    }

   

    public int getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(int idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getMotivoDevolucion() {
        return motivoDevolucion;
    }

    public void setMotivoDevolucion(String motivoDevolucion) {
        this.motivoDevolucion = motivoDevolucion;
    }

    public String getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(String tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdPQRS() {
        return idPQRS;
    }

    public void setIdPQRS(int idPQRS) {
        this.idPQRS = idPQRS;
    }
    
    
    
    
    }
    
    
    
    
    
    

