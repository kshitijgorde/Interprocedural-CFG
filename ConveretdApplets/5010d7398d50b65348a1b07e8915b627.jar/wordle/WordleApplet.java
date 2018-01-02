// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.regex.Matcher;
import java.util.Calendar;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.KeyStroke;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.AbstractButton;
import java.awt.Container;
import java.awt.event.WindowListener;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.imageio.stream.ImageOutputStream;
import java.io.OutputStream;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import java.util.List;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import java.util.Locale;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.io.ByteArrayOutputStream;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Collection;
import wordle.core.c.e;
import wordle.core.fitness.CenterLine;
import cue.lang.stop.StopWords;
import cue.lang.unicode.b;
import cue.lang.c;
import wordle.core.C;
import java.util.StringTokenizer;
import java.io.IOException;
import java.awt.EventQueue;
import wordle.core.c.f;
import java.awt.event.ComponentListener;
import java.text.SimpleDateFormat;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import wordle.core.c.a;
import wordle.core.r;
import wordle.core.t;
import wordle.core.n;
import wordle.core.c.d;
import wordle.core.J;
import java.util.concurrent.Executor;
import javax.swing.JApplet;

public class WordleApplet extends JApplet implements Executor, J, d
{
    private final n a;
    private final t b;
    private final r c;
    private final a d;
    private final ai e;
    private final R f;
    private final w g;
    private final l h;
    private final k i;
    private wordle.J j;
    private wordle.core.e.a k;
    private final DateFormat l;
    private static boolean m;
    private static Map n;
    
    static {
        WordleApplet.m = true;
        WordleApplet.n = new HashMap();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(new h());
    }
    
    public WordleApplet() {
        this.a = new n();
        this.b = new t(this.a);
        this.c = new r();
        this.d = new a();
        this.e = new ai(this);
        this.f = new R(this, this.d, this.e);
        this.g = new w(this.d, this.e);
        this.h = new l(this, this.d, this.e);
        this.i = new k(this, this.d, this.e);
        this.k = null;
        this.l = new SimpleDateFormat("EEE, dd-MMM-yyyy hh:mm:ss z");
        System.err.println("Constructed WordleApplet Build 1354 - \u0540\u0561\u0575\u0561\u057d\u057f\u0561\u0576");
        this.addComponentListener(new i(this));
    }
    
    public final void a(final f f) {
        this.a.setBackground(f.c.b());
        this.b.a(f.d, f.b);
    }
    
    final n a() {
        return this.a;
    }
    
