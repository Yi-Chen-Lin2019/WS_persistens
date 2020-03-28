package GuiLayer;


import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Models.*;
import ControlLayer.*;
import GuiLayer.CellRenderer.*;


public class OrderUI {
	
//	private LegalPersonCtr personController = new LegalPersonCtr();
	
	private double fullPrice, discountValue, discountPrice, finalPrice;
	
	private int selectedEmployee, selectedCustomer;
	
	private JTextField productSearch, customerSearch, customerId, employeeSearch, employeeId, fullPriceField, discountPriceField, finalPriceField;
	
	private JList<ProductDescription> productJList, shoppingJList;
	private JList<LegalPerson> customerJList;
	private JList<Person> employeeJList;

	private JScrollPane fullProductListScroll, shoppingCartListScroll, employeeListScroll, customerListScroll;

	private DefaultListModel<ProductDescription> productListRepresentation, cartListRepresentation;
	private DefaultListModel<LegalPerson> customerListRepresentation;
	private DefaultListModel<Person> employeeListRepresentation;
	
//	private productContainer prodCon = CONTROLLAYER.get
//	private PersonContainer persCon = CONTROLLAYER.get
	private ArrayList<ProductDescription> staticProductList = new ArrayList<>(); //= CONTROLLAYER.get
	private ArrayList<ProductDescription> productList, shoppingList;
	private ArrayList<Person> staticEmployeeList;
	private ArrayList<LegalPerson> staticCustomerList; //= CONTROLLAYER.get
	private ArrayList<LegalPerson> customerList;
	private ArrayList<Person> employeeList;

	private CellRenderer cellRenderers = CellRenderer.getInstance();
	
	public OrderUI() {
		//try {
		try {
			initializeFrame();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (db.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//}
		//catch(Exception e) {System.out.println("OrderUI constructor expection.");}
	}
	
	private void initializeFrame() throws DataAccessException, db.DataAccessException {

		productList = new ArrayList<>();
		shoppingList = new ArrayList<>();
		customerList = new ArrayList<>();
		employeeList = new ArrayList<>();

		
		JFrame frame = new JFrame("Register a Sale");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		/**Product Finder */
		productSearch = new JTextField();
		productSearch.setText("Search Product");
		productSearch.setColumns(1);
		productSearch.setBackground(new Color(255, 255, 255));
		productSearch.setForeground(Color.BLACK);
		productSearch.setHorizontalAlignment(SwingConstants.LEFT);
		productSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		productSearch.setBounds(64, 60, 200, 30);
		productSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(productSearch.getText().equals("Search Product")) {productSearch.setText(""); 
			}
			}
		});
		frame.getContentPane().add(productSearch);
		
