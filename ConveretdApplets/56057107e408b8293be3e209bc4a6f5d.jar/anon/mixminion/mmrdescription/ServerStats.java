// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.mmrdescription;

import java.io.IOException;
import HTTPClient.HTTPResponse;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;
import logging.LogHolder;
import logging.LogType;
import HTTPClient.HTTPConnection;
import java.util.Vector;

public class ServerStats
{
    private String m_Server;
    private int m_Port;
    
    public ServerStats() {
        this.m_Server = "privacy.outel.org";
        this.m_Port = 80;
    }
    
    public Vector getWhoIsDown() throws IOException {
        final Vector<String> vector = new Vector<String>();
        String text;
        try {
            final HTTPResponse get = new HTTPConnection(this.m_Server, this.m_Port).Get("/minion/nlist.txt");
            if (get.getStatusCode() != 200) {
                LogHolder.log(7, LogType.MISC, "There was a problem with fetching the Statistics of the Mixminion-network. ");
                return vector;
            }
            text = get.getText();
        }
        catch (Throwable t) {
            return vector;
        }
        final LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(text));
        lineNumberReader.readLine();
        lineNumberReader.readLine();
        lineNumberReader.readLine();
        lineNumberReader.readLine();
        lineNumberReader.readLine();
        for (String s = lineNumberReader.readLine(); s.length() > 5; s = lineNumberReader.readLine()) {
            final char char1 = s.charAt(26);
            if (char1 == ' ' || char1 == '.' || char1 == '_' || char1 == '-') {
                vector.addElement(s.substring(0, 15).trim());
            }
        }
        return vector;
    }
}
