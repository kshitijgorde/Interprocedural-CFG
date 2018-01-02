// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import javax.swing.JLabel;

class LabelFactory
{
    public static JLabel getLabel(final String s) {
        final JLabel label = new JLabel(s + " :");
        label.setHorizontalAlignment(4);
        return label;
    }
    
    public static JLabel getLabel(final String s, final char displayedMnemonic) {
        final JLabel label = getLabel(s);
        label.setDisplayedMnemonic(displayedMnemonic);
        return label;
    }
}
