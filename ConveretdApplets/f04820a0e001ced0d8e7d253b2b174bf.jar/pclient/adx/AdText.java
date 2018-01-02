// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adx;

import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import pclient.shd.Config;
import java.awt.Color;
import com.pchat.sc.StringUtil;
import java.awt.Component;
import java.awt.BorderLayout;
import com.pchat.sc.WindowUtil;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import pclient.adv.AppletSpice;
import pclient.adv.ComInter;
import javax.swing.JPanel;

public class AdText extends JPanel implements ComInter
{
    private AppletSpice parentApplet;
    private TextHyper[] hyperLinks;
    private TextAttribute[] textObjects;
    private boolean isAnimated;
    private AnimPanel animPanel;
    private int displayTime;
    private int displayRounds;
    private int fontSize;
    private boolean sizeDefined;
    private static final int MAX_COMPONENT = 6;
    
    public AdText() {
        this.textObjects = null;
        this.isAnimated = false;
        this.animPanel = null;
        this.displayTime = 20;
        this.displayRounds = 0;
        this.fontSize = 14;
        this.sizeDefined = false;
    }
    
    public void process(final int n, final String[] array) {
    }
    
    public void restart() {
    }
    
    public void destroy() {
        if (this.animPanel != null) {
            this.animPanel.stopIt();
        }
    }
    
    public void setPara(final AppletSpice parentApplet) {
        this.parentApplet = parentApplet;
        this.setOpaque(true);
        this.initData();
        this.initPanel();
    }
    
    private void initPanel() {
        this.getConfig();
        this.parentApplet.paraConf.printer().print("displayTime = " + this.displayTime);
        this.parentApplet.paraConf.printer().print("displayRounds = " + this.displayRounds);
        this.parentApplet.paraConf.printer().print("fontSize = " + this.fontSize);
        this.parentApplet.paraConf.printer().print("sizeDefined = " + this.sizeDefined);
        this.printAds();
        if (!this.isAnimated) {
            this.parentApplet.paraConf.printer().print("text ad, add static text");
            this.addStatic();
        }
        else {
            this.parentApplet.paraConf.printer().print("text ad, add animated text");
            this.addAnimation();
        }
    }
    
    private void initData() {
        this.hyperLinks = new TextHyper[6];
        this.textObjects = new TextAttribute[6];
        for (int i = 0; i < 6; ++i) {
            this.textObjects[i] = new TextAttribute();
            this.hyperLinks[i] = null;
        }
        this.setLayout(new GridLayout(1, 2));
    }
    
    private void addAnimation() {
        for (int i = 0; i < 6; ++i) {
            this.textObjects[i].foreground = WindowUtil.parseColor(this.textObjects[i].fg, this.textObjects[i].foreground);
            this.textObjects[i].background = WindowUtil.parseColor(this.textObjects[i].bg, this.textObjects[i].background);
        }
        this.animPanel = new AnimPanel(this.parentApplet, this.textObjects, this.displayTime, this.displayRounds, this.fontSize);
        this.setLayout(new BorderLayout());
        this.add("Center", this.animPanel);
    }
    
    private void addStatic() {
        this.parentApplet.paraConf.printer().print("text ad=static");
        for (int i = 0; i < 6; ++i) {
            if (this.textObjects[i].isActive) {
                this.parentApplet.paraConf.printer().print("text ad #" + i);
                this.addHyper(i);
                this.hyperLinks[i].setHyper(this.textObjects[i].dText, this.textObjects[i].linkText);
            }
        }
    }
    
