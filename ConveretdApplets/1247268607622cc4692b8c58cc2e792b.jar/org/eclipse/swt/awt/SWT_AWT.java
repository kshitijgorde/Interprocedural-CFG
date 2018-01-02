// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.awt;

import java.awt.event.ComponentListener;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.AWTEvent;
import java.awt.Window;
import java.awt.event.WindowEvent;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.OS;
import java.awt.EventQueue;
import java.lang.reflect.Field;
import org.eclipse.swt.SWT;
import java.awt.Frame;
import org.eclipse.swt.widgets.Composite;
import java.lang.reflect.Method;
import org.eclipse.swt.internal.Library;
import java.awt.Toolkit;
import java.awt.Canvas;

public class SWT_AWT
{
    public static String embeddedFrameClass;
    static String EMBEDDED_FRAME_KEY;
    static boolean loaded;
    static boolean swingInitialized;
    
    static {
        SWT_AWT.EMBEDDED_FRAME_KEY = "org.eclipse.swt.awt.SWT_AWT.embeddedFrame";
    }
    
    static final native int getAWTHandle(final Canvas p0);
    
    static synchronized void loadLibrary() {
        if (SWT_AWT.loaded) {
            return;
        }
        SWT_AWT.loaded = true;
        Toolkit.getDefaultToolkit();
        try {
            System.loadLibrary("jawt");
        }
        catch (Throwable t) {}
        Library.loadLibrary("swt-awt");
    }
    
    static synchronized void initializeSwing() {
        if (SWT_AWT.swingInitialized) {
            return;
        }
        SWT_AWT.swingInitialized = true;
        try {
            final Class[] array = new Class[0];
            final Object[] array2 = new Object[0];
            final Class<?> forName = Class.forName("javax.swing.UIManager");
            final Method method = forName.getMethod("getDefaults", (Class[])array);
            if (method != null) {
                method.invoke(forName, array2);
            }
        }
        catch (Throwable t) {}
    }
    
    public static Frame getFrame(final Composite composite) {
        if (composite == null) {
            SWT.error(4);
        }
        if ((composite.getStyle() & 0x1000000) == 0x0) {
            return null;
        }
        return (Frame)composite.getData(SWT_AWT.EMBEDDED_FRAME_KEY);
    }
    
