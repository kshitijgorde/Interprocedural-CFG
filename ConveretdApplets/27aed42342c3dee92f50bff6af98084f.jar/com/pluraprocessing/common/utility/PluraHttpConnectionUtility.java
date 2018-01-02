// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.net.HttpURLConnection;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.jar.JarOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.jar.JarInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.util.List;
import java.util.Iterator;
import java.util.Set;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.HashMap;
import com.pluraprocessing.common.domain.HttpResponse;

public class PluraHttpConnectionUtility
{
    public static HttpResponse httpRequestByIP(final String hostIP, final int port, final String uri, final String content, final HttpMethod httpMethod) throws IOException {
        return httpRequestByIP(hostIP, port, uri, content, null, httpMethod);
    }
    
    public static HttpResponse httpRequestByIP(final String hostIP, final int port, final String uri, final String content, final HashMap<String, String> headerTree, final HttpMethod httpMethod) throws IOException {
        final String host = hostIP.replace("http://", "").replace("/", "");
        InetAddress addr;
        try {
            final String[] strs = host.split("\\.");
            final byte[] bytes = new byte[4];
            for (int i = 0; i < 4; ++i) {
                bytes[i] = (byte)Integer.parseInt(strs[i]);
            }
            addr = InetAddress.getByAddress(bytes);
        }
        catch (Exception e) {
            addr = InetAddress.getByName(host);
        }
        return httpRequest(addr, host, port, uri, content, headerTree, httpMethod);
    }
    
    public static HttpResponse httpRequestByDomain(final String serverName, final int port, final String uri, final String content, final HashMap<String, String> headerTree, final HttpMethod httpMethod) throws IOException {
        final String host = serverName.replace("http://", "").replace("/", "");
        final InetAddress addr = InetAddress.getByName(host);
        return httpRequest(addr, host, port, uri, content, headerTree, httpMethod);
    }
    
