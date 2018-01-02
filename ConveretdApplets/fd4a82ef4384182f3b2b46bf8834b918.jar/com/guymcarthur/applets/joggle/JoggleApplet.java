// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets.joggle;

import java.text.ChoiceFormat;
import java.util.Observable;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.applet.AppletStub;
import com.guymcarthur.applets.AppAppletStub;
import java.awt.Frame;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.io.InputStream;
import java.awt.Image;
import java.awt.Container;
import com.guymcarthur.widget.GridBagManager;
import java.awt.GridBagLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Observer;
import com.guymcarthur.util.CachingWordList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Hashtable;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Button;
import com.guymcarthur.applets.BaseApplet;

public class JoggleApplet extends BaseApplet
{
    JoggleBoard board;
    JoggleCanvas canvas;
    JogglePlayer player;
    JoggleEnemy computer;
    Button StartButton;
    Button PauseButton;
    Choice SkillChoice;
    Label PlayerScore;
    Label ComputerScore;
    Label PlayerTotal;
    Label ComputerTotal;
    TextField PlayerEntry;
    TextArea PlayerMessages;
    TextArea PlayerWords;
    TextArea ComputerWords;
    Scrollbar Timer;
    Label EntryLabel;
    Label ScrollLabel;
    Label Round;
    Hashtable tiles;
    private boolean running;
    private int rounds;
    PlayerObserver playerObs;
    ComputerObserver computerObs;
    TimerThread timer;
    Thread thread;
    
