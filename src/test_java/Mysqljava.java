package test_java;

import java.util.Scanner;

import sql.AnyList;

public class Mysqljava {

	public static void Error(String s) {
		System.out.println("Error:" + s + " , Please Input like \"company_query –q <Number> -p [Parameters]\"");
	}

	public static void main(String[] arg) {
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		String s = "";
		String[] par = new String[2];
		SqlSentence[] sen = new SqlSentence[]{new SqlSentence(), new SqlSentence("select distinct ESSN from WORKS_ON where PNO=", "", ";"),
				new SqlSentence("select ENAME from EMPLOYEE where ESSN in (select ESSN from WORKS_ON where PNO in (select PNO from PROJECT where PNAME=", "", "));"),
				new SqlSentence("select ENAME,ADDRESS from EMPLOYEE where DNO in (select DNUMBER from DEPARTMENT where DNAME=", "", ");"),
				new SqlSentence("select ENAME,ADDRESS from EMPLOYEE where SALARY<", " and DNO in(select DNUMBER from DEPARTMENT where DNAME=", ");"),
				new SqlSentence("select ENAME from EMPLOYEE where ESSN not in(select ESSN from WORKS_ON where PNO=", "", ");"),
				new SqlSentence("select ENAME,DNAME from EMPLOYEE,DEPARTMENT where EMPLOYEE.DNO=DEPARTMENT.DNUMBER and SUPERSSN in (select ESSN from EMPLOYEE where ENAME=", "", ");"),
				new SqlSentence("select ESSN from WORKS_ON where PNO=", " and ESSN in (select ESSN from WORKS_ON where PNO=", ");"),
				new SqlSentence("select DNAME from DEPARTMENT where DNUMBER in(select DNO from EMPLOYEE group by DNO having avg(SALARY)<", "", ");"),
				new SqlSentence("select ENAME from EMPLOYEE where ESSN in(select ESSN from WORKS_ON group by ESSN having count(PNO)>=", " and sum(HOURS)<=", ");")};
		int num;
		while (s != "exit") {
			s = sc.next();
			if (s.equals("company_query")) {
				s = sc.next();
				if (!s.equals("-q")) {
					Error(s);
					continue;
				}
				num = sc.nextInt();
				if (num < 1 || num > 9) {
					Error(s);
					continue;
				}
				s = sc.next();
				if (!s.equals("-p")) {
					Error(s);
					continue;
				}
				if (num == 4 || num == 7 || num == 9) {
					par[0] = sc.next();
					par[1] = sc.nextLine();
				} else {
					par[0] = sc.nextLine();
					par[1] = "";
				}
				s = sen[num].getString(par);
				System.out.println(s);
				AnyList al = new AnyList(s);
				al.Output();
			} else
				Error(s);
		}
		sc.close();
	}
}