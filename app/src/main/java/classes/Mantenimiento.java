package classes;


public class Mantenimiento {

    private int id, campaña;
    private String finca, tipo;
    private double latitud, longitud;

    public Mantenimiento(int id, int campaña, String finca, String tipo, double latitud, double longitud){
        this.id = id;
        this.campaña = campaña;
        this.finca = finca;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCampaña() {
        return campaña;
    }

    public void setCampaña(int campaña) {
        this.campaña = campaña;
    }

    public String getFinca() {
        return finca;
    }

    public void setFinca(String finca) {
        this.finca = finca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
