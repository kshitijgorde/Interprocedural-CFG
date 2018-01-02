import java.util.StringTokenizer;
import sun.audio.AudioPlayer;
import sun.audio.AudioData;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import sun.audio.AudioDataStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Speech extends Applet implements Runnable
{
    char[] bdhblf;
    char[] Bdhblf;
    char[] bDhblf;
    boolean BDhblf;
    URL bdHblf;
    URL BdHblf;
    boolean bDHblf;
    float[] BDHblf;
    private Speech l;
    int[][] bdhBlf;
    String[] BdhBlf;
    int[] bDhBlf;
    int BDhBlf;
    int bdHBlf;
    String BdHBlf;
    String bDHBlf;
    String BDHBlf;
    AudioDataStream[] bdhbLf;
    AudioDataStream[] BdhbLf;
    AudioDataStream[] bDhbLf;
    AudioDataStream[] BDhbLf;
    int bdHbLf;
    int BdHbLf;
    int bDHbLf;
    int BDHbLf;
    String[][] bdhBLf;
    String[] BdhBLf;
    String[] bDhBLf;
    static int BDhBLf;
    int[] bdHBLf;
    static int BdHBLf;
    static boolean bDHBLf;
    static boolean BDHBLf;
    static String bdhblF;
    static int BdhblF;
    boolean bDhblF;
    boolean BDhblF;
    Graphics bdHblF;
    Image BdHblF;
    int bDHblF;
    int BDHblF;
    int bdhBlF;
    Thread BdhBlF;
    int bDhBlF;
    int BDhBlF;
    Font bdHBlF;
    float[] BdHBlF;
    float[] bDHBlF;
    Color BDHBlF;
    static String bdhbLF;
    static String BdhbLF;
    String bDhbLF;
    int BDhbLF;
    String bdHbLF;
    
    public int zzzfc(final String s, final int n, final int n2) {
        final char c = ' ';
        String string = "";
        int i = n;
        char char1 = c;
        while (i <= n2) {
            char1 = s.charAt(i);
            if (this.zzzcc(char1)) {
                string += Character.toUpperCase(char1);
            }
            else if (char1 != '\'' && char1 != '´') {
                break;
            }
            ++i;
        }
        switch (char1) {
            case '+': {
                if (i == n) {
                    this.zzzec("PLUS ");
                    break;
                }
                break;
            }
            case '=': {
                if (i == n) {
                    this.zzzec("EQUAL ");
                    break;
                }
                break;
            }
            case '*': {
                if (i == n) {
                    this.zzzec("MULTIPLY ");
                    break;
                }
                break;
            }
            case '/': {
                if (i == n) {
                    this.zzzec("DIVIDE ");
                    break;
                }
                break;
            }
            case '.': {
                if (string.lastIndexOf("MRS") != -1) {
                    this.zzzec("MISSUS ");
                    if (null != null) {
                        break;
                    }
                    break;
                }
                else if (string.lastIndexOf("MR") != -1) {
                    this.zzzec("MISTER ");
                    if (null == null) {
                        break;
                    }
                    break;
                }
                else {
                    if (string.lastIndexOf("DR") != -1) {
                        this.zzzec("DOCTOR ");
                        break;
                    }
                    this.zzzec(string + " ");
                    break;
                }
                break;
            }
            default: {
                this.zzzec(string + " ");
                break;
            }
        }
        return i + 1;
    }
    
    public void zzzvb(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public final void zzzgc() {
        this.BdHBlF = new float[3];
        this.bDHBlF = new float[3];
        this.zzzhc(this.BdHBlF);
        this.zzzhc(this.bDHBlF);
    }
    
    public boolean zzzmb(final int n, final int n2, final int n3, final String s) {
        if (this.bdhBLf[n][this.bDHbLf].charAt(0) == '*') {
            return true;
        }
        if (this.bdhBLf[n][this.bDHbLf].charAt(0) == ' ' && n2 > n3) {
            return true;
        }
        if (n2 > n3) {
            return false;
        }
        final int n4 = this.bdhBLf[n][this.bDHbLf].length() - 1;
        int n5 = 0;
        int i = n2;
        final int n6 = n4;
        if (null == null) {}
        while (n5 <= n6 && i <= n3) {
            final char char1 = this.bdhBLf[n][this.bDHbLf].charAt(n5);
            final char char2 = s.charAt(i);
            if (this.zzzcc(char1)) {
                if (char1 != char2) {
                    return false;
                }
                ++i;
            }
            else {
                switch (char1) {
                    case '#': {
                        if (!this.zzzsb(char2)) {
                            return false;
                        }
                        ++i;
                        if (null == null) {}
                        while (i <= n3) {
                            if (!this.zzzsb(s.charAt(i))) {
                                break;
                            }
                            ++i;
                        }
                        break;
                    }
                    case ':': {
                        while (i <= n3) {
                            if (!this.zzztb(s.charAt(i))) {
                                if (null != null) {
                                    break;
                                }
                                break;
                            }
                            else {
                                ++i;
                            }
                        }
                        break;
                    }
                    case '^': {
                        if (!this.zzztb(char2)) {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '!': {
                        if (!this.zzzsb(char2)) {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '.': {
                        if (char2 != 'B' && char2 != 'D' && char2 != 'V' && char2 != 'G' && char2 != 'J' && char2 != 'L' && char2 != 'M' && char2 != 'N' && char2 != 'R' && char2 != 'W' && char2 != 'Z') {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '+': {
                        if (char2 != 'E' && char2 != 'I' && char2 != 'Y') {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '%': {
                        if (i + 2 <= n3 && (s.regionMatches(i, "ING", 0, 3) || s.regionMatches(i, "ELY", 0, 3))) {
                            i += 3;
                            break;
                        }
                        if (i + 1 <= n3 && (s.regionMatches(i, "ER", 0, 2) || s.regionMatches(i, "ES", 0, 2) || s.regionMatches(i, "ED", 0, 2))) {
                            i += 2;
                            break;
                        }
                        if (char2 == 'E') {
                            ++i;
                            break;
                        }
                        return false;
                    }
                    default: {
                        return false;
                    }
                }
            }
            ++n5;
        }
        return n5 > n6 || (i > n3 && this.bdhBLf[n][this.bDHbLf].charAt(n5) == ' ');
    }
    
    public void zzzhc(final float[] array) {
        array[1] = (array[0] = 0.0f);
        array[2] = 1.0f;
    }
    
    public final void zzzeb() {
        this.BdHblF = this.createImage(this.bDHblF, this.BDHblF);
        (this.bdHblF = this.BdHblF.getGraphics()).clipRect(0, 0, this.bDHblF, this.BDHblF);
        this.zzzgc();
    }
    
    public final void zzzkb() {
        if (this.BdHblF == null) {
            this.zzzeb();
        }
        this.bdHblF.setColor(this.BDHBlF);
        this.bdHblF.fillRect(0, 0, this.bDHblF, this.BDHblF);
    }
    
    public void zzzhb(final int n) {
        if (this.BdHblF == null) {
            this.zzzeb();
        }
        final int n2 = 100;
        final int n3 = 6;
        this.bdhBlF = n;
        final int n4 = 45;
        final int n5 = 28;
        final int n6 = n2;
        this.bdHblF.setColor(new Color(20 + (int)(80.0f * this.BdHBlF[0]), 20 + (int)(80.0f * this.BdHBlF[1]), 20 + (int)(80.0f * this.BdHBlF[2])));
        this.bdHblF.drawRect(n4 - 1, n5 - 1, n6 + 1, n3 + 1);
        this.bdHblF.setColor(new Color(15 + (int)(50.0f * this.BdHBlF[0]), 15 + (int)(50.0f * this.BdHBlF[1]), (int)(50.0f * this.BdHBlF[2]) + 15));
        this.bdHblF.fillRect(n4, n5, n6, n3);
        this.bdHblF.setColor(new Color(80 + (int)(80.0f * this.BdHBlF[0]), 80 + (int)(80.0f * this.BdHBlF[1]), 80 + (int)(80.0f * this.BdHBlF[2])));
        this.bdHblF.fillRect(n4, n5, n, 1);
        this.bdHblF.setColor(new Color(200 + (int)(55.0f * this.BdHBlF[0]), 200 + (int)(55.0f * this.BdHBlF[1]), 200 + (int)(55.0f * this.BdHBlF[2])));
        this.bdHblF.fillRect(n4, n5 + 1, n, 1);
        this.bdHblF.setColor(new Color(80 + (int)(175.0f * this.BdHBlF[0]), 80 + (int)(175.0f * this.BdHBlF[1]), 80 + (int)(175.0f * this.BdHBlF[2])));
        this.bdHblF.fillRect(n4, n5 + 2, n, 1);
        this.bdHblF.setColor(new Color(80 + (int)(90.0f * this.BdHBlF[0]), 80 + (int)(90.0f * this.BdHBlF[1]), 80 + (int)(90.0f * this.BdHBlF[2])));
        this.bdHblF.fillRect(n4, n5 + 3, n, 1);
        this.bdHblF.setColor(new Color(40 + (int)(160.0f * this.BdHBlF[0]), 40 + (int)(160.0f * this.BdHBlF[1]), 40 + (int)(169.0f * this.BdHBlF[2])));
        this.bdHblF.fillRect(n4, n5 + 4, n, 2);
        this.bdHblF.setFont(this.bdHBlF);
        this.bdHblF.setColor(new Color(45 + (int)(this.bDHBlF[0] * 25.0f), 45 + (int)(this.bDHBlF[1] * 25.0f), 45 + (int)(this.bDHBlF[2] * 25.0f)));
        this.bdHblF.setColor(new Color((int)(50.0f * this.BdHBlF[0]), (int)(50.0f * this.BdHBlF[1]), (int)(50.0f * this.BdHBlF[2])));
        this.bdHblF.drawString("Initializing Speech - " + this.bDhBlF + "%", n4 - 12, n5 - 5);
        this.bDhBlF = n;
        this.bdHblF.setColor(Color.white);
        this.bdHblF.drawString("Initializing Speech - " + n + "%", n4 - 12, n5 - 5);
    }
    
    public int zzzob(final String s, int zzzob, final int n, final boolean b) {
        final int n2 = 0;
        char char1 = ' ';
        int i = zzzob;
        int n3 = n2;
        if (null == null) {}
        while (i <= n) {
            char1 = s.charAt(i);
            if (!Character.isDigit(char1)) {
                break;
            }
            n3 = n3 * 10 + (char1 - '0');
            ++i;
        }
        this.zzzdc(n3);
        if (char1 == '.') {
            if (!b) {
                this.zzzec("POINT ");
            }
            else {
                if (n3 == 1) {
                    this.zzzec("DOLLAR ");
                }
                else {
                    this.zzzec("DOLLARS ");
                }
                if (i + 2 <= n) {
                    final char c = (char)((s.charAt(i + 1) - '0') * '\n' + (s.charAt(i + 2) - '0'));
                    if (c == '\0') {
                        return i + 3;
                    }
                    this.zzzec("AND ");
                    zzzob = this.zzzob(s, i + 1, n, false);
                    if (c == '\u0001') {
                        this.zzzec("CENT ");
                    }
                    else {
                        this.zzzec("CENTS ");
                    }
                    return zzzob;
                }
            }
        }
        if (b) {
            if (n3 == 1) {
                this.zzzec("DOLLAR ");
            }
            else {
                this.zzzec("DOLLARS ");
            }
        }
        return i + 1;
    }
    
    public final void zzzlb(final int n, final int n2, final int n3, final int n4, final String s, final boolean b) {
        if (this.BdHblF == null) {
            this.zzzeb();
        }
        final int n5 = n4 / 2;
        final int n6 = n + n5;
        final int n7 = n2 + n5;
        this.bdHblF.setFont(this.bdHBlF);
        final FontMetrics fontMetrics = this.bdHblF.getFontMetrics(this.bdHBlF);
        final int n8 = (n6 + n3 - fontMetrics.stringWidth(s)) / 2;
        final int n9 = (n7 + n4 - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        this.bdHblF.setColor(new Color(50 + (int)(this.bDHBlF[0] * 40.0f), 50 + (int)(this.bDHBlF[1] * 40.0f), 50 + (int)(this.bDHBlF[2] * 80.0f)));
        this.bdHblF.fillOval(n, n2, n4, n4);
        this.bdHblF.fillOval(n + n3 - n4, n2, n4, n4);
        this.bdHblF.fillRect(n6, n2, n3 - n4, n4);
        this.bdHblF.setColor(new Color((int)(50.0f * this.BdHBlF[0]), (int)(50.0f * this.BdHBlF[1]), (int)(50.0f * this.BdHBlF[2])));
        this.bdHblF.fillOval(n + 1, n2 + 1, n4 - 2, n4 - 2);
        this.bdHblF.fillOval(n + n3 - n4 + 1, n2 + 1, n4 - 2, n4 - 2);
        this.bdHblF.fillRect(n6 + 1, n2 + 1, n3 - n4 - 2, n4 - 2);
        if (b) {
            this.bdHblF.setColor(new Color(80 + (int)(this.bDHBlF[0] * 175.0f), 80 + (int)(this.bDHBlF[1] * 175.0f), 80 + (int)(this.bDHBlF[2] * 175.0f)));
            this.bdHblF.drawLine(n6 - 2, n9 + 1, n6 + n3 - n4, n9 + 1);
            this.bdHblF.setColor(new Color(80 + (int)(this.bDHBlF[0] * 90.0f), 80 + (int)(this.bDHBlF[0] * 90.0f), 80 + (int)(this.bDHBlF[0] * 90.0f)));
            this.bdHblF.drawLine(n6 - 2, n9 + 2, n6 + n3 - n4, n9 + 2);
            this.bdHblF.setColor(new Color(40 + (int)(this.bDHBlF[0] * 80.0f), 40 + (int)(this.bDHBlF[0] * 80.0f), 40 + (int)(this.bDHBlF[0] * 80.0f)));
            this.bdHblF.drawLine(n6 - 2, n9 + 3, n6 + n3 - n4, n9 + 3);
        }
        this.bdHblF.setColor(new Color(30 + (int)(this.bDHBlF[0] * 25.0f), 30 + (int)(this.bDHBlF[1] * 25.0f), 30 + (int)(this.bDHBlF[2] * 25.0f)));
        this.bdHblF.drawString(s, n8 + 1, n9 + 1);
        this.bdHblF.setColor(Color.white);
        this.bdHblF.drawString(s, n8, n9);
        this.bdHblF.setColor(Color.white);
    }
    
    public void zzzdc(long n) {
        if (n < 0L) {
            this.zzzec("OVERRUN    ");
            return;
        }
        if (n >= 1000000000L) {
            this.zzzdc(n / 1000000000L);
            this.zzzec("BILLION ");
            n %= 1000000000L;
            if (n == 0L) {
                return;
            }
            if (n < 100L) {
                this.zzzec("AND ");
            }
        }
        if (n >= 1000000L) {
            this.zzzdc(n / 1000000L);
            this.zzzec("MILLION ");
            n %= 1000000L;
            if (n == 0L) {
                return;
            }
            if (n < 100L) {
                this.zzzec("AND ");
            }
        }
        if ((n >= 1000L && n <= 1099L) || n >= 2000L) {
            this.zzzdc(n / 1000L);
            this.zzzec("THOUSAND ");
            n %= 1000L;
            if (n == 0L) {
                return;
            }
            if (n < 100L) {
                this.zzzec("AND ");
            }
        }
        if (n >= 100L) {
            this.zzzec(this.BdhBLf[(int)(n / 100L)]);
            this.zzzec("HUNDRED ");
            n %= 100L;
            if (n == 0L) {
                return;
            }
        }
        if (n >= 20L) {
            this.zzzec(this.bDhBLf[(int)((n - 20L) / 10L)]);
            n %= 10L;
            if (n == 0L) {
                return;
            }
        }
        this.zzzec(this.BdhBLf[(int)n]);
    }
    
    public void run() {
        while (this.BDhBlF != 3) {
            this.showStatus(Speech.bdhbLF);
            if (this.BDhBlF != 0 && this.BDhBlF == 2) {
                this.showStatus(Speech.bdhbLF);
                this.BDhBlF = 1;
                this.zzzkb();
                this.BDhBlF = 3;
                this.zzzlb(10, 10, 170, 30, "", false);
                for (int i = 0; i < 100; i += 25) {
                    this.zzzhb(i);
                    this.repaint();
                }
                this.zzzkb();
                this.zzzfb();
            }
        }
        while (true) {
            this.showStatus(Speech.bdhbLF);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.BdHblF, 0, 0, this);
    }
    
    public boolean zzzxb(final int n, final int n2, final String s) {
        if (this.bdhBLf[n][this.bdHbLf].charAt(0) == '*') {
            return true;
        }
        if (this.bdhBLf[n][this.bdHbLf].charAt(0) == ' ' && n2 == 0) {
            return true;
        }
        if (this.bdhBLf[n][this.bdHbLf].charAt(0) == '&' && n2 == 0) {
            boolean zzzxb = false;
            if (this.zzztb(s.charAt(1))) {
                zzzxb = this.zzzxb(n, n2 + 2, s);
            }
            return zzzxb;
        }
        int length = this.bdhBLf[n][this.bdHbLf].length();
        int i = n2 - 1;
        if (null != null) {}
        while (length > 0 && i >= 0) {
            final char char1 = this.bdhBLf[n][this.bdHbLf].charAt(length - 1);
            final char char2 = s.charAt(i);
            if (this.zzzcc(char1)) {
                if (char1 != char2) {
                    return false;
                }
                --i;
                if (null != null) {}
            }
            else {
                switch (char1) {
                    case '(': {
                        final boolean b = false;
                        int j = 1;
                        boolean b2 = b;
                        while (j < this.bdhBLf[n][this.bdHbLf].length()) {
                            if (char2 == this.bdhBLf[n][this.bdHbLf].charAt(j)) {
                                b2 = true;
                            }
                            ++j;
                        }
                        return b2;
                    }
                    case '#': {
                        if (!this.zzzsb(char2)) {
                            return false;
                        }
                        --i;
                        while (i >= 0) {
                            if (!this.zzzsb(s.charAt(i))) {
                                if (null != null) {
                                    break;
                                }
                                break;
                            }
                            else {
                                --i;
                            }
                        }
                        break;
                    }
                    case ':': {
                        while (i >= 0) {
                            if (!this.zzztb(s.charAt(i))) {
                                if (null != null) {
                                    break;
                                }
                                break;
                            }
                            else {
                                --i;
                            }
                        }
                        break;
                    }
                    case '^': {
                        if (!this.zzztb(char2)) {
                            return false;
                        }
                        --i;
                        if (null == null) {
                            break;
                        }
                        break;
                    }
                    case '!': {
                        if (!this.zzzsb(char2)) {
                            return false;
                        }
                        --i;
                        break;
                    }
                    case '.': {
                        if (char2 != 'B' && char2 != 'D' && char2 != 'V' && char2 != 'G' && char2 != 'J' && char2 != 'L' && char2 != 'M' && char2 != 'N' && char2 != 'R' && char2 != 'W' && char2 != 'Z') {
                            return false;
                        }
                        --i;
                        break;
                    }
                    case '+': {
                        if (char2 != 'E' && char2 != 'I' && char2 != 'Y') {
                            return false;
                        }
                        --i;
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
            --length;
        }
        return length == 0 || (i < 0 && this.bdhBLf[n][this.bdHbLf].charAt(0) == ' ');
    }
    
    public void init() {
        if (this.getParameter("license") != null) {
            this.bdHbLF = this.getParameter("license");
        }
        if (!this.zzzbc()) {
            return;
        }
        System.out.println("(C)-2000 http://www.wyka-warzecha.com");
        System.out.println(Speech.bdhbLF);
        if (!this.getParameter("copyright").equalsIgnoreCase(Speech.BdhbLF)) {
            return;
        }
        this.BDhblF = false;
        if (this.size().width < 184) {
            return;
        }
        this.BDHBlF = new Color(Integer.parseInt(this.getParameter("backgroundColor"), 16));
        this.bdHBlF = new Font("Arial", 0, 12);
        this.bDHblF = this.size().width;
        this.BDHblF = this.size().height;
        if (this.size().height < 50) {
            return;
        }
        this.zzzeb();
        this.zzzkb();
        this.zzzlb(10, 10, 170, 30, "To activate, click here", true);
        (this.BdhBlF = new Thread(this)).start();
        this.BdhBlF.setPriority(10);
    }
    
    public void zzzfb() {
        this.bDHblf = false;
        this.zzznb();
    }
    
    public void start() {
        this.BdhBlF.resume();
    }
    
    public void destroy() {
        this.BdhBlF.stop();
        this.BdhBlF = null;
    }
    
    public void zzznb() {
        final String s = "outfile";
        try {
            this.bdHblf = new URL(this.getDocumentBase(), s);
            if (null != null) {}
        }
        catch (Exception ex) {}
        this.zzzyb(this.bdHblf, null);
        this.zzzkb();
        this.zzzlb(10, 10, 170, 30, Speech.bdhbLF, true);
        this.bDhblF = true;
        this.repaint();
        this.bDHblf = true;
    }
    
    public final void sayIt(final String s) {
        if (this.bDHblf) {
            this.zzzgb(s);
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (this.BDhBlF != 2) {
                    this.BDhBlF = 2;
                }
                if (this.BDhblF) {
                    try {
                        this.getAppletContext().showDocument(new URL(Speech.bdhbLF), "_new");
                        if (null == null) {}
                    }
                    catch (Exception ex) {}
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    public boolean zzzsb(final char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y';
    }
    
    public boolean zzzyb(final URL url, final String s) {
        try {
            DataInputStream dataInputStream;
            if (url != null) {
                dataInputStream = new DataInputStream(url.openStream());
                if (null != null) {}
            }
            else {
                dataInputStream = new DataInputStream(new FileInputStream(s));
            }
            if (dataInputStream == null) {
                return false;
            }
            this.zzzlb(10, 10, 170, 30, "", false);
            for (int i = 0; i < 42; ++i) {
                this.zzzhb((int)(i / 41.0f * 100.0f));
                this.repaint();
                try {
                    Thread.sleep(1L);
                }
                catch (InterruptedException ex) {}
                final int int1 = dataInputStream.readInt();
                final byte[] array = new byte[int1];
                dataInputStream.readFully(array, 0, int1);
                this.bdhBlf[0][i] = Math.max(15, int1 / 8 - (this.bdhblf[i] & '\u007f') * '\u0005');
                this.bdhBlf[1][i] = Math.max(15, int1 / 8 - (this.Bdhblf[i] & '\u007f') * '\u0005');
                this.bdhBlf[2][i] = Math.max(15, int1 / 8 - (this.bDhblf[i] & '\u007f') * '\u0005');
                final byte[] array2 = new byte[this.bdhBlf[0][i] * 8];
                final byte[] array3 = new byte[this.bdhBlf[1][i] * 8];
                final byte[] array4 = new byte[this.bdhBlf[2][i] * 8];
                System.arraycopy(array, 0, array2, 0, this.bdhBlf[0][i] * 8);
                System.arraycopy(array, 0, array3, 0, this.bdhBlf[1][i] * 8);
                System.arraycopy(array, 0, array4, 0, this.bdhBlf[2][i] * 8);
                this.BdhbLf[i] = new AudioDataStream(new AudioData(array2));
                this.bDhbLf[i] = new AudioDataStream(new AudioData(array3));
                this.BDhbLf[i] = new AudioDataStream(new AudioData(array4));
                AudioPlayer.player.start(this.BdhbLf[i]);
                AudioPlayer.player.stop(this.BdhbLf[i]);
                this.BdhbLf[i].reset();
                AudioPlayer.player.start(this.bDhbLf[i]);
                AudioPlayer.player.stop(this.bDhbLf[i]);
                this.bDhbLf[i].reset();
                AudioPlayer.player.start(this.BDhbLf[i]);
                AudioPlayer.player.stop(this.BDhbLf[i]);
                this.BDhbLf[i].reset();
            }
            final byte[] array5 = { -1 };
            this.bdhbLf[42] = new AudioDataStream(new AudioData(array5));
            this.bdhbLf[43] = new AudioDataStream(new AudioData(array5));
            dataInputStream.readLine();
            int n = 0;
            try {
                boolean b = true;
                while (b) {
                    final String line = dataInputStream.readLine();
                    if (line == null) {
                        b = false;
                    }
                    else {
                        final String s2 = "";
                        int j = 0;
                        String string = s2;
                        while (j < line.length()) {
                            string += (char)(line.charAt(j) - Speech.bdhbLF.charAt(j % 25));
                            ++j;
                        }
                        final StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
                        this.bdhBLf[n][0] = stringTokenizer.nextToken();
                        this.bdhBLf[n][1] = stringTokenizer.nextToken();
                        this.bdhBLf[n][2] = stringTokenizer.nextToken();
                        this.bdhBLf[n][3] = stringTokenizer.nextToken();
                        ++n;
                    }
                }
                this.bdhBLf[n][0] = "@";
                this.bdhBLf[n][1] = "@";
                this.bdhBLf[n][2] = "@";
                this.bdhBLf[n][3] = "@";
            }
            catch (Exception ex2) {}
            dataInputStream.close();
            if (null != null) {}
        }
        catch (Exception ex3) {
            return false;
        }
        return this.BDhblF = true;
    }
    
    public void zzzib(final int bdHBlf) {
        this.bdHBlf = bdHBlf;
    }
    
    public void zzzpb(final int bDhBlf) {
        this.BDhBlf = bDhBlf;
    }
    
    public String zzzgb(final String s) {
        final String bdhblF = "";
        this.BdHBlf = "";
        this.BDHBlf = "";
        this.bDHBlf = "";
        Speech.bdhblF = bdhblF;
        final int n = s.length() - 1;
        int i = 0;
        final int n2 = n;
        while (i <= n2) {
            final char char1 = s.charAt(i);
            char char2;
            if (i == n2) {
                char2 = ' ';
            }
            else {
                char2 = s.charAt(i + 1);
            }
            if (Character.isDigit(char1)) {
                i = this.zzzob(s, i, n2, false);
            }
            else if (char1 == '$' && Character.isDigit(char2)) {
                i = this.zzzob(s, i + 1, n2, true);
            }
            else {
                i = this.zzzfc(s, i, n2);
            }
        }
        final String zzzrb = this.zzzrb(this.BdHBlf);
        int j = 0;
        this.bDHBlf = zzzrb;
        while (j < this.bDHBlf.length()) {
            ++j;
        }
        this.BDHBlf = this.zzzjc(this.bDHBlf);
        this.zzzjb(this.bDHBlf, this.BDHBlf);
        return this.BdHBlf;
    }
    
    public boolean zzztb(final char c) {
        return Character.isUpperCase(c) && !this.zzzsb(c);
    }
    
    public void zzzac(final String s) {
        this.BdHBlf += s;
    }
    
    public int zzzub(final String s) {
        final String[] array = { "IY", "EY", "AE", "AO", "UH", "ER", "AH", "AW", "IH", "EH", "AA", "OW", "UW", "AX", "OY", "p", "t", "k", "f", "TH", "s", "SH", "h", "n", "l", "y", "CH", "WH", "b", "d", "g", "v", "DH", "z", "ZH", "m", "NG", "w", "r", "j", "AY", "YU", " ", ",", "@@" };
        int n = 0;
        int i = 0;
        final String[] array2 = array;
        final int length = s.length();
        do {
            final boolean b = true;
            int n2 = 2;
            boolean b2 = b;
            if (null == null) {}
            while (b2 && n2 > 0) {
                for (int n3 = 0; b2 && i + n2 < length && array2[n3].charAt(0) != '@'; ++n3) {
                    if (array2[n3].length() == n2 && array2[n3].equals(s.substring(i, i + n2))) {
                        i += n2;
                        ++n;
                        b2 = false;
                    }
                }
                --n2;
            }
            if (b2) {
                ++i;
            }
        } while (i < length);
        return n + 1;
    }
    
    public String zzzrb(final String s) {
        final String[] array = { "IY", "EY", "AE", "AO", "UH", "ER", "AH", "AW", "IH", "EH", "AA", "OW", "UW", "AX", "OY", "p", "t", "k", "f", "TH", "s", "SH", "h", "n", "l", "y", "CH", "WH", "b", "d", "g", "v", "DH", "z", "ZH", "m", "NG", "w", "r", "j", "AY", "YU", " ", ",", "@@" };
        String s2 = "";
        int i = 0;
        final String[] array2 = array;
        final int length = s.length();
        do {
            final boolean b = true;
            int n = 2;
            boolean b2 = b;
            if (null == null) {}
            while (b2 && n > 0) {
                for (int n2 = 0; b2 && i + n < length && array2[n2].charAt(0) != '@'; ++n2) {
                    if (array2[n2].length() == n && array2[n2].equals(s.substring(i, i + n))) {
                        i += n;
                        final String string = s2 + (char)n2;
                        b2 = false;
                        s2 = string;
                    }
                }
                --n;
            }
            if (b2) {
                ++i;
            }
        } while (i < length);
        return s2 + '*';
    }
    
    public void zzzjb(final String s, final String s2) {
        final int length = s.length();
        for (int i = 0, n = length; i < n; ++i) {
            final char char1 = s.charAt(i);
            if (i + 1 < n) {
                if (s.charAt(i + 1) == ' ') {
                    Speech.BdhblF = 30;
                }
                else {
                    Speech.BdhblF = 0;
                }
            }
            else {
                Speech.BdhblF = 0;
            }
            final char char2 = s2.charAt(i);
            int n2 = 0;
            final char c = char2;
            switch (c) {
                case 1: {
                    n2 = (this.bdhblf[char1] & '\u007f');
                    break;
                }
                case 2: {
                    n2 = (this.Bdhblf[char1] & '\u007f');
                    break;
                }
                case 3: {
                    n2 = (this.bDhblf[char1] & '\u007f');
                    break;
                }
            }
            this.zzzzb(char1, n2, c);
        }
    }
    
    public String zzzjc(final String s) {
        String s2 = "" + '\0';
        if (s.length() != 1) {
            if (s.charAt(1) != '*') {
                s2 = "" + '\u0001';
            }
            else {
                s2 = "" + '\u0003';
            }
        }
        if (Speech.bdhblF != null && Speech.bdhblF.charAt(0) != '0') {
            s2 = "" + (char)(Speech.bdhblF.charAt(0) - '0');
        }
        int i = 1;
        if (null != null) {}
        while (i < s.length()) {
            char c = '\0';
            if (s.charAt(i - 1) == '*' || s.charAt(i - 1) == '+') {
                c = '\u0001';
            }
            else if (i + 1 < s.length()) {
                if (s.charAt(i + 1) != '*' && s.charAt(i + 1) != '+') {
                    c = '\u0002';
                }
                else {
                    c = '\u0003';
                }
            }
            if (Speech.bdhblF.charAt(i) == '0') {
                s2 += c;
            }
            else {
                s2 += (char)(Speech.bdhblF.charAt(i) - '0');
            }
            ++i;
        }
        return s2;
    }
    
    public void zzzic(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * 128 + n2;
        if (n3 == 1) {
            this.bdhblf[n] = (char)n5;
            return;
        }
        if (n3 == 2) {
            this.Bdhblf[n] = (char)n5;
            return;
        }
        this.bDhblf[n] = (char)n5;
    }
    
    public void zzzzb(final int n, int n2, final int n3) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (n < 42) {
            switch (n3) {
                case 1: {
                    this.BdhbLf[n].reset();
                    if (null == null) {
                        break;
                    }
                    break;
                }
                case 2: {
                    this.bDhbLf[n].reset();
                    break;
                }
                case 3: {
                    this.BDhbLf[n].reset();
                    break;
                }
            }
            if (n2 == 0) {
                final int bdHBlf = this.bdHBlf;
            }
            n2 = this.bDhBlf[n] - n2 * 5;
            switch (n3) {
                case 1: {
                    AudioPlayer.player.start(this.BdhbLf[n]);
                    break;
                }
                case 2: {
                    AudioPlayer.player.start(this.bDhbLf[n]);
                    break;
                }
                case 3: {
                    AudioPlayer.player.start(this.BDhbLf[n]);
                    break;
                }
            }
        }
        if (n >= 42) {
            try {
                Thread.sleep(150L);
                return;
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        try {
            Thread.sleep(Math.max(0L, currentTimeMillis + this.bdhBlf[n3 - 1][n] - System.currentTimeMillis() - Speech.BdhblF - 20L));
        }
        catch (InterruptedException ex2) {}
    }
    
    public void zzzwb(final int n, final int n2) {
        this.bdhbLf[n].reset();
        AudioPlayer.player.start(this.bdhbLf[n]);
        try {
            Thread.sleep(this.bdHBlf * n2 / 100);
        }
        catch (InterruptedException ex) {}
    }
    
    public void zzzec(String substring) {
        final int n = substring.length() - 1;
        int i = 0;
        int n3;
        int n2;
        for (n2 = (n3 = n); n3 > 0 && substring.charAt(n3) == ' '; --n3) {}
        if (substring.charAt(0) == 'X' && substring.charAt(1) == 'X') {
            substring = substring.substring(2);
            do {
                final boolean b = true;
                boolean b2 = false;
                int n4;
                int n5;
                for (n4 = 0, n5 = (b ? 1 : 0); n5 != 0 && this.BdhBlf[n4].charAt(0) != '@'; ++n4) {
                    final int n6 = 2;
                    if (this.BdhBlf[n4].regionMatches(0, substring, i, n6) && this.BdhBlf[n4].length() == 2) {
                        i += n6;
                        this.zzzwb(n4, this.bDhBlf[n4]);
                        final boolean b3 = false;
                        b2 = true;
                        n5 = (b3 ? 1 : 0);
                    }
                }
                for (int n7 = 0; !b2 && n5 != 0 && this.BdhBlf[n7].charAt(0) != '@'; ++n7) {
                    final int n8 = 1;
                    if (this.BdhBlf[n7].regionMatches(0, substring, i, n8) && this.BdhBlf[n7].length() == 1) {
                        i += n8;
                        this.zzzwb(n7, this.bDhBlf[n7]);
                        n5 = 0;
                    }
                }
                if (n5 != 0) {
                    ++i;
                }
            } while (i <= n2);
            return;
        }
        do {
            final boolean b4 = true;
            int n9;
            boolean b5;
            int length;
            int zzzub;
            int j;
            for (n9 = 0, b5 = b4; b5 && this.bdhBLf[n9][this.BdHbLf].charAt(0) != '@'; ++n9) {
                length = this.bdhBLf[n9][this.BdHbLf].length();
                if (this.bdhBLf[n9][this.BdHbLf].regionMatches(0, substring, i, length) && this.zzzxb(n9, i, substring) && this.zzzmb(n9, i + length, n3, substring)) {
                    if (this.bdhBLf[n9][this.bdHbLf].charAt(0) == '(') {
                        ++i;
                    }
                    else {
                        i += length;
                    }
                    if (this.bdhBLf[n9][this.BDHbLf].indexOf("?") > -1) {
                        Speech.bdhblF += this.bdhBLf[n9][this.BDHbLf].substring(this.bdhBLf[n9][this.BDHbLf].indexOf("?") + 1, this.bdhBLf[n9][this.BDHbLf].length());
                    }
                    else {
                        zzzub = this.zzzub(this.bdhBLf[n9][this.BDHbLf]);
                        for (j = 0; j < zzzub; ++j) {
                            Speech.bdhblF += "0";
                        }
                    }
                    this.zzzac(this.bdhBLf[n9][this.BDHbLf]);
                    b5 = false;
                }
            }
            if (b5) {
                ++i;
            }
        } while (i <= n2);
    }
    
    public boolean zzzcc(final char c) {
        boolean letter;
        try {
            letter = Character.isLetter(c);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            letter = ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        }
        return letter;
    }
    
    public Speech() {
        this.bdhblf = new char[] { '£', '\0', '\u00ad', '£', '2', '¨', '®', '\u0094', '(', '\u009e', '2', '¨', '\0', '-', '\u0094', '\u008a', '\u0092', '\u0094', '¶', '\u00cb', '²', '²', '\u009b', '\u0094', '\u00c8', '²', '\0', '\u00c5', '\u008c', '\u0099', '§', '¨', '¹', '\0', '\0', '²', '\0', '\u009b', '¨', '!', '\0', '\u0014', '\0' };
        this.Bdhblf = new char[] { '(', '\u001e', '*', '(', '+', '-', '-', '#', '(', '(', ':', '\u0019', '\u000f', '(', '\u000f', '\u000f', '\u000f', '\b', '(', '(', '\u0019', '(', '\u000f', '\u000f', '9', '\u000f', '\u000f', '\u000f', '\u000f', '\u0014', '\u000f', '-', 'A', '(', '\u000f', '\u001e', '\u000f', '\u000f', '#', '\u000f', '\u000f', '\u000f', '\u000f' };
        this.bDhblf = new char[] { '\0', '\0', '\u001e', '\u001e', '\0', '\0', '7', '\0', '2', '-', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\u001e', '\u0018', '\0', '\0', '\0', '\u0005', '2', '\0', '\0', '\0', '\0', '\0', '\0', '\u001b', '\0', '\n', '\0', '\u001e', '\0', '\u0014', '\n', '\0', '\0', '\0', '\0' };
        this.BdhBlf = new String[] { "IY", "EY", "AE", "AO", "UH", "ER", "AH", "AW", "IH", "EH", "AA", "OW", "UW", "AX", "OY", "P", "T", "K", "F", "TH", "S", "SH", "HH", "N", "L", "Y", "CH", "WH", "B", "D", "G", "V", "DH", "Z", "ZH", "M", "NG", "W", "R", "J", "AY", "YU", "@@" };
        this.bDhBlf = new int[] { 290, 254, 357, 300, 386, 388, 350, 366, 270, 320, 353, 282, 240, 380, 272, 90, 120, 122, 315, 370, 350, 390, 70, 330, 425, 320, 115, 200, 50, 80, 230, 360, 450, 350, 312, 335, 325, 65, 240, 210, 260, 280, 25, 25, 25, 25 };
        this.BdhBLf = new String[] { "ZERO ", "ONE ", "TWO ", "THREE ", "FOUR ", "FIVE ", "SIX ", "SEVEN ", "EIGHT ", "NINE ", "TEN ", "ELEVEN ", "TWELVE ", "THIRTEEN ", "FOURTEEN ", "FIFTEEN ", "SIXTEEN ", "SEVENTEEN ", "EIGHTEEN ", "NINETEEN " };
        final String[] bDhBLf = { "TWENTY ", "THIRTY ", "FORTY ", "FIFTY ", "SIXTY ", "SEVENTY ", "EIGHTY ", "NINETY " };
        this.bdHbLF = "";
        this.bDHblf = false;
        this.bDhblF = false;
        this.BDhblF = false;
        this.BDhblf = true;
        this.BDhBlf = 250;
        this.bdHBlf = 100;
        this.BdHBlf = "";
        this.bDHBlf = "";
        this.bDhBLf = bDhBLf;
        this.bdhbLf = new AudioDataStream[50];
        this.BdhbLf = new AudioDataStream[50];
        this.bDhbLf = new AudioDataStream[50];
        this.BDhbLf = new AudioDataStream[50];
        final int[][] bdhBlf = new int[3][50];
        this.BdHbLf = 1;
        this.bDHbLf = 2;
        this.BDHbLf = 3;
        this.bdhBlf = bdhBlf;
        this.bdhBLf = new String[1000][4];
    }
    
    public boolean zzzbc() {
        final String host = this.getDocumentBase().getHost();
        this.BDhbLF = 1;
        this.bDhbLF = host;
        this.bDhbLF = this.bDhbLF.toLowerCase().trim();
        if (this.bDhbLF.startsWith("http://")) {
            this.bDhbLF = this.bDhbLF.substring(7, this.bDhbLF.length());
        }
        if (this.bDhbLF.startsWith("https://")) {
            this.bDhbLF = this.bDhbLF.substring(8, this.bDhbLF.length());
        }
        if (this.bDhbLF.startsWith("www.")) {
            this.bDhbLF = this.bDhbLF.substring(4, this.bDhbLF.length());
        }
        if (this.bDhbLF.indexOf("/") != -1) {
            this.bDhbLF = this.bDhbLF.substring(0, this.bDhbLF.indexOf("/"));
        }
        for (int i = 0; i < this.bDhbLF.length(); ++i) {
            this.BDhbLF += this.bDhbLF.charAt(i);
        }
        this.BDhbLF *= 123456;
        final String string = "" + this.BDhbLF;
        return this.bDhbLF.indexOf("javapowered") != -1 || this.bDhbLF.indexOf("javaboutique") != -1 || this.bDhbLF.indexOf("internet") != -1 || string.equals(this.bdHbLF);
    }
    
    static {
        final String bdhblF = "";
        Speech.bdhbLF = "http://www.say-it-now.com";
        Speech.BdhbLF = "http://www.wyka-warzecha.com";
        Speech.bdhblF = bdhblF;
    }
}
