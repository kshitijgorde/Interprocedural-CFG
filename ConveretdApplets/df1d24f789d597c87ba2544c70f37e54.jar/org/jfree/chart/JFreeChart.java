// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import org.jfree.util.Log;
import java.awt.SystemColor;
import java.io.ObjectInputStream;
import java.io.IOException;
import org.jfree.io.SerialUtilities;
import java.io.ObjectOutputStream;
import org.jfree.util.ObjectUtils;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.LegendChangeEvent;
import org.jfree.chart.event.TitleChangeEvent;
import org.jfree.chart.event.ChartProgressListener;
import org.jfree.chart.event.ChartChangeListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.util.Iterator;
import org.jfree.chart.plot.PlotState;
import org.jfree.ui.Align;
import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.util.Map;
import java.awt.Shape;
import org.jfree.chart.event.ChartProgressEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.title.Title;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.BasicStroke;
import org.jfree.util.LogContext;
import javax.swing.event.EventListenerList;
import org.jfree.chart.plot.Plot;
import java.util.List;
import org.jfree.chart.title.TextTitle;
import java.awt.Stroke;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Font;
import org.jfree.ui.about.ProjectInfo;
import java.io.Serializable;
import org.jfree.chart.event.PlotChangeListener;
import org.jfree.chart.event.LegendChangeListener;
import org.jfree.chart.event.TitleChangeListener;
import org.jfree.ui.Drawable;

public class JFreeChart implements Drawable, TitleChangeListener, LegendChangeListener, PlotChangeListener, Serializable, Cloneable
{
    public static final ProjectInfo INFO;
    public static final Font DEFAULT_TITLE_FONT;
    public static final Paint DEFAULT_BACKGROUND_PAINT;
    public static final Image DEFAULT_BACKGROUND_IMAGE;
    public static final int DEFAULT_BACKGROUND_IMAGE_ALIGNMENT = 15;
    public static float DEFAULT_BACKGROUND_IMAGE_ALPHA;
    private transient RenderingHints renderingHints;
    private boolean borderVisible;
    private transient Stroke borderStroke;
    private transient Paint borderPaint;
    private TextTitle title;
    private List subtitles;
    private Legend legend;
    private Plot plot;
    private transient Paint backgroundPaint;
    private transient Image backgroundImage;
    private int backgroundImageAlignment;
    private float backgroundImageAlpha;
    private transient EventListenerList changeListeners;
    private transient EventListenerList progressListeners;
    private boolean notify;
    private static final LogContext LOGGER;
    static /* synthetic */ Class class$org$jfree$chart$JFreeChart;
    static /* synthetic */ Class class$org$jfree$chart$event$ChartChangeListener;
    static /* synthetic */ Class class$org$jfree$chart$event$ChartProgressListener;
    
    public JFreeChart(final Plot plot) {
        this(null, null, plot, false);
    }
    
