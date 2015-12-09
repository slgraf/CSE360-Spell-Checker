import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

public class gui extends JPanel implements ActionListener {
	
	/**
	 * Serialization for implementing the ActionListener
	 */
	private static final long serialVersionUID = -6995306994174311730L;
	
	protected JFileChooser fc;
	private boolean isFileOpen = false;
	private JFrame frm;
	private JLabel lbl_filePath;
	private JList<String> list;
	private JMenuBar menuBar;
	private JMenuItem m_File, m_Help, mi_OpenFile, mi_Exit, mi_About;
	private JButton btn_start, btn_add, btn_add_r, btn_ignore, btn_ignore_r, btn_quit;
	private JPanel pnl_currFile, pnl_buttons, pnl_wordList;
	private SpellChecker checker;
	private DefaultListModel listModel;
	private String filepath;
	private List input_files;



	/**
	 * Gets the main JFrame window
	 * @return the frm
	 */
	public JFrame getFrm() {
		return frm;
	}

	/**
	 * Sets the main JFrame window
	 * @param frm the frm to set
	 */
	public void setFrm(JFrame frm) {
		this.frm = frm;
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
		checker = new SpellChecker();
		checker.createDictionary(new File("dictionary.txt"));
		
		input_files = new List();
	}

	/**
	 * Initialize the main application window / frame
	 */
	private void initialize() {
		setFrm(new JFrame());
		getFrm().setResizable(false);
		getFrm().setType(Type.UTILITY);
		getFrm().setAlwaysOnTop(true);
		getFrm().setTitle("CSE 360 - SpellChecker - Final Project");
		getFrm().setBounds(100, 100, 370, 320);
		getFrm().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Menu Bar
		init_menu();
		getFrm().setJMenuBar(menuBar);
		
		// Panel Components
		init_panel();
		
		// Action Events
		init_action_command();
		init_action_listener();
		
		// Default button state
		button_state(true);
	}

	private void button_state(boolean X) {
		if (X == true){
			btn_start.setEnabled(true);
			btn_add.setEnabled(false);
			btn_add_r.setEnabled(false);
			btn_ignore.setEnabled(false);
			btn_ignore_r.setEnabled(false);
		} else {
			btn_start.setEnabled(false);
			btn_add.setEnabled(true);
			btn_add_r.setEnabled(true);
			btn_ignore.setEnabled(true);
			btn_ignore_r.setEnabled(true);
		}
	}

	private void init_panel(){
		pnl_currFile = new JPanel();
		getFrm().getContentPane().add(pnl_currFile, BorderLayout.SOUTH);
		
		lbl_filePath = new JLabel("Waiting for file...");
		pnl_currFile.add(lbl_filePath);
		
		pnl_buttons = new JPanel();
		pnl_buttons.setBorder(null);
		getFrm().getContentPane().add(pnl_buttons, BorderLayout.EAST);
		pnl_buttons.setLayout(new MigLayout("", "[115px]", "[22px][23px][23px][][][][][][]"));
		
		btn_start = new JButton("Start Spell Check");
		pnl_buttons.add(btn_start, "cell 0 0,growx,aligny center");
		btn_start.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		pnl_buttons.add(rigidArea, "cell 0 1");
		
		btn_add = new JButton("Add to Dictionary");
		pnl_buttons.add(btn_add, "cell 0 2,growx,aligny center");
		btn_add.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btn_quit = new JButton("Quit");
		
		btn_add_r = new JButton("Add Remaining");
		
		pnl_buttons.add(btn_add_r, "cell 0 3,growx,aligny center");
		
		Component rigidArea_mid = Box.createRigidArea(new Dimension(20, 5));
		pnl_buttons.add(rigidArea_mid, "cell 0 4");
		
		btn_ignore = new JButton("Ignore");

		pnl_buttons.add(btn_ignore, "cell 0 5,growx,aligny center");
		
		btn_ignore_r = new JButton("Ignore Remaining");
	
		pnl_buttons.add(btn_ignore_r, "cell 0 6,growx,aligny center");
		
		pnl_buttons.add(rigidArea, "cell 0 7");
		
		pnl_buttons.add(btn_quit, "cell 0 8,alignx center,aligny center");
		
		pnl_wordList = new JPanel();
		getFrm().getContentPane().add(pnl_wordList, BorderLayout.CENTER);
		
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setSize(new Dimension(170, 230));
		list.setMaximumSize(new Dimension(170, 231));
		list.setVisibleRowCount(12);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setPreferredSize(new Dimension(170, 231));
		list.setMinimumSize(new Dimension(170, 231));
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBackground(Color.WHITE);
		pnl_wordList.add(list);
	}

	/**
	 * Initializes the menu bar and menu items
	 */
	private void init_menu() {
		// New Menu Bar
		menuBar = new JMenuBar();

		// Menus
		m_File = new JMenu("File");
		m_Help = new JMenu("Help");

		// Menu Items
		mi_OpenFile = new JMenuItem("Open File...");
		mi_Exit = new JMenuItem("Exit");
		mi_About = new JMenuItem("About");

		JSeparator s = new JSeparator();
		
		// Adds components
		menuBar.add(m_File);
		m_File.add(mi_OpenFile);
		m_File.add(s);
		m_File.add(mi_Exit);
		
		menuBar.add(m_Help);
		m_Help.add(mi_About);
	}
	
	/**
	 * Adds action listeners to each component of the GUI
	 */
	private void init_action_listener() {
		btn_start.addActionListener(this);
		btn_add.addActionListener(this);
		btn_add_r.addActionListener(this);
		btn_ignore.addActionListener(this);
		btn_ignore_r.addActionListener(this);
		btn_quit.addActionListener(this);
		
		mi_OpenFile.addActionListener(this);
		mi_Exit.addActionListener(this);
		mi_About.addActionListener(this);
	}
	
