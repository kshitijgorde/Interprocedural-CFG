// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Container;
import javax.swing.text.View;
import javax.swing.text.JTextComponent;
import java.awt.Shape;
import java.awt.Graphics;
import javax.swing.text.Element;
import javax.swing.text.ComponentView;

class EditableView extends ComponentView
{
    private boolean isVisible;
    
    EditableView(final Element element) {
        super(element);
    }
    
    public float getMaximumSpan(final int n) {
        if (this.isVisible) {
            return super.getMaximumSpan(n);
        }
        return 0.0f;
    }
    
    public float getMinimumSpan(final int n) {
        if (this.isVisible) {
            return super.getMinimumSpan(n);
        }
        return 0.0f;
    }
    
    public float getPreferredSpan(final int n) {
        if (this.isVisible) {
            return super.getPreferredSpan(n);
        }
        return 0.0f;
    }
    
    public boolean isVisible() {
        return this.isVisible;
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        this.getComponent();
        final Container container = this.getContainer();
        if (container != null && this.isVisible != ((JTextComponent)container).isEditable()) {
            this.isVisible = ((JTextComponent)container).isEditable();
            this.preferenceChanged(null, true, true);
            container.repaint();
        }
        if (this.isVisible) {
            super.paint(graphics, shape);
        }
        else {
            this.setSize(0.0f, 0.0f);
        }
    }
    
    public void setParent(final View parent) {
        if (parent != null) {
            final Container container = parent.getContainer();
            if (container != null) {
                this.isVisible = ((JTextComponent)container).isEditable();
            }
        }
        super.setParent(parent);
    }
}
