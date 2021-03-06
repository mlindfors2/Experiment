package farm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

/**
 * Main UI class that temporarily doubles as a minor controller.
 * 
 * @author Mikael Lindfors, Max Rudander, Elin Olsson, Malin Zederfeldt,
 *         Matthias Svensson Falk
 *
 */

public class UIMain extends JFrame implements ActionListener {
	private JPanel pnlMain = new JPanel(); //will be removed
	private JPanel pnlNorth = new JPanel();
	private JPanel pnlEast = new JPanel();
	private JPanel pnlEastTabs = new JPanel();
	private JPanel pnlEastCenter = new JPanel();
	private JPanel pnlSouth = new JPanel();

	private Dimension tabDimension = new Dimension(140, 500);
	private Dimension menuDimension = new Dimension(460, 500);
	private Dimension actionDimension = new Dimension(600, 50);

	private Controller controller;
	private Board mainBoard;

	private JButton btnNextDay = new JButton("Next Day");
	private JButton btnExit = new JButton("Quit");
	private JButton btnMarket = new JButton("Market");
	private JButton btnFinance = new JButton("Finance");
	private JButton btnConsole = new JButton("Console");
	private JButton btnCrops = new JButton("Crops");
	private JButton btnBuildings = new JButton("Buildnings");
	private JButton btnFields = new JButton("Fields");

	private JLabel lblDate = new JLabel();
	private JLabel lblCash = new JLabel();
	private JLabel lblAction = new JLabel();

	private ArrayList<Commodity> items = new ArrayList<Commodity>();
	private ArrayList<Property> prop = new ArrayList<Property>();

	/**
	 * Constructs the UI
	 */
	public UIMain(Controller controller, Board mainBoard) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.controller = controller;
		this.mainBoard = mainBoard;
		pnlMain.setLayout(new BorderLayout());
		pnlNorth.setLayout(new GridLayout(1, 4));
		pnlSouth.setLayout(new BorderLayout());
		pnlEast.setLayout(new BorderLayout());
		pnlEastTabs.setLayout(new GridLayout(6, 1));
		lblCash.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheck();

		pnlNorth.add(btnNextDay);
		pnlNorth.add(lblDate);
		pnlNorth.add(lblCash);
		pnlNorth.add(btnExit);

		pnlSouth.add(lblAction);
		pnlSouth.setPreferredSize(actionDimension);

		pnlEastTabs.setPreferredSize(tabDimension);
		pnlEastTabs.add(btnMarket);
		pnlEastTabs.add(btnCrops);
		pnlEastTabs.add(btnBuildings);
		pnlEastTabs.add(btnFields);
		pnlEastTabs.add(btnFinance);
		pnlEastTabs.add(btnConsole);
		switchTab(pnlMarket());

		pnlEast.add(pnlEastTabs, BorderLayout.WEST);
		pnlEast.add(pnlEastCenter, BorderLayout.CENTER);

		pnlMain.add(pnlNorth, BorderLayout.NORTH);
		pnlMain.add(mainBoard, BorderLayout.CENTER);
		pnlMain.add(pnlEast, BorderLayout.EAST);
		pnlMain.add(pnlSouth, BorderLayout.SOUTH);

		setButtonListeners();
		add(pnlMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(true);
		setVisible(true);
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}

	/**
	 * Updates the labels in the northern panel with their current values.
	 */

	public void lblCheck() {
		lblDate.setText("Dag: " + controller.getDay());
		lblCash.setText("$ " + controller.getCash());
	}

	/**
	 * Creates and returns a panel for the side menu.
	 * 
	 * @return the new JPanel
	 */
	public JPanel pnlMarket() {
		JPanel pnlMarket = new JPanel();

		pnlMarket.setLayout(new GridLayout(Math.max(20, items.size()), 1));
		for (int i = 0; i < items.size(); i++) {
			pnlMarket.add(items.get(i).toJPanel());
		}

		pnlMarket.setPreferredSize(menuDimension);
		return pnlMarket;
	}

	/**
	 * Creates and returns a panel for the side menu. Currently a simple test case.
	 * 
	 * @return the new JPanel
	 */

	public JPanel pnlCrops() {
		JPanel pnlCrops = new JPanel();

		pnlCrops.setLayout(new GridLayout(Math.max(20, items.size()), 1));
		pnlCrops.add(new JLabel("Buy and sell crops here"));
		pnlCrops.setPreferredSize(menuDimension);
		return pnlCrops;
	}

