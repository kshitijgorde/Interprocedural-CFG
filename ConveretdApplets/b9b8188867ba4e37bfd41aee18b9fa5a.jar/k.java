import java.io.PrintStream;
import java.util.StringTokenizer;
import java.net.Socket;
import java.io.IOException;
import netscape.security.PrivilegeManager;
import java.io.DataInputStream;
import java.net.ServerSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public class k extends Thread
{
    public ServerSocket bn;
    public boolean bm;
    public String bl;
    public int bk;
    public boolean bj;
    public DataInputStream bi;
    
    public k(final int n, final boolean bj, final String bl) {
        super("Identd Thread");
        this.bm = false;
        this.bl = "JPilotChat";
        this.bj = true;
        this.bl = bl;
        this.bj = bj;
        if (bj) {
            try {
                PrivilegeManager.enablePrivilege("30Capabilities");
            }
            catch (Exception ex) {
                System.out.println("Failed to obtain permissionn");
            }
        }
        try {
            this.bn = new ServerSocket(n);
            this.bm = true;
        }
        catch (Exception ex2) {
            this.bm = false;
        }
    }
    
    public final void run() {
        if (this.bj) {
            try {
                PrivilegeManager.enablePrivilege("30Capabilities");
            }
            catch (Exception ex) {
                System.out.println("No permission for socket.");
            }
        }
        if (this.bm) {
            try {
                final Socket accept = this.bn.accept();
                final DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
                this.a2(accept);
            }
            catch (IOException ex2) {}
        }
        this.a1();
    }
    
    public final void a2(final Socket socket) {
        String line;
        try {
            line = new DataInputStream(socket.getInputStream()).readLine();
        }
        catch (IOException ex) {
            this.az(socket);
            return;
        }
        int int1;
        int int2;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
        }
        catch (Exception ex2) {
            this.az(socket);
            return;
        }
        if (this.bk == int2) {
            this.a_(socket, String.valueOf(int1) + " , " + int2 + " : USERID : UNIX :" + this.bl);
            return;
        }
        this.a_(socket, String.valueOf(line) + " : ERROR : INVALID-PORT");
    }
    
    public final void a1() {
        try {
            if (this.bn != null) {
                this.bn.close();
            }
            if (this.bi != null) {
                this.bi.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a0(final int bk) {
        this.bk = bk;
    }
    
    public final void a_(final Socket socket, final String s) {
        try {
            new PrintStream(socket.getOutputStream()).print(String.valueOf(s) + "\n");
            System.out.println("Ident: " + s);
        }
        catch (IOException ex) {}
        this.az(socket);
    }
    
    public final void az(final Socket socket) {
        try {
            socket.close();
        }
        catch (Exception ex) {}
    }
}
