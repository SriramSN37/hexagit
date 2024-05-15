package com.example.summa.myexceptions;

public class PatientNumberNotFoundException extends Exception {
	public PatientNumberNotFoundException(String message) {
        super("enter a valid patient Id");
    }
}


