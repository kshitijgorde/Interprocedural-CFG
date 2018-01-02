// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import com.pchat.sc.MsgOptions;
import java.awt.Component;

public interface ChatRender
{
    Component getComp();
    
    void showSelf(final String p0, final String p1, final MsgOptions p2);
    
    void showOthers(final String p0, final String p1, final MsgOptions p2);
    
    void showSysInfo(final String p0, final MsgOptions p1);
    
    void showSysError(final String p0, final MsgOptions p1);
    
    void showLocalInfo(final String p0, final MsgOptions p1);
    
    void showLocalError(final String p0, final MsgOptions p1, final boolean p2);
    
    void modMsg(final String p0, final String p1);
    
    void modAnswer(final String p0, final String p1, final String p2, final String p3);
}
