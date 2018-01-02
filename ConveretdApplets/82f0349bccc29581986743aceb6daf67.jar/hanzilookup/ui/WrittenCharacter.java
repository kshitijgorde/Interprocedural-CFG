// 
// Decompiled by Procyon v0.5.30
// 

package hanzilookup.ui;

import java.awt.Point;
import java.awt.geom.Point2D;
import hanzilookup.data.CharacterDescriptor;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class WrittenCharacter
{
    private double leftX;
    private double rightX;
    private double topY;
    private double bottomY;
    private List strokeList;
    
    public WrittenCharacter() {
        this.strokeList = new ArrayList();
        this.resetEdges();
    }
    
    public List getStrokeList() {
        return this.strokeList;
    }
    
    public void addStroke(final WrittenStroke stroke) {
        this.strokeList.add(stroke);
    }
    
    public void clear() {
        this.strokeList.clear();
        this.resetEdges();
    }
    
    private void resetEdges() {
        this.leftX = Double.POSITIVE_INFINITY;
        this.rightX = Double.NEGATIVE_INFINITY;
        this.topY = Double.POSITIVE_INFINITY;
        this.bottomY = Double.NEGATIVE_INFINITY;
    }
    
    public void analyzeAndMark() {
        for (final WrittenStroke nextStroke : this.strokeList) {
            if (!nextStroke.isAnalyzed()) {
                nextStroke.analyzeAndMark();
            }
        }
    }
    
    public CharacterDescriptor buildCharacterDescriptor() {
        final int strokeCount = this.strokeList.size();
        int subStrokeCount = 0;
        final CharacterDescriptor descriptor = new CharacterDescriptor();
        final double[] directions = descriptor.getDirections();
        final double[] lengths = descriptor.getLengths();
        final Iterator strokeIter = this.strokeList.iterator();
        while (strokeIter.hasNext() && subStrokeCount < 64) {
            final WrittenStroke nextStroke = strokeIter.next();
            final List subStrokes = nextStroke.getSubStrokes();
            for (Iterator subStrokeIter = subStrokes.iterator(); subStrokeIter.hasNext() && subStrokeCount < 64; ++subStrokeCount) {
                final SubStrokeDescriptor subStroke = subStrokeIter.next();
                directions[subStrokeCount] = subStroke.direction;
                lengths[subStrokeCount] = subStroke.length;
            }
        }
        descriptor.setStrokeCount(strokeCount);
        descriptor.setSubStrokeCount(subStrokeCount);
        return descriptor;
    }
    
    static /* synthetic */ void access$1(final WrittenCharacter writtenCharacter, final double leftX) {
        writtenCharacter.leftX = leftX;
    }
    
    static /* synthetic */ void access$3(final WrittenCharacter writtenCharacter, final double rightX) {
        writtenCharacter.rightX = rightX;
    }
    
    static /* synthetic */ void access$5(final WrittenCharacter writtenCharacter, final double topY) {
        writtenCharacter.topY = topY;
    }
    
    static /* synthetic */ void access$7(final WrittenCharacter writtenCharacter, final double bottomY) {
        writtenCharacter.bottomY = bottomY;
    }
    
    public class WrittenStroke
    {
        private List pointList;
        private boolean isAnalyzed;
        private static final double MIN_SEGMENT_LENGTH = 12.5;
        private static final double MAX_LOCAL_LENGTH_RATIO = 1.1;
        private static final double MAX_RUNNING_LENGTH_RATIO = 1.09;
        
        public WrittenStroke() {
            this.pointList = new ArrayList();
            this.isAnalyzed = false;
        }
        
        public List getPointList() {
            return this.pointList;
        }
        
        public boolean isAnalyzed() {
            return this.isAnalyzed;
        }
        
        public void addPoint(final WrittenPoint point) {
            final int pointX = (int)point.getX();
            final int pointY = (int)point.getY();
            WrittenCharacter.access$1(WrittenCharacter.this, Math.min(pointX, WrittenCharacter.this.leftX));
            WrittenCharacter.access$3(WrittenCharacter.this, Math.max(pointX, WrittenCharacter.this.rightX));
            WrittenCharacter.access$5(WrittenCharacter.this, Math.min(pointY, WrittenCharacter.this.topY));
            WrittenCharacter.access$7(WrittenCharacter.this, Math.max(pointY, WrittenCharacter.this.bottomY));
            this.pointList.add(point);
        }
        
        public List getSubStrokes() {
            if (!this.isAnalyzed) {
                this.analyzeAndMark();
            }
            final List subStrokes = new ArrayList();
            final Iterator pointIter = this.pointList.iterator();
            WrittenPoint previousPoint = pointIter.next();
            while (pointIter.hasNext()) {
                final WrittenPoint nextPoint = pointIter.next();
                if (nextPoint.isPivot()) {
                    final double direction = previousPoint.getDirection(nextPoint);
                    final double normalizedLength = previousPoint.getDistanceNormalized(nextPoint);
                    final SubStrokeDescriptor subStroke = new SubStrokeDescriptor(direction, normalizedLength, null);
                    subStrokes.add(subStroke);
                    previousPoint = nextPoint;
                }
            }
            return subStrokes;
        }
        
        private void analyzeAndMark() {
            final Iterator pointIter = this.pointList.iterator();
            WrittenPoint previousPoint;
            WrittenPoint firstPoint = previousPoint = pointIter.next();
            WrittenPoint pivotPoint = pointIter.next();
            firstPoint.setIsPivot(true);
            int subStrokeIndex = 1;
            firstPoint.setSubStrokeIndex(subStrokeIndex);
            pivotPoint.setSubStrokeIndex(subStrokeIndex);
            double runningLength;
            double localLength = runningLength = firstPoint.distance(pivotPoint);
            while (pointIter.hasNext()) {
                final WrittenPoint nextPoint = pointIter.next();
                final double pivotLength = pivotPoint.distance(nextPoint);
                localLength += pivotLength;
                runningLength += pivotLength;
                if (localLength >= 1.1 * previousPoint.distance(nextPoint) || runningLength >= 1.09 * firstPoint.distance(nextPoint)) {
                    if (previousPoint.isPivot() && previousPoint.distance(pivotPoint) < 12.5) {
                        previousPoint.setIsPivot(false);
                        previousPoint.setSubStrokeIndex(subStrokeIndex - 1);
                    }
                    else {
                        ++subStrokeIndex;
                    }
                    pivotPoint.setIsPivot(true);
                    runningLength = pivotLength;
                    firstPoint = pivotPoint;
                }
                localLength = pivotLength;
                previousPoint = pivotPoint;
                pivotPoint = nextPoint;
                pivotPoint.setSubStrokeIndex(subStrokeIndex);
            }
            pivotPoint.setIsPivot(true);
            if (previousPoint.isPivot() && previousPoint.distance(pivotPoint) < 12.5 && previousPoint != this.pointList.get(0)) {
                previousPoint.setIsPivot(false);
                pivotPoint.setSubStrokeIndex(subStrokeIndex - 1);
            }
            this.isAnalyzed = true;
        }
    }
    
    public class WrittenPoint extends Point
    {
        private int subStrokeIndex;
        private boolean isPivot;
        
        public WrittenPoint(final int x, final int y) {
            super(x, y);
        }
        
        public int getSubStrokeIndex() {
            return this.subStrokeIndex;
        }
        
        private void setSubStrokeIndex(final int subStrokeIndex) {
            this.subStrokeIndex = subStrokeIndex;
        }
        
        public boolean isPivot() {
            return this.isPivot;
        }
        
        private void setIsPivot(final boolean isPivot) {
            this.isPivot = isPivot;
        }
        
        private double getDistanceNormalized(final WrittenPoint comparePoint) {
            final double width = WrittenCharacter.this.rightX - WrittenCharacter.this.leftX;
            final double height = WrittenCharacter.this.bottomY - WrittenCharacter.this.topY;
            final double dimensionSquared = (width > height) ? (width * width) : (height * height);
            final double normalizer = Math.sqrt(dimensionSquared + dimensionSquared);
            double distanceNormalized = this.distance(comparePoint) / normalizer;
            distanceNormalized = Math.min(distanceNormalized, 1.0);
            return distanceNormalized;
        }
        
        private double getDirection(final WrittenPoint comparePoint) {
            final double dx = this.getX() - comparePoint.getX();
            final double dy = this.getY() - comparePoint.getY();
            final double direction = 3.141592653589793 - Math.atan2(dy, dx);
            return direction;
        }
    }
    
    public static class SubStrokeDescriptor
    {
        private double direction;
        private double length;
        
        private SubStrokeDescriptor(final double direction, final double length) {
            this.direction = direction;
            this.length = length;
        }
        
        public double getDirection() {
            return this.direction;
        }
        
        public double getLength() {
            return this.length;
        }
    }
}