    public JFreeChart(final String title, final Plot plot) {
        this(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
    }
    
    public JFreeChart(final String title, Font titleFont, final Plot plot, final boolean createLegend) {
        this.backgroundImageAlignment = 15;
        this.backgroundImageAlpha = 0.5f;
        if (plot == null) {
            throw new NullPointerException("JFreeChart(..): Plot is null");
        }
        this.progressListeners = new EventListenerList();
        this.changeListeners = new EventListenerList();
        this.notify = true;
        this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.borderVisible = false;
        this.borderStroke = new BasicStroke(1.0f);
        this.borderPaint = Color.black;
        (this.plot = plot).addChangeListener(this);
        this.subtitles = new ArrayList();
        if (createLegend) {
            this.setLegend(Legend.createInstance(this));
        }
        if (title != null) {
            if (titleFont == null) {
                titleFont = JFreeChart.DEFAULT_TITLE_FONT;
            }
            (this.title = new TextTitle(title, titleFont)).addChangeListener(this);
        }
        this.backgroundPaint = JFreeChart.DEFAULT_BACKGROUND_PAINT;
        this.backgroundImage = JFreeChart.DEFAULT_BACKGROUND_IMAGE;
        this.backgroundImageAlignment = 15;
        this.backgroundImageAlpha = JFreeChart.DEFAULT_BACKGROUND_IMAGE_ALPHA;
    }
    
    public RenderingHints getRenderingHints() {
        return this.renderingHints;
    }
    
    public void setRenderingHints(final RenderingHints renderingHints) {
        if (renderingHints == null) {
            throw new NullPointerException("RenderingHints given are null");
        }
        this.renderingHints = renderingHints;
        this.fireChartChanged();
    }
    
    public boolean isBorderVisible() {
        return this.borderVisible;
    }
    
    public void setBorderVisible(final boolean visible) {
        this.borderVisible = visible;
        this.fireChartChanged();
    }
    
    public Stroke getBorderStroke() {
        return this.borderStroke;
    }
    
    public void setBorderStroke(final Stroke stroke) {
        this.borderStroke = stroke;
        this.fireChartChanged();
    }
    
    public Paint getBorderPaint() {
        return this.borderPaint;
    }
    
    public void setBorderPaint(final Paint paint) {
        this.borderPaint = paint;
        this.fireChartChanged();
    }
    
    public TextTitle getTitle() {
        return this.title;
    }
    
    public void setTitle(final TextTitle title) {
        this.title = title;
        this.fireChartChanged();
    }
    
    public void setTitle(final String title) {
        if (title != null) {
            if (this.title == null) {
                this.setTitle(new TextTitle(title, JFreeChart.DEFAULT_TITLE_FONT));
            }
            else {
                this.title.setText(title);
            }
        }
        else {
            this.setTitle((TextTitle)null);
        }
    }
    
    public List getSubtitles() {
        return this.subtitles;
    }
    
    public void setSubtitles(final List subtitles) {
        if (subtitles == null) {
            throw new NullPointerException("JFreeChart.setSubtitles(..): argument is null.");
        }
        this.subtitles = subtitles;
        this.fireChartChanged();
    }
    
    public int getSubtitleCount() {
        return this.subtitles.size();
    }
    
    public Title getSubtitle(final int index) {
        if (index < 0 || index == this.getSubtitleCount()) {
            throw new IllegalArgumentException("JFreeChart.getSubtitle(...): index out of range.");
        }
        return this.subtitles.get(index);
    }
    
    public void addSubtitle(final Title subtitle) {
        if (subtitle != null) {
            this.subtitles.add(subtitle);
            subtitle.addChangeListener(this);
            this.fireChartChanged();
        }
    }
    
    public Legend getLegend() {
        return this.legend;
    }
    
    public void setLegend(final Legend legend) {
        final Legend existing = this.legend;
        if (existing != null) {
            existing.removeChangeListener(this);
            existing.registerChart(null);
        }
        if ((this.legend = legend) != null) {
            legend.registerChart(this);
            legend.addChangeListener(this);
        }
        this.fireChartChanged();
    }
    
    public Plot getPlot() {
        return this.plot;
    }
    
    public CategoryPlot getCategoryPlot() {
        return (CategoryPlot)this.plot;
    }
    
    public XYPlot getXYPlot() {
        return (XYPlot)this.plot;
    }
    
    public boolean getAntiAlias() {
        final Object o = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
        return o != null && o.equals(RenderingHints.VALUE_ANTIALIAS_ON);
    }
    
    public void setAntiAlias(final boolean flag) {
        Object o = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
        if (o == null) {
            o = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
        }
        if ((!flag && RenderingHints.VALUE_ANTIALIAS_OFF.equals(o)) || (flag && RenderingHints.VALUE_ANTIALIAS_ON.equals(o))) {
            return;
        }
        if (flag) {
            this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        else {
            this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        this.fireChartChanged();
    }
    
    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }
    
    public void setBackgroundPaint(final Paint paint) {
        if (this.backgroundPaint != null) {
            if (!this.backgroundPaint.equals(paint)) {
                this.backgroundPaint = paint;
                this.fireChartChanged();
            }
        }
        else if (paint != null) {
            this.backgroundPaint = paint;
            this.fireChartChanged();
        }
    }
    
    public Image getBackgroundImage() {
        return this.backgroundImage;
    }
    
    public void setBackgroundImage(final Image image) {
        if (this.backgroundImage != null) {
            if (!this.backgroundImage.equals(image)) {
                this.backgroundImage = image;
                this.fireChartChanged();
            }
        }
        else if (image != null) {
            this.backgroundImage = image;
            this.fireChartChanged();
        }
    }
    
    public int getBackgroundImageAlignment() {
        return this.backgroundImageAlignment;
    }
    
    public void setBackgroundImageAlignment(final int alignment) {
        if (this.backgroundImageAlignment != alignment) {
            this.backgroundImageAlignment = alignment;
            this.fireChartChanged();
        }
    }
    
    public float getBackgroundImageAlpha() {
        return this.backgroundImageAlpha;
    }
    
    public void setBackgroundImageAlpha(final float alpha) {
        if (this.backgroundImageAlpha != alpha) {
            this.backgroundImageAlpha = alpha;
            this.fireChartChanged();
        }
    }
    
    public boolean isNotify() {
        return this.notify;
    }
    
    public void setNotify(final boolean notify) {
        this.notify = notify;
        if (notify) {
            this.notifyListeners(new ChartChangeEvent(this));
        }
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area) {
        this.draw(g2, area, null, null);
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D area, final ChartRenderingInfo info) {
        this.draw(g2, area, null, info);
    }
    
    public void draw(final Graphics2D g2, final Rectangle2D chartArea, final Point2D anchor, final ChartRenderingInfo info) {
        if (JFreeChart.LOGGER.isDebugEnabled()) {
            JFreeChart.LOGGER.debug("Entering draw() method, chartArea = " + chartArea.toString());
        }
        this.notifyListeners(new ChartProgressEvent(this, this, 1, 0));
        if (info != null) {
            info.clear();
            info.setChartArea(chartArea);
        }
        final Shape savedClip = g2.getClip();
        g2.clip(chartArea);
        g2.addRenderingHints(this.renderingHints);
        if (this.backgroundPaint != null) {
            g2.setPaint(this.backgroundPaint);
            g2.fill(chartArea);
        }
        if (this.backgroundImage != null) {
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, this.backgroundImageAlpha));
            final Rectangle2D dest = new Rectangle2D.Double(0.0, 0.0, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
            Align.align(dest, chartArea, this.backgroundImageAlignment);
            if (JFreeChart.LOGGER.isDebugEnabled()) {
                JFreeChart.LOGGER.debug("Drawing background image into " + dest.toString());
            }
            g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth(), (int)dest.getHeight(), null);
            g2.setComposite(originalComposite);
        }
        if (this.isBorderVisible()) {
            final Paint paint = this.getBorderPaint();
            final Stroke stroke = this.getBorderStroke();
            if (paint != null && stroke != null) {
                final Rectangle2D borderArea = new Rectangle2D.Double(chartArea.getX(), chartArea.getY(), chartArea.getWidth() - 1.0, chartArea.getHeight() - 1.0);
                g2.setPaint(paint);
                g2.setStroke(stroke);
                g2.draw(borderArea);
            }
        }
        final Rectangle2D nonTitleArea = new Rectangle2D.Double();
        nonTitleArea.setRect(chartArea);
        if (this.title != null) {
            this.drawTitle(this.title, g2, nonTitleArea);
        }
        for (final Title currentTitle : this.subtitles) {
            this.drawTitle(currentTitle, g2, nonTitleArea);
        }
        final Rectangle2D plotArea = nonTitleArea;
        if (this.legend != null) {
            plotArea.setRect(this.legend.draw(g2, nonTitleArea, info));
        }
        PlotRenderingInfo plotInfo = null;
        if (info != null) {
            plotInfo = info.getPlotInfo();
        }
        this.plot.draw(g2, plotArea, anchor, null, plotInfo);
        g2.setClip(savedClip);
        this.notifyListeners(new ChartProgressEvent(this, this, 2, 100));
        if (JFreeChart.LOGGER.isDebugEnabled()) {
            JFreeChart.LOGGER.debug("Leaving draw() method");
        }
    }
    
