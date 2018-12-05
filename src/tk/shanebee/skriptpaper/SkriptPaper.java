package tk.shanebee.skriptpaper;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SkriptPaper extends JavaPlugin {

    SkriptPaper instance;
    SkriptAddon addon;
    private String prefix = "[" + this.getDescription().getName() + "] ";

    @Override
    public void onEnable() {
        if ((Bukkit.getPluginManager().getPlugin("Skript") != null) && (Skript.isAcceptRegistrations())) {
            if (isRunningPaper()) {
                instance = this;
                addon = Skript.registerAddon(this);
                try {
                    addon.loadClasses("tk.shanebee.skriptpaper", "elements");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getLogger().info(ChatColor.GREEN + "Successfully enabled");
            } else {
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "****************************************");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*                                      *");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*" + ChatColor.GOLD + "    You must run Paper server for     " + ChatColor.RED + "*");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*" + ChatColor.GOLD + "        Skript-Paper to work          " + ChatColor.RED + "*");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*                                      *");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*" + ChatColor.GOLD + "    More info can be found here:      " + ChatColor.RED + "*");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*" + ChatColor.GOLD + "        https://papermc.io            " + ChatColor.RED + "*");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "*                                      *");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "****************************************");
                Bukkit.getPluginManager().disablePlugin(this);
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Disabled");
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Dependency Skript was not found");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private static boolean isRunningPaper() {
        try {
            Class.forName("co.aikar.timings.Timing");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

}
