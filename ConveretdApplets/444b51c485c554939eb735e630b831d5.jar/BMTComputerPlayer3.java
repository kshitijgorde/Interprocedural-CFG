import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTComputerPlayer3 extends BMTPlayer implements Runnable
{
    private Thread thread;
    private boolean[][] orField;
    private int[][] rField;
    private boolean pack;
    private boolean packOR;
    private boolean alwaysPack;
    private int step;
    private Random random;
    private Object threadLock;
    
    BMTComputerPlayer3(final BMTField bmtField, final String s, final Color color) {
        super(bmtField, s, color);
        this.thread = null;
        this.packOR = false;
        this.random = new Random();
        this.threadLock = new Object();
    }
    
    void start() {
        this.alwaysPack = false;
        this.step = 1;
        for (int i = 0; i < super.field.players.length; ++i) {
            if (super.field.players[i] instanceof BMTComputerPlayer3) {
                ++this.step;
            }
            if (super.field.players[i] == this) {
                break;
            }
        }
        this.step = this.step % 2 + 1;
        super.start();
    }
    
    void stop() {
        synchronized (this.threadLock) {
            this.thread = null;
        }
        super.stop();
    }
    
    void update() {
        synchronized (this.threadLock) {
            if (this.thread != null) {
                return;
            }
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void run() {
        BMTron.trace(this + ".run()");
        ++this.step;
        if (this.step > 2) {
            this.step = 1;
        }
        final Point location = this.getLocation();
        this.orField = new boolean[50][35];
        this.rField = new int[50][35];
        this.pack = true;
        int n = 0;
        for (int i = 0; i < 50; ++i) {
            for (int j = 0; j < 35; ++j) {
                final int largestRectArea = this.getLargestRectArea(i, j);
                if (largestRectArea > n) {
                    n = largestRectArea;
                }
            }
        }
        if (this.pack && !this.alwaysPack) {
            if (this.step == 1) {
                this.packOR = (!this.isOpponentReachable(location.x - 1, location.y) && !this.isOpponentReachable(location.x + 1, location.y) && !this.isOpponentReachable(location.x, location.y - 1) && !this.isOpponentReachable(location.x, location.y + 1));
            }
            this.pack = this.packOR;
        }
        if (this.pack) {
            this.alwaysPack = true;
        }
        for (int k = 0; k <= super.field.players.length - 1; ++k) {
            if (super.field.players[k] != this & !super.field.players[k].stoped) {
                final Point location2 = super.field.players[k].getLocation();
                int n2 = location2.x - 1;
                if (n2 < 0) {
                    n2 = 0;
                }
                int n3 = location2.y - 1;
                if (n3 < 0) {
                    n3 = 0;
                }
                int n4 = location2.x + 1;
                if (n4 > 50 - 1) {
                    n4 = 50 - 1;
                }
                int n5 = location2.y + 1;
                if (n5 > 35 - 1) {
                    n5 = 35 - 1;
                }
                for (int l = n2; l <= n4; ++l) {
                    for (int n6 = n3; n6 <= n5; ++n6) {
                        if (this.rField[l][n6] > 0) {
                            this.rField[l][n6] = -this.rField[l][n6];
                            if (-this.rField[l][n6] > n) {
                                n = -this.rField[l][n6];
                            }
                        }
                    }
                }
            }
        }
        int direction = 0;
        Path shortestPath = this.findShortestPath(location.x, location.y - 1, n);
        final Path shortestPath2 = this.findShortestPath(location.x + 1, location.y, n);
        if (this.isBetterPath(shortestPath, shortestPath2)) {
            shortestPath = shortestPath2;
            direction = 1;
        }
        final Path shortestPath3 = this.findShortestPath(location.x, location.y + 1, n);
        if (this.isBetterPath(shortestPath, shortestPath3)) {
            shortestPath = shortestPath3;
            direction = 2;
        }
        if (this.isBetterPath(shortestPath, this.findShortestPath(location.x - 1, location.y, n))) {
            direction = 3;
        }
        synchronized (this.threadLock) {
            if (Thread.currentThread() != this.thread) {
                return;
            }
            this.thread = null;
        }
        this.setDirection(direction);
        BMTron.trace("   pack = " + this.pack);
        BMTron.trace("----------------");
    }
    
    private boolean isBetterPath(final Path path, final Path path2) {
        return path2.rectArea > path.rectArea || (path2.rectArea == path.rectArea && ((this.pack & path2.length > path.length) | (!this.pack & path2.length < path.length)));
    }
    
    private Path findShortestPath(final int n, final int n2, final int n3) {
        final boolean[][] array = new boolean[50][35];
        final Vector vector = new Vector<Point>();
        final Path path = new Path(0, -1);
        vector.add(new Point(n, n2));
    Label_0364:
        while (!vector.isEmpty()) {
            final Path path2 = path;
            ++path2.length;
            final int size = vector.size();
            for (int i = 0; i < size; ++i) {
                final Point point = vector.elementAt(i);
                if (super.field.isFree(point.x, point.y) && !array[point.x][point.y] && !this.isDangerousTunnel(point.x, point.y)) {
                    if (Math.abs(this.rField[point.x][point.y]) > path.rectArea) {
                        path.rectArea = Math.abs(this.rField[point.x][point.y]);
                        if (path.rectArea >= n3) {
                            break Label_0364;
                        }
                    }
                    array[point.x][point.y] = true;
                    vector.add(new Point(point.x - 1, point.y));
                    vector.add(new Point(point.x + 1, point.y));
                    vector.add(new Point(point.x, point.y - 1));
                    vector.add(new Point(point.x, point.y + 1));
                }
            }
            for (int j = size - 1; j >= 0; --j) {
                vector.remove(j);
            }
        }
        return path;
    }
    
    private boolean isOpponentReachable(final int n, final int n2) {
        if (!super.field.isFree(n, n2)) {
            return false;
        }
        if (this.orField[n][n2]) {
            return false;
        }
        this.orField[n][n2] = true;
        for (int i = 0; i < super.field.players.length; ++i) {
            if (super.field.players[i] != this & !super.field.players[i].stoped) {
                final Point location = super.field.players[i].getLocation();
                if (location.x == n - 1 & location.y == n2) {
                    return true;
                }
                if (location.x == n + 1 & location.y == n2) {
                    return true;
                }
                if (location.x == n & location.y == n2 - 1) {
                    return true;
                }
                if (location.x == n & location.y == n2 + 1) {
                    return true;
                }
            }
        }
        return this.isOpponentReachable(n - 1, n2) || this.isOpponentReachable(n + 1, n2) || this.isOpponentReachable(n, n2 - 1) || this.isOpponentReachable(n, n2 + 1);
    }
    
    private boolean isDangerousTunnel(final int n, final int n2) {
        if (this.isWall(n - 1, n2) & this.isWall(n + 1, n2)) {
            for (int i = 0; i < super.field.players.length; ++i) {
                if (super.field.players[i] != this & !super.field.players[i].stoped) {
                    final Point location = super.field.players[i].getLocation();
                    if ((location.x == n - 1 | location.x == n + 1) & location.y == n2) {
                        return true;
                    }
                }
            }
        }
        if (this.isWall(n, n2 - 1) & this.isWall(n, n2 + 1)) {
            for (int j = 0; j < super.field.players.length; ++j) {
                if (super.field.players[j] != this & !super.field.players[j].stoped) {
                    final Point location2 = super.field.players[j].getLocation();
                    if ((location2.y == n2 - 1 | location2.y == n2 + 1) & location2.x == n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isWall(final int n, final int n2) {
        final Point location = this.getLocation();
        return !(location.x == n & location.y == n2) && !super.field.isFree(n, n2);
    }
    
    private int getLargestRectArea(final int n, final int n2) {
        if (!super.field.isFree(n, n2)) {
            return 0;
        }
        if (this.rField[n][n2] != 0) {
            return this.rField[n][n2];
        }
        final Rectangle rectangle = new Rectangle(n, n2, 1, 1);
        boolean b = true;
        while (b) {
            final boolean b2 = false;
            boolean b3 = true;
            for (int i = rectangle.x; i <= rectangle.width + rectangle.x - 1; ++i) {
                b3 &= super.field.isFree(i, rectangle.y - 1);
                if (!b3) {
                    break;
                }
            }
            final boolean b4 = b2 | b3;
            if (b3) {
                final Rectangle rectangle2 = rectangle;
                --rectangle2.y;
                final Rectangle rectangle3 = rectangle;
                ++rectangle3.height;
            }
            boolean b5 = true;
            for (int j = rectangle.x; j <= rectangle.width + rectangle.x - 1; ++j) {
                b5 &= super.field.isFree(j, rectangle.y + rectangle.height);
                if (!b5) {
                    break;
                }
            }
            final boolean b6 = b4 | b5;
            if (b5) {
                final Rectangle rectangle4 = rectangle;
                ++rectangle4.height;
            }
            boolean b7 = true;
            for (int k = rectangle.y; k <= rectangle.height + rectangle.y - 1; ++k) {
                b7 &= super.field.isFree(rectangle.x - 1, k);
                if (!b7) {
                    break;
                }
            }
            final boolean b8 = b6 | b7;
            if (b7) {
                final Rectangle rectangle5 = rectangle;
                --rectangle5.x;
                final Rectangle rectangle6 = rectangle;
                ++rectangle6.width;
            }
            boolean b9 = true;
            for (int l = rectangle.y; l <= rectangle.height + rectangle.y - 1; ++l) {
                b9 &= super.field.isFree(rectangle.x + rectangle.width, l);
                if (!b9) {
                    break;
                }
            }
            b = (b8 | b9);
            if (b9) {
                final Rectangle rectangle7 = rectangle;
                ++rectangle7.width;
            }
        }
        final int n3 = rectangle.width * rectangle.height;
        for (int x = rectangle.x; x < rectangle.width + rectangle.x; ++x) {
            for (int y = rectangle.y; y < rectangle.height + rectangle.y; ++y) {
                if (this.rField[x][y] < n3) {
                    this.rField[x][y] = n3;
                }
                final Point location = this.getLocation();
                if (this.pack && !this.alwaysPack && location.x > rectangle.x && location.y > rectangle.y && location.x < rectangle.x + rectangle.width && location.y < rectangle.y + rectangle.height) {
                    for (int n4 = 0; n4 < super.field.players.length; ++n4) {
                        if (super.field.players[n4] != this & !super.field.players[n4].stoped) {
                            final Point location2 = super.field.players[n4].getLocation();
                            if ((location2.x == x - 1 && location2.y == y) || (location2.x == x + 1 && location2.y == y) || (location2.x == x && location2.y == y - 1) || (location2.x == x && location2.y == y + 1)) {
                                this.pack = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return n3;
    }
    
    private class Path
    {
        int rectArea;
        int length;
        
        Path(final int rectArea, final int length) {
            this.rectArea = rectArea;
            this.length = length;
        }
    }
}
