package me.philcali.pillbox.commands.files;

import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name="find",
        description = "List all files and directories.")
public class Find implements Callable<Integer> {
    @CommandLine.Option(names = "-maxdepth", description = "Max recursion depth")
    private int maxdepth = -1;

    @CommandLine.Option(names = "-name", description = "Filter files by glob name, ie: *.txt")
    private String name = "*";

    @CommandLine.Parameters(index = "0")
    private String file;

    private void printFilePath(Path filePath, int currentDepth) throws IOException {
        PathMatcher matcher = filePath.getFileSystem().getPathMatcher("glob:" + name);
        if (matcher.matches(filePath.getFileName())) {
            System.out.println(filePath);
        }
        if (Files.isDirectory(filePath) && (maxdepth == -1 || currentDepth < maxdepth)) {
            try (DirectoryStream<Path> files = Files.newDirectoryStream(filePath)) {
                for (Path nextFile : files) {
                    printFilePath(nextFile, currentDepth + 1);
                }
            }
        }
    }

    @Override
    public Integer call() throws Exception {
        Path filePath = Paths.get(file);
        int exitCode = Exists.call(filePath);
        if (exitCode > 0) {
            return exitCode;
        }
        printFilePath(filePath, 0);
        return 0;
    }
}
