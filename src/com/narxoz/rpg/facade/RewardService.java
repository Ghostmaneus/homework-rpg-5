package com.narxoz.rpg.facade;

public class RewardService {
    /**
     * Reward rules:
     * - Quick victory (≤ 3 rounds)  → Legendary chest
     * - Normal victory (≤ 6 rounds) → Rare reward
     * - Long victory   (> 6 rounds) → Common reward
     */
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }

        int rounds = battleResult.getRounds();

        if (rounds <= 3) {
            return "Legendary Chest: 500 gold + enchanted weapon";
        } else if (rounds <= 6) {
            return "Rare Reward: 250 gold + upgrade shard";
        } else {
            return "Common Reward: 100 gold + healing potion";
        }
    }
}
