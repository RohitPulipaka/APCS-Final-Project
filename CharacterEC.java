public class CharacterEC
{
    private String name;
    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int minDamage;
    private int maxDamage;

    //User
    public CharacterEC(String iName)
    {
        name = iName;
        health = 100;
        maxHealth = 250;
        mana = 100;
        maxMana = 100;
        minDamage = 12;
        maxDamage = 16;
    }

    //CPU
    public CharacterEC()
    {
        name = "CPU";
        health = 120;
        maxHealth = 120;
        maxMana = 100;
        mana = 100;
        minDamage = 15;
        maxDamage = 20;
    }
    
    public void takeDamage(int n)
    {
        health -= n;
    }

    public boolean isDead()
    {
        if (health <= 0)
        {
            System.out.println("UH-OH! LOOKS LIKE YOU'RE DEAD!");
            return true;
        }
        return false;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getHealth(CharacterEC enemy)
    {
        return health;
    }
    
    public int getMana()
    {
        return mana;
    }
    
    public String toString()
    {
        return ("Name: " + name + "\nMana: " + mana + "\nHealth: " + health);
    }
    
    /*USER SPELL METHODS*/
    //HEAL SPELLS
    public void healSpellU()
    {
        if (mana < 10)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else if ((health + 8) > maxHealth)
        {
            System.out.println("SORRY BUT YOU HAVE TO LOOSE MORE HEALTH TO USE THIS SPELL");
        }
        else
        {
            mana -= 10;
            health += 8;
        }
    }

    public void cupidsArrowU()
    {
        if (mana < 15)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else if ((health + 15) > maxHealth)
        {
            System.out.println("SORRY BUT YOU HAVE TO LOOSE MORE HEALTH TO USE THIS SPELL");
        }
        else
        {
            mana -= 15;
            health += 15;
        }
    }

    public void moonShineU()
    {
        if (mana < 40)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 40;            
            int remHealth = Math.abs(100 - health);
            health += remHealth;
        }
    }
    
    public void healBeltU()
    {
        if (mana < 55)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 55;
            health += (health*0.5);
        }
    }
    
    public void whiteMagikU(CharacterEC enemy)
    {
        if (mana < 75)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 75;
            int enemyHealth = enemy.getHealth();
            health += enemyHealth;
            enemyHealth -= (enemyHealth*0.5);
        }
    }
    
    //ATTACK SPELLS
    public void zapU(CharacterEC enemy)
    {
        if (mana < 10)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 10;
            enemy.takeDamage(8);
            System.out.println("YOU'RE ENEMY TOOK 8 DAMAGE!");
        }
    }
    
    public void fireSpellU(CharacterEC enemy)
    {
        if (mana < 15)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 15;
            enemy.takeDamage(15);
            System.out.println("YOU'RE ENEMY TOOK 15 DAMAGE!");
        }
    }
    
    public void cryoBlastU(CharacterEC enemy)
    {
        if (mana < 40)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 40;
            enemy.takeDamage(30);
            System.out.println("YOU'RE ENEMY TOOK 30 DAMAGE!");
        }
    }
    
    public void divinePowerU(CharacterEC enemy)
    {
        if (mana < 70)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 70;
            enemy.takeDamage(60);
        }
    }
    
    public void blackMagikU(CharacterEC enemy)
    {
        if (mana < 120)
        {
            System.out.println("YOU DON'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 120;
            enemy.takeDamage(enemy.getHealth(enemy));
        }
    }
    
    //UTILITY SPELLS
    public void manaPumpU()
    {
        mana += (mana*0.2);
    }
    
    public void manaRefillU()
    {
        health -= (health*0.2);
        mana += (mana*0.5);
    }
    
    /*CPU SPELL METHODS*/
    //HEAL SPELLS
    public void healSpellC()
    {
        if (mana < 10)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else if ((health + 8) > maxHealth)
        {
            System.out.println("SORRY BUT CPU NEEDS TO LOOSE MORE HEALTH TO USE THIS SPELL");
        }
        else
        {
            mana -= 10;
            health += 8;
        }
    }

    public void cupidsArrowC()
    {
        if (mana < 15)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else if ((health + 15) > maxHealth)
        {
            System.out.println("SORRY BUT CPU NEEDS TO LOOSE MORE HEALTH TO USE THIS SPELL");
        }
        else
        {
            mana -= 15;
            health += 15;
        }
    }

    public void moonShineC()
    {
        if (mana < 40)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 40;            
            int remHealth = Math.abs(100 - health);
            health += remHealth;
        }
    }
    
    public void healBeltC()
    {
        if (mana < 55)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 55;
            health += (health*0.5);
        }
    }
    
    public void whiteMagikC(CharacterEC enemy)
    {
        if (mana < 75)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 75;
            int enemyHealth = enemy.getHealth();
            health += enemyHealth;
            enemyHealth -= (enemyHealth*0.5);
        }
    }
    
    //ATTACK SPELLS
    public void zapC(CharacterEC enemy)
    {
        if (mana < 10)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 10;
            enemy.takeDamage(8);
            System.out.println("YOU TOOK 8 DAMAGE!");
        }
    }
    
    public void fireSpellC(CharacterEC enemy)
    {
        if (mana < 15)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 15;
            enemy.takeDamage(15);
            System.out.println("YOU TOOK 15 DAMAGE!");
        }
    }
    
    public void cryoBlastC(CharacterEC enemy)
    {
        if (mana < 40)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 40;
            enemy.takeDamage(30);
            System.out.println("YOU TOOK 30 DAMAGE!");
        }
    }
    
    public void divinePowerC(CharacterEC enemy)
    {
        if (mana < 70)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 70;
            enemy.takeDamage(60);
        }
    }
    
    public void blackMagikC(CharacterEC enemy)
    {
        if (mana < 120)
        {
            System.out.println("CPU DOESN'T HAVE ENOUGH MANA");
        }
        else
        {
            mana -= 120;
            enemy.takeDamage(enemy.getHealth(enemy));
        }
    }
    
    //UTILITY SPELLS
    public void manaPumpC()
    {
        mana += (mana*0.2);
    }
    
    public void manaRefillC()
    {
        health -= (health*0.2);
        mana += (mana*0.5);
    }
}