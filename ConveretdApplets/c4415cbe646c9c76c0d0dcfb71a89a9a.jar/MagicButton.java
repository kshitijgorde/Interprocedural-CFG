import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.awt.Event;
import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.Button;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Label;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MagicButton extends Applet implements Runnable
{
    int ipar;
    String sUR;
    String sREG;
    float fXCur;
    float fYCur;
    float fWCur;
    float fHCur;
    float fAddMov;
    int iMovPath;
    int iDownDescentX;
    int iDownDescentY;
    Graphics grImgBG;
    Image imgBG;
    String sImgPath;
    String sSndPath;
    String[] sFN;
    boolean bClip;
    boolean bPreLoad;
    long AST;
    long gWaitFor;
    int iMSleepTime;
    String sProgramStuck;
    boolean bNewP;
    boolean bPnted;
    boolean bManPnt;
    boolean bDismTip;
    boolean CoolJAVA;
    boolean bAllowHC;
    boolean AniGifPresent;
    boolean doRepaint;
    boolean bDoScroll;
    long gScDelay;
    long gORIGScDelay;
    int iMouseX;
    int iMouseY;
    int iLastScrlBtn;
    int iScStep;
    int iScDirection;
    String smTip;
    Label lblTip;
    Label lblTipFrame;
    int iTipFW;
    Font mTipFont;
    FontMetrics fmTip;
    Color cTipColor;
    Color cTipForeColor;
    Color cTipFrameColor;
    int iOldMouseX;
    int iOldMouseY;
    boolean bShowingTip;
    int iSetTipX;
    int iSetTipY;
    int iTipC;
    int iTipDelay;
    int iTipExp;
    boolean DoneSettingButtons;
    String nL;
    String forGetJS;
    boolean bIsHand;
    int MouseEnteredTo;
    Color AbackColor;
    Image imgOffBuf;
    Button myB;
    boolean CV;
    Thread me;
    int ButtonCount;
    int CurrentID;
    int PressedBtn;
    int iDOWNBtn;
    String sBackImg;
    Image imgBackImg;
    int iBackImgType;
    int BgOffsetX;
    int BgOffsetY;
    String[] AppletProgram;
    boolean bAPMO;
    boolean bAPS;
    int ABorderStyle;
    int ABorderWidth;
    int ABorderShade;
    Color ABorderColor;
    Color AcFadeColor;
    int AiFadeStyle;
    String sLoading;
    Color cLoadingFont;
    Color cLoadingBack;
    Font fLoadingFont;
    FontMetrics fmLoadingFont;
    boolean[] bPnt;
    boolean[] isDOWN;
    boolean[] bRad;
    int[] state;
    boolean[][] bTransp;
    boolean[] ShowHand;
    String[] StatusB;
    String[] sTip;
    String[][] sLabel;
    int[][] iLblStyle;
    int[][] iLblPosX;
    int[][] iLblPosY;
    Image[][] img;
    int[][] iImgStyle;
    AudioClip[][] snd;
    boolean[][] sndStop;
    boolean[][] sndLoop;
    int[] left;
    int[] top;
    int[] width;
    int[] height;
    int[] oldTop;
    int[] oldLeft;
    boolean[] hidden;
    Font[][] mFont;
    Color[][] backColor;
    Color[][] fontColor;
    URL[] bURL;
    int[] noURL;
    String[] sUrlTarget;
    String[] sJavaScript;
    String[][] sProgram;
    int[][] BorderStyle;
    int[][] BorderWidth;
    int[][] BorderShade;
    Color[][] BorderColor;
    int[][] TFrameThick;
    Color[][] TFrameColor;
    boolean[][] TFrameLighter;
    int[][] TFrameShade;
    boolean[][] TFrameLeftToRight;
    Color[][] cFadeColor;
    int[][] iFadeStyle;
    Color[][] cBFrameColor;
    int[][] iBFrameThick;
    int[] NIndex;
    String[] sBName;
    int[][] iImgX;
    int[][] iImgY;
    
    public void stopSounds(final int n, final int n2, final boolean b) {
        int n3 = 1;
        do {
            if (n2 != n3 && this.sndStop[n3][n] && (this.PressedBtn != n || b)) {
                this.stopSound(n, n3);
            }
        } while (++n3 <= 3);
    }
    
    public String GetProgPart(final String s, final int n) {
        try {
            if (s == null) {
                return "-1";
            }
            String string = "";
            int i = 0;
            int n2 = 1;
            final char[] array = new char[s.length() + 1];
            final char[] charArray = s.toCharArray();
            while (i < s.length()) {
                if (s.charAt(i) != ' ') {
                    string += charArray[i];
                }
                if (s.charAt(i) == ' ' || i == s.length() - 1) {
                    if (n2 == n) {
                        return string;
                    }
                    string = "";
                    ++n2;
                }
                ++i;
            }
            return "-1";
        }
        catch (Exception ex) {
            this.pC("GetProgPart(): " + ex);
            return "-1";
        }
    }
    
    public void drawTFrame(final int n, final Graphics graphics, final String s, final int n2, final int n3, final int n4, final Color color, final boolean b, final int n5, final boolean b2) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int n6 = n2 - n4;
        final int n7 = n2 + n4;
        int n8 = 0;
        if (n4 <= 0) {
            return;
        }
        for (int i = n3 - n4; i <= n3; ++i) {
            if (b2) {
                n8 = 0;
            }
            else {
                graphics.setColor(this.STC(n5, n4, red, green, blue, n8, b));
                ++n8;
            }
            for (int j = n6; j <= n7; ++j) {
                if (b2) {
                    graphics.setColor(this.STC(n5, n4, red, green, blue, n8, b));
                    ++n8;
                }
                graphics.drawString(s, j, i);
            }
        }
        int n9 = 0;
        for (int k = n3 + n4; k > n3; --k) {
            if (b2) {
                n9 = 0;
            }
            else {
                graphics.setColor(this.STC(n5, n4, red, green, blue, n9, b));
                ++n9;
            }
            for (int l = n6; l <= n7; ++l) {
                if (b2) {
                    graphics.setColor(this.STC(n5, n4, red, green, blue, n9, b));
                    ++n9;
                }
                graphics.drawString(s, l, k);
            }
        }
        graphics.setColor(this.fontColor[this.state[n]][n]);
    }
    
    public void setBorder(final String s, final int n, final int n2, final int n3, final int n4, final String s2) {
        try {
            for (int setNIndex = this.SetNIndex(s), i = 0; i < setNIndex; ++i) {
                final int n5 = this.NIndex[i];
                if (n2 != -999) {
                    this.BorderStyle[n][n5] = n2;
                }
                if (n3 != -999) {
                    this.BorderWidth[n][n5] = n3;
                }
                if (n4 != -999) {
                    this.BorderShade[n][n5] = n4;
                }
                if (s2.compareTo("-999") != 0) {
                    this.BorderColor[n][n5] = new Color(Integer.parseInt(s2, 16));
                }
            }
            this.bManPnt = true;
            if (this.doRepaint) {
                this.repaint();
            }
        }
        catch (Exception ex) {
            this.pC("setColor(): " + ex);
        }
    }
    
    public void DoJavaScript(final String forGetJS) {
        try {
            this.forGetJS = forGetJS;
            JSObject.getWindow((Applet)this).eval(forGetJS);
        }
        catch (Exception ex) {
            this.pC("DoJavaScript(): " + ex);
        }
    }
    
    private float getMoveStp(final int n, final int n2) {
        return n / n2;
    }
    
    private int s2int(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public void SetButtons() {
        try {
            String s = "";
            String s2 = "";
            final String[] array = new String[3];
            final String[] array2 = new String[3];
            final int[] array3 = new int[3];
            final int[] array4 = new int[3];
            for (int i = 0; i < this.ButtonCount; ++i) {
                this.state[i] = 1;
                int j = 0;
                int n = 0;
                int int1 = 0;
                String s3 = "";
                final String param = this.getParam("" + (i + 1), "");
                final char[] array5 = new char[param.length() + 1];
                final char[] charArray = param.toCharArray();
                while (j < param.length()) {
                    if (int1 != 0 || param.charAt(j) == '|') {
                        if (int1 == 0) {
                            s3 = this.myReplace(this.myReplace(s3, "&!3", "|"), "&!4", "^");
                        }
                        switch (n) {
                            case 0: {
                                this.left[i] = Integer.parseInt(s3);
                                this.oldLeft[i] = this.left[i];
                                break;
                            }
                            case 1: {
                                this.top[i] = Integer.parseInt(s3);
                                this.oldTop[i] = this.top[i];
                                break;
                            }
                            case 2: {
                                this.width[i] = Integer.parseInt(s3);
                                break;
                            }
                            case 3: {
                                this.height[i] = Integer.parseInt(s3);
                                break;
                            }
                            case 4: {
                                this.ipar = Integer.parseInt(s3);
                                this.ShowHand[i] = this.gp();
                                this.bRad[i] = this.gp();
                                this.bTransp[1][i] = this.gp();
                                this.bTransp[2][i] = this.gp();
                                this.bTransp[3][i] = this.gp();
                                this.hidden[i] = this.gp();
                                this.TFrameLighter[1][i] = this.gp();
                                this.TFrameLighter[2][i] = this.gp();
                                this.TFrameLighter[3][i] = this.gp();
                                this.TFrameLeftToRight[1][i] = this.gp();
                                this.TFrameLeftToRight[2][i] = this.gp();
                                this.TFrameLeftToRight[3][i] = this.gp();
                                this.sndStop[1][i] = this.gp();
                                this.sndStop[2][i] = this.gp();
                                this.sndStop[3][i] = this.gp();
                                this.sndLoop[1][i] = this.gp();
                                this.sndLoop[2][i] = this.gp();
                                this.sndLoop[3][i] = this.gp();
                                break;
                            }
                            case 5: {
                                this.fontColor[1][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 6: {
                                this.fontColor[2][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 7: {
                                this.fontColor[3][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 8: {
                                this.BorderColor[1][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 9: {
                                this.backColor[1][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 10: {
                                this.BorderColor[2][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 11: {
                                this.backColor[2][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 12: {
                                this.BorderColor[3][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 13: {
                                this.backColor[3][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 14: {
                                this.TFrameColor[1][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 15: {
                                this.TFrameColor[2][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 16: {
                                this.TFrameColor[3][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 17: {
                                this.cFadeColor[1][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 18: {
                                this.cFadeColor[2][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 19: {
                                this.cFadeColor[3][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 20: {
                                this.cBFrameColor[1][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 21: {
                                this.cBFrameColor[2][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 22: {
                                this.cBFrameColor[3][i] = new Color(Integer.parseInt(s3, 16));
                                break;
                            }
                            case 23: {
                                this.iLblStyle[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 24: {
                                this.iLblStyle[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 25: {
                                this.iLblStyle[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 26: {
                                this.iLblPosX[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 27: {
                                this.iLblPosY[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 28: {
                                this.iLblPosX[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 29: {
                                this.iLblPosY[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 30: {
                                this.iLblPosX[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 31: {
                                this.iLblPosY[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 32: {
                                array2[0] = this.sFN[Integer.parseInt(s3)];
                                break;
                            }
                            case 33: {
                                array2[1] = this.sFN[Integer.parseInt(s3)];
                                break;
                            }
                            case 34: {
                                array2[2] = this.sFN[Integer.parseInt(s3)];
                                break;
                            }
                            case 35: {
                                this.TFrameShade[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 36: {
                                this.TFrameShade[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 37: {
                                this.TFrameShade[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 38: {
                                array3[0] = Integer.parseInt(s3);
                                break;
                            }
                            case 39: {
                                array3[1] = Integer.parseInt(s3);
                                break;
                            }
                            case 40: {
                                array3[2] = Integer.parseInt(s3);
                                break;
                            }
                            case 41: {
                                this.iFadeStyle[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 42: {
                                this.iFadeStyle[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 43: {
                                this.iFadeStyle[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 44: {
                                this.TFrameThick[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 45: {
                                this.TFrameThick[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 46: {
                                this.TFrameThick[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 47: {
                                this.iBFrameThick[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 48: {
                                this.iBFrameThick[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 49: {
                                this.iBFrameThick[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 50: {
                                this.iImgX[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 51: {
                                this.iImgY[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 52: {
                                this.iImgX[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 53: {
                                this.iImgY[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 54: {
                                this.iImgX[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 55: {
                                this.iImgY[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 56: {
                                this.iImgStyle[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 57: {
                                this.iImgStyle[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 58: {
                                this.iImgStyle[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 59: {
                                this.BorderStyle[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 60: {
                                this.BorderStyle[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 61: {
                                this.BorderStyle[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 62: {
                                this.BorderWidth[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 63: {
                                this.BorderWidth[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 64: {
                                this.BorderWidth[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 65: {
                                this.BorderShade[1][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 66: {
                                this.BorderShade[2][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 67: {
                                this.BorderShade[3][i] = Integer.parseInt(s3);
                                break;
                            }
                            case 68: {
                                array4[0] = Integer.parseInt(s3);
                                break;
                            }
                            case 69: {
                                array4[1] = Integer.parseInt(s3);
                                break;
                            }
                            case 70: {
                                array4[2] = Integer.parseInt(s3);
                                break;
                            }
                            case 71: {
                                this.sLabel[1][i] = this.myReplace(s3, this.nL, "\n");
                                break;
                            }
                            case 72: {
                                this.sLabel[2][i] = this.myReplace(s3, this.nL, "\n");
                                break;
                            }
                            case 73: {
                                this.sLabel[3][i] = this.myReplace(s3, this.nL, "\n");
                                break;
                            }
                            case 74: {
                                s2 = s3;
                                break;
                            }
                            case 75: {
                                this.StatusB[i] = s3;
                                break;
                            }
                            case 76: {
                                this.sUrlTarget[i] = ((s3.compareTo("") == 0) ? "_self" : s3);
                                break;
                            }
                            case 77: {
                                if (this.iImgStyle[1][i] != 9) {
                                    this.img[1][i] = this.getImage(this.getCodeBase(), this.sImgPath + s3);
                                }
                                break;
                            }
                            case 78: {
                                if (this.iImgStyle[2][i] != 9) {
                                    this.img[2][i] = this.getImage(this.getCodeBase(), this.sImgPath + s3);
                                }
                                break;
                            }
                            case 79: {
                                if (this.iImgStyle[3][i] != 9) {
                                    this.img[3][i] = this.getImage(this.getCodeBase(), this.sImgPath + s3);
                                }
                                break;
                            }
                            case 80: {
                                array[0] = s3;
                                break;
                            }
                            case 81: {
                                array[1] = s3;
                                break;
                            }
                            case 82: {
                                array[2] = s3;
                                break;
                            }
                            case 83: {
                                this.sProgram[0][i] = s3;
                                break;
                            }
                            case 84: {
                                this.sProgram[1][i] = s3;
                                break;
                            }
                            case 85: {
                                this.sProgram[2][i] = s3;
                                break;
                            }
                            case 86: {
                                this.sProgram[3][i] = s3;
                                break;
                            }
                            case 87: {
                                this.sProgram[4][i] = s3;
                                break;
                            }
                            case 88: {
                                this.sProgram[5][i] = s3;
                                break;
                            }
                            case 89: {
                                this.sTip[i] = s3;
                                break;
                            }
                            case 90: {
                                this.sBName[i] = ((s3.compareTo("") == 0) ? ("Button&!0" + (i + 1)) : s3);
                                break;
                            }
                        }
                        ++n;
                        if (int1 > 0) {
                            --int1;
                        }
                        if (int1 != 0) {
                            continue;
                        }
                        s = s3;
                        s3 = "";
                        ++j;
                    }
                    else {
                        if (charArray[j] == '^') {
                            s3 = s;
                            if (charArray[j + 1] != '|') {
                                int1 = Integer.parseInt("" + charArray[j + 1], 36);
                            }
                        }
                        else {
                            s3 += charArray[j];
                        }
                        ++j;
                    }
                }
                int n2 = 0;
                do {
                    if (array[n2] != "") {
                        this.snd[n2 + 1][i] = this.getAudioClip(this.getCodeBase(), this.sSndPath + array[n2]);
                    }
                    if (this.CoolJAVA && this.snd[n2 + 1][i] != null) {
                        this.snd[n2 + 1][i].play();
                        this.snd[n2 + 1][i].stop();
                    }
                    this.mFont[n2 + 1][i] = new Font(array2[n2], array3[n2], array4[n2]);
                } while (++n2 < 3);
                if (this.iDOWNBtn - 1 == i) {
                    this.state[i] = 3;
                    if (this.bRad[i]) {
                        this.isDOWN[i] = true;
                        this.PressedBtn = i;
                    }
                }
                try {
                    if (s2.compareTo("") != 0) {
                        if (this.myStartsWith(s2, "javascript:")) {
                            this.sJavaScript[i] = this.cutFirstChars(s2, 11);
                        }
                        else if (s2.startsWith("#")) {
                            final int index = this.getDocumentBase().toString().indexOf(35);
                            String s4;
                            if (index == -1) {
                                s4 = this.getDocumentBase().toString();
                            }
                            else {
                                s4 = this.getDocumentBase().toString().substring(0, index);
                            }
                            this.bURL[i] = new URL(s4 + s2);
                        }
                        else {
                            this.bURL[i] = new URL(this.getCodeBase(), s2);
                        }
                    }
                    else {
                        this.noURL[i] = 1;
                    }
                }
                catch (MalformedURLException ex2) {}
            }
        }
        catch (Exception ex) {
            this.pC("SetButtons(): " + ex);
        }
    }
    
    public String cutFirstChars(final String s, final int n) {
        try {
            final StringBuffer sb = new StringBuffer(s);
            final char[] array = new char[s.length() - n];
            sb.getChars(n, s.length(), array, 0);
            return new String(array);
        }
        catch (Exception ex) {
            this.pC("cutFirstChars(): " + ex);
            return "";
        }
    }
    
    private int SetNIndex(String replaceIlligal_In_Prog) {
        int n = 0;
        boolean b = false;
        replaceIlligal_In_Prog = this.ReplaceIlligal_In_Prog(replaceIlligal_In_Prog);
        if (replaceIlligal_In_Prog.endsWith("*")) {
            b = true;
        }
        for (int i = 0; i < this.ButtonCount; ++i) {
            if (b) {
                if (this.sBName[i].regionMatches(0, replaceIlligal_In_Prog, 0, replaceIlligal_In_Prog.length() - 1)) {
                    this.NIndex[n] = i;
                    ++n;
                }
            }
            else if (this.sBName[i].compareTo(replaceIlligal_In_Prog) == 0) {
                this.NIndex[n] = i;
                ++n;
            }
        }
        return n;
    }
    
    private int CalcMove(final int n, String s) {
        if (s.compareTo("-999") == 0) {
            return n;
        }
        s = s.trim();
        if (s.endsWith("+")) {
            s = this.chop(s);
            return n + Integer.parseInt(s);
        }
        if (s.endsWith("-")) {
            s = this.chop(s);
            return n - Integer.parseInt(s);
        }
        return Integer.parseInt(s);
    }
    
    private String decodeMove(final int n, final String s, final String s2, final String s3, final String s4, final String s5, final int n2, final int n3) {
        String s6 = "";
        this.fXCur = this.left[n];
        this.fYCur = this.top[n];
        this.fWCur = this.width[n];
        this.fHCur = this.height[n];
        final int moveTar = this.getMoveTar(this.left[n], s2);
        final float moveStp = this.getMoveStp(this.iMovPath, n2);
        final int moveTar2 = this.getMoveTar(this.top[n], s3);
        final float moveStp2 = this.getMoveStp(this.iMovPath, n2);
        final int moveTar3 = this.getMoveTar(this.width[n], s4);
        final float moveStp3 = this.getMoveStp(this.iMovPath, n2);
        final int moveTar4 = this.getMoveTar(this.height[n], s5);
        final float moveStp4 = this.getMoveStp(this.iMovPath, n2);
        while (true) {
            final String curMove = this.getCurMove(this.fXCur, moveTar, moveStp);
            this.fXCur += this.fAddMov;
            final String curMove2 = this.getCurMove(this.fYCur, moveTar2, moveStp2);
            this.fYCur += this.fAddMov;
            final String curMove3 = this.getCurMove(this.fWCur, moveTar3, moveStp3);
            this.fWCur += this.fAddMov;
            final String curMove4 = this.getCurMove(this.fHCur, moveTar4, moveStp4);
            this.fHCur += this.fAddMov;
            if (curMove.compareTo("0+") == 0 && curMove2.compareTo("0+") == 0 && curMove3.compareTo("0+") == 0 && curMove4.compareTo("0+") == 0) {
                break;
            }
            s6 = s6 + "3 " + s + " " + curMove + " " + curMove2 + " " + curMove3 + " " + curMove4 + ";";
            if (n3 <= 0) {
                continue;
            }
            s6 = s6 + "6 " + n3 + ";";
        }
        return s6;
    }
    
    public void pC(final String s) {
        System.out.println(s);
    }
    
    public synchronized boolean mouseExit(final Event event, final int n, final int n2) {
        try {
            if (!this.DoneSettingButtons) {
                return true;
            }
            this.iMouseX = -1;
            this.iMouseY = -1;
            this.sProgramStuck += this.AppletProgram[2];
            this.bAPMO = false;
            this.DoMouseExit();
        }
        catch (Exception ex) {
            this.pC("mouseExit(): " + ex);
        }
        return true;
    }
    
    public void DoProgram(final String s) {
        try {
            if (s == null || s.compareTo("") == 0) {
                return;
            }
            String string = "";
            int i = 0;
            int n = 0;
            final char[] array = new char[s.length() + 1];
            final char[] charArray = s.toCharArray();
            while (i < s.length()) {
                if (s.charAt(i) == ';') {
                    final int int1 = Integer.parseInt(this.GetProgPart(string, 1));
                    final String getProgPart = this.GetProgPart(string, 2);
                    switch (int1) {
                        case 1: {
                            this.setParam(getProgPart, 1, "1");
                            break;
                        }
                        case 2: {
                            this.setParam(getProgPart, 1, "0");
                            break;
                        }
                        case 3: {
                            final int s2int = this.s2int(this.GetProgPart(string, 7));
                            final int s2int2 = this.s2int(this.GetProgPart(string, 8));
                            if (s2int <= 0) {
                                this.moveButton(getProgPart, this.GetProgPart(string, 3), this.GetProgPart(string, 4), this.GetProgPart(string, 5), this.GetProgPart(string, 6));
                                break;
                            }
                            this.gWaitFor = 0L;
                            this.sProgramStuck = s.substring(i + 1);
                            this.SetNIndex(getProgPart);
                            this.sProgramStuck = this.decodeMove(this.NIndex[0], getProgPart, this.GetProgPart(string, 3), this.GetProgPart(string, 4), this.GetProgPart(string, 5), this.GetProgPart(string, 6), s2int, s2int2) + this.sProgramStuck;
                            return;
                        }
                        case 4: {
                            this.setLabel(getProgPart, Integer.parseInt(this.GetProgPart(string, 3)), this.myReplace(this.myReplace(this.myReplace(this.GetProgPart(string, 4), "&!1", ";"), "&!0", " "), "\\n", "\n"));
                            break;
                        }
                        case 5: {
                            this.setColor(getProgPart, Integer.parseInt(this.GetProgPart(string, 3)), this.GetProgPart(string, 4));
                            break;
                        }
                        case 6: {
                            this.gWaitFor = this.mytime() + Integer.parseInt(getProgPart);
                            this.sProgramStuck = s.substring(i + 1);
                            return;
                        }
                        case 7: {
                            this.DoJavaScript(this.myReplace(this.myReplace(this.GetProgPart(string, 3), "&!1", ";"), "&!0", " "));
                            break;
                        }
                        case 8: {
                            this.setParam(getProgPart, 7, this.GetProgPart(string, 3));
                            break;
                        }
                        case 9: {
                            this.setBorder(getProgPart, Integer.parseInt(this.GetProgPart(string, 3)), -999, -999, -999, this.GetProgPart(string, 4));
                            break;
                        }
                        case 10: {
                            this.gWaitFor = 0L;
                            this.sProgramStuck = s.substring(i + 1);
                            this.sProgramStuck = this.decodeBlink(getProgPart, this.s2int(this.GetProgPart(string, 3)), this.GetProgPart(string, 4)) + this.sProgramStuck;
                            return;
                        }
                    }
                    string = "";
                    ++n;
                }
                else {
                    string += charArray[i];
                }
                ++i;
            }
            this.sProgramStuck = "";
        }
        catch (Exception ex) {
            this.pC("DoProgram(): " + ex);
        }
    }
    
    public synchronized void DoMouseExit() {
        try {
            if (this.CurrentID == -1) {
                return;
            }
            this.smTip = "";
            this.lblTipFrame.hide();
            this.lblTip.hide();
            if (!this.isDOWN[this.CurrentID]) {
                this.state[this.CurrentID] = 1;
                this.bPnt[this.CurrentID] = false;
                this.bManPnt = true;
                if (this.doRepaint) {
                    this.repaint();
                }
                this.sProgramStuck += this.sProgram[1][this.CurrentID];
            }
            this.showStatus(" ");
            if (this.bIsHand) {
                try {
                    ((Frame)this.getParent()).setCursor(0);
                }
                catch (Exception ex2) {}
                this.bIsHand = false;
            }
            this.stopSounds(this.CurrentID, 0, false);
            this.CurrentID = -1;
            this.MouseEnteredTo = -1;
        }
        catch (Exception ex) {
            this.pC("DoMouseExit(): " + this.CurrentID + ex);
        }
    }
    
    void Vycvet(final Graphics graphics, final Color color, final Color color2, final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            final int red = color.getRed();
            final int green = color.getGreen();
            final int blue = color.getBlue();
            final int red2 = color2.getRed();
            final int green2 = color2.getGreen();
            final int blue2 = color2.getBlue();
            int n6 = 0;
            switch (n5) {
                case 1: {
                    n6 = n3;
                    break;
                }
                case 2: {
                    n6 = n4;
                    break;
                }
            }
            final double dUvilich = this.dUvilich(red2, red, n6);
            final double dUvilich2 = this.dUvilich(green2, green, n6);
            final double dUvilich3 = this.dUvilich(blue2, blue, n6);
            for (int i = 0; i < n6; ++i) {
                graphics.setColor(new Color(Math.max(0, Math.min(255, red2 + (int)(dUvilich * i))), Math.max(0, Math.min(255, green2 + (int)(dUvilich2 * i))), Math.max(0, Math.min(255, blue2 + (int)(dUvilich3 * i)))));
                switch (n5) {
                    case 1: {
                        graphics.drawLine(n + i, n2, n + i, n2 + n4);
                        break;
                    }
                    case 2: {
                        graphics.drawLine(n, n2 + i, n + n3, n2 + i);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            this.pC("Vycvet(): " + ex);
        }
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (!this.DoneSettingButtons) {
                return true;
            }
            final int whatsThat;
            if ((whatsThat = this.WhatsThat(n, n2)) == -1) {
                return true;
            }
            this.iTipC = 0;
            this.lblTipFrame.hide();
            this.lblTip.hide();
            this.smTip = this.sTip[whatsThat];
            if (this.MouseEnteredTo != -1 && whatsThat != this.MouseEnteredTo) {
                if (!this.isDOWN[this.MouseEnteredTo]) {
                    this.DoMouseExit();
                }
                return true;
            }
            if (this.bRad[whatsThat] && !this.isDOWN[whatsThat]) {
                this.CurrentID = whatsThat;
                this.DePress();
                this.isDOWN[whatsThat] = true;
                this.PressedBtn = whatsThat;
                this.sProgramStuck += this.sProgram[4][this.PressedBtn];
            }
            if (!this.bRad[whatsThat]) {
                this.state[whatsThat] = 2;
                this.bManPnt = true;
                this.bPnt[whatsThat] = false;
                if (this.doRepaint) {
                    this.repaint();
                }
                this.playSound(whatsThat, 1);
                this.stopSounds(whatsThat, 1, false);
            }
            this.CurrentID = whatsThat;
            this.sProgramStuck += this.sProgram[0][whatsThat];
            this.GOURL();
        }
        catch (Exception ex) {
            this.pC("mouseUp(): " + ex);
        }
        return true;
    }
    
    public void destroy() {
        this.me = null;
        this.stop();
        if (this.imgBG != null) {
            this.imgBG.flush();
        }
        if (this.imgOffBuf != null) {
            this.imgOffBuf.flush();
        }
        System.gc();
    }
    
    public synchronized void DoMouseEnter() {
        try {
            if (this.MouseEnteredTo == this.CurrentID) {
                return;
            }
            this.showStatus(this.StatusB[this.CurrentID]);
            this.smTip = this.sTip[this.CurrentID];
            if (!this.isDOWN[this.CurrentID]) {
                this.state[this.CurrentID] = 2;
                this.bPnt[this.CurrentID] = false;
                this.bManPnt = true;
                if (this.doRepaint) {
                    this.repaint();
                }
                this.playSound(this.CurrentID, 2);
                this.stopSounds(this.CurrentID, 2, false);
            }
            if (this.ShowHand[this.CurrentID] && this.bAllowHC) {
                try {
                    ((Frame)this.getParent()).setCursor(12);
                    this.bIsHand = true;
                }
                catch (Exception ex2) {}
            }
            else if (this.bIsHand) {
                try {
                    ((Frame)this.getParent()).setCursor(0);
                }
                catch (Exception ex3) {}
                this.bIsHand = false;
            }
            this.MouseEnteredTo = this.CurrentID;
            this.sProgramStuck += this.sProgram[2][this.CurrentID];
        }
        catch (Exception ex) {
            this.pC("DoMouseEnter(): " + ex);
        }
    }
    
    private int myAbs(final int n) {
        if (n < 0) {
            return n * -1;
        }
        return n;
    }
    
    private float fAbs(final float n) {
        if (n < 0.0f) {
            return n * -1.0f;
        }
        return n;
    }
    
    private String chop(final String s) {
        return s.substring(0, s.length() - 1);
    }
    
    public void Draw3DF(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final int n6, final int n7) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        boolean b = true;
        boolean b2 = true;
        switch (n) {
            case 0: {
                return;
            }
            case 1: {
                b = true;
                b2 = false;
                break;
            }
            case 2: {
                b = false;
                b2 = true;
                break;
            }
            case 3: {
                b = true;
                b2 = true;
                break;
            }
            case 4: {
                b = false;
                b2 = false;
                break;
            }
        }
        for (int i = 0; i < n7; ++i) {
            graphics.setColor(this.STC(n6, n7, red, green, blue, i, b));
            graphics.drawLine(i + n2, i + n3, n4 - 1 - i + n2, i + n3);
            graphics.drawLine(i + n2, i + n3, i + n2, n5 - 1 - i + n3);
            graphics.setColor(this.STC(n6, n7, red, green, blue, i, b2));
            graphics.drawLine(i + n2, n5 - 1 - i + n3, n4 - 1 - i + n2, n5 - 1 - i + n3);
            graphics.drawLine(n4 - 1 - i + n2, i + n3, n4 - 1 - i + n2, n5 - 1 - i + n3);
        }
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (!this.DoneSettingButtons) {
                return true;
            }
            this.smTip = "";
            this.lblTipFrame.hide();
            this.lblTip.hide();
            final int whatsThat;
            if ((whatsThat = this.WhatsThat(n, n2)) == -1) {
                this.sProgramStuck += this.AppletProgram[1];
                return true;
            }
            this.state[whatsThat] = 3;
            this.bPnt[whatsThat] = false;
            this.bManPnt = true;
            if (this.doRepaint) {
                this.repaint();
            }
            this.sProgramStuck += this.sProgram[3][whatsThat];
            this.playSound(whatsThat, 3);
            this.stopSounds(whatsThat, 3, false);
            this.CurrentID = whatsThat;
        }
        catch (Exception ex) {
            this.pC("mouseDown(): " + ex);
        }
        return true;
    }
    
    public void run() {
        try {
            Thread.currentThread().setPriority(10);
            if (!this.DoneSettingButtons) {
                this.SetButtons();
                if (this.bPreLoad) {
                    this.loadImgs();
                    this.paintBG();
                    this.DoneSettingButtons = true;
                }
                else {
                    this.paintBG();
                    this.DoneSettingButtons = true;
                    this.repaint();
                    this.loadImgs();
                    this.paintBG();
                }
                if (this.imgBackImg != null) {
                    this.imgBackImg.flush();
                    this.grImgBG.dispose();
                    System.gc();
                }
            }
            this.repaint();
            if (this.AppletProgram[3].compareTo("") != 0) {
                this.bAPS = true;
            }
            this.showStatus(" ");
            this.AST = System.currentTimeMillis();
            while (Thread.currentThread() == this.me) {
                try {
                    Thread.sleep(this.iMSleepTime);
                }
                catch (InterruptedException ex2) {}
                if (this.smTip.length() > 0 && !this.bDismTip) {
                    if (this.iMouseX == this.iOldMouseX && this.iMouseY == this.iOldMouseY) {
                        ++this.iTipC;
                    }
                    else {
                        this.iTipC = 0;
                        this.bShowingTip = false;
                    }
                    if (this.iTipC > this.iTipDelay && this.iTipC < this.iTipExp && !this.bShowingTip) {
                        this.bShowingTip = true;
                        this.lblTip.setText(this.smTip);
                        final int n = this.fmTip.stringWidth(this.smTip) + 6;
                        final int n2 = this.fmTip.getHeight() + 1;
                        int iTipFW = this.iMouseX - n / 2;
                        int n3 = this.iMouseY + 24 + this.iTipFW;
                        if (n3 + n2 + this.iTipFW * 2 > this.size().height) {
                            n3 = this.iMouseY - 24;
                        }
                        if (iTipFW + n + this.iTipFW * 2 > this.size().width) {
                            iTipFW = this.size().width - n - this.iTipFW * 2;
                        }
                        if (iTipFW < 0) {
                            iTipFW = this.iTipFW;
                        }
                        this.lblTip.reshape(iTipFW, n3, n, n2);
                        this.lblTipFrame.reshape(iTipFW - this.iTipFW, n3 - this.iTipFW, n + this.iTipFW * 2, n2 + this.iTipFW * 2);
                        this.lblTipFrame.show();
                        this.lblTip.show();
                    }
                    if (this.iTipC > this.iTipExp) {
                        this.bShowingTip = false;
                        this.iTipC = 0;
                        this.lblTipFrame.hide();
                        this.lblTip.hide();
                    }
                    this.iOldMouseX = this.iMouseX;
                    this.iOldMouseY = this.iMouseY;
                }
                if (this.mytime() >= this.gWaitFor) {
                    this.DoProgram(this.sProgramStuck);
                }
                if (this.bAPS && this.mytime() > 200L) {
                    this.DoProgram(this.AppletProgram[3]);
                    this.AppletProgram[3] = "";
                    this.bAPS = false;
                }
                if (this.bDoScroll) {
                    this.DoScroll();
                }
            }
        }
        catch (Exception ex) {
            this.pC("run(): " + ex);
        }
    }
    
    public void init() {
        this.setLayout(null);
        this.PrepareA();
        if (this.CV) {
            if (!this.getCodeBase().getProtocol().equals("file")) {
                (this.myB = new Button(this.sREG)).reshape(this.size().width / 2 - 60, this.size().height / 2 - 20, 120, 40);
                this.add(this.myB);
                this.myB.show();
            }
        }
    }
    
    public void stopSound(final int n, final int n2) {
        if (this.snd[n2][n] != null) {
            this.snd[n2][n].stop();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.CV && event.id == 1001 && event.target == this.myB) {
            try {
                this.getAppletContext().showDocument(new URL(this.sUR), "_blank");
            }
            catch (Exception ex) {}
        }
        return super.handleEvent(event);
    }
    
    public void setLabel(final String s, final int n, final String s2) {
        try {
            for (int setNIndex = this.SetNIndex(s), i = 0; i < setNIndex; ++i) {
                this.sLabel[n][this.NIndex[i]] = s2;
                this.bManPnt = true;
            }
            if (this.doRepaint) {
                this.repaint();
            }
        }
        catch (Exception ex) {
            this.pC("setLabel(): " + ex);
        }
    }
    
    public synchronized boolean mouseMove(final Event event, final int iMouseX, final int iMouseY) {
        try {
            if (!this.DoneSettingButtons) {
                return true;
            }
            this.iMouseX = iMouseX;
            this.iMouseY = iMouseY;
            final int whatsThat = this.WhatsThat(iMouseX, iMouseY);
            if (whatsThat == -1) {
                this.DoMouseExit();
                if (!this.bAPMO) {
                    this.sProgramStuck += this.AppletProgram[0];
                    this.bAPMO = true;
                }
                this.showStatus(" ");
            }
            else {
                this.bAPMO = false;
                if (this.MouseEnteredTo != whatsThat && this.CurrentID != -1) {
                    this.DoMouseExit();
                }
                this.CurrentID = whatsThat;
                this.DoMouseEnter();
            }
        }
        catch (Exception ex) {
            this.pC("mouseMove(): " + ex);
        }
        return true;
    }
    
    public void moveButton(final String s, final String s2, final String s3, final String s4, final String s5) {
        try {
            for (int setNIndex = this.SetNIndex(s), i = 0; i < setNIndex; ++i) {
                final int n = this.NIndex[i];
                this.left[n] = this.CalcMove(this.left[n], s2);
                this.top[n] = this.CalcMove(this.top[n], s3);
                this.width[n] = this.CalcMove(this.width[n], s4);
                this.height[n] = this.CalcMove(this.height[n], s5);
            }
            if (this.doRepaint) {
                this.repaint();
            }
        }
        catch (Exception ex) {
            this.pC("moveButton(): " + ex);
        }
    }
    
    public void stop() {
        if (this.me != null && this.me.isAlive()) {
            this.me.stop();
            System.gc();
        }
    }
    
    void loadImgs() {
        for (int i = 0; i < this.ButtonCount; ++i) {
            this.loadImg(this.img[1][i]);
            this.loadImg(this.img[2][i]);
            this.loadImg(this.img[3][i]);
        }
        this.loadImg(this.imgBackImg);
        System.gc();
    }
    
    private String getCurMove(final float n, final int n2, final float n3) {
        final Float n4 = new Float(n);
        Float n5 = new Float(n3);
        if (this.fAbs(n4 - new Float(n2)) <= 0.1) {
            this.fAddMov = 0.0f;
            return "0+";
        }
        final int n6 = n2 - (int)(Object)n4;
        if (this.myAbs(n6) < (int)(Object)n5) {
            n5 = new Float(this.myAbs(n6));
        }
        if (n6 > 0) {
            this.fAddMov = n5;
            return Integer.toString(this.myInt(new Float(n4 + n5)));
        }
        this.fAddMov = -n5;
        return Integer.toString(this.myInt(new Float(n4 - n5)));
    }
    
    private int getMoveTar(final int n, final String s) {
        if (s.compareTo("-999") == 0) {
            return n;
        }
        if (s.endsWith("+")) {
            return n + (this.iMovPath = this.s2int(this.chop(s)));
        }
        if (s.endsWith("-")) {
            return n - (this.iMovPath = this.s2int(this.chop(s)));
        }
        this.iMovPath = this.myAbs(n - this.s2int(s));
        return this.s2int(s);
    }
    
    double dUvilich(final int n, final int n2, final int n3) {
        double n4 = n2 - n;
        if (n4 != 0.0 && n3 != 0) {
            n4 /= n3;
        }
        return n4;
    }
    
    public synchronized void update(final Graphics graphics) {
        try {
            if (this.bNewP && !this.bManPnt) {
                this.bPnt = new boolean[this.ButtonCount];
                this.bPnted = false;
            }
            this.bManPnt = false;
            this.paint(graphics);
        }
        catch (Exception ex) {
            this.pC("update(): " + ex);
        }
    }
    
    public synchronized int WhatsThat(final int n, final int n2) {
        try {
            boolean b = false;
            int i;
            for (i = this.ButtonCount - 1; i >= 0; --i) {
                if (!this.hidden[i] && n - this.left[i] <= this.width[i] && n - this.left[i] > 0 && n2 - this.top[i] <= this.height[i] && n2 - this.top[i] > 0) {
                    b = true;
                    break;
                }
                if (b) {
                    break;
                }
            }
            if (b) {
                return i;
            }
            return -1;
        }
        catch (Exception ex) {
            this.pC("WhatsThat(): " + ex);
            return -1;
        }
    }
    
    protected String getParam(final String s, final String s2) {
        String s3 = this.getParameter(s);
        try {
            s3 = this.myReplace(s3, "&!2", "\"");
        }
        catch (Exception ex) {
            this.pC("getParam(): " + ex);
        }
        if (s3 == null) {
            return s2;
        }
        return s3;
    }
    
    public void setParam(final String s, final int n, final String s2) {
        try {
            for (int setNIndex = this.SetNIndex(s), i = 0; i < setNIndex; ++i) {
                final int n2 = this.NIndex[i];
                switch (n) {
                    case 1: {
                        if (Integer.parseInt(s2) == 0) {
                            this.hidden[n2] = true;
                        }
                        else {
                            this.hidden[n2] = false;
                        }
                        break;
                    }
                    case 2: {
                        this.left[n2] = Integer.parseInt(s2);
                        break;
                    }
                    case 3: {
                        this.top[n2] = Integer.parseInt(s2);
                        break;
                    }
                    case 4: {
                        this.width[n2] = Integer.parseInt(s2);
                        break;
                    }
                    case 5: {
                        this.height[n2] = Integer.parseInt(s2);
                        break;
                    }
                    case 6: {
                        this.BorderStyle[this.state[n2]][n2] = Integer.parseInt(s2);
                        break;
                    }
                    case 7: {
                        this.state[n2] = Integer.parseInt(s2);
                        this.sProgramStuck += this.sProgram[this.state[n2]][n2];
                        this.playSound(n2, this.state[n2]);
                        this.stopSounds(n2, this.state[n2], false);
                        break;
                    }
                    case 8: {
                        int n3 = 1;
                        do {
                            this.bTransp[n3][n2] = (s2.compareTo("1") == 0);
                        } while (++n3 < 4);
                        break;
                    }
                    case 9: {
                        this.ShowHand[n2] = (s2.compareTo("1") == 0);
                        break;
                    }
                    case 10: {
                        this.StatusB[n2] = s2;
                        break;
                    }
                }
                if (n != 1 && Integer.parseInt(s2) == 0) {
                    this.bManPnt = true;
                }
                if (this.doRepaint) {
                    this.repaint();
                }
            }
        }
        catch (Exception ex) {
            this.pC("setParam(): " + ex);
        }
    }
    
    public void PrepareA() {
        try {
            System.gc();
            this.imgOffBuf = this.createImage(this.size().width, this.size().height);
            final String property = System.getProperty("java.version");
            if (property.startsWith("1.0")) {
                this.CoolJAVA = false;
            }
            if (this.CoolJAVA && this.AniGifPresent) {
                this.doRepaint = false;
            }
            final String lowerCase = System.getProperty("java.vendor").toLowerCase();
            if (this.CoolJAVA) {
                if (lowerCase.indexOf("netscape") != -1 && property.startsWith("1.1.2")) {
                    this.bAllowHC = false;
                }
            }
            else {
                this.bAllowHC = false;
                if (lowerCase.indexOf("microsoft") != -1) {
                    this.bDismTip = true;
                }
            }
            int n = 0;
            int n2 = 10;
            int i = 0;
            int n3 = 0;
            String string = "";
            this.AbackColor = new Color(Integer.parseInt("FFFFFF", 16));
            final String param = this.getParam("p", "");
            final char[] array = new char[param.length() + 1];
            final char[] charArray = param.toCharArray();
            while (i < param.length()) {
                if (param.charAt(i) == '|') {
                    final String myReplace = this.myReplace(this.myReplace(string, "&!3", "|"), "&!4", "^");
                    switch (n3) {
                        case 0: {
                            this.AbackColor = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 1: {
                            this.sLoading = myReplace;
                            break;
                        }
                        case 2: {
                            this.cLoadingFont = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 3: {
                            this.cLoadingBack = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 4: {
                            n = Integer.parseInt(myReplace);
                            break;
                        }
                        case 5: {
                            n2 = Integer.parseInt(myReplace);
                            break;
                        }
                        case 6: {
                            this.fLoadingFont = new Font(this.sFN[Integer.parseInt(myReplace)], n2, n);
                            break;
                        }
                        case 7: {
                            this.ButtonCount = Integer.parseInt(myReplace);
                            break;
                        }
                        case 8: {
                            this.AiFadeStyle = Integer.parseInt(myReplace);
                            break;
                        }
                        case 9: {
                            this.AcFadeColor = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 10: {
                            this.AppletProgram[3] = myReplace;
                            break;
                        }
                        case 11: {
                            this.sBackImg = myReplace;
                            break;
                        }
                        case 12: {
                            this.iBackImgType = Integer.parseInt(myReplace);
                            break;
                        }
                        case 13: {
                            this.ipar = Integer.parseInt(myReplace);
                            this.bClip = this.gp();
                            this.CV = !this.gp();
                            this.bPreLoad = this.gp();
                            this.bDoScroll = this.gp();
                            this.bNewP = this.gp();
                            this.AniGifPresent = this.gp();
                            break;
                        }
                        case 14: {
                            this.iDOWNBtn = Integer.parseInt(myReplace);
                            break;
                        }
                        case 15: {
                            this.AppletProgram[0] = myReplace;
                            break;
                        }
                        case 16: {
                            this.AppletProgram[1] = myReplace;
                            break;
                        }
                        case 17: {
                            this.AppletProgram[2] = myReplace;
                            break;
                        }
                        case 18: {
                            this.BgOffsetX = Integer.parseInt(myReplace);
                            break;
                        }
                        case 19: {
                            this.BgOffsetY = Integer.parseInt(myReplace);
                            break;
                        }
                        case 20: {
                            this.iDownDescentX = Integer.parseInt(myReplace);
                            break;
                        }
                        case 21: {
                            this.iDownDescentY = Integer.parseInt(myReplace);
                            break;
                        }
                        case 22: {
                            this.iLastScrlBtn = Integer.parseInt(myReplace);
                            break;
                        }
                        case 23: {
                            this.iScStep = Integer.parseInt(myReplace);
                            break;
                        }
                        case 24: {
                            this.gORIGScDelay = Integer.parseInt(myReplace);
                            this.gScDelay = this.gORIGScDelay + System.currentTimeMillis();
                            break;
                        }
                        case 25: {
                            this.iScDirection = Integer.parseInt(myReplace);
                            break;
                        }
                        case 26: {
                            this.cTipColor = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 27: {
                            this.cTipForeColor = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 28: {
                            this.cTipFrameColor = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                        case 29: {
                            this.iTipFW = Integer.parseInt(myReplace);
                            break;
                        }
                        case 30: {
                            this.iTipDelay = Integer.parseInt(myReplace) / this.iMSleepTime;
                            break;
                        }
                        case 31: {
                            this.iTipExp = Integer.parseInt(myReplace) / this.iMSleepTime;
                            break;
                        }
                        case 32: {
                            n = Integer.parseInt(myReplace);
                            break;
                        }
                        case 33: {
                            n2 = Integer.parseInt(myReplace);
                            break;
                        }
                        case 34: {
                            this.mTipFont = new Font(this.sFN[Integer.parseInt(myReplace)], n, n2);
                            this.fmTip = this.getFontMetrics(this.mTipFont);
                            this.lblTip.setFont(this.mTipFont);
                            break;
                        }
                        case 35: {
                            this.sImgPath = myReplace;
                            break;
                        }
                        case 36: {
                            this.sSndPath = myReplace;
                            break;
                        }
                        case 37: {
                            this.ABorderStyle = Integer.parseInt(myReplace);
                            break;
                        }
                        case 38: {
                            this.ABorderWidth = Integer.parseInt(myReplace);
                            break;
                        }
                        case 39: {
                            this.ABorderShade = Integer.parseInt(myReplace);
                            break;
                        }
                        case 40: {
                            this.ABorderColor = new Color(Integer.parseInt(myReplace, 16));
                            break;
                        }
                    }
                    string = "";
                    ++n3;
                }
                else {
                    string += charArray[i];
                }
                ++i;
            }
            this.bPnt = new boolean[this.ButtonCount];
            final int n4 = 4;
            this.BorderStyle = new int[n4][this.ButtonCount];
            this.BorderWidth = new int[n4][this.ButtonCount];
            this.BorderShade = new int[n4][this.ButtonCount];
            this.BorderColor = new Color[n4][this.ButtonCount];
            this.TFrameThick = new int[n4][this.ButtonCount];
            this.TFrameColor = new Color[n4][this.ButtonCount];
            this.TFrameLighter = new boolean[n4][this.ButtonCount];
            this.TFrameShade = new int[n4][this.ButtonCount];
            this.TFrameLeftToRight = new boolean[n4][this.ButtonCount];
            this.isDOWN = new boolean[this.ButtonCount];
            this.bRad = new boolean[this.ButtonCount];
            this.state = new int[this.ButtonCount];
            this.bTransp = new boolean[n4][this.ButtonCount];
            this.ShowHand = new boolean[this.ButtonCount];
            this.StatusB = new String[this.ButtonCount];
            this.sTip = new String[this.ButtonCount];
            this.sLabel = new String[n4][this.ButtonCount];
            this.iLblStyle = new int[n4][this.ButtonCount];
            this.iLblPosX = new int[n4][this.ButtonCount];
            this.iLblPosY = new int[n4][this.ButtonCount];
            this.img = new Image[n4][this.ButtonCount];
            this.iImgStyle = new int[n4][this.ButtonCount];
            this.snd = new AudioClip[n4][this.ButtonCount];
            this.sndStop = new boolean[n4][this.ButtonCount];
            this.sndLoop = new boolean[n4][this.ButtonCount];
            this.left = new int[this.ButtonCount];
            this.top = new int[this.ButtonCount];
            this.width = new int[this.ButtonCount];
            this.height = new int[this.ButtonCount];
            this.oldTop = new int[this.ButtonCount];
            this.oldLeft = new int[this.ButtonCount];
            this.hidden = new boolean[this.ButtonCount];
            this.mFont = new Font[n4][this.ButtonCount];
            this.backColor = new Color[n4][this.ButtonCount];
            this.fontColor = new Color[n4][this.ButtonCount];
            this.bURL = new URL[this.ButtonCount];
            this.noURL = new int[this.ButtonCount];
            this.sUrlTarget = new String[this.ButtonCount];
            this.sJavaScript = new String[this.ButtonCount];
            this.sProgram = new String[6][this.ButtonCount];
            this.cFadeColor = new Color[n4][this.ButtonCount];
            this.iFadeStyle = new int[n4][this.ButtonCount];
            this.iBFrameThick = new int[n4][this.ButtonCount];
            this.cBFrameColor = new Color[n4][this.ButtonCount];
            this.NIndex = new int[this.ButtonCount];
            this.sBName = new String[this.ButtonCount];
            this.iImgX = new int[n4][this.ButtonCount];
            this.iImgY = new int[n4][this.ButtonCount];
            this.lblTipFrame.setBackground(this.cTipFrameColor);
            this.lblTip.setBackground(this.cTipColor);
            this.lblTip.setForeground(this.cTipForeColor);
            this.lblTip.setAlignment(1);
            this.add(this.lblTip);
            this.add(this.lblTipFrame);
            this.lblTipFrame.hide();
            this.lblTip.hide();
            if (this.iBackImgType != 0) {
                this.imgBackImg = this.getImage(this.getCodeBase(), this.sImgPath + this.sBackImg);
            }
        }
        catch (Exception ex) {
            this.pC("PrepareA(): " + ex);
        }
    }
    
    public boolean myStartsWith(final String s, final String s2) {
        try {
            return s.toLowerCase().startsWith(s2.toLowerCase());
        }
        catch (Exception ex) {
            this.pC("myStartsWith(): " + ex);
            return false;
        }
    }
    
    public void start() {
        try {
            if (this.me == null || !this.me.isAlive()) {
                this.me = new Thread(this);
            }
            this.me.start();
        }
        catch (Exception ex) {
            this.pC("start(): " + ex);
        }
    }
    
    long mytime() {
        return System.currentTimeMillis() - this.AST;
    }
    
    boolean gp() {
        final boolean b = (this.ipar & 0x1) == 0x1;
        this.ipar >>= 1;
        return b;
    }
    
    private String ReplaceIlligal_In_Prog(String s) {
        s = this.myReplace(s, " ", "&!0");
        s = this.myReplace(s, ";", "&!1");
        s = this.myReplace(s, "\"", "&!2");
        return s;
    }
    
    private void paintBG() {
        this.imgBG = this.createImage(this.size().width, this.size().height);
        this.grImgBG = this.imgBG.getGraphics();
        if (this.AiFadeStyle == 0) {
            this.grImgBG.setColor(this.AbackColor);
            this.grImgBG.fillRect(0, 0, this.size().width, this.size().height);
        }
        else {
            this.Fade(this.grImgBG, this.AbackColor, this.AcFadeColor, this.ABorderWidth, this.ABorderWidth, this.size().width - this.ABorderWidth * 2, this.size().height - this.ABorderWidth * 2, this.AiFadeStyle);
        }
        switch (this.iBackImgType) {
            case 1: {
                this.grImgBG.drawImage(this.imgBackImg, this.BgOffsetX, this.BgOffsetY, this);
                break;
            }
            case 2: {
                this.grImgBG.drawImage(this.imgBackImg, this.size().width / 2 - this.imgBackImg.getWidth(this) / 2, this.size().height / 2 - this.imgBackImg.getHeight(this) / 2, this);
                break;
            }
            case 4: {
                this.grImgBG.drawImage(this.imgBackImg, this.BgOffsetX, this.BgOffsetY, this.size().width - this.BgOffsetX, this.size().height - this.BgOffsetY, this);
                break;
            }
            case 3: {
                this.grImgBG.drawImage(this.imgBackImg, this.BgOffsetX, this.BgOffsetY, this);
                if (this.imgBackImg.getHeight(this) > 0 && this.imgBackImg.getWidth(this) > 0) {
                    for (int i = this.BgOffsetY; i <= this.size().height; i += this.imgBackImg.getHeight(this)) {
                        for (int j = this.BgOffsetX; j <= this.size().width; j += this.imgBackImg.getWidth(this)) {
                            this.grImgBG.drawImage(this.imgBackImg, j, i, this);
                        }
                    }
                }
                break;
            }
        }
        this.Draw3DF(this.grImgBG, this.ABorderStyle, 0, 0, this.size().width, this.size().height, this.ABorderColor, this.ABorderShade, this.ABorderWidth);
    }
    
    public void playSound(final int n, final int n2) {
        if (this.snd[n2][n] != null) {
            if (this.sndLoop[n2][n]) {
                this.snd[n2][n].loop();
            }
            else {
                this.snd[n2][n].play();
            }
        }
    }
    
    public String getJS() {
        try {
            final String forGetJS = this.forGetJS;
            this.forGetJS = "null";
            return forGetJS;
        }
        catch (Exception ex) {
            this.pC("getJS(): " + ex);
            return "";
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        try {
            if (!this.DoneSettingButtons) {
                graphics.setColor(this.cLoadingBack);
                graphics.fillRect(0, 0, this.size().width, this.size().height);
                graphics.setColor(this.cLoadingFont);
                graphics.setFont(this.fLoadingFont);
                this.fmLoadingFont = this.getFontMetrics(this.fLoadingFont);
                graphics.drawString(this.sLoading, 10, this.fmLoadingFont.getHeight());
                return;
            }
            Image imgOffBuf = this.createImage(this.size().width, this.size().height);
            Graphics graphics2 = imgOffBuf.getGraphics();
            if (!this.bNewP || !this.bPnted) {
                graphics2.drawImage(this.imgBG, 0, 0, this);
            }
            if (this.bNewP && this.bPnted) {
                imgOffBuf = this.imgOffBuf;
            }
            this.bPnted = true;
            for (int i = 0; i < this.ButtonCount; ++i) {
                if ((!this.bNewP || !this.bPnt[i]) && !this.hidden[i]) {
                    this.bPnt[i] = true;
                    graphics2 = imgOffBuf.getGraphics();
                    if (this.bClip) {
                        final int n = this.iBFrameThick[this.state[i]][i];
                        graphics2.clipRect(this.left[i] - n, this.top[i] - n, this.width[i] + n * 2, this.height[i] + n * 2);
                    }
                    if (!this.bTransp[this.state[i]][i]) {
                        if (this.iFadeStyle[this.state[i]][i] == 0) {
                            graphics2.setColor(this.backColor[this.state[i]][i]);
                            graphics2.fillRect(this.left[i], this.top[i], this.width[i], this.height[i]);
                        }
                        else {
                            this.Fade(graphics2, this.backColor[this.state[i]][i], this.cFadeColor[this.state[i]][i], this.left[i] + this.BorderWidth[this.state[i]][i], this.top[i] + this.BorderWidth[this.state[i]][i], this.width[i] - this.BorderWidth[this.state[i]][i] * 2, this.height[i] - this.BorderWidth[this.state[i]][i] * 2, this.iFadeStyle[this.state[i]][i]);
                        }
                    }
                    if (this.img[this.state[i]][i] != null) {
                        switch (this.iImgStyle[this.state[i]][i]) {
                            case 0: {
                                graphics2.drawImage(this.img[this.state[i]][i], this.left[i] + this.width[i] / 2 - this.img[this.state[i]][i].getWidth(this) / 2, this.top[i] + this.height[i] / 2 - this.img[this.state[i]][i].getHeight(this) / 2, this);
                                break;
                            }
                            case 1: {
                                graphics2.drawImage(this.img[this.state[i]][i], this.left[i] + this.iImgX[this.state[i]][i], this.top[i] + this.iImgY[this.state[i]][i], this);
                                break;
                            }
                            case 2: {
                                graphics2.drawImage(this.img[this.state[i]][i], this.left[i], this.top[i], this.width[i], this.height[i], this);
                                break;
                            }
                        }
                    }
                    this.Draw3DF(graphics2, this.BorderStyle[this.state[i]][i], this.left[i], this.top[i], this.width[i], this.height[i], this.BorderColor[this.state[i]][i], this.BorderShade[this.state[i]][i], this.BorderWidth[this.state[i]][i]);
                    graphics2.setFont(this.mFont[this.state[i]][i]);
                    final FontMetrics fontMetrics = this.getFontMetrics(this.mFont[this.state[i]][i]);
                    graphics2.setColor(this.fontColor[this.state[i]][i]);
                    final int n2 = fontMetrics.getAscent() - fontMetrics.getDescent();
                    if (this.iLblStyle[this.state[i]][i] == 0) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(this.sLabel[this.state[i]][i], "\n");
                        int n3;
                        if (stringTokenizer.countTokens() > 1) {
                            n3 = this.top[i] + (this.height[i] / 2 - fontMetrics.getHeight() * (stringTokenizer.countTokens() - 1) / 3);
                        }
                        else {
                            n3 = this.top[i] + (this.height[i] / 2 + fontMetrics.getHeight() / 3);
                        }
                        if (this.state[i] == 3) {
                            n3 += this.iDownDescentY;
                        }
                        while (stringTokenizer.hasMoreTokens()) {
                            final String s = new String(stringTokenizer.nextToken());
                            int n4 = this.left[i] + this.width[i] / 2 - fontMetrics.stringWidth(s) / 2;
                            if (this.state[i] == 3) {
                                n4 += this.iDownDescentX;
                            }
                            this.drawTFrame(i, graphics2, s, n4, n3, this.TFrameThick[this.state[i]][i], this.TFrameColor[this.state[i]][i], this.TFrameLighter[this.state[i]][i], this.TFrameShade[this.state[i]][i], this.TFrameLeftToRight[this.state[i]][i]);
                            graphics2.drawString(s, n4, n3);
                            n3 += fontMetrics.getHeight() / 3 + n2;
                        }
                    }
                    else {
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.sLabel[this.state[i]][i], "\n");
                        int n5 = this.top[i] + this.iLblPosY[this.state[i]][i];
                        while (stringTokenizer2.hasMoreTokens()) {
                            final String s2 = new String(stringTokenizer2.nextToken());
                            final int n6 = this.left[i] + this.iLblPosX[this.state[i]][i];
                            this.drawTFrame(i, graphics2, s2, n6, n5, this.TFrameThick[this.state[i]][i], this.TFrameColor[this.state[i]][i], this.TFrameLighter[this.state[i]][i], this.TFrameShade[this.state[i]][i], this.TFrameLeftToRight[this.state[i]][i]);
                            graphics2.drawString(s2, n6, n5);
                            n5 += fontMetrics.getHeight() / 3 + n2;
                        }
                    }
                    this.BFrame(graphics2, this.cBFrameColor[this.state[i]][i], this.left[i], this.top[i], this.width[i] - 1, this.height[i] - 1, this.iBFrameThick[this.state[i]][i]);
                    graphics2.dispose();
                }
            }
            graphics.drawImage(imgOffBuf, 0, 0, this);
            if (this.bNewP) {
                this.imgOffBuf = imgOffBuf;
            }
            graphics2.dispose();
            imgOffBuf.flush();
            System.gc();
        }
        catch (Exception ex) {
            this.pC("paint(): " + ex);
        }
    }
    
    public MagicButton() {
        this.sUR = "https://www.regnow.com/softsell/nph-softsell.cgi?item=2189-1";
        this.sREG = "UNREGISTERED";
        this.sImgPath = "";
        this.sSndPath = "";
        this.sFN = new String[] { "Helvetica", "Courier", "TimesRoman", "Dialog" };
        this.bClip = false;
        this.bPreLoad = false;
        this.AST = 0L;
        this.gWaitFor = 0L;
        this.iMSleepTime = 20;
        this.sProgramStuck = "";
        this.bNewP = false;
        this.bPnted = false;
        this.bManPnt = false;
        this.bDismTip = false;
        this.CoolJAVA = true;
        this.bAllowHC = true;
        this.AniGifPresent = false;
        this.doRepaint = true;
        this.bDoScroll = false;
        this.gScDelay = 0L;
        this.gORIGScDelay = 0L;
        this.iMouseX = -1;
        this.iMouseY = -1;
        this.iLastScrlBtn = 0;
        this.iScStep = 1;
        this.iScDirection = 4;
        this.smTip = "";
        this.lblTip = new Label("");
        this.lblTipFrame = new Label("");
        this.iTipFW = 1;
        this.mTipFont = new Font(this.sFN[0], 0, 11);
        this.fmTip = this.getFontMetrics(this.mTipFont);
        this.cTipColor = new Color(255, 255, 0);
        this.cTipForeColor = new Color(0, 0, 0);
        this.cTipFrameColor = new Color(0, 0, 255);
        this.iOldMouseX = -1;
        this.iOldMouseY = -1;
        this.bShowingTip = false;
        this.iSetTipX = -1;
        this.iSetTipY = -1;
        this.iTipC = 0;
        this.iTipDelay = 30;
        this.iTipExp = 200;
        this.DoneSettingButtons = false;
        this.nL = "\\n";
        this.forGetJS = "null";
        this.MouseEnteredTo = -1;
        this.CV = false;
        this.ButtonCount = 0;
        this.CurrentID = -1;
        this.PressedBtn = -1;
        this.iBackImgType = 0;
        this.BgOffsetX = 0;
        this.BgOffsetY = 0;
        this.AppletProgram = new String[4];
        this.bAPMO = false;
        this.bAPS = false;
        this.ABorderStyle = 1;
        this.ABorderWidth = 10;
        this.ABorderShade = 10;
        this.ABorderColor = Color.black;
        this.AcFadeColor = Color.blue;
        this.AiFadeStyle = 0;
        this.sLoading = "Loading...";
        this.cLoadingFont = new Color(0, 0, 255);
        this.cLoadingBack = new Color(255, 255, 255);
        this.fLoadingFont = new Font(this.sFN[0], 0, 11);
        this.fmLoadingFont = this.getFontMetrics(this.fLoadingFont);
    }
    
    public void setColor(final String s, final int n, final String s2) {
        try {
            for (int setNIndex = this.SetNIndex(s), i = 0; i < setNIndex; ++i) {
                this.backColor[n][this.NIndex[i]] = new Color(Integer.parseInt(s2, 16));
                this.bManPnt = true;
            }
            if (this.doRepaint) {
                this.repaint();
            }
        }
        catch (Exception ex) {
            this.pC("setColor(): " + ex);
        }
    }
    
    public void setVisible(final String s, final int n) {
        this.setParam(s, 1, Integer.toString(n));
    }
    
    public int getVisible(final String s) {
        this.SetNIndex(s);
        if (this.hidden[this.NIndex[0]]) {
            return 0;
        }
        return 1;
    }
    
    void loadImg(final Image image) {
        try {
            if (image == null) {
                return;
            }
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            this.pC("loadImg(): " + ex);
        }
    }
    
    private String decodeBlink(final String s, final int n, final String s2) {
        String string = "";
        int n2 = 0;
        String string2;
        while (true) {
            ++n2;
            string2 = string + "2 " + s + ";6 " + s2 + ";1 " + s + ";";
            if (n2 >= n) {
                break;
            }
            string = string2 + "6 " + s2 + ";";
        }
        return string2;
    }
    
    public void DoScroll() {
        if (!this.DoneSettingButtons) {
            return;
        }
        if (System.currentTimeMillis() >= this.gScDelay) {
            this.gScDelay = this.gORIGScDelay + System.currentTimeMillis();
            final Event event = null;
            for (int i = 0; i < this.ButtonCount; ++i) {
                switch (this.iScDirection) {
                    case 0: {
                        this.top[i] -= this.iScStep;
                        break;
                    }
                    case 1: {
                        this.top[i] += this.iScStep;
                        break;
                    }
                    case 2: {
                        this.left[i] -= this.iScStep;
                        break;
                    }
                    case 3: {
                        this.left[i] += this.iScStep;
                        break;
                    }
                }
                if ((this.iScDirection == 0 && this.top[i] + this.height[i] < 0 && i == this.iLastScrlBtn) || (this.iScDirection == 1 && this.top[i] > this.size().height && i == this.iLastScrlBtn)) {
                    for (int j = 0; j < this.ButtonCount; ++j) {
                        this.top[j] = this.oldTop[j];
                    }
                }
                if ((this.iScDirection == 2 && this.left[i] + this.width[i] < 0 && i == this.iLastScrlBtn) || (this.iScDirection == 3 && this.left[i] > this.size().width && i == this.iLastScrlBtn)) {
                    for (int k = 0; k < this.ButtonCount; ++k) {
                        this.left[k] = this.oldLeft[k];
                    }
                }
            }
            if (this.iMouseX != -1 && this.iMouseY != -1) {
                this.mouseMove(event, this.iMouseX, this.iMouseY);
            }
            if (this.doRepaint) {
                this.repaint();
            }
        }
    }
    
    public void GOURL() {
        try {
            if (this.noURL[this.CurrentID] == 1 || this.hidden[this.CurrentID]) {
                return;
            }
            if (this.sJavaScript[this.CurrentID] != null) {
                this.DoJavaScript(this.sJavaScript[this.CurrentID]);
                return;
            }
            if (this.bURL[this.CurrentID] != null) {
                this.getAppletContext().showDocument(this.bURL[this.CurrentID], this.sUrlTarget[this.CurrentID]);
            }
        }
        catch (Exception ex) {
            this.pC("GOURL(): " + ex);
        }
    }
    
    public String myReplace(String string, final String s, final String s2) {
        try {
            if (string.indexOf(s) == -1) {
                return string;
            }
            final String s3 = new String();
            final String s4 = new String();
            for (int i = 0; i < string.length(); ++i) {
                if (string.regionMatches(i, s, 0, s.length())) {
                    string = string.substring(0, i) + s2 + string.substring(i + s.length());
                }
            }
            return string;
        }
        catch (Exception ex) {
            this.pC("myReplace(): " + ex);
            return "";
        }
    }
    
    private int myInt(final Float n) {
        final int intValue = (int)(Object)n;
        int n2 = 1;
        if (intValue < 0) {
            n2 = -1;
        }
        if (n - new Float(intValue) < 0.5) {
            return intValue;
        }
        return intValue + n2;
    }
    
    void Fade(final Graphics graphics, final Color color, final Color color2, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n5 == 0) {
            return;
        }
        switch (n5) {
            case 1: {
                this.Vycvet(graphics, color, color2, n, n2, n3, n4, n5);
                break;
            }
            case 2: {
                this.Vycvet(graphics, color, color2, n, n2, n3, n4 + 1, n5);
                break;
            }
            case 3: {
                this.Vycvet(graphics, color, color2, n, n2, n3 / 2, n4, 1);
                this.Vycvet(graphics, color2, color, n + n3 / 2, n2, n3 / 2 + 1, n4, 1);
                break;
            }
            case 4: {
                this.Vycvet(graphics, color, color2, n, n2, n3, n4 / 2, 2);
                this.Vycvet(graphics, color2, color, n, n2 + n4 / 2, n3, n4 / 2 + 1, 2);
                break;
            }
        }
    }
    
    Color STC(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        Color color;
        if (b) {
            color = new Color(Math.max(0, Math.min(255, n3 + (n * n2 - n6 * n))), Math.max(0, Math.min(255, n4 + (n * n2 - n6 * n))), Math.max(0, Math.min(255, n5 + (n * n2 - n6 * n))));
        }
        else {
            color = new Color(Math.min(255, Math.max(0, n3 - (n * n2 - n6 * n))), Math.min(255, Math.max(0, n4 - (n * n2 - n6 * n))), Math.min(255, Math.max(0, n5 - (n * n2 - n6 * n))));
        }
        return color;
    }
    
    public synchronized void DePress() {
        try {
            if (this.PressedBtn != -1 && this.PressedBtn != this.CurrentID) {
                this.isDOWN[this.PressedBtn] = false;
                this.state[this.PressedBtn] = 1;
                this.bManPnt = true;
                if (this.bRad[this.PressedBtn] && this.bNewP) {
                    this.bPnt[this.PressedBtn] = false;
                }
                if (this.doRepaint) {
                    this.repaint();
                }
                this.playSound(this.PressedBtn, 1);
                this.stopSounds(this.PressedBtn, 1, true);
                this.sProgramStuck += this.sProgram[1][this.PressedBtn];
                this.sProgramStuck += this.sProgram[5][this.PressedBtn];
            }
        }
        catch (Exception ex) {
            this.pC("DePress(): " + ex);
        }
    }
    
    void BFrame(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n5 == 0) {
            return;
        }
        graphics.setColor(color);
        for (int i = 1; i <= n5; ++i) {
            graphics.drawRect(n - i, n2 - i, n3 + i * 2, n4 + i * 2);
        }
    }
}