    public void drawTitle(final Title title, final Graphics2D g2, final Rectangle2D area) {
        if (title == null) {
            throw new IllegalArgumentException("Null 'title' argument.");
        }
        if (area == null) {
            throw new IllegalArgumentException("Null 'area' argument.");
        }
        final Rectangle2D titleArea = new Rectangle2D.Double();
        double availableHeight = 0.0;
        double availableWidth = 0.0;
        final RectangleEdge position = title.getPosition();
        if (position == RectangleEdge.TOP) {
            availableWidth = area.getWidth();
            availableHeight = Math.min(title.getPreferredHeight(g2, (float)availableWidth), area.getHeight());
            titleArea.setRect(area.getX(), area.getY(), availableWidth, availableHeight);
            title.draw(g2, titleArea);
            area.setRect(area.getX(), Math.min(area.getY() + availableHeight, area.getMaxY()), availableWidth, Math.max(area.getHeight() - availableHeight, 0.0));
        }
        else if (position == RectangleEdge.BOTTOM) {
            availableWidth = area.getWidth();
            availableHeight = Math.min(title.getPreferredHeight(g2, (float)availableWidth), area.getHeight());
            titleArea.setRect(area.getX(), area.getMaxY() - availableHeight, availableWidth, availableHeight);
            title.draw(g2, titleArea);
            area.setRect(area.getX(), area.getY(), availableWidth, area.getHeight() - availableHeight);
        }
        else if (position == RectangleEdge.RIGHT) {
            availableHeight = area.getHeight();
            availableWidth = Math.min(title.getPreferredWidth(g2, (float)availableHeight), area.getWidth());
            titleArea.setRect(area.getMaxX() - availableWidth, area.getY(), availableWidth, availableHeight);
            title.draw(g2, titleArea);
            area.setRect(area.getX(), area.getY(), area.getWidth() - availableWidth, availableHeight);
        }
        else {
            if (position != RectangleEdge.LEFT) {
                throw new RuntimeException("JFreeChart.drawTitle(...): unknown title position.");
            }
            availableHeight = area.getHeight();
            availableWidth = Math.min(title.getPreferredWidth(g2, (float)availableHeight), area.getWidth());
            titleArea.setRect(area.getX(), area.getY(), availableWidth, availableHeight);
            title.draw(g2, titleArea);
            area.setRect(area.getX() + availableWidth, area.getY(), area.getWidth() - availableWidth, availableHeight);
        }
    }
    
