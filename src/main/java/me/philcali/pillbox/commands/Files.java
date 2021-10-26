package me.philcali.pillbox.commands;

import me.philcali.pillbox.commands.files.Cat;
import me.philcali.pillbox.commands.files.Exists;
import me.philcali.pillbox.commands.files.Find;
import me.philcali.pillbox.commands.files.Remove;
import picocli.CommandLine;

@CommandLine.Command(name="files",
        description = "Platform independent file system interaction.",
        subcommands = { Cat.class, Find.class, Exists.class, Remove.class})
public class Files {
}
