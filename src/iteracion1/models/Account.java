package iteracion1.models;

public class Account {
	private Client associatedClient; //Cliente asociado a la cuenta
	private String idAccount; //Identificador de la cuenta
	private boolean isActive = false; //Estado de la cuenta, activa o inactiva
	private  Double currentMoney = 0.0; //Dinero actual en la cuenta

	public Account() { //Constructor vacío
		super();
	}

	public Account(Double currentMoney, Client associatedClient,String idAccount) { //Constructor con parámetros
		super();
		this.idAccount = idAccount;
		this.currentMoney = currentMoney;
		this.associatedClient = associatedClient;
	}

	public Client getAssociatedClient() { //Método para obtener el cliente asociado a la cuenta
		return associatedClient;
	}

	public void setAssociatedClient(Client associatedClient) { //Método para establecer el cliente asociado a la cuenta
		this.associatedClient = associatedClient;
	}

	public String getIdAccount() { //Método para obtener el identificador de la cuenta
		return idAccount;
	}

	public void setIdAccount(String idAccount) { //Método para establecer el identificador de la cuenta
		this.idAccount = idAccount;
	}

	public boolean isActive() { //Método para verificar si la cuenta está activa o inactiva
		return isActive;
	}

	public void setActive(boolean isActive) { //Método para establecer el estado de la cuenta
		this.isActive = isActive;
	}

	public Double getCurrentMoney() { //Método para obtener el dinero actual en la cuenta
		return currentMoney;
	}

	public void setCurrentMoney(Double currentMoney) { //Método para establecer el dinero actual en la cuenta
		this.currentMoney = currentMoney;
	}

	@Override
	public String toString() { //Método para obtener la representación en string de la cuenta
		return "Account [associatedClient=" + associatedClient + ", idAccount=" + idAccount + ", isActive=" + isActive
				+ ", currentMoney=" + currentMoney + "]";
	}

	public boolean isIdEqualTo(String accountId) { //Método para verificar si el identificador de la cuenta es igual al proporcionado
		if (this.idAccount.equals(accountId))
			return true;
		return false;
	}
	
}
