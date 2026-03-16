package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        // ── Decorator Pattern: multiple runtime combinations ───────────

        AttackAction basic = new BasicAttack("Sword Strike", 12);

        AttackAction fireOnly = new FireRuneDecorator(basic);

        AttackAction poisonCrit = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(basic)
        );

        AttackAction ultimate = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)
                )
        );

        System.out.println("--- Decorator: Runtime Attack Composition ---");
        printAction("Base attack", basic);
        printAction("Fire Rune only", fireOnly);
        printAction("Poison + Critical", poisonCrit);
        printAction("All three stacked", ultimate);

        // ── Facade Pattern: one full dungeon run ───────────────────────

        HeroProfile hero = new HeroProfile("Aldric the Brave", 100);
        BossEnemy boss = new BossEnemy("Infernal Warden", 120, 14);

        System.out.println("--- Facade: Dungeon Adventure ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, ultimate);

        for (String line : result.getLog()) {
            System.out.println("  " + line);
        }

        // ── Final Summary ──────────────────────────────────────────────

        System.out.println("\n--- Final Summary ---");
        System.out.println("Winner : " + result.getWinner());
        System.out.println("Rounds : " + result.getRounds());
        System.out.println("Reward : " + result.getReward());
        System.out.println("Action : " + ultimate.getActionName());
        System.out.println("Effects: " + ultimate.getEffectSummary());

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printAction(String label, AttackAction action) {
        System.out.println(label + ":");
        System.out.println("  Name    = " + action.getActionName());
        System.out.println("  Damage  = " + action.getDamage());
        System.out.println("  Effects = " + action.getEffectSummary());
        System.out.println();
    }
}
