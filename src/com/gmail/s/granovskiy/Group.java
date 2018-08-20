package com.gmail.s.granovskiy;


import java.util.Scanner;
import com.gmail.s.granovskiy.MyException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
//import java.util.InputMismatchException;

public class Group implements Voenkom {
	
	/*  empty array for 12 elements  */
	private Student[] studentsArray = new Student[12];
	private int counter;
	
	/*  constructors  */
	public Group(Student[] studentsArray) {
		super();
		this.studentsArray = studentsArray;
	}

	public Group() {
		super();
	}
	
	/*  getters and setters  */
	public Student[] getStudentsArray() {
		return studentsArray;
	}

	public void setStudentsArray(Student[] studentsArray) {
		this.studentsArray = studentsArray;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	/* method addStudent  */
	public void addStudent(Student student) throws MyException {
		/*
		if (student == null) {
			throw new IllegalArgumentException("Null student");
		}
		*/
		for (int i = 0; i < studentsArray.length; i++) {
			if (studentsArray[i] == null) {
				studentsArray[i] = student;
				System.out.println(student.getLastName() + " is added to group.");
				return;
			}
		}
		throw new MyException();
	}

	/*  method deleteStudent  */
	public boolean deleteStudent(int number) { 
		for (int i = 0; i < studentsArray.length; i++) {
			if(studentsArray[i] != null && studentsArray[i].getId() == number) {
				System.out.println();
				System.out.println(studentsArray[i].getLastName() + " is deleted from the group.");
				studentsArray[i] = null;
				return true;
			}
		}
		return false;
	} 
	
	/*  method searchStudent  */
	public Student searchStudent(String searchedLastName) {
		for (Student student : studentsArray) {
			if (student != null && student.getLastName().equals(searchedLastName)) {
				return student;
			}
		}
		return null;
	}
	
	/*  methods to read info about student from scanner  */
	public int getStudentId() {
		System.out.println("Please, enter student's ID.");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public String getStudentFirstName() {
		System.out.println("Please, enter student's first name.");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public String getStudentLastName() {
		System.out.println("Please, enter student's last name.");
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
	
	public int getStudentAge() {
		System.out.println("Please, enter student's age.");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public boolean getStudentSex() {
		System.out.println("Please, enter student's sex."); 
		Scanner sc = new Scanner(System.in);
		return sc.nextBoolean();
	}
	
	public boolean getStudentExamPassed() {
		System.out.println("Please, enter if the student has passed the exams: true or false");
		Scanner sc = new Scanner(System.in);
		return sc.nextBoolean();
	}
	
	public boolean getScholarshipReceived() {
		System.out.println("Please, enter if the student is getting the scolarship: true or false");
		Scanner sc = new Scanner(System.in);
		return sc.nextBoolean();
	}
	
	/*  method to make student from scanned info  */
	public Student makeStudent() {
	    Student student = new Student(getStudentId(), getStudentFirstName(), getStudentLastName(), getStudentAge(), getStudentSex(), getStudentExamPassed(), getScholarshipReceived());
	    return student;
	}	
	
	/*  method to get scanned info and create a student object and add it to the group  */
	public void getStudentInfoAndAdd() {
		/*  Starting dialog with a user  */
		System.out.println();
		System.out.print("Do you want to enter more student data? Please, type: Y/N.");
		
		/*  scanner work   */
		Scanner sc = new Scanner(System.in);
		String word = sc.nextLine();
		System.out.println();
		
		/*  conditional statement  */
		if(word.equals("Y")) {
			try { 
				/*  making and adding student to group  */
				System.out.print("OK! Let's get started! ");
				this.addStudent(this.makeStudent());
				
			} catch (MyException e) {
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("Well... Maybe next time...  :).  Good-bye! ");
		}	
	}

	public int countNulls() {
		counter = 0;
		for (int i = 0; i < studentsArray.length; i++) {
			if (studentsArray[i] == null) {
				counter++;
			}
		}
		return counter;
	}
	
	public void loopStudentInfoAndAdd() {
		final int k = this.countNulls();
		for (int i = 0; i <= k; i++) {
				this.getStudentInfoAndAdd();
				//System.out.println(this);
		}
	}
	
	/*  method for sorting students by last name  */
	public void sortLastName() {
		for (int i = 0; i < studentsArray.length - 1; i++) {
			for (int j = i + 1; j < studentsArray.length; j++) {
				if (compareStudentLastName(studentsArray[i], studentsArray[j]) > 0) {
					Student temp = studentsArray[i];
					studentsArray[i] = studentsArray[j];
					studentsArray[j] = temp;
				}
			}
		}
	}
	/*  method to compare 2 family names  */
	private int compareStudentLastName(Student a, Student b) {
		if (a != null && b == null) {
			return 1;
		}
		if (a == null && b != null) {
			return -1;
		}
		if (a == null && b == null) {
			return 0;
		}
		return a.getLastName().compareTo(b.getLastName());
	}
	
	@Override
	/*  method readyForArmy() returns array voenkomArray of students ready for the Army    */
	public Student[] readyForArmy() {
	/*first the method gets the size of the voenkomArray (amount of students that qualify for the Army)*/
		int size = 0;
		for (Student student : studentsArray) {
			if (student != null && student.isSex() == true && student.getAge() >= 18) {
				size++;
			}
		}
	/* then the method creates, fills and returns voenkomArray */
		Student[] voenkomArray = new Student[size];

		
		try (PrintWriter pw = new PrintWriter("c.csv")) {
			//pw.println("This is the alphabetical list of students who go to the army:");
			//pw.println();
			int i = 0;
			for (Student student : this.studentsArray) {
				if (student != null && student.isSex() == true && student.getAge() >= 18) {
					voenkomArray[i++] = student;
					pw.println(student);
				}
			}	
		} catch (FileNotFoundException e) {
			System.out.println("ERROR FILE WRITE");
		}
		return voenkomArray;
	}
	
	
	@Override
	public String toString() {
		return "Group [studentsArray=" + Arrays.toString(studentsArray) + "]";
	}
	
}
