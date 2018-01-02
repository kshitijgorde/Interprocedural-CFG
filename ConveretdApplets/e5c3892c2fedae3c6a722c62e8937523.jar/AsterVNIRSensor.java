import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AsterVNIRSensor extends AsterSensor
{
    AsterVNIRSensor(final imgViewer imgViewer) {
        super(imgViewer, "L1A Day (VNIR/SWIR/TIR)", "aster/vnir", "LPDAAC_ASTER", "ASTER_VNIR", "http://lpdaac.usgs.gov/lpdaac/products", Color.YELLOW);
        this.navModel = new WRS2Model();
    }
}
