package com.lamantinov.checkinfo.htmlKeepers;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class YandexHTMLKeeper {
    public static String getHTMLCode() {
        BufferedReader br = null;
        String everything;
        try {
            br = new BufferedReader(new FileReader("/home/grigorii/checkInfo/src/main/resources/htmlfile.txt"));
            StringBuilder sb = new StringBuilder();
            String htmlCode = br.readLine();
            while (htmlCode != null) {
                sb.append(htmlCode);
                sb.append(System.lineSeparator());
                htmlCode = br.readLine();
            }
            everything = sb.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return everything;
    }
}
