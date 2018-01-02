// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.io.StringReader;
import java.awt.datatransfer.DataFlavor;
import rene.dialogs.Warning;
import java.net.URL;
import java.io.File;
import java.awt.Dimension;
import jagoclient.dialogs.Help;
import rene.util.FileName;
import rene.util.xml.XmlReaderException;
import rene.util.xml.XmlReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.FileInputStream;
import jagoclient.BMPFile;
import java.io.IOException;
import jagoclient.Go;
import java.io.FileOutputStream;
import java.awt.FileDialog;
import jagoclient.mail.MailDialog;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import jagoclient.dialogs.Message;
import java.awt.Window;
import jagoclient.gui.Panel3D;
import jagoclient.gui.SimplePanel;
import java.awt.Component;
import jagoclient.gui.MyPanel;
import jagoclient.gui.MyLabel;
import jagoclient.gui.CheckboxMenuItemAction;
import java.awt.MenuItem;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.MenuItemAction;
import java.awt.Menu;
import jagoclient.gui.MyMenu;
import java.awt.MenuBar;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import rene.gui.Global;
import java.io.InputStreamReader;
import java.awt.Color;
import java.awt.Panel;
import java.awt.CheckboxMenuItem;
import jagoclient.gui.NavigationButtonAction;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.KeyListener;
import java.io.FilenameFilter;
import jagoclient.gui.CloseFrame;

public class GoFrame extends CloseFrame implements FilenameFilter, KeyListener, BoardInterface, ClipboardOwner
{
    TextArea T;
    public Label L;
    public Label Lm;
    TextArea Comment;
    String Dir;
    public Board B;
    NavigationButtonAction LeftVar;
    NavigationButtonAction RightVar;
    NavigationButtonAction UpVar;
    NavigationButtonAction MainVar;
    NavigationButtonAction MainVarDown;
    NavigationButtonAction[] Up;
    NavigationButtonAction[] Down;
    CheckboxMenuItem SetBlack;
    CheckboxMenuItem SetWhite;
    CheckboxMenuItem Black;
    CheckboxMenuItem White;
    CheckboxMenuItem Mark;
    CheckboxMenuItem Letter;
    CheckboxMenuItem Hide;
    CheckboxMenuItem Square;
    CheckboxMenuItem Cross;
    CheckboxMenuItem Circle;
    CheckboxMenuItem Triangle;
    CheckboxMenuItem TextMark;
    Panel BPanel;
    public Color BoardColor;
    public Color BlackColor;
    public Color BlackSparkleColor;
    public Color WhiteColor;
    public Color WhiteSparkleColor;
    public Color MarkerColor;
    public Color LabelColor;
    CheckboxMenuItem Coordinates;
    CheckboxMenuItem UpperLeftCoordinates;
    CheckboxMenuItem LowerRightCoordinates;
    CheckboxMenuItem PureSGF;
    CheckboxMenuItem CommentSGF;
    CheckboxMenuItem DoSound;
    CheckboxMenuItem BeepOnly;
    CheckboxMenuItem TrueColor;
    CheckboxMenuItem Alias;
    CheckboxMenuItem TrueColorStones;
    CheckboxMenuItem SmallerStones;
    CheckboxMenuItem MenuLastNumber;
    CheckboxMenuItem MenuTarget;
    CheckboxMenuItem Shadows;
    CheckboxMenuItem BlackOnly;
    CheckboxMenuItem UseXML;
    CheckboxMenuItem UseSGF;
    public boolean BWColor;
    public boolean LastNumber;
    public boolean ShowTarget;
    CheckboxMenuItem MenuBWColor;
    CheckboxMenuItem ShowButtons;
    String Text;
    boolean Show;
    TextMarkQuestion TMQ;
    InputStreamReader LaterLoad;
    boolean LaterLoadXml;
    
    public GoFrame(final String s) {
        super(s);
        this.BWColor = false;
        this.LastNumber = false;
        this.ShowTarget = false;
        this.Text = Global.getParameter("textmark", "A");
        this.seticon("iboard.gif");
        this.setcolors();
    }
    
    void setcolors() {
        this.BoardColor = jagoclient.Global.getColor("boardcolor", 170, 120, 70);
        this.BlackColor = jagoclient.Global.getColor("blackcolor", 30, 30, 30);
        this.BlackSparkleColor = jagoclient.Global.getColor("blacksparklecolor", 120, 120, 120);
        this.WhiteColor = jagoclient.Global.getColor("whitecolor", 210, 210, 210);
        this.WhiteSparkleColor = jagoclient.Global.getColor("whitesparklecolor", 250, 250, 250);
        this.MarkerColor = jagoclient.Global.getColor("markercolor", Color.blue);
        this.LabelColor = jagoclient.Global.getColor("labelcolor", Color.pink.darker());
        jagoclient.Global.setColor("boardcolor", this.BoardColor);
        jagoclient.Global.setColor("blackcolor", this.BlackColor);
        jagoclient.Global.setColor("blacksparklecolor", this.BlackSparkleColor);
        jagoclient.Global.setColor("whitecolor", this.WhiteColor);
        jagoclient.Global.setColor("whitesparklecolor", this.WhiteSparkleColor);
        jagoclient.Global.setColor("markercolor", this.MarkerColor);
        jagoclient.Global.setColor("labelcolor", this.LabelColor);
    }
    
