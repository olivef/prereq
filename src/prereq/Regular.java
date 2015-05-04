package prereq;

public class Regular {

    public static void main(String[] args)  {

        System.out.println(Animal.getAllowedAnimals());
        Animal.getAllowedAnimals2();


        Cat cat = new Cat();
        cat.speak();
        cat.miao();

        Dog dog = new Dog();
        dog.speak();
        dog.bark();

    }

}
