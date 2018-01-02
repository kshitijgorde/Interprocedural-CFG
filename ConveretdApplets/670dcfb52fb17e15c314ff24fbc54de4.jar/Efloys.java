import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.awt.Color;
import java.awt.Event;
import java.awt.Dimension;
import java.applet.AppletContext;
import java.awt.Image;
import java.applet.AudioClip;
import java.util.Vector;
import java.util.Date;
import java.awt.Font;
import java.net.URL;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Efloys extends Applet implements Runnable
{
    Thread runner;
    EfloyCanvas canvas;
    EfloyCommand fcommand;
    EfloyInfo finfo;
    EfloyPredefined fpredefined;
    static Efloy[] Efloys;
    static Efloy[] NewFloys;
    Button Start;
    Button Pause;
    Button Rules;
    Button Info;
    Button Control;
    Button Kick;
    Button Slower;
    Button Faster;
    Button Scramble;
    Button Stranger;
    Button Breed;
    static Button Evolution;
    Button Quit;
    Button Help;
    Button Log;
    Button Predefined;
    Button Numbers;
    Button Sound;
    Button Ranges;
    Graphics gra;
    Panel ControlPanel;
    Panel center;
    Panel center1;
    Label MainTitle;
    URL MainPage;
    static EfloyParam[] params;
    static EfloyParam[] fixpars;
    static EfloyParam[] envpars;
    Font ButtonFont;
    Date StartDate;
    int wa;
    int ha;
    int wc;
    int hc;
    int CurrentBehavior;
    int CurrentNum;
    long CurrentStep;
    long CurrentTotalStep;
    static long CurrentGeneration;
    long CurrentRandom;
    long SumFitness;
    boolean running;
    static boolean First;
    static boolean ResetPopulation;
    static boolean NewGeneration;
    static boolean InEvolution;
    static boolean DrawNumbers;
    static boolean WithSound;
    static boolean InLog;
    static boolean LimitedRanges;
    static EfloyGeneration FloyGen;
    static Vector HistoryData;
    static int NF;
    static float KICK;
    static long SLEEP;
    static AudioClip joy;
    static AudioClip beep;
    static Image picture;
    static AppletContext appcontext;
    static EfloyLog deb;
    static EfloyLog helpwin;
    static EfloyLog rulewin;
    static Dimension ScreenSize;
    
    public boolean keyDown(final Event evt, final int key) {
        final char c = (char)key;
        if (c == '\u03f0') {
            Efloys.helpwin.toFront();
            return true;
        }
        if (c == '\u03f1') {
            Efloys.deb.toFront();
            return true;
        }
        if (c == '\u03f2') {
            Efloys.deb.clear();
            return true;
        }
        if (c == '\u03f3') {
            Efloys.InLog = false;
            return true;
        }
        if (c == '\u03f4') {
            return Efloys.InLog = true;
        }
        if (c == '\u03f5') {
            this.KickAll();
            return true;
        }
        return false;
    }
    
    static {
        Efloys.ResetPopulation = true;
        Efloys.WithSound = true;
        Efloys.InLog = true;
    }
    
    void KickAll() {
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            Efloys.Efloys[i].mutate();
        }
        Efloys.beep.play();
    }
    
    public int GetWinner() {
        int e = 0;
        int winner = 0;
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            Efloys.Efloys[i].GetFitness();
            if (Efloys.Efloys[i].fitness > e) {
                e = Efloys.Efloys[i].fitness;
                winner = i;
            }
        }
        return winner;
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        if (evt.target == this.Start) {
            this.showStatus("Restart the Floys population with identical default values");
        }
        if (evt.target == this.Scramble) {
            this.showStatus("Give a high dose of radiation, causing many mutations and diverse population");
        }
        if (evt.target == this.Pause) {
            this.showStatus("Freeze all movement. A second click will resume movement");
        }
        if (evt.target == Efloys.Evolution) {
            this.showStatus("Start/Stop a continuous process, where breeding occurs each time a stranger is killed");
        }
        if (evt.target == this.Rules) {
            this.showStatus("Display eFloys behavioral rules and the rules of the game");
        }
        if (evt.target == this.Info) {
            this.showStatus("Display Floys' traits, environmental properties and history data");
        }
        if (evt.target == this.Control) {
            this.showStatus("Modify individual or global properies - assign different colors to modified Floys");
        }
        if (evt.target == this.Slower) {
            this.showStatus("Define a slower Floy movement - click multiple time to get the desired effect");
        }
        if (evt.target == this.Faster) {
            this.showStatus("Define a faster Floy movement - click multiple time to get the desired effect");
        }
        if (evt.target == this.Stranger) {
            this.showStatus("Release a red intruder into the territory, and local Floys will chase and attack him");
        }
        if (evt.target == this.Breed) {
            this.showStatus("Create a new generation, where each new Floy is the descendant of two old parents");
        }
        if (evt.target == this.Help) {
            this.showStatus("Display help screen. Close the help screen by clicking its top right X icon");
        }
        if (evt.target == this.Log) {
            this.showStatus("Display Log screen (log notes of current session). Close by clicking top right X icon");
        }
        if (evt.target == this.Predefined) {
            this.showStatus("Select a set of predefined properties that define a specific environment and/or population");
        }
        if (evt.target == this.Numbers) {
            this.showStatus("Toggle between display of Floys (fish like) shape and Floys identity numbers");
        }
        if (evt.target == this.Sound) {
            this.showStatus("Turn Off or On the sound effects (e.g. the sigh of a dying Floy)");
        }
        if (evt.target == this.Ranges) {
            this.showStatus("Limit/Reset variation ranges of properties during evolution");
        }
        return true;
    }
    
    static boolean Flip(final float ref) {
        final float rand = (float)Math.random();
        return rand < ref;
    }
    
    public void CreateNewGeneration() {
        this.RecordGeneration();
        ++Efloys.CurrentGeneration;
        this.PreScale();
        this.Breed();
        this.ReleaseStranger();
        Efloys.NewGeneration = false;
        this.CurrentStep = 0L;
        this.showStatus("Generation #" + Efloys.CurrentGeneration);
    }
    
    private int GetParentID() {
        int i = 0;
        int partsum = 0;
        final int n = Efloys.Efloys.length;
        final int rand = (int)(Math.random() * this.SumFitness);
        final int avg = (int)(this.SumFitness / n);
        int j = 0;
        for (i = 0; i < Efloys.Efloys.length; ++i) {
            Efloys.Efloys[i].GetFitness();
            partsum += Efloys.Efloys[i].fitness;
            if (partsum >= rand) {
                j = 1;
                break;
            }
        }
        if (j == 0) {
            i = Efloys.Efloys.length - 1;
        }
        return Efloys.Efloys[i].id;
    }
    
    void UpdateFloysParams() {
        for (int size = Efloys.Efloys[0].PopulationSize, i = 0; i < size; ++i) {
            Efloys.Efloys[i].params = Efloys.params;
            Efloys.Efloys[i].fixpars = Efloys.fixpars;
            Efloys.Efloys[i].envpars = Efloys.envpars;
            final String st = EncodeChrom(Efloys.params);
            final String fx = EncodeChrom(Efloys.fixpars);
            final String ev = EncodeChrom(Efloys.envpars);
            Efloys.Efloys[i] = new Efloy(Efloys.params, st, Efloys.fixpars, fx, Efloys.envpars, ev);
            Efloys.Efloys[i].id = i;
        }
    }
    
    public void ReleaseStranger() {
        final int num = this.GetFloyNumber(0);
        Efloys.Efloys[num].type = 1;
        Efloys.Efloys[num].color = Color.red;
        Efloys.Efloys[num].energy = Efloys.Efloys[num].LifeSpan;
        Efloys.Efloys[num].safety = 10;
        Efloys.Efloys[num].cooperation = 10;
        Efloys.Efloys[num].GetFitness();
        Efloys.Efloys[num].x = this.canvas.size().width;
        Efloys.Efloys[num].y = 0.0f;
        Efloys.Efloys[num].DistLocalFactor = 0.0f;
        Efloys.Efloys[num].CollisionLocalFactor = -40.0f;
        Efloys.Efloys[num].CenterAcceleration = 0.1f;
    }
    
    public boolean action(final Event evt, final Object o) {
        boolean rt = true;
        if (evt.target == this.Quit) {
            this.showStatus("Quit");
            try {
                this.MainPage = new URL("Javafloys.html");
                Efloys.appcontext.showDocument(this.MainPage);
            }
            catch (MalformedURLException ex) {}
            rt = true;
        }
        else if (evt.target == this.Pause) {
            if (this.running) {
                this.runner.suspend();
                this.running = false;
                this.Pause.setLabel("Continue");
            }
            else {
                this.runner.resume();
                this.running = true;
                this.Pause.setLabel("Pause");
            }
            rt = true;
        }
        else if (evt.target == Efloys.Evolution) {
            if (Efloys.InEvolution) {
                Efloys.Evolution.setLabel("Start Evolution");
                Efloys.InEvolution = false;
                Efloys.NewGeneration = false;
                this.showStatus("");
            }
            else {
                Efloys.Evolution.setLabel("Stop Evolution");
                Efloys.InEvolution = true;
                Efloys.NewGeneration = true;
                this.showStatus("");
            }
            rt = true;
        }
        else if (evt.target == this.Info) {
            this.MainTitle.setForeground(Color.blue);
            final String OldTitle = this.MainTitle.getText();
            this.MainTitle.setText("Loading Info Screen, please wait..");
            this.MainTitle.resize(this.MainTitle.preferredSize());
            this.showStatus("Loading Info Screen, please wait..");
            this.ResizeFrame(this.finfo = new EfloyInfo(Efloys.Efloys));
            this.MainTitle.setText(OldTitle);
            this.MainTitle.setForeground(Color.black);
        }
        else if (evt.target == this.Control) {
            this.MainTitle.setForeground(Color.blue);
            final String OldTitle = this.MainTitle.getText();
            this.MainTitle.setText("Loading Properties Screen, please wait..");
            this.MainTitle.resize(this.MainTitle.preferredSize());
            this.showStatus("Loading Control Screen, please wait..");
            this.ResizeFrame(this.fcommand = new EfloyCommand(Efloys.Efloys));
            this.MainTitle.setText(OldTitle);
            this.MainTitle.setForeground(Color.black);
        }
        else if (evt.target == this.Stranger) {
            this.ReleaseStranger();
        }
        else if (evt.target == this.Start) {
            if (!this.running) {
                this.runner.resume();
                this.running = true;
                this.Pause.setLabel("Pause");
            }
            Efloys.deb.clear();
            Efloys.deb.start();
            this.InitParamsExtended();
            Efloys.InEvolution = false;
            Efloys.NewGeneration = false;
            this.CurrentStep = 0L;
            this.CurrentTotalStep = 0L;
            Efloys.CurrentGeneration = 0L;
            this.Restart(Efloys.ResetPopulation = true);
        }
        else if (evt.target == this.Scramble) {
            if (Efloys.WithSound) {
                Efloys.beep.play();
            }
            this.scramble();
        }
        else if (evt.target == this.Breed) {
            if (Efloys.WithSound) {
                Efloys.beep.play();
            }
            this.CreateNewGeneration();
        }
        else if (evt.target == this.Slower) {
            if (Efloys.SLEEP < 10L) {
                ++Efloys.SLEEP;
            }
            else if (Efloys.SLEEP < 150L) {
                Efloys.SLEEP += 10L;
            }
        }
        else if (evt.target == this.Faster) {
            if (Efloys.SLEEP > 10L) {
                Efloys.SLEEP -= 10L;
            }
            else if (Efloys.SLEEP > 1L) {
                --Efloys.SLEEP;
            }
        }
        else if (evt.target == this.Help) {
            Efloys.helpwin.show();
            rt = true;
        }
        else if (evt.target == this.Rules) {
            Efloys.rulewin.show();
            rt = true;
        }
        else if (evt.target == this.Log) {
            Efloys.deb.show();
            rt = true;
        }
        else if (evt.target == this.Predefined) {
            this.ResizeFrame(this.fpredefined = new EfloyPredefined());
            rt = true;
        }
        else if (evt.target == this.Numbers) {
            if (Efloys.DrawNumbers) {
                this.Numbers.setLabel("Show Numbers");
                Efloys.DrawNumbers = false;
            }
            else {
                this.Numbers.setLabel("Show Shapes");
                Efloys.DrawNumbers = true;
            }
            rt = true;
        }
        else if (evt.target == this.Sound) {
            if (Efloys.WithSound) {
                this.Sound.setLabel("Turn Sound On");
            }
            else {
                this.Sound.setLabel("Turn Sound Off");
            }
            Efloys.WithSound = !Efloys.WithSound;
            rt = true;
        }
        else if (evt.target == this.Ranges) {
            if (Efloys.LimitedRanges) {
                this.Ranges.setLabel("Limited Ranges");
                this.InitParamsExtended();
                this.UpdateFloysParams();
                Efloys.LimitedRanges = false;
            }
            else {
                this.Ranges.setLabel("Normal Ranges");
                this.InitParamsLimited();
                this.UpdateFloysParams();
                Efloys.LimitedRanges = true;
            }
            rt = true;
        }
        else {
            rt = true;
        }
        return rt;
    }
    
    void CreateRules(final EfloyLog win) {
        win.ta.setForeground(Color.black);
        win.ta.setFont(new Font("TimesRoman", 1, 12));
        win.clear();
        String txt = "";
        txt += "The instinctive behavioral rules of eFloys\n";
        txt += "\n";
        txt += "eFloys' behavior is governed by two rules:\n";
        txt += "\t1. A rule specifying how to relate to one's own kind.\n";
        txt += "\t2. A rule specifying how to relate to strangers.\n";
        txt += "\n";
        txt += "1. How to relate to one's own kind:\n";
        txt += "\tIdentify two members of your flock that are near to you and try to stay close to them, but not too close.\n";
        txt += "\n";
        txt += "2. How to relate to strangers:\n";
        txt += "\tIf you are in your territory:\n";
        txt += "\t\tWhen you spot a stranger go after him, if you are close enough - attack\n";
        txt += "\tIf you are not in your territory:\n";
        txt += "\t\tIf local Floys chase you - run away.\n";
        txt += "\n\n";
        txt += "Rewards and Penalties\n";
        txt += "(The fitness is calculated as a weighted function of total energy and safety)\n";
        txt += "\n";
        txt += "Biting a stranger:\t\t\t Energy increase\n";
        txt += "Moving fast:\t\t\t Energy decrease\n";
        txt += "Being bitten by a local eFloy:\t\t Energy decrease\n";
        txt += "Keeping close to one's neightbors:\t Safety increase\n";
        txt += "\n\n";
        txt += "How to play with eFloys\n";
        txt += "\n";
        txt += "1. Adjust overall movement speed (if necessary)\n";
        txt += "\tThe default movement should be smooth and calm\n";
        txt += "\tClick the 'Slower' and/or 'Faster' buttons several times to define a smooth movement\n";
        txt += "\n";
        txt += "2. Play with Population\n";
        txt += "\n";
        txt += "\tInsert a stranger and watch how eFloys chase and attack him\n";
        txt += "\tThe poor stranger's sigh means he is finally eliminated\n";
        txt += "\n";
        txt += "\tGo to 'Edit Properties' and modify the environment or the traits\n";
        txt += "\tClick 'UpdateAll' to apply the new traits to all members of the population\n";
        txt += "\t(Optionally) Insert a stranger\n";
        txt += "\n";
        txt += "\tGo to 'Edit Properties' and assign traits to a specific eFloy (or eFloys)\n";
        txt += "\tRemember to assign each modified eFloy a different color, so that he will be easy to follow\n";
        txt += "\tClick 'Update' for each eFloy.\n";
        txt += "\t(Optionally) Insert a stranger\n";
        txt += "\n";
        txt += "\n";
        txt += "3. Play with Evolution\n";
        txt += "\n";
        txt += "\tDefine the fitness function\n";
        txt += "\tGo to 'Edit Properties' and assign Energy and Safety Weight Factors\n";
        txt += "\tIf Energy weight is higher, eFloys will evolve to be braver and faster\n";
        txt += "\tIf Safety weight is higher, eFloys will evolve to be more cautious and they will keep together\n";
        txt += "\n";
        txt += "\tStart with a diverse, random population\n";
        txt += "\tClick 'Scramble' several times. Each click will make the population more diverse\n";
        txt += "\tClick 'Start Evolution'\n";
        txt += "\n";
        txt += "\tor Start with a homogeneous population\n";
        txt += "\tUse default population, or one you defines by 'UpdateAll' in 'Edit Properties'\n";
        txt += "\tIn this case evolution will be slower, because it can build only on mutation\n";
        txt += "\tClick 'Start Evolution'\n";
        txt += "\n";
        txt += "\tor Start with controlled population\n";
        txt += "\tUse a population that you defined previously by assigning each eFloy his own specific traits\n";
        txt += "\tClick 'Start Evolution'\n";
        txt += "\n";
        txt += "\n";
        txt += "4. How to use the buttons\n";
        txt += "\tClick on any button and see what it does\n";
        txt += "\tor look in the Help screen (click 'Show Help')\n";
        txt += "\n";
        win.showMsg(txt);
    }
    
    public void destroy() {
        Efloys.deb.dispose();
        Efloys.helpwin.dispose();
        Efloys.rulewin.dispose();
    }
    
    public void init() {
        this.ButtonFont = new Font("TimesRoman", 0, 12);
        (Efloys.deb = new EfloyLog("Log Window")).hide();
        Efloys.deb.showMsg("");
        Efloys.deb.reshape(50, 40, 540, 380);
        Efloys.deb.start();
        (Efloys.helpwin = new EfloyLog("Help Window")).hide();
        Efloys.helpwin.showMsg("");
        final Dimension d = Efloys.ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = d.width;
        final int h = d.height;
        Efloys.helpwin.reshape(0, 0, w - 2, h - 2);
        this.CreateHelp(Efloys.helpwin);
        (Efloys.rulewin = new EfloyLog("Behavioral Rules of eFloys")).hide();
        Efloys.rulewin.showMsg("");
        Efloys.rulewin.reshape(2, 2, w - 2, h - 2);
        this.CreateRules(Efloys.rulewin);
        this.setLayout(new BorderLayout());
        final Panel bot = new Panel();
        this.Start = new Button(" Restart ");
        this.Scramble = new Button(" Scramble ");
        this.Stranger = new Button(" Insert Stranger ");
        this.Breed = new Button(" Breed ");
        Efloys.Evolution = new Button(" Start Evolution ");
        this.Quit = new Button(" Quit ");
        bot.setFont(this.ButtonFont);
        bot.add(this.Start);
        bot.add(this.Scramble);
        bot.add(this.Breed);
        bot.add(Efloys.Evolution);
        bot.add(this.Stranger);
        this.add("South", bot);
        this.Numbers = new Button("Show Numbers");
        this.Sound = new Button("Turn Sound Off");
        this.Slower = new Button("Move Slower");
        this.Faster = new Button("Move Faster");
        this.Pause = new Button("Pause");
        this.Ranges = new Button("Limited Ranges");
        this.Help = new Button("Show Help");
        this.Log = new Button("Show Log");
        this.Predefined = new Button("Predefined");
        this.Control = new Button("Edit Properties");
        this.Info = new Button("Show Info");
        this.Rules = new Button("Show Rules");
        final Panel west = new Panel();
        west.setLayout(new GridLayout(6, 1));
        west.setFont(this.ButtonFont);
        west.add(this.Numbers);
        west.add(this.Sound);
        west.add(this.Slower);
        west.add(this.Faster);
        west.add(this.Pause);
        west.add(this.Ranges);
        this.add("West", west);
        final Panel east = new Panel();
        east.setLayout(new GridLayout(6, 1));
        east.setFont(this.ButtonFont);
        east.add(this.Help);
        east.add(this.Rules);
        east.add(this.Log);
        east.add(this.Predefined);
        east.add(this.Info);
        east.add(this.Control);
        this.add("East", east);
        final Panel north = new Panel();
        final Font BoldFont = new Font("TimesRoman", 1, 12);
        north.setFont(BoldFont);
        (this.MainTitle = new Label("The eFloys Aquarium")).setForeground(Color.black);
        north.add(this.MainTitle);
        this.add("North", north);
        Efloys.appcontext = this.getAppletContext();
        Efloys.picture = this.getImage(this.getCodeBase(), "Efloys1.gif");
        Efloys.joy = this.getAudioClip(this.getCodeBase(), "joy.au");
        Efloys.beep = this.getAudioClip(this.getCodeBase(), "Beep.au");
        this.add("Center", this.canvas = new EfloyCanvas(Color.red));
        this.canvas.repaint();
        this.gra = this.canvas.GetGra();
        (this.center = new Panel()).setLayout(new GridLayout(2, 2));
        this.center.add(new Button("B1"));
        this.center.add(new Button("B2"));
        this.center.add(new Button("B3"));
        this.center.add(new Button("B4"));
        (this.center1 = new Panel()).setLayout(new GridLayout(2, 2));
        this.center1.add(new Button("B1a"));
        this.center1.add(new Button("B2a"));
        this.center1.add(new Button("B3a"));
        this.center1.add(new Button("B4a"));
        Efloys.HistoryData = new Vector();
        this.resize(620, 250);
        this.CurrentBehavior = 0;
        Efloys.First = true;
    }
    
    private void Breed() {
        this.SumFitness = this.GetSumFitness();
        Efloys.NewFloys = new Efloy[Efloys.Efloys.length];
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            Efloys.NewFloys[i] = this.Mate();
            Efloys.NewFloys[i].id = i;
        }
        if (Efloys.Efloys[0].SurviversFactor > 0.0f) {
            this.SortFloys(Efloys.Efloys);
            this.SortFloys(Efloys.NewFloys);
            final int n = (int)(Efloys.Efloys.length * Efloys.Efloys[0].SurviversFactor / 100.0f);
            for (int i = 0; i < Efloys.Efloys.length; ++i) {
                if (i > n - 1) {
                    Efloys.Efloys[i] = Efloys.NewFloys[i - n];
                }
            }
        }
        else {
            for (int i = 0; i < Efloys.Efloys.length; ++i) {
                Efloys.Efloys[i] = Efloys.NewFloys[i];
            }
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
            this.running = true;
            this.CurrentStep = 0L;
            this.CurrentTotalStep = 0L;
            this.CurrentRandom = 0L;
            Efloys.CurrentGeneration = 0L;
        }
    }
    
    public void Restart(final boolean Reset) {
        if (Reset) {
            Efloys.HistoryData.removeAllElements();
            Efloys.InEvolution = false;
            Efloys.NewGeneration = false;
            this.CurrentStep = 0L;
            this.CurrentTotalStep = 0L;
            Efloys.CurrentGeneration = 0L;
            Efloys.ResetPopulation = true;
            this.stop();
            Efloys.First = true;
            this.showStatus("");
            this.start();
        }
        if (Efloys.ResetPopulation) {
            Efloys.Efloys = new Efloy[Efloys.NF];
            for (int i = 0; i < Efloys.Efloys.length; ++i) {
                this.CurrentNum = i;
                final String st = EncodeChrom(Efloys.params);
                final String fx = EncodeChrom(Efloys.fixpars);
                final String ev = EncodeChrom(Efloys.envpars);
                Efloys.Efloys[i] = new Efloy(Efloys.params, st, Efloys.fixpars, fx, Efloys.envpars, ev);
                Efloys.Efloys[i].id = i;
            }
            Efloys.ResetPopulation = false;
        }
    }
    
    void CreateHelp(final EfloyLog win) {
        win.ta.setForeground(Color.black);
        win.ta.setFont(new Font("TimesRoman", 1, 12));
        win.clear();
        String txt = "";
        txt += "Contents\n\n";
        txt += "1.\t Button Functions\n";
        txt += "2.\t Properties\n";
        txt += "3.\t Keyboard Functions\n";
        txt += "4.\t Differences between Netscape and Microsoft Internet Explorer\n";
        txt += "\n\n";
        txt += "Button Functions - Bottom Button Bar\n\n";
        txt += "Restart:\t\t Restart the Floys population with identical default values\n";
        txt += "Scramble:\t\t Give a high dose of radiation, causing many mutations and diverse population\n";
        txt += "Breed:\t\t Create a new generation, where each new Floy is the descendant of two old parents\n";
        txt += "Evolution:\t\t Start/Stop a continuous process, where breeding occurs each time a stranger is killed\n";
        txt += "Insert Stranger:\t Release a red intruder into the territory, and local Floys will chase and attack him\n";
        txt += "\nButton Functions - Right Button Bar\n\n";
        txt += "Show Help:\t Display help screen. Close the help screen by clicking its top right X icon\n";
        txt += "Show Log Screen:\t Display Log screen (log notes of current session). Close by clicking top right X icon\n";
        txt += "Predefined:\t\t Select a set of predefined properties that define a specific environment and/or population\n";
        txt += "Show Rules:\t\t Display eFloys behavioral rules, and the rules of the game\n";
        txt += "Show Info:\t Display Floys' traits, environmental properties and history data\n";
        txt += "Edit Properties:\t Modify individual or global properies - assign different colors to modified Floys\n";
        txt += "\nButton Functions - Left Button Bar\n\n";
        txt += "Show Numbers:\t Toggle between display of Floys (fish like) shape and Floys identity numbers\n";
        txt += "Turn Sound Off:\t Turn Off or On the sound effects (e.g. the sigh of a dying Floy)\n";
        txt += "Slower:\t\t Define a slower Floy movement - click multiple time to get the desired effect\n";
        txt += "Faster:\t\t Define a faster Floy movement - click multiple time to get the desired effect\n";
        txt += "Pause:\t\t Freeze all movement. A second click will resume movement\n";
        txt += "Limited Ranges:\t\t Limit/Reset variation ranges for critical properties\n";
        txt += "\n\nDescription of Properties (Units are arbitrary and have no meaning)\n\n";
        txt += "Primary Traits:\t The main behavioral traits, affected by evolutionary operators (crossover and mutation)\n";
        txt += "Secondary Traits:\t The specific responses to various inputs, affected by the evolutionary operators\n";
        txt += "Fixed traits:\t Various individual traits, not affected by the evolutionary operators\n";
        txt += "Environmental:\t These are properties of the environment, and are shared by the whole community\n";
        txt += "\n";
        txt += "Primary Traits\n\n";
        txt += "Maximum Speed:\t The maximum speed a Floy can reach\n";
        txt += "Bounce Speed:\t The speed of turning away when hitting a wall\n";
        txt += "Acceleration:\t The basic acceleration of response to various inputs\n";
        txt += "Attract to Center:\t The force by which a Floy is attracted to the center of the territory\n";
        txt += "Collision Distance:\t The distance (squared) at which a Floy can touch another\n";
        txt += "\n";
        txt += "Secondary Traits:\n\n";
        txt += "Response (speed change) to a distant Floy\n";
        txt += "Local to Local:\t A Floy's response to a member of its own community\n";
        txt += "Local to Stranger:\t How a Floy in his own territory responds to a foreigner\n";
        txt += "Stranger to Local:\t How a Floy not in his own territory responds to local Floys\n";
        txt += "\n";
        txt += "Response (speed change) to a close Floy (at touching distance)\n";
        txt += "Local to Local:\t A Floy's response to a member of its own community\n";
        txt += "Local to Stranger:\t How a Floy in his own territory responds to a foreigner\n";
        txt += "Stranger to Local:\t How a Floy not in his own territory responds to local Floys\n";
        txt += "\n";
        txt += "Fixed Traits\n\n";
        txt += "Type:\t\t Either local or stranger. By default, any Floy with a red color is a stranger\n";
        txt += "Color:\t\t The Floy's color, useful for observing and following specific Floys\n";
        txt += "No. of Neighbors:\t The number of Floys to relate to\n";
        txt += "Mutation Factor:\t The probability for a gene to be changed by mutation\n";
        txt += "Crossover Factor:\t The probability for crossover in the breeding process (in general should be 1)\n";
        txt += "Energy Level:\t A dynamic attribute, affected by interaction with strangers\n";
        txt += "Safety Level:\t A dynamic attribute, affected by interaction with one's neighbors\n";
        txt += "Cooperation Level:\t A previous-generation-dependent attribute, affected by community achievements\n";
        txt += "Fitness Level:\t Overall fitness calculated as a weighted function of the above\n";
        txt += "\n";
        txt += "Environmental Properties\n\n";
        txt += "Population Size:\t The number of Floys in the community\n";
        txt += "Slowdown Factor:\t A delay factor for adjusting the overall movement speed\n";
        txt += "Energy Factor:\t The weighting factor of energy in the fitness function\n";
        txt += "Safety Factor:\t The weighting factor of safety in the fitness function\n";
        txt += "Cooperation Factor:\t The weighting factor of cooperation in the fitness function\n";
        txt += "Max. Energy Dose:\t Maximum energy points (reward or penalty) an eFloy can get or lose\n";
        txt += "Max. Safety Dose:\t Maximum safety points (reward or penalty) an eFloy can get or lose\n";
        txt += "Max. Coop, Dose:\t Maximum cooperation points (reward or penalty) an eFloy can get or lose\n";
        txt += "Survivers Percent:\t The percentage of parents that are allowed to continue in the new generation\n";
        txt += "Free Will Factor:\t The amount of randomal behavior \n";
        txt += "Stranger Life Span:\t The energy given to a stranger at birth, defining its life span.\n";
        txt += "\n\nKeyboard functions\n";
        txt += "Available when the applet has focus: Click anywhere in the applet area to give it focus\n\n";
        txt += "F1:\t Display help screen (this screen)\n";
        txt += "F2:\t Display Log Screen\n";
        txt += "F3:\t Reset Log Screen (Clear log file)\n";
        txt += "F4:\t Stop Log (Stop writing to log file)\n";
        txt += "F5:\t Restart Log (Resume writing to log file)\n";
        txt += "F6:\t Give a gentle kick (a gentle mutation, unlike the Scramble)\n";
        txt += "\n\nDifferences between Netscape and Microsoft Internet Explorer\n\n";
        txt += "Netscape 3.0 and Explorer 3.0 browsers (for Windows 95) behave differently in some respects\n";
        txt += "Here are a few points that I noticed, and are relevant to the Floys applet\n";
        txt += "\n";
        txt += "1. The applet runs faster on Explorer. Adjust by the Slower and Faster buttons (clicking several times)\n";
        txt += "2. Context sensitive help for each button is displayed in Netscape's status bar but not in Explorer's\n";
        txt += "3. Scrollbars (in the Info screen) behave differrently in the two browsers\n";
        txt += "4. The Help and Log screens are closed in Netscape, but not in Explorer. You can minimize them instead\n";
        txt += "5. Colored labels are displayed colored in Netscape, black in Explorer\n";
        txt += "\n";
        win.showMsg(txt);
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
            this.running = false;
        }
    }
    
    private int GetFloyNumber(final int fid) {
        int num = -1;
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            if (Efloys.Efloys[i].id == fid) {
                num = i;
            }
        }
        return num;
    }
    
    public void CenterFrame(final Frame frame) {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = d.width;
        final int h = d.height;
        final int fw = 640;
        final int fh = 480;
        final int dw = (w - fw) / 2;
        final int dh = (h - fh) / 1;
        frame.move(dw, dh);
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        this.showStatus("");
        return true;
    }
    
    public void scramble() {
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            Efloys.Efloys[i].shuffle(false, 0.3f);
        }
        this.GetSumFitness();
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            if (Efloys.Efloys[i].type == 1) {
                Efloys.Efloys[i].energy = Efloys.Efloys[i].LifeSpan;
            }
        }
    }
    
    public void ResizeFrame(final Frame frame) {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = d.width;
        final int h = d.height;
        frame.reshape(0, 0, w, h);
        frame.show();
    }
    
    void SortFloys() {
        for (int i = 0; i < Efloys.Efloys.length - 1; ++i) {
            boolean swap = false;
            for (int j = Efloys.Efloys.length - 2; j >= i; --j) {
                Efloys.Efloys[j].GetFitness();
                Efloys.Efloys[j + 1].GetFitness();
                final int e = Efloys.Efloys[j].fitness - Efloys.Efloys[j + 1].fitness;
                if (e < 0) {
                    final Efloy etemp = Efloys.Efloys[j];
                    Efloys.Efloys[j] = Efloys.Efloys[j + 1];
                    Efloys.Efloys[j + 1] = etemp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }
    
    void SortFloys(final Efloy[] floys) {
        for (int i = 0; i < floys.length - 1; ++i) {
            boolean swap = false;
            for (int j = floys.length - 2; j >= i; --j) {
                Efloys.Efloys[j].GetFitness();
                Efloys.Efloys[j + 1].GetFitness();
                final int e = floys[j].fitness - floys[j + 1].fitness;
                if (e < 0) {
                    final Efloy etemp = floys[j];
                    floys[j] = floys[j + 1];
                    floys[j + 1] = etemp;
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }
        }
    }
    
    static String EncodeChrom(final EfloyParam[] pars) {
        final StringBuffer sb = new StringBuffer(pars.length);
        for (int i = 0; i < pars.length; ++i) {
            final char kar = pars[i].EncodeValue();
            sb.append(kar);
        }
        final String st = sb.toString();
        return st;
    }
    
    public void randemize() {
        ++this.CurrentRandom;
        for (int k = 0; k < Efloys.Efloys.length; ++k) {
            final Efloy ng = Efloys.Efloys[k];
            for (int j = 0; j < ng.NumberOfNeighbors; ++j) {
                final int n = (int)(Math.random() * (Efloys.Efloys.length - 1));
                ng.neighbors[j] = Efloys.Efloys[n];
            }
        }
    }
    
    float GetOneParameter(final String name, final float def) {
        final String s = this.getParameter(name);
        float FloatPar;
        if (s == null) {
            FloatPar = def;
        }
        else {
            final Float temp = new Float(s);
            FloatPar = temp;
        }
        return FloatPar;
    }
    
    void InitParamsLimited() {
        (Efloys.params = new EfloyParam[12])[0] = new EfloyParam(1.0f, 11.0f, 0.5f, 5.0f, "MaxSpeed", true);
        Efloys.params[1] = new EfloyParam(0.2f, 2.2f, 0.1f, 0.8f, "BounceSpeed", true);
        Efloys.params[2] = new EfloyParam(0.1f, 1.1f, 0.05f, 0.3f, "ApproachAcceleration", true);
        Efloys.params[3] = new EfloyParam(0.1f, 1.1f, 0.05f, 0.3f, "RetreatAcceleration", true);
        Efloys.params[4] = new EfloyParam(0.0f, 0.2f, 0.01f, 0.1f, "CenterAcceleration", true);
        Efloys.params[5] = new EfloyParam(0.0f, 10.0f, 0.5f, 1.0f, "DistBrotherFactor", true);
        Efloys.params[6] = new EfloyParam(0.0f, 50.0f, 2.5f, 20.0f, "DistStrangerFactor", true);
        Efloys.params[7] = new EfloyParam(-50.0f, 50.0f, 0.5f, 0.0f, "DistLocalFactor", true);
        Efloys.params[8] = new EfloyParam(0.0f, 500.0f, 25.0f, 200.0f, "CollisionDistance", true);
        Efloys.params[9] = new EfloyParam(-10.0f, 0.0f, 0.5f, -1.0f, "CollisionBrotherFactor", true);
        Efloys.params[10] = new EfloyParam(0.0f, 100.0f, 5.0f, 30.0f, "CollisionStrangerFactor", true);
        Efloys.params[11] = new EfloyParam(-100.0f, 0.0f, 5.0f, -40.0f, "CollisionLocalFactor", true);
        (Efloys.fixpars = new EfloyParam[12])[0] = new EfloyParam(1.0f, 100.0f, 1.0f, this.CurrentNum, "id", false);
        Efloys.fixpars[1] = new EfloyParam(1.0f, 100.0f, 1.0f, 0.0f, "father", false);
        Efloys.fixpars[2] = new EfloyParam(1.0f, 100.0f, 1.0f, 0.0f, "mother", false);
        Efloys.fixpars[3] = new EfloyParam(0.0f, 10.0f, 1.0f, 0.0f, "type", false);
        Efloys.fixpars[4] = new EfloyParam(0.0f, 13.0f, 1.0f, 5.0f, "color", false);
        Efloys.fixpars[5] = new EfloyParam(1.0f, 10.0f, 1.0f, 2.0f, "NumberOfNeighbors", false);
        Efloys.fixpars[6] = new EfloyParam(0.0f, 0.2f, 0.01f, 0.1f, "MutationFactor", false);
        Efloys.fixpars[7] = new EfloyParam(0.0f, 1.0f, 0.1f, 1.0f, "CrossoverFactor", false);
        Efloys.fixpars[8] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "energy", true);
        Efloys.fixpars[9] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "safety", true);
        Efloys.fixpars[10] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "cooperation", true);
        Efloys.fixpars[11] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "fitness", true);
        Efloys.envpars = new EfloyParam[15];
        final float h = this.canvas.size().height;
        final float w = this.canvas.size().width;
        Efloys.envpars[0] = new EfloyParam(100.0f, 1000.0f, 10.0f, w, "width", false);
        Efloys.envpars[1] = new EfloyParam(100.0f, 1000.0f, 10.0f, h, "height", false);
        Efloys.envpars[2] = new EfloyParam(1.0f, 10.0f, 1.0f, 4.0f, "v0", false);
        Efloys.envpars[3] = new EfloyParam(5.0f, 50.0f, 5.0f, 10.0f, "sleep", false);
        Efloys.envpars[4] = new EfloyParam(0.0f, 50.0f, 5.0f, 30.0f, "margin", false);
        Efloys.envpars[5] = new EfloyParam(0.0f, 10.0f, 1.0f, 1.0f, "EnergyFactor", true);
        Efloys.envpars[6] = new EfloyParam(0.0f, 10.0f, 1.0f, 1.0f, "SafetyFactor", true);
        Efloys.envpars[7] = new EfloyParam(0.0f, 10.0f, 1.0f, 0.0f, "CooperationFactor", true);
        Efloys.envpars[8] = new EfloyParam(0.0f, 100.0f, 10.0f, 0.0f, "SurviversFactor", true);
        Efloys.envpars[9] = new EfloyParam(-10.0f, 10.0f, 1.0f, 1.0f, "MaxEnergyDose", true);
        Efloys.envpars[10] = new EfloyParam(-10.0f, 10.0f, 1.0f, 1.0f, "MaxSafetyDose", true);
        Efloys.envpars[11] = new EfloyParam(-10.0f, 10.0f, 1.0f, 1.0f, "MaxCooperationDose", true);
        Efloys.envpars[12] = new EfloyParam(5.0f, 50.0f, 5.0f, 15.0f, "PopulationSize", true);
        Efloys.envpars[13] = new EfloyParam(0.01f, 0.1f, 0.01f, 0.05f, "FreeWillFactor", true);
        Efloys.envpars[14] = new EfloyParam(10.0f, 200.0f, 10.0f, 50.0f, "LifeSpan", true);
    }
    
    public void run() {
        if (Efloys.First) {
            this.InitParamsExtended();
            this.GetParameters();
            this.Restart(false);
            this.randemize();
            Efloys.First = false;
        }
        while (true) {
            if (Efloys.First) {
                this.Restart(false);
            }
            Efloys.First = false;
            ++this.CurrentStep;
            ++this.CurrentTotalStep;
            if (Math.random() < Efloys.KICK) {
                this.randemize();
            }
            if ((Efloys.InEvolution && Efloys.NewGeneration) || (Efloys.InEvolution && this.CurrentStep > 5000L)) {
                this.StartDate = new Date();
                Efloys.deb.showMsg("New Generation #" + (Efloys.CurrentGeneration + 1L) + " started at " + this.StartDate.toLocaleString());
                this.CreateNewGeneration();
            }
            this.canvas.repaint();
            try {
                Thread.sleep(Efloys.SLEEP);
            }
            catch (InterruptedException e) {
                this.showStatus("Thread.sleep InterruptedException " + e.toString());
            }
        }
    }
    
    private void PreScale() {
        double tmin = 1.0E36;
        double tmax = -1.0E36;
        double tsum = 0.0;
        final int size = Efloys.Efloys[0].PopulationSize;
        for (int i = 0; i < size; ++i) {
            Efloys.Efloys[i].GetFitness();
            if (Efloys.Efloys[i].fitness < tmin) {
                tmin = Efloys.Efloys[i].fitness;
            }
            if (Efloys.Efloys[i].fitness > tmax) {
                tmax = Efloys.Efloys[i].fitness;
            }
            tsum += Efloys.Efloys[i].fitness;
        }
        final double tavg = tsum / size;
        final double temp = (tmax + tmin) / 2.0;
        final double factor = 1.8;
        double a_coeff = 1.0;
        double b_coeff = 0.0;
        if (factor > 1.0) {
            if (tmin > (factor * tavg - tmax) / (factor - 1.0)) {
                final double delta = tmax - tavg;
                if (delta != 0.0) {
                    a_coeff = (factor - 1.0) * tavg / delta;
                    b_coeff = tavg * (tmax - factor * tavg) / delta;
                }
            }
        }
        else {
            final double delta = tavg - tmin;
            if (delta != 0.0) {
                a_coeff = tavg / delta;
                b_coeff = -tmin * tavg / delta;
            }
        }
        for (int i = 0; i < size; ++i) {
            Efloys.Efloys[i].fitness = (int)(Efloys.Efloys[i].fitness * a_coeff + b_coeff);
        }
    }
    
    void GetParameters() {
        Efloys.params[0].value = this.GetOneParameter("MaxSpeed", 5.0f);
        Efloys.params[1].value = this.GetOneParameter("BounceSpeed", 0.8f);
        Efloys.params[2].value = this.GetOneParameter("ApproachAcceleration", 0.3f);
        Efloys.params[4].value = this.GetOneParameter("CenterAcceleration", 0.1f);
        Efloys.params[5].value = this.GetOneParameter("DistBrotherFactor", 1.0f);
        Efloys.params[6].value = this.GetOneParameter("DistStrangerFactor", 20.0f);
        Efloys.params[7].value = this.GetOneParameter("DistLocalFactor", 0.0f);
        Efloys.params[8].value = this.GetOneParameter("CollisionDistance", 200.0f);
        Efloys.params[9].value = this.GetOneParameter("CollisionBrotherFactor", -1.0f);
        Efloys.params[10].value = this.GetOneParameter("CollisionStrangerFactor", 30.0f);
        Efloys.params[11].value = this.GetOneParameter("CollisionLocalFactor", -40.0f);
        Efloys.fixpars[5].value = this.GetOneParameter("NumberOfNeighbors", 2.0f);
        Efloys.fixpars[6].value = this.GetOneParameter("MutationFactor", 0.1f);
        Efloys.fixpars[7].value = this.GetOneParameter("CrossoverFactor", 1.0f);
        Efloys.fixpars[8].value = this.GetOneParameter("energy", 10.0f);
        Efloys.fixpars[9].value = this.GetOneParameter("safety", 10.0f);
        Efloys.fixpars[10].value = this.GetOneParameter("cooperation", 10.0f);
        Efloys.envpars[5].value = this.GetOneParameter("EnergyFactor", 1.0f);
        Efloys.envpars[6].value = this.GetOneParameter("SafetyFactor", 1.0f);
        Efloys.envpars[7].value = this.GetOneParameter("CooperationFactor", 0.0f);
        Efloys.envpars[8].value = this.GetOneParameter("SurviversFactor", 0.0f);
        Efloys.envpars[12].value = this.GetOneParameter("PopulationSize", 15.0f);
        Efloys.envpars[13].value = this.GetOneParameter("FreeWillFactor", 0.05f);
        Efloys.envpars[14].value = this.GetOneParameter("LifeSpan", 50.0f);
        Efloys.NF = (int)Efloys.envpars[12].value;
        Efloys.SLEEP = (long)Efloys.envpars[3].value;
        Efloys.KICK = Efloys.envpars[13].value;
    }
    
    public void RecordGeneration() {
        int fit = 0;
        int w = 0;
        float SpeedSum = 0.0f;
        float AccSum = 0.0f;
        float DllSum = 0.0f;
        float DlsSum = 0.0f;
        float DslSum = 0.0f;
        float CllSum = 0.0f;
        float ClsSum = 0.0f;
        float CslSum = 0.0f;
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            SpeedSum += Efloys.Efloys[i].MaxSpeed;
            AccSum += Efloys.Efloys[i].ApproachAcceleration;
            DllSum += Efloys.Efloys[i].DistBrotherFactor;
            DlsSum = DllSum + Efloys.Efloys[i].DistStrangerFactor;
            DslSum = DllSum + Efloys.Efloys[i].DistLocalFactor;
            CllSum += Efloys.Efloys[i].CollisionBrotherFactor;
            ClsSum = CllSum + Efloys.Efloys[i].CollisionStrangerFactor;
            CslSum = CllSum + Efloys.Efloys[i].CollisionLocalFactor;
            Efloys.Efloys[i].GetFitness();
            if (Efloys.Efloys[i].fitness > fit) {
                fit = Efloys.Efloys[i].fitness;
                w = i;
            }
        }
        final float SpeedAvg = SpeedSum / Efloys.Efloys.length;
        final float AccAvg = AccSum / Efloys.Efloys.length;
        final float DllAvg = DllSum / Efloys.Efloys.length;
        final float DlsAvg = DlsSum / Efloys.Efloys.length;
        final float DslAvg = DslSum / Efloys.Efloys.length;
        final float CllAvg = CllSum / Efloys.Efloys.length;
        final float ClsAvg = ClsSum / Efloys.Efloys.length;
        final float CslAvg = CslSum / Efloys.Efloys.length;
        Efloys.Efloys[w].GetFitness();
        Efloys.FloyGen = new EfloyGeneration(Efloys.CurrentGeneration, this.CurrentStep, 0, Efloys.Efloys[w].id, Efloys.Efloys[w].chrom, Efloys.Efloys[w].fitness, Efloys.Efloys[w].energy, Efloys.Efloys[w].safety, Efloys.Efloys[w].cooperation, Efloys.Efloys[w].MaxSpeed, Efloys.Efloys[w].ApproachAcceleration, AccSum, AccAvg, DllAvg, DlsAvg, DslAvg, CllAvg, ClsAvg, CslAvg);
        Efloys.HistoryData.addElement(Efloys.FloyGen);
    }
    
    void InitParamsExtended() {
        (Efloys.params = new EfloyParam[12])[0] = new EfloyParam(1.0f, 11.0f, 0.5f, 5.0f, "MaxSpeed", true);
        Efloys.params[1] = new EfloyParam(0.2f, 2.2f, 0.1f, 0.8f, "BounceSpeed", true);
        Efloys.params[2] = new EfloyParam(0.1f, 2.1f, 0.1f, 0.3f, "ApproachAcceleration", true);
        Efloys.params[3] = new EfloyParam(0.1f, 2.1f, 0.1f, 0.3f, "RetreatAcceleration", true);
        Efloys.params[4] = new EfloyParam(0.0f, 0.2f, 0.01f, 0.1f, "CenterAcceleration", true);
        Efloys.params[5] = new EfloyParam(-10.0f, 10.0f, 1.0f, 1.0f, "DistBrotherFactor", true);
        Efloys.params[6] = new EfloyParam(-50.0f, 50.0f, 5.0f, 20.0f, "DistStrangerFactor", true);
        Efloys.params[7] = new EfloyParam(-50.0f, 50.0f, 5.0f, 0.0f, "DistLocalFactor", true);
        Efloys.params[8] = new EfloyParam(0.0f, 500.0f, 25.0f, 200.0f, "CollisionDistance", true);
        Efloys.params[9] = new EfloyParam(-10.0f, 10.0f, 1.0f, -1.0f, "CollisionBrotherFactor", true);
        Efloys.params[10] = new EfloyParam(-100.0f, 100.0f, 10.0f, 30.0f, "CollisionStrangerFactor", true);
        Efloys.params[11] = new EfloyParam(-100.0f, 100.0f, 10.0f, -40.0f, "CollisionLocalFactor", true);
        (Efloys.fixpars = new EfloyParam[12])[0] = new EfloyParam(1.0f, 100.0f, 1.0f, this.CurrentNum, "id", false);
        Efloys.fixpars[1] = new EfloyParam(1.0f, 100.0f, 1.0f, 0.0f, "father", false);
        Efloys.fixpars[2] = new EfloyParam(1.0f, 100.0f, 1.0f, 0.0f, "mother", false);
        Efloys.fixpars[3] = new EfloyParam(0.0f, 10.0f, 1.0f, 0.0f, "type", false);
        Efloys.fixpars[4] = new EfloyParam(0.0f, 13.0f, 1.0f, 5.0f, "color", false);
        Efloys.fixpars[5] = new EfloyParam(1.0f, 10.0f, 1.0f, 2.0f, "NumberOfNeighbors", false);
        Efloys.fixpars[6] = new EfloyParam(0.0f, 0.2f, 0.01f, 0.1f, "MutationFactor", false);
        Efloys.fixpars[7] = new EfloyParam(0.0f, 1.0f, 0.1f, 1.0f, "CrossoverFactor", false);
        Efloys.fixpars[8] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "energy", true);
        Efloys.fixpars[9] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "safety", true);
        Efloys.fixpars[10] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "cooperation", true);
        Efloys.fixpars[11] = new EfloyParam(0.0f, 100.0f, 10.0f, 10.0f, "fitness", true);
        Efloys.envpars = new EfloyParam[15];
        final float h = this.canvas.size().height;
        final float w = this.canvas.size().width;
        Efloys.envpars[0] = new EfloyParam(100.0f, 1000.0f, 10.0f, w, "width", false);
        Efloys.envpars[1] = new EfloyParam(100.0f, 1000.0f, 10.0f, h, "height", false);
        Efloys.envpars[2] = new EfloyParam(1.0f, 10.0f, 1.0f, 4.0f, "v0", false);
        Efloys.envpars[3] = new EfloyParam(5.0f, 50.0f, 5.0f, 10.0f, "sleep", false);
        Efloys.envpars[4] = new EfloyParam(0.0f, 50.0f, 5.0f, 30.0f, "margin", false);
        Efloys.envpars[5] = new EfloyParam(0.0f, 10.0f, 1.0f, 1.0f, "EnergyFactor", true);
        Efloys.envpars[6] = new EfloyParam(0.0f, 10.0f, 1.0f, 1.0f, "SafetyFactor", true);
        Efloys.envpars[7] = new EfloyParam(0.0f, 10.0f, 1.0f, 0.0f, "CooperationFactor", true);
        Efloys.envpars[8] = new EfloyParam(0.0f, 100.0f, 10.0f, 0.0f, "SurviversFactor", true);
        Efloys.envpars[9] = new EfloyParam(0.0f, 20.0f, 1.0f, 10.0f, "MaxEnergyDose", true);
        Efloys.envpars[10] = new EfloyParam(0.0f, 20.0f, 1.0f, 10.0f, "MaxSafetyDose", true);
        Efloys.envpars[11] = new EfloyParam(0.0f, 20.0f, 1.0f, 10.0f, "MaxCooperationDose", true);
        Efloys.envpars[12] = new EfloyParam(5.0f, 50.0f, 5.0f, 15.0f, "PopulationSize", true);
        Efloys.envpars[13] = new EfloyParam(0.0f, 0.1f, 0.01f, 0.05f, "FreeWillFactor", true);
        Efloys.envpars[14] = new EfloyParam(10.0f, 200.0f, 10.0f, 50.0f, "LifeSpan", true);
    }
    
    public static void reset() {
        Efloys.NF = 15;
        Efloys.KICK = 0.05f;
        Efloys.SLEEP = 10L;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.id == 201) {
            this.showStatus("Window Distroy");
            this.destroy();
            System.exit(0);
        }
        else if (evt.id == 501) {
            Efloys.deb.hide();
        }
        return super.handleEvent(evt);
    }
    
    private long GetSumFitness() {
        float eng = 0.0f;
        float saf = 0.0f;
        long sum = 0L;
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            eng += Efloys.Efloys[i].energy;
            saf += Efloys.Efloys[i].safety;
        }
        final float avg = 100.0f;
        final float facavg = eng / Efloys.Efloys.length / avg;
        final float facsaf = saf / eng;
        for (int i = 0; i < Efloys.Efloys.length; ++i) {
            Efloys.Efloys[i].energy /= (int)facavg;
            Efloys.Efloys[i].safety = (int)(Efloys.Efloys[i].safety / facavg / facsaf);
            Efloys.Efloys[i].GetFitness();
            sum += Efloys.Efloys[i].fitness;
        }
        return sum;
    }
    
    private Efloy Mate() {
        int MomID = this.GetParentID();
        int DadID = this.GetParentID();
        Efloy Mom = Efloys.Efloys[this.GetFloyNumber(MomID)];
        Efloy Dad = Efloys.Efloys[this.GetFloyNumber(DadID)];
        if (Mom.type == 1) {
            while (Mom.type == 1) {
                MomID = this.GetParentID();
                Mom = Efloys.Efloys[this.GetFloyNumber(MomID)];
                Efloys.deb.showMsg("Repeating Mom = " + MomID);
            }
        }
        if (Dad.type == 1) {
            while (Dad.type == 1) {
                DadID = this.GetParentID();
                Dad = Efloys.Efloys[this.GetFloyNumber(DadID)];
                Efloys.deb.showMsg("Repeating Dad = " + DadID);
            }
        }
        final String KidChrom = Dad.CrossOver(Mom.chrom);
        final String fx = EncodeChrom(Efloys.fixpars);
        final String ev = EncodeChrom(Efloys.envpars);
        final Efloy Kid = new Efloy(Efloys.params, KidChrom, Efloys.fixpars, fx, Efloys.envpars, ev);
        Kid.chrom = Kid.mutate();
        if (Dad.color == Color.red) {
            Kid.AssignColor(Mom.GetColorNumber());
        }
        else {
            Kid.AssignColor(Dad.GetColorNumber());
        }
        Kid.energy = (Dad.energy + Mom.energy) / 2;
        Kid.safety = (Dad.safety + Mom.safety) / 2;
        Kid.cooperation = (Dad.cooperation + Mom.cooperation) / 2;
        Kid.GetFitness();
        Kid.x = 10.0f;
        Kid.y = 10.0f;
        this.canvas.repaint();
        return Kid;
    }
}
