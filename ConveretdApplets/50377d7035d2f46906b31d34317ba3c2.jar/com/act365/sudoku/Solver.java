// 
// Decompiled by Procyon v0.5.30
// 

package com.act365.sudoku;

import java.text.DecimalFormat;
import java.util.Date;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Solver extends Thread
{
    Grid grid;
    IStrategy strategy;
    IStrategy composeSolver;
    int maxSolns;
    int maxUnwinds;
    int maxComplexity;
    int composeSolverThreshold;
    int index;
    boolean useNative;
    Composer composer;
    PrintWriter debug;
    transient int nUnwinds;
    transient int nSolns;
    transient int complexity;
    transient int firstDisputableMove;
    
    public Solver(final String threadName, final Composer composer, final int index, final Grid grid, final IStrategy strategy, final IStrategy composeSolver, final int composeSolverThreshold, final int maxSolns, final int maxUnwinds, final int maxComplexity, final PrintStream debug, final boolean useNative) {
        super(threadName);
        this.composer = composer;
        this.index = index;
        this.grid = grid;
        this.strategy = strategy;
        this.composeSolver = composeSolver;
        this.composeSolverThreshold = composeSolverThreshold;
        this.maxSolns = maxSolns;
        this.maxUnwinds = maxUnwinds;
        this.maxComplexity = maxComplexity;
        this.debug = ((debug instanceof PrintStream) ? new PrintWriter(debug) : null);
        this.useNative = useNative;
    }
    
    public Solver(final Grid grid, final IStrategy strategy, final IStrategy composeSolver, final int composeSolverThreshold, final int maxSolns, final PrintStream debug) {
        this.composer = null;
        this.index = 0;
        this.grid = grid;
        this.strategy = strategy;
        this.composeSolver = composeSolver;
        this.composeSolverThreshold = composeSolverThreshold;
        this.maxSolns = maxSolns;
        this.maxUnwinds = 0;
        this.maxComplexity = Integer.MAX_VALUE;
        this.debug = ((debug instanceof PrintStream) ? new PrintWriter(debug) : null);
        this.useNative = false;
    }
    
    public Solver(final Grid grid, final IStrategy strategy) {
        this(grid, strategy, null, 0, 1, null);
    }
    
    public void run() {
        if (this.useNative) {
            if (!(this.strategy instanceof MostCandidates)) {
                System.err.println("Warning: Most Candidates composer strategy will be used");
            }
            else if (!(this.composeSolver instanceof LeastCandidatesHybrid)) {
                System.err.println("Warning: Least Candidates Hybrid strategy will be used");
            }
            this.nSolns = this._solve((MostCandidates)this.strategy, (LeastCandidatesHybrid)this.composeSolver, this.composeSolverThreshold, this.maxSolns, true, this.maxUnwinds, this.maxComplexity);
        }
        else {
            try {
                this.nSolns = this.solve(this.strategy, this.composeSolver, this.composeSolverThreshold, this.maxSolns, true, this.maxUnwinds, this.maxComplexity);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.err.println(this.grid);
                System.err.println(this.strategy);
                this.nSolns = 0;
            }
        }
        if (this.composer instanceof Composer) {
            this.composer.solverFinished(this.index);
        }
    }
    
    public int getNumberOfSolutions() {
        return this.nSolns;
    }
    
    public int getNumberOfUnwinds() {
        return this.nUnwinds;
    }
    
    public int getComplexity() {
        return this.complexity;
    }
    
    int solve(final IStrategy strategy, final IStrategy composeSolver, final int composeSolverThreshold, final int maxSolns, final boolean countUnwinds, final int maxUnwinds, final int maxComplexity) throws Exception {
        int nSolns = 0;
        int nComposeSolns = 2;
        boolean stillIndisputable = true;
        if (countUnwinds) {
            final boolean b = false;
            this.complexity = (b ? 1 : 0);
            this.nUnwinds = (b ? 1 : 0);
        }
        else {
            this.firstDisputableMove = 0;
        }
        while (true) {
            Label_0790: {
                try {
                    strategy.setup(this.grid);
                    break Label_0790;
                }
                catch (Exception e) {
                    return 0;
                }
                if (strategy.findCandidates() > 0) {
                    strategy.selectCandidate();
                    strategy.setCandidate();
                    if (!strategy.updateState(strategy.getBestX(), strategy.getBestY(), strategy.getBestValue(), strategy.getBestReason(), strategy.getScore() > 1)) {
                        return nSolns;
                    }
                    if (stillIndisputable && !countUnwinds) {
                        if (strategy.getScore() == 1) {
                            ++this.firstDisputableMove;
                        }
                        else {
                            stillIndisputable = false;
                        }
                    }
                    final int count = this.grid.countFilledCells();
                    if (composeSolver instanceof IStrategy && count >= composeSolverThreshold) {
                        nComposeSolns = this.solve(composeSolver, null, 0, 2, false, 0, 0);
                        composeSolver.reset();
                        if (nComposeSolns == 0) {
                            nComposeSolns = 2;
                            final int lastWrittenMove = strategy.getLastWrittenMove();
                            this.complexity += strategy.getThreadLength() - lastWrittenMove;
                            if ((countUnwinds && (++this.nUnwinds == maxUnwinds || this.complexity >= maxComplexity)) || !strategy.unwind(lastWrittenMove, true)) {
                                return nSolns;
                            }
                            break Label_0790;
                        }
                    }
                    if (count == this.grid.cellsInRow * this.grid.cellsInRow || nComposeSolns == 1) {
                        if (nComposeSolns == 1) {
                            this.composer.addSolution(this.index);
                            nComposeSolns = 2;
                        }
                        if (this.debug instanceof PrintWriter) {
                            this.debug.println(String.valueOf(1 + nSolns) + ".");
                            this.debug.println(this.grid.toString());
                            for (int i = 0; i < strategy.getThreadLength(); ++i) {
                                this.debug.print(String.valueOf(1 + i) + ". " + strategy.getReason(i));
                            }
                            this.debug.println();
                            this.debug.flush();
                        }
                        if (++nSolns == maxSolns) {
                            return nSolns;
                        }
                        final int lastWrittenMove = strategy.getLastWrittenMove();
                        this.complexity += strategy.getThreadLength() - lastWrittenMove;
                        if ((countUnwinds && (++this.nUnwinds == maxUnwinds || this.complexity >= maxComplexity)) || !strategy.unwind(lastWrittenMove, true)) {
                            return nSolns;
                        }
                        break Label_0790;
                    }
                    else {
                        if (!(composeSolver instanceof IStrategy) || count < composeSolverThreshold) {
                            break Label_0790;
                        }
                        try {
                            for (int i = 0; i < this.firstDisputableMove; ++i) {
                                strategy.updateState(composeSolver.getThreadX(i), composeSolver.getThreadY(i), this.grid.data[composeSolver.getThreadX(i)][composeSolver.getThreadY(i)], null, false);
                            }
                            composeSolver.reset(this.firstDisputableMove);
                            break Label_0790;
                        }
                        catch (Exception e) {
                            composeSolver.reset();
                            final int lastWrittenMove = strategy.getLastWrittenMove();
                            this.complexity += strategy.getThreadLength() - lastWrittenMove;
                            if ((countUnwinds && (++this.nUnwinds == maxUnwinds || this.complexity >= maxComplexity)) || !strategy.unwind(lastWrittenMove, true)) {
                                return nSolns;
                            }
                            break Label_0790;
                        }
                    }
                }
                final int lastWrittenMove = strategy.getLastWrittenMove();
                this.complexity += strategy.getThreadLength() - lastWrittenMove;
                if ((countUnwinds && (++this.nUnwinds == maxUnwinds || this.complexity >= maxComplexity)) || !strategy.unwind(lastWrittenMove, true)) {
                    return nSolns;
                }
            }
            if (this.isInterrupted()) {
                return nSolns;
            }
            continue;
        }
    }
    
    native int _solve(final MostCandidates p0, final LeastCandidatesHybrid p1, final int p2, final int p3, final boolean p4, final int p5, final int p6);
    
    public static void main(final String[] args) {
        final String usage = "Usage: Solver [-m max solutions] [-s strategy] [-v] [-p profile]";
        boolean debug = false;
        boolean profile = false;
        int maxSolns = 0;
        final int maxUnwinds = 0;
        final int maxComplexity = 0;
        String strategyLabel = "Least Candidates Hybrid";
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("-m")) {
                try {
                    maxSolns = Integer.parseInt(args[++i]);
                }
                catch (NumberFormatException e2) {
                    System.err.println("Usage: Solver [-m max solutions] [-s strategy] [-v] [-p profile]");
                    System.exit(1);
                }
            }
            else if (args[i].equals("-v")) {
                debug = true;
            }
            else if (args[i].equals("-s")) {
                strategyLabel = args[++i];
            }
            else if (args[i].equals("-p")) {
                profile = true;
            }
            else {
                System.err.println("Usage: Solver [-m max solutions] [-s strategy] [-v] [-p profile]");
                System.exit(1);
            }
        }
        final IStrategy strategy;
        if ((strategy = Strategy.create(strategyLabel)) == null) {
            System.err.println("Unsupported strategy");
            System.exit(2);
        }
        final Grid grid = new Grid();
        final StringBuffer gridText = new StringBuffer();
        final BufferedReader standardInputReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String text;
            while ((text = standardInputReader.readLine()) != null && text.length() != 0) {
                gridText.append(text);
                gridText.append('\n');
            }
            grid.populate(gridText.toString());
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(3);
        }
        final Solver solver = new Solver(grid, strategy, null, 0, maxSolns, debug ? System.out : null);
        final long startTime = new Date().getTime();
        solver.start();
        try {
            solver.join();
        }
        catch (InterruptedException e3) {
            System.out.println("Solver interrupted");
        }
        final double solveTime = (new Date().getTime() - startTime) / 1000.0;
        System.out.print(String.valueOf(solver.getNumberOfSolutions()) + " solutions found in ");
        System.out.println(String.valueOf(new DecimalFormat("#0.000").format(solveTime)) + "s");
        if (profile) {
            System.out.println("Unwinds: " + solver.nUnwinds);
            System.out.println("Complexity: " + solver.complexity);
            if (strategy instanceof LeastCandidatesHybrid) {
                final LeastCandidatesHybrid lch = (LeastCandidatesHybrid)strategy;
                if (lch.state instanceof IState) {
                    System.out.println("Single Candidature: " + lch.singleCandidatureCalls + " calls");
                    System.out.println("Single Sector Candidates: " + lch.singleSectorCandidatesCalls + " calls " + lch.singleSectorCandidatesEliminations + " eliminations");
                    System.out.println("Disjoint Subsets: " + lch.disjointSubsetsCalls + " calls " + lch.disjointSubsetsEliminations + " eliminations");
                    System.out.println("X-Wings: " + lch.xWingsCalls + " calls " + lch.xWingsEliminations + " eliminations");
                    System.out.println("Swordfish: " + lch.swordfishCalls + " calls " + lch.swordfishEliminations + " eliminations");
                    System.out.println("Nishio: " + lch.nishioCalls + " calls " + lch.nishioEliminations + " eliminations");
                }
            }
        }
    }
}
