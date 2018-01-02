import java.net.URL;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AsterSensor extends Sensor
{
    private static final int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    protected String entityIdPrefix;
    protected boolean displayConfirmed;
    
    AsterSensor(final imgViewer imgViewer, final String s, final String s2, final String s3, final String cgiDatasetName, final String s4, final Color color) {
        super(imgViewer, s, s2, s3, "showAsterBrowse.cgi", "showAsterMetadata.cgi", "NASA_small.gif", "http://lpdaac.usgs.gov/lpdaac/products", s4, null, "Enter the last 10 digits of the granule ID", AsterSensor.resolutions, AsterSensor.borderX, AsterSensor.borderY, color);
        this.displayConfirmed = false;
        this.cgiDatasetName = cgiDatasetName;
        this.entityIdPrefix = new String("AST_L1A.");
        this.isFullMosaic = true;
        this.isOrderable = true;
        this.displaySceneCenterLatLong = true;
        this.allowAddAll = false;
        this.hasSwathMode = true;
        this.hasUserDefinedArea = true;
        this.hasGridColRowFilter = true;
        this.logoLocation = 2;
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/y");
        sb.append(metadata.year);
        sb.append("/");
        sb.append(metadata.entityID.substring(metadata.entityID.indexOf(58) + 1));
        sb.append("_");
        sb.append(Integer.toString(n));
        if (n == 155) {
            sb.append(".jpg");
        }
        else {
            sb.append(".gif");
        }
        return sb.toString();
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == AsterSensor.resolutions[0]) {
            return this.applet.md.getMosaicSize();
        }
        if (n == AsterSensor.resolutions[1]) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public SceneFilter getSceneFilter(final MosaicData mosaicData, final TOC[] array) {
        return new SwathSceneFilter(mosaicData, array, this, this.applet, true);
    }
    
    @Override
    public String buildOrderURL(final Metadata[] array) {
        final Vector<String> vector = new Vector<String>();
        final String s = new String();
        if (array.length < 1) {
            return null;
        }
        for (int i = 0; i < array.length; ++i) {
            vector.add(array[i].entityID.substring(4));
        }
        return EarthExplorer.buildShoppingCartUrl(this.datasetName, vector);
    }
    
    @Override
    public Dimension getNominalSceneSize() {
        return new Dimension(60000, 60000);
    }
    
    @Override
    public int getStartingYear() {
        return 2000;
    }
    
    @Override
    public int getEndingYear() {
        return -1;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 6000;
        }
        if (n == 400) {
            return 25000;
        }
        return 50000;
    }
    
    @Override
    String buildEntityID(final Metadata metadata) {
        return this.entityIdPrefix + metadata.entityID;
    }
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        final String substring = metadata.entityID.substring(metadata.entityID.indexOf(58) + 1);
        final String string = this.getCellDirectory(metadata.gridCol, metadata.gridRow) + "/y" + metadata.year + "/" + substring;
        array[0] = string + ".meta";
        array[1] = substring + ".meta";
        array[2] = string + ".jpg";
        array[3] = substring + ".jpg";
        return array;
    }
    
    @Override
    public boolean confirmInitialDisplay() {
        boolean b = false;
        if (this.displayConfirmed) {
            return true;
        }
        final Object[] array = { "Continue in GloVis", "Go to WIST", "View ASTER Policies" };
        final String s = "ASTER Downloads Restricted";
        final JOptionPane optionPane = new JOptionPane(new JLabel("<html>NOTICE: The ASTER Data Access policy has changed.\n<br>The ASTER L1A, On-demand and Higher level datasets are\n<br>available for metadata search and browse through GloVis\n<br>or WIST. Users must be approved to submit orders. Please\n<br>select the [View ASTER Policies] option below or contact\n<br>LP DAAC User Services at lpdaac@usgs.gov for additional\n<br>information.</html>"), 1, 1, null, array, array[0]);
        optionPane.createDialog(this.applet.getDialogContainer(), s).setVisible(true);
        final Object value = optionPane.getValue();
        if (value == null) {
            b = false;
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].equals(value)) {
                    if (i == 0) {
                        b = true;
                        this.displayConfirmed = true;
                    }
                    else if (i == 1) {
                        b = false;
                        try {
                            this.applet.getAppletContext().showDocument(new URL("https://wist.echo.nasa.gov/wist-bin/api/ims.cgi"), "wist");
                        }
                        catch (Exception ex) {
                            System.out.println("exception: " + ex);
                        }
                    }
                    else {
                        b = false;
                        try {
                            this.applet.getAppletContext().showDocument(new URL("https://lpdaac.usgs.gov/lpdaac/products/aster_policies"), "aster_policies");
                        }
                        catch (Exception ex2) {
                            System.out.println("exception: " + ex2);
                        }
                    }
                }
            }
        }
        return b;
    }
    
    static {
        resolutions = new int[] { 1000, 400, 155 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
