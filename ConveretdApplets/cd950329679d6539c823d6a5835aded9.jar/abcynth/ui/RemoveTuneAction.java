// 
// Decompiled by Procyon v0.5.30
// 

package abcynth.ui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class RemoveTuneAction extends AbstractAction
{
    private TuneBookTable m_tunebookTable;
    
    public RemoveTuneAction(final String name, final String description, final int shortcut, final TuneBookTable tuneBookTable) {
        this.m_tunebookTable = null;
        this.m_tunebookTable = tuneBookTable;
        this.putValue("Name", name);
        this.putValue("ShortDescription", description);
        this.putValue("MnemonicKey", new Integer(shortcut));
    }
    
    public void actionPerformed(final ActionEvent e) {
        final int selectedIndex = this.m_tunebookTable.getSelectionModel().getLeadSelectionIndex();
        if (selectedIndex != -1) {
            final int viewColumnNumber = this.m_tunebookTable.convertColumnIndexToView(10);
            final int selectedTuneIndex = (int)this.m_tunebookTable.getValueAt(selectedIndex, viewColumnNumber);
            this.m_tunebookTable.getTuneBook().removeTune(selectedTuneIndex);
        }
    }
}
