package bodog.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class  Utils {


    public static Object[][] getData() throws Exception {

        String csvFile = "src/resources/LoginBodogTest.csv";
        int numberOfLines = countLines(new File(csvFile));
        if (numberOfLines == -1) {
            throw new Exception("File not found exception");
        }
        BufferedReader br = null;
        String line = "", csvSplitBy = ",";
        String[] temp;
        Object[][] loginDetails = new Object[numberOfLines][];
        ArrayList<String> csvDetails = new ArrayList<String>();
        Map<String, String> passDetails = new HashMap<String, String>();

        try {

                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    csvDetails.add(line);
                }

                int i = 0;
                for (String s : csvDetails) {
                    passDetails.clear();
                    temp = s.split(csvSplitBy);
                    passDetails.put("username", temp[0]);
                    passDetails.put("password", temp[1]);

                    loginDetails = new Object[][] {{passDetails}};
                    return loginDetails;

                }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }


    public static int countLines(File aFile) throws IOException {
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new FileReader(aFile));
            while ((reader.readLine()) != null);
            return reader.getLineNumber();
        } catch (Exception ex) {
            return -1;
        } finally {
            if(reader != null)
                reader.close();
        }
    }






}
