import java.util.*;
public class Casino_Arcade
{
    public static void main()
    {
        Scanner s = new Scanner(System.in);

        //stores the players choice [1 for BLACKJACK][2 for VIDEO POKER][3 for ROULETTE][4 to QUIT]
        int choice = 0;
        //sets the initial amount of money the player has
        Money money = new Money(10000);
        //canswers to consecutive security checks
        int age = 0;
        String response;

        System.out.println("HOW OLD ARE YOU?");
        age = s.nextInt();
        if (age < 21)
        {
            //welcomes player into Aracade Rohit [variation of 'Casino Royale']
            System.out.println("--WELCOME TO ARCADE ROHIT!!!--\n");
            System.out.println("WHAT GAME WOULD YOU LIKE TO PLAY?\n\n[1 for ACTION GAME]\n[2 for ROCK PAPER SCISSORS]\n[3 to QUIT]");
            choice = s.nextInt();

            while(choice != 3)
            {
                if (choice == 1)
                {
                    actionGame();
                }
                else if (choice == 2)
                {
                    rps();
                }
                System.out.println("WHAT GAME WOULD YOU LIKE TO PLAY?\n\n[1 for Action Game]\n[2 for ROCK PAPER SCISSORS]\n[3 to QUIT]");
                choice = s.nextInt();
            }
        }
        else if (age >= 21)
        {
            //welcomes player into Casino Rohit [variation of 'Casino Royale']
            System.out.println("--WELCOME TO CASINO ROHIT!!!--\n");
            //accepts the player's decision and stores into 'choice'
            System.out.println("--YOU HAVE $" + money.getMoneyLeft() + " IN YOUR WALLET--\n--WHAT GAME WOULD YOU LIKE TO PLAY?--\n\n[1 for BLACKJACK]\n[2 for VIDEO POKER]\n[3 for ROULETTE]\n[4 to QUIT]");
            choice = s.nextInt();

            //if player chooses 1, 2 or 3, enters loop until player chooses 4 ['Quit']
            while(choice != 4)
            {
                if (money.getMoneyLeft() <= 0)
                {
                    System.out.println("GET OUT OF HERE!!! YOU AIN'T GOT NO MONEY");
                    break;
                }
                else
                {
                    if (choice == 1) //plays Black Jack
                    {
                        blackJack(money ,money.getMoneyLeft());
                    }
                    else if (choice == 2) //plays Video Poker
                    {
                        videoPoker(money ,money.getMoneyLeft());
                    }
                    else if (choice == 3) //plays Roulette
                    {
                        roulette(money ,money.getMoneyLeft());
                    }
                    //loops back and asks for the players choice and stores into 'choice'
                    System.out.println("--YOU HAVE $" + money.getMoneyLeft() + " IN YOUR WALLET--\n--WHAT GAME WOULD YOU LIKE TO PLAY?--\n\n[1 for BLACKJACK]\n[2 for VIDEO POKER]\n[3 for ROULETTE]\n[4 to QUIT]");
                    choice = s.nextInt();
                }
            }
        }
    }
    //Black Jack game method
    public static void blackJack(Money moneyLeft, int moneyInWallet)
    {
        Scanner s = new Scanner(System.in);

        //uses this object to caluclate pocket money value
        Money money = moneyLeft;
        //the physical money value
        int moneyComp = moneyInWallet;
        //stores how much the player wants to bet and hit choice of play [1 to \"Hit\"] or [2 to \"Stay\"] or [3 to \"Double down\"]
        int bet = 0, choice = 0;

        //calls a Deck object, fills deck with cards in order
        Deck deck = new Deck();
        //shuffles the deck of cards
        deck.shuffle();

        //sets up a Player object
        Player player = new Player();
        //set up a Dealer object
        Dealer dealer = new Dealer();

        //fills the dealers hand and the player's hand with cards
        player.fillHand(deck.dealTopCard());
        dealer.fillHand(deck.dealTopCard());
        player.fillHand(deck.dealTopCard());
        dealer.fillHand(deck.dealTopCard());

        //asks how much player want to bet and stores is into 'bet'
        System.out.println("HOW MUCH DO YOU WANT TO BET?");
        bet = s.nextInt();
        while (bet > money.getMoneyLeft())
        {
            System.out.println("YOU DON'T EVEN HAVE THAT MUCH MONEY!!! HOW MUCH DO YOU WANT TO BET?");
            bet = s.nextInt();
        }

        //displays the player's hand, the player's count, and the dealer's hand
        System.out.println("Here is Your Hand: " + player.getHand());
        //determines what value 'Ace' should be
        checkAcePlayer(player);
        System.out.println("Here is Your Hand's Total Value: " + player.getCount());
        System.out.println();
        System.out.println("Here is the Dealer's open card: " + dealer.getOpenCard());

        //if the player's starting hand is 21, multiplys the bet amount by 1.5
        if (is21(player))
        {
            bet *= 1.5;
        }

        //asks for the player's choice [1 to \"Hit\"] or [2 to \"Stay\"] or [3 to \"Double down\"] and stores it into 'choice'
        System.out.println("What would you like to do? [1 to \"Hit\"] or [2 to \"Stay\"] or [3 to \"Double down\"]");
        choice = s.nextInt();

        //if choice = 3
        if (choice == 3)
        {
            if (bet*2 > money.getMoneyLeft())
            {
                System.out.println("YOU DON'T HAVE THAT MUCH MONEY TO DOUBLE DOWN!!! What would you like to do? [1 to \"Hit\"] or [2 to \"Stay\"]");
                choice = s.nextInt();
            }
            else
            {
                //'Double down', increase bet value by 2 times and hit once more and stops
                bet *= 2;
                player.fillHand(deck.dealTopCard());
                System.out.println("Here is Your Hand: " + player.getHand());
                //determines what value 'Ace' should be
                checkAcePlayer(player);
                System.out.println("Here is Your Hand's Total Value: " + player.getCount());
                choice = 2;
            }
        }

        //when choice = 1
        while (choice == 1)
        {
            //deals a card to the player and displays the player's hand and the eplayer's count
            player.fillHand(deck.dealTopCard());
            System.out.println("Here is Your Hand: " + player.getHand());
            //determines what value 'Ace' should be
            checkAcePlayer(player);
            System.out.println("Here is Your Hand's Total Value: " + player.getCount());

            //checks if the player busted (count > 21)
            if (checkPlayerBust(player) == true)
            {
                //if true, exits loop
                choice = 2;
            }
            else
            {
                //or else, continues asking what the player wants to do [1 to \"Hit\"] or [2 to \"Stay\"] and store it into 'choice'
                System.out.println("What would you like to do? [1 to \"Hit\"] or [2 to \"Stay\"]");
                choice = s.nextInt();
            }
        }

        //checks if the player is busted
        if (checkPlayerBust(player) == true) //runs if player is busted
        {
            //calls the checkWinner() method to check who won the game
            if (checkWinner(dealer,player) == 1)
            {
                //formats the correct way to display the results
                if (bet == 1)
                {
                    System.out.println("HEY! YOU WON!!! YOU WON $" + bet + " DOLLAR!");
                    money.addMoney(bet);
                }
                else if (bet > 1)
                {
                    System.out.println("HEY! YOU WON!!! YOU WON $" + bet + " DOLLARS!");
                    money.addMoney(bet);
                }  
            }
            else
            {
                //formats the correct way to display the results
                if (bet == 1)
                {
                    System.out.println("HEY! YOU LOST!!! YOU LOST $" + bet + " DOLLAR!");
                    money.subtractMoney(bet);
                }
                else if (bet > 1)
                {
                    System.out.println("HEY! YOU LOST!!! YOU LOST $" + bet + " DOLLARS!");
                    money.subtractMoney(bet);
                }
            }
        }
        else //runs if player is not busted, dealer's turn
        {
            System.out.println();

            //displays the dealer's hand
            System.out.println("Here is the Dealer's hand: " + dealer.getHand());
            System.out.println();

            //if the total count value of the dealer's hand is less than 16 and the dealer is not bust...
            while (dealer.getCount() < 16 && !checkDealerBust(dealer))
            {
                //...fills the dealer's hand with another card
                dealer.fillHand(deck.dealTopCard());

                //displays the dealer's hand and the dealer's hand count
                System.out.println("Here is the Dealer's hand: " + dealer.getHand());
                //determines what value 'Ace' should be
                checkAceDealer(dealer);
                System.out.println("Here is the Dealer's Total Value: " + dealer.getCount());

                checkDealerBust(dealer);
            }

            //checks the winner again when the dealer's hand count is >= 16
            if (checkWinner(dealer, player) == 1)
            {
                if (bet == 1)
                {
                    System.out.println("HEY! YOU WON!!! YOU WON $" + bet + " DOLLAR!");
                    money.addMoney(bet);
                }
                else if (bet > 1)
                {
                    System.out.println("HEY! YOU WON!!! YOU WON $" + bet + " DOLLARS!");
                    money.addMoney(bet);
                }  
            }
            else if (checkWinner(dealer, player) == 2)
            {
                if (bet == 1)
                {
                    System.out.println("HEY! YOU LOST!!! YOU LOST $" + bet + " DOLLAR!");
                    money.subtractMoney(bet);
                }
                else if (bet > 1)
                {
                    System.out.println("HEY! YOU LOST!!! YOU LOST $" + bet + " DOLLARS!");
                    money.subtractMoney(bet);
                }
            }
            else if (checkWinner(dealer, player) == 0)
            {
                System.out.println("HEY! WE TIED!!! NO ONE GETS ANY MONEY!");
            }
        }
        //end of Black Jack
        //returns back to the main method
    }