		JButton searchBtn = new JButton("");
		searchBtn.setBounds(274, 60, 30, 30);
		frame.getContentPane().add(searchBtn);
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {searchProduct();
			}
		});
		
		fullProductListScroll = new JScrollPane();
		fullProductListScroll.setBounds(64, 100, 240, 500);
		frame.getContentPane().add(fullProductListScroll);
		
		productJList = new JList<ProductDescription>();
		productJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {productSearch.setText(productJList.getModel().getElementAt(productJList.locationToIndex(e.getPoint())).getName());
			}
		});
		productJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2){shoppingList.add(productJList.getModel().getElementAt(productJList.locationToIndex(e.getPoint())));
			updateCart();}
			}
		});
		fullProductListScroll.setViewportView(productJList);
		
		JButton addToCartBtn = new JButton("Add to Cart");
		addToCartBtn.setBounds(125, 610, 120, 20);
		frame.getContentPane().add(addToCartBtn);
		addToCartBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(productJList.getSelectedIndex()>=0) {shoppingList.add((productJList.getModel().getElementAt(productJList.getSelectedIndex()))); 
			updateCart();}
			}
		});
		
		/** Shopping Cart */
		
		shoppingCartListScroll = new JScrollPane();
		shoppingCartListScroll.setBounds(368, 100, 240, 500);
		frame.getContentPane().add(shoppingCartListScroll);
		
		shoppingJList = new JList<ProductDescription>();
		shoppingJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2){shoppingList.remove(shoppingJList.getModel().getElementAt(shoppingJList.locationToIndex(e.getPoint())));
			updateCart();}
			}
		});
		shoppingCartListScroll.setViewportView(shoppingJList);
		
		JButton removeFromCartBtn = new JButton("Remove from Cart");
		removeFromCartBtn.setBounds(430, 610, 120, 20);
		frame.getContentPane().add(removeFromCartBtn);
		removeFromCartBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(shoppingJList.getSelectedIndex()>=0) {shoppingList.remove(shoppingJList.getModel().getElementAt(shoppingJList.getSelectedIndex())); 
			int select = shoppingJList.getSelectedIndex();
			updateCart();
			shoppingJList.setSelectedIndex(select-1);}
			}
		});
		
		/** Select Employee */
		employeeListScroll = new JScrollPane();
		employeeListScroll.setBounds(672, 100, 240, 250);
		frame.getContentPane().add(employeeListScroll);
		employeeJList = new JList<Person>();
		employeeJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {customerId.setText("Employee ID: " + employeeJList.getModel().getElementAt(employeeJList.locationToIndex(e.getPoint())).getId());
			selectedEmployee = employeeJList.getModel().getElementAt(employeeJList.locationToIndex(e.getPoint())).getId();
			}
		});
		employeeListScroll.setViewportView(employeeJList);
		
		employeeSearch = new JTextField();
		employeeSearch.setText("Search Employee");
		employeeSearch.setColumns(1);
		employeeSearch.setBackground(new Color(255, 255, 255));
		employeeSearch.setForeground(Color.BLACK);
		employeeSearch.setHorizontalAlignment(SwingConstants.LEFT);
		employeeSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		employeeSearch.setBounds(672, 60, 200, 30);
		employeeSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(employeeSearch.getText().equals("Search Employee")) {employeeSearch.setText(""); 
			}
			}
		});
		frame.getContentPane().add(employeeSearch);
		
		JButton searchEmployeeBtn = new JButton("");
		searchEmployeeBtn.setBounds(882, 60, 30, 30);
		frame.getContentPane().add(searchEmployeeBtn);
//		searchEmployeeBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {searchEmployee();
//			}
//		});
		
		employeeId = new JTextField();
		employeeId.setEnabled(false);
		employeeId.setText("Select Employee");
		employeeId.setColumns(1);
		employeeId.setBackground(new Color(255, 255, 255));
		employeeId.setForeground(Color.BLACK);
		employeeId.setHorizontalAlignment(SwingConstants.CENTER);
		employeeId.setFont(new Font("Tahoma", Font.BOLD, 17));
		employeeId.setBounds(672, 360, 240, 30);
		frame.getContentPane().add(employeeId);
		
		
		/** Select Customer */
		
		customerListScroll = new JScrollPane();
		customerListScroll.setBounds(976, 100, 240, 250);
		frame.getContentPane().add(customerListScroll);
		customerJList = new JList<LegalPerson>();
		customerJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(customerJList.getModel().getElementAt(customerJList.locationToIndex(e.getPoint())) instanceof Organization) {customerId.setText("Customer ID: " + ((Organization) customerJList.getModel().getElementAt(customerJList.locationToIndex(e.getPoint()))).getOrganizationID());
			selectedCustomer = ((Organization) customerJList.getModel().getElementAt(customerJList.locationToIndex(e.getPoint()))).getOrganizationID();}
			else if(customerJList.getModel().getElementAt(customerJList.locationToIndex(e.getPoint())) instanceof Person) {
				customerId.setText("Customer ID: " + ((Person) customerJList.getModel().getElementAt(customerJList.locationToIndex(e.getPoint()))).getId());
			selectedCustomer = ((Person) customerJList.getModel().getElementAt(customerJList.locationToIndex(e.getPoint()))).getId();}
			}
		});
		customerListScroll.setViewportView(customerJList);
		
		customerSearch = new JTextField();
		customerSearch.setText("Search Customer");
		customerSearch.setColumns(1);
		customerSearch.setBackground(new Color(255, 255, 255));
		customerSearch.setForeground(Color.BLACK);
		customerSearch.setHorizontalAlignment(SwingConstants.LEFT);
		customerSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerSearch.setBounds(976, 60, 200, 30);
		customerSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {if(customerSearch.getText().equals("Search Customer")) {customerSearch.setText("");
			}
			}
		});
		frame.getContentPane().add(customerSearch);
		
		customerId = new JTextField();
		customerId.setEnabled(false);
		customerId.setText("Select Customer");
		customerId.setColumns(1);
		customerId.setBackground(new Color(255, 255, 255));
		customerId.setForeground(Color.BLACK);
		customerId.setHorizontalAlignment(SwingConstants.CENTER);
		customerId.setFont(new Font("Tahoma", Font.BOLD, 17));
		customerId.setBounds(976, 360, 240, 30);
		frame.getContentPane().add(customerId);
		
		JButton searchCustomerBtn = new JButton("");
		searchCustomerBtn.setBounds(1184, 60, 30, 30);
