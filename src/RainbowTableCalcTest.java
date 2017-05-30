import static org.junit.Assert.*;

/**
 * Created by dimitri on 20.05.2017.
 */
public class RainbowTableCalcTest {
    RainbowTableCalc rainBowTable;
    @org.junit.Before
    public void setUp() throws Exception {
        rainBowTable = new RainbowTableCalc();

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testGfetReducedMD5() throws Exception {
        String res = rainBowTable.getReduction(rainBowTable.getMD5("0000000"), 0);
        assertEquals(res, "87inwgn");

        String res2 = rainBowTable.getReduction(rainBowTable.getMD5(res), 1);
        assertEquals(res2, "frrkiis");

        String res3 = rainBowTable.getReduction(rainBowTable.getMD5(res2), 2);
        assertEquals(res3, "dues6fg");
    }

    @org.junit.Test
    public void testSearchForHashValue() throws Exception {
        String password = rainBowTable.findPassword("29c3eea3f305d6b823f562ac4be35217", 1999);
        assertEquals(password, "0000000");

        String password1 = rainBowTable.findPassword("12e2feb5a0feccf82a8d4172a3bd51c3", 1999);
        assertEquals(password1, "87inwgn");

        String password2 = rainBowTable.findPassword("437988e45a53c01e54d21e5dc4ae658a", 1999);
        assertEquals(password2, "frrkiis");

        String password3 = rainBowTable.findPassword("c0e9a2f2ae2b9300b6f7ef3e63807e84", 1999);
        assertEquals(password3, "dues6fg");
    }



}