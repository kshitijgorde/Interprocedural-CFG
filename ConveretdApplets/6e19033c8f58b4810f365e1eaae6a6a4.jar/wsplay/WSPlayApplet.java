// 
// Decompiled by Procyon v0.5.30
// 

package wsplay;

import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.ParseException;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Date;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.Color;
import java.awt.TextField;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class WSPlayApplet extends Applet implements MouseListener, MouseMotionListener
{
    boolean isStandalone;
    String WordPosList;
    String FillPosList;
    String SolveAnswers;
    String WordLineTypes;
    int GridLeftPos;
    int GridTopPos;
    int GridWidth;
    int GridHeight;
    int CellWidth;
    int WordsLeftPos;
    int WordsTopPos;
    int WordsLineHeight;
    int WordsWidth;
    int NoteWidth;
    int NoteHeight;
    int TextTop;
    int TimerTop;
    int TimerLeft;
    int TimerJust;
    int ButtonsTop;
    int ButtonsLeft;
    int ButtonsWidth;
    int ButtonsHeight;
    int ButtonsSpacer;
    int RightWidth;
    int ScrollButtonHeight;
    int totalWordListHeight;
    int WordsTopOffset;
    int NoteTopOffset;
    int textHeight;
    int timerWidth;
    int lastPointerLine;
    int saveCounter;
    int AudioTop;
    int AudioLeft;
    int AudioSize;
    int AudioHorzIn;
    int AudioVertIn;
    int WordsSliderTop;
    int NoteSliderTop;
    int SliderLeft;
    int SliderWidth;
    int SliderHeight;
    int sliderMoveOffset;
    int lastWordsSliderTop;
    int lastNoteSliderTop;
    int WordsFound;
    int WordCount;
    int LineCount;
    boolean[] foundWords;
    boolean[] solvedWords;
    boolean[] questionLine;
    boolean[] unscrambleLine;
    int wordPicked;
    int pointerHeight;
    int pointerWidth;
    int SliderBarLength;
    int SliderBarTop;
    int SliderBarBottom;
    int WordsScrollLength;
    int NoteScrollLength;
    int lastxPos;
    int lastyPos;
    int[] xPoints;
    int[] yPoints;
    boolean appletWasStopped;
    boolean puzzleSolved;
    boolean ShowPlay;
    boolean ShowSolve;
    boolean ShowNote;
    boolean AllowFullSolve;
    boolean FullSolveWins;
    boolean AllowReset;
    boolean SolveUnhideFirst;
    boolean ScrollWords;
    boolean ScrollNote;
    boolean MovingSlider;
    boolean SolvePressed;
    boolean aboutImageLoaded;
    boolean handsOnWord;
    boolean PlaySound;
    boolean ValidData;
    boolean puzzleLoaded;
    boolean audioImageLoaded;
    boolean startCircling;
    boolean endCircling;
    boolean cheated;
    boolean inPuzzleMode;
    boolean SentenceSolved;
    boolean SpecialAboutBox;
    boolean NoSpaceInHidden;
    boolean HideNameOnAbout;
    boolean DebugMode;
    boolean SayWordClick;
    boolean SayWordCircle;
    boolean ShowMuteButton;
    boolean UseBaseSounds;
    boolean UseTutorialSounds;
    boolean HasScrambled;
    boolean HasHidden;
    String WinAddress;
    String WSPAddress;
    String Sentence;
    String SolveString;
    String RegistrationName;
    String Passcode;
    String BasePuzzleName;
    String JavaCheckString;
    String CorruptString;
    String TrialPeriodString;
    String RegDateAString;
    String RegDateBString;
    String answerValue;
    String WinFile;
    String PointerFile;
    String AudioOnFile;
    String AudioOffFile;
    String AboutFile;
    int AboutCode;
    String AboutAddress;
    String TextPAS;
    String TextPZI;
    String TextNTI;
    String TextWAF;
    String TextSVI;
    String TextOWS;
    String TextWUS;
    String TextSPA;
    String TextSBP;
    String TextAOL;
    String TextSLS;
    String TextSBU;
    String TextELV;
    String TextSWH;
    String TextSFR;
    String TextFOW;
    String TextAFW;
    String TextNCW;
    String TextLOP;
    String TextISC;
    String TextPTS;
    String TextPAU;
    String TextTIM;
    String TextJNP;
    String TextINP;
    String TextWFN;
    String TextWFA;
    String TextWFP;
    String TextSON;
    String TextSOF;
    String TextHSS;
    String TextHSN;
    String TextIN1;
    String TextIN2;
    String TextIN3;
    String TextPCB;
    String ErrorWIN;
    String ErrorCOR;
    String ErrorNOT;
    String ErrorPUZ;
    String ErrorABT;
    String ErrorPNL;
    String ErrorGNL;
    String ErrorTNL;
    String ErrorANL;
    boolean LoadError;
    boolean frameLoaded;
    boolean CirclingWord;
    boolean SolveDouble;
    MediaTracker tracker;
    Toolkit toolkit;
    FontMetrics fm;
    TextField answerTextField;
    StartThread sthread;
    SliderThread slThread;
    TimerThread tthread;
    CircleThread cThread;
    String DebugString;
    int playMode;
    int appletWidth;
    int appletHeight;
    int winLeft;
    int winTop;
    int AboutLeft;
    int AboutTop;
    int AboutButtonLeft;
    int SolveButtonLeft;
    int NoteButtonLeft;
    int PlayButtonLeft;
    int WordListLine;
    int CellXOffset;
    int CellYOffset;
    int WinPause;
    int xPos;
    int yPos;
    Color TextColor;
    String TextColorString;
    Color BackColor;
    String BackColorString;
    Color SolveCircleColor;
    String SolveCircleColorString;
    Color CirclingColor;
    String CirclingColorString;
    Color CircledColor;
    String CircledColorString;
    AppletContext ac;
    URL jumpUrl;
    URL aboutUrl;
    boolean doJump;
    int xStartCell;
    int yStartCell;
    int xEndCell;
    int yEndCell;
    int oxStartCell;
    int oyStartCell;
    int oxEndCell;
    int oyEndCell;
    Image frameImage;
    Image gridImage;
    Image gridLoadImage;
    Image noteImage;
    Image aboutImage;
    Image wordsImage;
    Image buttonsImage;
    Image sliderImage;
    Image actualListImage;
    Image pointerImage;
    Image winImage;
    Image pointerSaveImage;
    Image audioOnImage;
    Image audioOffImage;
    Image dbufImage;
    Graphics dbufGraphics;
    Label messageLabel;
    Label timerLabel;
    Font LargeFont;
    Font SmallFont;
    AudioClip CircleSound;
    AudioClip SliderSound;
    AudioClip GoodSound;
    AudioClip BadSound;
    AudioClip ButtonSound;
    AudioClip WinSound;
    AudioClip ClickWordSound;
    AudioClip FirstClickWordSound;
    AudioClip ClickSentenceSound;
    AudioClip FirstClickSentenceSound;
    AudioClip SolveUnscrambleSound;
    AudioClip FirstSolveUnscrambleSound;
    AudioClip SolveHiddenSound;
    AudioClip FirstSolveHiddenSound;
    AudioClip SolveCircleSound;
    AudioClip FirstSolveCircleSound;
    AudioClip SolveAllSound;
    AudioClip SolveErrorSound;
    AudioClip SolveAllNoteSound;
    AudioClip StartUnscrambleSound;
    AudioClip FirstStartUnscrambleSound;
    AudioClip FinishUnscrambleSound;
    AudioClip FirstFinishUnscrambleSound;
    AudioClip StartHiddenSound;
    AudioClip FirstStartHiddenSound;
    AudioClip FinishHiddenSound;
    AudioClip FirstFinishHiddenSound;
    AudioClip StartPhraseSound;
    AudioClip PlayStandardSound;
    AudioClip FirstPlayStandardSound;
    AudioClip PlayHiddenSound;
    AudioClip FirstPlayHiddenSound;
    AudioClip PlayUnscrambleSound;
    AudioClip FirstPlayUnscrambleSound;
    AudioClip NoteButtonSound;
    AudioClip FirstNoteButtonSound;
    AudioClip AboutButtonSound;
    boolean PlayFirstPlay;
    boolean PlayFirstClickWord;
    boolean PlayFirstClickSentence;
    boolean PlayFirstSolveHidden;
    boolean PlayFirstSolveUnscramble;
    boolean PlayFirstSolveCircle;
    boolean PlayFirstStartUnscramble;
    boolean PlayFirstFinishUnscramble;
    boolean PlayFirstStartHidden;
    boolean PlayFirstFinishHidden;
    AudioClip[] WordSound;
    boolean[] WordSoundLoaded;
    String CircleSoundFile;
    String SliderSoundFile;
    String GoodSoundFile;
    String BadSoundFile;
    String ButtonSoundFile;
    String WinSoundFile;
    String ClickWordSoundFile;
    String FirstClickWordSoundFile;
    String ClickSentenceSoundFile;
    String FirstClickSentenceSoundFile;
    String SolveHiddenSoundFile;
    String FirstSolveHiddenSoundFile;
    String SolveUnscrambleSoundFile;
    String FirstSolveUnscrambleSoundFile;
    String SolveCircleSoundFile;
    String FirstSolveCircleSoundFile;
    String SolveAllSoundFile;
    String SolveErrorSoundFile;
    String SolveAllNoteSoundFile;
    String StartUnscrambleSoundFile;
    String FirstStartUnscrambleSoundFile;
    String StartHiddenSoundFile;
    String FirstStartHiddenSoundFile;
    String FinishUnscrambleSoundFile;
    String FirstFinishUnscrambleSoundFile;
    String FinishHiddenSoundFile;
    String FirstFinishHiddenSoundFile;
    String StartPhraseSoundFile;
    String PlayStandardSoundFile;
    String FirstPlayStandardSoundFile;
    String PlayHiddenSoundFile;
    String FirstPlayHiddenSoundFile;
    String PlayUnscrambleSoundFile;
    String FirstPlayUnscrambleSoundFile;
    String NoteButtonSoundFile;
    String FirstNoteButtonSoundFile;
    String AboutButtonSoundFile;
    String WordSoundsLocation;
    String ActionSoundsLocation;
    String PuzzleGraphicsLocation;
    String SharedGraphicsLocation;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public WSPlayApplet() {
        this.isStandalone = false;
        this.saveCounter = 0;
        this.lastxPos = 0;
        this.lastyPos = 0;
        this.appletWasStopped = false;
        this.puzzleSolved = false;
        this.ShowPlay = true;
        this.ShowSolve = false;
        this.ShowNote = false;
        this.AllowFullSolve = false;
        this.FullSolveWins = true;
        this.AllowReset = true;
        this.SolveUnhideFirst = true;
        this.ScrollWords = true;
        this.ScrollNote = true;
        this.MovingSlider = false;
        this.SolvePressed = false;
        this.aboutImageLoaded = false;
        this.PlaySound = false;
        this.ValidData = false;
        this.puzzleLoaded = false;
        this.audioImageLoaded = false;
        this.inPuzzleMode = false;
        this.DebugMode = false;
        this.HasScrambled = false;
        this.HasHidden = false;
        this.WSPAddress = "http://wordsplashpro.chronasoft.com";
        this.CorruptString = "ERROR: Puzzle is corrupt or was tampered with.";
        this.TrialPeriodString = "ERROR: Puzzle is past the unregistered applet trial period.";
        this.answerValue = "";
        this.AudioOnFile = "";
        this.LoadError = false;
        this.frameLoaded = false;
        this.CirclingWord = false;
        this.SolveDouble = false;
        this.tthread = null;
        this.DebugString = "";
        this.playMode = 0;
        this.WordListLine = 0;
        this.CellXOffset = 0;
        this.CellYOffset = 0;
        this.frameImage = null;
        this.audioOnImage = null;
        this.audioOffImage = null;
        this.messageLabel = new Label();
        this.timerLabel = new Label();
        this.LargeFont = new Font("Dialog", 0, 18);
        this.SmallFont = new Font("Dialog", 0, 12);
        this.CircleSound = null;
        this.SliderSound = null;
        this.GoodSound = null;
        this.BadSound = null;
        this.ButtonSound = null;
        this.WinSound = null;
        this.ClickWordSound = null;
        this.FirstClickWordSound = null;
        this.ClickSentenceSound = null;
        this.FirstClickSentenceSound = null;
        this.SolveUnscrambleSound = null;
        this.FirstSolveUnscrambleSound = null;
        this.SolveHiddenSound = null;
        this.FirstSolveHiddenSound = null;
        this.SolveCircleSound = null;
        this.FirstSolveCircleSound = null;
        this.SolveAllSound = null;
        this.SolveErrorSound = null;
        this.SolveAllNoteSound = null;
        this.StartUnscrambleSound = null;
        this.FirstStartUnscrambleSound = null;
        this.FinishUnscrambleSound = null;
        this.FirstFinishUnscrambleSound = null;
        this.StartHiddenSound = null;
        this.FirstStartHiddenSound = null;
        this.FinishHiddenSound = null;
        this.FirstFinishHiddenSound = null;
        this.StartPhraseSound = null;
        this.PlayStandardSound = null;
        this.FirstPlayStandardSound = null;
        this.PlayHiddenSound = null;
        this.FirstPlayHiddenSound = null;
        this.PlayUnscrambleSound = null;
        this.FirstPlayUnscrambleSound = null;
        this.NoteButtonSound = null;
        this.FirstNoteButtonSound = null;
        this.AboutButtonSound = null;
        this.PlayFirstPlay = true;
        this.PlayFirstClickWord = true;
        this.PlayFirstClickSentence = true;
        this.PlayFirstSolveHidden = true;
        this.PlayFirstSolveUnscramble = true;
        this.PlayFirstSolveCircle = true;
        this.PlayFirstStartUnscramble = true;
        this.PlayFirstFinishUnscramble = true;
        this.PlayFirstStartHidden = true;
        this.PlayFirstFinishHidden = true;
        this.CircleSoundFile = "";
        this.SliderSoundFile = "";
        this.GoodSoundFile = "";
        this.BadSoundFile = "";
        this.ButtonSoundFile = "";
        this.WinSoundFile = "";
        this.ClickWordSoundFile = "";
        this.FirstClickWordSoundFile = "";
        this.ClickSentenceSoundFile = "";
        this.FirstClickSentenceSoundFile = "";
        this.SolveHiddenSoundFile = "";
        this.FirstSolveHiddenSoundFile = "";
        this.SolveUnscrambleSoundFile = "";
        this.FirstSolveUnscrambleSoundFile = "";
        this.SolveCircleSoundFile = "";
        this.FirstSolveCircleSoundFile = "";
        this.SolveAllSoundFile = "";
        this.SolveErrorSoundFile = "";
        this.SolveAllNoteSoundFile = "";
        this.StartUnscrambleSoundFile = "";
        this.FirstStartUnscrambleSoundFile = "";
        this.StartHiddenSoundFile = "";
        this.FirstStartHiddenSoundFile = "";
        this.FinishUnscrambleSoundFile = "";
        this.FirstFinishUnscrambleSoundFile = "";
        this.FinishHiddenSoundFile = "";
        this.FirstFinishHiddenSoundFile = "";
        this.StartPhraseSoundFile = "";
        this.PlayStandardSoundFile = "";
        this.FirstPlayStandardSoundFile = "";
        this.PlayHiddenSoundFile = "";
        this.FirstPlayHiddenSoundFile = "";
        this.PlayUnscrambleSoundFile = "";
        this.FirstPlayUnscrambleSoundFile = "";
        this.NoteButtonSoundFile = "";
        this.FirstNoteButtonSoundFile = "";
        this.AboutButtonSoundFile = "";
        this.WordSoundsLocation = "";
        this.ActionSoundsLocation = "";
        this.PuzzleGraphicsLocation = "";
        this.SharedGraphicsLocation = "";
    }
    
    public void init() {
        try {
            this.WordPosList = this.getParameter("WordPosList", "");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.FillPosList = this.getParameter("FillPosList", "");
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        try {
            this.SolveAnswers = this.getParameter("SolveAnswers", "");
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            this.WordLineTypes = this.getParameter("LineTypes", "");
        }
        catch (Exception ex4) {
            ex4.printStackTrace();
        }
        try {
            this.GridLeftPos = Integer.parseInt(this.getParameter("GridLeftPos", "0"));
        }
        catch (Exception ex5) {
            ex5.printStackTrace();
        }
        try {
            this.GridTopPos = Integer.parseInt(this.getParameter("GridTopPos", "0"));
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
        try {
            this.GridWidth = Integer.parseInt(this.getParameter("GridWidth", "0"));
        }
        catch (Exception ex7) {
            ex7.printStackTrace();
        }
        try {
            this.GridHeight = Integer.parseInt(this.getParameter("GridHeight", "0"));
        }
        catch (Exception ex8) {
            ex8.printStackTrace();
        }
        try {
            this.CellWidth = Integer.parseInt(this.getParameter("CellWidth", "0"));
        }
        catch (Exception ex9) {
            ex9.printStackTrace();
        }
        try {
            this.CellXOffset = Integer.parseInt(this.getParameter("CellXOffset", "0"));
        }
        catch (Exception ex10) {
            ex10.printStackTrace();
        }
        try {
            this.CellYOffset = Integer.parseInt(this.getParameter("CellYOffset", "0"));
        }
        catch (Exception ex11) {
            ex11.printStackTrace();
        }
        try {
            this.WordsLeftPos = Integer.parseInt(this.getParameter("WordsLeftPos", "0"));
        }
        catch (Exception ex12) {
            ex12.printStackTrace();
        }
        try {
            this.WordsTopPos = Integer.parseInt(this.getParameter("WordsTopPos", "0"));
        }
        catch (Exception ex13) {
            ex13.printStackTrace();
        }
        try {
            this.WordsLineHeight = Integer.parseInt(this.getParameter("WordsLineHeight", "0"));
        }
        catch (Exception ex14) {
            ex14.printStackTrace();
        }
        try {
            this.WordsWidth = Integer.parseInt(this.getParameter("WordsWidth", "0"));
        }
        catch (Exception ex15) {
            ex15.printStackTrace();
        }
        try {
            this.ButtonsTop = Integer.parseInt(this.getParameter("ButtonsTop", "0"));
        }
        catch (Exception ex16) {
            ex16.printStackTrace();
        }
        try {
            this.ButtonsLeft = Integer.parseInt(this.getParameter("ButtonsLeft", "0"));
        }
        catch (Exception ex17) {
            ex17.printStackTrace();
        }
        try {
            this.ButtonsWidth = Integer.parseInt(this.getParameter("ButtonsWidth", "0"));
        }
        catch (Exception ex18) {
            ex18.printStackTrace();
        }
        try {
            this.ButtonsHeight = Integer.parseInt(this.getParameter("ButtonsHeight", "0"));
        }
        catch (Exception ex19) {
            ex19.printStackTrace();
        }
        try {
            this.ButtonsSpacer = Integer.parseInt(this.getParameter("ButtonsSpacer", "0"));
        }
        catch (Exception ex20) {
            ex20.printStackTrace();
        }
        try {
            this.RightWidth = Integer.parseInt(this.getParameter("RightWidth", "0"));
        }
        catch (Exception ex21) {
            ex21.printStackTrace();
        }
        try {
            this.ScrollButtonHeight = Integer.parseInt(this.getParameter("ScrollButtonHeight", "0"));
        }
        catch (Exception ex22) {
            ex22.printStackTrace();
        }
        try {
            this.TextColorString = this.getParameter("TextColor", "0");
        }
        catch (Exception ex23) {
            ex23.printStackTrace();
        }
        try {
            this.BackColorString = this.getParameter("BackColor", "0");
        }
        catch (Exception ex24) {
            ex24.printStackTrace();
        }
        try {
            this.SolveCircleColorString = this.getParameter("SolvedCircleColor", "0");
        }
        catch (Exception ex25) {
            ex25.printStackTrace();
        }
        try {
            this.CirclingColorString = this.getParameter("CirclingColor", "0");
        }
        catch (Exception ex26) {
            ex26.printStackTrace();
        }
        try {
            this.CircledColorString = this.getParameter("CircledColor", "0");
        }
        catch (Exception ex27) {
            ex27.printStackTrace();
        }
        try {
            this.ShowSolve = Boolean.valueOf(this.getParameter("ShowSolve", "false"));
        }
        catch (Exception ex28) {
            ex28.printStackTrace();
        }
        try {
            this.DebugMode = Boolean.valueOf(this.getParameter("Debug", "false"));
        }
        catch (Exception ex29) {
            ex29.printStackTrace();
        }
        try {
            this.AllowFullSolve = Boolean.valueOf(this.getParameter("AllowFullSolve", "false"));
        }
        catch (Exception ex30) {
            ex30.printStackTrace();
        }
        try {
            this.FullSolveWins = Boolean.valueOf(this.getParameter("FullSolveWins", "false"));
        }
        catch (Exception ex31) {
            ex31.printStackTrace();
        }
        try {
            this.AllowReset = Boolean.valueOf(this.getParameter("AllowReset", "false"));
        }
        catch (Exception ex32) {
            ex32.printStackTrace();
        }
        try {
            this.SolveUnhideFirst = Boolean.valueOf(this.getParameter("SolveUnhideFirst", "false"));
        }
        catch (Exception ex33) {
            ex33.printStackTrace();
        }
        try {
            this.NoSpaceInHidden = Boolean.valueOf(this.getParameter("NoSpaceInHidden", "false"));
        }
        catch (Exception ex34) {
            ex34.printStackTrace();
        }
        try {
            this.HideNameOnAbout = Boolean.valueOf(this.getParameter("HideNameOnAbout", "false"));
        }
        catch (Exception ex35) {
            ex35.printStackTrace();
        }
        try {
            this.ShowNote = Boolean.valueOf(this.getParameter("ShowNote", "false"));
        }
        catch (Exception ex36) {
            ex36.printStackTrace();
        }
        try {
            this.ScrollWords = Boolean.valueOf(this.getParameter("ScrollWords", "false"));
        }
        catch (Exception ex37) {
            ex37.printStackTrace();
        }
        try {
            this.ScrollNote = Boolean.valueOf(this.getParameter("ScrollNote", "false"));
        }
        catch (Exception ex38) {
            ex38.printStackTrace();
        }
        try {
            this.PlaySound = Boolean.valueOf(this.getParameter("PlaySound", "false"));
        }
        catch (Exception ex39) {
            ex39.printStackTrace();
        }
        try {
            this.TextTop = Integer.parseInt(this.getParameter("TextTop", "0"));
        }
        catch (Exception ex40) {
            ex40.printStackTrace();
        }
        try {
            this.TimerTop = Integer.parseInt(this.getParameter("TimerTop", "0"));
        }
        catch (Exception ex41) {
            ex41.printStackTrace();
        }
        try {
            this.TimerJust = Integer.parseInt(this.getParameter("TimerJust", "0"));
        }
        catch (Exception ex42) {
            ex42.printStackTrace();
        }
        try {
            this.WordCount = Integer.parseInt(this.getParameter("WordCount", "0"));
        }
        catch (Exception ex43) {
            ex43.printStackTrace();
        }
        try {
            this.LineCount = Integer.parseInt(this.getParameter("LineCount", "0"));
        }
        catch (Exception ex44) {
            ex44.printStackTrace();
        }
        try {
            this.WinAddress = this.getParameter("WinUrl", "");
        }
        catch (Exception ex45) {
            ex45.printStackTrace();
        }
        try {
            this.WinPause = Integer.parseInt(this.getParameter("WinPause", "0"));
        }
        catch (Exception ex46) {
            ex46.printStackTrace();
        }
        try {
            this.Sentence = this.getParameter("Sentence", "");
        }
        catch (Exception ex47) {
            ex47.printStackTrace();
        }
        try {
            this.RegDateAString = this.getParameter("RDA", "");
        }
        catch (Exception ex48) {
            ex48.printStackTrace();
        }
        try {
            this.RegDateBString = this.getParameter("RDB", "");
        }
        catch (Exception ex49) {
            ex49.printStackTrace();
        }
        try {
            this.RegistrationName = this.getParameter("RegistrationName", "");
        }
        catch (Exception ex50) {
            ex50.printStackTrace();
        }
        try {
            this.Passcode = this.getParameter("Passcode", "");
        }
        catch (Exception ex51) {
            ex51.printStackTrace();
        }
        try {
            this.BasePuzzleName = this.getParameter("BasePuzzleName", "");
        }
        catch (Exception ex52) {
            ex52.printStackTrace();
        }
        try {
            this.WinFile = this.getParameter("WinFile", "WSPlayWin.gif");
        }
        catch (Exception ex53) {
            ex53.printStackTrace();
        }
        try {
            this.PointerFile = this.getParameter("PointerFile", "");
        }
        catch (Exception ex54) {
            ex54.printStackTrace();
        }
        try {
            this.AudioOnFile = this.getParameter("AudioOnFile", "");
        }
        catch (Exception ex55) {
            ex55.printStackTrace();
        }
        try {
            this.AudioOffFile = this.getParameter("AudioOffFile", "");
        }
        catch (Exception ex56) {
            ex56.printStackTrace();
        }
        try {
            this.AboutFile = this.getParameter("AboutFile", "");
        }
        catch (Exception ex57) {
            ex57.printStackTrace();
        }
        try {
            this.AboutCode = Integer.parseInt(this.getParameter("AboutCode", "0"));
        }
        catch (Exception ex58) {
            ex58.printStackTrace();
        }
        try {
            this.AboutAddress = this.getParameter("AboutURL", this.WSPAddress);
        }
        catch (Exception ex59) {
            ex59.printStackTrace();
        }
        try {
            this.TextPAS = this.getParameter("TextPAS", "Puzzle was already solved");
        }
        catch (Exception ex60) {
            ex60.printStackTrace();
        }
        try {
            this.TextPZI = this.getParameter("TextPZI", "Click and drag mouse to circle words.");
        }
        catch (Exception ex61) {
            ex61.printStackTrace();
        }
        try {
            this.TextNTI = this.getParameter("TextNTI", "Press PLAY to continue.");
        }
        catch (Exception ex62) {
            ex62.printStackTrace();
        }
        try {
            this.TextWAF = this.getParameter("TextWAF", "Selected word was already found");
        }
        catch (Exception ex63) {
            ex63.printStackTrace();
        }
        try {
            this.TextSVI = this.getParameter("TextSVI", "Press SOLVE again to find in puzzle");
        }
        catch (Exception ex64) {
            ex64.printStackTrace();
        }
        try {
            this.TextOWS = this.getParameter("TextOWS", "One word Solved");
        }
        catch (Exception ex65) {
            ex65.printStackTrace();
        }
        try {
            this.TextWUS = this.getParameter("TextWUS", "Won using Solve on last word.");
        }
        catch (Exception ex66) {
            ex66.printStackTrace();
        }
        try {
            this.TextSPA = this.getParameter("TextSPA", "SOLVE Button Pressed. Press PLAY to try it again.");
        }
        catch (Exception ex67) {
            ex67.printStackTrace();
        }
        try {
            this.TextSBP = this.getParameter("TextSBP", "SOLVE Button Pressed.");
        }
        catch (Exception ex68) {
            ex68.printStackTrace();
        }
        try {
            this.TextAOL = this.getParameter("TextAOL", "Press SOLVE again for ALL, or select a single line first");
        }
        catch (Exception ex69) {
            ex69.printStackTrace();
        }
        try {
            this.TextSLS = this.getParameter("TextSLS", "First select a line to solve.");
        }
        catch (Exception ex70) {
            ex70.printStackTrace();
        }
        try {
            this.TextSBU = this.getParameter("TextSBU", "SOLVE Button used to solve the puzzle");
        }
        catch (Exception ex71) {
            ex71.printStackTrace();
        }
        try {
            this.TextELV = this.getParameter("TextELV", "Enter the value that belongs on that line");
        }
        catch (Exception ex72) {
            ex72.printStackTrace();
        }
        try {
            this.TextSWH = this.getParameter("TextSWH", "YOU DID IT! (With a little help)");
        }
        catch (Exception ex73) {
            ex73.printStackTrace();
        }
        try {
            this.TextSFR = this.getParameter("TextSFR", "YOU DID IT!");
        }
        catch (Exception ex74) {
            ex74.printStackTrace();
        }
        try {
            this.TextFOW = this.getParameter("TextFOW", "Found One!");
        }
        catch (Exception ex75) {
            ex75.printStackTrace();
        }
        try {
            this.TextAFW = this.getParameter("TextAFW", "You Already Found That One.");
        }
        catch (Exception ex76) {
            ex76.printStackTrace();
        }
        try {
            this.TextNCW = this.getParameter("TextNCW", "Not what we're looking for.");
        }
        catch (Exception ex77) {
            ex77.printStackTrace();
        }
        try {
            this.TextLOP = this.getParameter("TextLOP", "Loading Puzzle...");
        }
        catch (Exception ex78) {
            ex78.printStackTrace();
        }
        try {
            this.TextISC = this.getParameter("TextISC", " is correct!");
        }
        catch (Exception ex79) {
            ex79.printStackTrace();
        }
        try {
            this.TextPTS = this.getParameter("TextPTS", "Press PLAY to start.");
        }
        catch (Exception ex80) {
            ex80.printStackTrace();
        }
        try {
            this.TextPAU = this.getParameter("TextPAU", "Paused");
        }
        catch (Exception ex81) {
            ex81.printStackTrace();
        }
        try {
            this.TextTIM = this.getParameter("TextTIM", "Time: ");
        }
        catch (Exception ex82) {
            ex82.printStackTrace();
        }
        try {
            this.TextJNP = this.getParameter("TextJNP", "Jumping to a new page...");
        }
        catch (Exception ex83) {
            ex83.printStackTrace();
        }
        try {
            this.TextINP = this.getParameter("TextINP", "(Invalid next page URL was specified)");
        }
        catch (Exception ex84) {
            ex84.printStackTrace();
        }
        try {
            this.TextWFN = this.getParameter("TextNCW", "Waiting for Note to load...");
        }
        catch (Exception ex85) {
            ex85.printStackTrace();
        }
        try {
            this.TextWFA = this.getParameter("TextWFA", "Waiting for Audio to load...");
        }
        catch (Exception ex86) {
            ex86.printStackTrace();
        }
        try {
            this.TextWFP = this.getParameter("TextWFP", "Waiting for Puzzle to load...");
        }
        catch (Exception ex87) {
            ex87.printStackTrace();
        }
        try {
            this.TextSON = this.getParameter("TextSON", "Sound ON");
        }
        catch (Exception ex88) {
            ex88.printStackTrace();
        }
        try {
            this.TextSOF = this.getParameter("TextSOF", "Sound OFF");
        }
        catch (Exception ex89) {
            ex89.printStackTrace();
        }
        try {
            this.TextHSS = this.getParameter("TextHSS", "Type message hidden in the remaining letters");
        }
        catch (Exception ex90) {
            ex90.printStackTrace();
        }
        try {
            this.TextHSN = this.getParameter("TextHSN", "Add spaces between the words");
        }
        catch (Exception ex91) {
            ex91.printStackTrace();
        }
        try {
            this.TextIN1 = this.getParameter("TextIN1", "Initializing...");
        }
        catch (Exception ex92) {
            ex92.printStackTrace();
        }
        try {
            this.TextIN2 = this.getParameter("TextIN2", "Verifying...");
        }
        catch (Exception ex93) {
            ex93.printStackTrace();
        }
        try {
            this.TextIN3 = this.getParameter("TextIN3", "Preparing...");
        }
        catch (Exception ex94) {
            ex94.printStackTrace();
        }
        try {
            this.TextPCB = this.getParameter("TextPCB", "Puzzle created by: ");
        }
        catch (Exception ex95) {
            ex95.printStackTrace();
        }
        try {
            this.ErrorWIN = this.getParameter("ErrorWIN", "ERROR: Could Not Load WIN File");
        }
        catch (Exception ex96) {
            ex96.printStackTrace();
        }
        try {
            this.ErrorNOT = this.getParameter("ErrorNOT", "ERROR: Could Not Load NOTE File");
        }
        catch (Exception ex97) {
            ex97.printStackTrace();
        }
        try {
            this.ErrorPUZ = this.getParameter("ErrorPUZ", "ERROR: Could Not Load all PUZZLE Files");
        }
        catch (Exception ex98) {
            ex98.printStackTrace();
        }
        try {
            this.ErrorABT = this.getParameter("ErrorABT", "ERROR: Could Not Load ABOUT File");
        }
        catch (Exception ex99) {
            ex99.printStackTrace();
        }
        try {
            this.ErrorCOR = this.getParameter("ErrorCOR", "ERROR: About Box image not correct");
        }
        catch (Exception ex100) {
            ex100.printStackTrace();
        }
        try {
            this.ErrorPNL = this.getParameter("ErrorPNL", "Puzzle grid not loaded yet.");
        }
        catch (Exception ex101) {
            ex101.printStackTrace();
        }
        try {
            this.ErrorGNL = this.getParameter("ErrorGNL", "Puzzle grid not loaded yet.");
        }
        catch (Exception ex102) {
            ex102.printStackTrace();
        }
        try {
            this.ErrorTNL = this.getParameter("ErrorTNL", "Note image not loaded yet.");
        }
        catch (Exception ex103) {
            ex103.printStackTrace();
        }
        try {
            this.ErrorANL = this.getParameter("ErrorANL", "About Box not loaded yet.");
        }
        catch (Exception ex104) {
            ex104.printStackTrace();
        }
        try {
            this.UseBaseSounds = Boolean.valueOf(this.getParameter("UseBaseSounds", "false"));
        }
        catch (Exception ex105) {
            ex105.printStackTrace();
        }
        if (this.UseBaseSounds) {
            try {
                this.CircleSoundFile = this.getParameter("CircleSound", "circle.au");
            }
            catch (Exception ex106) {
                ex106.printStackTrace();
            }
            try {
                this.SliderSoundFile = this.getParameter("SliderSound", "slider.au");
            }
            catch (Exception ex107) {
                ex107.printStackTrace();
            }
            try {
                this.GoodSoundFile = this.getParameter("GoodWordSound", "goodword.au");
            }
            catch (Exception ex108) {
                ex108.printStackTrace();
            }
            try {
                this.BadSoundFile = this.getParameter("BadWordSound", "badword.au");
            }
            catch (Exception ex109) {
                ex109.printStackTrace();
            }
            try {
                this.ButtonSoundFile = this.getParameter("ButtonClickSound", "buttonclick.au");
            }
            catch (Exception ex110) {
                ex110.printStackTrace();
            }
            try {
                this.WinSoundFile = this.getParameter("WinSound", "win.au");
            }
            catch (Exception ex111) {
                ex111.printStackTrace();
            }
        }
        try {
            this.UseTutorialSounds = Boolean.valueOf(this.getParameter("UseTutorialSounds", "false"));
        }
        catch (Exception ex112) {
            ex112.printStackTrace();
        }
        if (this.UseTutorialSounds) {
            try {
                this.ClickWordSoundFile = this.getParameter("ClickWordSound", "clickword.au");
            }
            catch (Exception ex113) {
                ex113.printStackTrace();
            }
            try {
                this.FirstClickWordSoundFile = this.getParameter("FirstClickWordSound", "firstclickword.au");
            }
            catch (Exception ex114) {
                ex114.printStackTrace();
            }
            try {
                this.ClickSentenceSoundFile = this.getParameter("ClickSentenceSound", "clicksentence.au");
            }
            catch (Exception ex115) {
                ex115.printStackTrace();
            }
            try {
                this.FirstClickSentenceSoundFile = this.getParameter("FirstClickSentenceSound", "firstclicksentence.au");
            }
            catch (Exception ex116) {
                ex116.printStackTrace();
            }
            try {
                this.SolveHiddenSoundFile = this.getParameter("SolveHiddenSound", "solvehidden.au");
            }
            catch (Exception ex117) {
                ex117.printStackTrace();
            }
            try {
                this.FirstSolveHiddenSoundFile = this.getParameter("FirstSolveHiddenSound", "firstsolvehidden.au");
            }
            catch (Exception ex118) {
                ex118.printStackTrace();
            }
            try {
                this.SolveUnscrambleSoundFile = this.getParameter("SolveUnscrambleSound", "solveunscramble.au");
            }
            catch (Exception ex119) {
                ex119.printStackTrace();
            }
            try {
                this.FirstSolveUnscrambleSoundFile = this.getParameter("FirstSolveUnscrambleSound", "firstsolveunscramble.au");
            }
            catch (Exception ex120) {
                ex120.printStackTrace();
            }
            try {
                this.SolveCircleSoundFile = this.getParameter("SolveCircleSound", "solvecircle.au");
            }
            catch (Exception ex121) {
                ex121.printStackTrace();
            }
            try {
                this.FirstSolveCircleSoundFile = this.getParameter("FirstSolveCircleSound", "firstsolvecircle.au");
            }
            catch (Exception ex122) {
                ex122.printStackTrace();
            }
            try {
                this.SolveAllSoundFile = this.getParameter("SolveAllSound", "solveall.au");
            }
            catch (Exception ex123) {
                ex123.printStackTrace();
            }
            try {
                this.SolveErrorSoundFile = this.getParameter("SolveErrorSound", "solveerror.au");
            }
            catch (Exception ex124) {
                ex124.printStackTrace();
            }
            try {
                this.SolveAllNoteSoundFile = this.getParameter("SolveAllNoteSound", "solveallnote.au");
            }
            catch (Exception ex125) {
                ex125.printStackTrace();
            }
            try {
                this.StartUnscrambleSoundFile = this.getParameter("StartUnscrambleSound", "startunscramble.au");
            }
            catch (Exception ex126) {
                ex126.printStackTrace();
            }
            try {
                this.FirstStartUnscrambleSoundFile = this.getParameter("FirstStartUnscrambleSound", "firststartunscramble.au");
            }
            catch (Exception ex127) {
                ex127.printStackTrace();
            }
            try {
                this.FinishUnscrambleSoundFile = this.getParameter("FinishUnscrambleSound", "finishunscramble.au");
            }
            catch (Exception ex128) {
                ex128.printStackTrace();
            }
            try {
                this.FirstFinishUnscrambleSoundFile = this.getParameter("FirstFinishUnscrambleSound", "firstfinishunscramble.au");
            }
            catch (Exception ex129) {
                ex129.printStackTrace();
            }
            try {
                this.StartHiddenSoundFile = this.getParameter("StartHiddenSound", "starthidden.au");
            }
            catch (Exception ex130) {
                ex130.printStackTrace();
            }
            try {
                this.FirstStartHiddenSoundFile = this.getParameter("FirstStartHiddenSound", "firststarthidden.au");
            }
            catch (Exception ex131) {
                ex131.printStackTrace();
            }
            try {
                this.FinishHiddenSoundFile = this.getParameter("FinishHiddenSound", "finishhidden.au");
            }
            catch (Exception ex132) {
                ex132.printStackTrace();
            }
            try {
                this.FirstFinishHiddenSoundFile = this.getParameter("FirstFinishHiddenSound", "firstfinishhidden.au");
            }
            catch (Exception ex133) {
                ex133.printStackTrace();
            }
            try {
                this.StartPhraseSoundFile = this.getParameter("StartPhraseSound", "startphrase.au");
            }
            catch (Exception ex134) {
                ex134.printStackTrace();
            }
            try {
                this.PlayStandardSoundFile = this.getParameter("PlayStandardSound", "playstandard.au");
            }
            catch (Exception ex135) {
                ex135.printStackTrace();
            }
            try {
                this.FirstPlayStandardSoundFile = this.getParameter("FirstPlayStandardSound", "firstplaystandard.au");
            }
            catch (Exception ex136) {
                ex136.printStackTrace();
            }
            try {
                this.PlayHiddenSoundFile = this.getParameter("PlayHiddenSound", "playhidden.au");
            }
            catch (Exception ex137) {
                ex137.printStackTrace();
            }
            try {
                this.FirstPlayHiddenSoundFile = this.getParameter("FirstPlayHiddenSound", "firstplayhidden.au");
            }
            catch (Exception ex138) {
                ex138.printStackTrace();
            }
            try {
                this.PlayUnscrambleSoundFile = this.getParameter("PlayUnscrambleSound", "playunscramble.au");
            }
            catch (Exception ex139) {
                ex139.printStackTrace();
            }
            try {
                this.FirstPlayUnscrambleSoundFile = this.getParameter("FirstPlayUnscrambleSound", "firstplayunscramble.au");
            }
            catch (Exception ex140) {
                ex140.printStackTrace();
            }
            try {
                this.NoteButtonSoundFile = this.getParameter("NoteSound", "note.au");
            }
            catch (Exception ex141) {
                ex141.printStackTrace();
            }
            try {
                this.FirstNoteButtonSoundFile = this.getParameter("FirstNoteSound", "firstnote.au");
            }
            catch (Exception ex142) {
                ex142.printStackTrace();
            }
            try {
                this.AboutButtonSoundFile = this.getParameter("AboutSound", "about.au");
            }
            catch (Exception ex143) {
                ex143.printStackTrace();
            }
        }
        try {
            this.WordSoundsLocation = this.getParameter("WordSoundsLocation", "");
        }
        catch (Exception ex144) {
            ex144.printStackTrace();
        }
        try {
            this.ActionSoundsLocation = this.getParameter("ActionSoundsLocation", "");
        }
        catch (Exception ex145) {
            ex145.printStackTrace();
        }
        try {
            this.PuzzleGraphicsLocation = this.getParameter("PuzzleGraphicsLocation", "");
        }
        catch (Exception ex146) {
            ex146.printStackTrace();
        }
        try {
            this.SharedGraphicsLocation = this.getParameter("SharedGraphicsLocation", "");
        }
        catch (Exception ex147) {
            ex147.printStackTrace();
        }
        try {
            this.ShowMuteButton = Boolean.valueOf(this.getParameter("ShowMuteButton", "true"));
        }
        catch (Exception ex148) {
            ex148.printStackTrace();
        }
        try {
            this.SayWordClick = Boolean.valueOf(this.getParameter("SayWordClick", "false"));
        }
        catch (Exception ex149) {
            ex149.printStackTrace();
        }
        try {
            this.SayWordCircle = Boolean.valueOf(this.getParameter("SayWordCircle", "false"));
        }
        catch (Exception ex150) {
            ex150.printStackTrace();
        }
        try {
            this._$5527();
        }
        catch (Exception ex151) {
            ex151.printStackTrace();
        }
        this.Sentence = this.Sentence.toUpperCase();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    private void _$5527() throws Exception {
        this.DoDebug("*** Start Initializing Applet ***", false);
        this.setLayout(null);
        this.ac = this.getAppletContext();
        this.toolkit = Toolkit.getDefaultToolkit();
        this.fm = this.getFontMetrics(this.getGraphics().getFont());
        this.xPoints = new int[23];
        this.yPoints = new int[23];
        this.TextColor = new Color(Integer.parseInt(this.TextColorString.substring(0, 3)), Integer.parseInt(this.TextColorString.substring(3, 6)), Integer.parseInt(this.TextColorString.substring(6, 9)));
        this.BackColor = new Color(Integer.parseInt(this.BackColorString.substring(0, 3)), Integer.parseInt(this.BackColorString.substring(3, 6)), Integer.parseInt(this.BackColorString.substring(6, 9)));
        this.SolveCircleColor = new Color(Integer.parseInt(this.SolveCircleColorString.substring(0, 3)), Integer.parseInt(this.SolveCircleColorString.substring(3, 6)), Integer.parseInt(this.SolveCircleColorString.substring(6, 9)));
        this.CirclingColor = new Color(Integer.parseInt(this.CirclingColorString.substring(0, 3)), Integer.parseInt(this.CirclingColorString.substring(3, 6)), Integer.parseInt(this.CirclingColorString.substring(6, 9)));
        this.CircledColor = new Color(Integer.parseInt(this.CircledColorString.substring(0, 3)), Integer.parseInt(this.CircledColorString.substring(3, 6)), Integer.parseInt(this.CircledColorString.substring(6, 9)));
        this.appletWidth = this.getSize().width;
        this.appletHeight = this.getSize().height;
        this.PlayButtonLeft = this.ButtonsLeft;
        this.NoteButtonLeft = this.ButtonsLeft + this.ButtonsWidth + this.ButtonsSpacer;
        if (this.ShowNote) {
            this.SolveButtonLeft = this.ButtonsLeft + (this.ButtonsWidth + this.ButtonsSpacer) * 2;
        }
        else {
            this.SolveButtonLeft = this.ButtonsLeft + (this.ButtonsWidth + this.ButtonsSpacer);
        }
        this.AboutButtonLeft = this.appletWidth - this.RightWidth - this.ButtonsWidth;
        this.textHeight = this.fm.getHeight();
        this.timerWidth = this.fm.stringWidth(String.valueOf(String.valueOf(this.TextTIM)).concat(" 00:00:00"));
        this.dbufImage = this.createImage(this.appletWidth, this.appletHeight);
        this.dbufGraphics = this.dbufImage.getGraphics();
        this.AudioLeft = this.appletWidth - this.RightWidth + 4;
        this.AudioSize = this.appletWidth - this.AudioLeft - 4;
        if (this.AudioSize > 20) {
            this.AudioSize = 20;
        }
        if (this.AudioSize < 12) {
            this.AudioSize = 12;
        }
        this.AudioHorzIn = (this.AudioSize - 5) / 2;
        this.AudioVertIn = (this.AudioSize - 12) / 2;
        this.AudioTop = this.ButtonsTop + (this.ButtonsHeight - this.AudioSize) / 2;
        this.messageLabel.setFont(this.LargeFont);
        this.messageLabel.setLocation(this.GridLeftPos, this.GridTopPos + this.GridHeight / 2 - this.textHeight);
        this.messageLabel.setSize(this.GridWidth + this.WordsWidth, this.textHeight * 2);
        this.messageLabel.setForeground(Color.black);
        this.messageLabel.setBackground(Color.white);
        this.messageLabel.setAlignment(1);
        this.add(this.messageLabel);
        (this.answerTextField = new TextField("")).setVisible(false);
        this.answerTextField.setLocation(this.GridLeftPos, this.TextTop + this.textHeight + 2);
        this.answerTextField.setSize(this.GridWidth + this.WordsWidth, this.textHeight + 4);
        this.answerTextField.addTextListener(new TextListener() {
            public void textValueChanged(final TextEvent textEvent) {
                WSPlayApplet.this.answerTextField_textValueChanged(textEvent);
            }
        });
        this.add(this.answerTextField);
        int length = this.WordPosList.length();
        if (length > 100) {
            length = 100;
        }
        if (this.RegDateAString.length() > 0) {
            this.JavaCheckString = String.valueOf(String.valueOf(new StringBuffer("WSP").append(this.GC(this.RegistrationName.toUpperCase())).append(this.GC(this.WordPosList.substring(0, length).toUpperCase())).append(this.GC(this.RegDateAString.toUpperCase())).append(this.GC(this.RegDateBString.toUpperCase()))));
        }
        else {
            this.JavaCheckString = String.valueOf(String.valueOf(new StringBuffer("WSP").append(this.GC(this.RegistrationName.toUpperCase())).append(this.GC(this.WordPosList.substring(0, length).toUpperCase()))));
        }
        if (this.Passcode.equals(this.JavaCheckString)) {
            if (this.RegDateAString.length() > 0) {
                Date parse = new Date();
                Date parse2 = new Date();
                final Date date = new Date();
                final DateFormat dateInstance = DateFormat.getDateInstance(3, Locale.US);
                try {
                    parse = dateInstance.parse(this.RegDateAString);
                    parse2 = dateInstance.parse(this.RegDateBString);
                }
                catch (ParseException ex) {
                    this.DebugString = "Error: Invalid Dates";
                }
                if (this.DebugString.length() == 0) {
                    if (parse.before(date) && parse2.after(date)) {
                        this.ValidData = true;
                    }
                    if (!this.ValidData) {
                        this.DebugString = this.TrialPeriodString;
                    }
                }
            }
            else {
                this.ValidData = true;
            }
        }
        else {
            this.DebugString = this.CorruptString;
        }
        if (this.ValidData) {
            this.DoDebug(" Start MediaTracker", false);
            this.tracker = new MediaTracker(this);
            this.DoDebug(" Start Loading Thread", false);
            this.sthread = new StartThread();
        }
        else {
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (this.CirclingWord) {
            if (this.startCircling) {
                this.startCircling = false;
            }
            else {
                graphics.setClip(this.GridLeftPos, this.GridTopPos, this.GridWidth, this.GridHeight);
                this.CircleWord(graphics, this.oxStartCell, this.oyStartCell, this.oxEndCell, this.oyEndCell, this.GridLeftPos + this.CellXOffset, this.GridTopPos + this.CellYOffset, this.CircledColor, this.CirclingColor, true, true);
            }
            if (this.endCircling) {
                this.endCircling = false;
            }
            else {
                graphics.setClip(this.GridLeftPos, this.GridTopPos, this.GridWidth, this.GridHeight);
                this.CircleWord(graphics, this.xStartCell, this.yStartCell, this.xEndCell, this.yEndCell, this.GridLeftPos + this.CellXOffset, this.GridTopPos + this.CellYOffset, this.CircledColor, this.CirclingColor, true, true);
                this.oxStartCell = this.xStartCell;
                this.oyStartCell = this.yStartCell;
                this.oxEndCell = this.xEndCell;
                this.oyEndCell = this.yEndCell;
            }
        }
        else {
            if (this.frameLoaded) {
                this.inPuzzleMode = false;
                if (this.frameImage != null) {
                    this.dbufGraphics.drawImage(this.frameImage, 0, 0, this);
                }
                switch (this.playMode) {
                    case 6: {
                        this.answerTextField.setVisible(true);
                        this.answerTextField.setText("");
                        this.answerTextField.requestFocus();
                        this.ShowMessage(this.TextHSS, false);
                        this.PlayASound(this.StartPhraseSound, true, false);
                    }
                    case 1: {
                        this.inPuzzleMode = true;
                        if (this.gridImage != null) {
                            this.dbufGraphics.drawImage(this.gridImage, this.GridLeftPos, this.GridTopPos, this);
                            this.CopyArea(this.actualListImage, 0, this.WordsTopOffset, this.WordsWidth, this.GridHeight, this.dbufImage, this.WordsLeftPos, this.WordsTopPos);
                            break;
                        }
                        this.ShowMessage(this.ErrorPNL, false);
                        break;
                    }
                    case 2: {
                        if (this.noteImage != null) {
                            this.CopyArea(this.noteImage, 0, this.NoteTopOffset, this.NoteWidth, this.GridHeight, this.dbufImage, this.GridLeftPos, this.GridTopPos);
                            break;
                        }
                        this.ShowMessage(this.ErrorTNL, false);
                        break;
                    }
                    case 5: {
                        this.ShowMessage(this.SolveString, false);
                    }
                    case 3: {
                        this.answerTextField.setVisible(false);
                        this.inPuzzleMode = true;
                        if (this.gridImage != null) {
                            this.dbufGraphics.drawImage(this.gridImage, this.GridLeftPos, this.GridTopPos, this);
                            this.CopyArea(this.actualListImage, 0, this.WordsTopOffset, this.WordsWidth, this.GridHeight, this.dbufImage, this.WordsLeftPos, this.WordsTopPos);
                        }
                        else {
                            this.ShowMessage(this.ErrorGNL, false);
                        }
                        if (this.playMode == 3) {
                            this.ShowNote = false;
                            this.ShowPlay = false;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        this.dbufGraphics.setColor(Color.white);
                        this.dbufGraphics.fillRect(this.GridLeftPos, this.GridTopPos, this.GridWidth + this.WordsWidth, this.GridHeight);
                        if (this.aboutImageLoaded) {
                            this.dbufGraphics.drawImage(this.aboutImage, this.AboutLeft, this.AboutTop, this);
                            break;
                        }
                        this.dbufGraphics.setFont(this.LargeFont);
                        this.dbufGraphics.setColor(Color.black);
                        final String s = "Word Splash Pro Java Applet 3.1";
                        final int n = this.GridTopPos + this.GridHeight / 2 - 50;
                        this.dbufGraphics.drawString(s, this.GridLeftPos + Math.round((this.GridWidth + this.WordsWidth - this.dbufGraphics.getFontMetrics().stringWidth(s)) / 2), n);
                        final int n2 = n + this.dbufGraphics.getFontMetrics().getHeight();
                        this.dbufGraphics.setFont(this.SmallFont);
                        final String s2 = "Copyright 2000-2005 Albert Edward Bogdan";
                        this.dbufGraphics.drawString(s2, this.GridLeftPos + Math.round((this.GridWidth + this.WordsWidth - this.dbufGraphics.getFontMetrics().stringWidth(s2)) / 2), n2);
                        final int n3 = n2 + this.dbufGraphics.getFontMetrics().getHeight() * 2;
                        this.dbufGraphics.setColor(Color.blue);
                        final String wspAddress = this.WSPAddress;
                        this.dbufGraphics.drawString(wspAddress, this.GridLeftPos + Math.round((this.GridWidth + this.WordsWidth - this.dbufGraphics.getFontMetrics().stringWidth(wspAddress)) / 2), n3);
                        break;
                    }
                }
                if (this.ScrollNote & this.playMode == 2 & this.sliderImage != null) {
                    this.CopyArea(this.sliderImage, 0, 0, this.SliderWidth, this.SliderHeight, this.dbufImage, this.SliderLeft, this.NoteSliderTop);
                }
                if (this.puzzleLoaded) {
                    if (this.ScrollWords & this.inPuzzleMode & this.sliderImage != null) {
                        this.CopyArea(this.sliderImage, 0, 0, this.SliderWidth, this.SliderHeight, this.dbufImage, this.SliderLeft, this.WordsSliderTop);
                    }
                    if (this.puzzleSolved & this.inPuzzleMode & this.winImage != null & !this.SolvePressed) {
                        this.dbufGraphics.drawImage(this.winImage, this.winLeft, this.winTop, this);
                    }
                    if (this.buttonsImage != null) {
                        if (this.ShowPlay) {
                            this.CopyArea(this.buttonsImage, 0, 0, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.PlayButtonLeft, this.ButtonsTop);
                        }
                        if (this.ShowNote) {
                            this.CopyArea(this.buttonsImage, this.ButtonsWidth, 0, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.NoteButtonLeft, this.ButtonsTop);
                        }
                        if (this.ShowSolve & !this.puzzleSolved & (this.playMode == 6 | this.playMode == 1)) {
                            this.CopyArea(this.buttonsImage, this.ButtonsWidth * 2, 0, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.SolveButtonLeft, this.ButtonsTop);
                        }
                        this.CopyArea(this.buttonsImage, this.ButtonsWidth * 3, 0, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.AboutButtonLeft, this.ButtonsTop);
                    }
                    if (this.buttonsImage != null) {
                        switch (this.playMode) {
                            case 1:
                            case 6: {
                                this.CopyArea(this.buttonsImage, 0, this.ButtonsHeight, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.PlayButtonLeft, this.ButtonsTop);
                                break;
                            }
                            case 2: {
                                this.CopyArea(this.buttonsImage, this.ButtonsWidth, this.ButtonsHeight, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.NoteButtonLeft, this.ButtonsTop);
                                break;
                            }
                            case 3: {
                                this.CopyArea(this.buttonsImage, this.ButtonsWidth * 2, this.ButtonsHeight, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.SolveButtonLeft, this.ButtonsTop);
                                break;
                            }
                            case 4: {
                                this.CopyArea(this.buttonsImage, this.ButtonsWidth * 3, this.ButtonsHeight, this.ButtonsWidth, this.ButtonsHeight, this.dbufImage, this.AboutButtonLeft, this.ButtonsTop);
                                break;
                            }
                        }
                    }
                }
                if (this.ShowMuteButton) {
                    if (this.audioImageLoaded) {
                        if (this.PlaySound) {
                            this.CopyArea(this.audioOnImage, 0, 0, this.audioOnImage.getWidth(null), this.audioOnImage.getHeight(null), this.dbufImage, this.AudioLeft, this.AudioTop);
                        }
                        else {
                            this.CopyArea(this.audioOffImage, 0, 0, this.audioOffImage.getWidth(null), this.audioOffImage.getHeight(null), this.dbufImage, this.AudioLeft, this.AudioTop);
                        }
                    }
                    else if (this.AudioOnFile.equals("")) {
                        if (this.PlaySound) {
                            this.dbufGraphics.setColor(Color.green);
                        }
                        else {
                            this.dbufGraphics.setColor(Color.red);
                        }
                        this.dbufGraphics.fillOval(this.AudioLeft, this.AudioTop, this.AudioSize, this.AudioSize);
                        this.dbufGraphics.setColor(Color.white);
                        if (this.PlaySound) {
                            this.dbufGraphics.drawOval(this.AudioLeft - 1, this.AudioTop - 1, this.AudioSize, this.AudioSize);
                        }
                        else {
                            this.dbufGraphics.drawOval(this.AudioLeft, this.AudioTop, this.AudioSize - 1, this.AudioSize - 1);
                        }
                        this.dbufGraphics.setColor(Color.black);
                        if (this.PlaySound) {
                            this.dbufGraphics.drawOval(this.AudioLeft, this.AudioTop, this.AudioSize + 1, this.AudioSize + 1);
                        }
                        else {
                            this.dbufGraphics.drawOval(this.AudioLeft + 1, this.AudioTop + 1, this.AudioSize - 1, this.AudioSize - 1);
                        }
                        this.dbufGraphics.drawOval(this.AudioLeft, this.AudioTop, this.AudioSize, this.AudioSize);
                        this.dbufGraphics.drawLine(this.AudioLeft + this.AudioSize - this.AudioHorzIn - 4, this.AudioTop + this.AudioVertIn, this.AudioLeft + this.AudioSize - this.AudioHorzIn - 4, this.AudioTop + this.AudioSize - this.AudioVertIn);
                        this.dbufGraphics.drawLine(this.AudioLeft + this.AudioSize - this.AudioHorzIn - 2, this.AudioTop + this.AudioVertIn + 2, this.AudioLeft + this.AudioSize - this.AudioHorzIn - 2, this.AudioTop + this.AudioSize - this.AudioVertIn - 2);
                        this.dbufGraphics.drawLine(this.AudioLeft + this.AudioSize - this.AudioHorzIn, this.AudioTop + this.AudioVertIn + 4, this.AudioLeft + this.AudioSize - this.AudioHorzIn, this.AudioTop + this.AudioSize - this.AudioVertIn - 4);
                    }
                }
            }
            else {
                this.dbufGraphics.setColor(Color.white);
                this.dbufGraphics.fillRect(0, 0, this.appletWidth, this.appletHeight);
            }
            if (this.DebugString.length() > 0) {
                this.ShowMessage(this.DebugString, false);
            }
            graphics.drawImage(this.dbufImage, 0, 0, this);
        }
    }
    
    public void start() {
        this.appletWasStopped = false;
        if (this.TimerTop > 0) {
            (this.tthread = new TimerThread()).resetCounter(this.saveCounter);
        }
    }
    
    public void stop() {
        if (this.TimerTop > 0 & this.tthread != null) {
            this.saveCounter = this.tthread.getCounter();
        }
        this.MuteSounds(this.appletWasStopped = true);
        if (this.TimerTop > 0 & this.tthread != null) {
            while (this.tthread.isAlive()) {}
        }
    }
    
    public void destroy() {
    }
    
    public String getAppletInfo() {
        return "Word Splash Pro Word Search Puzzle Player 3.1 by Chronasoft\n\nCopyright 2001-2005 Albert Edward Bogdan";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "TextPAS", "String", "Puzzle was already solved" }, { "TextPZI", "String", "Click and drag mouse to circle words." }, { "TextNTI", "String", "Press PLAY to continue." }, { "TextWAF", "String", "Selected word was already found" }, { "TextSVI", "String", "Press SOLVE again to find in puzzle" }, { "TextOWS", "String", "One word Solved" }, { "TextWUS", "String", "Won using Solve on last word." }, { "TextSPA", "String", "SOLVE Button Pressed. Press PLAY to try it again." }, { "TextSBP", "String", "SOLVE Button Pressed." }, { "TextAOL", "String", "Press SOLVE again for ALL, or select a single line first" }, { "TextSLS", "String", "First select a line to solve." }, { "TextSBU", "String", "SOLVE Button used to solve the puzzle" }, { "TextELV", "String", "Enter the value that belongs on that line" }, { "TextSWH", "String", "YOU DID IT! (With a little help)" }, { "TextSFR", "String", "YOU DID IT!" }, { "TextFOW", "String", "Found One!" }, { "TextAFW", "String", "You Already Found That One." }, { "TextNCW", "String", "Not what we're looking for" }, { "TextLOP", "String", "Loading Puzzle..." }, { "TextISC", "String", " is correct!" }, { "TextPTS", "String", "Press PLAY to start." }, { "TextPAU", "String", "Paused" }, { "TextTIM", "String", "Time: " }, { "TextJNP", "String", "Jumping to a new page..." }, { "TextINP", "String", "(Invalid next page URL was specified)" }, { "TextWFN", "String", "Waiting for Note to load..." }, { "TextWFA", "String", "Waiting for Audio to load..." }, { "TextWFP", "String", "Waiting for puzzle to load..." }, { "TextSON", "String", "Sound ON" }, { "TextSOF", "String", "Sound OFF" }, { "TextHSS", "String", "Type message hidden in the remaining letters" }, { "TextHSN", "String", "Add spaces between the words" }, { "TextIN1", "String", "Initializing..." }, { "TextIN2", "String", "Verifying..." }, { "TextIN3", "String", "Preparing..." }, { "TextPCB", "String", "Puzzle created by: " }, { "ErrorWIN", "String", "ERROR: Could Not Load WIN File" }, { "ErrorCOR", "String", "ERROR: About Box image not correct" }, { "ErrorNOT", "String", "ERROR: Could Not Load NOTE File" }, { "ErrorPUZ", "String", "ERROR: Could Not Load all PUZZLE Files" }, { "ErrorABT", "String", "ERROR: Could Not Load ABOUT File" }, { "ErrorPNL", "String", "Puzzle grid not loaded yet." }, { "ErrorGNL", "String", "Puzzle grid not loaded yet." }, { "ErrorTNL", "String", "Note image not loaded yet." }, { "ErrorANL", "String", "About Box not loaded yet." } };
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        boolean fullSolveWins = false;
        int n = 0;
        String s = "";
        if (this.playMode > 0) {
            if (y > this.ButtonsTop & y < this.ButtonsTop + this.ButtonsHeight) {
                if (x >= this.AudioLeft & x <= this.AudioLeft + this.AudioSize) {
                    if (this.PlaySound) {
                        this.PlaySound = false;
                        this.ShowMessage(this.TextSOF, false);
                        this.MuteSounds(false);
                    }
                    else {
                        this.PlaySound = true;
                        this.ShowMessage(this.TextSON, false);
                    }
                }
                if (this.puzzleLoaded & (x >= this.PlayButtonLeft & x <= this.PlayButtonLeft + this.ButtonsWidth)) {
                    this.PlayASound(this.ButtonSound, false, false);
                    this.SolveDouble = false;
                    if (!this.SolvePressed) {
                        this.playMode = 1;
                        if (this.puzzleSolved) {
                            if (this.AllowReset) {
                                this.sthread.run();
                            }
                            else {
                                this.ShowMessage(this.TextPAS, false);
                                if (this.ButtonSound == null & this.PlaySound) {
                                    this.toolkit.beep();
                                }
                            }
                        }
                        else {
                            this.ShowMessage(this.TextPZI, false);
                        }
                    }
                    else if (this.AllowReset) {
                        this.sthread.run();
                    }
                    this.PlayPLAYSound();
                }
                if (this.ShowNote && (x >= this.NoteButtonLeft & x <= this.NoteButtonLeft + this.ButtonsWidth)) {
                    this.PlayASound(this.ButtonSound, false, false);
                    this.SolveDouble = false;
                    this.playMode = 2;
                    this.ShowMessage(this.TextNTI, false);
                    this.PlayASound(this.NoteButtonSound, true, false);
                }
                if ((this.puzzleLoaded & (this.ShowSolve & !this.puzzleSolved)) && (x >= this.SolveButtonLeft & x <= this.SolveButtonLeft + this.ButtonsWidth)) {
                    this.PlayASound(this.ButtonSound, false, false);
                    if (!this.SolvePressed) {
                        if (this.handsOnWord) {
                            final int n2 = (this.wordPicked - 1) * this.WordsLineHeight + 2;
                            if (this.foundWords[this.wordPicked - 1]) {
                                this.ShowMessage(this.TextWAF, false);
                                this.PlayASound(this.SolveErrorSound, true, false);
                            }
                            else if (this.SolveUnhideFirst & !this.solvedWords[this.wordPicked - 1]) {
                                this.answerTextField.setVisible(false);
                                this.ShowMessage(this.TextSVI, false);
                                this.CopyArea(this.wordsImage, this.WordsWidth * 2, n2, this.WordsWidth, this.WordsLineHeight, this.actualListImage, 0, n2);
                                this.solvedWords[this.wordPicked - 1] = true;
                                this.handsOnWord = false;
                                this.lastPointerLine = 0;
                                this.SPTL(this.wordPicked);
                                if (!this.unscrambleLine[this.wordPicked - 1]) {
                                    this.PlayFirstSound(this.FirstSolveHiddenSound, this.SolveHiddenSound, true, true, this.PlayFirstSolveHidden);
                                    this.PlayFirstSolveHidden = false;
                                }
                                else {
                                    this.PlayFirstSound(this.FirstSolveUnscrambleSound, this.SolveUnscrambleSound, true, true, this.PlayFirstSolveUnscramble);
                                    this.PlayFirstSolveUnscramble = false;
                                }
                            }
                            else {
                                this.ShowMessage(this.TextOWS, false);
                                this.SPTL(0);
                                this.handsOnWord = false;
                                this.answerTextField.setVisible(false);
                                this.SP(false, this.wordPicked - 1);
                                if (this.SolvePressed) {
                                    if (!this.AllowReset) {
                                        this.playMode = 3;
                                    }
                                    else {
                                        this.playMode = 5;
                                    }
                                    this.repaint();
                                    n = 1;
                                    if (this.FullSolveWins) {
                                        if (this.Sentence.length() > 0) {
                                            s = this.Sentence;
                                        }
                                        else {
                                            s = this.TextWUS;
                                        }
                                        fullSolveWins = true;
                                    }
                                }
                                else {
                                    this.PDC();
                                }
                                this.PlayFirstSound(this.FirstSolveCircleSound, this.SolveCircleSound, true, false, this.PlayFirstSolveCircle);
                                this.PlayFirstSolveCircle = false;
                            }
                        }
                        else if (this.SolveDouble) {
                            if (!this.AllowReset) {
                                this.playMode = 3;
                            }
                            this.SP(true, 0);
                            if (this.FullSolveWins) {
                                if (this.AllowReset) {
                                    this.playMode = 5;
                                    if (this.Sentence.length() > 0) {
                                        s = this.Sentence;
                                    }
                                    else {
                                        s = this.TextSPA;
                                    }
                                }
                                else {
                                    this.playMode = 3;
                                    if (this.Sentence.length() > 0) {
                                        s = this.Sentence;
                                    }
                                    else {
                                        s = this.TextSBP;
                                    }
                                }
                            }
                            n = 1;
                            fullSolveWins = this.FullSolveWins;
                        }
                        else {
                            if (this.AllowFullSolve) {
                                this.ShowMessage(this.TextAOL, false);
                                this.SolveDouble = true;
                            }
                            else {
                                this.ShowMessage(this.TextSLS, false);
                            }
                            this.PlayASound(this.SolveAllNoteSound, true, true);
                        }
                        if (fullSolveWins && this.PlayASound(this.WinSound, true, false)) {
                            n = 0;
                        }
                        if (n == 1) {
                            this.PlayASound(this.SolveAllSound, true, false);
                        }
                        if (!s.equals("")) {
                            this.PW(s);
                        }
                    }
                    else {
                        if (!this.AllowReset) {
                            this.playMode = 3;
                        }
                        this.ShowMessage(this.TextSBU, false);
                    }
                    if (this.playMode == 2 | this.playMode == 4) {
                        if (this.WordsFound == this.WordCount & this.Sentence.length() > 0 & !this.SentenceSolved) {
                            this.playMode = 6;
                            this.CF(this.gridImage.getGraphics());
                        }
                        else {
                            this.playMode = 1;
                        }
                    }
                }
                if (x >= this.AboutButtonLeft & x <= this.AboutButtonLeft + this.ButtonsWidth) {
                    this.PlayASound(this.ButtonSound, false, false);
                    this.SolveDouble = false;
                    this.playMode = 4;
                    if (!this.SpecialAboutBox) {
                        if (!this.HideNameOnAbout) {
                            this.ShowMessage(String.valueOf(String.valueOf(this.TextPCB)).concat(String.valueOf(String.valueOf(this.RegistrationName))), false);
                        }
                        else {
                            this.ShowMessage("", false);
                        }
                    }
                    else {
                        this.ShowMessage("Java Puzzle Player - Version 3.1", false);
                    }
                    this.PlayASound(this.AboutButtonSound, true, false);
                }
            }
            else {
                this.SolveDouble = false;
                if (this.inPuzzleMode | (this.playMode == 2 & this.ScrollNote)) {
                    if (this.playMode == 1 & x > this.GridLeftPos + this.GridWidth & x < this.appletWidth - this.RightWidth) {
                        if (this.puzzleLoaded & x > this.WordsLeftPos) {
                            boolean b = false;
                            boolean b2 = true;
                            this.wordPicked = (this.WordsTopOffset + y - this.GridTopPos) / this.WordsLineHeight + 1;
                            if (this.wordPicked > this.LineCount) {
                                this.wordPicked = this.LineCount;
                            }
                            if (this.questionLine[this.wordPicked - 1]) {
                                if (this.PlaySound) {
                                    if (this.SayWordClick) {
                                        b = this.PlayWordSound(this.wordPicked, false);
                                    }
                                    if (!b) {
                                        b = this.PlayFirstSound(this.FirstClickSentenceSound, this.ClickSentenceSound, true, false, this.PlayFirstClickSentence);
                                        this.PlayFirstClickSentence = false;
                                    }
                                }
                                int wordPicked = this.wordPicked;
                                final int n3 = (this.WordsTopOffset + this.GridHeight + 2) / this.WordsLineHeight;
                                while (this.questionLine[wordPicked - 1] & wordPicked <= this.LineCount) {
                                    ++wordPicked;
                                }
                                if (wordPicked <= this.LineCount) {
                                    if (wordPicked > n3) {
                                        this.PWD();
                                    }
                                    this.wordPicked = wordPicked;
                                }
                            }
                            if (!this.questionLine[this.wordPicked - 1]) {
                                this.SPTL(this.wordPicked);
                                this.answerTextField.setVisible(false);
                                if (this.SolveAnswers.length() != 0 && !this.solvedWords[this.wordPicked - 1]) {
                                    b2 = false;
                                    int n4;
                                    int n5;
                                    for (n4 = 1, n5 = 0; this.wordPicked > n4; ++n4, n5 = n5 + this.SolveAnswers.substring(n5, this.SolveAnswers.length()).indexOf(";") + 1) {}
                                    this.answerValue = this.SolveAnswers.substring(n5, n5 + this.SolveAnswers.substring(n5, this.SolveAnswers.length()).indexOf(";")).toUpperCase();
                                    if (this.answerValue.length() != 0) {
                                        if (this.PlaySound) {
                                            this.toolkit.beep();
                                        }
                                        this.answerTextField.setVisible(true);
                                        this.answerTextField.setText("");
                                        this.answerTextField.requestFocus();
                                        this.ShowMessage(this.TextELV, false);
                                        if (this.questionLine[this.wordPicked - 2] && (this.SayWordClick & this.PlaySound)) {
                                            b = this.PlayWordSound(this.wordPicked - 1, false);
                                        }
                                        if (!b) {
                                            if (!this.unscrambleLine[this.wordPicked - 1]) {
                                                b = this.PlayFirstSound(this.FirstStartHiddenSound, this.StartHiddenSound, true, false, this.PlayFirstStartHidden);
                                                this.PlayFirstStartHidden = false;
                                            }
                                            else {
                                                b = this.PlayFirstSound(this.FirstStartUnscrambleSound, this.StartUnscrambleSound, true, false, this.PlayFirstStartUnscramble);
                                                this.PlayFirstStartUnscramble = false;
                                            }
                                        }
                                    }
                                    else {
                                        this.answerTextField.setVisible(false);
                                    }
                                }
                                if (!b & b2) {
                                    if (this.SayWordClick & this.PlaySound) {
                                        b = this.PlayWordSound(this.wordPicked, false);
                                    }
                                    if (!b) {
                                        this.PlayFirstSound(this.FirstClickWordSound, this.ClickWordSound, true, false, this.PlayFirstClickWord);
                                        this.PlayFirstClickWord = false;
                                    }
                                }
                            }
                        }
                    }
                    else if (x > this.appletWidth - this.RightWidth) {
                        this.PlayASound(this.ButtonSound, false, false);
                        if (this.ScrollWords & this.inPuzzleMode) {
                            if (y > this.GridTopPos & y < this.GridTopPos + this.ScrollButtonHeight) {
                                this.WordsTopOffset = (this.WordsTopOffset / this.WordsLineHeight - 1) * this.WordsLineHeight;
                                if (this.WordsTopOffset < 0) {
                                    this.WordsTopOffset = 0;
                                }
                                this.RST();
                            }
                            else if (y > this.GridTopPos + this.GridHeight - this.ScrollButtonHeight & y < this.GridTopPos + this.GridHeight) {
                                this.WordsTopOffset = (this.WordsTopOffset / this.WordsLineHeight + 1) * this.WordsLineHeight;
                                if (this.WordsTopOffset >= this.totalWordListHeight - this.GridHeight) {
                                    this.WordsTopOffset = this.totalWordListHeight - this.GridHeight;
                                }
                                this.RST();
                            }
                            else if (y > this.GridTopPos + this.ScrollButtonHeight & y < this.GridTopPos + this.GridHeight - this.ScrollButtonHeight) {
                                if (y < this.WordsSliderTop) {
                                    this.PWU();
                                }
                                else if (y > this.WordsSliderTop + this.SliderHeight) {
                                    this.PWD();
                                }
                            }
                        }
                        if (this.ScrollNote & this.playMode == 2) {
                            if (y > this.GridTopPos & y < this.GridTopPos + this.ScrollButtonHeight) {
                                this.NoteTopOffset -= this.WordsLineHeight;
                                if (this.NoteTopOffset < 0) {
                                    this.NoteTopOffset = 0;
                                }
                                this.RST();
                            }
                            else if (y > this.GridTopPos + this.GridHeight - this.ScrollButtonHeight & y < this.GridTopPos + this.GridHeight) {
                                this.NoteTopOffset += this.WordsLineHeight;
                                if (this.NoteTopOffset >= this.NoteHeight - this.GridHeight) {
                                    this.NoteTopOffset = this.NoteHeight - this.GridHeight;
                                }
                                this.RST();
                            }
                            else if (y > this.GridTopPos + this.ScrollButtonHeight & y < this.GridTopPos + this.GridHeight - this.ScrollButtonHeight) {
                                if (y < this.NoteSliderTop) {
                                    this.PWU();
                                }
                                else if (y > this.NoteSliderTop + this.SliderHeight) {
                                    this.PWD();
                                }
                            }
                        }
                    }
                }
                else if (this.playMode == 4 && x < this.appletWidth - this.RightWidth && y < this.ButtonsTop) {
                    this.ac.showDocument(this.aboutUrl, "_Blank");
                }
            }
        }
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.xPos = mouseEvent.getX();
        this.yPos = mouseEvent.getY();
        if (((this.puzzleLoaded & this.inPuzzleMode) | (this.playMode == 2 & this.ScrollNote)) && (this.yPos >= this.GridTopPos & this.yPos <= this.GridTopPos + this.GridHeight)) {
            this.SolveDouble = false;
            if (this.xPos >= this.GridLeftPos & this.xPos <= this.GridLeftPos + this.GridWidth) {
                if (this.playMode == 1) {
                    if (this.CircleSound != null & this.PlaySound & this.UseBaseSounds) {
                        this.CircleSound.loop();
                    }
                    this.CirclingWord = true;
                    this.startCircling = true;
                    this.endCircling = false;
                    this.xStartCell = (this.xPos - this.GridLeftPos - this.CellXOffset) / this.CellWidth;
                    this.yStartCell = (this.yPos - this.GridTopPos - this.CellYOffset) / this.CellWidth;
                    this.cThread = new CircleThread();
                }
            }
            else if (this.xPos > this.appletWidth - this.RightWidth) {
                if ((this.ScrollWords & this.inPuzzleMode) && (this.yPos > this.WordsSliderTop & this.yPos < this.WordsSliderTop + this.SliderHeight)) {
                    if (this.SliderSound != null & this.PlaySound & this.UseBaseSounds) {
                        this.SliderSound.loop();
                    }
                    this.MovingSlider = true;
                    this.sliderMoveOffset = this.yPos - this.WordsSliderTop;
                    this.slThread = new SliderThread();
                }
                if ((this.ScrollNote & this.playMode == 2) && (this.yPos > this.NoteSliderTop & this.yPos < this.NoteSliderTop + this.SliderHeight)) {
                    if (this.SliderSound != null & this.PlaySound & this.UseBaseSounds) {
                        this.SliderSound.loop();
                    }
                    this.MovingSlider = true;
                    this.sliderMoveOffset = this.yPos - this.NoteSliderTop;
                    this.slThread = new SliderThread();
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.xPos = mouseEvent.getX();
        this.yPos = mouseEvent.getY();
        if (this.CirclingWord) {
            if (this.CircleSound != null & this.UseBaseSounds) {
                this.CircleSound.stop();
            }
            this.endCircling = true;
            this.repaint();
            this.CirclingWord = false;
            this.xEndCell = (this.xPos - this.GridLeftPos - this.CellXOffset) / this.CellWidth;
            this.yEndCell = (this.yPos - this.GridTopPos - this.CellYOffset) / this.CellWidth;
            String s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.xStartCell))).append(",").append(this.yStartCell).append(":").append(this.xEndCell).append(",").append(this.yEndCell)));
            int n = this.WordPosList.indexOf(s);
            if (n < 0) {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.xEndCell))).append(",").append(this.yEndCell).append(":").append(this.xStartCell).append(",").append(this.yStartCell)));
                n = this.WordPosList.indexOf(s);
            }
            if (n >= 0) {
                final int int1 = Integer.parseInt(this.WordPosList.substring(n + s.length(), n + s.length() + 4));
                if (!this.foundWords[int1 - 1]) {
                    if (this.SayWordCircle & this.PlaySound) {
                        this.PlayWordSound(int1, false);
                    }
                    this.SPTL(0);
                    this.foundWords[int1 - 1] = true;
                    this.solvedWords[int1 - 1] = true;
                    ++this.WordsFound;
                    final int n2 = (int1 - 1) * this.WordsLineHeight + 2;
                    this.CopyArea(this.wordsImage, this.WordsWidth, n2, this.WordsWidth, this.WordsLineHeight, this.actualListImage, 0, n2);
                    this.CircleWord(this.gridImage.getGraphics(), this.xStartCell, this.yStartCell, this.xEndCell, this.yEndCell, this.CellXOffset, this.CellYOffset, this.CircledColor, this.CircledColor, false, false);
                    this.answerTextField.setVisible(false);
                    if (this.WordsFound == this.WordCount) {
                        if (this.Sentence.length() > 0 & !this.SentenceSolved) {
                            this.playMode = 6;
                            this.CF(this.gridImage.getGraphics());
                        }
                        else {
                            this.PlayASound(this.WinSound, true, false);
                            if (this.cheated) {
                                this.PW(this.TextSWH);
                            }
                            else {
                                this.PW(this.TextSFR);
                            }
                        }
                    }
                    else {
                        if (!this.SayWordCircle) {
                            this.PlayASound(this.GoodSound, false, false);
                        }
                        this.ShowMessage(this.TextFOW, false);
                        if (this.ScrollWords) {
                            this.PDC();
                        }
                        this.repaint();
                    }
                }
                else {
                    this.PlayASound(this.BadSound, false, true);
                    this.ShowMessage(this.TextAFW, false);
                }
            }
            else {
                this.PlayASound(this.BadSound, false, true);
                this.ShowMessage(this.TextNCW, true);
            }
        }
        if (this.MovingSlider) {
            if (this.SliderSound != null & this.UseBaseSounds) {
                this.SliderSound.stop();
            }
            this.MovingSlider = false;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.xPos = mouseEvent.getX();
        this.yPos = mouseEvent.getY();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.xPos = mouseEvent.getX();
        this.yPos = mouseEvent.getY();
    }
    
    void answerTextField_textValueChanged(final TextEvent textEvent) {
        final String upperCase = this.answerTextField.getText().toUpperCase();
        if (this.playMode == 6) {
            if (!this.NoSpaceInHidden) {
                this.ShowMessage(this.TextHSN, false);
            }
            if (upperCase.equals(this.Sentence) | (this.NoSpaceInHidden & upperCase.equals(this.RemoveSpaces(this.Sentence)))) {
                this.answerTextField.setVisible(false);
                this.SentenceSolved = true;
                this.playMode = 1;
                if (this.WordsFound == this.WordCount) {
                    this.PlayASound(this.WinSound, true, true);
                    if (this.cheated) {
                        this.PW(this.TextSWH);
                    }
                    else {
                        this.PW(this.TextSFR);
                    }
                }
            }
        }
        else if (this.answerValue.length() > 0 && upperCase.equals(this.answerValue)) {
            this.ShowMessage(String.valueOf(String.valueOf(this.answerValue)).concat(String.valueOf(String.valueOf(this.TextISC))), false);
            final int n = (this.wordPicked - 1) * this.WordsLineHeight + 2;
            this.CopyArea(this.wordsImage, this.WordsWidth * 2, n, this.WordsWidth, this.WordsLineHeight, this.actualListImage, 0, n);
            this.answerTextField.setVisible(false);
            this.solvedWords[this.lastPointerLine - 1] = true;
            this.lastPointerLine = 0;
            this.repaint();
            if (this.PlaySound) {
                boolean playWordSound = false;
                if (this.SayWordClick) {
                    playWordSound = this.PlayWordSound(this.wordPicked, false);
                }
                if (!playWordSound) {
                    if (!this.unscrambleLine[this.wordPicked - 1]) {
                        this.PlayFirstSound(this.FirstFinishHiddenSound, this.FinishHiddenSound, true, true, this.PlayFirstFinishHidden);
                        this.PlayFirstFinishHidden = false;
                    }
                    else {
                        this.PlayFirstSound(this.FirstFinishUnscrambleSound, this.FinishUnscrambleSound, true, true, this.PlayFirstFinishUnscramble);
                        this.PlayFirstFinishUnscramble = false;
                    }
                }
            }
        }
    }
    
    String RemoveSpaces(String s) {
        for (int i = s.indexOf(32); i != -1; i = s.indexOf(32)) {
            if (i > 0) {
                s = String.valueOf(String.valueOf(s.substring(0, i))).concat(String.valueOf(String.valueOf(s.substring(i + 1, s.length()))));
            }
            else {
                s = s.substring(i + 1, s.length());
            }
        }
        return s;
    }
    
    void SetMessageFinalPosition() {
        this.messageLabel.setFont(this.SmallFont);
        this.messageLabel.setLocation(this.GridLeftPos, this.TextTop);
        this.messageLabel.setSize(this.GridWidth + this.WordsWidth - this.timerWidth, this.textHeight + 2);
        this.messageLabel.setAlignment(0);
        this.messageLabel.setForeground(this.TextColor);
        this.messageLabel.setBackground(this.BackColor);
    }
    
    void CircleWord(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final Color color2, final boolean b, final boolean b2) {
        final int n7 = (int)(n * this.CellWidth + n5 + this.CellWidth * 0.5);
        final int n8 = (int)(n2 * this.CellWidth + n6 + this.CellWidth * 0.4) - 1;
        final int n9 = (int)(n3 * this.CellWidth + n5 + this.CellWidth * 0.5);
        final int n10 = (int)(n4 * this.CellWidth + n6 + this.CellWidth * 0.4) - 1;
        final double n11 = (int)(this.CellWidth * 0.5);
        final double n12 = (int)(this.CellWidth * 0.5);
        double atan;
        if (n == n3) {
            atan = 1.5707963;
            if (n2 > n4) {
                atan = 4.7123889000000005;
            }
        }
        else {
            atan = Math.atan((n10 - n8) / (n7 - n9));
        }
        double n13 = 0.31415926;
        if (n < n3) {
            n13 = -n13;
        }
        for (int i = 0; i <= 10; ++i) {
            this.xPoints[i] = n7 + (int)(Math.sin(atan + i * n13) * n11);
            this.yPoints[i] = n8 + (int)(Math.cos(atan + i * n13) * n12);
        }
        final double n14 = atan + 3.1415926;
        for (int j = 11; j <= 21; ++j) {
            this.xPoints[j] = n9 + (int)(Math.sin(n14 + (j - 11) * n13) * n11);
            this.yPoints[j] = n10 + (int)(Math.cos(n14 + (j - 11) * n13) * n12);
        }
        this.xPoints[22] = this.xPoints[0];
        this.yPoints[22] = this.yPoints[0];
        if (n2 == n4) {
            this.yPoints[21] = this.yPoints[22];
            this.yPoints[11] = this.yPoints[10];
        }
        if (n == n3) {
            this.xPoints[21] = this.xPoints[22];
            this.xPoints[11] = this.xPoints[10];
        }
        if (b2) {
            graphics.setXORMode(Color.white);
        }
        if (b) {
            graphics.setColor(color2);
            graphics.fillPolygon(this.xPoints, this.yPoints, 22);
        }
        else {
            graphics.setColor(color);
            graphics.drawPolygon(this.xPoints, this.yPoints, 22);
        }
    }
    
    void CopyArea(final Image image, final int n, final int n2, final int n3, final int n4, final Image image2, final int n5, final int n6) {
        image2.getGraphics().drawImage(image, n5, n6, n5 + n3, n6 + n4, n, n2, n + n3, n2 + n4, null);
    }
    
    void RST() {
        if (this.inPuzzleMode) {
            this.WordsSliderTop = this.SliderBarTop + Math.round(this.SliderBarLength / 100.0f * this.WordsTopOffset / (this.WordsScrollLength / 100.0f));
        }
        else {
            this.NoteSliderTop = this.SliderBarTop + Math.round(this.SliderBarLength / 100.0f * this.NoteTopOffset / (this.NoteScrollLength / 100.0f));
        }
    }
    
    public void ShowMessage(final String text, final boolean b) {
        this.messageLabel.setText(text);
        if (b) {
            this.repaint();
        }
    }
    
    void SP(final boolean b, final int n) {
        int i = 0;
        final Graphics graphics = this.gridImage.getGraphics();
        graphics.setColor(this.SolveCircleColor);
        int n2 = 0;
        int n3 = this.WordPosList.indexOf(";");
        while (i < this.LineCount) {
            final String substring = this.WordPosList.substring(n2, n3);
            if (substring.length() > 0) {
                final int index = substring.indexOf(",");
                final int index2 = substring.indexOf(":");
                final int int1 = Integer.parseInt(substring.substring(0, index));
                final int int2 = Integer.parseInt(substring.substring(index + 1, index2));
                final String substring2 = substring.substring(index2 + 1, substring.length() - 4);
                final int index3 = substring2.indexOf(",");
                final int int3 = Integer.parseInt(substring2.substring(0, index3));
                final int int4 = Integer.parseInt(substring2.substring(index3 + 1, substring2.length()));
                if ((b | n == i) && !this.foundWords[i]) {
                    final int n4 = i * this.WordsLineHeight + 2;
                    this.CircleWord(graphics, int1, int2, int3, int4, this.CellXOffset, this.CellYOffset, this.SolveCircleColor, this.SolveCircleColor, false, false);
                    this.CopyArea(this.wordsImage, this.WordsWidth, n4, this.WordsWidth, this.WordsLineHeight, this.actualListImage, 0, n4);
                    this.foundWords[i] = true;
                    this.solvedWords[i] = true;
                    ++this.WordsFound;
                    this.cheated = true;
                }
            }
            n2 = n3 + 1;
            n3 = this.WordPosList.indexOf(";", n2);
            ++i;
        }
        if (this.WordsFound == this.WordCount) {
            this.SolvePressed = true;
        }
    }
    
    void CF(final Graphics graphics) {
        String s = this.FillPosList;
        while (s.length() > 0) {
            final int index = s.indexOf(";");
            final int index2 = s.indexOf(",");
            final int int1 = Integer.parseInt(s.substring(0, index2));
            final int int2 = Integer.parseInt(s.substring(index2 + 1, index));
            s = s.substring(index + 1, s.length());
            this.CircleWord(graphics, int1, int2, int1, int2, this.CellXOffset, this.CellYOffset, this.CirclingColor, this.CirclingColor, true, true);
        }
    }
    
    void SPTL(final int lastPointerLine) {
        if (lastPointerLine != this.lastPointerLine) {
            if (this.lastPointerLine > 0) {
                this.CopyArea(this.pointerSaveImage, 0, 0, this.pointerWidth, this.pointerHeight, this.actualListImage, 2, (this.lastPointerLine - 1) * this.WordsLineHeight + Math.round((this.WordsLineHeight - this.pointerHeight) / 2) + 2);
                this.lastPointerLine = -1;
                this.handsOnWord = false;
            }
            if (this.wordPicked > 0) {
                final int n = (lastPointerLine - 1) * this.WordsLineHeight + Math.round((this.WordsLineHeight - this.pointerHeight) / 2) + 2;
                this.CopyArea(this.actualListImage, 2, n, this.pointerWidth, this.pointerHeight, this.pointerSaveImage, 0, 0);
                this.CopyArea(this.pointerImage, 0, 0, this.pointerWidth, this.pointerHeight, this.actualListImage, 2, n);
                this.handsOnWord = true;
            }
            this.lastPointerLine = lastPointerLine;
        }
    }
    
    String GC(final String s) {
        final int length = s.length();
        long n = 13L;
        for (int i = 1; i <= length; ++i) {
            n = n + i + 3;
        }
        return "".concat(String.valueOf(String.valueOf(n)));
    }
    
    String PW(final String solveString) {
        this.DoDebug("  Process Win START", false);
        if (this.WinAddress.length() > 0) {
            if (this.doJump) {
                this.SolveString = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(solveString))).append(" ").append(this.TextJNP)));
            }
            else {
                this.SolveString = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(solveString))).append(" ").append(this.TextINP)));
            }
        }
        else {
            this.SolveString = solveString;
        }
        this.ShowMessage(solveString, false);
        this.puzzleSolved = true;
        if (this.WinAddress.length() > 0 & this.doJump) {
            this.playMode = 5;
            this.paint(this.getGraphics());
            try {
                Thread.sleep(this.WinPause * 1000);
            }
            catch (InterruptedException ex) {}
            this.ac.showDocument(this.jumpUrl, "_self");
        }
        else {
            this.repaint();
        }
        this.DoDebug("  Process Win END", false);
        return solveString;
    }
    
    void PWD() {
        if (this.inPuzzleMode) {
            this.WordsTopOffset = this.WordsTopOffset + this.GridHeight + 3;
            if (this.WordsTopOffset >= this.totalWordListHeight - this.GridHeight) {
                this.WordsTopOffset = this.totalWordListHeight - this.GridHeight;
            }
            else {
                int n = this.WordsTopOffset / this.WordsLineHeight;
                if (n > 0) {
                    if (this.questionLine[n]) {
                        while (this.questionLine[n - 1] & n > 0) {
                            --n;
                        }
                    }
                    else {
                        while (this.questionLine[n - 1] & n > 0) {
                            --n;
                        }
                    }
                }
                this.WordsTopOffset = n * this.WordsLineHeight;
                if (this.WordsTopOffset >= this.totalWordListHeight - this.GridHeight) {
                    this.WordsTopOffset = this.totalWordListHeight - this.GridHeight;
                }
            }
        }
        else {
            this.NoteTopOffset += this.GridHeight;
            if (this.NoteTopOffset >= this.NoteHeight - this.GridHeight) {
                this.NoteTopOffset = this.NoteHeight - this.GridHeight;
            }
        }
        this.RST();
        this.repaint();
    }
    
    void PWU() {
        if (this.inPuzzleMode) {
            this.WordsTopOffset -= this.GridHeight;
            if (this.WordsTopOffset < 0) {
                this.WordsTopOffset = 0;
            }
            else {
                int n = this.WordsTopOffset / this.WordsLineHeight;
                if (n > 0) {
                    if (this.questionLine[n]) {
                        while (this.questionLine[n - 1] & n > 0) {
                            --n;
                        }
                    }
                    else {
                        while (this.questionLine[n - 1] & n > 0) {
                            --n;
                        }
                    }
                }
                this.WordsTopOffset = n * this.WordsLineHeight;
                if (this.WordsTopOffset >= this.totalWordListHeight - this.GridHeight) {
                    this.WordsTopOffset = this.totalWordListHeight - this.GridHeight;
                }
            }
        }
        else {
            this.NoteTopOffset -= this.GridHeight;
            if (this.NoteTopOffset < 0) {
                this.NoteTopOffset = 0;
            }
        }
        this.RST();
        this.repaint();
    }
    
    void PDC() {
        int i = 0;
        int n = (this.WordsTopOffset + this.GridHeight + 2) / this.WordsLineHeight;
        int n2 = 1;
        while (i < n) {
            if (!this.foundWords[i]) {
                n2 = 0;
                i = n;
            }
            ++i;
        }
        if (n2 == 1) {
            if (this.questionLine[n - 1]) {
                while (this.questionLine[n - 1] & n > 0) {
                    --n;
                }
            }
            else {
                while (this.questionLine[n] & n > 0) {
                    --n;
                }
            }
            this.WordsTopOffset += n * this.WordsLineHeight;
            if (this.WordsTopOffset >= this.totalWordListHeight - this.GridHeight) {
                this.WordsTopOffset = this.totalWordListHeight - this.GridHeight;
            }
            this.RST();
            this.repaint();
        }
    }
    
    AudioClip InitializeSound(final String s) {
        AudioClip audioClip = null;
        if (!s.equals("")) {
            this.DoDebug(String.valueOf(String.valueOf(new StringBuffer(" Initializing Sound: ").append(s).append("..."))), true);
            final URL actualLocation = this.getActualLocation(this.ActionSoundsLocation, s);
            if (actualLocation != null) {
                try {
                    audioClip = this.getAudioClip(actualLocation);
                    if (audioClip != null) {
                        audioClip.play();
                        audioClip.stop();
                    }
                }
                catch (NullPointerException ex) {
                    audioClip = null;
                    this.ShowMessage(String.valueOf(String.valueOf(s)).concat(" invalid!"), true);
                }
            }
        }
        return audioClip;
    }
    
    boolean PlayASound(final AudioClip audioClip, final boolean b, final boolean b2) {
        boolean b3 = false;
        if (this.PlaySound) {
            if (audioClip != null) {
                if (b) {
                    this.MuteSounds(true);
                }
                this.DoDebug(" PLAY SOUND", false);
                audioClip.play();
                b3 = true;
            }
            else if (b2) {
                this.toolkit.beep();
            }
        }
        return b3;
    }
    
    boolean PlayFirstSound(final AudioClip audioClip, final AudioClip audioClip2, final boolean b, final boolean b2, final boolean b3) {
        boolean b4;
        if (audioClip != null & b3) {
            b4 = this.PlayASound(audioClip, b, b2);
        }
        else {
            b4 = this.PlayASound(audioClip2, b, b2);
        }
        return b4;
    }
    
    boolean PlayWordSound(final int n, final boolean b) {
        boolean b2 = false;
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.BasePuzzleName))).append("_").append(n).append(".au")));
        if (!this.WordSoundLoaded[n - 1]) {
            this.WordSoundLoaded[n - 1] = true;
            try {
                this.DoDebug(String.valueOf(String.valueOf(new StringBuffer(" INIT WORD SOUND: ").append(this.WordSoundsLocation).append("/").append(value))), true);
                final URL actualLocation = this.getActualLocation(this.WordSoundsLocation, value);
                if (actualLocation != null) {
                    this.WordSound[n - 1] = this.getAudioClip(actualLocation);
                    if (b & this.WordSound[n - 1] != null) {
                        this.WordSound[n - 1].play();
                        this.WordSound[n - 1].stop();
                    }
                }
                else {
                    this.DoDebug(String.valueOf(String.valueOf(value)).concat(" invalid!"), true);
                    this.WordSound[n - 1] = null;
                }
            }
            catch (NullPointerException ex) {
                this.WordSound[n - 1] = null;
                this.ShowMessage(String.valueOf(String.valueOf(value)).concat(" invalid!"), true);
            }
        }
        if (!b & this.WordSound[n - 1] != null) {
            this.MuteSounds(false);
            this.DoDebug(" PLAY WORD SOUND: ".concat(String.valueOf(String.valueOf(n))), false);
            this.WordSound[n - 1].play();
            b2 = true;
        }
        return b2;
    }
    
    void PlayPLAYSound() {
        if (this.HasHidden) {
            this.PlayFirstSound(this.FirstPlayHiddenSound, this.PlayHiddenSound, true, false, this.PlayFirstPlay);
            this.PlayFirstPlay = false;
        }
        else if (this.HasScrambled) {
            this.PlayFirstSound(this.FirstPlayUnscrambleSound, this.PlayUnscrambleSound, true, false, this.PlayFirstPlay);
            this.PlayFirstPlay = false;
        }
        else {
            this.PlayFirstSound(this.FirstPlayStandardSound, this.PlayStandardSound, true, false, this.PlayFirstPlay);
            this.PlayFirstPlay = false;
        }
    }
    
    void MuteSounds(final boolean b) {
        if (this.WinSound != null & !b) {
            this.WinSound.stop();
        }
        if (this.ClickWordSound != null) {
            this.ClickWordSound.stop();
        }
        if (this.FirstClickWordSound != null) {
            this.FirstClickWordSound.stop();
        }
        if (this.ClickSentenceSound != null) {
            this.ClickSentenceSound.stop();
        }
        if (this.FirstClickSentenceSound != null) {
            this.FirstClickSentenceSound.stop();
        }
        if (this.SolveHiddenSound != null) {
            this.SolveHiddenSound.stop();
        }
        if (this.FirstSolveHiddenSound != null) {
            this.FirstSolveHiddenSound.stop();
        }
        if (this.SolveUnscrambleSound != null) {
            this.SolveUnscrambleSound.stop();
        }
        if (this.FirstSolveUnscrambleSound != null) {
            this.FirstSolveUnscrambleSound.stop();
        }
        if (this.SolveCircleSound != null) {
            this.SolveCircleSound.stop();
        }
        if (this.FirstSolveCircleSound != null) {
            this.FirstSolveCircleSound.stop();
        }
        if (this.SolveAllSound != null) {
            this.SolveAllSound.stop();
        }
        if (this.SolveErrorSound != null) {
            this.SolveErrorSound.stop();
        }
        if (this.SolveAllNoteSound != null) {
            this.SolveAllNoteSound.stop();
        }
        if (this.StartUnscrambleSound != null) {
            this.StartUnscrambleSound.stop();
        }
        if (this.FirstStartUnscrambleSound != null) {
            this.FirstStartUnscrambleSound.stop();
        }
        if (this.FinishUnscrambleSound != null) {
            this.FinishUnscrambleSound.stop();
        }
        if (this.FirstFinishUnscrambleSound != null) {
            this.FirstFinishUnscrambleSound.stop();
        }
        if (this.StartHiddenSound != null) {
            this.StartHiddenSound.stop();
        }
        if (this.FirstStartHiddenSound != null) {
            this.FirstStartHiddenSound.stop();
        }
        if (this.FinishHiddenSound != null) {
            this.FinishHiddenSound.stop();
        }
        if (this.FirstFinishHiddenSound != null) {
            this.FirstFinishHiddenSound.stop();
        }
        if (this.StartPhraseSound != null) {
            this.StartPhraseSound.stop();
        }
        if (this.PlayStandardSound != null) {
            this.PlayStandardSound.stop();
        }
        if (this.FirstPlayStandardSound != null) {
            this.FirstPlayStandardSound.stop();
        }
        if (this.PlayHiddenSound != null) {
            this.PlayHiddenSound.stop();
        }
        if (this.FirstPlayHiddenSound != null) {
            this.FirstPlayHiddenSound.stop();
        }
        if (this.PlayUnscrambleSound != null) {
            this.PlayUnscrambleSound.stop();
        }
        if (this.FirstPlayUnscrambleSound != null) {
            this.FirstPlayUnscrambleSound.stop();
        }
        if (this.NoteButtonSound != null) {
            this.NoteButtonSound.stop();
        }
        if (this.FirstNoteButtonSound != null) {
            this.FirstNoteButtonSound.stop();
        }
        if (this.AboutButtonSound != null) {
            this.AboutButtonSound.stop();
        }
    }
    
    URL getActualLocation(String s, final String s2) {
        URL url = null;
        if (s.equals("")) {
            try {
                url = new URL(this.getDocumentBase(), s2);
            }
            catch (MalformedURLException ex) {}
        }
        else {
            boolean b = false;
            if (s.length() > 3) {
                if (s.substring(0, 3).toLowerCase().equals("www.")) {
                    b = true;
                }
                else if (s.length() > 6 && s.substring(0, 6).toLowerCase().equals("http://")) {
                    b = true;
                }
            }
            if (!b) {
                String string;
                int length;
                for (string = this.getDocumentBase().toString(), length = string.length(); length > 1 & !string.substring(length - 1, length).equals("\\") & !string.substring(length - 1, length).equals("/"); --length) {}
                if (length > 1) {
                    s = String.valueOf(String.valueOf(string.substring(0, length))).concat(String.valueOf(String.valueOf(s)));
                    final int length2 = s.length();
                    if (!s.substring(length2 - 1, length2).equals("/")) {
                        s = String.valueOf(String.valueOf(s)).concat("/");
                    }
                }
            }
            try {
                url = new URL(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s2))));
            }
            catch (MalformedURLException ex2) {
                if (this.DebugString.equals("")) {
                    this.DebugString = String.valueOf(String.valueOf(new StringBuffer("Bad URL: ").append(s).append(s2)));
                }
                try {
                    url = new URL(this.getDocumentBase(), s2);
                }
                catch (MalformedURLException ex3) {}
            }
        }
        return url;
    }
    
    void DoDebug(final String s, final boolean b) {
        if (this.DebugMode) {
            System.out.println("==> ".concat(String.valueOf(String.valueOf(s))));
            if (b) {
                this.ShowMessage(s, true);
            }
        }
    }
    
    class SliderThread extends Thread
    {
        public SliderThread() {
            this.start();
        }
        
        public void run() {
            while (WSPlayApplet.this.MovingSlider) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                if (WSPlayApplet.this.inPuzzleMode) {
                    WSPlayApplet.this.WordsSliderTop = WSPlayApplet.this.yPos - WSPlayApplet.this.sliderMoveOffset;
                    if (WSPlayApplet.this.WordsSliderTop < WSPlayApplet.this.SliderBarTop) {
                        WSPlayApplet.this.WordsSliderTop = WSPlayApplet.this.SliderBarTop;
                    }
                    if (WSPlayApplet.this.WordsSliderTop > WSPlayApplet.this.SliderBarBottom) {
                        WSPlayApplet.this.WordsSliderTop = WSPlayApplet.this.SliderBarBottom;
                    }
                    if (WSPlayApplet.this.WordsSliderTop == WSPlayApplet.this.lastWordsSliderTop) {
                        continue;
                    }
                    WSPlayApplet.this.lastWordsSliderTop = WSPlayApplet.this.WordsSliderTop;
                    WSPlayApplet.this.WordsTopOffset = Math.round(WSPlayApplet.this.WordsScrollLength / 100.0f * (WSPlayApplet.this.WordsSliderTop - WSPlayApplet.this.SliderBarTop) / (WSPlayApplet.this.SliderBarLength / 100.0f));
                    WSPlayApplet.this.repaint();
                }
                else {
                    WSPlayApplet.this.NoteSliderTop = WSPlayApplet.this.yPos - WSPlayApplet.this.sliderMoveOffset;
                    if (WSPlayApplet.this.NoteSliderTop < WSPlayApplet.this.SliderBarTop) {
                        WSPlayApplet.this.NoteSliderTop = WSPlayApplet.this.SliderBarTop;
                    }
                    if (WSPlayApplet.this.NoteSliderTop > WSPlayApplet.this.SliderBarBottom) {
                        WSPlayApplet.this.NoteSliderTop = WSPlayApplet.this.SliderBarBottom;
                    }
                    if (WSPlayApplet.this.NoteSliderTop == WSPlayApplet.this.lastNoteSliderTop) {
                        continue;
                    }
                    WSPlayApplet.this.lastNoteSliderTop = WSPlayApplet.this.NoteSliderTop;
                    WSPlayApplet.this.NoteTopOffset = Math.round(WSPlayApplet.this.NoteScrollLength / 100.0f * (WSPlayApplet.this.NoteSliderTop - WSPlayApplet.this.SliderBarTop) / (WSPlayApplet.this.SliderBarLength / 100.0f));
                    WSPlayApplet.this.repaint();
                }
            }
        }
    }
    
    class CircleThread extends Thread
    {
        public CircleThread() {
            this.start();
            WSPlayApplet.this.oxEndCell = 0;
            WSPlayApplet.this.oyEndCell = 0;
        }
        
        public void run() {
            while (WSPlayApplet.this.CirclingWord) {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
                if (WSPlayApplet.this.xPos != WSPlayApplet.this.lastxPos | WSPlayApplet.this.yPos != WSPlayApplet.this.lastyPos) {
                    WSPlayApplet.this.xEndCell = (WSPlayApplet.this.xPos - WSPlayApplet.this.GridLeftPos - WSPlayApplet.this.CellXOffset) / WSPlayApplet.this.CellWidth;
                    WSPlayApplet.this.yEndCell = (WSPlayApplet.this.yPos - WSPlayApplet.this.GridTopPos - WSPlayApplet.this.CellYOffset) / WSPlayApplet.this.CellWidth;
                    if (!(WSPlayApplet.this.oxEndCell != WSPlayApplet.this.xEndCell | WSPlayApplet.this.oyEndCell != WSPlayApplet.this.yEndCell)) {
                        continue;
                    }
                    WSPlayApplet.this.repaint();
                }
            }
        }
    }
    
    class StartThread extends Thread
    {
        public StartThread() {
            this.start();
        }
        
        public void run() {
            WSPlayApplet.this.DoDebug("*** Loader Thread Started ***", false);
            WSPlayApplet.this.handsOnWord = false;
            WSPlayApplet.this.SentenceSolved = false;
            WSPlayApplet.this.playMode = 0;
            WSPlayApplet.this.WordsTopOffset = 0;
            WSPlayApplet.this.NoteTopOffset = 0;
            WSPlayApplet.this.WordsFound = 0;
            WSPlayApplet.this.lastPointerLine = 0;
            WSPlayApplet.this.lastWordsSliderTop = 0;
            WSPlayApplet.this.lastNoteSliderTop = 0;
            WSPlayApplet.this.puzzleSolved = false;
            WSPlayApplet.this.SolvePressed = false;
            WSPlayApplet.this.cheated = false;
            WSPlayApplet.this.ShowMessage(WSPlayApplet.this.TextLOP, true);
            WSPlayApplet.this.DoDebug(" Waiting For Frame", false);
            WSPlayApplet.this.frameImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, String.valueOf(String.valueOf(WSPlayApplet.this.BasePuzzleName)).concat("_frm.jpg")));
            WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.frameImage, 0);
            try {
                WSPlayApplet.this.tracker.waitForID(0);
            }
            catch (InterruptedException ex) {
                WSPlayApplet.this.LoadError = true;
            }
            WSPlayApplet.this.frameLoaded = true;
            WSPlayApplet.this.SpecialAboutBox = false;
            WSPlayApplet.this.DoDebug(" Initializing Arrays", false);
            WSPlayApplet.this.foundWords = new boolean[WSPlayApplet.this.LineCount];
            WSPlayApplet.this.solvedWords = new boolean[WSPlayApplet.this.LineCount];
            WSPlayApplet.this.questionLine = new boolean[WSPlayApplet.this.LineCount];
            WSPlayApplet.this.unscrambleLine = new boolean[WSPlayApplet.this.LineCount];
            if (WSPlayApplet.this.SayWordClick | WSPlayApplet.this.SayWordCircle) {
                WSPlayApplet.this.WordSound = new AudioClip[WSPlayApplet.this.LineCount];
                WSPlayApplet.this.WordSoundLoaded = new boolean[WSPlayApplet.this.LineCount];
                for (int i = 0; i < WSPlayApplet.this.LineCount; ++i) {
                    WSPlayApplet.this.WordSound[i] = null;
                    WSPlayApplet.this.WordSoundLoaded[i] = false;
                }
            }
            int j = 0;
            int n = 0;
            WSPlayApplet.this.answerValue = "";
            while (j < WSPlayApplet.this.LineCount) {
                if (WSPlayApplet.this.SolveAnswers.length() > 0) {
                    final int index = WSPlayApplet.this.SolveAnswers.substring(n, WSPlayApplet.this.SolveAnswers.length()).indexOf(";");
                    WSPlayApplet.this.answerValue = WSPlayApplet.this.SolveAnswers.substring(n, n + index).toUpperCase();
                    n = n + index + 1;
                }
                if (WSPlayApplet.this.WordLineTypes.substring(j, j + 1).equals("Q")) {
                    WSPlayApplet.this.foundWords[j] = true;
                    WSPlayApplet.this.solvedWords[j] = true;
                    WSPlayApplet.this.questionLine[j] = true;
                    WSPlayApplet.this.unscrambleLine[j] = false;
                }
                else {
                    WSPlayApplet.this.foundWords[j] = false;
                    WSPlayApplet.this.questionLine[j] = false;
                    if (WSPlayApplet.this.WordLineTypes.substring(j, j + 1).equals("H")) {
                        WSPlayApplet.this.HasHidden = true;
                    }
                    if (WSPlayApplet.this.WordLineTypes.substring(j, j + 1).equals("S")) {
                        WSPlayApplet.this.HasScrambled = true;
                        WSPlayApplet.this.unscrambleLine[j] = true;
                    }
                    else {
                        WSPlayApplet.this.unscrambleLine[j] = false;
                    }
                    if (WSPlayApplet.this.answerValue.length() > 0) {
                        WSPlayApplet.this.solvedWords[j] = false;
                    }
                    else {
                        WSPlayApplet.this.solvedWords[j] = true;
                    }
                }
                ++j;
            }
            WSPlayApplet.this.DoDebug(" Loading Puzzle Graphics", false);
            if (WSPlayApplet.this.appletWasStopped) {
                return;
            }
            if (WSPlayApplet.this.ShowNote & !WSPlayApplet.this.puzzleLoaded) {
                WSPlayApplet.this.noteImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, String.valueOf(String.valueOf(WSPlayApplet.this.BasePuzzleName)).concat("_txt.jpg")));
                WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.noteImage, 1);
            }
            WSPlayApplet.this.gridLoadImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, String.valueOf(String.valueOf(WSPlayApplet.this.BasePuzzleName)).concat("_pzl.jpg")));
            WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.gridLoadImage, 2);
            if (!WSPlayApplet.this.puzzleLoaded) {
                WSPlayApplet.this.wordsImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, String.valueOf(String.valueOf(WSPlayApplet.this.BasePuzzleName)).concat("_wds.jpg")));
                WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.wordsImage, 2);
            }
            if (!WSPlayApplet.this.puzzleLoaded) {
                WSPlayApplet.this.buttonsImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, String.valueOf(String.valueOf(WSPlayApplet.this.BasePuzzleName)).concat("_btn.jpg")));
                WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.buttonsImage, 2);
            }
            if (!WSPlayApplet.this.puzzleLoaded & (WSPlayApplet.this.ScrollWords | WSPlayApplet.this.ScrollNote)) {
                WSPlayApplet.this.sliderImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, String.valueOf(String.valueOf(WSPlayApplet.this.BasePuzzleName)).concat("_sld.jpg")));
                WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.sliderImage, 2);
            }
            if (!WSPlayApplet.this.puzzleLoaded && !WSPlayApplet.this.PointerFile.equals("")) {
                WSPlayApplet.this.pointerImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, WSPlayApplet.this.PointerFile));
                WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.pointerImage, 2);
            }
            if (!WSPlayApplet.this.puzzleLoaded & WSPlayApplet.this.ShowMuteButton) {
                if (!WSPlayApplet.this.AudioOnFile.equals("")) {
                    WSPlayApplet.this.audioOnImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, WSPlayApplet.this.AudioOnFile));
                    WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.audioOnImage, 2);
                }
                if (!WSPlayApplet.this.AudioOffFile.equals("")) {
                    WSPlayApplet.this.audioOffImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.PuzzleGraphicsLocation, WSPlayApplet.this.AudioOffFile));
                    WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.audioOffImage, 2);
                }
            }
            if (WSPlayApplet.this.ShowNote) {
                WSPlayApplet.this.DoDebug(" ".concat(String.valueOf(String.valueOf(WSPlayApplet.this.TextWFN))), true);
                try {
                    WSPlayApplet.this.tracker.waitForID(1);
                }
                catch (InterruptedException ex2) {
                    WSPlayApplet.this.DebugString = WSPlayApplet.this.ErrorNOT;
                }
                WSPlayApplet.this.playMode = 2;
                WSPlayApplet.this.NoteWidth = WSPlayApplet.this.noteImage.getWidth(null);
                WSPlayApplet.this.NoteHeight = WSPlayApplet.this.noteImage.getHeight(null);
                WSPlayApplet.this.NoteScrollLength = WSPlayApplet.this.NoteHeight - WSPlayApplet.this.GridHeight;
                WSPlayApplet.this.SetMessageFinalPosition();
                WSPlayApplet.this.repaint();
            }
            if (WSPlayApplet.this.appletWasStopped) {
                return;
            }
            if (WSPlayApplet.this.UseTutorialSounds) {
                WSPlayApplet.this.DoDebug(" ".concat(String.valueOf(String.valueOf(WSPlayApplet.this.TextWFN))), true);
                WSPlayApplet.this.FirstNoteButtonSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstNoteButtonSoundFile);
                WSPlayApplet.this.FirstPlayStandardSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstPlayStandardSoundFile);
                WSPlayApplet.this.FirstPlayHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstPlayHiddenSoundFile);
                WSPlayApplet.this.FirstPlayUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstPlayUnscrambleSoundFile);
                WSPlayApplet.this.NoteButtonSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.NoteButtonSoundFile);
                WSPlayApplet.this.PlayStandardSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.PlayStandardSoundFile);
                WSPlayApplet.this.PlayHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.PlayHiddenSoundFile);
                WSPlayApplet.this.PlayUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.PlayUnscrambleSoundFile);
            }
            WSPlayApplet.this.DoDebug(" ".concat(String.valueOf(String.valueOf(WSPlayApplet.this.TextWFP))), true);
            try {
                WSPlayApplet.this.tracker.waitForID(2);
            }
            catch (InterruptedException ex3) {
                WSPlayApplet.this.DebugString = WSPlayApplet.this.ErrorPUZ;
            }
            if (WSPlayApplet.this.appletWasStopped) {
                return;
            }
            WSPlayApplet.this.DoDebug(" Initialize Puzzle...", true);
            if (!WSPlayApplet.this.AudioOnFile.equals("")) {
                WSPlayApplet.this.AudioLeft = WSPlayApplet.this.appletWidth - WSPlayApplet.this.RightWidth + 4;
                WSPlayApplet.this.AudioTop = WSPlayApplet.this.ButtonsTop + WSPlayApplet.this.ButtonsHeight / 2 - WSPlayApplet.this.audioOnImage.getHeight(null) / 2;
                WSPlayApplet.this.audioImageLoaded = true;
            }
            WSPlayApplet.this.gridImage = WSPlayApplet.this.createImage(WSPlayApplet.this.gridLoadImage.getWidth(null), WSPlayApplet.this.gridLoadImage.getHeight(null));
            WSPlayApplet.this.CopyArea(WSPlayApplet.this.gridLoadImage, 0, 0, WSPlayApplet.this.gridLoadImage.getWidth(null), WSPlayApplet.this.gridLoadImage.getHeight(null), WSPlayApplet.this.gridImage, 0, 0);
            WSPlayApplet.this.actualListImage = WSPlayApplet.this.createImage(WSPlayApplet.this.WordsWidth, WSPlayApplet.this.wordsImage.getHeight(null));
            WSPlayApplet.this.totalWordListHeight = WSPlayApplet.this.wordsImage.getHeight(null);
            WSPlayApplet.this.CopyArea(WSPlayApplet.this.wordsImage, 0, 0, WSPlayApplet.this.WordsWidth, WSPlayApplet.this.totalWordListHeight, WSPlayApplet.this.actualListImage, 0, 0);
            if (WSPlayApplet.this.sliderImage != null) {
                WSPlayApplet.this.WordsSliderTop = WSPlayApplet.this.GridTopPos + WSPlayApplet.this.ScrollButtonHeight;
                WSPlayApplet.this.NoteSliderTop = WSPlayApplet.this.GridTopPos + WSPlayApplet.this.ScrollButtonHeight;
                WSPlayApplet.this.SliderLeft = WSPlayApplet.this.appletWidth - WSPlayApplet.this.RightWidth;
                WSPlayApplet.this.SliderWidth = WSPlayApplet.this.sliderImage.getWidth(null);
                WSPlayApplet.this.SliderHeight = WSPlayApplet.this.sliderImage.getHeight(null);
                WSPlayApplet.this.SliderBarTop = WSPlayApplet.this.GridTopPos + WSPlayApplet.this.ScrollButtonHeight;
                WSPlayApplet.this.SliderBarBottom = WSPlayApplet.this.GridTopPos + WSPlayApplet.this.GridHeight - WSPlayApplet.this.ScrollButtonHeight - WSPlayApplet.this.SliderHeight;
                WSPlayApplet.this.SliderBarLength = WSPlayApplet.this.SliderBarBottom - WSPlayApplet.this.SliderBarTop;
                WSPlayApplet.this.WordsScrollLength = WSPlayApplet.this.totalWordListHeight - WSPlayApplet.this.GridHeight;
            }
            if (WSPlayApplet.this.pointerImage != null) {
                WSPlayApplet.this.pointerHeight = WSPlayApplet.this.pointerImage.getHeight(null);
                WSPlayApplet.this.pointerWidth = WSPlayApplet.this.pointerImage.getWidth(null);
            }
            else {
                WSPlayApplet.this.pointerWidth = 16;
                WSPlayApplet.this.pointerHeight = 16;
                WSPlayApplet.this.pointerImage = WSPlayApplet.this.createImage(WSPlayApplet.this.pointerWidth, WSPlayApplet.this.pointerHeight);
                WSPlayApplet.this.pointerImage.getGraphics().clearRect(0, 0, WSPlayApplet.this.pointerWidth - 1, WSPlayApplet.this.pointerHeight - 1);
                WSPlayApplet.this.pointerImage.getGraphics().setColor(Color.white);
                WSPlayApplet.this.pointerImage.getGraphics().drawOval(0, 0, WSPlayApplet.this.pointerWidth - 1, WSPlayApplet.this.pointerHeight - 1);
                WSPlayApplet.this.pointerImage.getGraphics().setColor(Color.black);
                WSPlayApplet.this.pointerImage.getGraphics().fillOval(2, 2, WSPlayApplet.this.pointerWidth - 4, WSPlayApplet.this.pointerHeight - 4);
            }
            WSPlayApplet.this.pointerSaveImage = WSPlayApplet.this.createImage(WSPlayApplet.this.pointerWidth, WSPlayApplet.this.pointerHeight);
            if (WSPlayApplet.this.ShowNote & !WSPlayApplet.this.puzzleLoaded) {
                WSPlayApplet.this.playMode = 2;
                WSPlayApplet.this.ShowMessage(WSPlayApplet.this.TextPTS, false);
                WSPlayApplet.this.PlayFirstSound(WSPlayApplet.this.FirstNoteButtonSound, WSPlayApplet.this.NoteButtonSound, false, false, true);
            }
            else {
                WSPlayApplet.this.playMode = 1;
                WSPlayApplet.this.ShowMessage(WSPlayApplet.this.TextPZI, false);
                WSPlayApplet.this.PlayPLAYSound();
            }
            WSPlayApplet.this.DoDebug("*** Allow Playing Puzzle ***", false);
            WSPlayApplet.this.SetMessageFinalPosition();
            WSPlayApplet.this.puzzleLoaded = true;
            WSPlayApplet.this.repaint();
            if (WSPlayApplet.this.appletWasStopped) {
                return;
            }
            WSPlayApplet.this.DoDebug(" Initializing Timer", false);
            if (WSPlayApplet.this.TimerTop > 0) {
                WSPlayApplet.this.timerLabel.setSize(WSPlayApplet.this.timerWidth, WSPlayApplet.this.textHeight + 4);
                WSPlayApplet.this.timerLabel.setForeground(WSPlayApplet.this.TextColor);
                WSPlayApplet.this.timerLabel.setBackground(WSPlayApplet.this.BackColor);
                switch (WSPlayApplet.this.TimerJust) {
                    case 0: {
                        WSPlayApplet.this.timerLabel.setLocation(WSPlayApplet.this.GridLeftPos, WSPlayApplet.this.TimerTop);
                        WSPlayApplet.this.timerLabel.setAlignment(0);
                        break;
                    }
                    case 1: {
                        WSPlayApplet.this.timerLabel.setLocation(WSPlayApplet.this.GridLeftPos + (WSPlayApplet.this.GridWidth + WSPlayApplet.this.WordsWidth - WSPlayApplet.this.timerWidth) / 2, WSPlayApplet.this.TimerTop);
                        WSPlayApplet.this.timerLabel.setAlignment(1);
                        break;
                    }
                    default: {
                        WSPlayApplet.this.timerLabel.setLocation(WSPlayApplet.this.appletWidth - WSPlayApplet.this.RightWidth - WSPlayApplet.this.timerWidth, WSPlayApplet.this.TimerTop);
                        WSPlayApplet.this.timerLabel.setAlignment(2);
                        break;
                    }
                }
                WSPlayApplet.this.timerLabel.setText("");
                WSPlayApplet.this.add(WSPlayApplet.this.timerLabel);
            }
            if (WSPlayApplet.this.UseBaseSounds) {
                WSPlayApplet.this.DoDebug(" Initialize Base Sounds", false);
                WSPlayApplet.this.ButtonSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.ButtonSoundFile);
                WSPlayApplet.this.CircleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.CircleSoundFile);
                WSPlayApplet.this.GoodSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.GoodSoundFile);
                WSPlayApplet.this.BadSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.BadSoundFile);
                WSPlayApplet.this.SliderSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SliderSoundFile);
            }
            if (WSPlayApplet.this.SayWordClick | WSPlayApplet.this.SayWordCircle) {
                WSPlayApplet.this.DoDebug(" Initialize Word Sounds", false);
                for (int k = 1; k <= WSPlayApplet.this.LineCount; ++k) {
                    WSPlayApplet.this.PlayWordSound(k, true);
                }
            }
            if (WSPlayApplet.this.UseBaseSounds) {
                WSPlayApplet.this.WinSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.WinSoundFile);
            }
            if (WSPlayApplet.this.appletWasStopped) {
                return;
            }
            if (WSPlayApplet.this.UseTutorialSounds) {
                WSPlayApplet.this.DoDebug(" Initialize Tutorial Sounds", false);
                WSPlayApplet.this.FirstClickWordSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstClickWordSoundFile);
                WSPlayApplet.this.FirstClickSentenceSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstClickSentenceSoundFile);
                WSPlayApplet.this.ClickWordSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.ClickWordSoundFile);
                WSPlayApplet.this.ClickSentenceSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.ClickSentenceSoundFile);
                WSPlayApplet.this.FirstSolveHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstSolveHiddenSoundFile);
                WSPlayApplet.this.SolveHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SolveHiddenSoundFile);
                WSPlayApplet.this.FirstSolveUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstSolveUnscrambleSoundFile);
                WSPlayApplet.this.SolveUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SolveUnscrambleSoundFile);
                WSPlayApplet.this.FirstSolveCircleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstSolveCircleSoundFile);
                WSPlayApplet.this.SolveCircleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SolveCircleSoundFile);
                WSPlayApplet.this.SolveAllSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SolveAllSoundFile);
                WSPlayApplet.this.SolveErrorSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SolveErrorSoundFile);
                WSPlayApplet.this.SolveAllNoteSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.SolveAllNoteSoundFile);
                WSPlayApplet.this.FirstStartUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstStartUnscrambleSoundFile);
                WSPlayApplet.this.FirstFinishUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstFinishUnscrambleSoundFile);
                WSPlayApplet.this.FirstStartHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstStartHiddenSoundFile);
                WSPlayApplet.this.FirstFinishHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FirstFinishHiddenSoundFile);
                WSPlayApplet.this.StartUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.StartUnscrambleSoundFile);
                WSPlayApplet.this.FinishUnscrambleSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FinishUnscrambleSoundFile);
                WSPlayApplet.this.StartHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.StartHiddenSoundFile);
                WSPlayApplet.this.FinishHiddenSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.FinishHiddenSoundFile);
                WSPlayApplet.this.StartPhraseSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.StartPhraseSoundFile);
                WSPlayApplet.this.AboutButtonSound = WSPlayApplet.this.InitializeSound(WSPlayApplet.this.AboutButtonSoundFile);
                if (WSPlayApplet.this.appletWasStopped) {
                    return;
                }
            }
            if (!WSPlayApplet.this.aboutImageLoaded && !WSPlayApplet.this.AboutFile.equals("")) {
                WSPlayApplet.this.DoDebug(" Loading About Box", true);
                try {
                    WSPlayApplet.this.aboutImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.SharedGraphicsLocation, WSPlayApplet.this.AboutFile));
                    WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.aboutImage, 3);
                    WSPlayApplet.this.tracker.waitForID(3);
                }
                catch (InterruptedException ex4) {
                    WSPlayApplet.this.DebugString = WSPlayApplet.this.ErrorABT;
                }
                if (WSPlayApplet.this.aboutImage != null) {
                    WSPlayApplet.this.AboutLeft = WSPlayApplet.this.appletWidth - WSPlayApplet.this.GridLeftPos - WSPlayApplet.this.RightWidth - WSPlayApplet.this.aboutImage.getWidth(null);
                    if (WSPlayApplet.this.AboutLeft < 0) {
                        WSPlayApplet.this.AboutLeft = WSPlayApplet.this.appletWidth - WSPlayApplet.this.aboutImage.getWidth(null);
                        if (WSPlayApplet.this.AboutLeft < 0) {
                            WSPlayApplet.this.AboutLeft = 0;
                        }
                        else {
                            WSPlayApplet.this.AboutLeft /= 2;
                        }
                    }
                    else {
                        WSPlayApplet.this.AboutLeft = WSPlayApplet.this.GridLeftPos + WSPlayApplet.this.AboutLeft / 2;
                    }
                    WSPlayApplet.this.AboutTop = WSPlayApplet.this.GridHeight - WSPlayApplet.this.aboutImage.getHeight(null);
                    if (WSPlayApplet.this.AboutTop < 0) {
                        WSPlayApplet.this.AboutTop = WSPlayApplet.this.appletHeight - WSPlayApplet.this.aboutImage.getHeight(null);
                        if (WSPlayApplet.this.AboutTop < 0) {
                            WSPlayApplet.this.AboutTop = 0;
                        }
                        else {
                            WSPlayApplet.this.AboutTop /= 2;
                        }
                    }
                    else {
                        WSPlayApplet.this.AboutTop = WSPlayApplet.this.GridTopPos + WSPlayApplet.this.AboutTop / 2;
                    }
                    final int n2 = WSPlayApplet.this.aboutImage.getWidth(null) * WSPlayApplet.this.aboutImage.getHeight(null);
                    final int[] array = new int[n2];
                    double n3 = 0.0;
                    final PixelGrabber pixelGrabber = new PixelGrabber(WSPlayApplet.this.aboutImage, 0, 0, WSPlayApplet.this.aboutImage.getWidth(null), WSPlayApplet.this.aboutImage.getHeight(null), array, 0, WSPlayApplet.this.aboutImage.getWidth(null));
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (InterruptedException ex5) {}
                    for (int l = 0; l < n2; ++l) {
                        n3 += array[l];
                    }
                    double n4 = Math.abs(Math.round(n3 / n2));
                    if (n4 < 255) {
                        n4 = WSPlayApplet.this.AboutCode + 1;
                    }
                    if (n4 == 1726677 || n4 == WSPlayApplet.this.AboutCode) {
                        WSPlayApplet.this.aboutImageLoaded = true;
                        if (n4 != WSPlayApplet.this.AboutCode) {
                            WSPlayApplet.this.AboutAddress = WSPlayApplet.this.WSPAddress;
                        }
                        else {
                            WSPlayApplet.this.SpecialAboutBox = true;
                        }
                    }
                }
                if (WSPlayApplet.this.playMode == 4) {
                    WSPlayApplet.this.repaint();
                }
            }
            if (!WSPlayApplet.this.aboutImageLoaded) {
                WSPlayApplet.this.AboutAddress = WSPlayApplet.this.WSPAddress;
            }
            if (WSPlayApplet.this.appletWasStopped) {
                return;
            }
            try {
                WSPlayApplet.this.aboutUrl = new URL(WSPlayApplet.this.AboutAddress);
            }
            catch (MalformedURLException ex6) {}
            if (WSPlayApplet.this.winImage == null) {
                WSPlayApplet.this.DoDebug(" Loading Win Image", true);
                try {
                    WSPlayApplet.this.winImage = WSPlayApplet.this.getImage(WSPlayApplet.this.getActualLocation(WSPlayApplet.this.SharedGraphicsLocation, WSPlayApplet.this.WinFile));
                    WSPlayApplet.this.tracker.addImage(WSPlayApplet.this.winImage, 4);
                    WSPlayApplet.this.tracker.waitForID(4);
                }
                catch (InterruptedException ex7) {
                    WSPlayApplet.this.DebugString = WSPlayApplet.this.ErrorWIN;
                }
                if (WSPlayApplet.this.winImage != null) {
                    WSPlayApplet.this.winLeft = WSPlayApplet.this.appletWidth - WSPlayApplet.this.GridLeftPos - WSPlayApplet.this.RightWidth - WSPlayApplet.this.winImage.getWidth(null);
                    if (WSPlayApplet.this.winLeft < 0) {
                        WSPlayApplet.this.winLeft = WSPlayApplet.this.appletWidth - WSPlayApplet.this.winImage.getWidth(null);
                        if (WSPlayApplet.this.winLeft < 0) {
                            WSPlayApplet.this.winLeft = 0;
                        }
                        else {
                            WSPlayApplet.this.winLeft /= 2;
                        }
                    }
                    else {
                        WSPlayApplet.this.winLeft = WSPlayApplet.this.GridLeftPos + WSPlayApplet.this.winLeft / 2;
                    }
                    WSPlayApplet.this.winTop = WSPlayApplet.this.GridHeight - WSPlayApplet.this.winImage.getHeight(null);
                    if (WSPlayApplet.this.winTop < 0) {
                        WSPlayApplet.this.winTop = WSPlayApplet.this.appletHeight - WSPlayApplet.this.winImage.getHeight(null);
                        if (WSPlayApplet.this.winTop < 0) {
                            WSPlayApplet.this.winTop = 0;
                        }
                        else {
                            WSPlayApplet.this.winTop /= 2;
                        }
                    }
                    else {
                        WSPlayApplet.this.winTop = WSPlayApplet.this.GridTopPos + WSPlayApplet.this.winTop / 2;
                    }
                }
            }
            if (WSPlayApplet.this.WinAddress.length() != 0) {
                WSPlayApplet.this.doJump = true;
                if (WSPlayApplet.this.WinAddress.indexOf("/") == -1) {
                    try {
                        WSPlayApplet.this.jumpUrl = new URL(WSPlayApplet.this.getDocumentBase(), WSPlayApplet.this.WinAddress);
                    }
                    catch (MalformedURLException ex8) {
                        WSPlayApplet.this.doJump = false;
                    }
                }
                else {
                    try {
                        WSPlayApplet.this.jumpUrl = new URL(WSPlayApplet.this.WinAddress);
                    }
                    catch (MalformedURLException ex9) {
                        WSPlayApplet.this.doJump = false;
                    }
                }
            }
            WSPlayApplet.this.DoDebug("*** Puzzle Fully Loaded ***", true);
        }
    }
    
    class TimerThread extends Thread
    {
        String timerString;
        String scStr;
        String mnStr;
        String messageToShow;
        int hrVal;
        int mnVal;
        int scVal;
        int timeCounter;
        int pauseTime;
        boolean BlinkNow;
        
        public TimerThread() {
            this.pauseTime = 3000;
            this.messageToShow = "";
            this.start();
        }
        
        public void resetCounter(final int timeCounter) {
            this.timeCounter = timeCounter;
        }
        
        public int getCounter() {
            return this.timeCounter;
        }
        
        public void run() {
            WSPlayApplet.this.DoDebug("Timer Thread START", false);
            while (!WSPlayApplet.this.appletWasStopped & !WSPlayApplet.this.puzzleSolved) {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
                if (WSPlayApplet.this.playMode == 1 && !WSPlayApplet.this.puzzleSolved) {
                    ++this.timeCounter;
                }
                this.mnVal = this.timeCounter / 60;
                this.scVal = this.timeCounter % 60;
                if (this.mnVal > 0) {
                    this.hrVal = this.mnVal / 60;
                    this.mnVal %= 60;
                }
                if (this.scVal > 9) {
                    this.scStr = "".concat(String.valueOf(String.valueOf(this.scVal)));
                }
                else {
                    this.scStr = "0".concat(String.valueOf(String.valueOf(this.scVal)));
                }
                if (this.hrVal > 0) {
                    if (this.mnVal > 9) {
                        this.mnStr = "".concat(String.valueOf(String.valueOf(this.mnVal)));
                    }
                    else {
                        this.mnStr = "0".concat(String.valueOf(String.valueOf(this.mnVal)));
                    }
                    this.timerString = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.hrVal))).append(":").append(this.mnStr).append(":").append(this.scStr)));
                }
                else {
                    this.timerString = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.mnVal))).append(":").append(this.scStr)));
                }
                if (WSPlayApplet.this.puzzleLoaded) {
                    if (WSPlayApplet.this.playMode == 1) {
                        if (WSPlayApplet.this.puzzleSolved) {
                            continue;
                        }
                        WSPlayApplet.this.timerLabel.setText(String.valueOf(String.valueOf(WSPlayApplet.this.TextTIM)).concat(String.valueOf(String.valueOf(this.timerString))));
                    }
                    else {
                        if (WSPlayApplet.this.timerLabel.getText().equals(WSPlayApplet.this.TextPAU)) {
                            continue;
                        }
                        WSPlayApplet.this.timerLabel.setText(WSPlayApplet.this.TextPAU);
                    }
                }
            }
            WSPlayApplet.this.DoDebug("Timer Thread END", false);
        }
    }
}
