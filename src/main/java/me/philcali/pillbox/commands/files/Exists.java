package me.philcali.pillbox.commands.files;

import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "exists",
        description = "Returns successfully if the path exists.")
public class Exists implements Callable<Integer> {
    @CommandLine.Parameters(index = "0")
    private String file;

    static int call(Path filePath) {
        if (Files.notExists(filePath)) {
            System.err.println("File '" + filePath + "' does not exists.");
            return 1;
        }
        return 0;
    }

    @Override
    public Integer call() throws Exception {
        Path filePath = Paths.get(file);
        return call(filePath);
    }
}
