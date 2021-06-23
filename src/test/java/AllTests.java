import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AllTests {
    MostActiveCookie mostActiveCookie;

    @Test
    public void test_getMostActiveCookie() {

        String[] resArray = new String[] {"a"};
        mostActiveCookie = new MostActiveCookie();
        mostActiveCookie.getDateToList().put("date", Arrays.asList(resArray));
        assertArrayEquals(resArray, mostActiveCookie.getMostActiveCookie("date").toArray());
    }

    @Test
    public void test_sortFreqMap() {
        mostActiveCookie = new MostActiveCookie();
        HashMap<String,Integer> fMap = new HashMap<String, Integer>();
        fMap.put("a", 2);
        fMap.put("b", 3);
        List<String> res = mostActiveCookie.sortFreqMap(fMap);
        assertEquals(1, res.size());
        assertEquals("b", res.get(0));
    }

    @Test
    public void test_splitAndProcess() {
        mostActiveCookie = new MostActiveCookie();
        mostActiveCookie.splitAndProcess("a,2018-12-09T14:19:00+00:00");
        mostActiveCookie.splitAndProcess("a,2018-12-09T14:19:00+00:00");
        mostActiveCookie.splitAndProcess("b,2018-12-09T14:19:00+00:00");
        mostActiveCookie.splitAndProcess("end,end");
        assertEquals(1, mostActiveCookie.getDateToList().get("2018-12-09").size());


    }
}
