// 
// Decompiled by Procyon v0.5.30
// 

package net.formis.fstech.bandwidthtest;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URLConnection;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JApplet;

public class ComponentHandler
{
    public static final String GET_METHOD = "GET";
    public static final String POST_METHOD = "POST";
    JApplet app;
    
    public ComponentHandler(final JApplet applet) {
        this.app = null;
        this.app = applet;
    }
    
    public String httpRequestAction(final boolean secure, String url, final HashMap getParams, final HashMap postParams) throws Exception {
        String objJSON = null;
        URLConnection objConn = null;
        try {
            if (getParams != null) {
                url = url + "?" + this.populateGetParams(getParams);
            }
            this.debug("[httpRequestAction] Try to establish connection..." + url);
            final URL objUrl = new URL(url);
            if (secure) {
                objConn = this.initHttpsConnection(objUrl, "GET", postParams);
            }
            else {
                objConn = this.initHttpConnection(objUrl, "GET", postParams);
            }
            objConn.connect();
            this.debug("[httpRequestAction] URL connection calling...");
            objJSON = this.retrieveOutputStream(objConn);
            this.debug("[httpRequestAction] URL connection response : " + objJSON);
            this.debug("[httpRequestAction] URL connection called...");
        }
        catch (Exception exp) {
            throw exp;
        }
        return objJSON;
    }
    
    private String retrieveOutputStream(final URLConnection objConn) throws Exception {
        final BufferedInputStream ri = new BufferedInputStream(objConn.getInputStream());
        final BufferedReader rd = new BufferedReader(new InputStreamReader(ri));
        final StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.substring(0);
    }
    
    private HttpURLConnection initHttpConnection(final URL objUrl, final String httpMethod, final HashMap postParam) throws Exception {
        final HttpURLConnection objConn = (HttpURLConnection)objUrl.openConnection();
        objConn.setDoInput(true);
        objConn.setDoOutput(true);
        objConn.setUseCaches(false);
        objConn.setDefaultUseCaches(false);
        objConn.setRequestMethod(httpMethod);
        if (httpMethod.equalsIgnoreCase("GET") && postParam != null && postParam.size() > 0) {
            objConn.setRequestProperty("Content-Type", "application/octet-stream");
            final String postParameters = this.populatePostParams(postParam);
            objConn.setRequestProperty("Content-Length", "" + Integer.toString(postParameters.getBytes().length));
            objConn.setRequestProperty("Content-Language", "en-US");
            final DataOutputStream objDOS = new DataOutputStream(objConn.getOutputStream());
            objDOS.writeBytes(postParameters);
            objDOS.flush();
            objDOS.close();
        }
        return objConn;
    }
    
    private HttpsURLConnection initHttpsConnection(final URL objUrl, final String httpMethod, final HashMap postParam) throws Exception {
        final HttpsURLConnection objConn = (HttpsURLConnection)objUrl.openConnection();
        objConn.setDoInput(true);
        objConn.setDoOutput(true);
        objConn.setUseCaches(false);
        objConn.setDefaultUseCaches(false);
        objConn.setRequestMethod(httpMethod);
        if (httpMethod.equalsIgnoreCase("GET") && postParam != null && postParam.size() > 0) {
            objConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            final String postParameters = this.populatePostParams(postParam);
            objConn.setRequestProperty("Content-Length", "" + Integer.toString(postParameters.getBytes().length));
            objConn.setRequestProperty("Content-Language", "en-US");
            final DataOutputStream objDOS = new DataOutputStream(objConn.getOutputStream());
            objDOS.writeBytes(postParameters);
            objDOS.flush();
            objDOS.close();
        }
        return objConn;
    }
    
    private String populateGetParams(final HashMap params) {
        final StringBuffer strBuffer = new StringBuffer();
        if (params != null) {
            final Set keyset = params.keySet();
            final Iterator iter = keyset.iterator();
            while (iter.hasNext()) {
                final String key = iter.next();
                final Object value = params.get(key);
                strBuffer.append(key).append("=").append(value);
                if (iter.hasNext()) {
                    strBuffer.append("&");
                }
            }
        }
        return strBuffer.substring(0);
    }
    
    private String populatePostParams(final HashMap params) throws Exception {
        final StringBuffer strBuf = new StringBuffer();
        if (params != null) {
            final Set keyset = params.keySet();
            final Iterator iter = keyset.iterator();
            while (iter.hasNext()) {
                final String key = iter.next();
                final String value = params.get(key);
                strBuf.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
                if (iter.hasNext()) {
                    strBuf.append("&");
                }
            }
        }
        return strBuf.substring(0);
    }
    
    private void debug(final String msg) {
        System.out.println("[INFO] " + msg);
    }
}
