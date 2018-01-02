import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.Cursor;
import java.util.Locale;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Lware extends Frame
{
    String a;
    String b;
    String c;
    String d;
    String e;
    String f;
    int g;
    String h;
    private AppletContext i;
    private boolean j;
    private String k;
    int l;
    private String m;
    private static final String n = "L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg";
    private static final int o = 30;
    private static final int p = 1073741824;
    private static final int q = 1073741823;
    private static final int r = 15;
    private static final int s = 32768;
    private static final int t = 32767;
    public static int u;
    
    public Lware(final Applet applet, final String s) {
        final int u = Lware.u;
        this.a = b("\u001d\u007f\u001dk/O7ZW2I7O[%");
        this.b = b("k;NW5\u001d%JIo\\<[G5X3P\u0010\"R?\u001dJ.\u001d6RI/Q=\\Z");
        this.c = b("{\u0000x{a|<[Ga[=O\u001e\u0016T<YQ6N~\u001ds ^rRLaq;SK9\u0013");
        this.d = b("\u001dl\u001d\u001e\u000ev~\u001dz\u000ej\u001cqq\u0000ys\u001d\u001e}\u001d");
        this.e = b("X<");
        this.f = b("H!");
        this.g = 18;
        this.h = b("U&IN{\u0012}JI6\u00133SX8I7\\So^=P");
        this.j = false;
        this.l = 4;
        this.i = applet.getAppletContext();
        String s2;
        try {
            s2 = applet.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s2 = b("[;Q[");
        }
        try {
            this.k = applet.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            this.k = "";
        }
        this.k.toLowerCase();
        s2.toLowerCase();
        Label_0238: {
            if (s2.equals(b("[;Q[")) || this.k.length() < 1 || this.k.startsWith(b("Q=^_-")) || this.k.equals(b("\f`\n\u0010q\u0013b\u0013\u000f"))) {
                this.j = true;
                if (u == 0) {
                    break Label_0238;
                }
            }
            if (this.k.startsWith(b("J%J\u0010"))) {
                this.k = this.k.substring(4);
            }
        }
        final int length = this.k.length();
        if (length > 0) {
            final char[] array = new char[length];
            this.k.getChars(0, length, array, 0);
            int n = 0;
            while (true) {
                while (true) {
                    Label_0299: {
                        if (u == 0) {
                            break Label_0299;
                        }
                        if (array[n] == '0') {
                            array[n] = '1';
                        }
                        n += 5;
                    }
                    if (n < length) {
                        continue;
                    }
                    break;
                }
                if (u != 0) {
                    continue;
                }
                break;
            }
            this.k = new String(array);
        }
        boolean b = false;
        try {
            b("\f|\r");
            if (!System.getProperty(b("W3K_oK7OM(R<")).startsWith(b("\f|\r"))) {
                b = true;
            }
        }
        catch (Exception ex3) {}
        if (b) {
            try {
                try {
                    this.a();
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            catch (Exception ex4) {}
        }
        if (this.e.equalsIgnoreCase(b("Y7"))) {
            this.a = b("\u001d\u007f\u001dk/O7ZW2I T[3I7\u001dr(G7SD");
            this.b = b("\u007f7NK\"U7S\u001e\u0012T7\u001dI6J|\\P'D&X_,\u00131RSaV=NJ$S>RM$O");
            this.c = b("y=JP-R3Y\u001e7R<\u001d\u007f/[+\u001dX½OrjW/Y=JMm\u001d\u001f\\]aH<Y\u001e\rT<HFo");
            this.d = b("\u001dl\u001d\u001e\u000ev~\u001dz\u000ej\u001cqq\u0000ys\u001d\u001e}\u001d");
        }
        if (this.e.equalsIgnoreCase(b("X!"))) {
            this.a = b("\u001d\u007f\u001dm(Sro[&T!IL O");
            this.b = b("k;NW5XrJI6\u00133SX8I7\\So^=P\u001e1\\ \\\u001e._&XP$OrXRaM RY3\\?\\");
            this.c = b("|<[GaZ \\J4T&RMaM3O_aj;SZ.J!\u0011\u001e\f\\1\u001dGaq;SK9\u0013");
            this.d = b("\u001dl\u001d\u001e\u0003T7S\u0012ar\u0010i{\u000fz\u0013qq`\u001dr\u0001\u001e");
        }
        if (this.e.equalsIgnoreCase(b("[ "))) {
            this.a = b("\u001d\u007f\u001dp.SrXP3X5TM5O»");
            this.b = b("k;NW5X(\u001dI6J|\\P'D&X_,\u00131RSaM=HLaI»Q\u00d7\"U3OY$O");
            this.c = b("z\u0000|j\u0014t\u0006\u001d\u007f/[+\u001dN.H \u001di(S6RI2\u0011rp_\"\u001d=H\u001e\rT<HFo");
            this.d = b("r\u0019\u0011\u001e\u0015\u00f4\u001e\u00f4}\t|\u0000z{\u0013\u001c");
        }
        if (this.e.equalsIgnoreCase(b("T&"))) {
            this.a = b("\u001d\u007f\u001dp.SrO[&T!IL I=");
            this.b = b("k;NW5\\rJI6\u00133SX8I7\\So^=P\u001e1X \u001dM\"\\ T] O7");
            this.c = b("z\u0000|j\bnr|P'DrM[3\u001d\u0005TP%R%N\u0012ap3^\u001e.\u001d\u001eTP4E|");
            this.d = b("\u001dl\u001d\u001e\u000ev~\u001dm\u0002|\u0000t}\u0000\u001cr\u001d\u0002a");
        }
        if (this.e.equalsIgnoreCase(b("W3"))) {
            this.a = b("\u001d\u007f\u001d\u6714\u763a\u930f\u303c\u30fa\u30dc\u4e6c\u305a\u300b");
            this.b = b("\u306e\u303c\u309f\u30e9\u30ad\u30fe\u309a\u3053\u30fe\u30e7\u30ce\u30bf\u30c1\u30f7\u302e\u001d%JIo\\<[G5X3P\u0010\"R?\u001d\u3075\u30c8\uff3c");
            this.c = b("|<[G\u302f\u711c\u65cb\u30fa\u30dc\u7209\u3052\u3053jW/Y=JMnp3^\u0011\rT<HF\u3026\u4f42\u757a\u305a\u3073\u303f\u3064");
            this.d = b("\u001dl\u001d\u001e\u30ee\u30d7\u3091\u3092\u3069\u3027\u303c\u3092\u309b\u30cd\u30ac\u30c1\u309b\u306a\u3058\u300e\u305d\u3007\u3079\uff3fa\u001dn\u001d");
            this.g = 16;
            this.h = String.valueOf(this.h) + b("\u0012>S\u0011+\\\"\u0012");
        }
        if (this.e.equalsIgnoreCase(b("V="))) {
            this.a = b("\u001d\u007f\u001d\ub4cf\ub81c\uc749r\ub425\uc9fea\uc577\uc50a\uc288\ub2f6\ub2a5");
            this.b = b("J%J\u0010 S4DJ$\\?\u0013].Pr\uc091\uc74a\ud2f9\uc5ed\uc14e\u001d\ub2da\uc6f5\ub861\ub48e\u001d\ubc25\uc77d\uc105\uc6c6");
            this.c = b("\uc735\ub396\uc68d\uc697m\u001d\ub9b6\ud095\ud19e\uc29d\uc694~\u001d\ub992\ub244\uc299\uc6fb\u001d\ud53a\ub9ed\uc6d5\uc5e6\u001d\u007f/[+");
            this.d = b("\u001dl\u001d\u001e\ub2a5\uc689\ub80e\ub4e1\u001fa\u001dn\u001d");
            this.h = String.valueOf(this.h) + b("\u0012>S\u0011*R X_/\u0012");
        }
        if (this.e.equalsIgnoreCase(b("S>"))) {
            this.a = b("\u001d\u007f\u001dq/Z7O[&T!IL$X Y");
            this.b = b("\u007f7GQ$VrJI6\u00133SX8I7\\So^=P\u001e.PrI[aY=JP-R3Y[/");
            this.c = b("z\u0000|j\bnr|P'DrKQ.OrjW/Y=JMm\u001d\u001f\\]aX<\u001dr(S'E\u0010");
            this.d = b("\u001dl\u001d\u001e\u000ev~\u001dz\u000ej\u001cqq\u0000ys\u001d\u001e}\u001d");
        }
        if (this.e.equalsIgnoreCase(b("M&"))) {
            this.a = b("\u001d\u007f\u001dp¢RrO[&T!IL Y=");
            this.b = b("k;NW5XrJI6\u00133SX8I7\\So^=P\u001e1\\ \\\u001e%R%SR.\\6");
            this.c = b("|<[Gaz\u0000\u00fcj\bnrM_3\\rjW/Y=JMm\u001d\u001f\\]aR'\u001dr(S'E\u0010");
            this.d = b("\u001dl\u001d\u001e\u000ev~\u001dz\u000ej\u001cqq\u0000ys\u001d\u001e}\u001d");
        }
        if (this.e.equalsIgnoreCase(b("N$"))) {
            this.a = b("\u001d\u007f\u001dq3X5TM5O7O_%");
            this.b = b("\u007f7N\u00c8*\u001d%JIo\\<[G5X3P\u0010\"R?\u001dX·Or\\J5\u001d>\\Z%\\rS[3");
            this.c = b("|<[Ga[¤O\u001e\u0016T<YQ6N~\u001ds ^rXR-X \u001dr(S'E\u001e\u0006o\u0013iw\u0012\u001c");
            this.d = b("\u001dl\u001d\u001e\u000ev~\u001dr\u0000y\u0016|\u001e\u000fx\u0000\u001c\u001ea\u0001r");
        }
        if (this.e.equalsIgnoreCase(b("\\ "))) {
            String property = "i";
            try {
                property = System.getProperty(b("_ RI2X \u0013H$S6RL"));
            }
            catch (Exception ex5) {}
            if (!property.startsWith(b("s7IM\"\\\"X"))) {
                this.a = b("\u001d\r\u001d\u0604\u060b\u060cr\u0678\u060d\u066d\u0679");
                this.b = b("\u0679\u0616\u0617\u0613\u0604\u0677\u0616\u001dI6J|\\P'D&X_,\u00131RSa\u061e\u067c\u0617\u060f");
                this.c = b("\u0615\u0663\u067b\u0619\u0604\u0611r\u061a\u067a\u0662\u067b\u0613\u0677\u001e\u0604\u0611\u0675\u067b\u0619\u060a\u001d\u0616\u061e\u0678\u0679\u0678\u067b\u001d\u0676\u060b\u067b\u067d\u0675\u060c\u064d\u001d\u0617\u061a\u067d\u064d\u001d\u0675\u0675\u001e\u0605\u0677\u0614\u067e\u060d");
                this.d = b("\u001dl\u001d\u001e\u0604\u0675\u0675\u067c\u067ca\u0631r\u061a\u0616\u066e\u061er\u061a\u067a\u066b\u0610\u0617\u0677\u067aa\u001dn\u001d");
            }
        }
        if (this.e.equalsIgnoreCase(b("O'"))) {
            this.a = b("\u001d\u007f\u001d\u0423\u0474\u001d\u0465\u040d\u047e\u0474\u040e\u046a\u047c\u047c\u0401\u0405\u0412\u0403\u040c\u0471\u0400\u046f\u040d\u0471a\u0407\u046c\u0402\u0406\u040e\u001c");
            this.b = b("\u0422\u046c\u047c\u040b\u0403\u0405\u0410\u0408\u001e6J%\u0013_/[+I[ P|^Q,\u001d\u0466\u0406\u0471a\u0402\u046c\u0406\u047d\u0406\u0408\u046f\u0405\u0471a\u040c\u0467\u047c\u0401\u047a\u040d\u0410\u0400\u0475\u0404");
            this.c = b("\u040d\u046d\u0402\u0405\u0474\u047f\u046c\u040f\u001e\u0000S4D\u001e\u0475\u0406\u041d\u001di(S6RI2\u0011rp_\"\u001d\u046a\u0406\u0406aq;SK9\u0013");
            this.d = b("\u001dl\u001d\u001e\u000ev~\u001d\u0429\u0451\u042e\u0472\u041e\u0429\u0459\u041f\u047e\u001c\u001ea\u0001r");
        }
        Label_1220: {
            if (this.e.equalsIgnoreCase(b("G:"))) {
                if (this.f.equalsIgnoreCase(b("~\u001c")) || this.f.equalsIgnoreCase(b("~\u001a"))) {
                    this.a = b("\u001d\u007f\u001d\u6714\u6ca9\u51b1");
                    this.b = b("\u8bca\u5262JI6\u00133SX8I7\\So^=P\u4e35\u8f3c");
                    this.c = b("\u5170\u8d6b|P'Dr[Q3\u001d\u0005TP%R%N\u0012ap3^\u001e\u6257\u001d\u001eTP4E");
                    this.d = b("\u001dl\u001d\u001e\u782f\u5ba7~\u001d\u4e35\u8f3c\u001cr\u001d\u0002a");
                    this.h = String.valueOf(this.h) + b("\u0012>S\u0011\"U;NW,M}");
                    if (u == 0) {
                        break Label_1220;
                    }
                }
                this.a = b("\u001d\u007f\u001d\u6714\u8a7a\u51b7");
                this.b = b("\u8af6\u5262JI6\u00133SX8I7\\So^=P\u4e35\u8f48");
                this.c = b("\u5170\u8ce9|P'Dr[Q3\u001d\u0005TP%R%N\u0012ap3^\u001e\u6257\u001d\u001eTP4E");
                this.d = b("\u001dl\u001d\u001e\u78fb\u5ba7~\u001d\u4e35\u8f48\u001cr\u001d\u0002a");
                this.h = String.valueOf(this.h) + b("\u0012>S\u0011\"U;IL Y}");
            }
        }
        this.setTitle(b("w3K_a|\"MR$Ir_Ga\\<[G5X3P\u0010\"R?"));
        this.setBackground(new Color(235, 235, 245));
        this.setForeground(Color.black);
        this.setFont(new Font(b("n+NJ$P"), 1, 12));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final Label label = new Label(String.valueOf(s) + this.a, 1);
        layout.setConstraints(label, gridBagConstraints);
        this.add(label);
        final Label label2 = new Label(this.b, 1);
        layout.setConstraints(label2, gridBagConstraints);
        this.add(label2);
        final Label label3 = new Label(this.c, 1);
        layout.setConstraints(label3, gridBagConstraints);
        this.add(label3);
        gridBagConstraints.gridwidth = 1;
        final Label label4 = new Label(" ");
        layout.setConstraints(label4, gridBagConstraints);
        this.add(label4);
        final Label label5 = new Label(" ");
        layout.setConstraints(label5, gridBagConstraints);
        this.add(label5);
        final Label label6 = new Label(" ");
        layout.setConstraints(label6, gridBagConstraints);
        this.add(label6);
        final Label label7 = new Label(" ");
        layout.setConstraints(label7, gridBagConstraints);
        this.add(label7);
        final Label label8 = new Label(" ");
        layout.setConstraints(label8, gridBagConstraints);
        this.add(label8);
        gridBagConstraints.gridwidth = 0;
        final Label label9 = new Label(" ");
        layout.setConstraints(label9, gridBagConstraints);
        this.add(label9);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridwidth = 4;
        final Button button = new Button(this.d);
        layout.setConstraints(button, gridBagConstraints);
        button.setBackground(Color.yellow);
        button.setFont(new Font(b("n+NJ$P"), 1, this.g));
        this.add(button);
        Label_1705: {
            if (b) {
                try {
                    try {
                        this.a(button);
                    }
                    catch (NoSuchMethodError noSuchMethodError2) {}
                }
                catch (Exception ex6) {
                    if (u == 0) {
                        break Label_1705;
                    }
                }
            }
            this.setCursor(12);
        }
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridwidth = 0;
        final Label label10 = new Label(" ");
        layout.setConstraints(label10, gridBagConstraints);
        this.add(label10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 6;
        final Label label11 = new Label(" ");
        layout.setConstraints(label11, gridBagConstraints);
        this.add(label11);
        this.pack();
    }
    
    void a() {
        try {
            final Locale default1 = Locale.getDefault();
            this.e = default1.getLanguage();
            this.f = default1.getCountry();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    void a(final Button button) {
        try {
            button.setCursor(new Cursor(12));
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public final boolean action(final Event event, final Object o) {
        if (o.equals(this.d)) {
            this.hide();
            try {
                this.i.showDocument(new URL(this.h), b("b0Q_/V"));
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.l = 5;
            this.hide();
        }
        if (event.id == 501) {
            this.hide();
            try {
                this.i.showDocument(new URL(this.h), b("b0Q_/V"));
            }
            catch (Exception ex) {}
        }
        if (event.id == 1005 && this.l > 1) {
            if (this.l < 5) {
                this.toFront();
            }
            --this.l;
        }
        if (event.id == 1004) {
            this.show();
            this.toFront();
        }
        return super.handleEvent(event);
    }
    
    public final void dck() {
        int n = 2;
        if (!this.j) {
            do {
                n *= 2;
            } while (Lware.u == 0);
        }
    }
    
    public final String dr(final String s, int n, final boolean b) {
        final int i = Lware.u;
        final int[][] array = new int[2][];
        final int n2 = 0;
        final int[] array2 = new int[16];
        array2[0] = 714175159;
        array2[1] = 1;
        array[n2] = array2;
        array[1] = new int[] { 2, 0 };
        final int[][] array3 = array;
        final int[][] array4 = new int[2][];
        final int n3 = 0;
        final int[] array5 = new int[16];
        array5[0] = 116921063;
        array5[1] = 2;
        array4[n3] = array5;
        array4[1] = new int[] { 2, 0 };
        final int[][] array6 = array4;
        this.m = "";
        if (n == 1) {
            if (this.j != b) {
                do {
                    ++n;
                } while (i == 0);
            }
            return "";
        }
        try {
            final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(this.a(s)));
            final byte[] array7 = new byte[4];
            try {
                byte[] array8;
                int[][] b2;
                int[][] b3;
                int n4;
                String string;
                int n5;
                Label_0249_Outer:Label_0285_Outer:
                while (true) {
                    array8 = new byte[dataInputStream.readByte()];
                    dataInputStream.readFully(array8);
                    b2 = this.b();
                    this.a(array8, b2);
                    b3 = this.b();
                    this.b(b3, b2, array3, array6);
                    this.a(b3, array7);
                    n4 = (array7[0] << 24 & 0xFF000000) + (array7[1] << 16 & 0xFF0000) + (array7[2] << 8 & 0xFF00) + (array7[3] & 0xFF);
                    string = "";
                    n5 = 0;
                    while (true) {
                        while (true) {
                            Label_0288: {
                                if (i == 0) {
                                    break Label_0288;
                                }
                                string = String.valueOf(this.a(n4 & 0x3F)) + string;
                                n4 >>= 6;
                                ++n5;
                            }
                            if (n5 < 5) {
                                continue Label_0285_Outer;
                            }
                            break;
                        }
                        this.m = String.valueOf(this.m) + string;
                        if (i == 0) {
                            continue Label_0249_Outer;
                        }
                        continue;
                    }
                }
            }
            catch (EOFException ex) {
                if (this.m.equals(this.k)) {
                    this.j = true;
                }
                return this.m;
            }
        }
        catch (Exception ex2) {
            return "";
        }
    }
    
    private final String a(int n) {
        final int u = Lware.u;
        Label_0090: {
            if (n >= 0 && n < 10) {
                n += 48;
                if (u == 0) {
                    break Label_0090;
                }
            }
            if (n >= 10 && n < 36) {
                n += 87;
                if (u == 0) {
                    break Label_0090;
                }
            }
            if (n == 36) {
                n = 45;
                if (u == 0) {
                    break Label_0090;
                }
            }
            if (n == 37) {
                n = 46;
                if (u == 0) {
                    break Label_0090;
                }
            }
            if (n == 38) {
                n = 38;
                if (u == 0) {
                    break Label_0090;
                }
            }
            System.out.println(b("^:\\LaS=I\u001e2H\"M"));
        }
        if (n != 38) {
            return new Character((char)n).toString();
        }
        return "";
    }
    
    private final byte[] a(final String s) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n = 0;
        final char[] charArray = s.toCharArray();
        try {
            while (true) {
                Label_0178: {
                    if (Lware.u == 0) {
                        break Label_0178;
                    }
                    byteArrayOutputStream.write((b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n++]) << 3) + (b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n]) >>> 2));
                    byteArrayOutputStream.write((b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n++]) << 6) + (b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n++]) << 1) + (b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n]) >> 4));
                    byteArrayOutputStream.write((b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n++]) << 4) + b("L%XL5D'TQ1\\!YX&U8VR;E1K\\/P`\u0004\u0006v\u000bg").indexOf(charArray[n++]));
                }
                if (n < charArray.length) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {}
        return byteArrayOutputStream.toByteArray();
    }
    
    private final int[][] b() {
        final int[][] array = new int[2][];
        array[0] = new int[16];
        (array[1] = new int[2])[0] = 0;
        array[1][1] = 0;
        return array;
    }
    
    private final void a(final int[][] array, final int[][] array2) {
        if (array == array2) {
            return;
        }
        array[0] = new int[array2[0].length];
        array[1][1] = array2[1][1];
        array[1][0] = array2[1][0];
        if (array2[1][0] > 0) {
            System.arraycopy(array2[0], 0, array[0], 0, array2[1][0]);
        }
    }
    
    private final int b(final int[][] array, final int[][] array2) {
        final int u = Lware.u;
        final int n = array[1][0];
        final int n2 = array2[1][0];
        if (n < n2) {
            return -1;
        }
        if (n > n2) {
            return 1;
        }
        final int[] array3 = array[0];
        final int[] array4 = array2[0];
        int n3 = n - 1;
        while (true) {
            while (true) {
                Label_0087: {
                    if (u == 0) {
                        break Label_0087;
                    }
                    final int n4 = array3[n3];
                    final int n5;
                    if (n5 < array4[n3]) {
                        return -1;
                    }
                    if (array3[n3] > array4[n3]) {
                        return 1;
                    }
                    --n3;
                }
                if (n3 >= 0) {
                    continue;
                }
                break;
            }
            final int n5 = 0;
            if (u == 0) {
                return n5;
            }
            continue;
        }
    }
    
    private final int a(final int[][] array) {
        final int u = Lware.u;
        final int n = array[1][0];
        if (n == 0) {
            return 0;
        }
        int n2 = (n - 1) * 30;
        int n3 = array[0][n - 1];
        while (true) {
            Label_0047: {
                if (u == 0) {
                    break Label_0047;
                }
                final int n4;
                n3 = n4;
                ++n2;
            }
            if (n3 != 0) {
                continue;
            }
            final int n4 = n2;
            if (u == 0) {
                return n4;
            }
            continue;
        }
    }
    
    private final void b(final int[][] array) {
        array[0][0] = 0;
        array[1][1] = 0;
        array[1][0] = 0;
    }
    
    private final void a(final int[][] array, int n) {
        final int[] array2 = array[0];
        if (n <= array2.length) {
            return;
        }
        n += 16;
        final int[] array3 = new int[n];
        System.arraycopy(array2, 0, array3, 0, array2.length);
        array[0] = array3;
    }
    
    private final void a(final int[][] array, final int[][] array2, final int n) {
        final int u = Lware.u;
        final short n2 = (short)n;
        if (array2[1][0] == 0) {
            this.b(array);
            return;
        }
        final short n3 = (short)(n2 % 30);
        final short n4 = (short)(n2 / 30);
        final int n5 = array2[1][0];
        this.a(array, array[1][0] = n5 + n4);
        final int[] array3 = array[0];
        System.arraycopy(array2[0], 0, array3, n4, n5);
        if (n4 > 0) {
            int n6 = n4 - 1;
            while (true) {
                Label_0107: {
                    if (u == 0) {
                        break Label_0107;
                    }
                    array3[n6] = 0;
                    --n6;
                }
                if (n6 >= 0) {
                    continue;
                }
                break;
            }
        }
        if (n3 != 0) {
            int n7 = 0;
            int n8 = array[1][0];
            int n9 = n4;
            int n12 = 0;
            while (true) {
                while (true) {
                    Label_0172: {
                        if (u == 0) {
                            break Label_0172;
                        }
                        final int n10 = array3[n9];
                        final int n11 = n12;
                        array3[n9] = ((n11 << n3 | n7) & 0x3FFFFFFF);
                        n7 = n11 >>> 30 - n3;
                        ++n9;
                    }
                    if (n9 < n8) {
                        continue;
                    }
                    break;
                }
                n12 = n7;
                if (u != 0) {
                    continue;
                }
                break;
            }
            if (n12 != 0) {
                ++n8;
                this.a(array, n8);
                array[0][n8 - 1] = n7;
                array[1][0] = n8;
            }
        }
    }
    
    private final int c(final int[][] array, final int[][] array2) {
        if (array[1][0] == 0 && array2[1][0] == 0) {
            return 0;
        }
        if (array[1][1] != 0) {
            if (array2[1][1] != 0) {
                return this.b(array2, array);
            }
            return -1;
        }
        else {
            if (array2[1][1] != 0) {
                return 1;
            }
            return this.b(array, array2);
        }
    }
    
    private final void a(final int[][] array, int[][] array2, int[][] array3) {
        final int u = Lware.u;
        Label_0061: {
            switch (this.b(array2, array3)) {
                case 0: {
                    this.b(array);
                    return;
                }
                case -1: {
                    final int[][] array4 = array2;
                    array2 = array3;
                    array3 = array4;
                    array[1][1] = 1;
                    if (u != 0) {
                        break Label_0061;
                    }
                    break;
                }
                case 1: {
                    array[1][1] = 0;
                    break;
                }
            }
        }
        this.a(array, array2[1][0]);
        final int[] array5 = array2[0];
        final int[] array6 = array3[0];
        final int[] array7 = array[0];
        final int n = array2[1][0];
        final int n2 = array3[1][0];
        int n3 = 0;
        int n4 = 0;
    Label_0278_Outer:
        while (true) {
            Label_0166: {
                if (u == 0) {
                    break Label_0166;
                }
                final int n5 = array5[n4] - array6[n4] - ((n3 != 0) ? 1 : 0);
                array7[n4] = (n5 & 0x3FFFFFFF);
                n3 = ((n5 < 0) ? 1 : 0);
                ++n4;
            }
            if (n4 >= n2) {
                int n7 = 0;
                int n8 = 0;
                Label_0244: {
                    while (true) {
                        Label_0220: {
                            if (u == 0) {
                                break Label_0220;
                            }
                            final int n6 = array5[n4] - 1;
                            Label_0217: {
                                if (n6 >= 0) {
                                    array7[n4] = n6;
                                    n3 = 0;
                                    if (u == 0) {
                                        break Label_0217;
                                    }
                                }
                                array7[n4] = (n6 & 0x3FFFFFFF);
                            }
                            ++n4;
                        }
                        if (n3 != 0) {
                            n7 = n4;
                            n8 = n;
                            if (u != 0) {
                                break Label_0244;
                            }
                            if (n7 < n8) {
                                continue Label_0278_Outer;
                            }
                        }
                        break;
                    }
                    final int n9 = array2[1][0];
                }
                if (n7 > n8) {
                    System.arraycopy(array5, n4, array7, n4, array2[1][0] - n4);
                }
                int n10 = array2[1][0];
                int[] array8 = null;
                int n11 = 0;
                Label_0305: {
                    while (true) {
                        Label_0281: {
                            if (u == 0) {
                                break Label_0281;
                            }
                            --n10;
                        }
                        if (n10 > 0) {
                            array8 = array7;
                            n11 = n10 - 1;
                            if (u != 0) {
                                break Label_0305;
                            }
                            if (array8[n11] == 0) {
                                continue;
                            }
                        }
                        break;
                    }
                    final int[] array9 = array[1];
                }
                array8[n11] = n10;
                return;
            }
            continue;
        }
    }
    
    private final void b(final int[][] array, int[][] array2, int[][] array3) {
        final int u = Lware.u;
        if (array2[1][0] < array3[1][0]) {
            final int[][] array4 = array2;
            array2 = array3;
            array3 = array4;
        }
        final int n = array2[1][0];
        final int n2 = array3[1][0];
        this.a(array, array[1][0] = n);
        array[1][1] = 0;
        final int[] array5 = array2[0];
        final int[] array6 = array3[0];
        final int[] array7 = array[0];
        int n3 = 0;
        int n4 = 0;
        while (true) {
            Label_0140: {
                if (u == 0) {
                    break Label_0140;
                }
                final int n5 = array5[n4] + array6[n4] + ((n3 != 0) ? 1 : 0);
                array7[n4] = (n5 & 0x3FFFFFFF);
                n3 = ((n5 >= 1073741824) ? 1 : 0);
                ++n4;
            }
            if (n4 >= n2) {
                int n7 = 0;
                int n8 = 0;
                Label_0220: {
                    while (true) {
                        Label_0196: {
                            if (u == 0) {
                                break Label_0196;
                            }
                            final int n6 = array5[n4] + 1;
                            Label_0193: {
                                if (n6 < 1073741824) {
                                    array7[n4] = n6;
                                    n3 = 0;
                                    if (u == 0) {
                                        break Label_0193;
                                    }
                                }
                                array7[n4] = (n6 & 0x3FFFFFFF);
                            }
                            ++n4;
                        }
                        if (n3 != 0) {
                            n7 = n4;
                            n8 = n;
                            if (u != 0) {
                                break Label_0220;
                            }
                            if (n7 < n8) {
                                continue;
                            }
                        }
                        break;
                    }
                    final int n9 = array2[1][0];
                }
                if (n7 > n8) {
                    System.arraycopy(array5, n4, array7, n4, n - n4);
                }
                if (n3 != 0) {
                    final int[] array8 = array[1];
                    final int n10 = 0;
                    ++array8[n10];
                    this.a(array, array[1][0]);
                    array[0][n4] = 1;
                }
                return;
            }
            continue;
        }
    }
    
    private final void c(final int[][] array, final int[][] array2, final int[][] array3) {
        if (array2[1][0] == 0) {
            this.a(array, array3);
            if (array3[1][0] > 0) {
                array[1][0] = ((array3[1][1] == 0) ? 1 : 0);
            }
            return;
        }
        if (array3[1][0] == 0) {
            this.a(array, array2);
            return;
        }
        if (array2[1][1] != 0) {
            if (array3[1][1] != 0) {
                this.a(array, array3, array2);
                return;
            }
            this.b(array, array3, array2);
            array[1][1] = 1;
        }
        else {
            if (array3[1][1] != 0) {
                this.b(array, array2, array3);
                return;
            }
            this.a(array, array2, array3);
        }
    }
    
    private final void b(final int[][] array, final int[][] array2, final int n) {
        final int u = Lware.u;
        final short n2 = (short)n;
        final short n3 = (short)(n2 % 30);
        final short n4 = (short)(n2 / 30);
        if (n4 >= array2[1][0]) {
            this.b(array);
            return;
        }
        this.a(array, array[1][0] = array2[1][0] - n4);
        System.arraycopy(array2[0], n4, array[0], 0, array[1][0]);
        if (n3 != 0) {
            int n5 = 0;
            final int[] array3 = array[0];
            final int n6 = array[1][0];
            int n7 = n6 - 1;
            int n8 = 0;
            while (true) {
                while (true) {
                    Label_0146: {
                        if (u == 0) {
                            break Label_0146;
                        }
                        array3[n7] = (array3[n7] >>> n3 | n5);
                        n5 = n8;
                        --n7;
                    }
                    if (n7 > 0) {
                        continue;
                    }
                    break;
                }
                array3[0] = (array3[0] >>> n3 | n5);
                n8 = n6;
                if (u != 0) {
                    continue;
                }
                break;
            }
            if (n8 > 0 && array3[n6 - 1] == 0) {
                final int[] array4 = array[1];
                final int n9 = 0;
                --array4[n9];
            }
        }
    }
    
    private final void d(final int[][] array, final int[][] array2, final int[][] array3) {
        final int u = Lware.u;
        if (array == array2) {
            System.out.println(b("O7N\u001e|\u001d?"));
        }
        this.a(array, array2);
        if (this.b(array2, array3) < 0) {
            return;
        }
        int n = this.a(array2) - this.a(array3);
        final int[][] b = this.b();
        this.a(b, array3, n);
        while (true) {
            while (true) {
                Label_0100: {
                    if (u == 0) {
                        break Label_0100;
                    }
                    if (this.c(array, b) >= 0) {
                        this.c(array, array, b);
                    }
                    this.b(b, b, 1);
                    --n;
                }
                if (n >= 0) {
                    continue;
                }
                break;
            }
            if (u == 0) {
                return;
            }
            continue;
        }
    }
    
    private final void c(final int[][] array) {
        array[0][0] = 1;
        array[1][1] = 0;
        array[1][0] = 1;
    }
    
    private final void a(int[][] b, int[][] b2, final int[][] array, final int[][] array2) {
        final int u = Lware.u;
        if (array2[1][0] == 0) {
            System.out.println(b("Y;K\u001e#Dr\r"));
        }
        if (this.c(array, array2) < 0) {
            if (b2 != null) {
                this.a(b2, array);
            }
            if (b != null) {
                this.b(b);
            }
            return;
        }
        if (b == null) {
            b = this.b();
        }
        if (b2 == null) {
            b2 = this.b();
        }
        final int[][] b3 = this.b();
        this.a(b2, array);
        this.b(b);
        int n = this.a(array) - this.a(array2);
        this.a(b3, array2, n);
        while (true) {
            while (true) {
                Label_0201: {
                    if (u == 0) {
                        break Label_0201;
                    }
                    Label_0189: {
                        if (b[1][0] == 0) {
                            if (this.c(b2, b3) < 0) {
                                break Label_0189;
                            }
                            this.c(b);
                            this.c(b2, b2, b3);
                            if (u == 0) {
                                break Label_0189;
                            }
                        }
                        this.a(b, b, 1);
                        if (this.c(b2, b3) < 0) {
                            break Label_0189;
                        }
                        final int[] array3 = b[0];
                        final int n3;
                        final int n2 = array3[n3 = 0] | 0x1;
                        final int n4;
                        final int n5;
                        array3[n4] = n5;
                        this.c(b2, b2, b3);
                    }
                    this.b(b3, b3, 1);
                    --n;
                }
                if (n >= 0) {
                    continue;
                }
                break;
            }
            final int[] array4 = b[1];
            final int n4 = 1;
            final int n5 = array[1][1];
            if (u == 0) {
                array4[n4] = ((n5 != 0 ^ array2[1][1] != 0) ? 1 : 0);
                return;
            }
            continue;
        }
    }
    
    private final int d(final int[][] array, final int[][] array2) {
        final int[][] b = this.b();
        this.c(b);
        final int a = this.a(array2);
        this.a(b, b, 2 * a);
        this.a(array, null, b, array2);
        return a + 1;
    }
    
    private final void e(final int[][] array, final int[][] array2, final int[][] array3) {
        final int u = Lware.u;
        if (array == array2 || array == array3) {
            System.out.println(b("\\rRLa_r\u0000\u001e3"));
        }
        if (array2[1][0] == 0 || array3[1][0] == 0) {
            this.b(array);
            return;
        }
        array[1][1] = ((array2[1][1] != 0 ^ array3[1][1] != 0) ? 1 : 0);
        this.a(array, array[1][0] = array2[1][0] + array3[1][0]);
        final int[] array4 = array2[0];
        final int[] array5 = array3[0];
        final int[] array6 = array[0];
        final int n = array[1][0];
        int n2 = n - 1;
        int n3;
        while (true) {
            while (true) {
                Label_0156: {
                    if (u == 0) {
                        break Label_0156;
                    }
                    array6[n2] = 0;
                    --n2;
                }
                if (n2 >= 0) {
                    continue;
                }
                break;
            }
            n3 = 0;
            if (u != 0) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0272: {
                if (u == 0) {
                    break Label_0272;
                }
                long n4 = 0L;
                final long n5 = array4[n3];
                int n6 = n3;
                int n7 = 0;
                int[] array7 = null;
                int n9 = 0;
                while (true) {
                    Label_0246: {
                        if (u == 0) {
                            break Label_0246;
                        }
                        final long n8 = array7[n9] + (array5[n7] * n5 + n4);
                        n4 = n8 >>> 30;
                        array6[n6++] = ((int)n8 & 0x3FFFFFFF);
                        ++n7;
                    }
                    if (n7 < array3[1][0]) {
                        continue;
                    }
                    array7 = array6;
                    n9 = n6;
                    if (u != 0) {
                        continue;
                    }
                    break;
                }
                array7[n9] = (int)n4;
                ++n3;
            }
            if (n3 >= array2[1][0]) {
                if (array6[n - 1] == 0) {
                    final int[] array8 = array[1];
                    final int n10 = 0;
                    --array8[n10];
                }
                return;
            }
            continue;
        }
    }
    
    private final void a(final int[][] array, final int[][] array2, final int[][] array3, final int[][] array4, final int[][] array5, final short n) {
        final int u = Lware.u;
        final int[][] b = this.b();
        final int[][] b2 = this.b();
        final int[][] b3 = this.b();
        final int[][] b4 = this.b();
        this.e(b, array2, array3);
        this.b(b4, b, n - 1);
        this.e(b2, b4, array5);
        this.b(b3, b2, n - 1);
        this.e(b2, array4, b3);
        this.c(array, b, b2);
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0126: {
                    if (u == 0) {
                        break Label_0126;
                    }
                    if (n2++ > 2) {
                        System.out.println(b("P=YS4Qr[_(Q"));
                    }
                    this.c(array, array, array4);
                }
                if (this.c(array, array4) >= 0) {
                    continue;
                }
                break;
            }
            if (u == 0) {
                return;
            }
            continue;
        }
    }
    
    private final boolean b(final int[][] array, int n) {
        final int n2 = n % 30;
        n /= 30;
        return n < array[1][0] && (array[0][n] & 1L << n2) != 0x0L;
    }
    
    private final void b(final int[][] array, final int[][] array2, final int[][] array3, final int[][] array4) {
        final int u = Lware.u;
        final int[][] b = this.b();
        final int[][] b2 = this.b();
        this.d(b2, array2, array4);
        final int a = this.a(array3);
        Label_0061: {
            if ((array3[0][0] & 0x1) != 0x0) {
                this.d(array, array2, array4);
                if (u == 0) {
                    break Label_0061;
                }
            }
            this.c(array);
        }
        final int d = this.d(b, array4);
        int n = 1;
        while (true) {
            while (true) {
                Label_0124: {
                    if (u == 0) {
                        break Label_0124;
                    }
                    this.a(b2, b2, b2, array4, b, (short)d);
                    if (this.b(array3, n)) {
                        this.a(array, array, b2, array4, b, (short)d);
                    }
                    ++n;
                }
                if (n < a) {
                    continue;
                }
                break;
            }
            if (u == 0) {
                return;
            }
            continue;
        }
    }
    
    private final int a(final int[][] array, final byte[] array2) {
        final int u = Lware.u;
        final int n = (this.a(array) + 7) / 8;
        if (array2.length < n) {
            System.out.println(b("T<_W/\u001d0HXaN?\\R-"));
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = n - 1;
        while (true) {
            while (true) {
                Label_0122: {
                    if (u == 0) {
                        break Label_0122;
                    }
                    final int n5 = (int)(array[0][n2] >>> n3 & 0xFFL);
                    final int n7;
                    int n6 = n7;
                    n3 += 8;
                    if (n3 >= 30) {
                        n3 -= 30;
                        ++n2;
                        if (n3 > 0) {
                            n6 |= (int)(array[0][n2] << 8 - n3 & 0xFFL);
                        }
                    }
                    array2[n4] = (byte)n6;
                    --n4;
                }
                if (n4 >= 0) {
                    continue;
                }
                break;
            }
            final int n7 = n;
            if (u == 0) {
                return n7;
            }
            continue;
        }
    }
    
    private final void a(final byte[] array, final int[][] array2) {
        final int u = Lware.u;
        array2[1][1] = 0;
        this.a(array2, array2[1][0] = (array.length * 8 + 30 - 1) / 30);
        int n = 0;
        array2[0][n] = 0;
        int n2 = 0;
        int n3 = array.length - 1;
        while (true) {
            Label_0130: {
                if (u == 0) {
                    break Label_0130;
                }
                final int n4 = array[n3] & 0xFF;
                final int[] array3 = array2[0];
                final int n5 = n;
                array3[n5] |= (n4 << n2 & 0x3FFFFFFF);
                n2 += 8;
                if (n2 >= 30) {
                    ++n;
                    array2[0][n] = 0;
                    n2 -= 30;
                    if (n2 > 0) {
                        array2[0][n] = n4 >>> 8 - n2;
                    }
                }
                --n3;
            }
            if (n3 >= 0) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0149: {
                if (u == 0) {
                    break Label_0149;
                }
                final int[] array4 = array2[1];
                final int n6 = 0;
                --array4[n6];
            }
            if (array2[1][0] <= 0 || array2[0][array2[1][0] - 1] != 0) {
                return;
            }
            continue;
        }
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0094: {
                if (length > 1) {
                    break Label_0094;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '=';
                            break;
                        }
                        case 1: {
                            c2 = 'R';
                            break;
                        }
                        case 2: {
                            c2 = '=';
                            break;
                        }
                        case 3: {
                            c2 = '>';
                            break;
                        }
                        default: {
                            c2 = 'A';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
