import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class ReadLocalMachineInformation
{
    public static String getMACAddress() {
        Process exec = null;
        String parse = null;
        if (System.getProperty("os.name", "").startsWith("Windows")) {
            try {
                exec = Runtime.getRuntime().exec(new String[] { "ipconfig", "/all" }, null);
            }
            catch (Exception ex) {}
        }
        if (exec != null) {
            try {
                String line;
                while ((line = new BufferedReader(new InputStreamReader(exec.getInputStream()), 128).readLine()) != null) {
                    parse = parse(line);
                    if (parse != null) {
                        return parse;
                    }
                }
            }
            catch (IOException ex2) {}
        }
        return parse;
    }
    
    public static String getMachineName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    static String parse(final String s) {
        if (s.indexOf("Phy") != -1 && s.indexOf(":") != -1) {
            final int index = s.indexOf(32, s.indexOf(":"));
            if (index > -2) {
                System.out.println("Line !" + index + s.substring(s.indexOf(":")));
                return s.substring(s.indexOf(":") + 1);
            }
        }
        return null;
    }
}
