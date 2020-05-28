package com.comp6651;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Red {
	public Red() {
	}
	static int n;
	static int[] startInterval, endInterval;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		startInterval = new int[n];
		endInterval = new int[n];
		scanner.nextLine();
		for(int index=0; index < n; index++) {
			String[] s = scanner.nextLine().split(" ");
			startInterval[index] = Integer.parseInt(s[0]);
			endInterval[index] = Integer.parseInt(s[1]);
		}
		int[][] graphMatrix = new int[n][n];
		for(int index=0; index<n; index++) {
			for(int index2 = 0; index2<n; index2++) {
				graphMatrix[index][index2] = 0;
			}
		}
		for(int index=0; index<n; index++) {
			for(int index2 = index+1; index2<n; index2++) {
				if(Math.max(startInterval[index], startInterval[index2]) <= Math.min(endInterval[index], endInterval[index2])) {
					graphMatrix[index][index2] = 1;
					graphMatrix[index2][index] = 1;
				} else {
					graphMatrix[index][index2] = 0;
					graphMatrix[index2][index] = 0;
				}
			}
		}
		// To print adjacency matrix
//		for(int index=0; index<n; index++) {
//			for(int index2 = 0; index2<n; index2++) {
//				System.out.print(graphMatrix[index][index2] + " ");
//			}System.out.println();
//		}
		
		//Generating all possible sets from given graph
		ArrayList<String> allPossibleSets = new ArrayList<>();
		
		for(int index = 0; index < (1 << n); index++) {
//			allPossibleSets.add(Integer.toString(index));
			String s = "";
			for(int index2 = 0; index2 < n; index2++) {
				if((index & (1 << index2)) > 0) {
					s = s + index2 + " ";
				}		
			}
			if(!s.isEmpty() && s != null) {
				allPossibleSets.add(s.trim());
			}
			
		}
		
//		//Displaying all possible sets
//		System.out.println("All possible sets");
//		for(String s : allPossibleSets) {
//			System.out.println(s);
//		}
		
		ArrayList<String> allPossibleDominatingSet = new ArrayList<>();
		Set<Integer> vertexCover = new HashSet<>();
		
		//Checking for valid dominating set that covers all vertices by adjacency list
		for(String s : allPossibleSets) {
			String[] vertices = s.split(" ");
			for(int index = 0; index < vertices.length; index++) {
				int currentVertex = Integer.parseInt(vertices[index]);
				vertexCover.add(currentVertex);
				for(int index2 = 0; index2 < n; index2++) {
					if(graphMatrix[currentVertex][index2] == 1) {
						vertexCover.add(index2);
					}
				}
			}
			if(vertexCover.size() == n) {
				allPossibleDominatingSet.add(s);
				vertexCover.clear();
			} else {
				vertexCover.clear();
				continue;
			}
		}
		
//		//Displaying All possible Dominating Sets
//		System.out.println("Dominating Sets : ");
//		for(String s : allPossibleDominatingSet) {
//			System.out.println(s);
//		}
		System.out.println(allPossibleDominatingSet.size());
		
	}
}
