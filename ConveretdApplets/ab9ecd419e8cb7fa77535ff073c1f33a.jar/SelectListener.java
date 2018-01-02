import javax.swing.tree.TreePath;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class SelectListener implements TreeSelectionListener
{
    DirectoryTree treeInstanz;
    
    public SelectListener(final DirectoryTree treeInstanz) {
        this.treeInstanz = treeInstanz;
    }
    
    public void valueChanged(final TreeSelectionEvent treeSelectionEvent) {
        final TreePath newLeadSelectionPath = treeSelectionEvent.getNewLeadSelectionPath();
        if (newLeadSelectionPath == null) {
            return;
        }
        String fileString = "";
        final String property = System.getProperty("file.separator");
        for (int i = 1; i < newLeadSelectionPath.getPathCount(); ++i) {
            fileString += newLeadSelectionPath.getPathComponent(i);
            if (i > 1 && i < newLeadSelectionPath.getPathCount() - 1) {
                fileString += property;
            }
        }
        this.treeInstanz.setFileString(fileString);
    }
}
