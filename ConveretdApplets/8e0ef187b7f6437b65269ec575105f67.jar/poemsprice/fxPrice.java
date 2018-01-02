// 
// Decompiled by Procyon v0.5.30
// 

package poemsprice;

import java.util.Date;

public class fxPrice
{
    private String _contract;
    private String _bidPrice;
    private String _askPrice;
    private String _lowPrice;
    private String _highPrice;
    private String _updatedTime;
    private int _Tradable;
    private int _appTradable;
    private Date _LastUpdatedTime;
    private String _Name;
    private String _ReceivedData;
    private String _TimeStamp;
    private String _openPrice;
    private String _closePrice;
    
    public fxPrice() {
        this._contract = "-";
        this._bidPrice = "0.0";
        this._askPrice = "0.0";
        this._lowPrice = "-";
        this._highPrice = "-";
        this._updatedTime = "-";
        this._Tradable = -1;
        this._appTradable = -1;
        this._LastUpdatedTime = null;
        this._Name = "";
        this._openPrice = "-";
        this._closePrice = "-";
    }
    
    public void setContract(final String value) {
        this._contract = value;
    }
    
    public String getContract() {
        return this._contract;
    }
    
    public void setAskPrice(final String value) {
        this._askPrice = value;
    }
    
    public String getAskPrice() {
        return this._askPrice;
    }
    
    public void setBidPrice(final String value) {
        this._bidPrice = value;
    }
    
    public String getBidPrice() {
        return this._bidPrice;
    }
    
    public void setLowPrice(final String value) {
        this._lowPrice = value;
    }
    
    public String getLowPrice() {
        return this._lowPrice;
    }
    
    public void setHighPrice(final String value) {
        this._highPrice = value;
    }
    
    public String getHighPrice() {
        return this._highPrice;
    }
    
    public void setUpdatedTime(final String value) {
        this._updatedTime = value;
    }
    
    public String getUpdatedTime() {
        return this._updatedTime;
    }
    
    public void setTradablePrice(final int value) {
        this._Tradable = value;
    }
    
    public int getTradablePrice() {
        return this._Tradable;
    }
    
    public void setAppTradablePrice(final int value) {
        this._appTradable = value;
    }
    
    public int getAppTradablePrice() {
        return this._appTradable;
    }
    
    public void setLastFeederUpdatedTime(final Date value) {
        this._LastUpdatedTime = value;
    }
    
    public Date getFeederUpdatedTime() {
        return this._LastUpdatedTime;
    }
    
    public void setName(final String value) {
        this._Name = value;
    }
    
    public String getName() {
        return this._Name;
    }
    
    public fxPrice getCloneObject() {
        final fxPrice priceObject = new fxPrice();
        priceObject._askPrice = this._askPrice;
        priceObject._Tradable = this._Tradable;
        priceObject._bidPrice = this._bidPrice;
        priceObject._contract = this._contract;
        priceObject._highPrice = this._highPrice;
        priceObject._lowPrice = this._lowPrice;
        priceObject._updatedTime = this._updatedTime;
        priceObject._appTradable = this._appTradable;
        priceObject._LastUpdatedTime = this._LastUpdatedTime;
        priceObject._Name = this._Name;
        priceObject._ReceivedData = this._ReceivedData;
        priceObject._TimeStamp = this._TimeStamp;
        priceObject._openPrice = this._openPrice;
        priceObject._closePrice = this._closePrice;
        return priceObject;
    }
    
    public void setReceivedData(final String value) {
        this._ReceivedData = value;
    }
    
    public String getReceivedData() {
        return this._ReceivedData;
    }
    
    public void setTimeStamp(final String value) {
        this._TimeStamp = value;
    }
    
    public String getTimeStamp() {
        return this._TimeStamp;
    }
    
    public void setOpenPrice(final String value) {
        this._openPrice = value;
    }
    
    public String getOpenPrice() {
        return this._openPrice;
    }
    
    public void setClosePrice(final String value) {
        this._closePrice = value;
    }
    
    public String getClosePrice() {
        return this._closePrice;
    }
    
    public boolean isNumeric(final String str) {
        try {
            final double i = Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException exp) {
            return false;
        }
    }
}
