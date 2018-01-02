import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyServerThread extends Thread
{
    private Socket socket;
    
    public MyServerThread(final Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        try {
            final DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
            while (true) {
                System.out.println(dataInputStream.readUTF());
                int n = 0;
                String string = "";
                try {
                    final FileReader fileReader = new FileReader("hits.dat");
                    for (int i = fileReader.read(); i != -1; i = fileReader.read()) {
                        string += (char)i;
                        if (++n >= 1000) {}
                    }
                    fileReader.close();
                }
                catch (IOException ex2) {
                    System.out.println("io error for read images webpage");
                }
                int int1 = Integer.parseInt(string.substring(0, string.indexOf("X")));
                ++int1;
                final String string2 = "" + int1 + "X";
                try {
                    final FileWriter fileWriter = new FileWriter("hits.dat");
                    for (int j = 0; j < string2.length(); ++j) {
                        fileWriter.write(string2.charAt(j));
                    }
                    fileWriter.close();
                    System.out.println("closed");
                }
                catch (IOException ex3) {
                    System.out.println("io error for write images webpage");
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
