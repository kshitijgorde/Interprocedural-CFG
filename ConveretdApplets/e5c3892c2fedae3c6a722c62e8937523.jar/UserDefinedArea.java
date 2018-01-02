import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;
import java.awt.Polygon;

// 
// Decompiled by Procyon v0.5.30
// 

public class UserDefinedArea
{
    private imgViewer applet;
    private MosaicData md;
    private UserDefinedAreaDialog userDefinedAreaDialog;
    private Polygon area;
    private Polygon displayArea;
    private boolean polygonIsClosed;
    private Stack undoStack;
    private LatLong startPos;
    private int selectedPointIndex;
    private int projectionCode;
    private PolygonState prevState;
    private PolygonIntersectTester intersectTester;
    private static final int THRESHOLD_FACTOR = 5;
    private Point prevUpperLeft;
    
    public UserDefinedArea(final imgViewer applet, final MosaicData md, final UserDefinedAreaDialog userDefinedAreaDialog) {
        this.applet = applet;
        this.md = md;
        this.userDefinedAreaDialog = userDefinedAreaDialog;
        this.area = new Polygon();
        this.undoStack = new Stack();
        this.polygonIsClosed = false;
        this.projectionCode = -1;
        this.prevUpperLeft = null;
    }
    
    private boolean addPoint(final Point point) {
        boolean b = false;
        if (this.area.npoints < 3) {
            if (this.area.npoints == 0) {
                this.startPos = this.md.getLatLong(point);
                this.area = new Polygon();
                this.undoStack = new Stack();
                final PolygonState polygonState = new PolygonState();
                polygonState.areaPolygon = new Polygon();
                polygonState.closedFlag = false;
                this.undoStack.push(polygonState);
                this.projectionCode = this.md.getProjectionCode();
            }
            this.area.addPoint(point.x, point.y);
            b = true;
        }
        else if (this.doesAddCauseIntersect(point)) {
            this.applet.statusBar.showStatus("Point causes invalid polygon");
        }
        else {
            this.area.addPoint(point.x, point.y);
            b = true;
        }
        return b;
    }
    
    private int whichPointSelected(final Point point) {
        if (this.area.npoints == 0) {
            return -1;
        }
        for (int i = 0; i < this.area.npoints; ++i) {
            if (this.findDistSquared(new Point(this.area.xpoints[i], this.area.ypoints[i]), point) < Math.pow(5.0 * this.md.actualPixelSize, 2.0)) {
                return i;
            }
        }
        return -1;
    }
    
    private double findDistSquared(final Point point, final Point point2) {
        return Math.pow(point.x - point2.x, 2.0) + Math.pow(point.y - point2.y, 2.0);
    }
    
    private boolean polygonClosed(final Point point) {
        if (this.polygonIsClosed) {
            return false;
        }
        if (this.area.npoints < 3) {
            return false;
        }
        final int npoints = this.area.npoints;
        if (this.selectedPointIndex == 0 && this.findDistSquared(point, new Point(this.area.xpoints[npoints - 1], this.area.ypoints[npoints - 1])) < Math.pow(5.0 * this.md.actualPixelSize, 2.0)) {
            this.polygonIsClosed = true;
            final Polygon area = this.area;
            --area.npoints;
            return true;
        }
        if (this.selectedPointIndex == npoints - 1 && this.findDistSquared(new Point(this.area.xpoints[0], this.area.ypoints[0]), point) < Math.pow(5.0 * this.md.actualPixelSize, 2.0)) {
            this.polygonIsClosed = true;
            final Polygon area2 = this.area;
            --area2.npoints;
            return true;
        }
        final int whichPointSelected = this.whichPointSelected(point);
        if (this.selectedPointIndex == 0 && whichPointSelected == 0) {
            final Point point2 = new Point();
            point2.x = this.prevState.areaPolygon.xpoints[this.selectedPointIndex];
            point2.y = this.prevState.areaPolygon.ypoints[this.selectedPointIndex];
            final double distSquared = this.findDistSquared(point, point2);
            final double actualPixelSize = this.md.actualPixelSize;
            if (!this.doesAddCauseIntersect(point2) && distSquared < Math.pow(5.0 * actualPixelSize, 2.0)) {
                return this.polygonIsClosed = true;
            }
        }
        return false;
    }
    
