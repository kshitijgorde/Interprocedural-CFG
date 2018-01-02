// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.sharklet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.twmacinta.util.MD5;
import java.io.File;

public class FileHash
{
    public static String computeHashForFile(final File file) {
        final byte[] byteHash = mp3ByteHash(file);
        return MD5.asHex(byteHash);
    }
    
    private static long mp3StartByte(final InputStream mp3Stream) throws IOException {
        final byte[] id3header = new byte[4];
        int size = -10;
        mp3Stream.read(id3header, 0, 3);
        if (id3header[0] == 73 && id3header[1] == 68 && id3header[2] == 51) {
            mp3Stream.read(id3header, 0, 3);
            mp3Stream.read(id3header, 0, 4);
            size = (id3header[0] << 21) + (id3header[1] << 14) + (id3header[2] << 7) + id3header[3];
        }
        return size + 10;
    }
    
    private static byte[] mp3ByteHash(final File mp3File) {
        final int buf = 8192;
        final int id3 = 128;
        FileInputStream mp3Stream = null;
        final MD5 md5 = new MD5();
        try {
            mp3Stream = new FileInputStream(mp3File);
            final long fileLength = mp3File.length();
            byte[] buffer = new byte[buf];
            md5.Init();
            final long startByte = mp3StartByte(mp3Stream);
            mp3Stream.close();
            mp3Stream = new FileInputStream(mp3File);
            mp3Stream.skip(startByte);
            int read;
            long curByte;
            for (read = 0, curByte = startByte; curByte + buf < fileLength - id3; curByte += read) {
                read = mp3Stream.read(buffer);
                md5.Update(buffer, read);
            }
            final Long tempLong = new Long(fileLength - curByte - id3);
            buffer = new byte[(int)(Object)tempLong];
            read = mp3Stream.read(buffer);
            md5.Update(buffer, read);
            buffer = new byte[3];
            read = mp3Stream.read(buffer);
            final String tag = new String(buffer);
            if (!tag.equals("TAG")) {
                md5.Update(buffer, read);
                buffer = new byte[id3 - read];
                while (read != -1) {
                    read = mp3Stream.read(buffer);
                    if (read != -1) {
                        md5.Update(buffer, read);
                    }
                }
            }
            return md5.Final();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (mp3Stream != null) {
                try {
                    mp3Stream.close();
                }
                catch (IOException ex) {}
            }
        }
    }
}
