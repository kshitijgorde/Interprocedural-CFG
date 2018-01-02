// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.converter;

public enum Type
{
    BOOLEAN("boolean", "t", "true", "true"), 
    SIGNEDBYTE(" byte", "b", "1", "1"), 
    UNSIGNEDBYTE("/*unsigned*/ byte", "u", "1", "1"), 
    SHORT("short", "s", "1", "1"), 
    CHAR("char", "c", "'1'", "'1'"), 
    INT("int", "i", "1", "1"), 
    LONG("long", "n", "1L", "1L"), 
    FLOAT("float", "f", "1.0f", "1.0f"), 
    DOUBLE("double", "d", "1.0d", "1.0d"), 
    STRING("String", "g", "\"1\"", "\"1\""), 
    WRAPPEDBOOLEAN("Boolean", "tt", "Boolean.TRUE", "Boolean.TRUE"), 
    WRAPPEDSIGNEDBYTE("/*signed*/ Byte", "bb", "new Byte((byte)1)", "1"), 
    WRAPPEDUNSIGNEDBYTE("/*unsigned*/ Byte", "uu", "new Byte((byte)1)", "1"), 
    WRAPPEDSHORT("Short", "ss", "new Short((short)1)", "1"), 
    WRAPPEDCHARACTER("Character", "cc", "new Character('1')", "'1'"), 
    WRAPPEDINTEGER("Integer", "ii", "new Integer(1)", "1"), 
    WRAPPEDLONG("Long", "nn", "new Long(1L)", "1L"), 
    WRAPPEDFLOAT("Float", "ff", "new Float(1.0f)", "1.0f"), 
    WRAPPEDDOUBLE("Double", "dd", "new Double(1.0d)", "1.0d");
    
    private final String init14;
    private final String init15;
    private final String name;
    private final String var;
    
    public static String how(final Type from, final Type to, final boolean considerAutobox) {
        if (from != to) {
            return "// to " + to.name + " " + to.var + " from " + from.name + " " + from.var + "\n" + howCore(from, to, considerAutobox);
        }
        return "// no conversion necessary";
    }
    
    public static String howCore(final Type from, final Type to, final boolean considerAutobox) {
        if (from != to) {
            return considerAutobox ? Grid15.how15[from.ordinal()][to.ordinal()] : Grid14.how14[from.ordinal()][to.ordinal()];
        }
        return "// no conversion necessary";
    }
    
    public String toString() {
        return this.name;
    }
    
    private Type(final String name, final String var, final String init14, final String init15) {
        this.name = name;
        this.var = var;
        this.init14 = init14;
        this.init15 = init15;
    }
    
    String getInit14() {
        return this.init14;
    }
    
    String getInit15() {
        return this.init15;
    }
    
    String getName() {
        return this.name;
    }
    
    String getVar() {
        return this.var;
    }
}
