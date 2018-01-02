// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

import java.io.IOException;

public class SshWrapperExample
{
    public static void main(final String[] args) {
        final SshWrapper ssh = new SshWrapper();
        try {
            final byte[] buffer = new byte[256];
            ssh.connect(args[0], 22);
            ssh.login("marcus", "xxxxx");
            ssh.setPrompt("marcus");
            System.out.println("after login");
            ssh.send("ls -l");
            ssh.read(buffer);
            System.out.println(new String(buffer));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
