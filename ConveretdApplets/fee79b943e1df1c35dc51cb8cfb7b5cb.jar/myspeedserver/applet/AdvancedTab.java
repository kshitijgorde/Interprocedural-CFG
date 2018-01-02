// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Date;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.event.WindowListener;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.event.KeyListener;

public class AdvancedTab extends AppletTab implements Runnable, KeyListener
{
    private JFrame GV;
    private JTextArea HV;
    private myspeed TU;
    
    public AdvancedTab(final Applet applet) {
        super(applet, null, ((myspeed)applet).getImageFromJar("tabadvanced.gif"), "advanced");
        this.GV = new JFrame();
        this.HV = new JTextArea(18, 80);
        this.TU = (myspeed)applet;
    }
    
    public void doFirstTimeInit() {
        this.HV.setEditable(false);
        this.HV.setFont(new Font(TX("Courier"), 0, 12));
        this.HV.setBackground(new Color(16777215));
        this.HV.setForeground(new Color(0));
        this.GV.setTitle(String.valueOf(this.TU.appNameVer(true)) + TX(" Statistics"));
        this.GV.add(TX("Center"), new JScrollPane(this.HV));
        this.GV.setLocation(0, 0);
        this.GV.pack();
        this.GV.addWindowListener(this.TU);
        this.addKeyListener(this);
    }
    
    public void reset() {
    }
    
