import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.Cursor;
import java.awt.event.WindowListener;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.Container;
import java.awt.TextField;
import java.awt.Button;
import symantec.itools.util.Timer;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MainContainer extends Panel implements ActionListener
{
    static final String MSG_BAD_AUDIO = "Error retrieving audio file.  Program\nwon't be able to sound the alarm when\nthe timer goes off";
    static final String MSG_LOGO = "CodeHouse\nJava(tm) consulting and tools\nwww.codehouse.com\n1-617-244-3867\n";
    static final String MSG_ABOUT = "Jtimer Version 1.02\nCopyright (c) CodeHouse, 1997-1998\n\nCodeHouse\nJava(tm) consulting and tools\nwww.codehouse.com\n1-617-244-3867\n\nJtimer is free. You're invited to put\nJtimer on your web site or on your\ncomputer. If you'd like to do so, visit\nthe Jtimer section of our web site:\n\nwww.codehouse.com/jtimer\n\nPlease e-mail us if you have any\nquestions, comments or bugs to report:\nsolutions@codehouse.com\n";
    static final String MSG_ZERO_TIME = "All the fields are zero.\nYou need to enter a desired countdown time";
    static final String MSG_HOURS_MISSING = "There's no entry for the \"Hours\" field.\nEnter an amount of hours in the range of 0 - 99";
    static final String MSG_MINS_MISSING = "There's no entry for the \"Minutes\" field.\nEnter an amount of minutes in the range of 0 - 59";
    static final String MSG_SECS_MISSING = "The \"Seconds\" field is missing.\nEnter an amount of seconds in the range of 0 - 59";
    static final String MSG_SECS_INVALID = "The \"Seconds\" field is invalid.\nEnter an amount of seconds in the range of 0 - 59";
    static final String MSG_MINS_INVALID = "The \"Minutes\" field is invalid.\nEnter an amount of minutes in the range of 0 - 59";
    static final String MSG_CLOSE_YESNO = "The timer is still active. Are you\nsure you want to close the window?";
    static String s;
    static final String MSG_HELP = "Jtimer can run as an applet or as an\napplication. In some cases, Jtimer\nworks differently, depending on\nwhether it's being run as an applet\nor as an application. These\ndifferences are pointed out below.\n\n\nSETTING THE TIMER:\n\nIn the fields labeled \"Hours\", \"Minutes\",\nand \"Seconds\", enter the amount of time you\nwish to elapse before the alarm sounds. If\nyou\u2019re running Jtimer as an applet, as long\nas your browser is open, the alarm will go\noff when the time has elapsed, even if you\nload a new web page.\n\nTURNING OFF THE ALARM WHEN\nTHE TIMER GOES OFF:\n\nOnce the alarm sounds, a dialog box will\ncome up, instructing you to shut the alarm\nby pressing its \"Stop Alarm\" button. Follow\nthe instruction.\n\nDETACHING JTIMER FROM A WEB PAGE:\n\nIf you\u2019re running Jtimer from a web page,\nyou can start as many instances of Jtimer as\nyou wish. To create another instance of\nJtimer, press the \"Detach\" button. The\ncurrent Jtimer will be moved to a new\nwindow on your desktop, where you can\nmove and resize it. It\u2019ll contain any data that\nyou entered on the web page, and if you\nstarted it on the web page, it'll continue\nwhatever countdown it was running. There\nwill now be a new copy of Jtimer on the web\npage, which you can enter data into, start,\nand detach as you see fit. Every time you\ndetach a copy, it\u2019ll continue to perform\nwhatever it was performing on the web\npage, and a new copy will replace it on that\nWeb page. You can even detach copies of\nJtimer that you have not entered any data\ninto and use them when you wish.\n\nMAXIMIZING SCREEN SPACE:\n\nThis feature isn't applicable when Jtimer\nis embedded in a web page. To make only the\ntimer\u2019s digits visible, thus saving screen\nspace, grab the bottom right corner of\nthe Jtimer window with the mouse and move\nthe mouse inwards. You\u2019ll notice that the\ndigits remain visible while the other items of\nthe window disappear\n\nUSING A CUSTOM ALARM\n\nIf you're running Jtimer as an application,\nyou can specify your own sound file to be\nplayed when the timer reaches zero. You do\nso by specifying the name of the sound file\nas a parameter on the command line. Jtimer\nwill only accept sound files of \".au\"\nformat. Here's an example:\n\njava Jtimer MyOwnSoundFile.au\n\nCONTACTING CODEHOUSE:\n\nIf you have any further questions,\ncomments, or bugs to report, please feel free\nto contact us:\n\nsupport@codehouse.com\nwww.codehouse.com\n1-617-244-3867";
    static final Font BTN_FONT;
    static final Color TXT_FOREGROUND;
    static final String IMG_LOGO_UP = "U.gif";
    static final String IMG_LOGO_DOWN = "D.gif";
    static Image imgLogoUp;
    static Image imgLogoDown;
    static final String BTN_DETACH_CLOSE = "Close";
    static final Vector frameVec;
    Timer ticker;
    Button btnStartTimer;
    Button btnPause;
    Button btnResume;
    Button btnReset;
    Button btnDetach;
    Button btnHelp;
    Button btnAbout;
    Display display;
    FTextField tfSecs;
    FTextField tfMins;
    FTextField tfHours;
    TextField tfNotes;
    ImgBtn btnImage;
    int cTicks;
    String rcAudioName;
    boolean sysExit;
    RCLoadAudio audio;
    Container theParent;
    static boolean logosLoaded;
    boolean userAbort;
    static boolean badAudioAlerted;
    
    void closeFrame(final Frame frame) {
        if (this.cTicks != 0) {
            this.ticker.pause();
            final Button button = new Button("No");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    MainContainer.this.userAbort = true;
                    ((Component)actionEvent.getSource()).getParent().getParent().setVisible(false);
                }
                
                {
                    MainContainer.this.userAbort = false;
                }
            });
            new MsgDlg("Jtimer", "The timer is still active. Are you\nsure you want to close the window?", "Yes", new Component[] { button }, button);
            if (this.userAbort) {
                this.ticker.resume();
                return;
            }
            this.ticker.stop();
            this.ticker = null;
            if (this.audio != null) {
                this.audio.stop();
                this.audio = null;
            }
        }
        if (this.theParent != null) {
            synchronized (this) {
                MainContainer.frameVec.removeElement(frame);
                final Enumeration<Frame> elements = MainContainer.frameVec.elements();
                while (elements.hasMoreElements()) {
                    final Frame frame2 = elements.nextElement();
                    this.setFrameTitleNumber(frame2, MainContainer.frameVec.indexOf(frame2) + 1);
                }
            }
        }
        if (this.sysExit) {
            System.exit(0);
            return;
        }
        frame.setVisible(false);
    }
    
    void setFrameTitleNumber(final Frame frame, final int n) {
        frame.setTitle("Jtimer -- " + n);
    }
    
    void setAudio(final String s) {
        try {
            this.audio = new RCLoadAudio(this, s);
        }
        catch (Exception ex) {
            if (!MainContainer.badAudioAlerted) {
                new MsgDlg("Jtimer", "Error retrieving audio file.  Program\nwon't be able to sound the alarm when\nthe timer goes off");
                MainContainer.badAudioAlerted = true;
            }
        }
    }
    
    void MainContainerInit(final String audio) {
        this.setAudio(audio);
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 15, 15, 15);
        final Label label = new Label("Countdown to Zero Timer");
        label.setFont(new Font("Helvetica", 1, 16));
        label.setForeground(MainContainer.TXT_FOREGROUND);
        this.gbSet(this, layout, gridBagConstraints, label, 0);
        gridBagConstraints.weighty = 0.0;
        this.gbSet(this, layout, gridBagConstraints, this.display = new Display(), 0);
        gridBagConstraints.weighty = 1.0;
        final Panel panel = new Panel(new FlowLayout(1, 5, 0));
        final Label label2 = new Label("Hours:", 2);
        label2.setForeground(MainContainer.TXT_FOREGROUND);
        label2.setFont(new Font("Helvetica", 1, 12));
        panel.add(label2);
        (this.tfHours = new FTextField(2)).setText("0");
        this.tfHours.setBackground(Color.white);
        panel.add(this.tfHours);
        final Label label3 = new Label("Minutes:", 2);
        label3.setForeground(MainContainer.TXT_FOREGROUND);
        label3.setFont(new Font("Helvetica", 1, 12));
        panel.add(label3);
        (this.tfMins = new FTextField(2)).setText("0");
        this.tfMins.setBackground(Color.white);
        panel.add(this.tfMins);
        final Label label4 = new Label("Seconds:", 2);
        label4.setForeground(MainContainer.TXT_FOREGROUND);
        label4.setFont(new Font("Helvetica", 1, 12));
        panel.add(label4);
        (this.tfSecs = new FTextField(2)).setText("0");
        this.tfSecs.setBackground(Color.white);
        panel.add(this.tfSecs);
        this.gbSet(this, layout, gridBagConstraints, panel, 0);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(1, 5, 0));
        (this.btnStartTimer = new Button("Start Timer")).setFont(MainContainer.BTN_FONT);
        panel2.add(this.btnStartTimer);
        (this.btnPause = new Button("Pause")).setFont(MainContainer.BTN_FONT);
        this.btnPause.setEnabled(false);
        panel2.add(this.btnPause);
        (this.btnResume = new Button("Resume")).setFont(MainContainer.BTN_FONT);
        this.btnResume.setEnabled(false);
        panel2.add(this.btnResume);
        (this.btnReset = new Button("Reset")).setFont(MainContainer.BTN_FONT);
        this.btnReset.setEnabled(false);
        panel2.add(this.btnReset);
        panel2.add(new Panel());
        (this.btnDetach = new Button("Detach")).setFont(MainContainer.BTN_FONT);
        panel2.add(this.btnDetach);
        this.gbSet(this, layout, gridBagConstraints, panel2, 0);
        gridBagConstraints.fill = 2;
        this.gbSet(this, layout, gridBagConstraints, this.getPanel3(), 0);
        gridBagConstraints.fill = 0;
        gridBagConstraints.insets = new Insets(0, 5, 5, 5);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 16;
        final Label label5 = new Label("Jtimer");
        label5.setForeground(new Color(57, 123, 247));
        label5.setFont(new Font("Helvetica", 3, 18));
        this.gbSet(this, layout, gridBagConstraints, label5, 1);
        gridBagConstraints.anchor = 14;
        this.gbSet(this, layout, gridBagConstraints, this.btnImage = new ImgBtn(true, MainContainer.imgLogoUp, MainContainer.imgLogoDown), 0);
        this.ticker = new Timer(true);
        this.addListeners();
    }
    
    MainContainer(final String rcAudioName) {
        if (!MainContainer.logosLoaded) {
            MainContainer.logosLoaded = true;
            MainContainer.imgLogoUp = RCLoadImage.loadImage(this.getClass(), "U.gif");
            MainContainer.imgLogoDown = RCLoadImage.loadImage(this.getClass(), "D.gif");
        }
        this.MainContainerInit(rcAudioName);
        this.rcAudioName = rcAudioName;
    }
    
    public void addNotify() {
        super.addNotify();
        if (this.theParent == null) {
            this.theParent = this.getParent();
            this.tfMins.requestFocus();
        }
    }
    
    void frame(final boolean sysExit) {
        if (this.theParent != null) {
            System.err.println("Error: Can't call frame() after MainContainer has been added to layout");
            return;
        }
        this.sysExit = sysExit;
        new DetachFrame();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.btnStartTimer) {
            this.btnStartTimer_Clicked(actionEvent);
            return;
        }
        if (source == this.btnPause) {
            this.btnPause_Clicked(actionEvent);
            return;
        }
        if (source == this.btnResume) {
            this.btnResume_Clicked(actionEvent);
            return;
        }
        if (source == this.ticker) {
            this.ticker_TimerEvent(actionEvent);
            return;
        }
        if (source == this.btnReset) {
            this.btnReset_Clicked(actionEvent);
            return;
        }
        if (source == this.btnHelp) {
            new MsgDlg("Jtimer -- Help", "Jtimer can run as an applet or as an\napplication. In some cases, Jtimer\nworks differently, depending on\nwhether it's being run as an applet\nor as an application. These\ndifferences are pointed out below.\n\n\nSETTING THE TIMER:\n\nIn the fields labeled \"Hours\", \"Minutes\",\nand \"Seconds\", enter the amount of time you\nwish to elapse before the alarm sounds. If\nyou\u2019re running Jtimer as an applet, as long\nas your browser is open, the alarm will go\noff when the time has elapsed, even if you\nload a new web page.\n\nTURNING OFF THE ALARM WHEN\nTHE TIMER GOES OFF:\n\nOnce the alarm sounds, a dialog box will\ncome up, instructing you to shut the alarm\nby pressing its \"Stop Alarm\" button. Follow\nthe instruction.\n\nDETACHING JTIMER FROM A WEB PAGE:\n\nIf you\u2019re running Jtimer from a web page,\nyou can start as many instances of Jtimer as\nyou wish. To create another instance of\nJtimer, press the \"Detach\" button. The\ncurrent Jtimer will be moved to a new\nwindow on your desktop, where you can\nmove and resize it. It\u2019ll contain any data that\nyou entered on the web page, and if you\nstarted it on the web page, it'll continue\nwhatever countdown it was running. There\nwill now be a new copy of Jtimer on the web\npage, which you can enter data into, start,\nand detach as you see fit. Every time you\ndetach a copy, it\u2019ll continue to perform\nwhatever it was performing on the web\npage, and a new copy will replace it on that\nWeb page. You can even detach copies of\nJtimer that you have not entered any data\ninto and use them when you wish.\n\nMAXIMIZING SCREEN SPACE:\n\nThis feature isn't applicable when Jtimer\nis embedded in a web page. To make only the\ntimer\u2019s digits visible, thus saving screen\nspace, grab the bottom right corner of\nthe Jtimer window with the mouse and move\nthe mouse inwards. You\u2019ll notice that the\ndigits remain visible while the other items of\nthe window disappear\n\nUSING A CUSTOM ALARM\n\nIf you're running Jtimer as an application,\nyou can specify your own sound file to be\nplayed when the timer reaches zero. You do\nso by specifying the name of the sound file\nas a parameter on the command line. Jtimer\nwill only accept sound files of \".au\"\nformat. Here's an example:\n\njava Jtimer MyOwnSoundFile.au\n\nCONTACTING CODEHOUSE:\n\nIf you have any further questions,\ncomments, or bugs to report, please feel free\nto contact us:\n\nsupport@codehouse.com\nwww.codehouse.com\n1-617-244-3867", null, 20, null, null);
            return;
        }
        if (source == this.btnAbout) {
            new MsgDlg("Jtimer -- About", "Jtimer Version 1.02\nCopyright (c) CodeHouse, 1997-1998\n\nCodeHouse\nJava(tm) consulting and tools\nwww.codehouse.com\n1-617-244-3867\n\nJtimer is free. You're invited to put\nJtimer on your web site or on your\ncomputer. If you'd like to do so, visit\nthe Jtimer section of our web site:\n\nwww.codehouse.com/jtimer\n\nPlease e-mail us if you have any\nquestions, comments or bugs to report:\nsolutions@codehouse.com\n");
            return;
        }
        if (source == this.btnImage) {
            new MsgDlg("Jtimer", "CodeHouse\nJava(tm) consulting and tools\nwww.codehouse.com\n1-617-244-3867\n");
            return;
        }
        if (source instanceof FTextField) {
            this.btnStartTimer_Clicked(null);
            return;
        }
        if (source == this.btnDetach) {
            if (this.btnDetach.getLabel().equals("Close")) {
                this.closeFrame((Frame)this.getParent());
                return;
            }
            new DetachFrame();
        }
    }
    
    void gbSet(final Container container, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints, final Component component, final int gridwidth) {
        gridBagConstraints.gridwidth = gridwidth;
        gridBagLayout.setConstraints(component, gridBagConstraints);
        container.add(component);
    }
    
    Panel getPanel3() {
        final Panel panel = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        final Label label = new Label("Notes:", 2);
        label.setForeground(MainContainer.TXT_FOREGROUND);
        this.setFont(new Font("Helvetica", 1, 12));
        this.gbSet(panel, layout, gridBagConstraints, label, 1);
        (this.tfNotes = new TextField()).setBackground(Color.white);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        this.gbSet(panel, layout, gridBagConstraints, this.tfNotes, 1);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        final Insets insets = gridBagConstraints.insets;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        (this.btnHelp = new Button("Help")).setFont(MainContainer.BTN_FONT);
        this.gbSet(panel, layout, gridBagConstraints, this.btnHelp, 1);
        gridBagConstraints.insets = insets;
        (this.btnAbout = new Button("About")).setFont(MainContainer.BTN_FONT);
        this.gbSet(panel, layout, gridBagConstraints, this.btnAbout, 0);
        return panel;
    }
    
    void addListeners() {
        this.ticker.addActionListener(this);
        this.btnStartTimer.addActionListener(this);
        this.btnPause.addActionListener(this);
        this.btnResume.addActionListener(this);
        this.btnHelp.addActionListener(this);
        this.btnReset.addActionListener(this);
        this.tfSecs.addActionListener(this);
        this.tfMins.addActionListener(this);
        this.tfHours.addActionListener(this);
        this.btnDetach.addActionListener(this);
        this.btnAbout.addActionListener(this);
        this.btnImage.addActionListener(this);
    }
    
    void ticker_TimerEvent(final ActionEvent actionEvent) {
        this.display.showTime(this.cTicks);
        if (this.cTicks == 0) {
            this.ticker.stop();
            this.btnPause.setEnabled(false);
            this.btnResume.setEnabled(false);
            this.btnReset.setEnabled(false);
            new MsgDlgAlarm(this.audio);
            this.stopAlarm();
            return;
        }
        --this.cTicks;
    }
    
    void stopAlarm() {
        this.tfsetEnabled(true);
        this.btnStartTimer.setEnabled(true);
        this.tfMins.requestFocus();
    }
    
    void btnReset_Clicked(final ActionEvent actionEvent) {
        this.ticker.stop();
        this.cTicks = 0;
        this.display.showTime(this.cTicks);
        this.tfsetEnabled(true);
        this.btnReset.setEnabled(false);
        this.btnPause.setEnabled(false);
        this.btnResume.setEnabled(false);
        this.btnStartTimer.setEnabled(true);
        this.tfMins.requestFocus();
    }
    
    void btnResume_Clicked(final ActionEvent actionEvent) {
        this.ticker.resume();
        this.btnResume.setEnabled(false);
        this.btnReset.requestFocus();
        this.btnPause.setEnabled(true);
    }
    
    void btnPause_Clicked(final ActionEvent actionEvent) {
        this.ticker.pause();
        this.btnResume.setEnabled(true);
        this.btnResume.requestFocus();
        this.btnPause.setEnabled(false);
    }
    
    void btnStartTimer_Clicked(final ActionEvent actionEvent) {
        final String trim = this.tfHours.getText().trim();
        final String trim2 = this.tfMins.getText().trim();
        final String trim3 = this.tfSecs.getText().trim();
        if (trim.length() == 0) {
            new MsgDlg("Jtimer", "There's no entry for the \"Hours\" field.\nEnter an amount of hours in the range of 0 - 99");
            this.tfHours.requestFocus();
            return;
        }
        if (trim2.length() == 0) {
            new MsgDlg("Jtimer", "There's no entry for the \"Minutes\" field.\nEnter an amount of minutes in the range of 0 - 59");
            this.tfMins.requestFocus();
            return;
        }
        if (trim3.length() == 0) {
            new MsgDlg("Jtimer", "The \"Seconds\" field is missing.\nEnter an amount of seconds in the range of 0 - 59");
            this.tfSecs.requestFocus();
            return;
        }
        final int int1 = Integer.parseInt(trim);
        final int int2 = Integer.parseInt(trim2);
        final int int3 = Integer.parseInt(trim3);
        if (int1 + int2 + int3 == 0) {
            new MsgDlg("Jtimer", "All the fields are zero.\nYou need to enter a desired countdown time");
            return;
        }
        if (int2 > 59) {
            new MsgDlg("Jtimer", "The \"Minutes\" field is invalid.\nEnter an amount of minutes in the range of 0 - 59");
            this.tfMins.requestFocus();
            return;
        }
        if (int3 > 59) {
            new MsgDlg("Jtimer", "The \"Seconds\" field is invalid.\nEnter an amount of seconds in the range of 0 - 59");
            this.tfSecs.requestFocus();
            return;
        }
        this.btnStartTimer.setEnabled(false);
        this.btnReset.setEnabled(true);
        this.btnReset.requestFocus();
        this.btnPause.setEnabled(true);
        this.tfsetEnabled(false);
        this.cTicks = int1 * 3600 + int2 * 60 + int3;
        this.ticker.start();
        this.display.showTime(this.cTicks);
        --this.cTicks;
    }
    
    void tfsetEnabled(final boolean enabled) {
        this.tfMins.setEnabled(enabled);
        this.tfSecs.setEnabled(enabled);
        this.tfHours.setEnabled(enabled);
    }
    
    static {
        MainContainer.s = "In the fields labeled \"Hours\" \n";
        BTN_FONT = new Font("Helvetica", 0, 12);
        TXT_FOREGROUND = Color.yellow;
        frameVec = new Vector();
    }
    
    class DetachFrame extends Frame implements WindowListener
    {
        boolean activated;
        
        DetachFrame() {
            Label_0129: {
                if (MainContainer.this.theParent != null) {
                    MainContainer.this.theParent.remove(MainContainer.this);
                    final Cursor cursor = MainContainer.this.theParent.getCursor();
                    MainContainer.this.theParent.setCursor(Cursor.getPredefinedCursor(3));
                    final MainContainer mainContainer2 = new MainContainer(MainContainer.this.rcAudioName);
                    MainContainer.this.theParent.setCursor(cursor);
                    MainContainer.this.theParent.add(mainContainer2, "Center");
                    MainContainer.this.theParent.validate();
                    synchronized (this) {
                        MainContainer.frameVec.addElement(this);
                        MainContainer.this.setFrameTitleNumber(this, MainContainer.frameVec.size());
                        break Label_0129;
                    }
                }
                this.setTitle("Jtimer");
            }
            this.setBackground(Color.black);
            MainContainer.this.btnDetach.setLabel("Close");
            this.addWindowListener(this);
            this.add(MainContainer.this, "Center");
            this.pack();
            this.setVisible(true);
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            MainContainer.this.closeFrame(this);
        }
        
        public void windowActivated(final WindowEvent windowEvent) {
            if (!this.activated) {
                MainContainer.this.tfMins.requestFocus();
                this.activated = true;
            }
        }
        
        public void windowOpened(final WindowEvent windowEvent) {
        }
        
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
        }
    }
    
    class FTextField extends TextField
    {
        public FTextField(final int n) {
            super(n);
            this.enableEvents(8L);
        }
        
        public void processKeyEvent(final KeyEvent keyEvent) {
            if (keyEvent.getID() == 401) {
                switch (keyEvent.getKeyCode()) {
                    default: {
                        keyEvent.consume();
                    }
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57: {
                        if (keyEvent.isShiftDown() || (this.getSelectedText().length() == 0 && this.getText().length() >= this.getColumns())) {
                            keyEvent.consume();
                            return;
                        }
                    }
                    case 8:
                    case 9:
                    case 10:
                    case 35:
                    case 36:
                    case 37:
                    case 39:
                    case 127: {
                        super.processKeyEvent(keyEvent);
                        break;
                    }
                }
            }
        }
    }
}
