package Security;

import java.util.Arrays;

/**
 * @author 杨梓鹏
 */
public class CaesarCipher {
    public static void main(String[] args) {
        String Source = "asdfz";
        int key = 3;
        char[] arr = Source.toCharArray();
        for (int i = 0; i < arr.length; i++){
            int end = ((arr[i] + key) / 26);
            arr[i] = (char) end;
        }
        System.out.println(Arrays.toString(arr));
    }


}
