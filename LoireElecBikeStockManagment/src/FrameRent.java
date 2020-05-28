
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;

public class FrameRent extends JFrame{

	private JFrame frameRent;
	private DBConnection database;
	private JTextField searchField;
	private JButton btnFrameRent;
	private JButton btnFrameShop;
	private JButton btnUpdateList;
	private JCheckBox checkBoxAvailability;
	private JList bikeList;
	private JLabel total;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnChangeStatus;
	
	private static final String  driver = "jdbc:sqlserver://localhost:1433";;
    private static final String  databaseName = ";databaseName=Bikes";
	private static String  userName = ";user=sa";
    private static String password = ";password=azerty";
    
    
    private static final String URL =  driver + databaseName + userName + password;
	

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRent window = new FrameRent();
					window.frameRent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application
	 */
	public FrameRent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		
		frameRent = new JFrame();
		frameRent.setBounds(100, 100, 1000, 600);
		frameRent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		frameRent.getContentPane().setLayout(gridBagLayout);
		frameRent.setTitle("LoireElecBikeStockManagment - Rent");

		/**
		 * Initialization of widgets
		 */
		JButton btnFrameShop = new JButton("Shop");
		GridBagConstraints gbc_btnFrameShop = new GridBagConstraints();
		gbc_btnFrameShop.anchor = GridBagConstraints.WEST;
		gbc_btnFrameShop.insets = new Insets(0, 0, 5, 5);
		gbc_btnFrameShop.gridx = 0;
		gbc_btnFrameShop.gridy = 0;
		frameRent.getContentPane().add(btnFrameShop, gbc_btnFrameShop);
		
		searchField = new JTextField();
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 1;
		frameRent.getContentPane().add(searchField, gbc_searchField);
		searchField.setColumns(10);
		
		JButton btnUpdateList = new JButton("Update List");
		GridBagConstraints gbc_btnUpdateList = new GridBagConstraints();
		gbc_btnUpdateList.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdateList.gridx = 1;
		gbc_btnUpdateList.gridy = 1;
		frameRent.getContentPane().add(btnUpdateList, gbc_btnUpdateList);
		
		JCheckBox checkBoxAvailability = new JCheckBox("List only available :");
		GridBagConstraints gbc_checkBoxAvailability = new GridBagConstraints();
		gbc_checkBoxAvailability.insets = new Insets(0, 0, 5, 0);
		gbc_checkBoxAvailability.gridx = 2;
		gbc_checkBoxAvailability.gridy = 1;
		frameRent.getContentPane().add(checkBoxAvailability, gbc_checkBoxAvailability);
			
		JList bikeList = new JList();
		GridBagConstraints gbc_bikeList = new GridBagConstraints();
		gbc_bikeList.gridwidth = 2;
		gbc_bikeList.gridheight = 5;
		gbc_bikeList.fill = GridBagConstraints.BOTH;
		gbc_bikeList.gridx = 1;
		gbc_bikeList.gridy = 2;
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> listOfBike = database.listBikes(URL, userName, password);
		for(int i = 0; i < listOfBike.size(); i++)
		{
			listModel.addElement(listOfBike.get(i));
		}
		bikeList.setModel(listModel);
		frameRent.getContentPane().add(bikeList, gbc_bikeList);
		
		JLabel total = new JLabel(" Total bikes : " + database.countBikes(URL, userName, password));
		total.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_total = new GridBagConstraints();
		gbc_total.anchor = GridBagConstraints.NORTHWEST;
		gbc_total.insets = new Insets(0, 0, 5, 5);
		gbc_total.gridx = 0;
		gbc_total.gridy = 3;
		frameRent.getContentPane().add(total, gbc_total);
		
		JButton btnAdd = new JButton("Add bike");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.anchor = GridBagConstraints.WEST;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 4;
		frameRent.getContentPane().add(btnAdd, gbc_btnAdd);
			
		JButton btnRemove = new JButton("Remove bike");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.anchor = GridBagConstraints.WEST;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 5;
		frameRent.getContentPane().add(btnRemove, gbc_btnRemove);
		
		JButton btnChangeStatus = new JButton("Change bike Status");
		GridBagConstraints gbc_btnChangeStatus = new GridBagConstraints();
		gbc_btnChangeStatus.anchor = GridBagConstraints.WEST;
		gbc_btnChangeStatus.insets = new Insets(0, 0, 0, 6);
		gbc_btnChangeStatus.gridx = 0;
		gbc_btnChangeStatus.gridy = 6;
		frameRent.getContentPane().add(btnChangeStatus, gbc_btnChangeStatus);
		
		
		/**
		 * Add listeners to widgets
		 */
		btnFrameShop.addActionListener(new ButtonListener());
		btnAdd.addActionListener(new ButtonListener());
		btnRemove.addActionListener(new ButtonListener());
		btnChangeStatus.addActionListener(new ButtonListener());		
		searchField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String textSearchField = new String("");
				textSearchField = searchField.getText();
				//TODO query a SELECT accordingly
				//System.out.println(textSearchField);
				
			}
		});		
		checkBoxAvailability.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					DefaultListModel listModel = new DefaultListModel();
					ArrayList<String> listOfBike = database.listBikes(URL, userName, password);
					for(int i = 0; i < listOfBike.size(); i++)
					{
						listModel.addElement(listOfBike.get(i));
					}
					bikeList.setModel(listModel);
				}
				else
				{
					DefaultListModel listModel = new DefaultListModel();
					ArrayList<String> listOfBike = database.listBikesA(URL, userName, password);
					for(int i = 0; i < listOfBike.size(); i++)
					{
						listModel.addElement(listOfBike.get(i));
					}
					bikeList.setModel(listModel);
				}
			}
		});
		
	}
	
	/**
	 *Class that will handle the events for Buttons action
	 */
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * Differentiation for events is done with the text contained in the buttons
			 */
			if(e.getActionCommand() == "Shop")
			{
				frameRent.dispose();
				FrameShop frameShop = new FrameShop();
				frameShop.setVisible(true);	
			}
			if(e.getActionCommand() == "Update List")
			{
				updateRentUi();
			}
			if(e.getActionCommand() == "Add")
			{
				FrameAddBike frameAddBike = new FrameAddBike();
				frameAddBike.setVisible(true);
			}
			if(e.getActionCommand() == "Remove")
			{
				ArrayList<String> listOfBike = database.listBikes(URL, userName, password);
				int idToRemove = Integer.parseInt(listOfBike.get(bikeList.getSelectedIndex()));
				database.deleteBikes(URL, userName, password, idToRemove);
			}
			if(e.getActionCommand() == "Change Status")
			{
				//TODO change status into database
			}			
		}
		
	}
	
	/**
	 * Method to update the UI
	 */
	private void updateRentUi()
	{
		total.setText(" Total bikes : " + database.countBikes(URL, userName, password));
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> listOfBike = database.listBikes(URL, userName, password);
		for(int i = 0; i < listOfBike.size(); i++)
		{
			listModel.addElement(listOfBike.get(i));
		}
		bikeList.setModel(listModel);
	}

}
