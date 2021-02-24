public class Card
{
    private String face, suit;
    private int value; //1 - 13

    //BlackJack_Poker card
    public Card(String type, int card)
    {
        //suit of Card ('Hearts', 'Diamonds', 'Clubs', 'Spades')
        suit = type;
        //value of Card
        value = card;

        if (value >= 2 && value <= 10) //cards the value of 2, 3, 4, 5, 6, 7, 8, 9, 10
        {
            face = "" + value;
        }
        else if (value == 0) //resets value of 'Ace' to be 0 if 'Ace' has to be 1
        {
            value = 1;
            face = "Ace";
        }
        else if (value == 1) //gives initial value of 'Ace'
        {
            value = 11;
            face = "Ace";
        }
        else if (value == 11) //give value to 'Jack'
        {
            value = 10;
            face = "Jack";
        }
        else if (value == 12) //give value to 'Queen'
        {
            value = 10;
            face = "Queen";
        }
        else if (value == 13) //give value to 'King'
        {
            value = 10;
            face = "King";
        }
    }

    //returns the suit of a card
    public String getSuit()
    {
        return suit;
    }

    //returns the value of a card
    public int getValue()
    {
        return value;
    }

    //returns the face value of a card
    public String getFace()
    {
        return face;
    }

    //formats the card display
    public String toString()
    {
        String display = new String(face + " of " + suit);
        return display;
    }
}