package dev.unnm3d.rediseconomy.hook;

import dev.unnm3d.rediseconomy.RedisEconomyPlugin;
import dev.unnm3d.rediseconomy.hook.impl.PlaceholderAPIHook;
import dev.unnm3d.rediseconomy.hook.impl.shopgui.ShopGuiHook;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.PluginManager;

@RequiredArgsConstructor
public class HookManager {

    private final RedisEconomyPlugin plugin;

    @Getter private Hook shopHook;
    @Getter private Hook placeholderHook;

    public void enable() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        if (pluginManager.getPlugin("ShopGUIPlus") != null) {
            (shopHook = new ShopGuiHook(plugin)).enable();
        }

        if (pluginManager.getPlugin("PlaceholderAPI") != null) {
            (placeholderHook = new PlaceholderAPIHook(plugin)).enable();
        }
    }

    public void disable() {
        if (shopHook != null)
            shopHook.disable();

        if (placeholderHook != null)
            placeholderHook.disable();
    }

}
