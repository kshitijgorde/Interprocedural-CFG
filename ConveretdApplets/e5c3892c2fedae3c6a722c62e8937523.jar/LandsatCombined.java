import java.util.Vector;
import java.util.HashMap;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class LandsatCombined extends LandsatSensor
{
    private static int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    private Sensor[] sensors;
    private int startYear;
    
    LandsatCombined(final imgViewer imgViewer, final Sensor[] array) {
        super(imgViewer, "L4-7 Combined", "", "LANDSAT_COMBINED", "showbrowse.cgi", "showmetadata.cgi", "USGS_logo.gif", "http://www.usgs.gov", "http://eros.usgs.gov/#/Find_Data/Products_and_Data_Available/ETM", null, LandsatCombined.resolutions, LandsatCombined.borderX, LandsatCombined.borderY, Color.GRAY);
        this.numQualityValues = 2;
        this.hasCustomSceneInfoLine = true;
        this.hasMultipleDatasets = true;
        this.isDownloadable = true;
        this.mightBeDownloadable = true;
        this.maxScenesPerOrder = 100;
        this.sensors = new Sensor[array.length];
        this.startYear = 10000;
        for (int i = 0; i < array.length; ++i) {
            this.sensors[i] = array[i];
            if (this.sensors[i].getStartingYear() < this.startYear) {
                this.startYear = this.sensors[i].getStartingYear();
            }
        }
        this.navModel = new WRS2Model();
        final SceneList[] array2 = new SceneList[array.length];
        final SceneList[] array3 = new SceneList[array.length];
        for (int j = 0; j < array.length; ++j) {
            array2[j] = array[j].sceneList;
            array3[j] = array[j].hiddenSceneList;
        }
        this.sceneList = new CombinedSceneList(imgViewer, this, array2);
        this.hiddenSceneList = new CombinedSceneList(imgViewer, this, array3);
    }
    
    @Override
    public void readTOC(final TOC toc) {
        toc.read(this.sensors[0]);
        final TOC toc2 = new TOC(this.applet.getCodeBase(), toc.gridCol, toc.gridRow);
        for (int i = 1; i < this.sensors.length; ++i) {
            if (toc.valid) {
                toc2.read(this.sensors[i]);
                toc.add(toc2);
            }
            else {
                toc.read(this.sensors[i]);
            }
        }
    }
    
    @Override
    public Color getBorderColor(final Metadata metadata) {
        final Sensor sensor = metadata.getSensor();
        if (sensor instanceof LandsatETMSensor) {
            return Color.blue;
        }
        if (sensor instanceof LandsatETMSlcOffSensor) {
            return Color.green;
        }
        if (sensor instanceof LandsatTMSensor) {
            return Color.yellow;
        }
        if (sensor instanceof Landsat4_5MssSensor) {
            return Color.red;
        }
        return Color.lightGray;
    }
    
    @Override
    public String getCustomSceneInfo(final Metadata metadata) {
        if (metadata == null) {
            return "Qlty:    Sensor: ";
        }
        String s = "Qlty: " + metadata.getQuality() + "  Sensor: ";
        final Sensor sensor = metadata.getSensor();
        if (sensor instanceof LandsatETMSensor) {
            s += "ETM+";
        }
        else if (sensor instanceof LandsatETMSlcOffSensor) {
            s += "ETM+ SLC-off";
        }
        else if (sensor instanceof LandsatTMSensor) {
            s += "TM";
        }
        else if (sensor instanceof Landsat4_5MssSensor) {
            s += "MSS";
        }
        return s;
    }
    
    @Override
    public int getStartingYear() {
        return this.startYear;
    }
    
    @Override
    public int getEndingYear() {
        return -1;
    }
    
    @Override
    public int getImageFileSize(final int n) {
        if (n == 1000) {
            return 35000;
        }
        return 120000;
    }
    
    @Override
    public String buildOrderURL(final Metadata[] array) {
        final HashMap<Object, Vector<?>> hashMap = new HashMap<Object, Vector<?>>();
        final String s = new String("");
        for (int i = 0; i < array.length; ++i) {
            final Metadata metadata = array[i];
            final Sensor sensor = metadata.getSensor();
            Vector<?> vector;
            if (hashMap.containsKey(sensor.datasetName)) {
                vector = hashMap.get(sensor.datasetName);
            }
            else {
                vector = new Vector<Object>();
            }
            vector.add(metadata.entityID);
            hashMap.put(sensor.datasetName, vector);
        }
        return EarthExplorer.buildShoppingCartMultiDatasetUrl(hashMap);
    }
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        array[0] = metadata.getSensor().makeImageName(metadata, LandsatCombined.resolutions[LandsatCombined.resolutions.length - 1]);
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
    public String makeImageName(final Metadata metadata, final int n) {
        return metadata.getSensor().makeImageName(metadata, n);
    }
    
    @Override
    public Metadata createMetadata(final String s, final int n, final int n2) {
        for (int i = 0; i < this.sensors.length; ++i) {
            if (s.startsWith(this.sensors[i].datasetName)) {
                return new Metadata(s.substring(s.indexOf(44) + 1), this.sensors[i], n, n2);
            }
        }
        return null;
    }
    
    @Override
    public Sensor[] getSensorList() {
        return this.sensors;
    }
    
    @Override
    public String buildDownloadURL(final Metadata metadata) {
        return metadata.getSensor().buildDownloadURL(metadata);
    }
    
    static {
        LandsatCombined.resolutions = new int[] { 1000, 240 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
    }
}
