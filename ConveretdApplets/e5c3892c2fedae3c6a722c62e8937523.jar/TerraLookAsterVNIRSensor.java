import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class TerraLookAsterVNIRSensor extends AsterSensor
{
    TerraLookAsterVNIRSensor(final imgViewer imgViewer) {
        super(imgViewer, "TL ASTER (2000->)", "aster/vnir", "TERRA_ASTER", "TERRA_ASTER", "http://terralook.cr.usgs.gov", Color.YELLOW);
        this.navModel = new WRS2Model();
        this.isOrderable = true;
        this.cgiDatasetName = "ASTER_VNIR";
    }
    
    @Override
    public boolean confirmInitialDisplay() {
        return true;
    }
}
