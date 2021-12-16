package com.company;

import java.util.Scanner;

public class Main {

    /*
    This class runs the Game and User Interface
    User may choose the difficulty of the Puzzle and
    the type of search algorithm they will use to solve
    the algorithms.
     */

  /*
     Initializes puzzles at various difficulties to be chosen
     by the user in main method. Puzzle represented as a one dimensional
     array to keep to simplicity.
  */

    final static int[] puzzle0 =
            {
                    1, 2, 3,
                    4, 5, 6,
                    7, 0, 8
            };
    final static int[] puzzle1 =
            {
                    1, 8, 2,
                    0, 4, 3,
                    7, 6, 5
            };
    final static int[] puzzle2 =
            {
                    1, 5, 8,
                    2, 3, 0,
                    4, 6, 7
            };
    final static int[] puzzle3 =
            {
                    1, 2, 3,
                    4, 0, 5,
                    8, 6, 7
            };

    final static int[] puzzle4 =
            {
                    1, 0, 3,
                    4, 2, 5,
                    8, 6, 7
            };

    final static int[] puzzle5 =
            {
                    1, 2, 3,
                    4, 5, 0,
                    8, 6, 7
            };

    final static int[] puzzle6 =
            {
                    1, 3, 5,
                    7, 8, 4,
                    2, 6, 0
            };

    final static int[] puzzle7 =
            {
                    1, 3, 5,
                    7, 8, 0,
                    2, 6, 4
            };

    final static int[] puzzle8 =
            {
                    1, 2, 3,
                    0, 4, 6,
                    7, 5, 8
            };

    final static int[] puzzle9 =
            {
                    0, 2, 3,
                    1, 4, 6,
                    7, 5, 8
            };

    final static int[] puzzle10 =
            {
                    4, 1, 3,
                    0, 2, 6,
                    7, 5, 8
            };



    final static int[] puzzle11 =
            {
                    1, 2, 3,
                    4, 0, 5,
                    7, 8, 6
            };

    final static int[] puzzle12 =
            {
                    1, 2, 3,
                    4, 5, 0,
                    7, 8, 6
            };


    public static void main(String args[]) {


        Node finalPuzzle = new Node(puzzle0, 0, "", 0);;

        Scanner sc = new Scanner(System.in);

        System.out.println("This is an 8-Puzzle Solver");
        System.out.println("Please choose a board:");


        int userInput = sc.nextInt();

        switch (userInput) {
            case 1:
                finalPuzzle = new Node(puzzle1, 0, "", 0);
                break;
            case 2:
                finalPuzzle = new Node(puzzle2, 0, "", 0);
                break;
            case 3:
                finalPuzzle = new Node(puzzle3, 0, "", 0);
                break;
            case 4:
                finalPuzzle = new Node(puzzle4, 0, "", 0);
                break;
            case 5:
                finalPuzzle = new Node(puzzle5, 0, "", 0);
                break;
            case 6:
                finalPuzzle = new Node(puzzle6, 0, "", 0);
                break;
            case 7:
                finalPuzzle = new Node(puzzle7, 0, "", 0);
                break;
            case 8:
                finalPuzzle = new Node(puzzle8, 0, "", 0);
                break;
            case 9:
                finalPuzzle = new Node(puzzle9, 0, "", 0);
                break;
            case 10:
                finalPuzzle = new Node(puzzle10, 0, "", 0);
                break;
            case 11:
                finalPuzzle = new Node(puzzle11, 0, "", 0);
                break;
            case 12:
                finalPuzzle = new Node(puzzle12, 0, "", 0);
                break;
            case 13:
                //finalPuzzle = new Node(puzzle13, 0, "", 0);
                break;
        }

        finalPuzzle.printPuzzle();


        System.out.println("Please choose a search algorithm:");
        System.out.println("1: Breadth First Search");
        System.out.println("2: A* with Manhattan Search");

        int userInput2 = sc.nextInt();
        Search search = new Search(finalPuzzle);
        if (userInput2 < 1 || userInput2 > 2) {
            System.out.println("Please enter a number between 1 and 7");
        } else {
            if (userInput2 == 1) {
                System.out.println("You chose BreadthFirstSearch");
                System.out.println("Running Search.....");
                search.breadthFirstSearch();
            }
        }
        if (userInput2 == 2) {
            System.out.println("You chose A* with Manhattan Search");
            System.out.println("Running search....");
            search.aStar2FirstSearch();
        }

    }
}


