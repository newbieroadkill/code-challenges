package teleporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import teleporter.manager.TeleporterManager;

import java.util.Arrays;

@SpringBootApplication
public class TeleporterRunner implements CommandLineRunner{

    @Autowired
    TeleporterManager teleporterManager;

    public static void main(String[] args) {
        SpringApplication.run(TeleporterRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
           //Parse input file line by line

               //Add to the teleporter

               //Create appropriate output




    }
}