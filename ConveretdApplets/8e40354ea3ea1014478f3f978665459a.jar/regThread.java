import java.util.LinkedList;

// 
// Decompiled by Procyon v0.5.30
// 

public class regThread implements Runnable
{
    public static int state;
    public static int refresh;
    public static int debug;
    public static String registrar;
    public static String username;
    public static boolean exiting;
    
    public regThread(final String host, final String user) {
        regThread.state = 0;
        regThread.refresh = 3600;
        regThread.registrar = host;
        regThread.username = user;
        this.tryReg();
    }
    
    public void run() {
        if (regThread.debug > 0) {
            Starphone.log("Called regThread's run()");
        }
        while (true) {
            if (regThread.state == 1) {
                try {
                    Thread.sleep(1000 * (regThread.refresh - 10));
                }
                catch (InterruptedException ie) {
                    System.out.println("interrupt 1 in regThread");
                }
                if (regThread.debug > 0) {
                    Starphone.log("Registration about to expire: attempting to reregister now");
                }
                this.tryReg();
            }
            if (regThread.state == 2) {
                try {
                    Thread.sleep(15000L);
                }
                catch (InterruptedException ie) {
                    System.out.println("interrupt 2 in regThread");
                }
                if (regThread.debug > 0) {
                    Starphone.log("Last registration attempt rejected: attempting to reregister now");
                }
                this.tryReg();
            }
            if (regThread.state == 4) {
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ie) {
                    System.out.println("interrupt 3 in regThread");
                }
                if (regThread.debug > 0) {
                    Starphone.log("Previous registration released: attempting to reregister now");
                }
                this.tryReg();
            }
        }
    }
    
    public void tryReg() {
        if (regThread.username == null || regThread.username.equals("")) {
            return;
        }
        regThread.state = 3;
        final IAXCall ic = new IAXCall(regThread.registrar);
        if (regThread.debug > 0) {
            Starphone.log("calling IAXCall constructor from regThread, ic.scallno = " + ic.scallno);
        }
        ic.setPeerAddress(regThread.registrar);
        IAXCall.addCall(ic);
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.USERNAME, regThread.username));
        final IAXFullFrame ifr = new IAXFullFrame(ic, IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.REGREQ.ordinal(), ieList);
        networkThread.qadd(new qent(ic, ifr));
    }
    
    public static void releaseReg() {
        regThread.state = 4;
        final IAXCall ic = new IAXCall(regThread.registrar);
        ic.setPeerAddress(regThread.registrar);
        IAXCall.addCall(ic);
        final LinkedList<IAXIE> ieList = new LinkedList<IAXIE>();
        ieList.add(new IAXIE(IAXIE.IE.USERNAME, regThread.username));
        final IAXFullFrame ifr = new IAXFullFrame(ic, IAXFrame.FRAMETYPES.IAX, IAXFrame.IAXSUBCLASS.REGREL.ordinal(), ieList);
        networkThread.qadd(new qent(ic, ifr));
    }
    
    public static void setState(final int s, final int ref) {
        regThread.state = s;
        regThread.refresh = ref;
        if (regThread.debug > 0) {
            Starphone.log("Reg state set to " + s + ", with refresh of " + regThread.refresh);
        }
    }
    
    public static int getRegState() {
        return regThread.state;
    }
    
    public static String getRegUsername() {
        return regThread.username;
    }
    
    public static String getRegHost() {
        return regThread.registrar;
    }
    
    public static void setRegUsername(final String s) {
        if (!s.equals(regThread.username)) {
            regThread.username = s;
            setState(4, regThread.refresh);
        }
    }
    
    static {
        regThread.debug = 0;
        regThread.exiting = false;
    }
}
