// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.Component;

public class Field extends Component implements Runnable, KeyListener
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
    final int interval = 75;
    final int cols = 50;
    final int rows = 35;
    final int c2x = 10;
    final int r2y = 10;
    private Thread thread;
    final Game game;
    private SettingsScreen settings;
    Player[] players;
    private int[][] field;
    private int stopedPlayers;
    private long startTime;
    private int time;
    private boolean paused;
    private int winner;
    private Object fieldLock;
    
    Field(final SettingsScreen settings) {
        this.thread = null;
        this.field = new int[50][35];
        this.stopedPlayers = 0;
        this.paused = false;
        this.winner = -1;
        this.fieldLock = new Object();
        this.settings = settings;
        this.game = settings.game;
        this.addKeyListener(this);
    }
    
    void start(final Player[] players) {
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
        this.game.setStatus(this.getScore(), "00:00", false);
        for (int j = 0; j <= players.length - 1; ++j) {
            players[j].start();
        }
        this.stopedPlayers = 0;
        if (!this.isVisible()) {
            return;
        }
        this.repaint();
        (this.thread = new Thread(this)).start();
    }
    
    void stop() {
        final Thread thread = this.thread;
        this.thread = null;
        if (thread != null) {
            thread.interrupt();
        }
    }
    
    private void locatePlayer(final int n, final int n2, final int n3) {
        this.players[n].setLocation(n2, n3);
        this.setPlayerBody(n2, n3, n, 10);
    }
    
    public void run() {
        this.waitToStart();
        boolean endRound = false;
        while (Thread.currentThread() == this.thread) {
            final long waitWhilePaused = this.waitWhilePaused();
            this.displayTime();
            this.move();
            final long n = 75L - (System.currentTimeMillis() - waitWhilePaused);
            if (n > 0L) {
                try {
                    Thread.currentThread();
                    Thread.sleep(n);
                }
                catch (Exception ex) {}
            }
            if (Thread.currentThread() != this.thread) {
                break;
            }
            if (this.stopedPlayers >= this.players.length - 1) {
                endRound = this.endRound();
                break;
            }
        }
        for (int i = 0; i <= this.players.length - 1; ++i) {
            if (!this.players[i].isStoped()) {
                this.players[i].stop();
            }
        }
        this.stopedPlayers = this.players.length;
        if (endRound) {
            this.start(this.players);
        }
    }
    
    private boolean endRound() {
        int n = 0;
        for (int i = 0; i <= this.players.length - 1; ++i) {
            if (!this.players[i].isStoped()) {
                ++n;
                this.winner = i;
            }
        }
        if (n != 1) {
            this.game.setStatus("There is no winner", null);
        }
        else {
            final Player player = this.players[this.winner];
            ++player.points;
            this.game.setStatus(this.players[this.winner].name + " wins!", null);
        }
        int points = -1;
        if (this.winner >= 0) {
            points = this.players[this.winner].points;
        }
        if (points >= 10) {
            this.game.setStatus(this.players[this.winner].name + " wins!", this.getScore(), true);
            try {
                Thread.currentThread();
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
            this.settings.setVisible(true);
            this.setVisible(false);
            this.game.setStatus(null, null, false);
            return false;
        }
        try {
            Thread.currentThread();
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex2) {}
        return Thread.currentThread() == this.thread;
    }
    
    private void waitToStart() {
        boolean b = true;
        while (b & Thread.currentThread() == this.thread) {
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
            catch (InterruptedException ex) {}
        }
        this.time = 0;
        this.paused = false;
        this.startTime = System.currentTimeMillis();
    }
    
    private long waitWhilePaused() {
        long n = System.currentTimeMillis();
        int n2 = 0;
        while (this.paused) {
            if (n2 <= 3) {
                this.game.setStatus("Pause");
            }
            else {
                this.game.setStatus("");
            }
            if (++n2 > 7) {
                n2 = 0;
            }
            try {
                Thread.currentThread();
                Thread.sleep(250L);
            }
            catch (InterruptedException ex) {}
            this.time = -1;
            this.startTime += System.currentTimeMillis() - n;
            n = System.currentTimeMillis();
        }
        return n;
    }
    
    private void move() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;
        for (int i = 0; i <= this.players.length - 1; ++i) {
            if (!this.players[i].isStoped()) {
                final Point location = this.players[i].getLocation();
                int x3 = location.x;
                int y3 = location.y;
                int n = -1;
                int n2 = -1;
                int body = this.getBody(location.x, location.y);
                switch (this.players[i].getDirection()) {
                    case 0: {
                        --y3;
                        switch (body) {
                            case 8:
                            case 9:
                            case 10: {
                                n = 1;
                                break;
                            }
                            case 7: {
                                n = 5;
                                break;
                            }
                            case 6: {
                                n = 4;
                                break;
                            }
                        }
                        body = 8;
                        n2 = 33;
                        break;
                    }
                    case 1: {
                        ++x3;
                        switch (body) {
                            case 8:
                            case 10: {
                                n = 2;
                                break;
                            }
                            case 6:
                            case 7: {
                                n = 0;
                                break;
                            }
                            case 9: {
                                n = 4;
                                break;
                            }
                        }
                        body = 7;
                        n2 = 22;
                        break;
                    }
                    case 2: {
                        ++y3;
                        switch (body) {
                            case 7: {
                                n = 3;
                                break;
                            }
                            case 8:
                            case 9:
                            case 10: {
                                n = 1;
                                break;
                            }
                            case 6: {
                                n = 2;
                                break;
                            }
                        }
                        body = 9;
                        n2 = 44;
                        break;
                    }
                    case 3: {
                        --x3;
                        switch (body) {
                            case 8: {
                                n = 3;
                                break;
                            }
                            case 9:
                            case 10: {
                                n = 5;
                                break;
                            }
                            case 6:
                            case 7: {
                                n = 0;
                                break;
                            }
                        }
                        body = 6;
                        n2 = 11;
                        break;
                    }
                }
                if (this.isFree(x3, y3)) {
                    this.setPlayerBody(x3, y3, i, body);
                    this.setPlayerBody(location.x, location.y, i, n);
                    this.players[i].setLocation(x3, y3);
                }
                else {
                    this.players[i].stop();
                    this.setPlayerBody(location.x, location.y, i, n + n2);
                    ++this.stopedPlayers;
                    if (this.getValue(x3, y3) > 0) {
                        int n3 = 0;
                        switch (this.getBody(x3, y3)) {
                            case 8:
                            case 9: {
                                n3 = 1;
                                break;
                            }
                            case 6:
                            case 7: {
                                n3 = 0;
                                break;
                            }
                            default: {
                                n3 = -1;
                                break;
                            }
                        }
                        if (n3 >= 0) {
                            final int player = this.getPlayer(x3, y3);
                            if (!this.players[player].isStoped()) {
                                this.players[player].stop();
                                ++this.stopedPlayers;
                                this.setPlayerBody(x3, y3, player, n3);
                            }
                        }
                    }
                }
                if (x3 < x) {
                    x = x3;
                }
                if (x3 > x2) {
                    x2 = x3;
                }
                if (y3 < y) {
                    y = y3;
                }
                if (y3 > y2) {
                    y2 = y3;
                }
                if (location.x < x) {
                    x = location.x;
                }
                if (location.x > x2) {
                    x2 = location.x;
                }
                if (location.y < y) {
                    y = location.y;
                }
                if (location.y > y2) {
                    y2 = location.y;
                }
            }
        }
        this.repaint(x * 10, y * 10, (x2 - x + 1) * 10, (y2 - y + 1) * 10);
        for (int j = 0; j <= this.players.length - 1; ++j) {
            if (!this.players[j].isStoped()) {
                this.players[j].update();
            }
        }
    }
    
    private String getScore() {
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
            this.game.setStatus(((n < 10) ? "0" : "") + n + ((n2 < 10) ? ":0" : ":") + n2);
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
        final int width = this.game.skin.field.getWidth(null);
        final int height = this.game.skin.field.getHeight(null);
        for (int i = clipBounds.x / width * width; i < clipBounds.x + clipBounds.width; i += width) {
            for (int j = clipBounds.y / height * height; j < clipBounds.y + clipBounds.height; j += height) {
                graphics.drawImage(this.game.skin.field, i, j, null);
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
                        graphics.drawImage(this.players[this.getPlayer(k, l)].getImage(body), k * 10, l * 10, null);
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
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 44), n3 * 10, n4 * 10, null);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 10, n4 * 10 - 5, this);
                    }
                    else if (body2 >= 33) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 33), n3 * 10, n4 * 10, null);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 10, n4 * 10 - 15, this);
                    }
                    else if (body2 >= 22) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 22), n3 * 10, n4 * 10, null);
                        this.players[this.getPlayer(n3, n4)].getBlow().drawFrame(graphics, n3 * 10 - 5, n4 * 10 - 10, this);
                    }
                    else if (body2 >= 11) {
                        graphics.drawImage(this.players[this.getPlayer(n3, n4)].getImage(body2 - 11), n3 * 10, n4 * 10, null);
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
                    final Game game = this.game;
                    final StringBuffer sb = new StringBuffer();
                    final Game game2 = this.game;
                    final StringBuffer append = sb.append("BMTron").append(" ");
                    final Game game3 = this.game;
                    game.setStatus(append.append("1.1").toString(), this.getScore(), false);
                    this.settings.setVisible(true);
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
