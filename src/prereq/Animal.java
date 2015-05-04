package prereq;

import java.util.Arrays;
import org.reflections.*;

import java.util.List;
import java.util.LinkedList;

import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import prereq.Dog;
import prereq.Cat;

class Animal {

    private String sound;
    final static String[] ALLOWED_CLASSES = { Dog.class.getSimpleName(), Cat.class.getSimpleName() };

    Animal(String input) {
        this.is_dog_or_cat_class();
        sound = input;
    }

    String getSound() {
        return sound;
    }

    private void is_dog_or_cat_class() {
        if (!Arrays.asList(ALLOWED_CLASSES).contains(this.getClass().getSimpleName())) {
            throw new RuntimeException("Please use getAllowedAnimals()");
        }
    }

    void speak() {
        System.out.println("The " + this.getClass().getSimpleName() + " says " + this.getSound() + ".");
    }

    // with reflections
    static void getAllowedAnimals2() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /*
                                                        * don't exclude
                                                        * Object.class
                                                        */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("prereq"))));

        System.out.println("Allowed Animals using reflections:");
        try {
            for (Class<? extends Animal> classes : reflections.getSubTypesOf(Animal.class)) {
               //the following if would not be needed if class is created in a different package.
                if (Arrays.asList(ALLOWED_CLASSES).contains(classes.getSimpleName())) {
                    System.out.println(classes.getSimpleName());
                }
            }
        } catch (RuntimeException e) {
            System.out.println("Please use method getAllowedAnimals()");
        }

    }

    static String getAllowedAnimals() {
        return "Allowed animals: " + Arrays.toString(ALLOWED_CLASSES);

    }

}
