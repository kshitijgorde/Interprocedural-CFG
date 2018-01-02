import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class OrthoPanSharpETMDataset extends Sensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    
    OrthoPanSharpETMDataset(final imgViewer imgViewer) {
        super(imgViewer, "ETM+ Pan (1999-2003)", "ortho/pansharp_etm", "ESAT_ETM_PAN", "showOrthoBrowse.cgi", "showOrthoMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/Tri-Decadal_Global_Landsat_Orthorectified_Overview", null, "Enter a 21 character scene ID", OrthoPanSharpETMDataset.resolutions, OrthoPanSharpETMDataset.borderX, OrthoPanSharpETMDataset.borderY, Color.YELLOW);
        this.navModel = new WRS2Model();
        this.isOrderable = false;
        this.isDownloadable = true;
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
        return 1999;
    }
    
    @Override
    public int getEndingYear() {
        return 2003;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 8500;
        }
        return 147500;
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == OrthoPanSharpETMDataset.resolutions[0]) {
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
        OrthoPanSharpETMDataset.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
