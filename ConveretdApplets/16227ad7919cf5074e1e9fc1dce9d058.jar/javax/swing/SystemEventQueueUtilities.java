// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.util.Enumeration;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Hashtable;

class SystemEventQueueUtilities
{
    private static Hashtable rootTable;
    private static final Object classLock;
    
    static {
        SystemEventQueueUtilities.rootTable = new Hashtable(4);
        classLock = new Object();
        if (SwingUtilities.is1dot2) {
            System.err.println("warning: running 1.1 version of SystemEventQueueUtilities");
        }
    }
    
    static void addRunnableCanvas(final JRootPane rootPane) {
        synchronized (SystemEventQueueUtilities.classLock) {
            if (SystemEventQueue.get(rootPane) != null) {
                // monitorexit(SystemEventQueueUtilities.classLock)
                return;
            }
            final JLayeredPane layeredPane = rootPane.getLayeredPane();
            if (layeredPane != null) {
                layeredPane.add(new RunnableCanvas(rootPane));
            }
        }
        // monitorexit(SystemEventQueueUtilities.classLock)
    }
    
    private static ThreadGroup getThreadGroupSafely() {
        return new Thread().getThreadGroup();
    }
    
    static Exception postRunnable(final Runnable runnable, final Object o) {
        final EventQueue value = SystemEventQueue.get();
        final RunnableEvent runnableEvent = new RunnableEvent(runnable, o);
        if (value != null) {
            value.postEvent(runnableEvent);
        }
        else {
            postRunnableCanvasEvent(runnableEvent);
        }
        return runnableEvent.exception;
    }
    
    private static void postRunnableCanvasEvent(final RunnableEvent runnableEvent) {
        synchronized (SystemEventQueueUtilities.classLock) {
            final RunnableCanvas lookup = RunnableCanvas.lookup(runnableEvent);
            if (lookup == null) {
                if (runnableEvent.doRun instanceof ComponentWorkRequest) {
                    final ComponentWorkRequest componentWorkRequest2;
                    final ComponentWorkRequest componentWorkRequest = componentWorkRequest2 = (ComponentWorkRequest)runnableEvent.doRun;
                    // monitorenter(componentWorkRequest2)
                    try {
                        componentWorkRequest.isPending = false;
                    }
                    // monitorexit(componentWorkRequest2)
                    finally {}
                }
                if (runnableEvent.doRun instanceof Timer.DoPostEvent) {
                    ((Timer.DoPostEvent)runnableEvent.doRun).getTimer().eventQueued = false;
                }
                if (runnableEvent.lock != null) {
                    runnableEvent.lock.notify();
                }
                // monitorexit(SystemEventQueueUtilities.classLock)
                return;
            }
            lookup.addRunnableEvent(runnableEvent);
            lookup.repaint();
        }
        // monitorexit(SystemEventQueueUtilities.classLock)
    }
    
    private static void processRunnableEvent(final RunnableEvent runnableEvent) {
        final Object lock = runnableEvent.lock;
        if (lock == null) {
            runnableEvent.doRun.run();
        }
        else {
            synchronized (lock) {
                try {
                    runnableEvent.doRun.run();
                }
                catch (Exception exception) {
                    runnableEvent.exception = exception;
                }
                finally {
                    if (runnableEvent.lock != null) {
                        runnableEvent.lock.notify();
                    }
                }
            }
        }
    }
    
    static void queueComponentWorkRequest(final Component component) {
        ComponentWorkRequest componentWorkRequest = SystemEventQueueUtilities.rootTable.get(component);
        final boolean b = componentWorkRequest == null;
        if (b) {
            componentWorkRequest = new ComponentWorkRequest(component);
        }
        synchronized (componentWorkRequest) {
            if (b) {
                SystemEventQueueUtilities.rootTable.put(component, componentWorkRequest);
            }
            if (!componentWorkRequest.isPending) {
                SwingUtilities.invokeLater(componentWorkRequest);
                componentWorkRequest.isPending = true;
            }
        }
    }
    
    static void removeRunnableCanvas(final JRootPane rootPane) {
        synchronized (SystemEventQueueUtilities.classLock) {
            SystemEventQueueUtilities.rootTable.remove(SwingUtilities.getRoot(rootPane));
            RunnableCanvas.remove(rootPane);
        }
        // monitorexit(SystemEventQueueUtilities.classLock)
    }
    
