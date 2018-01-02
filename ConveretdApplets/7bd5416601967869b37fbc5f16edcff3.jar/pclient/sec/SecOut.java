// 
// Decompiled by Procyon v0.5.30
// 

package pclient.sec;

import java.io.IOException;
import java.util.Date;
import pclient.shd.ClientUtil;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import com.pchat.sc.ByteUtil;
import com.pchat.sc.StringUtil;
import java.util.Vector;
import java.io.OutputStream;

public class SecOut extends Thread
{
    private SecStream mainStream;
    private OutputStream outStream;
    private Vector bufferList;
    private boolean userDisconnected;
    private boolean isRunning;
    
    public SecOut(final SecStream mainStream, final OutputStream outStream) {
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
        this.mainStream.chatModel.paraConf.printer().print("Sec Out:" + StringUtil.showByte(array));
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
                System.out.println("Sec-err4563.");
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
            catch (SSLHandshakeException ex) {
                ex.printStackTrace();
                this.mainStream.errorNotify(2);
                break;
            }
            catch (SSLException ex2) {
                ex2.printStackTrace();
                this.mainStream.errorNotify(2);
                break;
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
                if (!this.userDisconnected) {
                    this.mainStream.errorNotify(0);
                }
                break;
            }
            if (this.bufferList.size() == 0) {
                ClientUtil.doze(250);
            }
        }
        System.out.println(new Date() + " out-secstream ends. " + this.mainStream.getConnTime());
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
