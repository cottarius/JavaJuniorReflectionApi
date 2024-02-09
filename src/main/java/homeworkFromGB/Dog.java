package homeworkFromGB;

public class Dog extends Animal{
    private boolean trained;

    public Dog(String name, int age, boolean trained) {
        super(name, age);
        this.trained = trained;
    }

    @Override
     void makeSound() {
        System.out.println("Гав-Гав");
    }
}
