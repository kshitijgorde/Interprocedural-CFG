// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.SwingConstants;

public abstract class View implements SwingConstants
{
    public static final int BadBreakWeight = 0;
    public static final int GoodBreakWeight = 1000;
    public static final int ExcellentBreakWeight = 2000;
    public static final int ForcedBreakWeight = 3000;
    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    static final Position.Bias[] sharedBiasReturn;
    private View parent;
    private Element elem;
    
    static {
        sharedBiasReturn = new Position.Bias[1];
    }
    
    public View(final Element elem) {
        this.elem = elem;
    }
    
    public View breakView(final int n, final int n2, final float n3, final float n4) {
        return this;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
    }
    
    public View createFragment(final int n, final int n2) {
        return this;
    }
    
    public float getAlignment(final int n) {
        return 0.5f;
    }
    
    public AttributeSet getAttributes() {
        return this.elem.getAttributes();
    }
    
    public int getBreakWeight(final int n, final float n2, final float n3) {
        if (n3 > this.getPreferredSpan(n)) {
            return 1000;
        }
        return 0;
    }
    
    public Shape getChildAllocation(final int n, final Shape shape) {
        return null;
    }
    
    public Container getContainer() {
        final View parent = this.getParent();
        return (parent != null) ? parent.getContainer() : null;
    }
    
    public Document getDocument() {
        return this.elem.getDocument();
    }
    
    public Element getElement() {
        return this.elem;
    }
    
    public int getEndOffset() {
        return this.elem.getEndOffset();
    }
    
    public float getMaximumSpan(final int n) {
        if (this.getResizeWeight(n) == 0) {
            return this.getPreferredSpan(n);
        }
        return 2.14748365E9f;
    }
    
    public float getMinimumSpan(final int n) {
        if (this.getResizeWeight(n) == 0) {
            return this.getPreferredSpan(n);
        }
        return 0.0f;
    }
    
    public int getNextVisualPositionFrom(int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
        array[0] = Position.Bias.Forward;
        switch (n2) {
            case 1: {
                final JTextComponent textComponent = (JTextComponent)this.getContainer();
                n = Utilities.getPositionAbove(textComponent, n, textComponent.modelToView(n).x);
                break;
            }
            case 5: {
                final JTextComponent textComponent2 = (JTextComponent)this.getContainer();
                n = Utilities.getPositionBelow(textComponent2, n, textComponent2.modelToView(n).x);
                break;
            }
            case 7: {
                if (n == -1) {
                    n = Math.max(0, this.getEndOffset() - 1);
                    break;
                }
                n = Math.max(0, n - 1);
                break;
            }
            case 3: {
                if (n == -1) {
                    n = this.getStartOffset();
                    break;
                }
                n = Math.min(n + 1, this.getDocument().getLength());
                break;
            }
            default: {
                throw new IllegalArgumentException("Bad direction: " + n2);
            }
        }
        return n;
    }
    
    public View getParent() {
        return this.parent;
    }
    
    public abstract float getPreferredSpan(final int p0);
    
    public int getResizeWeight(final int n) {
        return 0;
    }
    
    public int getStartOffset() {
        return this.elem.getStartOffset();
    }
    
    public View getView(final int n) {
        return null;
    }
    
    public int getViewCount() {
        return 0;
    }
    
    public ViewFactory getViewFactory() {
        final View parent = this.getParent();
        return (parent != null) ? parent.getViewFactory() : null;
    }
    
    public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public Shape modelToView(final int n, final Shape shape) throws BadLocationException {
        return this.modelToView(n, shape, Position.Bias.Forward);
    }
    
    public abstract Shape modelToView(final int p0, final Shape p1, final Position.Bias p2) throws BadLocationException;
    
    public Shape modelToView(final int n, final Position.Bias bias, final int n2, final Position.Bias bias2, final Shape shape) throws BadLocationException {
        final Shape modelToView = this.modelToView(n, shape, bias);
        Shape shape2;
        if (n2 == this.getEndOffset()) {
            try {
                shape2 = this.modelToView(n2, shape, bias2);
            }
            catch (BadLocationException ex) {
                shape2 = null;
            }
            if (shape2 == null) {
                final Rectangle rectangle = (Rectangle)((shape instanceof Rectangle) ? shape : shape.getBounds());
                shape2 = new Rectangle(rectangle.x + rectangle.width - 1, rectangle.y, 1, rectangle.height);
            }
        }
        else {
            shape2 = this.modelToView(n2, shape, bias2);
        }
        final Rectangle bounds = modelToView.getBounds();
        final Rectangle rectangle2 = (Rectangle)((shape2 instanceof Rectangle) ? shape2 : shape2.getBounds());
        if (bounds.y != rectangle2.y) {
            final Rectangle rectangle3 = (Rectangle)((shape instanceof Rectangle) ? shape : shape.getBounds());
            bounds.x = rectangle3.x;
            bounds.width = rectangle3.width;
        }
        bounds.add(rectangle2);
        return bounds;
    }
    
    public abstract void paint(final Graphics p0, final Shape p1);
    
    public void preferenceChanged(final View view, final boolean b, final boolean b2) {
        final View parent = this.getParent();
        if (parent != null) {
            parent.preferenceChanged(this, b, b2);
        }
    }
    
    public void removeUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
    }
    
    public void setParent(final View parent) {
        this.parent = parent;
    }
    
    public void setSize(final float n, final float n2) {
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape) {
        View.sharedBiasReturn[0] = Position.Bias.Forward;
        return this.viewToModel(n, n2, shape, View.sharedBiasReturn);
    }
    
    public abstract int viewToModel(final float p0, final float p1, final Shape p2, final Position.Bias[] p3);
}