    public BufferedImage createBufferedImage(final int width, final int height) {
        return this.createBufferedImage(width, height, null);
    }
    
    public BufferedImage createBufferedImage(final int width, final int height, final ChartRenderingInfo info) {
        return this.createBufferedImage(width, height, 1, info);
    }
    
    public BufferedImage createBufferedImage(final int width, final int height, final int imageType, final ChartRenderingInfo info) {
        final BufferedImage image = new BufferedImage(width, height, imageType);
        final Graphics2D g2 = image.createGraphics();
        this.draw(g2, new Rectangle2D.Double(0.0, 0.0, width, height), null, info);
        g2.dispose();
        return image;
    }
    
    public BufferedImage createBufferedImage(final int imageWidth, final int imageHeight, final double drawWidth, final double drawHeight, final ChartRenderingInfo info) {
        final BufferedImage image = new BufferedImage(imageWidth, imageHeight, 1);
        final Graphics2D g2 = image.createGraphics();
        final double scaleX = imageWidth / drawWidth;
        final double scaleY = imageHeight / drawHeight;
        final AffineTransform st = AffineTransform.getScaleInstance(scaleX, scaleY);
        g2.transform(st);
        this.draw(g2, new Rectangle2D.Double(0.0, 0.0, drawWidth, drawHeight), null, info);
        g2.dispose();
        return image;
    }
    
    public void handleClick(final int x, final int y, final ChartRenderingInfo info) {
        this.plot.handleClick(x, y, info.getPlotInfo());
    }
    
    public void addChangeListener(final ChartChangeListener listener) {
        this.changeListeners.add((JFreeChart.class$org$jfree$chart$event$ChartChangeListener == null) ? (JFreeChart.class$org$jfree$chart$event$ChartChangeListener = class$("org.jfree.chart.event.ChartChangeListener")) : JFreeChart.class$org$jfree$chart$event$ChartChangeListener, listener);
    }
    
    public void removeChangeListener(final ChartChangeListener listener) {
        this.changeListeners.remove((JFreeChart.class$org$jfree$chart$event$ChartChangeListener == null) ? (JFreeChart.class$org$jfree$chart$event$ChartChangeListener = class$("org.jfree.chart.event.ChartChangeListener")) : JFreeChart.class$org$jfree$chart$event$ChartChangeListener, listener);
    }
    
    public void fireChartChanged() {
        final ChartChangeEvent event = new ChartChangeEvent(this);
        this.notifyListeners(event);
    }
    
