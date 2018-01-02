// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.net.URL;

public class SimplePost
{
    public static void main(final String[] args) {
        System.out.println();
    }
    
    public static String do_post(final String url_str, final String name, final String value) {
        final StringBuffer resp = new StringBuffer("");
        try {
            final URL url = new URL(url_str);
            final URLConnection con = url.openConnection();
            con.setDoOutput(true);
            final String params = URLEncoder.encode(name, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
            final OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(params);
            wr.flush();
            final BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while (rd.ready()) {
                resp.append(rd.readLine());
            }
            rd.close();
            wr.close();
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace(System.err);
        }
        catch (UnsupportedEncodingException ex2) {
            ex2.printStackTrace(System.err);
        }
        catch (MalformedURLException ex3) {
            ex3.printStackTrace(System.err);
        }
        catch (IOException ex4) {
            ex4.printStackTrace(System.err);
        }
        return resp.toString();
    }
}
