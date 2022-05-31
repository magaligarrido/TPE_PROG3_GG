import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "./datasets/dataset4.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
                for (int i = 0; i < items.length; i++) {
                	System.out.println(items[i]);					
				}   
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
