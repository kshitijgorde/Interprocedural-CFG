// 
// Decompiled by Procyon v0.5.30
// 

public class PathFinding
{
    public static final int EMPTY = 0;
    public static final int NUT = 1;
    public static final int ORG = 2;
    public static final int JMAN = 3;
    public static final int BARN = 5;
    public static final int BARNGATE = 6;
    public static final int LINE1 = 7;
    public static final int LINE2 = 8;
    public static final int BONUS = 9;
    private static final int INIFITY = 65535;
    private int[] nodes;
    private int[][] paths;
    private int numpath;
    private int numnode;
    private int[][] map;
    private int mapsizex;
    private int mapsizey;
    private int[] C;
    private int[] D;
    private int[] P;
    private int[] pathtrace;
    private int ptrace;
    private int[][] dirTable;
    private int[][] mapsave;
    
    public PathFinding() {
        this.nodes = null;
        this.paths = null;
    }
    
    public void initData(final String[] array) {
        this.mapsizey = array.length;
        int i = 0;
        this.mapsizex = 0;
        while (i < this.mapsizey) {
            this.mapsizex = Math.max(this.mapsizex, array[i].length());
            ++i;
        }
        this.map = new int[this.mapsizey][this.mapsizex];
        for (int j = 0; j < this.mapsizey; ++j) {
            for (int k = 0; k < this.mapsizex; ++k) {
                this.map[j][k] = 0;
            }
        }
        for (int l = 0; l < this.mapsizey; ++l) {
            for (int n = 0; n < array[l].length(); ++n) {
                switch (array[l].charAt(n)) {
                    case '.': {
                        this.map[l][n] = 7;
                        break;
                    }
                    case '*': {
                        this.map[l][n] = 8;
                        break;
                    }
                    case '+': {
                        this.map[l][n] = 1;
                        break;
                    }
                    case '$': {
                        this.map[l][n] = 2;
                        break;
                    }
                    case '#': {
                        this.map[l][n] = 5;
                        break;
                    }
                    case '@': {
                        this.map[l][n] = 3;
                        break;
                    }
                    case '%': {
                        this.map[l][n] = 9;
                        break;
                    }
                }
            }
        }
        int n2 = 0;
        for (int n3 = 0; n3 < this.mapsizey; ++n3) {
            for (int n4 = 0; n4 < this.mapsizex; ++n4) {
                if (this.isNode(n4, n3)) {
                    ++n2;
                }
            }
        }
        this.nodes = new int[n2 + 2];
        this.numnode = 0;
        for (int n5 = 0; n5 < this.mapsizey; ++n5) {
            for (int n6 = 0; n6 < this.mapsizex; ++n6) {
                if (this.isNode(n6, n5)) {
                    this.enterNode(n6, n5);
                }
            }
        }
        this.C = new int[this.numnode + 1];
        this.D = new int[this.numnode + 1];
        this.P = new int[this.numnode + 1];
        this.pathtrace = new int[this.numnode + 1];
        this.paths = new int[this.numnode + 2][this.numnode + 2];
        for (int n7 = 0; n7 < this.numnode; ++n7) {
            for (int n8 = 0; n8 < this.numnode; ++n8) {
                this.paths[n7][n8] = 65535;
            }
        }
        this.numpath = 0;
        for (int n9 = 0; n9 < this.numnode; ++n9) {
            this.enterPath(n9);
        }
        this.makeCopyMap();
    }
    
    public int getMapSizeX() {
        return this.mapsizex;
    }
    
    public int getMapSizeY() {
        return this.mapsizey;
    }
    
    public int getMap(final int n, final int n2) {
        if (n < 0 || n >= this.mapsizex || n2 < 0 || n2 >= this.mapsizey) {
            return 0;
        }
        return this.map[n2][n];
    }
    
    public boolean isMap(final int n, final int n2, final int n3) {
        return this.getMap(n, n2) == n3;
    }
    
    public boolean isMapEmpty(final int n, final int n2) {
        final int map = this.getMap(n, n2);
        return map != 7 && map != 8 && map != 9 && map != 1 && map != 2 && map != 5 && map != 3;
    }
    
    public void setMap(final int n, final int n2, final int n3) {
        if (n < 0 || n >= this.mapsizex || n2 < 0 || n2 >= this.mapsizey) {
            return;
        }
        this.map[n2][n] = n3;
    }
    
