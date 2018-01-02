import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AsterVNIRDataPoolSensor extends AsterSensor
{
    AsterVNIRDataPoolSensor(final imgViewer imgViewer) {
        super(imgViewer, "L1B US Day (VNIR/SWIR/TIR)", "aster_datapool/vnir", "LPDAAC_ASTER", "ASTER_VNIR_DATAPOOL", "http://lpdaac.usgs.gov/lpdaac/products", Color.YELLOW);
        this.navModel = new WRS2Model();
        this.entityIdPrefix = new String("AST_L1B.");
    }
    
    @Override
    public boolean confirmInitialDisplay() {
        return true;
    }
}
