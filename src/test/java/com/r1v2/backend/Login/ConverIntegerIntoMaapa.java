package com.r1v2.backend.Login;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConverIntegerIntoMaapa {
	public static void main(String[] args) {
		int [] arr= new int[3] ;
		arr[0]=10;
		arr[1]=20;
		arr[2]=30;
		
		//Integer[] data=Arrays.stream(arr).boxed().toArray(Integer[]:: new);
		
		
		//Map<Integer, Integer> map1=new HashMap<>();
		
		//map1=
		
		
		
		HashMap<Integer, Integer> map=new HashMap<>();
		
		
		for(Integer i:arr)
		map.put(arr[i], i);
		
		
		for(Map.Entry<Integer, Integer>entry:map.entrySet()){
			System.out.println(entry.getKey()+""+entry.getValue());
			//System.out.println(entry.getValue());
			
		}
	}

}
