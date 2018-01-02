// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

class PercentFilter extends DecimalFilter
{
    protected boolean isValidText(final String s) {
        return this.validateText("-0123456789,.%", s);
    }
}