    private boolean replacePoint(final Point point) {
        if (this.selectedPointIndex == -1) {
            return false;
        }
        final Point point2 = new Point();
        point2.x = this.prevState.areaPolygon.xpoints[this.selectedPointIndex];
        point2.y = this.prevState.areaPolygon.ypoints[this.selectedPointIndex];
        if (this.findDistSquared(point, point2) < Math.pow(5.0 * this.md.actualPixelSize, 2.0)) {
            return false;
        }
        final int npoints = this.area.npoints;
        if (npoints <= 3) {
            this.area.xpoints[this.selectedPointIndex] = point.x;
            this.area.ypoints[this.selectedPointIndex] = point.y;
            return true;
        }
        final int n = (this.selectedPointIndex - 1 + npoints) % npoints;
        final Point point3 = new Point(this.area.xpoints[n], this.area.ypoints[n]);
        final int n2 = (this.selectedPointIndex + 1) % npoints;
        final Point point4 = new Point(this.area.xpoints[n2], this.area.ypoints[n2]);
        if (this.selectedPointIndex == 0 && !this.polygonIsClosed) {
            int n3 = this.selectedPointIndex + 2;
            Point point5 = new Point(this.area.xpoints[n3 % npoints], this.area.ypoints[n3 % npoints]);
            for (int i = 0; i < npoints - 3; ++i) {
                final Point point6 = point5;
                point5 = new Point(this.area.xpoints[(n3 + 1) % npoints], this.area.ypoints[(n3 + 1) % npoints]);
                ++n3;
                if (PolygonIntersectTester.linesIntersect(point, point4, point6, point5)) {
                    return false;
                }
            }
        }
        else if (this.selectedPointIndex == npoints - 1 && !this.polygonIsClosed) {
            int n4 = this.selectedPointIndex + 1;
            Point point7 = new Point(this.area.xpoints[n4 % npoints], this.area.ypoints[n4 % npoints]);
            for (int j = 0; j < npoints - 3; ++j) {
                final Point point8 = point7;
                point7 = new Point(this.area.xpoints[(n4 + 1) % npoints], this.area.ypoints[(n4 + 1) % npoints]);
                ++n4;
                if (PolygonIntersectTester.linesIntersect(point3, point, point8, point7)) {
                    return false;
                }
            }
        }
        else {
            int n5 = this.selectedPointIndex + 1;
            Point point9 = new Point(this.area.xpoints[n5 % npoints], this.area.ypoints[n5 % npoints]);
            for (int k = 0; k < npoints - 3; ++k) {
                if ((n5 + 1) % npoints == 0 && !this.polygonIsClosed) {
                    ++n5;
                }
                else {
                    final Point point10 = point9;
                    point9 = new Point(this.area.xpoints[(n5 + 1) % npoints], this.area.ypoints[(n5 + 1) % npoints]);
                    ++n5;
                    if (PolygonIntersectTester.linesIntersect(point3, point, point10, point9)) {
                        return false;
                    }
                }
            }
            int n6 = this.selectedPointIndex + 2;
            Point point11 = new Point(this.area.xpoints[n6 % npoints], this.area.ypoints[n6 % npoints]);
            for (int l = 0; l < npoints - 3; ++l) {
                if ((n6 + 1) % npoints == 0 && !this.polygonIsClosed) {
                    ++n6;
                }
                else {
                    final Point point12 = point11;
                    point11 = new Point(this.area.xpoints[(n6 + 1) % npoints], this.area.ypoints[(n6 + 1) % npoints]);
                    ++n6;
                    if (PolygonIntersectTester.linesIntersect(point, point4, point12, point11)) {
                        return false;
                    }
                }
            }
        }
        this.area.xpoints[this.selectedPointIndex] = point.x;
        this.area.ypoints[this.selectedPointIndex] = point.y;
        return true;
    }
    