    private int adjuctX(int n) {
        if (n < 0) {
            n = this.mapsizex - 1;
        }
        if (n >= this.mapsizex) {
            n = 0;
        }
        return n;
    }
    
    private int adjuctY(int n) {
        if (n < 0) {
            n = this.mapsizey - 1;
        }
        if (n >= this.mapsizey) {
            n = 0;
        }
        return n;
    }
    
    private boolean isInRoad(int adjuctX, int adjuctY) {
        adjuctX = this.adjuctX(adjuctX);
        adjuctY = this.adjuctY(adjuctY);
        return this.map[adjuctY][adjuctX] != 0;
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
        if (this.isInRoad(n, n2)) {
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
    
    private void enterNode(final int n, final int n2) {
        int n3;
        int n4;
        for (n3 = n + (n2 << 8), n4 = 0; n4 < this.numnode && n3 != this.nodes[n4]; ++n4) {}
        this.nodes[n4] = n3;
        if (n4 == this.numnode) {
            ++this.numnode;
        }
    }
    
    private int getNodeCode(final int n, final int n2) {
        int n3;
        int n4;
        for (n3 = n + (n2 << 8), n4 = 0; n4 < this.numnode && n3 != this.nodes[n4]; ++n4) {}
        if (n4 >= this.numnode) {
            return -1;
        }
        return n4;
    }
    
    private void enterPath(final int n) {
        final int n2 = this.nodes[n] & 0xFF;
        final int n3 = this.nodes[n] >> 8 & 0xFF;
        final int nextNodeCode = this.findNextNodeCode(n2, n3, 4);
        if (nextNodeCode != -1) {
            this.setPathDistance(n, nextNodeCode & 0xFFFF, nextNodeCode >> 16);
        }
        final int nextNodeCode2 = this.findNextNodeCode(n2, n3, 3);
        if (nextNodeCode2 != -1) {
            this.setPathDistance(n, nextNodeCode2 & 0xFFFF, nextNodeCode2 >> 16);
        }
    }
    
    public int findNextNode(int n, int n2, final int n3) {
        int n4 = 0;
        int n5 = 0;
        switch (n3) {
            case 12: {
                n4 = -1;
                break;
            }
            case 4: {
                n4 = 1;
                break;
            }
            case 1: {
                n5 = -1;
                break;
            }
            case 3: {
                n5 = 1;
                break;
            }
        }
        int n6 = 1;
        while (this.isInRoad(n + n4, n2 + n5)) {
            n += n4;
            n2 += n5;
            if (n < 0) {
                n = this.mapsizex - 1;
                n6 += this.mapsizex / 2;
            }
            if (n >= this.mapsizex) {
                n = 0;
                n6 += this.mapsizex / 2;
            }
            if (n2 < 0) {
                n2 = this.mapsizey - 1;
                n6 += this.mapsizey / 2;
            }
            if (n2 >= this.mapsizey) {
                n2 = 0;
                n6 += this.mapsizey / 2;
            }
            if (this.isNode(n, n2)) {
                return n | n2 << 8 | n6 << 16;
            }
            ++n6;
        }
        return -1;
    }
    
    private int findPreviousNode(final int n, final int n2, final int n3) {
        return this.findNextNode(n, n2, Runner.getOppositDirections(n3));
    }
    
    private int findNextNodeCode(final int n, final int n2, final int n3) {
        int nextNode = this.findNextNode(n, n2, n3);
        if (nextNode != -1) {
            nextNode = (this.getNodeCode(nextNode & 0xFF, nextNode >> 8 & 0xFF) | (nextNode & 0xFF0000));
        }
        return nextNode;
    }
    
    private void skipPreviousPath(final int n, final int n2, final int n3) {
        final int n4 = this.findPreviousNode(n, n2, n3) & 0xFFFF;
        final int n5 = n4 & 0xFF;
        final int n6 = n4 >> 8;
        final int nodeCode = this.getNodeCode(n, n2);
        final int nodeCode2 = this.getNodeCode(n5, n6);
        if (nodeCode == -1 || nodeCode2 == -1) {
            return;
        }
        final int[] array = this.paths[nodeCode];
        final int n7 = nodeCode2;
        array[n7] |= 0xFF0000;
        final int[] array2 = this.paths[nodeCode2];
        final int n8 = nodeCode;
        array2[n8] |= 0xFF0000;
    }
    
    private void clearSkipPath() {
        for (int i = 0; i < this.numnode; ++i) {
            for (int j = 0; j < this.numnode; ++j) {
                final int[] array = this.paths[i];
                final int n = j;
                array[n] &= 0xFF00FFFF;
            }
        }
    }
    
    private int getPathDistance(final int n, final int n2) {
        return this.paths[n][n2];
    }
    
    private void setPathDistance(final int n, final int n2, final int n3) {
        this.paths[n][n2] = (this.paths[n2][n] = n3);
    }
    
    private void dijkstra(final int n) {
        for (int i = 0, n2 = 0; i < this.numnode - 1; ++i, ++n2) {
            if (n2 == n) {
                ++n2;
            }
            this.C[i] = n2;
            this.D[n2] = this.getPathDistance(n, n2);
            this.P[n2] = n;
        }
        int n3 = this.numnode - 1;
        for (int j = 0; j < this.numnode - 2; ++j) {
            int n4 = this.D[this.C[0]];
            int n5 = 0;
            for (int k = 1; k < n3; ++k) {
                if (this.D[this.C[k]] < n4) {
                    n4 = this.D[this.C[k]];
                    n5 = k;
                }
            }
            final int n6 = this.C[n5];
            this.C[n5] = this.C[n3 - 1];
            --n3;
            for (int l = 0; l < n3; ++l) {
                final int n7 = this.C[l];
                if (this.D[n7] > this.D[n6] + this.getPathDistance(n6, n7)) {
                    this.D[n7] = this.D[n6] + this.getPathDistance(n6, n7);
                    this.P[n7] = n6;
                }
            }
        }
    }
    
    private boolean findBestPath(final int n, final int n2) {
        if (n == n2) {
            return false;
        }
        this.dijkstra(n);
        this.ptrace = 0;
        for (int i = n2; i != n; i = this.P[i]) {
            this.pathtrace[this.ptrace] = this.nodes[i];
            ++this.ptrace;
        }
        this.pathtrace[this.ptrace] = this.nodes[n];
        ++this.ptrace;
        return this.ptrace > 2;
    }
    
    public int findBestDirection(final int n, final int n2) {
        if (!this.findBestPath(n, n2)) {
            return -1;
        }
        final int n3 = this.pathtrace[0] & 0xFF;
        final int n4 = this.pathtrace[0] >> 8 & 0xFF;
        final int n5 = this.pathtrace[1];
        for (int i = 0; i < 4; ++i) {
            if ((this.findNextNode(n3, n4, Runner.directionArray[i]) & 0xFFFF) == n5) {
                return Runner.directionArray[i];
            }
        }
        return -1;
    }
    
    public int findBestDirection(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (!this.isNode(n, n2) || !this.isNode(n3, n4) || (n == n3 && n2 == n4)) {
            return -1;
        }
        this.skipPreviousPath(n, n2, n5);
        final int bestDirection = this.findBestDirection(this.getNodeCode(n3, n4), this.getNodeCode(n, n2));
        this.clearSkipPath();
        return bestDirection;
    }
    
    public int countMap(final int n) {
        int n2 = 0;
        for (int i = 0; i < this.mapsizey; ++i) {
            for (int j = 0; j < this.mapsizex; ++j) {
                if (this.map[i][j] == n) {
                    ++n2;
                }
            }
        }
        return n2;
    }
    
    public int getFirstApperanceMap(final int n) {
        for (int i = 0; i < this.mapsizey; ++i) {
            for (int j = 0; j < this.mapsizex; ++j) {
                if (this.map[i][j] == n) {
                    return j | i << 8;
                }
            }
        }
        return -1;
    }
    
    public void makeCopyMap() {
        this.mapsave = new int[this.mapsizey][this.mapsizex];
        for (int i = 0; i < this.mapsizey; ++i) {
            for (int j = 0; j < this.mapsizex; ++j) {
                this.mapsave[i][j] = this.map[i][j];
            }
        }
    }
    
    public void restoreMap() {
        for (int i = 0; i < this.mapsizey; ++i) {
            for (int j = 0; j < this.mapsizex; ++j) {
                this.map[i][j] = this.mapsave[i][j];
            }
        }
    }
}
