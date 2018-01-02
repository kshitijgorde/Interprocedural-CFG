// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.FontMetrics;
import java.util.Iterator;
import edu.hws.eck.umb.comp.MandelbrotTask;
import edu.hws.eck.umb.util.I18n;
import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.Timer;
import edu.hws.eck.umb.comp.TaskManager;
import java.math.BigDecimal;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import edu.hws.eck.umb.palette.PaletteMapping;
import edu.hws.eck.umb.palette.Palette;
import javax.swing.JPanel;

public class MandelbrotDisplay extends JPanel
{
    public static final String PROPERTY_LIMITS = "mb_property_limits";
    public static final String PROPERTY_MAX_ITERATIONS = "mb_property_iterations";
    public static final String PROPERTY_PALETTE = "mb_property_palette";
    public static final String PROPERTY_HIGH_PRECISION = "mb_property_hp_enabled";
    public static final String PROPERTY_SUBPIXEL_SAMPLING = "mb_subpixel_sampling";
    public static final String PROPERTY_MANDLELBROT_COLOR = "mp_propery_mb_color";
    public static final String PROPERTY_REQUESTED_IMAGE_SIZE = "mb_property_size";
    public static final String PROPERTY_ACTUAL_IMAGE_SIZE = "mb_OSC_size";
    public static final String PROPERTY_ORBIT_POINT = "mb_orbit_point";
    public static final String PROPERTY_NUMBER_OF_POINTS_ON_ORBIT = "mb_points_on_orbit";
    public static final String PROPERTY_CURRENT_MOUSE_ACTION = "mb_mouse_action";
    public static final String PROPERTY_STATUS = "mb_stauts";
    public static final int MOUSE_ACTION_ZOOM_IN = 0;
    public static final int MOUSE_ACTION_ZOOM_OUT = 1;
    public static final int MOUSE_ACTION_DRAG = 2;
    public static final int MOUSE_ACTION_SHOW_ORBIT = 3;
    public static final int MOUSE_ACTION_SHOW_COORDS = 4;
    public static final int MOUSE_ACTION_RECENTER_ON_POINT = 5;
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_RUNNING_FIRST_PASS = 1;
    public static final int STATUS_DONE_FIRST_PASS = 2;
    public static final int STATUS_RUNNING_SECOND_PASS = 3;
    private static final int HP_CUTOFF_EXP = 15;
    public static final double HP_CUTOFF = 1.0E-15;
    private int maxIterations;
    private final Palette palette;
    private final PaletteMapping paletteMapping;
    private int[] paletteColors;
    private float[][] paletteColorComponents;
    private boolean highPrecisionEnabled;
    private boolean subpixelSamplingEnabled;
    private int rgbForMandelbrot;
    private float[] mandelbrotColorComponents;
    private Dimension imageSize;
    private int pointsOnOrbit;
    private int status;
    private BufferedImage OSC;
    private BigDecimal xmin_requested;
    private BigDecimal xmax_requested;
    private BigDecimal ymin_requested;
    private BigDecimal ymax_requested;
    private BigDecimal xmin;
    private BigDecimal xmax;
    private BigDecimal ymin;
    private BigDecimal ymax;
    private int scale;
    private int[][] iterationCounts;
    private int[][] subPixelCounts;
    private double fractionComplete;
    private final TaskManager taskManager;
    private TaskManager.Job currentJob;
    private volatile boolean needsRedraw;
    private boolean newPalette;
    private boolean resizing;
    private Timer resizeTimer;
    private boolean wasPainted;
    private Timer periodicRepaintTimer;
    private boolean announceChanges;
    private Dimension dragImageOffset;
    private Rectangle dragZoomRect;
    private BigDecimal[] orbitStartPoint;
    private String[] coordinateStrings;
    private Point coordinatePoint;
    private ArrayList<Point> orbitPoints;
    private int defaultMouseAction;
    private static final BigDecimal TWO;
    private static final BigDecimal TEN;
    
    public MandelbrotDisplay() {
        this(true, true);
    }
    
