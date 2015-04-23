package test_java;

public class SqlSentence {
	private String before,mid,after;
	SqlSentence(String b1,String m1,String a1){
		before=b1;
		mid=m1;
		after=a1;
	}
	
	SqlSentence(){
		before=mid=after="";
	}
	
	String getString(String[] par){
		return before+par[0]+mid+par[1]+after;
	}
}
