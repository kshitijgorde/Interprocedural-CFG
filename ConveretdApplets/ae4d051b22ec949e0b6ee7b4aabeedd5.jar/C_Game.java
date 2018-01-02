import java.awt.event.MouseEvent;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.io.PrintWriter;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Container;
import java.awt.Component;
import mika.graphics.G_ImageTools;
import java.net.URL;
import mika.application.Application;
import mika.system.S_TextReader;
import mika.graphics.G_Button;
import mika.util.U_Form;
import java.awt.Image;
import mika.graphics.G_Animation;
import java.awt.Color;
import mika.graphics.G_Font;
import mika.graphics.G_TextScreen;
import mika.system.S_Timer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import mika.application.GameModeBase;

// 
// Decompiled by Procyon v0.5.30
// 

public class C_Game extends GameModeBase implements KeyListener, MouseListener, MouseMotionListener
{
    int m_iX;
    int m_iY;
    int m_iWidth;
    int m_iHeight;
    final int GAME_OVER_PAUSE = 3000;
    int m_iGameOverPause;
    boolean m_bInitDone;
    float m_fDelta;
    S_Timer m_timer;
    boolean m_bGameOver;
    boolean m_bShowGameOver;
    boolean m_bIsNetworked;
    String m_strCgiURL;
    String m_strCgiDataPath;
    static final int BACKBUFFER_CNT = 1;
    int m_iHintWindowX;
    int m_iHintWindowY;
    int m_iHintWindowW;
    int m_iHintWindowH;
    C_TextImage m_tiHint;
    int m_iHelpWindowX;
    int m_iHelpWindowY;
    int m_iHelpWindowW;
    int m_iHelpWindowH;
    C_TextImage m_tiHelp;
    int m_iHelpTextCnt;
    G_TextScreen[] m_tsHelpText;
    G_Font m_fntCrossword;
    Color m_clrCrosswordFont;
    int m_iEndScreenX;
    int m_iEndScreenY;
    int m_iEndScreenW;
    int m_iEndScreenH;
    G_TextScreen m_tsEndScreen;
    Color m_clrEndScreen;
    int m_iCrosswordX;
    int m_iCrosswordY;
    int m_iCrosswordW;
    int m_iCrosswordH;
    int m_iSquareW;
    int m_iSquareH;
    final int SQUARE_NORMAL = 0;
    final int SQUARE_HIGHLIGHTED = 1;
    final int SQUARE_SELECTED = 2;
    G_Animation m_anmSquares;
    int[] m_iSquareReference;
    char[] m_cSquareCharacter;
    boolean m_bSquareHighlighted;
    int m_iHighlightedSquareX;
    int m_iHighlightedSquareY;
    final int SELECTION_UP = 0;
    final int SELECTION_DOWN = 1;
    final int SELECTION_LEFT = 2;
    final int SELECTION_RIGHT = 3;
    int m_iFirstSelectedSquareX;
    int m_iFirstSelectedSquareY;
    int m_iLastSelectedSquareX;
    int m_iLastSelectedSquareY;
    int m_iSelectionDirection;
    int m_iSelectedSquareCnt;
    final int STATE_IDLE = 0;
    final int STATE_WRITE = 1;
    final int STATE_MARK_LINE = 2;
    final int STATE_DONE = 3;
    final int STATE_SEND = 4;
    final int STATE_ERROR_IN_CROSSWORD = 5;
    final int STATE_END_SCREEN = 6;
    final int STATE_FILL_INFO = 7;
    int m_iState;
    boolean m_bMouseDown;
    boolean m_bWasMouseDown;
    int m_iMouseDownX;
    int m_iMouseDownY;
    int m_iMouseUpX;
    int m_iMouseUpY;
    int m_iMouseCurrentX;
    int m_iMouseCurrentY;
    int m_iReferenceCnt;
    Image[] m_imgReference;
    G_TextScreen[] m_tsReference;
    int m_iLastReference;
    U_Form m_form;
    G_Button m_btnSend;
    final String BTN_SEND = "SEND";
    int m_iSendX;
    int m_iSendY;
    int m_iSendW;
    int m_iSendH;
    G_Button m_btnTryAgain;
    final String BTN_TRY_AGAIN = "TRYAGAIN";
    int m_iTryAgainX;
    int m_iTryAgainY;
    int m_iTryAgainW;
    int m_iTryAgainH;
    G_Button m_btnDone;
    final String BTN_DONE = "DONE";
    int m_iDoneX;
    int m_iDoneY;
    int m_iDoneW;
    int m_iDoneH;
    boolean m_bSent;
    boolean m_bDone;
    static String sm_strParameterUser;
    String m_strUser;
    private Image m_tryAgainImage;
    private Image m_endScreenImage;
    private Image m_fillInfoImage;
    int m_iNextWrittenCharacterX;
    int m_iNextWrittenCharacterY;
    int m_iWrittenCharacterCnt;
    final int CHAR_BUFFER_SIZE = 128;
    char[] m_characterBuffer;
    int m_iNextCharacterOutput;
    int m_iNextCharacterInput;
    private String m_strOutputData;
    
    public C_Game() {
        this.m_iX = 0;
        this.m_iY = 0;
        this.m_iWidth = 0;
        this.m_iHeight = 0;
        this.m_iGameOverPause = 0;
        this.m_bInitDone = false;
        this.m_fDelta = 0.0f;
        this.m_timer = new S_Timer();
        this.m_bGameOver = false;
        this.m_bShowGameOver = false;
        this.m_bIsNetworked = false;
        this.m_fntCrossword = new G_Font();
        this.m_iEndScreenW = 400;
        this.m_iEndScreenH = 400;
        this.m_iState = 0;
        this.m_bSent = false;
        this.m_bDone = false;
        this.m_strUser = "";
        this.m_characterBuffer = new char[128];
        this.m_tiHint = new C_TextImage();
        this.m_tiHelp = new C_TextImage();
    }
    
