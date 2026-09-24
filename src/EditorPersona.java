import javax.swing.*;
import java.util.*;
import java.awt.GridLayout;
import java.lang.reflect.Field;

public class EditorPersona extends JFrame {
    
    private Map<String, JTextField> campiTesto;
    private JPanel pannello;
    private Persona persona;

    public EditorPersona(Class<?> classePersona) {
        Field[] campiPersona = classePersona.getDeclaredFields();
        campiTesto = new HashMap<>();
        pannello = new JPanel();
        
        add(pannello);

        pannello.setLayout(new GridLayout(campiPersona.length + 1, 2));
        for (Field campo : campiPersona) {
            JLabel label = new JLabel(campo.getName() + ":");
            JTextField textField = new JTextField(20);
            campiTesto.put(campo.getName(), textField);
            pannello.add(label);
            pannello.add(textField);
        }
        JButton bottoneAnnulla = new JButton("Annulla");
        bottoneAnnulla.addActionListener(e -> bottoneAnnullaAction());
        JButton bottoneSalva = new JButton("Salva");
        bottoneSalva.addActionListener(e -> bottoneSalvaAction());
        pannello.add(bottoneAnnulla);
        pannello.add(bottoneSalva);
        
    }
    public EditorPersona(Persona persona) throws IllegalArgumentException, IllegalAccessException {
        this(persona.getClass());
        this.persona = persona;
        for (Field campo : persona.getClass().getDeclaredFields()){
            campo.setAccessible(true);
            Object valore = campo.get(persona);
            campiTesto.get(campo.getName()).setText(valore.toString());
        }
    }

    private void bottoneSalvaAction() {
        // Eumero tutti i campi per vedere se sono vuoti
        try {
            Persona newPersona = new Persona(
                campiTesto.get("nome").getText(),
                campiTesto.get("cognome").getText(),
                campiTesto.get("indirizzo").getText(),
                campiTesto.get("telefono").getText(),
                Integer.parseInt(campiTesto.get("eta").getText())
            );
            if (this.persona == null) {
                App.personaDataModel.addPersona(newPersona);
                dispose();
            } else {
                App.personaDataModel.updatePersonaAt(App.tabellaRubrica.getSelectedRow(), newPersona);
                dispose();
            }
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "L'età deve essere un numero intero", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        
    }

    private void bottoneAnnullaAction() {
        dispose();
    }
        

}
