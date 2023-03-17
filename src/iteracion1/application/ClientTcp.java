package iteracion1.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import iteracion1.models.UpdatableUserInfoEnum;

public class ClientTcp {
	// Scanner utilizado para leer la entrada del usuario desde la consola
	private static final Scanner SCANNER = new Scanner(System.in);

	// Direccion y puerto del servidor al que se conectara el cliente
	public static final String SERVER = "localhost";
	public static final int PORT = 3400;

	// Flujos de entrada y salida para comunicarse con el servidor
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;

	// Socket utilizado para establecer la conexion con el servidor
	private Socket clientSideSocket;

	// Constructor
	public ClientTcp() {
		System.out.println("Echo TCP client is running ...");
	}

	// Metodo para inicializar el cliente y establecer una conexion con el servidor
	public void init() throws Exception {
		while (true) {
			// Se crea un nuevo socket para conectarse al servidor
			clientSideSocket = new Socket(SERVER, PORT);

			// Se crean los flujos de entrada y salida para comunicarse con el servidor
			createStreams(clientSideSocket);

			// Se inicia el protocolo de comunicacion con el servidor
			protocol(clientSideSocket);

			// Se cierra la conexion con el servidor
			clientSideSocket.close();
		}
	}

	// Metodo que implementa el protocolo de comunicacion con el servidor
	public void protocol(Socket socket) throws Exception {
		// Se muestra el menu principal al usuario
		showMainMenu();
		// Se lee la opcion elegida por el usuario
		Integer choosedOption = Integer.parseInt(SCANNER.nextLine());
		// Se construye el contenido a enviar al servidor segun la opcion elegida
		String contentToSend = makeContentToSend(choosedOption);
		// Se envia el contenido al servidor a traves del flujo de salida
		toNetwork.println(contentToSend);
		// Se lee la respuesta del servidor a traves del flujo de entrada
		String fromServer = fromNetwork.readLine();
		System.out.println("[Client] From server: " + fromServer);
	}

	// Metodo que muestra el menu principal al usuario
	private void showMainMenu() {
		System.out.println("\n---------------------------------------------------------");
		System.out.println("\n------------menu principal------------");
		System.out.println("\n---------------------------------------------------------");
		System.out.println("Ingrese la opcion que desea realizar:");
		System.out.println("1. Registrar una cuenta");
		System.out.println("2. Modificar los datos de una cuenta");
		System.out.println("3. Eliminar una cuenta");
	}

	// Metodo que construye el contenido a enviar al servidor segun la opcion elegida por el usuario
	private String makeContentToSend(Integer choosedOption) {
		switch (choosedOption) {
			case 1: {
				return getRegisterInfo();
			}
			case 2: {
				return getModifyAccountInfo();
			}
			case 3: {
				return getRemoveAccountInfo();
			}
			default:
		}
		return "none";
	}


private String getRemoveAccountInfo() {
		// Método para obtener la información necesaria para eliminar una cuenta
		String deleteUserParams = "";
		System.out.println("\n---------------------------------------------------------");
		System.out.println("\n------------menu de eliminacion de cuenta------------");
		System.out.println("\n---------------------------------------------------------");
		
		System.out.println("Ingrese el numero de cuenta a cancelar");
		String accountNumber = SCANNER.nextLine();

		System.out.println("escriba el motivo de la cancelacion");
		String deleteReason = SCANNER.nextLine();
		System.out.println("Escriba  su clave ");
		String password = SCANNER.nextLine();
		deleteUserParams = "delete,"+accountNumber+","+deleteReason+","+password;
		
		return deleteUserParams;
	}

	private String getModifyAccountInfo() {
		// Método para obtener la información necesaria para modificar una cuenta
		String updateInfo = "";
		System.out.println("\n---------------------------------------------------------");
		System.out.println("\n------------menu de modificacion de cuenta------------");
		System.out.println("\n---------------------------------------------------------");
		System.out.println("Ingrese su clave");
		String clave = SCANNER.nextLine();

		System.out.println("escriba el atributo que desea cambiar, atributos cambiables:");
		for (UpdatableUserInfoEnum infoUpdatable : UpdatableUserInfoEnum.values()) {
		System.out.println((infoUpdatable+"").toLowerCase());
		}
		System.out.println("------------------------------------------------------------");
		String userInformationToChange = SCANNER.nextLine();
		System.out.println("Escriba  su nuevo "+userInformationToChange);
		String newValue = SCANNER.nextLine();
		updateInfo = "update,"+clave+","+userInformationToChange+","+newValue;
		
		return updateInfo; 
	}

	private String getRegisterInfo() {
		// Método para obtener la información necesaria para registrar una cuenta
		String registerInfo = "";
		System.out.println("\n---------------------------------------------------------");
		System.out.println("\n------------------Menu de modificacion de cuenta");
		System.out.println("\n---------------------------------------------------------");
		System.out.println("Ingrese sus nombres");
		String names = SCANNER.nextLine();
		System.out.println("Ingrese sus apellidos");
		String lastNames = SCANNER.nextLine();
		System.out.println("Ingrese su cedula");
		String id = SCANNER.nextLine();
		System.out.println("Ingrese el monto con el que iniciara la cuenta");
		Double startingAmount = Double.parseDouble(SCANNER.nextLine());
		registerInfo = "register,"+names+","+lastNames+","+id+","+startingAmount;
		
		return registerInfo; 

	} 
	private void showMainMenu() {
		// Método para mostrar el menú principal
		System.out.println("------------------------------------");
		System.out.println("------------Menu principal----------");
		System.out.println("------------------------------------");
		System.out.println("1:abrir cuenta");
		System.out.println("2:modificar informacion de la cuenta");
		System.out.println("2:eliminar cuenta");	
	}

private void createStreams(Socket socket) throws Exception {
    // Crear objeto PrintWriter para enviar datos al servidor
    toNetwork = new PrintWriter(socket.getOutputStream(), true);
    // Crear objeto BufferedReader para recibir datos del servidor
    fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
}

public static void main(String args[]) throws Exception {
    // Crear objeto ClientTcp
    ClientTcp ec = new ClientTcp();
    // Iniciar el cliente
    ec.init();
}
