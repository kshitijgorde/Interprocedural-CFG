// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.backtrace;

import org.jruby.interpreter.Interpreter;
import org.jruby.evaluator.ASTInterpreter;
import java.util.HashMap;
import java.util.Map;

public enum FrameType
{
    METHOD, 
    BLOCK, 
    EVAL, 
    CLASS, 
    ROOT;
    
    public static final Map<String, FrameType> INTERPRETED_FRAMES;
    
    static {
        (INTERPRETED_FRAMES = new HashMap<String, FrameType>()).put(ASTInterpreter.class.getName() + ".INTERPRET_METHOD", FrameType.METHOD);
        FrameType.INTERPRETED_FRAMES.put(ASTInterpreter.class.getName() + ".INTERPRET_EVAL", FrameType.EVAL);
        FrameType.INTERPRETED_FRAMES.put(ASTInterpreter.class.getName() + ".INTERPRET_CLASS", FrameType.CLASS);
        FrameType.INTERPRETED_FRAMES.put(ASTInterpreter.class.getName() + ".INTERPRET_BLOCK", FrameType.BLOCK);
        FrameType.INTERPRETED_FRAMES.put(ASTInterpreter.class.getName() + ".INTERPRET_ROOT", FrameType.ROOT);
        FrameType.INTERPRETED_FRAMES.put(Interpreter.class.getName() + ".INTERPRET_METHOD", FrameType.ROOT);
    }
}
