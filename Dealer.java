import java.util.*;
public class Dealer
{
    //dealer's hand
    ArrayList<Card> hand;

    public Dealer()
    {
        hand = new ArrayList<Card>();
    }

    //returns the dealer's hand
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    //fills dealer's hand with a card
    public void fillHand(Card nextCard)
    {
        hand.add(nextCard);
    }

    //returns the open card
    public Card getOpenCard()
    {
        return hand.get(0);
    }

    //returns the closed card
    public Card getClosedCard()
    {
        return hand.get(1);
    }

    //returns the total value of the dealer's hand
    public int getCount()
    {
        int count = 0;

        for (int i = 0; i < hand.size(); i++)
        {
            count += hand.get(i).getValue();
        }
        return count;
    }
}