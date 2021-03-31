package simplemenuapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleMenu
{
	private Scanner consoleScanner = new Scanner(System.in);
	private ArrayList<Student> studentsList = new ArrayList<Student>();
	private int currentId = 0;
	protected void DisplayMenu()
	{
		try
		{
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		}
		catch(InterruptedException | IOException e)
		{
			System.out.println("Consola nu poate fi curatata" + e.getMessage());
		}
		System.out.println(" GESTIONARE STUDENTI1 \n");
		System.out.println("0. Iesire");
		System.out.println("1. Adaugare");
		System.out.println("2. Modificare");
		System.out.println("3. Stergere");
		System.out.println("4. Vizualizare");
		System.out.println("\nIntroduceti optiunea dvs. : ");
	}
	
	public void Run()
	{
		int option = 0;
		do
		{
			DisplayMenu();
			option = consoleScanner.nextInt();
			switch (option)
			{
			case 1 :
				uiAddStudent();
				break;
			case 2 :
				uiModifyStudent();
				break;
			case 3 :
				uiDeleteStudent();
				break;
			case 4 :
				uiViewStudents();
				break;
			default :
				System.out.println("\nAlegeti o optiune valida");
			}
		}while (option != 0);
		System.out.println("Aplicatia se inchide.");
	}
	private void listStudent(Student student)
	{
		System.out.println(student.getId() + "\t" + student.getName() + "\t" + student.getFirstName() + "\t" + student.getAverage());
	}
	private void uiViewStudents()
	{
		System.out.println("Id \tName\tFirstName\tAverage");
		
		for(Student currentStudent: studentsList)
		{
			listStudent(currentStudent);
		}
		consoleScanner.nextLine();
		consoleScanner.nextLine();
	}
	private void uiDeleteStudent()
	{
		System.out.println("Sterge studentul");
	}
	private void uiModifyStudent()
	{
		System.out.println("Modifica studentul");
	}
	private Student readStudentInfo()
	{
		Student newStudent = new Student();
		consoleScanner.nextLine();
		System.out.println("Introduceti numele: ");
		newStudent.setName(consoleScanner.nextLine());
		System.out.println("Intoduceti prenumele: ");
		newStudent.setFirstName(consoleScanner.nextLine());
		System.out.println("Introduceti media: ");
		newStudent.setAverage(consoleScanner.nextLine());
		return newStudent;
	}
	private void uiAddStudent()
	{
		System.out.println("Adauga Studentul");
		Student readStudent = readStudentInfo();
		if(!studentsList.contains(readStudent))
		{
			currentId++;
			readStudent.setId("" + currentId);
			studentsList.add(readStudent);
			return;
		}
		System.out.println("Studentul deja exista");
		consoleScanner.nextLine();
	}
}
