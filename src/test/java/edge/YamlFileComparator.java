package edge;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlFileComparator {

    public static void main(String[] args) {
        String file1Path = "org1.yml";
        String file2Path = "org2.yml";

        // Load YAML files
        Map<String, Object> file1Data = loadYaml(file1Path);
        Map<String, Object> file2Data = loadYaml(file2Path);

        if (file1Data != null && file2Data != null) {
            // Update file2Data based on file1Data
            updateYaml(file1Data, file2Data);

            // Save updated file2Data back to file2.yml
            saveYaml(file2Path, file2Data);

            System.out.println("Updated file2.yml based on file1.yml.");
        } else {
            System.out.println("Failed to load one or both YAML files.");
        }
    }

    private static Map<String, Object> loadYaml(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void updateYaml(Map<String, Object> source, Map<String, Object> target) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                if (!target.containsKey(key)) {
                    target.put(key, new LinkedHashMap<>());
                }
                updateYaml((Map<String, Object>) value, (Map<String, Object>) target.get(key));
            } else {
                target.put(key, value);
            }
        }
    }

    private static void saveYaml(String filePath, Map<String, Object> data) {
        DumperOptions options = new DumperOptions();
        options.setIndent(2); // Set YAML indentation
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // Preserve block style
        options.setPrettyFlow(true);

        Representer representer = new Representer();
        representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml(representer, options);
        try (Writer writer = new FileWriter(filePath)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
