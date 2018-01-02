// 
// Decompiled by Procyon v0.5.30
// 

package net.Slingshot.ClientAPI;

import java.io.InputStream;
import java.util.Enumeration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;

public class HttpConnection extends Thread
{
    public static final String version = "net.Slingshot.ClientAPI Version Pre Release(2.3) Computer Services Kaisha Deutschland GmbH";
    public static final int HashSize = 128;
    static String multipartHeader;
    static String finalHeader;
    public static final short recordStateNormal = 0;
    public static final short recordStatePending = 1;
    public static final short recordStateStale = 2;
    public static final short recordStateNotAvailable = 3;
    public static final short recordStateRequestError = 4;
    public static final short recordStatePermissionDenied = 5;
    public static final short recordStateServiceStale = 6;
    public static final short recordStateServiceUnknown = 7;
    public static final short recordStateUnmonitor = 8;
    public static final long recordNameChange = -2147483648L;
    public static final long updatesMissed = 1073741824L;
    public static final int initialiseConnection = 0;
    public static final int successfulConnection = 1;
    public static final int reconnecting = 2;
    public static final int connectionFailed = 3;
    public static final int connectionClosed = 4;
    public int state;
    public static final short insertStatusSuccess = 0;
    public static final short insertStatusFailed = 1;
    public static final short insertStatusTimedOut = 2;
    private static final short recordStateChangeName = 20;
    private HttpGetConnection getConnection;
    private HttpPostConnection postConnection;
    private Hashtable pending;
    private Hashtable cache;
    private Hashtable insert;
    private byte[] outBuf;
    private byte[] inBuf;
    private Vector myQueue;
    private InputStreamByteReader input;
    private int inBufLen;
    private long connSignature;
    private long connId;
    private long eventTime;
    private long activityTime;
    private boolean requestAll;
    private boolean initConnection;
    private boolean running;
    private long insertSeq;
    private int retryTime;
    private int testRetryTime;
    private long testRetry;
    private boolean shutDown;
    private long shutDownTime;
    private long activityTimeout;
    private long shutDownDelay;
    private HttpConnectionStatus statusCallback;
    private boolean debug;
    
    public HttpConnection(final URL url) {
        this.state = 0;
        this.requestAll = false;
        this.initConnection = true;
        this.running = true;
        this.insertSeq = 1L;
        this.retryTime = 15000;
        this.testRetryTime = 4000;
        this.shutDown = false;
        this.activityTimeout = 25000L;
        this.shutDownDelay = 30000L;
        this.debug = false;
        this.getConnection = new HttpGetConnection(url);
        this.postConnection = new HttpPostConnection(url);
        this.pending = new Hashtable(128);
        this.cache = new Hashtable(128);
        this.insert = new Hashtable(128);
        this.outBuf = new byte[2048];
        this.inBuf = new byte[8192];
        this.myQueue = new Vector();
        this.eventTime = System.currentTimeMillis();
        this.testRetry = this.eventTime;
    }
    
