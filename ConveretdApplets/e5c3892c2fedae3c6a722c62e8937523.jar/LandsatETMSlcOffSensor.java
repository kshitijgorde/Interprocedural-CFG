import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class LandsatETMSlcOffSensor extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    LandsatETMSlcOffSensor(final imgViewer imgViewer) {
        super(imgViewer, "L7 SLC-off (2003->)", "l7slc_off", "LANDSAT_ETM_SLC_OFF", "showbrowse.cgi", "showmetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/ETM", "http://landsat.usgs.gov/technical_details/data_acquisition/l7_acquisition_calendar.php", LandsatETMSlcOffSensor.resolutions, LandsatETMSlcOffSensor.borderX, LandsatETMSlcOffSensor.borderY, Color.YELLOW);
        this.numQualityValues = 2;
        this.qualityLimit = 9;
        this.dataHasGaps = true;
        this.isDownloadable = true;
        this.mightBeDownloadable = true;
        this.maxScenesPerOrder = 100;
        this.navModel = new WRS2Model();
    }
    
    @Override
    public int getStartingYear() {
        return 2003;
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
    
    @Override
    public String buildDownloadURL(final Metadata metadata) {
        if (this.isDownloadable) {
            final Vector<String> vector = new Vector<String>();
            vector.add(metadata.entityID);
            return EarthExplorer.buildShoppingCartUrl("LANDSAT_ETM_SLC_OFF_L1T", vector);
        }
        return null;
    }
    
    static {
        LandsatETMSlcOffSensor.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
