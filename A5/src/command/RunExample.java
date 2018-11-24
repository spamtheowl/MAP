package command;
import java.io.IOException;

import controller.*;
import model.MyStmtException;
public class RunExample extends Command
{
	private Controller ctrl;
	public RunExample(String key, String desc, Controller c)
	{
		super(key, desc);
		this.ctrl = c;
	}
	@Override
	public void execute() throws IOException, MyStmtException
	{
		ctrl.allStep();
	}
}
