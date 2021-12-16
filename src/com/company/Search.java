package com.company;

import java.lang.reflect.Array;
import java.util.*;

@SuppressWarnings("Duplicates")
public class Search {
  private final int[] GOAL = new int[]{ 1,2,3,4,5,6,7,8,0 };
  private Node root; // Initial State

  private Queue<Node> queue = new LinkedList<>(); // Used in Breadth first search


  private List<Node> childPath = new LinkedList<>(); // Used to hold the path from found node to root


  private ArrayList<int[]> visited = new ArrayList<>();// holds all Visited Node states

  private int nodesVisited = 0;

  public Search(Node root) {
    this.root = root;
  }
  /*\\\\\\\\\\\\\\/////////////////////////\\\\\\\\\\\\\\\\\\//////////////////////\\\\\\\\\\\\\\\\\\//////////////////////////\*/

  public void breadthFirstSearch() {
    queue.add(root); //adds root Node to queue
    int maxQueueSize = 1;
    boolean goalFound = false;
    long start_time = System.currentTimeMillis();
    while (!queue.isEmpty() && !goalFound) { // checks if queue is not empty run loop
      nodesVisited += 1;
      Node currentNode = queue.poll(); // removes front node from queue adds to current
      visited.add(currentNode.getPuzzleState()); // add the current Node to visited
      ArrayList<Node> nextSuccessors = currentNode.createSuccessors(); //Creates Nodes in successor function
      for (Node childNode : nextSuccessors) {
        if(!isList(visited, childNode.getPuzzleState())){ // checks to see if childNodeState is in visited
          if (childNode.isGoal()) { //goal checker
            long end_time = System.currentTimeMillis();
            childPathTracer(childPath, childNode);  //creates a path from found goal to rootNode
            pathPrinter(childPath);//prints path from found goal to rootNode
            GoalPrinter(nodesVisited, movementPrint(childPath), depthCalculator(childPath), movementCostPrint(childPath),  childNode.getTotalCost()); //Prints statistics
            System.out.println("Algo took " + (end_time - start_time) +"ms");
            System.out.println("Max queue size = " + maxQueueSize);
            goalFound = true; //exits loop
          }
          if (!queue.contains(childNode)) { //make sure queue doesn't contain child
            queue.add(childNode); //add child to front of the queue
            if (queue.size() > maxQueueSize)
              maxQueueSize = queue.size();
          }
        }
      }
    }
  }


  PriorityQueue<Node> a2PriorityQueue = new PriorityQueue<>(costComparator);
  public void aStar2FirstSearch(){
    a2PriorityQueue.add(root);
    int maxQueueSize = 1;
    boolean goalFound = false;
    int totalCost;
    long start_time = System.currentTimeMillis();
    while(!a2PriorityQueue.isEmpty() && !goalFound) {
      nodesVisited+=1;

      Node currentNode = a2PriorityQueue.poll(); // removes the min Node
      visited.add(currentNode.getPuzzleState());
      ArrayList<Node> nextSuccessors = currentNode.createSuccessors();
      for (Node childNode : nextSuccessors) {
        if (!isList(visited, childNode.getPuzzleState())) { // checks to see if childNodeState is in visited
          long end_time = System.currentTimeMillis();
          if (childNode.isGoal()) { //goal checker
            childPathTracer(childPath, childNode);  //creates a path from found goal to rootNode
            pathPrinter(childPath);//prints path from found goal to rootNode
            GoalPrinter(nodesVisited, movementPrint(childPath), depthCalculator(childPath), movementCostPrint(childPath), childNode.getTotalCost()); //Prints statistics
            System.out.println("Algo took " + (end_time - start_time) +"ms");
            System.out.println("Max queue size = " + maxQueueSize);
            goalFound = true; //exits loop
          }
          totalCost = childNode.getCost() + childNode.getTotalCost(); // tallies Cost
          childNode.setTotalCost(totalCost +manhattanHeuristic(childNode.getPuzzleState(), GOAL));
          if(!a2PriorityQueue.contains(childNode)){
            a2PriorityQueue.add(childNode);
            if (maxQueueSize < a2PriorityQueue.size())
              maxQueueSize = a2PriorityQueue.size();
          }
        }
      }
    }
  }

