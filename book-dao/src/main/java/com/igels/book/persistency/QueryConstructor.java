package com.igels.book.persistency;

import com.igels.book.common.TablesName;
import com.igels.book.entity.IFieldsName;
import com.igels.book.query.QueryType;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Query constructor class to SQL
 * SELECT, INSERT, UPDATE, DELETE requests.
 */
class QueryConstructor {

    private static final String select = " SELECT ";
    private static final String delete = " DELETE ";
    private static final String from = " FROM ";
    private static final String where = " WHERE ";
    private static final String insert = " INSERT ";
    private static final String into = " INTO ";
    private static final String values = " VALUES ";
    private static final String update = " UPDATE ";
    private static final String set = " SET ";

    private static final String all = " * ";
    private static final String key = " id ";
    private static final String assign = " = ";
    private static final String left = " ( ";
    private static final String right = " ) ";
    private static final String apostrophe = "'";
    private static final String dapostrophe = "\"";
    private static final String backspace = " ";
    private static final String space = " , ";

    /**
     * Construct SELECT and DELETE SQL requests.
     *
     * @param queryType  QueryType (SELECT and DELETE)
     * @param tablesName TablesName
     * @param id         Integer from database
     * @return String SQL request
     */
    static String construct(QueryType queryType, TablesName tablesName,
                            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Integer> id) {
        StringBuilder result = new StringBuilder();

        switch (queryType) {
            case SELECT: {
                if (id.isPresent()) {
                    result.append(select + all + from).
                            append(tablesName.getValue()).
                            append(where).
                            append(key).
                            append(assign).
                            append(id.get());
                } else {
                    result.append(select + all + from).
                            append(tablesName.getValue());
                }
                break;
            }
            case DELETE: {
                result.append(delete + from).
                        append(tablesName.getValue()).
                        append(where).append(key).
                        append(assign).
                        append(id.get());
                break;
            }
        }
        return result.toString();
    }

    /**
     * Construct INSERT SQL request
     *
     * @param queryType  QueryType (INSERT)
     * @param tablesName TablesName
     * @param fields     String fields, example: (a, b, c ,d)
     * @param value      String values (1, 2, 3, 4)
     * @return String SQL request
     */
    static String construct(QueryType queryType, TablesName tablesName, String fields, String value) {
        StringBuilder result = new StringBuilder();

        switch (queryType) {
            case INSERT: {
                result.append(insert).
                        append(into).
                        append(tablesName.getValue()).
                        append(fields).
                        append(values).
                        append(value);
                break;
            }
        }
        return result.toString();
    }

    /**
     * Construct UPDATE SQL request
     *
     * @param queryType    QueryType (UPDATE)
     * @param tablesName   TablesName
     * @param fieldsValues String, example: a=1, b=2, c=3
     * @param id           Integer from database
     * @return String SQL request
     */
    static String construct(QueryType queryType, TablesName tablesName, String fieldsValues,
                            @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<Integer> id) {
        StringBuilder result = new StringBuilder();

        switch (queryType) {
            case UPDATE: {
                result.append(update).
                        append(tablesName.getValue()).
                        append(set).
                        append(fieldsValues).
                        append(where).
                        append(key).
                        append(assign).
                        append(id.get());
            }
        }
        return result.toString();
    }

    /**
     * Construct field string from fields list.
     *
     * @param args IFieldsName fields list
     * @return String part SQL request
     */
    static String constructFields(IFieldsName... args) {
        StringBuilder result = new StringBuilder();
        result.append(left);

        for (IFieldsName field : args) {
            result.append(dapostrophe).
                    append(field.getValue()).
                    append(dapostrophe).
                    append((field == args[args.length - 1]) ? backspace : space);
        }
        result.append(right);

        return result.toString();
    }

    /**
     * Construct values string from objects list.
     *
     * @param args Object list
     * @return String part SQL request
     */
    static String constructValues(Object... args) {
        StringBuilder result = new StringBuilder();
        result.append(left);

        for (Object value : args) {
            result.append(apostrophe).
                    append(value).
                    append(apostrophe).
                    append((value == args[args.length - 1]) ? backspace : space);
        }
        result.append(right);

        return result.toString();
    }

    /**
     * Construct names/values string from pair <IFieldsName, object> list.
     *
     * @param args Pair<IFieldsName, Object>
     * @return String part SQL request
     */
    @SafeVarargs
    static String constructFieldsValues(Pair<IFieldsName, Object>... args) {
        StringBuilder result = new StringBuilder();

        for (Pair<IFieldsName, Object> pair : args) {
            result.append(dapostrophe).
                    append(pair.getKey().getValue()).
                    append(dapostrophe).
                    append(backspace).
                    append(assign).
                    append(apostrophe).
                    append(pair.getValue()).
                    append(apostrophe).
                    append((pair == args[args.length - 1]) ? backspace : space);
        }

        return result.toString();
    }
}
