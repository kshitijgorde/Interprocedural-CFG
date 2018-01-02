import java.net.URL;
import java.net.URLEncoder;
import java.awt.Dimension;
import java.util.Vector;
import java.text.DecimalFormat;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Sensor
{
    protected imgViewer applet;
    public static final int SINGLE_SCENE = 0;
    public String sensorName;
    private String showBrowseCGI;
    private String showMetadataCGI;
    protected String datasetName;
    protected String cgiDatasetName;
    protected String directory;
    public int[] resolutions;
    public int[] borderX;
    public int[] borderY;
    public int defaultProjectionCode;
    private Color borderColor;
    public boolean hasAcqDate;
    public boolean hasJulianDateMetadata;
    public boolean hasSecondaryIDMetadata;
    public boolean isFullMosaic;
    public boolean isOrderable;
    public int maxScenesPerOrder;
    public boolean isDownloadable;
    public boolean mightBeDownloadable;
    public boolean slowDownloadStart;
    public String downloadFileFormat;
    public boolean hasGeographicBumper;
    public boolean hasColRowInSceneID;
    public boolean displaySceneCenterLatLong;
    public boolean hasConstantOffsets;
    public boolean hasUpperLeftInToc;
    public boolean hasCloudCover;
    public boolean useCloudCoverForDefaultScenes;
    public boolean hasCustomSceneInfoLine;
    public boolean allowAddAll;
    public boolean hasDataVersions;
    public boolean hasMultipleBrowse;
    public boolean hasGridColRowFilter;
    public boolean hasNdviLineGraph;
    public boolean hasSwathMode;
    public boolean hasUserDefinedArea;
    public boolean dataHasGaps;
    public boolean useHighlightTransparentPixelFix;
    public boolean hasLookAngle;
    public boolean hasProjectName;
    public boolean hasMultipleDatasets;
    public boolean hideGridEntry;
    public boolean warnWhenOrderingPoorQuality;
    public int qualityLimit;
    public int numQualityValues;
    public String[] dataVersions;
    public String logoName;
    public String logoLink;
    public int logoLocation;
    public String productInfoURL;
    public String acquisitionScheduleURL;
    public String dataAcqRequestURL;
    public String orderWindowName;
    public String sceneIdHint;
    public NavigationModel navModel;
    public SceneList sceneList;
    public SceneList hiddenSceneList;
    public int locatorMap;
    private DecimalFormat threeDigitFormat;
    public static final int LOGO_LOWER_LEFT = 1;
    public static final int LOGO_LOWER_RIGHT = 2;
    
    Sensor(final imgViewer applet, final String sensorName, final String directory, final String datasetName, final String showBrowseCGI, final String showMetadataCGI, final String logoName, final String logoLink, final String productInfoURL, final String acquisitionScheduleURL, final String sceneIdHint, final int[] resolutions, final int[] borderX, final int[] borderY, final Color borderColor) {
        this.applet = applet;
        this.sensorName = sensorName;
        this.directory = directory;
        this.datasetName = datasetName;
        this.showBrowseCGI = showBrowseCGI;
        this.showMetadataCGI = showMetadataCGI;
        this.logoName = logoName;
        this.logoLink = logoLink;
        this.productInfoURL = productInfoURL;
        if (acquisitionScheduleURL != null) {
            this.acquisitionScheduleURL = acquisitionScheduleURL;
        }
        else {
            this.acquisitionScheduleURL = new String("acqSchedule.html");
        }
        this.dataAcqRequestURL = null;
        this.sceneIdHint = sceneIdHint;
        this.resolutions = resolutions;
        this.borderX = borderX;
        this.borderY = borderY;
        this.borderColor = borderColor;
        this.hasAcqDate = true;
        this.hasJulianDateMetadata = false;
        this.hasSecondaryIDMetadata = false;
        this.isFullMosaic = false;
        this.isOrderable = false;
        this.maxScenesPerOrder = 0;
        this.isDownloadable = false;
        this.mightBeDownloadable = false;
        this.slowDownloadStart = false;
        this.hasColRowInSceneID = false;
        this.displaySceneCenterLatLong = false;
        this.hasConstantOffsets = false;
        this.hasUpperLeftInToc = true;
        this.hasCloudCover = true;
        this.useCloudCoverForDefaultScenes = true;
        this.hasCustomSceneInfoLine = false;
        this.allowAddAll = true;
        this.hasDataVersions = false;
        this.hasMultipleBrowse = false;
        this.hasGridColRowFilter = false;
        this.hasSwathMode = false;
        this.hasNdviLineGraph = false;
        this.hasUserDefinedArea = false;
        this.dataHasGaps = false;
        this.cgiDatasetName = null;
        this.useHighlightTransparentPixelFix = true;
        this.hasLookAngle = false;
        this.hasProjectName = false;
        this.hasMultipleDatasets = false;
        this.hideGridEntry = false;
        this.warnWhenOrderingPoorQuality = false;
        this.hasGeographicBumper = true;
        this.qualityLimit = 0;
        this.logoLocation = 1;
        this.downloadFileFormat = "compressed GeoTIFF";
        this.defaultProjectionCode = -1;
        this.locatorMap = 0;
        this.orderWindowName = new String("EE_shopping_cart");
        this.numQualityValues = 0;
        this.sceneList = new SceneList(applet, this);
        this.hiddenSceneList = new SceneList(applet, this);
        this.threeDigitFormat = new DecimalFormat("000");
    }
    
    public void completeMetadata(final Metadata metadata) {
    }
    
    public String getSensorDirectory() {
        return this.directory;
    }
    
    public String getCellDirectory(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer(this.getSensorDirectory());
        sb.append("/p");
        sb.append(this.threeDigitFormat.format(n));
        sb.append("/r");
        sb.append(this.threeDigitFormat.format(n2));
        return sb.toString();
    }
    
    public void readTOC(final TOC toc) {
        toc.read(this);
    }
    
    public abstract String makeImageName(final Metadata p0, final int p1);
    
    public abstract int getNumCellsAtResolution(final int p0);
    
    public abstract SceneFilter getSceneFilter(final MosaicData p0, final TOC[] p1);
    
    public String buildOrderURL(final Metadata[] array) {
        final Vector<String> vector = new Vector<String>();
        final String s = new String();
        if (array.length < 1) {
            return null;
        }
        for (int i = 0; i < array.length; ++i) {
            vector.add(array[i].entityID);
        }
        return EarthExplorer.buildShoppingCartUrl(this.datasetName, vector);
    }
    
    public String buildDownloadURL(final Metadata metadata) {
        if (this.isDownloadable) {
            final Vector<String> vector = new Vector<String>();
            vector.add(metadata.entityID);
            return EarthExplorer.buildShoppingCartUrl(this.datasetName, vector);
        }
        return null;
    }
    
    public double getLowestResolution() {
        return this.getActualResolution(this.resolutions[0]);
    }
    
    public double getOffsetResolution() {
        return this.getActualResolution(this.resolutions[this.resolutions.length - 2]);
    }
    
    public double getActualResolution(final int n) {
        return n;
    }
    
    public abstract Dimension getNominalSceneSize();
    
    public abstract int getStartingYear();
    
    public abstract int getEndingYear();
    
    public abstract int getImageFileSize(final int p0);
    
    public void showBrowse(final Metadata metadata) {
        this.showSceneURL(this.showBrowseCGI, metadata);
    }
    
    public void showMetadata(final Metadata metadata) {
        this.showSceneURL(this.showMetadataCGI, metadata);
    }
    
    private void showSceneURL(final String s, final Metadata metadata) {
        try {
            String s2 = s + "?scene_id=" + URLEncoder.encode(metadata.entityID, "UTF-8");
            if (!this.hasColRowInSceneID) {
                s2 = s2 + "&path=" + this.navModel.getColumnString(metadata.gridCol) + "&row=" + this.navModel.getRowString(metadata.gridRow);
            }
            if (this.hasSecondaryIDMetadata) {
                s2 = s2 + "&secondary_id=" + URLEncoder.encode(metadata.secondaryID, "UTF-8");
            }
            if (this.hasMultipleBrowse) {
                s2 = s2 + "&browse_number=" + metadata.browseNumber;
            }
            if (this.cgiDatasetName != null) {
                s2 = s2 + "&dataset=" + URLEncoder.encode(this.cgiDatasetName, "UTF-8");
            }
            this.applet.getAppletContext().showDocument(new URL(this.applet.getCodeBase(), s2), "_blank");
        }
        catch (Exception ex) {
            System.out.println("exception: " + ex);
        }
    }
    
    String buildEntityID(final Metadata metadata) {
        return metadata.entityID;
    }
    
    String buildMediumEntityID(final Metadata metadata) {
        return this.buildEntityID(metadata);
    }
    
    String buildShortEntityID(final Metadata metadata) {
        return this.buildEntityID(metadata);
    }
    
    public String getCustomSceneInfo(final Metadata metadata) {
        return null;
    }
    
    public String[] getFilesForScene(final Metadata metadata) {
        final String[] array = new String[4];
        final String string = this.getCellDirectory(metadata.gridCol, metadata.gridRow) + "/y" + metadata.year + "/" + metadata.entityID;
        array[0] = string + ".meta";
        array[1] = metadata.entityID + ".meta";
        array[2] = string + ".jpg";
        array[3] = metadata.entityID + ".jpg";
        return array;
    }
    
    public Color getBorderColor(final Metadata metadata) {
        return this.borderColor;
    }
    
    public Metadata createMetadata(final String s, final int n, final int n2) {
        return new Metadata(s.substring(s.indexOf(44) + 1), this, n, n2);
    }
    
    public Sensor[] getSensorList() {
        return new Sensor[] { this };
    }
    
    public String getResolutionString(final int n) {
        return "" + this.resolutions[n] + "m";
    }
    
    public boolean confirmInitialDisplay() {
        return true;
    }
}