    //checks if the initial hand of the player = 21
    public static boolean is21(Player player)
    {
        //yes = true
        //no = false
        if (player.getHand().size() == 2 && player.getCount() == 21)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //determines what value 'Ace' should be
    public static void checkAcePlayer(Player player)
    {
        //goes through this if the player has an 'Ace' in the initial hand
        if (player.getHand().size() == 2)
        {
            //counts how many 'Aces' are in the hand
            int countAce = 0;
            for (int i = 0; i < player.getHand().size(); i++)
            {
                if (player.getHand().get(i).getValue() == 11)
                {
                    countAce++;
                }
            }

            //if more than one 'Ace' in the player's hand, changes the value of 'Ace' from 11 to 1
            if (countAce > 1)
            {
                player.getHand().set(1, new Card(player.getHand().get(1).getSuit(), 0));
            }
        }
        //goes through this if the player has more than 2 cards
        else if (player.getHand().size() > 2)
        {
            //countinues if player's hand count < 21
            if (player.getCount() > 21)
            {
                for (int i = 2; i < player.getHand().size(); i++)
                {
                    if (player.getHand().get(i).getValue() == 11)
                    {
                        //changes all remaining 'Aces' from 11 to 1
                        player.getHand().set(i, new Card(player.getHand().get(i).getSuit(), 0));
                    }
                }
            }
        }
    }

    //checks if the player busted (> 21)
    public static boolean checkPlayerBust(Player player)
    {
        //false = not bust
        //true = bust
        boolean isBust = false;
        if (player.getCount() > 21)
        {
            isBust = true;
        }
        return isBust;
    }

    //same thing as the player's 'Ace' checker
    public static void checkAceDealer(Dealer dealer)
    {
        if (dealer.getHand().size() == 2)
        {
            int countAce = 0;
            for (int i = 0; i < dealer.getHand().size(); i++)
            {
                if (dealer.getHand().get(i).getValue() == 11)
                {
                    countAce++;
                }
            }

            if (countAce > 1)
            {
                dealer.getHand().set(1, new Card(dealer.getHand().get(1).getSuit(), 0));
            }
        }
        else if (dealer.getHand().size() > 2)
        {
            if (dealer.getCount() > 21)
            {
                for (int i = 2; i < dealer.getHand().size(); i++)
                {
                    if (dealer.getHand().get(i).getValue() == 11)
                    {
                        dealer.getHand().set(i, new Card(dealer.getHand().get(i).getSuit(), 0));
                    }
                }
            }
        }
    }

    //same thing as the player bust checker
    public static boolean checkDealerBust(Dealer dealer)
    {
        boolean isBust = false;
        if (dealer.getCount() > 21)
        {
            isBust = true;
        }
        return isBust;
    }

    //determines the winner
    public static int checkWinner(Dealer dealer, Player player)
    {
        //1 = player wins
        //2 = dealer wins
        //0 = draw

        //checks if the player and the dealer tie
        if (dealer.getCount() == player.getCount())
        {
            //tie
            return 0;
        }
        //checks if the player is <= 21 and the dealer is > 21
        else if (dealer.getCount() > 21 && player.getCount() <= 21)
        {
            //player wins
            return 1;
        }
        //checks if the player is > 21 and the dealer is <= 21
        else if (player.getCount() > 21 && dealer.getCount() <= 21)
        {
            //dealer wins
            return 2;
        }
        //checks if the player > dealer or player < dealer
        else
        {
            if (dealer.getCount() > player.getCount())
            {
                //dealer wins
                return 2;
            }
            else
            {
                //player wins
                return 1;
            }
        }
    }

    //Video Poker game method
    public static void videoPoker(Money moneyLeft, int moneyInWallet)
    {
        Scanner s = new Scanner(System.in);

        //uses this object to caluclate pocket money value
        Money money = moneyLeft;
        //the physical money value
        int moneyComp = moneyInWallet;
        //stores how much the player wants to bet and hit choice of play [1 to \"Hit\"] or [2 to \"Stay\"] or [3 to \"Double down\"]
        int bet = 0, choice = 0;

        //calls a Deck object, fills deck with cards in order
        Deck deck = new Deck();
        //shuffles the deck of cards
        deck.shuffle();
        //sets up a Player object
        Player player = new Player();

        System.out.println("HOW MUCH DO YOU WANT TO BET?");
        bet = s.nextInt();
        
        while (bet > money.getMoneyLeft())
        {
            System.out.println("YOU DON'T EVEN HAVE THAT MUCH MONEY!!! HOW MUCH DO YOU WANT TO BET?");
            bet = s.nextInt();
        }

        //fills the player's hand with cards
        //♥, ♦, ♣, ♠
        //player.fillHand(new Card("♣", 10));
        //player.fillHand(new Card("♣", 11));
        //player.fillHand(new Card("♣", 12));
        //player.fillHand(new Card("♣", 13));
        //player.fillHand(new Card("♣", 1));
        
        player.fillHand(deck.dealTopCard());
        player.fillHand(deck.dealTopCard());
        player.fillHand(deck.dealTopCard());
        player.fillHand(deck.dealTopCard());
        player.fillHand(deck.dealTopCard());
        
        player.orderHand();
        System.out.println("Here is your hand: " + player.getHand());        
        String change;
        for(int i = 1; i < 6; i++)
        {
            if (i == 1)
            {
                System.out.println("Do you want to exchange card " + i + "? (Y/N)");
                change = s.nextLine();
                change = s.nextLine();
            }
            else
            {
                System.out.println("Do you want to exchange card " + i + "? (Y/N)");
                change = s.nextLine();
            }
            if (change.equalsIgnoreCase("Y"))
            {
                Card swap = player.getHand().get(i-1);
                player.getHand().remove(player.getHand().get(i-1));
                player.getHand().add(i-1, deck.dealTopCard());
            }
        }

        player.orderHand();
        System.out.println("Here is your new hand: " + player.getHand());
        PayOut checkPO = new PayOut(player.getHand());

        if (checkPO.royalFlush() == true)
        {
            System.out.println("YOU HAVE A ROYAL FLUSH!!!");
            bet *= 250;
            money.addMoney(bet);
        }
        else if (checkPO.straightFlush() == true)
        {
            System.out.println("YOU HAVE A STRAIGHT FLUSH!!!");
            bet *= 50;
            money.addMoney(bet);
        }
        else if (checkPO.fourOfAKind() == true)
        {
            System.out.println("YOU HAVE 4 OF A KIND!!!");
            bet *= 25;
            money.addMoney(bet);
        }
        else if (checkPO.fullHouse() == true)
        {
            System.out.println("YOU HAVE A FULL HOUSE!!!");
            bet *= 9;
            money.addMoney(bet);
        }
        else if (checkPO.flush() == true)
        {
            System.out.println("YOU HAVE A FLUSH!!!");
            bet *= 6;
            money.addMoney(bet);
        }
        else if (checkPO.straight() == true)
        {
            System.out.println("YOU HAVE A STRAIGHT!!!");
            bet *= 4;
            money.addMoney(bet);
        }
        else if (checkPO.threeOfAKind() == true)
        {
            System.out.println("YOU HAVE 3 OF A KIND!!!");
            bet *= 3;
            money.addMoney(bet);
        }
        else if (checkPO.twoPair() == true)
        {
            System.out.println("YOU HAVE A TWO PAIR!!!");
            bet *= 2;
            money.addMoney(bet);
        }
        else if (checkPO.onePair() == true)
        {
            System.out.println("YOU HAVE A ONE PAIR!!!");
            bet *= 1;
            money.addMoney(bet);
        }
        else
        {
            System.out.println("Sorry. You don't have anything");
            bet *= 1;
            money.subtractMoney(bet);
        }
    }

    //Extra Credit Games:
    //Roulette
    public static void roulette(Money moneyLeft, int moneyInWallet)
    {
        Scanner s = new Scanner(System.in);

        //uses this object to caluclate pocket money value
        Money money = moneyLeft;
        //the physical money value
        int moneyComp = moneyInWallet;
        //stores how much the player wants to bet, choice [1 for EVENS] or [2 for ODDS] or [3 for WLK AWAY], and the number the computer generates
        int bet = 0, choice = 0, compNum = 0;
        //determines if player wants to leave
        boolean answer = true;

        //counts how many times game has been played and the format of the questions
        int counter = 0;

        //loops until player wants to walk away
        while(answer && money.getMoneyLeft() > 0)
        {
            //formats what to display in the question
            if (counter == 0)
            {
                System.out.println("What would you like to bet on? [1 for \"EVENS\"] or [2 for \"ODDS\"] or [3 for \"WALK AWAY\"]");
                choice = s.nextInt();
            }
            else if (counter > 0)
            {
                System.out.println("You have $" + money.getMoneyLeft() + " dollars. What would you like to bet on? [1 for \"EVENS\"] or [2 for \"ODDS\"] or [3 for \"WALK AWAY\"]");
                choice = s.nextInt();
            }            

            if (choice == 3) //walks away
            {
                answer = false;
            }
            else //ccontinues the game
            {
                //stores bet value
                System.out.println("HOW MUCH DO YOU WANT TO BET?");
                bet = s.nextInt();
                while (bet > money.getMoneyLeft())
                {
                    System.out.println("YOU DON'T EVEN HAVE THAT MUCH MONEY!!! HOW MUCH DO YOU WANT TO BET?");
                    bet = s.nextInt();
                }

                //generates a random number from 0 - 36
                compNum = (int)(36 * Math.random());

                //determines if the player wins
                if (choice == 1 && compNum % 2 == 0)
                {
                    money.addMoney(bet);
                    System.out.println("The computer chose " + compNum);
                    System.out.println("HEY! YOU WON!!! YOU WON $" + bet + " DOLLARS!");
                }
                else if (choice == 2 && compNum % 2 != 0)
                {
                    money.addMoney(bet);
                    System.out.println("The computer chose " + compNum);
                    System.out.println("HEY! YOU WON!!! YOU WON $" + bet + " DOLLARS!");
                }
                else
                {
                    money.subtractMoney(bet);
                    System.out.println("The computer chose " + compNum);
                    System.out.println("HEY! YOU LOST!!! YOU LOST $" + bet + " DOLLARS!");
                }
            }
            counter++;
        }
    }

    public static void actionGame()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("WHAT IS YOUR NAME?");
        String pName = sc.nextLine();

        CharacterEC player1 = new CharacterEC(pName);
        CharacterEC player2 = new CharacterEC();

        System.out.println("--------------");
        System.out.println(player1.toString());
        System.out.println("--------------");
        System.out.println(player2.toString());
        System.out.println("--------------\n");

        for(int i = 0; player1.getHealth() > 0 && player2.getHealth() > 0; i++)
        {
            if (i % 2 == 0)
            {
                System.out.println("WHAT SPELL WOULD YOU LIKE TO USE?\n\n--HEAL SPELLS--\n(1) = HealSpell [Cost = 10 Mana]  (8 Health)\n(2) = CupidsArrow [Cost = 15 Mana]  (15 Health)\n(3) = MoonShine [Cost = 40 Mana]  (Refills Health)\n(4) = HealBelt [Cost = 55 Mana]  (Gives back 50% of current Health)\n(5) = WhiteMagik [Cost = 75 Mana]  (Reduces enemy's Health by 50% and gives you back opponent's Health before the turn)\n--ATTACK SPELLS--\n(6) = Zap [Cost = 10 Mana]  (Reduces 8 Health of opponent)\n(7) = FireSpell [Cost = 15 Mana]  (Reduces 15 Health of opponent)\n(8) = CryoBlast [Cost = 40 Mana]  (Reduces 30 Health of opponent)\n(9) = DivinePower [Cost = 70 Mana]  (Reduces 60 Health of opponent)\n(0) = BlackMagick [Cost = 120 Mana] (Insta-Kill)\n--UTILITY SPELLS--\n(+) = ManaPump  (Skips turn and gives back 20% Mana of how much Mana player has (Works if user has less than 80 mana))\n(-) = ManaRefill  (Looses 20% of Health and refills Mana by 50% of how much Mana player has (Works if user has at least 10 Health))\n(CHALLENGE[OPTIONAL]: DON'T USE BLACK MAGIK OR MANA REFILL)\n----------------------------------------------------------------------------------------------------------------------------------------------------");
                char choice = sc.next().charAt(0);

                //Users choice
                if (choice == '1')
                {
                    player1.healSpellU();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '2')
                {
                    player1.cupidsArrowU();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '3')
                {
                    player1.moonShineU();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '4')
                {
                    player1.healBeltU();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '5')
                {
                    player1.whiteMagikU(player2);
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '6')
                {
                    player1.zapU(player2);
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '7')
                {
                    player1.fireSpellU(player2);
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '8')
                {
                    player1.cryoBlastU(player2);
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '9')
                {
                    player1.divinePowerU(player2);
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '0')
                {
                    player1.blackMagikU(player2);
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '+')
                {
                    player1.manaPumpU();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
                else if (choice == '-')
                {
                    player1.manaRefillU();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");
                }
            }
            else
            {
                int cpu = (int)(1 + 12 * Math.random());

                //CPU choice
                if (cpu == 1)
                {
                    System.out.println("CPU USED HEAL SPELL");
                    player2.healSpellC();                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 2)
                {
                    System.out.println("CPU USED CUPIDS ARROW");
                    player2.cupidsArrowC();                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 3)
                {
                    System.out.println("CPU USED MOON SHINE");
                    player2.moonShineC();
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 4)
                {
                    System.out.println("CPU USED HEAL BELT");
                    player2.healBeltC();                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 5)
                {
                    System.out.println("CPU USED WHITE MAGIK");
                    player2.whiteMagikC(player1);                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 6)
                {
                    System.out.println("CPU USED ZAP");
                    player2.zapC(player1);                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 7)
                {
                    System.out.println("CPU USED FIRE SPELL");
                    player2.fireSpellC(player1);                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 8)
                {
                    System.out.println("CPU USED CRYO BLAST");
                    player2.cryoBlastC(player1);                  
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 9)
                {
                    System.out.println("CPU USED DIVINE POWER");
                    player2.divinePowerC(player1);                    
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 10)
                {
                    System.out.println("CPU USED BLACK MAGIK");
                    player2.blackMagikC(player1);                    
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 11)
                {
                    System.out.println("CPU USED MANA PUMP");
                    player2.manaPumpC();                    
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
                else if (cpu == 12)
                {
                    System.out.println("CPU USED MANA REFILL");
                    player2.manaRefillC();                    
                    System.out.println("--------------");
                    System.out.println(player1.toString());
                    System.out.println("--------------");
                    System.out.println(player2.toString());
                    System.out.println("--------------\n");                  
                }
            }
        }

        boolean isDone = player1.isDead();
        if(isDone)
        {
            System.out.println("THE CPU WINS!!!");
        }
        else
        {
            System.out.println("YOU WIN!!!");
        }        
    }

    public static void rps()
    {
        Scanner s = new Scanner (System.in);

        int playerRecord = 0, computerRecord = 0;
        int choice, randomNumber;
        boolean inGame = true;
        String answer;

        System.out.println("Welcome to Rock, Paper, Scissors.");

        while(inGame)
        {
            System.out.println("Enter [1 for ROCK][2 for PAPER][3 for SCISSORS]");
            choice = s.nextInt();

            randomNumber = (int)(1 + 3*Math.random());

            //player wins
            if ((choice == 1 && randomNumber == 3)||(choice == 2 && randomNumber == 1)||(choice == 3 && randomNumber == 2))
            {
                if (choice == 1)
                {
                    System.out.println("The computer chose: SCISSORS");
                    System.out.println("You chose: ROCK");
                    System.out.println("Congrats! YOU WON!!!.");
                    playerRecord++;
                }
                else if (choice == 2)
                {
                    System.out.println("The computer chose: ROCK");
                    System.out.println("You chose: PAPER");
                    System.out.println("Congrats! YOU WON!!!.");
                    playerRecord++;
                }
                else
                {
                    System.out.println("The computer chose: PAPEER");
                    System.out.println("You chose: SCISSORS");
                    System.out.println("Congrats! YOU WON!!!.");
                    playerRecord++;
                }
            }
            else if (choice == randomNumber)
            {
                if (choice == 1)
                {
                    System.out.println("The computer chose: ROCK");
                    System.out.println("You chose: ROCK");
                    System.out.println("Hey! WE TIED.");
                }
                else if (choice == 2)
                {
                    System.out.println("The computer chose: PAPER");
                    System.out.println("You chose: PAPER");
                    System.out.println("Hey! WE TIED.");
                }
                else
                {
                    System.out.println("The computer chose: SCISSORS");
                    System.out.println("You chose: SCISSORS");
                    System.out.println("Hey! WE TIED.");
                }
            }
            else
            {
                if (randomNumber == 1)
                {
                    System.out.println("The computer chose: ROCK");
                    System.out.println("You chose: SCISSORS");
                    System.out.println("Looks like you lost.");
                    computerRecord++;
                }
                else if (randomNumber == 2)
                {
                    System.out.println("The computer chose: PAPER");
                    System.out.println("You chose: ROCK");
                    System.out.println("Looks like you lost.");
                    computerRecord++;
                }
                else
                {
                    System.out.println("The computer chose: SCISSORS");
                    System.out.println("You chose: PAPER");
                    System.out.println("Looks like you lost.");
                    computerRecord++;
                }
            }

            System.out.println("Do you want to play again? (Y/N)");
            answer = s.nextLine();
            answer = s.nextLine();

            if (answer.equalsIgnoreCase("Y"))
                inGame = true;
            else
            {
                inGame = false;
                System.out.println("Here is the score: Computer Score (" + computerRecord + "), Your Score (" + playerRecord + ").");

                if (computerRecord > playerRecord)
                    System.out.println("THE COMPUTER WON!!!");
                else if (computerRecord == playerRecord)
                {
                    System.out.println("WE TIED!!!");
                    System.out.println("Hey, since we tied, let's play again to determine the winner. (Ok/No)");
                    answer = s.nextLine();

                    if (answer.equalsIgnoreCase("ok"))
                        inGame = true;
                    else
                    {
                        inGame = false;
                        System.out.println("...");
                    }
                }
                else
                    System.out.println("YOU WON!!!");
            }
        }
    }
}