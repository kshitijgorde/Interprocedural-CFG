// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.console.util;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.jms.Session;
import org.apache.activemq.broker.util.CommandMessageListener;

public final class SimpleConsole
{
    public static void main(final String[] args) {
        final CommandMessageListener listener = new CommandMessageListener(null);
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String line = reader.readLine();
                if (line == null || "quit".equalsIgnoreCase(line)) {
                    break;
                }
                line = line.trim();
                if (line.length() == 0) {
                    continue;
                }
                System.out.println(listener.processCommandText(line));
            }
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
}
