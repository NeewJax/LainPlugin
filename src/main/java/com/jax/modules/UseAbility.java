package com.jax.modules;

import eu.darkbot.api.extensions.Behavior;
import eu.darkbot.api.extensions.Feature;
import eu.darkbot.api.game.entities.Player;
import eu.darkbot.api.game.items.ItemFlag;
import eu.darkbot.api.game.items.SelectableItem;
import eu.darkbot.api.managers.EntitiesAPI;
import eu.darkbot.api.managers.HeroAPI;
import eu.darkbot.api.managers.HeroItemsAPI;

import java.util.Collection;
import java.util.Comparator;

@Feature(name = "UseAbility", description = "Use Ability")
public class UseAbility implements Behavior {
    protected final HeroAPI hero;
    protected final HeroItemsAPI items;

    protected final Collection<? extends Player> players;

    public UseAbility(HeroAPI hero, EntitiesAPI entities, HeroItemsAPI items){
        this.hero = hero;
        this.items = items;
        this.players = entities.getPlayers();
    }

    @Override
    public void onTickBehavior() {
        Player enemy = getMostClosedEnemy();
        if (enemy != null && enemy.distanceTo(hero) < 900){
            this.items.useItem(SelectableItem.Spray.CELEBRATE, ItemFlag.USABLE);
        }
    }

    private Player getMostClosedEnemy() {
        return this.players.stream().filter(player -> player.getEntityInfo().isEnemy()).min(Comparator.comparingDouble(player -> player.distanceTo(hero))).orElse(null);
    }
}
