import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class App {
    static JTable tabellaRubrica;
    static DefaultTableModel modelloTabella;
    static List<Persona> rubrica;
    static PersonaDataModel personaDataModel;
    
    public static void main(String[] args) throws Exception {

        rubrica = new ArrayList<>();

        try{
            File file = new File("src/informazioni.txt");
            rubrica = extractPersoneFromFile(file);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Errore durante la lettura del file");
            System.out.println("Errore" + e);
        }
        
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
            savePersoneToFile(rubrica, new File("src/informazioni.txt"));
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
                savePersoneToFile(rubrica, new File("src/informazioni.txt"));
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
        try {
            savePersoneToFile(rubrica, new File("src/informazioni.txt"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Errore durante il salvataggio");
        }
        
    }

    private static List<Persona> extractPersoneFromFile(File file) throws FileNotFoundException {
        List<Persona> persone = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length == 5) {
                String nome = parts[0].trim();
                String cognome = parts[1].trim();
                String indirizzo = parts[2].trim();
                String telefono = parts[3].trim();
                int eta = Integer.parseInt(parts[4].trim());
                Persona persona = new Persona(nome, cognome, indirizzo, telefono, eta);
                persone.add(persona);
            }
        }
        scanner.close();
        return persone;
    }

    private static void savePersoneToFile(List<Persona> persone, File file) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Persona persona : persone) {
            sb.append(persona.getNome()).append(";")
              .append(persona.getCognome()).append(";")
              .append(persona.getIndirizzo()).append(";")
              .append(persona.getTelefono()).append(";")
              .append(persona.getEta()).append("\n");
        }
        PrintStream out = new PrintStream(file);
        out.print(sb.toString());
        out.close();
        System.out.println("Salvataggio completato");
    }
}