    public void init() {
        try {
            EventQueue.invokeAndWait(new wordle.f(this));
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
        this.addComponentListener(new g(this));
    }
    
    public void start() {
        (this.k = new wordle.core.e.a("WordleApplet Slow Actions")).a();
        this.b.a();
        if (this.getParameter("saved") != null) {
            try {
                this.b.a(wordle.n.a(this));
                return;
            }
            catch (IOException ex) {
                this.e(ex.getMessage());
                return;
            }
        }
        this.d.a(this);
        this.a.a(this);
        final boolean b = this.getParameter("wordcounts") != null || this.getParameter("colorwordcounts") != null;
        final String parameter = this.getParameter("text");
        Label_0352: {
            try {
                if (this.getParameter("wordcounts") != null) {
                    final String parameter2 = this.getParameter("wordcounts");
                    try {
                        final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, ",");
                        while (stringTokenizer.hasMoreTokens()) {
                            final String nextToken;
                            if ((nextToken = stringTokenizer.nextToken()).trim().length() != 0) {
                                final String[] a = a(nextToken, 2);
                                this.c.a(new C(Double.parseDouble(a[1]), a[0].replace('\u2102', ',')));
                            }
                        }
                        break Label_0352;
                    }
                    catch (NumberFormatException ex2) {
                        System.err.println(ex2);
                        throw new RuntimeException("wordcounts");
                    }
                }
                if (this.getParameter("colorwordcounts") != null) {
                    this.d(this.getParameter("colorwordcounts"));
                }
                else {
                    if (parameter == null) {
                        throw new RuntimeException("huh?");
                    }
                    this.c.a(r.a(new c(new cue.lang.d(parameter))));
                }
            }
            catch (RuntimeException ex4) {
                final RuntimeException ex3 = ex4;
                if (ex4.getMessage().contains("wordcounts")) {
                    this.c("I couldn't make sense of your data.\nIs it formatted like the examples on the advanced page?");
                }
                else {
                    System.err.println(ex3);
                    ex3.printStackTrace(System.err);
                    this.c("There was an unexpected error.\nPlease see the troubleshooting FAQ.");
                }
            }
        }
        final Collection b2;
        final Character.UnicodeBlock a2 = cue.lang.unicode.b.a(b2 = this.c.b(50));
        System.err.println("Unicode: detect " + a2);
        final StopWords stopWords;
        if ((stopWords = ((this.c.b() < 20) ? null : StopWords.a(b2))) == null) {
            System.err.println("Stop words: none detected");
        }
        else {
            System.err.println("Stop words: detect " + stopWords);
        }
        if (stopWords != null && stopWords.b && parameter != null) {
            System.err.println(stopWords + ": treating apostrophes as word separators.");
            this.c.a();
            final c c;
            (c = new c()).a(new cue.lang.d(parameter.replace('\'', ' ').replace('\u2019', ' ')));
            this.c.a(r.a(c));
        }
        if (a2 == Character.UnicodeBlock.ARABIC || a2 == Character.UnicodeBlock.DEVANAGARI) {
            this.h.e();
        }
        else {
            this.h.a();
        }
        this.h.b();
        this.f.a(a2);
        this.f.a();
        final e e = new e(this.f.b(), this.h.c(), CenterLine.class, this.h.f(), b ? null : stopWords, 150);
        final wordle.core.b.a.e h = this.h();
        this.d.a(new f("Initial State", e, h, this.b.a(this.c, e, h)));
    }
    
    private void c(final String s) {
        try {
            EventQueue.invokeAndWait(new wordle.d(this, s));
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    private wordle.core.b.a.e h() {
        final Color i;
        if ((i = this.i()) == null) {
            return this.g.a();
        }
        return new wordle.core.b.a.b(i);
    }
    
    public void stop() {
        this.k.b();
        this.c.a();
        this.a.e();
        this.d.b();
        this.b.b();
        System.err.println("Stopped.");
    }
    
    private static Color a(final String s, final Color color) {
        try {
            return Color.decode("0x" + s);
        }
        catch (NumberFormatException ex) {
            System.err.println("Can't understand " + s + " as a hexadecimal number.");
            return color;
        }
    }
    
    private static String[] a(final String s, final int n) {
        if (n < 2) {
            throw new AssertionError((Object)"wanted >= 2");
        }
        final String[] split = s.split(":");
        final String[] array = new String[n];
        for (int i = 0; i < n - 1; ++i) {
            array[array.length - i - 1] = split[split.length - i - 1];
        }
        if (split.length == n) {
            array[0] = split[0];
        }
        else {
            final StringBuilder sb = new StringBuilder();
            for (int j = 0; j < split.length - n + 1; ++j) {
                if (j > 0) {
                    sb.append(':');
                }
                sb.append(split[j]);
            }
            array[0] = sb.toString();
        }
        return array;
    }
    
    private void d(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken;
                if ((nextToken = stringTokenizer.nextToken()).trim().length() != 0) {
                    final String[] a;
                    this.c.a(new C(Double.parseDouble(a[1]), (a = a(nextToken, 3))[0].replace('\u2102', ','), a(a[2], Color.BLACK)));
                }
            }
        }
        catch (NumberFormatException ex) {
            System.err.println(ex);
            throw new RuntimeException("colorwordcounts");
        }
    }
    
    private Color i() {
        final String parameter;
        if ((parameter = this.getParameter("bg")) == null) {
            return null;
        }
        return a(parameter, Color.WHITE);
    }
    
    public final void b() {
        EventQueue.invokeLater(new Z(this));
    }
    
    public final void c() {
        EventQueue.invokeLater(new S(this));
    }
    
