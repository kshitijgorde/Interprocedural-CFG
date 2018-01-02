// 
// Decompiled by Procyon v0.5.30
// 

package zylom;

import com.zylom.thirdparty.GameProperties;
import java.applet.Applet;
import com.zylom.thirdparty.ZylomHelper;

public class ZylomDataGather
{
    private static ZylomDataGather instance;
    public static boolean playing;
    public static int level;
    public static boolean bugfix;
    public ZylomHelper m_helper;
    private Properties m_props;
    
    public static ZylomHelper GetHelper() {
        if (ZylomDataGather.instance == null) {
            System.out.println("#Z001 No instance made of the Zylom Data Gather use ZylomDataGather.Start()");
            return null;
        }
        return ZylomDataGather.instance.m_helper;
    }
    
    public static void Start(final Applet applet) {
        if (!ZylomDataGather.bugfix) {
            return;
        }
        ZylomDataGather.bugfix = false;
        if (ZylomDataGather.instance != null) {
            Stop();
        }
        ZylomDataGather.instance = new ZylomDataGather(applet);
    }
    
    public static void Stop() {
        if (ZylomDataGather.instance == null) {
            System.out.println("#Z003 No instance made of the Zylom Data Gather you cant stop it.");
            return;
        }
        ZylomDataGather.instance.m_helper.stopPlayTimer();
        ZylomDataGather.instance.m_helper.indicateAppletDeath();
        ZylomDataGather.instance = null;
        try {
            Thread.sleep(1000L);
        }
        catch (Exception ex) {}
    }
    
    private ZylomDataGather(final Applet applet) {
        (this.m_props = new Properties()).init(applet);
        this.m_helper = new ZylomHelper(applet, this.m_props);
    }
    
    static {
        ZylomDataGather.instance = null;
        ZylomDataGather.playing = false;
        ZylomDataGather.level = 0;
        ZylomDataGather.bugfix = false;
    }
}
