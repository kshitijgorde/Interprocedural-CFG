// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;

public class myRemoveListener implements ActionListener
{
    DefaultListModel tModel;
    JList list;
    JUpload parent;
    
    myRemoveListener(final JUpload parent, final DefaultListModel tModel, final JList list) {
        this.debug("myRemoveListener()");
        this.parent = parent;
        this.tModel = tModel;
        this.list = list;
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.debug("myRemoveListener() actionPerformed() e=" + e);
        final int[] selected = this.list.getSelectedIndices();
        if (selected.length == 0) {
            JOptionPane.showMessageDialog(this.parent, "No files selected.\nSelect the files you wish to remove from the upload queue", "No files selected", 0);
            return;
        }
        for (int i = selected.length - 1; i >= 0; --i) {
            this.debug(" remove() i=" + i + " selected=" + selected[i] + " count=" + selected.length);
            this.tModel.remove(selected[i]);
        }
        this.parent.statpanel.updateModel(this.tModel);
    }
    
    public void debug(final String s) {
        if (Configurator.getDebug()) {
            System.out.println(s);
        }
    }
}
