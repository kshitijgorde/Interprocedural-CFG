// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter;

final class V extends DocumentFilter
{
    private final /* synthetic */ int a;
    
    V(final int a) {
        this.a = a;
    }
    
    public final void insertString(final FilterBypass filterBypass, final int n, final String s, final AttributeSet set) {
        if (filterBypass.getDocument().getLength() + s.length() <= this.a) {
            super.insertString(filterBypass, n, s, set);
            return;
        }
        Toolkit.getDefaultToolkit().beep();
    }
    
    public final void replace(final FilterBypass filterBypass, final int n, final int n2, final String s, final AttributeSet set) {
        if (filterBypass.getDocument().getLength() + s.length() - n2 <= this.a) {
            super.replace(filterBypass, n, n2, s, set);
            return;
        }
        Toolkit.getDefaultToolkit().beep();
    }
}
