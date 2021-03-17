package DBConGUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DBest.UseDB;

public class DBGUI2 extends JFrame implements ActionListener{

	private JPanel topPanel, leftPanel, innerLeftPanel, botPanel;
	private JScrollPane rightPanel;
	private JLabel topLbl, nameLbl, ageLbl, jobLbl;
	private JTextField nameField, ageField, jobField;
	private JButton addBtn, cancelBtn, removeBtn, exitBtn;
	private JTable table;
	private DefaultTableModel dtm;
	private boolean tableSelected;
	private int index;
	private UseDB db;
	private String tblName = "custmer01"; 
	
	public DBGUI2(){
		setGUI();
		setTableData();
		setListeners();
		connectDB();
	}
	
	private void connectDB(){
		db = new UseDB();
	}
	
	private void setListeners(){
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				tableSelected = true;
				index = table.getSelectedRow();
				nameField.setText(table.getValueAt(index, 0).toString());
				ageField.setText(table.getValueAt(index, 1).toString());
				jobField.setText(table.getValueAt(index, 2).toString());
				addBtn.setText("수정");
			}
		});
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
					nameField.requestFocus();
				} else if (ageField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
					ageField.requestFocus();
				} else if (jobField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "값을 입력 해주세요");
					jobField.requestFocus();
				} else {
					if (addBtn.getText().equals("입력")) {
						String[] add = { nameField.getText(),
								ageField.getText(), jobField.getText() };
						String indexString = JOptionPane.showInputDialog(null,
								"몇번째줄에 추가할까", "줄추가", 0);
						if (indexString != null && indexString != "") {
							try {
								index = Integer.parseInt(indexString) - 1;
								if (index > dtm.getRowCount()) {
									index = dtm.getRowCount();
								}
								dtm.insertRow(index, add);
								clearAll();
							} catch (NumberFormatException ee) {
								JOptionPane.showMessageDialog(null, "잘못된 입력");
							}
						}
					} else {
						String[] add = { nameField.getText(),
								ageField.getText(), jobField.getText() };
						for (int i = 0; i < add.length; i++) {
							dtm.setValueAt(add[i], index, i);
						}
						clearAll();
					}
				}
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAll();
			}
		});
		
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableSelected) {
					try {
						index = table.getSelectedRow();
						String name = dtm.getValueAt(index, 0).toString();
						System.out.println(name);
						db.remove(tblName, name);
						setTableData();
					} catch (Exception ee) {
						JOptionPane.showMessageDialog(null, "잘못된 입력");
					}
					clearAll();
				} else {
					String indexString = JOptionPane.showInputDialog(null,
							"몇번째줄 삭제할까", "줄삭제", 0);
					if (indexString != null && indexString != "") {
						try {
							index = Integer.parseInt(indexString) - 1;
							String name = dtm.getValueAt(index, 0).toString();
							System.out.println(name);
							db.remove(tblName, name);
							setTableData();
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, "잘못된 입력");
						}
					}
				}
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void clearAll() {
		table.clearSelection();
		nameField.setText(null);
		ageField.setText(null);
		jobField.setText(null);
		addBtn.setText("입력");
		tableSelected = false;
	}
	
	private void setTableData(){
		UseDB db = new UseDB();
		ResultSet rs = db.select(tblName);
		try{
			while(rs.next()){
				String[] add = {rs.getString("name"), rs.getString("age"), rs.getString("job")};  
				dtm.addRow(add);
			}
		}catch(Exception e){
			System.out.println("오류난듯");
		}
	}
	
	private void setGUI(){
		topPanel = new JPanel(); 
		this.add("North", topPanel);
		leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add("West", leftPanel);
		innerLeftPanel = new JPanel();
		innerLeftPanel.setLayout(new GridLayout(0, 2, 10, 10));
		leftPanel.add(innerLeftPanel);
		botPanel = new JPanel();
		this.add("South", botPanel);
		
		topLbl = new JLabel("제목");
		topPanel.add(topLbl);
		
		nameLbl = new JLabel("이름");
		nameField = new JTextField();
		
		innerLeftPanel.add(nameLbl);
		innerLeftPanel.add(nameField);
		ageLbl = new JLabel("나이");
		ageField = new JTextField();
		innerLeftPanel.add(ageLbl);
		innerLeftPanel.add(ageField);
		jobLbl = new JLabel("직업");
		jobField = new JTextField();
		innerLeftPanel.add(jobLbl);
		innerLeftPanel.add(jobField);
		
		addBtn = new JButton("입력");
		
		innerLeftPanel.add(addBtn);
		cancelBtn = new JButton("취소");
		innerLeftPanel.add(cancelBtn);
		
		String[] heading = {"이름", "나이", "직업"};
		String[][] data = null;
		dtm = new DefaultTableModel(data, heading);
		table = new JTable(dtm);
		rightPanel = new JScrollPane(table);
		this.add("Center", rightPanel);
		
		removeBtn = new JButton("삭제");
		botPanel.add(removeBtn);
		exitBtn = new JButton("종료");
		botPanel.add(exitBtn);
		
		setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public static void main(String[] args) {
		DBGUI2 aa = new DBGUI2();
		aa.setVisible(true);
	}

}
