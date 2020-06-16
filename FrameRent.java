
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


/**
 * Class managing  the frame handling the bike management
 */
public class FrameRent extends JFrame{

	private JFrame frameRent;
	private ConnectionDB DB;
	private JTextField searchField;
	private JButton btnFrameRent;
	private JButton btnFrameShop;
	private JButton btnUpdateList;
	private JCheckBox checkBoxAvailability;
	private JList bikeList;
	private JList statusBikeList;
	private JLabel total;
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
					ConnectionDB DB = new ConnectionDB();
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
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
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
		searchField.setText("Search");
		frameRent.getContentPane().add(searchField, gbc_searchField);
		searchField.setColumns(10);
		
		JButton btnUpdateList = new JButton("Update UI");
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
		gbc_bikeList.insets = new Insets(0, 0, 0, 5);
		gbc_bikeList.gridheight = 5;
		gbc_bikeList.fill = GridBagConstraints.BOTH;
		gbc_bikeList.gridx = 1;
		gbc_bikeList.gridy = 2;
		DefaultListModel listModelBike = new DefaultListModel();
		ArrayList<String> listOfBike = ConnectionDB.listBikes();
		for(int i = 0; i < listOfBike.size(); i++)
		{
			listModelBike.addElement(listOfBike.get(i));
		}
		bikeList.setModel(listModelBike);
		frameRent.getContentPane().add(bikeList, gbc_bikeList);
		
		JList statusBikeList = new JList();
		GridBagConstraints gbc_statusBikeList = new GridBagConstraints();
		gbc_statusBikeList.gridheight = 5;
		gbc_statusBikeList.insets = new Insets(0, 0, 5, 0);
		gbc_statusBikeList.fill = GridBagConstraints.BOTH;
		gbc_statusBikeList.gridx = 2;
		gbc_statusBikeList.gridy = 2;
		DefaultListModel listModelStatus = new DefaultListModel();
		ArrayList<String> listOfBikeStatus = ConnectionDB.listBikesStatus();
		for(int i = 0; i < listOfBike.size(); i++)
		{
			listModelStatus.addElement(listOfBikeStatus.get(i));
		}
		statusBikeList.setModel(listModelStatus);
		frameRent.getContentPane().add(statusBikeList, gbc_statusBikeList);
		statusBikeList.setEnabled(false);
		
		JLabel total = new JLabel(" Total bikes : " + listOfBike.size());
		total.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_total = new GridBagConstraints();
		gbc_total.anchor = GridBagConstraints.NORTHWEST;
		gbc_total.insets = new Insets(0, 0, 5, 5);
		gbc_total.gridx = 0;
		gbc_total.gridy = 3;
		frameRent.getContentPane().add(total, gbc_total);
		
		JButton btnAdd = new JButton("Add bike");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 4;
		frameRent.getContentPane().add(btnAdd, gbc_btnAdd);
			
		JButton btnRemove = new JButton("Remove bike");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 5;
		frameRent.getContentPane().add(btnRemove, gbc_btnRemove);
		
		JButton btnChangeStatus = new JButton("Change bike Status");
		GridBagConstraints gbc_btnChangeStatus = new GridBagConstraints();
		gbc_btnChangeStatus.insets = new Insets(0, 0, 0, 6);
		gbc_btnChangeStatus.gridx = 0;
		gbc_btnChangeStatus.gridy = 6;
		frameRent.getContentPane().add(btnChangeStatus, gbc_btnChangeStatus);
		
		
		/**
		 * Add listeners to widgets
		 */
		btnFrameShop.addActionListener(new ButtonListener());
		btnAdd.addActionListener(new ButtonListener());
		btnRemove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand() == "Remove bike")
				{
					ArrayList<String> listOfBike = ConnectionDB.listBikes();
					String idToRemove = listOfBike.get(bikeList.getSelectedIndex());
					ConnectionDB.deleteBikes(idToRemove);
					bikeList.setModel(updatebikeList(ConnectionDB.listBikes()));
					statusBikeList.setModel(updatestatusBikeList(ConnectionDB.listBikesStatus()));
				}
			}
		});
		btnUpdateList.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int listBikeSize = ConnectionDB.listBikes().size();
				total.setText(" Total bikes : " + Integer.toString(listBikeSize));
				bikeList.setModel(updatebikeList(ConnectionDB.listBikes()));
				statusBikeList.setModel(updatestatusBikeList(ConnectionDB.listBikesStatus()));					
			}
			
		});
		btnChangeStatus.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ArrayList<String> listOfBike = ConnectionDB.listBikes();
				String idToChange = listOfBike.get(bikeList.getSelectedIndex());
				if(statusBikeList.getModel().getElementAt(bikeList.getSelectedIndex()) == "Available")
				{
					ConnectionDB.changeBikesStatus(idToChange, "Taken");
				}
				else
				{
					ConnectionDB.changeBikesStatus(idToChange, "Available");
				}

				bikeList.setModel(updatebikeList(ConnectionDB.listBikes()));
				statusBikeList.setModel(updatestatusBikeList(ConnectionDB.listBikesStatus()));
			}
		});
		searchField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String textSearchField = new String("");
				textSearchField = searchField.getText();
				bikeList.setModel(updatebikeList(ConnectionDB.searchBike(textSearchField)));
				statusBikeList.setModel(updatestatusBikeList(ConnectionDB.searchBikeStatus(textSearchField)));
			}
		});
		
		checkBoxAvailability.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					DefaultListModel listModelId = new DefaultListModel();
					DefaultListModel listModelStatus = new DefaultListModel();
					ArrayList<String> listOfBike = ConnectionDB.listBikesAvailable();
					ArrayList<String> listOfBikeStatus = ConnectionDB.listBikesStatusAvailable();
					for(int i = 0; i < listOfBike.size(); i++)
					{
						listModelId.addElement(listOfBike.get(i));
						listModelStatus.addElement(listOfBikeStatus.get(i));
					}
					bikeList.setModel(listModelId);
					statusBikeList.setModel(listModelStatus);
				}
				else
				{
					DefaultListModel listModelId = new DefaultListModel();
					DefaultListModel listModelStatus = new DefaultListModel();
					ArrayList<String> listOfBike = ConnectionDB.listBikes();
					ArrayList<String> listOfBikeStatus = ConnectionDB.listBikesStatus();
					for(int i = 0; i < listOfBike.size(); i++)
					{
						listModelId.addElement(listOfBike.get(i));
						listModelStatus.addElement(listOfBikeStatus.get(i));
					}
					bikeList.setModel(listModelId);
					statusBikeList.setModel(listModelStatus);
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
			if(e.getActionCommand() == "Add bike")
			{
				FrameAddBike frameAddBike = new FrameAddBike();
				frameAddBike.setVisible(true);
			}	
		}
		
	}
	
	/**
	 * Methods to update the UI
	 */
	private DefaultListModel updatebikeList(ArrayList<String> bikeList)
	{
		DefaultListModel listModel = new DefaultListModel();
		for(int i = 0; i < bikeList.size(); i++)
		{
			listModel.addElement(bikeList.get(i));
		}
		return listModel;
	}
	
	private DefaultListModel updatestatusBikeList(ArrayList<String> statusBikeList)
	{
		DefaultListModel listModel = new DefaultListModel();
		for(int i = 0; i < statusBikeList.size(); i++)
		{
			listModel.addElement(statusBikeList.get(i));
		}
		return listModel;
	}

}
