package com.javacodegeeks.junitrestweb.api;

import java.util.ArrayList;

public class RestResponse {
	private ArrayList<String> messages;

	public ArrayList<String> getMessages() {
		return this.messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

	private ArrayList<Result> result;

	public ArrayList<Result> getResult() {
		return this.result;
	}

	public void setResult(ArrayList<Result> result) {
		this.result = result;
	}
}