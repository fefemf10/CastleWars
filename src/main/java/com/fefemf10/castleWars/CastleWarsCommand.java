package com.fefemf10.castleWars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CastleWarsCommand implements CommandExecutor, TabExecutor {

    private final Map<String, SubCommand> subCommands = new HashMap<>() {{
        put("create", new CreateCommand());
        put("delete", new DeleteCommand());
        put("setkit", new SetKitCommand());
        put("spawnpoint", new SpawnPointCommand());
        put("flagpoint", new FlagPointCommand());
        put("start", new StartCommand());
        put("stop", new StopCommand());
        put("connect", new ConnectCommand());
        put("leave", new LeaveCommand());
    }};

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            if (strings.length == 0) throw new IllegalArgumentException();

            String subCommand = strings[0].toLowerCase();

            if (!commandSender.hasPermission(String.format("castlewars.command.%s", subCommand))) {
                commandSender.sendMessage("You doesn't nave permission");
                return true;
            }

            if (subCommands.containsKey(subCommand))
                return subCommands.get(subCommand).process(commandSender, command, s, strings);
        } catch (Exception e) {
            commandSender.sendMessage("ERROR");
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return switch (strings.length) {
            case 1 -> {
                List<String> subCommandsFiltered = subCommands.keySet().stream().filter(x -> {
                    return commandSender.hasPermission(String.format("castlewars.command.%s", x));
                }).collect(Collectors.toList());

                yield subCommandsFiltered;
            }
            default -> Collections.emptyList();
        };
    }
}
