package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import controller.Controller;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.collections.*;
import stmt.*;
import exp.*;
import repository.*;
import dictionary.*;
import list.*;
import model.*;
import stack.*;
import tuple.*;

public class Main extends Application
{
	private Controller ctrl;

	IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new VarExp("v")));


	IStmt ex2 = new CompStmt (new CompStmt(new AssignStmt("a", new ArthExp('+', new ConstExp(2), new ConstExp(1))),
			new AssignStmt("b", new ArthExp('/', new VarExp("a"), new ConstExp(0)))), new PrintStmt(new VarExp("b")));

	IStmt ex3 = new CompStmt(new AssignStmt("a", new ArthExp('-',new ConstExp(2), new ConstExp(2))), new CompStmt(new IfStmt(new VarExp("a"),
			new AssignStmt("v",new ConstExp(2)), new AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));

	IStmt ex4 = new CompStmt(new openRFile("var_f", "test.in"), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
			new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
					new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
					new PrintStmt(new ConstExp(0))), new closeRFile(new VarExp("var_f"))))));

	IStmt ex5 = new CompStmt(new openRFile("var_f", "test.in"), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
			new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
					new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
					new PrintStmt(new ConstExp(0))), new CompStmt (new closeRFile(new VarExp("var_f")), new closeRFile(new VarExp("var_f")))))));

	IStmt ex6 = new CompStmt(new CompStmt(new openRFile("var_f", "test.in"), new openRFile("var_f", "test.in")), new CompStmt(new readFile(new VarExp("var_f"), "var_c"),
			new CompStmt(new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
					new CompStmt(new readFile(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
					new PrintStmt(new ConstExp(0))), new CompStmt (new closeRFile(new VarExp("var_f")), new closeRFile(new VarExp("var_f")))))));

	IStmt ex7 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)),
			new CompStmt(new NouveauStmt("a", new ConstExp(22)), new PrintStmt(new VarExp("v")))));

	IStmt ex8 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)),
			new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new PrintStmt(new ArthExp('+', new ConstExp(100),
					new ReadHeapExp("v"))), new PrintStmt(new ArthExp('+', new ConstExp(100), new ReadHeapExp("a")))))));

	IStmt ex9 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)),
			new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new WriteHeapStmt("a", new ConstExp(30)),
					new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new ReadHeapExp("a")))))));


	IStmt ex10 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)),
			new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new WriteHeapStmt("a", new ConstExp(30)),
					new CompStmt(new PrintStmt(new VarExp("a")), new CompStmt(new PrintStmt(new ReadHeapExp("a")),
							new AssignStmt("a", new ConstExp(0))))))));

	IStmt ex11 = new CompStmt(new AssignStmt("v", new ConstExp(6)), new CompStmt(new WhileStmt(new ArthExp('-', new VarExp("v"),
			new ConstExp(4)), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArthExp('-', new VarExp("v"),
					new ConstExp(1))))),new PrintStmt(new VarExp("v"))));

	IStmt ex12 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("v", new ConstExp(20)),
			new CompStmt(new NouveauStmt("a", new ConstExp(22)), new CompStmt(new WriteHeapStmt("a", new ConstExp(30)),
					new CompStmt(new PrintStmt(new VarExp("a")), new CompStmt(new PrintStmt(new ReadHeapExp("a")),
							new CompStmt(new AssignStmt("a", new ConstExp(0)), new PrintStmt(new ReadHeapExp("a")))))))));

	IStmt ex13 = new PrintStmt(new ArthExp('+', new ConstExp(10), new LessThanExp(new ConstExp(2), new ConstExp(6))));

	IStmt ex14 = new PrintStmt(new LessThanExp(new ArthExp('+', new ConstExp(10), new ConstExp(2)), new ConstExp(6)));

	IStmt ex15 = new CompStmt(new openRFile("var_f", "test.in"), new PrintStmt(new ReadHeapExp("a")));

	IStmt ex16 = new CompStmt(new AssignStmt("v", new ConstExp(10)), new CompStmt(new NouveauStmt("a", new ConstExp(22)),
			new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(32)),
					new CompStmt(new WriteHeapStmt("a", new ConstExp(30)),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))),
					new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))));

	IStmt ex17 = new CompStmt(new CompStmt(new AssignStmt("v", new ConstExp(10)), new NouveauStmt("a", new ConstExp(22))),
			new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(32)),
					new CompStmt(new WriteHeapStmt("a", new ConstExp(30)),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))),
					new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(3)),
					new CompStmt(new WriteHeapStmt("a", new ConstExp(31)),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))));

	IStmt ex18 = new CompStmt(new CompStmt(new AssignStmt("v", new ConstExp(10)), new NouveauStmt("a", new ConstExp(22))),
			new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(3)),
					new CompStmt(new ForkStmt(new CompStmt(new CompStmt(new WriteHeapStmt("a", new ConstExp(3)),
							new AssignStmt("v", new ConstExp(31))),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))),
							new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))));

	ObservableList<String> prgsStr = FXCollections.observableArrayList(ex1.toString(), ex2.toString(), ex3.toString(), ex4.toString(), ex5.toString(),
			ex6.toString(), ex7.toString(), ex8.toString(), ex9.toString(), ex10.toString(), ex11.toString(), ex12.toString(), ex13.toString(),
			ex14.toString(), ex15.toString(), ex16.toString(), ex17.toString(), ex18.toString());
	ObservableList<IStmt> prgs = FXCollections.observableArrayList(ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12, ex13, ex14, ex15,
			ex16, ex17, ex18);

	ListView<String> programList = new ListView<String>(prgsStr);
	TextField noOfPrograms = new TextField();
	TableView<Map.Entry<Integer, Integer>> heapTable = new TableView<Map.Entry<Integer, Integer>>(); //address & value
	ListView<String> out = new ListView<String>();
	TableView<Map.Entry<Integer, MyTuple<String, BufferedReader>>> fileTable = new TableView<Map.Entry<Integer, MyTuple<String, BufferedReader>>>(); //identifier && filename
	ListView<String> prgStates = new ListView<String>();
	ObservableList<String> prgListId;
	TableView<Map.Entry<String, Integer>> symTable = new TableView<Map.Entry<String, Integer>>();
	ListView<String> exeStack = new ListView<String>();
	TextField currentPrg = new TextField();
	Button run1Step = new Button("One Step");
	Button init = new Button("Pick program");

	private static PrgState createPrg(IStmt ex)
	{
		MyIStack<IStmt> stk = new MyStack<IStmt>();
		MyIDictionary<String, Integer> symtbl = new MyDictionary<String, Integer> ();
		MyIList<Integer> ot = new MyList<Integer>() ;
		MyIDictionary<Integer, MyTuple<String, BufferedReader>> fT = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
		MyIDictionary<Integer, Integer> heap = new MyDictionary<Integer, Integer>();
		PrgState prg = new PrgState(stk, symtbl, ot, fT, heap, ex, 0);
		return prg;
	}

