// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.awt.List;
import java.text.NumberFormat;
import java.awt.LayoutManager;
import Utilities.WBTotalsGeneric;

public class S1STotals extends WBTotalsGeneric
{
    public S1STotals() {
    }
    
    public S1STotals(final LayoutManager layout) {
        super(layout);
    }
    
    public void analyzeResults(final String sTotWt, final String sCG) {
        double dAftCGLimit = 67.0;
        double dFwdCGLimit = 64.0;
        final List MsgList = this.getMsgList();
        MsgList.removeAll();
        MsgList.addItem("This new one actually works!");
        final double TotalWt = Integer.parseInt(sTotWt);
        final double CG = Double.valueOf(sCG);
        if (TotalWt > 1150.0) {
            MsgList.addItem("Total Weight exceeds Max Gross (1150 lbs.)");
            MsgList.addItem("C.G. computation is irrelevant");
        }
        else if (TotalWt >= 1115.0) {
            dFwdCGLimit = 64.0 + (TotalWt - 1115.0) / 35.0;
            dAftCGLimit = 66.0 + (1150.0 - TotalWt) / 180.0;
        }
        else if (TotalWt >= 970.0) {
            dFwdCGLimit = 64.0;
            dAftCGLimit = 66.0 + (1150.0 - TotalWt) / 180.0;
        }
        else {
            dFwdCGLimit = 64.0;
            dAftCGLimit = 67.0;
        }
        final NumberFormat integerFormatter = NumberFormat.getNumberInstance();
        integerFormatter.setMaximumFractionDigits(2);
        integerFormatter.setMinimumFractionDigits(2);
        final String sFwdCGLimitDisplay = integerFormatter.format(dFwdCGLimit);
        final String sAftCGLimitDisplay = integerFormatter.format(dAftCGLimit);
        if (CG > 0.0) {
            if (CG < dFwdCGLimit) {
                MsgList.addItem("C.G.is too far forward (max fwd C.G. @" + sTotWt + " is " + sFwdCGLimitDisplay + ")");
            }
            else if (CG > dAftCGLimit) {
                MsgList.addItem("C.G.is too far aft (max aft C.G. @" + sTotWt + " is " + sAftCGLimitDisplay + ")");
            }
            else if (TotalWt <= 1150.0) {
                MsgList.addItem("Results are within the Weight and Balance envelope");
            }
        }
    }
}
