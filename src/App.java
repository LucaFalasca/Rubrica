import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class App {
    static JTable tabellaRubrica;
    static DefaultTableModel modelloTabella;
    static ArrayList<Persona> rubrica;
    public static void main(String[] args) throws Exception {

        Persona persona1 = new Persona("Mario", "Rossi", "Via Roma 1", "1234567890", 30);
        Persona persona2 = new Persona("Luigi", "Verdi", "Via Milano 2", "0987654321", 25);
        Persona persona3 = new Persona("Giulia", "Bianchi", "Via Napoli 3", "1112223334", 28);
        rubrica = new ArrayList<>();
        rubrica.add(persona1);
        rubrica.add(persona2);
        rubrica.add(persona3);

        ArrayList<Persona> rubrica = new ArrayList<>();

        rubrica.add(persona1);
        rubrica.add(persona2);
        rubrica.add(persona3);

        JFrame finestra = new JFrame("Rubrica");
        finestra.setLayout(new BorderLayout());
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setSize(600, 400);
        finestra.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel pannello = new JPanel();

        String[] colonne = {"Nome", "Cognome", "Telefono"};

        Object[][] dati = new Object[rubrica.size()][3];
        for (int i = 0; i < rubrica.size(); i++) {
            Persona persona = rubrica.get(i);
            dati[i][0] = persona.getNome();
            dati[i][1] = persona.getCognome();
            dati[i][2] = persona.getTelefono();
        }

        tabellaRubrica = new JTable(dati, colonne);

        pannello.add(new JScrollPane(tabellaRubrica));

        finestra.add(pannello, BorderLayout.CENTER);

        JPanel pannelloBottoni = new JPanel();

        JButton bottoneNuovo = new JButton("Nuovo");
        bottoneNuovo.addActionListener(e -> bottoneNuovoAction());
        JButton bottoneModifica = new JButton("Modifica");
        bottoneModifica.addActionListener(e -> bottoneModificaAction());
        JButton bottoneElimina = new JButton("Elimina");
        bottoneElimina.addActionListener(e -> bottoneEliminaAction());

        pannelloBottoni.add(bottoneNuovo);
        pannelloBottoni.add(bottoneModifica);
        pannelloBottoni.add(bottoneElimina);

        finestra.add(pannelloBottoni, BorderLayout.SOUTH);
        finestra.setVisible(true);
    }

    private static void bottoneNuovoAction() {
        // Logica per il bottone "Nuovo"
        EditorPersona editor = new EditorPersona();
        editor.setSize(400, 300);
        editor.setVisible(true);
    }

    private static void bottoneModificaAction() {
        // Logica per il bottone "Modifica"
        int riga = tabellaRubrica.getSelectedRow();
        Persona personaSelezionata = rubrica.get(riga);
        EditorPersona editor = new EditorPersona(personaSelezionata);
        editor.setSize(400, 300);
        editor.setVisible(true);
    }

    private static void bottoneEliminaAction() {
        // Logica per il bottone "Elimina"
        int riga = tabellaRubrica.getSelectedRow();
        rubrica.remove(riga);
        tabellaRubrica.remove(riga);
        tabellaRubrica.revalidate();
        tabellaRubrica.repaint();

    }
}
