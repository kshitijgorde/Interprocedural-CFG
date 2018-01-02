import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ortho1_3MssDataset extends Sensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    Ortho1_3MssDataset(final imgViewer imgViewer, final String s, final String s2, final String s3, final boolean isDownloadable) {
        super(imgViewer, s, "ortho/mss1_3", s2, "showOrthoBrowse.cgi", "showOrthoMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", s3, null, "Enter a 20 character scene ID", Ortho1_3MssDataset.resolutions, Ortho1_3MssDataset.borderX, Ortho1_3MssDataset.borderY, Color.YELLOW);
        this.navModel = new WRS1Model();
        this.isOrderable = false;
        this.isDownloadable = isDownloadable;
        this.slowDownloadStart = true;
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/y");
        sb.append(metadata.year);
        sb.append("/");
        sb.append(metadata.entityID);
        sb.append("_");
        sb.append(n);
        if (n != 1000) {
            sb.append(".jpg");
        }
        else {
            sb.append(".gif");
        }
        return sb.toString();
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
        return 60000;
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == Ortho1_3MssDataset.resolutions[0]) {
            return this.applet.md.getMosaicSize();
        }
        return 0;
    }
    
    @Override
    public SceneFilter getSceneFilter(final MosaicData mosaicData, final TOC[] array) {
        return new LandsatSceneFilter(mosaicData);
    }
    
    @Override
    public Dimension getNominalSceneSize() {
        return new Dimension(180000, 180000);
    }
    
    static {
        Ortho1_3MssDataset.resolutions = new int[] { 1000, 480 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
