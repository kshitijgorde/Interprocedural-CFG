// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.awt.event.MouseAdapter;
import ji.v1event.d8;
import ji.v1event.c9;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import ji.util.d;
import java.awt.Component;
import ji.util.e;
import java.awt.Color;
import ji.v1event.g9;
import java.awt.LayoutManager;
import ji.v1event.bp;
import ji.v1event.br;
import ji.awt.bb;
import ji.awt.c;
import java.awt.Insets;
import ji.graphic.b3;
import ji.v1event.as;
import java.awt.event.KeyListener;
import ji.v1event.ar;
import java.awt.Panel;

public class jiPanel extends Panel implements ar, KeyListener, as
{
    private b3 border;
    private boolean painting;
    private Insets insets;
    private boolean canAccept;
    private boolean released;
    private c fl;
    private c kl;
    private bb mouseTimerThread;
    private boolean stopMousePressedTimer;
    private s0 mouseEmulator;
    private br toolTipProcessor;
    private int mouseRightCLickTime;
    private c ml;
    private c mwl;
    private c tipListeners;
    private boolean rightMouseEmulator;
    private boolean enabled;
    private int lastKey;
    private static int numPanels;
    private String id;
    private boolean ita;
    private bp wheelHandler;
    private String focusId;
    private boolean allowClearInside;
    private String parentId;
    private static Object lockWheel;
    
    public jiPanel(final String s) {
        this.border = new b3();
        this.painting = false;
        this.insets = null;
        this.canAccept = false;
        this.released = false;
        this.fl = null;
        this.kl = null;
        this.mouseTimerThread = null;
        this.stopMousePressedTimer = false;
        this.mouseEmulator = null;
        this.toolTipProcessor = null;
        this.mouseRightCLickTime = 1;
        this.ml = null;
        this.mwl = null;
        this.tipListeners = null;
        this.rightMouseEmulator = false;
        this.enabled = true;
        this.lastKey = 0;
        this.id = null;
        this.ita = false;
        this.wheelHandler = null;
        this.focusId = null;
        this.allowClearInside = true;
        this.parentId = null;
        ++jiPanel.numPanels;
        this.id = "".concat(String.valueOf(String.valueOf(jiPanel.numPanels)));
        this.jbInit(s);
    }
    
    public jiPanel(final String s, final boolean b) {
        this.border = new b3();
        this.painting = false;
        this.insets = null;
        this.canAccept = false;
        this.released = false;
        this.fl = null;
        this.kl = null;
        this.mouseTimerThread = null;
        this.stopMousePressedTimer = false;
        this.mouseEmulator = null;
        this.toolTipProcessor = null;
        this.mouseRightCLickTime = 1;
        this.ml = null;
        this.mwl = null;
        this.tipListeners = null;
        this.rightMouseEmulator = false;
        this.enabled = true;
        this.lastKey = 0;
        this.id = null;
        this.ita = false;
        this.wheelHandler = null;
        this.focusId = null;
        this.allowClearInside = true;
        this.parentId = null;
        this.jbInit(s);
    }
    
    public jiPanel(final String s, final LayoutManager layout) {
        this.border = new b3();
        this.painting = false;
        this.insets = null;
        this.canAccept = false;
        this.released = false;
        this.fl = null;
        this.kl = null;
        this.mouseTimerThread = null;
        this.stopMousePressedTimer = false;
        this.mouseEmulator = null;
        this.toolTipProcessor = null;
        this.mouseRightCLickTime = 1;
        this.ml = null;
        this.mwl = null;
        this.tipListeners = null;
        this.rightMouseEmulator = false;
        this.enabled = true;
        this.lastKey = 0;
        this.id = null;
        this.ita = false;
        this.wheelHandler = null;
        this.focusId = null;
        this.allowClearInside = true;
        this.parentId = null;
        ++jiPanel.numPanels;
        this.id = "".concat(String.valueOf(String.valueOf(jiPanel.numPanels)));
        this.jbInit(s);
        super.setLayout(layout);
    }
    
    public void setAllowClearInside(final boolean allowClearInside) {
        this.allowClearInside = allowClearInside;
    }
    
    public void setFocusRingId(final String focusId) {
        this.focusId = focusId;
    }
    
    public String getFocusRingId() {
        return this.focusId;
    }
    
    public void mouseWheelMoved(final g9 g9) {
        this.fireMouseWheelEvent(g9);
    }
    
