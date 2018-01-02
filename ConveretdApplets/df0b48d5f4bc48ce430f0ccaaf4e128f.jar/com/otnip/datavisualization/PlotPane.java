// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization;

import javax.swing.JFrame;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import com.otnip.tools.ColorTools;
import java.awt.Stroke;
import java.awt.Font;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.SwingUtilities;
import java.awt.geom.Point2D;
import java.awt.LayoutManager;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import com.otnip.tools.IconFactory;
import com.otnip.datavisualization.elements.DefaultDataConverter;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import com.otnip.datavisualization.elements.DataConverter;
import com.otnip.datavisualization.elements.Axes;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import javax.swing.JPanel;

public abstract class PlotPane extends JPanel
{
    private static final BasicStroke zoomStroke;
    final double dataRenderingImageAreaOffsetX = 0.1;
    final double dataRenderingImageAreaOffsetY = 0.1;
    final double dataRenderingImageAreaWidth = 0.8;
    final double dataRenderingImageAreaHeight = 0.8;
    public BufferedImage dataRenderingImage;
    protected boolean updateDataRenderingImage;
    private Axes axes;
    private Axes previousAxes;
    private DataConverter xAxisLabeler;
    private DataConverter yAxisLabeler;
    private String xAxisLabel;
    private String yAxisLabel;
    private String title;
    private UIFeedbackState uiFeedbackState;
    private Coordinate2D initialDataCoordinate;
    private Coordinate2D currentDataCoordinate;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JPopupMenu popupMenu;
    private JMenu popupMenu_zoom;
    private JMenuItem popupMenu_zoom_zoomFull;
    private JMenuItem popupMenu_zoom_zoomFullY;
    private JMenuItem popupMenu_zoom_zoomIn;
    private JMenuItem popupMenu_zoom_zoomOut;
    private JMenuItem popupMenu_zoom_zoomPrevious;
    
    public PlotPane() {
        this.dataRenderingImage = new BufferedImage(1, 1, 5);
        this.updateDataRenderingImage = false;
        this.axes = new Axes(-1.0, -1.0, 1.0, 1.0);
        this.previousAxes = this.axes;
        this.xAxisLabeler = new DefaultDataConverter();
        this.yAxisLabeler = new DefaultDataConverter();
        this.xAxisLabel = "X-Axis";
        this.yAxisLabel = "Y-Axis";
        this.title = "Title";
        this.uiFeedbackState = UIFeedbackState.NULL;
        this.initialDataCoordinate = new Coordinate2D(0.0, 0.0);
        this.currentDataCoordinate = new Coordinate2D(0.0, 0.0);
        this.initComponents();
    }
    
