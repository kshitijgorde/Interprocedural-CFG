// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Vector;
import java.util.Hashtable;

public class NFParamDef
{
    public String param;
    public int type;
    public boolean loaded;
    public Object val;
    public Hashtable symtable;
    public Vector tuple_def;
    public Vector tuple_val;
    public Vector tuple_tmp;
    public NFParamDef vector_def;
    public Vector vector_val;
    public Vector vector_tmp;
    public NFParamDef inVector;
    public String imageLabel;
    public boolean changed;
    public Object expr;
    public NFParamDef parent;
    public boolean varLength;
    public Object dataBean;
    
    NFParamDef(final String param, final int type) {
        this.param = param;
        this.type = type;
        this.loaded = false;
        this.val = null;
        this.symtable = null;
        this.tuple_def = null;
        this.tuple_val = null;
        this.tuple_tmp = null;
        this.vector_def = null;
        this.vector_val = null;
        this.vector_tmp = null;
        this.inVector = null;
        this.imageLabel = null;
        this.changed = false;
        this.expr = null;
        this.parent = null;
        this.varLength = false;
        this.dataBean = null;
    }
}
