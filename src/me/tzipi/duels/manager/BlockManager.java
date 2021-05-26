package me.tzipi.duels.manager;

import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockJukeBox;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashSet;
import java.util.Set;

public class BlockManager {

    private GameManager gameManager;
    public BlockManager(GameManager gameManager) {
        this.gameManager = gameManager;


    }
    private Set<Material> allowedToBreak = new HashSet<>();
    public boolean canBreak(Block block) {
        return allowedToBreak.contains(block.getBlockData());
    }

}