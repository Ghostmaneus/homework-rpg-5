package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

/**
 * Battle rules (design decisions):
 * - Hero always attacks first each round.
 * - Hero damage = action.getDamage() + random bonus [0..4].
 * - Boss damage = boss.getAttackPower() + random bonus [0..3].
 * - Maximum 20 rounds; whoever reaches 0 HP first loses.
 * - If round limit is reached, the side with more HP wins.
 * - Determinism is controlled via setRandomSeed().
 */
public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        result.addLine("[Battle] " + hero.getName() + " vs " + boss.getName() + " — FIGHT!");

        int round = 0;
        final int maxRounds = 20;

        while (hero.isAlive() && boss.isAlive() && round < maxRounds) {
            round++;

            // Hero's turn
            int heroDmg = action.getDamage() + random.nextInt(5);
            boss.takeDamage(heroDmg);
            result.addLine("  Round " + round + ": "
                    + hero.getName() + " uses [" + action.getActionName() + "] → "
                    + heroDmg + " dmg → " + boss.getName() + " HP=" + boss.getHealth());

            if (!boss.isAlive()) break;

            // Boss's turn
            int bossDmg = boss.getAttackPower() + random.nextInt(4);
            hero.takeDamage(bossDmg);
            result.addLine("  Round " + round + ": "
                    + boss.getName() + " strikes back → "
                    + bossDmg + " dmg → " + hero.getName() + " HP=" + hero.getHealth());
        }

        result.setRounds(round);

        if (!boss.isAlive()) {
            result.setWinner(hero.getName());
            result.addLine("[Battle] " + hero.getName() + " wins in " + round + " rounds!");
        } else if (!hero.isAlive()) {
            result.setWinner(boss.getName());
            result.addLine("[Battle] " + boss.getName() + " wins in " + round + " rounds!");
        } else if (hero.getHealth() >= boss.getHealth()) {
            result.setWinner(hero.getName());
            result.addLine("[Battle] Round limit reached. " + hero.getName() + " wins on HP.");
        } else {
            result.setWinner(boss.getName());
            result.addLine("[Battle] Round limit reached. " + boss.getName() + " wins on HP.");
        }

        return result;
    }
}
