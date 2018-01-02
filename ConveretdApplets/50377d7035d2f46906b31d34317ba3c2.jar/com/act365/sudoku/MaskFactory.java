// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.NoSuchElementException;
import java.util.Enumeration;

public class MaskFactory implements Enumeration
{
    int nBalls;
    int nSlots;
    int cellsInRow;
    int filledCells;
    int[] g0;
    int[] g;
    boolean fillCentreCell;
    boolean haveIterated;
    boolean[][] mask;
    boolean[][] previousMask;
    
    public MaskFactory(final int cellsInRow, final int filledCells) throws Exception {
        this.resize(cellsInRow, filledCells);
        this.initiate(false);
    }
    
    public MaskFactory(final int cellsInRow, final int filledCells, final boolean randomize) throws Exception {
        this(cellsInRow, filledCells);
        this.initiate(randomize);
    }
    
    public MaskFactory(final int cellsInRow, final int filledCells, final boolean[][] mask) throws Exception {
        this(cellsInRow, filledCells);
        this.initiate(mask);
    }
    
    public MaskFactory(final int cellsInRow, final int filledCells, final int[] gaps) throws Exception {
        this(cellsInRow, filledCells);
        this.initiate(gaps);
    }
    
    public MaskFactory(final String s) throws Exception {
        this.initiate(s);
    }
    
    public MaskFactory(final int cellsInRow, final int filledCells, final int boxesAcross) throws Exception {
        this(cellsInRow, filledCells);
        this.initiate(boxesAcross);
    }
    
    void resize(final int cellsInRow, final int filledCells) throws Exception {
        if (this.cellsInRow == cellsInRow && this.filledCells == filledCells) {
            return;
        }
        this.cellsInRow = cellsInRow;
        this.filledCells = filledCells;
        this.mask = new boolean[cellsInRow][cellsInRow];
        this.previousMask = new boolean[cellsInRow][cellsInRow];
        if (cellsInRow % 2 == 1) {
            if (filledCells % 2 == 1) {
                this.fillCentreCell = true;
                this.nBalls = (filledCells - 1) / 2;
            }
            else {
                this.fillCentreCell = false;
                this.nBalls = filledCells / 2;
            }
            this.nSlots = (cellsInRow * cellsInRow - 1) / 2;
        }
        else {
            if (filledCells % 2 == 1) {
                throw new Exception("Number of filled cells should be even");
            }
            this.fillCentreCell = false;
            this.nBalls = filledCells / 2;
            this.nSlots = cellsInRow * cellsInRow / 2;
        }
        this.g = new int[this.nBalls + 1];
        this.g0 = new int[this.nBalls + 1];
    }
    
    void iterate() {
        final int[] reflection = new int[this.nBalls + 1];
        while (true) {
            this.iterateGaps();
            this.generateMask();
            for (int i = 0; i < reflection.length; reflection[i++] = 0) {}
            this.reflectTopBottom(reflection);
            if (!this.precedes(reflection)) {
                continue;
            }
            for (int i = 0; i < reflection.length; reflection[i++] = 0) {}
            this.reflectLeftRight(reflection);
            if (!this.precedes(reflection)) {
                continue;
            }
            for (int i = 0; i < reflection.length; reflection[i++] = 0) {}
            this.reflectTopLeftBottomRight(reflection);
            if (!this.precedes(reflection)) {
                continue;
            }
            for (int i = 0; i < reflection.length; reflection[i++] = 0) {}
            this.reflectTopRightBottomLeft(reflection);
            if (!this.precedes(reflection)) {
                continue;
            }
            break;
        }
    }
    
