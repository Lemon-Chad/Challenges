package lemon.algoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lemon.algoc.drop.DropAlgo;

public class Main {

    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("dropIn.txt"));
        DropAlgo dropAlgo = new DropAlgo(text);
        System.out.println(dropAlgo.path());
    }
}
