// 
// Decompiled by Procyon v0.5.30
// 

package SET;

import IO.romLoader;

public class globalSet
{
    public static String protocal;
    public static String site;
    public static String domain;
    public static String loadfile;
    public static String codebase;
    public static romLoader rl;
    
    static {
        globalSet.protocal = "http";
        globalSet.site = "api";
        globalSet.domain = "kryptonware.com";
        globalSet.loadfile = "gameLoad.php";
        globalSet.codebase = "";
    }
}
