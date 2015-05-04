package prereq;

public class Cat extends Animal {
    public Cat() {
        super("miao");
    }

    public void miao() {
        System.out.println(super.getSound());
    }
}
