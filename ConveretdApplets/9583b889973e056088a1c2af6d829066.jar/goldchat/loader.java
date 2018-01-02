// 
// Decompiled by Procyon v0.5.30
// 

package goldchat;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import jclass.bwt.JCActionEvent;
import java.awt.Container;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import jclass.bwt.JCActionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.Font;
import java.net.URL;
import java.awt.Component;
import jclass.util.JCString;
import java.util.StringTokenizer;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Menu;
import java.awt.TextArea;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.GridLayout;
import jclass.bwt.JCTextField;
import java.awt.Choice;
import jclass.bwt.JCList;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Frame;
import java.awt.Dimension;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.awt.Color;
import java.applet.AppletContext;
import java.applet.Applet;

public class loader extends Applet implements Runnable
{
    static boolean more;
    String RoomId;
    String PrevUsername;
    static String Username;
    String Password;
    String Profile;
    String RoomWelcome;
    static String UserFlag;
    static String myCodeBase;
    static String myDocumentBase;
    static AppletContext myAppletContext;
    static Color primaryBgColor;
    static Color primaryFgColor;
    static Color secondaryBgColor;
    static Color secondaryFgColor;
    static Color buttonBgColor;
    static Color buttonFgColor;
    static Color inputBgColor;
    static Color inputFgColor;
    static Color outputBgColor;
    String Action1;
    String Action2;
    String Action3;
    String Action4;
    String Action5;
    String Action6;
    String Action7;
    String Action8;
    String Action9;
    String Action10;
    int myport;
    int loopDelay;
    static int roomDelay;
    static boolean Debug;
    int z;
    int y;
    int j;
    String authstr;
    Thread myThread;
    BufferedReader sin;
    static PrintWriter sout;
    Socket mysocket;
    String myhost;
    boolean Connected;
    static boolean PermitInput;
    Dimension screenSize;
    Options myOptions;
    static MessageBox myMessageBox;
    Score myScore;
    SetWelcome mySetWelcome;
    SetStaff mySetStaff;
    SendUrl mySendUrl;
    static Chat[] myChat;
    boolean UsernameLocked;
    boolean PasswordLocked;
    boolean ProfileLocked;
    boolean AutoConnect;
    String ParamUsername;
    String ParamPassword;
    String ParamProfile;
    String ParamAutoConnect;
    Frame RoomFrame;
    List BlockedList;
    Label FloatLabel;
    boolean isStandalone;
    BorderLayout borderLayout1;
    Panel AppletWPanel;
    Panel AppletEPanel;
    Panel AppletSPanel;
    Panel AppletNPanel;
    Panel AppletBasePanel;
    BorderLayout borderLayout2;
    Panel ContentPanel;
    FlowLayout flowLayout1;
    FlowLayout flowLayout2;
    FlowLayout flowLayout4;
    Label CopyrightTxt;
    Panel TitlePanel;
    BorderLayout borderLayout4;
    Label TitleTxt;
    BorderLayout borderLayout5;
    BorderLayout borderLayout3;
    Panel BannerPanel;
    FlowLayout flowLayout3;
    Panel StatusPanel;
    Panel CardPanel;
    CardLayout cardLayout1;
    static Label PromptTxt;
    static Label StatusTxt;
    Panel Card1;
    Panel Card2;
    BorderLayout borderLayout7;
    Panel MainBtnPanel;
    FlowLayout flowLayout5;
    Button TextBtn;
    Panel UserPanel;
    BorderLayout borderLayout8;
    Panel InputPanel;
    BorderLayout borderLayout9;
    Panel OutputPanel;
    JCList UserList;
    Button SendBtn;
    Choice MsgType;
    JCTextField InputTxt;
    Label UsernameTxt;
    Panel RPadding;
    Panel LPadding;
    FlowLayout flowLayout6;
    FlowLayout flowLayout7;
    Button FloatBtn;
    Button LeaveBtn;
    Button ActionsBtn;
    Button OptionsBtn;
    Button StaffBtn;
    Button AdminBtn;
    Button HelpBtn;
    FlowLayout flowLayout8;
    Panel LoginPanel;
    GridLayout gridLayout1;
    Label label1;
    JCTextField PasswordInp;
    Label label3;
    JCTextField UsernameInp;
    Button ConnectBtn;
    Label label4;
    Label label5;
    Label label6;
    Label label7;
    Label label8;
    Panel UserBtnPanel;
    BorderLayout borderLayout11;
    Panel panel1;
    GridLayout gridLayout2;
    Button ChatBtn;
    Button BlockBtn;
    PopupMenu actionsMenu;
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    JCTextField ProfileInp;
    Label label9;
    Button ProfileBtn;
    Button EjectBtn;
    MenuItem menuItem4;
    MenuItem menuItem5;
    MenuItem menuItem6;
    MenuItem menuItem7;
    MenuItem menuItem8;
    MenuItem menuItem9;
    MenuItem menuItem10;
    PopupMenu textMenu;
    MenuItem menuItem11;
    MenuItem menuItem12;
    MenuItem menuItem13;
    MenuItem menuItem14;
    PopupMenu staffMenu;
    PopupMenu adminMenu;
    MenuItem menuItem15;
    MenuItem menuItem16;
    MenuItem menuItem17;
    MenuItem menuItem18;
    MenuItem menuItem19;
    MenuItem menuItem20;
    MenuItem menuItem21;
    MenuItem menuItem22;
    MenuItem menuItem23;
    MenuItem menuItem24;
    CardLayout cardLayout2;
    Panel OutputListPanel;
    JCList OutputList;
    BorderLayout borderLayout10;
    Panel OutputTextPanel;
    BorderLayout borderLayout12;
    TextArea OutputText;
    MenuItem OutputModeMenu;
    Panel QuizStatusPanel;
    Label QuizStatus;
    GridLayout gridLayout3;
    Label SilencedStatus;
    Label AdminStatus;
    Menu menu1;
    MenuItem menuItem25;
    MenuItem menuItem26;
    MenuItem menuItem27;
    MenuItem menuItem28;
    MenuItem menuItem29;
    MenuItem menuItem30;
    MenuItem menuItem31;
    GridLayout gridLayout4;
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public loader() {
        this.PrevUsername = "Unknown";
        this.z = 0;
        this.y = 0;
        this.j = 17371;
        this.authstr = "";
        this.UsernameLocked = false;
        this.PasswordLocked = false;
        this.ProfileLocked = false;
        this.AutoConnect = false;
        this.ParamUsername = "";
        this.ParamPassword = "";
        this.ParamProfile = "";
        this.ParamAutoConnect = "";
        this.BlockedList = new List();
        this.FloatLabel = new Label();
        this.isStandalone = false;
        this.borderLayout1 = new BorderLayout();
        this.AppletWPanel = new Panel();
        this.AppletEPanel = new Panel();
        this.AppletSPanel = new Panel();
        this.AppletNPanel = new Panel();
        this.AppletBasePanel = new Panel();
        this.borderLayout2 = new BorderLayout();
        this.ContentPanel = new Panel();
        this.flowLayout1 = new FlowLayout();
        this.flowLayout2 = new FlowLayout();
        this.flowLayout4 = new FlowLayout();
        this.CopyrightTxt = new Label();
        this.TitlePanel = new Panel();
        this.borderLayout4 = new BorderLayout();
        this.TitleTxt = new Label();
        this.borderLayout5 = new BorderLayout();
        this.borderLayout3 = new BorderLayout();
        this.BannerPanel = new Panel();
        this.flowLayout3 = new FlowLayout();
        this.StatusPanel = new Panel();
        this.CardPanel = new Panel();
        this.cardLayout1 = new CardLayout();
        this.Card1 = new Panel();
        this.Card2 = new Panel();
        this.borderLayout7 = new BorderLayout();
        this.MainBtnPanel = new Panel();
        this.flowLayout5 = new FlowLayout();
        this.TextBtn = new Button();
        this.UserPanel = new Panel();
        this.borderLayout8 = new BorderLayout();
        this.InputPanel = new Panel();
        this.borderLayout9 = new BorderLayout();
        this.OutputPanel = new Panel();
        this.UserList = new JCList();
        this.SendBtn = new Button();
        this.MsgType = new Choice();
        this.InputTxt = new JCTextField();
        this.UsernameTxt = new Label();
        this.RPadding = new Panel();
        this.LPadding = new Panel();
        this.flowLayout6 = new FlowLayout();
        this.flowLayout7 = new FlowLayout();
        this.FloatBtn = new Button();
        this.LeaveBtn = new Button();
        this.ActionsBtn = new Button();
        this.OptionsBtn = new Button();
        this.StaffBtn = new Button();
        this.AdminBtn = new Button();
        this.HelpBtn = new Button();
        this.flowLayout8 = new FlowLayout();
        this.LoginPanel = new Panel();
        this.gridLayout1 = new GridLayout();
        this.label1 = new Label();
        this.PasswordInp = new JCTextField();
        this.label3 = new Label();
        this.UsernameInp = new JCTextField();
        this.ConnectBtn = new Button();
        this.label4 = new Label();
        this.label5 = new Label();
        this.label6 = new Label();
        this.label7 = new Label();
        this.label8 = new Label();
        this.UserBtnPanel = new Panel();
        this.borderLayout11 = new BorderLayout();
        this.panel1 = new Panel();
        this.gridLayout2 = new GridLayout();
        this.ChatBtn = new Button();
        this.BlockBtn = new Button();
        this.actionsMenu = new PopupMenu();
        this.menuItem1 = new MenuItem();
        this.menuItem2 = new MenuItem();
        this.menuItem3 = new MenuItem();
        this.ProfileInp = new JCTextField();
        this.label9 = new Label();
        this.ProfileBtn = new Button();
        this.EjectBtn = new Button();
        this.menuItem4 = new MenuItem();
        this.menuItem5 = new MenuItem();
        this.menuItem6 = new MenuItem();
        this.menuItem7 = new MenuItem();
        this.menuItem8 = new MenuItem();
        this.menuItem9 = new MenuItem();
        this.menuItem10 = new MenuItem();
        this.textMenu = new PopupMenu();
        this.menuItem11 = new MenuItem();
        this.menuItem12 = new MenuItem();
        this.menuItem13 = new MenuItem();
        this.menuItem14 = new MenuItem();
        this.staffMenu = new PopupMenu();
        this.adminMenu = new PopupMenu();
        this.menuItem15 = new MenuItem();
        this.menuItem16 = new MenuItem();
        this.menuItem17 = new MenuItem();
        this.menuItem18 = new MenuItem();
        this.menuItem19 = new MenuItem();
        this.menuItem20 = new MenuItem();
        this.menuItem21 = new MenuItem();
        this.menuItem22 = new MenuItem();
        this.menuItem23 = new MenuItem();
        this.menuItem24 = new MenuItem();
        this.cardLayout2 = new CardLayout();
        this.OutputListPanel = new Panel();
        this.OutputList = new JCList();
        this.borderLayout10 = new BorderLayout();
        this.OutputTextPanel = new Panel();
        this.borderLayout12 = new BorderLayout();
        this.OutputText = new TextArea("", 20, 20, 1);
        this.OutputModeMenu = new MenuItem();
        this.QuizStatusPanel = new Panel();
        this.QuizStatus = new Label();
        this.gridLayout3 = new GridLayout();
        this.SilencedStatus = new Label();
        this.AdminStatus = new Label();
        this.menu1 = new Menu();
        this.menuItem25 = new MenuItem();
        this.menuItem26 = new MenuItem();
        this.menuItem27 = new MenuItem();
        this.menuItem28 = new MenuItem();
        this.menuItem29 = new MenuItem();
        this.menuItem30 = new MenuItem();
        this.menuItem31 = new MenuItem();
        this.gridLayout4 = new GridLayout();
    }
    