    public final void setBorderColor(final Color color) {
        try {
            this.border.a(color);
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public String getId() {
        return this.id;
    }
    
    public boolean isSwing() {
        return false;
    }
    
    public void setOpaque(final boolean b) {
    }
    
    private void jbInit(final String parentId) {
        try {
            this.parentId = parentId;
            this.setOpaque(true);
            e.a(this);
            e.a(parentId, this);
            d.a(parentId, this);
            d.c(this);
            this.addKeyListener(this);
        }
        catch (Exception ex) {}
    }
    
    private final void initWheelHandler(final String s) {
        try {
            if (this.wheelHandler == null) {
                this.wheelHandler = new bp(this, s);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void initTipListener() {
        if (this.toolTipProcessor == null) {
            this.addMouseMotionListener(this.toolTipProcessor = new br(this.parentId, this));
            this.addMouseListener(this.toolTipProcessor);
        }
    }
    
    public void disableDefaultKeyListener() {
        this.removeKeyListener(this);
    }
    
    public void setEnabled(final boolean enabled) {
        if (this.enabled != enabled) {
            super.setEnabled(this.enabled = enabled);
            if (!enabled && e.c(this.parentId, this)) {
                e.n(this.parentId);
            }
        }
    }
    
    public void setInsets(final Insets insets) {
        this.insets = new Insets(insets.top, insets.left, insets.bottom, insets.right);
    }
    
    public Insets getInsets() {
        if (this.insets == null) {
            return this.border.a();
        }
        return this.insets;
    }
    
    public void setBorderStyle(final int n) {
        try {
            if (n != this.border.b()) {
                this.border.c(this, this.getGraphics());
                this.border.a(n);
                this.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    public int getBorderStyle() {
        return this.border.b();
    }
    
    public void setDarkShade(final Color color) {
        this.border.b(color);
    }
    
    public Color getDarkShade() {
        return this.border.g();
    }
    
    public void setLightShade(final Color color) {
        this.border.c(color);
    }
    
    public Color getLightShade() {
        return this.border.h();
    }
    
    public final void setJoinTop(final boolean b) {
        this.border.a(b);
    }
    
    public final boolean getJoinTop() {
        return this.border.c();
    }
    
    public final void setJoinBottom(final boolean b) {
        this.border.b(b);
    }
    
    public final boolean getJoinBottom() {
        return this.border.d();
    }
    
    public final void setJoinLeft(final boolean b) {
        this.border.c(b);
    }
    
    public final boolean getJoinLeft() {
        return this.border.e();
    }
    
    public final void setJoinRight(final boolean b) {
        this.border.d(b);
    }
    
    public final boolean getJoinRight() {
        return this.border.f();
    }
    
    public void clearInsideBorder() {
        try {
            if (this.allowClearInside) {
                this.border.a(this, this.getGraphics());
            }
        }
        catch (Exception ex) {}
    }
    
    public void clearAll() {
        try {
            if (this.allowClearInside) {
                this.border.a(this, this.getGraphics());
            }
        }
        catch (Exception ex) {}
    }
    
    public void clearBorder() {
        try {
            this.border.c(this, this.getGraphics());
        }
        catch (Exception ex) {}
    }
    
    public boolean isVisible() {
        return super.isVisible();
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        this.validate();
    }
    
    public void setLocation(final int n, final int n2) {
        try {
            final Point location = this.getLocation();
            if (location.x != n || location.y != n2) {
                super.setLocation(n, n2);
            }
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.isSwing()) {
            super.paint(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void setToolTipText(final String s) {
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (!this.painting) {
            try {
                this.border.d(this, graphics);
            }
            finally {
                this.painting = false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (!this.painting) {
            try {
                if (this.isSwing()) {
                    super.update(graphics);
                }
                else {
                    if (this.allowClearInside) {
                        this.border.a(this, graphics);
                    }
                    this.paintComponent(graphics);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void setShadowWidth(final int n) {
        if (n != this.border.i()) {
            try {
                this.border.c(this, this.getGraphics());
            }
            catch (Exception ex) {}
            this.border.b(n);
            this.repaint();
        }
    }
    
    public int getShadowWidth() {
        return this.border.i();
    }
    
    public void setIgnoreTAB(final boolean ita) {
        this.ita = ita;
    }
    
    public boolean ignoreTAB() {
        return this.ita;
    }
    
    public void setAcceptFocus(final boolean canAccept) {
        this.canAccept = canAccept;
        e.a(this.parentId, this, canAccept);
    }
    
    public boolean acceptFocus() {
        return this.canAccept;
    }
    
    public void transferFocus() {
    }
    
    public void clearWheelHandler() {
        try {
            if (this.wheelHandler != null) {
                this.wheelHandler.a(this);
                this.wheelHandler = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void releaseResources() {
        if (!this.released) {
            if (!d.ao(this.parentId)) {
                this.removeNotify();
            }
            this.released = true;
            this.setRightMouseEmulator(false);
            this.clearWheelHandler();
            this.removeKeyListener(this);
            e.b(this.parentId, this);
            d.b(this.parentId, this);
            d.d(this);
            if (this.toolTipProcessor != null) {
                this.removeMouseListener(this.toolTipProcessor);
                this.removeMouseMotionListener(this.toolTipProcessor);
                this.toolTipProcessor.a();
                this.toolTipProcessor = null;
            }
            if (this.tipListeners != null) {
                this.tipListeners.c();
            }
            if (this.fl != null) {
                this.fl.c();
            }
            if (this.kl != null) {
                this.kl.c();
            }
            if (this.ml != null) {
                this.ml.c();
            }
            if (this.mwl != null) {
                this.mwl.c();
            }
            e.a(this.parentId, this, false);
        }
    }
    
    public void finalize() {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void setRightMouseEmulator(final boolean rightMouseEmulator) {
        if (d.dq()) {
            this.stopMouseTimer();
            this.rightMouseEmulator = rightMouseEmulator;
            if (rightMouseEmulator) {
                if (this.mouseEmulator == null) {
                    this.mouseEmulator = new s0();
                }
                super.addMouseListener(this.mouseEmulator);
                super.addMouseMotionListener(this.mouseEmulator);
            }
            else if (this.mouseEmulator != null) {
                super.removeMouseListener(this.mouseEmulator);
                super.removeMouseMotionListener(this.mouseEmulator);
                this.mouseEmulator = null;
            }
        }
    }
    
    public boolean isRightMouseEmulator() {
        return this.mouseEmulator != null;
    }
    
    public void setRightMouseTime(final int mouseRightCLickTime) {
        this.mouseRightCLickTime = mouseRightCLickTime;
    }
    
    public int getRightMouseTime() {
        return this.mouseRightCLickTime;
    }
    
    protected final void fireMouseReleased(final MouseEvent mouseEvent) {
        if (this.ml != null) {
            for (int i = 0; i < this.ml.b(); ++i) {
                ((MouseListener)this.ml.b(i)).mouseReleased(mouseEvent);
            }
        }
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
        if (this.rightMouseEmulator) {
            if (this.ml == null) {
                this.ml = new c("jiPanel1", 2);
            }
            if (!this.ml.a(mouseListener)) {
                this.ml.c(mouseListener);
            }
        }
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        super.removeMouseListener(mouseListener);
        if (this.ml != null && this.ml.a(mouseListener)) {
            this.ml.b(mouseListener);
        }
    }
    
    protected final void fireMouseWheelEvent(final g9 g9) {
        synchronized (jiPanel.lockWheel) {
            if (this.mwl != null) {
                for (int i = 0; i < this.mwl.b(); ++i) {
                    ((as)this.mwl.b(i)).mouseWheelMoved(g9);
                }
            }
        }
        // monitorexit(jiPanel.lockWheel)
    }
    
    public void addWheelListener(final as as, final String s) {
        if (!d.b()) {
            this.initWheelHandler(s);
            if (this.mwl == null) {
                this.mwl = new c("jiPanel1mwl", 2);
            }
            if (!this.mwl.a(as)) {
                this.mwl.c(as);
            }
        }
    }
    
    public void removeWheelListener(final as as) {
        if (this.mwl != null && this.mwl.a(as)) {
            this.mwl.b(as);
        }
    }
    
    public void addToolTipListener(final c9 c9) {
        this.initTipListener();
        if (this.tipListeners == null) {
            this.tipListeners = new c("jiPanel1", 2);
        }
        if (!this.tipListeners.a(c9)) {
            this.tipListeners.c(c9);
        }
    }
    
    public void removeToolTipListener(final c9 c9) {
        if (this.tipListeners != null && this.tipListeners.a(c9)) {
            this.tipListeners.b(c9);
        }
    }
    
    public void fireTipEvent(final d8 d8) {
        if (this.tipListeners != null) {
            for (int i = 0; i < this.tipListeners.b(); ++i) {
                if (!d8.f()) {
                    ((c9)this.tipListeners.b(i)).a(d8);
                }
            }
        }
    }
    
    private void startMouseTimer(final MouseEvent mouseEvent) {
        try {
            if (this.mouseTimerThread == null) {
                this.stopMousePressedTimer = false;
                (this.mouseTimerThread = new bb(this.parentId, new zh(this, mouseEvent))).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void stopMouseTimer() {
        this.stopMousePressedTimer = true;
    }
    
    static {
        jiPanel.numPanels = 0;
        jiPanel.lockWheel = new Object();
    }
    
    class s0 extends MouseAdapter implements MouseMotionListener
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            jiPanel.this.stopMouseTimer();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            jiPanel.this.stopMouseTimer();
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            jiPanel.this.startMouseTimer(mouseEvent);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            jiPanel.this.stopMouseTimer();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
    
    class zh implements Runnable
    {
        Component a;
        MouseEvent b;
        
        public zh(final Component a, final MouseEvent b) {
            this.a = null;
            this.b = null;
            this.a = a;
            this.b = b;
        }
        
        public final void run() {
            int n;
            int n2;
            for (n = 0, n2 = jiPanel.this.mouseRightCLickTime * 7; !jiPanel.this.stopMousePressedTimer && n < n2; ++n) {
                d.b(100, 48, jiPanel.this.parentId);
            }
            if (n >= n2 && !jiPanel.this.stopMousePressedTimer) {
                final Point point = this.b.getPoint();
                jiPanel.this.fireMouseReleased(new MouseEvent(this.a, 502, 0L, 8, point.x, point.y, 1, true));
            }
            jiPanel.this.mouseTimerThread = null;
            this.a = null;
            this.b = null;
        }
    }
}
