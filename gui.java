import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Window.Type;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import javax.swing.JPanel;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Button;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractListModel;
import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;

public class gui {
	
	JFileChooser fc;

	private JFrame frmCse;
	private final Action action = new SwingAction();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frmCse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCse = new JFrame();
		frmCse.setResizable(false);
		frmCse.setType(Type.UTILITY);
		frmCse.setAlwaysOnTop(true);
		frmCse.setTitle("CSE 360 - SpellChcker - Final Project");
		frmCse.setBounds(100, 100, 330, 320);
		frmCse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCse.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open File...");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					//label_filepath.setText("Open command worked");
				} else {
					//label_filepath.setText("Open command cancelled by user.");
				}
			}
		});
		
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setSelectedIcon(null);
		mnFile.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmCse, "Created by\n"
						+ "\nA. Edwards,"
						+ "\nS. Graf,"
						+ "\nM. Kuna,"
						+ "\nD. Rydstrom.", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JPanel panel_currFile = new JPanel();
		frmCse.getContentPane().add(panel_currFile, BorderLayout.SOUTH);
		
		JLabel lbl_filePath = new JLabel("Waiting for file...");
		panel_currFile.add(lbl_filePath);
		
		JPanel panel_buttons = new JPanel();
		panel_buttons.setBorder(null);
		frmCse.getContentPane().add(panel_buttons, BorderLayout.EAST);
		panel_buttons.setLayout(new MigLayout("", "[115px]", "[22px][23px][23px][][][][][]"));
		
		JButton btn_start_spellcheck = new JButton("Start Spell Check");
		panel_buttons.add(btn_start_spellcheck, "cell 0 0,growx,aligny top");
		btn_start_spellcheck.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_buttons.add(rigidArea, "cell 0 1");
		
		JButton btn_add_to_dict = new JButton("Add to Dictionary");
		panel_buttons.add(btn_add_to_dict, "cell 0 2,growx,aligny top");
		btn_add_to_dict.setEnabled(false);
		btn_add_to_dict.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btn_quit = new JButton("Quit");
		btn_quit.setAction(action);
	
		JButton btnNewButton = new JButton("Add Remaining");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_buttons.add(btnNewButton, "cell 0 3,growx,aligny center");
		
		JButton btn_ignore = new JButton("Ignore");
		btn_ignore.setEnabled(false);
		panel_buttons.add(btn_ignore, "cell 0 4,growx,aligny top");
		
		JButton btn_ignore_r = new JButton("Ignore Remaining");
		btn_ignore_r.setEnabled(false);
		panel_buttons.add(btn_ignore_r, "cell 0 5,growx");
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		panel_buttons.add(rigidArea_1, "cell 0 6");
		btn_quit.setEnabled(true);
		panel_buttons.add(btn_quit, "cell 0 7,growx,aligny center");
		
		JPanel panel_wordList = new JPanel();
		frmCse.getContentPane().add(panel_wordList, BorderLayout.CENTER);
		
		JList list = new JList();
		list.setSize(new Dimension(170, 230));
		list.setMaximumSize(new Dimension(170, 231));
		list.setVisibleRowCount(12);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setPreferredSize(new Dimension(170, 231));
		list.setMinimumSize(new Dimension(170, 231));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"testing", "with", "fake", "random", "data"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBackground(Color.WHITE);
		panel_wordList.add(list);
		
		JPanel panelanel_wordList = new JPanel();
		//frmCse.getContentPane().add(panel_wordList, BorderLayout.CENTER);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Quit");
			putValue(SHORT_DESCRIPTION, "Closes program...");
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Quit"){
				System.exit(0);
			}
		}
	}
}
