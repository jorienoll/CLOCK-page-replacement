/**
 * Name: Noll, Jorie
 * Page Replacement Algorithm
 * File: clock.java
 */
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class clock {

    public static void main(String[] args) throws IOException{
        //grabs input parameters from terminal line executed
         int memorySize = Integer.parseInt(args[0]);
         String pageReference = args[1];
         int hitCost = Integer.parseInt(args[2]);
         int swapInCost = Integer.parseInt(args[3]);
         int swapOutCost = Integer.parseInt(args[4]);
         int pageCounter = 0;
         int readFaultsCounter = 0;
         int writeFaultsCounter = 0;
         int accessingTime = 0;
         int swapInTime = 0;
         int swapOutTime = 0;

         //declares variables to be used through code, all elements 0
         int[][][] currentPage = new int[memorySize][2][2];

         //steps through each new page reference on the text file until there are no more lines
         Scanner solutionScanner = new Scanner(new File(pageReference));
         while (solutionScanner.hasNext() == true){
            // reads each lines read/write and input number
            String readWrite = solutionScanner.next();
            String input = solutionScanner.next();
            int numReference = Integer.parseInt(input);
            boolean inSlots = false;

            //checks if the input number reference is already in one of the slots
            int endArr;
            if (readFaultsCounter + writeFaultsCounter < memorySize-1){
                endArr = readFaultsCounter + writeFaultsCounter;
            }
            else{
                endArr = memorySize;
            }
            for(int i = 0; i < endArr; i++){
                if (numReference == currentPage[i][0][0] && numReference != 0){
                    // the reference is already in a slot
                    inSlots = true;

                    //add hit cost to the accessing time.
                    accessingTime = accessingTime + hitCost;

                    //add to the reference bit of this slot
                    currentPage[i][1][0] = currentPage[i][1][0]++;
                }
            }

            //if the reference is not already in a slot, create a new fault
            if (inSlots == false){

                //check if fault is read or write, create new fault for either
                if (readWrite.equals("W")){
                    writeFaultsCounter++;
                }
                else {
                  readFaultsCounter++;
                }

                //add page miss cost to the swapInTime
                swapInTime = swapInTime + swapInCost;

                //check if the page slots are not all full, find the first empty slot using iteration, enter the input reference
                if((readFaultsCounter + writeFaultsCounter) < memorySize){
                    currentPage[readFaultsCounter + writeFaultsCounter][0][0] = numReference;
                    currentPage[readFaultsCounter + writeFaultsCounter][1][0] = 0;
                    currentPage[readFaultsCounter + writeFaultsCounter][0][1] = 0;
                }
                else{
                  //if the page slots are full, must evict a number to add the new reference

                    // find spot to swap using FIFO
                    int firstInSpot = findReplacementSpot(memorySize, currentPage);
                    //if the reference bit at the FI is not 0, decrement RB and continue the process
                    while (currentPage[firstInSpot][1][0] > 0){
                        currentPage[firstInSpot][1][0]--;
                        firstInSpot = findReplacementSpot(memorySize, currentPage);
                    }

                    //now the reference bit is of replacement is 0, so swap in the new reference at this slot
                    currentPage[firstInSpot][0][0] = numReference;
                    currentPage[firstInSpot][1][0] = 0;
                    currentPage[firstInSpot][0][1] = 0;

                    //add cost to swap out time for the eviction
                    swapOutTime = swapOutTime + swapOutCost;
                 }
              }

              //increment the age each element
              for (int i = 0; i < memorySize; i++){
                  currentPage[i][0][1]++;
              }
              //increment the number of pages
              pageCounter++;
           }


        System.out.print("\n Total number of page references: " + pageCounter);
        System.out.print("\n Total number of page faults on READ: " + readFaultsCounter);
        System.out.print("\n Total number of page faults on WRITE:  " + writeFaultsCounter);
        System.out.print("\n Total number of time units for accessing pages in memory: " + accessingTime);
        System.out.print("\n Total number of time units for swapping in pages: " + swapInTime);
        System.out.print("\n Total number of time units for swapping out pages: " + swapOutTime);
        System.out.print("\n -----------------------------------------------------------------");
      }


      static int findReplacementSpot(int memorySize, int[][][] currentPage){
          //find which slot to replace using FIFO
          int firstInSpot = 0;
          for (int i = 1; i <= memorySize-1; i++){
              if (currentPage[i][0][1] > currentPage[firstInSpot][0][1]){
                  firstInSpot = i;
              }
          }
          return firstInSpot;
      }


  }
