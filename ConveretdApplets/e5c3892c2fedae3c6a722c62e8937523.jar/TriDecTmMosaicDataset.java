import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class TriDecTmMosaicDataset extends Sensor
{
    private static final int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    private static final Color borderColor;
    
    TriDecTmMosaicDataset(final imgViewer imgViewer) {
        super(imgViewer, "TM Mosaics", "ortho/tm_mosaic", "ORTHO_MOSAIC", "showMosaicBrowse.cgi", "showMosaicMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#Find_Data/Products_and_Data_Available/Tri-Decadal_Global_Landsat_Orthorectified_TM_and_ETM_Pan-sharpened_Mosaics", null, "Enter a Full TM Mosaic entity ID", TriDecTmMosaicDataset.resolutions, TriDecTmMosaicDataset.borderX, TriDecTmMosaicDataset.borderY, TriDecTmMosaicDataset.borderColor);
        this.isOrderable = false;
        this.hasCloudCover = false;
        this.hasAcqDate = false;
        this.isDownloadable = true;
        this.slowDownloadStart = true;
        this.hasColRowInSceneID = true;
        this.hideGridEntry = true;
        this.useCloudCoverForDefaultScenes = false;
        this.navModel = new TriDecTmMosaicModel();
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/");
        sb.append(metadata.entityID);
        sb.append("_");
        sb.append("" + n);
        if (n != TriDecTmMosaicDataset.resolutions[0]) {
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
        if (n == TriDecTmMosaicDataset.resolutions[0]) {
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
        return new Dimension(310000, 270000);
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
        if (n == TriDecTmMosaicDataset.resolutions[0]) {
            return 72200;
        }
        return 190000;
    }
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        final String string = this.getCellDirectory(metadata.gridCol, metadata.gridRow) + "/" + metadata.entityID;
        array[0] = string + ".meta";
        array[1] = metadata.entityID + ".meta";
        array[2] = string + ".jpg";
        array[3] = metadata.entityID + ".jpg";
        return array;
    }
    
    static {
        resolutions = new int[] { 2000, 700 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
        borderColor = Color.YELLOW;
    }
}
