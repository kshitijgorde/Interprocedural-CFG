import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jsTextAnimation_Nami extends Applet implements Runnable
{
    Color[] m_Color;
    Color m_BackColor;
    Color m_DefaultColor;
    String m_startMessage;
    double m_dNamiHeight;
    int m_nNamiSpeed;
    jsTA_MsgParser m_sMessage;
    jsTA_ImageReder m_ImageBuffer;
    jsTA_BGAction m_BackGround;
    jsTA_MsgReder m_MsgBuffer;
    int m_nCount;
    int m_nMsgCount;
    Thread m_MainThread;
    boolean m_ThreadSuspended;
    Font m_Font;
    Image m_ScreenA;
    Graphics m_GraphicsA;
    static final String m_gCopyright = "Jin Sato (http://www.magi.com/~jinsato)";
    int[] m_lastLocationX;
    int[] m_lastLocationY;
    int[] m_LicationXMoveDalta;
    int m_Stage;
    int m_nXOffset;
    int m_nCurXOffset;
    int m_nXMove;
    int m_nYOffset;
    int m_nCurYOffset;
    int m_nYMove;
    boolean m_fgMsgList;
    int m_nDisplayTime;
    boolean m_initDone;
    boolean m_debug;
    
    private void Debug(final String s) {
        if (this.m_debug) {
            System.out.println(s);
        }
    }
    
    void checkParamCopyright() {
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            this.m_startMessage = "Jin Sato (http://www.magi.com/~jinsato)";
            return;
        }
        if (!parameter.equals("Jin Sato (http://www.magi.com/~jinsato)")) {
            this.m_startMessage = "Jin Sato (http://www.magi.com/~jinsato)";
        }
    }
    
    void checkParamMessageAndColors() {
        if (this.m_startMessage == null) {
            this.m_startMessage = this.getParameter("Message");
            if (this.m_startMessage == null) {
                this.m_startMessage = "Jin Sato (http://www.magi.com/~jinsato)";
            }
        }
        final String parameter = this.getParameter("DefalutColor");
        if (parameter != null) {
            this.m_DefaultColor = this.parseRGB(parameter);
        }
        else {
            this.m_DefaultColor = this.getForeground();
        }
        this.initMessageAndColor(this.m_startMessage, this.m_DefaultColor, this.getParameter("Colors"));
        final String parameter2 = this.getParameter("BackColor");
        if (parameter2 != null) {
            this.m_BackColor = this.parseRGB(parameter2);
            return;
        }
        this.m_BackColor = this.getBackground();
    }
    
    void checkParamFontAndOther() {
        int intValue = 30;
        String s = "TimesRoman";
        int intValue2 = 0;
        final String parameter = this.getParameter("NamiHeight");
        if (parameter != null) {
            this.m_dNamiHeight = Integer.valueOf(parameter);
        }
        final String parameter2 = this.getParameter("Speed");
        if (parameter2 != null) {
            this.m_nNamiSpeed = Integer.valueOf(parameter2);
        }
        final String parameter3 = this.getParameter("DisplayTime");
        if (parameter3 != null) {
            this.m_nDisplayTime = Integer.valueOf(parameter3);
        }
        final String parameter4 = this.getParameter("FontSize");
        if (parameter4 != null) {
            intValue = Integer.valueOf(parameter4);
            if (intValue <= 0) {
                intValue = 32;
            }
        }
        final String parameter5 = this.getParameter("FontName");
        if (parameter5 != null) {
            s = parameter5;
        }
        final String parameter6 = this.getParameter("FontStyle");
        if (parameter6 != null) {
            intValue2 = Integer.valueOf(parameter6);
        }
        this.m_Font = new Font(s, intValue2, intValue);
    }
    
    public void init() {
        this.checkParamCopyright();
        this.checkParamFontAndOther();
        final Dimension size = this.size();
        this.m_ScreenA = this.createImage(size.width, size.height);
        this.m_GraphicsA = this.m_ScreenA.getGraphics();
        final FontMetrics fontMetrics = this.m_GraphicsA.getFontMetrics(this.m_Font);
        final String parameter = this.getParameter("ImageFiles");
        if (parameter != null) {
            (this.m_ImageBuffer = new jsTA_ImageReder((Applet)this, parameter)).start();
        }
        else {
            this.m_ImageBuffer = new jsTA_ImageReder((Applet)this, null);
        }
        this.m_sMessage = new jsTA_MsgParser(fontMetrics, this.m_ImageBuffer);
        final String parameter2 = this.getParameter("MessageFile");
        if (parameter2 != null) {
            (this.m_MsgBuffer = new jsTA_MsgReder(this.getCodeBase() + parameter2.trim())).start();
        }
        else {
            this.m_MsgBuffer = new jsTA_MsgReder((String)null);
        }
        this.checkParamMessageAndColors();
        (this.m_BackGround = new jsTA_BGAction(this)).checkParam();
        this.m_initDone = true;
    }
    
    public void start() {
        if (this.m_MainThread == null) {
            (this.m_MainThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_MainThread != null && this.m_MainThread.isAlive()) {
            this.m_MainThread.stop();
        }
        this.m_MainThread = null;
    }
    
    public void run() {
        while (this.m_MainThread != null) {
            if (this.m_initDone) {
                try {
                    Thread.sleep(this.m_nNamiSpeed);
                }
                catch (InterruptedException ex) {}
                this.repaint();
            }
        }
        this.m_MainThread = null;
    }
    
    void DrawNamiItem(final Graphics graphics, final int n, final int n2, final int n3) {
        if (this.m_sMessage.isImage(n)) {
            final Image image = this.m_ImageBuffer.getImage(this.m_sMessage.getImageID(n));
            if (image != null) {
                graphics.drawImage(image, n2, n3 - image.getHeight(this), this);
            }
        }
        else {
            graphics.drawString(this.m_sMessage.getItemOneCharString(n), n2, n3);
        }
    }
    
    void DrawNami(final Graphics graphics) {
        this.size();
        if (this.m_sMessage == null) {
            return;
        }
        final double n = 360.0 / this.m_sMessage.getItemNum();
        switch (this.m_MsgBuffer.getBeginigActionrList(this.m_nMsgCount)) {
            case 0: {
                this.m_nCurYOffset = this.m_nYOffset;
                if (this.m_nCurXOffset > this.m_nXOffset) {
                    this.m_nCurXOffset -= this.m_nXMove;
                    break;
                }
                this.m_nCurXOffset = this.m_nXOffset;
                break;
            }
            case 1: {
                this.m_nCurYOffset = this.m_nYOffset;
                if (this.m_nCurXOffset < this.m_nXOffset) {
                    this.m_nCurXOffset += this.m_nXMove;
                    break;
                }
                this.m_nCurXOffset = this.m_nXOffset;
                break;
            }
            case 2: {
                this.m_nCurXOffset = this.m_nXOffset;
                if (this.m_nCurYOffset < this.m_nYOffset) {
                    this.m_nCurYOffset += this.m_nYMove;
                    break;
                }
                this.m_nCurYOffset = this.m_nYOffset;
                break;
            }
            case 4: {
                this.m_nCurXOffset = this.m_nXOffset;
                if (this.m_nCurYOffset > this.m_nYOffset) {
                    this.m_nCurYOffset -= this.m_nYMove;
                    break;
                }
                this.m_nCurYOffset = this.m_nYOffset;
                break;
            }
            default: {
                this.m_nCurXOffset = this.m_nXOffset;
                this.m_nCurYOffset = this.m_nYOffset;
                break;
            }
        }
        int nCurXOffset = this.m_nCurXOffset;
        --this.m_nCount;
        for (int i = 0; i < this.m_sMessage.getItemNum(); ++i) {
            final double n2 = n * (this.m_nCount + i) % 360.0;
            if (i != 0) {
                nCurXOffset += this.m_sMessage.getItemWidth(i - 1);
            }
            final int n3 = this.m_nCurYOffset + (int)(this.m_dNamiHeight * Math.sin(n2 * 3.14 / 180.0));
            if (this.m_Color != null) {
                graphics.setColor(this.m_Color[i]);
            }
            this.DrawNamiItem(graphics, i, this.m_lastLocationX[i] = nCurXOffset, this.m_lastLocationY[i] = n3);
        }
    }
    
    void DrawRun(final Graphics graphics) {
        for (int i = 0; i < this.m_sMessage.getItemNum(); ++i) {
            if (this.m_Color != null) {
                graphics.setColor(this.m_Color[i]);
            }
            this.DrawNamiItem(graphics, i, this.m_lastLocationX[i], this.m_lastLocationY[i]);
        }
    }
    
    int DrawRunBoth(final Graphics graphics) {
        int n = 0;
        final Dimension size = this.size();
        ++this.m_nCount;
        int n2;
        if (this.m_nCount < this.m_sMessage.getItemNum()) {
            n2 = this.m_nCount;
        }
        else {
            n2 = this.m_sMessage.getItemNum();
        }
        for (int i = 0; i < n2 / 2; ++i) {
            if (this.m_lastLocationX[i] < 0) {
                ++n;
            }
            final int[] lastLocationX = this.m_lastLocationX;
            final int n3 = i;
            lastLocationX[n3] -= this.m_LicationXMoveDalta[i];
            final int[] licationXMoveDalta = this.m_LicationXMoveDalta;
            final int n4 = i;
            ++licationXMoveDalta[n4];
            final int n5 = this.m_sMessage.getItemNum() - i - 1;
            if (this.m_lastLocationX[n5] > size.width) {
                ++n;
            }
            final int[] lastLocationX2 = this.m_lastLocationX;
            final int n6 = n5;
            lastLocationX2[n6] += this.m_LicationXMoveDalta[n5];
            final int[] licationXMoveDalta2 = this.m_LicationXMoveDalta;
            final int n7 = n5;
            ++licationXMoveDalta2[n7];
        }
        this.DrawRun(graphics);
        if (n >= this.m_sMessage.getItemNum() - 1) {
            this.m_nCount = 0;
            this.m_Stage = 2;
        }
        return n;
    }
    
    int DrawRunLeft(final Graphics graphics) {
        int n = 0;
        this.size();
        ++this.m_nCount;
        int n2;
        if (this.m_nCount < this.m_sMessage.getItemNum()) {
            n2 = this.m_nCount;
        }
        else {
            n2 = this.m_sMessage.getItemNum();
        }
        for (int i = 0; i < n2; ++i) {
            if (this.m_lastLocationX[i] < 0) {
                ++n;
            }
            final int[] lastLocationX = this.m_lastLocationX;
            final int n3 = i;
            lastLocationX[n3] -= this.m_LicationXMoveDalta[i];
            final int[] licationXMoveDalta = this.m_LicationXMoveDalta;
            final int n4 = i;
            ++licationXMoveDalta[n4];
        }
        this.DrawRun(graphics);
        if (n >= this.m_sMessage.getItemNum() - 1) {
            this.m_nCount = 0;
            this.m_Stage = 2;
        }
        return n;
    }
    
    int DrawRunRight(final Graphics graphics) {
        int n = 0;
        final Dimension size = this.size();
        ++this.m_nCount;
        int n2;
        if (this.m_nCount < this.m_sMessage.getItemNum()) {
            n2 = this.m_nCount;
        }
        else {
            n2 = this.m_sMessage.getItemNum();
        }
        for (int i = 0; i < n2; ++i) {
            final int n3 = this.m_sMessage.getItemNum() - i - 1;
            if (this.m_lastLocationX[n3] > size.width) {
                ++n;
            }
            final int[] lastLocationX = this.m_lastLocationX;
            final int n4 = n3;
            lastLocationX[n4] += this.m_LicationXMoveDalta[n3];
            final int[] licationXMoveDalta = this.m_LicationXMoveDalta;
            final int n5 = n3;
            ++licationXMoveDalta[n5];
        }
        this.DrawRun(graphics);
        if (n >= this.m_sMessage.getItemNum() - 1) {
            this.m_nCount = 0;
            this.m_Stage = 2;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.drawImage(this.m_ScreenA, 0, 0, null);
        this.m_GraphicsA.setColor(this.m_BackColor);
        this.m_GraphicsA.fillRect(0, 0, size.width, size.height);
        this.m_GraphicsA.setFont(this.m_Font);
        this.m_BackGround.action(this.m_GraphicsA);
        switch (this.m_Stage) {
            case 0: {
                this.DrawNami(this.m_GraphicsA);
                if (this.m_MsgBuffer.getNumberOfMessageRead() > 0 && this.m_nCount % this.m_nDisplayTime == 0) {
                    this.initLicationXMoveDalt();
                    this.m_Stage = 1;
                    this.m_nCount = 0;
                    return;
                }
                break;
            }
            case 1: {
                switch (this.m_MsgBuffer.getEndingActionrList(this.m_nMsgCount)) {
                    case 0: {
                        this.DrawRunLeft(this.m_GraphicsA);
                        return;
                    }
                    case 1: {
                        this.DrawRunBoth(this.m_GraphicsA);
                        return;
                    }
                    case 2: {
                        this.DrawRunRight(this.m_GraphicsA);
                        return;
                    }
                    default: {
                        this.m_Stage = 2;
                        return;
                    }
                }
                break;
            }
            case 2: {
                ++this.m_nMsgCount;
                if (this.m_nMsgCount >= this.m_MsgBuffer.getNumberOfMessageRead()) {
                    this.m_nMsgCount = 0;
                }
                this.Debug("m_nMsgCount=" + this.m_nMsgCount);
                this.initMessageAndColor(this.m_MsgBuffer.getMessage(this.m_nMsgCount), this.m_DefaultColor, this.m_MsgBuffer.getColorList(this.m_nMsgCount));
                this.m_Stage = 0;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_ThreadSuspended) {
            this.m_MainThread.resume();
        }
        else {
            this.m_MainThread.suspend();
        }
        this.m_ThreadSuspended = !this.m_ThreadSuspended;
        return true;
    }
    
    Color parseRGB(final String s) {
        return new Color(Integer.parseInt(s.substring(0, 2), 16), Integer.parseInt(s.substring(2, 4), 16), Integer.parseInt(s.substring(4, 6), 16));
    }
    
    void parseColor(final String s, final Color[] array, final int n) {
        int n4;
        for (int n2 = 0, n3 = 0; n3 < s.length() && n2 < n; n3 = n4 + 1, ++n2) {
            n4 = s.indexOf(124, n3);
            if (n4 == -1) {
                n4 = s.length();
            }
            else {
                array[n2] = this.parseRGB(s.substring(n3, n3 + 6));
            }
        }
    }
    
    void initMessageAndColor(final String message, final Color color, final String s) {
        final Dimension size = this.size();
        this.m_sMessage.setMessage(message);
        this.m_lastLocationX = new int[this.m_sMessage.getItemNum()];
        this.m_lastLocationY = new int[this.m_sMessage.getItemNum()];
        this.m_lastLocationX[0] = 0;
        this.m_Color = new Color[this.m_sMessage.getItemNum()];
        for (int i = 0; i < this.m_sMessage.getItemNum(); ++i) {
            this.m_Color[i] = color;
        }
        if (s != null) {
            this.parseColor(s, this.m_Color, this.m_sMessage.getItemNum());
        }
        final int n = (size.width - this.m_sMessage.getWidth()) / 2;
        this.m_nXOffset = n;
        this.m_nCurXOffset = n;
        final int n2 = (this.m_sMessage.getHeight() + size.height) / 2 - this.m_sMessage.getHeight() / 4;
        this.m_nYOffset = n2;
        this.m_nCurYOffset = n2;
        switch (this.m_MsgBuffer.getBeginigActionrList(this.m_nMsgCount)) {
            case 0: {
                this.m_nCurXOffset = ((size.width - this.m_nXOffset) / this.m_nXMove + 1) * this.m_nXMove + this.m_nXOffset;
            }
            case 1: {
                this.m_nCurXOffset = (this.m_nXOffset % this.m_nXMove + this.m_sMessage.getWidth()) * -1;
            }
            case 2: {
                this.m_nYOffset = (this.m_sMessage.getHeight() + size.height) / 2 - this.m_sMessage.getHeight() / 4;
                this.m_nCurYOffset = 0;
            }
            case 4: {
                this.m_nYOffset = (this.m_sMessage.getHeight() + size.height) / 2 - this.m_sMessage.getHeight() / 4;
                this.m_nCurYOffset = (int)(this.m_sMessage.getHeight() * 1.5);
            }
            default: {}
        }
    }
    
    void initLicationXMoveDalt() {
        this.m_LicationXMoveDalta = new int[this.m_sMessage.getItemNum()];
        for (int i = 0; i < this.m_sMessage.getItemNum(); ++i) {
            this.m_LicationXMoveDalta[i] = 1;
        }
    }
    
    void initColor(final int n, final Color color) {
        this.m_Color = new Color[n];
        for (int i = 0; i < n; ++i) {
            this.m_Color[i] = color;
        }
    }
    
    public jsTextAnimation_Nami() {
        this.m_dNamiHeight = 10.0;
        this.m_nNamiSpeed = 100;
        this.m_ThreadSuspended = false;
        this.m_nXMove = 10;
        this.m_nYMove = 2;
        this.m_fgMsgList = false;
        this.m_nDisplayTime = 400;
        this.m_initDone = false;
        this.m_debug = false;
    }
}
