// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import java.io.OutputStream;
import java.io.InputStream;
import anon.AnonChannel;
import anon.AnonServiceFactory;
import java.net.InetAddress;

class XMLRPCTestClient
{
    public static void main(final String[] array) {
        try {
            final AnonChannel channel = AnonServiceFactory.create(InetAddress.getLocalHost().getHostAddress(), 8889).createChannel(0);
            final InputStream inputStream = channel.getInputStream();
            final OutputStream outputStream = channel.getOutputStream();
            outputStream.write("GET HTTP://anon.inf.tu-dresden.de/index.html HTTP/1.1\n\n".getBytes());
            outputStream.flush();
            final byte[] array2 = new byte[100];
            int read;
            while ((read = inputStream.read(array2)) > 0) {
                System.out.print(new String(array2, 0, read));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