    public void panelPaint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setColor(new Color(238, 242, 248));
        graphics.fillRect(0, 0, width - 1, height - 1);
        FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height2 = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        final int iniGetInteger = this.iniGetInteger("advancedpaddingx", 100);
        final int iniGetInteger2 = this.iniGetInteger("advancedcols", 1);
        final int n = width - iniGetInteger;
        graphics.translate(iniGetInteger / 2, 0);
        final int max = Math.max(1, Math.min(n / 200, iniGetInteger2));
        graphics.setFont(new Font(TX("Helvetica"), 1, 13));
        graphics.setColor(Color.black);
        final int n2 = 10;
        final int n3 = n / max;
        int n4 = 0;
        int n5 = n2 + this.drawWrappedLines(graphics, this.RC(TX("advancedstats")), 65, n2, n - 70).height;
        n5 += 20;
        graphics.setFont(new Font(TX("Helvetica"), 0, 12));
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getShowStatsText(), TX(","));
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            String s = null;
            String s2 = null;
            String substring = null;
            final String[] advancedDataItem = this.getAdvancedDataItem(trim);
            if (advancedDataItem != null) {
                s = advancedDataItem[0];
                s2 = advancedDataItem[1];
            }
            else if (trim.indexOf(TX("header/")) >= 0) {
                substring = trim.substring(trim.indexOf(TX("header/")) + 7);
            }
            if (s != null && s2 != null) {
                if (n4 > 0) {
                    n5 -= height2;
                    graphics.drawLine(n3 * n4, n5 - ascent, n3 * n4, n5 - ascent + height2);
                }
                graphics.setColor(Color.black);
                graphics.drawString(s, 10 + n3 * n4, n5);
                graphics.drawString(s2, n3 * (n4 + 1) - fontMetrics.stringWidth(s2) - 10, n5);
                n5 += height2;
                n4 = (n4 + 1) % max;
            }
            else {
                if (substring == null) {
                    continue;
                }
                n5 += 5;
                final Font font = graphics.getFont();
                graphics.setFont(new Font(font.getName(), 1, font.getSize()));
                final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                graphics.drawString(substring, 30, n5);
                Util.horzGradientFill(graphics, 10, n5 + fontMetrics2.getDescent() + 3, n - 100, 2, new Color(66, 77, 248), new Color(238, 242, 248));
                n5 += fontMetrics2.getHeight() + fontMetrics2.getDescent() + 3 + 2;
                graphics.setFont(font);
                fontMetrics = graphics.getFontMetrics();
                n4 = 0;
            }
        }
        final String rc = this.RC(TX("showtextwindow"));
        if (!"".equals(rc) && !TX("?viewastext?").equals(rc)) {
            final int stringWidth = fontMetrics.stringWidth(rc);
            graphics.translate(-iniGetInteger / 2, 0);
            this.drawWrappedLines(graphics, String.valueOf(TX("<a href=viewastext>")) + rc + TX("</a>"), n - stringWidth - 5, height - fontMetrics.getHeight() - 5, 9999);
            graphics.translate(iniGetInteger / 2, 0);
        }
        graphics.translate(-iniGetInteger / 2, 0);
    }
    
    private String getShowStatsText() {
        String s = this.crlfToCommaTrim(this.RC(TX("showstats")));
        final AppletPlugin[] plugins = this.TU.getPlugins();
        for (int n = 0; plugins != null && n < plugins.length; ++n) {
            final String name = plugins[n].getName();
            final String rc = this.RC(String.valueOf(name) + TX(".showstats"));
            final StringTokenizer stringTokenizer = new StringTokenizer((rc == null) ? "" : rc, "\r\n");
            while (stringTokenizer.hasMoreTokens()) {
                s = String.valueOf(s) + TX(",") + name + "." + stringTokenizer.nextToken().trim();
            }
        }
        return s;
    }
    
    public boolean hasData() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getShowStatsText(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            if (this.getAdvancedDataItem(stringTokenizer.nextToken().trim()) != null) {
                return true;
            }
        }
        return false;
    }
    
    private String crlfToCommaTrim(final String s) {
        if (s == null) {
            return null;
        }
        return s.replace('\n', ',').replace('\r', ',').replace(' ', ',');
    }
    
    private void copyAdvancedText() {
        if (this.TU.isProfessional()) {
            this.HV.setText(this.getAdvancedText(this.getShowStatsText(), true));
            this.GV.setVisible(true);
            new Thread(this).start();
        }
    }
    
    public void run() {
        try {
            Thread.sleep(500L);
        }
        catch (Exception ex) {}
        this.GV.toFront();
        this.GV.setVisible(true);
    }
    
    private String getAdvancedText(final String s, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, TX(","));
        while (stringTokenizer.hasMoreTokens()) {
            final String trim = stringTokenizer.nextToken().trim();
            final String[] advancedDataItem = this.getAdvancedDataItem(trim);
            if (advancedDataItem != null) {
                sb.append(advancedDataItem[0]).append(": ").append(advancedDataItem[1]).append("\r\n");
            }
            else {
                if (trim.indexOf(TX("header/")) < 0) {
                    continue;
                }
                final String tx = TX("---------------------------------------------------------------------------------------------------");
                sb.append("\r\n");
                final String substring = trim.substring(trim.indexOf(TX("header/")) + 7);
                sb.append(substring).append("\r\n");
                sb.append(tx.substring(0, Math.min(tx.length() - 1, substring.length()))).append("\r\n");
            }
        }
        if (b) {
            final String iniGetString = this.iniGetString("mss.clientip");
            final Date testTime = this.TU.getTestTime();
            final URL codeBase = this.TU.getCodeBase();
            sb.append("\r\n\r\nGeneral information\r\n-------------------\r\n");
            sb.append("IP address: " + ((iniGetString == null) ? "<unknown>" : iniGetString) + "\r\n");
            sb.append("Local time: " + ((testTime == null) ? "<unknown>" : testTime.toLocaleString()) + "\r\n");
            sb.append("Test server: " + ((codeBase == null) ? "<unknown>" : codeBase.toString()) + "\r\n");
        }
        return sb.toString();
    }
    
    private String[] getAdvancedDataItem(String s) {
        String s2 = null;
        String s3 = null;
        Object substring = null;
        final AppletPlugin[] plugins = this.TU.getPlugins();
        final int index = s.indexOf(".");
        if (index > 0) {
            substring = s.substring(0, index);
            s = s.substring(index + 1);
        }
        if (s.startsWith("adv_")) {
            s = s.substring(4);
        }
        for (int n = 0; plugins != null && n < plugins.length; ++n) {
            if (substring == null || plugins[n].getName().equals(substring)) {
                final AppletTest test = plugins[n].getTest();
                final String[] array = (String[])((test == null) ? null : test.getAdvancedDataItem(s));
                if (array != null) {
                    s2 = array[0];
                    s3 = array[1];
                    break;
                }
            }
        }
        String[] array2;
        if (s2 == null || s3 == null) {
            array2 = null;
        }
        else {
            final String[] array3 = array2 = new String[2];
            array3[0] = s2;
            array3[1] = s3;
        }
        return array2;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'a') {
            this.GV.setVisible(true);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (TX("viewastext").equals(this.getHitRegion(mouseEvent.getX(), mouseEvent.getY()))) {
            this.copyAdvancedText();
        }
        super.mousePressed(mouseEvent);
    }
    
    private static String TX(final String s) {
        return s;
    }
}
