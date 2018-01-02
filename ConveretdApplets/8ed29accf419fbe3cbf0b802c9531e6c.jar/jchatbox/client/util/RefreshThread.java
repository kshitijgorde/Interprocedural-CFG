// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.util;

import jchatbox.client.ChatPanel;

public class RefreshThread extends Thread
{
    private ChatPanel _$9142;
    private boolean _$9143;
    
    public RefreshThread(final ChatPanel $9142) {
        this._$9142 = null;
        this._$9143 = true;
        this._$9142 = $9142;
    }
    
    public void disable() {
        this._$9143 = false;
    }
    
    public void run() {
        while (this._$9143) {
            final int refreshRate = this._$9142.getRefreshRate();
            if (refreshRate > 0) {
                try {
                    Thread.sleep(refreshRate * 1000);
                    if (!this._$9143) {
                        continue;
                    }
                    this._$619(9, this.getClass().getName(), String.valueOf(String.valueOf(new StringBuffer("Refreshing : ").append(refreshRate * 1000).append(" ms"))));
                    this._$9142.performRefresh();
                }
                catch (InterruptedException ex) {
                    this._$619(5, this.getClass().getName(), "Refresh Thread error : ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                }
            }
            else {
                this._$9143 = false;
            }
        }
        this._$619(5, this.getClass().getName(), "Refresh Thread stopped");
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
}
