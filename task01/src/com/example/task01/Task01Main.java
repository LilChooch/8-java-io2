package com.example.task01;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
        System.out.println(extractSoundName(new File("C:\\Users\\Олег\\8-java-io2\\task01\\src\\main\\resources\\3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String ffprobe = "C:\\Users\\Олег\\Downloads\\ffmpeg-master-latest-win64-gpl\\ffmpeg-master-latest-win64-gpl\\bin\\ffprobe.exe";

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(ffprobe, "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath())
                .redirectErrorStream(true);

        Process process = processBuilder.start();
        String line;
        String title;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            line = reader.readLine();
            while(line != null) {
                if (line.contains("title")) {
                    title = line.split("=")[1].replace("\"", "");
                    return title;
                }
                line = reader.readLine();
            }
        }

        return null;
    }
}
