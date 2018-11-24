package command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.MyStmtException;

public class TextMenu
{
	private Map<String, Command> commands;
	public TextMenu()
	{
		commands = new HashMap<>();
	}
	public void addCommand(Command c)
	{
		commands.put(c.getKey(), c);
	}
	public void printMenu()
	{
		for(Command com: commands.values())
		{
			String line = String.format("%4s: %s",  com.getKey(), com.getDescription());
			System.out.println(line);
		}
	}
	public void show() throws IOException, MyStmtException
	{
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			printMenu();
			System.out.printf("Input the option: ");
			String key = scanner.nextLine();
			Command com = commands.get(key);
			if (com == null)
			{
				System.out.println("Invalid Option");
				continue;
			}
			//System.out.println(key);
			//System.out.println(com.getDescription());
			com.execute();
		}
	}
}
