package a1.demo;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {

    public static List<List<String>> parseCSV(String filePath, String splitter){
        List<List<String>> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(new File(filePath))) {
            rowScanner.useDelimiter("\n");
            while (rowScanner.hasNext()) {
                ArrayList<String> data = new ArrayList<>();
                for (String s : rowScanner.next().split(splitter))
                    if(!s.trim().isEmpty())
                        data.add(s.trim());
                if(!data.isEmpty())
                values.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return values;
    }

    private static String findLogin(List<String> postingsData, List<List<String>> loginsData){
        for(List<String> data : loginsData)
            if(postingsData.get(9).equalsIgnoreCase(data.get(1)) && data.get(2).replaceAll(" ", "").equals("True"))
                return "True";
        return "False";
    }

    public static boolean addIsAuthorizedColumn(List<List<String>> postingsCSV, List<List<String>> loginsCSV){
        if(postingsCSV.get(0).size()>10)
            return true;
        postingsCSV.get(0).add("IsAuthorized");
        for(int i = 1; i<postingsCSV.size();i++){
           postingsCSV.get(i).add(findLogin(postingsCSV.get(i),loginsCSV));
        }
        return true;
    }

    public static boolean saveCSV(List<List<String>> csvList, String splitter){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("postings.csv", false));
            for(List<String> data: csvList){
                for(int i = 0; i< data.size();i++){
                    bw.append(data.get(i));
                    if(i<data.size()-1)
                        bw.append(splitter);
                }
                bw.append("\n");
            }
          bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static List<Login> parseLogin(List<List<String>> loginList){
        List<Login> result = new ArrayList<>();
        for(int i = 1; i<loginList.size(); i++)
            result.add(new Login(loginList.get(i)));
        return result;
    }

    public static List<Posting> parsePostings(List<List<String>> postingsList) throws ParseException {
        List<Posting> result = new ArrayList<>();
        for(int i = 1; i<postingsList.size();i++)
            result.add(new Posting(postingsList.get(i)));
        return result;
    }
}
