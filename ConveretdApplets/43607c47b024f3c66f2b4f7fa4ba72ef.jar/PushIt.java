import java.io.InputStream;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Toolkit;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.util.Vector;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Point;
import java.awt.Font;
import java.net.URL;
import java.awt.Rectangle;
import java.util.Properties;
import java.awt.Color;
import java.awt.Image;
import java.awt.LayoutManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PushIt extends Applet implements Runnable, LayoutManager
{
    public static final int TOP = 0;
    public static final int MIDDLE = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private static final int NAME = 0;
    private static final int TYPE = 1;
    private static final Object IMAGE_T;
    private static final Object COLOR_T;
    private static final Object INT_T;
    private static final Object UNITY_T;
    private static final Object TEXT_T;
    private static final Object URL_T;
    private static final Object FONT_T;
    private static final Object CLIP_T;
    private static final Object PAIR_T;
    private static final Object OTHER_T;
    private Thread thread;
    private int width;
    private int height;
    private int bgWidth;
    private int bgHeight;
    private int totalN;
    private int addedN;
    private int hAlign;
    private int vAlign;
    private int arrangement;
    private Image bgImage;
    private int[] bgPixels;
    private Color bgColor;
    private static final Object[][] envParams;
    private static final Object[][] buttonParams;
    private Object[] envValues;
    private Object[] buttonValues;
    private Properties parameters;
    private ButtonImageSource seed;
    private PushButton[] buttons;
    private Rectangle loadBounds;
    private Image loadImage;
    
    public void stop() {
        this.thread = null;
        this.loadBounds.width = -1;
    }
    
    private void getParameterValues(final Object[][] array, final Object[] array2, final Object[] array3, final String s) {
        for (int i = 0; i < array2.length; ++i) {
            String concat = (String)array[i][0];
            if (s != null) {
                concat = concat.concat(s);
            }
            String s2 = this.getParameter(concat);
            final boolean b = s2 == null;
            if (b) {
                s2 = this.parameters.getProperty(concat);
            }
            switch ((int)array[i][1]) {
                case 0: {
                    array2[i] = this.parseImage(s2, (Image)array3[i]);
                    break;
                }
                case 1: {
                    array2[i] = this.parseColor(s2, (Color)array3[i]);
                    break;
                }
                case 2: {
                    array2[i] = this.parseInteger(s2, (Integer)array3[i]);
                    break;
                }
                case 3: {
                    array2[i] = this.parseUnity(s2, (Double)array3[i]);
                    break;
                }
                case 4: {
                    array2[i] = (b ? ((s2 != null) ? s2 : array3[i]) : this.parseText(s2, (String)array3[i]));
                    break;
                }
                case 5: {
                    array2[i] = this.parseURL(s2, (URL)array3[i], false);
                    break;
                }
                case 6: {
                    array2[i] = this.parseFont(s2, (Font)array3[i]);
                    break;
                }
                case 7: {
                    array2[i] = this.parseURL(s2, (URL)array3[i], true);
                    break;
                }
                case 8: {
                    array2[i] = this.parsePair(s2, (Point)array3[i]);
                    break;
                }
                default: {
                    array2[i] = ((s2 != null) ? s2 : array3[i]);
                    break;
                }
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return null;
    }
    
    public void setArrangement(final int n) {
        if (this.arrangement != n) {
            this.arrangement = Math.abs(n) % 2;
            this.validate();
        }
    }
    
    public int getArrangement() {
        return this.arrangement;
    }
    
    private int[] createLabel(final String s, final Font font, final int n, final Color color, final double n2, final boolean b, final Dimension dimension) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r");
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final Dimension labelSize = this.getLabelSize(s, fontMetrics);
        final int n3 = labelSize.width * labelSize.height;
        final int n4 = fontMetrics.getAscent() + fontMetrics.getDescent();
        int ascent = fontMetrics.getAscent();
        final int rgb = this.bgColor.getRGB();
        int n5 = rgb >> 16 & 0xFF;
        int n6 = rgb >> 8 & 0xFF;
        int n7 = rgb & 0xFF;
        final int n8 = ((color == null) ? -8421505 : color.getRGB()) & 0xFFFFFF;
        final int n9 = n8 >> 16 & 0xFF;
        final int n10 = n8 >> 8 & 0xFF;
        final int n11 = n8 & 0xFF;
        final int n12 = (int)(n2 * 255.0);
        int n13 = 0;
        int n14 = 0;
        final Image image = this.createImage(labelSize.width, labelSize.height);
        final Graphics graphics = image.getGraphics();
        final int[] array = new int[n3];
        graphics.setColor(new Color(0));
        graphics.fillRect(0, 0, labelSize.width, labelSize.height);
        graphics.setColor(new Color(-1));
        graphics.setFont(font);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int stringWidth = fontMetrics.stringWidth(nextToken);
            int n15;
            if (n == 0) {
                n15 = 1;
            }
            else if (n == 1) {
                n15 = (labelSize.width - stringWidth) / 2;
            }
            else {
                n15 = labelSize.width - stringWidth - 1;
            }
            graphics.drawString(nextToken, n15, ascent);
            ascent += n4;
        }
        graphics.dispose();
        final int[] pixels = this.getPixels(image, labelSize.width, labelSize.height);
        if (this.bgImage != null) {
            n13 = (this.width - labelSize.width) / 2;
            if (n13 < 0) {
                n13 = this.bgWidth - -n13 % this.bgWidth;
            }
            n14 = (this.height - labelSize.height) / 2;
            if (n14 < 0) {
                n14 = this.bgHeight - -n14 % this.bgHeight;
            }
        }
        for (int i = 0; i < labelSize.height; ++i) {
            for (int j = 0; j < labelSize.width; ++j) {
                final int n16 = i * labelSize.width + j;
                final int n17 = n12 + (255 - n12) * (pixels[n16] & 0xFF) / 255;
                pixels[n16] = (n17 << 24 | n8);
                if (b) {
                    if (this.bgImage != null) {
                        final int n18 = this.bgPixels[(i + n14) % this.bgHeight * this.bgWidth + (j + n13) % this.bgWidth];
                        n5 = (n18 >> 16 & 0xFF);
                        n6 = (n18 >> 8 & 0xFF);
                        n7 = (n18 & 0xFF);
                    }
                    pixels[n16] = (0xFF000000 | (n17 * n9 + (255 - n17) * n5) / 255 << 16 | (n17 * n10 + (255 - n17) * n6) / 255 << 8 | (n17 * n11 + (255 - n17) * n7) / 255);
                }
            }
        }
        dimension.width = labelSize.width;
        dimension.height = labelSize.height;
        return pixels;
    }
    
    private URL parseURL(final String s, final URL url, final boolean b) {
        URL url2 = url;
        if (s != null) {
            try {
                if (s.indexOf(":") != -1) {
                    url2 = new URL(s);
                }
                else {
                    url2 = new URL(b ? this.getCodeBase() : this.getDocumentBase(), s);
                }
            }
            catch (MalformedURLException ex) {}
        }
        return url2;
    }
    
    private Image waitForImage(Image image) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            image = null;
        }
        return image;
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private String[] parseQuoted(final String s) {
        String[] array = null;
        if (s != null) {
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(s));
            streamTokenizer.commentChar(40);
            final Vector vector = new Vector<String>();
            try {
                streamTokenizer.nextToken();
                while (streamTokenizer.ttype == 39 || streamTokenizer.ttype == 34) {
                    vector.addElement(streamTokenizer.sval);
                    streamTokenizer.nextToken();
                }
                final int size = vector.size();
                array = new String[size];
                for (int i = 0; i < size; ++i) {
                    array[i] = vector.elementAt(i);
                }
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    private int matchParen(final String s, int index) {
        int i = 0;
        try {
            do {
                final char char1 = s.charAt(index);
                if (char1 == '(') {
                    ++i;
                }
                else if (char1 == ')') {
                    --i;
                }
                else if (char1 == '\"' || char1 == '\'') {
                    do {
                        index = s.indexOf(char1, index + 1);
                    } while (index != -1 && s.charAt(index - 1) == '\\');
                }
                ++index;
            } while (i > 0);
        }
        catch (Exception ex) {
            index = -1;
        }
        return index;
    }
    
    private int[] getPixels(final Image image, final int n, final int n2) {
        int[] array = new int[n * n2];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            array = null;
        }
        return array;
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
        this.loadBounds.width = this.loadImage.getWidth(null);
    }
    
    private PopupMenu parsePopupMenu(final String s, final Font font, final ActionListener actionListener) {
        Menu menu = null;
        int matchParen = 0;
        if (s != null) {
            menu = new PopupMenu();
            int index;
            while ((index = s.indexOf(40, matchParen)) != -1) {
                matchParen = this.matchParen(s, index);
                if (matchParen == -1) {
                    break;
                }
                final MenuItem menuItem = this.parseMenuItem(s.substring(index + 1, matchParen), font, actionListener);
                if (menuItem != null) {
                    menu.add(menuItem);
                }
                else {
                    menu.insertSeparator(menu.getItemCount());
                }
            }
        }
        return (PopupMenu)menu;
    }
    
    private Integer parseInteger(final String s, final Integer n) {
        Integer n2 = n;
        if (s != null) {
            try {
                n2 = new Integer(Integer.parseInt(s));
            }
            catch (Exception ex) {}
        }
        return n2;
    }
    
    public String getAppletInfo() {
        return "Applet: PushIt\nVersion: 1.2 for Java 1.1 Platform\nAuthor: Uldarico Muico, um@mail.com\nDate: 29 July 1999";
    }
    
    public void setSize(final int width, final int height) {
        super.setSize(this.width = width, this.height = height);
        this.validate();
    }
    
    public void layoutContainer(final Container container) {
        int n = 0;
        Rectangle rectangle = (this.arrangement == 0) ? new Rectangle(this.width, 0) : new Rectangle(0, this.height);
        Rectangle rectangle2 = new Rectangle();
        final Point[] array = new Point[this.buttons.length];
        for (int i = 0; i < this.buttons.length; ++i) {
            final Dimension size = this.buttons[i].getSize();
            if (this.arrangement == 0) {
                if (rectangle.width + size.width > this.width) {
                    rectangle2 = rectangle2.union(rectangle);
                    int n2;
                    if (this.hAlign == 1) {
                        n2 = (this.width - size.width) / 2;
                    }
                    else if (this.hAlign == 0) {
                        n2 = 0;
                    }
                    else {
                        n2 = this.width - size.width;
                    }
                    rectangle.setBounds(n2, rectangle.y + rectangle.height, size.width, size.height);
                    array[i] = new Point(rectangle.x, rectangle.y);
                    n = i;
                }
                else {
                    int n3;
                    if (this.vAlign == 0) {
                        n3 = 0;
                    }
                    else if (this.vAlign == 1) {
                        n3 = (rectangle.height - size.height) / 2;
                    }
                    else {
                        n3 = rectangle.height - size.height;
                    }
                    array[i] = new Point(rectangle.x + rectangle.width, rectangle.y + n3);
                    final Rectangle union = rectangle.union(new Rectangle(array[i], size));
                    int n4;
                    if (this.hAlign == 1) {
                        n4 = -size.width / 2;
                    }
                    else if (this.hAlign == 0) {
                        n4 = 0;
                    }
                    else {
                        n4 = -size.width;
                    }
                    final int n5 = rectangle.y - union.y;
                    for (int j = n; j <= i; ++j) {
                        array[j].translate(n4, n5);
                    }
                    union.translate(n4, n5);
                    rectangle = union;
                }
            }
            else if (rectangle.height + size.height > this.height) {
                rectangle2 = rectangle2.union(rectangle);
                int n6;
                if (this.vAlign == 1) {
                    n6 = (this.height - size.height) / 2;
                }
                else if (this.vAlign == 0) {
                    n6 = 0;
                }
                else {
                    n6 = this.height - size.height;
                }
                rectangle.setBounds(rectangle.x + rectangle.width, n6, size.width, size.height);
                array[i] = new Point(rectangle.x, rectangle.y);
                n = i;
            }
            else {
                int n7;
                if (this.hAlign == 0) {
                    n7 = 0;
                }
                else if (this.hAlign == 1) {
                    n7 = (rectangle.width - size.width) / 2;
                }
                else {
                    n7 = rectangle.width - size.width;
                }
                array[i] = new Point(rectangle.x + n7, rectangle.y + rectangle.height);
                final Rectangle union2 = rectangle.union(new Rectangle(array[i], size));
                int n8;
                if (this.vAlign == 1) {
                    n8 = -size.height / 2;
                }
                else if (this.vAlign == 0) {
                    n8 = 0;
                }
                else {
                    n8 = -size.height;
                }
                final int n9 = rectangle.x - union2.x;
                for (int k = n; k <= i; ++k) {
                    array[k].translate(n9, n8);
                }
                union2.translate(n9, n8);
                rectangle = union2;
            }
        }
        final Rectangle union3 = rectangle2.union(rectangle);
        int n10;
        int n11;
        if (this.arrangement == 0) {
            if (this.hAlign == 0) {
                n10 = (union3.width - this.width) / 2;
            }
            else if (this.hAlign == 2) {
                n10 = (this.width - union3.width) / 2;
            }
            else {
                n10 = 0;
            }
            if (this.vAlign == 1) {
                n11 = (this.height - union3.height) / 2;
            }
            else if (this.vAlign == 2) {
                n11 = this.height - union3.height;
            }
            else {
                n11 = 0;
            }
        }
        else {
            if (this.hAlign == 0) {
                n10 = 0;
            }
            else if (this.hAlign == 2) {
                n10 = this.width - union3.width;
            }
            else {
                n10 = (this.width - union3.width) / 2;
            }
            if (this.vAlign == 1) {
                n11 = (union3.height - this.height) / 2;
            }
            else if (this.vAlign == 2) {
                n11 = (this.height - union3.height) / 2;
            }
            else {
                n11 = 0;
            }
        }
        for (int l = 0; l < this.buttons.length; ++l) {
            final PushButton pushButton = this.buttons[l];
            final Point location = pushButton.getLocation();
            array[l].translate(n10, n11);
            if (location.x != array[l].x || location.y != array[l].y) {
                pushButton.setLocation(array[l].x, array[l].y);
            }
        }
    }
    
    private String[] splitValues(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Vector vector = new Vector<String>();
        String[] array = null;
        try {
            while (stringTokenizer.hasMoreTokens()) {
                vector.addElement(stringTokenizer.nextToken());
            }
            final int size = vector.size();
            array = new String[size];
            for (int i = 0; i < size; ++i) {
                array[i] = vector.elementAt(i);
            }
        }
        catch (Exception ex) {}
        return array;
    }
    
    private Double parseUnity(final String s, final Double n) {
        Double n2 = n;
        if (s != null) {
            try {
                n2 = new Double(this.makeUnity(Double.valueOf(s)));
            }
            catch (Exception ex) {}
        }
        return n2;
    }
    
    private MenuItem parseMenuItem(final String s, final Font font, final ActionListener actionListener) {
        MenuItem menuItem = null;
        int matchParen = 0;
        if (s != null) {
            final String[] quoted = this.parseQuoted(s);
            if (quoted != null) {
                if (quoted.length > 1) {
                    menuItem = new MenuItem(quoted[0]);
                    if (font != null) {
                        menuItem.setFont(font);
                    }
                    for (int i = 2; i < quoted.length; ++i) {
                        quoted[1] = quoted[1].concat("\n" + quoted[i]);
                    }
                    menuItem.setActionCommand(quoted[1]);
                    menuItem.addActionListener(actionListener);
                }
                else if (quoted.length == 1) {
                    menuItem = new Menu(quoted[0]);
                    int index;
                    while ((index = s.indexOf(40, matchParen)) != -1) {
                        matchParen = this.matchParen(s, index);
                        if (matchParen == -1) {
                            break;
                        }
                        final MenuItem menuItem2 = this.parseMenuItem(s.substring(index + 1, matchParen), font, actionListener);
                        if (menuItem2 != null) {
                            ((Menu)menuItem).add(menuItem2);
                        }
                        else {
                            ((Menu)menuItem).insertSeparator(((Menu)menuItem).getItemCount());
                        }
                    }
                    if (((Menu)menuItem).getItemCount() == 0) {
                        menuItem = new MenuItem(quoted[0]);
                    }
                    if (font != null) {
                        menuItem.setFont(font);
                    }
                }
            }
        }
        return menuItem;
    }
    
    private int[] createFace(final int n, final int n2, final Color color) {
        final int n3 = n * n2;
        final int n4 = 0xFF000000 | color.getRGB();
        final int[] array = new int[n3];
        for (int i = 0; i < n3; ++i) {
            array[i] = n4;
        }
        return array;
    }
    
    private int getButtonCount() {
        int n;
        String value;
        for (n = 0; this.getParameter(PushIt.buttonParams[0][0].toString().concat(value = String.valueOf(n))) != null || this.getParameter(PushIt.buttonParams[10][0].toString().concat(value)) != null || this.getParameter(PushIt.buttonParams[13][0].toString().concat(value)) != null || this.getParameter(PushIt.buttonParams[9][0].toString().concat(value)) != null || this.parameters.getProperty(PushIt.buttonParams[0][0].toString().concat(value)) != null || this.parameters.getProperty(PushIt.buttonParams[10][0].toString().concat(value)) != null || this.parameters.getProperty(PushIt.buttonParams[13][0].toString().concat(value)) != null || this.parameters.getProperty(PushIt.buttonParams[9][0].toString().concat(value)) != null; ++n) {}
        return n;
    }
    
    public void setBackground(final Color color) {
        if (color != null) {
            this.bgImage = null;
            this.bgColor = color;
            if (this.thread == null) {
                final Component[] components = this.getComponents();
                for (int i = 0; i < components.length; ++i) {
                    ((PushButton)components[i]).setBackground(color);
                }
            }
        }
        this.repaint();
    }
    
    public void setBackground(final Image bgImage) {
        this.bgImage = bgImage;
        if (bgImage != null && this.waitForImage(bgImage) != null) {
            this.bgWidth = bgImage.getWidth(null);
            this.bgHeight = bgImage.getHeight(null);
            this.bgPixels = this.getPixels(bgImage, this.bgWidth, this.bgHeight);
            if (this.thread == null) {
                final Component[] components = this.getComponents();
                for (int i = 0; i < components.length; ++i) {
                    ((PushButton)components[i]).setBackground(this.bgPixels, this.bgWidth, this.bgHeight, true);
                }
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgImage != null) {
            for (int i = 0; i < this.height; i += this.bgHeight) {
                for (int j = 0; j < this.width; j += this.bgWidth) {
                    graphics.drawImage(this.bgImage, j, i, null);
                }
            }
        }
        else {
            graphics.setColor(this.bgColor);
            graphics.fillRect(0, 0, this.width, this.height);
        }
        if (this.loadBounds.width > 0) {
            graphics.drawImage(this.loadImage, this.loadBounds.x, this.loadBounds.y, null);
        }
        super.paint(graphics);
    }
    
    public void setHAlignment(final int n) {
        if (this.hAlign != n) {
            this.hAlign = Math.abs(n) % 3;
            this.validate();
        }
    }
    
    public void setVAlignment(final int n) {
        if (this.vAlign != n) {
            this.vAlign = Math.abs(n) % 3;
            this.validate();
        }
    }
    
    public int getHAlignment() {
        return this.hAlign;
    }
    
    public int getVAlignment() {
        return this.vAlign;
    }
    
    private Font parseFont(final String s, final Font font) {
        Font font2 = font;
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        if (s != null) {
            final String[] splitValues = this.splitValues(s, " \n\t\r");
            try {
                font2 = new Font(fontList[Integer.parseInt(splitValues[0]) % fontList.length], Integer.parseInt(splitValues[1]), Integer.parseInt(splitValues[2]));
            }
            catch (Exception ex) {
                font2 = font;
            }
        }
        return font2;
    }
    
    public PushIt() {
        this.envValues = new Object[] { null, new Color(-5263441), new Integer(115), new Double(0.3), new Integer(1), new Integer(0), new Integer(0), new String("Loading..."), new Font("SansSerif", 0, 11), new Color(-1) };
        this.buttonValues = new Object[] { null, null, null, new Double(1.0), null, null, null, null, null, null, null, null, null, null, null, null, new Double(1.0), null, null, null, null, null, new Integer(10), new Integer(10), new Font("SansSerif", 0, 18), new Integer(10), null, null, new String("_self"), null, null, null, null, null, new Integer(15), new Color(-65473), new Double(0.0), new Integer(1), new Integer(1), null, new Integer(1), new Point(0, 0), null, null, new Double(0.4), new Double(0.3), new Integer(50) };
        this.parameters = new Properties();
    }
    
    private Dimension getLabelSize(final String s, final FontMetrics fontMetrics) {
        final int n = fontMetrics.getAscent() + fontMetrics.getDescent();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r");
        final Dimension dimension = new Dimension();
        while (stringTokenizer.hasMoreTokens()) {
            final int stringWidth = fontMetrics.stringWidth(stringTokenizer.nextToken());
            if (dimension.width < stringWidth) {
                dimension.width = stringWidth;
            }
            final Dimension dimension2 = dimension;
            dimension2.height += n;
        }
        final Dimension dimension3 = dimension;
        dimension3.width += 2;
        return dimension;
    }
    
    private double makeUnity(double n) {
        if (n < 0.0) {
            n = 0.0;
        }
        else if (n > 1.0) {
            n = 1.0;
        }
        return n;
    }
    
    private Image parseImage(final String s, final Image image) {
        final URL url = this.parseURL(s, null, true);
        Image waitForImage = image;
        if (url != null) {
            waitForImage = this.waitForImage(this.getImage(url));
        }
        return waitForImage;
    }
    
    static {
        IMAGE_T = new Integer(0);
        COLOR_T = new Integer(1);
        INT_T = new Integer(2);
        UNITY_T = new Integer(3);
        TEXT_T = new Integer(4);
        URL_T = new Integer(5);
        FONT_T = new Integer(6);
        CLIP_T = new Integer(7);
        PAIR_T = new Integer(8);
        OTHER_T = new Integer(-1);
        envParams = new Object[][] { { "bgImage", PushIt.IMAGE_T }, { "bgColor", PushIt.COLOR_T }, { "angle", PushIt.INT_T }, { "intensity", PushIt.UNITY_T }, { "hAlignment", PushIt.INT_T }, { "vAlignment", PushIt.INT_T }, { "arrangement", PushIt.INT_T }, { "loadText", PushIt.TEXT_T }, { "loadFont", PushIt.FONT_T }, { "loadColor", PushIt.COLOR_T } };
        buttonParams = new Object[][] { { "faceImage", PushIt.IMAGE_T }, { "hoverFaceImage", PushIt.IMAGE_T }, { "pushedFaceImage", PushIt.IMAGE_T }, { "faceOpacity", PushIt.UNITY_T }, { "hoverFaceOpacity", PushIt.UNITY_T }, { "pushedFaceOpacity", PushIt.UNITY_T }, { "faceColor", PushIt.COLOR_T }, { "hoverFaceColor", PushIt.COLOR_T }, { "pushedFaceColor", PushIt.COLOR_T }, { "faceSize", PushIt.PAIR_T }, { "labelImage", PushIt.IMAGE_T }, { "hoverLabelImage", PushIt.IMAGE_T }, { "pushedLabelImage", PushIt.IMAGE_T }, { "labelText", PushIt.TEXT_T }, { "hoverLabelText", PushIt.TEXT_T }, { "pushedLabelText", PushIt.TEXT_T }, { "labelOpacity", PushIt.UNITY_T }, { "hoverLabelOpacity", PushIt.UNITY_T }, { "pushedLabelOpacity", PushIt.UNITY_T }, { "labelColor", PushIt.COLOR_T }, { "hoverLabelColor", PushIt.COLOR_T }, { "pushedLabelColor", PushIt.COLOR_T }, { "hMargin", PushIt.INT_T }, { "vMargin", PushIt.INT_T }, { "labelFont", PushIt.FONT_T }, { "elevation", PushIt.INT_T }, { "description", PushIt.TEXT_T }, { "action", PushIt.OTHER_T }, { "target", PushIt.TEXT_T }, { "onSound", PushIt.CLIP_T }, { "offSound", PushIt.CLIP_T }, { "pushedSound", PushIt.CLIP_T }, { "poppedSound", PushIt.CLIP_T }, { "triggerSound", PushIt.CLIP_T }, { "blinkPeriod", PushIt.INT_T }, { "blinkColor", PushIt.COLOR_T }, { "blinkAmount", PushIt.UNITY_T }, { "faceSmooth", PushIt.INT_T }, { "labelSmooth", PushIt.INT_T }, { "menuFont", PushIt.FONT_T }, { "labelAlignment", PushIt.INT_T }, { "labelOffset", PushIt.PAIR_T }, { "hoverLabelOffset", PushIt.PAIR_T }, { "pushedLabelOffset", PushIt.PAIR_T }, { "friction", PushIt.UNITY_T }, { "speed", PushIt.UNITY_T }, { "delay", PushIt.INT_T } };
    }
    
    private String parseText(String concat, final String s) {
        String sval = s;
        if (concat != null) {
            concat = new String("\"").concat(concat).concat("\"");
            try {
                final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(concat));
                streamTokenizer.nextToken();
                sval = streamTokenizer.sval;
            }
            catch (Exception ex) {
                sval = s;
            }
        }
        return sval;
    }
    
    private Point parsePair(final String s, final Point point) {
        Point point2 = point;
        if (s != null) {
            final String[] splitValues = this.splitValues(s, " \n\t\r");
            try {
                point2 = new Point(Integer.parseInt(splitValues[0]), Integer.parseInt(splitValues[1]));
            }
            catch (Exception ex) {
                point2 = point;
            }
        }
        return point2;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return null;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    private void createSeedSource() {
        final Dimension dimension = new Dimension();
        this.seed = new ButtonImageSource((int)this.envValues[2]);
        if (this.buttonValues[0] != null) {
            this.seed.setFace((Image)this.buttonValues[0], (int)this.buttonValues[37], true);
        }
        else if (this.buttonValues[9] != null) {
            dimension.width = Math.abs(((Point)this.buttonValues[9]).x);
            dimension.height = Math.abs(((Point)this.buttonValues[9]).y);
            this.seed.setFace(this.createFace(dimension.width, dimension.height, (Color)this.buttonValues[6]), dimension.width, dimension.height, (int)this.buttonValues[37], true);
        }
        if (this.buttonValues[10] != null) {
            this.seed.setLabel((Image)this.buttonValues[10], (int)this.buttonValues[38], true);
        }
        else if (this.buttonValues[13] != null) {
            this.seed.setLabel(this.createLabel((String)this.buttonValues[13], (Font)this.buttonValues[24], (int)this.buttonValues[40], (Color)this.buttonValues[19], 0.0, false, dimension), dimension.width, dimension.height, (int)this.buttonValues[38], false);
        }
        this.seed.setLabelAlignment((int)this.buttonValues[40]);
        this.seed.setLabelOffset(((Point)this.buttonValues[41]).x, ((Point)this.buttonValues[41]).y);
        this.seed.setFaceOpacity((double)this.buttonValues[3]);
        this.seed.setLabelOpacity((double)this.buttonValues[16]);
        this.seed.setFaceColor((Color)this.buttonValues[6], 0.501);
        this.seed.setLabelColor((Color)this.buttonValues[19], 0.501);
    }
    
    private synchronized void getButton(final int n) {
        final PushButton pushButton = this.buttons[n];
        final ButtonImageSource[] array = new ButtonImageSource[3];
        final Object[] array2 = new Object[this.buttonValues.length];
        final String value = String.valueOf(n);
        final Point[] array3 = new Point[3];
        final int intValue = (int)this.envValues[2];
        final Dimension dimension = new Dimension();
        this.getParameterValues(PushIt.buttonParams, array2, this.buttonValues, value);
        final int n2 = (int)(Math.log(2 * (int)array2[25] + 2) * Math.exp(-(double)this.envValues[3]));
        array[0] = (ButtonImageSource)this.seed.clone();
        if (array2[9] != null && array2[0] == this.buttonValues[0]) {
            dimension.width = Math.abs(((Point)array2[9]).x);
            dimension.height = Math.abs(((Point)array2[9]).y);
            array[0].setFace(this.createFace(dimension.width, dimension.height, (Color)array2[6]), dimension.width, dimension.height, (int)array2[37], true);
        }
        else if (array2[0] != null && array2[0] != this.buttonValues[0]) {
            array[0].setFace((Image)array2[0], (int)array2[37], true);
        }
        if (array2[10] != null && array2[10] != this.buttonValues[10]) {
            array[0].setLabel((Image)array2[10], (int)array2[38], true);
        }
        else if (array2[13] != null) {
            array[0].setLabel(this.createLabel((String)array2[13], (Font)array2[24], (int)array2[40], (Color)array2[19], 0.0, false, dimension), dimension.width, dimension.height, (int)array2[38], false);
        }
        array[0].setLabelAlignment((int)array2[40]);
        array[0].setLabelOffset(((Point)array2[41]).x, ((Point)array2[41]).y);
        array[0].setFaceOpacity((double)array2[3]);
        array[0].setLabelOpacity((double)array2[16]);
        array[0].setFaceColor((Color)array2[6], 0.501);
        array[0].setLabelColor((Color)array2[19], 0.501);
        array[0].setShadow(n2, (int)array2[25], (double)this.envValues[3]);
        array[1] = (ButtonImageSource)array[0].clone();
        if (array2[1] != null) {
            array[1].setFace((Image)array2[1], (int)array2[37], true);
        }
        if (array2[11] != null) {
            array[1].setLabel((Image)array2[11], (int)array2[38], true);
        }
        else if (array2[14] != null) {
            if (array2[20] == null) {
                array2[20] = array2[19];
            }
            array[1].setLabel(this.createLabel((String)array2[14], (Font)array2[24], (int)array2[40], (Color)array2[20], 0.0, false, dimension), dimension.width, dimension.height, (int)array2[38], false);
        }
        if (array2[42] != null) {
            array[1].setLabelOffset(((Point)array2[42]).x, ((Point)array2[42]).y);
        }
        if (array2[4] != null) {
            array[1].setFaceOpacity((double)array2[4]);
        }
        if (array2[17] != null) {
            array[1].setLabelOpacity((double)array2[17]);
        }
        if (array2[7] != null) {
            array[1].setFaceColor((Color)array2[7], 0.501);
        }
        if (array2[20] != null) {
            array[1].setLabelColor((Color)array2[20], 0.501);
        }
        array[2] = (ButtonImageSource)array[1].clone();
        if (array2[2] != null) {
            array[2].setFace((Image)array2[2], (int)array2[37], true);
        }
        if (array2[12] != null) {
            array[2].setLabel((Image)array2[12], (int)array2[38], true);
        }
        else if (array2[15] != null) {
            if (array2[21] == null) {
                array2[21] = array2[20];
            }
            array[2].setLabel(this.createLabel((String)array2[15], (Font)array2[24], (int)array2[40], (Color)array2[21], 0.0, false, dimension), dimension.width, dimension.height, (int)array2[38], false);
        }
        if (array2[43] != null) {
            array[2].setLabelOffset(((Point)array2[43]).x, ((Point)array2[43]).y);
        }
        if (array2[5] != null) {
            array[2].setFaceOpacity((double)array2[5]);
        }
        if (array2[18] != null) {
            array[2].setLabelOpacity((double)array2[18]);
        }
        if (array2[8] != null) {
            array[2].setFaceColor((Color)array2[8], 0.501);
        }
        if (array2[21] != null) {
            array[2].setLabelColor((Color)array2[21], 0.501);
        }
        array[2].setShadow(n2, (int)array2[25] / 3, 0.4 + 0.6 * (double)this.envValues[3]);
        final double n3 = Math.log(Math.abs((int)array2[25]) + 1) / 1.5;
        array3[2] = new Point(-(int)(n2 * Math.cos(intValue * 3.141592653589793 / 180.0)), (int)(n2 * Math.sin(intValue * 3.141592653589793 / 180.0)));
        pushButton.setFaces(array, array3);
        pushButton.setMargins((int)array2[22], (int)array2[23]);
        if (this.bgImage != null) {
            pushButton.setBackground(this.bgPixels, this.bgWidth, this.bgHeight, true);
        }
        else {
            pushButton.setBackground(this.bgColor);
        }
        if ((double)array2[36] != 0.0) {
            pushButton.setBlink((Color)array2[35], (int)array2[34], (double)array2[36]);
        }
        pushButton.description = (String)array2[26];
        pushButton.target = (String)array2[28];
        if (array2[27] != null) {
            if (pushButton.target.toLowerCase().indexOf("applet:") == -1 && ((String)array2[27]).trim().indexOf(40) == 0) {
                pushButton.setPopupMenu(this.parsePopupMenu((String)array2[27], (Font)array2[39], pushButton));
            }
            else {
                pushButton.addActionListener(pushButton);
                pushButton.setActionCommand((String)array2[27]);
            }
        }
        if (array2[45] != null) {
            pushButton.setSpeed((double)array2[45]);
        }
        if (array2[46] != null) {
            pushButton.setDelay((int)array2[46]);
        }
        pushButton.setFriction((double)array2[44]);
        pushButton.onURL = (URL)array2[29];
        pushButton.offURL = (URL)array2[30];
        pushButton.pushedURL = (URL)array2[31];
        pushButton.poppedURL = (URL)array2[32];
        pushButton.triggerURL = (URL)array2[33];
        pushButton.loadImage = this.loadImage;
        pushButton.loadBounds = this.loadBounds;
    }
    
    public synchronized void run() {
        this.repaint();
        while (this.addedN < this.totalN) {
            final int n = this.totalN - this.addedN;
            this.showStatus("(" + String.valueOf(n) + " button" + ((n > 1) ? "s " : " ") + "remaining) Preparing button ...");
            this.getButton(this.addedN);
            this.validate();
            ++this.addedN;
        }
        this.showStatus("");
        this.stop();
        this.repaint();
        for (int i = 0; i < this.buttons.length; ++i) {
            this.buttons[i].repaint(new Rectangle(this.width, this.height));
        }
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void init() {
        final String parameter = this.getParameter("parameterFile");
        final Dimension dimension = new Dimension();
        if (parameter != null) {
            this.showStatus("Reading data ...");
            try {
                final InputStream openStream = this.parseURL(parameter, null, true).openStream();
                this.parameters.load(openStream);
                openStream.close();
            }
            catch (Exception ex) {}
            this.showStatus("");
        }
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.getParameterValues(PushIt.envParams, this.envValues, this.envValues, null);
        this.getParameterValues(PushIt.buttonParams, this.buttonValues, this.buttonValues, null);
        this.hAlign = (int)this.envValues[4] % 3;
        this.vAlign = (int)this.envValues[5] % 3;
        this.arrangement = (int)this.envValues[6] % 2;
        this.setLayout(this);
        this.createSeedSource();
        this.setBackground((Color)this.envValues[1]);
        if (this.envValues[0] != null) {
            this.setBackground((Image)this.envValues[0]);
        }
        this.totalN = this.getButtonCount();
        this.loadImage = this.createImage(new MemoryImageSource(dimension.width, dimension.height, this.createLabel((String)this.envValues[7], (Font)this.envValues[8], 0, (Color)this.envValues[9], 0.25, true, dimension), 0, dimension.width));
        this.loadBounds = new Rectangle((this.width - this.loadImage.getWidth(null)) / 2, (this.height - this.loadImage.getHeight(null)) / 2, this.loadImage.getWidth(null), this.loadImage.getHeight(null));
        this.buttons = new PushButton[this.totalN];
        for (int i = 0; i < this.totalN; ++i) {
            this.add(this.buttons[i] = new PushButton(), i);
        }
    }
    
    private Color parseColor(final String s, final Color color) {
        Color color2 = color;
        if (s != null) {
            if (s.indexOf(" ") != -1) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s);
                try {
                    int n = 255;
                    while (stringTokenizer.hasMoreTokens()) {
                        n = (n << 8 | Integer.parseInt(stringTokenizer.nextToken()));
                    }
                    color2 = new Color(n);
                }
                catch (Exception ex) {
                    color2 = color;
                }
            }
            else {
                try {
                    color2 = new Color(0xFF000000 | Integer.parseInt(s, 16));
                }
                catch (Exception ex2) {
                    color2 = color;
                }
            }
        }
        return color2;
    }
}