    private byte[] a(final String s, final float compressionQuality) {
        final BufferedImage bufferedImage;
        final Graphics2D graphics2D = (Graphics2D)(bufferedImage = new BufferedImage(160, 120, 1)).getGraphics();
        try {
            final boolean b = this.a.b();
            this.a.a(false);
            this.a.a(graphics2D, new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
            this.a.a(b);
        }
        finally {
            graphics2D.dispose();
        }
        graphics2D.dispose();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            if (s.matches("^jpe?g$")) {
                final BufferedImage bufferedImage2 = bufferedImage;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                final BufferedImage bufferedImage3 = bufferedImage2;
                final ImageWriter imageWriter = ImageIO.getImageWritersByFormatName("jpg").next();
                final ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(byteArrayOutputStream2);
                imageWriter.setOutput(imageOutputStream);
                final JPEGImageWriteParam jpegImageWriteParam;
                (jpegImageWriteParam = new JPEGImageWriteParam(null)).setCompressionMode(2);
                jpegImageWriteParam.setCompressionQuality(compressionQuality);
                imageWriter.write(null, new IIOImage(bufferedImage3, null, null), jpegImageWriteParam);
                imageOutputStream.close();
            }
            else {
                ImageIO.write(bufferedImage, s, byteArrayOutputStream);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private void e(final String s) {
        JOptionPane.showMessageDialog(this, s, "Unexpected Error", 0);
    }
    
    public final Font a(final String s) {
        return this.f.a(s);
    }
    
    public final URL b(final String s) {
        final URL codeBase = this.getCodeBase();
        try {
            return new URL(codeBase.getProtocol(), codeBase.getHost(), codeBase.getPort(), s);
        }
        catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private String j() {
        try {
            return (String)((JSObject)JSObject.getWindow((Applet)this).getMember("document")).getMember("cookie");
        }
        catch (Exception ex) {
            System.err.println("Could not get cookie: " + ex);
            return "";
        }
    }
    
    public final void a(final int n, final int n2) {
        final JPopupMenu popupMenu;
        (popupMenu = new JPopupMenu("Wordle Actions")).add(this.d.a);
        popupMenu.add(this.d.b);
        popupMenu.addSeparator();
        popupMenu.add(this.i.a());
        popupMenu.add(this.f.a(this.k()));
        popupMenu.add(this.h.d());
        if (this.i() == null) {
            popupMenu.add(this.g.b());
        }
        popupMenu.show(this.a, n, n2);
    }
    
    public final void a(final wordle.core.b.c c, final int n, final int n2) {
        final String b = c.k().b;
        final JPopupMenu popupMenu;
        (popupMenu = new JPopupMenu(b)).add(this.e.a("Remove \"" + b + "\"", new U(this, b)));
        popupMenu.show(this.a, n, n2);
    }
    
    public final void a(final String s, final e e, final wordle.core.b.a.e e2) {
        this.execute(new W(this, s, e, e2));
    }
    
    public void execute(final Runnable runnable) {
        this.k.execute(runnable);
    }
    
    public final void d() {
        final Container parent = this.getParent();
        final X x;
        (x = new X(this)).setBackground(Color.WHITE);
        parent.remove(this);
        parent.add(x);
        parent.validate();
        if (this.j != null) {
            this.j.setVisible(false);
        }
        final JFrame frame;
        final Container contentPane = (frame = new JFrame("Wordle Applet")).getContentPane();
        frame.setResizable(true);
        frame.setSize(800, 600);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(this);
        frame.setVisible(true);
        frame.addWindowListener(new L(this, parent, x));
        frame.addComponentListener(new K(this, contentPane));
    }
    
    public final c e() {
        final c c = new c();
        final String parameter;
        if ((parameter = this.getParameter("text")) != null) {
            c.a(new cue.lang.d(parameter));
        }
        return c;
    }
    
    public String getParameter(final String s) {
        try {
            return super.getParameter(s);
        }
        catch (NullPointerException ex) {
            System.err.println("NPE for param " + s);
            return null;
        }
    }
    
    private boolean k() {
        return this.getParameter("g") == null;
    }
    
    public static void a(final char c, final AbstractButton abstractButton) {
        WordleApplet.n.put(c, abstractButton);
    }
}
