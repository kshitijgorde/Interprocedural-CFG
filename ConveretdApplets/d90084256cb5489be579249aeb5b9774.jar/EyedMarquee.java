import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class EyedMarquee extends Applet implements Runnable, HotSpotSink, EyedMarqueeImageSink, EyedMarqueeTextInfoProvider
{
    Rectangle m_bounds;
    Image m_image;
    EyedMarqueeImageTracker m_imageTracker;
    EyedMarqueeTextUpdater m_textUpdater;
    String m_text;
    int m_textSpeed;
    int m_textTop;
    Font m_textFont;
    
    public void setText(final String text) {
        this.m_text = text;
        if (this.m_textUpdater != null) {
            this.m_textUpdater.reset();
        }
    }
    
    public void setTextSpeed(final int textSpeed) {
        this.m_textSpeed = textSpeed;
        if (this.m_textUpdater != null) {
            this.m_textUpdater.reset();
        }
    }
    
    public void setTextTop(final int textTop) {
        this.m_textTop = textTop;
        if (this.m_textUpdater != null) {
            this.m_textUpdater.reset();
        }
    }
    
    public void init() {
        this.m_bounds = this.bounds();
        this.m_text = this.getStringParameter("text", null);
        this.m_textSpeed = this.getIntParameter("textSpeed", 4);
        this.m_textTop = this.getIntParameter("textTop", 2 * (this.m_bounds.height / 3));
        this.m_textFont = this.getFontParameter("textFont", null);
        System.out.println("textFont: " + this.m_textFont + "  (" + this.getStringParameter("textFont", null) + ")");
        if (this.m_textFont == null) {
            this.m_textFont = new Font("", 3, 48);
        }
        new Thread(this).start();
    }
    
    public void start() {
        if (this.m_imageTracker != null) {
            this.m_imageTracker.resume();
        }
    }
    
    public void stop() {
        if (this.m_imageTracker != null) {
            this.m_imageTracker.suspend();
        }
    }
    
    public void destroy() {
        if (this.m_imageTracker != null) {
            this.m_imageTracker.destroy();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_imageTracker != null) {
            if (this.m_image == null) {
                this.m_image = this.createImage(this.m_bounds.width, this.m_bounds.height);
            }
            this.m_imageTracker.drawImage(this.m_image);
            graphics.drawImage(this.m_image, 0, 0, this);
            return;
        }
        final Color colorParameter = this.getColorParameter("color", null);
        if (colorParameter != null) {
            graphics.setColor(colorParameter);
            graphics.fillRect(this.m_bounds.x, this.m_bounds.y, this.m_bounds.width, this.m_bounds.height);
        }
        graphics.setColor(Color.red);
        graphics.drawString("loading...", 5, this.m_bounds.height - 5);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        this.repaint();
        final String string = String.valueOf(this.getStringParameter("hotSpots", "")) + ";" + this.getStringParameter("hotSpot", "");
        HotSpotTracker hotSpotTracker = null;
        if (!string.equals("")) {
            hotSpotTracker = new HotSpotTracker(this);
            final StringTokenizer stringTokenizer = new StringTokenizer(string, ";\t\n\r");
            while (stringTokenizer.hasMoreTokens()) {
                final HotSpotInfo hotSpot = this.parseHotSpot(stringTokenizer.nextToken());
                if (hotSpot != null) {
                    hotSpotTracker.m_hotSpots.addElement(hotSpot);
                }
            }
        }
        this.m_imageTracker = new EyedMarqueeImageTracker(this, this.m_bounds);
        if (hotSpotTracker != null) {
            this.m_imageTracker.m_hotSpotTracker = hotSpotTracker;
        }
        final int intParameter = this.getIntParameter("eyeSize", 2 * this.m_textFont.getSize() / 3);
        final Color colorParameter = this.getColorParameter("eyeColor", Color.green);
        final String stringParameter = this.getStringParameter("eyeWhere", "");
        final Color colorParameter2 = this.getColorParameter("color", null);
        final String stringParameter2 = this.getStringParameter("image", "");
        final Color colorParameter3 = this.getColorParameter("textColor", Color.red);
        final String stringParameter3 = this.getStringParameter("stare", null);
        final int[] array = { 0, 0 };
        final String numbersAndString = this.parseNumbersAndString(stringParameter2, array);
        final int imageX = array[0];
        final int imageY = array[1];
        array[0] = this.m_bounds.width / 2;
        array[1] = intParameter / 2 + 5;
        this.parseNumbersAndString(stringParameter, array);
        final int n = array[0];
        final int n2 = array[1];
        System.out.println("eyeWhere: " + stringParameter + "  --  " + n + "," + n2);
        Image image = null;
        if (!numbersAndString.equals("")) {
            try {
                image = this.getImage(this.getDocumentBase(), numbersAndString);
            }
            catch (Exception ex) {
                System.err.println("error loading background image " + numbersAndString + ": " + ex);
                image = null;
            }
        }
        if (image != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            imageTracker.m_imageX = imageX;
            imageTracker.m_imageY = imageY;
            imageTracker.m_backgroundImage = image;
        }
        if (colorParameter2 != null) {
            this.m_imageTracker.m_backgroundColor = colorParameter2;
        }
        new EyedMarqueeEyesUpdater(this.m_imageTracker, intParameter, colorParameter, n, n2).start();
        if (stringParameter3 != null) {
            final int[] array2 = { this.m_bounds.x, this.m_bounds.y, this.m_bounds.x + this.m_bounds.width, this.m_bounds.y + this.m_bounds.height };
            final String numbersAndString2 = this.parseNumbersAndString(stringParameter3, array2);
            System.out.println("stare image: " + numbersAndString2);
            System.out.println("stare bounds: " + array2[0] + "," + array2[1] + "," + array2[2] + "," + array2[3]);
            Image image2 = null;
            if (!numbersAndString2.equals("")) {
                try {
                    image2 = this.getImage(this.getDocumentBase(), numbersAndString2);
                }
                catch (Exception ex2) {
                    System.err.println("error loading stare image " + numbersAndString2 + ": " + ex2);
                    image2 = null;
                }
            }
            new ShootUpdater(this.m_imageTracker, image2, array2[0], array2[1], array2[2], array2[3]).start();
        }
        (this.m_textUpdater = new EyedMarqueeTextUpdater(this.m_imageTracker, this, colorParameter3)).start();
        this.m_textUpdater.reset();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m_imageTracker != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            imageTracker.m_staringAtPointer = true;
            if (imageTracker.m_eyesUpdater != null) {
                imageTracker.m_eyesUpdater.updateStare(n, n2);
            }
            if (imageTracker.m_hotSpotTracker != null) {
                final HotSpotTracker hotSpotTracker = imageTracker.m_hotSpotTracker;
                hotSpotTracker.overHotSpot(hotSpotTracker.mapHotSpot(n, n2));
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_imageTracker != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            imageTracker.updateStare();
            if (imageTracker.m_hotSpotTracker != null) {
                imageTracker.m_hotSpotTracker.overHotSpot(null);
            }
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.m_imageTracker != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            imageTracker.m_staringAtPointer = true;
            if (imageTracker.m_eyesUpdater != null) {
                imageTracker.m_eyesUpdater.updateStare(n, n2);
            }
            if (imageTracker.m_hotSpotTracker != null) {
                final HotSpotTracker hotSpotTracker = imageTracker.m_hotSpotTracker;
                hotSpotTracker.overHotSpot(hotSpotTracker.mapHotSpot(n, n2));
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.m_imageTracker != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            imageTracker.m_staringAtPointer = true;
            if (imageTracker.m_eyesUpdater != null) {
                imageTracker.m_eyesUpdater.updateStare(n, n2);
            }
            if (imageTracker.m_hotSpotTracker != null) {
                final HotSpotTracker hotSpotTracker = imageTracker.m_hotSpotTracker;
                hotSpotTracker.overHotSpot(hotSpotTracker.mapHotSpot(n, n2));
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_imageTracker != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            if (imageTracker.m_hotSpotTracker != null) {
                final HotSpotTracker hotSpotTracker = imageTracker.m_hotSpotTracker;
                hotSpotTracker.m_mouseDown = true;
                hotSpotTracker.m_hotSpotSink.repaint();
            }
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m_imageTracker != null) {
            final EyedMarqueeImageTracker imageTracker = this.m_imageTracker;
            if (imageTracker.m_hotSpotTracker != null) {
                imageTracker.m_hotSpotTracker.mouseUp(n, n2);
            }
        }
        return true;
    }
    
    public void enterHotSpot(final HotSpotInfo hotSpotInfo) {
        final URL url = (hotSpotInfo != null) ? hotSpotInfo.getURL() : null;
        this.showStatus((url != null) ? url.toString() : null);
    }
    
    public void exitHotSpot(final HotSpotInfo hotSpotInfo) {
        this.showStatus(null);
    }
    
    public void clickHotSpot(final HotSpotInfo hotSpotInfo) {
        final URL url = (hotSpotInfo != null) ? hotSpotInfo.getURL() : null;
        if (url != null) {
            this.getAppletContext().showDocument(url);
        }
    }
    
    public String getText() {
        return this.m_text;
    }
    
    public int getTextSpeed() {
        return this.m_textSpeed;
    }
    
    public int getTextTop() {
        return this.m_textTop;
    }
    
    public Font getTextFont() {
        return this.m_textFont;
    }
    
    String getStringParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return s2;
    }
    
    int getIntParameter(final String s, final int n) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                return Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                System.err.println("invalid number encoding: " + parameter);
            }
        }
        return n;
    }
    
    Color getColorParameter(final String s, final Color color) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                return Color.decode(parameter);
            }
            catch (NumberFormatException ex) {
                System.err.println("invalid color encoding: " + parameter);
            }
        }
        return color;
    }
    
    Font getFontParameter(final String s, final Font font) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return Font.decode(parameter);
        }
        return font;
    }
    
    String parseNumbersAndString(String substring, final int[] array) {
        for (int i = 0; i < array.length; ++i) {
            final int index = substring.indexOf(44);
            String trim;
            if (index == -1) {
                trim = substring;
            }
            else {
                trim = substring.substring(0, index).trim();
            }
            if (!trim.equals("")) {
                try {
                    array[i] = Integer.parseInt(trim);
                }
                catch (NumberFormatException ex) {
                    break;
                }
            }
            if (index == -1) {
                substring = "";
                break;
            }
            substring = substring.substring(index + 1);
        }
        return substring;
    }
    
    HotSpotInfo parseHotSpot(final String s) {
        System.out.println("** encoding: " + s);
        final int[] array = { this.m_bounds.x, this.m_bounds.y, -1, -1 };
        final String numbersAndString = this.parseNumbersAndString(s, array);
        if (array[2] < 0) {
            array[2] = this.m_bounds.width - array[0];
        }
        if (array[3] < 0) {
            array[3] = this.m_bounds.height - array[1];
        }
        System.out.println("   HOT SPOT: " + numbersAndString + "," + array[0] + "," + array[1] + "," + array[2] + "," + array[3]);
        final URL url = (numbersAndString != null) ? this.getURL(numbersAndString) : null;
        if (url == null) {
            return null;
        }
        return new HotSpotInfo(new Rectangle(array[0], array[1], array[2], array[3]), url);
    }
    
    URL getURL(final String s) {
        URL url = null;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            try {
                url = new URL(this.getDocumentBase(), s);
            }
            catch (MalformedURLException ex2) {
                System.err.println("invalid URL: " + s);
            }
        }
        return url;
    }
}
