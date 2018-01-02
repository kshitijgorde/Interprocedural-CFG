import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class SystematicL1GDataset extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    SystematicL1GDataset(final imgViewer imgViewer) {
        super(imgViewer, "Systematic L1G", "lsat_sys", "SYS_ETM", "showLandsatL1Browse.cgi", "showLandsatL1Metadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/Tri-Decadal_Global_Landsat_Orthorectified_Overview", null, SystematicL1GDataset.resolutions, SystematicL1GDataset.borderX, SystematicL1GDataset.borderY, Color.YELLOW);
        this.navModel = new WRS2Model();
        this.hasJulianDateMetadata = false;
        this.hasSwathMode = false;
        this.warnWhenOrderingPoorQuality = false;
        this.qualityLimit = 0;
        this.hasNdviLineGraph = true;
        this.isOrderable = false;
        this.isDownloadable = true;
        this.hasCloudCover = false;
        this.useCloudCoverForDefaultScenes = false;
        this.dataHasGaps = false;
        this.downloadFileFormat = "GeoTIFF";
        this.slowDownloadStart = true;
        this.sceneIdHint = "Enter a 21 character scene ID";
        this.cgiDatasetName = "SYSTEMATIC_L1G";
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
        SystematicL1GDataset.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
