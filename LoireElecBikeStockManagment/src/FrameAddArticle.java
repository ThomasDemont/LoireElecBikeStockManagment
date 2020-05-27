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
	private JTextField textField;//TODO Change names and add or remove additional text fields
	private JTextField textField_1;
	private JTextField textField_2;

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
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 1;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 2;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
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
		textField.addActionListener(new TextFieldListener());
		textField_1.addActionListener(new TextFieldListener());
		textField_2.addActionListener(new TextFieldListener());
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
				FrameAddArticle.this.dispose();
				//TODO add to database
			}
			if(e.getActionCommand() == "Cancel")
			{ 
				FrameAddArticle.this.dispose();
			}
		}
	}
	
	/**
	 *Class that will handle the events for TextField action
	 */
	private class TextFieldListener implements ActionListener
	{
		/**
		 * Differentiation for events is done with the source object of the event
		 */
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//TODO query a SELECT accordingly + add action for each text field
			//System.out.println(textSearchField);
			if(e.getSource() == textField)
			{
				
			}
			if(e.getSource() == textField_1)
			{
				
			}
			if(e.getSource() == textField_2)
			{
				
			}
		}
	}


}