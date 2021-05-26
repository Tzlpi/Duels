package me.tzipi.duels.manager;

import me.tzipi.duels.Main;
import me.tzipi.duels.commands.Join;
import me.tzipi.duels.listeners.BlockBreakListener;
import me.tzipi.duels.listeners.EntityDamageByEntity;
import me.tzipi.duels.listeners.EntityHit;
import me.tzipi.duels.listeners.QuitEvent;
import me.tzipi.duels.scoreboard.Health;
import me.tzipi.duels.scoreboard.HubScoreboard;
import me.tzipi.duels.scoreboard.RemoveScoreboard;
import me.tzipi.duels.tasks.CountDownStart;
import me.tzipi.duels.tasks.TpToLobby;
import me.tzipi.duels.tasks.TpToTeamLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class GameManager {
    public ArrayList<Player> inGame = new ArrayList<Player>();
    public ArrayList<Player> notInGame  = new ArrayList<Player>();
    private Main plugin;
    public GameState gameState = GameState.LOBBY;
    private BlockManager blockManager;
    private TpToTeamLocation tpToTeamLocation;
    private PlayerManager playerManager;
    private CountDownStart countDownStart;
    private TpToLobby tpToLobby;
    private QuitEvent quitEvent;
    private EntityHit entityHit;
    private RemoveScoreboard removeScoreboard;
    private Join join;
    private HubScoreboard hubScoreboard;
    private BlockBreakListener blockBreakListener;
    private EntityDamageByEntity entityDamageByEntity;
    private Health health;

    public GameManager (Main plugin) {
        this.plugin = plugin;
        this.health = new Health(this);
        this.blockManager = new BlockManager(this);
        this.playerManager = new PlayerManager(this);
        this.removeScoreboard = new RemoveScoreboard(this);
        this.tpToTeamLocation = new TpToTeamLocation(this,plugin);
        this.quitEvent = new QuitEvent(plugin.sqlGetter, this, plugin);
        this.tpToLobby = new TpToLobby(this, plugin);
        this.hubScoreboard = new HubScoreboard(plugin, plugin.sqlGetter);
        this.entityDamageByEntity = new EntityDamageByEntity(this);
        this.entityHit = new EntityHit(this);
    }

    public void setGameState(GameState gameState) {
    if(this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;

        this.gameState = gameState;
        switch(gameState) {
            case STARTING:
                this.countDownStart = new CountDownStart(this, plugin);
                this.countDownStart.runTaskTimer(plugin, 0,20);
                break;

            case ACTIVE:
                Bukkit.broadcastMessage(ChatColor.GREEN + "The game has started have fun!");
                this.getPlayerManager().giveKits();
                this.getTpToTeamLocation().TpToSpawn1();
                this.getRemoveScoreboard().removeBoard();
                this.getHealth().setHealthNameTag();

            case LOBBY:
                this.getTpToLobby().tpToLobby1();
                for(Player online : Bukkit.getOnlinePlayers()) {
                    hubScoreboard.createBoard(online);
                }
                break;
        }
    }
    public void cleanup() {
    }
    public BlockManager getBlockManager() {
        return blockManager;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public TpToTeamLocation getTpToTeamLocation() {
        return tpToTeamLocation;
    }
    public TpToLobby getTpToLobby() {
        return tpToLobby;
    }
    public RemoveScoreboard getRemoveScoreboard() {return removeScoreboard;}
    public Health getHealth() {return health;}
}

