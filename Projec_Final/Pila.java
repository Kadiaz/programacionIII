// Pila.java
public class Pila<T> {

    // Nodo interno de la pila
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T datoP) {
            dato = datoP;
            siguiente = null;
        }
    }

    private Nodo tope;
    private int tamanio;

    public Pila() {
        tope = null;
        tamanio = 0;
    }

    public void apilar(T datoP) {
        Nodo nuevoNodo = new Nodo(datoP);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tamanio++;
    }

    public T desapilar() throws PilaDeshacerVaciaException {
        if (estaVacia()) {
            throw new PilaDeshacerVaciaException(
                "Error: No hay operaciones para deshacer."
            );
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    public T verTope() throws PilaDeshacerVaciaException {
        if (estaVacia()) {
            throw new PilaDeshacerVaciaException(
                "Error: La pila esta vacia."
            );
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia.");
            return;
        }
        Nodo actual = tope;
        int posicion = 1;
        while (actual != null) {
            System.out.println("Posicion " + posicion + ": " + actual.dato);
            actual = actual.siguiente;
            posicion++;
        }
    }
}