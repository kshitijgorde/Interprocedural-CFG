import java.net.URL;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import GK.FK;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Label;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextField;
import java.applet.Applet;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class KK extends Panel
{
    private static char[] MK;
    private static final String ZR;
    private Applet FS;
    private String GS;
    private EK MS;
    private EK NS;
    private EK OS;
    private TextField CR;
    private EK LS;
    private Button DS;
    private boolean ES;
    private static int[] AR;
    private static int[] BR;
    static Class BP;
    static Class CP;
    
    static {
        KK.MK = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        ZR = VK("z%~T1%'$3&zf1%'");
        KK.AR = new int[] { 0, 2, 3, 1 };
        KK.BR = new int[] { 10, 18, 11, 12, 17, 10, 13, 16, 15, 14 };
    }
    
    public KK(final Applet fs, final String gs) {
        this.CR = new TextField(25);
        this.DS = new Button(VK("M%s"));
        this.ES = true;
        this.FS = fs;
        this.GS = gs;
        this.setBackground(new Color(16777168));
        this.setForeground(Color.black);
        this.setLayout(new GridBagLayout());
        this.setFont(new Font(VK("L/(}/ +13"), 0, 12));
        this.CR.setFont(new Font(VK("Q%~\"+/\""), 0, 12));
        final EK ek = new EK(VK("H%-+&tetC~+1)tB/-+! \"3 +%&tgtA /$tct%.tc"));
        ek.setFont(new Font(VK("L/(}/ +13"), 1, 16));
        this.HS(ek, 0, 0, "w2");
        final String fm = FM(gs, VK("/'3+("));
        if (gs.indexOf("?") > 0 && fm != null && !IM(gs)) {
            final EK ek2 = new EK(VK("9?BHt}/\"+.+13 +%&t/\"\"%\"tggt$3\" +3(t?BHU7"));
            ek2.setForeground(Color.red);
            this.HS(ek2, 0, 1, "w2");
        }
        final String property = System.getProperty(VK("*3}3f}/\"!+%&"));
        if (this.IS(property)) {
            final EK ls = new EK(String.valueOf(VK("J3}3tcf_f{it?$03 /tB/1%''/&0/0tl.%~&0t")) + property + VK("ktggt,  $Zee|||f*3}3f1%'"));
            ls.KS(true);
            ls.setForeground(Color.red);
            this.HS(ls, 0, 2, "w2");
            this.LS = ls;
        }
        this.MS = new EK(VK("O& /\"tz%~\"t\"/-+! /\"/0t/'3+(t300\"/!!t %t311/!!t ,+!"));
        this.NS = new EK(VK("!/\"}+1/ftE ,/\"|+!/ht/& /\"tz%~\"t/'3+(t300\"/!!tggt3&0t3"));
        this.OS = new EK(VK("DKFt&~'2/\"t-\"3& +&-t311/!!t|+((t2/t/'3+(/0t %tz%~Z"));
        this.HS(this.MS, 0, 4, "w2");
        this.HS(this.NS, 0, 6, "w2");
        this.HS(this.OS, 0, 7, "w2b5");
        this.HS(this.CR, 0, 10, "x1f1");
        this.HS(this.DS, 1, 10, "");
        this.CR.setText((fm != null) ? fm : KK.ZR);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.DS || event.target == this.CR) {
            this.PS();
        }
        else if (this.LS != null && event.target == this.LS) {
            this.QS(VK(",  $Zee|||f*3}3f1%'"));
        }
        return true;
    }
    
    private String MM() {
        final String gs = this.GS;
        final int index = gs.indexOf(63);
        return (index > 0) ? gs.substring(0, index) : gs;
    }
    
    private static String TS(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            char char1 = s.charAt(i);
            if (char1 == '+') {
                char1 = ' ';
            }
            else if (char1 == '%') {
                try {
                    char1 = (char)Integer.parseInt(s.substring(i + 1, i + 1 + 2), 16);
                }
                catch (Exception ex) {}
                i += 2;
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    private void PQ() {
        if (this.ES) {
            this.ES = false;
            this.CR.selectAll();
            this.CR.requestFocus();
        }
    }
    
    private void PS() {
        final String trim = this.CR.getText().trim();
        if (!RS(trim)) {
            this.MS.setForeground(Color.red);
            this.NS.setForeground(Color.red);
            this.OS.setForeground(Color.red);
            this.CR.requestFocus();
            this.CR.selectAll();
            return;
        }
        this.QS(String.valueOf(VK(",  $Zee!/1~\"/f}+!~3(|3\"/f1%'e1\"'e!\"/-")) + VK("U}/\"Wcn/'3+(W") + SO(trim) + VK("n W") + System.currentTimeMillis() + VK("n~\"(W") + SO(this.MM()));
    }
    
    private static String SO(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(2 * length);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if ((char1 >= 'a' && char1 <= 'z') || (char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9')) {
                sb.append(char1);
            }
            else if (char1 == ' ') {
                sb.append('+');
            }
            else {
                sb.append('%');
                sb.append(KK.MK[char1 / '\u0010' & '\u000f']);
                sb.append(KK.MK[char1 & '\u000f']);
            }
        }
        return sb.toString();
    }
    
    private static String XS(final String s) {
        try {
            final int index = s.indexOf(63);
            if (index > 0) {
                String s2 = s.substring(0, index + 1);
                final StringTokenizer stringTokenizer = new StringTokenizer(s.substring(index + 1), "&", true);
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index2 = nextToken.indexOf(61);
                    if (index2 > 0) {
                        s2 = String.valueOf(s2) + nextToken.substring(0, index2 + 1) + SO(nextToken.substring(index2 + 1));
                    }
                    else {
                        s2 = String.valueOf(s2) + nextToken;
                    }
                }
                return s2;
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    private void HS(final Object o, final int n, final int n2, final String s) {
        final GridBagConstraints ss = SS(n, n2, s);
        final Component component = (o instanceof Component) ? ((Component)o) : new EK(o.toString());
        if (component instanceof Label) {
            ((Label)component).setBackground(Color.red);
        }
        this.add(component);
        try {
            ((GridBagLayout)this.getLayout()).setConstraints(component, ss);
        }
        catch (Exception ex) {
            System.out.println("gbAdd setConstraints failed: " + ex);
        }
    }
    
    public static String FM(final String s, final String s2) {
        final int index = s.indexOf(63);
        if (index > 0) {
            final String string = "&" + s.substring(index + 1);
            final String string2 = "&" + s2 + "=";
            final int index2 = string.indexOf(string2);
            if (index2 >= 0) {
                final int n = index2 + string2.length();
                final int index3 = string.indexOf("&", n);
                return TS((index3 >= n) ? string.substring(n, index3) : string.substring(n));
            }
        }
        return null;
    }
    
    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public static boolean JM(final String s) {
        try {
            return Math.abs(System.currentTimeMillis() - Long.parseLong(FM(s, "t"))) < 1800000L;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private boolean IS(final String s) {
        final String[] array = { VK("cfcfb"), VK("cfcfa"), VK("cfa"), VK("cf`fd") };
        for (int i = 0; i < array.length; ++i) {
            if (s.startsWith(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean IM(String substring) {
        try {
            final int index = substring.indexOf(35);
            if (index > 0) {
                substring = substring.substring(0, index);
            }
            final int index2 = substring.indexOf(VK("n}W"));
            if (index2 > 0) {
                return (0x1L | VS(US(substring.substring(0, index2)).substring(0, 16))) * VS(substring.substring(index2 + 3)) == 123456789L;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private static boolean RS(final String s) {
        if (s.indexOf(KK.ZR) >= 0) {
            return false;
        }
        if (s.length() > 0 && Character.isDigit(s.charAt(s.length() - 1))) {
            return false;
        }
        final int index = s.indexOf(64);
        final int index2 = s.indexOf(46, index + 1);
        return index > 1 && index2 > index + 1 && WS(s);
    }
    
    private static boolean WS(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if ((char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z') && (char1 < '0' || char1 > '9') && char1 != '_' && char1 != '-' && char1 != '.' && char1 != '@') {
                return false;
            }
        }
        return true;
    }
    
    private static void SM(final Applet applet, final String s) {
        try {
            final Class ap = FK.AP(VK("JA"));
            final String vk = VK("*!");
            final Class[] array = new Class[2];
            final int n = 0;
            Class bp;
            if ((bp = KK.BP) == null) {
                try {
                    bp = (KK.BP = FK.AP("java.applet.Applet"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n] = bp;
            final int n2 = 1;
            Class cp;
            if ((cp = KK.CP) == null) {
                try {
                    cp = (KK.CP = FK.AP("java.lang.String"));
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            array[n2] = cp;
            ap.getMethod(vk, (Class[])array).invoke(null, applet, s);
        }
        catch (InvocationTargetException ex3) {
            ex3.getTargetException().printStackTrace();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static long VS(final String s) {
        long n = 0L;
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char upperCase = Character.toUpperCase(s.charAt(i));
            n = n * 16L + ((upperCase >= '0' && upperCase <= '9') ? (upperCase - '0') : ((upperCase >= 'A' && upperCase <= 'F') ? (upperCase - 'A' + '\n') : '\0'));
        }
        return n;
    }
    
    private static GridBagConstraints SS(final int gridx, final int gridy, final String s) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = 17;
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, VK("|,3.{z$+( \"2"), true);
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    final String nextToken = stringTokenizer.nextToken();
                    final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                    if (nextToken.equals(VK("|"))) {
                        gridBagConstraints.gridwidth = int1;
                    }
                    else if (nextToken.equals(VK(","))) {
                        gridBagConstraints.gridheight = int1;
                    }
                    else if (nextToken.equals(VK("3"))) {
                        gridBagConstraints.anchor = KK.BR[int1];
                    }
                    else if (nextToken.equals(VK("."))) {
                        gridBagConstraints.fill = KK.AR[int1];
                    }
                    else if (nextToken.equals(VK("{"))) {
                        gridBagConstraints.weightx = int1;
                    }
                    else if (nextToken.equals(VK("z"))) {
                        gridBagConstraints.weighty = int1;
                    }
                    else if (nextToken.equals(VK("$"))) {
                        gridBagConstraints.ipadx = int1;
                        gridBagConstraints.ipady = int1;
                    }
                    else if (nextToken.equals(VK("+"))) {
                        gridBagConstraints.insets = new Insets(int1, int1, int1, int1);
                    }
                    else if (nextToken.equals(VK("("))) {
                        gridBagConstraints.insets.left = int1;
                    }
                    else if (nextToken.equals(VK(" "))) {
                        gridBagConstraints.insets.top = int1;
                    }
                    else if (nextToken.equals(VK("\""))) {
                        gridBagConstraints.insets.right = int1;
                    }
                    else if (nextToken.equals(VK("2"))) {
                        gridBagConstraints.insets.bottom = int1;
                    }
                    else {
                        System.out.println("?GBC:" + nextToken);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        }
        return gridBagConstraints;
    }
    
    public static String IP(final String s) {
        if (s != null) {
            if (IM(s)) {
                return s;
            }
            final String xs = XS(s);
            if (IM(xs)) {
                return xs;
            }
            final String ts = TS(s);
            if (IM(ts)) {
                return ts;
            }
        }
        return null;
    }
    
    private static void YS(final StringBuffer sb, int n) {
        for (int i = 0; i < 8; ++i) {
            sb.append(KK.MK[n >>> 28]);
            n <<= 4;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.PQ();
        final int n = this.size().width - 1;
        final int n2 = this.size().height - 1;
        graphics.setColor(Color.lightGray);
        graphics.drawLine(n, 0, n, n2);
        graphics.drawLine(n, n2, 0, n2);
        graphics.setColor(Color.gray);
        graphics.drawLine(0, 0, n, 0);
        graphics.drawLine(0, 0, 0, n2);
    }
    
    private static String US(final byte[] array) {
        final int length = array.length;
        final byte[] array2 = new byte[(length + 1 + 8 + 63) / 64 * 64 - length];
        array2[0] = -128;
        final long n = length * 8L;
        for (int i = 0; i < 8; ++i) {
            array2[array2.length - 1 - i] = (byte)(0xFFL & n >> i * 8);
        }
        int n2 = 1732584193;
        int n3 = -271733879;
        int n4 = -1732584194;
        int n5 = 271733878;
        int n6 = -1009589776;
        final int[] array3 = new int[80];
        final int n7 = length + array2.length;
        int j = 0;
        while (j < n7) {
            for (int k = 0; k < 16; ++k) {
                int n8 = 0;
                for (int l = 0; l < 4; ++l) {
                    n8 = (n8 << 8 | (0xFF & ((j < length) ? array[j] : array2[j - length])));
                    ++j;
                }
                array3[k] = n8;
            }
            for (int n9 = 16; n9 < 80; ++n9) {
                final int n10 = array3[n9 - 3] ^ array3[n9 - 8] ^ array3[n9 - 14] ^ array3[n9 - 16];
                array3[n9] = (n10 << 1 | n10 >>> -1);
            }
            int n11 = n2;
            int n12 = n3;
            int n13 = n4;
            int n14 = n5;
            int n15 = n6;
            for (int n16 = 0; n16 < 20; ++n16) {
                final int n17 = (n11 << 5 | n11 >>> -5) + ((n12 & n13) | (~n12 & n14)) + n15 + array3[n16] + 1518500249;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n17;
            }
            for (int n18 = 20; n18 < 40; ++n18) {
                final int n19 = (n11 << 5 | n11 >>> -5) + (n12 ^ n13 ^ n14) + n15 + array3[n18] + 1859775393;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n19;
            }
            for (int n20 = 40; n20 < 60; ++n20) {
                final int n21 = (n11 << 5 | n11 >>> -5) + ((n12 & n13) | (n12 & n14) | (n13 & n14)) + n15 + array3[n20] - 1894007588;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n21;
            }
            for (int n22 = 60; n22 < 80; ++n22) {
                final int n23 = (n11 << 5 | n11 >>> -5) + (n12 ^ n13 ^ n14) + n15 + array3[n22] - 899497514;
                n15 = n14;
                n14 = n13;
                n13 = (n12 << 30 | n12 >>> -30);
                n12 = n11;
                n11 = n23;
            }
            n2 += n11;
            n3 += n12;
            n4 += n13;
            n5 += n14;
            n6 += n15;
        }
        final StringBuffer sb = new StringBuffer(40);
        YS(sb, n2);
        YS(sb, n3);
        YS(sb, n4);
        YS(sb, n5);
        YS(sb, n6);
        return sb.toString();
    }
    
    private static String US(final String s) {
        return US(BN(s));
    }
    
    private void QS(final String s) {
        try {
            this.FS.getAppletContext().showDocument(new URL(s));
        }
        catch (Exception ex) {
            System.err.println("" + ex);
        }
        try {
            for (int n = 0; n < 10 && this.FS.isActive(); ++n) {
                Thread.sleep(50L);
            }
            if (this.FS.isActive()) {
                System.err.println(VK("=SBFKFMZt!,%|P%1~'/& lkt.3+(/0ht \"z+&-tJ3}3A1\"+$ t231)~$"));
                SM(this.FS, String.valueOf(VK("0%1~'/& f(%13 +%&f,\"/.Wm")) + s + "'");
            }
        }
        catch (Exception ex2) {
            System.err.println("" + ex2);
        }
    }
    
    private static byte[] BN(final String s) {
        if (s != null) {
            final byte[] array = new byte[s.length()];
            s.getBytes(0, array.length, array, 0);
            return array;
        }
        return null;
    }
    
    private static String VK(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            if (c >= ' ' && c <= '~') {
                charArray[i] = (char)(32 + (12904061 - c) % 95);
            }
        }
        return new String(charArray);
    }
}
