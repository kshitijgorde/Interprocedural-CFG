// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.lang.reflect.Method;
import java.io.OutputStream;
import java.net.URLConnection;
import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;

public class NFPrivilegeBasedNetworkAccess
{
    private static final boolean a = false;
    protected static Class thisClass;
    public static final String NETSCAPE_PRIVILEGE_MANAGER = "netscape.security.PrivilegeManager";
    public static final String NETSCAPE_PRIVILEGE_METHOD = "enablePrivilege";
    public static final String NETSCAPE_IS_CODEBASE_METHOD = "isCodebase";
    public static final String NETSCAPE_PRINCIPALS_METHOD = "getClassPrincipals";
    public static final String NETSCAPE_GET_PRIVILEGE_MANAGER_METHOD = "getPrivilegeManager";
    public static final String NETSCAPE_TERMINAL_ACCESS = "TerminalEmulator";
    public static final String NETSCAPE_FILE_ACCESS = "UniversalFileAccess";
    public static final String NETSCAPE_PROPERTY_READ_ACCESS = "UniversalPropertyRead";
    public static final String NETSCAPE_JAVA_SECURITY_MANAGER = "java.lang.SecurityManager";
    public static final String NETSCAPE_APPLET_FRAME = "netscape.applet.DerivedAppletFrame";
    public static final String IE_PRIVILEGE_MANAGER = "com.ms.security.PolicyEngine";
    public static final String IE_PERMISSION = "com.ms.security.PermissionID";
    public static final String IE_PERMISSION_SYSTEM = "SYSTEM";
    public static final String IE_PRIVILEGE_METHOD = "assertPermission";
    public static final String IE_CHECK_FOR_ALL_PERMISSIONS_METHOD = "checkClassForAllPermissions";
    public static int NETSCAPE;
    public static int IE;
    public static int OTHER;
    public static int BROWSER;
    public static boolean isPrivilegeBasedSecurityEnvironment;
    static /* synthetic */ Class b;
    static /* synthetic */ Class c;
    static /* synthetic */ Class d;
    
    protected static Class retrieveClass(final String s) {
        Class<?> forName;
        try {
            forName = Class.forName(s);
        }
        catch (Throwable t) {
            a("\tCould not find class: " + s + ".");
            forName = null;
        }
        return forName;
    }
    
    private static void a(final String s) {
    }
    
    public static InetAddress getLocalHost() throws UnknownHostException {
        a("getLocalHost...");
        final Class[] array = new Class[0];
        final Object[] array2 = new Object[0];
        try {
            return (InetAddress)invokePrivilegedMethod(null, NFPrivilegeBasedNetworkAccess.thisClass.getMethod("getLocalHost0", (Class[])array), array2);
        }
        catch (Exception ex) {
            if (ex instanceof InvocationTargetException && ((InvocationTargetException)ex).getTargetException() instanceof UnknownHostException) {
                throw (UnknownHostException)((InvocationTargetException)ex).getTargetException();
            }
            a("Exception duroing call: " + ex);
            return null;
        }
    }
    
