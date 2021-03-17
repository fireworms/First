package DBConGUI;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DBest.UseDB;

public class DBGUI extends JFrame{
	UseDB db;
	ResultSet rs;
	JScrollPane sPane;
	JTable table;
	
	public DBGUI(){
		
		String[] heading = {"�̸�", "���", "����", "����"};
		String[][] data = null;
		DefaultTableModel dtm = new DefaultTableModel(data, heading);
		table = new JTable(dtm);
		sPane = new JScrollPane(table);
		this.add(sPane);
		
		db = new UseDB();
		rs = db.select();
		try{
			while(rs.next()){
				String[] add = {rs.getString("name"), rs.getString("grade"), rs.getString("age"), rs.getString("job")};  
				dtm.addRow(add);
			}
		}catch(Exception e){
			System.out.println("��������");
		}
		pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		DBGUI aa = new DBGUI();
		aa.setVisible(true);
		
	}

}
