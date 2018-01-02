// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

public class CObjectPosition
{
    public int xPosition;
    public int trackPosition;
    public int validAsOfTrack;
    public boolean valid;
    
    public CObjectPosition(final int ixPosition, final int itrackPosition, final int ivalidAsOfTrack) {
        this.xPosition = ixPosition;
        this.trackPosition = itrackPosition;
        this.valid = true;
        this.validAsOfTrack = ivalidAsOfTrack;
    }
}
