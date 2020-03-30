package com.comp6651;

import java.util.ArrayList;
import java.util.Scanner;

public class MeeskeesBakery {

	public MeeskeesBakery() {
	}

	public static void main(String[] args) {
		int n;
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		scanner.nextLine();
		ArrayList<Integer>[] queues = new ArrayList[n];
		for(int index=0; index < n; index++) {
			String[] s = scanner.nextLine().split(" ");
			queues[index] = new ArrayList<>();
			int size = Integer.parseInt(s[0]);
			queues[index].add(size);
			for(int index2=1; index2 <= size; index2++) {
				queues[index].add(Integer.parseInt(s[index2]));
			}
		}
		
		int timeCount = 0;
		int[] currentIndexOfQueues = new int[n];
		//Initialization of current position of each queue
		for(int index=0; index<n; index++) {
			currentIndexOfQueues[index] = 1;
		}
		int minPatience = queues[0].get(1), minPatienceQueue = 0, totalPeopleServed = 0, check = 0;
		boolean allPeopleServed = false, fireStarted = false;
		
		while(!allPeopleServed && !fireStarted) {
			minPatience = queues[minPatienceQueue].get(currentIndexOfQueues[minPatienceQueue]);
//			System.out.println("while loop->" + minPatience + " -> " + minPatienceQueue);
			for(int index=0; index < n; index++) {
				if(currentIndexOfQueues[index] > -1) {
//					System.out.println("inside loop");
					if(queues[index].get(currentIndexOfQueues[index]) <= timeCount) {
//						System.out.println("Fire!!");
						fireStarted = true;
						break;
					}
					if(queues[index].get(currentIndexOfQueues[index]) < minPatience ) {
						minPatience = queues[index].get(currentIndexOfQueues[index]);
						minPatienceQueue = index;
//						System.out.println("Queue-"+minPatienceQueue + " selected");
					}
				}
			}
			for(int index = 0; index < n; index++) {
				if(currentIndexOfQueues[index] > -1) {
					for(int index2=currentIndexOfQueues[index]; index2 < queues[index].size(); index2++) {
						if(queues[index].get(index2) <= timeCount) {
							fireStarted = true;
//							System.out.println("Fire Started");
							break;
						}
						if(queues[index].get(index2) < minPatience && (index2 - currentIndexOfQueues[index]) < queues[index].get(index2)) {
							minPatience = queues[index].get(currentIndexOfQueues[index]);
							minPatienceQueue = index;
//							System.out.println("Queue " + minPatienceQueue + " is selected");
						}	
					}
				}
			}
			
			//Serving the front person
			if(currentIndexOfQueues[minPatienceQueue] < queues[minPatienceQueue].size() && currentIndexOfQueues[minPatienceQueue] > -1 && fireStarted != true) {
//				System.out.println(minPatience + " is served."); //+ queues[minPatienceQueue].get(currentIndexOfQueues[minPatienceQueue])); //
				if(currentIndexOfQueues[minPatienceQueue] == queues[minPatienceQueue].size() - 1) {
					currentIndexOfQueues[minPatienceQueue] = -1;
					check++;
					for(int index = 0; index < n ; index++ ) {
						if(currentIndexOfQueues[index] != -1) {
							minPatienceQueue = index;
							break;
						}
					}
				} else {
					currentIndexOfQueues[minPatienceQueue] += 1;
				}
				timeCount++;
				totalPeopleServed++;
			} else {
//				System.out.println("Queue " + minPatienceQueue + " is empty");
				currentIndexOfQueues[minPatienceQueue] = -1;
				check++;
				for(int index = 0; index < n ; index++ ) {
					if(currentIndexOfQueues[index] != -1) {
						minPatienceQueue = index;
						break;
					}
				}
				
			}
			
			//Checking exit condition
			if(check == queues.length) {
				allPeopleServed = true;
				break;
			}
			
		}
		System.out.println(totalPeopleServed);
//		// To show the input
//		for(int index=0; index < n; index++) {
//			System.out.println(queues[index]);
//		}
	}

}
