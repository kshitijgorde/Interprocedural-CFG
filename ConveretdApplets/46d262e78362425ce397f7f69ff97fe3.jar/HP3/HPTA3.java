// 
// Decompiled by Procyon v0.5.30
// 

package HP3;

import java.awt.Cursor;
import java.awt.Event;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class HPTA3 extends Applet implements Runnable
{
    private Image K;
    private Graphics P;
    private Rectangle i;
    private Image u;
    private Graphics n;
    private Dimension V;
    private static AppletContext U;
    private Thread W;
    private int new;
    private String H;
    private String if;
    private boolean do;
    private String T;
    private boolean try;
    private String D;
    private String j;
    private String o;
    private String I;
    private URL z;
    private String e;
    private String char;
    boolean s;
    String M;
    String t;
    String l;
    String[][] int;
    String[] X;
    private String else;
    int[] J;
    int[] q;
    int[] m;
    int[] h;
    int A;
    int for;
    int null;
    int Q;
    int S;
    private int O;
    String c;
    boolean G;
    boolean f;
    boolean B;
    String[] long;
    String r;
    String b;
    int d;
    Color E;
    Color case;
    int g;
    Color C;
    Color goto;
    int byte;
    Color R;
    int N;
    Color Z;
    Color w;
    Color p;
    Color a;
    Color k;
    String Y;
    String v;
    public static URL L;
    int F;
    private boolean void;
    
    public HPTA3() {
        this.W = null;
        this.H = "(c)CMA";
        this.if = "CMA";
        this.do = false;
        this.T = "";
        this.try = false;
        this.D = "2.12";
        this.j = "CMAGRAFICO";
        this.o = "";
        this.I = "HP2_AJUDA.html";
        this.e = "PORT";
        this.char = "CLASSE PROTEGIDA";
        this.s = false;
        this.M = "";
        this.t = "";
        this.l = "";
        this.int = new String[64][64];
        this.X = new String[64];
        this.else = "";
        this.J = new int[64];
        this.q = new int[32];
        this.m = new int[32];
        this.h = new int[32];
        this.for = 0;
        this.null = 0;
        this.Q = 0;
        this.c = "Sem Dados";
        this.G = false;
        this.f = false;
        this.B = true;
        this.long = new String[8];
        this.r = "";
        this.b = "Arial";
        this.d = 12;
        this.E = Color.orange;
        this.case = Color.orange;
        this.g = 10;
        this.C = Color.white;
        this.goto = Color.orange;
        this.byte = 10;
        this.R = Color.blue;
        this.N = 8;
        this.Z = Color.orange;
        this.w = Color.orange;
        this.p = Color.white;
        this.a = Color.white;
        this.k = Color.black;
        this.Y = "";
        this.v = "L";
        this.void = false;
        this.new = 99;
        this.new = 45;
    }
    
    public void init() {
        this.z = this.getCodeBase();
        this.for();
        HPTA3.U = this.getAppletContext();
        HPTA3.L = this.getDocumentBase();
        this.K = this.createImage(this.getWidth(), this.getHeight());
        this.P = this.K.getGraphics();
        if (System.getProperty("java.version").compareTo("1.1") == 0 && System.getProperty("java.vendor").indexOf("Microsoft") > -1) {
            this.do = true;
        }
        else {
            this.do = this.a();
        }
    }
    
    public void for() {
        this.long[0] = "URL Invalida";
        this.long[1] = "Falha na Conex\u00e3o";
        this.long[2] = "Falha na Leitura dos dados";
        this.long[3] = "Aguarde...";
        this.O = ((this.getParameter("Refresh") == null) ? 300 : Integer.valueOf(this.getParameter("Refresh")));
        String y;
        if ((y = this.getParameter("Endereco")) != null || (y = this.getParameter("URL")) != null) {
            this.Y = y;
            this.f = true;
        }
        final String parameter;
        if ((parameter = this.getParameter("Browser")) != null) {
            this.r = parameter;
        }
        String s;
        if ((s = this.getParameter("MostraLink")) != null || (s = this.getParameter("ShowLink")) != null) {
            if (s.equalsIgnoreCase("true")) {
                this.B = true;
            }
            else {
                this.B = false;
            }
        }
        String b;
        if ((b = this.getParameter("Fonte_Titulo")) != null || (b = this.getParameter("Title_Font")) != null) {
            this.b = b;
        }
        String v;
        if ((v = this.getParameter("Align_Titulo")) != null || (v = this.getParameter("Title_Align")) != null) {
            this.v = v;
        }
        String j;
        if ((j = this.getParameter("AppletG")) != null || (j = this.getParameter("AppletC")) != null) {
            this.j = j;
        }
        String s2;
        if ((s2 = this.getParameter("Mensagem0")) != null || (s2 = this.getParameter("Message0")) != null) {
            this.long[0] = s2;
        }
        String s3;
        if ((s3 = this.getParameter("Mensagem1")) != null || (s3 = this.getParameter("Message1")) != null) {
            this.long[1] = s3;
        }
        String s4;
        if ((s4 = this.getParameter("Mensagem2")) != null || (s4 = this.getParameter("Message2")) != null) {
            this.long[2] = s4;
        }
        String s5;
        if ((s5 = this.getParameter("Mensagem3")) != null || (s5 = this.getParameter("Message3")) != null) {
            this.long[3] = s5;
        }
        String s6;
        if ((s6 = this.getParameter("Tamanho_Titulo")) != null || (s6 = this.getParameter("Title_Size")) != null) {
            this.d = Integer.valueOf(s6);
        }
        String s7;
        if ((s7 = this.getParameter("Cor_Titulo")) != null || (s7 = this.getParameter("Title_Color")) != null) {
            this.case = this.a(s7, Color.orange);
        }
        String s8;
        if ((s8 = this.getParameter("CorF_Titulo")) != null || (s8 = this.getParameter("Title_BGColor")) != null) {
            this.case = this.a(s8, Color.orange);
        }
        String s9;
        if ((s9 = this.getParameter("Tamanho_Label")) != null || (s9 = this.getParameter("Label_Size")) != null) {
            this.g = Integer.valueOf(s9);
        }
        String s10;
        if ((s10 = this.getParameter("Linhas")) != null || (s10 = this.getParameter("Num_Lines")) != null) {
            this.for = Integer.valueOf(s10);
        }
        String s11;
        if ((s11 = this.getParameter("Cor_Label")) != null || (s11 = this.getParameter("Label_Color")) != null) {
            this.C = this.a(s11, Color.white);
        }
        String s12;
        if ((s12 = this.getParameter("CorF_Label")) != null || (s12 = this.getParameter("Label_BGColor")) != null) {
            this.goto = this.a(s12, Color.orange);
        }
        String s13;
        if ((s13 = this.getParameter("Tamanho_Corpo")) != null || (s13 = this.getParameter("FontSize_Body")) != null) {
            this.byte = Integer.valueOf(s13);
        }
        String s14;
        if ((s14 = this.getParameter("Cor_Corpo")) != null || (s14 = this.getParameter("FontColor_Body")) != null) {
            this.R = this.a(s14, Color.blue);
        }
        String s15;
        if ((s15 = this.getParameter("Tamanho_Rodape")) != null || (s15 = this.getParameter("Border_Size")) != null) {
            this.N = Integer.valueOf(s15);
        }
        String s16;
        if ((s16 = this.getParameter("Cor_Rodape")) != null || (s16 = this.getParameter("Border_Color")) != null) {
            this.Z = this.a(s16, Color.orange);
        }
        String s17;
        if ((s17 = this.getParameter("CorF_Rodape")) != null || (s17 = this.getParameter("BorderBG_Color")) != null) {
            this.w = this.a(s17, Color.orange);
        }
        String s18;
        if ((s18 = this.getParameter("Cor_Par")) != null || (s18 = this.getParameter("Color_Even")) != null) {
            this.p = this.a(s18, Color.white);
        }
        String s19;
        if ((s19 = this.getParameter("Cor_Impar")) != null || (s19 = this.getParameter("Color_Odd")) != null) {
            this.a = this.a(s19, Color.white);
        }
        String s20;
        if ((s20 = this.getParameter("Cor_Seta")) != null || (s20 = this.getParameter("Color_Scrollbar")) != null) {
            this.k = this.a(s20, Color.black);
        }
        String e;
        if ((e = this.getParameter("Idioma")) != null || (e = this.getParameter("Language")) != null) {
            this.e = e;
        }
        if (this.e.equalsIgnoreCase("ING")) {
            this.long[0] = "Invalid URL";
            this.long[1] = "Connection Failed";
            this.long[2] = "Reading data failed";
            this.long[3] = "Wait...";
            this.c = "No Data";
            this.char = "PROTECTED CLASS";
        }
        else if (this.e.equalsIgnoreCase("ESP")) {
            this.long[0] = "URL Inv\u00e1lido";
            this.long[1] = "La Conexi\u00f3n Fall\u00f3";
            this.long[2] = "Los datos de lectura fallaron";
            this.long[3] = "Cargando...";
            this.c = "Ningunos Datos";
            this.char = "CLASE PROTEGIDA";
        }
        this.i = new Rectangle(0, 0, this.getWidth(), this.getHeight());
        if (this.for != 0) {
            ++this.for;
        }
        this.null = 0;
    }
    
    public void destroy() {
    }
    
    public void start() {
        this.void = true;
        if (this.W == null) {
            (this.W = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.void = false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.do) {
            this.a(this.P);
            graphics.drawImage(this.K, 0, 0, this);
        }
        else {
            graphics.setColor(this.case);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            final Font font = new Font(this.b, 1, 20);
            graphics.setFont(font);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            graphics.setColor(Color.red);
            graphics.drawString(this.char, (this.getWidth() - fontMetrics.stringWidth(this.char)) / 2, this.getHeight() / 2);
        }
        if (this.try) {
            graphics.setColor(this.case);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            final Font font2 = new Font(this.b, 1, 20);
            graphics.setFont(font2);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
            graphics.setColor(Color.red);
            final String string = "Versao: " + this.D;
            graphics.drawString(string, (this.getWidth() - fontMetrics2.stringWidth(string)) / 2, this.getHeight() / 4);
            final String string2 = "URL Liberada por: " + this.T.toUpperCase();
            graphics.drawString(string2, (this.getWidth() - fontMetrics2.stringWidth(string2)) / 2, this.getHeight() / 4 * 3);
        }
    }
    
    public void a(final Graphics graphics) {
        final int[] array = new int[64];
        final int[] array2 = new int[8];
        final int[] array3 = new int[8];
        if (this.G) {
            graphics.setColor(Color.gray);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            int for1 = this.for;
            if (this.t != "") {
                ++for1;
            }
            if (this.l != "") {
                ++for1;
            }
            int n2;
            final int n = n2 = this.getHeight() / for1;
            if (this.t != "") {
                graphics.setColor(this.case);
                graphics.fillRect(0, 0, this.getWidth(), n);
                final Font font = new Font(this.b, 1, this.d);
                graphics.setFont(font);
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                graphics.setColor(this.E);
                fontMetrics.stringWidth(this.t);
                int n3 = 2;
                if (this.v.toUpperCase() == "C") {
                    n3 = (this.getWidth() - fontMetrics.stringWidth(this.t)) / 2;
                }
                if (this.v.toUpperCase() == "R") {
                    n3 = this.getWidth() - fontMetrics.stringWidth(this.t) - 2;
                }
                graphics.drawString(this.t, n3, (n - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
                n2 += n;
            }
            final Font font2 = new Font(this.b, 1, this.byte);
            graphics.setFont(font2);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
            final int n4 = fontMetrics2.getDescent() + (n - fontMetrics2.getHeight()) / 2;
            array[0] = 14;
            for (int i = 0; i < 16; ++i) {
                this.J[i] = 0;
            }
            for (int j = 0; j < this.A; ++j) {
                for (int k = 0; k < this.S; ++k) {
                    final int stringWidth = fontMetrics2.stringWidth(this.int[j][k]);
                    if (stringWidth > this.J[j]) {
                        this.J[j] = stringWidth;
                    }
                }
            }
            int n5;
            if (this.S == this.for) {
                n5 = this.getWidth() - 2 - 14;
            }
            else {
                n5 = this.getWidth() - 14 - 14;
            }
            for (int l = 0; l < this.A; ++l) {
                n5 -= this.J[l];
            }
            final int n6 = n5 / (this.A - 1);
            for (int n7 = 1; n7 < this.A; ++n7) {
                if (n7 == 1) {
                    array[n7] = array[n7 - 1] + this.J[n7 - 1] + n6 + this.J[n7];
                }
                else {
                    array[n7] = array[n7 - 1] + n6 + this.J[n7];
                }
            }
            int n8 = 0;
            final int n9 = 0;
            if (this.F == 1) {
                ++n8;
                graphics.setColor(this.C);
                graphics.fillRect(0, n2 - n, this.getWidth(), n);
                final Font font3 = new Font(this.b, 1, this.g);
                graphics.setFont(font3);
                final FontMetrics fontMetrics3 = this.getFontMetrics(font3);
                graphics.setColor(this.goto);
                for (int n10 = 0; n10 < this.A; ++n10) {
                    if (n10 == 0) {
                        graphics.drawString(this.int[n10][n9], array[n10], n2 - n4);
                    }
                    else {
                        graphics.drawString(this.int[n10][n9], array[n10] - fontMetrics3.stringWidth(this.int[n10][n9]), n2 - n4);
                    }
                }
                n2 += n;
            }
            final int n11 = n8 + this.null;
            int n12 = 0;
            for (int n13 = n11; n13 < this.null + this.for; ++n13) {
                if (n12 % 2 == this.F) {
                    graphics.setColor(this.p);
                }
                else {
                    graphics.setColor(this.a);
                }
                graphics.fillRect(0, n2 - n, this.getWidth(), n + 10);
                if (this.X[n13].substring(this.X[n13].lastIndexOf("/") + 1, this.X[n13].length()).equals(this.else)) {
                    final int n14 = (n - 10) / 2;
                    array2[0] = 2;
                    array2[array2[1] = 2] = 12;
                    array2[3] = 2;
                    array3[0] = n2 - n14;
                    array3[1] = n2 - n + n14;
                    array3[2] = array3[1] + (array3[0] - array3[1]) / 2;
                    array3[3] = n2 - n14;
                    graphics.setColor(this.k);
                    graphics.fillPolygon(array2, array3, 8);
                }
                if (this.null != 0 && n13 == n11) {
                    final int n15 = (n - 10) / 2;
                    array2[0] = this.getWidth() - 2;
                    array2[1] = this.getWidth() - 7;
                    array2[2] = this.getWidth() - 12;
                    array2[3] = this.getWidth() - 2;
                    array3[0] = n2 - n15;
                    array3[1] = n2 - n + n15;
                    array3[3] = (array3[2] = n2 - n15);
                    graphics.setColor(this.k);
                    graphics.fillPolygon(array2, array3, 8);
                }
                if (n13 == this.null + this.for - 1 && this.null + this.for != this.S) {
                    final int n16 = (n - 10) / 2;
                    array2[0] = this.getWidth() - 7;
                    array2[1] = this.getWidth() - 2;
                    array2[2] = this.getWidth() - 12;
                    array2[3] = this.getWidth() - 7;
                    array3[0] = n2 - n16;
                    array3[2] = (array3[1] = n2 - n + n16);
                    array3[3] = n2 - n16;
                    graphics.setColor(this.k);
                    graphics.fillPolygon(array2, array3, 8);
                }
                this.h[n12] = n13;
                this.q[n12] = n2 - n;
                this.m[n12++] = n2;
                for (int n17 = 0; n17 < this.A; ++n17) {
                    final Font font4 = new Font(this.b, 1, this.byte);
                    graphics.setFont(font4);
                    final FontMetrics fontMetrics4 = this.getFontMetrics(font4);
                    graphics.setColor(this.R);
                    if (n17 == 0) {
                        graphics.drawString(this.int[n17][n13], array[n17], n2 - n4);
                    }
                    else {
                        graphics.drawString(this.int[n17][n13], array[n17] - fontMetrics4.stringWidth(this.int[n17][n13]), n2 - n4);
                    }
                    if (n17 == 0 && this.B) {
                        graphics.drawLine(array[n17], n2 - n4 + 1, array[n17] + fontMetrics4.stringWidth(this.int[n17][n13]), n2 - n4 + 1);
                    }
                }
                n2 += n;
            }
            if (this.l != "") {
                graphics.setColor(this.Z);
                graphics.fillRect(0, n2 - n, this.getWidth(), n);
                final Font font5 = new Font(this.b, 1, this.N);
                graphics.setFont(font5);
                final FontMetrics fontMetrics5 = this.getFontMetrics(font5);
                graphics.setColor(this.w);
                graphics.drawString(this.l, (this.getWidth() - fontMetrics5.stringWidth(this.l)) / 2, n2 + (n - fontMetrics5.getHeight()) / 2 + fontMetrics5.getAscent() - n);
            }
        }
        else {
            graphics.setColor(this.case);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
            final Font font6 = new Font(this.b, 1, 20);
            graphics.setFont(font6);
            final FontMetrics fontMetrics6 = this.getFontMetrics(font6);
            graphics.setColor(Color.red);
            graphics.drawString(this.long[3], (this.getWidth() - fontMetrics6.stringWidth(this.long[3])) / 2, this.getHeight() / 2);
        }
    }
    
    public void run() {
        int n = this.O * 10 + 1;
        while (this.void) {
            if (n >= this.O) {
                this.do();
                n = 0;
            }
            try {
                Thread.sleep(1000L);
                ++n;
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public Color a(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            return new Color(Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public void do() {
        final boolean b = false;
        this.G = false;
        URL url;
        try {
            if (this.Y.toLowerCase().compareTo("http") != 0) {
                url = new URL(this.z, this.Y);
            }
            else {
                url = new URL(this.Y);
            }
        }
        catch (MalformedURLException ex) {
            this.c = this.long[0];
            this.repaint();
            return;
        }
        URLConnection openConnection;
        try {
            openConnection = url.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
        }
        catch (IOException ex2) {
            this.c = this.long[1];
            this.repaint();
            return;
        }
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            this.t = "";
            this.l = "";
            this.F = 0;
            this.A = 0;
            while (this.A < 16) {
                this.J[this.A] = 0;
                ++this.A;
            }
            if (!b) {
                this.S = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.indexOf(";") > 0) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                        try {
                            final String nextToken = stringTokenizer.nextToken();
                            if (nextToken.compareTo("Titulo") == 0) {
                                this.t = stringTokenizer.nextToken();
                            }
                            else if (nextToken.compareTo("Rodape") == 0) {
                                this.l = stringTokenizer.nextToken();
                            }
                            else {
                                if (nextToken.compareTo("Label") == 0) {
                                    this.F = 1;
                                }
                                this.X[this.S] = nextToken;
                                this.A = 0;
                                while (stringTokenizer.hasMoreTokens()) {
                                    final String nextToken2 = stringTokenizer.nextToken();
                                    if (nextToken2.length() > this.J[this.A]) {
                                        this.J[this.A] = nextToken2.length();
                                    }
                                    this.int[this.A][this.S] = nextToken2;
                                    ++this.A;
                                }
                                ++this.S;
                            }
                        }
                        catch (Exception ex3) {
                            this.c = this.long[0];
                        }
                    }
                }
                this.G = true;
                this.c = "";
            }
        }
        catch (Throwable t) {
            this.c = this.long[2];
            this.repaint();
            this.s = true;
            return;
        }
        if (this.for == 0) {
            this.for = this.S;
        }
        this.repaint();
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension v = new Dimension();
        v.height = this.getHeight();
        v.width = this.getWidth();
        if (this.u == null || v.width != this.V.width || v.height != this.V.height) {
            this.u = this.createImage(v.width, v.height);
            this.V = v;
            (this.n = this.u.getGraphics()).setFont(this.getFont());
        }
        this.n.fillRect(0, 0, v.width, v.height);
        this.paint(this.n);
        graphics.drawImage(this.u, 0, 0, null);
    }
    
    public String a(final String s, final int n, final char c) {
        boolean b = false;
        int n2 = s.indexOf(".");
        if (n2 == 0) {
            b = true;
            n2 = s.indexOf(",");
        }
        String nextToken;
        String s2;
        if (n2 > 0) {
            StringTokenizer stringTokenizer;
            if (!b) {
                stringTokenizer = new StringTokenizer(s, ".");
            }
            else {
                stringTokenizer = new StringTokenizer(s, ",");
            }
            nextToken = stringTokenizer.nextToken();
            s2 = stringTokenizer.nextToken();
        }
        else {
            nextToken = s;
            s2 = "000000000000";
        }
        while (s2.length() < n) {
            s2 += "0";
        }
        String string;
        if (n > 0) {
            string = nextToken + c + s2.substring(0, n);
        }
        else {
            string = nextToken;
        }
        return string;
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        String substring = "";
        this.setCursor(Cursor.getPredefinedCursor(0));
        if (this.i.contains(n, n2)) {
            int width;
            if (this.S == this.for) {
                width = this.getWidth();
            }
            else {
                width = this.getWidth() - 20;
            }
            if (n <= width) {
                int n3 = -1;
                for (int i = 0; i < this.for - 1; ++i) {
                    if (n2 >= this.q[i] && n2 <= this.m[i]) {
                        n3 = i;
                        break;
                    }
                }
                if (n3 >= 0) {
                    substring = this.X[this.h[n3]].substring(this.X[this.h[n3]].lastIndexOf("/") + 1, this.X[this.h[n3]].length());
                    if (this.B) {
                        this.setCursor(Cursor.getPredefinedCursor(12));
                    }
                    else {
                        this.setCursor(Cursor.getPredefinedCursor(0));
                    }
                }
                else {
                    substring = "";
                }
            }
            else if (n2 >= this.q[0] && n2 <= this.m[0] && this.null > 0) {
                substring = "Rolar para Cima";
            }
            else if (n2 >= this.q[this.for - 2] && n2 <= this.m[this.for - 2] && this.for + this.null <= this.S - 1) {
                substring = "Rolar para Baixo";
            }
            if (this.B) {
                this.showStatus(substring);
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.i.contains(n, n2)) {
            int width;
            if (this.S == this.for) {
                width = this.getWidth();
            }
            else {
                width = this.getWidth() - 20;
            }
            if (n <= width) {
                int n3 = -1;
                for (int i = 0; i < this.for - 1; ++i) {
                    if (n2 >= this.q[i] && n2 <= this.m[i]) {
                        n3 = i;
                        break;
                    }
                }
                if (n3 >= 0) {
                    new StringBuffer().append(this.X[this.h[n3]]).append(" ").append(n).append(" ").append(n2).toString();
                    this.NovoPapel(this.X[this.h[n3]], event.controlDown());
                }
            }
            if (n > width) {
                if (n2 >= this.q[0] && n2 <= this.m[0] && this.null > 0) {
                    --this.null;
                    this.repaint();
                }
                if (n2 >= this.q[this.for - 2] && n2 <= this.m[this.for - 2] && this.for + this.null <= this.S - 1) {
                    ++this.null;
                    this.repaint();
                }
            }
        }
        return true;
    }
    
    private void if() {
        try {
            HPTA3.U.showDocument(new URL(HPTA3.L, this.I), "HP2");
        }
        catch (MalformedURLException ex) {
            System.out.println("Falha_na_URL");
        }
    }
    
    private void NovoPapel(final String s, final boolean b) {
        final HPGR3 hpgr3 = (HPGR3)this.getAppletContext().getApplet(this.j);
        if (hpgr3 != null) {
            String s2;
            if (b) {
                s2 = s + ",+";
            }
            else {
                s2 = s + ",0";
            }
            hpgr3.NovoPapel(s2);
        }
        this.if(s);
        this.showStatus(s);
    }
    
    public void a(final String s) {
        this.else = s.toString();
        this.repaint();
    }
    
    private void if(final String s) {
        URL url = null;
        if (this.r.length() > 0) {
            try {
                final String string = this.r + s;
                if (this.r.toLowerCase().compareTo("http") != 0) {
                    url = new URL(this.getDocumentBase(), string);
                }
                else {
                    url = new URL(string);
                }
                this.getAppletContext().showDocument(url, "HPTA3");
            }
            catch (MalformedURLException ex) {
                System.out.println("(Tabela)Falha_na_URL " + url.toString());
            }
        }
    }
    
    private boolean a() {
        final URL documentBase = this.getDocumentBase();
        URL url;
        try {
            url = new URL(this.z, "URL");
            System.out.println("Url : " + url.toString());
        }
        catch (MalformedURLException ex) {
            return false;
        }
        URLConnection openConnection;
        try {
            openConnection = url.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
        }
        catch (IOException ex2) {
            return false;
        }
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
        }
        catch (Throwable t) {
            return false;
        }
        final a a = new a();
        boolean b = false;
        String line;
        try {
            line = bufferedReader.readLine();
        }
        catch (IOException ex3) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a.if("zwqc54v$%3", line), ";");
        this.T = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreTokens()) {
            if (documentBase.toString().indexOf(stringTokenizer.nextToken()) >= 0) {
                b = true;
                break;
            }
        }
        char c = '\0';
        for (int i = 0; i < this.H.length(); ++i) {
            c ^= this.H.charAt(i);
        }
        return c == this.new && b;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 26) {
            this.try = !this.try;
            this.repaint();
            return true;
        }
        return true;
    }
    
    static {
        HPTA3.U = null;
    }
}
