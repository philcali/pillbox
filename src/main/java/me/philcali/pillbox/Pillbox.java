package me.philcali.pillbox;

import me.philcali.pillbox.commands.Files;
import me.philcali.pillbox.commands.Process;
import picocli.CommandLine;

@CommandLine.Command(
        name = "pillbox",
        version = "1.0.0",
        description = "A platform independent utility for interacting with the OS.",
        subcommands = { Files.class, Process.class })
public class Pillbox {
    @CommandLine.Option(
            names = {"-h", "--help"},
            description = "Displays help usage information",
            scope = CommandLine.ScopeType.INHERIT,
            usageHelp = true)
    private boolean help;

    public static void main(String[] args) {
        System.exit(new CommandLine(new Pillbox()).execute(args));
    }
}
