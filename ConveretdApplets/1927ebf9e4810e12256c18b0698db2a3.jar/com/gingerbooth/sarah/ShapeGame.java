// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.Point;
import java.util.Random;
import com.persistence.Persistence;
import java.net.URL;
import courseware.util.Palettable;
import com.gingerbooth.websafe.SubsetPalette;
import com.gingerbooth.websafe.WebSafeHue;
import com.gingerbooth.websafe.WebSafeHuePalette;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import courseware.util.ModuleLayout;
import java.awt.Color;
import java.awt.Graphics;
import courseware.util.Palette;
import java.applet.Applet;

public class ShapeGame extends Applet
{
    private int lastX;
    private int lastY;
    private int firstX;
    private int firstY;
    private int mouseX;
    private int mouseY;
    private Tableau tableau;
    private Tableau shapeMenu;
    private Palette backgrounds;
    private UndoPanel undoPanel;
    private TransPanel leftPanel;
    private TransPanel tableauPanel;
    private ControlPanel controlPanel;
    private PaintMenu paintMenu;
    private int backgroundColor;
    private UndoableStack undoStack;
    int paintingColor;
    boolean paintMode;
    private final String shapesKey = "shapes";
    private UndoableTableau undoer;
    double panFactor;
    double zoomFactor;
    private int rotateAmount;
    private Pick rbPick;
    private static boolean firstTime;
    
    static {
        ShapeGame.firstTime = true;
    }
    
    public ShapeGame() {
        this.backgroundColor = 209;
        this.paintingColor = 0;
        this.paintMode = false;
        this.panFactor = 0.25;
        this.zoomFactor = 1.2;
        this.rotateAmount = 1;
        this.rbPick = null;
    }
    
    static /* synthetic */ void access$5(final ShapeGame shapeGame, final int rotateAmount) {
        shapeGame.rotateAmount = rotateAmount;
    }
    
    private void beginTableauEdit() {
        this.undoer = new UndoableTableau(this.tableau, this);
    }
    
    public void center() {
        this.beginTableauEdit();
        this.tableau.center();
        this.endTableauEdit();
        this.forceRepaint();
    }
    
    public void clearScreen() {
        this.beginTableauEdit();
        this.tableau.erase(this.tableau.backgroundColor);
        this.endTableauEdit();
        this.forceRepaint();
    }
    
    void colorAll() {
        if (this.rbPick == null) {
            return;
        }
        final Graphics graphics = this.tableauPanel.getGraphics();
        if (this.rbPick.isCopy()) {
            this.rbPick = this.shapeMenu.pick(this.firstX, this.firstY, false);
            this.tableau.xorOne(graphics, this.rbPick);
            this.tableau.removeOne(this.rbPick.shape);
            this.undoStack.pop();
        }
        final UndoableColorAll undoableColorAll = new UndoableColorAll(this.paintingColor, this.rbPick.shape);
        this.undoStack.push(undoableColorAll);
        this.rbPick.shape.color = this.paintingColor;
        this.tableau.replaceShapeColor(this.rbPick.shape.getClass(), this.paintingColor, undoableColorAll);
        this.rbPick = null;
        this.forceRepaint();
    }
    
    void colorOne() {
        if (this.rbPick == null) {
            return;
        }
        this.tableau.xorOne(this.tableauPanel.getGraphics(), this.rbPick);
        this.rbPick.shape.color = this.paintingColor;
        final UndoableShape undoableShape = (UndoableShape)this.undoStack.getCurrent();
        undoableShape.setStateAfter(this.rbPick.shape);
        undoableShape.setOperation(0);
        this.tableau.add(this.rbPick.shape);
        this.forceRepaint();
        this.rbPick = null;
    }
    
