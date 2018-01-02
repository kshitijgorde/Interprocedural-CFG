// 
// Decompiled by Procyon v0.5.30
// 

package JUpload;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;

public class MyFileListSelectionListener implements ListSelectionListener
{
    private DefaultListModel tModel;
    private JUpload upload;
    
    public MyFileListSelectionListener(final JUpload upload, final DefaultListModel tModel) {
        this.upload = upload;
        this.tModel = tModel;
    }
    
    public void valueChanged(final ListSelectionEvent e) {
        this.debug("MyFileListSelectionListener() e=" + e);
        if (!e.getValueIsAdjusting()) {
            final JList liste = (JList)e.getSource();
            final int index = liste.getSelectedIndex();
            if (index <= this.tModel.size()) {
                final MyFile myFile = this.tModel.getElementAt(index);
                this.debug("Selected file: " + myFile);
            }
        }
    }
    
    private void debug(final String string) {
        if (Configurator.getDebug()) {
            System.out.println(string);
        }
    }
}