    public GoFrame(final Frame frame, final String s) {
        super(s);
        this.BWColor = false;
        this.LastNumber = false;
        this.ShowTarget = false;
        this.Text = Global.getParameter("textmark", "A");
        this.setcolors();
        this.seticon("iboard.gif");
        this.setLayout(new BorderLayout());
        final MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);
        final MyMenu myMenu = new MyMenu(jagoclient.Global.resourceString("File"));
        menuBar.add(myMenu);
        myMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Ascii_Board")));
        myMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Game_Information")));
        myMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Game_Copyright")));
        myMenu.addSeparator();
        myMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Close")));
        final MyMenu myMenu2 = new MyMenu(jagoclient.Global.resourceString("Set"));
        menuBar.add(myMenu2);
        myMenu2.add(this.Mark = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Mark")));
        myMenu2.add(this.Letter = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Letter")));
        myMenu2.add(this.Hide = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Delete")));
        final MyMenu myMenu3 = new MyMenu(jagoclient.Global.resourceString("Special_Mark"));
        myMenu3.add(this.Square = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Square")));
        myMenu3.add(this.Circle = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Circle")));
        myMenu3.add(this.Triangle = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Triangle")));
        myMenu3.add(this.Cross = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Cross")));
        myMenu3.addSeparator();
        myMenu3.add(this.TextMark = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Text")));
        myMenu2.add(myMenu3);
        myMenu2.addSeparator();
        myMenu2.add(new MenuItemAction(this, jagoclient.Global.resourceString("Resume_playing")));
        myMenu2.addSeparator();
        myMenu2.add(new MenuItemAction(this, jagoclient.Global.resourceString("Pass")));
        myMenu2.addSeparator();
        myMenu2.add(this.SetBlack = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Set_Black")));
        myMenu2.add(this.SetWhite = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Set_White")));
        myMenu2.addSeparator();
        myMenu2.add(this.Black = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Black_to_play")));
        myMenu2.add(this.White = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("White_to_play")));
        myMenu2.addSeparator();
        myMenu2.add(new MenuItemAction(this, jagoclient.Global.resourceString("Undo_Adding_Removing")));
        myMenu2.add(new MenuItemAction(this, jagoclient.Global.resourceString("Clear_all_marks")));
        final MyMenu myMenu4 = new MyMenu(jagoclient.Global.resourceString("Nodes"));
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Insert_Node")));
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Insert_Variation")));
        myMenu4.addSeparator();
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Next_Game")));
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Previous_Game")));
        myMenu4.addSeparator();
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Search")));
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Search_Again")));
        myMenu4.addSeparator();
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Node_Name")));
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Goto_Next_Name")));
        myMenu4.add(new MenuItemAction(this, jagoclient.Global.resourceString("Goto_Previous_Name")));
        menuBar.add(myMenu4);
        final MyMenu myMenu5 = new MyMenu(jagoclient.Global.resourceString("Finish_Game"));
        menuBar.add(myMenu5);
        myMenu5.add(new MenuItemAction(this, jagoclient.Global.resourceString("Prisoner_Count")));
        myMenu5.add(new MenuItemAction(this, jagoclient.Global.resourceString("Remove_groups")));
        myMenu5.add(new MenuItemAction(this, jagoclient.Global.resourceString("Score")));
        final MyMenu myMenu6 = new MyMenu(jagoclient.Global.resourceString("Options"));
        final MyMenu myMenu7 = new MyMenu(jagoclient.Global.resourceString("Coordinates"));
        myMenu7.add(this.Coordinates = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("On")));
        this.Coordinates.setState(Global.getParameter("coordinates", true));
        myMenu7.add(this.UpperLeftCoordinates = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Upper_Left")));
        this.UpperLeftCoordinates.setState(Global.getParameter("upperleftcoordinates", true));
        myMenu7.add(this.LowerRightCoordinates = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Lower_Right")));
        this.LowerRightCoordinates.setState(Global.getParameter("lowerrightcoordinates", true));
        myMenu6.add(myMenu7);
        final MyMenu myMenu8 = new MyMenu(jagoclient.Global.resourceString("Colors"));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("Board_Color")));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("Black_Color")));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("Black_Sparkle_Color")));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("White_Color")));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("White_Sparkle_Color")));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("Label_Color")));
        myMenu8.add(new MenuItemAction(this, jagoclient.Global.resourceString("Marker_Color")));
        myMenu6.add(myMenu8);
        final MyMenu myMenu9 = new MyMenu(jagoclient.Global.resourceString("Fonts"));
        myMenu9.add(new MenuItemAction(this, jagoclient.Global.resourceString("Board_Font")));
        myMenu9.add(new MenuItemAction(this, jagoclient.Global.resourceString("Fixed_Font")));
        myMenu9.add(new MenuItemAction(this, jagoclient.Global.resourceString("Normal_Font")));
        myMenu6.add(myMenu9);
        myMenu6.addSeparator();
        myMenu6.add(this.MenuTarget = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Show_Target")));
        this.MenuTarget.setState(Global.getParameter("showtarget", false));
        this.ShowTarget = this.MenuTarget.getState();
        myMenu6.add(this.MenuLastNumber = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Last_Number")));
        this.MenuLastNumber.setState(Global.getParameter("lastnumber", false));
        this.LastNumber = this.MenuLastNumber.getState();
        myMenu6.add(new MenuItemAction(this, jagoclient.Global.resourceString("Last_50")));
        myMenu6.add(new MenuItemAction(this, jagoclient.Global.resourceString("Last_100")));
        myMenu6.addSeparator();
        myMenu6.add(this.TrueColor = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("True_Color_Board")));
        this.TrueColor.setState(Global.getParameter("beauty", true));
        myMenu6.add(this.TrueColorStones = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("True_Color_Stones")));
        this.TrueColorStones.setState(Global.getParameter("beautystones", true));
        myMenu6.add(this.Alias = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Anti_alias_Stones")));
        this.Alias.setState(Global.getParameter("alias", true));
        myMenu6.add(this.Shadows = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Shadows")));
        this.Shadows.setState(Global.getParameter("shadows", false));
        myMenu6.add(this.SmallerStones = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Smaller_Stones")));
        this.SmallerStones.setState(Global.getParameter("smallerstones", false));
        myMenu6.add(this.BlackOnly = new CheckboxMenuItemAction(this, jagoclient.Global.resourceString("Black_Only")));
        this.BlackOnly.setState(Global.getParameter("blackonly", false));
        final MyMenu helpMenu = new MyMenu(jagoclient.Global.resourceString("Help"));
        helpMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("About_Jago")));
        helpMenu.addSeparator();
        helpMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Board_Window")));
        helpMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Making_Moves")));
        helpMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Keyboard_Shortcuts")));
        helpMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("About_Variations")));
        helpMenu.add(new MenuItemAction(this, jagoclient.Global.resourceString("Playing_Games")));
        menuBar.add(myMenu6);
        menuBar.setHelpMenu(helpMenu);
        (this.Comment = new TextArea("", 0, 0, 1)).setFont(jagoclient.Global.SansSerif);
        this.Comment.setBackground(jagoclient.Global.gray);
        this.L = new MyLabel(jagoclient.Global.resourceString("New_Game"));
        this.Lm = new MyLabel("--");
        this.B = new Board(19, this);
        final MyPanel myPanel = new MyPanel();
        myPanel.setLayout(new BorderLayout());
        myPanel.add("Center", this.B);
        final SimplePanel simplePanel = new SimplePanel(this.L, 80.0, this.Lm, 20.0);
        myPanel.add("South", simplePanel);
        simplePanel.setBackground(jagoclient.Global.gray);
        this.add("Center", new BoardCommentPanel(myPanel, this.Comment, this.B));
        final MyPanel bPanel = new MyPanel();
        bPanel.add(new NavigationButtonAction(this, jagoclient.Global.resourceString("Undo")));
        bPanel.add(new MyLabel(" "));
        this.Up = new NavigationButtonAction[3];
        this.Down = new NavigationButtonAction[3];
        bPanel.add(this.Up[2] = new NavigationButtonAction(this, "I<<"));
        bPanel.add(this.Up[1] = new NavigationButtonAction(this, "<<"));
        bPanel.add(this.Up[0] = new NavigationButtonAction(this, "<"));
        bPanel.add(this.Down[0] = new NavigationButtonAction(this, ">"));
        bPanel.add(this.Down[1] = new NavigationButtonAction(this, ">>"));
        bPanel.add(this.Down[2] = new NavigationButtonAction(this, ">>I"));
        bPanel.add(new MyLabel(" "));
        bPanel.add(this.LeftVar = new NavigationButtonAction(this, "<V"));
        bPanel.add(this.UpVar = new NavigationButtonAction(this, "V"));
        bPanel.add(this.RightVar = new NavigationButtonAction(this, "V>"));
        bPanel.add(this.MainVar = new NavigationButtonAction(this, "*"));
        bPanel.add(this.MainVarDown = new NavigationButtonAction(this, "**"));
        if (Global.getParameter("showbuttons", true)) {
            this.add("South", new Panel3D(bPanel));
        }
        this.BPanel = bPanel;
        this.Dir = new String("");
        jagoclient.Global.setwindow(this, "board", 500, 450);
        this.validate();
        this.Show = true;
        this.B.addKeyListener(this);
        this.show();
        this.repaint();
    }
    
    public void doAction(final String s) {
        if (jagoclient.Global.resourceString("Undo").equals(s)) {
            this.B.undo();
            return;
        }
        if (jagoclient.Global.resourceString("Close").equals(s)) {
            this.close();
            return;
        }
        if (jagoclient.Global.resourceString("Board_size").equals(s)) {
            this.boardsize();
            return;
        }
        if ("<".equals(s)) {
            this.B.back();
            return;
        }
        if (">".equals(s)) {
            this.B.forward();
            return;
        }
        if (">>".equals(s)) {
            this.B.fastforward();
            return;
        }
        if ("<<".equals(s)) {
            this.B.fastback();
            return;
        }
        if ("I<<".equals(s)) {
            this.B.allback();
            return;
        }
        if (">>I".equals(s)) {
            this.B.allforward();
            return;
        }
        if ("<V".equals(s)) {
            this.B.varleft();
            return;
        }
        if ("V>".equals(s)) {
            this.B.varright();
            return;
        }
        if ("V".equals(s)) {
            this.B.varup();
            return;
        }
        if ("**".equals(s)) {
            this.B.varmaindown();
            return;
        }
        if ("*".equals(s)) {
            this.B.varmain();
            return;
        }
        if (jagoclient.Global.resourceString("Pass").equals(s)) {
            this.B.pass();
            this.notepass();
            return;
        }
        if (jagoclient.Global.resourceString("Resume_playing").equals(s)) {
            this.B.resume();
            return;
        }
        if (jagoclient.Global.resourceString("Clear_all_marks").equals(s)) {
            this.B.clearmarks();
            return;
        }
        if (jagoclient.Global.resourceString("Undo_Adding_Removing").equals(s)) {
            this.B.clearremovals();
            return;
        }
        if (jagoclient.Global.resourceString("Remove_groups").equals(s)) {
            this.B.score();
            return;
        }
        if (jagoclient.Global.resourceString("Score").equals(s)) {
            final String done = this.B.done();
            if (done != null) {
                new Message(this, done);
            }
        }
        else {
            if (jagoclient.Global.resourceString("Local_Count").equals(s)) {
                new Message(this, this.B.docount());
                return;
            }
            if (jagoclient.Global.resourceString("New").equals(s)) {
                this.B.deltree();
                this.B.copy();
                return;
            }
            if (jagoclient.Global.resourceString("Mail").equals(s)) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(50000);
                try {
                    if (Global.getParameter("xml", false)) {
                        final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF8"), true);
                        this.B.saveXML(printWriter, "utf-8");
                        printWriter.close();
                    }
                    else {
                        final PrintWriter printWriter2 = new PrintWriter(byteArrayOutputStream, true);
                        this.B.save(printWriter2);
                        printWriter2.close();
                    }
                }
                catch (Exception ex5) {}
                new MailDialog(this, byteArrayOutputStream.toString());
                return;
            }
            if (jagoclient.Global.resourceString("Ascii_Board").equals(s)) {
                final ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(10000);
                final PrintWriter printWriter3 = new PrintWriter(byteArrayOutputStream2, true);
                try {
                    this.B.asciisave(printWriter3);
                }
                catch (Exception ex6) {}
                new MailDialog(this, byteArrayOutputStream2.toString());
                return;
            }
            if (jagoclient.Global.resourceString("Print").equals(s)) {
                this.B.print(jagoclient.Global.frame());
                return;
            }
            if (jagoclient.Global.resourceString("Save").equals(s)) {
                final FileDialog fileDialog = new FileDialog(this, jagoclient.Global.resourceString("Save"), 1);
                if (!this.Dir.equals("")) {
                    fileDialog.setDirectory(this.Dir);
                }
                final String getaction = this.B.firstnode().getaction("GN");
                if (getaction != null && !getaction.equals("")) {
                    fileDialog.setFile(String.valueOf(getaction) + "." + Global.getParameter("extension", Global.getParameter("xml", false) ? "xml" : "sgf"));
                }
                else {
                    fileDialog.setFile("*." + Global.getParameter("extension", Global.getParameter("xml", false) ? "xml" : "sgf"));
                }
                fileDialog.setFilenameFilter(this);
                fileDialog.show();
                final String file = fileDialog.getFile();
                if (file == null) {
                    return;
                }
                this.Dir = fileDialog.getDirectory();
                try {
                    PrintWriter printWriter4;
                    if (Global.getParameter("xml", false)) {
                        printWriter4 = new PrintWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(fileDialog.getDirectory()) + file), "UTF8"));
                        this.B.saveXML(printWriter4, "utf-8");
                    }
                    else {
                        if (Go.isApplet) {
                            printWriter4 = new PrintWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(fileDialog.getDirectory()) + file), Global.getParameter("encoding", "ASCII")));
                        }
                        else {
                            printWriter4 = new PrintWriter(new OutputStreamWriter(new FileOutputStream(String.valueOf(fileDialog.getDirectory()) + file), Global.getParameter("encoding", System.getProperty("file.encoding"))));
                        }
                        this.B.save(printWriter4);
                    }
                    printWriter4.close();
                    return;
                }
                catch (IOException ex) {
                    final Message message = new Message(this, String.valueOf(jagoclient.Global.resourceString("Write_error_")) + "\n" + ex.toString());
                    return;
                }
            }
            if (jagoclient.Global.resourceString("Save_Bitmap").equals(s)) {
                final FileDialog fileDialog2 = new FileDialog(this, jagoclient.Global.resourceString("Save_Bitmap"), 1);
                if (!this.Dir.equals("")) {
                    fileDialog2.setDirectory(this.Dir);
                }
                final String getaction2 = this.B.firstnode().getaction("GN");
                if (getaction2 != null && !getaction2.equals("")) {
                    fileDialog2.setFile(String.valueOf(getaction2) + "." + Global.getParameter("extension", "bmp"));
                }
                else {
                    fileDialog2.setFile("*." + Global.getParameter("extension", "bmp"));
                }
                fileDialog2.setFilenameFilter(this);
                fileDialog2.show();
                final String file2 = fileDialog2.getFile();
                if (file2 == null) {
                    return;
                }
                this.Dir = fileDialog2.getDirectory();
                try {
                    final BMPFile bmpFile = new BMPFile();
                    final Dimension boardImageSize = this.B.getBoardImageSize();
                    bmpFile.saveBitmap(String.valueOf(fileDialog2.getDirectory()) + file2, this.B.getBoardImage(), boardImageSize.width, boardImageSize.height);
                    return;
                }
                catch (Exception ex2) {
                    final Message message2 = new Message(this, String.valueOf(jagoclient.Global.resourceString("Write_error_")) + "\n" + ex2.toString());
                    return;
                }
            }
            if (jagoclient.Global.resourceString("Load").equals(s)) {
                final FileDialog fileDialog3 = new FileDialog(this, jagoclient.Global.resourceString("Load_Game"), 0);
                if (!this.Dir.equals("")) {
                    fileDialog3.setDirectory(this.Dir);
                }
                fileDialog3.setFilenameFilter(this);
                fileDialog3.setFile("*." + Global.getParameter("extension", Global.getParameter("xml", false) ? "xml" : "sgf"));
                fileDialog3.show();
                final String file3 = fileDialog3.getFile();
                if (file3 == null) {
                    return;
                }
                this.Dir = fileDialog3.getDirectory();
                try {
                    if (Global.getParameter("xml", false)) {
                        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(fileDialog3.getDirectory()) + file3), "UTF8"));
                        try {
                            this.B.loadXml(new XmlReader(bufferedReader));
                        }
                        catch (XmlReaderException ex3) {
                            final Message message3 = new Message(this, "Error in file!\n" + ex3.getText());
                        }
                        bufferedReader.close();
                    }
                    else {
                        BufferedReader bufferedReader2;
                        if (Go.isApplet) {
                            bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(fileDialog3.getDirectory()) + file3), Global.getParameter("encoding", "ASCII")));
                        }
                        else {
                            bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(fileDialog3.getDirectory()) + file3), Global.getParameter("encoding", System.getProperty("file.encoding"))));
                        }
                        try {
                            this.B.load(bufferedReader2);
                        }
                        catch (IOException ex7) {
                            new Message(this, "Error in file!");
                        }
                        bufferedReader2.close();
                    }
                }
                catch (IOException ex4) {
                    final Message message4 = new Message(this, String.valueOf(jagoclient.Global.resourceString("Read_error_")) + "\n" + ex4.toString());
                    return;
                }
                final String getaction3 = this.B.firstnode().getaction("GN");
                if (getaction3 != null && !getaction3.equals("")) {
                    this.setTitle(getaction3);
                    return;
                }
                this.B.firstnode().setaction("GN", FileName.purefilename(file3));
                this.setTitle(FileName.purefilename(file3));
            }
            else {
                if (jagoclient.Global.resourceString("Load_from_Clipboard").equals(s)) {
                    this.loadClipboard();
                    return;
                }
                if (jagoclient.Global.resourceString("Copy_to_Clipboard").equals(s)) {
                    this.saveClipboard();
                    return;
                }
                if (jagoclient.Global.resourceString("About_Jago").equals(s)) {
                    new Help("about");
                    return;
                }
                if (jagoclient.Global.resourceString("Board_Window").equals(s)) {
                    new Help("board");
                    return;
                }
                if (jagoclient.Global.resourceString("Making_Moves").equals(s)) {
                    new Help("moves");
                    return;
                }
                if (jagoclient.Global.resourceString("Keyboard_Shortcuts").equals(s)) {
                    new Help("keyboard");
                    return;
                }
                if (jagoclient.Global.resourceString("Playing_Games").equals(s)) {
                    new Help("playing");
                    return;
                }
                if (jagoclient.Global.resourceString("About_Variations").equals(s)) {
                    new Help("variations");
                    return;
                }
                if (jagoclient.Global.resourceString("Mailing_Games").equals(s)) {
                    new Help("mail");
                    return;
                }
                if (jagoclient.Global.resourceString("Insert_Node").equals(s)) {
                    this.B.insertnode();
                    return;
                }
                if (jagoclient.Global.resourceString("Insert_Variation").equals(s)) {
                    this.B.insertvariation();
                    return;
                }
                if (jagoclient.Global.resourceString("Game_Information").equals(s)) {
                    new EditInformation(this, this.B.firstnode());
                    return;
                }
                if (jagoclient.Global.resourceString("Game_Copyright").equals(s)) {
                    new EditCopyright(this, this.B.firstnode());
                    return;
                }
                if (jagoclient.Global.resourceString("Prisoner_Count").equals(s)) {
                    new Message(this, String.valueOf(jagoclient.Global.resourceString("Black__")) + this.B.Pw + jagoclient.Global.resourceString("__White__") + this.B.Pb + "\n" + jagoclient.Global.resourceString("Komi") + " " + this.B.getKomi());
                    return;
                }
                if (jagoclient.Global.resourceString("Board_Color").equals(s)) {
                    new BoardColorEdit(this, "boardcolor", this.BoardColor);
                    return;
                }
                if (jagoclient.Global.resourceString("Black_Color").equals(s)) {
                    new BoardColorEdit(this, "blackcolor", this.BlackColor);
                    return;
                }
                if (jagoclient.Global.resourceString("Black_Sparkle_Color").equals(s)) {
                    new BoardColorEdit(this, "blacksparklecolor", this.BlackSparkleColor);
                    return;
                }
                if (jagoclient.Global.resourceString("White_Color").equals(s)) {
                    new BoardColorEdit(this, "whitecolor", this.WhiteColor);
                    return;
                }
                if (jagoclient.Global.resourceString("White_Sparkle_Color").equals(s)) {
                    new BoardColorEdit(this, "whitesparklecolor", this.WhiteSparkleColor);
                    return;
                }
                if (jagoclient.Global.resourceString("Label_Color").equals(s)) {
                    new BoardColorEdit(this, "labelcolor", this.LabelColor);
                    return;
                }
                if (jagoclient.Global.resourceString("Marker_Color").equals(s)) {
                    new BoardColorEdit(this, "markercolor", this.MarkerColor);
                    return;
                }
                if (jagoclient.Global.resourceString("Board_Font").equals(s)) {
                    new BoardGetFontSize(this, "boardfontname", Global.getParameter("boardfontname", "SansSerif"), "boardfontsize", Global.getParameter("boardfontsize", 10), true).show();
                    this.updateall();
                    return;
                }
                if (jagoclient.Global.resourceString("Normal_Font").equals(s)) {
                    new BoardGetFontSize(this, "sansserif", Global.getParameter("sansserif", "SansSerif"), "ssfontsize", Global.getParameter("ssfontsize", 11), true).show();
                    this.updateall();
                    return;
                }
                if (jagoclient.Global.resourceString("Fixed_Font").equals(s)) {
                    new BoardGetFontSize(this, "monospaced", Global.getParameter("monospaced", "Monospaced"), "msfontsize", Global.getParameter("msfontsize", 11), true).show();
                    this.updateall();
                    return;
                }
                if (jagoclient.Global.resourceString("Last_50").equals(s)) {
                    this.B.lastrange(50);
                    return;
                }
                if (jagoclient.Global.resourceString("Last_100").equals(s)) {
                    this.B.lastrange(100);
                    return;
                }
                if (jagoclient.Global.resourceString("Node_Name").equals(s)) {
                    this.callInsert();
                    return;
                }
                if (jagoclient.Global.resourceString("Goto_Next_Name").equals(s)) {
                    this.B.gotonext();
                    return;
                }
                if (jagoclient.Global.resourceString("Goto_Previous_Name").equals(s)) {
                    this.B.gotoprevious();
                    return;
                }
                if (jagoclient.Global.resourceString("Next_Game").equals(s)) {
                    this.B.gotonextmain();
                    return;
                }
                if (jagoclient.Global.resourceString("Previous_Game").equals(s)) {
                    this.B.gotopreviousmain();
                    return;
                }
                if (jagoclient.Global.resourceString("Add_Game").equals(s)) {
                    this.B.addnewgame();
                    return;
                }
                if (jagoclient.Global.resourceString("Remove_Game").equals(s)) {
                    this.B.removegame();
                    return;
                }
                if (jagoclient.Global.resourceString("Save_Size").equals(s)) {
                    jagoclient.Global.notewindow(this, "board");
                    return;
                }
                if (jagoclient.Global.resourceString("Set_Encoding").equals(s)) {
                    new GetEncoding(this);
                    return;
                }
                if (jagoclient.Global.resourceString("Search_Again").equals(s)) {
                    this.search();
                    return;
                }
                if (jagoclient.Global.resourceString("Search").equals(s)) {
                    new GetSearchString(this);
                    return;
                }
                super.doAction(s);
            }
        }
    }
    
    public void search() {
        this.B.search(Global.getParameter("searchstring", "++"));
    }
    
    public void itemAction(final String s, final boolean showTarget) {
        if (jagoclient.Global.resourceString("Save_pure_SGF").equals(s)) {
            Global.setParameter("puresgf", showTarget);
            return;
        }
        if (jagoclient.Global.resourceString("Use_SGF_Comments").equals(s)) {
            Global.setParameter("sgfcomments", showTarget);
            return;
        }
        if (jagoclient.Global.resourceString("On").equals(s)) {
            Global.setParameter("coordinates", showTarget);
            this.updateall();
            return;
        }
        if (jagoclient.Global.resourceString("Upper_Left").equals(s)) {
            Global.setParameter("upperleftcoordinates", showTarget);
            this.updateall();
            return;
        }
        if (jagoclient.Global.resourceString("Lower_Right").equals(s)) {
            Global.setParameter("lowerrightcoordinates", showTarget);
            this.updateall();
            return;
        }
        if (jagoclient.Global.resourceString("Set_Black").equals(s)) {
            this.B.setblack();
            return;
        }
        if (jagoclient.Global.resourceString("Set_White").equals(s)) {
            this.B.setwhite();
            return;
        }
        if (jagoclient.Global.resourceString("Black_to_play").equals(s)) {
            this.B.black();
            return;
        }
        if (jagoclient.Global.resourceString("White_to_play").equals(s)) {
            this.B.white();
            return;
        }
        if (jagoclient.Global.resourceString("Mark").equals(s)) {
            this.B.mark();
            return;
        }
        if (jagoclient.Global.resourceString("Text").equals(s)) {
            this.B.textmark(this.Text);
            if (this.TMQ == null) {
                this.TMQ = new TextMarkQuestion(this, this.Text);
            }
        }
        else {
            if (jagoclient.Global.resourceString("Square").equals(s)) {
                this.B.specialmark(2);
                return;
            }
            if (jagoclient.Global.resourceString("Triangle").equals(s)) {
                this.B.specialmark(3);
                return;
            }
            if (jagoclient.Global.resourceString("Cross").equals(s)) {
                this.B.specialmark(1);
                return;
            }
            if (jagoclient.Global.resourceString("Circle").equals(s)) {
                this.B.specialmark(4);
                return;
            }
            if (jagoclient.Global.resourceString("Letter").equals(s)) {
                this.B.letter();
                return;
            }
            if (jagoclient.Global.resourceString("Delete").equals(s)) {
                this.B.deletestones();
                return;
            }
            if (jagoclient.Global.resourceString("True_Color_Board").equals(s)) {
                Global.setParameter("beauty", showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("True_Color_Stones").equals(s)) {
                Global.setParameter("beautystones", showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Anti_alias_Stones").equals(s)) {
                Global.setParameter("alias", showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Shadows").equals(s)) {
                Global.setParameter("shadows", showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Smaller_Stones").equals(s)) {
                Global.setParameter("smallerstones", showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Black_Only").equals(s)) {
                Global.setParameter("blackonly", showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Use_B_W_marks").equals(s)) {
                Global.setParameter("bwcolor", this.BWColor = showTarget);
                this.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Last_Number").equals(s)) {
                Global.setParameter("lastnumber", this.LastNumber = showTarget);
                this.B.updateall();
                return;
            }
            if (jagoclient.Global.resourceString("Show_Target").equals(s)) {
                Global.setParameter("showtarget", this.ShowTarget = showTarget);
                return;
            }
            if (jagoclient.Global.resourceString("Show_Buttons").equals(s)) {
                if (showTarget) {
                    this.add("South", this.BPanel);
                }
                else {
                    this.remove(this.BPanel);
                }
                if (this instanceof ConnectedGoFrame) {
                    Global.setParameter("showbuttonsconnected", showTarget);
                }
                else {
                    Global.setParameter("showbuttons", showTarget);
                }
                this.validate();
                return;
            }
            if (jagoclient.Global.resourceString("Use_XML").equals(s)) {
                this.UseXML.setState(true);
                this.UseSGF.setState(false);
                Global.setParameter("xml", true);
                return;
            }
            if (jagoclient.Global.resourceString("Use_SGF").equals(s)) {
                this.UseSGF.setState(true);
                this.UseXML.setState(false);
                Global.setParameter("xml", false);
            }
        }
    }
    
    public void black(final int n, final int n2) {
        this.B.black(n, n2);
    }
    
    public void white(final int n, final int n2) {
        this.B.white(n, n2);
    }
    
    public void setblack(final int n, final int n2) {
        this.B.setblack(n, n2);
    }
    
    public void setwhite(final int n, final int n2) {
        this.B.setwhite(n, n2);
    }
    
    public void territory(final int n, final int n2) {
        this.B.territory(n, n2);
    }
    
    public void color(final int n) {
        if (n == -1) {
            this.B.white();
            return;
        }
        this.B.black();
    }
    
    public void setState(final int n) {
        this.Black.setState(false);
        this.White.setState(false);
        this.SetBlack.setState(false);
        this.SetWhite.setState(false);
        this.Mark.setState(false);
        this.Letter.setState(false);
        this.Hide.setState(false);
        this.Circle.setState(false);
        this.Cross.setState(false);
        this.Triangle.setState(false);
        this.Square.setState(false);
        this.TextMark.setState(false);
        switch (n) {
            case 1: {
                this.Black.setState(true);
            }
            case 2: {
                this.White.setState(true);
            }
            case 3: {
                this.SetBlack.setState(true);
            }
            case 4: {
                this.SetWhite.setState(true);
            }
            case 5: {
                this.Mark.setState(true);
            }
            case 6: {
                this.Letter.setState(true);
            }
            case 7: {
                this.Hide.setState(true);
            }
            case 10: {
                this.TextMark.setState(true);
            }
            default: {}
        }
    }
    
    public void setMarkState(final int n) {
        this.setState(0);
        switch (n) {
            case 2: {
                this.Square.setState(true);
            }
            case 3: {
                this.Triangle.setState(true);
            }
            case 1: {
                this.Cross.setState(true);
            }
            case 4: {
                this.Circle.setState(true);
            }
            default: {}
        }
    }
    
    public void setState(int i, final boolean enabled) {
        switch (i) {
            case 1: {
                this.LeftVar.setEnabled(enabled);
            }
            case 2: {
                this.RightVar.setEnabled(enabled);
            }
            case 3: {
                this.UpVar.setEnabled(enabled);
            }
            case 4: {
                this.MainVar.setEnabled(enabled);
            }
            case 5: {
                for (i = 0; i < 3; ++i) {
                    this.Down[i].setEnabled(enabled);
                }
            }
            case 6: {
                for (i = 0; i < 3; ++i) {
                    this.Up[i].setEnabled(enabled);
                }
            }
            case 7: {
                this.MainVarDown.setEnabled(enabled);
            }
            default: {}
        }
    }
    
    public boolean accept(final File file, final String s) {
        return s.endsWith("." + Global.getParameter("extension", "sgf"));
    }
    
    void setTextmark(final String s) {
        this.B.textmark(s);
    }
    
    public boolean blocked() {
        return false;
    }
    
    public void active(final boolean b) {
        this.B.active(b);
    }
    
    public void pass() {
        this.B.pass();
    }
    
    public void setpass() {
        this.B.setpass();
    }
    
    public void notepass() {
    }
    
    public void handicap(final int n) {
        this.B.handicap(n);
    }
    
    public boolean moveset(final int n, final int n2) {
        return true;
    }
    
    public void movepass() {
    }
    
    public void undo(final int n) {
        this.B.undo(n);
    }
    
    public void undo() {
    }
    
    public boolean close() {
        if (Global.getParameter("confirmations", true)) {
            if (new CloseQuestion(this).Result) {
                this.doclose();
            }
            return false;
        }
        this.doclose();
        return false;
    }
    
    public void doboardsize(final int n) {
        this.B.setsize(n);
    }
    
    public void boardsize() {
        new SizeQuestion(this);
    }
    
    public int getboardsize() {
        return this.B.getboardsize();
    }
    
    public void addComment(final String s) {
        this.B.addcomment(s);
    }
    
    public void result(final int n, final int n2) {
    }
    
    public void yourMove(final boolean b) {
    }
    
    public void load(final String s) {
        try {
            if (s.endsWith(".xml")) {
                this.LaterLoad = new InputStreamReader(new FileInputStream(s), "UTF8");
                this.LaterLoadXml = true;
                return;
            }
            this.LaterLoad = new InputStreamReader(new FileInputStream(s));
            this.LaterLoadXml = false;
        }
        catch (Exception ex) {
            this.LaterLoad = null;
        }
    }
    
    public void load(final URL url) {
        try {
            if (url.toExternalForm().endsWith(".xml")) {
                this.LaterLoad = new InputStreamReader(url.openStream(), "UTF8");
                this.LaterLoadXml = true;
                return;
            }
            this.LaterLoad = new InputStreamReader(url.openStream());
            this.LaterLoadXml = false;
        }
        catch (Exception ex) {
            this.LaterLoad = null;
        }
    }
    
    public void doload(final Reader reader) {
        this.validate();
        try {
            this.B.load(new BufferedReader(reader));
            reader.close();
        }
        catch (Exception ex) {
            new Warning(this, ex.toString(), "Warning", true).setVisible(true);
        }
    }
    
    public void doloadXml(final Reader reader) {
        this.validate();
        try {
            this.B.loadXml(new XmlReader(new BufferedReader(reader)));
            reader.close();
        }
        catch (Exception ex) {
            new Warning(this, ex.toString(), "Warning", true).setVisible(true);
        }
    }
    
    public void loadClipboard() {
        try {
            final String s = (String)this.getToolkit().getSystemClipboard().getContents(this).getTransferData(DataFlavor.stringFlavor);
            if (XmlReader.testXml(s)) {
                this.doloadXml(new StringReader(s));
                return;
            }
            this.doload(new StringReader(s));
        }
        catch (Exception ex) {}
    }
    
    public void saveClipboard() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(50000);
            try {
                if (Global.getParameter("xml", false)) {
                    final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF8"), true);
                    this.B.saveXML(printWriter, "utf-8");
                    printWriter.close();
                }
                else {
                    final PrintWriter printWriter2 = new PrintWriter(byteArrayOutputStream, true);
                    this.B.save(printWriter2);
                    printWriter2.close();
                }
            }
            catch (Exception ex) {}
            this.getToolkit().getSystemClipboard().setContents(new StringSelection(byteArrayOutputStream.toString()), this);
        }
        catch (Exception ex2) {}
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    public void activate() {
        if (this.LaterLoad != null) {
            if (this.LaterLoadXml) {
                this.doloadXml(this.LaterLoad);
            }
            else {
                this.doload(this.LaterLoad);
            }
        }
        this.LaterLoad = null;
    }
    
    public void updateall() {
        this.setcolors();
        this.B.updateboard();
    }
    
    public boolean askUndo() {
        return new AskUndoQuestion(this).Result;
    }
    
    public boolean askInsert() {
        return new AskInsertQuestion(this).Result;
    }
    
    public void setname(final String s) {
        this.B.setname(s);
    }
    
    public void callInsert() {
        new NodeNameEdit(this, this.B.getname());
    }
    
    public void remove(final int n, final int n2) {
        this.B.remove(n, n2);
    }
    
    public void advanceTextmark() {
        if (this.TMQ != null) {
            this.TMQ.advance();
        }
    }
    
    public void setComment(final String text) {
        this.Comment.setText(text);
        this.Comment.append("");
    }
    
    public String getComment() {
        return this.Comment.getText();
    }
    
    public void appendComment(final String s) {
        this.Comment.append(s);
    }
    
    public void setLabel(final String text) {
        this.L.setText(text);
    }
    
    public void setLabelM(final String text) {
        this.Lm.setText(text);
    }
    
    public boolean boardShowing() {
        return this.Show;
    }
    
    public boolean lastNumber() {
        return this.LastNumber;
    }
    
    public boolean showTarget() {
        return this.ShowTarget;
    }
    
    public Color blackColor() {
        return this.BlackColor;
    }
    
    public Color whiteColor() {
        return this.WhiteColor;
    }
    
    public Color whiteSparkleColor() {
        return this.WhiteSparkleColor;
    }
    
    public Color blackSparkleColor() {
        return this.BlackSparkleColor;
    }
    
    public Color markerColor() {
        return this.MarkerColor;
    }
    
    public Color boardColor() {
        return this.BoardColor;
    }
    
    public Color labelColor() {
        return this.LabelColor;
    }
    
    public boolean bwColor() {
        return this.BWColor;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.isActionKey()) {
            switch (keyEvent.getKeyCode()) {
                case 155: {
                    this.callInsert();
                }
            }
        }
        else {
            switch (keyEvent.getKeyChar()) {
                case 'F':
                case 'f': {
                    this.B.search(Global.getParameter("searchstring", "++"));
                }
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public Color getColor(final String s, final int n, final int n2, final int n3) {
        return jagoclient.Global.getColor(s, n, n2, n3);
    }
    
    public String resourceString(final String s) {
        return jagoclient.Global.resourceString(s);
    }
    
    public String version() {
        return jagoclient.Global.Version;
    }
    
    public boolean getParameter(final String s, final boolean b) {
        return Global.getParameter(s, b);
    }
    
    public Font boardFont() {
        return jagoclient.Global.BoardFont;
    }
    
    public Frame frame() {
        return jagoclient.Global.frame();
    }
    
    public boolean blackOnly() {
        return this.BlackOnly != null && this.BlackOnly.getState();
    }
    
    public Color backgroundColor() {
        return jagoclient.Global.gray;
    }
}
