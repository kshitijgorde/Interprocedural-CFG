// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.Document;
import javax.swing.text.BadLocationException;
import java.text.BreakIterator;
import javax.swing.text.ViewFactory;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import javax.swing.text.View;
import javax.swing.text.Element;
import javax.swing.text.AttributeSet;
import javax.swing.text.LabelView;

public class InlineView extends LabelView
{
    AttributeSet attr;
    
    public InlineView(final Element element) {
        super(element);
        this.attr = this.getStyleSheet().getViewAttributes(this);
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        super.changedUpdate(documentEvent, shape, viewFactory);
        this.attr = this.getStyleSheet().getViewAttributes(this);
        this.preferenceChanged(null, true, true);
    }
    
    public AttributeSet getAttributes() {
        return this.attr;
    }
    
    float getLongestWordSpan() {
        float n = 0.0f;
        try {
            final Document document = this.getDocument();
            final int startOffset = this.getStartOffset();
            final String text = document.getText(startOffset, this.getEndOffset() - startOffset);
            int n2 = startOffset;
            int n3 = startOffset;
            if (text != null && text.length() > 0) {
                final BreakIterator wordInstance = BreakIterator.getWordInstance();
                wordInstance.setText(text);
                int first = wordInstance.first();
                for (int i = wordInstance.next(); i != -1; i = wordInstance.next()) {
                    if (i - first > n3 - n2) {
                        n2 = first;
                        n3 = i;
                    }
                    first = i;
                }
            }
            if (n3 - n2 > 0) {
                n = this.getFontMetrics().stringWidth(text.substring(n2, n3));
            }
        }
        catch (BadLocationException ex) {}
        return n;
    }
    
    protected StyleSheet getStyleSheet() {
        return ((HTMLDocument)this.getDocument()).getStyleSheet();
    }
    
    protected void setPropertiesFromAttributes() {
        super.setPropertiesFromAttributes();
        final AttributeSet attributes = this.getAttributes();
        final Object attribute = attributes.getAttribute(CSS.Attribute.TEXT_DECORATION);
        this.setUnderline(attribute != null && attribute.toString().indexOf("underline") >= 0);
        this.setStrikeThrough(attribute != null && attribute.toString().indexOf("line-through") >= 0);
        final Object attribute2 = attributes.getAttribute(CSS.Attribute.VERTICAL_ALIGN);
        this.setSuperscript(attribute2 != null && attribute2.toString().indexOf("sup") >= 0);
        this.setSubscript(attribute2 != null && attribute2.toString().indexOf("sub") >= 0);
    }
}
