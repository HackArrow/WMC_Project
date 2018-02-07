package com.example.panagiotisspentzouris.batterystats;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class EditFiles {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String readFile() {
        Path file = Paths.get("downloads/batteryStats");
        try (InputStream in = Files.newInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            String drain [] = null;
            while ((line = reader.readLine()) != null) {
                drain = line.split(",");
                return drain[1].trim().substring(13);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void writeFile() {
        // Convert the string to a
        // byte array.
        String s = readFile();
        byte data[] = s.getBytes();
        Path p = Paths.get("downloads/my_batterystats.txt");
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