    void iterateGaps() {
        int s = 0;
        while (++s <= this.nBalls && this.g[s] == 0) {}
        if (s <= this.nBalls) {
            final int[] g = this.g;
            final int n = s;
            --g[n];
            this.g[s - 1] = 0;
            int i;
            int[] g2;
            int n2;
            for (i = this.nBalls + 1; i > s; g2[n2] -= this.g[--i]) {
                g2 = this.g;
                n2 = s - 1;
            }
            final int[] g3 = this.g;
            final int n3 = --i;
            g3[n3] += this.nSlots - this.nBalls;
            while (i > 0) {
                this.g[--i] = 0;
            }
        }
        else {
            this.g[this.nBalls] = this.nSlots - this.nBalls;
            for (int i = this.nBalls; i > 0; this.g[--i] = 0) {}
        }
    }
    
    void generateMask() {
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                this.mask[i][j] = false;
            }
        }
        int i;
        int k;
        for (int j = i = (k = 0); k < this.nBalls; j += this.g[k++], i += j / this.cellsInRow, j %= this.cellsInRow, this.mask[i][j] = (this.mask[this.cellsInRow - 1 - i][this.cellsInRow - 1 - j] = true), ++j) {}
        if (this.fillCentreCell) {
            this.mask[(this.cellsInRow - 1) / 2][(this.cellsInRow - 1) / 2] = true;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.cellsInRow; ++i) {
            for (int j = 0; j < this.cellsInRow; ++j) {
                if (this.mask[i][j]) {
                    sb.append('*');
                }
                else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public boolean hasMoreElements() {
        if (!this.haveIterated) {
            return true;
        }
        for (int i = 0; i < this.nBalls + 1; ++i) {
            if (this.g[i] != this.g0[i]) {
                return true;
            }
        }
        return false;
    }
    
    public Object nextElement() {
        if (this.hasMoreElements()) {
            for (int r = 0; r < this.cellsInRow; ++r) {
                for (int c = 0; c < this.cellsInRow; ++c) {
                    this.previousMask[r][c] = this.mask[r][c];
                }
            }
            this.iterate();
            this.haveIterated = true;
            return this.previousMask;
        }
        throw new NoSuchElementException();
    }
    
    boolean precedes(final int[] h) {
        int i = this.g.length;
        while (--i >= 0) {
            if (h[i] > this.g[i]) {
                return false;
            }
            if (h[i] < this.g[i]) {
                return true;
            }
        }
        return true;
    }
    
    void reflectTopBottom(final int[] h) {
        int i = 0;
        int j = 0;
        int k = 0;
        int b = 0;
        while (b <= this.nBalls) {
            if (this.mask[i][this.cellsInRow - 1 - j]) {
                ++b;
            }
            else {
                final int n = b;
                ++h[n];
            }
            if (++k == this.nSlots) {
                return;
            }
            if (++j != this.cellsInRow) {
                continue;
            }
            ++i;
            j = 0;
        }
    }
    
    void reflectLeftRight(final int[] h) {
        int i = 0;
        int j = 0;
        int k = 0;
        int b = 0;
        while (b <= this.nBalls) {
            if (this.mask[this.cellsInRow - 1 - i][j]) {
                ++b;
            }
            else {
                final int n = b;
                ++h[n];
            }
            if (++k == this.nSlots) {
                return;
            }
            if (++j != this.cellsInRow) {
                continue;
            }
            ++i;
            j = 0;
        }
    }
    
    void reflectTopLeftBottomRight(final int[] h) {
        int i = 0;
        int j = 0;
        int k = 0;
        int b = 0;
        while (b <= this.nBalls) {
            if (this.mask[j][i]) {
                ++b;
            }
            else {
                final int n = b;
                ++h[n];
            }
            if (++k == this.nSlots) {
                return;
            }
            if (++j != this.cellsInRow) {
                continue;
            }
            ++i;
            j = 0;
        }
    }
    
    void reflectTopRightBottomLeft(final int[] h) {
        int i = 0;
        int j = 0;
        int k = 0;
        int b = 0;
        while (b <= this.nBalls) {
            if (this.mask[this.cellsInRow - 1 - j][this.cellsInRow - 1 - i]) {
                ++b;
            }
            else {
                final int n = b;
                ++h[n];
            }
            if (++k == this.nSlots) {
                return;
            }
            if (++j != this.cellsInRow) {
                continue;
            }
            ++i;
            j = 0;
        }
    }
    
    void rectify() {
        final int[] h = new int[this.nBalls + 1];
        this.generateMask();
        this.reflectTopBottom(h);
        if (!this.precedes(h)) {
            for (int i = 0; i < this.nBalls + 1; ++i) {
                this.g[i] = h[i];
            }
        }
        for (int i = 0; i < this.nBalls + 1; h[i++] = 0) {}
        this.generateMask();
        this.reflectLeftRight(h);
        if (!this.precedes(h)) {
            for (int i = 0; i < this.nBalls + 1; ++i) {
                this.g[i] = h[i];
            }
        }
        for (int i = 0; i < this.nBalls + 1; h[i++] = 0) {}
        this.generateMask();
        this.reflectTopLeftBottomRight(h);
        if (!this.precedes(h)) {
            for (int i = 0; i < this.nBalls + 1; ++i) {
                this.g[i] = h[i];
            }
        }
        for (int i = 0; i < this.nBalls + 1; h[i++] = 0) {}
        this.generateMask();
        this.reflectTopRightBottomLeft(h);
        if (!this.precedes(h)) {
            for (int i = 0; i < this.nBalls + 1; ++i) {
                this.g[i] = h[i];
            }
        }
        this.generateMask();
    }
    
    void initiate(final int[] h) {
        for (int i = 0; i < this.nBalls + 1; ++i) {
            this.g[i] = h[i];
        }
        this.rectify();
        for (int i = 0; i < this.nBalls + 1; ++i) {
            this.g0[i] = this.g[i];
        }
        this.generateMask();
        this.haveIterated = false;
    }
    
    void initiate(final boolean[][] mask) {
        int j = 0;
        int k = 0;
        int b = 0;
        for (int i = 0; i < this.nBalls + 1; this.g[i++] = 0) {}
        int i = 0;
        while (b <= this.nBalls) {
            if (mask[i][j]) {
                ++b;
            }
            else {
                final int[] g = this.g;
                final int n = b;
                ++g[n];
            }
            if (++k == this.nSlots) {
                break;
            }
            if (++j != this.cellsInRow) {
                continue;
            }
            ++i;
            j = 0;
        }
        this.rectify();
        for (i = 0; i < this.nBalls + 1; ++i) {
            this.g0[i] = this.g[i];
        }
        this.generateMask();
        this.haveIterated = false;
    }
    
    void initiate(final String s) throws Exception {
        final int cellsInRow = s.indexOf(10);
        int filledCells = 0;
        final boolean[][] mask = new boolean[cellsInRow][cellsInRow];
        int i;
        for (int p = i = 0; i < cellsInRow; ++i) {
            int j = 0;
            while (j < cellsInRow) {
                final char c = s.charAt(p++);
                if (c == '*') {
                    mask[i][j] = true;
                    ++filledCells;
                }
                else {
                    if (c != '.') {
                        continue;
                    }
                    mask[i][j] = false;
                }
                ++j;
            }
        }
        this.resize(cellsInRow, filledCells);
        this.initiate(mask);
    }
    
    void initiate(final boolean randomize) {
        Random random = null;
        if (randomize) {
            random = new Random();
        }
        for (int i = 0; i < 1 + this.nBalls; this.g[i++] = 0) {}
        for (int i = 0; i < this.nSlots - this.nBalls; ++i) {
            if (randomize) {
                final int[] g = this.g;
                final int abs = Math.abs(random.nextInt() % (this.nBalls + 1));
                ++g[abs];
            }
            else {
                final int[] g2 = this.g;
                final int n = i % (this.nBalls + 1);
                ++g2[n];
            }
        }
        this.rectify();
        for (int i = 0; i < this.nBalls + 1; ++i) {
            this.g0[i] = this.g[i];
        }
        this.generateMask();
        this.haveIterated = false;
    }
    
    void initiate(final int boxesAcross) throws Exception {
        final Random generator = new Random();
        final MaskState state = new MaskState();
        state.setup(boxesAcross, this.cellsInRow / boxesAcross);
        final int[] rCandidates = new int[this.cellsInRow * this.cellsInRow];
        final int[] cCandidates = new int[this.cellsInRow * this.cellsInRow];
        if (this.fillCentreCell) {
            state.addCell(this.cellsInRow / 2, this.cellsInRow / 2);
        }
        for (int i = 0; i < this.nBalls; ++i) {
            int minEliminated = Integer.MAX_VALUE;
            int r;
            for (r = 0; r < this.cellsInRow / 2; ++r) {
                for (int c = 0; c < this.cellsInRow; ++c) {
                    if (state.nInvulnerable[r][c] < minEliminated) {
                        minEliminated = state.nInvulnerable[r][c];
                    }
                }
            }
            if (this.cellsInRow % 2 == 1) {
                for (int c = 0; c < this.cellsInRow / 2; ++c) {
                    if (state.nInvulnerable[r][c] < minEliminated) {
                        minEliminated = state.nInvulnerable[r][c];
                    }
                }
            }
            int nCandidates = 0;
            for (r = 0; r < this.cellsInRow / 2; ++r) {
                for (int c = 0; c < this.cellsInRow; ++c) {
                    if (state.nInvulnerable[r][c] == minEliminated) {
                        rCandidates[nCandidates] = r;
                        cCandidates[nCandidates] = c;
                        ++nCandidates;
                    }
                }
            }
            if (this.cellsInRow % 2 == 1) {
                for (int c = 0; c < this.cellsInRow / 2; ++c) {
                    if (state.nInvulnerable[r][c] == minEliminated) {
                        rCandidates[nCandidates] = r;
                        cCandidates[nCandidates] = c;
                        ++nCandidates;
                    }
                }
            }
            final int pick = (nCandidates > 1) ? Math.abs(generator.nextInt() % nCandidates) : 0;
            r = rCandidates[pick];
            int c = cCandidates[pick];
            state.addCell(r, c);
            state.addCell(this.cellsInRow - 1 - r, this.cellsInRow - 1 - c);
        }
        this.initiate(state.mask);
    }
    
    public int getCellsInRow() {
        return this.cellsInRow;
    }
    
    public int getFilledCells() {
        return this.filledCells;
    }
    
    public static boolean isSymmetricLeftRight(final boolean[][] mask) {
        int i;
        for (i = 0; i < mask.length / 2; ++i) {
            for (int j = 0; j < mask.length; ++j) {
                if (mask[i][j] != mask[mask.length - 1 - i][j]) {
                    return false;
                }
            }
        }
        if (mask.length % 2 == 1) {
            for (int j = 0; j < mask.length / 2; ++j) {
                if (mask[i][j] != mask[mask.length - 1 - i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isSymmetricTopBottom(final boolean[][] mask) {
        int j;
        for (j = 0; j < mask.length / 2; ++j) {
            for (int i = 0; i < mask.length; ++i) {
                if (mask[i][j] != mask[i][mask.length - 1 - j]) {
                    return false;
                }
            }
        }
        if (mask.length % 2 == 1) {
            for (int i = 0; i < mask.length / 2; ++i) {
                if (mask[i][j] != mask[i][mask.length - 1 - j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isSymmetricTopLeftBottomRight(final boolean[][] mask) {
        for (int i = 0; i < mask.length; ++i) {
            for (int j = i + 1; j < mask.length; ++j) {
                if (mask[i][j] != mask[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isSymmetricTopRightBottomLeft(final boolean[][] mask) {
        for (int i = 0; i < mask.length; ++i) {
            for (int j = 0; j < mask.length - 1 - i; ++j) {
                if (mask[i][j] != mask[mask.length - 1 - j][mask.length - 1 - i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isSymmetricOrder2(final boolean[][] mask) {
        int i;
        for (i = 0; i < mask.length / 2; ++i) {
            for (int j = 0; j < mask.length; ++j) {
                if (mask[i][j] != mask[mask.length - 1 - i][mask.length - 1 - j]) {
                    return false;
                }
            }
        }
        if (mask.length % 2 == 1) {
            for (int j = 0; j < mask.length / 2; ++j) {
                if (mask[i][j] != mask[mask.length - 1 - i][mask.length - 1 - j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean isSymmetricOrder4(final boolean[][] mask) {
        final int jMax = mask.length / 2 + ((mask.length % 2 == 1) ? 1 : 0);
        for (int i = 0; i < mask.length / 2; ++i) {
            for (int j = 0; j < jMax; ++j) {
                if (mask[i][j] != mask[j][mask.length - 1 - i] || mask[j][mask.length - 1 - i] != mask[mask.length - 1 - i][mask.length - 1 - j] || mask[mask.length - 1 - i][mask.length - 1 - j] != mask[mask.length - 1 - j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(final String[] args) {
        int size = 9;
        int filledCells = 0;
        int boxesAcross = 0;
        boolean debug = false;
        boolean random = false;
        boolean standardInput = false;
        final String usage = "Usage: MaskFactory [-c cellsInRow] [-r|-a boxes across] [-v] -i|filledCells";
        if (args.length == 0) {
            System.err.println("Usage: MaskFactory [-c cellsInRow] [-r|-a boxes across] [-v] -i|filledCells");
            System.exit(1);
        }
        int i;
        for (i = 0; i < args.length - 1; ++i) {
            if (args[i].equals("-v")) {
                debug = true;
            }
            else if (args[i].equals("-r")) {
                random = true;
            }
            else if (args[i].equals("-c")) {
                try {
                    size = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: MaskFactory [-c cellsInRow] [-r|-a boxes across] [-v] -i|filledCells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-a")) {
                try {
                    boxesAcross = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: MaskFactory [-c cellsInRow] [-r|-a boxes across] [-v] -i|filledCells");
                    System.exit(1);
                }
            }
            else {
                System.err.println("Usage: MaskFactory [-c cellsInRow] [-r|-a boxes across] [-v] -i|filledCells");
                System.exit(1);
            }
        }
        if (boxesAcross > 0 && size % boxesAcross != 0) {
            System.err.println("Numbers of boxes across and cells per row are incompatible");
            System.exit(2);
        }
        if (random && boxesAcross != 0) {
            System.err.println("The -a and -r options are mutually exclusive");
            System.exit(2);
        }
        try {
            filledCells = Integer.parseInt(args[i]);
        }
        catch (NumberFormatException e3) {
            if (args[i].equals("-i")) {
                standardInput = true;
            }
            else {
                System.err.println("Usage: MaskFactory [-c cellsInRow] [-r|-a boxes across] [-v] -i|filledCells");
                System.exit(1);
            }
        }
        MaskFactory maskFactory = null;
        try {
            if (standardInput) {
                final StringBuffer maskText = new StringBuffer();
                final BufferedReader standardInputReader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String text;
                    while ((text = standardInputReader.readLine()) != null) {
                        if (text.length() == 0) {
                            break;
                        }
                        maskText.append(text);
                        maskText.append('\n');
                    }
                }
                catch (IOException e) {
                    System.err.println(e.getMessage());
                    System.exit(3);
                }
                maskFactory = new MaskFactory(maskText.toString());
            }
            else if (boxesAcross > 0) {
                maskFactory = new MaskFactory(size, filledCells, boxesAcross);
            }
            else if (random) {
                maskFactory = new MaskFactory(size, filledCells, true);
            }
            else {
                maskFactory = new MaskFactory(size, filledCells);
            }
        }
        catch (Exception e2) {
            System.err.println(e2.getMessage());
            System.exit(2);
        }
        i = 0;
        while (maskFactory.hasMoreElements()) {
            ++i;
            if (debug) {
                System.out.println(String.valueOf(i) + ".");
                System.out.println(maskFactory.toString());
            }
            maskFactory.nextElement();
        }
        System.out.println(String.valueOf(i) + " distinct masks found");
    }
}