    void copyBegin(final Tableau tableau, final Tableau tableau2, final int n, final int n2) {
        if (this.rbPick != null) {
            this.rbEnd(n, n2);
            return;
        }
        this.rbPick = tableau.pickCross(n, n2, false, tableau2);
        if (this.rbPick == null) {
            return;
        }
        this.rbPick.shape = (Shape)this.rbPick.shape.clone();
        this.rbPick.setCopy(true);
        this.undoStack.push(new UndoableShape(1, tableau2, null));
        final Graphics graphics = this.tableauPanel.getGraphics();
        final double n3 = tableau2.x_world(n) - tableau.x_world(n);
        final double n4 = tableau2.y_world(n2) - tableau.y_world(n2);
        this.rbPick.setRefX(tableau2.x_world(n));
        this.rbPick.setRefY(tableau2.y_world(n2));
        this.rbPick.shape.moveBy(n3, n4);
        tableau.xorOne(graphics, this.rbPick);
    }
    
    private void endTableauEdit() {
        this.undoer.setStateAfter(this.tableau, this);
        this.undoStack.push(this.undoer);
    }
    
    public void finalize() throws Throwable {
        this.tableau.finalize();
        super.finalize();
    }
    
    public void forceRepaint() {
        this.tableau.setNeedRepaint(true);
        this.shapeMenu.setNeedRepaint(true);
        this.undoPanel.repaint();
        this.shapeMenu.repaint();
        this.repaint();
    }
    
    public int getBackgroundColor() {
        return this.backgroundColor;
    }
    
    public int getPaintColor() {
        return this.paintingColor;
    }
    
    public Tableau getTableau() {
        return this.tableau;
    }
    
    public UndoableStack getUndoStack() {
        return this.undoStack;
    }
    
