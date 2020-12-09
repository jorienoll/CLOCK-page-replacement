# CLOCK-page-replacement

 This program utilizes the CLOCK page replacement algorithm to calculate the total number
 of page faults and the time cost incurred by the page faults, given parameters entered
 by the user.


 In order to compile and execute this program, type the following two commands in the terminal,
 using the following variables:

 javac clock.java
 java clock mem ref hit in swapOutTime
 
 - mem = memory size (the number of available page frames)
 - ref = input page reference file that contains the page references
 - hit = hit cost (time needed to access page resident in memory)
 - in = swap-in cost (time needed to swap a page from swap space into memory)
 - out = swap-out cost (time needed to swap a page out of memory to swap space)

 For example, the user may enter:

 javac clock.java
 java clock 20 pageref.txt 1 10 20


The available text files in this project folder are listed below:
pageref.txt
pageref-small.txt
