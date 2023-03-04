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

	public static final int PORT = 3400;

	private ServerSocket listener;
	private Socket serverSideSocket;
	
	private PrintWriter toNetwork;
	private BufferedReader fromNetwork;
	private Bank bank = new Bank();

	public ServerTcp() {
		System.out.println("Echo TCP server is running on port: " + PORT);
	}

	public void init() throws Exception {
		listener = new ServerSocket(PORT);


		while (true) {
			serverSideSocket = listener.accept();

			createStreams(serverSideSocket);
			protocol(serverSideSocket);
		}
	}
	
	public void protocol(Socket socket) throws Exception {
		String userSolicitude = fromNetwork.readLine();
		try {
			String[] userSolicitudeAux = userSolicitude.split(",");
			String userOption = userSolicitudeAux[0];
			//creamos el estado de la respuesta
			String responseMessage = "500,Ha ocurrido un error en el servidor";
			System.out.println("solicitud aceptada, el tipo es "+userOption);

			switch (userOption) {
			case "register": {
				responseMessage = registerUserAccount(userSolicitudeAux);break;
			}
			case "update": {
				responseMessage = updateUserAccount(userSolicitudeAux);break;
			}
			case "delete": {
				responseMessage = deleteUserAccount(userSolicitudeAux);break;
			}
			default:
				responseMessage = "404,Opcion no encontrada";
			}
			toNetwork.println(responseMessage);
		} catch (Exception e) {
			toNetwork.println(e.getMessage());
		}
		
	}

	private String deleteUserAccount(String[] userSolicitudeAux) {
		String accountId = userSolicitudeAux[1];
		String deleteReason = userSolicitudeAux[2];
		String password = userSolicitudeAux[3];
		
		String response = bank.deleteUserAccount(accountId, deleteReason, password);
		return response;
	}

	private String updateUserAccount(String[] userSolicitude) {
		String pasword = userSolicitude[1];
		String attributeToChange = userSolicitude[2];
		String newValueToAtrribute = userSolicitude[3];

		String response = bank.updateUserAccountAttribute(pasword,attributeToChange,newValueToAtrribute);
		return response;
	}
	private String registerUserAccount(String[] userSolicitude) {
		String names = userSolicitude[1];
		String lastNames = userSolicitude[2];
		String id = userSolicitude[3];
		Double initialAmount = Double.parseDouble(userSolicitude[4]);

		Client newClient = new Client(names, lastNames, id, id, "");
		Account newAccount = new Account(initialAmount, newClient,id);
		
		String response = this.bank.registerUserWith(newClient,newAccount);

		return response;
	}

	private void createStreams(Socket socket) throws Exception {
		toNetwork = new PrintWriter(socket.getOutputStream(), true);
		fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public static void main(String args[]) throws Exception {
		ServerTcp es = new ServerTcp();
		es.init();
	}

}
