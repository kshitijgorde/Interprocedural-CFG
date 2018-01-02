// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Toolkit;
import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;
import borland.jbcl.model.ItemEditSite;
import java.awt.Rectangle;
import java.awt.Insets;
import borland.jbcl.model.ItemFormatter;
import borland.jbcl.model.ItemEditor;
import java.awt.TextField;

public class TextItemEditor extends TextField implements ItemEditor
{
    ItemFormatter itemFormatter;
    int alignment;
    
    public TextItemEditor(final int alignment, final Insets margins, final ItemFormatter itemFormatter) {
        this.itemFormatter = itemFormatter;
        this.alignment = alignment;
    }
    
    public TextItemEditor(final int alignment, final Insets margins) {
        this(alignment, margins, null);
    }
    
    public TextItemEditor(final int alignment) {
        this(alignment, new Insets(0, 0, 0, 0));
    }
    
    public TextItemEditor() {
        this(1);
    }
    
    public void startEdit(final Object value, final Rectangle bounds, final ItemEditSite site) {
        String text;
        try {
            if (this.itemFormatter != null && value instanceof Variant) {
                text = this.itemFormatter.format(value);
            }
            else if (value != null) {
                text = value.toString();
            }
            else {
                text = "";
            }
        }
        catch (InvalidFormatException ex) {
            text = "";
        }
        this.setText(text);
        if (site != null) {
            this.setBackground(site.getBackground());
            this.setForeground(site.getForeground());
            this.setFont(site.getFont());
        }
        this.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
        this.setVisible(true);
        final Point clickPoint = (site != null) ? site.getEditClickPoint() : null;
        int position = 0;
        if (clickPoint == null && text != null) {
            position = text.length();
            this.select(0, position);
        }
        else if (text != null) {
            final FontMetrics metrics = Toolkit.getDefaultToolkit().getFontMetrics(this.getFont());
            final int[] widths = metrics.getWidths();
            int i = 0;
            final int xClick = clickPoint.x - bounds.x;
            int x = 0;
            while (i < text.length()) {
                int charWidth;
                if (text.charAt(i) < '\u0100') {
                    charWidth = widths[text.charAt(i)];
                }
                else {
                    charWidth = metrics.charWidth(text.charAt(i));
                }
                final int offset = (charWidth > 3) ? (charWidth / 3) : 1;
                if (x + offset >= xClick) {
                    --i;
                    break;
                }
                if (x + charWidth - offset >= xClick) {
                    break;
                }
                x += charWidth;
                ++i;
            }
            position = i;
            this.select(position, position);
        }
        this.requestFocus();
    }
    
    public void changeBounds(final Rectangle bounds) {
        this.setBounds(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    
    public Object getValue() {
        if (this.itemFormatter != null) {
            try {
                return this.itemFormatter.parse(this.getText());
            }
            catch (InvalidFormatException x) {
                return this.getText();
            }
        }
        return this.getText();
    }
    
    public Component getComponent() {
        return this;
    }
    
    public boolean canPost() {
        return true;
    }
    
    public void endEdit(final boolean posted) {
    }
}
