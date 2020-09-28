package rippers;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * Класс-"потрошитель" для не-Map объектов
 */
public class ObjectRipper {


    /**
     * Метод принимает на вход:
     * @param object - объект, в котором необходимо пройти по полям.
     * @param fieldsToCleanup - Set имен полей, которые необходимо обнулить.
     * @param fieldsToOutput - Set имен полей, которые необходимо вывести в консоль.
     * Метод проверяет поля объекта и в случаи совпадения имени с именем поля из Set'ов обнуляет
     * или выводит значение поля в консоль. Если какого-нибудь поля из Set'ов нет в объекте, будет
     * выброшено исключение, а объект останется без изменения.
     */
    public void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        //Получаем класс объекта и массив с его полями.
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        //Проверяем, что все поля из списков есть в объекте.
        fieldsCheck(fields, fieldsToCleanup);
        fieldsCheck(fields, fieldsToOutput);

        //После проверки обнуляем поля
        try {
            fieldsCleanup(object, fields, fieldsToCleanup);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //и выводим в консоль
        try {
            fieldsShow(object, fields ,fieldsToOutput);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * Метод проверяет поля из списка Set на наличие в объекте.
     * Если какое-нибудь поле из Set отсутсвует в объекте, то будет выброшено исключение.
     * @param objectFields - массив полей, которые есть в объекте
     * @param fieldsToCheck - поля, наличие которых необходимо проверить.
     */
    private void fieldsCheck(Field[] objectFields, Set<String> fieldsToCheck) {

        boolean isPresent = false;

        for (String string: fieldsToCheck) {

            for (Field field: objectFields) {

                field.setAccessible(true);
                if (field.getName().equals(string)) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                throw new IllegalArgumentException("Данного поля нет в объекте: " + string);
            }

            isPresent = false;

        }

    }

    /**
     * Метод для обнуления полей в объекте.
     * @param object - объект, в котором необходимо обнулить поля
     * @param objectFields - массив его полей
     * @param fieldsToCleanup - Set полей, которые необходимо обнулить
     * @throws IllegalAccessException
     */
    private void fieldsCleanup(Object object, Field[] objectFields, Set<String> fieldsToCleanup) throws IllegalAccessException {

        //проходим по Set'у и при совпадении имен полей обнуляем поле.
        for (String string: fieldsToCleanup) {

            for (Field field : objectFields) {

                field.setAccessible(true);
                if (field.getName().equals(string)) {

                    String type = field.getType().getName();

                    //производим обнуление в зависимости от типа поля
                    switch (type) {
                        case("int"):
                            field.setInt(object,0);
                            break;
                        case("long"):
                            field.setLong(object,0L);
                            break;
                        case("byte"):
                            field.setByte(object, (byte) 0);
                            break;
                        case("short"):
                            field.setShort(object, (short) 0);
                            break;
                        case("double"):
                            field.setDouble(object,.0d);
                            break;
                        case("float"):
                            field.setFloat(object, .0f);
                            break;
                        case("char"):
                            field.setChar(object,'\u0000');
                            break;
                        case("boolean"):
                            field.setBoolean(object, false);
                        default:
                            field.set(object, null);
                    }

                }
            }
        }
    }

    /**
     * Метод для вывода полей в консоль.
     * @param object - объект, поля которого необходимо вывести
     * @param objectFields - массив его полей
     * @param fieldsToCleanup - название полей, которые нужно вывести в консоль.
     * @throws IllegalAccessException
     */
    private void fieldsShow(Object object, Field[] objectFields, Set<String> fieldsToCleanup) throws IllegalAccessException {

        //проходим по Set'у и при совпадении имен полей выводим поле в консоль.
        for (String string: fieldsToCleanup) {

            for (Field field : objectFields) {

                field.setAccessible(true);
                if (field.getName().equals(string)) {

                    String type = field.getType().getSimpleName();
                    String result;

                    //если поле примитивного типа используем функцию String.valueOf (так указано в задании)
                    if (type.equals("int") || type.equals("byte") || type.equals("char") || type.equals("short")
                        || type.equals("long") || type.equals("double") || type.equals("float") || type.equals("boolean")) {
                        result = "Поле: " + field.getName() + ", тип: " + type +  ", значение: " + String.valueOf(field.get(object));
                    } else {
                        //если поле ссылочного типа, то проводим проверку на null
                        String name = null;
                        if (field.get(object) != null) {
                           name = field.get(object).toString();
                        }
                        result = "Поле: " + field.getName() + ", тип: " + type + ", значение: " + name;
                    }
                    System.out.println(result);

                }

            }
        }

    }

}
