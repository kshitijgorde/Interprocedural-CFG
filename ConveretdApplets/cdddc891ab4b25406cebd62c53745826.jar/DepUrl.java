import java.awt.Component;
import java.awt.Color;
import java.awt.Event;
import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.awt.Button;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DepUrl extends Applet implements Runnable
{
    private String BUTTON_LABEL;
    private Choice ch;
    private Button bt;
    private Thread runner;
    private String[] url;
    private String address;
    private Vector urlList;
    private String displayFrame;
    
    public void stop() {
        this.runner.stop();
        this.runner = null;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void buttonHandler(final String label) {
        if (label.equals(this.BUTTON_LABEL)) {
            final int num = this.ch.getSelectedIndex();
            this.address = this.urlList.elementAt(num);
            try {
                final URL u = new URL(this.address);
                final AppletContext c = this.getAppletContext();
                if (c != null) {
                    if (this.displayFrame == "0") {
                        c.showDocument(u);
                    }
                    else if (this.displayFrame != "0") {
                        c.showDocument(u, this.displayFrame);
                    }
                }
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    public boolean action(final Event evt, final Object arg) {
        if (evt.target instanceof Button) {
            this.buttonHandler((String)arg);
            return true;
        }
        return false;
    }
    
    public void run() {
        while (true) {
            try {
                this.validate();
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        int numItems = 0;
        final String strTargetFrm = this.getParameter("targetFrame");
        if (strTargetFrm == null || strTargetFrm == "0") {
            this.displayFrame = "0";
        }
        else {
            this.displayFrame = strTargetFrm;
        }
        this.BUTTON_LABEL = this.getParameter("butLabel");
        if (this.BUTTON_LABEL == null) {
            this.BUTTON_LABEL = "GO!";
        }
        final String strBgR = this.getParameter("bgRVal");
        int bgR;
        if (strBgR == null) {
            bgR = 0;
        }
        else {
            bgR = Integer.parseInt(strBgR);
        }
        final String strBgG = this.getParameter("bgGVal");
        int bgG;
        if (strBgG == null) {
            bgG = 0;
        }
        else {
            bgG = Integer.parseInt(strBgG);
        }
        final String strBgB = this.getParameter("bgBVal");
        int bgB;
        if (strBgB == null) {
            bgB = 255;
        }
        else {
            bgB = Integer.parseInt(strBgB);
        }
        final String strMenuR = this.getParameter("menuRVal");
        int menuR;
        if (strMenuR == null) {
            menuR = 192;
        }
        else {
            menuR = Integer.parseInt(strMenuR);
        }
        final String strMenuG = this.getParameter("menuGVal");
        int menuG;
        if (strMenuG == null) {
            menuG = 192;
        }
        else {
            menuG = Integer.parseInt(strMenuG);
        }
        final String strMenuB = this.getParameter("menuBVal");
        int menuB;
        if (strMenuB == null) {
            menuB = 192;
        }
        else {
            menuB = Integer.parseInt(strMenuB);
        }
        final String strButR = this.getParameter("butRVal");
        int butR;
        if (strButR == null) {
            butR = 192;
        }
        else {
            butR = Integer.parseInt(strButR);
        }
        final String strButG = this.getParameter("butGVal");
        int butG;
        if (strButG == null) {
            butG = 192;
        }
        else {
            butG = Integer.parseInt(strButG);
        }
        final String strButB = this.getParameter("butBVal");
        int butB;
        if (strButB == null) {
            butB = 192;
        }
        else {
            butB = Integer.parseInt(strButB);
        }
        this.urlList = new Vector();
        final String strNum = this.getParameter("numItems");
        numItems = Integer.parseInt(strNum);
        final String[] item = new String[numItems];
        this.url = new String[numItems];
        if (bgR > -1 && bgR < 256 && bgG > -1 && bgG < 256 && bgB > -1 && bgB < 256) {
            final Color bgColor = new Color(bgR, bgG, bgB);
            this.setBackground(bgColor);
        }
        else {
            this.setBackground(Color.blue);
        }
        this.ch = new Choice();
        if (menuR > -1 && menuR < 256 && menuG > -1 && menuG < 256 && menuB > -1 && menuB < 256) {
            final Color menuColor = new Color(menuR, menuG, menuB);
            this.ch.setBackground(menuColor);
        }
        else {
            this.ch.setBackground(Color.lightGray);
        }
        this.bt = new Button(this.BUTTON_LABEL);
        if (butR > -1 && butR < 256 && butG > -1 && butG < 256 && butB > -1 && butB < 256) {
            final Color butColor = new Color(butR, butG, butB);
            this.bt.setBackground(butColor);
        }
        else {
            this.bt.setBackground(Color.lightGray);
        }
        for (int i = 0; i < numItems; ++i) {
            item[i] = this.getParameter("item" + i);
            this.ch.addItem(item[i]);
            this.url[i] = this.getParameter("url" + i);
            this.urlList.addElement(this.url[i]);
        }
        this.ch.show();
        this.add(this.ch);
        this.add(this.bt);
    }
}
