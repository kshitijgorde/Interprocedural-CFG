// 
// Decompiled by Procyon v0.5.30
// 

package medusa.display;

import medusa.DataFormatException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import medusa.graph.BasicGraph;
import medusa.graph.Graph;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Composite;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import medusa.graph.Edge;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import medusa.MedusaSettings;
import medusa.dataio.DataLoader;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import medusa.graph.Node;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class EditableGraphPanel extends BasicGraphPanel
{
    BufferedImage image;
    Image tempImage;
    public boolean showBorder;
    protected boolean showName;
    Frame f;
    int startx;
    int starty;
    int endx;
    int endy;
    boolean drawTempLine;
    Node temp1;
    Node temp2;
    boolean[] showEdges;
    int border;
    JPopupMenu popup;
    JMenuItem removeItem;
    JMenuItem editColorItem;
    JMenuItem editShapeItem;
    JMenuItem setAnnotationItem;
    JMenuItem getPositionItem;
    DataLoader dl;
    
    public EditableGraphPanel(final MedusaSettings stringSettings) {
        super(stringSettings);
        this.image = null;
        this.tempImage = null;
        this.showBorder = true;
        this.showName = true;
        this.f = new Frame();
        this.drawTempLine = false;
        this.temp1 = null;
        this.temp2 = null;
        this.border = 10;
        this.dl = new DataLoader(this.getPanelWidth(), this.getPanelHeight());
        this.initPopup();
        this.tryImage();
        this.initShow();
    }
    
    public EditableGraphPanel() {
        this.image = null;
        this.tempImage = null;
        this.showBorder = true;
        this.showName = true;
        this.f = new Frame();
        this.drawTempLine = false;
        this.temp1 = null;
        this.temp2 = null;
        this.border = 10;
        this.dl = new DataLoader(this.getPanelWidth(), this.getPanelHeight());
        this.initPopup();
        this.tryImage();
        this.initShow();
    }
    
    private void tryImage() {
        try {
            this.setImage();
            this.tempImage = this.image;
        }
        catch (Exception ex) {}
    }
    
    public void setImage() throws IOException {
        this.setImage("netview_default.png");
    }
    
    public void setImage(final String imageURL) throws IOException {
        final File imageFile = new File(imageURL);
        this.image = ImageIO.read(imageFile);
    }
    
    public void setImage(final BufferedImage myImage) throws InterruptedException {
        this.image = myImage;
    }
    
    public void clearImage() {
        this.image = null;
    }
    
    public void drawBackgroundImage(final Graphics2D g2d) {
        if (this.image == null) {
            return;
        }
        final Dimension d = this.getPreferredSize();
        g2d.drawImage(this.image, 0, 0, d.width, d.height, null);
    }
    
    public void paintEdge(final Graphics2D g, final Edge e) {
        if (!this.showEdges[e.getType() - 1]) {
            return;
        }
        super.paintEdge(g, e);
    }
    
    public void setShowBorder(final boolean show) {
        this.showBorder = show;
    }
    
    public void paintNet(final Graphics2D g2d) {
        if (this.showBorder) {
            final Rectangle2D.Double border = new Rectangle2D.Double(2.0, 2.0, this.getPanelWidth(), this.getPanelHeight());
            g2d.draw(border);
        }
        final Iterator edges = this.graph.edgesIterator();
        while (edges.hasNext()) {
            final Edge e = edges.next();
            final int col = e.getType();
            if (this.showEdges[col - 1]) {
                this.paintEdge(g2d, e);
            }
        }
        g2d.setComposite(this.makeComposite(1.0f));
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            this.paintNode(g2d, node);
        }
    }
    
    public void saveImage(final String path, final int param) {
        this.setShowBorder(false);
        super.saveImage(path, param);
        this.setShowBorder(true);
    }
    
    public synchronized void paintComponent(final Graphics g) {
        final Graphics2D g2d = this.prePaint(g);
        this.drawBackgroundImage(g2d);
        if (this.hideWhenMove) {
            if (!this.running & this.pick != null) {
                this.paintVisibleNet(g2d);
            }
            else {
                this.paintNet(g2d);
            }
        }
        else {
            this.paintNet(g2d);
        }
        this.drawBox(g2d);
        if (this.drawTempLine) {
            final float[] dashPattern = { 2.0f, 3.0f, 2.0f, 3.0f };
            g2d.setStroke(new BasicStroke(1.0f, 0, 0, 10.0f, dashPattern, 0.0f));
            g2d.drawLine(this.startx, this.starty, this.endx, this.endy);
        }
        g2d.setStroke(new BasicStroke(1.0f));
    }
    
    public void paintVisibleNet(final Graphics2D g2d) {
        if (this.showBorder) {
            final Rectangle2D.Double border = new Rectangle2D.Double(2.0, 2.0, this.getPanelWidth(), this.getPanelHeight());
            g2d.draw(border);
        }
        final Iterator edges = this.visibleGraph.edgesIterator();
        while (edges.hasNext()) {
            final Edge e = edges.next();
            final int col = e.getType();
            if (this.showEdges[col - 1]) {
                this.paintEdge(g2d, e);
            }
        }
        g2d.setComposite(this.makeComposite(1.0f));
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            this.paintNode(g2d, node);
        }
    }
    
    protected void drawBox(final Graphics2D g2d) {
        if (this.currentRect != null) {
            g2d.setXORMode(Color.red);
            final float[] dashPattern = { 2.0f, 3.0f, 2.0f, 3.0f };
            g2d.setStroke(new BasicStroke(1.0f, 0, 0, 10.0f, dashPattern, 0.0f));
            g2d.drawRect(this.rectToDraw.x, this.rectToDraw.y, this.rectToDraw.width - 1, this.rectToDraw.height - 1);
        }
    }
    
    public void setShowName(final boolean b) {
        this.showName = b;
        this.showAnnotation = !b;
    }
    
    private void handleRightMouseDragged(final MouseEvent e) {
        if ((e.getModifiers() & 0x4) != 0x0) {
            this.drawTempLine = true;
            this.endx = e.getX();
            this.endy = e.getY();
            this.repaint();
        }
        e.consume();
    }
    
    public void mouseDragged(final MouseEvent e) {
        this.handleLeftMouseDragged(e);
        this.handleRightMouseDragged(e);
    }
    
    public void handleReleaseRightButton(final MouseEvent e) {
        if (e.getButton() != 3) {
            return;
        }
        this.removeMouseMotionListener(this);
        final Node node = this.getClosest(e);
        if (node != null) {
            this.temp2 = node;
        }
        else {
            this.temp2 = new Node("unnamed", e.getX(), e.getY());
        }
        this.drawTempLine = false;
        this.endx = e.getX();
        this.endy = e.getY();
        if (this.temp1 != this.temp2) {
            this.addEdge();
            this.repaint();
            this.temp1 = null;
            this.temp2 = null;
        }
        else {
            this.showPopup(e);
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.handleReleaseLeftButton(e);
        this.handleReleaseRightButton(e);
    }
    
    private void handlePressRightButton(final MouseEvent e) {
        if (e.getButton() == 3) {
            this.addMouseMotionListener(this);
            final Node node = this.getClosest(e);
            if (node != null) {
                this.temp1 = node;
                this.startx = (int)this.temp1.getX();
                this.starty = (int)this.temp1.getY();
            }
            else {
                this.startx = e.getX();
                this.starty = e.getY();
                this.temp1 = new Node("unnamed", this.startx, this.starty);
            }
            e.consume();
        }
    }
    
    public void mousePressed(final MouseEvent e) {
        this.handlePressLeftButton(e);
        this.handlePressRightButton(e);
    }
    
    protected void addEdge() {
        final Edge e = EdgeDialog.showDialog(this.f, null, this.temp1, this.temp2, this.stringSettings);
        if (e != null) {
            this.temp1.setLabel(e.getFromName());
            this.temp2.setLabel(e.getToName());
            this.graph.addEdge(e);
            this.graph.setNode(this.temp1);
            this.graph.setNode(this.temp2);
            this.updateNodes();
        }
    }
    
    public void initShow() {
        final int max = 40;
        this.showEdges = new boolean[40];
        for (int i = 0; i < 40; ++i) {
            this.showEdges[i] = true;
        }
    }
    
    public void setShowEdge(final int i, final boolean b) {
        this.showEdges[i] = b;
    }
    
    public void setHideEdge(final int i, final boolean b) {
        this.showEdges[i] = !b;
    }
    
    public void setShape(final int shape) {
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            if (node.isFixed()) {
                node.setShape(shape);
            }
        }
    }
    
    private void initPopup() {
        this.popup = new JPopupMenu("Edit data");
        this.removeItem = new JMenuItem("Delete");
        this.editColorItem = new JMenuItem("Color");
        this.editShapeItem = new JMenuItem("Shape");
        this.setAnnotationItem = new JMenuItem("Annotation");
        this.getPositionItem = new JMenuItem("Position");
        this.removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (EditableGraphPanel.this.temp1 != null) {
                    EditableGraphPanel.this.removeEdgeByLabel(EditableGraphPanel.this.temp1.getLabel());
                    EditableGraphPanel.this.temp1 = null;
                    EditableGraphPanel.this.temp2 = null;
                    EditableGraphPanel.this.repaint();
                }
                else {
                    System.out.println("node is null, for some strange reason");
                }
            }
        });
        this.editColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Color newColor = JColorChooser.showDialog(EditableGraphPanel.this.getRootPane(), "Choose node color", EditableGraphPanel.this.temp1.getColor());
                if (newColor != null) {
                    EditableGraphPanel.this.temp1.setColor(newColor);
                }
                EditableGraphPanel.this.repaint();
            }
        });
        this.editShapeItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                EditableGraphPanel.this.temp1.setShape(EditableGraphPanel.this.chooseShape());
                EditableGraphPanel.this.repaint();
            }
        });
        this.setAnnotationItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                EditableGraphPanel.this.setAnnotation(EditableGraphPanel.this.temp1);
            }
        });
        this.getPositionItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                EditableGraphPanel.this.getPosition(EditableGraphPanel.this.temp1);
            }
        });
        this.popup.add(this.removeItem);
        this.popup.addSeparator();
        this.popup.add(this.editColorItem);
        this.popup.add(this.editShapeItem);
        this.popup.add(this.setAnnotationItem);
        this.popup.add(this.getPositionItem);
    }
    
    private void getPosition(final Node temp) {
        JOptionPane.showMessageDialog(this, "x: " + temp.getX() + " y:" + temp.getY());
    }
    
    private void setAnnotation(final Node temp) {
        final String s = JOptionPane.showInputDialog("View or edit node annotation:\n", temp.getAnnotation());
        if (s != null && s.length() > 0) {
            temp.setAnnotation(s);
        }
    }
    
    private int chooseShape() {
        final Object[] shapes = { "circle", "rectangle", "triangle", "diamond" };
        final int s = JOptionPane.showOptionDialog(this, "Choose a node shape", "Shape", 1, 3, null, shapes, "circle");
        return s;
    }
    
    private void showPopup(final MouseEvent e) {
        this.popup.show(e.getComponent(), e.getX(), e.getY());
        e.consume();
    }
    
    public ArrayList getFixed() {
        return this.graph.getFixed();
    }
    
    public void copyNodeSettings(final Graph g) {
        this.graph.copyNodeSettings(g);
    }
    
    public void removeEdgeByLabel(final String label) {
        this.graph.removeEdgeByLabel(label);
    }
    
    public void removeFixedNodes() {
        this.graph.removeFixed();
    }
    
    public void crop(final double confidence) {
        this.graph.cropEdges(confidence);
        this.calculateEdgeLength();
    }
    
    public void markNode(final Graphics2D g2d, final Node markedNode) {
        if (markedNode == null) {
            return;
        }
        final int refX = this.getPanelWidth() / 2;
        final int refY = this.getPanelHeight() / 2;
        final double r45 = 0.4487989505128276;
        final double deltaLength = 15.0;
        final double headLength = 13.0;
        final double x = markedNode.getX();
        final double y = markedNode.getY();
        final double deltaX = x - refX;
        final double deltaY = -(y - refY);
        double theta = Math.atan(deltaY / deltaX);
        if (deltaX < 0.0) {
            theta += 3.141592653589793;
        }
        final int xh = (int)(x - deltaLength * Math.cos(theta));
        final int yh = (int)(y + deltaLength * Math.sin(theta));
        final int xc = (int)(xh - headLength * Math.cos(theta - r45));
        final int yc = (int)(yh + headLength * Math.sin(theta - r45));
        final int xa = (int)(xh - headLength * Math.cos(theta + r45));
        final int ya = (int)(yh + headLength * Math.sin(theta + r45));
        final int shad = 3;
        final int[] xpoints = { xc, xh, xa };
        final int[] ypoints = { yc, yh, ya };
        final int[] shadowX = { xc + shad, xh + shad, xa + shad };
        final int[] shadowY = { yc + shad, yh + shad, ya + shad };
        final int npoints = 3;
        g2d.setColor(Color.black);
        g2d.fillPolygon(shadowX, shadowY, npoints);
        g2d.setColor(Color.orange);
        g2d.fillPolygon(xpoints, ypoints, npoints);
        g2d.setColor(Color.black);
    }
    
    public int selectNodeByRegExp(final String regexp) {
        int matches = 0;
        final Pattern labelPattern = Pattern.compile(regexp);
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            final Matcher matcher = labelPattern.matcher(node.getLabel());
            if (matcher.find()) {
                node.setFixed(true);
                ++matches;
            }
        }
        return matches;
    }
    
    public int selectNodeFromFile(final String fileName) throws IOException {
        int matches = 0;
        final File loadFile = new File(fileName);
        final BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(loadFile)));
        String inLine;
        while ((inLine = in.readLine()) != null) {
            final Iterator it = this.graph.nodesIterator();
            while (it.hasNext()) {
                final Node node = it.next();
                if (node.getLabel().compareTo(inLine) == 0) {
                    ++matches;
                    node.setFixed(true);
                }
            }
        }
        in.close();
        return matches;
    }
    
    public void manipulateColor(final int a, final int b, final boolean doSwitch) {
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            if (node.isFixed()) {
                node.manipulateColorElement(a, b, doSwitch);
            }
        }
    }
    
    public void addGradientX(final int channel) {
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            if (node.isFixed()) {
                final int value = (int)(node.getX() / this.getPanelWidth() * 255.0);
                node.manipulateChannel(channel, value);
            }
        }
    }
    
    public void addGradientY(final int channel) {
        final Iterator it = this.graph.nodesIterator();
        while (it.hasNext()) {
            final Node node = it.next();
            if (node.isFixed()) {
                final int value = (int)(node.getY() / this.getPanelWidth() * 255.0);
                node.manipulateChannel(channel, value);
            }
        }
    }
    
    public void clearGraph() {
        this.graph.clear();
        this.updateNodes();
    }
    
    public void loadGraph(final String fileName) throws IOException, DataFormatException {
        this.graph = this.dl.load(fileName);
        this.updateNodes();
    }
    
    public void saveGraph(final String fileName) throws IOException {
        this.dl.save(this.graph, fileName, this.scale);
        this.updateNodes();
    }
    
    public void writePajek(final String fileName) throws IOException {
        this.dl.saveAsPajek(this.graph, fileName);
    }
    
    public void writePS(final String fileName) throws IOException {
        this.dl.saveAsPS(this.graph, fileName, this.stringSettings, this.nodeSize, this.fontSize);
    }
    
    public void writeEPS(final String fileName) throws IOException {
        this.dl.saveAsEPS(this.graph, fileName, this.stringSettings, this.nodeSize, this.fontSize);
    }
    
    public void appendGraph(final String fileName) throws IOException, DataFormatException {
        final Graph temp = this.dl.load(fileName);
        this.graph.addGraph(temp);
        this.copyNodeSettings(temp);
        this.calculateEdgeLength();
        this.updateNodes();
    }
    
    public void addGraph(final Graph g) {
        if (g == null) {
            return;
        }
        g.rescaleNodes(600);
        this.graph.addGraph(g);
        this.copyNodeSettings(g);
    }
}
