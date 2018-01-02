// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing;

import javax.swing.Icon;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Date;
import javax.swing.JLabel;
import jmaster.util.property.B;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import jmaster.util.property.C;
import jmaster.util.property.D;
import java.awt.Insets;

public class GUIHelper
{
    public static final String GUI_PROPERTIES_LOCATION = "view/gui.properties";
    public static final String BYTE_SYMBOL = "B";
    public static final String KILOBYTE_SYMBOL = "KB";
    public static final String MEGABYTE_SYMBOL = "MB";
    public static final String GIGABYTE_SYMBOL = "GB";
    public static final String TERABYTE_SYMBOL = "TB";
    public static final long KB = 1024L;
    public static final long KB10 = 10240L;
    public static final long KB100 = 102400L;
    public static final long KB1000 = 1024000L;
    public static final long MB = 1048576L;
    public static final long MB10 = 10485760L;
    public static final long MB100 = 104857600L;
    public static final long MB1000 = 1048576000L;
    public static final long GB = 1073741824L;
    public static final long GB10 = 10737418240L;
    public static final long GB100 = 107374182400L;
    private static final String J = "dd.MM.yy HH:mm:ss";
    public static final String PERCENT_FORMAT_PATTERN = "0.0";
    public static final long MS_IN_HOUR = 3600000L;
    public static final long MS_IN_MINUTE = 60000L;
    public static final long MS_IN_SECOND = 1000L;
    public static final Insets INSETS0;
    public static final Insets INSETS1;
    public static final Insets INSETS2;
    public static final Insets INSETS4;
    public static final Insets INSETS8;
    protected static GUIHelper H;
    protected D I;
    protected C D;
    protected DecimalFormat B;
    protected DecimalFormat A;
    protected SimpleDateFormat G;
    protected DecimalFormat C;
    private Cursor E;
    private Cursor F;
    
    protected GUIHelper() {
        this.B = new DecimalFormat("0.0");
        this.A = new DecimalFormat("0.00");
        this.G = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        this.C = new DecimalFormat("0.0");
        this.E = new Cursor(12);
        this.F = new Cursor(0);
        this.I = jmaster.util.property.B.C().G("view/gui.properties");
        this.D = jmaster.util.property.C.A();
    }
    
    public static synchronized GUIHelper getInstance() {
        if (GUIHelper.H == null) {
            GUIHelper.H = new GUIHelper();
        }
        return GUIHelper.H;
    }
    
    public D getProperty() {
        return this.I;
    }
    
    public Cursor getDefaultCursor() {
        return this.F;
    }
    
    public Cursor getHandCursor() {
        return this.E;
    }
    
    public void injectProperties(final Object o, final String s) {
        this.D.A(o, this.I, s);
    }
    
    public void injectProperties(final Object o, final String s, final String s2) {
        this.D.A(o, this.I, this.I.F(s, s2));
    }
    
    public JLabel newSeparatorLabel() {
        final JLabel label = new JLabel();
        this.injectProperties(label, null, "separatorLabel");
        return label;
    }
    
    public JLabel newSpacerLabel() {
        final JLabel label = new JLabel();
        this.injectProperties(label, null, "spacerLabel");
        return label;
    }
    
    public synchronized String getLengthFormatted(final long n) {
        String s;
        if (n < 1000L) {
            s = "" + n + " " + "B";
        }
        else if (n < 10240L) {
            s = "" + this.A.format(n / 1024.0) + " " + "KB";
        }
        else if (n < 102400L) {
            s = "" + this.B.format(n / 1024.0) + " " + "KB";
        }
        else if (n < 1024000L) {
            s = "" + n / 1024L + " " + "KB";
        }
        else if (n < 10485760L) {
            s = "" + this.A.format(n / 1048576.0) + " " + "MB";
        }
        else if (n < 104857600L) {
            s = "" + this.B.format(n / 1048576.0) + " " + "MB";
        }
        else if (n < 1048576000L) {
            s = "" + n / 1048576L + " " + "MB";
        }
        else if (n < 10737418240L) {
            s = "" + this.A.format(n / 1.073741824E9) + " " + "GB";
        }
        else if (n < 107374182400L) {
            s = "" + this.B.format(n / 1.073741824E9) + " " + "GB";
        }
        else {
            s = "" + n / 1073741824L + " " + "GB";
        }
        return s;
    }
    
    public String getDateTimeFormatted(final Date date) {
        if (date == null) {
            return null;
        }
        synchronized (this.G) {
            return this.G.format(date);
        }
    }
    
    public String getTimeFormatted(final long n) {
        return this.getTimeFormatted(n, false);
    }
    
    public String getTimeFormatted(long n, final boolean b) {
        if (n == -1L) {
            return "N/A";
        }
        final StringBuffer sb = new StringBuffer();
        if (n < 0L) {
            n = -n;
            sb.append("-");
        }
        final long n2 = n / 3600000L;
        if (n2 < 10L) {
            sb.append("0");
        }
        sb.append("" + n2 + ":");
        final long n3 = n / 60000L % 60L;
        if (n3 < 10L) {
            sb.append("0");
        }
        sb.append("" + n3 + ":");
        final long n4 = n / 1000L % 60L;
        if (n4 < 10L) {
            sb.append("0");
        }
        sb.append("" + n4);
        if (b) {
            final long n5 = n % 1000L;
            sb.append('.');
            if (n5 < 100L) {
                sb.append('0');
            }
            if (n5 < 10L) {
                sb.append('0');
            }
            sb.append("" + n5);
        }
        return sb.toString();
    }
    
    public String getTimeFormattedInSeconds(long n, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        if (n < 0L) {
            n = -n;
            sb.append("-");
        }
        sb.append("" + n / 1000L);
        if (b) {
            final long n2 = n % 1000L;
            sb.append('.');
            if (n2 < 100L) {
                sb.append('0');
            }
            if (n2 < 10L) {
                sb.append('0');
            }
            sb.append("" + n2);
        }
        return sb.toString();
    }
    
    public String getPercentFormatted(final double n) {
        synchronized (this.C) {
            return this.C.format(n) + "%";
        }
    }
    
    public static GridBagConstraints initGBC(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2, final int fill, final int anchor) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
        return gridBagConstraints;
    }
    
    public static GridBagConstraints initGBC(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2, final int fill, final int anchor, final Insets insets) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
        gridBagConstraints.fill = fill;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.insets = insets;
        return gridBagConstraints;
    }
    
    public GridBagLayout newGBL() {
        return new GridBagLayout();
    }
    
    public GridBagConstraints newGBC() {
        return new GridBagConstraints();
    }
    
    public JPanel newGBLPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        return panel;
    }
    
    public Icon loadIcon(final String s) {
        return this.I.F(s);
    }
    
    static {
        INSETS0 = new Insets(0, 0, 0, 0);
        INSETS1 = new Insets(1, 1, 1, 1);
        INSETS2 = new Insets(2, 2, 2, 2);
        INSETS4 = new Insets(4, 4, 4, 4);
        INSETS8 = new Insets(8, 8, 8, 8);
    }
}
