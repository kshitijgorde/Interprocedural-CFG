// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.io.FileOutputStream;
import java.net.URL;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.ArrayList;

public class C64Reader
{
    public static final int NONE = 0;
    public static final int TAPE = 1;
    public static final int DISK = 2;
    private CPU cpu;
    private int[] memory;
    private String label;
    private ArrayList dirNames;
    private Hashtable dirEntries;
    private DiskListener listener;
    private int type;
    private byte[][] sectors;
    private int noBytes;
    private boolean lastEntry;
    private int nextSector;
    private int nextTrack;
    
    public C64Reader() {
        this.label = "";
        this.dirNames = new ArrayList();
        this.dirEntries = new Hashtable();
        this.type = 0;
        this.sectors = new byte[800][256];
    }
    
    public void setCPU(final CPU cpu) {
        this.cpu = cpu;
        this.memory = cpu.getMemory();
    }
    
    public void setDiskListener(final DiskListener listener) {
        this.listener = listener;
    }
    
    public int getLoadedType() {
        return this.type;
    }
    
    public ArrayList getDirNames() {
        return this.dirNames;
    }
    
    public DirEntry getDirEntry(final String s) {
        return this.dirEntries.get(s);
    }
    
    public boolean readDisk(final InputStream inputStream) {
        int n = 0;
        int n2 = 0;
        int n3 = 1;
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.dirNames.clear();
        this.type = 2;
        try {
            int read;
            while ((read = dataInputStream.read(this.sectors[n])) > 0) {
                if (read < 256) {
                    dataInputStream.readFully(this.sectors[n], read, 256 - read);
                }
                if (n3 == 18) {
                    this.readDir(this.sectors[n], n3, n2);
                }
                ++n;
                ++n2;
                if ((n3 < 18 && n2 == 21) || (n3 >= 18 && n3 < 25 && n2 == 19) || (n3 >= 25 && n3 < 31 && n2 == 18) || (n3 >= 31 && n3 < 41 && n2 == 17)) {
                    n2 = 0;
                    ++n3;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error reading sectors");
            System.out.println("Track: " + n3 + " sec: " + n);
            ex.printStackTrace();
            return false;
        }
        finally {
            try {
                dataInputStream.close();
            }
            catch (Exception ex2) {}
        }
        if (this.listener != null) {
            this.listener.diskChanged();
        }
        return true;
    }
    
    public byte[] getSector(final int n) {
        return this.sectors[n];
    }
    
    public static int getSectorCount(final int n) {
        if (n > 30) {
            return 17;
        }
        if (n > 24) {
            return 18;
        }
        if (n > 17) {
            return 19;
        }
        return 21;
    }
    
    public byte[] getSector(final int n, final int n2) {
        int n3;
        if (n < 18) {
            n3 = (n - 1) * 21;
        }
        else if (n >= 18 && n < 25) {
            n3 = (n - 1) * 19 + 34;
        }
        else if (n >= 25 && n < 31) {
            n3 = (n - 1) * 18 + 51 + 7;
        }
        else {
            n3 = (n - 1) * 17 + 68 + 14 + 6;
        }
        return this.sectors[n3 + n2];
    }
    
    private void readDir(final byte[] array, final int nextSector, final int n) {
        if (n == 0) {
            this.label = "";
            for (int i = 0; i < 16; ++i) {
                if (array[144 + i] != -96) {
                    this.label += (char)array[144 + i];
                }
            }
            System.out.println("Directory listing of '" + this.label + "'");
            this.lastEntry = false;
            this.nextSector = nextSector;
            this.nextSector = 1;
        }
        else if (!this.lastEntry) {
            if (this.nextSector == n && array[0] == 0) {
                this.lastEntry = true;
            }
            else {
                this.nextSector = array[1];
                this.nextTrack = array[0];
            }
            for (int n2 = 0; n2 < 255 && array[n2 + 2] != 0; n2 += 32) {
                final int n3 = array[n2 + 2] & 0xFF;
                switch (n3) {
                    case 128: {}
                    case 129: {}
                    case 130: {}
                    case 131: {}
                }
                String s = "";
                for (int j = 0; j < 16; ++j) {
                    if (array[n2 + 5 + j] != -96) {
                        s += (char)array[n2 + 5 + j];
                    }
                    else {
                        s += " ";
                    }
                }
                new StringBuffer().append(array[n2 + 3]).append("/").append(array[n2 + 4]).toString();
                final DirEntry dirEntry = new DirEntry(s, array[n2 + 3], array[n2 + 4], array[n2 + 30] & 255 + array[n2 + 31] * 256, n3);
                this.dirNames.add(dirEntry);
                this.dirEntries.put(s, dirEntry);
            }
            if (this.lastEntry) {
                System.out.println("No more files.");
            }
        }
    }
    
    public String readFile(final String s) {
        return this.readFile(s, -1);
    }
    
    public String readFile(final String s, final int n) {
        return this.readFile(s, n, null);
    }
    
    private void printDirListing(final OutputStream outputStream) {
        final byte[] array = new byte[4096];
        int n = 0;
        final int n2 = 2049;
        array[n++] = (byte)(n2 & 0xFF);
        array[n++] = (byte)(n2 >> 8);
        int n3 = n2 + (this.label.length() + 5);
        array[n++] = (byte)(n3 & 0xFF);
        array[n++] = (byte)(n3 >> 8);
        array[n++] = 0;
        array[n++] = 0;
        array[n++] = 18;
        array[n++] = 34;
        for (int i = 0; i < this.label.length(); ++i) {
            array[n++] = (byte)this.label.charAt(i);
        }
        array[n++] = 34;
        array[n++] = 0;
        for (int j = 0; j < this.dirNames.size(); ++j) {
            final DirEntry dirEntry = this.dirNames.get(j);
            n3 += 26;
            int n4 = 1;
            if (dirEntry.size < 10) {
                n4 = 3;
            }
            else if (dirEntry.size < 100) {
                n4 = 2;
            }
            n3 += n4;
            array[n++] = (byte)(n3 & 0xFF);
            array[n++] = (byte)(n3 >> 8);
            array[n++] = (byte)(dirEntry.size & 0xFF);
            array[n++] = (byte)(dirEntry.size >> 8);
            for (int k = 0; k < n4; ++k) {
                array[n++] = 32;
            }
            for (int l = 0; l < dirEntry.name.length(); ++l) {
                array[n++] = (byte)dirEntry.name.charAt(l);
            }
            for (int n5 = 0; n5 < 18 - dirEntry.name.length(); ++n5) {
                array[n++] = 32;
            }
            final String typeString = dirEntry.getTypeString();
            for (int n6 = 0; n6 < typeString.length(); ++n6) {
                array[n++] = (byte)typeString.charAt(n6);
            }
            array[n++] = 0;
        }
        array[n++] = 0;
        array[n++] = 0;
        try {
            outputStream.write(array, 0, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String readFile(String string, final int n, final OutputStream outputStream) {
        DirEntry dirEntry = null;
        System.out.println("Loading: '" + string + "' at " + n);
        final int index;
        if ((index = string.indexOf(42)) >= 0) {
            final String substring = string.substring(0, index);
            System.out.println("Matcher: " + substring);
            final Enumeration<String> keys = (Enumeration<String>)this.dirEntries.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                if (s.startsWith(substring)) {
                    System.out.println("Found: " + s);
                    dirEntry = (DirEntry)this.dirEntries.get(s);
                }
            }
        }
        else {
            if (string.equals("$")) {
                this.printDirListing(outputStream);
                return string;
            }
            dirEntry = this.dirEntries.get(string);
            if (dirEntry == null) {
                for (int i = string.length(); i < 16; ++i) {
                    string += ' ';
                }
                dirEntry = this.dirEntries.get(string);
            }
        }
        if (dirEntry == null) {
            return null;
        }
        if (this.type == 1) {
            return this.readTapeFile(dirEntry);
        }
        return this.readDiskFile(dirEntry, string, n, outputStream);
    }
    
    public String readDiskFile(final DirEntry dirEntry, final String s, final int n, final OutputStream outputStream) {
        final byte[] sector = this.getSector(dirEntry.trk, dirEntry.sec);
        int n2 = (sector[2] & 0xFF) + (sector[3] & 0xFF) * 256;
        System.out.println("*** Reading DISK file at " + Integer.toString(n2, 16));
        if (outputStream != null) {
            try {
                outputStream.write(sector[2] & 0xFF);
                outputStream.write(sector[3] & 0xFF);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (n != -1) {
            System.out.println("Address override: " + n2 + " -> " + n);
            n2 = n;
        }
        int n3 = sector[1] & 0xFF;
        int n4 = sector[0] & 0xFF;
        try {
            for (int i = 0; i < 252; ++i) {
                if (outputStream != null) {
                    outputStream.write(sector[i + 4] & 0xFF);
                }
                else {
                    this.memory[i + n2] = (sector[i + 4] & 0xFF);
                }
            }
        }
        catch (Exception ex2) {
            System.out.println("Could not write to output stream");
            ex2.printStackTrace();
        }
        int n5 = n2 + 252;
        boolean b = n4 != 0;
        while (b) {
            final byte[] sector2 = this.getSector(n4, n3);
            n3 = (sector2[1] & 0xFF);
            n4 = (sector2[0] & 0xFF);
            b = (n4 != 0);
            try {
                for (int j = 0; j < 254; ++j) {
                    if (outputStream != null) {
                        outputStream.write(sector2[j + 2] & 0xFF);
                    }
                    else {
                        this.memory[j + n5] = (sector2[j + 2] & 0xFF);
                    }
                }
            }
            catch (Exception ex3) {
                System.out.println("Could not write to output stream");
                ex3.printStackTrace();
            }
            n5 += 254;
        }
        final int address = n5 - 255 + n3;
        if (outputStream == null) {
            this.setAddress(address);
        }
        System.out.println("*** File loaded - end at: " + Integer.toString(address, 16));
        return dirEntry.name;
    }
    
    public String saveFile() {
        final int n = this.memory[43] + (this.memory[44] << 8);
        final int n2 = this.memory[45] + (this.memory[46] << 8);
        System.out.println("Dumping mem from: " + n + " to " + n2);
        final StringBuffer sb = new StringBuffer();
        if ((n & 0xFF) < 16) {
            sb.append('0');
        }
        sb.append(Integer.toString(n & 0xFF));
        if (n >> 8 < 16) {
            sb.append('0');
        }
        sb.append(Integer.toString(n >> 8));
        for (int i = n; i < n2; ++i) {
            if (this.memory[i] < 16) {
                sb.append('0');
            }
            sb.append(Integer.toString(this.memory[i], 16));
        }
        return sb.toString();
    }
    
    private void setAddress(int n) {
        if (n > 40704) {
            n = 40704;
        }
        this.memory[45] = (n & 0xFF);
        this.memory[46] = (n & 0xFF00) >> 8;
        this.memory[47] = (n & 0xFF);
        this.memory[48] = (n & 0xFF00) >> 8;
        this.memory[49] = (n & 0xFF);
        this.memory[50] = (n & 0xFF00) >> 8;
    }
    
    private String readTapeFile(final DirEntry dirEntry) {
        int sec = dirEntry.sec;
        final int trk = dirEntry.trk;
        System.out.println("Reading from " + trk);
        System.out.println("Storing at: " + sec);
        System.out.println("Size: " + dirEntry.size);
        for (int i = 0; i < dirEntry.size; ++i) {
            this.memory[sec++] = (this.sectors[i + trk >> 8][i + trk & 0xFF] & 0xFF);
        }
        this.setAddress(sec);
        return dirEntry.name;
    }
    
    private boolean readPGM(final InputStream inputStream, int address) {
        final byte[] array = new byte[2];
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(array);
            int n;
            int sector;
            for (n = 0; (sector = this.readSector(dataInputStream, n)) == 256; ++n) {}
            final int n2 = n * 256 + sector;
            System.out.println("Read " + n2 + " program data");
            if (address == -1) {
                address = array[0] + array[1] * 256;
            }
            System.out.println("Storing at: " + address);
            for (int i = 0; i < n2; ++i) {
                this.memory[address++] = (this.sectors[i >> 8][i & 0xFF] & 0xFF);
            }
            this.setAddress(address);
            return true;
        }
        catch (Exception ex) {
            System.out.println("Error while reading pgm file " + ex);
            return false;
        }
    }
    
    private boolean readTape(final InputStream inputStream) {
        final byte[] array = new byte[32];
        this.dirNames.clear();
        this.type = 1;
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(array);
            if (new String(array).startsWith("C64")) {
                System.out.println("Tape Archive found:");
                dataInputStream.readFully(array);
                final int n = array[3] * 256 + array[2];
                int n2 = array[5] * 256 + array[4];
                if (n2 == 0) {
                    n2 = 1;
                }
                String string = "";
                System.out.println("Type: " + array[0] * 256 + array[1]);
                System.out.println("Max Entries: " + n);
                System.out.println("Used Entries: " + n2);
                for (int i = 8; i < 32; ++i) {
                    string += (char)array[i];
                }
                System.out.println("Name: " + string);
                for (int j = 0; j < n2; ++j) {
                    dataInputStream.readFully(array);
                    final int n3 = (array[2] & 0xFF) + (array[3] & 0xFF) * 256;
                    final int n4 = (array[4] & 0xFF) + (array[5] & 0xFF) * 256;
                    final int n5 = (array[8] & 0xFF) + (array[9] & 0xFF) * 256;
                    System.out.println("---------------------");
                    System.out.println("Entry: " + j);
                    System.out.println("File Type: " + array[0]);
                    System.out.println("1541 Type: " + array[1]);
                    System.out.println("Start Adr: " + n3);
                    System.out.println("End Adr: " + n4 + " -> size = " + (n4 - n3));
                    System.out.println("Offset: " + n5);
                    String string2 = "";
                    for (int k = 16; k < 32; ++k) {
                        string2 += (char)array[k];
                    }
                    System.out.println("File Name: " + string2);
                    this.dirNames.add(string2);
                    this.dirEntries.put(string2, new DirEntry(string2, n5 - 32 * (n + 2), n3, n4 - n3, array[1]));
                }
                for (int l = n2; l < n; ++l) {
                    dataInputStream.readFully(array);
                }
                int n6;
                int sector;
                for (n6 = 0; (sector = this.readSector(dataInputStream, n6)) == 256; ++n6) {}
                this.noBytes = n6 * 256 + sector;
                System.out.println("Read " + this.noBytes + " program data");
            }
            return true;
        }
        catch (Exception ex) {
            System.out.println("Error while reading tape");
            return false;
        }
    }
    
    private boolean readSID(final InputStream inputStream) {
        final byte[] array = new byte[22];
        try {
            final DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(array);
            if (new String(array).startsWith("PSID")) {
                final int n = array[4] * 256 + array[5];
                final int n2 = array[6] * 256 + array[7];
                final int n3 = array[8] * 256 + array[9];
                final int n4 = array[10] * 256 + array[11];
                final int n5 = array[12] * 256 + array[13];
                final int n6 = array[14] * 256 + array[15];
                final int n7 = array[16] * 256 + array[17];
                final long n8 = (array[18] << 24) + (array[19] << 16) + (array[20] << 8) + array[21];
                System.out.println("FOUND SID TUNE!");
                System.out.println("Version: " + n);
                System.out.println("LAddr: " + n3);
                System.out.println("IAddr: " + n4);
                System.out.println("PAddr: " + n5);
                System.out.println("Songs: " + n6);
                System.out.println("StartSong: " + n7);
                System.out.println("Speed: " + n8);
                final byte[] array2 = new byte[32];
                for (int i = 0; i < n6; ++i) {
                    dataInputStream.readFully(array2);
                    System.out.println("Song " + (i + 1));
                    System.out.println("Name      :" + new String(array2));
                    dataInputStream.readFully(array2);
                    System.out.println("Author    :" + new String(array2));
                    dataInputStream.readFully(array2);
                    System.out.println("Copyright :" + new String(array2));
                }
                if (n == 2) {
                    dataInputStream.readFully(new byte[6]);
                }
                final byte[] array3 = new byte[2];
                dataInputStream.readFully(array3);
                final int n9 = array3[1] * 256 + array3[0];
            }
        }
        catch (Exception ex) {
            System.out.println("Error while reading SID");
        }
        return false;
    }
    
    private int readSector(final DataInputStream dataInputStream, final int n) {
        int i = 0;
        try {
            int read;
            for (i = dataInputStream.read(this.sectors[n]); i < 256; i += read) {
                read = dataInputStream.read(this.sectors[n], i, 256 - i);
                if (read == -1) {
                    return i;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception while reading file... " + ex);
        }
        return i;
    }
    
    public boolean readDiskFromFile(final String s) {
        try {
            System.out.println("Loading " + s);
            return this.readDisk(new FileInputStream(s));
        }
        catch (Exception ex) {
            System.out.println("Error while opening file " + s + "  " + ex);
            return false;
        }
    }
    
    public boolean readPGM(final URL url, final int n) {
        try {
            return this.readPGM(url.openConnection().getInputStream(), n);
        }
        catch (Exception ex) {
            System.out.println("Error when opening url " + url + "  " + ex);
            return false;
        }
    }
    
    public boolean readPGM(final String s, final int n) {
        try {
            return this.readPGM(new FileInputStream(s), n);
        }
        catch (Exception ex) {
            System.out.println("Error when opening file " + s + "  " + ex);
            return false;
        }
    }
    
    public boolean readDiskFromURL(final URL url) {
        try {
            System.out.println("Loading from " + url);
            return this.readDisk(url.openConnection().getInputStream());
        }
        catch (Exception ex) {
            System.out.println("Error when opening url " + url + "  " + ex);
            return false;
        }
    }
    
    public boolean readTapeFromFile(final String s) {
        try {
            System.out.println("Loading " + s);
            return this.readTape(new FileInputStream(s));
        }
        catch (Exception ex) {
            System.out.println("Error while opening file " + s + "  " + ex);
            return false;
        }
    }
    
    public boolean readTapeFromURL(final URL url) {
        try {
            System.out.println("Loading from " + url);
            return this.readTape(url.openConnection().getInputStream());
        }
        catch (Exception ex) {
            System.out.println("Error when opening url " + url + "  " + ex);
            return false;
        }
    }
    
    public boolean readSIDFromFile(final String s) {
        try {
            System.out.println("Loading SID " + s);
            return this.readSID(new FileInputStream(s));
        }
        catch (Exception ex) {
            System.out.println("Error while opening file " + s + "  " + ex);
            return false;
        }
    }
    
    public static void main(final String[] array) {
        final C64Reader c64Reader = new C64Reader();
        c64Reader.readDiskFromFile(array[0]);
        if (array.length > 1) {
            try {
                c64Reader.readFile(array[1], 0, new FileOutputStream(array[2]));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            c64Reader.printDirListing(System.out);
        }
    }
}
