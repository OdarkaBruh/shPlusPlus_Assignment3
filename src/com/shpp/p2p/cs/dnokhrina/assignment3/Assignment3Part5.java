package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.util.Random;

/*      Task: Two people play: the lucky one and the sweaty one.
            The lucky one leaves the casino when he wins $20 or more.
            The sweaty one puts $1 on the table, and the lucky one starts flipping a coin.
                If it’s heads, the Sweaty adds exactly the same amount to the money on the table.
                If it’s tails, everything on the table goes to the Lucky One.
            If the Lucky One ends up with less than $20, the game is repeated.
*/
public class Assignment3Part5 {
    static final Random random = new Random();

    static int currentBet = 1;
    static int luckyBalance = 0;
    static int gamesAmount = 0;

    public static void main(String[] args) {
        // play until the lucky one has 20 or more.
        while (luckyBalance < 20) playGame();
        System.out.println("It took " + gamesAmount + " games to earn $" + luckyBalance);
    }

    //Play a round of the game
    public static void playGame() {
        gamesAmount++;
        currentBet = 1;

        //if true (heads) ==> the Sweaty adds exactly the same amount to the money on the table.
        while (flipCoin()) currentBet *= 2;

        //if false (tails) ==> everything on the table goes to the Lucky One ==> the game is finished.
        luckyBalance += currentBet;
        System.out.println("This game, you earned $" + currentBet + "\tYour total is $" + luckyBalance);
    }

    /*  flip a coin:
        true == heads == орел
        false == tails == решка        */
    public static boolean flipCoin() {
        return random.nextBoolean();
    }
}