  /*\\\\\\\\\\\\\\/////////////////////////\\\\\\\\\\\\\\\\\\//////////////////////\\\\\\\\\\\\\\\\\\//////////////////////////\*/


  /**
   * Printer function to help clean the code
   * @param nodesVisited
   */
  public void GoalPrinter(int nodesVisited, ArrayList<String> moves, int depth, int moveCost, int totalCost)
  {
    System.out.println("========================================");
    System.out.println("Goal Node Found!");
    System.out.println("Nodes Visited: " + nodesVisited);
    System.out.println("Moves to Goal: " + moves);
    System.out.println("Depth: " + depth );
    //System.out.println("Path Cost: " + moveCost);
    //System.out.println("Total Path Cost: " + totalCost );
    System.out.println("=====================================");
  }

  /**
   * Stream to determine if a nodeState is in the
   * visited arrayList or not.
   */

  public boolean isList(List<int[]> visit, int[] nodeState){
    return visit.stream().anyMatch(a-> Arrays.equals(a, nodeState));
  }


  /**
   * Traces the path from final goal node up to the root adding the parents to a list path
   * @param path takes in the empty child path
   * @param n takes in the found childNode == goal
   */
  public void childPathTracer(List<Node> path, Node n){
    Node current = n;
    path.add(current);
    while(current.parent != null){
      current = current.parent;
      path.add(current);
    }
  }

  /**
   * Helps print cost for BFS
   * @param childPath
   * @return
   */

  public  int movementCostPrint(List<Node> childPath){
    int sum = 0;
    ArrayList<Integer> movementCost = new ArrayList<>();
    for (int i = childPath.size() - 1; i >= 0; i--) {
      movementCost.add(childPath.get(i).getCost());
    }
    for (int i = 0; i < movementCost.size(); i++) {
      sum = movementCost.stream().mapToInt(Integer::intValue).sum();
    }
    return sum;
  }

  /**
   * Used in priority queue to sort priority queue by the totalCost keeping the min item
   * at front of the queue
   */
  public static Comparator<Node> costComparator = new Comparator<Node>() {
    @Override
    public int compare(Node o1, Node o2) {
      return (o1.getTotalCost() - o2.getTotalCost());
    }
  };

  /**
   * Helper function to calculate depth
   * @param childPath
   * @return
   */

  public int depthCalculator(List<Node> childPath){
    int depth = 0;
    for(int i = 0; i < childPath.size(); i++){
      depth += 1;
    }
    return depth;
  }

  /**
   * Prints the moves from goalNode to root
   * @param childPath
   * @return
   */
  public ArrayList<String> movementPrint(List<Node> childPath) {
    ArrayList<String> movement = new ArrayList<>();
    if (childPath.size() > 0) {
      for (int i = childPath.size() - 1; i >= 0; i--) {
        movement.add(childPath.get(i).getMove());
      }
    }
    return movement;
  }

  /**
   *  takes in the list filled by the childPathTracer and prints out that path
   *  If there is no path prints no path
   * @param childPath takes in list childpath
   */
  public void pathPrinter(List<Node> childPath) {
    if(childPath.size() > 0){
      for(int i = 0; i < childPath.size(); i++){
        childPath.get(i).printPuzzle();
        System.out.println("Current Cost:" + childPath.get(i).getTotalCost());

      }
    }else{
      System.out.println("error");
    }
  }



  /**
   * Manhattan distance calculator used in A*2
   * @param puzzleState
   * @param goal
   * @return
   */
  private int manhattanHeuristic(int[] puzzleState, int[] goal){
    int manhattanDistance = 0;
    for(int i = 0; i < puzzleState.length;i++){
      for(int j = 0; j < puzzleState.length; j++){
        if(puzzleState[i] == GOAL[j]){
          manhattanDistance =  manhattanDistance + ((Math.abs(i % 3 - j % 3)) + (Math.abs(i / 3 + j / 3)));
        }
      }
    }
    return manhattanDistance;
  }
}
