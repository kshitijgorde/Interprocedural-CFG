// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.util.Properties;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.Applet;
import java.awt.Point;
import java.util.Hashtable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;

public class GraphTab extends AppletTab implements ActionListener
{
    private JComboBox XV;
    private Hashtable WV;
    private Point RV;
    private int SV;
    private int TV;
    private int UV;
    private int VV;
    private Point YV;
    private int ZV;
    private int AV;
    private int BV;
    private int CV;
    
    public GraphTab(final Applet applet) {
        super(applet, null, ((myspeed)applet).getImageFromJar("tabgraph.gif"), "graph");
        this.RV = null;
        this.SV = 1;
        this.TV = 1;
        this.UV = 0;
        this.VV = 0;
        this.WV = new Hashtable();
    }
    
    public void doFirstTimeInit() {
        this.setLayout(null);
        (this.XV = new JComboBox()).addActionListener(this);
        this.XV.removeAllItems();
        this.add(this.XV);
    }
    
    public void reset() {
    }
    
    public boolean canDisplayGraph() {
        return this.XV.getItemCount() > 0;
    }
    
    public void panelPaint(final Graphics graphics) {
        final Dimension dimension = new Dimension(this.getSize().width, this.getSize().height);
        final boolean visible = this.XV.getItemCount() > 1;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final String rc = this.RC(TX("graph_clickdetail"));
        final boolean b = TX("yes").equals(this.iniGetString("mssidlink")) && rc.length() > 0;
        this.drawGraph(graphics, new Point(), (String)this.XV.getSelectedItem(), 10, 10, dimension.width - 20, dimension.height - 20 - ((b || visible) ? (Math.max(this.XV.getSize().height, fontMetrics.getHeight()) + 5) : 0));
        graphics.setColor(Color.blue);
        if (b) {
            final int stringWidth = fontMetrics.stringWidth(rc);
            final int n = dimension.height - fontMetrics.getDescent() - 5 - 3;
            graphics.drawString(rc, dimension.width - 10 - stringWidth, n);
            graphics.drawLine(dimension.width - 10 - stringWidth, n + 2, dimension.width - 10, n + 2);
            this.addHitRegion("auditreport", new Rectangle(dimension.width - 10 - stringWidth, n - fontMetrics.getAscent(), stringWidth, fontMetrics.getHeight()));
        }
        graphics.setColor(Color.black);
        this.XV.setVisible(visible);
        if (visible) {
            final String rc2 = this.RC(TX("graph_plot"));
            graphics.drawString(rc2, 10, dimension.height - fontMetrics.getDescent() - 5 - 3);
            this.XV.setSize(this.XV.getPreferredSize());
            this.XV.setLocation(10 + fontMetrics.stringWidth(rc2) + 10, dimension.height - this.XV.getSize().height - 5);
        }
    }
    
    public void addGraphResults(final String s, final long[] array, final int[] array2, final float[] array3, final boolean[] array4) {
        final int n = (array == null || array.length <= 0) ? 8000 : ((int)(array[array.length - 1] - array[0]));
        final int n2 = (array3 == null) ? 750 : array3.length;
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("totalms", new StringBuffer().append(n).toString());
        ((Hashtable<String, String>)properties).put("totalpackets", new StringBuffer().append(n2).toString());
        ((Hashtable<String, String>)properties).put("delaygraph", (array != null && array2 != null) ? "yes" : "no");
        ((Hashtable<String, String>)properties).put("multfactor", "8");
        this.WV.put(s, new Object[] { array, array2, array3, array4, properties });
        this.XV.addItem(s);
        this.repaint();
    }
    
