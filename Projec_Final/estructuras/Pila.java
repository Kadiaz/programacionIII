package estructuras;

import excepciones.PilaDeshacerVaciaException;

/**
 * Implementacion manual de una Pila (Stack) usando nodos enlazados.
 * Sigue el principio LIFO (Last In, First Out):
 * Se usa para el sistema de deshacer/rehacer operaciones.
 */
public class Pila<T> {

     /**
     * Nodo interno de la pila.
     * Cada nodo guarda un dato y apunta al nodo inferior.
     */
    private class Nodo {
        T dato;
        Nodo siguiente;

        Nodo(T datoP) {
            dato = datoP;
            siguiente = null;
        }
    }

    private Nodo tope;
    private int tam;

    public Pila() {
        tope = null;
        tam = 0;
    }

    public void apilar(T datoP) {
        Nodo nuevoNodo = new Nodo(datoP);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
        tam++;
    }
    
    //Saca y retorna el elemento del tope de la pila.
    public T desapilar() throws PilaDeshacerVaciaException {
        if (estaVacia()) {
            throw new PilaDeshacerVaciaException(
                "Error: No hay operaciones para deshacer."
            );
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tam--;
        return dato;
    }
    
    //Retorna el elemento del tope sin sacarlo de la pila.
    public T verTope() throws PilaDeshacerVaciaException {
        if (estaVacia()) {
            throw new PilaDeshacerVaciaException(
                "Error: La pila esta vacia."
            );
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tam == 0;
    }

    public int getTamanio() {
        return tam;
    }

    /**
     * Muestra todos los elementos de la pila desde el tope hasta el fondo.
     */
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