package prereq;

public class Dog extends Animal {

    public Dog() {
        super("voff");
    }

    public void bark() {
        String noise = super.getSound();
        System.out.println(noise + " " + noise);
    }
}
