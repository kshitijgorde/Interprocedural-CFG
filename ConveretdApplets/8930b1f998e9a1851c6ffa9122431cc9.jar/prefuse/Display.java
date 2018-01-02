// 
// Decompiled by Procyon v0.5.30
// 

package prefuse;

import prefuse.activity.Pacer;
import prefuse.activity.SlowInSlowOutPacer;
import prefuse.activity.Activity;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import prefuse.util.ColorLib;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import prefuse.render.Renderer;
import java.awt.Point;
import prefuse.controls.Control;
import prefuse.util.display.ItemBoundsListener;
import prefuse.util.StringLib;
import java.awt.geom.NoninvertibleTransformException;
import java.util.Iterator;
import java.awt.Shape;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.OutputStream;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import prefuse.visual.sort.ItemSorter;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.data.expression.BooleanLiteral;
import java.awt.Font;
import java.awt.Dimension;
import prefuse.util.display.ExportDisplayAction;
import javax.swing.KeyStroke;
import prefuse.util.display.DebugStatsPainter;
import java.awt.event.ActionEvent;
import prefuse.util.display.PaintListener;
import java.awt.event.ActionListener;
import prefuse.data.event.ExpressionListener;
import prefuse.util.UpdateListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.JTextField;
import java.awt.Color;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.expression.Predicate;
import prefuse.visual.VisualItem;
import javax.swing.text.JTextComponent;
import javax.swing.JToolTip;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;
import prefuse.util.display.RenderingQueue;
import prefuse.util.display.BackgroundPainter;
import java.awt.geom.Rectangle2D;
import prefuse.util.display.Clip;
import java.awt.image.BufferedImage;
import prefuse.util.collections.CopyOnWriteArrayList;
import prefuse.data.expression.AndPredicate;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class Display extends JComponent
{
    private static final Logger s_logger;
    protected Visualization m_vis;
    protected AndPredicate m_predicate;
    protected CopyOnWriteArrayList m_controls;
    protected CopyOnWriteArrayList m_painters;
    protected CopyOnWriteArrayList m_bounders;
    protected BufferedImage m_offscreen;
    protected Clip m_clip;
    protected Clip m_screen;
    protected Clip m_bounds;
    protected Rectangle2D m_rclip;
    protected boolean m_damageRedraw;
    protected boolean m_highQuality;
    protected BackgroundPainter m_bgpainter;
    protected RenderingQueue m_queue;
    protected int m_visibleCount;
    protected AffineTransform m_transform;
    protected AffineTransform m_itransform;
    protected TransformActivity m_transact;
    protected Point2D m_tmpPoint;
    protected double frameRate;
    protected int nframes;
    private int sampleInterval;
    private long mark;
    protected JToolTip m_customToolTip;
    private JTextComponent m_editor;
    private boolean m_editing;
    private VisualItem m_editItem;
    private String m_editAttribute;
    
    public Display() {
        this(null);
    }
    
    public Display(final Visualization visualization) {
        this(visualization, (Predicate)null);
    }
    
    public Display(final Visualization visualization, final String s) {
        this(visualization, (Predicate)ExpressionParser.parse(s, true));
    }
    
    public Display(final Visualization visualization, final Predicate predicate) {
        this.m_predicate = new AndPredicate();
        this.m_controls = new CopyOnWriteArrayList();
        this.m_clip = new Clip();
        this.m_screen = new Clip();
        this.m_bounds = new Clip();
        this.m_rclip = new Rectangle2D.Double();
        this.m_damageRedraw = true;
        this.m_highQuality = false;
        this.m_bgpainter = null;
        this.m_queue = new RenderingQueue();
        this.m_visibleCount = 0;
        this.m_transform = new AffineTransform();
        this.m_itransform = new AffineTransform();
        this.m_transact = new TransformActivity();
        this.m_tmpPoint = new Point2D.Double();
        this.nframes = 0;
        this.sampleInterval = 10;
        this.mark = -1L;
        this.m_customToolTip = null;
        this.setDoubleBuffered(false);
        this.setBackground(Color.WHITE);
        this.m_editing = false;
        (this.m_editor = new JTextField()).setBorder(null);
        this.m_editor.setVisible(false);
        this.add(this.m_editor);
        final InputEventCapturer inputEventCapturer = new InputEventCapturer();
        this.addMouseListener(inputEventCapturer);
        this.addMouseMotionListener(inputEventCapturer);
        this.addMouseWheelListener(inputEventCapturer);
        this.addKeyListener(inputEventCapturer);
        this.registerDefaultCommands();
        this.m_predicate.addExpressionListener(new UpdateListener() {
            public void update(final Object o) {
                Display.this.damageReport();
            }
        });
        this.setVisualization(visualization);
        this.setPredicate(predicate);
        this.setSize(400, 400);
    }
    
    protected void registerDefaultCommands() {
        this.registerKeyboardAction(new ActionListener() {
            private PaintListener m_debug = null;
            
            public void actionPerformed(final ActionEvent actionEvent) {
                if (this.m_debug == null) {
                    this.m_debug = new DebugStatsPainter();
                    Display.this.addPaintListener(this.m_debug);
                }
                else {
                    Display.this.removePaintListener(this.m_debug);
                    this.m_debug = null;
                }
                Display.this.repaint();
            }
        }, "debug info", KeyStroke.getKeyStroke("ctrl D"), 0);
        this.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                Display.this.setHighQuality(!Display.this.isHighQuality());
                Display.this.repaint();
            }
        }, "toggle high-quality drawing", KeyStroke.getKeyStroke("ctrl H"), 0);
        try {
            this.registerKeyboardAction(new ExportDisplayAction(this), "export display", KeyStroke.getKeyStroke("ctrl E"), 0);
        }
        catch (SecurityException ex) {}
    }
    
    public void setSize(final int n, final int n2) {
        this.m_offscreen = null;
        this.setPreferredSize(new Dimension(n, n2));
        super.setSize(n, n2);
    }
    
    public void setSize(final Dimension dimension) {
        this.m_offscreen = null;
        this.setPreferredSize(dimension);
        super.setSize(dimension);
    }
    
    public void invalidate() {
        this.damageReport();
        super.invalidate();
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.m_offscreen = null;
        super.setBounds(n, n2, n3, n4);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.m_editor.setFont(font);
    }
    
    public double getFrameRate() {
        return this.frameRate;
    }
    
    public void setHighQuality(final boolean highQuality) {
        if (this.m_highQuality != highQuality) {
            this.damageReport();
        }
        this.m_highQuality = highQuality;
    }
    
    public boolean isHighQuality() {
        return this.m_highQuality;
    }
    
    public Visualization getVisualization() {
        return this.m_vis;
    }
    
    public void setVisualization(final Visualization vis) {
        if (this.m_vis == vis) {
            return;
        }
        if (this.m_vis != null) {
            this.m_vis.removeDisplay(this);
        }
        this.m_vis = vis;
        if (this.m_vis != null) {
            this.m_vis.addDisplay(this);
        }
    }
    
    public Predicate getPredicate() {
        if (this.m_predicate.size() == 1) {
            return BooleanLiteral.TRUE;
        }
        return this.m_predicate.get(0);
    }
    
    public void setPredicate(final String s) {
        this.setPredicate((Predicate)ExpressionParser.parse(s, true));
    }
    
    public synchronized void setPredicate(final Predicate predicate) {
        if (predicate == null) {
            this.m_predicate.set(VisiblePredicate.TRUE);
        }
        else {
            this.m_predicate.set(new Predicate[] { predicate, VisiblePredicate.TRUE });
        }
    }
    
    public int getVisibleItemCount() {
        return this.m_visibleCount;
    }
    
    public ItemSorter getItemSorter() {
        return this.m_queue.sort;
    }
    
    public synchronized void setItemSorter(final ItemSorter sort) {
        this.damageReport();
        this.m_queue.sort = sort;
    }
    
    public synchronized void setBackgroundImage(final Image image, final boolean b, final boolean b2) {
        BackgroundPainter backgroundPainter = null;
        if (image != null) {
            backgroundPainter = new BackgroundPainter(image, b, b2);
        }
        this.setBackgroundPainter(backgroundPainter);
    }
    
    public synchronized void setBackgroundImage(final String s, final boolean b, final boolean b2) {
        BackgroundPainter backgroundPainter = null;
        if (s != null) {
            backgroundPainter = new BackgroundPainter(s, b, b2);
        }
        this.setBackgroundPainter(backgroundPainter);
    }
    
    private void setBackgroundPainter(final BackgroundPainter bgpainter) {
        if (this.m_bgpainter != null) {
            this.removePaintListener(this.m_bgpainter);
        }
        if ((this.m_bgpainter = bgpainter) != null) {
            this.addPaintListener(bgpainter);
        }
    }
    
    public JToolTip createToolTip() {
        if (this.m_customToolTip == null) {
            return super.createToolTip();
        }
        return this.m_customToolTip;
    }
    
    public void setCustomToolTip(final JToolTip customToolTip) {
        this.m_customToolTip = customToolTip;
    }
    
    public JToolTip getCustomToolTip() {
        return this.m_customToolTip;
    }
    
    public synchronized boolean isDamageRedraw() {
        return this.m_damageRedraw;
    }
    
    public synchronized void setDamageRedraw(final boolean damageRedraw) {
        this.m_damageRedraw = damageRedraw;
        this.m_clip.invalidate();
    }
    
    public synchronized void damageReport(final Rectangle2D rectangle2D) {
        if (this.m_damageRedraw) {
            this.m_clip.union(rectangle2D);
        }
    }
    
    public synchronized void damageReport() {
        this.m_clip.invalidate();
    }
    
    public synchronized void clearDamage() {
        if (this.m_damageRedraw) {
            this.m_clip.reset();
        }
    }
    
    public synchronized Rectangle2D getItemBounds() {
        return this.getItemBounds(new Rectangle2D.Double());
    }
    
    public synchronized Rectangle2D getItemBounds(final Rectangle2D rectangle2D) {
        rectangle2D.setFrameFromDiagonal(this.m_bounds.getMinX(), this.m_bounds.getMinY(), this.m_bounds.getMaxX(), this.m_bounds.getMaxY());
        return rectangle2D;
    }
    
    public BufferedImage getOffscreenBuffer() {
        return this.m_offscreen;
    }
    
    protected BufferedImage getNewOffscreenBuffer(final int n, final int n2) {
        BufferedImage bufferedImage = null;
        if (!GraphicsEnvironment.isHeadless()) {
            try {
                bufferedImage = (BufferedImage)this.createImage(n, n2);
            }
            catch (Exception ex) {
                bufferedImage = null;
            }
        }
        if (bufferedImage == null) {
            return new BufferedImage(n, n2, 1);
        }
        return bufferedImage;
    }
    
    public boolean saveImage(final OutputStream outputStream, final String s, final double n) {
        try {
            final Dimension dimension = new Dimension((int)(n * this.getWidth()), (int)(n * this.getHeight()));
            final BufferedImage newOffscreenBuffer = this.getNewOffscreenBuffer(dimension.width, dimension.height);
            final Graphics2D graphics2D = (Graphics2D)newOffscreenBuffer.getGraphics();
            final Point2D.Double double1 = new Point2D.Double(0.0, 0.0);
            this.zoom(double1, n);
            final boolean highQuality = this.isHighQuality();
            this.setHighQuality(true);
            this.paintDisplay(graphics2D, dimension);
            this.setHighQuality(highQuality);
            this.zoom(double1, 1.0 / n);
            ImageIO.write(newOffscreenBuffer, s, outputStream);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    protected void paintBufferToScreen(final Graphics graphics) {
        synchronized (this) {
            graphics.drawImage(this.m_offscreen, 0, 0, null);
        }
    }
    
    public void repaintImmediate() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null && this.m_offscreen != null) {
            this.paintBufferToScreen(graphics);
        }
    }
    
    protected void prepareGraphics(final Graphics2D renderingHints) {
        if (this.m_transform != null) {
            renderingHints.transform(this.m_transform);
        }
        this.setRenderingHints(renderingHints);
    }
    
    protected void setRenderingHints(final Graphics2D graphics2D) {
        if (this.m_highQuality) {
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        else {
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.m_offscreen == null) {
            this.m_offscreen = this.getNewOffscreenBuffer(this.getWidth(), this.getHeight());
            this.damageReport();
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Graphics2D graphics2D2 = (Graphics2D)this.m_offscreen.getGraphics();
        this.paintDisplay(graphics2D2, this.getSize());
        this.paintBufferToScreen(graphics2D);
        this.firePostPaint(graphics2D);
        graphics2D2.dispose();
        ++this.nframes;
        if (this.mark < 0L) {
            this.mark = System.currentTimeMillis();
            this.nframes = 0;
        }
        else if (this.nframes == this.sampleInterval) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.frameRate = 1000.0 * this.nframes / (currentTimeMillis - this.mark);
            this.mark = currentTimeMillis;
            this.nframes = 0;
        }
    }
    
    public void paintDisplay(final Graphics2D graphics2D, final Dimension dimension) {
        synchronized (this.m_vis) {
            synchronized (this) {
                if (this.m_clip.isEmpty()) {
                    return;
                }
                this.m_screen.setClip(0.0, 0.0, dimension.width + 1, dimension.height + 1);
                this.m_screen.transform(this.m_itransform);
                final double n = 1.0 + 1.0 / this.getScale();
                if (this.m_damageRedraw) {
                    if (this.m_clip.isInvalid()) {
                        this.m_clip.setClip(this.m_screen);
                    }
                    else {
                        this.m_clip.intersection(this.m_screen);
                    }
                    this.m_clip.expand(n);
                    this.prepareGraphics(graphics2D);
                    this.m_rclip.setFrameFromDiagonal(this.m_clip.getMinX(), this.m_clip.getMinY(), this.m_clip.getMaxX(), this.m_clip.getMaxY());
                    graphics2D.setClip(this.m_rclip);
                    this.m_rclip.setFrameFromDiagonal(this.m_clip.getMinX() - n, this.m_clip.getMinY() - n, this.m_clip.getMaxX() + n, this.m_clip.getMaxY() + n);
                }
                else {
                    this.m_rclip.setFrame(0.0, 0.0, this.getWidth(), this.getHeight());
                    this.m_clip.setClip(this.m_screen);
                    this.prepareGraphics(graphics2D);
                }
                this.clearRegion(graphics2D, this.m_rclip);
                this.getItemBounds(this.m_rclip);
                this.m_bounds.reset();
                this.m_queue.clear();
                final Iterator items = this.m_vis.items(this.m_predicate);
                this.m_visibleCount = 0;
                while (items.hasNext()) {
                    final VisualItem visualItem = items.next();
                    final Rectangle2D bounds = visualItem.getBounds();
                    this.m_bounds.union(bounds);
                    if (this.m_clip.intersects(bounds, n)) {
                        this.m_queue.addToRenderQueue(visualItem);
                    }
                    if (visualItem.isInteractive()) {
                        this.m_queue.addToPickingQueue(visualItem);
                    }
                    ++this.m_visibleCount;
                }
                this.m_queue.sortRenderQueue();
                for (int i = 0; i < this.m_queue.rsize; ++i) {
                    this.m_queue.ritems[i].render(graphics2D);
                }
                if (this.m_damageRedraw) {
                    this.m_clip.reset();
                }
                this.checkItemBoundsChanged(this.m_rclip);
            }
        }
    }
    
    public void renderImmediate(final VisualItem visualItem) {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        this.prepareGraphics(graphics2D);
        visualItem.render(graphics2D);
    }
    
    protected void printComponent(final Graphics graphics) {
        final boolean highQuality = this.m_highQuality;
        try {
            this.m_highQuality = true;
            this.paintDisplay((Graphics2D)graphics, this.getSize());
        }
        finally {
            this.m_highQuality = highQuality;
        }
    }
    
    protected void clearRegion(final Graphics2D graphics2D, final Rectangle2D rectangle2D) {
        graphics2D.setColor(this.getBackground());
        graphics2D.fill(rectangle2D);
        this.firePrePaint(graphics2D);
    }
    
    public synchronized void setTransform(final AffineTransform transform) throws NoninvertibleTransformException {
        this.damageReport();
        this.m_transform = transform;
        this.m_itransform = this.m_transform.createInverse();
    }
    
    public AffineTransform getTransform() {
        return this.m_transform;
    }
    
    public AffineTransform getInverseTransform() {
        return this.m_itransform;
    }
    
    public Point2D getAbsoluteCoordinate(final Point2D point2D, final Point2D point2D2) {
        return this.m_itransform.transform(point2D, point2D2);
    }
    
    public double getScale() {
        return this.m_transform.getScaleX();
    }
    
    public double getDisplayX() {
        return -this.m_transform.getTranslateX();
    }
    
    public double getDisplayY() {
        return -this.m_transform.getTranslateY();
    }
    
    public synchronized void pan(final double n, final double n2) {
        this.panAbs(n / this.m_transform.getScaleX(), n2 / this.m_transform.getScaleY());
    }
    
    public synchronized void panAbs(final double n, final double n2) {
        this.damageReport();
        this.m_transform.translate(n, n2);
        try {
            this.m_itransform = this.m_transform.createInverse();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void panTo(final Point2D point2D) {
        this.m_itransform.transform(point2D, this.m_tmpPoint);
        this.panToAbs(this.m_tmpPoint);
    }
    
    public synchronized void panToAbs(final Point2D point2D) {
        final double scaleX = this.m_transform.getScaleX();
        final double scaleY = this.m_transform.getScaleY();
        final double x = point2D.getX();
        final double n = Double.isNaN(x) ? 0.0 : x;
        final double y = point2D.getY();
        final double n2 = Double.isNaN(y) ? 0.0 : y;
        final double n3 = this.getWidth() / (2.0 * scaleX) - n;
        final double n4 = this.getHeight() / (2.0 * scaleY) - n2;
        final double n5 = n3 - this.m_transform.getTranslateX() / scaleX;
        final double n6 = n4 - this.m_transform.getTranslateY() / scaleY;
        this.damageReport();
        this.m_transform.translate(n5, n6);
        try {
            this.m_itransform = this.m_transform.createInverse();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void zoom(final Point2D point2D, final double n) {
        this.m_itransform.transform(point2D, this.m_tmpPoint);
        this.zoomAbs(this.m_tmpPoint, n);
    }
    
    public synchronized void zoomAbs(final Point2D point2D, final double n) {
        final double x = point2D.getX();
        final double y = point2D.getY();
        this.damageReport();
        this.m_transform.translate(x, y);
        this.m_transform.scale(n, n);
        this.m_transform.translate(-x, -y);
        try {
            this.m_itransform = this.m_transform.createInverse();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void rotate(final Point2D point2D, final double n) {
        this.m_itransform.transform(point2D, this.m_tmpPoint);
        this.rotateAbs(this.m_tmpPoint, n);
    }
    
    public synchronized void rotateAbs(final Point2D point2D, final double n) {
        final double x = point2D.getX();
        final double y = point2D.getY();
        this.damageReport();
        this.m_transform.translate(x, y);
        this.m_transform.rotate(n);
        this.m_transform.translate(-x, -y);
        try {
            this.m_itransform = this.m_transform.createInverse();
        }
        catch (Exception ex) {}
    }
    
    public synchronized void animatePan(final double n, final double n2, final long n3) {
        this.animatePanAbs(n / this.m_transform.getScaleX(), n2 / this.m_transform.getScaleY(), n3);
    }
    
    public synchronized void animatePanAbs(final double n, final double n2, final long n3) {
        this.m_transact.pan(n, n2, n3);
    }
    
    public synchronized void animatePanTo(final Point2D point2D, final long n) {
        final Point2D.Double double1 = new Point2D.Double();
        this.m_itransform.transform(point2D, double1);
        this.animatePanToAbs(double1, n);
    }
    
    public synchronized void animatePanToAbs(final Point2D point2D, final long n) {
        this.m_tmpPoint.setLocation(0.0, 0.0);
        this.m_itransform.transform(this.m_tmpPoint, this.m_tmpPoint);
        final double x = point2D.getX();
        final double n2 = Double.isNaN(x) ? 0.0 : x;
        final double y = point2D.getY();
        this.animatePanAbs(this.getWidth() / (2.0 * this.m_transform.getScaleX()) - n2 + this.m_tmpPoint.getX(), this.getHeight() / (2.0 * this.m_transform.getScaleY()) - (Double.isNaN(y) ? 0.0 : y) + this.m_tmpPoint.getY(), n);
    }
    
    public synchronized void animateZoom(final Point2D point2D, final double n, final long n2) {
        final Point2D.Double double1 = new Point2D.Double();
        this.m_itransform.transform(point2D, double1);
        this.animateZoomAbs(double1, n, n2);
    }
    
    public synchronized void animateZoomAbs(final Point2D point2D, final double n, final long n2) {
        this.m_transact.zoom(point2D, n, n2);
    }
    
    public synchronized void animatePanAndZoomTo(final Point2D point2D, final double n, final long n2) {
        final Point2D.Double double1 = new Point2D.Double();
        this.m_itransform.transform(point2D, double1);
        this.animatePanAndZoomToAbs(double1, n, n2);
    }
    
    public synchronized void animatePanAndZoomToAbs(final Point2D point2D, final double n, final long n2) {
        this.m_transact.panAndZoom(point2D, n, n2);
    }
    
    public boolean isTranformInProgress() {
        return this.m_transact.isRunning();
    }
    
    public void addPaintListener(final PaintListener paintListener) {
        if (this.m_painters == null) {
            this.m_painters = new CopyOnWriteArrayList();
        }
        this.m_painters.add(paintListener);
    }
    
    public void removePaintListener(final PaintListener paintListener) {
        this.m_painters.remove(paintListener);
    }
    
    protected void firePrePaint(final Graphics2D graphics2D) {
        if (this.m_painters != null && this.m_painters.size() > 0) {
            final Object[] array = this.m_painters.getArray();
            for (int i = 0; i < array.length; ++i) {
                try {
                    ((PaintListener)array[i]).prePaint(this, graphics2D);
                }
                catch (Exception ex) {
                    Display.s_logger.warning("Exception thrown by PaintListener: " + ex + "\n" + StringLib.getStackTrace(ex));
                }
            }
        }
    }
    
    protected void firePostPaint(final Graphics2D graphics2D) {
        if (this.m_painters != null && this.m_painters.size() > 0) {
            final Object[] array = this.m_painters.getArray();
            for (int i = 0; i < array.length; ++i) {
                try {
                    ((PaintListener)array[i]).postPaint(this, graphics2D);
                }
                catch (Exception ex) {
                    Display.s_logger.warning("Exception thrown by PaintListener: " + ex + "\n" + StringLib.getStackTrace(ex));
                }
            }
        }
    }
    
    public void addItemBoundsListener(final ItemBoundsListener itemBoundsListener) {
        if (this.m_bounders == null) {
            this.m_bounders = new CopyOnWriteArrayList();
        }
        this.m_bounders.add(itemBoundsListener);
    }
    
    public void removeItemBoundsListener(final ItemBoundsListener itemBoundsListener) {
        this.m_bounders.remove(itemBoundsListener);
    }
    
    protected void checkItemBoundsChanged(final Rectangle2D rectangle2D) {
        if (this.m_bounds.equals(rectangle2D)) {
            return;
        }
        if (this.m_bounders != null && this.m_bounders.size() > 0) {
            final Object[] array = this.m_bounders.getArray();
            for (int i = 0; i < array.length; ++i) {
                try {
                    ((ItemBoundsListener)array[i]).itemBoundsChanged(this);
                }
                catch (Exception ex) {
                    Display.s_logger.warning("Exception thrown by ItemBoundsListener: " + ex + "\n" + StringLib.getStackTrace(ex));
                }
            }
        }
    }
    
    public void addControlListener(final Control control) {
        this.m_controls.add(control);
    }
    
    public void removeControlListener(final Control control) {
        this.m_controls.remove(control);
    }
    
    public synchronized VisualItem findItem(final Point point) {
        final Point2D point2D = (this.m_itransform == null) ? point : this.m_itransform.transform(point, this.m_tmpPoint);
        if (!this.m_queue.psorted) {
            this.m_queue.sortPickingQueue();
        }
        int psize = this.m_queue.psize;
        while (--psize >= 0) {
            final VisualItem visualItem = this.m_queue.pitems[psize];
            if (!visualItem.isValid()) {
                continue;
            }
            final Renderer renderer = visualItem.getRenderer();
            if (renderer != null && visualItem.isInteractive() && renderer.locatePoint(point2D, visualItem)) {
                return visualItem;
            }
        }
        return null;
    }
    
    public JTextComponent getTextEditor() {
        return this.m_editor;
    }
    
    public void setTextEditor(final JTextComponent editor) {
        this.remove(this.m_editor);
        this.add(this.m_editor = editor, 1);
    }
    
    public void editText(final VisualItem visualItem, final String s) {
        if (this.m_editing) {
            this.stopEditing();
        }
        final Rectangle bounds = this.m_transform.createTransformedShape(visualItem.getBounds()).getBounds();
        if (this.m_editor instanceof JTextArea) {
            final Rectangle rectangle = bounds;
            rectangle.y -= 2;
            final Rectangle rectangle2 = bounds;
            rectangle2.width += 22;
            final Rectangle rectangle3 = bounds;
            rectangle3.height += 2;
        }
        else {
            final Rectangle rectangle4 = bounds;
            rectangle4.x += 3;
            final Rectangle rectangle5 = bounds;
            ++rectangle5.y;
            final Rectangle rectangle6 = bounds;
            rectangle6.width -= 5;
            final Rectangle rectangle7 = bounds;
            rectangle7.height -= 2;
        }
        final Font font = this.getFont();
        this.m_editor.setFont(new Font(font.getFontName(), font.getStyle(), (int)Math.round(font.getSize() * this.m_transform.getScaleX())));
        this.editText(visualItem, s, bounds);
    }
    
    public void editText(final VisualItem editItem, final String editAttribute, final Rectangle rectangle) {
        if (this.m_editing) {
            this.stopEditing();
        }
        final String string = editItem.getString(editAttribute);
        this.m_editItem = editItem;
        this.m_editAttribute = editAttribute;
        final Color color = ColorLib.getColor(editItem.getTextColor());
        final Color color2 = ColorLib.getColor(editItem.getFillColor());
        this.m_editor.setForeground(color);
        this.m_editor.setBackground(color2);
        this.editText(string, rectangle);
    }
    
    public void editText(final String text, final Rectangle rectangle) {
        if (this.m_editing) {
            this.stopEditing();
        }
        this.m_editing = true;
        this.m_editor.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        this.m_editor.setText(text);
        this.m_editor.setVisible(true);
        this.m_editor.setCaretPosition(text.length());
        this.m_editor.requestFocus();
    }
    
    public void stopEditing() {
        this.m_editor.setVisible(false);
        if (this.m_editItem != null) {
            this.m_editItem.set(this.m_editAttribute, this.m_editor.getText());
            this.m_editItem = null;
            this.m_editAttribute = null;
            this.m_editor.setBackground(null);
            this.m_editor.setForeground(null);
        }
        this.m_editing = false;
    }
    
    static {
        s_logger = Logger.getLogger(Display.class.getName());
    }
    
    public class InputEventCapturer implements MouseMotionListener, MouseWheelListener, MouseListener, KeyListener
    {
        private VisualItem activeItem;
        private boolean mouseDown;
        
        public InputEventCapturer() {
            this.activeItem = null;
            this.mouseDown = false;
        }
        
        private boolean validityCheck() {
            if (this.activeItem.isValid()) {
                return true;
            }
            this.activeItem = null;
            return false;
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemDragged(this.activeItem, mouseEvent);
                    }
                }
                else {
                    this.fireMouseDragged(mouseEvent);
                }
            }
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                boolean b = false;
                final VisualItem item = Display.this.findItem(mouseEvent.getPoint());
                if (this.activeItem != null && this.activeItem != item) {
                    if (this.validityCheck()) {
                        this.fireItemExited(this.activeItem, mouseEvent);
                    }
                    b = true;
                }
                if (item != null && item != this.activeItem) {
                    this.fireItemEntered(item, mouseEvent);
                    b = true;
                }
                this.activeItem = item;
                if (b) {
                    return;
                }
                if (item != null && item == this.activeItem) {
                    this.fireItemMoved(item, mouseEvent);
                }
                if (item == null) {
                    this.fireMouseMoved(mouseEvent);
                }
            }
        }
        
        public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemWheelMoved(this.activeItem, mouseWheelEvent);
                    }
                }
                else {
                    this.fireMouseWheelMoved(mouseWheelEvent);
                }
            }
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemClicked(this.activeItem, mouseEvent);
                    }
                }
                else {
                    this.fireMouseClicked(mouseEvent);
                }
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                this.mouseDown = true;
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemPressed(this.activeItem, mouseEvent);
                    }
                }
                else {
                    this.fireMousePressed(mouseEvent);
                }
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemReleased(this.activeItem, mouseEvent);
                    }
                }
                else {
                    this.fireMouseReleased(mouseEvent);
                }
                if (this.activeItem != null && this.mouseDown && this.isOffComponent(mouseEvent)) {
                    this.fireItemExited(this.activeItem, mouseEvent);
                    this.activeItem = null;
                }
                this.mouseDown = false;
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                this.fireMouseEntered(mouseEvent);
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            synchronized (Display.this.m_vis) {
                if (!this.mouseDown && this.activeItem != null) {
                    this.fireItemExited(this.activeItem, mouseEvent);
                    this.activeItem = null;
                }
                this.fireMouseExited(mouseEvent);
            }
        }
        
        public void keyPressed(final KeyEvent keyEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemKeyPressed(this.activeItem, keyEvent);
                    }
                }
                else {
                    this.fireKeyPressed(keyEvent);
                }
            }
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemKeyReleased(this.activeItem, keyEvent);
                    }
                }
                else {
                    this.fireKeyReleased(keyEvent);
                }
            }
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
            synchronized (Display.this.m_vis) {
                if (this.activeItem != null) {
                    if (this.validityCheck()) {
                        this.fireItemKeyTyped(this.activeItem, keyEvent);
                    }
                }
                else {
                    this.fireKeyTyped(keyEvent);
                }
            }
        }
        
        private boolean isOffComponent(final MouseEvent mouseEvent) {
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            return x < 0 || x > Display.this.getWidth() || y < 0 || y > Display.this.getHeight();
        }
        
        private void fireItemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemDragged(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemMoved(final VisualItem visualItem, final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemMoved(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemWheelMoved(final VisualItem visualItem, final MouseWheelEvent mouseWheelEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemWheelMoved(visualItem, mouseWheelEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemClicked(final VisualItem visualItem, final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemClicked(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemPressed(final VisualItem visualItem, final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemPressed(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemReleased(final VisualItem visualItem, final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemReleased(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
            visualItem.setHover(true);
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemEntered(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
            if (visualItem.isValid()) {
                visualItem.setHover(false);
            }
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemExited(visualItem, mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemKeyPressed(final VisualItem visualItem, final KeyEvent keyEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            if (array.length == 0) {
                return;
            }
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemKeyPressed(visualItem, keyEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemKeyReleased(final VisualItem visualItem, final KeyEvent keyEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemKeyReleased(visualItem, keyEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireItemKeyTyped(final VisualItem visualItem, final KeyEvent keyEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.itemKeyTyped(visualItem, keyEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseEntered(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseEntered(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseExited(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseExited(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMousePressed(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mousePressed(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseReleased(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseReleased(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseClicked(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseClicked(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseDragged(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseDragged(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseMoved(final MouseEvent mouseEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseMoved(mouseEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireMouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.mouseWheelMoved(mouseWheelEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireKeyPressed(final KeyEvent keyEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.keyPressed(keyEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireKeyReleased(final KeyEvent keyEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.keyReleased(keyEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
        
        private void fireKeyTyped(final KeyEvent keyEvent) {
            final Object[] array = Display.this.m_controls.getArray();
            for (int i = 0; i < array.length; ++i) {
                final Control control = (Control)array[i];
                if (control.isEnabled()) {
                    try {
                        control.keyTyped(keyEvent);
                    }
                    catch (Exception ex) {
                        Display.s_logger.warning("Exception thrown by Control: " + ex + "\n" + StringLib.getStackTrace(ex));
                    }
                }
            }
        }
    }
    
    private class TransformActivity extends Activity
    {
        private double[] src;
        private double[] dst;
        private AffineTransform m_at;
        
        public TransformActivity() {
            super(2000L, 20L, 0L);
            this.src = new double[6];
            this.dst = new double[6];
            this.m_at = new AffineTransform();
            this.setPacingFunction(new SlowInSlowOutPacer());
        }
        
        private AffineTransform getTransform() {
            if (this.isScheduled()) {
                this.m_at.setTransform(this.dst[0], this.dst[1], this.dst[2], this.dst[3], this.dst[4], this.dst[5]);
            }
            else {
                this.m_at.setTransform(Display.this.m_transform);
            }
            return this.m_at;
        }
        
        public void panAndZoom(final Point2D point2D, final double n, final long duration) {
            final AffineTransform transform = this.getTransform();
            this.cancel();
            this.setDuration(duration);
            Display.this.m_tmpPoint.setLocation(0.0, 0.0);
            Display.this.m_itransform.transform(Display.this.m_tmpPoint, Display.this.m_tmpPoint);
            final double x = point2D.getX();
            final double n2 = Double.isNaN(x) ? 0.0 : x;
            final double y = point2D.getY();
            transform.translate(Display.this.getWidth() / (2.0 * Display.this.m_transform.getScaleX()) - n2 + Display.this.m_tmpPoint.getX(), Display.this.getHeight() / (2.0 * Display.this.m_transform.getScaleY()) - (Double.isNaN(y) ? 0.0 : y) + Display.this.m_tmpPoint.getY());
            transform.translate(point2D.getX(), point2D.getY());
            transform.scale(n, n);
            transform.translate(-point2D.getX(), -point2D.getY());
            transform.getMatrix(this.dst);
            Display.this.m_transform.getMatrix(this.src);
            this.run();
        }
        
        public void pan(final double n, final double n2, final long duration) {
            final AffineTransform transform = this.getTransform();
            this.cancel();
            this.setDuration(duration);
            transform.translate(n, n2);
            transform.getMatrix(this.dst);
            Display.this.m_transform.getMatrix(this.src);
            this.run();
        }
        
        public void zoom(final Point2D point2D, final double n, final long duration) {
            final AffineTransform transform = this.getTransform();
            this.cancel();
            this.setDuration(duration);
            final double x = point2D.getX();
            final double y = point2D.getY();
            transform.translate(x, y);
            transform.scale(n, n);
            transform.translate(-x, -y);
            transform.getMatrix(this.dst);
            Display.this.m_transform.getMatrix(this.src);
            this.run();
        }
        
        protected void run(final long n) {
            final double pace = this.getPace(n);
            Display.this.damageReport();
            Display.this.m_transform.setTransform(this.src[0] + pace * (this.dst[0] - this.src[0]), this.src[1] + pace * (this.dst[1] - this.src[1]), this.src[2] + pace * (this.dst[2] - this.src[2]), this.src[3] + pace * (this.dst[3] - this.src[3]), this.src[4] + pace * (this.dst[4] - this.src[4]), this.src[5] + pace * (this.dst[5] - this.src[5]));
            try {
                Display.this.m_itransform = Display.this.m_transform.createInverse();
            }
            catch (Exception ex) {}
            Display.this.repaint();
        }
    }
}
