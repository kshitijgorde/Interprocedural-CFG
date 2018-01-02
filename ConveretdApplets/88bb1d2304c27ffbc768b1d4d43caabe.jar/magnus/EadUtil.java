// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.GridBagConstraints;

public class EadUtil
{
    public static String printNumber(final double n, final int n2) {
        final String string = Double.toString(n);
        final int n3 = n2 + 1;
        if (n > 0.001 && n < 9999999.0) {
            final int index = string.indexOf(".");
            int length;
            if (string.length() > n3 + index) {
                length = n3 + index;
            }
            else {
                length = string.length();
            }
            return string.substring(0, length);
        }
        return string;
    }
    
    static GridBagConstraints a(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        return gridBagConstraints;
    }
}
