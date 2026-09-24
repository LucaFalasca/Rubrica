

public class Persona {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private int eta;

    public Persona(String nome, String cognome, String indirizzo, String telefono, int eta) {
        if (nome == null || cognome == null || indirizzo == null || telefono == null || nome.equals("") || cognome.equals("") || indirizzo.equals("") || telefono.equals("")) {
            throw new IllegalArgumentException("I campi non possono essere vuoti");
        }
        if (eta < 0) {
            throw new IllegalArgumentException("L'età non può essere negativa");
        }
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.eta = eta;
    }

    public static String[] getAttributiModificabili() {
        return new String[]{"Nome", "Cognome", "Indirizzo", "Telefono", "Età"};
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

}
