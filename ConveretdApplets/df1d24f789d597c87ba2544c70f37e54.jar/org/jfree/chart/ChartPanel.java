// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JMenu;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import org.jfree.ui.ExtensionFileFilter;
import javax.swing.JFileChooser;
import java.awt.Component;
import javax.swing.JOptionPane;
import org.jfree.chart.ui.ChartPropertyEditPanel;
import org.jfree.chart.plot.ValueAxisPlot;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import java.util.Iterator;
import java.awt.Shape;
import java.awt.Color;
import org.jfree.util.Log;
import org.jfree.ui.RefineryUtilities;
import java.io.IOException;
import java.awt.event.ActionEvent;
import org.jfree.chart.event.ChartProgressEvent;
import org.jfree.chart.event.ChartChangeEvent;
import java.awt.image.ImageObserver;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import org.jfree.chart.entity.ChartEntity;
import java.awt.Insets;
import org.jfree.chart.entity.EntityCollection;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import javax.swing.ToolTipManager;
import java.awt.Dimension;
import java.util.ArrayList;
import org.jfree.util.LogContext;
import java.util.ResourceBundle;
import java.awt.geom.Line2D;
import javax.swing.JMenuItem;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import javax.swing.JPopupMenu;
import java.awt.Image;
import java.util.List;
import java.io.Serializable;
import java.awt.print.Printable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.event.ChartChangeListener;
import javax.swing.JPanel;

public class ChartPanel extends JPanel implements ChartPanelConstants, ChartChangeListener, ChartProgressListener, ActionListener, MouseListener, MouseMotionListener, Printable, Serializable
{
    private JFreeChart chart;
    private List chartMouseListeners;
    private boolean useBuffer;
    private boolean refreshBuffer;
    private Image chartBuffer;
    private int chartBufferHeight;
    private int chartBufferWidth;
    private int minimumDrawWidth;
    private int minimumDrawHeight;
    private int maximumDrawWidth;
    private int maximumDrawHeight;
    private JPopupMenu popup;
    private ChartRenderingInfo info;
    private Point2D anchor;
    private double scaleX;
    private double scaleY;
    private transient Rectangle2D zoomRectangle;
    private Point2D zoomPoint;
    private boolean fillZoomRectangle;
    private boolean horizontalZoom;
    private boolean verticalZoom;
    private int zoomTriggerDistance;
    private boolean horizontalAxisTrace;
    private boolean verticalAxisTrace;
    private JMenuItem zoomInBothMenuItem;
    private JMenuItem zoomInHorizontalMenuItem;
    private JMenuItem zoomInVerticalMenuItem;
    private JMenuItem zoomOutBothMenuItem;
    private JMenuItem zoomOutHorizontalMenuItem;
    private JMenuItem zoomOutVerticalMenuItem;
    private JMenuItem autoRangeBothMenuItem;
    private JMenuItem autoRangeHorizontalMenuItem;
    private JMenuItem autoRangeVerticalMenuItem;
    private transient Line2D verticalTraceLine;
    private transient Line2D horizontalTraceLine;
    private boolean enforceFileExtensions;
    private boolean ownToolTipDelaysActive;
    private int originalToolTipInitialDelay;
    private int originalToolTipReshowDelay;
    private int originalToolTipDismissDelay;
    private int ownToolTipInitialDelay;
    private int ownToolTipReshowDelay;
    private int ownToolTipDismissDelay;
    private double zoomInFactor;
    private double zoomOutFactor;
    protected static ResourceBundle localizationResources;
    private static final LogContext LOGGER;
    private transient Rectangle2D available;
    private transient Rectangle2D chartArea;
    static /* synthetic */ Class class$org$jfree$chart$ChartPanel;
    
    public ChartPanel(final JFreeChart chart) {
        this(chart, 680, 420, 300, 200, 800, 600, false, true, true, true, true, true);
    }
    
    public ChartPanel(final JFreeChart chart, final boolean useBuffer) {
        this(chart, 680, 420, 300, 200, 800, 600, useBuffer, true, true, true, true, true);
    }
    
    public ChartPanel(final JFreeChart chart, final boolean properties, final boolean save, final boolean print, final boolean zoom, final boolean tooltips) {
        this(chart, 680, 420, 300, 200, 800, 600, false, properties, save, print, zoom, tooltips);
    }
    
