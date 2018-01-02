// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.hw.client.util.a;

final class ab implements dr
{
    public final cU a(final aJ aj) {
        if (aj.c().equalsIgnoreCase("SOCKS5")) {
            a.d("Enter username and password for SOCKS server on host " + aj.a());
            a.d("Authentication Method: username/password");
        }
        else {
            a.d("Enter username and password for realm `" + aj.d() + "' on host " + aj.a() + ":" + aj.b());
            a.d("Authentication Scheme: " + aj.c());
        }
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Username: ");
        System.out.flush();
        String line;
        try {
            line = bufferedReader.readLine();
        }
        catch (IOException ex) {
            return null;
        }
        if (line == null || line.length() == 0) {
            return null;
        }
        a(false);
        System.out.print("Password: ");
        System.out.flush();
        String line2;
        try {
            line2 = bufferedReader.readLine();
        }
        catch (IOException ex2) {
            return null;
        }
        a(true);
        if (line2 == null) {
            return null;
        }
        return new cU(line, line2);
    }
    
    private static void a(final boolean b) {
        final String property = System.getProperty("os.name");
        String[] array = null;
        if (property.equalsIgnoreCase("Windows 95") || property.equalsIgnoreCase("Windows NT")) {
            array = new String[] { "echo", b ? "on" : "off" };
        }
        else if (!property.equalsIgnoreCase("Windows") && !property.equalsIgnoreCase("16-bit Windows") && !property.equalsIgnoreCase("OS/2") && !property.equalsIgnoreCase("Mac OS") && !property.equalsIgnoreCase("MacOS")) {
            if (property.equalsIgnoreCase("OpenVMS") || property.equalsIgnoreCase("VMS")) {
                array = new String[] { "SET TERMINAL " + (b ? "/ECHO" : "/NOECHO") };
            }
            else {
                array = new String[] { "/bin/sh", "-c", "stty " + (b ? "echo" : "-echo") + " < /dev/tty" };
            }
        }
        if (array != null) {
            try {
                Runtime.getRuntime().exec(array).waitFor();
            }
            catch (Exception ex) {}
        }
    }
}
