import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextRotatorApplet extends Applet implements Runnable
{
    private static final char ACTION_DELIMITER = '~';
    VecPanel m_vecPanel;
    Thread m_threadRun;
    int m_iDelay;
    int m_iDefaultOriginX;
    int m_iDefaultOriginY;
    int m_iDefaultOriginZ;
    int m_iOriginX;
    int m_iOriginY;
    int m_iOriginZ;
    int m_iDeltaOriginX;
    int m_iDeltaOriginY;
    int m_iDeltaOriginZ;
    float m_flRotationX;
    float m_flRotationY;
    float m_flRotationZ;
    float m_flDeltaRotationX;
    float m_flDeltaRotationY;
    float m_flDeltaRotationZ;
    float m_flWriteRate;
    float m_flTextLen;
    int m_iCycles;
    int m_iTTYMode;
    boolean m_bBorder;
    String m_strText;
    int m_iTextPos;
    boolean m_bInCommand;
    private final String textParam = "text";
    private final String backgroundParam = "background";
    private final String foregroundParam = "foreground";
    private final String borderParam = "border";
    private final String originxParam = "originx";
    private final String originyParam = "originy";
    private final String originzParam = "originz";
    private final String charheightParam = "charheight";
    private final String charwidthParam = "charwidth";
    
    public void stop() {
        this.m_threadRun.stop();
    }
    
    public TextRotatorApplet() {
        this.m_iDelay = 50;
        this.m_iDefaultOriginX = 0;
        this.m_iDefaultOriginY = 0;
        this.m_iDefaultOriginZ = 5000;
        this.m_iOriginX = this.m_iDefaultOriginX;
        this.m_iOriginY = this.m_iDefaultOriginY;
        this.m_iOriginZ = this.m_iDefaultOriginZ;
        this.m_iDeltaOriginX = 0;
        this.m_iDeltaOriginY = 0;
        this.m_iDeltaOriginZ = 0;
        this.m_flRotationX = 0.0f;
        this.m_flRotationY = 0.0f;
        this.m_flRotationZ = 0.0f;
        this.m_flDeltaRotationX = 0.0f;
        this.m_flDeltaRotationY = 0.0f;
        this.m_flDeltaRotationZ = 0.0f;
        this.m_flWriteRate = 1.0f;
        this.m_flTextLen = 0.0f;
        this.m_iCycles = 1000;
        this.m_iTTYMode = 0;
        this.m_bBorder = false;
        this.m_bInCommand = false;
    }
    
    private int getIntActionParam() {
        String strValue = new String();
        while (this.m_iTextPos < this.m_strText.length() && (Character.isDigit(this.m_strText.charAt(this.m_iTextPos)) || this.m_strText.charAt(this.m_iTextPos) == '-')) {
            strValue += this.m_strText.charAt(this.m_iTextPos);
            ++this.m_iTextPos;
        }
        final Integer intValue = new Integer(strValue);
        return intValue;
    }
    
    public String[][] getParameterInfo() {
        final String[][] info = { { "text", "String", "Text to be displayed" }, { "background", "String", "Background color, format \"rrggbb\"" }, { "foreground", "String", "Foreground color, format \"rrggbb\"" } };
        return info;
    }
    
    void initForm() {
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setLayout(new BorderLayout());
        (this.m_vecPanel = new VecPanel()).setBackground(Color.white);
        this.m_vecPanel.setForeground(new Color(160, 160, 255));
        this.m_strText = "TEXT ROTATOR|BY JASON HOTCHKISS";
        this.m_vecPanel.setShowBorder(false);
        this.add("Center", this.m_vecPanel);
    }
    
    private void resetRotation() {
        this.m_flRotationX = 0.0f;
        this.m_flRotationY = 0.0f;
        this.m_flRotationZ = 0.0f;
        this.m_flDeltaRotationX = 0.0f;
        this.m_flDeltaRotationY = 0.0f;
        this.m_flDeltaRotationZ = 0.0f;
    }
    
    private void setupFX(final int iFx) {
        switch (iFx) {
            case 1:
            case 2:
            case 3: {
                this.m_flRotationX = 0.0f;
                this.m_flRotationY = 0.0f;
                this.m_flRotationZ = 0.0f;
                if (iFx == 1) {
                    this.m_flDeltaRotationY = 0.2f;
                }
                if (iFx == 2) {
                    this.m_flDeltaRotationX = 0.2f;
                }
                if (iFx == 3) {
                    this.m_flDeltaRotationZ = 0.2f;
                }
                this.m_iDelay = 20;
                this.m_iCycles = 100;
                this.m_iDeltaOriginX = 0;
                this.m_iDeltaOriginY = 0;
                this.m_iDeltaOriginZ = 200;
                break;
            }
            case 4:
            case 5:
            case 6: {
                this.m_flRotationX = 0.0f;
                this.m_flRotationY = 0.0f;
                this.m_flRotationZ = 0.0f;
                if (iFx == 4) {
                    this.m_flDeltaRotationY = -0.2f;
                }
                if (iFx == 5) {
                    this.m_flDeltaRotationX = -0.2f;
                }
                if (iFx == 6) {
                    this.m_flDeltaRotationZ = -0.2f;
                }
                this.m_iDelay = 20;
                this.m_iCycles = 100;
                this.m_iDeltaOriginX = 0;
                this.m_iDeltaOriginY = 0;
                this.m_iDeltaOriginZ = -200;
                break;
            }
            case 7: {
                this.m_flRotationX = -1.3707963f;
                this.m_flRotationY = 0.0f;
                this.m_flRotationZ = 0.0f;
                this.m_flDeltaRotationY = 0.0f;
                this.m_flDeltaRotationX = 0.0f;
                this.m_flDeltaRotationZ = 0.0f;
                this.m_iDelay = 20;
                this.m_iCycles = 200;
                this.m_iOriginX = 0;
                this.m_iOriginY = 50;
                this.m_iOriginZ = 500;
                this.m_iDeltaOriginX = 0;
                this.m_iDeltaOriginY = 0;
                this.m_iDeltaOriginZ = 20;
                break;
            }
        }
    }
    
    private float getFloatActionParam() {
        String strValue = new String();
        while (this.m_iTextPos < this.m_strText.length() && (Character.isDigit(this.m_strText.charAt(this.m_iTextPos)) || this.m_strText.charAt(this.m_iTextPos) == '-' || this.m_strText.charAt(this.m_iTextPos) == '.')) {
            strValue += this.m_strText.charAt(this.m_iTextPos);
            ++this.m_iTextPos;
        }
        final Double dblValue = new Double(strValue);
        return (float)(Object)dblValue;
    }
    
    private void usePageParams() {
        final String textValue = this.getParameter("text");
        final String backgroundValue = this.getParameter("background");
        final String foregroundValue = this.getParameter("foreground");
        final String borderValue = this.getParameter("border");
        final String originxValue = this.getParameter("originx");
        final String originyValue = this.getParameter("originy");
        final String originzValue = this.getParameter("originz");
        final String charheightValue = this.getParameter("charheight");
        final String charwidthValue = this.getParameter("charwidth");
        if (textValue != null) {
            this.setText(textValue);
        }
        if (backgroundValue != null) {
            this.m_vecPanel.setBackground(this.stringToColor(backgroundValue));
        }
        if (foregroundValue != null) {
            this.m_vecPanel.setForeground(this.stringToColor(foregroundValue));
        }
        if (borderValue != null) {
            this.m_vecPanel.setBorderColour(this.stringToColor(borderValue));
        }
        if (originxValue != null) {
            this.m_iDefaultOriginX = Integer.parseInt(this.getParameter("originx"));
        }
        if (originyValue != null) {
            this.m_iDefaultOriginY = Integer.parseInt(this.getParameter("originy"));
        }
        if (originzValue != null) {
            this.m_iDefaultOriginZ = Integer.parseInt(this.getParameter("originz"));
        }
        if (charheightValue != null) {
            this.m_vecPanel.setScaleX(Integer.parseInt(this.getParameter("charwidth")));
        }
        if (charwidthValue != null) {
            this.m_vecPanel.setScaleY(Integer.parseInt(this.getParameter("charheight")));
        }
    }
    
    public void start() {
        this.m_threadRun.start();
    }
    
    private void drawProc() {
        final int iTextLen = this.m_vecPanel.getTextLen();
        if (this.m_iTTYMode == 0) {
            this.m_flTextLen = iTextLen;
        }
        for (int iCount = 0; iCount < this.m_iCycles; ++iCount) {
            try {
                Thread.sleep(this.m_iDelay);
            }
            catch (Exception ex) {}
            this.m_vecPanel.transformText(this.m_flRotationX, this.m_flRotationY, this.m_flRotationZ, this.m_iOriginX, this.m_iOriginY, this.m_iOriginZ, (int)this.m_flTextLen);
            this.m_flRotationX += this.m_flDeltaRotationX;
            this.m_flRotationY += this.m_flDeltaRotationY;
            this.m_flRotationZ += this.m_flDeltaRotationZ;
            this.m_iOriginX += this.m_iDeltaOriginX;
            this.m_iOriginY += this.m_iDeltaOriginY;
            this.m_iOriginZ += this.m_iDeltaOriginZ;
            this.m_flTextLen += this.m_flWriteRate;
            if (this.m_iTTYMode == 2 && this.m_flTextLen > iTextLen) {
                break;
            }
        }
        this.m_iTTYMode = 0;
    }
    
    private void resetPosition() {
        this.m_iOriginX = this.m_iDefaultOriginX;
        this.m_iOriginY = this.m_iDefaultOriginY;
        this.m_iOriginZ = this.m_iDefaultOriginZ;
        this.m_iDeltaOriginX = 0;
        this.m_iDeltaOriginY = 0;
        this.m_iDeltaOriginZ = 0;
    }
    
    private boolean getNextAction() {
        if (this.m_iTextPos >= this.m_strText.length()) {
            return false;
        }
        String strDisplayText = new String();
        while (this.m_iTextPos < this.m_strText.length()) {
            if (this.m_bInCommand) {
                if (this.m_strText.charAt(this.m_iTextPos) == ';') {
                    ++this.m_iTextPos;
                }
                if (this.m_strText.startsWith("ax=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_flRotationX = this.getFloatActionParam();
                }
                else if (this.m_strText.startsWith("ay=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_flRotationY = this.getFloatActionParam();
                }
                else if (this.m_strText.startsWith("az=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_flRotationZ = this.getFloatActionParam();
                }
                else if (this.m_strText.startsWith("x=", this.m_iTextPos)) {
                    this.m_iTextPos += 2;
                    this.m_iOriginX = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("y=", this.m_iTextPos)) {
                    this.m_iTextPos += 2;
                    this.m_iOriginY = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("z=", this.m_iTextPos)) {
                    this.m_iTextPos += 2;
                    this.m_iOriginZ = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("dx=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_iDeltaOriginX = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("dy=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_iDeltaOriginY = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("dz=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_iDeltaOriginZ = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("dax=", this.m_iTextPos)) {
                    this.m_iTextPos += 4;
                    this.m_flDeltaRotationX = this.getFloatActionParam();
                }
                else if (this.m_strText.startsWith("day=", this.m_iTextPos)) {
                    this.m_iTextPos += 4;
                    this.m_flDeltaRotationY = this.getFloatActionParam();
                }
                else if (this.m_strText.startsWith("daz=", this.m_iTextPos)) {
                    this.m_iTextPos += 4;
                    this.m_flDeltaRotationZ = this.getFloatActionParam();
                }
                else if (this.m_strText.startsWith("wr=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_flWriteRate = this.getIntActionParam() / 100;
                }
                else if (this.m_strText.startsWith("delay=", this.m_iTextPos)) {
                    this.m_iTextPos += 6;
                    this.m_iDelay = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("tty", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    this.m_iTTYMode = 1;
                    this.m_flTextLen = 0.0f;
                }
                else if (this.m_strText.startsWith("wtty", this.m_iTextPos)) {
                    this.m_iTextPos += 4;
                    this.m_iTTYMode = 2;
                    this.m_flTextLen = 0.0f;
                }
                else if (this.m_strText.startsWith("origin", this.m_iTextPos)) {
                    this.m_iTextPos += 6;
                    this.resetPosition();
                }
                else if (this.m_strText.startsWith("orient", this.m_iTextPos)) {
                    this.m_iTextPos += 6;
                    this.resetRotation();
                }
                else if (this.m_strText.startsWith("reset", this.m_iTextPos)) {
                    this.m_iTextPos += 5;
                    this.resetPosition();
                    this.resetRotation();
                }
                else if (this.m_strText.startsWith("cycles=", this.m_iTextPos)) {
                    this.m_iTextPos += 7;
                    this.m_iCycles = this.getIntActionParam();
                }
                else if (this.m_strText.startsWith("fx=", this.m_iTextPos)) {
                    this.m_iTextPos += 3;
                    final int iFx = this.getIntActionParam();
                    this.setupFX(iFx);
                }
                else if (this.m_strText.startsWith("border=", this.m_iTextPos)) {
                    this.m_iTextPos += 7;
                    final int iBorder = this.getIntActionParam();
                    if (iBorder > 0) {
                        this.m_bBorder = true;
                    }
                    else {
                        this.m_bBorder = false;
                    }
                }
                else if (this.m_strText.startsWith("pause", this.m_iTextPos)) {
                    this.m_iTextPos += 5;
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex) {}
                }
                else {
                    if (this.m_strText.startsWith("go", this.m_iTextPos)) {
                        this.m_iTextPos += 2;
                        if (strDisplayText.length() > 0) {
                            this.setDisplayText(strDisplayText);
                        }
                        return true;
                    }
                    if (this.m_strText.startsWith("repeat", this.m_iTextPos)) {
                        this.resetPosition();
                        this.resetRotation();
                        this.m_bBorder = false;
                        this.m_bInCommand = false;
                        this.m_iTextPos = 0;
                    }
                    else {
                        if (this.m_strText.charAt(this.m_iTextPos) == '~') {
                            this.m_bInCommand = false;
                            ++this.m_iTextPos;
                            break;
                        }
                        this.m_vecPanel.setMessage("Syntax Error: " + this.m_strText.substring(this.m_iTextPos));
                        return false;
                    }
                }
            }
            else if (this.m_strText.charAt(this.m_iTextPos) == '~') {
                this.m_bInCommand = true;
                ++this.m_iTextPos;
            }
            else {
                strDisplayText += this.m_strText.charAt(this.m_iTextPos);
                ++this.m_iTextPos;
            }
        }
        this.setDisplayText(strDisplayText);
        return true;
    }
    
    public void run() {
        this.resetPosition();
        this.resetRotation();
        while (this.getNextAction()) {
            this.m_vecPanel.setShowBorder(this.m_bBorder);
            if (this.m_vecPanel.getTextLen() > 0) {
                this.drawProc();
            }
        }
    }
    
    public void init() {
        this.initForm();
        this.usePageParams();
        this.m_threadRun = new Thread(this);
    }
    
    private Color stringToColor(final String paramValue) {
        final int red = Integer.decode("0x" + paramValue.substring(0, 2));
        final int green = Integer.decode("0x" + paramValue.substring(2, 4));
        final int blue = Integer.decode("0x" + paramValue.substring(4, 6));
        return new Color(red, green, blue);
    }
    
    public void setText(final String strText) {
        this.m_strText = strText;
        this.m_iTextPos = 0;
    }
    
    public void setDisplayText(final String strDisplayText) {
        this.m_vecPanel.setText(strDisplayText);
        this.m_flTextLen = 0.0f;
    }
}
