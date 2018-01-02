// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet.support;

import ji.util.m;
import java.awt.Toolkit;
import ji.applet.jiApplet;
import java.lang.reflect.Method;
import java.awt.Component;
import java.awt.Container;
import ji.util.d;
import java.awt.event.WindowListener;
import ji.io.h;
import ji.util.i;
import java.awt.Window;
import java.applet.Applet;

public class b9
{
    static final String[] a;
    static final int[] b;
    
    public static Window a(final String s, final Applet applet) {
        if (i.c(124)) {
            h.d(s, "applet=".concat(String.valueOf(String.valueOf(applet))));
        }
        try {
            Container container = applet.getParent();
            if (i.c(124)) {
                h.d(s, "Shutdown dialog: parent=".concat(String.valueOf(String.valueOf(container))));
            }
            if (container instanceof WindowListener && i.c(124)) {
                h.d(s, "Shutdown dialog: windowListener = yes");
            }
            while (container.getParent() != null) {
                container = container.getParent();
                if (i.c(124)) {
                    h.d(s, "Shutdown dialog: parent=".concat(String.valueOf(String.valueOf(container))));
                }
                if (container instanceof WindowListener && i.c(124)) {
                    h.d(s, "Shutdown dialog: windowListener = yes");
                }
            }
            if (container instanceof Window) {
                return (Window)container;
            }
            return null;
        }
        catch (Exception ex) {
            d.a(ex);
            return null;
        }
    }
    
    public static int a(final String s, final Component component) {
        int n = 0;
        try {
            if (component != null && component instanceof Window) {
                final Window window = (Window)component;
                if (i.c(124)) {
                    h.d(s, "Looking for matching plugin based on ".concat(String.valueOf(String.valueOf(window.getClass().getName()))));
                }
                Block_7: {
                    for (int i = 0; i < b9.a.length; ++i) {
                        if (window.getClass().getName().equalsIgnoreCase(b9.a[i])) {
                            break Block_7;
                        }
                    }
                    return n;
                }
                int i = 0;
                if (ji.util.i.c(124)) {
                    h.d(s, "Discovered container type ".concat(String.valueOf(String.valueOf(b9.a[i]))));
                }
                n = b9.b[i];
            }
            else {
                if (i.c(124)) {
                    h.d(s, "Shutdown dialog: plugin is not a window");
                }
                n = 0;
            }
        }
        catch (Exception ex) {
            d.a(ex);
            n = 0;
        }
        return n;
    }
    
