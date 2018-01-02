import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public class hitServer
{
    private ServerSocket serverSocket;
    
    public static void main(final String[] array) throws IOException {
        new hitServer();
    }
    
    public hitServer() {
    Label_0045:
        while (true) {
            try {
                this.serverSocket = new ServerSocket(6592);
                System.out.println("Server Is Running ...");
                break Label_0045;
            }
            catch (IOException ex) {
                System.out.println("Could not listen on port: .");
                System.exit(-1);
                break Label_0045;
            }
            break Label_0045;
            while (true) {
                try {
                    while (true) {
                        final Socket accept = this.serverSocket.accept();
                        System.out.println("Connection from : " + accept);
                        new MyServerThread(accept).start();
                    }
                }
                catch (IOException ex2) {
                    continue;
                }
                continue Label_0045;
            }
            break;
        }
    }
}
