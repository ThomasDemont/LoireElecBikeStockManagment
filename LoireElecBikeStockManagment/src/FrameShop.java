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

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class FrameShop extends JFrame {

	private JPanel contentPane;
	private JButton btnFrameRent;
	private JTextField searchFieldShop;

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

		String[] sortListContent = {"Alphabetical", "Stock"};
		JComboBox comboBoxSort = new JComboBox(sortListContent);
		GridBagConstraints gbc_comboBoxSort = new GridBagConstraints();
		gbc_comboBoxSort.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSort.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSort.gridx = 1;
		gbc_comboBoxSort.gridy = 0;
		contentPane.add(comboBoxSort, gbc_comboBoxSort);
		
		searchFieldShop = new JTextField();
		GridBagConstraints gbc_searchFieldShop = new GridBagConstraints();
		gbc_searchFieldShop.insets = new Insets(0, 0, 5, 5);
		gbc_searchFieldShop.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchFieldShop.gridx = 0;
		gbc_searchFieldShop.gridy = 1;
		contentPane.add(searchFieldShop, gbc_searchFieldShop);
		searchFieldShop.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 2;
		contentPane.add(btnAdd, gbc_btnAdd);
		
		JButton btnRemove = new JButton("Remove");
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
				//TODO query a SELECT accordingly
				//System.out.println(textSearchField);
				
			}
		});
		comboBoxSort.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if(comboBoxSort.getSelectedIndex() == 0)
				{
					//TODO Sort alphabetically
				}
				if(comboBoxSort.getSelectedIndex() == 1)
				{
					//TODO Sort By stock amount
				}
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
				
			}
		}
	}

}
