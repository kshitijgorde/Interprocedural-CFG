// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import org.jruby.platform.Platform;
import org.jruby.RubyEncoding;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.Arrays;
import java.util.zip.ZipFile;
import org.jruby.RubyFile;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import org.jruby.ext.posix.JavaSecuredFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dir
{
    public static final boolean DOSISH;
    public static final boolean CASEFOLD_FILESYSTEM;
    public static final int FNM_NOESCAPE = 1;
    public static final int FNM_PATHNAME = 2;
    public static final int FNM_DOTMATCH = 4;
    public static final int FNM_CASEFOLD = 8;
    public static final int FNM_SYSCASE;
    public static final int FNM_NOMATCH = 1;
    public static final int FNM_ERROR = 2;
    public static final byte[] EMPTY;
    public static final byte[] SLASH;
    public static final byte[] STAR;
    public static final byte[] DOUBLE_STAR;
    public static final GlobFunc push_pattern;
    public static final GlobFunc glob_caller;
    
    private static boolean isdirsep(final byte c) {
        return Dir.DOSISH ? (c == 92 || c == 47) : (c == 47);
    }
    
    private static int rb_path_next(final byte[] _s, int s, final int send) {
        while (s < send && !isdirsep(_s[s])) {
            ++s;
        }
        return s;
    }
    
    private static int fnmatch_helper(final byte[] bytes, final int pstart, final int pend, final byte[] string, final int sstart, final int send, final int flags) {
        int s = sstart;
        int pat = pstart;
        final boolean escape = (flags & 0x1) == 0x0;
        final boolean pathname = (flags & 0x2) != 0x0;
        final boolean period = (flags & 0x4) == 0x0;
        final boolean nocase = (flags & 0x8) != 0x0;
        while (pat < pend) {
            byte c = bytes[pat++];
            switch (c) {
                case 63: {
                    if (s >= send || (pathname && isdirsep(string[s])) || (period && string[s] == 46 && (s == 0 || (pathname && isdirsep(string[s - 1]))))) {
                        return 1;
                    }
                    ++s;
                    continue;
                }
                case 42: {
                    while (pat < pend && (c = bytes[pat++]) == 42) {}
                    if (s < send && period && string[s] == 46 && (s == 0 || (pathname && isdirsep(string[s - 1])))) {
                        return 1;
                    }
                    if (pat > pend || (pat == pend && c == 42)) {
                        if (pathname && rb_path_next(string, s, send) < send) {
                            return 1;
                        }
                        return 0;
                    }
                    else {
                        if (!pathname || !isdirsep(c)) {
                            char test = (char)(((escape && c == 92 && pat < pend) ? bytes[pat] : c) & 0xFF);
                            test = Character.toLowerCase(test);
                            --pat;
                            while (s < send) {
                                if ((c == 63 || c == 91 || Character.toLowerCase((char)string[s]) == test) && fnmatch(bytes, pat, pend, string, s, send, flags | 0x4) == 0) {
                                    return 0;
                                }
                                if (pathname && isdirsep(string[s])) {
                                    break;
                                }
                                ++s;
                            }
                            return 1;
                        }
                        s = rb_path_next(string, s, send);
                        if (s < send) {
                            ++s;
                            continue;
                        }
                        return 1;
                    }
                    break;
                }
                case 91: {
                    if (s >= send || (pathname && isdirsep(string[s])) || (period && string[s] == 46 && (s == 0 || (pathname && isdirsep(string[s - 1]))))) {
                        return 1;
                    }
                    pat = range(bytes, pat, pend, (char)(string[s] & 0xFF), flags);
                    if (pat == -1) {
                        return 1;
                    }
                    ++s;
                    continue;
                }
                case 92: {
                    if (!escape) {
                        break;
                    }
                    if (pat >= pend) {
                        c = 92;
                        break;
                    }
                    c = bytes[pat++];
                    break;
                }
            }
            if (s >= send) {
                return 1;
            }
            if (!Dir.DOSISH || !pathname || !isdirsep(c) || !isdirsep(string[s])) {
                if (nocase) {
                    if (Character.toLowerCase((char)c) != Character.toLowerCase((char)string[s])) {
                        return 1;
                    }
                }
                else if (c != (char)string[s]) {
                    return 1;
                }
            }
            ++s;
        }
        return (s < send) ? 1 : 0;
    }
    
    public static int fnmatch(final byte[] bytes, final int pstart, final int pend, final byte[] string, final int sstart, final int send, final int flags) {
        final boolean period = (flags & 0x4) == 0x0;
        final boolean pathname = (flags & 0x2) != 0x0;
        int pat_pos = pstart;
        int str_pos = sstart;
        int ptmp = -1;
        int stmp = -1;
        if (pathname) {
            while (true) {
                if (isDoubleStarAndSlash(bytes, pat_pos)) {
                    do {
                        pat_pos += 3;
                    } while (isDoubleStarAndSlash(bytes, pat_pos));
                    ptmp = pat_pos;
                    stmp = str_pos;
                }
                int patSlashIdx = nextSlashIndex(bytes, pat_pos, pend);
                int strSlashIdx = nextSlashIndex(string, str_pos, send);
                if (fnmatch_helper(bytes, pat_pos, patSlashIdx, string, str_pos, strSlashIdx, flags) == 0) {
                    if (patSlashIdx < pend && strSlashIdx < send) {
                        pat_pos = ++patSlashIdx;
                        str_pos = ++strSlashIdx;
                        continue;
                    }
                    if (patSlashIdx == pend && strSlashIdx == send) {
                        return 0;
                    }
                }
                if (ptmp == -1 || stmp == -1 || (period && string[stmp] == 46)) {
                    break;
                }
                stmp = nextSlashIndex(string, stmp, send);
                if (stmp >= send) {
                    break;
                }
                pat_pos = ptmp;
                str_pos = ++stmp;
            }
            return 1;
        }
        return fnmatch_helper(bytes, pstart, pend, string, sstart, send, flags);
    }
    
    private static boolean isDoubleStarAndSlash(final byte[] bytes, final int pos) {
        return bytes.length - pos > 2 && bytes[pos] == 42 && bytes[pos + 1] == 42 && bytes[pos + 2] == 47;
    }
    
    private static int nextSlashIndex(final byte[] bytes, final int start, final int end) {
        int idx;
        for (idx = start; idx < end && idx < bytes.length && bytes[idx] != 47; ++idx) {}
        return idx;
    }
    
    public static int range(final byte[] _pat, int pat, final int pend, char test, final int flags) {
        boolean ok = false;
        final boolean nocase = (flags & 0x8) != 0x0;
        final boolean escape = (flags & 0x1) == 0x0;
        final boolean not = _pat[pat] == 33 || _pat[pat] == 94;
        if (not) {
            ++pat;
        }
        if (nocase) {
            test = Character.toLowerCase(test);
        }
        while (_pat[pat] != 93) {
            if (escape && _pat[pat] == 92) {
                ++pat;
            }
            if (pat >= pend) {
                return -1;
            }
            final char cstart;
            char cend = cstart = (char)(_pat[pat++] & 0xFF);
            if (_pat[pat] == 45 && _pat[pat + 1] != 93) {
                ++pat;
                if (escape && _pat[pat] == 92) {
                    ++pat;
                }
                if (pat >= pend) {
                    return -1;
                }
                cend = (char)(_pat[pat++] & 0xFF);
            }
            if (nocase) {
                if (Character.toLowerCase(cstart) > test || test > Character.toLowerCase(cend)) {
                    continue;
                }
                ok = true;
            }
            else {
                if (cstart > test || test > cend) {
                    continue;
                }
                ok = true;
            }
        }
        return (ok == not) ? -1 : (pat + 1);
    }
    
    public static List<ByteList> push_glob(final String cwd, final ByteList globByteList, final int flags) {
        final List<ByteList> result = new ArrayList<ByteList>();
        if (globByteList.length() > 0) {
            push_braces(cwd, result, new GlobPattern(globByteList, flags));
        }
        return result;
    }
    
    private static int push_braces(final String cwd, final List<ByteList> result, final GlobPattern pattern) {
        pattern.reset();
        final int lbrace = pattern.indexOf((byte)123);
        final int rbrace = pattern.findClosingIndexOf(lbrace);
        if (lbrace == -1 || rbrace == -1) {
            return push_globs(cwd, result, pattern);
        }
        final ByteList buf = new ByteList(20);
        int i = lbrace;
        while (pattern.bytes[i] != 125) {
            int middleRegionIndex;
            for (middleRegionIndex = ++i; i < pattern.end && pattern.bytes[i] != 125 && pattern.bytes[i] != 44; ++i) {
                if (pattern.bytes[i] == 123) {
                    i = pattern.findClosingIndexOf(i);
                }
            }
            buf.length(0);
            buf.append(pattern.bytes, pattern.begin, lbrace - pattern.begin);
            buf.append(pattern.bytes, middleRegionIndex, i - middleRegionIndex);
            buf.append(pattern.bytes, rbrace + 1, pattern.end - (rbrace + 1));
            final int status = push_braces(cwd, result, new GlobPattern(buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize(), pattern.flags));
            if (status != 0) {
                return status;
            }
        }
        return 0;
    }
    
    private static int push_globs(final String cwd, final List<ByteList> ary, final GlobPattern pattern) {
        pattern.flags |= Dir.FNM_SYSCASE;
        return glob_helper(cwd, pattern.bytes, pattern.begin, pattern.end, -1, pattern.flags, Dir.glob_caller, new GlobArgs(Dir.push_pattern, ary));
    }
    
    private static boolean has_magic(final byte[] bytes, final int begin, final int end, final int flags) {
        final boolean escape = (flags & 0x1) == 0x0;
        final boolean nocase = (flags & 0x8) != 0x0;
        int open = 0;
        for (int i = begin; i < end; ++i) {
            switch (bytes[i]) {
                case 42:
                case 63: {
                    return true;
                }
                case 91: {
                    ++open;
                    break;
                }
                case 93: {
                    if (open > 0) {
                        return true;
                    }
                    break;
                }
                case 92: {
                    if (escape && i == end) {
                        return false;
                    }
                    break;
                }
                default: {
                    if (Dir.FNM_SYSCASE == 0 && nocase && Character.isLetter((char)(bytes[i] & 0xFF))) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private static int remove_backslashes(final byte[] bytes, int index, final int len) {
        int t;
        for (t = index; index < len && (bytes[index] != 92 || ++index != len); ++index, ++t) {
            bytes[t] = bytes[index];
        }
        return t;
    }
    
    private static int strchr(final byte[] bytes, final int begin, final int end, final byte ch) {
        for (int i = begin; i < end; ++i) {
            if (bytes[i] == ch) {
                return i;
            }
        }
        return -1;
    }
    
    private static byte[] extract_path(final byte[] bytes, final int begin, final int end) {
        int len = end - begin;
        if (len > 1 && bytes[end - 1] == 47 && (!Dir.DOSISH || len < 2 || bytes[end - 2] != 58)) {
            --len;
        }
        final byte[] alloc = new byte[len];
        System.arraycopy(bytes, begin, alloc, 0, len);
        return alloc;
    }
    
    private static byte[] extract_elem(final byte[] bytes, final int begin, final int end) {
        int elementEnd = strchr(bytes, begin, end, (byte)47);
        if (elementEnd == -1) {
            elementEnd = end;
        }
        return extract_path(bytes, begin, elementEnd);
    }
    
    private static boolean BASE(final byte[] base) {
        final int length = base.length;
        return Dir.DOSISH ? (length > 0 && (!isdirsep(base[0]) || length >= 2) && (length <= 2 || base[1] != 58 || !isdirsep(base[2]) || length >= 4)) : (length > 0 && (!isdirsep(base[0]) || length >= 2));
    }
    
    private static boolean isJarFilePath(final byte[] bytes, final int begin, final int end) {
        return end > 6 && bytes[begin] == 102 && bytes[begin + 1] == 105 && bytes[begin + 2] == 108 && bytes[begin + 3] == 101 && bytes[begin + 4] == 58;
    }
    
    private static boolean isAbsolutePath(final byte[] path, final int begin, final int length) {
        return path[begin] == 47 || (Dir.DOSISH && begin + 2 < length && path[begin + 1] == 58 && isdirsep(path[begin + 2]));
    }
    
    private static String[] files(final File directory) {
        final String[] files = directory.list();
        if (files != null) {
            final String[] filesPlusDotFiles = new String[files.length + 2];
            System.arraycopy(files, 0, filesPlusDotFiles, 2, files.length);
            filesPlusDotFiles[0] = ".";
            filesPlusDotFiles[1] = "..";
            return filesPlusDotFiles;
        }
        return new String[0];
    }
    
    private static int addToResultIfExists(final String cwd, byte[] bytes, int begin, int end, final int flags, final GlobFunc func, final GlobArgs arg) {
        final String fileName = newStringFromUTF8(bytes, begin, end - begin);
        final JavaSecuredFile file = (cwd != null) ? new JavaSecuredFile(cwd, fileName) : new JavaSecuredFile(fileName);
        if (file.exists()) {
            if ((flags & 0x8) != 0x0) {
                try {
                    String realName = file.getCanonicalFile().getName();
                    final int newEnd = fileName.lastIndexOf(47);
                    if (newEnd != -1) {
                        realName = fileName.substring(0, newEnd + 1) + realName;
                    }
                    bytes = realName.getBytes();
                    begin = 0;
                    end = bytes.length;
                }
                catch (Exception ex) {}
            }
            return func.call(bytes, begin, end - begin, arg);
        }
        return 0;
    }
    
    private static ZipEntry getZipEntryFor(final byte[] bytes, final int begin, final int end) {
        int ix = end;
        for (int i = 0; i < end; ++i) {
            if (bytes[begin + i] == 33) {
                ix = i;
                break;
            }
        }
        final File file = new JavaSecuredFile(newStringFromUTF8(bytes, begin + 5, ix - 5));
        try {
            String jar = newStringFromUTF8(bytes, begin + ix + 1, end - (ix + 1));
            final JarFile jf = new JarFile(file);
            if (jar.startsWith("/")) {
                jar = jar.substring(1);
            }
            return RubyFile.getDirOrFileEntry(jf, jar);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    private static int glob_helper(final String cwd, byte[] bytes, final int begin, int end, int sub, final int flags, final GlobFunc func, final GlobArgs arg) {
        int status = 0;
        byte[] newpath = null;
        int p = (sub != -1) ? sub : begin;
        if (!has_magic(bytes, p, end, flags)) {
            if (Dir.DOSISH || (flags & 0x1) == 0x0) {
                newpath = new byte[end];
                System.arraycopy(bytes, 0, newpath, 0, end);
                if (sub != -1) {
                    p = sub - begin;
                    end = remove_backslashes(newpath, p, end);
                    sub = p;
                }
                else {
                    end = remove_backslashes(newpath, 0, end);
                    bytes = newpath;
                }
            }
            if (isAbsolutePath(bytes, begin, end)) {
                status = addToResultIfExists(null, bytes, begin, end, flags, func, arg);
            }
            else if (isJarFilePath(bytes, begin, end)) {
                if (getZipEntryFor(bytes, begin, end) != null) {
                    status = func.call(bytes, begin, end, arg);
                }
            }
            else if (end - begin > 0) {
                status = addToResultIfExists(cwd, bytes, begin, end, flags, func, arg);
            }
            return status;
        }
        ByteList buf = new ByteList(20);
        final List<DirGlobber> link = new ArrayList<DirGlobber>();
        while (p != -1 && status == 0) {
            if (bytes[p] == 47) {
                ++p;
            }
            final int m = strchr(bytes, p, end, (byte)47);
            if (has_magic(bytes, p, (m == -1) ? end : m, flags)) {
                final byte[] base = extract_path(bytes, begin, p);
                final byte[] array;
                if (begin == p) {
                    array = new byte[] { 46 };
                }
                final byte[] dir = array;
                final byte[] magic = extract_elem(bytes, p, end);
                boolean recursive = false;
                String jar = null;
                JarFile jf = null;
                File st;
                if (isAbsolutePath(dir, 0, dir.length)) {
                    st = new JavaSecuredFile(newStringFromUTF8(dir));
                }
                else if (isJarFilePath(dir, 0, dir.length)) {
                    int ix = dir.length;
                    for (int i = 0; i < dir.length; ++i) {
                        if (dir[i] == 33) {
                            ix = i;
                            break;
                        }
                    }
                    st = new JavaSecuredFile(newStringFromUTF8(dir, 5, ix - 5));
                    if (ix < dir.length) {
                        jar = newStringFromUTF8(dir, ix + 1, dir.length - (ix + 1));
                        try {
                            jf = new JarFile(st);
                            if (jar.startsWith("/")) {
                                jar = jar.substring(1);
                            }
                            if (jf.getEntry(jar + "/") != null) {
                                jar += "/";
                            }
                        }
                        catch (Exception e) {
                            jar = null;
                            jf = null;
                        }
                    }
                }
                else {
                    st = new JavaSecuredFile(cwd, newStringFromUTF8(dir));
                }
                if ((jf == null || (!"".equals(jar) && (jf.getJarEntry(jar) == null || !jf.getJarEntry(jar).isDirectory()))) && !st.isDirectory()) {
                    break;
                }
                Label_1742: {
                    if (m != -1 && Arrays.equals(magic, Dir.DOUBLE_STAR)) {
                        final int n = base.length;
                        recursive = true;
                        buf.length(0);
                        buf.append(base);
                        buf.append(bytes, (base.length > 0) ? m : (m + 1), end - ((base.length > 0) ? m : (m + 1)));
                        if (jf != null) {
                            buf = fixBytesForJarInUTF8(buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize());
                        }
                        status = glob_helper(cwd, buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize(), n, flags, func, arg);
                        if (status != 0) {
                            break Label_1742;
                        }
                    }
                    if (jar == null) {
                        final String[] dirp = files(st);
                        for (int i = 0; i < dirp.length; ++i) {
                            if (recursive) {
                                final byte[] bs = getBytesInUTF8(dirp[i]);
                                if (fnmatch(Dir.STAR, 0, 1, bs, 0, bs.length, flags) == 0) {
                                    buf.length(0);
                                    buf.append(base);
                                    buf.append(BASE(base) ? Dir.SLASH : Dir.EMPTY);
                                    buf.append(getBytesInUTF8(dirp[i]));
                                    if (isAbsolutePath(buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize())) {
                                        st = new JavaSecuredFile(newStringFromUTF8(buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize()));
                                    }
                                    else {
                                        st = new JavaSecuredFile(cwd, newStringFromUTF8(buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize()));
                                    }
                                    if (st.isDirectory() && !".".equals(dirp[i]) && !"..".equals(dirp[i])) {
                                        final int t = buf.getRealSize();
                                        buf.append(Dir.SLASH);
                                        buf.append(Dir.DOUBLE_STAR);
                                        buf.append(bytes, m, end - m);
                                        status = glob_helper(cwd, buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize(), t, flags, func, arg);
                                        if (status != 0) {
                                            break;
                                        }
                                    }
                                }
                            }
                            else {
                                final byte[] bs = getBytesInUTF8(dirp[i]);
                                if (fnmatch(magic, 0, magic.length, bs, 0, bs.length, flags) == 0) {
                                    buf.length(0);
                                    buf.append(base);
                                    buf.append(BASE(base) ? Dir.SLASH : Dir.EMPTY);
                                    buf.append(getBytesInUTF8(dirp[i]));
                                    if (m == -1) {
                                        status = func.call(buf.getUnsafeBytes(), 0, buf.getRealSize(), arg);
                                        if (status != 0) {
                                            break;
                                        }
                                    }
                                    else {
                                        link.add(new DirGlobber(buf, null));
                                        buf = new ByteList(20);
                                    }
                                }
                            }
                        }
                    }
                    else {
                        try {
                            final List<JarEntry> dirp2 = new ArrayList<JarEntry>();
                            final Enumeration<JarEntry> eje = jf.entries();
                            while (eje.hasMoreElements()) {
                                final JarEntry je = eje.nextElement();
                                final String name = je.getName();
                                final int ix2 = name.indexOf(47, jar.length());
                                if ((ix2 == -1 || ix2 == name.length() - 1) && ("/".equals(jar) || (name.startsWith(jar) && name.length() > jar.length()))) {
                                    dirp2.add(je);
                                }
                            }
                            final Iterator i$ = dirp2.iterator();
                            while (i$.hasNext()) {
                                final JarEntry je = i$.next();
                                final String basename = new File(je.getName()).getName();
                                final byte[] bs2 = getBytesInUTF8(basename);
                                final byte[] absoluteName = getBytesInUTF8(je.getName());
                                int len = bs2.length;
                                int absoluteLen = absoluteName.length;
                                if (je.isDirectory()) {
                                    --len;
                                    --absoluteLen;
                                }
                                if (recursive) {
                                    if (fnmatch(Dir.STAR, 0, 1, bs2, 0, len, flags) != 0) {
                                        continue;
                                    }
                                    buf.length(0);
                                    buf.append(base, 0, base.length - jar.length());
                                    buf.append(BASE(base) ? Dir.SLASH : Dir.EMPTY);
                                    buf.append(absoluteName, 0, absoluteLen);
                                    if (!je.isDirectory()) {
                                        continue;
                                    }
                                    final int t2 = buf.getRealSize();
                                    buf.append(Dir.SLASH);
                                    buf.append(Dir.DOUBLE_STAR);
                                    buf.append(bytes, m, end - m);
                                    buf = fixBytesForJarInUTF8(buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize());
                                    status = glob_helper(cwd, buf.getUnsafeBytes(), buf.getBegin(), buf.getRealSize(), t2, flags, func, arg);
                                    if (status != 0) {
                                        break;
                                    }
                                    continue;
                                }
                                else {
                                    if (fnmatch(magic, 0, magic.length, bs2, 0, len, flags) != 0) {
                                        continue;
                                    }
                                    buf.length(0);
                                    buf.append(base, 0, base.length - jar.length());
                                    buf.append(BASE(base) ? Dir.SLASH : Dir.EMPTY);
                                    buf.append(absoluteName, 0, absoluteLen);
                                    buf = fixBytesForJarInUTF8(buf.getUnsafeBytes(), 0, buf.getRealSize());
                                    if (m == -1) {
                                        status = func.call(buf.getUnsafeBytes(), 0, buf.getRealSize(), arg);
                                        if (status != 0) {
                                            break;
                                        }
                                        continue;
                                    }
                                    else {
                                        link.add(new DirGlobber(buf, je));
                                        buf = new ByteList(20);
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                if (link.size() > 0) {
                    for (final DirGlobber globber : link) {
                        final ByteList b = globber.link;
                        if (status == 0) {
                            if (isAbsolutePath(b.getUnsafeBytes(), b.begin(), b.getRealSize())) {
                                st = new JavaSecuredFile(newStringFromUTF8(b.getUnsafeBytes(), 0, b.getRealSize()));
                            }
                            else {
                                st = new JavaSecuredFile(cwd, newStringFromUTF8(b.getUnsafeBytes(), 0, b.getRealSize()));
                            }
                            if (!st.isDirectory() && (globber.jarEntry == null || !globber.jarEntry.isDirectory())) {
                                continue;
                            }
                            final int len2 = b.getRealSize();
                            buf.length(0);
                            buf.append(b);
                            buf.append(bytes, m, end - m);
                            if (globber.jarEntry != null) {
                                buf = fixBytesForJarInUTF8(buf.getUnsafeBytes(), 0, buf.getRealSize());
                            }
                            status = glob_helper(cwd, buf.getUnsafeBytes(), 0, buf.getRealSize(), len2, flags, func, arg);
                        }
                    }
                    break;
                }
            }
            p = m;
        }
        return status;
    }
    
    private static ByteList fixBytesForJarInUTF8(final byte[] buf, final int offset, final int len) {
        String path = newStringFromUTF8(buf, offset, len);
        path = path.replace(".jar/", ".jar!");
        return new ByteList(path.getBytes());
    }
    
    private static byte[] getBytesInUTF8(final String s) {
        return RubyEncoding.encodeUTF8(s);
    }
    
    private static String newStringFromUTF8(final byte[] buf, final int offset, final int len) {
        return RubyEncoding.decodeUTF8(buf, offset, len);
    }
    
    private static String newStringFromUTF8(final byte[] buf) {
        return RubyEncoding.decodeUTF8(buf);
    }
    
    static {
        DOSISH = Platform.IS_WINDOWS;
        CASEFOLD_FILESYSTEM = Dir.DOSISH;
        FNM_SYSCASE = (Dir.CASEFOLD_FILESYSTEM ? 8 : 0);
        EMPTY = new byte[0];
        SLASH = new byte[] { 47 };
        STAR = new byte[] { 42 };
        DOUBLE_STAR = new byte[] { 42, 42 };
        push_pattern = new GlobFunc() {
            public int call(final byte[] ptr, final int p, final int len, final Object ary) {
                ((List)ary).add(new ByteList(ptr, p, len));
                return 0;
            }
        };
        glob_caller = new GlobFunc() {
            public int call(final byte[] ptr, final int p, final int len, final Object ary) {
                final GlobArgs args = (GlobArgs)ary;
                args.c = p;
                return args.func.call(ptr, args.c, len, args.v);
            }
        };
    }
    
    private static class GlobPattern
    {
        final byte[] bytes;
        final int begin;
        final int end;
        int flags;
        int index;
        
        public GlobPattern(final ByteList bytelist, final int flags) {
            this(bytelist.getUnsafeBytes(), bytelist.getBegin(), bytelist.getBegin() + bytelist.getRealSize(), flags);
        }
        
        public GlobPattern(final byte[] bytes, final int index, final int end, final int flags) {
            this.bytes = bytes;
            this.index = index;
            this.begin = index;
            this.end = end;
            this.flags = flags;
        }
        
        public int findClosingIndexOf(final int leftTokenIndex) {
            if (leftTokenIndex == -1 || leftTokenIndex > this.end) {
                return -1;
            }
            final byte leftToken = this.bytes[leftTokenIndex];
            byte rightToken = 0;
            switch (leftToken) {
                case 123: {
                    rightToken = 125;
                    break;
                }
                case 91: {
                    rightToken = 93;
                    break;
                }
                default: {
                    return -1;
                }
            }
            int nest = 1;
            this.index = leftTokenIndex + 1;
            while (this.hasNext()) {
                final byte c = this.next();
                if (c == leftToken) {
                    ++nest;
                }
                else {
                    if (c == rightToken && --nest == 0) {
                        return this.index();
                    }
                    continue;
                }
            }
            return -1;
        }
        
        public boolean hasNext() {
            return this.index < this.end;
        }
        
        public void reset() {
            this.index = this.begin;
        }
        
        public void setIndex(final int value) {
            this.index = value;
        }
        
        public int index() {
            return this.index - 1;
        }
        
        public int indexOf(final byte c) {
            while (this.hasNext()) {
                if (this.next() == c) {
                    return this.index();
                }
            }
            return -1;
        }
        
        public byte next() {
            return this.bytes[this.index++];
        }
    }
    
    private static class GlobArgs
    {
        GlobFunc func;
        int c;
        List<ByteList> v;
        
        public GlobArgs(final GlobFunc func, final List<ByteList> arg) {
            this.c = -1;
            this.func = func;
            this.v = arg;
        }
    }
    
    private static final class DirGlobber
    {
        public final ByteList link;
        public final JarEntry jarEntry;
        
        public DirGlobber(final ByteList link, final JarEntry jarEntry) {
            this.link = link;
            this.jarEntry = jarEntry;
        }
    }
    
    public interface GlobFunc
    {
        int call(final byte[] p0, final int p1, final int p2, final Object p3);
    }
}
