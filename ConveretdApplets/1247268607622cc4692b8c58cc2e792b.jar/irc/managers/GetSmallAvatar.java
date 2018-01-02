// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.util.Enumeration;
import irc.com.nick.NickInfoBean;
import irc.com.nick.NickInfos;

public class GetSmallAvatar extends Thread
{
    private String nick;
    
    public GetSmallAvatar(final String nick) {
        this.nick = nick;
        this.setPriority(1);
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        int n = 0;
        NickInfos.setSall_avatar(this.nick.toLowerCase());
        while (true) {
            final Enumeration<NickInfoBean> elements = (Enumeration<NickInfoBean>)NickInfos.users.elements();
            while (elements.hasMoreElements()) {
                final NickInfoBean nickInfoBean = elements.nextElement();
                if (nickInfoBean != null && nickInfoBean.getNick() != null && !nickInfoBean.getNick().toLowerCase().equals(this.nick.toLowerCase())) {
                    if (!NickInfos.smallAvatar.containsKey(nickInfoBean.getNick().toLowerCase())) {
                        NickInfos.setSall_avatar(nickInfoBean.getNick().toLowerCase());
                        try {
                            Thread.sleep(200L);
                        }
                        catch (InterruptedException ex2) {}
                        ++n;
                    }
                    if (n <= 10) {
                        continue;
                    }
                    n = 0;
                    try {
                        Thread.sleep(2000L);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
            try {
                Thread.sleep(10000L);
            }
            catch (InterruptedException ex4) {}
        }
    }
}
