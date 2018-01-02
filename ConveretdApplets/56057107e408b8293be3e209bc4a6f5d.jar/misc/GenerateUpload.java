// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;

public class GenerateUpload
{
    private static final int MAX_WAIT_BETWEEN_CHANNELS = 100;
    private static final int MAX_CHANNELS = 1;
    private static final int MAX_SINGLE_CALL_UPLOAD = 18000;
    public int MAX_WAIT_BETWEEN_SINGLE_CALL_UPLOAD;
    static int MIN_UPLOAD;
    static int MAX_UPLOAD;
    private static byte[] randomData;
    static Random rand;
    static int success;
    static int failed;
    
    public GenerateUpload() {
        this.MAX_WAIT_BETWEEN_SINGLE_CALL_UPLOAD = 10;
    }
    
    static synchronized void incFailed() {
        ++GenerateUpload.failed;
    }
    
    static synchronized void incSuccess() {
        ++GenerateUpload.success;
    }
    
    public static void main(final String[] array) throws UnknownHostException, IOException {
        GenerateUpload.rand = new Random();
        GenerateUpload.randomData = new byte[256000];
        for (int i = 0; i < GenerateUpload.randomData.length; ++i) {
            GenerateUpload.randomData[i] = (byte)i;
        }
        new GenerateUpload().start();
    }
    
    void start() {
        final Upload[] array = { null };
        for (int i = 0; i < 1; ++i) {
            array[i] = new Upload(GenerateUpload.MIN_UPLOAD + GenerateUpload.rand.nextInt(GenerateUpload.MAX_UPLOAD - GenerateUpload.MIN_UPLOAD), i);
            try {
                Thread.sleep(GenerateUpload.rand.nextInt(100));
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        for (int j = 0; j < 1; ++j) {
            array[j].join();
        }
        System.out.println(GenerateUpload.success + " ok / " + GenerateUpload.failed + " failed");
    }
    
    static {
        GenerateUpload.MIN_UPLOAD = 59700000;
        GenerateUpload.MAX_UPLOAD = 170000000;
        GenerateUpload.success = 0;
        GenerateUpload.failed = 0;
    }
    
    class Upload implements Runnable
    {
        int maxLen;
        int id;
        Thread t;
        
        public Upload(final int maxLen, final int id) {
            this.id = id;
            this.maxLen = maxLen;
            (this.t = new Thread(this)).start();
        }
        
        public void join() {
            try {
                this.t.join();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        public void run() {
            int i = 0;
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 4001);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                final OutputStream outputStream = socket.getOutputStream();
                final Download download = new Download(socket.getInputStream(), this.maxLen, this.id);
                outputStream.write("CONNECT 127.0.0.1:7 HTTP/1.0\n\r\n\r".getBytes());
                int n = 0;
                while (i < this.maxLen) {
                    final int min = Math.min(Math.min(GenerateUpload.rand.nextInt(18000), GenerateUpload.randomData.length - n), this.maxLen - i);
                    outputStream.write(GenerateUpload.randomData, n, min);
                    n = (n + min) % GenerateUpload.randomData.length;
                    i += min;
                    if (i % 1000000 == 0) {
                        System.out.println("Sent: " + i / 1000000 + " MBytes");
                    }
                    try {
                        Thread.sleep(GenerateUpload.rand.nextInt(GenerateUpload.this.MAX_WAIT_BETWEEN_SINGLE_CALL_UPLOAD));
                    }
                    catch (InterruptedException ex2) {
                        ex2.printStackTrace();
                    }
                }
                System.out.println("Upload: " + this.id + " Sent: " + i + " Bytes");
                download.join();
                socket.close();
            }
            catch (Throwable t) {
                System.out.println("Exception in Upload -- l=" + i);
                t.printStackTrace();
            }
        }
        
        class Download implements Runnable
        {
            InputStream in;
            int maxLen;
            int id;
            Thread t;
            
            Download(final InputStream in, final int maxLen, final int id) {
                this.id = id;
                this.in = in;
                this.maxLen = maxLen;
                (this.t = new Thread(this)).start();
            }
            
            public void join() {
                try {
                    this.t.join();
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
            public void run() {
                try {
                    final byte[] array = new byte[10000];
                    int i = 0;
                    long n = 1000000L;
                    int n2 = 0;
                    this.in.read(array, 0, 39);
                    this.in.read(array, 0, 1);
                    while (i < this.maxLen) {
                        final int read = this.in.read(array, 0, Math.min(Math.min(array.length, GenerateUpload.randomData.length - n2), this.maxLen - i));
                        for (int j = 0; j < read; ++j) {
                            if (array[j] != GenerateUpload.randomData[n2 + j]) {
                                System.out.println("Download: " + this.id + " -- Read error! -- Position: " + i + " -- Read: " + (array[j] & 0xFF) + " -- Expected: " + (GenerateUpload.randomData[n2 + j] & 0xFF));
                                GenerateUpload.incFailed();
                                return;
                            }
                        }
                        i += read;
                        n2 = (n2 + read) % GenerateUpload.randomData.length;
                        if (i >= n) {
                            System.out.println("Read: " + i / 1000000.0 + " MBytes");
                            n += 1000000L;
                        }
                    }
                    System.out.println("Download: " + this.id + " -- Succesfully received: " + i + " Bytes");
                    GenerateUpload.incSuccess();
                }
                catch (Exception ex) {
                    System.out.println("Read failed!");
                }
            }
        }
    }
}
