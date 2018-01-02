import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Landsat1_3MssSensor extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    Landsat1_3MssSensor(final imgViewer imgViewer) {
        super(imgViewer, "Landsat 1-3 MSS", "l1_3mss", "LANDSAT_MSS", "showbrowse.cgi", "showmetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/MSS", null, Landsat1_3MssSensor.resolutions, Landsat1_3MssSensor.borderX, Landsat1_3MssSensor.borderY, Color.YELLOW);
        this.numQualityValues = 1;
        this.hasNdviLineGraph = false;
        this.isDownloadable = true;
        this.mightBeDownloadable = true;
        this.maxScenesPerOrder = 100;
        this.navModel = new WRS1Model();
    }
    
    @Override
    public int getStartingYear() {
        return 1972;
    }
    
    @Override
    public int getEndingYear() {
        return 1983;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    static {
        Landsat1_3MssSensor.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
