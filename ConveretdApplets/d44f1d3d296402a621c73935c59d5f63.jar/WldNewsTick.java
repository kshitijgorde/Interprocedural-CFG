import java.awt.Component;
import java.awt.Point;
import newstick.content.IContentParser;
import newstick.menu.MenuContentParser;
import java.awt.Color;
import java.awt.Font;
import newstick.typer.TyperDataText;
import java.awt.FontMetrics;
import newstick.menu.MenuItem;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Graphics;
import java.net.URL;
import newstick.content.Content;
import newstick.typer.TyperTextMagazine;
import java.awt.Image;
import java.awt.Rectangle;
import newstick.menu.Menu;
import newstick.js.OpenDocument;
import newstick.param.JavaScriptParameters;
import newstick.param.TyperParameters;
import newstick.param.MenuParameters;
import newstick.typer.Typer;
import newstick.typer.ITyperAction;
import newstick.menu.IMenuItemAction;
import newstick.menu.IMenuLinkPainter;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WldNewsTick extends Applet implements Runnable, IMenuLinkPainter, IMenuItemAction, ITyperAction
{
    private static final String codeBaseUrlParam = "codebase";
    private static final String themeUrlParam = "option";
    private static final String idUrlParam = "tickerID";
    private static final String jsNewsOpenParameters = "width=450,height=350,scrollbars=yes,menubar=no,location=no";
    private static final String jsMenuOpenParameters = "width=750,height=500,scrollbars=yes,menubar=yes,location=yes";
    private static final int intervalSecondsRefresh = 3600;
    private static final String menuOptionError = "Can`t display menu.";
    private static final boolean urlNotNormal = true;
    private static final boolean encodingCodebase = false;
    private Typer typer;
    private MenuParameters menuParameters;
    private TyperParameters typerParameters;
    private JavaScriptParameters jsParameters;
    private OpenDocument openDoc;
    private Menu menu;
    private Rectangle menuLinkArea;
    private Image backImage;
    private Image tickerImage;
    private int typerTop;
    private Thread threadTick;
    private TyperTextMagazine magazine;
    private Content threadMenuItems;
    private URL currentUrl;
    
    private void paintAll() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final Graphics graphics = this.backImage.getGraphics();
        graphics.setColor(this.typerParameters.backColor);
        graphics.fillRect(0, 0, width - 1, height - 1);
        graphics.setColor(this.typerParameters.outlineColor);
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.setColor(this.typerParameters.backColor);
        int n = width - (this.typerParameters.typerRight + this.typerParameters.typerLeft + 1);
        int n2 = height - (this.typerParameters.typerBottom + this.typerTop + 1);
        if (this.typerParameters.typer3DArea == 1 || this.typerParameters.typer3DArea == 2) {
            graphics.draw3DRect(this.typerParameters.typerLeft, this.typerTop, n, n2, this.typerParameters.typer3DArea == 1);
        }
        else {
            graphics.drawRect(this.typerParameters.typerLeft, this.typerTop, n, n2);
        }
        graphics.setColor(this.typerParameters.backTyperColor);
        int typerLeft = this.typerParameters.typerLeft;
        int typerTop = this.typerTop;
        if (this.typerParameters.typer3DAreaBool) {
            --n;
            --n2;
            ++typerLeft;
            ++typerTop;
        }
        graphics.fillRect(typerLeft, typerTop, n, n2);
    }
    
    public void stop() {
        this.stopThread(this.threadTick);
        this.stopThread(this.threadMenuItems);
        if (this.magazine != null) {
            this.magazine.stop();
        }
    }
    
    private boolean canMenu(final Image image) {
        image.getGraphics().setFont(this.typerParameters.font);
        return this.getSize().height > 4 * image.getGraphics().getFontMetrics().getHeight();
    }
    
    public void typerAction(final URL url) {
        URL url2 = null;
        try {
            url2 = new URL(url.toString());
            this.openDocumentInit();
            if (!this.openDoc.openNews(this.jsParameters.openNewsMethod, url2, "width=450,height=350,scrollbars=yes,menubar=no,location=no") && !this.openDoc.openNews(url2, "width=450,height=350,scrollbars=yes,menubar=no,location=no")) {
                this.getAppletContext().showDocument(url2, "_blank");
            }
        }
        catch (MalformedURLException ex) {}
        catch (Exception ex2) {
            if (url2 != null) {
                this.getAppletContext().showDocument(url2, "_blank");
            }
        }
    }
    
    private void sleep(final int n) {
        if (n > 0) {
            try {
                Thread.currentThread();
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void openDocumentInit() {
        if (this.openDoc == null) {
            this.openDoc = new OpenDocument(this);
        }
    }
    
    private void stopThread(final Thread thread) {
        if (thread != null) {
            thread.stop();
        }
    }
    
    public void paint(final Graphics graphics) {
        int n = this.typerParameters.typerLeft + this.typerParameters.textHorizMargin;
        int n2 = this.typerTop + this.typerParameters.textVertMargin;
        if (this.typerParameters.typer3DAreaBool) {
            ++n;
            ++n2;
        }
        this.backImage.getGraphics().drawImage(this.tickerImage, n, n2, this);
        if (this.menu != null && this.menu.isShowed()) {
            this.menu.paint(this.backImage.getGraphics());
        }
        graphics.drawImage(this.backImage, 0, 0, this);
    }
    
    public void menuItemAction(final MenuItem menuItem) {
        if (menuItem == null || menuItem.Command == null) {
            return;
        }
        URL url = null;
        if (menuItem.Command.startsWith("http://")) {
            try {
                url = new URL(menuItem.Command);
                this.openDocumentInit();
                if (!this.openDoc.openDocument(this.jsParameters.openDocumentMethod, url, "width=750,height=500,scrollbars=yes,menubar=yes,location=yes", menuItem.Display) && !this.openDoc.openDocument(url, "width=750,height=500,scrollbars=yes,menubar=yes,location=yes", menuItem.Display)) {
                    this.getAppletContext().showDocument(url, "_blank");
                }
            }
            catch (MalformedURLException ex) {}
            catch (Exception ex2) {
                if (url != null) {
                    this.getAppletContext().showDocument(url, "_blank");
                }
            }
        }
        else {
            this.tickerWaitingContentGet();
            this.tickerContentGet(this.typerParameters.contentUrl.toString(), "option" + "=" + menuItem.Command);
        }
    }
    
    private Rectangle getMenuLinkArea(final Image image, final float n) {
        final Graphics graphics = image.getGraphics();
        graphics.setFont(this.typerParameters.font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(this.menuParameters.menuDisplay);
        Rectangle rectangle;
        if (n == 1.0f) {
            rectangle = new Rectangle(image.getWidth(null) - this.typerParameters.typerLeft - stringWidth - 2, 0, stringWidth + 1, fontMetrics.getHeight() - 1);
        }
        else {
            rectangle = new Rectangle(this.typerParameters.typerLeft, 0, stringWidth + 1, fontMetrics.getHeight() - 1);
        }
        return rectangle;
    }
    
    public void menuLinkPaint() {
        this.paintAll();
        this.menuPaintLink(this.backImage.getGraphics(), this.typerParameters.font, this.typerParameters.foreColor, this.menuLinkArea);
    }
    
    private void initImage() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.backImage = this.createImage(width, height);
        if (this.menuParameters.menuShow) {
            this.menuParameters.menuShow = this.canMenu(this.backImage);
        }
        if (this.menuParameters.menuShow) {
            final Graphics graphics = this.backImage.getGraphics();
            graphics.setFont(this.typerParameters.font);
            this.typerTop = Math.max(this.typerParameters.typerTop, graphics.getFontMetrics().getHeight());
        }
        else {
            this.typerTop = this.typerParameters.typerTop;
        }
        int n = width - (this.typerParameters.typerLeft + this.typerParameters.typerRight);
        int n2 = height - (this.typerTop + this.typerParameters.typerBottom);
        if (this.typerParameters.typer3DAreaBool) {
            --n;
            --n2;
        }
        this.tickerImage = this.createImage(n - 2 * this.typerParameters.textHorizMargin - 1, n2 - 2 * this.typerParameters.textVertMargin - 1);
        final Graphics graphics2 = this.tickerImage.getGraphics();
        graphics2.setColor(this.typerParameters.backTyperColor);
        graphics2.fillRect(0, 0, n - 1, n2 - 1);
        this.paintAll();
    }
    
    private void tickerWaitingContentGet() {
        final TyperDataText[] array = new TyperDataText[this.typerParameters.waitingMessageRepeat];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new TyperDataText(this.typerParameters.waitingMessage, null, null);
        }
        this.typer.newContent(array);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void menuPaintLink(final Graphics graphics, final Font font, final Color color, final Rectangle rectangle) {
        graphics.setFont(font);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(color);
        graphics.drawString(this.menuParameters.menuDisplay, rectangle.x, rectangle.y + fontMetrics.getAscent());
    }
    
    private void menuRun() {
        URL authUrl;
        try {
            authUrl = this.makeAuthUrl(this.menuParameters.menuItemsUrl.toString());
        }
        catch (MalformedURLException ex) {
            authUrl = null;
        }
        if (authUrl != null) {
            final MenuContentParser menuContentParser = new MenuContentParser(this.menu, authUrl);
            menuContentParser.errorOption = "Can`t display menu.";
            (this.threadMenuItems = new Content(menuContentParser)).start();
        }
    }
    
    public void start() {
        (this.threadTick = new Thread(this)).start();
    }
    
    private URL makeAuthUrl(final String s) throws MalformedURLException {
        return this.makeAuthUrl(s, null);
    }
    
    private URL makeAuthUrl(final String s, final String s2) throws MalformedURLException {
        StringBuffer sb;
        if (s.charAt(s.length() - 1) == '/') {
            sb = new StringBuffer(s.substring(0, s.length() - 1));
        }
        else {
            sb = new StringBuffer(s);
        }
        sb.append("/" + "tickerID" + "/" + this.typerParameters.id);
        if (s2 != null) {
            sb.append("/" + s2.replace('=', '/').replace('&', '/'));
        }
        final String string = this.getDocumentBase().toString();
        String s3;
        if (this.getDocumentBase().getProtocol().compareTo("file") == 0) {
            s3 = ":/";
        }
        else {
            s3 = "://";
        }
        final String substring = string.substring(string.indexOf(s3) + s3.length(), string.length());
        if (substring.indexOf("?") != -1) {
            sb.append("/" + "codebase" + "/" + substring.substring(0, substring.indexOf("?")));
        }
        else {
            sb.append("/" + "codebase" + "/" + substring);
        }
        return new URL(sb.toString());
    }
    
    public void run() {
        final Rectangle area = new Rectangle(0, 0, this.tickerImage.getWidth(this), this.tickerImage.getHeight(this));
        final Graphics graphics = this.tickerImage.getGraphics();
        graphics.setFont(this.typerParameters.font);
        this.typer.setGraphics(graphics);
        this.typer.setArea(area);
        this.typer.setBackColor(this.typerParameters.backTyperColor);
        this.typer.setForeColor(this.typerParameters.foreColor);
        this.typer.setForeHighliteColor(this.typerParameters.highliteForeColor);
        this.typer.setTitleFont(this.typerParameters.titleFont);
        this.typer.setTitleColor(this.typerParameters.titleColor);
        this.typer.setTitleHighliteColor(this.typerParameters.highliteTitleColor);
        this.typer.setCharSpace(this.typerParameters.typerCharSpace);
        this.typer.setLineSpace(this.typerParameters.typerLineSpace);
        this.typer.setCharSpeed(this.typerParameters.typerCharSpeed);
        this.typer.setLineSpeed(this.typerParameters.typerLineSpeed);
        this.typer.setScrollSpeed(this.typerParameters.typerScrollSpeed);
        this.typer.setStartPos(this.typerParameters.typerStart);
        this.typer.setAfterTitleSpace(this.typerParameters.afterTitleSpace);
        this.typer.setAfterTextSpace(this.typerParameters.afterTextSpace);
        this.typer.setResetTopLines(this.typerParameters.newPageTop);
        this.tickerWaitingContentGet();
        String string = null;
        if (this.typerParameters.startTheme != null) {
            string = "option" + "=" + this.typerParameters.startTheme;
        }
        this.tickerContentGet(this.typerParameters.contentUrl.toString(), string);
        if (this.menuParameters.menuShow) {
            this.menuRun();
        }
        while (true) {
            if (this.currentUrl != null && this.magazine.isFresh(this.currentUrl)) {
                this.typer.clearContent();
                this.magazine.fillText(this.currentUrl, this.typer);
            }
            this.typer.type();
        }
    }
    
    public void init() {
        this.setFont(new Font("Tahoma", 0, 10));
        this.menuParameters = new MenuParameters(this);
        this.typerParameters = new TyperParameters(this);
        this.jsParameters = new JavaScriptParameters(this);
        this.initImage();
        if (this.menuParameters.menuShow) {
            this.menuInit();
        }
        this.magazine = new TyperTextMagazine(3600);
        this.magazine.errorMessage = this.typerParameters.waitingMessage;
        this.typer = new Typer(this, new Point(this.typerParameters.typerLeft + this.typerParameters.textHorizMargin, this.typerTop + this.typerParameters.textVertMargin));
        this.openDoc = null;
    }
    
    private void menuInit() {
        this.menuLinkArea = this.getMenuLinkArea(this.backImage, this.menuParameters.menuAlignment);
        this.menuPaintLink(this.backImage.getGraphics(), this.typerParameters.font, this.typerParameters.foreColor, this.menuLinkArea);
        (this.menu = new Menu(this, this.menuLinkArea)).setArea(new Rectangle(this.typerParameters.typerLeft, this.typerTop, this.getSize().width - (this.typerParameters.typerLeft + this.typerParameters.typerRight + 1), this.getSize().height - (this.typerTop + 1)));
        this.menu.setAlign(this.menuParameters.menuAlignment);
        this.menu.setFont(this.menuParameters.menuFont);
        this.menu.setHorizMargin(this.menuParameters.menuHorizMargin);
        this.menu.setVertMargin(this.menuParameters.menuVertMargin);
        this.menu.setBackColor(this.menuParameters.menuBackColor);
        this.menu.setForeColor(this.menuParameters.menuForeColor);
        this.menu.setOutlineColor(this.menuParameters.menuOutlineColor);
        this.menu.setHighliteBackColor(this.menuParameters.menuHighliteBackColor);
        this.menu.setHighliteForeColor(this.menuParameters.menuHighliteForeColor);
    }
    
    private void tickerContentGet(final String s, final String s2) {
        URL authUrl;
        try {
            authUrl = this.makeAuthUrl(s, s2);
        }
        catch (MalformedURLException ex) {
            this.typer.newContent(new TyperDataText[] { new TyperDataText(ex.getMessage(), "MalformedURLException", null) });
            authUrl = null;
        }
        if (authUrl != null) {
            this.currentUrl = authUrl;
            this.magazine.fillText(authUrl, this.typer);
        }
    }
}
