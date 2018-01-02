// 
// Decompiled by Procyon v0.5.30
// 

package action.list;

import java.util.Collection;
import fileutil.filecomparator.FileNameComparator;
import fileutil.filecomparator.FileTypeComparator;
import fileutil.filecomparator.FileMTIMEComparator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import fileutil.filecomparator.FileSizeComparator;
import fileutil.filefilter.FileNameAndTypeFilter;
import java.io.File;
import fileutil.filefilter.SFileFilter;
import fileutil.filefilter.FileEntryFilter;
import java.io.IOException;
import fileutil.SpaceUtil;
import org.json.JSONException;
import jsonutil.JSONUtil;
import org.json.JSONObject;
import fileutil.SFile;
import java.util.Vector;

public class ListHandler
{
    private static final String[] LIST_TYPE_STRING;
    public static final String FILE_NAME = "filename";
    public static final String FILE_SIZE = "filesize";
    public static final String FILE_MTIME = "mt";
    public static final String FILE_CTIME = "ct";
    public static final String FILE_ATIME = "at";
    public static final String FILE_OWNER = "owner";
    public static final String FILE_GROUP = "group";
    public static final String FILE_PRIVILEGE = "privilege";
    public static final String FILE_FILETYPE = "type";
    private int LIST_START;
    private int LIST_LIMIT;
    private Vector<SFile> _Files;
    
    public ListHandler() {
        this.LIST_START = 0;
        this.LIST_LIMIT = 50;
        this._Files = new Vector<SFile>();
    }
    
    public JSONObject getHostName() throws JSONException {
        return JSONUtil.setHostName();
    }
    
    public Object getUserPath() throws JSONException {
        return JSONUtil.setUserPath(System.getProperty("user.home"));
    }
    
    public JSONObject getSpaceInfo(final JSONObject jsonObject) throws JSONException {
        final String optString;
        if (null == (optString = jsonObject.optString("cwd", null))) {
            throw new IllegalArgumentException();
        }
        return JSONUtil.setDiskinformation(new SpaceUtil(optString));
    }
    
    public Object enumDirHandler(final JSONObject jsonObject) throws JSONException, NullPointerException, IOException {
        SFile sFile = null;
        final String optString;
        if (null == (optString = jsonObject.optString("node", null))) {
            throw new IllegalArgumentException();
        }
        if (optString.equals("local_fm_root")) {
            this.getRoot();
            this.sortDirAndFileList("filename", true, true);
            final String property;
            if (null != (property = System.getProperty("user.home"))) {
                sFile = new SFile(property);
            }
            return JSONUtil.setRootTree(this._Files, sFile);
        }
        this.enumDirList(optString);
        this.sortDirAndFileList("filename", true, false);
        return JSONUtil.setDirTree(this._Files, Boolean.valueOf(jsonObject.optString("isHome", null)));
    }
    
    private void enumDirList(final String s) throws SecurityException, NullPointerException, IOException {
        this._Files = new SFile(s).listFileVector(new FileEntryFilter());
    }
    
    public JSONObject enumDirAndFileHandler(final JSONObject jsonObject) throws JSONException, NullPointerException, IOException {
        final String optString;
        if (null == (optString = jsonObject.optString("target", null))) {
            throw new IllegalArgumentException();
        }
        final int optInt = jsonObject.optInt("start", this.LIST_START);
        final int optInt2 = jsonObject.optInt("limit", this.LIST_LIMIT);
        boolean b = true;
        if (jsonObject.optString("dir").equals("DESC")) {
            b = false;
        }
        final String optString2 = jsonObject.optString("sort");
        final String optString3 = jsonObject.optString("need");
        LIST_TYPE list_TYPE;
        if (optString3.equals(ListHandler.LIST_TYPE_STRING[1])) {
            list_TYPE = LIST_TYPE.DIR_TYPE;
        }
        else if (optString3.equals(ListHandler.LIST_TYPE_STRING[2])) {
            list_TYPE = LIST_TYPE.FILE_TYPE;
        }
        else {
            list_TYPE = LIST_TYPE.ALL_TYPE;
        }
        final int filterDirAndFileList = this.filterDirAndFileList(optString, optInt, optInt2, optString2, b, list_TYPE, jsonObject.optString("query"));
        if (optString.equals("local_fm_root")) {
            return JSONUtil.setRootGrid(this._Files, filterDirAndFileList);
        }
        return JSONUtil.setDirAndFileListGrid(this._Files, filterDirAndFileList);
    }
    
    private int filterDirAndFileList(final String s, final int n, final int n2, final String s2, final boolean b, final LIST_TYPE list_TYPE, final String s3) throws JSONException, NullPointerException, IOException {
        if (null == s || 0 > n || 0 >= n2 || null == s2) {
            throw new IllegalArgumentException();
        }
        int n3;
        if (s.equals("local_fm_root")) {
            n3 = this.getRoot();
            this.sortDirAndFileList(s2, b, true);
        }
        else {
            final SFile sFile = new SFile(s);
            if (!sFile.canRead() || (!sFile.isFile() && !sFile.isDirectory())) {
                throw new NullPointerException();
            }
            n3 = this.enumeDirAndFileList(sFile, list_TYPE, s3);
            this.sortDirAndFileList(s2, b, false);
        }
        this.sliceDirAndFileList(n, n2);
        return n3;
    }
    
    private int getRoot() {
        final File[] listRoots = File.listRoots();
        if (null == listRoots) {
            return 0;
        }
        for (int i = 0; i < listRoots.length; ++i) {
            this._Files.add(new SFile(listRoots[i]));
        }
        return this._Files.size();
    }
    
    private int enumeDirAndFileList(final SFile sFile, final LIST_TYPE list_TYPE, final String s) throws SecurityException, NullPointerException, IOException {
        this._Files = sFile.listFileVector(new FileNameAndTypeFilter(list_TYPE, s));
        return (null == this._Files) ? 0 : this._Files.size();
    }
    
    private void sortDirAndFileList(final String s, final boolean b, final boolean b2) {
        if (s.equals("filesize")) {
            Collections.sort(this._Files, new FileSizeComparator(b, b2));
        }
        else if (s.equals("mt")) {
            Collections.sort(this._Files, new FileMTIMEComparator(b));
        }
        else if (s.equals("type")) {
            Collections.sort(this._Files, new FileTypeComparator(b, b2));
        }
        else {
            Collections.sort(this._Files, new FileNameComparator(b));
        }
    }
    
    private void sliceDirAndFileList(final int n, final int n2) {
        if (1 > this._Files.size()) {
            return;
        }
        final int n3 = n + n2;
        this._Files = new Vector<SFile>(this._Files.subList(n, (n3 > this._Files.size()) ? this._Files.size() : n3));
    }
    
    static {
        LIST_TYPE_STRING = new String[] { "all", "dir", "file" };
    }
    
    public enum LIST_TYPE
    {
        ALL_TYPE, 
        DIR_TYPE, 
        FILE_TYPE;
    }
}
