// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import java.awt.Shape;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import medusa.DemoTools;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import medusa.graph.UniqueEdge;
import java.util.Iterator;
import java.awt.Component;
import javax.swing.ToolTipManager;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import medusa.MedusaSettings;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import medusa.graph.Graph;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import javax.swing.ProgressMonitor;
import medusa.graph.Node;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class BasicGraphPanel extends BasicGraphRenderer implements MouseListener, MouseMotionListener, Runnable
{
    protected Thread relaxThread;
    public boolean running;
    Node marked;
    ProgressMonitor energyBar;
    ActionListener updateEnergyBar;
    Timer timer;
    public static final double offset = 0.3;
    double edgeLen;
    protected double scale;
    protected double absoluteScale;
    protected FRspring frSpring;
    protected ActionListener runFR;
    static final double INITIAL_TEMP = 1.0;
    double temperature;
    boolean cool;
    boolean coolToggle;
    Node pick;
    boolean pickfixed;
    double oldX;
    double oldY;
    Rectangle currentRect;
    Rectangle rectToDraw;
    Rectangle previousRectDrawn;
    boolean box;
    Graph visibleGraph;
    
    public void setGraph(final Graph graph) {
        this.graph = graph;
        this.updateNodes();
    }
    
    public void updateNodes() {
        this.calculateEdgeLength();
    }
    
    public void setHideWhenMove(final boolean hideWhenMove) {
        this.hideWhenMove = hideWhenMove;
    }
    
    public void setAlpha(final boolean alpha) {
        this.alpha = alpha;
    }
    
    public void changeFont(final int fontSize) {
        this.nodeFont = new Font("TimesNewRoman", 0, fontSize);
        this.fontSize = fontSize;
    }
    
    public void setNodeSize(int nodeSize) {
        if (nodeSize < 1) {
            nodeSize = 10;
        }
        this.nodeSize = nodeSize;
        this.correct = (int)(nodeSize / 2.0);
    }
    
    public void setBackgroundColor(final Color background) {
        this.setBackground(background);
    }
    
    public String colorToString(final Color color) {
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }
    
    public void setPretty(final boolean pretty) {
        this.pretty = pretty;
    }
    
    public void setArrows(final boolean arrow) {
        this.arrow = arrow;
    }
    
    public void setLabel(final boolean label) {
        this.label = label;
    }
    
    public void setConfidence(final boolean showConfidence) {
        this.showConfidence = showConfidence;
    }
    
    public void setAnnotation(final boolean showAnnotation) {
        this.showAnnotation = showAnnotation;
    }
    
    public void setBasicEdgeColor(final Color basicEdgeColor) {
        this.basicEdgeColor = basicEdgeColor;
    }
    
    public void setFontColor(final Color fontColor) {
        this.fontColor = fontColor;
    }
    
    public Color getColor(final Integer n) {
        return this.getStringSettings().getColor(n);
    }
    
    public void setColor(final Integer n, final Color color) {
        this.getStringSettings().setColor(n, color);
    }
    
    public double getEdgeLen() {
        return this.edgeLen;
    }
    
    public void calculateEdgeLength() {
        final int nodeSize = this.graph.getNodeSize();
        if (nodeSize > 0) {
            this.edgeLen = Math.sqrt(this.getPanelWidth() * this.getPanelHeight() / nodeSize);
        }
    }
    
    public void setEdgeLen(final double edgeLen) {
        if (edgeLen > 0.0) {
            this.edgeLen = edgeLen;
        }
        else {
            this.calculateEdgeLength();
        }
    }
    
    public void setScale(final double scale) {
        this.scale = scale;
    }
    
    public void setAbsoluteScale(final double absoluteScale) {
        this.absoluteScale = absoluteScale;
    }
    
    public void setTimeFrameXY(final int n, final int n2) {
        this.setTimeFrameXY(new Dimension(n, n2));
    }
    
    public void setTimeFrameXY(final Dimension dims) {
        this.dims = dims;
        this.setPanelWidth((int)(this.getPanelWidth() * this.scale));
        this.setPanelHeight((int)(this.getPanelHeight() * this.scale));
        this.panelWidth = (int)(dims.getWidth() * this.scale);
        this.panelHeight = (int)(dims.getHeight() * this.scale);
        this.rescaleNodes();
        this.calculateEdgeLength();
        this.dims = new Dimension(this.panelWidth, this.panelHeight);
        this.setPreferredSize(new Dimension(this.panelWidth, this.panelHeight));
        this.setSize(new Dimension(this.panelWidth, this.panelHeight));
    }
    
    public String getToolTipText(final MouseEvent mouseEvent) {
        final Node closest = this.getClosest(mouseEvent);
        if (closest == null) {
            return null;
        }
        if (this.showAnnotation) {
            return closest.getLabel();
        }
        return closest.getAnnotation();
    }
    
    public void initEdges() {
        this.autoFixOrientation();
        this.calculateEdgeLength();
    }
    
    private void rescaleNodes() {
        this.graph.rescaleNodes(this.scale);
    }
    
    public void updateStringSettings(final MedusaSettings stringSettings) {
        this.setStringSettings(stringSettings);
    }
    
    public BasicGraphPanel(final MedusaSettings stringSettings) {
        this.running = false;
        this.marked = null;
        this.scale = 1.0;
        this.absoluteScale = 1.0;
        this.runFR = new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                BasicGraphPanel.this.energyBar.setProgress(BasicGraphPanel.this.frSpring.getCurrent());
                if (BasicGraphPanel.this.energyBar.isCanceled() || BasicGraphPanel.this.frSpring.isDone()) {
                    BasicGraphPanel.this.timer.stop();
                    BasicGraphPanel.this.frSpring.stop();
                    BasicGraphPanel.this.energyBar.close();
                    BasicGraphPanel.this.setCursor(null);
                    BasicGraphPanel.this.repaint();
                }
            }
        };
        this.temperature = 1.0;
        this.cool = true;
        this.coolToggle = false;
        this.oldX = 0.0;
        this.oldY = 0.0;
        this.currentRect = null;
        this.rectToDraw = null;
        this.previousRectDrawn = new Rectangle();
        this.visibleGraph = null;
        this.graph = new Graph();
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.edgeLen = 100.0;
        this.setPreferredSize(new Dimension(this.getPanelWidth(), this.getPanelHeight()));
        this.addMouseListener(this);
        this.setStringSettings(stringSettings);
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(1000000);
        this.setToolTipText("");
    }
    
    public BasicGraphPanel() {
        this(new MedusaSettings());
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        this.temperature = 1.0;
        this.resetDelta();
        this.running = true;
        while (this.relaxThread == currentThread) {
            this.relax2();
            try {
                Thread.sleep(30L);
                continue;
            }
            catch (InterruptedException ex) {
                this.running = false;
            }
            break;
        }
        this.running = false;
    }
    
    public void start() {
        if (this.relaxThread == null) {
            this.relaxThread = new Thread(this);
        }
        this.relaxThread.start();
    }
    
    public void stop() {
        if (this.relaxThread != null) {
            this.relaxThread = null;
        }
    }
    
    public synchronized void energy() {
        this.energyBar = new ProgressMonitor(this, "Fruchterman Rheingold Algorithm", "", 0, 200);
        this.frSpring = new FRspring(this.graph, this.getPanelWidth(), this.getPanelHeight());
        this.timer = new Timer(500, this.runFR);
        this.energyBar.setProgress(0);
        this.energyBar.setMillisToDecideToPopup(1000);
        this.frSpring.start();
        this.timer.start();
    }
    
    public void setFix(final boolean fixed) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            nodesIterator.next().setFixed(fixed);
        }
        this.repaint();
    }
    
    public void invertFix() {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            node.setFixed(!node.isFixed());
        }
        this.repaint();
    }
    
    protected void edgeAttract() {
        final Iterator<UniqueEdge> uniqueEdgesIterator = this.graph.uniqueEdgesIterator();
        while (uniqueEdgesIterator.hasNext()) {
            final UniqueEdge uniqueEdge = uniqueEdgesIterator.next();
            final Node node = this.graph.getNode(uniqueEdge.getFromName());
            final Node node2 = this.graph.getNode(uniqueEdge.getToName());
            final double n = node2.getX() - node.getX();
            final double n2 = node2.getY() - node.getY();
            final double sqrt = Math.sqrt(n * n + n2 * n2);
            final double n3 = (sqrt == 0.0) ? 1.0E-4 : sqrt;
            final double n4 = (this.edgeLen - n3) / (n3 * 3.0);
            final double n5 = n4 * n;
            final double n6 = n4 * n2;
            node2.setDX(node2.getDX() + n5);
            node2.setDY(node2.getDY() + n6);
            node.setDX(node.getDX() + -n5);
            node.setDY(node.getDY() + -n6);
        }
    }
    
    protected void edgeAttract2() {
        final Iterator<UniqueEdge> uniqueEdgesIterator = this.graph.uniqueEdgesIterator();
        while (uniqueEdgesIterator.hasNext()) {
            final UniqueEdge uniqueEdge = uniqueEdgesIterator.next();
            final Node node = this.graph.getNode(uniqueEdge.getFromName());
            final Node node2 = this.graph.getNode(uniqueEdge.getToName());
            final double n = node2.getX() - node.getX();
            final double n2 = node2.getY() - node.getY();
            final double sqrt = Math.sqrt(n * n + n2 * n2);
            final double n3 = (sqrt == 0.0) ? 1.0E-5 : sqrt;
            final double n4 = -n3 * n;
            final double n5 = -n3 * n2;
            node2.setDX(node2.getDX() + n4);
            node2.setDY(node2.getDY() + n5);
            node.setDX(node.getDX() + -n4);
            node.setDY(node.getDY() + -n5);
        }
    }
    
    protected void nodesRepel2(final double n, final double n2) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            double n3 = 0.0;
            double n4 = 0.0;
            final Iterator<Node> nodesIterator2 = this.graph.nodesIterator();
            while (nodesIterator2.hasNext()) {
                final Node node2 = nodesIterator2.next();
                if (node.equals(node2)) {
                    continue;
                }
                double n5 = node.getX() - node2.getX();
                double n6 = node.getY() - node2.getY();
                if (n5 > n) {
                    n5 = node.getX() - node2.getX();
                }
                if (n5 < -n) {
                    n5 = node.getX() - node2.getX();
                }
                if (n6 > n2) {
                    n6 = node.getY() - node2.getY();
                }
                if (n6 < -n2) {
                    n6 = node.getY() - node2.getY();
                }
                final double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
                if (sqrt == 0.0) {
                    n3 += Math.random() / 100.0;
                    n4 += Math.random() / 100.0;
                }
                else {
                    if (sqrt >= 0.1) {
                        continue;
                    }
                    n3 += n5 / sqrt;
                    n4 += n6 / sqrt;
                }
            }
            node.setDX(node.getDX() + n3);
            node.setDY(node.getDY() + n4);
        }
    }
    
    protected void nodesRepel(final double n, final double n2) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            double n3 = 0.0;
            double n4 = 0.0;
            final Iterator<Node> nodesIterator2 = this.graph.nodesIterator();
            while (nodesIterator2.hasNext()) {
                final Node node2 = nodesIterator2.next();
                if (node.equals(node2)) {
                    continue;
                }
                double n5 = node.getX() - node2.getX();
                double n6 = node.getY() - node2.getY();
                if (n5 > n) {
                    n5 = node.getX() - node2.getX() - this.getPanelWidth();
                }
                if (n5 < -n) {
                    n5 = node.getX() - node2.getX() + this.getPanelWidth();
                }
                if (n6 > n2) {
                    n6 = node.getY() - node2.getY() - this.getPanelHeight();
                }
                if (n6 < -n2) {
                    n6 = node.getY() - node2.getY() + this.getPanelHeight();
                }
                final double n7 = n5 * n5 + n6 * n6;
                if (n7 == 0.0) {
                    n3 += Math.random();
                    n4 += Math.random();
                }
                else {
                    if (n7 >= this.getPanelWidth() * this.getPanelHeight() / 9) {
                        continue;
                    }
                    n3 += n5 / n7;
                    n4 += n6 / n7;
                }
            }
            final double n8 = n3 * n3 + n4 * n4;
            if (n8 > 0.0) {
                final double n9 = Math.sqrt(n8) / 3.0;
                node.setDX(node.getDX() + n3 / n9);
                node.setDY(node.getDY() + n4 / n9);
            }
        }
    }
    
    protected synchronized void relax2() {
        final int n = 10;
        final int n2 = 10;
        final double n3 = this.getPanelWidth() * 3 / 4;
        final double n4 = this.getPanelHeight() * 3 / 4;
        this.edgeAttract();
        this.nodesRepel(n3, n4);
        this.moveNodes(n, n2);
        this.repaint();
        if (this.cool & this.coolToggle) {
            this.temperature -= 0.02;
            this.temperature = Math.max(this.temperature, 0.0);
        }
    }
    
    protected void moveNodes2(final int n, final int n2) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            final double n3 = 0.001;
            if (Math.abs(node.getDX()) < n3) {
                node.setDX(0.0);
            }
            if (Math.abs(node.getDY()) < n3) {
                node.setDY(0.0);
            }
            if (this.cool & this.coolToggle) {
                node.setDX(node.getDX() * this.temperature);
                node.setDY(node.getDY() * this.temperature);
            }
            if (!node.isFixed()) {
                node.setX(node.getX() + node.getDX());
                node.setY(node.getY() + node.getDY());
            }
            node.setDX(node.getDX() / 2.0);
            node.setDY(node.getDY() / 2.0);
        }
    }
    
    protected void moveNodes(final int n, final int n2) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (Math.abs(node.getDX()) < 1.0) {
                node.setDX(0.0);
            }
            if (Math.abs(node.getDY()) < 1.0) {
                node.setDY(0.0);
            }
            if (this.cool & this.coolToggle) {
                node.setDX(node.getDX() * this.temperature);
                node.setDY(node.getDY() * this.temperature);
            }
            if (!node.isFixed()) {
                node.setX(node.getX() + Math.max(-5.0, Math.min(5.0, node.getDX())));
                node.setY(node.getY() + Math.max(-5.0, Math.min(5.0, node.getDY())));
            }
            if (node.getX() < n) {
                node.setX(n);
            }
            else if (node.getX() > this.getPanelWidth() - 2 * n) {
                node.setX(this.getPanelWidth() - 2 * n);
            }
            if (node.getY() < n + n2) {
                node.setY(n + n2);
            }
            else if (node.getY() > this.getPanelHeight() - n) {
                node.setY(this.getPanelHeight() - n);
            }
            node.setDX(node.getDX() / 2.0);
            node.setDY(node.getDY() / 2.0);
        }
    }
    
    public void setCool(final boolean coolToggle) {
        this.coolToggle = coolToggle;
    }
    
    private void resetDelta() {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            node.setDY(0.0);
            node.setDX(0.0);
        }
    }
    
    protected synchronized void gravity() {
        final double n = 9.81;
        final double n2 = 0.1;
        final double n3 = n * n2;
        final double n4 = 0.9;
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (!node.isFixed()) {
                node.setDY(node.getDY() + n3);
                node.setY(node.getY() + 0.5 * n3 * n2 + node.getDY() * n2);
                if (node.getY() <= 600.0) {
                    continue;
                }
                node.setDY(-node.getDY() * n4);
                node.setY(600.0);
            }
        }
        this.repaint();
    }
    
    public void rotate(final double n) {
        final double n2 = this.getPanelWidth() / 2;
        final double n3 = this.getPanelHeight() / 2;
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            final double n4 = node.getX() - n2;
            final double n5 = node.getY() - n3;
            node.setX(n4 * cos - n5 * sin + n2);
            node.setY(n4 * sin + n5 * cos + n3);
        }
    }
    
    public void mirror(final boolean b, final boolean b2) {
        if (b) {
            final Iterator<Node> nodesIterator = this.graph.nodesIterator();
            while (nodesIterator.hasNext()) {
                final Node node = nodesIterator.next();
                node.setX(this.getPanelWidth() - node.getX());
            }
        }
        if (b2) {
            final Iterator<Node> nodesIterator2 = this.graph.nodesIterator();
            while (nodesIterator2.hasNext()) {
                final Node node2 = nodesIterator2.next();
                node2.setY(this.getPanelWidth() - node2.getY());
            }
        }
    }
    
    public void changeNodeColor(final int n, final int n2, final int n3) {
        final Color color = new Color(n, n2, n3);
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (node.isFixed()) {
                node.setColor(color);
            }
        }
    }
    
    public void changeNodeColor(final Color color) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (node.isFixed()) {
                node.setColor(color);
            }
        }
    }
    
    public void saveImage(final String s, final int n) {
        final Dimension size = this.getSize();
        final BufferedImage bufferedImage = new BufferedImage((int)size.getWidth(), (int)size.getHeight(), 1);
        final Graphics2D graphics = bufferedImage.createGraphics();
        this.paint(graphics);
        graphics.dispose();
        try {
            final File file = new File(s);
            if (n == 0) {
                ImageIO.write(bufferedImage, "jpg", file);
            }
            if (n == 1) {
                ImageIO.write(bufferedImage, "png", file);
            }
            bufferedImage.flush();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setNodeFixed(final String s, final boolean fixed) {
        this.graph.getNode(s).setFixed(fixed);
    }
    
    protected Node getClosest(final MouseEvent mouseEvent) {
        final int n = 50;
        Node node = null;
        double n2 = Double.MAX_VALUE;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node2 = nodesIterator.next();
            if (node2.getShape() == 4) {
                final int n3 = 25;
                final int n4 = 2;
                if (!PaintTools.getShape(node2.getShape(), (int)node2.getX() - n3 / 2 + n4 / 2 - n3 / 2, (int)node2.getY() - n3 / 2 + n4 / 2 + (int)(n3 * 0.75 / 4.0), n3 - 2 * n4).contains(x, y)) {
                    continue;
                }
                node = node2;
            }
            else {
                final double n5 = (node2.getX() - x) * (node2.getX() - x) + (node2.getY() - y) * (node2.getY() - y);
                if (!(n5 < n & n5 < n2)) {
                    continue;
                }
                n2 = n5;
                node = node2;
            }
        }
        return node;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 1) {
            this.handlePressLeftButton(mouseEvent);
        }
    }
    
    public void handlePressLeftButton(final MouseEvent mouseEvent) {
        if (this.pick != null) {
            return;
        }
        final Node closest = this.getClosest(mouseEvent);
        if (mouseEvent.isControlDown()) {
            if (closest != null) {
                closest.setFixed(!closest.isFixed());
            }
            this.pick = null;
            mouseEvent.consume();
            return;
        }
        if (mouseEvent.isShiftDown()) {
            this.addMouseMotionListener(this);
            if (closest != null) {
                this.setPick(closest, mouseEvent);
            }
            mouseEvent.consume();
            return;
        }
        if (mouseEvent.getModifiers() == 16) {
            this.addMouseMotionListener(this);
            if (closest != null) {
                this.setPick(closest, mouseEvent);
            }
            else {
                this.currentRect = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), 0, 0);
                this.updateDrawableRect(this.getWidth(), this.getHeight());
                this.repaint();
            }
            mouseEvent.consume();
        }
    }
    
    private void setPick(final Node pick, final MouseEvent mouseEvent) {
        this.pick = pick;
        if (this.hideWhenMove) {
            this.visibleGraph = this.graph.subGraph(pick);
        }
        this.pickfixed = this.pick.isFixed();
        this.pick.setFixed(true);
        this.pick.setX(mouseEvent.getX());
        this.pick.setY(mouseEvent.getY());
        this.oldX = this.pick.getX();
        this.oldY = this.pick.getY();
        this.temperature = 1.0;
        this.cool = false;
    }
    
    public void handlePressLeftButton_backup(final MouseEvent mouseEvent) {
        if (this.pick == null && mouseEvent.getButton() == 1 && (mouseEvent.getModifiers() == 16 | mouseEvent.getModifiers() == 17 | mouseEvent.getModifiers() == 18)) {
            this.addMouseMotionListener(this);
            final Node closest = this.getClosest(mouseEvent);
            if (closest != null) {
                if (mouseEvent.getModifiers() == 18) {
                    closest.setFixed(!closest.isFixed());
                    this.pick = null;
                }
                else {
                    this.pick = closest;
                    this.pickfixed = this.pick.isFixed();
                    this.pick.setFixed(true);
                    this.pick.setX(mouseEvent.getX());
                    this.pick.setY(mouseEvent.getY());
                    this.oldX = this.pick.getX();
                    this.oldY = this.pick.getY();
                    this.temperature = 1.0;
                    this.cool = false;
                }
                this.repaint();
            }
            else {
                this.currentRect = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), 0, 0);
                this.updateDrawableRect(this.getWidth(), this.getHeight());
                this.repaint();
            }
            mouseEvent.consume();
        }
    }
    
    public boolean findLabel(final String s) {
        final Pattern compile = Pattern.compile(s);
        if (s.compareTo("") == 0) {
            return false;
        }
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            final Matcher matcher = compile.matcher(node.getLabel());
            node.setFixed(false);
            if (matcher.find()) {
                node.setFixed(true);
            }
        }
        return true;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.handleReleaseLeftButton(mouseEvent);
    }
    
    protected void handleReleaseLeftButton(final MouseEvent mouseEvent) {
        if (mouseEvent.getButton() != 1) {
            return;
        }
        this.removeMouseMotionListener(this);
        if (mouseEvent.getModifiers() == 16) {
            if (this.pick != null) {
                this.pick.setX(mouseEvent.getX());
                this.pick.setY(mouseEvent.getY());
                this.pick.setFixed(this.pickfixed);
                this.cool = true;
            }
            else {
                this.updateSize(mouseEvent);
                this.fixNodesInRect(this.rectToDraw);
                this.currentRect = null;
            }
        }
        if (mouseEvent.isShiftDown() && this.pick != null) {
            this.moveFixed(mouseEvent.getX() - this.oldX, mouseEvent.getY() - this.oldY);
            this.pick.setFixed(this.pickfixed);
        }
        if (mouseEvent.isControlDown()) {
            if (this.pick == null & this.currentRect != null) {
                this.updateSize(mouseEvent);
                this.unFixNodesInRect(this.rectToDraw);
                this.currentRect = null;
            }
            else if (this.pick != null) {
                this.pick.setX(mouseEvent.getX());
                this.pick.setY(mouseEvent.getY());
                this.pick.setFixed(!this.pick.isFixed());
            }
        }
        this.pick = null;
        this.repaint();
        mouseEvent.consume();
    }
    
    protected void fixNodesInRect(final Rectangle rectangle) {
        if (rectangle == null) {
            return;
        }
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (rectangle.contains(node.getX(), node.getY())) {
                node.setFixed(true);
            }
        }
    }
    
    protected void unFixNodesInRect(final Rectangle rectangle) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (rectangle.contains(node.getX(), node.getY())) {
                node.setFixed(false);
            }
        }
    }
    
    private void updateSize(final MouseEvent mouseEvent) {
        if (this.currentRect == null) {
            return;
        }
        this.currentRect.setSize(mouseEvent.getX() - this.currentRect.x, mouseEvent.getY() - this.currentRect.y);
        this.updateDrawableRect(this.getPanelWidth(), this.getPanelHeight());
        final Rectangle union = this.rectToDraw.union(this.previousRectDrawn);
        this.repaint(union.x, union.y, union.width, union.height);
    }
    
    private void updateDrawableRect(final int n, final int n2) {
        int x = this.currentRect.x;
        int y = this.currentRect.y;
        int width = this.currentRect.width;
        int height = this.currentRect.height;
        if (width < 0) {
            width = 0 - width;
            x = x - width + 1;
            if (x < 0) {
                width += x;
                x = 0;
            }
        }
        if (height < 0) {
            height = 0 - height;
            y = y - height + 1;
            if (y < 0) {
                height += y;
                y = 0;
            }
        }
        if (this.rectToDraw != null) {
            this.previousRectDrawn.setBounds(this.rectToDraw.x, this.rectToDraw.y, this.rectToDraw.width, this.rectToDraw.height);
            this.rectToDraw.setBounds(x, y, width, height);
        }
        else {
            this.rectToDraw = new Rectangle(x, y, width, height);
        }
    }
    
    protected void moveFixed(final double n, final double n2) {
        final Iterator<Node> nodesIterator = this.graph.nodesIterator();
        while (nodesIterator.hasNext()) {
            final Node node = nodesIterator.next();
            if (node.isFixed() & node != this.pick) {
                node.setX(node.getX() + n);
                node.setY(node.getY() + n2);
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.handleLeftMouseDragged(mouseEvent);
    }
    
    public void handleLeftMouseDragged(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (this.pick != null & mouseEvent.getModifiers() != 18) {
                this.pick.setX(mouseEvent.getX());
                this.pick.setY(mouseEvent.getY());
                this.repaint();
            }
            else if (this.pick == null) {
                this.updateSize(mouseEvent);
            }
        }
        mouseEvent.consume();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void autoFixOrientation() {
        this.graph.autoFixOrientation();
    }
    
    public void randomGraph(final int n, final int n2) {
        (this.graph = new DemoTools().randomGraph(n, n2)).rescaleNodes(600);
        this.updateNodes();
    }
    
    public synchronized void paintComponent(final Graphics graphics) {
        final Graphics2D prePaint = this.prePaint(graphics);
        this.paintNet(prePaint);
        if (this.currentRect != null) {
            prePaint.setStroke(new BasicStroke(1.0f, 0, 0, 10.0f, new float[] { 2.0f, 3.0f, 2.0f, 3.0f }, 0.0f));
            prePaint.drawRect(this.rectToDraw.x, this.rectToDraw.y, this.rectToDraw.width - 1, this.rectToDraw.height - 1);
        }
        prePaint.setStroke(new BasicStroke(1.0f));
    }
    
    public void paintPick(final Graphics2D graphics2D) {
        final Node pick = this.pick;
        graphics2D.setPaint(pick.getColor());
        final Shape shape = PaintTools.getShape(pick.getShape(), (int)pick.getX() - this.correct, (int)pick.getY() - this.correct, this.nodeSize);
        graphics2D.fill(shape);
        graphics2D.setPaint(Color.black);
        if (pick.isFixed()) {
            graphics2D.setPaint(Color.yellow);
        }
        graphics2D.draw(shape);
    }
}
