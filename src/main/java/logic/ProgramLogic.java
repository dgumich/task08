package logic;

import rippers.MapRipper;
import rippers.ObjectRipper;

import java.util.Map;
import java.util.Set;

/**
 * Класс с логикой программы.
 */

public class ProgramLogic {


    /**
     * Метод принимает объект с которым будет работать программа и два Set полей,
     * которые нужно обнулить и вывести на экран
     * @param object - объект, с котором будем работать
     * @param fieldsToCleanup - поля, которые необходимо обнулить
     * @param fieldsToOutput - поля, котоые необходимо вывести в консоль
     */
    public void start(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        //проверяем объект на имплементацию интерфейса Map и запускаем "потрошителя" в зависимости от типа
        if (object instanceof Map) {
            MapRipper mapRipper = new MapRipper();
            mapRipper.cleanup((Map) object, fieldsToCleanup, fieldsToOutput);
        } else {
            ObjectRipper objectRipper = new ObjectRipper();
            objectRipper.cleanup(object, fieldsToCleanup, fieldsToOutput);
        }

    }


}
