--1：参加了项目编号为%PNO%的项目的员工号，其中%PNO%为C语言编写的程序的输入参数；
select distinct ESSN from WORKS_ON where PNO=%PNO%;
--company_query -q 1 -p 'P1'

--2：参加了项目名为%PNAME%的员工名字，其中%PNAME%为C语言编写的程序的输入参数；
select ENAME from EMPLOYEE where ESSN in (select ESSN from WORKS_ON where PNO in (select PNO from PROJECT where PNAME=%PNAME%));
--company_query -q 2 -p 'SQL Project'

--3：在%DNAME%工作的所有工作人员的名字和地址，其中%DNAME%为C语言编写的程序的输入参数；
select ENAME,ADDRESS from EMPLOYEE where DNO in (select DNUMBER from DEPARTMENT where DNAME=%DNAME%);
--company_query -q 3 -p 'Research Department'

--4：在%DNAME%工作且工资低于%SALARY%元的员工名字和地址，其中%DNAME%和%SALARY%为C语言编写的程序的输入参数；
select ENAME,ADDRESS from EMPLOYEE where SALARY<%SALARY% and DNO in(select DNUMBER from DEPARTMENT where DNAME=%DNAME%);
--company_query -q 4 -p 2000 'Research Department'
--company_query -q 4 -p 2000 '研发部'

--5：没有参加项目编号为%PNO%的项目的员工姓名，其中%PNO%为C语言编写的程序的输入参数；
select ENAME from EMPLOYEE where ESSN not in(select ESSN from WORKS_ON where PNO=%PNO%);
--company_query -q 5 -p 'P1'

--6：由%ENAME%领导的工作人员的姓名和所在部门的名字，，其中%ENAME%为C语言编写的程序的输入参数；
select ENAME,DNAME from EMPLOYEE,DEPARTMENT where EMPLOYEE.DNO=DEPARTMENT.DNUMBER and SUPERSSN in (select ESSN from EMPLOYEE where ENAME=%ENAME%);
--company_query -q 6 -p '张红'

--7：至少参加了项目编号为%PNO1%和%PNO2%的项目的员工号，其中%PNO1%和%PNO2%为C语言编写的程序的输入参数；
select ESSN from WORKS_ON where PNO=%PNO1% and ESSN in (select ESSN from WORKS_ON where PNO=%PNO2%);
--company_query -q 7 -p 'P1' 'P2'

--8：员工平均工资低于%SALARY%元的部门名称，其中%SALARY%为C语言编写的程序的输入参数；
select DNAME from DEPARTMENT where DNUMBER in(select DNO from EMPLOYEE group by DNO having avg(SALARY)<%SALARY%);
--company_query -q 8 -p 2000

--9：至少参与了%N%个项目且工作总时间不超过%HOURS%小时的员工名字，其中%N%和%SALARY%为C语言编写的程序的输入参数；
select ENAME from EMPLOYEE where ESSN in(select ESSN from WORKS_ON group by ESSN having count(PNO)>=%N% and sum(HOURS)<=%HOURS%);
--company_query -q 9 -p 2 1000