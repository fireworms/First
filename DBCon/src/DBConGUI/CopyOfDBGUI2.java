package DBConGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CopyOfDBGUI2 extends JFrame implements ActionListener, MouseListener{

	JPanel topPanel, leftPanel, botPanel;
	JScrollPane rightPanel;
	JLabel topLbl, nameLbl, ageLbl, jobLbl;
	JTextField nameField, ageField, jobField;
	JButton addBtn, cancelBtn, delBtn, exitBtn;
	JTable table;
	DefaultTableModel dtm;
	
	public CopyOfDBGUI2(){
		rightPanel = new JScrollPane();
		this.add(rightPanel);
		String[] heading = {"이름", "나이", "직업"};
		String[][] data = {{"a", "a", "a"}, {"a", "a", "a"}};
		dtm = new DefaultTableModel(data, heading);
		table = new JTable(dtm);
		rightPanel.add(table);
		setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setGUI(){
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public static void main(String[] args) {
		CopyOfDBGUI2 aa = new CopyOfDBGUI2();
		aa.setVisible(true);
	}

}
