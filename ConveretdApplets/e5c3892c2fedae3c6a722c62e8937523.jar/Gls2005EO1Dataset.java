import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gls2005EO1Dataset extends EO1Sensor
{
    private static int[] resolutions;
    
    Gls2005EO1Dataset(final imgViewer imgViewer) {
        super(imgViewer, "GLS 2005 Islands (EO-1)", "gls/gls2005_eo1", "GLS2005_EO1_ISLANDS", "showEO1Browse.cgi", "showLandsatL1Metadata.cgi", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/GLS", "acqSchedule.html", "", Gls2005EO1Dataset.resolutions);
        this.hasJulianDateMetadata = false;
        this.hasSwathMode = false;
        this.warnWhenOrderingPoorQuality = false;
        this.qualityLimit = 0;
        this.hasCustomSceneInfoLine = false;
        this.hasLookAngle = false;
        this.hasNdviLineGraph = true;
        this.hasCloudCover = false;
        this.useCloudCoverForDefaultScenes = false;
        this.dataHasGaps = false;
        this.downloadFileFormat = "GeoTIFF";
        this.slowDownloadStart = true;
        this.cgiDatasetName = "GLS2005_EO1";
    }
    
    @Override
    public int getStartingYear() {
        return 2004;
    }
    
    @Override
    public int getEndingYear() {
        return 2008;
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
        Gls2005EO1Dataset.resolutions = new int[] { 1000, 240 };
    }
}
