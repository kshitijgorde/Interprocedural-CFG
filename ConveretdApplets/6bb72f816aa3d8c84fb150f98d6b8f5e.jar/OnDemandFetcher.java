import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.CRC32;

// 
// Decompiled by Procyon v0.5.30
// 

public final class OnDemandFetcher extends OnDemandFetcherParent implements Runnable
{
    int i1;
    private int totalFiles;
    private final NodeList requested;
    private int anInt1332;
    public String statusString;
    private int writeLoopCycle;
    private long openSocketTime;
    private int[] mapIndices3;
    private final CRC32 crc32;
    private final byte[] ioBuffer;
    public int onDemandCycle;
    private final byte[][] fileStatus;
    private client clientInstance;
    private final NodeList aClass19_1344;
    private int completedSize;
    private int expectedSize;
    private int[] anIntArray1348;
    public int anInt1349;
    private int[] mapIndices2;
    private int filesLoaded;
    private boolean running;
    private OutputStream outputStream;
    private int[] mapIndices4;
    private boolean waiting;
    private final NodeList aClass19_1358;
    private final byte[] gzipInputBuffer;
    private int[] anIntArray1360;
    private final NodeSubList nodeSubList;
    private InputStream inputStream;
    private Socket socket;
    private final int[][] crcs;
    private int uncompletedCount;
    private int completedCount;
    private final NodeList aClass19_1368;
    private OnDemandData current;
    private final NodeList aClass19_1370;
    private int[] mapIndices1;
    private byte[] modelIndices;
    private int loopCycle;
    
    private void readData() {
        try {
            final int available = this.inputStream.available();
            if (this.expectedSize == 0 && available >= 6) {
                this.waiting = true;
                for (int i = 0; i < 6; i += this.inputStream.read(this.ioBuffer, i, 6 - i)) {}
                final int n = this.ioBuffer[0] & 0xFF;
                final int n2 = ((this.ioBuffer[1] & 0xFF) << 8) + (this.ioBuffer[2] & 0xFF);
                final int n3 = ((this.ioBuffer[3] & 0xFF) << 8) + (this.ioBuffer[4] & 0xFF);
                final int n4 = this.ioBuffer[5] & 0xFF;
                this.current = null;
                for (OnDemandData current = (OnDemandData)this.requested.reverseGetFirst(); current != null; current = (OnDemandData)this.requested.reverseGetNext()) {
                    if (current.dataType == n && current.ID == n2) {
                        this.current = current;
                    }
                    if (this.current != null) {
                        current.loopCycle = 0;
                    }
                }
                if (this.current != null) {
                    this.loopCycle = 0;
                    if (n3 == 0) {
                        SignLink.reporterror("Rej: " + n + "," + n2);
                        this.current.buffer = null;
                        if (this.current.incomplete) {
                            synchronized (this.aClass19_1358) {
                                this.aClass19_1358.insertHead(this.current);
                            }
                        }
                        else {
                            this.current.unlink();
                        }
                        this.current = null;
                    }
                    else {
                        if (this.current.buffer == null && n4 == 0) {
                            this.current.buffer = new byte[n3];
                        }
                        if (this.current.buffer == null && n4 != 0) {
                            throw new IOException("missing start of file");
                        }
                    }
                }
                this.completedSize = n4 * 500;
                this.expectedSize = 500;
                if (this.expectedSize > n3 - n4 * 500) {
                    this.expectedSize = n3 - n4 * 500;
                }
            }
            if (this.expectedSize > 0 && available >= this.expectedSize) {
                this.waiting = true;
                byte[] array = this.ioBuffer;
                int completedSize = 0;
                if (this.current != null) {
                    array = this.current.buffer;
                    completedSize = this.completedSize;
                }
                for (int j = 0; j < this.expectedSize; j += this.inputStream.read(array, j + completedSize, this.expectedSize - j)) {}
                if (this.expectedSize + this.completedSize >= array.length && this.current != null) {
                    if (this.clientInstance.decompressors[0] != null) {
                        this.clientInstance.decompressors[this.current.dataType + 1].method234(array.length, array, this.current.ID);
                    }
                    if (!this.current.incomplete && this.current.dataType == 3) {
                        this.current.incomplete = true;
                        this.current.dataType = 93;
                    }
                    if (this.current.incomplete) {
                        synchronized (this.aClass19_1358) {
                            this.aClass19_1358.insertHead(this.current);
                        }
                    }
                    else {
                        this.current.unlink();
                    }
                }
                this.expectedSize = 0;
            }
        }
        catch (IOException ex) {
            try {
                this.socket.close();
            }
            catch (Exception ex2) {}
            this.socket = null;
            this.inputStream = null;
            this.outputStream = null;
            this.expectedSize = 0;
        }
    }
    
