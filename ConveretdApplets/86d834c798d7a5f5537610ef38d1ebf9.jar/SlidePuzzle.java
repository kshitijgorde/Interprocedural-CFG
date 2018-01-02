import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.awt.Color;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SlidePuzzle extends Applet
{
    PuzzleFramedArea puzzleFramedArea;
    ControlFramedArea controlFramedArea;
    public int hnum;
    public int vnum;
    int count;
    public int scrollingTime;
    public URL imageURL;
    public Color backgroundColor;
    public Color emptySpaceColor;
    public boolean piecesHaveBorders;
    public boolean enableScrolling;
    public boolean piecesHaveButtonEffect;
    public boolean alwaysSolvable;
    public String solveThePuzzleText;
    public String imageStillLoadingText;
    public String userHasWonText;
    public String timeText;
    public String movesText;
    public String scrambleText;
    
    public SlidePuzzle() {
        this.hnum = 4;
        this.vnum = 4;
        this.count = 0;
        this.scrollingTime = 1000;
        this.piecesHaveBorders = true;
        this.enableScrolling = true;
        this.piecesHaveButtonEffect = true;
        this.alwaysSolvable = true;
        this.solveThePuzzleText = "Solve the puzzle !";
        this.imageStillLoadingText = "Image loading ...";
        this.userHasWonText = "Congratulations, you win !";
        this.timeText = "Time (seconds):";
        this.movesText = "Moves :";
        this.scrambleText = "Scramble";
    }
    
    public void Count() {
        ++this.count;
        this.controlFramedArea.increaseMoves();
        if (this.count == 1) {
            this.controlFramedArea.setFreeze(false);
        }
    }
    
    public String getAppletInfo() {
        return "SlidePuzzle version 1.0 (C) 1998 by Paul Koerber (paul.koerber@rug.ac.be)";
    }
    
    public int getCount() {
        return this.count;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "image", "URL", "an image source" }, { "hnum", "int", "number of horizontal partitions" }, { "vnum", "int", "number of vertical partitions" }, { "backgroundcolor", "hex number (RGB)", "background color" }, { "emptyspacecolor", "hex number (RGB)", "empty space color" }, { "soundfile", "URL", "action sound" }, { "pieceshaveborders", "boolean (true/false)", "pieces have borders ?" }, { "pieceshavebuttoneffect", "boolean (true/false)", "pieces show button behaviour ?" }, { "enablescrolling", "boolean (true/false)", "is scrolling enabled ?" }, { "scrollingtime", "int", "scrolling time in milliseconds" }, { "alwayssolvable", "boolean (true/false)", "puzzle is always solvable ?" }, { "solvethepuzzletext", "String", "label text" }, { "imagestillloadingtext", "String", "label text" }, { "userhaswontext", "String", "label text" }, { "timetext", "String", "timelabel text" }, { "movestext", "String", "number of moves text" }, { "scrambletext", "String", "scramble button text" } };
    }
    
    public void init() {
        final String parameter = this.getParameter("VNUM");
        if (parameter != null) {
            try {
                this.vnum = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        if (this.vnum < 2) {
            this.vnum = 3;
        }
        if (this.hnum < 2) {
            this.hnum = 3;
        }
        final String parameter2 = this.getParameter("HNUM");
        if (parameter2 != null) {
            try {
                this.hnum = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex2) {}
        }
        final String parameter3 = this.getParameter("SCROLLINGTIME");
        if (parameter3 != null) {
            try {
                this.scrollingTime = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex3) {}
        }
        try {
            final String parameter4 = this.getParameter("IMAGE");
            if (parameter4 != null) {
                this.imageURL = new URL(this.getCodeBase(), parameter4);
            }
        }
        catch (MalformedURLException ex4) {}
        String s;
        if (this.imageURL != null) {
            final String property = System.getProperty("file.separator", "\\");
            s = this.imageURL.getFile();
            int n = s.lastIndexOf(property);
            if (n == -1) {
                n = s.lastIndexOf("/");
            }
            if (n != -1) {
                s = s.substring(n + 1);
            }
        }
        else {
            s = "No image";
        }
        try {
            final String parameter5 = this.getParameter("BACKGROUNDCOLOR");
            if (parameter5 == null) {
                throw new NumberFormatException();
            }
            this.backgroundColor = this.parseColorString(parameter5);
        }
        catch (NumberFormatException ex5) {
            this.backgroundColor = Color.white;
        }
        this.setBackground(this.backgroundColor);
        try {
            final String parameter6 = this.getParameter("EMPTYSPACECOLOR");
            if (parameter6 == null) {
                throw new NumberFormatException();
            }
            this.emptySpaceColor = this.parseColorString(parameter6);
        }
        catch (NumberFormatException ex6) {
            this.emptySpaceColor = Color.white;
        }
        final String parameter7 = this.getParameter("PIECESHAVEBORDERS");
        if (parameter7 != null && parameter7.toLowerCase().equals("false")) {
            this.piecesHaveBorders = false;
        }
        final String parameter8 = this.getParameter("ENABLESCROLLING");
        if (parameter8 != null && parameter8.toLowerCase().equals("false")) {
            this.enableScrolling = false;
        }
        final String parameter9 = this.getParameter("PIECESHAVEBUTTONEFFECT");
        if (parameter9 != null && parameter9.toLowerCase().equals("false")) {
            this.piecesHaveButtonEffect = false;
        }
        final String parameter10 = this.getParameter("ALWAYSSOLVABLE");
        if (parameter10 != null && parameter10.toLowerCase().equals("false")) {
            this.alwaysSolvable = false;
        }
        final String parameter11 = this.getParameter("SOLVETHEPUZZLETEXT");
        if (parameter11 != null) {
            this.solveThePuzzleText = parameter11;
        }
        final String parameter12 = this.getParameter("IMAGESTILLLOADINGTEXT");
        if (parameter12 != null) {
            this.imageStillLoadingText = parameter12;
        }
        final String parameter13 = this.getParameter("USERHASWONTEXT");
        if (parameter13 != null) {
            this.userHasWonText = parameter13;
        }
        final String parameter14 = this.getParameter("TIMETEXT");
        if (parameter14 != null) {
            this.timeText = parameter14;
        }
        final String parameter15 = this.getParameter("MOVESTEXT");
        if (parameter15 != null) {
            this.movesText = parameter15;
        }
        final String parameter16 = this.getParameter("SCRAMBLETEXT");
        if (parameter16 != null) {
            this.scrambleText = parameter16;
        }
        this.setLayout(new FlowLayout(1, 15, 15));
        (this.controlFramedArea = new ControlFramedArea(this, "Control Panel")).setBackground(new Color(191, 191, 191));
        this.add(this.puzzleFramedArea = new PuzzleFramedArea(this, s));
        this.add(this.controlFramedArea);
        this.validate();
    }
    
    private Color parseColorString(final String s) throws NumberFormatException {
        if (s.length() == 6) {
            return new Color(Integer.valueOf(s.substring(0, 2), 16), Integer.valueOf(s.substring(2, 4), 16), Integer.valueOf(s.substring(4, 6), 16));
        }
        throw new NumberFormatException("Incorrect lenght");
    }
    
    public void resetCounter() {
        this.count = 0;
        this.controlFramedArea.resetMoves();
        this.controlFramedArea.setFreeze(true);
    }
    
    public void scramble() {
        this.resetCounter();
        this.controlFramedArea.setStartTime();
        this.puzzleFramedArea.scramble();
        this.controlFramedArea.validate();
    }
    
    public void setNoImage() {
        if (this.puzzleFramedArea != null) {
            this.puzzleFramedArea.setTitle("No Image");
        }
    }
    
    public void start() {
        this.scramble();
    }
    
    public void updateLabel(final String s) {
        this.controlFramedArea.updateLabel(s);
    }
    
    public void userHasWon() {
        this.updateLabel(this.userHasWonText);
        this.controlFramedArea.setFreeze(true);
    }
}
