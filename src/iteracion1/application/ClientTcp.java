package iteracion1.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import iteracion1.models.UpdatableUserInfoEnum;


public class ClientTcp {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static final String SERVER = "localhost";
	public static final int PORT = 3400;
	
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	
	private Socket clientSideSocket;

	public   ClientTcp() {
		System.out.println("Echo TCP client is running ...");
	}

	public void init() throws Exception {

		while(true) {
			clientSideSocket = new Socket(SERVER, PORT);
			
			createStreams(clientSideSocket);
			
			protocol(clientSideSocket);

			clientSideSocket.close();
		}
	}

	public void protocol(Socket socket) throws Exception {	
			showMainMenu();
			Integer choosedOption = Integer.parseInt(SCANNER.nextLine());
			String contentToSend = makeContentToSend(choosedOption);
			toNetwork.println(contentToSend);
			
			String fromServer = fromNetwork.readLine();
			System.out.println("[Client] From server: " + fromServer);
}
	private String makeContentToSend(Integer choosedOption) {
		switch (choosedOption) {
		case 1: {return getRegisterInfo();}
		case 2: {return getModifyAccountInfo();}
		case 3: {return getRemoveAccountInfo();}
		default:
		}
		return "none";
	}

	private String getRemoveAccountInfo() {
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

	} private void showMainMenu() {
		System.out.println("------------------------------------");
		System.out.println("------------Menu principal----------");
		System.out.println("------------------------------------");
		System.out.println("1:abrir cuenta");
		System.out.println("2:modificar informacion de la cuenta");
		System.out.println("2:eliminar cuenta");
		
	}

	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	public static void main(String args[]) throws Exception {
		ClientTcp ec = new ClientTcp();
		ec.init();
	}

}