    public MandelbrotDisplay(final boolean announceChanges, final boolean b) {
        this.maxIterations = 100;
        this.highPrecisionEnabled = true;
        this.subpixelSamplingEnabled = true;
        this.rgbForMandelbrot = 0;
        this.mandelbrotColorComponents = new float[] { 0.0f, 0.0f, 0.0f };
        this.pointsOnOrbit = 1000;
        this.status = 0;
        this.scale = 20;
        this.newPalette = true;
        this.periodicRepaintTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                MandelbrotDisplay.this.grabAvailableResults();
                MandelbrotDisplay.this.repaint();
            }
        });
        this.defaultMouseAction = 0;
        this.announceChanges = announceChanges;
        if (b) {
            this.addMouseListener(new MouseHandler());
        }
        this.setPreferredSize(new Dimension(800, 600));
        this.setOpaque(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.needsRedraw = true;
        this.taskManager = new TaskManager();
        this.xmin_requested = new BigDecimal("-2.333");
        this.xmax_requested = new BigDecimal("1");
        this.ymin_requested = new BigDecimal("-1.25");
        this.ymax_requested = new BigDecimal("1.25");
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                if (MandelbrotDisplay.this.resizeTimer != null) {
                    MandelbrotDisplay.this.resizeTimer.stop();
                }
                if (!MandelbrotDisplay.this.wasPainted || MandelbrotDisplay.this.imageSize != null) {
                    return;
                }
                MandelbrotDisplay.this.resizing = true;
                MandelbrotDisplay.this.resizeTimer = new Timer(333, new ActionListener() {
                    public void actionPerformed(final ActionEvent actionEvent) {
                        MandelbrotDisplay.this.resizing = false;
                        MandelbrotDisplay.this.resizeTimer = null;
                        MandelbrotDisplay.this.needsRedraw = true;
                        MandelbrotDisplay.this.repaint();
                    }
                });
                MandelbrotDisplay.this.resizeTimer.setInitialDelay(333);
                MandelbrotDisplay.this.resizeTimer.setRepeats(false);
                MandelbrotDisplay.this.resizeTimer.start();
            }
        });
        this.palette = new Palette();
        this.paletteMapping = new PaletteMapping();
        final ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                MandelbrotDisplay.this.newPalette = true;
                MandelbrotDisplay.this.repaint();
            }
        };
        this.palette.addChangeListener(changeListener);
        this.paletteMapping.addChangeListener(changeListener);
    }
    
    public void closing() {
        this.taskManager.shutDown();
    }
    
    public Dimension getImageSize() {
        return this.imageSize;
    }
    
    Dimension getActualImageSize() {
        if (this.OSC == null) {
            return new Dimension(-1, -1);
        }
        return new Dimension(this.OSC.getWidth(), this.OSC.getHeight());
    }
    
    public void setImageSize(final Dimension imageSize) {
        if ((this.imageSize == null && imageSize == null) || (this.imageSize != null && this.imageSize.equals(imageSize))) {
            return;
        }
        final Dimension imageSize2 = this.imageSize;
        this.imageSize = imageSize;
        if (this.imageSize != null) {
            this.setPreferredSize(this.imageSize);
        }
        this.needsRedraw = true;
        this.invalidate();
        if (this.getParent() != null) {
            this.getParent().validate();
        }
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_size", imageSize2, this.imageSize);
        }
        this.repaint();
    }
    
    public Palette getCopyOfPalette() {
        return this.palette.clone();
    }
    
    public void setPalette(final Palette palette) {
        if (palette.equals(this.palette)) {
            return;
        }
        Object o = null;
        if (this.announceChanges) {
            o = new PaletteInfo(this.palette.clone(), this.paletteMapping.clone());
        }
        this.palette.copyFrom(palette);
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_palette", o, new PaletteInfo(palette.clone(), this.paletteMapping.clone()));
        }
    }
    
    public void setPaletteInfo(final Palette palette, final PaletteMapping paletteMapping) {
        if (palette.equals(this.palette) && paletteMapping.equals(this.paletteMapping)) {
            return;
        }
        Object o = null;
        if (this.announceChanges) {
            o = new PaletteInfo(this.palette.clone(), this.paletteMapping.clone());
        }
        this.palette.copyFrom(palette);
        this.paletteMapping.setLength(paletteMapping.getLength());
        this.paletteMapping.setOffset(paletteMapping.getOffset());
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_palette", o, new PaletteInfo(palette.clone(), paletteMapping.clone()));
        }
    }
    
    public double getFractionComplete() {
        return this.fractionComplete;
    }
    
    public int getMaxIterations() {
        return this.maxIterations;
    }
    
    public void setMaxIterations(int maxIterations) {
        if (maxIterations <= 0) {
            maxIterations = 0;
        }
        if (maxIterations == this.maxIterations) {
            return;
        }
        final int maxIterations2 = this.maxIterations;
        this.maxIterations = maxIterations;
        this.paletteColors = null;
        this.paletteColorComponents = null;
        this.needsRedraw = true;
        this.repaint();
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_iterations", maxIterations2, maxIterations);
        }
    }
    
    public Color getMandelbrotColor() {
        return new Color(this.rgbForMandelbrot);
    }
    
    public void setMandelbrotColor(final Color color) {
        if ((color.getRGB() & 0xFFFFFF) == this.rgbForMandelbrot) {
            return;
        }
        final int rgbForMandelbrot = this.rgbForMandelbrot;
        this.rgbForMandelbrot = (color.getRGB() & 0xFFFFFF);
        this.mandelbrotColorComponents = color.getRGBColorComponents(null);
        synchronized (this) {
            if (this.subPixelCounts == null) {
                if (this.OSC != null && this.iterationCounts != null) {
                    for (int i = 0; i < this.iterationCounts.length; ++i) {
                        if (this.iterationCounts[i] != null) {
                            for (int j = 0; j < this.iterationCounts[i].length; ++j) {
                                if (this.iterationCounts[i][j] == this.maxIterations) {
                                    this.OSC.setRGB(j, i, this.rgbForMandelbrot);
                                }
                            }
                        }
                    }
                }
            }
            else {
                this.applyPalette();
            }
            this.repaint();
        }
        if (this.announceChanges) {
            this.firePropertyChange("mp_propery_mb_color", new Color(rgbForMandelbrot), color);
        }
    }
    
    public int getPaletteLength() {
        return this.paletteMapping.getLength();
    }
    
    public void setPaletteLength(int length) {
        if (length < 0) {
            length = 0;
        }
        if (this.paletteMapping.getLength() == length) {
            return;
        }
        Object o = null;
        if (this.announceChanges) {
            o = new PaletteInfo(this.palette.clone(), this.paletteMapping.clone());
        }
        this.paletteMapping.setLength(length);
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_palette", o, new PaletteInfo(this.palette.clone(), this.paletteMapping.clone()));
        }
    }
    
    public int getPaletteOffset() {
        return this.paletteMapping.getOffset();
    }
    
    public void setPaletteOffset(final int offset) {
        if (this.paletteMapping.getOffset() == offset) {
            return;
        }
        Object o = null;
        if (this.announceChanges) {
            o = new PaletteInfo(this.palette.clone(), this.paletteMapping.clone());
        }
        this.paletteMapping.setOffset(offset);
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_palette", o, new PaletteInfo(this.palette.clone(), this.paletteMapping.clone()));
        }
    }
    
    public boolean getHighPrecisionEnabled() {
        return this.highPrecisionEnabled;
    }
    
    public void setHighPrecisionEnabled(final boolean highPrecisionEnabled) {
        if (this.highPrecisionEnabled == highPrecisionEnabled) {
            return;
        }
        this.highPrecisionEnabled = highPrecisionEnabled;
        if (Math.abs((this.xmax.doubleValue() - this.xmin.doubleValue()) / this.getWidth()) < 1.0E-15) {
            this.needsRedraw = true;
            this.repaint();
        }
        if (this.announceChanges) {
            this.firePropertyChange("mb_property_hp_enabled", !this.highPrecisionEnabled, this.highPrecisionEnabled);
        }
    }
    
    public boolean getSubpixelSamplingEnabled() {
        return this.subpixelSamplingEnabled;
    }
    
    public void setSubpixelSamplingEnabled(final boolean subpixelSamplingEnabled) {
        if (this.subpixelSamplingEnabled == subpixelSamplingEnabled) {
            return;
        }
        this.subpixelSamplingEnabled = subpixelSamplingEnabled;
        if (this.announceChanges) {
            this.firePropertyChange("mb_subpixel_sampling", !subpixelSamplingEnabled, subpixelSamplingEnabled);
        }
        synchronized (this) {
            if (this.OSC == null) {
                return;
            }
            if (!subpixelSamplingEnabled) {
                this.subPixelCounts = null;
            }
            if (subpixelSamplingEnabled && this.status == 2) {
                this.startSecondPass();
                this.periodicRepaintTimer.start();
            }
            else if (!subpixelSamplingEnabled) {
                if (this.status == 3 && this.currentJob != null) {
                    this.currentJob.cancel();
                    this.currentJob = null;
                }
                if (this.status != 1) {
                    this.setStatusWithFractionComplete(2, 1.0);
                }
                this.applyPalette();
                this.repaint();
            }
        }
    }
    
    public int getPointsOnOrbit() {
        return this.pointsOnOrbit;
    }
    
    public void setPointsOnOrbit(final int pointsOnOrbit) {
        if (pointsOnOrbit == this.pointsOnOrbit) {
            return;
        }
        this.pointsOnOrbit = pointsOnOrbit;
        if (this.announceChanges) {
            this.firePropertyChange("mb_points_on_orbit", pointsOnOrbit, this.pointsOnOrbit);
        }
        if (this.orbitPoints != null) {
            this.repaint();
        }
    }
    
    public BigDecimal[] getLimitsRequested() {
        return new BigDecimal[] { this.xmin_requested, this.xmax_requested, this.ymin_requested, this.ymax_requested };
    }
    
    public BigDecimal[] getLimits() {
        if (this.OSC == null) {
            return this.getLimitsRequested();
        }
        return new BigDecimal[] { this.xmin, this.xmax, this.ymin, this.ymax };
    }
    
    public void setLimits(final BigDecimal[] array) {
        this.setLimits(array[0], array[1], array[2], array[3]);
    }
    
    public void setLimits(final BigDecimal xmin_requested, final BigDecimal xmax_requested, final BigDecimal ymin_requested, final BigDecimal ymax_requested) {
        if (xmin_requested.equals(this.xmin_requested) && xmax_requested.equals(this.xmax_requested) && ymin_requested.equals(this.ymin_requested) && ymax_requested.equals(this.ymax_requested)) {
            return;
        }
        if (xmax_requested.compareTo(xmin_requested) <= 0 || ymax_requested.compareTo(ymin_requested) <= 0) {
            throw new IllegalArgumentException("maximums must be less than minimums");
        }
        final BigDecimal[] limits = this.getLimits();
        this.xmin_requested = xmin_requested;
        this.xmax_requested = xmax_requested;
        this.ymin_requested = ymin_requested;
        this.ymax_requested = ymax_requested;
        if (this.OSC != null) {
            this.checkAspect();
        }
        this.needsRedraw = true;
        if (this.announceChanges && (!this.xmin.equals(limits[0]) || !this.xmax.equals(limits[1]) || !this.ymin.equals(limits[2]) || !this.ymax.equals(limits[3]))) {
            this.firePropertyChange("mb_property_limits", limits, new BigDecimal[] { this.xmin, this.xmax, this.ymin, this.ymax });
        }
        this.repaint();
    }
    
    public String[] getLimitsAsStrings() {
        if (this.OSC == null) {
            return new String[] { this.xmin_requested.toString(), this.xmax_requested.toString(), this.ymin_requested.toString(), this.ymax_requested.toString() };
        }
        BigDecimal bigDecimal = this.xmax.subtract(this.xmin);
        int n = 5;
        while (bigDecimal.compareTo(MandelbrotDisplay.TEN) < 0) {
            ++n;
            bigDecimal = bigDecimal.multiply(MandelbrotDisplay.TEN);
        }
        return new String[] { this.xmin.setScale(n, 6).toString(), this.xmax.setScale(n, 6).toString(), this.ymin.setScale(n, 6).toString(), this.ymax.setScale(n, 6).toString() };
    }
    
    String[] getOrbitStartPointAsStrings() {
        if (this.orbitStartPoint == null) {
            return null;
        }
        BigDecimal bigDecimal = this.xmax.subtract(this.xmin);
        int n = 5;
        while (bigDecimal.compareTo(MandelbrotDisplay.TEN) < 0) {
            ++n;
            bigDecimal = bigDecimal.multiply(MandelbrotDisplay.TEN);
        }
        return new String[] { this.orbitStartPoint[0].setScale(n, 6).toString(), this.orbitStartPoint[1].setScale(n, 6).toString() };
    }
    
    public synchronized int[] createIterationCountHistogram() {
        if (this.OSC == null) {
            return null;
        }
        final int[] array = new int[this.maxIterations];
        if (this.iterationCounts == null) {
            return array;
        }
        final int height = this.OSC.getHeight();
        final int width = this.OSC.getWidth();
        for (int i = 0; i < height; ++i) {
            final int[] array2 = this.iterationCounts[i];
            if (array2 != null) {
                for (int j = 0; j < width; ++j) {
                    if (array2[j] < this.maxIterations) {
                        final int[] array3 = array;
                        final int n = array2[j];
                        ++array3[n];
                    }
                }
            }
        }
        return array;
    }
    
    public int getDefaultMouseAction() {
        return this.defaultMouseAction;
    }
    
    public void setDefaultMouseAction(final int defaultMouseAction) {
        if (defaultMouseAction == this.defaultMouseAction) {
            return;
        }
        if (defaultMouseAction < 0 || defaultMouseAction > 5) {
            throw new IllegalArgumentException("Illegal mouse action code " + defaultMouseAction);
        }
        final int defaultMouseAction2 = this.defaultMouseAction;
        this.defaultMouseAction = defaultMouseAction;
        if (this.announceChanges) {
            this.firePropertyChange("mb_mouse_action", defaultMouseAction2, this.defaultMouseAction);
        }
    }
    
    public void applySettings(final MandelbrotSettings mandelbrotSettings) {
        this.setMaxIterations(mandelbrotSettings.getMaxIterations());
        this.setPaletteInfo(mandelbrotSettings.getPalette(), mandelbrotSettings.getPaletteMapping());
        this.setMandelbrotColor(mandelbrotSettings.getMandelbrotColor());
        this.setImageSize(mandelbrotSettings.getImageSize());
        this.setLimits(mandelbrotSettings.getLimits());
        this.setHighPrecisionEnabled(mandelbrotSettings.isHighPrecisionEnabled());
    }
    
    public void doZoom(final double n) {
        if (n <= 0.0) {
            throw new IllegalArgumentException("Zoom factor must be positive.");
        }
        if (this.OSC == null) {
            return;
        }
        this.doZoom(this.OSC.getWidth() / 2, this.OSC.getHeight() / 2, n, false);
    }
    
    public synchronized boolean writeImage(final File file, final String s) throws IOException {
        return this.OSC != null && ImageIO.write(this.OSC, s, file);
    }
    
    TaskManager getTaskManager() {
        return this.taskManager;
    }
    
    protected synchronized void paintComponent(final Graphics graphics) {
        this.wasPainted = true;
        if (this.dragImageOffset != null) {
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            graphics.drawImage(this.OSC, this.dragImageOffset.width, this.dragImageOffset.height, null);
            return;
        }
        if (this.resizing) {
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            if (this.OSC != null) {
                graphics.drawImage(this.OSC, 0, 0, null);
            }
            return;
        }
        if (this.needsRedraw) {
            this.setCursor(Cursor.getPredefinedCursor(3));
            if (this.currentJob != null) {
                this.currentJob.cancel();
                this.currentJob = null;
                this.periodicRepaintTimer.stop();
            }
            final int n = (this.imageSize == null) ? this.getWidth() : this.imageSize.width;
            final int n2 = (this.imageSize == null) ? this.getHeight() : this.imageSize.height;
            this.subPixelCounts = null;
            this.iterationCounts = null;
            if (this.OSC == null || this.OSC.getHeight() != n || this.OSC.getWidth() != n2) {
                final Dimension dimension = (this.OSC == null) ? null : new Dimension(this.OSC.getWidth(), this.OSC.getHeight());
                final Dimension dimension2 = new Dimension(n, n2);
                this.OSC = null;
                try {
                    this.OSC = new BufferedImage(n, n2, 1);
                    this.iterationCounts = new int[this.OSC.getHeight()][this.OSC.getWidth() * 2];
                    this.checkAspect();
                }
                catch (OutOfMemoryError outOfMemoryError) {
                    this.OSC = null;
                    this.iterationCounts = null;
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                    graphics.setColor(Color.RED);
                    graphics.drawString(I18n.tr("mandelbrotDisplay.OutOfMemory", new Object[0]), 20, 30);
                    this.setStatusWithFractionComplete(0, 0.0);
                    return;
                }
                if (this.announceChanges && !dimension2.equals(dimension)) {
                    this.firePropertyChange("mb_OSC_size", dimension, dimension2);
                }
            }
            this.iterationCounts = new int[this.OSC.getHeight()][];
            this.setStatusWithFractionComplete(1, 0.0);
            final Graphics graphics2 = this.OSC.getGraphics();
            graphics2.setColor(Color.LIGHT_GRAY);
            graphics2.fillRect(0, 0, this.OSC.getWidth(), this.OSC.getHeight());
            graphics2.dispose();
            this.needsRedraw = false;
            this.currentJob = this.taskManager.createJob();
            final int height = this.OSC.getHeight();
            final int width = this.OSC.getWidth();
            final BigDecimal divide = this.ymax.subtract(this.ymin).divide(new BigDecimal(height - 1), this.ymax.scale(), 6);
            if (!this.highPrecisionEnabled && divide.doubleValue() < 1.0E-20) {
                this.OSC = null;
                this.iterationCounts = null;
                graphics.setColor(Color.WHITE);
                graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
                graphics.setColor(Color.RED);
                graphics.drawString(I18n.tr("mandelbrotDisplay.HighPrecisionRequired", new Object[0]), 20, 30);
                this.setStatusWithFractionComplete(0, 0.0);
                return;
            }
            final boolean b = this.highPrecisionEnabled && Math.abs(divide.doubleValue()) < 1.0E-15;
            for (int i = 0; i < height; ++i) {
                this.currentJob.add(new MandelbrotTask(i, this.xmin, this.xmax, this.ymax.subtract(divide.multiply(new BigDecimal(i))), width, this.maxIterations, b));
            }
            this.currentJob.close();
            this.currentJob.await(200);
            if (this.newPalette) {
                this.paletteColors = null;
                this.paletteColorComponents = null;
                this.newPalette = false;
            }
            this.grabAvailableResults();
            if (this.currentJob != null) {
                this.periodicRepaintTimer.start();
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
        if (this.newPalette) {
            int n3 = this.paletteMapping.getLength();
            if (n3 == 0) {
                n3 = this.maxIterations;
            }
            this.paletteColors = this.palette.makeRGBs(n3, this.paletteMapping.getOffset());
            this.paletteColorComponents = null;
            this.applyPalette();
            this.newPalette = false;
        }
        if (this.OSC.getWidth() < this.getWidth()) {
            final int n4 = this.getWidth() - this.OSC.getWidth();
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(this.OSC.getWidth(), 0, n4, this.getHeight());
            graphics.setColor(Color.BLACK);
            graphics.drawLine(this.OSC.getWidth(), 0, this.OSC.getWidth(), this.OSC.getHeight());
        }
        if (this.OSC.getHeight() < this.getHeight()) {
            final int n5 = this.getHeight() - this.OSC.getHeight();
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.fillRect(0, this.OSC.getHeight(), this.OSC.getWidth(), n5);
            graphics.setColor(Color.BLACK);
            graphics.drawLine(0, this.OSC.getHeight(), this.OSC.getWidth(), this.OSC.getHeight());
        }
        graphics.drawImage(this.OSC, 0, 0, null);
        if (this.dragZoomRect != null) {
            graphics.setColor(Color.WHITE);
            graphics.drawRect(this.dragZoomRect.x, this.dragZoomRect.y, this.dragZoomRect.width - 1, this.dragZoomRect.height - 1);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(this.dragZoomRect.x - 1, this.dragZoomRect.y - 1, this.dragZoomRect.width + 1, this.dragZoomRect.height + 1);
            graphics.drawRect(this.dragZoomRect.x + 1, this.dragZoomRect.y + 1, this.dragZoomRect.width - 3, this.dragZoomRect.height - 3);
        }
        if (this.orbitPoints != null) {
            graphics.setColor(Color.BLACK);
            for (final Point point : this.orbitPoints) {
                graphics.fillRect(point.x - 1, point.y - 3, 3, 7);
                graphics.fillRect(point.x - 3, point.y - 1, 7, 3);
            }
            graphics.setColor(Color.WHITE);
            for (final Point point2 : this.orbitPoints) {
                graphics.fillRect(point2.x, point2.y - 2, 1, 5);
                graphics.fillRect(point2.x - 2, point2.y, 5, 1);
            }
        }
        if (this.coordinateStrings != null) {
            final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
            final int n6 = fontMetrics.getHeight() * (this.coordinateStrings.length - 1) + fontMetrics.getAscent() + fontMetrics.getDescent();
            int n7 = 0;
            final String[] coordinateStrings = this.coordinateStrings;
            for (int length = coordinateStrings.length, j = 0; j < length; ++j) {
                final int stringWidth = fontMetrics.stringWidth(coordinateStrings[j]);
                if (stringWidth > n7) {
                    n7 = stringWidth;
                }
            }
            int n8 = this.coordinatePoint.x - n7 - 13;
            int n9 = this.coordinatePoint.y - n6 - 13;
            if (n8 < 0) {
                n8 = this.coordinatePoint.x + 20;
            }
            if (n9 < 0) {
                n9 = this.coordinatePoint.y + 20;
            }
            graphics.setColor(Color.WHITE);
            graphics.fillRect(n8 + 2, n9 + 2, n7 + 6, n6 + 6);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(n8 + 2, n9 + 2, n7 + 6, n6 + 6);
            for (int k = 0; k < this.coordinateStrings.length; ++k) {
                graphics.drawString(this.coordinateStrings[k], n8 + 5, n9 + 5 + fontMetrics.getAscent() + fontMetrics.getHeight() * k);
            }
        }
    }
    
    private void setStatusWithFractionComplete(final int status, final double fractionComplete) {
        if (status == this.status && fractionComplete == this.fractionComplete) {
            return;
        }
        final double fractionComplete2 = this.fractionComplete;
        final int status2 = this.status;
        this.status = status;
        this.fractionComplete = fractionComplete;
        if (this.announceChanges) {
            this.firePropertyChange("mb_stauts", new StatusInfo(status2, fractionComplete2), new StatusInfo(this.status, fractionComplete));
        }
    }
    
    private void checkAspect() {
        this.xmin = this.xmin_requested;
        this.xmax = this.xmax_requested;
        this.ymin = this.ymin_requested;
        this.ymax = this.ymax_requested;
        if (this.xmin.scale() < 23) {
            this.xmin.setScale(23);
        }
        if (this.xmax.scale() < 23) {
            this.xmax.setScale(23);
        }
        if (this.ymin.scale() < 23) {
            this.ymin.setScale(23);
        }
        if (this.ymax.scale() < 23) {
            this.ymax.setScale(23);
        }
        BigDecimal bigDecimal = this.xmax.subtract(this.xmin).setScale(Math.max(this.xmax.scale(), 15) * 2, 6).divide(new BigDecimal(this.OSC.getWidth()), 6);
        int n = 0;
        while (bigDecimal.compareTo(MandelbrotDisplay.TWO) < 0) {
            ++n;
            bigDecimal = bigDecimal.multiply(MandelbrotDisplay.TEN);
        }
        if (n < 15) {
            n = 15;
        }
        this.scale = n + 5 + (n - 10) / 10;
        this.xmin = this.xmin.setScale(this.scale, 6);
        this.xmax = this.xmax.setScale(this.scale, 6);
        this.ymin = this.ymin.setScale(this.scale, 6);
        this.ymax = this.ymax.setScale(this.scale, 6);
        final BigDecimal subtract = this.xmax.subtract(this.xmin);
        final BigDecimal subtract2 = this.ymax.subtract(this.ymin);
        final BigDecimal divide = subtract.divide(subtract2, 6);
        final BigDecimal bigDecimal2 = new BigDecimal(this.OSC.getWidth() / this.OSC.getHeight());
        if (divide.compareTo(bigDecimal2) < 0) {
            final BigDecimal divide2 = subtract.multiply(bigDecimal2).divide(divide, 6);
            final BigDecimal divide3 = this.xmax.add(this.xmin).divide(MandelbrotDisplay.TWO, 6);
            this.xmax = divide3.add(divide2.divide(MandelbrotDisplay.TWO, 6)).setScale(this.scale, 6);
            this.xmin = divide3.subtract(divide2.divide(MandelbrotDisplay.TWO, 6)).setScale(this.scale, 6);
        }
        else if (divide.compareTo(bigDecimal2) > 0) {
            final BigDecimal divide4 = subtract2.multiply(divide).divide(bigDecimal2, 6);
            final BigDecimal divide5 = this.ymax.add(this.ymin).divide(MandelbrotDisplay.TWO, 6);
            this.ymax = divide5.add(divide4.divide(MandelbrotDisplay.TWO, 6)).setScale(this.scale, 6);
            this.ymin = divide5.subtract(divide4.divide(MandelbrotDisplay.TWO, 6)).setScale(this.scale, 6);
        }
    }
    
    private synchronized void grabAvailableResults() {
        if (this.currentJob == null) {
            return;
        }
        final MandelbrotTask[] finishedTasks;
        final boolean finished;
        final double fractionDone;
        synchronized (this.taskManager) {
            finishedTasks = this.currentJob.finishedTasks();
            finished = this.currentJob.isFinished();
            fractionDone = this.currentJob.fractionDone();
        }
        for (final MandelbrotTask mandelbrotTask : finishedTasks) {
            final int rowNumber = mandelbrotTask.getRowNumber();
            final int[] results = mandelbrotTask.getResults();
            if (this.paletteColors == null) {
                final int offset = this.paletteMapping.getOffset();
                int n = this.paletteMapping.getLength();
                if (n == 0) {
                    n = this.maxIterations;
                }
                this.paletteColors = this.palette.makeRGBs(n, offset);
                this.paletteColorComponents = null;
            }
            if (this.status == 3) {
                this.subPixelCounts[rowNumber] = results;
                if (rowNumber > 0 && this.subPixelCounts[rowNumber - 1] != null) {
                    this.applySubpixelData(rowNumber - 1);
                }
                if (rowNumber < this.subPixelCounts.length - 1 && this.subPixelCounts[rowNumber + 1] != null) {
                    this.applySubpixelData(rowNumber);
                }
            }
            else {
                this.iterationCounts[rowNumber] = results;
                for (int j = 0; j < results.length; ++j) {
                    int rgbForMandelbrot;
                    if (results[j] == this.maxIterations) {
                        rgbForMandelbrot = this.rgbForMandelbrot;
                    }
                    else {
                        rgbForMandelbrot = this.paletteColors[results[j] % this.paletteColors.length];
                    }
                    this.OSC.setRGB(j, rowNumber, rgbForMandelbrot);
                }
                this.repaint(0, rowNumber, results.length, 1);
            }
        }
        this.setFractionComplete(fractionDone);
        if (finished) {
            if (this.status == 3 || !this.subpixelSamplingEnabled) {
                this.periodicRepaintTimer.stop();
                this.currentJob = null;
                this.setStatusWithFractionComplete((this.status == 1) ? 2 : 0, 1.0);
                return;
            }
            this.startSecondPass();
        }
    }
    
    private void startSecondPass() {
        this.currentJob = this.taskManager.createJob();
        final int n = this.OSC.getHeight() + 1;
        final int n2 = this.OSC.getWidth() + 1;
        this.subPixelCounts = new int[n][];
        final BigDecimal divide = this.ymax.subtract(this.ymin).divide(new BigDecimal(n - 1), this.ymax.scale(), 6);
        final BigDecimal divide2 = divide.divide(MandelbrotDisplay.TWO, this.ymax.scale(), 6);
        final BigDecimal subtract = this.xmin.subtract(divide2);
        final BigDecimal add = this.xmax.add(divide2);
        final BigDecimal add2 = this.ymax.add(divide2);
        final boolean b = this.highPrecisionEnabled && Math.abs(divide.doubleValue()) < 1.0E-15;
        for (int i = 0; i < n; ++i) {
            this.currentJob.add(new MandelbrotTask(i, subtract, add, add2.subtract(divide.multiply(new BigDecimal(i))), n2, this.maxIterations, b));
        }
        this.currentJob.close();
        this.setStatusWithFractionComplete(3, 0.0);
    }
    
    private void applySubpixelData(final int n) {
        if (this.paletteColorComponents == null) {
            this.paletteColorComponents = new float[this.paletteColors.length][];
            for (int i = 0; i < this.paletteColors.length; ++i) {
                this.paletteColorComponents[i] = new Color(this.paletteColors[i]).getRGBColorComponents(null);
            }
        }
        final int[] array = this.iterationCounts[n];
        final int[] array2 = this.subPixelCounts[n];
        final int[] array3 = this.subPixelCounts[n + 1];
        for (int j = 0; j < array.length; ++j) {
            float[] mandelbrotColorComponents;
            if (array[j] == this.maxIterations) {
                mandelbrotColorComponents = this.mandelbrotColorComponents;
            }
            else {
                mandelbrotColorComponents = this.paletteColorComponents[array[j] % this.paletteColors.length];
            }
            float[] mandelbrotColorComponents2;
            if (array2[j] == this.maxIterations) {
                mandelbrotColorComponents2 = this.mandelbrotColorComponents;
            }
            else {
                mandelbrotColorComponents2 = this.paletteColorComponents[array2[j] % this.paletteColors.length];
            }
            float[] mandelbrotColorComponents3;
            if (array2[j + 1] == this.maxIterations) {
                mandelbrotColorComponents3 = this.mandelbrotColorComponents;
            }
            else {
                mandelbrotColorComponents3 = this.paletteColorComponents[array2[j + 1] % this.paletteColors.length];
            }
            float[] mandelbrotColorComponents4;
            if (array3[j] == this.maxIterations) {
                mandelbrotColorComponents4 = this.mandelbrotColorComponents;
            }
            else {
                mandelbrotColorComponents4 = this.paletteColorComponents[array3[j] % this.paletteColors.length];
            }
            float[] mandelbrotColorComponents5;
            if (array3[j + 1] == this.maxIterations) {
                mandelbrotColorComponents5 = this.mandelbrotColorComponents;
            }
            else {
                mandelbrotColorComponents5 = this.paletteColorComponents[array3[j + 1] % this.paletteColors.length];
            }
            this.OSC.setRGB(j, n, new Color((4.0f * mandelbrotColorComponents[0] + mandelbrotColorComponents2[0] + mandelbrotColorComponents3[0] + mandelbrotColorComponents4[0] + mandelbrotColorComponents5[0]) / 8.0f, (4.0f * mandelbrotColorComponents[1] + mandelbrotColorComponents2[1] + mandelbrotColorComponents3[1] + mandelbrotColorComponents4[1] + mandelbrotColorComponents5[1]) / 8.0f, (4.0f * mandelbrotColorComponents[2] + mandelbrotColorComponents2[2] + mandelbrotColorComponents3[2] + mandelbrotColorComponents4[2] + mandelbrotColorComponents5[2]) / 8.0f).getRGB());
        }
    }
    
    private void applyPalette() {
        if (this.paletteColors == null || this.OSC == null) {
            return;
        }
        for (int height = this.OSC.getHeight(), i = 0; i < height; ++i) {
            if (this.iterationCounts[i] != null) {
                if (this.subPixelCounts != null && this.subPixelCounts[i] != null && this.subPixelCounts[i + 1] != null) {
                    this.applySubpixelData(i);
                }
                else {
                    for (int j = 0; j < this.iterationCounts[i].length; ++j) {
                        int rgbForMandelbrot;
                        if (this.iterationCounts[i][j] == this.maxIterations) {
                            rgbForMandelbrot = this.rgbForMandelbrot;
                        }
                        else {
                            rgbForMandelbrot = this.paletteColors[this.iterationCounts[i][j] % this.paletteColors.length];
                        }
                        this.OSC.setRGB(j, i, rgbForMandelbrot);
                    }
                }
            }
        }
    }
    
    private void setFractionComplete(final double fractionComplete) {
        if (fractionComplete == this.fractionComplete) {
            return;
        }
        final double fractionComplete2 = this.fractionComplete;
        this.fractionComplete = fractionComplete;
        final int status = this.status;
        this.firePropertyChange("mb_stauts", new StatusInfo(status, fractionComplete2), new StatusInfo(status, fractionComplete));
    }
    
    private void doZoom(final int n, final int n2, final double n3, final boolean b) {
        if (this.OSC == null) {
            return;
        }
        final BigDecimal bigDecimal = new BigDecimal(n3);
        final BigDecimal bigDecimal2 = new BigDecimal(n);
        final BigDecimal bigDecimal3 = new BigDecimal(n2);
        final BigDecimal bigDecimal4 = new BigDecimal(this.OSC.getWidth());
        final BigDecimal bigDecimal5 = new BigDecimal(this.OSC.getHeight());
        final BigDecimal subtract = this.xmax.subtract(this.xmin);
        final BigDecimal subtract2 = this.ymax.subtract(this.ymin);
        final BigDecimal multiply = subtract.multiply(bigDecimal);
        final BigDecimal multiply2 = subtract2.multiply(bigDecimal);
        final BigDecimal divide = multiply.divide(bigDecimal4, 6);
        final BigDecimal divide2 = multiply2.divide(bigDecimal5, 6);
        final BigDecimal add = this.xmin.add(bigDecimal2.multiply(subtract).divide(bigDecimal4, 6));
        final BigDecimal subtract3 = this.ymax.subtract(bigDecimal3.multiply(subtract2).divide(bigDecimal5, 6));
        BigDecimal bigDecimal6;
        BigDecimal bigDecimal7;
        if (b) {
            bigDecimal6 = add.subtract(multiply.divide(MandelbrotDisplay.TWO, 6));
            bigDecimal7 = subtract3.add(multiply2.divide(MandelbrotDisplay.TWO, 6));
        }
        else {
            bigDecimal6 = add.subtract(bigDecimal2.multiply(divide));
            bigDecimal7 = subtract3.add(bigDecimal3.multiply(divide2));
        }
        this.setLimits(bigDecimal6, bigDecimal6.add(multiply), bigDecimal7.subtract(multiply2), bigDecimal7);
    }
    
    private void doRecenter(final int n, final int n2) {
        final BigDecimal divide = this.xmax.subtract(this.xmin).divide(new BigDecimal(this.OSC.getWidth()), 6);
        final BigDecimal add = this.xmin.add(divide.multiply(new BigDecimal(n)));
        final BigDecimal subtract = this.ymax.subtract(divide.multiply(new BigDecimal(n2)));
        final BigDecimal setScale = add.subtract(divide.multiply(new BigDecimal(this.getWidth() / 2))).setScale(this.xmin.scale(), 6);
        final BigDecimal setScale2 = subtract.subtract(divide.multiply(new BigDecimal(this.getHeight() / 2))).setScale(this.xmin.scale(), 6);
        this.setLimits(setScale, setScale.add(this.xmax.subtract(this.xmin)).setScale(this.xmin.scale(), 6), setScale2, setScale2.add(this.ymax.subtract(this.ymin)).setScale(this.xmin.scale(), 6));
    }
    
    private void doZoomInOnRect(final Rectangle rectangle) {
        if (this.OSC == null) {
            return;
        }
        final BigDecimal bigDecimal = new BigDecimal(rectangle.x);
        final BigDecimal bigDecimal2 = new BigDecimal(rectangle.y);
        final BigDecimal bigDecimal3 = new BigDecimal(rectangle.width);
        final BigDecimal bigDecimal4 = new BigDecimal(rectangle.height);
        final BigDecimal bigDecimal5 = new BigDecimal(this.OSC.getWidth());
        final BigDecimal bigDecimal6 = new BigDecimal(this.OSC.getHeight());
        final BigDecimal divide = this.xmax.subtract(this.xmin).divide(bigDecimal5, 6);
        final BigDecimal divide2 = this.ymax.subtract(this.ymin).divide(bigDecimal6, 6);
        final BigDecimal add = this.xmin.add(divide.multiply(bigDecimal));
        final BigDecimal subtract = this.ymax.subtract(divide2.multiply(bigDecimal2));
        this.setLimits(add, add.add(divide.multiply(bigDecimal3)), subtract.subtract(divide2.multiply(bigDecimal4)), subtract);
    }
    
    private void doZoomOutFromRect(final Rectangle rectangle) {
        if (this.OSC == null) {
            return;
        }
        final BigDecimal bigDecimal = new BigDecimal(rectangle.x);
        final BigDecimal bigDecimal2 = new BigDecimal(rectangle.y);
        final BigDecimal bigDecimal3 = new BigDecimal(rectangle.width);
        final BigDecimal bigDecimal4 = new BigDecimal(rectangle.height);
        final BigDecimal bigDecimal5 = new BigDecimal(this.OSC.getWidth());
        final BigDecimal bigDecimal6 = new BigDecimal(this.OSC.getHeight());
        final BigDecimal divide = this.xmax.subtract(this.xmin).divide(bigDecimal3, 6);
        final BigDecimal divide2 = this.ymax.subtract(this.ymin).divide(bigDecimal4, 6);
        final BigDecimal subtract = this.xmin.subtract(divide.multiply(bigDecimal));
        final BigDecimal add = this.ymax.add(divide2.multiply(bigDecimal2));
        this.setLimits(subtract, subtract.add(divide.multiply(bigDecimal5)), add.subtract(divide2.multiply(bigDecimal6)), add);
    }
    
    private void showCoords(final int n, final int n2) {
        BigDecimal bigDecimal2;
        final BigDecimal bigDecimal = bigDecimal2 = this.xmax.subtract(this.xmin).divide(new BigDecimal(this.OSC.getWidth()), 6);
        int n3 = 3;
        while (bigDecimal2.compareTo(MandelbrotDisplay.TEN) < 0) {
            ++n3;
            bigDecimal2 = bigDecimal2.multiply(MandelbrotDisplay.TEN);
        }
        final BigDecimal setScale = this.xmin.add(bigDecimal.multiply(new BigDecimal(n))).setScale(n3, 6);
        final BigDecimal setScale2 = this.ymax.subtract(bigDecimal.multiply(new BigDecimal(n2))).setScale(n3, 6);
        final String string = "x = " + setScale;
        final String string2 = "y = " + setScale2;
        String string3 = null;
        if (n >= 0 && n < this.OSC.getWidth() && n2 >= 0 && n2 < this.OSC.getHeight()) {
            synchronized (this) {
                if (this.iterationCounts != null && this.iterationCounts[n2] != null) {
                    string3 = I18n.tr("mandelbrotDisplay.IterationCount", new Object[0]) + " = " + this.iterationCounts[n2][n];
                }
            }
        }
        if (string3 == null) {
            this.coordinateStrings = new String[] { string, string2 };
        }
        else {
            this.coordinateStrings = new String[] { string, string2, string3 };
        }
        this.coordinatePoint = new Point(n, n2);
        this.repaint();
    }
    
    private void showOrbit(final int n, final int n2) {
        final BigDecimal divide = this.xmax.subtract(this.xmin).divide(new BigDecimal(this.OSC.getWidth()), 6);
        final BigDecimal divide2 = this.ymax.subtract(this.ymin).divide(new BigDecimal(this.OSC.getHeight()), 6);
        final BigDecimal setScale = this.xmin.add(divide.multiply(new BigDecimal(n))).setScale(this.scale, 6);
        final BigDecimal setScale2 = this.ymax.subtract(divide2.multiply(new BigDecimal(n2))).setScale(this.scale, 6);
        BigDecimal bigDecimal = setScale;
        BigDecimal bigDecimal2 = setScale2;
        final BigDecimal bigDecimal3 = new BigDecimal(100);
        (this.orbitPoints = new ArrayList<Point>()).add(new Point(n, n2));
        for (int i = 1; i < this.pointsOnOrbit; ++i) {
            final BigDecimal setScale3 = bigDecimal.multiply(bigDecimal).setScale(this.scale, 6);
            final BigDecimal setScale4 = bigDecimal2.multiply(bigDecimal2).setScale(this.scale, 6);
            if (setScale3.add(setScale4).abs().compareTo(bigDecimal3) > 0) {
                break;
            }
            final BigDecimal add = setScale3.subtract(setScale4).add(setScale);
            final BigDecimal add2 = MandelbrotDisplay.TWO.multiply(bigDecimal).multiply(bigDecimal2).setScale(this.scale, 6).add(setScale2);
            bigDecimal = add;
            bigDecimal2 = add2;
            if (bigDecimal.compareTo(this.xmin) >= 0 && bigDecimal.compareTo(this.xmax) <= 0 && bigDecimal2.compareTo(this.ymin) >= 0 && bigDecimal2.compareTo(this.ymax) <= 0) {
                this.orbitPoints.add(new Point(bigDecimal.subtract(this.xmin).divide(divide, 6).intValue(), this.ymax.subtract(bigDecimal2).divide(divide2, 6).intValue()));
            }
        }
        this.repaint();
        final BigDecimal[] orbitStartPoint = this.orbitStartPoint;
        this.orbitStartPoint = new BigDecimal[] { setScale, setScale2 };
        if (this.announceChanges) {
            this.firePropertyChange("mb_orbit_point", orbitStartPoint, this.orbitStartPoint);
        }
    }
    
    protected void setOrbitStart(final BigDecimal bigDecimal, final BigDecimal bigDecimal2) {
        if (bigDecimal == null) {
            this.orbitPoints = null;
        }
        else {
            final BigDecimal divide = this.xmax.subtract(this.xmin).divide(new BigDecimal(this.OSC.getWidth()), 6);
            final BigDecimal divide2 = this.ymax.subtract(this.ymin).divide(new BigDecimal(this.OSC.getHeight()), 6);
            BigDecimal bigDecimal3 = bigDecimal;
            BigDecimal bigDecimal4 = bigDecimal2;
            final BigDecimal bigDecimal5 = new BigDecimal(100);
            this.orbitPoints = new ArrayList<Point>();
            for (int i = 0; i < this.pointsOnOrbit; ++i) {
                if (bigDecimal3.compareTo(this.xmin) >= 0 && bigDecimal3.compareTo(this.xmax) <= 0 && bigDecimal4.compareTo(this.ymin) >= 0 && bigDecimal4.compareTo(this.ymax) <= 0) {
                    this.orbitPoints.add(new Point(bigDecimal3.subtract(this.xmin).divide(divide, 6).intValue(), this.ymax.subtract(bigDecimal4).divide(divide2, 6).intValue()));
                }
                final BigDecimal setScale = bigDecimal3.multiply(bigDecimal3).setScale(this.scale, 6);
                final BigDecimal setScale2 = bigDecimal4.multiply(bigDecimal4).setScale(this.scale, 6);
                if (setScale.add(setScale2).abs().compareTo(bigDecimal5) > 0) {
                    break;
                }
                final BigDecimal add = setScale.subtract(setScale2).add(bigDecimal);
                final BigDecimal add2 = MandelbrotDisplay.TWO.multiply(bigDecimal3).multiply(bigDecimal4).setScale(this.scale, 6).add(bigDecimal2);
                bigDecimal3 = add;
                bigDecimal4 = add2;
            }
        }
        this.repaint();
    }
    
    static {
        TWO = new BigDecimal("2");
        TEN = new BigDecimal("10");
    }
    
    public static class StatusInfo
    {
        public final int status;
        public final double fractionComplete;
        
        public StatusInfo(final int status, final double fractionComplete) {
            this.status = status;
            this.fractionComplete = fractionComplete;
        }
    }
    
    public static class PaletteInfo
    {
        public final Palette palette;
        public final PaletteMapping paletteMapping;
        
        public PaletteInfo(final Palette palette, final PaletteMapping paletteMapping) {
            this.palette = palette;
            this.paletteMapping = paletteMapping;
        }
    }
    
    private class MouseHandler extends MouseAdapter implements MouseMotionListener
    {
        boolean dragging;
        boolean movedMouse;
        int startX;
        int startY;
        int mouseAction;
        
        private MouseHandler() {
            this.movedMouse = false;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            if (MandelbrotDisplay.this.OSC == null || this.dragging) {
                return;
            }
            this.startX = mouseEvent.getX();
            this.startY = mouseEvent.getY();
            if (this.startX > MandelbrotDisplay.this.OSC.getWidth() || this.startY > MandelbrotDisplay.this.OSC.getHeight()) {
                return;
            }
            if (mouseEvent.isShiftDown()) {
                this.mouseAction = 2;
            }
            else if (mouseEvent.isControlDown() || mouseEvent.isMetaDown()) {
                this.mouseAction = 0;
            }
            else if (mouseEvent.isAltDown()) {
                this.mouseAction = 1;
            }
            else {
                this.mouseAction = MandelbrotDisplay.this.defaultMouseAction;
            }
            if (this.mouseAction == 5) {
                MandelbrotDisplay.this.doRecenter(this.startX, this.startY);
                return;
            }
            if (this.mouseAction == 0 && mouseEvent.getClickCount() == 2) {
                MandelbrotDisplay.this.doZoom(this.startX, this.startY, 0.5, false);
                return;
            }
            if (this.mouseAction == 1 && mouseEvent.getClickCount() == 2) {
                MandelbrotDisplay.this.doZoom(this.startX, this.startY, 2.0, false);
                return;
            }
            this.dragging = true;
            MandelbrotDisplay.this.addMouseMotionListener(this);
            this.movedMouse = false;
            if (this.mouseAction == 4) {
                MandelbrotDisplay.this.showCoords(this.startX, this.startY);
            }
            else if (this.mouseAction == 3) {
                MandelbrotDisplay.this.showOrbit(this.startX, this.startY);
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (!this.dragging) {
                return;
            }
            MandelbrotDisplay.this.removeMouseMotionListener(this);
            if (this.mouseAction == 2 && MandelbrotDisplay.this.dragImageOffset != null) {
                final BigDecimal divide = MandelbrotDisplay.this.xmax.subtract(MandelbrotDisplay.this.xmin).divide(new BigDecimal(MandelbrotDisplay.this.OSC.getWidth()), 6);
                final BigDecimal divide2 = MandelbrotDisplay.this.ymax.subtract(MandelbrotDisplay.this.ymin).divide(new BigDecimal(MandelbrotDisplay.this.OSC.getHeight()), 6);
                final BigDecimal setScale = divide.multiply(new BigDecimal(MandelbrotDisplay.this.dragImageOffset.width)).setScale(MandelbrotDisplay.this.xmin.scale(), 6);
                final BigDecimal setScale2 = divide2.multiply(new BigDecimal(MandelbrotDisplay.this.dragImageOffset.height)).setScale(MandelbrotDisplay.this.xmin.scale(), 6);
                MandelbrotDisplay.this.setLimits(MandelbrotDisplay.this.xmin.subtract(setScale).setScale(MandelbrotDisplay.this.xmin.scale(), 6), MandelbrotDisplay.this.xmax.subtract(setScale).setScale(MandelbrotDisplay.this.xmin.scale(), 6), MandelbrotDisplay.this.ymin.add(setScale2).setScale(MandelbrotDisplay.this.xmin.scale(), 6), MandelbrotDisplay.this.ymax.add(setScale2).setScale(MandelbrotDisplay.this.xmin.scale(), 6));
            }
            else if (this.mouseAction == 0 && MandelbrotDisplay.this.dragZoomRect != null) {
                MandelbrotDisplay.this.doZoomInOnRect(MandelbrotDisplay.this.dragZoomRect);
            }
            else if (this.mouseAction == 1 && MandelbrotDisplay.this.dragZoomRect != null) {
                MandelbrotDisplay.this.doZoomOutFromRect(MandelbrotDisplay.this.dragZoomRect);
            }
            this.dragging = false;
            MandelbrotDisplay.this.dragImageOffset = null;
            MandelbrotDisplay.this.dragZoomRect = null;
            MandelbrotDisplay.this.coordinateStrings = null;
            MandelbrotDisplay.this.coordinatePoint = null;
            MandelbrotDisplay.this.orbitPoints = null;
            if (MandelbrotDisplay.this.orbitStartPoint != null) {
                final BigDecimal[] access$2500 = MandelbrotDisplay.this.orbitStartPoint;
                MandelbrotDisplay.this.orbitStartPoint = null;
                if (MandelbrotDisplay.this.announceChanges) {
                    MandelbrotDisplay.this.firePropertyChange("mb_orbit_point", access$2500, MandelbrotDisplay.this.orbitStartPoint);
                }
            }
            MandelbrotDisplay.this.repaint();
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (!this.dragging) {
                return;
            }
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (this.mouseAction == 4) {
                MandelbrotDisplay.this.showCoords(x, y);
                return;
            }
            if (this.mouseAction == 3) {
                MandelbrotDisplay.this.showOrbit(x, y);
                return;
            }
            final int n = x - this.startX;
            final int n2 = y - this.startY;
            if (!this.movedMouse && Math.abs(n) < 3 && Math.abs(n2) < 3) {
                return;
            }
            this.movedMouse = true;
            if (this.mouseAction == 2) {
                if (Math.abs(n) < 3 && Math.abs(n2) < 3) {
                    MandelbrotDisplay.this.dragImageOffset = null;
                }
                else {
                    MandelbrotDisplay.this.dragImageOffset = new Dimension(n, n2);
                }
            }
            else if (this.mouseAction == 0 || this.mouseAction == 1) {
                int abs = Math.abs(n);
                int abs2 = Math.abs(n2);
                if (abs < 3 || abs2 < 3) {
                    MandelbrotDisplay.this.dragZoomRect = null;
                }
                else {
                    final double n3 = MandelbrotDisplay.this.OSC.getWidth() / MandelbrotDisplay.this.OSC.getHeight();
                    final double n4 = abs / abs2;
                    if (n3 > n4) {
                        abs = (int)(abs * n3 / n4 + 0.499);
                    }
                    else if (n3 < n4) {
                        abs2 = (int)(abs2 * n4 / n3 + 0.499);
                    }
                    int startX;
                    if (this.startX < x) {
                        startX = this.startX;
                    }
                    else {
                        startX = this.startX - abs;
                    }
                    int startY;
                    if (this.startY < y) {
                        startY = this.startY;
                    }
                    else {
                        startY = this.startY - abs2;
                    }
                    MandelbrotDisplay.this.dragZoomRect = new Rectangle(startX, startY, abs, abs2);
                }
            }
            MandelbrotDisplay.this.repaint();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
}
