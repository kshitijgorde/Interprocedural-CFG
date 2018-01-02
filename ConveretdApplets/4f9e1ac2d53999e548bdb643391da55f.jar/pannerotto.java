import java.util.Random;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class pannerotto extends Applet implements Runnable
{
    String SetLangPanneRotto;
    boolean ENGLISH;
    boolean JAPANESE;
    boolean DEBUGFLAG;
    boolean BETAFLAG;
    boolean DEBUGSHORTSTAGE;
    boolean DEBUGSOLVEALWAYS;
    boolean DEBUGEXTRAPWD;
    String PlayLevelID;
    String GameTitle;
    String VersionNum;
    Thread thread;
    MediaTracker mt;
    Graphics myG;
    Graphics offGrfx;
    boolean gameQuit;
    boolean gameOver;
    boolean Solving;
    Font f;
    Image CellImage;
    Image LeftBoardImage;
    Image VerLeftLineImage;
    Image VerRightLineImage;
    Image HorTopLineImage;
    Image HorBottomLineImage;
    Image BigTitleImage;
    Image SmallTitleImage;
    Image StageAllClearImage;
    Image EnterPasswordImage;
    Image NormalImage;
    Image NormalPlusImage;
    Image ExtraImage;
    Image ExtraPlusImage;
    Image EndlessImage;
    Image EndlessPlusImage;
    Image HowToPlayImage;
    Image AboutImage;
    Image LegalImage;
    Image QuitButtonImage;
    Image ResetButtonImage;
    Image SolveButtonImage;
    Image UndoButtonImage;
    Image BackButtonImage;
    Image PlayButtonImage;
    Image passwordpanelpicture;
    Image offImage;
    int winsizex;
    int winsizey;
    int innercellsize;
    int paintedcellsize;
    int centerx;
    int centery;
    int playfieldcenterx;
    int playfieldcentery;
    int heightIncrement;
    int playfieldcelllength;
    int playfieldxbegin;
    int playfieldybegin;
    int playfieldxend;
    int playfieldyend;
    int spacebetweenlineNball;
    int activecellmarkx;
    int activecellmarky;
    int cellpixellength;
    int cellpixellengthhalf;
    int leftinfoareapixellength;
    int centerleftinfoareapixellength;
    int NumOfSolutionSteps;
    int Counter;
    int CurrentNumOfSteps;
    int TempArrowImageLeftRightValueX;
    int ArrowImageLeftRightValueX;
    int ArrowImageLeftRightValueY;
    int ArrowImageWidth;
    boolean DisplayArrowImage;
    int positive1;
    int negative1;
    int direction;
    int directiondot1;
    int RotatingStepCurrent;
    int delayvalue;
    boolean RotatingRight;
    boolean RotatingLeft;
    boolean RightMouseClickedinPlayfield;
    boolean LeftMouseClickedinPlayfield;
    boolean MouseMovementLocked;
    boolean tempboolean;
    int tempint;
    int tempint2;
    boolean InTitleScreen;
    boolean InGamePlayMode;
    int BannerWidth;
    int BannerHeight;
    int ButtonWidth;
    int ButtonHeight;
    int HowToPlayImageXStart;
    int HowToPlayImageYStart;
    int NormalImageXStart;
    int NormalImageYStart;
    int NormalPlusImageXStart;
    int NormalPlusImageYStart;
    int ExtraImageXStart;
    int ExtraImageYStart;
    int ExtraPlusImageXStart;
    int ExtraPlusImageYStart;
    int EndlessImageXStart;
    int EndlessImageYStart;
    int EndlessPlusImageXStart;
    int EndlessPlusImageYStart;
    int EnterPasswordImageXStart;
    int EnterPasswordImageYStart;
    int AboutImageXStart;
    int AboutImageYStart;
    int LegalImageXStart;
    int LegalImageYStart;
    int BigTitleImageXStart;
    int BigTitleImageYStart;
    int ExplainImageXStart;
    int ExplainImageYStart;
    int StageAllClearImageXStart;
    int StageAllClearImageYStart;
    int QuitButtonImageXStart;
    int QuitButtonImageYStart;
    int ResetButtonImageXStart;
    int ResetButtonImageYStart;
    int SolveButtonImageXStart;
    int SolveButtonImageYStart;
    int UndoButtonImageXStart;
    int UndoButtonImageYStart;
    int PlayButtonImageXStart;
    int PlayButtonImageYStart;
    int BackButtonImageXStart;
    int BackButtonImageYStart;
    int MAXplayfieldcelllength;
    int MAXStageNumNormal;
    int MAXStageNumNormalPlus;
    int NumOfSpecialStages;
    int MAXSolutionSteps;
    boolean InPasswordScreen;
    boolean InAboutScreen;
    boolean InLegalScreen;
    boolean InNormalExplainScreen;
    boolean InNormalPlusExplainScreen;
    boolean InExtraExplainScreen;
    boolean InExtraPlusExplainScreen;
    boolean InEndlessExplainScreen;
    boolean InEndlessPlusExplainScreen;
    boolean InHowToPlayScreen;
    boolean NormalPlay;
    boolean NormalPlusPlay;
    boolean ExtraPlay;
    boolean ExtraPlusPlay;
    boolean EndlessPlay;
    boolean EndlessPlusPlay;
    boolean StageClear;
    boolean InStageClearScreen;
    boolean QuitPressed;
    boolean ResetPressed;
    boolean SolvePressed;
    boolean UndoPressed;
    boolean CurrentlyInHowToPlayImageBanner;
    boolean CurrentlyInNormalImageBanner;
    boolean CurrentlyInNormalPlusImageBanner;
    boolean CurrentlyInExtraImageBanner;
    boolean CurrentlyInExtraPlusImageBanner;
    boolean CurrentlyInEndlessImageBanner;
    boolean CurrentlyInEndlessPlusImageBanner;
    boolean CurrentlyInEnterPasswordImageBanner;
    boolean CurrentlyInAboutImageBanner;
    boolean CurrentlyInLegalImageBanner;
    boolean CurrentlyInPlayButtonImage;
    boolean CurrentlyInBackButtonImage;
    boolean CurrentlyInQuitButtonImage;
    boolean CurrentlyInResetButtonImage;
    boolean CurrentlyInSolveButtonImage;
    boolean CurrentlyInUndoButtonImage;
    int LeftNRightAdd;
    int PlusOrMinusOne;
    int Hi1;
    int Hi2;
    int Hi3;
    int Hi4;
    int Hi5;
    int Hi6;
    int Hi7;
    int Hi8;
    int Hi9;
    int Hi10;
    int Hi11;
    int LowAll;
    int DGSX;
    int DGSY1;
    int DGSY2;
    int DGSY3;
    int DGSY4;
    int DGSY5;
    int DGSY6;
    int DGSY7;
    int DGSY8;
    int DGSY9;
    int DGSY10;
    int DGSY11;
    int LDGSY1;
    int LDGSY2;
    int LDGSY3;
    int LDGSY4;
    int LDGSY5;
    int LDGSY6;
    int LDGSY7;
    int LDGSY8;
    int LDGSY9;
    int LDGSY10;
    int LDGSY11;
    int DGSSpace;
    int DGSInc1;
    int DGSInc2;
    int DGSInc3;
    int DGSInc4;
    int DGSInc5;
    int DGSInc6;
    int DGSInc7;
    int DGSInc8;
    int DGSInc9;
    int DGSInc10;
    int DGSInc11;
    boolean[][] ballexist;
    int[][] ballexistcolor;
    boolean[][] ballexisttarget;
    int[][] ballexisttargetcolor;
    boolean[][] storeballexist;
    int[][] storeballexistcolor;
    boolean[][] storeballexisttarget;
    int[][] storeballexisttargetcolor;
    int[][] cellxstart;
    int[][] cellystart;
    int[][] ballcolor;
    String[] NormalPasswordArray;
    String[] NormalPlusPasswordArray;
    int[] SolutionDataX;
    int[] TempSolutionDataX;
    int[] SolutionDataY;
    int[] TempSolutionDataY;
    char[] SolutionDataDir;
    char[] TempSolutionDataDir;
    int i;
    int i2;
    int i3;
    int i4;
    int tempplayfieldxbegin;
    int tempplayfieldybegin;
    int CurrentStage;
    int TotalStages;
    boolean AllCleared;
    boolean NormalPlayAllCleared;
    boolean NormalPlusPlayAllCleared;
    boolean ExtraPlayAllCleared;
    boolean ExtraPlusPlayAllCleared;
    boolean EndlessPlayAllCleared;
    boolean EndlessPlusPlayAllCleared;
    boolean InNormalPlayAllClearedScreen;
    boolean InNormalPlusPlayAllClearedScreen;
    boolean InExtraPlayAllClearedScreen;
    boolean InExtraPlusPlayAllClearedScreen;
    boolean InEndlessPlayAllClearedScreen;
    boolean InEndlessPlusPlayAllClearedScreen;
    boolean ShowPassword;
    boolean InShowPasswordScreen;
    String NextStagePassword;
    boolean LastStage;
    int ppanelx;
    int ppanely;
    String PasswordEnteredString;
    String TempString;
    int CursorPWD;
    int StatusBarLength;
    int NumOfImages;
    int StatusBarOnePortion;
    boolean[] PicLoad;
    int[] PicSize;
    int CurrentStatusBarLength;
    int TotalNumOfColoredCells;
    int RandomColor;
    double m1;
    int RandomXTarget;
    int RandomYTarget;
    int RandomXSource;
    int RandomYSource;
    boolean GetOutOfLoopForRandomCell;
    boolean SilentRotation;
    int RandomTotalRotate;
    int RandomNumRotate;
    boolean ContainCell;
    boolean SolvingFinished;
    int cellmarkR;
    int cellmarkG;
    int cellmarkB;
    int cellmarkRGBIncrement;
    int ArrowGlowColorR;
    int ArrowGlowColorG;
    int ArrowGlowColorB;
    int ArrowGlowRGBIncrement;
    int FirstArrowP1x;
    int FirstArrowP1y;
    int FirstArrowP2x;
    int FirstArrowP2y;
    int FirstArrowP3x;
    int FirstArrowP3y;
    int SecondArrowP1x;
    int SecondArrowP1y;
    int SecondArrowP2x;
    int SecondArrowP2y;
    int SecondArrowP3x;
    int SecondArrowP3y;
    int ThirdArrowP1x;
    int ThirdArrowP1y;
    int ThirdArrowP2x;
    int ThirdArrowP2y;
    int ThirdArrowP3x;
    int ThirdArrowP3y;
    int FourthArrowP1x;
    int FourthArrowP1y;
    int FourthArrowP2x;
    int FourthArrowP2y;
    int FourthArrowP3x;
    int FourthArrowP3y;
    int FifthArrowP1x;
    int FifthArrowP1y;
    int FifthArrowP2x;
    int FifthArrowP2y;
    int FifthArrowP3x;
    int FifthArrowP3y;
    int SixthArrowP1x;
    int SixthArrowP1y;
    int SixthArrowP2x;
    int SixthArrowP2y;
    int SixthArrowP3x;
    int SixthArrowP3y;
    int FirstArrowP4x;
    int FirstArrowP4y;
    int FirstArrowP5x;
    int FirstArrowP5y;
    int FirstArrowP6x;
    int FirstArrowP6y;
    int SecondArrowP4x;
    int SecondArrowP4y;
    int SecondArrowP5x;
    int SecondArrowP5y;
    int SecondArrowP6x;
    int SecondArrowP6y;
    int ThirdArrowP4x;
    int ThirdArrowP4y;
    int ThirdArrowP5x;
    int ThirdArrowP5y;
    int ThirdArrowP6x;
    int ThirdArrowP6y;
    int FourthArrowP4x;
    int FourthArrowP4y;
    int FourthArrowP5x;
    int FourthArrowP5y;
    int FourthArrowP6x;
    int FourthArrowP6y;
    int FifthArrowP4x;
    int FifthArrowP4y;
    int FifthArrowP5x;
    int FifthArrowP5y;
    int FifthArrowP6x;
    int FifthArrowP6y;
    int SixthArrowP4x;
    int SixthArrowP4y;
    int SixthArrowP5x;
    int SixthArrowP5y;
    int SixthArrowP6x;
    int SixthArrowP6y;
    boolean JustOutOfAutoSolve;
    int PlayLevel;
    int SetSelected;
    int TotalSet;
    boolean PlayButtonPressed;
    boolean LoadingImages;
    boolean MouseClickedOutofAutoSolve;
    int ExtraSteps;
    int MAXStepHistory;
    int[] HistoryActiveCellMarkX;
    int[] HistoryActiveCellMarkY;
    char[] HistoryLeftRight;
    boolean InDisplayStepReachedMaxScreen;
    String MessageInPlayField1;
    String MessageInPlayField2;
    String MessageInPlayField3;
    String QuitHelpMsg1;
    String QuitHelpMsg2;
    String QuitHelpMsg3;
    String ResetHelpMsg1;
    String ResetHelpMsg2;
    String ResetHelpMsg3;
    String SolveHelpMsg1;
    String SolveHelpMsg2;
    String SolveHelpMsg3;
    String UndoHelpMsg1;
    String UndoHelpMsg2;
    String UndoHelpMsg3;
    String ReachedMaxStepsHelpMsg1;
    String ReachedMaxStepsHelpMsg2;
    String ReachedMaxStepsHelpMsg3;
    String SolveWasPressedHelpMsg1;
    String SolveWasPressedHelpMsg2;
    String SolveWasPressedHelpMsg3;
    String ClickMouseHelpMsg1;
    String ClickMouseHelpMsg2;
    String ClickMouseHelpMsg3;
    String PwdForStageFront;
    String PwdForStageEnd;
    String NormalAllClearString;
    String TryThisPwdString;
    String NormalPlusAllClearString;
    String ExtraAllClearString;
    String ExtraPlusAllClearString;
    String StageString;
    String OfString;
    String MaxStepsString;
    String StepsTakenString;
    String LoadingImagesString;
    boolean SolvePressedOnce;
    boolean WaitingForMouseClick;
    boolean AllInOneColor;
    String SpecialPassword;
    int tempX;
    int tempY;
    String tempString;
    int DescX;
    int DescriptionStartY;
    int ClearedDescriptionStartY;
    String HowToDescLine1;
    String HowToDescLine2;
    String HowToDescLine3;
    String HowToDescLine4;
    String HowToDescLine5;
    String HowToDescLine6;
    String HowToDescLine7;
    String HowToDescLine8;
    String HowToDescLine9;
    String HowToDescLine10;
    String HowToDescLine11;
    String HowToDescLine12;
    String HowToDescLine13;
    String NormalDescLine1;
    String NormalDescLine2;
    String NormalDescLine3;
    String NormalDescLine4;
    String NormalDescLine5;
    String NormalDescLine6;
    String NormalDescLine7;
    String NormalDescLine8;
    String NormalDescLine9;
    String NormalDescLine10;
    String NormalDescLine11;
    String NormalDescLine12;
    String NormalDescLine13;
    String NormalPlusDescLine1;
    String NormalPlusDescLine2;
    String NormalPlusDescLine3;
    String NormalPlusDescLine4;
    String NormalPlusDescLine5;
    String NormalPlusDescLine6;
    String NormalPlusDescLine7;
    String NormalPlusDescLine8;
    String NormalPlusDescLine9;
    String NormalPlusDescLine10;
    String NormalPlusDescLine11;
    String NormalPlusDescLine12;
    String NormalPlusDescLine13;
    String ExtraDescLine1;
    String ExtraDescLine2;
    String ExtraDescLine3;
    String ExtraDescLine4;
    String ExtraDescLine5;
    String ExtraDescLine6;
    String ExtraDescLine7;
    String ExtraDescLine8;
    String ExtraDescLine9;
    String ExtraDescLine10;
    String ExtraDescLine11;
    String ExtraDescLine12;
    String ExtraDescLine13;
    String ExtraPlusDescLine1;
    String ExtraPlusDescLine2;
    String ExtraPlusDescLine3;
    String ExtraPlusDescLine4;
    String ExtraPlusDescLine5;
    String ExtraPlusDescLine6;
    String ExtraPlusDescLine7;
    String ExtraPlusDescLine8;
    String ExtraPlusDescLine9;
    String ExtraPlusDescLine10;
    String ExtraPlusDescLine11;
    String ExtraPlusDescLine12;
    String ExtraPlusDescLine13;
    String EndlessDescLine1;
    String EndlessDescLine2;
    String EndlessDescLine3;
    String EndlessDescLine4;
    String EndlessDescLine5;
    String EndlessDescLine6;
    String EndlessDescLine7;
    String EndlessDescLine8;
    String EndlessDescLine9;
    String EndlessDescLine10;
    String EndlessDescLine11;
    String EndlessDescLine12;
    String EndlessDescLine13;
    String EndlessPlusDescLine1;
    String EndlessPlusDescLine2;
    String EndlessPlusDescLine3;
    String EndlessPlusDescLine4;
    String EndlessPlusDescLine5;
    String EndlessPlusDescLine6;
    String EndlessPlusDescLine7;
    String EndlessPlusDescLine8;
    String EndlessPlusDescLine9;
    String EndlessPlusDescLine10;
    String EndlessPlusDescLine11;
    String EndlessPlusDescLine12;
    String EndlessPlusDescLine13;
    String AboutDescLine1;
    String AboutDescLine2;
    String AboutDescLine3;
    String AboutDescLine4;
    String AboutDescLine5;
    String AboutDescLine6;
    String AboutDescLine7;
    String AboutDescLine8;
    String AboutDescLine9;
    String AboutDescLine10;
    String AboutDescLine11;
    String AboutDescLine12;
    String AboutDescLine13;
    String LegalDescLine1;
    String LegalDescLine2;
    String LegalDescLine3;
    String LegalDescLine4;
    String LegalDescLine5;
    String LegalDescLine6;
    String LegalDescLine7;
    String LegalDescLine8;
    String LegalDescLine9;
    String LegalDescLine10;
    String LegalDescLine11;
    String LegalDescLine12;
    String LegalDescLine13;
    String ClearedDescLine1;
    String ClearedDescLine2;
    String ClearedDescLine3;
    String ClearedDescLine4;
    String ClearedDescLine5;
    String ClearedDescLine6;
    String ClearedDescLine7;
    String ClearedDescLine8;
    String ClearedDescLine9;
    String ClearedDescLine10;
    String ClearedDescLine11;
    String ClearedDescLine12;
    String ClearedDescLine13;
    String ClearedDescLine14;
    String ClearedDescLine15;
    String ClickOnLetters;
    String UndoReachedMaxLine1;
    String UndoReachedMaxLine2;
    String UndoReachedMaxLine3;
    int StageX;
    String StageActualDisplayString;
    int MaxStepsX;
    int StepsTakenX;
    String MaxStepsActualDisplayString;
    String StepsTakenActualDisplayString;
    int ClickLettersX;
    
    public pannerotto() {
        this.ENGLISH = true;
        this.JAPANESE = false;
        this.DEBUGFLAG = false;
        this.BETAFLAG = false;
        this.DEBUGSHORTSTAGE = false;
        this.DEBUGSOLVEALWAYS = false;
        this.DEBUGEXTRAPWD = false;
        this.PlayLevelID = "none";
        this.GameTitle = "PanneRotto!";
        this.VersionNum = "v1.01";
        this.gameQuit = false;
        this.gameOver = false;
        this.Solving = false;
        this.activecellmarkx = 2;
        this.activecellmarky = 1;
        this.CurrentNumOfSteps = 0;
        this.DisplayArrowImage = false;
        this.RotatingRight = false;
        this.RotatingLeft = false;
        this.RightMouseClickedinPlayfield = false;
        this.LeftMouseClickedinPlayfield = false;
        this.MouseMovementLocked = true;
        this.tempboolean = false;
        this.InTitleScreen = false;
        this.InGamePlayMode = false;
        this.InPasswordScreen = false;
        this.InAboutScreen = false;
        this.InLegalScreen = false;
        this.InNormalExplainScreen = false;
        this.InNormalPlusExplainScreen = false;
        this.InExtraExplainScreen = false;
        this.InExtraPlusExplainScreen = false;
        this.InEndlessExplainScreen = false;
        this.InEndlessPlusExplainScreen = false;
        this.InHowToPlayScreen = false;
        this.NormalPlay = false;
        this.NormalPlusPlay = false;
        this.ExtraPlay = false;
        this.ExtraPlusPlay = false;
        this.EndlessPlay = false;
        this.EndlessPlusPlay = false;
        this.StageClear = false;
        this.InStageClearScreen = false;
        this.QuitPressed = false;
        this.ResetPressed = false;
        this.SolvePressed = false;
        this.UndoPressed = false;
        this.CurrentlyInHowToPlayImageBanner = false;
        this.CurrentlyInNormalImageBanner = false;
        this.CurrentlyInNormalPlusImageBanner = false;
        this.CurrentlyInExtraImageBanner = false;
        this.CurrentlyInExtraPlusImageBanner = false;
        this.CurrentlyInEndlessImageBanner = false;
        this.CurrentlyInEndlessPlusImageBanner = false;
        this.CurrentlyInEnterPasswordImageBanner = false;
        this.CurrentlyInAboutImageBanner = false;
        this.CurrentlyInLegalImageBanner = false;
        this.CurrentlyInPlayButtonImage = false;
        this.CurrentlyInBackButtonImage = false;
        this.CurrentlyInQuitButtonImage = false;
        this.CurrentlyInResetButtonImage = false;
        this.CurrentlyInSolveButtonImage = false;
        this.CurrentlyInUndoButtonImage = false;
        this.DGSSpace = 12;
        this.AllCleared = false;
        this.NormalPlayAllCleared = false;
        this.NormalPlusPlayAllCleared = false;
        this.ExtraPlayAllCleared = false;
        this.ExtraPlusPlayAllCleared = false;
        this.EndlessPlayAllCleared = false;
        this.EndlessPlusPlayAllCleared = false;
        this.InNormalPlayAllClearedScreen = false;
        this.InNormalPlusPlayAllClearedScreen = false;
        this.InExtraPlayAllClearedScreen = false;
        this.InExtraPlusPlayAllClearedScreen = false;
        this.InEndlessPlayAllClearedScreen = false;
        this.InEndlessPlusPlayAllClearedScreen = false;
        this.ShowPassword = false;
        this.InShowPasswordScreen = false;
        this.LastStage = false;
        this.ppanelx = 133;
        this.ppanely = 93;
        this.StatusBarLength = 300;
        this.NumOfImages = 26;
        this.StatusBarOnePortion = 3;
        this.CurrentStatusBarLength = 1;
        this.GetOutOfLoopForRandomCell = false;
        this.SilentRotation = false;
        this.ContainCell = false;
        this.SolvingFinished = false;
        this.cellmarkR = 160;
        this.cellmarkG = 160;
        this.cellmarkB = 0;
        this.cellmarkRGBIncrement = 2;
        this.ArrowGlowColorR = 160;
        this.ArrowGlowColorG = 160;
        this.ArrowGlowColorB = 0;
        this.ArrowGlowRGBIncrement = 2;
        this.JustOutOfAutoSolve = false;
        this.PlayLevel = 1;
        this.SetSelected = 1;
        this.TotalSet = 10;
        this.PlayButtonPressed = false;
        this.LoadingImages = true;
        this.MouseClickedOutofAutoSolve = false;
        this.ExtraSteps = 0;
        this.MAXStepHistory = 255;
        this.InDisplayStepReachedMaxScreen = false;
        this.MessageInPlayField1 = " ";
        this.MessageInPlayField2 = " ";
        this.MessageInPlayField3 = " ";
        this.SolvePressedOnce = false;
        this.WaitingForMouseClick = false;
        this.AllInOneColor = false;
        this.StageActualDisplayString = "";
        this.MaxStepsActualDisplayString = "";
        this.StepsTakenActualDisplayString = "";
    }
    
    public void init() {
        this.SetLangPanneRotto = "";
        this.SetLangPanneRotto = this.getParameter("SetLangPanneRotto");
        if (this.SetLangPanneRotto.equals("ENGLISH")) {
            this.ENGLISH = true;
            this.JAPANESE = false;
        }
        else if (this.SetLangPanneRotto.equals("JAPANESE")) {
            this.ENGLISH = false;
            this.JAPANESE = true;
        }
        else {
            this.ENGLISH = true;
            this.JAPANESE = false;
        }
        if (this.ENGLISH) {
            this.GameTitle = "PanneRotto!";
        }
        else if (this.JAPANESE) {
            this.GameTitle = "\u3071\u306d\u308d\u3063\u3068\uff01";
        }
        this.winsizex = 400;
        this.winsizey = 300;
        this.centerx = this.winsizex / 2;
        this.centery = this.winsizey / 2;
        this.MAXStageNumNormal = 30;
        this.MAXStageNumNormalPlus = 40;
        this.NumOfSpecialStages = 1;
        this.CurrentNumOfSteps = 0;
        this.BannerWidth = 100;
        this.BannerHeight = 20;
        this.ButtonWidth = 50;
        this.ButtonHeight = 20;
        this.f = new Font("Dialog", 0, 12);
        (this.myG = this.getGraphics()).setFont(this.f);
        this.heightIncrement = this.myG.getFontMetrics().getDescent() + this.myG.getFontMetrics().getAscent();
        this.DisplayArrowImage = false;
        if (this.ENGLISH) {
            this.PwdForStageFront = "Password for stage ";
            this.PwdForStageEnd = " is: ";
            this.NormalAllClearString = "Normal Mode All Clear!";
            this.TryThisPwdString = "try this password: ";
            this.NormalPlusAllClearString = "Normal Plus Mode All Clear!";
            this.ExtraAllClearString = "Extra Mode All Clear!";
            this.ExtraPlusAllClearString = "Extra Plus Mode All Clear!";
            this.StageString = "Stage: ";
            this.OfString = " of ";
            this.MaxStepsString = "Max. steps: ";
            this.StepsTakenString = "Steps taken: ";
            this.LoadingImagesString = "Loading images.  Please wait....";
            this.QuitHelpMsg1 = "";
            this.QuitHelpMsg2 = "Quits stage and returns to title screen.";
            this.QuitHelpMsg3 = "";
            this.ResetHelpMsg1 = "";
            this.ResetHelpMsg2 = "Starts this stage from the beginning.";
            this.ResetHelpMsg3 = "";
            this.SolveHelpMsg1 = "Solves this stage automatically. Once";
            this.SolveHelpMsg2 = "this is selected, you can no longer";
            this.SolveHelpMsg3 = "proceed to the next stage.";
            this.UndoHelpMsg1 = "";
            this.UndoHelpMsg2 = "Undoes the last step you made.";
            this.UndoHelpMsg3 = "";
            this.ReachedMaxStepsHelpMsg1 = "Reached Max Steps.";
            this.ReachedMaxStepsHelpMsg2 = "Click mouse to start this";
            this.ReachedMaxStepsHelpMsg3 = "stage from the beginning.";
            this.SolveWasPressedHelpMsg1 = "[Solve] was selected at least once";
            this.SolveWasPressedHelpMsg2 = "during play.  Click mouse to start";
            this.SolveWasPressedHelpMsg3 = "this stage from the beginning.";
            this.ClickMouseHelpMsg1 = "";
            this.ClickMouseHelpMsg2 = "Click mouse to start over.";
            this.ClickMouseHelpMsg3 = "";
            this.UndoReachedMaxLine1 = "'Steps taken' has reached " + this.MAXStepHistory + ".";
            this.UndoReachedMaxLine2 = "Undo feature is now disabled.";
            this.UndoReachedMaxLine3 = "Press [Reset] to start over.";
            this.DescriptionStartY = 70;
            this.ClearedDescriptionStartY = 45;
            this.HowToDescLine1 = "";
            this.HowToDescLine2 = "Objective:";
            this.HowToDescLine3 = "To clear all stages by positioning all of the solid";
            this.HowToDescLine4 = "squares inside a corresponding colored outlined square";
            this.HowToDescLine5 = "by rotating the outer cells within the movable yellow panel.";
            this.HowToDescLine6 = "";
            this.HowToDescLine7 = "Control:";
            this.HowToDescLine8 = "Left mouse = counter clockwise rotation";
            this.HowToDescLine9 = "Right mouse = clockwise rotation";
            this.HowToDescLine10 = "If you have a one-button mouse, you can [control]";
            this.HowToDescLine11 = "click the mouse to get clockwise rotation.";
            this.HowToDescLine12 = "";
            this.HowToDescLine13 = "";
            this.NormalDescLine1 = "";
            this.NormalDescLine2 = "Normal mode consists of " + this.MAXStageNumNormal + " stages.";
            this.NormalDescLine3 = "A password is given at the end of every stage that";
            this.NormalDescLine4 = "is cleared and enables you to start from any stage";
            this.NormalDescLine5 = "between 1 and the next to be cleared.  To do this,";
            this.NormalDescLine6 = "first get a password by clearing at least one stage";
            this.NormalDescLine7 = "and write it down. The next time you play, in the";
            this.NormalDescLine8 = "title screen, select [Password], enter a password,";
            this.NormalDescLine9 = "and select [Play].";
            this.NormalDescLine10 = "";
            this.NormalDescLine11 = "";
            this.NormalDescLine12 = "";
            this.NormalDescLine13 = "";
            this.NormalPlusDescLine1 = "";
            this.NormalPlusDescLine2 = "Normal Plus mode consists of " + this.MAXStageNumNormalPlus + " stages.";
            this.NormalPlusDescLine3 = "It is the same as Normal mode except the number";
            this.NormalPlusDescLine4 = "of steps you are allowed to take is limited.";
            this.NormalPlusDescLine5 = "If you reach the maximum number of steps without";
            this.NormalPlusDescLine6 = "clearing the stage, clicking the mouse will reset";
            this.NormalPlusDescLine7 = "the stage to its original layout.";
            this.NormalPlusDescLine8 = "";
            this.NormalPlusDescLine9 = "";
            this.NormalPlusDescLine10 = "";
            this.NormalPlusDescLine11 = "";
            this.NormalPlusDescLine12 = "";
            this.NormalPlusDescLine13 = "";
            this.EndlessDescLine1 = "";
            this.EndlessDescLine2 = "Endless mode consists of an infinite number";
            this.EndlessDescLine3 = "of stages.  Stages are generated by computer.";
            this.EndlessDescLine4 = "Every time you play, you get different stages";
            this.EndlessDescLine5 = "so you can play repeatedly.  Passwords are not";
            this.EndlessDescLine6 = "provided.  Other than that, it is the same as";
            this.EndlessDescLine7 = "Normal mode.";
            this.EndlessDescLine8 = "";
            this.EndlessDescLine9 = "";
            this.EndlessDescLine10 = "";
            this.EndlessDescLine11 = "";
            this.EndlessDescLine12 = "";
            this.EndlessDescLine13 = "";
            this.EndlessPlusDescLine1 = "";
            this.EndlessPlusDescLine2 = "Endless Plus mode consists of an infinite";
            this.EndlessPlusDescLine3 = "number of stages.  Stages are generated by";
            this.EndlessPlusDescLine4 = "computer.  Every time you play, you get";
            this.EndlessPlusDescLine5 = "different stages so you can play repeatedly.";
            this.EndlessPlusDescLine6 = "Passwords are not privided.  Other than that,";
            this.EndlessPlusDescLine7 = "it is the same as Normal Plus mode.";
            this.EndlessPlusDescLine8 = "";
            this.EndlessPlusDescLine9 = "";
            this.EndlessPlusDescLine10 = "";
            this.EndlessPlusDescLine11 = "";
            this.EndlessPlusDescLine12 = "";
            this.EndlessPlusDescLine13 = "";
            this.ExtraDescLine1 = "";
            this.ExtraDescLine2 = "Extra mode consists of 16 stages.";
            this.ExtraDescLine3 = "Stages are generated by computer.  This means";
            this.ExtraDescLine4 = "that every time you play, you get different stages.";
            this.ExtraDescLine5 = "Because you get different stages every time,";
            this.ExtraDescLine6 = "you can play repeatedly even if you have cleared";
            this.ExtraDescLine7 = "all stages.  Passwords are not privided.  Other";
            this.ExtraDescLine8 = "than that, it is the same as Normal mode.";
            this.ExtraDescLine9 = "";
            this.ExtraDescLine10 = "";
            this.ExtraDescLine11 = "";
            this.ExtraDescLine12 = "";
            this.ExtraDescLine13 = "";
            this.ExtraPlusDescLine1 = "";
            this.ExtraPlusDescLine2 = "Extra Plus mode consists of 16 stages.";
            this.ExtraPlusDescLine3 = "Stages are generated by computer.  This means";
            this.ExtraPlusDescLine4 = "that every time you play, you get different stages.";
            this.ExtraPlusDescLine5 = "Because you get different stages every time,";
            this.ExtraPlusDescLine6 = "you can play repeatedly even if you have cleared";
            this.ExtraPlusDescLine7 = "all stages.  Passwords are not privided.";
            this.ExtraPlusDescLine8 = "Other than that, it is same as Normal Plus mode.";
            this.ExtraPlusDescLine9 = "";
            this.ExtraPlusDescLine10 = "";
            this.ExtraPlusDescLine11 = "";
            this.ExtraPlusDescLine12 = "";
            this.ExtraPlusDescLine13 = "";
            this.AboutDescLine1 = this.GameTitle + " " + this.VersionNum;
            this.AboutDescLine2 = "Copyright (c)2001 Masayoshi Ueda. All rights reserved.";
            this.AboutDescLine3 = "This is freeware.  Feel free to put this applet on your";
            this.AboutDescLine4 = "web page.  Download more applets from my web page:";
            this.AboutDescLine5 = "http://home.earthlink.net/~jsurfer";
            this.AboutDescLine6 = "My email address 1: jsurfer@earthlink.net";
            this.AboutDescLine7 = "My email address 2: cheezsj@yahoo.com";
            this.AboutDescLine8 = "Although this is freeware, if you enjoy this game";
            this.AboutDescLine9 = "I want to ask you to help others by making donations";
            this.AboutDescLine10 = "to either American Red Cross(http://www.crossnet.org/)";
            this.AboutDescLine11 = "or UNICEF(http://www.unicef.org/).";
            this.AboutDescLine12 = "Special thanks to MasaoA, ChiharuB, YujiH, KenjiI,";
            this.AboutDescLine13 = "KeikoN, AkikoU, and many thanks to Lora Lynne.";
            this.LegalDescLine1 = "";
            this.LegalDescLine2 = this.GameTitle + " " + this.VersionNum + " is provided as is.  There are no";
            this.LegalDescLine3 = "warranties, no support arrangements, no claim of";
            this.LegalDescLine4 = "merchantability or fitness for specific purpose";
            this.LegalDescLine5 = "and no acceptance of liability for loss or damage";
            this.LegalDescLine6 = "caused by use of this program.  Any distribution";
            this.LegalDescLine7 = "of " + this.GameTitle + " " + this.VersionNum + " must include all of the files";
            this.LegalDescLine8 = "in their original condition, without removal, addition";
            this.LegalDescLine9 = "or modification.  " + this.GameTitle + " " + this.VersionNum + " may not be sold,";
            this.LegalDescLine10 = "resold, included as part of a commercial package";
            this.LegalDescLine11 = "or used for any other commercial purpose without";
            this.LegalDescLine12 = "the prior written consent of the author.";
            this.LegalDescLine13 = "";
            this.ClearedDescLine1 = "thank you for playing " + this.GameTitle;
            this.ClearedDescLine2 = "";
            this.ClearedDescLine3 = "program written by masayoshi ueda -cheez-";
            this.ClearedDescLine4 = "program started on september 13, 2000";
            this.ClearedDescLine5 = "first release 11/19/2001";
            this.ClearedDescLine6 = "build environment";
            this.ClearedDescLine7 = " os : MS Windows98-Japanese version";
            this.ClearedDescLine8 = " cpu : PentiumIII-500MHz";
            this.ClearedDescLine9 = " memory&hd : 128MB, 20GB";
            this.ClearedDescLine10 = " compiler : JDK1.3.1_01";
            this.ClearedDescLine11 = " graphics app: Adobe Photoshop 5.5J";
            this.ClearedDescLine12 = "";
            this.ClearedDescLine13 = "masayoshi ueda -cheez-";
            this.ClearedDescLine14 = "san jose, california, usa";
            this.ClearedDescLine15 = "fall 2001...";
            this.ClickOnLetters = "Click on letters to enter a password.";
        }
        else if (this.JAPANESE) {
            this.PwdForStageFront = "\u30b9\u30c6\u30fc\u30b8 ";
            this.PwdForStageEnd = " \u306e\u30d1\u30b9\u30ef\u30fc\u30c9\uff1a";
            this.NormalAllClearString = "\u30ce\u30fc\u30de\u30eb \u30e2\u30fc\u30c9 \u30af\u30ea\u30a2\uff01";
            this.TryThisPwdString = "\u30b7\u30fc\u30af\u30ec\u30c3\u30c8\u30fb\u30d1\u30b9\u30ef\u30fc\u30c9: ";
            this.NormalPlusAllClearString = "\u30ce\u30fc\u30de\u30eb\u30fb\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9 \u30af\u30ea\u30a2\uff01";
            this.ExtraAllClearString = "\u304a\u307e\u3051 \u30e2\u30fc\u30c9 \u30af\u30ea\u30a2\uff01";
            this.ExtraPlusAllClearString = "\u304a\u307e\u3051\u30fb\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9 \u30af\u30ea\u30a2\uff01";
            this.StageString = "\u30b9\u30c6\u30fc\u30b8\uff1a ";
            this.OfString = " of ";
            this.MaxStepsString = "\u30b9\u30c6\u30c3\u30d7\u5236\u9650\u6570: ";
            this.StepsTakenString = "\u73fe\u5728\u306e\u30b9\u30c6\u30c3\u30d7\u6570: ";
            this.LoadingImagesString = "\u30a4\u30e1\u30fc\u30b8\u30d5\u30a1\u30a4\u30eb\u3092\u8aad\u307f\u8fbc\u3093\u3067\u3044\u307e\u3059....";
            this.QuitHelpMsg1 = "";
            this.QuitHelpMsg2 = "\u4e2d\u65ad\u3057\u3066\u30bf\u30a4\u30c8\u30eb\u30fb\u30b9\u30af\u30ea\u30fc\u30f3\u306b\u623b\u308a\u307e\u3059\u3002";
            this.QuitHelpMsg3 = "";
            this.ResetHelpMsg1 = "";
            this.ResetHelpMsg2 = "\u73fe\u5728\u306e\u30b9\u30c6\u30fc\u30b8\u3092\u6700\u521d\u304b\u3089\u3084\u308a\u76f4\u3057\u307e\u3059\u3002";
            this.ResetHelpMsg3 = "";
            this.SolveHelpMsg1 = "\u30b3\u30f3\u30d4\u30e5\u30fc\u30bf\u304c\u89e3\u7b54\u3092\u898b\u305b\u3066\u304f\u308c\u307e\u3059\u3002";
            this.SolveHelpMsg2 = "\u4e00\u5ea6\u3067\u3082\u3053\u306e\u30dc\u30bf\u30f3\u304c\u62bc\u3055\u308c\u308b\u3068";
            this.SolveHelpMsg3 = "\u6b21\u306e\u30b9\u30c6\u30fc\u30b8\u306b\u306f\u9032\u3081\u307e\u305b\u3093\u3002";
            this.UndoHelpMsg1 = "";
            this.UndoHelpMsg2 = "\u4e00\u3064\u524d\u306e\u30b9\u30c6\u30c3\u30d7\u306b\u623b\u308a\u307e\u3059\u3002";
            this.UndoHelpMsg3 = "";
            this.ReachedMaxStepsHelpMsg1 = "\u30b9\u30c6\u30c3\u30d7\u5236\u9650\u6570\u3092\u8d85\u3048\u307e\u3057\u305f\u3002";
            this.ReachedMaxStepsHelpMsg2 = "\u30de\u30a6\u30b9\u3092\u30af\u30ea\u30c3\u30af\u3092\u3057\u3066\u3053\u306e\u30b9\u30c6\u30fc\u30b8";
            this.ReachedMaxStepsHelpMsg3 = "\u3092\u3084\u308a\u76f4\u3057\u3066\u304f\u3060\u3055\u3044\u3002";
            this.SolveWasPressedHelpMsg1 = "[\u89e3\u7b54] \u30dc\u30bf\u30f3\u304c\u3053\u306e\u30b9\u30c6\u30fc\u30b8\u30d7\u30ec\u30a4\u4e2d\u306b";
            this.SolveWasPressedHelpMsg2 = "\u62bc\u3055\u308c\u307e\u3057\u305f\u3002\u30de\u30a6\u30b9\u3092\u30af\u30ea\u30c3\u30af\u3092\u3057\u3066";
            this.SolveWasPressedHelpMsg3 = "\u3053\u306e\u30b9\u30c6\u30fc\u30b8\u3092\u3084\u308a\u76f4\u3057\u3066\u304f\u3060\u3055\u3044\u3002";
            this.ClickMouseHelpMsg1 = "";
            this.ClickMouseHelpMsg2 = "\u30de\u30a6\u30b9\u3092\u30af\u30ea\u30c3\u30af\u3092\u3057\u3066\u3053\u306e\u30b9\u30c6\u30fc\u30b8\u3092";
            this.ClickMouseHelpMsg3 = "\u3084\u308a\u76f4\u3057\u3066\u304f\u3060\u3055\u3044\u3002";
            this.UndoReachedMaxLine1 = "'\u73fe\u5728\u306e\u30b9\u30c6\u30c3\u30d7\u6570' \u304c\u3000" + (this.MAXStepHistory - 1) + "";
            this.UndoReachedMaxLine2 = "\u3092\u8d85\u3048\u307e\u3057\u305f\u3002\u30a2\u30f3\u30c9\u30a5\u6a5f\u80fd\u306f\u505c\u6b62\u3057\u307e\u3059\u3002";
            this.UndoReachedMaxLine3 = "";
            this.DescriptionStartY = 70;
            this.ClearedDescriptionStartY = 45;
            this.HowToDescLine1 = "\u8272\u3067\u5857\u308a\u3064\u3076\u3055\u308c\u305f\u30bb\u30eb\u3092\u540c\u3058\u8272\u306e\u67a0\u304c\u3042\u308b\u30bb\u30eb\u306b";
            this.HowToDescLine2 = "\u79fb\u52d5\u3057\u307e\u3059\u3002\u3059\u3079\u3066\u306e\u30bb\u30eb\u304c\u3053\u306e\u8272\u67a0\u306e\u30bb\u30eb\u306b\u79fb\u52d5";
            this.HowToDescLine3 = "\u3059\u308c\u3070\u30b9\u30c6\u30fc\u30b8\u30af\u30ea\u30a2\u3067\u3059\u3002\u79fb\u52d5\u3067\u304d\u308b\u306e\u306f\u3001\u9078\u629e";
            this.HowToDescLine4 = "\u3055\u308c\u3066\u3044\u308b\u30bb\u30eb\u306e\u5916\u5074\u306b\u3042\u308b\uff18\u3064\u306e\u30bb\u30eb\u3060\u3051\u3067\u3059\u3002";
            this.HowToDescLine5 = "\u79fb\u52d5\u306f\u30de\u30a6\u30b9\u30af\u30ea\u30c3\u30af\u3067\u884c\u3044\u307e\u3059\u3002";
            this.HowToDescLine6 = "";
            this.HowToDescLine7 = "\u64cd\u4f5c\u65b9\u6cd5:";
            this.HowToDescLine8 = "\u5de6\u30de\u30a6\u30b9\u30dc\u30bf\u30f3 = \u6642\u8a08\u3068\u53cd\u5bfe\u65b9\u5411\u306b\u56de\u8ee2";
            this.HowToDescLine9 = "\u53f3\u30de\u30a6\u30b9\u30dc\u30bf\u30f3 = \u6642\u8a08\u56de\u308a\u306b\u56de\u8ee2";
            this.HowToDescLine10 = "\u30ef\u30f3\u30dc\u30bf\u30f3\u30de\u30a6\u30b9\u3092\u3054\u4f7f\u7528\u306e\u5834\u5408\u3001[\u30b3\u30f3\u30c8\u30ed\u30fc\u30eb]";
            this.HowToDescLine11 = "\u30ad\u30fc\u3092\u62bc\u3057\u306a\u304c\u3089\u30dc\u30bf\u30f3\u3092\u30af\u30ea\u30c3\u30af\u3059\u308b\u3068\u6642\u8a08\u56de\u308a\u306b";
            this.HowToDescLine12 = "\u56de\u8ee2\u3057\u307e\u3059\u3002";
            this.HowToDescLine13 = "";
            this.NormalDescLine1 = "";
            this.NormalDescLine2 = "\u30ce\u30fc\u30de\u30eb \u30e2\u30fc\u30c9\u306f\u5168" + this.MAXStageNumNormal + "\u30b9\u30c6\u30fc\u30b8\u3067\u6210\u308a\u7acb\u3063\u3066\u3044";
            this.NormalDescLine3 = "\u307e\u3059\u3002\u30b9\u30c6\u30fc\u30b8\u3092\u30af\u30ea\u30a2\u3059\u308b\u3054\u3068\u306b\u30d1\u30b9\u30ef\u30fc\u30c9\u304c";
            this.NormalDescLine4 = "\u3082\u3089\u3048\u307e\u3059\u3002\u3053\u306e\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u4f7f\u3046\u3053\u3068\u306b\u3088\u3063\u3066";
            this.NormalDescLine5 = "\u6b21\u306b\u30d7\u30ec\u30a4\u3059\u308b\u969b\u3001\u4e00\u5ea6\u30af\u30ea\u30a2\u3057\u305f\u30b9\u30c6\u30fc\u30b8\u306f";
            this.NormalDescLine6 = "\u3068\u3070\u3059\u3053\u3068\u304c\u3067\u304d\u307e\u3059\u3002\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u5165\u529b\u3059\u308b\u306b\u306f\u3001";
            this.NormalDescLine7 = "\u30bf\u30a4\u30c8\u30eb\u30fb\u30b9\u30af\u30ea\u30fc\u30f3\u306e [\u30d1\u30b9\u30ef\u30fc\u30c9] \u3092\u9078\u3093\u3067\u304f\u3060\u3055\u3044\u3002";
            this.NormalDescLine8 = "";
            this.NormalDescLine9 = "";
            this.NormalDescLine10 = "";
            this.NormalDescLine11 = "";
            this.NormalDescLine12 = "";
            this.NormalDescLine13 = "";
            this.NormalPlusDescLine1 = "";
            this.NormalPlusDescLine2 = "\u30ce\u30fc\u30de\u30eb\u30fb\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9\u306f\u5168" + this.MAXStageNumNormalPlus + "\u30b9\u30c6\u30fc\u30b8\u3067";
            this.NormalPlusDescLine3 = "\u6210\u308a\u7acb\u3063\u3066\u3044\u307e\u3059\u3002\u904a\u3073\u65b9\u306f\u30ce\u30fc\u30de\u30eb \u30e2\u30fc\u30c9";
            this.NormalPlusDescLine4 = "\u3068\u540c\u3058\u3067\u3059\u304c\u3001\u30b9\u30c6\u30c3\u30d7\u6570\u304c\u5236\u9650\u3055\u308c\u3066\u3044\u307e\u3059\u3002";
            this.NormalPlusDescLine5 = "\u30b9\u30c6\u30c3\u30d7\u6570\u304c\u3053\u306e\u5236\u9650\u6570\u3092\u8d85\u3048\u308b\u305d\u306e\u6642\u70b9\u3067";
            this.NormalPlusDescLine6 = "\u30b9\u30c6\u30fc\u30b8\u306e\u6700\u521d\u304b\u3089\u3084\u308a\u76f4\u3057\u3068\u306a\u308a\u307e\u3059\u3002";
            this.NormalPlusDescLine7 = "";
            this.NormalPlusDescLine8 = "";
            this.NormalPlusDescLine9 = "";
            this.NormalPlusDescLine10 = "";
            this.NormalPlusDescLine11 = "";
            this.NormalPlusDescLine12 = "";
            this.NormalPlusDescLine13 = "";
            this.EndlessDescLine1 = "";
            this.EndlessDescLine2 = "\u30a8\u30f3\u30c9\u30ec\u30b9 \u30e2\u30fc\u30c9\u306b\u306f\u30b9\u30c6\u30fc\u30b8\u304c\u9650\u308a\u306a\u304f\u5b58\u5728";
            this.EndlessDescLine3 = "\u3057\u307e\u3059\u3002\u5404\u30b9\u30c6\u30fc\u30b8\u306f\u30b3\u30f3\u30d4\u30e5\u30fc\u30bf\u306b\u3088\u3063\u3066";
            this.EndlessDescLine4 = "\u30e9\u30f3\u30c0\u30e0\u306b\u4f5c\u6210\u3055\u308c\u308b\u306e\u3067\u3001\u4f55\u56de\u3067\u3082\u904a\u3076\u3053\u3068\u304c";
            this.EndlessDescLine5 = "\u3067\u304d\u307e\u3059\u3002\u30d1\u30b9\u30ef\u30fc\u30c9\u306f\u3042\u308a\u307e\u305b\u3093\u3002\u30eb\u30fc\u30eb\u306f";
            this.EndlessDescLine6 = "\u30ce\u30fc\u30de\u30eb \u30e2\u30fc\u30c9\u3068\u540c\u3058\u3067\u3059\u3002";
            this.EndlessDescLine7 = "";
            this.EndlessDescLine8 = "";
            this.EndlessDescLine9 = "";
            this.EndlessDescLine10 = "";
            this.EndlessDescLine11 = "";
            this.EndlessDescLine12 = "";
            this.EndlessDescLine13 = "";
            this.EndlessPlusDescLine1 = "";
            this.EndlessPlusDescLine2 = "\u30a8\u30f3\u30c9\u30ec\u30b9\u30fb\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9\u306b\u306f\u30b9\u30c6\u30fc\u30b8\u304c\u9650\u308a";
            this.EndlessPlusDescLine3 = "\u306a\u304f\u5b58\u5728\u3057\u307e\u3059\u3002\u5404\u30b9\u30c6\u30fc\u30b8\u306f\u30b3\u30f3\u30d4\u30e5\u30fc\u30bf";
            this.EndlessPlusDescLine4 = "\u306b\u3088\u3063\u3066\u30e9\u30f3\u30c0\u30e0\u306b\u4f5c\u6210\u3055\u308c\u308b\u306e\u3067\u3001\u4f55\u56de\u3067\u3082";
            this.EndlessPlusDescLine5 = "\u904a\u3076\u3053\u3068\u304c\u3067\u304d\u307e\u3059\u3002\u30d1\u30b9\u30ef\u30fc\u30c9\u306f\u3042\u308a\u307e\u305b\u3093\u3002";
            this.EndlessPlusDescLine6 = "\u30eb\u30fc\u30eb\u306f\u30ce\u30fc\u30de\u30eb\u30fb\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9\u3068\u540c\u3058\u3067\u3059\u3002";
            this.EndlessPlusDescLine7 = "";
            this.EndlessPlusDescLine8 = "";
            this.EndlessPlusDescLine9 = "";
            this.EndlessPlusDescLine10 = "";
            this.EndlessPlusDescLine11 = "";
            this.EndlessPlusDescLine12 = "";
            this.EndlessPlusDescLine13 = "";
            this.ExtraDescLine1 = "";
            this.ExtraDescLine2 = "\u304a\u307e\u3051\u30e2\u30fc\u30c9\u306f\u516816\u30b9\u30c6\u30fc\u30b8\u3067\u6210\u308a\u7acb\u3063\u3066";
            this.ExtraDescLine3 = "\u3044\u307e\u3059\u3002\u30b9\u30c6\u30fc\u30b8\u306f\u30b3\u30f3\u30d4\u30e5\u30fc\u30bf\u306b\u3088\u3063\u3066";
            this.ExtraDescLine4 = "\u30e9\u30f3\u30c0\u30e0\u306b\u4f5c\u6210\u3055\u308c\u308b\u306e\u3067\u3001\u516816\u30b9\u30c6\u30fc\u30b8";
            this.ExtraDescLine5 = "\u3092\u30af\u30ea\u30a2\u3057\u3066\u3082\u3001\u4f55\u5ea6\u3067\u3082\u904a\u3076\u3053\u3068\u304c\u3067\u304d";
            this.ExtraDescLine6 = "\u307e\u3059\u3002\u30d1\u30b9\u30ef\u30fc\u30c9\u306f\u3042\u308a\u307e\u305b\u3093\u3002\u30eb\u30fc\u30eb\u306f";
            this.ExtraDescLine7 = "\u30ce\u30fc\u30de\u30eb \u30e2\u30fc\u30c9\u3068\u540c\u3058\u3067\u3059\u3002";
            this.ExtraDescLine8 = "";
            this.ExtraDescLine9 = "";
            this.ExtraDescLine10 = "";
            this.ExtraDescLine11 = "";
            this.ExtraDescLine12 = "";
            this.ExtraDescLine13 = "";
            this.ExtraPlusDescLine1 = "";
            this.ExtraPlusDescLine2 = "\u304a\u307e\u3051\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9\u306f\u516816\u30b9\u30c6\u30fc\u30b8\u3067\u6210\u308a\u7acb\u3063\u3066";
            this.ExtraPlusDescLine3 = "\u3044\u307e\u3059\u3002\u304a\u307e\u3051\u30e2\u30fc\u30c9\u3068\u540c\u69d8\u3001\u30b9\u30c6\u30fc\u30b8\u306f";
            this.ExtraPlusDescLine4 = "\u30b3\u30f3\u30d4\u30e5\u30fc\u30bf\u306b\u3088\u3063\u3066\u30e9\u30f3\u30c0\u30e0\u306b\u4f5c\u6210\u3055\u308c\u308b\u306e\u3067";
            this.ExtraPlusDescLine5 = "\u516816\u30b9\u30c6\u30fc\u30b8\u3092\u30af\u30ea\u30a2\u3057\u3066\u3082\u4f55\u5ea6\u3067\u3082\u904a\u3076\u3053\u3068\u304c";
            this.ExtraPlusDescLine6 = "\u3067\u304d\u307e\u3059\u3002\u30d1\u30b9\u30ef\u30fc\u30c9\u306f\u3042\u308a\u307e\u305b\u3093\u3002\u30eb\u30fc\u30eb\u306f";
            this.ExtraPlusDescLine7 = "\u30ce\u30fc\u30de\u30eb\u30fb\u30d7\u30e9\u30b9 \u30e2\u30fc\u30c9\u3068\u540c\u3058\u3067\u3059\u3002";
            this.ExtraPlusDescLine8 = "";
            this.ExtraPlusDescLine9 = "";
            this.ExtraPlusDescLine10 = "";
            this.ExtraPlusDescLine11 = "";
            this.ExtraPlusDescLine12 = "";
            this.ExtraPlusDescLine13 = "";
            this.AboutDescLine1 = this.GameTitle + " " + this.VersionNum;
            this.AboutDescLine2 = "Copyright (c)2001 \u4e0a\u7530\u6b63\u7fa9 All rights reserved.";
            this.AboutDescLine3 = this.GameTitle + " " + this.VersionNum + " \u306f\u30d5\u30ea\u30fc\u30a6\u30a7\u30a2\u3067\u3059\u3002\u518d\u914d\u5e03\u3001\u8ee2\u8f09\u306f";
            this.AboutDescLine4 = "\u3054\u81ea\u7531\u306b\u884c\u3063\u3066\u304f\u3060\u3055\u3044\u3002\u4e0b\u8a18\u306eURL\u304b\u3089\u30c0\u30a6\u30f3\u30ed\u30fc\u30c9\u3067\u304d\u307e\u3059\u3002";
            this.AboutDescLine5 = "http://home.earthlink.net/~jsurfer";
            this.AboutDescLine6 = "\u96fb\u5b50\u30e1\u30fc\u30eb\u30a2\u30c9\u30ec\u30b9 1: jsurfer@earthlink.net";
            this.AboutDescLine7 = "\u96fb\u5b50\u30e1\u30fc\u30eb\u30a2\u30c9\u30ec\u30b9 2: cheezsj@hotmail.com";
            this.AboutDescLine8 = "\u3053\u306e\u30b2\u30fc\u30e0\u306f\u30d5\u30ea\u30fc\u30a6\u30a7\u30a2\u3067\u3059\u304c\u3001\u3082\u3057\u3053\u306e\u30b2\u30fc\u30e0\u304c";
            this.AboutDescLine9 = "\u6c17\u306b\u5165\u3063\u305f\u5834\u5408\u3001\u65e5\u672c\u8d64\u5341\u5b57(http://www.jrc.or.jp/)";
            this.AboutDescLine10 = "\u307e\u305f\u306f\u65e5\u672c\u30e6\u30cb\u30bb\u30d5(http://www.unicef.or.jp/)\u306b";
            this.AboutDescLine11 = "\u52df\u91d1\u3057\u3066\u3082\u3089\u3048\u308b\u3068\u5b09\u3057\u3044\u3067\u3059\u3002";
            this.AboutDescLine12 = "Special thanks to MasaoA, ChiharuB, YujiH, KenjiI,";
            this.AboutDescLine13 = "KeikoN, AkikoU, and many thanks to Lora Lynne.";
            this.LegalDescLine1 = "";
            this.LegalDescLine2 = "\u672c\u30bd\u30d5\u30c8\u30a6\u30a7\u30a2\u3092\u4f7f\u7528\u3057\u305f\u7d50\u679c\u3044\u304b\u306a\u308b\u640d\u5bb3\u306b\u5bfe\u3057\u3066\u3082";
            this.LegalDescLine3 = "\u4f5c\u8005\u306f\u8cac\u4efb\u3092\u8ca0\u3048\u307e\u305b\u3093\u3002\u5185\u5bb9\u3092\u5909\u66f4\u3057\u306a\u3044\u9650\u308a\u518d\u914d\u5e03\u3001";
            this.LegalDescLine4 = "\u8ee2\u8f09\u306f\u81ea\u7531\u3068\u3057\u307e\u3059\u3002\u9023\u7d61\u306f\u4e0d\u8981\u3067\u3059\u3002\u305f\u3060\u3057\u3001\u96d1\u8a8c\u7b49";
            this.LegalDescLine5 = "\u7d19\u9762\u63b2\u8f09\u3059\u308b\u5834\u5408\u3084CD-ROM, DVD\u7b49\u30e1\u30c7\u30a3\u30a2\u306b\u53ce\u9332\u3057\u914d\u5e03";
            this.LegalDescLine6 = "\u3059\u308b\u5834\u5408\u306b\u306f\u63b2\u8f09/\u53ce\u9332\u306f\u9023\u7d61\u306a\u3057\u306b\u3054\u81ea\u7531\u306b\u884c\u3063\u3066\u3044\u305f";
            this.LegalDescLine7 = "\u3060\u3044\u3066\u7d50\u69cb\u3067\u3059\u304c\u3001\u4e8b\u524d\u307e\u305f\u306f\u4e8b\u5f8c\u306b\u901a\u77e5\u3092\u9802\u304d\u307e\u3059\u3088\u3046";
            this.LegalDescLine8 = "\u304a\u9858\u3044\u3044\u305f\u3057\u307e\u3059\u3002\u914d\u5e03\u306b\u95a2\u3057\u3066\u30e1\u30c7\u30a3\u30a2\u4ee3\u7b49\u306e\u5b9f\u8cbb\u4ee5\u5916";
            this.LegalDescLine9 = "\u306e\u91d1\u92ad\u306e\u6388\u53d7\u306f\u7981\u6b62\u3057\u307e\u3059\u3002";
            this.LegalDescLine10 = "";
            this.LegalDescLine11 = "";
            this.LegalDescLine12 = "";
            this.LegalDescLine13 = "";
            this.ClearedDescLine1 = "Thank you for playing " + this.GameTitle;
            this.ClearedDescLine2 = "";
            this.ClearedDescLine3 = "program written by masayoshi ueda -cheez-";
            this.ClearedDescLine4 = "program started on september 13, 2000";
            this.ClearedDescLine5 = "first release 11/19/2001";
            this.ClearedDescLine6 = "build environment";
            this.ClearedDescLine7 = " os : MS Windows98-Japanese version";
            this.ClearedDescLine8 = " cpu : PentiumIII-500MHz";
            this.ClearedDescLine9 = " memory&hd : 128MB, 20GB";
            this.ClearedDescLine10 = " compiler : JDK1.3.1_01";
            this.ClearedDescLine11 = " graphics app: Adobe Photoshop 5.5J";
            this.ClearedDescLine12 = "";
            this.ClearedDescLine13 = "\u4e0a\u7530\u6b63\u7fa9 -cheez-";
            this.ClearedDescLine14 = "\u30b5\u30f3\u30ce\u30bc, \u30ab\u30ea\u30d5\u30a9\u30eb\u30cb\u30a2, USA";
            this.ClearedDescLine15 = "2001\u5e74 \u79cb";
            this.ClickOnLetters = "\u30a2\u30eb\u30d5\u30a1\u30d9\u30c3\u30c8\u3092\u30af\u30ea\u30c3\u30af\u3057\u3066\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002";
        }
        this.innercellsize = 18;
        this.paintedcellsize = this.innercellsize - 7;
        this.spacebetweenlineNball = 2;
        this.MAXplayfieldcelllength = 11;
        this.leftinfoareapixellength = 120;
        this.centerleftinfoareapixellength = this.leftinfoareapixellength / 2;
        this.MAXSolutionSteps = 200;
        this.cellpixellength = this.innercellsize + this.spacebetweenlineNball * 2;
        this.cellpixellengthhalf = this.cellpixellength / 2;
        this.playfieldcenterx = (this.winsizex - this.leftinfoareapixellength) / 2;
        this.playfieldcenterx = this.winsizex - this.playfieldcenterx;
        this.playfieldcentery = this.winsizey / 2;
        this.RotatingRight = false;
        this.RotatingLeft = false;
        this.delayvalue = 6;
        this.positive1 = 1;
        this.negative1 = -1;
        this.direction = this.positive1;
        this.directiondot1 = 1;
        this.RightMouseClickedinPlayfield = false;
        this.LeftMouseClickedinPlayfield = false;
        this.MouseMovementLocked = true;
        this.InTitleScreen = false;
        this.InGamePlayMode = false;
        this.DGSX = this.centerx - 65;
        this.NormalPasswordArray = new String[this.MAXStageNumNormal + 2 + this.NumOfSpecialStages];
        this.NormalPlusPasswordArray = new String[this.MAXStageNumNormalPlus + 2 + this.NumOfSpecialStages];
        this.cellxstart = new int[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 2];
        this.cellystart = new int[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 2];
        this.ballexist = new boolean[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.ballexisttarget = new boolean[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.ballexistcolor = new int[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.ballexisttargetcolor = new int[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.storeballexist = new boolean[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.storeballexisttarget = new boolean[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.storeballexistcolor = new int[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.storeballexisttargetcolor = new int[this.MAXplayfieldcelllength + 1][this.MAXplayfieldcelllength + 1];
        this.SolutionDataX = new int[this.MAXSolutionSteps + 1];
        this.SolutionDataY = new int[this.MAXSolutionSteps + 1];
        this.SolutionDataDir = new char[this.MAXSolutionSteps + 1];
        this.TempSolutionDataX = new int[this.MAXSolutionSteps + 1];
        this.TempSolutionDataY = new int[this.MAXSolutionSteps + 1];
        this.TempSolutionDataDir = new char[this.MAXSolutionSteps + 1];
        this.HistoryActiveCellMarkX = new int[this.MAXStepHistory + 1];
        this.HistoryActiveCellMarkY = new int[this.MAXStepHistory + 1];
        this.HistoryLeftRight = new char[this.MAXStepHistory + 1];
        this.SpecialPassword = "AOIRO";
        this.NormalPasswordArray[1] = "AZURE";
        this.NormalPasswordArray[2] = "BOARD";
        this.NormalPasswordArray[3] = "CONGO";
        this.NormalPasswordArray[4] = "DRUMS";
        this.NormalPasswordArray[5] = "EXTRE";
        this.NormalPasswordArray[6] = "FROGS";
        this.NormalPasswordArray[7] = "GREEN";
        this.NormalPasswordArray[8] = "HIPPO";
        this.NormalPasswordArray[9] = "IRISS";
        this.NormalPasswordArray[10] = "JELLO";
        this.NormalPasswordArray[11] = "KASEI";
        this.NormalPasswordArray[12] = "LIGHT";
        this.NormalPasswordArray[13] = "MOONS";
        this.NormalPasswordArray[14] = "NIGHT";
        this.NormalPasswordArray[15] = "OASIS";
        this.NormalPasswordArray[16] = "POKEM";
        this.NormalPasswordArray[17] = "QUEEN";
        this.NormalPasswordArray[18] = "RIDGE";
        this.NormalPasswordArray[19] = "SKYDV";
        this.NormalPasswordArray[20] = "TAHOE";
        this.NormalPasswordArray[21] = "UTURN";
        this.NormalPasswordArray[22] = "VALUE";
        this.NormalPasswordArray[23] = "WHRFS";
        this.NormalPasswordArray[24] = "WHETT";
        this.NormalPasswordArray[25] = "XEBEC";
        this.NormalPasswordArray[26] = "XYLEM";
        this.NormalPasswordArray[27] = "YERNY";
        this.NormalPasswordArray[28] = "YUKNN";
        this.NormalPasswordArray[29] = "ZAALS";
        this.NormalPasswordArray[30] = "ZOOID";
        this.NormalPasswordArray[31] = "ZBQWN";
        this.NormalPlusPasswordArray[1] = "ALARM";
        this.NormalPlusPasswordArray[2] = "BRAVE";
        this.NormalPlusPasswordArray[3] = "CORAL";
        this.NormalPlusPasswordArray[4] = "DRIFT";
        this.NormalPlusPasswordArray[5] = "EXERT";
        this.NormalPlusPasswordArray[6] = "FOGGY";
        this.NormalPlusPasswordArray[7] = "GLASS";
        this.NormalPlusPasswordArray[8] = "HARRY";
        this.NormalPlusPasswordArray[9] = "IDEAL";
        this.NormalPlusPasswordArray[10] = "JABBR";
        this.NormalPlusPasswordArray[11] = "KAPOK";
        this.NormalPlusPasswordArray[12] = "LUCKY";
        this.NormalPlusPasswordArray[13] = "MELON";
        this.NormalPlusPasswordArray[14] = "METEO";
        this.NormalPlusPasswordArray[15] = "NEPAL";
        this.NormalPlusPasswordArray[16] = "NEURO";
        this.NormalPlusPasswordArray[17] = "OPERA";
        this.NormalPlusPasswordArray[18] = "OTTER";
        this.NormalPlusPasswordArray[19] = "PANIC";
        this.NormalPlusPasswordArray[20] = "PYRIT";
        this.NormalPlusPasswordArray[21] = "QUACK";
        this.NormalPlusPasswordArray[22] = "QUARK";
        this.NormalPlusPasswordArray[23] = "RAPID";
        this.NormalPlusPasswordArray[24] = "RELAY";
        this.NormalPlusPasswordArray[25] = "SNOWS";
        this.NormalPlusPasswordArray[26] = "SWELL";
        this.NormalPlusPasswordArray[27] = "TOAST";
        this.NormalPlusPasswordArray[28] = "TRIKE";
        this.NormalPlusPasswordArray[29] = "UNITE";
        this.NormalPlusPasswordArray[30] = "UTILE";
        this.NormalPlusPasswordArray[31] = "VASEZ";
        this.NormalPlusPasswordArray[32] = "VOLCA";
        this.NormalPlusPasswordArray[33] = "WEAVE";
        this.NormalPlusPasswordArray[34] = "WINCH";
        this.NormalPlusPasswordArray[35] = "XAXAN";
        this.NormalPlusPasswordArray[36] = "XOOON";
        this.NormalPlusPasswordArray[37] = "YAWLS";
        this.NormalPlusPasswordArray[38] = "YUCCA";
        this.NormalPlusPasswordArray[39] = "ZIGZG";
        this.NormalPlusPasswordArray[40] = "ZIZZZ";
        this.NormalPlusPasswordArray[41] = "ZZTKA";
        this.AllInOneColor = false;
        this.gameQuit = false;
        this.gameOver = false;
        this.Solving = false;
        this.PicLoad = new boolean[this.NumOfImages];
        (this.PicSize = new int[this.NumOfImages])[0] = 2;
        this.PicSize[1] = 2;
        this.PicSize[2] = 2;
        this.PicSize[3] = 2;
        this.PicSize[4] = 2;
        this.PicSize[5] = 10;
        this.PicSize[6] = 28;
        this.PicSize[7] = 10;
        this.PicSize[8] = 2;
        this.PicSize[9] = 10;
        this.PicSize[10] = 2;
        this.PicSize[11] = 2;
        this.PicSize[12] = 2;
        this.PicSize[13] = 2;
        this.PicSize[14] = 2;
        this.PicSize[15] = 2;
        this.PicSize[16] = 2;
        this.PicSize[17] = 2;
        this.PicSize[18] = 2;
        this.PicSize[19] = 2;
        this.PicSize[20] = 2;
        this.PicSize[21] = 2;
        this.PicSize[22] = 2;
        this.PicSize[23] = 2;
        this.PicSize[24] = 2;
        this.PicSize[25] = 2;
        this.mt = new MediaTracker(this);
        for (int i = 0; i < this.NumOfImages; ++i) {
            this.PicLoad[i] = false;
        }
        this.CellImage = this.getImage(this.getDocumentBase(), "cell.gif");
        this.mt.addImage(this.CellImage, 0);
        this.VerLeftLineImage = this.getImage(this.getDocumentBase(), "verleftline.gif");
        this.mt.addImage(this.VerLeftLineImage, 1);
        this.VerRightLineImage = this.getImage(this.getDocumentBase(), "verrightline.gif");
        this.mt.addImage(this.VerRightLineImage, 2);
        this.HorTopLineImage = this.getImage(this.getDocumentBase(), "hortopline.gif");
        this.mt.addImage(this.HorTopLineImage, 3);
        this.HorBottomLineImage = this.getImage(this.getDocumentBase(), "horbottmline.gif");
        this.mt.addImage(this.HorBottomLineImage, 4);
        this.LeftBoardImage = this.getImage(this.getDocumentBase(), "leftboard.gif");
        this.mt.addImage(this.LeftBoardImage, 5);
        this.StageAllClearImage = this.getImage(this.getDocumentBase(), "stageallclear.gif");
        this.mt.addImage(this.StageAllClearImage, 6);
        this.passwordpanelpicture = this.getImage(this.getDocumentBase(), "passwordpanel.gif");
        this.mt.addImage(this.passwordpanelpicture, 7);
        if (this.ENGLISH) {
            this.SmallTitleImage = this.getImage(this.getDocumentBase(), "smalltitle.gif");
            this.mt.addImage(this.SmallTitleImage, 8);
            this.BigTitleImage = this.getImage(this.getDocumentBase(), "titlebig.gif");
            this.mt.addImage(this.BigTitleImage, 9);
            this.EnterPasswordImage = this.getImage(this.getDocumentBase(), "passwordbanner.gif");
            this.mt.addImage(this.EnterPasswordImage, 10);
            this.NormalImage = this.getImage(this.getDocumentBase(), "normalbanner.gif");
            this.mt.addImage(this.NormalImage, 11);
            this.NormalPlusImage = this.getImage(this.getDocumentBase(), "normalplusbanner.gif");
            this.mt.addImage(this.NormalPlusImage, 12);
            this.ExtraImage = this.getImage(this.getDocumentBase(), "extrabanner.gif");
            this.mt.addImage(this.ExtraImage, 13);
            this.ExtraPlusImage = this.getImage(this.getDocumentBase(), "extraplusbanner.gif");
            this.mt.addImage(this.ExtraPlusImage, 14);
            this.EndlessImage = this.getImage(this.getDocumentBase(), "endlessbanner.gif");
            this.mt.addImage(this.EndlessImage, 15);
            this.EndlessPlusImage = this.getImage(this.getDocumentBase(), "endlessplusbanner.gif");
            this.mt.addImage(this.EndlessPlusImage, 16);
            this.HowToPlayImage = this.getImage(this.getDocumentBase(), "howtoplaybanner.gif");
            this.mt.addImage(this.HowToPlayImage, 17);
            this.QuitButtonImage = this.getImage(this.getDocumentBase(), "quitbutton.gif");
            this.mt.addImage(this.QuitButtonImage, 18);
            this.ResetButtonImage = this.getImage(this.getDocumentBase(), "resetbutton.gif");
            this.mt.addImage(this.ResetButtonImage, 19);
            this.SolveButtonImage = this.getImage(this.getDocumentBase(), "solvebutton.gif");
            this.mt.addImage(this.SolveButtonImage, 20);
            this.UndoButtonImage = this.getImage(this.getDocumentBase(), "undobutton.gif");
            this.mt.addImage(this.UndoButtonImage, 21);
            this.AboutImage = this.getImage(this.getDocumentBase(), "aboutbanner.gif");
            this.mt.addImage(this.AboutImage, 22);
            this.LegalImage = this.getImage(this.getDocumentBase(), "legalbanner.gif");
            this.mt.addImage(this.LegalImage, 23);
            this.PlayButtonImage = this.getImage(this.getDocumentBase(), "playbutton.gif");
            this.mt.addImage(this.PlayButtonImage, 24);
            this.BackButtonImage = this.getImage(this.getDocumentBase(), "backbutton.gif");
            this.mt.addImage(this.BackButtonImage, 25);
        }
        else if (this.JAPANESE) {
            this.SmallTitleImage = this.getImage(this.getDocumentBase(), "j_smalltitle.gif");
            this.mt.addImage(this.SmallTitleImage, 8);
            this.BigTitleImage = this.getImage(this.getDocumentBase(), "j_titlebig.gif");
            this.mt.addImage(this.BigTitleImage, 9);
            this.EnterPasswordImage = this.getImage(this.getDocumentBase(), "j_passwordbanner.gif");
            this.mt.addImage(this.EnterPasswordImage, 10);
            this.NormalImage = this.getImage(this.getDocumentBase(), "j_normalbanner.gif");
            this.mt.addImage(this.NormalImage, 11);
            this.NormalPlusImage = this.getImage(this.getDocumentBase(), "j_normalplusbanner.gif");
            this.mt.addImage(this.NormalPlusImage, 12);
            this.ExtraImage = this.getImage(this.getDocumentBase(), "j_extrabanner.gif");
            this.mt.addImage(this.ExtraImage, 13);
            this.ExtraPlusImage = this.getImage(this.getDocumentBase(), "j_extraplusbanner.gif");
            this.mt.addImage(this.ExtraPlusImage, 14);
            this.EndlessImage = this.getImage(this.getDocumentBase(), "j_endlessbanner.gif");
            this.mt.addImage(this.EndlessImage, 15);
            this.EndlessPlusImage = this.getImage(this.getDocumentBase(), "j_endlessplusbanner.gif");
            this.mt.addImage(this.EndlessPlusImage, 16);
            this.HowToPlayImage = this.getImage(this.getDocumentBase(), "j_howtoplaybanner.gif");
            this.mt.addImage(this.HowToPlayImage, 17);
            this.QuitButtonImage = this.getImage(this.getDocumentBase(), "j_quitbutton.gif");
            this.mt.addImage(this.QuitButtonImage, 18);
            this.ResetButtonImage = this.getImage(this.getDocumentBase(), "j_resetbutton.gif");
            this.mt.addImage(this.ResetButtonImage, 19);
            this.SolveButtonImage = this.getImage(this.getDocumentBase(), "j_solvebutton.gif");
            this.mt.addImage(this.SolveButtonImage, 20);
            this.UndoButtonImage = this.getImage(this.getDocumentBase(), "j_undobutton.gif");
            this.mt.addImage(this.UndoButtonImage, 21);
            this.AboutImage = this.getImage(this.getDocumentBase(), "j_aboutbanner.gif");
            this.mt.addImage(this.AboutImage, 22);
            this.LegalImage = this.getImage(this.getDocumentBase(), "j_legalbanner.gif");
            this.mt.addImage(this.LegalImage, 23);
            this.PlayButtonImage = this.getImage(this.getDocumentBase(), "j_playbutton.gif");
            this.mt.addImage(this.PlayButtonImage, 24);
            this.BackButtonImage = this.getImage(this.getDocumentBase(), "j_backbutton.gif");
            this.mt.addImage(this.BackButtonImage, 25);
        }
        this.CurrentlyInHowToPlayImageBanner = false;
        this.CurrentlyInNormalImageBanner = false;
        this.CurrentlyInNormalPlusImageBanner = false;
        this.CurrentlyInExtraImageBanner = false;
        this.CurrentlyInExtraPlusImageBanner = false;
        this.CurrentlyInEndlessImageBanner = false;
        this.CurrentlyInEndlessPlusImageBanner = false;
        this.CurrentlyInEnterPasswordImageBanner = false;
        this.CurrentlyInAboutImageBanner = false;
        this.CurrentlyInLegalImageBanner = false;
        this.TempArrowImageLeftRightValueX = 0;
        this.ArrowImageLeftRightValueX = 0;
        this.ArrowImageLeftRightValueY = 0;
        this.ArrowImageWidth = 10;
        this.ExplainImageXStart = this.centerx - this.BannerWidth / 2;
        this.ExplainImageYStart = 30;
        this.BigTitleImageXStart = 57;
        this.BigTitleImageYStart = 10;
        this.HowToPlayImageXStart = this.centerx - this.BannerWidth / 2;
        this.HowToPlayImageYStart = 67;
        this.NormalImageXStart = this.HowToPlayImageXStart;
        this.NormalImageYStart = this.HowToPlayImageYStart + this.BannerHeight + 1;
        this.NormalPlusImageXStart = this.HowToPlayImageXStart;
        this.NormalPlusImageYStart = this.NormalImageYStart + this.BannerHeight + 1;
        this.EndlessImageXStart = this.HowToPlayImageXStart;
        this.EndlessImageYStart = this.NormalPlusImageYStart + this.BannerHeight + 1;
        this.EndlessPlusImageXStart = this.HowToPlayImageXStart;
        this.EndlessPlusImageYStart = this.EndlessImageYStart + this.BannerHeight + 1;
        this.ExtraImageXStart = this.HowToPlayImageXStart;
        this.ExtraImageYStart = this.EndlessPlusImageYStart + this.BannerHeight + 1;
        this.ExtraPlusImageXStart = this.HowToPlayImageXStart;
        this.ExtraPlusImageYStart = this.ExtraImageYStart + this.BannerHeight + 1;
        this.EnterPasswordImageXStart = this.HowToPlayImageXStart;
        this.EnterPasswordImageYStart = this.ExtraPlusImageYStart + this.BannerHeight + 1;
        this.AboutImageXStart = this.HowToPlayImageXStart;
        this.AboutImageYStart = this.EnterPasswordImageYStart + this.BannerHeight + 1;
        this.LegalImageXStart = this.HowToPlayImageXStart;
        this.LegalImageYStart = this.AboutImageYStart + this.BannerHeight + 1;
        this.SolveButtonImageXStart = 35;
        this.SolveButtonImageYStart = 180;
        this.UndoButtonImageXStart = 35;
        this.UndoButtonImageYStart = this.SolveButtonImageYStart + this.ButtonHeight + 3;
        this.ResetButtonImageXStart = 35;
        this.ResetButtonImageYStart = this.UndoButtonImageYStart + this.ButtonHeight + 3;
        this.QuitButtonImageXStart = 35;
        this.QuitButtonImageYStart = this.ResetButtonImageYStart + this.ButtonHeight + 3;
        this.PlayButtonImageXStart = this.centerx - this.ButtonWidth - 20;
        this.PlayButtonImageYStart = 260;
        this.BackButtonImageXStart = this.centerx + 20;
        this.BackButtonImageYStart = 260;
        this.StageAllClearImageXStart = 150;
        this.StageAllClearImageYStart = 40;
        this.InPasswordScreen = false;
        this.InAboutScreen = false;
        this.InLegalScreen = false;
        this.InNormalExplainScreen = false;
        this.InNormalPlusExplainScreen = false;
        this.InExtraExplainScreen = false;
        this.InExtraPlusExplainScreen = false;
        this.InEndlessExplainScreen = false;
        this.InEndlessPlusExplainScreen = false;
        this.InHowToPlayScreen = false;
        this.NormalPlay = false;
        this.NormalPlusPlay = false;
        this.ExtraPlay = false;
        this.ExtraPlusPlay = false;
        this.EndlessPlay = false;
        this.EndlessPlusPlay = false;
        this.NumOfSolutionSteps = 5;
        this.setBackground(Color.black);
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void ArrowGlow(final int n, final int n2) {
        this.TempArrowImageLeftRightValueX = n - this.ArrowImageWidth;
        this.ArrowImageLeftRightValueY = n2 + this.ArrowImageWidth / 2;
        this.ArrowGlowColorR -= this.ArrowGlowRGBIncrement;
        this.ArrowGlowColorG -= this.ArrowGlowRGBIncrement;
        if (this.ArrowGlowColorR < 60) {
            this.ArrowGlowRGBIncrement *= -1;
            this.ArrowGlowColorR = 60;
        }
        if (this.ArrowGlowColorR > 255) {
            this.ArrowGlowRGBIncrement *= -1;
            this.ArrowGlowColorR = 255;
        }
    }
    
    public void CalculateArrowP1toP6(final int n) {
        this.FirstArrowP1x = this.TempArrowImageLeftRightValueX;
        this.FirstArrowP1y = this.ArrowImageLeftRightValueY - 1;
        this.FirstArrowP2x = this.TempArrowImageLeftRightValueX + 5;
        this.FirstArrowP2y = this.ArrowImageLeftRightValueY + 4;
        this.FirstArrowP3x = this.TempArrowImageLeftRightValueX;
        this.FirstArrowP3y = this.ArrowImageLeftRightValueY + 9;
        this.SecondArrowP1x = this.TempArrowImageLeftRightValueX;
        this.SecondArrowP1y = this.ArrowImageLeftRightValueY;
        this.SecondArrowP2x = this.TempArrowImageLeftRightValueX + 4;
        this.SecondArrowP2y = this.ArrowImageLeftRightValueY + 4;
        this.SecondArrowP3x = this.TempArrowImageLeftRightValueX;
        this.SecondArrowP3y = this.ArrowImageLeftRightValueY + 8;
        this.ThirdArrowP1x = this.TempArrowImageLeftRightValueX;
        this.ThirdArrowP1y = this.ArrowImageLeftRightValueY + 1;
        this.ThirdArrowP2x = this.TempArrowImageLeftRightValueX + 3;
        this.ThirdArrowP2y = this.ArrowImageLeftRightValueY + 4;
        this.ThirdArrowP3x = this.TempArrowImageLeftRightValueX;
        this.ThirdArrowP3y = this.ArrowImageLeftRightValueY + 7;
        this.FourthArrowP1x = this.TempArrowImageLeftRightValueX;
        this.FourthArrowP1y = this.ArrowImageLeftRightValueY + 2;
        this.FourthArrowP2x = this.TempArrowImageLeftRightValueX + 2;
        this.FourthArrowP2y = this.ArrowImageLeftRightValueY + 4;
        this.FourthArrowP3x = this.TempArrowImageLeftRightValueX;
        this.FourthArrowP3y = this.ArrowImageLeftRightValueY + 6;
        this.FifthArrowP1x = this.TempArrowImageLeftRightValueX;
        this.FifthArrowP1y = this.ArrowImageLeftRightValueY + 3;
        this.FifthArrowP2x = this.TempArrowImageLeftRightValueX + 1;
        this.FifthArrowP2y = this.ArrowImageLeftRightValueY + 4;
        this.FifthArrowP3x = this.TempArrowImageLeftRightValueX;
        this.FifthArrowP3y = this.ArrowImageLeftRightValueY + 5;
        this.SixthArrowP1x = this.TempArrowImageLeftRightValueX;
        this.SixthArrowP1y = this.ArrowImageLeftRightValueY + 4;
        this.SixthArrowP2x = this.TempArrowImageLeftRightValueX;
        this.SixthArrowP2y = this.ArrowImageLeftRightValueY + 4;
        this.SixthArrowP3x = this.TempArrowImageLeftRightValueX;
        this.SixthArrowP3y = this.ArrowImageLeftRightValueY + 4;
        this.FirstArrowP4x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.FirstArrowP4y = this.ArrowImageLeftRightValueY - 1;
        this.FirstArrowP5x = this.TempArrowImageLeftRightValueX + 5 + n + 8;
        this.FirstArrowP5y = this.ArrowImageLeftRightValueY + 4;
        this.FirstArrowP6x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.FirstArrowP6y = this.ArrowImageLeftRightValueY + 9;
        this.SecondArrowP4x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.SecondArrowP4y = this.ArrowImageLeftRightValueY;
        this.SecondArrowP5x = this.TempArrowImageLeftRightValueX + 5 + n + 9;
        this.SecondArrowP5y = this.ArrowImageLeftRightValueY + 4;
        this.SecondArrowP6x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.SecondArrowP6y = this.ArrowImageLeftRightValueY + 8;
        this.ThirdArrowP4x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.ThirdArrowP4y = this.ArrowImageLeftRightValueY + 1;
        this.ThirdArrowP5x = this.TempArrowImageLeftRightValueX + 5 + n + 10;
        this.ThirdArrowP5y = this.ArrowImageLeftRightValueY + 4;
        this.ThirdArrowP6x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.ThirdArrowP6y = this.ArrowImageLeftRightValueY + 7;
        this.FourthArrowP4x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.FourthArrowP4y = this.ArrowImageLeftRightValueY + 2;
        this.FourthArrowP5x = this.TempArrowImageLeftRightValueX + 5 + n + 11;
        this.FourthArrowP5y = this.ArrowImageLeftRightValueY + 4;
        this.FourthArrowP6x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.FourthArrowP6y = this.ArrowImageLeftRightValueY + 6;
        this.FifthArrowP4x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.FifthArrowP4y = this.ArrowImageLeftRightValueY + 3;
        this.FifthArrowP5x = this.TempArrowImageLeftRightValueX + 5 + n + 12;
        this.FifthArrowP5y = this.ArrowImageLeftRightValueY + 4;
        this.FifthArrowP6x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.FifthArrowP6y = this.ArrowImageLeftRightValueY + 5;
        this.SixthArrowP4x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.SixthArrowP4y = this.ArrowImageLeftRightValueY + 4;
        this.SixthArrowP5x = this.TempArrowImageLeftRightValueX + 5 + n + 13;
        this.SixthArrowP5y = this.ArrowImageLeftRightValueY + 4;
        this.SixthArrowP6x = this.TempArrowImageLeftRightValueX + n + 10 + 8;
        this.SixthArrowP6y = this.ArrowImageLeftRightValueY + 4;
    }
    
    public void DrawTriangles() {
        this.offGrfx.drawLine(this.FirstArrowP1x, this.FirstArrowP1y, this.FirstArrowP2x, this.FirstArrowP2y);
        this.offGrfx.drawLine(this.FirstArrowP3x, this.FirstArrowP3y, this.FirstArrowP2x, this.FirstArrowP2y);
        this.offGrfx.drawLine(this.SecondArrowP1x, this.SecondArrowP1y, this.SecondArrowP2x, this.SecondArrowP2y);
        this.offGrfx.drawLine(this.SecondArrowP3x, this.SecondArrowP3y, this.SecondArrowP2x, this.SecondArrowP2y);
        this.offGrfx.drawLine(this.ThirdArrowP1x, this.ThirdArrowP1y, this.ThirdArrowP2x, this.ThirdArrowP2y);
        this.offGrfx.drawLine(this.ThirdArrowP3x, this.ThirdArrowP3y, this.ThirdArrowP2x, this.ThirdArrowP2y);
        this.offGrfx.drawLine(this.FourthArrowP1x, this.FourthArrowP1y, this.FourthArrowP2x, this.FourthArrowP2y);
        this.offGrfx.drawLine(this.FourthArrowP3x, this.FourthArrowP3y, this.FourthArrowP2x, this.FourthArrowP2y);
        this.offGrfx.drawLine(this.FifthArrowP1x, this.FifthArrowP1y, this.FifthArrowP2x, this.FifthArrowP2y);
        this.offGrfx.drawLine(this.FifthArrowP3x, this.FifthArrowP3y, this.FifthArrowP2x, this.FifthArrowP2y);
        this.offGrfx.drawLine(this.SixthArrowP1x, this.SixthArrowP1y, this.SixthArrowP2x, this.SixthArrowP2y);
        this.offGrfx.drawLine(this.SixthArrowP3x, this.SixthArrowP3y, this.SixthArrowP2x, this.SixthArrowP2y);
        this.offGrfx.drawLine(this.FirstArrowP4x, this.FirstArrowP4y, this.FirstArrowP5x, this.FirstArrowP5y);
        this.offGrfx.drawLine(this.FirstArrowP6x, this.FirstArrowP6y, this.FirstArrowP5x, this.FirstArrowP5y);
        this.offGrfx.drawLine(this.SecondArrowP4x, this.SecondArrowP4y, this.SecondArrowP5x, this.SecondArrowP5y);
        this.offGrfx.drawLine(this.SecondArrowP6x, this.SecondArrowP6y, this.SecondArrowP5x, this.SecondArrowP5y);
        this.offGrfx.drawLine(this.ThirdArrowP4x, this.ThirdArrowP4y, this.ThirdArrowP5x, this.ThirdArrowP5y);
        this.offGrfx.drawLine(this.ThirdArrowP6x, this.ThirdArrowP6y, this.ThirdArrowP5x, this.ThirdArrowP5y);
        this.offGrfx.drawLine(this.FourthArrowP4x, this.FourthArrowP4y, this.FourthArrowP5x, this.FourthArrowP5y);
        this.offGrfx.drawLine(this.FourthArrowP6x, this.FourthArrowP6y, this.FourthArrowP5x, this.FourthArrowP5y);
        this.offGrfx.drawLine(this.FifthArrowP4x, this.FifthArrowP4y, this.FifthArrowP5x, this.FifthArrowP5y);
        this.offGrfx.drawLine(this.FifthArrowP6x, this.FifthArrowP6y, this.FifthArrowP5x, this.FifthArrowP5y);
        this.offGrfx.drawLine(this.SixthArrowP4x, this.SixthArrowP4y, this.SixthArrowP5x, this.SixthArrowP5y);
        this.offGrfx.drawLine(this.SixthArrowP6x, this.SixthArrowP6y, this.SixthArrowP5x, this.SixthArrowP5y);
    }
    
    public void SetColorForSource(final int n, final int n2) {
        if (this.ballexistcolor[n][n2] == 1) {
            this.offGrfx.setColor(Color.red);
        }
        else if (this.ballexistcolor[n][n2] == 2) {
            this.offGrfx.setColor(Color.blue);
        }
        else if (this.ballexistcolor[n][n2] == 3) {
            this.offGrfx.setColor(Color.green);
        }
        else if (this.ballexistcolor[n][n2] == 4) {
            this.offGrfx.setColor(Color.magenta);
        }
        else if (this.ballexistcolor[n][n2] == 5) {
            this.offGrfx.setColor(Color.cyan);
        }
    }
    
    public void SetColorForTarget(final int n, final int n2) {
        if (this.ballexisttargetcolor[n][n2] == 1) {
            this.offGrfx.setColor(Color.red);
        }
        else if (this.ballexisttargetcolor[n][n2] == 2) {
            this.offGrfx.setColor(Color.blue);
        }
        else if (this.ballexisttargetcolor[n][n2] == 3) {
            this.offGrfx.setColor(Color.green);
        }
        else if (this.ballexisttargetcolor[n][n2] == 4) {
            this.offGrfx.setColor(Color.magenta);
        }
        else if (this.ballexisttargetcolor[n][n2] == 5) {
            this.offGrfx.setColor(Color.cyan);
        }
    }
    
    public void DisplayClearMessage() {
        this.offGrfx.drawString(this.ClearedDescLine1, 10, this.ClearedDescriptionStartY);
        this.offGrfx.drawString(this.ClearedDescLine2, 10, this.ClearedDescriptionStartY + 15);
        this.offGrfx.drawString(this.ClearedDescLine3, 10, this.ClearedDescriptionStartY + 30);
        this.offGrfx.drawString(this.ClearedDescLine4, 10, this.ClearedDescriptionStartY + 45);
        this.offGrfx.drawString(this.ClearedDescLine5, 10, this.ClearedDescriptionStartY + 60);
        this.offGrfx.drawString(this.ClearedDescLine6, 10, this.ClearedDescriptionStartY + 75);
        this.offGrfx.drawString(this.ClearedDescLine7, 10, this.ClearedDescriptionStartY + 90);
        this.offGrfx.drawString(this.ClearedDescLine8, 10, this.ClearedDescriptionStartY + 105);
        this.offGrfx.drawString(this.ClearedDescLine9, 10, this.ClearedDescriptionStartY + 120);
        this.offGrfx.drawString(this.ClearedDescLine10, 10, this.ClearedDescriptionStartY + 135);
        this.offGrfx.drawString(this.ClearedDescLine11, 10, this.ClearedDescriptionStartY + 150);
        this.offGrfx.drawString(this.ClearedDescLine12, 10, this.ClearedDescriptionStartY + 165);
        this.offGrfx.drawString(this.ClearedDescLine13, 10, this.ClearedDescriptionStartY + 180);
        this.offGrfx.drawString(this.ClearedDescLine14, 10, this.ClearedDescriptionStartY + 195);
        this.offGrfx.drawString(this.ClearedDescLine15, 10, this.ClearedDescriptionStartY + 210);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offGrfx == null) {
            this.offImage = this.createImage(size.width, size.height);
            this.offGrfx = this.offImage.getGraphics();
        }
        this.offGrfx.setColor(this.getBackground());
        this.offGrfx.fillRect(0, 0, size.width, size.height);
        this.offGrfx.setColor(Color.black);
        if (!this.InGamePlayMode) {
            this.offGrfx.setColor(Color.gray);
            this.tempString = "Game title: " + this.GameTitle + " Copyright  Masayoshi Ueda";
            this.tempX = this.myG.getFontMetrics().stringWidth(this.tempString) / 2;
            this.tempX = this.centerx - this.tempX;
            this.tempY = this.winsizey - 8;
            this.offGrfx.drawString(this.tempString, this.tempX, this.tempY);
        }
        this.offGrfx.setColor(Color.yellow);
        if (this.InShowPasswordScreen) {
            this.offGrfx.setColor(Color.yellow);
            this.offGrfx.drawString(this.PwdForStageFront + (this.CurrentStage + 1) + this.PwdForStageEnd + this.NextStagePassword, 100, 100);
        }
        else if (this.InNormalPlayAllClearedScreen) {
            this.offGrfx.drawImage(this.StageAllClearImage, this.StageAllClearImageXStart, this.StageAllClearImageYStart, this);
            this.offGrfx.setColor(Color.gray);
            this.offGrfx.drawString(this.NormalAllClearString, 10, 20);
            this.DisplayClearMessage();
            this.offGrfx.drawString(this.TryThisPwdString + this.NormalPasswordArray[this.MAXStageNumNormal + 1], 10, this.ClearedDescriptionStartY + 240);
        }
        else if (this.InNormalPlusPlayAllClearedScreen) {
            this.offGrfx.drawImage(this.StageAllClearImage, this.StageAllClearImageXStart, this.StageAllClearImageYStart, this);
            this.offGrfx.setColor(Color.gray);
            this.offGrfx.drawString(this.NormalPlusAllClearString, 10, 20);
            this.DisplayClearMessage();
            this.offGrfx.drawString(this.TryThisPwdString + this.NormalPlusPasswordArray[this.MAXStageNumNormalPlus + 1], 10, this.ClearedDescriptionStartY + 240);
        }
        else if (this.InExtraPlayAllClearedScreen) {
            this.offGrfx.drawImage(this.StageAllClearImage, this.StageAllClearImageXStart, this.StageAllClearImageYStart, this);
            this.offGrfx.setColor(Color.gray);
            this.offGrfx.drawString(this.ExtraAllClearString, 10, 20);
            this.DisplayClearMessage();
            this.offGrfx.drawString(this.TryThisPwdString + this.SpecialPassword, 10, this.ClearedDescriptionStartY + 240);
        }
        else if (this.InExtraPlusPlayAllClearedScreen) {
            this.offGrfx.drawImage(this.StageAllClearImage, this.StageAllClearImageXStart, this.StageAllClearImageYStart, this);
            this.offGrfx.setColor(Color.gray);
            this.offGrfx.drawString(this.ExtraPlusAllClearString, 10, 20);
            this.DisplayClearMessage();
            this.offGrfx.drawString("thank you!", 10, this.ClearedDescriptionStartY + 240);
        }
        else if (this.InEndlessPlayAllClearedScreen || this.InEndlessPlusPlayAllClearedScreen) {
            this.offGrfx.setColor(Color.yellow);
            this.offGrfx.drawString("Endless (Plus) ALL Clear!", 100, 100);
        }
        else if (this.InTitleScreen) {
            if (this.CurrentlyInHowToPlayImageBanner) {
                this.ArrowGlow(this.HowToPlayImageXStart, this.HowToPlayImageYStart);
            }
            else if (this.CurrentlyInNormalImageBanner) {
                this.ArrowGlow(this.NormalImageXStart, this.NormalImageYStart);
            }
            else if (this.CurrentlyInNormalPlusImageBanner) {
                this.ArrowGlow(this.NormalPlusImageXStart, this.NormalPlusImageYStart);
            }
            else if (this.CurrentlyInExtraImageBanner) {
                this.ArrowGlow(this.ExtraImageXStart, this.ExtraImageYStart);
            }
            else if (this.CurrentlyInExtraPlusImageBanner) {
                this.ArrowGlow(this.ExtraPlusImageXStart, this.ExtraPlusImageYStart);
            }
            else if (this.CurrentlyInEndlessImageBanner) {
                this.ArrowGlow(this.EndlessImageXStart, this.EndlessImageYStart);
            }
            else if (this.CurrentlyInEndlessPlusImageBanner) {
                this.ArrowGlow(this.EndlessPlusImageXStart, this.EndlessPlusImageYStart);
            }
            else if (this.CurrentlyInEnterPasswordImageBanner) {
                this.ArrowGlow(this.EnterPasswordImageXStart, this.EnterPasswordImageYStart);
            }
            else if (this.CurrentlyInAboutImageBanner) {
                this.ArrowGlow(this.AboutImageXStart, this.AboutImageYStart);
            }
            else if (this.CurrentlyInLegalImageBanner) {
                this.ArrowGlow(this.LegalImageXStart, this.LegalImageYStart);
            }
            this.offGrfx.drawImage(this.BigTitleImage, this.BigTitleImageXStart, this.BigTitleImageYStart, this);
            this.offGrfx.drawImage(this.HowToPlayImage, this.HowToPlayImageXStart, this.HowToPlayImageYStart, this);
            this.offGrfx.drawImage(this.NormalImage, this.NormalImageXStart, this.NormalImageYStart, this);
            this.offGrfx.drawImage(this.NormalPlusImage, this.NormalPlusImageXStart, this.NormalPlusImageYStart, this);
            this.offGrfx.drawImage(this.ExtraImage, this.ExtraImageXStart, this.ExtraImageYStart, this);
            this.offGrfx.drawImage(this.ExtraPlusImage, this.ExtraPlusImageXStart, this.ExtraPlusImageYStart, this);
            this.offGrfx.drawImage(this.EndlessImage, this.EndlessImageXStart, this.EndlessImageYStart, this);
            this.offGrfx.drawImage(this.EndlessPlusImage, this.EndlessPlusImageXStart, this.EndlessPlusImageYStart, this);
            this.offGrfx.drawImage(this.EnterPasswordImage, this.EnterPasswordImageXStart, this.EnterPasswordImageYStart, this);
            this.offGrfx.drawImage(this.AboutImage, this.AboutImageXStart, this.AboutImageYStart, this);
            this.offGrfx.drawImage(this.LegalImage, this.LegalImageXStart, this.LegalImageYStart, this);
            if (this.DisplayArrowImage) {
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.BannerWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InNormalExplainScreen) {
            this.offGrfx.drawImage(this.NormalImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InNormalPlusExplainScreen) {
            this.offGrfx.drawImage(this.NormalPlusImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.NormalPlusDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.NormalPlusDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InExtraExplainScreen) {
            this.offGrfx.drawImage(this.ExtraImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InExtraPlusExplainScreen) {
            this.offGrfx.drawImage(this.ExtraPlusImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.ExtraPlusDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.ExtraPlusDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InEndlessExplainScreen) {
            this.offGrfx.drawImage(this.EndlessImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InEndlessPlusExplainScreen) {
            this.offGrfx.drawImage(this.EndlessPlusImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.EndlessPlusDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.EndlessPlusDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InPasswordScreen) {
            this.offGrfx.drawImage(this.EnterPasswordImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.offGrfx.setColor(Color.yellow);
            this.offGrfx.drawImage(this.passwordpanelpicture, this.ppanelx, this.ppanely, this);
            this.offGrfx.drawString(this.PasswordEnteredString, this.centerx - 20, 240);
            this.ClickLettersX = this.myG.getFontMetrics().stringWidth(this.ClickOnLetters) / 2;
            this.ClickLettersX = this.centerx - this.ClickLettersX;
            this.offGrfx.drawString(this.ClickOnLetters, this.ClickLettersX, 75);
            this.offGrfx.drawImage(this.PlayButtonImage, this.PlayButtonImageXStart, this.PlayButtonImageYStart, this);
            this.offGrfx.drawImage(this.BackButtonImage, this.BackButtonImageXStart, this.BackButtonImageYStart, this);
            if (this.CurrentlyInPlayButtonImage) {
                this.ArrowGlow(this.PlayButtonImageXStart, this.PlayButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.BackButtonImageXStart, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InHowToPlayScreen) {
            this.offGrfx.drawImage(this.HowToPlayImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.HowToDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.HowToDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.BackButtonImage, this.centerx - this.ButtonWidth / 2, this.BackButtonImageYStart, this);
            if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.centerx - this.ButtonWidth / 2, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InAboutScreen) {
            this.offGrfx.drawImage(this.AboutImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.AboutDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.AboutDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.BackButtonImage, this.centerx - this.ButtonWidth / 2, this.BackButtonImageYStart, this);
            if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.centerx - this.ButtonWidth / 2, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InLegalScreen) {
            this.offGrfx.drawImage(this.LegalImage, this.ExplainImageXStart, this.ExplainImageYStart, this);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine1) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine1, this.DescX, this.DescriptionStartY);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine2) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine2, this.DescX, this.DescriptionStartY + 15);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine3) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine3, this.DescX, this.DescriptionStartY + 30);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine4) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine4, this.DescX, this.DescriptionStartY + 45);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine5) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine5, this.DescX, this.DescriptionStartY + 60);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine6) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine6, this.DescX, this.DescriptionStartY + 75);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine7) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine7, this.DescX, this.DescriptionStartY + 90);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine8) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine8, this.DescX, this.DescriptionStartY + 105);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine9) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine9, this.DescX, this.DescriptionStartY + 120);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine10) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine10, this.DescX, this.DescriptionStartY + 135);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine11) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine11, this.DescX, this.DescriptionStartY + 150);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine12) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine12, this.DescX, this.DescriptionStartY + 165);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.LegalDescLine13) / 2;
            this.DescX = this.centerx - this.DescX;
            this.offGrfx.drawString(this.LegalDescLine13, this.DescX, this.DescriptionStartY + 180);
            this.offGrfx.drawImage(this.BackButtonImage, this.centerx - this.ButtonWidth / 2, this.BackButtonImageYStart, this);
            if (this.CurrentlyInBackButtonImage) {
                this.ArrowGlow(this.centerx - this.ButtonWidth / 2, this.BackButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
        }
        else if (this.InGamePlayMode) {
            this.offGrfx.setFont(this.f);
            this.offGrfx.drawImage(this.LeftBoardImage, 0, 0, this);
            this.offGrfx.drawImage(this.SmallTitleImage, 9, 9, this);
            this.offGrfx.drawImage(this.HorTopLineImage, this.leftinfoareapixellength, 0, this);
            this.offGrfx.drawImage(this.HorBottomLineImage, this.leftinfoareapixellength, this.winsizey - 10, this);
            this.offGrfx.drawImage(this.VerLeftLineImage, this.leftinfoareapixellength - 9, 9, this);
            this.offGrfx.drawImage(this.VerRightLineImage, this.winsizex - 10, 10, this);
            this.offGrfx.drawImage(this.ResetButtonImage, this.ResetButtonImageXStart, this.ResetButtonImageYStart, this);
            this.offGrfx.drawImage(this.QuitButtonImage, this.QuitButtonImageXStart, this.QuitButtonImageYStart, this);
            this.offGrfx.drawImage(this.UndoButtonImage, this.UndoButtonImageXStart, this.UndoButtonImageYStart, this);
            if (this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay || this.DEBUGSOLVEALWAYS) {
                this.offGrfx.drawImage(this.SolveButtonImage, this.SolveButtonImageXStart, this.SolveButtonImageYStart, this);
            }
            if (this.CurrentlyInQuitButtonImage) {
                this.ArrowGlow(this.QuitButtonImageXStart, this.QuitButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInResetButtonImage) {
                this.ArrowGlow(this.ResetButtonImageXStart, this.ResetButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInSolveButtonImage) {
                this.ArrowGlow(this.SolveButtonImageXStart, this.SolveButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            else if (this.CurrentlyInUndoButtonImage) {
                this.ArrowGlow(this.UndoButtonImageXStart, this.UndoButtonImageYStart);
                this.offGrfx.setColor(new Color(this.ArrowGlowColorR, this.ArrowGlowColorG, this.ArrowGlowColorB));
                this.CalculateArrowP1toP6(this.ButtonWidth);
                this.DrawTriangles();
            }
            this.offGrfx.setColor(Color.yellow);
            if (this.NormalPlay) {
                this.offGrfx.drawImage(this.NormalImage, 10, 40, this);
            }
            else if (this.NormalPlusPlay) {
                this.offGrfx.drawImage(this.NormalPlusImage, 10, 40, this);
            }
            else if (this.ExtraPlay) {
                this.offGrfx.drawImage(this.ExtraImage, 10, 40, this);
            }
            else if (this.ExtraPlusPlay) {
                this.offGrfx.drawImage(this.ExtraPlusImage, 10, 40, this);
            }
            else if (this.EndlessPlay) {
                this.offGrfx.drawImage(this.EndlessImage, 10, 40, this);
            }
            else if (this.EndlessPlusPlay) {
                this.offGrfx.drawImage(this.EndlessPlusImage, 10, 40, this);
            }
            this.offGrfx.setColor(Color.yellow);
            if (!this.EndlessPlay && !this.EndlessPlusPlay) {
                this.StageActualDisplayString = this.StageString + this.CurrentStage + this.OfString + this.TotalStages;
                this.StageX = this.myG.getFontMetrics().stringWidth(this.StageActualDisplayString) / 2;
                this.StageX = this.centerleftinfoareapixellength - this.StageX;
                this.offGrfx.drawString(this.StageActualDisplayString, this.StageX, 90);
            }
            this.offGrfx.drawString("X, Y: " + this.activecellmarkx + ", " + this.activecellmarky, 40, 106);
            if (this.NormalPlusPlay || this.ExtraPlusPlay || this.EndlessPlusPlay) {
                this.MaxStepsActualDisplayString = this.MaxStepsString + this.NumOfSolutionSteps;
                this.MaxStepsX = this.myG.getFontMetrics().stringWidth(this.MaxStepsActualDisplayString) / 2;
                this.MaxStepsX = this.centerleftinfoareapixellength - this.MaxStepsX;
                this.offGrfx.drawString(this.MaxStepsActualDisplayString, this.MaxStepsX, 120);
                this.StepsTakenActualDisplayString = this.StepsTakenString + this.CurrentNumOfSteps;
                this.StepsTakenX = this.myG.getFontMetrics().stringWidth(this.StepsTakenActualDisplayString) / 2;
                this.StepsTakenX = this.centerleftinfoareapixellength - this.StepsTakenX;
                this.offGrfx.drawString(this.StepsTakenActualDisplayString, this.StepsTakenX, 135);
            }
            for (int i = 1; i <= this.playfieldcelllength - 1; ++i) {
                for (int j = 1; j <= this.playfieldcelllength - 1; ++j) {
                    if ((i != 1 || j != 1) && (i != this.playfieldcelllength - 1 || j != 1) && (i != 1 || j != this.playfieldcelllength - 1) && (i != this.playfieldcelllength - 1 || j != this.playfieldcelllength - 1)) {
                        this.offGrfx.drawImage(this.CellImage, this.cellxstart[i][j], this.cellystart[i][j], this);
                    }
                }
            }
            for (int k = 1; k <= this.playfieldcelllength; ++k) {
                for (int l = 1; l <= this.playfieldcelllength; ++l) {
                    if (this.ballexist[k][l]) {
                        this.SetColorForSource(k, l);
                        this.offGrfx.fillRect(this.cellxstart[k][l] + this.spacebetweenlineNball + 2, this.cellystart[k][l] + this.spacebetweenlineNball + 2, this.paintedcellsize - 3, this.paintedcellsize - 3);
                    }
                    if (this.ballexisttarget[k][l]) {
                        this.SetColorForTarget(k, l);
                        this.offGrfx.drawRect(this.cellxstart[k][l] + 1, this.cellystart[k][l] + 1, this.innercellsize - 5, this.innercellsize - 5);
                        this.offGrfx.drawRect(this.cellxstart[k][l] + 2, this.cellystart[k][l] + 2, this.innercellsize - 7, this.innercellsize - 7);
                    }
                }
            }
            if (this.Solving) {
                this.offGrfx.setColor(Color.yellow);
            }
            else if (this.InStageClearScreen) {
                this.offGrfx.setColor(Color.yellow);
            }
            else {
                this.offGrfx.setColor(Color.yellow);
            }
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx + 1][this.activecellmarky] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 1][this.activecellmarky] - 1);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky] - 2, this.cellystart[this.activecellmarkx][this.activecellmarky] - 2, this.cellxstart[this.activecellmarkx + 1][this.activecellmarky] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx + 1][this.activecellmarky] - 2);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky] - 3, this.cellxstart[this.activecellmarkx + 1][this.activecellmarky] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 1][this.activecellmarky] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky + 4] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky + 4] - 1, this.cellxstart[this.activecellmarkx + 1][this.activecellmarky + 4] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 1][this.activecellmarky + 4] - 1);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky + 4] - 2, this.cellystart[this.activecellmarkx][this.activecellmarky + 4] - 2, this.cellxstart[this.activecellmarkx + 1][this.activecellmarky + 4] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx + 1][this.activecellmarky + 4] - 2);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky + 4] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky + 4] - 3, this.cellxstart[this.activecellmarkx + 1][this.activecellmarky + 4] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 1][this.activecellmarky + 4] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky] - 2, this.cellystart[this.activecellmarkx + 2][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] - 2, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky] - 3, this.cellystart[this.activecellmarkx + 2][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] - 3, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 6);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 6);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 3, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 3, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 6);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky] - 2, this.cellystart[this.activecellmarkx][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx][this.activecellmarky + 1] - 2, this.cellystart[this.activecellmarkx][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky] - 3, this.cellystart[this.activecellmarkx][this.activecellmarky] - 1, this.cellxstart[this.activecellmarkx][this.activecellmarky + 1] - 3, this.cellystart[this.activecellmarkx][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx][this.activecellmarky + 3] + this.cellpixellength - 6);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx][this.activecellmarky + 3] + this.cellpixellength - 6);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx][this.activecellmarky + 3] - 3, this.cellystart[this.activecellmarkx][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx][this.activecellmarky + 3] - 3, this.cellystart[this.activecellmarkx][this.activecellmarky + 3] + this.cellpixellength - 6);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 1);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] - 2, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 2, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 2);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 3, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] - 2, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 2, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 2);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 3, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 2, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 2);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 3, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx + 2][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 1, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 1);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 2, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 2);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] - 1, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] - 2, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] - 2, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 1] - 3, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] + this.cellpixellength - 4, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 4, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 5, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            this.offGrfx.drawLine(this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 1] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 1] - 1, this.cellxstart[this.activecellmarkx + 2][this.activecellmarky + 3] + this.cellpixellength - 6, this.cellystart[this.activecellmarkx - 1][this.activecellmarky + 3] - 3);
            if (this.DEBUGFLAG) {
                this.offGrfx.setColor(Color.yellow);
                this.offGrfx.drawString("DEBUG flag is on. Displaying XY: " + this.activecellmarkx + ", " + this.activecellmarky, 150, 30);
                this.offGrfx.drawString("playfieldcelllength = " + this.playfieldcelllength, 150, 40);
                this.offGrfx.drawString("PlayLevel=" + this.PlayLevel + "     PlayLevelID=" + this.PlayLevelID, 150, 50);
            }
            if (this.BETAFLAG) {
                this.offGrfx.setColor(Color.white);
                this.offGrfx.drawString("This product is BETA. Please delete this", 150, 258);
                this.offGrfx.drawString("and download Release version from:", 150, 269);
                this.offGrfx.drawString(" http://home.earthlink.net/~jsurfer", 150, 280);
            }
            if (this.DEBUGSHORTSTAGE) {
                this.offGrfx.setColor(Color.yellow);
                this.offGrfx.drawString("DEBUGSHORTSTAGE flag is on.", 150, 20);
            }
            this.offGrfx.setColor(Color.lightGray);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.MessageInPlayField1) / 2;
            this.DescX = this.playfieldcenterx - this.DescX;
            this.offGrfx.drawString(this.MessageInPlayField1, this.DescX, 253);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.MessageInPlayField2) / 2;
            this.DescX = this.playfieldcenterx - this.DescX;
            this.offGrfx.drawString(this.MessageInPlayField2, this.DescX, 265);
            this.DescX = this.myG.getFontMetrics().stringWidth(this.MessageInPlayField3) / 2;
            this.DescX = this.playfieldcenterx - this.DescX;
            this.offGrfx.drawString(this.MessageInPlayField3, this.DescX, 277);
            if (this.RotatingRight) {
                for (int n = 1; n <= this.playfieldcelllength; ++n) {
                    for (int n2 = 1; n2 <= this.playfieldcelllength; ++n2) {
                        if ((n == this.activecellmarkx && n2 == this.activecellmarky) || (n == this.activecellmarkx + 1 && n2 == this.activecellmarky) || (n == this.activecellmarkx + 2 && n2 == this.activecellmarky + 1) || (n == this.activecellmarkx + 2 && n2 == this.activecellmarky + 2) || (n == this.activecellmarkx + 1 && n2 == this.activecellmarky + 3) || (n == this.activecellmarkx && n2 == this.activecellmarky + 3) || (n == this.activecellmarkx - 1 && n2 == this.activecellmarky + 2) || (n == this.activecellmarkx - 1 && n2 == this.activecellmarky + 1)) {
                            this.offGrfx.setColor(this.getBackground());
                            this.offGrfx.fillRect(this.cellxstart[n][n2], this.cellystart[n][n2], this.cellpixellength - 6, this.cellpixellength - 6);
                        }
                    }
                }
                for (int n3 = 1; n3 <= this.playfieldcelllength; ++n3) {
                    for (int n4 = 1; n4 <= this.playfieldcelllength; ++n4) {
                        if (n3 == this.activecellmarkx && n4 == this.activecellmarky) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4] + this.RotatingStepCurrent, this.cellystart[n3][n4], this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] + 3 + this.RotatingStepCurrent + 1, this.cellystart[n3][n4] + 3 + 1, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx + 1 && n4 == this.activecellmarky) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4] + this.RotatingStepCurrent, this.cellystart[n3][n4] + this.RotatingStepCurrent, this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] + 3 + this.RotatingStepCurrent + 1, this.cellystart[n3][n4] + this.RotatingStepCurrent + 3 + 1, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx + 2 && n4 == this.activecellmarky + 1) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4], this.cellystart[n3][n4] + this.RotatingStepCurrent, this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] + 4, this.cellystart[n3][n4] + this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx + 2 && n4 == this.activecellmarky + 2) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4] - this.RotatingStepCurrent, this.cellystart[n3][n4] + this.RotatingStepCurrent, this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] - this.RotatingStepCurrent + 4, this.cellystart[n3][n4] + this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx + 1 && n4 == this.activecellmarky + 3) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4] - this.RotatingStepCurrent, this.cellystart[n3][n4], this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] - this.RotatingStepCurrent + 4, this.cellystart[n3][n4] + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx && n4 == this.activecellmarky + 3) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4] - this.RotatingStepCurrent, this.cellystart[n3][n4] - this.RotatingStepCurrent, this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] - this.RotatingStepCurrent + 4, this.cellystart[n3][n4] - this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx - 1 && n4 == this.activecellmarky + 2) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4], this.cellystart[n3][n4] - this.RotatingStepCurrent, this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] + 4, this.cellystart[n3][n4] - this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n3 == this.activecellmarkx - 1 && n4 == this.activecellmarky + 1) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n3][n4] + this.RotatingStepCurrent, this.cellystart[n3][n4] - this.RotatingStepCurrent, this);
                            if (this.ballexist[n3][n4]) {
                                this.SetColorForSource(n3, n4);
                                this.offGrfx.fillRect(this.cellxstart[n3][n4] + this.RotatingStepCurrent + 4, this.cellystart[n3][n4] - this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                    }
                }
                for (int n5 = 1; n5 <= this.playfieldcelllength; ++n5) {
                    for (int n6 = 1; n6 <= this.playfieldcelllength; ++n6) {
                        if (this.ballexisttarget[n5][n6]) {
                            this.SetColorForTarget(n5, n6);
                            this.offGrfx.drawRect(this.cellxstart[n5][n6] + 1, this.cellystart[n5][n6] + 1, this.innercellsize - 5, this.innercellsize - 5);
                            this.offGrfx.drawRect(this.cellxstart[n5][n6] + 2, this.cellystart[n5][n6] + 2, this.innercellsize - 7, this.innercellsize - 7);
                        }
                    }
                }
            }
            else if (this.RotatingLeft) {
                for (int n7 = 1; n7 <= this.playfieldcelllength; ++n7) {
                    for (int n8 = 1; n8 <= this.playfieldcelllength; ++n8) {
                        if ((n7 == this.activecellmarkx && n8 == this.activecellmarky) || (n7 == this.activecellmarkx + 1 && n8 == this.activecellmarky) || (n7 == this.activecellmarkx + 2 && n8 == this.activecellmarky + 1) || (n7 == this.activecellmarkx + 2 && n8 == this.activecellmarky + 2) || (n7 == this.activecellmarkx + 1 && n8 == this.activecellmarky + 3) || (n7 == this.activecellmarkx && n8 == this.activecellmarky + 3) || (n7 == this.activecellmarkx - 1 && n8 == this.activecellmarky + 2) || (n7 == this.activecellmarkx - 1 && n8 == this.activecellmarky + 1)) {
                            this.offGrfx.setColor(this.getBackground());
                            this.offGrfx.fillRect(this.cellxstart[n7][n8], this.cellystart[n7][n8], this.cellpixellength - 6, this.cellpixellength - 6);
                        }
                    }
                }
                for (int n9 = 1; n9 <= this.playfieldcelllength; ++n9) {
                    for (int n10 = 1; n10 <= this.playfieldcelllength; ++n10) {
                        if (n9 == this.activecellmarkx && n10 == this.activecellmarky) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10] - this.RotatingStepCurrent, this.cellystart[n9][n10] + this.RotatingStepCurrent, this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] - this.RotatingStepCurrent + 4, this.cellystart[n9][n10] + this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx + 1 && n10 == this.activecellmarky) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10] - this.RotatingStepCurrent, this.cellystart[n9][n10], this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] - this.RotatingStepCurrent + 4, this.cellystart[n9][n10] + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx + 2 && n10 == this.activecellmarky + 1) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10] - this.RotatingStepCurrent, this.cellystart[n9][n10] - this.RotatingStepCurrent, this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] - this.RotatingStepCurrent + 4, this.cellystart[n9][n10] - this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx + 2 && n10 == this.activecellmarky + 2) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10], this.cellystart[n9][n10] - this.RotatingStepCurrent, this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] + 4, this.cellystart[n9][n10] - this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx + 1 && n10 == this.activecellmarky + 3) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10] + this.RotatingStepCurrent, this.cellystart[n9][n10] - this.RotatingStepCurrent, this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] + this.RotatingStepCurrent + 4, this.cellystart[n9][n10] - this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx && n10 == this.activecellmarky + 3) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10] + this.RotatingStepCurrent, this.cellystart[n9][n10], this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] + this.RotatingStepCurrent + 4, this.cellystart[n9][n10] + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx - 1 && n10 == this.activecellmarky + 2) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10] + this.RotatingStepCurrent, this.cellystart[n9][n10] + this.RotatingStepCurrent, this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] + this.RotatingStepCurrent + 4, this.cellystart[n9][n10] + this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                        else if (n9 == this.activecellmarkx - 1 && n10 == this.activecellmarky + 1) {
                            this.offGrfx.drawImage(this.CellImage, this.cellxstart[n9][n10], this.cellystart[n9][n10] + this.RotatingStepCurrent, this);
                            if (this.ballexist[n9][n10]) {
                                this.SetColorForSource(n9, n10);
                                this.offGrfx.fillRect(this.cellxstart[n9][n10] + 4, this.cellystart[n9][n10] + this.RotatingStepCurrent + 4, this.paintedcellsize - 3, this.paintedcellsize - 3);
                            }
                        }
                    }
                }
                for (int n11 = 1; n11 <= this.playfieldcelllength; ++n11) {
                    for (int n12 = 1; n12 <= this.playfieldcelllength; ++n12) {
                        if (this.ballexisttarget[n11][n12]) {
                            this.SetColorForTarget(n11, n12);
                            this.offGrfx.drawRect(this.cellxstart[n11][n12] + 1, this.cellystart[n11][n12] + 1, this.innercellsize - 5, this.innercellsize - 5);
                            this.offGrfx.drawRect(this.cellxstart[n11][n12] + 2, this.cellystart[n11][n12] + 2, this.innercellsize - 7, this.innercellsize - 7);
                        }
                    }
                }
            }
            else if (this.InStageClearScreen) {
                this.offGrfx.setColor(Color.green);
                this.offGrfx.drawString("S", this.DGSX, this.DGSY1);
                this.offGrfx.drawString("T", this.DGSX + this.DGSSpace, this.DGSY2);
                this.offGrfx.drawString("A", this.DGSX + this.DGSSpace * 2, this.DGSY3);
                this.offGrfx.drawString("G", this.DGSX + this.DGSSpace * 3, this.DGSY4);
                this.offGrfx.drawString("E", this.DGSX + this.DGSSpace * 4, this.DGSY5);
                this.offGrfx.drawString("C", this.DGSX + this.DGSSpace * 6, this.DGSY6);
                this.offGrfx.drawString("L", this.DGSX + this.DGSSpace * 7, this.DGSY7);
                this.offGrfx.drawString("E", this.DGSX + this.DGSSpace * 8, this.DGSY8);
                this.offGrfx.drawString("A", this.DGSX + this.DGSSpace * 9, this.DGSY9);
                this.offGrfx.drawString("R", this.DGSX + this.DGSSpace * 10, this.DGSY10);
                this.offGrfx.drawString("!", this.DGSX + this.DGSSpace * 11, this.DGSY11);
                this.offGrfx.setColor(Color.green);
                this.offGrfx.drawString("C", this.DGSX, this.DGSY1 + 220);
                this.offGrfx.drawString("l", this.DGSX + this.DGSSpace, this.DGSY2 + 220);
                this.offGrfx.drawString("i", this.DGSX + this.DGSSpace * 2, this.DGSY3 + 220);
                this.offGrfx.drawString("c", this.DGSX + this.DGSSpace * 3, this.DGSY4 + 220);
                this.offGrfx.drawString("k", this.DGSX + this.DGSSpace * 4, this.DGSY5 + 220);
                this.offGrfx.drawString("m", this.DGSX + this.DGSSpace * 6, this.DGSY6 + 220);
                this.offGrfx.drawString("o", this.DGSX + this.DGSSpace * 7 + 3, this.DGSY7 + 220);
                this.offGrfx.drawString("u", this.DGSX + this.DGSSpace * 8, this.DGSY8 + 220);
                this.offGrfx.drawString("s", this.DGSX + this.DGSSpace * 9, this.DGSY9 + 220);
                this.offGrfx.drawString("e", this.DGSX + this.DGSSpace * 10, this.DGSY10 + 220);
                this.LDGSY1 = this.DGSY1;
                this.LDGSY2 = this.DGSY2;
                this.LDGSY3 = this.DGSY3;
                this.LDGSY4 = this.DGSY4;
                this.LDGSY5 = this.DGSY5;
                this.LDGSY6 = this.DGSY6;
                this.LDGSY7 = this.DGSY7;
                this.LDGSY8 = this.DGSY8;
                this.LDGSY9 = this.DGSY9;
                this.LDGSY10 = this.DGSY10;
                this.LDGSY11 = this.DGSY11;
                this.DGSY1 += this.DGSInc1;
                if (this.DGSY1 == this.Hi1) {
                    this.DGSInc1 *= -1;
                }
                else if (this.DGSY1 == this.LowAll) {
                    this.DGSInc1 *= -1;
                }
                this.DGSY2 += this.DGSInc2;
                if (this.DGSY2 == this.Hi2) {
                    this.DGSInc2 *= -1;
                }
                else if (this.DGSY2 == this.LowAll) {
                    this.DGSInc2 *= -1;
                }
                this.DGSY3 += this.DGSInc3;
                if (this.DGSY3 == this.Hi3) {
                    this.DGSInc3 *= -1;
                }
                else if (this.DGSY3 == this.LowAll) {
                    this.DGSInc3 *= -1;
                }
                this.DGSY4 += this.DGSInc4;
                if (this.DGSY4 == this.Hi4) {
                    this.DGSInc4 *= -1;
                }
                else if (this.DGSY4 == this.LowAll) {
                    this.DGSInc4 *= -1;
                }
                this.DGSY5 += this.DGSInc5;
                if (this.DGSY5 == this.Hi5) {
                    this.DGSInc5 *= -1;
                }
                else if (this.DGSY5 == this.LowAll) {
                    this.DGSInc5 *= -1;
                }
                this.DGSY6 += this.DGSInc6;
                if (this.DGSY6 == this.Hi6) {
                    this.DGSInc6 *= -1;
                }
                else if (this.DGSY6 == this.LowAll) {
                    this.DGSInc6 *= -1;
                }
                this.DGSY7 += this.DGSInc7;
                if (this.DGSY7 == this.Hi7) {
                    this.DGSInc7 *= -1;
                }
                else if (this.DGSY7 == this.LowAll) {
                    this.DGSInc7 *= -1;
                }
                this.DGSY8 += this.DGSInc8;
                if (this.DGSY8 == this.Hi8) {
                    this.DGSInc8 *= -1;
                }
                else if (this.DGSY8 == this.LowAll) {
                    this.DGSInc8 *= -1;
                }
                this.DGSY9 += this.DGSInc9;
                if (this.DGSY9 == this.Hi9) {
                    this.DGSInc9 *= -1;
                }
                else if (this.DGSY9 == this.LowAll) {
                    this.DGSInc9 *= -1;
                }
                this.DGSY10 += this.DGSInc10;
                if (this.DGSY10 == this.Hi10) {
                    this.DGSInc10 *= -1;
                }
                else if (this.DGSY10 == this.LowAll) {
                    this.DGSInc10 *= -1;
                }
                this.DGSY11 += this.DGSInc11;
                if (this.DGSY11 == this.Hi11) {
                    this.DGSInc11 *= -1;
                }
                else if (this.DGSY11 == this.LowAll) {
                    this.DGSInc11 *= -1;
                }
            }
        }
        else if (this.LoadingImages) {
            this.offGrfx.setColor(Color.green);
            this.offGrfx.drawString(this.GameTitle + " " + this.VersionNum, 5, 30);
            this.offGrfx.drawString(this.LoadingImagesString, 5, 50);
            this.offGrfx.drawLine(5, 80, 5 + this.StatusBarLength, 80);
            this.offGrfx.drawLine(5 + this.StatusBarLength, 80, 5 + this.StatusBarLength, 90);
            this.offGrfx.drawLine(5 + this.StatusBarLength, 90, 5, 90);
            this.offGrfx.fillRect(5, 80, this.CurrentStatusBarLength, 10);
        }
        graphics.drawImage(this.offImage, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(this.f);
        graphics.setColor(new Color(255, 255, 255));
    }
    
    public void LetterPutPassword(final char c) {
        if (this.CursorPWD == 1) {
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
        }
        else if (this.CursorPWD == 2) {
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
        }
        else if (this.CursorPWD == 3) {
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
        }
        else if (this.CursorPWD == 4) {
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
        }
        else if (this.CursorPWD == 5) {
            ++this.CursorPWD;
            if (this.PasswordEnteredString.length() == 5) {
                this.PasswordEnteredString.replace(this.PasswordEnteredString.charAt(5), c);
            }
            else {
                this.PasswordEnteredString += c;
            }
        }
        else if (this.CursorPWD == 6) {}
    }
    
    public void CheckPassword() {
        this.activecellmarkx = 2;
        this.activecellmarky = 1;
        this.CurrentStage = 1;
        this.NormalPlay = true;
        for (int i = 1; i <= this.MAXStageNumNormal + this.NumOfSpecialStages; ++i) {
            if (this.PasswordEnteredString.equals(this.NormalPasswordArray[i])) {
                this.CurrentStage = i;
                this.NormalPlay = true;
                this.NormalPlusPlay = false;
            }
        }
        for (int j = 1; j <= this.MAXStageNumNormalPlus + this.NumOfSpecialStages; ++j) {
            if (this.PasswordEnteredString.equals(this.NormalPlusPasswordArray[j])) {
                this.CurrentStage = j;
                this.NormalPlay = false;
                this.NormalPlusPlay = true;
            }
        }
        if (this.PasswordEnteredString.equals(this.SpecialPassword)) {
            if (this.AllInOneColor) {
                this.AllInOneColor = false;
            }
            else {
                this.AllInOneColor = true;
            }
        }
        if (this.DEBUGEXTRAPWD) {
            if (this.PasswordEnteredString.equals("XA")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 1;
            }
            else if (this.PasswordEnteredString.equals("XB")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 2;
            }
            else if (this.PasswordEnteredString.equals("XC")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 3;
            }
            else if (this.PasswordEnteredString.equals("XD")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 4;
            }
            else if (this.PasswordEnteredString.equals("XE")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 5;
            }
            else if (this.PasswordEnteredString.equals("XF")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 6;
            }
            else if (this.PasswordEnteredString.equals("XG")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 7;
            }
            else if (this.PasswordEnteredString.equals("XH")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 8;
            }
            else if (this.PasswordEnteredString.equals("XI")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 9;
            }
            else if (this.PasswordEnteredString.equals("XJ")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 10;
            }
            else if (this.PasswordEnteredString.equals("XK")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 11;
            }
            else if (this.PasswordEnteredString.equals("XL")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 12;
            }
            else if (this.PasswordEnteredString.equals("XM")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 13;
            }
            else if (this.PasswordEnteredString.equals("XN")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 14;
            }
            else if (this.PasswordEnteredString.equals("XO")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 15;
            }
            else if (this.PasswordEnteredString.equals("XP")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = true;
                this.ExtraPlusPlay = false;
                this.CurrentStage = 16;
            }
            else if (this.PasswordEnteredString.equals("YA")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 1;
            }
            else if (this.PasswordEnteredString.equals("YB")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 2;
            }
            else if (this.PasswordEnteredString.equals("YC")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 3;
            }
            else if (this.PasswordEnteredString.equals("YD")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 4;
            }
            else if (this.PasswordEnteredString.equals("YE")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 5;
            }
            else if (this.PasswordEnteredString.equals("YF")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 6;
            }
            else if (this.PasswordEnteredString.equals("YG")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 7;
            }
            else if (this.PasswordEnteredString.equals("YH")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 8;
            }
            else if (this.PasswordEnteredString.equals("YI")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 9;
            }
            else if (this.PasswordEnteredString.equals("YJ")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 10;
            }
            else if (this.PasswordEnteredString.equals("YK")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 11;
            }
            else if (this.PasswordEnteredString.equals("YL")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 12;
            }
            else if (this.PasswordEnteredString.equals("YM")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 13;
            }
            else if (this.PasswordEnteredString.equals("YN")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 14;
            }
            else if (this.PasswordEnteredString.equals("YO")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 15;
            }
            else if (this.PasswordEnteredString.equals("YP")) {
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = true;
                this.CurrentStage = 16;
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.InPasswordScreen) {
            switch (n) {
                case 97: {
                    this.LetterPutPassword('A');
                    break;
                }
                case 65: {
                    this.LetterPutPassword('A');
                    break;
                }
                case 98: {
                    this.LetterPutPassword('B');
                    break;
                }
                case 66: {
                    this.LetterPutPassword('B');
                    break;
                }
                case 99: {
                    this.LetterPutPassword('C');
                    break;
                }
                case 67: {
                    this.LetterPutPassword('C');
                    break;
                }
                case 100: {
                    this.LetterPutPassword('D');
                    break;
                }
                case 68: {
                    this.LetterPutPassword('D');
                    break;
                }
                case 101: {
                    this.LetterPutPassword('E');
                    break;
                }
                case 69: {
                    this.LetterPutPassword('E');
                    break;
                }
                case 102: {
                    this.LetterPutPassword('F');
                    break;
                }
                case 70: {
                    this.LetterPutPassword('F');
                    break;
                }
                case 103: {
                    this.LetterPutPassword('G');
                    break;
                }
                case 71: {
                    this.LetterPutPassword('G');
                    break;
                }
                case 104: {
                    this.LetterPutPassword('H');
                    break;
                }
                case 72: {
                    this.LetterPutPassword('H');
                    break;
                }
                case 105: {
                    this.LetterPutPassword('I');
                    break;
                }
                case 73: {
                    this.LetterPutPassword('I');
                    break;
                }
                case 106: {
                    this.LetterPutPassword('J');
                    break;
                }
                case 74: {
                    this.LetterPutPassword('J');
                    break;
                }
                case 107: {
                    this.LetterPutPassword('K');
                    break;
                }
                case 75: {
                    this.LetterPutPassword('K');
                    break;
                }
                case 108: {
                    this.LetterPutPassword('L');
                    break;
                }
                case 76: {
                    this.LetterPutPassword('L');
                    break;
                }
                case 109: {
                    this.LetterPutPassword('M');
                    break;
                }
                case 77: {
                    this.LetterPutPassword('M');
                    break;
                }
                case 110: {
                    this.LetterPutPassword('N');
                    break;
                }
                case 78: {
                    this.LetterPutPassword('N');
                    break;
                }
                case 111: {
                    this.LetterPutPassword('O');
                    break;
                }
                case 79: {
                    this.LetterPutPassword('O');
                    break;
                }
                case 112: {
                    this.LetterPutPassword('P');
                    break;
                }
                case 80: {
                    this.LetterPutPassword('P');
                    break;
                }
                case 113: {
                    this.LetterPutPassword('Q');
                    break;
                }
                case 81: {
                    this.LetterPutPassword('Q');
                    break;
                }
                case 114: {
                    this.LetterPutPassword('R');
                    break;
                }
                case 82: {
                    this.LetterPutPassword('R');
                    break;
                }
                case 115: {
                    this.LetterPutPassword('S');
                    break;
                }
                case 83: {
                    this.LetterPutPassword('S');
                    break;
                }
                case 116: {
                    this.LetterPutPassword('T');
                    break;
                }
                case 84: {
                    this.LetterPutPassword('T');
                    break;
                }
                case 117: {
                    this.LetterPutPassword('U');
                    break;
                }
                case 85: {
                    this.LetterPutPassword('U');
                    break;
                }
                case 118: {
                    this.LetterPutPassword('V');
                    break;
                }
                case 86: {
                    this.LetterPutPassword('V');
                    break;
                }
                case 119: {
                    this.LetterPutPassword('W');
                    break;
                }
                case 87: {
                    this.LetterPutPassword('W');
                    break;
                }
                case 120: {
                    this.LetterPutPassword('X');
                    break;
                }
                case 88: {
                    this.LetterPutPassword('X');
                    break;
                }
                case 121: {
                    this.LetterPutPassword('Y');
                    break;
                }
                case 89: {
                    this.LetterPutPassword('Y');
                    break;
                }
                case 122: {
                    this.LetterPutPassword('Z');
                    break;
                }
                case 90: {
                    this.LetterPutPassword('Z');
                    break;
                }
                case 8: {
                    if (this.CursorPWD == 1) {
                        break;
                    }
                    if (this.CursorPWD == 2) {
                        --this.CursorPWD;
                        this.PasswordEnteredString = "";
                        break;
                    }
                    if (this.CursorPWD == 3) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 1);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                        break;
                    }
                    if (this.CursorPWD == 4) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 2);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                        break;
                    }
                    if (this.CursorPWD == 5) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 3);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                        break;
                    }
                    if (this.CursorPWD == 6) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 4);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                        break;
                    }
                    break;
                }
                case 10: {
                    this.MouseMovementLocked = true;
                    this.CheckPassword();
                    this.PlayButtonPressed = true;
                    this.InPasswordScreen = false;
                    break;
                }
            }
        }
        this.repaint();
        return true;
    }
    
    public void reinitMessages() {
        this.MessageInPlayField1 = " ";
        this.MessageInPlayField2 = " ";
        this.MessageInPlayField3 = " ";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.reinitMessages();
        if (this.WaitingForMouseClick) {
            this.WaitingForMouseClick = false;
            this.InDisplayStepReachedMaxScreen = false;
        }
        else if (this.InNormalPlayAllClearedScreen || this.InNormalPlusPlayAllClearedScreen || this.InExtraPlayAllClearedScreen || this.InExtraPlusPlayAllClearedScreen) {
            this.InNormalPlayAllClearedScreen = false;
            this.InNormalPlusPlayAllClearedScreen = false;
            this.InExtraPlayAllClearedScreen = false;
            this.InExtraPlusPlayAllClearedScreen = false;
        }
        else if (this.InStageClearScreen) {
            if (this.ShowPassword && !this.LastStage && !this.SolvePressedOnce) {
                this.InShowPasswordScreen = true;
                this.InStageClearScreen = false;
            }
            else {
                this.InStageClearScreen = false;
                this.MouseMovementLocked = false;
            }
        }
        else if (this.InShowPasswordScreen) {
            this.InShowPasswordScreen = false;
            this.MouseMovementLocked = false;
        }
        else if (this.InTitleScreen) {
            if (n > this.NormalImageXStart && n2 > this.NormalImageYStart && n < this.NormalImageXStart + this.BannerWidth && n2 < this.NormalImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InNormalExplainScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.EnterPasswordImageXStart && n2 > this.EnterPasswordImageYStart && n < this.EnterPasswordImageXStart + this.BannerWidth && n2 < this.EnterPasswordImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InPasswordScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.NormalPlusImageXStart && n2 > this.NormalPlusImageYStart && n < this.NormalPlusImageXStart + this.BannerWidth && n2 < this.NormalPlusImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InNormalPlusExplainScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.ExtraImageXStart && n2 > this.ExtraImageYStart && n < this.ExtraImageXStart + this.BannerWidth && n2 < this.ExtraImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InExtraExplainScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.ExtraPlusImageXStart && n2 > this.ExtraPlusImageYStart && n < this.ExtraPlusImageXStart + this.BannerWidth && n2 < this.ExtraPlusImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InExtraPlusExplainScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.EndlessImageXStart && n2 > this.EndlessImageYStart && n < this.EndlessImageXStart + this.BannerWidth && n2 < this.EndlessImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InEndlessExplainScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.EndlessPlusImageXStart && n2 > this.EndlessPlusImageYStart && n < this.EndlessPlusImageXStart + this.BannerWidth && n2 < this.EndlessPlusImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InEndlessPlusExplainScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.HowToPlayImageXStart && n2 > this.HowToPlayImageYStart && n < this.HowToPlayImageXStart + this.BannerWidth && n2 < this.HowToPlayImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InHowToPlayScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.AboutImageXStart && n2 > this.AboutImageYStart && n < this.AboutImageXStart + this.BannerWidth && n2 < this.AboutImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InAboutScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            else if (n > this.LegalImageXStart && n2 > this.LegalImageYStart && n < this.LegalImageXStart + this.BannerWidth && n2 < this.LegalImageYStart + this.BannerHeight) {
                this.InTitleScreen = false;
                this.InLegalScreen = true;
                this.InitializeButtonBoolean();
                this.repaint();
            }
        }
        else if (this.InNormalExplainScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.PlayButtonPressed = true;
                this.InNormalExplainScreen = false;
                this.NormalPlay = true;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InNormalExplainScreen = false;
                this.repaint();
            }
        }
        else if (this.InPasswordScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.CheckPassword();
                this.PlayButtonPressed = true;
                this.InPasswordScreen = false;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InPasswordScreen = false;
                this.repaint();
            }
            else if (n >= this.ppanelx + 2 && n <= this.ppanelx + 14 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('A');
            }
            else if (n >= this.ppanelx + 23 && n <= this.ppanelx + 34 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('B');
            }
            else if (n >= this.ppanelx + 41 && n <= this.ppanelx + 53 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('C');
            }
            else if (n >= this.ppanelx + 61 && n <= this.ppanelx + 73 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('D');
            }
            else if (n >= this.ppanelx + 82 && n <= this.ppanelx + 94 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('E');
            }
            else if (n >= this.ppanelx + 101 && n <= this.ppanelx + 113 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('F');
            }
            else if (n >= this.ppanelx + 119 && n <= this.ppanelx + 132 && n2 >= this.ppanely + 0 && n2 <= this.ppanely + 15) {
                this.LetterPutPassword('G');
            }
            else if (n >= this.ppanelx + 0 && n <= this.ppanelx + 13 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('H');
            }
            else if (n >= this.ppanelx + 21 && n <= this.ppanelx + 31 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('I');
            }
            else if (n >= this.ppanelx + 38 && n <= this.ppanelx + 51 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('J');
            }
            else if (n >= this.ppanelx + 59 && n <= this.ppanelx + 71 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('K');
            }
            else if (n >= this.ppanelx + 78 && n <= this.ppanelx + 89 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('L');
            }
            else if (n >= this.ppanelx + 95 && n <= this.ppanelx + 111 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('M');
            }
            else if (n >= this.ppanelx + 119 && n <= this.ppanelx + 133 && n2 >= this.ppanely + 21 && n2 <= this.ppanely + 37) {
                this.LetterPutPassword('N');
            }
            else if (n >= this.ppanelx && n <= this.ppanelx + 12 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('O');
            }
            else if (n >= this.ppanelx + 20 && n <= this.ppanelx + 30 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('P');
            }
            else if (n >= this.ppanelx + 38 && n <= this.ppanelx + 55 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('Q');
            }
            else if (n >= this.ppanelx + 61 && n <= this.ppanelx + 75 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('R');
            }
            else if (n >= this.ppanelx + 81 && n <= this.ppanelx + 93 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('S');
            }
            else if (n >= this.ppanelx + 101 && n <= this.ppanelx + 116 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('T');
            }
            else if (n >= this.ppanelx + 121 && n <= this.ppanelx + 133 && n2 >= this.ppanely + 43 && n2 <= this.ppanely + 59) {
                this.LetterPutPassword('U');
            }
            else if (n >= this.ppanelx + 17 && n <= this.ppanelx + 29 && n2 >= this.ppanely + 65 && n2 <= this.ppanely + 82) {
                this.LetterPutPassword('V');
            }
            else if (n >= this.ppanelx + 37 && n <= this.ppanelx + 57 && n2 >= this.ppanely + 65 && n2 <= this.ppanely + 82) {
                this.LetterPutPassword('W');
            }
            else if (n >= this.ppanelx + 62 && n <= this.ppanelx + 75 && n2 >= this.ppanely + 65 && n2 <= this.ppanely + 82) {
                this.LetterPutPassword('X');
            }
            else if (n >= this.ppanelx + 83 && n <= this.ppanelx + 96 && n2 >= this.ppanely + 65 && n2 <= this.ppanely + 82) {
                this.LetterPutPassword('Y');
            }
            else if (n >= this.ppanelx + 103 && n <= this.ppanelx + 116 && n2 >= this.ppanely + 65 && n2 <= this.ppanely + 82) {
                this.LetterPutPassword('Z');
            }
            else if (n >= this.ppanelx + 16 && n <= this.ppanelx + 117 && n2 >= this.ppanely + 87 && n2 <= this.ppanely + 107) {
                if (this.CursorPWD != 1) {
                    if (this.CursorPWD == 2) {
                        --this.CursorPWD;
                        this.PasswordEnteredString = "";
                    }
                    else if (this.CursorPWD == 3) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 1);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                    }
                    else if (this.CursorPWD == 4) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 2);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                    }
                    else if (this.CursorPWD == 5) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 3);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                    }
                    else if (this.CursorPWD == 6) {
                        --this.CursorPWD;
                        this.TempString = this.PasswordEnteredString.substring(0, 4);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = this.TempString;
                    }
                }
            }
        }
        else if (this.InNormalPlusExplainScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.PlayButtonPressed = true;
                this.InNormalPlusExplainScreen = false;
                this.NormalPlusPlay = true;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InNormalPlusExplainScreen = false;
                this.repaint();
            }
        }
        else if (this.InExtraExplainScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.PlayButtonPressed = true;
                this.InExtraExplainScreen = false;
                this.ExtraPlay = true;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InExtraExplainScreen = false;
                this.repaint();
            }
        }
        else if (this.InExtraPlusExplainScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.PlayButtonPressed = true;
                this.InExtraPlusExplainScreen = false;
                this.ExtraPlusPlay = true;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InExtraPlusExplainScreen = false;
                this.repaint();
            }
        }
        else if (this.InEndlessExplainScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.PlayButtonPressed = true;
                this.InEndlessExplainScreen = false;
                this.EndlessPlay = true;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InEndlessExplainScreen = false;
                this.repaint();
            }
        }
        else if (this.InEndlessPlusExplainScreen) {
            if (n > this.PlayButtonImageXStart && n2 > this.PlayButtonImageYStart && n < this.PlayButtonImageXStart + this.ButtonWidth && n2 < this.PlayButtonImageYStart + this.ButtonHeight) {
                this.MouseMovementLocked = true;
                this.PlayButtonPressed = true;
                this.InEndlessPlusExplainScreen = false;
                this.EndlessPlusPlay = true;
            }
            else if (n > this.BackButtonImageXStart && n2 > this.BackButtonImageYStart && n < this.BackButtonImageXStart + this.ButtonWidth && n2 < this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InEndlessPlusExplainScreen = false;
                this.repaint();
            }
        }
        else if (this.InAboutScreen) {
            if (n >= this.centerx - this.ButtonWidth / 2 && n2 >= this.BackButtonImageYStart && n <= this.centerx + this.ButtonWidth / 2 && n2 <= this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InAboutScreen = false;
                this.repaint();
            }
        }
        else if (this.InLegalScreen) {
            if (n >= this.centerx - this.ButtonWidth / 2 && n2 >= this.BackButtonImageYStart && n <= this.centerx + this.ButtonWidth / 2 && n2 <= this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InLegalScreen = false;
                this.repaint();
            }
        }
        else if (this.InHowToPlayScreen) {
            if (n >= this.centerx - this.ButtonWidth / 2 && n2 >= this.BackButtonImageYStart && n <= this.centerx + this.ButtonWidth / 2 && n2 <= this.BackButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InHowToPlayScreen = false;
                this.repaint();
            }
        }
        else if (this.InGamePlayMode && this.Solving) {
            this.MouseClickedOutofAutoSolve = true;
            this.Solving = false;
        }
        else if (this.InGamePlayMode) {
            if (n > this.cellxstart[1][1] + 1 && n < this.cellxstart[this.playfieldcelllength][this.playfieldcelllength] + 1 && n2 > this.cellystart[1][1] + 1 && n2 < this.cellystart[this.playfieldcelllength][this.playfieldcelllength] + 1) {
                if (event.controlDown() || event.metaDown()) {
                    this.MouseMovementLocked = true;
                    this.RightMouseClickedinPlayfield = true;
                }
                else {
                    this.MouseMovementLocked = true;
                    this.LeftMouseClickedinPlayfield = true;
                }
            }
            else if (n > this.QuitButtonImageXStart && n2 > this.QuitButtonImageYStart && n < this.QuitButtonImageXStart + this.ButtonWidth && n2 < this.QuitButtonImageYStart + this.ButtonHeight) {
                this.InTitleScreen = true;
                this.InitializeArrowImageLeftRightBoolean();
                this.InGamePlayMode = false;
                this.gameOver = true;
                this.QuitPressed = true;
            }
            else if (n > this.ResetButtonImageXStart && n2 > this.ResetButtonImageYStart && n < this.ResetButtonImageXStart + this.ButtonWidth && n2 < this.ResetButtonImageYStart + this.ButtonHeight) {
                this.ResetPressed = true;
            }
            else if ((this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay || this.DEBUGSOLVEALWAYS) && n > this.SolveButtonImageXStart && n2 > this.SolveButtonImageYStart && n < this.SolveButtonImageXStart + this.ButtonWidth && n2 < this.SolveButtonImageYStart + this.ButtonHeight) {
                this.SolvePressed = true;
                this.SolvePressedOnce = true;
            }
            else if (n > this.UndoButtonImageXStart && n2 > this.UndoButtonImageYStart && n < this.UndoButtonImageXStart + this.ButtonWidth && n2 < this.UndoButtonImageYStart + this.ButtonHeight) {
                this.UndoPressed = true;
            }
            this.repaint();
        }
        return true;
    }
    
    public void UpdateArrow() {
        this.DisplayArrowImage = true;
        this.CurrentlyInHowToPlayImageBanner = false;
        this.CurrentlyInNormalImageBanner = false;
        this.CurrentlyInNormalPlusImageBanner = false;
        this.CurrentlyInExtraImageBanner = false;
        this.CurrentlyInExtraPlusImageBanner = false;
        this.CurrentlyInEndlessImageBanner = false;
        this.CurrentlyInEndlessPlusImageBanner = false;
        this.CurrentlyInEnterPasswordImageBanner = false;
        this.CurrentlyInAboutImageBanner = false;
        this.CurrentlyInLegalImageBanner = false;
        this.PlusOrMinusOne = 1;
        this.LeftNRightAdd = 0;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.InStageClearScreen && !this.WaitingForMouseClick) {
            this.reinitMessages();
        }
        if (!this.MouseMovementLocked) {
            if (this.InTitleScreen) {
                if (n >= this.HowToPlayImageXStart && n <= this.HowToPlayImageXStart + this.BannerWidth && n2 >= this.HowToPlayImageYStart && n2 <= this.HowToPlayImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInHowToPlayImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInHowToPlayImageBanner = true;
                    }
                }
                else if (n >= this.NormalImageXStart && n <= this.NormalImageXStart + this.BannerWidth && n2 >= this.NormalImageYStart && n2 <= this.NormalImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInNormalImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInNormalImageBanner = true;
                    }
                }
                else if (n >= this.NormalPlusImageXStart && n <= this.NormalPlusImageXStart + this.BannerWidth && n2 >= this.NormalPlusImageYStart && n2 <= this.NormalPlusImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInNormalPlusImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInNormalPlusImageBanner = true;
                    }
                }
                else if (n >= this.ExtraImageXStart && n <= this.ExtraImageXStart + this.BannerWidth && n2 >= this.ExtraImageYStart && n2 <= this.ExtraImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInExtraImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInExtraImageBanner = true;
                    }
                }
                else if (n >= this.ExtraPlusImageXStart && n <= this.ExtraPlusImageXStart + this.BannerWidth && n2 >= this.ExtraPlusImageYStart && n2 <= this.ExtraPlusImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInExtraPlusImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInExtraPlusImageBanner = true;
                    }
                }
                else if (n >= this.EndlessImageXStart && n <= this.EndlessImageXStart + this.BannerWidth && n2 >= this.EndlessImageYStart && n2 <= this.EndlessImageYStart + this.BannerHeight) {
                    this.UpdateArrow();
                    this.CurrentlyInEndlessImageBanner = true;
                }
                else if (n >= this.EndlessPlusImageXStart && n <= this.EndlessPlusImageXStart + this.BannerWidth && n2 >= this.EndlessPlusImageYStart && n2 <= this.EndlessPlusImageYStart + this.BannerHeight) {
                    this.UpdateArrow();
                    this.CurrentlyInEndlessPlusImageBanner = true;
                }
                else if (n >= this.EnterPasswordImageXStart && n <= this.EnterPasswordImageXStart + this.BannerWidth && n2 >= this.EnterPasswordImageYStart && n2 <= this.EnterPasswordImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInEnterPasswordImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInEnterPasswordImageBanner = true;
                    }
                }
                else if (n >= this.AboutImageXStart && n <= this.AboutImageXStart + this.BannerWidth && n2 >= this.AboutImageYStart && n2 <= this.AboutImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInAboutImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInAboutImageBanner = true;
                    }
                }
                else if (n >= this.LegalImageXStart && n <= this.LegalImageXStart + this.BannerWidth && n2 >= this.LegalImageYStart && n2 <= this.LegalImageYStart + this.BannerHeight) {
                    if (!this.CurrentlyInLegalImageBanner) {
                        this.UpdateArrow();
                        this.CurrentlyInLegalImageBanner = true;
                    }
                }
                else {
                    this.CurrentlyInHowToPlayImageBanner = false;
                    this.CurrentlyInNormalImageBanner = false;
                    this.CurrentlyInNormalPlusImageBanner = false;
                    this.CurrentlyInExtraImageBanner = false;
                    this.CurrentlyInExtraPlusImageBanner = false;
                    this.CurrentlyInEndlessImageBanner = false;
                    this.CurrentlyInEndlessPlusImageBanner = false;
                    this.CurrentlyInEnterPasswordImageBanner = false;
                    this.CurrentlyInAboutImageBanner = false;
                    this.CurrentlyInLegalImageBanner = false;
                    this.DisplayArrowImage = false;
                }
            }
            else if (this.InNormalExplainScreen || this.InNormalPlusExplainScreen || this.InExtraExplainScreen || this.InExtraPlusExplainScreen || this.InEndlessExplainScreen || this.InEndlessPlusExplainScreen || this.InPasswordScreen) {
                if (n >= this.PlayButtonImageXStart && n <= this.PlayButtonImageXStart + this.ButtonWidth && n2 >= this.PlayButtonImageYStart && n2 <= this.PlayButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInPlayButtonImage = true;
                    this.CurrentlyInBackButtonImage = false;
                }
                else if (n >= this.BackButtonImageXStart && n <= this.BackButtonImageXStart + this.ButtonWidth && n2 >= this.BackButtonImageYStart && n2 <= this.BackButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInPlayButtonImage = false;
                    this.CurrentlyInBackButtonImage = true;
                }
                else {
                    this.CurrentlyInPlayButtonImage = false;
                    this.CurrentlyInBackButtonImage = false;
                }
            }
            else if (this.InHowToPlayScreen || this.InAboutScreen || this.InLegalScreen) {
                if (n >= this.centerx - this.ButtonWidth / 2 && n <= this.centerx + this.ButtonWidth / 2 && n2 >= this.BackButtonImageYStart && n2 <= this.BackButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInBackButtonImage = true;
                }
                else {
                    this.CurrentlyInBackButtonImage = false;
                }
            }
            else if ((this.InGamePlayMode && !this.Solving && n < this.cellxstart[1][1]) || n2 < this.cellystart[1][1] || n > this.cellxstart[this.playfieldcelllength - 1][this.playfieldcelllength - 1] + this.cellpixellength || n2 > this.cellystart[this.playfieldcelllength - 1][this.playfieldcelllength - 1] + this.cellpixellength) {
                if (this.InGamePlayMode && !this.Solving && n >= this.QuitButtonImageXStart && n <= this.QuitButtonImageXStart + this.ButtonWidth && n2 >= this.QuitButtonImageYStart && n2 <= this.QuitButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInQuitButtonImage = true;
                    this.CurrentlyInResetButtonImage = false;
                    this.CurrentlyInSolveButtonImage = false;
                    this.CurrentlyInUndoButtonImage = false;
                    this.MessageInPlayField1 = this.QuitHelpMsg1;
                    this.MessageInPlayField2 = this.QuitHelpMsg2;
                    this.MessageInPlayField3 = this.QuitHelpMsg3;
                }
                else if (this.InGamePlayMode && !this.Solving && n >= this.ResetButtonImageXStart && n <= this.ResetButtonImageXStart + this.ButtonWidth && n2 >= this.ResetButtonImageYStart && n2 <= this.ResetButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInQuitButtonImage = false;
                    this.CurrentlyInResetButtonImage = true;
                    this.CurrentlyInSolveButtonImage = false;
                    this.CurrentlyInUndoButtonImage = false;
                    this.MessageInPlayField1 = this.ResetHelpMsg1;
                    this.MessageInPlayField2 = this.ResetHelpMsg2;
                    this.MessageInPlayField3 = this.ResetHelpMsg3;
                }
                else if (this.InGamePlayMode && (this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay || this.DEBUGSOLVEALWAYS) && !this.Solving && n >= this.SolveButtonImageXStart && n <= this.SolveButtonImageXStart + this.ButtonWidth && n2 >= this.SolveButtonImageYStart && n2 <= this.SolveButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInQuitButtonImage = false;
                    this.CurrentlyInResetButtonImage = false;
                    this.CurrentlyInSolveButtonImage = true;
                    this.CurrentlyInUndoButtonImage = false;
                    this.MessageInPlayField1 = this.SolveHelpMsg1;
                    this.MessageInPlayField2 = this.SolveHelpMsg2;
                    this.MessageInPlayField3 = this.SolveHelpMsg3;
                }
                else if (this.InGamePlayMode && !this.Solving && n >= this.UndoButtonImageXStart && n <= this.UndoButtonImageXStart + this.ButtonWidth && n2 >= this.UndoButtonImageYStart && n2 <= this.UndoButtonImageYStart + this.ButtonHeight) {
                    this.CurrentlyInQuitButtonImage = false;
                    this.CurrentlyInResetButtonImage = false;
                    this.CurrentlyInSolveButtonImage = false;
                    this.CurrentlyInUndoButtonImage = true;
                    this.MessageInPlayField1 = this.UndoHelpMsg1;
                    this.MessageInPlayField2 = this.UndoHelpMsg2;
                    this.MessageInPlayField3 = this.UndoHelpMsg3;
                }
                else {
                    this.CurrentlyInQuitButtonImage = false;
                    this.CurrentlyInResetButtonImage = false;
                    this.CurrentlyInSolveButtonImage = false;
                    this.CurrentlyInUndoButtonImage = false;
                }
            }
            else if (!this.MouseMovementLocked) {
                if (n >= this.cellxstart[3][3] - this.cellpixellengthhalf + 1 && n <= this.cellxstart[this.playfieldcelllength - 2][this.playfieldcelllength - 2] + this.cellpixellengthhalf + 1 && n2 >= this.cellystart[3][3] - this.cellpixellengthhalf + 1 && n2 <= this.cellystart[this.playfieldcelllength - 2][this.playfieldcelllength - 2] + this.cellpixellengthhalf + 1) {
                    for (int i = 3; i < this.playfieldcelllength - 1; ++i) {
                        for (int j = 3; j <= this.playfieldcelllength - 2; ++j) {
                            if (n > this.cellxstart[i][j] - this.cellpixellengthhalf - 1 && n2 > this.cellystart[i][j] - this.cellpixellengthhalf - 1 && n < this.cellxstart[i][j] + this.cellpixellengthhalf && n2 < this.cellystart[i][j] + this.cellpixellengthhalf) {
                                this.activecellmarkx = i - 1;
                                this.activecellmarky = j - 2;
                            }
                        }
                    }
                    this.InitializeButtonBoolean();
                }
                else if (n > this.cellxstart[1][1] - 1 && n < this.cellxstart[this.playfieldcelllength][this.playfieldcelllength] && n2 > this.cellystart[1][1] - 1 && n2 < this.cellystart[this.playfieldcelllength][this.playfieldcelllength]) {
                    if (n2 <= this.cellystart[3][3] - this.cellpixellengthhalf) {
                        for (int k = 1; k < this.playfieldcelllength; ++k) {
                            if (n > this.cellxstart[k][1] - this.cellpixellengthhalf + 1 && n < this.cellxstart[k][1] + this.cellpixellengthhalf + 1) {
                                if (k == 1 || k == 2) {
                                    this.activecellmarkx = 2;
                                    this.activecellmarky = 1;
                                }
                                else if (k == this.playfieldcelllength || k == this.playfieldcelllength - 1 || k == this.playfieldcelllength - 2) {
                                    this.activecellmarkx = this.playfieldcelllength - 3;
                                    this.activecellmarky = 1;
                                }
                                else {
                                    this.activecellmarkx = k - 1;
                                    this.activecellmarky = 1;
                                }
                            }
                        }
                        this.InitializeButtonBoolean();
                    }
                    else if (n2 >= this.cellystart[this.playfieldcelllength - 2][this.playfieldcelllength - 2] + this.cellpixellengthhalf) {
                        for (int l = 1; l < this.playfieldcelllength; ++l) {
                            if (n > this.cellxstart[l][this.playfieldcelllength - 2] - this.cellpixellengthhalf - 1 && n < this.cellxstart[l][this.playfieldcelllength - 2] + this.cellpixellengthhalf) {
                                if (l == 1 || l == 2) {
                                    this.activecellmarkx = 2;
                                    this.activecellmarky = this.playfieldcelllength - 4;
                                }
                                else if (l == this.playfieldcelllength || l == this.playfieldcelllength - 1 || l == this.playfieldcelllength - 2) {
                                    this.activecellmarkx = this.playfieldcelllength - 3;
                                    this.activecellmarky = this.playfieldcelllength - 4;
                                }
                                else {
                                    this.activecellmarkx = l - 1;
                                    this.activecellmarky = this.playfieldcelllength - 4;
                                }
                            }
                        }
                        this.InitializeButtonBoolean();
                    }
                    else if (n <= this.cellxstart[3][1] - this.cellpixellengthhalf - 1) {
                        for (int n3 = 1; n3 < this.playfieldcelllength; ++n3) {
                            if (n2 > this.cellystart[2][n3] && n2 < this.cellystart[2][n3] + this.cellpixellengthhalf) {
                                if (n3 == 1 || n3 == 2) {
                                    this.activecellmarkx = 2;
                                    this.activecellmarky = 1;
                                }
                                else if (n3 == this.playfieldcelllength || n3 == this.playfieldcelllength - 1) {
                                    this.activecellmarkx = this.playfieldcelllength - 3;
                                    this.activecellmarky = this.playfieldcelllength - 4;
                                }
                                else {
                                    this.activecellmarkx = 2;
                                    this.activecellmarky = n3 - 2;
                                }
                            }
                        }
                        this.InitializeButtonBoolean();
                    }
                    else if (n >= this.cellxstart[this.playfieldcelllength - 2][1] + this.cellpixellengthhalf - 1) {
                        for (int n4 = 1; n4 < this.playfieldcelllength; ++n4) {
                            if (n2 > this.cellystart[this.playfieldcelllength - 2][n4] - this.cellpixellengthhalf - 1 && n2 < this.cellystart[this.playfieldcelllength - 2][n4] + this.cellpixellengthhalf) {
                                if (n4 == 1 || n4 == 2) {
                                    this.activecellmarkx = this.playfieldcelllength - 2;
                                    this.activecellmarky = 1;
                                }
                                else {
                                    this.activecellmarkx = this.playfieldcelllength - 3;
                                    if (n4 - 2 <= this.playfieldcelllength - 4) {
                                        this.activecellmarky = n4 - 2;
                                    }
                                    this.activecellmarky = this.activecellmarky;
                                }
                            }
                        }
                        this.InitializeButtonBoolean();
                    }
                }
                this.repaint();
            }
        }
        return true;
    }
    
    public void DisplayTitle() {
    }
    
    public void ClearScreen() {
        this.myG.setColor(Color.black);
        this.myG.fillRect(0, 0, this.winsizex, this.winsizey);
    }
    
    public void DisplayGameoverScreen() {
    }
    
    public void RotateToRight() {
        if ((this.NormalPlay || this.NormalPlusPlay || this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay) && !this.SilentRotation && !this.UndoPressed && this.CurrentNumOfSteps < this.MAXStepHistory) {
            this.HistoryActiveCellMarkX[this.CurrentNumOfSteps] = this.activecellmarkx;
            this.HistoryActiveCellMarkY[this.CurrentNumOfSteps] = this.activecellmarky;
            this.HistoryLeftRight[this.CurrentNumOfSteps] = 'R';
            ++this.CurrentNumOfSteps;
        }
        this.RotatingRight = true;
        this.RotatingStepCurrent = 0;
        while (this.RotatingRight) {
            try {
                if (!this.SilentRotation) {
                    Thread.sleep(this.delayvalue);
                }
                ++this.RotatingStepCurrent;
                if (!this.SilentRotation) {
                    this.repaint();
                }
                if (this.RotatingStepCurrent < this.cellpixellength - 2) {
                    continue;
                }
                this.RotatingRight = false;
            }
            catch (InterruptedException ex) {
                break;
            }
        }
        this.tempboolean = this.ballexist[this.activecellmarkx][this.activecellmarky];
        this.tempint = this.ballexistcolor[this.activecellmarkx][this.activecellmarky];
        this.ballexist[this.activecellmarkx][this.activecellmarky] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 1];
        this.ballexistcolor[this.activecellmarkx][this.activecellmarky] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1];
        this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 1] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 2];
        this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2];
        this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx][this.activecellmarky + 3];
        this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx][this.activecellmarky + 3];
        this.ballexist[this.activecellmarkx][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky + 3];
        this.ballexistcolor[this.activecellmarkx][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3];
        this.ballexist[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 2];
        this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2];
        this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 1];
        this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1];
        this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky];
        this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky];
        this.ballexist[this.activecellmarkx + 1][this.activecellmarky] = this.tempboolean;
        this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky] = this.tempint;
        if (this.SilentRotation) {
            this.storeballexist[this.activecellmarkx][this.activecellmarky] = this.ballexist[this.activecellmarkx][this.activecellmarky];
            this.storeballexistcolor[this.activecellmarkx][this.activecellmarky] = this.ballexistcolor[this.activecellmarkx][this.activecellmarky];
            this.storeballexist[this.activecellmarkx - 1][this.activecellmarky + 1] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 1];
            this.storeballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1];
            this.storeballexist[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 2];
            this.storeballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2];
            this.storeballexist[this.activecellmarkx][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx][this.activecellmarky + 3];
            this.storeballexistcolor[this.activecellmarkx][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx][this.activecellmarky + 3];
            this.storeballexist[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky + 3];
            this.storeballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3];
            this.storeballexist[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 2];
            this.storeballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2];
            this.storeballexist[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 1];
            this.storeballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1];
            this.storeballexist[this.activecellmarkx + 1][this.activecellmarky] = this.tempboolean;
            this.storeballexistcolor[this.activecellmarkx + 1][this.activecellmarky] = this.tempint;
        }
        this.RightMouseClickedinPlayfield = false;
        this.MouseMovementLocked = false;
    }
    
    public void RotateToLeft() {
        if ((this.NormalPlay || this.NormalPlusPlay || this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay) && !this.SilentRotation && !this.UndoPressed && this.CurrentNumOfSteps < this.MAXStepHistory) {
            this.HistoryActiveCellMarkX[this.CurrentNumOfSteps] = this.activecellmarkx;
            this.HistoryActiveCellMarkY[this.CurrentNumOfSteps] = this.activecellmarky;
            this.HistoryLeftRight[this.CurrentNumOfSteps] = 'L';
            ++this.CurrentNumOfSteps;
        }
        this.RotatingLeft = true;
        this.RotatingStepCurrent = 0;
        while (this.RotatingLeft) {
            try {
                if (!this.SilentRotation) {
                    Thread.sleep(this.delayvalue);
                }
                ++this.RotatingStepCurrent;
                if (!this.SilentRotation) {
                    this.repaint();
                }
                if (this.RotatingStepCurrent < this.cellpixellength - 2) {
                    continue;
                }
                this.RotatingLeft = false;
            }
            catch (InterruptedException ex) {
                break;
            }
        }
        this.tempboolean = this.ballexist[this.activecellmarkx][this.activecellmarky];
        this.tempint = this.ballexistcolor[this.activecellmarkx][this.activecellmarky];
        this.ballexist[this.activecellmarkx][this.activecellmarky] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky];
        this.ballexistcolor[this.activecellmarkx][this.activecellmarky] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky];
        this.ballexist[this.activecellmarkx + 1][this.activecellmarky] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 1];
        this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1];
        this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 2];
        this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2];
        this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky + 3];
        this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3];
        this.ballexist[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx][this.activecellmarky + 3];
        this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx][this.activecellmarky + 3];
        this.ballexist[this.activecellmarkx][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 2];
        this.ballexistcolor[this.activecellmarkx][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2];
        this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 1];
        this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1];
        this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 1] = this.tempboolean;
        this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1] = this.tempint;
        if (this.SilentRotation) {
            this.storeballexist[this.activecellmarkx][this.activecellmarky] = this.ballexist[this.activecellmarkx][this.activecellmarky];
            this.storeballexistcolor[this.activecellmarkx][this.activecellmarky] = this.ballexistcolor[this.activecellmarkx][this.activecellmarky];
            this.storeballexist[this.activecellmarkx + 1][this.activecellmarky] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky];
            this.storeballexistcolor[this.activecellmarkx + 1][this.activecellmarky] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky];
            this.storeballexist[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 1];
            this.storeballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 1];
            this.storeballexist[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx + 2][this.activecellmarky + 2];
            this.storeballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx + 2][this.activecellmarky + 2];
            this.storeballexist[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx + 1][this.activecellmarky + 3];
            this.storeballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx + 1][this.activecellmarky + 3];
            this.storeballexist[this.activecellmarkx][this.activecellmarky + 3] = this.ballexist[this.activecellmarkx][this.activecellmarky + 3];
            this.storeballexistcolor[this.activecellmarkx][this.activecellmarky + 3] = this.ballexistcolor[this.activecellmarkx][this.activecellmarky + 3];
            this.storeballexist[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexist[this.activecellmarkx - 1][this.activecellmarky + 2];
            this.storeballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2] = this.ballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 2];
            this.storeballexist[this.activecellmarkx - 1][this.activecellmarky + 1] = this.tempboolean;
            this.storeballexistcolor[this.activecellmarkx - 1][this.activecellmarky + 1] = this.tempint;
        }
        this.LeftMouseClickedinPlayfield = false;
        this.MouseMovementLocked = false;
    }
    
    public void putsourcedata(final int n, final int n2, final int n3) {
        this.ballexist[n][n2] = true;
        this.ballexistcolor[n][n2] = n3;
        this.storeballexist[n][n2] = true;
        this.storeballexistcolor[n][n2] = n3;
        if (this.AllInOneColor) {
            this.ballexistcolor[n][n2] = 5;
            this.storeballexistcolor[n][n2] = 5;
        }
    }
    
    public void puttargetdata(final int n, final int n2, final int n3) {
        this.ballexisttarget[n][n2] = true;
        this.ballexisttargetcolor[n][n2] = n3;
        if (this.AllInOneColor) {
            this.ballexisttargetcolor[n][n2] = 5;
        }
    }
    
    public void putsolutiondata(final int n, final int n2, final int n3, final char c) {
        this.SolutionDataX[n] = n2;
        this.SolutionDataY[n] = n3;
        this.SolutionDataDir[n] = c;
    }
    
    public void ReSortSolutionData() {
        this.tempint = this.NumOfSolutionSteps;
        for (int i = 1; i <= this.NumOfSolutionSteps; ++i) {
            this.TempSolutionDataX[i] = this.SolutionDataX[this.tempint];
            this.TempSolutionDataY[i] = this.SolutionDataY[this.tempint];
            this.TempSolutionDataDir[i] = this.SolutionDataDir[this.tempint];
            --this.tempint;
        }
        for (int j = 1; j <= this.NumOfSolutionSteps; ++j) {
            this.SolutionDataX[j] = this.TempSolutionDataX[j];
            this.SolutionDataY[j] = this.TempSolutionDataY[j];
            this.SolutionDataDir[j] = this.TempSolutionDataDir[j];
        }
    }
    
    public void Emptystoreballexistarrays() {
        for (int i = 1; i < this.MAXplayfieldcelllength; ++i) {
            for (int j = 1; j < this.MAXplayfieldcelllength; ++j) {
                this.ballexist[i][j] = false;
                this.ballexistcolor[i][j] = 1;
                this.ballexisttarget[i][j] = false;
                this.ballexisttargetcolor[i][j] = 1;
                this.storeballexist[i][j] = false;
                this.storeballexistcolor[i][j] = 1;
                this.storeballexisttarget[i][j] = false;
                this.storeballexisttargetcolor[i][j] = 1;
            }
        }
    }
    
    public void SelectSetForLevel() {
        this.ExtraSteps = 0;
        if (this.ExtraPlay || this.EndlessPlay) {
            if (this.PlayLevel == 1) {
                this.TotalSet = 35;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-1-1";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-1-2";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-1-3";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-1-4";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-1-5";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-1-6";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-1-7";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-1-8";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraEnd-1-9";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraEnd-1-10";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraEnd-1-11";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraEnd-1-12";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraEnd-1-13";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraEnd-1-14";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraEnd-1-15";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraEnd-1-16";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 17) {
                    this.PlayLevelID = "ExtraEnd-1-17";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 18) {
                    this.PlayLevelID = "ExtraEnd-1-18";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 19) {
                    this.PlayLevelID = "ExtraEnd-1-19";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 20) {
                    this.PlayLevelID = "ExtraEnd-1-20";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 21) {
                    this.PlayLevelID = "ExtraEnd-1-21";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 22) {
                    this.PlayLevelID = "ExtraEnd-1-22";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 23) {
                    this.PlayLevelID = "ExtraEnd-1-23";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 24) {
                    this.PlayLevelID = "ExtraEnd-1-24";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 25) {
                    this.PlayLevelID = "ExtraEnd-1-25";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 26) {
                    this.PlayLevelID = "ExtraEnd-1-26";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 27) {
                    this.PlayLevelID = "ExtraEnd-1-27";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 28) {
                    this.PlayLevelID = "ExtraEnd-1-28";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 29) {
                    this.PlayLevelID = "ExtraEnd-1-29";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 30) {
                    this.PlayLevelID = "ExtraEnd-1-30";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 31) {
                    this.PlayLevelID = "ExtraEnd-1-31";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 32) {
                    this.PlayLevelID = "ExtraEnd-1-32";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 33) {
                    this.PlayLevelID = "ExtraEnd-1-33";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 34) {
                    this.PlayLevelID = "ExtraEnd-1-34";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 35) {
                    this.PlayLevelID = "ExtraEnd-1-35";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 2) {
                this.TotalSet = 21;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-02-01";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-02-02";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-02-03";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-02-04";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-02-05";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-02-06";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-02-07";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-02-08";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraEnd-02-09";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraEnd-02-10";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraEnd-02-11";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraEnd-02-12";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraEnd-02-13";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraEnd-02-14";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraEnd-02-15";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraEnd-02-16";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 17) {
                    this.PlayLevelID = "ExtraEnd-02-17";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 18) {
                    this.PlayLevelID = "ExtraEnd-02-18";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 19) {
                    this.PlayLevelID = "ExtraEnd-02-19";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 20) {
                    this.PlayLevelID = "ExtraEnd-02-20";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 21) {
                    this.PlayLevelID = "ExtraEnd-02-21";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 3;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 3) {
                this.TotalSet = 4;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-03-01";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-03-02";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-03-03";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-03-04";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 4) {
                this.TotalSet = 8;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-04-01";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-04-02";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-04-03";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-04-04";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-04-05";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-04-06";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-04-07";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-04-08";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 5) {
                this.TotalSet = 8;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-05-01";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-05-02";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-05-03";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-05-04";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-05-05";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-05-06";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-05-07";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-05-08";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 6) {
                this.TotalSet = 8;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-06-01";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-06-02";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-06-03";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-06-04";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-06-05";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-06-06";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-06-07";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-06-08";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 7) {
                this.TotalSet = 16;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-07-01";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-07-02";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-07-03";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-07-04";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-07-05";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-07-06";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-07-07";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-07-08";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraEnd-07-09";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraEnd-07-10";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraEnd-07-11";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraEnd-07-12";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraEnd-07-13";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraEnd-07-14";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraEnd-07-15";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraEnd-07-16";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 8) {
                this.TotalSet = 16;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-08-01";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-08-02";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-08-03";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-08-04";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-08-05";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-08-06";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-08-07";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-08-08";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraEnd-08-09";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraEnd-08-10";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraEnd-08-11";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraEnd-08-12";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraEnd-08-13";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraEnd-08-14";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraEnd-08-15";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraEnd-08-16";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 9) {
                this.TotalSet = 22;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-09-01";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-09-02";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-09-03";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-09-04";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-09-05";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-09-06";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-09-07";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-09-08";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraEnd-09-09";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraEnd-09-10";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraEnd-09-11";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraEnd-09-12";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraEnd-09-13";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraEnd-09-14";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraEnd-09-15";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraEnd-09-16";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 17) {
                    this.PlayLevelID = "ExtraEnd-09-17";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 18) {
                    this.PlayLevelID = "ExtraEnd-09-18";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 19) {
                    this.PlayLevelID = "ExtraEnd-09-19";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 20) {
                    this.PlayLevelID = "ExtraEnd-09-20";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 21) {
                    this.PlayLevelID = "ExtraEnd-09-21";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 22) {
                    this.PlayLevelID = "ExtraEnd-09-22";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 10) {
                this.TotalSet = 26;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraEnd-10-01";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 40;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraEnd-10-02";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 40;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraEnd-10-03";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 40;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraEnd-10-04";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 40;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraEnd-10-05";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraEnd-10-06";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 20;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraEnd-10-07";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraEnd-10-08";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraEnd-10-09";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraEnd-10-10";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 25;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraEnd-10-11";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 18;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraEnd-10-12";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 18;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraEnd-10-13";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 18;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraEnd-10-14";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 18;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraEnd-10-15";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraEnd-10-16";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 17) {
                    this.PlayLevelID = "ExtraEnd-10-17";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 18) {
                    this.PlayLevelID = "ExtraEnd-10-18";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 19) {
                    this.PlayLevelID = "ExtraEnd-10-19";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 20) {
                    this.PlayLevelID = "ExtraEnd-10-20";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 21) {
                    this.PlayLevelID = "ExtraEnd-10-21";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 22) {
                    this.PlayLevelID = "ExtraEnd-10-22";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 23) {
                    this.PlayLevelID = "ExtraEnd-10-23";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 12;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 24) {
                    this.PlayLevelID = "ExtraEnd-10-24";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 12;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 25) {
                    this.PlayLevelID = "ExtraEnd-10-25";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 12;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 26) {
                    this.PlayLevelID = "ExtraEnd-10-26";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 12;
                    this.RandomTotalRotate = 3;
                }
            }
        }
        else if (this.ExtraPlusPlay || this.EndlessPlusPlay) {
            if (this.PlayLevel == 1) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-01-01";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 9;
                    this.RandomTotalRotate = 0;
                }
            }
            else if (this.PlayLevel == 2) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-02-01";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 0;
                }
            }
            else if (this.PlayLevel == 3) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-03-01";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 3;
                    this.ExtraSteps = 3;
                }
            }
            else if (this.PlayLevel == 4) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-04-01";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 0;
                    this.ExtraSteps = 2;
                }
            }
            else if (this.PlayLevel == 5) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-05-01";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 0;
                    this.ExtraSteps = 1;
                }
            }
            else if (this.PlayLevel == 6) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-06-01";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 0;
                    this.ExtraSteps = 2;
                }
            }
            else if (this.PlayLevel == 7) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-07-01";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 0;
                    this.ExtraSteps = 1;
                }
            }
            else if (this.PlayLevel == 8) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-08-01";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 0;
                    this.ExtraSteps = 1;
                }
            }
            else if (this.PlayLevel == 9) {
                this.TotalSet = 1;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                this.SetSelected = 1;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-04-01";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 5) {
                this.TotalSet = 4;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-05-01";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraPlu-05-02";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraPlu-05-03";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraPlu-05-04";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 6) {
                this.TotalSet = 3;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-06-01";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                    this.ExtraSteps = 5;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraPlu-06-02";
                    this.playfieldcelllength = 11;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                    this.ExtraSteps = 5;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraPlu-06-03";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 7) {
                this.ExtraSteps = 10;
                this.TotalSet = 2;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-07-01";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 0;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraPlu-07-02";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 1;
                }
            }
            else if (this.PlayLevel == 8) {
                this.ExtraSteps = 7;
                this.TotalSet = 10;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-08-01";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 0;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraPlu-08-02";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 20;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraPlu-08-03";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraPlu-08-04";
                    this.ExtraSteps = 0;
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraPlu-08-05";
                    this.ExtraSteps = 0;
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraPlu-08-06";
                    this.ExtraSteps = 0;
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraPlu-08-07";
                    this.ExtraSteps = 0;
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
            }
            else if (this.PlayLevel == 9) {
                this.ExtraSteps = 20;
                this.TotalSet = 8;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-09-01";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraPlu-09-02";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraPlu-09-03";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraPlu-09-04";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraPlu-09-05";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraPlu-09-06";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraPlu-09-07";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraPlu-09-08";
                    this.playfieldcelllength = 9;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
            }
            else if (this.PlayLevel == 10) {
                this.ExtraSteps = 20;
                this.TotalSet = 18;
                this.m1 = Math.random();
                this.SetSelected = (int)(this.m1 * this.TotalSet);
                ++this.SetSelected;
                if (this.SetSelected == 1) {
                    this.PlayLevelID = "ExtraPlu-10-01";
                    this.playfieldcelllength = 10;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 5;
                }
                if (this.SetSelected == 2) {
                    this.PlayLevelID = "ExtraPlu-10-02";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 3) {
                    this.PlayLevelID = "ExtraPlu-10-03";
                    this.playfieldcelllength = 8;
                    this.TotalNumOfColoredCells = 15;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 4) {
                    this.PlayLevelID = "ExtraPlu-10-04";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 5) {
                    this.PlayLevelID = "ExtraPlu-10-05";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 10;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 6) {
                    this.PlayLevelID = "ExtraPlu-10-06";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 7) {
                    this.PlayLevelID = "ExtraPlu-10-07";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 8) {
                    this.PlayLevelID = "ExtraPlu-10-08";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 9) {
                    this.PlayLevelID = "ExtraPlu-10-09";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 10) {
                    this.PlayLevelID = "ExtraPlu-10-10";
                    this.playfieldcelllength = 7;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 11) {
                    this.PlayLevelID = "ExtraPlu-10-11";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 8;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 12) {
                    this.PlayLevelID = "ExtraPlu-10-12";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 13) {
                    this.PlayLevelID = "ExtraPlu-10-13";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 7;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 14) {
                    this.PlayLevelID = "ExtraPlu-10-14";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 15;
                }
                else if (this.SetSelected == 15) {
                    this.PlayLevelID = "ExtraPlu-10-15";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 6;
                    this.RandomTotalRotate = 10;
                }
                else if (this.SetSelected == 16) {
                    this.PlayLevelID = "ExtraPlu-10-16";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 5;
                }
                else if (this.SetSelected == 17) {
                    this.PlayLevelID = "ExtraPlu-10-17";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 5;
                    this.RandomTotalRotate = 3;
                }
                else if (this.SetSelected == 18) {
                    this.PlayLevelID = "ExtraPlu-10-18";
                    this.playfieldcelllength = 6;
                    this.TotalNumOfColoredCells = 4;
                    this.RandomTotalRotate = 3;
                }
            }
        }
    }
    
    public void FillStage() {
        this.LastStage = false;
        this.ShowPassword = false;
        this.AllCleared = false;
        this.NormalPlayAllCleared = false;
        this.NormalPlusPlayAllCleared = false;
        this.ExtraPlayAllCleared = false;
        this.ExtraPlusPlayAllCleared = false;
        this.EndlessPlayAllCleared = false;
        this.EndlessPlusPlayAllCleared = false;
        this.InNormalPlayAllClearedScreen = false;
        this.InNormalPlusPlayAllClearedScreen = false;
        this.InExtraPlayAllClearedScreen = false;
        this.InExtraPlusPlayAllClearedScreen = false;
        this.InEndlessPlusPlayAllClearedScreen = false;
        this.CurrentNumOfSteps = 0;
        this.StageClear = false;
        for (int i = 1; i <= this.MAXplayfieldcelllength; ++i) {
            for (int j = 1; j <= this.MAXplayfieldcelllength; ++j) {
                this.ballexist[i][j] = false;
                this.ballexisttarget[i][j] = false;
                this.ballexistcolor[i][j] = 0;
                this.ballexisttargetcolor[i][j] = 0;
            }
        }
        if (this.NormalPlay) {
            this.PlayLevelID = "none";
            this.ShowPassword = true;
            if (this.DEBUGSHORTSTAGE) {
                this.CurrentStage = 1;
                this.TotalStages = 1;
            }
            else {
                this.TotalStages = this.MAXStageNumNormal;
            }
            this.NextStagePassword = this.NormalPasswordArray[this.CurrentStage + 1];
            if (this.CurrentStage == 1) {
                this.playfieldcelllength = 11;
                this.putsourcedata(2, 2, 1);
                this.putsourcedata(3, 3, 2);
                this.putsourcedata(4, 4, 3);
                this.putsourcedata(5, 5, 4);
                this.puttargetdata(3, 2, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(6, 5, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
            }
            else if (this.CurrentStage == 2) {
                this.playfieldcelllength = 11;
                this.putsourcedata(4, 2, 1);
                this.putsourcedata(7, 2, 2);
                this.putsourcedata(5, 9, 3);
                this.putsourcedata(6, 9, 4);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(8, 3, 2);
                this.puttargetdata(3, 8, 3);
                this.puttargetdata(8, 8, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
            }
            else if (this.CurrentStage == 3) {
                this.playfieldcelllength = 10;
                this.putsourcedata(9, 5, 2);
                this.putsourcedata(9, 6, 4);
                this.putsourcedata(9, 7, 1);
                this.puttargetdata(9, 2, 1);
                this.puttargetdata(9, 3, 2);
                this.puttargetdata(9, 4, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'L');
            }
            else if (this.CurrentStage == 4) {
                this.playfieldcelllength = 11;
                this.putsourcedata(9, 2, 4);
                this.putsourcedata(3, 3, 1);
                this.putsourcedata(2, 4, 2);
                this.putsourcedata(7, 4, 3);
                this.putsourcedata(2, 6, 3);
                this.putsourcedata(7, 6, 2);
                this.putsourcedata(4, 7, 1);
                this.putsourcedata(8, 10, 4);
                this.puttargetdata(2, 2, 1);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(4, 4, 3);
                this.puttargetdata(5, 5, 4);
                this.puttargetdata(6, 6, 1);
                this.puttargetdata(7, 7, 2);
                this.puttargetdata(8, 8, 3);
                this.puttargetdata(9, 9, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
            }
            else if (this.CurrentStage == 5) {
                this.playfieldcelllength = 10;
                this.putsourcedata(6, 1, 4);
                this.putsourcedata(2, 2, 2);
                this.putsourcedata(3, 3, 3);
                this.putsourcedata(1, 4, 1);
                this.putsourcedata(2, 5, 4);
                this.putsourcedata(8, 5, 3);
                this.putsourcedata(9, 8, 1);
                this.putsourcedata(8, 9, 2);
                this.puttargetdata(3, 1, 2);
                this.puttargetdata(5, 1, 3);
                this.puttargetdata(7, 1, 1);
                this.puttargetdata(3, 2, 1);
                this.puttargetdata(4, 2, 4);
                this.puttargetdata(5, 2, 4);
                this.puttargetdata(6, 2, 3);
                this.puttargetdata(7, 2, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
            }
            else if (this.CurrentStage == 6) {
                this.playfieldcelllength = 10;
                this.putsourcedata(5, 2, 1);
                this.putsourcedata(2, 4, 1);
                this.putsourcedata(5, 4, 1);
                this.putsourcedata(5, 5, 1);
                this.putsourcedata(7, 5, 1);
                this.putsourcedata(8, 6, 1);
                this.putsourcedata(4, 7, 1);
                this.putsourcedata(6, 7, 1);
                this.putsourcedata(7, 8, 1);
                this.puttargetdata(3, 4, 1);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(6, 4, 1);
                this.puttargetdata(7, 4, 1);
                this.puttargetdata(4, 5, 1);
                this.puttargetdata(6, 5, 1);
                this.puttargetdata(4, 6, 1);
                this.puttargetdata(5, 6, 1);
                this.puttargetdata(6, 6, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
            }
            else if (this.CurrentStage == 7) {
                this.playfieldcelllength = 9;
                this.putsourcedata(2, 1, 2);
                this.putsourcedata(3, 1, 2);
                this.putsourcedata(2, 2, 2);
                this.putsourcedata(6, 2, 2);
                this.putsourcedata(2, 3, 2);
                this.putsourcedata(7, 3, 2);
                this.putsourcedata(6, 6, 2);
                this.putsourcedata(7, 6, 2);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(5, 3, 2);
                this.puttargetdata(6, 3, 2);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(6, 4, 2);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(5, 5, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
            }
            else if (this.CurrentStage == 8) {
                this.playfieldcelllength = 9;
                this.putsourcedata(2, 3, 1);
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(5, 4, 2);
                this.putsourcedata(1, 5, 3);
                this.puttargetdata(2, 1, 3);
                this.puttargetdata(3, 1, 4);
                this.puttargetdata(1, 2, 2);
                this.puttargetdata(1, 3, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
            }
            else if (this.CurrentStage == 9) {
                this.playfieldcelllength = 9;
                this.putsourcedata(4, 2, 1);
                this.putsourcedata(7, 3, 3);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(3, 6, 1);
                this.putsourcedata(7, 6, 2);
                this.putsourcedata(1, 6, 2);
                this.putsourcedata(4, 7, 3);
                this.puttargetdata(2, 1, 3);
                this.puttargetdata(6, 1, 1);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(8, 4, 2);
                this.puttargetdata(1, 6, 4);
                this.puttargetdata(6, 6, 3);
                this.puttargetdata(2, 8, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 10) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 2, 2);
                this.putsourcedata(3, 2, 1);
                this.putsourcedata(4, 2, 4);
                this.putsourcedata(7, 5, 3);
                this.putsourcedata(6, 6, 4);
                this.putsourcedata(5, 7, 1);
                this.puttargetdata(1, 3, 4);
                this.puttargetdata(7, 3, 3);
                this.puttargetdata(1, 4, 1);
                this.puttargetdata(7, 4, 4);
                this.puttargetdata(1, 5, 2);
                this.puttargetdata(7, 5, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
            }
            else if (this.CurrentStage == 11) {
                this.playfieldcelllength = 8;
                this.putsourcedata(4, 1, 4);
                this.putsourcedata(5, 1, 1);
                this.putsourcedata(4, 2, 1);
                this.putsourcedata(1, 3, 4);
                this.putsourcedata(5, 3, 1);
                this.putsourcedata(5, 4, 4);
                this.putsourcedata(5, 5, 1);
                this.putsourcedata(3, 6, 4);
                this.putsourcedata(4, 6, 2);
                this.puttargetdata(2, 1, 4);
                this.puttargetdata(3, 1, 4);
                this.puttargetdata(4, 1, 2);
                this.puttargetdata(5, 1, 1);
                this.puttargetdata(6, 1, 1);
                this.puttargetdata(2, 2, 4);
                this.puttargetdata(3, 2, 4);
                this.puttargetdata(5, 2, 1);
                this.puttargetdata(6, 2, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 12) {
                this.playfieldcelllength = 7;
                this.putsourcedata(2, 1, 1);
                this.putsourcedata(4, 1, 3);
                this.putsourcedata(3, 2, 4);
                this.putsourcedata(4, 2, 2);
                this.puttargetdata(2, 2, 1);
                this.puttargetdata(5, 2, 2);
                this.puttargetdata(2, 5, 4);
                this.puttargetdata(5, 5, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
            }
            else if (this.CurrentStage == 13) {
                this.playfieldcelllength = 10;
                this.putsourcedata(5, 2, 3);
                this.putsourcedata(6, 2, 4);
                this.putsourcedata(7, 2, 2);
                this.putsourcedata(2, 4, 1);
                this.putsourcedata(3, 5, 1);
                this.putsourcedata(9, 6, 4);
                this.putsourcedata(8, 7, 3);
                this.putsourcedata(9, 8, 2);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(5, 3, 2);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(5, 5, 4);
                this.puttargetdata(7, 5, 1);
                this.puttargetdata(5, 6, 2);
                this.puttargetdata(3, 7, 4);
                this.puttargetdata(5, 7, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
            }
            else if (this.CurrentStage == 14) {
                this.playfieldcelllength = 9;
                this.putsourcedata(4, 3, 1);
                this.putsourcedata(4, 4, 4);
                this.putsourcedata(8, 4, 1);
                this.putsourcedata(3, 6, 4);
                this.putsourcedata(6, 6, 3);
                this.putsourcedata(7, 6, 2);
                this.putsourcedata(8, 6, 3);
                this.putsourcedata(5, 7, 3);
                this.putsourcedata(6, 7, 2);
                this.putsourcedata(7, 7, 2);
                this.puttargetdata(7, 2, 2);
                this.puttargetdata(5, 3, 1);
                this.puttargetdata(6, 3, 3);
                this.puttargetdata(7, 3, 4);
                this.puttargetdata(5, 4, 2);
                this.puttargetdata(5, 5, 3);
                this.puttargetdata(5, 6, 4);
                this.puttargetdata(6, 6, 1);
                this.puttargetdata(7, 6, 2);
                this.puttargetdata(7, 7, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
            }
            else if (this.CurrentStage == 15) {
                this.playfieldcelllength = 8;
                this.putsourcedata(5, 2, 2);
                this.putsourcedata(6, 2, 4);
                this.putsourcedata(2, 3, 1);
                this.putsourcedata(1, 4, 4);
                this.putsourcedata(6, 4, 3);
                this.puttargetdata(4, 3, 1);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(5, 4, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
            }
            else if (this.CurrentStage == 16) {
                this.playfieldcelllength = 11;
                this.putsourcedata(2, 3, 1);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(7, 3, 3);
                this.putsourcedata(4, 4, 1);
                this.putsourcedata(2, 5, 2);
                this.putsourcedata(6, 5, 2);
                this.putsourcedata(8, 5, 4);
                this.putsourcedata(4, 6, 4);
                this.putsourcedata(6, 6, 2);
                this.putsourcedata(9, 6, 2);
                this.putsourcedata(6, 7, 4);
                this.putsourcedata(4, 8, 3);
                this.putsourcedata(6, 8, 4);
                this.putsourcedata(8, 8, 3);
                this.putsourcedata(10, 8, 1);
                this.putsourcedata(7, 9, 1);
                this.puttargetdata(1, 4, 1);
                this.puttargetdata(2, 4, 2);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(5, 4, 1);
                this.puttargetdata(6, 4, 2);
                this.puttargetdata(7, 4, 3);
                this.puttargetdata(8, 4, 4);
                this.puttargetdata(3, 7, 4);
                this.puttargetdata(4, 7, 3);
                this.puttargetdata(5, 7, 2);
                this.puttargetdata(6, 7, 1);
                this.puttargetdata(7, 7, 4);
                this.puttargetdata(8, 7, 3);
                this.puttargetdata(9, 7, 2);
                this.puttargetdata(10, 7, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
            }
            else if (this.CurrentStage == 17) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 1, 2);
                this.putsourcedata(6, 1, 3);
                this.putsourcedata(3, 4, 1);
                this.putsourcedata(5, 6, 1);
                this.putsourcedata(6, 6, 3);
                this.puttargetdata(2, 2, 3);
                this.puttargetdata(6, 2, 3);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(2, 6, 1);
                this.puttargetdata(6, 6, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
            }
            else if (this.CurrentStage == 18) {
                this.playfieldcelllength = 11;
                this.putsourcedata(4, 1, 4);
                this.putsourcedata(5, 1, 4);
                this.putsourcedata(7, 1, 4);
                this.putsourcedata(4, 2, 4);
                this.putsourcedata(5, 2, 2);
                this.putsourcedata(7, 2, 2);
                this.putsourcedata(5, 3, 1);
                this.putsourcedata(4, 6, 1);
                this.putsourcedata(5, 6, 2);
                this.putsourcedata(6, 6, 4);
                this.putsourcedata(5, 7, 4);
                this.putsourcedata(7, 7, 4);
                this.putsourcedata(8, 7, 1);
                this.putsourcedata(3, 8, 1);
                this.putsourcedata(8, 8, 2);
                this.putsourcedata(5, 9, 4);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(5, 3, 4);
                this.puttargetdata(6, 3, 4);
                this.puttargetdata(7, 3, 4);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(7, 4, 2);
                this.puttargetdata(5, 5, 1);
                this.puttargetdata(6, 5, 1);
                this.puttargetdata(5, 6, 1);
                this.puttargetdata(6, 6, 1);
                this.puttargetdata(4, 7, 2);
                this.puttargetdata(7, 7, 2);
                this.puttargetdata(4, 8, 4);
                this.puttargetdata(5, 8, 4);
                this.puttargetdata(6, 8, 4);
                this.puttargetdata(7, 8, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 7, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
            }
            else if (this.CurrentStage == 19) {
                this.playfieldcelllength = 9;
                this.putsourcedata(7, 1, 1);
                this.putsourcedata(3, 2, 4);
                this.putsourcedata(1, 4, 4);
                this.putsourcedata(8, 4, 1);
                this.putsourcedata(4, 5, 3);
                this.putsourcedata(2, 6, 2);
                this.putsourcedata(6, 7, 2);
                this.putsourcedata(6, 8, 1);
                this.puttargetdata(2, 3, 2);
                this.puttargetdata(7, 3, 1);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(5, 4, 4);
                this.puttargetdata(4, 5, 4);
                this.puttargetdata(5, 5, 3);
                this.puttargetdata(2, 6, 1);
                this.puttargetdata(7, 6, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
            }
            else if (this.CurrentStage == 20) {
                this.playfieldcelllength = 11;
                this.putsourcedata(2, 1, 4);
                this.putsourcedata(6, 2, 2);
                this.putsourcedata(4, 3, 1);
                this.putsourcedata(9, 3, 4);
                this.putsourcedata(2, 4, 2);
                this.putsourcedata(6, 4, 4);
                this.putsourcedata(7, 4, 4);
                this.putsourcedata(9, 4, 2);
                this.putsourcedata(7, 5, 3);
                this.putsourcedata(1, 6, 3);
                this.putsourcedata(3, 7, 3);
                this.putsourcedata(5, 7, 1);
                this.putsourcedata(9, 7, 2);
                this.putsourcedata(3, 9, 3);
                this.putsourcedata(3, 10, 1);
                this.putsourcedata(8, 10, 1);
                this.puttargetdata(2, 2, 4);
                this.puttargetdata(9, 2, 2);
                this.puttargetdata(3, 3, 3);
                this.puttargetdata(8, 3, 4);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(7, 4, 2);
                this.puttargetdata(5, 5, 1);
                this.puttargetdata(6, 5, 4);
                this.puttargetdata(5, 6, 3);
                this.puttargetdata(6, 6, 4);
                this.puttargetdata(4, 7, 1);
                this.puttargetdata(7, 7, 3);
                this.puttargetdata(3, 8, 3);
                this.puttargetdata(8, 8, 2);
                this.puttargetdata(2, 9, 1);
                this.puttargetdata(9, 9, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 7, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 7, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 7, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
            }
            else if (this.CurrentStage == 21) {
                this.playfieldcelllength = 10;
                this.putsourcedata(4, 5, 2);
                this.putsourcedata(3, 6, 4);
                this.putsourcedata(5, 6, 1);
                this.putsourcedata(3, 7, 3);
                this.putsourcedata(8, 7, 2);
                this.putsourcedata(4, 8, 1);
                this.putsourcedata(7, 8, 1);
                this.putsourcedata(4, 9, 4);
                this.putsourcedata(6, 9, 3);
                this.putsourcedata(7, 9, 2);
                this.puttargetdata(3, 7, 2);
                this.puttargetdata(7, 7, 1);
                this.puttargetdata(2, 8, 4);
                this.puttargetdata(3, 8, 4);
                this.puttargetdata(4, 8, 3);
                this.puttargetdata(5, 8, 2);
                this.puttargetdata(6, 8, 1);
                this.puttargetdata(7, 8, 2);
                this.puttargetdata(8, 8, 3);
                this.puttargetdata(5, 9, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 6, 'R');
            }
            else if (this.CurrentStage == 22) {
                this.playfieldcelllength = 10;
                this.putsourcedata(4, 1, 1);
                this.putsourcedata(2, 2, 3);
                this.putsourcedata(5, 2, 1);
                this.putsourcedata(6, 2, 3);
                this.putsourcedata(8, 2, 2);
                this.putsourcedata(6, 4, 2);
                this.putsourcedata(7, 4, 4);
                this.putsourcedata(7, 5, 3);
                this.putsourcedata(5, 6, 2);
                this.putsourcedata(9, 7, 4);
                this.putsourcedata(1, 8, 4);
                this.putsourcedata(8, 8, 1);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(5, 3, 1);
                this.puttargetdata(6, 3, 2);
                this.puttargetdata(7, 3, 1);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(7, 4, 2);
                this.puttargetdata(4, 5, 4);
                this.puttargetdata(7, 5, 3);
                this.puttargetdata(4, 6, 2);
                this.puttargetdata(5, 6, 1);
                this.puttargetdata(6, 6, 3);
                this.puttargetdata(7, 6, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
            }
            else if (this.CurrentStage == 23) {
                this.playfieldcelllength = 10;
                this.putsourcedata(2, 1, 4);
                this.putsourcedata(8, 1, 2);
                this.putsourcedata(2, 2, 1);
                this.putsourcedata(8, 2, 1);
                this.putsourcedata(3, 3, 3);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(1, 4, 2);
                this.putsourcedata(4, 5, 3);
                this.putsourcedata(4, 6, 1);
                this.putsourcedata(8, 6, 1);
                this.putsourcedata(9, 6, 2);
                this.putsourcedata(7, 7, 4);
                this.putsourcedata(8, 7, 3);
                this.putsourcedata(4, 8, 4);
                this.putsourcedata(7, 8, 4);
                this.putsourcedata(2, 9, 2);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(5, 3, 3);
                this.puttargetdata(6, 3, 2);
                this.puttargetdata(7, 3, 1);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(7, 4, 4);
                this.puttargetdata(3, 5, 3);
                this.puttargetdata(7, 5, 3);
                this.puttargetdata(3, 6, 4);
                this.puttargetdata(7, 6, 2);
                this.puttargetdata(3, 7, 1);
                this.puttargetdata(4, 7, 2);
                this.puttargetdata(5, 7, 3);
                this.puttargetdata(6, 7, 4);
                this.puttargetdata(7, 7, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
            }
            else if (this.CurrentStage == 24) {
                this.playfieldcelllength = 9;
                this.putsourcedata(3, 2, 3);
                this.putsourcedata(7, 2, 2);
                this.putsourcedata(7, 3, 3);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(6, 4, 4);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(4, 5, 1);
                this.putsourcedata(7, 6, 2);
                this.putsourcedata(2, 7, 4);
                this.putsourcedata(4, 7, 1);
                this.putsourcedata(6, 7, 4);
                this.putsourcedata(5, 8, 3);
                this.puttargetdata(3, 2, 2);
                this.puttargetdata(6, 2, 3);
                this.puttargetdata(2, 3, 3);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(6, 3, 2);
                this.puttargetdata(7, 3, 4);
                this.puttargetdata(2, 6, 4);
                this.puttargetdata(3, 6, 3);
                this.puttargetdata(6, 6, 4);
                this.puttargetdata(7, 6, 3);
                this.puttargetdata(3, 7, 2);
                this.puttargetdata(6, 7, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
            }
            else if (this.CurrentStage == 25) {
                this.playfieldcelllength = 9;
                this.putsourcedata(3, 2, 1);
                this.putsourcedata(4, 2, 2);
                this.putsourcedata(2, 3, 2);
                this.putsourcedata(5, 3, 4);
                this.putsourcedata(6, 3, 4);
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(6, 4, 1);
                this.putsourcedata(8, 4, 3);
                this.putsourcedata(2, 6, 1);
                this.putsourcedata(4, 6, 3);
                this.putsourcedata(3, 7, 2);
                this.putsourcedata(6, 7, 3);
                this.puttargetdata(4, 2, 1);
                this.puttargetdata(5, 2, 2);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(6, 3, 3);
                this.puttargetdata(2, 4, 3);
                this.puttargetdata(7, 4, 4);
                this.puttargetdata(2, 5, 4);
                this.puttargetdata(7, 5, 1);
                this.puttargetdata(3, 6, 1);
                this.puttargetdata(6, 6, 4);
                this.puttargetdata(4, 7, 2);
                this.puttargetdata(5, 7, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
            }
            else if (this.CurrentStage == 26) {
                this.playfieldcelllength = 11;
                this.putsourcedata(10, 4, 4);
                this.putsourcedata(10, 5, 3);
                this.putsourcedata(9, 6, 2);
                this.putsourcedata(10, 6, 1);
                this.putsourcedata(6, 7, 2);
                this.putsourcedata(10, 7, 3);
                this.putsourcedata(5, 8, 1);
                this.putsourcedata(6, 8, 4);
                this.putsourcedata(9, 9, 1);
                this.putsourcedata(6, 10, 2);
                this.putsourcedata(9, 10, 3);
                this.puttargetdata(8, 7, 1);
                this.puttargetdata(9, 7, 2);
                this.puttargetdata(10, 7, 3);
                this.puttargetdata(8, 8, 4);
                this.puttargetdata(9, 8, 2);
                this.puttargetdata(10, 8, 1);
                this.puttargetdata(8, 9, 3);
                this.puttargetdata(9, 9, 3);
                this.puttargetdata(10, 9, 4);
                this.puttargetdata(8, 10, 2);
                this.puttargetdata(9, 10, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 7, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 7, 'R');
            }
            else if (this.CurrentStage == 27) {
                this.playfieldcelllength = 9;
                this.putsourcedata(5, 2, 1);
                this.putsourcedata(6, 3, 1);
                this.putsourcedata(4, 4, 4);
                this.putsourcedata(1, 5, 1);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(4, 5, 3);
                this.putsourcedata(6, 5, 4);
                this.putsourcedata(4, 6, 4);
                this.putsourcedata(5, 6, 4);
                this.putsourcedata(6, 6, 3);
                this.putsourcedata(8, 6, 2);
                this.putsourcedata(3, 7, 3);
                this.putsourcedata(6, 7, 2);
                this.putsourcedata(6, 8, 1);
                this.puttargetdata(4, 2, 1);
                this.puttargetdata(5, 2, 1);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(5, 3, 2);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(6, 4, 3);
                this.puttargetdata(3, 5, 4);
                this.puttargetdata(6, 5, 4);
                this.puttargetdata(2, 7, 1);
                this.puttargetdata(3, 7, 2);
                this.puttargetdata(4, 7, 3);
                this.puttargetdata(5, 7, 4);
                this.puttargetdata(6, 7, 1);
                this.puttargetdata(7, 7, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 28) {
                this.playfieldcelllength = 8;
                this.putsourcedata(6, 1, 4);
                this.putsourcedata(4, 2, 4);
                this.putsourcedata(5, 2, 4);
                this.putsourcedata(2, 3, 4);
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(6, 3, 4);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(7, 5, 4);
                this.putsourcedata(6, 6, 4);
                this.puttargetdata(4, 2, 4);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(2, 4, 4);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(5, 4, 4);
                this.puttargetdata(6, 4, 4);
                this.puttargetdata(4, 5, 4);
                this.puttargetdata(4, 6, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
            }
            else if (this.CurrentStage == 29) {
                this.playfieldcelllength = 7;
                this.putsourcedata(2, 1, 4);
                this.putsourcedata(5, 2, 1);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(2, 4, 2);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(4, 4, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
            }
            else if (this.CurrentStage == 30) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 2, 2);
                this.putsourcedata(5, 2, 2);
                this.putsourcedata(2, 3, 3);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(5, 3, 1);
                this.putsourcedata(2, 4, 1);
                this.putsourcedata(2, 5, 3);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(5, 7, 4);
                this.puttargetdata(4, 2, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(2, 4, 3);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(4, 4, 3);
                this.puttargetdata(5, 4, 4);
                this.puttargetdata(6, 4, 3);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(4, 6, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'R');
            }
            else if (this.CurrentStage == this.MAXStageNumNormal + 1) {
                this.ShowPassword = false;
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 3, 4);
                this.putsourcedata(2, 4, 4);
                this.putsourcedata(2, 5, 4);
                this.putsourcedata(3, 2, 4);
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(3, 6, 4);
                this.putsourcedata(4, 1, 4);
                this.putsourcedata(4, 2, 4);
                this.putsourcedata(4, 4, 4);
                this.putsourcedata(4, 5, 4);
                this.putsourcedata(4, 7, 4);
                this.putsourcedata(5, 2, 4);
                this.putsourcedata(5, 3, 2);
                this.putsourcedata(5, 4, 4);
                this.putsourcedata(5, 5, 1);
                this.putsourcedata(5, 6, 4);
                this.putsourcedata(6, 2, 4);
                this.putsourcedata(6, 4, 4);
                this.putsourcedata(6, 5, 4);
                this.putsourcedata(6, 6, 4);
                this.puttargetdata(2, 2, 4);
                this.puttargetdata(2, 3, 4);
                this.puttargetdata(2, 5, 4);
                this.puttargetdata(2, 6, 4);
                this.puttargetdata(3, 2, 4);
                this.puttargetdata(3, 3, 4);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(3, 5, 4);
                this.puttargetdata(3, 6, 4);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(4, 5, 1);
                this.puttargetdata(5, 2, 4);
                this.puttargetdata(5, 3, 4);
                this.puttargetdata(5, 4, 4);
                this.puttargetdata(5, 5, 4);
                this.puttargetdata(5, 6, 4);
                this.puttargetdata(6, 2, 4);
                this.puttargetdata(6, 3, 4);
                this.puttargetdata(6, 5, 4);
                this.puttargetdata(6, 6, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
        }
        else if (this.NormalPlusPlay) {
            this.PlayLevelID = "none";
            this.ShowPassword = true;
            if (this.DEBUGSHORTSTAGE) {
                this.CurrentStage = 1;
                this.TotalStages = 1;
            }
            else {
                this.TotalStages = this.MAXStageNumNormalPlus;
            }
            this.NextStagePassword = this.NormalPlusPasswordArray[this.CurrentStage + 1];
            if (this.CurrentStage == 1) {
                this.playfieldcelllength = 10;
                this.putsourcedata(4, 6, 2);
                this.putsourcedata(5, 5, 4);
                this.putsourcedata(6, 6, 1);
                this.puttargetdata(4, 5, 4);
                this.puttargetdata(5, 5, 2);
                this.puttargetdata(6, 5, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
            }
            else if (this.CurrentStage == 2) {
                this.playfieldcelllength = 9;
                this.putsourcedata(3, 3, 2);
                this.putsourcedata(4, 2, 2);
                this.putsourcedata(5, 2, 2);
                this.putsourcedata(6, 3, 2);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(5, 5, 2);
                this.puttargetdata(6, 4, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
            }
            else if (this.CurrentStage == 3) {
                this.playfieldcelllength = 9;
                this.putsourcedata(3, 3, 3);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(4, 6, 3);
                this.putsourcedata(5, 4, 2);
                this.putsourcedata(6, 4, 1);
                this.putsourcedata(6, 6, 1);
                this.puttargetdata(3, 6, 2);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(4, 6, 1);
                this.puttargetdata(5, 3, 1);
                this.puttargetdata(5, 6, 3);
                this.puttargetdata(6, 3, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
            }
            else if (this.CurrentStage == 4) {
                this.playfieldcelllength = 9;
                this.putsourcedata(5, 8, 2);
                this.putsourcedata(6, 6, 3);
                this.putsourcedata(6, 8, 3);
                this.putsourcedata(7, 5, 1);
                this.putsourcedata(8, 6, 1);
                this.puttargetdata(6, 7, 3);
                this.puttargetdata(7, 6, 3);
                this.puttargetdata(7, 7, 2);
                this.puttargetdata(7, 8, 1);
                this.puttargetdata(8, 7, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'R');
            }
            else if (this.CurrentStage == 5) {
                this.playfieldcelllength = 10;
                this.putsourcedata(3, 4, 1);
                this.putsourcedata(4, 5, 4);
                this.putsourcedata(5, 6, 3);
                this.putsourcedata(7, 5, 2);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(6, 4, 2);
                this.puttargetdata(4, 6, 4);
                this.puttargetdata(6, 6, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
            }
            else if (this.CurrentStage == 6) {
                this.playfieldcelllength = 10;
                this.putsourcedata(3, 2, 2);
                this.putsourcedata(5, 3, 1);
                this.putsourcedata(4, 4, 3);
                this.puttargetdata(1, 4, 1);
                this.puttargetdata(1, 5, 2);
                this.puttargetdata(1, 6, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
            }
            else if (this.CurrentStage == 7) {
                this.playfieldcelllength = 10;
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(4, 4, 2);
                this.putsourcedata(7, 4, 4);
                this.putsourcedata(5, 5, 2);
                this.putsourcedata(6, 7, 1);
                this.puttargetdata(2, 5, 4);
                this.puttargetdata(3, 5, 2);
                this.puttargetdata(5, 5, 1);
                this.puttargetdata(7, 5, 2);
                this.puttargetdata(8, 5, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
            }
            else if (this.CurrentStage == 8) {
                this.playfieldcelllength = 9;
                this.putsourcedata(1, 4, 2);
                this.putsourcedata(1, 5, 1);
                this.putsourcedata(4, 4, 4);
                this.putsourcedata(4, 5, 3);
                this.putsourcedata(5, 3, 3);
                this.putsourcedata(5, 6, 4);
                this.putsourcedata(7, 4, 2);
                this.putsourcedata(7, 5, 1);
                this.puttargetdata(1, 3, 2);
                this.puttargetdata(1, 6, 1);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(3, 5, 4);
                this.puttargetdata(6, 4, 3);
                this.puttargetdata(6, 5, 4);
                this.puttargetdata(8, 3, 2);
                this.puttargetdata(8, 6, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'R');
            }
            else if (this.CurrentStage == 9) {
                this.playfieldcelllength = 7;
                this.putsourcedata(1, 2, 1);
                this.putsourcedata(2, 4, 4);
                this.putsourcedata(4, 6, 1);
                this.putsourcedata(6, 4, 2);
                this.puttargetdata(1, 3, 1);
                this.puttargetdata(1, 4, 4);
                this.puttargetdata(6, 3, 2);
                this.puttargetdata(6, 4, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 10) {
                this.playfieldcelllength = 6;
                this.putsourcedata(1, 2, 2);
                this.putsourcedata(2, 1, 1);
                this.putsourcedata(2, 3, 3);
                this.putsourcedata(3, 3, 1);
                this.putsourcedata(4, 5, 4);
                this.puttargetdata(1, 2, 1);
                this.puttargetdata(2, 2, 2);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(4, 4, 3);
                this.puttargetdata(5, 4, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
            }
            else if (this.CurrentStage == 11) {
                this.playfieldcelllength = 10;
                this.putsourcedata(2, 2, 2);
                this.putsourcedata(8, 2, 4);
                this.putsourcedata(2, 8, 1);
                this.putsourcedata(8, 8, 3);
                this.puttargetdata(2, 2, 1);
                this.puttargetdata(8, 2, 2);
                this.puttargetdata(2, 8, 3);
                this.puttargetdata(8, 8, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'R');
            }
            else if (this.CurrentStage == 12) {
                this.playfieldcelllength = 9;
                this.putsourcedata(3, 7, 1);
                this.putsourcedata(4, 2, 1);
                this.putsourcedata(4, 3, 2);
                this.putsourcedata(4, 6, 3);
                this.putsourcedata(5, 3, 3);
                this.putsourcedata(5, 5, 4);
                this.putsourcedata(5, 6, 2);
                this.putsourcedata(6, 4, 4);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(3, 6, 1);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(5, 5, 3);
                this.puttargetdata(6, 3, 4);
                this.puttargetdata(6, 6, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'L');
            }
            else if (this.CurrentStage == 13) {
                this.playfieldcelllength = 9;
                this.putsourcedata(1, 5, 1);
                this.putsourcedata(3, 7, 2);
                this.putsourcedata(4, 7, 3);
                this.putsourcedata(5, 7, 2);
                this.putsourcedata(6, 7, 3);
                this.putsourcedata(8, 5, 1);
                this.puttargetdata(1, 7, 1);
                this.puttargetdata(2, 6, 2);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(5, 5, 3);
                this.puttargetdata(7, 6, 2);
                this.puttargetdata(8, 7, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
            }
            else if (this.CurrentStage == 14) {
                this.playfieldcelllength = 9;
                this.putsourcedata(3, 3, 1);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(3, 6, 1);
                this.putsourcedata(4, 6, 2);
                this.putsourcedata(5, 4, 1);
                this.putsourcedata(7, 5, 2);
                this.puttargetdata(3, 5, 1);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(5, 4, 2);
                this.puttargetdata(5, 5, 1);
                this.puttargetdata(6, 5, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'L');
            }
            else if (this.CurrentStage == 15) {
                this.playfieldcelllength = 6;
                this.putsourcedata(1, 3, 4);
                this.putsourcedata(3, 2, 1);
                this.putsourcedata(4, 3, 2);
                this.putsourcedata(5, 4, 3);
                this.puttargetdata(2, 2, 1);
                this.puttargetdata(2, 4, 4);
                this.puttargetdata(4, 2, 2);
                this.puttargetdata(4, 4, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 16) {
                this.playfieldcelllength = 6;
                this.putsourcedata(1, 2, 3);
                this.putsourcedata(2, 5, 4);
                this.putsourcedata(3, 1, 1);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(4, 1, 2);
                this.putsourcedata(4, 5, 1);
                this.putsourcedata(5, 2, 4);
                this.putsourcedata(5, 4, 3);
                this.puttargetdata(1, 2, 4);
                this.puttargetdata(1, 4, 2);
                this.puttargetdata(2, 1, 3);
                this.puttargetdata(2, 5, 1);
                this.puttargetdata(4, 1, 1);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(5, 2, 2);
                this.puttargetdata(5, 4, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
            }
            else if (this.CurrentStage == 17) {
                this.playfieldcelllength = 8;
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(4, 2, 2);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(5, 6, 2);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(4, 5, 4);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(5, 5, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
            }
            else if (this.CurrentStage == 18) {
                this.playfieldcelllength = 10;
                this.putsourcedata(2, 3, 3);
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(6, 3, 1);
                this.putsourcedata(6, 5, 2);
                this.putsourcedata(3, 6, 4);
                this.putsourcedata(5, 7, 3);
                this.puttargetdata(3, 3, 3);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(6, 5, 1);
                this.puttargetdata(7, 5, 2);
                this.puttargetdata(3, 7, 4);
                this.puttargetdata(4, 7, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'L');
            }
            else if (this.CurrentStage == 19) {
                this.playfieldcelllength = 6;
                this.putsourcedata(2, 3, 1);
                this.putsourcedata(3, 1, 4);
                this.putsourcedata(3, 4, 2);
                this.putsourcedata(4, 5, 3);
                this.puttargetdata(2, 2, 1);
                this.puttargetdata(2, 4, 2);
                this.puttargetdata(4, 2, 4);
                this.puttargetdata(4, 4, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
            }
            else if (this.CurrentStage == 20) {
                this.playfieldcelllength = 11;
                this.putsourcedata(2, 4, 2);
                this.putsourcedata(2, 7, 4);
                this.putsourcedata(4, 5, 2);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(6, 7, 1);
                this.putsourcedata(7, 6, 4);
                this.putsourcedata(9, 4, 1);
                this.putsourcedata(9, 7, 3);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(3, 8, 4);
                this.puttargetdata(5, 5, 2);
                this.puttargetdata(5, 6, 1);
                this.puttargetdata(6, 5, 3);
                this.puttargetdata(6, 6, 4);
                this.puttargetdata(8, 3, 1);
                this.puttargetdata(8, 8, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 3, 'L');
            }
            else if (this.CurrentStage == 21) {
                this.playfieldcelllength = 11;
                this.putsourcedata(3, 4, 2);
                this.putsourcedata(4, 2, 1);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(4, 5, 4);
                this.putsourcedata(6, 3, 4);
                this.putsourcedata(7, 2, 2);
                this.putsourcedata(7, 3, 1);
                this.putsourcedata(7, 5, 3);
                this.puttargetdata(4, 1, 1);
                this.puttargetdata(4, 2, 3);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(7, 1, 2);
                this.puttargetdata(7, 2, 4);
                this.puttargetdata(7, 3, 1);
                this.puttargetdata(7, 4, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
            }
            else if (this.CurrentStage == 22) {
                this.playfieldcelllength = 7;
                this.putsourcedata(2, 2, 1);
                this.putsourcedata(2, 3, 2);
                this.putsourcedata(3, 4, 3);
                this.putsourcedata(4, 5, 4);
                this.putsourcedata(6, 3, 2);
                this.putsourcedata(6, 4, 1);
                this.puttargetdata(1, 3, 1);
                this.puttargetdata(2, 2, 2);
                this.puttargetdata(3, 3, 3);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(5, 5, 1);
                this.puttargetdata(6, 4, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
            }
            else if (this.CurrentStage == 23) {
                this.playfieldcelllength = 6;
                this.putsourcedata(1, 2, 4);
                this.putsourcedata(2, 3, 1);
                this.putsourcedata(3, 1, 3);
                this.putsourcedata(3, 2, 2);
                this.putsourcedata(4, 4, 4);
                this.putsourcedata(5, 2, 2);
                this.puttargetdata(2, 1, 4);
                this.puttargetdata(2, 5, 4);
                this.puttargetdata(3, 2, 3);
                this.puttargetdata(3, 4, 1);
                this.puttargetdata(4, 1, 2);
                this.puttargetdata(4, 5, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
            }
            else if (this.CurrentStage == 24) {
                this.playfieldcelllength = 10;
                this.putsourcedata(5, 3, 2);
                this.putsourcedata(2, 4, 1);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(8, 4, 1);
                this.putsourcedata(2, 6, 4);
                this.putsourcedata(5, 6, 2);
                this.putsourcedata(8, 6, 4);
                this.putsourcedata(6, 7, 3);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(7, 3, 1);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(6, 4, 2);
                this.puttargetdata(4, 6, 3);
                this.puttargetdata(6, 6, 3);
                this.puttargetdata(3, 7, 4);
                this.puttargetdata(7, 7, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
            }
            else if (this.CurrentStage == 25) {
                this.playfieldcelllength = 10;
                this.putsourcedata(3, 2, 1);
                this.putsourcedata(3, 5, 3);
                this.putsourcedata(6, 8, 4);
                this.putsourcedata(7, 4, 1);
                this.putsourcedata(8, 6, 2);
                this.puttargetdata(2, 5, 3);
                this.puttargetdata(5, 2, 1);
                this.puttargetdata(5, 5, 1);
                this.puttargetdata(5, 8, 4);
                this.puttargetdata(8, 5, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
            }
            else if (this.CurrentStage == 26) {
                this.playfieldcelllength = 11;
                this.putsourcedata(5, 5, 2);
                this.putsourcedata(7, 4, 4);
                this.putsourcedata(7, 5, 1);
                this.putsourcedata(7, 8, 3);
                this.putsourcedata(8, 6, 1);
                this.putsourcedata(9, 5, 2);
                this.puttargetdata(6, 4, 2);
                this.puttargetdata(6, 6, 1);
                this.puttargetdata(8, 5, 4);
                this.puttargetdata(8, 7, 3);
                this.puttargetdata(10, 6, 2);
                this.puttargetdata(10, 8, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 6, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 7, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 8, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 7, 5, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 4, 'R');
            }
            else if (this.CurrentStage == 27) {
                this.playfieldcelllength = 7;
                this.putsourcedata(1, 2, 3);
                this.putsourcedata(3, 1, 1);
                this.putsourcedata(5, 3, 2);
                this.putsourcedata(6, 2, 4);
                this.puttargetdata(1, 3, 1);
                this.puttargetdata(1, 4, 3);
                this.puttargetdata(6, 3, 2);
                this.puttargetdata(6, 4, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
            }
            else if (this.CurrentStage == 28) {
                this.playfieldcelllength = 10;
                this.putsourcedata(2, 4, 3);
                this.putsourcedata(2, 7, 2);
                this.putsourcedata(3, 7, 1);
                this.putsourcedata(4, 7, 4);
                this.putsourcedata(5, 4, 2);
                this.putsourcedata(6, 3, 3);
                this.putsourcedata(7, 2, 1);
                this.putsourcedata(7, 5, 4);
                this.puttargetdata(2, 6, 1);
                this.puttargetdata(3, 5, 3);
                this.puttargetdata(3, 7, 2);
                this.puttargetdata(4, 6, 4);
                this.puttargetdata(5, 3, 3);
                this.puttargetdata(6, 2, 2);
                this.puttargetdata(6, 4, 4);
                this.puttargetdata(7, 3, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 6, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'L');
            }
            else if (this.CurrentStage == 29) {
                this.playfieldcelllength = 7;
                this.putsourcedata(3, 1, 1);
                this.putsourcedata(3, 2, 4);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(3, 6, 3);
                this.putsourcedata(4, 1, 2);
                this.putsourcedata(4, 2, 3);
                this.putsourcedata(4, 3, 2);
                this.putsourcedata(4, 4, 1);
                this.puttargetdata(3, 2, 2);
                this.puttargetdata(3, 3, 4);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(3, 5, 4);
                this.puttargetdata(4, 2, 1);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(4, 5, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
            }
            else if (this.CurrentStage == 30) {
                this.playfieldcelllength = 7;
                this.putsourcedata(1, 3, 2);
                this.putsourcedata(1, 4, 2);
                this.putsourcedata(2, 2, 2);
                this.putsourcedata(2, 3, 3);
                this.putsourcedata(2, 4, 3);
                this.putsourcedata(2, 5, 2);
                this.putsourcedata(3, 2, 3);
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(4, 3, 1);
                this.putsourcedata(4, 4, 4);
                this.putsourcedata(4, 6, 3);
                this.putsourcedata(5, 2, 1);
                this.putsourcedata(5, 4, 1);
                this.putsourcedata(5, 5, 1);
                this.puttargetdata(2, 2, 2);
                this.puttargetdata(2, 3, 2);
                this.puttargetdata(2, 4, 2);
                this.puttargetdata(2, 5, 2);
                this.puttargetdata(3, 2, 3);
                this.puttargetdata(3, 3, 3);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(3, 5, 3);
                this.puttargetdata(4, 2, 4);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(4, 5, 4);
                this.puttargetdata(5, 2, 1);
                this.puttargetdata(5, 3, 1);
                this.puttargetdata(5, 4, 1);
                this.puttargetdata(5, 5, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 31) {
                this.playfieldcelllength = 7;
                this.putsourcedata(3, 2, 2);
                this.putsourcedata(3, 3, 1);
                this.putsourcedata(3, 4, 3);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(4, 2, 3);
                this.putsourcedata(4, 3, 4);
                this.putsourcedata(4, 4, 2);
                this.putsourcedata(4, 5, 1);
                this.puttargetdata(2, 3, 1);
                this.puttargetdata(2, 4, 4);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(4, 4, 2);
                this.puttargetdata(5, 3, 4);
                this.puttargetdata(5, 4, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
            }
            else if (this.CurrentStage == 32) {
                this.playfieldcelllength = 6;
                this.putsourcedata(1, 3, 1);
                this.putsourcedata(2, 3, 4);
                this.putsourcedata(3, 4, 2);
                this.putsourcedata(3, 5, 3);
                this.putsourcedata(5, 2, 2);
                this.puttargetdata(1, 4, 1);
                this.puttargetdata(2, 4, 4);
                this.puttargetdata(3, 2, 2);
                this.puttargetdata(4, 4, 3);
                this.puttargetdata(5, 4, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 33) {
                this.playfieldcelllength = 7;
                this.putsourcedata(1, 4, 2);
                this.putsourcedata(2, 3, 2);
                this.putsourcedata(3, 2, 3);
                this.putsourcedata(3, 3, 3);
                this.putsourcedata(4, 4, 1);
                this.putsourcedata(4, 5, 1);
                this.putsourcedata(5, 4, 4);
                this.putsourcedata(6, 3, 4);
                this.puttargetdata(2, 2, 2);
                this.puttargetdata(2, 5, 2);
                this.puttargetdata(3, 3, 3);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(4, 3, 1);
                this.puttargetdata(4, 4, 1);
                this.puttargetdata(5, 2, 4);
                this.puttargetdata(5, 5, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
            }
            else if (this.CurrentStage == 34) {
                this.playfieldcelllength = 6;
                this.putsourcedata(3, 3, 2);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(4, 2, 2);
                this.putsourcedata(4, 3, 4);
                this.putsourcedata(4, 4, 4);
                this.puttargetdata(2, 3, 4);
                this.puttargetdata(3, 2, 2);
                this.puttargetdata(3, 3, 2);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(4, 3, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
            }
            else if (this.CurrentStage == 35) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 4, 1);
                this.putsourcedata(2, 5, 2);
                this.putsourcedata(3, 4, 1);
                this.putsourcedata(3, 5, 2);
                this.putsourcedata(4, 3, 2);
                this.putsourcedata(4, 4, 1);
                this.putsourcedata(4, 5, 2);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(6, 3, 1);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(3, 5, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(4, 4, 3);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(5, 3, 1);
                this.puttargetdata(5, 4, 2);
                this.puttargetdata(5, 5, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'L');
            }
            else if (this.CurrentStage == 36) {
                this.playfieldcelllength = 8;
                this.putsourcedata(1, 3, 3);
                this.putsourcedata(1, 5, 4);
                this.putsourcedata(2, 4, 2);
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(6, 4, 1);
                this.putsourcedata(7, 3, 2);
                this.putsourcedata(7, 5, 1);
                this.puttargetdata(2, 4, 3);
                this.puttargetdata(3, 4, 4);
                this.puttargetdata(4, 2, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(4, 6, 4);
                this.puttargetdata(5, 4, 1);
                this.puttargetdata(6, 4, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 1, 'L');
            }
            else if (this.CurrentStage == 37) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 5, 1);
                this.putsourcedata(3, 4, 4);
                this.putsourcedata(3, 6, 2);
                this.putsourcedata(4, 5, 3);
                this.putsourcedata(4, 6, 1);
                this.putsourcedata(5, 3, 2);
                this.putsourcedata(5, 6, 4);
                this.putsourcedata(6, 5, 3);
                this.puttargetdata(2, 4, 1);
                this.puttargetdata(2, 5, 2);
                this.puttargetdata(3, 5, 4);
                this.puttargetdata(3, 6, 3);
                this.puttargetdata(5, 5, 2);
                this.puttargetdata(5, 6, 1);
                this.puttargetdata(6, 4, 3);
                this.puttargetdata(6, 5, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
            }
            else if (this.CurrentStage == 38) {
                this.playfieldcelllength = 8;
                this.putsourcedata(3, 5, 3);
                this.putsourcedata(4, 3, 2);
                this.putsourcedata(4, 4, 2);
                this.putsourcedata(4, 5, 1);
                this.putsourcedata(5, 3, 1);
                this.putsourcedata(5, 4, 3);
                this.puttargetdata(3, 3, 1);
                this.puttargetdata(3, 5, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(4, 5, 2);
                this.puttargetdata(5, 3, 3);
                this.puttargetdata(5, 5, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 3, 'R');
            }
            else if (this.CurrentStage == 39) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 3, 4);
                this.putsourcedata(2, 6, 4);
                this.putsourcedata(3, 2, 3);
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(3, 4, 3);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(3, 6, 3);
                this.putsourcedata(4, 1, 1);
                this.putsourcedata(4, 2, 2);
                this.putsourcedata(4, 4, 2);
                this.putsourcedata(4, 5, 2);
                this.putsourcedata(5, 2, 3);
                this.putsourcedata(5, 3, 4);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(5, 5, 1);
                this.putsourcedata(5, 6, 1);
                this.putsourcedata(6, 2, 2);
                this.putsourcedata(6, 3, 1);
                this.putsourcedata(6, 4, 2);
                this.putsourcedata(6, 5, 1);
                this.puttargetdata(2, 2, 4);
                this.puttargetdata(2, 6, 4);
                this.puttargetdata(3, 2, 3);
                this.puttargetdata(3, 3, 4);
                this.puttargetdata(3, 5, 4);
                this.puttargetdata(3, 6, 3);
                this.puttargetdata(4, 2, 2);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(4, 4, 4);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(4, 6, 2);
                this.puttargetdata(5, 2, 1);
                this.puttargetdata(5, 3, 2);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(5, 5, 2);
                this.puttargetdata(5, 6, 1);
                this.puttargetdata(6, 3, 1);
                this.puttargetdata(6, 4, 2);
                this.puttargetdata(6, 5, 1);
                this.puttargetdata(7, 4, 1);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 1, 'R');
            }
            else if (this.CurrentStage == 40) {
                this.playfieldcelllength = 8;
                this.putsourcedata(2, 2, 1);
                this.putsourcedata(2, 3, 3);
                this.putsourcedata(3, 2, 3);
                this.putsourcedata(3, 3, 4);
                this.putsourcedata(3, 4, 1);
                this.putsourcedata(3, 5, 4);
                this.putsourcedata(3, 6, 1);
                this.putsourcedata(3, 7, 4);
                this.putsourcedata(4, 4, 2);
                this.putsourcedata(4, 7, 2);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(6, 2, 2);
                this.putsourcedata(6, 3, 1);
                this.putsourcedata(6, 4, 3);
                this.putsourcedata(6, 5, 4);
                this.putsourcedata(7, 4, 2);
                this.puttargetdata(1, 4, 1);
                this.puttargetdata(2, 3, 3);
                this.puttargetdata(2, 5, 4);
                this.puttargetdata(3, 2, 1);
                this.puttargetdata(3, 4, 2);
                this.puttargetdata(3, 6, 1);
                this.puttargetdata(4, 1, 3);
                this.puttargetdata(4, 3, 4);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(4, 7, 4);
                this.puttargetdata(5, 2, 2);
                this.puttargetdata(5, 4, 1);
                this.puttargetdata(5, 6, 2);
                this.puttargetdata(6, 3, 3);
                this.puttargetdata(6, 5, 4);
                this.puttargetdata(7, 4, 2);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 1, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 4, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
            }
            else if (this.CurrentStage == this.MAXStageNumNormalPlus + 1) {
                this.ShowPassword = false;
                this.playfieldcelllength = 8;
                this.putsourcedata(1, 3, 3);
                this.putsourcedata(1, 4, 3);
                this.putsourcedata(1, 5, 3);
                this.putsourcedata(2, 3, 3);
                this.putsourcedata(2, 4, 3);
                this.putsourcedata(2, 5, 3);
                this.putsourcedata(3, 2, 3);
                this.putsourcedata(3, 3, 3);
                this.putsourcedata(3, 4, 3);
                this.putsourcedata(3, 5, 3);
                this.putsourcedata(4, 2, 3);
                this.putsourcedata(4, 3, 3);
                this.putsourcedata(4, 4, 3);
                this.putsourcedata(4, 5, 3);
                this.putsourcedata(4, 6, 3);
                this.putsourcedata(5, 2, 3);
                this.putsourcedata(5, 3, 3);
                this.putsourcedata(5, 4, 3);
                this.putsourcedata(5, 5, 3);
                this.putsourcedata(5, 6, 3);
                this.putsourcedata(6, 2, 3);
                this.putsourcedata(6, 3, 3);
                this.putsourcedata(6, 4, 3);
                this.putsourcedata(6, 6, 3);
                this.putsourcedata(7, 3, 3);
                this.puttargetdata(2, 2, 3);
                this.puttargetdata(2, 3, 3);
                this.puttargetdata(2, 4, 3);
                this.puttargetdata(2, 5, 3);
                this.puttargetdata(2, 6, 3);
                this.puttargetdata(3, 2, 3);
                this.puttargetdata(3, 3, 3);
                this.puttargetdata(3, 4, 3);
                this.puttargetdata(3, 5, 3);
                this.puttargetdata(3, 6, 3);
                this.puttargetdata(4, 2, 3);
                this.puttargetdata(4, 3, 3);
                this.puttargetdata(4, 4, 3);
                this.puttargetdata(4, 5, 3);
                this.puttargetdata(4, 6, 3);
                this.puttargetdata(5, 2, 3);
                this.puttargetdata(5, 3, 3);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(5, 5, 3);
                this.puttargetdata(5, 6, 3);
                this.puttargetdata(6, 2, 3);
                this.puttargetdata(6, 3, 3);
                this.puttargetdata(6, 4, 3);
                this.puttargetdata(6, 5, 3);
                this.puttargetdata(6, 6, 3);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'L');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 2, 'L');
            }
            if (this.DEBUGSHORTSTAGE) {
                for (int k = 1; k <= this.MAXplayfieldcelllength; ++k) {
                    for (int l = 1; l <= this.MAXplayfieldcelllength; ++l) {
                        this.ballexist[k][l] = false;
                        this.ballexisttarget[k][l] = false;
                        this.ballexistcolor[k][l] = 0;
                        this.ballexisttargetcolor[k][l] = 0;
                    }
                }
                this.playfieldcelllength = 11;
                this.putsourcedata(2, 2, 1);
                this.putsourcedata(3, 3, 2);
                this.putsourcedata(4, 4, 3);
                this.putsourcedata(5, 5, 4);
                this.puttargetdata(3, 2, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(6, 5, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
            }
        }
        else if (this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay) {
            this.Emptystoreballexistarrays();
            this.SilentRotation = true;
            this.ShowPassword = false;
            if (this.DEBUGSHORTSTAGE) {
                this.CurrentStage = 1;
                this.TotalStages = 1;
            }
            else {
                this.TotalStages = 16;
            }
            final Random random = new Random();
            if (this.CurrentStage <= this.TotalStages) {
                this.m1 = Math.random();
                this.playfieldcelllength = (int)(this.m1 * (this.MAXplayfieldcelllength - 6));
                this.playfieldcelllength += 7;
                this.playfieldcelllength = 6;
                this.m1 = Math.random();
                this.TotalNumOfColoredCells = (int)(this.m1 * 10.0);
                ++this.TotalNumOfColoredCells;
                this.m1 = Math.random();
                this.RandomTotalRotate = (int)(this.m1 * 5.0);
                if (this.ExtraPlay || this.ExtraPlusPlay) {
                    if (this.CurrentStage == 1 || this.CurrentStage == 2) {
                        this.PlayLevel = 1;
                    }
                    else if (this.CurrentStage == 3 || this.CurrentStage == 4) {
                        this.PlayLevel = 2;
                    }
                    else if (this.CurrentStage == 5 || this.CurrentStage == 6) {
                        this.PlayLevel = 3;
                    }
                    else if (this.CurrentStage == 7 || this.CurrentStage == 8) {
                        this.PlayLevel = 4;
                    }
                    else if (this.CurrentStage == 9 || this.CurrentStage == 10) {
                        this.PlayLevel = 5;
                    }
                    else if (this.CurrentStage == 11 || this.CurrentStage == 12) {
                        this.PlayLevel = 6;
                    }
                    else if (this.CurrentStage == 13 || this.CurrentStage == 14) {
                        this.PlayLevel = 7;
                    }
                    else if (this.CurrentStage == 15 || this.CurrentStage == 16) {
                        this.PlayLevel = 8;
                    }
                    else if (this.CurrentStage == 17 || this.CurrentStage == 18) {
                        this.PlayLevel = 9;
                    }
                    else if (this.CurrentStage == 19 || this.CurrentStage == 20) {
                        this.PlayLevel = 10;
                    }
                }
                else {
                    this.CurrentStage = 1;
                    this.m1 = Math.random();
                    this.PlayLevel = (int)(this.m1 * 5.0);
                    this.PlayLevel += 2;
                }
                this.SelectSetForLevel();
                for (int n = 1; n <= this.TotalNumOfColoredCells; ++n) {
                    this.m1 = Math.random();
                    this.RandomColor = (int)(this.m1 * 4.0);
                    ++this.RandomColor;
                    if (this.AllInOneColor) {
                        this.RandomColor = 5;
                    }
                    this.GetOutOfLoopForRandomCell = false;
                    while (!this.GetOutOfLoopForRandomCell) {
                        this.m1 = Math.random();
                        this.RandomXTarget = (int)(this.m1 * (this.playfieldcelllength - 1));
                        ++this.RandomXTarget;
                        this.m1 = Math.random();
                        this.RandomYTarget = (int)(this.m1 * (this.playfieldcelllength - 1));
                        ++this.RandomYTarget;
                        this.GetOutOfLoopForRandomCell = true;
                        if ((this.RandomXTarget == 1 || this.RandomXTarget == this.playfieldcelllength - 1) && this.RandomYTarget == 1) {
                            this.GetOutOfLoopForRandomCell = false;
                        }
                        if (this.RandomXTarget == 1 && (this.RandomYTarget == 1 || this.RandomYTarget == this.playfieldcelllength - 1)) {
                            this.GetOutOfLoopForRandomCell = false;
                        }
                        if (this.RandomXTarget == this.playfieldcelllength - 1 && this.RandomYTarget == this.playfieldcelllength - 1) {
                            this.GetOutOfLoopForRandomCell = false;
                        }
                        for (int n2 = 1; n2 <= this.playfieldcelllength; ++n2) {
                            for (int n3 = 1; n3 <= this.playfieldcelllength; ++n3) {
                                if (n2 == this.RandomXTarget && n3 == this.RandomYTarget && this.ballexisttarget[n2][n3]) {
                                    this.GetOutOfLoopForRandomCell = false;
                                }
                            }
                        }
                    }
                    this.puttargetdata(this.RandomXTarget, this.RandomYTarget, this.RandomColor);
                    this.storeballexisttarget[this.RandomXTarget][this.RandomYTarget] = true;
                    this.storeballexisttargetcolor[this.RandomXTarget][this.RandomYTarget] = this.RandomColor;
                    this.putsourcedata(this.RandomXTarget, this.RandomYTarget, this.RandomColor);
                }
                this.RandomTotalRotate += this.TotalNumOfColoredCells;
                this.NumOfSolutionSteps = 0;
                for (int n4 = 1; n4 <= this.RandomTotalRotate; ++n4) {
                    this.GetOutOfLoopForRandomCell = false;
                    while (!this.GetOutOfLoopForRandomCell) {
                        this.m1 = Math.random();
                        this.RandomXTarget = (int)(this.m1 * (this.playfieldcelllength - 3));
                        ++this.RandomXTarget;
                        this.m1 = Math.random();
                        this.RandomYTarget = (int)(this.m1 * (this.playfieldcelllength - 4));
                        ++this.RandomYTarget;
                        this.GetOutOfLoopForRandomCell = true;
                        if (this.RandomXTarget == 1) {
                            this.GetOutOfLoopForRandomCell = false;
                        }
                        if (this.GetOutOfLoopForRandomCell) {
                            this.ContainCell = false;
                            if (this.ballexist[this.RandomXTarget][this.RandomYTarget] || this.ballexist[this.RandomXTarget + 1][this.RandomYTarget] || this.ballexist[this.RandomXTarget + 2][this.RandomYTarget + 1] || this.ballexist[this.RandomXTarget + 2][this.RandomYTarget + 2] || this.ballexist[this.RandomXTarget + 1][this.RandomYTarget + 3] || this.ballexist[this.RandomXTarget][this.RandomYTarget + 3] || this.ballexist[this.RandomXTarget - 1][this.RandomYTarget + 2] || this.ballexist[this.RandomXTarget - 1][this.RandomYTarget + 1]) {
                                this.ContainCell = true;
                            }
                            if (this.ContainCell) {
                                continue;
                            }
                            this.GetOutOfLoopForRandomCell = false;
                        }
                    }
                    this.m1 = Math.random();
                    this.tempint = (int)(this.m1 * 2.0);
                    ++this.tempint;
                    if (this.tempint == 1) {
                        this.m1 = Math.random();
                        this.tempint2 = (int)(this.m1 * 2.0);
                        ++this.tempint2;
                        this.activecellmarkx = this.RandomXTarget;
                        this.activecellmarky = this.RandomYTarget;
                        for (int n5 = 1; n5 <= this.tempint2; ++n5) {
                            this.RotateToRight();
                            this.putsolutiondata(++this.NumOfSolutionSteps, this.activecellmarkx, this.activecellmarky, 'L');
                        }
                    }
                    else {
                        this.m1 = Math.random();
                        this.tempint2 = (int)(this.m1 * 2.0);
                        ++this.tempint2;
                        this.activecellmarkx = this.RandomXTarget;
                        this.activecellmarky = this.RandomYTarget;
                        for (int n6 = 1; n6 <= this.tempint2; ++n6) {
                            this.RotateToLeft();
                            this.putsolutiondata(++this.NumOfSolutionSteps, this.activecellmarkx, this.activecellmarky, 'R');
                        }
                    }
                }
                this.ReSortSolutionData();
                this.NumOfSolutionSteps += this.ExtraSteps;
            }
            this.SilentRotation = false;
            if (this.DEBUGSHORTSTAGE && !this.EndlessPlay && !this.EndlessPlusPlay) {
                for (int n7 = 1; n7 <= this.MAXplayfieldcelllength; ++n7) {
                    for (int n8 = 1; n8 <= this.MAXplayfieldcelllength; ++n8) {
                        this.ballexist[n7][n8] = false;
                        this.ballexisttarget[n7][n8] = false;
                        this.ballexistcolor[n7][n8] = 0;
                        this.ballexisttargetcolor[n7][n8] = 0;
                    }
                }
                this.playfieldcelllength = 11;
                this.putsourcedata(2, 2, 1);
                this.putsourcedata(3, 3, 2);
                this.putsourcedata(4, 4, 3);
                this.putsourcedata(5, 5, 4);
                this.puttargetdata(3, 2, 1);
                this.puttargetdata(4, 3, 2);
                this.puttargetdata(5, 4, 3);
                this.puttargetdata(6, 5, 4);
                this.NumOfSolutionSteps = 0;
                this.putsolutiondata(++this.NumOfSolutionSteps, 2, 2, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 3, 3, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 4, 4, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
                this.putsolutiondata(++this.NumOfSolutionSteps, 5, 5, 'R');
            }
        }
        if (this.CurrentStage == this.TotalStages) {
            this.LastStage = true;
        }
        this.playfieldxbegin = this.winsizex - (this.winsizex - this.leftinfoareapixellength) / 2 - this.cellpixellength * (this.playfieldcelllength / 2) + this.cellpixellength / 2;
        this.playfieldybegin = this.winsizey / 2 - this.cellpixellength * (this.playfieldcelllength / 2) + this.cellpixellength / 2;
        this.playfieldxend = this.playfieldcelllength * (this.innercellsize + this.spacebetweenlineNball * 2);
        this.playfieldyend = this.playfieldcelllength * (this.innercellsize + this.spacebetweenlineNball * 2);
        this.tempplayfieldxbegin = this.playfieldxbegin;
        this.tempplayfieldybegin = this.playfieldybegin;
        this.cellxstart[1][1] = this.tempplayfieldxbegin;
        this.cellystart[1][1] = this.tempplayfieldybegin;
        for (int n9 = 1; n9 <= this.playfieldcelllength; ++n9) {
            for (int n10 = 1; n10 <= this.playfieldcelllength; ++n10) {
                this.cellxstart[n9][n10] = this.tempplayfieldxbegin;
                this.cellystart[n9][n10] = this.tempplayfieldybegin;
                this.tempplayfieldybegin = this.tempplayfieldybegin + this.innercellsize + 1;
            }
            this.tempplayfieldybegin = this.playfieldybegin;
            this.tempplayfieldxbegin = this.tempplayfieldxbegin + this.innercellsize + 1;
        }
        this.activecellmarkx = 2;
        this.activecellmarky = 1;
    }
    
    public void CheckIfStageClear() {
        boolean b = true;
        for (int i = 1; i <= this.playfieldcelllength; ++i) {
            for (int j = 1; j <= this.playfieldcelllength; ++j) {
                if (this.ballexist[i][j] != this.ballexisttarget[i][j] || this.ballexistcolor[i][j] != this.ballexisttargetcolor[i][j]) {
                    b = false;
                }
            }
        }
        if (b) {
            this.StageClear = true;
            this.InitializeButtonBoolean();
        }
    }
    
    public void CheckIfStageClearedInAutoSolving() {
        boolean b = true;
        for (int i = 1; i <= this.playfieldcelllength; ++i) {
            for (int j = 1; j <= this.playfieldcelllength; ++j) {
                if (this.ballexist[i][j] != this.ballexisttarget[i][j] || this.ballexistcolor[i][j] != this.ballexisttargetcolor[i][j]) {
                    b = false;
                }
            }
        }
        if (b) {
            this.SolvingFinished = true;
            this.InitializeButtonBoolean();
        }
    }
    
    public void InitializeArrowImageLeftRightBoolean() {
        this.CurrentlyInHowToPlayImageBanner = false;
        this.CurrentlyInNormalImageBanner = false;
        this.CurrentlyInNormalPlusImageBanner = false;
        this.CurrentlyInExtraImageBanner = false;
        this.CurrentlyInExtraPlusImageBanner = false;
        this.CurrentlyInEndlessImageBanner = false;
        this.CurrentlyInEndlessPlusImageBanner = false;
        this.CurrentlyInEnterPasswordImageBanner = false;
        this.CurrentlyInAboutImageBanner = false;
        this.CurrentlyInLegalImageBanner = false;
        this.DisplayArrowImage = false;
    }
    
    public void InitializeButtonBoolean() {
        this.CurrentlyInQuitButtonImage = false;
        this.CurrentlyInResetButtonImage = false;
        this.CurrentlyInSolveButtonImage = false;
        this.CurrentlyInSolveButtonImage = false;
        this.CurrentlyInPlayButtonImage = false;
        this.CurrentlyInBackButtonImage = false;
    }
    
    public void reinitall() {
        this.activecellmarkx = 2;
        this.activecellmarkx = 1;
        this.gameOver = false;
        this.CurrentStage = 1;
        this.TotalStages = 1;
        this.NumOfSolutionSteps = 1;
        this.CurrentNumOfSteps = 1;
        this.playfieldcelllength = 11;
        this.LastStage = false;
        this.ShowPassword = false;
        this.AllCleared = false;
        this.NormalPlayAllCleared = false;
        this.NormalPlusPlayAllCleared = false;
        this.ExtraPlayAllCleared = false;
        this.ExtraPlusPlayAllCleared = false;
        this.EndlessPlayAllCleared = false;
        this.EndlessPlusPlayAllCleared = false;
        this.InNormalPlayAllClearedScreen = false;
        this.InNormalPlusPlayAllClearedScreen = false;
        this.InExtraPlayAllClearedScreen = false;
        this.InEndlessPlayAllClearedScreen = false;
        this.InEndlessPlusPlayAllClearedScreen = false;
        this.InShowPasswordScreen = false;
        this.RightMouseClickedinPlayfield = false;
        this.LeftMouseClickedinPlayfield = false;
        this.MouseMovementLocked = false;
        this.InDisplayStepReachedMaxScreen = false;
        this.SolvePressedOnce = false;
        this.WaitingForMouseClick = false;
        this.InitializeArrowImageLeftRightBoolean();
        this.InGamePlayMode = false;
        this.MessageInPlayField1 = " ";
        this.MessageInPlayField2 = " ";
        this.MessageInPlayField3 = " ";
        for (int i = 1; i <= this.MAXplayfieldcelllength; ++i) {
            for (int j = 1; j <= this.MAXplayfieldcelllength; ++j) {
                this.ballexist[i][j] = false;
                this.ballexisttarget[i][j] = false;
                this.ballexistcolor[i][j] = 0;
                this.ballexisttargetcolor[i][j] = 0;
            }
        }
        this.putsourcedata(4, 2, 1);
        this.putsourcedata(7, 2, 2);
        this.putsourcedata(5, 9, 3);
        this.putsourcedata(6, 9, 4);
        this.puttargetdata(3, 3, 1);
        this.puttargetdata(8, 3, 2);
        this.puttargetdata(3, 8, 3);
        this.puttargetdata(8, 8, 4);
        this.NumOfSolutionSteps = 0;
        this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
        this.putsolutiondata(++this.NumOfSolutionSteps, 6, 6, 'L');
        this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'R');
        this.putsolutiondata(++this.NumOfSolutionSteps, 4, 6, 'R');
        this.putsolutiondata(++this.NumOfSolutionSteps, 6, 2, 'R');
        this.putsolutiondata(++this.NumOfSolutionSteps, 4, 2, 'L');
        this.PasswordEnteredString = "";
        this.DGSX = (this.winsizex - this.leftinfoareapixellength) / 2 + this.leftinfoareapixellength - 70;
        this.LowAll = 40;
        this.Hi1 = this.LowAll - 5;
        this.Hi2 = this.Hi1;
        this.Hi3 = this.Hi1;
        this.Hi4 = this.Hi1;
        this.Hi5 = this.Hi1;
        this.Hi6 = this.Hi1;
        this.Hi7 = this.Hi1;
        this.Hi8 = this.Hi1;
        this.Hi9 = this.Hi1;
        this.Hi10 = this.Hi1;
        this.Hi11 = this.Hi1;
        this.DGSY1 = this.LowAll - 1;
        this.LDGSY1 = this.DGSY1;
        this.DGSY2 = this.LowAll - 2;
        this.LDGSY2 = this.DGSY2;
        this.DGSY3 = this.LowAll - 3;
        this.LDGSY3 = this.DGSY3;
        this.DGSY4 = this.LowAll - 4;
        this.LDGSY4 = this.DGSY4;
        this.DGSY5 = this.LowAll - 3;
        this.LDGSY5 = this.DGSY5;
        this.DGSY6 = this.LowAll - 1;
        this.LDGSY6 = this.DGSY6;
        this.DGSY7 = this.LowAll - 2;
        this.LDGSY7 = this.DGSY7;
        this.DGSY8 = this.LowAll - 3;
        this.LDGSY8 = this.DGSY8;
        this.DGSY9 = this.LowAll - 4;
        this.LDGSY9 = this.DGSY9;
        this.DGSY10 = this.LowAll - 3;
        this.LDGSY10 = this.DGSY10;
        this.DGSY11 = this.LowAll - 2;
        this.LDGSY11 = this.DGSY11;
        this.DGSInc1 = -1;
        this.DGSInc2 = this.DGSInc1;
        this.DGSInc3 = this.DGSInc1;
        this.DGSInc4 = this.DGSInc1;
        this.DGSInc5 = this.DGSInc1;
        this.DGSInc6 = this.DGSInc1;
        this.DGSInc7 = this.DGSInc1;
        this.DGSInc8 = this.DGSInc1;
        this.DGSInc9 = this.DGSInc1;
        this.DGSInc10 = this.DGSInc1;
        this.DGSInc11 = this.DGSInc1;
    }
    
    public void RestoreData() {
        this.CurrentNumOfSteps = 0;
        for (int i = 1; i < this.MAXplayfieldcelllength; ++i) {
            for (int j = 1; j < this.MAXplayfieldcelllength; ++j) {
                this.ballexist[i][j] = this.storeballexist[i][j];
                this.ballexistcolor[i][j] = this.storeballexistcolor[i][j];
                this.ballexisttarget[i][j] = this.storeballexisttarget[i][j];
                this.ballexisttargetcolor[i][j] = this.storeballexisttargetcolor[i][j];
            }
        }
        this.activecellmarkx = 2;
        this.activecellmarky = 1;
    }
    
    public void SolveStage() {
        if (this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay) {
            this.RestoreData();
        }
        else {
            this.FillStage();
        }
        this.repaint();
        this.Solving = true;
        this.InitializeButtonBoolean();
        this.Counter = 0;
        this.MouseMovementLocked = true;
        while (this.Solving) {
            ++this.Counter;
            try {
                this.MouseMovementLocked = true;
                this.activecellmarkx = this.SolutionDataX[this.Counter];
                this.activecellmarky = this.SolutionDataY[this.Counter];
                this.repaint();
                if (this.DEBUGFLAG) {
                    Thread.sleep(this.delayvalue);
                }
                else {
                    Thread.sleep(this.delayvalue * 90);
                }
                if (this.SolutionDataDir[this.Counter] == 'R') {
                    this.RotateToRight();
                    this.MouseMovementLocked = true;
                }
                else {
                    this.RotateToLeft();
                    this.MouseMovementLocked = true;
                }
                if (this.DEBUGFLAG) {
                    Thread.sleep(this.delayvalue);
                }
                else {
                    Thread.sleep(this.delayvalue * 90);
                }
            }
            catch (InterruptedException ex) {
                break;
            }
            if (this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay) {
                this.CheckIfStageClearedInAutoSolving();
                if (this.SolvingFinished) {
                    this.Solving = false;
                    this.SolvingFinished = false;
                }
            }
            if (this.Counter == this.NumOfSolutionSteps) {
                this.Solving = false;
            }
        }
    }
    
    public void run() {
        this.reinitall();
        this.LoadingImages = true;
        this.CurrentStatusBarLength = 1;
        this.InTitleScreen = false;
        for (int i = 0; i < this.NumOfImages; ++i) {
            try {
                this.mt.waitForID(i);
            }
            catch (InterruptedException ex) {
                return;
            }
            this.CurrentStatusBarLength += this.StatusBarOnePortion * this.PicSize[i];
            this.repaint();
        }
        this.LoadingImages = false;
        this.InTitleScreen = true;
        while (!this.gameQuit) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {
                break;
            }
            this.reinitall();
            this.InTitleScreen = true;
            this.repaint();
            while (this.InTitleScreen) {
                try {
                    Thread.sleep(1L);
                }
                catch (InterruptedException ex3) {
                    break;
                }
                this.NormalPlay = false;
                this.NormalPlusPlay = false;
                this.ExtraPlay = false;
                this.ExtraPlusPlay = false;
                this.EndlessPlay = false;
                this.EndlessPlusPlay = false;
                this.InitializeButtonBoolean();
                this.repaint();
            }
            this.CursorPWD = 1;
            while (this.InNormalExplainScreen || this.InNormalPlusExplainScreen || this.InExtraExplainScreen || this.InExtraPlusExplainScreen || this.InEndlessExplainScreen || this.InEndlessPlusExplainScreen || this.InPasswordScreen || this.InAboutScreen || this.InLegalScreen || this.InHowToPlayScreen) {
                try {
                    Thread.sleep(1L);
                }
                catch (InterruptedException ex4) {
                    break;
                }
                this.repaint();
            }
            this.JustOutOfAutoSolve = false;
            if (this.PlayButtonPressed) {
                this.InGamePlayMode = true;
                this.PlayButtonPressed = false;
            }
            if (this.InGamePlayMode) {
                try {
                    Thread.sleep(1L);
                }
                catch (InterruptedException ex5) {
                    break;
                }
                this.SolvePressedOnce = false;
                this.MouseMovementLocked = false;
                while (!this.gameOver) {
                    try {
                        Thread.sleep(1L);
                    }
                    catch (InterruptedException ex6) {
                        break;
                    }
                    if ((this.JustOutOfAutoSolve || this.ResetPressed) && (this.ExtraPlay || this.ExtraPlusPlay || this.EndlessPlay || this.EndlessPlusPlay)) {
                        this.RestoreData();
                    }
                    else {
                        this.FillStage();
                    }
                    this.JustOutOfAutoSolve = false;
                    this.StageClear = false;
                    this.QuitPressed = false;
                    this.ResetPressed = false;
                    this.SolvePressed = false;
                    while (!this.StageClear && !this.QuitPressed && !this.ResetPressed && !this.SolvePressed) {
                        try {
                            Thread.sleep(1L);
                        }
                        catch (InterruptedException ex7) {
                            break;
                        }
                        this.repaint();
                        if ((this.NormalPlusPlay || this.ExtraPlusPlay || this.EndlessPlusPlay) && this.CurrentNumOfSteps == this.NumOfSolutionSteps) {
                            this.InDisplayStepReachedMaxScreen = true;
                            this.MessageInPlayField1 = this.ReachedMaxStepsHelpMsg1;
                            this.MessageInPlayField2 = this.ReachedMaxStepsHelpMsg2;
                            this.MessageInPlayField3 = this.ReachedMaxStepsHelpMsg3;
                            this.repaint();
                            this.MouseMovementLocked = true;
                            while (this.InDisplayStepReachedMaxScreen) {
                                this.WaitingForMouseClick = true;
                                try {
                                    Thread.sleep(1L);
                                }
                                catch (InterruptedException ex8) {
                                    break;
                                }
                                try {
                                    Thread.sleep(this.delayvalue * 4);
                                }
                                catch (InterruptedException ex9) {
                                    break;
                                }
                                this.repaint();
                            }
                            this.MouseMovementLocked = false;
                            this.ResetPressed = true;
                        }
                        if (this.RightMouseClickedinPlayfield) {
                            this.RotateToRight();
                            this.CheckIfStageClear();
                        }
                        else if (this.LeftMouseClickedinPlayfield) {
                            this.RotateToLeft();
                            this.CheckIfStageClear();
                        }
                        if (this.UndoPressed) {
                            if (this.CurrentNumOfSteps > 0 && this.CurrentNumOfSteps < this.MAXStepHistory) {
                                this.MouseMovementLocked = true;
                                this.activecellmarkx = this.HistoryActiveCellMarkX[this.CurrentNumOfSteps - 1];
                                this.activecellmarky = this.HistoryActiveCellMarkY[this.CurrentNumOfSteps - 1];
                                if (this.HistoryLeftRight[this.CurrentNumOfSteps - 1] == 'R') {
                                    this.RotateToLeft();
                                    --this.CurrentNumOfSteps;
                                }
                                else {
                                    this.RotateToRight();
                                    --this.CurrentNumOfSteps;
                                }
                                this.MouseMovementLocked = false;
                            }
                            else if (this.CurrentNumOfSteps >= this.MAXStepHistory) {
                                this.MessageInPlayField1 = this.UndoReachedMaxLine1;
                                this.MessageInPlayField2 = this.UndoReachedMaxLine2;
                                this.MessageInPlayField3 = this.UndoReachedMaxLine3;
                            }
                            this.UndoPressed = false;
                        }
                    }
                    if (!this.gameOver && !this.ResetPressed && !this.SolvePressed) {
                        this.InStageClearScreen = true;
                        this.MouseMovementLocked = true;
                        if (this.SolvePressedOnce) {
                            this.MessageInPlayField1 = this.SolveWasPressedHelpMsg1;
                            this.MessageInPlayField2 = this.SolveWasPressedHelpMsg2;
                            this.MessageInPlayField3 = this.SolveWasPressedHelpMsg3;
                        }
                        while (this.InStageClearScreen) {
                            try {
                                Thread.sleep(1L);
                            }
                            catch (InterruptedException ex10) {
                                break;
                            }
                            try {
                                Thread.sleep(this.delayvalue * 4);
                            }
                            catch (InterruptedException ex11) {
                                break;
                            }
                            this.repaint();
                        }
                        if (this.ShowPassword && !this.SolvePressedOnce) {
                            this.MouseMovementLocked = true;
                            while (this.InShowPasswordScreen) {
                                try {
                                    Thread.sleep(1L);
                                }
                                catch (InterruptedException ex12) {
                                    break;
                                }
                                try {
                                    Thread.sleep(this.delayvalue * 4);
                                }
                                catch (InterruptedException ex13) {
                                    break;
                                }
                                this.repaint();
                            }
                            this.MouseMovementLocked = false;
                        }
                        if (!this.ResetPressed && !this.SolvePressedOnce) {
                            ++this.CurrentStage;
                        }
                        if (this.CurrentStage - 1 > this.TotalStages) {
                            this.CurrentStage = 1;
                        }
                        else if (this.CurrentStage - 1 == this.TotalStages) {
                            this.AllCleared = true;
                            if (this.NormalPlay) {
                                this.NormalPlayAllCleared = true;
                                this.InNormalPlayAllClearedScreen = true;
                                while (this.InNormalPlayAllClearedScreen) {
                                    try {
                                        Thread.sleep(1L);
                                    }
                                    catch (InterruptedException ex14) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep(this.delayvalue * 4);
                                    }
                                    catch (InterruptedException ex15) {
                                        break;
                                    }
                                    this.repaint();
                                }
                                this.repaint();
                            }
                            else if (this.NormalPlusPlay) {
                                this.NormalPlusPlayAllCleared = true;
                                this.InNormalPlusPlayAllClearedScreen = true;
                                while (this.InNormalPlusPlayAllClearedScreen) {
                                    try {
                                        Thread.sleep(1L);
                                    }
                                    catch (InterruptedException ex16) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep(this.delayvalue * 4);
                                    }
                                    catch (InterruptedException ex17) {
                                        break;
                                    }
                                    this.repaint();
                                }
                                this.repaint();
                            }
                            else if (this.ExtraPlay) {
                                this.JustOutOfAutoSolve = false;
                                this.ExtraPlayAllCleared = true;
                                this.InExtraPlayAllClearedScreen = true;
                                while (this.InExtraPlayAllClearedScreen) {
                                    try {
                                        Thread.sleep(1L);
                                    }
                                    catch (InterruptedException ex18) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep(this.delayvalue * 4);
                                    }
                                    catch (InterruptedException ex19) {
                                        break;
                                    }
                                    this.repaint();
                                }
                                this.repaint();
                            }
                            else if (this.ExtraPlusPlay) {
                                this.JustOutOfAutoSolve = false;
                                this.ExtraPlusPlayAllCleared = true;
                                this.InExtraPlusPlayAllClearedScreen = true;
                                while (this.InExtraPlusPlayAllClearedScreen) {
                                    try {
                                        Thread.sleep(1L);
                                    }
                                    catch (InterruptedException ex20) {
                                        break;
                                    }
                                    try {
                                        Thread.sleep(this.delayvalue * 4);
                                    }
                                    catch (InterruptedException ex21) {
                                        break;
                                    }
                                    this.repaint();
                                }
                                this.repaint();
                            }
                            this.gameOver = true;
                            this.InGamePlayMode = false;
                        }
                    }
                    if (this.SolvePressed) {
                        this.SolveStage();
                        if (!this.MouseClickedOutofAutoSolve) {
                            this.WaitingForMouseClick = true;
                            this.MessageInPlayField1 = this.ClickMouseHelpMsg1;
                            this.MessageInPlayField2 = this.ClickMouseHelpMsg2;
                            this.MessageInPlayField3 = this.ClickMouseHelpMsg3;
                            this.repaint();
                            while (this.WaitingForMouseClick) {
                                try {
                                    Thread.sleep(1L);
                                }
                                catch (InterruptedException ex22) {
                                    break;
                                }
                                try {
                                    Thread.sleep(this.delayvalue * 4);
                                }
                                catch (InterruptedException ex23) {
                                    break;
                                }
                                this.repaint();
                            }
                        }
                        this.MouseClickedOutofAutoSolve = false;
                        this.Solving = false;
                        this.MouseMovementLocked = false;
                        this.repaint();
                        this.JustOutOfAutoSolve = true;
                    }
                    if (this.SolvePressedOnce) {
                        this.JustOutOfAutoSolve = true;
                    }
                }
            }
        }
    }
}
