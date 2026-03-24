package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.util.Random;

/*     So, what are the odds of winning more than 10,000?
    Spoiler: With a sample size of 100,000,000 games, I consistently get ~46,500 games, which is 0.0465%.

    In the end, I got so carried away that I ended up plotting graphs in Python based on the data I had collected.*/
public class Assignment3Part5ext1 {
    static final Random random = new Random();

    static int currentBet = 1;
    static int luckyBalance = 0;

    /*  =================
        You can experiment with values below.

        Sample size (ALL_GAMES_AMOUNT) can be decreased.
        You will get less accurate/correct answer, but it will take less time.
     */
    static final int ALL_GAMES_AMOUNT = 100_000_000;
    static final int MONEY_AFTER_LUCKY_LEAVES = 20;
    static final int MONEY_SUM_THRESHOLD = 10_000;

    public static void main(String[] args) {
        int gamesWithSpecifiedAmountOfMore = 0;
        for (int i = 0; i < ALL_GAMES_AMOUNT; i++) {
            if (playSeparateGame() > MONEY_SUM_THRESHOLD) gamesWithSpecifiedAmountOfMore++;
        }
        System.out.println("Games where lucky won >" + MONEY_SUM_THRESHOLD + ": " + gamesWithSpecifiedAmountOfMore);
        System.out.printf("...which is %.3f%%%n",
                (double) gamesWithSpecifiedAmountOfMore / ALL_GAMES_AMOUNT * 100);
        System.out.println((double) gamesWithSpecifiedAmountOfMore / ALL_GAMES_AMOUNT * 100);
    }

    public static int playSeparateGame() {
        luckyBalance = 0;
        while (luckyBalance < MONEY_AFTER_LUCKY_LEAVES) playGame();
        return luckyBalance;
    }

    //Play a round of the game
    public static void playGame() {
        currentBet = 1;
        while (flipCoin()) currentBet *= 2;
        luckyBalance += currentBet;
    }

    public static boolean flipCoin() {
        return random.nextBoolean();
    }
}

//      If you are interested, here is more of my yapping: https://docs.google.com/document/d/1PETelADFX1CWNrZLVrjk3CZd5Q_PNWm1Xt_UOvnPyE4/edit?usp=sharing