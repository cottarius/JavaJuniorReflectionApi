package homeworkFromGB;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Cat cat1 = new Cat("Барсик", 4, true);
        Cat cat2 = new Cat("Мурка", 3, false);

        Dog dog1 = new Dog("Шарик", 6, true);
        Dog dog2 = new Dog("Барбос", 2, false);

        List<Animal> animals = new ArrayList<>();
        animals.add(cat1);
        animals.add(cat2);
        animals.add(dog1);
        animals.add(dog2);

        for (Animal animal : animals) {
            getInfoWithReflection(animal);
        }
    }
    static void getInfoWithReflection(Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Field[] declaredFieldsFromParent = object.getClass().getSuperclass().getDeclaredFields();
        for (Field declaredField : declaredFieldsFromParent) {
            printValues(object, declaredField);
        }

        Field[] declaredFieldsFromClass = object.getClass().getDeclaredFields();
        for (Field declaredField : declaredFieldsFromClass) {
            printValues(object, declaredField);
        }

        Method makeSound = object.getClass().getDeclaredMethod("makeSound");
        makeSound.setAccessible(true);
        System.out.print(makeSound.getName() + ": ");
        makeSound.invoke(object);
        System.out.println();
    }

    private static void printValues(Object object, Field declaredField) throws IllegalAccessException {
        declaredField.setAccessible(true);
        Object value = declaredField.get(object);
        System.out.print(declaredField.getName() + " - " + value + "\n");
    }
}
