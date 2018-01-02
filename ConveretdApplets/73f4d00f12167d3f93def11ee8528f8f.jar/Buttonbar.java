import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.FontMetrics;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Buttonbar extends Applet implements Runnable
{
    public static final int HORIZONTAL = 42;
    public static final int VERTICAL = 195951310;
    public static final int NORMAL = 2523;
    public static final int PRESSED = 2134;
    public static final int HIGHLIGHTED = 442;
    public static final int NONE = -234;
    public static final int LEFT = -235;
    public static final int RIGHT = -236;
    public static final int CENTER = -237;
    public static final String COPYRIGHT = "The Buttonbar Applet V1.6\r\nby Dirk Middendorf\r\ndmidden@moonman.com\r\nhttp://welcome.to/javaforall\r\n";
    public static final boolean SHAREWARE = true;
    private AudioClip MouseOverSound;
    private AudioClip PressedSound;
    private boolean InitializingMode;
    private boolean ButtonsStayPressed;
    private boolean ErrorOnInit;
    private Color DefaultColor;
    private Color DefaultHighlColor;
    private Color DefaultPressedColor;
    private Color[] ButtonFontColor;
    private Color[] HighlFontColor;
    private Color[] PressedFontColor;
    private Font[] ButtonFont;
    private Graphics OffGraphics;
    private Image NormImage;
    private Image HighlImage;
    private Image PressedImage;
    private Image OffImage;
    private Image BackgroundImage;
    private Image[] NormImages;
    private Image[] HighlImages;
    private Image[] PressedImages;
    private int ImageWidth;
    private int ImageHeight;
    private int NumberOfButtons;
    private int Orientation;
    private int CurHighlButton;
    private int CurPressedButton;
    private int AppletWidth;
    private int AppletHeight;
    private int DefaultFontSize;
    private int DefaultAlignment;
    private int[] ButtonState;
    private int[] ButtonAlignment;
    private String LoadingStatus;
    private String DefaultFontName;
    private String DefaultFontStyle;
    private String DefaultDestFrame;
    private String ErrorMessage;
    private String[] ButtonText;
    private String[] StatusMessage;
    private String[] DestFrame;
    private Thread TO;
    private URL HomepageURL;
    private URL[] DestURL;
    
    public Buttonbar() {
        final Color black = Color.black;
        this.DefaultPressedColor = black;
        this.DefaultHighlColor = black;
        this.DefaultColor = black;
        this.DefaultFontName = "Helvetica";
        this.DefaultFontStyle = "PLAIN";
        this.DefaultFontSize = 12;
        this.ErrorOnInit = false;
        this.InitializingMode = true;
        this.ErrorMessage = "";
        this.DefaultAlignment = -237;
        this.Orientation = 195951310;
    }
    
    private Color String2Color(final String s) {
        Color color;
        if (s.equalsIgnoreCase("BLACK")) {
            color = Color.black;
        }
        else if (s.equalsIgnoreCase("BLUE")) {
            color = Color.blue;
        }
        else if (s.equalsIgnoreCase("CYAN")) {
            color = Color.cyan;
        }
        else if (s.equalsIgnoreCase("DARKGRAY")) {
            color = Color.darkGray;
        }
        else if (s.equalsIgnoreCase("GRAY")) {
            color = Color.gray;
        }
        else if (s.equalsIgnoreCase("GREEN")) {
            color = Color.green;
        }
        else if (s.equalsIgnoreCase("LIGHTGRAY")) {
            color = Color.lightGray;
        }
        else if (s.equalsIgnoreCase("MAGENTA")) {
            color = Color.magenta;
        }
        else if (s.equalsIgnoreCase("ORANGE")) {
            color = Color.orange;
        }
        else if (s.equalsIgnoreCase("PINK")) {
            color = Color.pink;
        }
        else if (s.equalsIgnoreCase("RED")) {
            color = Color.red;
        }
        else if (s.equalsIgnoreCase("WHITE")) {
            color = Color.white;
        }
        else if (s.equalsIgnoreCase("YELLOW")) {
            color = Color.yellow;
        }
        else {
            color = new Color(Integer.parseInt(s, 16));
        }
        return color;
    }
    
    public String getAppletInfo() {
        return "The Buttonbar Applet V1.6\r\nby Dirk Middendorf\r\ndmidden@moonman.com\r\nhttp://welcome.to/javaforall\r\n";
    }
    
    private int getButtonIndex(final int n, final int n2) {
        if (this.Orientation == 42) {
            return n / this.ImageWidth;
        }
        if (this.Orientation == 195951310) {
            return n2 / this.ImageHeight;
        }
        return 0;
    }
    
    private int getTextXPos(final Graphics graphics, final int n) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this.Orientation == 195951310) {
            switch (this.ButtonAlignment[n]) {
                case -237: {
                    return (this.ImageWidth - fontMetrics.stringWidth(this.ButtonText[n])) / 2;
                }
                case -235: {
                    return 2;
                }
                case -236: {
                    return (this.ImageWidth - fontMetrics.stringWidth(this.ButtonText[n])) / 2 * 2 - 2;
                }
            }
        }
        if (this.Orientation == 42) {
            switch (this.ButtonAlignment[n]) {
                case -237: {
                    return (this.ImageWidth - fontMetrics.stringWidth(this.ButtonText[n])) / 2 + this.getXPos(n);
                }
                case -235: {
                    return this.getXPos(n) + 2;
                }
                case -236: {
                    int n2 = (this.ImageWidth - fontMetrics.stringWidth(this.ButtonText[n])) / 2 * 2 + this.getXPos(n);
                    n2 -= 2;
                    return n2;
                }
            }
        }
        return 0;
    }
    
    private int getTextYPos(final Graphics graphics, final int n) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int ascent = fontMetrics.getAscent();
        final int leading = fontMetrics.getLeading();
        final int n2 = (this.ImageHeight - height) / 2;
        if (this.Orientation == 195951310) {
            return n2 + ascent + leading + this.ImageHeight * n;
        }
        if (this.Orientation == 42) {
            return n2 + ascent + leading;
        }
        return 0;
    }
    
    private int getXPos(final int n) {
        if (this.Orientation == 42) {
            return n * this.ImageWidth;
        }
        if (this.Orientation == 195951310) {
            return 0;
        }
        return 0;
    }
    
    private int getYPos(final int n) {
        if (this.Orientation == 42) {
            return 0;
        }
        if (this.Orientation == 195951310) {
            return n * this.ImageHeight;
        }
        return 0;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int buttonIndex = this.getButtonIndex(n, n2);
        if (!this.InitializingMode) {
            if (this.CurPressedButton != -234) {
                this.ButtonState[this.CurPressedButton] = 2523;
                this.repaintButton(this.CurPressedButton);
            }
            this.ButtonState[buttonIndex] = 2134;
            this.repaintButton(this.CurPressedButton = buttonIndex);
            if (this.PressedSound != null) {
                this.PressedSound.play();
            }
            this.CurHighlButton = -234;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.InitializingMode && this.CurHighlButton != -234) {
            this.ButtonState[this.CurHighlButton] = 2523;
            this.repaintButton(this.CurHighlButton);
            this.CurHighlButton = -234;
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int buttonIndex = this.getButtonIndex(n, n2);
        if (this.InitializingMode) {
            return true;
        }
        if (this.StatusMessage[buttonIndex] == null) {}
        if (false) {
            this.showStatus(this.StatusMessage[buttonIndex]);
        }
        if (this.StatusMessage[buttonIndex] != null) {}
        if (false) {
            this.showStatus(" ");
        }
        this.showStatus("This version is Shareware! Please register!");
        if (buttonIndex == this.CurHighlButton) {
            return true;
        }
        if (buttonIndex == this.CurPressedButton) {
            if (this.CurHighlButton != -234) {
                this.ButtonState[this.CurHighlButton] = 2523;
                this.repaintButton(this.CurHighlButton);
                this.CurHighlButton = -234;
            }
            return true;
        }
        if (this.MouseOverSound != null) {
            this.MouseOverSound.play();
        }
        if (this.CurHighlButton != -234) {
            this.ButtonState[this.CurHighlButton] = 2523;
            this.repaintButton(this.CurHighlButton);
        }
        this.ButtonState[buttonIndex] = 442;
        this.repaintButton(buttonIndex);
        this.CurHighlButton = buttonIndex;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int buttonIndex = this.getButtonIndex(n, n2);
        if (!this.InitializingMode) {
            if (buttonIndex == this.CurPressedButton && !this.ButtonsStayPressed) {
                this.ButtonState[buttonIndex] = 442;
                this.repaintButton(buttonIndex);
                this.CurPressedButton = -234;
                this.myShowDocument(this.CurHighlButton = buttonIndex);
                return true;
            }
            if (buttonIndex == this.CurPressedButton && this.ButtonsStayPressed) {
                this.myShowDocument(buttonIndex);
                return true;
            }
            if (buttonIndex != this.CurPressedButton && !this.ButtonsStayPressed) {
                this.ButtonState[this.CurPressedButton] = 2523;
                this.repaintButton(this.CurPressedButton);
                this.ButtonState[buttonIndex] = 442;
                this.repaintButton(buttonIndex);
                this.CurPressedButton = -234;
                this.myShowDocument(this.CurHighlButton = buttonIndex);
                return true;
            }
            if (buttonIndex != this.CurPressedButton && this.ButtonsStayPressed) {
                this.ButtonState[buttonIndex] = 442;
                this.repaintButton(buttonIndex);
                this.myShowDocument(this.CurHighlButton = buttonIndex);
                return true;
            }
        }
        else {
            this.getAppletContext().showDocument(this.HomepageURL, "_new");
        }
        return true;
    }
    
    private void myShowDocument(final int n) {
        if (this.DestFrame[n] == null) {
            this.getAppletContext().showDocument(this.DestURL[n], "_self");
        }
        else {
            this.getAppletContext().showDocument(this.DestURL[n], this.DestFrame[n]);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.ErrorOnInit) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(Color.black);
            graphics.drawString(this.LoadingStatus, 5, 11);
            if (this.Orientation == 195951310) {
                graphics.drawString(this.ErrorMessage, 5, 25);
            }
            if (this.Orientation == 42) {
                graphics.drawString(this.ErrorMessage, 110, 11);
            }
            return;
        }
        if (this.InitializingMode) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(Color.black);
            if (this.LoadingStatus != null) {
                graphics.drawString(this.LoadingStatus, 5, 11);
            }
            if (this.Orientation == 195951310) {
                graphics.drawString("Click here to get this applet.", 5, 25);
            }
            if (this.Orientation == 42) {
                graphics.drawString("Click here to get this applet.", 110, 11);
            }
            return;
        }
        if (this.BackgroundImage != null) {
            this.OffGraphics.drawImage(this.BackgroundImage, 0, 0, this);
        }
        for (int i = 0; i < this.NumberOfButtons; ++i) {
            switch (this.ButtonState[i]) {
                case 2523: {
                    if (this.NormImages[i] != null) {
                        this.OffGraphics.drawImage(this.NormImages[i], this.getXPos(i), this.getYPos(i), this);
                    }
                    else {
                        this.OffGraphics.drawImage(this.NormImage, this.getXPos(i), this.getYPos(i), this);
                    }
                    this.OffGraphics.setColor(this.ButtonFontColor[i]);
                    break;
                }
                case 2134: {
                    if (this.PressedImages[i] != null) {
                        this.OffGraphics.drawImage(this.PressedImages[i], this.getXPos(i), this.getYPos(i), this);
                    }
                    else {
                        this.OffGraphics.drawImage(this.PressedImage, this.getXPos(i), this.getYPos(i), this);
                    }
                    this.OffGraphics.setColor(this.PressedFontColor[i]);
                    break;
                }
                case 442: {
                    if (this.HighlImages[i] != null) {
                        this.OffGraphics.drawImage(this.HighlImages[i], this.getXPos(i), this.getYPos(i), this);
                    }
                    else {
                        this.OffGraphics.drawImage(this.HighlImage, this.getXPos(i), this.getYPos(i), this);
                    }
                    this.OffGraphics.setColor(this.HighlFontColor[i]);
                    break;
                }
            }
            this.OffGraphics.setFont(this.ButtonFont[i]);
            this.OffGraphics.drawString(this.ButtonText[i], this.getTextXPos(this.OffGraphics, i), this.getTextYPos(this.OffGraphics, i));
        }
        graphics.drawImage(this.OffImage, 0, 0, this);
    }
    
    private void repaintButton(final int n) {
        this.repaint(this.getXPos(n), this.getYPos(n), this.getXPos(n) + this.ImageWidth, this.getYPos(n) + this.ImageHeight);
    }
    
    public void run() {
        System.out.println("The Buttonbar Applet V1.6\r\nby Dirk Middendorf\r\ndmidden@moonman.com\r\nhttp://welcome.to/javaforall\r\nShareware Version");
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.LoadingStatus = "Initializing...";
        if (this.getParameter("ORIENTATION") != null && this.getParameter("ORIENTATION").equalsIgnoreCase("HORIZONTAL")) {
            this.Orientation = 42;
        }
        try {
            this.HomepageURL = new URL("http://welcome.to/javaforall");
        }
        catch (MalformedURLException ex) {}
        this.AppletWidth = this.size().width;
        this.AppletHeight = this.size().height;
        this.repaint(0, 0, this.AppletWidth, 40);
        if (this.getParameter("backgroundimage") != null) {
            final Image image = this.getImage(this.getDocumentBase(), this.getParameter("backgroundimage"));
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex2) {}
            if (mediaTracker.isErrorAny()) {
                this.setErrorOnInit("Couldn't load background image!");
                this.TO.stop();
            }
            this.BackgroundImage = this.createImage(this.AppletWidth, this.AppletHeight);
            this.OffGraphics = this.BackgroundImage.getGraphics();
            for (int i = 0; i < this.AppletWidth; i += image.getWidth(this)) {
                for (int j = 0; j < this.AppletHeight; j += image.getHeight(this)) {
                    this.OffGraphics.drawImage(image, i, j, this);
                }
            }
        }
        this.OffImage = this.createImage(this.AppletWidth, this.AppletHeight);
        this.OffGraphics = this.OffImage.getGraphics();
        if (this.getParameter("pressedsound") != null) {
            this.PressedSound = this.getAudioClip(this.getDocumentBase(), this.getParameter("pressedsound"));
        }
        if (this.getParameter("DEFAULTALIGNMENT") != null) {
            if (this.getParameter("DEFAULTALIGNMENT").equalsIgnoreCase("left")) {
                this.DefaultAlignment = -235;
            }
            if (this.getParameter("DEFAULTALIGNMENT").equalsIgnoreCase("right")) {
                this.DefaultAlignment = -236;
            }
        }
        if (this.getParameter("DEFAULTFONTNAME") != null) {
            this.DefaultFontName = this.getParameter("DEFAULTFONTNAME");
        }
        if (this.getParameter("DEFAULTFONTSIZE") != null) {
            this.DefaultFontSize = Integer.parseInt(this.getParameter("DEFAULTFONTSIZE"));
        }
        if (this.getParameter("DEFAULTFONTSTYLE") != null) {
            this.DefaultFontStyle = this.getParameter("DEFAULTFONTSTYLE");
        }
        if (this.getParameter("DEFAULTFONTCOLOR") != null) {
            this.DefaultColor = this.String2Color(this.getParameter("DEFAULTFONTCOLOR"));
        }
        this.DefaultColor = Color.white;
        if (this.getParameter("DEFAULTPRESSEDFONTCOLOR") != null) {
            this.DefaultPressedColor = this.String2Color(this.getParameter("DEFAULTPRESSEDFONTCOLOR"));
        }
        else {
            this.DefaultPressedColor = this.DefaultColor;
        }
        if (this.getParameter("DEFAULTHIGHLFONTCOLOR") != null) {
            this.DefaultHighlColor = this.String2Color(this.getParameter("DEFAULTHIGHLFONTCOLOR"));
        }
        else {
            this.DefaultHighlColor = this.DefaultColor;
        }
        if (this.getParameter("DEFAULTDESTFRAME") != null) {
            this.DefaultDestFrame = this.getParameter("DEFAULTDESTFRAME");
        }
        if (this.getParameter("BUTTONSSTAYPRESSED") != null) {
            this.ButtonsStayPressed = true;
        }
        else {
            this.ButtonsStayPressed = false;
        }
        this.NormImage = this.getImage(this.getDocumentBase(), this.getParameter("NORMBUTTON"));
        this.HighlImage = this.getImage(this.getDocumentBase(), this.getParameter("HIGHLBUTTON"));
        this.PressedImage = this.getImage(this.getDocumentBase(), this.getParameter("PRESSEDBUTTON"));
        mediaTracker.addImage(this.NormImage, 0);
        mediaTracker.addImage(this.HighlImage, 1);
        mediaTracker.addImage(this.PressedImage, 2);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex3) {}
        if (mediaTracker.isErrorAny()) {
            this.setErrorOnInit("Couldn't load button images!");
            this.TO.stop();
        }
        this.ImageWidth = this.NormImage.getWidth(this);
        this.ImageHeight = this.NormImage.getHeight(this);
        while (this.getParameter("BUTTONTEXT" + (this.NumberOfButtons + 1)) != null) {
            ++this.NumberOfButtons;
        }
        if (this.NumberOfButtons > 10) {
            this.NumberOfButtons = 10;
        }
        this.NormImages = new Image[this.NumberOfButtons];
        this.HighlImages = new Image[this.NumberOfButtons];
        this.PressedImages = new Image[this.NumberOfButtons];
        this.ButtonText = new String[this.NumberOfButtons];
        this.ButtonFont = new Font[this.NumberOfButtons];
        this.ButtonFontColor = new Color[this.NumberOfButtons];
        this.ButtonState = new int[this.NumberOfButtons];
        this.DestURL = new URL[this.NumberOfButtons];
        this.DestFrame = new String[this.NumberOfButtons];
        this.StatusMessage = new String[this.NumberOfButtons];
        this.HighlFontColor = new Color[this.NumberOfButtons];
        this.PressedFontColor = new Color[this.NumberOfButtons];
        this.ButtonAlignment = new int[this.NumberOfButtons];
        for (int k = 0; k < this.NumberOfButtons; ++k) {
            if (this.getParameter("specnormbutton" + (k + 1)) != null) {
                mediaTracker.addImage(this.NormImages[k] = this.getImage(this.getDocumentBase(), this.getParameter("specnormbutton" + (k + 1))), 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex4) {}
                if (mediaTracker.isErrorAny()) {
                    this.setErrorOnInit("Couldn't load button #" + (k + 1));
                    this.TO.stop();
                }
            }
            if (this.getParameter("spechighlbutton" + (k + 1)) != null) {
                mediaTracker.addImage(this.HighlImages[k] = this.getImage(this.getDocumentBase(), this.getParameter("spechighlbutton" + (k + 1))), 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex5) {}
                if (mediaTracker.isErrorAny()) {
                    this.setErrorOnInit("Couldn't load button #" + (k + 1));
                    this.TO.stop();
                }
            }
            if (this.getParameter("specpressedbutton" + (k + 1)) != null) {
                mediaTracker.addImage(this.PressedImages[k] = this.getImage(this.getDocumentBase(), this.getParameter("specpressedbutton" + (k + 1))), 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex6) {}
                if (mediaTracker.isErrorAny()) {
                    this.setErrorOnInit("Couldn't load button #" + (k + 1));
                    this.TO.stop();
                }
            }
            this.ButtonText[k] = this.getParameter("BUTTONTEXT" + (k + 1));
            String s = this.getParameter("FONTNAME" + (k + 1));
            if (s == null) {
                s = this.DefaultFontName;
            }
            String s2 = this.getParameter("FONTSTYLE" + (k + 1));
            if (s2 == null) {
                s2 = this.DefaultFontStyle;
            }
            int n;
            if (this.getParameter("FONTSIZE" + (k + 1)) != null) {
                n = Integer.parseInt(this.getParameter("FONTSIZE" + (k + 1)));
            }
            else {
                n = this.DefaultFontSize;
            }
            if (s2.equalsIgnoreCase("PLAIN")) {
                this.ButtonFont[k] = new Font(s, 0, n);
            }
            else if (s2.equalsIgnoreCase("BOLD")) {
                this.ButtonFont[k] = new Font(s, 1, n);
            }
            else if (s2.equalsIgnoreCase("ITALIC")) {
                this.ButtonFont[k] = new Font(s, 2, n);
            }
            this.ButtonAlignment[k] = this.DefaultAlignment;
            if (this.getParameter("BUTTONALIGNMENT" + (k + 1)) != null) {
                if (this.getParameter("BUTTONALIGNMENT" + (k + 1)).equalsIgnoreCase("RIGHT")) {
                    this.ButtonAlignment[k] = -236;
                }
                if (this.getParameter("BUTTONALIGNMENT" + (k + 1)).equalsIgnoreCase("LEFT")) {
                    this.ButtonAlignment[k] = -235;
                }
            }
            if (this.getParameter("FONTCOLOR" + (k + 1)) == null) {
                this.ButtonFontColor[k] = this.DefaultColor;
            }
            else {
                this.ButtonFontColor[k] = this.String2Color(this.getParameter("FONTCOLOR" + (k + 1)));
            }
            if (this.getParameter("HIGHLFONTCOLOR" + (k + 1)) == null) {
                this.HighlFontColor[k] = this.DefaultHighlColor;
            }
            else {
                this.HighlFontColor[k] = this.String2Color(this.getParameter("HIGHLFONTCOLOR" + (k + 1)));
            }
            if (this.getParameter("PRESSEDFONTCOLOR" + (k + 1)) == null) {
                this.PressedFontColor[k] = this.DefaultPressedColor;
            }
            else {
                this.PressedFontColor[k] = this.String2Color(this.getParameter("PRESSEDFONTCOLOR" + (k + 1)));
            }
            try {
                if (k == 0) {
                    this.DestURL[k] = new URL(this.getDocumentBase(), "buttonbar_mainframe.html");
                }
                else if (this.getParameter("desturl" + (k + 1)).substring(0, 4).equalsIgnoreCase("http")) {
                    this.DestURL[k] = new URL(this.getParameter("desturl" + (k + 1)));
                }
                else {
                    this.DestURL[k] = new URL(this.getDocumentBase(), this.getParameter("DESTURL" + (k + 1)));
                }
            }
            catch (MalformedURLException ex7) {
                if (mediaTracker.isErrorAny()) {
                    this.setErrorOnInit("Malformed URL at button #" + (k + 1));
                    this.TO.stop();
                }
            }
            if (this.getParameter("DESTFRAME" + (k + 1)) != null) {
                this.DestFrame[k] = this.getParameter("DESTFRAME" + (k + 1));
            }
            else {
                this.DestFrame[k] = this.DefaultDestFrame;
            }
            this.StatusMessage[k] = this.getParameter("STATUSMESSAGE" + (k + 1));
            this.ButtonState[k] = 2523;
        }
        this.CurHighlButton = -234;
        this.CurPressedButton = -234;
        if (this.ButtonsStayPressed) {
            if (this.getParameter("STARTBUTTON") != null) {
                this.CurPressedButton = Integer.parseInt(this.getParameter("STARTBUTTON")) - 1;
                this.ButtonState[this.CurPressedButton] = 2134;
            }
            else {
                this.ButtonState[0] = 2134;
                this.CurPressedButton = 0;
            }
        }
        this.InitializingMode = false;
        this.LoadingStatus = null;
        this.repaint();
        this.TO = null;
    }
    
    private void setErrorOnInit(final String errorMessage) {
        this.ErrorOnInit = true;
        this.InitializingMode = false;
        this.showStatus(errorMessage);
        this.ErrorMessage = errorMessage;
        this.LoadingStatus = "Error while initializing!";
        this.repaint();
    }
    
    public void setNewPressedButton(final int n) {
        this.ButtonState[this.CurPressedButton] = 2523;
        this.repaintButton(this.CurPressedButton);
        this.CurPressedButton = n - 1;
        this.ButtonState[this.CurPressedButton] = 2134;
        this.repaintButton(this.CurPressedButton);
    }
    
    public void start() {
        if (this.TO == null && this.InitializingMode) {
            (this.TO = new Thread(this)).start();
        }
    }
    
    public void stop() {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
