import java.util.Hashtable;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.io.ByteArrayInputStream;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Vector;
import java.awt.Insets;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.awt.event.MouseAdapter;
import java.awt.Dimension;
import java.awt.PopupMenu;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class Canvas3d extends PlotterCanvas implements MouseListener, MouseMotionListener, KeyListener, ActionListener
{
    Point Afegir;
    private PopupMenu pm;
    private Plot3d plot;
    private boolean newImage;
    public Plot3dStream plotStream;
    private int[] old_buffer;
    int CURSOR_MOVE_POINTS;
    int CURSOR_MOVING_POINT;
    Canvas3d CURSOR_WAIT;
    private Dimension streamSize;
    private boolean cubeSizeChanged;
    private boolean transformChanged;
    private boolean infoChanged;
    private boolean showCubeChanged;
    private boolean showAxisChanged;
    private boolean windowSizeChanged;
    private boolean centerChanged;
    public AbstractBox box;
    private AppInterface context;
    private OmegaClient omega;
    public Object capsaPlotter;
    private WButton[] controlButtons;
    public boolean show_controls;
    public long smoothDelay;
    private static Object paintSync;
    private final float[][] ctrPos;
    private int ctrW;
    private int ctrH;
    private static int nCtr;
    private static final String[] ctrCmd;
    private final MouseAdapter RELEASE_CONTROL_LISTENER;
    private static final int[] TRANSPARENT_COLOR;
    
    public Canvas3d(final InputStream inputStream, final AbstractBox box, final AppInterface context) {
        this.newImage = true;
        this.cubeSizeChanged = false;
        this.transformChanged = false;
        this.infoChanged = false;
        this.showCubeChanged = false;
        this.showAxisChanged = false;
        this.windowSizeChanged = false;
        this.centerChanged = false;
        this.show_controls = true;
        this.smoothDelay = 250L;
        this.ctrPos = new float[][] { { 0.0f, -3.0f }, { 2.0f, -3.0f }, { 1.0f, -3.0f }, { 1.0f, -1.0f }, { 0.0f, -2.0f }, { 2.0f, -2.0f }, { 0.0f, -1.0f }, { 2.0f, -1.0f } };
        this.ctrW = 21;
        this.ctrH = 21;
        this.RELEASE_CONTROL_LISTENER = new Canvas3d$1(this);
        this.box = box;
        this.context = context;
        super.doubleBuffering = true;
        (this.plot = new Plot3d()).setSmoothLevel(1);
        this.plotStream = new Plot3dStream(inputStream, this.plot, this);
        this.streamSize = new Dimension(this.plotStream.cwidth, this.plotStream.cheight);
        this.setSize(this.plotStream.cwidth, this.plotStream.cheight);
        this.addControls();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        try {
            this.omega = OmegaClient.newOmega();
            if (context != null) {
                this.omega.setDocumentBase(context.getDocumentBase());
            }
        }
        catch (OException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void addNotify() {
        super.addNotify();
    }
    
    public static final WButton[] createControls(final ResourceProvider resourceProvider) {
        WImage read = null;
        WImage read2 = null;
        final WButton[] array = new WButton[Canvas3d.nCtr];
        Vector list;
        try {
            final InputStream internalResourceStream = resourceProvider.getInternalResourceStream("Icones/viewControls.wbi");
            if (internalResourceStream == null) {
                throw new FileNotFoundException("viewControls.wbi");
            }
            list = WImage.readList(internalResourceStream, Canvas3d.nCtr);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        final Insets padding = new Insets(3, 3, 3, 3);
        try {
            final InputStream internalResourceStream2 = resourceProvider.getInternalResourceStream("Icones/buttonMask.wbi");
            if (internalResourceStream2 == null) {
                throw new FileNotFoundException("buttonMask.wbi");
            }
            read = WImage.read(internalResourceStream2, true);
            final InputStream internalResourceStream3 = resourceProvider.getInternalResourceStream("Icones/buttonPressed.wbi");
            if (internalResourceStream3 == null) {
                throw new FileNotFoundException("buttonPressed.wbi");
            }
            read2 = WImage.read(internalResourceStream3, true);
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        for (int i = 0; i < Canvas3d.nCtr; ++i) {
            final WAutomaticButton wAutomaticButton = new WAutomaticButton(list.elementAt(i), null);
            wAutomaticButton.maskOver = read;
            wAutomaticButton.backPressed = read2;
            wAutomaticButton.setActionCommand(Canvas3d.ctrCmd[i]);
            wAutomaticButton.setName(Canvas3d.ctrCmd[i]);
            wAutomaticButton.setCursor(PlotCanvas.CURSOR_MOVE_POINTS);
            wAutomaticButton.padding = padding;
            array[i] = wAutomaticButton;
        }
        return array;
    }
    
    private void addControls() {
        this.controlButtons = null;
        if (this.context != null) {
            this.controlButtons = createControls(this.context.getResourceProvider());
        }
        if (this.controlButtons == null || this.controlButtons.length == 0) {
            Canvas3d.nCtr = 0;
            return;
        }
        final Dimension preferredSize = this.controlButtons[0].getPreferredSize();
        this.ctrW = preferredSize.width;
        this.ctrH = preferredSize.height;
        for (int i = 0; i < Canvas3d.nCtr; ++i) {
            final float[] array = this.ctrPos[i];
            final int n = 0;
            array[n] *= this.ctrW;
            final float[] array2 = this.ctrPos[i];
            final int n2 = 1;
            array2[n2] *= this.ctrH;
            this.add(this.controlButtons[i]);
            this.controlButtons[i].addActionListener(this);
            this.controlButtons[i].addMouseListener(this.RELEASE_CONTROL_LISTENER);
        }
        this.setControlsBounds();
    }
    
    public final void setControlsButtons(final boolean show_controls) {
        if (show_controls) {
            if (!this.show_controls) {
                for (int i = 0; i < Canvas3d.nCtr; ++i) {
                    this.add(this.controlButtons[i]);
                }
            }
        }
        else if (this.show_controls) {
            for (int j = 0; j < Canvas3d.nCtr; ++j) {
                this.remove(this.controlButtons[j]);
            }
        }
        this.show_controls = show_controls;
    }
    
    private void setControlsBounds() {
        final int n = 10;
        try {
            for (int i = 0; i < Canvas3d.nCtr; ++i) {
                this.getComponent(i).setBounds(n + (int)this.ctrPos[i][0], this.plotStream.cheight - n + (int)this.ctrPos[i][1], this.ctrW, this.ctrH);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void repaint(final boolean b) {
        if (b) {
            this.plot.setSmoothLevel(1);
        }
        else {
            this.plot.setSmoothLevel(0);
        }
        this.repaint();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        synchronized (Canvas3d.paintSync) {
            Cursor cursor = PlotCanvas.CURSOR_MOVE_POINTS;
            final int action = this.plotStream.action;
            this.plotStream.getClass();
            if (action == 2) {
                cursor = PlotCanvas.CURSOR_ZOOM;
            }
            final Dimension size = this.getSize();
            graphics.getClipBounds();
            if (this.plotStream.mMoved || this.thereIsSelectedPoint()) {
                this.plot.doNotPlot = true;
            }
            else {
                this.plot.doNotPlot = false;
            }
            if (size.width != this.plotStream.cwidth || size.height != this.plotStream.cheight) {
                this.plotStream.cwidth = size.width;
                this.plotStream.cheight = size.height;
                this.plotStream.resetScale(false);
                this.plotStream.computeCoorTransform();
                this.newImage = true;
                this.windowSizeChanged = true;
                this.transformChanged = true;
                this.plot.doNotPlot = false;
            }
            this.plotStream.draw();
            if (this.thereIsSelectedPoint()) {
                final float[] point = this.getPoint(this.getSelectedPoint());
                if (point != null && point.length == 3) {
                    cursor = PlotCanvas.CURSOR_MOVING_POINT;
                    this.plotStream.drawMovedPoint();
                }
            }
            this.plotStream.mMoved = false;
            final int[] buffer = this.plot.getBuffer();
            if (this.newImage || buffer != this.old_buffer || this.CURSOR_MOVE_POINTS != this.plotStream.cwidth || this.CURSOR_MOVING_POINT != this.plotStream.cheight) {
                this.setBgImage(new WImage(this.plotStream.cwidth, this.plotStream.cheight, buffer), -1);
                this.newImage = false;
            }
            if (this.show_controls) {
                this.setControlsBounds();
            }
            this.old_buffer = buffer;
            this.CURSOR_MOVE_POINTS = this.plotStream.cwidth;
            this.CURSOR_MOVING_POINT = this.plotStream.cheight;
            super.paint(graphics);
            this.setCursor(cursor);
            this.CURSOR_WAIT = this;
        }
    }
    
    public final void setPlotterCursor(final boolean b, final Cursor cursor) {
        final Component[] components = this.getParent().getComponents();
        if (components != null) {
            for (int i = 0; i < components.length; ++i) {
                if (components[i] == this || !b) {
                    components[i].setCursor(cursor);
                }
                else {
                    components[i].setCursor(PlotCanvas.CURSOR_MOVE_POINTS);
                }
            }
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            this.doPopup();
            this.pm.show(this, mouseEvent.getX(), mouseEvent.getY());
        }
        this.requestFocus();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        super.mouseState = 1;
        this.Afegir = mouseEvent.getPoint();
        this.plotStream.mMoved = true;
        this.repaint(false);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.Afegir = null;
        super.mouseState = 0;
        if (this.thereIsSelectedPoint()) {
            this.refresh(false);
            this.unselectPoint();
            this.repaintTrue();
        }
        else if (this.plot.getPlanes() == 1) {
            this.repaintTrue();
        }
    }
    
    private void doPopup() {
        if (this.pm == null) {
            this.pm = new PopupMenu();
            final MenuItem menuItem = new MenuItem("Axis");
            menuItem.setActionCommand("axis");
            menuItem.addActionListener(this);
            this.pm.add(menuItem);
            final MenuItem menuItem2 = new MenuItem("Grid");
            menuItem2.setActionCommand("grid");
            menuItem2.addActionListener(this);
            this.pm.add(menuItem2);
            final Menu menu = new Menu("Zoom");
            final MenuItem menuItem3 = new MenuItem("Restore");
            menuItem3.setActionCommand("zoomreset");
            menuItem3.addActionListener(this);
            menu.add(menuItem3);
            final MenuItem menuItem4 = new MenuItem("Zoom in");
            menuItem4.setActionCommand("zoomin");
            menuItem4.addActionListener(this);
            menu.add(menuItem4);
            final MenuItem menuItem5 = new MenuItem("Zoom out");
            menuItem5.setActionCommand("zoomout");
            menuItem5.addActionListener(this);
            menu.add(menuItem5);
            this.pm.add(menu);
            if (this.capsaPlotter != null) {
                final MenuItem menuItem6 = new MenuItem("Propiedades");
                menuItem6.setActionCommand("properties");
                menuItem6.addActionListener(this);
                this.pm.add(menuItem6);
            }
            this.add(this.pm);
        }
    }
    
    public final void refresh(final boolean b) {
        final Cursor cursor = this.getCursor();
        this.setPlotterCursor(false, PlotCanvas.CURSOR_WAIT);
        if (this.box != null) {
            final Vector vector = new Vector<String>();
            final Vector vector2 = new Vector();
            if (this.box instanceof CapsaComandes) {
                ((CapsaComandes)this.box).ObteOmegaStrings2(vector, vector2, false);
            }
            if (!b) {
                final Enumeration movedPoints = this.getMovedPoints();
                while (movedPoints.hasMoreElements()) {
                    vector.addElement(this.scriptFigure(PlotterCanvas.omega_format, movedPoints.nextElement(), true));
                }
                if (this.cubeSizeChanged || this.centerChanged) {
                    vector.addElement(this.attributesCode(PlotterCanvas.omega_format, false));
                }
            }
            final String[] array = new String[vector.size()];
            vector.copyInto(array);
            try {
                FormulaEditorCalc.evaluate(null, this.omega, array);
                final Vector graphics = this.omega.getGraphics();
                for (int i = 0; i < graphics.size(); ++i) {
                    final ByteArrayInputStream byteArrayInputStream = graphics.elementAt(i);
                    final String plotterName = PlotterCanvas.getPlotterName(byteArrayInputStream);
                    if (this.box instanceof CapsaComandes) {
                        ((CapsaComandes)this.box).updatePlotters(plotterName, byteArrayInputStream);
                    }
                }
            }
            catch (OException ex) {
                ex.printStackTrace();
            }
        }
        this.setPlotterCursor(true, cursor);
    }
    
    public final void setData(final InputStream inputStream, final AbstractBox box, final boolean b) {
        super.setData(inputStream, box, b);
        if (b) {
            this.resetChanges();
            this.plotStream.zoom = 1.0f;
            this.newImage = true;
        }
        this.box = box;
        this.plotStream.setData(new DataInputStream(inputStream));
        try {
            this.plotStream.initializeStream(b);
            if (b) {
                this.streamSize.width = this.plotStream.cwidth;
                this.streamSize.height = this.plotStream.cheight;
                this.setSize(this.streamSize);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private float[] getCoorFromProj(float n, float n2, float n3) {
        final float[] array = new float[3];
        final float n4 = this.plotStream.proj[3][2];
        array[2] = n3 / (1.0f - n4 * n3);
        array[1] = n2 * (n4 * array[2] + 1.0f) - this.plotStream.proj[1][2] * array[2];
        array[0] = n * (n4 * array[2] + 1.0f) - this.plotStream.proj[0][2] * array[2];
        for (int i = 0; i < 3; ++i) {
            final float[] array2 = array;
            final int n5 = i;
            array2[n5] -= this.plotStream.wcenter[i];
        }
        final float[][] array3 = new float[4][4];
        for (int j = 0; j < 4; ++j) {
            for (int k = 0; k < 4; ++k) {
                array3[j][k] = this.plot.view[k][j];
            }
        }
        n = array[0];
        n2 = array[1];
        n3 = array[2];
        for (int l = 0; l < 3; ++l) {
            array[l] = array3[l][0] * n + array3[l][1] * n2 + array3[l][2] * n3;
        }
        array[0] = array[0] / this.plotStream.xScale + this.plotStream.cCube[0];
        array[1] = array[1] / this.plotStream.yScale + this.plotStream.cCube[1];
        array[2] = array[2] / this.plotStream.zScale + this.plotStream.cCube[2];
        return array;
    }
    
    private String formatFloat(final String[] array, final float n) {
        return PlotCanvas.formatDouble(array, n, true);
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (mouseEvent.isMetaDown()) {
            if (this.Afegir != null) {
                final Point point = mouseEvent.getPoint();
                this.rotate(point.x - this.Afegir.x, point.y - this.Afegir.y);
                this.Afegir = point;
                this.unselectPoint();
                this.repaint(false);
            }
        }
        else {
            final int action = this.plotStream.action;
            this.plotStream.getClass();
            if (action == 2) {
                final Point point2 = mouseEvent.getPoint();
                final float n = point2.x - this.Afegir.x;
                final float n2 = point2.y - this.Afegir.y;
                final float[][] multiplyViewMatrix = this.plotStream.multiplyViewMatrix();
                final Plot3d plot = this.plot;
                final float n3 = Plot3d.mult0(multiplyViewMatrix, 0.0f, 0.0f, 0.0f) + n;
                final Plot3d plot2 = this.plot;
                final float n4 = Plot3d.mult1(multiplyViewMatrix, 0.0f, 0.0f, 0.0f) + n2;
                final Plot3d plot3 = this.plot;
                final float[] coorFromProj = this.getCoorFromProj(n3, n4, Plot3d.mult2(multiplyViewMatrix, 0.0f, 0.0f, 0.0f));
                final float[] oCube = this.plotStream.oCube;
                final int n5 = 0;
                oCube[n5] -= coorFromProj[0];
                final float[] oCube2 = this.plotStream.oCube;
                final int n6 = 1;
                oCube2[n6] -= coorFromProj[1];
                final float[] oCube3 = this.plotStream.oCube;
                final int n7 = 2;
                oCube3[n7] -= coorFromProj[2];
                final float[] cCube = this.plotStream.cCube;
                final int n8 = 0;
                cCube[n8] -= coorFromProj[0];
                final float[] cCube2 = this.plotStream.cCube;
                final int n9 = 1;
                cCube2[n9] -= coorFromProj[1];
                final float[] cCube3 = this.plotStream.cCube;
                final int n10 = 2;
                cCube3[n10] -= coorFromProj[2];
                this.plotStream.computeCoorTrMatrix();
                this.plotStream.computeAxis = true;
                this.centerChanged = true;
                this.Afegir = point2;
                this.repaint(false);
            }
            else if (this.thereIsSelectedPoint()) {
                final String selectedPoint = this.getSelectedPoint();
                final AbstractBox box = this.getBox(selectedPoint);
                if (box != null) {
                    this.plotStream.clearCacheTextImage(box);
                }
                if (!super.setSelectedPoint(selectedPoint, this.Afegir.x, this.Afegir.y, mouseEvent.getX(), mouseEvent.getY(), this.plotStream.formula)) {
                    this.plotStream.mMoved = true;
                    this.plot.over_x = mouseEvent.getX();
                    this.plot.over_y = mouseEvent.getY();
                    this.setSelectedPoint(this.getSelectedPoint(), this.getCoorFromProj(mouseEvent.getX(), mouseEvent.getY(), this.plotStream.zPoint));
                }
                this.repaint(false);
            }
        }
    }
    
    private void rotate(final float n, final float n2) {
        final double atan2 = Math.atan2(n2, n);
        this.plot.multiplyViewMatrix(Plot3d.spinMatrixZ((float)atan2));
        this.plot.multiplyViewMatrix(Plot3d.spinMatrixY(-(float)Math.sqrt(n * n + n2 * n2) / 50.0f));
        this.plot.multiplyViewMatrix(Plot3d.spinMatrixZ((float)(-atan2)));
        this.transformChanged = true;
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        this.plot.over_x = mouseEvent.getX();
        this.plot.over_y = mouseEvent.getY();
        this.plotStream.mMoved = true;
        this.repaintTrue();
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 107: {
                this.scale(1);
                this.repaint();
                break;
            }
            case 109: {
                this.scale(-1);
                this.repaint();
                break;
            }
            case 27: {
                this.plotStream.initView();
                this.plotStream.resetScale(false);
                this.plotStream.computeCoorTransform();
                this.repaint();
                break;
            }
            case 67: {
                this.plotStream.showCube = !this.plotStream.showCube;
                this.showCubeChanged = true;
                this.repaint();
                break;
            }
            case 65: {
                this.plotStream.showCoorAxis = !this.plotStream.showCoorAxis;
                this.showAxisChanged = true;
                this.repaint();
                break;
            }
            case 73: {
                switch (super.mouseLabelMode) {
                    case 1: {
                        super.mouseLabelMode = 2;
                        break;
                    }
                    case 2: {
                        super.mouseLabelMode = 3;
                        break;
                    }
                    case 3: {
                        super.mouseLabelMode = 0;
                        break;
                    }
                    case 0: {
                        super.mouseLabelMode = 1;
                        break;
                    }
                }
                this.infoChanged = true;
                this.repaint();
                break;
            }
        }
    }
    
    public final void scale(final int n) {
        if (n < 0) {
            final Plot3dStream plotStream = this.plotStream;
            plotStream.xScale *= 0.9f;
            final Plot3dStream plotStream2 = this.plotStream;
            plotStream2.yScale *= 0.9f;
            final Plot3dStream plotStream3 = this.plotStream;
            plotStream3.zScale *= 0.9f;
            this.plotStream.zoom = this.plotStream.zoom * 9.0f / 10.0f;
        }
        else {
            final Plot3dStream plotStream4 = this.plotStream;
            plotStream4.xScale *= 1.1111112f;
            final Plot3dStream plotStream5 = this.plotStream;
            plotStream5.yScale *= 1.1111112f;
            final Plot3dStream plotStream6 = this.plotStream;
            plotStream6.zScale *= 1.1111112f;
            this.plotStream.zoom = this.plotStream.zoom * 10.0f / 9.0f;
        }
        this.plotStream.computeCoorTrMatrix();
        this.plotStream.computeAxis = true;
        this.transformChanged = true;
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final float n = 9.817477f;
        final String actionCommand = actionEvent.getActionCommand();
        synchronized (this.plotStream) {
            if (actionCommand.equals("axis")) {
                this.plotStream.showCoorAxis = !this.plotStream.showCoorAxis;
                this.showAxisChanged = true;
                this.repaint();
            }
            else if (actionCommand.equals("grid")) {
                this.plotStream.showCube = !this.plotStream.showCube;
                this.showCubeChanged = true;
                this.repaint();
            }
            else if (actionCommand.equals("actionshowname")) {
                if (super.mouseLabelMode == 1) {
                    super.mouseLabelMode = 0;
                }
                else {
                    super.mouseLabelMode = 1;
                }
                this.infoChanged = true;
            }
            else if (actionCommand.equals("actionshowvalue")) {
                if (super.mouseLabelMode == 2) {
                    super.mouseLabelMode = 0;
                }
                else {
                    super.mouseLabelMode = 2;
                }
                this.infoChanged = true;
            }
            else if (actionCommand.equals("actionshowdef")) {
                if (super.mouseLabelMode == 3) {
                    super.mouseLabelMode = 0;
                }
                else {
                    super.mouseLabelMode = 3;
                }
                this.infoChanged = true;
            }
            else if (actionCommand.equals("zoomin")) {
                this.zoom(0.6666667f, 1.0f);
                this.repaint();
            }
            else if (actionCommand.equals("zoomout")) {
                this.zoom(1.5f, 1.0f);
                this.repaint();
            }
            else if (actionCommand.equals("zoomreset")) {
                this.plotStream.restoreScaleAndCubeSize();
                this.cubeSizeChanged = true;
                this.transformChanged = true;
                this.repaint();
            }
            else if (actionCommand.equals("aspectratio1")) {
                this.plotStream.resetScale(true);
                this.plotStream.computeCoorTransform();
                this.transformChanged = true;
                this.repaint();
            }
            else if (actionCommand.equals("refresh")) {
                this.refresh(false);
                this.repaint();
            }
            else if (actionCommand.equals("rotateZLeft")) {
                final float n2 = 0.19634955f;
                final Plot3d plot = this.plotStream.plot;
                final Plot3d plot2 = this.plotStream.plot;
                plot.multiplyViewMatrix(Plot3d.spinMatrixZ(n2));
                this.transformChanged = true;
                this.repaint(false);
            }
            else if (actionCommand.equals("rotateZRight")) {
                final float n3 = -0.19634955f;
                final Plot3d plot3 = this.plotStream.plot;
                final Plot3d plot4 = this.plotStream.plot;
                plot3.multiplyViewMatrix(Plot3d.spinMatrixZ(n3));
                this.transformChanged = true;
                this.repaint(false);
            }
            else if (actionCommand.equals("scaleUp")) {
                this.scale(1);
                this.repaint(false);
            }
            else if (actionCommand.equals("scaleDown")) {
                this.scale(-1);
                this.repaint(false);
            }
            else if (actionCommand.equals("rotateUp")) {
                this.rotate(0.0f, -n);
                this.repaint(false);
            }
            else if (actionCommand.equals("rotateDown")) {
                this.rotate(0.0f, n);
                this.repaint(false);
            }
            else if (actionCommand.equals("rotateLeft")) {
                this.rotate(-n, 0.0f);
                this.repaint(false);
            }
            else if (actionCommand.equals("rotateRight")) {
                this.rotate(n, 0.0f);
                this.repaint(false);
            }
            else if (actionCommand.equals("resetplotcode")) {
                if (this.plotStream.formula != null) {
                    this.fromPlotterToCommand();
                    this.plotStream.formula.repaint();
                }
            }
            else if (actionCommand.equals("recompute")) {
                this.resetChanges();
                this.refresh(true);
                this.repaint();
            }
            else if (actionCommand.equals("actionzoom")) {
                final Plot3dStream plotStream = this.plotStream;
                final int action = this.plotStream.action;
                this.plotStream.getClass();
                int action2;
                if (action == 2) {
                    this.plotStream.getClass();
                    action2 = 0;
                }
                else {
                    this.plotStream.getClass();
                    action2 = 2;
                }
                plotStream.action = action2;
            }
            else if (actionCommand.equals("actionmove")) {
                final Plot3dStream plotStream2 = this.plotStream;
                final int action3 = this.plotStream.action;
                this.plotStream.getClass();
                int action4;
                if (action3 == 1) {
                    this.plotStream.getClass();
                    action4 = 0;
                }
                else {
                    this.plotStream.getClass();
                    action4 = 1;
                }
                plotStream2.action = action4;
            }
            else if (actionCommand.equals("switchmove")) {
                final Plot3dStream plotStream3 = this.plotStream;
                final int action5 = this.plotStream.action;
                this.plotStream.getClass();
                int action6;
                if (action5 == 1) {
                    this.plotStream.getClass();
                    action6 = 2;
                }
                else {
                    this.plotStream.getClass();
                    action6 = 1;
                }
                plotStream3.action = action6;
            }
            else if (actionCommand.equals("logoicon")) {
                this.context.showDocumentNewWindow("http://www.wiris.com");
            }
            else if (actionCommand.equals("switchcontrols")) {
                this.setControlsButtons(!this.show_controls);
                this.repaint();
            }
        }
    }
    
    private void zoom(final float n, final float n2) {
        final Plot3dStream plotStream = this.plotStream;
        plotStream.xCubeSize *= n;
        final Plot3dStream plotStream2 = this.plotStream;
        plotStream2.yCubeSize *= n;
        final Plot3dStream plotStream3 = this.plotStream;
        plotStream3.zCubeSize *= n;
        for (int i = 0; i < 3; ++i) {
            final float[] vxCube = this.plotStream.vxCube;
            final int n3 = i;
            vxCube[n3] *= n;
            final float[] vyCube = this.plotStream.vyCube;
            final int n4 = i;
            vyCube[n4] *= n;
            final float[] vzCube = this.plotStream.vzCube;
            final int n5 = i;
            vzCube[n5] *= n;
            this.plotStream.oCube[i] = this.plotStream.cCube[i] + (this.plotStream.oCube[i] - this.plotStream.cCube[i]) * n;
        }
        final Plot3dStream plotStream4 = this.plotStream;
        plotStream4.zoom *= n2;
        this.plotStream.resetScale(false);
        this.plotStream.computeCoorTransform();
        this.cubeSizeChanged = true;
        this.transformChanged = true;
    }
    
    private void fromPlotterToCommand() {
        final Enumeration movedPoints = this.getMovedPoints();
        while (movedPoints.hasMoreElements()) {
            this.addCommand(this.scriptFigure(PlotterCanvas.script_format, movedPoints.nextElement(), true));
        }
        if (this.infoChanged || this.showCubeChanged || this.showAxisChanged || this.windowSizeChanged || this.transformChanged || this.cubeSizeChanged || this.centerChanged) {
            this.addCommand(this.attributesCode(PlotterCanvas.script_format, true));
        }
        this.resetChanges();
    }
    
    private void resetChanges() {
        this.clearAllPoints();
        this.infoChanged = false;
        this.showCubeChanged = false;
        this.showAxisChanged = false;
        this.windowSizeChanged = false;
        this.transformChanged = false;
        this.cubeSizeChanged = false;
        this.centerChanged = false;
    }
    
    private String formatId(final String[] array, final String s) {
        return array[8] + s + array[9];
    }
    
    private String cubeSizeCode(final String[] array) {
        final String s = array[6];
        final String s2 = array[7];
        return this.formatId(array, "width") + s2 + this.formatFloat(array, this.plotStream.xCubeSize) + s + this.formatId(array, "height") + s2 + this.formatFloat(array, this.plotStream.yCubeSize) + s + this.formatId(array, "depth") + s2 + this.formatFloat(array, this.plotStream.zCubeSize);
    }
    
    private String positionMatrixCode(final String[] array) {
        final String s = array[0];
        final String s2 = array[1];
        final String s3 = array[4];
        final String s4 = array[5];
        final String s5 = array[6];
        final String s6 = array[7];
        final StringBuffer sb = new StringBuffer();
        final float[][] array2 = new float[3][3];
        this.plotStream.scale(this.plot.view, array2, this.plotStream.xScale, this.plotStream.yScale, this.plotStream.zScale);
        sb.append(this.formatId(array, "transformation_matrix") + s6);
        sb.append(s + "<mtable>");
        for (int i = 0; i < 3; ++i) {
            sb.append("<mtr>");
            for (int j = 0; j < 3; ++j) {
                sb.append("<mtd>");
                sb.append(this.formatFloat(array, array2[i][j]));
                sb.append("</mtd>");
            }
            sb.append("</mtr>");
        }
        sb.append("</mtable></mfenced>");
        return sb.toString();
    }
    
    private String attributesCode(final String[] array, final boolean b) {
        final String s = array[6];
        final String s2 = array[0];
        final String s3 = array[1];
        final String s4 = array[2];
        final String s5 = array[3];
        final StringBuffer sb = new StringBuffer();
        int n = 1;
        sb.append(this.formatId(array, "attributes3d") + s2);
        sb.append(this.formatId(array, this.getName()));
        sb.append(s + s4);
        if (b) {
            if (this.infoChanged) {
                sb.append(this.informationCode(array));
                n = 0;
            }
            if (this.showCubeChanged) {
                if (n == 0) {
                    sb.append(s);
                }
                sb.append(this.showCubeCode(array));
                n = 0;
            }
            if (this.showAxisChanged) {
                if (n == 0) {
                    sb.append(s);
                }
                sb.append(this.showAxisCode(array));
                n = 0;
            }
            if (this.windowSizeChanged) {
                if (n == 0) {
                    sb.append(s);
                }
                sb.append(this.windowSizeCode(array));
                n = 0;
            }
            if (this.transformChanged) {
                if (n == 0) {
                    sb.append(s);
                }
                sb.append(this.positionMatrixCode(array));
                n = 0;
            }
        }
        if (this.cubeSizeChanged) {
            if (n == 0) {
                sb.append(s);
            }
            sb.append(this.cubeSizeCode(array));
            n = 0;
        }
        if (this.centerChanged) {
            if (n == 0) {
                sb.append(s);
            }
            sb.append(this.centerCode(array));
        }
        sb.append(s5 + s3);
        return sb.toString();
    }
    
    private String informationCode(final String[] array) {
        final String s = array[7];
        final String s2 = array[10];
        final String s3 = array[11];
        final StringBuffer sb = new StringBuffer();
        sb.append(this.formatId(array, "information") + s + s2);
        switch (super.mouseLabelMode) {
            case 1: {
                sb.append(this.formatId(array, "name"));
                break;
            }
            case 2: {
                sb.append(this.formatId(array, "value"));
                break;
            }
            case 3: {
                sb.append(this.formatId(array, "definition"));
                break;
            }
            case 0: {
                sb.append(this.formatId(array, "none"));
                break;
            }
        }
        sb.append(s3);
        return sb.toString();
    }
    
    private String showCubeCode(final String[] array) {
        return this.formatId(array, "show_cube") + array[7] + this.formatId(array, this.plotStream.showCube ? "true" : "false");
    }
    
    private String showAxisCode(final String[] array) {
        return this.formatId(array, "show_axis") + array[7] + this.formatId(array, this.plotStream.showCoorAxis ? "true" : "false");
    }
    
    private String windowSizeCode(final String[] array) {
        final String s = array[7];
        final String s2 = array[6];
        final int cwidth = this.plotStream.cwidth;
        final int cheight = this.plotStream.cheight;
        final int dpi = this.plotStream.formula.getDPI();
        return this.formatId(array, "window_width") + s + this.formatFloat(array, (cwidth * 72 + dpi / 2) / dpi) + s2 + this.formatId(array, "window_height") + s + this.formatFloat(array, (cheight * 72 + dpi / 2) / dpi);
    }
    
    private String centerCode(final String[] array) {
        final String s = array[7];
        final String s2 = array[6];
        return this.formatId(array, "center") + s + this.formatId(array, "point") + array[0] + this.formatFloat(array, this.plotStream.cCube[0]) + s2 + this.formatFloat(array, this.plotStream.cCube[1]) + s2 + this.formatFloat(array, this.plotStream.cCube[2]) + array[1];
    }
    
    private void addCommand(String string) {
        if (this.box instanceof CapsaComandes) {
            string = "<command><input><math>" + string + "</math></input></command>";
            this.box.fill[0].Afegir(this.plotStream.formula.parse(string), this.plotStream.formula);
        }
    }
    
    public final DataInputStream getData() {
        return this.plotStream.getData();
    }
    
    public final Dimension getPreferredSize() {
        final int dpi = this.plotStream.formula.getDPI();
        return new Dimension(this.streamSize.width * dpi / 72, this.streamSize.height * dpi / 72);
    }
    
    public final int[] getImageBuffer(final boolean b) {
        final int[] backColor = this.plotStream.backColor;
        this.plotStream.plot = new Plot3d();
        this.plotStream.plot.view = this.plot.view;
        if (!b) {
            this.plotStream.backColor = Canvas3d.TRANSPARENT_COLOR;
        }
        this.plotStream.draw();
        this.old_buffer = null;
        final int[] buffer = this.plotStream.plot.getBuffer();
        this.plotStream.backColor = backColor;
        this.plotStream.plot = this.plot;
        return buffer;
    }
    
    public final boolean newBackground() {
        return true;
    }
    
    public final void freeResources() {
        super.freeResources();
        this.setVisible(false);
        if (this.plot != null) {
            this.plot.free_resources();
        }
        this.plot = null;
        if (this.plotStream != null) {
            this.plotStream.free_resources();
        }
        this.plotStream = null;
        this.old_buffer = null;
        this.box = null;
        this.context = null;
        this.omega = null;
    }
    
    public final void repaintTrue() {
        this.repaint(true);
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        super.exchangeProperties(hashtable, n);
        super.mouseLabelMode = Attributes.exchangeIntTranslate(hashtable, "information", n, super.mouseLabelMode, 1, PlotterCanvas.infoArray);
        this.plotStream.showCoorAxis = Attributes.exchangeBool(hashtable, "show_axis", n, this.plotStream.showCoorAxis, true);
        this.plotStream.showCube = Attributes.exchangeBool(hashtable, "show_cube", n, this.plotStream.showCube, true);
        this.plotStream.cwidth = Attributes.exchangeInt(hashtable, "window_width", n, this.plotStream.cwidth, (n == 0 || n == 4097) ? this.plotStream.cwidth : 0);
        this.plotStream.cheight = Attributes.exchangeInt(hashtable, "window_height", n, this.plotStream.cheight, (n == 0 || n == 4097) ? this.plotStream.cheight : 0);
        final float[][] array = null;
        float[] cCube = null;
        float[][] array2;
        float[] array3;
        if (n == 1) {
            array2 = new float[3][3];
            this.plotStream.scale(this.plot.view, array2, this.plotStream.xScale, this.plotStream.yScale, this.plotStream.zScale);
            array3 = new float[] { this.plotStream.xCubeSize, this.plotStream.yCubeSize, this.plotStream.zCubeSize };
            cCube = this.plotStream.cCube;
        }
        else {
            array2 = new float[3][];
            array3 = new float[3];
        }
        array3[0] = Attributes.exchangeFloat(hashtable, "width", n, array3[0], 0.0f);
        array3[1] = Attributes.exchangeFloat(hashtable, "height", n, array3[1], 0.0f);
        array3[2] = Attributes.exchangeFloat(hashtable, "depth", n, array3[2], 0.0f);
        for (int i = 0; i < 3; ++i) {
            array2[i] = Attributes.exchangeFloatV(3, hashtable, "transformation_matrix_" + i, n, array2[i], new float[] { 0.0f, 0.0f, 0.0f });
        }
        final float[] exchangeFloatV = Attributes.exchangeFloatV(3, hashtable, "center", n, cCube, new float[] { 0.0f, 0.0f, 0.0f });
        if ((n == 0 || n == 4097) && array3[0] != 0.0f && array3[1] != 0.0f && array3[2] != 0.0f) {
            this.plotStream.setViewCube(exchangeFloatV, array3, array2);
        }
    }
    
    static final Plot3d I(final Canvas3d canvas3d) {
        return canvas3d.plot;
    }
    
    static {
        Canvas3d.paintSync = new Object();
        Canvas3d.nCtr = 8;
        ctrCmd = new String[] { "scaleUp", "scaleDown", "rotateUp", "rotateDown", "rotateLeft", "rotateRight", "rotateZLeft", "rotateZRight" };
        TRANSPARENT_COLOR = new int[] { 255, 255, 255, 0 };
    }
}
