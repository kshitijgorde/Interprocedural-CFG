// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.text.StyleConstants;
import javax.swing.text.ViewFactory;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.SizeRequirements;
import javax.swing.text.View;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;

class TableView extends javax.swing.text.TableView
{
    private AttributeSet attr;
    private StyleSheet.BoxPainter painter;
    
    public TableView(final Element element) {
        super(element);
        this.attr = this.getStyleSheet().getViewAttributes(this);
    }
    
    void adjustSizeForCSS(final int n, final SizeRequirements sizeRequirements) {
        if (n == 0) {
            final Object attribute = this.attr.getAttribute(CSS.Attribute.WIDTH);
            if (attribute != null) {
                final int n3;
                final int n2 = n3 = (int)((CSS.LengthValue)attribute).getValue();
                sizeRequirements.preferred = n3;
                sizeRequirements.minimum = n3;
                sizeRequirements.maximum = Math.max(sizeRequirements.maximum, n2);
            }
        }
        else {
            final Object attribute2 = this.attr.getAttribute(CSS.Attribute.HEIGHT);
            if (attribute2 != null) {
                final int n5;
                final int n4 = n5 = (int)((CSS.LengthValue)attribute2).getValue();
                sizeRequirements.preferred = n5;
                sizeRequirements.minimum = n5;
                sizeRequirements.maximum = Math.max(sizeRequirements.maximum, n4);
            }
        }
    }
    
    protected SizeRequirements calculateMinorAxisRequirements(final int n, final SizeRequirements sizeRequirements) {
        final SizeRequirements calculateMinorAxisRequirements = super.calculateMinorAxisRequirements(n, sizeRequirements);
        this.adjustSizeForCSS(n, calculateMinorAxisRequirements);
        return calculateMinorAxisRequirements;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.changedUpdate(documentEvent, shape, this.createViewFactory(viewFactory));
    }
    
    protected TableRow createTableRow(final Element element) {
        if (element.getAttributes().getAttribute(StyleConstants.NameAttribute) == HTML.Tag.TR) {
            return new RowView(element);
        }
        return null;
    }
    
    ViewFactory createViewFactory(final ViewFactory viewFactory) {
        return new TableFactory(viewFactory);
    }
    
    public AttributeSet getAttributes() {
        return this.attr;
    }
    
    public float getMaximumSpan(final int n) {
        return this.getPreferredSpan(n);
    }
    
    protected StyleSheet getStyleSheet() {
        return ((HTMLDocument)this.getDocument()).getStyleSheet();
    }
    
    public ViewFactory getViewFactory() {
        return this.createViewFactory(super.getViewFactory());
    }
    
    public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.insertUpdate(documentEvent, shape, this.createViewFactory(viewFactory));
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Rectangle rectangle = (Rectangle)shape;
        this.painter.paint(graphics, rectangle.x, rectangle.y, rectangle.width, rectangle.height, this);
        super.paint(graphics, rectangle);
    }
    
    public void removeUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.removeUpdate(documentEvent, shape, this.createViewFactory(viewFactory));
    }
    
    public void setParent(final View parent) {
        super.setParent(parent);
        this.painter = this.getStyleSheet().getBoxPainter(this.attr);
        this.setPropertiesFromAttributes();
    }
    
    protected void setPropertiesFromAttributes() {
        if (this.attr != null) {
            this.setInsets((short)this.painter.getInset(1, this), (short)this.painter.getInset(2, this), (short)this.painter.getInset(3, this), (short)this.painter.getInset(4, this));
        }
    }
    
    class RowView extends TableRow
    {
        private AttributeSet attr;
        
        public RowView(final Element element) {
            super(element);
            this.attr = this.getStyleSheet().getViewAttributes(this);
        }
        
        public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
            super.changedUpdate(documentEvent, shape, TableView.this.createViewFactory(viewFactory));
            final int offset = documentEvent.getOffset();
            if (offset <= this.getStartOffset() && offset + documentEvent.getLength() >= this.getEndOffset()) {
                this.attr = this.getStyleSheet().getViewAttributes(this);
            }
        }
        
        public AttributeSet getAttributes() {
            return this.attr;
        }
        
        protected StyleSheet getStyleSheet() {
            return ((HTMLDocument)this.getDocument()).getStyleSheet();
        }
    }
    
    static class CellView extends BlockView
    {
        public CellView(final Element element) {
            super(element, 1);
        }
        
        protected SizeRequirements calculateMajorAxisRequirements(final int n, final SizeRequirements sizeRequirements) {
            final SizeRequirements calculateMajorAxisRequirements = super.calculateMajorAxisRequirements(n, sizeRequirements);
            calculateMajorAxisRequirements.maximum = Integer.MAX_VALUE;
            return calculateMajorAxisRequirements;
        }
        
        protected void layoutMajorAxis(final int n, final int n2, final int[] array, final int[] array2) {
            super.layoutMajorAxis(n, n2, array, array2);
            int n3 = 0;
            final int length = array2.length;
            for (int i = 0; i < length; ++i) {
                n3 += array2[i];
            }
            int n4 = 0;
            if (n3 < n) {
                String s = (String)this.getElement().getAttributes().getAttribute(HTML.Attribute.VALIGN);
                if (s == null) {
                    s = (String)this.getElement().getParentElement().getAttributes().getAttribute(HTML.Attribute.VALIGN);
                }
                if (s == null || s.equals("middle")) {
                    n4 = (n - n3) / 2;
                }
                else if (s.equals("bottom")) {
                    n4 = n - n3;
                }
            }
            if (n4 != 0) {
                for (int j = 0; j < length; ++j) {
                    final int n5 = j;
                    array[n5] += n4;
                }
            }
        }
    }
    
    class TableFactory implements ViewFactory
    {
        ViewFactory f;
        
        TableFactory(final ViewFactory f) {
            this.f = f;
        }
        
        public View create(final Element element) {
            final Object attribute = element.getAttributes().getAttribute(StyleConstants.NameAttribute);
            if (attribute instanceof HTML.Tag) {
                final HTML.Tag tag = (HTML.Tag)attribute;
                if (tag == HTML.Tag.TR) {
                    return TableView.this.createTableRow(element);
                }
                if (tag == HTML.Tag.CAPTION) {
                    return new ParagraphView(element);
                }
            }
            return this.f.create(element);
        }
    }
}
