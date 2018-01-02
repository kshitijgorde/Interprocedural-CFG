// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter;

class DateFilter extends DocumentFilter
{
    public void replace(final FilterBypass filterBypass, final int n, final int n2, final String s, final AttributeSet set) throws BadLocationException {
        if (this.isValidText(s)) {
            super.replace(filterBypass, n, n2, s, set);
        }
    }
    
    protected boolean isValidText(final String s) {
        return this.validateText("0123456789/", s);
    }
    
    protected boolean validateText(final String s, final String s2) {
        for (int i = 0; i < s2.length(); ++i) {
            if (s.indexOf(s2.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }
}
