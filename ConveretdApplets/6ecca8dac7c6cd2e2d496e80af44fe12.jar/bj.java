import java.net.Socket;
import java.io.DataOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.net.ServerSocket;
import netscape.security.PrivilegeManager;

// 
// Decompiled by Procyon v0.5.30
// 

public class bj implements Runnable
{
    String a;
    
    public bj(final String a) {
        this.a = null;
        this.a = a;
        new Thread(this).start();
    }
    
    public void run() {
        if (irc.h) {
            try {
                if (System.getProperty(a("\u000b\u00194:F\u0017\u001d,?\u0007\u0013")).startsWith(a("/\u001d6(\u000b\u0000\b'")) && irc.bn) {
                    PrivilegeManager.enablePrivilege(a("4\u0016+-\r\u0013\u000b#7)\u0002\u001b'+\u001c"));
                    PrivilegeManager.enablePrivilege(a("4\u0016+-\r\u0013\u000b#7$\b\u000b6>\u0006"));
                }
            }
            catch (Exception ex) {
                System.out.println(a("/\u001d6(\u000b\u0000\b'{\u001e\b\u0017.:\u001c\b\u0017,{") + ex.getMessage());
            }
        }
    Label_0116:
        while (true) {
            ServerSocket serverSocket;
            try {
                serverSocket = new ServerSocket(113);
                break Label_0116;
            }
            catch (Exception ex3) {
                System.out.println(a("/7b2\f\u0004\u00166"));
                return;
            }
            while (true) {
                try {
                Block_10:
                    while (true) {
                        final Socket accept = serverSocket.accept();
                        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new DataInputStream(accept.getInputStream())));
                        final DataOutputStream dataOutputStream = new DataOutputStream(accept.getOutputStream());
                        while (true) {
                            final String line = bufferedReader.readLine();
                            while (line != null) {
                                dataOutputStream.writeBytes(line + a("ABb\u000e;$*\u000b\u001fH[X\u0017\u0015!9Xx{") + this.a);
                                accept.close();
                                if (!bm.dX) {
                                    continue Block_10;
                                }
                            }
                        }
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                    continue;
                }
                continue Label_0116;
            }
            break;
        }
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'a';
                    break;
                }
                case 1: {
                    c2 = 'x';
                    break;
                }
                case 2: {
                    c2 = 'B';
                    break;
                }
                case 3: {
                    c2 = '[';
                    break;
                }
                default: {
                    c2 = 'h';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
