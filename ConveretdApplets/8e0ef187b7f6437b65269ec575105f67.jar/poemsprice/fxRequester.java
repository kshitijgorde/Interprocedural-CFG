// 
// Decompiled by Procyon v0.5.30
// 

package poemsprice;

import poemsprice.FXClass.PriceViewer;
import java.util.Enumeration;
import java.io.IOException;
import net.Slingshot.ClientAPI.Record;
import java.net.MalformedURLException;
import net.Slingshot.ClientAPI.RecordRequest;
import net.Slingshot.ClientAPI.HttpConnection;
import java.net.URL;
import java.util.Hashtable;
import net.Slingshot.ClientAPI.HttpConnectionStatus;
import net.Slingshot.ClientAPI.RequestFidCallback;

public class fxRequester implements RequestFidCallback, FeederConstant, HttpConnectionStatus
{
    public boolean bAppletClosing;
    private Hashtable tblRequested;
    private URL m_wdsUrl;
    private String m_wdsProxyUrl;
    private String m_Svc;
    private HttpConnection m_hConn;
    private RecordRequest requested;
    private String Contracts;
    private String[] aContract;
    public int gblDebugMode;
    public String[] aCurrency;
    private Hashtable objTblPricePanel;
    private Hashtable objTblPriceObject;
    private int index;
    private boolean firstData;
    private short[] RequestFieldids;
    
    public fxRequester(final URL ServerURL, final String proxyURL, final String Svc, final String[] aCur) {
        this.bAppletClosing = false;
        this.RequestFieldids = new short[] { 49, 1, 3, 6, 5, 7, 8, 47, 54, 55, 56 };
        this.Contracts = "";
        this.gblDebugMode = 0;
        this.m_wdsUrl = ServerURL;
        this.m_wdsProxyUrl = proxyURL;
        this.m_Svc = Svc;
        this.aCurrency = aCur;
        this.firstData = false;
        this.tblRequested = new Hashtable();
        this.bAppletClosing = false;
    }
    
    public void setPricePanelObject(final Hashtable tblObj) {
        this.objTblPricePanel = tblObj;
    }
    
    public void setPriceObject(final Hashtable tblObj) {
        this.objTblPriceObject = tblObj;
    }
    
    public boolean start() {
        try {
            this.tblRequested.clear();
            this.m_hConn = new HttpConnection(this.m_wdsUrl);
            if (this.m_wdsProxyUrl.trim().length() > 0) {
                this.m_hConn.setProxy(new URL(this.m_wdsProxyUrl));
            }
            this.m_hConn.setStatus(this);
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return false;
        }
        try {
            this.m_hConn.connect();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return false;
        }
        return true;
    }
    