    static void restartTimerQueueThread() {
        synchronized (SystemEventQueueUtilities.classLock) {
            if (SystemEventQueue.get() == null) {
                RunnableCanvas.postRunnableEventToAll(new RunnableEvent(new TimerQueueRestart(), null));
            }
        }
        // monitorexit(SystemEventQueueUtilities.classLock)
    }
    
    private static class SystemEventQueue
    {
        private static boolean checkedEventQueue;
        private static EventQueue eventQueue;
        
        static {
            SystemEventQueue.checkedEventQueue = false;
            SystemEventQueue.eventQueue = null;
        }
        
        static EventQueue get() {
            if (!SystemEventQueue.checkedEventQueue) {
                init(null);
            }
            return SystemEventQueue.eventQueue;
        }
        
        static EventQueue get(final JRootPane rootPane) {
            if (!SystemEventQueue.checkedEventQueue) {
                init(rootPane);
            }
            return SystemEventQueue.eventQueue;
        }
        
        private static void init(final JComponent component) {
            synchronized (SystemEventQueueUtilities.classLock) {
                if (!SystemEventQueue.checkedEventQueue) {
                    if (component != null && component.getClientProperty("defeatSystemEventQueueCheck") != null) {
                        SystemEventQueue.checkedEventQueue = true;
                    }
                    else {
                        try {
                            SystemEventQueue.eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
                        }
                        catch (SecurityException ex) {
                            if (!SwingUtilities.is1dot2) {
                                System.err.println("Swing: checked access to system event queue.");
                            }
                        }
                        finally {
                            SystemEventQueue.checkedEventQueue = true;
                        }
                    }
                }
            }
            // monitorexit(SystemEventQueueUtilities.access$0())
        }
    }
    
    private static class ComponentWorkRequest implements Runnable
    {
        boolean isPending;
        Component component;
        
        ComponentWorkRequest(final Component component) {
            this.component = component;
        }
        
        public void run() {
            final RepaintManager currentManager;
            synchronized (this) {
                currentManager = RepaintManager.currentManager(this.component);
                this.isPending = false;
            }
            currentManager.validateInvalidComponents();
            currentManager.paintDirtyRegions();
        }
    }
    
    private static class TimerQueueRestart implements Runnable
    {
        boolean attemptedStart;
        
        public synchronized void run() {
            if (!this.attemptedStart) {
                final TimerQueue sharedInstance = TimerQueue.sharedInstance();
                synchronized (sharedInstance) {
                    if (!sharedInstance.running) {
                        sharedInstance.start();
                    }
                }
                // monitorexit(sharedInstance)
                this.attemptedStart = true;
            }
        }
    }
    
    private static class RunnableEvent extends AWTEvent
    {
        static final int EVENT_ID = 2999;
        static final Component target;
        final Runnable doRun;
        final Object lock;
        Exception exception;
        
        static {
            target = new RunnableTarget();
        }
        
        RunnableEvent(final Runnable doRun, final Object lock) {
            super(RunnableEvent.target, 2999);
            this.doRun = doRun;
            this.lock = lock;
        }
    }
    
    private static class RunnableTarget extends Component
    {
        RunnableTarget() {
            this.enableEvents(2999L);
        }
        
        protected void processEvent(final AWTEvent awtEvent) {
            if (awtEvent instanceof RunnableEvent) {
                processRunnableEvent((RunnableEvent)awtEvent);
            }
        }
    }
    
    private static class RunnableCanvas extends Canvas
    {
        private static final Graphics nullGraphics;
        private static Hashtable runnableCanvasTable;
        private Vector runnableEvents;
        private boolean isRegistered;
        
        static {
            nullGraphics = new RunnableCanvasGraphics();
            RunnableCanvas.runnableCanvasTable = new Hashtable(1);
        }
        
        RunnableCanvas(final JRootPane rootPane) {
            this.runnableEvents = new Vector(2);
            this.isRegistered = false;
            this.setBounds(0, 0, 1, 1);
            if (RunnableCanvas.runnableCanvasTable.get(Thread.currentThread()) == null) {
                try {
                    RunnableCanvas.runnableCanvasTable.put(Thread.currentThread(), this);
                    RunnableCanvas.runnableCanvasTable.put(getThreadGroupSafely(), this);
                    if (SwingUtilities.isEventDispatchThread()) {
                        this.isRegistered = true;
                    }
                }
                catch (Exception ex) {
                    System.err.println("Can't register RunnableCanvas");
                    ex.printStackTrace();
                }
            }
            RunnableCanvas.runnableCanvasTable.put(rootPane, this);
            this.maybeRegisterEventDispatchThread();
        }
        