//	MyIStack<IStmt> exeStack;
//	MyIDictionary<String, Integer> symTable;
//	MyIList<Integer> out;
//	MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable;
//	MyIDictionary<Integer, Integer> heap;

	@Override
	public void start(Stage window)
	{
		noOfPrograms.setText(Integer.toString(prgs.size()));
		TableColumn<Map.Entry<Integer, Integer>, Integer> addressColumn = new TableColumn<>("Address");
		TableColumn<Map.Entry<Integer, Integer>, Integer> valueColumn = new TableColumn<>("Value");
		TableColumn<Map.Entry<Integer, MyTuple<String, BufferedReader>>, Integer> idColumn = new TableColumn<>("Identifier");
		TableColumn<Map.Entry<Integer, MyTuple<String, BufferedReader>>, MyTuple<String, BufferedReader>> fileNameColumn = new TableColumn<>("Filename");
		TableColumn<Map.Entry<String, Integer>, String> varNameColumn = new TableColumn<>("Variable Name");
		TableColumn<Map.Entry<String, Integer>, Integer> valColumn = new TableColumn<>("Value");

		addressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, ObservableValue<Integer>>()
		{
			@Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer> p)
			{
                // this callback returns property for just one cell, you can't use a loop here
                // for first column we use key
                return new SimpleObjectProperty<Integer>(p.getValue().getKey());
            }
        });

		valueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer>, ObservableValue<Integer>>()
		{
			@Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, Integer> p)
			{
                // for second column we use value
                return new SimpleObjectProperty<Integer>(p.getValue().getValue());
            }
        });

		idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, MyTuple<String, BufferedReader>>, Integer>, ObservableValue<Integer>>()
		{
			@Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<Integer, MyTuple<String, BufferedReader>>, Integer> p)
			{
                // this callback returns property for just one cell, you can't use a loop here
                // for first column we use key
                return new SimpleObjectProperty<Integer>(p.getValue().getKey());
            }
        });

		fileNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, MyTuple<String, BufferedReader>>, MyTuple<String, BufferedReader>>, ObservableValue<MyTuple<String, BufferedReader>>>()
		{
			@Override
            public ObservableValue<MyTuple<String, BufferedReader>> call(TableColumn.CellDataFeatures<Map.Entry<Integer, MyTuple<String, BufferedReader>>, MyTuple<String, BufferedReader>> p)
			{
                // for second column we use value
                return new SimpleObjectProperty<MyTuple<String, BufferedReader>>(p.getValue().getValue());
            }
        });

		varNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>()
		{
			@Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> p)
			{
                // this callback returns property for just one cell, you can't use a loop here
                // for first column we use key
                return new SimpleObjectProperty<String>(p.getValue().getKey());
            }
        });

		valColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer>, ObservableValue<Integer>>()
		{
			@Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer> p)
			{
                // for second column we use value
                return new SimpleObjectProperty<Integer>(p.getValue().getValue());
            }
        });


		init.setOnAction(e->
		{
			IStmt ex = prgs.get(programList.getSelectionModel().getSelectedIndex());
			currentPrg.setText(ex.toString());
			PrgState prg = createPrg(ex);
			IRepository rep = new Repository(prg, "log.txt");
			this.ctrl = new Controller(rep);
		});

		run1Step.setOnAction(e ->
		{
			List<PrgState> prgList=ctrl.removeCompletedPrg(ctrl.getRep().getPrgList());
			try
			{
				ctrl.OneStepGUI();
			}
			catch (IOException | MyStmtException | InterruptedException e1)
			{
				AlertBox.display("Exception", e1.getMessage());
				e1.printStackTrace();
			}
			this.out.getItems().clear();
			this.prgStates.getItems().clear();
			this.heapTable.getItems().clear();
			this.fileTable.getItems().clear();
			ArrayList<String> ids = new ArrayList<String>();
			ids.addAll(this.ctrl.getRep().getPrgIds());
			prgListId = FXCollections.observableArrayList(ids);
			this.prgStates.setItems(prgListId);
			prgList.forEach(prg->
			{
				this.out.getItems().addAll(prg.getOut().toString());
				ObservableList<Entry<Integer, Integer>> items0 = FXCollections.observableArrayList(prg.getHeap().entrySet());
				this.heapTable.setItems(items0);
				ObservableList<Entry<Integer, MyTuple<String, BufferedReader>>> items1 = FXCollections.observableArrayList(prg.getFileTable().entrySet());
				this.fileTable.setItems(items1);
			});

			this.prgStates.setOnMouseClicked(event ->
			{
				int index = this.prgStates.getSelectionModel().getSelectedIndex();
				//this.currentPrg.setText(Integer.toString(index));
				try
				{
					if (index != -1)
					{
						this.exeStack.getItems().clear();
						this.symTable.getItems().clear();

						this.exeStack.getItems().addAll(ctrl.getRep().getPrgList().get(index).getExeStack().toString());
						ObservableList<Entry<String, Integer>> items2 = FXCollections.observableArrayList(ctrl.getRep().getPrgList().get(index).getSymTable().entrySet());
						this.symTable.setItems(items2);
					}
				}
					catch(Exception ee)
					{
						AlertBox.display("Exception", ee.getMessage());
						ee.printStackTrace();
					}
			});



//				try
//				{
//					this.ctrl.getRep().logPrgStateExec(prg);
//				}
//				catch (IOException e1)
//				{
//					AlertBox.display("Exception", e1.getMessage());
//					e1.printStackTrace();
//				}

			});

		this.heapTable.getColumns().setAll(addressColumn, valueColumn);
		this.fileTable.getColumns().setAll(idColumn, fileNameColumn);
		this.symTable.getColumns().setAll(varNameColumn, valColumn);
		window.setTitle("Application");

		//programList.getChildrenUnmodifiable().add(ex1);
		Label label0 = new Label("Program List");
		Label label1 = new Label("No Of Program States");
		Label label2 = new Label("Current Program");

		VBox layout0 = new VBox(10);
		layout0.getChildren().addAll(label0, programList, label1, noOfPrograms, run1Step, init, label2, currentPrg);

		Label label3 = new Label("Heap Table");
		Label label4 = new Label("Output");
		Label label5 = new Label("File Table");

		VBox layout1 = new VBox(10);
		layout1.getChildren().addAll(label3, heapTable, label4, out, label5, fileTable);

		Label label6 = new Label("Program State");
		Label label7 = new Label("Symbol Table");
		Label label8 = new Label("Execution Stack");

		VBox layout2 = new VBox(10);
		layout2.getChildren().addAll(label6, prgStates, label7, symTable, label8, exeStack);

		HBox mainLayout = new HBox(20);
		mainLayout.getChildren().addAll(layout0, layout1, layout2);

		Scene scene = new Scene(mainLayout, 500, 500);

		window.setScene(scene);
		window.show();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}
