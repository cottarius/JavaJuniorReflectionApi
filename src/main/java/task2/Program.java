package task2;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws IllegalAccessException {

        Employee user = new Employee("Oleg", "sample@gmail.com");
        QueryBuilder queryBuilder = new QueryBuilder();

        //Генерация SQL-запроса для вставки
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.println(insertQuery);

        UUID pk = UUID.randomUUID();
        String selectQuery = queryBuilder.buildSelectQuery(Employee.class, pk);
        System.out.println(selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.println(updateQuery);

        String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, pk);
        System.out.println(deleteQuery);
    }
}
