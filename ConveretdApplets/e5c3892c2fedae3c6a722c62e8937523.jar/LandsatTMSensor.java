import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class LandsatTMSensor extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    LandsatTMSensor(final imgViewer imgViewer) {
        super(imgViewer, "Landsat 4-5 TM", "l5", "LANDSAT_TM", "showbrowse.cgi", "showmetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/TM", "http://landsat.usgs.gov/technical_details/data_acquisition/l5_acquisition_calendar.php", LandsatTMSensor.resolutions, LandsatTMSensor.borderX, LandsatTMSensor.borderY, Color.YELLOW);
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
        return -1;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    static {
        LandsatTMSensor.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
