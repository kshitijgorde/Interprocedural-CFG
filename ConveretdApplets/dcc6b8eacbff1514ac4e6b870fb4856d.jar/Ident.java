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

public class Ident extends Thread
{
    public ServerSocket bk;
    public boolean bj;
    public String bi;
    public int bh;
    public boolean bg;
    public DataInputStream bf;
    
    public Ident(final int n, final boolean bg, final String bi) {
        super("Identd Thread");
        this.bj = false;
        this.bi = "JPilotChat";
        this.bg = true;
        this.bi = bi;
        this.bg = bg;
        if (bg) {
            try {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
            }
            catch (Exception ex) {
                System.out.println("Failed to obtain permissionn");
            }
        }
        try {
            this.bk = new ServerSocket(n);
            this.bj = true;
        }
        catch (Exception ex2) {
            this.bj = false;
        }
    }
    
    public final void run() {
        if (this.bg) {
            try {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
            }
            catch (Exception ex) {
                System.out.println("No permission for socket.");
            }
        }
        if (this.bj) {
            try {
                final Socket accept = this.bk.accept();
                final DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
                this.a1(accept);
            }
            catch (IOException ex2) {}
        }
        this.a0();
    }
    
    public final void a1(final Socket socket) {
        String line;
        try {
            line = new DataInputStream(socket.getInputStream()).readLine();
        }
        catch (IOException ex) {
            this.ay(socket);
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
            this.ay(socket);
            return;
        }
        if (this.bh == int2) {
            this.az(socket, String.valueOf(int1) + " , " + int2 + " : USERID : UNIX :" + this.bi);
            return;
        }
        this.az(socket, String.valueOf(line) + " : ERROR : INVALID-PORT");
    }
    
    public final void a0() {
        try {
            if (this.bk != null) {
                this.bk.close();
            }
            if (this.bf != null) {
                this.bf.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a_(final int bh) {
        this.bh = bh;
    }
    
    public final void az(final Socket socket, final String s) {
        try {
            new PrintStream(socket.getOutputStream()).print(String.valueOf(s) + "\n");
            System.out.println("Ident: " + s);
        }
        catch (IOException ex) {}
        this.ay(socket);
    }
    
    public final void ay(final Socket socket) {
        try {
            socket.close();
        }
        catch (Exception ex) {}
    }
}