    public void addGraphResults(final String s, final long[] array, final int[] array2, final boolean b, final int n) {
        final Properties properties = new Properties();
        ((Hashtable<String, String>)properties).put("bars", b ? "yes" : "no");
        ((Hashtable<String, String>)properties).put("totalms", new StringBuffer().append(n).toString());
        ((Hashtable<String, String>)properties).put("absolutetimes", "yes");
        ((Hashtable<String, String>)properties).put("yaxislabel", "Bytes ReTx");
        this.WV.put(s, new Object[] { array, array2, null, null, properties });
        this.XV.addItem(s);
        this.repaint();
    }
    
    private void drawGraph(final Graphics graphics, final Point point, final String s, final int n, final int n2, int n3, final int n4) {
        final Point point3;
        final Point point2 = point3 = new Point(point);
        point3.x += 3;
        n3 -= 6;
        graphics.setColor(Color.white);
        graphics.fillRect(n, n2, n3, n4);
        graphics.setColor(Color.gray);
        graphics.drawRect(n, n2, n3, n4);
        graphics.setColor(Color.lightGray);
        graphics.drawLine(n + 1, n2 + n4 + 1, n + n3 + 1, n2 + n4 + 1);
        graphics.drawLine(n + n3 + 1, n2 + 1, n + n3 + 1, n2 + n4 + 1);
        final Object[] array = this.WV.get(s);
        final long[] array2 = (long[])((array == null) ? null : array[0]);
        final int[] array3 = (int[])((array == null) ? null : array[1]);
        float[] array4 = (float[])((array == null) ? null : array[2]);
        boolean[] array5 = (boolean[])((array == null) ? null : array[3]);
        final Properties properties = (array == null || array.length <= 4) ? null : ((Properties)array[4]);
        final Properties properties2 = (properties == null) ? new Properties() : properties;
        final boolean b = array4 != null && array5 != null;
        final boolean equals = "yes".equals(((Hashtable<K, Object>)properties2).get("absolutetimes"));
        final long[] array6 = (long[])((array2 == null) ? null : new long[array2.length]);
        final int[] array7 = (int[])((array2 == null) ? null : new int[array2.length]);
        int n5 = 0;
        int n7;
        for (int n6 = 0; array6 != null && array7 != null && n6 < array6.length; n6 = n7) {
            array6[n5] = array2[n6] - (equals ? 0L : array2[0]);
            for (n7 = n6; n7 < array6.length && n7 < array2.length && array2[n6] == array2[n7]; ++n7) {
                final int[] array8 = array7;
                final int n8 = n5;
                array8[n8] += array3[n7];
            }
            ++n5;
        }
        final long[] array9 = (long[])((array6 == null) ? null : U.resize(array6, n5));
        final int[] array10 = (int[])((array7 == null) ? null : U.resize(array7, n5));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int stringWidth = fontMetrics.stringWidth("XXXXXX.X");
        final Rectangle rectangle = new Rectangle(n + stringWidth, n2 + height * 2, n3 - stringWidth * 2, n4 - height * 5);
        final Point point4 = (this.RV == null) ? null : new Point(this.RV.x, this.RV.y);
        if (point4 != null) {
            point4.translate(-point2.x, -point2.y);
        }
        if (array4 == null || array5 == null || array4.length == 0 || array5.length == 0) {
            array4 = null;
            array5 = null;
        }
        final int equals2 = "yes".equals(((Hashtable<K, String>)properties2).get("delaygraph")) ? 1 : 0;
        final int int1 = Util.parseInt(((Hashtable<K, String>)properties2).get("multfactor"), 1);
        final boolean equals3 = "yes".equals(((Hashtable<K, Object>)properties2).get("bars"));
        final int n9 = 1;
        final Color color = new Color(8355839);
        final Color color2 = new Color(16744319);
        final int int2 = Util.parseInt(((Hashtable<K, String>)properties2).get("totalpackets"), (array4 == null) ? 750 : array4.length);
        final int int3 = Util.parseInt(((Hashtable<K, String>)properties2).get("totalms"), (array9 == null || array9.length < 2) ? 8000 : Math.max(1000, (int)(array9[array9.length - 1] - array9[0])));
        graphics.setColor(Color.white);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(Color.gray);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final int n10 = 20;
        double n11 = b ? (rectangle.width / int2) : (rectangle.width / (int3 / n10));
        int n12 = 1;
        final double n13 = b ? 4 : 4;
        if (b && n11 < n13) {
            n12 = (int)Math.ceil(n13 / n11);
            n11 = n13;
        }
        final int n14 = b ? this.VV : this.UV;
        int n15 = -1;
        int n16 = -1;
        graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.translate(rectangle.x, rectangle.y);
        int n17 = 1;
        int min = 0;
        for (int n18 = 0; (b && array4 != null && n18 < array4.length) || (!b && array9 != null && n18 < array9.length - (equals3 ? 0 : 1)); ++n18) {
            if (equals3) {
                n17 = Math.max(n17, (int)(1.1 * int1 * array10[n18]));
            }
            else {
                n17 = Math.max(n17, (int)(b ? Math.ceil(array4[n18]) : (int1 * array10[n18] / ((n18 > 0 && n18 < array9.length) ? ((array9[n18 + 1] - array9[n18 - 1]) / 2L) : n10))));
            }
            min = Math.min(min, (int)(b ? Math.floor(array4[n18]) : array10[n18]));
        }
        final int n19 = min * rectangle.height / (n17 - min);
        Point point5 = null;
        graphics.setColor(color2);
        for (int n20 = 0; b && array4 != null && n20 < int2; n20 += n12) {
            int n21 = 0;
            for (int i = n20; i < Math.min(n20 + n12, int2); ++i) {
                if (array5[i]) {
                    ++n21;
                }
            }
            final int n22 = n21 * 100 / n12;
            graphics.fillRect(n14 + (int)(n20 * n11 / n12) - (int)(n11 / 2.0), rectangle.height * (100 - n22) / 100, Math.max(1, (int)n11 - 1), rectangle.height * n22 / 100);
        }
        graphics.setColor(color);
        int n27;
        for (int n23 = 0, n24 = 0; (b && array4 != null && n23 < array4.length - 1) || (!b && array9 != null && n23 < array9.length - (equals ? 0 : 1)); n23 = ((n27 == -1) ? n23 : n27), n24 += (b ? n12 : n10)) {
            double n25 = 0.0;
            int n26 = 0;
            n27 = (b ? (n24 + n12) : this.timeTo(array9, n23, n24 + n10));
            if (array9 != null && n27 == array9.length - 1 && n27 == n23) {
                ++n27;
            }
            for (int n28 = n23; n27 >= 0 && n28 < Math.min(b ? int2 : array9.length, n27); ++n28) {
                if (equals3) {
                    n25 += array10[n28];
                }
                else {
                    n25 += (b ? (array5[n28] ? 0.0f : array4[n28]) : (int1 * array10[n28] / ((n28 > 0 && n28 < array9.length) ? ((array9[n28 + 1] - array9[n28 - 1]) / 2L) : n10)));
                }
                n26 += ((b ? (array5[n28] ? false : true) : true) ? 1 : 0);
            }
            if (n26 > 0) {
                if (!equals3) {
                    n25 /= Math.max(n26, 1);
                }
                final int n29 = n14 + (int)((b ? n23 : ((int)(array9[n23] - (equals ? 0L : array9[0])))) * n11 / (b ? n12 : n10));
                final int n30 = n19 + rectangle.height - (int)(rectangle.height * n25 / (n17 - min));
                if (equals3) {
                    graphics.fillRect(n29 - (int)(n11 / 2.0), n30, (int)n11, rectangle.height - n30 - n19);
                }
                else if (point5 != null) {
                    graphics.drawLine(point5.x, point5.y, n29, n30);
                }
                if (point4 != null && rectangle.contains(point4) && (n16 < 0 || n16 > Math.abs(point4.x - rectangle.x - n29))) {
                    n16 = Math.abs(point4.x - rectangle.x - n29);
                    n15 = n23;
                }
                point5 = new Point(n29, n30);
            }
        }
        graphics.setColor(color2);
        int max = 50;
        if (!b && equals2 && array9 != null) {
            for (int j = 0; j < array9.length - 1; ++j) {
                max = Math.max(max, (int)(array9[j + 1] - array9[j]));
            }
            Point point6 = null;
            int timeTo;
            for (int n31 = 0; n31 >= 0 && max > 0 && n31 < array9.length - 1; n31 = timeTo) {
                timeTo = this.timeTo(array9, n31, array9[n31] + n10);
                int max2 = 1;
                for (int n32 = n31; n32 < array9.length - 1 && n32 < timeTo; ++n32) {
                    max2 = Math.max(max2, (int)(array9[n32 + 1] - array9[n32]));
                }
                final int n33 = n14 + (int)((array9[n31] - array9[0]) * n11 / n10);
                final int n34 = rectangle.height - rectangle.height * max2 / max;
                if (point6 != null) {
                    graphics.drawLine(point6.x, point6.y, n33, point6.y);
                    graphics.drawLine(n33, point6.y, n33, n34);
                }
                if (point4 != null && rectangle.contains(point4) && (n16 < 0 || n16 > Math.abs(point4.x - rectangle.x - n33))) {
                    n16 = Math.abs(point4.x - rectangle.x - n33);
                    n15 = n31;
                }
                point6 = new Point(n33, n34);
            }
        }
        graphics.setClip(-rectangle.x + n, -rectangle.y + n2, n3, n4);
        graphics.setColor(Color.gray);
        graphics.drawLine(0, n19 + rectangle.height, rectangle.width, n19 + rectangle.height);
        final int n35 = (int)(-n14 * (b ? n12 : n10) / n11);
        final int n36 = (int)(rectangle.width * (b ? n12 : n10) / n11);
        final int n37 = n35 + n36;
        final int[] array11 = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000 };
        int n38 = n36 / (rectangle.width / fontMetrics.stringWidth("XXXX"));
        for (int k = 0; k < array11.length; ++k) {
            if (array11[k] > n38) {
                n38 = array11[k];
                break;
            }
        }
        for (int l = Math.max(0, n38 * (int)Math.ceil(n35 / n38)); l <= n37; l += n38) {
            final int n39 = n14 + (int)(l * n11 / (b ? n12 : n10));
            final int stringWidth2 = fontMetrics.stringWidth(new StringBuffer().append(l).toString());
            graphics.drawLine(n39, rectangle.height, n39, rectangle.height + 2);
            graphics.drawString(new StringBuffer().append(l).toString(), n39 - stringWidth2 / 2, rectangle.height + fontMetrics.getAscent() + 2);
        }
        final String rc = this.RC(b ? TX("graph_voipx") : TX("graph_speedx"));
        graphics.drawString(new StringBuffer().append(rc).toString(), rectangle.width / 2 - fontMetrics.stringWidth(rc) / 2, rectangle.height + fontMetrics.getAscent() + 2 + fontMetrics.getHeight());
        final Rectangle rectangle2 = new Rectangle(n + point2.x, rectangle.y + rectangle.height + point2.y, n3, n4 - rectangle.y - rectangle.height + n2);
        if (b) {
            this.addHitRegion("voipaxis", rectangle2);
        }
        else {
            this.addHitRegion("speedaxis", rectangle2);
        }
        graphics.setColor(color);
        graphics.drawString(Util.parseString(((Hashtable<K, Object>)properties2).get("yaxislabel"), this.RC(b ? TX("graph_voipy1") : TX("graph_speedy1"))), -rectangle.x + 12, -fontMetrics.getHeight() + fontMetrics.getAscent() / 2);
        final double[] array12 = { 0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0, 2000.0, 5000.0, 10000.0, 20000.0, 50000.0, 100000.0, 200000.0, 500000.0, 1000000.0, 2000000.0, 5000000.0, 1.0E7 };
        double n40 = (n17 - min) / (rectangle.height / (height + 10));
        for (int n41 = 0; n41 < array12.length; ++n41) {
            if (array12[n41] > n40) {
                n40 = array12[n41];
                break;
            }
        }
        for (double n42 = min + Math.abs(min % n40); n42 <= n17; n42 += n40) {
            final String s2 = (n40 >= 1.0) ? new StringBuffer().append((int)n42).toString() : new StringBuffer().append((int)(100.0 * n42) / 100.0).toString();
            final int n43 = n19 + rectangle.height - (int)(rectangle.height * n42 / (n17 - min));
            graphics.drawString(s2, -3 - fontMetrics.stringWidth(s2), n43 + fontMetrics.getAscent() / 2);
            graphics.drawLine(-2, n43, 0, n43);
        }
        if (b || (!b && equals2 && max > 0)) {
            graphics.setColor(color2);
            final String rc2 = this.RC(b ? TX("graph_voipy2") : TX("graph_speedy2"));
            final int stringWidth3 = fontMetrics.stringWidth(rc2);
            graphics.drawString(rc2, Math.min(rectangle.x + n3 - stringWidth3, rectangle.width - stringWidth3 / 2), -fontMetrics.getHeight() + fontMetrics.getAscent() / 2);
            final int n44 = b ? 100 : max;
            final int[] array13 = { 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000 };
            int n45 = n44 / (rectangle.height / (height + 10));
            for (int n46 = 0; n46 < array13.length; ++n46) {
                if (array13[n46] > n45) {
                    n45 = array13[n46];
                    break;
                }
            }
            for (int n47 = 0; n47 <= n44; n47 += n45) {
                final int n48 = rectangle.height - rectangle.height * n47 / n44;
                graphics.drawString(new StringBuffer().append(n47).toString(), rectangle.width + 3, n48 + fontMetrics.getAscent() / 2);
                graphics.drawLine(rectangle.width, n48, rectangle.width + 2, n48);
            }
        }
        if (n15 >= 0) {
            final int n49 = n14 + (int)((b ? n15 : (array9[n15] - (equals ? 0L : array9[0]))) * n11 / (b ? n12 : n10));
            graphics.setColor(Color.red);
            graphics.drawLine(n49 - 1, 0, n49 - 1, n4);
            graphics.drawLine(n49, 0, n49, n4);
            graphics.drawLine(n49 + 1, 0, n49 + 1, n4);
            String[] array14;
            if (b) {
                int n50 = 0;
                float n51 = 0.0f;
                int n52 = 0;
                for (int n53 = n15; n53 < int2 && n53 < n15 + (b ? n12 : n10); ++n53) {
                    n50 += (array5[n53] ? 1 : 0);
                    n51 += (array5[n53] ? 0.0f : array4[n53]);
                    ++n52;
                }
                array14 = new String[] { String.valueOf(this.RC(TX("jitter"))) + TX(": ") + ((n52 - n50 > 0) ? new StringBuffer().append((int)(10.0f * n51 / (n52 - n50)) / 10.0).toString() : "") + TX(" ") + this.RC(TX("ms")), String.valueOf(this.RC(TX("packetloss"))) + TX(": ") + n50 * 100 / n52 + " %", String.valueOf(this.RC(TX("graph_samples"))) + TX(": ") + n52 };
            }
            else {
                array14 = new String[1 + n9 + equals2];
                array14[0] = String.valueOf(this.RC(TX("graph_time"))) + TX(": ") + (array9[n15] - (equals ? 0L : array9[0])) + TX(" ") + this.RC(TX("ms"));
                int n54 = 1;
                if (n9 != 0) {
                    array14[n54++] = String.valueOf(Util.parseString(((Hashtable<K, Object>)properties2).get("yaxislabel"), this.RC(TX("graph_downloaded")))) + TX(": ") + array10[n15] + TX(" ") + this.RC(TX("bytes"));
                }
                if (equals2 != 0) {
                    array14[n54++] = String.valueOf(this.RC(TX("graph_pause"))) + TX(": ") + ((n15 + 1 < array9.length) ? new StringBuffer().append(array9[n15 + 1] - array9[n15]).toString() : "--") + TX(" ") + this.RC(TX("ms"));
                }
            }
            int n55 = height * array14.length;
            int max3 = 0;
            for (int n56 = 0; n56 < array14.length; ++n56) {
                max3 = Math.max(max3, fontMetrics.stringWidth(array14[n56]));
            }
            max3 += 6;
            n55 += 6;
            final int max4 = Math.max(0, (n3 - max3) / 2);
            final int max5 = Math.max(0, (n4 - n55) / 2);
            graphics.setColor(Color.white);
            graphics.fillRect(max4, max5, max3, n55);
            graphics.setColor(Color.black);
            graphics.drawRect(max4, max5, max3, n55);
            for (int n57 = 0; n57 < array14.length; ++n57) {
                graphics.drawString(array14[n57], max4 + 3, max5 + height * n57 + fontMetrics.getAscent() + 3);
            }
        }
        graphics.translate(-rectangle.x, -rectangle.y);
        graphics.setClip(0, 0, this.getSize().width, this.getSize().height);
        rectangle.translate(point2.x, point2.y);
        if (b) {
            this.addHitRegion("voipgraph", rectangle);
        }
        else {
            this.addHitRegion("speedgraph", rectangle);
        }
    }
    
    private int timeTo(final long[] array, final int n, final long n2) {
        for (int n3 = n; array != null && n3 < array.length; ++n3) {
            if (array[n3] > n2) {
                return (n3 == n) ? -1 : n3;
            }
        }
        return array.length - 1;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.XV && this.XV.getItemCount() > 0) {
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.YV = new Point(mouseEvent.getX(), mouseEvent.getY());
        this.ZV = this.SV;
        this.AV = this.UV;
        this.BV = this.TV;
        this.CV = this.VV;
        this.RV = null;
        super.mousePressed(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        final String hitRegion = this.getHitRegion(point.x, point.y);
        if ("speedgraph".equals(hitRegion) || "voipgraph".equals(hitRegion)) {
            this.RV = new Point(point.x, point.y);
            this.repaint();
        }
        else if (this.RV != null) {
            this.RV = null;
            this.repaint();
        }
        this.setCursor(new Cursor(("speedaxis".equals(hitRegion) || "voipaxis".equals(hitRegion)) ? 11 : ((hitRegion != null) ? 12 : 0)));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.YV != null) {
            final String hitRegion = this.getHitRegion(point.x, point.y);
            final String hitRegion2 = this.getHitRegion(this.YV.x, this.YV.y);
            if ("speedaxis".equals(hitRegion)) {
                final int max = Math.max(1, this.ZV + (point.x - this.YV.x) / 40);
                if (this.SV != max) {
                    this.SV = max;
                    this.repaint();
                }
            }
            else if ("voipaxis".equals(hitRegion)) {
                final int max2 = Math.max(1, this.BV + (point.x - this.YV.x) / 40);
                if (this.TV != max2) {
                    this.TV = max2;
                    this.repaint();
                }
            }
            else if ("speedgraph".equals(hitRegion2)) {
                this.UV = this.AV - this.YV.x + point.x;
                this.repaint();
            }
            else if ("voipgraph".equals(hitRegion2)) {
                this.VV = this.CV - this.YV.x + point.x;
                this.repaint();
            }
        }
        super.mouseDragged(mouseEvent);
    }
    
    private static String TX(final String s) {
        return s;
    }
}
