// 
// Decompiled by Procyon v0.5.30
// 

package mika.system;

import java.io.PrintStream;
import java.applet.Applet;
import java.io.OutputStream;

public class S_Debug extends OutputStream
{
    public static boolean m_showDebug;
    public static S_DebugWindow m_debugWindow;
    public static S_Debug m_currentInstance;
    private static boolean m_windowMode;
    private String m_outBuffer;
    
    public S_Debug() {
        this.m_outBuffer = "";
        S_Debug.m_currentInstance = this;
    }
    
    public static void setDebugMode(final boolean showDebug) {
        S_Debug.m_showDebug = showDebug;
    }
    
    public static boolean isDebugMode() {
        return S_Debug.m_showDebug;
    }
    
    public static void start(final Applet applet) {
        if (applet.getParameter("debugMode") != null && applet.getParameter("debugMode").equals("true")) {
            S_Debug.m_showDebug = true;
        }
        else {
            S_Debug.m_showDebug = false;
        }
        if (S_Debug.m_showDebug) {
            openWindow();
        }
        clear();
    }
    
    public static void clear() {
        if (S_Debug.m_debugWindow != null) {
            S_Debug.m_debugWindow.clear();
        }
    }
    
    public static void show() {
        if (S_Debug.m_debugWindow != null) {
            S_Debug.m_debugWindow.show();
        }
    }
    
    public static void hide() {
        if (S_Debug.m_debugWindow != null) {
            S_Debug.m_debugWindow.hide();
        }
    }
    
    public static void openWindow() {
        if (S_Debug.m_debugWindow == null && !S_Debug.m_windowMode) {
            S_Debug.m_debugWindow = new S_DebugWindow();
        }
        S_Debug.m_debugWindow.show();
        S_Debug.m_windowMode = true;
    }
    
    public static void closeWindow() {
        if (S_Debug.m_debugWindow != null) {
            S_Debug.m_debugWindow.hide();
            S_Debug.m_debugWindow.dispose();
            S_Debug.m_debugWindow = null;
        }
        S_Debug.m_windowMode = false;
    }
    
    public static void print(final String s) {
        if (S_Debug.m_showDebug) {
            if (S_Debug.m_windowMode) {
                S_Debug.m_debugWindow.appendText(s + "\n");
            }
            else {
                System.out.print("Debug: " + s + "\r\n");
            }
        }
    }
    
    public static void print(final Object o, final String s) {
        if (S_Debug.m_showDebug) {
            print(o.getClass().getName() + ": " + s);
        }
    }
    
    public static void print(final Exception ex) {
        print("" + ex);
        print("" + ex.getMessage());
        ex.printStackTrace(new PrintStream(S_Debug.m_currentInstance, true));
    }
    
    public void write(final int n) {
        if (S_Debug.m_showDebug) {
            String string;
            if (n == 10 && !S_Debug.m_windowMode) {
                string = "\r\n";
            }
            else {
                string = "" + (char)n;
            }
            this.m_outBuffer += string;
            if (n == 10) {
                if (S_Debug.m_windowMode) {
                    S_Debug.m_debugWindow.appendText(this.m_outBuffer);
                }
                else {
                    System.out.print(this.m_outBuffer);
                }
                this.m_outBuffer = "";
            }
        }
    }
    
    static {
        new S_Debug();
    }
}
