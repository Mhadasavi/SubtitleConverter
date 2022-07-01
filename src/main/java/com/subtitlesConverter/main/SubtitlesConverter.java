package com.subtitlesConverter.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtitlesConverter {

    public static void main(String... args) {
        System.out.println("Hello world");
        //readSubtitlesFile();
        String string = " I should be coddled in mother's lap everyday";
        System.out.println(convertToHindi(string));

    }

    public static void readSubtitlesFile() {
        try {
            File file = new File("C:/Users/upkarchaudhary/Downloads/RRR/RRR.srt");
            FileReader fr = null;   //reads the file

            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream

            StringBuffer sb = new StringBuffer();    //constructs a string buffer with no characters
            String line;

            while ((line = br.readLine()) != null) {
                line = regExJob(line);
                sb.append(line);      //appends line to string buffer
                sb.append("\n");     //line feed
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/upkarchaudhary/Downloads/RRR/demo.txt"));
            writer.write(String.valueOf(sb));
            fr.close();    //closes the stream and release the resources

            System.out.println("Contents of File: ");
            System.out.println(sb.toString());   //returns a string that textually represents the object
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }

    public static String regExJob(String string) {
        String result = null;
        if (string != null && !string.matches("[0-9].*")) {
            result = string;
            return result;
        } else return result;
    }

    public static String convertToHindi(String string) {
        String prefix = "https://translate.google.com/?hl=en&sl=en&tl=hi&text=";
        String suffix = "&op=translate";
        String url = prefix + string + suffix;
        try {
            // URL searchUrl = new URL(url);
            Document doc = Jsoup.connect(url).get();
            String masthead = doc.select("span[class=Q4iAWc]").text();
            //Elements rows = doc.select("div.J0lOec");

            System.out.println(masthead);
            ScriptEngine ee = new ScriptEngineManager().getEngineByName("Nashorn");
            ee.eval(new FileReader("/hello.js"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
        return "test";
    }

}
