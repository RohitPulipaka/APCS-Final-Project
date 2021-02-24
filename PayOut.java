import java.util.*;
public class PayOut
{
    ArrayList<Card> checkHand;

    public PayOut(ArrayList<Card> hand)
    {
        checkHand = hand;
    }

    //One Pair (1 to 1)
    public boolean onePair()
    {
        for(int i = 0; i < checkHand.size(); i++)
        {
            for(int j = i+1; j < checkHand.size(); j++)
            {
                if (checkHand.get(i).getFace().equals(checkHand.get(j).getFace()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    //Two Pair (2 to 1)
    public boolean twoPair()
    {
        int counter = 0;
        for(int i = 0; i < checkHand.size(); i++)
        {
            for(int j = i+1; j < checkHand.size(); j++)
            {
                if (checkHand.get(i).getFace().equals(checkHand.get(j).getFace()))
                {
                    counter++;
                }
            }
        }

        if (counter == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Three of a Kind (3 to 1)
    public boolean threeOfAKind()
    {
        if ((checkHand.get(0).getFace().equals(checkHand.get(1).getFace()) && checkHand.get(1).getFace().equals(checkHand.get(2).getFace()))
        || (checkHand.get(1).getFace().equals(checkHand.get(2).getFace()) && checkHand.get(2).getFace().equals(checkHand.get(3).getFace()))
        || (checkHand.get(2).getFace().equals(checkHand.get(3).getFace()) && checkHand.get(3).getFace().equals(checkHand.get(4).getFace())))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Straight (4 to 1)
    public boolean straight()
    {
        if (checkHand.get(0).getValue() == 2 && checkHand.get(1).getValue() == 3 && checkHand.get(2).getValue() == 4 && checkHand.get(3).getValue() == 5 && checkHand.get(4).getValue() == 6)
        {
            return true;
        }
        if (checkHand.get(0).getValue() == 3 && checkHand.get(1).getValue() == 4 && checkHand.get(2).getValue() == 5 && checkHand.get(3).getValue() == 6 && checkHand.get(4).getValue() == 7)
        {
            return true;
        }
        if (checkHand.get(0).getValue() == 4 && checkHand.get(1).getValue() == 5 && checkHand.get(2).getValue() == 6 && checkHand.get(3).getValue() == 7 && checkHand.get(4).getValue() == 8)
        {
            return true;
        }
        if (checkHand.get(0).getValue() == 5 && checkHand.get(1).getValue() == 6 && checkHand.get(2).getValue() == 7 && checkHand.get(3).getValue() == 8 && checkHand.get(4).getValue() == 9 )
        {
            return true;
        }
        return false;
    }

    //Flush (6 to 1)
    public boolean flush()
    {
        if (checkHand.get(0).getSuit().equals(checkHand.get(1).getSuit())
        && checkHand.get(1).getSuit().equals(checkHand.get(2).getSuit())
        && checkHand.get(2).getSuit().equals(checkHand.get(3).getSuit())
        && checkHand.get(3).getSuit().equals(checkHand.get(4).getSuit()))
        {
            return true;
        }
        return false;
    }

    //FullHouse (9 to 1)
    public boolean fullHouse()
    {
        if (checkHand.get(0).getFace().equals(checkHand.get(1).getFace()) && checkHand.get(1).getFace().equals(checkHand.get(2).getFace()) && checkHand.get(3).getFace().equals(checkHand.get(4).getFace())
        && (!checkHand.get(0).getFace().equals(checkHand.get(4).getFace())))
        {
            return true;
        }
        if (checkHand.get(2).getFace().equals(checkHand.get(3).getFace()) && checkHand.get(3).getFace().equals(checkHand.get(4).getFace()) && checkHand.get(0).getFace().equals(checkHand.get(1).getFace())
        && (!checkHand.get(0).getFace().equals(checkHand.get(4).getFace())))
        {
            return true;
        }
        return false;
    }

    //Four of a Kind (25 to 1)
    public boolean fourOfAKind()
    {
        if ((checkHand.get(0).getFace().equals(checkHand.get(1).getFace()) && checkHand.get(1).getFace().equals(checkHand.get(2).getFace()) && checkHand.get(2).getFace().equals(checkHand.get(3).getFace())
        || (checkHand.get(1).getFace().equals(checkHand.get(2).getFace()) && checkHand.get(2).getFace().equals(checkHand.get(3).getFace()) && checkHand.get(3).getFace().equals(checkHand.get(4).getFace()))))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Straight Flush (50 to 1)
    public boolean straightFlush()
    {
        if (straight() == true && flush() == true)
        {
            return true;
        }
        return false;
    }

    //Royal Flush (250 to 1)
    public boolean royalFlush()
    {
        if (flush() == true && checkHand.get(0).getValue() == 10 && checkHand.get(1).getValue() == 10 && checkHand.get(2).getValue() == 10 && checkHand.get(3).getValue() == 10 && checkHand.get(4).getValue() == 11)
        {
            return true;
        }
        return false;
    }
}
