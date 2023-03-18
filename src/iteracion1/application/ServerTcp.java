// Esta clase define un servidor TCP para la aplicación.
package iteracion1.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import iteracion1.models.Account;
import iteracion1.models.Bank;
import iteracion1.models.Client;

public class ServerTcp {
    // Define el puerto en el que el servidor TCP escucha.
    public static final int PORT = 3400;

    // Declara los objetos para la comunicación con el cliente.
    private ServerSocket listener;
    private Socket serverSideSocket;
    private PrintWriter toNetwork;
    private BufferedReader fromNetwork;

    // Declara un objeto banco para manejar las cuentas de los clientes.
    private Bank bank = new Bank();

    // Constructor de la clase ServerTcp.
    public ServerTcp() {
        System.out.println("Echo TCP server is running on port: " + PORT);
    }

    // Inicializa el servidor.
    public void init() throws Exception {
        // Crea un objeto ServerSocket que escucha en el puerto PORT.
        listener = new ServerSocket(PORT);

        // Espera y procesa las solicitudes de los clientes de forma indefinida.
        while (true) {
            serverSideSocket = listener.accept();

            // Crea los objetos para enviar y recibir mensajes a través de la red.
            createStreams(serverSideSocket);

            // Procesa la solicitud del cliente.
            protocol(serverSideSocket);
        }
    }

    // Procesa la solicitud del cliente.
    public void protocol(Socket socket) throws Exception {
        // Lee la solicitud del cliente a través de fromNetwork.
        String userSolicitude = fromNetwork.readLine();
        
        try {
            // Analiza la solicitud del cliente.
            String[] userSolicitudeAux = userSolicitude.split(",");
            String userOption = userSolicitudeAux[0];
            String responseMessage = "500,Ha ocurrido un error en el servidor";
            
            // Imprime el tipo de solicitud recibida.
            System.out.println("solicitud aceptada, el tipo es "+userOption);

            // Ejecuta la acción correspondiente a la solicitud del cliente.
            switch (userOption) {
                case "register": {
                    responseMessage = registerUserAccount(userSolicitudeAux);
                    break;
                }
                case "update": {
                    responseMessage = updateUserAccount(userSolicitudeAux);
                    break;
                }
                case "delete": {
                    responseMessage = deleteUserAccount(userSolicitudeAux);
                    break;
                }
                default:
                    responseMessage = "404,Opcion no encontrada";
            }

            // Envía la respuesta al cliente a través de toNetwork.
            toNetwork.println(responseMessage);
        } catch (Exception e) {
            // En caso de un error, envía un mensaje de error al cliente.
            toNetwork.println(e.getMessage());
        }
    }
}

private String deleteUserAccount(String[] userSolicitudeAux) {
    // Obtenemos los datos de la solicitud del usuario
    String accountId = userSolicitudeAux[1];
    String deleteReason = userSolicitudeAux[2];
    String password = userSolicitudeAux[3];
    
    // Enviamos los datos al banco para eliminar la cuenta de usuario
    String response = bank.deleteUserAccount(accountId, deleteReason, password);
    return response;
}

private String updateUserAccount(String[] userSolicitude) {
    // Obtenemos los datos de la solicitud del usuario
    String password = userSolicitude[1];
    String attributeToChange = userSolicitude[2];
    String newValueToAtrribute = userSolicitude[3];

    // Enviamos los datos al banco para actualizar la cuenta de usuario
    String response = bank.updateUserAccountAttribute(password, attributeToChange, newValueToAtrribute);
    return response;
}

private String registerUserAccount(String[] userSolicitude) {
    // Obtenemos los datos de la solicitud del usuario
    String names = userSolicitude[1];
    String lastNames = userSolicitude[2];
    String id = userSolicitude[3];
    Double initialAmount = Double.parseDouble(userSolicitude[4]);

    // Creamos un nuevo cliente y cuenta de usuario
    Client newClient = new Client(names, lastNames, id, id, "");
    Account newAccount = new Account(initialAmount, newClient,id);
    
    // Enviamos los datos al banco para registrar al nuevo cliente y su cuenta
    String response = this.bank.registerUserWith(newClient, newAccount);

    return response;
}

private void createStreams(Socket socket) throws Exception {
    // Creamos un PrintWriter y BufferedReader para enviar y recibir datos del socket
    toNetwork = new PrintWriter(socket.getOutputStream(), true);
    fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
}

public static void main(String args[]) throws Exception {
    // Creamos un nuevo objeto ServerTcp y lo inicializamos
    ServerTcp es = new ServerTcp();
    es.init();
}