        synchronized void addRunnableEvent(final RunnableEvent runnableEvent) {
            this.runnableEvents.addElement(runnableEvent);
        }
        
        public Graphics getGraphics() {
            return RunnableCanvas.nullGraphics;
        }
        
        public Dimension getPreferredSize() {
            return new Dimension(1, 1);
        }
        
        private synchronized RunnableEvent[] getRunnableCanvasEvents() {
            final int size = this.runnableEvents.size();
            if (size == 0) {
                return null;
            }
            final RunnableEvent[] array = new RunnableEvent[size];
            for (int i = 0; i < size; ++i) {
                array[i] = (RunnableEvent)this.runnableEvents.elementAt(i);
            }
            this.runnableEvents.removeAllElements();
            return array;
        }
        
        public boolean isShowing() {
            return this.runnableEvents.size() > 0;
        }
        
        static RunnableCanvas lookup(final RunnableEvent runnableEvent) {
            if (runnableEvent.doRun instanceof ComponentWorkRequest) {
                final ComponentWorkRequest componentWorkRequest = (ComponentWorkRequest)runnableEvent.doRun;
                synchronized (componentWorkRequest) {
                    final JRootPane rootPane = SwingUtilities.getRootPane(componentWorkRequest.component);
                    if (rootPane != null) {
                        // monitorexit(componentWorkRequest)
                        return RunnableCanvas.runnableCanvasTable.get(rootPane);
                    }
                    componentWorkRequest.isPending = false;
                    // monitorexit(componentWorkRequest)
                    return null;
                }
            }
            final RunnableCanvas value = RunnableCanvas.runnableCanvasTable.get(Thread.currentThread());
            if (value != null) {
                return value;
            }
            ThreadGroup threadGroup;
            try {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            catch (SecurityException ex) {
                return null;
            }
            final RunnableCanvas runnableCanvas3 = RunnableCanvas.runnableCanvasTable.get(threadGroup);
            if (runnableCanvas3 == null) {
                final Enumeration<JRootPane> keys = (Enumeration<JRootPane>)RunnableCanvas.runnableCanvasTable.keys();
                if (keys == null) {
                    return null;
                }
                while (keys.hasMoreElements()) {
                    final JRootPane nextElement = keys.nextElement();
                    if (nextElement instanceof JRootPane && nextElement.isShowing()) {
                        return (RunnableCanvas)RunnableCanvas.runnableCanvasTable.get(nextElement);
                    }
                }
            }
            return runnableCanvas3;
        }
        
        private void maybeRegisterEventDispatchThread() {
            if (!this.isRegistered) {
                synchronized (this) {
                    if (!this.isRegistered && SwingUtilities.isEventDispatchThread()) {
                        final Thread currentThread = Thread.currentThread();
                        if (RunnableCanvas.runnableCanvasTable.get(currentThread) != null) {
                            this.isRegistered = true;
                        }
                        else {
                            RunnableCanvas.runnableCanvasTable.put(currentThread, this);
                            RunnableCanvas.runnableCanvasTable.put(getThreadGroupSafely(), this);
                            this.isRegistered = true;
                        }
                    }
                }
            }
        }
        
        public void paint(final Graphics graphics) {
            this.maybeRegisterEventDispatchThread();
        }
        
        static void postRunnableEventToAll(final RunnableEvent runnableEvent) {
            ThreadGroup threadGroup;
            try {
                threadGroup = new Thread().getThreadGroup();
            }
            catch (SecurityException ex) {
                threadGroup = null;
            }
            RunnableCanvas runnableCanvas;
            if (threadGroup != null) {
                runnableCanvas = RunnableCanvas.runnableCanvasTable.get(threadGroup);
            }
            else {
                runnableCanvas = null;
            }
            final Enumeration<Object> keys = (Enumeration<Object>)RunnableCanvas.runnableCanvasTable.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                if (nextElement instanceof JRootPane) {
                    final Object value = RunnableCanvas.runnableCanvasTable.get(nextElement);
                    if (value == runnableCanvas) {
                        continue;
                    }
                    final RunnableCanvas runnableCanvas2 = (RunnableCanvas)value;
                    runnableCanvas2.addRunnableEvent(runnableEvent);
                    runnableCanvas2.repaint();
                }
            }
        }
        
