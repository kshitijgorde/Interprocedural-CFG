// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.comparatorcutter;

import com.mindprod.common11.ST;
import com.mindprod.fastcat.FastCat;

public enum FieldType
{
    SPARE("-- spare --", "", "", ""), 
    CASE_SENSITIVE_STRING("String (case sensitive)", "int", "case sensitively", "%first%getter.compareTo( %second%getter )"), 
    CASE_INSENSITIVE_STRING("String (case insensitive)", "int", "case insensitively", "%first%getter.compareToIgnoreCase( %second%getter )"), 
    STRING_LENGTH("String (length)", "int", "length", "%first%getter.length() - %second%getter.length()"), 
    COMPARABLE("Comparable Object", "int", "Comparable", "%first%getter.compareTo( %second%getter )"), 
    BOOLEAN("boolean", "int", "logically", "( %first%getter ? 1 : 0 ) - ( %second%getter ? 1 : 0 )"), 
    SIGNEDBYTE(" byte", "int", "numerically", "%first%getter - %second%getter"), 
    UNSIGNEDBYTE("/*unsigned*/ byte", "int", "numerically", "( %first%getter & 0xff ) - ( %second%getter & 0xff )"), 
    SHORT("short", "int", "numerically", "%first%getter - %second%getter"), 
    CHAR("char", "int", "alphabetically", "%first%getter - %second%getter"), 
    INT("int", "int", "numerically", "%first%getter - %second%getter"), 
    LONG("long", "long", "numerically", "%first%getter - %second%getter"), 
    FLOAT("float", "float", "numerically", "%first%getter - %second%getter"), 
    DOUBLE("double", "double", "numerically", "%first%getter - %second%getter");
    
    private final String comparatorTemplate;
    private final String diffType;
    private final String fieldTypeName;
    private final String sortType;
    
    public String generateIntermediateCompareToLine(final boolean useGenerics, final boolean isComparator, final String oClass, final boolean isFieldDescending, final String fieldName, final int unique) {
        final FastCat sb = new FastCat(17);
        indent(sb, 2);
        sb.append(this.diffType);
        sb.append(" ");
        String diffVar;
        if (unique == 0) {
            diffVar = "diff";
        }
        else {
            diffVar = "diff" + Integer.toString(unique);
        }
        sb.append(diffVar);
        sb.append(" = ");
        sb.append(this.expandCompareToTemplate(useGenerics, isComparator, oClass, isFieldDescending, fieldName));
        sb.append(";\n");
        indent(sb, 2);
        sb.append("if ( ");
        sb.append(diffVar);
        sb.append(" != 0 )\n");
        indent(sb, 3);
        sb.append("{\n");
        indent(sb, 3);
        sb.append(this.generateCompareToLine(useGenerics, diffVar));
        indent(sb, 3);
        sb.append("}\n");
        return sb.toString();
    }
    
    public String generateLastCompareToLine(final boolean useGenerics, final boolean isComparable, final String oClass, final boolean isFieldDescending, final String fieldName) {
        final FastCat sb = new FastCat(2);
        final String diffExp = this.expandCompareToTemplate(useGenerics, isComparable, oClass, isFieldDescending, fieldName);
        indent(sb, 2);
        sb.append(this.generateCompareToLine(useGenerics, diffExp));
        return sb.toString();
    }
    
    public String getDiffType() {
        return this.diffType;
    }
    
    public String getSortType() {
        return this.sortType;
    }
    
    public String toString() {
        return this.fieldTypeName;
    }
    
    private static void indent(final FastCat sb, final int level) {
        sb.append(ST.spaces(level * 4));
    }
    
    private FieldType(final String fieldTypeName, final String diffType, final String sortType, final String comparatorTemplate) {
        this.fieldTypeName = fieldTypeName;
        this.diffType = diffType;
        this.sortType = sortType;
        this.comparatorTemplate = comparatorTemplate;
    }
    
    private String expandCompareToTemplate(final boolean useGenerics, final boolean isComparable, final String oClass, final boolean isFieldDescending, final String fieldName) {
        String first;
        String second;
        if (useGenerics) {
            if (isComparable) {
                if (isFieldDescending) {
                    first = "other";
                    second = "this";
                }
                else {
                    first = "this";
                    second = "other";
                }
            }
            else if (isFieldDescending) {
                first = "b";
                second = "a";
            }
            else {
                first = "a";
                second = "b";
            }
        }
        else if (isComparable) {
            if (isFieldDescending) {
                first = "( ( " + oClass + " ) other )";
                second = "this";
            }
            else {
                first = "this";
                second = "( ( " + oClass + " ) other )";
            }
        }
        else if (isFieldDescending) {
            first = "( ( " + oClass + " ) b )";
            second = "( ( " + oClass + " ) a )";
        }
        else {
            first = "( ( " + oClass + " ) a )";
            second = "( ( " + oClass + " ) b )";
        }
        final String getter = (fieldName.length() == 0) ? "" : ("." + fieldName);
        return this.comparatorTemplate.replace("%first", first).replace("%second", second).replace("%getter", getter);
    }
    
    private String generateCompareToLine(final boolean useGenerics, final String diffExp) {
        final StringBuilder sb = new StringBuilder(100);
        if (this.diffType.equals("int")) {
            sb.append("return ");
            sb.append(diffExp);
            sb.append(";\n");
        }
        else {
            sb.append("return ");
            if (useGenerics && this.diffType.equals("long")) {
                sb.append("Long");
            }
            else {
                sb.append("Misc");
            }
            sb.append(".signum( ");
            sb.append(diffExp);
            sb.append(" );\n");
        }
        return sb.toString();
    }
}
