import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class NhapDataset extends Sensor
{
    private static final int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    private static final Color borderColor;
    
    NhapDataset(final imgViewer imgViewer, final String s, final String s2, final int n) {
        super(imgViewer, s, s2, "NHAP", "showNhapBrowse.cgi", "showNhapMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/NHAP", null, "Enter a Full NHAP scene ID", NhapDataset.resolutions, NhapDataset.borderX, NhapDataset.borderY, NhapDataset.borderColor);
        this.isOrderable = true;
        this.hasCloudCover = false;
        this.hasProjectName = true;
        this.hasCustomSceneInfoLine = true;
        this.hasSecondaryIDMetadata = true;
        this.hideGridEntry = true;
        this.isDownloadable = true;
        this.slowDownloadStart = true;
        this.downloadFileFormat = "TIFF";
        this.useCloudCoverForDefaultScenes = false;
        this.defaultProjectionCode = 1011;
        this.locatorMap = 1;
        this.navModel = new NhapNavModel(n);
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/y");
        sb.append(metadata.year);
        sb.append("/");
        sb.append(metadata.entityID);
        if (n != NhapDataset.resolutions[1]) {
            sb.append("_lowRes");
        }
        sb.append(".jpg");
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
    public double getOffsetResolution() {
        return this.getActualResolution(NhapDataset.resolutions[NhapDataset.resolutions.length - 1]);
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == NhapDataset.resolutions[0]) {
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
        return new Dimension(12000, 12000);
    }
    
    @Override
    public int getStartingYear() {
        return 1980;
    }
    
    @Override
    public int getEndingYear() {
        return 1989;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == NhapDataset.resolutions[0]) {
            return 7600;
        }
        return 53000;
    }
    
    @Override
    public String getCustomSceneInfo(final Metadata metadata) {
        if (metadata == null) {
            return "Roll:            Frame:\nDate:                      Proj:";
        }
        final int length = metadata.entityID.length();
        return "Roll: " + metadata.entityID.substring(length - 7, length - 3) + "  Frame: " + metadata.entityID.substring(length - 3, length) + "\nDate: " + metadata.getDateString() + " Proj: " + metadata.projectName;
    }
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        final String string = this.getCellDirectory(metadata.gridCol, metadata.gridRow) + "/y" + metadata.year + "/" + metadata.entityID;
        array[0] = string + ".meta";
        array[1] = metadata.entityID + ".meta";
        array[2] = string + "_unclipped.jpg";
        array[3] = metadata.entityID + ".jpg";
        return array;
    }
    
    @Override
    public String getResolutionString(final int n) {
        if (n == 0) {
            return "Low";
        }
        return "High";
    }
    
    static {
        resolutions = new int[] { 90, 30 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
        borderColor = Color.YELLOW;
    }
}
