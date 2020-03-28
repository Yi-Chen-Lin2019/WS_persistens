package GuiLayer;


/*
import Models;
import ControllerLayer;
*/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuUI {

	private JFrame frame;
	
	private JButton orderButton;
	
	private JButton order2Button;
	private JButton order3Button;
	
	private JButton usersButton;
	private JButton productsButton;

	public MainMenuUI() {
		initializeFrame();
	}


	private void initializeFrame() {
		frame = new JFrame("Main Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		orderButtons();
		
		orderButton = new JButton("New Order");
		orderButton.setFont(new Font("Arial", Font.PLAIN, 25));
		orderButton.setBounds(140, 260, 250, 100);
		orderButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {order2Button.setVisible(true); order3Button.setVisible(true);
			}
		});
		frame.getContentPane().add(orderButton);
		
		usersButton = new JButton("Users");
		usersButton.setFont(new Font("Arial", Font.PLAIN, 25));
		usersButton.setBounds(530, 260, 250, 100);
		usersButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {order2Button.setVisible(false); order3Button.setVisible(false);
			}
		});
		frame.getContentPane().add(usersButton);
		
		productsButton = new JButton("Products");
		productsButton.setFont(new Font("Arial", Font.PLAIN, 25));
		productsButton.setBounds(920, 260, 250, 100);
		productsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {order2Button.setVisible(false); order3Button.setVisible(false);
			}
		});
		frame.getContentPane().add(productsButton);
		
		frame.setVisible(true);
	}
	
	private void orderButtons() {
		order2Button = new JButton("New Sale");
		order2Button.setFont(new Font("Arial", Font.PLAIN, 25));
		order2Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {OrderUI orderWindow = new OrderUI();
			}
		});
		order2Button.setBounds(165, 380, 200, 80);
		frame.getContentPane().add(order2Button);
		order2Button.setVisible(false);
		
		order3Button = new JButton("New Rent");
		order3Button.setFont(new Font("Arial", Font.PLAIN, 25));
		order3Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		order3Button.setBounds(165, 470, 200, 80);
		frame.getContentPane().add(order3Button);
		order3Button.setVisible(false);
	}

}
