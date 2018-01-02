// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Image;

public class SummaryTab extends AppletTab
{
    private myspeed TU;
    private Image WU;
    private Image XU;
    private Image YU;
    private Image ZU;
    private Image AU;
    private boolean VU;
    
    public SummaryTab(final Applet applet) {
        super(applet, null, ((myspeed)applet).getImageFromJar("tabsummary.gif"), "summ");
        this.VU = true;
        this.TU = (myspeed)applet;
    }
    
    public void doFirstTimeInit() {
        this.WU = this.TU.getImageFromJar(TX("sumgood.gif"));
        this.XU = this.TU.getImageFromJar(TX("sumwarning.gif"));
        this.YU = this.TU.getImageFromJar(TX("sumbad.gif"));
        this.ZU = this.TU.getImageFromJar(TX("sumnone.gif"));
    }
    
    public void reset() {
    }
    
    public void panelPaint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setColor(new Color(238, 242, 248));
        graphics.fillRect(0, 0, width - 1, height - 1);
        if (this.VU && this.AU == null) {
            this.VU = false;
            this.AU = this.getBackgroundOverlay();
        }
        if (this.AU != null) {
            graphics.drawImage(this.AU, 0, 0, null);
        }
        graphics.setFont(new Font(TX("Helvetica"), 1, 13));
        graphics.setColor(Color.black);
        final int n = 10;
        int n2 = n + this.drawWrappedLines(graphics, this.TU.RC(TX("connectionsummary")), 65, n, width - 70).height;
        n2 += 10;
        graphics.setFont(new Font("Helvetica", 0, 12));
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getConclusionsText(), TX(", "));
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final Object[] summaryDataItem = this.getSummaryDataItem(nextToken);
            if (summaryDataItem != null) {
                n2 += 20;
                final int intValue = (int)summaryDataItem[0];
                final String s = (String)summaryDataItem[1];
                if (intValue >= -1) {
                    this.drawColourBox(graphics, 10, n2 + 5, 36, 36, intValue);
                }
                n2 += Math.max(20, this.drawWrappedLines(graphics, s, 65, n2, width - ((intValue >= -1) ? 70 : 20)).height);
            }
            else if (this.TU.isProfessional() && TX("auditreport").equals(nextToken) && "yes".equals(this.iniGetString("mssidlink"))) {
                n2 += Math.max(20, this.drawWrappedLines(graphics, this.TU.RC(TX("auditreport")), 10, n2, width - 20).height);
            }
            else {
                if (!this.TU.isProfessional() || !TX("analysis").equals(nextToken)) {
                    continue;
                }
                final AppletPlugin[] plugins = this.TU.getPlugins();
                String s2 = "";
                for (int i = 0; i < plugins.length; ++i) {
                    if (s2.length() > 0) {
                        s2 = String.valueOf(s2) + " | ";
                    }
                    final String name = plugins[i].getName();
                    s2 = String.valueOf(s2) + TX("<a href=analysisportal_") + name + TX(">") + this.TU.RC(TX("analysislinkjoin"), name) + TX("</a>");
                }
                n2 += Math.max(20, this.drawWrappedLines(graphics, this.TU.RC(TX("analysislink"), s2), 10, n2, width - 20).height);
            }
        }
    }
    
    private String getConclusionsText() {
        String s = "";
        if ("yes".equals(this.RC(TX("conclusionsauditreport")))) {
            s = String.valueOf(s) + TX(",auditreport");
        }
        if ("yes".equals(this.RC(TX("conclusionsanalysisportal")))) {
            s = String.valueOf(s) + TX(",analysis");
        }
        final AppletPlugin[] plugins = this.TU.getPlugins();
        for (int n = 0; plugins != null && n < plugins.length; ++n) {
            final String name = plugins[n].getName();
            final String rc = this.RC(String.valueOf(name) + TX(".conclusions"));
            final StringTokenizer stringTokenizer = new StringTokenizer((rc == null) ? "" : rc, "\r\n");
            while (stringTokenizer.hasMoreTokens()) {
                s = String.valueOf(s) + TX(",") + name + "." + stringTokenizer.nextToken();
            }
        }
        return s;
    }
    
    public boolean hasData() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getConclusionsText(), ",");
        while (stringTokenizer.hasMoreTokens()) {
            if (this.getSummaryDataItem(stringTokenizer.nextToken()) != null) {
                return true;
            }
        }
        return false;
    }
    
    private void drawColourBox(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final Color[] array = { new Color(65408), new Color(16777152), new Color(16744576) };
        final Image[] array2 = { this.WU, this.XU, this.YU };
        final Color color = graphics.getColor();
        final Color color2 = (n5 == -1) ? Color.white : array[n5];
        final Image image = (n5 == -1) ? this.ZU : array2[n5];
        final int height = image.getHeight(null);
        if (image == null || height < 0) {
            Util.gradientFill(graphics, n, n2, n3, n4, color2, color2.darker());
            graphics.setColor(Color.darkGray);
            graphics.drawRect(n, n2, n3, n4);
        }
        else {
            graphics.drawImage(image, n, n2, null);
        }
        graphics.setColor(color);
    }
    
    private Object[] getSummaryDataItem(String s) {
        Object substring = null;
        final AppletPlugin[] plugins = this.TU.getPlugins();
        final int index = s.indexOf(".");
        if (index > 0) {
            substring = s.substring(0, index);
            s = s.substring(index + 1);
        }
        if (s.startsWith("sum_")) {
            s = s.substring(4);
        }
        for (int n = 0; plugins != null && n < plugins.length; ++n) {
            if (substring == null || plugins[n].getName().equals(substring)) {
                final AppletTest test = plugins[n].getTest();
                final Object[] array = (Object[])((test == null) ? null : test.getSummaryItem(s));
                if (array != null) {
                    return array;
                }
            }
        }
        return null;
    }
    
    private static String TX(final String s) {
        return s;
    }
}
