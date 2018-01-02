import java.applet.AppletContext;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ticker extends Thread
{
    private String updateFrom;
    private int updateDelay;
    public int speedDelay;
    public int tickBy;
    public Color background;
    public Color linkColor;
    private Vector headlines;
    private int numHeadlines;
    private Image image;
    private Image temp;
    private JavaTicker applet;
    public int width;
    public int height;
    public int x;
    public int y;
    private int yOffset;
    private int currentX;
    private int currentY;
    private Update update;
    public int bufferSize;
    public int mode;
    private boolean updating;
    private boolean copying;
    public boolean local;
    private boolean mousemoved;
    private boolean mouseexited;
    public boolean showline;
    public boolean paused;
    public boolean tocenter;
    private int checkX;
    private int checkY;
    private String linkTarget;
    public Graphics g;
    public Graphics dummyg;
    private Graphics tg;
    private Graphics tempG;
    private Graphics imageG;
    public URL base;
    public boolean scale;
    public boolean changingScale;
    public boolean scrollingNow;
    public boolean drawing;
    private Headline lastClicked;
    private Headline lastTrue;
    private int halfwid;
    private int halfhei;
    private boolean isXP;
    public boolean recycle;
    private Headline nextHeadline;
    private Headline nextH;
    private int endposition;
    private int howMany;
    private StringTokenizer headlineST;
    private StringTokenizer linkST;
    
    public Ticker(final int mode, final String updateFrom, final boolean local, final int updateDelay, final int speedDelay, final int tickBy, final int bufferSize, final Color background, final Color linkColor, final boolean showline, final JavaTicker applet, final int x, final int y, final int width, final int height, final String linkTarget, final int updateMode) {
        this.bufferSize = 40;
        this.updateFrom = updateFrom;
        this.updateDelay = updateDelay;
        this.speedDelay = speedDelay;
        this.background = background;
        this.linkColor = linkColor;
        this.applet = applet;
        this.tickBy = tickBy;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mode = mode;
        this.bufferSize = bufferSize;
        this.linkTarget = linkTarget;
        this.local = local;
        this.showline = showline;
        this.halfwid = width / 2;
        this.halfhei = height / 2;
        this.updating = false;
        this.tocenter = false;
        this.copying = false;
        this.mousemoved = false;
        this.mouseexited = false;
        this.paused = false;
        this.changingScale = false;
        this.scrollingNow = false;
        this.drawing = false;
        this.lastClicked = null;
        this.lastTrue = null;
        this.checkX = -1;
        this.checkY = -1;
        this.numHeadlines = 20;
        this.isXP = false;
        try {
            if (System.getProperty("os.name").toString().startsWith("Windows NT")) {
                final Double version = new Double(System.getProperty("os.version").toString());
                if (version >= 5.1) {
                    this.isXP = true;
                }
            }
        }
        catch (Exception ex) {}
        this.recycle = true;
        applet.setRecycle();
        if (local) {
            this.base = applet.getDocumentBase();
        }
        else {
            this.base = applet.getCodeBase();
        }
        this.g = applet.getGraphics();
        this.dummyg = applet.createImage(1, 1).getGraphics();
        this.headlines = new Vector();
        int tmpwid = width * 2;
        if (this.isXP) {
            tmpwid = width * 3;
        }
        try {
            this.image = applet.createImage(width, height);
            this.temp = applet.createImage(tmpwid, height * 2);
        }
        catch (Exception ex2) {
            this.image = applet.createImage(width, height);
            this.temp = applet.createImage(tmpwid, height * 2);
        }
        this.tg = this.temp.getGraphics();
        this.tempG = this.tg;
        this.imageG = this.image.getGraphics();
        this.currentX = 0;
        (this.update = new Update(updateFrom, updateDelay, this, applet, updateMode)).start();
        this.yOffset = 8;
        for (int i = 0; i < this.headlines.size(); ++i) {
            this.nextHeadline = this.headlines.elementAt(i);
            if (this.yOffset < this.nextHeadline.ascent) {
                this.yOffset = this.nextHeadline.ascent;
            }
        }
        if (this.yOffset > height) {
            System.out.println("Warning: { Height allocated is less than needed");
        }
        this.tocenter = true;
        if (this.vScrollMode()) {
            this.tocenter = false;
        }
        this.scale = true;
        if (this.tocenter) {
            this.yOffset += (height - this.yOffset) / 2;
        }
    }
    
    public void stopUpdating() {
        try {
            this.update.stop();
        }
        catch (Exception ex) {}
    }
    
    public boolean isLoaded() {
        return this.headlines.size() > 0;
    }
    
    public void run() {
        while (true) {
            if (!this.paused) {
                switch (this.mode) {
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        this.scroll();
                        continue;
                    }
                    case 5: {
                        this.display();
                        continue;
                    }
                    default: {
                        this.mode = 1;
                        this.scroll();
                        continue;
                    }
                }
            }
            else {
                try {
                    Thread.sleep(5L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void killAllHeadlines() {
        for (int i = 0; i < this.headlines.size(); ++i) {
            this.nextHeadline = this.headlines.elementAt(i);
            this.nextHeadline.alive = false;
        }
    }
    
    private boolean fallenOff(final Headline nextHeadline, final int i) {
        int max1 = 100;
        int max2 = 200;
        if (this.updateFrom.indexOf("newsstory") >= 0) {
            max1 = (max2 = 2 * this.numHeadlines);
        }
        if (!nextHeadline.alive || i != 0 || !this.recycle) {
            return false;
        }
        switch (this.mode) {
            case 1: {
                return nextHeadline.getEndPosition() < 0 && !this.tooManyHeadlines() && this.numHeadlines < max1;
            }
            case 2: {
                return nextHeadline.myPosition > this.width && !this.tooManyHeadlines() && this.numHeadlines < max1;
            }
            case 3: {
                return nextHeadline.getYPosition() < 0 && !this.tooManyHeadlines() && this.numHeadlines < max2;
            }
            case 4: {
                return nextHeadline.getYPosition() > this.height && !this.tooManyHeadlines() && this.numHeadlines < max2;
            }
            default: {
                return false;
            }
        }
    }
    
    private void copyOver(Headline nextHeadline, final int i) {
        if (this.fallenOff(nextHeadline, i) && !this.updating && !this.changingScale) {
            this.copying = true;
            nextHeadline.alive = false;
            for (int k = 0; k < this.numHeadlines; ++k) {
                nextHeadline = this.headlines.elementAt(k);
                final Headline newHeadline = new Headline(nextHeadline.headline, nextHeadline.link, this, this.applet);
                switch (this.mode) {
                    case 1: {
                        newHeadline.myPosition = this.bufferSize + this.getEndPosition();
                        break;
                    }
                    case 2: {
                        newHeadline.myPosition = this.getEndPosition() - newHeadline.myWidth - this.bufferSize;
                        break;
                    }
                    case 3: {
                        newHeadline.myPosition = 0;
                        newHeadline.myYpos = this.bufferSize + this.getYEndPosition();
                        break;
                    }
                    case 4: {
                        newHeadline.myPosition = 0;
                        newHeadline.myYpos = this.bufferSize + this.getYEndPosition() - newHeadline.ascent;
                        break;
                    }
                }
                nextHeadline.hover = false;
                this.headlines.addElement(newHeadline);
            }
            this.copying = false;
        }
    }
    
    private void highlight(final Headline headline, final boolean hover) {
        if (this.mode == 5 || this.copying || this.drawing || !headline.alive || headline.headline == null) {
            return;
        }
        int adjust = this.tickBy;
        if (this.mode == 2 || this.mode == 4) {
            adjust = -this.tickBy;
        }
        headline.hover = hover;
        if (hover) {
            this.lastTrue = headline;
        }
        if (this.vScrollMode()) {
            headline.draw(this.tg, this.halfhei + headline.getYPosition() - this.currentY + adjust);
        }
        else {
            headline.draw(this.tg, this.yOffset, headline.myPosition - this.currentX + adjust + this.halfwid);
        }
        headline.reverseShift();
        if (this.paused) {
            this.draw();
        }
    }
    
    public boolean vScrollMode() {
        return this.mode == 3 || this.mode == 4;
    }
    
    private boolean onScreen(final Headline headline) {
        switch (this.mode) {
            case 1:
            case 2: {
                return this.nextHeadline.getEndPosition() >= -this.halfwid && this.nextHeadline.myPosition <= this.width * 2;
            }
            case 3:
            case 4: {
                return this.nextHeadline.getYPosition() <= this.height * 2 && this.nextHeadline.getYPosition() >= -this.halfhei;
            }
            default: {
                return false;
            }
        }
    }
    
    private void drawHeadlines() {
        this.drawing = true;
        this.tg.setColor(this.background);
        this.tg.fillRect(0, 0, this.width * 2, this.height * 2);
        for (int i = 0; i < this.headlines.size(); ++i) {
            this.nextHeadline = this.headlines.elementAt(i);
            if (this.vScrollMode()) {
                this.nextHeadline.myPosition = 0;
            }
            if (this.onScreen(this.nextHeadline)) {
                if (this.vScrollMode()) {
                    this.nextHeadline.draw(this.tg, this.halfhei + this.nextHeadline.getYPosition());
                }
                else {
                    this.nextHeadline.draw(this.tg, this.yOffset, this.nextHeadline.myPosition + this.halfwid);
                }
                switch (this.mode) {
                    case 2: {
                        this.nextHeadline.reverseShift();
                        this.nextHeadline.reverseShift();
                        break;
                    }
                    case 3: {
                        this.nextHeadline.reverseShift();
                        this.nextHeadline.shiftYPosition();
                        break;
                    }
                    case 4: {
                        this.nextHeadline.reverseShift();
                        this.nextHeadline.reverseYShift();
                        break;
                    }
                }
            }
            else {
                switch (this.mode) {
                    case 1: {
                        this.nextHeadline.shiftPosition();
                        break;
                    }
                    case 2: {
                        this.nextHeadline.reverseShift();
                        break;
                    }
                    case 3: {
                        this.nextHeadline.shiftYPosition();
                        break;
                    }
                    case 4: {
                        this.nextHeadline.reverseYShift();
                        break;
                    }
                }
            }
            this.copyOver(this.nextHeadline, i);
        }
        this.drawing = false;
    }
    
    public void bumpOldHeadlines() {
        if (this.headlines.size() <= this.numHeadlines) {
            if (this.update.connectionup) {
                return;
            }
        }
        while (this.updating || this.changingScale) {
            try {
                Thread.sleep(5L);
            }
            catch (Exception ex) {}
        }
        boolean allOffScreen = true;
        for (int i = 0; i < this.numHeadlines; ++i) {
            this.nextHeadline = this.headlines.elementAt(i);
            switch (this.mode) {
                case 1: {
                    if (this.nextHeadline.getEndPosition() > -this.halfwid) {
                        allOffScreen = false;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.nextHeadline.myPosition < this.width * 2) {
                        allOffScreen = false;
                        break;
                    }
                    break;
                }
                case 3: {
                    if (this.nextHeadline.getYEndPosition() > -this.halfhei) {
                        allOffScreen = false;
                        break;
                    }
                    break;
                }
                case 4: {
                    if (this.nextHeadline.getYPosition() < this.height * 2) {
                        allOffScreen = false;
                        break;
                    }
                    break;
                }
            }
        }
        if (allOffScreen) {
            for (int j = 0; j < this.numHeadlines; ++j) {
                this.headlines.elementAt(0).killOff();
                this.headlines.removeElementAt(0);
            }
        }
    }
    
    public void scrollSome() {
        int to = this.halfhei - this.yOffset;
        if (!this.vScrollMode()) {
            to = this.halfwid - this.yOffset;
        }
        if (!this.update.connectionup) {
            to = 10;
        }
        if (to < 0) {
            if (!this.vScrollMode()) {
                to = this.halfwid;
            }
            else {
                to = this.halfhei;
            }
        }
        for (int i = to; i > 0; i -= Math.abs(this.tickBy)) {
            final long starttime = new Date().getTime();
            for (int j = 0; j < this.headlines.size(); ++j) {
                final Headline nextHeadline = this.headlines.elementAt(j);
                switch (this.mode) {
                    case 1: {
                        nextHeadline.shiftPosition();
                        break;
                    }
                    case 2: {
                        nextHeadline.reverseShift();
                        break;
                    }
                    case 3: {
                        nextHeadline.shiftYPosition();
                        break;
                    }
                    case 4: {
                        nextHeadline.reverseYShift();
                        break;
                    }
                }
            }
            switch (this.mode) {
                case 1: {
                    this.currentX -= this.tickBy;
                    break;
                }
                case 2: {
                    this.currentX += this.tickBy;
                    break;
                }
                case 3: {
                    this.currentY -= this.tickBy;
                    break;
                }
                case 4: {
                    this.currentY += this.tickBy;
                    break;
                }
            }
            this.draw();
            final long endtime = new Date().getTime();
            if (!this.scrollingNow) {
                return;
            }
            long delay = endtime - starttime;
            delay = this.speedDelay - delay;
            if (delay > 0L) {
                try {
                    Thread.sleep(delay);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            while (this.paused) {
                try {
                    Thread.sleep(this.speedDelay * 4);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void scroll() {
        this.scrollingNow = true;
        final long starttime = new Date().getTime();
        final boolean b = false;
        this.currentY = (b ? 1 : 0);
        this.currentX = (b ? 1 : 0);
        this.drawHeadlines();
        this.bumpOldHeadlines();
        this.draw();
        final long endtime = new Date().getTime();
        long delay = endtime - starttime;
        delay = this.speedDelay - delay;
        if (delay > 0L) {
            try {
                Thread.sleep(delay);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.scrollSome();
        this.scrollingNow = false;
    }
    
    public void display() {
        this.currentX = 0;
        this.tickBy = 0;
        for (int headlineTotal = this.headlines.size(), i = 0; i < headlineTotal; ++i) {
            this.nextHeadline = this.headlines.elementAt(i);
            this.nextHeadline.alive = true;
            this.nextHeadline.myPosition = this.bufferSize;
            this.tg.setColor(this.background);
            this.tg.fillRect(0, 0, this.width * 2, this.height * 2);
            this.nextHeadline.draw(this.tg, this.yOffset, this.nextHeadline.myPosition + this.halfwid);
            this.draw();
            try {
                Thread.sleep(this.speedDelay);
            }
            catch (Exception ex) {}
            while (this.paused) {
                try {
                    Thread.sleep(this.speedDelay);
                }
                catch (Exception ex2) {}
            }
            this.nextHeadline.alive = false;
        }
        final int j = this.numHeadlines;
        while (j < this.headlines.size()) {
            this.headlines.elementAt(0).killOff();
            this.headlines.removeElementAt(0);
        }
    }
    
    public boolean tooManyHeadlines() {
        if (this.updateFrom.indexOf("newsstory") >= 0) {
            return this.headlines.size() >= 4 * this.numHeadlines;
        }
        return this.headlines.size() >= 400;
    }
    
    private int getYEndPosition() {
        int lastPosition = 0;
        switch (this.mode) {
            case 3: {
                if (this.headlines.size() < 1) {
                    return this.height;
                }
                lastPosition = this.headlines.elementAt(this.headlines.size() - 1).getYEndPosition();
                if (lastPosition > this.height) {
                    return lastPosition;
                }
                return this.height;
            }
            case 4: {
                if (this.headlines.size() < 1) {
                    return 0;
                }
                lastPosition = this.headlines.elementAt(this.headlines.size() - 1).getBYEndPosition();
                if (lastPosition < 0) {
                    return lastPosition;
                }
                return 0;
            }
            default: {
                return lastPosition;
            }
        }
    }
    
    private int getEndPosition() {
        int lastPosition = 0;
        switch (this.mode) {
            case 1: {
                if (this.headlines.size() < 1) {
                    return this.width;
                }
                lastPosition = this.headlines.elementAt(this.headlines.size() - 1).getEndPosition();
                if (lastPosition > this.width) {
                    return lastPosition;
                }
                return this.width;
            }
            case 2: {
                if (this.headlines.size() < 1) {
                    return 0;
                }
                lastPosition = this.headlines.elementAt(this.headlines.size() - 1).myPosition;
                if (lastPosition < 0) {
                    return lastPosition;
                }
                return 0;
            }
            default: {
                return lastPosition;
            }
        }
    }
    
    public void draw(final Graphics g) {
        if (this.image != null) {
            g.drawImage(this.image, this.x, this.y, this.applet);
        }
    }
    
    private void draw() {
        try {
            this.imageG.setColor(this.background);
            this.imageG.fillRect(0, 0, this.width, this.height);
            int ytmp = this.currentY;
            int xtmp = this.currentX;
            if (this.vScrollMode()) {
                ytmp = this.currentY - this.halfhei;
            }
            else {
                xtmp = this.currentX - this.halfwid;
            }
            this.imageG.drawImage(this.temp, xtmp, ytmp, this.applet);
            this.g.drawImage(this.image, this.x, this.y, this.applet);
        }
        catch (Exception ex) {}
    }
    
    public void parseData(final String newHeadlines, final String newLinks) {
        while (this.copying || this.changingScale) {
            try {
                Thread.sleep(5L);
            }
            catch (Exception ex) {}
        }
        this.updating = true;
        StringTokenizer headlineST = new StringTokenizer(newHeadlines, "|");
        StringTokenizer linkST = new StringTokenizer(newLinks, "|");
        String nextHeadline = "";
        String nextLink = "";
        int endPosition = this.getEndPosition();
        int YendPosition = this.getYEndPosition();
        this.howMany = 0;
        while (headlineST.hasMoreTokens()) {
            nextHeadline = headlineST.nextToken();
            if (linkST.hasMoreTokens()) {
                nextLink = linkST.nextToken();
            }
            else {
                nextLink = "";
            }
            this.nextH = new Headline(nextHeadline, nextLink, this, this.applet);
            switch (this.mode) {
                case 1: {
                    this.nextH.myPosition = this.bufferSize + endPosition;
                    this.nextH.myYpos = this.nextH.ascent + this.yOffset;
                    endPosition = this.nextH.getEndPosition();
                    break;
                }
                case 2: {
                    this.nextH.myPosition = endPosition - this.nextH.myWidth - this.bufferSize;
                    this.nextH.myYpos = this.nextH.ascent + this.yOffset;
                    endPosition = this.nextH.myPosition;
                    break;
                }
                case 3: {
                    this.nextH.myPosition = this.bufferSize + endPosition;
                    this.nextH.myYpos = this.bufferSize + YendPosition;
                    YendPosition = this.nextH.getYEndPosition();
                    break;
                }
                case 4: {
                    this.nextH.myPosition = this.bufferSize + endPosition;
                    this.nextH.myYpos = this.bufferSize + YendPosition - this.nextH.ascent;
                    YendPosition = this.nextH.getBYEndPosition();
                    break;
                }
            }
            this.headlines.addElement(this.nextH);
            ++this.howMany;
        }
        this.numHeadlines = this.howMany;
        this.updating = false;
        headlineST = null;
        linkST = null;
        nextHeadline = null;
        nextLink = null;
    }
    
    public void processMouseClick(int xClick, int yClick) {
        try {
            switch (this.mode) {
                case 1: {
                    xClick -= this.tickBy;
                }
                case 2: {
                    xClick += this.tickBy;
                }
                case 3: {
                    yClick -= this.tickBy;
                }
                case 4: {
                    yClick += this.tickBy;
                    break;
                }
            }
            if (yClick < this.y || yClick > this.y + this.height) {
                return;
            }
            if (xClick < this.x || xClick > this.x + this.width) {
                return;
            }
            for (int i = 0; i < this.headlines.size(); ++i) {
                final Headline nextHeadline = this.headlines.elementAt(i);
                if (nextHeadline.wasClicked(xClick, yClick) && nextHeadline.hasLink()) {
                    final AppletContext context = this.applet.getAppletContext();
                    try {
                        final URL link = new URL(nextHeadline.link);
                        context.showDocument(link, this.linkTarget);
                    }
                    catch (Exception e) {
                        try {
                            final URL link = new URL(this.base, nextHeadline.link);
                            context.showDocument(link, this.linkTarget);
                        }
                        catch (Exception ex) {
                            System.out.println("Could not link: " + e.toString());
                        }
                    }
                    return;
                }
            }
        }
        catch (Exception ex2) {
            if (this.applet == null) {
                this.stop();
            }
        }
    }
    
    public boolean hasLinkWithin(int xClick, int yClick) {
        if (!this.copying && !this.drawing) {
            try {
                switch (this.mode) {
                    case 1: {
                        xClick -= this.tickBy;
                    }
                    case 2: {
                        xClick += this.tickBy;
                    }
                    case 3: {
                        yClick -= this.tickBy;
                    }
                    case 4: {
                        yClick += this.tickBy;
                        break;
                    }
                }
                if (yClick < this.y || yClick > this.y + this.height || xClick < this.x || xClick > this.x + this.width) {
                    if (this.lastClicked != null) {
                        this.highlight(this.lastClicked, false);
                    }
                    this.lastClicked = null;
                    if (this.lastTrue != null) {
                        this.highlight(this.lastTrue, false);
                    }
                    return false;
                }
                if (this.lastClicked != null && this.lastClicked.hover && this.lastClicked.wasClicked(xClick, yClick) && this.lastClicked.hasLink()) {
                    return true;
                }
                if (this.lastClicked != null) {
                    this.highlight(this.lastClicked, false);
                }
                final int stopat = this.headlines.size();
                boolean toreturn = false;
                for (int i = 0; i < stopat; ++i) {
                    final Headline nextHeadline = this.headlines.elementAt(i);
                    if (toreturn) {
                        if (nextHeadline.hover) {
                            this.highlight(nextHeadline, false);
                        }
                    }
                    else if (nextHeadline.hasLink() && nextHeadline.wasClicked(xClick, yClick)) {
                        this.highlight(this.lastClicked = nextHeadline, true);
                        toreturn = true;
                    }
                    else if (nextHeadline.hover) {
                        this.highlight(nextHeadline, false);
                    }
                }
                this.lastClicked = null;
                return toreturn;
            }
            catch (Exception ex) {
                return false;
            }
        }
        return false;
    }
    
    public String getLink() {
        for (int i = 0; i < this.headlines.size(); ++i) {
            final Headline nextHeadline = this.headlines.elementAt(i);
            if (nextHeadline.wasClicked(this.x + 1, this.y + 1)) {
                return nextHeadline.link;
            }
        }
        return "";
    }
}
