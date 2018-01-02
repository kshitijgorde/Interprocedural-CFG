// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.discover;

import java.io.IOException;
import java.io.PrintStream;
import java.io.DataInputStream;
import java.net.Socket;

public class PseudoPing
{
    public static void main(final String[] args) {
        try {
            final Socket t = new Socket(args[0], 7);
            final DataInputStream dis = new DataInputStream(t.getInputStream());
            final PrintStream ps = new PrintStream(t.getOutputStream());
            ps.println("Hello");
            final String str = dis.readLine();
            if (str.equals("Hello")) {
                System.out.println("Alive!");
            }
            else {
                System.out.println("Dead or echo port not responding");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
