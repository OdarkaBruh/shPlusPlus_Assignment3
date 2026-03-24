package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.util.Arrays;
import java.util.Scanner;

/*      Task:
            30 minutes of aerobic exercise five days a week.
            40 minutes three times a week to maintain low blood pressure.
            Write a program that asks the user how many minutes they spent exercising over the past seven days
            and, accordingly, reports the following:

                whether enough time was devoted to cardiovascular health exercises, and,
                    if not, displays: how many days of healthy living are missing from the recommended schedule.
                whether there was enough exercise to lower blood pressure and cholesterol, and,
                    if not, displays: how many days the user fell short of the 40-minute-a-day exercise goal.
 */

public class Assignment3Part1 {
    static final Scanner SCANNER = new Scanner(System.in);
    /*                  TESTING ZONE
        ====================== ======================
    In a test mode, value WON'T be scanned from input, instead will be used variable "testWeek" */

    static final boolean testingMode = false;
    static final int[] testWeek = {40, 0, 30, 40, 20, 80, 0};

    /*  ====================== ======================
                    END OF TEST ZONE  */
    public class TrainingRequirements {
        final String category;
        final int neededMinutes;
        final int neededAmountOfDays;

        TrainingRequirements(String category, int neededMinutes, int neededAmountOfDays) {
            this.category = category;
            this.neededMinutes = neededMinutes;
            this.neededAmountOfDays = neededAmountOfDays;
        }
    }

    void main(String[] args) {
        final int[] weekSurvey;

        //Get weekSurvey from input
        if (!testingMode) weekSurvey = surveyWeek();

            //Test mode => copy provided array and check if it's correct
        else {
            if (testWeek.length == 7) weekSurvey = testWeek;
            else {
                System.out.print("Incorrect number of days (" + testWeek.length + ", should be 7)");
                return;
            }
        }
        SCANNER.close();
        analyseWeek(weekSurvey);
    }

    public static int[] surveyWeek() { //Ask about the week
        int[] weekSurvey = new int[7];
        for (int i = 0; i < 7; i++) {
            weekSurvey[i] = scanDay(i);
        }
        return weekSurvey;
    }

    //Ask person about how was specified day. Depending on the result, update graphs
    public static int scanDay(int day) {
        System.out.print("How many minutes did you do on day " + (day + 1) + "?\t");
        return SCANNER.nextInt();
    }

    //Print the result
    public void analyseWeek(int[] weekSurvey) {
        final TrainingRequirements cardioExercise =
                new TrainingRequirements("Cardiovascular health", 30, 5);
        final TrainingRequirements bloodExercise =
                new TrainingRequirements("Blood pressure", 40, 3);
        System.out.println();
        printWeekReview(cardioExercise, weekSurvey);
        printWeekReview(bloodExercise, weekSurvey);
    }

    //Print result (title, graph and if there was enough done) for a specified category
    public static void printWeekReview(TrainingRequirements trainingRequirements, int[] weekSurvey) {
        System.out.println("\t" + trainingRequirements.category + ": "); // Title
        printGraph(weekSurvey, trainingRequirements.neededMinutes);
        int countOfEnoughDays = (int) Arrays.stream(weekSurvey)
                .filter(s -> s >= trainingRequirements.neededMinutes).count();
        printWhetherEnoughExercisesWereDone(countOfEnoughDays, trainingRequirements.neededAmountOfDays);
    }

    public static void printGraph(int[] weekSurvey, int neededMinutes) {
        String result = "[";
        for (int minutesDone : weekSurvey) {
            result += returnGraphSymbol(minutesDone, neededMinutes);
        }
        System.out.println(result + "]");
    }

    //Add a circle to the week depending on how many exercises were completed
    public static String returnGraphSymbol(int minutes, int neededMinutes) {
        if (minutes >= neededMinutes) return " ●"; //Needed amount was done
        else if (minutes > 0) return " ○"; //Exercises were done, but not needed amount
        else return " ◌"; //Exercises were not done at all
    }

    public static void printWhetherEnoughExercisesWereDone(int amount, int neededAmount) {
        if (amount >= neededAmount) System.out.println("Great job! You've done enough exercise.");
        else System.out.println("You needed to train hard for at least " + (neededAmount - amount)
                + " more day(s) a week!");
    }
}