    public String getAppletInfo() {
        return "This is Joggle $Revision: 1.7 $ by Guy McArthur (guym@guymcarthur.com) updated on $Date: 2002/05/25 02:26:31 $.";
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        try {
            this.message("Loading images.");
            final MediaTracker mediaTracker = new MediaTracker(this);
            for (int i = 0; i < JoggleBoard.letters.length; ++i) {
                final String string = "images/" + JoggleBoard.letters[i] + ".jpg";
                if (super.debug) {
                    this.message("Loading " + string);
                }
                final Image loadImage;
                mediaTracker.addImage(loadImage = this.loadImage(string), i);
                this.tiles.put(JoggleBoard.letters[i], loadImage);
            }
            mediaTracker.waitForAll();
        }
        catch (Throwable t) {
            this.message("IOException loading images.", t);
        }
        try {
            this.message("Loading dice");
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("dice.txt");
            if (resourceAsStream == null) {
                throw new IOException("Could not find resource dice.txt");
            }
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            final JoggleDie[] loadDice = JoggleDie.loadDice(bufferedReader);
            bufferedReader.close();
            resourceAsStream.close();
            this.message("Loaded " + loadDice.length + " dice.");
            int int1;
            int int2;
            try {
                int1 = Integer.parseInt(this.getParameter("rows"));
                int2 = Integer.parseInt(this.getParameter("cols"));
            }
            catch (Throwable t2) {
                int1 = 4;
                int2 = 4;
            }
            final JoggleDie[][] array = new JoggleDie[int1][int2];
            int j = 0;
            int n = 0;
            int n2 = 0;
            while (j < int1) {
                for (int k = 0; k < int2; ++k) {
                    if (n >= loadDice.length) {
                        ++n2;
                    }
                    array[j][k] = loadDice[n - n2];
                    if (super.debug) {
                        this.message("Playing with die " + array[j][k].toString());
                    }
                    ++n;
                }
                ++j;
            }
            this.message("Board size is " + array.length + " x " + array[0].length + ".");
            this.board = new JoggleBoard(array);
            if (super.debug) {
                this.board.setDebug(true);
            }
        }
        catch (IOException ex) {
            this.message("Could not load dice.", ex);
        }
        try {
            this.message("Loading word list.");
            final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("words.txt");
            if (resourceAsStream2 == null) {
                throw new IOException("Could not find resource words.txt");
            }
            final BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(resourceAsStream2));
            final CachingWordList list = new CachingWordList(bufferedReader2);
            bufferedReader2.close();
            resourceAsStream2.close();
            this.message("Loaded " + list.size() + " words.");
            this.player = new JogglePlayer(this.board, list);
            this.computer = new JoggleEnemy(this.board, list);
            this.playerObs = new PlayerObserver();
            this.computerObs = new ComputerObserver();
            if (super.debug) {
                this.computer.setDebug(true);
            }
            this.player.addObserver(this.playerObs);
            this.computer.addObserver(this.computerObs);
        }
        catch (IOException ex2) {
            this.message("Could not load words.", ex2);
        }
        this.setFont(new Font("MonoSpaced", 0, 12));
        final Panel panel = new Panel(new FlowLayout(0));
        final Panel panel2 = new Panel(new FlowLayout(2));
        this.StartButton = new Button("Start");
        this.PauseButton = new Button("Pause");
        (this.SkillChoice = new Choice()).add("Novice");
        this.SkillChoice.add("Intermediate");
        this.SkillChoice.add("Advanced");
        this.SkillChoice.add("Joggleholic");
        this.SkillChoice.select("Advanced");
        this.Round = new Label("Round " + this.rounds + "   ");
        panel.setBackground(Color.blue);
        panel.setForeground(Color.white);
        panel2.setBackground(Color.blue);
        panel2.setForeground(Color.white);
        this.StartButton.setBackground(Color.blue);
        this.PauseButton.setBackground(Color.blue);
        this.StartButton.setForeground(Color.white);
        this.PauseButton.setForeground(Color.white);
        this.SkillChoice.setBackground(Color.blue);
        this.SkillChoice.setForeground(Color.white);
        panel.add(this.StartButton);
        panel.add(this.PauseButton);
        panel.add(this.SkillChoice);
        panel2.add(this.Round);
        this.PlayerScore = new Label("0", 2);
        this.ComputerScore = new Label("0", 2);
        this.PlayerTotal = new Label("0", 2);
        this.ComputerTotal = new Label("0", 2);
        this.canvas = new JoggleCanvas(this.board, this.tiles);
        if (super.debug) {
            this.canvas.setDebug(true);
        }
        (this.PlayerEntry = new TextField(36)).setBackground(Color.lightGray);
        this.EntryLabel = new Label("Input words above or click tiles.");
        this.PlayerMessages = new TextArea("", 3, 20, 1);
        this.PlayerWords = new TextArea("", 3, 15, 1);
        this.ComputerWords = new TextArea("", 3, 15, 1);
        this.PlayerMessages.setBackground(Color.lightGray);
        this.PlayerWords.setBackground(Color.lightGray);
        this.ComputerWords.setBackground(Color.lightGray);
        this.Timer = new Scrollbar(0, 0, 1, 0, 180);
        this.ScrollLabel = new Label("Press \"Start\" to begin a game.");
        this.StartButton.addActionListener(new ActionListener() {
            public final void actionPerformed(final ActionEvent actionEvent) {
                JoggleApplet.this.setRunning(!JoggleApplet.this.running);
            }
            
            {
                this.constructor$0(JoggleApplet.this);
            }
            
            private final void constructor$0(final JoggleApplet joggleApplet) {
            }
        });
        this.PauseButton.addActionListener(new ActionListener() {
            boolean paused;
            
            public final void actionPerformed(final ActionEvent actionEvent) {
                if (!JoggleApplet.this.running) {
                    return;
                }
                if (!this.paused) {
                    JoggleApplet.this.PauseButton.setLabel("Resume");
                    JoggleApplet.this.board.setPlayable(false);
                    try {
                        if (JoggleApplet.this.timer.isActive()) {
                            JoggleApplet.this.timer.suspend();
                        }
                        if (JoggleApplet.this.computer.isActive()) {
                            JoggleApplet.this.thread.suspend();
                        }
                    }
                    catch (SecurityException ex) {}
                    JoggleApplet.this.canvas.setVisible(false);
                }
                else {
                    JoggleApplet.this.canvas.setVisible(true);
                    JoggleApplet.this.PauseButton.setLabel("Pause");
                    JoggleApplet.this.board.setPlayable(true);
                    JoggleApplet.this.PlayerEntry.requestFocus();
                    try {
                        if (JoggleApplet.this.computer.isActive()) {
                            JoggleApplet.this.thread.resume();
                        }
                        if (JoggleApplet.this.timer.isActive()) {
                            JoggleApplet.this.timer.resume();
                        }
                    }
                    catch (SecurityException ex2) {}
                }
                this.paused = !this.paused;
            }
            
            {
                this.constructor$0(JoggleApplet.this);
            }
            
            private final void constructor$0(final JoggleApplet joggleApplet) {
            }
        });
        this.SkillChoice.addItemListener(new ItemListener() {
            public final void itemStateChanged(final ItemEvent itemEvent) {
                switch (JoggleApplet.this.SkillChoice.getSelectedIndex()) {
                    case 0: {
                        JoggleApplet.this.computer.setSkill(JoggleEnemy.SKILL_NOVICE);
                        break;
                    }
                    case 1: {
                        JoggleApplet.this.computer.setSkill(JoggleEnemy.SKILL_INTERMEDIATE);
                        break;
                    }
                    case 2: {
                        JoggleApplet.this.computer.setSkill(JoggleEnemy.SKILL_ADVANCED);
                        break;
                    }
                    case 3: {
                        JoggleApplet.this.computer.setSkill(JoggleEnemy.SKILL_MASTER);
                        break;
                    }
                }
                if (JoggleApplet.this.running) {
                    JoggleApplet.this.setRunning(false);
                }
                JoggleApplet.this.player.zero();
                JoggleApplet.this.computer.zero();
                JoggleApplet.this.PlayerScore.setText("0");
                JoggleApplet.this.ComputerScore.setText("0");
                JoggleApplet.this.PlayerTotal.setText("0");
                JoggleApplet.this.ComputerTotal.setText("0");
                ((GameObserver)JoggleApplet.this.playerObs).reset();
                ((GameObserver)JoggleApplet.this.computerObs).reset();
            }
            
            {
                this.constructor$0(JoggleApplet.this);
            }
            
            private final void constructor$0(final JoggleApplet joggleApplet) {
            }
        });
        this.PlayerEntry.addActionListener(new ActionListener() {
            public final void actionPerformed(final ActionEvent actionEvent) {
                if (!JoggleApplet.this.board.isPlayable()) {
                    return;
                }
                JoggleApplet.this.scoreWords();
            }
            
            {
                this.constructor$0(JoggleApplet.this);
            }
            
            private final void constructor$0(final JoggleApplet joggleApplet) {
            }
        });
        this.canvas.addMouseListener(new MouseListener() {
            public final void mouseClicked(final MouseEvent mouseEvent) {
                if (!JoggleApplet.this.board.isPlayable()) {
                    return;
                }
                final int[] cell = JoggleApplet.this.canvas.getCell(mouseEvent.getX(), mouseEvent.getY());
                if (cell[0] == -1 || cell[1] == -1) {
                    return;
                }
                if (JoggleApplet.this.player.select(cell[0], cell[1])) {
                    JoggleApplet.this.canvas.selectCell(cell[0], cell[1], Color.blue);
                }
                else {
                    JoggleApplet.this.canvas.repaint();
                }
            }
            
            public final void mouseEntered(final MouseEvent mouseEvent) {
                JoggleApplet.this.setCursor(new Cursor(12));
            }
            
            public final void mouseExited(final MouseEvent mouseEvent) {
                JoggleApplet.this.setCursor(new Cursor(0));
            }
            
            public final void mousePressed(final MouseEvent mouseEvent) {
            }
            
            public final void mouseReleased(final MouseEvent mouseEvent) {
            }
            
            {
                this.constructor$0(JoggleApplet.this);
            }
            
            private final void constructor$0(final JoggleApplet joggleApplet) {
            }
        });
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        int n3 = 0;
        GridBagManager.add(this, panel, n3, 0, 1, 6, 2, 14);
        GridBagManager.add(this, panel2, n3, 6, 1, 2, 2, 14);
        GridBagManager.add(this, new Label("Player:"), ++n3, 0, 1, 2, 2, 14);
        GridBagManager.add(this, new Label("Score"), n3, 2, 1, 2, 2, 14);
        GridBagManager.add(this, this.PlayerScore, n3, 4, 1, 1, 2, 14);
        GridBagManager.add(this, new Label("Total"), n3, 5, 1, 2, 2, 14);
        GridBagManager.add(this, this.PlayerTotal, n3, 7, 1, 1);
        GridBagManager.add(this, new Label("Computer:"), ++n3, 0, 1, 2, 2, 14);
        GridBagManager.add(this, new Label("Score"), n3, 2, 1, 2, 2, 14);
        GridBagManager.add(this, this.ComputerScore, n3, 4, 1, 1, 2, 14);
        GridBagManager.add(this, new Label("Total"), n3, 5, 1, 2, 2, 14);
        GridBagManager.add(this, this.ComputerTotal, n3, 7, 1, 1);
        GridBagManager.add(this, this.canvas, ++n3, 0, 8, 8);
        int n4;
        GridBagManager.add(this, this.PlayerEntry, n4 = 11, 0, 1, 8, 1, 17);
        GridBagManager.add(this, this.EntryLabel, ++n4, 0, 1, 8, 1, 17);
        GridBagManager.add(this, this.PlayerMessages, ++n4, 0, 2, 8, 2, 17);
        final int n5;
        GridBagManager.add(this, this.PlayerWords, n5 = 15, 0, 2, 4, 0, 17);
        GridBagManager.add(this, this.ComputerWords, n5, 4, 2, 4, 2, 17);
        int n6;
        GridBagManager.add(this, new Label("Player's Words"), n6 = 17, 0, 1, 4, 1, 17);
        GridBagManager.add(this, new Label("Computer's Words"), n6, 4, 1, 4, 1, 17);
        GridBagManager.add(this, this.Timer, ++n6, 0, 1, 8, 1, 10);
        GridBagManager.add(this, this.ScrollLabel, ++n6, 0, 1, 8, 1, 17);
    }
    
    public void setRunning(final boolean running) {
        if (running && !this.running) {
            this.board.shuffle();
            this.canvas.repaint();
            this.player.init();
            this.computer.init();
            this.PlayerEntry.requestFocus();
            this.PlayerMessages.setText("Game is beginning.");
            (this.timer = new TimerThread()).setActive(true);
            this.timer.start();
            this.ComputerWords.setText("");
            this.PlayerWords.setText("");
            this.ComputerWords.setVisible(false);
            this.computer.setActive(true);
            this.thread = new Thread(this.computer);
            switch (this.SkillChoice.getSelectedIndex()) {
                case 0: {
                    this.computer.setSkill(JoggleEnemy.SKILL_NOVICE);
                    break;
                }
                case 1: {
                    this.computer.setSkill(JoggleEnemy.SKILL_INTERMEDIATE);
                    break;
                }
                case 2: {
                    this.computer.setSkill(JoggleEnemy.SKILL_ADVANCED);
                    break;
                }
                case 3: {
                    this.computer.setSkill(JoggleEnemy.SKILL_MASTER);
                    break;
                }
            }
            System.out.println("Skill level is " + this.computer.getSkill());
            this.thread.start();
            try {
                this.thread.setPriority(1);
            }
            catch (SecurityException ex) {
                this.message("Could not change thread priority.", ex);
            }
            try {
                this.timer.setPriority(1);
            }
            catch (SecurityException ex2) {
                this.message("Could not change timer priority.", ex2);
            }
            ++this.rounds;
            this.Round.setText("Round " + this.rounds);
            this.message("Round " + this.rounds + " started.");
            this.board.setPlayable(true);
            this.StartButton.setLabel("Stop");
            this.PlayerScore.setText("   0");
            this.ComputerScore.setText("   0");
        }
        else if (!running && this.running) {
            this.timer.setActive(false);
            this.computer.setActive(false);
            this.board.setPlayable(false);
            this.message("Round " + this.rounds + " finished.");
            this.scoreWords();
            this.PlayerEntry.setText("");
            this.ComputerWords.setVisible(true);
            final Enumeration<String> keys = (Enumeration<String>)this.player.added.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (this.computer.added.containsKey(s)) {
                    final int intValue = this.player.added.get(s);
                    final JogglePlayer player = this.player;
                    player.score -= intValue;
                    final JoggleEnemy computer = this.computer;
                    computer.score -= intValue;
                    this.player.added.remove(s);
                    this.computer.added.remove(s);
                }
            }
            this.PlayerWords.setText("");
            this.ComputerWords.setText("");
            final Enumeration<String> keys2 = (Enumeration<String>)this.player.added.keys();
            while (keys2.hasMoreElements()) {
                final String s2 = keys2.nextElement();
                this.PlayerWords.append(s2 + " (" + (int)this.player.added.get(s2) + ")\n");
            }
            final Enumeration<String> keys3 = (Enumeration<String>)this.computer.added.keys();
            while (keys3.hasMoreElements()) {
                final String s3 = keys3.nextElement();
                this.ComputerWords.append(s3 + " (" + (int)this.computer.added.get(s3) + ")\n");
            }
            this.player.total();
            this.computer.total();
            try {
                this.PlayerWords.setCaretPosition(0);
                this.ComputerWords.setCaretPosition(0);
            }
            catch (Throwable t) {}
            this.ScrollLabel.setText("Press \"Start\" to begin a new round.");
            this.StartButton.setLabel("Start");
            this.StartButton.requestFocus();
            final int size = this.player.added.size();
            final int size2 = this.computer.added.size();
            System.out.println("Player got " + size + " words. Average words/round =" + this.player.wordcount / this.rounds);
            System.out.println("Computer got " + size2 + " words. Average words/round =" + this.computer.wordcount / this.rounds);
            ((GameObserver)this.playerObs).reset();
            ((GameObserver)this.computerObs).reset();
        }
        this.running = running;
    }
    
    public void scoreWords() {
        final String text = this.PlayerEntry.getText();
        this.PlayerEntry.setText("");
        final StringTokenizer stringTokenizer = new StringTokenizer(text.trim(), " \t,;");
        while (stringTokenizer.hasMoreTokens()) {
            this.player.score(stringTokenizer.nextToken());
        }
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Joggle!");
        final JoggleApplet joggleApplet = new JoggleApplet();
        joggleApplet.setStub(new AppAppletStub());
        joggleApplet.init();
        frame.addWindowListener(new WindowAdapter() {
            public final void windowClosing(final WindowEvent windowEvent) {
                JoggleApplet.this.stop();
                JoggleApplet.this.destroy();
                System.exit(0);
            }
            
            {
                this.constructor$0();
            }
            
            private final void constructor$0() {
            }
        });
        frame.add("Center", joggleApplet);
        frame.pack();
        frame.show();
    }
    
    public JoggleApplet() {
        this.tiles = new Hashtable(26);
        this.rounds = 0;
    }
    
    protected class GameObserver
    {
        protected String newline;
        protected boolean updated;
        
        public void reset() {
            this.newline = "";
            this.updated = false;
        }
        
        public void update() {
            if (!this.updated) {
                this.newline = "\n";
                this.updated = true;
            }
        }
        
        GameObserver() {
            this.newline = "";
        }
    }
    
    protected class PlayerObserver extends GameObserver implements Observer
    {
        public void update(final Observable observable, final Object o) {
            final JoggleGame.GameMessage gameMessage = (JoggleGame.GameMessage)o;
            JoggleApplet.this.PlayerScore.setText("" + JoggleApplet.this.player.score);
            JoggleApplet.this.PlayerTotal.setText("" + JoggleApplet.this.player.total);
            JoggleApplet.this.PlayerMessages.append("\n" + gameMessage.msg);
            try {
                JoggleApplet.this.PlayerMessages.setCaretPosition(JoggleApplet.this.PlayerMessages.getText().length());
            }
            catch (Throwable t) {}
            if (gameMessage.pts > 0) {
                JoggleApplet.this.PlayerWords.append(super.newline + gameMessage.word + " (+" + gameMessage.pts + ")");
            }
            ((GameObserver)this).update();
        }
        
        PlayerObserver() {
            if (JoggleApplet.this == null) {
                throw null;
            }
        }
    }
    
    public class ComputerObserver extends GameObserver implements Observer
    {
        public void update(final Observable observable, final Object o) {
            final JoggleGame.GameMessage gameMessage = (JoggleGame.GameMessage)o;
            JoggleApplet.this.ComputerScore.setText("" + JoggleApplet.this.computer.score);
            JoggleApplet.this.ComputerTotal.setText("" + JoggleApplet.this.computer.total);
            if (gameMessage.pts > 0) {
                JoggleApplet.this.ComputerWords.append(super.newline + gameMessage.word + " (+" + gameMessage.pts + ")");
            }
            ((GameObserver)this).update();
        }
        
        public ComputerObserver() {
            if (JoggleApplet.this == null) {
                throw null;
            }
        }
    }
    
    private class TimerThread extends Thread
    {
        private final ChoiceFormat fmt;
        boolean active;
        
        public void setActive(final boolean active) {
            synchronized (this) {
                this.active = active;
            }
        }
        
        public boolean isActive() {
            return this.active;
        }
        
        public void run() {
            while (this.active) {
                try {
                    for (int minimum = JoggleApplet.this.Timer.getMinimum(); minimum < JoggleApplet.this.Timer.getMaximum() && this.active; ++minimum) {
                        JoggleApplet.this.Timer.setValue(minimum);
                        final int n = JoggleApplet.this.Timer.getMaximum() - minimum;
                        JoggleApplet.this.ScrollLabel.setText("" + n + this.fmt.format(n));
                        Thread.sleep(1000L);
                    }
                    JoggleApplet.this.setRunning(false);
                }
                catch (InterruptedException ex) {
                    JoggleApplet.this.message("Timer was interrupted.");
                }
            }
            this.active = false;
        }
        
        TimerThread() {
            this.fmt = new ChoiceFormat(" 1# second remaining|1< seconds remaining.");
        }
    }
}