        static void remove(final JRootPane rootPane) {
            final RunnableCanvas runnableCanvas = RunnableCanvas.runnableCanvasTable.get(rootPane);
            if (runnableCanvas != null) {
                RunnableCanvas runnableCanvas2 = null;
                rootPane.getLayeredPane().remove(runnableCanvas);
                final Enumeration<Object> keys = RunnableCanvas.runnableCanvasTable.keys();
                while (keys.hasMoreElements()) {
                    final Object nextElement = keys.nextElement();
                    final Object value = RunnableCanvas.runnableCanvasTable.get(nextElement);
                    if (runnableCanvas == value) {
                        RunnableCanvas.runnableCanvasTable.remove(nextElement);
                    }
                    else {
                        if (runnableCanvas2 != null) {
                            continue;
                        }
                        runnableCanvas2 = (RunnableCanvas)value;
                    }
                }
                final RunnableEvent[] runnableCanvasEvents = runnableCanvas.getRunnableCanvasEvents();
                final int n = (runnableCanvasEvents == null) ? 0 : runnableCanvasEvents.length;
                if (n > 0) {
                    if (runnableCanvas2 != null) {
                        for (final RunnableEvent runnableEvent : runnableCanvasEvents) {
                            if (runnableEvent.doRun instanceof Timer.DoPostEvent) {
                                runnableCanvas2.addRunnableEvent(runnableEvent);
                            }
                        }
                        runnableCanvas2.repaint();
                    }
                    else {
                        for (final RunnableEvent runnableEvent2 : runnableCanvasEvents) {
                            if (runnableEvent2.doRun instanceof Timer.DoPostEvent) {
                                ((Timer.DoPostEvent)runnableEvent2.doRun).getTimer().eventQueued = false;
                            }
                        }
                    }
                }
            }
        }
        
        public void update(final Graphics graphics) {
            final RunnableEvent[] runnableCanvasEvents = this.getRunnableCanvasEvents();
            if (runnableCanvasEvents != null) {
                for (int i = 0; i < runnableCanvasEvents.length; ++i) {
                    processRunnableEvent(runnableCanvasEvents[i]);
                }
            }
        }
    }
    
    private static class RunnableCanvasGraphics extends Graphics
    {
        public void clearRect(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void clipRect(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void copyArea(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        }
        
        public Graphics create() {
            return this;
        }
        
        public void dispose() {
        }
        
        public void drawArc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        }
        
        public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Color color, final ImageObserver imageObserver) {
            return false;
        }
        
        public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final ImageObserver imageObserver) {
            return false;
        }
        
        public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final ImageObserver imageObserver) {
            return false;
        }
        
        public boolean drawImage(final Image image, final int n, final int n2, final int n3, final int n4, final ImageObserver imageObserver) {
            return false;
        }
        
        public boolean drawImage(final Image image, final int n, final int n2, final Color color, final ImageObserver imageObserver) {
            return false;
        }
        
        public boolean drawImage(final Image image, final int n, final int n2, final ImageObserver imageObserver) {
            return false;
        }
        
        public void drawLine(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void drawOval(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void drawPolygon(final int[] array, final int[] array2, final int n) {
        }
        
        public void drawPolyline(final int[] array, final int[] array2, final int n) {
        }
        
        public void drawRoundRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        }
        
        public void drawString(final String s, final int n, final int n2) {
        }
        
        public void fillArc(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        }
        
        public void fillOval(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void fillPolygon(final int[] array, final int[] array2, final int n) {
        }
        
        public void fillRect(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void fillRoundRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        }
        
        public Shape getClip() {
            return this.getClipBounds();
        }
        
        public Rectangle getClipBounds() {
            return new Rectangle(0, 0, 32767, 32767);
        }
        
        public Color getColor() {
            return Color.black;
        }
        
        public Font getFont() {
            return null;
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return null;
        }
        
        public void setClip(final int n, final int n2, final int n3, final int n4) {
        }
        
        public void setClip(final Shape shape) {
        }
        
        public void setColor(final Color color) {
        }
        
        public void setFont(final Font font) {
        }
        
        public void setPaintMode() {
        }
        
        public void setXORMode(final Color color) {
        }
        
        public void translate(final int n, final int n2) {
        }
    }
}
