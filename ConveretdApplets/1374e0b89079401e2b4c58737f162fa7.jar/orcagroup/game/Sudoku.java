// 
// Decompiled by Procyon v0.5.30
// 

package orcagroup.game;

import java.text.DateFormat;
import java.awt.Event;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Date;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class Sudoku extends Applet implements Runnable
{
    private static final int GAME_MODE = 0;
    private static final int DIGIT_MODE = 1;
    private static final int ERASER_MODE = 2;
    private static final int DIALOG_MODE = 3;
    private static final int STARTGAME_MODE = 4;
    private static final int ENDGAME_MODE = 5;
    private static final int COMPLETE_ANIM_MODE = 7;
    private static final int LOADING_MODE = 8;
    private static final int COMPLETE_MODE = 9;
    private static final int INVALID_MODE = 99;
    private static final int MAX_DISTANCE = 400;
    private static final int DIALOG_NONE = -1;
    private static final int DIALOG_RESET = 0;
    private static final int DIALOG_CONTINUE = 1;
    String[][] language;
    int width;
    int height;
    Thread programThread;
    boolean threadSuspended;
    Puzzle puzzle;
    int lastPostedPuzzle;
    int Animation;
    int FrameCounter;
    int CellSize;
    int brTop;
    int brLeft;
    int brRight;
    int brBottom;
    int mouseX;
    int mouseY;
    int mCellX;
    int mCellY;
    int DigitX;
    int DigitY;
    boolean mouseCellValid;
    int status;
    Image backPageImage;
    Graphics backPageGraphics;
    Image imgLoading;
    int imagesLoaded;
    Rectangle[] DigitRects;
    Rectangle[] digitHintRects;
    Rectangle[] MenuChoiceRects;
    Rectangle[] buttonRects;
    int[] buttonImageIndex;
    Rectangle[] dialogButtons;
    int buttonHoverIndex;
    int dialogIndex;
    int dialogButtonIndex;
    int dialogButtonHover;
    int MenuChoiceIndex;
    boolean alternate_mode;
    boolean effect_mode;
    int digitDistance;
    double[] bubble_y;
    double[] bubble_speed;
    double[] bubble_x;
    boolean[] bubble_active;
    int[] bubble_size;
    int max_bubbles;
    int active_bubbles;
    int SelectedDigit;
    int selectedHintDigit;
    long startTime;
    long currentTime;
    long prevTime;
    long playTime;
    long timeSpan;
    long frameSpan;
    boolean timeCounting;
    boolean newpuzzle;
    Rectangle hintButtonRect;
    boolean mouseOnButton;
    String puzzleDate;
    String appId;
    boolean isInvalidClient;
    Date currentPuzzleDate;
    MediaTracker tracker;
    int puzzleresult_y;
    int dnis_y;
    int code_y;
    int puzzleresult_x;
    int dnis_x;
    int code_x;
    int center_code;
    int center_dnis;
    int center_result;
    int pleasure_mode;
    int show_date_selection;
    int date_selection_x;
    int date_selection_y;
    int languageId;
    private static String[] imageNames;
    private static String[] alternateNames;
    Image[] Images;
    Image[] AlternateImages;
    Image[] Banners;
    int maxBanners;
    double bannerOffset;
    int bannerMode;
    int bannerTimer;
    int bannerIndex;
    int bannersVisible;
    String alternateFolder;
    String skinFolder;
    boolean debugMode;
    
    public Sudoku() {
        this.language = new String[][] { { "Weet je zeker dat je de puzzel wilt resetten?", "Weet je zeker dat je door wilt gaan?" }, { "Bist du sicher, dass du das R\u00e4tsel\rzur\u00fccksetzen willst?", "Bist du sicher, dass du weiter machen willst?" } };
        this.programThread = null;
        this.lastPostedPuzzle = 0;
        this.Animation = 0;
        this.FrameCounter = 0;
        this.mouseCellValid = false;
        this.status = 8;
        this.backPageImage = null;
        this.imagesLoaded = 0;
        this.buttonHoverIndex = -1;
        this.dialogIndex = -1;
        this.dialogButtonIndex = -1;
        this.dialogButtonHover = -1;
        this.alternate_mode = false;
        this.effect_mode = false;
        this.digitDistance = 0;
        this.SelectedDigit = 0;
        this.selectedHintDigit = 0;
        this.startTime = 0L;
        this.currentTime = 0L;
        this.prevTime = 0L;
        this.playTime = 0L;
        this.timeSpan = 0L;
        this.frameSpan = 0L;
        this.timeCounting = false;
        this.newpuzzle = false;
        this.mouseOnButton = false;
        this.isInvalidClient = false;
        this.puzzleresult_y = 0;
        this.dnis_y = 0;
        this.code_y = 0;
        this.puzzleresult_x = 0;
        this.dnis_x = 0;
        this.code_x = 0;
        this.center_code = 1;
        this.center_dnis = 1;
        this.center_result = 1;
        this.pleasure_mode = 1;
        this.show_date_selection = 1;
        this.date_selection_x = 238;
        this.date_selection_y = 38;
        this.languageId = 0;
        this.maxBanners = 2;
        this.bannerOffset = 400.0;
        this.bannerMode = 0;
        this.bannerTimer = 0;
        this.bannerIndex = 0;
        this.bannersVisible = 0;
        this.debugMode = false;
    }
    
    @Override
    public void init() {
        this.timeSpan = 0L;
        this.bannerOffset = 400.0;
        this.bannerMode = 0;
        this.bannerTimer = 0;
        this.bannerIndex = 0;
        this.tracker = new MediaTracker(this);
        this.currentPuzzleDate = new Date();
        this.puzzle = new Puzzle();
        this.getAppletParameters();
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.MenuChoiceIndex = -1;
        this.imgLoading = this.skinGetImage(this.getCodeBase(), "loading.gif");
        this.tracker.addImage(this.imgLoading, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (this.alternate_mode) {
            this.max_bubbles = 10;
            this.bubble_y = new double[this.max_bubbles];
            this.bubble_speed = new double[this.max_bubbles];
            this.bubble_x = new double[this.max_bubbles];
            this.bubble_active = new boolean[this.max_bubbles];
            this.bubble_size = new int[this.max_bubbles];
            for (int w = 0; w < this.max_bubbles; ++w) {
                this.bubble_active[w] = false;
            }
        }
        try {
            this.backPageImage = this.createImage(this.width, this.height);
            this.backPageGraphics = this.backPageImage.getGraphics();
        }
        catch (Exception ex2) {}
        this.CellSize = 31;
        int w = (this.width - this.CellSize * 9) / 2;
        this.brTop = w;
        this.brLeft = w;
        this.brRight = this.width - w;
        this.brBottom = this.height - w;
        this.DigitRects = new Rectangle[9];
        this.digitHintRects = new Rectangle[9];
        for (w = 0; w < 9; ++w) {
            this.DigitRects[w] = new Rectangle();
            this.digitHintRects[w] = new Rectangle();
        }
        this.MenuChoiceRects = new Rectangle[2];
        for (w = 0; w < 2; ++w) {
            (this.MenuChoiceRects[w] = new Rectangle()).setBounds(0, 0, 0, 0);
        }
        (this.hintButtonRect = new Rectangle()).setBounds(132, 38, 69, 18);
        this.buttonRects = new Rectangle[4];
        this.buttonImageIndex = new int[4];
        for (w = 0; w < this.buttonRects.length; ++w) {
            (this.buttonRects[w] = new Rectangle()).setBounds(0, 0, 0, 0);
            this.buttonImageIndex[w] = 0;
        }
        this.buttonRects[0].setBounds(this.brLeft, this.brTop - 22, 69, 18);
        this.buttonImageIndex[0] = 50;
        if (this.show_date_selection == 1) {
            this.buttonRects[1].setBounds(this.date_selection_x, this.date_selection_y, 10, 18);
            this.buttonImageIndex[1] = 68;
            this.buttonRects[2].setBounds(this.date_selection_x + 70, this.date_selection_y, 10, 18);
            this.buttonImageIndex[2] = 70;
            this.buttonRects[3].setBounds(this.date_selection_x + 85, this.date_selection_y, 17, 18);
            this.buttonImageIndex[3] = 72;
        }
        this.dialogButtons = new Rectangle[2];
        for (w = 0; w < 2; ++w) {
            this.dialogButtons[w] = new Rectangle();
        }
        if (!this.isInvalidClient) {
            this.initializePuzzle();
            this.startGame();
        }
        else {
            this.status = 99;
        }
        this.status = 8;
        this.loadImages();
    }
    
    @Override
    public void destroy() {
        this.postPuzzleStats((int)this.playTime);
    }
    
    @Override
    public void start() {
        if (this.programThread == null) {
            (this.programThread = new Thread(this)).setPriority(10);
            this.threadSuspended = false;
            this.programThread.start();
        }
        else if (this.threadSuspended) {
            this.threadSuspended = false;
            synchronized (this) {
                this.notify();
            }
        }
    }
    
    @Override
    public void stop() {
        this.threadSuspended = true;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                ++this.FrameCounter;
                if (this.status == 8 && this.tracker != null) {
                    this.tracker.waitForAll();
                    while (this.tracker.statusAll(true) != 8) {
                        this.repaint();
                        final Thread programThread = this.programThread;
                        Thread.sleep(50L);
                    }
                    this.tracker = null;
                    this.startGame();
                }
                this.repaint();
                if (this.threadSuspended) {
                    synchronized (this) {
                        while (this.threadSuspended) {
                            this.wait();
                        }
                    }
                }
                final Thread programThread2 = this.programThread;
                Thread.sleep(10L);
            }
        }
        catch (InterruptedException e) {}
    }
    
    @Override
    public String getAppletInfo() {
        return "Sudoku v1.1 Written by J.F. van Leur (c) 2005 OrcaGroup, all rights reserved.";
    }
    
    @Override
    public void paint(final Graphics g) {
        final Graphics2D g2 = (Graphics2D)g;
        this.updateTimers();
        if (this.status != 8 && this.status != 99) {
            this.drawBoard(g);
            if (this.status != 9 && this.dialogIndex != 1) {
                g2.setFont(new Font("Arial", 0, 22));
                g2.setColor(Color.black);
                if (this.pleasure_mode == 0) {
                    g2.drawString(this.puzzle.getPuzzleCode(), this.brLeft, this.brBottom + 22);
                }
                if (this.timeCounting) {
                    final String format = "mm:ss";
                    final SimpleDateFormat df = new SimpleDateFormat(format);
                    final Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(this.playTime);
                    df.setCalendar(cal);
                    final String timerString = df.format(cal.getTime());
                    g2.drawString(timerString, this.brRight - 54, this.brBottom + 22);
                }
            }
            if (this.status == 1) {
                if (this.Animation < 10) {
                    this.Animation += 2;
                }
                else {
                    this.Animation = 10;
                }
                this.drawDigits(g);
            }
            else if (this.status == 2) {
                if (this.Animation < 10) {
                    this.Animation += 2;
                }
                else {
                    this.Animation = 10;
                }
                this.drawMenu(g);
            }
            else if (this.status == 7) {
                ++this.Animation;
                this.drawCompletedAnim(g);
            }
            else if (this.status == 9) {
                ++this.Animation;
                this.drawCompleted(g);
            }
            else if (this.status == 3) {
                switch (this.dialogIndex) {
                    case 0: {
                        this.drawResetQuestion(g);
                        break;
                    }
                    case 1: {
                        this.drawContinueQuestion(g);
                        break;
                    }
                }
            }
        }
        else if (this.status == 8) {
            final int cW = this.width - this.imgLoading.getWidth(this) >> 1;
            final int cH = this.height - this.imgLoading.getHeight(this) >> 1;
            g2.drawImage(this.imgLoading, cW, cH, this.imgLoading.getWidth(this), this.imgLoading.getHeight(this), this);
        }
        else if (this.status == 99) {
            g2.setColor(Color.black);
            g2.drawString("Invalid parameters.", 20, 20);
            g2.drawString("Please contact your administrator.", 20, 40);
        }
    }
    
    @Override
    public void update(final Graphics g) {
        this.paint(this.backPageGraphics);
        g.drawImage(this.backPageImage, 0, 0, null);
    }
    
    int getIntParam(final String paramName, final int defaultValue) {
        final String tmp = this.getParameter(paramName);
        if (tmp != null && tmp.length() != 0) {
            return Integer.parseInt(tmp);
        }
        return defaultValue;
    }
    
    void getAppletParameters() {
        final String debug = this.getParameter("debug");
        if (debug == null || debug.length() == 0) {
            this.debugMode = false;
        }
        final int iTmp = this.getIntParam("effect_mode", 0);
        if (iTmp == 1) {
            this.effect_mode = true;
        }
        this.puzzle.setDebugMode(this.debugMode);
        this.puzzleDate = this.getParameter("date");
        if (this.puzzleDate == null || this.puzzleDate.length() == 0) {
            this.puzzleDate = this.getNewPuzzleDate();
        }
        this.appId = this.getParameter("id");
        if (this.appId == null || this.appId.length() == 0) {
            this.isInvalidClient = true;
        }
        final String alt = this.getParameter("alternate");
        if (alt != null && alt.length() != 0) {
            this.alternateFolder = alt;
            if (this.alternateFolder.length() != 0) {
                this.alternate_mode = true;
            }
        }
        final String skin = this.getParameter("skin");
        if (skin != null && skin.length() != 0) {
            this.skinFolder = skin;
        }
        else {
            this.skinFolder = "orca";
        }
        this.puzzleresult_y = this.getIntParam("result_y", 300);
        this.dnis_y = this.getIntParam("dnis_y", 230);
        this.code_y = this.getIntParam("code_y", 270);
        this.center_result = this.getIntParam("center_result", 1);
        this.center_dnis = this.getIntParam("center_dnis", 1);
        this.center_code = this.getIntParam("center_code", 1);
        if (this.center_result == 0) {
            this.puzzleresult_x = this.getIntParam("result_x", 0);
        }
        if (this.center_dnis == 0) {
            this.dnis_x = this.getIntParam("dnis_x", 0);
        }
        if (this.center_code == 0) {
            this.code_x = this.getIntParam("code_x", 0);
        }
        this.pleasure_mode = this.getIntParam("pleasure_mode", 0);
        this.show_date_selection = this.getIntParam("show_date_selection", 0);
        this.date_selection_x = this.getIntParam("date_selection_x", 238);
        this.date_selection_y = this.getIntParam("date_selection_y", 38);
        this.languageId = this.getIntParam("language_id", 0);
        this.bannersVisible = this.getIntParam("banners", 0);
    }
    
    Image skinGetImage(final URL base, final String name) {
        String path;
        if (this.alternate_mode) {
            path = "alternate//" + this.alternateFolder + "//";
        }
        else {
            path = this.skinFolder + "//";
        }
        return this.getImage(base, path + name);
    }
    
    void loadImages() {
        String alternateImages = new String();
        if (this.alternate_mode) {
            alternateImages = "alternate//" + this.alternateFolder + "//";
        }
        else {
            alternateImages = this.skinFolder + "//";
        }
        this.Images = new Image[Sudoku.imageNames.length];
        int imageIndex;
        for (imageIndex = 0, imageIndex = 0; imageIndex < Sudoku.imageNames.length; ++imageIndex) {
            this.Images[imageIndex] = this.skinGetImage(this.getCodeBase(), Sudoku.imageNames[imageIndex]);
            this.tracker.addImage(this.Images[imageIndex], imageIndex);
        }
        String readText = null;
        String bannerNames = new String("");
        try {
            if (this.bannersVisible == 1) {
                final String urlData = new String(alternateImages + "//banners//banners.dat");
                final URL bannerURL = new URL(this.getCodeBase(), urlData);
                final URLConnection urlConnection = bannerURL.openConnection();
                final BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((readText = in.readLine()) != null) {
                    bannerNames = bannerNames + readText + "\n";
                }
                in.close();
                final String[] banners = bannerNames.split("\n");
                this.maxBanners = banners.length;
                this.Banners = new Image[this.maxBanners];
                int bIndex;
                for (bIndex = 0, bIndex = 0; bIndex < this.maxBanners; ++bIndex) {
                    this.Banners[bIndex] = this.skinGetImage(this.getCodeBase(), "banners//" + banners[bIndex]);
                    this.tracker.addImage(this.Banners[bIndex], bIndex);
                }
            }
        }
        catch (IOException e) {
            bannerNames = e.toString();
        }
        if (this.alternate_mode) {
            this.AlternateImages = new Image[Sudoku.alternateNames.length];
            for (imageIndex = 0; imageIndex < Sudoku.alternateNames.length; ++imageIndex) {
                this.AlternateImages[imageIndex] = this.skinGetImage(this.getCodeBase(), Sudoku.alternateNames[imageIndex]);
                this.tracker.addImage(this.AlternateImages[imageIndex], imageIndex);
            }
        }
    }
    
    void updateTimers() {
        final Calendar cal = Calendar.getInstance();
        this.prevTime = this.currentTime;
        this.currentTime = cal.getTimeInMillis();
        this.timeSpan = this.currentTime - this.prevTime;
        if (this.timeSpan < 0L) {
            this.timeSpan = 0L;
        }
        if (this.timeSpan > 20L) {
            this.timeSpan = 20L;
        }
        if ((this.status == 0 || this.status == 2 || this.status == 1) && this.timeCounting) {
            this.playTime += this.timeSpan;
        }
    }
    
    @Override
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.mouseX = x;
        this.mouseY = y;
        this.mouseOnButton = false;
        this.buttonHoverIndex = -1;
        if (this.status == 0) {
            this.mouseCellValid = false;
            if (!this.isMouseOnBoard(x, y)) {
                if (this.hintButtonRect.contains(this.mouseX, this.mouseY)) {
                    this.mouseOnButton = true;
                    this.repaint();
                    return true;
                }
                int i;
                for (i = 0, i = 0; i < this.buttonRects.length; ++i) {
                    if (this.buttonRects[i].contains(this.mouseX, this.mouseY)) {
                        this.buttonHoverIndex = i;
                        this.repaint();
                        return true;
                    }
                }
                return false;
            }
            else {
                this.mCellX = (this.mouseX - this.brLeft) / this.CellSize;
                this.mCellY = (this.mouseY - this.brTop) / this.CellSize;
                this.mouseCellValid = this.isCellOnBoard(this.mCellX, this.mCellY);
            }
        }
        else if (this.status == 1) {
            this.SelectedDigit = 0;
            this.selectedHintDigit = 0;
            for (int i = 0; i < 9; ++i) {
                if (this.puzzle.getValidDigit(i)) {
                    if (this.DigitRects[i].contains(this.mouseX, this.mouseY)) {
                        this.SelectedDigit = i + 1;
                        this.selectedHintDigit = 0;
                        this.repaint();
                        break;
                    }
                    if (this.digitHintRects[i].contains(this.mouseX, this.mouseY)) {
                        this.selectedHintDigit = i + 1;
                        this.SelectedDigit = 0;
                        this.repaint();
                        break;
                    }
                }
            }
        }
        else if (this.status == 2) {
            this.MenuChoiceIndex = -1;
            for (int i = 0; i < 2; ++i) {
                if (this.MenuChoiceRects[i].contains(this.mouseX, this.mouseY)) {
                    this.MenuChoiceIndex = i;
                    this.repaint();
                    break;
                }
            }
        }
        else if (this.status == 3) {
            this.dialogButtonHover = -1;
            this.dialogButtonIndex = -1;
            for (int i = 0; i < this.dialogButtons.length; ++i) {
                if (this.dialogButtons[i].contains(this.mouseX, this.mouseY)) {
                    this.dialogButtonIndex = i;
                    this.dialogButtonHover = i;
                    this.repaint();
                    break;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (this.status == 0) {
            this.mouseX = x;
            this.mouseY = y;
            this.mouseCellValid = false;
            if (!this.isMouseOnBoard(x, y)) {
                if (this.hintButtonRect.contains(this.mouseX, this.mouseY)) {
                    this.puzzle.setDigitHints(!this.puzzle.getDigitHints());
                    this.repaint();
                    return false;
                }
                for (int i = 0; i < this.buttonRects.length; ++i) {
                    if (this.buttonRects[i].contains(this.mouseX, this.mouseY)) {
                        switch (i) {
                            case 0: {
                                this.dialogIndex = i;
                                this.status = 3;
                                this.repaint();
                                return true;
                            }
                            case 1: {
                                this.previousPuzzleDate();
                                this.repaint();
                                return true;
                            }
                            case 2: {
                                this.nextPuzzleDate();
                                this.repaint();
                                return true;
                            }
                            case 3: {
                                this.getDatePuzzle();
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            else {
                if (!this.timeCounting) {
                    final Calendar cal = Calendar.getInstance();
                    this.startTime = cal.getTimeInMillis();
                    this.currentTime = this.startTime;
                    this.prevTime = this.currentTime;
                    this.playTime = 0L;
                    this.timeCounting = true;
                }
                this.mCellX = (this.mouseX - this.brLeft) / this.CellSize;
                this.mCellY = (this.mouseY - this.brTop) / this.CellSize;
                this.mouseCellValid = this.isCellOnBoard(this.mCellX, this.mCellY);
                if (this.mouseCellValid) {
                    if (this.puzzle.getCell(this.mCellX, this.mCellY) == 0) {
                        this.puzzle.checkValidDigits(this.mCellX, this.mCellY);
                        if (this.puzzle.getValidDigitCount() > 0) {
                            this.DigitX = this.brLeft + (this.mCellX + 1) * this.CellSize - (this.CellSize >> 1) + 3;
                            this.DigitY = this.brTop + (this.mCellY + 1) * this.CellSize - (this.CellSize >> 1) + 3;
                            this.Animation = 0;
                            this.status = 1;
                        }
                        else {
                            this.status = 0;
                        }
                    }
                    else if (!this.puzzle.IsFixedCell(this.mCellX, this.mCellY)) {
                        this.DigitX = this.brLeft + (this.mCellX + 1) * this.CellSize - (this.CellSize >> 1) + 3;
                        this.DigitY = this.brTop + (this.mCellY + 1) * this.CellSize - (this.CellSize >> 1) + 3;
                        this.Animation = 0;
                        this.status = 2;
                    }
                }
            }
        }
        else if (this.status == 1) {
            if (this.SelectedDigit != 0) {
                this.puzzle.setCell(this.mCellX, this.mCellY, (byte)this.SelectedDigit);
            }
            if (this.selectedHintDigit != 0) {
                this.puzzle.setCellHint(this.mCellX, this.mCellY, (byte)this.selectedHintDigit);
            }
            this.status = 0;
            this.SelectedDigit = 0;
            this.selectedHintDigit = 0;
            this.repaint();
            if (this.puzzle.mapCompleted()) {
                this.postPuzzleStats((int)this.playTime);
                this.status = 9;
                this.Animation = 0;
            }
        }
        else if (this.status == 2) {
            switch (this.MenuChoiceIndex) {
                case 0: {
                    this.puzzle.setCell(this.mCellX, this.mCellY, (byte)0);
                    break;
                }
                case 1: {
                    this.puzzle.solvePuzzle();
                    break;
                }
            }
            this.MenuChoiceIndex = -1;
            this.status = 0;
            this.Animation = 0;
            this.repaint();
        }
        else if (this.status == 3) {
            switch (this.dialogIndex) {
                case 0: {
                    switch (this.dialogButtonIndex) {
                        case 0: {
                            this.resetGame();
                            break;
                        }
                        case 1: {
                            this.status = 0;
                            break;
                        }
                    }
                    this.dialogButtonHover = -1;
                    this.dialogButtonIndex = -1;
                    break;
                }
                case 1: {
                    switch (this.dialogButtonIndex) {
                        case 0: {
                            this.Animation = 0;
                            this.timeCounting = false;
                            this.status = 0;
                            this.dialogIndex = -1;
                            break;
                        }
                        case 1: {
                            this.status = 9;
                            break;
                        }
                    }
                    this.dialogButtonHover = -1;
                    this.dialogButtonIndex = -1;
                    break;
                }
            }
        }
        else if (this.status == 9) {
            this.dialogIndex = 1;
            this.status = 3;
        }
        return false;
    }
    
    boolean isMouseOnBoard(final int x, final int y) {
        return x >= this.brLeft && y >= this.brTop && x <= this.brRight && y <= this.brBottom;
    }
    
    boolean isCellOnBoard(final int x, final int y) {
        return x >= 0 && x <= 8 && y >= 0 && y <= 8;
    }
    
    void launchBubble() {
        int i = 0;
        while (i < this.max_bubbles) {
            if (!this.bubble_active[i]) {
                this.bubble_y[i] = 410.0;
                this.bubble_speed[i] = 1.5;
                this.bubble_x[i] = this.width * Math.random();
                this.bubble_active[i] = true;
                this.bubble_size[i] = (int)(4.0 * Math.random());
                if (this.bubble_size[i] == 0) {
                    this.bubble_size[i] = 1;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
    }
    
    void drawAnimation(final Graphics g) {
        for (int i = 0; i < this.max_bubbles; ++i) {
            if (this.bubble_active[i]) {
                if (this.bubble_y[i] < 95.0) {
                    this.bubble_active[i] = false;
                    this.bubble_y[i] = this.height + 2;
                    this.bubble_speed[i] = 0.0;
                }
                else {
                    final double[] bubble_y = this.bubble_y;
                    final int n = i;
                    bubble_y[n] -= this.bubble_speed[i];
                    g.drawImage(this.AlternateImages[this.bubble_size[i]], (int)this.bubble_x[i], (int)this.bubble_y[i], this);
                }
                if (this.bubble_y[i] < 150.0 && this.FrameCounter % 4 == 0) {
                    this.bubble_speed[i] -= 0.1;
                    if (this.bubble_speed[i] < 0.1) {
                        this.bubble_active[i] = false;
                    }
                }
            }
        }
        int v = (int)(Math.random() * 500.0);
        if (v < 1) {
            v = 1;
        }
        if (this.FrameCounter % v == 0) {
            this.launchBubble();
        }
    }
    
    void drawButtons(final Graphics g) {
        int i = 0;
        int imageIndex = 50;
        for (i = 0; i < this.buttonRects.length; ++i) {
            imageIndex = this.buttonImageIndex[i];
            if (this.buttonHoverIndex == i) {
                ++imageIndex;
            }
            if (this.buttonRects[i].getWidth() != 0.0 && this.buttonRects[i].getHeight() != 0.0) {
                g.drawImage(this.Images[imageIndex], (int)this.buttonRects[i].getX(), (int)this.buttonRects[i].getY(), this);
            }
        }
        if (this.show_date_selection == 1) {
            String t = new String();
            t = this.dateToLocaleString(this.currentPuzzleDate);
            g.setFont(new Font("Arial", 0, 10));
            g.setColor(Color.black);
            g.drawString(t, this.date_selection_x + 13, this.date_selection_y + 13);
        }
    }
    
    void drawBanners(final Graphics g) {
        final int bannerSpeed = 75;
        final int bannerTimeout = 2000;
        if (this.bannerMode == 0) {
            this.bannerOffset -= this.timeSpan / 1000.0 * bannerSpeed;
            if (this.bannerOffset < 0.0) {
                this.bannerOffset = 0.0;
            }
            if (this.bannerOffset == 0.0) {
                this.bannerTimer += (int)this.timeSpan;
                if (this.bannerTimer > bannerTimeout) {
                    this.bannerTimer = 0;
                    this.bannerMode = 1;
                    this.bannerOffset = 0.0;
                }
            }
        }
        else if (this.bannerMode == 1) {
            this.bannerOffset -= this.timeSpan / 1000.0 * bannerSpeed;
            if (this.bannerOffset < -400.0) {
                this.bannerOffset = -400.0;
            }
            if (this.bannerOffset == -400.0) {
                this.bannerTimer += (int)this.timeSpan;
                if (this.bannerTimer > bannerTimeout) {
                    this.bannerTimer = 0;
                    this.bannerMode = 0;
                    this.bannerOffset = 400.0;
                    ++this.bannerIndex;
                    if (this.bannerIndex == this.maxBanners) {
                        this.bannerIndex = 0;
                    }
                }
            }
        }
        this.bannerOffset = Math.round(this.bannerOffset);
        g.drawImage(this.Banners[this.bannerIndex], (int)this.bannerOffset, 1, this);
    }
    
    void drawBoard(final Graphics g) {
        final int x = this.brLeft;
        final int y = this.brTop;
        g.drawImage(this.Images[45], 0, 0, this);
        if (this.alternate_mode) {
            this.drawAnimation(g);
        }
        if (this.bannersVisible == 1) {
            this.drawBanners(g);
        }
        if (this.status == 0 || this.status == 1 || this.status == 2 || this.status == 3 || this.status == 4 || this.status == 5) {
            if (this.dialogIndex != 1) {
                int i;
                if (this.puzzle.getDigitHints()) {
                    i = 47;
                }
                else {
                    i = 48;
                }
                if (this.mouseOnButton) {
                    if (i == 47) {
                        i = 49;
                    }
                    else {
                        i = 74;
                    }
                }
                g.drawImage(this.Images[i], (int)this.hintButtonRect.getX(), (int)this.hintButtonRect.getY(), this);
                this.drawButtons(g);
                g.drawImage(this.Images[36], 0, 0, this);
                if (this.mouseCellValid && !this.puzzle.IsFixedCell(this.mCellX, this.mCellY)) {
                    final int x2 = this.brLeft + this.mCellX * this.CellSize;
                    final int y2 = this.brTop + this.mCellY * this.CellSize;
                    if (this.alternate_mode) {
                        g.drawImage(this.AlternateImages[4], x2, y2, 30, 30, this);
                    }
                    else {
                        g.drawImage(this.Images[37], x2 + 4, y2 + 4, 24, 24, this);
                    }
                }
                if (this.status == 4) {
                    this.drawStartGameAnim(g);
                }
                else if (this.status == 5) {
                    this.drawEndGameAnim(g);
                }
                if (this.status != 4 && this.status != 5) {
                    for (i = 0; i < 81; ++i) {
                        if (this.puzzle.getIndexCell(i) != 0) {
                            final int x2 = i % 9 * this.CellSize;
                            final int y2 = i / 9 * this.CellSize;
                            int Index = this.puzzle.getIndexCell(i) - 1;
                            if (this.puzzle.IsFixedCell(x2 / this.CellSize, y2 / this.CellSize)) {
                                Index += 27;
                            }
                            g.drawImage(this.Images[Index], this.brLeft + x2 + 4, this.brTop + y2 + 4, 24, 24, this);
                        }
                        else {
                            final int hintCount = this.puzzle.getCellHintCountIndex(i);
                            if (hintCount != 0) {
                                final int x2 = 2 + this.brLeft + i % 9 * this.CellSize;
                                final int y2 = 2 + this.brTop + i / 9 * this.CellSize;
                                for (int ch = 0; ch < hintCount; ++ch) {
                                    final int hint = this.puzzle.getCellHintIndex(i, ch) - 1;
                                    switch (ch) {
                                        case 0: {
                                            g.drawImage(this.Images[59 + hint], x2, y2, 5, 5, this);
                                            break;
                                        }
                                        case 1: {
                                            g.drawImage(this.Images[59 + hint], x2 + 23, y2, 5, 5, this);
                                            break;
                                        }
                                        case 2: {
                                            g.drawImage(this.Images[59 + hint], x2, y2 + 23, 5, 5, this);
                                            break;
                                        }
                                        case 3: {
                                            g.drawImage(this.Images[59 + hint], x2 + 23, y2 + 23, 5, 5, this);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (this.dialogIndex == 1) {
                this.drawCompleted(g);
            }
        }
        if (this.debugMode) {
            g.drawString(String.valueOf(this.status), 10, 20);
        }
    }
    
    void drawStartGameAnim(final Graphics g) {
        ++this.Animation;
        this.digitDistance -= 8;
        if (this.digitDistance < 0) {
            this.digitDistance = 0;
            this.status = 0;
            return;
        }
        for (int i = 0; i < 81; ++i) {
            if (this.puzzle.getIndexCell(i) != 0) {
                int x1 = i % 9;
                int y1 = i / 9;
                if (this.puzzle.IsFixedCell(x1, y1)) {
                    x1 *= this.CellSize;
                    y1 *= this.CellSize;
                    int Index = this.puzzle.getIndexCell(i) - 1;
                    final double Angle = 4 * i * 3.141592653589793 / this.Animation;
                    final int y2 = this.brLeft + y1 + (int)(this.digitDistance * -Math.cos(Angle));
                    final int x2 = this.brTop + x1 + (int)(this.digitDistance * Math.sin(Angle));
                    Index = this.puzzle.getIndexCell(i) - 1 + 27;
                    g.drawImage(this.Images[Index], x2 + 4, y2 + 4, 24, 24, this);
                }
            }
        }
    }
    
    void drawEndGameAnim(final Graphics g) {
        ++this.Animation;
        this.digitDistance += 8;
        if (this.digitDistance > 400) {
            this.digitDistance = 400;
            this.puzzleDate = this.dateToFormatString("MM/dd/yyyy", this.currentPuzzleDate);
            this.puzzle.reset();
            this.Animation = 0;
            try {
                this.postPuzzleStats((int)this.playTime);
            }
            catch (Exception ex) {}
            this.initializePuzzle();
            this.timeCounting = false;
            this.dialogIndex = -1;
            this.newpuzzle = false;
            this.startGame();
            return;
        }
        for (int i = 0; i < 81; ++i) {
            if (this.puzzle.getIndexCell(i) != 0) {
                int x1 = i % 9;
                int y1 = i / 9;
                int Index = this.puzzle.getIndexCell(i) - 1;
                if (this.puzzle.IsFixedCell(x1, y1)) {
                    Index += 27;
                }
                x1 *= this.CellSize;
                y1 *= this.CellSize;
                final double Angle = 4 * i * 3.141592653589793 / this.Animation;
                final int y2 = this.brLeft + y1 + (int)(this.digitDistance * -Math.cos(Angle));
                final int x2 = this.brTop + x1 + (int)(this.digitDistance * Math.sin(Angle));
                g.drawImage(this.Images[Index], x2 + 4, y2 + 4, 24, 24, this);
            }
        }
    }
    
    void drawDigits(final Graphics g) {
        int mX = this.DigitX - this.CellSize / 2;
        if (mX > this.brRight) {
            mX = this.brRight;
        }
        if (mX < this.brLeft) {
            mX = this.brLeft;
        }
        int mY = this.DigitY - this.CellSize / 2;
        if (mY > this.brBottom) {
            mY = this.brBottom;
        }
        if (mY < this.brTop) {
            mY = this.brTop;
        }
        int vd = 0;
        for (int i = 0; i < 9; ++i) {
            if (this.puzzle.getValidDigit(i)) {
                ++vd;
                final double Angle = 360 / this.puzzle.getValidDigitCount() * vd * 3.141592653589793 / 180.0;
                double Distance = 6.0;
                final int x = (int)(this.Animation * Distance * Math.sin(Angle));
                final int y = (int)(this.Animation * Distance * -Math.cos(Angle));
                Distance = 3.0;
                final int xs = (int)(this.Animation * Distance * Math.sin(Angle)) + 5;
                final int ys = (int)(this.Animation * Distance * -Math.cos(Angle)) + 5;
                if (this.Animation == 10) {
                    this.DigitRects[i].setBounds(mX + x, mY + y, 24, 24);
                    this.digitHintRects[i].setBounds(mX + xs, mY + ys, 14, 13);
                    int Index;
                    if (this.SelectedDigit == i + 1) {
                        Index = 18 + i;
                    }
                    else {
                        Index = 9 + i;
                    }
                    g.drawImage(this.Images[Index], mX + x, mY + y, 24, 24, this);
                    if (this.selectedHintDigit == i + 1) {
                        g.drawImage(this.Images[58], mX + xs, mY + ys, 14, 13, this);
                    }
                    else {
                        g.drawImage(this.Images[57], mX + xs, mY + ys, 14, 13, this);
                    }
                }
                else {
                    g.drawImage(this.Images[9 + i], mX + x, mY + y, 24, 24, this);
                    g.drawImage(this.Images[58], mX + xs, mY + ys, 14, 13, this);
                }
            }
            else {
                this.DigitRects[i].setBounds(0, 0, 0, 0);
                this.digitHintRects[i].setBounds(0, 0, 0, 0);
            }
        }
    }
    
    void drawMenu(final Graphics g) {
        int j;
        if (this.debugMode) {
            j = 2;
        }
        else {
            j = 1;
        }
        int mX = this.DigitX - this.CellSize / 2;
        if (mX > this.brRight) {
            mX = this.brRight;
        }
        if (mX < this.brLeft) {
            mX = this.brLeft;
        }
        int mY = this.DigitY - this.CellSize / 2;
        if (mY > this.brBottom) {
            mY = this.brBottom;
        }
        if (mY < this.brTop) {
            mY = this.brTop;
        }
        for (int i = 0; i < j; ++i) {
            final double Angle = 90 * i * 3.141592653589793 / 180.0;
            final int x = (int)(this.Animation * 3.5 * Math.sin(Angle));
            final int y = (int)(this.Animation * 3.5 * -Math.cos(Angle));
            if (this.Animation == 10) {
                this.MenuChoiceRects[i].setBounds(mX + x, mY + y, 24, 24);
                int Index;
                if (this.MenuChoiceIndex == i) {
                    Index = 39 + i * 2;
                }
                else {
                    Index = 38 + i * 2;
                }
                g.drawImage(this.Images[Index], mX + x, mY + y, 24, 24, this);
            }
            else {
                g.drawImage(this.Images[38 + i * 2], mX + x, mY + y, 24, 24, this);
            }
        }
    }
    
    void drawNumber(final Graphics g, int x, final int y, final String text) {
        for (int i = 0; i < text.length(); ++i) {
            final char c = text.charAt(i);
            if (c != ' ') {
                final int a = Integer.parseInt(String.valueOf(c));
                g.drawImage(this.Images[a + 26], x, y, 24, 24, this);
                x += 30;
            }
        }
    }
    
    void drawAnswer(final Graphics g, final int x, final int y) {
        this.drawNumber(g, x, y, this.puzzle.getPuzzleAnswerCode());
    }
    
    void drawDnis(final Graphics g, final int x, final int y) {
    }
    
    void drawStringCenter(final Graphics g, final int y, final String text) {
        final int m = this.width / 2;
        final int w = g.getFontMetrics().stringWidth(text);
        g.drawString(text, m - w / 2, y);
    }
    
    void drawResetQuestion(final Graphics g) {
        final int x = (this.width - 313) / 2;
        final int y = (this.height - 181) / 2;
        g.drawImage(this.Images[52], x, y, this);
        this.dialogButtons[0].setBounds(x + 82, y + 140, 69, 18);
        this.dialogButtons[1].setBounds(x + 161, y + 140, 69, 18);
        if (this.dialogButtonHover == 0) {
            g.drawImage(this.Images[54], (int)this.dialogButtons[0].getX(), (int)this.dialogButtons[0].getY(), this);
        }
        else {
            g.drawImage(this.Images[53], (int)this.dialogButtons[0].getX(), (int)this.dialogButtons[0].getY(), this);
        }
        if (this.dialogButtonHover == 1) {
            g.drawImage(this.Images[56], (int)this.dialogButtons[1].getX(), (int)this.dialogButtons[1].getY(), this);
        }
        else {
            g.drawImage(this.Images[55], (int)this.dialogButtons[1].getX(), (int)this.dialogButtons[1].getY(), this);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 0, 14));
        final String text = new String(this.language[this.languageId][0]);
        final int w = g.getFontMetrics().stringWidth(text);
        this.drawMultiLine(g, text, x, y + 78);
    }
    
    void drawMultiLine(final Graphics g, final String text, final int x, int y) {
        final String[] parts = text.split("\r");
        for (int i = 0; i < parts.length; ++i) {
            final int w = g.getFontMetrics().stringWidth(parts[i]);
            g.drawString(parts[i], x + (313 - w) / 2, y);
            y += 20;
        }
    }
    
    void drawContinueQuestion(final Graphics g) {
        final int x = (this.width - 313) / 2;
        final int y = (this.height - 181) / 2;
        g.drawImage(this.Images[52], x, y, this);
        this.dialogButtons[0].setBounds(x + 82, y + 110, 69, 18);
        this.dialogButtons[1].setBounds(x + 161, y + 110, 69, 18);
        if (this.dialogButtonHover == 0) {
            g.drawImage(this.Images[54], (int)this.dialogButtons[0].getX(), (int)this.dialogButtons[0].getY(), this);
        }
        else {
            g.drawImage(this.Images[53], (int)this.dialogButtons[0].getX(), (int)this.dialogButtons[0].getY(), this);
        }
        if (this.dialogButtonHover == 1) {
            g.drawImage(this.Images[56], (int)this.dialogButtons[1].getX(), (int)this.dialogButtons[1].getY(), this);
        }
        else {
            g.drawImage(this.Images[55], (int)this.dialogButtons[1].getX(), (int)this.dialogButtons[1].getY(), this);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 0, 14));
        final String text = new String(this.language[this.languageId][1]);
        final int w = g.getFontMetrics().stringWidth(text);
        g.drawString(text, x + (313 - w) / 2, y + 78);
    }
    
    void drawCompletedAnim(final Graphics g) {
        this.drawButtons(g);
        g.drawImage(this.Images[36], 0, 0, this);
        this.digitDistance += 3;
        if (this.digitDistance > 400) {
            this.digitDistance = 400;
        }
        for (int i = 0; i < 81; ++i) {
            if (this.puzzle.getIndexCell(i) != 0) {
                final int x1 = i % 9 * this.CellSize;
                final int y1 = i / 9 * this.CellSize;
                int Index = this.puzzle.getIndexCell(i) - 1;
                if (this.puzzle.IsFixedCell(x1 / this.CellSize, y1 / this.CellSize)) {
                    Index += 27;
                }
                g.drawImage(this.Images[Index], this.brLeft + x1 + 4, this.brTop + y1 + 4, 24, 24, this);
            }
        }
        if (this.Animation > 500) {
            this.status = 9;
        }
    }
    
    void drawCompleted(final Graphics g) {
        g.drawImage(this.Images[46], 0, 0, this);
        g.setFont(new Font("Arial", 0, 18));
        g.setColor(Color.black);
        if (this.pleasure_mode == 0) {
            if (this.center_code == 1) {
                this.drawStringCenter(g, this.code_y, this.puzzle.getPuzzleCode());
            }
            else {
                g.drawString(this.puzzle.getPuzzleCode(), this.code_x, this.code_y);
            }
            if (this.center_dnis == 1) {
                this.drawStringCenter(g, this.dnis_y, this.puzzle.getServiceNumber());
            }
            else {
                g.drawString(this.puzzle.getServiceNumber(), this.dnis_x, this.dnis_y);
            }
        }
        if (this.center_result == 1) {
            int m = 0;
            m = this.width / 2 - 135;
            this.drawAnswer(g, m, this.puzzleresult_y);
        }
        else {
            this.drawAnswer(g, this.puzzleresult_x, this.puzzleresult_y);
        }
    }
    
    int postPuzzleStats(final int playTime) {
        if (playTime == 0) {
            return 0;
        }
        String basePath = new String();
        basePath = this.getCodeBase().toExternalForm();
        final int playMinutes = playTime / 1000;
        return this.puzzle.setPuzzleStats(basePath, playMinutes);
    }
    
    String getNewPuzzleDate() {
        final Calendar cal = Calendar.getInstance();
        final Date date = cal.getTime();
        this.currentPuzzleDate.setTime(date.getTime());
        final DateFormat localFormat = new SimpleDateFormat("MM/dd/yyyy");
        return localFormat.format(date);
    }
    
    void resetGame() {
        this.endGame();
    }
    
    void offsetPuzzleDate(final long value) {
        final long maxValue = new Date().getTime();
        long timeValue = this.currentPuzzleDate.getTime();
        timeValue += value;
        if (timeValue > maxValue) {
            timeValue = maxValue;
        }
        this.currentPuzzleDate.setTime(timeValue);
    }
    
    void previousPuzzleDate() {
        this.offsetPuzzleDate(-86400000L);
    }
    
    void nextPuzzleDate() {
        this.offsetPuzzleDate(86400000L);
    }
    
    void initializePuzzle() {
        String basePath = new String();
        basePath = this.getCodeBase().toExternalForm();
        this.puzzle.getPuzzleFromURL(basePath, this.appId, this.puzzleDate);
        this.currentPuzzleDate = this.puzzle.getPuzzleDate();
        this.puzzle.setDigitHints(false);
    }
    
    void getDatePuzzle() {
        if (this.effect_mode) {
            this.newpuzzle = true;
            this.endGame();
        }
        else {
            this.puzzleDate = this.dateToFormatString("MM/dd/yyyy", this.currentPuzzleDate);
            this.puzzle.reset();
            this.Animation = 0;
            this.postPuzzleStats((int)this.playTime);
            this.postPuzzleStats((int)this.playTime);
            this.initializePuzzle();
            this.timeCounting = false;
            this.startGame();
            this.dialogIndex = -1;
        }
    }
    
    String dateToLocaleString(final Date ADate) {
        return this.dateToFormatString("dd/MM/yyyy", ADate);
    }
    
    String dateToFormatString(final String fmt, final Date ADate) {
        final DateFormat format = new SimpleDateFormat(fmt);
        return format.format(ADate);
    }
    
    public String getJavaVersion() {
        return System.getProperty("java.version");
    }
    
    void startGame() {
        this.playTime = 0L;
        if (this.effect_mode) {
            this.digitDistance = 400;
            this.Animation = 100;
            this.status = 4;
        }
        else {
            this.status = 0;
        }
    }
    
    void endGame() {
        if (this.effect_mode) {
            this.digitDistance = 0;
            this.Animation = 100;
            this.status = 5;
        }
        else {
            this.puzzle.reset();
            this.startGame();
        }
    }
    
    static {
        Sudoku.imageNames = new String[] { "nr1.gif", "nr2.gif", "nr3.gif", "nr4.gif", "nr5.gif", "nr6.gif", "nr7.gif", "nr8.gif", "nr9.gif", "big1.gif", "big2.gif", "big3.gif", "big4.gif", "big5.gif", "big6.gif", "big7.gif", "big8.gif", "big9.gif", "sd1.gif", "sd2.gif", "sd3.gif", "sd4.gif", "sd5.gif", "sd6.gif", "sd7.gif", "sd8.gif", "sd9.gif", "fnr1.gif", "fnr2.gif", "fnr3.gif", "fnr4.gif", "fnr5.gif", "fnr6.gif", "fnr7.gif", "fnr8.gif", "fnr9.gif", "map.gif", "marker.gif", "eraser.gif", "eraser_active.gif", "menu_clear.gif", "menu_clear_active.gif", "star1.gif", "star2.gif", "star3.gif", "background.gif", "done.gif", "digit_hint_on.gif", "digit_hint_off.gif", "digit_hint_hover_on.gif", "button_reset.gif", "button_reset_hover.gif", "dialog.gif", "button_yes.gif", "button_yes_hover.gif", "button_no.gif", "button_no_hover.gif", "digit_hint.gif", "digit_hint_hover.gif", "hint_1.gif", "hint_2.gif", "hint_3.gif", "hint_4.gif", "hint_5.gif", "hint_6.gif", "hint_7.gif", "hint_8.gif", "hint_9.gif", "arrow_left.gif", "arrow_left_hover.gif", "arrow_right.gif", "arrow_right_hover.gif", "get_puzzle.gif", "get_puzzle_hover.gif", "digit_hint_hover_off.gif" };
        Sudoku.alternateNames = new String[] { "bubble_small.gif", "bubble_medium.gif", "bubble_large.gif", "bubble_huge.gif", "cap.gif" };
    }
}