    private void initComponents() {
        this.popupMenu = new JPopupMenu();
        this.popupMenu_zoom = new JMenu();
        this.popupMenu_zoom_zoomFull = new JMenuItem();
        this.popupMenu_zoom_zoomFullY = new JMenuItem();
        this.jSeparator1 = new JSeparator();
        this.popupMenu_zoom_zoomPrevious = new JMenuItem();
        this.jSeparator2 = new JSeparator();
        this.popupMenu_zoom_zoomIn = new JMenuItem();
        this.popupMenu_zoom_zoomOut = new JMenuItem();
        this.popupMenu_zoom.setIcon(IconFactory.get("IconExperience/icons/24x24/plain/view.png"));
        this.popupMenu_zoom.setText("Zoom Options");
        this.popupMenu_zoom_zoomFull.setIcon(IconFactory.get("IconExperience/icons/24x24/plain/fit_to_size.png"));
        this.popupMenu_zoom_zoomFull.setText("Zoom Full");
        this.popupMenu_zoom_zoomFull.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PlotPane.this.popupMenu_zoom_zoomFullActionPerformed(evt);
            }
        });
        this.popupMenu_zoom.add(this.popupMenu_zoom_zoomFull);
        this.popupMenu_zoom_zoomFullY.setIcon(IconFactory.get("HomeGrown/fit_to_size_y_24x24_plain.png"));
        this.popupMenu_zoom_zoomFullY.setText("Zoom Full Y");
        this.popupMenu_zoom_zoomFullY.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PlotPane.this.popupMenu_zoom_zoomFullYActionPerformed(evt);
            }
        });
        this.popupMenu_zoom.add(this.popupMenu_zoom_zoomFullY);
        this.popupMenu_zoom.add(this.jSeparator1);
        this.popupMenu_zoom_zoomPrevious.setIcon(IconFactory.get("IconExperience/icons/24x24/plain/undo.png"));
        this.popupMenu_zoom_zoomPrevious.setText("Zoom Previous");
        this.popupMenu_zoom_zoomPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PlotPane.this.popupMenu_zoom_zoomPreviousActionPerformed(evt);
            }
        });
        this.popupMenu_zoom.add(this.popupMenu_zoom_zoomPrevious);
        this.popupMenu_zoom.add(this.jSeparator2);
        this.popupMenu_zoom_zoomIn.setIcon(IconFactory.get("IconExperience/icons/24x24/plain/zoom_in.png"));
        this.popupMenu_zoom_zoomIn.setText("Zoom In");
        this.popupMenu_zoom_zoomIn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PlotPane.this.popupMenu_zoom_zoomInActionPerformed(evt);
            }
        });
        this.popupMenu_zoom.add(this.popupMenu_zoom_zoomIn);
        this.popupMenu_zoom_zoomOut.setIcon(IconFactory.get("IconExperience/icons/24x24/plain/zoom_out.png"));
        this.popupMenu_zoom_zoomOut.setText("Zoom Out");
        this.popupMenu_zoom_zoomOut.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PlotPane.this.popupMenu_zoom_zoomOutActionPerformed(evt);
            }
        });
        this.popupMenu_zoom.add(this.popupMenu_zoom_zoomOut);
        this.popupMenu.add(this.popupMenu_zoom);
        this.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(final MouseWheelEvent evt) {
                PlotPane.this.formMouseWheelMoved(evt);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent evt) {
                PlotPane.this.formMouseClicked(evt);
            }
            
            public void mousePressed(final MouseEvent evt) {
                PlotPane.this.formMousePressed(evt);
            }
            
            public void mouseReleased(final MouseEvent evt) {
                PlotPane.this.formMouseReleased(evt);
            }
        });
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent evt) {
                PlotPane.this.formComponentResized(evt);
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent evt) {
                PlotPane.this.formMouseDragged(evt);
            }
            
            public void mouseMoved(final MouseEvent evt) {
                PlotPane.this.formMouseMoved(evt);
            }
        });
        this.setLayout(null);
    }
    
    private void popupMenu_zoom_zoomFullYActionPerformed(final ActionEvent evt) {
        this.zoomFullY();
    }
    
    private void formMouseClicked(final MouseEvent evt) {
        if (evt.getClickCount() == 1 && evt.getButton() == 3) {
            this.popupMenu.show(this, evt.getX(), evt.getY());
        }
    }
    
    private void popupMenu_zoom_zoomFullActionPerformed(final ActionEvent evt) {
        this.zoomFull();
    }
    
    private void popupMenu_zoom_zoomPreviousActionPerformed(final ActionEvent evt) {
        this.zoomPrevious();
    }
    
    private void popupMenu_zoom_zoomInActionPerformed(final ActionEvent evt) {
        this.zoomIn();
    }
    
    private void popupMenu_zoom_zoomOutActionPerformed(final ActionEvent evt) {
        this.zoomOut();
    }
    
    private void formMouseWheelMoved(final MouseWheelEvent evt) {
        if (evt.getWheelRotation() < 0) {
            this.zoomIn();
        }
        else {
            this.zoomOut();
        }
    }
    
    private void formComponentResized(final ComponentEvent evt) {
        this.updateDataRenderingImage = true;
    }
    
    private void formMouseReleased(final MouseEvent evt) {
        if (this.uiFeedbackState == UIFeedbackState.ZOOMING && !this.initialDataCoordinate.equals(this.currentDataCoordinate)) {
            final double x1 = this.initialDataCoordinate.getX();
            final double y1 = this.initialDataCoordinate.getY();
            final double x2 = this.currentDataCoordinate.getX();
            final double y2 = this.currentDataCoordinate.getY();
            final Axes newAxes = new Axes(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2));
            this.setAxes(newAxes);
        }
        this.uiFeedbackState = UIFeedbackState.NULL;
    }
    
    private void formMouseMoved(final MouseEvent evt) {
        this.currentDataCoordinate.setDataCoordinatesFromScreenCoordinates(evt.getPoint());
    }
    
    private void formMousePressed(final MouseEvent evt) {
        if (evt.getButton() == 1) {
            this.initialDataCoordinate.setDataCoordinatesFromScreenCoordinates(evt.getPoint());
            this.uiFeedbackState = UIFeedbackState.ZOOMING;
        }
        if (evt.getButton() == 2) {
            final Point point = evt.getPoint();
            SwingUtilities.convertPointToScreen(point, (Component)evt.getSource());
            this.initialDataCoordinate.setLocation(point.getX(), point.getY());
            this.currentDataCoordinate.setLocation(this.axes.getMinX(), this.axes.getMinY());
            this.uiFeedbackState = UIFeedbackState.SCROLLING;
        }
    }
    
    private void formMouseDragged(final MouseEvent evt) {
        if (this.uiFeedbackState == UIFeedbackState.SCROLLING) {
            final double xRange = this.axes.getMaxX() - this.axes.getMinX();
            final double yRange = this.axes.getMaxY() - this.axes.getMinY();
            final double xScale = xRange / (0.8 * this.getWidth());
            final double yScale = yRange / (0.8 * this.getHeight());
            final Point point = evt.getPoint();
            SwingUtilities.convertPointToScreen(point, (Component)evt.getSource());
            final double dx = -xScale * (point.getX() - this.initialDataCoordinate.getX());
            final double dy = yScale * (point.getY() - this.initialDataCoordinate.getY());
            final Axes newAxes = new Axes(this.currentDataCoordinate.getX() + dx, this.currentDataCoordinate.getY() + dy, this.currentDataCoordinate.getX() + dx + xRange, this.currentDataCoordinate.getY() + dy + yRange);
            this.setAxes(newAxes);
        }
        else {
            this.currentDataCoordinate.setDataCoordinatesFromScreenCoordinates(evt.getPoint());
        }
        this.repaint();
    }
    
    protected abstract void renderData();
    
    protected abstract void zoomFull();
    
    protected abstract void zoomFullY();
    
    protected BufferedImage getPlotImage() {
        final int imageWidth = (int)(this.getWidth() * 0.8);
        final int imageHeight = (int)(this.getHeight() * 0.8);
        if (imageWidth != this.dataRenderingImage.getWidth() || imageHeight != this.dataRenderingImage.getHeight()) {
            this.dataRenderingImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(imageWidth, imageHeight);
        }
        return this.dataRenderingImage;
    }
    
    public void setXLabel(final String label) {
        this.xAxisLabel = label;
    }
    
    public void setYLabel(final String label) {
        this.yAxisLabel = label;
    }
    
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.updateDataRenderingImage) {
            this.renderData();
            this.updateDataRenderingImage = false;
        }
        g2.setColor(new Color(223, 223, 223));
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        final AffineTransform transform = g2.getTransform();
        g2.translate(0.1 * this.getWidth(), 0.1 * this.getHeight());
        g2.drawImage(this.dataRenderingImage, 0, 0, this);
        g2.setTransform(transform);
        this.renderStuff(g2);
    }
    
    private void renderStuff(final Graphics2D g2) {
        final AffineTransform transform = g2.getTransform();
        final Rectangle r = g2.getClipBounds();
        final double width = r.getWidth();
        final double height = r.getHeight();
        final double[] bounds = new double[2];
        g2.setColor(Color.BLACK);
        final int numberOfXTicks = 11;
        final double yOffset = 5.0;
        final int y1 = (int)(0.1 * height);
        final int y2 = (int)(y1 + 0.8 * height + 5.0);
        double dTick = 0.8 * width / 10.0;
        final double theta = 0.2617993877991494;
        final double dValue = (this.axes.getMaxX() - this.axes.getMinX()) / 10.0;
        final double xOffset = 2.5 * Math.tan(0.2617993877991494);
        bounds[0] = 1000.0;
        bounds[1] = 5.0 / Math.sin(0.2617993877991494);
        this.setFont("123.456", g2, bounds);
        double currentValue = 0.1 * width;
        for (int i = 0; i < 11; ++i) {
            final int x = (int)currentValue;
            g2.setTransform(transform);
            g2.drawLine(x, y1, x, y2);
            final String text = this.yAxisLabeler.getHumanRepresentation(this.axes.getMinX() + dValue * i);
            final AffineTransform t = new AffineTransform();
            final TextLayout textLayout = new TextLayout(text, g2.getFont(), g2.getFontRenderContext());
            t.translate(x - xOffset, y2 + 5.0 + 2.0);
            t.rotate(0.2617993877991494);
            g2.setTransform(t);
            g2.drawString(text, 0, 0);
            currentValue += dTick;
        }
        g2.setTransform(transform);
        final int numberOfYTicks = 11;
        final int x2 = (int)(0.1 * width) - 5;
        final int x3 = (int)(x2 + 0.8 * width) + 5;
        double currentValue2 = 0.1 * height;
        dTick = 0.8 * height / (numberOfYTicks - 1);
        final double dValue2 = (this.axes.getMaxY() - this.axes.getMinY()) / (numberOfYTicks - 1);
        final double dx = width * 0.1 - 1.0;
        bounds[0] = -4.9406;
        bounds[1] = dTick * 0.5;
        this.setFont("123.456", g2, bounds);
        for (int j = 0; j < numberOfYTicks; ++j) {
            final int y3 = (int)currentValue2;
            g2.drawLine(x2, y3, x3, y3);
            final String text2 = this.yAxisLabeler.getHumanRepresentation(this.axes.getMaxY() - dValue2 * j);
            final TextLayout textLayout2 = new TextLayout(text2, g2.getFont(), g2.getFontRenderContext());
            final Rectangle2D textBounds = textLayout2.getBounds();
            g2.drawString(text2, (int)(dx - textBounds.getWidth() - 7.0), (int)(currentValue2 + 0.5 * textBounds.getHeight()));
            currentValue2 += dTick;
        }
        g2.setFont(new Font("Arial", 3, 1));
        int dy = (int)(height * 0.1 * 0.8);
        bounds[0] = 0.99 * width;
        bounds[1] = dy;
        this.setFont(this.title, g2, bounds);
        int x4 = (int)(0.5 * (width - bounds[0]));
        int y4 = (int)(dy - 0.5 * (dy - bounds[1]));
        g2.drawString(this.title, x4, y4);
        dy = (int)(height * 0.1 * 0.5 * 0.99);
        bounds[0] = width;
        bounds[1] = dy - 5;
        this.setFont(this.xAxisLabel, g2, bounds);
        x4 = (int)(0.5 * width - 0.5 * bounds[0]);
        y4 = (int)(height - 0.5 * (dy - bounds[1]));
        g2.drawString(this.xAxisLabel, x4, y4 - 5);
        final int dx2 = (int)(width * 0.1 * 0.33 * 0.99);
        bounds[0] = height;
        bounds[1] = dx2;
        this.setFont(this.yAxisLabel, g2, bounds);
        x4 = (int)(0.5 * (bounds[1] + dx2));
        y4 = (int)(0.5 * (height + bounds[0]));
        final AffineTransform t2 = new AffineTransform();
        t2.translate(x4, y4);
        t2.rotate(-1.5707963267948966);
        g2.setTransform(t2);
        g2.drawString(this.yAxisLabel, 0, 0);
        g2.setTransform(transform);
        switch (this.uiFeedbackState) {
            case ZOOMING: {
                final double textBoundsScale = 1.5;
                final int ovalAlpha = 200;
                final Coordinate2D startCoordinate = this.initialDataCoordinate.getScreenCoordinatesFromDataCoordinates();
                final Coordinate2D endCoordinate = this.currentDataCoordinate.getScreenCoordinatesFromDataCoordinates();
                final int x5 = (int)Math.min(startCoordinate.getX(), endCoordinate.getX());
                final int y5 = (int)Math.min(startCoordinate.getY(), endCoordinate.getY());
                final int w = (int)Math.abs(startCoordinate.getX() - endCoordinate.getX());
                final int h = (int)Math.abs(startCoordinate.getY() - endCoordinate.getY());
                Color color = Color.BLACK;
                g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 64));
                g2.fillRect(x5, y5, w, h);
                final Stroke defaultStroke = g2.getStroke();
                g2.setColor(Color.BLACK);
                g2.setStroke(PlotPane.zoomStroke);
                g2.drawRect(x5, y5, w, h);
                g2.setStroke(defaultStroke);
                g2.setFont(new Font("Arial", 3, 12));
                g2.setColor(Color.RED);
                String text3 = "(x1, y1) = (" + this.xAxisLabeler.getHumanRepresentation(this.initialDataCoordinate.getX()) + ", " + this.yAxisLabeler.getHumanRepresentation(this.initialDataCoordinate.getY()) + ")";
                Rectangle2D textBounds2 = g2.getFontMetrics().getStringBounds(text3, g2);
                if (0.0 * textBounds2.getWidth() < w && 4.5 * textBounds2.getHeight() < h) {
                    final int ovalWidth = (int)(1.5 * textBounds2.getWidth());
                    final int ovalHeight = (int)(1.5 * textBounds2.getHeight());
                    final int ovalX = x5 + 2;
                    final int ovalY = y5 + 2;
                    final int textX = (int)(ovalX + 0.5 * (ovalWidth - textBounds2.getWidth()));
                    final int textY = (int)(ovalY + 0.5 * (ovalHeight + textBounds2.getHeight())) - 2;
                    color = g2.getColor();
                    g2.setColor(ColorTools.setAlpha(Color.WHITE, 200));
                    g2.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);
                    g2.setColor(ColorTools.setAlpha(Color.BLACK, 200));
                    g2.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);
                    g2.setColor(ColorTools.setAlpha(color, 200));
                    g2.drawString(text3, textX, textY);
                }
                text3 = "(x2, y2) = (" + this.yAxisLabeler.getHumanRepresentation(this.currentDataCoordinate.getX()) + ", " + this.yAxisLabeler.getHumanRepresentation(this.currentDataCoordinate.getY()) + ")";
                textBounds2 = g2.getFontMetrics().getStringBounds(text3, g2);
                if (0.0 * textBounds2.getWidth() < w && 4.5 * textBounds2.getHeight() < h) {
                    final int ovalWidth = (int)(1.5 * textBounds2.getWidth());
                    final int ovalHeight = (int)(1.5 * textBounds2.getHeight());
                    final int ovalX = x5 + w - ovalWidth - 2;
                    final int ovalY = y5 + h - ovalHeight - 2;
                    final int textX = (int)(ovalX + 0.5 * (ovalWidth - textBounds2.getWidth()));
                    final int textY = (int)(ovalY + 0.5 * (ovalHeight + textBounds2.getHeight())) - 2;
                    color = g2.getColor();
                    g2.setColor(ColorTools.setAlpha(Color.WHITE, 200));
                    g2.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);
                    g2.setColor(ColorTools.setAlpha(Color.BLACK, 200));
                    g2.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);
                    g2.setColor(ColorTools.setAlpha(color, 200));
                    g2.drawString(text3, textX, textY);
                }
                text3 = "dx x dy = " + this.xAxisLabeler.getHumanRepresentation(Math.abs(this.currentDataCoordinate.getX() - this.initialDataCoordinate.getX())) + " x " + this.yAxisLabeler.getHumanRepresentation(Math.abs(this.currentDataCoordinate.getY() - this.initialDataCoordinate.getY()));
                textBounds2 = g2.getFontMetrics().getStringBounds(text3, g2);
                if (0.0 * textBounds2.getWidth() < w && 4.5 * textBounds2.getHeight() < h) {
                    final int ovalWidth = (int)(1.5 * textBounds2.getWidth());
                    final int ovalHeight = (int)(1.5 * textBounds2.getHeight());
                    final int ovalX = x5 + (w - ovalWidth) / 2;
                    final int ovalY = y5 + (h - ovalHeight) / 2;
                    final int textX = (int)(x5 + 0.5 * (w - textBounds2.getWidth()));
                    final int textY = (int)(y5 + 0.5 * (h + textBounds2.getHeight())) - 2;
                    color = g2.getColor();
                    g2.setColor(ColorTools.setAlpha(Color.WHITE, 200));
                    g2.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);
                    g2.setColor(ColorTools.setAlpha(Color.BLACK, 200));
                    g2.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);
                    g2.setColor(ColorTools.setAlpha(color, 200));
                    g2.drawString(text3, textX, textY);
                    break;
                }
                break;
            }
        }
    }
    
    protected void setFont(final String text, final Graphics2D g2, final double[] bounds) {
        Font font = g2.getFont();
        final String fontName = font.getName();
        final int fontStyle = font.getStyle();
        int fontSize = 1;
        boolean keepSearching = true;
        while (keepSearching) {
            font = new Font(fontName, fontStyle, fontSize);
            final Rectangle2D textBounds = g2.getFontMetrics(font).getStringBounds(text, g2);
            keepSearching = (textBounds.getWidth() < bounds[0] && textBounds.getHeight() < bounds[1]);
            if (keepSearching) {
                g2.setFont(font);
                ++fontSize;
            }
        }
        final Rectangle2D textBounds = g2.getFontMetrics().getStringBounds(text, g2);
        bounds[0] = textBounds.getWidth();
        bounds[1] = textBounds.getHeight();
    }
    
    public void setAxes(final Axes axes) {
        this.previousAxes = this.axes;
        this.axes = axes;
        this.updateDataRenderingImage = true;
        this.repaint();
    }
    
    public void display() {
        final JFrame frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
    public void zoomIn() {
        final double deltaX = (this.axes.getMaxX() - this.axes.getMinX()) * 0.1;
        final double deltaY = (this.axes.getMaxY() - this.axes.getMinY()) * 0.1;
        final Axes newAxes = new Axes(this.axes.getMinX() + deltaX, this.axes.getMinY() + deltaY, this.axes.getMaxX() - deltaX, this.axes.getMaxY() - deltaY);
        this.setAxes(newAxes);
    }
    
    public void zoomOut() {
        final double deltaX = 0.1 * (this.axes.getMaxX() - this.axes.getMinX());
        final double deltaY = 0.1 * (this.axes.getMaxY() - this.axes.getMinY());
        final Axes newAxes = new Axes(this.axes.getMinX() - deltaX, this.axes.getMinY() - deltaY, this.axes.getMaxX() + deltaX, this.axes.getMaxY() + deltaY);
        this.setAxes(newAxes);
    }
    
    public void zoomPrevious() {
        this.setAxes(this.previousAxes);
    }
    
    public Axes getAxes() {
        return this.axes;
    }
    
    public void setTitle(final String title) {
        this.title = title;
        this.repaint();
    }
    
    public String getTitle() {
        return this.title;
    }
    
    static {
        zoomStroke = new BasicStroke(2.0f, 1, 1, 0.0f, new float[] { 7.0f, 7.0f }, 0.0f);
    }
    
    private enum UIFeedbackState
    {
        NULL, 
        ZOOMING, 
        SCROLLING, 
        BROWSING;
    }
    
    public class Coordinate2D extends Point2D
    {
        private double x;
        private double y;
        
        public Coordinate2D(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
        
        public double getX() {
            return this.x;
        }
        
        public double getY() {
            return this.y;
        }
        
        public void setLocation(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
        
        public void setDataCoordinatesFromScreenCoordinates(final Point2D coordinate) {
            this.x = coordinate.getX() / PlotPane.this.getWidth() - 0.1;
            this.x /= 0.8;
            this.x = PlotPane.this.axes.getMinX() + this.x * (PlotPane.this.axes.getMaxX() - PlotPane.this.axes.getMinX());
            this.y = coordinate.getY() / PlotPane.this.getHeight() - 0.1;
            this.y /= 0.8;
            this.y = PlotPane.this.axes.getMaxY() - this.y * (PlotPane.this.axes.getMaxY() - PlotPane.this.axes.getMinY());
        }
        
        public Coordinate2D getScreenCoordinatesFromDataCoordinates() {
            double temp = (this.x - PlotPane.this.axes.getMinX()) / (PlotPane.this.axes.getMaxX() - PlotPane.this.axes.getMinX());
            temp *= 0.8;
            temp += 0.1;
            final double tempX;
            temp = (tempX = temp * PlotPane.this.getWidth());
            temp = (PlotPane.this.axes.getMaxY() - this.y) / (PlotPane.this.axes.getMaxY() - PlotPane.this.axes.getMinY());
            temp *= 0.8;
            temp += 0.1;
            final double tempY;
            temp = (tempY = temp * PlotPane.this.getHeight());
            return new Coordinate2D(tempX, tempY);
        }
        
        public String toString() {
            return "Coordinate2D:  (" + this.x + ", " + this.y + ")";
        }
    }
}
