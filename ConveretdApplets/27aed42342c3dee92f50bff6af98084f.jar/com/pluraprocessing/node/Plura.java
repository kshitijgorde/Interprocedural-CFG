// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.node;

import com.pluraprocessing.common.utility.FilePathUtility;
import netscape.javascript.JSObject;
import java.util.UUID;
import com.pluraprocessing.common.xml.RequestedWorkParser;
import com.pluraprocessing.common.utility.PluraHttpConnectionUtility;
import com.pluraprocessing.common.utility.HttpMethod;
import java.util.List;
import com.pluraprocessing.common.domain.HttpResponse;
import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import com.pluraprocessing.common.domain.Data;
import com.pluraprocessing.common.utility.BitConversionUtility;
import com.pluraprocessing.node.customer.restricted.PluraRestrictedConnectorFactory;
import com.pluraprocessing.node.exception.DestroyIPluraConnectorException;
import com.pluraprocessing.node.customer.PluraConnectorFactory;
import com.pluraprocessing.common.domain.RequestedWork;
import java.applet.Applet;
import com.pluraprocessing.node.customer.restricted.IPluraRestrictedConnector;
import com.pluraprocessing.node.customer.IPluraConnector;
import com.pluraprocessing.node.customer.Result;
import java.util.HashMap;

public class Plura
{
    private int numberCycles;
    private long averageCompute;
    private long startTime;
    private long endTime;
    private long getWorkOverheadMS;
    private long computeMS;
    private long uploadWorkOverheadMS;
    private HashMap<String, Object> currentData;
    private byte[] completedResult;
    private byte[] ba;
    private byte[] dataConverted;
    private byte[] restrictedAPIResult;
    private String convertedResult;
    private Result fullAPIResult;
    private Result completedFullAPIResult;
    private IPluraConnector plura;
    private IPluraRestrictedConnector pluraRestricted;
    private Applet applet;
    private KillWUThread killThread;
    
    public Plura(final Applet applet) {
        this.numberCycles = 0;
        this.averageCompute = 0L;
        this.currentData = new HashMap<String, Object>();
        this.applet = applet;
    }
    
