package cool.compiler;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
    public static int intNumbers = 0;
    public static int stringNumbers = 0;
    public static Map<Integer, String> intValues = new LinkedHashMap<>();
    public static Map<String, String> stringValues = new LinkedHashMap<>();
    public static Map<Integer, String> boolValues = new LinkedHashMap<>();
    public static Map<String, Integer> classesAndIndexes = new LinkedHashMap<>();
    private static String intPrefix = "int_const";
    private static String stringPrefix = "str_const";
    private static String boolPrefix = "bool_const";

    public static void addInteger(int n) {
        if (!intValues.containsKey(n)) {
            intValues.put(n, intPrefix + intNumbers);
            ++intNumbers;
        }
    }

    public static void addString(String n) {
        if (!stringValues.containsKey(n)) {
            stringValues.put(n, stringPrefix + stringNumbers);
            ++stringNumbers;
        }
    }

    public static void addBool() {
        boolValues.put(0, "bool_const0");
        boolValues.put(1, "bool_const1");
    }

}
