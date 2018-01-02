// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

public class SimpleQueueItem
{
    public static final int E_ADD = 2;
    public static final int E_DEL = 4;
    public static final int E_CLR = 6;
    public static final int E_UPD = 8;
    public static final int E_REDO = 10;
    public static final int E_SET = 12;
    public static final int E_EXT2 = 14;
    public static final int E_EXT3 = 16;
    public static final int E_EXT4 = 18;
    public static final int E_EXT5 = 20;
    public static final int E_EXT6 = 22;
    public static final int E_EXT7 = 24;
    public static final int E_EXT8 = 26;
    public int type;
    public Object obj;
    public Object ext;
    
    public SimpleQueueItem() {
        this.obj = null;
        this.ext = null;
    }
    
    public String toString() {
        return this.type + " " + this.obj;
    }
}
