package service;

import common.Answer;
import common.Question;

public class Application {
	public static void main(String[] args) {
		Question q = new Question("What dis do?");		
		Answer a = new Answer(q, "Djingles the Djangle.");
		System.out.println(a.toString());
	}
}
