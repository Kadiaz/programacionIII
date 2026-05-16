package excepciones;

public class ArchivoInvalidoException extends Exception {
    public ArchivoInvalidoException(String mensajeP) {
        super(mensajeP);
    }
}