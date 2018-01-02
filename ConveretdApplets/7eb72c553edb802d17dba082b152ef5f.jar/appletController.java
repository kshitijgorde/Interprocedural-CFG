import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class appletController
{
    private static Applet m_jigSaw;
    private static Applet m_controller;
    
    public static synchronized Applet getJigSaw() {
        if (appletController.m_jigSaw == null) {
            try {
                Class.forName("appletController").wait();
            }
            catch (Exception ex) {}
        }
        return appletController.m_jigSaw;
    }
    
    public static synchronized void setJigSaw(final Applet p) {
        appletController.m_jigSaw = p;
        try {
            Class.forName("appletController").notifyAll();
        }
        catch (ClassNotFoundException ex) {}
    }
    
    public static synchronized Applet getController() {
        if (appletController.m_controller == null) {
            try {
                Class.forName("appletController").wait();
            }
            catch (Exception ex) {}
        }
        return appletController.m_controller;
    }
    
    public static synchronized void setController(final Applet p) {
        appletController.m_controller = p;
        try {
            Class.forName("appletController").notifyAll();
        }
        catch (ClassNotFoundException ex) {}
    }
    
    static {
        appletController.m_jigSaw = null;
        appletController.m_controller = null;
    }
}
