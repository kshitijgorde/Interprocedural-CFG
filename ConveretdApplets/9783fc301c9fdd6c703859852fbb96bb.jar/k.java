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
    public ServerSocket bo;
    public boolean bn;
    public String bm;
    public int bl;
    public boolean bk;
    public DataInputStream bj;
    
    public k(final int n, final boolean bk, final String bm) {
        super("Identd Thread");
        this.bn = false;
        this.bm = "JPilotChat";
        this.bk = true;
        this.bm = bm;
        this.bk = bk;
        if (bk) {
            try {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
            }
            catch (Exception ex) {
                System.out.println("Failed to obtain permissionn");
            }
        }
        try {
            this.bo = new ServerSocket(n);
            this.bn = true;
        }
        catch (Exception ex2) {
            this.bn = false;
        }
    }
    
    public final void run() {
        if (this.bk) {
            try {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
            }
            catch (Exception ex) {
                System.out.println("No permission for socket.");
            }
        }
        if (this.bn) {
            try {
                final Socket accept = this.bo.accept();
                final DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
                this.a_(accept);
            }
            catch (IOException ex2) {}
        }
        this.az();
    }
    
    public final void a_(final Socket socket) {
        String line;
        try {
            line = new DataInputStream(socket.getInputStream()).readLine();
        }
        catch (IOException ex) {
            this.aw(socket);
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
            this.aw(socket);
            return;
        }
        if (this.bl == int2) {
            this.ax(socket, String.valueOf(int1) + " , " + int2 + " : USERID : UNIX :" + this.bm);
            return;
        }
        this.ax(socket, String.valueOf(line) + " : ERROR : INVALID-PORT");
    }
    
    public final void az() {
        try {
            if (this.bo != null) {
                this.bo.close();
            }
            if (this.bj != null) {
                this.bj.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void ay(final int bl) {
        this.bl = bl;
    }
    
    public final void ax(final Socket socket, final String s) {
        try {
            new PrintStream(socket.getOutputStream()).print(String.valueOf(s) + "\n");
            System.out.println("Ident: " + s);
        }
        catch (IOException ex) {}
        this.aw(socket);
    }
    
    public final void aw(final Socket socket) {
        try {
            socket.close();
        }
        catch (Exception ex) {}
    }
}
