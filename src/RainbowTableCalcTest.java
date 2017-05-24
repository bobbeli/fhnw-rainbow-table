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
    public void testGetReducedMD5() throws Exception {
        String res = rainBowTable.getReducedMD5("0000000", 0);
        assertEquals(res, "87inwgn");

        String res2 = rainBowTable.getReducedMD5(res, 1);
        assertEquals(res2, "frrkiis");

        String res3 = rainBowTable.getReducedMD5(res2, 2);
        assertEquals(res3, "dues6fg");
    }

    @org.junit.Test
    public void testSearchForHashValue() throws Exception {
        String res = rainBowTable.searchForHashValue("29c3eea3f305d6b823f562ac4be35217");
        assertEquals(res, "87inwgn");

    }


}