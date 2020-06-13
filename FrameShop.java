import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class FrameShop extends JFrame {

	private JPanel contentPane;

	private DBConnection database;
	private JButton btnFrameRent;
	private JTextField searchFieldShop;
	private JList listArticle;
	
	private static final String  driver = "jdbc:mysql://localhost:3306/loireelecbikestockmanagament";//hildur.ucn.dk  jdbc:sqlserver://
	private static String  userName = "root";//dmai0919_1081946
    private static String password = "";//Password1!
    
    
    private static final String URL =  driver + userName + password;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameShop frameShop = new FrameShop();
					frameShop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public FrameShop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		/**
		 * Initialization of widgets
		 */
		JButton btnFrameRent = new JButton("Rent");
		GridBagConstraints gbc_btnFrameRent = new GridBagConstraints();
		gbc_btnFrameRent.anchor = GridBagConstraints.WEST;
		gbc_btnFrameRent.insets = new Insets(0, 0, 5, 5);
		gbc_btnFrameRent.gridx = 0;
		gbc_btnFrameRent.gridy = 0;
		contentPane.add(btnFrameRent, gbc_btnFrameRent);
		
		searchFieldShop = new JTextField();
		GridBagConstraints gbc_searchFieldShop = new GridBagConstraints();
		gbc_searchFieldShop.insets = new Insets(0, 0, 5, 5);
		gbc_searchFieldShop.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchFieldShop.gridx = 0;
		gbc_searchFieldShop.gridy = 1;
		contentPane.add(searchFieldShop, gbc_searchFieldShop);
		searchFieldShop.setColumns(10);
		
		JButton btnAdd = new JButton("Add article");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 2;
		contentPane.add(btnAdd, gbc_btnAdd);
		
		JButton btnRemove = new JButton("Remove article");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 3;
		contentPane.add(btnRemove, gbc_btnRemove);
		
		JList listArticle = new JList();
		GridBagConstraints gbc_listArticle = new GridBagConstraints();
		gbc_listArticle.gridheight = 5;
		gbc_listArticle.fill = GridBagConstraints.BOTH;
		gbc_listArticle.gridx = 1;
		gbc_listArticle.gridy = 1;
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<String> listOfArticle = database.listShop(URL, userName, password);
		for(int i = 0; i < listOfArticle.size(); i++)
		{
			listModel.addElement(listOfArticle.get(i));
		}
		listArticle.setModel(listModel);
		contentPane.add(listArticle, gbc_listArticle);
		
		/**
		 * Listeners for widget events
		 */
		btnFrameRent.addActionListener(new ButtonListener());
		btnAdd.addActionListener(new ButtonListener());
		btnRemove.addActionListener(new ButtonListener());
		searchFieldShop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String textSearchField = new String("");
				textSearchField = searchFieldShop.getText();
				database.searchShop(URL, userName, password, textSearchField);
				//System.out.println(textSearchField);
				
			}
		});
	}
	
	/**
	 * Class that will handle the events for Buttons action
	 */
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println("ActionPerformed");
			
			/**
			 * Differentiation for events is done with the text contained in the buttons
			 */
			if(e.getActionCommand() == "Rent")
			{
				FrameShop.this.dispose();
				FrameRent frameRent = new FrameRent();
				frameRent.main(null);
			}
			if(e.getActionCommand() == "Add")
			{
				FrameAddArticle frameAddArticle = new FrameAddArticle();
				frameAddArticle.setVisible(true);
			}
			if(e.getActionCommand() == "Remove")
			{
				//TODO Currently doesn't work as it would need to only get the id to be able to remove whereas it currently gets all the item information
				ArrayList<String> listOfArticle = database.listShop(URL, userName, password);
				int idToRemove = Integer.parseInt(listOfArticle.get(listArticle.getSelectedIndex()));
				database.deleteShop(URL, userName, password, idToRemove);
			}
		}
	}

}
