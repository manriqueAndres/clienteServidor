package iteracion1.models;

import java.util.ArrayList;

public class Bank {
	private String nit = "12345";
	private ArrayList<Client> clientList = new ArrayList<>();
	private ArrayList<Account> accountList = new ArrayList<>();
	private ArrayList<Adviser> adviserList = new ArrayList<>();
	public Bank() {
		super();
	}
	public String registerUserWith(Client newClient, Account newAccount) {
		if(clientList.add(newClient) && accountList.add(newAccount)) {
			return "200,El usuario y la cuenta se han registrado con exito";
		}
		return "500,Error al registrar el nuevo usuario";
	}
	
	public String updateUserAccountAttribute(String names, String attributeToChange, String newValueToAtrribute) {
		for (Client client : clientList) {
			if(client.isNameEqualTo(names)) {
				client.updateAttribute(attributeToChange,newValueToAtrribute);
				return "200. datos actualizados con exito "+client.toString();
			}
		}
		return "500. cliente no encontrado";
	}
	public String deleteUserAccount(String accountId, String deleteReason, String password) {
		Account account = getAccountbyId(accountId);
		if(account != null) {
			Client accountClient = account.getAssociatedClient();
			if (accountClient.isPasswordEqualTo(password)) {
				clientList.remove(accountClient);
				accountList.remove(account);
				return "200. cuenta eliminada con exito";
			}
			return "500. clave incorrecta";
		}
		return "500. cuenta no encontrada";
	}
	private Account getAccountbyId(String accountId) {
		for (Account account : accountList) {
			if(account.isIdEqualTo(accountId))
				return account;
		}
		return null;
	}
	

}