    public void run(final RequestedWork workUnitToComplete) {
        long timeToSleep = 0L;
        boolean incrementNoWorkCounter = false;
        try {
            Long bandwidthUsedKB = 0L;
            this.checkForInterruption();
            Label_0177: {
                if (!PluraRuntime.getInstance().usesLimitedAPI() && this.plura == null) {
                    (this.plura = PluraConnectorFactory.getPluraConnector()).initialize(PluraRuntime.getInstance().getBandwidthPercent(), PluraRuntime.getInstance().getPercentageCPU());
                    try {
                        if (PluraRuntime.getInstance().usesLimitedAPI()) {
                            if (this.pluraRestricted.killJVM()) {
                                this.killJVM();
                            }
                        }
                        else if (this.plura.killJVM()) {
                            this.killJVM();
                        }
                    }
                    catch (DestroyIPluraConnectorException e2) {
                        try {
                            this.plura.stopCompute();
                        }
                        catch (Throwable t) {
                            break Label_0177;
                        }
                        finally {
                            this.changeVersionAndMarkAsBad();
                        }
                        this.changeVersionAndMarkAsBad();
                    }
                }
                else if (PluraRuntime.getInstance().usesLimitedAPI() && this.pluraRestricted == null) {
                    this.pluraRestricted = PluraRestrictedConnectorFactory.getPluraRestrictedConnector();
                }
            }
            this.checkForInterruption();
            try {
                if (this.killThread == null || !this.killThread.isAlive()) {
                    (this.killThread = new KillWUThread(this)).start();
                }
                if (!PluraRuntime.getInstance().usesLimitedAPI() && this.plura.isBandwidthApplication() && workUnitToComplete == null) {
                    final double bandwidthLeft = PluraRuntime.getInstance().getAvailableBandwidth();
                    if (bandwidthLeft <= 0.0 && bandwidthLeft != -1.0) {
                        PluraRuntime.getInstance().setStopped(true);
                    }
                }
                this.checkForInterruption();
                this.startTime = System.currentTimeMillis();
                RequestedWork rw = null;
                if (workUnitToComplete != null) {
                    rw = workUnitToComplete;
                }
                else {
                    rw = this.requestWork(this.applet);
                    PluraRuntime.getInstance().setNumberTimesNoWork(0);
                }
                if (this.applet == null && workUnitToComplete == null) {
                    bandwidthUsedKB += PluraRuntime.getInstance().accountForBytesDownloaded() / 1024L;
                }
                this.endTime = System.currentTimeMillis();
                this.getWorkOverheadMS = this.endTime - this.startTime;
                rw.getWork().setByteContent(BitConversionUtility.convert6bitTo8bit(rw.getWork().getContent()));
                bandwidthUsedKB += (Long)(rw.getNumberBytesInRequest() / 1024);
                this.checkForInterruption();
                this.currentData.clear();
                for (final Data data : rw.getWork().getData()) {
                    final Object dataContent = PluraRuntime.getInstance().getAllData().get(data.getId().toString());
                    if (dataContent == null) {
                        PluraRuntime.print(String.valueOf(PluraRuntime.now()) + " Getting Data = " + data.getId().toString(), false);
                        final HttpResponse response = getData(data.getId());
                        this.ba = response.getResponseBody();
                        bandwidthUsedKB += (Long)(response.getResponseByteArraySize() / 1024);
                        this.dataConverted = BitConversionUtility.convert6bitTo8bit(this.ba);
                        PluraRuntime.print(String.valueOf(PluraRuntime.now()) + " Got Data = " + data.getId().toString(), false);
                        Object desData;
                        if (PluraRuntime.getInstance().usesLimitedAPI()) {
                            desData = this.pluraRestricted.deserializeData(this.dataConverted);
                        }
                        else {
                            desData = this.plura.deserializeData(this.dataConverted);
                        }
                        this.currentData.put(data.getId().toString(), desData);
                    }
                    else {
                        this.currentData.put(data.getId().toString(), dataContent);
                    }
                    this.checkForInterruption();
                }
                if (PluraRuntime.getInstance().usesLimitedAPI()) {
                    this.startTime = System.currentTimeMillis();
                    final List<Object> workPieces = (List<Object>)this.pluraRestricted.deserializeWork(rw.getWork().getByteContent(), (HashMap)this.currentData);
                    this.endTime = System.currentTimeMillis();
                    this.sleep(this.startTime, this.endTime, 0L);
                    for (final Object pieceWork : workPieces) {
                        this.startTime = System.currentTimeMillis();
                        this.restrictedAPIResult = this.pluraRestricted.process(pieceWork, (HashMap)this.currentData, this.restrictedAPIResult);
                        if (this.restrictedAPIResult == null) {
                            throw new Exception("Null result. Discontinuing work unit.");
                        }
                        this.endTime = System.currentTimeMillis();
                        this.computeMS = this.sleep(this.startTime, this.endTime, this.computeMS);
                        this.checkForInterruption();
                    }
                    this.startTime = System.currentTimeMillis();
                    this.completedResult = this.pluraRestricted.finalizeWork(this.restrictedAPIResult);
                    this.convertedResult = BitConversionUtility.convert8bitTo6bit(this.completedResult);
                    this.endTime = System.currentTimeMillis();
                    this.sleep(this.startTime, this.endTime, 0L);
                    this.checkForInterruption();
                }
                else {
                    List<Object> workPieces = new ArrayList<Object>();
                    this.startTime = System.currentTimeMillis();
                    workPieces = (List<Object>)this.plura.deserializeWork(rw.getWork().getByteContent(), (HashMap)this.currentData);
                    this.endTime = System.currentTimeMillis();
                    this.sleep(this.startTime, this.endTime, 0L);
                    this.fullAPIResult = new Result();
                    this.computeMS = 0L;
                    this.checkForInterruption();
                    for (final Object pieceWork : workPieces) {
                        this.startTime = System.currentTimeMillis();
                        this.fullAPIResult = this.plura.compute(pieceWork, (HashMap)this.currentData, this.fullAPIResult);
                        if (this.fullAPIResult == null || this.fullAPIResult.getResultBytes() == null) {
                            throw new Exception("Null result. Discontinuing work unit.");
                        }
                        bandwidthUsedKB += this.fullAPIResult.getDownloadTotalKB();
                        this.endTime = System.currentTimeMillis();
                        this.computeMS = this.sleep(this.startTime, this.endTime, this.computeMS);
                        this.checkForInterruption();
                    }
                    this.startTime = System.currentTimeMillis();
                    this.completedFullAPIResult = this.plura.finalizeWork(this.fullAPIResult);
                    if (this.completedFullAPIResult == null || this.completedFullAPIResult.getResultBytes() == null) {
                        throw new Exception("Null result. Discontinuing work unit.");
                    }
                    PluraRuntime.print("RESULT bytes: " + this.completedFullAPIResult.getResultBytes().length, false);
                    this.convertedResult = BitConversionUtility.convert8bitTo6bit(this.completedFullAPIResult.getResultBytes());
                    this.endTime = System.currentTimeMillis();
                    this.sleep(this.startTime, this.endTime, 0L);
                    this.checkForInterruption();
                }
                this.startTime = System.currentTimeMillis();
                if (workUnitToComplete != null) {
                    bandwidthUsedKB = 0L;
                }
                this.completeWork(rw.getWork().getId(), this.convertedResult, PluraRuntime.getInstance().getPluraServerName(), bandwidthUsedKB, rw.getDateRequested().getTimeInMillis());
                this.endTime = System.currentTimeMillis();
                this.uploadWorkOverheadMS = this.endTime - this.startTime;
                this.checkForInterruption();
                PluraRuntime.getInstance().saveData(this.currentData);
                ++this.numberCycles;
                this.averageCompute = (this.averageCompute * (this.numberCycles - 1) + this.computeMS) / this.numberCycles;
                PluraRuntime.print(String.valueOf(PluraRuntime.now()) + " Get(" + this.numberCycles + ")=" + this.getWorkOverheadMS + "ms, Compute=" + this.computeMS + "ms, Upload=" + this.uploadWorkOverheadMS + "ms, Sleep=" + this.computeMS * PluraRuntime.getInstance().getSleepPercent() + "ms, Avg Compute=" + this.averageCompute + "ms, BW(KB)=" + bandwidthUsedKB, true);
            }
            catch (IOException ex) {
                PluraRuntime.print(String.valueOf(PluraRuntime.now()) + " No work, sleep " + PluraRuntime.getInstance().numberMsSleepNoWork + " ms.", false);
                this.checkForInterruption();
                timeToSleep = PluraRuntime.getInstance().numberMsSleepNoWork;
                incrementNoWorkCounter = true;
            }
        }
        catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
        }
        catch (Exception e) {
            PluraRuntime.print(String.valueOf(PluraRuntime.now()) + " Oopsie. Sleeping for " + PluraRuntime.getInstance().numberMsSleepException + " ms. " + e.getMessage(), false);
            timeToSleep = PluraRuntime.getInstance().numberMsSleepException;
            incrementNoWorkCounter = true;
        }
        finally {
            if (this.killThread != null) {
                this.killThread.stopThread();
            }
            try {
                if (PluraRuntime.getInstance().usesLimitedAPI()) {
                    if (this.pluraRestricted.killJVM()) {
                        this.killJVM();
                    }
                }
                else if (this.plura.killJVM()) {
                    this.killJVM();
                }
            }
            catch (DestroyIPluraConnectorException e4) {
                try {
                    this.plura.stopCompute();
                }
                catch (Throwable t2) {
                    try {
                        this.changeVersionAndMarkAsBad();
                    }
                    catch (InterruptedException e5) {
                        Thread.currentThread().interrupt();
                    }
                }
                finally {
                    try {
                        this.changeVersionAndMarkAsBad();
                    }
                    catch (InterruptedException e5) {
                        Thread.currentThread().interrupt();
                    }
                }
                try {
                    this.changeVersionAndMarkAsBad();
                }
                catch (InterruptedException e5) {
                    Thread.currentThread().interrupt();
                }
            }
            if (incrementNoWorkCounter) {
                if (this.applet == null) {
                    PluraRuntime.getInstance().logNoWork();
                }
                else {
                    PluraRuntime.getInstance().setNumberTimesNoWorkApplet(PluraRuntime.getInstance().getNumberTimesNoWorkApplet() + 1);
                    if (PluraRuntime.getInstance().getNumberTimesNoWorkApplet() > PluraRuntime.getInstance().getNoWorkAppletThreshhold()) {
                        PluraRuntime.getInstance().setNumberTimesNoWorkApplet(0);
                        this.refreshPage(this.applet, "", PluraRuntime.getInstance().getPluraServerName());
                    }
                }
            }
            this.sleep(timeToSleep);
        }
        if (this.killThread != null) {
            this.killThread.stopThread();
        }
        try {
            if (PluraRuntime.getInstance().usesLimitedAPI()) {
                if (this.pluraRestricted.killJVM()) {
                    this.killJVM();
                }
            }
            else if (this.plura.killJVM()) {
                this.killJVM();
            }
        }
        catch (DestroyIPluraConnectorException e4) {
            try {
                this.plura.stopCompute();
            }
            catch (Throwable t3) {
                try {
                    this.changeVersionAndMarkAsBad();
                }
                catch (InterruptedException e5) {
                    Thread.currentThread().interrupt();
                }
            }
            finally {
                try {
                    this.changeVersionAndMarkAsBad();
                }
                catch (InterruptedException e5) {
                    Thread.currentThread().interrupt();
                }
            }
            try {
                this.changeVersionAndMarkAsBad();
            }
            catch (InterruptedException e5) {
                Thread.currentThread().interrupt();
            }
        }
        if (incrementNoWorkCounter) {
            if (this.applet == null) {
                PluraRuntime.getInstance().logNoWork();
            }
            else {
                PluraRuntime.getInstance().setNumberTimesNoWorkApplet(PluraRuntime.getInstance().getNumberTimesNoWorkApplet() + 1);
                if (PluraRuntime.getInstance().getNumberTimesNoWorkApplet() > PluraRuntime.getInstance().getNoWorkAppletThreshhold()) {
                    PluraRuntime.getInstance().setNumberTimesNoWorkApplet(0);
                    this.refreshPage(this.applet, "", PluraRuntime.getInstance().getPluraServerName());
                }
            }
        }
        this.sleep(timeToSleep);
    }
    
    public RequestedWork requestWork(final Applet applet) throws IOException, Exception {
        String parameters = "?client=" + PluraRuntime.getInstance().getClientId() + "&" + "version" + "=" + PluraRuntime.getInstance().getCurrentVersionId();
        if (PluraRuntime.getInstance().getAffiliateId() != null && !PluraRuntime.getInstance().getAffiliateId().equals("")) {
            parameters = String.valueOf(parameters) + "&" + "affiliate" + "=" + PluraRuntime.getInstance().getAffiliateId();
        }
        if (PluraRuntime.getInstance().getCurrentNodeType() != null) {
            parameters = String.valueOf(parameters) + "&" + "nodeType" + "=" + PluraRuntime.getInstance().getCurrentNodeType().name();
        }
        final HttpResponse response = PluraHttpConnectionUtility.httpRequestByIP(PluraRuntime.getInstance().getPluraServerName(), 80, String.valueOf(PluraRuntime.getInstance().PLURA_CLIENT_APPLET_LOCATION) + parameters, "", HttpMethod.GET);
        final String xmlResponse = response.getResponseBodyAsTrimmedUTF8String();
        if (xmlResponse.contains("<failure />")) {
            throw new IOException("PluraClient GET failed.");
        }
        final RequestedWork rw = RequestedWorkParser.getRequestedObject(xmlResponse);
        rw.setNumberBytesInRequest(response.getResponseByteArraySize());
        return rw;
    }
    
    public void completeWork(final UUID workId, final String result, final String pluraServer, final Long bandwidthUsed, final Long dateCreated) throws Exception {
        try {
            final String output = RequestedWorkParser.getCompletedWorkXML(result, workId.toString(), PluraRuntime.getInstance().getPercentUsed(), bandwidthUsed, PluraRuntime.getInstance().getCurrentVersionId(), dateCreated, PluraRuntime.getInstance().getAffiliateId(), PluraRuntime.getInstance().getClientId());
            final String xml = PluraHttpConnectionUtility.httpRequestByIP(pluraServer, 80, PluraRuntime.getInstance().PLURA_CLIENT_APPLET_LOCATION, output, HttpMethod.POST).getResponseBodyAsTrimmedUTF8String();
            if (xml.contains("failure")) {
                throw new Exception("PluraClient POST failed.");
            }
        }
        catch (Throwable e) {
            throw new Exception(e);
        }
    }
    
    public static HttpResponse getData(final UUID dataId) throws Exception {
        return PluraHttpConnectionUtility.httpRequestByIP(PluraRuntime.getInstance().PLURA_DATA_SVR, 80, String.valueOf(getDataFilepathString(dataId)) + "/" + dataId.toString(), "", HttpMethod.GET);
    }
    
    private void changeVersionAndMarkAsBad() throws InterruptedException {
        PluraRuntime.getInstance().getBadVersions().add(PluraRuntime.getInstance().getCurrentVersionId());
        PluraRuntime.getInstance().setStopped(true);
        PluraRuntime.getInstance().changeVersion();
        this.checkForInterruption();
    }
    
    public void sleep(final long timeToSleepMs) {
        try {
            for (int i = 0; i < timeToSleepMs / 100L; ++i) {
                Thread.sleep(100L);
                this.checkForInterruption();
            }
        }
        catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void refreshPage(final Applet applet, final String version, final String pluraServerName) {
        final JSObject window = JSObject.getWindow(applet);
        final String[] param = new String[2];
        if (version != null && !version.equals("")) {
            param[0] = version;
        }
        if (pluraServerName != null && !pluraServerName.equals("")) {
            param[1] = pluraServerName;
        }
        window.call("reload_page", (Object[])param);
    }
    
    private long sleep(final long start, final long end, final long totalTime) {
        this.sleep((long)((end - start) * PluraRuntime.getInstance().getSleepPercent()));
        return totalTime + end - start;
    }
    
    public static String getDataFilepathString(final UUID id) {
        return FilePathUtility.getDataWebDirectoryString(id, "", PluraRuntime.getInstance().NUMBER_DISKS_DATA_SERVER, PluraRuntime.getInstance().DATA_BASE_DIRECTORY, PluraRuntime.getInstance().NODE_SERVER_DISK_NAME);
    }
    
    private void checkForInterruption() throws InterruptedException {
        if (PluraRuntime.getInstance().isStopped()) {
            throw new InterruptedException("Plura has been stopped.");
        }
    }
    
    public void stopCompute() {
        if (this.pluraRestricted != null) {
            this.pluraRestricted.stopCompute();
        }
        else if (this.plura != null) {
            this.plura.stopCompute();
        }
    }
    
    public void killJVM() {
        PluraRuntime.print(String.valueOf(PluraRuntime.now()) + " Timeout error. Killing Plura.", false);
        this.stopCompute();
        PluraRuntime.getInstance().getPlura().stopPluraThreads(false, true);
        System.exit(0);
    }
}
