package com.comp6651;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SocretesCows {

	public SocretesCows() {
		// TODO Auto-generated constructor stub
	}

	static char source[];
	static char destination[];
	static int[] cost;
	static int noOfPath;
	static Map<String, Integer> pathToStable = new HashMap<>();
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		noOfPath = scan.nextInt();
//		ArrayList<String> paths = new ArrayList<>();
		scan.nextLine();
		
		source = new char[noOfPath];
		destination = new char[noOfPath];
		cost = new int[noOfPath];
		
		for(int index=0; index < noOfPath; index++) {
				String s = scan.nextLine();
				String[] temp = s.split(" ");
				source[index] = (char)temp[0].charAt(0);
				destination[index] = (char)temp[1].charAt(0);
				cost[index] = Integer.parseInt(temp[2]);
		}
		
		
		char dest = 'z';
		while(true) {
			for(int index=0; index < noOfPath; index++) {
				int timeToStable = 0;
				String path = null;
				if(source[index] >= 'A' && source[index] <= 'Z') {
					path = source[index] + " " + destination[index];
					goToPath(source[index], destination[index], cost[index], 0, path);
				}
			}
			break;
		}
		System.out.println("Done!!");
		char meadow = pathToStable.keySet().toArray()[0].toString().charAt(0);
		int minTimeToReachStable = (int) pathToStable.values().toArray()[0];
		for(Map.Entry<String, Integer> entry : pathToStable.entrySet()) {
			if(entry.getValue() < minTimeToReachStable) {
				meadow = entry.getKey().charAt(0);
				minTimeToReachStable = entry.getValue();
			}
		}
		System.out.println(meadow + " " + minTimeToReachStable);
//		for(String s : paths) {
//			
//		}
//		System.out.println(noOfPath);
//		for(int index=0; index < noOfPath; index++) {
////			System.out.println(paths.get(index));
//		}
	}
	public static void goToPath(char src, char dest, int time, int totalTime, String path) {
		if(dest == 'z') {
			totalTime += time;
			pathToStable.put(path, totalTime);
			System.out.println(path + " -> " + totalTime);
		} else {
			totalTime += time;
			src = dest;
			for(int index=0; index<noOfPath; index++) {
				if(source[index] == dest) {
					dest = destination[index];
					time = cost[index];
					path = path + " " + dest;
					break;
				}
			}
			goToPath(src, dest, time, totalTime, path);
		}
	}
}
