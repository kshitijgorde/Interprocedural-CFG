// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Enumeration;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Hashtable;

public class NFDataBeanTable
{
    private static NFDataBeanTable a;
    private static Hashtable b;
    private static Hashtable c;
    
    private static void a(final String s) {
        InputStream urlAsStream;
        try {
            final URL fileURL = NFUtil.getFileURL(NFFile.resolvePath(s));
            c("Attempting to load " + fileURL);
            urlAsStream = NFNetworkAccess.getURLAsStream(fileURL);
        }
        catch (Exception ex) {
            c(ex.getMessage());
            return;
        }
        try {
            final NFToken nfToken = new NFToken();
            nfToken.setCharType(2, 46, 0);
            nfToken.setCharType(2, 95, 0);
            nfToken.setCharType(2, 45, 0);
            final Properties properties = new Properties();
            properties.load(urlAsStream);
            final Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                final String s2 = (String)propertyNames.nextElement();
                final String property = properties.getProperty(s2);
                final StringBuffer sb = new StringBuffer();
                final StringBuffer sb2 = new StringBuffer();
                nfToken.setInput(property);
                nfToken.nextToken(sb);
                nfToken.nextToken(sb2);
                String string = sb2.toString();
                if (string.equalsIgnoreCase("NULL")) {
                    string = null;
                }
                addDataBean(sb.toString(), string, s2);
            }
        }
        catch (Exception ex2) {
            b("Failed to load " + s);
        }
    }
    
    public static void addDataBean(final String s, final String s2, final String s3) {
        c("Adding " + s + "," + s2 + "," + s3);
        NFDataBeanTable.b.put(s, s3);
        if (s2 != null) {
            NFDataBeanTable.c.put(s2, s);
        }
    }
    
    private static void b(final String s) {
        NFDebug.print(128L, "NFDataBeanTable: " + s);
    }
    
    private static void c(final String s) {
        NFDebug.print(128L, "NFDataBeanTable: " + s);
    }
    
    public static NFDataBeanTable getDefault() {
        if (NFDataBeanTable.a == null) {
            NFDataBeanTable.a = new NFDataBeanTable();
        }
        return NFDataBeanTable.a;
    }
    
    public NFDataBean getDataBeanForParam(final String s) {
        String substring = s;
        for (int i = 1; i < substring.length(); ++i) {
            if (Character.isUpperCase(substring.charAt(i))) {
                substring = substring.substring(0, i);
                break;
            }
        }
        return this.getDataBeanForPrefix(substring);
    }
    
    public NFDataBean getDataBeanForKeyword(final String s) {
        final String s2 = NFDataBeanTable.c.get(s);
        if (s2 == null) {
            return null;
        }
        return this.getDataBeanForPrefix(s2);
    }
    
    public NFDataBean getDataBeanForPrefix(final String s) {
        final String value = NFDataBeanTable.b.get(s);
        if (value == null) {
            return null;
        }
        if (value instanceof NFDataBean) {
            return (NFDataBean)value;
        }
        final String s2 = value;
        try {
            c("Loading " + s2);
            final Object instance = Class.forName(s2).newInstance();
            if (!(instance instanceof NFDataBean)) {
                c("Class " + s2 + " does not extend NFDataBean");
                return null;
            }
            NFDataBeanTable.b.put(s, instance);
            return (NFDataBean)instance;
        }
        catch (Exception ex) {
            if (NFDebug.enabled(128L)) {
                c("Unable to load " + s2);
                ex.printStackTrace();
            }
            return null;
        }
    }
    
    public NFDataBean newDataBean(NFDataBean nfDataBean) {
        try {
            nfDataBean = (NFDataBean)nfDataBean.getClass().newInstance();
            return nfDataBean;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    static {
        NFDataBeanTable.a = null;
        NFDataBeanTable.b = new Hashtable();
        NFDataBeanTable.c = new Hashtable();
        addDataBean("File", "FILE", "netcharts.databean.NFFileBean");
        addDataBean("Param", "SERVER", "netcharts.databean.NFParamBean");
        addDataBean("Include", null, "netcharts.databean.NFIncludeFile");
        addDataBean("Nds", "NDS", "netcharts.databean.NFNDSBean");
        addDataBean("Style", "STYLE", "netcharts.databean.NFStyleBean");
        addDataBean("CdxVariable", "CDXVARIABLE", "netcharts.databean.NFCDXBean");
        if (NFContext.getUserAgentType() == 0) {
            a("$NETCHARTS/databean/DataBeanTable.txt");
        }
    }
}
