import java.util.Vector;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class MazeBuilder implements Runnable
{
    public static final int CW_TOP = 1;
    public static final int CW_BOT = 2;
    public static final int CW_LEFT = 4;
    public static final int CW_RIGHT = 8;
    static final int CW_VIRGIN = 16;
    public static final int CW_ALL = 15;
    static final int CW_BOUND_SHIFT = 5;
    static final int CW_TOP_BOUND = 32;
    static final int CW_BOT_BOUND = 64;
    static final int CW_LEFT_BOUND = 128;
    static final int CW_RIGHT_BOUND = 256;
    static final int CW_ALL_BOUNDS = 480;
    static final int CW_IN_ROOM = 512;
    public static int[] dirsx;
    public static int[] dirsy;
    int width;
    int height;
    int startx;
    int starty;
    int[][] cells;
    int[][] origdirs;
    int[][] dists;
    Random random;
    Maze maze;
    int partiters;
    Vector seglist;
    static final int map_unit = 128;
    int colchange;
    Thread buildThread;
    int rooms;
    int expected_partiters;
    
    MazeBuilder() {
        this.partiters = 0;
    }
    
    boolean can_go(final int n, final int n2, final int n3, final int n4) {
        return (this.cells[n][n2] & this.get_bit(n3, n4) << 5) == 0x0 && (this.cells[n + n3][n2 + n4] & 0x10) != 0x0;
    }
    
    int get_bit(final int n, final int n2) {
        int n3 = 0;
        switch (n + n2 * 2) {
            case 1: {
                n3 = 8;
                break;
            }
            case -1: {
                n3 = 4;
                break;
            }
            case 2: {
                n3 = 2;
                break;
            }
            case -2: {
                n3 = 1;
                break;
            }
            default: {
                this.dbg("get_bit problem " + n + " " + n2);
                break;
            }
        }
        return n3;
    }
    
    int randno(final int n, final int n2) {
        return Math.abs(this.random.nextInt()) % (n2 - n + 1) + n;
    }
    
    void delwall(final int n, final int n2, final int n3, final int n4) {
        final int get_bit = this.get_bit(n3, n4);
        final int[] array = this.cells[n];
        array[n2] &= ~get_bit;
        final int get_bit2 = this.get_bit(-n3, -n4);
        final int[] array2 = this.cells[n + n3];
        final int n5 = n2 + n4;
        array2[n5] &= ~get_bit2;
    }
    
    void delbound(final int n, final int n2, final int n3, final int n4) {
        final int get_bit = this.get_bit(n3, n4);
        final int[] array = this.cells[n];
        array[n2] &= ~(get_bit << 5);
        final int get_bit2 = this.get_bit(-n3, -n4);
        final int[] array2 = this.cells[n + n3];
        final int n5 = n2 + n4;
        array2[n5] &= ~(get_bit2 << 5);
    }
    
    void addboundwall(final int n, final int n2, final int n3, final int n4) {
        final int get_bit = this.get_bit(n3, n4);
        final int[] array = this.cells[n];
        array[n2] |= (get_bit | get_bit << 5);
        final int get_bit2 = this.get_bit(-n3, -n4);
        final int[] array2 = this.cells[n + n3];
        final int n5 = n2 + n4;
        array2[n5] |= (get_bit2 | get_bit2 << 5);
    }
    
    int grade_partition(final Vector vector, final Seg seg) {
        final int x = seg.x;
        final int y = seg.y;
        final int dx = seg.dx;
        final int dy = seg.dy;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        if (vector.size() >= 100) {
            n4 = vector.size() / 50;
        }
        for (int i = 0; i < vector.size(); i += n4) {
            final Seg seg2 = vector.elementAt(i);
            final int n5 = seg2.x - x;
            final int n6 = seg2.y - y;
            final int n7 = seg2.x + seg2.dx;
            final int n8 = seg2.y + seg2.dy;
            final int n9 = n7 - x;
            final int n10 = n8 - y;
            final int n11 = dy;
            final int n12 = -dx;
            int n13 = n5 * n11 + n6 * n12;
            final int n14 = n9 * n11 + n10 * n12;
            if (getSign(n13) != getSign(n14)) {
                if (n13 == 0) {
                    n13 = n14;
                }
                else if (n14 != 0) {
                    ++n3;
                    continue;
                }
            }
            if (n13 > 0 || (n13 == 0 && seg2.GetDir() == seg.GetDir())) {
                ++n2;
            }
            else if (n13 < 0 || (n13 == 0 && seg2.GetDir() == -seg.GetDir())) {
                ++n;
            }
            else {
                this.dbg("grade_partition problem: dot1 = " + n13 + ", dot2 = " + n14);
            }
        }
        return Math.abs(n - n2) + n3 * 3;
    }
    
    static int getSign(final int n) {
        return (n < 0) ? -1 : ((n > 0) ? 1 : 0);
    }
    
    void Initialize() {
        for (int i = 0; i != this.width; ++i) {
            for (int j = 0; j != this.height; ++j) {
                this.cells[i][j] = 31;
            }
            final int[] array = this.cells[i];
            final int n = 0;
            array[n] |= 0x20;
            final int[] array2 = this.cells[i];
            final int n2 = this.height - 1;
            array2[n2] |= 0x40;
        }
        for (int k = 0; k != this.height; ++k) {
            final int[] array3 = this.cells[0];
            final int n3 = k;
            array3[n3] |= 0x80;
            final int[] array4 = this.cells[this.width - 1];
            final int n4 = k;
            array4[n4] |= 0x100;
        }
    }
    
    void Generate() {
        int n = 0;
        final int randno;
        int n2 = randno = this.randno(0, this.width - 1);
        int n4;
        int n3 = n4 = 0;
        final int[] array = this.cells[n2];
        final int n5 = n;
        array[n5] &= 0xFFFFFFEF;
        while (true) {
            final int n6 = MazeBuilder.dirsx[n3];
            final int n7 = MazeBuilder.dirsy[n3];
            if (!this.can_go(n2, n, n6, n7)) {
                n3 = (n3 + 1 & 0x3);
                if (n4 != n3) {
                    continue;
                }
                if (n2 == randno && n == 0) {
                    break;
                }
                final int n8 = this.origdirs[n2][n];
                final int n9 = MazeBuilder.dirsx[n8];
                final int n10 = MazeBuilder.dirsy[n8];
                n2 -= n9;
                n -= n10;
                n3 = (n4 = this.randno(0, 3));
            }
            else {
                this.delwall(n2, n, n6, n7);
                n2 += n6;
                n += n7;
                final int[] array2 = this.cells[n2];
                final int n11 = n;
                array2[n11] &= 0xFFFFFFEF;
                this.origdirs[n2][n] = n3;
                n3 = (n4 = this.randno(0, 3));
            }
        }
        this.computeDists(this.width / 2, this.height / 2);
        int n12 = -1;
        int n13 = -1;
        int n14 = 0;
        for (int i = 0; i != this.width; ++i) {
            if (this.dists[i][0] > n14) {
                n12 = i;
                n13 = 0;
                n14 = this.dists[i][0];
            }
            if (this.dists[i][this.height - 1] > n14) {
                n12 = i;
                n13 = this.height - 1;
                n14 = this.dists[i][this.height - 1];
            }
        }
        for (int j = 0; j != this.height; ++j) {
            if (this.dists[0][j] > n14) {
                n12 = 0;
                n13 = j;
                n14 = this.dists[0][j];
            }
            if (this.dists[this.width - 1][j] > n14) {
                n12 = this.width - 1;
                n13 = j;
                n14 = this.dists[this.width - 1][j];
            }
        }
        this.computeDists(n12, n13);
        int n15 = 0;
        for (int k = 0; k != this.width; ++k) {
            for (int l = 0; l != this.height; ++l) {
                if (this.dists[k][l] > n15) {
                    this.startx = k;
                    this.starty = l;
                    n15 = this.dists[k][l];
                }
            }
        }
        int n16 = 0;
        if (n12 == 0) {
            n16 = 4;
        }
        else if (n12 == this.width - 1) {
            n16 = 8;
        }
        else if (n13 == 0) {
            n16 = 1;
        }
        else if (n13 == this.height - 1) {
            n16 = 2;
        }
        else {
            this.dbg("Generate 1");
        }
        final int[] array3 = this.cells[n12];
        final int n17 = n13;
        array3[n17] &= ~n16;
    }
    
    void computeDists(final int n, final int n2) {
        final int n3 = 99999999;
        for (int i = 0; i != this.width; ++i) {
            for (int j = 0; j != this.height; ++j) {
                this.dists[i][j] = n3;
            }
        }
        this.dists[n][n2] = 1;
        boolean b;
        do {
            b = true;
            for (int k = 0; k != this.width; ++k) {
                for (int l = 0; l != this.height; ++l) {
                    int n4 = k;
                    int n5 = l;
                    int n6 = this.dists[n4][n5];
                    if (n6 == n3) {
                        b = false;
                    }
                    else {
                        int n7 = 0;
                        while (true) {
                            int n8 = -1;
                            final int n9 = this.cells[n4][n5];
                            for (int n10 = 0; n10 != 4; ++n10) {
                                final int n11 = n4 + MazeBuilder.dirsx[n10];
                                final int n12 = n5 + MazeBuilder.dirsy[n10];
                                if ((n9 & Maze.masks[n10]) == 0x0 && this.dists[n11][n12] > n6 + 1) {
                                    this.dists[n11][n12] = n6 + 1;
                                    n8 = n10;
                                }
                            }
                            ++n7;
                            if (n8 == -1) {
                                break;
                            }
                            n4 += MazeBuilder.dirsx[n8];
                            n5 += MazeBuilder.dirsy[n8];
                            ++n6;
                        }
                    }
                }
            }
        } while (!b);
    }
    
    boolean PlaceRoom() {
        final int randno = this.randno(3, 8);
        final int randno2 = this.randno(3, 8);
        if (randno >= this.width - 4) {
            return false;
        }
        if (randno2 >= this.height - 4) {
            return false;
        }
        final int randno3 = this.randno(1, this.width - randno - 1);
        final int randno4 = this.randno(1, this.height - randno2 - 1);
        final int n = randno3 + randno - 1;
        final int n2 = randno4 + randno2 - 1;
        for (int i = randno3 - 1; i <= n + 1; ++i) {
            for (int j = randno4 - 1; j <= n2 + 1; ++j) {
                if ((this.cells[i][j] & 0x200) != 0x0) {
                    return false;
                }
            }
        }
        for (int k = randno3; k <= n; ++k) {
            for (int l = randno4; l <= n2; ++l) {
                final int[] array = this.cells[k];
                final int n3 = l;
                array[n3] &= 0xFFFFFFF0;
                final int[] array2 = this.cells[k];
                final int n4 = l;
                array2[n4] |= 0x200;
            }
        }
        for (int n5 = randno3; n5 <= n; ++n5) {
            this.addboundwall(n5, randno4, 0, -1);
            this.addboundwall(n5, n2, 0, 1);
        }
        for (int n6 = randno4; n6 <= n2; ++n6) {
            this.addboundwall(randno3, n6, -1, 0);
            this.addboundwall(n, n6, 1, 0);
        }
        final int n7 = (randno + randno2) * 2;
        for (int n8 = 0; n8 != 5; ++n8) {
            final int randno5 = this.randno(0, n7 - 1);
            int n9;
            int n10;
            int n11;
            int n12;
            if (randno5 < randno * 2) {
                n9 = ((randno5 < randno) ? 0 : (randno2 - 1));
                n10 = ((randno5 < randno) ? -1 : 1);
                n11 = randno5 % randno;
                n12 = 0;
            }
            else {
                final int n13 = randno5 - randno * 2;
                n11 = ((n13 < randno2) ? 0 : (randno - 1));
                n12 = ((n13 < randno2) ? -1 : 1);
                n9 = n13 % randno2;
                n10 = 0;
            }
            this.delbound(n11 + randno3, n9 + randno4, n12, n10);
        }
        return true;
    }
    
    void GenSegs() {
        final Vector seglist = new Vector<Seg>();
        for (int i = 0; i != this.height; ++i) {
            int j = 0;
            while (j < this.width) {
                if ((this.cells[j][i] & 0x1) == 0x0) {
                    ++j;
                }
                else {
                    final int n = j;
                    while ((this.cells[j][i] & 0x1) != 0x0) {
                        if (++j == this.width) {
                            break;
                        }
                        if ((this.cells[j][i] & 0x4) != 0x0) {
                            break;
                        }
                    }
                    seglist.addElement(new Seg(j * 128, i * 128, (n - j) * 128, 0, this.dists[n][i], this.colchange));
                }
            }
            int k = 0;
            while (k < this.width) {
                if ((this.cells[k][i] & 0x2) == 0x0) {
                    ++k;
                }
                else {
                    final int n2 = k;
                    while ((this.cells[k][i] & 0x2) != 0x0) {
                        if (++k == this.width) {
                            break;
                        }
                        if ((this.cells[k][i] & 0x4) != 0x0) {
                            break;
                        }
                    }
                    seglist.addElement(new Seg(n2 * 128, (i + 1) * 128, (k - n2) * 128, 0, this.dists[n2][i], this.colchange));
                }
            }
        }
        for (int l = 0; l != this.width; ++l) {
            int n3 = 0;
            while (n3 < this.height) {
                if ((this.cells[l][n3] & 0x4) == 0x0) {
                    ++n3;
                }
                else {
                    final int n4 = n3;
                    while ((this.cells[l][n3] & 0x4) != 0x0) {
                        if (++n3 == this.height) {
                            break;
                        }
                        if ((this.cells[l][n3] & 0x1) != 0x0) {
                            break;
                        }
                    }
                    seglist.addElement(new Seg(l * 128, n4 * 128, 0, (n3 - n4) * 128, this.dists[l][n4], this.colchange));
                }
            }
            int n5 = 0;
            while (n5 < this.height) {
                if ((this.cells[l][n5] & 0x8) == 0x0) {
                    ++n5;
                }
                else {
                    final int n6 = n5;
                    while ((this.cells[l][n5] & 0x8) != 0x0) {
                        if (++n5 == this.height) {
                            break;
                        }
                        if ((this.cells[l][n5] & 0x1) != 0x0) {
                            break;
                        }
                    }
                    seglist.addElement(new Seg((l + 1) * 128, n5 * 128, 0, (n6 - n5) * 128, this.dists[l][n6], this.colchange));
                }
            }
        }
        this.seglist = seglist;
        for (int n7 = 0; n7 != seglist.size(); ++n7) {
            final Seg seg = seglist.elementAt(n7);
            if (((seg.x == 0 || seg.x == this.width) && seg.dx == 0) || ((seg.y == 0 || seg.y == this.height) && seg.dy == 0)) {
                seg.partition = true;
            }
        }
        final int[] array = this.cells[0];
        final int n8 = 0;
        array[n8] |= 0x1;
    }
    
    BSPNode GenNodes() {
        return this.GenNodes(this.seglist);
    }
    
    BSPNode GenNodes(final Vector vector) {
        int n = 0;
        for (int i = 0; i != vector.size(); ++i) {
            if (!vector.elementAt(i).partition) {
                ++n;
            }
        }
        if (n == 0) {
            return new BSPLeaf(vector);
        }
        int n2 = 5000;
        final int n3 = 50;
        Seg seg = null;
        int n4 = vector.size() / n3;
        if (n4 == 0) {
            n4 = 1;
        }
        for (int j = 0; j < vector.size(); j += n4) {
            final Seg seg2 = vector.elementAt(j);
            if (!seg2.partition) {
                ++this.partiters;
                if ((this.partiters & 0x1F) == 0x0) {
                    final int percentdone = this.partiters * 100 / this.expected_partiters;
                    if (percentdone > this.maze.percentdone && percentdone < 100) {
                        this.maze.percentdone = percentdone;
                        this.maze.redraw();
                        this.maze.repaint();
                        try {
                            Thread.currentThread();
                            Thread.sleep(10L);
                        }
                        catch (Exception ex) {}
                    }
                }
                final int grade_partition = this.grade_partition(vector, seg2);
                if (grade_partition < n2) {
                    n2 = grade_partition;
                    seg = seg2;
                }
            }
        }
        seg.partition = true;
        final int x = seg.x;
        final int y = seg.y;
        final int dx = seg.dx;
        final int dy = seg.dy;
        final Vector vector2 = new Vector<Seg>();
        final Vector vector3 = new Vector<Seg>();
        for (int k = 0; k != vector.size(); ++k) {
            final Seg seg3 = vector.elementAt(k);
            final int n5 = seg3.x - x;
            final int n6 = seg3.y - y;
            final int n7 = seg3.x + seg3.dx;
            final int n8 = seg3.y + seg3.dy;
            final int n9 = n7 - x;
            final int n10 = n8 - y;
            final int n11 = dy;
            final int n12 = -dx;
            int n13 = n5 * n11 + n6 * n12;
            final int n14 = n9 * n11 + n10 * n12;
            if (getSign(n13) != getSign(n14)) {
                if (n13 == 0) {
                    n13 = n14;
                }
                else if (n14 != 0) {
                    int x2 = seg3.x;
                    int y2 = seg3.y;
                    if (dx == 0) {
                        x2 = x;
                    }
                    else {
                        y2 = y;
                    }
                    final Seg seg4 = new Seg(seg3.x, seg3.y, x2 - seg3.x, y2 - seg3.y, seg3.dist, this.colchange);
                    final Seg seg5 = new Seg(x2, y2, n7 - x2, n8 - y2, seg3.dist, this.colchange);
                    if (n13 > 0) {
                        vector3.addElement(seg4);
                        vector2.addElement(seg5);
                    }
                    else {
                        vector3.addElement(seg5);
                        vector2.addElement(seg4);
                    }
                    final Seg seg6 = seg4;
                    final Seg seg7 = seg5;
                    final boolean partition = seg3.partition;
                    seg7.partition = partition;
                    seg6.partition = partition;
                    continue;
                }
            }
            if (n13 > 0 || (n13 == 0 && seg3.GetDir() == seg.GetDir())) {
                vector3.addElement(seg3);
                if (n13 == 0) {
                    seg3.partition = true;
                }
            }
            else if (n13 < 0 || (n13 == 0 && seg3.GetDir() == -seg.GetDir())) {
                vector2.addElement(seg3);
                if (n13 == 0) {
                    seg3.partition = true;
                }
            }
            else {
                this.dbg("error xx 1 " + n13);
            }
        }
        if (vector2.size() == 0) {
            return new BSPLeaf(vector3);
        }
        if (vector3.size() == 0) {
            return new BSPLeaf(vector2);
        }
        return new BSPBranch(x, y, dx, dy, this.GenNodes(vector2), this.GenNodes(vector3));
    }
    
    void dbg(final String s) {
        System.out.println("MazeBuilder: " + s);
    }
    
    void Build(final Maze maze, final int width, final int height, final int rooms, final int expected_partiters) {
        this.random = new Random();
        this.width = width;
        this.height = height;
        this.maze = maze;
        this.rooms = rooms;
        this.cells = new int[width][height];
        this.origdirs = new int[width][height];
        this.dists = new int[width][height];
        this.expected_partiters = expected_partiters;
        (this.buildThread = new Thread(this)).start();
    }
    
    public void run() {
        int n = 250;
        this.maze.redraw();
        this.maze.repaint();
        this.colchange = this.randno(0, 255);
        this.Initialize();
        while (n > 0 && this.rooms > 0) {
            if (this.PlaceRoom()) {
                --this.rooms;
            }
            else {
                --n;
            }
        }
        this.Generate();
        this.GenSegs();
        this.partiters = 0;
        this.maze.NewMaze(this.GenNodes(), this.cells, this.dists, this.startx, this.starty);
    }
    
    public void Interrupt() {
        this.buildThread.stop();
    }
    
    static {
        MazeBuilder.dirsx = new int[] { 1, 0, -1, 0 };
        MazeBuilder.dirsy = new int[] { 0, 1, 0, -1 };
    }
}
