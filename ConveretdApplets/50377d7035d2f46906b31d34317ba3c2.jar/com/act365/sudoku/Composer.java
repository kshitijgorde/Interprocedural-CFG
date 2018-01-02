// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.text.DecimalFormat;
import java.util.Date;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Vector;

public class Composer extends Thread
{
    Vector puzzles;
    GridContainer gridContainer;
    int maxSolns;
    int maxMasks;
    int maxUnwinds;
    int maxComplexity;
    int nSolvers;
    int composeSolverThreshold;
    int singleSectorCandidatesFilter;
    int disjointSubsetsFilter;
    int xWingsFilter;
    int swordfishFilter;
    int nishioFilter;
    boolean useNative;
    boolean logicalFilter;
    boolean shuffle;
    boolean xmlFormat;
    MaskFactory maskFactory;
    Solver[] solvers;
    IStrategy[] composeSolvers;
    boolean[][][] solverMasks;
    Grid[] solverGrids;
    PrintWriter output;
    static final String[] featuredGrades;
    static final int EASY = 0;
    static final int MEDIUM = 1;
    static final int HARD = 2;
    static final int X_WING = 3;
    static final int SWORDFISH = 4;
    static final int NISHIO = 5;
    static final int GUESS = 6;
    transient int cellsInRow;
    transient int maskSize;
    transient int nSolns;
    transient int nMasks;
    transient int nThreads;
    transient int maxPuzzleComplexity;
    transient int mostComplex;
    transient int tempSolutions;
    transient boolean allSolutionsFound;
    transient boolean[] isAlive;
    transient LeastCandidatesHybrid lch;
    transient long startTime;
    public static final int defaultThreads = 3;
    
    static {
        featuredGrades = new String[] { "Easy", "Medium", "Hard", "X-Wing", "Swordfish", "Nishio", "Guess" };
    }
    
    public Composer(final GridContainer gridContainer, final int boxesAcross, final int maxSolns, final int maxMasks, final int maxUnwinds, final int maxComplexity, final MaskFactory maskFactory, final int nSolvers, final int composeSolverThreshold, final PrintStream output, final boolean useNative, final boolean leastCandidatesHybridFilter, final int singleSectorCandidatesFilter, final int disjointSubsetsFilter, final int xWingsFilter, final int swordfishFilter, final int nishioFilter, final boolean shuffle, final boolean xmlFormat) throws Exception {
        this.gridContainer = gridContainer;
        this.maxSolns = maxSolns;
        this.maxMasks = maxMasks;
        this.maxUnwinds = maxUnwinds;
        this.maxComplexity = maxComplexity;
        this.maskFactory = maskFactory;
        this.nSolvers = nSolvers;
        this.composeSolverThreshold = composeSolverThreshold;
        this.output = ((output instanceof PrintStream) ? new PrintWriter(output) : null);
        this.useNative = useNative;
        this.singleSectorCandidatesFilter = singleSectorCandidatesFilter;
        this.disjointSubsetsFilter = disjointSubsetsFilter;
        this.xWingsFilter = xWingsFilter;
        this.swordfishFilter = swordfishFilter;
        this.nishioFilter = nishioFilter;
        this.shuffle = shuffle;
        this.xmlFormat = xmlFormat;
        this.maskSize = maskFactory.getFilledCells();
        this.cellsInRow = maskFactory.getCellsInRow();
        this.solvers = new Solver[nSolvers];
        this.composeSolvers = new LeastCandidatesHybrid[nSolvers];
        this.isAlive = new boolean[nSolvers];
        this.solverMasks = new boolean[nSolvers][this.cellsInRow][this.cellsInRow];
        this.solverGrids = new Grid[nSolvers];
        this.puzzles = new Vector();
        this.lch = new LeastCandidatesHybrid(false, true, true, true);
        this.logicalFilter = (singleSectorCandidatesFilter != 0 || disjointSubsetsFilter != 0 || xWingsFilter != 0 || swordfishFilter != 0 || nishioFilter != 0);
        for (int i = 0; i < nSolvers; ++i) {
            this.composeSolvers[i] = new LeastCandidatesHybrid(false, leastCandidatesHybridFilter, false, false);
            this.solverGrids[i] = new Grid(boxesAcross, this.cellsInRow / boxesAcross);
        }
        this.startTime = new Date().getTime();
        if (xmlFormat && this.output instanceof PrintWriter) {
            this.output.print(SuDokuUtils.libraryBookHeader(this.getClass().getName(), this.cellsInRow, boxesAcross, Composer.featuredGrades));
        }
    }
    
