import java.awt.Dimension;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class LandsatSensor extends Sensor
{
    LandsatSensor(final imgViewer imgViewer, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final int[] array, final int[] array2, final int[] array3, final Color color) {
        super(imgViewer, s, s2, s3, s4, s5, s6, s7, s8, s9, "Enter a 21 digit scene ID", array, array2, array3, color);
        this.hasJulianDateMetadata = true;
        this.isOrderable = true;
        this.hasColRowInSceneID = true;
        this.hasSwathMode = true;
        this.hasNdviLineGraph = true;
        this.warnWhenOrderingPoorQuality = true;
        this.qualityLimit = 8;
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
    public int getNumCellsAtResolution(final int n) {
        if (n == this.resolutions[0]) {
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
        array[0] = this.makeImageName(metadata, this.resolutions[this.resolutions.length - 1]);
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
}
