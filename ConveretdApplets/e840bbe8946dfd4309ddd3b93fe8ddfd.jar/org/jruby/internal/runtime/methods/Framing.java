// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.internal.runtime.methods;

import org.jruby.anno.FrameField;
import java.util.EnumSet;

public enum Framing
{
    Full(EnumSet.allOf(FrameField.class)), 
    Backtrace(EnumSet.of(FrameField.METHODNAME, FrameField.FILENAME, FrameField.LINE)), 
    None(EnumSet.noneOf(FrameField.class));
    
    private final EnumSet<FrameField> frameField;
    
    private Framing(final EnumSet<FrameField> frameField) {
        this.frameField = frameField;
    }
}