    public synchronized void addSolution(final int solverIndex) {
        boolean singleSectorCandidates = false;
        boolean disjointSubsets = false;
        boolean xWings = false;
        boolean swordfish = false;
        boolean nishio = false;
        int category = 6;
        if (this.nSolns >= this.maxSolns) {
            return;
        }
        final Grid solution = new Grid(this.solverGrids[solverIndex].boxesAcross, this.solverGrids[solverIndex].boxesDown);
        this.solverGrids[solverIndex].solve(this.lch, 1);
        for (int r = 0; r < this.cellsInRow; ++r) {
            for (int c = 0; c < this.cellsInRow; ++c) {
                if (this.solverMasks[solverIndex][r][c]) {
                    solution.data[r][c] = this.solverGrids[solverIndex].data[r][c];
                }
                else {
                    solution.data[r][c] = 0;
                }
            }
        }
        this.lch.reset();
        final Grid puzzle = (Grid)solution.clone();
        if (this.shuffle) {
            puzzle.shuffle();
        }
        else {
            puzzle.rectify(this.solverMasks[solverIndex]);
        }
        if (!this.puzzles.contains(puzzle)) {
            puzzle.solve(this.lch, 2);
            final int puzzleComplexity = puzzle.complexity;
            final int puzzleUnwinds = puzzle.nUnwinds;
            this.lch.reset();
            if (puzzleComplexity > this.maxComplexity) {
                this.mostComplex = this.nSolns;
                this.maxPuzzleComplexity = puzzleComplexity;
            }
            final boolean logical = puzzleUnwinds == 1;
            if (this.logicalFilter && !logical) {
                return;
            }
            puzzle.solve(this.lch, 1);
            if (logical) {
                singleSectorCandidates = (this.lch.singleSectorCandidatesEliminations > 0);
                if ((this.singleSectorCandidatesFilter == 1 && !singleSectorCandidates) || (this.singleSectorCandidatesFilter == -1 && singleSectorCandidates)) {
                    return;
                }
                disjointSubsets = (this.lch.disjointSubsetsEliminations > 0);
                if ((this.disjointSubsetsFilter == 1 && !disjointSubsets) || (this.disjointSubsetsFilter == -1 && disjointSubsets)) {
                    return;
                }
                xWings = (this.lch.xWingsEliminations > 0);
                if ((this.xWingsFilter == 1 && !xWings) || (this.xWingsFilter == -1 && xWings)) {
                    return;
                }
                swordfish = (this.lch.swordfishEliminations > 0);
                if ((this.swordfishFilter == 1 && !swordfish) || (this.swordfishFilter == -1 && swordfish)) {
                    return;
                }
                nishio = (this.lch.nishioEliminations > 0);
                if ((this.nishioFilter == 1 && !nishio) || (this.nishioFilter == -1 && nishio)) {
                    return;
                }
            }
            if (this.output instanceof PrintWriter) {
                if (!this.xmlFormat) {
                    final double t = (new Date().getTime() - this.startTime) / 1000.0;
                    this.output.println("Puzzle " + (1 + this.nSolns) + ":\n");
                    this.output.println("Puzzle Complexity = " + puzzleComplexity);
                    this.output.println("Puzzle Unwinds = " + puzzleUnwinds);
                    this.output.println("Cumulative Composer Complexity = " + this.solvers[solverIndex].complexity);
                    this.output.println("Cumulative Composer Unwinds = " + this.solvers[solverIndex].nUnwinds);
                    this.output.println("Time = " + new DecimalFormat("#0.000").format(t) + "s");
                }
                boolean multipleCategories = false;
                final StringBuffer sb = this.xmlFormat ? null : new StringBuffer();
                if (logical) {
                    category = 0;
                    if (singleSectorCandidates) {
                        category = 1;
                        if (!this.xmlFormat) {
                            if (multipleCategories) {
                                sb.append(":");
                            }
                            sb.append("Single Sector Candidates");
                            multipleCategories = true;
                        }
                    }
                    if (disjointSubsets) {
                        category = 2;
                        if (!this.xmlFormat) {
                            if (multipleCategories) {
                                sb.append(":");
                            }
                            sb.append("Disjoint Subsets");
                            multipleCategories = true;
                        }
                    }
                    if (xWings) {
                        category = 3;
                        if (!this.xmlFormat) {
                            if (multipleCategories) {
                                sb.append(":");
                            }
                            sb.append("X-Wings");
                            multipleCategories = true;
                        }
                    }
                    if (swordfish) {
                        category = 4;
                        if (!this.xmlFormat) {
                            if (multipleCategories) {
                                sb.append(":");
                            }
                            sb.append("Swordfish");
                            multipleCategories = true;
                        }
                    }
                    if (nishio) {
                        category = 5;
                        if (!this.xmlFormat) {
                            if (multipleCategories) {
                                sb.append(":");
                            }
                            sb.append("Nishio");
                            multipleCategories = true;
                        }
                    }
                    if (!this.xmlFormat && sb.length() > 0) {
                        this.output.println(sb.toString());
                    }
                }
            }
            this.lch.reset();
            this.puzzles.addElement(puzzle);
            if (this.output instanceof PrintWriter) {
                if (this.xmlFormat) {
                    this.output.println(puzzle.toXML(1 + this.nSolns, Composer.featuredGrades[category]));
                }
                else {
                    this.output.println(puzzle.toString());
                }
                this.output.flush();
            }
            if (++this.nSolns == this.maxSolns) {
                this.allSolutionsFound = true;
                this.notifyAll();
            }
        }
    }
    
