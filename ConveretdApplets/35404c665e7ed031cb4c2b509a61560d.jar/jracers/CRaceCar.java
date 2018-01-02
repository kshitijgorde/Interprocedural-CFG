// 
// Decompiled by Procyon v0.5.30
// 

package jracers;

import java.applet.Applet;
import java.awt.Image;

public class CRaceCar extends CVehicle
{
    private final int maxSpeed = 100;
    private final int minSpeed = -30;
    private final int accelarationFactor = 5;
    private final int reverseGearDelay = 15;
    private final int slowDownFactor = 5;
    public final int tankSize = 2000;
    private final double fuelBurnRate = 0.01;
    private final boolean computerCar = false;
    private final double frictionFactor = 0.98;
    private final double snowAccelarationFactor = 0.8;
    private final double snowDriftFactor = 0.5;
    private final double iceFrictionFactor = 0.99;
    public final int maxTireStatus = 100;
    
    public void init(final int ispeed, final int ixPosition, final int iyPosition, final CRaceTrack rt, final Image icarImage, final Applet a, final CGraphicsConstants gfxElements) {
        this.setCharacteristics(100, -30, 5, 15, 5, 2000, 0.01, false, 0.98, 0.8, 0.5, 0.99, 100);
        super.init(ispeed, ixPosition, iyPosition, rt, icarImage, a, 100, gfxElements);
    }
    
    public void autoPilot() {
    }
}
