import java.awt.Event;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Blocks extends Applet implements Runnable
{
    public static final int cols = 10;
    public static final int rows = 18;
    public static final int ElementSize = 15;
    public static final int MaxElement = 3;
    public static final Color BackGroundColor;
    public static final int SOUND_DROP = 0;
    public static final int SOUND_GAMEOVER = 1;
    public static final int SOUND_NEXTLEVEL = 2;
    public static final int SOUND_LINE = 3;
    PlayFieldCanvas myPlayFieldCanvas;
    StatisticsCanvas Statistics;
    Thread killme;
    Vector ShapeSet;
    Shape FallingShape;
    Shape NextShape;
    Color[][] PlayField;
    static AudioClip[] sounds;
    public boolean GamePaused;
    
    public void init() {
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(0, 2));
        this.add("Center", panel);
        panel.add(this.myPlayFieldCanvas = new PlayFieldCanvas());
        panel.add(this.Statistics = new StatisticsCanvas());
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout());
        this.add("East", panel2);
        panel2.add(new Button("About"));
        panel2.add(new Button("Pause / Resume"));
        (this.ShapeSet = new Vector()).addElement(new Shape(0, 4080, 4080, 0, Color.blue, 3));
        this.ShapeSet.addElement(new Shape(3840, 3840, 4080, 0, Color.yellow, 5));
        this.ShapeSet.addElement(new Shape(240, 240, 4080, 0, Color.pink, 5));
        this.ShapeSet.addElement(new Shape(0, 3840, 65520, 0, Color.green, 4));
        this.ShapeSet.addElement(new Shape(3840, 3840, 3840, 3840, Color.red, 4));
        this.ShapeSet.addElement(new Shape(3840, 4080, 240, 0, Color.magenta, 4));
        this.ShapeSet.addElement(new Shape(240, 4080, 3840, 0, Color.orange, 4));
        (Blocks.sounds = new AudioClip[4])[0] = this.getAudioClip(this.getCodeBase(), "audio/drop.au");
        Blocks.sounds[1] = this.getAudioClip(this.getCodeBase(), "audio/gameover.au");
        Blocks.sounds[2] = this.getAudioClip(this.getCodeBase(), "audio/nextlevel.au");
        Blocks.sounds[3] = this.getAudioClip(this.getCodeBase(), "audio/line.au");
        this.PlayField = new Color[10][18];
        this.myPlayFieldCanvas.SetPlayField(this.PlayField);
        this.InitNewGame();
        this.GetNextRandomShape();
    }
    
    public static void play(final int n) {
        if (Blocks.sounds[n] != null) {
            Blocks.sounds[n].play();
        }
    }
    
    public void InitNewGame() {
        this.ClearPlayField();
        this.Statistics.InitNewGame();
    }
    
    public void ClearPlayField() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 18; ++j) {
                this.PlayField[i][j] = Color.black;
            }
        }
    }
    
    public int GetRandomShapeNr() {
        int i;
        do {
            i = (int)(Math.random() * this.ShapeSet.size());
        } while (i >= this.ShapeSet.size());
        return i;
    }
    
    public void GetNextRandomShape() {
        if (this.FallingShape == null) {
            this.FallingShape = this.ShapeSet.elementAt(this.GetRandomShapeNr());
        }
        else {
            this.FallingShape = this.NextShape;
        }
        this.FallingShape.Init();
        if (!this.FallingShape.CheckIfShapeFits(this.PlayField, 0, 0, false)) {
            this.GameOver();
        }
        this.NextShape = this.ShapeSet.elementAt(this.GetRandomShapeNr());
        this.Statistics.DisplayNextShape(this.NextShape);
    }
    
    public void GameOver() {
        play(1);
        this.myPlayFieldCanvas.GameOver();
        this.Statistics.GameOver();
        this.InitNewGame();
    }
    
    public void start() {
        if (this.killme == null) {
            (this.killme = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.killme = null;
    }
    
    public void run() {
        while (this.killme != null) {
            try {
                Thread.sleep(this.Statistics.GetGameSpeed());
            }
            catch (InterruptedException ex) {}
            if (!this.GamePaused) {
                if (this.FallingShape != null) {
                    if (this.FallingShape.CheckIfShapeFits(this.PlayField, 0, 1, false)) {
                        this.ChangeShapePosition(0, 1, false);
                    }
                    else {
                        play(0);
                        this.FallingShape.PlaceInPlayField(this.PlayField);
                        this.myPlayFieldCanvas.RepaintPlayField();
                        this.Statistics.AddScore(this.FallingShape.GetValue());
                        this.CheckForFullLines();
                        this.GetNextRandomShape();
                    }
                }
                this.myPlayFieldCanvas.repaint(this.FallingShape);
            }
        }
        this.killme = null;
    }
    
    public void CheckForFullLines() {
        int n = 0;
        for (int i = 0; i < 18; ++i) {
            boolean b = false;
            for (int j = 0; j < 10; ++j) {
                if (this.PlayField[j][i] == Color.black) {
                    b = true;
                }
            }
            if (!b) {
                ++n;
                for (int k = i - 1; k >= 0; --k) {
                    for (int l = 0; l < 10; ++l) {
                        this.PlayField[l][k + 1] = this.PlayField[l][k];
                    }
                }
                for (int n2 = 0; n2 < 10; ++n2) {
                    this.PlayField[n2][0] = Color.black;
                }
            }
        }
        if (n > 0) {
            play(3);
            this.myPlayFieldCanvas.RepaintPlayField();
        }
        this.Statistics.AddLines(n);
    }
    
    public boolean action(final Event event, final Object o) {
        if ("About".equals(o)) {
            this.GamePaused = true;
            this.myPlayFieldCanvas.About();
        }
        if ("Pause / Resume".equals(o)) {
            if (this.GamePaused) {
                this.myPlayFieldCanvas.ShowAboutBox = false;
                this.myPlayFieldCanvas.RepaintPlayField();
            }
            this.GamePaused = !this.GamePaused;
        }
        return true;
    }
    
    public synchronized boolean handleEvent(final Event event) {
        int n = 0;
        int n2 = 0;
        boolean b = false;
        switch (event.id) {
            case 1001: {
                return this.action(event, event.arg);
            }
            case 403:
            case 401: {
                switch (event.key) {
                    case 98:
                    case 66: {
                        --n;
                        break;
                    }
                    case 77:
                    case 109: {
                        ++n;
                        break;
                    }
                    case 32: {
                        ++n2;
                        break;
                    }
                    case 78:
                    case 110: {
                        b = true;
                        break;
                    }
                    default: {
                        return false;
                    }
                }
                this.ChangeShapePosition(n, n2, b);
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public void ChangeShapePosition(final int n, final int n2, final boolean b) {
        while (!this.FallingShape.IsReady()) {}
        if (this.FallingShape.CheckIfShapeFits(this.PlayField, n, n2, b)) {
            this.FallingShape.ChangePosition(n, n2, b);
            this.myPlayFieldCanvas.repaint(this.FallingShape);
        }
    }
    
    public Blocks() {
        this.GamePaused = false;
    }
    
    static {
        BackGroundColor = Color.black;
    }
}
