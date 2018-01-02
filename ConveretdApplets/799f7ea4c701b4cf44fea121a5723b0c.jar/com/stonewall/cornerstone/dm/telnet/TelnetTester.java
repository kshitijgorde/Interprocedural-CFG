// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.telnet;

import java.io.IOException;
import java.util.Collections;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TelnetTester
{
    private static final String USAGE = "-ip \t server IP address or hostname;   \n-pt \t server port number (default 23); \n-id \t login ID;                        \n-pw \t password;                        \n-lp \t login prompt;                    \n-pp \t password prompt;                 \n-cp \t command prompt;                  \n-h  \t print this usage;                \n-?  \t same as -h                       \n";
    
    public static void main(final String[] args) {
        String host = "localhost";
        int port = 23;
        String id = "";
        String pw = "";
        String lp = "";
        String pp = "";
        String cp = ">";
        int i = 0;
        while (i < args.length) {
            if (args[i].equals("-h") || args[i].equals("-?")) {
                System.out.println("-ip \t server IP address or hostname;   \n-pt \t server port number (default 23); \n-id \t login ID;                        \n-pw \t password;                        \n-lp \t login prompt;                    \n-pp \t password prompt;                 \n-cp \t command prompt;                  \n-h  \t print this usage;                \n-?  \t same as -h                       \n");
                ++i;
                System.exit(1);
            }
            else if (args[i].equals("-ip")) {
                ++i;
                host = args[i];
                ++i;
            }
            else if (args[i].equals("-pt")) {
                ++i;
                port = Integer.parseInt(args[i]);
                ++i;
            }
            else if (args[i].equals("-id")) {
                ++i;
                id = args[i];
                ++i;
            }
            else if (args[i].equals("-pw")) {
                ++i;
                pw = args[i];
                ++i;
            }
            else if (args[i].equals("-lp")) {
                ++i;
                lp = args[i];
                ++i;
            }
            else if (args[i].equals("-pp")) {
                ++i;
                pp = args[i];
                ++i;
            }
            else if (args[i].equals("-cp")) {
                ++i;
                cp = args[i];
                ++i;
            }
            else {
                System.out.println("Invalid Option.");
                System.out.println("-ip \t server IP address or hostname;   \n-pt \t server port number (default 23); \n-id \t login ID;                        \n-pw \t password;                        \n-lp \t login prompt;                    \n-pp \t password prompt;                 \n-cp \t command prompt;                  \n-h  \t print this usage;                \n-?  \t same as -h                       \n");
                ++i;
                System.exit(1);
            }
        }
        final Telnet tt = new Telnet();
        try {
            System.out.println("Connecting to server " + host + " at port " + port + "...");
            tt.connect(host, port);
            System.out.println("Connected. Logging into server...");
            if (lp != "" && pp != "") {
                tt.login(lp, pp, id, pw);
            }
            else {
                tt.login(id, pw);
            }
            System.out.println("Logged in. Entering command mode...");
            tt.setPrompt(cp);
            tt.setLocalEcho(false);
            String cmd = "";
            for (BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); !cmd.equalsIgnoreCase("exit") && !cmd.equalsIgnoreCase("quit"); cmd = in.readLine()) {
                if (cmd.equalsIgnoreCase("enable")) {
                    System.out.print(tt.executeCommand(cmd, Collections.singletonList("")));
                    tt.setPrompt("#");
                }
                else {
                    tt.send(cmd);
                    System.out.print(cmd);
                }
            }
            System.out.println("Closing connection... ");
            tt.disconnect();
            System.out.println("Connection closed. Good Bye.");
        }
        catch (IOException ioex) {
            System.err.println("Error connecting to server " + host + " at port " + port + ".");
            System.err.println(ioex.toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
