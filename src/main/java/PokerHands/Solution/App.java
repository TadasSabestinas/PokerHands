package PokerHands.Solution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App 
{
    public static void main( String[] args )
    {
       String textFilePath = "src\\main\\java\\PokerHands\\Solution\\poker.txt";
    
       try {
    	   Files.lines(Paths.get(textFilePath)).forEach(System.out::println);
       } catch (IOException e) {
    	   System.err.println("Error while reading file");
       }
    }
}
