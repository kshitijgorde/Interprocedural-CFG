// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.core;

import java.net.HttpURLConnection;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import soht.client.java.configuration.ConfigurationManager;

public class BaseProxy extends Thread
{
    protected ConfigurationManager configurationManager;
    protected long connectionId;
    protected Socket socket;
    
    public BaseProxy(final String name, final ConfigurationManager configurationManager, final long connectionId, final Socket socket) {
        super(name);
        this.configurationManager = configurationManager;
        this.connectionId = connectionId;
        this.socket = socket;
    }
    
    protected void closeServer() {
        try {
            final HttpURLConnection urlConnection = this.configurationManager.getURLConnection();
            final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            out.write("action=close");
            out.write("&");
            out.write("id=" + this.connectionId);
            out.flush();
            out.close();
            urlConnection.connect();
            urlConnection.getInputStream();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected String encode(final byte[] data, final int length) {
        final StringBuffer encodedData = new StringBuffer();
        for (int index = 0; index < length; ++index) {
            if (this.needsEncoding(data[index])) {
                encodedData.append(this.encode(data[index]));
            }
            else {
                encodedData.append((char)data[index]);
            }
        }
        return encodedData.toString();
    }
    
    private boolean needsEncoding(final byte data) {
        boolean result = true;
        if (data >= 33 && data <= 126) {
            result = (data == 37 || data == 63 || data == 64 || data == 38 || data == 61 || data == 43 || data == 58 || data == 35);
        }
        return result;
    }
    
    private String encode(final byte data) {
        final StringBuffer result = new StringBuffer(3);
        result.append('#');
        final int intData = data & 0xFF;
        String hexString = Integer.toHexString(intData);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        result.append(hexString);
        return result.toString();
    }
}
