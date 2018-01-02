// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.ArrayList;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import java.util.Iterator;
import org.jruby.compiler.ir.Operation;
import java.util.List;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.Operand;

public class ExceptionRegionStartMarkerInstr extends Instr
{
    private static Operand[] _empty;
    public final Label _begin;
    public final Label _end;
    public final List<Label> _rescueBlockLabels;
    
    public ExceptionRegionStartMarkerInstr(final Label rBegin, final Label rEnd, final List<Label> rbLabels) {
        super(Operation.EXC_REGION_START);
        this._begin = rBegin;
        this._end = rEnd;
        this._rescueBlockLabels = rbLabels;
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer(super.toString());
        buf.append("(").append(this._begin).append(", ").append(this._end).append(", ").append("[");
        for (final Label l : this._rescueBlockLabels) {
            buf.append(l).append(",");
        }
        buf.append("])");
        return buf.toString();
    }
    
    public Operand[] getOperands() {
        return ExceptionRegionStartMarkerInstr._empty;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        final List<Label> newLabels = new ArrayList<Label>();
        for (final Label l : this._rescueBlockLabels) {
            newLabels.add(ii.getRenamedLabel(l));
        }
        return new ExceptionRegionStartMarkerInstr(ii.getRenamedLabel(this._begin), ii.getRenamedLabel(this._end), newLabels);
    }
    
    static {
        ExceptionRegionStartMarkerInstr._empty = new Operand[0];
    }
}
