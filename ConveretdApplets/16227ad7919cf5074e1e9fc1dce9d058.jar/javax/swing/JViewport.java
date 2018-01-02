// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.awt.event.ComponentEvent;
import java.io.Serializable;
import java.awt.event.ComponentAdapter;
import java.applet.Applet;
import javax.swing.border.Border;
import java.awt.image.ImageObserver;
import java.awt.Insets;
import java.awt.Shape;
import javax.accessibility.AccessibleContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.Component;
import java.util.EventListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentListener;
import java.awt.Image;
import java.awt.Point;
import javax.accessibility.Accessible;

public class JViewport extends JComponent implements Accessible
{
    static final Object EnableWindowBlit;
    protected boolean isViewSizeSet;
    protected Point lastPaintPosition;
    protected boolean backingStore;
    protected transient Image backingStoreImage;
    protected boolean scrollUnderway;
    private ComponentListener viewListener;
    private transient ChangeEvent changeEvent;
    private transient boolean windowBlit;
    private transient boolean repaintAll;
    private transient boolean waitingForRepaint;
    private transient Timer repaintTimer;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    static {
        EnableWindowBlit = "EnableWindowBlit";
    }
    
    public JViewport() {
        this.isViewSizeSet = false;
        this.lastPaintPosition = null;
        this.backingStore = false;
        this.backingStoreImage = null;
        this.scrollUnderway = false;
        this.viewListener = null;
        this.changeEvent = null;
        this.setLayout(this.createLayoutManager());
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        super.listenerList.add((JViewport.class$javax$swing$event$ChangeListener != null) ? JViewport.class$javax$swing$event$ChangeListener : (JViewport.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    protected void addImpl(final Component view, final Object o, final int n) {
        this.setView(view);
    }
    
    private void blitWindowGraphics(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        Container container;
        for (container = this.getParent(); !(container instanceof Window); container = container.getParent()) {}
        final Graphics graphics = container.getGraphics();
        final Rectangle convertRectangle = SwingUtilities.convertRectangle(this, new Rectangle(n, n2, n3, n4), container);
        graphics.copyArea(convertRectangle.x, convertRectangle.y, convertRectangle.width, convertRectangle.height, n5, n6);
    }
    
    private boolean canUseWindowBlitter() {
        if (!(this.getParent() instanceof JComponent) && !(this.getView() instanceof JComponent)) {
            return false;
        }
        final Rectangle bounds = new Rectangle(0, 0, this.getWidth(), this.getHeight());
        final Rectangle rectangle = new Rectangle();
        Component component = null;
        Container parent;
        for (parent = this; !(parent instanceof Window) && parent != null; parent = parent.getParent()) {
            Rectangle rectangle2;
            if (parent instanceof JComponent) {
                rectangle2 = ((JViewport)parent)._bounds;
            }
            else {
                rectangle2 = parent.getBounds();
            }
            rectangle.setBounds(bounds);
            SwingUtilities.computeIntersection(0, 0, rectangle2.width, rectangle2.height, bounds);
            if (!bounds.equals(rectangle)) {
                return false;
            }
            Label_0244: {
                if (component != null && parent instanceof JComponent && !((JViewport)parent).isOptimizedDrawingEnabled()) {
                    final Component[] components = parent.getComponents();
                    int i = 0;
                    while (true) {
                        for (int j = components.length - 1; j >= 0; --j) {
                            if (components[j] == component) {
                                i = j - 1;
                                while (i >= 0) {
                                    Rectangle rectangle3;
                                    if (components[i] instanceof JComponent) {
                                        rectangle3 = ((JComponent)components[i])._bounds;
                                    }
                                    else {
                                        rectangle3 = components[i].getBounds();
                                    }
                                    if (rectangle3.intersects(bounds)) {
                                        return false;
                                    }
                                    --i;
                                }
                                break Label_0244;
                            }
                        }
                        continue;
                    }
                }
            }
            final Rectangle rectangle4 = bounds;
            rectangle4.x += rectangle2.x;
            final Rectangle rectangle5 = bounds;
            rectangle5.y += rectangle2.y;
            component = parent;
        }
        return parent != null;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected boolean computeBlit(final int x, final int y, final Point point, final Point point2, final Dimension dimension, final Rectangle rectangle) {
        final int abs = Math.abs(x);
        final int abs2 = Math.abs(y);
        final Dimension extentSize = this.getExtentSize();
        if (x == 0 && y != 0 && abs2 < extentSize.height) {
            if (y < 0) {
                point.y = -y;
                point2.y = 0;
                rectangle.y = extentSize.height + y;
            }
            else {
                point.y = 0;
                point2.y = y;
                rectangle.y = 0;
            }
            final boolean x2 = false;
            point2.x = (x2 ? 1 : 0);
            point.x = (x2 ? 1 : 0);
            rectangle.x = (x2 ? 1 : 0);
            dimension.width = extentSize.width;
            dimension.height = extentSize.height - abs2;
            rectangle.width = extentSize.width;
            rectangle.height = abs2;
            return true;
        }
        if (y == 0 && x != 0 && abs < extentSize.width) {
            if (x < 0) {
                point.x = -x;
                point2.x = 0;
                rectangle.x = extentSize.width + x;
            }
            else {
                point.x = 0;
                point2.x = x;
                rectangle.x = 0;
            }
            final boolean y2 = false;
            point2.y = (y2 ? 1 : 0);
            point.y = (y2 ? 1 : 0);
            rectangle.y = (y2 ? 1 : 0);
            dimension.width = extentSize.width - abs;
            dimension.height = extentSize.height;
            rectangle.y = 0;
            rectangle.width = abs;
            rectangle.height = extentSize.height;
            return true;
        }
        return false;
    }
    
    protected LayoutManager createLayoutManager() {
        return new ViewportLayout();
    }
    
    private Timer createRepaintTimer() {
        final Timer timer = new Timer(300, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JViewport.this.waitingForRepaint) {
                    JViewport.this.repaint();
                }
            }
        });
        timer.setRepeats(false);
        return timer;
    }
    
    protected ViewListener createViewListener() {
        return new ViewListener();
    }
    
    protected void firePropertyChange(final String s, final Object o, final Object o2) {
        super.firePropertyChange(s, o, o2);
        if (s.equals("EnableWindowBlit")) {
            this.windowBlit = (o2 != null);
        }
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JViewport.class$javax$swing$event$ChangeListener != null) ? JViewport.class$javax$swing$event$ChangeListener : (JViewport.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    private void flushViewDirtyRegion(final Graphics graphics) {
        final RepaintManager currentManager = RepaintManager.currentManager(this);
        final JComponent component = (JComponent)this.getView();
        final Rectangle dirtyRegion = currentManager.getDirtyRegion((JComponent)this.getView());
        if (dirtyRegion != null && dirtyRegion.width > 0 && dirtyRegion.height > 0) {
            final Rectangle rectangle = dirtyRegion;
            rectangle.x += component.getX();
            final Rectangle rectangle2 = dirtyRegion;
            rectangle2.y += component.getY();
            if (graphics.getClipBounds() == null) {
                graphics.setClip(0, 0, this.getWidth(), this.getHeight());
            }
            graphics.clipRect(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width, dirtyRegion.height);
            this.paintView(graphics);
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJViewport();
        }
        return super.accessibleContext;
    }
    
    private Graphics getBackingStoreGraphics(final Graphics graphics) {
        final Graphics graphics2 = this.backingStoreImage.getGraphics();
        graphics2.setColor(graphics.getColor());
        graphics2.setFont(graphics.getFont());
        graphics2.setClip(graphics.getClipBounds());
        return graphics2;
    }
    
    public Dimension getExtentSize() {
        return this.getSize();
    }
    
    public final Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }
    
    public final Insets getInsets(final Insets insets) {
        final boolean b = false;
        insets.bottom = (b ? 1 : 0);
        insets.right = (b ? 1 : 0);
        insets.top = (b ? 1 : 0);
        insets.left = (b ? 1 : 0);
        return insets;
    }
    
    public Component getView() {
        return (this.getComponentCount() > 0) ? this.getComponent(0) : null;
    }
    
    private Point getViewLocation() {
        final Component view = this.getView();
        if (view != null) {
            return view.getLocation();
        }
        return new Point(0, 0);
    }
    
    public Point getViewPosition() {
        final Component view = this.getView();
        if (view != null) {
            final Point location = view.getLocation();
            location.x = -location.x;
            location.y = -location.y;
            return location;
        }
        return new Point(0, 0);
    }
    
    public Rectangle getViewRect() {
        return new Rectangle(this.getViewPosition(), this.getExtentSize());
    }
    
    public Dimension getViewSize() {
        final Component view = this.getView();
        if (view == null) {
            return new Dimension(0, 0);
        }
        if (this.isViewSizeSet) {
            return view.getSize();
        }
        return view.getPreferredSize();
    }
    
    public boolean isBackingStoreEnabled() {
        return this.backingStore;
    }
    
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        if (this.repaintAll) {
            this.repaintAll = false;
            final Rectangle clipBounds = graphics.getClipBounds();
            if (clipBounds.width < this.getWidth() || clipBounds.height < this.getHeight()) {
                this.waitingForRepaint = true;
                if (this.repaintTimer == null) {
                    this.repaintTimer = this.createRepaintTimer();
                }
                this.repaintTimer.stop();
                this.repaintTimer.start();
            }
            else {
                if (this.repaintTimer != null) {
                    this.repaintTimer.stop();
                }
                this.waitingForRepaint = false;
            }
        }
        else if (this.waitingForRepaint) {
            final Rectangle clipBounds2 = graphics.getClipBounds();
            if (clipBounds2.width >= this.getWidth() && clipBounds2.height >= this.getHeight()) {
                this.waitingForRepaint = false;
                this.repaintTimer.stop();
            }
        }
        if (!this.backingStore || this.windowBlit) {
            super.paint(graphics);
            this.lastPaintPosition = this.getViewLocation();
            return;
        }
        final Rectangle bounds = this.getView().getBounds();
        graphics.clipRect(0, 0, bounds.width, bounds.height);
        if (this.backingStoreImage == null) {
            this.backingStoreImage = this.createImage(width, height);
            final Rectangle clipBounds3 = graphics.getClipBounds();
            if (clipBounds3.width != width || clipBounds3.height != height) {
                graphics.setClip(0, 0, Math.min(bounds.width, width), Math.min(bounds.height, height));
                this.paintViaBackingStore(graphics, clipBounds3);
            }
            else {
                this.paintViaBackingStore(graphics);
            }
        }
        else if (!this.scrollUnderway || this.lastPaintPosition.equals(this.getViewLocation())) {
            this.paintViaBackingStore(graphics);
        }
        else {
            final Point point = new Point();
            final Point point2 = new Point();
            final Dimension dimension = new Dimension();
            final Rectangle rectangle = new Rectangle();
            final Point viewLocation = this.getViewLocation();
            if (!this.computeBlit(viewLocation.x - this.lastPaintPosition.x, viewLocation.y - this.lastPaintPosition.y, point, point2, dimension, rectangle)) {
                this.paintViaBackingStore(graphics);
            }
            else {
                final int n = point2.x - point.x;
                final int n2 = point2.y - point.y;
                final Rectangle clipBounds4 = graphics.getClipBounds();
                graphics.setClip(0, 0, width, height);
                final Graphics backingStoreGraphics = this.getBackingStoreGraphics(graphics);
                backingStoreGraphics.copyArea(point.x, point.y, dimension.width, dimension.height, n, n2);
                graphics.setClip(clipBounds4.x, clipBounds4.y, clipBounds4.width, clipBounds4.height);
                backingStoreGraphics.setClip(bounds.intersection(rectangle));
                super.paint(backingStoreGraphics);
                graphics.drawImage(this.backingStoreImage, 0, 0, this);
            }
        }
        this.lastPaintPosition = this.getViewLocation();
        this.scrollUnderway = false;
    }
    
