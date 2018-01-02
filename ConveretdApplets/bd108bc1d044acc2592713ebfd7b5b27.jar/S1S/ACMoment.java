// 
// Decompiled by Procyon v0.5.30
// 

package S1S;

import java.text.NumberFormat;

public class ACMoment
{
    int iWeight;
    double dArm;
    String sLocDesc;
    double dMoment;
    
    public String formatDouble(final double dInput, final int idecplaces) {
        final NumberFormat integerFormatter = NumberFormat.getNumberInstance();
        integerFormatter.setMaximumFractionDigits(idecplaces);
        integerFormatter.setMinimumFractionDigits(idecplaces);
        final String sX = integerFormatter.format(dInput);
        return sX;
    }
    
    public double getDblFromString(String sArg) throws NumberFormatException {
        if (sArg.length() == 0) {
            sArg = "0";
        }
        return Double.valueOf(sArg);
    }
    
    public int getIntFromString(String sArg) throws NumberFormatException {
        if (sArg.length() == 0) {
            sArg = "0";
        }
        return Integer.parseInt(sArg);
    }
    
    public double getMoment(final String sArm) {
        return this.iWeight * Double.valueOf(sArm);
    }
    
    public double getMoment(final String sWt, final String sArm) {
        final int iWt = this.getIntFromString(sWt);
        final double dArm = this.getDblFromString(sArm);
        return iWt * dArm;
    }
    
    public String getMomentString(final String sWt, final String sArm) {
        final int iWt = this.getIntFromString(sWt);
        final double dArm = this.getDblFromString(sArm);
        return this.formatDouble(iWt * dArm, 2);
    }
    
    public void setArm(final String sArm) {
        this.dArm = Double.valueOf(sArm);
    }
    
    public void setWeight(final String sWt) {
        this.iWeight = Integer.parseInt(sWt);
    }
    
    public ACMoment() {
        this.dMoment = 0.0;
    }
}