    public void init() {
        try {
            System.out.println("GOLD Chat Room v5.0 by Chat-Forum.com (Client Build 5.00a)\r\n©Copyright 2010 ASCII Software Ltd, All Rights Reserved.");
            this.RoomId = this.getParameter("RoomId", "#");
            if (this.getParameter("AsciiDebug", "0").compareTo("1") == 0) {
                loader.Debug = true;
            }
            else {
                loader.Debug = false;
            }
            if (this.getParameter("PID", "0") == "0") {
                System.out.println("CODE ERROR - Invalid PID");
            }
            else {
                this.myport = Integer.parseInt(this.getParameter("PID", "0"));
            }
            this.loopDelay = Integer.parseInt(this.getParameter("LoopDelay", "100"));
            loader.roomDelay = Integer.parseInt(this.getParameter("RoomDelay", "1000"));
            this.Action1 = this.getParameter("Action1", "laughs out loud");
            this.Action2 = this.getParameter("Action2", "looks around");
            this.Action3 = this.getParameter("Action3", "smiles");
            this.Action4 = this.getParameter("Action4", "grins");
            this.Action5 = this.getParameter("Action5", "runs away");
            this.Action6 = this.getParameter("Action6", "cries like a baby");
            this.Action7 = this.getParameter("Action7", "giggles");
            this.Action8 = this.getParameter("Action8", "applauds");
            this.Action9 = this.getParameter("Action9", "will be right back");
            this.Action10 = this.getParameter("Action10", "waves goodbye");
            try {
                loader.primaryBgColor = Color.decode(this.getParameter("PrimaryBgColor", "#0022AA"));
            }
            catch (Exception ex2) {
                System.out.println("Color Parameter Error (PrimaryBgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.primaryFgColor = Color.decode(this.getParameter("PrimaryFgColor", "#FFFFFF"));
            }
            catch (Exception ex3) {
                System.out.println("Color Parameter Error (PrimaryFgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.secondaryBgColor = Color.decode(this.getParameter("SecondaryBgColor", "#EEEEFF"));
            }
            catch (Exception ex4) {
                System.out.println("Color Parameter Error (SecondaryBgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.secondaryFgColor = Color.decode(this.getParameter("SecondaryFgColor", "#333333"));
            }
            catch (Exception ex5) {
                System.out.println("Color Parameter Error (SecondaryFgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.buttonBgColor = Color.decode(this.getParameter("ButtonBgColor", "#EEEEF8"));
            }
            catch (Exception ex6) {
                System.out.println("Color Parameter Error (ButtonBgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.buttonFgColor = Color.decode(this.getParameter("ButtonFgColor", "#333333"));
            }
            catch (Exception ex7) {
                System.out.println("Color Parameter Error (ButtonFgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.inputBgColor = Color.decode(this.getParameter("InputBgColor", "#FFFFFF"));
            }
            catch (Exception ex8) {
                System.out.println("Color Parameter Error (InputBgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.inputFgColor = Color.decode(this.getParameter("InputFgColor", "#000000"));
            }
            catch (Exception ex9) {
                System.out.println("Color Parameter Error (InputFgColor) Default Used! Check HTML Code.");
            }
            try {
                loader.outputBgColor = Color.decode(this.getParameter("OutputBgColor", "#FCFCFF"));
            }
            catch (Exception ex10) {
                System.out.println("Color Parameter Error (OutputBgColor) Default Used! Check HTML Code.");
            }
            loader.myAppletContext = this.getAppletContext();
            this.myhost = this.getCodeBase().getHost();
            loader.myCodeBase = this.getCodeBase().toString();
            this._$3507();
            this.OutputList.setBackground(loader.outputBgColor);
            this.TitleTxt.setText(String.valueOf(" ").concat(String.valueOf(this.getParameter("Title", "Live Chat Room"))));
            if (this.TitleTxt.getText().length() > 50) {
                this.TitleTxt.setText(" Live Chat Room");
            }
            this.ParamUsername = this.getParameter("Username", "");
            if (this.ParamUsername.length() != 0) {
                if (this.ParamUsername.length() < 3 || this.ParamUsername.length() > 15) {
                    System.out.println("CODE ERROR - Invalid Username Length");
                }
                else {
                    this.UsernameInp.setText(this.ParamUsername);
                    this.UsernameInp.setEnabled(false);
                    this.UsernameLocked = true;
                }
            }
            this.ParamPassword = this.getParameter("Password", "").trim();
            if (this.ParamPassword.length() != 0) {
                if (this.ParamPassword.length() < 3 || this.ParamPassword.length() > 15) {
                    System.out.println("CODE ERROR - Invalid Password Length");
                }
                else {
                    this.PasswordInp.setText(this.ParamPassword);
                    this.PasswordInp.setEnabled(false);
                    this.PasswordLocked = true;
                }
            }
            this.ParamProfile = this.getParameter("Profile", "").trim();
            if (this.ParamProfile.length() != 0) {
                if (this.ParamProfile.length() > 80) {
                    System.out.println("CODE ERROR - Invalid Profile Length");
                }
                else {
                    this.ProfileInp.setText(this.ParamProfile);
                    this.ProfileInp.setEnabled(false);
                    this.ProfileLocked = true;
                }
            }
            this.ParamAutoConnect = this.getParameter("AutoConnect", "FALSE");
            this.AutoConnect = false;
            if (this.ParamAutoConnect.length() != 0 && this.ParamAutoConnect.compareTo("TRUE") == 0 && this.UsernameLocked) {
                this.AutoConnect = true;
            }
            loader.myDocumentBase = this.getDocumentBase().toString();
            this.z = this.myport * loader.myDocumentBase.length();
            this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            this.MsgType.addItem("SAY");
            this.MsgType.addItem("THINK");
            this.MsgType.addItem("ACTION");
            this.CopyrightTxt.setCursor(new Cursor(12));
            if (this.myOptions == null) {
                this.myOptions = new Options();
            }
            this.myOptions.setLocation((this.screenSize.width - 250) / 2, (this.screenSize.height - 240) / 2);
            if (loader.myMessageBox == null) {
                loader.myMessageBox = new MessageBox();
            }
            if (this.myScore == null) {
                this.myScore = new Score();
            }
            this.myScore.setLocation((this.screenSize.width - 500) / 2, (this.screenSize.height - 400) / 2);
            if (this.mySetWelcome == null) {
                this.mySetWelcome = new SetWelcome();
            }
            this.mySetWelcome.setLocation((this.screenSize.width - 412) / 2, (this.screenSize.height - 185) / 2);
            if (this.mySetStaff == null) {
                this.mySetStaff = new SetStaff();
            }
            this.mySetStaff.setLocation((this.screenSize.width - 540) / 2, (this.screenSize.height - 310) / 2);
            if (this.mySendUrl == null) {
                this.mySendUrl = new SendUrl();
            }
            this.mySendUrl.setLocation((this.screenSize.width - 540) / 2, (this.screenSize.height - 180) / 2);
            for (int i = 0; i < 20; ++i) {
                (loader.myChat[i] = new Chat()).setLocation(i * 20 + 10, i * 20 + 10);
            }
            this.UsernameInp.requestFocus();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void ParseLine(final String s) {
        if (loader.Debug) {
            System.err.println(String.valueOf("<IN > ").concat(String.valueOf(s.trim())));
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        String nextToken = "";
        if (!stringTokenizer.hasMoreTokens()) {
            return;
        }
        final String nextToken2 = stringTokenizer.nextToken();
        if (!stringTokenizer.hasMoreTokens()) {
            return;
        }
        final String nextToken3 = stringTokenizer.nextToken();
        if (nextToken2.compareTo("000") == 0 && nextToken3.compareTo("000") == 0) {
            loader.StatusTxt.setText("  Logging In  \\");
            loader.StatusTxt.paint(loader.StatusTxt.getGraphics());
            this.authstr = Integer.toString(this.z + this.y);
            SendLine(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("000 000 ").concat(String.valueOf(this.authstr))).concat(String.valueOf(" "))).concat(String.valueOf(this.RoomId))).concat(String.valueOf(" "))).concat(String.valueOf(loader.Username))).concat(String.valueOf(" "))).concat(String.valueOf(this.Password))).concat(String.valueOf(" "))).concat(String.valueOf(this.PrevUsername))).concat(String.valueOf(" "))).concat(String.valueOf(loader.myDocumentBase))).concat(String.valueOf(" "))).concat(String.valueOf(this.Profile)));
            return;
        }
        if (nextToken2.compareTo("000") == 0 && nextToken3.compareTo("999") == 0) {
            this.GoDisconnect();
            String concat = " ";
            while (stringTokenizer.hasMoreTokens()) {
                concat = String.valueOf(String.valueOf(concat).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            this.ShowMessageBox("Connection Closed!", "Connection Closed!", concat);
            return;
        }
        if (nextToken2.compareTo("000") == 0 && nextToken3.compareTo("200") == 0) {
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            loader.UserFlag = nextToken;
            if (loader.UserFlag.compareTo("USER") == 0) {
                this.AdminStatus.setText("STANDARD USER");
            }
            else {
                this.AdminStatus.setText(String.valueOf(loader.UserFlag).concat(String.valueOf(" USER")));
            }
            this.EjectBtn.setEnabled(false);
            this.StaffBtn.setEnabled(false);
            this.AdminBtn.setEnabled(false);
            if (loader.UserFlag.compareTo("ADMIN") == 0 || loader.UserFlag.compareTo("WIZOP") == 0) {
                this.AdminBtn.setEnabled(true);
                this.StaffBtn.setEnabled(true);
                this.EjectBtn.setEnabled(true);
            }
            if (loader.UserFlag.compareTo("STAFF") == 0) {
                this.AdminBtn.setEnabled(false);
                this.StaffBtn.setEnabled(true);
                this.EjectBtn.setEnabled(true);
            }
            this.PrevUsername = loader.Username;
            this.UsernameTxt.setText(loader.Username);
            loader.StatusTxt.setText("  Logging In  |");
            loader.StatusTxt.paint(loader.StatusTxt.getGraphics());
            SendLine("000 200 OK");
            return;
        }
        if (nextToken2.compareTo("000") == 0 && nextToken3.compareTo("300") == 0) {
            if (stringTokenizer.nextToken().compareTo("NULL") != 0) {
                this.QuizStatus.setVisible(true);
                loader.StatusTxt.setText("Quiz Session In Progress, Stanby.. ");
            }
            else {
                this.QuizStatus.setVisible(false);
            }
            String concat2 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat2 = String.valueOf(String.valueOf(concat2).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            this.RoomWelcome = concat2.trim();
            loader.StatusTxt.setText("  Logging In  /");
            loader.StatusTxt.paint(loader.StatusTxt.getGraphics());
            SendLine("000 500 OK");
            return;
        }
        if (nextToken2.compareTo("000") == 0 && nextToken3.compareTo("500") == 0) {
            loader.StatusTxt.setText("  Logging In  -");
            loader.StatusTxt.paint(loader.StatusTxt.getGraphics());
            while (stringTokenizer.hasMoreTokens()) {
                this.AddUser(stringTokenizer.nextToken());
            }
            this.UIonline();
            if (this.myOptions.SystemSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd5.au");
                }
                catch (Exception ex2) {}
            }
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf(this.RoomWelcome)));
            return;
        }
        if (nextToken2.compareTo("100") == 0 && nextToken3.compareTo("100") == 0) {
            final String nextToken4 = stringTokenizer.nextToken();
            this.AddUser(nextToken4);
            this.AppendText("", String.valueOf(String.valueOf("5 ").concat(String.valueOf(nextToken4))).concat(String.valueOf(" has Entered the Room.")));
            if (this.myOptions.RoomSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd2.au");
                }
                catch (Exception ex3) {}
            }
            return;
        }
        if (nextToken2.compareTo("100") == 0 && nextToken3.compareTo("200") == 0) {
            final String nextToken5 = stringTokenizer.nextToken();
            this.RemoveUser(nextToken5);
            this.AppendText("", String.valueOf(String.valueOf("5 ").concat(String.valueOf(nextToken5))).concat(String.valueOf(" has Left the Room.")));
            if (this.myOptions.RoomSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd3.au");
                }
                catch (Exception ex4) {}
            }
            return;
        }
        if (nextToken2.compareTo("200") == 0 && nextToken3.compareTo("100") == 0) {
            final String nextToken6 = stringTokenizer.nextToken();
            final String nextToken7 = stringTokenizer.nextToken();
            final String nextToken8 = stringTokenizer.nextToken();
            final String nextToken9 = stringTokenizer.nextToken();
            stringTokenizer.nextToken();
            final String nextToken10 = stringTokenizer.nextToken();
            final String concat3 = String.valueOf(String.valueOf(stringTokenizer.nextToken()).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
            String concat4 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat4 = String.valueOf(String.valueOf(concat4).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            String s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("5 [COLOR=black]Profile Information For: [Color=red] ").concat(String.valueOf(nextToken6))).concat(String.valueOf(" [NEWLINE]"))).concat(String.valueOf("[COLOR=darkgray] Login Time:[COLOR=blue] "))).concat(String.valueOf(nextToken10))).concat(String.valueOf("  "))).concat(String.valueOf(concat3))).concat(String.valueOf(" (GMT)[NEWLINE]"));
            if (nextToken7.compareTo("#") != 0) {
                s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("[COLOR=gray] Prev Username:[COLOR=blue] "))).concat(String.valueOf(nextToken7))).concat(String.valueOf(" [NEWLINE]"));
            }
            if (nextToken8.compareTo("#") != 0) {
                s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("[COLOR=gray] User Type:[COLOR=blue] "))).concat(String.valueOf(nextToken8))).concat(String.valueOf(" [NEWLINE]"));
            }
            if (nextToken9.compareTo("#") != 0) {
                s2 = String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("[COLOR=gray] IP Address:[COLOR=blue] "))).concat(String.valueOf(nextToken9))).concat(String.valueOf(" [NEWLINE]"));
            }
            this.AppendText("", String.valueOf(String.valueOf(String.valueOf(s2).concat(String.valueOf("[COLOR=darkgray] Profile Details:[COLOR=blue] "))).concat(String.valueOf(concat4.trim()))).concat(String.valueOf(" [NEWLINE]")));
            return;
        }
        if (nextToken2.compareTo("200") == 0 && nextToken3.compareTo("199") == 0) {
            this.AppendText("", String.valueOf("5 !! ").concat(String.valueOf(String.valueOf(String.valueOf("User ").concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" Is No Longer Available.")))));
            return;
        }
        if (nextToken2.compareTo("300") == 0 && nextToken3.compareTo("100") == 0) {
            final String nextToken11 = stringTokenizer.nextToken();
            String concat5 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat5 = String.valueOf(String.valueOf(concat5).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            try {
                this.BlockedList.remove(nextToken11);
                this.BlockedList.addItem(nextToken11);
                SendLine(String.valueOf(String.valueOf("300 200 ").concat(String.valueOf(nextToken11))).concat(String.valueOf(" || Sorry, This User Has You Blocked.")));
                return;
            }
            catch (Exception ex5) {
                for (int i = 0; i < 20; ++i) {
                    if (loader.myChat[i].getTitle().compareTo(nextToken11) == 0) {
                        if (this.myOptions.SystemSoundsChk.getState()) {
                            try {
                                this.play(this.getCodeBase(), "snd4.au");
                            }
                            catch (Exception ex6) {}
                        }
                        loader.myChat[i].AppendText(nextToken11, concat5);
                        return;
                    }
                }
                if (!this.myOptions.PrvChatChk.getState()) {
                    SendLine(String.valueOf(String.valueOf("300 200 ").concat(String.valueOf(nextToken11))).concat(String.valueOf(" !! User Is Not Accepting Chat Requests.")));
                    return;
                }
                for (int j = 0; j < 20; ++j) {
                    if (loader.myChat[j].getTitle().compareTo("") == 0) {
                        if (this.myOptions.SystemSoundsChk.getState()) {
                            try {
                                this.play(this.getCodeBase(), "snd4.au");
                            }
                            catch (Exception ex7) {}
                        }
                        loader.myChat[j].setVisible(true);
                        loader.myChat[j].setTitle(nextToken11);
                        loader.myChat[j].AppendText(nextToken11, concat5);
                        return;
                    }
                }
                SendLine(String.valueOf(String.valueOf("300 200 ").concat(String.valueOf(nextToken11))).concat(String.valueOf(" !! Sorry, This User Is Too Busy.")));
                return;
            }
        }
        if (nextToken2.compareTo("300") == 0 && nextToken3.compareTo("200") == 0) {
            final String nextToken12 = stringTokenizer.nextToken();
            String concat6 = "!! ";
            while (stringTokenizer.hasMoreTokens()) {
                concat6 = String.valueOf(String.valueOf(concat6).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            for (int k = 0; k < 20; ++k) {
                if (loader.myChat[k].getTitle().compareTo(nextToken12) == 0) {
                    loader.myChat[k].AppendText("", concat6);
                    return;
                }
            }
            return;
        }
        if (nextToken2.compareTo("450") == 0 && nextToken3.compareTo("100") == 0) {
            final String nextToken13 = stringTokenizer.nextToken();
            try {
                this.BlockedList.remove(nextToken13);
                this.BlockedList.addItem(nextToken13);
                return;
            }
            catch (Exception ex8) {
                String s3 = stringTokenizer.nextToken();
                while (stringTokenizer.hasMoreTokens()) {
                    s3 = String.valueOf(String.valueOf(s3).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
                }
                if (this.myOptions.RoomSoundsChk.getState()) {
                    try {
                        this.play(this.getCodeBase(), "snd4.au");
                    }
                    catch (Exception ex9) {}
                }
                this.AppendText(nextToken13, s3);
                return;
            }
        }
        if (nextToken2.compareTo("450") == 0 && nextToken3.compareTo("199") == 0) {
            String s4 = stringTokenizer.nextToken();
            while (stringTokenizer.hasMoreTokens()) {
                s4 = String.valueOf(String.valueOf(s4).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
            }
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf(s4)));
            return;
        }
        if (nextToken2.compareTo("500") == 0 && nextToken3.compareTo("100") == 0) {
            final String nextToken14 = stringTokenizer.nextToken();
            this.AppendText("", String.valueOf("5 Quiz Session Started.  Quiz Host: ").concat(String.valueOf(nextToken14)));
            this.QuizStatus.setVisible(true);
            this.QuizStatusPanel.doLayout();
            loader.StatusTxt.setText("  Quiz Started, Standby..");
            if (nextToken14.compareTo(loader.Username) == 0) {
                this.myScore.setTitle("Quiz Controls  (Quiz Running)");
                this.myScore.StatusTxt.setText("Quiz Session Started ");
                this.myScore.StartBtn.setEnabled(false);
                this.myScore.SendQBtn.setEnabled(true);
                this.myScore.SendABtn.setEnabled(true);
                this.myScore.SendBtn.setEnabled(true);
                this.myScore.ResetAllBtn.setEnabled(true);
                this.myScore.ScoreList.setEnabled(true);
                this.myScore.ScoreList.removeAll();
                for (int l = 0; l < this.UserList.countItems(); ++l) {
                    try {
                        this.myScore.ScoreList.addItem(String.valueOf(((JCString)this.UserList.getItem(l)).getString().trim()).concat(String.valueOf("=0")));
                    }
                    catch (Exception ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                this.myScore.EndBtn.setEnabled(true);
            }
            if (this.myOptions.SystemSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd5.au");
                }
                catch (Exception ex10) {}
            }
            return;
        }
        if (nextToken2.compareTo("500") == 0 && nextToken3.compareTo("199") == 0) {
            String concat7 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat7 = String.valueOf(String.valueOf(concat7).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
            }
            this.myScore.StatusTxt.setText(concat7);
            this.InputTxt.beep();
            return;
        }
        if (nextToken2.compareTo("500") == 0 && nextToken3.compareTo("200") == 0) {
            final String nextToken15 = stringTokenizer.nextToken();
            this.AppendText("", "5 Quiz Session Ended.  Thanks for taking part.");
            this.QuizStatus.setVisible(false);
            this.QuizStatusPanel.doLayout();
            loader.StatusTxt.setText("  Quiz Session Ended");
            if (nextToken15.compareTo(loader.Username) == 0) {
                this.myScore.setTitle("Quiz Controls");
                this.myScore.StatusTxt.setText("Quiz Session Ended ");
                this.myScore.StartBtn.setEnabled(true);
                this.myScore.SendQBtn.setEnabled(false);
                this.myScore.SendABtn.setEnabled(false);
                this.myScore.SendBtn.setEnabled(false);
                this.myScore.ResetAllBtn.setEnabled(false);
                this.myScore.ScoreList.setEnabled(false);
                this.myScore.ScoreList.removeAll();
                this.myScore.EndBtn.setEnabled(false);
            }
            if (this.myOptions.SystemSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd5.au");
                }
                catch (Exception ex11) {}
            }
            return;
        }
        if (nextToken2.compareTo("500") == 0 && nextToken3.compareTo("300") == 0) {
            String concat8 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat8 = String.valueOf(String.valueOf(concat8).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
            }
            this.AppendText("", String.valueOf("4 QUESTION:  ").concat(String.valueOf(concat8)));
            loader.StatusTxt.setText("  New Quiz Question");
            if (this.myOptions.SystemSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd6.au");
                }
                catch (Exception ex12) {}
            }
            return;
        }
        if (nextToken2.compareTo("500") == 0 && nextToken3.compareTo("400") == 0) {
            String concat9 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat9 = String.valueOf(String.valueOf(concat9).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
            }
            this.AppendText("", String.valueOf("4 ANSWER:  ").concat(String.valueOf(concat9)));
            loader.StatusTxt.setText("  Wait For Next Question...");
            if (this.myOptions.SystemSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd6.au");
                }
                catch (Exception ex13) {}
            }
            return;
        }
        if (nextToken2.compareTo("500") == 0 && nextToken3.compareTo("500") == 0) {
            String concat10 = "";
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken16 = stringTokenizer.nextToken();
                concat10 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(concat10).concat(String.valueOf("[COLOR=blue]"))).concat(String.valueOf(nextToken16.substring(0, nextToken16.indexOf("="))))).concat(String.valueOf(" [COLOR=red]"))).concat(String.valueOf(nextToken16.substring(nextToken16.indexOf("=") + 1, nextToken16.length())))).concat(String.valueOf(",  "));
            }
            this.AppendText("", String.valueOf(String.valueOf("5 CURRENT QUIZ SCORES: [NEWLINE] [NEWLINE] ").concat(String.valueOf(concat10))).concat(String.valueOf(" [NEWLINE] [NEWLINE]")));
            loader.StatusTxt.setText("  Quiz Scores Updated");
            if (this.myOptions.SystemSoundsChk.getState()) {
                try {
                    this.play(this.getCodeBase(), "snd5.au");
                }
                catch (Exception ex14) {}
            }
            return;
        }
        if (nextToken2.compareTo("900") == 0 && nextToken3.compareTo("000") == 0) {
            String s5 = stringTokenizer.nextToken();
            while (stringTokenizer.hasMoreTokens()) {
                s5 = String.valueOf(String.valueOf(s5).concat(String.valueOf(" "))).concat(String.valueOf(stringTokenizer.nextToken()));
            }
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf(s5)));
            loader.StatusTxt.setText("");
            return;
        }
        if (nextToken2.compareTo("900") == 0 && nextToken3.compareTo("300") == 0) {
            ShowNewUrl(stringTokenizer.nextToken());
            return;
        }
        if (nextToken2.compareTo("900") == 0 && nextToken3.compareTo("400") == 0) {
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf("The Floor is Closed!  Room Input is Disabled, Please Stand by..")));
            loader.StatusTxt.setText("  FLOOR CLOSED");
            return;
        }
        if (nextToken2.compareTo("900") == 0 && nextToken3.compareTo("450") == 0) {
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf("The Floor has been Opened!  Room Input is now Enabled.")));
            loader.StatusTxt.setText("  FLOOR OPEN");
            return;
        }
        if (nextToken2.compareTo("900") == 0 && nextToken3.compareTo("600") == 0) {
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf("You have been Silenced by a Staff user, Room Input is Disabled. Please Stand by..")));
            this.InputTxt.setEnabled(false);
            this.InputTxt.setText("Silenced - Input Disabled");
            this.SendBtn.setEnabled(false);
            this.ActionsBtn.setEnabled(false);
            this.SilencedStatus.setVisible(true);
            loader.StatusTxt.setText("  SILENCED");
            return;
        }
        if (nextToken2.compareTo("900") == 0 && nextToken3.compareTo("650") == 0) {
            this.AppendText("", String.valueOf("5 ").concat(String.valueOf("You have been Unsilenced, Room Input is now Enabled.")));
            this.InputTxt.setEnabled(true);
            this.InputTxt.setText("");
            this.SendBtn.setEnabled(true);
            this.ActionsBtn.setEnabled(true);
            this.SilencedStatus.setVisible(false);
            loader.StatusTxt.setText("  UNSILENCED");
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("100") == 0) {
            String concat11 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat11 = String.valueOf(String.valueOf(concat11).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            this.mySetWelcome.PromptTxt.setText("Set new Room Welcome Message below. Max 400 chars.      ");
            this.mySetWelcome.PromptTxt.setForeground(loader.secondaryFgColor);
            this.mySetWelcome.MessageTxt.setText(concat11.trim());
            this.mySetWelcome.MessageTxt.setEnabled(true);
            this.mySetWelcome.SetBtn.setEnabled(true);
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("200") == 0) {
            this.AppendText("", "5 New Room Welcome Message Set Ok.");
            this.mySetWelcome.setVisible(false);
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("299") == 0) {
            String concat12 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat12 = String.valueOf(String.valueOf(concat12).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            this.ShowMessageBox("Error", "Error Setting Room Welcome Message", concat12.trim());
            this.mySetWelcome.MessageTxt.setEnabled(true);
            this.mySetWelcome.SetBtn.setEnabled(true);
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("600") == 0) {
            stringTokenizer.nextToken();
            this.mySetStaff.StaffList.removeAll();
            while (stringTokenizer.hasMoreTokens()) {
                this.mySetStaff.StaffList.addItem(stringTokenizer.nextToken());
            }
            this.mySetStaff.UIReady();
            this.mySetStaff.TotalDisp.setText(String.valueOf("Total Staff - ").concat(String.valueOf(Integer.toString(this.mySetStaff.StaffList.getItemCount()))));
            this.mySetStaff.StatusTxt.setText("Add and Remove Staff user accounts here.");
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("610") == 0) {
            final String nextToken17 = stringTokenizer.nextToken();
            this.mySetStaff.StaffList.addItem(nextToken17);
            this.mySetStaff.TotalDisp.setText(String.valueOf("Total Staff - ").concat(String.valueOf(Integer.toString(this.mySetStaff.StaffList.getItemCount()))));
            this.mySetStaff.UIReady();
            this.mySetStaff.StatusTxt.setText(String.valueOf(nextToken17).concat(String.valueOf(" Has Been Added To Staff List.")));
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("620") == 0) {
            final String nextToken18 = stringTokenizer.nextToken();
            this.mySetStaff.StaffList.remove(nextToken18);
            this.mySetStaff.TotalDisp.setText(String.valueOf("Total Staff - ").concat(String.valueOf(Integer.toString(this.mySetStaff.StaffList.getItemCount()))));
            this.mySetStaff.UIReady();
            this.mySetStaff.StatusTxt.setText(String.valueOf(nextToken18).concat(String.valueOf(" Has Been Removed From Staff List.")));
            return;
        }
        if (nextToken2.compareTo("910") == 0 && nextToken3.compareTo("699") == 0) {
            String concat13 = "";
            while (stringTokenizer.hasMoreTokens()) {
                concat13 = String.valueOf(String.valueOf(concat13).concat(String.valueOf(stringTokenizer.nextToken()))).concat(String.valueOf(" "));
            }
            this.mySetStaff.StatusTxt.setText(concat13.trim());
            this.ShowMessageBox("Error", "Staff Update Error", concat13.trim());
            this.mySetStaff.TotalDisp.setText(String.valueOf("Total Staff - ").concat(String.valueOf(Integer.toString(this.mySetStaff.StaffList.getItemCount()))));
            this.mySetStaff.UIReady();
            this.mySetStaff.StatusTxt.setText("");
        }
    }
    
    void AppendText(final String s, String s2) {
        this.AppendTranscript(s, s2);
        String s3 = "";
        String s4 = "";
        final String substring = s2.substring(0, s2.indexOf(" "));
        s2 = s2.substring(s2.indexOf(" "), s2.length());
        if (substring.compareTo("1") == 0) {
            s3 = "[COLOR=blue] > [COLOR=darkgray]";
            s4 = "";
        }
        else if (substring.compareTo("2") == 0) {
            s3 = "[COLOR=blue] .o0{ [COLOR=darkgray]";
            s4 = " [COLOR=blue]}";
        }
        else if (substring.compareTo("3") == 0) {
            s3 = "[COLOR=blue] ** [COLOR=darkgray]";
            s4 = " [COLOR=blue]**";
        }
        else if (substring.compareTo("4") == 0) {
            s3 = "[COLOR=blue] ";
            s4 = " [NEWLINE] [NEWLINE]";
        }
        else if (substring.compareTo("5") == 0) {
            s3 = "[COLOR=gray]";
            s4 = "";
        }
        s2 = String.valueOf(String.valueOf(s3).concat(String.valueOf(s2))).concat(String.valueOf(s4));
        String s5;
        if (s.compareTo("") == 0) {
            s5 = String.valueOf(String.valueOf("[ALIGN=MIDDLE][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img9.gif]"));
        }
        else if (s.compareTo(loader.Username) == 0) {
            s5 = String.valueOf("[COLOR=red]").concat(String.valueOf(s));
        }
        else {
            s5 = String.valueOf("[COLOR=blue]").concat(String.valueOf(s));
        }
        String s6 = s5;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2);
        while (stringTokenizer.hasMoreTokens()) {
            String s7 = stringTokenizer.nextToken();
            if (s7.compareTo(":)") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img11.gif]"));
            }
            else if (s7.compareTo(";)") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img12.gif]"));
            }
            else if (s7.toLowerCase().compareTo(":p") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img13.gif]"));
            }
            else if (s7.compareTo(":(") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img14.gif]"));
            }
            else if (s7.compareTo("8)") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img15.gif]"));
            }
            else if (s7.compareTo("!!") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img16.gif]"));
            }
            else if (s7.compareTo("])") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img17.gif]"));
            }
            else if (s7.compareTo(":]") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img18.gif]"));
            }
            else if (s7.toLowerCase().compareTo(":s") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img19.gif]"));
            }
            else if (s7.toLowerCase().compareTo(":o") == 0) {
                s7 = String.valueOf(String.valueOf("[align=middle][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img20.gif]"));
            }
            if (JCString.parse(this.OutputList, s6).getWidth(this.OutputList.getViewport(), this.OutputList.getFont()) + JCString.parse(this.OutputList, String.valueOf(" ").concat(String.valueOf(s7))).getWidth(this.OutputList.getViewport(), this.OutputList.getFont()) > this.OutputList.getSize().width - 70) {
                s5 = String.valueOf(String.valueOf(s5).concat(String.valueOf("[NEWLINE] "))).concat(String.valueOf(s7));
                s6 = String.valueOf(" ").concat(String.valueOf(s7));
            }
            else {
                s5 = String.valueOf(String.valueOf(s5).concat(String.valueOf(" "))).concat(String.valueOf(s7));
                s6 = String.valueOf(String.valueOf(s6).concat(String.valueOf(" "))).concat(String.valueOf(s7));
            }
        }
        final JCString parse = JCString.parse(this.OutputList, s5);
        this.OutputList.setBatched(true);
        while (this.OutputList.countItems() >= 250) {
            this.OutputList.deleteItem(0);
        }
        this.OutputList.addItem(parse);
        this.OutputList.setBatched(false);
        this.OutputList.makeVisible(this.OutputList.countItems() - 1);
    }
    
    void AppendTranscript(final String s, String s2) {
        String s3 = "";
        String s4 = "";
        final String substring = s2.substring(0, s2.indexOf(" "));
        s2 = s2.substring(s2.indexOf(" "), s2.length());
        if (substring.compareTo("1") == 0) {
            s3 = " > ";
            s4 = "";
        }
        else if (substring.compareTo("2") == 0) {
            s3 = " .o0{ ";
            s4 = " }";
        }
        else if (substring.compareTo("3") == 0) {
            s3 = " ** ";
            s4 = " **";
        }
        else if (substring.compareTo("4") == 0) {
            s3 = " QUIZ: ";
            s4 = "";
        }
        else if (substring.compareTo("5") == 0) {
            return;
        }
        s2 = String.valueOf(String.valueOf(s3).concat(String.valueOf(s2))).concat(String.valueOf(s4));
        String s5;
        if (s.compareTo("") == 0) {
            s5 = "[SYSTEM]";
        }
        else if (s.compareTo(loader.Username) == 0) {
            s5 = String.valueOf("*").concat(String.valueOf(s));
        }
        else {
            s5 = String.valueOf(" ").concat(String.valueOf(s));
        }
        this.OutputText.append(String.valueOf(String.valueOf(s5).concat(String.valueOf(s2))).concat(String.valueOf("\n")));
        this.OutputText.select(this.OutputText.getText().length(), this.OutputText.getText().length());
    }
    
    void DoChatEvent() {
        if (this.UserList.getSelectedIndex() == -999) {
            loader.StatusTxt.setText("  Select User First!");
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to chat with from the user list.");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        for (int i = 0; i < 20; ++i) {
            if (loader.myChat[i].getTitle().compareTo(trim) == 0) {
                loader.myChat[i].toFront();
                return;
            }
        }
        for (int j = 0; j < 20; ++j) {
            if (loader.myChat[j].getTitle().compareTo("") == 0) {
                loader.myChat[j].setTitle(trim);
                loader.myChat[j].setVisible(true);
                return;
            }
        }
        this.ShowMessageBox("Max Chats Error", "Too Many Chat Windows", "You have reached the max number of open chat windows.");
        loader.StatusTxt.setText("  Too Many Chat Windows! (20)");
    }
    
    void DoProfileEvent() {
        if (this.UserList.getSelectedIndex() == -999) {
            loader.StatusTxt.setText("  Select A User!");
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user from the user list to view a profile.");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        SendLine(String.valueOf("200 100 ").concat(String.valueOf(trim)));
        this.InputTxt.requestFocus();
    }
    
    void DoAction(String replace) {
        if (!loader.PermitInput) {
            loader.StatusTxt.setText("  Allow Others Time To Speak!");
            this.getToolkit().beep();
            return;
        }
        loader.PermitInput = false;
        if (loader.UserFlag.compareTo("WIZOP") != 0) {
            replace = replace.replace('[', '{');
        }
        SendLine(String.valueOf("450 100 3 ").concat(String.valueOf(replace)));
        this.InputTxt.requestFocus();
        loader.StatusTxt.setText("  Action Sent to Room");
    }
    
    public static void ShowNewUrl(final String s) {
        try {
            ShowNewStatus(String.valueOf("Opening ").concat(String.valueOf(s)));
            loader.StatusTxt.setText("  Opening Web Page..");
            loader.myAppletContext.showDocument(new URL(s.trim()), "_blank");
        }
        catch (Exception ex) {}
    }
    
    public static void ShowNewStatus(final String s) {
        try {
            loader.myAppletContext.showStatus(s);
        }
        catch (Exception ex) {}
    }
    
    public void ShowMessageBox(final String title, final String text, final String text2) {
        loader.myMessageBox.setVisible(false);
        loader.myMessageBox.setTitle(title);
        loader.myMessageBox.PromptTxt.setText(text);
        loader.myMessageBox.DetailsTxt.setText(text2);
        loader.myMessageBox.doLayout();
        loader.myMessageBox.setVisible(true);
        loader.myMessageBox.pack();
        this.getToolkit().beep();
        loader.myMessageBox.setLocation((this.screenSize.width - loader.myMessageBox.getSize().width) / 2, (this.screenSize.height - 350) / 2);
    }
    
    static void SendLine(String replace) {
        try {
            replace = replace.replace('&', '+');
            final String concat = String.valueOf(replace.trim()).concat(String.valueOf(" &&&\r\n"));
            if (loader.Debug) {
                System.err.println(String.valueOf("<OUT> ").concat(String.valueOf(concat)));
            }
            loader.sout.println(concat);
        }
        catch (Exception ex) {
            if (loader.Debug) {
                System.err.println("EXCPTN SendLine ");
            }
        }
    }
    
    void SendMessage() {
        if (!loader.PermitInput) {
            loader.StatusTxt.setText("  Allow Others Time To Speak!");
            this.getToolkit().beep();
            return;
        }
        final String string = Integer.toString(this.MsgType.getSelectedIndex() + 1);
        String s = this.InputTxt.getText().trim();
        if (s.compareTo("") == 0) {
            loader.StatusTxt.setText("  Cannot Send Empty Message!");
            this.getToolkit().beep();
            return;
        }
        if (s.length() > 400) {
            loader.StatusTxt.setText("  Message Too Long! (400)");
            this.getToolkit().beep();
            return;
        }
        loader.PermitInput = false;
        if (loader.UserFlag.compareTo("WIZOP") != 0) {
            s = s.replace('[', '{');
        }
        SendLine(String.valueOf(String.valueOf(String.valueOf("450 100 ").concat(String.valueOf(string))).concat(String.valueOf(" "))).concat(String.valueOf(s)));
        this.MsgType.select(0);
        this.InputTxt.setText("");
        this.InputTxt.requestFocus();
        loader.StatusTxt.setText("  Message Sent to Room");
    }
    
    private void _$3507() throws Exception {
        this.setForeground(loader.primaryFgColor);
        this.setBackground(loader.primaryBgColor);
        this.setSize(new Dimension(500, 500));
        this.FloatLabel.setForeground(Color.yellow);
        this.FloatLabel.setFont(new Font("Helvetica", 1, 13));
        this.FloatLabel.setAlignment(1);
        this.FloatLabel.setText("Chat Room is Floating, Do Not Close This Page!");
        this.AppletSPanel.setLayout(this.flowLayout4);
        this.AppletNPanel.setLayout(this.borderLayout5);
        this.AppletEPanel.setLayout(this.flowLayout2);
        this.AppletWPanel.setLayout(this.flowLayout1);
        this.ContentPanel.setForeground(loader.secondaryFgColor);
        this.ContentPanel.setBackground(loader.secondaryBgColor);
        this.flowLayout1.setVgap(2);
        this.flowLayout2.setVgap(2);
        this.flowLayout4.setVgap(2);
        this.CopyrightTxt.setFont(new Font("Dialog", 0, 11));
        this.CopyrightTxt.setAlignment(1);
        this.CopyrightTxt.setText("©2010 Chat-Forum.com, ASCII Software Ltd");
        this.CopyrightTxt.addMouseListener(new 1());
        this.borderLayout4.setVgap(10);
        this.borderLayout4.setHgap(10);
        this.flowLayout8.setVgap(100);
        this.gridLayout1.setRows(6);
        this.gridLayout1.setHgap(10);
        this.gridLayout1.setColumns(2);
        this.gridLayout1.setVgap(5);
        this.label1.setFont(new Font("Helvetica", 1, 14));
        this.label1.setText("Chat Room Login:");
        this.PasswordInp.setSelectedBackground(Color.blue);
        this.PasswordInp.setHighlightThickness(1);
        this.PasswordInp.setForeground(loader.inputFgColor);
        this.PasswordInp.setBackground(loader.inputBgColor);
        this.PasswordInp.setColumns(16);
        this.PasswordInp.setFont(new Font("Helvetica", 0, 12));
        this.PasswordInp.setHighlightColor(new Color(0, 34, 170));
        this.PasswordInp.setMaximumLength(15);
        this.PasswordInp.setEchoCharString("*");
        this.label3.setForeground(new Color(0, 34, 170));
        this.label3.setFont(new Font("Helvetica", 1, 13));
        this.label3.setText("Password (Admin & Staff)");
        this.UsernameInp.setSelectedBackground(Color.blue);
        this.UsernameInp.setHighlightThickness(1);
        this.UsernameInp.setForeground(loader.inputFgColor);
        this.UsernameInp.setBackground(loader.inputBgColor);
        this.UsernameInp.setColumns(16);
        this.UsernameInp.setFont(new Font("Helvetica", 0, 12));
        this.UsernameInp.setHighlightColor(new Color(0, 34, 170));
        this.UsernameInp.setMaximumLength(15);
        this.UsernameInp.addActionListener(new 2());
        this.ConnectBtn.setBackground(loader.buttonBgColor);
        this.ConnectBtn.setForeground(loader.buttonFgColor);
        this.ConnectBtn.setFont(new Font("Helvetica", 1, 14));
        this.ConnectBtn.setLabel(" Enter Chat Room ");
        this.ConnectBtn.addMouseListener(new 3());
        this.ConnectBtn.addActionListener(new loader_ConnectBtn_actionAdapter(this));
        this.label8.setForeground(new Color(0, 34, 170));
        this.label8.setFont(new Font("Helvetica", 1, 13));
        this.label8.setText("Username (Max 15 Chars)");
        this.borderLayout11.setHgap(2);
        this.borderLayout11.setVgap(2);
        this.gridLayout2.setHgap(3);
        this.gridLayout2.setRows(2);
        this.gridLayout2.setColumns(2);
        this.gridLayout2.setVgap(2);
        this.ChatBtn.setFont(new Font("Helvetica", 1, 12));
        this.ChatBtn.setForeground(loader.buttonFgColor);
        this.ChatBtn.setBackground(loader.buttonBgColor);
        this.ChatBtn.setLabel("  Chat  ");
        this.ChatBtn.addMouseListener(new 4());
        this.ChatBtn.addActionListener(new loader_ChatBtn_actionAdapter(this));
        this.BlockBtn.setFont(new Font("Helvetica", 1, 12));
        this.BlockBtn.setForeground(loader.buttonFgColor);
        this.BlockBtn.setBackground(loader.buttonBgColor);
        this.BlockBtn.setLabel("  Block  ");
        this.BlockBtn.addMouseListener(new 5());
        this.BlockBtn.addActionListener(new loader_BlockBtn_actionAdapter(this));
        this.menuItem1.setLabel(this.Action1);
        this.menuItem1.addActionListener(new 6());
        this.menuItem2.setLabel(this.Action2);
        this.menuItem2.addActionListener(new 7());
        this.menuItem3.setLabel(this.Action3);
        this.menuItem3.addActionListener(new 8());
        this.ProfileInp.setSelectedBackground(Color.blue);
        this.ProfileInp.setHighlightThickness(1);
        this.ProfileInp.setForeground(loader.inputFgColor);
        this.ProfileInp.setBackground(loader.inputBgColor);
        this.ProfileInp.setColumns(16);
        this.ProfileInp.setFont(new Font("Helvetica", 0, 12));
        this.ProfileInp.setHighlightColor(new Color(0, 34, 170));
        this.ProfileInp.setMaximumLength(80);
        this.ProfileBtn.setFont(new Font("Helvetica", 1, 12));
        this.ProfileBtn.setForeground(loader.buttonFgColor);
        this.ProfileBtn.setBackground(loader.buttonBgColor);
        this.ProfileBtn.setLabel("Profile ");
        this.ProfileBtn.addMouseListener(new 9());
        this.ProfileBtn.addActionListener(new loader_ProfileBtn_actionAdapter(this));
        this.EjectBtn.setFont(new Font("Helvetica", 1, 12));
        this.EjectBtn.setEnabled(false);
        this.EjectBtn.setForeground(loader.buttonFgColor);
        this.EjectBtn.setBackground(loader.buttonBgColor);
        this.EjectBtn.setLabel(" Eject ");
        this.EjectBtn.addMouseListener(new 10());
        this.EjectBtn.addActionListener(new loader_EjectBtn_actionAdapter(this));
        this.menuItem4.setLabel(this.Action4);
        this.menuItem4.addActionListener(new 11());
        this.menuItem5.setLabel(this.Action5);
        this.menuItem5.addActionListener(new 12());
        this.menuItem6.setLabel(this.Action6);
        this.menuItem6.addActionListener(new 13());
        this.menuItem7.setLabel(this.Action7);
        this.menuItem7.addActionListener(new 14());
        this.menuItem8.setLabel(this.Action8);
        this.menuItem8.addActionListener(new 15());
        this.menuItem9.setLabel(this.Action9);
        this.menuItem9.addActionListener(new 16());
        this.menuItem10.setLabel(this.Action10);
        this.menuItem10.addActionListener(new 17());
        this.menuItem11.setLabel("Clear Room Text ");
        this.menuItem11.addActionListener(new 18());
        this.menuItem12.setLabel("Smaller Text");
        this.menuItem12.addActionListener(new 19());
        this.menuItem13.setLabel("Normal Text");
        this.menuItem13.addActionListener(new 20());
        this.menuItem14.setLabel("Larger Text");
        this.menuItem14.addActionListener(new 21());
        this.menuItem15.setLabel("Show Quiz Controls.. ");
        this.menuItem15.addActionListener(new 22());
        this.menuItem16.setLabel("Warn Selected User ");
        this.menuItem16.addActionListener(new 23());
        this.menuItem17.setLabel("Silence Selected User ");
        this.menuItem17.addActionListener(new 24());
        this.menuItem18.setLabel("Unsilence Selected User ");
        this.menuItem18.addActionListener(new 25());
        this.menuItem19.setLabel("Eject Selected User ");
        this.menuItem19.addActionListener(new 26());
        this.menuItem20.setLabel("Open Room Floor ");
        this.menuItem20.addActionListener(new 27());
        this.menuItem21.setLabel("Close Room Floor ");
        this.menuItem21.addActionListener(new 28());
        this.menuItem22.setLabel("Set Room Welcome Message ...");
        this.menuItem22.addActionListener(new 29());
        this.menuItem23.setLabel("Add & Remove Staff Users...");
        this.menuItem23.addActionListener(new 30());
        this.menuItem24.setLabel("Send Web Page to Room...");
        this.OutputListPanel.setLayout(this.borderLayout10);
        this.OutputList.setFont(new Font("SansSerif", 1, 12));
        this.OutputList.setScrollbarDisplay(3);
        this.OutputList.setSelectedBackground(Color.white);
        this.OutputList.setRowHeight(-998);
        this.OutputList.setSelectedForeground(Color.black);
        this.OutputText.setText("CHAT ROOM TRANSCRIPT\n\n");
        this.OutputText.setEditable(false);
        this.OutputText.setBackground(Color.white);
        this.OutputText.setFont(new Font("SansSerif", 1, 12));
        this.OutputModeMenu.setLabel("Transcript Mode ");
        this.QuizStatus.setBackground(loader.buttonBgColor);
        this.QuizStatus.setForeground(Color.red);
        this.QuizStatus.setFont(new Font("Helvetica", 1, 11));
        this.QuizStatus.setText("QUIZ SESSION");
        this.gridLayout3.setRows(3);
        this.gridLayout3.setColumns(1);
        this.gridLayout3.setHgap(10);
        this.gridLayout3.setVgap(3);
        this.SilencedStatus.setBackground(Color.red);
        this.SilencedStatus.setVisible(false);
        this.SilencedStatus.setForeground(Color.white);
        this.SilencedStatus.setFont(new Font("Helvetica", 1, 11));
        this.SilencedStatus.setText("YOU ARE SILENCED");
        this.AdminStatus.setBackground(loader.buttonBgColor);
        this.AdminStatus.setForeground(loader.buttonFgColor);
        this.AdminStatus.setFont(new Font("Helvetica", 1, 11));
        this.AdminStatus.setText("NORMAL USER");
        this.menu1.setLabel("Background ");
        this.menuItem25.setLabel("Default ");
        this.menuItem25.addActionListener(new 31());
        this.menuItem26.setLabel("Pastel Blue ");
        this.menuItem26.addActionListener(new 32());
        this.menuItem27.setLabel("White ");
        this.menuItem27.addActionListener(new 33());
        this.menuItem28.setLabel("Cream ");
        this.menuItem28.addActionListener(new 34());
        this.menuItem29.setLabel("Pastel Pink");
        this.menuItem29.addActionListener(new 35());
        this.menuItem30.setLabel("Pastel Green ");
        this.menuItem30.addActionListener(new 36());
        this.menuItem31.setLabel("Light Grey ");
        this.menuItem31.addActionListener(new 37());
        this.AdminStatus.setAlignment(1);
        this.SilencedStatus.setAlignment(1);
        this.QuizStatus.setAlignment(1);
        this.QuizStatusPanel.setLayout(this.gridLayout3);
        this.OutputModeMenu.addActionListener(new 38());
        this.OutputTextPanel.setLayout(this.borderLayout12);
        this.OutputList.addActionListener(new 39());
        this.menuItem24.addActionListener(new 40());
        this.label9.setForeground(new Color(0, 34, 170));
        this.label9.setFont(new Font("Helvetica", 1, 13));
        this.label9.setText("Profile (Max 80 Chars)");
        this.panel1.setLayout(this.gridLayout2);
        this.UserBtnPanel.setLayout(this.borderLayout11);
        this.LoginPanel.setLayout(this.gridLayout1);
        this.flowLayout8.setHgap(50);
        loader.PromptTxt.setForeground(new Color(0, 34, 170));
        loader.PromptTxt.setFont(new Font("Dialog", 0, 11));
        loader.PromptTxt.setAlignment(2);
        loader.PromptTxt.setText("Hover Over Control For Help  ");
        this.Card1.setLayout(this.flowLayout8);
        this.borderLayout7.setHgap(3);
        this.borderLayout7.setVgap(5);
        this.flowLayout5.setHgap(2);
        this.flowLayout5.setAlignment(0);
        this.flowLayout5.setVgap(0);
        this.TextBtn.setForeground(loader.buttonFgColor);
        this.TextBtn.setBackground(loader.buttonBgColor);
        this.TextBtn.setFont(new Font("Helvetica", 1, 12));
        this.TextBtn.setLabel("Text");
        this.TextBtn.addMouseListener(new 41());
        this.TextBtn.addActionListener(new loader_TextBtn_actionAdapter(this));
        this.UserList.setFont(new Font("SansSerif", 1, 12));
        this.UserList.setSelectedBackground(new Color(0, 34, 170));
        this.UserList.addActionListener(new 42());
        this.SendBtn.setBackground(loader.buttonBgColor);
        this.SendBtn.setForeground(loader.buttonFgColor);
        this.SendBtn.setFont(new Font("Helvetica", 1, 12));
        this.SendBtn.setLabel(" SEND ");
        this.SendBtn.addMouseListener(new 43());
        this.SendBtn.addActionListener(new loader_SendBtn_actionAdapter(this));
        this.MsgType.setFont(new Font("Helvetica", 1, 12));
        this.MsgType.addMouseListener(new 44());
        this.MsgType.addItemListener(new 45());
        this.InputTxt.setForeground(loader.inputFgColor);
        this.InputTxt.setBackground(loader.inputBgColor);
        this.InputTxt.setFont(new Font("Helvetica", 0, 12));
        this.InputTxt.setHighlightColor(new Color(0, 34, 170));
        this.InputTxt.setMaximumLength(400);
        this.InputTxt.setColumns(40);
        this.InputTxt.setCursorPosition(0);
        this.InputTxt.addActionListener(new 46());
        this.InputTxt.setSelectedBackground(Color.blue);
        this.InputTxt.setHighlightThickness(1);
        this.UsernameTxt.setForeground(new Color(0, 34, 170));
        this.UsernameTxt.setFont(new Font("Helvetica", 1, 13));
        this.UsernameTxt.setText("Username");
        this.flowLayout6.setVgap(2);
        this.flowLayout6.setHgap(3);
        this.flowLayout7.setVgap(2);
        this.FloatBtn.setBackground(loader.buttonBgColor);
        this.FloatBtn.setForeground(loader.buttonFgColor);
        this.FloatBtn.setFont(new Font("Helvetica", 1, 12));
        this.FloatBtn.setLabel("Float");
        this.FloatBtn.addMouseListener(new 47());
        this.FloatBtn.addActionListener(new loader_FloatBtn_actionAdapter(this));
        this.LeaveBtn.setForeground(loader.buttonFgColor);
        this.LeaveBtn.setBackground(loader.buttonBgColor);
        this.LeaveBtn.setFont(new Font("Helvetica", 1, 12));
        this.LeaveBtn.setLabel("Log Out");
        this.LeaveBtn.addMouseListener(new 48());
        this.LeaveBtn.addActionListener(new loader_LeaveBtn_actionAdapter(this));
        this.ActionsBtn.setBackground(loader.buttonBgColor);
        this.ActionsBtn.setForeground(loader.buttonFgColor);
        this.ActionsBtn.setFont(new Font("Helvetica", 1, 12));
        this.ActionsBtn.setLabel("Actions");
        this.ActionsBtn.addMouseListener(new 49());
        this.ActionsBtn.addActionListener(new loader_ActionsBtn_actionAdapter(this));
        this.OptionsBtn.setBackground(loader.buttonBgColor);
        this.OptionsBtn.setForeground(loader.buttonFgColor);
        this.OptionsBtn.setFont(new Font("Helvetica", 1, 12));
        this.OptionsBtn.setLabel("Options");
        this.OptionsBtn.addMouseListener(new 50());
        this.OptionsBtn.addActionListener(new loader_OptionsBtn_actionAdapter(this));
        this.StaffBtn.setEnabled(false);
        this.StaffBtn.setForeground(loader.buttonFgColor);
        this.StaffBtn.setBackground(loader.buttonBgColor);
        this.StaffBtn.setFont(new Font("Helvetica", 1, 12));
        this.StaffBtn.setLabel("Staff");
        this.StaffBtn.addMouseListener(new 51());
        this.StaffBtn.addActionListener(new loader_StaffBtn_actionAdapter(this));
        this.AdminBtn.setEnabled(false);
        this.AdminBtn.setForeground(loader.buttonFgColor);
        this.AdminBtn.setBackground(loader.buttonBgColor);
        this.AdminBtn.setFont(new Font("Helvetica", 1, 12));
        this.AdminBtn.setLabel("Admin");
        this.AdminBtn.addMouseListener(new 52());
        this.AdminBtn.addActionListener(new loader_AdminBtn_actionAdapter(this));
        this.HelpBtn.setForeground(loader.buttonFgColor);
        this.HelpBtn.setBackground(loader.buttonBgColor);
        this.HelpBtn.setFont(new Font("Helvetica", 1, 12));
        this.HelpBtn.setLabel(" ? ");
        this.HelpBtn.addMouseListener(new 53());
        this.HelpBtn.addActionListener(new loader_HelpBtn_actionAdapter(this));
        this.flowLayout7.setHgap(3);
        this.RPadding.setLayout(this.flowLayout7);
        this.LPadding.setLayout(this.flowLayout6);
        this.OutputPanel.setLayout(this.cardLayout2);
        this.InputPanel.setLayout(this.borderLayout9);
        this.UserPanel.setLayout(this.borderLayout8);
        this.MainBtnPanel.setLayout(this.flowLayout5);
        this.Card2.setLayout(this.borderLayout7);
        loader.StatusTxt.setFont(new Font("Dialog", 1, 11));
        loader.StatusTxt.setText("  To Chat Please Login Above                    ");
        this.CardPanel.setLayout(this.cardLayout1);
        this.StatusPanel.setLayout(this.gridLayout4);
        this.BannerPanel.setLayout(this.flowLayout3);
        this.TitleTxt.setFont(new Font("Helvetica", 1, 16));
        this.TitlePanel.setLayout(this.borderLayout4);
        this.flowLayout4.setHgap(2);
        this.flowLayout2.setHgap(2);
        this.flowLayout1.setHgap(2);
        this.ContentPanel.setLayout(this.borderLayout3);
        this.AppletBasePanel.setLayout(this.borderLayout2);
        this.setLayout(this.borderLayout1);
        this.setSize(500, 500);
        this.add(this.AppletWPanel, "West");
        this.add(this.AppletEPanel, "East");
        this.add(this.AppletSPanel, "South");
        this.AppletSPanel.add(this.CopyrightTxt, null);
        this.add(this.AppletNPanel, "North");
        this.AppletNPanel.add(this.TitlePanel, "Center");
        this.TitlePanel.add(this.TitleTxt, "Center");
        this.add(this.AppletBasePanel, "Center");
        this.AppletBasePanel.add(this.ContentPanel, "Center");
        this.ContentPanel.add(this.BannerPanel, "North");
        this.ContentPanel.add(this.StatusPanel, "South");
        this.StatusPanel.add(loader.StatusTxt, null);
        this.StatusPanel.add(loader.PromptTxt, null);
        this.ContentPanel.add(this.CardPanel, "Center");
        this.CardPanel.add(this.Card1, "panel1");
        this.Card1.add(this.LoginPanel, null);
        this.LoginPanel.add(this.label1, null);
        this.LoginPanel.add(this.label7, null);
        this.LoginPanel.add(this.label8, null);
        this.LoginPanel.add(this.UsernameInp, null);
        this.LoginPanel.add(this.label9, null);
        this.LoginPanel.add(this.ProfileInp, null);
        this.LoginPanel.add(this.label3, null);
        this.LoginPanel.add(this.PasswordInp, null);
        this.LoginPanel.add(this.label4, null);
        this.LoginPanel.add(this.label5, null);
        this.LoginPanel.add(this.label6, null);
        this.LoginPanel.add(this.ConnectBtn, null);
        this.CardPanel.add(this.Card2, "panel2");
        this.Card2.add(this.MainBtnPanel, "North");
        this.MainBtnPanel.add(this.LeaveBtn, null);
        this.MainBtnPanel.add(this.FloatBtn, null);
        this.MainBtnPanel.add(this.ActionsBtn, null);
        this.MainBtnPanel.add(this.TextBtn, null);
        this.MainBtnPanel.add(this.OptionsBtn, null);
        this.MainBtnPanel.add(this.StaffBtn, null);
        this.MainBtnPanel.add(this.AdminBtn, null);
        this.MainBtnPanel.add(this.HelpBtn, null);
        this.Card2.add(this.UserPanel, "West");
        this.UserPanel.add(this.UserList, "Center");
        this.UserPanel.add(this.UserBtnPanel, "South");
        this.UserBtnPanel.add(this.panel1, "North");
        this.panel1.add(this.ChatBtn, null);
        this.panel1.add(this.ProfileBtn, null);
        this.panel1.add(this.BlockBtn, null);
        this.panel1.add(this.EjectBtn, null);
        this.UserBtnPanel.add(this.QuizStatusPanel, "South");
        this.QuizStatusPanel.add(this.AdminStatus, null);
        this.QuizStatusPanel.add(this.QuizStatus, null);
        this.QuizStatusPanel.add(this.SilencedStatus, null);
        this.Card2.add(this.InputPanel, "South");
        this.InputPanel.add(this.SendBtn, "East");
        this.InputPanel.add(this.MsgType, "West");
        this.InputPanel.add(this.InputTxt, "Center");
        this.InputPanel.add(this.UsernameTxt, "North");
        this.Card2.add(this.OutputPanel, "Center");
        this.OutputPanel.add(this.OutputListPanel, "panel2");
        this.OutputListPanel.add(this.OutputList, "Center");
        this.OutputPanel.add(this.OutputTextPanel, "panel2");
        this.OutputTextPanel.add(this.OutputText, "Center");
        this.OutputList.setBackground(loader.outputBgColor);
        this.ContentPanel.add(this.RPadding, "East");
        this.ContentPanel.add(this.LPadding, "West");
        this.actionsMenu.add(this.menuItem1);
        this.actionsMenu.add(this.menuItem2);
        this.actionsMenu.add(this.menuItem3);
        this.actionsMenu.add(this.menuItem4);
        this.actionsMenu.add(this.menuItem5);
        this.actionsMenu.add(this.menuItem6);
        this.actionsMenu.add(this.menuItem7);
        this.actionsMenu.add(this.menuItem8);
        this.actionsMenu.add(this.menuItem9);
        this.actionsMenu.add(this.menuItem10);
        this.ContentPanel.add(this.actionsMenu);
        this.ContentPanel.add(this.textMenu);
        this.ContentPanel.add(this.staffMenu);
        this.ContentPanel.add(this.adminMenu);
        this.textMenu.add(this.menuItem11);
        this.textMenu.addSeparator();
        this.textMenu.add(this.menuItem12);
        this.textMenu.add(this.menuItem13);
        this.textMenu.add(this.menuItem14);
        this.textMenu.addSeparator();
        this.textMenu.add(this.menu1);
        this.textMenu.addSeparator();
        this.textMenu.add(this.OutputModeMenu);
        this.staffMenu.add(this.menuItem15);
        this.staffMenu.addSeparator();
        this.staffMenu.add(this.menuItem16);
        this.staffMenu.add(this.menuItem17);
        this.staffMenu.add(this.menuItem18);
        this.staffMenu.addSeparator();
        this.staffMenu.add(this.menuItem19);
        this.staffMenu.addSeparator();
        this.staffMenu.add(this.menuItem20);
        this.staffMenu.add(this.menuItem21);
        this.adminMenu.add(this.menuItem22);
        this.adminMenu.addSeparator();
        this.adminMenu.add(this.menuItem23);
        this.adminMenu.addSeparator();
        this.adminMenu.add(this.menuItem24);
        this.menu1.add(this.menuItem25);
        this.menu1.addSeparator();
        this.menu1.add(this.menuItem31);
        this.menu1.add(this.menuItem26);
        this.menu1.add(this.menuItem27);
        this.menu1.add(this.menuItem28);
        this.menu1.add(this.menuItem29);
        this.menu1.add(this.menuItem30);
    }
    
    public void start() {
        this.Connected = false;
        if (this.myThread == null) {
            this.myThread = new Thread(this);
        }
        this.myThread.start();
    }
    
    public void stop() {
        this.PrevUsername = loader.Username;
        this.sin = null;
        this.GoDisconnect();
        if (this.myThread != null) {
            this.myThread.stop();
            this.myThread = null;
        }
    }
    
    public void run() {
        final boolean b = true;
        if (this.AutoConnect) {
            this.ConnectBtn_actionPerformed(null);
        }
        while (b) {
            this.OutputList.makeVisible(this.OutputList.countItems() - 1);
            this.OutputList.update(this.OutputList.getGraphics());
            this.OutputList.repaint();
            try {
                Thread.sleep(this.loopDelay);
            }
            catch (InterruptedException ex) {
                System.err.println(ex);
            }
            if (this.Connected) {
                String line = "";
                try {
                    if (this.sin != null) {
                        line = this.sin.readLine();
                    }
                }
                catch (IOException ex2) {
                    this.GoDisconnect();
                    System.err.println("SOCKET ABORT CLOSED");
                }
                if (line.trim().length() <= 0) {
                    continue;
                }
                this.ParseLine(line.trim());
            }
        }
    }
    
    void GoConnect() {
        try {
            loader.StatusTxt.setText("  Connecting..");
            loader.StatusTxt.paint(loader.StatusTxt.getGraphics());
            this.mysocket = new Socket(this.myhost, this.myport);
            if (this.mysocket != null) {
                loader.sout = new PrintWriter(this.mysocket.getOutputStream(), true);
                this.sin = new BufferedReader(new InputStreamReader(this.mysocket.getInputStream()));
                this.Connected = true;
                loader.StatusTxt.setText("  Logging In  -");
                loader.StatusTxt.paint(loader.StatusTxt.getGraphics());
                loader.Username = this.UsernameInp.getText().trim();
                this.Password = this.PasswordInp.getText().trim();
                if (this.Password.compareTo("") == 0) {
                    this.Password = "NULL";
                }
                this.Profile = this.ProfileInp.getText().trim();
                if (this.Profile.compareTo("") == 0) {
                    this.Profile = "None";
                }
            }
            else {
                this.Connected = false;
                this.UIoffline();
                loader.StatusTxt.setText("  Connection Failed, Try Again");
                this.ShowMessageBox("Connection Error", "Connection Failed, Try Again.", "Connection Failed - Socket Error: NULL Socket Error");
            }
        }
        catch (IOException ex) {
            this.Connected = false;
            this.UIoffline();
            loader.StatusTxt.setText("  Connection Failed, Try Again");
            this.ShowMessageBox("Connection Error", "Connection Failed, Try Again.", String.valueOf("Connection Failed - Socket Error: ").concat(String.valueOf(ex.getMessage())));
        }
    }
    
    void GoDisconnect() {
        for (int i = 0; i < 20; ++i) {
            loader.myChat[i].setVisible(false);
            loader.myChat[i].ResetChat();
        }
        this.myOptions.setVisible(false);
        this.Connected = false;
        this.UIoffline();
        loader.StatusTxt.setText("  Not Connected");
        try {
            if (this.mysocket != null) {
                this.mysocket.close();
                this.mysocket = null;
            }
        }
        catch (IOException ex) {}
    }
    
    void UIoffline() {
        if (this.FloatBtn.getLabel().trim().compareTo("Float") != 0) {
            this.RoomFrame.removeAll();
            this.AppletBasePanel.removeAll();
            this.AppletBasePanel.add(this.ContentPanel, "Center");
            this.AppletBasePanel.doLayout();
            this.ContentPanel.doLayout();
            this.RoomFrame.setVisible(false);
            this.FloatBtn.setLabel(" Float ");
            this.validateTree();
        }
        this.myScore.setVisible(false);
        this.myScore.setTitle("Quiz Controls");
        this.myScore.StatusTxt.setText("Click 'Start Quiz' To Get Started... ");
        this.myScore.StartBtn.setEnabled(false);
        this.myScore.SendQBtn.setEnabled(false);
        this.myScore.SendABtn.setEnabled(false);
        this.myScore.SendBtn.setEnabled(false);
        this.myScore.ResetAllBtn.setEnabled(false);
        this.myScore.ScoreList.setEnabled(false);
        this.myScore.ScoreList.removeAll();
        this.myScore.EndBtn.setEnabled(false);
        this.QuizStatus.setVisible(false);
        this.mySetStaff.setVisible(false);
        this.mySetWelcome.setVisible(false);
        this.mySendUrl.setVisible(false);
        this.UsernameInp.setHighlightColor(Color.blue);
        if (!this.UsernameLocked) {
            this.UsernameInp.setEnabled(true);
        }
        if (!this.PasswordLocked) {
            this.PasswordInp.setEnabled(true);
        }
        if (!this.ProfileLocked) {
            this.ProfileInp.setEnabled(true);
        }
        this.ConnectBtn.setEnabled(true);
        this.UserList.clear();
        this.OutputList.clear();
        this.EjectBtn.setEnabled(false);
        this.StaffBtn.setEnabled(false);
        this.AdminBtn.setEnabled(false);
        this.cardLayout1.first(this.CardPanel);
        if (!this.UsernameLocked) {
            this.UsernameInp.requestFocus();
        }
    }
    
    void UIbusy() {
        this.ConnectBtn.setEnabled(false);
        this.ConnectBtn.paint(this.ConnectBtn.getGraphics());
        this.UsernameInp.setEnabled(false);
        this.PasswordInp.setEnabled(false);
        this.ProfileInp.setEnabled(false);
    }
    
    void UIonline() {
        this.UsernameInp.setEnabled(false);
        this.PasswordInp.setEnabled(false);
        this.ProfileInp.setEnabled(false);
        this.ConnectBtn.setEnabled(false);
        this.myScore.StartBtn.setEnabled(true);
        loader.StatusTxt.setText("  Welcome to the Chat Room");
        this.cardLayout1.last(this.CardPanel);
        this.OutputList.clear();
        this.InputTxt.requestFocus();
    }
    
    boolean VerifyLogin() {
        final String trim = this.UsernameInp.getText().trim();
        final String trim2 = this.PasswordInp.getText().trim();
        final String replace = trim.replace(' ', '_').replace('\r', '?');
        replace.replace('\n', '?');
        final String replace2 = replace.replace('\t', '?');
        replace2.replace('\f', '?');
        final String replace3 = trim2.replace(' ', '_');
        this.UsernameInp.setText(replace2);
        this.PasswordInp.setText(replace3);
        this.repaint();
        if (replace2.length() < 3 || replace2.length() > 15) {
            this.UsernameInp.setHighlightColor(Color.red);
            this.UsernameInp.requestFocus();
            this.ShowMessageBox("Invalid Username", "Username Invalid Length", "Usernames must be 3 to 15 alpha-numeric chars, NO spaces.");
            return false;
        }
        this.y = this.j * this.UsernameInp.getText().length();
        return true;
    }
    
    void AddUser(final String s) {
        if (this.myScore.EndBtn.isEnabled()) {
            for (int i = 0; i < this.myScore.ScoreList.getItemCount(); ++i) {
                try {
                    final String item = this.myScore.ScoreList.getItem(i);
                    if (item.substring(0, item.indexOf("=")).compareTo(s) == 0) {
                        this.myScore.ScoreList.remove(i);
                    }
                }
                catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            this.myScore.ScoreList.addItem(String.valueOf(s).concat(String.valueOf("=0")));
        }
        final JCString parse = JCString.parse(this, String.valueOf(String.valueOf(String.valueOf("[ALIGN=MIDDLE][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img10.gif] "))).concat(String.valueOf(s)));
        for (int j = 0; j < this.UserList.countItems(); ++j) {
            try {
                if (((JCString)this.UserList.getItem(j)).getString().compareTo(parse.getString()) == 0) {
                    this.UserList.deleteItem(j);
                }
            }
            catch (Exception ex2) {
                System.err.println(ex2.getMessage());
            }
        }
        this.UserList.addItem(parse);
    }
    
    void RemoveUser(final String s) {
        if (this.myScore.EndBtn.isEnabled()) {
            for (int i = 0; i < this.myScore.ScoreList.getItemCount(); ++i) {
                try {
                    final String item = this.myScore.ScoreList.getItem(i);
                    if (item.substring(0, item.indexOf("=")).compareTo(s) == 0) {
                        this.myScore.ScoreList.remove(i);
                    }
                }
                catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        for (int j = 0; j < 20; ++j) {
            if (loader.myChat[j].getTitle().compareTo(s) == 0) {
                loader.myChat[j].AppendText("", "!! User has Left the Room. ");
                loader.myChat[j].setTitle("");
            }
        }
        final JCString parse = JCString.parse(this, String.valueOf(String.valueOf(String.valueOf("[ALIGN=MIDDLE][IMG=").concat(String.valueOf(loader.myCodeBase))).concat(String.valueOf("img7.gif] "))).concat(String.valueOf(s)));
        for (int k = 0; k < this.UserList.countItems(); ++k) {
            try {
                if (((JCString)this.UserList.getItem(k)).getString().compareTo(parse.getString()) == 0) {
                    this.UserList.deleteItem(k);
                }
            }
            catch (Exception ex2) {
                System.err.println(ex2.getMessage());
            }
        }
    }
    
    void ConnectBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.VerifyLogin()) {
            this.UIbusy();
            this.GoConnect();
        }
    }
    
    void LeaveBtn_actionPerformed(final ActionEvent actionEvent) {
        if (!this.UsernameLocked) {
            this.UsernameInp.setText("");
        }
        if (!this.PasswordLocked) {
            this.PasswordInp.setText("");
        }
        if (!this.ProfileLocked) {
            this.ProfileInp.setText("");
        }
        this.GoDisconnect();
    }
    
    void FloatBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.FloatBtn.getLabel().trim().compareTo("Float") == 0) {
            if (this.RoomFrame == null) {
                (this.RoomFrame = new Frame(this.TitleTxt.getText())).setSize(500, 500);
                this.RoomFrame.setLocation(200, 100);
            }
            this.AppletBasePanel.removeAll();
            this.AppletBasePanel.add(this.FloatLabel, "Center");
            this.AppletBasePanel.doLayout();
            this.repaint();
            this.RoomFrame.add(this.ContentPanel);
            this.FloatBtn.setLabel(" Embed ");
            loader.StatusTxt.setText("  Chat Room Floating ");
            this.RoomFrame.setVisible(true);
            this.InputTxt.requestFocus();
        }
        else {
            loader.StatusTxt.setText("  Chat Room Embedded ");
            this.RoomFrame.removeAll();
            this.AppletBasePanel.removeAll();
            this.AppletBasePanel.add(this.ContentPanel, "Center");
            this.AppletBasePanel.doLayout();
            this.ContentPanel.doLayout();
            this.RoomFrame.setVisible(false);
            this.FloatBtn.setLabel(" Float ");
            this.validateTree();
            this.InputTxt.requestFocus();
        }
    }
    
    void ActionsBtn_actionPerformed(final ActionEvent actionEvent) {
        this.actionsMenu.show(this.ActionsBtn, 1, 30);
    }
    
    void TextBtn_actionPerformed(final ActionEvent actionEvent) {
        this.textMenu.show(this.TextBtn, 1, 30);
    }
    
    void OptionsBtn_actionPerformed(final ActionEvent actionEvent) {
        this.myOptions.setVisible(true);
    }
    
    void StaffBtn_actionPerformed(final ActionEvent actionEvent) {
        this.staffMenu.show(this.StaffBtn, 1, 30);
    }
    
    void AdminBtn_actionPerformed(final ActionEvent actionEvent) {
        this.adminMenu.show(this.AdminBtn, 1, 30);
    }
    
    void HelpBtn_actionPerformed(final ActionEvent actionEvent) {
        ShowNewUrl("http://chat-forum.com/docs/gold4/");
    }
    
    void ChatBtn_actionPerformed(final ActionEvent actionEvent) {
        this.DoChatEvent();
    }
    
    void ProfileBtn_actionPerformed(final ActionEvent actionEvent) {
        this.DoProfileEvent();
    }
    
    void BlockBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to block from the user list.");
            loader.StatusTxt.setText("  Select User To Block!");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        try {
            this.BlockedList.remove(trim);
            this.ShowMessageBox("Information", "User Unblocked", String.valueOf(trim).concat(String.valueOf(" Has Been REMOVED Form Your Blocked List")));
        }
        catch (Exception ex) {
            this.BlockedList.addItem(trim);
            this.ShowMessageBox("Information", "User Blocked", String.valueOf(trim).concat(String.valueOf(" Has Been ADDED To Your Blocked List")));
        }
    }
    
    void EjectBtn_actionPerformed(final ActionEvent actionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to Eject from the user list.");
            loader.StatusTxt.setText("  Select User To Eject!");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        loader.StatusTxt.setText(String.valueOf(String.valueOf("  Ejecting ").concat(String.valueOf(trim))).concat(String.valueOf("...")));
        SendLine(String.valueOf("900 200 ").concat(String.valueOf(trim)));
    }
    
    void SendBtn_actionPerformed(final ActionEvent actionEvent) {
        this.SendMessage();
    }
    
    void UserList_actionPerformed(final JCActionEvent jcActionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            return;
        }
        this.DoChatEvent();
    }
    
    void OutputList_actionPerformed(final JCActionEvent jcActionEvent) {
        this.OutputList.clear();
    }
    
    void InputTxt_actionPerformed(final JCActionEvent jcActionEvent) {
        this.SendMessage();
    }
    
    void MsgType_itemStateChanged(final ItemEvent itemEvent) {
        this.InputTxt.requestFocus();
    }
    
    void UsernameInp_actionPerformed(final JCActionEvent jcActionEvent) {
        if (this.VerifyLogin()) {
            this.UIbusy();
            this.GoConnect();
        }
    }
    
    void CopyrightTxt_mouseClicked(final MouseEvent mouseEvent) {
        ShowNewUrl("http://chat-forum.com/");
    }
    
    void menuItem1_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem1.getLabel());
    }
    
    void menuItem2_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem2.getLabel());
    }
    
    void menuItem3_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem3.getLabel());
    }
    
    void menuItem4_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem4.getLabel());
    }
    
    void menuItem5_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem5.getLabel());
    }
    
    void menuItem6_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem6.getLabel());
    }
    
    void menuItem7_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem7.getLabel());
    }
    
    void menuItem8_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem8.getLabel());
    }
    
    void menuItem9_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem9.getLabel());
    }
    
    void menuItem10_actionPerformed(final ActionEvent actionEvent) {
        this.DoAction(this.menuItem10.getLabel());
    }
    
    void menuItem11_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.removeAll();
        loader.StatusTxt.setText("  Chat Room Text Cleared");
    }
    
    void menuItem12_actionPerformed(final ActionEvent actionEvent) {
        if (this.OutputList.getFont().getSize() == 8) {
            return;
        }
        this.OutputList.setFont(new Font(this.OutputList.getFont().getName(), this.OutputList.getFont().getStyle(), this.OutputList.getFont().getSize() - 2));
        loader.StatusTxt.setText("  Text Size Set Smaller");
        this.OutputList.repaint();
        this.OutputList.makeVisible(this.OutputList.countItems() - 1);
    }
    
    void menuItem13_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setFont(new Font("Helvetica", 1, 12));
        loader.StatusTxt.setText("  Text Size Set To Medium ");
        this.OutputList.repaint();
        this.OutputList.makeVisible(this.OutputList.countItems() - 1);
    }
    
    void menuItem14_actionPerformed(final ActionEvent actionEvent) {
        if (this.OutputList.getFont().getSize() == 22) {
            return;
        }
        this.OutputList.setFont(new Font(this.OutputList.getFont().getName(), this.OutputList.getFont().getStyle(), this.OutputList.getFont().getSize() + 2));
        loader.StatusTxt.setText("  Text Size Set Larger");
        this.OutputList.repaint();
        this.OutputList.makeVisible(this.OutputList.countItems() - 1);
    }
    
    void menuItem15_actionPerformed(final ActionEvent actionEvent) {
        this.myScore.setVisible(true);
    }
    
    void menuItem16_actionPerformed(final ActionEvent actionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to Warn from the user list.");
            loader.StatusTxt.setText("  Select User To Warn!");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        loader.StatusTxt.setText(String.valueOf(String.valueOf("  Warning ").concat(String.valueOf(trim))).concat(String.valueOf("...")));
        SendLine(String.valueOf(String.valueOf("900 100 ").concat(String.valueOf(trim))).concat(String.valueOf(" Admin Warning - Please adjust your conduct in the chat room or you may be removed/banned.")));
    }
    
    void menuItem17_actionPerformed(final ActionEvent actionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to Silence from the user list.");
            loader.StatusTxt.setText("  Select User To Silence!");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        loader.StatusTxt.setText(String.valueOf(String.valueOf("  Silencing ").concat(String.valueOf(trim))).concat(String.valueOf("...")));
        SendLine(String.valueOf("900 600 ").concat(String.valueOf(trim)));
    }
    
    void menuItem18_actionPerformed(final ActionEvent actionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to Unsilence from the user list.");
            loader.StatusTxt.setText("  Select User To Unsilence!");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        loader.StatusTxt.setText(String.valueOf(String.valueOf("  Unsilencing ").concat(String.valueOf(trim))).concat(String.valueOf("...")));
        SendLine(String.valueOf("900 650 ").concat(String.valueOf(trim)));
    }
    
    void menuItem19_actionPerformed(final ActionEvent actionEvent) {
        if (this.UserList.getSelectedIndex() == -999) {
            this.ShowMessageBox("Selection Error", "No User Selected", "Select a user to Eject from the user list.");
            loader.StatusTxt.setText("  Select User To Eject!");
            return;
        }
        final String trim = ((JCString)this.UserList.getItem(this.UserList.getSelectedIndex())).getString().trim();
        this.UserList.deselectAll();
        loader.StatusTxt.setText(String.valueOf(String.valueOf("  Ejecting ").concat(String.valueOf(trim))).concat(String.valueOf("...")));
        SendLine(String.valueOf("900 200 ").concat(String.valueOf(trim)));
    }
    
    void menuItem20_actionPerformed(final ActionEvent actionEvent) {
        loader.StatusTxt.setText("  Opening Floor...");
        SendLine("900 450 OK");
    }
    
    void menuItem21_actionPerformed(final ActionEvent actionEvent) {
        loader.StatusTxt.setText("  Closing Floor...");
        SendLine("900 400 OK");
    }
    
    void menuItem22_actionPerformed(final ActionEvent actionEvent) {
        this.mySetWelcome.MessageTxt.setText("Updating...");
        this.mySetWelcome.MessageTxt.setEnabled(false);
        this.mySetWelcome.SetBtn.setEnabled(false);
        this.mySetWelcome.setVisible(true);
        SendLine("910 100 OK");
    }
    
    void menuItem23_actionPerformed(final ActionEvent actionEvent) {
        this.mySetStaff.UIBusy();
        this.mySetStaff.StaffList.removeAll();
        this.mySetStaff.setVisible(true);
        SendLine("910 600 OK");
    }
    
    void menuItem24_actionPerformed(final ActionEvent actionEvent) {
        this.mySendUrl.UrlInp.setText("");
        this.mySendUrl.setVisible(true);
        this.mySendUrl.UrlInp.requestFocus();
    }
    
    void ConnectBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Enter a Username to Connect  ");
    }
    
    void ConnectBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void LeaveBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("EXIT the Chat Room  ");
    }
    
    void LeaveBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void FloatBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText(String.valueOf(this.FloatBtn.getLabel().trim()).concat(String.valueOf(" Chat Room  ")));
    }
    
    void FloatBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void ActionsBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Pre-set User **Actions**  ");
    }
    
    void ActionsBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void TextBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Chat Room Text Options  ");
    }
    
    void TextBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void OptionsBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Chat Room Options  ");
    }
    
    void OptionsBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void StaffBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Staff & Admin Options  ");
    }
    
    void StaffBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void AdminBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Admin Only Options  ");
    }
    
    void AdminBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void HelpBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Help (Opens New Window)  ");
    }
    
    void HelpBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void ChatBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Start a Private Chat  ");
    }
    
    void ChatBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void ProfileBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("View Selected User Profile  ");
    }
    
    void ProfileBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void BlockBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Block/Unblock Selected User  ");
    }
    
    void BlockBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void EjectBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Eject Selected User  ");
    }
    
    void EjectBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void MsgType_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Select Message Type  ");
    }
    
    void MsgType_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void SendBtn_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Send Message (or Enter)  ");
    }
    
    void SendBtn_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void CopyrightTxt_mouseEntered(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("Click to here visit Chat-Forum.com  ");
    }
    
    void CopyrightTxt_mouseExited(final MouseEvent mouseEvent) {
        loader.PromptTxt.setText("");
    }
    
    void OutputModeMenu_actionPerformed(final ActionEvent actionEvent) {
        if (this.OutputModeMenu.getLabel().trim().compareTo("Transcript Mode") == 0) {
            this.cardLayout2.last(this.OutputPanel);
            this.OutputModeMenu.setLabel("Graphics Mode");
        }
        else {
            this.cardLayout2.first(this.OutputPanel);
            this.OutputModeMenu.setLabel("Transcript Mode ");
        }
    }
    
    void menuItem25_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(loader.outputBgColor);
    }
    
    void menuItem31_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(new Color(235, 235, 235));
    }
    
    void menuItem26_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(new Color(235, 235, 255));
    }
    
    void menuItem27_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(new Color(255, 255, 255));
    }
    
    void menuItem28_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(new Color(255, 255, 225));
    }
    
    void menuItem29_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(new Color(255, 235, 235));
    }
    
    void menuItem30_actionPerformed(final ActionEvent actionEvent) {
        this.OutputList.setBackground(new Color(235, 255, 235));
    }
    
    static {
        loader.more = true;
        loader.primaryBgColor = Color.decode("#0022AA");
        loader.primaryFgColor = Color.decode("#FFFFFF");
        loader.secondaryBgColor = Color.decode("#EEEEFF");
        loader.secondaryFgColor = Color.decode("#333333");
        loader.buttonBgColor = Color.decode("#EEEEF8");
        loader.buttonFgColor = Color.decode("#333333");
        loader.inputBgColor = Color.decode("#FFFFFF");
        loader.inputFgColor = Color.decode("#000000");
        loader.outputBgColor = Color.decode("#FCFCFF");
        loader.PermitInput = true;
        loader.myChat = new Chat[20];
        loader.PromptTxt = new Label();
        loader.StatusTxt = new Label();
    }
    
    class 1 extends MouseAdapter
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
            loader.this.CopyrightTxt_mouseClicked(mouseEvent);
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.CopyrightTxt_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.CopyrightTxt_mouseExited(mouseEvent);
        }
    }
    
    class 2 implements JCActionListener
    {
        public void actionPerformed(final JCActionEvent jcActionEvent) {
            loader.this.UsernameInp_actionPerformed(jcActionEvent);
        }
    }
    
    class 3 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.ConnectBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.ConnectBtn_mouseExited(mouseEvent);
        }
    }
    
    class 4 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.ChatBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.ChatBtn_mouseExited(mouseEvent);
        }
    }
    
    class 5 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.BlockBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.BlockBtn_mouseExited(mouseEvent);
        }
    }
    
    class 6 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem1_actionPerformed(actionEvent);
        }
    }
    
    class 7 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem2_actionPerformed(actionEvent);
        }
    }
    
    class 8 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem3_actionPerformed(actionEvent);
        }
    }
    
    class 9 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.ProfileBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.ProfileBtn_mouseExited(mouseEvent);
        }
    }
    
    class 10 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.EjectBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.EjectBtn_mouseExited(mouseEvent);
        }
    }
    
    class 11 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem4_actionPerformed(actionEvent);
        }
    }
    
    class 12 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem5_actionPerformed(actionEvent);
        }
    }
    
    class 13 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem6_actionPerformed(actionEvent);
        }
    }
    
    class 14 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem7_actionPerformed(actionEvent);
        }
    }
    
    class 15 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem8_actionPerformed(actionEvent);
        }
    }
    
    class 16 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem9_actionPerformed(actionEvent);
        }
    }
    
    class 17 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem10_actionPerformed(actionEvent);
        }
    }
    
    class 18 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem11_actionPerformed(actionEvent);
        }
    }
    
    class 19 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem12_actionPerformed(actionEvent);
        }
    }
    
    class 20 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem13_actionPerformed(actionEvent);
        }
    }
    
    class 21 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem14_actionPerformed(actionEvent);
        }
    }
    
    class 22 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem15_actionPerformed(actionEvent);
        }
    }
    
    class 23 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem16_actionPerformed(actionEvent);
        }
    }
    
    class 24 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem17_actionPerformed(actionEvent);
        }
    }
    
    class 25 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem18_actionPerformed(actionEvent);
        }
    }
    
    class 26 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem19_actionPerformed(actionEvent);
        }
    }
    
    class 27 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem20_actionPerformed(actionEvent);
        }
    }
    
    class 28 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem21_actionPerformed(actionEvent);
        }
    }
    
    class 29 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem22_actionPerformed(actionEvent);
        }
    }
    
    class 30 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem23_actionPerformed(actionEvent);
        }
    }
    
    class 31 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem25_actionPerformed(actionEvent);
        }
    }
    
    class 32 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem26_actionPerformed(actionEvent);
        }
    }
    
    class 33 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem27_actionPerformed(actionEvent);
        }
    }
    
    class 34 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem28_actionPerformed(actionEvent);
        }
    }
    
    class 35 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem29_actionPerformed(actionEvent);
        }
    }
    
    class 36 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem30_actionPerformed(actionEvent);
        }
    }
    
    class 37 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem31_actionPerformed(actionEvent);
        }
    }
    
    class 38 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.OutputModeMenu_actionPerformed(actionEvent);
        }
    }
    
    class 39 implements JCActionListener
    {
        public void actionPerformed(final JCActionEvent jcActionEvent) {
            loader.this.OutputList_actionPerformed(jcActionEvent);
        }
    }
    
    class 40 implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            loader.this.menuItem24_actionPerformed(actionEvent);
        }
    }
    
    class 41 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.TextBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.TextBtn_mouseExited(mouseEvent);
        }
    }
    
    class 42 implements JCActionListener
    {
        public void actionPerformed(final JCActionEvent jcActionEvent) {
            loader.this.UserList_actionPerformed(jcActionEvent);
        }
    }
    
    class 43 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.SendBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.SendBtn_mouseExited(mouseEvent);
        }
    }
    
    class 44 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.MsgType_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.MsgType_mouseExited(mouseEvent);
        }
    }
    
    class 45 implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            loader.this.MsgType_itemStateChanged(itemEvent);
        }
    }
    
    class 46 implements JCActionListener
    {
        public void actionPerformed(final JCActionEvent jcActionEvent) {
            loader.this.InputTxt_actionPerformed(jcActionEvent);
        }
    }
    
    class 47 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.FloatBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.FloatBtn_mouseExited(mouseEvent);
        }
    }
    
    class 48 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.LeaveBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.LeaveBtn_mouseExited(mouseEvent);
        }
    }
    
    class 49 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.ActionsBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.ActionsBtn_mouseExited(mouseEvent);
        }
    }
    
    class 50 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.OptionsBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.OptionsBtn_mouseExited(mouseEvent);
        }
    }
    
    class 51 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.StaffBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.StaffBtn_mouseExited(mouseEvent);
        }
    }
    
    class 52 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.AdminBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.AdminBtn_mouseExited(mouseEvent);
        }
    }
    
    class 53 extends MouseAdapter
    {
        public void mouseEntered(final MouseEvent mouseEvent) {
            loader.this.HelpBtn_mouseEntered(mouseEvent);
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            loader.this.HelpBtn_mouseExited(mouseEvent);
        }
    }
}
