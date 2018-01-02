// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import blackmagic.swing.JFormattedDateField;
import blackmagic.swing.JFormattedPercentField;
import blackmagic.swing.JFormattedNumField;
import javax.swing.JComponent;
import blackmagic.swing.ComponentLabelPair;

public class FieldLabelFactory
{
    public static final int DOLLAR = 0;
    public static final int SHARE_UNITS = 1;
    public static final int PRICE_PER_SHARE = 2;
    public static final int PERCENTAGE = 3;
    public static final int LONG_PERCENTAGE = 4;
    public static final int DATE = 5;
    public static final int MULTIPLIER = 6;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$FieldLabelFactory;
    
    public static ComponentLabelPair getPair(final int n, final char displayedMnemonic, final String s) {
        final ComponentLabelPair pair = getPair(n, s);
        pair.getLabel().setDisplayedMnemonic(displayedMnemonic);
        return pair;
    }
    
    public static ComponentLabelPair getPair(final int n, final String s) {
        return new ComponentLabelPair(getField(n), LabelFactory.getLabel(s));
    }
    
    private static JComponent getField(final int n) {
        switch (n) {
            case 0: {
                return new JFormattedNumField("###,###,##0.00", 0.0, 12);
            }
            case 1: {
                return new JFormattedNumField("###,###,##0", 0.0, 10);
            }
            case 2: {
                return new JFormattedNumField("###,##0.0000", 0.0, 8);
            }
            case 3: {
                return new JFormattedPercentField("###,##0.00%", 0.0, 8);
            }
            case 4: {
                return new JFormattedPercentField("###,##0.00####%", 0.0, 10);
            }
            case 5: {
                return new JFormattedDateField("dd/MM/yyyy", 12);
            }
            case 6: {
                return new JFormattedNumField("###,###,##0.00", 0.0, 6);
            }
            default: {
                assert false : "Illegal Field type passed to factory";
                return null;
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        $assertionsDisabled = !((FieldLabelFactory.class$blackmagic$finance$investment$calculators$foundations$FieldLabelFactory == null) ? (FieldLabelFactory.class$blackmagic$finance$investment$calculators$foundations$FieldLabelFactory = class$("blackmagic.finance.investment.calculators.foundations.FieldLabelFactory")) : FieldLabelFactory.class$blackmagic$finance$investment$calculators$foundations$FieldLabelFactory).desiredAssertionStatus();
    }
}
