// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import org.xmodel.xaction.IXAction;

public interface IKeyFeature
{
    void bind(final String p0, final boolean p1, final IXAction p2);
    
    void unbind(final String p0, final IXAction p1);
    
    public enum Key
    {
        comma("comma", 0), 
        f1("f1", 1), 
        f2("f2", 2), 
        f3("f3", 3), 
        f4("f4", 4), 
        f5("f5", 5), 
        f6("f6", 6), 
        f7("f7", 7), 
        f8("f8", 8), 
        f9("f9", 9), 
        f10("f10", 10), 
        f11("f11", 11), 
        f12("f12", 12), 
        f13("f13", 13), 
        f14("f14", 14), 
        f15("f15", 15), 
        f16("f16", 16), 
        f17("f17", 17), 
        f18("f18", 18), 
        f19("f19", 19), 
        f20("f20", 20), 
        f21("f21", 21), 
        f22("f22", 22), 
        f23("f23", 23), 
        f24("f24", 24), 
        alt("alt", 25), 
        altgraph("altgraph", 26), 
        control("control", 27), 
        meta("meta", 28), 
        shift("shift", 29), 
        tab("tab", 30), 
        escape("escape", 31), 
        backspace("backspace", 32), 
        enter("enter", 33), 
        capslock("capslock", 34), 
        numlock("numlock", 35), 
        scrolllock("scrolllock", 36), 
        home("home", 37), 
        end("end", 38), 
        insert("insert", 39), 
        pageup("pageup", 40), 
        pagedown("pagedown", 41), 
        cut("cut", 42), 
        paste("paste", 43), 
        copy("copy", 44), 
        multiply("multiply", 45), 
        subtract("subtract", 46), 
        accept("accept", 47), 
        again("again", 48), 
        allcandidates("allcandidates", 49), 
        alphanumeric("alphanumeric", 50), 
        begin("begin", 51), 
        cancel("cancel", 52), 
        clear("clear", 53), 
        contextmenu("contextmenu", 54), 
        convert("convert", 55), 
        eurosign("eurosign", 56), 
        find("find", 57), 
        fullwidth("fullwidth", 58), 
        halfwidth("halfwidth", 59), 
        help("help", 60), 
        keypadup("keypadup", 61), 
        keypadleft("keypadleft", 62), 
        keypadright("keypadright", 63), 
        keypaddown("keypaddown", 64), 
        up("up", 65), 
        left("left", 66), 
        right("right", 67), 
        down("down", 68), 
        numpad0("numpad0", 69), 
        numpad1("numpad1", 70), 
        numpad2("numpad2", 71), 
        numpad3("numpad3", 72), 
        numpad4("numpad4", 73), 
        numpad5("numpad5", 74), 
        numpad6("numpad6", 75), 
        numpad7("numpad7", 76), 
        numpad8("numpad8", 77), 
        numpad9("numpad9", 78), 
        pause("pause", 79), 
        printscreen("printscreen", 80), 
        separator("separator", 81), 
        space("space", 82), 
        stop("stop", 83), 
        undo("undo", 84), 
        windows("windows", 85);
        
        private Key(final String s, final int n) {
        }
    }
}
