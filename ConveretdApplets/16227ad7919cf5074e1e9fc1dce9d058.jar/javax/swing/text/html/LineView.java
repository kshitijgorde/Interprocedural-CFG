// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.Document;
import java.awt.Toolkit;
import javax.swing.text.StyledDocument;
import java.awt.Rectangle;
import javax.swing.text.Element;

class LineView extends ParagraphView
{
    int tabBase;
    
    public LineView(final Element element) {
        super(element);
    }
    
    public float getAlignment(final int n) {
        if (n == 0) {
            return 0.0f;
        }
        return super.getAlignment(n);
    }
    
    protected int getCharactersPerTab() {
        return 8;
    }
    
    public float getMinimumSpan(final int n) {
        return this.getPreferredSpan(n);
    }
    
    protected float getPreTab(final float n, final int n2) {
        final Document document = this.getDocument();
        final View viewAtPosition = this.getViewAtPosition(n2, null);
        if (document instanceof StyledDocument && viewAtPosition != null) {
            final int n3 = this.getCharactersPerTab() * Toolkit.getDefaultToolkit().getFontMetrics(((StyledDocument)document).getFont(viewAtPosition.getAttributes())).charWidth('W');
            final int n4 = (int)this.getTabBase();
            return (((int)n - n4) / n3 + 1) * n3 + n4;
        }
        return 10.0f + n;
    }
    
    public int getResizeWeight(final int n) {
        switch (n) {
            case 0: {
                return 1;
            }
            case 1: {
                return 0;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public boolean isVisible() {
        return true;
    }
    
    protected void layout(final int n, final int n2) {
        super.layout(2147483646, n2);
    }
    
    public float nextTabStop(final float n, final int n2) {
        if (this.getTabSet() == null && StyleConstants.getAlignment(this.getAttributes()) == 0) {
            return this.getPreTab(n, n2);
        }
        return super.nextTabStop(n, n2);
    }
}
