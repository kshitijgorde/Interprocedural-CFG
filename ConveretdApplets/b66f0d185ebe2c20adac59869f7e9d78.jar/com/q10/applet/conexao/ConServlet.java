// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.conexao;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.util.Enumeration;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConServlet extends Cliente
{
    protected URLConnection con;
    private URL url;
    private String erro;
    
    public void abreConexao(final String s) {
        try {
            this.url = new URL(s);
        }
        catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        catch (IOException ex2) {
            System.out.println("abreConexao::" + ex2);
        }
    }
    
    public String setParametros(final Hashtable hashtable) {
        this.insereVersao(hashtable);
        final Enumeration<String> keys = hashtable.keys();
        final StringBuffer sb = new StringBuffer();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            sb.append(URLEncoder.encode(s));
            sb.append("=");
            sb.append(URLEncoder.encode(hashtable.get(s)));
            if (keys.hasMoreElements()) {
                sb.append("&");
            }
        }
        return sb.toString();
    }
    
    public void enviaComando(final String s) {
        try {
            (this.con = this.url.openConnection()).setUseCaches(false);
            this.con.setDoOutput(true);
            (super.out = new DataOutputStream(new BufferedOutputStream(this.con.getOutputStream()))).writeBytes(s);
            super.out.flush();
        }
        catch (IOException ex) {
            this.erro = ex.toString();
            System.err.println(String.valueOf(ex) + " enquanto enviando pedido pro servidor.");
        }
    }
    
    public byte[] recebeResposta() {
        byte[] array = null;
        try {
            super.in = new DataInputStream(new BufferedInputStream(this.con.getInputStream()));
            array = new byte[super.in.readInt()];
            super.in.read(array, 0, array.length);
        }
        catch (IOException ex) {
            this.erro = ex.toString();
            System.err.println(String.valueOf(ex) + " enquanto lendo resposta do servidor.");
        }
        return array;
    }
    
    public byte[] recebeRespostaSimples(final int n) {
        final byte[] array = new byte[(n > 0) ? n : 1024];
        try {
            (super.in = new DataInputStream(new BufferedInputStream(this.con.getInputStream()))).read(array, 0, array.length);
        }
        catch (IOException ex) {
            this.erro = ex.toString();
            System.err.println(String.valueOf(ex) + " enquanto lendo resposta do servidor.");
        }
        return array;
    }
    
    public String getErro() {
        return this.erro;
    }
    
    public byte[] recebeResposta2() {
        byte[] byteArray = null;
        try {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(this.con.getInputStream());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = { 0 };
            while (gzipInputStream.read(array) > -1) {
                byteArrayOutputStream.write(array);
            }
            byteArray = new byte[byteArrayOutputStream.size()];
            byteArray = byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            this.erro = ex.toString();
            System.err.println(String.valueOf(ex) + " enquanto lendo resposta do servidor.");
        }
        return byteArray;
    }
    
    public void terminaConexao() {
        try {
            if (super.in != null) {
                super.in.close();
                super.in = null;
            }
            if (super.out != null) {
                super.out.close();
                super.out = null;
            }
            this.url = null;
            this.con = null;
        }
        catch (IOException ex) {}
    }
    
    private void insereVersao(final Hashtable hashtable) {
        hashtable.put("versaojvm", System.getProperty("java.version"));
    }
}
