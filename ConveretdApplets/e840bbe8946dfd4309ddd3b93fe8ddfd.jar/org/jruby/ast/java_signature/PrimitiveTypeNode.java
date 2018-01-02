// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

public class PrimitiveTypeNode extends TypeNode
{
    public static final PrimitiveTypeNode BYTE;
    public static final PrimitiveTypeNode SHORT;
    public static final PrimitiveTypeNode INT;
    public static final PrimitiveTypeNode LONG;
    public static final PrimitiveTypeNode CHAR;
    public static final PrimitiveTypeNode FLOAT;
    public static final PrimitiveTypeNode DOUBLE;
    public static final PrimitiveTypeNode BOOLEAN;
    public static final PrimitiveTypeNode VOID;
    private final String wrapperName;
    
    protected PrimitiveTypeNode(final String name, final String wrapperName) {
        super(name);
        this.wrapperName = wrapperName;
    }
    
    public boolean isPrimitive() {
        return true;
    }
    
    public boolean isVoid() {
        return this.name.equals("void");
    }
    
    public String getWrapperName() {
        return this.wrapperName;
    }
    
    static {
        BYTE = new PrimitiveTypeNode("byte", "Byte");
        SHORT = new PrimitiveTypeNode("short", "Short");
        INT = new PrimitiveTypeNode("int", "Integer");
        LONG = new PrimitiveTypeNode("long", "Long");
        CHAR = new PrimitiveTypeNode("char", "Character");
        FLOAT = new PrimitiveTypeNode("float", "Float");
        DOUBLE = new PrimitiveTypeNode("double", "Double");
        BOOLEAN = new PrimitiveTypeNode("boolean", "Boolean");
        VOID = new PrimitiveTypeNode("void", "void");
    }
}
