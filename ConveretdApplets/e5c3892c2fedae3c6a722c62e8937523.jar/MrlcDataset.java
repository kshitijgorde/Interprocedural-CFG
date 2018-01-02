import java.awt.Color;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class MrlcDataset extends LandsatSensor
{
    private DecimalFormat threeDigitFormat;
    private DecimalFormat fourDigitFormat;
    
    MrlcDataset(final imgViewer imgViewer, final String s, final String s2, final String cgiDatasetName, final String s3, final int[] array, final int[] array2, final int[] array3, final Color color) {
        super(imgViewer, s, s2, cgiDatasetName, "showMrlcBrowse.cgi", "showMrlcMetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", s3, null, array, array2, array3, color);
        this.cgiDatasetName = cgiDatasetName;
        this.threeDigitFormat = new DecimalFormat("000");
        this.fourDigitFormat = new DecimalFormat("0000");
        this.hasJulianDateMetadata = false;
        this.isOrderable = false;
        this.hasColRowInSceneID = true;
        this.hasSwathMode = false;
        this.hasNdviLineGraph = true;
        this.warnWhenOrderingPoorQuality = false;
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
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        array[0] = this.makeImageName(metadata, this.resolutions[this.resolutions.length - 1]);
        array[0] = array[0].substring(0, array[0].lastIndexOf(46));
        final StringBuilder sb = new StringBuilder();
        final String[] array2 = array;
        final int n = 0;
        array2[n] = sb.append(array2[n]).append(".meta").toString();
        array[1] = metadata.entityID + ".meta";
        array[2] = null;
        array[3] = metadata.entityID + ".jpg";
        return array;
    }
}
