package helma;
import java.util.Hashtable;
import helma.xmlrpc.*;

public class JavaServer {
    public JavaServer () {
    }

    public Hashtable operaciones (int x, int y) {
        Hashtable result = new Hashtable();
        result.put("suma", new Integer(x + y));
        result.put("resta", new Integer(x - y));
        result.put("multiplicacion", new Integer(x * y));
        return result;
    }
    public static void main (String [] args) {
        try {
            //Creación del servidor
            WebServer server = new WebServer(8086);
            //Servicios permitidos
            server.addHandler("calculadora", new JavaServer());

        } catch (Exception exception) {
            System.err.println("JavaServer: " + exception.toString());
        }
    }
}