import java.util.Hashtable;
import java.awt.event.WindowEvent;
import java.awt.MediaTracker;
import java.net.InetAddress;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.lang.reflect.InvocationTargetException;
import GK.FK;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Container;
import java.net.URLEncoder;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import java.net.URLConnection;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Image;
import java.util.Date;
import java.util.Vector;
import java.util.Properties;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class myspeed extends Applet implements Runnable, WindowListener, HK
{
    private static final char[] LK;
    private static long UQ;
    private static final int JR = -1;
    private static final int KR = 0;
    private static final int LR = 1;
    private static final int MR = 2;
    private static final int NR = 3;
    private static final int OR = 4;
    private static final int PR = 9;
    private String[] WK;
    private long CK;
    private long DL;
    private long[] AK;
    private Button PK;
    private Button SK;
    private TextField QK;
    private boolean XL;
    private Properties VP;
    private Vector AO;
    private String EQ;
    private boolean JP;
    private String RL;
    private int HQ;
    private int FO;
    private String GN;
    private boolean SL;
    private int PL;
    private Date VM;
    private int LM;
    private long ML;
    private long XM;
    private long SN;
    private int YM;
    private int ZM;
    private int AM;
    private int ON;
    private String TM;
    private Image LQ;
    private boolean GL;
    private Point JL;
    private Rectangle SP;
    private Rectangle TP;
    private Rectangle RP;
    private boolean KQ;
    private boolean UL;
    private boolean CN;
    private Thread QO;
    private Thread QL;
    private int WN;
    private Frame XP;
    private TextArea AN;
    private String RO;
    private static char[] MK;
    private String PP;
    private long QP;
    private char TN;
    private static Object NK;
    private static boolean OK;
    private static Image PO;
    static Class BP;
    static Class CP;
    
    static {
        LK = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
        myspeed.MK = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        myspeed.NK = new Object();
        myspeed.OK = false;
    }
    
    private static int UK(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    private static long UK(final long n) {
        return (n < 0L) ? (-n) : n;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.PK || event.target == this.QK) {
            this.RK();
        }
        else if (event.target == this.SK) {
            this.TK();
        }
        return true;
    }
    
    private static String WM(long uk) {
        final StringBuffer sb = new StringBuffer();
        final boolean b = uk < 0L;
        uk = UK(uk);
        for (int n = 0; n == 0 || uk != 0L; uk /= 10L, ++n) {
            if (n > 0 && n % 3 == 0) {
                sb.append(VK("*"));
            }
            sb.append(myspeed.LK[(int)(uk % 10L)]);
        }
        if (b) {
            sb.append(VK(")"));
        }
        return sb.reverse().toString();
    }
    
    private static int GQ(final URLConnection urlConnection) {
        final long lastModified = urlConnection.getLastModified();
        final long date = urlConnection.getDate();
        return (date != 0L && lastModified != 0L) ? UK((int)((date - lastModified) / 86400000L)) : -1;
    }
    
    private String NM(final boolean b) {
        return String.valueOf(this.WK[0]) + ((this.XK() && b) ? VK("6eCFOPBBLFGTI") : "");
    }
    
    private URL MM() {
        return VK("\\").equals(this.YK(VK("R"))) ? this.getCodeBase() : this.getDocumentBase();
    }
    
    private int LL(final long n) {
        return (int)ZK(2147483647L, n / 8L);
    }
    
    private Color XO(final long n) {
        final long[] ak = this.AK;
        for (int n2 = ak.length / 2, i = 0; i < n2 - 1; ++i) {
            final long n3 = ak[i * 2 + 0];
            final long n4 = ak[i * 2 + 2];
            if (n >= n3 && n <= n4) {
                return this.BK(n3, n4, n, (int)ak[i * 2 + 1], (int)ak[i * 2 + 3]);
            }
        }
        return Color.white;
    }
    
    private double YO(long el) {
        final long ck = this.CK;
        final long dl = this.DL;
        el = EL(ck, ZK(dl, el));
        return (FL(el) - FL(ck)) / (FL(dl) - FL(ck));
    }
    
    private String SQ(final long n) {
        final boolean gl = this.GL;
        final long n2 = gl ? (n / 8L) : n;
        final int n3 = 100;
        long n4 = n2 * n3 / 1000L;
        final boolean b = n4 / n3 > 1000L;
        final String s = b ? this.HL(VK("h")) : this.HL(VK("j"));
        if (b) {
            n4 /= 1000L;
        }
        final int n5 = (int)(n4 / 100L);
        final int n6 = (int)(n4 % 100L);
        String s2 = String.valueOf(VK("")) + n5;
        if (n5 < 100) {
            s2 = String.valueOf(s2) + VK("(") + n4 % 100L / 10L;
            if (n5 < 10) {
                s2 = String.valueOf(s2) + n4 % 10L;
            }
        }
        return String.valueOf(s2) + VK("6") + s + (gl ? this.HL(VK("sEB")) : this.HL(VK("SEB")));
    }
    
    private Color ZO(final Color color, final int n) {
        return new Color(color.getRed() * n / 100, color.getGreen() * n / 100, color.getBlue() * n / 100);
    }
    
    private void BQ() {
        try {
            if (VK(",").equals(this.PK.getLabel())) {
                this.RK();
                return;
            }
            final boolean b = !"no".equals(this.HL(VK("IFTQTGLHTALFG")));
            final long el = EL(14400L, this.CK);
            final long zk = ZK(this.DL, EL(this.CK * 2L, 100000000L));
            long n = el;
            double n2 = 1.1;
            IL(200L);
            int n3 = 0;
            while (this.OL() && this.PL == -1) {
                final Point jl = this.JL;
                if (jl != null) {
                    this.ML = this.LL((this.KL(jl.x) + 500L) / 1000L * 1000L);
                    this.NL();
                }
                else {
                    final long n4 = (long)(n * n2);
                    if (n4 < el || n4 > zk) {
                        n2 = ((n2 < 1.0) ? 1.1 : 0.9090909090909091);
                    }
                    n *= (long)n2;
                    this.ML = (b ? this.LL(n) : 0);
                    this.NL();
                }
                IL(50L);
                ++n3;
            }
        }
        finally {
            this.QL = null;
        }
        this.QL = null;
    }
    
    private void HR() {
        final String rl = this.RL;
        if (rl != null && rl.toLowerCase().indexOf(VK("RF<FAP")) > 0 && !this.SL) {
            this.SL = true;
            final String[] array = { VK("pccfc{6aMP6kTJTCAT'aFHRTA6rF<FAP6maae6RFGGPRAFC6HT=mAAEmPTQPCbL;P6LB6AFF6BHTII("), VK("bfi`alfg{6lG6AMP6yrFGGPRAFCw6.BPC?PC(=HI-*6BPA6/HT=mAAEmPTQPCbL;Px4%#%&~$4/") };
            for (int i = 0; i < array.length; ++i) {
                this.TL(array[i]);
            }
        }
    }
    
    private void HO() {
        if (this.UL || !this.VL()) {
            this.WL();
        }
    }
    
    private void XN(final Graphics graphics, final String s, final int n, int n2, final Color color) {
        final Font font = graphics.getFont();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        int el = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, VK("\n"));
        final int countTokens = stringTokenizer.countTokens();
        for (int i = 0; i < countTokens; ++i) {
            el = EL(el, fontMetrics.stringWidth(stringTokenizer.nextToken()));
        }
        n2 -= (countTokens - 1) * height / 2;
        if (color != null) {
            graphics.setColor(color);
            graphics.fillRect(n - 3, n2 - height, el + 3 + 3, height * countTokens);
            graphics.setColor(Color.black);
            graphics.drawRect(n - 3, n2 - height, el + 3 + 3, height * countTokens);
        }
        graphics.setColor(Color.black);
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s, VK("\n"));
        for (int j = 0; j < countTokens; ++j) {
            graphics.drawString(stringTokenizer2.nextToken(), n, n2 - fontMetrics.getDescent());
            n2 += height;
        }
        graphics.setFont(font);
    }
    
    private void XN(final Graphics graphics, String nextToken, int n, final Color color) {
        final Font font = graphics.getFont();
        graphics.setFont(new Font(VK("mPI?PALRT"), 0, 16));
        final int width = this.size().width;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final StringTokenizer stringTokenizer = new StringTokenizer(nextToken, VK("\n"));
        final int countTokens = stringTokenizer.countTokens();
        n -= (countTokens - 1) * height / 2;
        for (int i = 0; i < countTokens; ++i) {
            nextToken = stringTokenizer.nextToken();
            final int stringWidth = fontMetrics.stringWidth(nextToken);
            final int n2 = (width - stringWidth) / 2;
            if (color != null) {
                graphics.setColor(color);
                graphics.fillRect(n2 - 3, n - height, stringWidth + 3 + 3, height);
                graphics.setColor(Color.black);
                graphics.drawRect(n2 - 3, n - height, stringWidth + 3 + 3, height);
            }
            graphics.setColor(Color.black);
            graphics.drawString(nextToken, n2, n - fontMetrics.getDescent());
            n += height;
        }
        graphics.setFont(font);
    }
    
    private boolean GM(final Graphics graphics) {
        String string = "";
        for (int i = 0; i < this.WK.length; ++i) {
            string = String.valueOf(string) + this.WK[i];
        }
        long n = 0L;
        final int length = string.length();
        for (char c = '\0'; c < length; ++c) {
            n ^= (string.charAt(c) + c) * 1013359411854961L;
        }
        if (n != 21330975360434766L || "-".equals(this.YK(VK("S")))) {
            final Dimension size = this.size();
            if (graphics != null) {
                graphics.setColor(Color.black);
                graphics.drawLine(0, 0, size.width, size.height);
                graphics.drawLine(0, size.height, size.width, 0);
            }
            return true;
        }
        return false;
    }
    
    private void PQ() {
        if (!this.XL) {
            this.XL = true;
            this.YL();
            this.ZL();
            this.AL();
            this.BL();
            this.CL();
            if (this.size().height < 300) {
                this.setFont(new Font(VK("mPI?PALRT"), 0, 9));
            }
            final String dm = this.DM();
            final String em = this.EM(null, this.WK);
            final boolean equals = VK("\\").equals(this.YK(VK("C")));
            KK.FM(dm, VK("PHTLI"));
            if (!this.GM(null) && !this.HM(null) && (em == null || em == this.WK[3])) {
                if (!equals || (KK.IM(dm) && KK.JM(dm))) {
                    int n = 3;
                    final String km = this.KM(VK("blq"));
                    if (km != null) {
                        final boolean startsWith = km.startsWith(VK(","));
                        this.QK.setText(startsWith ? km.substring(1) : km);
                        if (!startsWith) {
                            this.add(this.QK);
                            this.QK.resize(this.QK.preferredSize());
                            this.QK.move(n, 3);
                            n += this.QK.size().width + 3;
                            this.QK.selectAll();
                        }
                    }
                    else if (this.XK()) {
                        final String fm = KK.FM(dm, VK("PHTLI"));
                        final int n2 = (fm != null) ? fm.indexOf(64) : -1;
                        if (n2 > 0) {
                            this.QK.setText(fm.substring(0, n2));
                        }
                    }
                    this.PK.setLabel(this.HL(VK("BATCA")));
                    this.add(this.PK);
                    this.PK.resize(this.PK.preferredSize());
                    this.PK.move(n, 3);
                    this.LM = 3 + this.PK.size().height + 2;
                    ((Component)((km != null && this.QK.isShowing()) ? this.QK : this.PK)).requestFocus();
                    (this.QL = new Thread(this, VK("h<bEPPQ)tGLHTALFG"))).start();
                }
                else {
                    final KK kk = new KK(this, dm);
                    this.add(kk);
                    kk.resize(kk.preferredSize());
                    kk.move(5, 5);
                    kk.layout();
                    this.LM = 5 + kk.size().height;
                    (this.QL = new Thread(this, VK("h<bEPPQ)tGLHTALFG"))).start();
                }
            }
            this.NL();
        }
    }
    
    public String doGetReportURL() {
        try {
            final String km = this.KM(VK("HBB"));
            if (km != null) {
                return new URL(this.MM(), String.valueOf(km) + VK("CPEFCA")).toString();
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    private boolean HM(final Graphics graphics) {
        final String nm = this.NM(false);
        final String om = this.OM(VK("?PCBLFG"));
        final String om2 = this.OM(VK("pfo"));
        if (om == null || om2 == null) {
            this.QM(graphics, String.valueOf(VK("pCCFC6TRRPBBLGN6/")) + this.PM() + VK("/6OLIP6FG6BPC?PC"), Color.red);
            return true;
        }
        if (!nm.equals(om)) {
            this.QM(graphics, this.HL(VK("CPOCPBM"), new String[] { nm, om }), Color.red);
            return true;
        }
        return false;
    }
    
    private void LO() {
        final String om = this.OM(VK("KB"));
        if (om != null) {
            this.SM(this.RM(om, true, null));
        }
    }
    
    private String RM(String s, final boolean b, final String s2) {
        final String tm = this.TM;
        s = JK.UM(s, VK("2`ci2"), this.MM().toString());
        s = JK.UM(s, VK("2qtapalhp2"), this.VM.toString());
        s = JK.UM(s, VK("2qbeppq2"), Long.toString(this.ML * 8L));
        s = JK.UM(s, VK("2rqbeppq2"), WM(this.ML * 8L));
        s = JK.UM(s, VK("2`beppq2"), Long.toString(this.XM * 8L));
        s = JK.UM(s, VK("2r`beppq2"), WM(this.XM * 8L));
        s = JK.UM(s, VK("2dfb2"), Integer.toString(this.YM));
        s = JK.UM(s, VK("2caa2"), Integer.toString(this.ZM));
        s = JK.UM(s, VK("2ht]et`bp2"), Integer.toString(this.AM));
        s = JK.UM(s, VK("2psfq\\2"), (s2 != null) ? s2 : "");
        final String s3 = b ? "\"" : "";
        s = JK.UM(s, VK("2hbblq2"), String.valueOf(s3) + ((tm != null) ? tm : "NA") + s3);
        return s;
    }
    
    private boolean VL() {
        boolean b = false;
        try {
            final String vk = VK("HBB(QF>GIFTQ(EFCA");
            final int bm = this.BM(vk, -1);
            if (bm > 0) {
                this.CM(String.valueOf(VK("qf^giftqlgn6?LT6")) + vk + VK("x") + bm);
                final Socket socket = new Socket(this.MM().getHost(), bm);
                JK.DN(socket, 65536);
                if (JK.EN(socket) > 0) {
                    this.CM(String.valueOf(VK("6BFRJPA6S@OOPCB{6BGQx")) + JK.FN(socket) + VK("6CR?x") + JK.EN(socket));
                }
                b = true;
                final InputStream inputStream = socket.getInputStream();
                final int[] array = new int[inputStream.read() & 0xFF];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = (0xFF & inputStream.read());
                }
                if (array.length == 4) {
                    this.GN = array[0] + "." + array[1] + "." + array[2] + "." + array[3];
                }
                this.JN(inputStream, this.HN(), VK("h<bEPPQbPC?IPA"));
            }
            else if (bm == 0) {
                this.CM(String.valueOf(vk) + VK("xQLBTSIPQ"));
            }
        }
        catch (Exception ex) {
            this.TL(" " + ex);
        }
        return b;
    }
    
    private boolean VO() {
        Socket socket = null;
        try {
            final String vk = VK("HBB(@EIFTQ(EFCA");
            final int bm = this.BM(vk, -1);
            if (bm > 0) {
                this.CM(String.valueOf(VK("`eiftqlgn6?LT6")) + vk + VK("x") + bm);
                final byte[] kn = JK.KN(65536);
                final int ln = this.LN();
                socket = new Socket(this.MM().getHost(), bm);
                JK.MN(socket, 65536);
                if (JK.EN(socket) > 0) {
                    this.CM(String.valueOf(VK("6BFRJPA6S@OOPCB{6BGQx")) + JK.FN(socket) + VK("6CR?x") + JK.EN(socket));
                }
                final DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                final OutputStream outputStream = socket.getOutputStream();
                final long nn = JK.NN();
                long n;
                long nn2 = n = nn;
                int n2 = 0;
                int int1 = 0;
                long n3 = 0L;
                for (int hn = this.HN(); this.OL() && nn2 - nn < ln && n2 < hn; nn2 = JK.NN()) {
                    final int n4 = (n2 < 65536) ? 8192 : ((n2 < 262144) ? 32768 : 65536);
                    outputStream.write(kn, 0, n4);
                    n2 += n4;
                    while (dataInputStream.available() >= 8) {
                        n3 = dataInputStream.readInt();
                        int1 = dataInputStream.readInt();
                        this.XM = ((n3 > 0L) ? (int1 * 1000L / n3) : 0L);
                        if (nn2 > n) {
                            n = nn2 + 250L;
                            this.NL();
                        }
                    }
                    this.ON = ZK(100, EL((int)((nn2 - nn) * 100L / ln), (int)(n2 * 100L / hn)));
                }
                this.CM(String.valueOf(VK("`eiftqpq6")) + WM(int1) + VK("6S<APB6LG6") + n3 + VK("6HB6.") + WM(this.XM * 8L) + VK("6SEB-"));
                return true;
            }
            if (bm == 0) {
                this.CM(String.valueOf(vk) + VK("xQLBTSIPQ"));
            }
        }
        catch (Exception ex) {
            this.TL(" " + ex);
        }
        finally {
            try {
                socket.close();
            }
            catch (Exception ex2) {}
        }
        try {
            socket.close();
        }
        catch (Exception ex3) {}
        return false;
    }
    
    private void WL() {
        InputStream inputStream = null;
        final String om = this.OM(VK("QTAT(SLG"));
        final String s = (om != null) ? om : VK("QTAT(SLG");
        try {
            final URL url = new URL(this.MM(), String.valueOf(s) + VK("vAx") + JK.NN());
            final URLConnection pn = JK.PN(url.openConnection());
            final int contentLength = pn.getContentLength();
            if (contentLength <= 0) {
                throw new RuntimeException(url + VK("6HLBBLGN6rFGAPGA)iPGNAM6MPTQPC6OLPIQ"));
            }
            inputStream = pn.getInputStream();
            this.CM(String.valueOf(VK("qf^giftqlgn6?LT6maae6OLIP6")) + s);
            this.JN(inputStream, contentLength, s);
        }
        catch (Throwable t) {
            this.QN(s, t);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception ex) {}
            this.ON = 100;
        }
        try {
            inputStream.close();
        }
        catch (Exception ex2) {}
        this.ON = 100;
    }
    
    private void WO() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int zk = 99999;
        final int n4 = 5;
        final long n5 = this.LN();
        final long nn = JK.NN();
        long n6;
        long nn2 = n6 = nn;
        long el = EL(0L, this.ML / 15L);
        int[] rn = null;
        this.CM(String.valueOf(VK("`eiftqlgn6?LT6maae6OLIP6")) + VK("@EIFTQ(SLG"));
        if (this.ML > 8192L) {
            IL(1000L);
            rn = this.RN(8192);
            if (rn != null) {
                this.CM(String.valueOf(VK("6`e6")) + WM(rn[0]) + VK("6S<APB6LG6") + rn[1] + VK("6HB"));
            }
        }
        for (int n7 = 0; n7 < n4 && this.OL() && nn2 - nn < n5; ++n7) {
            final int[] rn2 = this.RN(0);
            final int[] rn3 = this.RN(EL(8192, (int)(el * EL(4L, n5 / 3L) / 1000L)));
            if (rn2 != null && rn3 != null) {
                this.CM(String.valueOf(VK("6@E6")) + WM(rn3[0]) + VK("6S<APB6LG6") + rn3[1] + VK(")") + rn2[1] + VK("6HB"));
                zk = ZK(zk, ZK(rn2[1], rn3[1]));
                n += rn3[0];
                n2 += rn3[1];
                ++n3;
                final int n8 = n2 - n3 * zk;
                el = ((n8 > 0) ? ((int)(n * 1000L / n8)) : 1250000);
                if (n8 > 10) {
                    this.XM = el;
                }
            }
            nn2 = JK.NN();
            this.ON = ZK(100, EL((int)((nn2 - nn) * 100L / n5), n7 * 100 / n4));
            if (nn2 > n6) {
                n6 = nn2 + 25L;
                this.NL();
            }
        }
        this.CM(String.valueOf(VK("`eiftqpq6")) + WM(n) + VK("6S<APB6LG6") + (n2 - n3 * zk) + VK("6HB6.") + WM(this.XM * 8L) + VK("6SEB-"));
        if (rn != null && zk < 1000) {
            final int n9 = rn[0];
            final int n10 = rn[1] - zk;
            final long sn = (n10 > 0) ? (n9 * 1000L / n10) : 0L;
            if (this.XM > 0L && sn > this.XM * 3L / 2L) {
                this.SN = sn;
                this.CM(String.valueOf(VK("`eiftq6s`cba6")) + WM(n9) + VK("6S<APB6LG6") + n10 + VK("6HB6.") + WM(this.SN * 8L) + VK("6SEB-"));
            }
        }
    }
    
    private void YQ(final Graphics graphics) {
        boolean b = false;
        this.EM(graphics, this.WK);
        String s = null;
        if (this.TN == 'v') {
            s = this.NM(true);
        }
        else if (this.TN == 'c') {
            s = this.WK[1];
        }
        else if (this.TN == 'p') {
            s = this.WK[2];
        }
        else if (this.TN == 'j') {
            s = this.VN(true);
        }
        else if (this.TN == 's' || this.TN == 'b' || this.TN == 'e') {
            s = this.YK("" + this.TN);
        }
        else if (this.TN == 'm') {
            final Runtime runtime = Runtime.getRuntime();
            s = runtime.totalMemory() / 1024L + VK("J66") + runtime.freeMemory() / 1024L + VK("J");
        }
        else if (this.WN > 0 && 9 != this.PL) {
            b = true;
            s = this.OM(VK("PCCFCBIFNNPQ"));
        }
        if (s != null) {
            this.XN(graphics, s, this.size().height - 10, new Color(b ? 16765136 : 16777152));
        }
    }
    
    private int LP(final InputStream inputStream, final byte[] array) {
        final int available = inputStream.available();
        return (available > 0) ? inputStream.read(array, 0, ZK(array.length, available)) : 0;
    }
    
    private int KP(final InputStream inputStream, final byte[] array) {
        int read = inputStream.read(array, 0, 256);
        if (read > 0) {
            final int available = inputStream.available();
            if (available > 0) {
                read += EL(0, inputStream.read(array, read, ZK(array.length - read, available)));
            }
        }
        return read;
    }
    
    private void NL() {
        this.repaint();
    }
    
    private void YN(final String s) {
        try {
            final String om = this.OM(VK("CPEFCA@CI"));
            if (om != null) {
                JK.PN(new URL(this.MM(), String.valueOf(om) + s).openConnection()).getInputStream().close();
            }
        }
        catch (Throwable t) {}
    }
    
    private void IQ(final String s) {
        this.YN(String.valueOf(VK("vP=RPEALFGx")) + URLEncoder.encode(s));
    }
    
    private void DO() {
        try {
            final String km = this.KM(VK("HBB"));
            if (km != null) {
                final URLConnection pn = JK.PN(new URL(this.MM(), String.valueOf(km) + VK("CPEFCA")).openConnection());
                pn.setDoOutput(true);
                pn.setUseCaches(false);
                final OutputStream outputStream = pn.getOutputStream();
                final String zn = this.ZN();
                outputStream.write(JK.BN(String.valueOf(VK("Qx")) + this.ML * 8L + VK("0@x") + this.XM * 8L + VK("0Dx") + this.YM + VK("0CAAx") + this.ZM + VK("0HT=ET@BPx") + this.AM + VK("0Lx") + URLEncoder.encode(this.AN.getText()) + ((this.GN != null) ? (String.valueOf(VK("0LEx")) + this.GN) : "") + ((zn != null) ? (String.valueOf(VK("0BLQx")) + URLEncoder.encode(zn)) : "")));
                String line;
                while ((line = new DataInputStream(pn.getInputStream()).readLine()) != null) {
                    final int index = line.indexOf(61);
                    if (index > 0) {
                        final String substring = line.substring(0, index);
                        final String substring2 = line.substring(index + 1);
                        if (!VK("HBBLQ").equals(substring)) {
                            continue;
                        }
                        this.TM = substring2;
                        this.CM(String.valueOf(VK("hbblqx")) + this.TM);
                        this.CN = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.TL(String.valueOf(VK("QFcPEFCAhbb{6")) + ex);
        }
    }
    
    private void KO() {
        if (this.OL()) {
            final String zn = this.ZN();
            this.YN(String.valueOf(VK("vQx")) + this.ML * 8L + VK("0@x") + this.XM * 8L + VK("0Dx") + this.YM + VK("0Cx") + this.ZM + VK("0Ex") + this.AM + ((this.WN > 0) ? (String.valueOf(VK("0Px")) + this.WN) : "") + ((zn != null) ? (String.valueOf(VK("0Bx")) + URLEncoder.encode(zn)) : "") + VK("0Ax") + JK.NN());
            this.DO();
        }
    }
    
    private void AQ() {
        this.PL = 0;
        while (this.QL != null) {
            IL(50L);
        }
        this.LM = 0;
        this.VM = new Date();
        this.ML = 0L;
        this.XM = 0L;
        this.SN = 0L;
        this.YM = 0;
        this.ZM = 0;
        this.AM = 0;
        this.ON = 0;
        this.TM = null;
        this.NL();
        Thread.currentThread().setPriority(10);
        this.FO = JK.EO();
        this.CM(String.valueOf(VK("ALRJCPBx")) + this.FO + VK("6HB"));
        this.PL = 1;
        this.ON = 0;
        this.HO();
        this.ON = 100;
        this.NL();
        IL(250L);
        this.PL = 2;
        this.ON = 0;
        this.NL();
        this.IO();
        this.ON = 100;
        this.NL();
        IL(250L);
        this.PL = 3;
        this.ZM = new IK().JO(this, this.MM());
        this.NL();
        this.PL = 4;
        this.NL();
        this.KO();
        IL((this.WN > 0) ? 3000 : 0);
        this.PL = 9;
        this.NL();
        this.LO();
    }
    
    private boolean MQ(final int n, final int n2) {
        final boolean mo = this.MO(n, n2);
        final boolean no = this.NO(n, n2);
        final boolean oo = this.OO(n, n2);
        final int cursor = no ? 12 : (mo ? 12 : (oo ? 12 : 0));
        try {
            for (Container parent = this; parent != null; parent = parent.getParent()) {
                if (parent instanceof Frame) {
                    ((Frame)parent).setCursor(cursor);
                    break;
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    private void TO(final String s) {
        try {
            final URL url = new URL(this.MM(), s);
            this.CM("URL: " + url);
            this.getAppletContext().showDocument(url);
        }
        catch (Throwable t) {
            this.TL(" " + t);
        }
    }
    
    private static boolean NQ(final Component component, final Graphics graphics) {
        synchronized (myspeed.NK) {
            if (myspeed.OK) {
                // monitorexit(myspeed.NK)
                return true;
            }
            try {
                myspeed.OK = true;
                final Rectangle rectangle = new Rectangle(component.size());
                final Rectangle clipRect = graphics.getClipRect();
                if (clipRect != null && clipRect.width > 0 && clipRect.height > 0) {
                    rectangle.intersection(clipRect);
                }
                Image po = myspeed.PO;
                if (po == null || rectangle.width > po.getWidth(null) || rectangle.height > po.getHeight(null)) {
                    po = (myspeed.PO = component.createImage(rectangle.width, rectangle.height));
                }
                final Graphics graphics2 = po.getGraphics();
                graphics2.translate(-rectangle.x, -rectangle.y);
                graphics2.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                graphics2.setColor(component.getBackground());
                graphics2.fillRect(0, 0, rectangle.width, rectangle.height);
                graphics2.setColor(graphics.getColor());
                graphics2.setFont(graphics.getFont());
                component.paint(graphics2);
                graphics.drawImage(po, rectangle.x, rectangle.y, rectangle.width, rectangle.height, 0, 0, rectangle.width, rectangle.height, null);
                graphics2.dispose();
            }
            catch (Exception ex) {
                myspeed.PO = null;
                ex.printStackTrace();
                final int height = graphics.getFontMetrics().getHeight();
                graphics.setColor(Color.red);
                graphics.drawString(ex.toString(), 2, height);
            }
            finally {
                myspeed.OK = false;
            }
            myspeed.OK = false;
            // monitorexit(myspeed.NK)
            return false;
        }
    }
    
    public void doStartButton() {
        if (this.XK()) {
            this.RK();
        }
    }
    
    private void RK() {
        final boolean b = this.QO == null && this.PK.isShowing();
        final boolean b2 = this.QO != null && !this.QO.isAlive();
        if (b || b2) {
            if (this.QK.isShowing() && this.ZN() == null) {
                this.QK.selectAll();
                this.QK.requestFocus();
                return;
            }
            this.requestFocus();
            this.remove(this.QK);
            this.remove(this.PK);
            if (this.RO == null) {
                this.RO = this.AN.getText();
            }
            else {
                this.AN.setText(this.RO);
            }
            (this.QO = new Thread(this, VK("h<bEPPQ)aPBA"))).start();
        }
    }
    
    private void TK() {
        final String km = this.KM(VK("B@SHLA(@CI"));
        if (km != null) {
            final String km2 = this.KM(VK("B@SHLA(PSFQ<"));
            final String rm = this.RM(km, false, (km2 != null) ? SO(this.RM(km2, false, null)) : null);
            final String vk = VK("KT?TBRCLEA{");
            if (rm.startsWith(vk)) {
                this.SM(rm.substring(vk.length()));
            }
            else {
                this.TO(rm);
            }
        }
    }
    
    private void IO() {
        if (this.UO(VK("@EIFTQ")) && (this.UL || !this.VO())) {
            this.WO();
        }
    }
    
    private void RQ(final Graphics graphics, final Dimension dimension) {
        if (this.YK(VK("B")) == null) {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int height = fontMetrics.getHeight();
            final int n = height / 2;
            final String s = this.WK[6];
            final int stringWidth = fontMetrics.stringWidth(String.valueOf(s) + VK("6"));
            int n2 = 0;
            graphics.setColor(new Color(14211288));
            for (int i = height; i < dimension.height; i += height + n) {
                for (int j = 0; j < dimension.width; j += stringWidth + n) {
                    graphics.drawString(s, n2 + j, i);
                }
                n2 = ((n2 == 0) ? (-(stringWidth + n) / 2) : 0);
            }
        }
        for (int k = 0; k < dimension.width; ++k) {
            final int n3 = dimension.height - k * dimension.height / dimension.width;
            graphics.setColor(this.XO(this.KL(k)));
            graphics.drawLine(k, n3, k, dimension.height);
        }
        for (long n4 = 10000L; n4 < 10000000000L; n4 *= 10L) {
            if (n4 > this.CK && n4 < this.DL) {
                final double yo = this.YO(n4);
                final int n5 = (int)(dimension.width * yo);
                final int n6 = dimension.height - (int)(dimension.height * yo);
                graphics.setColor(this.ZO(this.XO(n4), 90));
                graphics.drawLine(n5, n6, n5, dimension.height);
            }
        }
        graphics.setColor(Color.white);
        graphics.drawLine(0, dimension.height - 1, dimension.width, -1);
        graphics.setColor(Color.gray);
        graphics.drawLine(0, dimension.height, dimension.width, 0);
        final Color lightGray = Color.lightGray;
        final Color color = new Color(11579647);
        final int height2 = graphics.getFontMetrics().getHeight();
        for (int l = 0; l < this.AO.size(); ++l) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.AO.elementAt(l), VK("*"));
            if (stringTokenizer.countTokens() == 5) {
                final int bo = BO(stringTokenizer.nextToken());
                final long co = CO(stringTokenizer.nextToken());
                if (co > this.CK && co < this.DL) {
                    this.EP(graphics, height2 * bo / 100, co, DP(stringTokenizer.nextToken()), DP(stringTokenizer.nextToken()), DP(stringTokenizer.nextToken()), (co == 100000L || co == 1000000L || co == 10000000L || co == 100000000L || co == 1000000000L) ? color : lightGray);
                }
            }
        }
        final Image fp = this.FP();
        if (fp != null) {
            graphics.drawImage(fp, 0, 0, null);
        }
    }
    
    private void GP(final Graphics graphics, final int n, final int n2, final Color color) {
        graphics.setColor(Color.black);
        graphics.fillRect(n - 3, n2 - 1, 7, 3);
        graphics.fillRect(n - 2, n2 - 2, 5, 5);
        graphics.fillRect(n - 1, n2 - 3, 3, 7);
        graphics.setColor(color);
        graphics.fillRect(n - 2, n2 - 1, 5, 3);
        graphics.fillRect(n - 1, n2 - 2, 3, 5);
    }
    
    private void EP(final Graphics graphics, final int n, final long n2, final String s, final String s2, final String s3, final Color color) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Dimension size = this.size();
        final double yo = this.YO(n2);
        final int n3 = (int)(size.width * yo);
        final int n4 = size.height - (int)(size.height * yo);
        this.GP(graphics, n3, n4, color);
        final String s4 = this.GL ? s3 : s2;
        if (s4 != null) {
            graphics.setColor(Color.black);
            final boolean b = s != null && s4.startsWith("$");
            final String s5 = b ? s : (String.valueOf((s != null) ? new StringBuffer(String.valueOf(s)).append(VK("6)6")).toString() : VK("")) + s4);
            final String s6 = b ? s4.substring(1) : null;
            final int height = fontMetrics.getHeight();
            final int n5 = n3 + 2 + n;
            final int n6 = n4 + height + 1 - n;
            graphics.drawString(s5, n5, n6);
            if (s6 != null) {
                graphics.drawString(s6, n5, n6 + height);
            }
        }
    }
    
    private void QM(final Graphics graphics, final String s, final Color color) {
        if (graphics != null) {
            final int height = graphics.getFontMetrics().getHeight();
            graphics.setColor(color);
            int n = 1;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, VK("\n"), true);
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals(VK("\n"))) {
                    ++n;
                }
                else {
                    graphics.drawString(nextToken, 5, height * n);
                }
            }
        }
    }
    
    private void TQ(final Graphics graphics, final int n, final int n2, final int n3) {
        final int n4 = 100;
        graphics.setColor(new Color(10526880));
        graphics.fillRect(n - 1, n2 - 1, n4 + 1 + 1 + 1, 3);
        graphics.setColor(new Color(14737632));
        graphics.drawLine(n, n2, n + n4, n2);
        graphics.setColor(new Color(4210943));
        graphics.drawLine(n, n2, n + n4 * n3 / 100, n2);
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
                sb.append(' ');
            }
            else {
                sb.append('%');
                sb.append(myspeed.MK[char1 / '\u0010' & '\u000f']);
                sb.append(myspeed.MK[char1 & '\u000f']);
            }
        }
        return sb.toString();
    }
    
    private void TL(final String s) {
        this.CM(s);
        System.err.println(s);
    }
    
    private Image FP() {
        final String km = this.KM(VK("STRJNCF@GQF?PCIT<"));
        return (km != null) ? this.HP(this.getImage(this.MM(), km)) : null;
    }
    
    private String DM() {
        final String ip = KK.IP(this.getParameter(VK("@CI")));
        if (ip != null) {
            return ip;
        }
        final String string = this.getDocumentBase().toString();
        final String ip2 = KK.IP(string);
        if (ip2 != null) {
            return ip2;
        }
        return string;
    }
    
    private int CQ() {
        final String rl = this.RL;
        int zk = 8192;
        final int bm = this.BM(VK("HT=MPTQPCBL;P"), -1);
        if (bm > 0) {
            zk = bm;
        }
        else if (!this.JP && rl != null) {
            final String lowerCase = rl.toLowerCase();
            if (lowerCase.indexOf(VK("TETRMP")) >= 0) {
                zk = 131072;
            }
            else if (lowerCase.indexOf(VK("?LB@TI>TCP")) >= 0) {
                zk = 87040;
            }
            else if (lowerCase.indexOf(VK("LLB' (")) >= 0) {
                zk = 16384;
            }
        }
        if (this.WN > 0) {
            zk = ZK(zk, 8192);
        }
        return EL(256, zk - 768);
    }
    
    private int DR(final int n) {
        final String rl = this.RL;
        if (rl != null && rl.toLowerCase().indexOf(VK("TETRMP")) >= 0 && n > 65536) {
            return 4000;
        }
        return 1000;
    }
    
    private int LN() {
        return EL(2000, ZK(45000, this.BM(VK("ALHPF@A"), 8000)));
    }
    
    private String PM() {
        final String vk = VK("H<BEPPQ(SLG");
        final String parameter = this.getParameter(vk);
        return (parameter != null) ? parameter : vk;
    }
    
    private String ZN() {
        final String trim = this.QK.getText().trim();
        final String km = this.KM(VK("blq"));
        return (trim.length() > 0 && !trim.equals(km)) ? trim : null;
    }
    
    private String ZP(final String s, final String s2) {
        return System.getProperty(s, s2);
    }
    
    private void JN(final InputStream inputStream, final int n, final String s) {
        try {
            final byte[] array = new byte[65536];
            final int n2 = n / 20;
            final long[] array2 = new long[n / 256 + 100];
            final int[] array3 = new int[n / 256 + 100];
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            while (JK.NN() - JK.NN() < 1000L && n4 < n2) {
                n4 += EL(0, this.KP(inputStream, array));
            }
            long nn;
            while ((nn = JK.NN()) == JK.NN()) {
                n5 += EL(0, this.LP(inputStream, array));
            }
            final long n7;
            long n6;
            long nn2 = n6 = (n7 = nn);
            int n8 = 0;
            final int ln = this.LN();
            int kp;
            while (this.OL() && nn2 - n7 < ln && -1 != (kp = this.KP(inputStream, array))) {
                nn2 = JK.NN();
                n8 += kp;
                final long n9 = nn2 - n7;
                if (n3 < array2.length) {
                    array2[n3] = n9;
                    array3[n3] = kp;
                    ++n3;
                }
                this.ON = ZK(100, EL((int)(n9 * 100L / ln), (int)(n8 * 100L / n)));
                if (n9 > 500L) {
                    this.ML = ((n9 > 0L) ? (n8 * 1000L / n9) : 0L);
                }
                if (nn2 > n6) {
                    n6 = nn2 + 250L;
                    this.NL();
                }
            }
            final long mp = JK.MP();
            final long n10 = mp - n7;
            final int n11 = n - n4 - n5 - n8;
            final long[] np = NP(array2, n3);
            final int[] np2 = NP(array3, n3);
            if (this.OL()) {
                final StringBuffer sb = new StringBuffer();
                int n12;
                for (int i = 0; i < np.length; i = n12) {
                    for (n12 = i + 1; n12 < np.length && np[i] == np[n12] && np2[i] == np2[n12]; ++n12) {}
                    sb.append(String.valueOf(VK("6")) + np[i] + VK("6") + np2[i] + VK("6") + (n12 - i) + VK("\n"));
                    this.AM = ((i > 0) ? ((int)EL(this.AM, np[i] - np[i - 1])) : 0);
                }
                final int n13 = 65536;
                String s2 = sb.toString();
                if (s2.length() > n13) {
                    final String substring = s2.substring(0, n13 / 2);
                    final String substring2 = s2.substring(s2.length() - n13 / 2);
                    s2 = String.valueOf(substring.substring(0, substring.lastIndexOf(10) + 1)) + VK("6((((") + substring2.substring(substring2.indexOf(10));
                }
                this.CM(s2);
                this.CM(String.valueOf(VK("6HT=6ET@BPx")) + this.AM + VK("6HB"));
                this.ML = ((n10 > 0L) ? (n8 * 1000L / n10) : 0L);
                this.CM(String.valueOf(VK("qf^giftqpq6")) + WM(n8) + VK("6S<APB6LG6") + n10 + VK("6HB6.") + WM(this.ML * 8L) + VK("6SEB-6b[x") + n + VK("6o%x") + n4 + VK("6o$x") + n5 + VK("6p]x") + n11);
            }
            if (this.OL()) {
                final int n14 = (n10 > 1000L) ? 5 : 3;
                long zk = 999999999L;
                long el = 0L;
                for (int j = 0; j < n14; ++j) {
                    final long n15 = n10 * j / n14;
                    final long n16 = n10 * (j + 1) / n14;
                    final long n17 = this.OP(np, np2, n15, n16, mp);
                    final long n18 = n16 - n15;
                    final long n19 = (n18 > 0L) ? (n17 * 8L * 1000L / n18) : 0L;
                    zk = ZK(n17, zk);
                    el = EL(n17, el);
                    this.CM(String.valueOf(VK("6Z")) + n15 + VK("*") + n16 + VK("-6x6") + WM(n17) + VK("6S<APB") + ((n19 > 0L) ? (String.valueOf(VK("6.")) + WM(n19) + " bps)") : ""));
                }
                if (el > 0L) {
                    this.YM = (int)(zk * 100L / el);
                    this.CM(String.valueOf(VK("dfbx")) + this.YM + VK("16.") + zk + VK("'") + el + VK("-"));
                }
            }
            this.NL();
        }
        catch (Throwable t) {
            this.QN(s, t);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception ex) {}
            this.ON = 100;
        }
        try {
            inputStream.close();
        }
        catch (Exception ex2) {}
        this.ON = 100;
        IL(3000L);
    }
    
    private String YK(final String s) {
        if (s == null || this.PP == null) {
            return null;
        }
        long n = 0L;
        final int length = this.PP.length();
        for (char c = '\0'; c < length; ++c) {
            n ^= (this.PP.charAt(c) + c) * 1013359411854961L;
        }
        if ((n | 0x1L) * this.QP == 7238801289283465829L) {
            final int index = this.PP.indexOf(String.valueOf(s) + '=');
            if (index >= 0) {
                final int n2 = index + (s.length() + 1);
                final int index2 = this.PP.indexOf(10, n2);
                if (index2 > n2) {
                    return this.PP.substring(n2, index2);
                }
            }
            return null;
        }
        return "-";
    }
    
    private String WQ(final long n, final long n2) {
        final long n3 = (n + n2 - 1L) / n2;
        if (n3 >= 3600L) {
            final long n4 = (n3 + 30L) / 60L;
            return n4 / 60L + VK("(") + n4 * 10L / 60L % 10L + VK("6") + this.HL(VK("MF@CB"));
        }
        if (n3 >= 60L) {
            return (n3 + 40L) / 60L + VK("6") + this.HL(VK("HLG@APB"));
        }
        return (n3 > 1L) ? (n3 + VK("6") + this.HL(VK("BPRFGQB"))) : this.HL(VK("B@SBPRFGQ"));
    }
    
    private boolean OO(final int n, final int n2) {
        return this.RP.inside(n, n2);
    }
    
    private boolean MO(final int n, final int n2) {
        return this.SP.inside(n, n2);
    }
    
    private boolean NO(final int n, final int n2) {
        return this.TP.inside(n, n2);
    }
    
    private int BM(final String s, final int n) {
        try {
            return Integer.parseInt(this.OM(s));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    private long WP(final String s, final long n) {
        try {
            return Long.parseLong(this.OM(s));
        }
        catch (Throwable t) {
            return n;
        }
    }
    
    private String KM(final String s) {
        return this.XK() ? this.OM(s) : null;
    }
    
    private String OM(final String s) {
        final String parameter = this.getParameter(s);
        return (parameter != null) ? parameter : this.VP.getProperty(s);
    }
    
    public void init() {
        this.setBackground(new Color(15790320));
        this.setForeground(new Color(0));
        this.setLayout(null);
    }
    
    private void CL() {
        try {
            final String om = this.OM(VK("SEB$R"));
            if (om != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(om, VK("*"));
                final int countTokens = stringTokenizer.countTokens();
                final long[] ak = new long[countTokens * 2];
                for (int i = 0; i < countTokens; ++i) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index = nextToken.indexOf(61);
                    final long long1 = Long.parseLong(nextToken.substring(0, index));
                    final int int1 = Integer.parseInt(nextToken.substring(index + 1), 16);
                    ak[i * 2 + 0] = long1;
                    ak[i * 2 + 1] = int1;
                }
                this.AK = ak;
            }
        }
        catch (Exception ex) {}
    }
    
    private void BL() {
        final long wp = this.WP(VK("IFSEB"), -1L);
        if (wp > 0L) {
            this.CK = wp;
        }
        final long wp2 = this.WP(VK("MLSEB"), -1L);
        if (wp2 > this.CK * 2L) {
            this.DL = wp2;
        }
    }
    
    private void YL() {
        this.AN.setEditable(false);
        this.AN.setFont(new Font(VK("rF@CLPC"), 0, 12));
        this.AN.setBackground(new Color(16777215));
        this.AN.setForeground(new Color(0));
        this.XP.setTitle(String.valueOf(this.NM(true)) + VK("6lGOFCHTALFG"));
        this.XP.add(VK("rPGAPC"), this.AN);
        this.XP.move(0, 0);
        this.XP.pack();
        this.XP.addWindowListener(this);
        this.CM(String.valueOf(this.NM(true)) + VK("6)6MAAE{''>>>(H<BEPPQ(RFH6)6") + this.WK[1]);
        this.CM(String.valueOf(VK("KT?Tx")) + this.VN(true));
        this.CM(String.valueOf(VK("RILPGAx")) + this.YP(true));
    }
    
    private boolean VQ() {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer("1.5", ".");
            final StringTokenizer stringTokenizer2 = new StringTokenizer(System.getProperty("java.version"), ".");
            while (stringTokenizer.hasMoreTokens()) {
                final char char1 = stringTokenizer.nextToken().charAt(0);
                final char c = stringTokenizer2.hasMoreTokens() ? stringTokenizer2.nextToken().charAt(0) : '0';
                if (char1 > c) {
                    return true;
                }
                if (char1 < c) {
                    break;
                }
            }
        }
        catch (Throwable t) {}
        return false;
    }
    
    private boolean XK() {
        final String yk = this.YK(VK("E"));
        return yk == null || "Y".equals(yk);
    }
    
    private static String FQ(final URLConnection urlConnection) {
        final String[] array = { VK("_LT"), VK("tNP"), VK("eCF=<)rFGGPRALFG"), VK("])rTRMP"), VK("gPAtGAB") };
        Object string = null;
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final String headerField = urlConnection.getHeaderField(s);
            if (headerField != null) {
                string = String.valueOf((string != null) ? new StringBuffer(String.valueOf(string)).append(VK("*6")).toString() : "") + s + VK("x") + headerField;
            }
        }
        return (String)string;
    }
    
    private boolean OL() {
        return this.isActive();
    }
    
    private boolean UO(final String s) {
        return this.HL(VK("C@GAPBAB")).indexOf(s) >= 0;
    }
    
    private String VN(final boolean b) {
        final String zp = this.ZP(VK("KT?T(?PGQFC"), "");
        final String lowerCase = zp.toLowerCase();
        return String.valueOf(b ? zp : ((lowerCase.indexOf(VK("HLRCFBFOA")) >= 0) ? VK("hb") : ((lowerCase.indexOf(VK("B@G")) >= 0) ? VK("b@G") : ""))) + VK("6kT?T6") + this.ZP(VK("KT?T(?PCBLFG"), VK("v"));
    }
    
    private void SM(final String s) {
        this.CM(String.valueOf(VK("tBJLGN6kT?TbRCLEA6AF6P?TI6/")) + s + VK("/"));
        try {
            final Class ap = FK.AP(VK("kb"));
            final String vk = VK("KB");
            final Class[] array = new Class[2];
            final int n = 0;
            Class bp;
            if ((bp = myspeed.BP) == null) {
                try {
                    bp = (myspeed.BP = FK.AP("java.applet.Applet"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n] = bp;
            final int n2 = 1;
            Class cp;
            if ((cp = myspeed.CP) == null) {
                try {
                    cp = (myspeed.CP = FK.AP("java.lang.String"));
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            array[n2] = cp;
            ap.getMethod(vk, (Class[])array).invoke(null, this, s);
        }
        catch (InvocationTargetException ex3) {
            final Throwable targetException = ex3.getTargetException();
            if ((" " + targetException).toLowerCase().indexOf(VK("HT<BRCLEA")) > 0) {
                this.TL(VK("pccfc{6kT?TbRCLEA6OTLI@CP6SPRT@BP6/ht\\brclea/6OITN6>LAMLG6AMP6yTEEIPAw6ATN6LB6HLBBLGN"));
            }
            else {
                this.TL(" " + targetException);
                targetException.printStackTrace();
            }
        }
        catch (Throwable t) {
            this.TL(" " + t);
            t.printStackTrace();
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.target == this) {
            if (n == 105) {
                this.XP.show();
            }
            else if (n == 104) {
                this.UL = !this.UL;
            }
            else if (n == 120 && this.TN == 'x') {
                try {
                    throw new RuntimeException(VK("APBALGN"));
                }
                catch (Throwable t) {
                    this.QN(VK("aPBALGN"), t);
                }
            }
            this.TN = (char)((n == this.TN) ? 0 : n);
            this.NL();
            return true;
        }
        return super.keyDown(event, n);
    }
    
    private void ZL() {
        BufferedReader bufferedReader = null;
        final String pm = this.PM();
        try {
            final URL mm = this.MM();
            final URLConnection pn = JK.PN(new URL(mm, String.valueOf(pm) + ((mm.getHost().length() > 0) ? (String.valueOf(VK("vAx")) + JK.NN()) : "")).openConnection());
            bufferedReader = new BufferedReader(new CharArrayReader(this.DQ(pn.getInputStream())));
            String s = "";
            String s2;
            while ((s2 = bufferedReader.readLine()) != null) {
                if (s2.startsWith(VK("3"))) {
                    continue;
                }
                if (s2.startsWith(VK("6+"))) {
                    s2 = String.valueOf(s) + "\n" + s2.substring(2);
                }
                else if (s2.startsWith(VK("+"))) {
                    s2 = String.valueOf(s) + "\n" + s2.substring(1);
                }
                s = s2;
                final int index = s2.indexOf(61);
                if (index <= 0) {
                    continue;
                }
                final String substring = s2.substring(0, index);
                final String substring2 = s2.substring(index + 1);
                if (substring.equals(VK("ITSPI"))) {
                    this.AO.addElement(substring2);
                }
                else {
                    ((Hashtable<String, String>)this.VP).put(substring, substring2);
                }
            }
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 50; ++i) {
                final String headerField = pn.getHeaderField(i);
                if (headerField != null) {
                    final String headerFieldKey = pn.getHeaderFieldKey(i);
                    if (sb.length() > 0) {
                        sb.append(VK("6"));
                    }
                    sb.append(headerFieldKey).append(VK("x")).append(headerField);
                }
            }
            this.EQ = sb.toString();
            final String fq = FQ(pn);
            this.JP = (fq != null);
            this.RL = pn.getHeaderField(VK("bPC?PC"));
            this.HQ = GQ(pn);
            String s3 = this.MM().toString();
            final int index2 = s3.indexOf(63);
            if (index2 > 0) {
                s3 = s3.substring(0, index2);
            }
            this.CM(String.valueOf(VK("@CIx")) + s3);
            this.CM(String.valueOf(VK("BPC?PCx")) + this.RL + ((fq != null) ? VK("6Zecf]\\'rtrmp6qpaprapqX") : VK("")));
            if (fq != null) {
                this.CM(String.valueOf(VK("^tcglgn{6")) + fq);
            }
        }
        catch (Throwable t) {
            this.QN(pm, t);
        }
        finally {
            try {
                bufferedReader.close();
            }
            catch (Exception ex) {}
        }
        try {
            bufferedReader.close();
        }
        catch (Exception ex2) {}
    }
    
    private void AL() {
        final String km = this.KM(VK("HBB"));
        if (km != null) {
            BufferedReader bufferedReader = null;
            try {
                final URL mm = this.MM();
                if (mm.getHost().length() > 0) {}
                bufferedReader = new BufferedReader(new InputStreamReader(JK.PN(new URL(mm, String.valueOf(km) + VK("HBBvAx") + JK.NN()).openConnection()).getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final int index = line.indexOf(61);
                    if (index > 0) {
                        ((Hashtable<String, String>)this.VP).put(line.substring(0, index), line.substring(index + 1));
                    }
                }
            }
            catch (Exception ex) {
                this.TL("loadMSS: " + ex);
            }
            finally {
                try {
                    bufferedReader.close();
                }
                catch (Exception ex2) {}
            }
            try {
                bufferedReader.close();
            }
            catch (Exception ex3) {}
        }
    }
    
    private static double FL(final double n) {
        return Math.log(n);
    }
    
    private void QN(final String s, final Throwable t) {
        if (!(t instanceof ThreadDeath)) {
            ++this.WN;
            final String string = String.valueOf(s) + VK("{6") + t;
            this.TL(String.valueOf(VK(",,,6p]rpealfg{6")) + string + VK("6,,,"));
            t.printStackTrace();
            this.IQ(string);
            if (this.WN == 1) {
                final String eq = this.EQ;
                if (eq != null) {
                    this.YN(String.valueOf(VK("vCMPTQPCBx")) + URLEncoder.encode(eq));
                }
            }
        }
    }
    
    private static String ER(final int n) {
        final char[] array = new char[n];
        for (int i = 0; i < array.length; ++i) {
            array[i] = myspeed.LK[JQ(myspeed.LK.length)];
        }
        return new String(array);
    }
    
    private static String DP(final String s) {
        return s.equals(VK(")")) ? null : s;
    }
    
    private String XQ(final String s, final int n) {
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "<>=", true);
                    final String nextToken = stringTokenizer2.nextToken();
                    int n2 = 0;
                    boolean b = false;
                    if (!stringTokenizer2.hasMoreTokens()) {
                        return nextToken;
                    }
                    while (stringTokenizer2.hasMoreTokens()) {
                        final String nextToken2 = stringTokenizer2.nextToken();
                        if ("<".equals(nextToken2)) {
                            n2 = -1;
                        }
                        else if (">".equals(nextToken2)) {
                            n2 = 1;
                        }
                        else if ("=".equals(nextToken2)) {
                            b = true;
                        }
                        else {
                            final int int1 = Integer.parseInt(nextToken2);
                            if ((n2 < 0 && n < int1) || (n2 > 0 && n > int1) || (b && n == int1)) {
                                return nextToken;
                            }
                            continue;
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
        return "";
    }
    
    private static int EL(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    private static long EL(final long n, final long n2) {
        return (n > n2) ? n : n2;
    }
    
    private static int ZK(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    private static long ZK(final long n, final long n2) {
        return (n < n2) ? n : n2;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.requestFocus();
        if (this.NO(n, n2)) {
            this.TO(VK("MAAE{''>>>(KT?T(RFH'QF>GIFTQ'"));
        }
        else if (this.OO(n, n2)) {
            if (this.KM(VK("HBB")) != null) {
                this.TO(String.valueOf(this.doGetReportURL()) + VK("vLQx") + this.TM);
            }
        }
        else if (this.MO(n, n2)) {
            this.KQ = false;
            this.GL = !this.GL;
            this.LQ = null;
            this.NL();
        }
        else {
            this.JL = new Point(n, n2);
        }
        return this.MQ(n, n2);
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.JL = ((this.JL != null) ? new Point(n, n2) : null);
        return this.MQ(n, n2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return this.MQ(n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.JL = null;
        this.NL();
        return this.MQ(n, n2);
    }
    
    private int HN() {
        return this.BM(VK("HBB(=OPC"), 10485760);
    }
    
    private String YP(final boolean b) {
        return String.valueOf(this.ZP(VK("FB(GTHP"), "")) + VK("6") + this.ZP(VK("FB(?PCBLFG"), "") + VK("6") + this.ZP(VK("FB(TCRM"), "");
    }
    
    public void CM(final String s) {
        this.AN.appendText(s.endsWith("\n") ? s : (String.valueOf(s) + "\n"));
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (NQ(this, graphics)) {
            this.OQ(graphics);
        }
    }
    
    private void OQ(final Graphics graphics) {
        this.PQ();
        this.QQ(graphics);
    }
    
    private void QQ(final Graphics graphics) {
        final Dimension size = this.size();
        final Rectangle tp = new Rectangle();
        if (this.GM(graphics)) {
            return;
        }
        if (this.HM(graphics)) {
            return;
        }
        Image lq = this.LQ;
        if (lq == null || size.width != lq.getWidth(null) || size.height != lq.getHeight(null)) {
            lq = this.createImage(size.width, size.height);
            this.LQ = lq;
            final Graphics graphics2 = lq.getGraphics();
            this.RQ(graphics2, size);
            graphics2.dispose();
        }
        graphics.drawImage(lq, 0, 0, null);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final String string = String.valueOf((this.KQ && this.QL != null && (JK.NN() / 750L & 0x1L) == 0x0L) ? new StringBuffer(String.valueOf(this.HL(VK("RILRJ")))).append(VK("6")).toString() : VK("")) + (this.GL ? this.HL(VK("JsEB")) : this.HL(VK("JSEB")));
        final int stringWidth = fontMetrics.stringWidth(string);
        graphics.setColor(Color.gray);
        final int n = size.width - 1 - 2 - stringWidth;
        final int n2 = size.height - 1 - 2;
        graphics.drawString(string, n, n2);
        this.SP = new Rectangle(n, n2 - fontMetrics.getAscent(), stringWidth, fontMetrics.getHeight());
        int lm = this.LM;
        final int height = fontMetrics.getHeight();
        final int n3 = height + fontMetrics.getDescent();
        final int n4 = 5;
        if (this.QO != null || this.QL != null) {
            final long ml = this.ML;
            String hl = (ml > 0L) ? this.SQ(ml * 8L) : ((this.PL > 1) ? this.HL(VK("vvv")) : this.HL(VK(">FCJLGN")));
            if (this.QL != null) {
                hl = ((ml > 0L) ? this.HL(VK("LBQF>GIFTQBEPPQ"), new String[] { hl }) : "");
            }
            else if (this.PL > 1) {
                hl = this.HL(VK("QF>GIFTQBEPPQ"), new String[] { hl });
            }
            final int stringWidth2 = fontMetrics.stringWidth(hl);
            if (hl.length() > 0) {
                graphics.setColor(Color.white);
                graphics.fillRect(n4, lm + n4, stringWidth2 + 2 + 2, n3);
                graphics.setColor(Color.black);
                graphics.drawRect(n4, lm + n4, stringWidth2 + 2 + 2, n3);
                graphics.drawString(hl, n4 + 2, lm + n4 + height);
            }
            final int on = this.ON;
            if (this.PL == 1 && on >= 0 && on <= 100) {
                this.TQ(graphics, n4 + stringWidth2 + 3 * n4, lm + n4 + n3 / 2, on);
            }
            if (this.QL != null) {
                final long n5 = 8000L;
                final long nn = JK.NN();
                if (myspeed.UQ == 0L) {
                    myspeed.UQ = nn;
                }
                final long n6 = nn - myspeed.UQ;
                if (n6 < n5) {
                    final int n7 = n4;
                    final int n8 = lm + n4 + n3 + n4 + height;
                    final int n9 = (n6 < n5 / 2L) ? EL(128, 240 - (int)n6 / 8) : EL(128, 240 - (int)(n5 - n6) / 8);
                    graphics.setColor(new Color(n9, n9, n9));
                    graphics.drawString(this.NM(true), n7, n8);
                    final int n10 = n8 + height;
                    graphics.drawString(this.WK[1], n7, n10);
                    final int n11 = n10 + height;
                    final String vn = this.VN(false);
                    graphics.drawString(vn, n7, n11);
                    final String om = this.OM(VK("KT?T@EQTAP"));
                    if (om != null && this.VQ()) {
                        final int n12 = n7 + fontMetrics.stringWidth(vn) + fontMetrics.stringWidth("x");
                        graphics.drawString(om, n12, n11);
                        final int n13 = n12 + fontMetrics.stringWidth(om);
                        graphics.drawLine(n12, n11, n13, n11);
                        tp.reshape(n12, n11 - height + fontMetrics.getDescent(), n13, height);
                    }
                    final int n14 = n11 + height;
                    final String yk = this.YK(VK("S"));
                    if (yk != null) {
                        graphics.drawString(yk, n7, n14);
                    }
                }
            }
            if (ml > 0L) {
                graphics.setColor(Color.blue);
                final double yo = this.YO(ml * 8L);
                graphics.drawLine(n4 + stringWidth2 + 2 + 2, lm + n4 + n3, (int)(size.width * yo), size.height - (int)(size.height * yo));
                this.EP(graphics, 0, ml * 8L, null, null, null, Color.yellow);
            }
            if (ml > 0L) {
                this.XN(graphics, this.HL(VK("QF>GIFTQSF="), new String[] { this.SQ(ml * 8L), this.WQ(1000000L, ml), this.WQ(10000000L, ml), this.WQ(100000000L, ml), this.WQ(1000000000L, ml), this.WQ(650000000L, ml), this.WQ(4700000000L, ml), this.WQ(15000000000L, ml) }), size.width * 66 / 100, size.height * 80 / 100, Color.white);
            }
            lm += n3 + n4;
        }
        if ((this.QO != null || this.QL != null) && this.PL >= 2 && this.UO(VK("@EIFTQ"))) {
            final long xm = this.XM;
            String hl2 = (xm > 0L) ? this.SQ(xm * 8L) : ((this.PL > 2) ? this.HL(VK("vvv")) : this.HL(VK(">FCJLGN")));
            if (this.PL > 2) {
                hl2 = this.HL(VK("@EIFTQBEPPQ"), new String[] { hl2 });
            }
            final int stringWidth3 = fontMetrics.stringWidth(hl2);
            graphics.setColor(Color.white);
            graphics.fillRect(n4, lm + n4, stringWidth3 + 2 + 2, n3);
            graphics.setColor(Color.black);
            graphics.drawRect(n4, lm + n4, stringWidth3 + 2 + 2, n3);
            graphics.drawString(hl2, n4 + 2, lm + fontMetrics.getHeight() + n4);
            final int on2 = this.ON;
            if (this.PL == 2 && on2 >= 0 && on2 <= 100) {
                this.TQ(graphics, n4 + stringWidth3 + 3 * n4, lm + n4 + n3 / 2, on2);
            }
            if (xm > 0L) {
                graphics.setColor(Color.blue);
                final double yo2 = this.YO(xm * 8L);
                graphics.drawLine(n4 + stringWidth3 + 2 + 2, lm + n4 + n3, (int)(size.width * yo2), size.height - (int)(size.height * yo2));
                this.EP(graphics, 0, xm * 8L, null, null, null, Color.yellow);
            }
            lm += n3 + n4;
        }
        if (this.PL >= 4) {
            int n15 = lm + height;
            graphics.setColor(new Color(128, 128, 128));
            final StringTokenizer stringTokenizer = new StringTokenizer(this.HL(VK("BMF>P=ACT")), VK("*"));
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals(VK("@ES@CBA"))) {
                    final long sn = this.SN;
                    if (sn <= 0L) {
                        continue;
                    }
                    n15 += height;
                    graphics.drawString(this.HL(nextToken, new String[] { this.SQ(sn * 8L) }), n4, n15);
                }
                else if (nextToken.equals(VK("DFB"))) {
                    final int ym = this.YM;
                    if (ym <= 0) {
                        continue;
                    }
                    n15 += height;
                    graphics.drawString(this.HL(nextToken, new String[] { "" + ym, this.XQ(this.OM(String.valueOf(nextToken) + VK("(ITSPIB")), ym) }), n4, n15);
                }
                else if (nextToken.equals(VK("CAA"))) {
                    final int zm = this.ZM;
                    if (zm <= 0) {
                        continue;
                    }
                    n15 += height;
                    graphics.drawString(this.HL(nextToken, new String[] { "" + zm, this.XQ(this.OM(String.valueOf(nextToken) + VK("(ITSPIB")), zm) }), n4, n15);
                }
                else if (nextToken.equals(VK("HT=ET@BP"))) {
                    final int am = this.AM;
                    if (am <= 0) {
                        continue;
                    }
                    n15 += height;
                    graphics.drawString(this.HL(nextToken, new String[] { "" + am, this.XQ(this.OM(String.valueOf(nextToken) + VK("(ITSPIB")), am) }), n4, n15);
                }
                else {
                    if (!nextToken.equals(VK("HBBLQ"))) {
                        continue;
                    }
                    final String tm = this.TM;
                    if (tm == null) {
                        continue;
                    }
                    n15 += height;
                    final String hl3 = this.HL(nextToken, new String[] { tm });
                    graphics.drawString(hl3, n4, n15);
                    this.RP = new Rectangle(n4, n15 - height, fontMetrics.stringWidth(hl3), height);
                }
            }
            final int n16 = n15 + height / 2;
            if (!this.SK.isShowing() || this.CN) {
                this.CN = false;
                final String km = this.KM(VK("B@SHLA(AP=A"));
                final String km2 = this.KM(VK("B@SHLA(@CI"));
                if (km != null && km2 != null) {
                    this.SK.setLabel(km);
                    this.add(this.SK);
                    this.SK.resize(this.SK.preferredSize());
                    this.SK.move(3, n16);
                    this.SK.requestFocus();
                }
            }
        }
        this.YQ(graphics);
        this.TP = tp;
    }
    
    private static int BO(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    private static long CO(final String s) {
        try {
            return Long.parseLong(s);
        }
        catch (Exception ex) {
            return 0L;
        }
    }
    
    private static double IR(final double n, final double n2) {
        return Math.pow(n, n2);
    }
    
    private static int JQ(final int n) {
        return (int)(Math.random() * n) % n;
    }
    
    private String HL(final String s) {
        final String om = this.OM(s);
        return (om != null) ? om : (String.valueOf(VK("v")) + s + VK("v"));
    }
    
    private String HL(final String s, final String[] array) {
        String s2 = this.HL(s);
        for (int i = 0; i < array.length; ++i) {
            s2 = this.ZQ(s2, String.valueOf(VK("1")) + i, array[i]);
        }
        return s2;
    }
    
    private static int[] NP(final int[] array, final int n) {
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, ZK(array.length, n));
        return array2;
    }
    
    private static long[] NP(final long[] array, final int n) {
        final long[] array2 = new long[n];
        System.arraycopy(array, 0, array2, 0, ZK(array.length, n));
        return array2;
    }
    
    public void run() {
        try {
            final Thread currentThread = Thread.currentThread();
            if (currentThread == this.QO) {
                this.AQ();
            }
            else if (currentThread == this.QL) {
                this.BQ();
            }
        }
        catch (Throwable t) {
            this.QN(VK("aMCPTQ"), t);
        }
    }
    
    private static String QR(final URLConnection urlConnection) {
        return urlConnection.getHeaderField(VK("qTAP"));
    }
    
    private static void IL(final long n) {
        try {
            if (n > 0L) {
                Thread.sleep(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public void stop() {
        this.XP.hide();
    }
    
    private char[] DQ(final InputStream inputStream) {
        final StringBuffer sb = new StringBuffer();
        final boolean b = ((inputStream.read() & 0xFF) | (inputStream.read() & 0xFF) << 8) == 0xFEFF;
        int read;
        int read2;
        while ((read = inputStream.read()) != -1 && (read2 = inputStream.read()) != -1) {
            sb.append(b ? ((char)((read & 0xFF) | (read2 & 0xFF) << 8)) : ((char)((read2 & 0xFF) | (read & 0xFF) << 8)));
        }
        return sb.toString().toCharArray();
    }
    
    private String ZQ(String string, final String s, final String s2) {
        int index;
        while ((index = string.indexOf(s)) >= 0) {
            string = String.valueOf(string.substring(0, index)) + s2 + string.substring(index + s.length());
        }
        return string;
    }
    
    private int OP(final long[] array, final int[] array2, final long n, final long n2, final long n3) {
        int n4 = 0;
        for (int i = 0; i < array.length; ++i) {
            final long n5 = (i > 0) ? array[i - 1] : 0L;
            final long zk = ZK(n3, EL(n5 + this.FO, array[i]));
            final long n6 = ZK(n2, zk) - EL(n, n5);
            if (n6 > 0L) {
                n4 += (int)(array2[i] * n6 / (zk - n5));
            }
        }
        return n4;
    }
    
    private static String VK(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            if (c >= ' ' && c <= '~') {
                charArray[i] = (char)(32 + (7066439 - c) % 95);
            }
        }
        return new String(charArray);
    }
    
    private String EM(final Graphics graphics, final String[] array) {
        final String lowerCase = this.MM().getHost().toLowerCase();
        final String yk = this.YK(VK("B"));
        String vk = null;
        int int1 = 0;
        try {
            int1 = Integer.parseInt(this.YK(VK("P")));
        }
        catch (Exception ex) {}
        if ((int1 > 0 && JK.NN() / 86400000L >= int1) || (yk == null && this.HQ > 45)) {
            vk = array[4];
        }
        else if (lowerCase.length() == 0) {
            vk = VK("h<bEPPQ6H@BA6SP6MFBAPQ6FG6T6>PS6BPC?PC");
        }
        else if (yk == null) {
            vk = ((this.HQ == 0) ? null : array[3]);
        }
        else if (lowerCase.indexOf(yk.toLowerCase()) < 0 && (this.RL == null || this.RL.indexOf(yk) < 0)) {
            String hostAddress = null;
            try {
                hostAddress = InetAddress.getByName(lowerCase).getHostAddress();
            }
            catch (Exception ex2) {}
            if ((hostAddress == null || yk.indexOf(hostAddress) < 0) && (hostAddress == null || !hostAddress.startsWith(yk))) {
                vk = yk;
            }
        }
        if (vk != null && graphics != null) {
            this.XN(graphics, vk, (this.size().height + graphics.getFontMetrics().getHeight()) / 2, new Color(16777152));
        }
        return vk;
    }
    
    private int[] RN(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: ldc_w           "@EIFTQ(SLG"
        //     5: invokestatic    myspeed.VK:(Ljava/lang/String;)Ljava/lang/String;
        //     8: astore_3       
        //     9: iconst_0       
        //    10: iload_1        
        //    11: aload_0        
        //    12: invokespecial   myspeed.CQ:()I
        //    15: invokestatic    myspeed.ZK:(II)I
        //    18: invokestatic    myspeed.EL:(II)I
        //    21: istore_1       
        //    22: new             Ljava/lang/StringBuffer;
        //    25: dup            
        //    26: aload_3        
        //    27: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    30: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //    33: ldc_w           "vGx"
        //    36: invokestatic    myspeed.VK:(Ljava/lang/String;)Ljava/lang/String;
        //    39: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    42: iload_1        
        //    43: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //    46: ldc_w           "0Ax"
        //    49: invokestatic    myspeed.VK:(Ljava/lang/String;)Ljava/lang/String;
        //    52: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    55: invokestatic    JK.NN:()J
        //    58: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
        //    61: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    64: astore          5
        //    66: new             Ljava/net/URL;
        //    69: dup            
        //    70: aload_0        
        //    71: invokespecial   myspeed.MM:()Ljava/net/URL;
        //    74: aload           5
        //    76: invokespecial   java/net/URL.<init>:(Ljava/net/URL;Ljava/lang/String;)V
        //    79: astore          6
        //    81: aload           6
        //    83: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    86: invokestatic    JK.PN:(Ljava/net/URLConnection;)Ljava/net/URLConnection;
        //    89: astore          7
        //    91: aload           6
        //    93: invokevirtual   java/net/URL.getHost:()Ljava/lang/String;
        //    96: astore          8
        //    98: aload           6
        //   100: invokevirtual   java/net/URL.getPort:()I
        //   103: istore          9
        //   105: aload           7
        //   107: ldc_w           "mFBA"
        //   110: invokestatic    myspeed.VK:(Ljava/lang/String;)Ljava/lang/String;
        //   113: new             Ljava/lang/StringBuffer;
        //   116: dup            
        //   117: aload           8
        //   119: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   122: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   125: iload           9
        //   127: iconst_m1      
        //   128: if_icmpeq       159
        //   131: iload           9
        //   133: bipush          80
        //   135: if_icmpeq       159
        //   138: new             Ljava/lang/StringBuffer;
        //   141: dup            
        //   142: ldc_w           ":"
        //   145: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   148: iload           9
        //   150: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   153: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   156: goto            161
        //   159: ldc             ""
        //   161: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   164: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   167: invokevirtual   java/net/URLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   170: iload_1        
        //   171: ifle            273
        //   174: aload_0        
        //   175: iload_1        
        //   176: invokespecial   myspeed.DR:(I)I
        //   179: istore          8
        //   181: iconst_1       
        //   182: iload_1        
        //   183: iload           8
        //   185: idiv           
        //   186: iadd           
        //   187: istore          9
        //   189: iload_1        
        //   190: istore          10
        //   192: iload           9
        //   194: istore          11
        //   196: goto            268
        //   199: new             Ljava/lang/StringBuffer;
        //   202: dup            
        //   203: ldc_w           "])oLII)"
        //   206: invokestatic    myspeed.VK:(Ljava/lang/String;)Ljava/lang/String;
        //   209: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   212: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   215: iload           11
        //   217: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   220: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   223: astore          12
        //   225: iload           10
        //   227: iload           11
        //   229: idiv           
        //   230: istore          13
        //   232: iload           13
        //   234: aload           12
        //   236: invokevirtual   java/lang/String.length:()I
        //   239: isub           
        //   240: iconst_2       
        //   241: isub           
        //   242: iconst_2       
        //   243: isub           
        //   244: invokestatic    myspeed.ER:(I)Ljava/lang/String;
        //   247: astore          14
        //   249: aload           7
        //   251: aload           12
        //   253: aload           14
        //   255: invokevirtual   java/net/URLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   258: iload           10
        //   260: iload           13
        //   262: isub           
        //   263: istore          10
        //   265: iinc            11, -1
        //   268: iload           11
        //   270: ifgt            199
        //   273: invokestatic    JK.FR:()J
        //   276: lstore          8
        //   278: aload           7
        //   280: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   283: astore_2       
        //   284: aload_2        
        //   285: invokevirtual   java/io/InputStream.read:()I
        //   288: iconst_m1      
        //   289: if_icmpne       284
        //   292: invokestatic    JK.MP:()J
        //   295: lstore          10
        //   297: aload           7
        //   299: iconst_0       
        //   300: invokevirtual   java/net/URLConnection.getHeaderField:(I)Ljava/lang/String;
        //   303: astore          12
        //   305: aload           12
        //   307: ifnull          334
        //   310: aload           12
        //   312: ldc_w           "$&&"
        //   315: invokestatic    myspeed.VK:(Ljava/lang/String;)Ljava/lang/String;
        //   318: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //   321: ifge            334
        //   324: new             Ljava/lang/RuntimeException;
        //   327: dup            
        //   328: aload           12
        //   330: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   333: athrow         
        //   334: iconst_2       
        //   335: newarray        I
        //   337: dup            
        //   338: iconst_0       
        //   339: iload_1        
        //   340: iastore        
        //   341: dup            
        //   342: iconst_1       
        //   343: lload           10
        //   345: lload           8
        //   347: lsub           
        //   348: l2i            
        //   349: iastore        
        //   350: astore          15
        //   352: jsr             388
        //   355: aload           15
        //   357: areturn        
        //   358: astore          5
        //   360: aload_0        
        //   361: aload_3        
        //   362: aload           5
        //   364: invokespecial   myspeed.QN:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   367: aload_0        
        //   368: invokespecial   myspeed.HR:()V
        //   371: goto            382
        //   374: astore          5
        //   376: jsr             388
        //   379: aload           5
        //   381: athrow         
        //   382: jsr             388
        //   385: goto            400
        //   388: astore          4
        //   390: aload_2        
        //   391: invokevirtual   java/io/InputStream.close:()V
        //   394: goto            398
        //   397: pop            
        //   398: ret             4
        //   400: aconst_null    
        //   401: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      358    358    402    Ljava/lang/Throwable;
        //  9      371    374    382    Any
        //  390    394    397    398    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 199, Size: 199
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private Image HP(final Image image) {
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            if (image.getWidth(null) > 0 && image.getHeight(null) > 0) {
                return image;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        windowEvent.getWindow().hide();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    private long KL(int zk) {
        final int width = this.size().width;
        final double fl = FL(this.CK);
        final double fl2 = FL(this.DL);
        zk = ZK(width, EL(0, zk));
        return (long)IR(2.718281828459045, fl + (fl2 - fl) * zk / width);
    }
    
    private Color BK(final long n, final long n2, final long n3, final int n4, final int n5) {
        final double n6 = 1.0 * (n3 - n) / (n2 - n);
        final int n7 = n4 >> 16 & 0xFF;
        final int n8 = n4 >> 8 & 0xFF;
        final int n9 = n4 >> 0 & 0xFF;
        return new Color(n7 + (int)(n6 * ((n5 >> 16 & 0xFF) - n7)), n8 + (int)(n6 * ((n5 >> 8 & 0xFF) - n8)), n9 + (int)(n6 * ((n5 >> 0 & 0xFF) - n9)));
    }
    
    public myspeed() {
        this.WK = new String[] { VK("h<bEPPQ6.AH-6!($R"), VK(".R-6%||~)$&&!6_LB@TI>TCP"), VK(""), VK("`GILRPGBPQ6_PCBLFG"), VK("iLRPGBP6p=ELCPQ"), VK(","), VK("`GILRPGBPQ") };
        this.CK = 10000L;
        this.DL = 1000000000L;
        this.AK = new long[] { 10000L, 16744576L, 28000L, 16761024L, 128000L, 16777152L, 3000000L, 12648384L, 1000000000L, 65408L };
        this.PK = new Button();
        this.SK = new Button();
        this.QK = new TextField();
        this.VP = new Properties();
        this.AO = new Vector();
        this.EQ = null;
        this.HQ = -1;
        this.PL = -1;
        this.VM = new Date();
        this.YM = 0;
        this.SP = new Rectangle();
        this.TP = new Rectangle();
        this.RP = new Rectangle();
        this.KQ = true;
        this.XP = new Frame();
        this.AN = new TextArea(18, 80);
        this.PP = "s=internetfrog.com\np=N\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        this.QP = 5325308174875162217L;
    }
}
