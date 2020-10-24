package utils;

public class StringUtils {
    public static boolean isEmpty(String string){
        return string==null||"".equals(string)||string.trim().equals("");
    }
    public static boolean isNotEmpty(String string){
        return !isEmpty(string);
    }
    public static Integer[] convertStringArrayToIntArray(String[] arr) {
        if(arr == null || arr.length == 0) return null;
        Integer[] intArr = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
    }
}
