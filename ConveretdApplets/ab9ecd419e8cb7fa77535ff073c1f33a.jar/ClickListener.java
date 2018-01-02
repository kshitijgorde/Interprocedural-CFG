import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class ClickListener extends MouseAdapter implements MouseListener
{
    DirectoryTree tree;
    
    public ClickListener(final DirectoryTree tree) {
        this.tree = tree;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 1) {
            if (this.tree.getFileString() == null) {
                return;
            }
            ((dateiAuswahl)this.tree.getParent()).breakfromoutside();
        }
    }
}
