import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import com.next.gt.Actor;
import com.next.gt.ImageManager;
import com.next.gt.ScoreManager;
import java.awt.event.WindowListener;
import com.next.gt.WindowDestroyer;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.KeyListener;
import com.next.gt.BonusHandler;
import com.next.gt.Gamelication;

// 
// Decompiled by Procyon v0.5.30
// 

public class Boinkaroids extends Gamelication implements BonusHandler, KeyListener
{
    public Ship player;
    public int numShips;
    private int badGuyCount;
    public int level;
    public boolean createNewPlayer;
    private Label myLabel;
    private int SAFETY_ZONE_WIDTH;
    private int SAFETY_ZONE_HEIGHT;
    
    public void init() {
        super.init(500, 400, "./");
        final Frame frame = new Frame("Boinkaroids");
        frame.setSize(500, 500);
        frame.add("South", this.myLabel = new Label("Score: 00000000   Ships: " + this.numShips + "   Level: " + this.level));
        frame.add("Center", (Component)this);
        frame.addWindowListener(new WindowDestroyer(frame, this));
        frame.setResizable(false);
        (super.scoreManager = new ScoreManager()).registerForBonusNotification(this, 20000);
        frame.show();
        new ImageManager(this);
        this.myLabel.addKeyListener(this);
        this.newGame();
        super.displayManager.setBackgroundTile(this.getImage(this.getCodeBase(), "images/background.gif"));
        this.start();
        this.run();
    }
    
    public void newGame() {
        super.scoreManager.setScore(0);
        this.numShips = 3;
        this.badGuyCount = 0;
        this.level = 0;
        this.removePlayer();
        super.actorManager.removeAllActors();
        this.createActors();
    }
    
    public void newLevel() {
        ++this.level;
        super.actorManager.removeAllActors();
        this.createActors();
    }
    
    public void createActors() {
        for (int i = 0; i < 2 * this.level; ++i) {
            super.actorManager.addActor(new Asteroid(this, null, "gumball", 2));
            this.addOneBadGuy();
        }
        for (int n = 0; n < 0.5 * this.level; ++n) {
            super.actorManager.addActor(new Bigoobie(this));
            this.addOneBadGuy();
        }
        this.createPlayer();
    }
    
    public void removeOneBadGuy() {
        --this.badGuyCount;
    }
    
    public void addOneBadGuy() {
        ++this.badGuyCount;
    }
    
    public void createPlayer() {
        this.removePlayer();
        this.player = new Ship((Gamelication)this);
        this.myLabel.addKeyListener((KeyListener)this.player);
        super.actorManager.addActor((Actor)this.player);
        this.createNewPlayer = false;
    }
    
    public void removePlayer() {
        if (this.player != null) {
            super.actorManager.removeActor((Actor)this.player);
            this.myLabel.removeKeyListener((KeyListener)this.player);
            this.player = null;
        }
    }
    
    public void tick() {
        super.tick();
        this.myLabel.setText("Score: " + super.scoreManager.score + "   Ships: " + this.numShips + "   Level: " + this.level);
        if (this.badGuyCount <= 0) {
            this.newLevel();
        }
        if (this.createNewPlayer && !super.actorManager.isActorIn(new Rectangle(((Component)this).getSize().width / 2 - this.SAFETY_ZONE_WIDTH / 2, ((Component)this).getSize().height / 2 - this.SAFETY_ZONE_HEIGHT / 2, this.SAFETY_ZONE_WIDTH, this.SAFETY_ZONE_HEIGHT))) {
            this.createNewPlayer = false;
            this.createPlayer();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 112) {
            this.newGame();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void didAchieveBonus() {
        ++this.numShips;
    }
    
    public void decrementShipCount() {
        if (this.numShips-- > 0) {
            this.createNewPlayer = true;
            this.removePlayer();
        }
    }
    
    public static void main(final String[] array) {
        new Boinkaroids().init();
    }
    
    public Boinkaroids() {
        this.createNewPlayer = true;
        this.SAFETY_ZONE_WIDTH = 128;
        this.SAFETY_ZONE_HEIGHT = 128;
    }
}
