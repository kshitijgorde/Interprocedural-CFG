// 
// Decompiled by Procyon v0.5.30
// 

package HP3;

import java.util.GregorianCalendar;
import java.awt.Cursor;
import netscape.javascript.JSObject;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class HPTI3 extends Applet implements Runnable
{
    private String K;
    private boolean do;
    private boolean try;
    private boolean Q;
    private int o;
    private int C;
    private Image u;
    private Graphics m;
    private Dimension S;
    private int V;
    private boolean l;
    private boolean if;
    private String k;
    private final String void = "";
    private final String goto = "  ";
    private static final int E = 100;
    private static final c[] new;
    private static int null;
    private String long;
    private String J;
    private String r;
    private Color N;
    private Color Y;
    private Color d;
    private Color j;
    private Color A;
    private int T;
    private int H;
    private String F;
    private int g;
    private int G;
    private boolean q;
    private boolean char;
    private boolean O;
    private boolean f;
    private boolean P;
    private boolean z;
    private boolean B;
    private boolean case;
    private boolean p;
    private boolean M;
    private String c;
    private String n;
    private String byte;
    private String b;
    private int int;
    private String D;
    private String R;
    private boolean else;
    private Dimension I;
    private Font for;
    private FontMetrics t;
    private int L;
    private URL w;
    private int i;
    private int U;
    private int v;
    private Thread a;
    private SimpleDateFormat e;
    private static final StringBuffer X;
    private c W;
    private static String s;
    int h;
    
    public HPTI3() {
        this.K = "(c)CMA";
        this.if = true;
        this.k = "CMAGRAFICO";
        this.long = "";
        this.J = "";
        this.r = "";
        this.T = 20;
        this.H = 1;
        this.F = "Arial";
        this.g = 10;
        this.G = 60;
        this.f = true;
        this.B = true;
        this.M = true;
        this.c = "PORT";
        this.n = "Aguarde...";
        this.byte = "CLASSE PROTEGIDA";
        this.b = "";
        this.D = "2.20";
        this.R = "";
        this.v = 10;
        this.e = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        this.N = new Color(0, 255, 0);
        this.Y = new Color(255, 0, 0);
        this.d = new Color(255, 255, 0);
        this.j = new Color(0, 0, 0);
        this.A = new Color(255, 255, 255);
        this.int = 99;
        this.int = 45;
        this.J = "";
        this.h = 50;
        this.I = new Dimension();
    }
    
    public void init() {
        this.a("HPTI3", "init");
        final String parameter;
        if ((parameter = this.getParameter("URL")) != null) {
            this.long = parameter;
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("Browser")) != null) {
            this.r = parameter2;
        }
        String j;
        if ((j = this.getParameter("Separador")) != null || (j = this.getParameter("Separator")) != null) {
            this.J = j;
        }
        String f;
        if ((f = this.getParameter("Fonte")) != null || (f = this.getParameter("Font")) != null) {
            this.F = f;
        }
        String k;
        if ((k = this.getParameter("AppletG")) != null || (k = this.getParameter("AppletC")) != null) {
            this.k = k;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("Lateral")) != null) {
            if (parameter3.equalsIgnoreCase("true")) {
                this.q = true;
            }
            else {
                this.q = false;
            }
        }
        String s;
        if ((s = this.getParameter("Parada")) != null || (s = this.getParameter("Stop")) != null) {
            if (s.equalsIgnoreCase("true")) {
                this.O = true;
            }
            else {
                this.O = false;
            }
        }
        String s2;
        if ((s2 = this.getParameter("Roda")) != null || (s2 = this.getParameter("Continuous")) != null) {
            if (s2.equalsIgnoreCase("true")) {
                this.f = true;
            }
            else {
                this.f = false;
            }
        }
        String s3;
        if ((s3 = this.getParameter("Alterna")) != null || (s3 = this.getParameter("Alternate")) != null) {
            if (s3.equalsIgnoreCase("true")) {
                this.char = true;
            }
            else {
                this.char = false;
            }
        }
        String s4;
        if ((s4 = this.getParameter("Atualizacao")) != null || (s4 = this.getParameter("Refresh")) != null) {
            this.G = Integer.valueOf(s4);
        }
        String s5;
        if ((s5 = this.getParameter("Passo")) != null || (s5 = this.getParameter("Border")) != null) {
            this.H = Integer.valueOf(s5);
        }
        String s6;
        if ((s6 = this.getParameter("Tempo")) != null || (s6 = this.getParameter("Time")) != null) {
            this.T = Integer.valueOf(s6);
        }
        String s7;
        if ((s7 = this.getParameter("Tamanho")) != null || (s7 = this.getParameter("FontSize")) != null) {
            this.g = Integer.valueOf(s7);
        }
        String s8;
        if ((s8 = this.getParameter("Fundo")) != null || (s8 = this.getParameter("BGColor")) != null) {
            this.j = this.a(s8);
        }
        String s9;
        if ((s9 = this.getParameter("Positivo")) != null || (s9 = this.getParameter("PositiveColor")) != null) {
            this.N = this.a(s9);
        }
        String s10;
        if ((s10 = this.getParameter("Negativo")) != null || (s10 = this.getParameter("NegativeColor")) != null) {
            this.Y = this.a(s10);
        }
        String s11;
        if ((s11 = this.getParameter("Igual")) != null || (s11 = this.getParameter("NoChangeColor")) != null) {
            this.d = this.a(s11);
        }
        String s12;
        if ((s12 = this.getParameter("CorTexto")) != null || (s12 = this.getParameter("TextColor")) != null) {
            this.A = this.a(s12);
        }
        String s13;
        if ((s13 = this.getParameter("NomeAtivo")) != null || (s13 = this.getParameter("SymbolName")) != null) {
            if (s13.equalsIgnoreCase("true")) {
                this.z = true;
            }
            else {
                this.z = false;
            }
        }
        String s14;
        if ((s14 = this.getParameter("MostraLink")) != null || (s14 = this.getParameter("ShowLink")) != null) {
            if (s14.equalsIgnoreCase("true")) {
                this.B = true;
            }
            else {
                this.B = false;
            }
        }
        String s15;
        if ((s15 = this.getParameter("MostraHora")) != null || (s15 = this.getParameter("ShowTime")) != null) {
            if (s15.equalsIgnoreCase("true")) {
                this.case = true;
            }
            else {
                this.case = false;
            }
        }
        String s16;
        if ((s16 = this.getParameter("MostraSinalPerc")) != null || (s16 = this.getParameter("ShowPercent")) != null) {
            if (s16.equalsIgnoreCase("true")) {
                this.p = true;
            }
            else {
                this.p = false;
            }
        }
        String c;
        if ((c = this.getParameter("Idioma")) != null || (c = this.getParameter("Language")) != null) {
            this.c = c;
        }
        String b;
        if ((b = this.getParameter("FuncaoJS")) != null || (b = this.getParameter("JSFunction")) != null) {
            this.b = b;
        }
        String s17;
        if ((s17 = this.getParameter("Longo")) != null || (s17 = this.getParameter("Long")) != null) {
            if (s17.equalsIgnoreCase("true")) {
                this.M = true;
            }
            else {
                this.M = false;
            }
        }
        if (this.c.equalsIgnoreCase("ING")) {
            this.n = "Wait...";
            this.byte = "PROTECTED CLASS";
        }
        else if (this.c.equalsIgnoreCase("ESP")) {
            this.n = "Cargando...";
            this.byte = "CLASE PROTEGIDA";
        }
        this.o = this.getBounds().width;
        if (this.for() > 0) {
            this.if = false;
        }
        else {
            this.if = true;
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("nomearquivo")) != null) {
            this.P = true;
            String parameter5;
            if ((parameter5 = this.getParameter("titulo")) != null && parameter5.equalsIgnoreCase("true")) {
                parameter5 = "1";
            }
            final String parameter6;
            if ((parameter6 = this.getParameter("msg")) == null) {}
            int i;
            final int n = i = parameter4.indexOf(".dat", 0);
            while (i >= 0) {
                if (parameter4.charAt(i) == '\\' || parameter4.charAt(i) == '/') {
                    final String substring = parameter4.substring(i + 1, n + 4);
                    if (!this.z) {
                        this.long = parameter4.substring(0, i) + "/Ticker.asp?Titulo=" + parameter5 + "&Arquivo=" + substring;
                        break;
                    }
                    this.long = parameter4.substring(0, i) + "/Ticker.asp?Titulo=" + parameter5 + "&Arquivo=" + substring + "&NomeAtivo=1";
                    break;
                }
                else {
                    --i;
                }
            }
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("tamfonte")) != null) {
            this.g = Integer.parseInt(parameter7);
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("corzero")) != null) {
            this.d = this.a(parameter8);
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("cormais")) != null) {
            this.N = this.a(parameter9);
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("cormenos")) != null) {
            this.Y = this.a(parameter10);
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("corfundo")) != null) {
            this.j = this.a(parameter11);
        }
        if (this.P) {
            final String parameter12;
            if ((parameter12 = this.getParameter("url")) != null) {
                this.r = parameter12;
            }
            final String parameter13;
            if ((parameter13 = this.getParameter("intervalo")) != null) {
                this.G = Integer.valueOf(parameter13) * 60;
            }
        }
        String s18;
        if ((s18 = this.getParameter("Espera")) != null || (s18 = this.getParameter("Delay")) != null) {
            this.v = Integer.valueOf(s18);
        }
        if (System.getProperty("java.version").compareTo("1.1") == 0 && System.getProperty("java.vendor").indexOf("Microsoft") > -1) {
            this.do = true;
        }
        else {
            this.do = this.int();
        }
        this.I.height = this.getBounds().height;
        this.I.width = this.getBounds().width;
    }
    
    private int for() {
        final FontMetrics fontMetrics = this.getFontMetrics(new Font(this.F, 1, this.g));
        int n = 0;
        this.C = 0;
        int do1 = 0;
        String new1 = "";
        String s;
        while ((s = this.getParameter("Texto" + n)) != null || (s = this.getParameter("Text" + n)) != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
            if (s.length() < 3) {
                continue;
            }
            final String nextToken = stringTokenizer.nextToken();
            String nextToken2 = "";
            double doubleValue = 0.0;
            String nextToken3 = "";
            String nextToken4 = "";
            if (stringTokenizer.hasMoreTokens()) {
                nextToken2 = stringTokenizer.nextToken();
                if (stringTokenizer.hasMoreTokens()) {
                    new1 = stringTokenizer.nextToken();
                    if (new1.equalsIgnoreCase("-") || new1.equalsIgnoreCase("")) {
                        doubleValue = 9999.0;
                    }
                    else {
                        doubleValue = Double.valueOf(new1);
                    }
                }
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken3 = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken4 = stringTokenizer.nextToken();
                }
            }
            if (HPTI3.new[this.U] == null) {
                HPTI3.new[this.U] = new c();
            }
            final c c = HPTI3.new[this.U];
            c.new = new1;
            c.case = doubleValue;
            c.byte = nextToken2;
            c.if = nextToken;
            c.int = nextToken3;
            c.a = nextToken4;
            if (this.p) {
                new1 = doubleValue + "%";
            }
            else {
                new1 = String.valueOf(doubleValue);
            }
            if (this.case) {
                new1 = new1 + "  " + nextToken4;
            }
            String s2;
            if (this.J.length() > 0) {
                if (nextToken2.length() > 0) {
                    if (!this.z) {
                        s2 = nextToken + "  " + nextToken2 + "  " + new1 + this.J;
                    }
                    else {
                        s2 = nextToken3 + "  " + nextToken2 + "  " + new1 + this.J;
                    }
                }
                else {
                    s2 = nextToken + this.J;
                }
            }
            else if (nextToken2.length() > 0) {
                if (!this.z) {
                    s2 = nextToken + "  " + nextToken2 + "  " + new1;
                }
                else {
                    s2 = nextToken3 + "  " + nextToken2 + "  " + new1;
                }
            }
            else {
                s2 = nextToken;
            }
            final int stringWidth = fontMetrics.stringWidth(s2);
            c.for = stringWidth + 20;
            c.do = do1;
            HPTI3.new[this.U++] = c;
            this.C += stringWidth + 20;
            do1 = do1 + stringWidth + 20;
            ++n;
        }
        return n;
    }
    
    private Color a(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            return new Color(Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getAppletInfo() {
        return "Applet Desenvolvida por M\u00e1rcio G. Rotondano";
    }
    
    public void Alterna() {
        this.char = !this.char;
    }
    
    public void Rot() {
        this.f = !this.f;
    }
    
    public void Refresh() {
        this.a();
    }
    
    public void start() {
        this.a("HPTI3", "start");
        try {
            if (this.long.toLowerCase().compareTo("http") != 0) {
                this.w = new URL(this.getCodeBase(), this.long);
            }
            else {
                this.w = new URL(this.long);
            }
            System.out.println("Refresh : " + this.w.toString());
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return;
        }
        this.else = true;
        (this.a = new Thread(this)).setPriority(4);
        this.a.start();
    }
    
    public void stop() {
        this.else = false;
        this.a("HPTI3", "stop");
    }
    
    public void destroy() {
        if (this.a != null) {
            this.a = null;
        }
        for (int i = 0; i < HPTI3.new.length; ++i) {
            if (HPTI3.new[i] != null) {
                HPTI3.new[i] = null;
            }
        }
        this.a("HPTI3", "destroy");
    }
    
    public void run() {
        int t = this.T;
        int n = this.G * 100;
        this.a("HPTI3", "run");
        this.for = new Font(this.F, 1, this.g);
        this.t = this.getFontMetrics(this.for);
        this.L = (this.getBounds().height - this.t.getDescent()) / 2 + this.t.getHeight() / 2;
        this.i = this.t.stringWidth("  ");
        while (this.else) {
            try {
                if (this.h != 50) {
                    if (this.h < 1) {
                        this.h = 1;
                    }
                    if (this.h > 100) {
                        this.h = 100;
                    }
                    this.T = this.h;
                }
                if (++t >= this.T) {
                    if (!this.l) {
                        this.o -= this.H;
                        if (this.C + this.o < 0) {
                            if (this.f) {
                                this.o = 20;
                            }
                            else {
                                this.o = this.getBounds().width;
                            }
                        }
                        this.repaint();
                    }
                    t = 0;
                }
                if (++n >= n) {
                    if (this.if) {
                        this.a();
                    }
                    n = 0;
                }
                try {
                    Thread.yield();
                    Thread.sleep(this.v);
                }
                catch (InterruptedException ex2) {}
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.a("HPTI3", "run finalizado");
    }
    
    private void a() {
        try {
            final URLConnection openConnection = this.w.openConnection();
            openConnection.setDefaultUseCaches(false);
            openConnection.setUseCaches(false);
            BufferedReader bufferedReader;
            if (openConnection.getContentEncoding() != null) {
                if (openConnection.getContentEncoding().indexOf("gzip") > 0) {
                    bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(openConnection.getInputStream())));
                }
                else {
                    bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                }
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            }
            this.a(bufferedReader);
            bufferedReader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private int a(final BufferedReader bufferedReader) {
        String new1 = "";
        int n = 0;
        this.C = 0;
        int do1 = 0;
        ++HPTI3.null;
        if (HPTI3.new != null && this.U > 0) {
            this.do();
        }
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
                if (line.length() < 3) {
                    continue;
                }
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("")) {
                    this.Q = true;
                }
                else {
                    this.Q = false;
                }
                if (this.Q && !this.b.equalsIgnoreCase("")) {
                    return n;
                }
                String nextToken2 = "";
                String nextToken3 = "";
                String nextToken4 = "";
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken2 = stringTokenizer.nextToken();
                }
                else {
                    nextToken3 = nextToken;
                }
                String nextToken5 = "";
                double doubleValue = 0.0;
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken5 = stringTokenizer.nextToken();
                    if (stringTokenizer.hasMoreTokens()) {
                        new1 = stringTokenizer.nextToken();
                        if (new1.equalsIgnoreCase("-") || new1.equalsIgnoreCase("")) {
                            doubleValue = 9999.0;
                        }
                        else {
                            doubleValue = Double.valueOf(new1);
                        }
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        nextToken3 = stringTokenizer.nextToken();
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        nextToken4 = stringTokenizer.nextToken();
                    }
                }
                if (HPTI3.new[this.U] == null) {
                    HPTI3.new[this.U] = new c();
                }
                this.W = HPTI3.new[this.U];
                this.W.new = new1;
                this.W.try = nextToken2;
                this.W.case = doubleValue;
                this.W.byte = nextToken5;
                this.W.if = nextToken;
                this.W.int = nextToken3;
                this.W.a = nextToken4;
                if (doubleValue == 9999.0) {
                    new1 = "";
                }
                else {
                    new1 = String.valueOf(doubleValue);
                }
                if (this.p) {
                    new1 += "%";
                }
                if (this.case) {
                    new1 = new1 + "  " + nextToken4;
                }
                if (this.J.length() > 0) {
                    if (nextToken5.length() > 0) {
                        if (!this.z) {
                            HPTI3.X.append(nextToken).append("  ");
                            HPTI3.X.append(nextToken5).append("  ");
                            HPTI3.X.append(new1).append(this.J);
                        }
                        else {
                            HPTI3.X.append(nextToken3).append("  ");
                            HPTI3.X.append(nextToken5).append("  ");
                            HPTI3.X.append(new1).append(this.J);
                        }
                    }
                    else {
                        HPTI3.X.append(nextToken).append("  ").append(this.J);
                    }
                }
                else if (nextToken5.length() > 0) {
                    if (!this.z) {
                        HPTI3.X.append(nextToken).append("  ");
                        HPTI3.X.append(nextToken5).append("  ");
                        HPTI3.X.append(new1);
                    }
                    else {
                        HPTI3.X.append(nextToken3).append("  ");
                        HPTI3.X.append(nextToken5).append("  ");
                        HPTI3.X.append(new1);
                    }
                }
                else {
                    HPTI3.X.append(nextToken);
                }
                final int stringWidth = this.t.stringWidth(HPTI3.X.toString());
                HPTI3.X.setLength(0);
                this.W.for = stringWidth + 20;
                this.W.do = do1;
                HPTI3.new[this.U++] = this.W;
                this.C += stringWidth + 20;
                do1 = do1 + stringWidth + 20;
                ++n;
            }
            return n;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        if (HPTI3.s != null) {
            graphics.setColor(Color.white);
            graphics.drawString(HPTI3.s, 100, this.L);
        }
        int i = this.o;
        if (this.do) {
            if (HPTI3.new != null) {
                if (this.U == 0) {
                    graphics.setColor(this.Y);
                    graphics.drawString(this.n, 0, this.L);
                }
                else {
                    do {
                        for (int j = 0; j < this.U; ++j) {
                            this.W = HPTI3.new[j];
                            if (i + this.W.for < 0) {
                                i += this.W.for;
                            }
                            else {
                                if (this.W.case < 0.0) {
                                    graphics.setColor(this.Y);
                                }
                                else if (this.W.case > 0.0 && this.W.case < 9999.0) {
                                    graphics.setColor(this.N);
                                }
                                else {
                                    graphics.setColor(this.d);
                                }
                                final int n = 0;
                                if (this.char) {
                                    if (j % 2 == 0) {
                                        this.L = this.t.getAscent();
                                    }
                                    else {
                                        this.L = this.getBounds().height - this.t.getDescent();
                                    }
                                }
                                if (this.W.byte.length() <= 0) {
                                    graphics.setColor(this.A);
                                }
                                int n2;
                                if (!this.z) {
                                    graphics.drawString(this.W.if, i + n, this.L);
                                    n2 = this.t.stringWidth(this.W.if);
                                }
                                else {
                                    graphics.drawString(this.W.int, i + n, this.L);
                                    n2 = this.t.stringWidth(this.W.int);
                                }
                                final int n3 = n2 + this.i;
                                if (this.W.byte.length() > 0) {
                                    graphics.drawString(this.W.byte, i + n3, this.L);
                                    final int n4 = n3 + (this.t.stringWidth(this.W.byte) + this.i);
                                    int n5;
                                    if (this.W.case == 9999.0) {
                                        n5 = n4 + this.t.charWidth(' ');
                                    }
                                    else if (this.p) {
                                        graphics.drawString(this.W.new, i + n4, this.L);
                                        final int n6 = n4 + this.t.stringWidth(this.W.new);
                                        graphics.drawString("%", i + n6, this.L);
                                        n5 = n6 + this.t.charWidth('%');
                                    }
                                    else {
                                        graphics.drawString(this.W.new, i + n4, this.L);
                                        n5 = n4 + this.t.stringWidth(this.W.new);
                                    }
                                    HPTI3.X.setLength(0);
                                    if (this.W.a.length() > 1 && this.case) {
                                        HPTI3.X.append("  ").append(this.W.a);
                                        graphics.drawString(HPTI3.X.toString(), i + n5, this.L);
                                        n5 += this.t.stringWidth(HPTI3.X.toString());
                                        HPTI3.X.setLength(0);
                                    }
                                    if (this.J.length() > 0 && j < this.U - 1) {
                                        graphics.setColor(this.A);
                                        graphics.drawString(this.J, i + n5, this.L);
                                        final int n7 = n5 + this.t.stringWidth(this.J);
                                    }
                                }
                                i += this.W.for;
                                if (i > this.getBounds().width) {
                                    return;
                                }
                            }
                        }
                        if (!this.f) {
                            break;
                        }
                        i += 20;
                    } while (i <= this.getBounds().width);
                }
            }
        }
        else {
            graphics.setColor(this.Y);
            graphics.drawString(this.byte, 0, this.L);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.u == null || this.I.width != this.S.width || this.I.height != this.S.height) {
            this.u = this.createImage(this.I.width, this.I.height);
            this.S = this.I;
            (this.m = this.u.getGraphics()).setFont(this.for);
            if (!this.char) {
                this.L = (this.I.height - this.t.getDescent()) / 2 + this.t.getHeight() / 2;
            }
        }
        this.m.setColor(this.j);
        this.m.fillRect(0, 0, this.I.width, this.I.height);
        this.paint(this.m);
        graphics.drawImage(this.u, 0, 0, null);
    }
    
    public boolean mouseMove(final Event event, final int v, final int n) {
        if (!this.q) {
            return true;
        }
        if (this.V < v) {
            this.o += 20;
        }
        else if (this.V > v) {
            this.o -= 20;
        }
        this.V = v;
        this.repaint();
        return true;
    }
    
    private void a(final String s, final boolean b) {
        if (!this.Q) {
            JSObject.getWindow((Applet)this).eval("javascript:" + this.b + "('" + s + "');");
        }
    }
    
    private void if(final String s, final String s2, final boolean b) {
        if (!this.Q) {
            JSObject.getWindow((Applet)this).eval("javascript:" + this.b + "('" + s + "','" + s2 + "');");
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (HPTI3.new != null && this.B) {
            for (int length = HPTI3.new.length, i = 0; i < length; ++i) {
                final c c = HPTI3.new[i];
                if (n >= c.do + this.o && n <= this.o + c.do + c.for && c.byte.length() > 0) {
                    final boolean controlDown = event.controlDown();
                    if (this.M) {
                        this.a(c.try, c.if, controlDown);
                        if (!this.b.equalsIgnoreCase("")) {
                            this.if(c.try, c.if, controlDown);
                        }
                    }
                    else {
                        this.do(c.if, controlDown);
                        if (!this.b.equalsIgnoreCase("")) {
                            this.a(c.if, controlDown);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int v, final int n) {
        this.V = v;
        if (this.O) {
            this.l = true;
        }
        if (this.B) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int v, final int n) {
        this.V = v;
        this.l = false;
        this.setCursor(Cursor.getPredefinedCursor(0));
        return true;
    }
    
    public void TimeP() {
        if (this.T < 100) {
            ++this.T;
        }
    }
    
    public void TimeM() {
        if (this.T > 1) {
            --this.T;
        }
    }
    
    public void StepP() {
        if (this.H < 10) {
            ++this.H;
        }
    }
    
    public void StepM() {
        if (this.H > 1) {
            --this.H;
        }
    }
    
    public void Stop() {
        this.l = true;
    }
    
    public void Start() {
        this.l = false;
    }
    
    public void if() {
        this.l = !this.l;
    }
    
    public void Lateral() {
        this.q = !this.q;
    }
    
    private void a(final String s, final String s2, final boolean b) {
        URL url = null;
        if (this.r.length() > 0) {
            try {
                final String string = this.r + s + "/" + s2;
                if (this.r.toLowerCase().compareTo("http") != 0) {
                    url = new URL(this.getDocumentBase(), string);
                }
                else {
                    url = new URL(string);
                }
                this.getAppletContext().showDocument(url, "HPTI3");
            }
            catch (MalformedURLException ex) {
                System.out.println("Falha_na_URL " + url.toString());
            }
        }
        this.NovoPapel(s, s2, b);
    }
    
    private void do(final String s, final boolean b) {
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
                this.getAppletContext().showDocument(url, "HPTI3");
            }
            catch (MalformedURLException ex) {
                System.out.println("Falha_na_URL " + url.toString());
            }
        }
        this.if(s, b);
    }
    
    private void NovoPapel(final String s, final String s2, final boolean b) {
        final String string = s + "/" + s2;
        final HPGR3 hpgr3 = (HPGR3)this.getAppletContext().getApplet(this.k);
        String s3;
        if (hpgr3 != null) {
            if (b) {
                s3 = string + ",+";
            }
            else {
                s3 = string + ",0";
            }
            hpgr3.NovoPapel(s3);
        }
        else {
            s3 = "Applet Grafica N\u00e3o Localizada";
        }
        this.showStatus(s3);
    }
    
    private void if(final String s, final boolean b) {
        final HPGR3 hpgr3 = (HPGR3)this.getAppletContext().getApplet(this.k);
        String s2;
        if (hpgr3 != null) {
            if (b) {
                s2 = s + ",+";
            }
            else {
                s2 = s + ",0";
            }
            hpgr3.NovoPapel(s2);
        }
        else {
            s2 = "Applet Grafica N\u00e3o Localizada";
        }
        this.showStatus(s2);
    }
    
    private boolean int() {
        final URL documentBase = this.getDocumentBase();
        URL url;
        try {
            url = new URL(this.getCodeBase(), "URL");
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
        catch (Exception ex3) {
            return false;
        }
        final a a = new a();
        String line = "";
        boolean b = false;
        try {
            line = bufferedReader.readLine();
        }
        catch (IOException ex4) {
            return false;
        }
        finally {
            try {
                bufferedReader.close();
            }
            catch (IOException ex5) {}
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(a.if("zwqc54v$%3", line), ";");
        this.R = stringTokenizer.nextToken();
        while (stringTokenizer.hasMoreTokens()) {
            if (documentBase.toString().indexOf(stringTokenizer.nextToken()) >= 0) {
                b = true;
                break;
            }
        }
        char c = '\0';
        for (int i = 0; i < this.K.length(); ++i) {
            c ^= this.K.charAt(i);
        }
        return c == this.int && b;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 26) {
            this.try = !this.try;
            this.repaint();
            return true;
        }
        return true;
    }
    
    private void do() {
        this.U = 0;
    }
    
    private void a(final String s, final String s2) {
        System.out.println(this.e.format(new GregorianCalendar().getTime()) + " [" + s + "] " + s2);
    }
    
    static {
        new = new c[100];
        HPTI3.null = 0;
        X = new StringBuffer();
    }
}
