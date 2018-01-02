import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AsterTIRSensor extends AsterSensor
{
    AsterTIRSensor(final imgViewer imgViewer) {
        super(imgViewer, "L1A Night (SWIR/TIR)", "aster/tir", "LPDAAC_ASTER", "ASTER_TIR", "http://lpdaac.usgs.gov/lpdaac/products", Color.YELLOW);
        this.navModel = new WRS2AscendingModel();
    }
}
