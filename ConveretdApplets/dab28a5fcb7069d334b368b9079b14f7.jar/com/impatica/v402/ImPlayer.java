// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.io.FileInputStream;
import java.awt.Cursor;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Toolkit;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.zip.ZipInputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.Container;
import java.awt.Component;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.applet.Applet;

public class ImPlayer extends Applet implements ImIsys, Runnable
{
    boolean add;
    ImPlayer append;
    Image arraycopy;
    MemoryImageSource charAt;
    int[] cos;
    int I;
    int createImage;
    Dimension Z;
    ImWindow currentThread;
    ImWindow C;
    boolean B;
    ImFullScreen D;
    int F;
    int J;
    ImIplayer S;
    int currentTimeMillis;
    Font dispose;
    String drawImage;
    Color A;
    int E;
    int drawString;
    boolean G;
    int[] H;
    String endsWith;
    String K;
    boolean exec;
    int fillOval;
    int fillPolygon;
    Thread L;
    String[] M;
    
    public final Rectangle bounds() {
        if (this.G) {
            final Dimension size = this.size();
            if (this.Z == null || size.width != this.Z.width || size.height != this.Z.height) {
                if (size.width == 0 || size.height == 0) {
                    return new Rectangle(2048, 2048);
                }
                this.resize(size.width, size.height);
            }
        }
        else if (this.Z == null) {
            return new Rectangle(2048, 2048);
        }
        return new Rectangle(this.Z);
    }
    
    public final void destroy() {
        this.stop();
        if (this.S != null) {
            this.S.I();
        }
    }
    