	public JPanel pnlBuildings() {
		JPanel pnlBuildings = new JPanel();

		pnlBuildings.setLayout(new GridLayout(Math.max(20, prop.size()), 1));
		for (int i = 0; i < prop.size(); i++) {
			pnlBuildings.add(prop.get(i).toJPanel());
		}
		pnlBuildings.setPreferredSize(menuDimension);
		return pnlBuildings;
	}

	public JPanel pnlFields() {
		JPanel pnlFields = new JPanel();

		pnlFields.setLayout(new GridLayout(Math.max(20, items.size()), 1));
		pnlFields.add(new JLabel("Buy and sell fields here"));
		pnlFields.setPreferredSize(menuDimension);
		return pnlFields;
	}

	private JPanel pnlFinance() {
		JPanel pnlFinance = new JPanel();

		pnlFinance.add(new JLabel("Hello!"));
		pnlFinance.setPreferredSize(menuDimension);
		return pnlFinance;
	}

	private JPanel pnlConsole() {
		JPanel pnlConsole = new Console(this, mainBoard);
		pnlConsole.setPreferredSize(menuDimension);
		return pnlConsole;
	}

	/**
	 * An over complicated way to add listeners to the buttons.
	 */
	public void setButtonListeners() {
		JButton button;
		for (int i = 0; i < pnlNorth.getComponentCount(); i++) {
			if (pnlNorth.getComponent(i) instanceof JButton) {
				button = (JButton) pnlNorth.getComponent(i);
				button.addActionListener(this);
			}
		}
		for (int i = 0; i < pnlEastTabs.getComponentCount(); i++) {
			if (pnlEastTabs.getComponent(i) instanceof JButton) {
				button = (JButton) pnlEastTabs.getComponent(i);
				button.addActionListener(this);
			}
		}
	}

	/**
	 * Switches the panel to be shown in the side menu.
	 * 
	 * @param panel
	 *            The panel to be shown.
	 */
	public void switchTab(JPanel panel) {
		pnlEastCenter.removeAll();
		pnlEastCenter.add(panel);
		pnlEastCenter.revalidate();
	}

	/**
	 * Method that compares the prices to the available funds. Enables or disables
	 * the buy & sell buttons after stock and required funds.
	 */
	public void marketCheck() {
		for (int i = 0; i < items.size(); i++) {
			if (controller.getCash() < items.get(i).price) {
				items.get(i).btnBuy.setEnabled(false);
			} else {
				items.get(i).btnBuy.setEnabled(true);
			}
			if (items.get(i).stock > 0) {
				items.get(i).btnSell.setEnabled(true);
				;
			} else {
				items.get(i).btnSell.setEnabled(false);
			}
		}
		lblCheck();
	}

	public void propertyCheck() {
		for (int i = 0; i < prop.size(); i++) {
			if (controller.getCash() < prop.get(i).price) {
				prop.get(i).btnBuy.setEnabled(false);
			} else {
				prop.get(i).btnBuy.setEnabled(true);
			}
			if (prop.get(i).stock > 0) {
				prop.get(i).btnSell.setEnabled(true);
				;
			} else {
				prop.get(i).btnSell.setEnabled(false);
			}
		}
		lblCheck();
	}

	/**
	 * when called adds an new Commodity
	 * 
	 * @param name
	 *            the Commoditys name
	 * @param price
	 *            the Commoditys price
	 * @param stock
	 *            sets the number of Commoditys owned by player
	 */
	public void addCommodity(String name, int price, int stock, Icon icon) {
		new Commodity(name, price, stock, icon);
	}

	/**
	 * Method that changes the status of a Commodity.
	 * 
	 * @param name
	 *            - The name of the Commodity to be changed.
	 * @param price
	 *            - The new price. Set negative value for unchanged.
	 * @param stock
	 *            - The new stock. Set negative value for unchanged.
	 */
	public void editCommodity(String name, int price, int stock) {
		Commodity com;
		for (int i = 0; i < items.size(); i++) {
			com = items.get(i);
			if (com.lblComName.getText() == name) {
				if (price >= 0) {
					com.setPrice(price);
				}
				if (stock >= 0) {
					com.setStock(stock);
				}
			}
		}
	}

	public void addProperty(String name, int price, int stock, Icon icon) {
		new Property(name, price, stock, icon);
	}

