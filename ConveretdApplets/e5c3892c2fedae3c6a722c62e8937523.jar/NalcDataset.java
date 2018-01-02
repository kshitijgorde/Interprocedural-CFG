import java.awt.Dimension;
import java.awt.Color;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class NalcDataset extends Sensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    private DecimalFormat threeDigitFormat;
    
    NalcDataset(final imgViewer imgViewer) {
        super(imgViewer, "NALC Triplicates", "nalc", "NALC", "showNalcBrowse.cgi", "showNalcMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/NALC", null, "Enter a 13 digit scene ID", NalcDataset.resolutions, NalcDataset.borderX, NalcDataset.borderY, Color.YELLOW);
        this.threeDigitFormat = new DecimalFormat("000");
        this.isOrderable = false;
        this.hasCloudCover = false;
        this.hasColRowInSceneID = true;
        this.hasAcqDate = false;
        this.isDownloadable = true;
        this.slowDownloadStart = true;
        this.downloadFileFormat = "compressed raw imagery";
        this.navModel = new WRS2Model();
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
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
    public int getNumCellsAtResolution(final int n) {
        if (n == NalcDataset.resolutions[0]) {
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
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        array[0] = this.makeImageName(metadata, NalcDataset.resolutions[NalcDataset.resolutions.length - 1]);
        array[0] = array[0].substring(0, array[0].lastIndexOf(47) + 1);
        final StringBuilder sb = new StringBuilder();
        final String[] array2 = array;
        final int n = 0;
        array2[n] = sb.append(array2[n]).append(metadata.entityID).toString();
        final StringBuilder sb2 = new StringBuilder();
        final String[] array3 = array;
        final int n2 = 0;
        array3[n2] = sb2.append(array3[n2]).append(".meta").toString();
        array[1] = metadata.entityID + ".meta";
        array[2] = null;
        array[3] = metadata.entityID + ".jpg";
        return array;
    }
    
    @Override
    public int getStartingYear() {
        return 1972;
    }
    
    @Override
    public int getEndingYear() {
        return 1995;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 50000;
    }
    
    static {
        NalcDataset.resolutions = new int[] { 1000, 480 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
