// 
// Decompiled by Procyon v0.5.30
// 

package ifs;

import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Scanner;
import java.util.Iterator;
import java.io.PrintWriter;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JMenuBar;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class IFSCanvas extends JPanel
{
    private int iterations;
    private int iterationsForFastBatch;
    private static final int FAST_BATCH_SIZE = 2500;
    private Point2D.Double[] fastbatch;
    private Random random;
    private IFSRunner runner;
    private BufferedImage image;
    private int leftgap;
    private int topgap;
    private int unitSquareSize;
    private ArrayList<AffineMap> maps;
    private int selectedMapIndex;
    private SelectionInfo si;
    private static final Color DARK_BLUE;
    private static final Color DARK_MAGENTA;
    private MenuHandler menuHandler;
    private boolean showMaps;
    private volatile boolean colorCode;
    private JFileChooser fileDialog;
    private Color[] palette;
    
    static {
        DARK_BLUE = new Color(0, 0, 150);
        DARK_MAGENTA = new Color(155, 0, 155);
    }
    
    public IFSCanvas() {
        this.iterations = 100;
        this.iterationsForFastBatch = 50;
        this.random = new Random();
        this.maps = new ArrayList<AffineMap>();
        this.selectedMapIndex = -1;
        this.menuHandler = new MenuHandler((MenuHandler)null);
        this.showMaps = true;
        this.colorCode = false;
        this.palette = new Color[] { new Color(150, 0, 0), new Color(0, 150, 0), new Color(0, 0, 150), new Color(0, 150, 150), new Color(150, 0, 150), new Color(150, 150, 0), Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.GRAY, new Color(255, 150, 150), new Color(150, 255, 150), new Color(150, 255, 255), new Color(255, 150, 255), new Color(255, 255, 150), Color.BLACK };
        final MouseHandler mh = new MouseHandler((MouseHandler)null);
        this.addMouseListener(mh);
        this.addMouseMotionListener(mh);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent e) {
                synchronized (IFSCanvas.this) {
                    IFSCanvas.this.setShowMaps(true);
                    if (IFSCanvas.this.maps.size() > 0 && IFSCanvas.this.selectedMapIndex == -1) {
                        IFSCanvas.this.select(0);
                    }
                    IFSCanvas.this.incJobnum();
                    IFSCanvas.access$26(IFSCanvas.this, null);
                    final int w = IFSCanvas.this.getWidth();
                    final int h = IFSCanvas.this.getHeight();
                    if (w >= h) {
                        IFSCanvas.access$27(IFSCanvas.this, h - 20);
                        IFSCanvas.access$28(IFSCanvas.this, 10);
                        IFSCanvas.access$29(IFSCanvas.this, (w - IFSCanvas.this.unitSquareSize) / 2);
                    }
                    else {
                        IFSCanvas.access$27(IFSCanvas.this, w - 20);
                        IFSCanvas.access$28(IFSCanvas.this, (h - IFSCanvas.this.unitSquareSize) / 2);
                        IFSCanvas.access$29(IFSCanvas.this, 10);
                    }
                }
                // monitorexit(this.this$0)
            }
        });
        this.fastbatch = new Point2D.Double[2500];
        for (int i = 0; i < 2500; ++i) {
            this.fastbatch[i] = new Point2D.Double();
        }
        (this.runner = new IFSRunner()).start();
    }
    
    int getIterationCount() {
        return this.iterations;
    }
    
    synchronized void setIterationCount(final int iterations) {
        if (iterations >= 10 && iterations != this.iterations) {
            this.iterations = iterations;
            if (this.maps.size() > 1) {
                this.clearImage();
            }
        }
    }
    
    int getIterationsForPreview() {
        return this.iterationsForFastBatch;
    }
    
    synchronized void setIterationsForPreview(final int iterationsForFastBatch) {
        if (iterationsForFastBatch >= 10 && iterationsForFastBatch != this.iterationsForFastBatch) {
            this.iterationsForFastBatch = iterationsForFastBatch;
            if (this.maps.size() > 1 && this.selectedMapIndex >= 0) {
                this.repaint();
            }
        }
    }
    
    public JMenuBar getMenuBar(final boolean forApplet) {
        return this.menuHandler.makeMenus(forApplet);
    }
    
    protected void paintComponent(final Graphics g) {
        this.checkImage();
        if (this.image != null && this.selectedMapIndex == -1 && this.maps.size() > 1) {
            g.drawImage(this.image, 0, 0, null);
        }
        else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (!this.showMaps) {
            return;
        }
        final Graphics2D g2 = (Graphics2D)g;
        g2.translate(this.leftgap, this.topgap);
        if (this.selectedMapIndex >= 0 && this.maps.size() > 1) {
            this.makeFastBatch();
            g2.setColor(IFSCanvas.DARK_MAGENTA);
            final Point2D.Double[] fastbatch = this.fastbatch;
            for (int k = 0; k < fastbatch.length; ++k) {
                final Point2D.Double pt = fastbatch[k];
                final int a = (int)(pt.x * this.unitSquareSize);
                final int b = (int)((1.0 - pt.y) * this.unitSquareSize);
                g2.fillRect(a - 1, b - 1, 3, 3);
            }
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < this.maps.size(); ++i) {
            if (i != this.selectedMapIndex) {
                final AffineMap map = this.maps.get(i);
                this.drawAffineMap(map, g2, this.unitSquareSize, this.unitSquareSize);
            }
        }
        if (this.selectedMapIndex >= 0) {
            final AffineMap map2 = this.maps.get(this.selectedMapIndex);
            if (this.si == null) {
                this.si = new SelectionInfo(map2, this.unitSquareSize, this.unitSquareSize);
            }
            this.drawSelectedAffineMap(map2, g2, this.unitSquareSize, this.unitSquareSize);
        }
        g2.translate(-this.leftgap, -this.topgap);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2.setColor(new Color(0, 0, 0, 60));
        if (this.leftgap > 3) {
            g2.fillRect(0, 0, this.leftgap - 3, this.getHeight());
            g2.fillRect(this.leftgap + this.unitSquareSize + 3, 0, this.leftgap + 1, this.getHeight());
        }
        if (this.topgap > 3) {
            g2.fillRect(this.leftgap - 3, 0, this.unitSquareSize + 6, this.topgap - 3);
            g2.fillRect(this.leftgap - 3, this.topgap + this.unitSquareSize + 3, this.unitSquareSize + 6, this.topgap + 1);
        }
        final Graphics g3 = g.create(this.leftgap - 3, this.topgap - 3, this.unitSquareSize + 6, this.unitSquareSize + 6);
        for (int j = 2; j >= 0; --j) {
            final int s = this.unitSquareSize + 6;
            g3.setColor(Color.RED);
            g3.drawLine(0, j, s, j);
            g3.setColor(Color.GREEN);
            g3.drawLine(s - j - 1, 0, s - j - 1, s);
            g3.setColor(Color.BLUE);
            g3.drawLine(0, s - j - 1, s, s - j - 1);
            g3.setColor(Color.CYAN);
            g3.drawLine(j, 0, j, s);
        }
        g3.dispose();
    }
    
    private synchronized void checkImage() {
        if ((this.image == null || this.image.getWidth() != this.getWidth() || this.image.getHeight() != this.getHeight()) && this.maps.size() > 1 && this.selectedMapIndex == -1) {
            this.image = null;
            this.image = new BufferedImage(this.getWidth(), this.getHeight(), 1);
            final Graphics ig = this.image.getGraphics();
            ig.setColor(Color.WHITE);
            ig.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
            ig.dispose();
        }
    }
    
    private synchronized void select(final int selectedIndex) {
        this.selectedMapIndex = selectedIndex;
        this.menuHandler.flipSelected.setEnabled(this.selectedMapIndex >= 0);
        this.menuHandler.deleteSelected.setEnabled(this.selectedMapIndex >= 0);
        this.si = null;
        this.repaint();
        this.incJobnum();
    }
    
    private synchronized void deleteSelectedMap() {
        if (this.selectedMapIndex == -1) {
            return;
        }
        this.incJobnum();
        this.clearImage();
        this.maps.remove(this.selectedMapIndex);
        this.select(-1);
    }
    
    private synchronized void flipSelectedMap() {
        if (this.selectedMapIndex == -1) {
            return;
        }
        final AffineMap oldMap = this.maps.get(this.selectedMapIndex);
        final AffineMap map = new AffineMap(oldMap.x0, oldMap.y0, oldMap.x2, oldMap.y2, oldMap.x1, oldMap.y1);
        this.setMapAt(this.selectedMapIndex, map);
    }
    
    private synchronized void setShowMaps(final boolean show) {
        if (show == this.showMaps) {
            return;
        }
        this.showMaps = show;
        this.menuHandler.showMapsToggle.setState(show);
        this.select(-1);
    }
    
    private void clearImage() {
        if (this.image == null) {
            return;
        }
        final Graphics g = this.image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
        g.dispose();
    }
    
    private synchronized void addMap(final AffineMap map, final boolean select) {
        this.incJobnum();
        this.clearImage();
        this.maps.add(map);
        this.setShowMaps(true);
        if (select) {
            this.select(this.maps.size() - 1);
        }
        else {
            this.select(-1);
        }
    }
    
    private synchronized void clear() {
        this.clearImage();
        this.incJobnum();
        this.maps.clear();
        this.select(-1);
        this.notify();
    }
    
    private synchronized void setMapAt(final int index, final AffineMap map) {
        this.clearImage();
        this.incJobnum();
        this.maps.set(index, map);
        this.si = null;
        this.repaint();
        this.notify();
    }
    
    private synchronized void setColorCode(final boolean show) {
        if (show == this.colorCode) {
            return;
        }
        this.colorCode = show;
        this.menuHandler.colorCodeCommand.setState(this.colorCode);
        this.clearImage();
        this.incJobnum();
        this.repaint();
    }
    
    private void makeFastBatch() {
        final double[] cumulativeAreas = new double[this.maps.size()];
        cumulativeAreas[0] = this.maps.get(0).area;
        for (int i = 1; i < cumulativeAreas.length; ++i) {
            cumulativeAreas[i] = cumulativeAreas[i - 1] + this.maps.get(i).area;
        }
        final double totalArea = cumulativeAreas[cumulativeAreas.length - 1];
        this.random.setSeed(17L);
        final Point2D.Double[] fastbatch = this.fastbatch;
        for (int l = 0; l < fastbatch.length; ++l) {
            final Point2D.Double pt = fastbatch[l];
            pt.setLocation(this.random.nextDouble(), this.random.nextDouble());
            for (int j = 0; j < this.iterationsForFastBatch; ++j) {
                double r;
                int k;
                for (r = this.random.nextDouble() * totalArea, k = 0; k < cumulativeAreas.length - 1 && r > cumulativeAreas[k]; ++k) {}
                this.maps.get(k).apply(pt);
            }
        }
    }
    
    private void drawLine(final Graphics2D g, final int width, final int height, final double x1, final double y1, final double x2, final double y2) {
        final int a = (int)(x1 * width);
        final int b = (int)((1.0 - y1) * height);
        final int c = (int)(x2 * width);
        final int d = (int)((1.0 - y2) * height);
        g.drawLine(a, b, c, d);
    }
    
    private void drawAffineMap(final AffineMap map, final Graphics2D g, final int width, final int height) {
        g.setColor(Color.RED);
        this.drawLine(g, width, height, map.x2, map.y2, map.x3, map.y3);
        g.setColor(Color.GREEN);
        this.drawLine(g, width, height, map.x3, map.y3, map.x1, map.y1);
        g.setColor(Color.BLUE);
        this.drawLine(g, width, height, map.x1, map.y1, map.x0, map.y0);
        g.setColor(Color.CYAN);
        this.drawLine(g, width, height, map.x0, map.y0, map.x2, map.y2);
    }
    
    private void drawSelectedAffineMap(final AffineMap map, final Graphics2D g, final int width, final int height) {
        final Polygon quad = new Polygon();
        quad.addPoint((int)(map.x0 * width), (int)((1.0 - map.y0) * height));
        quad.addPoint((int)(map.x1 * width), (int)((1.0 - map.y1) * height));
        quad.addPoint((int)(map.x3 * width), (int)((1.0 - map.y3) * height));
        quad.addPoint((int)(map.x2 * width), (int)((1.0 - map.y2) * height));
        g.setColor(new Color(0, 0, 255, 50));
        g.fillPolygon(quad);
        final Stroke saveStroke = g.getStroke();
        g.setStroke(new BasicStroke(2.5f));
        this.drawAffineMap(map, g, width, height);
        this.si.draw(g, width, height);
        g.setStroke(saveStroke);
    }
    
    synchronized void incJobnum() {
        final IFSRunner runner = this.runner;
        ++runner.currentJobnum;
        this.notify();
    }
    
    private synchronized void installExample(final double[][] data) {
        this.clear();
        this.incJobnum();
        for (int i = 0; i < data.length; ++i) {
            final double[] d = data[i];
            this.maps.add(new AffineMap(d[0], d[1], d[2], d[3], d[4], d[5]));
        }
        this.setShowMaps(true);
        this.repaint();
    }
    
    private synchronized void saveImage(final boolean includeMaps) {
        if (this.maps.size() < 2) {
            JOptionPane.showMessageDialog(this, "You should have at least two maps\nbefore you think about saving.");
            return;
        }
        if (this.fileDialog == null) {
            this.fileDialog = new JFileChooser();
        }
        this.fileDialog.setSelectedFile(new File("ChaosGameImage.png"));
        if (includeMaps) {
            this.fileDialog.setDialogTitle("Save Image with Maps");
        }
        else {
            this.fileDialog.setDialogTitle("Save Image");
        }
        final int option = this.fileDialog.showSaveDialog(this);
        if (option != 0) {
            return;
        }
        final File selectedFile = this.fileDialog.getSelectedFile();
        if (selectedFile.exists()) {
            final int response = JOptionPane.showConfirmDialog(this, "The file \"" + selectedFile.getName() + "\" already exists.\nDo you want to replace it?", "Confirm Replace File", 0, 2);
            if (response == 1) {
                return;
            }
        }
        BufferedImage saveImage = this.image;
        try {
            if (includeMaps) {
                saveImage = new BufferedImage(this.getWidth(), this.getHeight(), 2);
                final Graphics g = saveImage.getGraphics();
                final int saveSelectedIndex = this.selectedMapIndex;
                final boolean saveShow = this.showMaps;
                this.selectedMapIndex = -1;
                this.showMaps = true;
                this.paintComponent(g);
                this.selectedMapIndex = saveSelectedIndex;
                this.showMaps = saveShow;
                g.dispose();
            }
        }
        catch (OutOfMemoryError e2) {
            saveImage = this.image;
        }
        try {
            final boolean hasPNG = ImageIO.write(saveImage, "PNG", selectedFile);
            if (!hasPNG) {
                throw new Exception("Sorry, the PNG image format\ndoesn't seem to be available!");
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sorry, an error occurred while trying to\nwrite the file \"" + selectedFile.getName() + "\".\n" + "Error: " + e.getMessage());
        }
    }
    
    private void saveMapsData() {
        if (this.maps.size() < 2) {
            JOptionPane.showMessageDialog(this, "You should have at least two maps\nbefore you think about saving.");
            return;
        }
        if (this.fileDialog == null) {
            this.fileDialog = new JFileChooser();
        }
        this.fileDialog.setDialogTitle("Save Maps Data");
        this.fileDialog.setSelectedFile(new File("ChaosGameMaps.txt"));
        final int option = this.fileDialog.showSaveDialog(this);
        if (option != 0) {
            return;
        }
        final File selectedFile = this.fileDialog.getSelectedFile();
        if (selectedFile.exists()) {
            final int response = JOptionPane.showConfirmDialog(this, "The file \"" + selectedFile.getName() + "\" already exists.\nDo you want to replace it?", "Confirm Replace File", 0, 2);
            if (response == 1) {
                return;
            }
        }
        try {
            final PrintWriter out = new PrintWriter(selectedFile);
            out.println("Affine Maps for Chaos Game");
            out.println(this.maps.size());
            for (final AffineMap map : this.maps) {
                out.print(String.valueOf(map.x0) + " ");
                out.print(String.valueOf(map.y0) + " ");
                out.print(String.valueOf(map.x1) + " ");
                out.print(String.valueOf(map.y1) + " ");
                out.print(String.valueOf(map.x2) + " ");
                out.println(map.y2);
            }
            out.flush();
            out.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sorry, an error occurred while trying to\nwrite the file \"" + selectedFile.getName() + "\".\n" + "Error: " + e.getMessage());
        }
    }
    
    private void openMapsFile() {
        if (this.fileDialog == null) {
            this.fileDialog = new JFileChooser();
        }
        this.fileDialog.setDialogTitle("Open Chaos Game Map Data File");
        this.fileDialog.setSelectedFile(null);
        final int option = this.fileDialog.showOpenDialog(this);
        if (option != 0) {
            return;
        }
        final File selectedFile = this.fileDialog.getSelectedFile();
        try {
            final Scanner in = new Scanner(selectedFile);
            final String firstLine = in.nextLine();
            if (!firstLine.startsWith("Affine Maps")) {
                throw new Exception("This does not seem to be a Chaos Game data file.");
            }
            final int ct = in.nextInt();
            if (ct < 2) {
                throw new Exception("This does not seem to be a Chaos Game data file.");
            }
            final double[][] data = new double[ct][6];
            for (int i = 0; i < ct; ++i) {
                for (int j = 0; j < 6; ++j) {
                    data[i][j] = in.nextDouble();
                }
            }
            in.close();
            this.installExample(data);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sorry, an error occurred while trying to\nread the file \"" + selectedFile.getName() + "\".\n" + "Error: " + e.getMessage());
        }
    }
    
    static /* synthetic */ void access$13(final IFSCanvas ifsCanvas, final SelectionInfo si) {
        ifsCanvas.si = si;
    }
    
    static /* synthetic */ void access$26(final IFSCanvas ifsCanvas, final BufferedImage image) {
        ifsCanvas.image = image;
    }
    
    static /* synthetic */ void access$27(final IFSCanvas ifsCanvas, final int unitSquareSize) {
        ifsCanvas.unitSquareSize = unitSquareSize;
    }
    
    static /* synthetic */ void access$28(final IFSCanvas ifsCanvas, final int topgap) {
        ifsCanvas.topgap = topgap;
    }
    
    static /* synthetic */ void access$29(final IFSCanvas ifsCanvas, final int leftgap) {
        ifsCanvas.leftgap = leftgap;
    }
    
    private class IFSRunner extends Thread
    {
        int jobnum;
        final int batchsize = 500;
        Point2D.Double[] points;
        int[] colorInfo;
        int currentJobnum;
        
        IFSRunner() {
            try {
                this.setPriority(this.getPriority() - 1);
                this.setDaemon(true);
            }
            catch (Exception ex) {}
        }
        
        private void makeBatch() {
            final double[] cumulativeAreas = new double[IFSCanvas.this.maps.size()];
            cumulativeAreas[0] = IFSCanvas.this.maps.get(0).area;
            for (int i = 1; i < cumulativeAreas.length; ++i) {
                cumulativeAreas[i] = cumulativeAreas[i - 1] + IFSCanvas.this.maps.get(i).area;
            }
            final double totalArea = cumulativeAreas[cumulativeAreas.length - 1];
            for (int p = 0; p < 500; ++p) {
                final Point2D.Double pt = this.points[p];
                if (this.jobnum != this.currentJobnum) {
                    return;
                }
                pt.setLocation(Math.random(), Math.random());
                for (int j = 0; j < IFSCanvas.this.iterations; ++j) {
                    final double r = Math.random() * totalArea;
                    this.colorInfo[p] = 0;
                    while (this.colorInfo[p] < cumulativeAreas.length - 1 && r > cumulativeAreas[this.colorInfo[p]]) {
                        final int[] colorInfo = this.colorInfo;
                        final int n = p;
                        ++colorInfo[n];
                    }
                    IFSCanvas.this.maps.get(this.colorInfo[p]).apply(pt);
                }
            }
        }
        
        public void run() {
            this.points = new Point2D.Double[500];
            this.colorInfo = new int[500];
            for (int i = 0; i < 500; ++i) {
                this.points[i] = new Point2D.Double();
            }
            while (true) {
                try {
                    while (true) {
                        final int[] intPalette;
                        synchronized (IFSCanvas.this) {
                            while (IFSCanvas.this.selectedMapIndex > -1 || IFSCanvas.this.maps.size() < 2) {
                                try {
                                    IFSCanvas.this.wait();
                                }
                                catch (InterruptedException ex) {}
                            }
                            this.jobnum = this.currentJobnum;
                            intPalette = new int[IFSCanvas.this.maps.size()];
                            for (int j = 0; j < intPalette.length; ++j) {
                                intPalette[j] = IFSCanvas.this.palette[j % intPalette.length].getRGB();
                            }
                        }
                        // monitorexit(this.this$0)
                        while (this.jobnum == this.currentJobnum) {
                            this.makeBatch();
                            boolean newpoint = false;
                            boolean pointinrange = false;
                            synchronized (IFSCanvas.this) {
                                if (this.jobnum != this.currentJobnum) {
                                    // monitorexit(this.this$0)
                                    break;
                                }
                                IFSCanvas.this.checkImage();
                                for (int k = 0; k < 500; ++k) {
                                    final Point2D.Double pt = this.points[k];
                                    final int x = IFSCanvas.this.leftgap + (int)(pt.x * IFSCanvas.this.unitSquareSize);
                                    final int y = IFSCanvas.this.topgap + (int)((1.0 - pt.y) * IFSCanvas.this.unitSquareSize);
                                    try {
                                        final int rgb = IFSCanvas.this.image.getRGB(x, y);
                                        if ((rgb & 0xFFFFFF) == 0xFFFFFF) {
                                            newpoint = true;
                                            if (IFSCanvas.this.colorCode) {
                                                IFSCanvas.this.image.setRGB(x, y, intPalette[this.colorInfo[k]]);
                                            }
                                            else {
                                                IFSCanvas.this.image.setRGB(x, y, -16777216);
                                            }
                                        }
                                        pointinrange = true;
                                    }
                                    catch (Exception ex2) {}
                                }
                            }
                            // monitorexit(this.this$0)
                            if (!newpoint && this.jobnum == this.currentJobnum) {
                                System.out.println("No new points");
                                synchronized (IFSCanvas.this) {
                                    while (this.jobnum == this.currentJobnum) {
                                        try {
                                            IFSCanvas.this.wait();
                                        }
                                        catch (InterruptedException ex3) {}
                                    }
                                    // monitorexit(this.this$0)
                                    break;
                                }
                            }
                            if (!pointinrange && this.jobnum == this.currentJobnum) {
                                System.out.println("All points out of range");
                                synchronized (IFSCanvas.this) {
                                    while (this.jobnum == this.currentJobnum) {
                                        try {
                                            this.wait();
                                        }
                                        catch (InterruptedException ex4) {}
                                    }
                                    // monitorexit(this.this$0)
                                    break;
                                }
                            }
                            IFSCanvas.this.repaint();
                        }
                    }
                }
                catch (Exception ex5) {
                    continue;
                }
                break;
            }
        }
    }
    
    private static class SelectionInfo
    {
        double centerX;
        double centerY;
        double rotateHandleX;
        double rotateHandleY;
        double[] cornerX;
        double[] cornerY;
        double[] arrowX;
        double[] arrowY;
        double[] arrowDX;
        double[] arrowDY;
        
        SelectionInfo(final AffineMap map, final int w, final int h) {
            this.cornerX = new double[4];
            this.cornerY = new double[4];
            this.arrowX = new double[5];
            this.arrowY = new double[5];
            this.arrowDX = new double[5];
            this.arrowDY = new double[5];
            this.make(map, w, h);
        }
        
        void make(final AffineMap map, final int width, final int height) {
            this.cornerX[0] = map.x0;
            this.cornerY[0] = map.y0;
            this.cornerX[1] = map.x1;
            this.cornerY[1] = map.y1;
            this.cornerX[2] = map.x2;
            this.cornerY[2] = map.y2;
            this.cornerX[3] = map.x3;
            this.cornerY[3] = map.y3;
            final double n = 0.0;
            this.centerY = n;
            this.centerX = n;
            for (int i = 0; i < 4; ++i) {
                this.centerX += this.cornerX[i];
                this.centerY += this.cornerY[i];
            }
            this.centerX /= 4.0;
            this.centerY /= 4.0;
            double dx = map.x1 - map.x0;
            double dy = map.y1 - map.y0;
            double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
            final double temp = dx * Math.cos(0.5235987755982988) - dy * Math.sin(0.5235987755982988);
            dy = dx * Math.sin(0.5235987755982988) + dy * Math.cos(0.5235987755982988);
            dx = temp;
            this.rotateHandleX = this.centerX + 80.0 / width * dx;
            this.rotateHandleY = this.centerY + 80.0 / width * dy;
            this.arrowX[0] = (map.x0 + map.x1) / 2.0;
            this.arrowY[0] = (map.y0 + map.y1) / 2.0;
            this.arrowX[1] = (map.x2 + map.x3) / 2.0;
            this.arrowY[1] = (map.y2 + map.y3) / 2.0;
            this.arrowX[2] = (map.x0 + map.x2) / 2.0;
            this.arrowY[2] = (map.y0 + map.y2) / 2.0;
            this.arrowX[3] = (map.x1 + map.x3) / 2.0;
            this.arrowY[3] = (map.y1 + map.y3) / 2.0;
            this.arrowX[4] = this.rotateHandleX;
            this.arrowY[4] = this.rotateHandleY;
            this.arrowDX[0] = map.x1 - map.x0;
            this.arrowDY[0] = map.y1 - map.y0;
            this.arrowDX[1] = map.x2 - map.x3;
            this.arrowDY[1] = map.y2 - map.y3;
            this.arrowDX[2] = map.x0 - map.x2;
            this.arrowDY[2] = map.y0 - map.y2;
            this.arrowDX[3] = map.x3 - map.x1;
            this.arrowDY[3] = map.y3 - map.y1;
            this.arrowDX[4] = -dy;
            this.arrowDY[4] = dx;
            for (int j = 0; j < 4; ++j) {
                length = Math.sqrt(this.arrowDX[j] * this.arrowDX[j] + this.arrowDY[j] * this.arrowDY[j]);
                final double[] arrowDX = this.arrowDX;
                final int n2 = j;
                arrowDX[n2] /= length;
                final double[] arrowDY = this.arrowDY;
                final int n3 = j;
                arrowDY[n3] /= length;
            }
        }
        
        void draw(final Graphics2D g, final int width, final int height) {
            final double pw = 1.0 / width;
            final double ph = 1.0 / height;
            g.setColor(Color.BLACK);
            for (int i = 0; i < 4; ++i) {
                g.fillRect((int)(this.cornerX[i] * width) - 4, (int)((1.0 - this.cornerY[i]) * height) - 4, 9, 9);
            }
            g.setColor(IFSCanvas.DARK_BLUE);
            g.setStroke(new BasicStroke(2.5f));
            g.draw(new Line2D.Double(this.centerX * width, (1.0 - this.centerY) * height, this.rotateHandleX * width, (1.0 - this.rotateHandleY) * height));
            g.fillOval((int)(this.centerX * width) - 4, (int)((1.0 - this.centerY) * width) - 4, 9, 9);
            g.setStroke(new BasicStroke(5.0f));
            for (int i = 0; i < 5; ++i) {
                g.setColor((i < 4) ? Color.BLACK : IFSCanvas.DARK_BLUE);
                final double a = 20.0;
                final double x1 = this.arrowX[i] + a * pw * this.arrowDX[i];
                final double y1 = this.arrowY[i] + a * ph * this.arrowDY[i];
                final double x2 = this.arrowX[i] - a * pw * this.arrowDX[i];
                final double y2 = this.arrowY[i] - a * ph * this.arrowDY[i];
                g.draw(new Line2D.Double(x1 * width, (1.0 - y1) * height, x2 * width, (1.0 - y2) * height));
                double x3 = x1 - a * pw * (this.arrowDX[i] - this.arrowDY[i]) / 6.0;
                double y3 = y1 - a * pw * (this.arrowDY[i] + this.arrowDX[i]) / 6.0;
                double x4 = x1 - a * pw * (this.arrowDX[i] + this.arrowDY[i]) / 6.0;
                double y4 = y1 - a * pw * (this.arrowDY[i] - this.arrowDX[i]) / 6.0;
                g.draw(new Line2D.Double(x1 * width, (1.0 - y1) * height, x3 * width, (1.0 - y3) * height));
                g.draw(new Line2D.Double(x1 * width, (1.0 - y1) * height, x4 * width, (1.0 - y4) * height));
                x3 = x2 + a * pw * (this.arrowDX[i] - this.arrowDY[i]) / 6.0;
                y3 = y2 + a * pw * (this.arrowDY[i] + this.arrowDX[i]) / 6.0;
                x4 = x2 + a * pw * (this.arrowDX[i] + this.arrowDY[i]) / 6.0;
                y4 = y2 + a * pw * (this.arrowDY[i] - this.arrowDX[i]) / 6.0;
                g.draw(new Line2D.Double(x2 * width, (1.0 - y2) * height, x3 * width, (1.0 - y3) * height));
                g.draw(new Line2D.Double(x2 * width, (1.0 - y2) * height, x4 * width, (1.0 - y4) * height));
            }
        }
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener
    {
        boolean dragging;
        int whatIsBeingDragged;
        AffineMap dragMap;
        static final int CORNER0 = 0;
        static final int CORNER1 = 1;
        static final int CORNER2 = 2;
        static final int CORNER3 = 3;
        static final int SKEW0 = 4;
        static final int SKEW1 = 5;
        static final int SKEW2 = 6;
        static final int SKEW3 = 7;
        static final int SIDE0 = 8;
        static final int SIDE1 = 9;
        static final int SIDE2 = 10;
        static final int SIDE3 = 11;
        static final int ROTATE = 12;
        static final int INSIDE = 13;
        static final int NONE = -1;
        int startX;
        int startY;
        int prevX;
        int prevY;
        double dragLineDX;
        double dragLineDY;
        boolean shifted;
        int insideDragWhileShifted;
        
        boolean hitPoint(final int mouseX, final int mouseY, final double x, final double y) {
            final int xint = IFSCanvas.this.leftgap + (int)(x * IFSCanvas.this.unitSquareSize);
            final int yint = IFSCanvas.this.topgap + (int)((1.0 - y) * IFSCanvas.this.unitSquareSize);
            final int xdiff = xint - mouseX;
            final int ydiff = yint - mouseY;
            return xdiff * xdiff + ydiff * ydiff < 36;
        }
        
        boolean hitLine(final int mouseX, final int mouseY, final double x1, final double y1, final double x2, final double y2, final int epsilon) {
            double dx = x2 - x1;
            double dy = y2 - y1;
            final double length = Math.sqrt(dx * dx + dy * dy);
            dx /= length;
            dy /= length;
            final double ptx = (mouseX - IFSCanvas.this.leftgap) / IFSCanvas.this.unitSquareSize;
            final double pty = 1.0 - (mouseY - IFSCanvas.this.topgap) / IFSCanvas.this.unitSquareSize;
            final double distance = (ptx - x1) * -dy + (pty - y1) * dx;
            final double distanceInPixels = distance * IFSCanvas.this.unitSquareSize;
            if (Math.abs(distanceInPixels) > 4.0) {
                return false;
            }
            final double projlen = (ptx - x1) * -dy + (pty - y1) * dx;
            final double projx = ptx - projlen * -dy;
            final double projy = pty - projlen * dx;
            final double eps = 1.0 / IFSCanvas.this.unitSquareSize * epsilon;
            return ((x1 - eps <= projx && projx <= x2 + eps) || (x2 - eps <= projx && projx <= x1 + eps)) && ((y1 - eps <= projy && projy <= y2 + eps) || (y2 - eps <= projy && projy <= y1 + eps));
        }
        
        boolean inside(final int mouseX, final int mouseY, final AffineMap map) {
            final double ptx = (mouseX - IFSCanvas.this.leftgap) / IFSCanvas.this.unitSquareSize;
            final double pty = 1.0 - (mouseY - IFSCanvas.this.topgap) / IFSCanvas.this.unitSquareSize;
            return map.containsPoint(ptx, pty);
        }
        
        boolean hitArrow(final int mouseX, final int mouseY, final double centerX, final double centerY, final double dx, final double dy) {
            final double pw = 1.0 / IFSCanvas.this.unitSquareSize;
            final double x1 = centerX + dx * pw * 20.0;
            final double y1 = centerY + dy * pw * 20.0;
            final double x2 = centerX - dx * pw * 20.0;
            final double y2 = centerY - dy * pw * 20.0;
            return this.hitLine(mouseX, mouseY, x1, y1, x2, y2, 7);
        }
        
        public void mousePressed(final MouseEvent e) {
            if (!IFSCanvas.this.showMaps) {
                return;
            }
            if (this.dragging) {
                return;
            }
            this.shifted = (e.isShiftDown() || e.isMetaDown());
            this.insideDragWhileShifted = 0;
            final int x = e.getX();
            final int y = e.getY();
            int hit = -1;
            if (IFSCanvas.this.selectedMapIndex > -1) {
                final AffineMap map = IFSCanvas.this.maps.get(IFSCanvas.this.selectedMapIndex);
                if (IFSCanvas.this.si == null) {
                    IFSCanvas.access$13(IFSCanvas.this, new SelectionInfo(map, IFSCanvas.this.unitSquareSize, IFSCanvas.this.unitSquareSize));
                }
                this.dragLineDX = Double.NaN;
                if (this.hitPoint(x, y, IFSCanvas.this.si.cornerX[0], IFSCanvas.this.si.cornerY[0])) {
                    hit = 0;
                    this.dragLineDX = IFSCanvas.this.si.cornerX[3] - IFSCanvas.this.si.cornerX[0];
                    this.dragLineDY = IFSCanvas.this.si.cornerY[3] - IFSCanvas.this.si.cornerY[0];
                    final double length = Math.sqrt(this.dragLineDX * this.dragLineDX + this.dragLineDY * this.dragLineDY);
                    this.dragLineDX /= length;
                    this.dragLineDY /= length;
                }
                else if (this.hitPoint(x, y, IFSCanvas.this.si.cornerX[1], IFSCanvas.this.si.cornerY[1])) {
                    hit = 1;
                    this.dragLineDX = IFSCanvas.this.si.cornerX[2] - IFSCanvas.this.si.cornerX[1];
                    this.dragLineDY = IFSCanvas.this.si.cornerY[2] - IFSCanvas.this.si.cornerY[1];
                    final double length = Math.sqrt(this.dragLineDX * this.dragLineDX + this.dragLineDY * this.dragLineDY);
                    this.dragLineDX /= length;
                    this.dragLineDY /= length;
                }
                else if (this.hitPoint(x, y, IFSCanvas.this.si.cornerX[2], IFSCanvas.this.si.cornerY[2])) {
                    hit = 2;
                    this.dragLineDX = IFSCanvas.this.si.cornerX[1] - IFSCanvas.this.si.cornerX[2];
                    this.dragLineDY = IFSCanvas.this.si.cornerY[1] - IFSCanvas.this.si.cornerY[2];
                    final double length = Math.sqrt(this.dragLineDX * this.dragLineDX + this.dragLineDY * this.dragLineDY);
                    this.dragLineDX /= length;
                    this.dragLineDY /= length;
                }
                else if (this.hitPoint(x, y, IFSCanvas.this.si.cornerX[3], IFSCanvas.this.si.cornerY[3])) {
                    hit = 3;
                    this.dragLineDX = IFSCanvas.this.si.cornerX[0] - IFSCanvas.this.si.cornerX[3];
                    this.dragLineDY = IFSCanvas.this.si.cornerY[0] - IFSCanvas.this.si.cornerY[3];
                    final double length = Math.sqrt(this.dragLineDX * this.dragLineDX + this.dragLineDY * this.dragLineDY);
                    this.dragLineDX /= length;
                    this.dragLineDY /= length;
                }
                else if (this.hitArrow(x, y, IFSCanvas.this.si.arrowX[0], IFSCanvas.this.si.arrowY[0], IFSCanvas.this.si.arrowDX[0], IFSCanvas.this.si.arrowDY[0])) {
                    hit = 4;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[0];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[0];
                }
                else if (this.hitArrow(x, y, IFSCanvas.this.si.arrowX[1], IFSCanvas.this.si.arrowY[1], IFSCanvas.this.si.arrowDX[1], IFSCanvas.this.si.arrowDY[1])) {
                    hit = 5;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[1];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[1];
                }
                else if (this.hitArrow(x, y, IFSCanvas.this.si.arrowX[2], IFSCanvas.this.si.arrowY[2], IFSCanvas.this.si.arrowDX[2], IFSCanvas.this.si.arrowDY[2])) {
                    hit = 6;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[2];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[2];
                }
                else if (this.hitArrow(x, y, IFSCanvas.this.si.arrowX[3], IFSCanvas.this.si.arrowY[3], IFSCanvas.this.si.arrowDX[3], IFSCanvas.this.si.arrowDY[3])) {
                    hit = 7;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[3];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[3];
                }
                else if (this.hitArrow(x, y, IFSCanvas.this.si.arrowX[4], IFSCanvas.this.si.arrowY[4], IFSCanvas.this.si.arrowDX[4], IFSCanvas.this.si.arrowDY[4])) {
                    hit = 12;
                }
                else if (this.hitLine(x, y, map.x0, map.y0, map.x1, map.y1, 5)) {
                    hit = 8;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[2];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[2];
                }
                else if (this.hitLine(x, y, map.x3, map.y3, map.x2, map.y2, 5)) {
                    hit = 9;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[2];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[2];
                }
                else if (this.hitLine(x, y, map.x0, map.y0, map.x2, map.y2, 5)) {
                    hit = 10;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[0];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[0];
                }
                else if (this.hitLine(x, y, map.x3, map.y3, map.x1, map.y1, 5)) {
                    hit = 11;
                    this.dragLineDX = IFSCanvas.this.si.arrowDX[0];
                    this.dragLineDY = IFSCanvas.this.si.arrowDY[0];
                }
                else {
                    if (this.hitLine(x, y, IFSCanvas.this.si.centerX, IFSCanvas.this.si.centerY, IFSCanvas.this.si.rotateHandleX, IFSCanvas.this.si.rotateHandleY, 5)) {
                        return;
                    }
                    if (this.inside(x, y, map)) {
                        hit = 13;
                    }
                }
                if (hit != -1) {
                    this.dragging = true;
                    this.dragMap = map;
                    final int n = x;
                    this.prevX = n;
                    this.startX = n;
                    final int n2 = y;
                    this.prevY = n2;
                    this.startY = n2;
                    this.whatIsBeingDragged = hit;
                    IFSCanvas.this.repaint();
                    return;
                }
            }
            for (int i = IFSCanvas.this.maps.size() - 1; i >= 0; --i) {
                if (i != IFSCanvas.this.selectedMapIndex) {
                    final AffineMap map2 = IFSCanvas.this.maps.get(i);
                    if (this.hitLine(x, y, map2.x0, map2.y0, map2.x1, map2.y1, 4) || this.hitLine(x, y, map2.x1, map2.y1, map2.x3, map2.y3, 4) || this.hitLine(x, y, map2.x3, map2.y3, map2.x2, map2.y2, 4) || this.hitLine(x, y, map2.x2, map2.y2, map2.x0, map2.y0, 4)) {
                        IFSCanvas.this.select(i);
                        return;
                    }
                }
            }
            final double ptx = (x - IFSCanvas.this.leftgap) / IFSCanvas.this.unitSquareSize;
            final double pty = 1.0 - (y - IFSCanvas.this.topgap) / IFSCanvas.this.unitSquareSize;
            for (int j = IFSCanvas.this.maps.size() - 1; j >= 0; --j) {
                if (j != IFSCanvas.this.selectedMapIndex && IFSCanvas.this.maps.get(j).containsPoint(ptx, pty)) {
                    IFSCanvas.this.select(j);
                    return;
                }
            }
            IFSCanvas.this.select(-1);
        }
        
        public void mouseReleased(final MouseEvent e) {
            this.dragging = false;
            this.dragMap = null;
        }
        
        public void mouseDragged(final MouseEvent e) {
            if (!this.dragging) {
                return;
            }
            final int x = e.getX();
            final int y = e.getY();
            double offsetFromStartX = (x - this.startX) / IFSCanvas.this.unitSquareSize;
            double offsetFromStartY = -(y - this.startY) / IFSCanvas.this.unitSquareSize;
            if (!Double.isNaN(this.dragLineDX)) {
                final double dot = this.dragLineDX * offsetFromStartX + this.dragLineDY * offsetFromStartY;
                offsetFromStartX = this.dragLineDX * dot;
                offsetFromStartY = this.dragLineDY * dot;
            }
            final AffineMap map = this.dragMap;
            AffineMap newMap = null;
            final double startX_real = (this.startX - IFSCanvas.this.leftgap) / IFSCanvas.this.unitSquareSize;
            final double startY_real = 1.0 - (this.startY - IFSCanvas.this.topgap) / IFSCanvas.this.unitSquareSize;
            final double centerX = (map.x1 + map.x2) / 2.0;
            final double centerY = (map.y1 + map.y2) / 2.0;
            try {
                switch (this.whatIsBeingDragged) {
                    case 13: {
                        if (this.shifted) {
                            if (this.insideDragWhileShifted == 0) {
                                if (Math.abs(offsetFromStartX) + Math.abs(offsetFromStartY) < 10.0 / IFSCanvas.this.unitSquareSize) {
                                    break;
                                }
                                if (Math.abs(offsetFromStartX) >= Math.abs(offsetFromStartY)) {
                                    this.insideDragWhileShifted = 1;
                                }
                                else {
                                    this.insideDragWhileShifted = 2;
                                }
                            }
                            if (this.insideDragWhileShifted == 1) {
                                offsetFromStartY = 0.0;
                            }
                            else {
                                offsetFromStartX = 0.0;
                            }
                        }
                        newMap = new AffineMap(map.x0 + offsetFromStartX, map.y0 + offsetFromStartY, map.x1 + offsetFromStartX, map.y1 + offsetFromStartY, map.x2 + offsetFromStartX, map.y2 + offsetFromStartY);
                        break;
                    }
                    case 0:
                    case 1:
                    case 2:
                    case 3: {
                        final double startDist = Math.sqrt((centerX - startX_real) * (centerX - startX_real) + (centerY - startY_real) * (centerY - startY_real));
                        double newCenterX = centerX;
                        double newCenterY = centerY;
                        if (!this.shifted) {
                            newCenterX = centerX + offsetFromStartX / 2.0;
                            newCenterY = centerY + offsetFromStartY / 2.0;
                        }
                        double newx;
                        double newy;
                        if (this.whatIsBeingDragged == 0) {
                            newx = map.x0 + offsetFromStartX;
                            newy = map.y0 + offsetFromStartY;
                        }
                        else if (this.whatIsBeingDragged == 1) {
                            newx = map.x1 + offsetFromStartX;
                            newy = map.y1 + offsetFromStartY;
                        }
                        else if (this.whatIsBeingDragged == 2) {
                            newx = map.x2 + offsetFromStartX;
                            newy = map.y2 + offsetFromStartY;
                        }
                        else {
                            newx = map.x3 + offsetFromStartX;
                            newy = map.y3 + offsetFromStartY;
                        }
                        final double newDist = Math.sqrt((newCenterX - newx) * (newCenterX - newx) + (newCenterY - newy) * (newCenterY - newy));
                        if (newDist < 1.0E-10) {
                            break;
                        }
                        double ratio = newDist / startDist;
                        if (newCenterX - newx > 0.0 != centerX - startX_real > 0.0 || newCenterY - newy > 0.0 != centerY - startY_real > 0.0) {
                            ratio = -ratio;
                        }
                        newMap = new AffineMap(newCenterX + ratio * (map.x0 - centerX), newCenterY + ratio * (map.y0 - centerY), newCenterX + ratio * (map.x1 - centerX), newCenterY + ratio * (map.y1 - centerY), newCenterX + ratio * (map.x2 - centerX), newCenterY + ratio * (map.y2 - centerY));
                        break;
                    }
                    case 4:
                    case 8: {
                        if (this.shifted) {
                            newMap = new AffineMap(map.x0 + offsetFromStartX, map.y0 + offsetFromStartY, map.x1 + offsetFromStartX, map.y1 + offsetFromStartY, map.x2 - offsetFromStartX, map.y2 - offsetFromStartY);
                            break;
                        }
                        newMap = new AffineMap(map.x0 + offsetFromStartX, map.y0 + offsetFromStartY, map.x1 + offsetFromStartX, map.y1 + offsetFromStartY, map.x2, map.y2);
                        break;
                    }
                    case 5:
                    case 9: {
                        if (this.shifted) {
                            newMap = new AffineMap(map.x0 - offsetFromStartX, map.y0 - offsetFromStartY, map.x1 - offsetFromStartX, map.y1 - offsetFromStartY, map.x2 + offsetFromStartX, map.y2 + offsetFromStartY);
                            break;
                        }
                        newMap = new AffineMap(map.x0, map.y0, map.x1, map.y1, map.x2 + offsetFromStartX, map.y2 + offsetFromStartY);
                        break;
                    }
                    case 6:
                    case 10: {
                        if (this.shifted) {
                            newMap = new AffineMap(map.x0 + offsetFromStartX, map.y0 + offsetFromStartY, map.x1 - offsetFromStartX, map.y1 - offsetFromStartY, map.x2 + offsetFromStartX, map.y2 + offsetFromStartY);
                            break;
                        }
                        newMap = new AffineMap(map.x0 + offsetFromStartX, map.y0 + offsetFromStartY, map.x1, map.y1, map.x2 + offsetFromStartX, map.y2 + offsetFromStartY);
                        break;
                    }
                    case 7:
                    case 11: {
                        if (this.shifted) {
                            newMap = new AffineMap(map.x0 - offsetFromStartX, map.y0 - offsetFromStartY, map.x1 + offsetFromStartX, map.y1 + offsetFromStartY, map.x2 - offsetFromStartX, map.y2 - offsetFromStartY);
                            break;
                        }
                        newMap = new AffineMap(map.x0, map.y0, map.x1 + offsetFromStartX, map.y1 + offsetFromStartY, map.x2, map.y2);
                        break;
                    }
                    case 12: {
                        final double y_real = 1.0 - (y - IFSCanvas.this.topgap) / IFSCanvas.this.unitSquareSize;
                        final double x_real = (x - IFSCanvas.this.leftgap) / IFSCanvas.this.unitSquareSize;
                        final double original_angle = Math.atan2(startY_real - centerY, startX_real - centerX);
                        final double angle = Math.atan2(y_real - centerY, x_real - centerX);
                        double change = angle - original_angle;
                        if (this.shifted) {
                            change = 0.2617993877991494 * (int)(change / 0.2617993877991494);
                        }
                        final double s = Math.sin(change);
                        final double c = Math.cos(change);
                        final double a0 = centerX + (c * (map.x0 - centerX) - s * (map.y0 - centerY));
                        final double b0 = centerY + (s * (map.x0 - centerX) + c * (map.y0 - centerY));
                        final double a2 = centerX + (c * (map.x1 - centerX) - s * (map.y1 - centerY));
                        final double b2 = centerY + (s * (map.x1 - centerX) + c * (map.y1 - centerY));
                        final double a3 = centerX + (c * (map.x2 - centerX) - s * (map.y2 - centerY));
                        final double b3 = centerY + (s * (map.x2 - centerX) + c * (map.y2 - centerY));
                        newMap = new AffineMap(a0, b0, a2, b2, a3, b3);
                        break;
                    }
                }
                if (newMap != null) {
                    IFSCanvas.this.setMapAt(IFSCanvas.this.selectedMapIndex, newMap);
                }
            }
            catch (IllegalArgumentException ex) {}
            this.prevX = x;
            this.prevY = y;
        }
        
        public void mouseMoved(final MouseEvent e) {
        }
        
        public void mouseClicked(final MouseEvent e) {
        }
        
        public void mouseEntered(final MouseEvent e) {
        }
        
        public void mouseExited(final MouseEvent e) {
        }
    }
    
    private class MenuHandler implements ActionListener
    {
        String commandKey;
        JCheckBoxMenuItem showMapsToggle;
        JCheckBoxMenuItem colorCodeCommand;
        JMenuItem deleteSelected;
        JMenuItem flipSelected;
        
        JMenuBar makeMenus(final boolean forApplet) {
            final JMenuBar menuBar = new JMenuBar();
            if (!forApplet) {
                final JMenu fileMenu = new JMenu("File");
                this.addItem(fileMenu, "Save Map Data...", "S");
                this.addItem(fileMenu, "Open Map Data File...", "O");
                fileMenu.addSeparator();
                this.addItem(fileMenu, "Save PNG Image...", "P");
                this.addItem(fileMenu, "Save PNG Image Including Maps...", "shift P");
                fileMenu.addSeparator();
                this.addItem(fileMenu, "Clear All Maps");
                fileMenu.addSeparator();
                this.addItem(fileMenu, "Quit", "Q");
                menuBar.add(fileMenu);
            }
            final JMenu controlMenu = new JMenu("Control");
            menuBar.add(controlMenu);
            this.showMapsToggle = new JCheckBoxMenuItem("Show Maps", true);
            if (!forApplet) {
                this.showMapsToggle.setAccelerator(this.makeAccel("M"));
            }
            this.showMapsToggle.addActionListener(this);
            controlMenu.add(this.showMapsToggle);
            this.colorCodeCommand = new JCheckBoxMenuItem("Color Code Maps", false);
            if (!forApplet) {
                this.colorCodeCommand.setAccelerator(this.makeAccel("K"));
            }
            this.colorCodeCommand.addActionListener(this);
            controlMenu.add(this.colorCodeCommand);
            if (forApplet) {
                controlMenu.addSeparator();
                this.addItem(controlMenu, "Clear All Maps");
            }
            controlMenu.addSeparator();
            this.addItem(controlMenu, "Select No Map");
            this.addItem(controlMenu, "Select Next Map");
            controlMenu.addSeparator();
            this.deleteSelected = this.addItem(controlMenu, "Delete Selected Map", forApplet ? null : "X");
            this.flipSelected = this.addItem(controlMenu, "Flip Selected Map", forApplet ? null : "F");
            this.deleteSelected.setEnabled(false);
            this.flipSelected.setEnabled(false);
            controlMenu.addSeparator();
            this.addItem(controlMenu, "Set Iteration Count");
            final JMenu addMenu = new JMenu("Add Map");
            this.addItem(addMenu, "Add 1/2 Size Map", forApplet ? null : "1");
            this.addItem(addMenu, "Add 1/3 Size Map", forApplet ? null : "2");
            this.addItem(addMenu, "Add 1/4 Size Map", forApplet ? null : "3");
            this.addItem(addMenu, "Add 1/5 Size Map", forApplet ? null : "4");
            this.addItem(addMenu, "Add 1/6 Size Map", forApplet ? null : "5");
            this.addItem(addMenu, "Add 1/8 Size Map", forApplet ? null : "6");
            this.addItem(addMenu, "Add 2/3 Size Map", forApplet ? null : "7");
            this.addItem(addMenu, "Add 3/4 Size Map", forApplet ? null : "8");
            this.addItem(addMenu, "Add 7/8 Size Map", forApplet ? null : "9");
            menuBar.add(addMenu);
            final JMenu exampleMenu = new JMenu("Examples");
            this.addItem(exampleMenu, "Sierpinski Triangle");
            this.addItem(exampleMenu, "Triangle with Inversion");
            this.addItem(exampleMenu, "Sierpinski Carpet");
            this.addItem(exampleMenu, "Koch Curve");
            this.addItem(exampleMenu, "Antenna");
            this.addItem(exampleMenu, "Spiral");
            menuBar.add(exampleMenu);
            return menuBar;
        }
        
        KeyStroke makeAccel(final String keyStroke) {
            if (this.commandKey == null) {
                this.commandKey = "control ";
                try {
                    if (System.getProperty("mrj.version") != null) {
                        this.commandKey = "meta ";
                    }
                }
                catch (SecurityException ex) {}
            }
            return KeyStroke.getKeyStroke(String.valueOf(this.commandKey) + keyStroke);
        }
        
        JMenuItem addItem(final JMenu menu, final String text, final String keyStroke) {
            final JMenuItem item = new JMenuItem(text);
            menu.add(item);
            item.addActionListener(this);
            if (keyStroke != null) {
                item.setAccelerator(this.makeAccel(keyStroke));
            }
            return item;
        }
        
        JMenuItem addItem(final JMenu menu, final String text) {
            return this.addItem(menu, text, null);
        }
        
        void addMap(final double sizeFactor) {
            final double x = 0.5 - sizeFactor / 2.0;
            final double y = 0.5 - sizeFactor / 2.0;
            IFSCanvas.this.addMap(new AffineMap(x, y, x + sizeFactor, y, x, y + sizeFactor), true);
        }
        
        public void actionPerformed(final ActionEvent evt) {
            final String command = evt.getActionCommand();
            if (command.equals("Quit")) {
                System.exit(0);
            }
            else if (command.equals("Save Map Data...")) {
                IFSCanvas.this.saveMapsData();
            }
            else if (command.equals("Open Map Data File...")) {
                IFSCanvas.this.openMapsFile();
            }
            else if (command.equals("Save PNG Image Including Maps...")) {
                IFSCanvas.this.saveImage(true);
            }
            else if (command.equals("Save PNG Image...")) {
                IFSCanvas.this.saveImage(false);
            }
            else if (command.equals("Clear All Maps")) {
                IFSCanvas.this.clear();
            }
            else if (command.equals("Select No Map")) {
                if (IFSCanvas.this.selectedMapIndex >= 0) {
                    IFSCanvas.this.select(-1);
                }
            }
            else if (command.equals("Select Next Map")) {
                if (IFSCanvas.this.maps.size() == 0) {
                    return;
                }
                IFSCanvas.this.setShowMaps(true);
                if (IFSCanvas.this.selectedMapIndex >= 0) {
                    IFSCanvas.this.select((IFSCanvas.this.selectedMapIndex == IFSCanvas.this.maps.size() - 1) ? 0 : (IFSCanvas.this.selectedMapIndex + 1));
                }
                else {
                    IFSCanvas.this.select(0);
                }
            }
            else if (command.equals("Delete Selected Map")) {
                IFSCanvas.this.deleteSelectedMap();
            }
            else if (command.equals("Flip Selected Map")) {
                IFSCanvas.this.flipSelectedMap();
            }
            else if (command.equals("Set Iteration Count")) {
                new IterationCountDialog(IFSCanvas.this).setVisible(true);
            }
            else if (command.equals("Show Maps")) {
                IFSCanvas.this.setShowMaps(this.showMapsToggle.getState());
            }
            else if (command.equals("Color Code Maps")) {
                IFSCanvas.this.setColorCode(this.colorCodeCommand.getState());
            }
            else if (command.equals("Add 1/2 Size Map")) {
                this.addMap(0.5);
            }
            else if (command.equals("Add 1/3 Size Map")) {
                this.addMap(0.3333333333333333);
            }
            else if (command.equals("Add 1/4 Size Map")) {
                this.addMap(0.25);
            }
            else if (command.equals("Add 1/5 Size Map")) {
                this.addMap(0.2);
            }
            else if (command.equals("Add 1/6 Size Map")) {
                this.addMap(0.16666666666666666);
            }
            else if (command.equals("Add 1/8 Size Map")) {
                this.addMap(0.125);
            }
            else if (command.equals("Add 2/3 Size Map")) {
                this.addMap(0.6666666666666666);
            }
            else if (command.equals("Add 3/4 Size Map")) {
                this.addMap(0.75);
            }
            else if (command.equals("Add 7/8 Size Map")) {
                this.addMap(0.875);
            }
            else if (command.equals("Sierpinski Triangle")) {
                final double t = 0.25;
                IFSCanvas.this.installExample(new double[][] { { 0.0, 0.0, 0.5, 0.0, 0.0, 0.5 }, { 0.5, 0.0, 1.0, 0.0, 0.5, 0.5 }, { t, 0.5, t + 0.5, 0.5, t, 1.0 } });
            }
            else if (command.equals("Triangle with Inversion")) {
                final double t = 0.25;
                IFSCanvas.this.installExample(new double[][] { { 0.0, 0.0, 0.5, 0.0, 0.0, 0.5 }, { 0.5, 0.0, 1.0, 0.0, 0.5, 0.5 }, { t, 1.0, t + 0.5, 1.0, t, 0.5 } });
            }
            else if (command.equals("Sierpinski Carpet")) {
                final double t = 0.3333333333333333;
                final double s = 2.0 * t;
                IFSCanvas.this.installExample(new double[][] { { 0.0, 0.0, t, 0.0, 0.0, t }, { t, 0.0, s, 0.0, t, t }, { s, 0.0, 1.0, 0.0, s, t }, { 0.0, t, t, t, 0.0, s }, { s, t, 1.0, t, s, s }, { 0.0, s, t, s, 0.0, 1.0 }, { t, s, s, s, t, 1.0 }, { s, s, 1.0, s, s, 1.0 } });
            }
            else if (command.equals("Koch Curve")) {
                final double t = 0.3333333333333333;
                final double s2 = Math.sin(0.5235987755982988);
                final double c30 = Math.cos(0.5235987755982988);
                IFSCanvas.this.installExample(new double[][] { { 0.0, t, t, t, 0.0, 2.0 * t }, { 2.0 * t, t, 1.0, t, 2.0 * t, 2.0 * t }, { t + t / 2.0 * c30, 0.5 - t / 2.0 * s2, t + t / 2.0 * c30 + t * s2, 0.5 - t / 2.0 * s2 + t * c30, t - t / 2.0 * c30, 0.5 + t / 2.0 * s2 }, { 2.0 * t + -t / 2.0 * c30 - t * s2, 0.5 - t / 2.0 * s2 + t * c30, 2.0 * t + -t / 2.0 * c30, 0.5 - t / 2.0 * s2, 2.0 * t + -t / 2.0 * c30 - t * s2 + t * c30, 0.5 - t / 2.0 * s2 + t * c30 + t * s2 } });
            }
            else if (command.equals("Antenna")) {
                final double t = 0.3333333333333333;
                IFSCanvas.this.installExample(new double[][] { { 0.0, 2.0 * t, t, 2.0 * t, 0.0, 1.0 }, { 2.0 * t, 0.0, 1.0, 0.0, 2.0 * t, t }, { t / 2.0, 1.0 - t / 2.0, 1.0 - t / 2.0, 1.0 - t / 2.0, t / 2.0, t / 2.0 } });
            }
            else if (command.equals("Spiral")) {
                IFSCanvas.this.installExample(new double[][] { { 0.28905566795944176, 0.2513366656842541, 0.09036898139052062, 0.2508887640207258, 0.2895035696229701, 0.05264997911533296 }, { -0.1143641581399009, 0.20908397337302598, 0.7921786528896009, -0.11562678440252727, 0.21034659963565214, 1.1156267844025272 } });
            }
        }
    }
}
