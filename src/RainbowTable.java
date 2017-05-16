import java.util.List;

/**
 * Created by dimitri on 16.05.2017.
 */
public class RainbowTable {

    public static int counter = 0;

    public static void main(String[] args) {
        String[] myStringArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"};

        String[] res = getAllLists(myStringArray);
        System.out.print("");


    }

    private static String[] getAllLists(String[] elements) {
        String [] res =  new String [2000];

        for(int i = 0 ; i < elements.length; i++){
            res[i] = ("0000000" + elements[i]).substring(elements[i].length());
        }


        return res;
    }


}