    public static Frame new_Frame(final Composite composite) {
        if (composite == null) {
            SWT.error(4);
        }
        if ((composite.getStyle() & 0x1000000) == 0x0) {
            SWT.error(5);
        }
        final int handle = composite.handle;
        final Frame[] array = { null };
        final Throwable[] array2 = { null };
        final Runnable runnable = new Runnable() {
            private final /* synthetic */ Frame[] val$result = val$result;
            
            public void run() {
                try {
                    Class<?> forName = null;
                    Label_0063: {
                        try {
                            forName = Class.forName((SWT_AWT.embeddedFrameClass != null) ? SWT_AWT.embeddedFrameClass : "sun.awt.windows.WEmbeddedFrame");
                            break Label_0063;
                        }
                        catch (Throwable t) {
                            array2[0] = t;
                        }
                        return;
                    }
                    SWT_AWT.initializeSwing();
                    Object o = null;
                    try {
                        o = forName.getConstructor(Integer.TYPE).newInstance(new Integer(handle));
                    }
                    catch (Throwable t3) {
                        try {
                            o = forName.getConstructor(Long.TYPE).newInstance(new Long(handle));
                        }
                        catch (Throwable t2) {
                            array2[0] = t2;
                        }
                    }
                    final Frame frame = (Frame)o;
                    frame.addNotify();
                    try {
                        final Field declaredField = Class.forName("sun.awt.windows.WComponentPeer").getDeclaredField("winGraphicsConfig");
                        declaredField.setAccessible(true);
                        declaredField.set(frame.getPeer(), frame.getGraphicsConfiguration());
                    }
                    catch (Throwable t4) {}
                    this.val$result[0] = frame;
                }
                finally {
                    synchronized (this.val$result) {
                        this.val$result.notify();
                    }
                    // monitorexit(this.val$result)
                }
                synchronized (this.val$result) {
                    this.val$result.notify();
                }
                // monitorexit(this.val$result)
            }
        };
        if (EventQueue.isDispatchThread() || composite.getDisplay().getSyncThread() != null) {
            runnable.run();
        }
        else {
            EventQueue.invokeLater(runnable);
            OS.ReplyMessage(0);
            boolean b = false;
            final MSG msg = new MSG();
            final int n = 4194306;
            while (array[0] == null && array2[0] == null) {
                OS.PeekMessage(msg, 0, 0, 0, n);
                try {
                    synchronized (array) {
                        array.wait(50L);
                    }
                    // monitorexit(array)
                }
                catch (InterruptedException ex) {
                    b = true;
                }
            }
            if (b) {
                Compatibility.interrupt();
            }
        }
        if (array2[0] != null) {
            SWT.error(20, array2[0]);
        }
        final Frame frame = array[0];
        composite.setData(SWT_AWT.EMBEDDED_FRAME_KEY, frame);
        final Listener listener = new Listener() {
            private final /* synthetic */ Frame val$frame = val$frame;
            
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 20: {
                        EventQueue.invokeLater(new Runnable() {
                            private final /* synthetic */ Frame val$frame = this.val$frame;
                            
                            public void run() {
                                this.val$frame.dispatchEvent(new WindowEvent(this.val$frame, 204));
                            }
                        });
                        break;
                    }
                    case 19: {
                        EventQueue.invokeLater(new Runnable() {
                            private final /* synthetic */ Frame val$frame = this.val$frame;
                            
                            public void run() {
                                this.val$frame.dispatchEvent(new WindowEvent(this.val$frame, 203));
                            }
                        });
                        break;
                    }
                }
            }
        };
        final Shell shell = composite.getShell();
        shell.addListener(20, listener);
        shell.addListener(19, listener);
        final Listener listener2 = new Listener() {
            private final /* synthetic */ Composite val$parent = val$parent;
            private final /* synthetic */ Listener val$shellListener = listener;
            
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        final Shell shell = this.val$parent.getShell();
                        shell.removeListener(20, this.val$shellListener);
                        shell.removeListener(19, this.val$shellListener);
                        this.val$parent.setVisible(false);
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    frame.dispose();
                                }
                                catch (Throwable t) {}
                            }
                        });
                        break;
                    }
                    case 15:
                    case 26: {
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 4, 0)) {
                                    frame.dispatchEvent(new WindowEvent(frame, 205));
                                    frame.dispatchEvent(new FocusEvent(frame, 1004));
                                }
                                else if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 5, 0)) {
                                    frame.dispatchEvent(new WindowEvent(frame, 205));
                                    frame.dispatchEvent(new WindowEvent(frame, 207));
                                }
                                else {
                                    if (frame.isActive()) {
                                        return;
                                    }
                                    try {
                                        final Method method = frame.getClass().getMethod("synthesizeWindowActivation", Boolean.TYPE);
                                        if (method != null) {
                                            method.invoke(frame, new Boolean(true));
                                        }
                                    }
                                    catch (Throwable t) {}
                                }
                            }
                        });
                        break;
                    }
                    case 27: {
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 4, 0)) {
                                    frame.dispatchEvent(new WindowEvent(frame, 206));
                                    frame.dispatchEvent(new FocusEvent(frame, 1005));
                                }
                                else if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 5, 0)) {
                                    frame.dispatchEvent(new WindowEvent(frame, 208));
                                    frame.dispatchEvent(new WindowEvent(frame, 206));
                                }
                                else {
                                    if (!frame.isActive()) {
                                        return;
                                    }
                                    try {
                                        final Method method = frame.getClass().getMethod("synthesizeWindowActivation", Boolean.TYPE);
                                        if (method != null) {
                                            method.invoke(frame, new Boolean(false));
                                        }
                                    }
                                    catch (Throwable t) {}
                                }
                            }
                        });
                        break;
                    }
                }
            }
        };
        if (Library.JAVA_VERSION < Library.JAVA_VERSION(1, 5, 0)) {
            composite.addListener(26, listener2);
        }
        else {
            composite.addListener(15, listener2);
        }
        composite.addListener(27, listener2);
        composite.addListener(12, listener2);
        composite.getDisplay().asyncExec(new Runnable() {
            private final /* synthetic */ Composite val$parent = val$parent;
            
            public void run() {
                if (this.val$parent.isDisposed()) {
                    return;
                }
                EventQueue.invokeLater(new Runnable() {
                    private final /* synthetic */ Rectangle val$clientArea = this.val$parent.getClientArea();
                    
                    public void run() {
                        frame.setSize(this.val$clientArea.width, this.val$clientArea.height);
                        frame.validate();
                    }
                });
            }
        });
        return frame;
    }
    
    public static Shell new_Shell(final Display display, final Canvas canvas) {
        if (display == null) {
            SWT.error(4);
        }
        if (canvas == null) {
            SWT.error(4);
        }
        int awtHandle = 0;
        try {
            loadLibrary();
            awtHandle = getAWTHandle(canvas);
        }
        catch (Throwable t) {
            SWT.error(20, t);
        }
        if (awtHandle == 0) {
            SWT.error(5, null, " [peer not created]");
        }
        final Shell win32_new = Shell.win32_new(display, awtHandle);
        final ComponentAdapter componentAdapter = new ComponentAdapter() {
            private final /* synthetic */ Display val$display = val$display;
            
            public void componentResized(final ComponentEvent componentEvent) {
                this.val$display.syncExec(new Runnable() {
                    public void run() {
                        if (win32_new.isDisposed()) {
                            return;
                        }
                        final Dimension size = canvas.getSize();
                        win32_new.setSize(size.width, size.height);
                    }
                });
            }
        };
        canvas.addComponentListener(componentAdapter);
        win32_new.addListener(12, new Listener() {
            private final /* synthetic */ Canvas val$parent = val$parent;
            private final /* synthetic */ ComponentListener val$listener = componentAdapter;
            
            public void handleEvent(final Event event) {
                this.val$parent.removeComponentListener(this.val$listener);
            }
        });
        win32_new.setVisible(true);
        return win32_new;
    }
}
