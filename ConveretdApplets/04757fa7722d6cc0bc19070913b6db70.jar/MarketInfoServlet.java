import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import com.icondesigns.dbutil.ConnectionPool;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServlet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketInfoServlet extends HttpServlet
{
    Sector sector;
    MarketActivityInfo mai;
    StatusCurrencyRateInfo scri;
    MarketInfo2 info;
    String dateTime;
    Date date;
    SimpleDateFormat format;
    String sectorId;
    String sectorName;
    double indexPoints;
    double change;
    double percentChange;
    int totalTrades;
    int advances;
    int declines;
    int noChange;
    double totalValue;
    double totalSharesTraded;
    String marketStatus;
    String tradingDate;
    String closeTime;
    double USExRate;
    Object object;
    ConnectionPool pool;
    
    public void init(final ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        this.info = new MarketInfo2();
        this.date = new Date();
        this.format = new SimpleDateFormat("dd-MMM-yyyy,  h:mm:ss a");
    }
    
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            this.getServletContext();
            this.pool = ConnectionPool.getInstance();
        }
        catch (Exception ex4) {
            System.out.println("DATABASE ERROR");
            return;
        }
        this.date.setTime(System.currentTimeMillis());
        this.dateTime = this.format.format(this.date);
        this.info.setDateTime(this.dateTime);
        httpServletResponse.setContentType("application/octet-stream");
        final ObjectOutputStream objectOutputStream = new ObjectOutputStream((OutputStream)httpServletResponse.getOutputStream());
        final String parameter = httpServletRequest.getParameter("target");
        Connection connection = null;
        try {
            connection = this.pool.getConnection();
            Serializable s;
            if (parameter.equals("status")) {
                this.selectMarketStatus(connection);
                s = this.marketStatus;
            }
            else {
                this.buildIndexSectoralAveragesInfo(connection);
                this.buildMarketActivityInfo(connection);
                this.buildStatusCurrencyInfo(connection);
                s = this.info;
                new StringBuffer().append("").append(this.info.getMarketActivityInfo().getTotalTrades()).toString();
            }
            objectOutputStream.writeObject(s);
            objectOutputStream.flush();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
    }
    
    public void selectMarketStatus(final Connection connection) throws ServletException, IOException {
        try {
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select STATUS from MARKET");
            while (executeQuery.next()) {
                this.marketStatus = executeQuery.getString("STATUS");
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void buildIndexSectoralAveragesInfo(final Connection connection) throws ServletException, IOException {
        this.info.clearSectors();
        try {
            final CallableStatement prepareCall = connection.prepareCall("{? = call getMarketInfo()}");
            prepareCall.registerOutParameter(1, -10);
            prepareCall.execute();
            final ResultSet set = (ResultSet)prepareCall.getObject(1);
            int n = 0;
            while (set.next()) {
                this.sectorId = set.getString("INDEX_ID");
                this.sectorName = set.getString("INDEX_NAME");
                this.indexPoints = set.getDouble("INDEX_POINTS");
                this.change = set.getDouble("CHANGE");
                this.percentChange = set.getDouble("PERC_CHANGE");
                this.sector = new Sector(this.sectorId, this.sectorName, this.indexPoints, this.change, this.percentChange);
                this.info.addSector(String.valueOf(n), this.sector);
                ++n;
            }
            set.close();
            prepareCall.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void buildMarketActivityInfo(final Connection connection) throws ServletException, IOException {
        try {
            final CallableStatement prepareCall = connection.prepareCall("{? = call getMarketUpdate()}");
            prepareCall.registerOutParameter(1, -10);
            prepareCall.execute();
            final ResultSet set = (ResultSet)prepareCall.getObject(1);
            while (set.next()) {
                this.totalTrades = set.getInt("TOTAL_TRADES");
                this.totalSharesTraded = set.getDouble("TOTAL_SHARES_TRADED");
                this.totalValue = set.getDouble("TOTAL_VALUE");
                this.advances = set.getInt("ADVANCES");
                this.declines = set.getInt("DECLINES");
                this.noChange = set.getInt("NO_CHANGE");
                this.mai = new MarketActivityInfo(this.totalTrades, this.totalSharesTraded, this.totalValue, this.advances, this.declines, this.noChange);
                this.info.setMarketActivityInfo(this.mai);
            }
            set.close();
            prepareCall.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void buildStatusCurrencyInfo(final Connection connection) throws ServletException, IOException {
        try {
            final Statement statement = connection.createStatement();
            final ResultSet executeQuery = statement.executeQuery("select STATUS, US_EX_RATE, to_char(TRADING_DATE,'DD-Mon-YYYY') as TRADE_DATE, to_char(to_date(CLOSE_TIME,'HHMISS'),'HH:MI:SS AM') as CLOSING_TIME from MARKET");
            while (executeQuery.next()) {
                this.marketStatus = executeQuery.getString("STATUS");
                this.USExRate = executeQuery.getDouble("US_EX_RATE");
                this.tradingDate = executeQuery.getString("TRADE_DATE");
                this.closeTime = executeQuery.getString("CLOSING_TIME");
                this.scri = new StatusCurrencyRateInfo(this.marketStatus, this.USExRate, this.tradingDate, this.closeTime);
                this.info.setStatusCurrencyInfo(this.scri);
                this.info.getStatusCurrencyInfo().getMarketStatus();
            }
            executeQuery.close();
            statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
