import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class OrthoTMDataset extends Sensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    OrthoTMDataset(final imgViewer imgViewer, final String s, final String s2, final String s3, final boolean isDownloadable) {
        super(imgViewer, s, "ortho/tm", s2, "showOrthoBrowse.cgi", "showOrthoMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", s3, null, "Enter a 20 character scene ID", OrthoTMDataset.resolutions, OrthoTMDataset.borderX, OrthoTMDataset.borderY, Color.YELLOW);
        this.navModel = new WRS2Model();
        this.isOrderable = false;
        this.isDownloadable = isDownloadable;
        this.hasNdviLineGraph = true;
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
        return 1984;
    }
    
    @Override
    public int getEndingYear() {
        return 1997;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 23050;
        }
        return 37900;
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == OrthoTMDataset.resolutions[0]) {
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
        OrthoTMDataset.resolutions = new int[] { 1000, 480 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