    private boolean doesAddCauseIntersect(final Point point) {
        final int npoints = this.area.npoints;
        if (npoints <= 1) {
            return false;
        }
        final Point point2 = new Point();
        final Point point3 = new Point();
        final Point point4 = new Point(this.area.xpoints[npoints - 1], this.area.ypoints[npoints - 1]);
        int i = 0;
        if (point.x == this.area.xpoints[0] && point.y == this.area.ypoints[0]) {
            i = 1;
        }
        Point point5 = new Point(this.area.xpoints[i], this.area.ypoints[i]);
        while (i < npoints - 2) {
            final Point point6 = point5;
            point5 = new Point(this.area.xpoints[i + 1], this.area.ypoints[i + 1]);
            if (PolygonIntersectTester.linesIntersect(point4, point, point6, point5)) {
                return true;
            }
            ++i;
        }
        return false;
    }
    
    private Polygon getAreaPolygonInScreenCoords() {
        if (this.area.npoints == 0) {
            return null;
        }
        final Point upperLeftCorner = this.applet.imgArea.getUpperLeftCorner();
        if (upperLeftCorner == null) {
            return null;
        }
        if (this.displayArea == null || upperLeftCorner != this.prevUpperLeft) {
            this.intersectTester = null;
            final double actualPixelSize = this.md.actualPixelSize;
            this.displayArea = new Polygon();
            for (int i = 0; i < this.area.npoints; ++i) {
                this.displayArea.addPoint((int)Math.round((this.area.xpoints[i] - upperLeftCorner.x) / actualPixelSize), (int)Math.round((-this.area.ypoints[i] + upperLeftCorner.y) / actualPixelSize));
            }
            this.prevUpperLeft = new Point(upperLeftCorner);
        }
        return this.displayArea;
    }
    
    public boolean sceneIntersects(final Metadata metadata) {
        if (metadata == null) {
            return false;
        }
        if (!this.polygonIsClosed) {
            return false;
        }
        if (metadata.screenLocation == null) {
            return false;
        }
        if (this.displayArea == null || this.intersectTester == null) {
            this.displayArea = this.getAreaPolygonInScreenCoords();
            final Point offsetToCenterDisplay = this.applet.imgArea.getOffsetToCenterDisplay();
            (this.intersectTester = new PolygonIntersectTester(this.displayArea)).translate(-offsetToCenterDisplay.x, -offsetToCenterDisplay.y);
        }
        return this.intersectTester.intersects(metadata.screenLocation);
    }
    
    public boolean getPolygonIsClosed() {
        return this.polygonIsClosed;
    }
    
    public void setPolygonIsClosed(final boolean polygonIsClosed) {
        this.polygonIsClosed = polygonIsClosed;
    }
    
    public int numberOfPolygonPoints() {
        return this.area.npoints;
    }
    
    public boolean isUndoStackEmpty() {
        return this.undoStack.empty();
    }
    
    public boolean doesCloseCauseIntersect() {
        return this.doesAddCauseIntersect(new Point(this.area.xpoints[0], this.area.ypoints[0]));
    }
    
    public void applyUserDefinedArea() {
        this.applet.searchLimitDialog.applyFilter();
        this.md.sceneFilter.filter();
        this.applet.imgArea.repaint();
    }
    
    public void closeAndApplyArea() {
        this.displayArea = null;
        this.saveState();
        this.polygonIsClosed = true;
        this.applyUserDefinedArea();
        final TOC currentCell = this.md.getCurrentCell();
        Metadata metadata = null;
        if (currentCell != null && currentCell.valid && currentCell.currentDateIndex >= 0) {
            metadata = currentCell.scenes[currentCell.currentDateIndex];
        }
        if (metadata == null || !this.sceneIntersects(metadata)) {
            this.md.sceneFilter.gotoLastDate();
        }
    }
    
    public void uncloseArea() {
        this.displayArea = null;
        this.saveState();
        this.polygonIsClosed = false;
        this.applet.imgArea.repaint();
    }
    
