// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.data;

import java.util.Iterator;
import java.util.HashMap;
import kiang.util.PriorityList;
import java.util.Map;
import kiang.awt.geom.CurveUtils;
import java.awt.geom.CubicCurve2D;
import java.io.IOException;

public class StrokesMatcher
{
    private CharacterDescriptor inputCharacter;
    private CharacterDescriptor compareTo;
    private double[][] scoreMatrix;
    private boolean searchTraditional;
    private boolean searchSimplified;
    private double looseness;
    private CharacterMatchCollector matches;
    private boolean running;
    private StrokesDataSource strokesDataSource;
    static final double CORRECT_NUM_STROKES_BONUS = 0.1;
    static final int CORRECT_NUM_STROKES_CAP = 10;
    private static final double SKIP_PENALTY_MULTIPLIER = 1.75;
    private static final double[] DIRECTION_SCORE_TABLE;
    private static final double[] LENGTH_SCORE_TABLE;
    
    static {
        DIRECTION_SCORE_TABLE = initDirectionScoreTable();
        LENGTH_SCORE_TABLE = initLengthScoreTable();
    }
    
    public StrokesMatcher(final CharacterDescriptor character, final boolean searchTraditional, final boolean searchSimplified, final double looseness, final int numMatches, final StrokesDataSource strokesDataSource) {
        this.inputCharacter = character;
        this.compareTo = new CharacterDescriptor();
        this.searchTraditional = searchTraditional;
        this.searchSimplified = searchSimplified;
        this.looseness = looseness;
        this.running = true;
        this.strokesDataSource = strokesDataSource;
        this.matches = new CharacterMatchCollector(numMatches, null);
        this.initScoreMatrix();
    }
    
    public Character[] doMatching() {
        final int strokeCount = this.inputCharacter.getStrokeCount();
        final int subStrokeCount = this.inputCharacter.getSubStrokeCount();
        final int strokeRange = this.getStrokesRange(strokeCount, this.looseness);
        final int minimumStrokes = Math.max(strokeCount - strokeRange, 1);
        final int maximumStrokes = Math.min(strokeCount + strokeRange, 48);
        final int subStrokesRange = this.getSubStrokesRange(subStrokeCount, this.looseness);
        final StrokesDataSource.StrokesDataScanner strokesScanner = this.strokesDataSource.getStrokesScanner(this.searchTraditional, this.searchSimplified, minimumStrokes, maximumStrokes);
        try {
            while (strokesScanner.loadNextCharacterStrokeData(this.compareTo)) {
                final CharacterMatch match = this.compareToNext(strokeCount, subStrokeCount, subStrokesRange);
                this.matches.addMatch(match);
            }
        }
        catch (IOException ioe) {
            System.err.println("Error running strokes comparison!");
            ioe.printStackTrace();
        }
        final Character[] matches = this.matches.getMatches();
        if (this.isRunning()) {
            return matches;
        }
        return null;
    }
    
    private int getStrokesRange(final int strokeCount, final double looseness) {
        if (looseness == 0.0) {
            return 0;
        }
        if (looseness == 1.0) {
            return 48;
        }
        final double ctrl1X = 0.35;
        final double ctrl1Y = strokeCount * 0.4;
        final double ctrl2X = 0.6;
        final double ctrl2Y = strokeCount;
        final double[] solutions = { 0.0 };
        final CubicCurve2D curve = new CubicCurve2D.Double(0.0, 0.0, ctrl1X, ctrl1Y, ctrl2X, ctrl2Y, 1.0, 48.0);
        CurveUtils.solveCubicCurveForX(curve, looseness, solutions);
        final double t = solutions[0];
        return (int)Math.round(CurveUtils.getPointOnCubicCurve(curve, t).getY());
    }
    
