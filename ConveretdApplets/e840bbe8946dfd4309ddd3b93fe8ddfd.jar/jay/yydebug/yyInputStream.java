// 
// Decompiled by Procyon v0.5.30
// 

package jay.yydebug;

import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.io.InputStream;

public class yyInputStream extends InputStream implements KeyListener
{
    protected final StringBuffer line;
    protected ArrayList queue;
    
    public yyInputStream() {
        this.line = new StringBuffer();
        this.queue = new ArrayList();
    }
    
    public synchronized int available() throws IOException {
        if (this.queue == null) {
            throw new IOException("closed");
        }
        return this.queue.isEmpty() ? 0 : ((byte[])this.queue.get(0)).length;
    }
    
    public synchronized void close() throws IOException {
        if (this.queue == null) {
            throw new IOException("closed");
        }
        this.queue = null;
    }
    
    public synchronized int read() throws IOException {
        if (this.queue == null) {
            throw new IOException("closed");
        }
        while (this.queue.isEmpty()) {
            try {
                this.wait();
                continue;
            }
            catch (InterruptedException ex) {
                throw new IOException("interrupted");
            }
            break;
        }
        final byte[] array = this.queue.get(0);
        switch (array.length) {
            case 0: {
                return -1;
            }
            case 1: {
                this.queue.remove(0);
                break;
            }
            default: {
                final byte[] array2 = new byte[array.length - 1];
                System.arraycopy(array, 1, array2, 0, array2.length);
                this.queue.set(0, array2);
                this.notifyAll();
                break;
            }
        }
        return array[0] & 0xFF;
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
        if (this.queue == null) {
            throw new IOException("closed");
        }
        while (this.queue.isEmpty()) {
            try {
                this.wait();
                continue;
            }
            catch (InterruptedException ex) {
                throw new IOException("interrupted");
            }
            break;
        }
        final byte[] array2 = this.queue.get(0);
        if (array2.length == 0) {
            return -1;
        }
        if (array2.length <= n2) {
            System.arraycopy(array2, 0, array, n, array2.length);
            this.queue.remove(0);
            return array2.length;
        }
        System.arraycopy(array2, 0, array, n, n2);
        final byte[] array3 = new byte[array2.length - n2];
        System.arraycopy(array2, n2, array3, 0, array3.length);
        this.queue.set(0, array3);
        this.notifyAll();
        return n2;
    }
    
    public long skip(final long n) {
        return 0L;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final TextArea textArea = (TextArea)keyEvent.getComponent();
        final int length = textArea.getText().length();
        textArea.select(length, length);
        textArea.setCaretPosition(length);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final TextArea textArea = (TextArea)keyEvent.getComponent();
        final char keyChar = keyEvent.getKeyChar();
        switch (keyChar) {
            case 10:
            case 13: {
                this.line.append('\n');
                break;
            }
            case 4: {
                textArea.append("^D");
                textArea.setCaretPosition(textArea.getText().length());
                break;
            }
            case 8: {
                final int length = this.line.length();
                if (length > 0) {
                    this.line.setLength(length - 1);
                }
                return;
            }
            case 21: {
                this.line.setLength(0);
                textArea.append("^U\n");
                textArea.setCaretPosition(textArea.getText().length());
                return;
            }
            default: {
                this.line.append(keyChar);
                return;
            }
        }
        synchronized (this) {
            this.queue.add(this.line.toString().getBytes());
            this.notifyAll();
        }
        this.line.setLength(0);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
}
