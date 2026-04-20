public class Maleta
{
    private String   id;
    private double   peso;
    private Pasajero propietario;

    public static final int TIEMPO_CARGA = 1;
    //Carga y descarga de maletad
    public Maleta(String idMaleta, double pesoMaleta, Pasajero dueno)
    {
        id          = idMaleta;
        peso        = pesoMaleta;
        propietario = dueno;
    }

    public String   getId()          { return id;          }
    public double   getPeso()        { return peso;         }
    public Pasajero getPropietario() { return propietario;  }

    @Override
    public String toString()
    {
        return "Maleta[" + id + " | " + peso + "kg | " + propietario.getNombre() + "]";
    }
}