import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PersonaDataModel extends AbstractTableModel{

    private List<Persona> persone;
    private String[] colonne;

    public PersonaDataModel(List<Persona> persone, String[] colonne){
        this.persone = persone;
        this.colonne = colonne;
    }

    public Persona getPersonaAt(int riga){
        return persone.get(riga);
    }

    public void removePersonaAt(int riga){
        persone.remove(riga);
        fireTableDataChanged();
    }

    public void addPersona(Persona persona){
        persone.add(persona);
        fireTableDataChanged();
    }

    public void updatePersonaAt(int riga, Persona persona){
        persone.set(riga, persona);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    @Override
    public int getRowCount() {
        return persone.size();
    }

    @Override 
    public String getColumnName(int column) {
        return colonne[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona = getPersonaAt(rowIndex);
        switch(columnIndex){
            case 0: return persona.getNome();
            case 1: return persona.getCognome();
            case 2: return persona.getTelefono();
            default: throw new IndexOutOfBoundsException();
        }
    }
    
}
