import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * This Class Represents a Instance
 * of a RainbowTable
 */
public class RainbowTableCalc {

    public static int L = 7;

    private char[] Z = new char [] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    // Hashmap with Key (Password) and Value (placeholder for Endpunkt)
    public static Map<String, String>  PASSWORDS = new HashMap<>();

    public static String finalHashValue = "1d56a37fb6b08aa709fe90e12ca59e12";

    /**
     * Constructor
     * Generates The RainbowTable
     */
    public RainbowTableCalc(){
        printMessage("Started generating all Possible Passwords");
        generatePossibleStrings(7, Z, "");
        printMessage("Passwords are generated");

        printMessage("Started generating EndPoint Values of all Passwords ...");
        generateEndPointValForAllPasswords();
        printMessage("All End Point Values are Generated");
        printMessage("Rainbow Table Ready");
    }

    /**
     * Tries to find a clear Text
     * Value of the given HashValue
     */
    public void findPasswordForGivenHash(){
        String password = findPassword( finalHashValue, 1999 );
        printMessage("Password: "+password+"  to Hash: "+finalHashValue);
    }

    /**
     * Searches the Password for a given Hash and the Max r (stuffe)
     * @param hashString hash Value as Hex String
     * @param stuffe Max Reduction Stuffe of RainbowTable
     * @return String the Password of the given hashString or a Not Found Message
     */
    public String findPassword( String hashString, int stuffe ) {

        BigInteger temp = null;
        BigInteger hash = new BigInteger(hashString, 16);
        String r, startVal;
        boolean haveFoundHash = false;
        boolean haveFoundEndVal = false;
        r = getReduction(hash, stuffe);

        while ( stuffe >= 0 ) {
            for (Map.Entry<String, String> entry : PASSWORDS.entrySet()){
                if( entry.getValue().equals(r) ) {

                    haveFoundEndVal = true;
                    printMessage("Found EedPoint Value");
                    startVal = entry.getKey();

                    int i = 0;
                    while ( ! haveFoundHash  && i < 1999) {
                        BigInteger tempHash = getMD5(startVal);

                        // If Hash is Equals, the startVal is our Password
                        if(hash.equals(tempHash)){

                            return startVal;
                        }
                        startVal = getReduction(tempHash, i);
                        i++;
                    }
                }
            }
            stuffe--;

            temp = hash;
            for (int i = stuffe; i < 2000; i++){
                r = getReduction(temp, i);
                temp = getMD5(r);
            }

        }

        return "no password found";
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

            String endPoint = getEndPointVal(entry.getKey(), 2000);

            entry.setValue(endPoint);
        }
    }

    /**
     * Generates the EndPoint Value of the Rainbow Table
     * of a given input String
     * @param input String Start Value of the RainbowTable
     * @return String of Endpoint Value after 2000 Iterations
     */
    public String getEndPointVal(String input, int length) {
        for(int i = 0; i < length; i++ ){
            input = getReduction(getMD5(input), i);
        }
        return input;
    }

    /**
     * Creats a MD5 Hash Value of a given String
     * @param input
     * @return BigInteger of the hashed String
     */
    public BigInteger getMD5(String input){
        BigInteger md5 = null;

        if(null == input) return null;

        try {
            //Create & Update MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest());

            // Get

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
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

    /**
     * Print Method
     * @param msg
     */
    public void printMessage(String msg){
        System.out.println("----------------------------------------------------------");
        System.out.println("     " + msg);

    }

}
