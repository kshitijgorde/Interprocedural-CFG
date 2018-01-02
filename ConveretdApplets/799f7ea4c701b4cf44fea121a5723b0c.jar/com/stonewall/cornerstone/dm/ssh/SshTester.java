// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.ssh;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SshTester
{
    private static final String USAGE = "-ip \t server IP address or hostname;   \n-pt \t server port number (default 23); \n-id \t login ID;                        \n-pw \t password;                        \n-lp \t login prompt;                    \n-pp \t password prompt;                 \n-cp \t command prompt;                  \n-h  \t print this usage;                \n-?  \t same as -h                       \n";
    
    public static void main(final String[] args) {
        String host = "192.168.1.13";
        int port = 22;
        String id = "test";
        String pw = "cyberwerx";
        String lp = "";
        String pp = "";
        String cp = "";
        final int ver = 2;
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
        if (ver == 1) {
            final SSH1 st = new SSH1();
            try {
                System.out.println("Connecting to server " + host + " at port " + port + "...");
                st.connect(host, port);
                System.out.println("Connected. Logging into server...");
                if (lp != "" && pp != "") {
                    st.login(lp, pp, id, pw);
                }
                else {
                    st.login(id, pw);
                }
                System.out.println("Logged in. Entering command mode...");
                st.setPrompt(cp);
                st.setLocalEcho(false);
                String cmd = "ls";
                for (BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); !cmd.equals("exit") && !cmd.equals("quit"); cmd = in.readLine()) {
                    System.out.print(st.send(cmd));
                }
                System.out.println("Closing connection... ");
                st.disconnect();
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
        if (ver == 2) {
            final SshV2 obj = new SshV2();
            obj.vars(host, port, id, pw);
            SshV2.main(null);
        }
    }
}
