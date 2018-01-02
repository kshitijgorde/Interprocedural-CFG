// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.util.Iterator;
import java.awt.Point;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PositionManager
{
    private final int myFramesCount;
    private final int myFramesBetweenSymbols;
    private final Map myActorId2Approximator;
    private final List myActors;
    
    public PositionManager(final int myFramesCount, final int myFramesBetweenSymbols) {
        this.myFramesCount = myFramesCount;
        this.myFramesBetweenSymbols = myFramesBetweenSymbols;
        this.myActorId2Approximator = new HashMap();
        this.myActors = new LinkedList();
    }
    
    public void addActor(final AnimationActor animationActor, final Point point, final Point point2, final Point point3) {
        this.registerActor(animationActor, (point.x == point2.x || point.x == point3.x || point2.x == point3.x) ? new LinearApproximator(point, point3) : new QuadraticApproximator(point, point2, point3));
    }
    
    public void addAppearingActor(final AnimationActor animationActor, final Point point, final float n) {
        this.registerActor(animationActor, new SwitchApproximator(point, false, n));
    }
    
    public void addDisappearingActor(final AnimationActor animationActor, final Point point, final float n) {
        this.registerActor(animationActor, new SwitchApproximator(point, true, n));
    }
    
    private void registerActor(final AnimationActor animationActor, final Approximator approximator) {
        this.myActorId2Approximator.put(animationActor.getIdentity(), approximator);
        this.myActors.add(animationActor);
    }
    
    public void placeActors(final int n) {
        int n2 = 0;
        for (final AnimationActor animationActor : this.myActors) {
            int myFramesCount = n - n2 * this.myFramesBetweenSymbols;
            if (myFramesCount < 0) {
                myFramesCount = 0;
            }
            if (myFramesCount > this.myFramesCount) {
                myFramesCount = this.myFramesCount;
            }
            animationActor.acceptPosition(this.myActorId2Approximator.get(animationActor.getIdentity()).approximate(myFramesCount, this.myFramesCount), myFramesCount, this.myFramesCount);
            ++n2;
        }
    }
    
    private static class QuadraticApproximator implements Approximator
    {
        private float myA;
        private float myB;
        private float myC;
        private final Point myStartPoint;
        private final Point myEndPoint;
        
        public QuadraticApproximator(final Point point, final Point point2, final Point point3) {
            this.myStartPoint = (Point)point.clone();
            this.myEndPoint = (Point)point3.clone();
            final float n = (float)point.getX();
            final float n2 = (float)point.getY();
            final float n3 = (float)point2.getX();
            final float n4 = (float)point2.getY();
            final float n5 = (float)point3.getX();
            final float n6 = (float)point3.getY();
            final float n7 = n * n;
            final float n8 = n3 * n3;
            final float n9 = n5 * n5;
            this.myB = ((n7 - n8) * (n2 - n6) - (n2 - n4) * (n7 - n9)) / ((n7 - n8) * (n - n5) - (n - n3) * (n7 - n9));
            this.myA = (n2 - n6 - this.myB * (n - n5)) / (n7 - n9);
            this.myC = n2 - this.myB * n - this.myA * n7;
            if (isBrokenValue(this.myA) || isBrokenValue(this.myB) || isBrokenValue(this.myC)) {
                throw new IllegalStateException("Animator should only use real curves, but all actual points are on single line:" + point + ", " + point2 + ", " + point3);
            }
        }
        
        public Point approximate(final int n, final int n2) {
            return this.approximate((float)this.myStartPoint.getX() + (float)(this.myEndPoint.getX() - this.myStartPoint.getX()) * n / n2);
        }
        
        public Point approximate(final float n) {
            return new Point(Math.round(n), Math.round(this.myA * n * n + this.myB * n + this.myC));
        }
        
        private static boolean isBrokenValue(final float n) {
            return Float.isInfinite(n) || Float.isNaN(n);
        }
    }
    
    private interface Approximator
    {
        Point approximate(final int p0, final int p1);
    }
    
    private static class SwitchApproximator extends LinearApproximator
    {
        private final boolean myInitiallyShown;
        private final float myRate;
        
        public SwitchApproximator(final Point point, final boolean myInitiallyShown, final float myRate) {
            super(point, point);
            this.myInitiallyShown = myInitiallyShown;
            this.myRate = myRate;
        }
        
        public Point approximate(final int n, final int n2) {
            return this.shouldBeShown(n, n2) ? super.approximate(n, n2) : null;
        }
        
        private boolean shouldBeShown(final int n, final int n2) {
            if (this.myInitiallyShown) {
                return n / n2 <= this.myRate;
            }
            return n / n2 >= 1.0f - this.myRate;
        }
    }
    
    private static class LinearApproximator implements Approximator
    {
        private final int myDx;
        private final int myDy;
        private final int myStartX;
        private final int myStartY;
        
        public LinearApproximator(final Point point, final Point point2) {
            this.myStartX = point.x;
            this.myStartY = point.y;
            this.myDx = point2.x - this.myStartX;
            this.myDy = point2.y - this.myStartY;
        }
        
        public Point approximate(final int n, final int n2) {
            final float n3 = n / n2;
            return new Point(Math.round(this.myStartX + n3 * this.myDx), Math.round(this.myStartY + n3 * this.myDy));
        }
    }
}
