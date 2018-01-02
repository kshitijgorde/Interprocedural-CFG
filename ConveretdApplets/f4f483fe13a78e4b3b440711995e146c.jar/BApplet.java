import java.io.InputStream;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.image.ImageObserver;
import java.awt.image.ColorModel;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Calendar;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.text.NumberFormat;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BApplet extends Applet implements BConstants, Runnable, MouseListener, MouseMotionListener, KeyListener
{
    static final int DEFAULT_WIDTH = 100;
    static final int DEFAULT_HEIGHT = 100;
    static byte[] tiffHeader;
    private static NumberFormat float_nf;
    private static int float_nf_left;
    private static int float_nf_right;
    private static NumberFormat int_nf;
    private static int int_nf_digits;
    public BGraphics g;
    public int[] pixels;
    DirectColorModel cm;
    MemoryImageSource mis;
    Image image;
    public int mouseX;
    public int mouseY;
    public int pmouseX;
    public int pmouseY;
    boolean firstMouseEvent;
    public boolean mousePressed;
    public MouseEvent mouseEvent;
    public int key;
    public boolean keyPressed;
    public KeyEvent keyEvent;
    boolean timing;
    long actualMillis;
    long millisOffset;
    Calendar calendar;
    protected float fps;
    protected long fpsLastMillis;
    protected long fpsLastDelayTime;
    protected float fpsTarget;
    boolean drawMethod;
    boolean loopMethod;
    boolean firstFrame;
    boolean finished;
    boolean drawn;
    Thread thread;
    Exception exception;
    int width;
    int height;
    int screenGrabCount;
    boolean isMacintosh;
    
    public void init() {
        this.checkParams();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.timing = true;
        this.millisOffset = System.currentTimeMillis();
        this.finished = false;
        this.drawn = false;
        this.firstFrame = true;
        this.drawMethod = true;
        this.loopMethod = true;
        this.firstMouseEvent = true;
        try {
            this.setup();
        }
        catch (NullPointerException ex) {
            this.size(100, 100);
            this.setup();
        }
        if (this.g == null) {
            this.size(100, 100);
        }
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        final Thread[] array = new Thread[Thread.activeCount()];
        Thread.enumerate(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                if (array[i].getName().indexOf("Thread-") == 0) {
                    array[i].stop();
                }
            }
        }
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    void setup() {
    }
    
    void draw() {
        this.drawMethod = false;
    }
    
    void loop() {
        this.loopMethod = false;
    }
    
    public void size(final int width, final int height) {
        if (this.g != null) {
            return;
        }
        this.width = width;
        this.height = height;
        this.allocate();
        this.g.applet = this;
    }
    
    public void allocate() {
        this.cm = new DirectColorModel(32, 16711680, 65280, 255);
        this.g = new BGraphics(this.width, this.height);
        this.pixels = this.g.pixels;
        for (int i = 0; i < this.pixels.length; ++i) {
            this.pixels[i] = -1;
        }
        (this.mis = new MemoryImageSource(this.width, this.height, this.pixels, 0, this.width)).setFullBufferUpdates(true);
        this.mis.setAnimated(true);
        this.image = Toolkit.getDefaultToolkit().createImage(this.mis);
    }
    
    public void paint(final Graphics graphics) {
        this.mis.newPixels(this.pixels, this.cm, 0, this.width);
        graphics.drawImage(this.image, 0, 0, null);
    }
    
    public void update() {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.paint(graphics);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while (Thread.currentThread() == this.thread && !this.finished) {
            this.nextFrame();
            if (this.firstFrame) {
                this.firstFrame = false;
            }
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void nextFrame() {
        if (this.timing) {
            this.actualMillis = System.currentTimeMillis();
            this.calendar = null;
        }
        if (this.fpsTarget != 0.0f) {
            this.framerate_delay();
        }
        if (!this.drawn) {
            this.g.beginFrame();
            this.draw();
            if (this.drawMethod) {
                this.g.endFrame();
                this.update();
                if (this.isMacintosh) {
                    this.getToolkit().sync();
                }
                this.finished = true;
            }
            this.drawn = true;
        }
        if (!this.drawMethod) {
            this.g.beginFrame();
            this.loop();
            this.g.endFrame();
            this.update();
            if (this.isMacintosh) {
                this.getToolkit().sync();
            }
        }
        if (!this.loopMethod) {
            this.finished = true;
        }
        this.pmouseX = this.mouseX;
        this.pmouseY = this.mouseY;
    }
    
    void mouseClicked() {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.mouseClicked();
    }
    
    void mousePressed() {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mousePressed = true;
        this.mousePressed();
    }
    
    void mouseReleased() {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mousePressed = false;
        this.mouseReleased();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    void mouseDragged() {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (this.firstMouseEvent) {
            this.pmouseX = this.mouseX;
            this.pmouseY = this.mouseY;
            this.firstMouseEvent = false;
        }
        this.mousePressed = true;
        this.mouseDragged();
    }
    
    void mouseMoved() {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        if (this.firstMouseEvent) {
            this.pmouseX = this.mouseX;
            this.pmouseY = this.mouseY;
            this.firstMouseEvent = false;
        }
        this.mousePressed = false;
        this.mouseMoved();
    }
    
    void keyTyped() {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.key = keyEvent.getKeyChar();
        this.keyTyped();
    }
    
    void keyPressed() {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.keyPressed = true;
        this.key = keyEvent.getKeyChar();
        this.keyPressed();
    }
    
    void keyReleased() {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.keyPressed = false;
        this.key = keyEvent.getKeyChar();
        this.keyReleased();
    }
    
    public int millis() {
        return (int)(this.actualMillis - this.millisOffset);
    }
    
    public int second() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(13);
    }
    
    public int minute() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(12);
    }
    
    public int hour() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(11);
    }
    
    public int day() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(5);
    }
    
    public int month() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(2) + 1;
    }
    
    public int year() {
        if (this.calendar == null) {
            this.calendar = Calendar.getInstance();
        }
        return this.calendar.get(1);
    }
    
    public void delay(final int n) {
        if (this.firstFrame) {
            return;
        }
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public float framerate() {
        if (this.fpsLastMillis != 0L) {
            final float n = this.actualMillis - this.fpsLastMillis;
            if (n != 0.0f) {
                this.fps = this.fps * 0.9f + 1.0f / (n / 1000.0f) * 0.1f;
            }
        }
        this.fpsLastMillis = this.actualMillis;
        return this.fps;
    }
    
    public void framerate(final float fpsTarget) {
        this.fpsTarget = fpsTarget;
    }
    
    protected void framerate_delay() {
        if (this.fpsLastDelayTime == 0L) {
            this.fpsLastDelayTime = this.actualMillis;
            return;
        }
        final long fpsLastDelayTime = this.fpsLastDelayTime + (long)(1000.0f / this.fpsTarget);
        final int n = (int)(fpsLastDelayTime - this.actualMillis);
        this.fpsLastDelayTime = fpsLastDelayTime;
        this.delay(n);
    }
    
    static byte[] makeTiffData(final int[] array, final int n, final int n2) {
        final byte[] array2 = new byte[768 + n * n2 * 3];
        System.arraycopy(BApplet.tiffHeader, 0, array2, 0, BApplet.tiffHeader.length);
        array2[30] = (byte)(n >> 8 & 0xFF);
        array2[31] = (byte)(n & 0xFF);
        array2[42] = (array2[102] = (byte)(n2 >> 8 & 0xFF));
        array2[43] = (array2[103] = (byte)(n2 & 0xFF));
        final int n3 = n * n2 * 3;
        array2[114] = (byte)(n3 >> 24 & 0xFF);
        array2[115] = (byte)(n3 >> 16 & 0xFF);
        array2[116] = (byte)(n3 >> 8 & 0xFF);
        array2[117] = (byte)(n3 & 0xFF);
        int n4 = 768;
        for (int i = 0; i < array.length; ++i) {
            array2[n4++] = (byte)(array[i] >> 16 & 0xFF);
            array2[n4++] = (byte)(array[i] >> 8 & 0xFF);
            array2[n4++] = (byte)(array[i] & 0xFF);
        }
        return array2;
    }
    
    public void screenGrab() {
        if (this.screenGrabCount == -1) {
            do {
                ++this.screenGrabCount;
            } while (new File("screen-" + nf(this.screenGrabCount, 4) + ".tif").exists());
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream("screen-" + nf(this.screenGrabCount++, 4) + ".tif");
            fileOutputStream.write(makeTiffData(this.pixels, this.width, this.height));
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void print(final boolean b) {
        System.out.print(b);
        System.out.flush();
    }
    
    public void print(final int n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public void print(final float n) {
        System.out.print(n);
        System.out.flush();
    }
    
    public void print(final String s) {
        System.out.print(s);
        System.out.flush();
    }
    
    public void print(final Object o) {
        System.out.print(o.toString());
        System.out.flush();
    }
    
    public void println(final boolean b) {
        this.print(b);
        System.out.println();
    }
    
    public void println(final int n) {
        this.print(n);
        System.out.println();
    }
    
    public void println(final float n) {
        this.print(n);
        System.out.println();
    }
    
    public void println(final String s) {
        this.print(s);
        System.out.println();
    }
    
    public void println(final Object o) {
        System.out.println(o.toString());
    }
    
    public void println() {
        System.out.println();
    }
    
    public final float abs(final float n) {
        return (n < 0.0f) ? (-n) : n;
    }
    
    public final int abs(final int n) {
        return (n < 0) ? (-n) : n;
    }
    
    public final float sq(final float n) {
        return n * n;
    }
    
    public final float sqrt(final float n) {
        return (float)Math.sqrt(n);
    }
    
    public final float pow(final float n, final float n2) {
        return (float)Math.pow(n, n2);
    }
    
    public final float max(final float n, final float n2) {
        return Math.max(n, n2);
    }
    
    public final float max(final float n, final float n2, final float n3) {
        return Math.max(n, Math.max(n2, n3));
    }
    
    public final float min(final float n, final float n2) {
        return Math.min(n, n2);
    }
    
    public final float min(final float n, final float n2, final float n3) {
        return Math.min(n, Math.min(n2, n3));
    }
    
    public final float constrain(final float n, final float n2, final float n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public final int max(final int n, final int n2) {
        return (n > n2) ? n : n2;
    }
    
    public final int max(final int n, final int n2, final int n3) {
        return (n > n2) ? ((n > n3) ? n : n3) : n2;
    }
    
    public final int min(final int n, final int n2) {
        return (n < n2) ? n : n2;
    }
    
    public final int min(final int n, final int n2, final int n3) {
        return (n < n2) ? ((n < n3) ? n : n3) : n2;
    }
    
    public final float constrain(final int n, final int n2, final int n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public final float sin(final float n) {
        return (float)Math.sin(n);
    }
    
    public final float cos(final float n) {
        return (float)Math.cos(n);
    }
    
    public final float tan(final float n) {
        return (float)Math.tan(n);
    }
    
    public final float atan2(final float n, final float n2) {
        return (float)Math.atan2(n, n2);
    }
    
    public final float degrees(final float n) {
        return n * 57.295776f;
    }
    
    public final float radians(final float n) {
        return n * 0.017453292f;
    }
    
    public final float ceil(final float n) {
        return (float)Math.ceil(n);
    }
    
    public final float floor(final float n) {
        return (float)Math.floor(n);
    }
    
    public final float round(final float n) {
        return Math.round(n);
    }
    
    public final float dist(final float n, final float n2, final float n3, final float n4) {
        return this.sqrt(this.sq(n3 - n) + this.sq(n4 - n2));
    }
    
    public final float dist(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        return this.sqrt(this.sq(n4 - n) + this.sq(n5 - n2) + this.sq(n6 - n3));
    }
    
    public final float random(final float n) {
        return (float)Math.random() * n;
    }
    
    public final float random(final float n, final float n2) {
        return n + (float)Math.random() * (n2 - n);
    }
    
    public static String[] splitStrings(final String s) {
        return splitStrings(s, ' ');
    }
    
    public static String[] splitStrings(final String s, final char c) {
        final char[] charArray = s.toCharArray();
        int n = 1;
        for (int i = 0; i < charArray.length; ++i) {
            if (charArray[i] == c) {
                ++n;
            }
        }
        if (charArray[charArray.length - 1] == c) {
            --n;
        }
        if (n == 0) {
            return new String[] { new String(s) };
        }
        final String[] array = new String[n];
        int n2 = 0;
        int n3 = 0;
        for (int j = 0; j < charArray.length; ++j) {
            if (charArray[j] == c) {
                array[n2++] = new String(charArray, n3, j - n3);
                n3 = j + 1;
            }
        }
        if (n3 != charArray.length) {
            array[n2] = new String(charArray, n3, charArray.length - n3);
        }
        return array;
    }
    
    public static int[] splitInts(final String s) {
        return splitInts(s, ' ', 0);
    }
    
    public static int[] splitInts(final String s, final char c) {
        return splitInts(s, c, 0);
    }
    
    public static int[] splitInts(final String s, final char c, final int n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final int[] array = new int[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = Integer.parseInt(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static long[] splitLongs(final String s) {
        return splitLongs(s, ' ', 0L);
    }
    
    public static long[] splitLongs(final String s, final char c) {
        return splitLongs(s, c, 0L);
    }
    
    public static long[] splitLongs(final String s, final char c, final long n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final long[] array = new long[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = Long.parseLong(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static float[] splitFloats(final String s) {
        return splitFloats(s, ' ', Float.NaN);
    }
    
    public static float[] splitFloats(final String s, final char c) {
        return splitFloats(s, c, Float.NaN);
    }
    
    public static float[] splitFloats(final String s, final char c, final float n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final float[] array = new float[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = new Float(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static double[] splitDoubles(final String s) {
        return splitDoubles(s, ' ', Double.NaN);
    }
    
    public static double[] splitDoubles(final String s, final char c) {
        return splitDoubles(s, c, Double.NaN);
    }
    
    public static double[] splitDoubles(final String s, final char c, final double n) {
        final String[] splitStrings = splitStrings(s, c);
        if (splitStrings == null) {
            return null;
        }
        final double[] array = new double[splitStrings.length];
        for (int i = 0; i < splitStrings.length; ++i) {
            try {
                array[i] = new Double(splitStrings[i]);
            }
            catch (NumberFormatException ex) {
                array[i] = n;
            }
        }
        return array;
    }
    
    public static String join(final String[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final int[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final int[] array, final String s, final int minimumIntegerDigits) {
        if (minimumIntegerDigits == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        instance.setMinimumIntegerDigits(minimumIntegerDigits);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String join(final long[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final long[] array, final String s, final int minimumIntegerDigits) {
        if (minimumIntegerDigits == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setMinimumIntegerDigits(minimumIntegerDigits);
        instance.setGroupingUsed(false);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String join(final float[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final float[] array, final String s, final int minimumIntegerDigits, final int n) {
        if (minimumIntegerDigits == 0 && n == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        if (minimumIntegerDigits != 0) {
            instance.setMinimumIntegerDigits(minimumIntegerDigits);
        }
        if (n != 0) {
            instance.setMinimumFractionDigits(n);
            instance.setMaximumFractionDigits(n);
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String join(final double[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String join(final double[] array, final String s, final int minimumIntegerDigits, final int n) {
        if (minimumIntegerDigits == 0 && n == 0) {
            return join(array, s);
        }
        final NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        if (minimumIntegerDigits != 0) {
            instance.setMinimumIntegerDigits(minimumIntegerDigits);
        }
        if (n != 0) {
            instance.setMinimumFractionDigits(n);
            instance.setMaximumFractionDigits(n);
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                sb.append(s);
            }
            sb.append(instance.format(array[i]));
        }
        return sb.toString();
    }
    
    public static String nf(final float n, final int n2, final int float_nf_right) {
        if (BApplet.float_nf != null && BApplet.float_nf_left == n2 && BApplet.float_nf_right == float_nf_right) {
            return BApplet.float_nf.format(n);
        }
        (BApplet.float_nf = NumberFormat.getInstance()).setGroupingUsed(false);
        if (n2 != 0) {
            BApplet.float_nf.setMinimumIntegerDigits(n2);
        }
        if (float_nf_right != 0) {
            BApplet.float_nf.setMinimumFractionDigits(float_nf_right);
            BApplet.float_nf.setMaximumFractionDigits(float_nf_right);
        }
        BApplet.float_nf_left = n2;
        BApplet.float_nf_right = float_nf_right;
        return BApplet.float_nf.format(n);
    }
    
    public static String nf(final int n, final int n2) {
        if (BApplet.int_nf != null && BApplet.int_nf_digits == n2) {
            return BApplet.int_nf.format(n);
        }
        (BApplet.int_nf = NumberFormat.getInstance()).setGroupingUsed(false);
        BApplet.int_nf.setMinimumIntegerDigits(n2);
        BApplet.int_nf_digits = n2;
        return BApplet.int_nf.format(n);
    }
    
    void checkParams() {
        this.isMacintosh = (System.getProperty("os.name").toLowerCase().indexOf("mac") != -1);
    }
    
    public static void main(final String[] array) {
        if (array.length != 1) {
            System.err.println("error: BApplet <appletname>");
            System.exit(1);
        }
        try {
            final Frame frame = new Frame();
            final BApplet bApplet = (BApplet)Class.forName(array[0]).newInstance();
            bApplet.init();
            bApplet.start();
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLayout(new BorderLayout());
            frame.add(bApplet, "Center");
            frame.pack();
            frame.setLocation((screenSize.width - bApplet.g.width) / 2, (screenSize.height - bApplet.g.height) / 2);
            frame.show();
            bApplet.requestFocus();
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(final WindowEvent windowEvent) {
                    System.exit(0);
                }
                
                {
                    this.constructor$0();
                }
                
                private final void constructor$0() {
                }
            });
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    public void defaults() {
        this.g.defaults();
    }
    
    public void beginFrame() {
        this.g.beginFrame();
    }
    
    public void clear() {
        this.g.clear();
    }
    
    public void endFrame() {
        this.g.endFrame();
    }
    
    public void beginShape() {
        this.g.beginShape();
    }
    
    public void beginShape(final int n) {
        this.g.beginShape(n);
    }
    
    public void textureImage(final BImage bImage) {
        this.g.textureImage(bImage);
    }
    
    public void vertexTexture(final float n, final float n2) {
        this.g.vertexTexture(n, n2);
    }
    
    public void vertexNormal(final float n, final float n2, final float n3) {
        this.g.vertexNormal(n, n2, n3);
    }
    
    public void vertex(final float n, final float n2) {
        this.g.vertex(n, n2);
    }
    
    public void vertex(final float n, final float n2, final float n3) {
        this.g.vertex(n, n2, n3);
    }
    
    public void bezierVertex(final float n, final float n2) {
        this.g.bezierVertex(n, n2);
    }
    
    public void bezierVertex(final float n, final float n2, final float n3) {
        this.g.bezierVertex(n, n2, n3);
    }
    
    public void curveVertex(final float n, final float n2) {
        this.g.curveVertex(n, n2);
    }
    
    public void curveVertex(final float n, final float n2, final float n3) {
        this.g.curveVertex(n, n2, n3);
    }
    
    public void endShape() {
        this.g.endShape();
    }
    
    public void simage(final BImage bImage, final int n, final int n2) {
        this.g.simage(bImage, n, n2);
    }
    
    public void point(final float n, final float n2) {
        this.g.point(n, n2);
    }
    
    public void point(final float n, final float n2, final float n3) {
        this.g.point(n, n2, n3);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4) {
        this.g.line(n, n2, n3, n4);
    }
    
    public void line(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.line(n, n2, n3, n4, n5, n6);
    }
    
    public void triangle(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.triangle(n, n2, n3, n4, n5, n6);
    }
    
    public void quad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.quad(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void rectMode(final int n) {
        this.g.rectMode(n);
    }
    
    public void rect(final float n, final float n2, final float n3, final float n4) {
        this.g.rect(n, n2, n3, n4);
    }
    
    public void ellipseMode(final int n) {
        this.g.ellipseMode(n);
    }
    
    public void ellipse(final float n, final float n2, final float n3, final float n4) {
        this.g.ellipse(n, n2, n3, n4);
    }
    
    public void box(final float n) {
        this.g.box(n);
    }
    
    public void box(final float n, final float n2, final float n3) {
        this.g.box(n, n2, n3);
    }
    
    public void sphere(final float n) {
        this.g.sphere(n);
    }
    
    public void potato() {
        this.g.potato();
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.bezier(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void bezier(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.g.bezier(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.curve(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void curve(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.g.curve(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public BImage loadImage(final String s) {
        return this.g.loadImage(s);
    }
    
    public BImage loadImage(final String s, final boolean b) {
        return this.g.loadImage(s, b);
    }
    
    public void imageMode(final int n) {
        this.g.imageMode(n);
    }
    
    public void image(final BImage bImage, final float n, final float n2) {
        this.g.image(bImage, n, n2);
    }
    
    public void image(final BImage bImage, final float n, final float n2, final float n3, final float n4) {
        this.g.image(bImage, n, n2, n3, n4);
    }
    
    public void image(final BImage bImage, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.g.image(bImage, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void cache(final BImage bImage) {
        this.g.cache(bImage);
    }
    
    public void cache(final BImage[] array) {
        this.g.cache(array);
    }
    
    public BFont loadFont(final String s) {
        return this.g.loadFont(s);
    }
    
    public void setFont(final BFont font) {
        this.g.setFont(font);
    }
    
    public void setFont(final BFont bFont, final float n) {
        this.g.setFont(bFont, n);
    }
    
    public void text(final char c, final float n, final float n2) {
        this.g.text(c, n, n2);
    }
    
    public void text(final String s, final float n, final float n2) {
        this.g.text(s, n, n2);
    }
    
    public void push() {
        this.g.push();
    }
    
    public void pop() {
        this.g.pop();
    }
    
    public void resetMatrix() {
        this.g.resetMatrix();
    }
    
    public void applyMatrix(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.g.applyMatrix(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void beginCamera() {
        this.g.beginCamera();
    }
    
    public void cameraMode(final int n) {
        this.g.cameraMode(n);
    }
    
    public void endCamera() {
        this.g.endCamera();
    }
    
    public float screenX(final float n, final float n2, final float n3) {
        return this.g.screenX(n, n2, n3);
    }
    
    public float screenY(final float n, final float n2, final float n3) {
        return this.g.screenY(n, n2, n3);
    }
    
    public float screenZ(final float n, final float n2, final float n3) {
        return this.g.screenZ(n, n2, n3);
    }
    
    public float objectX(final float n, final float n2, final float n3) {
        return this.g.objectX(n, n2, n3);
    }
    
    public float objectY(final float n, final float n2, final float n3) {
        return this.g.objectY(n, n2, n3);
    }
    
    public float objectZ(final float n, final float n2, final float n3) {
        return this.g.objectZ(n, n2, n3);
    }
    
    public void ortho(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.ortho(n, n2, n3, n4, n5, n6);
    }
    
    public void perspective(final float n, final float n2, final float n3, final float n4) {
        this.g.perspective(n, n2, n3, n4);
    }
    
    public void frustum(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.g.frustum(n, n2, n3, n4, n5, n6);
    }
    
    public void lookat(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.g.lookat(n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public void translate(final float n, final float n2) {
        this.g.translate(n, n2);
    }
    
    public void translate(final float n, final float n2, final float n3) {
        this.g.translate(n, n2, n3);
    }
    
    public void shearX(final float n) {
        this.g.shearX(n);
    }
    
    public void shearY(final float n) {
        this.g.shearY(n);
    }
    
    public void rotate(final float n) {
        this.g.rotate(n);
    }
    
    public void rotateX(final float n) {
        this.g.rotateX(n);
    }
    
    public void rotateY(final float n) {
        this.g.rotateY(n);
    }
    
    public void rotateZ(final float n) {
        this.g.rotateZ(n);
    }
    
    public void rotate(final float n, final float n2, final float n3, final float n4) {
        this.g.rotate(n, n2, n3, n4);
    }
    
    public void scale(final float n) {
        this.g.scale(n);
    }
    
    public void scale(final float n, final float n2) {
        this.g.scale(n, n2);
    }
    
    public void scale(final float n, final float n2, final float n3) {
        this.g.scale(n, n2, n3);
    }
    
    public void transform(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        this.g.transform(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16);
    }
    
    public void colorMode(final int n) {
        this.g.colorMode(n);
    }
    
    public void colorMode(final int n, final float n2) {
        this.g.colorMode(n, n2);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4) {
        this.g.colorMode(n, n2, n3, n4);
    }
    
    public void colorMode(final int n, final float n2, final float n3, final float n4, final float n5) {
        this.g.colorMode(n, n2, n3, n4, n5);
    }
    
    public void noFill() {
        this.g.noFill();
    }
    
    public void fill(final int n) {
        this.g.fill(n);
    }
    
    public void fill(final float n) {
        this.g.fill(n);
    }
    
    public void fill(final float n, final float n2) {
        this.g.fill(n, n2);
    }
    
    public void fill(final float n, final float n2, final float n3) {
        this.g.fill(n, n2, n3);
    }
    
    public void fill(final float n, final float n2, final float n3, final float n4) {
        this.g.fill(n, n2, n3, n4);
    }
    
    public void strokeWidth(final float n) {
        this.g.strokeWidth(n);
    }
    
    public void strokeMode(final int n) {
        this.g.strokeMode(n);
    }
    
    public void noStroke() {
        this.g.noStroke();
    }
    
    public void stroke(final int n) {
        this.g.stroke(n);
    }
    
    public void stroke(final float n) {
        this.g.stroke(n);
    }
    
    public void stroke(final float n, final float n2) {
        this.g.stroke(n, n2);
    }
    
    public void stroke(final float n, final float n2, final float n3) {
        this.g.stroke(n, n2, n3);
    }
    
    public void stroke(final float n, final float n2, final float n3, final float n4) {
        this.g.stroke(n, n2, n3, n4);
    }
    
    public void noBackground() {
        this.g.noBackground();
    }
    
    public void background(final int n) {
        this.g.background(n);
    }
    
    public void background(final float n) {
        this.g.background(n);
    }
    
    public void background(final float n, final float n2, final float n3) {
        this.g.background(n, n2, n3);
    }
    
    public void lights() {
        this.g.lights();
    }
    
    public void noLights() {
        this.g.noLights();
    }
    
    public void smooth() {
        this.g.smooth();
    }
    
    public void noSmooth() {
        this.g.noSmooth();
    }
    
    public void hint(final int n) {
        this.g.hint(n);
    }
    
    public void unhint(final int n) {
        this.g.unhint(n);
    }
    
    public void message(final int n, final String s) {
        this.g.message(n, s);
    }
    
    public void message(final int n, final String s, final Exception ex) {
        this.g.message(n, s, ex);
    }
    
    public InputStream loadStream(final String s) throws IOException {
        return this.g.loadStream(s);
    }
    
    public byte[] loadBytes(final String s) {
        return this.g.loadBytes(s);
    }
    
    public String[] loadStrings(final String s) {
        return this.g.loadStrings(s);
    }
    
    public int getPixel(final int n, final int n2) {
        return this.g.getPixel(n, n2);
    }
    
    public void setPixel(final int n, final int n2, final int n3) {
        this.g.setPixel(n, n2, n3);
    }
    
    public final int color(final float n, final float n2, final float n3) {
        return this.g.color(n, n2, n3);
    }
    
    public final float red(final int n) {
        return this.g.red(n);
    }
    
    public final float green(final int n) {
        return this.g.green(n);
    }
    
    public final float blue(final int n) {
        return this.g.blue(n);
    }
    
    public final float hue(final int n) {
        return this.g.hue(n);
    }
    
    public final float saturation(final int n) {
        return this.g.saturation(n);
    }
    
    public final float brightness(final int n) {
        return this.g.brightness(n);
    }
    
    public BApplet() {
        this.fps = 10.0f;
        this.fpsLastMillis = 0L;
        this.fpsLastDelayTime = 0L;
        this.fpsTarget = 0.0f;
        this.screenGrabCount = -1;
    }
    
    static {
        BApplet.tiffHeader = new byte[] { 77, 77, 0, 42, 0, 0, 0, 8, 0, 9, 0, -2, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 2, 0, 3, 0, 0, 0, 3, 0, 0, 0, 122, 1, 6, 0, 3, 0, 0, 0, 1, 0, 2, 0, 0, 1, 17, 0, 4, 0, 0, 0, 1, 0, 0, 3, 0, 1, 21, 0, 3, 0, 0, 0, 1, 0, 3, 0, 0, 1, 22, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 1, 23, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 8, 0, 8 };
    }
}
