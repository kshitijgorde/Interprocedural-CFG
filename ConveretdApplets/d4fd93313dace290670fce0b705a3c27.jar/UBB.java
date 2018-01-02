import java.net.URLConnection;
import java.io.InputStreamReader;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Dimension;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBB extends Component implements UBBComponentListener
{
    private boolean doDemo;
    private boolean demoActive;
    private boolean suspendPaint;
    private UBBComponent buttonBar;
    private UBBErrorHandler error;
    private Dimension size;
    private boolean started;
    private boolean usePaint;
    protected UBBMouseManager mouseManager;
    private Vector mouseComponents;
    private UBBListener ubbListener;
    private boolean pendingInit;
    private int tagNbr;
    private int[][] map;
    private UBBTag[] buttonBarTags;
    private UBBImageFactory images;
    private UBBTextTools textTools;
    private UBBAnimationTimer animation;
    private static final int TAG_TYPE = 0;
    private static final int TAG_POS = 1;
    private static final int AREA = 1;
    private static final int BAR = 2;
    private static final int BUTTON = 3;
    private static final String[] basicTag;
    
    public void addUBBListener(final UBBListener ubbListener) {
        if (this.ubbListener == null) {
            this.ubbListener = ubbListener;
            return;
        }
        this.error.notify(null, 0, "Only one listener allowed", null);
    }
    
    public void stop() {
        if (this.mouseManager != null) {
            this.removeMouseListener(this.mouseManager);
            this.removeMouseMotionListener(this.mouseManager);
        }
        this.started = false;
        this.animation.stop();
        this.buttonBar.stop();
        System.gc();
    }
    
    public void ubbEvent(final UBBComponent ubbComponent, final String[][] array) {
        if (this.ubbListener != null) {
            String name;
            if (ubbComponent == null) {
                name = "unknown";
            }
            else {
                name = ubbComponent.getName();
            }
            for (int i = 0; i < array.length; ++i) {
                final String s = array[i][0];
                final String s2 = array[i][1];
                if (s != null && s2 != null) {
                    if (s.equals("show") || s.equals("stick")) {
                        final boolean b = s.charAt(1) == 'h';
                        boolean b2 = false;
                        final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
                        this.suspendPaint = true;
                        while (stringTokenizer.hasMoreTokens()) {
                            String s3 = stringTokenizer.nextToken().trim();
                            final char char1 = s3.charAt(0);
                            boolean b3;
                            if (char1 == '-' || char1 == '+') {
                                s3 = s3.substring(1);
                                b3 = (char1 == '-');
                            }
                            else {
                                b3 = false;
                            }
                            if (b) {
                                if (this.buttonBar.setHidden(s3, b3)) {
                                    b2 = true;
                                }
                                else {
                                    this.error.notify(null, 1, s3 + " not " + (b3 ? "hidden" : "shown"), null);
                                }
                            }
                            else {
                                if (s3.toLowerCase().equals("all")) {
                                    s3 = null;
                                }
                                this.buttonBar.setState(s3, b3 ? 1 : 2);
                            }
                        }
                        this.suspendPaint = false;
                        if (b2 || !b) {
                            if (b) {
                                this.mouseManager.queryHidden();
                            }
                            this.componentUpdate(null, false);
                        }
                    }
                    else if (this.animation != null && s.equals("ticks")) {
                        try {
                            this.animation.control(Integer.parseInt(s2));
                        }
                        catch (Exception ex2) {
                            final char char2 = s2.toLowerCase().charAt(0);
                            if (char2 == 'p') {
                                this.animation.control(1);
                            }
                            else if (char2 == 's') {
                                this.animation.control(2);
                            }
                            else {
                                this.animation.control(0);
                            }
                        }
                    }
                    else {
                        try {
                            this.ubbListener.ubbEvent(name, s, s2);
                        }
                        catch (Exception ex) {
                            this.error.notify(null, 0, "ubbEvent " + s + "=" + s2, ex);
                        }
                    }
                }
            }
        }
    }
    
    public UBB(String loadDefnFile, final boolean b, String s, final Dimension size, final URL url, final int n, final int n2, final UBBErrorHandler error) throws Exception {
        this.usePaint = true;
        if (url.getHost().toLowerCase().indexOf("modern") == -1) {
            this.doDemo = true;
        }
        else {
            System.out.println("[UBB2] Demo running on Modern Minds site");
        }
        this.error = error;
        this.size = size;
        if (b) {
            loadDefnFile = this.loadDefnFile(url, loadDefnFile, (n2 & 0x1) == 0x0);
        }
        if (loadDefnFile != null) {
            this.setBounds(0, 0, size.width, size.height);
            this.images = new UBBImageFactory(this, null, url, error);
            if (s == null) {
                s = "<>\"";
            }
            this.textTools = new UBBTextTools(null, s, size.width, error);
            this.animation = new UBBAnimationTimer(n);
            this.buttonBarTags = new UBBTagParser(s, error).parse(loadDefnFile);
            if (this.buttonBarTags != null) {
                this.map = new int[this.buttonBarTags.length][2];
                for (int i = 0; i < this.buttonBarTags.length; ++i) {
                    final String tag = this.buttonBarTags[i].tag;
                    final boolean b2 = tag.charAt(0) == '/';
                    int n3 = 0;
                    if (tag.indexOf("area") >= 0) {
                        n3 = 1;
                    }
                    else if (tag.indexOf("bar") >= 0) {
                        n3 = 2;
                    }
                    else if (tag.indexOf("button") >= 0) {
                        n3 = 3;
                    }
                    else if (tag.indexOf("state") < 0 && tag.indexOf("action") < 0) {
                        error.notify(null, 0, "Unknown tag " + tag, null);
                    }
                    if (n3 != 0) {
                        this.map[this.tagNbr][0] = (b2 ? (-n3) : n3);
                        this.map[this.tagNbr++][1] = i;
                    }
                }
                int n4;
                for (n4 = this.map.length - 1; n4 >= 0 && this.map[n4][0] != -1; --n4) {}
                final int closingTag = this.findClosingTag(this.map, 1, 1);
                if (this.map.length == 0 || this.map[0][0] != 1 || closingTag < 0 || closingTag != n4) {
                    error.notify(null, 0, "Check format of tags", null);
                    if ((n2 & 0x8) != 0x0) {
                        System.out.println("\n<!-- DEFN FOLLOWS -->\n\n" + loadDefnFile + "\n\n<!-- DEFN END -->\n");
                    }
                    throw new Exception("Not within single AREA");
                }
                this.pendingInit = true;
            }
            return;
        }
        throw new Exception("No button bar definition");
    }
    
    public void paint(final Graphics graphics) {
        if (!this.suspendPaint) {
            if (this.demoActive) {
                this.demo(graphics);
            }
            if (this.buttonBar != null) {
                this.buttonBar.paint(graphics);
            }
        }
    }
    
    private synchronized void demo(final Graphics graphics) {
        if (graphics != null) {
            Graphics graphics2 = null;
            final Image image = this.createImage(this.size.width, this.size.height);
            if (image != null) {
                graphics2 = image.getGraphics();
            }
            if (graphics2 != null) {
                this.demoActive = false;
                if (this.buttonBar != null) {
                    this.buttonBar.paint(graphics2);
                }
                graphics2.setFont(new Font("Courier", 1, 18));
                final FontMetrics fontMetrics = graphics2.getFontMetrics();
                final Color color = new Color(104, 110, 224);
                final Color color2 = new Color(222, 255, 253);
                boolean b = true;
                final int n = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
                final int stringWidth = fontMetrics.stringWidth("Demo ");
                for (int i = n; i < this.size.height; i += n) {
                    for (int j = 0; j < this.size.width + stringWidth; j += stringWidth) {
                        if (b) {
                            graphics2.setColor(color);
                        }
                        else {
                            graphics2.setColor(color2);
                        }
                        b = !b;
                        graphics2.drawString("Demo ", j, i);
                    }
                }
                graphics2.dispose();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                graphics.drawImage(image, 0, 0, null);
                try {
                    Thread.currentThread();
                    Thread.sleep(750L);
                }
                catch (InterruptedException ex2) {}
                image.flush();
            }
        }
    }
    
    public void destroy() {
        if (this.buttonBar != null) {
            this.buttonBar.destroy();
        }
        this.ubbListener = null;
        this.mouseManager.destroy();
        this.mouseManager = null;
        this.mouseComponents = null;
        this.animation.destroy();
        this.animation = null;
        this.map = null;
        this.buttonBarTags = null;
        this.images = null;
        this.textTools = null;
        this.buttonBar = null;
        System.gc();
    }
    
    protected void regMouseListener(final UBBComponent ubbComponent, final boolean b) {
        int index = -1;
        if (this.mouseComponents == null) {
            this.mouseComponents = new Vector(20, 20);
        }
        else {
            index = this.mouseComponents.indexOf(ubbComponent);
        }
        if (index >= 0) {
            if (b && !(boolean)this.mouseComponents.elementAt(index + 2)) {
                this.mouseComponents.setElementAt(new Boolean(true), index + 2);
            }
        }
        else {
            this.mouseComponents.addElement(ubbComponent);
            this.mouseComponents.addElement(ubbComponent.getAbsPosition());
            this.mouseComponents.addElement(new Boolean(b));
        }
    }
    
    protected void setDemo() {
        this.doDemo = true;
        this.demoActive = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    int findClosingTag(final int[][] array, final int n, int i) {
        while (i < array.length) {
            if (i < 0) {
                return i;
            }
            final int n2 = array[i][0];
            if (n2 < 0) {
                if (n2 == -n) {
                    return i;
                }
                this.error.notify(null, 0, "Closing " + UBB.basicTag[-n2].toUpperCase() + " tag (" + (i + 1) + ") out of order", null);
                return -i;
            }
            else {
                i = this.findClosingTag(array, n2, i + 1) + 1;
            }
        }
        this.error.notify(null, 0, "Closing " + UBB.basicTag[Math.abs(n)].toUpperCase() + " tag not found", null);
        return -i;
    }
    
    public static final int hashString(final String s) {
        int n = 0;
        if (s != null) {
            for (int i = 0; i < s.length(); ++i) {
                final char c = (char)(n + s.charAt(i));
                n = c + ((c << 3) / (i + 1) >> 2);
            }
        }
        return n;
    }
    
    static {
        basicTag = new String[] { "", "Area", "Bar", "Button" };
        System.out.println("The Ultimate Button Bar v2.00 - Copyright © 1997, 1998 Modern Minds, Inc.");
    }
    
    public void start() {
        if (this.doDemo) {
            this.demoActive = true;
        }
        if (this.pendingInit && this.tagNbr > 0) {
            final Vector vector = new Vector<ComponentToAdd>(this.tagNbr);
            for (int i = 0; i < this.tagNbr; ++i) {
                if (this.map[i][0] > 0) {
                    final int n = this.map[i][1];
                    final int n2 = this.map[i][0];
                    int n3 = 0;
                    try {
                        final int n4 = this.map[i + 1][1];
                        if (n + 1 != n4) {
                            n3 = n4 - n - 1;
                        }
                    }
                    catch (IndexOutOfBoundsException ex3) {
                        n3 = this.buttonBarTags.length - 1 - n;
                    }
                    UBBTag[] array = null;
                    if (n3 > 0) {
                        array = new UBBTag[n3];
                        for (int j = 0; j < n3; ++j) {
                            array[j] = this.buttonBarTags[n + 1 + j];
                        }
                    }
                    final UBBTag ubbTag = this.buttonBarTags[n];
                    String s = "unnamed";
                    String s2 = null;
                    int int1 = 0;
                    int int2 = 0;
                    int int3 = 0;
                    int int4 = 0;
                    final Vector vector2 = new Vector<String[]>(ubbTag.attribute.length);
                    for (int k = 0; k < ubbTag.attribute.length; ++k) {
                        final String s3 = ubbTag.attribute[k][0];
                        final String s4 = ubbTag.attribute[k][1];
                        if (s4 != null && s3 != null) {
                            try {
                                if (s3.equals("w")) {
                                    int1 = Integer.parseInt(s4);
                                }
                                else if (s3.equals("h")) {
                                    int2 = Integer.parseInt(s4);
                                }
                                else if (s3.equals("x")) {
                                    int3 = Integer.parseInt(s4);
                                }
                                else if (s3.equals("y")) {
                                    int4 = Integer.parseInt(s4);
                                }
                                else if (s3.equals("name")) {
                                    s = s4;
                                }
                                else if (s3.equals("type")) {
                                    s2 = s4;
                                }
                                else {
                                    vector2.addElement(ubbTag.attribute[k]);
                                }
                            }
                            catch (Exception ex) {
                                this.error.notify(null, 0, "Invalid " + s3, ex);
                            }
                        }
                    }
                    final String s5 = UBB.basicTag[n2];
                    String s6 = "UBB" + s5;
                    if (s2 != null && !s2.equals(s5.toLowerCase())) {
                        s6 = s6 + s2.substring(0, 1).toUpperCase() + s2.substring(1).toLowerCase();
                    }
                    final String[][] array2 = new String[vector2.size()][2];
                    vector2.copyInto(array2);
                    vector2.removeAllElements();
                    try {
                        final UBBComponent ubbComponent = (UBBComponent)Class.forName(s6).newInstance();
                        ubbComponent.init(this, s, int1, int2, array2, ubbTag.text, array, this.images, this.textTools, this.animation, this.error, false);
                        vector.addElement(new ComponentToAdd(ubbComponent, int3, int4, i));
                    }
                    catch (Exception ex2) {
                        this.error.notify(null, 0, "Creating " + s6, ex2);
                    }
                }
            }
            final ComponentToAdd[] array3 = new ComponentToAdd[vector.size()];
            vector.copyInto(array3);
            vector.removeAllElements();
            for (int l = array3.length - 1; l >= 1; --l) {
                int n5 = array3[l].mapPos - 1;
                int n6 = -1;
                int n7 = l;
                while (n5 >= 0 && n6 != 0) {
                    if (this.map[n5--][0] > 0) {
                        --n7;
                        ++n6;
                    }
                    else {
                        --n6;
                    }
                }
                final ComponentToAdd componentToAdd = array3[l];
                array3[n7].component.addUBBComponent(componentToAdd.component, componentToAdd.x, componentToAdd.y);
            }
            this.buttonBar = array3[0].component;
            final String[][] create = this.buttonBar.create();
            if (create != null) {
                for (int n8 = 0; n8 < create.length; ++n8) {
                    if (create[n8][0] != null) {
                        this.error.notify(this.buttonBar.getName(), 1, "Bad attribute " + create[n8][0] + "=" + create[n8][1], null);
                    }
                }
            }
            this.buttonBar.addUBBComponentListener(this);
            this.animation.notify(this.buttonBar);
            this.mouseManager = new UBBMouseManager(this, this.error);
            this.textTools.releaseFontTable();
            this.images.releaseManagedImages();
            this.pendingInit = false;
        }
        this.map = null;
        this.buttonBarTags = null;
        this.textTools = null;
        this.images = null;
        if (this.buttonBar != null) {
            this.buttonBar.start();
            if (this.mouseComponents != null) {
                if (this.mouseManager != null) {
                    this.addMouseListener(this.mouseManager);
                    this.addMouseMotionListener(this.mouseManager);
                    this.mouseManager.start(this.mouseComponents);
                }
                this.mouseComponents.removeAllElements();
                this.mouseComponents = null;
            }
            this.animation.start();
            System.gc();
            this.started = true;
            this.repaint();
        }
    }
    
    public Dimension getPreferredSize() {
        return this.size;
    }
    
    public Dimension getMinimumSize() {
        return this.size;
    }
    
    public void componentUpdate(final UBBComponent ubbComponent, final boolean b) {
        if (this.started) {
            if (this.usePaint) {
                final Graphics graphics = this.getGraphics();
                if (graphics != null) {
                    this.paint(graphics);
                    graphics.dispose();
                }
            }
            else {
                this.repaint();
            }
        }
    }
    
    private String loadDefnFile(final URL url, final String s, final boolean useCaches) {
        char[] array = null;
        InputStreamReader inputStreamReader = null;
        if (s != null) {
            try {
                final URLConnection openConnection = new URL(url, s).openConnection();
                openConnection.setUseCaches(useCaches);
                final int contentLength = openConnection.getContentLength();
                inputStreamReader = new InputStreamReader(openConnection.getInputStream());
                array = new char[32768];
                int read = inputStreamReader.read(array, 0, 32768);
                for (int n = 0; read < contentLength && n++ <= 32; read += inputStreamReader.read(array, read, 32768)) {}
                if (read < contentLength) {
                    throw new Exception(read + " of " + contentLength + " read");
                }
            }
            catch (Exception ex) {
                this.error.notify(null, 0, "Can't read \"" + s + "\"", ex);
                array = null;
            }
            finally {
                try {
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                }
                catch (Exception ex2) {}
            }
        }
        if (array != null) {
            return new String(array).trim();
        }
        return null;
    }
    
    class ComponentToAdd
    {
        UBBComponent component;
        int x;
        int y;
        int mapPos;
        
        ComponentToAdd(final UBBComponent component, final int x, final int y, final int mapPos) {
            this.component = component;
            this.x = x;
            this.y = y;
            this.mapPos = mapPos;
        }
    }
}
