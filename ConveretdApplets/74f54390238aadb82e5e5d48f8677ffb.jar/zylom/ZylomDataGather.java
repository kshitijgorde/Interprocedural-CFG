// 
// Decompiled by Procyon v0.5.30
// 

package zylom;

import com.zylom.thirdparty.GameProperties;
import com.zylom.thirdparty.ZylomHelper;
import java.applet.Applet;

public class ZylomDataGather
{
    public static boolean bugfix;
    public static Applet gameapplet;
    private static ZylomDataGather instance;
    public static int level;
    public ZylomHelper m_helper;
    private Properties m_props;
    public static boolean playing;
    
    static {
        ZylomDataGather.instance = null;
        ZylomDataGather.playing = false;
        ZylomDataGather.level = 0;
        ZylomDataGather.bugfix = true;
        ZylomDataGather.gameapplet = null;
    }
    
    private ZylomDataGather(final Applet app) {
        ZylomDataGather.gameapplet = app;
        (this.m_props = new Properties()).init(app);
        this.m_helper = new ZylomHelper(app, this.m_props);
    }
    
    public static ZylomHelper GetHelper() {
        if (ZylomDataGather.instance == null) {
            System.out.println("#Z001 No instance made of the Zylom Data Gather use ZylomDataGather.Start()");
            return null;
        }
        return ZylomDataGather.instance.m_helper;
    }
    
    public static Properties GetProp() {
        if (ZylomDataGather.instance == null) {
            System.out.println("#Z001 No instance made of the Zylom Data Gather use ZylomDataGather.Start()");
            return null;
        }
        return ZylomDataGather.instance.m_props;
    }
    
    public static void Start(final Applet app) {
        if (ZylomDataGather.instance != null) {
            Stop();
        }
        ZylomDataGather.instance = new ZylomDataGather(app);
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
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
