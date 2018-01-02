// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.lib.sql;

import org.w3c.dom.DOMException;
import java.sql.SQLException;
import org.w3c.dom.Node;
import java.util.Hashtable;
import java.sql.ResultSetMetaData;
import org.w3c.dom.Element;
import org.w3c.dom.Attr;

public class ColumnAttribute extends StreamableNode implements Attr
{
    Element m_owner;
    ResultSetMetaData m_metadata;
    String m_name;
    int m_type;
    int m_columnIndex;
    ColumnAttribute m_columnAttr;
    static final String S_ISTRUE = "true";
    static final String S_ISFALSE = "false";
    public static final int CATALOGUE_NAME = 0;
    public static final int DISPLAY_SIZE = 1;
    public static final int COLUMN_LABEL = 2;
    public static final int COLUMN_NAME = 3;
    public static final int COLUMN_TYPE = 4;
    public static final int COLUMN_TYPENAME = 5;
    public static final int PRECISION = 6;
    public static final int SCALE = 7;
    public static final int SCHEMA_NAME = 8;
    public static final int TABLE_NAME = 9;
    public static final int CASESENSITIVE = 10;
    public static final int DEFINATELYWRITABLE = 11;
    public static final int ISNULLABLE = 12;
    public static final int ISSIGNED = 13;
    public static final int ISWRITEABLE = 14;
    public static final int ISSEARCHABLE = 15;
    public static final int NUMBER_ATTRIBUTES = 16;
    public static final String S_CATALOGUE_NAME = "catalogue-name";
    public static final String S_DISPLAY_SIZE = "column-display-size";
    public static final String S_COLUMN_LABEL = "column-label";
    public static final String S_COLUMN_NAME = "column-name";
    public static final String S_COLUMN_TYPE = "column-type";
    public static final String S_COLUMN_TYPENAME = "column-type-name";
    public static final String S_PRECISION = "precision";
    public static final String S_SCALE = "scale";
    public static final String S_SCHEMA_NAME = "schema-name";
    public static final String S_TABLE_NAME = "table-name";
    public static final String S_CASESENSITIVE = "case-sensitive";
    public static final String S_DEFINATELYWRITABLE = "definitely-writable";
    public static final String S_ISNULLABLE = "nullable";
    public static final String S_ISSIGNED = "signed";
    public static final String S_ISWRITEABLE = "writable";
    public static final String S_ISSEARCHABLE = "searchable";
    static Hashtable m_namelookup;
    
    static {
        (ColumnAttribute.m_namelookup = new Hashtable()).put("catalogue-name", new Integer(0));
        ColumnAttribute.m_namelookup.put("column-display-size", new Integer(1));
        ColumnAttribute.m_namelookup.put("column-label", new Integer(2));
        ColumnAttribute.m_namelookup.put("column-name", new Integer(3));
        ColumnAttribute.m_namelookup.put("column-type", new Integer(4));
        ColumnAttribute.m_namelookup.put("column-type-name", new Integer(5));
        ColumnAttribute.m_namelookup.put("precision", new Integer(6));
        ColumnAttribute.m_namelookup.put("scale", new Integer(7));
        ColumnAttribute.m_namelookup.put("schema-name", new Integer(8));
        ColumnAttribute.m_namelookup.put("table-name", new Integer(9));
        ColumnAttribute.m_namelookup.put("case-sensitive", new Integer(10));
        ColumnAttribute.m_namelookup.put("definitely-writable", new Integer(11));
        ColumnAttribute.m_namelookup.put("nullable", new Integer(12));
        ColumnAttribute.m_namelookup.put("signed", new Integer(13));
        ColumnAttribute.m_namelookup.put("writable", new Integer(14));
        ColumnAttribute.m_namelookup.put("searchable", new Integer(15));
    }
    
    public ColumnAttribute(final XStatement statement, final Element owner, final int columnIndex, final int type, final ResultSetMetaData metadata) {
        super(statement);
        this.m_owner = owner;
        this.m_metadata = metadata;
        this.m_columnIndex = columnIndex;
        this.m_type = type;
    }
    
    static String getAttrNameFromPos(final int pos) {
        switch (pos) {
            case 0: {
                return "catalogue-name";
            }
            case 1: {
                return "column-display-size";
            }
            case 2: {
                return "column-label";
            }
            case 3: {
                return "column-name";
            }
            case 4: {
                return "column-type";
            }
            case 5: {
                return "column-type-name";
            }
            case 6: {
                return "precision";
            }
            case 7: {
                return "scale";
            }
            case 8: {
                return "schema-name";
            }
            case 9: {
                return "table-name";
            }
            case 10: {
                return "case-sensitive";
            }
            case 11: {
                return "definitely-writable";
            }
            case 12: {
                return "nullable";
            }
            case 13: {
                return "signed";
            }
            case 14: {
                return "writable";
            }
            case 15: {
                return "searchable";
            }
            default: {
                return null;
            }
        }
    }
    
    static int getAttrPosFromName(final String name) {
        final Integer intObj = ColumnAttribute.m_namelookup.get(name);
        if (intObj != null) {
            return intObj;
        }
        return -1;
    }
    
    public String getName() {
        return this.m_name;
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public short getNodeType() {
        return 2;
    }
    
    public String getNodeValue() {
        return this.getValue();
    }
    
    public Element getOwnerElement() {
        return this.m_owner;
    }
    
    public Node getParentNode() {
        return null;
    }
    
    public boolean getSpecified() {
        return true;
    }
    
    public String getValue() {
        final int i = this.m_columnIndex + 1;
        try {
            switch (this.m_type) {
                case 0: {
                    return this.m_metadata.getCatalogName(i);
                }
                case 1: {
                    return Integer.toString(this.m_metadata.getColumnDisplaySize(i));
                }
                case 2: {
                    return this.m_metadata.getColumnLabel(i);
                }
                case 3: {
                    return this.m_metadata.getColumnName(i);
                }
                case 4: {
                    return Integer.toString(this.m_metadata.getColumnType(i));
                }
                case 5: {
                    return this.m_metadata.getColumnTypeName(i);
                }
                case 6: {
                    return Integer.toString(this.m_metadata.getPrecision(i));
                }
                case 7: {
                    return Integer.toString(this.m_metadata.getScale(i));
                }
                case 8: {
                    return this.m_metadata.getSchemaName(i);
                }
                case 9: {
                    return this.m_metadata.getTableName(i);
                }
                case 10: {
                    return this.m_metadata.isCaseSensitive(i) ? "true" : "false";
                }
                case 11: {
                    return this.m_metadata.isDefinitelyWritable(i) ? "true" : "false";
                }
                case 12: {
                    return Integer.toString(this.m_metadata.isNullable(i));
                }
                case 13: {
                    return this.m_metadata.isSigned(i) ? "true" : "false";
                }
                case 14: {
                    return this.m_metadata.isWritable(i) ? "true" : "false";
                }
                case 15: {
                    return this.m_metadata.isSearchable(i) ? "true" : "false";
                }
                default: {
                    return "";
                }
            }
        }
        catch (SQLException ex) {
            return "SQL ERROR!";
        }
    }
    
    public boolean setName(final String name) {
        this.m_name = name;
        final int i = getAttrPosFromName(name);
        return i >= 0;
    }
    
    public void setValue(final String value) throws DOMException {
        this.error(80);
    }
}
