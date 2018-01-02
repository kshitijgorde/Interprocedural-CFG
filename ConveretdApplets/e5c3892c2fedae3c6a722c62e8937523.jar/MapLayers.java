import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class MapLayers implements Runnable, WorkMonitor, MapLayerLoadingCallback
{
    public boolean layersOn;
    private ImagePane imagePane;
    private imgViewer applet;
    private MapLayer[] layers;
    private int[] drawOrder;
    private boolean needClipping;
    private boolean areLayersValid;
    private int projCode;
    private Thread loadThread;
    private boolean isLoading;
    private CancelLoad isLoadCancelled;
    private boolean isLoadingFiles;
    private boolean killThread;
    private int numMapFilesToLoad;
    private int currMapFileLoading;
    private PointOfInterestMapLayer pointOfInterest;
    private AddressSearchMapLayer addressSearchMapLayer;
    private int normalLayerCount;
    private ShapeFileAttributesDialog attributesDialog;
    private ModifyLayerColorsDialog colorsDialog;
    private boolean disableExtraLayerDrawing;
    private SceneOverlayMapLayer sceneOverlayMapLayer;
    private PendingLoad pendingLoad;
    
    public MapLayers(final imgViewer applet, final ImagePane imagePane) {
        this.layersOn = false;
        this.drawOrder = new int[] { 9, 10, 12, 1, 4, 11, 3, 8, 7, 2, 5, 0, 6 };
        this.isLoadCancelled = new CancelLoad();
        this.applet = applet;
        this.imagePane = imagePane;
        this.layers = new MapLayer[13];
        this.addressSearchMapLayer = new AddressSearchMapLayer(applet, 68);
        this.layers[0] = this.addressSearchMapLayer;
        (this.layers[1] = new LineMapLayer(applet, "Admin Boundaries", "Political", Color.WHITE, 1.5f, false, true, 65, false)).setLayerOn(false);
        (this.layers[2] = new WorldCitiesMapLayer(applet, Color.YELLOW, 67)).setLayerOn(false);
        (this.layers[3] = new GridMapLayer(applet, new Color(255, 226, 55), 71)).setLayerOn(false);
        (this.layers[4] = new LineMapLayer(applet, "Country Boundaries", "Country", Color.WHITE, 3.0f, false, true, 66, true)).setLayerOn(false);
        (this.layers[5] = new NorthArrowMapLayer(applet, 78)).setLayerOn(false);
        this.pointOfInterest = new PointOfInterestMapLayer(applet, 73);
        (this.layers[6] = this.pointOfInterest).setLayerOn(false);
        (this.layers[7] = new LineMapLayer(applet, "Protected Area Points", "ProtPoint", Color.YELLOW, 1.0f, true, false, 79, false)).setLayerOn(false);
        (this.layers[8] = new LineMapLayer(applet, "Protected Area Polygons", "ProtArea", Color.YELLOW, 1.0f, true, false, 80, false)).setLayerOn(false);
        (this.layers[9] = new LineMapLayer(applet, "Railroads", "Railroad", new Color(255, 0, 153), 1.0f, false, false, 76, false)).setLayerOn(false);
        (this.layers[10] = new LineMapLayer(applet, "Roads", "Road", new Color(255, 204, 153), 1.0f, false, false, 82, false)).setLayerOn(false);
        this.sceneOverlayMapLayer = new SceneOverlayMapLayer(applet, new Color(0, 255, 0), 86);
        (this.layers[11] = this.sceneOverlayMapLayer).setLayerOn(true);
        (this.layers[12] = new LineMapLayer(applet, "Water", "Water", new Color(0, 192, 227), 1.0f, false, false, 87, true)).setLayerOn(false);
        this.normalLayerCount = 13;
        this.attributesDialog = new ShapeFileAttributesDialog(this.applet.getDialogParent());
        this.colorsDialog = new ModifyLayerColorsDialog(this.applet, this.layers);
        this.pendingLoad = new PendingLoad();
        (this.loadThread = new Thread(this, "Map Layer Loader")).start();
    }
    
    public void cleanup() {
        this.attributesDialog.dispose();
        this.attributesDialog = null;
        this.sceneOverlayMapLayer.cleanup();
        this.colorsDialog.dispose();
        this.colorsDialog = null;
    }
    
    public void showAttributes() {
        final Point dialogLoc;
        final Point location = dialogLoc = this.applet.getDialogLoc();
        dialogLoc.y += 30;
        this.attributesDialog.setLocation(location);
        this.attributesDialog.setVisible(true);
    }
    
    public ShapeFileAttributesDialog getAttributesDialog() {
        return this.attributesDialog;
    }
    
    public void showSceneOverlayConfiguration() {
        this.sceneOverlayMapLayer.showConfigureDialog();
    }
    
    public void showModifyLayerColors() {
        if (!this.colorsDialog.isVisible()) {
            this.colorsDialog.setColors();
        }
        final Point dialogLoc;
        final Point location = dialogLoc = this.applet.getDialogLoc();
        dialogLoc.y += 30;
        this.colorsDialog.setLocation(location);
        this.colorsDialog.setVisible(true);
    }
    
    public void disableExtraMapLayers(final boolean disableExtraLayerDrawing) {
        if (this.layers.length > this.normalLayerCount && this.layersOn) {
            this.disableExtraLayerDrawing = disableExtraLayerDrawing;
            this.applet.imgArea.repaint();
        }
    }
    
    @Override
    public String getWorkLabel() {
        return "Loading Map Layers";
    }
    
    @Override
    public boolean isWorking() {
        return this.isBusy();
    }
    
    @Override
    public int getTotalWork() {
        return this.numMapFilesToLoad;
    }
    
    @Override
    public int getWorkComplete() {
        return this.currMapFileLoading;
    }
    
    @Override
    public void incrementFileReadCounter() {
        ++this.currMapFileLoading;
    }
    
    public int getNumberOfLayers() {
        return this.layers.length;
    }
    
    public int getNumberOfStandardLayers() {
        return this.normalLayerCount;
    }
    
    public MapLayer getLayerAt(final int n) {
        return this.layers[n];
    }
    
    public int addMapLayer(final MapLayer mapLayer) {
        int numberOfLayers = this.getNumberOfLayers();
        final MapLayer[] layers = new MapLayer[++numberOfLayers];
        final int[] drawOrder = new int[numberOfLayers];
        final int n = numberOfLayers - 1;
        for (int i = 0; i < n; ++i) {
            layers[i] = this.layers[i];
            drawOrder[i] = this.drawOrder[i];
        }
        layers[n] = mapLayer;
        drawOrder[n] = n;
        this.drawOrder = drawOrder;
        this.layers = layers;
        return n;
    }
    
    public PointOfInterestMapLayer getPointOfInterestMapLayer() {
        return this.pointOfInterest;
    }
    
    public AddressSearchMapLayer getAddressSearchMapLayer() {
        return this.addressSearchMapLayer;
    }
    
    public void showLayers() {
        this.layersOn = true;
        this.disableExtraLayerDrawing = false;
        this.startLoad(this.areLayersValid);
    }
    
    public void clearLayers() {
        this.layersOn = false;
        this.disableExtraLayerDrawing = false;
        this.imagePane.clear();
        this.imagePane.repaint();
    }
    
    public void cancelLoad() {
        if (this.isLoading) {
            this.isLoadCancelled.cancelled = true;
        }
    }
    
    public boolean isBusy() {
        return this.isLoadingFiles;
    }
    
    public void load(final Point ulMeters, final Point lrMeters, final ProjectionTransformation projectionTransformation, final int projCode) {
        final LatLong minboxULdeg = new LatLong(0.0, 0.0);
        final LatLong minboxLRdeg = new LatLong(0.0, 0.0);
        if (projCode != 1010) {
            MinBox.calculateMinBox(ulMeters, lrMeters, projectionTransformation, minboxULdeg, minboxLRdeg);
        }
        synchronized (this.pendingLoad) {
            boolean b = false;
            if (this.isLoading && (minboxULdeg.latitude != this.pendingLoad.minboxULdeg.latitude || minboxULdeg.longitude != this.pendingLoad.minboxULdeg.longitude || minboxLRdeg.latitude != this.pendingLoad.minboxLRdeg.latitude || minboxLRdeg.longitude != this.pendingLoad.minboxLRdeg.longitude || ulMeters.x != this.pendingLoad.ulMeters.x || ulMeters.y != this.pendingLoad.ulMeters.y || lrMeters.x != this.pendingLoad.lrMeters.x || lrMeters.y != this.pendingLoad.lrMeters.y || projCode != this.pendingLoad.projCode)) {
                b = true;
            }
            this.pendingLoad.minboxULdeg = minboxULdeg;
            this.pendingLoad.minboxLRdeg = minboxLRdeg;
            this.pendingLoad.ulMeters = ulMeters;
            this.pendingLoad.lrMeters = lrMeters;
            this.pendingLoad.projCode = projCode;
            this.pendingLoad.areLayersValid = false;
            this.pendingLoad.pending = true;
            if (this.isLoading && b) {
                this.isLoadCancelled.cancelled = true;
            }
            this.pendingLoad.notify();
        }
    }
    
    private void startLoad(final boolean b) {
        if (this.pendingLoad.ulMeters == null) {
            return;
        }
        synchronized (this.pendingLoad) {
            if (!this.pendingLoad.pending) {
                this.pendingLoad.areLayersValid = (b && this.areLayersValid);
                this.pendingLoad.pending = true;
            }
            this.needClipping = true;
            if (this.isLoading) {
                this.isLoadCancelled.cancelled = true;
            }
            if (this.layersOn) {
                this.pendingLoad.notify();
            }
        }
    }
    
    public void killThread() {
        synchronized (this.pendingLoad) {
            this.cancelLoad();
            this.killThread = true;
            this.pendingLoad.notify();
        }
    }
    
    @Override
    public void run() {
        Point ulMeters = new Point(0, 0);
        Point lrMeters = new Point(0, 0);
        while (true) {
            LatLong minboxULdeg = null;
            LatLong minboxLRdeg = null;
            while (!this.isLoading) {
                synchronized (this.pendingLoad) {
                    if (!this.pendingLoad.pending && !this.killThread) {
                        try {
                            this.pendingLoad.wait();
                        }
                        catch (InterruptedException ex) {}
                    }
                    if (this.pendingLoad.pending) {
                        minboxULdeg = this.pendingLoad.minboxULdeg;
                        minboxLRdeg = this.pendingLoad.minboxLRdeg;
                        ulMeters = this.pendingLoad.ulMeters;
                        lrMeters = this.pendingLoad.lrMeters;
                        this.projCode = this.pendingLoad.projCode;
                        this.areLayersValid = this.pendingLoad.areLayersValid;
                        this.isLoading = true;
                        this.isLoadCancelled.cancelled = false;
                        this.pendingLoad.pending = false;
                    }
                    if (this.killThread) {
                        this.loadThread = null;
                        return;
                    }
                    continue;
                }
            }
            int numMapFilesToLoad = 0;
            this.currMapFileLoading = 0;
            this.numMapFilesToLoad = 0;
            for (int i = 0; i < this.layers.length; ++i) {
                if (this.layers[i].isLayerOn()) {
                    if (this.projCode != 1010) {
                        numMapFilesToLoad += this.layers[i].setDisplayAreaUsingLatLong(minboxULdeg, minboxLRdeg, this.projCode);
                    }
                    else {
                        numMapFilesToLoad += this.layers[i].setDisplayAreaUsingProjCoords(ulMeters, lrMeters, this.projCode);
                    }
                }
            }
            if ((this.numMapFilesToLoad = numMapFilesToLoad) > 0) {
                this.isLoadingFiles = true;
            }
            if (this.isLoadingFiles) {
                this.applet.updateBusyIndicators();
            }
            for (int j = 0; j < this.layers.length; ++j) {
                if (this.layers[j].isLayerOn()) {
                    this.layers[j].read(this.isLoadCancelled, ulMeters, this.projCode, this);
                    if (this.isLoadCancelled.cancelled) {
                        if (this.applet.verboseOutput) {
                            System.out.println("Linework load cancelled");
                        }
                        this.isLoadCancelled.cancelled = false;
                        this.isLoading = false;
                        this.isLoadingFiles = false;
                        this.applet.updateBusyIndicators();
                        break;
                    }
                }
            }
            this.isLoading = false;
            this.isLoadingFiles = false;
            this.needClipping = true;
            this.areLayersValid = true;
            this.applet.updateBusyIndicators();
            this.imagePane.repaint();
        }
    }
    
    public void clip() {
        if (!this.areLayersValid) {
            return;
        }
        final Point upperLeftCorner = this.imagePane.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return;
        }
        this.needClipping = false;
        final ProjectionTransformation projection = this.imagePane.md.getProjection();
        final int pixelSize = this.imagePane.md.pixelSize;
        final Dimension size = this.imagePane.getSize();
        for (int i = 0; i < this.layers.length; ++i) {
            if (this.layers[i].isLayerOn()) {
                this.layers[i].clip(upperLeftCorner, pixelSize, size, projection);
            }
        }
    }
    
    public String findFeatureName(final int n, final int n2, final boolean b) {
        if (!this.areLayersValid) {
            return null;
        }
        if (this.layersOn) {
            MapLayerFeatureInfo mapLayerFeatureInfo = null;
            int n3 = -1;
            for (int i = this.layers.length - 1; i >= 0; --i) {
                final int n4 = this.drawOrder[i];
                if (this.layers[n4].isLayerOn()) {
                    final MapLayerFeatureInfo featureName = this.layers[n4].findFeatureName(n, n2);
                    if (featureName != null && (mapLayerFeatureInfo == null || featureName.area < mapLayerFeatureInfo.area)) {
                        mapLayerFeatureInfo = featureName;
                        n3 = n4;
                    }
                }
            }
            if (mapLayerFeatureInfo != null) {
                if (b) {
                    this.layers[n3].updateAttributeWindow();
                }
                return mapLayerFeatureInfo.name;
            }
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.areLayersValid) {
            return;
        }
        if (this.layersOn) {
            if (this.needClipping) {
                this.clip();
            }
            for (int i = 0; i < this.layers.length; ++i) {
                final int n = this.drawOrder[i];
                if (this.layers[n].isLayerOn()) {
                    if (n < this.normalLayerCount || !this.disableExtraLayerDrawing) {
                        this.layers[n].draw(graphics);
                    }
                }
            }
        }
    }
    
    private class PendingLoad
    {
        LatLong minboxULdeg;
        LatLong minboxLRdeg;
        Point ulMeters;
        Point lrMeters;
        int projCode;
        boolean areLayersValid;
        boolean pending;
    }
}
