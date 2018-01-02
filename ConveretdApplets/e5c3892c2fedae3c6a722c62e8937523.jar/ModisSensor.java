import java.awt.Dimension;
import java.util.Vector;
import java.awt.Point;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Color;
import java.text.DecimalFormat;

// 
// Decompiled by Procyon v0.5.30
// 

public class ModisSensor extends Sensor
{
    private DecimalFormat twoDigitFormat;
    private static final int[] resolutions;
    private static final int[] borderX;
    private static final int[] borderY;
    private static final Color borderColor;
    private LatLongToModisTile tileConverter;
    private static final int[] lineOffsets;
    private static final int[] sampOffsets;
    
    ModisSensor(final imgViewer imgViewer, final String s, final String s2, final String s3, final String cgiDatasetName, final boolean hasCloudCover) {
        super(imgViewer, s, s2, s3, "showModisBrowse.cgi", "showModisMetadata.cgi", "NASA_small.gif", "http://lpdaac.usgs.gov/lpdaac/products", "https://lpdaac.usgs.gov/lpdaac/products/modis_products_table", null, "Enter a MODIS scene ID", ModisSensor.resolutions, ModisSensor.borderX, ModisSensor.borderY, ModisSensor.borderColor);
        this.cgiDatasetName = cgiDatasetName;
        this.twoDigitFormat = new DecimalFormat("00");
        this.isOrderable = true;
        this.hasColRowInSceneID = true;
        this.hasConstantOffsets = true;
        this.hasUpperLeftInToc = false;
        this.hasSecondaryIDMetadata = true;
        this.hasMultipleBrowse = true;
        this.hasCustomSceneInfoLine = true;
        this.hasDataVersions = true;
        this.hasGeographicBumper = false;
        this.hasCloudCover = hasCloudCover;
        this.useCloudCoverForDefaultScenes = false;
        this.defaultProjectionCode = 1010;
        this.locatorMap = 2;
        this.navModel = new ModisTileModel();
        this.tileConverter = new LatLongToModisTile();
        this.dataVersions = this.getDataVersions();
        this.logoLocation = 2;
    }
    