    public void setNoMerge() {
        this.postConnection.Flags = 1;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void connect() {
        this.start();
        this.setPriority(this.getThreadGroup().getMaxPriority());
    }
    
    public void setShutdownDelay(final long shutDownDelay) {
        this.shutDownDelay = shutDownDelay;
    }
    
    public synchronized void setStatus(final HttpConnectionStatus statusCallback) {
        this.statusCallback = statusCallback;
    }
    
    public synchronized void request(final Record record) throws IOException {
        if (!this.running) {
            throw new IOException("HttpConnection: HttpConnection is closed");
        }
        this.myQueue.insertElementAt(record, 0);
        this.shutDown = false;
    }
    
    public void setProxy(final URL url) {
        ((Hashtable<String, String>)System.getProperties()).put("proxySet", "true");
        ((Hashtable<String, String>)System.getProperties()).put("proxyHost", url.getHost());
        ((Hashtable<String, String>)System.getProperties()).put("proxyPort", String.valueOf(url.getPort()));
    }
    
    public void run() {
        System.out.println("");
        System.out.println("net.Slingshot.ClientAPI Version Pre Release(2.3) Computer Services Kaisha Deutschland GmbH");
        int n = 1;
        try {
            if (this.initConnection) {
                for (int i = 0; i < 5; ++i) {
                    try {
                        if (this.debug) {
                            System.out.println("HttpConnection: Initial Connection: " + i);
                        }
                        this.connectServer();
                        this.activityTime = System.currentTimeMillis();
                        break;
                    }
                    catch (FileNotFoundException ex) {
                        if (this.debug) {
                            System.out.println("HttpConnection: Not found connect failed: " + ex);
                        }
                        Thread.sleep(1000L);
                    }
                    catch (IOException ex2) {
                        if (this.debug) {
                            System.out.println("HttpConnection: IOException connect failed: " + ex2);
                        }
                        Thread.sleep(1000L);
                    }
                }
                if (this.input == null) {
                    this.running = false;
                    while (!this.myQueue.isEmpty()) {
                        final Record record = this.myQueue.lastElement();
                        this.myQueue.removeElement(record);
                        record.error();
                    }
                    this.setState(3, "HttpConnection: Connection Failed");
                }
            }
            while (this.running) {
                Label_0240: {
                    if (n == 0) {
                        break Label_0240;
                    }
                    this.setState(1, "HttpConnection: Connection Active");
                    n = 0;
                    try {
                        try {
                            this.handleTimeEvents();
                        }
                        catch (NoActivityException ex3) {
                            System.out.println("HttpConnection: NoActivityException HandleTimeEvents " + ex3);
                            throw new IOException("HttpConnection: No Avtivity on GET Connection");
                        }
                        catch (IOException ex4) {
                            System.out.println("HttpConnection: IOException HandleTimeEvents " + ex4);
                        }
                        if (!this.running) {
                            return;
                        }
                        this.processInStream();
                    }
                    catch (IOException ex5) {
                        System.currentTimeMillis();
                        if (this.debug) {
                            System.out.println("HttpConnection: IOException " + ex5);
                        }
                        this.inBufLen = 0;
                        while (this.running) {
                            try {
                                if (this.debug) {
                                    System.out.println("HttpConnection: Closing GET connection and retrying ");
                                }
                                this.input = null;
                                try {
                                    this.getConnection.close();
                                    this.inBufLen = 0;
                                }
                                catch (IOException ex6) {}
                                Thread.sleep(1000L);
                                try {
                                    this.handleTimeEvents();
                                }
                                catch (NoActivityException ex7) {}
                                catch (IOException ex8) {}
                                this.connectServer();
                                this.activityTime = System.currentTimeMillis();
                            }
                            catch (IOException ex9) {
                                if (!this.running) {
                                    return;
                                }
                                Thread.sleep(1000L);
                                if (n == 0) {
                                    this.markStale();
                                    n = 1;
                                    this.setState(2, "HttpConnection: Connection Stale");
                                    this.requestAll = true;
                                    this.getConnection.clearServerUrl();
                                }
                                if (this.shutDown && this.shutDownTime <= System.currentTimeMillis()) {
                                    this.running = false;
                                    this.closeHttp();
                                    return;
                                }
                                continue;
                            }
                        }
                        return;
                    }
                }
                if (this.shutDown && this.shutDownTime <= System.currentTimeMillis()) {
                    this.running = false;
                    this.closeHttp();
                    this.setState(4, "HttpConnection: Connection Closed");
                    return;
                }
                Thread.sleep(200L);
            }
        }
        catch (InterruptedException ex10) {}
        finally {
            this.closeHttp();
        }
    }
    
    public boolean isRunning() {
        return this.running;
    }
    
    public synchronized void close() {
        this.running = false;
        try {
            this.join();
        }
        catch (InterruptedException ex) {}
        try {
            if (this.getConnection != null) {
                this.getConnection.shutdown();
            }
        }
        catch (IOException ex2) {}
        if (this.postConnection != null) {
            this.postConnection.shutdown();
        }
        this.inBufLen = 0;
        this.getConnection = null;
        this.postConnection = null;
        final Runtime runtime = Runtime.getRuntime();
        runtime.runFinalization();
        runtime.gc();
    }
    
    private void closeHttp() {
        try {
            if (this.getConnection != null) {
                this.getConnection.close();
            }
        }
        catch (IOException ex) {}
        if (this.postConnection != null) {
            this.postConnection.close();
        }
        this.inBufLen = 0;
    }
    
    private void processInqueue(final int n) {
        int n2 = n;
        while (!this.myQueue.isEmpty() && this.connId > 0L && this.connSignature > 0L && (n2-- > 0 || n == 0)) {
            try {
                final Record record = this.myQueue.lastElement();
                this.myQueue.removeElement(record);
                if (this.debug) {
                    System.out.println("Request Recv: " + record);
                }
                switch (record.getType()) {
                    case 1000:
                    case 1001: {
                        this.processRequest((RecordRequest)record);
                        continue;
                    }
                    case 1003: {
                        this.processUnmonitor((RecordRequest)record);
                        continue;
                    }
                    case 1004: {
                        this.processInsert((UpdateRecord)record);
                        continue;
                    }
                    default: {
                        System.out.println("HttpConnection: Invalid Request Type(" + record.getType() + ")");
                        continue;
                    }
                }
            }
            catch (NullPointerException ex) {
                System.out.println("HttpConnection: Null Pointer ProcessRequest " + ex);
            }
            catch (IOException ex2) {}
        }
    }
    
    private void handleTimeEvents() throws IOException {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > this.testRetry + this.testRetryTime) {
            this.requestPending(currentTimeMillis);
            this.testInsert(currentTimeMillis);
            this.testCache();
            this.testRetry = currentTimeMillis;
        }
        if (currentTimeMillis > this.eventTime + 400L) {
            if (currentTimeMillis > this.activityTime + this.activityTimeout) {
                throw new NoActivityException("HttpConnection: No Activity Timer Failed Time: " + (currentTimeMillis - this.activityTime));
            }
            if (!this.shutDown && this.pending.isEmpty() && this.cache.isEmpty() && this.insert.isEmpty() && this.myQueue.isEmpty()) {
                this.shutDown = true;
                this.shutDownTime = currentTimeMillis + this.shutDownDelay;
            }
            this.processInqueue(0);
            if (this.postConnection.bufferToSend()) {
                try {
                    if (this.getConnection.getServerURL() == null) {
                        throw new IOException("HttpConnection: IOException: GET Server Url not Set");
                    }
                    if (this.debug) {
                        System.out.println("HttpConnection: Server " + this.getConnection.getServerURL());
                    }
                    this.postConnection.setURL(this.getConnection.getServerURL());
                    this.postConnection.send();
                }
                catch (IOException ex) {
                    System.out.println("HttpConnection: Post Connection IOException" + ex);
                    this.postConnection.close();
                    this.eventTime = currentTimeMillis + 500L;
                    return;
                }
            }
            this.eventTime = currentTimeMillis;
        }
    }
    
    private boolean processInStream() throws IOException {
        int n = 0;
        int n2 = 0;
        final int available;
        if (this.input != null && (available = this.input.available()) > 0) {
            if (this.inBuf.length < available + this.inBufLen) {
                final int n3 = 1024;
                final int n4 = available + this.inBufLen;
                final byte[] inBuf = new byte[n4 - n4 % n3 + n3];
                System.arraycopy(this.inBuf, 0, inBuf, 0, this.inBufLen);
                this.inBuf = inBuf;
            }
            final int read = this.input.read(this.inBuf, this.inBufLen, available);
            if (read > 0) {
                this.inBufLen += read;
                if (this.debug) {
                    System.out.println("HttpConnection: Received " + read + " bytes on the input stream ");
                }
                while (this.inBufLen > 0) {
                    if (this.inBufLen >= HttpConnection.multipartHeader.length() && new String(this.inBuf, 0, HttpConnection.multipartHeader.length()).compareTo(HttpConnection.multipartHeader) == 0) {
                        this.inBufLen -= HttpConnection.multipartHeader.length();
                        System.arraycopy(this.inBuf, HttpConnection.multipartHeader.length(), this.inBuf, 0, this.inBufLen);
                    }
                    else if (this.inBufLen >= HttpConnection.finalHeader.length() && new String(this.inBuf, 0, HttpConnection.finalHeader.length()).compareTo(HttpConnection.finalHeader) == 0) {
                        this.inBufLen = 0;
                        this.getConnection.close();
                        this.input = null;
                        if (this.initConnection) {
                            this.initConnection = false;
                            this.postConnection.setBuffered();
                            if (this.debug) {
                                System.out.println("HttpConnection: Using Buffered Proxy");
                            }
                            this.postConnection.sendPing();
                        }
                        throw new IOException("HttpConnection:  GET connnection closed");
                    }
                    if (this.inBufLen <= 4) {
                        break;
                    }
                    final long longToNative = MessageFormat.longToNative(this.inBuf, n);
                    if (longToNative > this.inBufLen - 4) {
                        break;
                    }
                    ++n2;
                    this.processInputBuffer(longToNative);
                    while (this.inBufLen > n && (this.inBuf[n] == 13 || this.inBuf[n] == 10)) {
                        ++n;
                    }
                    if (n == this.inBufLen) {
                        this.inBufLen = 0;
                    }
                    else {
                        this.inBufLen -= n;
                        System.arraycopy(this.inBuf, n, this.inBuf, 0, this.inBufLen);
                        n = 0;
                    }
                }
                if (this.initConnection) {
                    this.initConnection = false;
                }
                this.activityTime = System.currentTimeMillis();
            }
            return true;
        }
        return false;
    }
    
