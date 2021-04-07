package reusablemenu.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import reusablemenu.abstractions.IMenuItem;
import reusablemenu.implementation.Menu;
import reusablemenu.implementation.MenuItem;


public class ApplicationMenu 
{
	private Scanner consoleScanner = new Scanner(System.in);
	private ArrayList<Student> studentsList = new ArrayList<Student>();
	private Menu mainMenu = null;
	
	public ApplicationMenu()
	{
		
	}
	
	private void uiAddStudent()
	{
		System.out.println("Adauga student ");
		try
		{
			System.out.println("Nume: ");
			String nume = consoleScanner.next();
			
			System.out.println("Prenume: ");
			String prenume = consoleScanner.next();
			
			System.out.println("Medie: ");
			double medie = consoleScanner.nextDouble();
			
			studentsList.add(new Student());	
		}
		catch(Exception e)
		{
			System.out.println("Eroare " + e.getMessage());
		}
	}
	
	private void uiDeleteStudent()
	{
		System.out.println("Stergeti student");
	}
	
	private void uiViewStudents()
	{
		System.out.println("Nume\tPrenume\tMedie");
		
		for(Student currentStudent: studentsList)
		{
			listStudent(currentStudent);
		}
		consoleScanner.nextLine();
		consoleScanner.nextLine();
	}

	private void listStudent(Student currentStudent) 
	{
		System.out.println((studentsList.indexOf(currentStudent) + 1) + "\t" + currentStudent.getNume() + "\t" + currentStudent.getPrenume() + "\t" + currentStudent.getMedie());
	}
	
	private void crescatorMedie()
	{
		Collections.sort(studentsList, new Comparator<Student>()
		{
			public int compare(Student Student1, Student Student2)
			{
				return Double.compare(Student1.getMedie(), Student2.getMedie());
			}
		});
		uiViewStudents();
	}
	
	private void descrescatorMedie()
	{
		Collections.sort(studentsList, new Comparator<Student>()
		{
			public int compare(Student Student1, Student Student2)
			{
				return Double.compare(Student1.getMedie(), Student2.getMedie());
			}
		});
		Collections.reverse(studentsList);
		uiViewStudents();
	}
	
	private void uiModifyStudent()
	{
		uiViewStudents();
		try
		{
			System.out.println("Introduceti index-ul studentului: ");
			int index = consoleScanner.nextInt();
			System.out.println("Introduceti modificarea numelui studentului: ");
			String nume = consoleScanner.next();
			System.out.println("Introduceti modificarea prenumelui studentului: ");
			String prenume = consoleScanner.next();
			System.out.println("Introduceti modificarea mediei studentului: ");
			double medie = consoleScanner.nextDouble();
			
			studentsList.set(index, new Student());
		}
		catch(Exception e)
		{
			System.out.println("Eroare " + e.getMessage());
		}
	}
	
	public void load()
	{
		ArrayList<IMenuItem> menuItems = new ArrayList();
		ArrayList<IMenuItem> showStudentsOptions = new ArrayList();
		
		int shortCut = 1;
		
		IMenuItem currentItem = new MenuItem("Adaugare student nou", shortCut++, (prameters)->{
			uiAddStudent();
			});
		menuItems.add(currentItem);
		
		IMenuItem currentItem1 = new MenuItem("Stergere informatii student", shortCut++, (prameters)->{
			uiDeleteStudent();
			});
		menuItems.add(currentItem1);
		
		currentItem1 = new MenuItem("Modificare informatii student", shortCut++, (prameters)->{
			uiModifyStudent();
			});
		menuItems.add(currentItem1);
		
		showStudentsOptions.add(new MenuItem("Ordonare medie crescator", 1, (parameters)->{
			crescatorMedie();}));
		
		showStudentsOptions.add(new MenuItem("Ordonare medie descrescator", 2, (parameters)->{
			descrescatorMedie();
			}));
		
		currentItem1 = new Menu("Afiseaza informatii student", shortCut++, showStudentsOptions);
		menuItems.add(currentItem1);
		
		mainMenu = new Menu("Main Menu", -1, menuItems);
	}
	
	public void execute()
	{
		mainMenu.execute();
	}
}
