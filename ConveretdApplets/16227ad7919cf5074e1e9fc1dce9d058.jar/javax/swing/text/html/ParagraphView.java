// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Container;
import javax.swing.text.JTextComponent;
import javax.swing.text.ViewFactory;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.SizeRequirements;
import javax.swing.text.View;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;

public class ParagraphView extends javax.swing.text.ParagraphView
{
    private AttributeSet attr;
    private StyleSheet.BoxPainter painter;
    
    public ParagraphView(final Element element) {
        super(element);
        final StyleSheet styleSheet = this.getStyleSheet();
        this.attr = styleSheet.getViewAttributes(this);
        this.painter = styleSheet.getBoxPainter(this.attr);
    }
    
    protected SizeRequirements calculateMinorAxisRequirements(final int n, SizeRequirements calculateMinorAxisRequirements) {
        calculateMinorAxisRequirements = super.calculateMinorAxisRequirements(n, calculateMinorAxisRequirements);
        float n2 = 0.0f;
        for (int layoutViewCount = this.getLayoutViewCount(), i = 0; i < layoutViewCount; ++i) {
            final View layoutView = this.getLayoutView(i);
            if (layoutView instanceof InlineView) {
                n2 = Math.max(((InlineView)layoutView).getLongestWordSpan(), n2);
            }
            else {
                n2 = Math.max(layoutView.getMinimumSpan(n), n2);
            }
        }
        calculateMinorAxisRequirements.minimum = (int)n2;
        calculateMinorAxisRequirements.preferred = Math.max(calculateMinorAxisRequirements.minimum, calculateMinorAxisRequirements.preferred);
        calculateMinorAxisRequirements.maximum = Math.max(calculateMinorAxisRequirements.preferred, calculateMinorAxisRequirements.maximum);
        return calculateMinorAxisRequirements;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.attr = this.getStyleSheet().getViewAttributes(this);
        super.changedUpdate(documentEvent, shape, viewFactory);
    }
    
    public AttributeSet getAttributes() {
        return this.attr;
    }
    
    public float getMaximumSpan(final int n) {
        if (!this.isVisible()) {
            return 0.0f;
        }
        return super.getMaximumSpan(n);
    }
    
    public float getMinimumSpan(final int n) {
        if (!this.isVisible()) {
            return 0.0f;
        }
        return super.getMinimumSpan(n);
    }
    
    public float getPreferredSpan(final int n) {
        if (!this.isVisible()) {
            return 0.0f;
        }
        return super.getPreferredSpan(n);
    }
    
    protected StyleSheet getStyleSheet() {
        return ((HTMLDocument)this.getDocument()).getStyleSheet();
    }
    
    public boolean isVisible() {
        final int n = this.getLayoutViewCount() - 1;
        for (int i = 0; i < n; ++i) {
            if (this.getLayoutView(i).isVisible()) {
                return true;
            }
        }
        if (n > 0) {
            final View layoutView = this.getLayoutView(n);
            if (layoutView.getEndOffset() - layoutView.getStartOffset() == 1) {
                return false;
            }
        }
        if (this.getStartOffset() == this.getDocument().getLength()) {
            boolean editable = false;
            final Container container = this.getContainer();
            if (container instanceof JTextComponent) {
                editable = ((JTextComponent)container).isEditable();
            }
            if (!editable) {
                return false;
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        Rectangle bounds;
        if (shape instanceof Rectangle) {
            bounds = (Rectangle)shape;
        }
        else {
            bounds = shape.getBounds();
        }
        this.painter.paint(graphics, bounds.x, bounds.y, bounds.width, bounds.height, this);
        super.paint(graphics, shape);
    }
    
    public void setParent(final View parent) {
        super.setParent(parent);
        this.setPropertiesFromAttributes();
    }
    
    protected void setPropertiesFromAttributes() {
        if (this.attr != null) {
            super.setPropertiesFromAttributes();
            this.setInsets((short)this.painter.getInset(1, this), (short)this.painter.getInset(2, this), (short)this.painter.getInset(3, this), (short)this.painter.getInset(4, this));
            final Object attribute = this.attr.getAttribute(CSS.Attribute.TEXT_ALIGN);
            if (attribute != null) {
                final String string = attribute.toString();
                if (string.equals("left")) {
                    this.setJustification(0);
                }
                else if (string.equals("center")) {
                    this.setJustification(1);
                }
                else if (string.equals("right")) {
                    this.setJustification(2);
                }
                else if (string.equals("justify")) {
                    this.setJustification(3);
                }
            }
        }
    }
}
