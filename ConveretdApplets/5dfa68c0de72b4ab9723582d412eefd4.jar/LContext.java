import java.io.PrintStream;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

class LContext
{
    static final boolean isApplet = true;
    Hashtable oblist;
    Hashtable props;
    MapList iline;
    Symbol cfun;
    Symbol ufun;
    Object ufunresult;
    Object juststop;
    boolean mustOutput;
    boolean timeToStop;
    int priority;
    Object[] locals;
    String errormessage;
    String codeBase;
    String projectURL;
    PlayerCanvas canvas;
    Sprite who;
    Thread logoThread;
    PrintStream tyo;
    boolean autostart;
    
    LContext() {
        this.oblist = new Hashtable();
        this.props = new Hashtable();
        this.juststop = new Object();
        this.priority = 0;
    }
}
