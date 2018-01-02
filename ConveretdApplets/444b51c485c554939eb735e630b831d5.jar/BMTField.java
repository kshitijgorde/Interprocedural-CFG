import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTField extends Component implements Runnable, KeyListener
{
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    final int BODY_H = 0;
    final int BODY_V = 1;
    final int BODY_TL = 2;
    final int BODY_TR = 3;
    final int BODY_BL = 4;
    final int BODY_BR = 5;
    final int HEAD_L = 6;
    final int HEAD_R = 7;
    final int HEAD_U = 8;
    final int HEAD_D = 9;
    final int APPEAR = 10;
    final int BLOW_L = 11;
    final int BLOW_R = 22;
    final int BLOW_U = 33;
    final int BLOW_D = 44;
    final int NUM_IMAGES = 56;
    final int sleepInterval = 75;
    final int cols = 50;
    final int rows = 35;
    private Thread game;
    BMTron bmt;
    BMTOptions bmto;
    BMTPlayer[] players;
    private int[][] field;
    private int stopedPlayers;
    private long startTime;
    private int time;
    private boolean paused;
    private int winner;
    private Object fieldLock;
    
    BMTField(final BMTOptions bmto) {
        this.game = null;
        this.field = new int[50][35];
        this.stopedPlayers = 0;
        this.paused = false;
        this.winner = -1;
        this.fieldLock = new Object();
        this.bmto = bmto;
        this.bmt = bmto.bmt;
        this.addKeyListener(this);
    }
    
    void start(final BMTPlayer[] players) {
        this.field = new int[50][35];
        this.players = players;
        for (int i = 0; i <= players.length - 1; ++i) {
            players[i].reset();
        }
        if (players.length == 2) {
            this.locatePlayer(0, 15, 17);
            this.locatePlayer(1, 34, 17);
        }
        else if (players.length == 3) {
            this.locatePlayer(0, 17, 12);
            this.locatePlayer(1, 17, 22);
            this.locatePlayer(2, 32, 17);
        }
        else if (players.length == 4) {
            this.locatePlayer(0, 15, 10);
            this.locatePlayer(1, 34, 10);
            this.locatePlayer(2, 15, 24);
            this.locatePlayer(3, 34, 24);
        }
        this.bmt.setStatus(this.getPoints(), "00:00", false);
        for (int j = 0; j <= players.length - 1; ++j) {
            players[j].start();
        }
        for (int k = 0; k <= players.length - 1; ++k) {
            players[k].update();
        }
        this.stopedPlayers = 0;
        if (!this.isVisible()) {
            return;
        }
        this.repaint();
        (this.game = new Thread(this)).start();
        this.requestFocus();
    }
    
    void stop() {
        this.game = null;
        int n = 0;
        for (int i = 0; i <= this.players.length - 1; ++i) {
            if (!this.players[i].stoped) {
                ++n;
                this.winner = i;
            }
        }
        if (n != 1) {
            this.bmt.setStatus("There is no winner", null);
        }
        else {
            final BMTPlayer bmtPlayer = this.players[this.winner];
            ++bmtPlayer.points;
            this.bmt.setStatus(this.players[this.winner].name + " wins!", null);
        }
    }
    
    private void locatePlayer(final int n, final int n2, final int n3) {
        this.players[n].setLocation(n2, n3);
        this.setPlayerBody(n2, n3, n, 10);
    }
    
    public void run() {
        boolean b = true;
        while (b & Thread.currentThread() == this.game) {
            b = false;
            for (int i = 0; i <= this.players.length - 1; ++i) {
                if (this.players[i].getDirection() < 0) {
                    b = true;
                    break;
                }
            }
            try {
                Thread.currentThread();
                Thread.sleep(75L);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
        this.time = 0;
        this.paused = false;
        this.startTime = System.currentTimeMillis();
        long n = System.currentTimeMillis();
        while (Thread.currentThread() == this.game) {
            int n2 = 0;
            while (this.paused) {
                if (n2 <= 3) {
                    this.bmt.setStatus("Pause");
                }
                else {
                    this.bmt.setStatus("");
                }
                if (++n2 > 7) {
                    n2 = 0;
                }
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (Exception ex2) {
                    System.err.println(ex2);
                }
                this.time = -1;
                this.startTime += System.currentTimeMillis() - n;
                n = System.currentTimeMillis();
            }
            this.displayTime();
            for (int j = 0; j <= this.players.length - 1; ++j) {
                if (!this.players[j].stoped) {
                    final Point location = this.players[j].getLocation();
                    int x = location.x;
                    int y = location.y;
                    int n3 = -1;
                    int n4 = -1;
                    int body = this.getBody(location.x, location.y);
                    switch (this.players[j].getDirection()) {
                        case 0: {
                            --y;
                            switch (body) {
                                case 8:
                                case 9:
                                case 10: {
                                    n3 = 1;
                                    break;
                                }
                                case 7: {
                                    n3 = 5;
                                    break;
                                }
                                case 6: {
                                    n3 = 4;
                                    break;
                                }
                            }
                            body = 8;
                            n4 = 33;
                            break;
                        }
                        case 1: {
                            ++x;
                            switch (body) {
                                case 8:
                                case 10: {
                                    n3 = 2;
                                    break;
                                }
                                case 6:
                                case 7: {
                                    n3 = 0;
                                    break;
                                }
                                case 9: {
                                    n3 = 4;
                                    break;
                                }
                            }
                            body = 7;
                            n4 = 22;
                            break;
                        }
                        case 2: {
                            ++y;
                            switch (body) {
                                case 7: {
                                    n3 = 3;
                                    break;
                                }
                                case 8:
                                case 9:
                                case 10: {
                                    n3 = 1;
                                    break;
                                }
                                case 6: {
                                    n3 = 2;
                                    break;
                                }
                            }
                            body = 9;
                            n4 = 44;
                            break;
                        }
                        case 3: {
                            --x;
                            switch (body) {
                                case 8: {
                                    n3 = 3;
                                    break;
                                }
                                case 9:
                                case 10: {
                                    n3 = 5;
                                    break;
                                }
                                case 6:
                                case 7: {
                                    n3 = 0;
                                    break;
                                }
                            }
                            body = 6;
                            n4 = 11;
                            break;
                        }
                    }
                    if (this.isFree(x, y)) {
                        this.setPlayerBody(x, y, j, body);
                        this.setPlayerBody(location.x, location.y, j, n3);
                        this.players[j].setLocation(x, y);
                    }
                    else {
                        this.players[j].stop();
                        this.setPlayerBody(location.x, location.y, j, n3 + n4);
                        ++this.stopedPlayers;
                        if (this.getValue(x, y) > 0) {
                            int n5 = 0;
                            switch (this.getBody(x, y)) {
                                case 8:
                                case 9: {
                                    n5 = 1;
                                    break;
                                }
                                case 6:
                                case 7: {
                                    n5 = 0;
                                    break;
                                }
                                default: {
                                    n5 = -1;
                                    break;
                                }
                            }
                            if (n5 >= 0) {
                                final int player = this.getPlayer(x, y);
                                if (!this.players[player].stoped) {
                                    this.players[player].stop();
                                    ++this.stopedPlayers;
                                    this.setPlayerBody(x, y, player, n5);
                                }
                            }
                        }
                    }
                    this.repaint(Math.min(location.x, x) * 10, Math.min(location.y, y) * 10, (Math.abs(location.x - x) + 1) * 10, (Math.abs(location.y - y) + 1) * 10);
                }
            }
            for (int k = 0; k <= this.players.length - 1; ++k) {
                if (!this.players[k].stoped) {
                    this.players[k].update();
                }
            }
            final long n6 = 75L - (System.currentTimeMillis() - n);
            if (n6 > 0L) {
                try {
                    Thread.currentThread();
                    Thread.sleep(n6);
                }
                catch (Exception ex3) {
                    System.err.println(ex3);
                }
            }
            n = System.currentTimeMillis();
            if (Thread.currentThread() != this.game) {
                return;
            }
            if (this.stopedPlayers >= this.players.length - 1) {
                this.stop();
                int n7 = this.winner;
                if (this.winner >= 0) {
                    n7 = this.players[this.winner].points;
                }
                if (n7 >= 10) {
                    this.bmt.setStatus(this.players[this.winner].name + " wins!", this.getPoints(), true);
                    try {
                        Thread.currentThread();
                        Thread.sleep(2000L);
                    }
                    catch (Exception ex4) {
                        System.err.println(ex4);
                    }
                    this.bmto.setVisible(true);
                    this.setVisible(false);
                    this.bmt.setStatus(null, null, false);
                }
                else {
                    try {
                        Thread.currentThread();
                        Thread.sleep(2000L);
                    }
                    catch (Exception ex5) {
                        System.err.println(ex5);
                    }
                    this.start(this.players);
                }
            }
        }
    }
    
    private String getPoints() {
        String s = "" + this.players[0].points;
        for (int i = 1; i <= this.players.length - 1; ++i) {
            s = s + ":" + this.players[i].points;
        }
        return s;
    }
    
    private void displayTime() {
        final int time = (int)(System.currentTimeMillis() - this.startTime) / 1000;
        if (time != this.time) {
            this.time = time;
            final int n = this.time / 60;
            final int n2 = this.time - n * 60;
            this.bmt.setStatus(((n < 10) ? "0" : "") + n + ((n2 < 10) ? ":0" : ":") + n2);
        }
    }
    
    private void setPlayerBody(final int n, final int n2, final int n3, final int n4) {
        this.setValue(n, n2, 56 * n3 + n4 + 1);
    }
    
    int getPlayer(final int n, final int n2) {
        return (this.getValue(n, n2) - 1) / 56;
    }
    
    int getBody(final int n, final int n2) {
        final int n3 = this.getValue(n, n2) - 1;
        return n3 - n3 / 56 * 56;
    }
    
    boolean isFree(final int n, final int n2) {
        return this.getValue(n, n2) == 0;
    }
    
    int getValue(final int n, final int n2) {
        synchronized (this.fieldLock) {
            if (n < 0 | n2 < 0 | n >= this.field.length | n2 >= this.field[0].length) {
                return -1;
            }
            return this.field[n][n2];
        }
    }
    
    private void setValue(final int n, final int n2, final int n3) {
        synchronized (this.fieldLock) {
            this.field[n][n2] = n3;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final int width = this.bmt.skin.field.getWidth(this);
        final int height = this.bmt.skin.field.getHeight(this);
        for (int i = clipBounds.x / width * width; i < clipBounds.x + clipBounds.width; i += width) {
            for (int j = clipBounds.y / height * height; j < clipBounds.y + clipBounds.height; j += height) {
                graphics.drawImage(this.bmt.skin.field, i, j, this);
            }
        }
        int n = (clipBounds.x + clipBounds.width) / 10;
        if (n * 10 < clipBounds.x + clipBounds.width) {
            ++n;
        }
        int n2 = (clipBounds.y + clipBounds.height) / 10;
        if (n2 * 10 < clipBounds.y + clipBounds.height) {
            ++n2;
        }
        for (int k = clipBounds.x / 10; k < n; ++k) {
            for (int l = clipBounds.y / 10; l < n2; ++l) {
                if (this.getValue(k, l) > 0) {
                    final int body = this.getBody(k, l);
                    if (body < 10) {
                        graphics.drawImage(this.players[this.getPlayer(k, l)].getImage(body), k * 10, l * 10, this);
                    }
                    else if (body == 10) {
                        this.players[this.getPlayer(k, l)].getAppear().drawFrame(graphics, k * 10 - 20, l * 10 - 20, this);
                    }
                }
            }
        }
        for (int n3 = clipBounds.x / 10; n3 < n; ++n3) {
            for (int n4 = clipBounds.y / 10; n4 < n2; ++n4) {
                if (this.getValue(n3, n4) > 0) {
                    final int body2 = this.getBody(n3, n4);
                    if (body2 >= 44) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 44), n3 * 10, n4 * 10, this);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 10, n4 * 10 - 5, this);
                    }
                    else if (body2 >= 33) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 33), n3 * 10, n4 * 10, this);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 10, n4 * 10 - 15, this);
                    }
                    else if (body2 >= 22) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 22), n3 * 10, n4 * 10, this);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 5, n4 * 10 - 10, this);
                    }
                    else if (body2 >= 11) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 11), n3 * 10, n4 * 10, this);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 15, n4 * 10 - 10, this);
                    }
                }
            }
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 27: {
                if (!this.paused) {
                    this.stop();
                    this.bmt.setStatus("BMTron" + " " + "1.0.4", this.getPoints(), false);
                    this.bmto.setVisible(true);
                    this.setVisible(false);
                    break;
                }
                this.paused = false;
                break;
            }
            case 19: {
                this.paused = !this.paused;
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public boolean isOpaque() {
        return true;
    }
}
