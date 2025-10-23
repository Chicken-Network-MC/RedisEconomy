package dev.unnm3d.rediseconomy.hook.impl.shopgui;

import dev.unnm3d.rediseconomy.currency.Currency;
import lombok.RequiredArgsConstructor;
import net.brcdev.shopgui.provider.economy.EconomyProvider;
import org.bukkit.entity.Player;

import java.util.Locale;

@RequiredArgsConstructor
public class ShopGuiEcoProvider extends EconomyProvider {

    private final Currency currency;

    @Override
    public String getName() {
        return currency.getCurrencyName().toUpperCase(Locale.ENGLISH);
    }

    @Override
    public double getBalance(Player player) {
        return currency.getBalance(player);
    }

    @Override
    public void deposit(Player player, double v) {
        currency.depositPlayer(player, v);
    }

    @Override
    public void withdraw(Player player, double v) {
        currency.withdrawPlayer(player, v);
    }
}
