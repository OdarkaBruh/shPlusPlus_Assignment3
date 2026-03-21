package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/*     So, what are the odds of winning more than 10,000?
    Spoiler: With a sample size of 100,000,000 games, I consistently get ~46,500 games, which is 0.0465%.
 */
public class Assignment3Part5ext2 {
    static final Random random = new Random();

    static int currentBet = 1;
    static int luckyBalance = 0;

    /*  =================
        You can experiment with values below.

        Sample size (ALL_GAMES_AMOUNT) can be decreased.
        You will get less accurate/correct answer, but it will take less time.
     */
    static final int ALL_GAMES_AMOUNT = 10_000;
    static final int MONEY_AFTER_LUCKY_LEAVES = 20;
    static final int MONEY_SUM_THRESHOLD = 10_000;

    static final int AMOUNT_OF_GAMES_TO_GROUP = 1;

    public static void main(String[] args) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < ALL_GAMES_AMOUNT / AMOUNT_OF_GAMES_TO_GROUP; i++) {
            int sum = 0;
            for (int j = 0; j < AMOUNT_OF_GAMES_TO_GROUP; j++) {
                sum += playSeparateGame();
            }
            result.add(sum / AMOUNT_OF_GAMES_TO_GROUP);
        }
        try {
            new FileWriter("result.txt").write(result.toString().replaceAll("[ \\[\\]]",""));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Sometime at this stage, I decided to stop trying to draw graphs using Java, and switched to Python.
        I don't have time right now to figure out how to draw graphs in Java, I am sorry 😭  */
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