    final void I() {
        final ImIplayer s = this.S;
        final ImRecord c = s.C(3);
        if (c == null) {
            this.H = new int[0];
            return;
        }
        final int s2 = c.S();
        this.H = new int[s2];
        int n = 0;
        int s3 = c.S();
        for (int i = 0; i < s2; ++i) {
            final int s4 = c.S();
            final int s5 = c.S();
            final int n2 = s5 - s3;
            final int a = c.A();
            c.Z(s3);
            boolean b = false;
            int n3 = 0;
            for (int j = 0; j < n2; ++j) {
                final char c2 = (char)c.F();
                if (c2 == '-') {
                    b = true;
                }
                else if (b) {
                    if (c2 >= '0' && c2 <= '9') {
                        n3 = n3 * 10 + (c2 - '0');
                    }
                    else if (c2 < 'A' || c2 > 'Z' || j != n2 - 1) {
                        n3 = -1;
                    }
                }
            }
            if (n3 <= 0) {
                this.H[n++] = s4;
            }
            c.Z(a);
            s3 = s5;
        }
        if (n < s2) {
            System.arraycopy(this.H, 0, this.H = new int[n], 0, n);
        }
        final ImRecord c3 = s.C(5);
        final String[] m = new String[n];
        if (c3 != null) {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new InflaterInputStream(new ByteArrayInputStream(c3.B(c3.E())))));
                bufferedReader.readLine();
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    final int index = line.indexOf(32);
                    if (index == -1) {
                        continue;
                    }
                    try {
                        final int int1 = Integer.parseInt(line.substring(0, index));
                        if (int1 < 1 || int1 > n) {
                            continue;
                        }
                        m[int1 - 1] = line;
                    }
                    catch (NumberFormatException ex) {
                        System.out.println(ex);
                    }
                }
            }
            catch (IOException ex2) {
                System.out.println(ex2);
            }
        }
        for (int k = 0; k < n; ++k) {
            if (m[k] == null) {
                m[k] = "" + (k + 1);
            }
        }
        this.M = m;
    }
    
    final void I(final boolean b) {
        if (this.D == null || b == this.B) {
            return;
        }
        if (b) {
            final ImWindow currentThread = new ImWindow();
            ImPlayer imPlayer;
            if (this.F != 0) {
                final ImPlayerConsole imPlayerConsole = (ImPlayerConsole)(imPlayer = new ImPlayerConsole());
                final ImPlayerConsole imPlayerConsole2 = (ImPlayerConsole)this;
                imPlayerConsole.N = imPlayerConsole2.N;
                imPlayerConsole.O = imPlayerConsole2.O;
                imPlayerConsole.P = imPlayerConsole2.P;
                imPlayerConsole.Q = imPlayerConsole2.Q;
                imPlayerConsole.R = imPlayerConsole2.R;
                imPlayerConsole.T = imPlayerConsole2.T;
            }
            else {
                imPlayer = new ImPlayer();
            }
            imPlayer.append = this;
            imPlayer.Z = this.Z;
            imPlayer.H = this.H;
            imPlayer.currentThread = currentThread;
            imPlayer.F = this.F;
            imPlayer.S = this.S;
            imPlayer.K = this.K;
            imPlayer.M = this.M;
            imPlayer.B = b;
            imPlayer.I = this.I;
            imPlayer.A = this.A;
            imPlayer.E = this.E;
            this.S.t = imPlayer;
            this.stop();
            currentThread.add(currentThread.I = imPlayer);
            this.charAt(this, imPlayer);
            this.S.I = 0;
            this.D.Z(currentThread);
            if (ImFullScreen.Z) {
                currentThread.pack();
            }
            currentThread.show();
            imPlayer.start();
        }
        else {
            this.stop();
            this.charAt(this, this.append);
            final ImWindow currentThread2 = this.currentThread;
            if (currentThread2 == null) {
                return;
            }
            this.D.I(currentThread2);
            currentThread2.dispose();
            this.currentThread = null;
            this.S.t = this.append;
            this.S.I = this.append.I;
            this.S.Z(this.append.Z.width, this.append.Z.height - this.F);
            this.append.start();
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401:
            case 403: {
                final int key = event.key;
                if (key == 1006) {
                    this.S.C(-1, 0);
                    break;
                }
                if (key == 1007) {
                    this.S.C(1, 0);
                    break;
                }
                if (key == 1004) {
                    this.S.C(0, -1);
                    break;
                }
                if (key == 1005) {
                    this.S.C(0, 1);
                    break;
                }
                this.S.Z(event.key);
                break;
            }
            case 502: {
                this.S.i = true;
            }
            case 501:
            case 503:
            case 506: {
                this.S.z = event.x;
                this.S.c = event.y;
                break;
            }
        }
        return false;
    }
    
    public ImPlayer() {
        this.J = 6000;
        this.A = Color.white;
        this.E = 16777114;
        try {
            final String property = System.getProperty("java.version");
            this.currentTimeMillis = (property.charAt(0) - '0') * 'd';
            if (property.charAt(1) == '.') {
                this.currentTimeMillis += property.charAt(2) - '0';
            }
            else {
                this.currentTimeMillis += (property.charAt(1) - '0') * '\n';
                this.currentTimeMillis += property.charAt(3) - '0';
            }
            this.endsWith = System.getProperty("os.name");
            if (this.endsWith == null) {
                this.endsWith = "";
            }
            this.G = this.endsWith.startsWith("Mac");
        }
        catch (Throwable t) {}
        this.I = -1;
        this.S = new ImIplayer(this);
        if (this.currentTimeMillis >= 104) {
            this.D = new ImFullScreen(this.G);
        }
    }
    
    public void init() {
        this.add = true;
        this.add();
        final String parameter = this.getParameter("BGCOLOR");
        if (parameter != null) {
            this.I = Integer.parseInt(parameter, 16);
            this.S.I = this.I;
            this.A = new Color(this.I);
            if (this.I != 16777215) {
                this.E = ~this.I;
            }
        }
        final String parameter2 = this.getParameter("LMS");
        if (parameter2 != null) {
            try {
                this.S.P = (ImLmsInterface)Class.forName(parameter2).newInstance();
            }
            catch (Throwable t) {
                System.out.println("Cannot load " + parameter2 + " " + t);
            }
        }
        String k = this.getParameter("FILE");
        if (k != null) {
            if (!k.endsWith(".jar") && !k.endsWith(".imp")) {
                k += ".imp";
            }
            this.append(this.K = k);
        }
    }
    
    private final void add() {
        final String parameter = this.getParameter("IDLEBPS");
        if (parameter != null) {
            this.J = Integer.parseInt(parameter) >> 3;
        }
    }
    
    private final void append(final String s) {
        try {
            InputStream openStream = new URL(this.getCodeBase(), s).openStream();
            if (s.endsWith(".jar")) {
                final ZipInputStream zipInputStream = new ZipInputStream(openStream);
                zipInputStream.getNextEntry();
                openStream = zipInputStream;
            }
            final ImIstreamer imIstreamer = new ImIstreamer();
            if (!imIstreamer.I(openStream) || !this.I(imIstreamer)) {
                throw new IOException("open");
            }
            if (!this.add) {
                this.S.Z(this.Z.width, this.Z.height - this.F);
            }
        }
        catch (Throwable t) {
            System.out.println("Could not open " + s + " " + t);
        }
    }
    
    boolean I(final ImIstream imIstream) {
        if (this.S.I(imIstream)) {
            if (!this.add) {
                this.S.C();
                this.S.Z();
                this.Z = new Dimension(this.S.II, this.S.G + this.F);
            }
            if (this.S.O == null) {
                final StringBuffer sb = new StringBuffer("Fwbmvbujpo!pomz!xxx/jnqbujdb/dpn");
                for (int i = 0; i < sb.length(); ++i) {
                    sb.setCharAt(i, (char)(sb.charAt(i) - '\u0001'));
                }
                this.drawImage = sb.toString();
                this.dispose = new Font("helvetica", 1, 18);
            }
            else {
                this.drawImage = null;
            }
            return true;
        }
        return false;
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.add && this.currentTimeMillis <= 101 && this.Z != null) {
            final Rectangle clipRect = graphics.getClipRect();
            final int n = clipRect.x + clipRect.width;
            final int n2 = clipRect.y + clipRect.height;
            if (n > this.Z.width || n2 > this.Z.height) {
                this.resize(n, n2);
            }
            else if (clipRect.x == 0 && clipRect.y == 0 && (n < this.Z.width || n2 < this.Z.height)) {
                final Dimension size = this.size();
                final int width = size.width;
                final int height = size.height;
                if (width != 0 && height > this.F && (width != this.Z.width || height != this.Z.height)) {
                    this.resize(width, height);
                }
            }
        }
        if (this.arraycopy == null) {
            this.arraycopy(graphics);
        }
        else {
            graphics.drawImage(this.arraycopy, 0, 0, null);
            if (this.drawImage != null) {
                graphics.setXORMode(Color.white);
                final Font font = graphics.getFont();
                graphics.setFont(this.dispose);
                graphics.drawString(this.drawImage, (this.Z.width - 307) / 2, (this.Z.height - 14) / 2);
                graphics.setPaintMode();
                graphics.setFont(font);
            }
            this.notify();
        }
    }
    
    private final void arraycopy(final Graphics graphics) {
        if (this.Z == null || this.S.M == null || graphics == null) {
            return;
        }
        final int width = this.Z.width;
        final int height = this.Z.height;
        if (this.drawString <= 1) {
            this.repaint(0, 0, width, height);
            graphics.setColor(this.A);
            graphics.fillRect(0, 0, width, height);
        }
        final int n = width >> 1;
        final int n2 = height >> 1;
        final int n3 = 2;
        final int n4 = n3 >> 1;
        graphics.fillOval(n - n4, n2 - n4, n3, n3);
        final int n5 = 30;
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        final int n6 = this.E >> 16 & 0xFF;
        int n7 = this.E >> 8 & 0xFF;
        int n8 = this.E & 0xFF;
        double n9 = this.drawString++ * 0.5235987755982988;
        final double n10 = 6.283185307179586 * ((n4 + 1) / (6.283185307179586 * n5));
        for (int i = 0; i < 12; ++i) {
            graphics.setColor(new Color(n6, n7, n8));
            n7 -= 14;
            if (n7 < 0) {
                n7 += 255;
            }
            n8 -= 14;
            if (n8 < 0) {
                n8 += 255;
            }
            final double cos = Math.cos(n9);
            final double sin = Math.sin(n9);
            graphics.fillOval((int)(n + n5 * cos + 0.5) - n4, (int)(n2 + n5 * sin + 0.5) - n4, n3, n3);
            final int n11 = 6;
            array[0] = (int)(n + n11 * cos + 0.5);
            array2[0] = (int)(n2 + n11 * sin + 0.5);
            final double cos2 = Math.cos(n9 - n10);
            final double sin2 = Math.sin(n9 - n10);
            array[1] = (int)(n + n5 * cos2 + 0.5);
            array2[1] = (int)(n2 + n5 * sin2 + 0.5);
            final double cos3 = Math.cos(n9 + n10);
            final double sin3 = Math.sin(n9 + n10);
            array[2] = (int)(n + n5 * cos3 + 0.5);
            array2[2] = (int)(n2 + n5 * sin3 + 0.5);
            graphics.fillPolygon(new Polygon(array, array2, 3));
            n9 += 0.5235987755982988;
        }
        cos(125);
        if (this.charAt != null) {
            this.repaint(0, 0, width, height);
        }
        else {
            this.repaint(n - n5 - 1, n2 - n5 - 1, n5 + n5 + 4, n5 + n5 + 4);
        }
    }
    
    public final Dimension preferredSize() {
        return this.Z;
    }
    
    public void resize(int fillOval, int n) {
        if (fillOval > 2048) {
            n = 2048 * n / fillOval;
            fillOval = 2048;
        }
        this.fillOval = fillOval;
        this.fillPolygon = n - this.F;
        this.Z = new Dimension(fillOval, n);
    }
    
    public final void run() {
        while (this.L == Thread.currentThread()) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.fillOval != 0) {
                this.S.Z(this.fillOval, this.fillPolygon);
                this.fillPolygon = 0;
                this.fillOval = 0;
            }
            final int j = this.S.J();
            this.Z();
            if (j == 0) {
                break;
            }
            final int n = (int)(System.currentTimeMillis() - currentTimeMillis);
            this.S.H.I(this.J);
            final int n2 = j - n;
            if (n2 > 0) {
                cos(n2);
            }
            else {
                if (j != this.S.B) {
                    continue;
                }
                final int n3 = -n2 / j;
                if (n3 <= 0) {
                    continue;
                }
                final ImIplayer s = this.S;
                s.J -= n3;
                if (this.S.J >= 0) {
                    continue;
                }
                this.S.J = 0;
            }
        }
        this.exec = false;
    }
    
    public final void start() {
        if (this.L == null) {
            if (this.S.P != null) {
                this.S.P.I(this);
            }
            this.exec = true;
            (this.L = new Thread(this)).start();
        }
    }
    
    void Z() {
    }
    
    public final void stop() {
        if (this.S.P != null) {
            this.S.P.I();
        }
        this.L = null;
        while (this.exec) {
            cos(1);
        }
    }
    
    public ImVi I(final ImSound imSound) {
        if (this.currentTimeMillis <= 101) {
            return new ImViAudio1(imSound);
        }
        return new ImViAudio2(imSound);
    }
    
    public boolean I(final ImIrgb imIrgb, final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5) {
        Image image = Toolkit.getDefaultToolkit().createImage(array, n, n2);
        if (n5 != 1) {
            image = image.getScaledInstance(n3, n4, 2);
        }
        int width;
        int height;
        while (true) {
            width = image.getWidth(null);
            height = image.getHeight(null);
            if (width != -1 && height != -1) {
                break;
            }
            cos(20);
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, imIrgb.C, 0, width);
        try {
            while (!pixelGrabber.grabPixels(20L)) {}
        }
        catch (InterruptedException ex) {}
        image.flush();
        return true;
    }
    
    public void I(String s) {
        final char char1 = s.charAt(0);
        if (this.B & char1 != '/') {
            this.I(this.exec = false);
            this.append.I(s);
            return;
        }
        s = s.substring(1);
        final int index = s.indexOf(58);
        int n;
        if (index == -1) {
            n = 0;
        }
        else {
            final int index2 = s.indexOf(47);
            n = ((index2 == -1 || index < index2) ? 1 : 0);
        }
        final int index3 = s.indexOf(1);
        if (index3 != -1) {
            if (n != 0) {
                s = s.substring(0, index3);
            }
            else {
                s = s.substring(index3 + 1);
            }
        }
        if (char1 == '/') {
            String k = this.K;
            if (k == null) {
                k = "";
            }
            int n2 = k.lastIndexOf(92);
            if (n2 == -1) {
                n2 = k.lastIndexOf(47);
            }
            if (n2 != -1) {
                s = k.substring(0, n2 + 1) + s;
            }
            if (k.endsWith(".imp")) {
                s += ".imp";
            }
            else {
                s += "-imp.jar";
            }
            if (this.add) {
                this.append(s);
            }
            else {
                this.C.I(s);
            }
        }
        else if (this.add) {
            String s2 = "_self";
            if (char1 == '^') {
                s2 = "_blank";
            }
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), s), s2);
            }
            catch (Throwable t) {
                System.out.println(s);
                System.out.println(t);
            }
        }
        else if (this.G) {
            try {
                Runtime.getRuntime().exec("open " + s);
            }
            catch (Throwable t2) {
                System.out.println(s);
                System.out.println(t2);
            }
        }
        else {
            try {
                if (s.indexOf(32) != -1) {
                    s = '\"' + s + '\"';
                }
                if (this.endsWith.startsWith("Windows 9")) {
                    Runtime.getRuntime().exec("command /c start " + s);
                }
                else {
                    Runtime.getRuntime().exec("cmd /c start \"\" " + s);
                }
            }
            catch (Throwable t3) {
                System.out.println(s);
                System.out.println(t3);
            }
        }
    }
    
    public synchronized void I(final int n, final int n2, final int n3, final int n4) {
        final int[] c = this.S.M.C;
        if (this.charAt == null || c != this.cos) {
            (this.charAt = new MemoryImageSource(n3, n4, new DirectColorModel(24, 16711680, 65280, 255), c, 0, n3)).setAnimated(true);
            this.arraycopy = this.createImage(this.charAt);
            this.cos = c;
        }
        else {
            this.charAt.newPixels(n, n2, n3, n4);
        }
        this.repaint(n, n2, n3, n4);
        try {
            this.wait(500L);
        }
        catch (InterruptedException ex) {}
    }
    
    public void I(final int createImage) {
        if (createImage != this.createImage) {
            this.createImage = createImage;
            try {
                if (createImage == 0) {
                    this.setCursor(Cursor.getDefaultCursor());
                }
                else {
                    this.setCursor(Cursor.getPredefinedCursor(12));
                }
            }
            catch (Throwable t) {}
        }
    }
    
    public void I(final ImVi imVi) {
        imVi.stop();
        this.remove((Component)imVi);
    }
    
    public ImVi Z(String s) {
        ImVi imVi;
        InputStream openStream;
        try {
            imVi = (ImVi)Class.forName("com.impatica.v402.ImVideo").newInstance();
            if (imVi == null) {
                return null;
            }
            if (!s.endsWith(".imv")) {
                s += ".imv";
            }
            if (this.K != null) {
                int n = this.K.lastIndexOf(92);
                if (n == -1) {
                    n = this.K.lastIndexOf(47);
                }
                if (n != -1) {
                    s = this.K.substring(0, n + 1) + s;
                }
            }
            ImPlayer append = this.append;
            if (append == null) {
                append = this;
            }
            if (append.add) {
                openStream = new URL(append.getCodeBase(), s).openStream();
            }
            else {
                openStream = new FileInputStream(s);
            }
        }
        catch (Throwable t) {
            System.out.println(t);
            return null;
        }
        final ImIstreamer imIstreamer = new ImIstreamer(true);
        if (!imIstreamer.I(openStream)) {
            return null;
        }
        if (imVi.I(this, imIstreamer)) {
            this.add((Component)imVi);
            return imVi;
        }
        return null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void charAt(final Container container, final Container container2) {
        final Component[] components = container.getComponents();
        for (int i = components.length - 1; i >= 0; --i) {
            final Component component = components[i];
            if (component instanceof ImVideo) {
                container.remove(component);
                container2.add(component);
            }
        }
    }
    
    static final void cos(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
}
