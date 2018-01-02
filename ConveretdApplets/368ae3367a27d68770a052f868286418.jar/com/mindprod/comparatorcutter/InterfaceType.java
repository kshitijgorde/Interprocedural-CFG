// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.comparatorcutter;

import com.mindprod.common11.BigDate;
import com.mindprod.common11.ST;
import com.mindprod.fastcat.FastCat;

public enum InterfaceType
{
    COMPARABLE {
        String compose(final boolean useGenerics, final String gClass, final String oClass, final String comment, final SortKey... sortKeys) {
            final FastCat sb = new FastCat(70);
            sb.append("// <> Generated Comparable Java code to be inserted into ");
            sb.append(oClass);
            sb.append(".java.\n");
            sb.append("// <> package ...\n");
            sb.append("// <> import ...\n");
            sb.append("// <> no need to import java.lang.Comparable;\n");
            if (miscImportNeeded(useGenerics, sortKeys)) {
                sb.append("import static java.lang.System.err; import static java.lang.System.*; import com.mindprod.common11.Misc;\n");
            }
            sb.append("\n");
            sb.append("class ");
            sb.append(oClass);
            sb.append(" implements Comparable");
            if (useGenerics) {
                sb.append("<");
                sb.append(oClass);
                sb.append(">\n");
            }
            sb.append("\n");
            sb.append("// <> {\n");
            sb.append("// <> Insert the generated code, discarding the <> locating comments.\n");
            sb.append("\n");
            indent(sb, 1);
            sb.append("/**\n");
            indent(sb, 1);
            sb.append(" * ");
            sb.append(comment);
            sb.append(".\n");
            indent(sb, 1);
            sb.append(" * Defines default the sort order for ");
            sb.append(oClass);
            sb.append(" Objects.\n");
            indent(sb, 1);
            sb.append(" * Compare this ");
            sb.append(oClass);
            sb.append(" with another ");
            sb.append(oClass);
            sb.append(" ");
            sb.append(useGenerics ? "with" : "without");
            sb.append(" JDK 1.5+ generics.\n");
            if (sortKeys.length != 0) {
                indent(sb, 1);
                sb.append(" * ");
                sb.append(sortKeyOrderInWords(sortKeys));
                sb.append("\n");
            }
            indent(sb, 1);
            sb.append(" * Informally, returns (this-other) or +ve if this sorts after other.\n");
            indent(sb, 1);
            sb.append(" *\n");
            indent(sb, 1);
            sb.append(" * @param other other ");
            sb.append(oClass);
            sb.append(" to compare with this one\n");
            indent(sb, 1);
            sb.append(" *\n");
            indent(sb, 1);
            sb.append(" * @return +ve if this&gt;other, 0 if this==other, -ve if this&lt;other\n");
            indent(sb, 1);
            sb.append(" */\n");
            indent(sb, 1);
            sb.append("public final int compareTo( ");
            if (useGenerics) {
                sb.append(oClass);
            }
            else {
                sb.append("Object");
            }
            sb.append(" other )\n");
            indent(sb, 2);
            sb.append("{\n");
            if (sortKeys.length == 0) {
                indent(sb, 2);
                sb.append("// no sort keys defined yet.\n");
                indent(sb, 2);
                sb.append("return 0; // dummy code for now\n");
            }
            else {
                for (int i = 0; i < sortKeys.length; ++i) {
                    final SortKey sortKey = sortKeys[i];
                    final FieldType fieldType = sortKey.getFieldType();
                    final boolean isFieldDescending = sortKey.isFieldDescending();
                    final String fieldName = sortKey.getFieldName();
                    if (i < sortKeys.length - 1) {
                        sb.append(fieldType.generateIntermediateCompareToLine(useGenerics, true, oClass, isFieldDescending, fieldName, i));
                    }
                    else {
                        sb.append(fieldType.generateLastCompareToLine(useGenerics, true, oClass, isFieldDescending, fieldName));
                    }
                }
            }
            indent(sb, 2);
            sb.append("}\n");
            return sb.toString();
        }
        
        int scrollTo() {
            return 500;
        }
        
        public String toString() {
            return "Comparable";
        }
    }, 
    COMPARATOR_TOP_LEVEL {
        String compose(final boolean useGenerics, final String gClass, final String oClass, final String comment, final SortKey... sortKeys) {
            final String today = BigDate.localToday().toString();
            final FastCat sb = new FastCat(sortKeys.length + 120);
            sb.append("// <> Generated Comparator Java code to save as ");
            sb.append(gClass);
            sb.append(".java.\n");
            sb.append("// <> Insert the generated code, discarding the <> locating comments.\n");
            sb.append("/*\n");
            sb.append(" * @(#)");
            sb.append(gClass);
            sb.append(".java\n");
            sb.append(" *\n");
            sb.append(" * Summary: ");
            sb.append(comment);
            sb.append(".\n");
            sb.append(" *\n");
            sb.append(" * Requires: JDK 1.5+\n");
            sb.append(" *\n");
            sb.append(" * Created with: Canadian Mind Products ComparatorCutter.\n");
            sb.append(" *\n");
            sb.append(" * Version History:\n");
            sb.append(" *  1.0 ");
            sb.append(today);
            sb.append(" - initial release\n");
            sb.append(" */\n");
            sb.append("// <> package ...\n");
            sb.append("import java.util.Comparator;\n");
            if (miscImportNeeded(useGenerics, sortKeys)) {
                sb.append("import static java.lang.System.err; import static java.lang.System.*; import com.mindprod.common11.Misc;\n");
            }
            sb.append("\n");
            sb.append("/**\n");
            sb.append(" * ");
            sb.append(comment);
            sb.append(".\n");
            sb.append(" * <p/>\n");
            sb.append(" * Defines an alternate sort order for ");
            sb.append(oClass);
            sb.append(".\n");
            sb.append(" *\n");
            sb.append(" * @author ...\n");
            sb.append(" * @version 1.0 ");
            sb.append(today);
            sb.append(" - initial release\n");
            sb.append(" * @since ");
            sb.append(today);
            sb.append("\n");
            sb.append(" */\n");
            sb.append("class ");
            sb.append(gClass);
            sb.append(" implements Comparator");
            if (useGenerics) {
                sb.append("<");
                sb.append(oClass);
                sb.append(">");
            }
            sb.append("\n");
            indent(sb, 1);
            sb.append("{\n");
            sb.append("");
            indent(sb, 1);
            sb.append("/**\n");
            indent(sb, 1);
            sb.append(" * ");
            sb.append(comment);
            sb.append(".\n");
            indent(sb, 1);
            sb.append(" * Defines an alternate sort order for ");
            sb.append(oClass);
            sb.append(" ");
            sb.append(useGenerics ? "with" : "without");
            sb.append(" JDK 1.5+ generics.\n");
            indent(sb, 1);
            sb.append(" * Compare two ");
            sb.append(oClass);
            sb.append(" Objects.\n");
            if (sortKeys.length != 0) {
                indent(sb, 1);
                sb.append(" * ");
                sb.append(sortKeyOrderInWords(sortKeys));
                sb.append("\n");
            }
            indent(sb, 1);
            sb.append(" * Informally, returns (a-b), or +ve if a sorts after b.\n");
            indent(sb, 1);
            sb.append(" *\n");
            indent(sb, 1);
            sb.append(" * @param a first ");
            sb.append(oClass);
            sb.append(" to compare\n");
            indent(sb, 1);
            sb.append(" * @param b second ");
            sb.append(oClass);
            sb.append(" to compare\n");
            indent(sb, 1);
            sb.append(" *\n");
            indent(sb, 1);
            sb.append(" * @return +ve if a&gt;b, 0 if a==b, -ve if a&lt;b\n");
            indent(sb, 1);
            sb.append(" */\n");
            indent(sb, 1);
            sb.append("public final int compare( ");
            if (useGenerics) {
                sb.append(oClass);
                sb.append(" a, ");
                sb.append(oClass);
                sb.append(" b");
            }
            else {
                sb.append("Object a, Object b");
            }
            sb.append(" )\n");
            indent(sb, 2);
            sb.append("{\n");
            if (sortKeys.length == 0) {
                indent(sb, 2);
                sb.append("// no sort keys defined yet.\n");
                indent(sb, 2);
                sb.append("return 0; // dummy code for now\n");
            }
            else {
                for (int i = 0; i < sortKeys.length; ++i) {
                    final SortKey sortKey = sortKeys[i];
                    final FieldType fieldType = sortKey.getFieldType();
                    final boolean isFieldDescending = sortKey.isFieldDescending();
                    final String fieldName = sortKey.getFieldName();
                    if (i < sortKeys.length - 1) {
                        sb.append(fieldType.generateIntermediateCompareToLine(useGenerics, false, oClass, isFieldDescending, fieldName, i));
                    }
                    else {
                        sb.append(fieldType.generateLastCompareToLine(useGenerics, false, oClass, isFieldDescending, fieldName));
                    }
                }
            }
            indent(sb, 2);
            sb.append("}\n");
            indent(sb, 1);
            sb.append("}\n");
            return sb.toString();
        }
        
        int scrollTo() {
            return 780;
        }
        
        public String toString() {
            return "Comparator (top level)";
        }
    }, 
    COMPARATOR_NESTED {
        String compose(final boolean useGenerics, final String gClass, final String oClass, final String comment, final SortKey... sortKeys) {
            final FastCat sb = new FastCat(sortKeys.length + 85);
            sb.append("// <> Generated Comparator Java code to insert in ");
            sb.append(oClass);
            sb.append(".java as a nested static class, discarding the comments above the package line.\n");
            sb.append("// <> Insert the generated code, discarding the <> locating comments.\n");
            if (miscImportNeeded(useGenerics, sortKeys)) {
                sb.append("import static java.lang.System.err; import static java.lang.System.*; import com.mindprod.common11.Misc;\n");
            }
            sb.append("// <> ...\n");
            sb.append("\n");
            sb.append("/**\n");
            sb.append(" * ");
            sb.append(comment);
            sb.append(".\n");
            sb.append(" * <p/>\n");
            sb.append(" * Defines an alternate sort order for ");
            sb.append(oClass);
            sb.append(".\n");
            sb.append(" */\n");
            sb.append("static class ");
            sb.append(gClass);
            sb.append(" implements Comparator");
            if (useGenerics) {
                sb.append("<");
                sb.append(oClass);
                sb.append(">");
            }
            sb.append("\n");
            indent(sb, 1);
            sb.append("{\n");
            sb.append("\n");
            indent(sb, 1);
            sb.append("/**\n");
            indent(sb, 1);
            sb.append(" * ");
            sb.append(comment);
            sb.append(".\n");
            indent(sb, 1);
            sb.append(" * Defines an alternate sort order for ");
            sb.append(oClass);
            sb.append(" ");
            sb.append(useGenerics ? "with" : "without");
            sb.append(" JDK 1.5+ generics.\n");
            indent(sb, 1);
            sb.append(" * Compare two ");
            sb.append(oClass);
            sb.append(" Objects.\n");
            if (sortKeys.length != 0) {
                indent(sb, 1);
                sb.append(" * ");
                sb.append(sortKeyOrderInWords(sortKeys));
                sb.append("\n");
            }
            indent(sb, 1);
            sb.append(" * Informally, returns (a-b), or +ve if a comes after b.\n");
            indent(sb, 1);
            sb.append(" *\n");
            indent(sb, 1);
            sb.append(" * @param a first ");
            sb.append(oClass);
            sb.append(" to compare\n");
            indent(sb, 1);
            sb.append(" * @param b second ");
            sb.append(oClass);
            sb.append(" to compare\n");
            indent(sb, 1);
            sb.append(" *\n");
            indent(sb, 1);
            sb.append(" * @return +ve if a&gt;b, 0 if a==b, -ve if a&lt;b\n");
            indent(sb, 1);
            sb.append(" */\n");
            indent(sb, 1);
            sb.append("public final int compare( ");
            if (useGenerics) {
                sb.append(oClass);
                sb.append(" a, ");
                sb.append(oClass);
                sb.append(" b");
            }
            else {
                sb.append("Object a, Object b");
            }
            sb.append(" )\n");
            indent(sb, 2);
            sb.append("{\n");
            if (sortKeys.length == 0) {
                indent(sb, 2);
                sb.append("// no sort keys defined yet.\n");
                indent(sb, 2);
                sb.append("return 0; // dummy code for now\n");
            }
            else {
                for (int i = 0; i < sortKeys.length; ++i) {
                    final SortKey sortKey = sortKeys[i];
                    final FieldType fieldType = sortKey.getFieldType();
                    final boolean isFieldDescending = sortKey.isFieldDescending();
                    final String fieldName = sortKey.getFieldName();
                    if (i < sortKeys.length - 1) {
                        sb.append(fieldType.generateIntermediateCompareToLine(useGenerics, false, oClass, isFieldDescending, fieldName, i));
                    }
                    else {
                        sb.append(fieldType.generateLastCompareToLine(useGenerics, false, oClass, isFieldDescending, fieldName));
                    }
                }
            }
            indent(sb, 2);
            sb.append("}\n");
            indent(sb, 1);
            sb.append("}\n");
            return sb.toString();
        }
        
        int scrollTo() {
            return 780;
        }
        
        public String toString() {
            return "Comparator (nested)";
        }
    };
    
