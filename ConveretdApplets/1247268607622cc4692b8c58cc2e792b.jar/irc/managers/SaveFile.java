// 
// Decompiled by Procyon v0.5.30
// 

package irc.managers;

import java.io.IOException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;

class SaveFile extends Thread
{
    String FilePath;
    String message;
    
    public SaveFile() {
        this.setPriority(1);
    }
    
    @Override
    public void run() {
        try {
            final PrintWriter printWriter = new PrintWriter(new FileWriter(this.FilePath), true);
            printWriter.println(this.message);
            printWriter.close();
        }
        catch (IOException ex) {}
    }
    
    public void setFilePath(final String filePath) {
        this.FilePath = filePath;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
}
