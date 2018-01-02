import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fader extends Applet implements Runnable, MouseListener
{
    XPanel source;
    XPanel target;
    XPanel buffer;
    XPanel[] pnlPanels;
    int currentPnl;
    Thread DrawThread;
    boolean isDone;
    int stepWidth;
    int stepPause;
    int endPause;
    String mode;
    String sourceFile;
    String targetFile;
    String linkFile;
    String linkTarget;
    String[] sourcesFile;
    String[] linksFile;
    boolean countUp;
    String strOnClick;
    
    public Fader() {
        this.currentPnl = 0;
        this.isDone = false;
        this.stepWidth = 10;
        this.stepPause = 5;
        this.endPause = 1000;
        this.mode = "loop_oneway";
        this.linkFile = "";
        this.linkTarget = "";
        this.sourcesFile = null;
        this.linksFile = null;
        this.countUp = true;
        this.strOnClick = "open link";
    }
    
    public void init() {
        final String parameter = this.getParameter("width");
        int intValue;
        if (parameter != null) {
            intValue = Integer.valueOf(parameter);
        }
        else {
            intValue = 320;
        }
        final String parameter2 = this.getParameter("height");
        int intValue2;
        if (parameter2 != null) {
            intValue2 = Integer.valueOf(parameter2);
        }
        else {
            intValue2 = 160;
        }
        final String parameter3 = this.getParameter("stepWidth");
        if (parameter3 != null) {
            this.stepWidth = Integer.valueOf(parameter3);
        }
        else {
            this.stepWidth = 10;
        }
        final String parameter4 = this.getParameter("stepPause");
        if (parameter4 != null) {
            this.stepPause = Integer.valueOf(parameter4);
        }
        else {
            this.stepPause = 5;
        }
        final String parameter5 = this.getParameter("endPause");
        if (parameter5 != null) {
            this.endPause = Integer.valueOf(parameter5);
        }
        else {
            this.endPause = 1000;
        }
        final String parameter6 = this.getParameter("mode");
        if (parameter6 != null) {
            this.mode = parameter6;
        }
        final String parameter7 = this.getParameter("sources");
        if (parameter7 != null) {
            this.sourcesFile = this.splitStringIntoArray(parameter7, ",");
        }
        final String parameter8 = this.getParameter("links");
        if (parameter8 != null) {
            this.linksFile = this.splitStringIntoArray(parameter8, ",");
        }
        final String parameter9 = this.getParameter("source");
        if (parameter9 != null) {
            this.sourceFile = parameter9;
        }
        else {
            this.sourceFile = "";
        }
        final String parameter10 = this.getParameter("target");
        if (parameter10 != null) {
            this.targetFile = parameter10;
        }
        else {
            this.targetFile = "";
        }
        if (!this.sourceFile.equals("") && !this.targetFile.equals("")) {
            (this.sourcesFile = new String[2])[0] = this.sourceFile;
            this.sourcesFile[1] = this.targetFile;
        }
        final String parameter11 = this.getParameter("link");
        if (parameter11 != null) {
            this.linkFile = parameter11;
        }
        else {
            this.linkFile = "";
        }
        if (!this.linkFile.equals("")) {
            (this.linksFile = new String[2])[0] = this.linkFile;
            this.linksFile[1] = this.linkFile;
        }
        final String parameter12 = this.getParameter("linkTarget");
        if (parameter12 != null) {
            this.linkTarget = parameter12;
        }
        else {
            this.linkTarget = "";
        }
        final String parameter13 = this.getParameter("onClick");
        if (parameter13 != null) {
            this.strOnClick = parameter13;
        }
        this.pnlPanels = new XPanel[this.sourcesFile.length];
        for (int i = 0; i < this.sourcesFile.length; ++i) {
            (this.pnlPanels[i] = new XPanel(intValue, intValue2)).fill(this.getImage(this.getCodeBase(), this.sourcesFile[i]));
        }
        this.buffer = new XPanel(intValue, intValue2);
        this.addMouseListener(this);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!mouseEvent.isPopupTrigger()) {
            if (!mouseEvent.isControlDown()) {
                try {
                    if (this.strOnClick.equals("goto next")) {
                        this.gotoNextImage();
                    }
                    else if (this.strOnClick.equals("open link")) {
                        this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.linksFile[this.curPnl()]), this.linkTarget);
                    }
                }
                catch (Exception ex) {
                    System.out.println("error: " + mouseEvent.toString());
                }
                return;
            }
        }
        try {
            this.getAppletContext().showDocument(new URL("http://enriques.mathematik.uni-mainz.de/cubicsurface/tools/fader/fader.php"), "_new");
        }
        catch (Exception ex2) {}
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public XPanel crossFade(final XPanel xPanel, final XPanel xPanel2, final int n) {
        final XPanel xPanel3 = new XPanel(xPanel2.getWidth(), xPanel2.getHeight());
        for (int i = 0; i < xPanel2.getHeight(); ++i) {
            for (int j = 0; j < xPanel2.getWidth(); ++j) {
                final int pixel = xPanel.getPixel(j, i);
                final int pixel2 = xPanel2.getPixel(j, i);
                xPanel3.setPixel(j, i, -16777216 + (((pixel >> 16 & 0xFF) * n + (pixel2 >> 16 & 0xFF) * (100 - n)) / 100 << 16) + (((pixel >> 8 & 0xFF) * n + (pixel2 >> 8 & 0xFF) * (100 - n)) / 100 << 8) + ((pixel & 0xFF) * n + (pixel2 & 0xFF) * (100 - n)) / 100);
            }
        }
        return xPanel3;
    }
    
    public int curPnl() {
        return this.currentPnl;
    }
    
    public int maxPnl() {
        return this.sourcesFile.length - 1;
    }
    
    public int nextPnl() {
        if (this.countUp) {
            if (this.currentPnl < this.maxPnl()) {
                return this.currentPnl + 1;
            }
            return 0;
        }
        else {
            if (this.currentPnl > 0) {
                return this.currentPnl - 1;
            }
            return this.maxPnl();
        }
    }
    
    public void nextImage() {
        this.currentPnl = this.nextPnl();
    }
    
    public void prevImage() {
        if (this.curPnl() > 1) {
            --this.currentPnl;
        }
    }
    
    public void gotoNextImage() {
        this.stop();
        this.nextImage();
        this.start();
    }
    
    public void nextImage(final String s) {
        if (this.sourcesFile.length == 2) {
            this.stop();
            this.sourcesFile[0] = this.sourcesFile[1];
            this.sourcesFile[1] = s;
            this.source.fill(this.getImage(this.getCodeBase(), this.sourceFile));
            this.target.fill(this.getImage(this.getCodeBase(), this.targetFile));
            this.start();
        }
    }
    
    public void start() {
        (this.DrawThread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.DrawThread != null) {
            try {
                this.DrawThread.stop();
                this.DrawThread = null;
                this.showStatus("fader stopped");
            }
            catch (Exception ex) {}
        }
    }
    
    public void destroy() {
        this.DrawThread = null;
    }
    
    public void run() {
        int n = -this.stepWidth;
        int n2 = 0;
        this.countUp = true;
        this.showStatus("fader running...");
        if (Thread.currentThread() != this.DrawThread) {
            return;
        }
        while (true) {
            System.out.println("coutUp:" + this.countUp + ", frameNo:" + n + ", rounds:" + n2 + ", curPnl:" + this.curPnl() + ", nextPnl:" + this.nextPnl());
            if (this.countUp) {
                if (n + this.stepWidth > 100) {
                    if (this.curPnl() == this.maxPnl() - 1) {
                        n = 100;
                    }
                    else {
                        n = 0;
                        this.nextImage();
                    }
                }
                else {
                    n += this.stepWidth;
                }
            }
            else if (n - this.stepWidth < 0) {
                if (this.curPnl() == 1) {
                    n = 0;
                }
                else {
                    n = 100;
                    this.nextImage();
                }
            }
            else {
                n -= this.stepWidth;
            }
            int n3;
            if (n == 100 || n == 0) {
                n3 = this.endPause;
            }
            else {
                n3 = this.stepPause;
            }
            this.isDone = false;
            final long currentTimeMillis = System.currentTimeMillis();
            System.out.println(this.curPnl() + "," + this.nextPnl() + "," + this.maxPnl());
            if (this.countUp) {
                this.buffer = this.crossFade(this.pnlPanels[this.curPnl()], this.pnlPanels[this.nextPnl()], 100 - n);
            }
            else {
                this.buffer = this.crossFade(this.pnlPanels[this.curPnl()], this.pnlPanels[this.nextPnl()], n);
            }
            final long n4 = currentTimeMillis - System.currentTimeMillis();
            this.isDone = true;
            this.update(this.getGraphics());
            if (this.mode.compareTo("onClick") == 0 && n >= 100) {
                this.stop();
            }
            if (n >= 100 && (this.mode.compareTo("bothways") == 0 || this.mode.compareTo("loop_bothways") == 0)) {
                if (this.curPnl() == this.maxPnl() - 1 && this.countUp) {
                    ++this.currentPnl;
                    n = 100;
                    this.countUp = false;
                }
            }
            else if (n <= 0 && (this.mode.compareTo("bothways") == 0 || this.mode.compareTo("loop_bothways") == 0) && this.curPnl() == 1 && !this.countUp) {
                --this.currentPnl;
                this.countUp = true;
                n = 0;
                ++n2;
            }
            if (n >= 100) {
                if (this.curPnl() == this.maxPnl() - 1 && this.mode.compareTo("oneway") == 0) {
                    this.stop();
                }
                else if (this.mode.compareTo("loop_oneway") == 0) {
                    n = 0;
                    if (this.curPnl() == this.maxPnl()) {
                        this.currentPnl = 0;
                    }
                    else {
                        this.nextImage();
                    }
                }
            }
            if (n == 0 && n2 == 1 && this.mode.compareTo("bothways") == 0) {
                this.stop();
            }
            try {
                if (n4 >= n3) {
                    continue;
                }
                Thread.sleep(n3 - n4);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.isDone) {
            graphics.drawImage(this.buffer.getImage(), 0, 0, null);
        }
    }
    
    public String getAppletInfo() {
        return "written by Oliver Labs";
    }
    
    public String[] splitStringIntoArray(final String s, final String s2) {
        final Vector vector = new Vector<String>();
        for (int index = 0, n = -1; n < s.length() && index >= 0; n = index) {
            ++n;
            index = s.indexOf(s2, n);
            if (index < 0) {
                vector.addElement(s.substring(n).trim());
            }
            else {
                vector.addElement(s.substring(n, index).trim());
            }
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
}
