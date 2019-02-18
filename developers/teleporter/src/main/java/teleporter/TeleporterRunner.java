package teleporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import teleporter.manager.TeleporterManager;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class TeleporterRunner implements CommandLineRunner{

    @Autowired
    TeleporterManager teleporterManager;

    public static void main(String[] args) {
        SpringApplication.run(TeleporterRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(new File("input/example.txt"));
        PrintStream output = new PrintStream(new File("output/example.txt"));

        Pattern jumpPattern = Pattern.compile("cities from (.*?) in (\\d+) jumps");
        Pattern reachablePattern = Pattern.compile("can I teleport from (.*?) to (.*)");
        Pattern loopPattern = Pattern.compile("loop possible from (.*)");

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();

            if(line.contains("-")){
                String[] cities = line.split("-");
                teleporterManager.addRoute(cities[0].trim(), cities[1].trim());
            } else if (jumpPattern.matcher(line.trim()).matches()){
                Matcher matcher = jumpPattern.matcher(line.trim());
                if(matcher.find()){
                    String city = matcher.group(1);
                    int numOfJumps = Integer.parseInt(matcher.group(2));
                    output.println(line.trim()+": "+teleporterManager.citiesInRange(city, numOfJumps).toString().replaceAll("[\\[\\]]",""));
                }
            } else if (reachablePattern.matcher(line.trim()).matches()){
                Matcher matcher = reachablePattern.matcher(line.trim());
                if(matcher.find()){
                    String startingCity = matcher.group(1);
                    String destinationCity = matcher.group(2);
                    output.println(line.trim() + ": " + (teleporterManager.cityReachable(startingCity, destinationCity) ? "yes" : "no"));
                }
            } else if (loopPattern.matcher(line.trim()).matches()){
                Matcher matcher = loopPattern.matcher(line.trim());
                if(matcher.find()){
                    String city = matcher.group(1);
                    output.println(line.trim() + ": " + (teleporterManager.loopPoosible(city) ? "yes" : "no"));

                }
            }
        }


               //Add to the teleporter

               //Create appropriate output




    }
}