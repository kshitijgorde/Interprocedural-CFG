// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.applet.Applet;
import java.awt.Image;

public class CPickableObject extends CMovingShape
{
    private int visibleAsOfLap;
    public int type;
    public static final int FUEL_CANISTER = 0;
    public static final int TIRES = 1;
    public static final int CLOCK = 2;
    public static final int BOOST = 3;
    public static final int MANHOLE = 4;
    
    public void CPickableObject() {
    }
    
    public boolean isVisible(final int yPos, final int ySize, final int totalLength, final int inLap) {
        boolean rval = false;
        if (inLap >= this.visibleAsOfLap) {
            rval = true;
        }
        rval = (rval && super.isVisible(yPos, ySize, totalLength));
        return rval;
    }
    
    public boolean isActive(final int inLap) {
        return inLap >= this.visibleAsOfLap && super.isActive();
    }
    
    public void bringToBounds(final CRaceTrack raceTrack) {
        int newX = 0;
        final CCollisionCode ccode = raceTrack.bringToBounds(this);
        newX = ccode.xShift;
        if (newX != 0) {
            this.setNewXPos(newX);
        }
    }
    
    public void init(final int ixPosition, final int itrackPosition, final Image icanisterImage, final Applet a, final int ivisibleAsOfLap, final int itype) {
        this.setImage(icanisterImage, a);
        this.setCoordinates(ixPosition, 0, itrackPosition);
        this.visibleAsOfLap = ivisibleAsOfLap;
        this.type = itype;
    }
}
