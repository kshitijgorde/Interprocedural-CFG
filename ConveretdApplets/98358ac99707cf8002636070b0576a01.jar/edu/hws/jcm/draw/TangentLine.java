// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import edu.hws.jcm.data.Constant;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;

public class TangentLine extends DrawGeometric
{
    public TangentLine(final Value value, final Function function) {
        super(5, value, new ValueMath(function, value), new Constant(1.0), new ValueMath(function.derivative(1), value));
    }
}