    private static void indent(final FastCat sb, final int level) {
        sb.append(ST.spaces(level * 4));
    }
    
    private static boolean miscImportNeeded(final boolean useGenerics, final SortKey... sortKeys) {
        for (final SortKey s : sortKeys) {
            final String diffType = s.getFieldType().getDiffType();
            if (!diffType.equals("int") && (!diffType.equals("long") || useGenerics)) {
                return true;
            }
        }
        return false;
    }
    
    private static String sortKeyOrderInWords(final SortKey... sortKeys) {
        final FastCat sb = new FastCat(6 * sortKeys.length + 2);
        assert sortKeys.length != 0 : "empty sort keys list";
        sb.append("Compares ");
        for (int i = 0; i < sortKeys.length; ++i) {
            final SortKey sortKey = sortKeys[i];
            final boolean isFieldDescending = sortKey.isFieldDescending();
            String fieldName = sortKey.getFieldName();
            if (fieldName.length() == 0) {
                fieldName = "whole object";
            }
            final String sortType = sortKey.getFieldType().getSortType();
            if (isFieldDescending) {
                sb.append("descending ");
            }
            sb.append(fieldName);
            sb.append(" ");
            sb.append(sortType);
            if (i < sortKeys.length - 1) {
                sb.append(" then ");
            }
        }
        sb.append(".");
        return sb.toString();
    }
    
    abstract String compose(final boolean p0, final String p1, final String p2, final String p3, final SortKey... p4);
    
    abstract int scrollTo();
}
