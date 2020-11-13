package it.flowzz.reclaim;

import it.flowzz.reclaim.commands.ReclaimCommand;
import it.flowzz.reclaim.profiles.ReclaimProfile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReclaimPlugin extends JavaPlugin {


    @Getter
    private List<ReclaimProfile> profiles = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadProfiles();
        new ReclaimCommand(this);
    }

    @Override
    public void onDisable() {
        profiles.clear();
    }

    private void loadProfiles() {
        getConfig().getConfigurationSection("Reclaim-profiles").getKeys(false).forEach(key -> profiles.add(
                new ReclaimProfile(getConfig().getStringList("Reclaim-profiles." + key + ".commands"),
                        getConfig().getString("Reclaim-profiles." + key + ".permission"),
                        getConfig().getInt("Reclaim-profiles." + key + ".weight")
                )));

        Collections.sort(profiles);
    }
}
