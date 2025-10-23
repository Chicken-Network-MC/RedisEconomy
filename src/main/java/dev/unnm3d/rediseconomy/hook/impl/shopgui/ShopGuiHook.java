package dev.unnm3d.rediseconomy.hook.impl.shopgui;

import dev.unnm3d.rediseconomy.RedisEconomyPlugin;
import dev.unnm3d.rediseconomy.currency.Currency;
import dev.unnm3d.rediseconomy.hook.Hook;
import lombok.RequiredArgsConstructor;
import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.event.ShopGUIPlusPostEnableEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class ShopGuiHook implements Hook {

    private final RedisEconomyPlugin plugin;

    @Override
    public void enable() {
        plugin.getLogger().info("Hooking into ShopGUIPlus");
        plugin.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onEnable(ShopGUIPlusPostEnableEvent event) {
                plugin.getLogger().info("ShopGUIPlus enabled, registering RedisEconomy and its currencies as economy provider...");

                for (Currency currency : plugin.getCurrenciesManager().getCurrencies()) {
                    // Skip Vault currency to avoid conflicts with Vault economy provider
                    if (currency.getCurrencyName().equalsIgnoreCase("vault")) continue;

                    ShopGuiPlusApi.registerEconomyProvider(new ShopGuiEcoProvider(currency));
                }

                plugin.getLogger().info("RedisEconomy and all of its currencies are now registered as economy provider for ShopGUIPlus.");
            }
        }, plugin);
    }

    @Override
    public void disable() {
        // Nothing to do
    }

}
