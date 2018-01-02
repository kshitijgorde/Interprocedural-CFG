import java.awt.Color;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class EO1Sensor extends Sensor
{
    private DecimalFormat fourDigitFormat;
    private static final int[] borderX;
    private static final int[] borderY;
    
    EO1Sensor(final imgViewer imgViewer, final String s, final String s2, final String s3, final int[] array) {
        super(imgViewer, s, s2, s3, "showEO1Browse.cgi", "showEO1Metadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eo1.usgs.gov/products", "http://eo1.usgs.gov/acquisition", "Enter at least the first 22 characters of a scene ID", array, EO1Sensor.borderX, EO1Sensor.borderY, Color.YELLOW);
        this.isFullMosaic = true;
        this.fourDigitFormat = new DecimalFormat("0000");
        this.hasJulianDateMetadata = true;
        this.isOrderable = false;
        this.isDownloadable = true;
        this.hasColRowInSceneID = true;
        this.hasCustomSceneInfoLine = true;
        this.allowAddAll = false;
        this.hasLookAngle = true;
        this.hasNdviLineGraph = true;
        this.dataAcqRequestURL = new String("http://eo1.usgs.gov/dar");
        this.useHighlightTransparentPixelFix = false;
        this.navModel = new WRS2Model();
        this.hasUserDefinedArea = true;
    }
    
    EO1Sensor(final imgViewer imgViewer, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, String s8, final int[] array) {
        super(imgViewer, s, s2, s3, s4, s5, "USGS_logo.gif", "http://www.usgs.gov", s6, s7, "Enter at least the first 22 characters of a scene ID", array, EO1Sensor.borderX, EO1Sensor.borderY, Color.YELLOW);
        this.isFullMosaic = true;
        this.fourDigitFormat = new DecimalFormat("0000");
        this.hasJulianDateMetadata = true;
        this.isOrderable = false;
        this.isDownloadable = true;
        this.hasColRowInSceneID = true;
        this.hasCustomSceneInfoLine = true;
        this.allowAddAll = false;
        this.hasLookAngle = true;
        this.hasNdviLineGraph = true;
        s8 = s8;
        this.useHighlightTransparentPixelFix = false;
        this.navModel = new WRS2Model();
        this.hasUserDefinedArea = true;
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/y");
        sb.append(metadata.year);
        sb.append("/");
        sb.append(metadata.entityID);
        sb.append("_");
        sb.append(this.fourDigitFormat.format(n));
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
        if (n == this.resolutions[0]) {
            return this.applet.md.getMosaicSize();
        }
        return 0;
    }
    
    @Override
    public SceneFilter getSceneFilter(final MosaicData mosaicData, final TOC[] array) {
        return new SwathSceneFilter(mosaicData, array, this, this.applet, true);
    }
    
    @Override
    public int getStartingYear() {
        return 1999;
    }
    
    @Override
    public int getEndingYear() {
        return -1;
    }
    
    @Override
    String buildShortEntityID(final Metadata metadata) {
        return metadata.entityID.substring(0, 22);
    }
    
    @Override
    public String getCustomSceneInfo(final Metadata metadata) {
        if (metadata == null) {
            return "Look Angle: ";
        }
        return "Look Angle: " + metadata.lookAngle;
    }
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        array[0] = this.makeImageName(metadata, this.resolutions[this.resolutions.length - 1]);
        array[0] = array[0].substring(0, array[0].lastIndexOf(46));
        final StringBuilder sb = new StringBuilder();
        final String[] array2 = array;
        final int n = 0;
        array2[n] = sb.append(array2[n]).append(".meta").toString();
        array[1] = metadata.entityID + ".meta";
        array[2] = this.getCellDirectory(metadata.gridCol, metadata.gridRow) + "/y" + metadata.year + "/" + metadata.entityID.substring(0, 3) + metadata.entityID.substring(4) + ".jpeg";
        array[3] = metadata.entityID + ".jpeg";
        return array;
    }
    
    static {
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