    private void requestObject() {
        try {
            for (int i = 0; i < this.aCurrency.length; ++i) {
                this.request(this.aCurrency[i].trim(), this.RequestFieldids);
            }
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    
    public void request(final String key, final short[] fids) {
        final RecordRequest monReq = new RecordRequest();
        (this.requested = monReq).setService(this.m_Svc);
        monReq.setKey(key);
        for (int iLen = fids.length, i = 0; i < iLen; ++i) {
            monReq.setFid(new Short(fids[i]), this);
        }
        try {
            this.m_hConn.request(monReq);
            this.tblRequested.put(key, monReq);
            if (this.gblDebugMode > 0) {
                System.out.println("[" + this.m_Svc + "][" + key + "][Requested]");
            }
        }
        catch (IOException ex) {
            System.out.println("Error:" + ex.getStackTrace());
        }
    }
    
    private void releaseAll() {
        try {
            final Enumeration f = this.tblRequested.elements();
            while (f.hasMoreElements()) {
                RecordRequest reqOBJ = f.nextElement();
                if (reqOBJ != null) {
                    reqOBJ = reqOBJ.unmonitor();
                    this.tblRequested.remove(reqOBJ.getKey());
                    if (this.gblDebugMode <= 0) {
                        continue;
                    }
                    System.out.println("[" + this.m_Svc + "][" + reqOBJ.getKey() + "][released]");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Release exception");
            e.printStackTrace();
        }
    }
    
    public void stop() {
        this.releaseAll();
        this.m_hConn.close();
    }
    
    public void status(final int State, final String stateTxt) {
        System.err.println("Connection State:" + stateTxt);
        if (!this.bAppletClosing) {
            final HttpConnection hConn = this.m_hConn;
            if (State == 1) {
                this.requestObject();
                return;
            }
        }
        if (!this.bAppletClosing && State == 2) {
            this.setPricePanelAsBadPrice();
        }
        else if (!this.bAppletClosing && State > 2) {
            this.setPricePanelAsBadPrice();
            this.start();
        }
    }
    
    private void setPricePanelAsBadPrice() {
        try {
            final Enumeration f = this.objTblPricePanel.elements();
            while (f.hasMoreElements()) {
                final PriceViewer objCur = f.nextElement();
                if (objCur != null) {
                    objCur.refreshAppletBadPrice("2");
                }
            }
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    
    public String getFieldValue(final String key, final short Fid) {
        String fieldValue = "";
        try {
            if (this.objTblPricePanel != null) {
                final PriceViewer objCur = this.objTblPricePanel.get(key);
                if (objCur != null && objCur.myData != null) {
                    switch (Fid) {
                        case 1: {
                            fieldValue = objCur.myData.getName();
                            break;
                        }
                        case 3: {
                            fieldValue = objCur.myData.getContract();
                            break;
                        }
                        case 5: {
                            fieldValue = objCur.myData.getBidPrice();
                            break;
                        }
                        case 6: {
                            fieldValue = objCur.myData.getAskPrice();
                            break;
                        }
                        case 13: {
                            fieldValue = objCur.myData.getOpenPrice();
                            break;
                        }
                        case 7: {
                            fieldValue = objCur.myData.getHighPrice();
                            break;
                        }
                        case 8: {
                            fieldValue = objCur.myData.getLowPrice();
                            break;
                        }
                        case 18: {
                            fieldValue = objCur.myData.getClosePrice();
                            break;
                        }
                        case 49: {
                            fieldValue = String.valueOf(objCur.myData.getTradablePrice());
                            break;
                        }
                        case 47: {
                            fieldValue = String.valueOf(objCur.myData.getUpdatedTime());
                            break;
                        }
                        case 149: {
                            fieldValue = String.valueOf(objCur.myData.getAppTradablePrice());
                            break;
                        }
                        case 56: {
                            fieldValue = objCur.myData.getTimeStamp();
                        }
                        case 57: {
                            fieldValue = objCur.myData.getReceivedData();
                            break;
                        }
                    }
                    return fieldValue;
                }
            }
            if (this.objTblPriceObject != null) {
                final fxPrice priceObject = this.objTblPriceObject.get(key);
                if (priceObject != null) {
                    switch (Fid) {
                        case 1: {
                            fieldValue = priceObject.getName();
                            break;
                        }
                        case 3: {
                            fieldValue = priceObject.getContract();
                            break;
                        }
                        case 5: {
                            fieldValue = priceObject.getBidPrice();
                            break;
                        }
                        case 6: {
                            fieldValue = priceObject.getAskPrice();
                            break;
                        }
                        case 13: {
                            fieldValue = priceObject.getOpenPrice();
                            break;
                        }
                        case 7: {
                            fieldValue = priceObject.getHighPrice();
                            break;
                        }
                        case 8: {
                            fieldValue = priceObject.getLowPrice();
                            break;
                        }
                        case 18: {
                            fieldValue = priceObject.getClosePrice();
                            break;
                        }
                        case 49: {
                            fieldValue = String.valueOf(priceObject.getTradablePrice());
                            break;
                        }
                        case 47: {
                            fieldValue = String.valueOf(priceObject.getUpdatedTime());
                            break;
                        }
                        case 149: {
                            fieldValue = String.valueOf(priceObject.getAppTradablePrice());
                            break;
                        }
                        case 56: {
                            fieldValue = priceObject.getTimeStamp();
                        }
                        case 57: {
                            fieldValue = priceObject.getReceivedData();
                            break;
                        }
                    }
                    return fieldValue;
                }
            }
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
        return "";
    }
    
    public void FidUpdate(final String key, final short Fid, final String Value, final short State, final short Offset, final long Flags) {
        if (Value == null) {
            return;
        }
        try {
            if (this.objTblPricePanel != null) {
                final PriceViewer objCur = this.objTblPricePanel.get(key);
                if (objCur != null) {
                    this.firstData = true;
                    if (2 <= this.gblDebugMode) {
                        System.out.println("Log:UPD:" + objCur.objName + " " + Fid + " " + Value + " " + State);
                    }
                    switch (Fid) {
                        case 1: {
                            objCur.setName(Value.toString().trim());
                            break;
                        }
                        case 5: {
                            objCur.callback_Tradable(key, String.valueOf(Fid), Value);
                            break;
                        }
                        case 6: {
                            objCur.callback_Tradable(key, String.valueOf(Fid), Value);
                            break;
                        }
                        case 13: {
                            objCur.setPriceOpen(Value.toString().trim());
                            break;
                        }
                        case 7: {
                            objCur.setPriceHigh(Value.toString().trim());
                            break;
                        }
                        case 8: {
                            objCur.setPriceLow(Value.toString().trim());
                            break;
                        }
                        case 18: {
                            objCur.setPriceClose(Value.toString().trim());
                            break;
                        }
                        case 49: {
                            objCur.refreshAppletBadPrice(Value.trim());
                            objCur.callback_Tradable(key, String.valueOf(49), Value.trim());
                            break;
                        }
                        case 56: {
                            objCur.refreshPrice(Value.toString().trim());
                            break;
                        }
                    }
                    return;
                }
            }
            if (this.objTblPriceObject != null) {
                final fxPrice priceObject = this.objTblPriceObject.get(key);
                if (key != null) {
                    this.firstData = true;
                    if (2 <= this.gblDebugMode) {
                        System.out.println("Log:UPD:" + key + " " + Fid + " " + Value + " " + State);
                    }
                    if (Value != null) {
                        switch (Fid) {
                            case 1: {
                                priceObject.setName(Value.toString().trim());
                                break;
                            }
                            case 5: {
                                priceObject.setBidPrice(Value.trim());
                                break;
                            }
                            case 6: {
                                priceObject.setAskPrice(Value.trim());
                                break;
                            }
                            case 13: {
                                priceObject.setOpenPrice(Value.toString().trim());
                                break;
                            }
                            case 7: {
                                priceObject.setHighPrice(Value.toString().trim());
                                break;
                            }
                            case 8: {
                                priceObject.setLowPrice(Value.toString().trim());
                                break;
                            }
                            case 18: {
                                priceObject.setClosePrice(Value.toString().trim());
                                break;
                            }
                            case 49: {
                                priceObject.setTradablePrice(Integer.parseInt(Value.trim()));
                                priceObject.setAppTradablePrice(Integer.parseInt(Value.trim()));
                                break;
                            }
                            case 56: {
                                priceObject.setReceivedData(Value.toString().trim());
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
