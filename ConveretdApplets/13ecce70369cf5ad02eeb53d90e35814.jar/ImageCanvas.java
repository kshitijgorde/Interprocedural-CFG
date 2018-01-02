import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageCanvas extends Canvas
{
    private Image[] myImage;
    private ChordAnalyser chordAnalyser;
    private final int OFFSET_XPOS = 20;
    private final int FRET_DISTANCE = 26;
    private final int C_NOTE = 0;
    private final int CIS_NOTE = 1;
    private final int D_NOTE = 2;
    private final int DIS_NOTE = 3;
    private final int E_NOTE = 4;
    private final int F_NOTE = 5;
    private final int FIS_NOTE = 6;
    private final int G_NOTE = 7;
    private final int GIS_NOTE = 8;
    private final int A_NOTE = 9;
    private final int AIS_NOTE = 10;
    private final int B_NOTE = 11;
    public int nBase;
    public int nSec;
    public int nThird;
    public int nFourth;
    public int nFifth;
    public int nSixth;
    private int nSelectIntervalsArray;
    int nBa;
    int nSe;
    int nTh;
    int nFo;
    int nFi;
    int nSi;
    private int nLastStringMouseClick;
    private int nLastMidiNote;
    private int nBeginFretPos;
    private int nEndFretPos;
    private boolean bShowAllNotes;
    private boolean bShowNotes;
    private boolean bShowIntervals;
    private boolean bShowScope;
    private boolean bResetChordResults;
    private boolean bLeftHand;
    private boolean bUserNotes;
    private boolean bScaleList;
    private boolean bMousePosChanged;
    private boolean bSwitchEvent;
    private String[] noteArr;
    private int[][] userArr;
    private int[] fretPos;
    private String userResult;
    public int[][] mouseLastPos;
    private int[][] mousePosArr;
    
    public ImageCanvas(final Image[] myImage, final ChordAnalyser chordAnalyser) {
        this.nBase = 4;
        this.nSec = 9;
        this.nThird = 2;
        this.nFourth = 7;
        this.nFifth = 11;
        this.nSixth = 4;
        this.nSelectIntervalsArray = 0;
        this.nLastStringMouseClick = -1;
        this.nLastMidiNote = -1;
        this.bShowAllNotes = false;
        this.bShowNotes = true;
        this.bShowIntervals = false;
        this.bShowScope = false;
        this.bResetChordResults = false;
        this.bLeftHand = false;
        this.bUserNotes = false;
        this.bScaleList = false;
        this.bMousePosChanged = true;
        this.bSwitchEvent = false;
        this.noteArr = null;
        this.myImage = myImage;
        this.chordAnalyser = chordAnalyser;
        this.setCursor(new Cursor(12));
        this.userArr = new int[6][5];
        this.mousePosArr = new int[1][5];
        this.mouseLastPos = new int[1][5];
        this.resetUserArray();
        this.fretPos = new int[6];
        this.resetFretPositions();
        this.nBeginFretPos = 0;
        this.nEndFretPos = 286;
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(myImage[0], 0);
        mediaTracker.addImage(myImage[1], 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
    
    public void update(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.deleteArea(graphics2D, 17, 48, 115, 12);
        if (!this.bLeftHand) {
            graphics2D.drawImage(this.myImage[0], 9, 60, this);
        }
        else {
            graphics2D.drawImage(this.myImage[1], 9, 60, this);
        }
        this.paintTuning(graphics2D);
        if (this.bShowAllNotes) {
            this.showAllNotes(graphics2D);
        }
        if (this.bUserNotes) {
            this.showUserNotes();
        }
        if (this.bShowNotes) {
            this.paintNoteAnalyserResult();
        }
        if (!this.bUserNotes && this.bShowIntervals) {
            this.paintIntervalAnalyserResult();
        }
        if (this.bUserNotes) {
            this.paintUserNotes();
        }
        else {
            this.updateOpenStrings();
        }
        this.paintOpenStrings();
        if (this.bShowScope) {
            this.paintScope();
        }
    }
    
    public int[] getMidiChordValues() {
        if (this.bUserNotes) {
            return this.getUserMidiNotes();
        }
        return this.chordAnalyser.getMidiValues();
    }
    
    public int getLastMidiNote() {
        if (this.nLastStringMouseClick >= 0 && this.nLastStringMouseClick < 6) {
            this.getUserMidiNotes();
            return this.nLastMidiNote;
        }
        return -1;
    }
    
    private int[] getUserMidiNotes() {
        final int[] array = { -1, -1, -1, -1, -1, -1 };
        if (this.userArr[0][0] != -1) {
            if (this.fretPos[0] + 1 > 0 && this.userArr[0][0] <= this.nBase) {
                array[0] = this.userArr[0][0] + 48;
            }
            else {
                array[0] = this.userArr[0][0] + 36;
            }
        }
        if (this.userArr[1][0] != -1) {
            if (this.fretPos[1] + 1 > 0 && this.userArr[1][0] <= this.nSec) {
                array[1] = this.userArr[1][0] + 48;
            }
            else {
                array[1] = this.userArr[1][0] + 36;
            }
        }
        if (this.userArr[2][0] != -1) {
            if (this.fretPos[2] + 1 > 0 && this.userArr[2][0] <= this.nThird) {
                array[2] = this.userArr[2][0] + 60;
            }
            else {
                array[2] = this.userArr[2][0] + 48;
            }
        }
        if (this.userArr[3][0] != -1) {
            if (this.fretPos[3] + 1 > 0 && this.userArr[3][0] <= this.nFourth) {
                array[3] = this.userArr[3][0] + 60;
            }
            else {
                array[3] = this.userArr[3][0] + 48;
            }
        }
        if (this.userArr[4][0] != -1) {
            if (this.fretPos[4] + 1 > 0 && this.userArr[4][0] <= this.nFifth) {
                array[4] = this.userArr[4][0] + 60;
            }
            else {
                array[4] = this.userArr[4][0] + 48;
            }
        }
        if (this.userArr[5][0] != -1) {
            if (this.fretPos[5] + 1 > 0 && this.userArr[5][0] <= this.nSixth) {
                array[5] = this.userArr[5][0] + 72;
            }
            else {
                array[5] = this.userArr[5][0] + 60;
            }
        }
        int n = 0;
        for (int i = 0; i < 6; ++i) {
            if (array[i] != -1) {
                ++n;
            }
        }
        final int[] array2 = new int[n];
        int j = 0;
        int n2 = 0;
        while (j < 6) {
            if (array[j] != -1) {
                array2[n2++] = array[j];
            }
            ++j;
        }
        this.nLastMidiNote = array[this.nLastStringMouseClick];
        return array2;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void setChordAnalyser(final ChordAnalyser chordAnalyser) {
        this.chordAnalyser = chordAnalyser;
    }
    
    private boolean isNoteEqual(final String[] array, final String s) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int getCenteredHorizontPos(final int n, final int n2, final int n3) {
        return n + (n2 - n3) / 2 + 1;
    }
    
    private int getCenterVerticalPos(final int n, final int n2, final int n3) {
        return n + n2 - (n2 - n3) / 2 - 2;
    }
    
    public void setDisplaySize(final int n, int n2) {
        n2 = n2 + n - 1;
        if (n2 > 12) {
            n2 = 12;
        }
        this.nBeginFretPos = (n - 1) * 26;
        this.nEndFretPos = (n2 - 1) * 26;
    }
    
    public void paintScope() {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        graphics2D.setColor(Color.gray);
        graphics2D.fillRoundRect(12, this.nBeginFretPos + 60, 125, 10, 10, 10);
        graphics2D.setColor(Color.lightGray);
        graphics2D.fillRoundRect(14, this.nBeginFretPos + 62, 122, 5, 10, 10);
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(16, this.nBeginFretPos + 64, 119, 2);
        graphics2D.setColor(Color.gray);
        graphics2D.fillRoundRect(12, this.nEndFretPos + 93, 125, 10, 10, 10);
        graphics2D.setColor(Color.lightGray);
        graphics2D.fillRoundRect(14, this.nEndFretPos + 95, 122, 5, 10, 10);
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(16, this.nEndFretPos + 97, 119, 2);
    }
    
    public void resetUserArray() {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 6; ++j) {
                this.userArr[j][i] = -1;
            }
        }
    }
    
    public void resetFretPositions() {
        for (int i = 0; i < this.fretPos.length; ++i) {
            this.fretPos[i] = -1;
        }
    }
    
    public boolean isUserNote() {
        return this.bUserNotes;
    }
    
    public void updateUserNote(final int n, final boolean b) {
        if (n != -1 && this.userArr[n][0] != -1) {
            if (b) {
                this.userArr[n][0] = this.incrementNote(this.userArr[n][0]);
            }
            else {
                this.userArr[n][0] = this.decrementNote(this.userArr[n][0]);
            }
            this.bSwitchEvent = true;
            this.updateAllUserNotes();
        }
    }
    
    private void setFretPosition(final int n, final int n2) {
        this.fretPos[n] = n2;
    }
    
    public void switchUserPositions() {
        final int n = this.fretPos[0];
        final int n2 = this.fretPos[1];
        final int n3 = this.fretPos[2];
        final int n4 = this.fretPos[3];
        final int n5 = this.fretPos[4];
        final int n6 = this.fretPos[5];
        this.fretPos[5] = n;
        this.fretPos[4] = n2;
        this.fretPos[3] = n3;
        this.fretPos[2] = n4;
        this.fretPos[1] = n5;
        this.fretPos[0] = n6;
        final int n7 = this.userArr[0][0];
        this.userArr[0][0] = this.userArr[5][0];
        this.userArr[5][0] = n7;
        final int n8 = this.userArr[1][0];
        this.userArr[1][0] = this.userArr[4][0];
        this.userArr[4][0] = n8;
        final int n9 = this.userArr[3][0];
        this.userArr[3][0] = this.userArr[2][0];
        this.userArr[2][0] = n9;
        this.bSwitchEvent = true;
        this.updateAllUserNotes();
    }
    
    public void updateAllUserNotes() {
        for (int i = 0; i < 6; ++i) {
            this.setUserNote(i + 1, this.fretPos[i] + 1);
        }
        this.bSwitchEvent = false;
        this.updateUserResult();
    }
    
    public void updateUserResult() {
        this.userResult = "";
        for (int i = 0; i < 6; ++i) {
            if (this.userArr[i][0] != -1) {
                this.userResult += this.chordAnalyser.getNoteFromSharpArr(this.userArr[i][0]);
            }
        }
        this.userResult = this.chordAnalyser.analyseChordNotes(this.userResult);
    }
    
    public void setMousePosition(final int n, final int n2) {
        this.nBa = this.nBase;
        this.nSe = this.nSec;
        this.nTh = this.nThird;
        this.nFo = this.nFourth;
        this.nFi = this.nFifth;
        this.nSi = this.nSixth;
        final int n3 = 17;
        final int n4 = 72;
        final int n5 = 26 * n2 - 26;
        final int n6 = (n - 1) * 20;
        if (n2 > 0) {
            switch (n) {
                case 1: {
                    for (int i = 0; i < n2; ++i) {
                        this.nBa = this.incrementNote(this.nBa);
                    }
                    this.mousePosArr[0][0] = this.nBa;
                    this.mousePosArr[0][1] = n3;
                    this.mousePosArr[0][2] = n4;
                    this.mousePosArr[0][3] = n6;
                    this.mousePosArr[0][4] = n5;
                    break;
                }
                case 2: {
                    for (int j = 0; j < n2; ++j) {
                        this.nSe = this.incrementNote(this.nSe);
                    }
                    this.mousePosArr[0][0] = this.nSe;
                    this.mousePosArr[0][1] = n3;
                    this.mousePosArr[0][2] = n4;
                    this.mousePosArr[0][3] = n6;
                    this.mousePosArr[0][4] = n5;
                    break;
                }
                case 3: {
                    for (int k = 0; k < n2; ++k) {
                        this.nTh = this.incrementNote(this.nTh);
                    }
                    this.mousePosArr[0][0] = this.nTh;
                    this.mousePosArr[0][1] = n3;
                    this.mousePosArr[0][2] = n4;
                    this.mousePosArr[0][3] = n6;
                    this.mousePosArr[0][4] = n5;
                    break;
                }
                case 4: {
                    for (int l = 0; l < n2; ++l) {
                        this.nFo = this.incrementNote(this.nFo);
                    }
                    this.mousePosArr[0][0] = this.nFo;
                    this.mousePosArr[0][1] = n3;
                    this.mousePosArr[0][2] = n4;
                    this.mousePosArr[0][3] = n6;
                    this.mousePosArr[0][4] = n5;
                    break;
                }
                case 5: {
                    for (int n7 = 0; n7 < n2; ++n7) {
                        this.nFi = this.incrementNote(this.nFi);
                    }
                    this.mousePosArr[0][0] = this.nFi;
                    this.mousePosArr[0][1] = n3;
                    this.mousePosArr[0][2] = n4;
                    this.mousePosArr[0][3] = n6;
                    this.mousePosArr[0][4] = n5;
                    break;
                }
                case 6: {
                    for (int n8 = 0; n8 < n2; ++n8) {
                        this.nSi = this.incrementNote(this.nSi);
                    }
                    this.mousePosArr[0][0] = this.nSi;
                    this.mousePosArr[0][1] = n3;
                    this.mousePosArr[0][2] = n4;
                    this.mousePosArr[0][3] = n6;
                    this.mousePosArr[0][4] = n5;
                    break;
                }
            }
        }
        if (this.mouseLastPos != this.mousePosArr) {
            this.repaint(this.mouseLastPos[0][3] + 20 - 3, this.mouseLastPos[0][4] + 72, 21, 21);
            this.bMousePosChanged = true;
            for (int n9 = 0; n9 < 5; ++n9) {
                this.mouseLastPos[0][n9] = this.mousePosArr[0][n9];
            }
            this.paintNoteOnMousePosition();
        }
        else {
            this.bMousePosChanged = false;
        }
    }
    
    public void paintNoteOnMousePosition() {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        final int n = 16;
        final int n2 = 16;
        graphics2D.setFont(new Font("SansSerif", 1, 11));
        graphics2D.getFont();
        if (this.mousePosArr[0][0] != -1 && this.mousePosArr[0][1] != 0 && this.mousePosArr[0][2] != 0) {
            final FontMetrics fontMetrics = graphics2D.getFontMetrics();
            graphics2D.setColor(Color.red);
            graphics2D.fillOval(this.mousePosArr[0][1] + this.mousePosArr[0][3] + 1, 2 + this.mousePosArr[0][2] + this.mousePosArr[0][4], n, n2);
            graphics2D.setColor(Color.white);
            graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.mousePosArr[0][0]), this.mousePosArr[0][3] + this.getCenteredHorizontPos(this.mousePosArr[0][1], n, fontMetrics.stringWidth(this.chordAnalyser.getNoteFromSharpArr(this.mousePosArr[0][0]))), this.mousePosArr[0][4] + this.getCenterVerticalPos(this.mousePosArr[0][2] + 2, n2, fontMetrics.getAscent()));
        }
    }
    
    public boolean isMousePositionChanged() {
        return this.bMousePosChanged;
    }
    
    public void setUserNote(final int n, final int n2) {
        this.nBa = this.nBase;
        this.nSe = this.nSec;
        this.nTh = this.nThird;
        this.nFo = this.nFourth;
        this.nFi = this.nFifth;
        this.nSi = this.nSixth;
        this.nLastStringMouseClick = n - 1;
        final int n3 = 17;
        final int n4 = 72;
        final int n5 = this.nBeginFretPos - 26 + 26 * n2;
        final int n6 = (n - 1) * 20;
        if (n2 > 0) {
            switch (n) {
                case 1: {
                    for (int i = 0; i < n2; ++i) {
                        this.nBa = this.incrementNote(this.nBa);
                    }
                    if (this.userArr[0][0] != this.nBa || this.bSwitchEvent) {
                        this.userArr[0][0] = this.nBa;
                        this.userArr[0][1] = n3;
                        this.userArr[0][2] = n4;
                        this.userArr[0][3] = n6;
                        this.userArr[0][4] = n5;
                        this.setFretPosition(n - 1, n2 - 1);
                        break;
                    }
                    for (int j = 0; j < 5; ++j) {
                        this.userArr[0][j] = -1;
                    }
                    this.fretPos[0] = -1;
                    break;
                }
                case 2: {
                    for (int k = 0; k < n2; ++k) {
                        this.nSe = this.incrementNote(this.nSe);
                    }
                    if (this.userArr[1][0] != this.nSe || this.bSwitchEvent) {
                        this.userArr[1][0] = this.nSe;
                        this.userArr[1][1] = n3;
                        this.userArr[1][2] = n4;
                        this.userArr[1][3] = n6;
                        this.userArr[1][4] = n5;
                        this.setFretPosition(n - 1, n2 - 1);
                        break;
                    }
                    for (int l = 0; l < 5; ++l) {
                        this.userArr[1][l] = -1;
                    }
                    this.fretPos[1] = -1;
                    break;
                }
                case 3: {
                    for (int n7 = 0; n7 < n2; ++n7) {
                        this.nTh = this.incrementNote(this.nTh);
                    }
                    if (this.userArr[2][0] != this.nTh || this.bSwitchEvent) {
                        this.userArr[2][0] = this.nTh;
                        this.userArr[2][1] = n3;
                        this.userArr[2][2] = n4;
                        this.userArr[2][3] = n6;
                        this.userArr[2][4] = n5;
                        this.setFretPosition(n - 1, n2 - 1);
                        break;
                    }
                    for (int n8 = 0; n8 < 5; ++n8) {
                        this.userArr[2][n8] = -1;
                    }
                    this.fretPos[2] = -1;
                    break;
                }
                case 4: {
                    for (int n9 = 0; n9 < n2; ++n9) {
                        this.nFo = this.incrementNote(this.nFo);
                    }
                    if (this.userArr[3][0] != this.nFo || this.bSwitchEvent) {
                        this.userArr[3][0] = this.nFo;
                        this.userArr[3][1] = n3;
                        this.userArr[3][2] = n4;
                        this.userArr[3][3] = n6;
                        this.userArr[3][4] = n5;
                        this.setFretPosition(n - 1, n2 - 1);
                        break;
                    }
                    for (int n10 = 0; n10 < 5; ++n10) {
                        this.userArr[3][n10] = -1;
                    }
                    this.fretPos[3] = -1;
                    break;
                }
                case 5: {
                    for (int n11 = 0; n11 < n2; ++n11) {
                        this.nFi = this.incrementNote(this.nFi);
                    }
                    if (this.userArr[4][0] != this.nFi || this.bSwitchEvent) {
                        this.userArr[4][0] = this.nFi;
                        this.userArr[4][1] = n3;
                        this.userArr[4][2] = n4;
                        this.userArr[4][3] = n6;
                        this.userArr[4][4] = n5;
                        this.setFretPosition(n - 1, n2 - 1);
                        break;
                    }
                    for (int n12 = 0; n12 < 5; ++n12) {
                        this.userArr[4][n12] = -1;
                    }
                    this.fretPos[4] = -1;
                    break;
                }
                case 6: {
                    for (int n13 = 0; n13 < n2; ++n13) {
                        this.nSi = this.incrementNote(this.nSi);
                    }
                    if (this.userArr[5][0] != this.nSi || this.bSwitchEvent) {
                        this.userArr[5][0] = this.nSi;
                        this.userArr[5][1] = n3;
                        this.userArr[5][2] = n4;
                        this.userArr[5][3] = n6;
                        this.userArr[5][4] = n5;
                        this.setFretPosition(n - 1, n2 - 1);
                        break;
                    }
                    for (int n14 = 0; n14 < 5; ++n14) {
                        this.userArr[5][n14] = -1;
                    }
                    this.fretPos[5] = -1;
                    break;
                }
            }
        }
    }
    
    private void paintUserNotes() {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        final int n = 20;
        final int n2 = 20;
        graphics2D.setFont(new Font("SansSerif", 1, 12));
        final Font font = graphics2D.getFont();
        final Color color = new Color(255, 255, 255);
        final Color color2 = new Color(0, 0, 0);
        for (int i = 0; i < 6; ++i) {
            if (this.userArr[i][0] != -1 && this.fretPos[i] > -1) {
                this.paintNote(graphics2D, font, color, color2, "", this.userArr[i][0], this.userArr[i][1], this.userArr[i][2], this.userArr[i][3], this.userArr[i][4], n, n2, 1);
            }
        }
    }
    
    public String getUserResult() {
        return this.userResult;
    }
    
    public void setOpenStringStatus(final int n) {
        switch (n) {
            case 1: {
                if (this.userArr[0][0] == this.nBase) {
                    this.userArr[0][0] = -1;
                }
                else {
                    this.userArr[0][0] = this.nBase;
                }
                this.setFretPosition(n - 1, -1);
                break;
            }
            case 2: {
                if (this.userArr[1][0] == this.nSec) {
                    this.userArr[1][0] = -1;
                }
                else {
                    this.userArr[1][0] = this.nSec;
                }
                this.setFretPosition(n - 1, -1);
                break;
            }
            case 3: {
                if (this.userArr[2][0] == this.nThird) {
                    this.userArr[2][0] = -1;
                }
                else {
                    this.userArr[2][0] = this.nThird;
                }
                this.setFretPosition(n - 1, -1);
                break;
            }
            case 4: {
                if (this.userArr[3][0] == this.nFourth) {
                    this.userArr[3][0] = -1;
                }
                else {
                    this.userArr[3][0] = this.nFourth;
                }
                this.setFretPosition(n - 1, -1);
                break;
            }
            case 5: {
                if (this.userArr[4][0] == this.nFifth) {
                    this.userArr[4][0] = -1;
                }
                else {
                    this.userArr[4][0] = this.nFifth;
                }
                this.setFretPosition(n - 1, -1);
                break;
            }
            case 6: {
                if (this.userArr[5][0] == this.nSixth) {
                    this.userArr[5][0] = -1;
                }
                else {
                    this.userArr[5][0] = this.nSixth;
                }
                this.setFretPosition(n - 1, -1);
                break;
            }
        }
        this.paintOpenStrings();
    }
    
    private void paintOpenStrings() {
        final int n = 8;
        final int n2 = 8;
        final int n3 = 20;
        final int n4 = 23;
        final int n5 = n4 + n3;
        final int n6 = n4 + n3 * 2;
        final int n7 = n4 + n3 * 3;
        final int n8 = n4 + n3 * 4;
        final int n9 = n4 + n3 * 5;
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        final int n10 = 59;
        graphics2D.setFont(new Font("SansSerif", 1, 12));
        graphics2D.setColor(Color.black);
        if (this.userArr[0][0] == this.nBase) {
            graphics2D.setColor(Color.orange);
            graphics2D.fillOval(n4, n10 - 8, n, n2);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(n4, n10 - 8, n, n2);
        }
        else if (this.userArr[0][0] == -1) {
            graphics2D.drawString("x", n4 + 1, n10);
        }
        if (this.userArr[1][0] == this.nSec) {
            graphics2D.setColor(Color.orange);
            graphics2D.fillOval(n5, n10 - 8, n, n2);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(n5, n10 - 8, n, n2);
        }
        else if (this.userArr[1][0] == -1) {
            graphics2D.drawString("x", n5 + 1, n10);
        }
        if (this.userArr[2][0] == this.nThird) {
            graphics2D.setColor(Color.orange);
            graphics2D.fillOval(n6, n10 - 8, n, n2);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(n6, n10 - 8, n, n2);
        }
        else if (this.userArr[2][0] == -1) {
            graphics2D.drawString("x", n6 + 1, n10);
        }
        if (this.userArr[3][0] == this.nFourth) {
            graphics2D.setColor(Color.orange);
            graphics2D.fillOval(n7, n10 - 8, n, n2);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(n7, n10 - 8, n, n2);
        }
        else if (this.userArr[3][0] == -1) {
            graphics2D.drawString("x", n7 + 1, n10);
        }
        if (this.userArr[4][0] == this.nFifth) {
            graphics2D.setColor(Color.orange);
            graphics2D.fillOval(n8, n10 - 8, n, n2);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(n8, n10 - 8, n, n2);
        }
        else if (this.userArr[4][0] == -1) {
            graphics2D.drawString("x", n8 + 1, n10);
        }
        if (this.userArr[5][0] == this.nSixth) {
            graphics2D.setColor(Color.orange);
            graphics2D.fillOval(n9, n10 - 8, n, n2);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(n9, n10 - 8, n, n2);
        }
        else if (this.userArr[5][0] == -1) {
            graphics2D.drawString("x", n9 + 1, n10);
        }
    }
    
    public void updateOpenStrings() {
        if (this.noteArr != null) {
            for (int i = 0; i < this.noteArr.length; ++i) {
                if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromSharpArr(this.nBase))) {
                    this.userArr[0][0] = this.nBase;
                }
                else if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromFlatArr(this.nBase))) {
                    this.userArr[0][0] = this.nBase;
                }
                if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromSharpArr(this.nSec))) {
                    this.userArr[1][0] = this.nSec;
                }
                else if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromFlatArr(this.nSec))) {
                    this.userArr[1][0] = this.nSec;
                }
                if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromSharpArr(this.nThird))) {
                    this.userArr[2][0] = this.nThird;
                }
                else if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromFlatArr(this.nThird))) {
                    this.userArr[2][0] = this.nThird;
                }
                if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromSharpArr(this.nFourth))) {
                    this.userArr[3][0] = this.nFourth;
                }
                else if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromFlatArr(this.nFourth))) {
                    this.userArr[3][0] = this.nFourth;
                }
                if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromSharpArr(this.nFifth))) {
                    this.userArr[4][0] = this.nFifth;
                }
                else if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromFlatArr(this.nFifth))) {
                    this.userArr[4][0] = this.nFifth;
                }
                if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromSharpArr(this.nSixth))) {
                    this.userArr[5][0] = this.nSixth;
                }
                else if (this.noteArr[i].equals(this.chordAnalyser.getNoteFromFlatArr(this.nSixth))) {
                    this.userArr[5][0] = this.nSixth;
                }
            }
        }
    }
    
    public void paintNoteAnalyserResult() {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        this.nBa = this.nBase;
        this.nSe = this.nSec;
        this.nTh = this.nThird;
        this.nFo = this.nFourth;
        this.nFi = this.nFifth;
        this.nSi = this.nSixth;
        StringTokenizer stringTokenizer;
        if (this.bScaleList) {
            stringTokenizer = new StringTokenizer(this.chordAnalyser.getScaleNotes());
        }
        else {
            stringTokenizer = new StringTokenizer(this.chordAnalyser.getSingleNotes());
        }
        this.noteArr = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            this.noteArr[n++] = nextToken.substring(0, nextToken.length() - 1);
        }
        if (this.noteArr != null && !this.bResetChordResults) {
            final String s = this.noteArr[0];
            final int n2 = 20;
            final int n3 = 20;
            final int n4 = 17;
            final int n5 = 72;
            graphics2D.setFont(new Font("SansSerif", 1, 12));
            final Font font = graphics2D.getFont();
            final Color color = new Color(255, 255, 255);
            final Color color2 = new Color(0, 0, 0);
            final int n6 = this.nBeginFretPos / 26;
            if (n6 != 0) {
                for (int i = 0; i < n6; ++i) {
                    this.nBa = this.incrementNote(this.nBa);
                    this.nSe = this.incrementNote(this.nSe);
                    this.nTh = this.incrementNote(this.nTh);
                    this.nFo = this.incrementNote(this.nFo);
                    this.nFi = this.incrementNote(this.nFi);
                    this.nSi = this.incrementNote(this.nSi);
                }
            }
            for (int j = this.nBeginFretPos; j <= this.nEndFretPos; j += 26) {
                for (int k = 0, n7 = 1; k <= 100; k += 20, ++n7) {
                    switch (n7) {
                        case 1: {
                            this.nBa = this.incrementNote(this.nBa);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nBa))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nBa, n4, n5, k, j, n2, n3, 1);
                                break;
                            }
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nBa))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nBa, n4, n5, k, j, n2, n3, 0);
                                break;
                            }
                            break;
                        }
                        case 2: {
                            this.nSe = this.incrementNote(this.nSe);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nSe))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nSe, n4, n5, k, j, n2, n3, 1);
                                break;
                            }
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nSe))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nSe, n4, n5, k, j, n2, n3, 0);
                                break;
                            }
                            break;
                        }
                        case 3: {
                            this.nTh = this.incrementNote(this.nTh);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nTh))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nTh, n4, n5, k, j, n2, n3, 1);
                                break;
                            }
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nTh))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nTh, n4, n5, k, j, n2, n3, 0);
                                break;
                            }
                            break;
                        }
                        case 4: {
                            this.nFo = this.incrementNote(this.nFo);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nFo))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nFo, n4, n5, k, j, n2, n3, 1);
                                break;
                            }
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nFo))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nFo, n4, n5, k, j, n2, n3, 0);
                                break;
                            }
                            break;
                        }
                        case 5: {
                            this.nFi = this.incrementNote(this.nFi);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nFi))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nFi, n4, n5, k, j, n2, n3, 1);
                                break;
                            }
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nFi))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nFi, n4, n5, k, j, n2, n3, 0);
                                break;
                            }
                            break;
                        }
                        case 6: {
                            this.nSi = this.incrementNote(this.nSi);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nSi))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nSi, n4, n5, k, j, n2, n3, 1);
                                break;
                            }
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nSi))) {
                                this.paintNote(graphics2D, font, color, color2, s, this.nSi, n4, n5, k, j, n2, n3, 0);
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void paintNote(final Graphics2D graphics2D, final Font font, final Color color, final Color color2, final String s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        graphics2D.setFont(font);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        if (this.chordAnalyser.getNoteFromSharpArr(n).equals(s)) {
            graphics2D.setColor(new Color(0, 0, 200));
        }
        else {
            graphics2D.setColor(color2);
        }
        graphics2D.fillOval(n2 + n4, n3 + n5, n6, n7);
        graphics2D.setColor(color);
        final int stringWidth = fontMetrics.stringWidth(this.chordAnalyser.getNoteFromSharpArr(n));
        final int ascent = fontMetrics.getAscent();
        if (n8 == 1) {
            graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(n), n4 + this.getCenteredHorizontPos(n2, n6, stringWidth), n5 + this.getCenterVerticalPos(n3 + 1, n7, ascent));
        }
        else {
            graphics2D.drawString(this.chordAnalyser.getNoteFromFlatArr(n), n4 + this.getCenteredHorizontPos(n2, n6, stringWidth), n5 + this.getCenterVerticalPos(n3 + 1, n7, ascent));
        }
        graphics2D.setColor(Color.orange);
        graphics2D.drawOval(n2 + n4, n3 + n5, n6, n7);
    }
    
    private void paintInterval(final Graphics2D graphics2D, final Font font, final int n, final Color color, final Color color2, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        graphics2D.setFont(font);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        graphics2D.setColor(color2);
        graphics2D.fillOval(n3 + n5, n4 + n6, n7, n8);
        graphics2D.setColor(color);
        switch (n) {
            case 1: {
                graphics2D.drawString(this.chordAnalyser.getSharpNoteNames(n2), n5 + this.getCenteredHorizontPos(n3, n7, fontMetrics.stringWidth(this.chordAnalyser.getSharpNoteNames(n2))), n6 + this.getCenterVerticalPos(n4 + 1, n8, fontMetrics.getAscent()));
                break;
            }
            case 2: {
                graphics2D.drawString(this.chordAnalyser.getFlatNoteNames(n2), n5 + this.getCenteredHorizontPos(n3, n7, fontMetrics.stringWidth(this.chordAnalyser.getFlatNoteNames(n2))), n6 + this.getCenterVerticalPos(n4 + 1, n8, fontMetrics.getAscent()));
                break;
            }
            case 3: {
                graphics2D.drawString(this.chordAnalyser.getAddNoteNames(n2), n5 + this.getCenteredHorizontPos(n3, n7, fontMetrics.stringWidth(this.chordAnalyser.getAddNoteNames(n2))), n6 + this.getCenterVerticalPos(n4 + 1, n8, fontMetrics.getAscent()));
                break;
            }
            case 4: {
                graphics2D.drawString(this.chordAnalyser.getAddFlatNoteNames(n2), n5 + this.getCenteredHorizontPos(n3, n7, fontMetrics.stringWidth(this.chordAnalyser.getAddFlatNoteNames(n2))), n6 + this.getCenterVerticalPos(n4 + 1, n8, fontMetrics.getAscent()));
                break;
            }
        }
        graphics2D.setColor(Color.orange);
        graphics2D.drawOval(n3 + n5, n4 + n6, n7, n8);
    }
    
    private String isIntervalEqual(final String[] array, final int n) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i].equals(this.chordAnalyser.getSharpNoteNames(n))) {
                    this.nSelectIntervalsArray = 1;
                    return array[i];
                }
                if (array[i].equals(this.chordAnalyser.getFlatNoteNames(n))) {
                    this.nSelectIntervalsArray = 2;
                    return array[i];
                }
                if (array[i].equals(this.chordAnalyser.getAddNoteNames(n))) {
                    this.nSelectIntervalsArray = 3;
                    return array[i];
                }
                if (array[i].equals(this.chordAnalyser.getAddFlatNoteNames(n))) {
                    this.nSelectIntervalsArray = 4;
                    return array[i];
                }
            }
        }
        this.nSelectIntervalsArray = 0;
        return "";
    }
    
    private int getIntervalIndex(final int n, final int n2) {
        return (n - n2 < 0) ? (n - n2 + 12) : (n - n2);
    }
    
    public void paintIntervalAnalyserResult() {
        final Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        this.nBa = this.nBase;
        this.nSe = this.nSec;
        this.nTh = this.nThird;
        this.nFo = this.nFourth;
        this.nFi = this.nFifth;
        this.nSi = this.nSixth;
        StringTokenizer stringTokenizer;
        StringTokenizer stringTokenizer2;
        if (this.bScaleList) {
            stringTokenizer = new StringTokenizer(this.chordAnalyser.getScaleFormula());
            stringTokenizer2 = new StringTokenizer(this.chordAnalyser.getScaleNotes());
        }
        else {
            stringTokenizer = new StringTokenizer(this.chordAnalyser.getChordFormula());
            stringTokenizer2 = new StringTokenizer(this.chordAnalyser.getSingleNotes());
        }
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            array[n++] = nextToken.substring(0, nextToken.length());
        }
        this.noteArr = new String[stringTokenizer2.countTokens()];
        int n2 = 0;
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer2.nextToken();
            this.noteArr[n2++] = nextToken2.substring(0, nextToken2.length() - 1);
        }
        final int noteIndex = this.chordAnalyser.getNoteIndex(this.noteArr[0]);
        if (this.noteArr != null && !this.bResetChordResults) {
            final String s = this.noteArr[0];
            final int n3 = 20;
            final int n4 = 20;
            final int n5 = 17;
            final int n6 = 72;
            graphics2D.setFont(new Font("SansSerif", 0, 11));
            final Font font = graphics2D.getFont();
            final Color color = new Color(255, 255, 255);
            final Color color2 = new Color(0, 0, 0);
            final int n7 = this.nBeginFretPos / 26;
            if (n7 != 0) {
                for (int i = 0; i < n7; ++i) {
                    this.nBa = this.incrementNote(this.nBa);
                    this.nSe = this.incrementNote(this.nSe);
                    this.nTh = this.incrementNote(this.nTh);
                    this.nFo = this.incrementNote(this.nFo);
                    this.nFi = this.incrementNote(this.nFi);
                    this.nSi = this.incrementNote(this.nSi);
                }
            }
            for (int j = this.nBeginFretPos; j <= this.nEndFretPos; j += 26) {
                for (int k = 0, n8 = 1; k <= 100; k += 20, ++n8) {
                    switch (n8) {
                        case 1: {
                            this.nBa = this.incrementNote(this.nBa);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nBa)) || this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nBa))) {
                                this.isIntervalEqual(array, this.getIntervalIndex(this.nBa, noteIndex));
                                this.paintInterval(graphics2D, font, this.nSelectIntervalsArray, color, color2, this.getIntervalIndex(this.nBa, noteIndex), n5, n6, k, j, n3, n4);
                                break;
                            }
                            break;
                        }
                        case 2: {
                            this.nSe = this.incrementNote(this.nSe);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nSe)) || this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nSe))) {
                                this.isIntervalEqual(array, this.getIntervalIndex(this.nSe, noteIndex));
                                this.paintInterval(graphics2D, font, this.nSelectIntervalsArray, color, color2, this.getIntervalIndex(this.nSe, noteIndex), n5, n6, k, j, n3, n4);
                                break;
                            }
                            break;
                        }
                        case 3: {
                            this.nTh = this.incrementNote(this.nTh);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nTh)) || this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nTh))) {
                                this.isIntervalEqual(array, this.getIntervalIndex(this.nTh, noteIndex));
                                this.paintInterval(graphics2D, font, this.nSelectIntervalsArray, color, color2, this.getIntervalIndex(this.nTh, noteIndex), n5, n6, k, j, n3, n4);
                                break;
                            }
                            break;
                        }
                        case 4: {
                            this.nFo = this.incrementNote(this.nFo);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nFo)) || this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nFo))) {
                                this.isIntervalEqual(array, this.getIntervalIndex(this.nFo, noteIndex));
                                this.paintInterval(graphics2D, font, this.nSelectIntervalsArray, color, color2, this.getIntervalIndex(this.nFo, noteIndex), n5, n6, k, j, n3, n4);
                                break;
                            }
                            break;
                        }
                        case 5: {
                            this.nFi = this.incrementNote(this.nFi);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nFi)) || this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nFi))) {
                                this.isIntervalEqual(array, this.getIntervalIndex(this.nFi, noteIndex));
                                this.paintInterval(graphics2D, font, this.nSelectIntervalsArray, color, color2, this.getIntervalIndex(this.nFi, noteIndex), n5, n6, k, j, n3, n4);
                                break;
                            }
                            break;
                        }
                        case 6: {
                            this.nSi = this.incrementNote(this.nSi);
                            if (this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromSharpArr(this.nSi)) || this.isNoteEqual(this.noteArr, this.chordAnalyser.getNoteFromFlatArr(this.nSi))) {
                                this.isIntervalEqual(array, this.getIntervalIndex(this.nSi, noteIndex));
                                this.paintInterval(graphics2D, font, this.nSelectIntervalsArray, color, color2, this.getIntervalIndex(this.nSi, noteIndex), n5, n6, k, j, n3, n4);
                                break;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void showAllNotes(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final int n = 19;
        final int n2 = 73;
        final int n3 = 17;
        final int n4 = 17;
        this.nBa = this.nBase;
        this.nSe = this.nSec;
        this.nTh = this.nThird;
        this.nFo = this.nFourth;
        this.nFi = this.nFifth;
        this.nSi = this.nSixth;
        final Color color = new Color(0, 255, 0);
        final Color color2 = new Color(110, 110, 110);
        graphics2D.setFont(new Font("SansSerif", 0, 10));
        final Font font = graphics2D.getFont();
        final int n5 = this.nBeginFretPos / 26;
        if (n5 != 0) {
            for (int i = 0; i < n5; ++i) {
                this.nBa = this.incrementNote(this.nBa);
                this.nSe = this.incrementNote(this.nSe);
                this.nTh = this.incrementNote(this.nTh);
                this.nFo = this.incrementNote(this.nFo);
                this.nFi = this.incrementNote(this.nFi);
                this.nSi = this.incrementNote(this.nSi);
            }
        }
        for (int j = this.nBeginFretPos; j <= this.nEndFretPos; j += 26) {
            for (int k = 0, n6 = 1; k <= 100; k += 20, ++n6) {
                switch (n6) {
                    case 1: {
                        this.paintAllNotes(graphics2D, font, color, color2, this.nBa = this.incrementNote(this.nBa), n, n2, k, j, n3, n4);
                        break;
                    }
                    case 2: {
                        this.paintAllNotes(graphics2D, font, color, color2, this.nSe = this.incrementNote(this.nSe), n, n2, k, j, n3, n4);
                        break;
                    }
                    case 3: {
                        this.paintAllNotes(graphics2D, font, color, color2, this.nTh = this.incrementNote(this.nTh), n, n2, k, j, n3, n4);
                        break;
                    }
                    case 4: {
                        this.paintAllNotes(graphics2D, font, color, color2, this.nFo = this.incrementNote(this.nFo), n, n2, k, j, n3, n4);
                        break;
                    }
                    case 5: {
                        this.paintAllNotes(graphics2D, font, color, color2, this.nFi = this.incrementNote(this.nFi), n, n2, k, j, n3, n4);
                        break;
                    }
                    case 6: {
                        this.paintAllNotes(graphics2D, font, color, color2, this.nSi = this.incrementNote(this.nSi), n, n2, k, j, n3, n4);
                        break;
                    }
                }
            }
        }
    }
    
    private void paintAllNotes(final Graphics2D graphics2D, final Font font, final Color color, final Color color2, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        graphics2D.setFont(font);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        graphics2D.setColor(Color.lightGray);
        graphics2D.fillOval(n2 + n4, n3 + n5, n6, n7);
        graphics2D.setColor(Color.gray);
        graphics2D.fillOval(n2 + n4 + 1, 2 + n3 + n5, n6 - 2, n7 - 2);
        graphics2D.setColor(Color.black);
        graphics2D.fillOval(n2 + n4 + 3, 4 + n3 + n5, n6 - 5, n7 - 6);
        final int stringWidth = fontMetrics.stringWidth(this.chordAnalyser.getNoteFromSharpArr(n));
        final int ascent = fontMetrics.getAscent();
        graphics2D.setColor(color);
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(n), n4 + this.getCenteredHorizontPos(n2, n6, stringWidth), n5 + this.getCenterVerticalPos(n3, n7, ascent));
    }
    
    public void paintTuning(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.black);
        final int n = 13;
        final int n2 = 13;
        final int n3 = 20;
        final int n4 = 23;
        final int n5 = n4 + n3;
        final int n6 = n4 + n3 * 2;
        final int n7 = n4 + n3 * 3;
        final int n8 = n4 + n3 * 4;
        final int n9 = n4 + n3 * 5;
        final int n10 = 12;
        final int n11 = n10 - 12;
        graphics2D.setFont(new Font("SansSerif", 1, 14));
        graphics2D.drawString("+", n4, n10);
        graphics2D.drawRect(n4 - 3, n11, n, n2);
        graphics2D.drawString("+", n5, n10);
        graphics2D.drawRect(n5 - 3, n11, n, n2);
        graphics2D.drawString("+", n6, n10);
        graphics2D.drawRect(n6 - 3, n11, n, n2);
        graphics2D.drawString("+", n7, n10);
        graphics2D.drawRect(n7 - 3, n11, n, n2);
        graphics2D.drawString("+", n8, n10);
        graphics2D.drawRect(n8 - 3, n11, n, n2);
        graphics2D.drawString("+", n9, n10);
        graphics2D.drawRect(n9 - 3, n11, n, n2);
        final int n12 = 28;
        this.deleteArea(graphics2D, n4, n12 - 10, n9 - (n4 - 20), 12);
        graphics2D.setColor(Color.black);
        graphics2D.setFont(new Font("SansSerif", 1, 14));
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.nBase), n4, n12);
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.nSec), n5, n12);
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.nThird), n6, n12);
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.nFourth), n7, n12);
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.nFifth), n8, n12);
        graphics2D.drawString(this.chordAnalyser.getNoteFromSharpArr(this.nSixth), n9, n12);
        final int n13 = 45;
        final int n14 = n13 - 12;
        graphics2D.setFont(new Font("SansSerif", 0, 20));
        graphics2D.drawString("-", n4 + 1, n13);
        graphics2D.drawRect(n4 - 3, n14, n, n2);
        graphics2D.drawString("-", n5 + 1, n13);
        graphics2D.drawRect(n5 - 3, n14, n, n2);
        graphics2D.drawString("-", n6 + 1, n13);
        graphics2D.drawRect(n6 - 3, n14, n, n2);
        graphics2D.drawString("-", n7 + 1, n13);
        graphics2D.drawRect(n7 - 3, n14, n, n2);
        graphics2D.drawString("-", n8 + 1, n13);
        graphics2D.drawRect(n8 - 3, n14, n, n2);
        graphics2D.drawString("-", n9 + 1, n13);
        graphics2D.drawRect(n9 - 3, n14, n, n2);
        graphics2D.setFont(new Font("Serif", 2, 12));
        graphics2D.setColor(Color.black);
        graphics2D.drawString("3fr.", 140, 140);
        graphics2D.drawString("5fr.", 140, 190);
        graphics2D.drawString("7fr.", 140, 242);
        graphics2D.drawString("10fr.", 140, 320);
        graphics2D.setFont(new Font("Serif", 2, 12));
        graphics2D.setColor(new Color(120, 120, 120));
        graphics2D.drawString("GuitarCodex by Robert Leh", 10, 420);
        graphics2D.drawString("© 2002 www.microtools.de", 10, 430);
    }
    
    public int incrementNote(int n) {
        return (n == 11) ? 0 : (++n);
    }
    
    public int decrementNote(int n) {
        return (n == 0) ? 11 : (--n);
    }
    
    public boolean getShowNotesStatus() {
        return this.bShowAllNotes;
    }
    
    public boolean getShowScopeStatus() {
        return this.bShowScope;
    }
    
    public void enableScopeFrame() {
        this.bShowScope = true;
    }
    
    public void resetScopeStatus() {
        this.bShowScope = false;
    }
    
    public void allNotesBtnPressed() {
        this.bShowAllNotes = !this.bShowAllNotes;
    }
    
    public void scopeBtnPressed() {
        this.bShowScope = !this.bShowScope;
    }
    
    public void switchNoteIntervalView(final boolean b) {
        this.bUserNotes = false;
        if (b) {
            this.bShowNotes = true;
            this.bShowIntervals = false;
        }
        else {
            this.bShowNotes = false;
            this.bShowIntervals = true;
        }
    }
    
    public boolean resetChordResult(final boolean bResetChordResults) {
        this.resetUserArray();
        this.resetFretPositions();
        if (bResetChordResults) {
            this.setTuning(0);
        }
        return this.bResetChordResults = bResetChordResults;
    }
    
    public void showNotes() {
        this.bUserNotes = false;
        this.bShowIntervals = false;
        this.bShowNotes = true;
    }
    
    public void showUserNotes() {
        this.bShowNotes = false;
        this.bShowIntervals = false;
        this.bUserNotes = true;
    }
    
    public void showIntervals() {
        this.bUserNotes = false;
        this.bShowNotes = false;
        this.bShowIntervals = true;
    }
    
    public void isScaleList(final boolean bScaleList) {
        this.bScaleList = bScaleList;
    }
    
    private void deleteArea(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        graphics2D.setColor(this.getBackground());
        graphics2D.fillRect(n, n2, n3, n4);
    }
    
    private void updateTuning(final int nBase, final int nSec, final int nThird, final int nFourth, final int nFifth, final int nSixth) {
        this.nBase = nBase;
        this.nSec = nSec;
        this.nThird = nThird;
        this.nFourth = nFourth;
        this.nFifth = nFifth;
        this.nSixth = nSixth;
    }
    
    public void setTuning(final int n) {
        if (!this.bLeftHand) {
            switch (n) {
                case 0: {
                    this.updateTuning(4, 9, 2, 7, 11, 4);
                    break;
                }
                case 1: {
                    this.updateTuning(2, 9, 2, 7, 11, 4);
                    break;
                }
                case 2: {
                    this.updateTuning(2, 9, 2, 7, 11, 2);
                    break;
                }
                case 3: {
                    this.updateTuning(2, 9, 2, 6, 9, 2);
                    break;
                }
                case 4: {
                    this.updateTuning(2, 9, 2, 5, 9, 2);
                    break;
                }
                case 5: {
                    this.updateTuning(2, 9, 2, 2, 9, 2);
                    break;
                }
                case 6: {
                    this.updateTuning(4, 9, 2, 7, 0, 5);
                    break;
                }
                case 7: {
                    this.updateTuning(4, 11, 4, 8, 11, 4);
                    break;
                }
                case 8: {
                    this.updateTuning(2, 7, 2, 7, 11, 4);
                    break;
                }
                case 9: {
                    this.updateTuning(2, 7, 2, 7, 11, 2);
                    break;
                }
                case 10: {
                    this.updateTuning(2, 7, 2, 7, 10, 2);
                    break;
                }
                case 11: {
                    this.updateTuning(4, 9, 4, 9, 1, 4);
                    break;
                }
                case 12: {
                    this.updateTuning(4, 9, 4, 9, 0, 4);
                    break;
                }
                case 13: {
                    this.updateTuning(0, 7, 0, 7, 9, 4);
                    break;
                }
                case 14: {
                    this.updateTuning(0, 7, 0, 7, 0, 4);
                    break;
                }
                case 15: {
                    this.updateTuning(0, 7, 2, 7, 9, 2);
                    break;
                }
            }
        }
        else {
            switch (n) {
                case 0: {
                    this.updateTuning(4, 11, 7, 2, 9, 4);
                    break;
                }
                case 1: {
                    this.updateTuning(4, 11, 7, 2, 9, 2);
                    break;
                }
                case 2: {
                    this.updateTuning(2, 11, 7, 2, 9, 2);
                    break;
                }
                case 3: {
                    this.updateTuning(2, 9, 6, 2, 9, 2);
                    break;
                }
                case 4: {
                    this.updateTuning(2, 9, 5, 2, 9, 2);
                    break;
                }
                case 5: {
                    this.updateTuning(2, 9, 2, 2, 9, 2);
                    break;
                }
                case 6: {
                    this.updateTuning(5, 0, 7, 2, 9, 4);
                    break;
                }
                case 7: {
                    this.updateTuning(4, 11, 8, 4, 11, 4);
                    break;
                }
                case 8: {
                    this.updateTuning(4, 11, 7, 2, 7, 2);
                    break;
                }
                case 9: {
                    this.updateTuning(2, 11, 7, 2, 7, 2);
                    break;
                }
                case 10: {
                    this.updateTuning(2, 10, 7, 2, 7, 2);
                    break;
                }
                case 11: {
                    this.updateTuning(4, 1, 9, 4, 9, 4);
                    break;
                }
                case 12: {
                    this.updateTuning(4, 0, 9, 4, 9, 4);
                    break;
                }
                case 13: {
                    this.updateTuning(4, 9, 7, 0, 7, 0);
                    break;
                }
                case 14: {
                    this.updateTuning(4, 0, 7, 0, 7, 0);
                    break;
                }
                case 15: {
                    this.updateTuning(2, 9, 7, 2, 7, 0);
                    break;
                }
            }
        }
    }
    
    public boolean isLeftHanded() {
        return this.bLeftHand;
    }
    
    public void setLeftHand(final boolean bLeftHand) {
        this.bLeftHand = bLeftHand;
        final int nBase = this.nBase;
        this.nBase = this.nSixth;
        this.nSixth = nBase;
        final int nSec = this.nSec;
        this.nSec = this.nFifth;
        this.nFifth = nSec;
        final int nThird = this.nThird;
        this.nThird = this.nFourth;
        this.nFourth = nThird;
    }
    
    public int getXOffset() {
        return 20;
    }
}