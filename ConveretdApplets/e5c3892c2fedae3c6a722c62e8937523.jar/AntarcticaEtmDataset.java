import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AntarcticaEtmDataset extends Sensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    AntarcticaEtmDataset(final imgViewer imgViewer) {
        super(imgViewer, "LIMA Antarctica ETM+", "lima", "LIMA", "showLandsatL1Browse.cgi", "showLandsatL1Metadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://lima.usgs.gov", null, "Enter an 18 digit id", AntarcticaEtmDataset.resolutions, AntarcticaEtmDataset.borderX, AntarcticaEtmDataset.borderY, Color.YELLOW);
        this.navModel = new PolarNavModel();
        this.defaultProjectionCode = 1012;
        this.hasJulianDateMetadata = false;
        this.hasSwathMode = false;
        this.warnWhenOrderingPoorQuality = false;
        this.qualityLimit = 0;
        this.hasNdviLineGraph = false;
        this.isDownloadable = true;
        this.hasCloudCover = false;
        this.useCloudCoverForDefaultScenes = false;
        this.dataHasGaps = false;
        this.downloadFileFormat = "GeoTIFF";
        this.locatorMap = 3;
        this.isOrderable = false;
        this.slowDownloadStart = true;
        this.isFullMosaic = true;
        this.cgiDatasetName = "LIMA";
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/y");
        sb.append(metadata.year);
        sb.append("/");
        sb.append(metadata.entityID);
        sb.append("_");
        sb.append("" + n);
        if (n != 1000) {
            sb.append(".jpg");
        }
        else {
            sb.append(".gif");
        }
        return sb.toString();
    }
    
    @Override
    public String getCellDirectory(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer(this.getSensorDirectory());
        sb.append("/");
        sb.append(this.navModel.getColumnString(n));
        sb.append("/");
        sb.append(this.navModel.getRowString(n2));
        return sb.toString();
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == AntarcticaEtmDataset.resolutions[0]) {
            return this.applet.md.getMosaicSize();
        }
        return 0;
    }
    
    @Override
    public SceneFilter getSceneFilter(final MosaicData mosaicData, final TOC[] array) {
        return new SwathSceneFilter(mosaicData, array, this, this.applet, false);
    }
    
    @Override
    public Dimension getNominalSceneSize() {
        return new Dimension(180000, 180000);
    }
    
    @Override
    public int getStartingYear() {
        return 1999;
    }
    
    @Override
    public int getEndingYear() {
        return 2006;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    static {
        AntarcticaEtmDataset.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
