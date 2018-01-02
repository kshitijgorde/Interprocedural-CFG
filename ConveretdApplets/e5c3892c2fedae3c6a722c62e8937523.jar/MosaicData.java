import java.awt.Component;
import javax.swing.JOptionPane;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Polygon;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class MosaicData extends Observable implements Runnable, WorkMonitor
{
    private imgViewer applet;
    private LocatorMap locatorMap;
    public static final Object DISPLAY_MODE_CHANGED;
    public Point targetXY;
    private boolean isTargetXY;
    private final int mosaicWidth = 3;
    private final int mosaicHeight = 3;
    private final int mosaicSize = 9;
    private final int colCenterIndex = 1;
    private final int rowCenterIndex = 1;
    private final int mosaicCenterIndex = 4;
    private int activeCellIndex;
    private int[] defaultZOrder;
    int gridCol;
    int gridRow;
    private ProjectionTransformation proj;
    private int projectionCode;
    private TOC[] mosaicCells;
    private int cellsToDisplay;
    private Sensor currSensor;
    int pixelSize;
    double actualPixelSize;
    MapLayers mapLayers;
    private DateCache dateCache;
    int maxCloudCover;
    private ZOrderList zOrderList;
    private ImagePane pane;
    private Dimension displaySize;
    public MosaicCoords mosaicCoords;
    public SceneFilter sceneFilter;
    private int subCol;
    private int subRow;
    public static final int STEPS = 3;
    public ImageLoader imageLoader;
    private boolean areImagesLoading;
    private int notifyType;
    private static final int NORMAL_NOTIFY = 0;
    private static final int TARGETXY_NOTIFY = 1;
    private static final int DISPLAY_MODE_CHANGE_NOTIFY = 2;
    private Thread loaderThread;
    private Object loadLock;
    private boolean killThread;
    private boolean isLoading;
    private boolean isLoadCancelled;
    private TOC[] loadingMosaicCells;
    private int loadingActiveCellIndex;
    private boolean loadingPreserveZOrder;
    private boolean[] loadingUsed;
    private boolean loadCompleted;
    private boolean tocChangePending;
    private boolean resolutionChangePending;
    private Metadata targetScene;
    private Metadata refScrollScene;
    private int numTocsToLoad;
    private int currTocLoading;
    private boolean isCalledFromScrolledData;
    private Metadata targetDateScene;
    private LatLong targetLatLong;
    
    MosaicData(final imgViewer applet, final ImagePane pane, final LocatorMap locatorMap) {
        this.targetXY = new Point();
        this.isTargetXY = false;
        this.activeCellIndex = 4;
        this.maxCloudCover = 100;
        this.applet = applet;
        this.pane = pane;
        this.locatorMap = locatorMap;
        this.isCalledFromScrolledData = false;
        this.targetDateScene = null;
        this.mosaicCells = new TOC[9];
        this.defaultZOrder = new int[9];
        int n = 0;
        for (int i = 0; i < 3; ++i) {
            if (i != 1) {
                for (int j = 0; j < 3; ++j) {
                    this.defaultZOrder[n] = i * 3 + j;
                    ++n;
                }
            }
        }
        final int n2 = 1;
        for (int k = 0; k < 3; ++k) {
            if (k != 1) {
                this.defaultZOrder[n] = n2 * 3 + k;
                ++n;
            }
        }
        this.defaultZOrder[n] = 4;
        this.imageLoader = new ImageLoader(applet, pane);
        this.dateCache = new DateCache(20);
        this.zOrderList = new ZOrderList();
        this.mapLayers = new MapLayers(this.applet, this.pane);
        this.sceneFilter = new LandsatSceneFilter(this);
        this.mosaicCoords = new MosaicCoords();
        this.displaySize = new Dimension(1, 1);
        this.loadLock = new Object();
        (this.loaderThread = new Thread(this, "TOC Loader Thread")).start();
    }
    
    @Override
    public String getWorkLabel() {
        return "Reading Inventory";
    }
    
    @Override
    public boolean isWorking() {
        return this.isUnstableTOC();
    }
    
    @Override
    public int getTotalWork() {
        return this.numTocsToLoad;
    }
    
    @Override
    public int getWorkComplete() {
        return this.currTocLoading;
    }
    
    private void setDefaultZOrder() {
        if (this.currSensor.isFullMosaic) {
            for (int i = 0; i < this.mosaicCells.length; ++i) {
                final TOC toc = this.mosaicCells[this.defaultZOrder[i]];
                if (toc.valid) {
                    for (int j = 0; j < toc.numImg; ++j) {
                        this.zOrderList.insertByCloudCover(toc.scenes[j]);
                    }
                }
            }
        }
        if (this.applet.toolsMenu.isDefaultToDateEnabled()) {
            final TOC toc2 = this.mosaicCells[this.activeCellIndex];
            if (toc2.valid) {
                this.setDefaultToSelectedDate(toc2.scenes[toc2.currentDateIndex]);
            }
        }
        for (int k = 0; k < this.mosaicCells.length; ++k) {
            final TOC toc3 = this.mosaicCells[this.defaultZOrder[k]];
            if (toc3.valid) {
                this.zOrderList.putOnTop(toc3.scenes[toc3.currentDateIndex]);
            }
        }
        if (this.currSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) {
            final TOC toc4 = this.mosaicCells[this.activeCellIndex];
            if (toc4.valid) {
                this.buildSwath(toc4.scenes[toc4.currentDateIndex], true);
            }
        }
    }
    
    public void setSelectedScene(final Metadata metadata) {
        this.updateSelectedScene(metadata);
        this.notifyType = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    private void updateSelectedScene(final Metadata metadata) {
        this.zOrderList.putOnTop(metadata);
        final int colRowToCell = this.colRowToCell(metadata.gridCol, metadata.gridRow);
        if (colRowToCell == -1) {
            System.out.println("Bug detected in updateSelectedScene");
            return;
        }
        TOC toc;
        int currentDateIndex;
        for (toc = this.mosaicCells[colRowToCell], currentDateIndex = 0; currentDateIndex < toc.numImg && metadata != toc.scenes[currentDateIndex]; ++currentDateIndex) {}
        if (currentDateIndex < toc.numImg) {
            toc.currentDateIndex = currentDateIndex;
        }
        else {
            System.out.println("Bug in updateSelectedScene");
        }
        this.activeCellIndex = colRowToCell;
        this.sceneFilter.filter();
        if (this.currSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) {
            this.buildSwath(metadata, true);
            this.mosaicCoordsUpdate();
            this.loadScenes();
        }
    }
    
    private void setSelectedCell(final int activeCellIndex, final boolean b) {
        if (!b) {
            boolean b2 = false;
            if (this.subCol != 0 || this.subRow != 0) {
                this.zOrderList.top();
                Metadata down;
                while ((down = this.zOrderList.down()) != null) {
                    if (down.visible) {
                        this.updateSelectedScene(down);
                        b2 = true;
                        break;
                    }
                }
            }
            if (!b2) {
                final TOC toc = this.mosaicCells[activeCellIndex];
                if (toc.valid) {
                    this.zOrderList.putOnTop(toc.scenes[toc.currentDateIndex]);
                }
                this.activeCellIndex = activeCellIndex;
                this.sceneFilter.filter();
            }
        }
        else {
            this.sceneFilter.filter();
        }
        this.setChanged();
        if (this.isTargetXY) {
            this.notifyType = 1;
            this.notifyObservers(this.targetXY);
        }
        else {
            this.notifyType = 0;
            this.notifyObservers();
        }
    }
    
    private boolean isInActiveSwath(final Metadata metadata) {
        final Metadata currentScene = this.getCurrentScene();
        return currentScene != null && currentScene.date == metadata.date && currentScene.getSensor() == metadata.getSensor();
    }
    
    public void lowerScene(final Metadata metadata) {
        if (this.cellsToDisplay == 0) {
            return;
        }
        if (this.currSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled() && this.isInActiveSwath(metadata)) {
            final Vector scenesInSwath = this.getScenesInSwath(metadata);
            for (int i = 0; i < scenesInSwath.size(); ++i) {
                this.zOrderList.putOnBottom(scenesInSwath.elementAt(i));
            }
            this.zOrderList.top();
            final Metadata down = this.zOrderList.down();
            final int colRowToCell = this.colRowToCell(down.gridCol, down.gridRow);
            final TOC toc = this.mosaicCells[colRowToCell];
            toc.currentDateIndex = toc.getIndexOfDate(down.date, down.getSensor());
            this.activeCellIndex = colRowToCell;
            this.buildSwath(down, true);
            this.mosaicCoordsUpdate();
            this.loadScenes();
            return;
        }
        this.zOrderList.top();
        final Metadata down2 = this.zOrderList.down();
        if (down2 == metadata) {
            final Polygon screenLocation = down2.screenLocation;
            boolean b = false;
            Metadata down3;
            while ((down3 = this.zOrderList.down()) != null) {
                if (!down3.visible) {
                    continue;
                }
                final int[] xpoints = down3.screenLocation.xpoints;
                final int[] ypoints = down3.screenLocation.ypoints;
                for (int j = 0; j < 4; ++j) {
                    if (screenLocation.contains(xpoints[j], ypoints[j])) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    break;
                }
            }
            if (b) {
                this.zOrderList.putOnBottom(metadata);
                int colRowToCell2;
                int currentDateIndex;
                for (colRowToCell2 = this.colRowToCell(down3.gridCol, down3.gridRow), currentDateIndex = 0; currentDateIndex < this.mosaicCells[colRowToCell2].numImg && this.mosaicCells[colRowToCell2].scenes[currentDateIndex] != down3; ++currentDateIndex) {}
                if (currentDateIndex < this.mosaicCells[colRowToCell2].numImg) {
                    this.mosaicCells[colRowToCell2].currentDateIndex = currentDateIndex;
                }
                this.setSelectedCell(colRowToCell2, false);
            }
        }
        else {
            this.zOrderList.putOnBottom(metadata);
            this.pane.repaint();
        }
    }
    
    public void setScenesToDate(final Metadata metadata) {
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            this.mosaicCells[i].findSceneClosestToDate(metadata);
        }
        this.rebuildZOrder();
        this.mosaicCoordsUpdate();
        this.loadScenes();
        this.notifyType = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    public void setDefaultToSelectedDate(final Metadata metadata) {
        if (this.applet.toolsMenu.isDefaultToDateEnabled()) {
            if (this.targetDateScene == null) {
                this.targetDateScene = metadata;
            }
            if (!this.isCalledFromScrolledData) {
                this.targetDateScene = metadata;
            }
            for (int i = 0; i < this.mosaicCells.length; ++i) {
                this.mosaicCells[i].findSceneClosestToDate(this.targetDateScene);
            }
            this.isCalledFromScrolledData = false;
        }
    }
    
    public void resetTargetDate() {
        this.targetDateScene = null;
    }
    
    public void setDefaultScene(final Metadata metadata) {
        final int colRowToCell = this.colRowToCell(metadata.gridCol, metadata.gridRow);
        final TOC toc = this.mosaicCells[colRowToCell];
        if (!this.isUnstableTOC() && toc.valid) {
            final int pickDefaultScene = this.pickDefaultScene(colRowToCell);
            this.flushCurrentDateIndex(toc);
            toc.currentDateIndex = pickDefaultScene;
            this.dateCache.remove(toc.gridCol, toc.gridRow);
            if (this.currSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) {
                this.buildSwath(toc.scenes[toc.currentDateIndex], true);
            }
            if (this.applet.toolsMenu.isDefaultToDateEnabled()) {
                this.setDefaultToSelectedDate(toc.scenes[toc.currentDateIndex]);
            }
            this.rebuildZOrder();
            this.mosaicCoordsUpdate();
            this.loadScenes();
            this.notifyType = 0;
            this.setChanged();
            this.notifyObservers();
        }
    }
    
    public void selectUserAreaScenes(final Metadata metadata) {
        if (this.cellsToDisplay == 0) {
            final TOC toc = this.mosaicCells[this.activeCellIndex];
            final Metadata metadata2 = toc.scenes[toc.currentDateIndex];
            if (metadata2.visible) {
                this.currSensor.sceneList.add(metadata2);
            }
        }
        else {
            final UserDefinedArea userDefinedArea = this.applet.userDefinedAreaDialog.getUserDefinedArea();
            final boolean userDefinedAreaEnabled = this.applet.searchLimitDialog.isUserDefinedAreaEnabled();
            for (int i = 0; i < this.mosaicCells.length; ++i) {
                final TOC toc2 = this.mosaicCells[i];
                if (toc2.valid) {
                    for (int j = 0; j < toc2.numImg; ++j) {
                        final Metadata metadata3 = toc2.scenes[j];
                        if (userDefinedAreaEnabled) {
                            if (metadata3.visible) {
                                this.currSensor.sceneList.add(metadata3);
                            }
                        }
                        else if (metadata3.visible && userDefinedArea.sceneIntersects(metadata3)) {
                            this.currSensor.sceneList.add(metadata3);
                        }
                    }
                }
            }
        }
    }
    
    public void selectAllScenes(final Metadata metadata) {
        if (this.cellsToDisplay == 0) {
            final TOC toc = this.mosaicCells[this.activeCellIndex];
            final Metadata metadata2 = toc.scenes[toc.currentDateIndex];
            if (metadata2.visible) {
                this.currSensor.sceneList.add(metadata2);
            }
        }
        else {
            for (int i = 0; i < this.mosaicCells.length; ++i) {
                final TOC toc2 = this.mosaicCells[i];
                if (toc2.valid) {
                    final Metadata metadata3 = toc2.scenes[toc2.currentDateIndex];
                    if (metadata3.visible) {
                        this.currSensor.sceneList.add(metadata3);
                    }
                }
            }
        }
    }
    
    public void selectSwathScenes(final Metadata metadata, final boolean b) {
        final Vector scenesInSwath = this.getScenesInSwath(metadata);
        for (int i = 0; i < scenesInSwath.size(); ++i) {
            final Metadata metadata2 = scenesInSwath.elementAt(i);
            if (metadata2.visible) {
                if (b) {
                    this.currSensor.hiddenSceneList.add(metadata2);
                }
                else {
                    this.currSensor.sceneList.add(metadata2);
                }
            }
        }
        if (b) {
            this.zOrderList.top();
            this.buildSwath(this.zOrderList.down(), true);
        }
    }
    
    public void setSensor(final Sensor currSensor) {
        this.cancelLoad();
        this.imageLoader.cancelLoad();
        this.areImagesLoading = false;
        this.mapLayers.cancelLoad();
        if (this.currSensor != currSensor) {
            this.dateCache.flush();
            this.zOrderList.empty();
        }
        LatLong latLong = null;
        if (this.currSensor == null) {
            latLong = currSensor.navModel.gridToLatLong(this.gridCol, this.gridRow);
        }
        else {
            if (this.cellsToDisplay == 1 || this.cellsToDisplay == 0) {
                final TOC toc = this.mosaicCells[this.activeCellIndex];
                if (toc.valid) {
                    latLong = this.currSensor.navModel.gridToLatLong(toc.gridCol, toc.gridRow);
                }
            }
            if (latLong == null) {
                latLong = this.currSensor.navModel.gridToLatLong(this.gridCol, this.gridRow);
            }
        }
        this.mosaicCoords.invalidate();
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            this.mosaicCells[i].valid = false;
        }
        this.pixelSize = this.applet.resolutionMenu.setSensor(this.currSensor, currSensor);
        this.currSensor = currSensor;
        this.subCol = 0;
        this.subRow = 0;
        if (this.currSensor.isFullMosaic) {
            this.zOrderList.setMultipleSceneMode();
        }
        else {
            this.zOrderList.setSingleSceneMode();
        }
        this.actualPixelSize = this.currSensor.getActualResolution(this.pixelSize);
        this.cellsToDisplay = this.currSensor.getNumCellsAtResolution(this.pixelSize);
        Point point = this.currSensor.navModel.latLongToGrid(latLong.latitude, latLong.longitude);
        if (!this.canMoveToMapArea(point.x, point.y)) {
            final GeographicLocatorMapConfig mapConfig = this.locatorMap.getMapConfig(this.currSensor.locatorMap);
            latLong.latitude = (mapConfig.topLat + mapConfig.bottomLat) / 2.0;
            latLong.longitude = (mapConfig.leftLon + mapConfig.rightLon) / 2.0;
            point = this.currSensor.navModel.latLongToGrid(latLong.latitude, latLong.longitude);
        }
        this.gridCol = point.x;
        this.gridRow = point.y;
        this.scrollData(this.gridCol, this.gridRow, 0, 0, false, false, false);
    }
    
    private int pickPreferredProjCode() {
        int projCode = 1100;
        if (this.mosaicCells[4].valid) {
            projCode = this.mosaicCells[4].projCode;
        }
        else {
            int n = 1100;
            int n2 = 0;
            int n3 = 0;
            for (int i = 0; i < this.mosaicCells.length; ++i) {
                final TOC toc = this.mosaicCells[i];
                if (toc.valid) {
                    if (n3 == 0) {
                        projCode = (n = toc.projCode);
                    }
                    ++n3;
                    if (projCode == toc.projCode) {
                        ++n2;
                    }
                    else {
                        n = toc.projCode;
                    }
                }
            }
            if (2 * n2 < n3) {
                projCode = n;
            }
        }
        return projCode;
    }
    
    public int getProjectionCode() {
        return this.projectionCode;
    }
    
    private int pickDefaultScene(final int n) {
        final TOC toc = this.mosaicCells[n];
        int n2 = -1;
        int n3 = -1;
        int n5;
        int n4 = n5 = 1010;
        for (int i = toc.numImg - 1; i >= 0; --i) {
            final Metadata metadata = toc.scenes[i];
            int quality = metadata.getQuality();
            if (quality == -1) {
                quality = 9;
            }
            final int n6 = 1 * metadata.cloudCover + 101 * (9 - quality);
            if (n6 < n4 && metadata.visible) {
                n4 = n6;
                n2 = i;
            }
            if (n6 < n5) {
                n5 = n6;
                n3 = i;
            }
        }
        if (n2 == -1) {
            n2 = n3;
        }
        return n2;
    }
    
    private void pickSceneDates() {
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            final TOC toc = this.mosaicCells[i];
            int currentDateIndex = toc.currentDateIndex;
            if (this.targetScene != null && this.targetScene.gridCol == toc.gridCol && this.targetScene.gridRow == toc.gridRow) {
                currentDateIndex = toc.findScene(this.targetScene);
                if (currentDateIndex >= 0) {
                    toc.currentDateIndex = currentDateIndex;
                }
                this.targetScene = null;
            }
            if (toc.valid && currentDateIndex != -1 && !toc.scenes[toc.currentDateIndex].visible) {
                currentDateIndex = -1;
            }
            if (toc.valid && currentDateIndex == -1) {
                int currentDateIndex2 = this.dateCache.lookupDate(toc);
                if (currentDateIndex2 == -1 || currentDateIndex2 >= toc.numImg || !toc.scenes[currentDateIndex2].visible) {
                    currentDateIndex2 = this.currSensor.sceneList.contains(toc);
                    if (currentDateIndex2 == -1 || currentDateIndex2 >= toc.numImg || !toc.scenes[currentDateIndex2].visible) {
                        if (!this.currSensor.useCloudCoverForDefaultScenes) {
                            currentDateIndex2 = -1;
                            for (int j = toc.numImg - 1; j >= 0; --j) {
                                if (toc.scenes[j].visible) {
                                    currentDateIndex2 = j;
                                    break;
                                }
                            }
                            if (currentDateIndex2 == -1) {
                                currentDateIndex2 = toc.numImg - 1;
                            }
                        }
                        else {
                            currentDateIndex2 = this.pickDefaultScene(i);
                        }
                    }
                }
                if (!this.currSensor.isFullMosaic && currentDateIndex2 != toc.currentDateIndex && toc.currentDateIndex != -1 && toc.currentDateIndex < toc.numImg) {
                    this.flushCurrentDateIndex(toc);
                }
                toc.currentDateIndex = currentDateIndex2;
            }
        }
        if (this.currSensor.isFullMosaic && this.cellsToDisplay == 0 && this.isTargetXY) {
            double n = 1.0E12;
            final TOC toc2 = this.mosaicCells[this.activeCellIndex];
            for (int k = 0; k < toc2.numImg; ++k) {
                final Metadata metadata = toc2.scenes[k];
                if (metadata.visible) {
                    final double n2 = (metadata.ulX - this.targetXY.x) / 1000;
                    final double n3 = (metadata.ulY - this.targetXY.y) / 1000;
                    final double n4 = n2 * n2 + n3 * n3;
                    if (n4 < n) {
                        toc2.currentDateIndex = k;
                        n = n4;
                    }
                }
            }
        }
        this.pickSwathScenes();
    }
    
    private void flushCurrentDateIndex(final TOC toc) {
        final Metadata metadata = toc.scenes[toc.currentDateIndex];
        if (metadata.image != null) {
            metadata.image.flush();
            metadata.image = null;
            metadata.imageRes = -1;
        }
    }
    
    private void pickSwathScenes() {
        if (this.currSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) {
            if (this.currSensor.isFullMosaic) {
                boolean b = false;
                if (this.refScrollScene != null) {
                    final Vector scenesInSwath = this.getScenesInSwath(this.refScrollScene);
                    this.refScrollScene = null;
                    for (int i = 0; i < scenesInSwath.size(); ++i) {
                        final Metadata metadata = scenesInSwath.elementAt(i);
                        if (metadata.visible) {
                            final int colRowToCell = this.colRowToCell(metadata.gridCol, metadata.gridRow);
                            if (colRowToCell != -1) {
                                this.activeCellIndex = colRowToCell;
                                this.buildSwath(metadata, true);
                                final TOC toc = this.mosaicCells[colRowToCell];
                                for (int j = 0; j < toc.numImg; ++j) {
                                    if (toc.scenes[j] == metadata) {
                                        toc.currentDateIndex = j;
                                        break;
                                    }
                                }
                                b = true;
                                break;
                            }
                        }
                    }
                }
                if (!b) {
                    final TOC toc2 = this.mosaicCells[this.activeCellIndex];
                    if (toc2.valid) {
                        this.buildSwath(toc2.scenes[toc2.currentDateIndex], false);
                    }
                }
            }
            else {
                final int n = this.activeCellIndex / 3;
                for (int k = 0; k < 3; ++k) {
                    TOC toc3;
                    if (n == k) {
                        toc3 = this.mosaicCells[this.activeCellIndex];
                    }
                    else {
                        toc3 = this.mosaicCells[k * 3 + 1];
                    }
                    if (toc3.valid) {
                        this.buildSwath(toc3.scenes[toc3.currentDateIndex], false);
                    }
                }
            }
        }
    }
    
    private void buildSwath(final Metadata metadata, final boolean b) {
        final Vector scenesInSwath = this.getScenesInSwath(metadata);
        for (int i = 0; i < scenesInSwath.size(); ++i) {
            final Metadata metadata2 = scenesInSwath.elementAt(i);
            if (metadata2.visible) {
                if (!this.currSensor.isFullMosaic) {
                    final TOC cellForScene = this.getCellForScene(metadata2);
                    if (cellForScene == null) {
                        continue;
                    }
                    if (metadata2 != cellForScene.scenes[cellForScene.currentDateIndex]) {
                        this.flushCurrentDateIndex(cellForScene);
                    }
                    final int indexOfDate = cellForScene.getIndexOfDate(metadata2.date, metadata2.getSensor());
                    if (indexOfDate != -1) {
                        cellForScene.currentDateIndex = indexOfDate;
                    }
                }
                if (b) {
                    this.zOrderList.putOnTop(metadata2);
                }
                else if (!this.currSensor.isFullMosaic) {
                    this.zOrderList.changeScene(metadata2);
                }
            }
        }
        if (b) {
            this.zOrderList.putOnTop(metadata);
        }
    }
    
    public Vector getScenesInSwath(final Metadata metadata) {
        final Vector vector = new Vector();
        for (int i = 0; i < 9; ++i) {
            this.mosaicCells[i].getScenesOnDate(metadata.date, metadata.getSensor(), vector);
        }
        return vector;
    }
    
    private void setDisplaySize() {
        int n = 0;
        int n2 = 0;
        if (this.cellsToDisplay == 0) {
            final TOC toc = this.mosaicCells[this.activeCellIndex];
            if (toc.valid) {
                final Metadata metadata = toc.scenes[toc.currentDateIndex];
                if (metadata.image != null) {
                    n = metadata.image.getWidth(null);
                    n2 = metadata.image.getHeight(null);
                }
            }
        }
        else if (this.cellsToDisplay == 1) {
            final Dimension viewportPixels = this.mosaicCoords.getViewportPixels();
            n2 = viewportPixels.height;
            n = viewportPixels.width;
        }
        else {
            final Dimension mosaicPixels = this.mosaicCoords.getMosaicPixels();
            n = mosaicPixels.width;
            n2 = mosaicPixels.height;
        }
        if (n > 0 && n2 > 0) {
            this.displaySize = new Dimension(n, n2);
        }
    }
    
    public Dimension getDisplaySize() {
        return new Dimension(this.displaySize);
    }
    
    private void loadScenes() {
        this.applet.statusBar.showStatus("Loading Imagery...");
        if (!this.resolutionChangePending) {
            this.areImagesLoading = true;
            this.applet.updateBusyIndicators();
            this.imageLoader.loadImages(this.zOrderList, this.cellsToDisplay, this.pixelSize, this.currSensor);
        }
    }
    
    private void mosaicCoordsUpdate() {
        this.mosaicCoords.update(this.applet, this.mosaicCells, this.currSensor, this.pane.getParent().getMinimumSize(), this.proj, this.cellsToDisplay, this.activeCellIndex, this.actualPixelSize, this.subCol, this.subRow);
    }
    
    public void checkForCompletedLoad() {
        if (this.loadCompleted) {
            this.loadCompleted = false;
            if (this.tocChangePending) {
                this.tocChangePending = false;
                this.activateNewTocArray();
            }
            else {
                System.out.println("Bug in checkForCompletedLoad");
            }
        }
        if (this.areImagesLoading && !this.imageLoader.isBusy()) {
            this.areImagesLoading = false;
            this.setDisplaySize();
            this.setChanged();
            if (this.notifyType == 0) {
                this.notifyObservers();
            }
            else if (this.notifyType == 1) {
                this.notifyObservers(this.targetXY);
            }
            else if (this.notifyType == 2) {
                this.notifyObservers(MosaicData.DISPLAY_MODE_CHANGED);
            }
            else {
                System.out.println("Error - unhandled notify type");
            }
            this.applet.updateBusyIndicators();
            this.applet.statusBar.showStatus("");
        }
    }
    
    public boolean isBusy() {
        return this.isLoading || this.loadCompleted || this.areImagesLoading || this.resolutionChangePending;
    }
    
    public boolean isUnstableTOC() {
        return this.isLoading || this.loadCompleted;
    }
    
    public void initialize(final double n, final double n2) {
        final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
        final Point latLongToGrid = currentSensor.navModel.latLongToGrid(n, n2);
        this.gridCol = latLongToGrid.x;
        this.gridRow = latLongToGrid.y;
        final URL codeBase = this.applet.getCodeBase();
        int n3 = 0;
        for (int i = 1; i >= -1; --i) {
            for (int j = -1; j <= 1; ++j) {
                this.mosaicCells[n3] = new TOC(codeBase, this.gridCol + i, this.gridRow + j);
                ++n3;
            }
        }
        this.setSensor(currentSensor);
    }
    
    public void scrollInDirection(final int n, final int n2, final int n3, final int n4) {
        if (n2 + n + n3 + n4 == 0) {
            return;
        }
        int n5 = this.gridCol;
        int n6 = this.gridRow;
        int n7 = 0;
        int n8 = 0;
        final int rowDownDirection = this.currSensor.navModel.getRowDownDirection();
        final int columnRightDirection = this.currSensor.navModel.getColumnRightDirection();
        if (this.cellsToDisplay == 0) {
            n5 = this.mosaicCells[this.activeCellIndex].gridCol;
            n6 = this.mosaicCells[this.activeCellIndex].gridRow;
        }
        this.refScrollScene = null;
        if (this.currSensor.isFullMosaic) {
            this.refScrollScene = this.getCurrentScene();
        }
        int gridCol;
        int gridRow;
        if (this.cellsToDisplay == 1) {
            gridCol = this.gridCol;
            gridRow = this.gridRow;
            n7 = this.subCol + columnRightDirection * (n - n2);
            n8 = this.subRow + rowDownDirection * (n4 - n3);
            if (n7 >= 3) {
                n7 -= 3;
                ++gridCol;
            }
            else if (n7 <= -3) {
                n7 += 3;
                --gridCol;
            }
            if (n8 >= 3) {
                n8 -= 3;
                ++gridRow;
            }
            else if (n8 <= -3) {
                n8 += 3;
                --gridRow;
            }
        }
        else {
            gridCol = n5 + columnRightDirection * (n - n2);
            gridRow = n6 + rowDownDirection * (n4 - n3);
        }
        final NavigationModel navModel = this.currSensor.navModel;
        boolean b = false;
        if (!navModel.allowColumnWrapAround() && gridCol != navModel.checkColumnBounds(gridCol)) {
            b = true;
        }
        if (!navModel.allowRowWrapAround() && gridRow != navModel.checkRowBounds(gridRow)) {
            b = true;
        }
        if (b) {
            this.applet.statusBar.showStatus("Cannot move in that direction");
            return;
        }
        if (navModel.isValidGridCell(gridCol, gridRow)) {
            if (this.canMoveToMapArea(gridCol, gridRow)) {
                this.scrollData(gridCol, gridRow, n7, n8, false, true, false);
            }
        }
        else {
            this.applet.statusBar.showStatus(navModel.getColName() + "=" + gridCol + ", " + navModel.getRowName() + "=" + gridRow + " does not contain data!");
        }
    }
    
    public boolean canMoveToMapArea(final int n, final int n2) {
        boolean b = true;
        final GeographicLocatorMapConfig mapConfig = this.locatorMap.getMapConfig(this.currSensor.locatorMap);
        if (mapConfig != null && mapConfig.enforceGeographicBumper && this.currSensor.hasGeographicBumper) {
            boolean b2 = false;
            final NavigationModel navModel = this.currSensor.navModel;
            final LatLong gridToLatLong = navModel.gridToLatLong(n, n2);
            if (gridToLatLong.longitude > mapConfig.leftLon && gridToLatLong.latitude < mapConfig.topLat && gridToLatLong.longitude < mapConfig.rightLon && gridToLatLong.latitude > mapConfig.bottomLat) {
                b2 = true;
            }
            if (!b2) {
                b = false;
                this.applet.statusBar.showStatus(navModel.getColName() + "=" + navModel.getColumnString(n) + ", " + navModel.getRowName() + "=" + navModel.getRowString(n2) + " does not contain data!");
            }
        }
        return b;
    }
    
    private void cancelLoad() {
        if (this.isLoading) {
            this.isLoadCancelled = true;
        }
    }
    
    private void readTOCs(final TOC[] loadingMosaicCells, final boolean[] loadingUsed, final int loadingActiveCellIndex, final boolean loadingPreserveZOrder, final boolean isTargetXY) {
        this.applet.statusBar.showStatus("Querying Inventory...");
        synchronized (this.loadLock) {
            this.loadCompleted = false;
            this.isTargetXY = isTargetXY;
            this.loadingMosaicCells = loadingMosaicCells;
            this.loadingUsed = loadingUsed;
            this.loadingActiveCellIndex = loadingActiveCellIndex;
            this.loadingPreserveZOrder = loadingPreserveZOrder;
            this.tocChangePending = true;
            this.isLoadCancelled = false;
            this.isLoading = true;
            this.applet.updateBusyIndicators();
            this.loadLock.notify();
        }
    }
    
    public void killThread() {
        this.cancelLoad();
        this.imageLoader.killThread();
        synchronized (this.loadLock) {
            this.killThread = true;
            this.loadLock.notify();
        }
    }
    
    @Override
    public void run() {
        synchronized (this.loadLock) {
            while (true) {
                if (!this.isLoading) {
                    try {
                        this.loadLock.wait();
                    }
                    catch (InterruptedException ex) {}
                    if (this.killThread) {
                        break;
                    }
                    continue;
                }
                else {
                    this.numTocsToLoad = 0;
                    this.currTocLoading = 0;
                    for (int i = 0; i < this.loadingMosaicCells.length; ++i) {
                        if (!this.loadingMosaicCells[i].valid) {
                            ++this.numTocsToLoad;
                        }
                    }
                    for (int n = 0; n < this.loadingMosaicCells.length && !this.isLoadCancelled; ++n) {
                        if (!this.loadingMosaicCells[n].valid) {
                            if (this.applet.verboseOutput) {
                                System.out.println("Reading TOC at " + this.loadingMosaicCells[n].gridCol + " " + this.loadingMosaicCells[n].gridRow);
                            }
                            this.currSensor.readTOC(this.loadingMosaicCells[n]);
                            if (this.applet.slowdown) {
                                try {
                                    Thread.currentThread();
                                    Thread.sleep(400L);
                                }
                                catch (InterruptedException ex2) {}
                            }
                            ++this.currTocLoading;
                        }
                    }
                    this.imageLoader.waitUntilDone();
                    if (!this.isLoadCancelled) {
                        this.loadCompleted = true;
                        this.isLoading = false;
                        this.pane.repaint();
                    }
                    this.isLoading = false;
                }
            }
            this.loaderThread = null;
        }
    }
    
    public boolean gotoXY(final int x, final int y) {
        final NavigationModel navModel = this.currSensor.navModel;
        this.targetXY.x = x;
        this.targetXY.y = y;
        int n = this.gridCol;
        int n2 = this.gridRow;
        int subCol = this.subCol;
        int subRow = this.subRow;
        final double n3 = 0.3333333333333333;
        if (this.cellsToDisplay == 0) {
            n = this.mosaicCells[this.activeCellIndex].gridCol;
            n2 = this.mosaicCells[this.activeCellIndex].gridRow;
        }
        final Point upperLeftCorner = this.mosaicCoords.getUpperLeftCorner();
        final Dimension size = this.pane.getSize();
        final Dimension size2 = this.applet.imgScroll.getSize();
        final Point point = new Point((size.width - size2.width) / 2, (size.height - size2.height) / 2);
        final Point point2 = upperLeftCorner;
        point2.x += (int)Math.round(point.x * this.actualPixelSize);
        final Point point3 = upperLeftCorner;
        point3.y -= (int)Math.round(point.y * this.actualPixelSize);
        final DoublePoint projToDoubleGrid = navModel.projToDoubleGrid(upperLeftCorner, this.proj);
        final DoublePoint projToDoubleGrid2 = navModel.projToDoubleGrid(new Point(x, y), this.proj);
        final double n4 = projToDoubleGrid.x - (n + this.subCol * n3);
        final double n5 = projToDoubleGrid.y - (n2 + this.subRow * n3);
        final double n6 = projToDoubleGrid2.x - n4;
        final double n7 = projToDoubleGrid2.y - n5;
        final int n8 = (int)(n6 + 0.5);
        final int n9 = (int)(n7 + 0.5);
        if (this.cellsToDisplay == 1) {
            final double n10 = (n6 - n8) * 3.0;
            double n11;
            if (n10 >= 0.0) {
                n11 = n10 + 0.5;
            }
            else {
                n11 = n10 - 0.5;
            }
            subCol = (int)n11;
            final double n12 = (n7 - n9) * 3.0;
            double n13;
            if (n12 >= 0.0) {
                n13 = n12 + 0.5;
            }
            else {
                n13 = n12 - 0.5;
            }
            subRow = (int)n13;
        }
        final int checkColumnBounds = navModel.checkColumnBounds(n8);
        final int checkRowBounds = navModel.checkRowBounds(n9);
        if (!navModel.isValidGridCell(checkColumnBounds, checkRowBounds)) {
            this.applet.statusBar.showStatus("Selected tile does not contain data!");
            return false;
        }
        if (checkColumnBounds == n && checkRowBounds == n2 && subCol == this.subCol && subRow == this.subRow && (!this.currSensor.isFullMosaic || this.cellsToDisplay != 0)) {
            return false;
        }
        if (this.canMoveToMapArea(checkColumnBounds, checkRowBounds)) {
            this.scrollData(checkColumnBounds, checkRowBounds, subCol, subRow, false, true, true);
            return true;
        }
        return false;
    }
    
    public void gotoLatLong(final double n, final double n2) {
        final Point latLongToGrid = this.currSensor.navModel.latLongToGrid(n, n2);
        if (this.canMoveToMapArea(latLongToGrid.x, latLongToGrid.y)) {
            this.targetLatLong = new LatLong(n, n2);
            this.scrollData(latLongToGrid.x, latLongToGrid.y, 0, 0, false, true, false);
        }
    }
    
    public void showScene(final Metadata metadata) {
        this.applet.searchLimitDialog.applySearchLimits(metadata);
        if (!metadata.visible) {
            JOptionPane.showMessageDialog(this.applet.getDialogContainer(), "Scene " + metadata.entityID + " is filtered out by the search limits", "Scene filtered out", 2);
            return;
        }
        boolean b = true;
        final int colRowToCell = this.colRowToCell(metadata.gridCol, metadata.gridRow);
        if (colRowToCell != -1) {
            final TOC toc = this.mosaicCells[colRowToCell];
            final int scene = toc.findScene(metadata);
            if (scene >= 0) {
                if (toc.scenes[scene].visible) {
                    this.activeCellIndex = colRowToCell;
                    this.zOrderList.putOnTop(toc.scenes[scene]);
                    this.drawNewDate(metadata.gridCol, metadata.gridRow, scene);
                    b = false;
                }
            }
            else {
                System.out.println("Bug!  scene not found in inventory");
            }
        }
        if (b) {
            this.targetScene = metadata;
            this.targetDateScene = metadata;
            this.scrollData(metadata.gridCol, metadata.gridRow, 0, 0, false, true, false);
        }
    }
    
    public void scrollData(int checkColumnBounds, int checkRowBounds, final int subCol, final int subRow, final boolean b, final boolean b2, final boolean b3) {
        this.cancelLoad();
        this.imageLoader.cancelLoad();
        this.mapLayers.cancelLoad();
        this.isCalledFromScrolledData = true;
        final NavigationModel navModel = this.currSensor.navModel;
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        checkColumnBounds = navModel.checkColumnBounds(checkColumnBounds);
        final int columnRightDirection = navModel.getColumnRightDirection();
        for (int i = 0; i < 3; ++i) {
            array2[i] = checkColumnBounds - columnRightDirection * (1 - i);
            if (navModel.allowColumnWrapAround()) {
                array2[i] = navModel.checkColumnBounds(array2[i]);
            }
        }
        checkRowBounds = navModel.checkRowBounds(checkRowBounds);
        final int rowDownDirection = navModel.getRowDownDirection();
        for (int j = 0; j < 3; ++j) {
            array[j] = checkRowBounds - rowDownDirection * (1 - j);
            if (navModel.allowRowWrapAround()) {
                array[j] = navModel.checkRowBounds(array[j]);
            }
        }
        final boolean[] array3 = new boolean[this.mosaicCells.length];
        final TOC[] array4 = new TOC[this.mosaicCells.length];
        if (b2) {
            for (int k = 0; k < this.mosaicCells.length; ++k) {
                array4[k] = null;
                final int colRowToCell = this.colRowToCell(array2[k / 3], array[k % 3]);
                if (colRowToCell != -1) {
                    array4[k] = this.mosaicCells[colRowToCell];
                    array3[colRowToCell] = true;
                }
            }
        }
        final URL codeBase = this.applet.getCodeBase();
        for (int l = 0; l < array4.length; ++l) {
            if (array4[l] == null) {
                array4[l] = new TOC(codeBase, array2[l / 3], array[l % 3]);
            }
        }
        this.gridCol = checkColumnBounds;
        this.gridRow = checkRowBounds;
        this.subCol = subCol;
        this.subRow = subRow;
        int activeCellIndex = 4;
        if (b) {
            activeCellIndex = this.activeCellIndex;
        }
        else {
            this.zOrderList.empty();
        }
        this.readTOCs(array4, array3, activeCellIndex, b, b3);
    }
    
    public void refreshDisplay() {
        this.scrollData(this.gridCol, this.gridRow, this.subCol, this.subRow, true, false, false);
    }
    
    public void updateDisplay() {
        this.scrollData(this.gridCol, this.gridRow, this.subCol, this.subRow, false, true, false);
    }
    
    private void activateNewTocArray() {
        if (this.loadingPreserveZOrder && this.currSensor.isFullMosaic) {
            final ZOrderList zOrderList = new ZOrderList();
            zOrderList.setMultipleSceneMode();
            this.zOrderList.top();
            int n = 1;
            Metadata down;
            while ((down = this.zOrderList.down()) != null) {
                final int colRowToCell = this.colRowToCell(down.gridCol, down.gridRow);
                final TOC toc = this.loadingMosaicCells[colRowToCell];
                final int scene = toc.findScene(down);
                zOrderList.putOnBottom(toc.scenes[scene]);
                if (n != 0) {
                    this.loadingMosaicCells[colRowToCell].currentDateIndex = scene;
                    n = 0;
                }
            }
            this.zOrderList = zOrderList;
        }
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            if (!this.loadingUsed[i]) {
                this.mosaicCells[i].cleanup();
                this.mosaicCells[i] = null;
            }
        }
        this.loadingUsed = null;
        this.mosaicCells = this.loadingMosaicCells;
        this.loadingMosaicCells = null;
        this.activeCellIndex = this.loadingActiveCellIndex;
        this.projectionCode = this.pickPreferredProjCode();
        if (this.projectionCode == 1100) {
            this.projectionCode = CreateProjection.getDefaultProjectionCode(this.currSensor, this.currSensor.navModel.gridToLatLong(this.gridCol, this.gridRow));
        }
        this.proj = CreateProjection.fromProjectionNumber(this.projectionCode);
        for (int j = 0; j < this.mosaicCells.length; ++j) {
            final TOC toc2 = this.mosaicCells[j];
            if (toc2.projCode != this.projectionCode) {
                toc2.valid = false;
            }
            toc2.setSceneCorners(this.proj);
        }
        final int startYear = this.applet.searchLimitDialog.getStartYear();
        final int endYear = this.applet.searchLimitDialog.getEndYear();
        final int startMonth = this.applet.searchLimitDialog.getStartMonth();
        final int endMonth = this.applet.searchLimitDialog.getEndMonth();
        final boolean sceneListFilterEnabled = this.applet.searchLimitDialog.isSceneListFilterEnabled();
        final int minQuality = this.applet.searchLimitDialog.getMinQuality();
        final String dataVersion = this.applet.searchLimitDialog.getDataVersion();
        final boolean userDefinedAreaEnabled = this.applet.searchLimitDialog.isUserDefinedAreaEnabled();
        final int startGridCol = this.applet.searchLimitDialog.getStartGridCol();
        final int endGridCol = this.applet.searchLimitDialog.getEndGridCol();
        final int startGridRow = this.applet.searchLimitDialog.getStartGridRow();
        final int endGridRow = this.applet.searchLimitDialog.getEndGridRow();
        for (int k = 0; k < this.mosaicCells.length; ++k) {
            this.mosaicCells[k].filterScenesToCloudCover(this.maxCloudCover);
            this.mosaicCells[k].filterScenesToDateRange(startYear, endYear, startMonth, endMonth);
            this.mosaicCells[k].filterScenesToSceneList(sceneListFilterEnabled);
            this.mosaicCells[k].filterScenesToQuality(minQuality);
            this.mosaicCells[k].filterScenesToDataVersion(dataVersion);
            this.mosaicCells[k].filterToHiddenScene();
            this.mosaicCells[k].filterScenesToGridColRowRange(startGridCol, endGridCol, startGridRow, endGridRow);
        }
        if (this.targetLatLong != null) {
            if (this.currSensor.isFullMosaic && this.cellsToDisplay == 0) {
                final Point latLongToProj = this.proj.latLongToProj(this.targetLatLong);
                if (latLongToProj != null) {
                    final TOC toc3 = this.mosaicCells[this.activeCellIndex];
                    if (toc3.valid) {
                        double n2 = 1.0E12;
                        int n3 = -1;
                        for (int l = 0; l < toc3.numImg; ++l) {
                            final Metadata metadata = toc3.scenes[l];
                            if (metadata.visible) {
                                final double n4 = metadata.centerXY.x - latLongToProj.x;
                                final double n5 = metadata.centerXY.y - latLongToProj.y;
                                final double n6 = n4 * n4 + n5 * n5;
                                if (n6 < n2) {
                                    n3 = l;
                                    n2 = n6;
                                }
                            }
                        }
                        if (n3 != -1) {
                            this.targetScene = toc3.scenes[n3];
                        }
                    }
                }
            }
            this.targetLatLong = null;
        }
        final Metadata targetScene = this.targetScene;
        this.pickSceneDates();
        this.mosaicCoordsUpdate();
        this.targetScene = targetScene;
        for (int n7 = 0; n7 < this.mosaicCells.length; ++n7) {
            this.mosaicCells[n7].filterScenesToUserArea(userDefinedAreaEnabled, this.applet.userDefinedAreaDialog);
        }
        this.pickSceneDates();
        this.sceneFilter = this.currSensor.getSceneFilter(this, this.mosaicCells);
        if (this.loadingPreserveZOrder) {
            if (!this.currSensor.isFullMosaic) {
                this.rebuildZOrder();
            }
        }
        else {
            this.setDefaultZOrder();
        }
        this.loadScenes();
        this.setDisplaySize();
        this.setSelectedCell(this.activeCellIndex, this.loadingPreserveZOrder);
        if (this.resolutionChangePending) {
            this.resolutionChangePending = false;
            this.setResolution(this.pixelSize);
        }
        this.applet.updateBusyIndicators();
    }
    
    public void setResolution(final int pixelSize) {
        this.pixelSize = pixelSize;
        this.actualPixelSize = this.currSensor.getActualResolution(this.pixelSize);
        this.cellsToDisplay = this.currSensor.getNumCellsAtResolution(this.pixelSize);
        this.imageLoader.cancelLoad();
        this.mapLayers.cancelLoad();
        if (this.tocChangePending) {
            this.resolutionChangePending = true;
            return;
        }
        this.subCol = 0;
        this.subRow = 0;
        if (this.cellsToDisplay == 1) {
            final TOC toc = this.mosaicCells[this.activeCellIndex];
            this.scrollData(toc.gridCol, toc.gridRow, 0, 0, false, true, false);
        }
        else {
            for (int i = 0; i < this.mosaicCells.length; ++i) {
                this.mosaicCells[i].clearSceneFilters(1);
            }
            final TOC toc2 = this.mosaicCells[this.activeCellIndex];
            if (toc2.valid) {
                final Metadata defaultToSelectedDate = toc2.scenes[toc2.currentDateIndex];
                if (this.applet.toolsMenu.isDefaultToDateEnabled()) {
                    this.setDefaultToSelectedDate(defaultToSelectedDate);
                }
                this.zOrderList.putOnTop(defaultToSelectedDate);
                if (this.currSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) {
                    this.buildSwath(defaultToSelectedDate, true);
                }
            }
            this.mosaicCoordsUpdate();
            this.loadScenes();
            this.setDisplaySize();
            this.sceneFilter.filter();
            this.notifyType = 2;
            this.setChanged();
            this.notifyObservers(MosaicData.DISPLAY_MODE_CHANGED);
        }
    }
    
    public int colRowToCell(final int n, final int n2) {
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            final TOC toc = this.mosaicCells[i];
            if (toc != null && toc.valid && toc.gridCol == n && toc.gridRow == n2) {
                return i;
            }
        }
        return -1;
    }
    
    public TOC getCellForScene(final Metadata metadata) {
        final int colRowToCell = this.colRowToCell(metadata.gridCol, metadata.gridRow);
        TOC toc = null;
        if (colRowToCell >= 0) {
            toc = this.mosaicCells[colRowToCell];
        }
        return toc;
    }
    
    public TOC getCurrentCell() {
        return this.mosaicCells[this.activeCellIndex];
    }
    
    public Metadata getCurrentScene() {
        final TOC toc = this.mosaicCells[this.activeCellIndex];
        if (toc == null || !toc.valid) {
            return null;
        }
        final Metadata metadata = toc.scenes[toc.currentDateIndex];
        if (metadata.visible) {
            return metadata;
        }
        return null;
    }
    
    private void loadScene(final int n, final int n2) {
        final TOC toc = this.mosaicCells[n];
        final Metadata metadata = toc.scenes[n2];
        this.dateCache.add(toc.gridCol, toc.gridRow, metadata.date);
        if (metadata.image == null || metadata.imageRes != this.pixelSize) {
            if (this.cellsToDisplay == 0) {
                this.mosaicCoords.updateDisplayedUL(metadata.ulX, metadata.ulY, toc.valid);
            }
            this.areImagesLoading = true;
            this.applet.statusBar.showStatus("Loading Imagery...");
            this.applet.updateBusyIndicators();
            this.imageLoader.loadImages(this.zOrderList, this.cellsToDisplay, this.pixelSize, this.currSensor);
        }
    }
    
    public void drawNewDate(final int n, final int n2, final int n3) {
        if (this.isLoading || this.tocChangePending) {
            return;
        }
        final int colRowToCell = this.colRowToCell(n, n2);
        if (colRowToCell != -1) {
            final TOC toc = this.mosaicCells[colRowToCell];
            if (!this.currSensor.isFullMosaic || this.cellsToDisplay == 0) {
                this.flushCurrentDateIndex(toc);
                toc.currentDateIndex = n3;
                this.flushCurrentDateIndex(toc);
            }
            toc.currentDateIndex = n3;
            final Sensor currentSensor = this.applet.sensorMenu.getCurrentSensor();
            if (currentSensor.isFullMosaic) {
                this.activeCellIndex = colRowToCell;
            }
            if (toc.valid) {
                final Metadata defaultToSelectedDate = toc.scenes[toc.currentDateIndex];
                if (currentSensor.hasSwathMode && this.applet.toolsMenu.isSwathModeEnabled()) {
                    this.buildSwath(defaultToSelectedDate, this.activeCellIndex == colRowToCell);
                }
                if (currentSensor.isFullMosaic) {
                    this.zOrderList.putOnTop(defaultToSelectedDate);
                }
                else if (this.applet.toolsMenu.isDefaultToDateEnabled()) {
                    this.setDefaultToSelectedDate(defaultToSelectedDate);
                    this.rebuildZOrder();
                }
                else {
                    this.zOrderList.changeScene(defaultToSelectedDate);
                }
                this.loadScene(colRowToCell, toc.currentDateIndex);
                if (this.cellsToDisplay == 0) {
                    this.mapLayers.clip();
                }
                this.notifyType = 0;
                this.setChanged();
                this.notifyObservers();
            }
        }
    }
    
    public Metadata findSceneAt(final int n, final int n2) {
        Metadata metadata = null;
        if (this.cellsToDisplay != 0) {
            this.zOrderList.top();
            Metadata down;
            while ((down = this.zOrderList.down()) != null) {
                if (!down.visible) {
                    continue;
                }
                if (down.screenLocation.contains(n, n2)) {
                    metadata = down;
                    break;
                }
            }
        }
        else {
            final TOC toc = this.mosaicCells[this.activeCellIndex];
            if (toc.valid) {
                metadata = toc.scenes[toc.currentDateIndex];
            }
        }
        return metadata;
    }
    
    public Vector findScenesAt(final int n, final int n2) {
        final Vector<Metadata> vector = new Vector<Metadata>();
        if (this.cellsToDisplay == 0) {
            final Metadata scene = this.findSceneAt(n, n2);
            if (scene != null) {
                vector.addElement(scene);
            }
        }
        else {
            this.zOrderList.top();
            Metadata down;
            while ((down = this.zOrderList.down()) != null) {
                if (!down.visible) {
                    continue;
                }
                if (!down.screenLocation.contains(n, n2)) {
                    continue;
                }
                vector.addElement(down);
            }
        }
        return vector;
    }
    
    public LatLong getLatLong(final Point point) {
        return this.proj.projToLatLong(point.x, point.y);
    }
    
    public ProjectionTransformation getProjection() {
        return this.proj;
    }
    
    public TOC[] getMosaicCells() {
        return this.mosaicCells;
    }
    
    public void setCCLimit(final int n) {
        this.maxCloudCover = n;
        this.applet.searchLimitDialog.setCloudCover(n);
        this.applet.sceneListDialog.updateForChangedSearchLimits();
        if (this.isUnstableTOC()) {
            return;
        }
        this.imageLoader.cancelLoad();
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            this.mosaicCells[i].filterScenesToCloudCover(this.maxCloudCover);
            this.mosaicCells[i].findClosestVisibleDate();
        }
        this.pickSwathScenes();
        this.rebuildZOrder();
        this.sceneFilter.filter();
        this.mosaicCoordsUpdate();
        this.loadScenes();
        this.notifyType = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getCellsToDisplay() {
        return this.cellsToDisplay;
    }
    
    public int getActiveCellIndex() {
        return this.activeCellIndex;
    }
    
    public int getSubCol() {
        return this.subCol;
    }
    
    public int getSubRow() {
        return this.subRow;
    }
    
    public TOC[] copyTOC() {
        final TOC[] array = new TOC[9];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new TOC(this.mosaicCells[i]);
        }
        return array;
    }
    
    public void applyDataLimit(final TOC[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final String s, final boolean b, final boolean b2, final int n7, final int n8, final int n9, final int n10) {
        for (int i = 0; i < array.length; ++i) {
            array[i].filterScenesToDateRange(n, n2, n3, n4);
            array[i].filterScenesToCloudCover(n5);
            array[i].filterScenesToSceneList(b2);
            array[i].filterScenesToQuality(n6);
            array[i].filterScenesToDataVersion(s);
            array[i].filterToHiddenScene();
            array[i].filterScenesToUserArea(b, this.applet.userDefinedAreaDialog);
            array[i].filterScenesToGridColRowRange(n7, n8, n9, n10);
        }
    }
    
    public void setSceneToClosestDate(final Metadata metadata, final int n, final int n2) {
        final TOC toc = this.mosaicCells[this.colRowToCell(metadata.gridCol, metadata.gridRow)];
        final int date = toc.findDate(n2, n);
        if (date != -1) {
            this.drawNewDate(toc.gridCol, toc.gridRow, date);
        }
    }
    
    public void setSearchLimitValues(final int n, final int n2, final int n3, final int n4, final int maxCloudCover, final int n5, final String s, final int n6, final int n7, final int n8, final int n9) {
        this.maxCloudCover = maxCloudCover;
        final boolean sceneListFilterEnabled = this.applet.searchLimitDialog.isSceneListFilterEnabled();
        final boolean userDefinedAreaEnabled = this.applet.searchLimitDialog.isUserDefinedAreaEnabled();
        this.applet.maxCC.setCloudCover(this.maxCloudCover);
        this.applet.sceneListDialog.updateForChangedSearchLimits();
        if (this.isUnstableTOC()) {
            return;
        }
        this.imageLoader.cancelLoad();
        this.mosaicCoordsUpdate();
        this.applyDataLimit(this.mosaicCells, n, n2, n3, n4, this.maxCloudCover, n5, s, userDefinedAreaEnabled, sceneListFilterEnabled, n6, n7, n8, n9);
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            this.mosaicCells[i].findClosestVisibleDate();
        }
        this.pickSwathScenes();
        this.rebuildZOrder();
        this.sceneFilter.filter();
        this.mosaicCoordsUpdate();
        this.loadScenes();
        this.notifyType = 0;
        this.setChanged();
        this.notifyObservers();
    }
    
    private void rebuildZOrder() {
        if (this.currSensor.isFullMosaic) {
            this.zOrderList.top();
            final Metadata down = this.zOrderList.down();
            if (down != null && !down.visible) {
                Metadata down2;
                while ((down2 = this.zOrderList.down()) != null) {
                    if (down2.visible) {
                        this.zOrderList.putOnTop(down2);
                        this.activeCellIndex = this.colRowToCell(down2.gridCol, down2.gridRow);
                        final TOC toc = this.mosaicCells[this.activeCellIndex];
                        toc.currentDateIndex = toc.findScene(down2);
                        break;
                    }
                }
            }
        }
        else {
            final ZOrderList zOrderList = new ZOrderList();
            zOrderList.setSingleSceneMode();
            this.zOrderList.top();
            Metadata down3;
            while ((down3 = this.zOrderList.down()) != null) {
                final TOC toc2 = this.mosaicCells[this.colRowToCell(down3.gridCol, down3.gridRow)];
                zOrderList.putOnBottom(toc2.scenes[toc2.currentDateIndex]);
            }
            this.zOrderList = zOrderList;
        }
    }
    
    public ZOrderList getZOrder() {
        if (this.cellsToDisplay == 0) {
            this.zOrderList.top();
            this.zOrderList.down();
            if (this.zOrderList.up() == null) {
                this.zOrderList.bottom();
            }
            else {
                this.zOrderList.top();
                this.zOrderList.down();
            }
        }
        else {
            this.zOrderList.bottom();
        }
        return this.zOrderList;
    }
    
    public boolean canDisplay(final Metadata metadata) {
        if (!metadata.visible) {
            return false;
        }
        if (this.cellsToDisplay == 0) {
            final TOC toc = this.mosaicCells[this.activeCellIndex];
            if (metadata.gridRow != toc.gridRow || metadata.gridCol != toc.gridCol) {
                return false;
            }
        }
        return true;
    }
    
    public boolean canShowHighlight() {
        return this.cellsToDisplay != 0;
    }
    
    public void cleanup() {
        for (int i = 0; i < this.mosaicCells.length; ++i) {
            if (this.mosaicCells[i] != null) {
                this.mosaicCells[i].cleanup();
            }
        }
    }
    
    public int getMosaicSize() {
        return 9;
    }
    
    public int getMosaicWidth() {
        return 3;
    }
    
    public int getMosaicHeight() {
        return 3;
    }
    
    public int getMosaicCenterIndex() {
        return 4;
    }
    
    static {
        DISPLAY_MODE_CHANGED = new Object();
    }
}
