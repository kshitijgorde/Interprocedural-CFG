// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Value;

public class Crosshair extends DrawGeometric
{
    public Crosshair(final Value value, final Value value2) {
        super(11, value, value2, 7, 7);
    }
    
    public Crosshair(final Value value, final Function function) {
        super(11, value, new ValueMath(function, value), 7, 7);
    }
}
