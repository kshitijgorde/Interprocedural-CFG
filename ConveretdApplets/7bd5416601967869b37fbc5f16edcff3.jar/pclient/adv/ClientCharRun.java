// 
// Decompiled by Procyon v0.5.30
// 

package pclient.adv;

public class ClientCharRun implements Runnable
{
    private ClientCharacter parentChar;
    private String runCommand;
    
    public ClientCharRun(final ClientCharacter parentChar, final String runCommand) {
        this.parentChar = parentChar;
        this.runCommand = runCommand;
    }
    
    public void run() {
        if ("ret".equals(this.runCommand)) {
            this.parentChar.runGet();
        }
        else if ("sto".equals(this.runCommand)) {
            this.parentChar.runStore();
        }
    }
}
