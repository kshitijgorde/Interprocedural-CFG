import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Game extends Canvas implements Runnable
{
    public boolean pauseGame;
    public boolean superjaman;
    public boolean levelbymap;
    public int numDevil;
    public int numJaman;
    public Choice mapChoice;
    public Choice numJamanChoice;
    public Checkbox soundCheckbox;
    public Label scoreLabel;
    public Label levelLabel;
    public Label numJamanLabel;
    public Label highScoreLabel;
    public int highScore;
    private Image backIm;
    private int cell_x;
    private int cell_y;
    private int startx;
    private int starty;
    private Man man;
    private Devil[] devil;
    private Bonus bonus;
    private int score;
    private int num_org;
    private int num_del;
    private int numNut;
    private int level;
    private int devilBarnX;
    private int devilBarnY;
    private int manLastCross;
    private int manComingCross;
    private boolean hideGame;
    private boolean gameOver;
    private int gameScreenSizeX;
    private int gameScreenSizeY;
    Thread kicker;
    private int[] numJamanArray;
    int stateInRoad;
    public String[][] mapStr;
    public String[] mapName;
    private int currentMapIndex;
    PathFinding pathFinding;
    private int[] lastPos;
    Image offSreenIm;
    Graphics g2;
    public static final int SOUND_BACKGROUND = 0;
    public static final int SOUND_NUT_EAT = 1;
    public static final int SOUND_ORG_EAT = 2;
    public static final int SOUND_DEVIL_EAT = 3;
    public static final int SOUND_DIE = 4;
    public static final int SOUND_VICTORY = 5;
    public static final int SOUND_LOOSE = 6;
    public static final int SOUND_BONUS_EAT = 7;
    public static final int SOUND_NEW_JAMAN = 8;
    
    public Game() {
        this.pauseGame = false;
        this.superjaman = false;
        this.levelbymap = false;
        this.numDevil = 4;
        this.numJaman = 0;
        this.mapChoice = new Choice();
        this.numJamanChoice = new Choice();
        this.soundCheckbox = new Checkbox("Sound", false);
        this.scoreLabel = new Label(" Score: 0");
        this.levelLabel = new Label(" Level: 1");
        this.numJamanLabel = new Label(" Jaman: 1");
        this.highScoreLabel = new Label(" Highest: 0");
        this.highScore = 0;
        this.backIm = null;
        this.startx = 0;
        this.starty = 0;
        this.man = new Man();
        this.devil = new Devil[this.numDevil];
        this.bonus = new Bonus();
        this.score = 0;
        this.num_org = 0;
        this.num_del = 0;
        this.numNut = 0;
        this.level = 0;
        this.devilBarnX = 1;
        this.devilBarnY = 1;
        this.manLastCross = -1;
        this.manComingCross = -1;
        this.hideGame = true;
        this.gameOver = false;
        this.kicker = null;
        this.numJamanArray = new int[] { 0, 3, 5, 10, 20, 20 };
        this.stateInRoad = 2;
        this.currentMapIndex = 0;
        this.pathFinding = new PathFinding();
        this.lastPos = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.g2 = null;
    }
    
    private void init() {
        Runner.setStart(this.startx, this.starty);
        this.cell_x = Runner.getCellX();
        this.cell_y = Runner.getCellY();
        this.man.initMan();
        this.bonus.initBonus();
        for (int i = 0; i < this.numDevil; ++i) {
            (this.devil[i] = new Devil()).initDevil(i);
        }
        for (int j = 0; j < this.mapStr.length; ++j) {
            this.mapChoice.add("Map " + (j + 1));
        }
        if (this.superjaman) {
            this.numJaman = 0;
        }
        this.numJamanChoice.add("0 Jaman");
        this.numJamanChoice.add("3 Jamen");
        this.numJamanChoice.add("5 Jamen");
        this.numJamanChoice.add("10 Jamen");
        this.numJamanChoice.add("20 Jamen");
        int n;
        for (n = 0; n < 5 && this.numJamanArray[n] != this.numJaman; ++n) {}
        if (n == 5) {
            this.numJamanArray[5] = this.numJaman;
            this.numJamanChoice.add(this.numJaman + " Jamen");
        }
        this.numJamanChoice.select(n);
    }
    
    public void start() {
        this.init();
        this.currentMapIndex = -1;
        this.newGame();
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.kicker != null) {
            this.kicker.stop();
            this.kicker = null;
        }
    }
    
    public void run() {
        int n = 0;
        while (this.kicker != null) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
            if (this.hideGame) {
                continue;
            }
            if (this.pauseGame || this.gameOver) {
                continue;
            }
            if (this.numNut == 0 && n == 0) {
                n = 60;
                this.makeSound(5);
            }
            if (n > 0) {
                --n;
                this.man.nextImage();
                this.repaint();
                if (n != 0) {
                    continue;
                }
                if (this.man.getState() == 5) {
                    this.gameOver = true;
                }
                else {
                    this.nextLevel();
                }
            }
            else {
                if (this.manControler()) {
                    this.repaint();
                }
                if (this.devilControler()) {
                    this.repaint();
                }
                if (!this.bonusControler()) {
                    continue;
                }
                this.repaint();
            }
        }
    }
    
    public boolean isHideGame() {
        return this.hideGame;
    }
    
    public void setHideGame(final boolean hideGame) {
        this.hideGame = hideGame;
    }
    
    private boolean manControler() {
        if (this.man.getState() == 6) {
            return false;
        }
        this.stateInRoad = 0;
        if (this.man.getState() != 5) {
            if (this.man.isDelaying()) {
                return false;
            }
            int direction = this.man.getDirection();
            final int nextDirection = this.man.getNextDirection();
            if (Runner.isOppositDirections(direction, nextDirection)) {
                this.man.setManDirection(nextDirection);
                direction = nextDirection;
            }
            this.manScore();
            if (this.man.isInOrigin()) {
                final int originX = this.man.getOriginX();
                final int originY = this.man.getOriginY();
                if (this.isCross(originX, originY)) {
                    this.manLastCross = originX + (originY << 8);
                    this.manComingCross = -1;
                }
                if (nextDirection != direction && this.isNode(originX, originY) && this.canTurn(originX, originY, nextDirection)) {
                    this.man.setManDirection(nextDirection);
                    if (Runner.isOppositDirections(direction, nextDirection)) {
                        this.manLastCross = this.manComingCross;
                    }
                    this.manComingCross = this.calcuComingCross(originX, originY, nextDirection);
                    direction = nextDirection;
                }
                this.manComingCross = this.calcuComingCross(originX, originY, direction);
                if (!this.canTurn(originX, originY, direction)) {
                    return false;
                }
            }
            this.man.calcuPosition();
            this.adjuctOrigin(this.man);
        }
        this.man.nextImage();
        this.man.Timer();
        return true;
    }
    
    private void manScore() {
        boolean b = false;
        final int n = this.score / 10000;
        if (this.man.isInOrigin()) {
            final int originX = this.man.getOriginX();
            final int originY = this.man.getOriginY();
            if (this.pathFinding.getMap(originX, originY) == 1) {
                this.score += 10;
                --this.numNut;
                b = true;
                this.man.setStepSize(6, 6);
                this.pathFinding.setMap(originX, originY, 7);
                this.updateBackground(originX, originY);
                this.makeSound(1);
            }
            if (this.pathFinding.getMap(originX, originY) == 2) {
                this.score += 100 << this.num_org;
                ++this.num_org;
                this.num_del = 0;
                b = true;
                for (int i = 0; i < this.numDevil; ++i) {
                    this.devilMakeWeak(this.devil[i]);
                }
                this.pathFinding.setMap(originX, originY, 7);
                this.updateBackground(originX, originY);
                this.makeSound(2);
            }
        }
        for (int j = 0; j < this.numDevil; ++j) {
            if (this.man.isKilledDistance(this.devil[j])) {
                if (this.devil[j].getState() == 3) {
                    this.devil[j].setState(5);
                    this.score += 200 << this.num_del;
                    ++this.num_del;
                    b = true;
                    this.makeSound(3);
                }
                else if (this.devil[j].getState() == 0) {
                    if (this.superjaman) {
                        --this.numJaman;
                        b = true;
                    }
                    else {
                        if (this.numJaman > 0) {
                            this.makeSound(4);
                            this.nextMan();
                            break;
                        }
                        this.man.setState(5);
                        this.man.setManDied();
                        this.makeSound(6);
                        break;
                    }
                }
            }
        }
        if (this.bonus.canTakeBonus() && this.man.isKilledDistance(this.bonus)) {
            this.score += this.bonus.takeBonus();
            b = true;
            this.makeSound(7);
        }
        if (b) {
            final int n2 = this.score / 10000 - n;
            if (n2 > 0) {
                this.numJaman += n2;
                this.makeSound(8);
            }
            this.updateGameInfo();
        }
    }
    
    private void devilMakeWeak(final Devil devil) {
        if (devil.getState() == 5) {
            return;
        }
        devil.setweakState();
        if (!devil.isInOrigin() && Math.random() < 0.7) {
            devil.setDevilDirection(Runner.getOppositDirections(devil.getDirection()));
        }
    }
    
    private boolean bonusControler() {
        this.bonus.Timer();
        return this.bonus.isVisual();
    }
    
    private boolean devilControler() {
        for (int i = 0; i < this.numDevil; ++i) {
            this.stateInRoad = this.devil[i].getState();
            if (!this.devil[i].isDelaying() && !this.devil[i].isTurnDelaying() && this.devilTurnManager(this.devil[i])) {
                this.devil[i].calcuPosition();
            }
            this.devil[i].nextDevilImage();
            this.devil[i].Timer();
        }
        return true;
    }
    
    private boolean devilTurnManager(final Devil devil) {
        this.adjuctOrigin(devil);
        final int originX = devil.getOriginX();
        final int originY = devil.getOriginY();
        if (devil.getState() == 0) {
            final boolean map = this.pathFinding.isMap(originX, originY, 8);
            if (devil.isSlowStep() && !map) {
                devil.setStepSize(10, 10);
            }
            else if (!devil.isSlowStep() && map) {
                devil.setTempSlowDown();
            }
        }
        if (devil.isInOrigin() && (devil.changeDirAllow() || this.isStuck(devil))) {
            final int state = devil.getState();
            final boolean map2 = this.pathFinding.isMap(originX, originY, 5);
            if (state == 2 && !map2) {
                devil.setState(0);
            }
            else if (state == 4 && !map2) {
                devil.setState(3);
            }
            else if (state == 0 && map2) {
                devil.setState(2);
            }
            else if (state == 5 && map2) {
                devil.setState(1);
            }
            if (this.isNode(originX, originY)) {
                int direction = devil.getDirection();
                if (devil.getState() != 1) {
                    direction = direction;
                }
                final int numNewDirection = this.numNewDirection(originX, originY, direction);
                if (numNewDirection > 0) {
                    int devilDirection = -1;
                    if (devil.getState() == 0 || devil.getState() == 2 || devil.getState() == 4) {
                        devilDirection = this.chaseMan(devil, numNewDirection);
                    }
                    else if (devil.getState() == 5) {
                        devilDirection = this.comeHome(devil, numNewDirection);
                    }
                    if (devilDirection == -1 || Runner.isOppositDirections(devilDirection, direction)) {
                        devilDirection = this.getRandomNewDirection(originX, originY, direction);
                    }
                    devil.setDevilDirection(devilDirection);
                    if (devilDirection != direction) {
                        devil.setTurnDelay();
                        devil.turnOffChangeDir();
                        return false;
                    }
                }
                else if (this.isStuck(devil)) {
                    devil.setDevilDirection(Runner.getOppositDirections(direction));
                }
            }
        }
        return true;
    }
    
    private boolean isStuck(final Runner runner) {
        return !this.canTurn(runner.getOriginX(), runner.getOriginY(), runner.getDirection());
    }
    
    private int chaseMan(final Devil devil, final int n) {
        final int originX = devil.getOriginX();
        final int originY = devil.getOriginY();
        final int n2 = originX | originY << 8;
        int bestDirection = -1;
        final int direction = devil.getDirection();
        this.calcuComingNode(this.man.getOriginX(), this.man.getOriginY(), this.man.getDirection());
        for (int i = 0; i < 4; ++i) {
            final int n3 = Runner.directionArray[i];
            if (!Runner.isOppositDirections(n3, direction) && this.canTurn(originX, originY, n3) && this.isPathCanCatch(originX, originY, this.man.getOriginX(), this.man.getOriginY(), n3)) {
                return n3;
            }
        }
        if (n > 1 && this.manComingCross != -1 && n2 != this.manComingCross && devil.getRandom()) {
            bestDirection = this.pathFinding.findBestDirection(originX, originY, this.manComingCross & 0xFF, this.manComingCross >> 8, direction);
        }
        return bestDirection;
    }
    
    private int comeHome(final Devil devil, final int n) {
        final int originX = devil.getOriginX();
        final int originY = devil.getOriginY();
        int bestDirection = -1;
        final int n2 = this.devilBarnX | this.devilBarnY << 8;
        final int direction = devil.getDirection();
        for (int i = 0; i < 4; ++i) {
            final int n3 = Runner.directionArray[i];
            if (!Runner.isOppositDirections(n3, direction) && this.canTurn(originX, originY, n3) && (this.pathFinding.findNextNode(originX, originY, n3) & 0xFFFF) == n2) {
                return n3;
            }
        }
        if (n > 1 && (originX != this.devilBarnX || originY != this.devilBarnY) && devil.getRandom()) {
            bestDirection = this.pathFinding.findBestDirection(originX, originY, this.devilBarnX, this.devilBarnY, direction);
        }
        return bestDirection;
    }
    
    private boolean isPathCanCatch(final int n, final int n2, final int n3, final int n4, int randomNewDirection) {
        int adjuctX = n;
        int adjuctY = n2;
        while (adjuctX != n3 || adjuctY != n4) {
            if (this.isInRoad(adjuctX, adjuctY)) {
                if (!this.canTurn(adjuctX, adjuctY, randomNewDirection)) {
                    randomNewDirection = this.getRandomNewDirection(adjuctX, adjuctY, randomNewDirection);
                }
                switch (randomNewDirection) {
                    case 1: {
                        --adjuctY;
                        break;
                    }
                    case 3: {
                        ++adjuctY;
                        break;
                    }
                    case 4: {
                        ++adjuctX;
                        break;
                    }
                    case 12: {
                        --adjuctX;
                        break;
                    }
                }
                adjuctX = this.adjuctX(adjuctX);
                adjuctY = this.adjuctY(adjuctY);
                if (!this.isCross(adjuctX, adjuctY)) {
                    continue;
                }
            }
            return false;
        }
        return true;
    }
    
    private boolean isKillDistance(final int n, final int n2, final int n3, final int n4) {
        return Math.abs(n - n3) < this.cell_x && Math.abs(n2 - n4) < this.cell_y;
    }
    
    private boolean isInRoad(int adjuctX, int adjuctY) {
        adjuctX = this.adjuctX(adjuctX);
        adjuctY = this.adjuctY(adjuctY);
        if (this.pathFinding.isMapEmpty(adjuctX, adjuctY)) {
            return false;
        }
        final int map = this.pathFinding.getMap(adjuctX, adjuctY);
        return this.stateInRoad == 5 || this.stateInRoad == 1 || this.stateInRoad == 2 || this.stateInRoad == 4 || map != 5;
    }
    
    private boolean isNode(final int n, final int n2) {
        int n3 = 0;
        if (this.isInRoad(n, n2 - 1) || this.isInRoad(n, n2 + 1)) {
            ++n3;
        }
        if (this.isInRoad(n - 1, n2) || this.isInRoad(n + 1, n2)) {
            ++n3;
        }
        if (n3 > 1) {
            return true;
        }
        int n4 = 0;
        if (this.isInRoad(n, n2 - 1)) {
            ++n4;
        }
        if (this.isInRoad(n, n2 + 1)) {
            ++n4;
        }
        if (this.isInRoad(n - 1, n2)) {
            ++n4;
        }
        if (this.isInRoad(n + 1, n2)) {
            ++n4;
        }
        return n4 == 1;
    }
    
    private boolean isCross(final int n, final int n2) {
        if (!this.isInRoad(n, n2)) {
            return false;
        }
        int n3 = 0;
        if (this.isInRoad(n - 1, n2)) {
            ++n3;
        }
        if (this.isInRoad(n + 1, n2)) {
            ++n3;
        }
        if (this.isInRoad(n, n2 - 1)) {
            ++n3;
        }
        if (this.isInRoad(n, n2 + 1)) {
            ++n3;
        }
        return n3 > 2;
    }
    
    private boolean canTurn(final int n, final int n2, final int n3) {
        switch (n3) {
            case 1: {
                if (this.isInRoad(n, n2 - 1)) {
                    return true;
                }
                break;
            }
            case 3: {
                if (this.isInRoad(n, n2 + 1)) {
                    return true;
                }
                break;
            }
            case 12: {
                if (this.isInRoad(n - 1, n2)) {
                    return true;
                }
                break;
            }
            case 4: {
                if (this.isInRoad(n + 1, n2)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    private int numNewDirection(final int n, final int n2, final int n3) {
        int n4 = 0;
        if (n3 != 1 && this.isInRoad(n, n2 + 1)) {
            ++n4;
        }
        if (n3 != 3 && this.isInRoad(n, n2 - 1)) {
            ++n4;
        }
        if (n3 != 12 && this.isInRoad(n + 1, n2)) {
            ++n4;
        }
        if (n3 != 4 && this.isInRoad(n - 1, n2)) {
            ++n4;
        }
        return n4;
    }
    
    private int getRandomNewDirection(final int n, final int n2, final int n3) {
        final int[] array = new int[4];
        int n4 = 0;
        if (n3 != 1 && this.isInRoad(n, n2 + 1)) {
            array[n4] = 3;
            ++n4;
        }
        if (n3 != 3 && this.isInRoad(n, n2 - 1)) {
            array[n4] = 1;
            ++n4;
        }
        if (n3 != 12 && this.isInRoad(n + 1, n2)) {
            array[n4] = 4;
            ++n4;
        }
        if (n3 != 4 && this.isInRoad(n - 1, n2)) {
            array[n4] = 12;
            ++n4;
        }
        if (n4 == 0) {
            return 0;
        }
        if (n4 == 1) {
            return array[0];
        }
        return array[(int)(Math.random() * 101.0) % n4];
    }
    
    private int calcuComingNode(int adjuctX, int adjuctY, final int n) {
        while (!this.isNode(adjuctX, adjuctY)) {
            if (!this.isInRoad(adjuctX, adjuctY)) {
                return -1;
            }
            switch (n) {
                case 1: {
                    --adjuctY;
                    break;
                }
                case 3: {
                    ++adjuctY;
                    break;
                }
                case 4: {
                    ++adjuctX;
                    break;
                }
                case 12: {
                    --adjuctX;
                    break;
                }
            }
            adjuctX = this.adjuctX(adjuctX);
            adjuctY = this.adjuctY(adjuctY);
        }
        return adjuctX + adjuctY * 256;
    }
    
    public int calcuManComingCross() {
        final int originX = this.man.getOriginX();
        final int originY = this.man.getOriginY();
        final int direction = this.man.getDirection();
        if (this.man.isInOrigin() && this.numNewDirection(originX, originY, direction) > 1) {
            return originX + originY * 256;
        }
        return this.calcuComingCross(originX, originY, direction);
    }
    
    public int calcuComingCross(int adjuctX, int adjuctY, int randomNewDirection) {
        do {
            randomNewDirection = this.getRandomNewDirection(adjuctX, adjuctY, randomNewDirection);
            switch (randomNewDirection) {
                case 1: {
                    --adjuctY;
                    break;
                }
                case 3: {
                    ++adjuctY;
                    break;
                }
                case 4: {
                    ++adjuctX;
                    break;
                }
                case 12: {
                    --adjuctX;
                    break;
                }
            }
            adjuctX = this.adjuctX(adjuctX);
            adjuctY = this.adjuctY(adjuctY);
        } while (!this.isCross(adjuctX, adjuctY));
        return adjuctX + adjuctY * 256;
    }
    
    private int adjuctX(int n) {
        if (n < 0) {
            n = this.pathFinding.getMapSizeX() - 1;
        }
        if (n >= this.pathFinding.getMapSizeX()) {
            n = 0;
        }
        return n;
    }
    
    private int adjuctY(int n) {
        if (n < 0) {
            n = this.pathFinding.getMapSizeY() - 1;
        }
        if (n >= this.pathFinding.getMapSizeY()) {
            n = 0;
        }
        return n;
    }
    
    private void adjuctOrigin(final Runner runner) {
        runner.setOrigin(this.adjuctX(runner.getOriginX()), this.adjuctY(runner.getOriginY()));
    }
    
    private void initNewMap() {
        this.pathFinding.initData(this.mapStr[this.currentMapIndex]);
    }
    
    public int getGameSreenSizeX() {
        return this.gameScreenSizeX;
    }
    
    public int getGameSreenSizeY() {
        return this.gameScreenSizeY;
    }
    
    public void newGame() {
        this.level = 0;
        this.score = 0;
        final int selectedIndex = this.mapChoice.getSelectedIndex();
        if (selectedIndex != this.currentMapIndex) {
            this.currentMapIndex = selectedIndex;
            this.initNewMap();
        }
        else {
            this.pathFinding.restoreMap();
        }
        this.numJaman = this.numJamanArray[this.numJamanChoice.getSelectedIndex()];
        this.gameReset();
    }
    
    private void nextMan() {
        --this.numJaman;
        this.gameReset();
        this.delay(1000);
    }
    
    private void nextLevel() {
        ++this.level;
        if (this.levelbymap) {
            ++this.currentMapIndex;
            if (this.currentMapIndex >= this.mapChoice.getItemCount()) {
                this.currentMapIndex = 0;
            }
            this.mapChoice.select(this.currentMapIndex);
            this.initNewMap();
        }
        else {
            this.pathFinding.restoreMap();
        }
        this.gameReset();
    }
    
    private void gameReset() {
        this.num_org = 0;
        this.gameOver = false;
        this.numNut = this.pathFinding.countMap(1);
        int level = this.level;
        if (this.levelbymap) {
            level = 0;
        }
        for (int i = 0; i < this.numDevil; ++i) {
            this.devil[i].initDevil(i);
            this.devil[i].setLevel(level);
        }
        this.man.initMan();
        this.man.setLevel(level);
        this.bonus.setLevel(level);
        this.backIm = null;
        this.updateGameInfo();
        this.setRunnerPosition();
        this.setGameSize();
        String string = "Jaman";
        if (this.mapName != null && this.mapName[this.currentMapIndex] != null) {
            string = string + " - " + this.mapName[this.currentMapIndex];
        }
        ((Frame)this.getParent()).setTitle(string);
    }
    
    private void setGameSize() {
        this.gameScreenSizeX = this.startx + 2 * this.cell_x * ((this.pathFinding.getMapSizeX() + 1) / 2) + 10;
        if ((this.pathFinding.getMapSizeX() & 0x1) == 0x0) {
            this.gameScreenSizeX += this.cell_x;
        }
        this.gameScreenSizeY = this.starty + 2 * this.cell_y * ((this.pathFinding.getMapSizeY() + 1) / 2) + 10;
        if ((this.pathFinding.getMapSizeY() & 0x1) == 0x0) {
            this.gameScreenSizeY += this.cell_y;
        }
        this.setSize(this.gameScreenSizeX, this.gameScreenSizeY);
        this.getParent().setSize(this.gameScreenSizeX, this.gameScreenSizeY + 100);
    }
    
    private void updateGameInfo() {
        this.scoreLabel.setText(" Score: " + this.score);
        this.numJamanLabel.setText("Jaman: " + this.numJaman);
        this.levelLabel.setText(" Level: " + (this.level + 1));
        if (this.highScore < this.score) {
            this.highScore = this.score;
        }
        this.highScoreLabel.setText("Highest: " + this.highScore);
    }
    
    private void delay(final int n) {
        final Thread thread = new Thread();
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    private void setRunnerPosition() {
        int n = this.pathFinding.getFirstApperanceMap(3);
        if (n == -1) {
            n = this.pathFinding.getFirstApperanceMap(7);
        }
        if (n == -1) {
            n = this.pathFinding.getFirstApperanceMap(8);
        }
        if (n == -1) {
            n = this.pathFinding.getFirstApperanceMap(1);
        }
        final int n2 = n & 0xFF;
        final int n3 = n >> 8 & 0xFF;
        this.man.setOrigin(n2, n3);
        for (int i = 0; i < 4; ++i) {
            if (this.canTurn(n2, n3, Runner.directionArray[i])) {
                this.man.setManDirection(Runner.directionArray[i]);
                this.man.setNextDirection(Runner.directionArray[i]);
                break;
            }
        }
        final int n4 = n;
        int firstApperanceMap = this.pathFinding.getFirstApperanceMap(9);
        if (firstApperanceMap == -1) {
            firstApperanceMap = n4;
        }
        this.bonus.setOrigin(firstApperanceMap & 0xFF, firstApperanceMap >> 8 & 0xFF);
        int n5 = this.pathFinding.getFirstApperanceMap(5);
        if (n5 == -1) {
            n5 = this.pathFinding.getFirstApperanceMap(7);
        }
        this.devilBarnX = (n5 & 0xFF);
        this.devilBarnY = (n5 >> 8 & 0xFF);
        int direction;
        for (direction = 0; direction < 4 && !this.canTurn(this.devilBarnX, this.devilBarnY, Runner.directionArray[direction]); ++direction) {}
        for (int j = 0; j < this.numDevil; ++j) {
            this.devil[j].setOrigin(this.devilBarnX, this.devilBarnY);
            this.devil[j].setDirection(direction);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.backIm == null) {
            this.makeBackground();
        }
        graphics.drawImage(this.backIm, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        if (this.backIm == null) {
            this.paint(graphics);
        }
        final int n = this.numDevil + 2;
        final int[] lastPos = new int[n];
        final Runner[] array = new Runner[n];
        final Rectangle[] array2 = new Rectangle[n];
        final Rectangle[] array3 = new Rectangle[n];
        for (int i = 0; i < n; ++i) {
            if (i == 0) {
                array[i] = this.bonus;
            }
            else if (i == n - 1) {
                array[i] = this.man;
            }
            else {
                array[i] = this.devil[i - 1];
            }
            lastPos[i] = array[i].getCurrentDisplayPosition();
            final Rectangle rectangle = new Rectangle((short)(this.lastPos[i] & 0xFFFF), (short)(this.lastPos[i] >> 16), this.cell_x * 2, this.cell_y * 2);
            array2[i] = new Rectangle((short)(lastPos[i] & 0xFFFF), (short)(lastPos[i] >> 16), this.cell_x * 2, this.cell_y * 2);
            array3[i] = array2[i].union(rectangle);
        }
        for (int j = 0; j < n; ++j) {
            if (array3[j] != null) {
                for (int k = j + 1; k < n; ++k) {
                    if (array3[k] != null && array3[j].intersects(array3[k])) {
                        array3[j] = array3[j].union(array3[k]);
                        array3[k] = null;
                    }
                }
            }
        }
        for (int l = 0; l < n; ++l) {
            if (array3[l] != null) {
                final int width = array3[l].width;
                final int height = array3[l].height;
                this.g2.drawImage(this.backIm, array3[l].x, array3[l].y, array3[l].x + width, array3[l].y + height, array3[l].x, array3[l].y, array3[l].x + width, array3[l].y + height, this);
                for (int n2 = l; n2 < n; ++n2) {
                    if (array3[l].intersects(array2[n2]) && (n2 > 0 || this.bonus.isVisual())) {
                        array[n2].displayRunner(this.g2, array2[n2].x, array2[n2].y);
                    }
                }
                graphics.drawImage(this.offSreenIm, array3[l].x, array3[l].y, array3[l].x + width, array3[l].y + height, array3[l].x, array3[l].y, array3[l].x + width, array3[l].y + height, this);
                array3[l] = null;
            }
        }
        this.lastPos = lastPos;
    }
    
    private void makeBackground() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.backIm = this.createImage(width, height);
        final Graphics graphics = this.backIm.getGraphics();
        this.offSreenIm = this.createImage(width, height);
        if (this.g2 != null) {
            this.g2.dispose();
        }
        this.g2 = this.offSreenIm.getGraphics();
        graphics.setColor(new Color(0, 180, 0));
        graphics.fillRect(0, 0, width, height);
        this.pathFinding.getMapSizeY();
        this.pathFinding.getMapSizeX();
        for (int i = 0; i < this.pathFinding.getMapSizeY(); ++i) {
            i = i;
            for (int j = 0; j < this.pathFinding.getMapSizeX(); ++j) {
                if (i == 2 && j == 41) {
                    i = i;
                }
                final int n = this.startx + j * this.cell_x;
                final int n2 = this.starty + i * this.cell_y;
                if (!this.pathFinding.isMapEmpty(j, i)) {
                    graphics.setColor(Color.black);
                    if (this.pathFinding.getMap(j, i) == 5) {
                        graphics.setColor(Color.darkGray);
                    }
                    graphics.fillRect(n, n2, this.cell_x * 2 - 1, this.cell_y * 2 - 1);
                }
                else if (i < this.mapStr[this.currentMapIndex].length && j < this.mapStr[this.currentMapIndex][i].length()) {
                    final char char1 = this.mapStr[this.currentMapIndex][i].charAt(j);
                    if (char1 != ' ' && (j == 0 || !this.pathFinding.isMapEmpty(j - 1, i))) {
                        String s = "" + char1;
                        for (int k = j + 1; k < this.mapStr[this.currentMapIndex][i].length(); ++k) {
                            final char char2 = this.mapStr[this.currentMapIndex][i].charAt(k);
                            if (char2 == ' ' || !this.pathFinding.isMapEmpty(k, i)) {
                                break;
                            }
                            s += char2;
                        }
                        graphics.setColor(Color.darkGray);
                        graphics.drawString(s, n, n2 + 3 * this.cell_y / 2 + 2);
                    }
                }
            }
        }
        this.drawBgDetail(graphics);
        graphics.dispose();
    }
    
    private void updateBackground(final int n, final int n2) {
        if (n < 0 || n >= this.pathFinding.getMapSizeX() || n2 < 0 || n2 >= this.pathFinding.getMapSizeY() || (this.pathFinding.getMap(n, n2) != 7 && this.pathFinding.getMap(n, n2) == 8) || this.backIm == null) {
            return;
        }
        final Graphics graphics = this.backIm.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(this.startx + n * this.cell_x + 6, this.starty + n2 * this.cell_y + 6, this.cell_x * 2 - 12, this.cell_y * 2 - 12);
        graphics.dispose();
    }
    
    private void drawBgDetail(final Graphics graphics) {
        for (int i = 0; i < this.pathFinding.getMapSizeY(); ++i) {
            for (int j = 0; j < this.pathFinding.getMapSizeX(); ++j) {
                this.drawBgDetail(graphics, j, i);
            }
        }
    }
    
    private void drawBgDetail(final Graphics graphics, final int n, final int n2) {
        if (n < 0 || n >= this.pathFinding.getMapSizeX() || n2 < 0 || n2 >= this.pathFinding.getMapSizeY() || this.pathFinding.isMapEmpty(n, n2)) {
            return;
        }
        graphics.setColor(Color.yellow);
        final int n3 = this.startx + n * this.cell_x;
        final int n4 = this.starty + n2 * this.cell_y;
        if (this.pathFinding.getMap(n, n2) == 1) {
            graphics.fillRect(n3 + this.cell_x - 1, n4 + this.cell_y - 1, 2, 2);
        }
        if (this.pathFinding.getMap(n, n2) == 2) {
            graphics.fillRoundRect(n3 + this.cell_x - 6, n4 + this.cell_y - 6, 12, 12, 12, 12);
        }
    }
    
    private void makeSound(final int n) {
        if (!this.soundCheckbox.getState() || jaman.audioClip == null || n > jaman.audioClip.length) {
            return;
        }
        if (jaman.audioClip[n] != null) {
            jaman.audioClip[n].play();
        }
    }
    
    public void setSound() {
        if (jaman.audioClip[0] == null) {
            return;
        }
        if (this.soundCheckbox.getState()) {
            jaman.audioClip[0].loop();
        }
        else {
            jaman.audioClip[0].stop();
        }
    }
    
    public void KeyRight() {
        this.man.setNextDirection(4);
        this.pauseGame = false;
    }
    
    public void KeyLeft() {
        this.man.setNextDirection(12);
        this.pauseGame = false;
    }
    
    public void KeyUp() {
        this.man.setNextDirection(1);
        this.pauseGame = false;
    }
    
    public void KeyDown() {
        this.man.setNextDirection(3);
        this.pauseGame = false;
    }
}
