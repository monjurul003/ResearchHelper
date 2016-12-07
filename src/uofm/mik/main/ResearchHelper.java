package uofm.mik.main;
import uofm.mik.logic.CongestionWindow;

import java.text.DecimalFormat;
import java.io.File;


/**
 * Created by khanmm3 on 06/12/16.
 */
public class ResearchHelper {

    public static void main(String[] args) {
        CongestionWindow cwndObj = new CongestionWindow();
        String applicationPath = System.getProperty("user.dir");
        String[] inputString = cwndObj.readFileAsAStringArray(applicationPath+ File.separator+"input"+ File.separator, "TcpNewReno_sim9_sock1_cwnd.txt");

        double timeBase = 0.0;
        double timePrev = 0.0;
        int j = 0;
        for (int i = 0; i < inputString.length; i++) {
            String[] input = inputString[i].split("\\s+");
            double time = Double.parseDouble(input[0]);

            if (timePrev != time) {
                j++;
                timePrev = time;
            }
        }
        String[] outputString = new String[j];
        j = 0;
        DecimalFormat nf   = new DecimalFormat("#.###");

        for (int i = 0; i < inputString.length; i++) {
            String[] input = inputString[i].split("\\s+");
            double time = Double.parseDouble(input[0]);

            if (i == 0) {
                timeBase = time;
            }
            if (timePrev != time) {

                input[0] = String.valueOf(nf.format(time - timeBase));
                outputString[j++] = input[0] + " " + input[1];
                timePrev = time;
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(outputString[i]);
        }

        cwndObj.writeStringArrayInFile(applicationPath+ File.separator, "TcpNewReno_sim9_sock1_cwnd.txt", outputString);

    }
}
