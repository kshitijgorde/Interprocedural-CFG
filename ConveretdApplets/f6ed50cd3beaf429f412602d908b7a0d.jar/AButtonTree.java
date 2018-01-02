import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.Color;
import java.util.Vector;
import java.awt.Point;
import java.net.URL;
import java.awt.Image;
import java.awt.Button;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AButtonTree extends Applet implements ActionListener, MouseMotionListener, MouseListener, ComponentListener
{
    private int maxSize;
    protected Button[] buttonList;
    protected Image downImage;
    protected Image upImage;
    protected int numberOfButtons;
    protected int selectedButton;
    protected int startIcon;
    public Image[][] imageArray;
    public String[][] stringArray;
    public String[][] imageFileArray;
    public URL[][] urlArray;
    public int[] imageCount;
    protected int imageWidthPercent;
    protected int buttonHeight;
    public int deltaBetweenIcons;
    public Point mousePoint;
    protected int firstImageIndex;
    protected int pressedImage;
    public boolean showDownButton;
    protected boolean smoothScroll;
    protected int scrollIndex;
    protected int sleepTime;
    private final boolean demoMode = false;
    private Vector listeners;
    protected int changeCursor;
    protected String appletFrameName;
    protected int garbageCollectorInterval;
    protected int garbageCollectorCurrent;
    protected Color buttonNormalColor;
    protected Color buttonHighlightedColor;
    protected boolean beanUsage;
    
    public AButtonTree() {
        this.maxSize = 20;
        this.downImage = null;
        this.upImage = null;
        this.numberOfButtons = 0;
        this.selectedButton = -1;
        this.startIcon = 0;
        this.imageWidthPercent = 40;
        this.buttonHeight = 20;
        this.deltaBetweenIcons = 20;
        this.mousePoint = null;
        this.firstImageIndex = 0;
        this.pressedImage = -1;
        this.showDownButton = false;
        this.smoothScroll = true;
        this.scrollIndex = 0;
        this.sleepTime = 0;
        this.listeners = new Vector();
        this.changeCursor = 0;
        this.appletFrameName = null;
        this.garbageCollectorInterval = 10;
        this.garbageCollectorCurrent = 0;
        this.buttonNormalColor = null;
        this.buttonHighlightedColor = Color.lightGray;
        this.beanUsage = true;
        this.checkLicense();
        this.buttonList = new Button[this.maxSize];
        this.imageArray = new Image[this.maxSize][this.maxSize];
        this.stringArray = new String[this.maxSize][this.maxSize];
        this.imageFileArray = new String[this.maxSize][this.maxSize];
        this.urlArray = new URL[this.maxSize][this.maxSize];
        this.imageCount = new int[this.maxSize];
        this.setLayout(null);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addComponentListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        for (int numberOfButtons = this.getNumberOfButtons(), i = 0; i < numberOfButtons; ++i) {
            if (this.getButton(i) == actionEvent.getSource()) {
                this.pressButton(i);
                break;
            }
        }
    }
    
    public synchronized void addAButtonTreeListener(final AButtonTreeListener aButtonTreeListener) {
        this.listeners.addElement(aButtonTreeListener);
    }
    
    public boolean addButton(final String name) {
        final int numberOfButtons = this.getNumberOfButtons();
        if (numberOfButtons < this.maxSize) {
            final Button button = new Button(name);
            button.setName(name);
            button.setBounds(2, 2 + numberOfButtons * this.getButtonHeight(), this.getWidth() - 4, this.getButtonHeight());
            this.buttonList[numberOfButtons] = button;
            this.imageCount[numberOfButtons] = 0;
            this.incrementNumberOfButtons();
            this.add(button);
            button.addActionListener(this);
            this.setSelectedButton(numberOfButtons);
            return true;
        }
        return false;
    }
    
    public void addImageToButton(final int n, final String s, final String s2) {
        this.addImageToButton(n, s, s2, null);
    }
    
    public void addImageToButton(final int n, final String s, final String s2, final String s3) {
        if (this.downImage == null) {
            this.downImage = this.loadImage("buttdown.gif");
        }
        if (this.upImage == null) {
            this.upImage = this.loadImage("buttup.gif");
        }
        if (n < this.getNumberOfButtons() && this.imageCount[n] < this.maxSize) {
            final Image loadImage = this.loadImage(s);
            if (loadImage == null) {
                System.err.println("Couldn't load image " + s);
                return;
            }
            this.imageArray[n][this.imageCount[n]] = loadImage;
            this.stringArray[n][this.imageCount[n]] = s2;
            this.imageFileArray[n][this.imageCount[n]] = s;
            try {
                if (s3 != null) {
                    this.urlArray[n][this.imageCount[n]] = new URL(s3);
                }
                else {
                    this.urlArray[n][this.imageCount[n]] = null;
                }
            }
            catch (MalformedURLException ex) {
                try {
                    this.urlArray[n][this.imageCount[n]] = new URL(this.getCodeBase(), s3);
                }
                catch (MalformedURLException ex2) {
                    this.urlArray[n][this.imageCount[n]] = null;
                    System.err.println("Cannot construct url from source string: " + s3);
                }
            }
            final int[] imageCount = this.imageCount;
            ++imageCount[n];
            this.repaint();
        }
    }
    
    private void checkLicense() {
        AButtonTreeLicense aButtonTreeLicense = null;
        int n = 1;
        try {
            aButtonTreeLicense = (AButtonTreeLicense)Class.forName("AButtonTreeLicense").newInstance();
        }
        catch (Throwable t) {
            n = 0;
            System.err.println("Exception " + t + "when trying to read license");
        }
        if (n != 0 && aButtonTreeLicense.getLicenseString().compareTo(new String("ALXABUTTONTREE19990XCF4DCT")) != 0) {
            n = 0;
        }
        if (n == 0) {
            this.maxSize = 2;
        }
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void decrementNumberOfButtons() {
        --this.numberOfButtons;
    }
    
    public void deleteButton(final int n) {
        if (n >= 0 && n < this.getNumberOfButtons()) {
            this.remove(this.buttonList[n]);
            for (int i = n + 1; i < this.getNumberOfButtons(); ++i) {
                for (int j = 0; j < this.imageCount[i]; ++j) {
                    this.imageArray[i - 1][j] = this.imageArray[i][j];
                    this.stringArray[i - 1][j] = this.stringArray[i][j];
                    this.urlArray[i - 1][j] = this.urlArray[i][j];
                }
                this.imageCount[i - 1] = this.imageCount[i];
                this.buttonList[i - 1] = this.buttonList[i];
            }
            this.imageCount[this.getNumberOfButtons() - 1] = 0;
            this.buttonList[this.getNumberOfButtons() - 1] = null;
            this.setNumberOfButtons(this.getNumberOfButtons() - 1);
        }
    }
    
    public void deleteImageButton(final int n, final int n2) {
        if (n >= 0 && n < this.getNumberOfButtons() && n2 >= 0 && n2 < this.imageCount[n]) {
            for (int i = n2 + 1; i < this.imageCount[n]; ++i) {
                this.imageArray[n][i - 1] = this.imageArray[n][i];
                this.stringArray[n][i - 1] = this.stringArray[n][i];
                this.urlArray[n][i - 1] = this.urlArray[n][i];
            }
            this.imageArray[n][this.imageCount[n] - 1] = null;
            this.stringArray[n][this.imageCount[n] - 1] = null;
            this.urlArray[n][this.imageCount[n] - 1] = null;
            final int[] imageCount = this.imageCount;
            --imageCount[n];
            this.repaint();
        }
    }
    
    void drawStandardButton(final int n, final Rectangle rectangle, final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.getImageIndex(this.mousePoint) == -n - 2) {
            graphics.setColor(this.getButtonHighlightedColor());
        }
        else {
            graphics.setColor(this.getButtonNormalColor());
        }
        graphics.fillRect(rectangle.x + 3, rectangle.y + 3, rectangle.width - 6, rectangle.height - 6);
        graphics.setColor(Color.black);
        final String label = this.getButton(n).getLabel();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(label);
        fontMetrics.getHeight();
        graphics.drawString(label, rectangle.x + rectangle.width / 2 - stringWidth / 2, rectangle.y + rectangle.height - 4);
        graphics.setColor(Color.black);
        graphics.drawRect(rectangle.x + 2, rectangle.y + 2, rectangle.width - 4, rectangle.height - 4);
    }
    
    public Button getButton(final int n) {
        if (n < this.getNumberOfButtons() && n >= 0) {
            return this.buttonList[n];
        }
        return null;
    }
    
    public int getButtonHeight() {
        return this.buttonHeight;
    }
    
    public Color getButtonHighlightedColor() {
        if (this.buttonHighlightedColor != null) {
            return this.buttonHighlightedColor;
        }
        return Color.lightGray;
    }
    
    public Color getButtonNormalColor() {
        if (this.buttonNormalColor != null) {
            return this.buttonNormalColor;
        }
        return this.getBackground();
    }
    
    public int getChangeCursor() {
        return this.changeCursor;
    }
    
    public int getDeltaBetweenIcons() {
        return this.deltaBetweenIcons;
    }
    
    public Rectangle getDownButtonRect() {
        return new Rectangle(this.getWidth() - 18, this.getHeight() - (this.getNumberOfButtons() - this.getSelectedButton()) * this.getButtonHeight(), 12, 12);
    }
    
    public int getFirstImageIndex() {
        return this.firstImageIndex;
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public Rectangle getIconRectangle(final int n) {
        return new Rectangle((this.getWidth() - this.getIconWidth()) / 2, (this.getSelectedButton() + 1) * this.getButtonHeight() + (n - this.firstImageIndex) * (this.getIconWidth() + this.deltaBetweenIcons) + this.deltaBetweenIcons / 2, this.getIconWidth(), this.getIconWidth());
    }
    
    public int getIconWidth() {
        return this.getImageWidthPercent() * this.getWidth() / 100;
    }
    
    public int getImageIndex(final Point point) {
        if (point == null) {
            return -1;
        }
        if (this.numberOfButtons == 0) {
            return -1;
        }
        for (int i = 0; i < this.numberOfButtons; ++i) {
            if (this.getButton(i).getBounds().contains(point)) {
                return -i - 2;
            }
        }
        for (int j = this.firstImageIndex; j < this.firstImageIndex + this.imageCount[this.getSelectedButton()]; ++j) {
            if (this.getIconRectangle(j).contains(point)) {
                return j;
            }
        }
        return -1;
    }
    
    public int getImageWidthPercent() {
        return this.imageWidthPercent;
    }
    
    public int getIntFromString(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(20, 50);
    }
    
    public int getNumberOfButtons() {
        return this.numberOfButtons;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(50, 100);
    }
    
    public int getSelectedButton() {
        return this.selectedButton;
    }
    
    public int getSleepTime() {
        return this.sleepTime;
    }
    
    public Rectangle getUpButtonRect() {
        return new Rectangle(this.getWidth() - 18, (this.getSelectedButton() + 1) * this.getButtonHeight() + 10, 12, 12);
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public void incrementNumberOfButtons() {
        ++this.numberOfButtons;
    }
    
    public void init() {
        this.beanUsage = false;
        this.loadFromAppletParams();
    }
    
    public boolean isSmoothScroll() {
        return this.smoothScroll;
    }
    
    private void loadDemo() {
        this.setSize(new Dimension(80, 300));
        this.setBackground(Color.gray);
        this.addButton(new String("Personal"));
        this.addImageToButton(0, new String("bmp0.gif"), new String("Main Page"), new String("index2.html"));
        this.addImageToButton(0, new String("bmp1.gif"), new String("Resume"), new String("cv.html"));
        this.addImageToButton(0, new String("bmp2.gif"), new String("JavaBeans"), new String("beans.html"));
        this.addImageToButton(0, new String("bmp3.gif"), new String("WebMessenger"), new String("wm.html"));
        this.addImageToButton(0, new String("bmp4.gif"), new String("Contact me"), new String("contact.html"));
        this.addButton(new String("Friends"));
        this.addImageToButton(1, new String("bmp0.gif"), new String("Bogdan Marin"), new String("bogdanmarin.html"));
        this.addButton(new String("Links"));
        this.addImageToButton(2, new String("bmp0.gif"), new String("JARS"), new String("http://www.jars.com"));
        this.addImageToButton(2, new String("bmp1.gif"), new String("Flashline"), new String("http://www.flashline.com"));
        this.addImageToButton(2, new String("bmp2.gif"), new String("SUN's Java"), new String("http://www.sun.com/java"));
        this.setSelectedButton(0);
        this.appletFrameName = new String("page");
    }
    
    protected void loadFromAppletParams() {
        int intFromString = 0;
        this.setSize(new Dimension(80, 300));
        final String parameter = this.getParameter("backgroundColorRed");
        final String parameter2 = this.getParameter("backgroundColorGreen");
        final String parameter3 = this.getParameter("backgroundColorBlue");
        if (parameter != null && parameter2 != null && parameter3 != null) {
            this.setBackground(new Color(this.getIntFromString(parameter), this.getIntFromString(parameter2), this.getIntFromString(parameter3)));
        }
        else {
            this.setBackground(Color.gray);
        }
        final String parameter4 = this.getParameter("buttonNumber");
        if (parameter4 != null) {
            final int intFromString2 = this.getIntFromString(parameter4);
            for (int i = 0; i < intFromString2; ++i) {
                this.addButton(this.getParameter(String.valueOf(new String("button")) + i));
                final String parameter5 = this.getParameter("imageNumberOfButton" + i);
                if (parameter5 != null) {
                    for (int intFromString3 = this.getIntFromString(parameter5), j = 0; j < intFromString3; ++j) {
                        this.addImageToButton(i, this.getParameter("button" + i + "imageName" + j), this.getParameter("button" + i + "imageCaption" + j), this.getParameter("button" + i + "imageURL" + j));
                    }
                }
            }
            final String parameter6 = this.getParameter("selectedButton");
            if (parameter6 != null) {
                intFromString = this.getIntFromString(parameter6);
            }
            if (intFromString < 0 || intFromString >= intFromString2) {
                intFromString = 0;
            }
            this.setSelectedButton(intFromString);
        }
        final String parameter7 = this.getParameter("appletFrameName");
        if (parameter7 != null) {
            this.appletFrameName = new String(parameter7);
        }
    }
    
    public Image loadImage(final String s) {
        try {
            Image image;
            if (this.beanUsage) {
                image = Toolkit.getDefaultToolkit().getImage(s);
            }
            else {
                this.showStatus(String.valueOf(new String(" Loading image ")) + s);
                image = this.getImage(this.getDocumentBase(), s);
            }
            if (image != null) {
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                try {
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex2) {}
            }
            return image;
        }
        catch (Exception ex) {
            System.err.println("Exception " + ex + " occurred when trying to load image " + s);
            return null;
        }
    }
    
    public void modifyButton(final int n, final String label) {
        if (n >= 0 && n < this.getNumberOfButtons()) {
            this.buttonList[n].setLabel(label);
        }
    }
    
    public void modifyImageOfButton(final int n, final int n2, final String s, final String s2, final String s3) {
        if (n >= 0 && n < this.getNumberOfButtons() && n2 >= 0 && n2 < this.imageCount[n]) {
            final Image loadImage = this.loadImage(s);
            if (loadImage == null) {
                System.err.println("Couldn't load image " + s);
                return;
            }
            this.imageArray[n][n2] = loadImage;
            this.stringArray[n][n2] = s2;
            this.imageFileArray[n][n2] = s;
            try {
                if (s3 != null) {
                    this.urlArray[n][n2] = new URL(s2);
                }
                else {
                    this.urlArray[n][n2] = null;
                }
            }
            catch (MalformedURLException ex) {
                this.urlArray[n][n2] = null;
            }
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.pressedImage >= 0) {
            this.pressedImage = -1;
            this.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.getChangeCursor() != 0) {
            this.setCursor(new Cursor(12));
        }
        this.mousePoint = mouseEvent.getPoint();
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point mousePoint = this.mousePoint;
        this.mousePoint = mouseEvent.getPoint();
        if (this.getChangeCursor() == 1) {
            if (this.getImageIndex(this.mousePoint) >= 0) {
                this.setCursor(new Cursor(12));
            }
            else {
                this.setCursor(new Cursor(0));
            }
        }
        if (this.getImageIndex(mousePoint) != this.getImageIndex(this.mousePoint)) {
            this.repaint();
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (this.getUpButtonRect().contains(point)) {
            this.onUpButtonPressed();
            return;
        }
        if (this.showDownButton && this.getDownButtonRect().contains(point)) {
            this.onDownButtonPressed();
            return;
        }
        final int imageIndex = this.getImageIndex(point);
        if (imageIndex >= 0) {
            this.onImageButtonPressed(imageIndex);
            return;
        }
        for (int numberOfButtons = this.getNumberOfButtons(), i = 0; i < numberOfButtons; ++i) {
            if (this.getButton(i).getBounds().contains(point)) {
                this.setSelectedButton(i);
                return;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.pressedImage >= 0) {
            this.pressedImage = -1;
            this.repaint();
        }
    }
    
    public void onDownButtonPressed() {
        final int n = 5;
        if (this.isSmoothScroll()) {
            final int n2 = (this.getIconWidth() + this.getDeltaBetweenIcons()) / n;
            try {
                for (int i = 0; i < n; ++i) {
                    this.scrollIndex += n2;
                    if (this.sleepTime > 0) {
                        Thread.currentThread();
                        Thread.sleep(this.sleepTime);
                    }
                    this.paint(this.getGraphics());
                }
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        ++this.firstImageIndex;
        this.scrollIndex = 0;
        this.repaint();
    }
    
    public void onImageButtonPressed(final int pressedImage) {
        final AButtonTreeEvent aButtonTreeEvent = new AButtonTreeEvent(this, this.stringArray[this.getSelectedButton()][pressedImage], this.getSelectedButton());
        final Enumeration<AButtonTreeListener> elements = (Enumeration<AButtonTreeListener>)this.listeners.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().imageButtonPressed(aButtonTreeEvent);
        }
        final URL url = this.urlArray[this.getSelectedButton()][pressedImage];
        if (url != null) {
            System.err.println("appletFrameName  = " + this.appletFrameName);
            if (this.appletFrameName != null) {
                this.getAppletContext().showDocument(url, this.appletFrameName);
            }
            else {
                this.getAppletContext().showDocument(url);
            }
        }
        this.pressedImage = pressedImage;
        this.repaint();
    }
    
    public void onUpButtonPressed() {
        if (this.firstImageIndex > 0) {
            final int n = 5;
            if (this.isSmoothScroll()) {
                final int n2 = (this.getIconWidth() + this.getDeltaBetweenIcons()) / n;
                try {
                    for (int i = 0; i < n; ++i) {
                        this.scrollIndex -= n2;
                        if (this.sleepTime > 0) {
                            Thread.currentThread();
                            Thread.sleep(this.sleepTime);
                        }
                        this.paint(this.getGraphics());
                    }
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            --this.firstImageIndex;
            this.scrollIndex = 0;
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.recomputeButtonPositions();
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final Color background = this.getBackground();
        final Color foreground = this.getForeground();
        if (this.getNumberOfButtons() == 0) {
            graphics.setColor(background);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(foreground);
            return;
        }
        final int n = (100 - this.getImageWidthPercent()) / 2 * this.getWidth() / 100;
        this.getIconWidth();
        final int n2 = this.getWidth() - 4;
        final int n3 = height - this.getNumberOfButtons() * this.getButtonHeight();
        final Image image = this.createImage(n2, n3);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(background);
        graphics2.fillRect(0, 0, n2, n3);
        graphics2.setColor(foreground);
        final int n4 = (n3 - this.deltaBetweenIcons) / (this.getIconWidth() + this.deltaBetweenIcons);
        int n5 = n4 + 1;
        if (n5 + this.firstImageIndex > this.imageCount[this.getSelectedButton()]) {
            n5 = this.imageCount[this.getSelectedButton()] - this.firstImageIndex;
        }
        for (int i = this.firstImageIndex; i < this.firstImageIndex + n5; ++i) {
            final Rectangle iconRectangle = this.getIconRectangle(i);
            iconRectangle.translate(0, -((this.getSelectedButton() + 1) * this.getButtonHeight()) - this.scrollIndex);
            graphics2.drawImage(this.imageArray[this.getSelectedButton()][i], iconRectangle.x, iconRectangle.y, iconRectangle.width, iconRectangle.height, this);
            if (this.getImageIndex(this.mousePoint) == i) {
                if (this.pressedImage == -1) {
                    graphics2.setColor(Color.lightGray);
                }
                else {
                    graphics2.setColor(Color.black);
                }
                graphics2.drawLine(iconRectangle.x, iconRectangle.y, iconRectangle.x + iconRectangle.width, iconRectangle.y);
                graphics2.drawLine(iconRectangle.x, iconRectangle.y, iconRectangle.x, iconRectangle.y + iconRectangle.height);
                if (this.pressedImage == -1) {
                    graphics2.setColor(Color.black);
                }
                else {
                    graphics2.setColor(Color.lightGray);
                }
                graphics2.drawLine(iconRectangle.x + iconRectangle.width, iconRectangle.y, iconRectangle.x + iconRectangle.width, iconRectangle.y + iconRectangle.height);
                graphics2.drawLine(iconRectangle.x, iconRectangle.y + iconRectangle.height, iconRectangle.x + iconRectangle.width, iconRectangle.y + iconRectangle.height);
                if (!this.beanUsage) {
                    this.showStatus(this.stringArray[this.getSelectedButton()][i]);
                }
            }
            graphics2.setColor(Color.white);
            final String s = this.stringArray[this.getSelectedButton()][i];
            final FontMetrics fontMetrics = graphics2.getFontMetrics();
            final int stringWidth = fontMetrics.stringWidth(s);
            fontMetrics.getHeight();
            graphics2.drawString(s, iconRectangle.x + iconRectangle.width / 2 - stringWidth / 2, iconRectangle.y + iconRectangle.height + 12);
        }
        if (n5 > n4) {
            final Rectangle downButtonRect = this.getDownButtonRect();
            downButtonRect.translate(-2, -((this.getSelectedButton() + 1) * this.getButtonHeight()));
            graphics2.drawImage(this.downImage, downButtonRect.x, downButtonRect.y, downButtonRect.width, downButtonRect.height, this);
            this.showDownButton = true;
        }
        else {
            this.showDownButton = false;
        }
        if (this.firstImageIndex > 0) {
            final Rectangle upButtonRect = this.getUpButtonRect();
            upButtonRect.translate(-2, -((this.getSelectedButton() + 1) * this.getButtonHeight()));
            graphics2.drawImage(this.upImage, upButtonRect.x, upButtonRect.y, upButtonRect.width, upButtonRect.height, this);
        }
        graphics.drawImage(image, 2, this.getButtonHeight() * (this.getSelectedButton() + 1), this);
        graphics.setColor(Color.gray);
        graphics.drawLine(0, 0, width, 0);
        graphics.drawLine(0, 0, 0, height);
        graphics.setColor(Color.black.brighter());
        graphics.drawLine(1, 1, width - 1, 1);
        graphics.drawLine(1, 1, 1, height - 1);
        graphics.setColor(Color.lightGray.darker());
        graphics.drawLine(2, height - 1, width - 1, height - 1);
        graphics.drawLine(width - 1, height - 1, width - 1, 1);
        graphics.setColor(Color.lightGray);
        graphics.drawLine(2, height - 2, width - 2, height - 2);
        graphics.drawLine(width - 2, height - 2, width - 2, 2);
        for (int numberOfButtons = this.getNumberOfButtons(), j = 0; j < numberOfButtons; ++j) {
            this.drawStandardButton(j, this.getButton(j).getBounds(), graphics);
        }
        if (++this.garbageCollectorCurrent > this.garbageCollectorInterval) {
            this.garbageCollectorCurrent = 0;
            System.gc();
        }
    }
    
    public void pressButton(final int selectedButton) {
        this.setSelectedButton(selectedButton);
    }
    
    public void recomputeButtonPositions() {
        final int numberOfButtons = this.getNumberOfButtons();
        final int selectedButton = this.getSelectedButton();
        for (int i = 0; i < numberOfButtons; ++i) {
            this.getButton(i).setVisible(false);
            if (i <= selectedButton) {
                this.getButton(i).setBounds(2, 2 + this.getButtonHeight() * i, this.getWidth() - 4, this.getButtonHeight());
            }
            else {
                this.getButton(i).setBounds(2, this.getHeight() - (numberOfButtons - selectedButton) * this.getButtonHeight() + this.getButtonHeight() * (i - selectedButton), this.getWidth() - 4, this.getButtonHeight());
            }
        }
    }
    
    public synchronized void removeAButtonTreeListener(final AButtonTreeListener aButtonTreeListener) {
        this.listeners.removeElement(aButtonTreeListener);
    }
    
    public void setButtonHeight(final int buttonHeight) {
        if (buttonHeight > 0) {
            this.buttonHeight = buttonHeight;
        }
    }
    
    public void setButtonHighlightedColor(final Color buttonHighlightedColor) {
        this.buttonHighlightedColor = buttonHighlightedColor;
    }
    
    public void setButtonNormalColor(final Color buttonNormalColor) {
        this.buttonNormalColor = buttonNormalColor;
    }
    
    public void setChangeCursor(final int changeCursor) {
        if (changeCursor >= 0) {
            this.changeCursor = changeCursor;
        }
    }
    
    public void setDeltaBetweenIcons(final int deltaBetweenIcons) {
        if (deltaBetweenIcons > 0) {
            this.deltaBetweenIcons = deltaBetweenIcons;
            this.repaint();
        }
    }
    
    public void setFirstImageIndex(final int firstImageIndex) {
        if (firstImageIndex >= 0 && firstImageIndex < this.maxSize - 1) {
            this.firstImageIndex = firstImageIndex;
        }
    }
    
    public void setImageWidthPercent(final int imageWidthPercent) {
        if (imageWidthPercent > 0 && imageWidthPercent <= 100) {
            this.imageWidthPercent = imageWidthPercent;
        }
    }
    
    public void setNumberOfButtons(final int numberOfButtons) {
        if (numberOfButtons >= 0 && numberOfButtons < this.maxSize) {
            this.numberOfButtons = numberOfButtons;
            this.repaint();
        }
    }
    
    public void setSelectedButton(final int selectedButton) {
        final int selectedButton2 = this.selectedButton;
        if (selectedButton >= 0 && selectedButton < this.getNumberOfButtons()) {
            this.selectedButton = selectedButton;
            if (selectedButton2 != this.selectedButton) {
                this.firstImageIndex = 0;
            }
            this.repaint();
        }
    }
    
    public void setSleepTime(final int sleepTime) {
        if (sleepTime >= 0) {
            this.sleepTime = sleepTime;
        }
    }
    
    public void setSmoothScroll(final boolean smoothScroll) {
        this.smoothScroll = smoothScroll;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
