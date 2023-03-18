package iteracion1.models;

public class Client extends Persona{

    public Client(String name, String lastNames, String id, String password, String email) {
        super(name, lastNames, id, password, email);
    }

    // Verifica si el nombre del cliente es igual al nombre dado
    public boolean isNameEqualTo(String names) {
        if(getName().equals(names))
            return true;
        return false;
    }

    // Actualiza el atributo del cliente dado con el nuevo valor proporcionado
    public void updateAttribute(String attributeToChange, String newValueToAtrribute) {
        attributeToChange = attributeToChange.toLowerCase();
        
        switch (attributeToChange) {
        case "nombres":
            setName(newValueToAtrribute);
            break;
        case "apellidos":
            setLastNames(newValueToAtrribute);
            break;
        default:
            break;
        }
    }

    // Verifica si la contraseña del cliente es igual a la contraseña dada
    public boolean isPasswordEqualTo(String password) {
        if(getPassword().equals(password))
            return true;
        return false;
    }
}
