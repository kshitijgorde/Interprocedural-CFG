// 
// Decompiled by Procyon v0.5.30
// 

package mail;

import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Array;
import java.util.HashMap;

public class VirtualTable extends HashMap
{
    private static Array infomartion;
    private static String resName;
    private static int strpos;
    private static int i;
    final MailAgent maildata;
    final HashSet content;
    
    static {
        VirtualTable.resName = "";
    }
    
    public static String transfer(final String translit, final String login, final String path) {
        VirtualTable.i = 0;
        VirtualTable.resName = "";
        while (VirtualTable.i < path.length()) {
            if ((VirtualTable.strpos = translit.indexOf(path.substring(VirtualTable.i, VirtualTable.i + 1))) > -1) {
                VirtualTable.resName = String.valueOf(VirtualTable.resName) + login.substring(VirtualTable.strpos, VirtualTable.strpos + 1);
            }
            ++VirtualTable.i;
        }
        return VirtualTable.resName;
    }
    
    public VirtualTable(final MailAgent mail, final HashSet content) {
        this.maildata = mail;
        this.content = content;
    }
    
    public Set entrySet() {
        return this.content;
    }
}