//		searchCustomerBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {searchCustomer(); 
//			}
//		});
		frame.getContentPane().add(searchCustomerBtn);
		
		customerId = new JTextField();
		customerId.setEnabled(false);
		customerId.setText("Select Customer");
		customerId.setColumns(1);
		customerId.setBackground(new Color(255, 255, 255));
		customerId.setForeground(Color.BLACK);
		customerId.setHorizontalAlignment(SwingConstants.CENTER);
		customerId.setFont(new Font("Tahoma", Font.BOLD, 17));
		customerId.setBounds(976, 360, 240, 30);
		frame.getContentPane().add(customerId);
		
		
/**Price Tags*/
		
		fullPriceField = new JTextField();
		fullPriceField.setEnabled(false);
		fullPriceField.setText("0.00kr");
		fullPriceField.setColumns(1);
		fullPriceField.setBackground(new Color(255, 255, 255));
		fullPriceField.setForeground(Color.BLACK);
		fullPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
		fullPriceField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		fullPriceField.setBounds(976, 490, 240, 30);
		frame.getContentPane().add(fullPriceField);
		
		discountPriceField = new JTextField();
		discountPriceField.setEnabled(false);
		discountPriceField.setText("-0.00kr");
		discountPriceField.setColumns(1);
		discountPriceField.setBackground(new Color(255, 255, 255));
		discountPriceField.setForeground(Color.BLACK);
		discountPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
		discountPriceField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		discountPriceField.setBounds(976, 530, 240, 30);
		frame.getContentPane().add(discountPriceField);
		
		finalPriceField = new JTextField();
		finalPriceField.setEnabled(false);
		finalPriceField.setText("0.00kr");
		finalPriceField.setColumns(1);
		finalPriceField.setBackground(new Color(255, 255, 255));
		finalPriceField.setForeground(Color.BLACK);
		finalPriceField.setHorizontalAlignment(SwingConstants.RIGHT);
		finalPriceField.setFont(new Font("Tahoma", Font.BOLD, 17));
		finalPriceField.setBounds(976, 570, 240, 30);
		frame.getContentPane().add(finalPriceField);
		
		
		/**Accept and Cancel Buttons*/
		
		JButton acceptBtn = new JButton("Accept");
		acceptBtn.setBounds(1058, 640, 80, 20);
