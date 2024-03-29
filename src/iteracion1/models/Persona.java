
// Clase abstracta que representa a una persona genérica.
package iteracion1.models;

public abstract class Persona {
    private String name, lastNames, id, password, email;

    // Constructor que recibe información básica de una persona.
    public Persona(String name, String lastNames, String id, String password, String email) {
        super();
        this.name = name;
        this.lastNames = lastNames;
        this.id = id;
        this.password = password;
        this.email = email;
    }

    // Métodos getter y setter para los atributos de una persona.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para imprimir información de una persona.
    @Override
    public String toString() {
        return "Persona [name=" + name + ", lastNames=" + lastNames + ", id=" + id +  "]";
    }
}
