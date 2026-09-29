import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    public static List<Persona> extractPersoneFromFile(File file) throws FileNotFoundException {
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


    public static void savePersoneToFile(List<Persona> persone, File file) throws Exception {
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
