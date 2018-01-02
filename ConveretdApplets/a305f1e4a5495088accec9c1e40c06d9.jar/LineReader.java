import java.io.IOException;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class LineReader extends Thread
{
    DataInputStream d;
    Wiper w;
    Thread thread;
    
    public LineReader(final DataInputStream d, final Wiper w) {
        super("line reader");
        this.setPriority(1);
        this.d = d;
        this.w = w;
        this.start();
    }
    
    public void run() {
        while (true) {
            String line;
            try {
                line = this.d.readLine();
            }
            catch (IOException ex) {
                return;
            }
            if (line != null) {
                synchronized (this.w) {
                    this.w.addLine(line);
                    // monitorexit(this.w)
                    continue;
                }
                break;
            }
            break;
        }
    }
}
