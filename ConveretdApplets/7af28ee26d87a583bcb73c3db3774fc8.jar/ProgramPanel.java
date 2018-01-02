import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ProgramPanel extends JPanel implements ProgramInterface
{
    private JTable _tableProgram;
    private JScrollPane _scrollTable;
    private Vector _vectorProgram;
    
    public ProgramPanel(final int x, final int y, final int width, final int height, final Color background, final Vector program) {
        this._vectorProgram = program;
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        this.setBackground(background);
        this.setVisible(true);
        final String[] arrHeader = { "Current State", "Current Value", "Value To Write", "Direction", "Next State" };
        final String[][] arrValues = new String[program.size()][5];
        for (int i = 0; i < program.size(); ++i) {
            final StateClass state = program.elementAt(i);
            arrValues[i][0] = state.getStateCurrent();
            arrValues[i][1] = state.getValueCurrent();
            arrValues[i][2] = state.getValueNext();
            arrValues[i][3] = state.getDirection();
            arrValues[i][4] = state.getStateNext();
        }
        (this._tableProgram = new JTable(arrValues, arrHeader)).setSelectionMode(0);
        this._tableProgram.getTableHeader().setReorderingAllowed(false);
        this._tableProgram.setEnabled(false);
        this._tableProgram.setBackground(new Color(192, 192, 192));
        this._tableProgram.clearSelection();
        this._tableProgram.changeSelection(0, 0, false, false);
        this._tableProgram.setSelectionBackground(Color.orange);
        (this._scrollTable = new JScrollPane()).setViewportView(this._tableProgram);
        this._scrollTable.setBounds(0, 40, width, height - 40);
        this.add(this._scrollTable);
    }
    
    public StateClass setState(final StateClass state) {
        StateClass stateReturn = null;
        final String passedState = state.getStateCurrent().trim();
        final String passedValue = state.getValueCurrent().trim();
        for (int i = 0; i < this._tableProgram.getRowCount(); ++i) {
            final String thisState = ((String)this._tableProgram.getValueAt(i, 0)).trim();
            final String thisValue = ((String)this._tableProgram.getValueAt(i, 1)).trim();
            if (thisState.equalsIgnoreCase(passedState) && thisValue.equalsIgnoreCase(passedValue)) {
                if (i == this._tableProgram.getRowCount() - 1) {
                    this._tableProgram.setSelectionForeground(Color.red);
                }
                else {
                    this._tableProgram.setSelectionForeground(Color.black);
                }
                this._tableProgram.changeSelection(i, 0, false, false);
                this._tableProgram.repaint();
                stateReturn = this._vectorProgram.elementAt(i);
            }
        }
        return stateReturn;
    }
}
