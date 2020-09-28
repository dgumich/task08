package additional;

/**
 * Класс, который будем использовать для теста
 */



public class Person {

    private String name;

    private int age;

    private Double height;

    private String sex;

    private boolean isMarried;

    private Integer weight;

    public Person(String name, int age, Double height, String sex, boolean isMarried, Integer weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.sex = sex;
        this.isMarried = isMarried;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "additional.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", sex='" + sex + '\'' +
                ", isMarried=" + isMarried +
                ", weight=" + weight +
                '}';
    }
}