    public void writeFile(final byte[] array, final String s) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(s);
        fileOutputStream.write(array);
        fileOutputStream.close();
    }
    
    public void start(final StreamLoader streamLoader, final client clientInstance) {
        final byte[] dataForName = streamLoader.getDataForName("map_index");
        final Stream stream = new Stream(dataForName);
        final int n = dataForName.length / 7;
        this.mapIndices1 = new int[n];
        this.mapIndices2 = new int[n];
        this.mapIndices3 = new int[n];
        this.mapIndices4 = new int[n];
        for (int i = 0; i < n; ++i) {
            this.mapIndices1[i] = stream.readUnsignedWord();
            this.mapIndices2[i] = stream.readUnsignedWord();
            this.mapIndices3[i] = stream.readUnsignedWord();
            this.mapIndices4[i] = stream.readUnsignedByte();
        }
        final byte[] dataForName2 = streamLoader.getDataForName("midi_index");
        final Stream stream2 = new Stream(dataForName2);
        final int length = dataForName2.length;
        this.anIntArray1348 = new int[length];
        for (int j = 0; j < length; ++j) {
            this.anIntArray1348[j] = stream2.readUnsignedByte();
        }
        this.clientInstance = clientInstance;
        this.running = true;
        this.clientInstance.startRunnable(this, 2);
    }
    
    public int getNodeCount() {
        synchronized (this.nodeSubList) {
            return this.nodeSubList.getNodeCount();
        }
    }
    
    public void disable() {
        this.running = false;
    }
    
    public int getModelCount() {
        return 41761;
    }
    
    private void closeRequest(final OnDemandData onDemandData) {
        try {
            if (this.socket == null) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.openSocketTime < 4000L) {
                    return;
                }
                this.openSocketTime = currentTimeMillis;
                this.socket = this.clientInstance.openSocket(43593);
                this.inputStream = this.socket.getInputStream();
                (this.outputStream = this.socket.getOutputStream()).write(15);
                for (int i = 0; i < 8; ++i) {
                    this.inputStream.read();
                }
                this.loopCycle = 0;
            }
            this.ioBuffer[0] = (byte)onDemandData.dataType;
            this.ioBuffer[1] = (byte)(onDemandData.ID >> 8);
            this.ioBuffer[2] = (byte)onDemandData.ID;
            if (onDemandData.incomplete) {
                this.ioBuffer[3] = 2;
            }
            else if (!this.clientInstance.loggedIn) {
                this.ioBuffer[3] = 1;
            }
            else {
                this.ioBuffer[3] = 0;
            }
            this.outputStream.write(this.ioBuffer, 0, 4);
            this.writeLoopCycle = 0;
            this.anInt1349 = -10000;
        }
        catch (IOException ex) {
            try {
                this.socket.close();
            }
            catch (Exception ex2) {}
            this.socket = null;
            this.inputStream = null;
            this.outputStream = null;
            this.expectedSize = 0;
            ++this.anInt1349;
        }
    }
    
    public int getAnimCount() {
        return 29192;
    }
    
    public void method558(final int dataType, final int id) {
        if (dataType < 0 || id < 0) {
            return;
        }
        synchronized (this.nodeSubList) {
            for (OnDemandData onDemandData = (OnDemandData)this.nodeSubList.reverseGetFirst(); onDemandData != null; onDemandData = (OnDemandData)this.nodeSubList.reverseGetNext()) {
                if (onDemandData.dataType == dataType && onDemandData.ID == id) {
                    return;
                }
            }
            final OnDemandData onDemandData2 = new OnDemandData();
            onDemandData2.dataType = dataType;
            onDemandData2.ID = id;
            onDemandData2.incomplete = true;
            synchronized (this.aClass19_1370) {
                this.aClass19_1370.insertHead(onDemandData2);
            }
            this.nodeSubList.insertHead(onDemandData2);
        }
    }
    
    @Override
    public void run() {
        try {
            while (this.running) {
                ++this.onDemandCycle;
                int n = 20;
                if (this.anInt1332 == 0 && this.clientInstance.decompressors[0] != null) {
                    n = 50;
                }
                try {
                    Thread.sleep(n);
                }
                catch (Exception ex2) {}
                this.waiting = true;
                for (int i = 0; i < 100; ++i) {
                    this.waiting = false;
                    this.checkReceived();
                    this.handleFailed();
                    if (this.uncompletedCount == 0 && i >= 5) {
                        break;
                    }
                    this.method568();
                    if (this.inputStream != null) {
                        this.readData();
                    }
                }
                int n2 = 0;
                for (OnDemandData onDemandData = (OnDemandData)this.requested.reverseGetFirst(); onDemandData != null; onDemandData = (OnDemandData)this.requested.reverseGetNext()) {
                    if (onDemandData.incomplete) {
                        n2 = 1;
                        final OnDemandData onDemandData2 = onDemandData;
                        ++onDemandData2.loopCycle;
                        if (onDemandData.loopCycle > 50) {
                            onDemandData.loopCycle = 0;
                            this.closeRequest(onDemandData);
                        }
                    }
                }
                if (n2 == 0) {
                    for (OnDemandData onDemandData3 = (OnDemandData)this.requested.reverseGetFirst(); onDemandData3 != null; onDemandData3 = (OnDemandData)this.requested.reverseGetNext()) {
                        n2 = 1;
                        final OnDemandData onDemandData4 = onDemandData3;
                        ++onDemandData4.loopCycle;
                        if (onDemandData3.loopCycle > 50) {
                            onDemandData3.loopCycle = 0;
                            this.closeRequest(onDemandData3);
                        }
                    }
                }
                if (n2 != 0) {
                    ++this.loopCycle;
                    if (this.loopCycle > 750) {
                        try {
                            this.socket.close();
                        }
                        catch (Exception ex3) {}
                        this.socket = null;
                        this.inputStream = null;
                        this.outputStream = null;
                        this.expectedSize = 0;
                    }
                }
                else {
                    this.loopCycle = 0;
                    this.statusString = "";
                }
                if (this.clientInstance.loggedIn && this.socket != null && this.outputStream != null && (this.anInt1332 > 0 || this.clientInstance.decompressors[0] == null)) {
                    ++this.writeLoopCycle;
                    if (this.writeLoopCycle <= 500) {
                        continue;
                    }
                    this.writeLoopCycle = 0;
                    this.ioBuffer[0] = 0;
                    this.ioBuffer[1] = 0;
                    this.ioBuffer[2] = 0;
                    this.ioBuffer[3] = 10;
                    try {
                        this.outputStream.write(this.ioBuffer, 0, 4);
                    }
                    catch (IOException ex4) {
                        this.loopCycle = 5000;
                    }
                }
            }
        }
        catch (Exception ex) {
            SignLink.reporterror("od_ex " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void method560(final int id, final int dataType) {
        if (this.clientInstance.decompressors[0] == null) {
            return;
        }
        if (this.anInt1332 == 0) {
            return;
        }
        final OnDemandData onDemandData = new OnDemandData();
        onDemandData.dataType = dataType;
        onDemandData.ID = id;
        onDemandData.incomplete = false;
        synchronized (this.aClass19_1344) {
            this.aClass19_1344.insertHead(onDemandData);
        }
    }
    
    public OnDemandData getNextNode() {
        final OnDemandData onDemandData;
        synchronized (this.aClass19_1358) {
            onDemandData = (OnDemandData)this.aClass19_1358.popHead();
        }
        if (onDemandData == null) {
            return null;
        }
        synchronized (this.nodeSubList) {
            onDemandData.unlinkSub();
        }
        if (onDemandData.buffer == null) {
            return onDemandData;
        }
        int i = 0;
        Label_0155: {
            try {
                final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(onDemandData.buffer));
                while (i != this.gzipInputBuffer.length) {
                    final int read = gzipInputStream.read(this.gzipInputBuffer, i, this.gzipInputBuffer.length - i);
                    if (read == -1) {
                        break Label_0155;
                    }
                    i += read;
                }
                throw new RuntimeException("buffer overflow!");
            }
            catch (IOException ex) {
                throw new RuntimeException("error unzipping");
            }
        }
        onDemandData.buffer = new byte[i];
        System.arraycopy(this.gzipInputBuffer, 0, onDemandData.buffer, 0, i);
        return onDemandData;
    }
    
    public int method562(final int n, final int n2, final int n3) {
        final int n4 = (n3 << 8) + n2;
        int i = 0;
        while (i < this.mapIndices1.length) {
            if (this.mapIndices1[i] == n4) {
                if (n == 0) {
                    return this.mapIndices2[i];
                }
                return this.mapIndices3[i];
            }
            else {
                ++i;
            }
        }
        return -1;
    }
    
    @Override
    public void method548(final int n) {
        this.method558(0, n);
    }
    
    public boolean method564(final int n) {
        for (int i = 0; i < this.mapIndices1.length; ++i) {
            if (this.mapIndices3[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    private void handleFailed() {
        this.uncompletedCount = 0;
        this.completedCount = 0;
        for (OnDemandData onDemandData = (OnDemandData)this.requested.reverseGetFirst(); onDemandData != null; onDemandData = (OnDemandData)this.requested.reverseGetNext()) {
            if (onDemandData.incomplete) {
                ++this.uncompletedCount;
            }
            else {
                ++this.completedCount;
            }
        }
        while (this.uncompletedCount < 10) {
            try {
                final OnDemandData onDemandData2 = (OnDemandData)this.aClass19_1368.popHead();
                if (onDemandData2 == null) {
                    break;
                }
                if (this.fileStatus[onDemandData2.dataType][onDemandData2.ID] != 0) {
                    ++this.filesLoaded;
                }
                this.fileStatus[onDemandData2.dataType][onDemandData2.ID] = 0;
                this.requested.insertHead(onDemandData2);
                ++this.uncompletedCount;
                this.closeRequest(onDemandData2);
                this.waiting = true;
            }
            catch (Exception ex) {}
        }
    }
    
    public void method566() {
        synchronized (this.aClass19_1344) {
            this.aClass19_1344.removeAll();
        }
    }
    
    private void checkReceived() {
        OnDemandData onDemandData;
        synchronized (this.aClass19_1370) {
            onDemandData = (OnDemandData)this.aClass19_1370.popHead();
        }
        while (onDemandData != null) {
            this.waiting = true;
            byte[] decompress = null;
            if (this.clientInstance.decompressors[0] != null) {
                decompress = this.clientInstance.decompressors[onDemandData.dataType + 1].decompress(onDemandData.ID);
            }
            synchronized (this.aClass19_1370) {
                if (decompress == null) {
                    this.aClass19_1368.insertHead(onDemandData);
                }
                else {
                    onDemandData.buffer = decompress;
                    synchronized (this.aClass19_1358) {
                        this.aClass19_1358.insertHead(onDemandData);
                    }
                }
                onDemandData = (OnDemandData)this.aClass19_1370.popHead();
            }
        }
    }
    
    private void method568() {
        while (this.uncompletedCount == 0 && this.completedCount < 10 && this.anInt1332 != 0) {
            OnDemandData onDemandData;
            synchronized (this.aClass19_1344) {
                onDemandData = (OnDemandData)this.aClass19_1344.popHead();
            }
            while (onDemandData != null) {
                if (this.fileStatus[onDemandData.dataType][onDemandData.ID] != 0) {
                    this.fileStatus[onDemandData.dataType][onDemandData.ID] = 0;
                    this.requested.insertHead(onDemandData);
                    this.closeRequest(onDemandData);
                    this.waiting = true;
                    if (this.filesLoaded < this.totalFiles) {
                        ++this.filesLoaded;
                    }
                    this.statusString = "Loading extra files - " + this.filesLoaded * 100 / this.totalFiles + "%";
                    ++this.completedCount;
                    if (this.completedCount == 10) {
                        return;
                    }
                }
                synchronized (this.aClass19_1344) {
                    onDemandData = (OnDemandData)this.aClass19_1344.popHead();
                }
            }
            for (int i = 0; i < 4; ++i) {
                final byte[] array = this.fileStatus[i];
                for (int length = array.length, j = 0; j < length; ++j) {
                    if (array[j] == this.anInt1332) {
                        array[j] = 0;
                        final OnDemandData onDemandData2 = new OnDemandData();
                        onDemandData2.dataType = i;
                        onDemandData2.ID = j;
                        onDemandData2.incomplete = false;
                        this.requested.insertHead(onDemandData2);
                        this.closeRequest(onDemandData2);
                        this.waiting = true;
                        if (this.filesLoaded < this.totalFiles) {
                            ++this.filesLoaded;
                        }
                        this.statusString = "Loading extra files - " + this.filesLoaded * 100 / this.totalFiles + "%";
                        ++this.completedCount;
                        if (this.completedCount == 10) {
                            return;
                        }
                    }
                }
            }
            --this.anInt1332;
        }
    }
    
    public boolean method569(final int n) {
        return this.anIntArray1348[n] == 1;
    }
    
    public OnDemandFetcher() {
        this.i1 = 0;
        this.requested = new NodeList();
        this.statusString = "";
        this.crc32 = new CRC32();
        this.ioBuffer = new byte[500];
        this.fileStatus = new byte[4][];
        this.aClass19_1344 = new NodeList();
        this.running = true;
        this.waiting = false;
        this.aClass19_1358 = new NodeList();
        this.gzipInputBuffer = new byte[465000];
        this.nodeSubList = new NodeSubList();
        this.crcs = new int[4][];
        this.aClass19_1368 = new NodeList();
        this.aClass19_1370 = new NodeList();
    }
}
