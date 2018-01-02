import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import javax.swing.event.ListDataEvent;
import java.awt.Point;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import javax.swing.event.ListDataListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class SceneOverlayMapLayer extends MapLayer implements ListDataListener
{
    private imgViewer applet;
    private Vector sceneList;
    private boolean[] enabledSensors;
    private ConfigureSceneOverlayDialog configureDialog;
    
    public SceneOverlayMapLayer(final imgViewer applet, final Color color, final int n) {
        super(applet.imgArea, "Scene List Overlay", color, n, true);
        this.applet = applet;
        final Sensor[] sensors = applet.sensorMenu.getSensors();
        this.enabledSensors = new boolean[sensors.length];
        for (int i = 0; i < sensors.length; ++i) {
            sensors[i].sceneList.addListDataListener(this);
            if (!sensors[i].hasMultipleDatasets) {
                this.enabledSensors[i] = true;
            }
            else {
                this.enabledSensors[i] = false;
            }
        }
        this.sceneList = new Vector();
        this.configureDialog = new ConfigureSceneOverlayDialog(applet, this, this.enabledSensors);
    }
    
    public void showConfigureDialog() {
        if (!this.configureDialog.isVisible()) {
            this.configureDialog.setEnabledSensors(this.enabledSensors);
        }
        final Point dialogLoc;
        final Point location = dialogLoc = this.applet.getDialogLoc();
        dialogLoc.y += 30;
        this.configureDialog.setLocation(location);
        this.configureDialog.setVisible(true);
    }
    
    public void cleanup() {
        this.configureDialog.dispose();
        this.configureDialog = null;
    }
    
    public void updateShownSensors(final boolean[] array) {
        for (int i = 0; i < this.enabledSensors.length; ++i) {
            this.enabledSensors[i] = array[i];
        }
        this.setLayerOn(true);
        this.applet.mapLayerMenu.setLayerState(this.getName(), true, true);
        this.updateForSceneListChange();
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        return 0;
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        return 0;
    }
    
    @Override
    public void read(final CancelLoad cancelLoad, final Point point, final int n, final MapLayerLoadingCallback mapLayerLoadingCallback) {
    }
    
    @Override
    public void contentsChanged(final ListDataEvent listDataEvent) {
        this.updateForSceneListChange();
    }
    
    @Override
    public void intervalAdded(final ListDataEvent listDataEvent) {
        this.updateForSceneListChange();
    }
    
    @Override
    public void intervalRemoved(final ListDataEvent listDataEvent) {
        this.updateForSceneListChange();
    }
    
    private void updateForSceneListChange() {
        final ImagePane imgArea = this.applet.imgArea;
        final Point upperLeftCorner = imgArea.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return;
        }
        this.clip(upperLeftCorner, imgArea.md.pixelSize, imgArea.getSize(), imgArea.md.getProjection());
        this.applet.imgArea.repaint();
    }
    
    @Override
    public void clip(final Point point, final int n, final Dimension dimension, final ProjectionTransformation projectionTransformation) {
        this.sceneList.clear();
        if (!this.isLayerOn()) {
            return;
        }
        final double actualPixelSize = this.applet.imgArea.md.actualPixelSize;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final Rectangle2D.Double double1 = new Rectangle2D.Double(0.0, 0.0, dimension.width, dimension.height);
        final Sensor[] sensors = this.applet.sensorMenu.getSensors();
        for (int i = 0; i < sensors.length; ++i) {
            if (this.enabledSensors[i]) {
                final Sensor sensor = sensors[i];
                for (int sceneCount = sensor.sceneList.getSceneCount(), j = 0; j < sceneCount; ++j) {
                    final Metadata scene = sensor.sceneList.getSceneAt(j);
                    scene.getSceneCornersInProj(projectionTransformation, array, array2);
                    for (int k = 0; k < 4; ++k) {
                        final double n2 = (array[k] - point.x) / actualPixelSize;
                        final double n3 = (point.y - array2[k]) / actualPixelSize;
                        array[k] = (int)Math.round(n2);
                        array2[k] = (int)Math.round(n3);
                    }
                    final Polygon polygon = new Polygon(array, array2, 4);
                    if (polygon.intersects(double1)) {
                        final SceneInfo sceneInfo = new SceneInfo();
                        sceneInfo.polygon = polygon;
                        sceneInfo.name = sensor.buildShortEntityID(scene);
                        this.sceneList.add(sceneInfo);
                    }
                }
            }
        }
    }
    
    @Override
    public MapLayerFeatureInfo findFeatureName(final int n, final int n2) {
        double area = 1.0E11;
        String name = null;
        for (int size = this.sceneList.size(), i = 0; i < size; ++i) {
            final SceneInfo sceneInfo = this.sceneList.get(i);
            if (sceneInfo.polygon.contains(n, n2)) {
                final Rectangle bounds = sceneInfo.polygon.getBounds();
                final double n3 = bounds.width * bounds.height;
                if (n3 < area) {
                    area = n3;
                    name = sceneInfo.name;
                }
            }
        }
        if (name != null) {
            final MapLayerFeatureInfo mapLayerFeatureInfo = new MapLayerFeatureInfo();
            mapLayerFeatureInfo.name = name;
            mapLayerFeatureInfo.area = area;
            return mapLayerFeatureInfo;
        }
        return null;
    }
    
    @Override
    public void draw(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Stroke stroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(2.0f, 1, 1));
        graphics2D.setColor(Color.BLACK);
        graphics2D.translate(-1, 1);
        final int size = this.sceneList.size();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < size; ++j) {
                graphics.drawPolygon(((SceneInfo)this.sceneList.get(j)).polygon);
            }
            graphics2D.setColor(this.color);
            if (i == 0) {
                graphics2D.translate(1, -1);
            }
        }
        graphics2D.setStroke(stroke);
    }
    
    private class SceneInfo
    {
        public Polygon polygon;
        public String name;
    }
}
