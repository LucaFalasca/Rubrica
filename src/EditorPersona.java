import javax.swing.*;
import java.util.*;
import java.awt.GridLayout;

public class EditorPersona extends JFrame {
    
    private List<JTextField> campiTesto;
    private JPanel pannello;

    public EditorPersona() {
        campiTesto = new ArrayList<>();
        pannello = new JPanel();
        
        add(pannello);
        String[] campi = Persona.getAttributiModificabili();
        pannello.setLayout(new GridLayout(campi.length, 2));
        for (int i = 0; i < campi.length; i++) {
            JLabel label = new JLabel(campi[i] + ":");
            JTextField textField = new JTextField(20);
            campiTesto.add(textField);
            pannello.add(label);
            pannello.add(textField);
        }
    }
    public EditorPersona(Persona persona) {
        this();
        campiTesto.get(0).setText(persona.getNome());
        campiTesto.get(1).setText(persona.getCognome());
        campiTesto.get(2).setText(persona.getIndirizzo());
        campiTesto.get(3).setText(persona.getTelefono());
        campiTesto.get(4).setText(String.valueOf(persona.getEta()));
    }
        

}
