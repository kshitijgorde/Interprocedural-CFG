import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Landsat4_5MssSensor extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    Landsat4_5MssSensor(final imgViewer imgViewer) {
        super(imgViewer, "Landsat 4-5 MSS", "l4_5mss", "LANDSAT_MSS", "showbrowse.cgi", "showmetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/MSS", null, Landsat4_5MssSensor.resolutions, Landsat4_5MssSensor.borderX, Landsat4_5MssSensor.borderY, Color.YELLOW);
        this.numQualityValues = 1;
        this.isDownloadable = true;
        this.mightBeDownloadable = true;
        this.maxScenesPerOrder = 100;
        this.navModel = new WRS2Model();
    }
    
    @Override
    public int getStartingYear() {
        return 1982;
    }
    
    @Override
    public int getEndingYear() {
        return 1992;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    static {
        Landsat4_5MssSensor.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
