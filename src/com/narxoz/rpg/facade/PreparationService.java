package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null || boss == null || action == null) {
            throw new IllegalArgumentException("Hero, boss, and action must not be null.");
        }

        return "[Preparation] " + hero.getName()
                + " (HP:" + hero.getHealth() + ")"
                + " prepares [" + action.getActionName() + "]"
                + " (DMG:" + action.getDamage() + ")"
                + " to fight " + boss.getName()
                + " (HP:" + boss.getHealth() + ", ATK:" + boss.getAttackPower() + ")";
    }
}
