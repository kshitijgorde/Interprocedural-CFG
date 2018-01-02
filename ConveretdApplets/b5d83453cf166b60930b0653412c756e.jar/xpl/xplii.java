// 
// Decompiled by Procyon v0.5.30
// 

package xpl;

import java.awt.Cursor;
import java.io.IOException;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Label;
import java.applet.Applet;

public class xplii extends Applet
{
    boolean isStandalone;
    int pX;
    int pY;
    int extPreset;
    int appW;
    int appH;
    int presetId;
    int maxPresets;
    String[] paramsStr;
    XplView xpv;
    Label label;
    
    public xplii() {
        this.isStandalone = false;
        this.pX = 100;
        this.pY = 100;
        this.extPreset = -1;
        this.appW = 100;
        this.appH = 100;
        this.presetId = (int)(Math.random() * 17);
        this.maxPresets = 16;
        this.xpv = null;
        this.label = new Label();
        (this.paramsStr = new String[20])[0] = new String("<>2.118.136.20.179.73.35.14.9.1.157.43.2.324.1.");
        this.paramsStr[1] = new String("<>154.63.30.68.20.60.39.5.5.40.118.40.0.2.1.");
        this.paramsStr[2] = new String("<>172.74.82.79.56.64.0.5.1.28.103.108.3.180.180.");
        this.paramsStr[3] = new String("<>10.247.10.247.2.247.21.3.7.3.124.38.2.180.180.");
        this.paramsStr[4] = new String("<>2.247.2.247.2.247.0.7.7.29.124.76.2.180.180.");
        this.paramsStr[5] = new String("<>2.247.55.116.60.60.41.5.9.5.187.23.2.180.180.");
        this.paramsStr[6] = new String("<>2.247.2.247.2.247.0.36.3.5.212.13.3.180.180.");
        this.paramsStr[7] = new String("<>141.93.5.63.2.247.0.12.5.25.214.17.1.180.180.");
        this.paramsStr[8] = new String("<>131.118.141.106.151.98.13.1.1.54.230.16.3.56.1.");
        this.paramsStr[9] = new String("<>2.247.2.247.2.247.0.23.2.24.55.293.1.180.180.");
        this.paramsStr[10] = new String("<>196.53.128.83.176.73.0.0.7.28.128.50.3.1.1.");
        this.paramsStr[11] = new String("<>126.123.128.121.148.100.15.0.2.28.225.17.3.206.181.");
        this.paramsStr[12] = new String("<>2.123.2.121.154.100.15.0.2.69.231.18.3.34.69.");
        this.paramsStr[13] = new String("<>108.88.75.116.191.60.26.9.9.5.282.24.1.180.180.");
        this.paramsStr[14] = new String("<>131.123.2.121.2.100.28.1.3.58.310.6.2.177.192.");
        this.paramsStr[15] = new String("<>2.242.2.179.2.186.0.22.5.26.183.115.0.1.202.");
        this.paramsStr[16] = new String("<>172.74.82.79.56.64.40.5.4.28.111.109.2.116.111.");
        this.maxPresets = 16;
    }
    
    public void init() {
        try {
            this.pX = Integer.parseInt(this.getParameter("percentX"));
            this.pY = Integer.parseInt(this.getParameter("percentY"));
            this.extPreset = Integer.parseInt(this.getParameter("preset"));
            this.pX = Math.max(10, Math.min(this.pX, 100));
            this.pY = Math.max(10, Math.min(this.pY, 100));
        }
        catch (Exception ex) {}
        try {
            this.jbInit();
        }
        catch (Exception ex2) {}
        if (this.xpv != null) {
            this.xpv.stop();
        }
        if (this.xpv != null) {
            this.xpv = null;
        }
        this.xpv = new XplView(this, 2, 2, (int)((this.appW - 4) * this.pX / 100.0), (int)((this.appH - 4) * this.pY / 100.0), this.appW - 4, this.appH - 4);
        this.xpv.callStart = true;
        this.xpv.stepsBeforeNew = 0;
        this.initByStr(this.paramsStr[this.presetId]);
        this.xpv.start();
    }
    
    private void jbInit() throws Exception {
        if (!this.getCodeBase().toString().equals("http://cz.itmo.by/javas/lifex/")) {
            this.label.setBackground(Color.white);
            this.label.setBounds(new Rectangle(3, 3, 90, 18));
            this.label.setForeground(Color.blue);
            this.label.setText("© Control-Zed");
        }
        this.label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                xplii.this.showHP();
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
                xplii.this.label_mouseExited(mouseEvent);
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                xplii.this.label_mouseEntered(mouseEvent);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                xplii.this.showHP();
            }
        });
        this.setLayout(null);
        this.add(this.label, null);
    }
    
    public String getAppletInfo() {
        return "Massaraksh";
    }
    
    void initByStr(final String s) {
        final String s2 = new String("");
        String concat = new String("");
        final int[] array = new int[20];
        int i = 2;
        int n = 0;
        while (i < s.length()) {
            final String substring = s.substring(i, i + 1);
            if (!substring.equals(".")) {
                concat = String.valueOf(concat).concat(String.valueOf(substring));
            }
            else {
                array[n] = new Integer(concat);
                concat = "";
                ++n;
            }
            ++i;
        }
        this.xpv.gammaR = array[0];
        this.xpv.gammadR = array[1];
        this.xpv.gammaG = array[2];
        this.xpv.gammadG = array[3];
        this.xpv.gammaB = array[4];
        this.xpv.gammadB = array[5];
        this.xpv.minR = array[6];
        this.xpv.deltaR = array[7];
        this.xpv.speed = array[8];
        this.xpv.spiralK = array[9];
        this.xpv.lifeTime = array[10];
        this.xpv.sleepK = array[11];
        this.xpv.shadeK = array[12];
        this.xpv.xg2r = array[13];
        this.xpv.yg2r = array[14];
    }
    
    public void resize(final int appW, final int appH) {
        super.resize(this.appW = appW, this.appH = appH);
    }
    
    public void showHP() {
        try {
            this.getAppletContext().showDocument(new URL("http://cz.itmo.by/javas/lifex"), "Mss");
        }
        catch (IOException ex) {}
    }
    
    public void start() {
        if (this.extPreset == -1) {
            this.presetId = (int)(Math.random() * this.maxPresets);
        }
        else {
            this.presetId = this.extPreset;
        }
        this.initByStr(this.paramsStr[this.presetId]);
    }
    
    void label_mouseExited(final MouseEvent mouseEvent) {
        this.label.setForeground(Color.blue);
        this.setCursor(new Cursor(0));
    }
    
    void label_mouseEntered(final MouseEvent mouseEvent) {
        this.label.setForeground(Color.red);
        this.setCursor(new Cursor(12));
    }
}
