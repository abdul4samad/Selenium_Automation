package run;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CreateDataFile {
    public static void main(String[] args) {
        String dataFilePath = "DataFile.txt";
        String imageNamesFilePath = "imageNames.txt";
        String filteredDataFilePath = "filteredDataFinal.txt";

        try {
            List<String> imageNames = Files.readAllLines(Paths.get(imageNamesFilePath));
            BufferedReader reader = new BufferedReader(new FileReader(dataFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filteredDataFilePath));

            String line;
            while ((line = reader.readLine()) != null) {
                String imageName = line.split("-->")[0];
                if (imageNames.contains(imageName)) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
