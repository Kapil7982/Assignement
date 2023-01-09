package com.question2;

import java.util.*;
import java.io.*;


public class CSV {


	public static void main(String[] args) throws IOException {
		
		// Reading the input of csv file.
		
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ASHU BABA\\Documents\\Zoom\\csv1.csv"));
		
		String line;
		
		List<String[]> lines =new ArrayList<>();
		
		while((line= reader.readLine())!=null)
		{
			lines.add(line.split(","));
		}
		
		reader.close();
		
		//After calculating the formula saving the output in this file.
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ASHU BABA\\Documents\\Zoom\\csv1.csv"));
		
		for(String[] rows: lines)
		{
			for(int i=0; i<rows.length; i++)
			{
				if(rows[i].startsWith("="))
				{
					rows[i]= String.valueOf(evaluate(rows[i].substring(1)));
				}
			}
			writer.write(String.join(",", rows));
			writer.newLine();
		}
		
	}

	// calculating the formula
	
	private static int evaluate(String formula) {
		
		
		String[] str = formula.split("\\+");
		
		int answer =0;
		
		for(String term: str)
		{
			if(Character.isDigit(term.charAt(0)))
			{
				answer+= Integer.parseInt(term);			
		    }
			else
			{
				answer += evaluate(term);
			}
		}
		return answer;
	}
}
