// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.LayoutManager;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Dimension;
import java.awt.Component;

public class ComponentView extends View
{
    private Component createdC;
    private Component c;
    
    public ComponentView(final Element element) {
        super(element);
    }
    
    protected Component createComponent() {
        return StyleConstants.getComponent(this.getElement().getAttributes());
    }
    
    public float getAlignment(final int n) {
        if (this.c != null) {
            switch (n) {
                case 0: {
                    return this.c.getAlignmentX();
                }
                case 1: {
                    return this.c.getAlignmentY();
                }
            }
        }
        return super.getAlignment(n);
    }
    
    public final Component getComponent() {
        return this.createdC;
    }
    
    public float getMaximumSpan(final int n) {
        if (this.c == null) {
            return 0.0f;
        }
        final Dimension maximumSize = this.c.getMaximumSize();
        switch (n) {
            case 0: {
                return maximumSize.width;
            }
            case 1: {
                return maximumSize.height;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public float getMinimumSpan(final int n) {
        if (this.c == null) {
            return 0.0f;
        }
        final Dimension minimumSize = this.c.getMinimumSize();
        switch (n) {
            case 0: {
                return minimumSize.width;
            }
            case 1: {
                return minimumSize.height;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public float getPreferredSpan(final int n) {
        if (this.c == null) {
            return 0.0f;
        }
        final Dimension preferredSize = this.c.getPreferredSize();
        switch (n) {
            case 0: {
                return preferredSize.width;
            }
            case 1: {
                return preferredSize.height;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
        final int startOffset = this.getStartOffset();
        final int endOffset = this.getEndOffset();
        if (n >= startOffset && n <= endOffset) {
            final Rectangle bounds = shape.getBounds();
            if (n == endOffset) {
                final Rectangle rectangle = bounds;
                rectangle.x += bounds.width;
            }
            bounds.width = 0;
            return bounds;
        }
        throw new BadLocationException(String.valueOf(n) + " not in range " + startOffset + "," + endOffset, n);
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        if (this.c != null) {
            this.c.setBounds(shape.getBounds());
            if (!this.c.isVisible()) {
                this.c.setVisible(true);
            }
        }
    }
    
    void setComponentParent() {
        if (this.getParent() == null) {
            if (this.c != null) {
                final Container parent = this.c.getParent();
                if (parent != null) {
                    parent.remove(this.c);
                }
            }
        }
        else {
            if (this.c == null) {
                final Component component = this.createComponent();
                if (component != null) {
                    this.createdC = component;
                    (this.c = new Invalidator(component)).setVisible(false);
                }
            }
            if (this.c != null && this.c.getParent() == null) {
                this.getContainer().add(this.c);
            }
        }
    }
    
    public void setParent(final View parent) {
        super.setParent(parent);
        if (SwingUtilities.isEventDispatchThread()) {
            this.setComponentParent();
        }
        else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    final Document document = ComponentView.this.getDocument();
                    try {
                        if (document instanceof AbstractDocument) {
                            ((AbstractDocument)document).readLock();
                        }
                        ComponentView.this.setComponentParent();
                        final Container container = ComponentView.this.getContainer();
                        if (container != null) {
                            ComponentView.this.preferenceChanged(null, true, true);
                            container.repaint();
                        }
                    }
                    finally {
                        if (document instanceof AbstractDocument) {
                            ((AbstractDocument)document).readUnlock();
                        }
                    }
                }
            });
        }
    }
    
    public void setSize(final float n, final float n2) {
        if (this.c != null) {
            if (SwingUtilities.isEventDispatchThread()) {
                this.c.setSize((int)n, (int)n2);
            }
            else {
                SwingUtilities.invokeLater(new SafeResizer((int)n, (int)n2));
            }
        }
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
        final Rectangle rectangle = (Rectangle)shape;
        if (n < rectangle.x + rectangle.width / 2) {
            array[0] = Position.Bias.Forward;
            return this.getStartOffset();
        }
        array[0] = Position.Bias.Backward;
        return this.getEndOffset();
    }
    
    class SafeResizer implements Runnable
    {
        private int width;
        private int height;
        
        SafeResizer(final int width, final int height) {
            this.width = width;
            this.height = height;
        }
        
        public void run() {
            if (ComponentView.this.c != null) {
                ComponentView.this.c.setSize(this.width, this.height);
            }
        }
    }
    
    class Invalidator extends Container
    {
        Dimension min;
        Dimension pref;
        Dimension max;
        float yalign;
        float xalign;
        
        Invalidator(final Component component) {
            this.setLayout(new OverlayLayout(this));
            this.add(component);
            this.min = component.getMinimumSize();
            this.pref = component.getPreferredSize();
            this.max = component.getMaximumSize();
            this.yalign = component.getAlignmentY();
            this.xalign = component.getAlignmentX();
        }
        
        public float getAlignmentX() {
            return this.xalign;
        }
        
        public float getAlignmentY() {
            return this.yalign;
        }
        
        public Dimension getMaximumSize() {
            return this.max;
        }
        
        public Dimension getMinimumSize() {
            return this.min;
        }
        
        public Dimension getPreferredSize() {
            return this.pref;
        }
        
        public void invalidate() {
            super.invalidate();
            this.min = super.getMinimumSize();
            this.pref = super.getPreferredSize();
            this.max = super.getMaximumSize();
            this.yalign = super.getAlignmentY();
            this.xalign = super.getAlignmentX();
            if (this.getParent() != null) {
                ComponentView.this.preferenceChanged(null, true, true);
            }
        }
        
        public void setVisible(final boolean b) {
            super.setVisible(b);
            this.getComponent(0).setVisible(b);
        }
    }
}
