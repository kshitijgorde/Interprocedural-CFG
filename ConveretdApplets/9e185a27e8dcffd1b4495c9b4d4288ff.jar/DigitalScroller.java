import java.util.Vector;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DigitalScroller extends Applet
{
    private DigitalPanel digi;
    private String paramColour;
    private String paramCells;
    private String paramScrollDelay;
    private String paramLoopDelay;
    private String paramLeft;
    private String paramTop;
    private String[] paramMessages;
    private int left;
    private int top;
    
    public DigitalScroller() {
        this.digi = null;
        final boolean b = false;
        this.top = (b ? 1 : 0);
        this.left = (b ? 1 : 0);
    }
    
    public void init() {
        final Image magiLabel = this.loadMagiLabel();
        this.readHTMLparams();
        if (this.digi == null) {
            (this.digi = new DigitalPanel(this.getScrollerColour(), this.left, this.top, Integer.parseInt(this.paramCells), magiLabel)).setDelay(Integer.parseInt(this.paramScrollDelay));
            this.digi.loop(Integer.parseInt(this.paramLoopDelay));
            this.setLayout(new BorderLayout());
            this.add(this.digi, "Center");
        }
    }
    
    private Image loadMagiLabel() {
        Image img = null;
        final MediaTracker mt = new MediaTracker(this);
        try {
            img = this.getImage(this.getCodeBase(), "magi.gif");
            mt.addImage(img, 0);
            mt.waitForID(0);
        }
        catch (Exception iex) {
            return null;
        }
        return img;
    }
    
    public void start() {
        this.digi.start();
        this.digi.showMessage(this.paramMessages);
    }
    
    public void stop() {
        this.digi.stop();
    }
    
    private Color getScrollerColour() {
        if (this.paramColour.equalsIgnoreCase("green")) {
            return Color.green;
        }
        if (this.paramColour.equalsIgnoreCase("red")) {
            return Color.red;
        }
        if (this.paramColour.equalsIgnoreCase("blue")) {
            return Color.blue;
        }
        if (this.paramColour.equalsIgnoreCase("white")) {
            return Color.white;
        }
        return Color.green;
    }
    
    public void readHTMLparams() {
        if (this.paramColour != null) {
            return;
        }
        this.paramColour = this.getParameter("Color");
        this.paramCells = this.getParameter("Cells");
        this.paramScrollDelay = this.getParameter("ScrollDelay");
        this.paramLoopDelay = this.getParameter("LoopDelay");
        this.paramLeft = this.getParameter("Left");
        this.paramTop = this.getParameter("Top");
        if (this.paramLeft != null) {
            this.left = Integer.parseInt(this.paramLeft);
        }
        if (this.paramTop != null) {
            this.top = Integer.parseInt(this.paramTop);
        }
        int i = 1;
        final Vector messages = new Vector();
        String paramMessage;
        while ((paramMessage = this.getParameter("Message".concat(String.valueOf(i)))) != null) {
            messages.addElement(paramMessage);
            ++i;
        }
        if (i == 1) {
            messages.addElement("Digital Scroller Error: No Message To Scroll");
        }
        final int numMessages = messages.size();
        this.paramMessages = new String[numMessages];
        for (i = 0; i < numMessages; ++i) {
            this.paramMessages[i] = messages.elementAt(i);
        }
        if (this.paramColour == null) {
            this.paramColour = "green";
        }
        if (this.paramCells == null) {
            this.paramCells = "10";
        }
        if (this.paramScrollDelay == null) {
            this.paramScrollDelay = "200";
        }
        if (this.paramLoopDelay == null) {
            this.paramLoopDelay = "500";
        }
    }
}
