
import additional.Person;
import logic.ProgramLogic;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        Person person = new Person("Vasya",42,166.6,"Male",false,72);

        Set<String> toSet = new HashSet<>();

        toSet.add("name");
        toSet.add("height");
        toSet.add("sex");
        toSet.add("age");

        Set<String> toShow = new HashSet<>();

        toShow.add("sex");
        toShow.add("isMarried");
        toShow.add("age");
        toShow.add("height");

        System.out.println(person.toString());
        ProgramLogic programLogic = new ProgramLogic();

        programLogic.start(person, toSet, toShow);

        System.out.println(person.toString());

    }

}
