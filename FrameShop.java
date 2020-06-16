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


/**
 * Class managing  the frame handling the Shop management
 */
public class FrameShop extends JFrame {

	private JPanel contentPane;

	private ConnectionDB database;
	private JButton btnFrameRent;
	private JTextField searchFieldShop;
	private JList listArticle;


	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectionDB DB = new ConnectionDB();
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
		searchFieldShop.setText("Search");
		contentPane.add(searchFieldShop, gbc_searchFieldShop);
		searchFieldShop.setColumns(10);
		
		JButton btnAdd = new JButton("Add article");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 2;
		contentPane.add(btnAdd, gbc_btnAdd);
		
		JButton btnRemove = new JButton("Remove Article");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.insets = new Insets(0, 0, 5, 5);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 3;
		contentPane.add(btnRemove, gbc_btnRemove);
		
		JButton btnUpdateUI = new JButton("Refresh UI");
		GridBagConstraints gbc_btnUpdateUI = new GridBagConstraints();
		gbc_btnUpdateUI.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdateUI.gridx = 0;
		gbc_btnUpdateUI.gridy = 4;
		contentPane.add(btnUpdateUI, gbc_btnUpdateUI);

		DefaultListModel<Article> listModel = new DefaultListModel<>();
		ArrayList<Article> listOfArticle = ConnectionDB.listArticles();
		for(int i = 0; i < listOfArticle.size(); i++)
		{
			listModel.addElement(listOfArticle.get(i));
		}
		JList<Article> listArticle = new JList<>(listModel);
		GridBagConstraints gbc_listArticle = new GridBagConstraints();
		gbc_listArticle.gridheight = 5;
		gbc_listArticle.fill = GridBagConstraints.BOTH;
		gbc_listArticle.gridx = 1;
		gbc_listArticle.gridy = 1;
		
		contentPane.add(listArticle, gbc_listArticle);
		
		/**
		 * Listeners for widget events
		 */
		btnFrameRent.addActionListener(new ButtonListener());
		btnAdd.addActionListener(new ButtonListener());
		btnRemove.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
					Article articleToRemove;
					articleToRemove = listOfArticle.get(listArticle.getSelectedIndex());
					int idToRemove = articleToRemove.idArticle;
					ConnectionDB.deleteArticle(idToRemove);
					DefaultListModel listModel = new DefaultListModel();
					ArrayList<Article> listOfArticleAfterRemoval = ConnectionDB.listArticles();
					for(int i = 0; i < listOfArticleAfterRemoval.size(); i++)
					{
						listModel.addElement(listOfArticleAfterRemoval.get(i));
					}
					listArticle.setModel(listModel);
			}
		});
		btnUpdateUI.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
					DefaultListModel listModel = new DefaultListModel();
					ArrayList<Article> listOfArticleAfterRemoval = ConnectionDB.listArticles();
					for(int i = 0; i < listOfArticleAfterRemoval.size(); i++)
					{
						listModel.addElement(listOfArticleAfterRemoval.get(i));
					}
					listArticle.setModel(listModel);
			}	
		});
		searchFieldShop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DefaultListModel listModel = new DefaultListModel();
				String textSearchField = new String("");
				textSearchField = searchFieldShop.getText();
				ArrayList<Article> listOfArticleSearched = ConnectionDB.searchArticle(textSearchField);
				for(int i = 0; i < listOfArticleSearched.size(); i++)
				{
					listModel.addElement(listOfArticleSearched.get(i));
					//listArticle.setModel(updateArticleList(ConnectionDB.searchArticle(textSearchField)));
				}
				listArticle.setModel(listModel);
			}
		});
	}
	
	private DefaultListModel updateArticleList(ArrayList<String> ArticleList)
	{
		DefaultListModel listModel = new DefaultListModel();
		for(int i = 0; i < ArticleList.size(); i++)
		{
			listModel.addElement(ArticleList.get(i));
		}
		return listModel;
	}
	
	/**
	 * Class that will handle the events for Buttons action
	 */
	private class ButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			/**
			 * Differentiation for events is done with the text contained in the buttons
			 */
			if(e.getActionCommand() == "Rent")
			{
				FrameShop.this.dispose();
				FrameRent frameRent = new FrameRent();
				frameRent.main(null);
			}
			if(e.getActionCommand() == "Add article")
			{
				FrameAddArticle frameAddArticle = new FrameAddArticle();
				frameAddArticle.setVisible(true);
			}
		}
	}

}
