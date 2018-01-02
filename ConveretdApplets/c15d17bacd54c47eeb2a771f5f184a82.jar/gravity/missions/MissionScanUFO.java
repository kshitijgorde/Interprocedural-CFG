// 
// Decompiled by Procyon v0.5.30
// 

package gravity.missions;

public class MissionScanUFO extends Mission
{
    public MissionScanUFO() {
        super._needUFO = true;
        super._scanTarget = true;
        super._description = "Scan the alien ship";
    }
    
    public String getIncomingData(final int n, final int n2, final boolean b) {
        if (b) {
            return new String("Receiving now data about the alien ship...");
        }
        return new String();
    }
    
    public String getSuccessMessage(final int n, final int n2, final int n3) {
        String string = new String("You successfully scanned the alien ship - using ");
        String s;
        if (n == 1) {
            s = string + "just 1 probe!";
        }
        else {
            if (n < 6) {
                string += "only ";
            }
            s = string + n + " probes!";
        }
        return s;
    }
}
