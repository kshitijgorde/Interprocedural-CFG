import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class LandsatETMSensor extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    LandsatETMSensor(final imgViewer imgViewer) {
        super(imgViewer, "L7 SLC-on (1999-2003)", "l7", "LANDSAT_ETM", "showbrowse.cgi", "showmetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/ETM", "http://landsat.usgs.gov/technical_details/data_acquisition/l7_acquisition_calendar.php", LandsatETMSensor.resolutions, LandsatETMSensor.borderX, LandsatETMSensor.borderY, Color.YELLOW);
        this.numQualityValues = 2;
        this.qualityLimit = 9;
        this.isDownloadable = true;
        this.mightBeDownloadable = true;
        this.maxScenesPerOrder = 100;
        this.navModel = new WRS2Model();
    }
    
    @Override
    public int getStartingYear() {
        return 1999;
    }
    
    @Override
    public int getEndingYear() {
        return 2003;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    static {
        LandsatETMSensor.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
