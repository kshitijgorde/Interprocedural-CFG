import java.awt.Toolkit;
import java.applet.AppletContext;
import java.awt.Event;
import netscape.javascript.JSObject;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.util.StringTokenizer;
import java.lang.reflect.Field;
import java.io.InputStream;
import java.awt.Rectangle;
import java.awt.Component;
import java.lang.reflect.Method;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.awt.MediaTracker;
import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Container;
import java.util.Vector;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BtnPlayer extends Applet implements Runnable
{
    int[] try;
    Image[] u;
    int[] int;
    Object z;
    Object[] case;
    Color[] m;
    String[] L;
    int H;
    boolean f;
    boolean I;
    int if;
    boolean p;
    boolean K;
    boolean goto;
    boolean i;
    boolean g;
    boolean C;
    String B;
    String null;
    String l;
    Frame char;
    Image byte;
    Image c;
    boolean a;
    int A;
    int v;
    int j;
    int d;
    Point h;
    String s;
    int F;
    int long;
    int q;
    Color k;
    int J;
    int E;
    Point D;
    String void;
    int for;
    String new;
    Vector M;
    Vector do;
    Vector G;
    Vector o;
    Vector w;
    boolean else;
    boolean r;
    final int n = 50;
    final int e = 19;
    final int b = 15;
    String t;
    
    public BtnPlayer() {
        this.z = null;
        this.H = -1;
        this.f = true;
        this.I = true;
        this.if = 0;
        this.j = -1;
        this.d = -1;
        this.h = null;
        this.long = -1;
        this.q = -1;
        this.M = new Vector();
        this.do = new Vector();
        this.G = new Vector();
        this.o = new Vector();
        this.w = new Vector();
        this.t = null;
    }
    
    public void init() {
        try {
            this.I = this.getCodeBase().getProtocol().equals("file");
            this.else = false;
            this.r = false;
            this.p = false;
            final String property = System.getProperty("java.version");
            final boolean equals = property.equals("1.0.2");
            this.else = !property.startsWith("1.0");
            this.r = (!property.startsWith("1.1") && !property.startsWith("1.0"));
            final boolean startsWith = System.getProperty("os.version").startsWith("3.1");
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            final boolean b = lowerCase.indexOf("windows") != -1 && !startsWith;
            this.p = (lowerCase.indexOf("mac") != -1);
            this.K = (System.getProperty("java.vendor").toLowerCase().indexOf("microsoft") != -1);
            final boolean b2 = !this.K;
            this.goto = ((this.K || (this.p && !b2)) && this.else);
            this.g = (b2 && this.r);
            this.i = (b2 && this.else && !this.g);
            this.A = this.size().width;
            this.v = this.size().height;
            if (this.byte == null) {
                this.byte = this.createImage(this.A, this.v);
            }
            if (this.c == null) {
                this.c = this.createImage(this.A, this.v);
            }
            for (Container container = this.getParent(); container != null; container = container.getParent()) {
                if (container instanceof Frame) {
                    this.char = (Frame)container;
                }
            }
            final String parameter = this.getParameter("p");
            this.void = parameter;
            this.for = 0;
            this.s = this.a();
            this.F = this.do();
            this.do();
            this.do();
            this.setBackground(this.k = this.try(this.if()));
            this.show();
            this.paintAll();
            this.J = this.do();
            this.new = this.a();
            this.D = new Point(this.do(), this.do());
            this.C = (this.do() == 1);
            this.B = this.a();
            this.null = this.a();
            this.l = this.a();
            int n;
            int a = n = this.a(parameter, 1114221141);
            this.int = new int[this.F * 50];
            this.try = new int[this.F * 3];
            this.u = new Image[this.F * 6];
            this.case = new Object[this.F * 9];
            this.L = new String[this.F * 19];
            this.m = new Color[this.F * 15];
            final int[] int1 = this.int;
            for (int i = 0; i < this.F; ++i) {
                final String parameter2 = this.getParameter("b".concat(String.valueOf(String.valueOf(i))));
                if (parameter2 != null) {
                    this.void = parameter2;
                    this.for = 0;
                    final int n2 = i * 50;
                    int j;
                    for (j = 0; j < 8; ++j) {
                        int1[n2 + j] = this.do();
                    }
                    final String[] array = new String[3];
                    for (int k = 0; k < 13; ++k) {
                        this.a(array);
                        for (int l = 0; l < 3; ++l, ++j) {
                            int1[n2 + j] = this.for(array[l]);
                        }
                    }
                    int1[n2 + 50 - 1] = ((int1[n2 + 5] == 1) ? 2 : 0);
                    if (this.j < 0 && int1[n2 + 7] == 1 && int1[n2 + 5] != 0) {
                        this.j = i;
                    }
                    final String substring = this.void.substring(0, this.for);
                    this.a(array);
                    for (int n3 = 0; n3 < 3; ++n3) {
                        this.L[i * 19 + 13 + n3] = array[n3];
                        this.try[i * 3 + n3] = -1;
                    }
                    int n4 = i * 19;
                    for (int n5 = 0; n5 < 3; ++n5) {
                        this.a(array);
                        for (int n6 = 0; n6 < 3; ++n6, ++n4) {
                            this.L[n4] = array[n6];
                        }
                    }
                    for (int n7 = 0; n7 < 4; ++n7, ++n4) {
                        this.L[n4] = this.a();
                    }
                    final int for1 = this.for;
                    int n8 = i * 15;
                    for (int n9 = 0; n9 < 3; ++n9) {
                        this.a(array);
                        for (int n10 = 0; n10 < 3; ++n10, ++n8) {
                            this.m[n8] = this.try(array[n10]);
                        }
                    }
                    final String concat = String.valueOf(String.valueOf(substring)).concat(String.valueOf(String.valueOf(this.void.substring(for1, this.for - 1))));
                    int n11 = i * 19 + 16;
                    this.a(array);
                    for (int n12 = 0; n12 < 3; ++n12, ++n11) {
                        this.L[n11] = array[n12];
                    }
                    a = this.a(parameter2, a);
                    n = this.a(concat, n);
                }
            }
            final int n13 = a & Integer.MAX_VALUE;
            final int n14 = n & Integer.MAX_VALUE;
            final int int2 = this.int("r");
            final int int3 = this.int("c");
            if (n13 != int2 && n14 != int3 && !this.I) {
                this.f = false;
            }
            this.H = 0;
            if ((b && equals) || this.else) {
                new Thread(this).start();
            }
            else {
                this.run();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String getParameter(final String s) {
        String s2 = super.getParameter(s);
        if (s2 == null) {
            s2 = this.a(s);
        }
        return s2;
    }
    
    private String a(final String s) {
        if (this.t == null) {
            final URL documentBase = this.getDocumentBase();
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(documentBase.openStream()));
                int n = 0;
                final String value = String.valueOf(String.valueOf(new StringBuffer("<applet name=\"").append(this.s).append("\"")));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (n != 0) {
                        final int index = line.toLowerCase().indexOf("</applet>");
                        if (index >= 0) {
                            this.t = String.valueOf(String.valueOf(this.t)).concat(String.valueOf(String.valueOf(line.substring(0, index + "</applet>".length()))));
                            break;
                        }
                        this.t = String.valueOf(String.valueOf(this.t)).concat(String.valueOf(String.valueOf(line)));
                    }
                    else {
                        final int index2 = line.toLowerCase().indexOf(value);
                        if (index2 < 0) {
                            continue;
                        }
                        n = 1;
                        this.t = line.substring(index2);
                    }
                }
                bufferedReader.close();
            }
            catch (IOException ex) {}
        }
        if (this.t != null) {
            final String value2 = String.valueOf(String.valueOf(new StringBuffer("<param name=\"").append(s).append("\" value=\"")));
            final int index3 = this.t.indexOf(value2);
            if (index3 >= 0) {
                final int index4 = this.t.indexOf("\">", index3);
                if (index4 >= 0) {
                    return this.t.substring(index3 + value2.length(), index4);
                }
            }
        }
        return null;
    }
    
    private String new(final String s) {
        final StringBuffer sb = new StringBuffer(s);
        final StringBuffer sb2 = new StringBuffer();
        sb2.setLength(s.length());
        int n = 0;
        for (int i = 0; i < sb.length(); ++i) {
            final char char1 = sb.charAt(i);
            if (this.else) {
                if (!Character.isWhitespace(sb.charAt(i))) {
                    sb2.setCharAt(n++, char1);
                }
            }
            else if (Character.isSpace(char1) || char1 == '\t' || char1 == '\n' || char1 == '\r') {
                sb2.setCharAt(n++, char1);
            }
        }
        return sb2.toString();
    }
    
    int a(final String s, int n) {
        final char[] charArray = this.new(s).toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            n ^= (c << 24 | c << 16 | c << 8 | c);
            final char c2 = (char)(c & '\u001f');
            n = ((n & -1) << c2 | n >>> ' ' - c2);
        }
        return n;
    }
    
    int int(final String s) {
        final String parameter = this.getParameter(s);
        return (parameter != null && parameter.length() > 0) ? Integer.parseInt(parameter, 16) : Integer.MIN_VALUE;
    }
    
    String if() {
        String trim = "";
        if (this.for < this.void.length()) {
            int n = this.void.indexOf(44, this.for);
            if (n < 0) {
                n = this.void.length();
            }
            trim = this.void.substring(this.for, n).trim();
            this.for = n + 1;
        }
        return trim;
    }
    
    void a(final String[] array) {
        final String if1 = this.if();
        if (if1.startsWith("^")) {
            final String do1 = this.do(if1.substring(1, if1.length()));
            for (int i = 0; i < 3; ++i) {
                array[i] = do1;
            }
        }
        else {
            array[0] = this.do(if1);
            for (int j = 1; j < 3; ++j) {
                array[j] = this.a();
            }
        }
    }
    
    String do(String replace) {
        replace = replace.trim().replace('|', ',').replace('`', '\"');
        return replace.equals(".") ? null : replace;
    }
    
    String a() {
        return this.do(this.if());
    }
    
    int for(String substring) {
        if (substring.startsWith("+")) {
            substring = substring.substring(1);
        }
        int int1 = 0;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (Exception ex) {}
        return int1;
    }
    
    int do() {
        return this.for(this.if());
    }
    
    Color try(final String s) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(s.trim(), 16);
        }
        catch (Exception ex) {}
        return new Color(int1);
    }
    
    Image a(final Vector vector, final int n) {
        return (n < 0 || n >= vector.size()) ? null : vector.elementAt(n);
    }
    
    int a(final String s, final int n, final int n2, final boolean b, final boolean b2, final MediaTracker mediaTracker) {
        if (s == null) {
            return -1;
        }
        for (int i = this.M.size() - 1; i >= 0; --i) {
            if (((String)this.M.elementAt(i)).equals(s)) {
                return i;
            }
        }
        int size = -1;
        try {
            Image image = null;
            if (this.g) {
                String value = s;
                if (this.B != null && !this.I) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.B))).append("/").append(s)));
                }
                try {
                    image = this.getImage(this.getDocumentBase(), value);
                }
                catch (Exception ex) {
                    System.err.println(ex);
                }
            }
            Label_0226: {
                if (image == null) {
                    if (!this.i && !this.goto) {
                        if (!this.g) {
                            break Label_0226;
                        }
                    }
                    try {
                        final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(s));
                        if (bufferedInputStream != null) {
                            final byte[] array = new byte[bufferedInputStream.available()];
                            bufferedInputStream.read(array);
                            bufferedInputStream.close();
                            image = this.getToolkit().createImage(array);
                        }
                    }
                    catch (Exception ex4) {}
                }
            }
            if (image == null) {
                String value2 = s;
                if (this.B != null && !this.I) {
                    value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.B))).append("/").append(s)));
                }
                try {
                    image = this.getImage(this.getDocumentBase(), value2);
                }
                catch (Exception ex2) {
                    System.err.println(ex2);
                }
            }
            image.getWidth(this);
            final Graphics graphics = this.c.getGraphics();
            graphics.drawImage(image, 0, 0, this);
            graphics.dispose();
            size = this.do.size();
            this.checkImage(image, this);
            this.M.addElement(s);
            this.do.addElement(image);
            this.G.addElement(null);
        }
        catch (Exception ex3) {
            System.err.println("outer catch: ".concat(String.valueOf(String.valueOf(ex3))));
        }
        return size;
    }
    
    Object if(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = this.o.size() - 1; i >= 0; --i) {
            if (((String)this.o.elementAt(i)).equals(s)) {
                return this.w.elementAt(i);
            }
        }
        Object o = null;
        Object resourceAsStream = null;
        try {
            if (this.i) {
                resourceAsStream = this.getClass().getResourceAsStream(s);
            }
            if (resourceAsStream == null || this.g) {
                String value = s;
                if (this.null != null && !this.I) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.null))).append("/").append(s)));
                }
                final AudioClip audioClip = (AudioClip)(o = this.getAudioClip(this.getDocumentBase(), value));
                if (this.i) {
                    audioClip.play();
                    audioClip.stop();
                }
            }
            else {
                final Object instance = Class.forName("sun.audio.AudioStream").getConstructor(Class.forName("java.io.InputStream")).newInstance(resourceAsStream);
                final Method[] methods = instance.getClass().getMethods();
                Method method = null;
                for (int j = 0; j < methods.length; ++j) {
                    if (methods[j].getName().compareTo("getData") == 0) {
                        method = methods[j];
                    }
                }
                o = method.invoke(instance, (Object[])null);
            }
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        if (o != null) {
            this.o.addElement(s);
            this.w.addElement(o);
        }
        return o;
    }
    
    void if(final int n) {
        final int n2 = n * 9 + 3;
        for (int i = 0; i < 3; ++i) {
            this.case[n2 + i] = this.a(this.case[n2 + i]);
        }
        final int[] int1 = this.int;
        final int n3 = n * 50;
        if ((int1[n3 + 6] & 0x4) != 0x0) {
            final int n4 = this.try[n * 3 + int1[n3 + 50 - 1]];
            final Image a = this.a(this.do, n4);
            final Image a2 = this.a(this.G, n4);
            if (a != null && a2 == a && (this.goto || (this.checkImage(a, this) & 0x20) != 0x0)) {
                this.a = true;
                a.flush();
                a.getWidth(this);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(a, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.checkImage(a, this);
                this.a = false;
            }
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (!this.a) {
            final boolean b = (n & 0x10) != 0x0;
            final boolean b2 = (n & 0x20) != 0x0;
            if (b && !b2) {
                final int index = this.do.indexOf(image);
                if (index >= 0) {
                    final Image a = this.a(this.G, index);
                    if (a == null) {
                        this.G.setElementAt(this.byte, index);
                    }
                    else if (a == this.byte) {
                        this.G.setElementAt(image, index);
                    }
                }
            }
            if (b || b2) {
                final Vector<Rectangle> vector = new Vector<Rectangle>();
                this.buildImage(image, vector, null);
                for (int i = 0; i < vector.size(); ++i) {
                    this.paintRect(vector.elementAt(i));
                }
            }
        }
        return (n & 0xA0) == 0x0;
    }
    
    Object a(final Object o) {
        if (o != null) {
            try {
                if (o instanceof AudioClip) {
                    ((AudioClip)o).stop();
                }
                else {
                    Class<?> forName = null;
                    try {
                        forName = Class.forName("sun.audio.AudioData");
                    }
                    catch (Exception ex2) {}
                    if (forName != null && forName.isInstance(o)) {
                        final Class<?> forName2 = Class.forName("sun.audio.AudioPlayer");
                        final Object value = forName2.getField("player").get(forName2.newInstance());
                        final Method[] methods = value.getClass().getMethods();
                        Method method = null;
                        for (int i = 0; i < methods.length; ++i) {
                            if (methods[i].getName().compareTo("stop") == 0) {
                                method = methods[i];
                            }
                        }
                        method.invoke(value, o);
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("From stopSound(): ".concat(String.valueOf(String.valueOf(ex))));
            }
        }
        return null;
    }
    
    void if(final int n, final int n2) {
        final int n3 = n * 9 + n2;
        Object z = this.case[n3];
        final int n4 = this.int[n * 50 + 35 + n2] >> 2;
        if (z != null) {
            if (this.C) {
                this.z = this.a(this.z);
            }
            try {
                if (z instanceof AudioClip) {
                    final AudioClip audioClip = (AudioClip)z;
                    if ((n4 & 0x2) != 0x0) {
                        audioClip.loop();
                    }
                    else {
                        audioClip.play();
                    }
                }
                else {
                    Class<?> forName = null;
                    try {
                        forName = Class.forName("sun.audio.AudioData");
                    }
                    catch (Exception ex2) {}
                    if (forName != null && forName.isInstance(z)) {
                        final Class<?> forName2 = Class.forName("sun.audio.AudioPlayer");
                        this.case[n3 + 6] = this.a(this.case[n3 + 6]);
                        InputStream inputStream;
                        if ((n4 & 0x2) != 0x0) {
                            inputStream = (InputStream)Class.forName("sun.audio.ContinuousAudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(z);
                        }
                        else {
                            inputStream = (InputStream)Class.forName("sun.audio.AudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(z);
                        }
                        final Field field = forName2.getField("player");
                        field.getType();
                        final Object value = field.get(null);
                        final Method[] methods = value.getClass().getMethods();
                        Method method = null;
                        for (int i = 0; i < methods.length; ++i) {
                            if (methods[i].getName().compareTo("start") == 0) {
                                method = methods[i];
                            }
                        }
                        method.invoke(value, inputStream);
                        z = inputStream;
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("From playSound(): ".concat(String.valueOf(String.valueOf(ex))));
            }
            if (this.C) {
                this.z = z;
            }
            this.case[n3 + 6] = z;
            if (n4 != 0) {
                this.case[n3 + 3] = z;
            }
        }
    }
    
    void a(final String s, final boolean b) {
        Object if1 = this.if(s);
        if (if1 != null) {
            if (this.C) {
                this.z = this.a(this.z);
            }
            try {
                if (if1 instanceof AudioClip) {
                    final AudioClip audioClip = (AudioClip)if1;
                    if (b) {
                        audioClip.loop();
                    }
                    else {
                        audioClip.play();
                    }
                }
                else {
                    Class<?> forName = null;
                    try {
                        forName = Class.forName("sun.audio.AudioData");
                    }
                    catch (Exception ex2) {}
                    if (forName != null && forName.isInstance(if1)) {
                        final Class<?> forName2 = Class.forName("sun.audio.AudioPlayer");
                        InputStream inputStream;
                        if (b) {
                            inputStream = (InputStream)Class.forName("sun.audio.ContinuousAudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(if1);
                        }
                        else {
                            inputStream = (InputStream)Class.forName("sun.audio.AudioDataStream").getConstructor(Class.forName("sun.audio.AudioData")).newInstance(if1);
                        }
                        final Field field = forName2.getField("player");
                        field.getType();
                        final Object value = field.get(null);
                        final Method[] methods = value.getClass().getMethods();
                        Method method = null;
                        for (int i = 0; i < methods.length; ++i) {
                            if (methods[i].getName().compareTo("start") == 0) {
                                method = methods[i];
                            }
                        }
                        method.invoke(value, inputStream);
                        if1 = inputStream;
                    }
                }
            }
            catch (Exception ex) {
                System.err.println("From playSound(): ".concat(String.valueOf(String.valueOf(ex))));
            }
            if (this.C) {
                this.z = if1;
            }
        }
    }
    
    Rectangle a(final Rectangle rectangle, final Rectangle rectangle2) {
        return (rectangle == null) ? rectangle2 : rectangle.union(rectangle2);
    }
    
    void a(final long n) {
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
        }
    }
    
    void a(final StringTokenizer stringTokenizer, final int[] array) {
        String s = stringTokenizer.nextToken().trim();
        if (s.endsWith("+")) {
            array[0] = 1;
        }
        else if (s.endsWith("-")) {
            array[0] = -1;
        }
        if (array[0] != 0) {
            s = s.substring(0, s.length() - 1);
        }
        array[1] = this.for(s);
    }
    
    Rectangle a(final String s, Rectangle rectangle) {
        if (s != null) {
            final int[] int1 = this.int;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
            for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
                final String trim = stringTokenizer.nextToken().trim();
                int index = trim.indexOf(32);
                if (index >= 0) {
                    String[] array;
                    String substring;
                    int n;
                    for (array = new String[] { "show", "hide", "up", "over", "down", "wait", "call", "move", "size", "xform", "settext", "play", "fade" }, substring = trim.substring(0, index), n = array.length - 1; n >= 0 && !substring.equalsIgnoreCase(array[n]); --n) {}
                    if (n >= 0) {
                        while (trim.charAt(index) == ' ' && index < trim.length()) {
                            ++index;
                        }
                        if (index < trim.length()) {
                            final String substring2 = trim.substring(index);
                            switch (n) {
                                case 5: {
                                    this.paintRect(rectangle);
                                    this.a((long)this.for(substring2));
                                    break;
                                }
                                case 6: {
                                    this.callJS(substring2);
                                    break;
                                }
                                case 7:
                                case 8:
                                case 9:
                                case 12: {
                                    final int index2 = substring2.indexOf(":");
                                    if (index2 >= 0) {
                                        final String trim2 = substring2.substring(0, index2).trim();
                                        final StringTokenizer stringTokenizer2 = new StringTokenizer(substring2.substring(index2 + 1).trim(), ", ");
                                        final int[] array2 = { 0, 0 };
                                        final int[] array3 = { 0, 0 };
                                        final int[] array4 = { 0, 0 };
                                        final int[] array5 = { 0, 0 };
                                        final int[] array6 = { 0, 0 };
                                        if (n == 7 || n == 9) {
                                            this.a(stringTokenizer2, array2);
                                            this.a(stringTokenizer2, array3);
                                        }
                                        if (n == 8 || n == 9) {
                                            this.a(stringTokenizer2, array4);
                                            this.a(stringTokenizer2, array5);
                                        }
                                        if (n == 12) {
                                            this.a(stringTokenizer2, array6);
                                        }
                                        int for1 = 0;
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            for1 = this.for(stringTokenizer2.nextToken().trim());
                                        }
                                        int for2 = 0;
                                        if (stringTokenizer2.hasMoreTokens()) {
                                            for2 = this.for(stringTokenizer2.nextToken().trim());
                                        }
                                        for (int j = this.F - 1; j >= 0; --j) {
                                            if (this.L[j * 19 + 11].equals(trim2)) {
                                                rectangle = this.a(rectangle, this.getBtnRect(j));
                                                final int n2 = j * 50;
                                                final Rectangle rectangle2 = new Rectangle(int1[n2], int1[n2 + 1], int1[n2 + 2], int1[n2 + 3]);
                                                final Rectangle union = rectangle2.union(rectangle2);
                                                if (n == 7 || n == 9) {
                                                    union.x = ((array2[0] == 0) ? array2[1] : (rectangle2.x + array2[0] * array2[1]));
                                                    union.y = ((array3[0] == 0) ? array3[1] : (rectangle2.y + array3[0] * array3[1]));
                                                }
                                                if (n == 8 || n == 9) {
                                                    union.width = ((array4[0] == 0) ? array4[1] : (rectangle2.width + array4[0] * array4[1]));
                                                    union.height = ((array5[0] == 0) ? array5[1] : (rectangle2.height + array5[0] * array5[1]));
                                                    this.a(this.d = j);
                                                }
                                                if (for1 == 0) {
                                                    this.a(j, union);
                                                    rectangle = this.a(rectangle, this.getBtnRect(j));
                                                }
                                                else {
                                                    this.if |= 0x2;
                                                    this.paintRect(rectangle);
                                                    int max = for1;
                                                    if (this.g && for2 == 0) {
                                                        max = Math.max(max / 5, 1);
                                                    }
                                                    for (int k = 1; k <= max; ++k) {
                                                        final long currentTimeMillis = System.currentTimeMillis();
                                                        final int n3 = max - k;
                                                        final Rectangle btnRect = this.getBtnRect(j);
                                                        this.a(j, new Rectangle((n3 * rectangle2.x + k * union.x) / max, (n3 * rectangle2.y + k * union.y) / max, (n3 * rectangle2.width + k * union.width) / max, (n3 * rectangle2.height + k * union.height) / max));
                                                        this.paintRect(btnRect.union(this.getBtnRect(j)));
                                                        if (k < max) {
                                                            this.a(for2 - (System.currentTimeMillis() - currentTimeMillis));
                                                        }
                                                    }
                                                    this.if &= 0xFFFFFFFD;
                                                }
                                                this.d = -1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                    break;
                                }
                                case 10: {
                                    final int index3 = substring2.indexOf(44);
                                    if (index3 < 0) {
                                        break;
                                    }
                                    final int index4 = substring2.indexOf(58, index3);
                                    if (index4 < 0) {
                                        break;
                                    }
                                    final String trim3 = substring2.substring(0, index3).trim();
                                    final String lowerCase = substring2.substring(index3 + 1, index4).trim().toLowerCase();
                                    final String substring3 = substring2.substring(index4 + 1);
                                    int n4 = -1;
                                    if (lowerCase.equals("up")) {
                                        n4 = 0;
                                    }
                                    else if (lowerCase.equals("over")) {
                                        n4 = 1;
                                    }
                                    else if (lowerCase.equals("down")) {
                                        n4 = 2;
                                    }
                                    else if (lowerCase.equals("all")) {
                                        n4 = 3;
                                    }
                                    if (n4 == 3) {
                                        rectangle = this.a(trim3, 3, substring3, rectangle, true);
                                        rectangle = this.a(trim3, 4, substring3, rectangle, true);
                                        rectangle = this.a(trim3, 5, substring3, rectangle, true);
                                        break;
                                    }
                                    if (n4 >= 0) {
                                        rectangle = this.a(trim3, 3 + n4, substring3, rectangle, true);
                                        break;
                                    }
                                    break;
                                }
                                case 11: {
                                    final int index5 = substring2.indexOf(44);
                                    boolean b = false;
                                    String trim4 = substring2;
                                    if (index5 >= 0) {
                                        trim4 = substring2.substring(0, index5).trim();
                                        if (!substring2.substring(index5 + 1).trim().equals("0")) {
                                            b = true;
                                        }
                                    }
                                    this.a(trim4, b);
                                    break;
                                }
                                default: {
                                    if (n < 2) {
                                        rectangle = this.a(substring2, 4, 1 - n, rectangle, false);
                                        break;
                                    }
                                    rectangle = this.a(substring2, 49, n - 2, rectangle, false);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return rectangle;
    }
    
    Rectangle a(final int n, final Rectangle rectangle, final int n2) {
        return this.a(this.L[n * 19 + ((n2 >= 3) ? (13 + n2) : (6 + n2))], this.a(rectangle, this.getBtnRect(n)));
    }
    
    void a(final int n, final Rectangle rectangle) {
        final int n2 = n * 50;
        final int[] int1 = this.int;
        int1[n2] = rectangle.x;
        int1[n2 + 1] = rectangle.y;
        int1[n2 + 2] = rectangle.width;
        int1[n2 + 3] = rectangle.height;
    }
    
    public Rectangle getBtnRect(final int n) {
        final int n2 = n * 50;
        int n3 = 0;
        final int[] int1 = this.int;
        for (int i = 0; i < 3; ++i) {
            final int n4 = int1[n2 + 38 + i];
            if (n4 > n3) {
                n3 = n4;
            }
        }
        return new Rectangle(int1[n2] - n3, int1[n2 + 1] - n3, int1[n2 + 2] + n3 * 2, int1[n2 + 3] + n3 * 2);
    }
    
    public void paintRect(final Rectangle rectangle) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null && rectangle != null) {
            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            this.a(graphics);
        }
    }
    
    public void paintAll() {
        this.paintRect(new Rectangle(0, 0, this.A, this.v));
    }
    
    public void paintBtn(final int n) {
        this.paintRect(this.getBtnRect(n));
    }
    
    void if(final boolean b, final Graphics graphics, final int[] array, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (b) {
            graphics.setColor(color);
            graphics.drawLine(n3, n2, n4, n2);
        }
        else {
            final int n5 = color.getRGB() | 0xFF000000;
            final int n6 = n2 * n;
            int i = n6 + n3;
            do {
                array[i++] = n5;
            } while (i != n6 + n4 + 1);
        }
    }
    
    void a(final boolean b, final Graphics graphics, final int[] array, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (b) {
            graphics.setColor(color);
            graphics.drawLine(n2, n3, n2, n4);
        }
        else {
            final int n5 = color.getRGB() | 0xFF000000;
            int i = n3 * n + n2;
            do {
                array[i] = n5;
                i += n;
            } while (i != (n4 + 1) * n + n2);
        }
    }
    
    public void drawBtnImage(final Graphics graphics, final int n, final int n2, final int n3, final Image image, final Vector vector) {
        try {
            final boolean b = n == this.d;
            final int n4 = n * 6 + ((n3 == 0) ? 0 : 3) + n2;
            final Image[] u = this.u;
            if (n3 == 1 || b || u[n4] == null) {
                final int[] int1 = this.int;
                final int n5 = n * 50;
                final int n6 = int1[n5 + 2];
                final int n7 = int1[n5 + 3];
                final Color color = this.m[n * 15 + n2];
                final int n8 = int1[n5 + 14 + n2];
                final boolean b2 = (n8 & 0x100) != 0x0;
                final int n9 = n8 & 0xFF;
                final int n10 = int1[n5 + 11 + n2];
                final int red = color.getRed();
                final int green = color.getGreen();
                final int blue = color.getBlue();
                final int min = Math.min(red + n9, 255);
                final int min2 = Math.min(green + n9, 255);
                final int min3 = Math.min(blue + n9, 255);
                final int max = Math.max(red - n9, 0);
                final int max2 = Math.max(green - n9, 0);
                final int max3 = Math.max(blue - n9, 0);
                final Color color2 = new Color(min, min2, min3);
                final Color color3 = new Color(max, max2, max3);
                final int n11 = int1[n5 + 8 + n2];
                final int n12 = (n11 > 1) ? n10 : 0;
                final int n13 = (n11 == 3 || n11 == 5) ? 2 : 0;
                final int n14 = n6 * n7;
                int[] array = null;
                if (n3 == 0) {
                    if (n11 == 0 || vector != null) {
                        return;
                    }
                    if (!b) {
                        if (n14 <= 0) {
                            return;
                        }
                        array = new int[n14];
                    }
                    if (n11 == 1 || n11 > 3) {
                        if (b) {
                            graphics.setColor(color);
                            graphics.fillRect(0, 0, n6, n7);
                        }
                        else {
                            final int n15 = color.getRGB() | 0xFF000000;
                            for (int i = 0; i < n6; ++i) {
                                array[i] = n15;
                            }
                            for (int n16 = 0, j = 0; j < n7 - 1; ++j, n16 += n6) {
                                System.arraycopy(array, n16, array, n16 + n6, n6);
                            }
                        }
                    }
                    if (n10 > 0 && n11 > 1) {
                        for (int k = 0; k < n10; ++k) {
                            final int n17 = n10 - k;
                            final Color color4 = b2 ? color2 : new Color(red + (min - red) * n17 / n10, green + (min2 - green) * n17 / n10, blue + (min3 - blue) * n17 / n10);
                            final Color color5 = b2 ? color3 : new Color(red + (max - red) * n17 / n10, green + (max2 - green) * n17 / n10, blue + (max3 - blue) * n17 / n10);
                            final Color color6 = (n11 == 3 || n11 == 5) ? color5 : color4;
                            this.if(b, graphics, array, n6, k, k, n6 - 1 - k, color6);
                            this.a(b, graphics, array, n6, k, k, n7 - 1 - k, color6);
                            final Color color7 = (n11 == 3 || n11 == 5) ? color4 : color5;
                            this.a(b, graphics, array, n6, n6 - 1 - k, k, n7 - 1 - k, color7);
                            this.if(b, graphics, array, n6, n7 - 1 - k, k, n6 - 1 - k, color7);
                        }
                    }
                }
                else {
                    if (n3 == 1) {
                        final Image a = this.a(this.do, this.try[n * 3 + n2]);
                        if (a != null) {
                            this.checkImage(a, this);
                            final int width = a.getWidth(this);
                            final int height = a.getHeight(this);
                            if (width != -1 && height != -1) {
                                if (vector != null && image == a) {
                                    vector.addElement(new Rectangle(int1[n5], int1[n5 + 1], int1[n5 + 2], int1[n5 + 3]));
                                }
                                else {
                                    final int n18 = (n7 - height) / 2 + n13;
                                    final int n19 = int1[n5 + 35 + n2];
                                    switch (((n19 & 0x10) != 0x0) ? 4 : (n19 & 0x3)) {
                                        case 0: {
                                            graphics.drawImage(a, n12 + n13, n18, this);
                                            break;
                                        }
                                        default: {
                                            graphics.drawImage(a, (n6 - width) / 2 + n13, n18, this);
                                            break;
                                        }
                                        case 2: {
                                            graphics.drawImage(a, n6 - width - n12 + n13, n18, this);
                                            break;
                                        }
                                        case 3: {
                                            graphics.drawImage(a, n12, n12, n6 - 2 * n12, n7 - 2 * n12, this);
                                            break;
                                        }
                                        case 4: {
                                            graphics.drawImage(a, int1[n5 + 41 + n2] - width / 2, int1[n5 + 44 + n2] - height / 2, this);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        return;
                    }
                    if (n3 == 2) {
                        final String s = this.L[n * 19 + 3 + n2];
                        if (s == null || vector != null) {
                            return;
                        }
                        Image image2 = null;
                        final Color color8 = this.m[n * 15 + 3 + n2];
                        Graphics graphics2;
                        if (b) {
                            graphics2 = graphics;
                            graphics2.setColor(color8);
                        }
                        else {
                            if (n14 <= 0) {
                                return;
                            }
                            array = new int[n14];
                            image2 = this.createImage(n6, n7);
                            graphics2 = image2.getGraphics();
                        }
                        final int n20 = int1[n5 + 23 + n2];
                        String s2 = null;
                        switch (int1[n5 + 17 + n2]) {
                            case 0:
                            case 7: {
                                s2 = "Dialog";
                                break;
                            }
                            default: {
                                s2 = "SansSerif";
                                break;
                            }
                            case 2:
                            case 5: {
                                s2 = "Serif";
                                break;
                            }
                            case 3:
                            case 6: {
                                s2 = "Monospaced";
                                break;
                            }
                            case 8: {
                                if (this.g) {
                                    s2 = "SansSerif";
                                    break;
                                }
                                s2 = "ZapfDingbats";
                                break;
                            }
                        }
                        final Font font = new Font(s2, n20 & 0x3, int1[n5 + 20 + n2]);
                        graphics2.setFont(font);
                        final FontMetrics fontMetrics = graphics2.getFontMetrics(font);
                        int height2 = fontMetrics.getHeight();
                        int ascent = fontMetrics.getAscent();
                        if (this.i) {
                            height2 = (height2 * 17 + 10) / 20;
                            ascent = (ascent * 17 + 10) / 20;
                        }
                        int index = -2;
                        int n21 = 1;
                        while ((index = s.indexOf("\\n", index + 2)) != -1) {
                            ++n21;
                        }
                        final int n22 = height2 * n21;
                        final int n23 = (int1[n5 + 26 + n2] == 3) ? int1[n5 + 32 + n2] : ((n7 - n22) / 2 + ascent);
                        final int n24 = n20 & 0x18;
                        final int n25 = (n24 == 8) ? 1 : -1;
                        final int[] array2 = { -1, 1, 0 };
                        final Color[] array3 = { color2, color3, color8 };
                        for (int l = (n24 == 0) ? 2 : 0; l < 3; ++l) {
                            final Color color9 = array3[l];
                            final int n26 = array2[l] * n25;
                            if (!b) {
                                graphics2.setColor(Color.black);
                                graphics2.fillRect(0, 0, n6, n7);
                            }
                            graphics2.setColor(b ? color9 : Color.white);
                            int index2 = 0;
                            for (int n27 = n23, n28 = 0; n28 < n21; ++n28, n27 += height2) {
                                final int n29 = index2;
                                index2 = s.indexOf("\\n", n29);
                                this.a((index2 == -1) ? s.substring(n29) : s.substring(n29, index2), graphics2, n27 + n13, n26, int1, n5, n2, fontMetrics, n6, n13, n12, n20);
                                index2 += 2;
                            }
                            if (!b) {
                                final int[] array4 = new int[n14];
                                final PixelGrabber pixelGrabber = new PixelGrabber(image2, 0, 0, n6, n7, array4, 0, n6);
                                try {
                                    pixelGrabber.grabPixels();
                                }
                                catch (Exception ex) {}
                                final int n30 = color9.getRGB() | 0xFF000000;
                                for (int n31 = 0; n31 < n14; ++n31) {
                                    if ((array4[n31] & 0xFF) > 127) {
                                        array[n31] = n30;
                                    }
                                }
                            }
                        }
                        if (!b) {
                            graphics2.dispose();
                            image2.flush();
                        }
                    }
                }
                if (b) {
                    return;
                }
                u[n4] = this.createImage(new MemoryImageSource(n6, n7, array, 0, n6));
            }
            if (graphics != null) {
                graphics.drawImage(u[n4], 0, 0, this);
            }
        }
        catch (Exception ex2) {}
    }
    
    void a(final String s, final Graphics graphics, final int n, final int n2, final int[] array, final int n3, final int n4, final FontMetrics fontMetrics, final int n5, final int n6, final int n7, final int n8) {
        final int stringWidth = fontMetrics.stringWidth(s);
        int n9 = 0;
        switch (array[n3 + 26 + n4]) {
            case 0: {
                n9 = n6 + n7;
                break;
            }
            default: {
                n9 = (n5 - stringWidth) / 2 + n6;
                break;
            }
            case 2: {
                n9 = n5 - stringWidth - n7 + n6;
                break;
            }
            case 3: {
                n9 = array[n3 + 29 + n4];
                break;
            }
        }
        this.a(graphics, s, n9 + n2, n + n2, stringWidth, n8, array[n3 + 20 + n4]);
    }
    
    void a(final Graphics graphics, final String s, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawString(s, n, n2);
        if ((n4 & 0x4) != 0x0) {
            final int n6 = n2 + n5 / 6;
            graphics.drawLine(n, n6, n + n3, n6);
        }
    }
    
    void a(final Graphics graphics) {
        this.buildImage(null, null, graphics.getClipRect());
        graphics.drawImage(this.byte, 0, 0, this);
        graphics.dispose();
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics);
        this.if |= 0x1;
    }
    
    public void buildImage(final Image image, final Vector vector, final Rectangle rectangle) {
        final int a = this.A;
        final int v = this.v;
        final Graphics graphics = this.byte.getGraphics();
        graphics.setColor(this.k);
        if (rectangle == null) {
            graphics.fillRect(0, 0, a, v);
        }
        else {
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        final Image a2 = this.a(this.do, this.E);
        if (a2 != null) {
            final int width = a2.getWidth(this);
            final int height = a2.getHeight(this);
            if (width != -1 && height != -1) {
                if (vector != null && image != null && a2 == image) {
                    vector.addElement(new Rectangle(0, 0, a, v));
                }
                else {
                    switch (this.J) {
                        case 0: {
                            graphics.drawImage(a2, (a - width) / 2, (v - height) / 2, this);
                            break;
                        }
                        case 1: {
                            graphics.drawImage(a2, 0, 0, a, v, this);
                            break;
                        }
                        case 2: {
                            for (int i = this.D.y; i < v; i += height) {
                                for (int j = this.D.x; j < a; j += width) {
                                    graphics.drawImage(a2, j, i, width, height, this);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (this.H >= 0) {
            final int[] int1 = this.int;
            for (int k = this.F - 1; k >= 0; --k) {
                final int n = k * 50;
                if (int1[n + 4] != 0 && (rectangle == null || rectangle.intersects(this.getBtnRect(k)))) {
                    final int n2 = int1[n + 50 - 1];
                    final int n3 = int1[n + 38 + n2];
                    final int n4 = int1[n + 2];
                    final int n5 = int1[n + 3];
                    final Graphics graphics2 = this.byte.getGraphics();
                    graphics2.translate(int1[n], int1[n + 1]);
                    graphics2.clipRect(-n3, -n3, n4 + n3 * 2, n5 + n3 * 2);
                    for (int l = 0; l < 3; ++l) {
                        this.drawBtnImage(graphics2, k, n2, l, image, vector);
                    }
                    if (n3 > 0) {
                        graphics2.setColor(this.m[k * 15 + 6 + n2]);
                        for (int n6 = 1; n6 <= n3; ++n6) {
                            graphics2.drawRect(-n6, -n6, n4 + n6 * 2 - 1, n5 + n6 * 2 - 1);
                        }
                    }
                    if (n3 > 0) {
                        graphics2.setColor(this.m[k * 15 + 6 + n2]);
                        for (int n7 = 1; n7 <= n3; ++n7) {
                            graphics2.drawRect(-n7, -n7, n4 + n7 * 2 - 1, n5 + n7 * 2 - 1);
                        }
                    }
                    graphics2.dispose();
                }
            }
        }
        if (!this.f) {
            final String s = "UNREGISTERED";
            final int stringWidth = this.getFontMetrics(graphics.getFont()).stringWidth(s);
            graphics.setColor(Color.black);
            graphics.fillRect((a - stringWidth) / 2 - 10, v / 2 - 15, stringWidth + 20, 30);
            graphics.setColor(Color.white);
            graphics.drawRect((a - stringWidth) / 2 - 11, v / 2 - 16, stringWidth + 21, 31);
            graphics.drawString(s, (a - stringWidth) / 2, v / 2 + 4);
        }
        graphics.dispose();
    }
    
    public boolean callJSObject(final String s) {
        boolean b = false;
        try {
            final Class<?> forName = Class.forName("netscape.javascript.JSObject");
            final Method[] methods = forName.getMethods();
            Method method = null;
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().compareTo("getWindow") == 0) {
                    method = methods[i];
                }
            }
            final Object[] array = { this };
            final Object[] array2 = { s };
            Method method2 = null;
            final Object invoke = method.invoke(forName, array);
            final Method[] methods2 = invoke.getClass().getMethods();
            for (int j = 0; j < methods2.length; ++j) {
                if (methods2[j].getName().compareTo("eval") == 0) {
                    method2 = methods2[j];
                }
            }
            method2.invoke(invoke, array2);
            b = true;
        }
        catch (Exception ex) {}
        if (!b) {
            try {
                String nextToken = "";
                Object[] array3 = new Object[0];
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "(");
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                }
                if (stringTokenizer.hasMoreTokens()) {
                    String s2 = stringTokenizer.nextToken();
                    final int lastIndex = s2.lastIndexOf(41);
                    if (lastIndex > 0) {
                        s2 = s2.substring(0, lastIndex - 1);
                    }
                    else if (lastIndex == 0) {
                        s2 = "";
                    }
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(s2, ",");
                    final int countTokens = stringTokenizer2.countTokens();
                    final String[] array4 = new String[countTokens];
                    int n = 0;
                    while (stringTokenizer2.hasMoreTokens()) {
                        array4[n] = stringTokenizer2.nextToken();
                        ++n;
                    }
                    final String[] array5 = new String[countTokens];
                    int n2 = 0;
                    for (int k = 0; k < array4.length; ++k) {
                        final String trim = array4[k].trim();
                        if (trim.length() == 0) {
                            array5[n2] = "";
                            ++n2;
                        }
                        else {
                            final char char1 = trim.charAt(0);
                            final char char2 = trim.charAt(trim.length() - 1);
                            if ((char1 == '\"' && char2 == '\"') || (char1 == '\'' && char2 == '\'')) {
                                array5[n2] = trim.substring(1, trim.length() - 1);
                                ++n2;
                            }
                            else if (char1 != '\"' && char1 != '\'') {
                                array5[n2] = trim;
                                ++n2;
                            }
                            else if (k == array4.length - 1) {
                                array5[n2] = trim.substring(1);
                                ++n2;
                            }
                            else {
                                array4[k + 1] = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(array4[k]))).append(",").append(array4[k + 1])));
                            }
                        }
                    }
                    array3 = new Object[n2];
                    for (int l = 0; l < n2; ++l) {
                        array3[l] = array5[l];
                    }
                }
                JSObject.getWindow((Applet)this).call(nextToken, array3);
                b = true;
            }
            catch (Exception ex2) {}
        }
        return b;
    }
    
    public void callJS(String concat) {
        if (this.p && this.K) {
            return;
        }
        if (!concat.endsWith(";")) {
            concat = String.valueOf(String.valueOf(concat)).concat(";");
        }
        this.callJSObject(concat);
    }
    
    int a(final int n, final int n2) {
        final int[] int1 = this.int;
        for (int i = 0; i < this.F; ++i) {
            final int n3 = i * 50;
            if (int1[n3 + 4] != 0) {
                if (this.h != null) {
                    if (i == this.q) {
                        continue;
                    }
                    if ((int1[n3 + 6] & 0x1) != 0x0) {
                        continue;
                    }
                }
                final Rectangle btnRect = this.getBtnRect(i);
                final int n4 = n - btnRect.x;
                final int n5 = n2 - btnRect.y;
                if (n4 >= 0 && n4 < btnRect.width && n5 >= 0 && n5 < btnRect.height) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.H >= 0) {
            this.mouseMove(event, n, n2);
            Rectangle a = null;
            final int a2 = this.a(n, n2);
            final int[] int1 = this.int;
            if (a2 != -1 && (this.q == -1 || this.q == a2)) {
                final int n3 = a2 * 50;
                if (int1[n3 + 50 - 1] != 2) {
                    int1[n3 + 50 - 1] = 2;
                    this.if(a2);
                    this.if(a2, 2);
                    this.h = (((int1[n3 + 6] & 0x1) != 0x0) ? new Point(n - int1[n3], n2 - int1[n3 + 1]) : null);
                    a = this.a(a2, a, 2);
                }
                this.q = a2;
            }
            if (a != null) {
                this.paintRect(a);
            }
        }
        this.long = this.a(n, n2);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.H >= 0) {
            this.mouseDrag(event, n, n2);
            boolean b = false;
            final int a = this.a(n, n2);
            if (this.q >= 0) {
                final int[] int1 = this.int;
                final int n3 = this.q * 50;
                final int n4 = int1[n3 + 4];
                int1[n3 + 4] = 1;
                final int a2 = this.a(n, n2);
                int1[n3 + 4] = n4;
                Rectangle rectangle = this.a(this.q, null, 3);
                if (a == this.q || this.h != null) {
                    if (this.h != null && this.long >= 0) {
                        int1[n3 + 4] = 0;
                        rectangle = this.getBtnRect(this.long);
                        int1[this.long * 50 + 50 - 1] = 1;
                        this.if(this.long);
                        this.if(this.q, 0);
                        b = true;
                    }
                    else {
                        switch (int1[n3 + 7]) {
                            default: {
                                int1[n3 + 50 - 1] = 1;
                                this.long = this.q;
                                this.if(this.q);
                                if (this.h == null) {
                                    this.if(this.q, 0);
                                    break;
                                }
                                break;
                            }
                            case 0: {
                                int1[n3 + 5] = 1 - int1[n3 + 5];
                                int1[n3 + 50 - 1] = ((int1[n3 + 5] == 1) ? 2 : 1);
                                rectangle = this.a(this.q, rectangle, 4 + ((int1[n3 + 5] != 1) ? 1 : 0));
                                this.if(this.q);
                                if (int1[n3 + 5] == 0) {
                                    this.if(a, 0);
                                    this.long = this.q;
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                if (this.j != this.q) {
                                    if (this.j >= 0) {
                                        final int n5 = this.j * 50;
                                        int1[n5 + 50 - 1] = 0;
                                        this.if(this.j);
                                        int1[n5 + 5] = 0;
                                        rectangle = this.a(this.j, this.a(this.j, rectangle, 5), 0);
                                    }
                                    this.j = this.q;
                                    int1[n3 + 5] = 1;
                                    rectangle = this.a(this.j, rectangle, 4);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    final Rectangle a3 = this.a(this.q, rectangle, int1[n3 + 50 - 1]);
                    if (a3 != null) {
                        this.paintRect(a3);
                    }
                }
                if (b || a2 == this.q) {
                    final int n6 = this.q * 19;
                    final String s = this.L[n6 + 9];
                    final String s2 = this.L[n6 + 10];
                    if (s != null) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                        final StringTokenizer stringTokenizer2 = (s2 != null) ? new StringTokenizer(s2, ",") : null;
                        while (stringTokenizer.hasMoreTokens()) {
                            String s3 = stringTokenizer.nextToken().trim();
                            if (s3.toLowerCase().startsWith("javascript:")) {
                                this.callJS(s3.substring(s3.indexOf(58) + 1).trim());
                            }
                            else {
                                final int index;
                                if (this.K && !this.goto && (index = s3.indexOf(35)) >= 0) {
                                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3.substring(0, index + 1)))).append("#").append(s3.substring(index + 1))));
                                }
                                URL url = null;
                                try {
                                    url = new URL(this.getDocumentBase(), s3);
                                }
                                catch (Exception ex) {}
                                if (url == null) {
                                    continue;
                                }
                                final AppletContext appletContext = this.getAppletContext();
                                if (stringTokenizer2 != null && stringTokenizer2.hasMoreTokens()) {
                                    appletContext.showDocument(url, stringTokenizer2.nextToken().trim());
                                }
                                else {
                                    if (s3.indexOf(".htm") != -1) {
                                        this.SetCursor(3);
                                    }
                                    appletContext.showDocument(url);
                                }
                            }
                        }
                    }
                }
            }
            this.h = null;
            this.q = -1;
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.H >= 0 && this.q != -1) {
            Rectangle a = null;
            int n3 = this.q * 50;
            final int[] int1 = this.int;
            if (this.h != null) {
                final int n4 = int1[n3 + 38 + int1[n3 + 50 - 1]];
                final Rectangle rectangle = new Rectangle(int1[n3] - n4, int1[n3 + 1] - n4, int1[n3 + 2] + n4 + n4, int1[n3 + 3] + n4 + n4);
                int1[n3] = n - this.h.x;
                int1[n3 + 1] = n2 - this.h.y;
                Rectangle rectangle2 = new Rectangle(int1[n3] - n4, int1[n3 + 1] - n4, int1[n3 + 2] + n4 + n4, int1[n3 + 3] + n4 + n4).union(rectangle);
                final int a2 = this.a(n, n2);
                if (this.long >= 0 && this.long != a2 && a2 != this.q) {
                    n3 = this.long * 50;
                    if (int1[n3 + 50 - 1] == 2 && int1[n3 + 5] == 0) {
                        int1[n3 + 50 - 1] = 0;
                        this.if(this.long);
                        rectangle2 = rectangle2.union(this.getBtnRect(this.long));
                    }
                }
                if (a2 != this.long && a2 != -1) {
                    n3 = a2 * 50;
                    if (int1[n3 + 50 - 1] != 2) {
                        int1[n3 + 50 - 1] = 2;
                        this.if(a2);
                        rectangle2 = rectangle2.union(this.getBtnRect(a2));
                        this.if(a2, 2);
                    }
                }
                this.long = a2;
                this.paintRect(rectangle2);
            }
            else {
                final int a3 = this.a(n, n2);
                this.mouseDown(event, n, n2);
                if (a3 != this.q && int1[n3 + 5] == 0 && int1[n3 + 50 - 1] == 2 && this.q != this.j) {
                    int1[n3 + 50 - 1] = 0;
                    this.if(this.q);
                    a = this.a(this.q, a, 0);
                }
            }
            if (a != null) {
                this.paintRect(a);
            }
            if ((this.goto || this.i || this.g) && (int1[n3 + 6] & 0x2) == 0x0) {
                this.SetCursor((this.long < 0) ? 0 : 12);
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.H >= 0) {
            Rectangle rectangle = null;
            final int a = this.a(n, n2);
            final int[] int1 = this.int;
            if (this.long != -1 && this.long != a) {
                final int n3 = this.long * 50;
                final int n4 = n3 + 50 - 1;
                final int n5 = int1[n4];
                if (int1[n4] == 1 && int1[n3 + 5] == 0) {
                    int1[n4] = 0;
                    this.if(this.long);
                    rectangle = this.a(this.long, rectangle, 0);
                }
                if ((this.goto || this.i || this.g) && (int1[n3 + 6] & 0x2) == 0x0) {
                    this.SetCursor(0);
                }
                this.long = -1;
            }
            if (a < 0) {
                this.long = a;
                this.showStatus("");
            }
            else if (a != this.long) {
                final int n6 = a * 50;
                final int n7 = n6 + 50 - 1;
                final int n8 = int1[n7];
                final String s = this.L[a * 19 + 12];
                this.showStatus((s != null) ? s : "");
                if (int1[n7] == 0 && int1[n6 + 5] == 0) {
                    int1[n7] = 1;
                    this.if(a);
                    this.if(a, 1);
                    rectangle = this.a(a, rectangle, 1);
                }
                if ((this.goto || this.i || this.g) && (int1[n6 + 6] & 0x2) == 0x0) {
                    this.SetCursor(12);
                }
                this.long = a;
            }
            if (rectangle != null) {
                this.paintRect(rectangle);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.mouseMove(event, Integer.MIN_VALUE, Integer.MIN_VALUE);
        return true;
    }
    
    public void SetCursor(final int cursor) {
        if (this.char != null) {
            this.char.setCursor(cursor);
        }
    }
    
    public void start() {
        this.paintAll();
        this.SetCursor(0);
    }
    
    public void stop() {
        System.gc();
    }
    
    public void destroy() {
        this.try = null;
        this.u = null;
        this.int = null;
        this.z = null;
        this.case = null;
        this.m = null;
        this.L = null;
        this.B = null;
        this.null = null;
        this.l = null;
        this.char = null;
        this.byte = null;
        this.c = null;
        this.h = null;
        this.k = null;
        this.D = null;
        this.void = null;
        this.new = null;
        this.M = null;
        this.do = null;
        this.G = null;
        this.o = null;
        this.w = null;
        System.gc();
    }
    
    public void run() {
        final int[] int1 = this.int;
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.E = ((this.J >= 0) ? this.a(this.new, this.A, this.v, true, this.J == 3, mediaTracker) : -1);
        for (int i = 0; i < this.F; ++i) {
            final int n = i * 50;
            for (int j = 0; j < 3; ++j) {
                final int n2 = int1[n + 8 + j];
                final int n3 = int1[n + 11 + j];
                final int n4 = (n2 > 1) ? n3 : false;
                this.try[i * 3 + j] = this.a(this.L[i * 19 + 13 + j], int1[n + 2] - 2 * n4, int1[n + 3] - 2 * n4, j != 0 || int1[n + 4] == 0, (int1[n + 35 + j] & 0x3) == 0x3, mediaTracker);
            }
        }
        this.H = 1;
        this.paintAll();
        for (int k = 0; k < this.F; ++k) {
            for (int l = 0; l < 3; ++l) {
                this.drawBtnImage(null, k, l, 0, null, null);
                this.drawBtnImage(null, k, l, 2, null, null);
            }
        }
        this.performAction(this.l);
        for (int n5 = 0; n5 < this.F; ++n5) {
            for (int n6 = 0; n6 < 3; ++n6) {
                this.case[n5 * 9 + n6] = this.if(this.L[n5 * 19 + n6]);
            }
        }
        this.paintAll();
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {}
        this.paintAll();
        while (this.goto) {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex2) {}
            if (this.if == 1) {
                this.paintAll();
                this.if = 0;
            }
        }
    }
    
    void a(final int n) {
        final int n2 = n * 6;
        for (int i = 0; i < 6; ++i) {
            if (this.u[n2 + i] != null) {
                this.u[n2 + i].flush();
                this.u[n2 + i] = null;
            }
        }
    }
    
    Rectangle a(final String s, final int n, final int n2, final Rectangle rectangle, final boolean b) {
        Rectangle rectangle2 = rectangle;
        final int index = s.indexOf(42);
        for (int i = 0; i < this.F; ++i) {
            final String s2 = this.L[i * 19 + 11];
            if ((index >= 0 && s2.regionMatches(0, s, 0, index)) || s2.equals(s)) {
                rectangle2 = this.a(rectangle2, this.getBtnRect(i));
                this.int[i * 50 + n] = n2;
                if (b) {
                    this.a(i);
                }
                this.if(i);
                if (n == 49) {
                    final int[] int1 = this.int;
                    final int n3 = i * 50;
                    if (int1[n3 + 7] == 0) {
                        int1[n3 + 5] = ((n2 == 2) ? 1 : 0);
                    }
                    else if (int1[n3 + 7] == 1) {
                        if ((n2 == 2 && this.j >= 0 && this.j != i) || (n2 == 0 && i == this.j)) {
                            final int n4 = this.j * 50;
                            int1[n4 + 50 - 1] = 0;
                            this.if(this.j);
                            int1[n4 + 5] = 0;
                            rectangle2 = rectangle2.union(this.getBtnRect(this.j));
                            this.j = -1;
                        }
                        if (n2 == 2) {
                            int1[n3 + 5] = 1;
                            this.j = i;
                        }
                    }
                }
            }
        }
        if (rectangle == null) {
            this.paintRect(rectangle2);
        }
        return rectangle2;
    }
    
    Rectangle a(final String s, final int n, final String s2, final Rectangle rectangle, final boolean b) {
        Rectangle a = rectangle;
        final int index = s.indexOf(42);
        for (int i = 0; i < this.F; ++i) {
            final String s3 = this.L[i * 19 + 11];
            if ((index >= 0 && s3.regionMatches(0, s, 0, index)) || s3.equals(s)) {
                a = this.a(a, this.getBtnRect(i));
                this.L[i * 19 + n] = s2;
                if (b) {
                    this.a(i);
                }
                this.if(i);
            }
        }
        if (rectangle == null) {
            this.paintRect(a);
        }
        return a;
    }
    
    public void setState(final String s, final int n) {
        this.a(s, 49, n, null, true);
    }
    
    public void setText(final String s, final int n, final String s2) {
        this.a(s, 3 + n, s2, null, true);
    }
    
    public void setVisible(final String s, final int n) {
        this.a(s, 4, n, null, true);
    }
    
    public int getAttrib(final String s, final int n) {
        for (int i = 0; i < this.F; ++i) {
            if (s.equals(this.L[i * 19 + 11])) {
                return this.int[i * 50 + n];
            }
        }
        return -1;
    }
    
    public int getState(final String s) {
        return this.getAttrib(s, 49);
    }
    
    public int getVisible(final String s) {
        return this.getAttrib(s, 4);
    }
    
    public void setRect(final String s, final int n, final int n2, final int n3, final int n4) {
        for (int i = 0; i < this.F; ++i) {
            if (s.equals(this.L[i * 19 + 11])) {
                final Rectangle btnRect = this.getBtnRect(i);
                final int[] int1 = this.int;
                final int n5 = i * 50;
                int1[n5] = n;
                int1[n5 + 1] = n2;
                int1[n5 + 2] = n3;
                int1[n5 + 3] = n4;
                this.a(i);
                this.paintRect(btnRect.union(this.getBtnRect(i)));
                break;
            }
        }
    }
    
    public void performAction(final String s) {
        this.paintRect(this.a(s, null));
    }
    
    String[] for() {
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        final String[] array = new String[fontList.length + 1];
        for (int i = 0; i < fontList.length; ++i) {
            array[i] = fontList[i];
        }
        array[array.length - 1] = "Arial";
        return array;
    }
}
