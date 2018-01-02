// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import COM.NextBus.Predictor2Comm.StopPrediction;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class c
{
    private byte[] a;
    
    public final byte[] a() {
        if (this.a == null) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            null.a(dataOutputStream);
            dataOutputStream.close();
            this.a = byteArrayOutputStream.toByteArray();
        }
        return this.a;
    }
    
    public static String a(final String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
}
