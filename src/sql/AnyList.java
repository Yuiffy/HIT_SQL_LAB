package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnyList {
	private List<String> list = new ArrayList<String>();

	public AnyList() {

	}

	public List<String> getList() {
		return this.list;
	}

	public void Output() {
		for (int i = 0; i < this.list.size(); i++) {
			System.out.println(this.list.get(i));

		}
	}

	public AnyList(String s) {
		SqlCon sqlC = new SqlCon();
		Connection conn = sqlC.getConn();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(s);
			// /"select * from book where Author="+ authorName
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (0 == columnCount)
				System.out.println("Nothing to output.");
			this.list = new ArrayList<String>();
			String temp = "";
			for (int i = 0; i < columnCount; i++)
				temp += rsmd.getColumnName(i + 1) + "\t";
			this.list.add(temp);
			while (rs.next()) {
				temp = "";
				for (int i = 0; i < columnCount; i++)
					temp += rs.getString(i + 1) + "\t";
				this.list.add(temp);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		}
	}
}
