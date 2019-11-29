package Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Conn {
	static String driverClass="oracle.jdbc.driver.OracleDriver";
	static String url="jdbc:oracle:thin:@139.196.161.28:1521:orcl";
	static String user="C##ANIMALSHELTER"; //sys as sysdba
	static String password="123456";
	static boolean statusFlag=false;
	
	static Connection conn=null;
	public static Connection getconn() {//获得数据库链接
		try {
			//首先建立驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//驱动成功后进行连接
			conn=DriverManager.getConnection(url, user, password);
			System.out.println("连接成功");
			statusFlag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public boolean getConnStatus() {//获取链接状态
		return statusFlag;
	}
	
	public boolean UserLoginCheck(String Account,String Password) {//用户登录
		String sql = "{call \"CheckUser\"(?,?,?)}";
		CallableStatement call = null;
		boolean res=false;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, Account);
			call.setString(2, Password);
			call.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(3);
			System.out.println(resFlag);
			if(resFlag==1)
				res=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean AdminLoginCheck(String Account,String Password) {//管理员登录
		String sql = "{call \"CheckAdmin\"(?,?,?)}";
		CallableStatement call = null;
		boolean res=false;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, Account);
			call.setString(2, Password);
			call.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(3);
			System.out.println(resFlag);
			if(resFlag==1)
				res=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int UserRegistrationCheck(String inputUserID,String inputUserName,String inputUserPassword,//注册检查
			String inputUserEmail,String inputUserPhone,String inputUserShelterID) {
		String sql = "{call \"UserRegistration\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inputUserID);
			call.setString(2, inputUserName);
			call.setString(3, inputUserPassword);
			call.setString(4, inputUserEmail);
			call.setString(5, inputUserPhone);
			call.setString(6, inputUserShelterID);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public Object[][] getUserInfTable() throws SQLException {//表格获取user信息
		Statement st=conn.createStatement();
        String sql="SELECT \"UserID\",\"UserName\",\"Email\",\"PhoneNo\",\"ShelterID\" \n" + 
        		"FROM \"User\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        Object[] columnNames = {"UserID","UserName", "Email", "PhoneNo","Shelter ID"};
        Vector<Vector<String>> resVector=new Vector<Vector<String>>();
        while(rs.next()) { //循环遍历结果集
            String UserID=rs.getString("UserID");
            String UserName=rs.getString("UserName");
            String Email=rs.getString("Email");
            String PhoneNo=rs.getString("PhoneNo");
            String ShelterID=rs.getString("SHelterID");
        Vector<String> temp = new Vector<String>();
        temp.add(UserID);
        temp.add(UserName);
        temp.add(Email);
        temp.add(PhoneNo);
        temp.add(ShelterID);
        resVector.add(temp);
        }
        Object[][] resObjects=new Object[resVector.size()+1][resVector.elementAt(0).size()];
        for(int i=0;i<resVector.elementAt(0).size();i++) {
        	resObjects[0][i]=columnNames[i];
        }
        for(int i=1;i<resVector.size()+1;i++) {
        	for(int j=0;j<resVector.elementAt(0).size();j++) {
        		resObjects[i][j]=resVector.elementAt(i-1).elementAt(j);
        	}
        }
        return resObjects;
	}

	public Object[][] getAnimalInfTable() throws SQLException {//表格获取animal信息
		Statement st=conn.createStatement();
        String sql="SELECT \"AnimalID\",\"AnimalNo\",\"AnimalName\",\"AnimalType\",\"AnimalSex\",\"AnimalAge\",\"ShelterID\"\n " + 
        		"FROM \"Animal\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        Object[] columnNames = {"AnimalID", "AnimalNo", "AnimalName", "AnimalType", "AnimalSex","AnimalAge","ShelterID"};
        Vector<Vector<String>> resVector=new Vector<Vector<String>>();
        while(rs.next()) { //循环遍历结果集
        String AnimalID=rs.getString("AnimalID");
        String AnimalNo=rs.getString("AnimalNo");
        String AnimalName=rs.getString("AnimalName");
        String AnimalType=rs.getString("AnimalType");
        String AnimalSex=rs.getString("AnimalSex");
        int AnimalAge=rs.getInt("AnimalAge");
        String ShelterID=rs.getString("ShelterID");
        Vector<String> temp = new Vector<String>();
        temp.add(AnimalID);
        temp.add(AnimalNo);
        temp.add(AnimalName);
        temp.add(AnimalType);
        temp.add(AnimalSex);
        temp.add(AnimalAge+"");
        temp.add(ShelterID);
        resVector.add(temp);
        }
        Object[][] resObjects=new Object[resVector.size()+1][resVector.elementAt(0).size()];
        for(int i=0;i<resVector.elementAt(0).size();i++) {
        	resObjects[0][i]=columnNames[i];
        }
        for(int i=1;i<resVector.size()+1;i++) {
        	for(int j=0;j<resVector.elementAt(0).size();j++) {
        		resObjects[i][j]=resVector.elementAt(i-1).elementAt(j);
        	}
        }
        return resObjects;
	}
	
	public Object[][] getShelterInfTable() throws SQLException {//表格获取shelter信息
		Statement st=conn.createStatement();
        String sql="SELECT * from \"Shelter\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        Object[] columnNames = {"ShelterID", "ShelterName", "ShelterAddress", "ShelterZipCode", "ShelterAllRoomNum","ShelterRemainingRoomNum"};
        Vector<Vector<String>> resVector=new Vector<Vector<String>>();
        while(rs.next()) { //循环遍历结果集
        String ShelterID=rs.getString("ShelterID");
        String ShelterName=rs.getString("ShelterName");
        String ShelterAddress=rs.getString("ShelterAddress");
        String ShelterZipCode=rs.getString("ShelterZipCode");
        int ShelterAllRoomNum=rs.getInt("ShelterAllRoomNum");
        int ShelterRemainingRoomNum=rs.getInt("ShelterRemainingRoomNum");
        Vector<String> temp = new Vector<String>();
        temp.add(ShelterID);
        temp.add(ShelterName);
        temp.add(ShelterAddress);
        temp.add(ShelterZipCode);
        temp.add(ShelterAllRoomNum+"");
        temp.add(ShelterRemainingRoomNum+"");
        resVector.add(temp);
        }
        Object[][] resObjects=new Object[resVector.size()+1][resVector.elementAt(0).size()];
        for(int i=0;i<resVector.elementAt(0).size();i++) {
        	resObjects[0][i]=columnNames[i];
        }
        for(int i=1;i<resVector.size()+1;i++) {
        	for(int j=0;j<resVector.elementAt(0).size();j++) {
        		resObjects[i][j]=resVector.elementAt(i-1).elementAt(j);
        	}
        }
        return resObjects;
	}
	
	public Object[][] getHealthInfTable() throws SQLException {//表格获取Health信息
		Statement st=conn.createStatement();
        String sql="SELECT * from \"Health\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        Object[] columnNames = {"HealthID","AnimalID","UserID","HealthInformation","CheckDate","Note"};
        Vector<Vector<String>> resVector=new Vector<Vector<String>>();
        while(rs.next()) { //循环遍历结果集
        String HealthID=rs.getString("HealthID");
        String AnimalID=rs.getString("AnimalID");
        String UserID=rs.getString("UserID");
        String HealthInformation=rs.getString("HealthInformation");
        String CheckDate=rs.getString("CheckDate");
        String Note=rs.getString("Note");
        Vector<String> temp = new Vector<String>();
        temp.add(HealthID);
        temp.add(AnimalID);
        temp.add(UserID);
        temp.add(HealthInformation);
        temp.add(CheckDate+"");
        temp.add(Note);
        resVector.add(temp);
        }
        Object[][] resObjects=new Object[resVector.size()+1][resVector.elementAt(0).size()];
        for(int i=0;i<resVector.elementAt(0).size();i++) {
        	resObjects[0][i]=columnNames[i];
        }
        for(int i=1;i<resVector.size()+1;i++) {
        	for(int j=0;j<resVector.elementAt(0).size();j++) {
        		resObjects[i][j]=resVector.elementAt(i-1).elementAt(j);
        	}
        }
        return resObjects;
	}
	
	public Object[][] getVaccineInfTable() throws SQLException {//表格获取Vaccine信息
		Statement st=conn.createStatement();
        String sql="SELECT * from \"Vaccine\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        Object[] columnNames = {"VaccineID","AnimalID","UserID","VaccineName","VaccinationTime","Note"};
        Vector<Vector<String>> resVector=new Vector<Vector<String>>();
        while(rs.next()) { //循环遍历结果集
        String VaccineID=rs.getString("VaccineID");
        String AnimalID=rs.getString("AnimalID");
        String UserID=rs.getString("UserID");
        String VaccineName=rs.getString("VaccineName");
        String VaccinationTime=rs.getString("vaccinationTime");
        String Note=rs.getString("Note");
        Vector<String> temp = new Vector<String>();
        temp.add(VaccineID);
        temp.add(AnimalID);
        temp.add(UserID);
        temp.add(VaccineName);
        temp.add(VaccinationTime);
        temp.add(Note);
        resVector.add(temp);
        }
        Object[][] resObjects=new Object[resVector.size()+1][resVector.elementAt(0).size()];
        for(int i=0;i<resVector.elementAt(0).size();i++) {
        	resObjects[0][i]=columnNames[i];
        }
        for(int i=1;i<resVector.size()+1;i++) {
        	for(int j=0;j<resVector.elementAt(0).size();j++) {
        		resObjects[i][j]=resVector.elementAt(i-1).elementAt(j);
        	}
        }
        return resObjects;
	}
		
	public int UserInfUpdate(String inputUserID,String inputUserName,String inputUserPassword,//更新User信息
			String inputUserEmail,String inputUserPhone,String inputUserShelterID) {
		String sql = "{call \"UpdateUser\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inputUserID);
			call.setString(2, inputUserName);
			call.setString(3, inputUserPassword);
			call.setString(4, inputUserEmail);
			call.setString(5, inputUserPhone);
			call.setString(6, inputUserShelterID);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int UserInfDelete(String inputUserID) {//根据UserID删除User信息
		String sql = "{call \"DeleteUser\"(?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inputUserID);
			call.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(2);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int AnimalInfAdd(String inAnimalID,String inAnimalNo,String inAnimalName,//添加Animal信息
			String inAnimalType,String inAnimalSex, String inAnimalAge, String inAnimalImage, String inShelterID) {
		int inAnimalAgeInt = Integer.parseInt(inAnimalAge);  // 10
		String sql = "{call \"AddAnimal\"(?,?,?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inAnimalID);
			call.setString(2, inAnimalNo);
			call.setString(3, inAnimalName);
			call.setString(4, inAnimalType);
			call.setString(5, inAnimalSex);
			call.setInt(6, inAnimalAgeInt);
			call.setString(7, inAnimalImage);
			call.setString(8, inShelterID);
			call.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(9);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int AnimalInfUpdate(String inAnimalID,String inAnimalNo,String inAnimalName,//更新Animal信息
			String inAnimalType,String inAnimalSex, String inAnimalAge, String inAnimalImage, String inShelterID) {
		int inAnimalAgeInt = Integer.parseInt(inAnimalAge);
		String sql = "{call \"UpdateAnimal\"(?,?,?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inAnimalID);
			call.setString(2, inAnimalNo);
			call.setString(3, inAnimalName);
			call.setString(4, inAnimalType);
			call.setString(5, inAnimalSex);
			call.setInt(6, inAnimalAgeInt);
			call.setString(7, inAnimalImage);
			call.setString(8, inShelterID);
			call.registerOutParameter(9, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(9);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int AnimalInfDelete(String inAnimalID) {//根据AnimalID删除animal信息
		String sql = "{call \"DeleteAnimal\"(?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inAnimalID);
			call.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(2);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int ShelterInfAdd(String inShelterID,String inShelterName,String inShelterAddress,//添加Shelter信息
			String inSheterZipCoce,String inShelterAllroomNum, String inShelterRemainingRoonNum) {
		int inShelterAllroomNumInt = Integer.parseInt(inShelterAllroomNum); 
		int inShelterRemainingRoonNumInt = Integer.parseInt(inShelterRemainingRoonNum); 
		String sql = "{call \"AddShelter\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inShelterID);
			call.setString(2, inShelterName);
			call.setString(3, inShelterAddress);
			call.setString(4, inSheterZipCoce);
			call.setInt(5, inShelterAllroomNumInt);
			call.setInt(6, inShelterRemainingRoonNumInt);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int ShelterInfUpdate(String inShelterID,String inShelterName,String inShelterAddress,//更新Shelter信息
			String inSheterZipCoce,String inShelterAllroomNum, String inShelterRemainingRoonNum) {
		int inShelterAllroomNumInt = Integer.parseInt(inShelterAllroomNum); 
		int inShelterRemainingRoonNumInt = Integer.parseInt(inShelterRemainingRoonNum); 
		String sql = "{call \"UpdateShelter\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inShelterID);
			call.setString(2, inShelterName);
			call.setString(3, inShelterAddress);
			call.setString(4, inSheterZipCoce);
			call.setInt(5, inShelterAllroomNumInt);
			call.setInt(6, inShelterRemainingRoonNumInt);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int ShelterInfDelete(String inShelterID) {//根据ShelterID删除Shelter信息
		String sql = "{call \"DeleteShelter\"(?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inShelterID);
			call.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(2);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int HealthInfAdd(String inHealthID,String inAnimalID,String inUserID,//添加Health信息
			String inHealthInf,String inCheckDate, String inNote) {
		String sql = "{call \"AddHealth\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inHealthID);
			call.setString(2, inAnimalID);
			call.setString(3, inUserID);
			call.setString(4, inHealthInf);
			call.setString(5, inCheckDate);
			call.setString(6, inNote);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int HealthInfUpdate(String inHealthID,String inAnimalID,String inUserID,//更新health信息
			String inHealthInf,String inCheckDate, String inNote) {
		String sql = "{call \"UpdateHealth\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inHealthID);
			call.setString(2, inAnimalID);
			call.setString(3, inUserID);
			call.setString(4, inHealthInf);
			call.setString(5, inCheckDate);
			call.setString(6, inNote);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int HealthInfDelete(String inHealthID) {//根据healthID删除health信息
		String sql = "{call \"DeleteHealth\"(?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inHealthID);
			call.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(2);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int VaccineInfAdd(String inVaccineID,String inAnimalID,String inUserID,//添加Vaccine信息
			String inVaccineName,String inVaccinationTime, String inNote) {
		String sql = "{call \"AddVaccine\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inVaccineID);
			call.setString(2, inAnimalID);
			call.setString(3, inUserID);
			call.setString(4, inVaccineName);
			call.setString(5, inVaccinationTime);
			call.setString(6, inNote);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int VaccineInfUpdate(String inVaccineID,String inAnimalID,String inUserID,//更新Vaccine信息
			String inVaccineName,String inVaccinationTime, String inNote) {
		String sql = "{call \"UpdateVaccine\"(?,?,?,?,?,?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inVaccineID);
			call.setString(2, inAnimalID);
			call.setString(3, inUserID);
			call.setString(4, inVaccineName);
			call.setString(5, inVaccinationTime);
			call.setString(6, inNote);
			call.registerOutParameter(7, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(7);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int VaccineInfDelete(String inVaccineID) {//根据VaccineID删除Vaccine信息
		String sql = "{call \"DeleteVaccine\"(?,?)}";
		CallableStatement call = null;
		int res = 0;
		try {
			call = conn.prepareCall(sql);
			call.setString(1, inVaccineID);
			call.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			int resFlag=call.getInt(2);
			System.out.println(resFlag);
			return resFlag;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public String GetInf(String ID,int type) {//详细信息获取
		String sql = "{call \"GetInf\"(?,?,?,?)}";
		System.out.print(ID+"  "+type);
		CallableStatement call = null;
		String resString = "Error!";
		try {
			call = conn.prepareCall(sql);
			call.setString(1, ID);
			call.setInt(2, type);
			call.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			call.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
			call.execute();
			int resFlag=call.getInt(3);
				if(resFlag == 1) {
					resString=call.getString(4);
				}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resString = resString.replace("`", "\n");
		return resString;
	}
	
	
/*
	public String getUserInf() throws SQLException {//获取user信息
		String res=" ";
		Statement st=conn.createStatement();
        String sql="SELECT \"UserID\",\"UserName\",\"Email\",\"PhoneNo\",\"ShelterID\" \n" + 
        		"FROM \"User\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        while(rs.next()) { //循环遍历结果集
        String UserID=rs.getString("UserID");
        String UserName=rs.getString("UserName");
        String Email=rs.getString("Email");
        String PhoneNo=rs.getString("PhoneNo");
        String ShelterID=rs.getString("SHelterID");
        //System.out.println("UserID:"+UserID+"\nUserName:"+UserName+"\nEmail:"+Email+"\nPhoneNo:"+PhoneNo+"\nShelterID:"+ShelterID);
        res+="UserID:"+UserID+"\nUserName:"+UserName+"\nEmail:"+Email+"\nPhoneNo:"+PhoneNo+"\nShelterID:"+ShelterID+"\n\n";
        }
        System.out.println(res);
        return res;
	}
*/
	
/*
	public String getAnimalInf() throws SQLException {//获取animal信息
		String res=" ";
		Statement st=conn.createStatement();
        String sql="SELECT \"AnimalID\",\"AnimalNo\",\"AnimalName\",\"AnimalType\",\"AnimalSex\",\"AnimalAge\",\"ShelterID\"\n " + 
        		"FROM \"Animal\" ";
        ResultSet rs=st.executeQuery(sql);
        rs=st.executeQuery(sql);
        while(rs.next()) { //循环遍历结果集
        String AnimalID=rs.getString("AnimalID");
        String AnimalNo=rs.getString("AnimalNo");
        String AnimalName=rs.getString("AnimalName");
        String AnimalType=rs.getString("AnimalType");
        String AnimalSex=rs.getString("AnimalSex");
        int AnimalAge=rs.getInt("AnimalAge");
        String ShelterID=rs.getString("ShelterID");
        res+="AnimalID:"+AnimalID+"\nAnimalNo:"+AnimalNo+"\nAnimalName:"+AnimalName+"\nAnimalType:"+AnimalType+"\nAnimalSex:"+AnimalSex+"\nAnimalAge:"+AnimalAge+"\nShelterID:"+ShelterID+"\n\n";
        }
        System.out.println(res);
        return res;
	}
*/	
	
/*
	public boolean UserLogin(String Account,String Password) {//用户登录
		Statement st;
		try {
			st = conn.createStatement();
			String sql="select * from \"User\" ";
			ResultSet rs=st.executeQuery(sql);
			rs=st.executeQuery(sql);
			while(rs.next()) { //循环遍历结果集
				String UserID=rs.getString("UserID");
				String UserName=rs.getString("UserName");	
				String PassWord=rs.getString("Password");
				if(UserID.equals(Account)&&PassWord.equals(Password)){
					return true;
        	}
        }
		} catch (SQLException e) {
			System.out.println("UserLogin error!");
			e.printStackTrace();
		}
        return false;
	}
	
	public boolean AdminLogin(String Account,String Password) {//管理员登录
		Statement st;
		try {
			st = conn.createStatement();
			String sql="select * from \"Administrator\" ";
			ResultSet rs=st.executeQuery(sql);
			rs=st.executeQuery(sql);
			while(rs.next()) { //循环遍历结果集
				String AdminID=rs.getString("AdminID");
				String PassWord=rs.getString("AdminPassword");
				if(AdminID.equals(Account)&&PassWord.equals(Password)){
					return true;
        	}
        }
		} catch (SQLException e) {
			System.out.println("AdminLogin error!");
			e.printStackTrace();
		}
        return false;
	}
*/	
	
/*
	public boolean UserRegistration(String inputUserID,String inputUserName,String inputUserPassword,
			String inputUserEmail,String inputUserPhone,String inputUserShelterID) {//用户注册
		Statement st;
		try {
			st = conn.createStatement();
			//检测重复
			String sql="select \"UserID\" from \"User\" ";
			ResultSet rs=st.executeQuery(sql);
			rs=st.executeQuery(sql);
			while(rs.next()) { //循环遍历结果集
				String UserID=rs.getString("UserID");
				if(UserID.equals(inputUserID)){
					return false;
				}
			}
			//插入信息
			sql="insert into \"User\" values ('"+inputUserID+"','"+inputUserName+"','"+inputUserPassword+"','"+
			inputUserEmail+"','"+inputUserPhone+"','"+inputUserShelterID+"') ";
			st.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("Insert error!");
			e.printStackTrace();
		}
        return false;
	}
*/	
}