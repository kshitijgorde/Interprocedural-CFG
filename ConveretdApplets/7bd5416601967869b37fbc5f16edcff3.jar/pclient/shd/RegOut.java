// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.io.IOException;
import java.util.Date;
import com.pchat.sc.ByteUtil;
import java.util.Vector;
import java.io.OutputStream;

public class RegOut extends Thread
{
    private RegStream mainStream;
    private OutputStream outStream;
    private Vector bufferList;
    private boolean userDisconnected;
    private boolean isRunning;
    
    public RegOut(final RegStream mainStream, final OutputStream outStream) {
        this.userDisconnected = false;
        this.isRunning = true;
        this.bufferList = new Vector();
        this.mainStream = mainStream;
        this.outStream = outStream;
        this.userDisconnected = false;
        this.isRunning = true;
    }
    
    public void send(final byte[] array) {
        if (this.userDisconnected) {
            return;
        }
        final byte[] duplicate = ByteUtil.duplicate(array);
        synchronized (this.bufferList) {
            this.bufferList.addElement(duplicate);
        }
    }
    
    public void stopIt() {
        this.isRunning = false;
    }
    
    public void disconnect() {
        this.userDisconnected = true;
        while (this.bufferList.size() > 0) {
            try {
                this.writeOut();
                continue;
            }
            catch (Exception ex) {
                System.out.println("Net-err4529.");
                ex.printStackTrace();
            }
            break;
        }
    }
    
    public void run() {
        while (!this.userDisconnected && this.isRunning) {
            try {
                this.writeOut();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (!this.userDisconnected) {
                    this.mainStream.errorNotify();
                }
                break;
            }
            if (this.bufferList.size() == 0) {
                ClientUtil.doze(200);
            }
        }
        System.out.println(new Date() + " out-stream ends. " + this.mainStream.getConnTime());
    }
    
    private void writeOut() throws IOException {
        byte[] array = null;
        synchronized (this.bufferList) {
            if (this.bufferList.size() > 0) {
                array = this.bufferList.firstElement();
                this.bufferList.removeElementAt(0);
            }
            if (array == null) {
                return;
            }
            if (!false) {
                this.outStream.write(array);
            }
            else {
                for (int i = 0; i < array.length; ++i) {
                    ClientUtil.doze(50);
                    this.outStream.write(array[i]);
                }
            }
        }
    }
}