    private void addHyper(final int n) {
        (this.hyperLinks[n] = new TextHyper()).setApplet(this.parentApplet);
        if (this.sizeDefined) {
            this.hyperLinks[n].setFontSize(this.fontSize);
        }
        this.parentApplet.paraConf.printer().print("text fg=" + this.textObjects[n].fg);
        this.parentApplet.paraConf.printer().print("text bg=" + this.textObjects[n].bg);
        if (!StringUtil.isTrimmedEmpty(this.textObjects[n].fg)) {
            try {
                this.hyperLinks[n].setForeground(new Color(Integer.parseInt(this.textObjects[n].fg, 16)));
            }
            catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        Color color = null;
        if (!StringUtil.isTrimmedEmpty(this.textObjects[n].bg)) {
            try {
                color = new Color(Integer.parseInt(this.textObjects[n].bg, 16));
                this.hyperLinks[n].setBackground(color);
            }
            catch (NumberFormatException ex2) {
                ex2.printStackTrace();
            }
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(1, 1));
        panel.add("Center", this.hyperLinks[n]);
        this.add(panel);
        if (color != null) {
            panel.setBackground(color);
        }
    }
    
    private void getConfig() {
        boolean scripts = false;
        final String value = this.parentApplet.paraConf.get("Net.Site");
        if (value != null) {
            scripts = this.readScripts(Config.getSitesDir() + "/" + value.trim() + ".st" + "/" + "panel.ad" + "/" + "text.cf");
        }
        if (scripts) {
            return;
        }
        this.readScripts("appletconf/panel.ad/text.cf");
    }
    
    private boolean readScripts(final String s) {
        final Vector file = this.readFile(this.parentApplet, s);
        if (file.size() == 0) {
            return false;
        }
        final int n = 32;
        for (int i = 0; i < file.size(); ++i) {
            final String s2 = file.elementAt(i);
            if (!s2.startsWith("#")) {
                if (s2.toLowerCase().startsWith("<html")) {
                    return false;
                }
                final int length = s2.length();
                final int index = s2.indexOf(n);
                if (index >= 0) {
                    final String substring = s2.substring(0, index);
                    if (index != length - 1) {
                        final int index2 = s2.indexOf(n, index + 1);
                        if (index2 >= 0) {
                            final String substring2 = s2.substring(index + 1, index2);
                            final int n2 = index2;
                            if (n2 != length - 1) {
                                final String substring3 = s2.substring(n2 + 1);
                                if (substring.equalsIgnoreCase("conf")) {
                                    this.parseConf(substring2, substring3);
                                }
                                else {
                                    int n3;
                                    if (substring.equalsIgnoreCase("ad1")) {
                                        n3 = 0;
                                    }
                                    else if (substring.equalsIgnoreCase("ad2")) {
                                        n3 = 1;
                                    }
                                    else if (substring.equalsIgnoreCase("ad3")) {
                                        n3 = 2;
                                    }
                                    else if (substring.equalsIgnoreCase("ad4")) {
                                        n3 = 3;
                                    }
                                    else if (substring.equalsIgnoreCase("ad5")) {
                                        n3 = 4;
                                    }
                                    else {
                                        if (!substring.equalsIgnoreCase("ad6")) {
                                            continue;
                                        }
                                        n3 = 5;
                                    }
                                    this.textObjects[n3].isActive = true;
                                    if (substring2.equalsIgnoreCase("text")) {
                                        this.textObjects[n3].dText = substring3;
                                    }
                                    else if (substring2.equalsIgnoreCase("link")) {
                                        this.textObjects[n3].linkText = substring3;
                                    }
                                    else if (substring2.equalsIgnoreCase("fg")) {
                                        this.textObjects[n3].fg = substring3;
                                    }
                                    else if (substring2.equalsIgnoreCase("bg")) {
                                        this.textObjects[n3].bg = substring3;
                                    }
                                    if (this.textObjects[n3].fg != null) {
                                        this.textObjects[n3].fg = this.textObjects[n3].fg.trim();
                                    }
                                    if (this.textObjects[n3].bg != null) {
                                        this.textObjects[n3].bg = this.textObjects[n3].bg.trim();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private void parseConf(String trim, String trim2) {
        trim = trim.trim();
        trim2 = trim2.trim();
        if (trim.equalsIgnoreCase("style")) {
            if (trim2.equalsIgnoreCase("static")) {
                this.isAnimated = false;
            }
            else if (trim2.equalsIgnoreCase("animated")) {
                this.isAnimated = true;
            }
        }
        else if (trim.equalsIgnoreCase("displaytime")) {
            this.displayTime = WindowUtil.parseInt(trim2, this.displayTime);
        }
        else if (trim.equalsIgnoreCase("rounds")) {
            this.displayRounds = WindowUtil.parseInt(trim2, this.displayRounds);
        }
        else if (trim.equalsIgnoreCase("fontsize")) {
            this.sizeDefined = true;
            this.fontSize = WindowUtil.parseInt(trim2, this.fontSize);
        }
    }
    
    private Vector readFile(final AppletSpice appletSpice, final String s) {
        final Vector<String> vector = new Vector<String>(16);
        try {
            final URLConnection openConnection = new URL(appletSpice.paraConf.getApplet().getCodeBase(), s).openConnection();
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                vector.addElement(line);
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            appletSpice.paraConf.printer().print("Warn892,loading text_ad," + ex);
        }
        catch (Exception ex2) {
            System.out.println("#Error 87523");
            ex2.printStackTrace();
        }
        return vector;
    }
    
    private void printAds() {
        for (int i = 0; i < 6; ++i) {
            this.parentApplet.paraConf.printer().print(this.textObjects[i].toString());
        }
    }
}
