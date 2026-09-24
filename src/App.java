import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class App {
    static JTable tabellaRubrica;
    static DefaultTableModel modelloTabella;
    static ArrayList<Persona> rubrica;
    static PersonaDataModel personaDataModel;
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
        String[] colonne = {"Nome", "Cognome", "Telefono"};
        personaDataModel = new PersonaDataModel(rubrica, colonne);

        JFrame finestra = new JFrame("Rubrica");
        finestra.setLayout(new BorderLayout());
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setSize(600, 400);
        finestra.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel pannello = new JPanel();

        tabellaRubrica = new JTable(personaDataModel);

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
        try{
            EditorPersona editor = new EditorPersona(Persona.class);
            editor.setSize(400, 300);
            editor.setVisible(true);
        }
        catch(Exception e){
            System.out.println("ErroreNuovo" + e);
        }
        
    }

    private static void bottoneModificaAction() {
        if(tabellaRubrica.getSelectedRow() != -1){
            int riga = tabellaRubrica.getSelectedRow();
            Persona personaSelezionata = personaDataModel.getPersonaAt(riga);
            try{
                EditorPersona editor = new EditorPersona(personaSelezionata);
                editor.setSize(400, 300);
                editor.setVisible(true);
            }
            catch(Exception e){
                System.out.println("errore" + e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Seleziona una persona da modificare.");
        }
    }

    private static void bottoneEliminaAction() {
        int riga = tabellaRubrica.getSelectedRow();
        Persona personaSelezionata = personaDataModel.getPersonaAt(riga);
        int risposta = JOptionPane.showConfirmDialog(null, "Eliminare la persona " + personaSelezionata.getNome() + " " + personaSelezionata.getCognome() + "?", "Elimina", JOptionPane.YES_NO_OPTION);
        if (risposta == JOptionPane.YES_OPTION) {
            personaDataModel.removePersonaAt(riga);
        }
        
    }
}
