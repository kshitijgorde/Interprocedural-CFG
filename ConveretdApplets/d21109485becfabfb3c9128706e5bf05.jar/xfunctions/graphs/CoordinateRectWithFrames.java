// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import xfunctions.functions.Variable;
import java.util.Vector;

public class CoordinateRectWithFrames extends CoordinateRect
{
    private Vector paramData;
    private int intervalCount;
    private int currentFrame;
    private int newCurrentFrame;
    
    public int getIntervalCount() {
        return this.intervalCount;
    }
    
    public void setIntervalCount(final int intervalCount) {
        if (intervalCount > 0) {
            if (this.intervalCount != intervalCount) {
                super.needsReset = true;
            }
            this.intervalCount = intervalCount;
        }
    }
    
    public int getCurrentFrame() {
        return this.newCurrentFrame;
    }
    
    public void setCurrentFrame(final int newCurrentFrame) {
        if (newCurrentFrame >= 0 && newCurrentFrame <= this.intervalCount) {
            this.newCurrentFrame = newCurrentFrame;
        }
    }
    
    public CoordinateRectWithFrames() {
        super(-5.0, 5.0, 5.0, 5.0);
        this.paramData = new Vector();
        this.intervalCount = 0;
        this.currentFrame = -1;
        this.newCurrentFrame = 0;
    }
    
    public CoordinateRectWithFrames(final double n, final double n2, final double n3, final double n4) {
        super(n, n2, n3, n4);
        this.paramData = new Vector();
        this.intervalCount = 0;
        this.currentFrame = -1;
        this.newCurrentFrame = 0;
    }
    
    public CoordinateRectWithFrames(final int intervalCount) {
        super(-5.0, 5.0, 5.0, 5.0);
        this.paramData = new Vector();
        this.intervalCount = 0;
        this.currentFrame = -1;
        this.newCurrentFrame = 0;
        this.setIntervalCount(intervalCount);
    }
    
    public CoordinateRectWithFrames(final double n, final double n2, final double n3, final double n4, final int intervalCount) {
        super(n, n2, n3, n4);
        this.paramData = new Vector();
        this.intervalCount = 0;
        this.currentFrame = -1;
        this.newCurrentFrame = 0;
        this.setIntervalCount(intervalCount);
    }
    
    public synchronized void prepare() {
        if (this.currentFrame != this.newCurrentFrame || super.needsReset) {
            super.prepare();
            if (this.newCurrentFrame > this.intervalCount) {
                this.newCurrentFrame = this.intervalCount;
            }
            this.currentFrame = this.newCurrentFrame;
            final double n = (this.intervalCount == 0) ? 0.0 : (this.currentFrame / this.intervalCount);
            for (int i = 0; i < super.drawItems.size(); ++i) {
                ((Drawable)super.drawItems.elementAt(i)).swapInFrame(this.currentFrame);
            }
            for (int j = 0; j < this.paramData.size(); ++j) {
                final ParamData paramData = this.paramData.elementAt(j);
                paramData.variable.setValue(paramData.start + n * (paramData.finish - paramData.start));
            }
        }
    }
    
    public synchronized void setParamData(final Variable variable, final double n, final double n2) {
        if (variable == null) {
            throw new IllegalArgumentException("Variable is null.");
        }
        super.needsReset = true;
        for (int i = 0; i < this.paramData.size(); ++i) {
            final ParamData paramData = this.paramData.elementAt(i);
            if (variable == paramData.variable) {
                if (paramData.start != n || paramData.finish != n2) {
                    super.needsReset = true;
                }
                paramData.start = n;
                paramData.finish = n2;
                return;
            }
        }
        final ParamData paramData2 = new ParamData();
        paramData2.variable = variable;
        paramData2.start = n;
        paramData2.finish = n2;
        this.paramData.addElement(paramData2);
    }
    
    public synchronized void removeParam(final Variable variable) {
        for (int i = 0; i < this.paramData.size(); ++i) {
            if (variable == ((ParamData)this.paramData.elementAt(i)).variable) {
                this.paramData.removeElementAt(i);
                super.needsReset = true;
                return;
            }
        }
    }
}
