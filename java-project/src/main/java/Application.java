import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import conversion.JsonToYaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        String content = new Scanner(new File("C:\\facultate\\an3\\sem1\\AI\\java-project\\src\\main\\resources\\history-ontology.json")).useDelimiter("\\Z").next();
//        System.out.println(content);
        System.out.println(new JsonToYaml().asYaml(content));
    }
}