    private String[] getDataVersions() {
        BufferedReader bufferedReader = null;
        final ArrayList<String> list = new ArrayList<String>();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.applet.getCodeBase(), "modis/versions.txt").openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line.trim());
            }
            bufferedReader.close();
            bufferedReader = null;
        }
        catch (Exception ex) {
            list.add("ERROR");
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (Exception ex2) {}
            }
        }
        list.trimToSize();
        final String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            array[i] = list.get(i).toString();
        }
        return array;
    }
    
    @Override
    public void completeMetadata(final Metadata metadata) {
        metadata.lineOffset = ModisSensor.lineOffsets;
        metadata.sampOffset = ModisSensor.sampOffsets;
        final int index = metadata.secondaryID.indexOf(46, metadata.secondaryID.indexOf(46, metadata.secondaryID.indexOf(46) + 1) + 1);
        metadata.dataVersion = metadata.secondaryID.substring(index + 1, metadata.secondaryID.indexOf(46, index + 2));
        metadata.entityID = "." + metadata.dataVersion + ":" + metadata.entityID;
        final Point tileToCoordinate = this.tileConverter.tileToCoordinate(new ModisTile(metadata.gridCol, metadata.gridRow), false);
        metadata.ulX = tileToCoordinate.x;
        metadata.ulY = tileToCoordinate.y;
    }
    
    @Override
    public String makeImageName(final Metadata metadata, final int n) {
        final StringBuffer sb = new StringBuffer(this.getCellDirectory(metadata.gridCol, metadata.gridRow));
        sb.append("/y");
        sb.append(metadata.year);
        sb.append("/");
        if (n != 5000) {
            sb.append(metadata.secondaryID.substring(0, metadata.secondaryID.lastIndexOf(46, metadata.secondaryID.lastIndexOf(46) - 1)));
            if (metadata.browseNumber > 0) {
                sb.append("_");
                sb.append(metadata.browseNumber);
            }
            sb.append(".");
            sb.append(n / 1000);
            sb.append("km.jpg");
        }
        else {
            sb.append(metadata.secondaryID.substring(0, metadata.secondaryID.lastIndexOf(46)));
            if (metadata.browseNumber > 0) {
                sb.append("_");
                sb.append(metadata.browseNumber);
            }
            sb.append(".jpg");
        }
        return sb.toString();
    }
    
    @Override
    public String getCellDirectory(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer(this.getSensorDirectory());
        sb.append("/h");
        sb.append(this.twoDigitFormat.format(n));
        sb.append("/v");
        sb.append(this.twoDigitFormat.format(n2));
        return sb.toString();
    }
    
    @Override
    public double getOffsetResolution() {
        return this.getActualResolution(ModisSensor.resolutions[ModisSensor.resolutions.length - 1]);
    }
    
    @Override
    public double getActualResolution(final int n) {
        return n * 926.625433 / 1000.0;
    }
    
    @Override
    public int getNumCellsAtResolution(final int n) {
        if (n == ModisSensor.resolutions[0]) {
            return this.applet.md.getMosaicSize();
        }
        return 0;
    }
    
    @Override
    public SceneFilter getSceneFilter(final MosaicData mosaicData, final TOC[] array) {
        return new ModisSceneFilter(mosaicData);
    }
    
    @Override
    public String buildOrderURL(final Metadata[] array) {
        final Vector<String> vector = new Vector<String>();
        final String s = new String();
        if (array.length < 1) {
            return null;
        }
        for (int i = 0; i < array.length; ++i) {
            vector.add(array[i].entityID.substring(5));
        }
        return EarthExplorer.buildShoppingCartUrl(this.datasetName, vector);
    }
    
    @Override
    public Dimension getNominalSceneSize() {
        return new Dimension(1111951, 1111951);
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
        if (n == 10000) {
            return 3500;
        }
        if (n == 5000) {
            return 55000;
        }
        return 1500;
    }
    
    @Override
    public String getCustomSceneInfo(final Metadata metadata) {
        if (this.hasCloudCover) {
            if (metadata == null) {
                return "Granule ID:      #";
            }
            return "Granule ID: " + metadata.entityID.substring(5) + "  # " + metadata.browseNumber;
        }
        else {
            if (metadata == null) {
                return "Date:\nGranule ID:      #";
            }
            return "Date: " + metadata.getDateString() + "\nGranule ID: " + metadata.entityID.substring(5) + "  # " + metadata.browseNumber;
        }
    }
    
    @Override
    String buildMediumEntityID(final Metadata metadata) {
        return metadata.secondaryID.substring(0, metadata.secondaryID.lastIndexOf(46, metadata.secondaryID.lastIndexOf(46) - 1));
    }
    
    @Override
    String buildShortEntityID(final Metadata metadata) {
        return metadata.secondaryID.substring(metadata.secondaryID.indexOf(46) + 1, metadata.secondaryID.lastIndexOf(46, metadata.secondaryID.lastIndexOf(46) - 1));
    }
    
    @Override
    String buildEntityID(final Metadata metadata) {
        return metadata.secondaryID;
    }
    
    @Override
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        final StringBuffer sb = new StringBuffer(metadata.secondaryID.substring(0, metadata.secondaryID.lastIndexOf(46)));
        if (metadata.browseNumber > 0) {
            sb.append('_');
            sb.append(metadata.browseNumber);
        }
        final String string = this.getCellDirectory(metadata.gridCol, metadata.gridRow) + "/y" + metadata.year + "/" + sb.toString();
        array[0] = string + ".meta";
        array[1] = sb.toString() + ".meta";
        array[2] = string + ".jpg";
        array[3] = sb.toString() + ".jpg";
        return array;
    }
    
    static {
        resolutions = new int[] { 10000, 5000 };
        borderX = new int[] { -4, 4, 4, -4 };
        borderY = new int[] { -4, -4, 4, 4 };
        borderColor = Color.YELLOW;
        lineOffsets = new int[] { 0, 0, 239, 239 };
        sampOffsets = new int[] { 0, 239, 239, 0 };
    }
}
