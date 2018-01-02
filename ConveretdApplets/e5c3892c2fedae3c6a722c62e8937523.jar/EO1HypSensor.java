import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class EO1HypSensor extends EO1Sensor
{
    private static int[] resolutions;
    
    EO1HypSensor(final imgViewer imgViewer) {
        super(imgViewer, "EO-1 Hyperion", "eo1/hyp", "EO1_HYP_PUB", EO1HypSensor.resolutions);
    }
    
    @Override
    public Dimension getNominalSceneSize() {
        return new Dimension(7700, 42000);
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 10000;
        }
        return 60000;
    }
    
    static {
        EO1HypSensor.resolutions = new int[] { 1000, 120 };
    }
}
