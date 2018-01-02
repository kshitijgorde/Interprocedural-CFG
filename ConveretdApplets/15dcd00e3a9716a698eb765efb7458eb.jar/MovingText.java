import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MovingText extends Applet implements Runnable, MouseListener
{
    private static final String COPYRIGHT = "© Witek Mozga";
    private int x;
    private int y;
    private int gap;
    private int fHeight;
    private int w;
    private int h;
    private int lMargin;
    private int rMargin;
    private int delay;
    private Thread current;
    private Thread animator;
    private Thread backgroundLoader;
    private Thread textGenerator;
    private boolean textLoadedAndFormated;
    private boolean backgroundImageProcessed;
    private boolean animateFlag;
    private boolean goFlag;
    private boolean mouseOnFlag;
    private boolean drawBorder;
    private boolean drawShadow;
    private boolean borderRounded;
    private Graphics memoryGraphics;
    private Image memoryImage;
    private Image backgroundImage;
    private Color bgColor;
    private Color fColor;
    private Color shColor;
    private Color brColor;
    private Color bgMouseColor;
    private Color fMouseColor;
    private Color shMouseColor;
    private Color brMouseColor;
    private Color bgCurrentColor;
    private Color fCurrentColor;
    private Color shCurrentColor;
    private Color brCurrentColor;
    private Font font;
    private Vector napisy;
    private String textFileToRead;
    private String imageToLoad;
    private String alert;
    private String urlToGo;
    private Cursor hand;
    
    public void init() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.memoryImage = this.createImage(this.w, this.h);
        this.memoryGraphics = this.memoryImage.getGraphics();
        this.bgColor = this.getBackgroundColor();
        this.fColor = this.getFontColor();
        this.shColor = this.getFontShadowColor();
        this.brColor = this.getBorderColor();
        this.bgCurrentColor = this.getBackgroundColor();
        this.fCurrentColor = this.getFontColor();
        this.shCurrentColor = this.getFontShadowColor();
        this.brCurrentColor = this.getBorderColor();
        this.bgMouseColor = this.getBackgroundMouseColor();
        this.fMouseColor = this.getFontMouseColor();
        this.shMouseColor = this.getFontShadowMouseColor();
        this.brMouseColor = this.getBorderMouseColor();
        this.delay = this.getDelay();
        this.drawBorder = this.isBorder();
        this.drawShadow = this.isShadow();
        this.borderRounded = this.isBorderRounded();
        this.mouseOnFlag = this.isStoppedByMouse();
        this.lMargin = this.getLeftMargin();
        this.rMargin = this.getRightMargin();
        this.font = this.getFontData();
        this.urlToGo = this.getUrlToGo();
        this.imageToLoad = this.getImageToLoad();
        this.memoryGraphics.setFont(this.font);
        this.calculateFontSize(this.font);
        this.textFileToRead = this.getFileToRead();
        this.x = this.lMargin;
        this.y = this.h + this.fHeight;
        this.addMouseListener(this);
    }
    
    public void start() {
        if (this.backgroundLoader == null) {
            (this.backgroundLoader = new Thread(this)).start();
            this.backgroundLoader.setPriority(10);
        }
        if (this.textGenerator == null) {
            (this.textGenerator = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.animateFlag = false;
    }
    
    public void run() {
        this.current = Thread.currentThread();
        if (this.current == this.animator) {
            this.animate();
        }
        else if (this.current == this.textGenerator) {
            this.loadAndFormatText();
        }
        else if (this.current == this.backgroundLoader) {
            this.loadBackgroundImage();
        }
    }
    
    private void loadBackgroundImage() {
        if (this.imageToLoad != null && !this.imageToLoad.equals("") && !this.backgroundImageProcessed) {
            this.backgroundImage = this.getImage(this.getDocumentBase(), this.imageToLoad);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.backgroundImage, 1);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {}
        }
        this.backgroundImageProcessed = true;
    }
    
    private void animate() {
        while (this.animateFlag) {
            this.repaint();
            --this.y;
            try {
                Thread.sleep(this.delay);
                synchronized (this) {
                    while (!this.goFlag) {
                        this.wait();
                    }
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void loadAndFormatText() {
        if (!this.textLoadedAndFormated) {
            final String textFile = this.readTextFile(this.textFileToRead);
            if (textFile == null) {
                this.alert = "TEXT NOT FOUND!";
                this.repaint();
                return;
            }
            this.napisy = this.createTextLines(textFile, this.font);
            if (this.animator == null) {
                (this.animator = new Thread(this)).start();
                this.animator.setPriority(10);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.createMemoryImage();
        graphics.drawImage(this.memoryImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void createMemoryImage() {
        this.clear();
        if (!this.textLoadedAndFormated || !this.backgroundImageProcessed) {
            this.drawAlert();
        }
        else {
            if (this.backgroundImage != null) {
                this.drawBackgroundImage();
            }
            if (this.drawBorder) {
                this.drawBorder();
            }
            this.drawText();
        }
    }
    
    private void clear() {
        this.memoryGraphics.setClip(0, 0, this.w, this.h);
        this.memoryGraphics.setColor(this.bgCurrentColor);
        this.memoryGraphics.fillRect(0, 0, this.w, this.h);
    }
    
    private void drawBorder() {
        this.memoryGraphics.setClip(0, 0, this.w, this.h);
        this.memoryGraphics.setColor(this.brCurrentColor);
        if (this.borderRounded) {
            this.memoryGraphics.drawRoundRect(0, 0, this.w - 1, this.h - 1, 16, 16);
        }
        else {
            this.memoryGraphics.drawRect(0, 0, this.w - 1, this.h - 1);
        }
    }
    
    private void drawBackgroundImage() {
        final int width = this.backgroundImage.getWidth(this);
        final int height = this.backgroundImage.getHeight(this);
        if (width == -1 || height == -1) {
            return;
        }
        final int n = 1 + this.h / height;
        final int n2 = 1 + this.w / width;
        this.memoryGraphics.setClip(0, 0, this.w, this.h);
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                this.memoryGraphics.drawImage(this.backgroundImage, i * width, j * height, this);
            }
        }
    }
    
    private void drawText() {
        this.memoryGraphics.setClip(0, 2, this.w, this.h - 4);
        Enumeration elements;
        int n;
        for (elements = this.napisy.elements(), n = 0; elements.hasMoreElements() && this.y + (this.gap + this.fHeight) * n < -this.fHeight; ++n) {
            elements.nextElement();
        }
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            this.memoryGraphics.setColor(this.shCurrentColor);
            if (this.drawShadow) {
                this.memoryGraphics.drawString(s, this.x + 2, 2 + this.y + (this.gap + this.fHeight) * n);
            }
            this.memoryGraphics.setColor(this.fCurrentColor);
            this.memoryGraphics.drawString(s, this.x, this.y + (this.gap + this.fHeight) * n);
            ++n;
            if (this.y + (this.gap + this.fHeight) * n > this.h + this.fHeight) {
                break;
            }
        }
        if (this.y + (this.gap + this.fHeight) * n <= -this.fHeight) {
            this.y = this.h + this.fHeight;
        }
    }
    
    private void drawAlert() {
        this.memoryGraphics.setColor(this.fCurrentColor);
        this.memoryGraphics.drawString(this.alert, (this.w - this.getFontMetrics(this.font).stringWidth(this.alert)) / 2, (this.h + this.fHeight) / 2);
    }
    
    private Font getFontData() {
        String parameter = this.getParameter("font_face");
        String parameter2 = this.getParameter("font_size");
        String parameter3 = this.getParameter("is_bold");
        String parameter4 = this.getParameter("is_italic");
        if (parameter == null) {
            parameter = "SansSerif";
        }
        if (parameter2 == null) {
            parameter2 = "14";
        }
        if (parameter3 == null) {
            parameter3 = "n";
        }
        if (parameter4 == null) {
            parameter4 = "n";
        }
        int intValue;
        try {
            intValue = Integer.valueOf(parameter2);
        }
        catch (NumberFormatException ex) {
            intValue = 14;
        }
        int n = 0;
        if (parameter3.equals("yes")) {
            n = 1;
        }
        if (parameter4.equals("yes")) {
            n += 2;
        }
        return new Font(parameter, n, intValue);
    }
    
    private void calculateFontSize(final Font font) {
        String parameter = this.getParameter("line_space");
        if (parameter == null) {
            parameter = "2";
        }
        try {
            this.gap = Integer.valueOf(parameter);
        }
        catch (NumberFormatException ex) {
            this.gap = 2;
        }
        this.fHeight = this.getFontMetrics(font).getAscent();
    }
    
    private String getFileToRead() {
        final String parameter = this.getParameter("text_file");
        if (parameter == null || parameter.equals("")) {
            return null;
        }
        return parameter;
    }
    
    private String getImageToLoad() {
        final String parameter = this.getParameter("background_image");
        if (parameter == null || parameter.equals("")) {
            return null;
        }
        return parameter;
    }
    
    private String getUrlToGo() {
        final String parameter = this.getParameter("link");
        if (parameter == null || parameter.equals("")) {
            return null;
        }
        this.setCursor(this.hand);
        return parameter;
    }
    
    private Color getBackgroundColor() {
        final String parameter = this.getParameter("background_color");
        if (parameter == null || parameter.equals("")) {
            return new Color(255, 255, 255);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(255, 255, 255);
        }
        return decode;
    }
    
    private Color getBackgroundMouseColor() {
        final String parameter = this.getParameter("background_color_on_mouse_over");
        if (parameter == null || parameter.equals("")) {
            return new Color(255, 255, 255);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(255, 255, 255);
        }
        return decode;
    }
    
    private Color getFontColor() {
        final String parameter = this.getParameter("text_color");
        if (parameter == null || parameter.equals("")) {
            return new Color(127, 127, 127);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(127, 127, 127);
        }
        return decode;
    }
    
    private Color getFontShadowColor() {
        final String parameter = this.getParameter("text_shadow");
        if (parameter == null || parameter.equals("")) {
            return new Color(255, 255, 255);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(255, 255, 255);
        }
        return decode;
    }
    
    private Color getFontShadowMouseColor() {
        final String parameter = this.getParameter("text_shadow_on_mouse_over");
        if (parameter == null || parameter.equals("")) {
            return new Color(255, 255, 255);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(255, 255, 255);
        }
        return decode;
    }
    
    private Color getFontMouseColor() {
        final String parameter = this.getParameter("text_color_on_mouse_over");
        if (parameter == null || parameter.equals("")) {
            return new Color(127, 127, 127);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(127, 127, 127);
        }
        return decode;
    }
    
    private Color getBorderColor() {
        final String parameter = this.getParameter("border_color");
        if (parameter == null || parameter.equals("")) {
            return new Color(192, 192, 192);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(192, 192, 192);
        }
        return decode;
    }
    
    private Color getBorderMouseColor() {
        final String parameter = this.getParameter("border_color_on_mouse_over");
        if (parameter == null || parameter.equals("")) {
            return new Color(192, 192, 192);
        }
        Color decode;
        try {
            decode = Color.decode(parameter);
        }
        catch (NumberFormatException ex) {
            return new Color(192, 192, 192);
        }
        return decode;
    }
    
    private boolean isBorder() {
        final String parameter = this.getParameter("is_border");
        return parameter == null || !parameter.equals("no");
    }
    
    private boolean isShadow() {
        final String parameter = this.getParameter("is_shadow");
        return parameter != null && parameter.equals("yes");
    }
    
    private boolean isBorderRounded() {
        final String parameter = this.getParameter("border_style");
        return parameter != null && parameter.equals("round");
    }
    
    private boolean isStoppedByMouse() {
        final String parameter = this.getParameter("stop_on_mouse_over");
        return parameter != null && parameter.equals("yes");
    }
    
    private int getRightMargin() {
        final String parameter = this.getParameter("right_margin");
        if (parameter == null || parameter.equals("")) {
            return 8;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(parameter);
        }
        catch (NumberFormatException ex) {
            return 8;
        }
        return intValue;
    }
    
    private int getDelay() {
        final String parameter = this.getParameter("animation_delay");
        if (parameter == null || parameter.equals("")) {
            return 40;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(parameter);
        }
        catch (NumberFormatException ex) {
            return 40;
        }
        return intValue;
    }
    
    private Vector createTextLines(final String s, final Font font) {
        final Vector<String> vector = new Vector<String>();
        final Vector<String> vector2 = new Vector<String>();
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " \n", true);
        while (stringTokenizer.hasMoreTokens()) {
            vector2.addElement(stringTokenizer.nextToken());
        }
        final Enumeration<String> elements = vector2.elements();
        final String s2 = elements.nextElement();
        StringBuffer sb = new StringBuffer(s2);
        StringBuffer sb2 = new StringBuffer();
        if (!s2.equals("\n")) {
            sb2 = new StringBuffer(sb.toString());
        }
        final int n = this.w - (this.lMargin + this.rMargin);
        while (elements.hasMoreElements()) {
            String s3 = elements.nextElement();
            sb.append(s3);
            if (s3.equals("\n")) {
                vector.addElement(sb2.toString());
                sb = new StringBuffer("");
                sb2 = new StringBuffer("");
            }
            else if (fontMetrics.stringWidth(sb.toString()) >= n) {
                vector.addElement(sb2.toString());
                if (s3.equals(" ")) {
                    s3 = "";
                }
                sb = new StringBuffer(s3);
                sb2 = new StringBuffer(s3);
            }
            else {
                sb2.append(s3);
            }
        }
        vector.addElement(sb2.toString());
        this.textLoadedAndFormated = true;
        return vector;
    }
    
    private String readTextFile(final String s) {
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(this.getDocumentBase(), s).openStream(), "Cp1250"));
            int read;
            while ((read = bufferedReader.read()) != -1) {
                char c = (char)read;
                if (c == '\r') {
                    continue;
                }
                if (c == '\t' || c == '\f') {
                    c = ' ';
                }
                sb.append(c);
            }
            bufferedReader.close();
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return null;
        }
        return sb.toString();
    }
    
    private int getLeftMargin() {
        final String parameter = this.getParameter("left_margin");
        if (parameter == null || parameter.equals("")) {
            return 8;
        }
        int intValue;
        try {
            intValue = Integer.valueOf(parameter);
        }
        catch (NumberFormatException ex) {
            return 8;
        }
        return intValue;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.bgCurrentColor = this.bgMouseColor;
        this.fCurrentColor = this.fMouseColor;
        this.shCurrentColor = this.shMouseColor;
        this.brCurrentColor = this.brMouseColor;
        this.showStatus("© Witek Mozga");
        if (this.mouseOnFlag) {
            this.goFlag = false;
        }
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.bgCurrentColor = this.bgColor;
        this.fCurrentColor = this.fColor;
        this.brCurrentColor = this.brColor;
        this.shCurrentColor = this.shColor;
        this.goFlag = true;
        synchronized (this) {
            this.notify();
        }
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        try {
            this.getAppletContext().showDocument(new URL(this.urlToGo));
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public String getAppletInfo() {
        return "Applet created by Witek Mozga mozga@trimen.pl\nIt`s free for non-commercial use.";
    }
    
    public MovingText() {
        this.textLoadedAndFormated = false;
        this.backgroundImageProcessed = false;
        this.animateFlag = true;
        this.goFlag = true;
        this.alert = "LOADING...";
        this.hand = new Cursor(12);
    }
}
