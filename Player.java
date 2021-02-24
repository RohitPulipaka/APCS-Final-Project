import java.util.*;
public class Player
{
    //player's hand
    ArrayList<Card> hand;

    public Player()
    {
        hand = new ArrayList<Card>();
    }

    //return the player's hand from least value to greatest value
    public void orderHand()
    {
        ArrayList<Card> newHand = new ArrayList<Card>();
        int length = hand.size();
        int min = 0;        
        while(newHand.size() != length)
        {
            for (int i = 0; i < hand.size(); i++)
            {
                if (i == 0)
                {
                    min = hand.get(i).getValue();
                }
                else if (hand.get(i).getValue() < min)
                {
                    min = hand.get(i).getValue();
                }
            }
            for (int c = 0; c < hand.size(); c++)
            {
                if (hand.get(c).getValue() == min)
                {
                    Card minCard = hand.get(c);
                    hand.remove(c);
                    newHand.add(minCard);
                    break;
                }
            }
            min = 0;
        }
        hand = newHand;
    }

    //returns the player's hand
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    //fills player's hand with a card
    public void fillHand(Card nextCard)
    {
        hand.add(nextCard);
    }

    //returns the total value of the player's hand
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
