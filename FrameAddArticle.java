import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrameAddArticle extends JFrame {

	private JPanel contentPane;
	DBConnection database;
	private JTextField textFieldIdArticle;
	private JTextField textFieldNameArticle;
	private JTextField textFieldQuantityArticle;
	
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
					FrameAddArticle frameAddArticle = new FrameAddArticle();
					frameAddArticle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public FrameAddArticle() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		/**
		 * Initialization of widgets
		 */
		textFieldIdArticle = new JTextField();
		GridBagConstraints gbc_textFieldIdArticle = new GridBagConstraints();
		gbc_textFieldIdArticle.gridwidth = 2;
		gbc_textFieldIdArticle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIdArticle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIdArticle.gridx = 0;
		gbc_textFieldIdArticle.gridy = 0;
		contentPane.add(textFieldIdArticle, gbc_textFieldIdArticle);
		textFieldIdArticle.setColumns(10);
		
		textFieldNameArticle = new JTextField();
		GridBagConstraints gbc_textFieldNameArticle = new GridBagConstraints();
		gbc_textFieldNameArticle.gridwidth = 2;
		gbc_textFieldNameArticle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNameArticle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNameArticle.gridx = 0;
		gbc_textFieldNameArticle.gridy = 1;
		contentPane.add(textFieldNameArticle, gbc_textFieldNameArticle);
		textFieldNameArticle.setColumns(10);
		
		textFieldQuantityArticle = new JTextField();
		GridBagConstraints gbc_textFieldQuantityArticle = new GridBagConstraints();
		gbc_textFieldQuantityArticle.gridwidth = 2;
		gbc_textFieldQuantityArticle.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldQuantityArticle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldQuantityArticle.gridx = 0;
		gbc_textFieldQuantityArticle.gridy = 2;
		contentPane.add(textFieldQuantityArticle, gbc_textFieldQuantityArticle);
		textFieldQuantityArticle.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 3;
		contentPane.add(btnOk, gbc_btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 3;
		contentPane.add(btnCancel, gbc_btnCancel);

		/**
		 * Add listeners to widgets
		 */
		btnOk.addActionListener(new ButtonListener());
		btnCancel.addActionListener(new ButtonListener());
	}
	
	/**
	 *Class that will handle the events for Buttons action
	 */
	private class ButtonListener implements ActionListener
	{
		/**
		 * Differentiation for events is done with the text contained in the buttons
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand() == "OK")
			{
				Integer iDArticle = Integer.parseInt(textFieldIdArticle.getText());
				String nameArticle = textFieldNameArticle.getText();
				Integer quantityArticle = Integer.parseInt(textFieldQuantityArticle.getText());
				
				database.addArticle(URL, userName, password, iDArticle, nameArticle, quantityArticle);
				FrameAddArticle.this.dispose();
			}
			if(e.getActionCommand() == "Cancel")
			{ 
				FrameAddArticle.this.dispose();
			}
		}
	}

}
