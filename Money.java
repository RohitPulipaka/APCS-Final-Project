public class Money
{
    //amount of money the player has
    private int moneyLeft;
    
    public Money(int money)
    {
        moneyLeft = money;
    }
    
    //returns how much money there is
    public int getMoneyLeft()
    {
        return moneyLeft;
    }
    
    //adds the money that was bet
    public void addMoney(int money)
    {
        moneyLeft += money;
    }
    
    //subtracts the money that was bet
    public void subtractMoney(int money)
    {
        moneyLeft -= money;
    }
}
