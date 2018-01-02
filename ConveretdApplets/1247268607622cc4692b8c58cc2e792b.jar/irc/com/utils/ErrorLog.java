// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import irc.EIRC;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.InetAddress;
import java.io.File;

public class ErrorLog extends Thread
{
    File errlog;
    String nick;
    
    public ErrorLog(final String nick, final File errlog) {
        this.nick = nick;
        this.errlog = errlog;
        this.setPriority(1);
    }
    
    @Override
    public void run() {
        try {
            final Socket socket = new Socket(InetAddress.getByName("java.chat-land.org").getHostAddress(), 1305);
            final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            printWriter.println(this.nick + "_" + "m_V 5.34");
            printWriter.flush();
            printWriter.println("m_V 5.34  java.version : " + System.getProperty("java.version") + " os.name : " + System.getProperty("os.name") + " resolution : " + EIRC.resolution);
            printWriter.flush();
            int n = 0;
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(this.errlog));
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
            final byte[] array = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                n += read;
                bufferedOutputStream.write(array, 0, read);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }
        catch (Exception ex) {}
        System.exit(0);
    }
}
