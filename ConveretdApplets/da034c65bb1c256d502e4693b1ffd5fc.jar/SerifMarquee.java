import java.awt.Component;
import java.awt.Rectangle;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SerifMarquee extends Applet implements Runnable
{
    Thread m_SerifMarquee;
    Image m_backBuffer;
    Animation m_animation;
    boolean m_haveMouse;
    int m_mx;
    int m_my;
    private String m_Global;
    private String m_Event1;
    private String m_Event2;
    private String m_Event3;
    private String m_Event4;
    private String m_Event5;
    private String m_Event6;
    private String m_Event7;
    private String m_Event8;
    private String m_Event9;
    private String m_Event10;
    private final String PARAM_Global = "Global";
    private final String PARAM_Event1 = "Event1";
    private final String PARAM_Event2 = "Event2";
    private final String PARAM_Event3 = "Event3";
    private final String PARAM_Event4 = "Event4";
    private final String PARAM_Event5 = "Event5";
    private final String PARAM_Event6 = "Event6";
    private final String PARAM_Event7 = "Event7";
    private final String PARAM_Event8 = "Event8";
    private final String PARAM_Event9 = "Event9";
    private final String PARAM_Event10 = "Event10";
    
    public void stop() {
        if (this.m_SerifMarquee != null) {
            this.m_SerifMarquee.stop();
            this.m_SerifMarquee = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int mx, final int my) {
        this.m_haveMouse = true;
        this.m_mx = mx;
        this.m_my = my;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.m_haveMouse = false;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.m_backBuffer, 0, 0, null);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final String url = this.m_animation.getUrl(n, n2);
        if (url == null) {
            return false;
        }
        try {
            URL url2;
            if (url.startsWith("http://")) {
                url2 = new URL(url);
            }
            else {
                url2 = new URL(this.getDocumentBase(), url);
            }
            this.getAppletContext().showDocument(url2);
            this.getAppletContext().showStatus("");
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Global", "String", "Global Animation Parameters" }, { "Event1", "String", "First Animation Event" }, { "Event2", "String", "Second Animation Event" }, { "Event3", "String", "Third Animation Event" }, { "Event4", "String", "Fourth Animation Event" }, { "Event5", "String", "Fifth Animation Event" }, { "Event6", "String", "Sixth Animation Event" }, { "Event7", "String", "Seventh Animation Event" }, { "Event8", "String", "Eighth Animation Event" }, { "Event9", "String", "Ninth Animation Event" }, { "Event10", "String", "Tenth Animation Event" } };
    }
    
    public void destroy() {
    }
    
    public SerifMarquee() {
        this.m_Global = "LENGTH:10;FPS:20;BACKGROUND:0,0,0";
        this.m_Event1 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 1;FONT:ARIAL;STYLE:BOLD;COLOUR:255,64,64;MOTION:LSLIDEIN;";
        this.m_Event2 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 2;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event3 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 3;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event4 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 4;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event5 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 5;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event6 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 6;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event7 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 7;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event8 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 8;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event9 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 9;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_Event10 = "TYPE:TEXT;STARTIME:0;TEXT:TEXTSHOW 10;FONT:ARIAL;STYLE:BOLD;MOTION:LSLIDEIN;";
        this.m_animation = new Animation();
    }
    
    public AnimationEvent initEvent(final String s) {
        final String nextToken = new StringTokenizer(s, ";").nextToken();
        final int index = nextToken.indexOf(58);
        if (index != -1) {
            final String upperCase = nextToken.substring(0, index).toUpperCase();
            final String substring = nextToken.substring(index + 1);
            if (upperCase.equals("TYPE") && substring.equals("TEXT")) {
                return this.initTextEvent(s);
            }
        }
        return null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.m_SerifMarquee == null) {
            (this.m_SerifMarquee = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: SerifMarquee\r\nAuthor: Paul Hempstock for Serif (Europe) Ltd.";
    }
    
    void initEvents() {
        final String parameter = this.getParameter("Event1");
        if (parameter == null) {
            return;
        }
        this.m_Event1 = parameter;
        this.m_animation.addEvent(this.initEvent(this.m_Event1));
        final String parameter2 = this.getParameter("Event2");
        if (parameter2 == null) {
            return;
        }
        this.m_Event2 = parameter2;
        this.m_animation.addEvent(this.initEvent(this.m_Event2));
        final String parameter3 = this.getParameter("Event3");
        if (parameter3 == null) {
            return;
        }
        this.m_Event3 = parameter3;
        this.m_animation.addEvent(this.initEvent(this.m_Event3));
        final String parameter4 = this.getParameter("Event4");
        if (parameter4 == null) {
            return;
        }
        this.m_Event4 = parameter4;
        this.m_animation.addEvent(this.initEvent(this.m_Event4));
        final String parameter5 = this.getParameter("Event5");
        if (parameter5 == null) {
            return;
        }
        this.m_Event5 = parameter5;
        this.m_animation.addEvent(this.initEvent(this.m_Event5));
        final String parameter6 = this.getParameter("Event6");
        if (parameter6 == null) {
            return;
        }
        this.m_Event6 = parameter6;
        this.m_animation.addEvent(this.initEvent(this.m_Event6));
        final String parameter7 = this.getParameter("Event7");
        if (parameter7 == null) {
            return;
        }
        this.m_Event7 = parameter7;
        this.m_animation.addEvent(this.initEvent(this.m_Event7));
        final String parameter8 = this.getParameter("Event8");
        if (parameter8 == null) {
            return;
        }
        this.m_Event8 = parameter8;
        this.m_animation.addEvent(this.initEvent(this.m_Event8));
        final String parameter9 = this.getParameter("Event9");
        if (parameter9 == null) {
            return;
        }
        this.m_Event9 = parameter9;
        this.m_animation.addEvent(this.initEvent(this.m_Event9));
        final String parameter10 = this.getParameter("Event10");
        if (parameter10 != null) {
            this.m_Event10 = parameter10;
            this.m_animation.addEvent(this.initEvent(this.m_Event10));
        }
    }
    
    public void run() {
        final int n = 1000 / this.m_animation.m_fps;
        try {
            while (true) {
                this.m_animation.drawFrame(this.m_backBuffer);
                this.m_animation.incFrame();
                this.repaint();
                if (this.m_haveMouse) {
                    this.getAppletContext().showStatus(this.m_animation.getUrl(this.m_mx, this.m_my));
                }
                Thread.sleep(n);
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void init() {
        final Rectangle bounds = this.bounds();
        this.m_backBuffer = this.createImage(bounds.width, bounds.height);
        this.m_animation.setSize(bounds);
        final String parameter = this.getParameter("Global");
        if (parameter != null) {
            this.m_Global = parameter;
            this.m_animation.initFromParams(parameter);
        }
        this.initEvents();
        this.m_animation.drawFrame(this.m_backBuffer);
    }
    
    public AnimationEvent initTextEvent(final String s) {
        final TextEvent textEvent = new TextEvent(this, this.m_animation);
        textEvent.initFromParams(s);
        return textEvent;
    }
    
    public boolean mouseMove(final Event event, final int mx, final int my) {
        this.m_mx = mx;
        this.m_my = my;
        return true;
    }
}