    public void init() {
        super.init();
        this.undoStack = new UndoableStack();
        super.setBackground(new Color(0, 0, 128));
        this.undoStack = new UndoableStack();
        this.setLayout(new ModuleLayout());
        this.leftPanel = new TransPanel();
        (this.undoPanel = new UndoPanel(this)).setVisible(true);
        this.leftPanel.add(this.undoPanel);
        this.leftPanel.setVisible(true);
        (this.tableauPanel = new TransPanel()).setLayout(new TableauLayout2(this.undoPanel.getPreferredSize().width));
        this.add(this.tableauPanel, ModuleLayout.BIG_STRING);
        (this.tableau = new Tableau()).setOutline(true);
        this.tableau.setVisible(true);
        this.tableauPanel.add(TableauLayout.THEREST, this.tableau);
        (this.shapeMenu = new Tableau()).setOutline(true);
        this.shapeMenu.outlineColor = 215;
        this.shapeMenu.setVisible(true);
        this.leftPanel.add(this.shapeMenu);
        this.leftPanel.setLayout(new LeftLayout(this.undoPanel, this.shapeMenu));
        this.tableauPanel.add(TableauLayout.FIXED, this.leftPanel);
        this.tableauPanel.setVisible(true);
        this.setVisible(true);
        this.shapeMenu.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ShapeGame.this.initXY(mouseEvent);
                ShapeGame.this.copyBegin(ShapeGame.this.shapeMenu, ShapeGame.this.tableau, ShapeGame.this.lastX, ShapeGame.this.lastY);
                if (mouseEvent.isMetaDown() || mouseEvent.isAltDown()) {
                    ShapeGame.access$5(ShapeGame.this, -1);
                }
                else {
                    ShapeGame.access$5(ShapeGame.this, 1);
                }
            }
        });
        this.tableau.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                ShapeGame.this.initXY(mouseEvent);
                ShapeGame.this.rbBegin(ShapeGame.this.tableau, ShapeGame.this.lastX, ShapeGame.this.lastY);
                if (mouseEvent.isMetaDown() || mouseEvent.isAltDown()) {
                    ShapeGame.access$5(ShapeGame.this, -1);
                }
                else {
                    ShapeGame.access$5(ShapeGame.this, 1);
                }
            }
        });
        this.tableau.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                ShapeGame.this.mouseXY(mouseEvent);
                ShapeGame.this.rbDrag(ShapeGame.this.mouseX, ShapeGame.this.mouseY);
                ShapeGame.this.mouse2last();
            }
        });
        this.shapeMenu.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                ShapeGame.this.mouseXY(mouseEvent);
                ShapeGame.this.rbDrag(ShapeGame.this.mouseX, ShapeGame.this.mouseY);
                ShapeGame.this.mouse2last();
            }
        });
        this.tableau.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                ShapeGame.this.mouseXY(mouseEvent);
                if (!ShapeGame.this.mouseMoved()) {
                    if (ShapeGame.this.paintMode) {
                        ShapeGame.this.colorOne();
                    }
                    else {
                        ShapeGame.this.rotateClick(ShapeGame.this.tableau, ShapeGame.this.mouseX, ShapeGame.this.mouseY, ShapeGame.this.rotateAmount);
                    }
                }
                else {
                    if (ShapeGame.this.paintMode) {
                        ShapeGame.this.paintOff();
                    }
                    ShapeGame.this.rbEnd(ShapeGame.this.mouseX, ShapeGame.this.mouseY);
                }
                ShapeGame.this.mouse2last();
            }
        });
        this.shapeMenu.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                ShapeGame.this.mouseXY(mouseEvent);
                if (!ShapeGame.this.mouseMoved()) {
                    if (ShapeGame.this.paintMode) {
                        ShapeGame.this.colorAll();
                    }
                    else {
                        ShapeGame.this.rotateClick(ShapeGame.this.shapeMenu, ShapeGame.this.mouseX, ShapeGame.this.mouseY, ShapeGame.this.rotateAmount);
                    }
                }
                else {
                    if (ShapeGame.this.paintMode) {
                        ShapeGame.this.paintOff();
                    }
                    ShapeGame.this.rbEnd(ShapeGame.this.mouseX, ShapeGame.this.mouseY);
                }
                ShapeGame.this.mouse2last();
            }
        });
        Tableau.setPalette(new SubsetPalette(new WebSafeHuePalette(), WebSafeHue.crayon26));
        Tableau.setBackPalette(new SubsetPalette(new WebSafeHuePalette(), WebSafeHue.backgrounds12));
        this.paintMenu = new PaintMenu(this, (SubsetPalette)Tableau.palette, (SubsetPalette)Tableau.backPalette, new SubsetPalette(new WebSafeHuePalette(), WebSafeHue.outlines5));
        this.tableauPanel.add(TableauLayout2.RIGHT, this.paintMenu);
        this.setBackgroundColor(this.backgroundColor);
    }
    
    void initXY(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        this.lastX = x;
        this.firstX = x;
        final int y = mouseEvent.getY();
        this.lastY = y;
        this.firstY = y;
    }
    
    public boolean isPaintMode() {
        return this.paintMode;
    }
    
    public void loadData(final String s) {
        try {
            this.tableau.load(new URL(this.getDocumentBase(), s).openStream(), this);
        }
        catch (Exception ex) {
            System.err.println("Error loading file " + s);
            ex.printStackTrace();
        }
    }
    
    public void loadPreset() {
        final String parameter = this.getParameter("URL");
        if (parameter.length() > 2) {
            this.loadData(parameter);
        }
        if (this.getParameter("itemid") == null) {
            return;
        }
        final Persistence persistence = new Persistence(this);
        try {
            this.tableau.load(persistence.getInputStream("shapes"), this);
        }
        catch (Exception ex) {
            System.err.println("Error loading data from server");
            ex.printStackTrace();
        }
    }
    
    public void menuShapes() {
        this.shapeMenu.erase(this.shapeMenu.backgroundColor);
        final double edgeLength = Tableau.edgeLength;
        final double sqrt = Math.sqrt(0.75);
        final int n = this.shapeMenu.getSize().height - Math.round(Math.round(edgeLength * (3.0 * sqrt + 1.0 + Math.sqrt(3.0) + 0.5)));
        final int round = Math.round(Math.round(n / 6.5));
        final int n2 = (n - 5 * round) / 2;
        final double n3 = (this.shapeMenu.getMaxX() - this.shapeMenu.getMinX()) / 2.0;
        this.place(new Triangle(), this.place(new Square(), this.place(new Hexagon(), this.place(new Diamond(), this.place(new Sliver(), this.place(new HalfHexagon(), n2, edgeLength * sqrt, n3, round), edgeLength / 2.0, n3, round), edgeLength * sqrt, n3, round), edgeLength * Math.sqrt(3.0), n3, round), edgeLength, n3, round), edgeLength * sqrt, n3, round);
    }
    
    public String moduleMethod(final String s) {
        String saveData = "";
        if (s.equals("zoomIn")) {
            this.zoomIn();
        }
        else if (s.equals("zoomOut")) {
            this.zoomOut();
        }
        else if (s.equals("center")) {
            this.center();
        }
        else if (s.equals("clear")) {
            this.clearScreen();
        }
        else if (s.equals("panRight")) {
            this.panRight();
        }
        else if (s.equals("panLeft")) {
            this.panLeft();
        }
        else if (s.equals("panUp")) {
            this.panUp();
        }
        else if (s.equals("panDown")) {
            this.panDown();
        }
        else if (s.equals("saveData")) {
            saveData = this.saveData();
        }
        else {
            System.err.println("Unrecognized module method ignored: " + s);
        }
        return saveData;
    }
    
    public void moduleMethodArgs(final String s, final String s2) {
        if (s.equals("setOutline")) {
            this.setOutline(new Integer(s2));
        }
        else if (s.equals("loadData")) {
            this.loadData(new String(s2));
        }
        else {
            System.err.println("Unrecognized module method ignored: " + s + "(" + s2 + ")");
        }
    }
    
    void mouse2last() {
        this.lastX = this.mouseX;
        this.lastY = this.mouseY;
    }
    
    boolean mouseMoved() {
        int mouseSensitivity = Tableau.mouseSensitivity;
        if (this.paintMode) {
            mouseSensitivity *= 3;
        }
        final boolean b = Math.abs(this.mouseX - this.firstX) > mouseSensitivity;
        final boolean b2 = Math.abs(this.mouseY - this.firstY) > mouseSensitivity;
        return b || b2;
    }
    
    void mouseXY(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
    }
    
    public void paintOff() {
        this.paintMode = false;
        this.paintMenu.needRepaint = true;
        this.paintMenu.repaint();
    }
    
    public void panDown() {
        this.beginTableauEdit();
        this.tableau.pan(0.0, 0.25);
        this.endTableauEdit();
    }
    
    public void panLeft() {
        this.beginTableauEdit();
        this.tableau.pan(0.25, 0.0);
        this.endTableauEdit();
    }
    
    public void panRight() {
        this.beginTableauEdit();
        this.tableau.pan(-0.25, 0.0);
        this.endTableauEdit();
    }
    
    public void panUp() {
        this.beginTableauEdit();
        this.tableau.pan(0.0, -0.25);
        this.endTableauEdit();
    }
    
    public void pickColor() {
        this.paintingColor = Math.round(new Random().nextFloat() * 7.0f);
        this.paintMode = true;
    }
    
    private double place(final Shape shape, double n, final double n2, final double n3, final double n4) {
        double n5 = n + n2 / 2.0;
        if (shape instanceof Triangle) {
            n5 = n + n2 / 3.0;
        }
        final double[] center = shape.getCenter();
        shape.moveBy(n3 - center[0], n5 - center[1]);
        this.shapeMenu.add(shape);
        n += n2 + n4;
        return n;
    }
    
    void rbBegin(final Tableau tableau, final int n, final int n2) {
        if (this.rbPick != null) {
            this.rbEnd(n, n2);
            return;
        }
        this.rbPick = tableau.pick(n, n2, false);
        if (this.rbPick == null) {
            return;
        }
        this.undoStack.push(new UndoableShape(2, this.tableau, this.rbPick.shape));
        tableau.removeOne(this.rbPick.shape);
        this.rbPick.shape = (Shape)this.rbPick.shape.clone();
        final Graphics graphics = this.tableauPanel.getGraphics();
        final Point location = tableau.getLocation();
        this.rbPick.moveBy(location.x, location.y);
        tableau.xorOne(graphics, this.rbPick);
    }
    
    void rbDrag(final int n, final int n2) {
        if (this.rbPick == null) {
            return;
        }
        final Graphics graphics = this.tableauPanel.getGraphics();
        this.tableau.xorOne(graphics, this.rbPick);
        this.rbPick.moveBy(n - this.lastX, n2 - this.lastY);
        this.tableau.xorOne(graphics, this.rbPick);
    }
    
    void rbEnd(final int n, final int n2) {
        if (this.rbPick == null) {
            return;
        }
        this.tableau.xorOne(this.tableauPanel.getGraphics(), this.rbPick);
        int n3 = n;
        if (this.rbPick.isCopy()) {
            n3 -= this.shapeMenu.getSize().width;
        }
        if (n3 == 0 || n2 == 0 || n3 == this.tableau.getSize().width - 1 || n2 == this.tableau.getSize().height - 1) {
            this.rbPick = null;
            final UndoableShape undoableShape = (UndoableShape)this.undoStack.getCurrent();
            if (undoableShape.getOperation() == 1) {
                this.undoStack.pop();
            }
            undoableShape.setOperation(2);
            return;
        }
        final double y_db = this.tableau.y_db(n2);
        final double x_db = this.tableau.x_db(n3);
        if (x_db < this.tableau.getMinX() || x_db > this.tableau.getMaxX() || y_db < this.tableau.getMinY() || y_db > this.tableau.getMaxY()) {
            this.rbPick = null;
            final UndoableShape undoableShape2 = (UndoableShape)this.undoStack.getCurrent();
            if (undoableShape2.getOperation() == 1) {
                this.undoStack.pop();
            }
            undoableShape2.setOperation(2);
            return;
        }
        this.rbPick.shape.moveBy(this.tableau.x_world(n) - this.rbPick.getRefX(), this.tableau.y_world(n2) - this.rbPick.getRefY());
        this.snapVertex(this.rbPick, n);
        final UndoableShape undoableShape3 = (UndoableShape)this.undoStack.getCurrent();
        undoableShape3.setStateAfter(this.rbPick.shape);
        if (undoableShape3.getOperation() == 2) {
            undoableShape3.setOperation(0);
        }
        this.tableau.add(this.rbPick.shape);
        this.forceRepaint();
        this.rbPick = null;
    }
    
    public synchronized void redo() {
        synchronized (this.tableau) {
            final Graphics graphics = this.getGraphics();
            // monitorenter(graphics)
            try {
                this.undoStack.redo();
                this.forceRepaint();
            }
            // monitorexit(graphics)
            finally {}
        }
        // monitorexit(this.tableau)
    }
    
    void rotateClick(final Tableau tableau, final int n, final int n2, final int n3) {
        if (this.rbPick == null) {
            return;
        }
        final Graphics graphics = this.tableauPanel.getGraphics();
        tableau.xorOne(graphics, this.rbPick);
        tableau.removeOne(this.rbPick.shape);
        if (this.rbPick.isCopy()) {
            tableau.xorOne(graphics, this.rbPick = this.shapeMenu.pick(n, n2, false));
            tableau.removeOne(this.rbPick.shape);
            tableau.add(this.rbPick.shape);
            this.rbPick = null;
            this.undoStack.pop();
            return;
        }
        final double[] center = this.rbPick.shape.getCenter();
        this.rbPick.shape.turnClick(n3);
        final double[] center2 = this.rbPick.shape.getCenter();
        this.rbPick.shape.moveBy(center[0] - center2[0], center[1] - center2[1]);
        final UndoableShape undoableShape = (UndoableShape)this.undoStack.getCurrent();
        undoableShape.setStateAfter(this.rbPick.shape);
        if (undoableShape.getOperation() == 2) {
            undoableShape.setOperation(0);
        }
        tableau.add(this.rbPick.shape);
        this.rbPick = null;
    }
    
    public String saveData() {
        return this.tableau.print();
    }
    
    public void setBackgroundColor(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
        if (!ShapeGame.firstTime) {
            this.beginTableauEdit();
        }
        final Color color = Tableau.backPalette.getColor(backgroundColor);
        this.tableau.newBackground(backgroundColor);
        if (!ShapeGame.firstTime) {
            this.endTableauEdit();
        }
        ShapeGame.firstTime = false;
        this.shapeMenu.newBackground(211);
        this.paintMenu.setBackground(color);
        this.paintMenu.needRepaint = true;
        this.paintMenu.repaint();
        this.forceRepaint();
    }
    
    public void setBackgroundColorNoUndo(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
        final Color color = Tableau.backPalette.getColor(backgroundColor);
        this.tableau.newBackground(backgroundColor);
        this.shapeMenu.newBackground(211);
        this.paintMenu.setBackground(color);
        this.paintMenu.needRepaint = true;
        this.paintMenu.repaint();
        this.forceRepaint();
    }
    
    public void setEdgeLength(final int n) {
        Tableau.setEdgeLength(n);
    }
    
    public void setOutline(final int outlineNoUndo) {
        this.beginTableauEdit();
        this.setOutlineNoUndo(outlineNoUndo);
        this.endTableauEdit();
    }
    
    public void setOutlineNoUndo(final int outlineColor) {
        if (outlineColor < 0) {
            this.tableau.setOutline(false);
        }
        else {
            this.tableau.setOutline(true);
            this.tableau.outlineColor = outlineColor;
        }
        this.tableau.setNeedRepaint(true);
        this.shapeMenu.setNeedRepaint(true);
        this.paintMenu.setNeedRepaint(true);
        this.repaint();
    }
    
    public void setPaintColor(final int paintingColor) {
        this.paintingColor = paintingColor;
    }
    
    public void setPaintMode(final boolean paintMode) {
        this.paintMode = paintMode;
    }
    
    public void setSnap(final int pickResolution) {
        Tableau.setPickResolution(pickResolution);
    }
    
    void snapVertex(final Pick pick, final int n) {
        final double n2 = this.tableau.x_db(n + Tableau.pickPixelTolerance) - this.tableau.x_db(n);
        final double[][] pts = pick.shape.getPts();
        Pick pick2 = null;
        int i;
        for (i = 0; i < pts[0].length; ++i) {
            pick2 = this.tableau.pick(pts[0][i], pts[1][i], true, this.tableau);
            if (pick2 != null) {
                break;
            }
        }
        if (pick2 != null) {
            final double[][] pts2 = pick2.shape.getPts();
            for (int j = 0; j < pts2[0].length; ++j) {
                if (Math.abs(pts2[0][j] - pts[0][i]) <= n2 && Math.abs(pts2[1][j] - pts[1][i]) <= n2) {
                    pick.shape.moveBy(pts2[0][j] - pts[0][i], pts2[1][j] - pts[1][i]);
                    break;
                }
            }
        }
    }
    
    public void start() {
        this.setVisible(true);
        this.leftPanel.doLayout();
        this.doLayout();
        this.menuShapes();
        this.zoomIn();
        this.repaint();
        this.loadPreset();
    }
    
    public synchronized void undo() {
        synchronized (this.tableau) {
            this.undoStack.undo();
            this.forceRepaint();
        }
        // monitorexit(this.tableau)
    }
    
    public void update(final Graphics graphics) {
        this.paintComponents(graphics);
    }
    
    public void zoomIn() {
        this.beginTableauEdit();
        this.tableau.setScale(this.zoomFactor);
        this.tableau.setNeedRepaint(true);
        this.repaint();
        this.endTableauEdit();
    }
    
    public void zoomOut() {
        this.beginTableauEdit();
        this.tableau.setScale(1.0 / this.zoomFactor);
        this.tableau.setNeedRepaint(true);
        this.repaint();
        this.endTableauEdit();
    }
}