	/**
	 * Triggers various methods when buttons are pressed.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnMarket) {
			switchTab(pnlMarket());
		}
		if (e.getSource() == btnCrops) {
			switchTab(pnlCrops());
		}
		if (e.getSource() == btnBuildings) {
			switchTab(pnlBuildings());
		}
		if (e.getSource() == btnFields) {
			switchTab(pnlFields());
		}
		if (e.getSource() == btnFinance) {
			switchTab(pnlFinance());
		}
		if (e.getSource() == btnConsole) {
			switchTab(pnlConsole());
		}
		if (e.getSource() == btnNextDay) {
			controller.setDay();
			controller.endTurn();
			marketCheck();

		}
		if (e.getSource() == btnExit) {
			controller.exit();
		}
	}

	/**
	 * Inner class that handles commodities for the market.
	 *
	 */
	private class Commodity implements ActionListener {
		private JLabel lblComName = new JLabel();
		private JLabel lblComImage = new JLabel();
		private JLabel lblComStock = new JLabel();
		private JLabel lblComPrice = new JLabel();
		private JButton btnBuy = new JButton("Buy!");
		private JButton btnSell = new JButton("Sell!");
		private int price;
		private int stock;

		/**
		 * Constructor for the Commodity. Also adds it to a list of Commodities for
		 * future use.
		 * 
		 * @param name
		 *            the commodity's name.
		 * @param price
		 *            the commodity's value.
		 */

		public Commodity(String name, int price, int stock, Icon icon) {
			lblComName.setText(name);
			lblComImage.setIcon(icon);
			setPrice(price);
			setStock(stock);
			btnBuy.addActionListener(this);
			btnSell.addActionListener(this);
			items.add(this);
			marketCheck();
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
			lblComPrice.setText("$" + price);
			marketCheck();
		}

		public int getStock() {

			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
			lblComStock.setText("#" + stock);
			marketCheck();

		}

		/**
		 * Creates and returns a panel containing all information about a commodity.
		 * 
		 * @return the created panel
		 */
		public JPanel toJPanel() {
			JPanel panel = new JPanel(new GridLayout(1, 6));
			panel.add(lblComName);
			panel.add(lblComImage);
			panel.add(lblComPrice);
			panel.add(lblComStock);
			panel.add(btnBuy);
			panel.add(btnSell);
			return panel;
		}

		/**
		 * Handles the buying and selling of the commodity.
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnBuy)) {
				stock++;
				setStock(stock);
				controller.setCash(-price);
				controller.buyCommodity(lblComName.getText());
			} else if (e.getSource().equals(btnSell)) {
				stock--;
				setStock(stock);
				controller.setCash(price);
				controller.sellCommodity(lblComName.getText());
			}
			marketCheck();
			lblAction.setText("You have " + stock + " " + lblComName.getText() + "(s)! \n Your remaining funds: "
					+ controller.getCash() + "$!");
		}

	}

	private class Property implements ActionListener {
		private JLabel lblPropName = new JLabel();
		private JLabel lblPropImage = new JLabel();
		private JLabel lblPropPrice = new JLabel();
		private JLabel lblPropStock = new JLabel();
		private JButton btnBuy = new JButton("Buy!");
		private JButton btnSell = new JButton("Sell!");
		private int price;
		private int stock;

		public Property(String name, int price, int stock, Icon icon) {
			lblPropName.setText(name);
			lblPropImage.setIcon(icon);
			setPrice(price);
			setStock(stock);
			btnBuy.addActionListener(this);
			btnSell.addActionListener(this);
			prop.add(this);
			propertyCheck();

		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
			lblPropPrice.setText("$" + price);
			propertyCheck();

		}

		public int getStock() {

			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
			lblPropStock.setText("#" + stock);
			propertyCheck();

		}

		public JPanel toJPanel() {
			JPanel panel = new JPanel(new GridLayout(1, 6));
			panel.add(lblPropName);
			panel.add(lblPropImage);
			panel.add(lblPropPrice);
			panel.add(lblPropStock);
			panel.add(btnBuy);
			panel.add(btnSell);
			return panel;

		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnBuy)) {
				stock++;
				setStock(stock);
				controller.setCash(-price);
				controller.buyProperty(lblPropName.getText());
			} else if (e.getSource().equals(btnSell)) {
				stock--;
				setStock(stock);
				controller.setCash(price);
				controller.sellProperty(lblPropName.getText());
			}
			propertyCheck();
			lblAction.setText("You have " + stock + " " + lblPropName.getText() + "(s)! \n Your remaining funds: "
					+ controller.getCash() + "$!");

		}

	}

}
