import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gls1975Mss1_3Dataset extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    Gls1975Mss1_3Dataset(final imgViewer imgViewer) {
        this(imgViewer, "GLS 1975 (Landsat 1-3)", "GLS1975", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/GLS", false, true);
    }
    
    Gls1975Mss1_3Dataset(final imgViewer imgViewer, final String s, final String s2, final String s3, final boolean isOrderable, final boolean isDownloadable) {
        super(imgViewer, s, "gls/gls1975_mss1_3", s2, "showLandsatL1Browse.cgi", "showLandsatL1Metadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", s3, null, Gls1975Mss1_3Dataset.resolutions, Gls1975Mss1_3Dataset.borderX, Gls1975Mss1_3Dataset.borderY, Color.YELLOW);
        this.navModel = new WRS1Model();
        this.hasJulianDateMetadata = false;
        this.hasSwathMode = false;
        this.warnWhenOrderingPoorQuality = false;
        this.qualityLimit = 0;
        this.isOrderable = isOrderable;
        this.isDownloadable = isDownloadable;
        this.hasCloudCover = false;
        this.useCloudCoverForDefaultScenes = false;
        this.dataHasGaps = false;
        this.downloadFileFormat = "GeoTIFF";
        this.slowDownloadStart = true;
        this.sceneIdHint = "Enter a 19 digit scene ID";
        this.cgiDatasetName = "GLS1975_1_3MSS";
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
        Gls1975Mss1_3Dataset.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
