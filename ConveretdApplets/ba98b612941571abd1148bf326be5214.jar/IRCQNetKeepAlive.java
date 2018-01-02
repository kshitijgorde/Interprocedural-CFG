// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetKeepAlive extends Thread
{
    IRCQNetTCPClient myTCP;
    
    public IRCQNetKeepAlive(final IRCQNetTCPClient myTCP) {
        this.myTCP = myTCP;
    }
    
    public void run() {
        try {
            while (true) {
                this.myTCP.Send("PING irc.icq.com");
                try {
                    Thread.sleep(600000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (ThreadDeath threadDeath) {}
    }
}