    public ChartPanel(final JFreeChart chart, final int width, final int height, final int minimumDrawWidth, final int minimumDrawHeight, final int maximumDrawWidth, final int maximumDrawHeight, final boolean useBuffer, final boolean properties, final boolean save, final boolean print, final boolean zoom, final boolean tooltips) {
        this.zoomRectangle = null;
        this.zoomPoint = null;
        this.fillZoomRectangle = false;
        this.horizontalZoom = false;
        this.verticalZoom = false;
        this.horizontalAxisTrace = false;
        this.verticalAxisTrace = false;
        this.zoomInFactor = 0.5;
        this.zoomOutFactor = 2.0;
        this.available = new Rectangle2D.Double();
        this.chartArea = new Rectangle2D.Double();
        this.chart = chart;
        this.chartMouseListeners = new ArrayList();
        this.info = new ChartRenderingInfo();
        this.setPreferredSize(new Dimension(width, height));
        this.useBuffer = useBuffer;
        this.refreshBuffer = false;
        this.chart.addChangeListener(this);
        this.minimumDrawWidth = minimumDrawWidth;
        this.minimumDrawHeight = minimumDrawHeight;
        this.maximumDrawWidth = maximumDrawWidth;
        this.maximumDrawHeight = maximumDrawHeight;
        this.zoomTriggerDistance = 10;
        this.popup = null;
        if (properties || save || print || zoom) {
            this.popup = this.createPopupMenu(properties, save, print, zoom);
        }
        this.enableEvents(16L);
        this.enableEvents(32L);
        this.setDisplayToolTips(tooltips);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.enforceFileExtensions = true;
        final ToolTipManager ttm = ToolTipManager.sharedInstance();
        this.ownToolTipInitialDelay = ttm.getInitialDelay();
        this.ownToolTipDismissDelay = ttm.getDismissDelay();
        this.ownToolTipReshowDelay = ttm.getReshowDelay();
    }
    
    public JFreeChart getChart() {
        return this.chart;
    }
    
    public void setChart(final JFreeChart chart) {
        if (this.chart != null) {
            this.chart.removeChangeListener(this);
            this.chart.removeProgressListener(this);
        }
        (this.chart = chart).addChangeListener(this);
        this.chart.addProgressListener(this);
        if (this.useBuffer) {
            this.refreshBuffer = true;
        }
        final Plot plot = chart.getPlot();
        final ValueAxis horizontalAxis = this.getHorizontalValueAxis(plot);
        this.horizontalZoom = (this.horizontalZoom && horizontalAxis != null);
        final ValueAxis verticalAxis = this.getVerticalValueAxis(plot);
        this.verticalZoom = (this.verticalZoom && verticalAxis != null);
        this.repaint();
    }
    
    public int getMinimumDrawWidth() {
        return this.minimumDrawWidth;
    }
    
    public void setMinimumDrawWidth(final int width) {
        this.minimumDrawWidth = width;
    }
    
    public int getMaximumDrawWidth() {
        return this.maximumDrawWidth;
    }
    
    public void setMaximumDrawWidth(final int width) {
        this.maximumDrawWidth = width;
    }
    
    public void setMinimumDrawHeight(final int height) {
        this.minimumDrawHeight = height;
    }
    
    public int getMinimumDrawHeight() {
        return this.minimumDrawHeight;
    }
    
    public int getMaximumDrawHeight() {
        return this.maximumDrawHeight;
    }
    
    public void setMaximumDrawHeight(final int height) {
        this.maximumDrawHeight = height;
    }
    
    public double getScaleX() {
        return this.scaleX;
    }
    
    public double getScaleY() {
        return this.scaleY;
    }
    
    public JPopupMenu getPopupMenu() {
        return this.popup;
    }
    
    public void setPopupMenu(final JPopupMenu popup) {
        this.popup = popup;
    }
    
    public ChartRenderingInfo getChartRenderingInfo() {
        return this.info;
    }
    
    public void setMouseZoomable(final boolean flag) {
        this.setMouseZoomable(flag, true);
    }
    
    public void setMouseZoomable(final boolean flag, final boolean fillRectangle) {
        this.setHorizontalZoom(flag);
        this.setVerticalZoom(flag);
        this.setFillZoomRectangle(fillRectangle);
    }
    
    public void setHorizontalZoom(final boolean flag) {
        final Plot plot = this.chart.getPlot();
        final ValueAxis axis = this.getHorizontalValueAxis(plot);
        this.horizontalZoom = (flag && axis != null);
    }
    
    public void setFillZoomRectangle(final boolean flag) {
        this.fillZoomRectangle = flag;
    }
    
    public void setVerticalZoom(final boolean flag) {
        final Plot plot = this.chart.getPlot();
        final ValueAxis axis = this.getVerticalValueAxis(plot);
        this.verticalZoom = (flag && axis != null);
    }
    
    public int getZoomTriggerDistance() {
        return this.zoomTriggerDistance;
    }
    
    public void setZoomTriggerDistance(final int distance) {
        this.zoomTriggerDistance = distance;
    }
    
    public void setHorizontalAxisTrace(final boolean flag) {
        this.horizontalAxisTrace = flag;
    }
    
    public void setVerticalAxisTrace(final boolean flag) {
        this.verticalAxisTrace = flag;
    }
    
    public boolean isEnforceFileExtensions() {
        return this.enforceFileExtensions;
    }
    
    public void setEnforceFileExtensions(final boolean enforce) {
        this.enforceFileExtensions = enforce;
    }
    
    public void setDisplayToolTips(final boolean flag) {
        if (flag) {
            ToolTipManager.sharedInstance().registerComponent(this);
        }
        else {
            ToolTipManager.sharedInstance().unregisterComponent(this);
        }
    }
    
    public String getToolTipText(final MouseEvent e) {
        String result = null;
        if (this.info != null) {
            final EntityCollection entities = this.info.getEntityCollection();
            if (entities != null) {
                final Insets insets = this.getInsets();
                final ChartEntity entity = entities.getEntity((int)((e.getX() - insets.left) / this.scaleX), (int)((e.getY() - insets.top) / this.scaleY));
                if (entity != null) {
                    result = entity.getToolTipText();
                }
            }
        }
        return result;
    }
    