	/**
	 * Sets the string command of actions which is used when an action occurs
	 * to retrieve the source of the event
	 */
	private void init_action_command() {
		btn_start.setActionCommand("START");
		btn_add.setActionCommand("ADD");
		btn_add_r.setActionCommand("ADD_R");
		btn_ignore.setActionCommand("IGNORE");
		btn_ignore_r.setActionCommand("IGNORE_R");
		btn_quit.setActionCommand("HALT");
		
		mi_OpenFile.setActionCommand("OPENFILE");
		mi_About.setActionCommand("ABOUT");
		mi_Exit.setActionCommand("HALT");
	}

	/**
	 * Overrides the actionPerformed listener
	 * @param e is the ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			/*
			 *  START SPELLCHECKER
			 */
			if (e.getActionCommand() == "START"){
				if (isFileOpen == false){
					displayWarning();
				} else {
					button_state(false);
					list_populate();
				}
			}

			/*
			 * ADD ITEM FROM LIST
			 */
			if (e.getActionCommand() == "ADD"){
				list_add();
				list_update();
			}
			
			/*
			 * ADD REMAINING ITEMS FROM LIST
			 */
			if (e.getActionCommand() == "ADD_R"){
				list_add_r();
				list_update();
			}
			
			/*
			 * IGNORE ITEM FROM LIST
			 */
			if (e.getActionCommand() == "IGNORE"){
				list_ignore();
				list_update();
			}
			
			/*
			 * IGNORE REMAINING ITEMS FROM LIST
			 */
			if (e.getActionCommand() == "IGNORE_R"){
				list_ignore_r();
				list_update();
			}

			/*
			 * OPEN FILE DIALOG
			 */
			if (e.getActionCommand() == "OPENFILE"){
				fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(getFrm());

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					input_files.insert(file.getName().substring(0, file.getName().length()-4));
					filepath = file.getAbsolutePath();
					
					isFileOpen = true;
					lbl_filePath.setText(filepath);
				} else {
					lbl_filePath.setText("Open command cancelled by user.");
				}
			}

			/*
			 *  ABOUT DIALOG BOX
			 */
			if (e.getActionCommand() == "ABOUT"){
				displayAbout();
			}

			/*
			 * HALT PROGRAM
			 */
			if (e.getActionCommand() == "HALT"){
				File dictionary = new File("dictionary.txt");

				try{
					if(!dictionary.exists())
						dictionary.createNewFile();
				}
				catch (IOException io){};
				try {
					PrintWriter writer = new PrintWriter(dictionary, "UTF-8");
					writer.println(checker.getDictionary());
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String infile_name = input_files.toString();
				Scanner listParser = new Scanner(infile_name);
				listParser.useDelimiter("\n");
				while(listParser.hasNext()){
					String f = listParser.next();
					File out_a = new File(f+"_added.txt");
					File out_i = new File(f+"_ignored.txt");
					try{
						if(!out_a.exists())
							out_a.createNewFile();
						if(!out_i.exists())
							out_i.createNewFile();
					}
					catch (IOException io){
						io.printStackTrace();
					}
					
					PrintWriter writer;
					try {
						writer = new PrintWriter(out_i, "UTF-8");
						writer.println(checker.getIgnore());
						writer.close();
						
						writer = new PrintWriter(out_a, "UTF-8");
						writer.println(checker.getAdded());
						writer.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				listParser.close();
				System.exit(0);
			}
	}
	
	/**
	 * Adds items from file to the JList to display for user
	 */
	private void list_populate() {
		File inputFile = new File(filepath);
		checker.createInputList(inputFile);
		String inputList = checker.getInputList();
		Scanner listParser = new Scanner(inputList);
		listParser.useDelimiter("\n");
		while(listParser.hasNext())
		{
			listModel.addElement(listParser.next());
		}
		listParser.close();
	}
	
	/**
	 * Updates the display for the user of the JList
	 * (called by JList container listener)
	 */
	private void list_update() {
		// TODO
		
	}

	private void list_ignore_r() {
		String word = "";
		for(int index = 0; index < listModel.getSize(); index++){
			word = (String) listModel.getElementAt(index);
			checker.addToIgnore(word);
		}
		listModel.removeAllElements();
	}

	private void list_ignore() {
		String word = list.getSelectedValue();
		int index = list.getSelectedIndex();
		if(index >= 0)
			listModel.remove(index);
		checker.addToIgnore(word);
	}

	private void list_add_r() {
		String word = "";
		for(int index = 0; index < listModel.getSize(); index++){
			word = (String) listModel.getElementAt(index);
			checker.addToDictionary(word);
		}
		listModel.removeAllElements();
	}

	private void list_add() {
		String word = list.getSelectedValue();
		int index = list.getSelectedIndex();
		if(index >= 0)
			listModel.remove(index);
		checker.addToDictionary(word);		
	}

	/**
	 * Show dialog box warning when no file is loaded
	 * and the user attempts to start spell checking
	 */
	private void displayWarning() {
		JOptionPane.showMessageDialog(getFrm(), "Cannot start, no file loaded!"
				+ "\n"
				+ "\nChoose File < Open File..."
				, "Please select a file", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Show program information dialog box
	 */
	private void displayAbout() {
		JOptionPane.showMessageDialog(getFrm(), "Created by\n"
				+ "\nA. Edwards,"
				+ "\nS. Graf,"
				+ "\nM. Kuna,"
				+ "\nD. Rydstrom.", "About", JOptionPane.INFORMATION_MESSAGE);
	}
}
