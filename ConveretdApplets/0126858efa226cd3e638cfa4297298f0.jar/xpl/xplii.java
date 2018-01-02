// 
// Decompiled by Procyon v0.5.30
// 

package xpl;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.LayoutManager;
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
    int k3deffect;
    String[] paramsStr;
    XplView xpv;
    Label label;
    int[] key;
    String persetsFile;
    String urlStr;
    String target;
    String hintStr;
    
    public xplii() {
        this.isStandalone = false;
        this.pX = 100;
        this.pY = 100;
        this.extPreset = -1;
        this.appW = 100;
        this.appH = 100;
        this.presetId = 0;
        this.paramsStr = null;
        this.xpv = null;
        this.label = new Label();
    }
    
    public void init() {
        this.key = new int[3];
        try {
            this.pX = Integer.parseInt(this.getParameter("percentX"));
            this.pY = Integer.parseInt(this.getParameter("percentY"));
            this.extPreset = Integer.parseInt(this.getParameter("preset"));
            this.urlStr = this.getParameter("URL");
            this.target = this.getParameter("TARGET");
            if (this.target == null) {
                this.target = this.urlStr;
            }
            this.hintStr = this.getParameter("HINT");
            if (this.hintStr == null) {
                this.hintStr = this.urlStr;
            }
            this.persetsFile = this.getParameter("PRESETS_FILE");
            if (this.persetsFile == null) {
                this.persetsFile = "iii";
            }
            if (this.getParameter("k3deffect") != null) {
                this.k3deffect = Integer.parseInt(this.getParameter("k3deffect"));
            }
            this.key[0] = Integer.parseInt(this.getParameter("key1"));
            this.key[1] = Integer.parseInt(this.getParameter("key2"));
            this.key[2] = Integer.parseInt(this.getParameter("key3"));
            this.pX = Math.max(10, Math.min(this.pX, 100));
            this.pY = Math.max(10, Math.min(this.pY, 100));
        }
        catch (Exception ex) {}
        this.loadPresets(this.persetsFile);
        if (this.paramsStr == null) {
            (this.paramsStr = new String[3])[0] = new String("<>233.21.170.18.34.18.0.50.5.42.180.22.4.30.9.-99.-99.");
            this.maxPresets = 1;
            this.extPreset = 0;
            this.presetId = 0;
        }
        if (this.extPreset >= this.maxPresets) {
            this.extPreset = this.maxPresets - 1;
        }
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
        this.xpv = new XplView(this, 0, 0, (int)((this.appW - 0) * this.pX / 100.0), (int)((this.appH - 0) * this.pY / 100.0), this.appW - 0, this.appH - 0);
        this.xpv.callStart = true;
        this.xpv.stepsBeforeNew = 0;
        this.xpv.k3deffect = this.k3deffect;
        this.xpv.initByStr(this.paramsStr[this.presetId]);
    }
    
    private void jbInit() throws Exception {
        String.valueOf("host=").concat(String.valueOf(this.getCodeBase().getHost()));
        this.setBackground(Color.black);
        this.setEnabled(true);
        this.setLayout(null);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                xplii.this.showHP();
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                xplii.this.setCursor(new Cursor(12));
                xplii.this.showStatus(new String("hint"));
            }
        });
    }
    
    public String getAppletInfo() {
        return "Massaraksh";
    }
    
    public void resize(final int appW, final int appH) {
        super.resize(this.appW = appW, this.appH = appH);
    }
    
    public void showHP() {
        try {
            this.getAppletContext().showDocument(new URL(this.urlStr), this.target);
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
        this.xpv.initByStr(this.paramsStr[this.presetId]);
        if (this.xpv != null) {
            this.xpv.stop();
        }
        this.xpv.start();
        super.start();
    }
    
    public void stop() {
        if (this.xpv != null) {
            this.xpv.stop();
        }
        super.stop();
    }
    
    int makeKey(final String s) {
        char c = '\u9d6c';
        for (int i = 0; i < s.length(); ++i) {
            c += (char)(c * s.charAt(i) % '\u2824');
        }
        return c;
    }
    
    public void loadPresets(final String s) {
        final String[] array = new String[100];
        final URL resource = this.getClass().getResource(s);
        if (resource == null) {
            return;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.openStream()));
            String s2 = bufferedReader.readLine();
            int maxPresets = 0;
            while (s2 != null) {
                array[maxPresets] = s2;
                ++maxPresets;
                s2 = bufferedReader.readLine();
            }
            this.maxPresets = maxPresets;
            System.arraycopy(array, 0, this.paramsStr = new String[this.maxPresets], 0, this.maxPresets);
        }
        catch (IOException ex) {
            System.out.println(String.valueOf("Can't read presets file - ").concat(String.valueOf(s)));
        }
    }
    
    public void showStatus(final String s) {
        if (this.paramsStr == null) {
            return;
        }
        if (this.extPreset == -1) {
            this.presetId = (int)(Math.random() * this.maxPresets);
        }
        else {
            this.presetId = this.extPreset;
        }
        this.xpv.initByStr(this.paramsStr[this.presetId]);
        if (s.length() != 0) {
            super.showStatus(this.hintStr);
        }
    }
}
