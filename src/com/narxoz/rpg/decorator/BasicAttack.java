package com.narxoz.rpg.decorator;

public class BasicAttack implements AttackAction {
    private final String actionName;
    private final int baseDamage;

    public BasicAttack(String actionName, int baseDamage) {
        this.actionName = actionName;
        this.baseDamage = baseDamage;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public int getDamage() {
        return baseDamage;
    }

    @Override
    public String getEffectSummary() {
        return "No special effects";
    }
}
