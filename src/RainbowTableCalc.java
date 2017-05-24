import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimitri on 16.05.2017.
 */
public class RainbowTableCalc {

    public static int L = 7;

    private char[] Z = new char [] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    // Hashmap with Key (Password) and Value (placeholder for Endpunkt)
    public static Map<String, String>  PASSWORDS = new HashMap<>();

    public static String finalHashValue = "1d56a37fb6b08aa709fe90e12ca59e12";


    public RainbowTableCalc(){
        // Fills the PASSWORD Hash Map with all possible Passwords
        printMessage("Started generating all Possible Passwords");
        generatePossibleStrings(7, Z, "");
        printMessage("Passwords are generated");

        printMessage("Started generating EndPoint Values of all Passwords");
        generateEndPointValForAllPasswords();
        printMessage("All End Point Values are Generated");




    }

    /**
     * TODO 
     * @param hash
     * @param stuffe
     * @return
     */
    public String searchForHashValue( String hash, int stuffe ) {
        // while ( stuffe < 200 )
        // chck hash with las reduction if its in passwords as value
            // if (yes) -> get Start Value and search for h(start) -> r(start,1) etc.. bis h(x) == hash
            // then, get x as password
            // else check stuffe -1

        return null;
    }

    /**
     * Generating 2000 Passwords
     */
    public void generatePossibleStrings(int maxLength, char[] alphabet, String curr) {
        if(curr.length() == maxLength) {
            PASSWORDS.put(curr.toString(), null);
        } else {
            if( PASSWORDS.size() < 2000 ) {
                for(int i = 0; i < alphabet.length; i++) {
                    String oldCurr = curr;
                    curr += alphabet[i];
                    generatePossibleStrings(maxLength,alphabet,curr);
                    curr = oldCurr;
                }
            }

        }
    }

    /**
     * Generates for all Passwords the Endpoint Value
     * and saves it to the PASSWORD HashMap
     */
    public void generateEndPointValForAllPasswords(){

        for (Map.Entry<String, String> entry : PASSWORDS.entrySet()){
            String endPoint = getEndPointVal(entry.getKey());
            entry.setValue(endPoint);
        }
    }

    /**
     * Geenrates a MD5 Hash of a given String
     * and calcs Reducefunction of it
     * @param input String Value to be hashed
     * @return String of Endpoint Value after 2000 Iterations
     */
    public String getEndPointVal(String input) {
        for(int i = 0; i < 2000; i++ ){
            input = getReducedMD5(input, i);
        }
        return input;
    }

    public String getReducedMD5(String input, int stuffe){
        BigInteger md5 = null;
        String res = "";

        if(null == input) return null;

        try {
            //Create & Update MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest());

            // Get
            res = getReduction(md5, stuffe);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return res;
    }

    /**
     * Get Reduction String of Big Integer
     * @param hash Big Integer MD5 Hash
     * @return String reduced hash as String
     */
    public String getReduction(BigInteger hash, int stuffe){
        String res = "";
        hash = hash.add(BigInteger.valueOf(stuffe));

        for ( int i = 0; i < L; i++ ){
            int test = hash.mod(new BigInteger("36")).intValue();
            res = Z[test] + res;
            hash = hash.divide(new BigInteger("36"));
        }

        return res;
    }

    public void printMessage(String msg){
        System.out.println("----------------------------------------------------------");
        System.out.println("     " + msg);

    }


}