    private int getSubStrokesRange(final int subStrokeCount, final double looseness) {
        if (looseness == 1.0) {
            return 64;
        }
        final double y0 = subStrokeCount * 0.25;
        final double ctrl1X = 0.4;
        final double ctrl1Y = 1.5 * y0;
        final double ctrl2X = 0.75;
        final double ctrl2Y = 1.5 * ctrl1Y;
        final double[] solutions = { 0.0 };
        final CubicCurve2D curve = new CubicCurve2D.Double(0.0, y0, ctrl1X, ctrl1Y, ctrl2X, ctrl2Y, 1.0, 64.0);
        CurveUtils.solveCubicCurveForX(curve, looseness, solutions);
        final double t = solutions[0];
        return (int)Math.round(CurveUtils.getPointOnCubicCurve(curve, t).getY());
    }
    
    private void initScoreMatrix() {
        final double AVG_SUBSTROKE_LENGTH = 0.33;
        final int scoreMatrixDimension = 65;
        this.scoreMatrix = new double[scoreMatrixDimension][scoreMatrixDimension];
        for (int i = 0; i < scoreMatrixDimension; ++i) {
            final double penalty = -0.5775 * i;
            this.scoreMatrix[i][0] = penalty;
            this.scoreMatrix[0][i] = penalty;
        }
    }
    
    private CharacterMatch compareToNext(final int inputStrokeCount, final int inputSubStrokeCount, final int subStrokesRange) {
        final Character character = this.compareTo.getCharacter();
        final int type = this.compareTo.getCharacterType();
        final int compareStrokeCount = this.compareTo.getStrokeCount();
        final int compareSubStrokeCount = this.compareTo.getSubStrokeCount();
        double score = this.computeMatchScore(inputSubStrokeCount, compareSubStrokeCount, subStrokesRange);
        if (inputStrokeCount == compareStrokeCount && inputStrokeCount < 10) {
            final double bonus = 0.1 * (Math.max(10 - inputStrokeCount, 0) / 10.0);
            score += bonus * score;
        }
        return new CharacterMatch(character, score);
    }
    
    private double computeMatchScore(final int inputSubStrokeCount, final int compareSubStrokeCount, final int subStrokesRange) {
        final double[] inputDirections = this.inputCharacter.getDirections();
        final double[] inputLengths = this.inputCharacter.getLengths();
        final double[] compareDirections = this.compareTo.getDirections();
        final double[] compareLengths = this.compareTo.getLengths();
        for (int x = 0; x < inputSubStrokeCount; ++x) {
            final double inputDirection = inputDirections[x];
            final double inputLength = inputLengths[x];
            for (int y = 0; y < compareSubStrokeCount; ++y) {
                double newScore = Double.NEGATIVE_INFINITY;
                if (Math.abs(x - y) <= subStrokesRange) {
                    final double compareDirection = compareDirections[y];
                    final double compareLength = compareLengths[y];
                    final double skip1Score = this.scoreMatrix[x][y + 1] - inputLength * 1.75;
                    final double skip2Score = this.scoreMatrix[x + 1][y] - compareLength * 1.75;
                    final double skipScore = Math.max(skip1Score, skip2Score);
                    final double matchScore = this.computeSubStrokeScore(inputDirection, inputLength, compareDirection, compareLength);
                    final double previousScore = this.scoreMatrix[x][y];
                    newScore = Math.max(previousScore + matchScore, skipScore);
                }
                this.scoreMatrix[x + 1][y + 1] = newScore;
            }
        }
        return this.scoreMatrix[inputSubStrokeCount][compareSubStrokeCount];
    }
    
    private double computeSubStrokeScore(final double direction1, final double length1, final double direction2, final double length2) {
        final double directionScore = this.getDirectionScore(direction1, direction2, length1);
        final double lengthScore = this.getLengthScore(length1, length2);
        final double score = lengthScore * directionScore;
        return score;
    }
    
    private static double[] initDirectionScoreTable() {
        final CubicCurve2D curve = new CubicCurve2D.Double(0.0, 1.0, 0.5, 1.0, 0.25, -2.0, 1.0, 1.0);
        return initCubicCurveScoreTable(curve, 100);
    }
    
    private static double[] initLengthScoreTable() {
        final CubicCurve2D curve = new CubicCurve2D.Double(0.0, 0.0, 0.25, 1.0, 0.75, 1.0, 1.0, 1.0);
        return initCubicCurveScoreTable(curve, 100);
    }
    
