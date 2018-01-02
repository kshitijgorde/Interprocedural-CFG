import java.net.InetAddress;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.Hashtable;
import java.io.OutputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.util.jar.Pack200;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import sign.signlink;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.zip.CRC32;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class loader extends Applet implements Runnable, KeyListener
{
    private static CRC32 j;
    private Image l;
    private static final Color e;
    private static final Color f;
    private static final Font g;
    private static final Color b;
    private static final Color h;
    private static boolean a;
    private boolean m;
    private boolean o;
    private boolean k;
    private Applet d;
    static /* synthetic */ Class i;
    static /* synthetic */ Class c;
    public static boolean n;
    private static final String[] z;
    
    public loader() {
        final boolean n = loader.n;
        this.addKeyListener(this);
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        if (a.b) {
            loader.n = !n;
        }
    }
    
    public synchronized void init() {
        new Thread(this).start();
    }
    
    private static String b(String trim) {
        final boolean n = loader.n;
        final String s = trim;
        if (n || s != null) {
            final int length = s.length();
            if (!n) {
                if (length == 0) {
                    return null;
                }
                trim = trim.trim();
                trim.length();
            }
            return (length == 0) ? null : trim;
        }
        return null;
    }
    
    public final byte[] a(final URL url, final String s, final int i) throws IOException {
        final boolean n = loader.n;
        final InputStream openStream = url.openStream();
        if (openStream == null) {
            return null;
        }
        final byte[] array = new byte[i];
        int n2 = 0;
        int n3 = -1;
        while (i != n2) {
            final int n5;
            int n4 = n5 = i - n2;
            if (!n) {
                if (n5 > 1024) {
                    n4 = 1024;
                }
                openStream.read(array, n2, n4);
            }
            final int n6 = n5;
            int n8;
            final int n7 = n8 = n6;
            if (!n) {
                if (n7 < 0) {
                    return null;
                }
                final int n9;
                n8 = (n9 = n6);
            }
            final int n10 = n4;
            if (!n) {
                if (n7 > n10) {
                    return null;
                }
                n8 = n2;
            }
            n2 = n8 + n10;
            final int n11 = (int)(n2 * 100L / i);
            int n13;
            final int n12 = n13 = n3;
            Label_0212: {
                if (!n) {
                    if (n12 == n11) {
                        break Label_0212;
                    }
                    final boolean a;
                    n13 = ((a = this.a(loader.z[14] + s + loader.z[15] + n11 + "%", signlink.name + loader.z[13], 0)) ? 1 : 0);
                }
                if (!n) {
                    if (n12 == 0) {
                        break Label_0212;
                    }
                    n13 = n11;
                }
                n3 = n13;
            }
            if (n) {
                break;
            }
        }
        return array;
    }
    
    public void run() {
        final boolean n = loader.n;
        try {
            final SecurityManager securityManager2;
            final SecurityManager securityManager = securityManager2 = System.getSecurityManager();
            Label_0035: {
                if (!n) {
                    if (securityManager2 == null) {
                        break Label_0035;
                    }
                    securityManager.checkCreateClassLoader();
                }
                securityManager2.checkConnect(this.getCodeBase().getHost(), 80);
            }
            synchronized (this) {
                final Applet d = this.d;
                if (!n && d != null) {
                    this.d = null;
                    try {
                        Label_0078: {
                            try {
                                d.stop();
                                break Label_0078;
                            }
                            finally {
                                d.destroy();
                            }
                        }
                        goto Label_0083;
                    }
                    catch (Exception ex2) {}
                    goto Label_0083;
                }
            }
            int int1 = 0;
            try {
                int1 = Integer.parseInt(this.getParameter(loader.z[0]));
            }
            catch (Exception ex3) {}
            int int2 = 0;
            try {
                int2 = Integer.parseInt(this.getParameter(loader.z[10]));
            }
            catch (Exception ex4) {}
            int int3 = 0;
            try {
                int3 = Integer.parseInt(this.getParameter(loader.z[8]));
            }
            catch (Exception ex5) {}
            final int n2 = int1;
            final int n3 = int2;
            final String parameter = this.getParameter(loader.z[7]);
            final String parameter2 = this.getParameter(loader.z[4]);
            final int n4 = int3;
            if (!n && n4 != 0) {}
            signlink.init(n2, n3, this, parameter, parameter2, n4 != 0);
            if (!n) {
                if (securityManager != null) {
                    securityManager.checkConnect(signlink.host, 80);
                    securityManager.checkConnect(signlink.host, 43594);
                    final SecurityManager securityManager3 = securityManager;
                    final String host = signlink.host;
                    final int[] ports = signlink.ports;
                    int n6;
                    final int n5 = n6 = int3;
                    Label_0262: {
                        Label_0261: {
                            if (!n) {
                                if (n5 < 0) {
                                    break Label_0261;
                                }
                                final int n7;
                                n6 = (n7 = int3);
                            }
                            if (!n) {
                                if (n5 >= signlink.ports.length) {
                                    break Label_0261;
                                }
                                n6 = int3;
                            }
                            break Label_0262;
                        }
                        n6 = 0;
                    }
                    securityManager3.checkConnect(host, ports[n6]);
                }
                loader.a = signlink.oldgame;
            }
            int n8 = 0;
            try {
                if (Class.forName(loader.z[9]) != null) {
                    n8 = 1;
                }
            }
            catch (Throwable t) {}
            final StringBuffer append = new StringBuffer().append(loader.z[12]);
            final int n9 = n8;
            if (!n && n9 == 0) {}
            final File file = signlink.getFile(append.append(n9).toString());
            final int n10 = n8;
            if (!n && n10 == 0) {}
            final int n11 = n10;
            final int n12 = n8;
            if (!n && n12 == 0) {}
            final int n13 = n12;
            final URL[] array = { new URL(this.getCodeBase(), loader.z[6] + n11 + ((n8 != 0) ? loader.z[1] : loader.z[3])) };
            final File file2 = file;
            byte[] a = (byte[])((!n && file2 == null) ? null : a(file2));
            int n14 = 0;
            final CRC32 j = loader.j;
            while (true) {
                Label_0580: {
                    final int length;
                    final int n15;
                    Label_0566: {
                        if (a != null) {
                            length = a.length;
                            n15 = n13;
                            if (n) {
                                break Label_0566;
                            }
                            if (length == n15) {
                                synchronized (j) {
                                    j.reset();
                                    j.update(a, 0, n13);
                                    final int n16 = (int)j.getValue();
                                    if (!n && n11 == n16) {
                                        // monitorexit(j)
                                        if (n) {
                                            goto Label_0536;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        final int n17 = n14;
                        if (!n && n17 == 0) {
                            break Label_0580;
                        }
                    }
                    if (length < n15) {}
                    try {
                        Thread.sleep(2000L);
                    }
                    catch (Exception ex6) {}
                }
                a = this.a(array[(int)(Math.random() * array.length)], loader.z[2], n13);
                ++n14;
            }
            final File file3 = file;
            if (n || file3 != null) {
                a(file3, a);
            }
            synchronized (this) {
                this.l = null;
                if (n || this.m) {
                    return;
                }
                final a a3;
                final a a2 = a3 = new a(a((n8 != 0) ? b(a) : a));
                final Class i = loader.i;
                if (!n) {
                    if (i == null) {
                        loader.i = a(loader.z[11]);
                    }
                    else {
                        final Class k = loader.i;
                    }
                }
                a3.a(i);
                final a a4 = a2;
                final Class c = loader.c;
                if (!n) {
                    if (c == null) {
                        loader.c = a(loader.z[5]);
                    }
                    else {
                        final Class c2 = loader.c;
                    }
                }
                a4.a(c);
                final Applet d2 = (Applet)a2.loadClass(loader.z[6]).newInstance();
                d2.init();
                final boolean l = this.k;
                if (!n) {
                    if (l) {
                        d2.start();
                    }
                    if (n) {
                        return;
                    }
                    final boolean o = this.o;
                }
                if (l) {
                    d2.stop();
                }
                this.d = d2;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static byte[] b(final byte[] array) throws IOException {
        final byte[] array2 = new byte[array.length + 2];
        array2[0] = 31;
        array2[1] = -117;
        System.arraycopy(array, 0, array2, 2, array.length);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Pack200.newUnpacker().unpack(new GZIPInputStream(new ByteArrayInputStream(array2)), new JarOutputStream(byteArrayOutputStream));
        return byteArrayOutputStream.toByteArray();
    }
    
    private static Hashtable a(byte[] array) throws IOException {
        final boolean n = loader.n;
        final ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(array, 0, array.length));
        final Hashtable<String, byte[]> hashtable = new Hashtable<String, byte[]>();
        array = new byte[4096];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
    Label_0049:
        while (true) {
            while (true) {
                final ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null && !n) {
                    break;
                }
                final String name;
                final String s = name = nextEntry.getName();
                String s2 = null;
                while (s2 != null) {
                    s2 = s;
                    if (!n) {
                        final int length;
                        int endsWith = length = s2.length();
                    Label_0087:
                        while (true) {
                            if (!n) {
                                final int n2;
                                if (n2 == 0) {
                                    continue Label_0049;
                                }
                                endsWith = (s.endsWith("/") ? 1 : 0);
                            }
                            if (endsWith == 0 || n) {
                                byteArrayOutputStream.reset();
                                do {
                                    final int read = zipInputStream.read(array, 0, 4096);
                                    if (read < 0) {
                                        break;
                                    }
                                    final int n2 = endsWith = read;
                                    if (n) {
                                        continue Label_0087;
                                    }
                                    if (n2 > 4096 && !n) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(array, 0, read);
                                } while (!n);
                                break;
                            }
                            continue Label_0049;
                        }
                        hashtable.put(s, byteArrayOutputStream.toByteArray());
                        if (n) {
                            break Label_0049;
                        }
                        break;
                    }
                }
            }
            break;
        }
        return hashtable;
    }
    
    private static byte[] a(final File file) {
        final boolean n = loader.n;
        boolean b2;
        final boolean b = b2 = file.exists();
        if (!n) {
            if (!b) {
                return null;
            }
            final boolean file2;
            b2 = (file2 = file.isFile());
        }
        File file3 = null;
        Label_0044: {
            if (!n) {
                if (!b) {
                    return null;
                }
                file3 = file;
                if (n) {
                    break Label_0044;
                }
                b2 = file.canRead();
            }
            if (!b2) {
                return null;
            }
            file3 = file;
        }
        final long length = file3.length();
        long n3;
        final long n2 = n3 = lcmp(length, 0L);
        if (!n) {
            if (n2 < 0) {
                return null;
            }
            final int n4;
            n3 = (n4 = lcmp(length, 2147483647L));
        }
        if (!n) {
            if (n2 > 0) {
                return null;
            }
            n3 = (int)length;
        }
        long n5 = n3;
        try {
            final FileInputStream fileInputStream = new FileInputStream(file);
            byte[] array;
            try {
                array = new byte[n5];
                int n6 = 0;
                while (n5 != 0) {
                    final int read = fileInputStream.read(array, n6, (int)n5);
                    if (!n) {
                        int n8;
                        final int n7 = n8 = read;
                        Label_0137: {
                            if (!n) {
                                if (n7 < 0) {
                                    break Label_0137;
                                }
                                final int n9;
                                n8 = (n9 = read);
                            }
                            final int n10 = (int)n5;
                            if (!n) {
                                if (n7 > n10) {
                                    break Label_0137;
                                }
                                n6 += read;
                                n8 = (int)n5;
                            }
                            n5 = n8 - n10;
                            if (n) {
                                break;
                            }
                            continue;
                        }
                        array = null;
                        break;
                    }
                    return array;
                }
                return array;
            }
            finally {
                fileInputStream.close();
            }
            return array;
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }
    
    private static boolean a(final File file, final byte[] array) {
        final boolean n = loader.n;
        file.delete();
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(array, 0, array.length);
            }
            finally {
                fileOutputStream.close();
            }
        }
        catch (Exception ex) {}
        long n2;
        boolean b2;
        final boolean b = b2 = ((n2 = (file.exists() ? 1 : 0)) != 0L);
        if (!n) {
            if (!b) {
                return false;
            }
            final boolean b3;
            b2 = (b3 = ((n2 = (file.isFile() ? 1 : 0)) != 0L));
        }
        if (!n) {
            if (!b) {
                return false;
            }
            n2 = ((b2 = lcmp(file.length(), (long)array.length)) ? 1 : 0);
        }
        if (!n) {
            if (b2) {
                return false;
            }
            n2 = 1;
        }
        return n2 != 0L;
        n2 = 0;
        return n2 != 0L;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean n = loader.n;
        final Container parent = this.getParent();
        Container container2;
        final Container container = container2 = parent;
        if (!n) {
            if (container == null) {
                return;
            }
            final Container container3;
            container2 = (container3 = parent);
        }
        if (!n) {
            if (!(container instanceof KeyListener)) {
                return;
            }
            container2 = parent;
        }
        ((KeyListener)container2).keyPressed(keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public synchronized void start() {
        this.k = true;
        final Applet d = this.d;
        if (!loader.n) {
            if (d == null) {
                return;
            }
            final Applet d2 = this.d;
        }
        d.start();
    }
    
    public synchronized void stop() {
        this.o = true;
        final Applet d = this.d;
        if (!loader.n) {
            if (d == null) {
                return;
            }
            final Applet d2 = this.d;
        }
        d.stop();
    }
    
    public void paint(final Graphics graphics) {
        final Applet d = this.d;
        if (!loader.n) {
            if (d == null) {
                return;
            }
            final Applet d2 = this.d;
        }
        d.paint(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Applet d = this.d;
        if (!loader.n) {
            if (d == null) {
                return;
            }
            final Applet d2 = this.d;
        }
        d.update(graphics);
    }
    
    public synchronized void destroy() {
        this.m = true;
        final Applet d = this.d;
        if (!loader.n) {
            if (d == null) {
                return;
            }
            final Applet d2 = this.d;
        }
        d.destroy();
        this.d = null;
    }
    
    private static void a(final Graphics graphics) {
        try {
            Graphics graphics2 = graphics;
            if (!loader.n) {
                if (!(graphics instanceof Graphics2D)) {
                    return;
                }
                graphics2 = graphics;
            }
            final Graphics2D graphics2D = (Graphics2D)graphics2;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        }
        catch (Throwable t) {}
    }
    
    public boolean a(final String p0, final String p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       loader.n:Z
        //     3: istore          23
        //     5: iconst_0       
        //     6: istore          4
        //     8: aload_0        
        //     9: invokevirtual   java/awt/Component.getGraphics:()Ljava/awt/Graphics;
        //    12: astore          5
        //    14: aload           5
        //    16: iload           23
        //    18: ifne            32
        //    21: ifnonnull       30
        //    24: aload_0        
        //    25: invokevirtual   java/awt/Component.repaint:()V
        //    28: iconst_0       
        //    29: ireturn        
        //    30: aload           5
        //    32: invokestatic    loader.a:(Ljava/awt/Graphics;)V
        //    35: aload_0        
        //    36: invokevirtual   java/awt/Component.getSize:()Ljava/awt/Dimension;
        //    39: astore          6
        //    41: aload           6
        //    43: getfield        java/awt/Dimension.width:I
        //    46: istore          7
        //    48: iload           7
        //    50: iload           23
        //    52: ifne            66
        //    55: iconst_1       
        //    56: if_icmpge       61
        //    59: iconst_0       
        //    60: ireturn        
        //    61: aload           6
        //    63: getfield        java/awt/Dimension.height:I
        //    66: istore          8
        //    68: iload           8
        //    70: iload           23
        //    72: ifne            82
        //    75: iconst_1       
        //    76: if_icmpge       81
        //    79: iconst_0       
        //    80: ireturn        
        //    81: iload_3        
        //    82: iload           23
        //    84: ifne            98
        //    87: ifge            97
        //    90: iconst_0       
        //    91: istore_3       
        //    92: iload           23
        //    94: ifeq            111
        //    97: iload_3        
        //    98: bipush          100
        //   100: iload           23
        //   102: ifne            142
        //   105: if_icmple       111
        //   108: bipush          100
        //   110: istore_3       
        //   111: aload_1        
        //   112: invokestatic    loader.b:(Ljava/lang/String;)Ljava/lang/String;
        //   115: astore_1       
        //   116: aload_2        
        //   117: invokestatic    loader.b:(Ljava/lang/String;)Ljava/lang/String;
        //   120: astore_2       
        //   121: iload           7
        //   123: sipush          300
        //   126: isub           
        //   127: bipush          30
        //   129: isub           
        //   130: bipush          30
        //   132: isub           
        //   133: iconst_2       
        //   134: isub           
        //   135: iconst_2       
        //   136: isub           
        //   137: iconst_2       
        //   138: isub           
        //   139: iconst_2       
        //   140: isub           
        //   141: iconst_2       
        //   142: idiv           
        //   143: istore          9
        //   145: iload           8
        //   147: sipush          300
        //   150: isub           
        //   151: bipush          30
        //   153: isub           
        //   154: bipush          30
        //   156: isub           
        //   157: iconst_2       
        //   158: isub           
        //   159: iconst_2       
        //   160: isub           
        //   161: iconst_2       
        //   162: isub           
        //   163: iconst_2       
        //   164: isub           
        //   165: iconst_2       
        //   166: idiv           
        //   167: istore          10
        //   169: aload_0        
        //   170: dup            
        //   171: astore          12
        //   173: monitorenter   
        //   174: aload_0        
        //   175: getfield        loader.l:Ljava/awt/Image;
        //   178: astore          11
        //   180: iload           23
        //   182: ifne            210
        //   185: aload           11
        //   187: ifnonnull       207
        //   190: aload_0        
        //   191: aload_0        
        //   192: sipush          370
        //   195: sipush          370
        //   198: invokevirtual   java/awt/Component.createImage:(II)Ljava/awt/Image;
        //   201: dup_x1         
        //   202: putfield        loader.l:Ljava/awt/Image;
        //   205: astore          11
        //   207: aload           12
        //   209: monitorexit    
        //   210: goto            221
        //   213: astore          13
        //   215: aload           12
        //   217: monitorexit    
        //   218: aload           13
        //   220: athrow         
        //   221: aload           11
        //   223: invokevirtual   java/awt/Image.getGraphics:()Ljava/awt/Graphics;
        //   226: astore          12
        //   228: aload           12
        //   230: iload           23
        //   232: ifne            271
        //   235: ifnull          658
        //   238: aload           12
        //   240: invokestatic    loader.a:(Ljava/awt/Graphics;)V
        //   243: aload           12
        //   245: getstatic       loader.e:Ljava/awt/Color;
        //   248: iload           23
        //   250: ifne            280
        //   253: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   256: aload           12
        //   258: iconst_0       
        //   259: iconst_0       
        //   260: sipush          370
        //   263: sipush          370
        //   266: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   269: aload           12
        //   271: getstatic       loader.a:Z
        //   274: ifeq            283
        //   277: getstatic       loader.h:Ljava/awt/Color;
        //   280: goto            286
        //   283: getstatic       loader.b:Ljava/awt/Color;
        //   286: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   289: aload           12
        //   291: iconst_1       
        //   292: iconst_1       
        //   293: sipush          368
        //   296: sipush          368
        //   299: invokevirtual   java/awt/Graphics.drawOval:(IIII)V
        //   302: aload           12
        //   304: iconst_3       
        //   305: iconst_3       
        //   306: sipush          364
        //   309: sipush          364
        //   312: bipush          90
        //   314: iload_3        
        //   315: bipush          36
        //   317: imul           
        //   318: bipush          10
        //   320: idiv           
        //   321: ineg           
        //   322: invokevirtual   java/awt/Graphics.fillArc:(IIIIII)V
        //   325: aload           12
        //   327: getstatic       loader.e:Ljava/awt/Color;
        //   330: iload           23
        //   332: ifne            364
        //   335: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   338: aload           12
        //   340: bipush          33
        //   342: bipush          33
        //   344: sipush          304
        //   347: sipush          304
        //   350: invokevirtual   java/awt/Graphics.fillOval:(IIII)V
        //   353: aload           12
        //   355: getstatic       loader.a:Z
        //   358: ifeq            367
        //   361: getstatic       loader.h:Ljava/awt/Color;
        //   364: goto            370
        //   367: getstatic       loader.b:Ljava/awt/Color;
        //   370: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   373: aload           12
        //   375: bipush          35
        //   377: bipush          35
        //   379: sipush          300
        //   382: sipush          300
        //   385: invokevirtual   java/awt/Graphics.drawOval:(IIII)V
        //   388: aload           12
        //   390: getstatic       loader.g:Ljava/awt/Font;
        //   393: invokevirtual   java/awt/Graphics.setFont:(Ljava/awt/Font;)V
        //   396: aload           12
        //   398: invokevirtual   java/awt/Graphics.getFontMetrics:()Ljava/awt/FontMetrics;
        //   401: astore          13
        //   403: iload           23
        //   405: ifne            421
        //   408: aload           13
        //   410: ifnull          523
        //   413: aload           12
        //   415: getstatic       loader.f:Ljava/awt/Color;
        //   418: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   421: iconst_0       
        //   422: istore          14
        //   424: aload_1        
        //   425: iload           23
        //   427: ifne            437
        //   430: ifnull          436
        //   433: iinc            14, 1
        //   436: aload_2        
        //   437: ifnull          443
        //   440: iinc            14, 1
        //   443: sipush          398
        //   446: iload           14
        //   448: bipush          20
        //   450: imul           
        //   451: isub           
        //   452: iconst_2       
        //   453: idiv           
        //   454: istore          15
        //   456: aload_1        
        //   457: iload           23
        //   459: ifne            493
        //   462: ifnull          492
        //   465: aload           12
        //   467: aload_1        
        //   468: iconst_1       
        //   469: sipush          368
        //   472: aload           13
        //   474: aload_1        
        //   475: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //   478: isub           
        //   479: iconst_2       
        //   480: idiv           
        //   481: iadd           
        //   482: iconst_1       
        //   483: iload           15
        //   485: iadd           
        //   486: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   489: iinc            15, 25
        //   492: aload_2        
        //   493: ifnull          523
        //   496: aload           12
        //   498: aload_2        
        //   499: iconst_1       
        //   500: sipush          368
        //   503: aload           13
        //   505: aload_2        
        //   506: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //   509: isub           
        //   510: iconst_2       
        //   511: idiv           
        //   512: iadd           
        //   513: iconst_1       
        //   514: iload           15
        //   516: iadd           
        //   517: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   520: iinc            15, 25
        //   523: jsr             537
        //   526: goto            546
        //   529: astore          16
        //   531: jsr             537
        //   534: aload           16
        //   536: athrow         
        //   537: astore          17
        //   539: aload           12
        //   541: invokevirtual   java/awt/Graphics.dispose:()V
        //   544: ret             17
        //   546: aload           5
        //   548: getstatic       loader.e:Ljava/awt/Color;
        //   551: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   554: aload           5
        //   556: iconst_0       
        //   557: iconst_0       
        //   558: iload           7
        //   560: iload           10
        //   562: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   565: aload           5
        //   567: iconst_0       
        //   568: iload           10
        //   570: iload           9
        //   572: iload           8
        //   574: iload           10
        //   576: isub           
        //   577: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   580: aload           5
        //   582: iload           9
        //   584: iload           10
        //   586: sipush          368
        //   589: iadd           
        //   590: iconst_2       
        //   591: iadd           
        //   592: iload           7
        //   594: iload           9
        //   596: isub           
        //   597: iload           8
        //   599: iload           10
        //   601: isub           
        //   602: sipush          368
        //   605: isub           
        //   606: iconst_2       
        //   607: isub           
        //   608: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   611: aload           5
        //   613: iload           9
        //   615: sipush          368
        //   618: iadd           
        //   619: iconst_2       
        //   620: iadd           
        //   621: iload           10
        //   623: iload           7
        //   625: iload           9
        //   627: isub           
        //   628: sipush          368
        //   631: isub           
        //   632: iconst_2       
        //   633: isub           
        //   634: sipush          370
        //   637: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   640: aload           5
        //   642: aload_0        
        //   643: getfield        loader.l:Ljava/awt/Image;
        //   646: iload           9
        //   648: iload           10
        //   650: aload_0        
        //   651: invokevirtual   java/awt/Graphics.drawImage:(Ljava/awt/Image;IILjava/awt/image/ImageObserver;)Z
        //   654: pop            
        //   655: iconst_1       
        //   656: istore          4
        //   658: jsr             672
        //   661: goto            914
        //   664: astore          18
        //   666: jsr             672
        //   669: aload           18
        //   671: athrow         
        //   672: astore          19
        //   674: iload           23
        //   676: ifne            708
        //   679: iload           4
        //   681: ifne            889
        //   684: aload           5
        //   686: getstatic       loader.e:Ljava/awt/Color;
        //   689: iload           23
        //   691: ifne            719
        //   694: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   697: aload           5
        //   699: iconst_0       
        //   700: iconst_0       
        //   701: iload           7
        //   703: iload           8
        //   705: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   708: aload           5
        //   710: getstatic       loader.a:Z
        //   713: ifeq            722
        //   716: getstatic       loader.h:Ljava/awt/Color;
        //   719: goto            725
        //   722: getstatic       loader.b:Ljava/awt/Color;
        //   725: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   728: aload           5
        //   730: iload           7
        //   732: sipush          300
        //   735: isub           
        //   736: iconst_2       
        //   737: isub           
        //   738: iconst_2       
        //   739: isub           
        //   740: iconst_2       
        //   741: idiv           
        //   742: iload           8
        //   744: bipush          30
        //   746: isub           
        //   747: iconst_2       
        //   748: isub           
        //   749: iconst_2       
        //   750: isub           
        //   751: iconst_2       
        //   752: idiv           
        //   753: sipush          899
        //   756: sipush          629
        //   759: invokevirtual   java/awt/Graphics.drawRect:(IIII)V
        //   762: aload           5
        //   764: iload           7
        //   766: sipush          300
        //   769: isub           
        //   770: iconst_2       
        //   771: idiv           
        //   772: iload           8
        //   774: bipush          30
        //   776: isub           
        //   777: iconst_2       
        //   778: idiv           
        //   779: sipush          300
        //   782: iload_3        
        //   783: imul           
        //   784: bipush          100
        //   786: idiv           
        //   787: bipush          30
        //   789: invokevirtual   java/awt/Graphics.fillRect:(IIII)V
        //   792: aload           5
        //   794: getstatic       loader.g:Ljava/awt/Font;
        //   797: invokevirtual   java/awt/Graphics.setFont:(Ljava/awt/Font;)V
        //   800: aload           5
        //   802: invokevirtual   java/awt/Graphics.getFontMetrics:()Ljava/awt/FontMetrics;
        //   805: astore          20
        //   807: iload           23
        //   809: ifne            825
        //   812: aload           20
        //   814: ifnull          886
        //   817: aload           5
        //   819: getstatic       loader.f:Ljava/awt/Color;
        //   822: invokevirtual   java/awt/Graphics.setColor:(Ljava/awt/Color;)V
        //   825: aload_1        
        //   826: iload           23
        //   828: ifne            859
        //   831: ifnull          858
        //   834: aload           5
        //   836: aload_1        
        //   837: iload           7
        //   839: aload           20
        //   841: aload_1        
        //   842: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //   845: isub           
        //   846: iconst_2       
        //   847: idiv           
        //   848: iload           8
        //   850: bipush          10
        //   852: iadd           
        //   853: iconst_2       
        //   854: idiv           
        //   855: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   858: aload_2        
        //   859: ifnull          886
        //   862: aload           5
        //   864: aload_2        
        //   865: iload           7
        //   867: aload           20
        //   869: aload_2        
        //   870: invokevirtual   java/awt/FontMetrics.stringWidth:(Ljava/lang/String;)I
        //   873: isub           
        //   874: iconst_2       
        //   875: idiv           
        //   876: iload           8
        //   878: bipush          44
        //   880: isub           
        //   881: iconst_2       
        //   882: idiv           
        //   883: invokevirtual   java/awt/Graphics.drawString:(Ljava/lang/String;II)V
        //   886: iconst_1       
        //   887: istore          4
        //   889: jsr             903
        //   892: goto            912
        //   895: astore          21
        //   897: jsr             903
        //   900: aload           21
        //   902: athrow         
        //   903: astore          22
        //   905: aload           5
        //   907: invokevirtual   java/awt/Graphics.dispose:()V
        //   910: ret             22
        //   912: ret             19
        //   914: goto            919
        //   917: astore          5
        //   919: iload           4
        //   921: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  174    210    213    221    Any
        //  213    218    213    221    Any
        //  238    526    529    537    Any
        //  529    534    529    537    Any
        //  121    661    664    672    Any
        //  664    669    664    672    Any
        //  674    892    895    903    Any
        //  895    900    895    903    Any
        //  8      29     917    919    Ljava/lang/Exception;
        //  30     60     917    919    Ljava/lang/Exception;
        //  61     80     917    919    Ljava/lang/Exception;
        //  81     914    917    919    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #1417 (coming from #1413).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        z = new String[] { z(z("Q!GQ\u0002T/W")), z(z("\u0012>BW\u001e\u000e~\u0013")), z(z("[/NQU_!GQ")), z(z("\u0012$BF")), z(z("R/NQ")), z(z("O'DZ[Z'OQ\u001aR*JG\u001e")), z(z("_\"JQ\u001bH")), z(z("T!P@")), z(z("[/NQ")), z(z("V/UU[I:JX[V/Q\u001a%]-H\u0006E\f")), z(z("Q!GQ\u0002T+QQ")), z(z("O'DZ[O'DZ\u0019U H")), z(z("Q/JZ*Z'OQ*_/@\\\u0010\u0012*B@")), z(z("\u001c'P\u0014\u0019S/G]\u001b[n\u000e\u0014\u0005P+BG\u0010\u001c9B]\u0001\u0012`\r")), z(z("p!BP\u001cR)\u0003")), z(z("\u001cc\u0003")), z(z("P!BP\u0010N")), z(z("t+OB\u0010H'@U")) };
        loader.j = new CRC32();
        e = Color.black;
        f = Color.white;
        g = new Font(loader.z[17], 1, 13);
        b = new Color(16731922);
        h = new Color(140, 17, 17);
        loader.a = false;
        long n = 0L;
        try {
            n = (InetAddress.getLocalHost().hashCode() & 0xFFFFFFFFL);
        }
        catch (Exception ex) {}
        final int[] array = { -663535616, -663527425 };
        for (int i = 0; i < array.length - 1; i += 2) {
            long n2 = array[i] & 0xFFFFFFFFL;
            long n3 = array[1 + i] & 0xFFFFFFFFL;
            if (n2 > n3) {
                final long n4 = n2;
                n2 = n3;
                n3 = n4;
            }
            if (n >= n2 && n <= n3) {
                try {
                    System.exit(0);
                }
                finally {
                    throw new NoClassDefFoundError(loader.z[16]);
                }
            }
        }
        try {
            System.setProperty(z(z("O;M\u001a\u001f]8B\u0006\u0011\u0012 LP\u0011N/T")), z(z("H<VQ")));
            System.setProperty(z(z("O;M\u001a\u0014K:\rZ\u001aY<BG\u0010^/@_\u0012N!VZ\u0011")), z(z("H<VQ")));
            System.setProperty(z(z("O;M\u001a\u001f]8B\u0006\u0011\u0012*\u0010P")), z(z("Z/OG\u0010")));
            System.setProperty(z(z("O;M\u001a\u001f]8B\u0006\u0011\u0012!SQ\u001b[\"")), z(z("Z/OG\u0010")));
            System.setProperty(z(z("O;M\u001a\u001f]8B\u0006\u0011\u0012>N[\u0013Z=@F\u0010Y ")), z(z("Z/OG\u0010")));
        }
        catch (Throwable t) {}
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'u';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = 'N';
                            break;
                        }
                        case 2: {
                            c2 = '#';
                            break;
                        }
                        case 3: {
                            c2 = '4';
                            break;
                        }
                        default: {
                            c2 = 'u';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