    private void processInputBuffer(final long n) throws IOException {
        int i = 4;
        while (i < i + (int)n) {
            final short shortToNative = MessageFormat.shortToNative(this.inBuf, i);
            i += 2;
            final byte b = this.inBuf[i++];
            if (MessageFormat.isaArray(b)) {
                MessageFormat.shortToNative(this.inBuf, i);
                i += 2;
            }
            switch (MessageFormat.getTypeOnly(b)) {
                case 11: {
                    final short shortToNative2 = MessageFormat.shortToNative(this.inBuf, i);
                    i += 2;
                    final short offset = (short)i;
                    i += shortToNative2;
                    switch (shortToNative) {
                        case -78: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Received HELLO Message");
                                continue;
                            }
                            continue;
                        }
                        case -66: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Received PING Message");
                            }
                            this.postConnection.sendPing();
                            continue;
                        }
                        case -69: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Connect Reply message Received");
                            }
                            this.processConnect(this.inBuf, offset, shortToNative2);
                            continue;
                        }
                        case -76: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Insert Response");
                            }
                            final RecordRequest recordRequest = new RecordRequest(shortToNative, this.inBuf, offset, shortToNative2);
                            final keyRecord key = this.getKey(this.insert, recordRequest.getService(), recordRequest.getKey());
                            if (key != null) {
                                final Enumeration keys = key.value.keys();
                                while (keys.hasMoreElements()) {
                                    final Short n2 = keys.nextElement();
                                    final ValueWatcher valueWatcher = key.value.get(n2);
                                    final CallBack callBack = null;
                                    CallBack callBack2 = valueWatcher.w;
                                    while (callBack2 != null) {
                                        if (recordRequest.seq == callBack2.mySeq) {
                                            callBack2.insert.insertStatus(n2, recordRequest.getStatus(), callBack2.sequence, recordRequest.txt);
                                            if (callBack == null) {
                                                valueWatcher.w = callBack2.next;
                                                break;
                                            }
                                            callBack.next = callBack2.next;
                                            break;
                                        }
                                        else {
                                            callBack2 = callBack2.next;
                                        }
                                    }
                                }
                                continue;
                            }
                            continue;
                        }
                        case -20: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Request Record Message Received");
                                continue;
                            }
                            continue;
                        }
                        case -18:
                        case -16: {
                            final UpdateRecord updateRecord = new UpdateRecord(shortToNative, this.inBuf, offset, shortToNative2);
                            if (this.debug) {
                                System.out.println("HttpConnection: Update " + updateRecord + "Lgth " + shortToNative2 + "conn Id " + this.connId);
                            }
                            this.processUpdate(updateRecord);
                            continue;
                        }
                        case -45: {
                            final RecordRequest recordRequest2 = new RecordRequest(shortToNative, this.inBuf, offset, shortToNative2);
                            if (this.debug) {
                                System.out.println("HttpConnection: Request Status Message Received " + recordRequest2);
                            }
                            final keyRecord key2 = this.getKey(this.pending, recordRequest2.getService(), recordRequest2.getKey());
                            if (recordRequest2.getStatus() == 20 && recordRequest2.change) {
                                this.renameKey(this.pending, recordRequest2.getService(), recordRequest2.oldKey, recordRequest2.getKey());
                                this.renameKey(this.cache, recordRequest2.getService(), recordRequest2.oldKey, recordRequest2.getKey());
                                continue;
                            }
                            if (recordRequest2.getStatus() == 2) {
                                this.processStale(recordRequest2);
                                continue;
                            }
                            if (key2 == null) {
                                continue;
                            }
                            final Enumeration fids = recordRequest2.fids();
                            while (fids.hasMoreElements()) {
                                final Short fid = fids.nextElement().fid;
                                final ValueWatcher valueWatcher2 = key2.value.get(fid);
                                if (valueWatcher2 != null) {
                                    CallBack callBack3 = valueWatcher2.w;
                                    CallBack callBack4 = null;
                                    while (callBack3 != null) {
                                        if (callBack3.update != null) {
                                            callBack3.update.FidUpdate(recordRequest2.getKey(), fid, null, recordRequest2.getStatus(), (short)0, 0L);
                                            if (callBack4 == null) {
                                                valueWatcher2.w = callBack3.next;
                                            }
                                            else {
                                                callBack4.next = callBack3.next;
                                            }
                                        }
                                        else {
                                            callBack4 = callBack3;
                                        }
                                        callBack3 = callBack3.next;
                                    }
                                    if (valueWatcher2.w != null) {
                                        continue;
                                    }
                                    key2.value.remove(fid);
                                }
                            }
                            if (key2.value.isEmpty()) {
                                this.deleteKey(this.pending, recordRequest2.getService(), recordRequest2.getKey());
                                continue;
                            }
                            continue;
                        }
                        case 1003: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Unmonitor Message Received");
                            }
                            final RecordRequest recordRequest3 = new RecordRequest(shortToNative, this.inBuf, offset, shortToNative2);
                            final keyRecord key3 = this.getKey(this.pending, recordRequest3.getService(), recordRequest3.getKey());
                            if (key3 != null) {
                                final Enumeration fids2 = recordRequest3.fids();
                                while (fids2.hasMoreElements()) {
                                    final Short fid2 = fids2.nextElement().fid;
                                    final ValueWatcher valueWatcher3 = key3.value.get(fid2);
                                    key3.value.remove(fid2);
                                    if (valueWatcher3 != null) {
                                        for (CallBack callBack5 = valueWatcher3.w; callBack5 != null; callBack5 = callBack5.next) {
                                            callBack5.update.FidUpdate(recordRequest3.getKey(), fid2, null, (short)8, (short)0, 0L);
                                        }
                                        key3.value.remove(fid2);
                                    }
                                }
                                if (key3.value.isEmpty()) {
                                    this.deleteKey(this.pending, recordRequest3.getService(), recordRequest3.getKey());
                                }
                            }
                            final keyRecord key4 = this.getKey(this.cache, recordRequest3.getService(), recordRequest3.getKey());
                            if (key4 == null) {
                                continue;
                            }
                            final Enumeration fids3 = recordRequest3.fids();
                            while (fids3.hasMoreElements()) {
                                final Short fid3 = fids3.nextElement().fid;
                                final ValueWatcher valueWatcher4 = key4.value.get(fid3);
                                if (valueWatcher4 != null) {
                                    for (CallBack callBack6 = valueWatcher4.w; callBack6 != null; callBack6 = callBack6.next) {
                                        callBack6.update.FidUpdate(recordRequest3.getKey(), fid3, valueWatcher4.value, (short)8, (short)0, 0L);
                                    }
                                    key4.value.remove(fid3);
                                }
                            }
                            if (key4.value.isEmpty()) {
                                this.deleteKey(this.cache, recordRequest3.getService(), recordRequest3.getKey());
                                continue;
                            }
                            continue;
                        }
                        case 1006: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Insert Status Message Received");
                                continue;
                            }
                            continue;
                        }
                        default: {
                            if (this.debug) {
                                System.out.println("HttpConnection: Invalid Message type Received ");
                                continue;
                            }
                            continue;
                        }
                    }
                    break;
                }
                default: {
                    System.out.println("HttpConnection: Input Buffer corrupt");
                    this.inBufLen = 0;
                    throw new IOException("HttpConnection: Input Buffer corrupt");
                }
            }
        }
        this.inBufLen -= i;
        System.arraycopy(this.inBuf, i, this.inBuf, 0, this.inBufLen);
    }
    
    private void processConnect(final byte[] array, final int n, final int n2) throws IOException {
        int i = n;
        final int n3 = i + n2;
        long longToNative = 0L;
        long longToNative2 = 0L;
        while (i < n3) {
            final short shortToNative = MessageFormat.shortToNative(array, i);
            i += 2;
            if (MessageFormat.isaArray(array[i++])) {
                MessageFormat.shortToNative(array, i);
                i += 2;
            }
            if (shortToNative == -70) {
                longToNative = MessageFormat.longToNative(array, i);
                i += 4;
            }
            else if (shortToNative == -71) {
                longToNative2 = MessageFormat.longToNative(array, i);
                i += 4;
            }
            else {
                if (shortToNative != -80) {
                    continue;
                }
                final long longToNative3 = MessageFormat.longToNative(array, i);
                i += 4;
                if (longToNative3 <= 0L) {
                    continue;
                }
                this.activityTimeout = longToNative3 * 1000L * 3L;
            }
        }
        if (longToNative2 == this.connId && this.connSignature == longToNative) {
            if (this.requestAll && this.connId != 0L && this.connSignature != 0L) {
                this.requestAll = false;
                this.requestAll();
            }
            return;
        }
        if (this.debug) {
            System.out.println("HttpConnection: Connect Ids request All OldSig(" + this.connSignature + ") newSig(" + longToNative + ") OldId(" + this.connId + ") newId(" + longToNative2 + ")");
        }
        this.connId = longToNative2;
        this.connSignature = longToNative;
        this.postConnection.setSignatureConnId(this.connSignature, this.connId);
        this.getConnection.setSignatureConnId(this.connSignature, this.connId);
        if (!this.initConnection) {
            if (this.debug) {
                System.out.println("HttpConnection: New Connect Ids request All OldSig(" + this.connSignature + ") newSig(" + longToNative + ") OldId(" + this.connId + ") newId(" + longToNative2 + ")");
            }
            this.requestAll();
            this.requestAll = false;
            return;
        }
        if (this.debug) {
            System.out.println("HttpConnection: Connect Ids newSig(" + longToNative + ") newId(" + longToNative2 + ")");
        }
        this.postConnection.sendPing();
    }
    
    private void processUpdate(final UpdateRecord updateRecord) {
        final String service = updateRecord.getService();
        final String key = updateRecord.getKey();
        if (key != null && service != null) {
            final keyRecord key2 = this.getKey(this.pending, service, key);
            keyRecord keyRecord = this.getKey(this.cache, service, key);
            if (updateRecord.isFull() && key2 != null) {
                if (keyRecord == null) {
                    keyRecord = this.createKey(this.cache, service, key);
                }
                final Enumeration fids = updateRecord.fids();
                while (fids.hasMoreElements()) {
                    final FidValue fidValue = fids.nextElement();
                    final Short fid = fidValue.fid;
                    final ValueWatcher valueWatcher = key2.value.get(fid);
                    ValueWatcher valueWatcher2 = keyRecord.value.get(fid);
                    key2.value.remove(fid);
                    if (valueWatcher2 == null && valueWatcher != null) {
                        keyRecord.value.put(fid, valueWatcher);
                        valueWatcher2 = valueWatcher;
                    }
                    else if (valueWatcher2 != null && valueWatcher != null) {
                        CallBack callBack = null;
                        for (CallBack callBack2 = valueWatcher2.w; callBack2 != null; callBack2 = callBack2.next) {
                            callBack = callBack2;
                        }
                        callBack.next = valueWatcher.w;
                    }
                    CallBack callBack3 = valueWatcher2.w;
                    CallBack callBack4 = null;
                    if (valueWatcher2.value.length() >= fidValue.value.length() + fidValue.offset) {
                        final byte[] bytes = valueWatcher2.value.getBytes();
                        final byte[] bytes2 = fidValue.value.getBytes();
                        System.arraycopy(bytes2, 0, bytes, fidValue.offset, bytes2.length);
                        valueWatcher2.value = new String(bytes);
                    }
                    else {
                        final byte[] array = new byte[fidValue.value.length() + fidValue.offset];
                        final byte[] bytes3 = valueWatcher2.value.getBytes();
                        final byte[] bytes4 = fidValue.value.getBytes();
                        System.arraycopy(bytes3, 0, array, 0, bytes3.length);
                        System.arraycopy(bytes4, 0, array, fidValue.offset, bytes4.length);
                        valueWatcher2.value = new String(array);
                    }
                    while (callBack3 != null) {
                        if (callBack3.update != null) {
                            long n = (long)(fidValue.partial ? 1 : 0);
                            if (key2.change) {
                                n |= 0xFFFFFFFF80000000L;
                            }
                            callBack3.update.FidUpdate(key, fid, fidValue.value, (short)0, fidValue.offset, n);
                        }
                        if (callBack3.type != 1000 || callBack3.update == null) {
                            if (callBack4 == null) {
                                valueWatcher2.w = callBack3.next;
                            }
                            else {
                                callBack4.next = callBack3.next;
                            }
                        }
                        else {
                            callBack4 = callBack3;
                        }
                        callBack3 = callBack3.next;
                    }
                    if (valueWatcher2.w == null) {
                        keyRecord.value.remove(fid);
                    }
                }
                key2.change = false;
                if (keyRecord.value.isEmpty()) {
                    this.deleteKey(this.cache, service, key);
                }
                if (key2.value.isEmpty()) {
                    this.deleteKey(this.pending, service, key);
                }
            }
            else if (keyRecord != null) {
                final Enumeration fids2 = updateRecord.fids();
                while (fids2.hasMoreElements()) {
                    final FidValue fidValue2 = fids2.nextElement();
                    final Short fid2 = fidValue2.fid;
                    final ValueWatcher valueWatcher3 = keyRecord.value.get(fid2);
                    if (valueWatcher3 != null) {
                        CallBack callBack5 = valueWatcher3.w;
                        if (fidValue2.partial) {
                            if (valueWatcher3.value.length() >= fidValue2.value.length() + fidValue2.offset) {
                                final char[] charArray = valueWatcher3.value.toCharArray();
                                final char[] charArray2 = fidValue2.value.toCharArray();
                                System.arraycopy(charArray2, 0, charArray, fidValue2.offset, charArray2.length);
                                valueWatcher3.value = new String(charArray);
                            }
                            else {
                                final char[] array2 = new char[fidValue2.value.length() + fidValue2.offset];
                                final char[] charArray3 = valueWatcher3.value.toCharArray();
                                final char[] charArray4 = fidValue2.value.toCharArray();
                                System.arraycopy(charArray3, 0, array2, 0, charArray3.length);
                                if (charArray3.length < fidValue2.offset) {
                                    for (int i = charArray3.length; i < fidValue2.offset; ++i) {
                                        array2[i] = ' ';
                                    }
                                }
                                System.arraycopy(charArray4, 0, array2, fidValue2.offset, charArray4.length);
                                valueWatcher3.value = new String(array2);
                            }
                        }
                        else {
                            valueWatcher3.value = fidValue2.value;
                        }
                        while (callBack5 != null) {
                            long n2 = (long)(fidValue2.partial ? 1 : 0);
                            if (keyRecord.change) {
                                n2 |= 0xFFFFFFFF80000000L;
                            }
                            if (fidValue2.missUpdate) {
                                n2 |= 0x40000000L;
                            }
                            callBack5.update.FidUpdate(key, fid2, fidValue2.value, (short)0, fidValue2.offset, n2);
                            callBack5 = callBack5.next;
                        }
                    }
                }
                keyRecord.change = false;
            }
        }
    }
    
    private void processStale(final RecordRequest recordRequest) {
        final String service = recordRequest.getService();
        final String key = recordRequest.getKey();
        if (key != null && service != null) {
            final keyRecord key2 = this.getKey(this.cache, service, key);
            if (key2 != null) {
                final Enumeration fids = recordRequest.fids();
                while (fids.hasMoreElements()) {
                    final Short fid = fids.nextElement().fid;
                    final ValueWatcher valueWatcher = key2.value.get(fid);
                    if (valueWatcher != null) {
                        for (CallBack callBack = valueWatcher.w; callBack != null; callBack = callBack.next) {
                            long n = 0L;
                            if (key2.change) {
                                n |= 0xFFFFFFFF80000000L;
                            }
                            callBack.update.FidUpdate(key, fid, valueWatcher.value, (short)2, (short)0, n);
                        }
                    }
                }
                key2.change = false;
            }
        }
    }
    
    private void connectServer() throws IOException, FileNotFoundException {
        this.input = new InputStreamByteReader(this.getConnection.getInputStream(), 8192);
    }
    
    private keyRecord createKey(final Hashtable hashtable, final String s, final String s2) {
        service service = hashtable.get(s);
        if (service == null) {
            service = new service(s);
            hashtable.put(service.srvName, service);
        }
        keyRecord keyRecord = service.findEntry(s2);
        if (keyRecord == null) {
            keyRecord = service.createEntry(s2);
        }
        return keyRecord;
    }
    
    private int insertPending(final RecordRequest recordRequest) {
        int n = 1;
        final Vector vector = new Vector<CallBack>();
        service service = this.pending.get(recordRequest.getService());
        if (service == null) {
            service = new service(recordRequest.getService());
            this.pending.put(service.srvName, service);
        }
        keyRecord keyRecord = service.findEntry(recordRequest.getKey());
        if (keyRecord == null) {
            keyRecord = service.createEntry(recordRequest.getKey());
        }
        keyRecord.cnt = 0;
        final Enumeration fids = recordRequest.fids();
        while (fids.hasMoreElements()) {
            final CallBack callBack = fids.nextElement();
            final Short fid = callBack.fid;
            final ValueWatcher valueWatcher = keyRecord.value.get(fid);
            if (callBack != null) {
                final CallBack callBack2 = callBack;
                callBack2.type = recordRequest.getType();
                if (valueWatcher == null) {
                    final ValueWatcher valueWatcher2 = new ValueWatcher("");
                    valueWatcher2.w = callBack2;
                    keyRecord.value.put(new Short(fid), valueWatcher2);
                    if (callBack.update != null) {
                        callBack.update.FidUpdate(recordRequest.getKey(), fid, null, (short)1, (short)0, 0L);
                    }
                    n = 0;
                }
                else {
                    CallBack callBack3;
                    for (callBack3 = valueWatcher.w; callBack3 != null && callBack.update != callBack3.update; callBack3 = callBack3.next) {}
                    if (callBack3 != null) {
                        if (recordRequest.getType() == 1000 && callBack3.type != 1000) {
                            n = 0;
                        }
                        else {
                            vector.addElement(callBack);
                        }
                        if (recordRequest.getType() != 1000) {
                            continue;
                        }
                        callBack3.type = 1000;
                    }
                    else {
                        callBack2.next = valueWatcher.w;
                        valueWatcher.w = callBack2;
                        if (callBack.update != null) {
                            callBack.update.FidUpdate(recordRequest.getKey(), fid, null, (short)1, (short)0, 0L);
                        }
                        n = 0;
                    }
                }
            }
        }
        if (!vector.isEmpty()) {
            final Enumeration<CallBack> elements = vector.elements();
            while (elements.hasMoreElements()) {
                recordRequest.remove(elements.nextElement());
            }
        }
        return n;
    }
    
    private keyRecord getKey(final Hashtable hashtable, final String s, final String s2) {
        final service service = hashtable.get(s);
        if (service == null) {
            return null;
        }
        return service.findEntry(s2);
    }
    
    private keyRecord renameKey(final Hashtable hashtable, final String s, final String s2, final String s3) {
        final service service = hashtable.get(s);
        if (service == null) {
            return null;
        }
        final keyRecord entry;
        if ((entry = service.findEntry(s2)) != null) {
            service.deleteEntry(s2);
            entry.Key = new String(s3);
            entry.change = true;
            service.addEntry(s3, entry);
            return entry;
        }
        return null;
    }
    
    private void deleteKey(final Hashtable hashtable, final String s, final String s2) {
        final service service = hashtable.get(s);
        if (service == null) {
            return;
        }
        service.deleteEntry(s2);
        if (service.keyHash.isEmpty()) {
            hashtable.remove(s);
        }
    }
    
    private synchronized int processRequest(final RecordRequest recordRequest) throws IOException {
        final String service = recordRequest.getService();
        final String key = recordRequest.getKey();
        final Vector vector = new Vector<CallBack>();
        if (key != null && service != null) {
            final keyRecord key2 = this.getKey(this.cache, service, key);
            if (key2 != null) {
                final Enumeration fids = recordRequest.fids();
                while (fids.hasMoreElements()) {
                    final CallBack callBack = fids.nextElement();
                    final Short fid = callBack.fid;
                    final ValueWatcher valueWatcher = key2.value.get(fid);
                    if (valueWatcher != null) {
                        CallBack callBack2 = valueWatcher.w;
                        vector.addElement(callBack);
                        while (callBack2 != null && callBack.update != callBack2.update) {
                            callBack2 = callBack2.next;
                        }
                        if (recordRequest.getType() == 1000) {
                            if (callBack2 != null) {
                                continue;
                            }
                            final CallBack w = callBack;
                            w.type = recordRequest.getType();
                            w.next = valueWatcher.w;
                            valueWatcher.w = w;
                            callBack.update.FidUpdate(key, fid, valueWatcher.value, valueWatcher.state, (short)0, 0L);
                        }
                        else {
                            callBack.update.FidUpdate(key, fid, valueWatcher.value, valueWatcher.state, (short)0, 0L);
                        }
                    }
                }
            }
            if (!vector.isEmpty()) {
                final Enumeration<CallBack> elements = vector.elements();
                while (elements.hasMoreElements()) {
                    recordRequest.remove(elements.nextElement());
                }
            }
            if (!recordRequest.isFidsEmpty() && this.insertPending(recordRequest) == 0) {
                final int formatRequest = recordRequest.formatRequest(this.outBuf, 0);
                if (formatRequest > 0) {
                    this.postConnection.bufferSend(this.outBuf, formatRequest);
                }
            }
        }
        return 0;
    }
    
    private synchronized int processInsert(final UpdateRecord updateRecord) throws IOException {
        final String service = updateRecord.getService();
        final String key = updateRecord.getKey();
        if (key != null && service != null) {
            keyRecord keyRecord = this.getKey(this.insert, service, key);
            if (keyRecord == null) {
                keyRecord = this.createKey(this.insert, service, key);
            }
            final Enumeration fids = updateRecord.fids();
            while (fids.hasMoreElements()) {
                final CallBack w = fids.nextElement();
                final Short fid = w.fid;
                ValueWatcher valueWatcher = keyRecord.value.get(fid);
                if (valueWatcher == null && w.insert != null) {
                    valueWatcher = new ValueWatcher("");
                    keyRecord.value.put(fid, valueWatcher);
                }
                w.mySeq = this.insertSeq;
                if (w.insert != null) {
                    w.next = valueWatcher.w;
                    valueWatcher.w = w;
                }
            }
            if (keyRecord.value.size() == 0) {
                this.deleteKey(this.insert, service, key);
            }
            updateRecord.seq = this.insertSeq++;
            if (!updateRecord.isFidsEmpty()) {
                final int formatRequest = updateRecord.formatRequest(this.outBuf, 0);
                if (formatRequest > 0) {
                    this.postConnection.bufferSend(this.outBuf, formatRequest);
                }
            }
        }
        return 0;
    }
    
    private void testCache() {
        final Enumeration<String> keys = this.pending.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final service service = this.pending.get(s);
            if (service != null && service.keyHash.isEmpty()) {
                this.pending.remove(s);
            }
        }
    }
    
    private synchronized void processUnmonitor(final RecordRequest recordRequest) throws IOException {
        final keyRecord key = this.getKey(this.pending, recordRequest.getService(), recordRequest.getKey());
        final Vector vector = new Vector<CallBack>();
        if (key != null) {
            final Enumeration fids = recordRequest.fids();
            while (fids.hasMoreElements()) {
                final CallBack callBack = fids.nextElement();
                final Short fid = callBack.fid;
                final ValueWatcher valueWatcher = key.value.get(fid);
                if (valueWatcher != null) {
                    CallBack callBack2 = valueWatcher.w;
                    CallBack callBack3 = null;
                    while (callBack2 != null) {
                        if (callBack2.update == callBack.update) {
                            callBack2.update.FidUpdate(recordRequest.getKey(), fid, null, (short)8, (short)0, 0L);
                            if (callBack3 == null) {
                                valueWatcher.w = callBack2.next;
                                callBack2 = valueWatcher.w;
                            }
                            else {
                                callBack3.next = callBack2.next;
                                callBack2 = callBack2.next;
                            }
                        }
                        else {
                            callBack3 = callBack2;
                            callBack2 = callBack2.next;
                        }
                    }
                }
                if (valueWatcher.w != null) {
                    vector.addElement(callBack);
                }
                else {
                    key.value.remove(fid);
                }
            }
            if (key.value.isEmpty()) {
                this.deleteKey(this.pending, recordRequest.getService(), recordRequest.getKey());
            }
        }
        final keyRecord key2 = this.getKey(this.cache, recordRequest.getService(), recordRequest.getKey());
        if (key2 != null) {
            final Enumeration fids2 = recordRequest.fids();
            while (fids2.hasMoreElements()) {
                final CallBack callBack4 = fids2.nextElement();
                final Short fid2 = callBack4.fid;
                final ValueWatcher valueWatcher2 = key2.value.get(fid2);
                if (valueWatcher2 != null) {
                    CallBack callBack5 = valueWatcher2.w;
                    CallBack callBack6 = null;
                    while (callBack5 != null) {
                        if (callBack5.update == callBack4.update) {
                            callBack5.update.FidUpdate(recordRequest.getKey(), fid2, valueWatcher2.value, (short)8, (short)0, 0L);
                            if (callBack6 == null) {
                                valueWatcher2.w = callBack5.next;
                                callBack5 = valueWatcher2.w;
                            }
                            else {
                                callBack6.next = callBack5.next;
                                callBack5 = callBack5.next;
                            }
                        }
                        else {
                            callBack6 = callBack5;
                            callBack5 = callBack5.next;
                        }
                    }
                    if (valueWatcher2.w != null) {
                        vector.addElement(callBack4);
                    }
                    else {
                        key2.value.remove(fid2);
                    }
                }
            }
            if (key2.value.isEmpty()) {
                this.deleteKey(this.cache, recordRequest.getService(), recordRequest.getKey());
            }
        }
        if (!vector.isEmpty()) {
            final Enumeration<CallBack> elements = vector.elements();
            while (elements.hasMoreElements()) {
                recordRequest.remove(elements.nextElement());
            }
        }
        if (!recordRequest.isFidsEmpty()) {
            final int formatRequest = recordRequest.formatRequest(this.outBuf, 0);
            if (formatRequest > 0) {
                this.postConnection.bufferSend(this.outBuf, formatRequest);
            }
        }
    }
    
    private void markStale() {
        if (this.debug) {
            System.out.println("HttpConnection: Mark All records Stale");
        }
        final Enumeration<String> keys = this.cache.keys();
        while (keys.hasMoreElements()) {
            final service service = this.cache.get(keys.nextElement());
            final Enumeration keys2 = service.keyHash.keys();
            while (keys2.hasMoreElements()) {
                final String s = keys2.nextElement();
                final keyRecord keyRecord = service.keyHash.get(s);
                final Enumeration keys3 = keyRecord.value.keys();
                while (keys3.hasMoreElements()) {
                    final Short n = keys3.nextElement();
                    final ValueWatcher valueWatcher = keyRecord.value.get(n);
                    CallBack callBack = valueWatcher.w;
                    valueWatcher.state = 2;
                    while (callBack != null) {
                        long n2 = 0L;
                        if (keyRecord.change) {
                            n2 |= 0xFFFFFFFF80000000L;
                        }
                        callBack.update.FidUpdate(s, n, valueWatcher.value, (short)2, (short)0, n2);
                        callBack = callBack.next;
                    }
                }
                keyRecord.change = false;
            }
        }
    }
    
    private void requestPending(final long rTime) throws IOException {
        final Enumeration<String> keys = (Enumeration<String>)this.pending.keys();
        while (keys.hasMoreElements()) {
            final String service = keys.nextElement();
            final service service2 = this.pending.get(service);
            if (service2 != null) {
                final Enumeration keys2 = service2.keyHash.keys();
                while (keys2.hasMoreElements()) {
                    final String key = keys2.nextElement();
                    final keyRecord keyRecord = service2.keyHash.get(key);
                    if (keyRecord.cnt >= 5) {
                        final Enumeration keys3 = keyRecord.value.keys();
                        while (keys3.hasMoreElements()) {
                            final Short n = keys3.nextElement();
                            for (CallBack callBack = keyRecord.value.get(n).w; callBack != null && callBack.update != null; callBack = callBack.next) {
                                callBack.update.FidUpdate(key, n, null, (short)3, (short)0, 0L);
                            }
                        }
                        this.deleteKey(this.pending, service, key);
                    }
                    else {
                        if (rTime != 0L && rTime <= keyRecord.RTime + this.retryTime) {
                            continue;
                        }
                        final RecordRequest recordRequest = new RecordRequest();
                        recordRequest.setService(service);
                        recordRequest.setKey(key);
                        final Enumeration keys4 = keyRecord.value.keys();
                        while (keys4.hasMoreElements()) {
                            recordRequest.setFid(keys4.nextElement(), null);
                        }
                        final int formatRequest = recordRequest.formatRequest(this.outBuf, 0);
                        final keyRecord keyRecord2 = keyRecord;
                        ++keyRecord2.cnt;
                        keyRecord.RTime = rTime;
                        if (formatRequest <= 0) {
                            continue;
                        }
                        this.postConnection.bufferSend(this.outBuf, formatRequest);
                    }
                }
                if (!service2.keyHash.isEmpty()) {
                    continue;
                }
                this.pending.remove(service);
            }
        }
    }
    
    private void testInsert(final long n) throws IOException {
        final Enumeration<String> keys = this.insert.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final service service = this.insert.get(s);
            if (service != null) {
                final Enumeration keys2 = service.keyHash.keys();
                while (keys2.hasMoreElements()) {
                    final String s2 = keys2.nextElement();
                    final keyRecord keyRecord = service.keyHash.get(s2);
                    final Enumeration keys3 = keyRecord.value.keys();
                    while (keys3.hasMoreElements()) {
                        final Short n2 = keys3.nextElement();
                        final ValueWatcher valueWatcher = keyRecord.value.get(n2);
                        CallBack callBack = valueWatcher.w;
                        final CallBack callBack2 = null;
                        while (callBack != null) {
                            if (callBack.timeout != 0L && n > callBack.timeout) {
                                callBack.insert.insertStatus(n2, (short)2, callBack.sequence, "Insert Timed Out");
                                if (callBack2 == null) {
                                    valueWatcher.w = callBack.next;
                                }
                                else {
                                    callBack2.next = callBack.next;
                                }
                            }
                            callBack = callBack.next;
                        }
                        if (valueWatcher.w == null) {
                            keyRecord.value.remove(n2);
                        }
                    }
                    if (keyRecord.value.isEmpty()) {
                        service.keyHash.remove(s2);
                    }
                }
                if (!service.keyHash.isEmpty()) {
                    continue;
                }
                this.insert.remove(s);
            }
        }
    }
    
    private void requestAll() throws IOException {
        final String s = "SESSION-";
        this.requestPending(0L);
        if (!this.cache.isEmpty()) {
            final Enumeration<String> keys = this.cache.keys();
            while (keys.hasMoreElements()) {
                final String service = keys.nextElement();
                final service service2 = this.cache.get(service);
                if (!service2.keyHash.isEmpty()) {
                    final Enumeration keys2 = service2.keyHash.keys();
                    while (keys2.hasMoreElements()) {
                        final String key = keys2.nextElement();
                        final keyRecord keyRecord = service2.keyHash.get(key);
                        if (s.regionMatches(0, key, 0, s.length())) {
                            final Enumeration keys3 = keyRecord.value.keys();
                            while (keys3.hasMoreElements()) {
                                final Short n = keys3.nextElement();
                                for (CallBack callBack = keyRecord.value.get(n).w; callBack != null && callBack.update != null; callBack = callBack.next) {
                                    callBack.update.FidUpdate(key, n, null, (short)3, (short)0, 0L);
                                }
                            }
                            this.deleteKey(this.pending, service, key);
                        }
                        else {
                            final RecordRequest recordRequest = new RecordRequest();
                            recordRequest.setService(service);
                            recordRequest.setKey(key);
                            final Enumeration keys4 = keyRecord.value.keys();
                            while (keys4.hasMoreElements()) {
                                recordRequest.setFid(keys4.nextElement(), null);
                            }
                            final int formatRequest = recordRequest.formatRequest(this.outBuf, 0);
                            if (formatRequest <= 0) {
                                continue;
                            }
                            this.insertPending(recordRequest);
                            this.postConnection.bufferSend(this.outBuf, formatRequest);
                        }
                    }
                }
            }
        }
    }
    
    private void setState(final int state, final String s) {
        this.state = state;
        if (this.statusCallback != null) {
            this.statusCallback.status(state, s);
        }
    }
    
    static {
        HttpConnection.multipartHeader = new String("--MultiPartData\r\nContent-Type: Application/octet-stream\r\n\r\n");
        HttpConnection.finalHeader = new String("--MultiPartData--\r\n\r\n");
    }
    
    private class InputStreamByteReader extends Thread
    {
        InputStream input;
        byte[] inBuf;
        int inBufLen;
        boolean running;
        
        InputStreamByteReader(final InputStream input, final int n) {
            this.running = true;
            this.input = input;
            this.inBuf = new byte[n];
            this.start();
        }
        
        public void run() {
            while (this.running) {
                try {
                    if (this.input != null && this.inBufLen < this.inBuf.length && this.input.available() > 0) {
                        this.read();
                    }
                }
                catch (IOException ex) {
                    this.running = false;
                    return;
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex2) {
                    this.running = false;
                }
            }
        }
        
        public void finalize() {
            this.running = false;
        }
        
        public int available() throws IOException {
            if (!this.running) {
                throw new IOException("Connection broken");
            }
            return this.inBufLen;
        }
        
        public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
            if (!this.running) {
                throw new IOException("Connection broken");
            }
            final int n3 = (n2 > this.inBufLen) ? this.inBufLen : n2;
            if (n3 > 0) {
                System.arraycopy(this.inBuf, 0, array, n, n3);
                this.inBufLen -= n3;
                if (this.inBufLen > 0) {
                    System.arraycopy(this.inBuf, n3, this.inBuf, 0, this.inBufLen);
                }
            }
            return n3;
        }
        
        private synchronized void read() throws IOException {
            final int available = this.input.available();
            final int n = (available + this.inBufLen > this.inBuf.length) ? (this.inBuf.length - this.inBufLen) : available;
            if (n <= 0) {
                return;
            }
            final int read = this.input.read(this.inBuf, this.inBufLen, n);
            if (read > 0) {
                this.inBufLen += read;
                return;
            }
            throw new IOException("Connection broken");
        }
        
        public String toString() {
            return new String("InputStreamByteReader running(" + this.running + ") inBuf Lgth(" + this.inBufLen + ")");
        }
    }
    
    private class ValueWatcher
    {
        public String value;
        public short state;
        public CallBack w;
        
        ValueWatcher(final String value) {
            this.value = value;
            this.state = 0;
        }
    }
    
    private class keyRecord
    {
        String Key;
        int Type;
        int cnt;
        long RTime;
        Hashtable value;
        boolean change;
        
        keyRecord(final String s) {
            this.change = false;
            this.Key = new String(s);
            this.RTime = System.currentTimeMillis();
            this.value = new Hashtable(128);
        }
    }
    
    private class NoActivityException extends IOException
    {
        NoActivityException(final String s) {
            super(s);
        }
    }
    
    private class service
    {
        public String srvName;
        public Hashtable keyHash;
        
        service(final String s) {
            this.srvName = new String(s);
            this.keyHash = new Hashtable(128);
        }
        
        public keyRecord findEntry(final String s) {
            if (s == null) {
                return null;
            }
            return this.keyHash.get(s);
        }
        
        public keyRecord createEntry(final String s) {
            if (s == null) {
                return null;
            }
            final keyRecord keyRecord = new keyRecord(s);
            this.keyHash.put(s, keyRecord);
            return keyRecord;
        }
        
        public keyRecord addEntry(final String s, final keyRecord keyRecord) {
            if (s == null || keyRecord == null) {
                return null;
            }
            this.keyHash.put(s, keyRecord);
            return keyRecord;
        }
        
        public void deleteEntry(final String s) {
            this.keyHash.remove(s);
        }
    }
}
