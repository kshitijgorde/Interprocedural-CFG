// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.representations;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import org.jruby.compiler.ir.operands.Label;

public class ExceptionRegion
{
    Label _ensureBlockLabel;
    List<Label> _rescueBlockLabels;
    List<BasicBlock> _exclusiveBBs;
    List<ExceptionRegion> _nestedRegions;
    BasicBlock _endBB;
    BasicBlock _firstRescueBB;
    
    public ExceptionRegion(final List<Label> rescueBlockLabels) {
        this._rescueBlockLabels = rescueBlockLabels;
        this._exclusiveBBs = new ArrayList<BasicBlock>();
        this._nestedRegions = new ArrayList<ExceptionRegion>();
    }
    
    public void setEndBB(final BasicBlock bb) {
        this._endBB = bb;
    }
    
    public void addBB(final BasicBlock bb) {
        this._exclusiveBBs.add(bb);
    }
    
    public void addNestedRegion(final ExceptionRegion r) {
        this._nestedRegions.add(r);
    }
    
    public void setFirstRescueBB(final BasicBlock frbb) {
        this._firstRescueBB = frbb;
    }
    
    public Label getFirstRescueBlockLabel() {
        return this._rescueBlockLabels.get(0);
    }
    
    public ExceptionRegion cloneForInlining(final InlinerInfo ii) {
        final List<Label> newLabels = new ArrayList<Label>();
        for (final Label l : this._rescueBlockLabels) {
            newLabels.add(ii.getRenamedLabel(l));
        }
        final ExceptionRegion newR = new ExceptionRegion(newLabels);
        newR._endBB = ii.getRenamedBB(this._endBB);
        newR._firstRescueBB = ii.getRenamedBB(this._firstRescueBB);
        for (final BasicBlock b : this._exclusiveBBs) {
            newR.addBB(ii.getRenamedBB(b));
        }
        for (final ExceptionRegion r : this._nestedRegions) {
            newR.addNestedRegion(r.cloneForInlining(ii));
        }
        return newR;
    }
}
