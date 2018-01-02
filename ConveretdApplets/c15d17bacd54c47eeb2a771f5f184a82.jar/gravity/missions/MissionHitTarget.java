// 
// Decompiled by Procyon v0.5.30
// 

package gravity.missions;

public class MissionHitTarget extends Mission
{
    public MissionHitTarget() {
        super._needTarget = true;
        super._hitTarget = true;
        super._description = "Hit the target";
    }
    
    public String getSuccessMessage(final int n, final int n2, final int n3) {
        final String s = new String("You hit the target using ");
        String s2;
        if (n == 1) {
            s2 = s + "just 1 probe!";
        }
        else {
            s2 = s + n + " probes.";
        }
        if (n < 6) {
            s2 += " Good work...";
        }
        return s2;
    }
}
