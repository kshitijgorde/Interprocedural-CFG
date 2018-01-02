// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import java.sql.SQLWarning;
import java.sql.SQLException;
import org.apache.xml.dtm.DTMManager;

public class SQLErrorDocument extends DTMDocument
{
    private static final String S_EXT_ERROR = "ext-error";
    private static final String S_SQL_ERROR = "sql-error";
    private static final String S_MESSAGE = "message";
    private static final String S_CODE = "code";
    private static final String S_STATE = "state";
    private static final String S_SQL_WARNING = "sql-warning";
    private int m_ErrorExt_TypeID;
    private int m_Message_TypeID;
    private int m_Code_TypeID;
    private int m_State_TypeID;
    private int m_SQLWarning_TypeID;
    private int m_SQLError_TypeID;
    private int m_rootID;
    private int m_extErrorID;
    private int m_MainMessageID;
    
    public SQLErrorDocument(final DTMManager mgr, final int ident, final SQLException error) {
        super(mgr, ident);
        this.m_ErrorExt_TypeID = -1;
        this.m_Message_TypeID = -1;
        this.m_Code_TypeID = -1;
        this.m_State_TypeID = -1;
        this.m_SQLWarning_TypeID = -1;
        this.m_SQLError_TypeID = -1;
        this.m_rootID = -1;
        this.m_extErrorID = -1;
        this.m_MainMessageID = -1;
        this.createExpandedNameTable();
        this.buildBasicStructure(error);
        final int sqlError = this.addElement(2, this.m_SQLError_TypeID, this.m_extErrorID, this.m_MainMessageID);
        int element = -1;
        element = this.addElementWithData(new Integer(error.getErrorCode()), 3, this.m_Code_TypeID, sqlError, element);
        element = this.addElementWithData(error.getLocalizedMessage(), 3, this.m_Message_TypeID, sqlError, element);
    }
    
    public SQLErrorDocument(final DTMManager mgr, final int ident, final Exception error) {
        super(mgr, ident);
        this.m_ErrorExt_TypeID = -1;
        this.m_Message_TypeID = -1;
        this.m_Code_TypeID = -1;
        this.m_State_TypeID = -1;
        this.m_SQLWarning_TypeID = -1;
        this.m_SQLError_TypeID = -1;
        this.m_rootID = -1;
        this.m_extErrorID = -1;
        this.m_MainMessageID = -1;
        this.createExpandedNameTable();
        this.buildBasicStructure(error);
    }
    
    public SQLErrorDocument(final DTMManager mgr, final int ident, final Exception error, final SQLWarning warning, final boolean full) {
        super(mgr, ident);
        this.m_ErrorExt_TypeID = -1;
        this.m_Message_TypeID = -1;
        this.m_Code_TypeID = -1;
        this.m_State_TypeID = -1;
        this.m_SQLWarning_TypeID = -1;
        this.m_SQLError_TypeID = -1;
        this.m_rootID = -1;
        this.m_extErrorID = -1;
        this.m_MainMessageID = -1;
        this.createExpandedNameTable();
        this.buildBasicStructure(error);
        SQLException se = null;
        int prev = this.m_MainMessageID;
        boolean inWarnings = false;
        if (error != null && error instanceof SQLException) {
            se = (SQLException)error;
        }
        else if (full && warning != null) {
            se = warning;
            inWarnings = true;
        }
        while (se != null) {
            final int sqlError = prev = this.addElement(2, inWarnings ? this.m_SQLWarning_TypeID : this.m_SQLError_TypeID, this.m_extErrorID, prev);
            int element = -1;
            element = this.addElementWithData(new Integer(se.getErrorCode()), 3, this.m_Code_TypeID, sqlError, element);
            element = this.addElementWithData(se.getLocalizedMessage(), 3, this.m_Message_TypeID, sqlError, element);
            if (full) {
                final String state = se.getSQLState();
                if (state != null && state.length() > 0) {
                    element = this.addElementWithData(state, 3, this.m_State_TypeID, sqlError, element);
                }
                if (inWarnings) {
                    se = ((SQLWarning)se).getNextWarning();
                }
                else {
                    se = se.getNextException();
                }
            }
            else {
                se = null;
            }
        }
    }
    
    private void buildBasicStructure(final Exception e) {
        this.m_rootID = this.addElement(0, super.m_Document_TypeID, -1, -1);
        this.m_extErrorID = this.addElement(1, this.m_ErrorExt_TypeID, this.m_rootID, -1);
        this.m_MainMessageID = this.addElementWithData((e != null) ? e.getLocalizedMessage() : "SQLWarning", 2, this.m_Message_TypeID, this.m_extErrorID, -1);
    }
    
    protected void createExpandedNameTable() {
        super.createExpandedNameTable();
        this.m_ErrorExt_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "ext-error", 1);
        this.m_SQLError_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "sql-error", 1);
        this.m_Message_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "message", 1);
        this.m_Code_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "code", 1);
        this.m_State_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "state", 1);
        this.m_SQLWarning_TypeID = super.m_expandedNameTable.getExpandedTypeID("http://xml.apache.org/xalan/SQLExtension", "sql-warning", 1);
    }
}
