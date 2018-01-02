import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class javaTest extends Applet implements Runnable
{
    Thread m_theThread;
    int m_fps;
    static boolean m_bRepaintAll;
    private Dimension m_dimWin;
    private static poolTable m_theTable;
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (size.width > this.m_dimWin.width || size.height > this.m_dimWin.height) {
            javaTest.m_bRepaintAll = true;
            this.m_dimWin = new Dimension(size.width, size.height);
        }
        if (javaTest.m_bRepaintAll) {
            this.paint(graphics);
        }
        else {
            javaTest.m_theTable.paint(graphics);
        }
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        javaTest.m_theTable.m_cue.drag(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        javaTest.m_theTable.store();
        javaTest.m_theTable.m_cue.release(n, n2);
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        javaTest.m_theTable.m_cue.activate(n, n2);
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        javaTest.m_theTable.restore();
        return javaTest.m_bRepaintAll = true;
    }
    
    public javaTest() {
        this.m_theThread = null;
        this.m_fps = 30;
        this.m_dimWin = new Dimension(0, 0);
    }
    
    public String getAppletInfo() {
        return "Name: javaTest\r\nAuthor: Dave Whipp\r\nCreated with Microsoft Visual J++ Version 1.1";
    }
    
    public void init() {
        this.setSize(800, 400);
        reset();
    }
    
    static void reset() {
        javaTest.m_theTable = new poolTable(800, 400);
    }
    
    public void destroy() {
    }
    
    public void paint(final Graphics graphics) {
        if (javaTest.m_theTable != null) {
            javaTest.m_theTable.paintAll(graphics);
        }
        javaTest.m_bRepaintAll = false;
    }
    
    public void start() {
        if (this.m_theThread == null) {
            (this.m_theThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_theThread != null) {
            this.m_theThread.stop();
            this.m_theThread = null;
        }
    }
    
    public void run() {
        final int n = 1000 / this.m_fps;
        final int n2 = 800 / this.m_fps;
    Label_0021:
        while (true) {
            break Label_0021;
            while (true) {
                try {
                    while (true) {
                        for (int i = 0; i < n2; ++i) {
                            javaTest.m_theTable.Animate();
                        }
                        this.repaint();
                        Thread.sleep(n);
                    }
                }
                catch (InterruptedException ex) {
                    this.stop();
                    continue;
                }
                continue Label_0021;
            }
            break;
        }
    }
    
    static {
        javaTest.m_bRepaintAll = true;
        javaTest.m_theTable = null;
    }
}