    public static HttpResponse httpRequest(final InetAddress addr, final String host, final int port, final String uri, final String content, final HashMap<String, String> headerTree, final HttpMethod httpMethod) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        InputStream inputStream = null;
        final HttpResponse httpResponse = new HttpResponse();
        try {
            socket = new Socket(addr, port);
            socket.setSoTimeout(360000);
            out = new PrintWriter(socket.getOutputStream(), true);
            inputStream = socket.getInputStream();
            out.println(String.valueOf(httpMethod.toString()) + " " + uri + " HTTP/1.1");
            out.println("Connection: close");
            out.println("Accept: */*");
            out.println("Host: " + host + ":" + port);
            out.println("Content-Length: " + content.length());
            out.println("Accept-Encoding: gzip");
            if (headerTree != null) {
                final Set<Map.Entry<String, String>> set = headerTree.entrySet();
                for (final Map.Entry<String, String> me : set) {
                    final String key = me.getKey();
                    if (!key.contains("Connection") && !key.contains("Accept") && !key.contains("Host") && !key.contains("Content-Length") && !key.contains("Accept-Encoding")) {
                        out.println(String.valueOf(key) + ": " + me.getValue());
                    }
                }
            }
            out.println();
            out.println(content);
            final byte[] request = obtainByteData(inputStream);
            httpResponse.setResponseByteArraySize(request.length);
            final List<String> headerList = new ArrayList<String>();
            ByteArrayOutputStream line = new ByteArrayOutputStream();
            int i;
            for (i = 0; i < request.length; ++i) {
                if (request[i] != 10) {
                    line.write(request[i]);
                }
                else {
                    final byte[] array = line.toByteArray();
                    if (array.length == 1) {
                        break;
                    }
                    if (httpResponse.getStatusCodeLine() == null) {
                        httpResponse.setStatusCodeLine(new String(array).trim());
                    }
                    else {
                        headerList.add(new String(array));
                    }
                    line = new ByteArrayOutputStream();
                }
            }
            httpResponse.setResponseHeaderTree(parseHeader(headerList.toString()));
            line = null;
            byte[] body;
            if (httpResponse.getHeader("Transfer-Encoding") != null && httpResponse.getHeader("Transfer-Encoding").equals("chunked")) {
                final ArrayList<byte[]> chunks = new ArrayList<byte[]>();
                int totalLength = 0;
                final int maxI = request.length - 1;
                boolean stop = false;
                do {
                    byte readByte;
                    for (readByte = request[i]; (readByte == 10 || readByte == 13) && i < maxI; ++i, readByte = request[i]) {}
                    line = new ByteArrayOutputStream();
                    while (readByte != 32 && readByte != 10 && readByte != 13 && i < maxI) {
                        ++i;
                        line.write(readByte);
                        readByte = request[i];
                    }
                    final byte[] hexSizeArray = line.toByteArray();
                    final String hexValue = new String(hexSizeArray, 0, hexSizeArray.length, "UTF-8");
                    int numberBytesToRead = 0;
                    try {
                        numberBytesToRead = Integer.parseInt(hexValue, 16);
                    }
                    catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    totalLength += numberBytesToRead;
                    if (numberBytesToRead != 0) {
                        while (readByte != 10 && readByte != 13 && i < maxI) {
                            ++i;
                            readByte = request[i];
                        }
                        for (int num = 0; (readByte == 10 || readByte == 13) && i < maxI && num < 2; ++i, ++num, readByte = request[i]) {}
                        if (numberBytesToRead > request.length - i) {
                            numberBytesToRead = request.length - i;
                        }
                        final byte[] chunk = new byte[numberBytesToRead];
                        System.arraycopy(request, i, chunk, 0, numberBytesToRead);
                        chunks.add(chunk);
                        i += numberBytesToRead;
                    }
                    else {
                        stop = true;
                    }
                } while (!stop && i < request.length);
                body = new byte[totalLength];
                int position = 0;
                for (final byte[] chunk2 : chunks) {
                    System.arraycopy(chunk2, 0, body, position, chunk2.length);
                    position += chunk2.length;
                }
            }
            else {
                body = new byte[request.length - (i + 1)];
                System.arraycopy(request, i + 1, body, 0, body.length);
            }
            final String contentEncodingString = httpResponse.getHeader("Content-Encoding");
            if (contentEncodingString != null && contentEncodingString.equals("gzip")) {
                httpResponse.setResponseBody(GZipUtility.uncompress(body));
            }
            else {
                httpResponse.setResponseBody(body);
            }
        }
        finally {
            if (out != null) {
                out.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                    socket = null;
                }
                catch (IOException ex) {}
            }
        }
        if (out != null) {
            out.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
                socket = null;
            }
            catch (IOException ex2) {}
        }
        return httpResponse;
    }
    
    @Deprecated
    public static String readCharacters(final BufferedReader reader) throws IOException {
        final int characterBufferLength = 256;
        final char[] characters = new char[characterBufferLength];
        final StringBuilder process = new StringBuilder();
        int numRead = 0;
        do {
            process.append(characters, 0, numRead);
            numRead = reader.read(characters, 0, characterBufferLength);
        } while (numRead != -1);
        return process.toString();
    }
    
    private static HashMap<String, String> parseHeader(final String data) {
        final HashMap<String, String> tm = new HashMap<String, String>();
        final String pat = ", (.*?): (.*?)$";
        final Matcher match = Pattern.compile(pat, 8).matcher(data);
        while (match.find()) {
            tm.put(match.group(1).trim(), match.group(2).trim());
        }
        return tm;
    }
    
    public static byte[] obtainByteData(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        final byte[] bytes = new byte[512];
        int readBytes;
        while ((readBytes = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, readBytes);
        }
        final byte[] byteData = outputStream.toByteArray();
        outputStream.close();
        return byteData;
    }
    
    public static void obtainJarFile(final InputStream inputStream, final String filePath) throws IOException {
        JarOutputStream outputStream = null;
        JarInputStream jarInputStream = null;
        try {
            jarInputStream = new JarInputStream(inputStream, false);
            if (jarInputStream.available() == 0) {
                return;
            }
            if (jarInputStream.getManifest() != null) {
                outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filePath))), jarInputStream.getManifest());
            }
            else {
                outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filePath))));
            }
            JarEntry jarEntry;
            while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                outputStream.putNextEntry(new ZipEntry(jarEntry.getName()));
                final byte[] bytes = new byte[16384];
                int bytesRead = jarInputStream.read(bytes);
                int valueRead = 0;
                while (bytesRead != -1) {
                    valueRead += bytesRead;
                    outputStream.write(bytes, 0, bytesRead);
                    bytesRead = jarInputStream.read(bytes);
                }
                outputStream.closeEntry();
            }
        }
        finally {
            if (outputStream != null) {
                outputStream.finish();
                outputStream.flush();
                outputStream.close();
            }
            if (jarInputStream != null) {
                jarInputStream.close();
                jarInputStream = null;
            }
        }
        if (outputStream != null) {
            outputStream.finish();
            outputStream.flush();
            outputStream.close();
        }
        if (jarInputStream != null) {
            jarInputStream.close();
            jarInputStream = null;
        }
    }
    
    public static String getStringFromInputStream(final InputStream in) throws IOException {
        final InputStreamReader isr = new InputStreamReader(in);
        final int characterBufferLength = 256;
        final char[] characters = new char[characterBufferLength];
        final StringBuilder process = new StringBuilder();
        int numRead = 0;
        do {
            process.append(characters, 0, numRead);
            numRead = isr.read(characters, 0, characterBufferLength);
        } while (numRead != -1);
        return process.toString();
    }
    
    @Deprecated
    public static String getXMLFromInputString(final HttpURLConnection conn) throws IOException {
        return getStringFromInputStream(conn.getInputStream());
    }
}