    protected void notifyListeners(final ChartChangeEvent event) {
        if (this.notify) {
            final Object[] listeners = this.changeListeners.getListenerList();
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == ((JFreeChart.class$org$jfree$chart$event$ChartChangeListener == null) ? (JFreeChart.class$org$jfree$chart$event$ChartChangeListener = class$("org.jfree.chart.event.ChartChangeListener")) : JFreeChart.class$org$jfree$chart$event$ChartChangeListener)) {
                    ((ChartChangeListener)listeners[i + 1]).chartChanged(event);
                }
            }
        }
    }
    
    public void addProgressListener(final ChartProgressListener listener) {
        this.progressListeners.add((JFreeChart.class$org$jfree$chart$event$ChartProgressListener == null) ? (JFreeChart.class$org$jfree$chart$event$ChartProgressListener = class$("org.jfree.chart.event.ChartProgressListener")) : JFreeChart.class$org$jfree$chart$event$ChartProgressListener, listener);
    }
    
    public void removeProgressListener(final ChartProgressListener listener) {
        this.progressListeners.remove((JFreeChart.class$org$jfree$chart$event$ChartProgressListener == null) ? (JFreeChart.class$org$jfree$chart$event$ChartProgressListener = class$("org.jfree.chart.event.ChartProgressListener")) : JFreeChart.class$org$jfree$chart$event$ChartProgressListener, listener);
    }
    
    protected void notifyListeners(final ChartProgressEvent event) {
        final Object[] listeners = this.progressListeners.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ((JFreeChart.class$org$jfree$chart$event$ChartProgressListener == null) ? (JFreeChart.class$org$jfree$chart$event$ChartProgressListener = class$("org.jfree.chart.event.ChartProgressListener")) : JFreeChart.class$org$jfree$chart$event$ChartProgressListener)) {
                ((ChartProgressListener)listeners[i + 1]).chartProgress(event);
            }
        }
    }
    
    public void titleChanged(final TitleChangeEvent event) {
        event.setChart(this);
        this.notifyListeners(event);
    }
    
    public void legendChanged(final LegendChangeEvent event) {
        event.setChart(this);
        this.notifyListeners(event);
    }
    
    public void plotChanged(final PlotChangeEvent event) {
        event.setChart(this);
        this.notifyListeners(event);
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof JFreeChart) {
            final JFreeChart c = (JFreeChart)obj;
            final boolean b0 = ObjectUtils.equal(this.title, c.title);
            final boolean b2 = ObjectUtils.equal(this.subtitles, c.subtitles);
            final boolean b3 = ObjectUtils.equal(this.legend, c.legend);
            final boolean b4 = ObjectUtils.equal(this.plot, c.plot);
            final boolean b5 = ObjectUtils.equal(this.backgroundPaint, c.backgroundPaint);
            final boolean b6 = ObjectUtils.equal(this.backgroundImage, c.backgroundImage);
            final boolean b7 = this.backgroundImageAlignment == c.backgroundImageAlignment;
            final boolean b8 = this.backgroundImageAlpha == c.backgroundImageAlpha;
            final boolean b9 = this.notify == c.notify;
            return b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9;
        }
        return false;
    }
    
    private void writeObject(final ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        SerialUtilities.writeStroke(this.borderStroke, stream);
        SerialUtilities.writePaint(this.borderPaint, stream);
        SerialUtilities.writePaint(this.backgroundPaint, stream);
    }
    
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.borderStroke = SerialUtilities.readStroke(stream);
        this.borderPaint = SerialUtilities.readPaint(stream);
        this.backgroundPaint = SerialUtilities.readPaint(stream);
        this.progressListeners = new EventListenerList();
        this.changeListeners = new EventListenerList();
        this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.title != null) {
            this.title.addChangeListener(this);
        }
        for (int i = 0; i < this.getSubtitleCount(); ++i) {
            this.getSubtitle(i).addChangeListener(this);
        }
        if (this.legend != null) {
            this.legend.addChangeListener(this);
        }
        this.plot.addChangeListener(this);
    }
    
    public static void main(final String[] args) {
        System.out.println(JFreeChart.INFO.toString());
    }
    
    public boolean getSuppressChartChangeEvents() {
        return this.isNotify();
    }
    
    public void setSuppressChartChangeEvents(final boolean flag) {
        this.setNotify(!flag);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final JFreeChart chart = (JFreeChart)super.clone();
        chart.renderingHints = (RenderingHints)this.renderingHints.clone();
        if (this.title != null) {
            (chart.title = (TextTitle)this.title.clone()).addChangeListener(chart);
        }
        chart.subtitles = new ArrayList();
        for (int i = 0; i < this.getSubtitleCount(); ++i) {
            final Title subtitle = (Title)this.getSubtitle(i).clone();
            chart.subtitles.add(subtitle);
            subtitle.addChangeListener(chart);
        }
        if (this.legend != null) {
            (chart.legend = (Legend)this.legend.clone()).registerChart(chart);
            chart.legend.addChangeListener(chart);
        }
        if (this.plot != null) {
            (chart.plot = (Plot)this.plot.clone()).addChangeListener(chart);
        }
        chart.progressListeners = new EventListenerList();
        chart.changeListeners = new EventListenerList();
        return chart;
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
        INFO = new JFreeChartInfo();
        DEFAULT_TITLE_FONT = new Font("SansSerif", 1, 18);
        DEFAULT_BACKGROUND_PAINT = SystemColor.control;
        DEFAULT_BACKGROUND_IMAGE = null;
        JFreeChart.DEFAULT_BACKGROUND_IMAGE_ALPHA = 0.5f;
        LOGGER = Log.createContext((JFreeChart.class$org$jfree$chart$JFreeChart == null) ? (JFreeChart.class$org$jfree$chart$JFreeChart = class$("org.jfree.chart.JFreeChart")) : JFreeChart.class$org$jfree$chart$JFreeChart);
    }
}