    public Point translateJava2DToScreen(final Point2D java2DPoint) {
        final Insets insets = this.getInsets();
        final int x = (int)(java2DPoint.getX() * this.scaleX + insets.left);
        final int y = (int)(java2DPoint.getY() * this.scaleY + insets.top);
        return new Point(x, y);
    }
    
    public Point2D translateScreenToJava2D(final Point screenPoint) {
        final Insets insets = this.getInsets();
        final double x = (screenPoint.getX() - insets.left) / this.scaleX;
        final double y = (screenPoint.getY() - insets.top) / this.scaleY;
        return new Point2D.Double(x, y);
    }
    
    public ChartEntity getEntityForPoint(final int viewX, final int viewY) {
        ChartEntity result = null;
        if (this.info != null) {
            final Insets insets = this.getInsets();
            final double x = (viewX - insets.left) / this.scaleX;
            final double y = (viewY - insets.top) / this.scaleY;
            final EntityCollection entities = this.info.getEntityCollection();
            result = ((entities != null) ? entities.getEntity(x, y) : null);
        }
        return result;
    }
    
    public void setRefreshBuffer(final boolean flag) {
        this.refreshBuffer = flag;
    }
    
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D)g.create();
        final Dimension size = this.getSize();
        final Insets insets = this.getInsets();
        this.available.setRect(insets.left, insets.top, size.getWidth() - insets.left - insets.right, size.getHeight() - insets.top - insets.bottom);
        boolean scale = false;
        double drawWidth = this.available.getWidth();
        double drawHeight = this.available.getHeight();
        this.scaleX = 1.0;
        this.scaleY = 1.0;
        if (drawWidth < this.minimumDrawWidth) {
            this.scaleX = drawWidth / this.minimumDrawWidth;
            drawWidth = this.minimumDrawWidth;
            scale = true;
        }
        else if (drawWidth > this.maximumDrawWidth) {
            this.scaleX = drawWidth / this.maximumDrawWidth;
            drawWidth = this.maximumDrawWidth;
            scale = true;
        }
        if (drawHeight < this.minimumDrawHeight) {
            this.scaleY = drawHeight / this.minimumDrawHeight;
            drawHeight = this.minimumDrawHeight;
            scale = true;
        }
        else if (drawHeight > this.maximumDrawHeight) {
            this.scaleY = drawHeight / this.maximumDrawHeight;
            drawHeight = this.maximumDrawHeight;
            scale = true;
        }
        this.chartArea.setRect(0.0, 0.0, drawWidth, drawHeight);
        if (this.useBuffer) {
            if (this.chartBuffer == null || this.chartBufferWidth != this.available.getWidth() || this.chartBufferHeight != this.available.getHeight()) {
                this.chartBufferWidth = (int)this.available.getWidth();
                this.chartBufferHeight = (int)this.available.getHeight();
                this.chartBuffer = this.createImage(this.chartBufferWidth, this.chartBufferHeight);
                this.refreshBuffer = true;
            }
            if (this.refreshBuffer) {
                final Rectangle2D bufferArea = new Rectangle2D.Double(0.0, 0.0, this.chartBufferWidth, this.chartBufferHeight);
                final Graphics2D bufferG2 = (Graphics2D)this.chartBuffer.getGraphics();
                if (scale) {
                    final AffineTransform saved = bufferG2.getTransform();
                    final AffineTransform st = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
                    bufferG2.transform(st);
                    this.chart.draw(bufferG2, this.chartArea, this.anchor, this.info);
                    bufferG2.setTransform(saved);
                }
                else {
                    this.chart.draw(bufferG2, bufferArea, this.anchor, this.info);
                }
                this.refreshBuffer = false;
            }
            g2.drawImage(this.chartBuffer, insets.left, insets.right, this);
        }
        else {
            final AffineTransform saved2 = g2.getTransform();
            g2.translate(insets.left, insets.top);
            if (scale) {
                final AffineTransform st2 = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
                g2.transform(st2);
            }
            this.chart.draw(g2, this.chartArea, this.anchor, this.info);
            g2.setTransform(saved2);
        }
        this.anchor = null;
        this.verticalTraceLine = null;
        this.horizontalTraceLine = null;
    }
    
    public void chartChanged(final ChartChangeEvent event) {
        this.refreshBuffer = true;
        this.repaint();
    }
    
    public void chartProgress(final ChartProgressEvent event) {
    }
    
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        if (command.equals("PROPERTIES")) {
            this.attemptEditChartProperties();
        }
        else if (command.equals("SAVE")) {
            try {
                this.doSaveAs();
            }
            catch (IOException e) {
                System.err.println("ChartPanel.doSaveAs: i/o exception = " + e.getMessage());
            }
        }
        else if (command.equals("PRINT")) {
            this.createChartPrintJob();
        }
        else if (command.equals("ZOOM_IN_BOTH")) {
            this.zoomInBoth(this.zoomPoint.getX(), this.zoomPoint.getY());
        }
        else if (command.equals("ZOOM_IN_HORIZONTAL")) {
            this.zoomInHorizontal(this.zoomPoint.getX());
        }
        else if (command.equals("ZOOM_IN_VERTICAL")) {
            this.zoomInVertical(this.zoomPoint.getY());
        }
        else if (command.equals("ZOOM_OUT_BOTH")) {
            this.zoomOutBoth(this.zoomPoint.getX(), this.zoomPoint.getY());
        }
        else if (command.equals("ZOOM_HORIZONTAL_BOTH")) {
            this.zoomOutHorizontal(this.zoomPoint.getX());
        }
        else if (command.equals("ZOOM_VERTICAL_BOTH")) {
            this.zoomOutVertical(this.zoomPoint.getY());
        }
        else if (command.equals("AUTO_RANGE_BOTH")) {
            this.autoRangeBoth();
        }
        else if (command.equals("AUTO_RANGE_HORIZONTAL")) {
            this.autoRangeHorizontal();
        }
        else if (command.equals("AUTO_RANGE_VERTICAL")) {
            this.autoRangeVertical();
        }
    }
    
    public void mouseEntered(final MouseEvent e) {
        if (!this.ownToolTipDelaysActive) {
            final ToolTipManager ttm = ToolTipManager.sharedInstance();
            this.originalToolTipInitialDelay = ttm.getInitialDelay();
            ttm.setInitialDelay(this.ownToolTipInitialDelay);
            this.originalToolTipReshowDelay = ttm.getReshowDelay();
            ttm.setReshowDelay(this.ownToolTipReshowDelay);
            this.originalToolTipDismissDelay = ttm.getDismissDelay();
            ttm.setDismissDelay(this.ownToolTipDismissDelay);
            this.ownToolTipDelaysActive = true;
        }
    }
    
    public void mouseExited(final MouseEvent e) {
        if (this.ownToolTipDelaysActive) {
            final ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(this.originalToolTipInitialDelay);
            ttm.setReshowDelay(this.originalToolTipReshowDelay);
            ttm.setDismissDelay(this.originalToolTipDismissDelay);
            this.ownToolTipDelaysActive = false;
        }
    }
    
    public void mousePressed(final MouseEvent e) {
        if (this.zoomRectangle == null) {
            this.zoomPoint = RefineryUtilities.getPointInRectangle(e.getX(), e.getY(), this.getScaledDataArea());
            ChartPanel.LOGGER.debug("In mousePressed()");
            ChartPanel.LOGGER.debug("getScaledDataArea() = " + this.getScaledDataArea());
            ChartPanel.LOGGER.debug("this.zoomPoint = " + this.zoomPoint);
            if (e.isPopupTrigger() && this.popup != null) {
                this.displayPopupMenu(e.getX(), e.getY());
            }
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
        ChartPanel.LOGGER.debug("In mouseReleased()");
        ChartPanel.LOGGER.debug("this.zoomRectangle = " + this.zoomRectangle);
        if (this.zoomRectangle != null) {
            final boolean zoomTrigger1 = this.horizontalZoom && Math.abs(e.getX() - this.zoomPoint.getX()) >= this.zoomTriggerDistance;
            final boolean zoomTrigger2 = this.verticalZoom && Math.abs(e.getY() - this.zoomPoint.getY()) >= this.zoomTriggerDistance;
            if (zoomTrigger1 || zoomTrigger2) {
                if ((this.horizontalZoom && e.getX() < this.zoomPoint.getX()) || (this.verticalZoom && e.getY() < this.zoomPoint.getY())) {
                    this.autoRangeBoth();
                }
                else {
                    final Rectangle2D scaledDataArea = this.getScaledDataArea();
                    double x;
                    double y;
                    double w;
                    double h;
                    if (!this.verticalZoom) {
                        x = this.zoomPoint.getX();
                        y = scaledDataArea.getMinY();
                        w = Math.min(this.zoomRectangle.getWidth(), scaledDataArea.getMaxX() - this.zoomPoint.getX());
                        h = scaledDataArea.getHeight();
                    }
                    else if (!this.horizontalZoom) {
                        x = scaledDataArea.getMinX();
                        y = this.zoomPoint.getY();
                        w = scaledDataArea.getWidth();
                        h = Math.min(this.zoomRectangle.getHeight(), scaledDataArea.getMaxY() - this.zoomPoint.getY());
                    }
                    else {
                        x = this.zoomPoint.getX();
                        y = this.zoomPoint.getY();
                        w = Math.min(this.zoomRectangle.getWidth(), scaledDataArea.getMaxX() - this.zoomPoint.getX());
                        h = Math.min(this.zoomRectangle.getHeight(), scaledDataArea.getMaxY() - this.zoomPoint.getY());
                    }
                    final Rectangle2D zoomArea = new Rectangle2D.Double(x, y, w, h);
                    Log.debug("zoomArea = " + zoomArea);
                    this.zoom(zoomArea);
                }
                this.zoomPoint = null;
                this.zoomRectangle = null;
            }
            else {
                final Graphics2D g2 = (Graphics2D)this.getGraphics();
                g2.setXORMode(Color.gray);
                if (this.fillZoomRectangle) {
                    g2.fill(this.zoomRectangle);
                }
                else {
                    g2.draw(this.zoomRectangle);
                }
                g2.dispose();
                this.zoomRectangle = null;
            }
        }
        else if (e.isPopupTrigger() && this.popup != null) {
            this.displayPopupMenu(e.getX(), e.getY());
        }
    }
    
    public void mouseClicked(final MouseEvent event) {
        final Insets insets = this.getInsets();
        final int x = (int)((event.getX() - insets.left) / this.scaleX);
        final int y = (int)((event.getY() - insets.top) / this.scaleY);
        this.anchor = new Point2D.Double(x, y);
        this.chart.setTitle(this.chart.getTitle());
        if (this.chartMouseListeners.isEmpty()) {
            return;
        }
        ChartEntity entity = null;
        if (this.info != null) {
            final EntityCollection entities = this.info.getEntityCollection();
            if (entities != null) {
                entity = entities.getEntity(x, y);
            }
        }
        final ChartMouseEvent chartEvent = new ChartMouseEvent(this.getChart(), event, entity);
        for (final ChartMouseListener listener : this.chartMouseListeners) {
            listener.chartMouseClicked(chartEvent);
        }
    }
    
    public void mouseMoved(final MouseEvent e) {
        if (this.horizontalAxisTrace) {
            this.drawHorizontalAxisTrace(e.getX());
        }
        if (this.verticalAxisTrace) {
            this.drawVerticalAxisTrace(e.getY());
        }
        if (this.chartMouseListeners.isEmpty()) {
            return;
        }
        final Insets insets = this.getInsets();
        final int x = (int)((e.getX() - insets.left) / this.scaleX);
        final int y = (int)((e.getY() - insets.top) / this.scaleY);
        ChartEntity entity = null;
        if (this.info != null) {
            final EntityCollection entities = this.info.getEntityCollection();
            if (entities != null) {
                entity = entities.getEntity(x, y);
            }
        }
        final ChartMouseEvent event = new ChartMouseEvent(this.getChart(), e, entity);
        for (final ChartMouseListener listener : this.chartMouseListeners) {
            listener.chartMouseMoved(event);
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
        if (this.popup != null && this.popup.isShowing()) {
            return;
        }
        final Graphics2D g2 = (Graphics2D)this.getGraphics();
        g2.setXORMode(Color.gray);
        if (this.zoomRectangle != null) {
            if (this.fillZoomRectangle) {
                g2.fill(this.zoomRectangle);
            }
            else {
                g2.draw(this.zoomRectangle);
            }
        }
        final Rectangle2D scaledDataArea = this.getScaledDataArea();
        if (this.horizontalZoom && this.verticalZoom) {
            final double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
            final double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
            this.zoomRectangle = new Rectangle2D.Double(this.zoomPoint.getX(), this.zoomPoint.getY(), xmax - this.zoomPoint.getX(), ymax - this.zoomPoint.getY());
        }
        else if (this.horizontalZoom) {
            final double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
            this.zoomRectangle = new Rectangle2D.Double(this.zoomPoint.getX(), scaledDataArea.getMinY(), xmax - this.zoomPoint.getX(), scaledDataArea.getHeight());
        }
        else if (this.verticalZoom) {
            final double ymax2 = Math.min(e.getY(), scaledDataArea.getMaxY());
            this.zoomRectangle = new Rectangle2D.Double(scaledDataArea.getMinX(), this.zoomPoint.getY(), scaledDataArea.getWidth(), ymax2 - this.zoomPoint.getY());
        }
        if (this.zoomRectangle != null) {
            if (this.fillZoomRectangle) {
                g2.fill(this.zoomRectangle);
            }
            else {
                g2.draw(this.zoomRectangle);
            }
        }
        g2.dispose();
    }
    
    public void zoomInBoth(final double x, final double y) {
        this.zoomInHorizontal(x);
        this.zoomInVertical(y);
    }
    
    private ValueAxis getHorizontalValueAxis(final Plot plot) {
        if (plot == null) {
            return null;
        }
        ValueAxis axis = null;
        if (plot instanceof CategoryPlot) {
            final CategoryPlot cp = (CategoryPlot)plot;
            if (cp.getOrientation() == PlotOrientation.HORIZONTAL) {
                axis = cp.getRangeAxis();
            }
        }
        if (plot instanceof XYPlot) {
            final XYPlot xyp = (XYPlot)plot;
            if (xyp.getOrientation() == PlotOrientation.HORIZONTAL) {
                axis = xyp.getRangeAxis();
            }
            else if (xyp.getOrientation() == PlotOrientation.VERTICAL) {
                axis = xyp.getDomainAxis();
            }
        }
        if (plot instanceof FastScatterPlot) {
            final FastScatterPlot fsp = (FastScatterPlot)plot;
            axis = fsp.getDomainAxis();
        }
        return axis;
    }
    
    private ValueAxis getVerticalValueAxis(final Plot plot) {
        if (plot == null) {
            return null;
        }
        ValueAxis axis = null;
        if (plot instanceof CategoryPlot) {
            final CategoryPlot cp = (CategoryPlot)plot;
            if (cp.getOrientation() == PlotOrientation.VERTICAL) {
                axis = cp.getRangeAxis();
            }
        }
        if (plot instanceof XYPlot) {
            final XYPlot xyp = (XYPlot)plot;
            if (xyp.getOrientation() == PlotOrientation.HORIZONTAL) {
                axis = xyp.getDomainAxis();
            }
            else if (xyp.getOrientation() == PlotOrientation.VERTICAL) {
                axis = xyp.getRangeAxis();
            }
        }
        if (plot instanceof FastScatterPlot) {
            final FastScatterPlot fsp = (FastScatterPlot)plot;
            axis = fsp.getRangeAxis();
        }
        return axis;
    }
    
    public void zoomInHorizontal(final double x) {
        final Plot p = this.chart.getPlot();
        if (p instanceof ValueAxisPlot) {
            final ValueAxisPlot plot = (ValueAxisPlot)p;
            plot.zoomHorizontalAxes(this.zoomInFactor);
        }
    }
    
    public void zoomInVertical(final double y) {
        final Plot p = this.chart.getPlot();
        if (p instanceof ValueAxisPlot) {
            final ValueAxisPlot plot = (ValueAxisPlot)p;
            plot.zoomVerticalAxes(this.zoomInFactor);
        }
    }
    
    public void zoomOutBoth(final double x, final double y) {
        this.zoomOutHorizontal(x);
        this.zoomOutVertical(y);
    }
    
    public void zoomOutHorizontal(final double x) {
        final Plot p = this.chart.getPlot();
        if (p instanceof ValueAxisPlot) {
            final ValueAxisPlot plot = (ValueAxisPlot)p;
            plot.zoomHorizontalAxes(this.zoomOutFactor);
        }
    }
    
    public void zoomOutVertical(final double y) {
        final Plot p = this.chart.getPlot();
        if (p instanceof ValueAxisPlot) {
            final ValueAxisPlot plot = (ValueAxisPlot)p;
            plot.zoomVerticalAxes(this.zoomOutFactor);
        }
    }
    
    public void zoom(final Rectangle2D selection) {
        double hLower = 0.0;
        double hUpper = 0.0;
        double vLower = 0.0;
        double vUpper = 0.0;
        if (selection.getHeight() > 0.0 && selection.getWidth() > 0.0) {
            final Rectangle2D scaledDataArea = this.getScaledDataArea();
            hLower = (selection.getMinX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
            hUpper = (selection.getMaxX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
            vLower = (scaledDataArea.getMaxY() - selection.getMaxY()) / scaledDataArea.getHeight();
            vUpper = (scaledDataArea.getMaxY() - selection.getMinY()) / scaledDataArea.getHeight();
            ChartPanel.LOGGER.debug("hLower = " + hLower);
            ChartPanel.LOGGER.debug("hUpper = " + hUpper);
            ChartPanel.LOGGER.debug("vLower = " + vLower);
            ChartPanel.LOGGER.debug("vUpper = " + vUpper);
            final Plot p = this.chart.getPlot();
            if (p instanceof ValueAxisPlot) {
                final ValueAxisPlot plot = (ValueAxisPlot)p;
                plot.zoomHorizontalAxes(hLower, hUpper);
                plot.zoomVerticalAxes(vLower, vUpper);
            }
        }
    }
    
    public void autoRangeBoth() {
        this.autoRangeHorizontal();
        this.autoRangeVertical();
    }
    
    public void autoRangeHorizontal() {
        final Plot p = this.chart.getPlot();
        if (p instanceof ValueAxisPlot) {
            final ValueAxisPlot plot = (ValueAxisPlot)p;
            plot.zoomHorizontalAxes(0.0);
        }
    }
    
    public void autoRangeVertical() {
        final Plot p = this.chart.getPlot();
        if (p instanceof ValueAxisPlot) {
            final ValueAxisPlot plot = (ValueAxisPlot)p;
            plot.zoomVerticalAxes(0.0);
        }
    }
    
    public Rectangle2D getScaledDataArea() {
        final Rectangle2D dataArea = this.info.getPlotInfo().getDataArea();
        final Insets insets = this.getInsets();
        final double x = dataArea.getX() * this.scaleX + insets.left;
        final double y = dataArea.getY() * this.scaleY + insets.top;
        final double w = dataArea.getWidth() * this.scaleX;
        final double h = dataArea.getHeight() * this.scaleY;
        return new Rectangle2D.Double(x, y, w, h);
    }
    
    public int getInitialDelay() {
        return this.ownToolTipInitialDelay;
    }
    
    public int getReshowDelay() {
        return this.ownToolTipReshowDelay;
    }
    
    public int getDismissDelay() {
        return this.ownToolTipDismissDelay;
    }
    
    public void setInitialDelay(final int delay) {
        this.ownToolTipInitialDelay = delay;
    }
    
    public void setReshowDelay(final int delay) {
        this.ownToolTipReshowDelay = delay;
    }
    
    public void setDismissDelay(final int delay) {
        this.ownToolTipDismissDelay = delay;
    }
    
    public double getZoomInFactor() {
        return this.zoomInFactor;
    }
    
    public void setZoomInFactor(final double factor) {
        this.zoomInFactor = factor;
    }
    
    public double getZoomOutFactor() {
        return this.zoomOutFactor;
    }
    
    public void setZoomOutFactor(final double factor) {
        this.zoomOutFactor = factor;
    }
    
    private void drawHorizontalAxisTrace(final int x) {
        final Graphics2D g2 = (Graphics2D)this.getGraphics();
        final Rectangle2D dataArea = this.getScaledDataArea();
        g2.setXORMode(Color.orange);
        if ((int)dataArea.getMinX() < x && x < (int)dataArea.getMaxX()) {
            if (this.verticalTraceLine != null) {
                g2.draw(this.verticalTraceLine);
                this.verticalTraceLine.setLine(x, (int)dataArea.getMinY(), x, (int)dataArea.getMaxY());
            }
            else {
                this.verticalTraceLine = new Line2D.Float(x, (int)dataArea.getMinY(), x, (int)dataArea.getMaxY());
            }
            g2.draw(this.verticalTraceLine);
        }
    }
    
    private void drawVerticalAxisTrace(final int y) {
        final Graphics2D g2 = (Graphics2D)this.getGraphics();
        final Rectangle2D dataArea = this.getScaledDataArea();
        g2.setXORMode(Color.orange);
        if ((int)dataArea.getMinY() < y && y < (int)dataArea.getMaxY()) {
            if (this.horizontalTraceLine != null) {
                g2.draw(this.horizontalTraceLine);
                this.horizontalTraceLine.setLine((int)dataArea.getMinX(), y, (int)dataArea.getMaxX(), y);
            }
            else {
                this.horizontalTraceLine = new Line2D.Float((int)dataArea.getMinX(), y, (int)dataArea.getMaxX(), y);
            }
            g2.draw(this.horizontalTraceLine);
        }
    }
    
    private void attemptEditChartProperties() {
        final ChartPropertyEditPanel panel = new ChartPropertyEditPanel(this.chart);
        final int result = JOptionPane.showConfirmDialog(this, panel, ChartPanel.localizationResources.getString("Chart_Properties"), 2, -1);
        if (result == 0) {
            panel.updateChartProperties(this.chart);
        }
    }
    
    public void doSaveAs() throws IOException {
        final JFileChooser fileChooser = new JFileChooser();
        final ExtensionFileFilter filter = new ExtensionFileFilter(ChartPanel.localizationResources.getString("PNG_Image_Files"), ".png");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.addChoosableFileFilter(new ExtensionFileFilter("All files", ""));
        final int option = fileChooser.showSaveDialog(this);
        if (option == 0) {
            String filename = fileChooser.getSelectedFile().getPath();
            if (this.isEnforceFileExtensions() && !filename.endsWith(".png")) {
                filename += ".png";
            }
            ChartUtilities.saveChartAsPNG(new File(filename), this.chart, this.getWidth(), this.getHeight());
        }
    }
    
    public void createChartPrintJob() {
        final PrinterJob job = PrinterJob.getPrinterJob();
        final PageFormat pf = job.defaultPage();
        final PageFormat pf2 = job.pageDialog(pf);
        if (pf2 != pf) {
            job.setPrintable(this, pf2);
            if (job.printDialog()) {
                try {
                    job.print();
                }
                catch (PrinterException e) {
                    JOptionPane.showMessageDialog(this, e);
                }
            }
        }
    }
    
    public int print(final Graphics g, final PageFormat pf, final int pageIndex) {
        if (pageIndex != 0) {
            return 1;
        }
        final Graphics2D g2 = (Graphics2D)g;
        final double x = pf.getImageableX();
        final double y = pf.getImageableY();
        final double w = pf.getImageableWidth();
        final double h = pf.getImageableHeight();
        this.chart.draw(g2, new Rectangle2D.Double(x, y, w, h), this.anchor, null);
        return 0;
    }
    
    public void addChartMouseListener(final ChartMouseListener listener) {
        this.chartMouseListeners.add(listener);
    }
    
    public void removeChartMouseListener(final ChartMouseListener listener) {
        this.chartMouseListeners.remove(listener);
    }
    
    protected JPopupMenu createPopupMenu(final boolean properties, final boolean save, final boolean print, final boolean zoom) {
        final JPopupMenu result = new JPopupMenu("Chart:");
        boolean separator = false;
        if (properties) {
            final JMenuItem propertiesItem = new JMenuItem(ChartPanel.localizationResources.getString("Properties..."));
            propertiesItem.setActionCommand("PROPERTIES");
            propertiesItem.addActionListener(this);
            result.add(propertiesItem);
            separator = true;
        }
        if (save) {
            if (separator) {
                result.addSeparator();
                separator = false;
            }
            final JMenuItem saveItem = new JMenuItem(ChartPanel.localizationResources.getString("Save_as..."));
            saveItem.setActionCommand("SAVE");
            saveItem.addActionListener(this);
            result.add(saveItem);
            separator = true;
        }
        if (print) {
            if (separator) {
                result.addSeparator();
                separator = false;
            }
            final JMenuItem printItem = new JMenuItem(ChartPanel.localizationResources.getString("Print..."));
            printItem.setActionCommand("PRINT");
            printItem.addActionListener(this);
            result.add(printItem);
            separator = true;
        }
        if (zoom) {
            if (separator) {
                result.addSeparator();
                separator = false;
            }
            final JMenu zoomInMenu = new JMenu(ChartPanel.localizationResources.getString("Zoom_In"));
            (this.zoomInBothMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("All_Axes"))).setActionCommand("ZOOM_IN_BOTH");
            this.zoomInBothMenuItem.addActionListener(this);
            zoomInMenu.add(this.zoomInBothMenuItem);
            zoomInMenu.addSeparator();
            (this.zoomInHorizontalMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("Horizontal_Axis"))).setActionCommand("ZOOM_IN_HORIZONTAL");
            this.zoomInHorizontalMenuItem.addActionListener(this);
            zoomInMenu.add(this.zoomInHorizontalMenuItem);
            (this.zoomInVerticalMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("Vertical_Axis"))).setActionCommand("ZOOM_IN_VERTICAL");
            this.zoomInVerticalMenuItem.addActionListener(this);
            zoomInMenu.add(this.zoomInVerticalMenuItem);
            result.add(zoomInMenu);
            final JMenu zoomOutMenu = new JMenu(ChartPanel.localizationResources.getString("Zoom_Out"));
            (this.zoomOutBothMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("All_Axes"))).setActionCommand("ZOOM_OUT_BOTH");
            this.zoomOutBothMenuItem.addActionListener(this);
            zoomOutMenu.add(this.zoomOutBothMenuItem);
            zoomOutMenu.addSeparator();
            (this.zoomOutHorizontalMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("Horizontal_Axis"))).setActionCommand("ZOOM_HORIZONTAL_BOTH");
            this.zoomOutHorizontalMenuItem.addActionListener(this);
            zoomOutMenu.add(this.zoomOutHorizontalMenuItem);
            (this.zoomOutVerticalMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("Vertical_Axis"))).setActionCommand("ZOOM_VERTICAL_BOTH");
            this.zoomOutVerticalMenuItem.addActionListener(this);
            zoomOutMenu.add(this.zoomOutVerticalMenuItem);
            result.add(zoomOutMenu);
            final JMenu autoRangeMenu = new JMenu(ChartPanel.localizationResources.getString("Auto_Range"));
            (this.autoRangeBothMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("All_Axes"))).setActionCommand("AUTO_RANGE_BOTH");
            this.autoRangeBothMenuItem.addActionListener(this);
            autoRangeMenu.add(this.autoRangeBothMenuItem);
            autoRangeMenu.addSeparator();
            (this.autoRangeHorizontalMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("Horizontal_Axis"))).setActionCommand("AUTO_RANGE_HORIZONTAL");
            this.autoRangeHorizontalMenuItem.addActionListener(this);
            autoRangeMenu.add(this.autoRangeHorizontalMenuItem);
            (this.autoRangeVerticalMenuItem = new JMenuItem(ChartPanel.localizationResources.getString("Vertical_Axis"))).setActionCommand("AUTO_RANGE_VERTICAL");
            this.autoRangeVerticalMenuItem.addActionListener(this);
            autoRangeMenu.add(this.autoRangeVerticalMenuItem);
            result.addSeparator();
            result.add(autoRangeMenu);
        }
        return result;
    }
    
    protected void displayPopupMenu(final int x, final int y) {
        if (this.popup != null) {
            final Plot plot = this.chart.getPlot();
            final ValueAxis horizontalAxis = this.getHorizontalValueAxis(plot);
            final boolean isHorizontal = horizontalAxis != null;
            final ValueAxis verticalAxis = this.getVerticalValueAxis(plot);
            final boolean isVertical = verticalAxis != null;
            if (this.zoomInHorizontalMenuItem != null) {
                this.zoomInHorizontalMenuItem.setEnabled(isHorizontal);
            }
            if (this.zoomOutHorizontalMenuItem != null) {
                this.zoomOutHorizontalMenuItem.setEnabled(isHorizontal);
            }
            if (this.autoRangeHorizontalMenuItem != null) {
                this.autoRangeHorizontalMenuItem.setEnabled(isHorizontal);
            }
            if (this.zoomInVerticalMenuItem != null) {
                this.zoomInVerticalMenuItem.setEnabled(isVertical);
            }
            if (this.zoomOutVerticalMenuItem != null) {
                this.zoomOutVerticalMenuItem.setEnabled(isVertical);
            }
            if (this.autoRangeVerticalMenuItem != null) {
                this.autoRangeVerticalMenuItem.setEnabled(isVertical);
            }
            if (this.zoomInBothMenuItem != null) {
                this.zoomInBothMenuItem.setEnabled(isHorizontal & isVertical);
            }
            if (this.zoomOutBothMenuItem != null) {
                this.zoomOutBothMenuItem.setEnabled(isHorizontal & isVertical);
            }
            if (this.autoRangeBothMenuItem != null) {
                this.autoRangeBothMenuItem.setEnabled(isHorizontal & isVertical);
            }
            this.popup.show(this, x, y);
        }
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.available = new Rectangle2D.Double();
        this.chartArea = new Rectangle2D.Double();
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        ChartPanel.localizationResources = ResourceBundle.getBundle("org.jfree.chart.LocalizationBundle");
        LOGGER = Log.createContext((ChartPanel.class$org$jfree$chart$ChartPanel == null) ? (ChartPanel.class$org$jfree$chart$ChartPanel = class$("org.jfree.chart.ChartPanel")) : ChartPanel.class$org$jfree$chart$ChartPanel);
    }
}
