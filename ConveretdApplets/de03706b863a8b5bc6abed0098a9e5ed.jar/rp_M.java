import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_M extends MouseAdapter
{
    private /* synthetic */ rp_du a;
    
    protected rp_M(final rp_du a) {
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent) || !this.a.a.isEnabled()) {
            return;
        }
        if (this.a.a.isEditable()) {
            final Component editorComponent;
            if (!((editorComponent = this.a.a.getEditor().getEditorComponent()) instanceof JComponent) || ((JComponent)editorComponent).isRequestFocusEnabled()) {
                editorComponent.requestFocus();
            }
        }
        else if (this.a.a.isRequestFocusEnabled()) {
            this.a.a.requestFocus();
        }
        this.a.a();
    }
}
