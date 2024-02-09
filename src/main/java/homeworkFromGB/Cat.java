package homeworkFromGB;

public class Cat extends Animal{

    private boolean catchingMice;
    public Cat(String name, int age, boolean catchingMice) {
        super(name, age);
        this.catchingMice = catchingMice;
    }

    @Override
    void makeSound() {
        System.out.println("Мяу-Мяу");
    }
}
