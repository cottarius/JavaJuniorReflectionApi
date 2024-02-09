package task2;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {

    public String buildUpdateQuery(Object obj) {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }

        StringBuilder query = new StringBuilder("UPDATE ");


        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" SET ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);

                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey())
                    continue;
                try {
                    query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("', ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if (query.charAt(query.length() - 2) == ',') {
            query.delete(query.length() - 2, query.length());
        }

        query.append(" WHERE ");

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    try {
                        query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("'");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        return query.toString();
    }

    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }
        StringBuilder query = new StringBuilder("DELETE FROM ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" WHERE ");


        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name())
                            .append(" = ")
                            .append("'")
                            .append(primaryKey)
                            .append("'");
                    break;
                }
            }
        }

        return query.toString();

    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }

        StringBuilder query = new StringBuilder("SELECT * FROM ");

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" WHERE ");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name())
                            .append(" = ")
                            .append("'")
                            .append(primaryKey)
                            .append("'");

                    break;
                }
            }
        }
        return query.toString();
    }


    public String buildInsertQuery(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }

        StringBuilder query = new StringBuilder("INSERT INTO ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query
                .append(tableAnnotation.name())
                .append(" (");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                query
                        .append(columnAnnotation.name())
                        .append(", ");
            }
        }

        if (query.charAt(query.length() - 2) == ',') {
            query.delete(query.length() - 2, query.length());
        }
        query.append(") VALUES (");

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                query.append("'").append(field.get(object)).append("', ");

            }
        }

        if (query.charAt(query.length() - 2) == ',') {
            query.delete(query.length() - 2, query.length());
        }
        query.append(")");

        return query.toString();

    }
}
