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

public class UrlFind extends Applet implements Runnable
{
    private String BUTTON_LABEL;
    private Choice urlMenu;
    private Button goButton;
    private Thread runner;
    private String[] url;
    private String[] target;
    private String address;
    private Vector urlList;
    private String baseDisplayFrame;
    
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
            final int num = this.urlMenu.getSelectedIndex();
            this.address = this.urlList.elementAt(num);
            try {
                final URL u = new URL(this.address);
                final AppletContext c = this.getAppletContext();
                if (c != null) {
                    if (this.target[this.urlMenu.getSelectedIndex()] != "none") {
                        c.showDocument(u, this.target[this.urlMenu.getSelectedIndex()]);
                    }
                    else if (this.baseDisplayFrame == "0") {
                        c.showDocument(u);
                    }
                    else if (this.baseDisplayFrame != "0") {
                        c.showDocument(u, this.baseDisplayFrame);
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
            this.baseDisplayFrame = "0";
        }
        else {
            this.baseDisplayFrame = strTargetFrm;
        }
        this.BUTTON_LABEL = this.getParameter("butLabel");
        if (this.BUTTON_LABEL == null) {
            this.BUTTON_LABEL = "GO!";
        }
        final String strBgR = this.getParameter("bgRVal");
        int bgR;
        if (strBgR == null) {
            bgR = 192;
        }
        else {
            bgR = Integer.parseInt(strBgR);
        }
        final String strBgG = this.getParameter("bgGVal");
        int bgG;
        if (strBgG == null) {
            bgG = 192;
        }
        else {
            bgG = Integer.parseInt(strBgG);
        }
        final String strBgB = this.getParameter("bgBVal");
        int bgB;
        if (strBgB == null) {
            bgB = 192;
        }
        else {
            bgB = Integer.parseInt(strBgB);
        }
        final String strMenuR = this.getParameter("menuRVal");
        int menuR;
        if (strMenuR == null) {
            menuR = 207;
        }
        else {
            menuR = Integer.parseInt(strMenuR);
        }
        final String strMenuG = this.getParameter("menuGVal");
        int menuG;
        if (strMenuG == null) {
            menuG = 201;
        }
        else {
            menuG = Integer.parseInt(strMenuG);
        }
        final String strMenuB = this.getParameter("menuBVal");
        int menuB;
        if (strMenuB == null) {
            menuB = 211;
        }
        else {
            menuB = Integer.parseInt(strMenuB);
        }
        final String strButR = this.getParameter("butRVal");
        int butR;
        if (strButR == null) {
            butR = 207;
        }
        else {
            butR = Integer.parseInt(strButR);
        }
        final String strButG = this.getParameter("butGVal");
        int butG;
        if (strButG == null) {
            butG = 201;
        }
        else {
            butG = Integer.parseInt(strButG);
        }
        final String strButB = this.getParameter("butBVal");
        int butB;
        if (strButB == null) {
            butB = 211;
        }
        else {
            butB = Integer.parseInt(strButB);
        }
        this.urlList = new Vector();
        final String strNum = this.getParameter("numItems");
        numItems = Integer.parseInt(strNum);
        final String[] item = new String[numItems];
        this.url = new String[numItems];
        this.target = new String[numItems];
        if (bgR > -1 && bgR < 256 && bgG > -1 && bgG < 256 && bgB > -1 && bgB < 256) {
            final Color bgColor = new Color(bgR, bgG, bgB);
            this.setBackground(bgColor);
        }
        else {
            this.setBackground(Color.blue);
        }
        this.urlMenu = new Choice();
        if (menuR > -1 && menuR < 256 && menuG > -1 && menuG < 256 && menuB > -1 && menuB < 256) {
            final Color menuColor = new Color(menuR, menuG, menuB);
            this.urlMenu.setBackground(menuColor);
        }
        else {
            this.urlMenu.setBackground(Color.lightGray);
        }
        this.goButton = new Button(this.BUTTON_LABEL);
        if (butR > -1 && butR < 256 && butG > -1 && butG < 256 && butB > -1 && butB < 256) {
            final Color butColor = new Color(butR, butG, butB);
            this.goButton.setBackground(butColor);
        }
        else {
            this.goButton.setBackground(Color.lightGray);
        }
        for (int i = 0; i < numItems; ++i) {
            item[i] = this.getParameter("item" + i);
            this.urlMenu.addItem(item[i]);
            this.url[i] = this.getParameter("url" + i);
            this.urlList.addElement(this.url[i]);
            final String newTarget = this.getParameter("newTarget" + i);
            if (newTarget == null) {
                this.target[i] = "none";
            }
            else {
                this.target[i] = newTarget;
            }
        }
        this.urlMenu.show();
        this.add(this.urlMenu);
        this.add(this.goButton);
    }
}
