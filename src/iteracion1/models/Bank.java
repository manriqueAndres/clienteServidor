package iteracion1.models;

import java.util.ArrayList;

public class Bank {
    // Atributos de la clase Bank
    private String nit = "12345"; // NIT del banco
    private ArrayList<Client> clientList = new ArrayList<>(); // Lista de clientes del banco
    private ArrayList<Account> accountList = new ArrayList<>(); // Lista de cuentas del banco
    private ArrayList<Adviser> adviserList = new ArrayList<>(); // Lista de asesores del banco
    
    // Constructor vacío
    public Bank() {
        super();
    }
    
    // Método para registrar un nuevo usuario y cuenta en el banco
    public String registerUserWith(Client newClient, Account newAccount) {
        if(clientList.add(newClient) && accountList.add(newAccount)) {
            // Si se agregaron correctamente a las listas de clientes y cuentas
            return "200,El usuario y la cuenta se han registrado con exito";
        }
        // Si no se pudieron agregar
        return "500,Error al registrar el nuevo usuario";
    }
    
    // Método para actualizar un atributo de una cuenta de un cliente del banco
    public String updateUserAccountAttribute(String names, String attributeToChange, String newValueToAtrribute) {
        for (Client client : clientList) {
            if(client.isNameEqualTo(names)) { // Si se encuentra al cliente con el nombre dado
                client.updateAttribute(attributeToChange,newValueToAtrribute); // Se actualiza el atributo dado
                return "200. datos actualizados con exito "+client.toString(); // Se retorna mensaje de éxito
            }
        }
        // Si no se encontró al cliente
        return "500. cliente no encontrado";
    }
    
    // Método para eliminar una cuenta de un cliente del banco
    public String deleteUserAccount(String accountId, String deleteReason, String password) {
        Account account = getAccountbyId(accountId); // Se obtiene la cuenta correspondiente al ID dado
        if(account != null) { // Si se encontró la cuenta
            Client accountClient = account.getAssociatedClient(); // Se obtiene el cliente asociado a la cuenta
            if (accountClient.isPasswordEqualTo(password)) { // Si la contraseña dada es la correcta
                clientList.remove(accountClient); // Se remueve al cliente de la lista de clientes del banco
                accountList.remove(account); // Se remueve la cuenta de la lista de cuentas del banco
                return "200. cuenta eliminada con exito"; // Se retorna mensaje de éxito
            }
            // Si la contraseña es incorrecta
            return "500. clave incorrecta";
        }
        // Si no se encontró la cuenta
        return "500. cuenta no encontrada";
    }
    
    // Método privado para obtener una cuenta a partir de un ID dado
    private Account getAccountbyId(String accountId) {
        for (Account account : accountList) {
            if(account.isIdEqualTo(accountId)) // Si el ID coincide con el dado
                return account; // Se retorna la cuenta
        }
        // Si no se encontró la cuenta
        return null;
    }
} 
