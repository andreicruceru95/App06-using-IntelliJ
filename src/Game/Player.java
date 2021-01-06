package Game;
import java.util.*;

/**
 * The game user.
 *
 * @authors Andrei Cruceru
 * @version 1.0.12
 */
public class Player extends Actor
{
    private static final VisualField VISUAL_FIELD = new VisualField();
    public static final Item SWORD = new Weapon("Steel Sword", 20000, 1, 12);
    public static final Item ARMOUR = new Armour("Steel Armour", 10000, 1, 8);
    public static final Item POTION = new Potion("HP Potion", 20000, 1, 200);
    public static final Item AMULET = new Amulet("Hellen's Gift", 50000, 1, 1000);
    public static final Item RING = new Ring("Potus's Ring", 10, 1, 10);
    public static final Item BRACELET = new Bracelet("Spirit Trinket", 10,1,10);
    public static final Inventory INVENTORY = new Inventory();
    public static final Display DISPLAY = new Display();
    public static final String[] QUESTS = new String[6];
    
    private boolean ring = false;
    private boolean bracelet = false;
    private boolean stone = true;
    private boolean oldLadyQuest = false;
    private boolean jewelerQuest = false;
    private boolean biologistQuest = false;
    private boolean findStone = true;
    private boolean farmerQuest = false;
    private boolean queenIsDeath = false;
    private boolean foxIsDeath = false;
    
    private int goldAmount = 1000000;
    private int score = 0;
    private int monsterKilled = 0;
    private int playerRowCoord = 23;
    private int playerColCoord = 17;
    
    /**
     * Constructor for player class.
     */
    public Player(String name, int level)
    {
        super(name, level);
        
        update();
        
    }
        
    /**
     * 
     */
    public void checkCharacter(String character)
    {
        if (character.equals(Characters.SPIDER_QUEEN.getCharacter()))
            queenIsDeath = true;
            
        else if(character.equals(Characters.NINE_TAILS.getCharacter()))
            foxIsDeath = true;
            
    }
    
    /**
     * @return the monster status
     */
    public boolean getMonsterStatus(String monsterName)
    {
        if(monsterName.equals("fox"))
            return foxIsDeath;
            
        else if(monsterName.equals("queen"))
            return queenIsDeath;
        else
            return false;
    }
    
    /**
     * Add an amount of objects to inventory.
     */
    public void addToInventory(String string, int amount)
    {
        INVENTORY.addToInventory(string,amount);
    }
    
    /**
     * Remove items from inventory.
     */
    public void removeFromInventory(String string, int amount)
    {
        INVENTORY.removeFromInventory(string , amount);
    }
    
    /**
     * Check inventory for an item.
     */
    public boolean checkInventory(String string, int amount)
    {
        return INVENTORY.checkInventory(string,amount);
    }
    
    /**
     * Print the inventory items.
     */
    public void printInventory()
    {
        INVENTORY.printInventory();    
    }
    
    /**
     * increase monster killed amount.
     */
    public void increaseKilled()
    {
        monsterKilled++;
    }
    
    /**
     * decrease monster killed amount.
     */
    public void decreaseKilled(int amount)
    {
        monsterKilled -= amount;
    }
    
    /**
     * @return the amount of kills.
     */
    public int getKilled()
    {
        return monsterKilled;
    }
    
    /**
     * Receive gold.
     */
    public void addGold(int gold)
    {
        if(gold > 0)
        {
            System.err.println("\t\t\tReceived " + gold + "Gold");
        }
        
        this.goldAmount += gold;
        score ++;
    }
    
    /**
     * return the gold amount
     */
    public int getGold()
    {
        return goldAmount;
    }
    
    /**
     * Pay for enchantment.
     */
    public void pay(int cost)
    {
        goldAmount -= cost;
        
        update();
    }
    
    /**
     * Increase attack force.
     */
    public void increaseAttackForce(int value)
    {
        this.attackForce += value;
        
    }
    
    /**
     * set player's health to 100%
     */
    public void setFullHealth()
    {
        currentHealthPoints = maxHealthPoints;
        
    }
    
    /**
     * @return the hit points
     */
    public boolean getHealthPoints()
    {
        if (currentHealthPoints > 0)
            return true;
        else
            return false;           
        
    }
    
    /**
     * get reward for flowers.
     * @return the amount of flowers times 10.
     */
    public int getReward()
    {
        return INVENTORY.countFlowers();
    }
    
    /**
     * Increase shield.
     */
    public void increaseShield(int value)
    {
        this.shield += value;
        
    }
    
    /**
     * Increase maximum hit points.
     */
    public void increaseHealthPoints(int value)
    {
        this.maxHealthPoints += value;
        
    }
    
    /**
     * increase potion amount
     */
    public void increasePotionAmount (int amount)
    {
        ((Potion) POTION).increaseAmount(amount);
        
    }
    
    public int getPotionAmount()
    {
        return ((Potion) POTION).getAmount();
        
    }
    
    /**
     * add score to the player
     */
    public void addScore(int score)
    {
        this.score += score;
        
    }
    
    /**
     * @return score.
     */
    public int getScore()
    {
        return score;
        
    }
    
    /**
     * update the visual field.
     */
    public void updateVisualField(String[][] map)
    {
        VISUAL_FIELD.setVisualField(map, playerRowCoord, playerColCoord);
        
    }
    