    private static double[] initCubicCurveScoreTable(final CubicCurve2D curve, final int numSamples) {
        final double x1 = curve.getX1();
        final double x2 = curve.getX2();
        final double range = x2 - x1;
        double x3 = x1;
        final double xInc = range / numSamples;
        final double[] scoreTable = new double[numSamples];
        final double[] solutions = { 0.0 };
        for (int i = 0; i < numSamples; ++i) {
            CurveUtils.solveCubicCurveForX(curve, Math.min(x3, x2), solutions);
            final double t = solutions[0];
            scoreTable[i] = CurveUtils.getPointOnCubicCurve(curve, t).getY();
            x3 += xInc;
        }
        return scoreTable;
    }
    
    private double getDirectionScore(final double direction1, final double direction2, final double inputLength) {
        double theta = Math.abs(direction1 - direction2);
        if (theta > 3.141592653589793) {
            theta = 6.283185307179586 - theta;
        }
        final int index = (int)(theta / 3.141592653589793 * (StrokesMatcher.DIRECTION_SCORE_TABLE.length - 1));
        double directionScore = StrokesMatcher.DIRECTION_SCORE_TABLE[index];
        final double shortLengthBonusMax = Math.min(1.0, 1.0 - directionScore);
        final double shortLengthBonus = shortLengthBonusMax * (-4.0 * inputLength + 1.0);
        if (shortLengthBonus > 0.0) {
            directionScore += shortLengthBonus;
        }
        return directionScore;
    }
    
    private double getLengthScore(final double length1, final double length2) {
        final double lengthRatio = (length1 < length2) ? (length1 / length2) : (length2 / length1);
        final int index = (int)(lengthRatio * (StrokesMatcher.LENGTH_SCORE_TABLE.length - 1));
        final double lengthScore = StrokesMatcher.LENGTH_SCORE_TABLE[index];
        return lengthScore;
    }
    
    private synchronized boolean isRunning() {
        return this.running;
    }
    
    public synchronized void stop() {
        this.running = false;
    }
    
    private static class CharacterMatch implements Comparable
    {
        private Character character;
        private double score;
        
        public CharacterMatch(final Character character, final double score) {
            this.character = character;
            this.score = score;
        }
        
        public int compareTo(final Object o) {
            final CharacterMatch compareMatch = (CharacterMatch)o;
            final double thisScore = this.score;
            final double compareScore = compareMatch.score;
            if (thisScore < compareScore) {
                return 1;
            }
            if (thisScore > compareScore) {
                return -1;
            }
            return 0;
        }
    }
    
    private static class CharacterMatchCollector
    {
        private Map matchMap;
        private PriorityList matches;
        private int maxSize;
        
        private CharacterMatchCollector(final int maxSize) {
            this.matchMap = new HashMap();
            this.matches = new PriorityList();
            this.maxSize = maxSize;
        }
        
        private boolean addMatch(final CharacterMatch match) {
            final CharacterMatch existingMatch = this.matchMap.get(match.character);
            if (existingMatch != null) {
                if (match.score <= existingMatch.score) {
                    return false;
                }
                this.matches.remove(existingMatch);
            }
            if (this.matches.size() >= this.maxSize) {
                final CharacterMatch worstMatch = (CharacterMatch)this.matches.getLast();
                if (match.score <= worstMatch.score) {
                    return false;
                }
                this.matches.removeLast();
                this.matchMap.remove(worstMatch.character);
            }
            this.matchMap.put(match.character, match);
            this.matches.add(match);
            return true;
        }
        
        private Character[] getMatches() {
            final Character[] matchArray = new Character[this.matches.size()];
            final Iterator matchIter = this.matches.iterator();
            int i = 0;
            while (matchIter.hasNext()) {
                final CharacterMatch nextMatch = matchIter.next();
                matchArray[i] = nextMatch.character;
                ++i;
            }
            return matchArray;
        }
    }
}