    public void drawArea(final Graphics graphics) {
        if (this.projectionCode != this.md.getProjectionCode()) {
            return;
        }
        this.displayArea = null;
        this.getAreaPolygonInScreenCoords();
        if (this.displayArea != null && (this.userDefinedAreaDialog.isVisible() || this.polygonIsClosed)) {
            final int[] xpoints = this.displayArea.xpoints;
            final int[] ypoints = this.displayArea.ypoints;
            final int npoints = this.displayArea.npoints;
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < npoints; ++i) {
                graphics.fillOval(xpoints[i] - 6, ypoints[i] - 2, 9, 9);
            }
            if (this.polygonIsClosed) {
                this.displayArea.translate(-2, 2);
                graphics.drawPolygon(this.displayArea);
                this.displayArea.translate(1, -1);
                graphics.drawPolygon(this.displayArea);
                graphics.setColor(Color.WHITE);
                this.displayArea.translate(1, -1);
                graphics.drawPolygon(this.displayArea);
                this.displayArea.translate(1, -1);
                graphics.drawPolygon(this.displayArea);
                this.displayArea.translate(-1, 1);
            }
            else {
                this.displayArea.translate(-2, 2);
                graphics.drawPolyline(xpoints, ypoints, npoints);
                this.displayArea.translate(1, -1);
                graphics.drawPolyline(xpoints, ypoints, npoints);
                graphics.setColor(Color.WHITE);
                this.displayArea.translate(1, -1);
                graphics.drawPolyline(xpoints, ypoints, npoints);
                this.displayArea.translate(1, -1);
                graphics.drawPolyline(xpoints, ypoints, npoints);
                this.displayArea.translate(-1, 1);
            }
            for (int j = 0; j < npoints; ++j) {
                graphics.fillOval(xpoints[j] - 4, ypoints[j] - 4, 9, 9);
            }
        }
    }
    
    private void saveState() {
        this.prevState = new PolygonState();
        this.prevState.closedFlag = this.polygonIsClosed;
        this.prevState.areaPolygon = new Polygon(this.area.xpoints, this.area.ypoints, this.area.npoints);
        this.undoStack.push(this.prevState);
    }
    
    public void mouseRelease(final Point point) {
        boolean b = this.polygonClosed(point);
        if (!this.polygonIsClosed && this.selectedPointIndex == -1 && !b) {
            b = this.addPoint(point);
        }
        if (this.selectedPointIndex != -1 && !b) {
            b = this.replacePoint(point);
        }
        if (this.polygonIsClosed) {
            this.displayArea = null;
            this.applyUserDefinedArea();
            this.applet.searchLimitDialog.applyFilter();
            this.md.sceneFilter.filter();
        }
        if (!b) {
            this.undoStack.pop();
        }
        else {
            this.displayArea = null;
            this.applet.imgArea.repaint();
        }
        this.userDefinedAreaDialog.enableButtons();
        this.selectedPointIndex = -1;
    }
    
    public void mousePressed(final Point point) {
        this.selectedPointIndex = this.whichPointSelected(point);
        this.saveState();
    }
    
    public void mouseDragged(final Point point) {
        if (this.replacePoint(point)) {
            this.displayArea = null;
            this.applet.imgArea.repaint();
        }
    }
    
    public void clearPolygon() {
        if (this.area.npoints > 0) {
            this.saveState();
            this.area.npoints = 0;
            this.polygonIsClosed = false;
            this.userDefinedAreaDialog.enableButtons();
            this.displayArea = null;
            this.applet.searchLimitDialog.clearUserDefinedAreaEnabled();
            this.applet.searchLimitDialog.updateNumOfAvailScenes();
            this.applet.searchLimitDialog.applyFilter();
            this.applet.imgArea.repaint();
        }
    }
    
    public void returnToPrevState() {
        this.polygonIsClosed = this.prevState.closedFlag;
        this.area = this.prevState.areaPolygon;
        this.undoStack.pop();
        if (!this.undoStack.empty()) {
            this.prevState = this.undoStack.peek();
        }
        this.userDefinedAreaDialog.enableButtons();
        this.displayArea = null;
        if (this.applet.searchLimitDialog.isUserDefinedAreaEnabled()) {
            this.applet.searchLimitDialog.clearUserDefinedAreaEnabled();
            this.applet.searchLimitDialog.updateNumOfAvailScenes();
            this.applet.searchLimitDialog.applyFilter();
        }
        this.applet.imgArea.repaint();
    }
    
    public void moveScreenToPolygon() {
        if (this.area.npoints > 0) {
            this.md.gotoLatLong(this.startPos.latitude, this.startPos.longitude);
        }
    }
    
    private class PolygonState
    {
        boolean closedFlag;
        Polygon areaPolygon;
    }
}