    public int getWidth() {
        return this.m_iWidth;
    }
    
    public int getHeight() {
        return this.m_iHeight;
    }
    
    public boolean isInitDone() {
        return this.m_bInitDone;
    }
    
    public void load(final S_TextReader s_TextReader) {
        try {
            this.addKeyListener(this);
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            System.out.println("Game loader:");
            this.m_iX = s_TextReader.readIntegerValue();
            this.m_iY = s_TextReader.readIntegerValue();
            System.out.println("Game position: " + this.m_iX + "x" + this.m_iY);
            this.m_iWidth = s_TextReader.readIntegerValue();
            this.m_iHeight = s_TextReader.readIntegerValue();
            System.out.println("Size: " + this.m_iWidth + "x" + this.m_iHeight);
            this.m_bIsNetworked = Boolean.valueOf(s_TextReader.readStringValue());
            System.out.println("Is networked: " + this.m_bIsNetworked);
            this.m_strCgiURL = s_TextReader.readStringValue();
            this.m_strCgiDataPath = s_TextReader.readStringValue();
            if (this.m_bIsNetworked) {
                System.out.println("CGI: " + this.m_strCgiURL);
                System.out.println("Score path: " + this.m_strCgiDataPath);
            }
            this.m_iHintWindowX = s_TextReader.readIntegerValue();
            this.m_iHintWindowY = s_TextReader.readIntegerValue();
            this.m_iHintWindowW = s_TextReader.readIntegerValue();
            this.m_iHintWindowH = s_TextReader.readIntegerValue();
            final String stringValue = s_TextReader.readStringValue();
            this.m_tiHint.setBackgroundImage(G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue), stringValue));
            this.m_tiHint.setSize(this.m_iHintWindowW, this.m_iHintWindowH);
            this.m_iHelpWindowX = s_TextReader.readIntegerValue();
            this.m_iHelpWindowY = s_TextReader.readIntegerValue();
            this.m_iHelpWindowW = s_TextReader.readIntegerValue();
            this.m_iHelpWindowH = s_TextReader.readIntegerValue();
            final String stringValue2 = s_TextReader.readStringValue();
            this.m_tiHelp.setBackgroundImage(G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue2), stringValue2));
            this.m_tiHelp.setSize(this.m_iHelpWindowW, this.m_iHelpWindowH);
            this.m_iHelpTextCnt = s_TextReader.readIntegerValue();
            this.m_tsHelpText = new G_TextScreen[this.m_iHelpTextCnt];
            for (int i = 0; i < this.m_iHelpTextCnt; ++i) {
                (this.m_tsHelpText[i] = new G_TextScreen()).readTextScreen(s_TextReader);
            }
            this.m_fntCrossword.load(s_TextReader);
            this.m_clrCrosswordFont = new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            final String stringValue3 = s_TextReader.readStringValue();
            final Image loadImage = G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue3), stringValue3);
            this.m_iSquareW = s_TextReader.readIntegerValue();
            this.m_iSquareH = s_TextReader.readIntegerValue();
            System.out.println("Square image: " + stringValue3 + "; size: " + this.m_iSquareW + "x" + this.m_iSquareH);
            this.m_anmSquares = new G_Animation(Application.getApplet(), loadImage, 0, 0, this.m_iSquareW, this.m_iSquareH, 3, 0);
            this.m_iDoneX = s_TextReader.readIntegerValue();
            this.m_iDoneY = s_TextReader.readIntegerValue();
            final int integerValue = s_TextReader.readIntegerValue();
            final int integerValue2 = s_TextReader.readIntegerValue();
            final int integerValue3 = s_TextReader.readIntegerValue();
            final int integerValue4 = s_TextReader.readIntegerValue();
            this.m_btnDone = new G_Button("DONE", G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue, integerValue2, integerValue3, integerValue4), G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue + integerValue3, integerValue2, integerValue3, integerValue4), G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue + 2 * integerValue3, integerValue2, integerValue3, integerValue4), null);
            this.m_iDoneW = integerValue3;
            this.m_iDoneH = integerValue4;
            this.m_iSendX = s_TextReader.readIntegerValue();
            this.m_iSendY = s_TextReader.readIntegerValue();
            final int integerValue5 = s_TextReader.readIntegerValue();
            final int integerValue6 = s_TextReader.readIntegerValue();
            final int integerValue7 = s_TextReader.readIntegerValue();
            final int integerValue8 = s_TextReader.readIntegerValue();
            this.m_btnSend = new G_Button("SEND", G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue5, integerValue6, integerValue7, integerValue8), G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue5 + integerValue7, integerValue6, integerValue7, integerValue8), G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue5 + 2 * integerValue7, integerValue6, integerValue7, integerValue8), null);
            this.m_iSendW = integerValue7;
            this.m_iSendH = integerValue8;
            this.m_iTryAgainX = s_TextReader.readIntegerValue();
            this.m_iTryAgainY = s_TextReader.readIntegerValue();
            final int integerValue9 = s_TextReader.readIntegerValue();
            final int integerValue10 = s_TextReader.readIntegerValue();
            final int integerValue11 = s_TextReader.readIntegerValue();
            final int integerValue12 = s_TextReader.readIntegerValue();
            this.m_btnTryAgain = new G_Button("TRYAGAIN", G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue9, integerValue10, integerValue11, integerValue12), G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue9 + integerValue11, integerValue10, integerValue11, integerValue12), G_ImageTools.extractImage(Application.getApplet(), loadImage, integerValue9 + 2 * integerValue11, integerValue10, integerValue11, integerValue12), null);
            this.m_iTryAgainW = integerValue11;
            this.m_iTryAgainH = integerValue12;
            final G_TextScreen g_TextScreen = new G_TextScreen();
            g_TextScreen.readFonts(s_TextReader);
            this.m_iReferenceCnt = s_TextReader.readIntegerValue();
            this.m_imgReference = new Image[this.m_iReferenceCnt];
            this.m_tsReference = new G_TextScreen[this.m_iReferenceCnt];
            for (int j = 0; j < this.m_iReferenceCnt; ++j) {
                final String stringValue4 = s_TextReader.readStringValue();
                if (stringValue4.equals("image")) {
                    final String stringValue5 = s_TextReader.readStringValue();
                    this.m_imgReference[j] = G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue5), stringValue5);
                }
                else if (stringValue4.equals("text")) {
                    (this.m_tsReference[j] = new G_TextScreen()).cloneFonts(g_TextScreen);
                    this.m_tsReference[j].readText(s_TextReader);
                }
                else {
                    System.out.println("Illegal reference type(" + stringValue4 + ")");
                }
            }
            this.m_iCrosswordX = s_TextReader.readIntegerValue();
            this.m_iCrosswordY = s_TextReader.readIntegerValue();
            this.m_iCrosswordW = s_TextReader.readIntegerValue();
            this.m_iCrosswordH = s_TextReader.readIntegerValue();
            System.out.println("Crossword position: " + this.m_iCrosswordX + ", " + this.m_iCrosswordY + "; size: " + this.m_iCrosswordW + "x" + this.m_iCrosswordH);
            this.m_iSquareReference = new int[this.m_iCrosswordW * this.m_iCrosswordH];
            for (int k = 0; k < this.m_iCrosswordH; ++k) {
                for (int l = 0; l < this.m_iCrosswordW; ++l) {
                    this.m_iSquareReference[k * this.m_iCrosswordW + l] = s_TextReader.readIntegerValue();
                }
            }
            this.m_cSquareCharacter = new char[this.m_iCrosswordW * this.m_iCrosswordH];
            (this.m_form = new U_Form(this)).readFont(s_TextReader);
            this.m_form.addNameField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_form.addStreetField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_form.addAreaCodeField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_form.addCityField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_form.addPhoneField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_form.addEmailField(s_TextReader.readLine(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            final String stringValue6 = s_TextReader.readStringValue();
            System.out.println(stringValue6);
            this.m_tryAgainImage = G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue6), stringValue6);
            final String stringValue7 = s_TextReader.readStringValue();
            System.out.println(stringValue7);
            this.m_fillInfoImage = G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue7), stringValue7);
            final String stringValue8 = s_TextReader.readStringValue();
            this.m_endScreenImage = G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue8), stringValue8);
            System.out.println(stringValue8 + ";  " + this.m_endScreenImage.getWidth(this) + "x" + this.m_endScreenImage.getHeight(this));
            this.m_clrEndScreen = new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_iEndScreenX = s_TextReader.readIntegerValue();
            this.m_iEndScreenY = s_TextReader.readIntegerValue();
            (this.m_tsEndScreen = new G_TextScreen()).readTextScreen(s_TextReader);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void init() {
        try {
            System.out.println("game init");
            System.out.println("Don't add send button");
            this.m_strUser = Application.getApplet().getParameter(C_Game.sm_strParameterUser);
            if (this.m_strUser == null) {
                this.m_strUser = "anonymous";
            }
            this.createOffscreenBuffer(1);
            this.createElements();
            this.reset();
            this.m_bInitDone = true;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void createElements() {
        System.out.println("createElements()");
        try {
            this.setLayout(null);
            this.m_form.hide();
            this.add(this.m_btnDone);
            this.m_btnDone.show();
            this.add(this.m_btnSend);
            this.m_btnSend.hide();
            this.add(this.m_btnTryAgain);
            this.m_btnTryAgain.hide();
            this.add(this.m_tiHint);
            this.m_tiHint.show();
            this.add(this.m_tiHelp);
            this.m_tiHelp.show();
            this.repositionElements();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void repositionElements() {
        System.out.println("repositionElements()");
        try {
            this.reshape(this.m_iX, this.m_iY, this.m_iWidth, this.m_iHeight);
            this.m_form.repositionElements();
            this.m_btnDone.reshape(this.m_iDoneX, this.m_iDoneY, this.m_iDoneW, this.m_iDoneH);
            this.m_btnSend.reshape(this.m_iSendX, this.m_iSendY, this.m_iSendW, this.m_iSendH);
            this.m_btnTryAgain.reshape(this.m_iTryAgainX, this.m_iTryAgainY, this.m_iTryAgainW, this.m_iTryAgainH);
            this.m_tiHelp.reshape(this.m_iHelpWindowX, this.m_iHelpWindowY, this.m_iHelpWindowW, this.m_iHelpWindowH);
            this.m_tiHint.reshape(this.m_iHintWindowX, this.m_iHintWindowY, this.m_iHintWindowW, this.m_iHintWindowH);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void setState(final int iState) {
        this.m_iState = iState;
    }
    
    public void setupTimer() {
        this.m_timer.reset();
    }
    
    public void calculateDelta() {
        this.m_fDelta = this.m_timer.sample();
        if (this.m_fDelta > 1.0f) {
            this.m_fDelta = 1.0f;
        }
    }
    
    public void reset() {
        System.out.println("C_Game::reset()");
        try {
            this.m_bGameOver = false;
            this.m_bShowGameOver = false;
            this.setState(0);
            this.m_bSquareHighlighted = false;
            this.m_iHighlightedSquareX = 0;
            this.m_iHighlightedSquareY = 0;
            this.m_iFirstSelectedSquareX = 0;
            this.m_iFirstSelectedSquareY = 0;
            this.m_iLastSelectedSquareX = -1;
            this.m_iLastSelectedSquareY = -1;
            this.m_iSelectionDirection = 1;
            this.m_iSelectedSquareCnt = 0;
            this.m_iNextCharacterOutput = 0;
            this.m_iNextCharacterInput = 0;
            this.m_bMouseDown = false;
            this.m_bWasMouseDown = false;
            this.m_tiHelp.setText(this.m_tsHelpText[0]);
            this.m_tiHint.setText(null);
            this.m_tiHint.setPicture(null);
            this.m_iLastReference = -1;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mainloopMarkLine() {
        try {
            if (this.m_bWasMouseDown || this.m_bMouseDown) {
                this.m_bWasMouseDown = false;
                final int iMouseCurrentX = this.m_iMouseCurrentX;
                final int iMouseCurrentY = this.m_iMouseCurrentY;
                if (this.isValidPath(this.m_iFirstSelectedSquareX, this.m_iFirstSelectedSquareY, iMouseCurrentX, iMouseCurrentY)) {
                    this.updateSelectedSquares(iMouseCurrentX, iMouseCurrentY);
                }
                else {
                    this.updateSelectedSquares(this.m_iFirstSelectedSquareX, this.m_iFirstSelectedSquareY);
                }
            }
            else {
                this.repaintCrossword();
                if (this.m_iFirstSelectedSquareX > this.m_iLastSelectedSquareX) {
                    final int iFirstSelectedSquareX = this.m_iFirstSelectedSquareX;
                    this.m_iFirstSelectedSquareX = this.m_iLastSelectedSquareX;
                    this.m_iLastSelectedSquareX = iFirstSelectedSquareX;
                    this.m_iSelectionDirection = 3;
                }
                if (this.m_iFirstSelectedSquareY > this.m_iLastSelectedSquareY) {
                    final int iFirstSelectedSquareY = this.m_iFirstSelectedSquareY;
                    this.m_iFirstSelectedSquareY = this.m_iLastSelectedSquareY;
                    this.m_iLastSelectedSquareY = iFirstSelectedSquareY;
                    this.m_iSelectionDirection = 1;
                }
                this.m_iNextWrittenCharacterX = this.m_iFirstSelectedSquareX;
                this.m_iNextWrittenCharacterY = this.m_iFirstSelectedSquareY;
                this.m_iWrittenCharacterCnt = 0;
                this.setState(1);
                this.flushCharacterBuffer();
                this.updateCursor(this.getOffscreenBuffer().getGraphics());
                this.repaint();
            }
            Thread.sleep(0L);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void removeLastWrittenCharacter() {
        try {
            final Graphics graphics = this.getOffscreenBuffer().getGraphics();
            this.drawSelectedSquares(graphics);
            if (this.m_iFirstSelectedSquareX != this.m_iLastSelectedSquareX && this.m_iNextWrittenCharacterX != this.m_iFirstSelectedSquareX) {
                --this.m_iNextWrittenCharacterX;
                this.m_cSquareCharacter[this.m_iNextWrittenCharacterX + this.m_iNextWrittenCharacterY * this.m_iCrosswordW] = '\0';
                --this.m_iWrittenCharacterCnt;
            }
            else if (this.m_iFirstSelectedSquareY != this.m_iLastSelectedSquareY && this.m_iNextWrittenCharacterY != this.m_iFirstSelectedSquareY) {
                --this.m_iNextWrittenCharacterY;
                this.m_cSquareCharacter[this.m_iNextWrittenCharacterX + this.m_iNextWrittenCharacterY * this.m_iCrosswordW] = '\0';
                --this.m_iWrittenCharacterCnt;
            }
            this.drawSelectedSquares(graphics);
            this.repaint();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void checkReferences() {
        try {
            final int iMouseCurrentX = this.m_iMouseCurrentX;
            final int iMouseCurrentY = this.m_iMouseCurrentY;
            if (iMouseCurrentX >= 0 && iMouseCurrentX < this.m_iCrosswordW && iMouseCurrentY >= 0 && iMouseCurrentY < this.m_iCrosswordH) {
                final int n = iMouseCurrentX + iMouseCurrentY * this.m_iCrosswordW;
                if (this.m_iSquareReference[n] != 0 && this.m_iSquareReference[n] != this.m_iLastReference) {
                    final int n2 = this.m_iSquareReference[n] - 1;
                    if (this.m_imgReference[n2] != null) {
                        this.m_tiHint.setPicture(this.m_imgReference[n2]);
                        this.m_tiHint.setText(null);
                    }
                    else {
                        this.m_tiHint.setText(this.m_tsReference[n2]);
                        this.m_tiHint.setPicture(null);
                    }
                    this.m_iLastReference = this.m_iSquareReference[n];
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void addWrittenCharacter(final char c) {
        try {
            final Graphics graphics = this.getOffscreenBuffer().getGraphics();
            this.m_cSquareCharacter[this.m_iNextWrittenCharacterX + this.m_iNextWrittenCharacterY * this.m_iCrosswordW] = c;
            this.drawSelectedSquares(graphics);
            if (this.m_iFirstSelectedSquareX == this.m_iLastSelectedSquareX) {
                ++this.m_iNextWrittenCharacterY;
            }
            else {
                ++this.m_iNextWrittenCharacterX;
            }
            ++this.m_iWrittenCharacterCnt;
            if (this.m_iWrittenCharacterCnt >= this.m_iSelectedSquareCnt) {
                this.clearSelectedSquares(graphics);
                this.setState(0);
            }
            this.repaint();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mainloopWrite() {
        try {
            while (!this.isCharacterBufferEmpty()) {
                final Graphics graphics = this.getOffscreenBuffer().getGraphics();
                final char nextCharacter = this.getNextCharacter();
                if (nextCharacter == '\0') {
                    this.removeLastWrittenCharacter();
                }
                else {
                    this.addWrittenCharacter(nextCharacter);
                }
                this.drawSelectedSquares(graphics);
                if (this.m_iState != 0) {
                    this.updateCursor(graphics);
                }
                this.repaint();
            }
            if (this.m_bMouseDown || this.m_bWasMouseDown) {
                this.m_bWasMouseDown = false;
                if (this.isValidSquare(this.m_iMouseDownX, this.m_iMouseDownY)) {
                    this.setMarkLine(this.m_iMouseDownX, this.m_iMouseDownY);
                }
            }
            Thread.sleep(20L);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public boolean isCharacterBufferEmpty() {
        return this.m_iNextCharacterOutput == this.m_iNextCharacterInput;
    }
    
    public void flushCharacterBuffer() {
        this.m_iNextCharacterOutput = this.m_iNextCharacterInput;
    }
    
    public char getNextCharacter() {
        char c = '\0';
        try {
            c = this.m_characterBuffer[this.m_iNextCharacterOutput];
            this.m_iNextCharacterOutput = (this.m_iNextCharacterOutput + 1) % 128;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return c;
    }
    
    public void addCharacter(final char c) {
        try {
            this.m_characterBuffer[this.m_iNextCharacterInput] = c;
            this.m_iNextCharacterInput = (this.m_iNextCharacterInput + 1) % 128;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateCursor(final Graphics graphics) {
        try {
            graphics.drawImage(this.m_anmSquares.getImage(1), this.m_iCrosswordX + this.m_iNextWrittenCharacterX * this.m_iSquareW, this.m_iCrosswordY + this.m_iNextWrittenCharacterY * this.m_iSquareH, this);
            this.drawCharacter(graphics, this.m_iNextWrittenCharacterX, this.m_iNextWrittenCharacterY);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mainloopIdle() {
        try {
            if (this.m_bMouseDown || this.m_bWasMouseDown) {
                this.m_bWasMouseDown = false;
                if (this.isValidSquare(this.m_iMouseDownX, this.m_iMouseDownY)) {
                    this.setMarkLine(this.m_iMouseDownX, this.m_iMouseDownY);
                }
            }
            Thread.sleep(20L);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void setMarkLine(final int iFirstSelectedSquareX, final int iFirstSelectedSquareY) {
        try {
            final Graphics graphics = this.getOffscreenBuffer().getGraphics();
            this.clearSelectedSquares(graphics);
            this.m_iFirstSelectedSquareX = iFirstSelectedSquareX;
            this.m_iFirstSelectedSquareY = iFirstSelectedSquareY;
            this.m_iSelectedSquareCnt = 1;
            this.drawSelectedSquares(graphics);
            this.repaint();
            this.setState(2);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mainloopDone() {
        try {
            if (!this.m_bDone) {
                this.m_btnDone.hide();
                this.m_form.show();
                this.m_btnSend.show();
                this.m_iState = 7;
                final Graphics graphics = this.getOffscreenBuffer().getGraphics();
                graphics.drawImage(this.m_fillInfoImage, 0, 0, this);
                this.m_form.paint(graphics);
                this.repaint();
            }
            this.m_bDone = true;
            Thread.sleep(20L);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mainloopErrorInCrossword() {
        try {
            Thread.sleep(20L);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void mainloopSend() {
        try {
            if (!this.m_bSent) {
                this.m_form.hide();
                this.m_btnSend.hide();
                boolean sendData = true;
                if (this.m_bIsNetworked) {
                    sendData = this.sendData();
                }
                if (sendData) {
                    this.m_iState = 6;
                    this.getOffscreenBuffer().getGraphics().drawImage(this.m_endScreenImage, 0, 0, this);
                    this.repaint();
                    this.m_bSent = true;
                }
                else {
                    this.m_iState = 5;
                    this.m_btnTryAgain.show();
                    this.getOffscreenBuffer().getGraphics().drawImage(this.m_tryAgainImage, 0, 0, this);
                    this.swapBuffer();
                    this.getOffscreenBuffer().getGraphics().drawImage(this.m_tryAgainImage, 0, 0, this);
                    this.repaint();
                }
            }
            Thread.sleep(20L);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int run() {
        try {
            this.show();
            this.reset();
            this.setupTimer();
            this.paintImage();
            while (!false) {
                this.calculateDelta();
                switch (this.m_iState) {
                    case 1: {
                        this.requestFocus();
                        this.mainloopWrite();
                        break;
                    }
                    case 2: {
                        this.mainloopMarkLine();
                        break;
                    }
                    case 0: {
                        this.mainloopIdle();
                        break;
                    }
                    case 3: {
                        this.mainloopDone();
                        break;
                    }
                    case 4: {
                        this.mainloopSend();
                        break;
                    }
                    case 5:
                    case 6:
                    case 7: {
                        this.mainloopErrorInCrossword();
                        break;
                    }
                    default: {
                        System.out.println("Invalid game state");
                        break;
                    }
                }
                this.checkReferences();
            }
            this.hide();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
    
    private boolean sendData() {
        boolean b = false;
        try {
            this.resetOutputData();
            this.addToOutputData("Path", this.m_strCgiDataPath);
            this.addToOutputData("Name", this.m_form.getName());
            this.addToOutputData("Street", this.m_form.getStreet());
            this.addToOutputData("AreaCode", this.m_form.getAreaCode());
            this.addToOutputData("City", this.m_form.getCity());
            this.addToOutputData("Phone", this.m_form.getPhone());
            this.addToOutputData("Email", this.m_form.getEmail());
            this.addToOutputData("CrosswordW", String.valueOf(this.m_iCrosswordW));
            this.addToOutputData("CrosswordH", String.valueOf(this.m_iCrosswordH));
            for (int i = 0; i < this.m_iCrosswordH; ++i) {
                String s = "";
                for (int j = 0; j < this.m_iCrosswordW; ++j) {
                    if (this.m_cSquareCharacter[i * this.m_iCrosswordW + j] == '\u00c5' || this.m_cSquareCharacter[i * this.m_iCrosswordW + j] == '\u00e5') {
                        s += "A";
                    }
                    else if (this.m_cSquareCharacter[i * this.m_iCrosswordW + j] == '\u00c4' || this.m_cSquareCharacter[i * this.m_iCrosswordW + j] == '\u00e4') {
                        s += "A";
                    }
                    else if (this.m_cSquareCharacter[i * this.m_iCrosswordW + j] == '\u00d6' || this.m_cSquareCharacter[i * this.m_iCrosswordW + j] == '\u00f6') {
                        s += "O";
                    }
                    else if (this.m_cSquareCharacter[i * this.m_iCrosswordW + j] != '\0' && this.m_cSquareCharacter[i * this.m_iCrosswordW + j] != ' ') {
                        s += this.m_cSquareCharacter[i * this.m_iCrosswordW + j];
                    }
                    else {
                        s += "_";
                    }
                }
                this.addToOutputData("CL" + i, s);
            }
            final URL url = new URL(this.m_strCgiURL);
            System.out.println("CGI info:");
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("File: " + url.getFile());
            System.out.println("Port: " + url.getPort());
            System.out.println("URL to string: " + url.toString());
            int port = 80;
            if (url.getPort() != -1) {
                port = url.getPort();
            }
            System.out.println("Create url");
            final URL url2 = new URL(url.getProtocol(), url.getHost(), port, url.getFile());
            System.out.println("Information cgi: " + url.toString());
            final URLConnection openConnection = url2.openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            System.out.println("open output stream");
            openConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            final PrintWriter printWriter = new PrintWriter(openConnection.getOutputStream());
            printWriter.print(this.getOutputData());
            printWriter.close();
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            String line;
            while ((line = dataInputStream.readLine()) != null) {
                if (line.equals("ok")) {
                    b = true;
                }
                System.out.println(line);
            }
            dataInputStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return b;
    }
    
    public String getOutputData() {
        return this.m_strOutputData;
    }
    
    public void resetOutputData() {
        this.m_strOutputData = "";
    }
    
    public void addToOutputData(final String s, final String s2) {
        if (!this.m_strOutputData.equals("")) {
            this.m_strOutputData += "&";
        }
        this.m_strOutputData = this.m_strOutputData + s + "=" + URLEncoder.encode(s2);
    }
    
    public String formatOutput(final String s, final String s2, final boolean b) {
        return s + "=" + URLEncoder.encode(s2) + (b ? "" : "&");
    }
    
    public synchronized void paintImage() {
        try {
            final Graphics graphics = this.getOffscreenBuffer().getGraphics();
            if (this.m_bSent) {
                graphics.drawImage(this.m_endScreenImage, 0, 0, this);
            }
            else if (this.m_iState == 5) {
                graphics.drawImage(this.m_tryAgainImage, 0, 0, this);
            }
            else if (this.m_iState == 7) {
                graphics.drawImage(this.m_fillInfoImage, 0, 0, this);
            }
            else {
                this.repaintCrossword();
            }
            this.swapBuffer();
            this.repaint();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        System.out.println("" + keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        System.out.println("" + keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        System.out.println("" + keyEvent);
        if (Character.isLetter(keyChar)) {
            System.out.println("is letter " + keyChar);
            this.addCharacter(Character.toUpperCase(keyChar));
        }
        else if (keyChar == ' ') {
            this.addCharacter(' ');
        }
        else if (keyChar == '\b') {
            this.addCharacter('\0');
        }
        else {
            System.out.println("not letter " + (int)keyChar);
            switch (keyChar) {
                case 128:
                case 138: {
                    this.addCharacter('\u00c4');
                    break;
                }
                case 129:
                case 140: {
                    this.addCharacter('\u00c5');
                    break;
                }
                case 133:
                case 154: {
                    this.addCharacter('\u00d6');
                    break;
                }
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!super.keyDown(event, n)) {
            if (Character.isLetter((char)n)) {
                System.out.println("is letter " + (char)n);
                this.addCharacter(Character.toUpperCase((char)n));
            }
            else if (n == 32) {
                this.addCharacter(' ');
            }
            else if (n == 8) {
                this.addCharacter('\0');
            }
            else {
                System.out.println("not letter " + n);
                switch (n) {
                    case 128:
                    case 138: {
                        this.addCharacter('\u00c4');
                        break;
                    }
                    case 129:
                    case 140: {
                        this.addCharacter('\u00c5');
                        break;
                    }
                    case 133:
                    case 154: {
                        this.addCharacter('\u00d6');
                        break;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean keyUp(final Event event, final int n) {
        if (!super.keyUp(event, n)) {}
        return false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.m_bMouseDown = true;
        this.m_bWasMouseDown = true;
        final int squareX = this.getSquareX(x);
        this.m_iMouseDownX = squareX;
        this.m_iMouseCurrentX = squareX;
        final int squareY = this.getSquareY(y);
        this.m_iMouseDownY = squareY;
        this.m_iMouseCurrentY = squareY;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.m_bMouseDown) {
            this.m_iMouseCurrentX = this.getSquareX(x);
            this.m_iMouseCurrentY = this.getSquareY(y);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.m_bMouseDown = false;
        final int squareX = this.getSquareX(x);
        this.m_iMouseUpX = squareX;
        this.m_iMouseCurrentX = squareX;
        final int squareY = this.getSquareY(y);
        this.m_iMouseUpY = squareY;
        this.m_iMouseCurrentY = squareY;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.m_iMouseCurrentX = this.getSquareX(x);
        this.m_iMouseCurrentY = this.getSquareY(y);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.m_bMouseDown = true;
        this.m_bWasMouseDown = true;
        final int squareX = this.getSquareX(n);
        this.m_iMouseDownX = squareX;
        this.m_iMouseCurrentX = squareX;
        final int squareY = this.getSquareY(n2);
        this.m_iMouseDownY = squareY;
        this.m_iMouseCurrentY = squareY;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.m_bMouseDown) {
            this.m_iMouseCurrentX = this.getSquareX(n);
            this.m_iMouseCurrentY = this.getSquareY(n2);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.m_bMouseDown = false;
        final int squareX = this.getSquareX(n);
        this.m_iMouseUpX = squareX;
        this.m_iMouseCurrentX = squareX;
        final int squareY = this.getSquareY(n2);
        this.m_iMouseUpY = squareY;
        this.m_iMouseCurrentY = squareY;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.m_iMouseCurrentX = this.getSquareX(n);
        this.m_iMouseCurrentY = this.getSquareY(n2);
        return true;
    }
    
    public int getPathLength(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        if (n == n3) {
            n5 = n4 - n2;
            if (n5 < 0) {
                n5 = -n5;
            }
            ++n5;
        }
        else if (n2 == n4) {
            n5 = n3 - n;
            if (n5 < 0) {
                n5 = -n5;
            }
            ++n5;
        }
        return n5;
    }
    
    public boolean isValidPath(final int n, final int n2, final int n3, final int n4) {
        boolean b = false;
        try {
            if (this.isValidSquare(n, n2) && this.isValidSquare(n3, n4)) {
                if (n == n3) {
                    int n5 = n2;
                    while (this.isValidSquare(n, n5) && n5 != n4) {
                        if (n2 < n4) {
                            ++n5;
                        }
                        else {
                            --n5;
                        }
                    }
                    if (n5 == n4 && this.isValidSquare(n3, n4)) {
                        b = true;
                    }
                }
                else if (n2 == n4) {
                    int n6 = n;
                    while (this.isValidSquare(n6, n2) && n6 != n3) {
                        if (n < n3) {
                            ++n6;
                        }
                        else {
                            --n6;
                        }
                    }
                    if (n6 == n3 && this.isValidSquare(n3, n4)) {
                        b = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return b;
    }
    
    public boolean isValidSquare(final int n, final int n2) {
        boolean b = false;
        if (n >= 0 && n < this.m_iCrosswordW && n2 >= 0 && n2 < this.m_iCrosswordH) {
            b = (this.m_iSquareReference[n + n2 * this.m_iCrosswordW] == 0);
        }
        return b;
    }
    
    public int getSquareX(final int n) {
        int n2 = -1;
        try {
            if (n >= this.m_iCrosswordX && n < this.m_iCrosswordX + this.m_iCrosswordW * this.m_iSquareW) {
                n2 = (n - this.m_iCrosswordX) / this.m_iSquareW;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return n2;
    }
    
    public int getSquareY(final int n) {
        int n2 = -1;
        try {
            if (n >= this.m_iCrosswordY && n < this.m_iCrosswordY + this.m_iCrosswordH * this.m_iSquareH) {
                n2 = (n - this.m_iCrosswordY) / this.m_iSquareH;
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return n2;
    }
    
    public void updateSelectedSquares(final int iLastSelectedSquareX, final int iLastSelectedSquareY) {
        try {
            final Graphics graphics = this.getOffscreenBuffer().getGraphics();
            this.clearSelectedSquares(graphics);
            this.m_iLastSelectedSquareX = iLastSelectedSquareX;
            this.m_iLastSelectedSquareY = iLastSelectedSquareY;
            this.m_iSelectedSquareCnt = this.getPathLength(this.m_iFirstSelectedSquareX, this.m_iFirstSelectedSquareY, iLastSelectedSquareX, iLastSelectedSquareY);
            if (this.m_iFirstSelectedSquareX == iLastSelectedSquareX) {
                if (this.m_iFirstSelectedSquareY < iLastSelectedSquareY) {
                    this.m_iSelectionDirection = 1;
                }
                else {
                    this.m_iSelectionDirection = 0;
                }
            }
            else if (this.m_iFirstSelectedSquareY == iLastSelectedSquareY) {
                if (this.m_iFirstSelectedSquareX < iLastSelectedSquareX) {
                    this.m_iSelectionDirection = 3;
                }
                else {
                    this.m_iSelectionDirection = 2;
                }
            }
            this.drawSelectedSquares(graphics);
            this.repaint();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void drawCharacters(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        try {
            int n5 = this.m_iCrosswordX + n * this.m_iSquareW;
            int n6 = this.m_iCrosswordY + n2 * this.m_iSquareH;
            if (n == n3) {
                int n7 = n4 - n2;
                if (n7 < 0) {
                    n7 = -n7;
                }
                ++n7;
                int n8 = n2;
                for (int i = 0; i < n7; ++i) {
                    if (this.isValidSquare(n, n8)) {
                        graphics.drawImage(this.m_anmSquares.getImage(0), n5, n6, this);
                        this.drawCharacter(graphics, n, n8);
                    }
                    if (n2 < n4) {
                        n6 += this.m_iSquareH;
                        ++n8;
                    }
                    else {
                        n6 -= this.m_iSquareH;
                        --n8;
                    }
                }
            }
            else if (n2 == n4) {
                int n9 = n3 - n;
                if (n9 < 0) {
                    n9 = -n9;
                }
                ++n9;
                int n10 = n;
                for (int j = 0; j < n9; ++j) {
                    if (this.isValidSquare(n10, n2)) {
                        graphics.drawImage(this.m_anmSquares.getImage(0), n5, n6, this);
                        this.drawCharacter(graphics, n10, n2);
                    }
                    if (n < n3) {
                        n5 += this.m_iSquareW;
                        ++n10;
                    }
                    else {
                        n5 -= this.m_iSquareW;
                        --n10;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void clearSelectedSquares(final Graphics graphics) {
        try {
            if (this.m_iLastSelectedSquareX != -1 && this.m_iLastSelectedSquareY != -1) {
                this.drawCharacters(graphics, this.m_iFirstSelectedSquareX, this.m_iFirstSelectedSquareY, this.m_iLastSelectedSquareX, this.m_iLastSelectedSquareY);
            }
            this.m_iLastSelectedSquareX = -1;
            this.m_iLastSelectedSquareY = -1;
            this.m_iSelectedSquareCnt = 0;
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void drawSelectedSquares(final Graphics graphics) {
        try {
            int n = this.m_iCrosswordX + this.m_iFirstSelectedSquareX * this.m_iSquareW;
            int n2 = this.m_iCrosswordY + this.m_iFirstSelectedSquareY * this.m_iSquareH;
            int iFirstSelectedSquareX = this.m_iFirstSelectedSquareX;
            int iFirstSelectedSquareY = this.m_iFirstSelectedSquareY;
            for (int i = 0; i < this.m_iSelectedSquareCnt; ++i) {
                graphics.drawImage(this.m_anmSquares.getImage(2), n, n2, this);
                this.drawCharacter(graphics, iFirstSelectedSquareX, iFirstSelectedSquareY);
                switch (this.m_iSelectionDirection) {
                    case 0: {
                        n2 -= this.m_iSquareH;
                        --iFirstSelectedSquareY;
                        break;
                    }
                    case 1: {
                        n2 += this.m_iSquareH;
                        ++iFirstSelectedSquareY;
                        break;
                    }
                    case 2: {
                        n -= this.m_iSquareW;
                        --iFirstSelectedSquareX;
                        break;
                    }
                    case 3: {
                        n += this.m_iSquareW;
                        ++iFirstSelectedSquareX;
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void drawHighlightedSquare(final Graphics graphics) {
        try {
            if (this.m_bSquareHighlighted) {
                graphics.drawImage(this.m_anmSquares.getImage(1), this.m_iCrosswordX + this.m_iHighlightedSquareX * this.m_iSquareW, this.m_iCrosswordY + this.m_iHighlightedSquareY * this.m_iSquareH, this);
                this.drawCharacter(graphics, this.m_iHighlightedSquareX, this.m_iHighlightedSquareY);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void drawCharacter(final Graphics graphics, final int n, final int n2) {
        try {
            final int n3 = n + n2 * this.m_iCrosswordW;
            if (this.m_cSquareCharacter[n3] != '\0') {
                graphics.setFont(this.m_fntCrossword.getFont());
                graphics.setColor(this.m_clrCrosswordFont);
                graphics.drawString("" + this.m_cSquareCharacter[n3], this.m_iCrosswordX + this.m_iSquareW * n + (this.m_iSquareW - graphics.getFontMetrics().charWidth(this.m_cSquareCharacter[n3])) / 2, this.m_iCrosswordY + this.m_iSquareH * (n2 + 1) - (this.m_iSquareH - graphics.getFontMetrics().getHeight()) / 2 - graphics.getFontMetrics().getDescent());
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void repaintCrossword() {
        try {
            final Graphics graphics = this.getOffscreenBuffer().getGraphics();
            final int iCrosswordX = this.m_iCrosswordX;
            int iCrosswordY = this.m_iCrosswordY;
            graphics.drawImage(this.getMainBackground(), -this.m_iX, -this.m_iY, this);
            int n = 0;
            for (int i = 0; i < this.m_iCrosswordH; ++i, iCrosswordY += this.m_iSquareH) {
                for (int iCrosswordX2 = this.m_iCrosswordX, j = 0; j < this.m_iCrosswordW; ++j, ++n, iCrosswordX2 += this.m_iSquareW) {
                    if (this.m_iSquareReference[n] == 0) {
                        graphics.drawImage(this.m_anmSquares.getImage(0), iCrosswordX2, iCrosswordY, this);
                        this.drawCharacter(graphics, j, i);
                    }
                }
            }
            this.drawSelectedSquares(graphics);
            this.drawHighlightedSquare(graphics);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof G_Button) {
            if ("TRYAGAIN".equals(o)) {
                this.m_btnTryAgain.hide();
                this.m_btnDone.show();
                this.m_bSquareHighlighted = false;
                this.m_iHighlightedSquareX = 0;
                this.m_iHighlightedSquareY = 0;
                this.m_iFirstSelectedSquareX = 0;
                this.m_iFirstSelectedSquareY = 0;
                this.m_iLastSelectedSquareX = -1;
                this.m_iLastSelectedSquareY = -1;
                this.m_iSelectionDirection = 1;
                this.m_iSelectedSquareCnt = 0;
                this.m_iNextCharacterOutput = 0;
                this.m_iNextCharacterInput = 0;
                this.m_bMouseDown = false;
                this.m_bWasMouseDown = false;
                this.m_bSent = false;
                this.m_bDone = false;
                this.repaintCrossword();
                this.setState(0);
                this.repaint();
                return true;
            }
            if ("DONE".equals(o)) {
                this.setState(3);
                return true;
            }
            if ("SEND".equals(o)) {
                this.setState(4);
                return true;
            }
        }
        return super.action(event, o);
    }
    
    static {
        C_Game.sm_strParameterUser = "user";
    }
}
