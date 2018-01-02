import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.Event;
import java.awt.Font;
import java.awt.Label;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class KaablitzEv extends Applet implements Runnable
{
    static String bdhblfz;
    Dimension Bdhblfz;
    FontMetrics bDhblfz;
    Color BDhblfz;
    Color bdHblfz;
    Color BdHblfz;
    Color bDHblfz;
    Color BDHblfz;
    Color bdhBlfz;
    Color BdhBlfz;
    Color bDhBlfz;
    Color BDhBlfz;
    String bdHBlfz;
    boolean BdHBlfz;
    boolean bDHBlfz;
    boolean BDHBlfz;
    boolean bdhbLfz;
    boolean BdhbLfz;
    boolean bDhbLfz;
    int BDhbLfz;
    int bdHbLfz;
    char[][] BdHbLfz;
    char[][] bDHbLfz;
    int BDHbLfz;
    int bdhBLfz;
    int BdhBLfz;
    int bDhBLfz;
    int BDhBLfz;
    int bdHBLfz;
    int BdHBLfz;
    int bDHBLfz;
    int BDHBLfz;
    Vector bdhblFz;
    Vector BdhblFz;
    zzzpe[][] bDhblFz;
    zzzpe BDhblFz;
    zzzke bdHblFz;
    zzzkg BdHblFz;
    zzzgf bDHblFz;
    private Thread LTHTl;
    boolean BDHblFz;
    long bdhBlFz;
    long BdhBlFz;
    public static int zzzoc;
    public long zzzpc;
    public long zzzqc;
    public long zzzrc;
    AudioClip bDhBlFz;
    AudioClip BDhBlFz;
    AudioClip bdHBlFz;
    public static int zzzvc;
    String BdHBlFz;
    int bDHBlFz;
    boolean BDHBlFz;
    static boolean bdhbLFz;
    static String BdhbLFz;
    String bDhbLFz;
    String BDhbLFz;
    String bdHbLFz;
    String BdHbLFz;
    long bDHbLFz;
    boolean BDHbLFz;
    String bdhBLFz;
    String BdhBLFz;
    Label bDhBLFz;
    int BDhBLFz;
    int bdHBLFz;
    int BdHBLFz;
    int bDHBLFz;
    Font BDHBLFz;
    static boolean bdhblfZ;
    String BdhblfZ;
    int bDhblfZ;
    
    public KaablitzEv() {
        final boolean bdhBlfz = false;
        this.BDHblFz = false;
        this.zzzpc = 500000L;
        this.zzzqc = 5000L;
        this.zzzrc = 10000L;
        this.BdHBlFz = "KrossWordz!";
        this.BDHBlFz = false;
        this.BdHbLFz = "krosswords";
        this.BDHbLFz = false;
        this.bdhBLFz = "ht";
        this.BdhBLFz = "So";
        this.BdhblfZ = "";
        this.bDHBlfz = bdhBlfz;
    }
    
    private final boolean lthtl() {
        System.out.println("  Hey dic*wad. This is copyright, you know? Write your own program you id**t!".substring(0, 0));
        System.out.println("(C)-2000 http://www.wyka-warzecha.com");
        return this.getDocumentBase().toString().startsWith("http://www.wyka-warzecha.com");
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.bDHBlfz) {
            return true;
        }
        if (KaablitzEv.bdhbLFz) {
            KaablitzEv.bdhbLFz = false;
            this.zzzn();
        }
        if (!this.BDHBlFz) {
            this.zzzn();
            return true;
        }
        if (this.bdHblFz != null && this.BDHBlFz) {
            final char lowerCase = Character.toLowerCase((char)n);
            if (n == 127) {
                if (this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] != this.BdHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()]) {
                    this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] = '\0';
                }
            }
            else if ((lowerCase > 'Z' && lowerCase < '\u00ff') || lowerCase == ' ' || lowerCase == '-') {
                if (lowerCase != this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] && lowerCase == this.BdHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()]) {
                    this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] = lowerCase;
                    this.BdhBlFz += this.zzzqc;
                    if (this.BDHbLFz) {
                        this.bdHBlFz.play();
                    }
                    if (this.BDhblFz.zzzle().zzztd().equals(this.BdHblFz.zzzif())) {
                        ++this.bDHBlFz;
                        if (this.bDHBlFz == this.BDHbLfz) {
                            this.bDHblFz.zzzbf = this.BDhbLFz;
                            final String bdHbLFz = this.bdHbLFz;
                            this.BDHBlFz = false;
                            KaablitzEv.bdhbLFz = true;
                            KaablitzEv.BdhbLFz = bdHbLFz;
                            if (this.BDHbLFz) {
                                this.bDhBlFz.play();
                                if (null == null) {}
                            }
                        }
                    }
                }
                else if (this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] != this.BdHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()]) {
                    this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] = lowerCase;
                    this.BdhBlFz -= this.zzzrc;
                    if (this.BDHbLFz) {
                        this.BDhBlFz.play();
                    }
                }
                this.bdHblFz.zzzae(1);
                this.lTHTl(true);
                if (null != null) {}
            }
            else if (n == 8) {
                if (this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] != this.BdHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()]) {
                    this.bDHbLfz[this.BdHBLfz][this.bdHblFz.zzzwd()] = '\0';
                }
                this.bdHblFz.zzzae(-1);
                this.lTHTl(false);
            }
            else if (n == 1004) {
                if (this.BdHBLfz > 0) {
                    --this.BdHBLfz;
                    if ((this.BDhblFz = this.LtHTl()) != null) {
                        this.LThTl();
                    }
                }
                else {
                    this.BdHBLfz = this.BDHbLfz - 1;
                    if ((this.BDhblFz = this.LtHTl()) != null) {
                        this.LThTl();
                    }
                }
            }
            else if (n == 1005) {
                if (this.BdHBLfz < this.BDHbLfz - 1) {
                    ++this.BdHBLfz;
                    if ((this.BDhblFz = this.LtHTl()) != null) {
                        this.LThTl();
                    }
                }
                else {
                    this.BdHBLfz = 0;
                    if ((this.BDhblFz = this.LtHTl()) != null) {
                        this.LThTl();
                    }
                }
            }
            else if (n == 1006) {
                this.bdHblFz.zzzae(-1);
                this.lTHTl(false);
            }
            else if (n == 1007) {
                this.bdHblFz.zzzae(1);
                this.lTHTl(true);
            }
        }
        this.repaint();
        return true;
    }
    
    public char[][] zzzd() {
        final char[][] array = new char[this.BdHblFz.zzzag][this.BdHblFz.zzzbg];
        int i = 0;
        final char[][] array2 = array;
        while (i < this.BdHblFz.zzzag) {
            final boolean b = false;
            int j = 0;
            int n = b ? 1 : 0;
            if (null == null) {}
            while (j < this.BdHblFz.zzzbg) {
                if (this.BdHblFz.zzzfg[i][j] != '#') {
                    if (this.BdHblFz.zzzxf) {
                        array2[i][j] = this.BdHblFz.zzzfg[i][j];
                    }
                    n = j + 1;
                    break;
                }
                ++j;
            }
            if (this.BdHblFz.zzzyf) {
                for (int k = n; k < this.BdHblFz.zzzbg; ++k) {
                    if (this.BdHblFz.zzzfg[i][k] == '#') {
                        array2[i][k - 1] = this.BdHblFz.zzzfg[i][k - 1];
                        break;
                    }
                    if (k == this.BdHblFz.zzzbg - 1) {
                        array2[i][k] = this.BdHblFz.zzzfg[i][k];
                        break;
                    }
                }
            }
            ++i;
        }
        return array2;
    }
    
    public static zzzpe[][] zzzj(final char[][] array, final Vector vector, final int n) {
        final char[][] lthtl = Lthtl(array);
        final int length = lthtl.length;
        final int length2 = lthtl[0].length;
        final zzzpe[][] array2 = new zzzpe[length - 2][length2 - 2];
        final Enumeration<Object> elements = vector.elements();
        int i = 1;
        final Enumeration<Object> enumeration = elements;
        while (i < length - 1) {
            for (int j = 1; j < length2 - 1; ++j) {
                if (lthtl[i][j] != '#') {
                    zzzke zzzke = null;
                    if (lthtl[i][j - 1] == '#' && lthtl[i][j + 1] != '#') {
                        zzzke = new zzzke(lThtl(lthtl, length, length2, i, j), (j - 1) * n, (i - 1) * n, n);
                        zzzke.zzzde = i - 1;
                        zzzke.zzzzd(j - 1);
                    }
                    if (zzzke != null) {
                        String string = "";
                        if (enumeration.hasMoreElements()) {
                            string = enumeration.nextElement().toString();
                        }
                        array2[i - 1][j - 1] = new zzzpe(zzzke, string);
                    }
                }
            }
            ++i;
        }
        return array2;
    }
    
    private static char[][] Lthtl(final char[][] array) {
        final int n = array.length + 2;
        final int n2 = array[0].length + 2;
        final char[][] array2 = new char[n][n2];
        int i = 0;
        final char[][] array3 = array2;
        while (i < n) {
            for (int j = 0; j < n2; ++j) {
                array3[i][j] = '#';
            }
            ++i;
        }
        for (int k = 1; k < n - 1; ++k) {
            for (int l = 1; l < n2 - 1; ++l) {
                array3[k][l] = array[k - 1][l - 1];
            }
        }
        return array3;
    }
    
    private static String lThtl(final char[][] array, final int n, final int n2, final int n3, final int n4) {
        final StringBuffer sb = new StringBuffer();
        int n5 = n4;
        final StringBuffer sb2 = sb;
        while (n5 < n2 && array[n3][n5] != '#') {
            sb2.append(array[n3][n5]);
            ++n5;
        }
        return sb2.toString();
    }
    
    public static Color zzzv(final String s) {
        final int n = 0;
        int int1 = 0;
        int int2 = 0;
        int int3 = n;
        s.trim();
        final int index = s.indexOf("#");
        if (index != -1 && index + 1 != s.length()) {
            return new Color(Integer.parseInt(s.substring(index + 1), 16));
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() > 3) {
            return Color.black;
        }
        while (stringTokenizer.hasMoreTokens()) {
            int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
        }
        if (int3 > 255) {
            int3 = 255;
        }
        if (int1 > 255) {
            int1 = 255;
        }
        if (int2 > 255) {
            int2 = 255;
        }
        return new Color(int3, int1, int2);
    }
    
    public void run() {
        this.zzzi();
        try {
            this.bDHblFz.paint(this.getGraphics());
            this.BdHblFz.paint(this.getGraphics());
            this.LthTl(this.getGraphics());
        }
        catch (Exception ex) {}
        this.bDHBlfz = true;
        while (this.BDHblFz) {
            try {
                Thread.sleep(20L);
                if (null != null) {}
            }
            catch (InterruptedException ex2) {}
            this.showStatus(this.bdhBLFz + " has free applets!");
            this.repaint();
        }
    }
    
    public void stop() {
        if (this.LTHTl != null && this.LTHTl.isAlive()) {
            this.LTHTl.stop();
            final Thread lthTl = null;
            this.BDHblFz = false;
            this.LTHTl = lthTl;
        }
    }
    
    public void start() {
        if (this.LTHTl == null || !this.LTHTl.isAlive()) {}
    }
    
    public void init() {
        final Dimension size = this.size();
        this.bDHBlfz = false;
        this.Bdhblfz = size;
        this.bdhBLFz += "tp:";
        final String string = this.BdhBLFz + "ft";
        this.BDHBlFz = false;
        this.BDHbLFz = false;
        this.BdhBLFz = string;
        final String string2 = this.bdhBLFz + "//";
        this.BdhblfZ = "(";
        this.bdhBLFz = string2;
        this.BDhblfz = new Color(8, 0, 49);
        this.bdhBLFz += "www";
        final String string3 = this.BdhBLFz + "wa";
        this.bdHblfz = Color.white;
        this.BdHblfz = Color.black;
        this.BdhBLFz = string3;
        final Color bdHblfz = new Color(14737663);
        this.BDHblfz = Color.white;
        this.bdhBlfz = Color.red;
        this.bDHblfz = bdHblfz;
        final Color bdhBlfz = new Color(10066431);
        this.bDhBlfz = Color.pink;
        this.BdhBlfz = bdhBlfz;
        this.bdhBLFz += ".w";
        this.bdhBLFz += "yk";
        final String string4 = this.BdhBLFz + "re ";
        this.BdhBLfz = 20;
        this.BdhBLFz = string4;
        final String string5 = this.bdhBLFz + "a-";
        this.bDhBLfz = 10;
        this.BDhBLfz = 1;
        this.bdHBLfz = 12;
        this.bDhblfZ = 3;
        this.bdhBLFz = string5;
        this.BdhblfZ += "C";
        this.bdhBLFz += "wa";
        this.setBackground(Color.white);
        this.bdhBLFz += "rz";
        this.setFont(new Font("Arial", 1, 12));
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        this.LTHTl = null;
        this.bDhblfz = fontMetrics;
        this.bdhBLFz += "ec";
        this.BdhblfZ += ")";
        this.bdhBLFz += "ha.";
        this.BdhblfZ += "-";
        this.bdhBLFz += "co";
        this.bdhBLFz += "m";
        this.BdhblfZ += "2";
        this.LTHTl = new Thread(this);
        this.BdhblfZ += "0";
        this.lthtl();
        this.BdhblfZ += "0";
        this.BdhblfZ += "0";
        final String string6 = this.BdhblfZ + " ";
        this.BDHblFz = true;
        this.BdhblfZ = string6;
        this.LTHTl.start();
        this.bDhblfZ *= 2;
    }
    
    public int zzzi() {
        if (this.lTHtl() == -1) {
            return -1;
        }
        if (this.zzzk() == -1) {
            return -1;
        }
        if (!this.bdhBLFz.equals("http://www.wyka-warzecha.com")) {
            return -1;
        }
        this.bDHbLfz = new char[this.BDHbLfz][this.bdhBLfz];
        this.bDhblfZ *= 2;
        this.ltHtl();
        this.bDhblFz = zzzj(this.BdHbLfz, this.BdhblFz, this.BdhBLfz);
        try {
            Thread.sleep(0L);
        }
        catch (Exception ex) {}
        final Font font = new Font("Arial", this.BDhBLfz, this.bdHBLfz);
        this.BDHBLFz = new Font("Arial", 0, this.bDhblfZ - 1);
        final Dimension dimension = new Dimension(this.bdhBLfz * this.BdhBLfz + 1, this.BDHbLfz * this.BdhBLfz + 1);
        final Dimension zzzse = new Dimension(this.Bdhblfz.width - this.bDhBLfz * ((this.bDhblfZ + 1) / 6), this.BdhBLfz * 2 + this.bDhBLfz + 1);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final FontMetrics fontMetrics2 = this.getFontMetrics(this.BDHBLFz);
        KaablitzEv.zzzvc = this.bdHBLfz * (this.bDhblfZ + 1) / 3;
        this.setBackground(this.BDhblfz);
        this.setLayout(null);
        this.BdHblFz = new zzzkg();
        this.BdHblFz.zzzjf = this.BDhblfz;
        this.BdHblFz.zzzkf = this.bdHblfz;
        this.BdHblFz.zzzlf = this.BdHblfz;
        this.BdHblFz.zzzmf = this.bDHblfz;
        this.BdHblFz.zzznf = this.bdhBlfz;
        this.BdHblFz.zzzof = this.bDhBlfz;
        this.BdHblFz.zzzpf = this.BDHblfz;
        this.BdHblFz.zzzqf = this.BdhBlfz;
        this.BdHblFz.zzzgg = this.bDHbLfz;
        this.BdHblFz.zzzfg = this.BdHbLfz;
        this.BdHblFz.zzzag = this.BdHbLfz.length;
        this.BdHblFz.zzzbg = this.BdHbLfz[0].length;
        this.BdHblFz.zzzuf = this.Bdhblfz;
        this.BdHblFz.zzzzf = this.BdhBLfz;
        this.BdHblFz.zzzwf = this.BDHBlfz;
        this.BdHblFz.zzzeg = this.BDHBLfz * (this.bDhblfZ / 11) - (this.bDhblfZ + 1) / 12;
        this.BdHblFz.zzzvf = font;
        this.BdHblFz.zzztf = this.getFontMetrics(this.BdHblFz.zzzvf);
        this.BdHblFz.zzzdg = this.BdHblFz.zzzzf / 2 + (this.BdHblFz.zzztf.getAscent() - this.BdHblFz.zzztf.getDescent()) / 2;
        this.BdHblFz.zzzxf = this.bdhbLfz;
        this.BdHblFz.zzzyf = this.BdhbLfz;
        this.BdHblFz.zzzhg = this.zzzd();
        this.BdHblFz.reshape((this.Bdhblfz.width - dimension.width) / 2, KaablitzEv.zzzvc, dimension.width, dimension.height);
        (this.bDHblFz = new zzzgf()).reshape(this.bDhBLfz, dimension.height + KaablitzEv.zzzvc, zzzse.width, zzzse.height);
        this.bDHblFz.zzzbf = KaablitzEv.BdhbLFz;
        this.bDHblFz.zzzaf = this.bDhBLfz;
        this.bDHblFz.zzzse = zzzse;
        this.bDHblFz.zzzcf = this.bDhbLfz;
        this.bDHblFz.zzzve = this.BDhblfz;
        this.bDHblFz.zzzwe = this.bdHblfz;
        this.bDHblFz.zzzxe = this.BdHblfz;
        this.bDHblFz.zzzye = this.BDHblfz;
        this.bDHblFz.zzzue = font;
        this.bDHblFz.zzzte = this.getFontMetrics(font);
        this.bDHblFz.zzzze = (this.bDHblFz.zzzse.height - this.bDHblFz.zzzaf) / 2 / 2 + (this.bDHblFz.zzzte.getAscent() - this.bDHblFz.zzzte.getDescent()) / 2;
        final Graphics graphics = this.getGraphics();
        this.LthTl(graphics);
        this.setFont(font);
        final Label label = new Label(this.BdHBlFz);
        label.setForeground(this.BDhBlfz);
        label.setBackground(this.BDhblfz);
        label.reshape((this.size().width - fontMetrics.stringWidth(this.BdHBlFz)) / 2, 0, (int)(fontMetrics.stringWidth(this.BdHBlFz) * 1.5), (int)(this.bdHBLfz * 1.5));
        this.BDhBLFz = (this.size().width - fontMetrics2.stringWidth(this.BdhBLFz + this.BdhblfZ + this.bdhBLFz)) / 2;
        this.bdHBLFz = (int)(this.bdHBLfz * 1.5);
        this.BdHBLFz = fontMetrics2.stringWidth(this.BdhBLFz + this.BdhblfZ + this.bdhBLFz);
        this.bDHBLFz = this.bdHBLFz + 12;
        this.add(label);
        this.add(this.BdHblFz);
        this.add(this.bDHblFz);
        this.validate();
        this.LthTl(graphics);
        this.repaint();
        return 1;
    }
    
    private int LThtl(final Dimension dimension) {
        return (this.Bdhblfz.width - dimension.width) / 2;
    }
    
    private void ltHtl() {
        final char[][] bdHbLfz = new char[this.BDHbLfz][this.bdhBLfz];
        int i = 0;
        this.BdHbLfz = bdHbLfz;
        while (i < this.BDHbLfz) {
            final char[] charArray = this.bdhblFz.elementAt(i).toString().toCharArray();
            int j = 0;
            final char[] array = charArray;
            if (null == null) {}
            while (j < this.bdhBLfz) {
                this.BdHbLfz[i][j] = array[j];
                ++j;
            }
            ++i;
        }
    }
    
    private void LtHtl() {
        for (int i = 0; i < this.BDHbLfz; ++i) {
            for (int j = 0; j < this.bdhBLfz; ++j) {
                this.bDHbLfz[i][j] = ' ';
            }
        }
    }
    
    private int lTHtl() {
        if (this.getParameter("game") != null) {
            this.BdHbLFz = this.getParameter("game");
        }
        if (!this.getParameter("copyright").equals(this.bdhBLFz)) {
            return -1;
        }
        if (this.getParameter("sound") != null) {
            this.BDHbLFz = true;
            this.bDhBlFz = this.getAudioClip(this.getCodeBase(), "completed.au");
            this.bdHBlFz = this.getAudioClip(this.getCodeBase(), "correct.au");
            this.BDhBlFz = this.getAudioClip(this.getCodeBase(), "incorrect.au");
            this.bDhBlFz.play();
            this.bDhBlFz.stop();
            this.bdHBlFz.play();
            this.bdHBlFz.stop();
            this.BDhBlFz.play();
            this.BDhBlFz.stop();
        }
        this.bdHBlfz = "crossword_data.dat";
        final String parameter = this.getParameter("center_hints");
        if (parameter != null && parameter.equalsIgnoreCase("yes")) {
            this.bDhbLfz = true;
        }
        final boolean bdHbLfz = false;
        this.bdhBLfz = 0;
        this.BDHbLfz = (bdHbLfz ? 1 : 0);
        return 1;
    }
    
    public int zzzk() {
        final boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = b;
        this.bdhblFz = new Vector();
        final Vector bdhblFz = new Vector();
        final String s = "10|20|true|true|true|#012356|#123467|#234578|#345689|#45679A|#DF5050|#6789BC|#000000|#FFFFFF|bold|12|200000|5000|10000|Click on the crossword or press any key to start!|Congratulations! Click to play again.| |";
        this.BdhblFz = bdhblFz;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        this.BDHBLfz = Integer.parseInt(stringTokenizer.nextToken());
        this.BdhBLfz = Integer.parseInt(stringTokenizer.nextToken());
        if (stringTokenizer.nextToken().equalsIgnoreCase("true")) {
            this.BDHBlfz = true;
        }
        else {
            this.BDHBlfz = false;
        }
        if (stringTokenizer.nextToken().equalsIgnoreCase("true")) {
            this.bdhbLfz = true;
        }
        else {
            this.bdhbLfz = false;
        }
        if (stringTokenizer.nextToken().equalsIgnoreCase("true")) {
            this.BdhbLfz = true;
        }
        else {
            this.BdhbLfz = false;
        }
        this.BDhblfz = zzzv(stringTokenizer.nextToken());
        this.BDHblfz = zzzv(stringTokenizer.nextToken());
        this.bDHblfz = zzzv(stringTokenizer.nextToken());
        this.bDhBlfz = zzzv(stringTokenizer.nextToken());
        this.BdhBlfz = zzzv(stringTokenizer.nextToken());
        this.bdhBlfz = zzzv(stringTokenizer.nextToken());
        this.BdHblfz = zzzv(stringTokenizer.nextToken());
        this.bdHblfz = zzzv(stringTokenizer.nextToken());
        this.BDhBlfz = zzzv(stringTokenizer.nextToken());
        final String nextToken = stringTokenizer.nextToken();
        if (nextToken.equalsIgnoreCase("plain")) {
            this.BDhBLfz = 0;
        }
        else if (nextToken.equalsIgnoreCase("bold")) {
            this.BDhBLfz = 1;
        }
        else if (nextToken.equalsIgnoreCase("italic")) {
            this.BDhBLfz = 2;
            if (null == null) {}
        }
        else if (nextToken.equalsIgnoreCase("bolditalic")) {
            this.BDhBLfz = 3;
            if (null != null) {}
        }
        else {
            this.BDhBLfz = 0;
        }
        this.bdHBLfz = Integer.parseInt(stringTokenizer.nextToken());
        this.zzzpc = Integer.parseInt(stringTokenizer.nextToken());
        this.zzzqc = Integer.parseInt(stringTokenizer.nextToken());
        this.zzzrc = Integer.parseInt(stringTokenizer.nextToken());
        this.bDhbLFz = stringTokenizer.nextToken();
        this.BDhbLFz = stringTokenizer.nextToken();
        this.bdHbLFz = stringTokenizer.nextToken();
        if (!KaablitzEv.bdhblfZ) {
            final String bDhbLFz = this.bDhbLFz;
            KaablitzEv.bdhblfZ = true;
            KaablitzEv.BdhbLFz = bDhbLFz;
        }
        try {
            final InputStream openStream = new URL(this.getCodeBase(), this.bdHBlfz).openStream();
            final DataInputStream dataInputStream = new DataInputStream(openStream);
            if (null != null) {}
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                final String trim = line.trim();
                if (!trim.equals("") && !trim.startsWith("//")) {
                    if (this.LTHtl(trim, "puzzle")) {
                        final boolean b6 = true;
                        b2 = false;
                        b3 = false;
                        b4 = false;
                        b5 = b6;
                    }
                    else if (this.LTHtl(trim, "hints")) {
                        final boolean b7 = false;
                        b2 = true;
                        b3 = false;
                        b4 = false;
                        b5 = b7;
                    }
                    else if (this.LTHtl(trim, "title")) {
                        final boolean b8 = false;
                        b2 = false;
                        b3 = true;
                        b4 = false;
                        b5 = b8;
                        if (null == null) {
                            continue;
                        }
                        continue;
                    }
                    else if (this.LTHtl(trim, "column position")) {
                        final boolean b9 = false;
                        b2 = false;
                        b3 = false;
                        b4 = true;
                        b5 = b9;
                    }
                    else if (b5) {
                        this.bdhblFz.addElement(trim.toLowerCase());
                        ++this.BDHbLfz;
                        this.bdhBLfz = trim.toLowerCase().length();
                    }
                    else if (b2) {
                        this.BdhblFz.addElement(trim);
                    }
                    else if (b3) {
                        this.BdHBlFz = trim;
                    }
                    else {
                        if (!b4) {
                            continue;
                        }
                        this.BDHBLfz = Integer.parseInt(trim);
                    }
                }
            }
            dataInputStream.close();
            openStream.close();
            if (null != null) {}
        }
        catch (IOException ex) {
            this.zzzb("fatal error: " + this.bdHBlfz + "");
            return -1;
        }
        return 1;
    }
    
    private boolean LTHtl(String lowerCase, String lowerCase2) {
        lowerCase2 = lowerCase2.toLowerCase();
        lowerCase = lowerCase.toLowerCase();
        return lowerCase.indexOf("[") != -1 && lowerCase.indexOf("]") != -1 && lowerCase.indexOf(lowerCase2) != -1;
    }
    
    private int lthTl(final String s, final boolean b) {
        if (b) {
            return (this.Bdhblfz.width - this.bDhblfz.stringWidth(s)) / 2;
        }
        return this.Bdhblfz.height / 2 - 20;
    }
    
    private void LthTl(final Graphics graphics) {
        graphics.setColor(this.BDhblfz);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(this.bdHblfz);
    }
    
    private void lThTl(final Graphics graphics) {
        graphics.setColor(new Color(8, 0, 49));
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(Color.white);
        final String s = "Loading, please wait...";
        graphics.drawString(s, this.lthTl(s, true), this.lthTl(s, false));
        graphics.drawString(this.BdhBLFz + this.BdhblfZ + this.bdhBLFz, this.lthTl(this.BdhBLFz + this.BdhblfZ + this.bdhBLFz, true), this.lthTl(this.BdhBLFz + this.BdhblfZ + this.bdhBLFz, false) + 15);
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        if (this.bDHBlfz) {
            graphics.setColor(this.BdHblfz);
            graphics.setFont(this.BDHBLFz);
            graphics.drawString(this.BdhBLFz + this.BdhblfZ + this.bdhBLFz, this.BDhBLFz, this.bDHBLFz);
            graphics.drawLine(this.BDhBLFz, this.bDHBLFz + 2, this.BDhBLFz + this.BdHBLFz, this.bDHBLFz + 2);
            this.BdHblFz.repaint();
            if (this.BDHBlFz) {
                this.bDHbLFz = this.BdhBlFz - System.currentTimeMillis();
                this.bDHblFz.zzzef = "Score: " + this.bDHbLFz;
            }
            this.bDHblFz.repaint();
            if (this.BdHBlfz) {
                this.showStatus(KaablitzEv.bdhblfz);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.bDHBlfz) {
            this.lThTl(graphics);
            return;
        }
        this.update(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        if (n2 >= this.bdHBLFz && n2 <= this.bDHBLFz + 10) {
            try {
                this.getAppletContext().showDocument(new URL(this.bdhBLFz), "_blank");
            }
            catch (MalformedURLException ex) {
                if (null == null) {}
            }
        }
        if (!this.bDHBlfz) {
            return true;
        }
        if (KaablitzEv.bdhbLFz) {
            KaablitzEv.bdhbLFz = false;
            this.zzzn();
        }
        if (!this.BDHBlFz) {
            this.zzzn();
            return true;
        }
        n2 -= KaablitzEv.zzzvc;
        if (this.BdHblFz.inside(n, n2) && this.BDHBlFz) {
            final zzzpe bDhblFz = null;
            this.bdHblFz = null;
            this.BDhblFz = bDhblFz;
            this.BdHblFz.zzzig = null;
            this.bDHblFz.zzzbf = "";
            final int n3 = (n2 - 2) / this.BdhBLfz;
            final int n4 = (n - 2) / this.BdhBLfz;
            if ((this.BDhblFz = this.ltHTl(n, n2)) != null) {
                this.LThTl();
            }
            this.repaint();
        }
        return true;
    }
    
    private void LThTl() {
        this.bdHblFz = this.BDhblFz.zzzle();
        this.BdHBLfz = this.bdHblFz.zzzde;
        this.bDHBLfz = this.bdHblFz.zzzee;
        this.BdHblFz.zzzig = this.bdHblFz;
        final zzzke bdHblFz = this.bdHblFz;
        int zzzbe;
        if (this.bdhbLfz) {
            zzzbe = 1;
            while (null != null) {}
        }
        else {
            zzzbe = 0;
        }
        bdHblFz.zzzbe = zzzbe;
        this.bDHblFz.zzzbf = this.BDhblFz.zzzme();
    }
    
    private zzzpe ltHTl(final int n, final int n2) {
        final zzzpe zzzpe = null;
        int i = 0;
        zzzpe zzzpe2 = zzzpe;
    Label_0068:
        while (i < this.BDHbLfz) {
            for (int j = 0; j < this.bdhBLfz; ++j) {
                if ((zzzpe2 = this.bDhblFz[i][j]) != null && zzzpe2.zzzle().zzzvd(n, n2)) {
                    break Label_0068;
                }
            }
            ++i;
        }
        return zzzpe2;
    }
    
    private zzzpe LtHTl() {
        final zzzpe zzzpe = null;
        int i = 0;
        zzzpe zzzpe2 = zzzpe;
    Label_0064:
        while (i < this.BDHbLfz) {
            for (int j = 0; j < this.bdhBLfz; ++j) {
                if ((zzzpe2 = this.bDhblFz[i][j]) != null && zzzpe2.zzzle().zzzde == this.BdHBLfz) {
                    break Label_0064;
                }
            }
            ++i;
        }
        return zzzpe2;
    }
    
    public void zzzn() {
        final boolean bdhBlFz = false;
        this.BdHBLfz = 0;
        this.bDHBlFz = (bdhBlFz ? 1 : 0);
        this.LtHtl();
        final zzzpe ltHTl = this.LtHTl();
        this.BDhblFz = ltHTl;
        if (ltHTl != null) {
            this.LThTl();
        }
        final long bdhBlFz2 = System.currentTimeMillis() + this.zzzpc;
        this.BDHBlFz = true;
        this.BdhBlFz = bdhBlFz2;
    }
    
    private void lTHTl(final boolean b) {
        if (this.BdhbLfz && this.bdHblFz.zzzbe == this.bdHblFz.zzzud() - 1) {
            if (b) {
                final zzzke bdHblFz = this.bdHblFz;
                int zzzbe;
                if (this.bdhbLfz) {
                    zzzbe = 1;
                    while (null != null) {}
                }
                else {
                    zzzbe = 0;
                }
                bdHblFz.zzzbe = zzzbe;
            }
            else {
                this.bdHblFz.zzzbe = this.bdHblFz.zzzud() - 2;
            }
        }
        if (this.bdhbLfz && this.bdHblFz.zzzbe == 0) {
            if (b) {
                this.bdHblFz.zzzbe = 1;
                return;
            }
            final zzzke bdHblFz2 = this.bdHblFz;
            int zzzbe2;
            if (this.BdhbLfz) {
                zzzbe2 = this.bdHblFz.zzzud() - 2;
                while (null != null) {}
            }
            else {
                zzzbe2 = this.bdHblFz.zzzud() - 1;
            }
            bdHblFz2.zzzbe = zzzbe2;
        }
    }
    
    public void zzzb(final String s) {
        this.BdHBlfz = true;
        this.showStatus(KaablitzEv.bdhblfz);
        System.out.println(this.getClass().getName() + ": " + s);
    }
    
    static {
        final String bdhblfz = "";
        KaablitzEv.zzzvc = 30;
        KaablitzEv.BdhbLFz = "Click on the crossword or press any key to start!";
        KaablitzEv.bdhblfz = bdhblfz;
    }
}