    public static InetAddress getLocalHost0() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }
    
    public static InetAddress getIPByName(final String s) throws UnknownHostException {
        a("getIPByName, " + s + "...");
        final Class[] array = { (NFPrivilegeBasedNetworkAccess.b == null) ? (NFPrivilegeBasedNetworkAccess.b = class$("java.lang.String")) : NFPrivilegeBasedNetworkAccess.b };
        final Object[] array2 = { s };
        try {
            return (InetAddress)invokePrivilegedMethod(null, NFPrivilegeBasedNetworkAccess.thisClass.getMethod("getIPByName0", (Class[])array), array2);
        }
        catch (Exception ex) {
            if (ex instanceof InvocationTargetException && ((InvocationTargetException)ex).getTargetException() instanceof UnknownHostException) {
                throw (UnknownHostException)((InvocationTargetException)ex).getTargetException();
            }
            a("Exception during call: " + ex);
            return null;
        }
    }
    
    public static InetAddress getIPByName0(final String s) throws UnknownHostException {
        return InetAddress.getByName(s);
    }
    
    public static InputStream getResourceAsStream(final Class clazz, final String s) {
        a("getResource Class:" + clazz + " resource:" + s + " ...");
        final Class[] array = new Class[2];
        final Object[] array2 = { clazz, null };
        array[0] = clazz.getClass();
        array2[1] = s;
        array[1] = s.getClass();
        try {
            return (InputStream)invokePrivilegedMethod(null, NFPrivilegeBasedNetworkAccess.thisClass.getMethod("getResourceAsStream0", (Class[])array), array2);
        }
        catch (Exception ex) {
            a("Exception duroing call: " + ex);
            return null;
        }
    }
    
    public static InputStream getResourceAsStream0(final Class clazz, final String s) {
        a("\t\t\tInside getResourceAsStream0...");
        ByteArrayInputStream byteArrayInputStream;
        try {
            final InputStream resourceAsStream = clazz.getResourceAsStream(s);
            a("\t\t\t\tgot is=" + resourceAsStream + "...");
            byteArrayInputStream = new ByteArrayInputStream(NFHttpClient.readBytesFully(resourceAsStream));
            a("\t\t\t\tdone reading fully.");
        }
        catch (Exception ex) {
            a("Exception during getResourceAsStream0: " + ex);
            byteArrayInputStream = null;
        }
        return byteArrayInputStream;
    }
    
    public static InputStream getURLAsStream(final URL url) {
        final Object[] array = { url };
        final Class[] array2 = { url.getClass() };
        try {
            return (InputStream)invokePrivilegedMethod(null, NFPrivilegeBasedNetworkAccess.thisClass.getMethod("getURLAsStream0", (Class[])array2), array);
        }
        catch (Exception ex) {
            a("Exception during call: " + ex);
            return null;
        }
    }
    
    public static InputStream getURLAsStream0(final URL url) {
        a("\t\t\tInside getURLAsStream0...");
        InputStream inputStream2;
        try {
            InputStream inputStream;
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
                inputStream = new FileInputStream(NFUtil.urlDecode(s2));
            }
            else {
                final URLConnection openConnection = url.openConnection();
                openConnection.setUseCaches(false);
                inputStream = openConnection.getInputStream();
            }
            a("\t\t\t\tgot is=" + inputStream + "...");
            inputStream2 = new ByteArrayInputStream(NFHttpClient.readBytesFully(inputStream));
            a("\t\t\t\tdone reading fully.");
        }
        catch (Exception ex) {
            a("Exception during getURLAsStream0: " + ex);
            inputStream2 = null;
        }
        return inputStream2;
    }
    
    public static InputStream writeContentToURL(final URL url, final String s) {
        return writeContentToURL(url, s.getBytes());
    }
    
    public static InputStream writeContentToURL(final URL url, final byte[] array) {
        final Object[] array2 = { url, array };
        final Class[] array3 = { url.getClass(), array.getClass() };
        try {
            return (InputStream)invokePrivilegedMethod(null, NFPrivilegeBasedNetworkAccess.thisClass.getMethod("writeContentToURL0", (Class[])array3), array2);
        }
        catch (Exception ex) {
            a("Exception during call: " + ex);
            return null;
        }
    }
    
    public static InputStream writeContentToURL0(final URL url, final byte[] array) {
        a("\t\t\tInside writeContentToURL0...");
        ByteArrayInputStream byteArrayInputStream;
        try {
            a("\t\t\t\topening connection....");
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            openConnection.setDoOutput(true);
            openConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            a("\t\t\t\twriting....");
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(array);
            outputStream.flush();
            outputStream.close();
            a("\t\t\t\treading....");
            byteArrayInputStream = new ByteArrayInputStream(NFHttpClient.readBytesFully(openConnection.getInputStream()));
            a("\t\t\t\tdone.");
        }
        catch (Exception ex) {
            a("Exception during writeContentToURL0: " + ex);
            byteArrayInputStream = null;
        }
        return byteArrayInputStream;
    }
    
    public static int getBrowser() {
        return NFPrivilegeBasedNetworkAccess.BROWSER;
    }
    
    public static boolean isPrivilegeBasedSecurityEnvironment() {
        return NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment;
    }
    
    private static boolean a() {
        final Class clazz = (NFPrivilegeBasedNetworkAccess.c == null) ? (NFPrivilegeBasedNetworkAccess.c = class$("netcharts.util.NFUtil")) : NFPrivilegeBasedNetworkAccess.c;
        if (NFPrivilegeBasedNetworkAccess.BROWSER == NFPrivilegeBasedNetworkAccess.NETSCAPE) {
            try {
                final Class[] array = { (NFPrivilegeBasedNetworkAccess.d == null) ? (NFPrivilegeBasedNetworkAccess.d = class$("java.lang.Class")) : NFPrivilegeBasedNetworkAccess.d };
                final Class[] array2 = { clazz };
                final Class retrieveClass = retrieveClass("netscape.security.PrivilegeManager");
                final Object o = ((Object[])retrieveClass.getMethod("getClassPrincipals", (Class[])array).invoke(retrieveClass.getMethod("getPrivilegeManager", (Class[])null).invoke(null, (Object[])null), (Object[])array2))[0];
                return !(boolean)o.getClass().getMethod("isCodebase", (Class<?>[])null).invoke(o, (Object[])null);
            }
            catch (Exception ex) {
                a("Could not determine anything in Netscape.");
                return false;
            }
        }
        if (NFPrivilegeBasedNetworkAccess.BROWSER == NFPrivilegeBasedNetworkAccess.IE) {
            try {
                retrieveClass("com.ms.security.PolicyEngine").getMethod("checkClassForAllPermissions", (NFPrivilegeBasedNetworkAccess.d == null) ? (NFPrivilegeBasedNetworkAccess.d = class$("java.lang.Class")) : NFPrivilegeBasedNetworkAccess.d).invoke(null, clazz);
                return true;
            }
            catch (Exception ex2) {
                return false;
            }
        }
        return false;
    }
    
    public static Object invokePrivilegedMethod(final Object o, final Method method, final Object[] array) {
        a("invokePrivmethod: " + method.getName() + "...");
        if (NFPrivilegeBasedNetworkAccess.BROWSER == NFPrivilegeBasedNetworkAccess.NETSCAPE) {
            try {
                a("\t Netscape Method....");
                final Class retrieveClass = retrieveClass("java.lang.SecurityManager");
                final Object[] array2 = { "UniversalPropertyRead" };
                final Class[] array3 = { array2[0].getClass() };
                a("\t\tEnabling JSM Property Read Access Privilege for Netscape Browser...");
                retrieveClass.getMethod("enablePrivilege", (Class[])array3).invoke(null, array2);
                a("\t\t\tSuccess! JSM Property Read Access Privilege is enabled.");
                final Method method2 = retrieveClass("netscape.security.PrivilegeManager").getMethod("enablePrivilege", (Class[])array3);
                a("\t\tEnabling Terminal Privilege for Netscape Browser...");
                array2[0] = "TerminalEmulator";
                method2.invoke(null, array2);
                a("\t\t\tSuccess! Terminal Privilege is enabled.");
                a("\t\tEnabling File Access Privilege for Netscape Browser...");
                array2[0] = "UniversalFileAccess";
                method2.invoke(null, array2);
                a("\t\t\tSuccess! File Access Privilege is enabled.");
                a("\t\tEnabling Property Read Access Privilege for Netscape Browser...");
                array2[0] = "UniversalPropertyRead";
                method2.invoke(null, array2);
                a("\t\t\tSuccess! Property Read Access Privilege is enabled.");
            }
            catch (Exception ex) {
                a("Exception enabling Netscape privs: " + ex);
            }
        }
        else if (NFPrivilegeBasedNetworkAccess.BROWSER == NFPrivilegeBasedNetworkAccess.IE) {
            return NFIEPermission.invokePrivilegedMethod(o, method, array);
        }
        try {
            return method.invoke(o, array);
        }
        catch (Exception ex2) {
            a("Exception thrown durong method invoke: " + ex2);
            return null;
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        NFPrivilegeBasedNetworkAccess.thisClass = null;
        NFPrivilegeBasedNetworkAccess.NETSCAPE = 0;
        NFPrivilegeBasedNetworkAccess.IE = 1;
        NFPrivilegeBasedNetworkAccess.OTHER = 2;
        NFPrivilegeBasedNetworkAccess.BROWSER = NFPrivilegeBasedNetworkAccess.OTHER;
        NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment = false;
        try {
            NFPrivilegeBasedNetworkAccess.thisClass = Class.forName("netcharts.util.NFPrivilegeBasedNetworkAccess");
        }
        catch (Exception ex) {
            NFPrivilegeBasedNetworkAccess.thisClass = null;
        }
        final Class retrieveClass = retrieveClass("netscape.applet.DerivedAppletFrame");
        final boolean b = retrieveClass("netscape.security.PrivilegeManager") != null && retrieveClass != null;
        final boolean b2 = retrieveClass("com.ms.security.PolicyEngine") != null && retrieveClass == null;
        if (b2 && !b) {
            NFPrivilegeBasedNetworkAccess.BROWSER = NFPrivilegeBasedNetworkAccess.IE;
        }
        else if (b && !b2) {
            NFPrivilegeBasedNetworkAccess.BROWSER = NFPrivilegeBasedNetworkAccess.NETSCAPE;
        }
        else {
            NFPrivilegeBasedNetworkAccess.BROWSER = NFPrivilegeBasedNetworkAccess.OTHER;
        }
        NFPrivilegeBasedNetworkAccess.isPrivilegeBasedSecurityEnvironment = a();
    }
}
