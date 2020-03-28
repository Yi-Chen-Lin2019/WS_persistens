package GuiLayer;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Models.*;

public class CellRenderer {
	
	private static CellRenderer cellRenderer;
	
	private CellRenderer() {}
	
	public static CellRenderer getInstance()
    {
        if (cellRenderer==null)
        {
        	cellRenderer = new CellRenderer();
        }
        return cellRenderer;
    }
	
	public class ProductCellRenderer implements ListCellRenderer<ProductDescription> {

		private DefaultListCellRenderer dlcr;
		
		@Override
		public Component getListCellRendererComponent(JList<? extends ProductDescription> list, ProductDescription value, int index, boolean isSelected, boolean cellHasFocus) {
			dlcr = new DefaultListCellRenderer();
			dlcr.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {System.out.print("click"+value.getName());
				}
				});
			String price = String.format("%.2f", value.getPrice());
			String textToShow = "ID: " + value.getBarcode()  +"     "+ value.getName()+ "     " + price + "kr";
			return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
		}
	}
	//ID: 21314     T-Shirt     75kr
	
	public class EmployeeCellRenderer implements ListCellRenderer<Person> {

		private DefaultListCellRenderer dlcr;
		
		@Override
		public Component getListCellRendererComponent(JList<? extends Person> list, Person value, int index, boolean isSelected, boolean cellHasFocus) {
			dlcr = new DefaultListCellRenderer();
			dlcr.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {System.out.print("click");
				}
				});
			String textToShow = "ID: " + value.getId() + "     " + value.getfName() + "  " + value.getfName() + "     " + value.getEmployeeRole().getTitle();
			return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
		}
	}
	//ID: 34     Smith  John     Marketing Management
	
	public class CustomerCellRenderer implements ListCellRenderer<LegalPerson> {

		private DefaultListCellRenderer dlcr;
		
		@Override
		public Component getListCellRendererComponent(JList<? extends LegalPerson> list, LegalPerson value, int index, boolean isSelected, boolean cellHasFocus) {
			dlcr = new DefaultListCellRenderer();
			dlcr.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {System.out.print("click");
				}
				});
			String textToShow = "";
			if(value instanceof Organization)
			{
				textToShow = ((Organization) value).getOrgnizationName() + "     Phone: " + value.getPhoneNumber();
			}
			
			else if(value instanceof Person)
			{
				textToShow = ((Person) value).getfName() + "  " + ((Person) value).getlName() + "     Phone: " + value.getPhoneNumber();
			}
			return dlcr.getListCellRendererComponent(list, textToShow, index, isSelected, cellHasFocus);
		}
	}
	//Smith John    Phone: 675756774
	//ClubBanana     Phone: 64674777
}