    private void paintViaBackingStore(final Graphics graphics) {
        super.paint(this.getBackingStoreGraphics(graphics));
        graphics.drawImage(this.backingStoreImage, 0, 0, this);
    }
    
    private void paintViaBackingStore(final Graphics graphics, final Rectangle clip) {
        super.paint(this.getBackingStoreGraphics(graphics));
        graphics.setClip(clip);
        graphics.drawImage(this.backingStoreImage, 0, 0, this);
    }
    
    private void paintView(final Graphics graphics) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final RepaintManager currentManager = RepaintManager.currentManager(this);
        final boolean doubleBufferingEnabled = currentManager.isDoubleBufferingEnabled();
        final JComponent component = (JComponent)this.getView();
        final Rectangle rectangle = clipBounds;
        rectangle.x -= component.getX();
        final Rectangle rectangle2 = clipBounds;
        rectangle2.y -= component.getY();
        final Image offscreenBuffer = currentManager.getOffscreenBuffer(this, clipBounds.width, clipBounds.height);
        final Graphics graphics2 = offscreenBuffer.getGraphics();
        graphics2.translate(-clipBounds.x, -clipBounds.y);
        graphics2.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        currentManager.setDoubleBufferingEnabled(false);
        component.paint(graphics2);
        if (doubleBufferingEnabled) {
            currentManager.setDoubleBufferingEnabled(true);
        }
        graphics.drawImage(offscreenBuffer, clipBounds.x + component.getX(), clipBounds.y + component.getY(), null);
        graphics2.dispose();
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",backingStore=" + (this.backingStore ? "true" : "false") + ",backingStoreImage=" + ((this.backingStoreImage != null) ? this.backingStoreImage.toString() : "") + ",isViewSizeSet=" + (this.isViewSizeSet ? "true" : "false") + ",lastPaintPosition=" + ((this.lastPaintPosition != null) ? this.lastPaintPosition.toString() : "") + ",scrollUnderway=" + (this.scrollUnderway ? "true" : "false");
    }
    
    private int positionAdjustment(final int n, final int n2, final int n3) {
        if (n3 >= 0 && n2 + n3 <= n) {
            return 0;
        }
        if (n3 <= 0 && n2 + n3 >= n) {
            return 0;
        }
        if (n3 > 0 && n2 <= n) {
            return -n3 + n - n2;
        }
        if (n3 >= 0 && n2 >= n) {
            return -n3;
        }
        if (n3 <= 0 && n2 <= n) {
            return -n3;
        }
        if (n3 < 0 && n2 >= n) {
            return -n3 + n - n2;
        }
        return 0;
    }
    
    public void remove(final Component component) {
        component.removeComponentListener(this.viewListener);
        super.remove(component);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        super.listenerList.remove((JViewport.class$javax$swing$event$ChangeListener != null) ? JViewport.class$javax$swing$event$ChangeListener : (JViewport.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void repaint(final long n, final int n2, final int n3, final int n4, final int n5) {
        final Container parent = this.getParent();
        if (parent != null) {
            parent.repaint(n, n2 + this.getX(), n3 + this.getY(), n4, n5);
        }
        else {
            super.repaint(n, n2, n3, n4, n5);
        }
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        final boolean b = this.getWidth() != n3 || this.getHeight() != n4;
        if (b) {
            this.backingStoreImage = null;
        }
        super.reshape(n, n2, n3, n4);
        if (b) {
            this.fireStateChanged();
        }
    }
    
    public void scrollRectToVisible(final Rectangle rectangle) {
        final Component view = this.getView();
        if (view == null) {
            return;
        }
        if (!view.isValid()) {
            this.validateView();
        }
        final Rectangle bounds = this.getBounds();
        final int positionAdjustment = this.positionAdjustment(bounds.width, rectangle.width, rectangle.x);
        final int positionAdjustment2 = this.positionAdjustment(bounds.height, rectangle.height, rectangle.y);
        if (positionAdjustment != 0 || positionAdjustment2 != 0) {
            final Point viewPosition = this.getViewPosition();
            this.setViewPosition(new Point(viewPosition.x - positionAdjustment, viewPosition.y - positionAdjustment2));
            this.scrollUnderway = false;
        }
    }
    
    public void setBackingStoreEnabled(final boolean backingStore) {
        this.backingStore = backingStore;
    }
    
    public final void setBorder(final Border border) {
        if (border != null) {
            throw new IllegalArgumentException("JViewport.setBorder() not supported");
        }
    }
    
    public void setExtentSize(final Dimension size) {
        if (!size.equals(this.getExtentSize())) {
            this.setSize(size);
            this.fireStateChanged();
        }
    }
    
    public void setView(final Component component) {
        for (int i = this.getComponentCount() - 1; i >= 0; --i) {
            this.remove(i);
        }
        this.isViewSizeSet = false;
        if (component != null) {
            super.addImpl(component, null, -1);
            component.addComponentListener(this.viewListener = this.createViewListener());
        }
        this.revalidate();
        this.repaint();
    }
    
    public void setViewPosition(final Point point) {
        final Component view = this.getView();
        if (view == null) {
            return;
        }
        final int x = point.x;
        final int y = point.y;
        int n;
        int n2;
        if (view instanceof JComponent) {
            final JComponent component = (JComponent)view;
            n = component.getX();
            n2 = component.getY();
        }
        else {
            final Rectangle bounds = view.getBounds();
            n = bounds.x;
            n2 = bounds.y;
        }
        final int n3 = -x;
        final int n4 = -y;
        if (n != n3 || n2 != n4) {
            if (!this.waitingForRepaint && this.windowBlit && this.canUseWindowBlitter()) {
                final Graphics graphics = this.getGraphics();
                this.flushViewDirtyRegion(graphics);
                view.setLocation(n3, n4);
                graphics.clipRect(0, 0, this.getWidth(), this.getHeight());
                this.repaintAll = this.windowBlitPaint(graphics);
                graphics.dispose();
                final RepaintManager currentManager = RepaintManager.currentManager(this);
                currentManager.markCompletelyClean((JComponent)this.getParent());
                currentManager.markCompletelyClean(this);
                currentManager.markCompletelyClean((JComponent)view);
            }
            else {
                this.scrollUnderway = true;
                view.setLocation(n3, n4);
            }
            this.fireStateChanged();
        }
    }
    
    public void setViewSize(final Dimension size) {
        final Component view = this.getView();
        if (view != null && !size.equals(view.getSize())) {
            this.scrollUnderway = false;
            view.setSize(size);
            this.isViewSizeSet = true;
            this.fireStateChanged();
        }
    }
    
    public Dimension toViewCoordinates(final Dimension dimension) {
        return new Dimension(dimension);
    }
    
    public Point toViewCoordinates(final Point point) {
        return new Point(point);
    }
    
    private void validateView() {
        JComponent component = null;
        for (Container parent = this; parent != null; parent = parent.getParent()) {
            if (parent instanceof CellRendererPane || parent.getPeer() == null) {
                return;
            }
            if (parent instanceof JComponent && ((JViewport)parent).isValidateRoot()) {
                component = (JComponent)parent;
                break;
            }
        }
        if (component == null) {
            return;
        }
        Container container = null;
        for (Container parent2 = component; parent2 != null; parent2 = parent2.getParent()) {
            if (!parent2.isVisible() || parent2.getPeer() == null) {
                return;
            }
            if (parent2 instanceof Window || parent2 instanceof Applet) {
                container = parent2;
                break;
            }
        }
        if (container == null) {
            return;
        }
        component.validate();
        final RepaintManager currentManager = RepaintManager.currentManager(this);
        if (currentManager != null) {
            currentManager.removeInvalidComponent(component);
        }
    }
    
    private boolean windowBlitPaint(final Graphics graphics) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        if (width == 0 || height == 0) {
            return false;
        }
        final RepaintManager currentManager = RepaintManager.currentManager(this);
        final JComponent component = (JComponent)this.getView();
        boolean b;
        if (this.lastPaintPosition == null || this.lastPaintPosition.equals(this.getViewLocation())) {
            this.paintView(graphics);
            b = false;
        }
        else {
            final Point point = new Point();
            final Point point2 = new Point();
            final Dimension dimension = new Dimension();
            final Rectangle rectangle = new Rectangle();
            final Point viewLocation = this.getViewLocation();
            if (!this.computeBlit(viewLocation.x - this.lastPaintPosition.x, viewLocation.y - this.lastPaintPosition.y, point, point2, dimension, rectangle)) {
                this.paintView(graphics);
                b = false;
            }
            else {
                final boolean doubleBufferingEnabled = currentManager.isDoubleBufferingEnabled();
                final int n = point2.x - point.x;
                final int n2 = point2.y - point.y;
                final Rectangle intersection;
                final Rectangle rectangle2 = intersection = component.getBounds().intersection(rectangle);
                intersection.x -= component.getX();
                final Rectangle rectangle3 = rectangle2;
                rectangle3.y -= component.getY();
                final Image offscreenBuffer = currentManager.getOffscreenBuffer(this, this.getWidth(), this.getHeight());
                final Graphics graphics2 = offscreenBuffer.getGraphics();
                graphics2.translate(-rectangle2.x, -rectangle2.y);
                graphics2.setClip(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                currentManager.setDoubleBufferingEnabled(false);
                component.paint(graphics2);
                currentManager.setDoubleBufferingEnabled(doubleBufferingEnabled);
                this.blitWindowGraphics(point.x, point.y, dimension.width, dimension.height, n, n2);
                final Rectangle rectangle4 = rectangle2;
                rectangle4.x += component.getX();
                final Rectangle rectangle5 = rectangle2;
                rectangle5.y += component.getY();
                graphics.setClip(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                graphics.drawImage(offscreenBuffer, rectangle2.x, rectangle2.y, null);
                graphics2.dispose();
                b = true;
            }
        }
        this.lastPaintPosition = this.getViewLocation();
        return b;
    }
    
    protected class ViewListener extends ComponentAdapter implements Serializable
    {
        public void componentResized(final ComponentEvent componentEvent) {
            JViewport.this.fireStateChanged();
        }
    }
    
    protected class AccessibleJViewport extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.VIEWPORT;
        }
    }
}
