package Game;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
    public static final Quests QUESTS = new Quests();

    private boolean ring = false;
    private boolean bracelet = false;

    private int goldAmount = 1000;
    private int score = 0;

    private int playerRowCoord = 6;
    private int playerColCoord = 6;

    /**
     * Constructor for player class.
     */
    public Player(String name, int level)
    {
        super(name, level);

        update();

    }

    public void addToInventory(String string, int amount)
    {
        INVENTORY.addToInventory(string,amount);
    }

    public void removeFromInventory(String string, int amount)
    {
        INVENTORY.removeFromInventory(string , amount);
    }

    public boolean checkInventory(String string, int amount)
    {
        return INVENTORY.checkInventory(string,amount);
    }

    public void printInventory()
    {
        INVENTORY.printInventory();
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
        return currentHealthPoints > 0;

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

    public Item getWeapon()
    {
        return SWORD;
    }

    public void setRing()
    {
        ring = true;
    }

    public void setBracelet()
    {
        bracelet = true;
    }

    public boolean braceletExists()
    {
        return bracelet;
    }

    public boolean ringExists()
    {
        return ring;
    }

    public Item getArmour()
    {
        return ARMOUR;
    }

    public Item getPotion()
    {
        return POTION;
    }

    public Item getAmulet()
    {
        return AMULET;
    }

    public Item getRing()
    {
        return RING;
    }

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

    public int getHitChance()
    {
        return doubleHitChance;
    }

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

    public void printQuestList()
    {
        QUESTS.printQuestList();
    }

    public void startQuest(String string)
    {
        QUESTS.startQuest(string);
    }

    public boolean checkQuestStatus(String string)
    {
        return QUESTS.checkQuestStatus(string);
    }

    public int getRewardAmount(String string)
    {
        return QUESTS.getRewardAmount(string);
    }

    public int getRequirementAmount(String string)
    {
        return QUESTS.getRequirementAmount(string);
    }

    public String getRequirement(String string)
    {
        return QUESTS.getRequirement(string);
    }
}