//		acceptBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {updatePrices();
//			orderController.newSale(MainMenuUI.getCurrentUser().getEmployeeRole().getDepartmentId(), MainMenuUI.currentUser.getId(), selectedCustomer, finalPrice);
//			frame.dispose();}
//		});
		frame.getContentPane().add(acceptBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(1150, 640, 80, 20);
		cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {frame.dispose();
			}
		});
		frame.getContentPane().add(cancelBtn);
		
		
		
		
		
	
		
		initializeProductList();
		updateProductList();
		
		initializeCart();
		updateCart();
		
		initializeEmployeeList();
		updateEmployeeList();
		
		initializeCustomerList();
		updateCustomerList();
		
		updatePrices();
		frame.setVisible(true);
	}
	
	private void initializeProductList() {
		try {
			ProductCtr pCtr = new ProductCtr();
			for(ProductDescription i : pCtr.findAll())
			{
				staticProductList.add(i);
			}
			productList = staticProductList;
		} catch (DataAccessException | db.DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductCellRenderer cellRenderer = cellRenderers.new ProductCellRenderer();
		productJList.setCellRenderer(cellRenderer);
	}
	
	private void updateProductList() {
		if(productList!=null) {
		productListRepresentation = new DefaultListModel<ProductDescription>();
		for(ProductDescription i : productList) {
			productListRepresentation.addElement(i);
		}
		productJList.setModel(productListRepresentation);}
	}
	
	private void searchProduct() {
		String searchTxt = productSearch.getText();
		productList = new ArrayList<>();
		
			for(ProductDescription i : staticProductList) 
			{
				try {
				if(i != null && i.getName().toLowerCase().startsWith(searchTxt.toLowerCase()) || i.getBarcode()==Integer.parseInt(searchTxt))
				{productList.add(i);}
				}
				catch(Exception e){}
			}
		
		
		updateProductList();
	}
	
	private void initializeCart() {
		ProductCellRenderer cellRenderer = cellRenderers.new ProductCellRenderer();
		shoppingJList.setCellRenderer(cellRenderer);
	}
	
	private void updateCart() {
		if(shoppingList!=null) {
		cartListRepresentation = new DefaultListModel<ProductDescription>();
		for(ProductDescription i : shoppingList) {
			cartListRepresentation.addElement(i);
		}
		shoppingJList.setModel(cartListRepresentation);}
		updatePrices();
	}
	
	private void initializeEmployeeList() throws DataAccessException, db.DataAccessException {
//		for(LegalPerson i : LegalPersonCtr.findAll())
//		{
//			if(i instanceof Person) {staticEmployeeList.add(i.getPerson());}
//		}
		employeeList = staticEmployeeList;
		EmployeeCellRenderer cellRenderer = cellRenderers.new EmployeeCellRenderer();
		employeeJList.setCellRenderer(cellRenderer);
	}
	
	public void updateEmployeeList() {
		if(employeeList!=null) {
		employeeListRepresentation = new DefaultListModel<Person>();
		for(Person i : employeeList) {
			employeeListRepresentation.addElement(i);
		}
		employeeJList.setModel(employeeListRepresentation);}
	}
	
	private void initializeCustomerList() {
		customerList = staticCustomerList;
		CustomerCellRenderer cellRenderer = cellRenderers.new CustomerCellRenderer();
		customerJList.setCellRenderer(cellRenderer);
	}
	
	public void updateCustomerList() {
		if(customerList!=null) {
		customerListRepresentation = new DefaultListModel<LegalPerson>();
		for(LegalPerson i : customerList) {
			customerListRepresentation.addElement(i);
		}
		customerJList.setModel(customerListRepresentation);}
	}
	
	public void updatePrices() {
		fullPrice = 0;
		discountValue = 0;
		discountPrice = 0;
		String tempFullPrice = String.format("%.2f", fullPrice);
		String tempDiscountPrice = String.format("%.2f", discountPrice);
		String tempFinalPrice = String.format("%.2f", finalPrice);
		if(shoppingList != null) 
		{
		for(ProductDescription i : shoppingList)
		{
			fullPrice += i.getPrice();
		}}
		
//		if(fullPrice >= 1500 && customer = organization?)
//		{
//			discountValue = 0.10;
//			discountPrice = fullPrice*discountValue;
//		}
//		else if(fullPrice >= 2500 && customer = person)
//		{
//			discountPrice = -45;
//		}
		
		finalPrice = fullPrice-discountPrice;
		
		if(fullPrice==0) 
		{
			fullPriceField.setText("0.00kr");
			discountPriceField.setText("-0.00kr");
			finalPriceField.setText("0.00kr");
		}
		else 
		{
			fullPriceField.setText(tempFullPrice + "kr");
			discountPriceField.setText("-" + tempDiscountPrice + "kr");
			finalPriceField.setText(tempFinalPrice + "kr");
		}
		
	}
}
