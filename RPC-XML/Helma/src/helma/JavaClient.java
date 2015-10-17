package helma;
import java.util.Vector;
import java.util.Hashtable;
import helma.xmlrpc.*;
import java.io.IOException;
import java.util.Scanner;

public class JavaClient {
    
    //Ubicación del servidor
    private final static String server_url =
          "http://localhost:8086/RPC";
    public static void main (String [] args) {
        try {
            Scanner scan = new Scanner(System.in);         
            System.out.println("Ingrese el primer parámetro: ");
            int a = scan.nextInt();
            System.out.println("Ingrese el segundo parámetro: ");
            int b = scan.nextInt();

            //Creación de un cliente
            XmlRpcClient server = new XmlRpcClient(server_url);

            //Lista de parámetros
            Vector params = new Vector();
            params.addElement(a);
            params.addElement(b);

            //LLamada al servidor
            Hashtable result = (Hashtable) server.execute("calculadora.operaciones", params);
            int suma = ((Integer) result.get("suma")).intValue();
            int resta = ((Integer) result.get("resta")).intValue();
            int multiplicacion = ((Integer) result.get("multiplicacion")).intValue();   
            
            //Imprimir el resultado
            System.out.println("Resultado");
            System.out.println("Suma: " + Integer.toString(suma));
            System.out.println("Resta: " + Integer.toString(resta));
            System.out.println("Multiplicacion: " + Integer.toString(multiplicacion));

        } catch (XmlRpcException exception) {
            System.err.println("JavaClient: XML-RPC Fault #" +
                               Integer.toString(exception.code) + ": " +
                               exception.toString());
        } catch (IOException exception) {
            System.err.println("JavaClient: " + exception.toString());
        }
    }
}