
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrameRent extends JFrame{

	private JFrame frameRent;
	private JTextField searchField;
	private JButton btnFrameRent;
	private JButton btnFrameShop;
	private JComboBox sort;
	private JComboBox status;
	private JLabel totalReserved;
	private JList bikeList;
	private JLabel total;
	private JLabel totalAvailable;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnChangeStatus;
	

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
		
		String[] sortListContent = {"Alphabetical", "Stock"};
		JComboBox sortComboBox = new JComboBox(sortListContent);
		sortComboBox.setToolTipText("Sort");
		GridBagConstraints gbc_sortComboBox = new GridBagConstraints();
		gbc_sortComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_sortComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_sortComboBox.gridx = 1;
		gbc_sortComboBox.gridy = 1;
		frameRent.getContentPane().add(sortComboBox, gbc_sortComboBox);
		
		JCheckBox checkBoxAvailability = new JCheckBox("List non available :");
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
		frameRent.getContentPane().add(bikeList, gbc_bikeList);
		
		JLabel totalAvailable = new JLabel(" Total Available bikes : ");
		GridBagConstraints gbc_totalAvailable = new GridBagConstraints();
		gbc_totalAvailable.anchor = GridBagConstraints.WEST;
		gbc_totalAvailable.fill = GridBagConstraints.VERTICAL;
		gbc_totalAvailable.insets = new Insets(0, 0, 5, 5);
		gbc_totalAvailable.gridx = 0;
		gbc_totalAvailable.gridy = 2;
		frameRent.getContentPane().add(totalAvailable, gbc_totalAvailable);
		
		JLabel total = new JLabel(" Total bikes : ");
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
		sortComboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(sortComboBox.getSelectedIndex() == 0)
				{
					//TODO Sort alphabetically
				}
				if(sortComboBox.getSelectedIndex() == 1)
				{
					//TODO Sort By stock amount
				}
			}
		});	
		checkBoxAvailability.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					//TODO Change bikeList to display all bikes
				}
				else
				{
					//TODO Change bikeList to display only available bikes
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
			if(e.getActionCommand() == "Add")
			{
				FrameAddBike frameAddBike = new FrameAddBike();
				frameAddBike.setVisible(true);
				//TODO FrameAddBike + update listBike, Total
			}
			if(e.getActionCommand() == "Remove")
			{
				//TODO remove from the database with id + update listBike, Total
			}
			if(e.getActionCommand() == "Change Status")
			{
				//TODO change status into database  + update listBike, Total
			}			
		}
		
	}
	
	/**
	 * Method to update the UI
	 */
	private void updateRentUi()
	{
		totalAvailable.setText(" Total Available bikes : "/* + query number of available bike*/);
		total.setText(" Total bikes : "/* + query number of bike*/);
		//TODO update listBike
	}

}
