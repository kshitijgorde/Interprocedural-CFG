import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bap150b extends Applet implements Runnable
{
    boolean testmode;
    int StatusBarLength;
    int NumOfImages;
    int StatusBarOnePortion;
    boolean[] PicLoad;
    int[] PicSize;
    int CurrentStatusBarLength;
    MediaTracker mt;
    boolean inFinalMessageScreen;
    boolean CongMoveCompleted;
    boolean CircleCompleted;
    boolean exitthisloop;
    boolean actionTaken;
    int delaydiv2;
    int delaydiv3;
    int delaydiv4;
    int delaydiv5;
    int delaydiv10;
    int thisstagepadxsizediv2;
    int ppanelx;
    int ppanely;
    boolean InCongratScreen;
    boolean InDisplayPassword;
    double m1;
    boolean ExitAboutScreen;
    boolean GotoAboutScreen;
    boolean inAboutScreen;
    Scrollbar scr;
    int[] tempinteger;
    int currentnumofextra;
    int currentnumofextend;
    boolean stoprandomcreating;
    int thisstagepadxsize;
    int numextrabricks;
    int numextendbricks;
    int maxextrabricks;
    int maxextendbricks;
    int[] extrabrickx;
    int[] extrabricky;
    int[] extendbrickx;
    int[] extendbricky;
    int[] congratx;
    int[] congraty;
    int[] congratxl;
    int[] congratyl;
    int ang;
    boolean InWaitForStart;
    boolean ExitPassword;
    String PasswordEnteredString;
    boolean EnterPressed;
    boolean GotoPasswordScreen;
    int FirstChoiceBeginX;
    int FirstChoiceBeginY;
    int FirstChoiceLengthX;
    int FirstChoiceLengthY;
    int FirstChoiceEndX;
    int FirstChoiceEndY;
    int SecondChoiceBeginX;
    int SecondChoiceBeginY;
    int SecondChoiceLengthX;
    int SecondChoiceLengthY;
    int SecondChoiceEndX;
    int SecondChoiceEndY;
    boolean AllClear;
    boolean StageClear;
    int CurrentStage;
    int TotalStages;
    int CursorPWD;
    boolean gameQuit;
    Image titlepicture;
    Image playnowpicture;
    Image passwordpicture;
    Image passwordpanelpicture;
    Image picturebrick0;
    Image picturebrick1;
    Image picturebrick2;
    Image picturebrick3;
    Image picturebrick4;
    Image picturebrick5;
    Image picturebrick6;
    Image picturebrickone;
    Image picturebrickext;
    Image picturepadside;
    Image trophypicture;
    boolean inTitle;
    boolean inPasswordScreen;
    AudioClip au;
    AudioClip auwall;
    int delayvalue;
    int ballsize;
    int ballsizehalf;
    int ballmovement;
    Random rnd;
    int numball;
    int winsizex;
    int winsizey;
    int innerxsize;
    int innerysize;
    int minx;
    int miny;
    int maxx;
    int maxy;
    int currentx;
    int currenty;
    int xmove;
    int ymove;
    int padx;
    int pady;
    int origpadxsize;
    int padxsize;
    int padysize;
    int leftframexbegin;
    int leftframeybegin;
    int leftframexend;
    int leftframeyend;
    int upframexbegin;
    int upframeybegin;
    int upframexend;
    int upframeyend;
    int rightframexbegin;
    int rightframeybegin;
    int rightframexend;
    int rightframeyend;
    int winx;
    int winy;
    boolean gameover;
    boolean padMoved;
    Graphics myG;
    int previousx;
    int previousy;
    int previouspadx;
    int previouspady;
    boolean waiting;
    int centerx;
    int centery;
    int stagex;
    int stagey;
    int ballremainx;
    int ballremainy;
    int numbx;
    int numby;
    int firstblockpositionx;
    int firstblockpositiony;
    int tempx;
    int tempy;
    boolean[][] blocks;
    int[][] blockx;
    int[][] blocky;
    Color[] color;
    int[][] blockcolor;
    String[] StagePattern;
    int lenofblock;
    int heightofblock;
    int tempint;
    int tempint2;
    int incornerx1;
    int incornerx2;
    int incornerx3;
    int incornerx4;
    int incornery1;
    int incornery2;
    int incornery3;
    int incornery4;
    Font f;
    int heightIncrement;
    String[] CopyrightString;
    int[] LenofCopyrightdiv2;
    int[] YofCopyright;
    int CopyrightRow;
    String OKButton;
    int LenofOKButton;
    int YofOKButton;
    String Speedline;
    int LenofSpeedline;
    int YofSpeedline;
    String Click;
    int LenofClick;
    int YofClick;
    String AboutButton;
    int LenofAboutButton;
    int YofAboutButton;
    String ClickChoice;
    int LenofClickChoice;
    int YofClickChoice;
    String TitleCopyright;
    int LenofTitleCopyright;
    int YofTitleCopyright;
    String MyEmail;
    int LenofMyEmail;
    int YofMyEmail;
    String SpeedEqual;
    String PasswordFor;
    int LenofPasswordFor;
    int YofPasswordFor;
    String ActualPassword;
    int LenofActualPassword;
    int YofActualPassword;
    Thread thread;
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("South", this.scr = new Scrollbar(0, 30, 10, 0, 100));
        this.PicLoad = new boolean[this.NumOfImages];
        (this.PicSize = new int[this.NumOfImages])[0] = 8;
        this.PicSize[1] = 0;
        this.PicSize[2] = 1;
        this.PicSize[3] = 1;
        this.PicSize[4] = 1;
        this.PicSize[5] = 15;
        this.PicSize[6] = 1;
        this.PicSize[7] = 1;
        this.PicSize[8] = 1;
        this.PicSize[9] = 1;
        this.PicSize[10] = 1;
        this.PicSize[11] = 1;
        this.PicSize[12] = 2;
        this.PicSize[13] = 1;
        this.PicSize[14] = 9;
        int n = 0;
        for (int i = 1; i < this.NumOfImages; ++i) {
            n += this.PicSize[i];
        }
        this.StatusBarOnePortion = this.StatusBarLength / n;
        this.CopyrightRow = 23;
        this.CopyrightString = new String[this.CopyrightRow + 1];
        this.LenofCopyrightdiv2 = new int[this.CopyrightRow + 1];
        this.YofCopyright = new int[this.CopyrightRow + 1];
        this.numextrabricks = 0;
        this.numextendbricks = 0;
        this.maxextrabricks = 11;
        this.maxextendbricks = 10;
        this.tempinteger = new int[this.maxextrabricks + this.maxextendbricks + 2];
        this.extrabrickx = new int[this.maxextrabricks + 1];
        this.extrabricky = new int[this.maxextrabricks + 1];
        this.extendbrickx = new int[this.maxextendbricks + 1];
        this.extendbricky = new int[this.maxextendbricks + 1];
        this.FirstChoiceEndX = this.FirstChoiceBeginX + this.FirstChoiceLengthX;
        this.FirstChoiceEndY = this.FirstChoiceBeginY + this.FirstChoiceLengthY;
        this.SecondChoiceEndX = this.SecondChoiceBeginX + this.SecondChoiceLengthX;
        this.SecondChoiceEndY = this.SecondChoiceBeginY + this.SecondChoiceLengthY;
        this.GotoAboutScreen = false;
        this.GotoPasswordScreen = false;
        this.AllClear = false;
        this.StageClear = false;
        this.CurrentStage = 1;
        this.TotalStages = 30;
        this.gameQuit = false;
        this.mt = new MediaTracker(this);
        for (int j = 0; j < this.NumOfImages; ++j) {
            this.PicLoad[j] = false;
        }
        this.titlepicture = this.getImage(this.getDocumentBase(), "baptitle.gif");
        this.mt.addImage(this.titlepicture, 0);
        this.picturebrick0 = this.getImage(this.getDocumentBase(), "brick0.gif");
        this.mt.addImage(this.picturebrick0, 1);
        this.picturebrick1 = this.getImage(this.getDocumentBase(), "brick1.gif");
        this.mt.addImage(this.picturebrick1, 2);
        this.picturebrick2 = this.getImage(this.getDocumentBase(), "brick2.gif");
        this.mt.addImage(this.picturebrick2, 3);
        this.picturebrick3 = this.getImage(this.getDocumentBase(), "brick3.gif");
        this.mt.addImage(this.picturebrick3, 4);
        this.picturebrick4 = this.getImage(this.getDocumentBase(), "brick4.gif");
        this.mt.addImage(this.picturebrick4, 5);
        this.trophypicture = this.getImage(this.getDocumentBase(), "trpy.gif");
        this.mt.addImage(this.trophypicture, 6);
        this.picturebrick5 = this.getImage(this.getDocumentBase(), "brick5.gif");
        this.mt.addImage(this.picturebrick5, 7);
        this.picturebrick6 = this.getImage(this.getDocumentBase(), "brick6.gif");
        this.mt.addImage(this.picturebrick6, 8);
        this.picturebrickext = this.getImage(this.getDocumentBase(), "brickext.gif");
        this.mt.addImage(this.picturebrickext, 9);
        this.picturebrickone = this.getImage(this.getDocumentBase(), "brickone.gif");
        this.mt.addImage(this.picturebrickone, 10);
        this.picturepadside = this.getImage(this.getDocumentBase(), "padside.gif");
        this.mt.addImage(this.picturepadside, 11);
        this.passwordpicture = this.getImage(this.getDocumentBase(), "password.gif");
        this.mt.addImage(this.passwordpicture, 12);
        this.playnowpicture = this.getImage(this.getDocumentBase(), "playnow.gif");
        this.mt.addImage(this.playnowpicture, 13);
        this.passwordpanelpicture = this.getImage(this.getDocumentBase(), "ppanel.gif");
        this.mt.addImage(this.passwordpanelpicture, 14);
        this.numball = 2;
        this.minx = 5;
        this.miny = 5;
        this.ballsize = 10;
        this.ballsizehalf = this.ballsize / 2;
        this.ballmovement = this.ballsizehalf;
        this.ballmovement = 5;
        this.winsizex = 410;
        this.winsizey = 330;
        this.innerxsize = this.winsizex - this.minx * 2;
        this.innerysize = this.winsizey - 15 - this.miny - this.ballsize;
        this.origpadxsize = this.ballsize * 4;
        this.padxsize = this.origpadxsize;
        this.padysize = this.ballsize;
        this.winx = this.winsizex - this.minx;
        this.winy = this.winsizey - 15;
        this.maxx = this.winsizex - this.minx;
        this.maxy = this.winy;
        this.centerx = this.winx / 2;
        this.centery = this.winy / 2;
        this.rnd = new Random();
        int n2 = 12;
        this.f = new Font("Dialog", 0, n2);
        (this.myG = this.getGraphics()).setFont(this.f);
        this.heightIncrement = this.myG.getFontMetrics().getDescent() + this.myG.getFontMetrics().getAscent() - 1;
        this.CopyrightString[1] = "BAP! v1.50b";
        this.CopyrightString[2] = "Copyright (c)1999 Masayoshi Ueda a.k.a. Cheez.  All rights reserved.";
        this.CopyrightString[3] = " ";
        this.CopyrightString[4] = "Thank you for playing BAP! v1.50b.  Feel free to put this applet on";
        this.CopyrightString[5] = "your Web page (sending email to me and letting me know would be";
        this.CopyrightString[6] = "nice).  Also if you like this game, write and let me know!";
        this.CopyrightString[7] = "My email address 1(English or Japanese): jsurfer@earthlink.net";
        this.CopyrightString[8] = "My email address 2(English only): cheezsj@yahoo.com";
        this.CopyrightString[9] = " ";
        this.CopyrightString[10] = "Special thanks to: YujiH, BlakeW, YokoF, GeoffT, MasakoK";
        this.CopyrightString[11] = "TonyC, KenjiI, AntoneR, MasaoA, KeithP, TamieR, & BrianM";
        this.CopyrightString[12] = " ";
        this.CopyrightString[13] = " ";
        this.CopyrightString[14] = "And here is the usual boring stuff....";
        this.CopyrightString[15] = " ";
        this.CopyrightString[16] = "BAP! v1.50b is provided as is.  There are no warranties, no support";
        this.CopyrightString[17] = "arrangements, no claim of merchantability or fitness for specific";
        this.CopyrightString[18] = "purpose and no acceptance of liability for loss or damage caused by";
        this.CopyrightString[19] = "use of this program.  Any distribution of BAP! v1.50b must include all";
        this.CopyrightString[20] = "of the files in their original condition, without removal, addition or";
        this.CopyrightString[21] = "modification.  BAP! v1.50b may not be sold, resold, included as part";
        this.CopyrightString[22] = "of a commercial package or used for any other commercial purpose";
        this.CopyrightString[23] = "without the prior written consent of the author.";
        this.OKButton = "[ OK ]";
        boolean b = false;
        while (!b) {
            if (this.winsizex > this.myG.getFontMetrics().stringWidth(this.CopyrightString[20])) {
                b = true;
            }
            else {
                --n2;
                this.f = new Font("Dialog", 0, n2);
                this.myG.setFont(this.f);
                this.heightIncrement = this.myG.getFontMetrics().getDescent() + this.myG.getFontMetrics().getAscent() - 1;
            }
        }
        this.LenofOKButton = this.myG.getFontMetrics().stringWidth(this.OKButton) / 2;
        this.YofOKButton = this.winy - this.myG.getFontMetrics().getDescent() + this.myG.getFontMetrics().getAscent();
        int n3 = 5;
        for (int k = 1; k <= this.CopyrightRow; ++k) {
            this.LenofCopyrightdiv2[k] = this.myG.getFontMetrics().stringWidth(this.CopyrightString[k]) / 2;
            if (this.CopyrightString[k] == " ") {
                n3 += 4;
            }
            else {
                n3 += this.heightIncrement;
            }
        }
        int heightIncrement;
        if (n3 < this.winy * 0.7) {
            this.heightIncrement = this.myG.getFontMetrics().getDescent() + this.myG.getFontMetrics().getAscent();
            heightIncrement = this.heightIncrement;
        }
        else {
            heightIncrement = 4;
        }
        int n4 = 5;
        for (int l = 1; l <= this.CopyrightRow; ++l) {
            this.LenofCopyrightdiv2[l] = this.myG.getFontMetrics().stringWidth(this.CopyrightString[l]) / 2;
            if (this.CopyrightString[l] == " ") {
                n4 += heightIncrement;
            }
            else {
                n4 += this.heightIncrement;
            }
            this.YofCopyright[l] = n4;
        }
        this.Speedline = "Speed=      Use scroll bar below to change speed(0=Fast, 100=Slow)";
        this.LenofSpeedline = this.myG.getFontMetrics().stringWidth(this.Speedline) / 2;
        this.YofSpeedline = this.winy + 15;
        this.SpeedEqual = "Speed=";
        this.OKButton = "[ OK ]";
        this.LenofOKButton = this.myG.getFontMetrics().stringWidth(this.OKButton) / 2;
        this.YofOKButton = this.winy - this.heightIncrement;
        this.Click = "* Click mouse button to continue *";
        this.LenofClick = this.myG.getFontMetrics().stringWidth(this.Click) / 2;
        this.YofClick = this.centery + 100;
        this.AboutButton = "[About BAP!]";
        this.LenofAboutButton = this.myG.getFontMetrics().stringWidth(this.AboutButton) / 2;
        this.YofAboutButton = 150;
        this.ClickChoice = "Click on your choice";
        this.LenofClickChoice = this.myG.getFontMetrics().stringWidth(this.ClickChoice) / 2;
        this.YofClickChoice = 275;
        this.TitleCopyright = "Copyright (c)1999 Masayoshi Ueda a.k.a. Cheez.  All rights reserved.";
        this.LenofTitleCopyright = this.myG.getFontMetrics().stringWidth(this.TitleCopyright) / 2;
        this.YofTitleCopyright = 300;
        this.MyEmail = "email: jsurfer@earthlink.net or cheezsj@yahoo.com";
        this.LenofMyEmail = this.myG.getFontMetrics().stringWidth(this.MyEmail) / 2;
        this.YofMyEmail = this.YofTitleCopyright + this.heightIncrement - 1;
        this.PasswordFor = "Password for stage 99 is:";
        this.LenofPasswordFor = this.myG.getFontMetrics().stringWidth(this.PasswordFor) / 2;
        this.YofPasswordFor = this.centery - (this.YofClick - this.centery);
        this.ActualPassword = "MMMMM";
        this.LenofActualPassword = this.myG.getFontMetrics().stringWidth(this.ActualPassword) / 2;
        this.YofActualPassword = this.centery;
        this.delayvalue = 30;
        this.delaydiv2 = this.delayvalue / 2;
        this.delaydiv3 = this.delayvalue / 3;
        this.delaydiv4 = this.delayvalue / 4;
        this.delaydiv5 = this.delayvalue / 5;
        this.delaydiv10 = this.delayvalue / 10;
        this.leftframexbegin = 0;
        this.leftframeybegin = 0;
        this.leftframexend = this.leftframexbegin + this.minx;
        this.leftframeyend = this.leftframeybegin + this.winy;
        this.upframexbegin = this.leftframexend;
        this.upframeybegin = this.leftframeybegin;
        this.upframexend = this.maxx;
        this.upframeyend = this.miny;
        this.rightframexbegin = this.upframexend;
        this.rightframeybegin = this.upframeybegin;
        this.rightframexend = this.winsizex;
        this.rightframeyend = this.winy;
        this.xmove = 5;
        this.ymove = this.ballmovement * -1;
        this.padx = this.winx / 2;
        this.pady = this.innerysize;
        this.gameover = false;
        this.padMoved = false;
        this.previouspadx = this.padx;
        this.previouspady = this.pady;
        this.waiting = true;
        this.stagex = 2;
        this.stagey = this.winsizey - 2;
        this.ballremainx = this.winsizex - 52;
        this.ballremainy = this.winsizey - 2;
        this.setBackground(Color.black);
        this.numbx = 10;
        this.numby = 25;
        this.lenofblock = this.innerxsize / this.numbx;
        this.heightofblock = this.padysize;
        this.congratx = new int[17];
        this.congraty = new int[17];
        this.congratxl = new int[17];
        this.congratyl = new int[17];
        this.blocks = new boolean[this.numby + 1][this.numbx + 1];
        this.blockx = new int[this.numby + 1][this.numbx + 1];
        this.blocky = new int[this.numby + 1][this.numbx + 1];
        this.blockcolor = new int[this.numby + 1][this.numbx + 1];
        this.firstblockpositiony = this.heightofblock * this.numby - this.miny;
        this.firstblockpositionx = this.minx;
        this.StagePattern = new String[this.numby + 1];
        this.currentx = 45;
        this.currenty = this.firstblockpositiony + this.heightofblock + this.ballsize * 3 + this.ballsizehalf;
        this.previousx = this.currentx;
        this.previousy = this.currenty;
        this.incornerx1 = this.minx;
        this.incornery1 = this.miny;
        this.incornerx2 = this.upframexend;
        this.incornery2 = this.upframeyend;
        this.incornerx3 = this.upframexend;
        this.incornery3 = this.rightframeyend;
        this.incornerx4 = this.leftframexend;
        this.incornery4 = this.leftframeyend;
        this.rnd = new Random();
        (this.color = new Color[10])[0] = new Color(0, 0, 255);
        this.color[1] = new Color(255, 0, 0);
        this.color[2] = new Color(0, 255, 0);
        this.color[3] = new Color(255, 0, 255);
        this.color[4] = new Color(100, 100, 0);
        this.color[5] = new Color(192, 192, 192);
        this.color[6] = new Color(255, 0, 0);
        this.color[7] = new Color(255, 175, 175);
        this.color[8] = new Color(255, 200, 0);
        this.color[9] = new Color(255, 255, 0);
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
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.scr) {
            this.repaint();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void FillStagePattern() {
        if (this.CurrentStage == 1) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[25] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z..........";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z----------";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z..........";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z..........";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z..........";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 2) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z-.........";
            this.StagePattern[this.numby - 4] = "z--........";
            this.StagePattern[this.numby - 5] = "z---.......";
            this.StagePattern[this.numby - 6] = "z----......";
            this.StagePattern[this.numby - 7] = "z-----.....";
            this.StagePattern[this.numby - 8] = "z------....";
            this.StagePattern[this.numby - 9] = "z-------...";
            this.StagePattern[this.numby - 10] = "z--------..";
            this.StagePattern[this.numby - 11] = "z---------.";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z..........";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z..........";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 3) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z.--------.";
            this.StagePattern[this.numby - 4] = "z..-.--.-..";
            this.StagePattern[this.numby - 5] = "z..-.--.-..";
            this.StagePattern[this.numby - 6] = "z..-.--.-..";
            this.StagePattern[this.numby - 7] = "z..-.--.-..";
            this.StagePattern[this.numby - 8] = "z..-.--.-..";
            this.StagePattern[this.numby - 9] = "z..-.--.-..";
            this.StagePattern[this.numby - 10] = "z..-.--.-..";
            this.StagePattern[this.numby - 11] = "z..-.--.-..";
            this.StagePattern[this.numby - 12] = "z..-.--.-..";
            this.StagePattern[this.numby - 13] = "z..-.--.-..";
            this.StagePattern[this.numby - 14] = "z.--------.";
            this.StagePattern[this.numby - 15] = "z..........";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 4) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z....--....";
            this.StagePattern[this.numby - 4] = "z...----...";
            this.StagePattern[this.numby - 5] = "z..------..";
            this.StagePattern[this.numby - 6] = "z...----...";
            this.StagePattern[this.numby - 7] = "z....--....";
            this.StagePattern[this.numby - 8] = "z...----...";
            this.StagePattern[this.numby - 9] = "z..------..";
            this.StagePattern[this.numby - 10] = "z...----...";
            this.StagePattern[this.numby - 11] = "z..------..";
            this.StagePattern[this.numby - 12] = "z...----...";
            this.StagePattern[this.numby - 13] = "z..------..";
            this.StagePattern[this.numby - 14] = "z.--------.";
            this.StagePattern[this.numby - 15] = "z..........";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 5) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 1;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z..........";
            this.StagePattern[this.numby - 6] = "z..........";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z..........";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 6) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 4] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 5] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 6] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 7] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 8] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 9] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 10] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 11] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 12] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 13] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 14] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 15] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 16] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 7) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z.---..---.";
            this.StagePattern[this.numby - 3] = "z..-....-..";
            this.StagePattern[this.numby - 4] = "z..-....-..";
            this.StagePattern[this.numby - 5] = "z.---..---.";
            this.StagePattern[this.numby - 6] = "z..-....-..";
            this.StagePattern[this.numby - 7] = "z..-....-..";
            this.StagePattern[this.numby - 8] = "z.---..---.";
            this.StagePattern[this.numby - 9] = "z..-....-..";
            this.StagePattern[this.numby - 10] = "z..-....-..";
            this.StagePattern[this.numby - 11] = "z.---..---.";
            this.StagePattern[this.numby - 12] = "z..-....-..";
            this.StagePattern[this.numby - 13] = "z..-....-..";
            this.StagePattern[this.numby - 14] = "z.---..---.";
            this.StagePattern[this.numby - 15] = "z..-....-..";
            this.StagePattern[this.numby - 16] = "z..-....-..";
            this.StagePattern[this.numby - 17] = "z.---..---.";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z....--....";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 8) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z.---------";
            this.StagePattern[this.numby - 5] = "z..--------";
            this.StagePattern[this.numby - 6] = "z...-------";
            this.StagePattern[this.numby - 7] = "z....------";
            this.StagePattern[this.numby - 8] = "z.....-----";
            this.StagePattern[this.numby - 9] = "z......----";
            this.StagePattern[this.numby - 10] = "z.......---";
            this.StagePattern[this.numby - 11] = "z........--";
            this.StagePattern[this.numby - 12] = "z.........-";
            this.StagePattern[this.numby - 13] = "z----------";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z..........";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z.-......-.";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 9) {
            this.thisstagepadxsize = this.origpadxsize + this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 3] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 4] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 5] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 6] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 7] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 8] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 9] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 10] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 11] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 12] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 13] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 14] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 15] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 16] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 10) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 1;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z..........";
            this.StagePattern[this.numby - 4] = "z..........";
            this.StagePattern[this.numby - 5] = "z....--....";
            this.StagePattern[this.numby - 6] = "z...----...";
            this.StagePattern[this.numby - 7] = "z..------..";
            this.StagePattern[this.numby - 8] = "z.--------.";
            this.StagePattern[this.numby - 9] = "z----------";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z---....---";
            this.StagePattern[this.numby - 12] = "z--......--";
            this.StagePattern[this.numby - 13] = "z-........-";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z..-....-..";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 18] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 19] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 11) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z-.--..--.-";
            this.StagePattern[this.numby - 4] = "z-.--..--.-";
            this.StagePattern[this.numby - 5] = "z----------";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z..........";
            this.StagePattern[this.numby - 8] = "z..........";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z.-..--..-.";
            this.StagePattern[this.numby - 13] = "z.-..--..-.";
            this.StagePattern[this.numby - 14] = "z.-..--..-.";
            this.StagePattern[this.numby - 15] = "z.-..--..-.";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z..........";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 12) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z..........";
            this.StagePattern[this.numby - 4] = "z..........";
            this.StagePattern[this.numby - 5] = "z..........";
            this.StagePattern[this.numby - 6] = "z---....---";
            this.StagePattern[this.numby - 7] = "z---....---";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z....--....";
            this.StagePattern[this.numby - 10] = "z....--....";
            this.StagePattern[this.numby - 11] = "z..........";
            this.StagePattern[this.numby - 12] = "z..........";
            this.StagePattern[this.numby - 13] = "z..........";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 13) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z-.-....-.-";
            this.StagePattern[this.numby - 1] = "z-.-....-.-";
            this.StagePattern[this.numby - 2] = "z-.-....-.-";
            this.StagePattern[this.numby - 3] = "z-.-....-.-";
            this.StagePattern[this.numby - 4] = "z.--------.";
            this.StagePattern[this.numby - 5] = "z.--------.";
            this.StagePattern[this.numby - 6] = "z..........";
            this.StagePattern[this.numby - 7] = "z..........";
            this.StagePattern[this.numby - 8] = "z..........";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z.--------.";
            this.StagePattern[this.numby - 11] = "z.--------.";
            this.StagePattern[this.numby - 12] = "z-.-....-.-";
            this.StagePattern[this.numby - 13] = "z-.-....-.-";
            this.StagePattern[this.numby - 14] = "z-.-....-.-";
            this.StagePattern[this.numby - 15] = "z-.-....-.-";
            this.StagePattern[this.numby - 16] = "z.--------.";
            this.StagePattern[this.numby - 17] = "z.--------.";
            this.StagePattern[this.numby - 18] = "z.--------.";
            this.StagePattern[this.numby - 19] = "z.--------.";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 14) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z----------";
            this.StagePattern[this.numby - 1] = "z----------";
            this.StagePattern[this.numby - 2] = "z........--";
            this.StagePattern[this.numby - 3] = "z........--";
            this.StagePattern[this.numby - 4] = "z........--";
            this.StagePattern[this.numby - 5] = "z......--..";
            this.StagePattern[this.numby - 6] = "z......--..";
            this.StagePattern[this.numby - 7] = "z......--..";
            this.StagePattern[this.numby - 8] = "z....--....";
            this.StagePattern[this.numby - 9] = "z....--....";
            this.StagePattern[this.numby - 10] = "z....--....";
            this.StagePattern[this.numby - 11] = "z..--......";
            this.StagePattern[this.numby - 12] = "z..--......";
            this.StagePattern[this.numby - 13] = "z..--......";
            this.StagePattern[this.numby - 14] = "z--........";
            this.StagePattern[this.numby - 15] = "z--........";
            this.StagePattern[this.numby - 16] = "z--........";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 15) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z..........";
            this.StagePattern[this.numby - 6] = "z..........";
            this.StagePattern[this.numby - 7] = "z..........";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z----------";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z..........";
            this.StagePattern[this.numby - 12] = "z..........";
            this.StagePattern[this.numby - 13] = "z----------";
            this.StagePattern[this.numby - 14] = "z--..--..--";
            this.StagePattern[this.numby - 15] = "z--..--..--";
            this.StagePattern[this.numby - 16] = "z--..--..--";
            this.StagePattern[this.numby - 17] = "z--..--..--";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z--..--..--";
            this.StagePattern[this.numby - 20] = "z--..--..--";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 16) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z---.......";
            this.StagePattern[this.numby - 1] = "z----......";
            this.StagePattern[this.numby - 2] = "z-----.....";
            this.StagePattern[this.numby - 3] = "z------....";
            this.StagePattern[this.numby - 4] = "z-------...";
            this.StagePattern[this.numby - 5] = "z--------..";
            this.StagePattern[this.numby - 6] = "z---------.";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z.---------";
            this.StagePattern[this.numby - 10] = "z..--------";
            this.StagePattern[this.numby - 11] = "z...-------";
            this.StagePattern[this.numby - 12] = "z....------";
            this.StagePattern[this.numby - 13] = "z.....-----";
            this.StagePattern[this.numby - 14] = "z-----.....";
            this.StagePattern[this.numby - 15] = "z------....";
            this.StagePattern[this.numby - 16] = "z-------...";
            this.StagePattern[this.numby - 17] = "z--------..";
            this.StagePattern[this.numby - 18] = "z---------.";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 17) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z----------";
            this.StagePattern[this.numby - 6] = "z----..----";
            this.StagePattern[this.numby - 7] = "z----..----";
            this.StagePattern[this.numby - 8] = "z----..----";
            this.StagePattern[this.numby - 9] = "z---....---";
            this.StagePattern[this.numby - 10] = "z---....---";
            this.StagePattern[this.numby - 11] = "z---....---";
            this.StagePattern[this.numby - 12] = "z--......--";
            this.StagePattern[this.numby - 13] = "z--......--";
            this.StagePattern[this.numby - 14] = "z--......--";
            this.StagePattern[this.numby - 15] = "z---....---";
            this.StagePattern[this.numby - 16] = "z---....---";
            this.StagePattern[this.numby - 17] = "z---....---";
            this.StagePattern[this.numby - 18] = "z----..----";
            this.StagePattern[this.numby - 19] = "z----..----";
            this.StagePattern[this.numby - 20] = "z----..----";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 18) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 1;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..-....-..";
            this.StagePattern[this.numby - 2] = "z..-....-..";
            this.StagePattern[this.numby - 3] = "z..-....-..";
            this.StagePattern[this.numby - 4] = "z..-....-..";
            this.StagePattern[this.numby - 5] = "z--.----.--";
            this.StagePattern[this.numby - 6] = "z..........";
            this.StagePattern[this.numby - 7] = "z..........";
            this.StagePattern[this.numby - 8] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 9] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 10] = "z.-.-.-.-.-";
            this.StagePattern[this.numby - 11] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 12] = "z..........";
            this.StagePattern[this.numby - 13] = "z..........";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z...-..-...";
            this.StagePattern[this.numby - 18] = "z...-..-...";
            this.StagePattern[this.numby - 19] = "z---.--.---";
            this.StagePattern[this.numby - 20] = "z----------";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 19) {
            this.thisstagepadxsize = this.origpadxsize;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z...----...";
            this.StagePattern[this.numby - 4] = "z...----...";
            this.StagePattern[this.numby - 5] = "z.--....--.";
            this.StagePattern[this.numby - 6] = "z.--....--.";
            this.StagePattern[this.numby - 7] = "z.--....--.";
            this.StagePattern[this.numby - 8] = "z....--....";
            this.StagePattern[this.numby - 9] = "z....--....";
            this.StagePattern[this.numby - 10] = "z.--....--.";
            this.StagePattern[this.numby - 11] = "z.--....--.";
            this.StagePattern[this.numby - 12] = "z.--....--.";
            this.StagePattern[this.numby - 13] = "z....--....";
            this.StagePattern[this.numby - 14] = "z....--....";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z-.-....-.-";
            this.StagePattern[this.numby - 17] = "z-.-....-.-";
            this.StagePattern[this.numby - 18] = "z-.-....-.-";
            this.StagePattern[this.numby - 19] = "z-.-....-.-";
            this.StagePattern[this.numby - 20] = "z.-.----.-.";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 20) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z...----...";
            this.StagePattern[this.numby - 4] = "z...----...";
            this.StagePattern[this.numby - 5] = "z...----...";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z-.-....-.-";
            this.StagePattern[this.numby - 9] = "z-.-....-.-";
            this.StagePattern[this.numby - 10] = "z-.-....-.-";
            this.StagePattern[this.numby - 11] = "z-.-....-.-";
            this.StagePattern[this.numby - 12] = "z.-......-.";
            this.StagePattern[this.numby - 13] = "z.-......-.";
            this.StagePattern[this.numby - 14] = "z.-......-.";
            this.StagePattern[this.numby - 15] = "z.-......-.";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z-........-";
            this.StagePattern[this.numby - 20] = "z-........-";
            this.StagePattern[this.numby - 21] = "z--......--";
            this.StagePattern[this.numby - 22] = "z--......--";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 21) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z..........";
            this.StagePattern[this.numby - 5] = "z..........";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z..........";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z..........";
            this.StagePattern[this.numby - 13] = "z..........";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z..........";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z....--....";
            this.StagePattern[this.numby - 19] = "z----..----";
            this.StagePattern[this.numby - 20] = "z----..----";
            this.StagePattern[this.numby - 21] = "z---....---";
            this.StagePattern[this.numby - 22] = "z---....---";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 22) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z--.-..-.--";
            this.StagePattern[this.numby - 4] = "z--.-..-.--";
            this.StagePattern[this.numby - 5] = "z----------";
            this.StagePattern[this.numby - 6] = "z--.-..-.--";
            this.StagePattern[this.numby - 7] = "z--.-..-.--";
            this.StagePattern[this.numby - 8] = "z..........";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z--.-..-.--";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z--.-..-.--";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z..-....-..";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z---....---";
            this.StagePattern[this.numby - 18] = "z....--....";
            this.StagePattern[this.numby - 19] = "z....--....";
            this.StagePattern[this.numby - 20] = "z...----...";
            this.StagePattern[this.numby - 21] = "z...----...";
            this.StagePattern[this.numby - 22] = "z...----...";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 23) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z..........";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z..........";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z..........";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z----------";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z..-....-..";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z----------";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 24) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 1;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z----..----";
            this.StagePattern[this.numby - 4] = "z----..----";
            this.StagePattern[this.numby - 5] = "z----..----";
            this.StagePattern[this.numby - 6] = "z----..----";
            this.StagePattern[this.numby - 7] = "z----..----";
            this.StagePattern[this.numby - 8] = "z----..----";
            this.StagePattern[this.numby - 9] = "z----..----";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z..........";
            this.StagePattern[this.numby - 12] = "z..........";
            this.StagePattern[this.numby - 13] = "z----..----";
            this.StagePattern[this.numby - 14] = "z----..----";
            this.StagePattern[this.numby - 15] = "z----..----";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z----..----";
            this.StagePattern[this.numby - 19] = "z----..----";
            this.StagePattern[this.numby - 20] = "z----..----";
            this.StagePattern[this.numby - 21] = "z--......--";
            this.StagePattern[this.numby - 22] = "z--......--";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 25) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 2;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z....--....";
            this.StagePattern[this.numby - 4] = "z...----...";
            this.StagePattern[this.numby - 5] = "z..------..";
            this.StagePattern[this.numby - 6] = "z.--------.";
            this.StagePattern[this.numby - 7] = "z..------..";
            this.StagePattern[this.numby - 8] = "z...----...";
            this.StagePattern[this.numby - 9] = "z....--....";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z..------..";
            this.StagePattern[this.numby - 13] = "z..------..";
            this.StagePattern[this.numby - 14] = "z..------..";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z..........";
            this.StagePattern[this.numby - 17] = "z.-.-..-.-.";
            this.StagePattern[this.numby - 18] = "z..........";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z--......--";
            this.StagePattern[this.numby - 21] = "z--......--";
            this.StagePattern[this.numby - 22] = "z--......--";
            this.StagePattern[this.numby - 23] = "z----------";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 26) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 1;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z----------";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..--..--..";
            this.StagePattern[this.numby - 3] = "z..........";
            this.StagePattern[this.numby - 4] = "z-...--...-";
            this.StagePattern[this.numby - 5] = "z.--....--.";
            this.StagePattern[this.numby - 6] = "z..--..--..";
            this.StagePattern[this.numby - 7] = "z.--....--.";
            this.StagePattern[this.numby - 8] = "z...----...";
            this.StagePattern[this.numby - 9] = "z.--....--.";
            this.StagePattern[this.numby - 10] = "z..........";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z..-....-..";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z..-....-..";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z.-..--..-.";
            this.StagePattern[this.numby - 18] = "z.-..--..-.";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z----------";
            this.StagePattern[this.numby - 21] = "z.-..--..-.";
            this.StagePattern[this.numby - 22] = "z.-..--..-.";
            this.StagePattern[this.numby - 23] = "z.-..--..-.";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 27) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z----------";
            this.StagePattern[this.numby - 1] = "z----------";
            this.StagePattern[this.numby - 2] = "z--......--";
            this.StagePattern[this.numby - 3] = "z--......--";
            this.StagePattern[this.numby - 4] = "z--......--";
            this.StagePattern[this.numby - 5] = "z..------..";
            this.StagePattern[this.numby - 6] = "z..------..";
            this.StagePattern[this.numby - 7] = "z..------..";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z----------";
            this.StagePattern[this.numby - 10] = "z...----...";
            this.StagePattern[this.numby - 11] = "z...----...";
            this.StagePattern[this.numby - 12] = "z---....---";
            this.StagePattern[this.numby - 13] = "z---....---";
            this.StagePattern[this.numby - 14] = "z---....---";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z-.-.--.-.-";
            this.StagePattern[this.numby - 19] = "z-.-.--.-.-";
            this.StagePattern[this.numby - 20] = "z-.-.--.-.-";
            this.StagePattern[this.numby - 21] = "z-.-.--.-.-";
            this.StagePattern[this.numby - 22] = "z-.-.--.-.-";
            this.StagePattern[this.numby - 23] = "z-.-.--.-.-";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 28) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z----------";
            this.StagePattern[this.numby - 1] = "z.-..--..-.";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z.-..--..-.";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z.-..--..-.";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z.-..--..-.";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z.-..--..-.";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z.-..--..-.";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z.-..--..-.";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z.-..--..-.";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z.-..--..-.";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z.-..--..-.";
            this.StagePattern[this.numby - 20] = "z----------";
            this.StagePattern[this.numby - 21] = "z.-..--..-.";
            this.StagePattern[this.numby - 22] = "z----------";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 29) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z----------";
            this.StagePattern[this.numby - 1] = "z----------";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z..........";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z..........";
            this.StagePattern[this.numby - 9] = "z----------";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z..........";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z----------";
            this.StagePattern[this.numby - 14] = "z..........";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z..........";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z..........";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z----------";
            this.StagePattern[this.numby - 23] = "z----------";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 30) {
            this.thisstagepadxsize = this.origpadxsize / 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z----------";
            this.StagePattern[this.numby - 1] = "z----------";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z----------";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z----------";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z----------";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z----------";
            this.StagePattern[this.numby - 21] = "z----------";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z-.-.-.-.-.";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        else if (this.CurrentStage == 99) {
            this.thisstagepadxsize = this.origpadxsize * 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 7;
            this.numextendbricks = 7;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z..........";
            this.StagePattern[this.numby - 3] = "z..........";
            this.StagePattern[this.numby - 4] = "z..........";
            this.StagePattern[this.numby - 5] = "z..-.--.-..";
            this.StagePattern[this.numby - 6] = "z..-.--.-..";
            this.StagePattern[this.numby - 7] = "z..-.--.-..";
            this.StagePattern[this.numby - 8] = "z..-.--.-..";
            this.StagePattern[this.numby - 9] = "z..-.--.-..";
            this.StagePattern[this.numby - 10] = "z..-.--.-..";
            this.StagePattern[this.numby - 11] = "z..-.--.-..";
            this.StagePattern[this.numby - 12] = "z..-.--.-..";
            this.StagePattern[this.numby - 13] = "z..-.--.-..";
            this.StagePattern[this.numby - 14] = "z..-.--.-..";
            this.StagePattern[this.numby - 15] = "z..-.--.-..";
            this.StagePattern[this.numby - 16] = "z..-.--.-..";
            this.StagePattern[this.numby - 17] = "z..-.--.-..";
            this.StagePattern[this.numby - 18] = "z..-.--.-..";
            this.StagePattern[this.numby - 19] = "z..-.--.-..";
            this.StagePattern[this.numby - 20] = "z..-.--.-..";
            this.StagePattern[this.numby - 21] = "z..........";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        if (this.CurrentStage == 89) {
            this.thisstagepadxsize = this.origpadxsize * 2;
            this.padxsize = this.thisstagepadxsize;
            this.numextrabricks = 0;
            this.numextendbricks = 0;
            this.StagePattern[this.numby] = "z..........";
            this.StagePattern[this.numby - 1] = "z..........";
            this.StagePattern[this.numby - 2] = "z----------";
            this.StagePattern[this.numby - 3] = "z----------";
            this.StagePattern[this.numby - 4] = "z----------";
            this.StagePattern[this.numby - 5] = "z----------";
            this.StagePattern[this.numby - 6] = "z----------";
            this.StagePattern[this.numby - 7] = "z----------";
            this.StagePattern[this.numby - 8] = "z----------";
            this.StagePattern[this.numby - 9] = "z----------";
            this.StagePattern[this.numby - 10] = "z----------";
            this.StagePattern[this.numby - 11] = "z----------";
            this.StagePattern[this.numby - 12] = "z----------";
            this.StagePattern[this.numby - 13] = "z----------";
            this.StagePattern[this.numby - 14] = "z----------";
            this.StagePattern[this.numby - 15] = "z----------";
            this.StagePattern[this.numby - 16] = "z----------";
            this.StagePattern[this.numby - 17] = "z----------";
            this.StagePattern[this.numby - 18] = "z----------";
            this.StagePattern[this.numby - 19] = "z----------";
            this.StagePattern[this.numby - 20] = "z----------";
            this.StagePattern[this.numby - 21] = "z----------";
            this.StagePattern[this.numby - 22] = "z..........";
            this.StagePattern[this.numby - 23] = "z..........";
            this.StagePattern[this.numby - 24] = "z..........";
        }
        this.thisstagepadxsizediv2 = this.padxsize / 2;
        this.tempy = this.firstblockpositiony;
        this.tempint2 = 0;
        for (int i = 1; i <= this.numby; ++i) {
            this.tempx = this.minx;
            for (int j = 1; j <= this.numbx; ++j) {
                final char char1 = this.StagePattern[i].charAt(j);
                if (char1 == '-') {
                    this.blocks[i][j] = true;
                    this.blockx[i][j] = this.tempx;
                    this.blocky[i][j] = this.tempy;
                    this.tempx += this.lenofblock;
                    this.m1 = Math.random();
                    this.blockcolor[i][j] = (int)(this.m1 * 7.0);
                    ++this.tempint2;
                }
                else if (char1 == '.') {
                    this.blocks[i][j] = false;
                    this.blockx[i][j] = this.tempx;
                    this.blocky[i][j] = this.tempy;
                    this.tempx += this.lenofblock;
                    this.m1 = Math.random();
                    this.blockcolor[i][j] = (int)(this.m1 * 7.0);
                }
            }
            this.tempy -= this.heightofblock;
        }
        this.rnd = new Random();
        if (this.numextrabricks + this.numextendbricks > 0) {
            for (int k = 1; k <= this.numextrabricks + this.numextendbricks; ++k) {
                this.tempinteger[k] = 0;
            }
        }
        if (this.numextrabricks > 0) {
            for (int l = 1; l <= this.numextrabricks; ++l) {
                this.extrabrickx[l] = 0;
                this.extrabricky[l] = 0;
            }
        }
        if (this.numextendbricks > 0) {
            for (int n = 1; n <= this.numextendbricks; ++n) {
                this.extendbrickx[n] = 0;
                this.extendbricky[n] = 0;
            }
        }
        if (this.numextrabricks + this.numextendbricks > 0) {
            for (int n2 = 1; n2 <= this.numextrabricks + this.numextendbricks; ++n2) {
                this.m1 = Math.random();
                this.tempinteger[n2] = (int)(this.m1 * (this.tempint2 - 1) + 1.0);
                if (n2 > 1) {
                    this.stoprandomcreating = false;
                    while (!this.stoprandomcreating) {
                        this.stoprandomcreating = true;
                        this.m1 = Math.random();
                        this.tempinteger[n2] = (int)(this.m1 * (this.tempint2 - 1) + 1.0);
                        for (int n3 = 1; n3 < n2; ++n3) {
                            if (this.tempinteger[n2] == this.tempinteger[n3]) {
                                this.stoprandomcreating = false;
                            }
                        }
                    }
                }
            }
        }
        this.tempint = 0;
        this.currentnumofextra = this.numextrabricks;
        this.currentnumofextend = this.numextendbricks;
        int n4 = 1;
        for (int n5 = 1; n5 <= this.numby; ++n5) {
            for (int n6 = 1; n6 <= this.numbx; ++n6) {
                if (this.blocks[n5][n6]) {
                    n4 *= -1;
                    ++this.tempint;
                    if (this.numextrabricks + this.numextendbricks > 0) {
                        for (int n7 = 1; n7 <= this.numextrabricks + this.numextendbricks; ++n7) {
                            if (this.tempint == this.tempinteger[n7]) {
                                if (n4 > 0) {
                                    if (this.currentnumofextra > 0) {
                                        this.extrabrickx[this.currentnumofextra] = n5;
                                        this.extrabricky[this.currentnumofextra] = n6;
                                        --this.currentnumofextra;
                                    }
                                    else {
                                        this.extendbrickx[this.currentnumofextend] = n5;
                                        this.extendbricky[this.currentnumofextend] = n6;
                                        --this.currentnumofextend;
                                    }
                                }
                                else if (this.currentnumofextend > 0) {
                                    this.extendbrickx[this.currentnumofextend] = n5;
                                    this.extendbricky[this.currentnumofextend] = n6;
                                    --this.currentnumofextend;
                                }
                                else {
                                    this.extrabrickx[this.currentnumofextra] = n5;
                                    this.extrabricky[this.currentnumofextra] = n6;
                                    --this.currentnumofextra;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void drawExtrabrick(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(Color.yellow);
        graphics.fillRect(this.blockx[n][n2] + 2, this.blocky[n][n2] + 4, 6, 2);
        graphics.fillRect(this.blockx[n][n2] + 4, this.blocky[n][n2] + 2, 2, 6);
        graphics.drawLine(this.blockx[n][n2] + 12, this.blocky[n][n2] + 1, this.blockx[n][n2] + 16, this.blocky[n][n2] + 1);
        graphics.drawLine(this.blockx[n][n2] + 11, this.blocky[n][n2] + 2, this.blockx[n][n2] + 17, this.blocky[n][n2] + 2);
        graphics.drawLine(this.blockx[n][n2] + 10, this.blocky[n][n2] + 3, this.blockx[n][n2] + 18, this.blocky[n][n2] + 3);
        graphics.drawLine(this.blockx[n][n2] + 10, this.blocky[n][n2] + 4, this.blockx[n][n2] + 18, this.blocky[n][n2] + 4);
        graphics.drawLine(this.blockx[n][n2] + 10, this.blocky[n][n2] + 5, this.blockx[n][n2] + 18, this.blocky[n][n2] + 5);
        graphics.drawLine(this.blockx[n][n2] + 10, this.blocky[n][n2] + 6, this.blockx[n][n2] + 18, this.blocky[n][n2] + 6);
        graphics.drawLine(this.blockx[n][n2] + 11, this.blocky[n][n2] + 7, this.blockx[n][n2] + 17, this.blocky[n][n2] + 7);
        graphics.drawLine(this.blockx[n][n2] + 20, this.blocky[n][n2] + 2, this.blockx[n][n2] + 20, this.blocky[n][n2] + 7);
        graphics.drawLine(this.blockx[n][n2] + 21, this.blocky[n][n2] + 2, this.blockx[n][n2] + 27, this.blocky[n][n2] + 7);
        graphics.drawLine(this.blockx[n][n2] + 28, this.blocky[n][n2] + 2, this.blockx[n][n2] + 28, this.blocky[n][n2] + 7);
        graphics.drawLine(this.blockx[n][n2] + 30, this.blocky[n][n2] + 2, this.blockx[n][n2] + 30, this.blocky[n][n2] + 7);
        graphics.drawLine(this.blockx[n][n2] + 30, this.blocky[n][n2] + 2, this.blockx[n][n2] + 37, this.blocky[n][n2] + 2);
        graphics.drawLine(this.blockx[n][n2] + 30, this.blocky[n][n2] + 4, this.blockx[n][n2] + 36, this.blocky[n][n2] + 4);
        graphics.drawLine(this.blockx[n][n2] + 30, this.blocky[n][n2] + 7, this.blockx[n][n2] + 37, this.blocky[n][n2] + 7);
    }
    
    public void drawExtendbrick(final Graphics graphics, final int n, final int n2) {
        graphics.setColor(Color.yellow);
        graphics.drawLine(this.blockx[n][n2] + 5, this.blocky[n][n2] + 1, this.blockx[n][n2] + 6, this.blocky[n][n2] + 1);
        graphics.drawLine(this.blockx[n][n2] + 4, this.blocky[n][n2] + 2, this.blockx[n][n2] + 6, this.blocky[n][n2] + 2);
        graphics.drawLine(this.blockx[n][n2] + 33, this.blocky[n][n2] + 1, this.blockx[n][n2] + 34, this.blocky[n][n2] + 1);
        graphics.drawLine(this.blockx[n][n2] + 33, this.blocky[n][n2] + 2, this.blockx[n][n2] + 35, this.blocky[n][n2] + 2);
        graphics.drawLine(this.blockx[n][n2] + 3, this.blocky[n][n2] + 3, this.blockx[n][n2] + 36, this.blocky[n][n2] + 3);
        graphics.drawLine(this.blockx[n][n2] + 2, this.blocky[n][n2] + 4, this.blockx[n][n2] + 37, this.blocky[n][n2] + 4);
        graphics.drawLine(this.blockx[n][n2] + 3, this.blocky[n][n2] + 5, this.blockx[n][n2] + 36, this.blocky[n][n2] + 5);
        graphics.drawLine(this.blockx[n][n2] + 4, this.blocky[n][n2] + 6, this.blockx[n][n2] + 6, this.blocky[n][n2] + 6);
        graphics.drawLine(this.blockx[n][n2] + 5, this.blocky[n][n2] + 7, this.blockx[n][n2] + 6, this.blocky[n][n2] + 7);
        graphics.drawLine(this.blockx[n][n2] + 33, this.blocky[n][n2] + 6, this.blockx[n][n2] + 35, this.blocky[n][n2] + 6);
        graphics.drawLine(this.blockx[n][n2] + 33, this.blocky[n][n2] + 7, this.blockx[n][n2] + 34, this.blocky[n][n2] + 7);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(this.f);
        this.delayvalue = this.scr.getValue();
        if (this.mt.checkID(this.NumOfImages - 1)) {
            this.delaydiv2 = this.delayvalue / 2;
            this.delaydiv3 = this.delayvalue / 3;
            this.delaydiv4 = this.delayvalue / 4;
            this.delaydiv5 = this.delayvalue / 5;
            this.delaydiv10 = this.delayvalue / 10;
            if (this.inTitle) {
                graphics.drawImage(this.titlepicture, 25, 20, this);
                graphics.drawImage(this.playnowpicture, this.FirstChoiceBeginX, this.FirstChoiceBeginY, this);
                graphics.drawImage(this.passwordpicture, this.SecondChoiceBeginX, this.SecondChoiceBeginY, this);
                graphics.setColor(new Color(150, 150, 0));
                graphics.drawString("v1.50b", 361, 110);
                graphics.setColor(Color.gray);
                graphics.drawString(this.AboutButton, this.centerx - this.LenofAboutButton, this.YofAboutButton);
                graphics.drawString(this.TitleCopyright, this.centerx - this.LenofTitleCopyright, this.YofTitleCopyright);
                graphics.drawString(this.MyEmail, this.centerx - this.LenofMyEmail, this.YofMyEmail);
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
                graphics.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
                return;
            }
            if (this.inAboutScreen) {
                graphics.setColor(Color.gray);
                this.drawFish(this.centerx - 7, this.YofCopyright[12] - 4, 128, 128, 128);
                for (int i = 1; i <= this.CopyrightRow; ++i) {
                    graphics.drawString(this.CopyrightString[i], this.centerx - this.LenofCopyrightdiv2[i], this.YofCopyright[i]);
                }
                graphics.drawString(this.OKButton, this.centerx - this.LenofOKButton, this.YofOKButton);
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
                graphics.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
                return;
            }
            if (this.gameover) {
                graphics.setColor(Color.white);
                graphics.drawString(this.Click, this.centerx - this.LenofClick, this.YofClick);
                return;
            }
            if (this.inPasswordScreen) {
                graphics.drawImage(this.passwordpanelpicture, this.ppanelx, this.ppanely, this);
                graphics.setColor(Color.white);
                graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                graphics.setColor(Color.gray);
                graphics.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
                graphics.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
                graphics.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
                graphics.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
                graphics.setColor(new Color(255, 255, 255));
                graphics.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
                graphics.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
                graphics.setColor(Color.gray);
                graphics.drawString(this.OKButton, this.centerx - this.LenofOKButton, this.YofOKButton);
                if (this.CursorPWD == 1) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.black);
                    graphics.fillRect(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, 9, 10);
                    graphics.setColor(Color.white);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 + 9 - 4, this.ppanely + 170 + 1);
                    return;
                }
                if (this.CursorPWD == 2) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.black);
                    graphics.fillRect(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, 9, 10);
                    graphics.setColor(Color.white);
                    graphics.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(0) + "", this.ppanelx + 70 - 4, this.ppanely + 170 + 1);
                    return;
                }
                if (this.CursorPWD == 3) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.black);
                    graphics.fillRect(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, 9, 10);
                    graphics.setColor(Color.white);
                    graphics.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(0) + "", this.ppanelx + 70 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(1) + "", this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1);
                    return;
                }
                if (this.CursorPWD == 4) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.black);
                    graphics.fillRect(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, 9, 10);
                    graphics.setColor(Color.white);
                    graphics.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(0) + "", this.ppanelx + 70 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(1) + "", this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(2) + "", this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1);
                    return;
                }
                if (this.CursorPWD == 5) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.black);
                    graphics.fillRect(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, 9, 10);
                    graphics.setColor(Color.white);
                    graphics.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.setColor(Color.gray);
                    graphics.drawString(this.PasswordEnteredString.charAt(0) + "", this.ppanelx + 70 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(1) + "", this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(2) + "", this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(3) + "", this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1);
                    return;
                }
                if (this.CursorPWD == 6) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(0) + "", this.ppanelx + 70 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(1) + "", this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(2) + "", this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(3) + "", this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1);
                    graphics.drawString(this.PasswordEnteredString.charAt(4) + "", this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1);
                }
            }
            else {
                if (this.InDisplayPassword) {
                    graphics.setColor(Color.white);
                    graphics.drawString("Password for stage " + this.CurrentStage + " is:", this.centerx - this.LenofPasswordFor, this.YofPasswordFor);
                    if (this.CurrentStage == 3) {
                        graphics.drawString("HELLO", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 6) {
                        graphics.drawString("SNARE", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 9) {
                        graphics.drawString("BUGSS", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 12) {
                        graphics.drawString("SPACE", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 15) {
                        graphics.drawString("OCEAN", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 18) {
                        graphics.drawString("NIHON", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 21) {
                        graphics.drawString("VENUS", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 24) {
                        graphics.drawString("MOCHA", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 27) {
                        graphics.drawString("TREEZ", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    else if (this.CurrentStage == 30) {
                        graphics.drawString("ORPMF", this.centerx - this.LenofActualPassword, this.YofActualPassword);
                    }
                    graphics.setColor(Color.white);
                    graphics.drawString(this.Click, this.centerx - this.LenofClick, this.YofClick);
                    return;
                }
                if (this.InCongratScreen) {
                    if (this.CircleCompleted && this.CongMoveCompleted && !this.inFinalMessageScreen) {
                        graphics.setColor(Color.green);
                        graphics.drawString("C", this.congratx[1], this.congraty[1]);
                        graphics.drawString("O", this.congratx[2], this.congraty[2]);
                        graphics.drawString("N", this.congratx[3], this.congraty[3]);
                        graphics.drawString("G", this.congratx[4], this.congraty[4]);
                        graphics.drawString("R", this.congratx[5], this.congraty[5]);
                        graphics.drawString("A", this.congratx[6], this.congraty[6]);
                        graphics.drawString("T", this.congratx[7], this.congraty[7]);
                        graphics.drawString("U", this.congratx[8], this.congraty[8]);
                        graphics.drawString("L", this.congratx[9], this.congraty[9]);
                        graphics.drawString("A", this.congratx[10], this.congraty[10]);
                        graphics.drawString("T", this.congratx[11], this.congraty[11]);
                        graphics.drawString("I", this.congratx[12], this.congraty[12]);
                        graphics.drawString("O", this.congratx[13], this.congraty[13]);
                        graphics.drawString("N", this.congratx[14], this.congraty[14]);
                        graphics.drawString("S", this.congratx[15], this.congraty[15]);
                        graphics.drawString("!", this.congratx[16], this.congraty[16]);
                        graphics.drawImage(this.trophypicture, this.centerx - 125, this.centery - 125, this);
                        return;
                    }
                    if (this.inFinalMessageScreen) {
                        graphics.setColor(Color.gray);
                        graphics.drawString("thank you for playing", 10, 13);
                        graphics.drawString("[yet another game of Ball And Paddle]", 10, 26);
                        graphics.drawString(" ", 10, 39);
                        graphics.drawString("special password:", 10, 52);
                        graphics.drawString("AHEWQ", 10, 65);
                        graphics.drawString(" ", 10, 78);
                        graphics.drawString("program written by masayoshi ueda -cheez-", 10, 91);
                        graphics.drawString("program started on march 18, 1998", 10, 104);
                        graphics.drawString("first release 11/19/98, last release 5/10/1999", 10, 117);
                        graphics.drawString("build environment", 10, 130);
                        graphics.drawString(" machine : Panasonic AL-N2 (Let's note)", 10, 143);
                        graphics.drawString(" os : MS Windows95-Japanese version", 10, 156);
                        graphics.drawString(" cpu : Pentium-166MHz MMX ", 10, 169);
                        graphics.drawString(" memory&hd : 64MB, 2.1GB ", 10, 182);
                        graphics.drawString(" compiler : JDK1.0.2 ", 10, 195);
                        graphics.drawString(" graphics app: Adobe Photoshop4.0J", 10, 208);
                        graphics.drawString(" ", 10, 221);
                        graphics.drawString("masayoshi ueda -cheez-", 10, 234);
                        graphics.drawString("san jose, california, usa", 10, 247);
                        graphics.drawString("spring 1999....", 10, 260);
                        this.drawFish(13, 279, 128, 128, 128);
                    }
                }
                else {
                    graphics.setColor(Color.green);
                    graphics.fillRect(this.leftframexbegin, this.leftframeybegin, this.leftframexbegin + this.minx, this.leftframeybegin + this.maxy);
                    graphics.fillRect(this.upframexbegin, this.upframeybegin, this.upframexbegin + (this.rightframexbegin - this.minx) + 1, this.upframeybegin + this.miny);
                    graphics.fillRect(this.rightframexbegin + 1, this.rightframeybegin, this.minx, this.rightframeyend);
                    graphics.setColor(Color.green);
                    if (this.CurrentStage >= 50) {
                        graphics.drawString("Stage: ", this.stagex, this.stagey);
                        graphics.setColor(Color.yellow);
                        graphics.drawString("hidden", this.stagex + 40, this.stagey);
                        graphics.setColor(Color.green);
                    }
                    else if (!this.StageClear) {
                        graphics.drawString("Stage: " + this.CurrentStage + " of " + this.TotalStages, this.stagex, this.stagey);
                    }
                    else {
                        graphics.drawString("Stage: " + (this.CurrentStage - 1) + " of " + this.TotalStages, this.stagex, this.stagey);
                    }
                    graphics.drawString("Speed (0=Fast, 100=Slow): " + this.delayvalue, this.stagex + 125, this.stagey);
                    graphics.drawString("Balls : " + this.numball, this.ballremainx, this.ballremainy);
                    graphics.setColor(Color.yellow);
                    graphics.fillOval(this.currentx, this.currenty, this.ballsize, this.ballsize);
                    graphics.setColor(Color.blue);
                    graphics.fillRect(this.padx + this.ballsizehalf, this.pady, this.padxsize - this.ballsize, this.padysize);
                    graphics.drawImage(this.picturepadside, this.padx, this.pady, this);
                    graphics.drawImage(this.picturepadside, this.padx + this.padxsize - this.ballsize, this.pady, this);
                    for (int j = 1; j <= this.numby; ++j) {
                        for (int k = 1; k <= this.numbx; ++k) {
                            if (this.blocks[j][k]) {
                                if (this.CurrentStage == 89) {
                                    if (j == 25) {
                                        graphics.setColor(new Color(0, 0, 255));
                                    }
                                    else if (j == 24) {
                                        graphics.setColor(new Color(0, 0, 245));
                                    }
                                    else if (j == 23) {
                                        graphics.setColor(new Color(0, 0, 235));
                                    }
                                    else if (j == 22) {
                                        graphics.setColor(new Color(0, 0, 225));
                                    }
                                    else if (j == 21) {
                                        graphics.setColor(new Color(0, 0, 215));
                                    }
                                    else if (j == 20) {
                                        graphics.setColor(new Color(0, 0, 205));
                                    }
                                    else if (j == 19) {
                                        graphics.setColor(new Color(0, 0, 195));
                                    }
                                    else if (j == 18) {
                                        graphics.setColor(new Color(0, 0, 185));
                                    }
                                    else if (j == 17) {
                                        graphics.setColor(new Color(0, 0, 175));
                                    }
                                    else if (j == 16) {
                                        graphics.setColor(new Color(0, 0, 165));
                                    }
                                    else if (j == 15) {
                                        graphics.setColor(new Color(0, 0, 155));
                                    }
                                    else if (j == 14) {
                                        graphics.setColor(new Color(0, 0, 145));
                                    }
                                    else if (j == 13) {
                                        graphics.setColor(new Color(0, 0, 135));
                                    }
                                    else if (j == 12) {
                                        graphics.setColor(new Color(0, 0, 125));
                                    }
                                    else if (j == 11) {
                                        graphics.setColor(new Color(0, 0, 115));
                                    }
                                    else if (j == 10) {
                                        graphics.setColor(new Color(0, 0, 105));
                                    }
                                    else if (j == 9) {
                                        graphics.setColor(new Color(0, 0, 95));
                                    }
                                    else if (j == 8) {
                                        graphics.setColor(new Color(0, 0, 85));
                                    }
                                    else if (j == 7) {
                                        graphics.setColor(new Color(0, 0, 75));
                                    }
                                    else if (j == 6) {
                                        graphics.setColor(new Color(0, 0, 65));
                                    }
                                    else if (j == 5) {
                                        graphics.setColor(new Color(0, 0, 55));
                                    }
                                    else if (j == 4) {
                                        graphics.setColor(new Color(0, 0, 45));
                                    }
                                    else if (j == 3) {
                                        graphics.setColor(new Color(0, 0, 35));
                                    }
                                    else if (j == 2) {
                                        graphics.setColor(new Color(0, 0, 25));
                                    }
                                    else {
                                        graphics.setColor(new Color(0, 0, 15));
                                    }
                                    graphics.fillRect(this.blockx[j][k], this.blocky[j][k], this.lenofblock - 1, this.heightofblock - 1);
                                }
                                else if (this.blockcolor[j][k] == 0) {
                                    graphics.drawImage(this.picturebrick0, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                else if (this.blockcolor[j][k] == 1) {
                                    graphics.drawImage(this.picturebrick1, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                else if (this.blockcolor[j][k] == 2) {
                                    graphics.drawImage(this.picturebrick2, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                else if (this.blockcolor[j][k] == 3) {
                                    graphics.drawImage(this.picturebrick3, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                else if (this.blockcolor[j][k] == 4) {
                                    graphics.drawImage(this.picturebrick4, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                else if (this.blockcolor[j][k] == 5) {
                                    graphics.drawImage(this.picturebrick5, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                else if (this.blockcolor[j][k] == 6) {
                                    graphics.drawImage(this.picturebrick6, this.blockx[j][k], this.blocky[j][k], this);
                                }
                                if (this.numextrabricks > 0) {
                                    for (int l = 1; l <= this.numextrabricks; ++l) {
                                        if (this.extrabrickx[l] == j && this.extrabricky[l] == k) {
                                            graphics.drawImage(this.picturebrickone, this.blockx[j][k], this.blocky[j][k], this);
                                        }
                                    }
                                }
                                if (this.numextendbricks > 0) {
                                    for (int n = 1; n <= this.numextendbricks; ++n) {
                                        if (this.extendbrickx[n] == j && this.extendbricky[n] == k) {
                                            graphics.drawImage(this.picturebrickext, this.blockx[j][k], this.blocky[j][k], this);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!this.waiting || this.InWaitForStart) {
                        return;
                    }
                    graphics.setColor(Color.white);
                    graphics.drawString(this.Click, this.centerx - this.LenofClick, this.YofClick);
                }
            }
        }
        else {
            graphics.setColor(Color.green);
            graphics.drawString("BAP! v1.50b", 5, 30);
            graphics.drawString("Loading images.  Please wait....", 5, 50);
            graphics.drawLine(5, 80, 5 + this.StatusBarLength, 80);
            graphics.drawLine(5 + this.StatusBarLength, 80, 5 + this.StatusBarLength, 90);
            graphics.drawLine(5 + this.StatusBarLength, 90, 5, 90);
            graphics.fillRect(5, 80, this.CurrentStatusBarLength, 10);
        }
    }
    
    public void LetterPutPassword(final char c) {
        this.myG.setColor(Color.gray);
        this.myG.drawString(this.OKButton, this.centerx - this.LenofOKButton, this.YofOKButton);
        this.myG.setColor(Color.gray);
        this.myG.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.setColor(new Color(255, 255, 255));
        this.myG.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.myG.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        if (this.CursorPWD == 1) {
            this.myG.setColor(Color.gray);
            this.myG.drawString(c + "", this.ppanelx + 70 - 4, this.ppanely + 170 + 1);
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
            this.myG.setColor(Color.gray);
            this.myG.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
            this.myG.setColor(Color.white);
            this.myG.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
            this.waiting = true;
            return;
        }
        if (this.CursorPWD == 2) {
            this.myG.setColor(Color.gray);
            this.myG.drawString(c + "", this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1);
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
            this.myG.setColor(Color.gray);
            this.myG.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
            this.myG.setColor(Color.white);
            this.myG.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
            this.waiting = true;
            return;
        }
        if (this.CursorPWD == 3) {
            this.myG.setColor(Color.gray);
            this.myG.drawString(c + "", this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1);
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
            this.myG.setColor(Color.gray);
            this.myG.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
            this.myG.setColor(Color.white);
            this.myG.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
            this.waiting = true;
            return;
        }
        if (this.CursorPWD == 4) {
            this.myG.setColor(Color.gray);
            this.myG.drawString(c + "", this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1);
            ++this.CursorPWD;
            this.PasswordEnteredString += c;
            this.myG.setColor(Color.gray);
            this.myG.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
            this.myG.setColor(Color.white);
            this.myG.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
            this.waiting = true;
            return;
        }
        if (this.CursorPWD == 5) {
            this.myG.setColor(Color.gray);
            this.myG.drawString(c + "", this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1);
            ++this.CursorPWD;
            this.myG.setColor(Color.gray);
            this.myG.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
            if (this.PasswordEnteredString.length() == 5) {
                this.PasswordEnteredString.replace(this.PasswordEnteredString.charAt(5), c);
            }
            else {
                this.PasswordEnteredString += c;
            }
            this.waiting = true;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.inTitle && n >= this.FirstChoiceBeginX && n2 >= this.FirstChoiceBeginY && n <= this.FirstChoiceEndX && n2 <= this.FirstChoiceEndY) {
            this.waiting = false;
        }
        else if (this.inTitle && n >= this.SecondChoiceBeginX && n2 >= this.SecondChoiceBeginY && n <= this.SecondChoiceEndX && n2 <= this.SecondChoiceEndY) {
            this.GotoPasswordScreen = true;
            this.waiting = false;
        }
        else if (this.inTitle && n >= 172 && n2 >= 140 && n <= 238 && n2 <= 150) {
            this.GotoAboutScreen = true;
            this.waiting = false;
        }
        else if (this.inTitle) {
            this.waiting = true;
        }
        else if (this.inPasswordScreen) {
            if (n >= this.ppanelx + 25 && n <= this.ppanelx + 40 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('A');
            }
            else if (n >= this.ppanelx + 25 + 21 && n <= this.ppanelx + 25 + 21 + 13 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('B');
            }
            else if (n >= this.ppanelx + 25 + 40 && n <= this.ppanelx + 25 + 40 + 14 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('C');
            }
            else if (n >= this.ppanelx + 25 + 60 && n <= this.ppanelx + 25 + 60 + 14 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('D');
            }
            else if (n >= this.ppanelx + 25 + 80 && n <= this.ppanelx + 25 + 80 + 14 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('E');
            }
            else if (n >= this.ppanelx + 25 + 99 && n <= this.ppanelx + 25 + 99 + 12 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('F');
            }
            else if (n >= this.ppanelx + 25 + 117 && n <= this.ppanelx + 25 + 117 + 15 && n2 >= this.ppanely + 11 && n2 <= this.ppanely + 26) {
                this.LetterPutPassword('G');
            }
            else if (n >= this.ppanelx + 25 + 4 && n <= this.ppanelx + 25 + 4 + 13 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('H');
            }
            else if (n >= this.ppanelx + 25 + 22 && n <= this.ppanelx + 25 + 22 + 10 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('I');
            }
            else if (n >= this.ppanelx + 25 + 35 && n <= this.ppanelx + 25 + 35 + 12 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('J');
            }
            else if (n >= this.ppanelx + 25 + 53 && n <= this.ppanelx + 25 + 53 + 15 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('K');
            }
            else if (n >= this.ppanelx + 25 + 73 && n <= this.ppanelx + 25 + 73 + 14 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('L');
            }
            else if (n >= this.ppanelx + 25 + 91 && n <= this.ppanelx + 25 + 91 + 18 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('M');
            }
            else if (n >= this.ppanelx + 25 + 114 && n <= this.ppanelx + 25 + 114 + 15 && n2 >= this.ppanely + 32 && n2 <= this.ppanely + 48) {
                this.LetterPutPassword('N');
            }
            else if (n >= this.ppanelx + 24 && n <= this.ppanelx + 24 + 16 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('O');
            }
            else if (n >= this.ppanelx + 44 && n <= this.ppanelx + 44 + 14 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('P');
            }
            else if (n >= this.ppanelx + 64 && n <= this.ppanelx + 64 + 17 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('Q');
            }
            else if (n >= this.ppanelx + 84 && n <= this.ppanelx + 84 + 17 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('R');
            }
            else if (n >= this.ppanelx + 104 && n <= this.ppanelx + 104 + 17 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('S');
            }
            else if (n >= this.ppanelx + 122 && n <= this.ppanelx + 122 + 16 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('T');
            }
            else if (n >= this.ppanelx + 142 && n <= this.ppanelx + 142 + 16 && n2 >= this.ppanely + 54 && n2 <= this.ppanely + 71) {
                this.LetterPutPassword('U');
            }
            else if (n >= this.ppanelx + 43 && n <= this.ppanelx + 43 + 15 && n2 >= this.ppanely + 76 && n2 <= this.ppanely + 93) {
                this.LetterPutPassword('V');
            }
            else if (n >= this.ppanelx + 63 && n <= this.ppanelx + 62 + 20 && n2 >= this.ppanely + 76 && n2 <= this.ppanely + 93) {
                this.LetterPutPassword('W');
            }
            else if (n >= this.ppanelx + 86 && n <= this.ppanelx + 86 + 16 && n2 >= this.ppanely + 76 && n2 <= this.ppanely + 93) {
                this.LetterPutPassword('X');
            }
            else if (n >= this.ppanelx + 104 && n <= this.ppanelx + 104 + 17 && n2 >= this.ppanely + 76 && n2 <= this.ppanely + 93) {
                this.LetterPutPassword('Y');
            }
            else if (n >= this.ppanelx + 124 && n <= this.ppanelx + 124 + 16 && n2 >= this.ppanely + 76 && n2 <= this.ppanely + 93) {
                this.LetterPutPassword('Z');
            }
            else if (n >= this.ppanelx + 25 && n <= this.ppanelx + 25 + 130 && n2 >= this.ppanely + 97 && n2 <= this.ppanely + 119) {
                if (this.CursorPWD != 1) {
                    if (this.CursorPWD == 2) {
                        this.myG.setColor(Color.black);
                        this.myG.fillRect(this.ppanelx + 70 - 4, this.ppanely + 170 + 1 - 10, 9, 10);
                        --this.CursorPWD;
                        this.PasswordEnteredString = "";
                        this.myG.setColor(Color.white);
                        this.myG.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
                        this.myG.setColor(Color.gray);
                        this.myG.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
                        this.waiting = true;
                    }
                    else if (this.CursorPWD == 3) {
                        this.myG.setColor(Color.black);
                        this.myG.fillRect(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1 - 10, 9, 10);
                        --this.CursorPWD;
                        final String substring = this.PasswordEnteredString.substring(0, 1);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = substring;
                        this.myG.setColor(Color.white);
                        this.myG.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
                        this.myG.setColor(Color.gray);
                        this.myG.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
                        this.waiting = true;
                    }
                    else if (this.CursorPWD == 4) {
                        this.myG.setColor(Color.black);
                        this.myG.fillRect(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1 - 10, 9, 10);
                        --this.CursorPWD;
                        final String substring2 = this.PasswordEnteredString.substring(0, 2);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = substring2;
                        this.myG.setColor(Color.white);
                        this.myG.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
                        this.myG.setColor(Color.gray);
                        this.myG.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
                        this.waiting = true;
                    }
                    else if (this.CursorPWD == 5) {
                        this.myG.setColor(Color.black);
                        this.myG.fillRect(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1 - 10, 9, 10);
                        --this.CursorPWD;
                        final String substring3 = this.PasswordEnteredString.substring(0, 3);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = substring3;
                        this.myG.setColor(Color.white);
                        this.myG.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
                        this.myG.setColor(Color.gray);
                        this.myG.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
                        this.waiting = true;
                    }
                    else if (this.CursorPWD == 6) {
                        this.myG.setColor(Color.black);
                        this.myG.fillRect(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1 - 10, 9, 10);
                        --this.CursorPWD;
                        final String substring4 = this.PasswordEnteredString.substring(0, 4);
                        this.PasswordEnteredString = "";
                        this.PasswordEnteredString = substring4;
                        this.myG.setColor(Color.white);
                        this.myG.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
                        this.waiting = true;
                    }
                }
            }
            else if (n >= this.centerx - this.LenofOKButton && n <= this.centerx + this.LenofOKButton && n2 >= this.YofOKButton - 12 && n2 <= this.YofOKButton) {
                this.ExitPassword = true;
            }
        }
        else if (this.inAboutScreen) {
            if (n >= this.centerx - this.LenofOKButton && n <= this.centerx + this.LenofOKButton && n2 >= this.YofOKButton - 12 && n2 <= this.YofOKButton) {
                this.ExitAboutScreen = true;
            }
        }
        else {
            this.waiting = false;
        }
        return true;
    }
    
    public void decidecurrentx() {
        if ((this.currentx - this.currentx % this.ballsize - this.ballsizehalf) % this.ballsize == this.ballsizehalf) {
            this.currentx -= this.currentx % this.ballsize;
            return;
        }
        if ((this.currentx - this.currentx % this.ballsize - this.ballsizehalf) % this.ballsize == 0) {
            this.currentx = this.currentx - this.currentx % this.ballsize + this.ballsizehalf;
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.waiting && n >= this.minx && n <= this.maxx - this.padxsize - 1) {
            this.padx = n;
            this.padMoved = true;
        }
        else if (!this.waiting && n <= this.minx) {
            this.padx = this.minx;
            this.padMoved = true;
        }
        else if (!this.waiting && n > this.maxx - this.padxsize - 1) {
            this.padx = this.maxx - this.padxsize - 1;
            this.padMoved = true;
        }
        else if (this.waiting && this.InWaitForStart) {
            if (n >= this.minx && n <= this.maxx - this.padxsize - 1) {
                this.padx = n;
                this.currentx = this.padx + this.thisstagepadxsizediv2 - this.ballsizehalf;
                this.decidecurrentx();
                this.padMoved = true;
            }
            else if (n <= this.minx) {
                this.padx = this.minx;
                this.currentx = this.padx + this.thisstagepadxsizediv2 - this.ballsizehalf;
                this.decidecurrentx();
                this.padMoved = true;
            }
            else if (n > this.maxx - this.padxsize - 1) {
                this.padx = this.maxx - this.padxsize - 1;
                this.currentx = this.padx + this.thisstagepadxsizediv2 - this.ballsizehalf;
                this.decidecurrentx();
                this.padMoved = true;
            }
        }
        return true;
    }
    
    public void ballDraw() {
        this.myG.setColor(Color.black);
        this.myG.fillOval(this.previousx, this.previousy, this.ballsize, this.ballsize);
        this.previousx = this.currentx;
        this.previousy = this.currenty;
        this.myG.setColor(Color.yellow);
        this.myG.fillOval(this.currentx, this.currenty, this.ballsize, this.ballsize);
    }
    
    public void padDraw() {
        this.myG.setColor(Color.black);
        if (this.previouspadx + this.padxsize >= this.rightframexbegin) {
            this.myG.fillRect(this.previouspadx, this.previouspady, this.rightframexbegin - this.previouspadx, this.padysize);
        }
        else {
            this.myG.fillRect(this.previouspadx, this.previouspady, this.padxsize, this.padysize);
        }
        this.previouspadx = this.padx;
        this.previouspady = this.pady;
        this.myG.setColor(Color.blue);
        if (this.padx + this.padxsize >= this.rightframexbegin) {
            this.myG.fillRect(this.padx + this.ballsizehalf, this.pady, this.rightframexbegin - this.padx - this.ballsize, this.padysize);
            this.myG.drawImage(this.picturepadside, this.padx, this.pady, this);
            this.myG.drawImage(this.picturepadside, this.rightframexbegin - this.ballsize, this.pady, this);
        }
        else {
            this.myG.fillRect(this.padx + this.ballsizehalf, this.pady, this.padxsize - this.ballsize, this.padysize);
            this.myG.drawImage(this.picturepadside, this.padx, this.pady, this);
            this.myG.drawImage(this.picturepadside, this.padx + this.padxsize - this.ballsize, this.pady, this);
        }
        this.padMoved = false;
    }
    
    public void waitformousedown() {
        this.myG.setColor(Color.white);
        this.myG.drawString(this.Click, this.centerx - this.LenofClick, this.YofClick);
    }
    
    public void CheckIfSpecialBlockisHit(final int n, final int n2) {
        if (this.numextrabricks > 0) {
            for (int i = 1; i <= this.numextrabricks; ++i) {
                if (this.extrabrickx[i] == n && this.extrabricky[i] == n2) {
                    this.extrabrickx[i] = 0;
                    this.extrabricky[i] = 0;
                    this.myG.setColor(Color.black);
                    this.myG.drawString("Balls : " + this.numball, this.ballremainx, this.ballremainy);
                    ++this.numball;
                    this.myG.setColor(Color.green);
                    this.myG.drawString("Balls : " + this.numball, this.ballremainx, this.ballremainy);
                }
            }
        }
        if (this.numextendbricks > 0) {
            for (int j = 1; j <= this.numextendbricks; ++j) {
                if (this.extendbrickx[j] == n && this.extendbricky[j] == n2) {
                    this.extendbrickx[j] = 0;
                    this.extendbricky[j] = 0;
                    this.padxsize += this.ballsize * 2;
                    this.padDraw();
                }
            }
        }
    }
    
    public void checkIfHitBlock() {
        this.actionTaken = false;
        if (this.xmove > 0 && this.ymove < 0) {
            for (int i = 1; i <= this.numby; ++i) {
                if (this.currenty == this.blocky[i][1] + this.heightofblock) {
                    for (int j = 1; j <= this.numbx; ++j) {
                        if (this.currentx >= this.blockx[i][j] && this.currentx <= this.blockx[i][j] + this.lenofblock - this.ballsize && this.blocks[i][j] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[i][j], this.blocky[i][j], this.lenofblock, this.heightofblock);
                            this.blocks[i][j] = false;
                            this.CheckIfSpecialBlockisHit(i, j);
                            this.ymove *= -1;
                            this.actionTaken = true;
                            if (this.currentx == this.blockx[i][j] + this.lenofblock - this.ballsize && j < this.numbx && i > 1) {
                                if (this.blocks[i - 1][j + 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[i - 1][j + 1], this.blocky[i - 1][j + 1], this.lenofblock, this.heightofblock);
                                    this.blocks[i - 1][j + 1] = false;
                                    this.CheckIfSpecialBlockisHit(i - 1, j + 1);
                                    this.xmove *= -1;
                                }
                                else if (i > 2 && j < this.numbx && this.currentx == this.blockx[i][j] + this.lenofblock - this.ballsize && this.blocks[i - 2][j + 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[i - 2][j + 1], this.blocky[i - 2][j + 1], this.lenofblock, this.heightofblock);
                                    this.blocks[i - 2][j + 1] = false;
                                    this.CheckIfSpecialBlockisHit(i - 2, j + 1);
                                    this.ymove *= -1;
                                    this.xmove *= -1;
                                    this.actionTaken = true;
                                }
                            }
                        }
                        else if (j != 1 && this.currentx == this.blockx[i][j] - this.ballsize && !this.blocks[i][j - 1] && this.blocks[i - 1][j] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[i - 1][j], this.blocky[i - 1][j], this.lenofblock, this.heightofblock);
                            this.blocks[i - 1][j] = false;
                            this.CheckIfSpecialBlockisHit(i - 1, j);
                            this.xmove *= -1;
                            this.actionTaken = true;
                        }
                        else if (j != 1 && this.currentx == this.blockx[i][j] - this.ballsize && !this.blocks[i][j - 1] && this.blocks[i][j] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[i][j], this.blocky[i][j], this.lenofblock, this.heightofblock);
                            this.blocks[i][j] = false;
                            this.CheckIfSpecialBlockisHit(i, j);
                            this.ymove *= -1;
                            this.xmove *= -1;
                            this.actionTaken = true;
                        }
                    }
                }
            }
            return;
        }
        if (this.xmove < 0 && this.ymove < 0) {
            for (int k = 1; k <= this.numby; ++k) {
                if (this.currenty == this.blocky[k][1] + this.heightofblock) {
                    for (int l = 1; l <= this.numbx; ++l) {
                        if (this.currentx >= this.blockx[k][l] && this.currentx <= this.blockx[k][l] + this.lenofblock - this.ballsize && this.blocks[k][l] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[k][l], this.blocky[k][l], this.lenofblock, this.heightofblock);
                            this.blocks[k][l] = false;
                            this.CheckIfSpecialBlockisHit(k, l);
                            this.ymove *= -1;
                            this.actionTaken = true;
                            if (this.currentx == this.blockx[k][l] && l > 1 && k > 1) {
                                if (this.blocks[k - 1][l - 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[k - 1][l - 1], this.blocky[k - 1][l - 1], this.lenofblock, this.heightofblock);
                                    this.blocks[k - 1][l - 1] = false;
                                    this.CheckIfSpecialBlockisHit(k - 1, l - 1);
                                    this.xmove *= -1;
                                }
                                else if (k > 2 && l > 1 && this.currentx == this.blockx[k][l] && this.blocks[k - 2][l - 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[k - 2][l - 1], this.blocky[k - 2][l - 1], this.lenofblock, this.heightofblock);
                                    this.blocks[k - 2][l - 1] = false;
                                    this.CheckIfSpecialBlockisHit(k - 2, l - 1);
                                    this.ymove *= -1;
                                    this.xmove *= -1;
                                    this.actionTaken = true;
                                }
                            }
                        }
                        else if (l != this.numbx && this.currentx == this.blockx[k][l] + this.lenofblock && !this.blocks[k][l + 1] && this.blocks[k - 1][l] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[k - 1][l], this.blocky[k - 1][l], this.lenofblock, this.heightofblock);
                            this.blocks[k - 1][l] = false;
                            this.CheckIfSpecialBlockisHit(k - 1, l);
                            this.xmove *= -1;
                            this.actionTaken = true;
                        }
                        else if (l != this.numbx && this.currentx == this.blockx[k][l] + this.lenofblock && !this.blocks[k][l + 1] && this.blocks[k][l] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[k][l], this.blocky[k][l], this.lenofblock, this.heightofblock);
                            this.blocks[k][l] = false;
                            this.CheckIfSpecialBlockisHit(k, l);
                            this.ymove *= -1;
                            this.xmove *= -1;
                            this.actionTaken = true;
                        }
                    }
                }
            }
            return;
        }
        if (this.xmove > 0 && this.ymove > 0) {
            for (int n = 1; n <= this.numby; ++n) {
                if (this.currenty == this.blocky[n][1] - this.heightofblock) {
                    for (int n2 = 1; n2 <= this.numbx; ++n2) {
                        if (this.currentx >= this.blockx[n][n2] && this.currentx <= this.blockx[n][n2] + this.lenofblock - this.ballsize && this.blocks[n][n2] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[n][n2], this.blocky[n][n2], this.lenofblock, this.heightofblock);
                            this.blocks[n][n2] = false;
                            this.CheckIfSpecialBlockisHit(n, n2);
                            this.ymove *= -1;
                            this.actionTaken = true;
                            if (this.currentx == this.blockx[n][n2] + this.lenofblock - this.ballsize && n2 < this.numbx && n < this.numby) {
                                if (this.blocks[n + 1][n2 + 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[n + 1][n2 + 1], this.blocky[n + 1][n2 + 1], this.lenofblock, this.heightofblock);
                                    this.blocks[n + 1][n2 + 1] = false;
                                    this.CheckIfSpecialBlockisHit(n + 1, n2 + 1);
                                    this.xmove *= -1;
                                }
                                else if (n < this.numby - 2 && n2 < this.numbx && this.currentx == this.blockx[n][n2] + this.lenofblock - this.ballsize && this.blocks[n + 2][n2 + 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[n + 2][n2 + 1], this.blocky[n + 2][n2 + 1], this.lenofblock, this.heightofblock);
                                    this.blocks[n + 2][n2 + 1] = false;
                                    this.CheckIfSpecialBlockisHit(n + 2, n2 + 1);
                                    this.ymove *= -1;
                                    this.xmove *= -1;
                                    this.actionTaken = true;
                                }
                            }
                        }
                        else if (n2 != 1 && this.currentx == this.blockx[n][n2] - this.ballsize && !this.blocks[n][n2 - 1] && this.blocks[n + 1][n2] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[n + 1][n2], this.blocky[n + 1][n2], this.lenofblock, this.heightofblock);
                            this.blocks[n + 1][n2] = false;
                            this.CheckIfSpecialBlockisHit(n + 1, n2);
                            this.xmove *= -1;
                            this.actionTaken = true;
                        }
                        else if (n2 != 1 && this.currentx == this.blockx[n][n2] - this.ballsize && !this.blocks[n][n2 - 1] && this.blocks[n][n2] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[n][n2], this.blocky[n][n2], this.lenofblock, this.heightofblock);
                            this.blocks[n][n2] = false;
                            this.CheckIfSpecialBlockisHit(n, n2);
                            this.ymove *= -1;
                            this.xmove *= -1;
                            if (n == this.numby - 1) {
                                this.ymove *= -1;
                            }
                            this.actionTaken = true;
                        }
                    }
                }
            }
            return;
        }
        if (this.xmove < 0 && this.ymove > 0) {
            for (int n3 = 1; n3 <= this.numby; ++n3) {
                if (this.currenty == this.blocky[n3][1] - this.heightofblock) {
                    for (int n4 = 1; n4 <= this.numbx; ++n4) {
                        if (this.currentx >= this.blockx[n3][n4] && this.currentx <= this.blockx[n3][n4] + this.lenofblock - this.ballsize && this.blocks[n3][n4] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[n3][n4], this.blocky[n3][n4], this.lenofblock, this.heightofblock);
                            this.blocks[n3][n4] = false;
                            this.CheckIfSpecialBlockisHit(n3, n4);
                            this.ymove *= -1;
                            this.actionTaken = true;
                            if (this.currentx == this.blockx[n3][n4] && n4 > 1 && n3 > 1) {
                                if (this.blocks[n3 + 1][n4 - 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[n3 + 1][n4 - 1], this.blocky[n3 + 1][n4 - 1], this.lenofblock, this.heightofblock);
                                    this.blocks[n3 + 1][n4 - 1] = false;
                                    this.CheckIfSpecialBlockisHit(n3 + 1, n4 - 1);
                                    this.xmove *= -1;
                                }
                                else if (n3 > 1 && n4 < this.numby - 1 && this.currentx == this.blockx[n3][n4] && this.blocks[n3 + 2][n4 - 1]) {
                                    this.myG.setColor(Color.black);
                                    this.myG.fillRect(this.blockx[n3 + 2][n4 - 1], this.blocky[n3 + 2][n4 - 1], this.lenofblock, this.heightofblock);
                                    this.blocks[n3 + 2][n4 - 1] = false;
                                    this.CheckIfSpecialBlockisHit(n3 + 2, n4 - 1);
                                    this.ymove *= -1;
                                    this.xmove *= -1;
                                    this.actionTaken = true;
                                }
                            }
                        }
                        else if (n4 != this.numbx && this.currentx == this.blockx[n3][n4] + this.lenofblock && !this.blocks[n3][n4 + 1] && this.blocks[n3 + 1][n4] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[n3 + 1][n4], this.blocky[n3 + 1][n4], this.lenofblock, this.heightofblock);
                            this.blocks[n3 + 1][n4] = false;
                            this.CheckIfSpecialBlockisHit(n3 + 1, n4);
                            this.xmove *= -1;
                            this.actionTaken = true;
                        }
                        else if (n4 != this.numbx && this.currentx == this.blockx[n3][n4] + this.lenofblock && !this.blocks[n3][n4 + 1] && this.blocks[n3][n4] && !this.actionTaken) {
                            this.myG.setColor(Color.black);
                            this.myG.fillRect(this.blockx[n3][n4], this.blocky[n3][n4], this.lenofblock, this.heightofblock);
                            this.blocks[n3][n4] = false;
                            this.CheckIfSpecialBlockisHit(n3, n4);
                            this.ymove *= -1;
                            this.xmove *= -1;
                            if (n3 == this.numby - 1) {
                                this.ymove *= -1;
                            }
                            this.actionTaken = true;
                        }
                    }
                }
            }
        }
    }
    
    public void checkIfBlocksAreAllGone() {
        this.StageClear = true;
        for (int i = 1; i <= this.numby; ++i) {
            for (int j = 1; j <= this.numbx; ++j) {
                if (this.blocks[i][j]) {
                    this.StageClear = false;
                }
            }
        }
    }
    
    public void DisplayTitle() {
        int n = 200;
        int n2 = 150;
        int n3 = 100;
        int n4 = 50;
        int n5 = 1;
        int n6 = 1;
        int n7 = 1;
        int n8 = 1;
        this.inTitle = true;
        this.myG.drawImage(this.titlepicture, 25, 20, this);
        this.myG.drawImage(this.playnowpicture, this.FirstChoiceBeginX, this.FirstChoiceBeginY, this);
        this.myG.drawImage(this.passwordpicture, this.SecondChoiceBeginX, this.SecondChoiceBeginY, this);
        this.myG.setColor(new Color(150, 150, 0));
        this.myG.drawString("v1.50b", 361, 110);
        this.myG.setColor(Color.gray);
        this.myG.drawString(this.TitleCopyright, this.centerx - this.LenofTitleCopyright, this.YofTitleCopyright);
        this.myG.drawString(this.MyEmail, this.centerx - this.LenofMyEmail, this.YofMyEmail);
        this.myG.setColor(new Color(255, 255, 255));
        this.myG.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.myG.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.myG.setColor(Color.gray);
        this.myG.drawString(this.AboutButton, this.centerx - this.LenofAboutButton, this.YofAboutButton);
        this.waiting = true;
        while (this.waiting) {
            if (n5 != 0) {
                if (++n >= 255) {
                    n5 = 0;
                }
            }
            else if (n5 == 0 && --n <= 0) {
                n5 = 1;
            }
            if (n6 != 0) {
                if (++n2 >= 255) {
                    n6 = 0;
                }
            }
            else if (n6 == 0 && --n2 <= 0) {
                n6 = 1;
            }
            if (n7 != 0) {
                if (++n3 >= 255) {
                    n7 = 0;
                }
            }
            else if (n7 == 0 && --n3 <= 0) {
                n7 = 1;
            }
            if (n8 != 0) {
                if (++n4 >= 255) {
                    n8 = 0;
                }
            }
            else if (n8 == 0 && --n4 <= 0) {
                n8 = 1;
            }
            try {
                Thread.sleep(this.delaydiv10);
            }
            catch (InterruptedException ex) {
                break;
            }
            this.myG.setColor(new Color(0, n, 0));
            this.myG.drawString("Click", this.centerx - this.LenofClickChoice, this.YofClickChoice);
            this.myG.setColor(new Color(0, n2, 0));
            this.myG.drawString("on", this.centerx - this.LenofClickChoice + 36, this.YofClickChoice);
            this.myG.setColor(new Color(0, n3, 0));
            this.myG.drawString("your", this.centerx - this.LenofClickChoice + 54, this.YofClickChoice);
            this.myG.setColor(new Color(0, n4, 0));
            this.myG.drawString("choice", this.centerx - this.LenofClickChoice + 84, this.YofClickChoice);
        }
        this.inTitle = false;
    }
    
    public void ClearScreen() {
        this.myG.setColor(Color.black);
        this.myG.fillRect(0, 0, this.winsizex + 2, this.winsizey + 3);
    }
    
    public void CongratulationsAllClear() {
        final double n = 0.017453292519943295;
        int n2 = 40;
        final int n3 = 0;
        final int n4 = 0;
        int n5 = 200;
        int n6 = 200;
        this.inFinalMessageScreen = false;
        this.InCongratScreen = true;
        this.ClearScreen();
        this.congratx[1] = -70;
        this.congratx[2] = -60;
        this.congratx[3] = -50;
        this.congratx[4] = -40;
        this.congratx[5] = -30;
        this.congratx[6] = -20;
        this.congratx[7] = -10;
        this.congratx[8] = 0;
        this.congratx[9] = 10;
        this.congratx[10] = 20;
        this.congratx[11] = 30;
        this.congratx[12] = 40;
        this.congratx[13] = 50;
        this.congratx[14] = 60;
        this.congratx[15] = 70;
        this.congratx[16] = 80;
        for (int i = 1; i <= 16; ++i) {
            this.congraty[i] = 0;
        }
        for (int j = 1; j <= 16; ++j) {
            this.congratxl[j] = this.congratx[j];
            this.congratyl[j] = this.congraty[j];
        }
        this.CircleCompleted = false;
        this.waiting = true;
        while (!this.CircleCompleted) {
            this.myG.setColor(Color.black);
            if (this.congratxl[1] < this.winsizex - 12 && this.congratxl[1] > 12 && this.congratyl[1] < this.winsizey - 12 && this.congratyl[1] > 12) {
                this.myG.drawString("C", this.congratxl[1], this.congratyl[1]);
            }
            if (this.congratxl[2] < this.winsizex - 12 && this.congratxl[2] > 12 && this.congratyl[2] < this.winsizey - 12 && this.congratyl[2] > 12) {
                this.myG.drawString("O", this.congratxl[2], this.congratyl[2]);
            }
            if (this.congratxl[3] < this.winsizex - 12 && this.congratxl[3] > 12 && this.congratyl[3] < this.winsizey - 12 && this.congratyl[3] > 12) {
                this.myG.drawString("N", this.congratxl[3], this.congratyl[3]);
            }
            if (this.congratxl[4] < this.winsizex - 12 && this.congratxl[4] > 12 && this.congratyl[4] < this.winsizey - 12 && this.congratyl[4] > 12) {
                this.myG.drawString("G", this.congratxl[4], this.congratyl[4]);
            }
            if (this.congratxl[5] < this.winsizex - 12 && this.congratxl[5] > 12 && this.congratyl[5] < this.winsizey - 12 && this.congratyl[5] > 12) {
                this.myG.drawString("R", this.congratxl[5], this.congratyl[5]);
            }
            if (this.congratxl[6] < this.winsizex - 12 && this.congratxl[6] > 12 && this.congratyl[6] < this.winsizey - 12 && this.congratyl[6] > 12) {
                this.myG.drawString("A", this.congratxl[6], this.congratyl[6]);
            }
            if (this.congratxl[7] < this.winsizex - 12 && this.congratxl[7] > 12 && this.congratyl[7] < this.winsizey - 12 && this.congratyl[7] > 12) {
                this.myG.drawString("T", this.congratxl[7], this.congratyl[7]);
            }
            if (this.congratxl[8] < this.winsizex - 12 && this.congratxl[8] > 12 && this.congratyl[8] < this.winsizey - 12 && this.congratyl[8] > 12) {
                this.myG.drawString("U", this.congratxl[8], this.congratyl[8]);
            }
            if (this.congratxl[9] < this.winsizex - 12 && this.congratxl[9] > 12 && this.congratyl[9] < this.winsizey - 12 && this.congratyl[9] > 12) {
                this.myG.drawString("L", this.congratxl[9], this.congratyl[9]);
            }
            if (this.congratxl[10] < this.winsizex - 12 && this.congratxl[10] > 12 && this.congratyl[10] < this.winsizey - 12 && this.congratyl[10] > 12) {
                this.myG.drawString("A", this.congratxl[10], this.congratyl[10]);
            }
            if (this.congratxl[11] < this.winsizex - 12 && this.congratxl[11] > 12 && this.congratyl[11] < this.winsizey - 12 && this.congratyl[11] > 12) {
                this.myG.drawString("T", this.congratxl[11], this.congratyl[11]);
            }
            if (this.congratxl[12] < this.winsizex - 12 && this.congratxl[12] > 12 && this.congratyl[12] < this.winsizey - 12 && this.congratyl[12] > 12) {
                this.myG.drawString("I", this.congratxl[12], this.congratyl[12]);
            }
            if (this.congratxl[13] < this.winsizex - 12 && this.congratxl[13] > 12 && this.congratyl[13] < this.winsizey - 12 && this.congratyl[13] > 12) {
                this.myG.drawString("O", this.congratxl[13], this.congratyl[13]);
            }
            if (this.congratxl[14] < this.winsizex - 12 && this.congratxl[14] > 12 && this.congratyl[14] < this.winsizey - 12 && this.congratyl[14] > 12) {
                this.myG.drawString("N", this.congratxl[14], this.congratyl[14]);
            }
            if (this.congratxl[15] < this.winsizex - 12 && this.congratxl[15] > 12 && this.congratyl[15] < this.winsizey - 12 && this.congratyl[15] > 12) {
                this.myG.drawString("S", this.congratxl[15], this.congratyl[15]);
            }
            if (this.congratxl[16] < this.winsizex - 12 && this.congratxl[16] > 12 && this.congratyl[16] < this.winsizey - 12 && this.congratyl[16] > 12) {
                this.myG.drawString("!", this.congratxl[16], this.congratyl[16]);
            }
            this.myG.setColor(Color.green);
            if (this.congratx[1] < this.winsizex - 12 && this.congratx[1] > 12 && this.congraty[1] < this.winsizey - 12 && this.congraty[1] > 12) {
                this.myG.drawString("C", this.congratx[1], this.congraty[1]);
            }
            if (this.congratx[2] < this.winsizex - 12 && this.congratx[2] > 12 && this.congraty[2] < this.winsizey - 12 && this.congraty[2] > 12) {
                this.myG.drawString("O", this.congratx[2], this.congraty[2]);
            }
            if (this.congratx[3] < this.winsizex - 12 && this.congratx[3] > 12 && this.congraty[3] < this.winsizey - 12 && this.congraty[3] > 12) {
                this.myG.drawString("N", this.congratx[3], this.congraty[3]);
            }
            if (this.congratx[4] < this.winsizex - 12 && this.congratx[4] > 12 && this.congraty[4] < this.winsizey - 12 && this.congraty[4] > 12) {
                this.myG.drawString("G", this.congratx[4], this.congraty[4]);
            }
            if (this.congratx[5] < this.winsizex - 12 && this.congratx[5] > 12 && this.congraty[5] < this.winsizey - 12 && this.congraty[5] > 12) {
                this.myG.drawString("R", this.congratx[5], this.congraty[5]);
            }
            if (this.congratx[6] < this.winsizex - 12 && this.congratx[6] > 12 && this.congraty[6] < this.winsizey - 12 && this.congraty[6] > 12) {
                this.myG.drawString("A", this.congratx[6], this.congraty[6]);
            }
            if (this.congratx[7] < this.winsizex - 12 && this.congratx[7] > 12 && this.congraty[7] < this.winsizey - 12 && this.congraty[7] > 12) {
                this.myG.drawString("T", this.congratx[7], this.congraty[7]);
            }
            if (this.congratx[8] < this.winsizex - 12 && this.congratx[8] > 12 && this.congraty[8] < this.winsizey - 12 && this.congraty[8] > 12) {
                this.myG.drawString("U", this.congratx[8], this.congraty[8]);
            }
            if (this.congratx[9] < this.winsizex - 12 && this.congratx[9] > 12 && this.congraty[9] < this.winsizey - 12 && this.congraty[9] > 12) {
                this.myG.drawString("L", this.congratx[9], this.congraty[9]);
            }
            if (this.congratx[10] < this.winsizex - 12 && this.congratx[10] > 12 && this.congraty[10] < this.winsizey - 12 && this.congraty[10] > 12) {
                this.myG.drawString("A", this.congratx[10], this.congraty[10]);
            }
            if (this.congratx[11] < this.winsizex - 12 && this.congratx[11] > 12 && this.congraty[11] < this.winsizey - 12 && this.congraty[11] > 12) {
                this.myG.drawString("T", this.congratx[11], this.congraty[11]);
            }
            if (this.congratx[12] < this.winsizex - 12 && this.congratx[12] > 12 && this.congraty[12] < this.winsizey - 12 && this.congraty[12] > 12) {
                this.myG.drawString("I", this.congratx[12], this.congraty[12]);
            }
            if (this.congratx[13] < this.winsizex - 12 && this.congratx[13] > 12 && this.congraty[13] < this.winsizey - 12 && this.congraty[13] > 12) {
                this.myG.drawString("O", this.congratx[13], this.congraty[13]);
            }
            if (this.congratx[14] < this.winsizex - 12 && this.congratx[14] > 12 && this.congraty[14] < this.winsizey - 12 && this.congraty[14] > 12) {
                this.myG.drawString("N", this.congratx[14], this.congraty[14]);
            }
            if (this.congratx[15] < this.winsizex - 12 && this.congratx[15] > 12 && this.congraty[15] < this.winsizey - 12 && this.congraty[15] > 12) {
                this.myG.drawString("S", this.congratx[15], this.congraty[15]);
            }
            if (this.congratx[16] < this.winsizex - 12 && this.congratx[16] > 12 && this.congraty[16] < this.winsizey - 12 && this.congraty[16] > 12) {
                this.myG.drawString("!", this.congratx[16], this.congraty[16]);
            }
            for (int k = 1; k <= 16; ++k) {
                this.congratxl[k] = this.congratx[k];
                this.congratyl[k] = this.congraty[k];
            }
            for (int l = 1; l <= 16; ++l) {
                if (l <= 12) {
                    this.congratx[l] = (int)(n5 * Math.cos(n * n2) + (this.centerx - 80) + l * 10) + n3;
                }
                else {
                    this.congratx[l] = (int)(n5 * Math.cos(n * n2) + (this.centerx - 80) + (l * 10 - 4)) + n3;
                }
                this.congraty[l] = (int)(n6 * Math.sin(n * n2) + this.centery) + n4;
            }
            if (this.congratx[1] == this.congratxl[1] && this.congraty[1] == this.congratyl[1]) {
                this.CircleCompleted = true;
            }
            --n5;
            --n6;
            if (n5 < 0) {
                n5 = 0;
            }
            if (n6 < 0) {
                n6 = 0;
            }
            n2 += 5;
            if (n2 > 360) {
                n2 = 0;
            }
            if (n5 > 150) {
                try {
                    Thread.sleep(this.delayvalue);
                    continue;
                }
                catch (InterruptedException ex) {
                    break;
                }
            }
            if (n5 > 100) {
                try {
                    Thread.sleep(this.delaydiv2);
                    continue;
                }
                catch (InterruptedException ex2) {
                    break;
                }
            }
            if (n5 > 50) {
                try {
                    Thread.sleep(this.delaydiv4);
                    continue;
                }
                catch (InterruptedException ex3) {
                    break;
                }
            }
            try {
                Thread.sleep(this.delaydiv5);
            }
            catch (InterruptedException ex4) {
                break;
            }
        }
        this.CongMoveCompleted = false;
        while (!this.CongMoveCompleted) {
            this.myG.setColor(Color.black);
            this.myG.drawString("C", this.congratxl[1], this.congratyl[1]);
            this.myG.drawString("O", this.congratxl[2], this.congratyl[2]);
            this.myG.drawString("N", this.congratxl[3], this.congratyl[3]);
            this.myG.drawString("G", this.congratxl[4], this.congratyl[4]);
            this.myG.drawString("R", this.congratxl[5], this.congratyl[5]);
            this.myG.drawString("A", this.congratxl[6], this.congratyl[6]);
            this.myG.drawString("T", this.congratxl[7], this.congratyl[7]);
            this.myG.drawString("U", this.congratxl[8], this.congratyl[8]);
            this.myG.drawString("L", this.congratxl[9], this.congratyl[9]);
            this.myG.drawString("A", this.congratxl[10], this.congratyl[10]);
            this.myG.drawString("T", this.congratxl[11], this.congratyl[11]);
            this.myG.drawString("I", this.congratxl[12], this.congratyl[12]);
            this.myG.drawString("O", this.congratxl[13], this.congratyl[13]);
            this.myG.drawString("N", this.congratxl[14], this.congratyl[14]);
            this.myG.drawString("S", this.congratxl[15], this.congratyl[15]);
            this.myG.drawString("!", this.congratxl[16], this.congratyl[16]);
            for (int n7 = 1; n7 <= 16; ++n7) {
                --this.congraty[n7];
            }
            this.myG.setColor(Color.green);
            this.myG.drawString("C", this.congratx[1], this.congraty[1]);
            this.myG.drawString("O", this.congratx[2], this.congraty[2]);
            this.myG.drawString("N", this.congratx[3], this.congraty[3]);
            this.myG.drawString("G", this.congratx[4], this.congraty[4]);
            this.myG.drawString("R", this.congratx[5], this.congraty[5]);
            this.myG.drawString("A", this.congratx[6], this.congraty[6]);
            this.myG.drawString("T", this.congratx[7], this.congraty[7]);
            this.myG.drawString("U", this.congratx[8], this.congraty[8]);
            this.myG.drawString("L", this.congratx[9], this.congraty[9]);
            this.myG.drawString("A", this.congratx[10], this.congraty[10]);
            this.myG.drawString("T", this.congratx[11], this.congraty[11]);
            this.myG.drawString("I", this.congratx[12], this.congraty[12]);
            this.myG.drawString("O", this.congratx[13], this.congraty[13]);
            this.myG.drawString("N", this.congratx[14], this.congraty[14]);
            this.myG.drawString("S", this.congratx[15], this.congraty[15]);
            this.myG.drawString("!", this.congratx[16], this.congraty[16]);
            for (int n8 = 1; n8 <= 16; ++n8) {
                this.congratxl[n8] = this.congratx[n8];
                this.congratyl[n8] = this.congraty[n8];
            }
            try {
                Thread.sleep(this.delaydiv2);
            }
            catch (InterruptedException ex5) {
                break;
            }
            if (this.congraty[16] <= 18) {
                this.CongMoveCompleted = true;
            }
        }
        this.repaint();
        this.waiting = true;
        while (this.waiting) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex6) {
                break;
            }
        }
        this.inFinalMessageScreen = true;
        this.ClearScreen();
        this.myG.setColor(Color.gray);
        this.myG.drawString("thank you for playing", 10, 13);
        this.myG.drawString("[yet another game of Ball And Paddle]", 10, 26);
        this.myG.drawString(" ", 10, 39);
        this.myG.drawString("special password:", 10, 52);
        this.myG.drawString("AHEWQ", 10, 65);
        this.myG.drawString(" ", 10, 78);
        this.myG.drawString("program written by masayoshi ueda -cheez-", 10, 91);
        this.myG.drawString("program started on march 18, 1998", 10, 104);
        this.myG.drawString("first release 11/19/98, last release 5/10/1999", 10, 117);
        this.myG.drawString("build environment", 10, 130);
        this.myG.drawString(" machine : Panasonic AL-N2 (Let's note)", 10, 143);
        this.myG.drawString(" os : MS Windows95-Japanese version", 10, 156);
        this.myG.drawString(" cpu : Pentium-166MHz MMX ", 10, 169);
        this.myG.drawString(" memory&hd : 64MB, 2.1GB ", 10, 182);
        this.myG.drawString(" compiler : JDK1.0.2 ", 10, 195);
        this.myG.drawString(" graphics app: Adobe Photoshop4.0J", 10, 208);
        this.myG.drawString(" ", 10, 221);
        this.myG.drawString("masayoshi ueda -cheez-", 10, 234);
        this.myG.drawString("san jose, california, usa", 10, 247);
        this.myG.drawString("spring 1999....", 10, 260);
        this.drawFish(13, 279, 128, 128, 128);
        this.waiting = true;
        while (this.waiting) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex7) {
                break;
            }
        }
        this.inFinalMessageScreen = false;
        this.InCongratScreen = false;
    }
    
    public void drawFish(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.myG.setColor(new Color(n3, n4, n5));
        this.myG.drawArc(n, n2 + 4, 12, 5, 0, 360);
        this.myG.drawLine(n + 13, n2 + 6, n + 15, n2 + 8);
        this.myG.drawLine(n + 13, n2 + 6, n + 15, n2 + 4);
    }
    
    public void DisplayAboutScreen() {
        this.ClearScreen();
        this.myG.setColor(Color.gray);
        for (int i = 1; i <= this.CopyrightRow; ++i) {
            this.myG.drawString(this.CopyrightString[i], this.centerx - this.LenofCopyrightdiv2[i], this.YofCopyright[i]);
        }
        this.drawFish(this.centerx - 7, this.YofCopyright[12] - 4, 128, 128, 128);
        this.myG.drawString(this.OKButton, this.centerx - this.LenofOKButton, this.YofOKButton);
        this.myG.setColor(new Color(255, 255, 255));
        this.myG.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.myG.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.ExitAboutScreen = false;
        int n = 0;
        int n2 = 129;
        int n3 = 1;
        this.repaint();
        while (!this.ExitAboutScreen) {
            try {
                Thread.sleep(this.delaydiv5);
            }
            catch (InterruptedException ex) {
                return;
            }
            if (n == 1) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
                if (n3 == 0) {
                    this.myG.setColor(new Color(n2, n2, n2));
                    this.myG.drawString("w", this.centerx - this.LenofCopyrightdiv2[23], this.YofCopyright[23]);
                }
            }
            else if (n == 2) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
                if (n3 == 0) {
                    this.myG.setColor(new Color(n2, n2, n2));
                    this.myG.drawString("y", this.centerx - this.LenofCopyrightdiv2[5], this.YofCopyright[5]);
                }
            }
            else if (n == 3) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
                if (n3 == 0) {
                    this.myG.setColor(new Color(n2, n2, n2));
                    this.myG.drawString("T", this.centerx - this.LenofCopyrightdiv2[4], this.YofCopyright[4]);
                }
            }
            else if (n == 4) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
                if (n3 == 0) {
                    this.myG.setColor(new Color(n2, n2, n2));
                    this.myG.drawString("o", this.centerx - this.LenofCopyrightdiv2[20], this.YofCopyright[20]);
                }
            }
            else if (n == 5) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
                if (n3 == 0) {
                    this.myG.setColor(new Color(n2, n2, n2));
                    this.myG.drawString("B", this.centerx - this.LenofCopyrightdiv2[16], this.YofCopyright[16]);
                }
            }
            else if (n == 6) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
                if (n3 == 0) {
                    this.drawFish(this.centerx - 7, this.YofCopyright[12] - 4, n2, n2, n2);
                }
            }
            else if (n == 0) {
                if (n2 >= 256) {
                    n2 = 255;
                    n3 = 0;
                }
                else if (n2 <= 127) {
                    n2 = 128;
                    n3 = 1;
                    if (++n == 7) {
                        n = 0;
                    }
                }
            }
            if (n3 != 0) {
                ++n2;
            }
            else {
                --n2;
            }
        }
    }
    
    public void DisplayPasswordScreen() {
        this.ClearScreen();
        this.myG.drawImage(this.passwordpanelpicture, this.ppanelx, this.ppanely, this);
        this.myG.setColor(Color.white);
        this.myG.drawLine(this.ppanelx + 70 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.setColor(Color.gray);
        this.myG.drawLine(this.ppanelx + 70 + 9 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 9 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 18 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 18 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 27 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 27 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawLine(this.ppanelx + 70 + 36 - 4, this.ppanely + 170 + 1, this.ppanelx + 70 + 36 + 7 - 4, this.ppanely + 170 + 1);
        this.myG.drawString(this.OKButton, this.centerx - this.LenofOKButton, this.YofOKButton);
        this.myG.setColor(new Color(255, 255, 255));
        this.myG.drawString(this.Speedline, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.myG.drawString(this.SpeedEqual + this.delayvalue, this.centerx - this.LenofSpeedline, this.YofSpeedline);
        this.repaint();
        this.CursorPWD = 1;
        this.ExitPassword = false;
        while (!this.ExitPassword) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void CheckPassword() {
        if (this.PasswordEnteredString.equals("HELLO")) {
            this.CurrentStage = 3;
            return;
        }
        if (this.PasswordEnteredString.equals("SNARE")) {
            this.CurrentStage = 6;
            return;
        }
        if (this.PasswordEnteredString.equals("BUGSS")) {
            this.CurrentStage = 9;
            return;
        }
        if (this.PasswordEnteredString.equals("SPACE")) {
            this.CurrentStage = 12;
            return;
        }
        if (this.PasswordEnteredString.equals("OCEAN")) {
            this.CurrentStage = 15;
            return;
        }
        if (this.PasswordEnteredString.equals("NIHON")) {
            this.CurrentStage = 18;
            return;
        }
        if (this.PasswordEnteredString.equals("VENUS")) {
            this.CurrentStage = 21;
            return;
        }
        if (this.PasswordEnteredString.equals("MOCHA")) {
            this.CurrentStage = 24;
            return;
        }
        if (this.PasswordEnteredString.equals("TREEZ")) {
            this.CurrentStage = 27;
            return;
        }
        if (this.PasswordEnteredString.equals("ORPMF")) {
            this.CurrentStage = 30;
            return;
        }
        if (this.PasswordEnteredString.equals("WYTOB")) {
            this.CurrentStage = 89;
            return;
        }
        if (this.PasswordEnteredString.equals("AHEWQ")) {
            this.CurrentStage = 99;
        }
    }
    
    public void DisplayPassword(final int n, final String s) {
        this.ClearScreen();
        this.InDisplayPassword = true;
        this.myG.setColor(Color.white);
        this.myG.drawString("Password for stage " + n + " is:", this.centerx - this.LenofPasswordFor, this.YofPasswordFor);
        this.myG.drawString(s, this.centerx - this.LenofActualPassword, this.YofActualPassword);
        this.repaint();
        this.waiting = true;
        this.waitformousedown();
        while (this.waiting) {
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {
                break;
            }
        }
        this.InDisplayPassword = false;
    }
    
    public void InitialBallPadDraw() {
        if (this.padx >= this.minx && this.padx <= this.maxx - this.padxsize - 1) {
            this.currentx = this.padx + this.padxsize / 2 - this.ballsize / 2;
            this.decidecurrentx();
        }
        else if (this.padx <= this.minx) {
            this.padx = this.minx;
            this.currentx = this.padx + this.padxsize / 2 - this.ballsize / 2;
            this.decidecurrentx();
        }
        else if (this.padx > this.maxx - this.padxsize - 1) {
            this.padx = this.maxx - this.padxsize - 1;
            this.currentx = this.padx + this.padxsize / 2 - this.ballsize / 2;
            this.decidecurrentx();
        }
        this.padDraw();
        this.ballDraw();
    }
    
    public void DisplayGameoverScreen() {
        final int n = 90;
        final int n2 = 85;
        final int n3 = 80;
        final int n4 = 75;
        final int n5 = 75;
        final int n6 = 80;
        final int n7 = 85;
        final int n8 = 90;
        final int n9 = 100;
        final int n10 = this.centerx - 54;
        final int n11 = 12;
        int n13;
        int n12 = n13 = n9;
        int n15;
        int n14 = n15 = n9;
        int n17;
        int n16 = n17 = n9;
        int n19;
        int n18 = n19 = n9;
        int n21;
        int n20 = n21 = n9;
        int n23;
        int n22 = n23 = n9;
        int n25;
        int n24 = n25 = n9;
        int n27;
        int n26 = n27 = n9;
        int n35;
        int n34;
        int n33;
        int n32;
        int n31;
        int n30;
        int n29;
        int n28 = n29 = (n30 = (n31 = (n32 = (n33 = (n34 = (n35 = -1))))));
        this.ClearScreen();
        this.waiting = true;
        this.waitformousedown();
        while (this.waiting) {
            this.myG.setColor(Color.black);
            this.myG.drawString("G", n10, n13);
            this.myG.drawString("A", n10 + n11, n15);
            this.myG.drawString("M", n10 + n11 * 2, n17);
            this.myG.drawString("E", n10 + n11 * 3, n19);
            this.myG.drawString("O", n10 + n11 * 5, n21);
            this.myG.drawString("V", n10 + n11 * 6, n23);
            this.myG.drawString("E", n10 + n11 * 7, n25);
            this.myG.drawString("R", n10 + n11 * 8, n27);
            this.myG.setColor(Color.green);
            this.myG.drawString("G", n10, n12);
            this.myG.drawString("A", n10 + n11, n14);
            this.myG.drawString("M", n10 + n11 * 2, n16);
            this.myG.drawString("E", n10 + n11 * 3, n18);
            this.myG.drawString("O", n10 + n11 * 5, n20);
            this.myG.drawString("V", n10 + n11 * 6, n22);
            this.myG.drawString("E", n10 + n11 * 7, n24);
            this.myG.drawString("R", n10 + n11 * 8, n26);
            try {
                Thread.sleep(this.delayvalue);
            }
            catch (InterruptedException ex) {
                return;
            }
            n13 = n12;
            n15 = n14;
            n17 = n16;
            n19 = n18;
            n21 = n20;
            n23 = n22;
            n25 = n24;
            n27 = n26;
            n12 += n28;
            if (n12 == n) {
                n28 *= -1;
            }
            else if (n12 == n9) {
                n28 *= -1;
            }
            n14 += n35;
            if (n14 == n2) {
                n35 *= -1;
            }
            else if (n14 == n9) {
                n35 *= -1;
            }
            n16 += n34;
            if (n16 == n3) {
                n34 *= -1;
            }
            else if (n16 == n9) {
                n34 *= -1;
            }
            n18 += n33;
            if (n18 == n4) {
                n33 *= -1;
            }
            else if (n18 == n9) {
                n33 *= -1;
            }
            n20 += n32;
            if (n20 == n5) {
                n32 *= -1;
            }
            else if (n20 == n9) {
                n32 *= -1;
            }
            n22 += n31;
            if (n22 == n6) {
                n31 *= -1;
            }
            else if (n22 == n9) {
                n31 *= -1;
            }
            n24 += n30;
            if (n24 == n7) {
                n30 *= -1;
            }
            else if (n24 == n9) {
                n30 *= -1;
            }
            n26 += n29;
            if (n26 == n8) {
                n29 *= -1;
            }
            else {
                if (n26 != n9) {
                    continue;
                }
                n29 *= -1;
            }
        }
    }
    
    public void run() {
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
        this.reinitall();
        this.reinit();
        while (!this.gameQuit) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {
                return;
            }
            this.GotoAboutScreen = false;
            this.gameover = false;
            this.ClearScreen();
            this.DisplayTitle();
            this.EnterPressed = false;
            this.PasswordEnteredString = "";
            if (this.GotoPasswordScreen) {
                this.CursorPWD = 1;
                this.PasswordEnteredString = "";
                this.ClearScreen();
                this.inPasswordScreen = true;
                this.DisplayPasswordScreen();
                this.inPasswordScreen = false;
                this.GotoPasswordScreen = false;
                this.waiting = false;
            }
            else if (this.GotoAboutScreen) {
                this.ClearScreen();
                this.inAboutScreen = true;
                this.DisplayAboutScreen();
                this.inAboutScreen = false;
                this.waiting = false;
            }
            if (!this.GotoAboutScreen) {
                this.CheckPassword();
                this.FillStagePattern();
                this.InitialBallPadDraw();
                this.repaint();
                this.waiting = true;
                this.waitformousedown();
                this.InWaitForStart = true;
                this.padMoved = false;
                if (!this.testmode) {
                    while (this.waiting) {
                        try {
                            Thread.sleep(1L);
                        }
                        catch (InterruptedException ex3) {
                            break;
                        }
                        if (this.padMoved) {
                            this.padDraw();
                            this.ballDraw();
                        }
                    }
                }
                this.InWaitForStart = false;
                this.repaint();
                while (!this.gameover) {
                    while (true) {
                        try {
                            Thread.sleep(this.delayvalue);
                        }
                        catch (InterruptedException ex4) {
                            break;
                        }
                        this.currentx += this.xmove;
                        if (this.currentx >= this.maxx - this.ballsize) {
                            this.xmove *= -1;
                            this.currentx = this.maxx - this.ballsize;
                        }
                        if (this.currentx <= this.minx) {
                            this.xmove *= -1;
                            this.currentx = this.minx;
                        }
                        this.currenty += this.ymove;
                        if (this.currenty >= this.pady - this.padysize) {
                            if (this.currentx >= this.padx && this.currentx <= this.padx + this.padxsize - this.ballsize) {
                                this.ymove *= -1;
                                this.currenty = this.pady - this.ballsize;
                            }
                            else if (this.xmove > 0 && this.currentx >= this.padx - this.ballsize - this.ballsizehalf && this.currentx <= this.padx) {
                                this.ymove *= -1;
                                this.xmove *= -1;
                                this.currenty = this.pady - this.ballsize;
                            }
                            else if (this.xmove < 0 && this.currentx <= this.padx + this.padxsize + this.ballsize && this.currentx >= this.padx + this.padxsize - this.ballsize) {
                                this.ymove *= -1;
                                this.xmove *= -1;
                                this.currenty = this.pady - this.ballsize;
                            }
                            else if (this.xmove < 0 && this.currentx >= this.padx - this.ballsize - 1 && this.currentx <= this.padx) {
                                this.ymove *= -1;
                                this.currenty = this.pady - this.ballsize;
                            }
                            else if (this.xmove > 0 && this.currentx <= this.padx + this.padxsize + this.ballsize && this.currentx >= this.padx + this.padxsize - this.ballsize) {
                                this.ymove *= -1;
                                this.currenty = this.pady - this.ballsize;
                            }
                            else {
                                --this.numball;
                                if (this.numball <= -1) {
                                    this.gameover = true;
                                    break;
                                }
                                this.reinit();
                                if (!this.testmode) {
                                    this.waiting = true;
                                    this.waitformousedown();
                                    while (this.waiting) {
                                        try {
                                            Thread.sleep(1L);
                                        }
                                        catch (InterruptedException ex5) {
                                            break;
                                        }
                                    }
                                }
                                this.InitialBallPadDraw();
                                this.repaint();
                                this.waiting = true;
                                if (!this.testmode) {
                                    this.waitformousedown();
                                }
                                this.InWaitForStart = true;
                                this.thisstagepadxsizediv2 = this.padxsize / 2;
                                this.padMoved = false;
                                if (!this.testmode) {
                                    while (this.waiting) {
                                        try {
                                            Thread.sleep(1L);
                                        }
                                        catch (InterruptedException ex6) {
                                            break;
                                        }
                                        if (this.padMoved) {
                                            this.padDraw();
                                            this.ballDraw();
                                        }
                                    }
                                }
                                this.InWaitForStart = false;
                                this.repaint();
                            }
                        }
                        if (this.currenty <= this.miny) {
                            this.ymove *= -1;
                            this.currenty = this.miny;
                        }
                        this.ballDraw();
                        if (this.testmode) {
                            this.padx = this.currentx;
                            this.padDraw();
                        }
                        this.checkIfHitBlock();
                        if (!this.actionTaken) {
                            this.checkIfBlocksAreAllGone();
                        }
                        if (this.padMoved) {
                            this.padDraw();
                        }
                        if (this.StageClear) {
                            ++this.CurrentStage;
                            if (this.CurrentStage >= 50) {
                                this.CurrentStage = 1;
                                break;
                            }
                            break;
                        }
                    }
                    if (!this.testmode) {
                        this.waiting = true;
                        this.waitformousedown();
                        while (this.waiting) {
                            try {
                                Thread.sleep(1L);
                            }
                            catch (InterruptedException ex7) {
                                break;
                            }
                        }
                    }
                    if (!this.testmode && !this.gameover) {
                        if (this.CurrentStage == 3) {
                            this.DisplayPassword(3, "HELLO");
                        }
                        else if (this.CurrentStage == 6) {
                            this.DisplayPassword(6, "SNARE");
                        }
                        else if (this.CurrentStage == 9) {
                            this.DisplayPassword(9, "BUGSS");
                        }
                        else if (this.CurrentStage == 12) {
                            this.DisplayPassword(12, "SPACE");
                        }
                        else if (this.CurrentStage == 15) {
                            this.DisplayPassword(15, "OCEAN");
                        }
                        else if (this.CurrentStage == 18) {
                            this.DisplayPassword(18, "NIHON");
                        }
                        else if (this.CurrentStage == 21) {
                            this.DisplayPassword(21, "VENUS");
                        }
                        else if (this.CurrentStage == 24) {
                            this.DisplayPassword(24, "MOCHA");
                        }
                        else if (this.CurrentStage == 27) {
                            this.DisplayPassword(27, "TREEZ");
                        }
                        else if (this.CurrentStage == 30) {
                            this.DisplayPassword(30, "ORPMF");
                        }
                    }
                    if (this.CurrentStage == this.TotalStages + 1) {
                        this.AllClear = true;
                    }
                    else {
                        this.reinit();
                        if (!this.gameover) {
                            this.FillStagePattern();
                            this.InitialBallPadDraw();
                            this.repaint();
                            if (!this.testmode) {
                                this.waiting = true;
                                this.waitformousedown();
                                this.InWaitForStart = true;
                                this.padMoved = false;
                                while (this.waiting) {
                                    try {
                                        Thread.sleep(1L);
                                    }
                                    catch (InterruptedException ex8) {
                                        break;
                                    }
                                    if (this.padMoved) {
                                        this.padDraw();
                                        this.ballDraw();
                                    }
                                }
                            }
                        }
                        this.InWaitForStart = false;
                        if (!this.gameover) {
                            this.repaint();
                        }
                        else {
                            this.DisplayGameoverScreen();
                        }
                    }
                    if (this.AllClear) {
                        this.CongratulationsAllClear();
                        this.gameover = true;
                        break;
                    }
                }
                if (!this.gameover && !this.testmode) {
                    this.waiting = true;
                    this.waitformousedown();
                    while (this.waiting) {
                        try {
                            Thread.sleep(5L);
                        }
                        catch (InterruptedException ex9) {
                            break;
                        }
                    }
                }
                this.reinitall();
            }
        }
    }
    
    public void reinitall() {
        this.inFinalMessageScreen = false;
        this.CongMoveCompleted = false;
        this.CircleCompleted = false;
        this.exitthisloop = false;
        this.actionTaken = false;
        this.InCongratScreen = false;
        this.InDisplayPassword = false;
        this.ExitAboutScreen = false;
        this.GotoAboutScreen = false;
        this.inAboutScreen = false;
        this.stoprandomcreating = false;
        this.InWaitForStart = false;
        this.ExitPassword = false;
        this.EnterPressed = false;
        this.GotoPasswordScreen = false;
        this.AllClear = false;
        this.StageClear = false;
        this.gameQuit = false;
        this.inTitle = true;
        this.inPasswordScreen = false;
        this.gameover = false;
        this.padMoved = false;
        this.waiting = false;
        this.GotoPasswordScreen = false;
        this.StageClear = false;
        this.CurrentStage = 1;
        this.gameQuit = false;
        this.rnd = new Random();
        this.numball = 2;
        this.xmove = 5;
        this.ymove = this.ballmovement * -1;
        this.padx = this.winx / 2;
        this.pady = this.innerysize;
        this.gameover = false;
        this.padMoved = false;
        this.previouspadx = this.padx;
        this.previouspady = this.pady;
        this.waiting = true;
        this.setBackground(Color.black);
        this.currentx = 45;
        this.currenty = this.firstblockpositiony + this.heightofblock + this.ballsize * 3 + this.ballsizehalf;
        this.previousx = this.currentx;
        this.previousy = this.currenty;
    }
    
    public void reinit() {
        this.GotoPasswordScreen = false;
        this.StageClear = false;
        this.currentx = 45;
        this.currenty = this.firstblockpositiony + this.heightofblock + this.ballsize * 3 + this.ballsizehalf;
        this.previousx = this.currentx;
        this.previousy = this.currenty;
        this.xmove = 5;
        this.ymove = this.ballmovement * -1;
        this.padx = this.winx / 2;
        this.pady = this.innerysize;
        this.padMoved = false;
        this.previouspadx = this.padx;
        this.previouspady = this.pady;
        this.setBackground(Color.black);
        this.AllClear = false;
    }
    
    public bap150b() {
        this.testmode = false;
        this.StatusBarLength = 300;
        this.NumOfImages = 15;
        this.CurrentStatusBarLength = 1;
        this.thisstagepadxsizediv2 = 10;
        this.ppanelx = 115;
        this.ppanely = 50;
        this.ang = 90;
        this.FirstChoiceBeginX = 65;
        this.FirstChoiceBeginY = 170;
        this.FirstChoiceLengthX = 300;
        this.FirstChoiceLengthY = 30;
        this.SecondChoiceBeginX = 60;
        this.SecondChoiceBeginY = 210;
        this.SecondChoiceLengthX = 300;
        this.SecondChoiceLengthY = 30;
        this.gameover = false;
    }
}
