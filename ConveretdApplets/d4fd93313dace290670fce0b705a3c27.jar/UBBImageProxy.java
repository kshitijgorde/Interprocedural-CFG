import java.util.Enumeration;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class UBBImageProxy
{
    private Image theImage;
    private String imageFileName;
    private UBBImageFactory parent;
    private UBBErrorHandler error;
    private Vector listeners;
    private long lastUpdate;
    private int infoFlags;
    private int width;
    private int height;
    private boolean subImageInit;
    private int subImageX;
    private int subImageY;
    private static final int UPDATES_DONE = 224;
    private static final int IMAGE_READY = 35;
    private static final int UPDATE_FREQ = 250;
    private static final int TOTAL_EFFECTS = 6;
    private static final int BORDER_EFFECT = 1;
    private static final int SHIFT_EFFECT = 2;
    private static final int ADJUST_EFFECT = 4;
    private static final int FILTER_EFFECT = 8;
    private static final int TINT_EFFECT = 16;
    private static final int EMBOSS_EFFECT = 32;
    
    public Image getImageCopy(final int n, final int n2, final int n3, final int n4) {
        return this.createSubImage(n, n2, n3, n4);
    }
    
    public Image getImageCopy(final int n, final int n2) {
        Image scaledInstance = null;
        final Image subImage = this.createSubImage(0, 0, this.width, this.height);
        if (subImage != null) {
            scaledInstance = subImage.getScaledInstance((n == 0) ? this.width : n, (n2 == 0) ? this.height : n2, 4);
            subImage.flush();
        }
        return scaledInstance;
    }
    
    private int[] filter(final int[] array, final boolean b, final boolean b2, final boolean b3) {
        final boolean b4 = (!b && !b2 && !b3) || (b && b2 && b3);
        for (int i = 0; i < this.width * this.height; ++i) {
            final int n = array[i];
            final int n2 = (((n & 0xFF0000) >> 16) * 3 + ((n & 0xFF00) >> 8) * 4 + (n & 0xFF) * 2) / 9;
            int n3 = -16777216;
            if (b4) {
                n3 += (n2 << 16) + (n2 << 8) + n2;
            }
            else {
                if (b) {
                    n3 += n2 << 16;
                }
                if (b2) {
                    n3 += n2 << 8;
                }
                if (b3) {
                    n3 += n2;
                }
            }
            array[i] = n3;
        }
        return array;
    }
    
    protected synchronized void updateStatus(final int infoFlags, final int n, final int n2, final int width, final int height) {
        this.infoFlags = infoFlags;
        if ((infoFlags & 0x1) != 0x0) {
            this.width = width;
        }
        if ((infoFlags & 0x2) != 0x0) {
            this.height = height;
        }
        if ((infoFlags & 0xE0) != 0x0 || this.lastUpdate + 250L <= System.currentTimeMillis()) {
            this.doListenerUpdate();
            this.lastUpdate = System.currentTimeMillis();
        }
    }
    
    public Image scaleImage(final int width, final int height) {
        Image theImage = null;
        final Image imageCopy = this.getImageCopy(width, height);
        if (imageCopy != null) {
            theImage = this.theImage;
            this.theImage = imageCopy;
            this.width = width;
            this.height = height;
        }
        return theImage;
    }
    
    protected UBBImageProxy(final UBBImageFactory parent, final Image theImage, final String s, final Rectangle rectangle, final UBBErrorHandler error) {
        this.width = -1;
        this.height = -1;
        this.parent = parent;
        this.theImage = theImage;
        this.imageFileName = ((s == null) ? "<?>" : s);
        this.error = error;
        if (rectangle != null) {
            this.subImageInit = true;
            this.infoFlags = 35;
            this.subImageX = rectangle.x;
            this.subImageY = rectangle.y;
            this.width = rectangle.width;
            this.height = rectangle.height;
        }
    }
    
    public boolean isError() {
        return (this.infoFlags & 0xC0) != 0x0;
    }
    
    public String getImageName() {
        return this.imageFileName;
    }
    
    public void destroy() {
        if (this.theImage != null) {
            this.theImage.flush();
            this.theImage = null;
        }
        if (this.listeners != null) {
            this.listeners.removeAllElements();
            this.listeners = null;
        }
        this.parent = null;
        this.error = null;
    }
    
    public synchronized void removeUBBImageListener(final UBBImageListener ubbImageListener) {
        if (ubbImageListener != null && this.listeners != null) {
            this.listeners.removeElement(ubbImageListener);
            this.listeners.trimToSize();
            if (this.listeners.size() == 0) {
                this.listeners = null;
            }
        }
    }
    
    private Image createSubImage(final int n, final int n2, final int n3, final int n4) {
        Image image = null;
        if (this.subImageInit) {
            this.getImage();
        }
        if (this.theImage != null) {
            try {
                final int[] array = new int[n3 * n4];
                if (!new PixelGrabber(new FilteredImageSource(this.theImage.getSource(), new CropImageFilter(n, n2, n3, n4)), 0, 0, n3, n4, array, 0, n3).grabPixels()) {
                    throw new Exception("grabPixels");
                }
                image = this.parent.createImage(new MemoryImageSource(n3, n4, array, 0, n3));
            }
            catch (Exception ex) {
                this.error.notify(this.imageFileName, 0, "createSubImage", ex);
            }
        }
        return image;
    }
    
    private int[] shiftImage(int[] array, final int n, final int n2, final int n3) {
        final int[] array2 = array;
        array = new int[array2.length];
        if (n3 == 1) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
        else {
            for (int i = 0; i < array2.length; ++i) {
                array[i] = n3;
            }
        }
        int n4 = 0;
        for (int j = 0; j < this.height; ++j) {
            for (int k = 0; k < this.width; ++k) {
                final int n5 = k - n;
                final int n6 = j - n2;
                if (n5 >= 0 && n5 < this.width && n6 >= 0 && n6 < this.height) {
                    array[n4] = array2[this.width * n6 + n5];
                }
                ++n4;
            }
        }
        return array;
    }
    
    private int[] borders(final int[] array, final int n, int n2, int n3, int n4, int n5, int n6, final boolean b) {
        if (n2 == -2 && n4 == 0) {
            n4 = n5;
        }
        if (n3 == -2 && n5 == 0) {
            n5 = n4;
        }
        if (n2 == -2 && n3 != -2) {
            n2 = n3;
        }
        else if (n3 == -2 && n2 != -2) {
            n3 = n2;
        }
        else if (n2 == -2 && n3 == -2) {
            n3 = (n2 = 0);
        }
        if (n4 != 0) {
            n2 = 0;
        }
        if (n5 != 0) {
            n3 = 0;
        }
        if (b) {
            n6 = -n6;
            final int n7 = n2;
            n2 = n3;
            n3 = n7;
            final int n8 = n4;
            n4 = n5;
            n5 = n8;
        }
        final int n9 = (n4 != 0) ? n4 : n6;
        final int n10 = (n5 != 0) ? n5 : (-n6);
        try {
            int n11 = 0;
            for (int i = 0; i < n; ++i) {
                if (n11 < n) {
                    ++n11;
                }
                for (int j = 0; j < this.width; ++j) {
                    if (j < this.width - n11 && j >= n) {
                        final int n12 = j + i * this.width;
                        if (n2 == 0) {
                            final int n13 = array[n12];
                            array[n12] = (n13 & 0xFF000000) + (Math.max(Math.min(((n13 & 0xFF0000) >> 16) + n9, 255), 0) << 16) + (Math.max(Math.min(((n13 & 0xFF00) >> 8) + n9, 255), 0) << 8) + Math.max(Math.min((n13 & 0xFF) + n9, 255), 0);
                        }
                        else {
                            array[n12] = n2;
                        }
                    }
                    if (j >= n11 && j < this.width - n) {
                        final int n14 = j + (this.height - i - 1) * this.width;
                        if (n3 == 0) {
                            final int n15 = array[n14];
                            array[n14] = (n15 & 0xFF000000) + (Math.max(Math.min(((n15 & 0xFF0000) >> 16) + n10, 255), 0) << 16) + (Math.max(Math.min(((n15 & 0xFF00) >> 8) + n10, 255), 0) << 8) + Math.max(Math.min((n15 & 0xFF) + n10, 255), 0);
                        }
                        else {
                            array[n14] = n3;
                        }
                    }
                }
            }
            int n16 = 0;
            for (int k = 0; k < n; ++k) {
                if (n16 < n) {
                    ++n16;
                }
                for (int l = 0; l < this.height; ++l) {
                    if (l <= this.height - n16) {
                        final int n17 = k + l * this.width;
                        if (n2 == 0) {
                            final int n18 = array[n17];
                            array[n17] = (n18 & 0xFF000000) + (Math.max(Math.min(((n18 & 0xFF0000) >> 16) + n9, 255), 0) << 16) + (Math.max(Math.min(((n18 & 0xFF00) >> 8) + n9, 255), 0) << 8) + Math.max(Math.min((n18 & 0xFF) + n9, 255), 0);
                        }
                        else {
                            array[n17] = n2;
                        }
                    }
                    if (l >= n - n16) {
                        final int n19 = k + (this.width - n) + l * this.width;
                        if (n3 == 0) {
                            final int n20 = array[n19];
                            array[n19] = (n20 & 0xFF000000) + (Math.max(Math.min(((n20 & 0xFF0000) >> 16) + n10, 255), 0) << 16) + (Math.max(Math.min(((n20 & 0xFF00) >> 8) + n10, 255), 0) << 8) + Math.max(Math.min((n20 & 0xFF) + n10, 255), 0);
                        }
                        else {
                            array[n19] = n3;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.error.notify(this.imageFileName, 0, "Creating borders", null);
        }
        return array;
    }
    
    public void tile(final UBBImageProxy ubbImageProxy, final int n, final int n2, final Color color) {
        if (ubbImageProxy != null) {
            this.tile(ubbImageProxy.getImage(), n, n2, color);
        }
    }
    
    public void tile(final Image image, final int n, final int n2, final Color color) {
        if (image != null) {
            final Graphics graphics = image.getGraphics();
            if (graphics != null) {
                this.tile(graphics, n, n2, image.getWidth(null), image.getHeight(null), color);
                graphics.dispose();
            }
        }
    }
    
    public synchronized void addUBBImageListener(final UBBImageListener ubbImageListener) {
        if (ubbImageListener != null) {
            if (this.listeners == null) {
                this.listeners = new Vector(5, 5);
            }
            if (!this.listeners.contains(ubbImageListener)) {
                this.listeners.addElement(ubbImageListener);
            }
            ubbImageListener.managedImageUpdate(new UBBImageListenerEvent(this));
        }
    }
    
    public void tile(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (this.isReady() && graphics != null) {
            int i = n;
            if (this.subImageInit) {
                this.getImage();
            }
            if (this.theImage != null) {
                if (color != null && (n != 0 || n2 != 0)) {
                    graphics.setColor(color);
                    graphics.fillRect(0, 0, n3, n4);
                }
                while (i < n3) {
                    int n5 = n2;
                    graphics.drawImage(this.theImage, i, n5, null);
                    while ((n5 += this.height) < n4) {
                        graphics.drawImage(this.theImage, i, n5, null);
                    }
                    i += this.width;
                }
            }
        }
    }
    
    public int getStatus() {
        return this.infoFlags;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public synchronized Image getImage() {
        if (this.subImageInit) {
            this.subImageInit = false;
            this.theImage = this.createSubImage(this.subImageX, this.subImageY, this.width, this.height);
        }
        return this.theImage;
    }
    
    public Image changeImage(final int n, final int n2) {
        return this.changeImage(this.parent.createImage(n, n2));
    }
    
    public synchronized Image changeImage(final Image theImage) {
        Image theImage2 = null;
        if (theImage != null) {
            this.width = theImage.getWidth(null);
            this.height = theImage.getHeight(null);
            if (this.width > 0 && this.height > 0) {
                theImage2 = this.theImage;
                this.theImage = theImage;
                this.subImageInit = false;
                this.infoFlags = 35;
                this.doListenerUpdate();
            }
        }
        return theImage2;
    }
    
    public Image applyEffects(final String s, final boolean b) {
        Image image = null;
        int[] array = null;
        if (this.theImage != null && s != null) {
            if (this.subImageInit) {
                this.getImage();
            }
            try {
                array = new int[this.width * this.height];
                if (!new PixelGrabber(this.theImage, 0, 0, this.width, this.height, array, 0, this.width).grabPixels()) {
                    throw new Exception("grabPixels");
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), ")");
                int n = 0;
                int n2 = 0;
                final int[] array2 = new int[6];
                int int1 = 100;
                int rgb = 0;
                boolean b2 = false;
                int int2 = 1;
                int int3 = 0;
                int n3 = -2;
                int n4 = -2;
                int n5 = 0;
                int n6 = 0;
                int n7 = 99999;
                int n8 = 99999;
                int rgb2 = 1;
                int n9 = 0;
                int n10 = 0;
                boolean b3 = false;
                boolean b4 = false;
                boolean b5 = false;
                int int4 = 45;
                int int5 = 100;
                int int6 = 0;
                int rgb3 = 0;
                int int7 = 100;
                while (stringTokenizer.hasMoreTokens()) {
                    final String trim = stringTokenizer.nextToken().trim();
                    final int index = trim.indexOf(40);
                    if (index > 0) {
                        final String substring = trim.substring(0, index);
                        if (index > trim.length() - 2) {
                            continue;
                        }
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(trim.substring(index + 1), " ,");
                        final int countTokens = stringTokenizer2.countTokens();
                        final String[] array3 = new String[countTokens];
                        final String[] array4 = new String[countTokens];
                        for (int i = 0; i < countTokens; ++i) {
                            final String trim2 = stringTokenizer2.nextToken().trim();
                            final int index2 = trim2.indexOf(61);
                            if (index2 > 0) {
                                array3[i] = trim2.substring(0, index2).trim();
                                if (index2 < trim2.length() - 1) {
                                    array4[i] = trim2.substring(index2 + 1).trim();
                                }
                            }
                            else {
                                array3[i] = trim2;
                            }
                        }
                        int j = 0;
                        final int length = array3.length;
                        int n11 = -1;
                        switch (substring.charAt(0)) {
                            case 'e': {
                                n11 = 32;
                                while (j < length) {
                                    try {
                                        switch (array3[j].charAt(0)) {
                                            case 'A':
                                            case 'a': {
                                                int4 = Integer.parseInt(array4[j]);
                                                break;
                                            }
                                            case 'B':
                                            case 'b': {
                                                int5 = Integer.parseInt(array4[j]);
                                                break;
                                            }
                                            case 'G':
                                            case 'g': {
                                                int6 = Integer.parseInt(array4[j]);
                                                break;
                                            }
                                            case 'C':
                                            case 'c': {
                                                rgb3 = UBBImageFactory.parseColor(array4[j]).getRGB();
                                                break;
                                            }
                                            case 'P':
                                            case 'p': {
                                                int7 = Integer.parseInt(array4[j]);
                                                break;
                                            }
                                            default: {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    catch (Exception ex) {
                                        this.error.notify(this.imageFileName, 0, "EMBOSS " + array3[j] + "=" + array4[j], ex);
                                    }
                                    ++j;
                                }
                                break;
                            }
                            case 't': {
                                n11 = 16;
                                while (j < length) {
                                    try {
                                        switch (array3[j].charAt(0)) {
                                            case 'C':
                                            case 'c': {
                                                rgb = UBBImageFactory.parseColor(array4[j]).getRGB();
                                                break;
                                            }
                                            case 'P':
                                            case 'p': {
                                                int1 = Integer.parseInt(array4[j]);
                                                break;
                                            }
                                            default: {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    catch (Exception ex2) {
                                        this.error.notify(this.imageFileName, 0, "TINT " + array3[j] + "=" + array4[j], ex2);
                                    }
                                    ++j;
                                }
                                break;
                            }
                            case 'b': {
                                n11 = 1;
                                int n12 = 0;
                                while (j < length) {
                                    try {
                                        switch (array3[j].charAt(0)) {
                                            case 'R':
                                            case 'r': {
                                                b2 = true;
                                                break;
                                            }
                                            case 'S':
                                            case 's': {
                                                int2 = Integer.parseInt(array4[j]);
                                                break;
                                            }
                                            case 'A':
                                            case 'a': {
                                                String substring2 = array4[j];
                                                final char char1 = substring2.charAt(0);
                                                if (char1 == '+' || char1 == '-') {
                                                    substring2 = substring2.substring(1);
                                                }
                                                int3 = Integer.parseInt(substring2);
                                                if (char1 == '-') {
                                                    int3 = -int3;
                                                    break;
                                                }
                                                break;
                                            }
                                            case 'T':
                                            case 't': {
                                                n12 = 1;
                                            }
                                            case 'B':
                                            case 'b': {
                                                final char char2 = array4[j].charAt(0);
                                                if (char2 == '+' || char2 == '-') {
                                                    int int8 = Integer.parseInt(array4[j].substring(1));
                                                    if (char2 == '-') {
                                                        int8 = -int8;
                                                    }
                                                    if (n12 != 0) {
                                                        n5 = int8;
                                                    }
                                                    else {
                                                        n6 = int8;
                                                    }
                                                }
                                                else {
                                                    final int rgb4 = UBBImageFactory.parseColor(array4[j]).getRGB();
                                                    if (n12 != 0) {
                                                        n3 = rgb4;
                                                    }
                                                    else {
                                                        n4 = rgb4;
                                                    }
                                                }
                                                n12 = 0;
                                                break;
                                            }
                                            default: {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    catch (Exception ex3) {
                                        this.error.notify(this.imageFileName, 0, "BORDER " + array3[j] + "=" + array4[j], ex3);
                                    }
                                    ++j;
                                }
                                break;
                            }
                            case 'f': {
                                n11 = 8;
                                while (j < length) {
                                    try {
                                        switch (array3[j].charAt(0)) {
                                            case 'C':
                                            case 'c': {
                                                final String lowerCase = array4[j].toLowerCase();
                                                if (lowerCase.indexOf(120) >= 0) {
                                                    b4 = (b3 = (b5 = true));
                                                    break;
                                                }
                                                if (lowerCase.indexOf(114) >= 0) {
                                                    b3 = true;
                                                }
                                                if (lowerCase.indexOf(103) >= 0) {
                                                    b4 = true;
                                                }
                                                if (lowerCase.indexOf(98) >= 0) {
                                                    b5 = true;
                                                    break;
                                                }
                                                break;
                                            }
                                            default: {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    catch (Exception ex4) {
                                        this.error.notify(this.imageFileName, 0, "FILTER " + array3[j] + "=" + array4[j], ex4);
                                    }
                                    ++j;
                                }
                                break;
                            }
                            case 'a': {
                                n11 = 4;
                                while (j < length) {
                                    try {
                                        switch (array3[j].charAt(0)) {
                                            case 'B':
                                            case 'C':
                                            case 'b':
                                            case 'c': {
                                                String substring3 = array4[j];
                                                final char char3 = substring3.charAt(0);
                                                if (char3 == '+' || char3 == '-') {
                                                    substring3 = substring3.substring(1);
                                                }
                                                int int9 = Integer.parseInt(substring3);
                                                if (char3 == '-') {
                                                    int9 = -int9;
                                                }
                                                if (array3[j].charAt(0) == 'b' || array3[j].charAt(0) == 'B') {
                                                    n9 = int9;
                                                    break;
                                                }
                                                n10 = int9;
                                                break;
                                            }
                                            default: {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    catch (Exception ex5) {
                                        this.error.notify(this.imageFileName, 0, "ADJUST " + array3[j] + "=" + array4[j], ex5);
                                    }
                                    ++j;
                                }
                                break;
                            }
                            case 's': {
                                n11 = 2;
                                while (j < length) {
                                    try {
                                        switch (array3[j].charAt(0)) {
                                            case 'X':
                                            case 'Y':
                                            case 'x':
                                            case 'y': {
                                                String substring4 = array4[j];
                                                final char char4 = substring4.charAt(0);
                                                if (char4 == '+' || char4 == '-') {
                                                    substring4 = substring4.substring(1);
                                                }
                                                int int10 = Integer.parseInt(substring4);
                                                if (char4 == '-') {
                                                    int10 = -int10;
                                                }
                                                if (array3[j].charAt(0) == 'x' || array3[j].charAt(0) == 'X') {
                                                    n7 = int10;
                                                    break;
                                                }
                                                n8 = int10;
                                                break;
                                            }
                                            case 'F':
                                            case 'f': {
                                                rgb2 = UBBImageFactory.parseColor(array4[j]).getRGB();
                                                break;
                                            }
                                            default: {
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                    }
                                    catch (Exception ex6) {
                                        this.error.notify(this.imageFileName, 0, "SHIFT " + array3[j] + "=" + array4[j], ex6);
                                    }
                                    ++j;
                                }
                                break;
                            }
                            default: {
                                this.error.notify(this.imageFileName, 0, "Unknown effect type: " + substring, null);
                                break;
                            }
                        }
                        if (n11 < 0 || (n & n11) != 0x0) {
                            continue;
                        }
                        n |= n11;
                        array2[n2++] = n11;
                    }
                    else {
                        this.error.notify(this.imageFileName, 0, "Unknown effect: " + trim, null);
                    }
                }
                for (int k = 0; k < n2; ++k) {
                    switch (array2[k]) {
                        case 16: {
                            array = this.tint(array, rgb, int1);
                            break;
                        }
                        case 1: {
                            array = this.borders(array, int2, n3, n4, n5, n6, int3, b2);
                            break;
                        }
                        case 2: {
                            if (n7 != 99999 || n8 != 99999) {
                                if (n7 == 99999) {
                                    n7 = n8;
                                }
                                if (n8 == 99999) {
                                    n8 = n7;
                                }
                                array = this.shiftImage(array, n7, n8, rgb2);
                                break;
                            }
                            break;
                        }
                        case 4: {
                            array = this.adjust(array, n9, n10);
                            break;
                        }
                        case 8: {
                            array = this.filter(array, b3, b4, b5);
                            break;
                        }
                        case 32: {
                            array = this.emboss(array, int4, int5, int6, rgb3, int7);
                            break;
                        }
                    }
                }
            }
            catch (Exception ex7) {
                array = null;
                this.error.notify(this.imageFileName, 0, "Apply effects", null);
            }
            if (array != null) {
                image = this.parent.createImage(this.width, this.height);
                if (image != null) {
                    final Graphics graphics = image.getGraphics();
                    if (graphics != null) {
                        final Image image2 = this.parent.createImage(new MemoryImageSource(this.width, this.height, array, 0, this.width));
                        graphics.drawImage(image2, 0, 0, null);
                        graphics.dispose();
                        image2.flush();
                    }
                    else {
                        image = null;
                    }
                }
                if (image == null) {
                    this.error.notify(this.imageFileName, 0, "Create from pixels", null);
                }
                else if (!b) {
                    this.theImage = image;
                }
            }
        }
        if ((image == null && this.theImage != null) || array == null) {
            image = (b ? this.getImageCopy() : this.theImage);
        }
        return image;
    }
    
    private void doListenerUpdate() {
        if (this.listeners != null) {
            final Enumeration<UBBImageListener> elements = (Enumeration<UBBImageListener>)this.listeners.elements();
            final UBBImageListenerEvent ubbImageListenerEvent = new UBBImageListenerEvent(this);
            while (elements.hasMoreElements()) {
                try {
                    elements.nextElement().managedImageUpdate(ubbImageListenerEvent);
                }
                catch (Exception ex) {
                    this.error.notify(this.imageFileName, 0, "managedImageUpdate", ex);
                }
            }
        }
    }
    
    public boolean isReady() {
        return (this.infoFlags & 0x20) != 0x0 && !this.isError();
    }
    
    private int[] adjust(final int[] array, final int n, int n2) {
        final int n3 = this.width * this.height;
        long n4 = 0L;
        if (n != 0 || n2 != 0) {
            for (int i = 0; i < n3; ++i) {
                final int n5 = array[i];
                int max = (n5 & 0xFF0000) >> 16;
                int max2 = (n5 & 0xFF00) >> 8;
                int max3 = n5 & 0xFF;
                if (n != 0) {
                    max = Math.max(Math.min(255, max + max * n / 100), 0);
                    max2 = Math.max(Math.min(255, max2 + max2 * n / 100), 0);
                    max3 = Math.max(Math.min(255, max3 + max3 * n / 100), 0);
                    array[i] = -16777216 + (max << 16) + (max2 << 8) + max3;
                }
                if (n2 != 0) {
                    n4 += (max + max2 + max3) / 3;
                }
            }
            if (n2 != 0) {
                final int n6 = (int)(n4 / n3);
                boolean b = false;
                if (n2 < 0) {
                    b = true;
                    n2 = -n2;
                }
                for (int j = 0; j < n3; ++j) {
                    final int n7 = array[j];
                    final int n8 = (n7 & 0xFF0000) >> 16;
                    final int n9 = (n7 & 0xFF00) >> 8;
                    final int n10 = n7 & 0xFF;
                    final int n11 = (n8 + n9 + n10) / 3;
                    int n12;
                    if (n11 <= n6) {
                        if (b) {
                            n12 = (n6 - n11) * n2;
                        }
                        else {
                            n12 = -(n11 * n2);
                        }
                    }
                    else if (b) {
                        n12 = -((n11 - n6) * n2);
                    }
                    else {
                        n12 = (255 - n11) * n2;
                    }
                    final int n13 = n12 / 100;
                    array[j] = -16777216 + (Math.max(Math.min(255, n8 + n13), 0) << 16) + (Math.max(Math.min(255, n9 + n13), 0) << 8) + Math.max(Math.min(255, n10 + n13), 0);
                }
            }
        }
        return array;
    }
    
    private int[] tint(final int[] array, final int n, final int n2) {
        final int n3 = ((n & 0xFF0000) >> 16) * n2 / 100;
        final int n4 = ((n & 0xFF00) >> 8) * n2 / 100;
        final int n5 = (n & 0xFF) * n2 / 100;
        for (int i = 0; i < this.width * this.height; ++i) {
            final int n6 = array[i];
            final int n7 = ((n6 & 0xFF0000) >> 16) + n3;
            final int n8 = ((n6 & 0xFF00) >> 8) + n4;
            final int n9 = (n6 & 0xFF) + n5;
            int n10;
            int n11;
            int n12;
            if (n2 < 0) {
                n10 = Math.max(0, n7);
                n11 = Math.max(0, n8);
                n12 = Math.max(0, n9);
            }
            else {
                n10 = Math.min(255, n7);
                n11 = Math.min(255, n8);
                n12 = Math.min(255, n9);
            }
            array[i] = (n6 & 0xFF000000) + (n10 << 16) + (n11 << 8) + n12;
        }
        return array;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public Graphics getImageGraphics() {
        if (this.theImage != null) {
            return this.theImage.getGraphics();
        }
        return null;
    }
    
    private int[] emboss(int[] array, final int n, int min, int n2, int n3, final int n4) {
        this.filter(array, true, true, true);
        final int[] array2 = array;
        array = new int[array2.length];
        final int n5 = ((n3 & 0xFF0000) >> 16) * n4 / 100;
        final int n6 = ((n3 & 0xFF00) >> 8) * n4 / 100;
        final int n7 = (n3 & 0xFF) * n4 / 100;
        min = Math.min(Math.max((min + 50 - 100) * 255 / 100, -255), 255);
        final double n8 = n / 57.29577951308232;
        final int n9 = (int)(Math.round(Math.sin(n8)) * this.width) + (int)Math.round(Math.cos(n8));
        final int n10 = this.width * this.height - this.width - 1;
        n2 += 100;
        long n11 = 0L;
        long n12 = 0L;
        long n13 = 0L;
        for (int i = this.width + 1; i < n10; ++i) {
            final int n14 = array2[i];
            final int n15 = (n14 & 0xFF0000) >> 16;
            final int n16 = (n14 & 0xFF00) >> 8;
            final int n17 = n14 & 0xFF;
            final int n18 = array2[i - n9];
            final int n19 = (n18 & 0xFF0000) >> 16;
            final int n20 = (n18 & 0xFF00) >> 8;
            final int n21 = n18 & 0xFF;
            final int min2;
            n11 += (min2 = Math.min(Math.max(n5 + ((n19 - n15) * n2 / 100 + min), 0), 255));
            final int min3;
            n12 += (min3 = Math.min(Math.max(n6 + ((n20 - n16) * n2 / 100 + min), 0), 255));
            final int min4;
            n13 += (min4 = Math.min(Math.max(n7 + ((n21 - n17) * n2 / 100 + min), 0), 255));
            array[i] = (n18 & 0xFF000000) + (min2 << 16) + (min3 << 8) + min4;
        }
        n3 = -16777216 + ((int)(n11 / n10) << 16) + ((int)n12 / n10 << 8) + (int)n13 / n10;
        for (int j = 0; j < this.width; ++j) {
            array[j] = n3;
            array[this.width * this.height - j - 1] = n3;
        }
        for (int k = 0; k < this.width * this.height; k += this.width) {
            array[k] = n3;
            array[k + this.width - 1] = n3;
        }
        return array;
    }
    
    public Image getImageCopy() {
        return this.createSubImage(0, 0, this.width, this.height);
    }
}
