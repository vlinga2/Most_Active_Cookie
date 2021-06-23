import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class MostActiveCookie {
    private HashMap<String, List<String>> dateToList = new HashMap();
    private String pre = "";
    private HashMap<String,Integer> freqMap;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[2].substring(args[2].indexOf("/")+1)));
        MostActiveCookie mostActiveCookie = new MostActiveCookie();
        while(sc.hasNext()) {
            String pass = sc.nextLine();
            mostActiveCookie.splitAndProcess(pass);
            if(!sc.hasNext()) mostActiveCookie.splitAndProcess("end,end");
        }

        String date = args[4];
        List<String> res = mostActiveCookie.getMostActiveCookie(date);
        for(String cookie:res) {
            System.out.println(cookie);
        }

    }


    public List<String> getMostActiveCookie(String date) {
        return dateToList.get(date);
    }

    public void splitAndProcess(String line) {
        String[] spl = line.split(",");

        String cookie = spl[0];
        String date = spl[1].split("T")[0];

        if(dateToList.containsKey(date)) {
        } else {
            dateToList.put(date, null);
            if(pre.equals("")) {
                pre = date;
            } else {
                List<String> sortedList = sortFreqMap(freqMap);
                dateToList.put(pre, sortedList);
                pre = date;
            }
            freqMap = new HashMap<String, Integer>();
        }

        freqMap.put(cookie, freqMap.getOrDefault(cookie, 0)+1);

    }

    public List<String> sortFreqMap(HashMap<String,Integer> freqMap) {
        ArrayList<pair> list  = new ArrayList<pair>();
        for(String key:freqMap.keySet()) {
            list.add(new pair(key,freqMap.get(key)));
        }
        Collections.sort(list,(p1,p2)-> p2.frequency-p1.frequency);
        int maxFreq = list.get(0).frequency;
        List<String> res = new ArrayList<String>();
        for(pair p:list) {
            if(p.frequency != maxFreq) break;
            res.add(p.cookie);
        }
        return res;
    }

    public HashMap<String,List<String>> getDateToList(){
        return dateToList;
    }

    class pair{
        String cookie;
        int frequency;
        pair(String cookie,int frequency){
            this.cookie = cookie;
            this.frequency = frequency;
        }
    }
}
