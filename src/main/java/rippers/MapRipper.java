package rippers;

import java.util.Map;
import java.util.Set;

/**
 * Класс-"потрошитель" для Map объектов
 */
public class MapRipper {

    /**
     * Метод принимает на вход:
     * @param object - объект, в котором необходимо пройти по полям.
     * @param fieldsToCleanup - Set имен полей, которые необходимо обнулить.
     * @param fieldsToOutput - Set имен полей, которые необходимо вывести в консоль.
     * Метод проверяет поля объекта и в случаи совпадения имени с именем поля из Set'ов обнуляет
     * или выводит значение поля в консоль. Если какого-нибудь поля из Set'ов нет в объекте, будет
     * выброшено исключение, а объект останется без изменения.
     */

    public void cleanup(Map object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        //Проверяем Map на пустоту
        if (object.size() == 0) {
            System.out.println("Объект типа Map - пустой");
            return;
        }

        // получаем Set с названием ключей
        Set keysSet = object.keySet();

        ////Проверяем, что все поля из списков есть в объекте.
        fieldsCheck(keysSet, fieldsToCleanup);
        fieldsCheck(keysSet, fieldsToOutput);

        //После проверки обнуляем поля
        fieldsCleanup(object, keysSet, fieldsToCleanup);
        //и выводим в консоль
        fieldsShow(object, keysSet, fieldsToOutput);


    }

    /**
     * Метод проверяет поля из списка Set на наличие в Map.
     * Если какое-нибудь поле из Set отсутсвует в объекте, то будет выброшено исключение.
     * @param keys - Set из ключей объекта Map
     * @param fieldsToCheck - Set из полей, которые необходимо проверить
     */

    private void fieldsCheck(Set keys, Set<String> fieldsToCheck) {

        boolean isPresent = false;

        for (String string: fieldsToCheck) {

            for (Object key: keys) {

                if (key.toString().equals(string)) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                throw new IllegalArgumentException("Данного ключа нет в Map: " + string);
            }

            isPresent = false;

        }

    }

    /**
     * Метод для обнуления значений в Map
     * @param object - объект-Map, в котором необходимо обнулить значения
     * @param keys - Set ключей данного объекта
     * @param fieldsToCleanup - Set полей, которые необходимо обнулить
     */
    private void fieldsCleanup(Map object, Set keys, Set<String> fieldsToCleanup) {

        for (String string: fieldsToCleanup) {

            for (Object key : keys) {

                if (key.toString().equals(string)) {

                    object.put(key, null);
                    break;

                }
            }
        }

    }

    /**
     * Метод для полей в консоль
     * @param object - Map-объект, у которого необходимо вывести поля
     * @param keys - Set с ключами данного объекта
     * @param fieldsToOutPut - Set полей, которые необходимо вывести
     */
    private void fieldsShow(Map object, Set keys, Set<String> fieldsToOutPut) {

        for (String string: fieldsToOutPut) {

            for (Object key : keys) {

                if (key.toString().equals(string)) {

                    String name = null;

                    if (object.get(key) != null) {
                        name = object.get(key).toString();
                    }

                    System.out.println("Ключ: " + key.toString() + ", значение: " + name);
                    break;

                }
            }
        }

    }

}
