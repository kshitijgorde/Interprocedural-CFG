import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Metadata
{
    int date;
    int gridCol;
    int gridRow;
    int year;
    int month;
    int jDate;
    int ulX;
    int ulY;
    int cloudCover;
    int browseNumber;
    int downloadFileSize;
    int[] quality;
    String dataVersion;
    String entityID;
    String secondaryID;
    String projectName;
    int[] sampOffset;
    int[] lineOffset;
    Point centerXY;
    Polygon screenLocation;
    Image image;
    int imageRes;
    String lookAngle;
    boolean isDownloadable;
    boolean visible;
    private int filterFlags;
    private Sensor sensor;
    private LatLong[] sceneCorners;
    private static final int[] monthToJDate;
    private static final int[] leapYearMonthToJDate;
    static final int VIEWPORT_FILTER = 1;
    static final int CLOUD_COVER_FILTER = 2;
    static final int DATE_FILTER = 4;
    static final int SCENE_LIST_FILTER = 8;
    static final int QUALITY_FILTER = 16;
    static final int DATA_VERSION_FILTER = 32;
    static final int HIDDEN_SCENE_FILTER = 64;
    static final int USER_DEFINED_AREA_FILTER = 128;
    static final int GRID_COL_ROW_FILTER = 256;
    static final int IMAGE_QUALITY_MAX = 9;
    
    Metadata(final String s, final Sensor sensor, final int gridCol, final int gridRow) {
        this.sensor = sensor;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        this.visible = true;
        this.isDownloadable = false;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (sensor.hasAcqDate) {
            this.date = Integer.parseInt(stringTokenizer.nextToken());
            this.year = this.date / 10000;
            this.month = (this.date - this.year * 10000) / 100;
            if (sensor.hasJulianDateMetadata) {
                this.jDate = Integer.parseInt(stringTokenizer.nextToken());
            }
            else if (this.year % 4 != 0 || (this.year % 100 == 0 && this.year % 400 != 0)) {
                this.jDate = Metadata.monthToJDate[this.month - 1] + this.date - this.year * 10000 - this.month * 100;
            }
            else {
                this.jDate = Metadata.leapYearMonthToJDate[this.month - 1] + this.date - this.year * 10000 - this.month * 100;
            }
        }
        if (sensor.hasUpperLeftInToc) {
            this.ulX = Integer.parseInt(stringTokenizer.nextToken());
            this.ulY = Integer.parseInt(stringTokenizer.nextToken());
        }
        if (sensor.hasCloudCover) {
            this.cloudCover = Integer.parseInt(stringTokenizer.nextToken());
        }
        else {
            this.cloudCover = 0;
        }
        this.quality = null;
        if (sensor.hasSecondaryIDMetadata) {
            this.entityID = stringTokenizer.nextToken();
            this.secondaryID = stringTokenizer.nextToken();
        }
        else {
            this.entityID = stringTokenizer.nextToken();
        }
        if (sensor.numQualityValues > 0) {
            this.quality = new int[sensor.numQualityValues];
            for (int i = 0; i < sensor.numQualityValues; ++i) {
                this.quality[i] = Character.digit(stringTokenizer.nextToken().trim().charAt(0), 16);
                if (this.quality[i] > 9) {
                    this.quality[i] = 9;
                }
            }
        }
        if (sensor.hasProjectName) {
            this.projectName = stringTokenizer.nextToken();
        }
        else {
            this.projectName = null;
        }
        if (!sensor.hasConstantOffsets) {
            this.sampOffset = new int[4];
            this.lineOffset = new int[4];
            for (int j = 0; j < 4; ++j) {
                this.sampOffset[j] = Integer.parseInt(stringTokenizer.nextToken());
                this.lineOffset[j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        if (sensor.hasMultipleBrowse) {
            if (stringTokenizer.hasMoreTokens()) {
                this.browseNumber = Integer.parseInt(stringTokenizer.nextToken());
            }
            else {
                this.browseNumber = 0;
            }
        }
        else {
            this.browseNumber = 0;
        }
        if (sensor.isDownloadable && stringTokenizer.hasMoreTokens()) {
            this.downloadFileSize = Integer.parseInt(stringTokenizer.nextToken());
            if (this.downloadFileSize > 0) {
                this.isDownloadable = true;
            }
        }
        this.lookAngle = new String();
        if (sensor.hasLookAngle && stringTokenizer.hasMoreTokens()) {
            this.lookAngle = new String(stringTokenizer.nextToken());
        }
        if (!sensor.hasUpperLeftInToc || sensor.hasConstantOffsets) {
            sensor.completeMetadata(this);
        }
        this.sceneCorners = null;
    }
    
    Metadata(final Metadata metadata) {
        this.date = metadata.date;
        this.gridCol = metadata.gridCol;
        this.gridRow = metadata.gridRow;
        this.year = metadata.year;
        this.month = metadata.month;
        this.jDate = metadata.jDate;
        this.ulX = metadata.ulX;
        this.ulY = metadata.ulY;
        this.cloudCover = metadata.cloudCover;
        this.browseNumber = metadata.browseNumber;
        this.dataVersion = metadata.dataVersion;
        this.lookAngle = new String(metadata.lookAngle);
        this.projectName = metadata.projectName;
        this.quality = metadata.quality;
        this.entityID = new String(metadata.entityID);
        this.secondaryID = metadata.secondaryID;
        this.sampOffset = null;
        this.lineOffset = null;
        this.downloadFileSize = metadata.downloadFileSize;
        this.isDownloadable = metadata.isDownloadable;
        if (metadata.centerXY != null) {
            this.centerXY = new Point(metadata.centerXY);
        }
        this.screenLocation = metadata.screenLocation;
        this.image = null;
        this.imageRes = metadata.imageRes;
        this.visible = false;
        this.filterFlags = metadata.filterFlags;
        this.sensor = metadata.sensor;
        this.sceneCorners = metadata.sceneCorners;
    }
    
    public String getDateString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.year);
        sb.append("/");
        sb.append(this.month);
        sb.append("/");
        sb.append(this.date - (this.year * 10000 + this.month * 100));
        return sb.toString();
    }
    
    public void calculateSceneCenter(final double n) {
        this.centerXY = new Point(this.ulX + (int)Math.round(((this.sampOffset[2] - this.sampOffset[0]) / 2 + this.sampOffset[0]) * n), this.ulY - (int)Math.round(((this.lineOffset[2] - this.lineOffset[0]) / 2 + this.lineOffset[0]) * n));
    }
    
    public void setSceneCorners(final ProjectionTransformation projectionTransformation) {
        this.sceneCorners = new LatLong[4];
        final double offsetResolution = this.sensor.getOffsetResolution();
        for (int i = 0; i < 4; ++i) {
            this.sceneCorners[i] = projectionTransformation.projToLatLong((int)Math.round(this.ulX + this.sampOffset[i] * offsetResolution), (int)Math.round(this.ulY - this.lineOffset[i] * offsetResolution));
        }
    }
    
    public void getSceneCornersInProj(final ProjectionTransformation projectionTransformation, final int[] array, final int[] array2) {
        for (int i = 0; i < 4; ++i) {
            final Point latLongToProj = projectionTransformation.latLongToProj(this.sceneCorners[i]);
            array[i] = latLongToProj.x;
            array2[i] = latLongToProj.y;
        }
    }
    
    public int daysBetween(final int n, final int n2) {
        int abs;
        if (n == this.year) {
            abs = Math.abs(n2 - this.jDate);
        }
        else if (n < this.year) {
            abs = 365 * (this.year - n) + (this.jDate - n2);
        }
        else {
            abs = 365 * (n - this.year) + (n2 - this.jDate);
        }
        return abs;
    }
    
    public void cleanup() {
        this.quality = null;
        this.entityID = null;
        this.secondaryID = null;
        this.sampOffset = null;
        this.lineOffset = null;
        this.screenLocation = null;
        if (this.image != null) {
            this.image.flush();
        }
        this.image = null;
        this.sensor = null;
    }
    
    public final void showBrowse() {
        this.sensor.showBrowse(this);
    }
    
    public final void showMetadata() {
        this.sensor.showMetadata(this);
    }
    
    private final void updateVisible() {
        if (this.filterFlags == 0) {
            this.visible = true;
        }
        else {
            this.visible = false;
        }
    }
    
    public void clearFilter(final int n) {
        this.filterFlags &= ~n;
        this.updateVisible();
    }
    
    public void filterToViewport(final Polygon polygon) {
        if (polygon.contains(this.centerXY.x, this.centerXY.y)) {
            this.filterFlags &= 0xFFFFFFFE;
        }
        else {
            this.filterFlags |= 0x1;
        }
        this.updateVisible();
    }
    
    public void filterToCloudCover(final int n) {
        if (this.cloudCover <= n) {
            this.filterFlags &= 0xFFFFFFFD;
        }
        else {
            this.filterFlags |= 0x2;
        }
        this.updateVisible();
    }
    
    public void filterToDateRange(final int n, final int n2, int n3, int n4) {
        if (!this.sensor.hasAcqDate) {
            this.filterFlags &= 0xFFFFFFFB;
            return;
        }
        ++n3;
        ++n4;
        final boolean b = n3 > n4;
        if (this.year >= n && this.year <= n2) {
            boolean b2 = false;
            if (!b) {
                if (this.month >= n3 && this.month <= n4) {
                    b2 = true;
                }
            }
            else if (this.month >= n3 || this.month <= n4) {
                b2 = true;
            }
            if (b2) {
                this.filterFlags &= 0xFFFFFFFB;
            }
            else {
                this.filterFlags |= 0x4;
            }
        }
        else {
            this.filterFlags |= 0x4;
        }
        this.updateVisible();
    }
    
    public void filterToGridColRowRange(final int n, final int n2, final int n3, final int n4) {
        if (this.sensor.hasGridColRowFilter) {
            if (this.gridCol >= n && this.gridCol <= n2) {
                boolean b = false;
                if (this.gridRow >= n3 && this.gridRow <= n4) {
                    b = true;
                }
                if (b) {
                    this.filterFlags &= 0xFFFFFEFF;
                }
                else {
                    this.filterFlags |= 0x100;
                }
            }
            else {
                this.filterFlags |= 0x100;
            }
            this.updateVisible();
        }
    }
    
    public void filterToSceneList(final boolean b) {
        if (b) {
            if (this.sensor.sceneList.find(this) != -1) {
                this.filterFlags &= 0xFFFFFFF7;
            }
            else {
                this.filterFlags |= 0x8;
            }
        }
        else {
            this.filterFlags &= 0xFFFFFFF7;
        }
        this.updateVisible();
    }
    
    public void filterToHiddenScene() {
        if (this.sensor.hiddenSceneList.find(this) != -1) {
            this.filterFlags |= 0x40;
        }
        else {
            this.filterFlags &= 0xFFFFFFBF;
        }
        this.updateVisible();
    }
    
    public void filterToUserArea(final boolean b, final UserDefinedAreaDialog userDefinedAreaDialog) {
        if (b && this.sensor.hasUserDefinedArea) {
            if (userDefinedAreaDialog.getUserDefinedArea().sceneIntersects(this)) {
                this.filterFlags &= 0xFFFFFF7F;
            }
            else {
                this.filterFlags |= 0x80;
            }
        }
        else {
            this.filterFlags &= 0xFFFFFF7F;
        }
        this.updateVisible();
    }
    
    public void filterToQuality(final int n) {
        final int quality = this.getQuality();
        if (quality >= 0) {
            if (quality >= n) {
                this.filterFlags &= 0xFFFFFFEF;
            }
            else {
                this.filterFlags |= 0x10;
            }
            this.updateVisible();
        }
    }
    
    public void filterToDataVersion(final String s) {
        if (this.sensor.hasDataVersions) {
            if (s.equals("All") || this.dataVersion.equals(s)) {
                this.filterFlags &= 0xFFFFFFDF;
            }
            else {
                this.filterFlags |= 0x20;
            }
            this.updateVisible();
        }
    }
    
    String getEntityIDForDisplay() {
        return this.sensor.buildEntityID(this);
    }
    
    String getMediumEntityIDForDisplay() {
        return this.sensor.buildMediumEntityID(this);
    }
    
    String getShortEntityIDForDisplay() {
        return this.sensor.buildShortEntityID(this);
    }
    
    public int getQuality() {
        if (this.sensor.numQualityValues > 0) {
            int n = this.quality[0];
            for (int i = 1; i < this.quality.length; ++i) {
                if (this.quality[i] < n) {
                    n = this.quality[i];
                }
            }
            return n;
        }
        return -1;
    }
    
    Sensor getSensor() {
        return this.sensor;
    }
    
    static {
        monthToJDate = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
        leapYearMonthToJDate = new int[] { 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };
    }
}
