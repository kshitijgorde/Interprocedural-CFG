// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import java.util.HashMap;
import java.util.Collection;
import com.google.gson.reflect.TypeToken;
import java.util.LinkedList;
import com.google.gson.Gson;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import java.util.List;
import org.apache.commons.httpclient.HttpMethod;

public class Grooveshark
{
    private static final String LIBRARY_SCRIPT = "/cached_files.php";
    private static final String URL_SCRIPT = "/url_for_file.php";
    private static final String UPLOAD_SCRIPT = "/upload.php";
    private static String serverUrl;
    private static String sessionId;
    
    public Grooveshark(final String serverUrl, final String session) {
        Grooveshark.sessionId = session;
        Grooveshark.serverUrl = serverUrl;
    }
    
    public boolean addToLibrary(final Song song) throws Exception {
        final String jsonSong = this.convertSongToJson(song);
        final HttpMethod method = this.createLibraryRequest(jsonSong);
        final String response = this.postData(method);
        final List<String> hashes = this.extractHashes(response);
        return hashes.isEmpty();
    }
    
    private HttpMethod createLibraryRequest(final String jsonRequest) {
        final PostMethod method = new PostMethod(Grooveshark.serverUrl + "/cached_files.php");
        final NameValuePair[] data = { new NameValuePair("sessionId", Grooveshark.sessionId), new NameValuePair("files", jsonRequest) };
        method.setRequestBody(data);
        return (HttpMethod)method;
    }
    
    private List<String> extractHashes(final String response) {
        final Gson gson = new Gson();
        final List<String> hashesToCache = new LinkedList<String>();
        final TypeToken<List<String>> type = new TypeToken<List<String>>() {};
        final List<String> hashes = (List<String>)gson.fromJson(response, type.getType());
        hashesToCache.addAll(hashes);
        return hashes;
    }
    
    private String convertSongToJson(final Song song) {
        final Gson gson = new Gson();
        final HashMap<String, Song> request = new HashMap<String, Song>();
        final String filehash = song.getFileHash();
        request.put(filehash, song);
        return gson.toJson((Object)request);
    }
    
    private HttpMethod createUploadRequest(final Song song) {
        final String fileHash = song.getFileHash();
        final String fileSize = Long.toString(song.getFile().length());
        final GetMethod method = new GetMethod(Grooveshark.serverUrl + "/url_for_file.php");
        final NameValuePair[] data = { new NameValuePair("sessionId", Grooveshark.sessionId), new NameValuePair("fileSize", fileSize), new NameValuePair("fileHash", fileHash) };
        method.setQueryString(data);
        return (HttpMethod)method;
    }
    
    public String getUploadLocation(final Song song) {
        String uploadUrl = Grooveshark.serverUrl + "/upload.php";
        final HttpMethod method = this.createUploadRequest(song);
        try {
            final String response = this.postData(method);
            final int newlineIndex = response.indexOf("\n");
            uploadUrl = response.substring(0, newlineIndex);
        }
        catch (Exception e) {
            System.err.println("Failed to load upload URL from server.");
        }
        return uploadUrl;
    }
    
    private String postData(final HttpMethod method) throws Exception {
        try {
            final HttpClient httpClient = new HttpClient();
            final DefaultHttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler();
            httpClient.getParams().setParameter("http.method.retry-handler", (Object)retryHandler);
            final int responseCode = httpClient.executeMethod(method);
            if (responseCode == 200) {
                return method.getResponseBodyAsString();
            }
            throw new Exception("Method failed: " + method.getStatusLine());
        }
        finally {
            method.releaseConnection();
        }
    }
}