    public synchronized void solverFinished(final int solverIndex) {
        this.isAlive[solverIndex] = false;
        --this.nThreads;
        this.notifyAll();
    }
    
    synchronized void startThread(final int solverIndex) {
        boolean[][] mask = null;
        try {
            mask = (boolean[][])this.maskFactory.nextElement();
        }
        catch (NoSuchElementException e) {
            return;
        }
        ++this.nMasks;
        for (int r = 0; r < this.cellsInRow; ++r) {
            for (int c = 0; c < this.cellsInRow; ++c) {
                this.solverMasks[solverIndex][r][c] = mask[r][c];
            }
        }
        this.solverGrids[solverIndex].reset();
        (this.solvers[solverIndex] = new Solver("Solver-" + (solverIndex + 1), this, solverIndex, this.solverGrids[solverIndex], new MostCandidates(this.solverMasks[solverIndex], true), this.composeSolvers[solverIndex], this.composeSolverThreshold, this.maxSolns, this.maxUnwinds, this.maxComplexity, null, this.useNative)).start();
        while (!this.solvers[solverIndex].isAlive()) {}
        this.isAlive[solverIndex] = true;
        ++this.nThreads;
    }
    
    public void run() {
        this.nSolns = 0;
        this.nMasks = 0;
        this.allSolutionsFound = false;
        this.nThreads = 0;
        int i = 0;
        while (i < this.nSolvers && (this.maxMasks == 0 || this.nMasks < this.maxMasks)) {
            this.isAlive[i] = false;
            this.startThread(i++);
        }
        synchronized (this) {
            while (!this.allSolutionsFound && this.nThreads > 0 && (this.maxMasks == 0 || this.nMasks <= this.maxMasks)) {
                try {
                    while (!this.allSolutionsFound && this.nThreads == this.nSolvers) {
                        if (this.maxMasks != 0 && this.nMasks > this.maxMasks) {
                            break;
                        }
                        this.wait();
                    }
                }
                catch (InterruptedException e) {
                    break;
                }
                for (i = 0; i < this.nSolvers; ++i) {
                    if (!this.isAlive[i]) {
                        this.startThread(i);
                    }
                }
            }
            for (i = 0; i < this.nSolvers; ++i) {
                if (this.isAlive[i]) {
                    this.solvers[i].interrupt();
                    while (this.isAlive[i]) {
                        try {
                            this.wait();
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
            if (this.gridContainer instanceof GridContainer) {
                if (this.puzzles.size() > 0) {
                    this.gridContainer.setGrid(this.puzzles.elementAt(0));
                }
            }
            else if (this.xmlFormat && this.output instanceof PrintWriter) {
                this.output.println(SuDokuUtils.libraryBookFooter());
                this.output.close();
            }
            else {
                System.out.println(String.valueOf(this.nSolns) + " solutions found");
                if (this.nSolns > 0) {
                    System.out.println("Most complex: (" + this.maxPuzzleComplexity + ")");
                    System.out.println(this.puzzles.elementAt(this.mostComplex).toString());
                }
            }
        }
    }
    
    public static void main(final String[] args) {
        final String usage = "Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells";
        final String strategyTypes = "Valid strategy types are:\nSSC [Single Sector Candidates]\nDS [Disjoint Subsets]\nXWings\nSwordfish\nNishio";
        int boxesAcross = 3;
        int boxesDown = 3;
        int maxSolns = 0;
        int maxMasks = 0;
        int maxUnwinds = 0;
        int maxComplexity = Integer.MAX_VALUE;
        int nSolvers = 3;
        int filledCells = 0;
        int composeSolverThreshold = 0;
        int singleSectorCandidatesFilter = 0;
        int disjointSubsetsFilter = 0;
        int xwingsFilter = 0;
        int swordfishFilter = 0;
        int nishioFilter = 0;
        boolean randomize = false;
        boolean trace = false;
        boolean standardInput = false;
        boolean useNative = false;
        boolean leastCandidatesHybridFilter = false;
        boolean shuffle = false;
        boolean xmlFormat = false;
        if (args.length == 0) {
            System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
            System.exit(1);
        }
        int i;
        for (i = 0; i < args.length - 1; ++i) {
            int sign = 0;
            if (args[i].equals("-a")) {
                try {
                    boxesAcross = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-d")) {
                try {
                    boxesDown = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-ms")) {
                try {
                    maxSolns = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-mm")) {
                try {
                    maxMasks = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-mu")) {
                try {
                    maxUnwinds = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-mc")) {
                try {
                    maxComplexity = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-s")) {
                try {
                    nSolvers = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-c")) {
                try {
                    composeSolverThreshold = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e3) {
                    System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-r")) {
                randomize = true;
            }
            else if (args[i].equals("-v")) {
                trace = true;
            }
            else if (args[i].equals("-f")) {
                leastCandidatesHybridFilter = true;
            }
            else if (args[i].equals("-n")) {
                try {
                    System.loadLibrary("SuDoku");
                    useNative = true;
                }
                catch (Exception e4) {
                    System.err.println("Native library could not be loaded");
                }
            }
            else if (args[i].equals("-shuffle")) {
                shuffle = true;
            }
            else if (args[i].equals("-xml")) {
                xmlFormat = true;
            }
            else if (args[i].charAt(0) == '+') {
                sign = 1;
            }
            else if (args[i].charAt(0) == '-') {
                sign = -1;
            }
            else {
                System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
                System.exit(1);
            }
            if (sign != 0) {
                final String strategy = args[i].substring(1);
                if (strategy.equalsIgnoreCase("ssc")) {
                    singleSectorCandidatesFilter = sign;
                }
                else if (strategy.equalsIgnoreCase("ds")) {
                    disjointSubsetsFilter = sign;
                }
                else if (strategy.equalsIgnoreCase("xwings")) {
                    xwingsFilter = sign;
                }
                else if (strategy.equalsIgnoreCase("swordfish")) {
                    swordfishFilter = sign;
                }
                else if (strategy.equalsIgnoreCase("nishio")) {
                    nishioFilter = sign;
                }
                else {
                    System.err.println("Valid strategy types are:\nSSC [Single Sector Candidates]\nDS [Disjoint Subsets]\nXWings\nSwordfish\nNishio");
                    System.exit(1);
                }
            }
        }
        if (i == args.length) {
            System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
            System.exit(1);
        }
        if (maxMasks > 0 && maxSolns > 0) {
            System.err.println("The -ms and -mm options are mutually exclusive");
            System.exit(1);
        }
        try {
            filledCells = Integer.parseInt(args[i]);
        }
        catch (NumberFormatException e3) {
            if (args[i].equals("-i")) {
                standardInput = true;
            }
            else {
                System.err.println("Usage: Composer [-a across] [-d down] [-ms max solns|-mm max masks] [-mu max unwinds] [-mc max complexity] [-s solvers] [-c threshold] [-r] [-v] [-n] [-shuffle] [-f] [-xml] -i|#cells");
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
            else {
                maskFactory = new MaskFactory(boxesAcross * boxesDown, filledCells, boxesAcross);
            }
        }
        catch (Exception e2) {
            System.err.println(e2.getMessage());
            System.exit(2);
        }
        try {
            new Composer(null, boxesAcross, maxSolns, maxMasks, maxUnwinds, maxComplexity, maskFactory, nSolvers, composeSolverThreshold, trace ? System.out : null, useNative, leastCandidatesHybridFilter, singleSectorCandidatesFilter, disjointSubsetsFilter, xwingsFilter, swordfishFilter, nishioFilter, shuffle, xmlFormat).start();
        }
        catch (Exception e2) {
            System.out.println(e2.getMessage());
            System.exit(3);
        }
    }
}