    /**
     * Update the player's stats.
     */
    public void update()
    {
        attackForce = initialAttackForce + SWORD.getStats();
        shield = initialShield + ARMOUR.getStats();
        maxHealthPoints = initialMaxHealthPoints + AMULET.getStats();
        currentHealthPoints += AMULET.getStats();
        if(ring)
            doubleHitChance = initialDoubleHitChance + RING.getStats();
            
        if(bracelet)
            evasionChance = initialEvasionChance + BRACELET.getStats();
            
        check();
    }
    
    /**
     * Keep the current health points lower or equal then maximum.
     */
    public void check()
    {
        if(maxHealthPoints < currentHealthPoints)
        {
            currentHealthPoints = maxHealthPoints;
        }
        
    }
    
    /**
     * @return the weapon.
     */
    public Item getWeapon()
    {
        return SWORD;
    }
    
    /**
     * set ring.
     */
    public void setRing()
    {
        ring = true;
    }
    
    /**
     * check if the stone exists.
     */
    public boolean stoneExists()
    {
        return stone;
    }
    
    /**
     * set stone.
     */
    public void setStone()
    {
        stone = true;
    }
    
    /**
     * set bracelet.
     */
    public void setBracelet()
    {
        bracelet = true;
    }
    
    /**
     * check if the bracelet exists.
     */
    public boolean braceletExists()
    {
        return bracelet;
    }
    
    /**
     * Check if the ring exists.
     */
    public boolean ringExists()
    {
        return ring;
    }
    
    /**
     * @return Armour.
     */
    public Item getArmour()
    {
        return ARMOUR;
    }
    
    /**
     * @return potion.
     */
    public Item getPotion()
    {
        return POTION;
    }
    
    /**
     * @return Amulet.
     */
    public Item getAmulet()
    {
        return AMULET;
    }
    
    /**
     * @return Ring.
     */
    public Item getRing()
    {
        return RING;            
    }
    
    /**
     * @return Bracelet.
     */
    public Item getBracelet()
    {
        return BRACELET;
    }
    
    /**
     * set player's coordinates
     */
    public void setCoordinates(int playerRowCoord, int playerColCoord)
    {
        this.playerRowCoord = playerRowCoord;
        this.playerColCoord = playerColCoord;
    }
    
    /**
     * @return player's row coordinates
     */
    public int getRowCoord()
    {
        return playerRowCoord;
    }
    
    /**
     * @return player's column coordinates
     */
    public int getColCoord()
    {
        return playerColCoord;
    }
    
    /**
     * @return the chance of double hit.
     */
    public int getHitChance()
    {
        return doubleHitChance;
    }
    
    /**
     * @return the chance of evasion.
     */
    public int getEvasionChance()
    {
        return evasionChance;
    }
    
    /**
     * Drink a health potion.
     */
    public void drinkPotion()
    {
        if(((Potion) POTION).getAmount() > 0)
        {
            currentHealthPoints += POTION.getStats();
            
            ((Potion) POTION).printMessage();
            System.out.println("Your heal regenerated " + POTION.getStats());
            System.out.println("\nYour health is now " + currentHealthPoints + " / " + maxHealthPoints); 
            
            ((Potion) POTION).decreaseAmount();
            
            check();
        }
        else
            System.err.println("Not enough potions");
        
    }
    
    /**
     * Check the visual field for an object
     */
    public boolean checkVisualField(String object)
    {
        return VISUAL_FIELD.checkVisualField(object);
        
    }
    
    /**
     * Change the player's image.
     */
    public void changeImage(String image)
    {
        VISUAL_FIELD.changeImage(image);
        
    }
    
    /**
     * @return current health.
     */
    public int getCurrentHealth()
    {
        return currentHealthPoints;
        
    }
    
    /**
     * @return full health.
     */
    public int getFullHealth()
    {
        return maxHealthPoints;
        
    }
    
    /**
     * set quests active
     */
    public void setQuestActive(String name)
    {
        if (name.equals("oldlady"))
            oldLadyQuest = true;
            
        else if(name.equals("farmer"))
            farmerQuest = true;
            
        else if(name.equals("jeweler"))
            jewelerQuest = true;   
            
        else if(name.equals("biologist"))
            biologistQuest = true;
                        
    }
    
    /**
     * set quests inactive
     */
    public void setQuestInactive(String name)
    {
        if (name.equals("oldlady"))
            oldLadyQuest = false;
            
        else if(name.equals("farmer"))
            farmerQuest = false;
            
        else if(name.equals("jeweler"))
            jewelerQuest = false;  
            
        else if(name.equals("biologist"))
            biologistQuest = false; 
            
        else if(name.equals("stone"))
            findStone = false;      
    }
    
    /**
     * print active quests.
     */
    public void printQuestList()
    {
        QUESTS[0] = "\n\t\tRetrieve the Mythical Stone - Kill the Reaper in the demon tower.";
        
        if(oldLadyQuest)
            QUESTS[1] = "\t\tFind husband remains - Kill monsters.";
        
        if(farmerQuest)
            QUESTS[2] = "\t\tKill 50 monsters.";
            
        if(biologistQuest)
            QUESTS[3] = "\t\tCollect Flowers - Kill monsters.";
         
        if(jewelerQuest)
            QUESTS[4] = "\t\tCollect Star fragments (" + Characters.STAR_FRAG.getCharacter() + ") - Kill demons.";
            
        DISPLAY.listOptions(QUESTS);    
    }
}