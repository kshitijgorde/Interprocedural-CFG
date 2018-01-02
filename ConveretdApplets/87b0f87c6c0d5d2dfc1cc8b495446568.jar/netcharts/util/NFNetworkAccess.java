// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.awt.Toolkit;
import java.awt.Image;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class NFNetworkAccess
{
    private static final boolean a = false;
    
    public static InetAddress getLocalHost() throws UnknownHostException {
        a("Getting localHost....");
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            a("\tUsing Privilege Based approach.");
            return NFPrivilegeBasedNetworkAccess.getLocalHost();
        }
        a("\tUsing Normal approach.");
        return InetAddress.getLocalHost();
    }
    
    public static InetAddress getIPByName(final String s) throws UnknownHostException {
        a("Getting IPByName," + s + "....");
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            a("\tUsing Privilege Based approach.");
            return NFPrivilegeBasedNetworkAccess.getIPByName(s);
        }
        a("\tUsing Normal approach.");
        return InetAddress.getByName(s);
    }
    
    public static InputStream getResourceAsStream(final Class clazz, final String s) {
        a("Getting resource, " + s + "....");
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            a("\tUsing Privilege Based approach.");
            return NFPrivilegeBasedNetworkAccess.getResourceAsStream(clazz, s);
        }
        a("\tUsing Normal approach.");
        return clazz.getResourceAsStream(s);
    }
    
    public static InputStream getURLAsStream(final URL url) {
        a("Getting URL, " + url + "....");
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            a("\tUsing Privilege Based approach.");
            return NFPrivilegeBasedNetworkAccess.getURLAsStream(url);
        }
        a("\tUsing Normal approach.");
        try {
            if (url.getProtocol().equalsIgnoreCase("file")) {
                final String file = url.getFile();
                final int index = url.toString().indexOf(file);
                String s;
                if (File.separatorChar == '/') {
                    s = url.toString().substring(index);
                }
                else {
                    s = url.toString().substring((file.charAt(0) == '/') ? (index + 1) : index);
                    if (s.charAt(1) == '|') {
                        s = s.substring(0, 1) + ":" + s.substring(2, s.length());
                    }
                }
                String s2;
                if (File.separatorChar == '\\') {
                    s2 = s.replace('/', File.separatorChar);
                }
                else {
                    s2 = s.replace('\\', File.separatorChar);
                }
                return new FileInputStream(NFUtil.urlDecode(s2));
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            return openConnection.getInputStream();
        }
        catch (Exception ex) {
            a("\tException Opening Stream: " + ex);
            return null;
        }
    }
    
    public static InputStream writeContentToURL(final URL url, final String s) {
        return writeContentToURL(url, s.getBytes());
    }
    
    public static InputStream writeContentToURL(final URL url, final byte[] array) {
        a("Writing to URL, " + url + "....");
        if (NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment()) {
            a("\tUsing Privilege Based approach.");
            return NFPrivilegeBasedNetworkAccess.writeContentToURL(url, array);
        }
        a("\tUsing Normal approach.");
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(array);
            outputStream.flush();
            outputStream.close();
            return openConnection.getInputStream();
        }
        catch (Exception ex) {
            a("\tException Writing to URL: " + ex);
            return null;
        }
    }
    
    public static Image getImageResource(final Class clazz, final String s) {
        try {
            return Toolkit.getDefaultToolkit().createImage(NFHttpClient.readBytesFully(getResourceAsStream(clazz, s)));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Image getImageFromURL(final URL url) {
        try {
            return Toolkit.getDefaultToolkit().createImage(NFHttpClient.readBytesFully(getURLAsStream(url)));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static void a(final String s) {
    }
    
    public static void main(final String[] array) {
        try {
            System.out.println("file contents:\n" + new String(NFHttpClient.readBytesFully(getURLAsStream(new URL("http://camden/~bduncan/")))));
        }
        catch (Exception ex) {
            System.out.println("could not get url : " + ex);
            ex.printStackTrace();
        }
    }
}
