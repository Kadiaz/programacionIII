/**
 * Representa una maleta que se carga en la bodega del avión.
 * La bodega usa Stack (LIFO): última en entrar, primera en salir.
 */
public class Maleta
{
    private String   id;
    private double   peso;         // en kg
    private Pasajero propietario;

    // Tiempo fijo (en minutos) para cargar o descargar una maleta
    public static final int TIEMPO_CARGA = 1;

    public Maleta(String id, double peso, Pasajero propietario)
    {
        this.id          = id;
        this.peso        = peso;
        this.propietario = propietario;
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