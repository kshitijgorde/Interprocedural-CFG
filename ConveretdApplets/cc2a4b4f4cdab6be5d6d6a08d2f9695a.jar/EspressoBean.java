import java.io.UnsupportedEncodingException;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.net.URL;
import java.net.URLDecoder;

// 
// Decompiled by Procyon v0.5.30
// 

public class EspressoBean
{
    private String strVendorGuid;
    private String strSecureId;
    private String strDrmServerAddress;
    private boolean m_bRetValUseCodec;
    private int[] m_BaseKey;
    
    public EspressoBean() {
        this.strVendorGuid = "D64857B8A807442DAFC57EAD923FFF62";
        this.strSecureId = "{8369755D-E06B-4dff-BFAA-1425E6EA4DA0}";
        this.strDrmServerAddress = "";
        this.m_bRetValUseCodec = false;
        this.m_BaseKey = new int[] { 523046488, 523046489, 523046490, 523046491, 1200170579, 1200170580, 1200170581, 1200170582, 22931881, 22931882, 22931883, 22931884, 1769166245, 1769166246, 1769166247, 1769166248, 1569777609, 1569777610, 1569777611, 1569777612, -485542403, -485542402, -485542401, 485542400, 97691617, 97691618, 97691619, 97691620, -2000163443, -2000163442, -2000163441, -2000163440 };
    }
    
    public void setDrmServerAddress(final String strDrmServerAddress) {
        this.strDrmServerAddress = strDrmServerAddress;
    }
    
    public void SetRetValUseCodec(final boolean bRetValUseCodec) {
        this.m_bRetValUseCodec = bRetValUseCodec;
    }
    
    public void SetDrmKey(String strVendorGuid) {
        if (strVendorGuid.contains("{")) {
            strVendorGuid = strVendorGuid.replace("{", "");
        }
        if (strVendorGuid.contains("}")) {
            strVendorGuid = strVendorGuid.replace("}", "");
        }
        if (strVendorGuid.contains("-")) {
            strVendorGuid = strVendorGuid.replace("-", "");
        }
        strVendorGuid = strVendorGuid.toUpperCase();
        this.strVendorGuid = strVendorGuid;
    }
    
    public String getTargetURL(final String s, final boolean b) {
        try {
            this.strDrmServerAddress = URLDecoder.decode(this.strDrmServerAddress);
            final URLConnection openConnection = new URL(this.strDrmServerAddress).openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setAllowUserInteraction(false);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openConnection.getOutputStream());
            if (b) {
                bufferedOutputStream.write(this.Codec(this.getMultiByteCharByUnicode(s)));
            }
            else {
                bufferedOutputStream.write(this.getMultiByteCharByUnicode(s));
            }
            bufferedOutputStream.close();
            String string = "";
            final InputStream inputStream = openConnection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            if (this.m_bRetValUseCodec) {
                int n;
                byte[] array;
                int read;
                for (n = 0, array = new byte[1024]; (read = inputStream.read(array, n, 1)) != -1; n += read) {}
                string = new String(new String(this.Codec(array, n), 0, n, "UTF-8").getBytes());
            }
            else {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    string += line;
                }
            }
            bufferedReader.close();
            return string;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public byte[] getMultiByteCharByUnicode(final String s) {
        byte[] bytes;
        try {
            bytes = s.getBytes("KSC5601");
        }
        catch (UnsupportedEncodingException ex) {
            bytes = null;
        }
        return bytes;
    }
    
    public byte[] Codec(final byte[] array, final int n) {
        if (array == null) {
            return null;
        }
        final byte[] array2 = new byte[n];
        final int[] array3 = new int[64];
        for (int i = 0; i < 64; ++i) {
            int n2;
            if (i < 32) {
                n2 = i;
            }
            else {
                n2 = i - 32;
            }
            if (n2 < this.strVendorGuid.length()) {
                this.strVendorGuid.charAt(n2);
            }
            final char char1 = this.strVendorGuid.charAt(n2);
            if (char1 >= '0' && char1 <= '9') {
                array3[i] = char1 - '0';
            }
            else if (char1 >= 'A' && char1 <= 'Z') {
                array3[i] = char1 - 'A' + '\n';
            }
            else {
                array3[i] = 0;
            }
            if (i >= 32) {
                final int[] array4 = array3;
                final int n3 = i;
                array4[n3] += 16;
            }
        }
        final int[] array5 = new int[32];
        for (int j = 0; j < 32; ++j) {
            if (j < this.strSecureId.length()) {
                array5[j] = this.strSecureId.charAt(j);
            }
            else {
                array5[j] = 0;
            }
        }
        int n4 = 0;
        int n5 = 0;
        for (int k = 0; k < n; ++k) {
            int n6 = array[k];
            for (int l = n4; l < 64; ++l) {
                n6 ^= this.m_BaseKey[array3[l]];
            }
            if (n4 >= 64) {
                n4 = 0;
            }
            else {
                ++n4;
            }
            for (int n7 = n5; n7 < 32; ++n7) {
                n6 ^= array5[n7];
            }
            if (n5 >= 32) {
                n5 = 0;
            }
            else {
                ++n5;
            }
            array2[k] = (byte)n6;
        }
        return array2;
    }
    
    public byte[] Codec(final byte[] array) {
        if (array == null) {
            return null;
        }
        final int length = array.length;
        final byte[] array2 = new byte[length];
        final int[] array3 = new int[64];
        for (int i = 0; i < 64; ++i) {
            int n;
            if (i < 32) {
                n = i;
            }
            else {
                n = i - 32;
            }
            if (n < this.strVendorGuid.length()) {
                this.strVendorGuid.charAt(n);
            }
            final char char1 = this.strVendorGuid.charAt(n);
            if (char1 >= '0' && char1 <= '9') {
                array3[i] = char1 - '0';
            }
            else if (char1 >= 'A' && char1 <= 'Z') {
                array3[i] = char1 - 'A' + '\n';
            }
            else {
                array3[i] = 0;
            }
            if (i >= 32) {
                final int[] array4 = array3;
                final int n2 = i;
                array4[n2] += 16;
            }
        }
        final int[] array5 = new int[32];
        for (int j = 0; j < 32; ++j) {
            if (j < this.strSecureId.length()) {
                array5[j] = this.strSecureId.charAt(j);
            }
            else {
                array5[j] = 0;
            }
        }
        int n3 = 0;
        int n4 = 0;
        for (int k = 0; k < length; ++k) {
            int n5 = array[k];
            for (int l = n3; l < 64; ++l) {
                n5 ^= this.m_BaseKey[array3[l]];
            }
            if (n3 >= 64) {
                n3 = 0;
            }
            else {
                ++n3;
            }
            for (int n6 = n4; n6 < 32; ++n6) {
                n5 ^= array5[n6];
            }
            if (n4 >= 32) {
                n4 = 0;
            }
            else {
                ++n4;
            }
            array2[k] = (byte)n5;
        }
        return array2;
    }
}
