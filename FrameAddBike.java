import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FrameAddBike extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private ConnectionDB DB;

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAddBike frameAddBike = new FrameAddBike();
					frameAddBike.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public FrameAddBike() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		textFieldID = new JTextField();
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.gridwidth = 2;
		gbc_textFieldID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 0;
		gbc_textFieldID.gridy = 0;
		textFieldID.setText("Id");
		contentPane.add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(10);
		
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
		btnCancel.addActionListener(new ButtonListener());
		btnOk.addActionListener(new ButtonListener());
		
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
				DB.addBikes(Integer.parseInt(textFieldID.getText()));
				FrameAddBike.this.dispose();
				
			}
			if(e.getActionCommand() == "Cancel")
			{ 
				FrameAddBike.this.dispose();
			}
		}
	}

}
