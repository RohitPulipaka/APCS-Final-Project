import java.util.*;
public class Deck
{
    //a new card deck
    public ArrayList<Card> newDeck = new ArrayList<Card>();

    public Deck()
    {
        fillDeck();
    }

    //places all 52 cards inside of a deck
    private void fillDeck()
    {
        //adds all cards with the suit of 'Hearts'
        for (int i = 1; i <= 13; i++)
        {
            newDeck.add(new Card("♥", i));
        }
        
        //adds all cards with the suit of 'Diamonds'
        for (int i = 1; i <= 13; i++)
        {
            newDeck.add(new Card("♦", i));
        }
        
        //adds all cards with the suit of 'Clubs'
        for (int i = 1; i <= 13; i++)
        {
            newDeck.add(new Card("♣", i));
        }
        
        //adds all cards with the suit of 'Spades'
        for (int i = 1; i <= 13; i++)
        {
            newDeck.add(new Card("♠", i));
        }
    }

    //shuffles the card randomly
    public void shuffle()
    {
        ArrayList<Card> shuffleDeck = new ArrayList<Card>();
        for (int r = 0; r < newDeck.size(); r++)
        {
            shuffleDeck.add(r, null);
        }

        boolean noCard;
        int location;

        for (Card i: newDeck)
        {
            noCard = true;
            while(noCard)
            {
                location = (int)(newDeck.size()*Math.random());
                if (shuffleDeck.get(location) == null)
                {
                    shuffleDeck.set(location, i);
                    noCard = false;
                }
            }
        }

        newDeck = shuffleDeck;
    }
    
    //deals the first card to the next person
    public Card dealTopCard()
    {
        Card topCard = newDeck.get(0);
        newDeck.remove(0);
        return topCard;
    }
}
