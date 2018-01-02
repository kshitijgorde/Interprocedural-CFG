// 
// Decompiled by Procyon v0.5.30
// 

package sexy.util;

import java.util.Hashtable;

public class XMLElement
{
    public static final int TYPE_NONE = 0;
    public static final int TYPE_START = 1;
    public static final int TYPE_END = 2;
    public static final int TYPE_ELEMENT = 3;
    public static final int TYPE_INSTRUCTION = 4;
    public int mType;
    public String mSection;
    public String mValue;
    public String mInstruction;
    public Hashtable mAttributes;
}
