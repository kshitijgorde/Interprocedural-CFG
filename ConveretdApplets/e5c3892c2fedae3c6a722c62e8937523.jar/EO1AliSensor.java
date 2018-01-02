import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class EO1AliSensor extends EO1Sensor
{
    private static int[] resolutions;
    
    EO1AliSensor(final imgViewer imgViewer) {
        super(imgViewer, "EO-1 ALI", "eo1/ali", "EO1_ALI_PUB", EO1AliSensor.resolutions);
    }
    
    @Override
    public Dimension getNominalSceneSize() {
        return new Dimension(37000, 42000);
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 10000;
        }
        return 60000;
    }
    
    static {
        EO1AliSensor.resolutions = new int[] { 1000, 240 };
    }
}
