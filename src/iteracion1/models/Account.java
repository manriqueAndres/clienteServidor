package iteracion1.models;

public class Account {
	private Client associatedClient;
	private String idAccount;
	private boolean isActive = false;
	private  Double currentMoney = 0.0;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Double currentMoney, Client associatedClient,String idAccount) {
		super();
		this.idAccount = idAccount;
		this.currentMoney = currentMoney;
		this.associatedClient = associatedClient;
	}

	public Client getAssociatedClient() {
		return associatedClient;
	}

	public void setAssociatedClient(Client associatedClient) {
		this.associatedClient = associatedClient;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Double getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(Double currentMoney) {
		this.currentMoney = currentMoney;
	}

	@Override
	public String toString() {
		return "Account [associatedClient=" + associatedClient + ", idAccount=" + idAccount + ", isActive=" + isActive
				+ ", currentMoney=" + currentMoney + "]";
	}

	public boolean isIdEqualTo(String accountId) {
		if (this.idAccount.equals(accountId))
			return true;
		return false;
	}
	
}
