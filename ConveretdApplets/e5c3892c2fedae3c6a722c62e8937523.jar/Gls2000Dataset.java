import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gls2000Dataset extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    Gls2000Dataset(final imgViewer imgViewer) {
        this(imgViewer, "GLS 2000", "GLS2000", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/GLS", false, true);
    }
    
    Gls2000Dataset(final imgViewer imgViewer, final String s, final String s2, final String s3, final boolean isOrderable, final boolean isDownloadable) {
        super(imgViewer, s, "gls/gls2000", s2, "showLandsatL1Browse.cgi", "showLandsatL1Metadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", s3, null, Gls2000Dataset.resolutions, Gls2000Dataset.borderX, Gls2000Dataset.borderY, Color.YELLOW);
        this.navModel = new WRS2Model();
        this.hasJulianDateMetadata = false;
        this.hasSwathMode = false;
        this.warnWhenOrderingPoorQuality = false;
        this.qualityLimit = 0;
        this.hasNdviLineGraph = true;
        this.isOrderable = isOrderable;
        this.isDownloadable = isDownloadable;
        this.hasCloudCover = false;
        this.useCloudCoverForDefaultScenes = false;
        this.dataHasGaps = false;
        this.downloadFileFormat = "GeoTIFF";
        this.slowDownloadStart = true;
        this.sceneIdHint = "Enter a 19 digit scene ID";
        this.cgiDatasetName = "GLS2000";
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
        Gls2000Dataset.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}