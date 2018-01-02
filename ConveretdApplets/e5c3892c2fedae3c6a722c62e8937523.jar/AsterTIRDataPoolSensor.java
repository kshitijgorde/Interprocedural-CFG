import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AsterTIRDataPoolSensor extends AsterSensor
{
    AsterTIRDataPoolSensor(final imgViewer imgViewer) {
        super(imgViewer, "L1B US Night (SWIR/TIR)", "aster_datapool/tir", "LPDAAC_ASTER", "ASTER_TIR_DATAPOOL", "http://lpdaac.usgs.gov/lpdaac/products", Color.YELLOW);
        this.navModel = new WRS2AscendingModel();
        this.entityIdPrefix = new String("AST_L1B.");
    }
    
    @Override
    public boolean confirmInitialDisplay() {
        return true;
    }
}
