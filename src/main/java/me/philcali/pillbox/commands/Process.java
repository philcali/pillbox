package me.philcali.pillbox.commands;

import me.philcali.pillbox.commands.process.Descendants;
import picocli.CommandLine;

@CommandLine.Command(
        name = "process",
        description = "Platform independent process management utility",
        subcommands = Descendants.class)
public class Process {
}
