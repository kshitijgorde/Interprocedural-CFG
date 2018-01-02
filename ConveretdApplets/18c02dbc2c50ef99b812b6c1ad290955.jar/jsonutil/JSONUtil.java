// 
// Decompiled by Procyon v0.5.30
// 

package jsonutil;

import java.io.File;
import action.upload.params.UploadTaskParam;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import action.FileStationHandler;
import fileutil.SpaceUtil;
import fileutil.FileUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import fileutil.SFile;
import java.util.Vector;

public class JSONUtil
{
    static String _TYPE_LOCAL;
    static String _TYPE_LOCAL_HOME;
    
    public static JSONObject setDirAndFileListGrid(final Vector<SFile> vector, final int n) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (0 == vector.size()) {
            jsonObject.put("items", new JSONArray());
        }
        else {
            for (int i = 0; i < vector.size(); ++i) {
                jsonObject.append("items", setNoramilFileEntry(vector.get(i)));
            }
        }
        jsonObject.put("total", n);
        return jsonObject;
    }
    
    public static JSONObject setRootGrid(final Vector<SFile> vector, final int n) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (0 == vector.size()) {
            jsonObject.put("items", new JSONArray());
        }
        else {
            for (int i = 0; i < vector.size(); ++i) {
                jsonObject.append("items", SetOneRootEntry(vector.get(i)));
            }
        }
        jsonObject.put("total", n);
        return jsonObject;
    }
    
    private static void setOneEntry(final Map map, final SFile sFile) {
        map.put("file_id", sFile.getAbsolutePath());
        map.put("mt", sFile.lastModified() / 1000L);
        map.put("path", sFile.getAbsolutePath());
    }
    
    private static Map SetOneRootEntry(final SFile sFile) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        setOneEntry(hashMap, sFile);
        hashMap.put("filename", sFile.getAbsolutePath());
        hashMap.put("type", FileUtil.getTypeDescription(sFile));
        final long totalSpace = SpaceUtil.getTotalSpace(sFile);
        hashMap.put("filesize", (!SpaceUtil._isRootAccessible || 0L == totalSpace) ? "" : SpaceUtil.getFileUnit(totalSpace));
        hashMap.put("icon", FileUtil.getImgFileExt(sFile.getAbsolutePath(), true));
        hashMap.put("isdir", "root");
        return hashMap;
    }
    
    private static Map setNoramilFileEntry(final SFile sFile) {
        final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        setOneEntry(hashMap, sFile);
        hashMap.put("filename", sFile.getName());
        hashMap.put("filesize", SpaceUtil.getFileUnit(sFile.length()));
        hashMap.put("type", sFile.isDirectory() ? "" : FileUtil.getTypeDescription(sFile));
        hashMap.put("icon", FileUtil.getImgFileExt(sFile.getName(), !sFile.isFile()));
        hashMap.put("isdir", (String)!sFile.isFile());
        hashMap.put("is_compressed", (String)FileUtil.isCompressedFile(sFile.getName(), !sFile.isFile()));
        return hashMap;
    }
    
    public static Object setDirTree(final Vector<SFile> vector, final boolean b) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        if (0 == vector.size()) {
            return new JSONObject().put("items", new JSONArray());
        }
        for (int i = 0; i < vector.size(); ++i) {
            jsonArray.put(setNormalDirTree(vector.get(i), true, b));
        }
        return jsonArray;
    }
    
    private static void setOneDirTree(final Map map, final SFile sFile, final boolean b) {
        map.put("spath", sFile.getAbsolutePath());
        map.put("leaf", false);
        map.put("draggable", b);
        map.put("path", sFile.getAbsolutePath());
        map.put("right", "R");
        map.put("ftpright", 0);
    }
    
    private static Map setNormalDirTree(final SFile sFile, final boolean b, final boolean b2) {
        final String s = b2 ? JSONUtil._TYPE_LOCAL_HOME : JSONUtil._TYPE_LOCAL;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("text", sFile.getName());
        hashMap.put("id", s + sFile.getAbsolutePath());
        hashMap.put("type", s);
        hashMap.put("qtip", sFile.getName());
        setOneDirTree(hashMap, sFile, b);
        return hashMap;
    }
    
    public static Object setRootTree(final Vector<SFile> vector, final SFile sFile) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        if (0 == vector.size()) {
            return jsonArray;
        }
        if (null != sFile) {
            jsonArray.put(setRootTree(sFile, false, true));
        }
        for (int i = 0; i < vector.size(); ++i) {
            jsonArray.put(setRootTree(vector.get(i), false, false));
        }
        return jsonArray;
    }
    
    private static Map setRootTree(final SFile sFile, final boolean b, final boolean b2) {
        final String s = b2 ? JSONUtil._TYPE_LOCAL_HOME : JSONUtil._TYPE_LOCAL;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("text", b2 ? sFile.getName() : sFile.getAbsolutePath());
        hashMap.put("id", s + sFile.getAbsolutePath());
        hashMap.put("type", s);
        hashMap.put("qtip", b2 ? sFile.getName() : sFile.getAbsolutePath());
        setOneDirTree(hashMap, sFile, b);
        return hashMap;
    }
    
    public static JSONObject setDiskinformation(final SpaceUtil spaceUtil) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("used", spaceUtil.getUsedSpace());
        jsonObject.put("free", spaceUtil.getFreeSpace());
        jsonObject.put("currVolume", spaceUtil.getPartition());
        return jsonObject;
    }
    
    public static JSONObject setHostName() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("hostname", System.getProperty("user.name"));
        return jsonObject;
    }
    
    public static JSONObject setUserPath(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("userpath", s);
        return jsonObject;
    }
    
    public static JSONObject setError(final String s, final String s2) {
        final JSONObject jsonObject = new JSONObject();
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("key", s2);
            hashMap.put("section", s);
            jsonObject.put("errno", hashMap);
        }
        catch (JSONException ex) {
            FileStationHandler.log(ex);
        }
        return jsonObject;
    }
    
    public static JSONObject setSuccess(final boolean b) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", b);
        return jsonObject;
    }
    
    public static JSONObject transStreamToJSON(final InputStreamReader inputStreamReader) throws IOException, JSONException {
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        final StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        inputStreamReader.close();
        return new JSONObject(sb.toString());
    }
    
    public static JSONObject setUploadTaskParam(final UploadTaskParam uploadTaskParam) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", uploadTaskParam.getTaskID());
        jsonObject.put("status", uploadTaskParam.getStatus());
        jsonObject.put("name", uploadTaskParam.getTaskFile().getName());
        jsonObject.put("size", uploadTaskParam.getSize());
        jsonObject.put("isdir", uploadTaskParam.getTaskFile().isDirectory());
        jsonObject.put("remotedir", uploadTaskParam.getRemoteDir());
        jsonObject.put("timeLeft", uploadTaskParam.getTimeAndSpaceParam().getLeftTime());
        jsonObject.put("rate", uploadTaskParam.getTimeAndSpaceParam().getVelocity());
        jsonObject.put("bytesTotal", uploadTaskParam.getSize());
        jsonObject.put("bytesLoaded", uploadTaskParam.getTimeAndSpaceParam().getUploadBytes());
        jsonObject.put("response", uploadTaskParam.getJSONResponese());
        jsonObject.put("curname", uploadTaskParam.getCurrentFileName());
        if (null != uploadTaskParam.getJSONResponese()) {
            jsonObject.put("isSkip", uploadTaskParam.getJSONResponese().optBoolean("isSkip"));
        }
        return jsonObject;
    }
    
    public static JSONObject setUploadFile(final String s, final Vector<File> vector, final boolean b) {
        final JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < vector.size(); ++i) {
                jsonArray.put(vector.get(i).getAbsolutePath());
            }
            jsonObject.put("files", jsonArray);
            jsonObject.put("remoteDir", s);
            jsonObject.put("blOverwrite", b);
        }
        catch (JSONException ex) {
            FileStationHandler.log(ex);
        }
        return jsonObject;
    }
    
    public static JSONObject setSelectError(final Vector<File> vector) {
        final JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < vector.size(); ++i) {
                jsonArray.put(vector.get(i).getName());
            }
            jsonObject.put("files", jsonArray);
        }
        catch (JSONException ex) {
            FileStationHandler.log(ex);
        }
        return jsonObject;
    }
    
    static {
        JSONUtil._TYPE_LOCAL = "local";
        JSONUtil._TYPE_LOCAL_HOME = "localh";
    }
}