    public static int a(final int n, final Window window, final String s) {
        int n2 = 0;
        Label_0422: {
            try {
                if (n == 3 || n == 1 || n == 9 || n == 7) {
                    if (i.c(124)) {
                        h.d(s, "Shutdown dialog: general plugin 1.4+");
                    }
                    try {
                        window.getClass().getMethod("setEvent", Integer.TYPE);
                        n2 = 2;
                        if (i.c(124)) {
                            h.d(s, "specific plugin 1.4.0/1");
                        }
                        break Label_0422;
                    }
                    catch (NoSuchMethodException ex2) {
                        try {
                            window.getClass().getMethod("activateEmbeddingTopLevel", (Class<?>[])null);
                            n2 = 5;
                            if (i.c(124)) {
                                h.d(s, "specific plugin 1.6.0_10");
                            }
                            break Label_0422;
                        }
                        catch (NoSuchMethodException ex3) {
                            try {
                                window.getClass().getMethod("isLocationByPlatform", (Class<?>[])null);
                                n2 = 4;
                                if (i.c(124)) {
                                    h.d(s, "specific plugin 1.5.0");
                                }
                                break Label_0422;
                            }
                            catch (NoSuchMethodException ex4) {
                                n2 = 3;
                                if (i.c(124)) {
                                    h.d(s, "specific plugin 1.4.2");
                                }
                                break Label_0422;
                            }
                        }
                    }
                }
                if (n == 5) {
                    if (i.c(124)) {
                        h.d(s, "specific plugin MSJVM");
                    }
                    n2 = 1;
                }
                else if (i.c(124) && n != 0 && n != 10 && n != 5 && n != 2 && n != 4) {
                    final Method[] methods = window.getClass().getMethods();
                    for (int i = 0; i < methods.length; ++i) {
                        String s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(methods[i].getReturnType().getName()))).append(" ").append(methods[i].getName()).append("(")));
                        final Class<?>[] parameterTypes = methods[i].getParameterTypes();
                        for (int j = 0; j < parameterTypes.length; ++j) {
                            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(parameterTypes[j].getName())));
                            if (j < parameterTypes.length - 1) {
                                s2 = String.valueOf(String.valueOf(s2)).concat(", ");
                            }
                        }
                        h.d(s, String.valueOf(String.valueOf(s2)).concat(")"));
                    }
                }
                else if (i.c(124)) {
                    h.d(s, "Not discovering plugin, unrecognised container");
                }
            }
            catch (Exception ex) {
                d.a(ex);
            }
        }
        if (n2 == 0) {
            if (d.c(1, 4, 0, 0, s)) {
                n2 = 2;
                if (i.c(124)) {
                    h.d(s, "Discovered plugin (alternative): 1.4.0");
                }
            }
            if (d.c(1, 4, 2, 0, s)) {
                n2 = 3;
                if (i.c(124)) {
                    h.d(s, "Discovered plugin (alternative): 1.4.2");
                }
            }
            if (d.c(1, 5, 0, 0, s)) {
                n2 = 4;
                if (i.c(124)) {
                    h.d(s, "Discovered plugin (alternative): 1.5.0");
                }
            }
            if (n2 == 0 && i.c(124)) {
                h.d(s, "Not discovering plugin (alternative), unrecognised container");
            }
        }
        return n2;
    }
    
    public static void a(final String s, final int n, final Window window, final jiApplet jiApplet) {
        try {
            synchronized (jiApplet.EventProcessorLOCK) {
                if (jiApplet.java_1_3_hangEventQueuePatch == null) {
                    jiApplet.java_1_3_hangEventQueuePatch = new ca(jiApplet, window);
                    if (i.c(131)) {
                        h.d(s, "pushing onto ".concat(String.valueOf(String.valueOf(Toolkit.getDefaultToolkit().getSystemEventQueue()))));
                    }
                    new m(Toolkit.getDefaultToolkit().getSystemEventQueue()).a("push", jiApplet.java_1_3_hangEventQueuePatch);
                }
                if (jiApplet.java_1_3_hangEventQueuePatch != null) {
                    jiApplet.java_1_3_hangEventQueuePatch.a(s, window, jiApplet);
                }
            }
            // monitorexit(jiApplet.EventProcessorLOCK)
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean a(final int n, final int n2, final String s) {
        boolean b = false;
        boolean b2 = false;
        switch (n) {
            case 3: {
                switch (n2) {
                    case 0: {
                        b = true;
                        break;
                    }
                    case 2: {
                        b = true;
                        break;
                    }
                    case 3: {
                        if (d.b(1, 4, 2, 12, s)) {
                            b = true;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if (d.b(1, 5, 0, 8, s)) {
                            b = true;
                            break;
                        }
                        break;
                    }
                    case 5: {
                        if (d.b(1, 6, 0, 10, s)) {
                            b = false;
                            b2 = true;
                            break;
                        }
                        break;
                    }
                }
                if (!b2 && d.b(1, 6, 0, 0, s)) {
                    b = true;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    public static boolean a(final int n, final int n2) {
        boolean b = false;
        switch (n) {
            case 4: {
                b = false;
                break;
            }
            case 2: {
                b = false;
                break;
            }
            case 5: {
                b = true;
                break;
            }
            case 3: {
                switch (n2) {
                    case 2: {
                        b = false;
                        break;
                    }
                    case 3: {
                        b = true;
                        break;
                    }
                    case 4: {
                        b = true;
                        break;
                    }
                    case 5: {
                        b = true;
                        break;
                    }
                }
                break;
            }
            case 1: {
                switch (n2) {
                    case 2: {
                        b = false;
                        break;
                    }
                    case 3: {
                        b = true;
                        break;
                    }
                    case 4: {
                        b = true;
                        break;
                    }
                    case 5: {
                        b = true;
                        break;
                    }
                }
                break;
            }
            case 9: {
                b = false;
                break;
            }
            case 11: {
                b = false;
                break;
            }
            case 7: {
                switch (n2) {
                    case 3: {
                        b = false;
                        break;
                    }
                    case 4: {
                        b = false;
                        break;
                    }
                }
                break;
            }
            case 6: {
                b = false;
                break;
            }
            default: {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public static boolean b(final int n, final int n2) {
        boolean b = false;
        Label_0058: {
            switch (n) {
                case 4: {
                    b = true;
                    break;
                }
                case 2: {
                    b = true;
                    break;
                }
                case 3: {
                    switch (n2) {
                        case 2: {
                            b = false;
                            break Label_0058;
                        }
                    }
                    break;
                }
            }
        }
        return b;
    }
    
    static {
        a = new String[] { "Unknown", "sun.plugin.viewer.frame.IExplorerEmbeddedFrame", "com.ms.applet.BrowserAppletFrame", "sun.beans.ole.OleEmbeddedFrame", "sun.plugin.viewer.frame.WNetscapeEmbeddedFrame", "sun.plugin.navig.win32.PluginFrame", "netscape.applet.DerivedAppletFrame", "sun.awt.windows.WEmbeddedFrame", "sun.applet.AppletViewer", "sun.plugin.viewer.frame.XNetscapeEmbeddedFrame", "sun.plugin.viewer.frame.WebKitEmbeddedFrame", "com.apple.mrj.JavaEmbedding.JE_AppletViewer", "sun.plugin2.main.client.PluginEmbeddedFrame" };
        b = new int[] { 0, 3, 5, 4, 1, 2, 8, 9, 10, 11, 7, 6, 12 };
    }
}
