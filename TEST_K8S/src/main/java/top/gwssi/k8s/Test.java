package top.gwssi.k8s;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

public class Test {
	public static void main(String[] args) throws Exception {
		
		List<String> hosts = new ArrayList<String>() ;
		InetAddress[] allByName = InetAddress.getAllByName("baidu.com");
		System.out.println("allByName.length = " + allByName.length);
		for(InetAddress address : allByName){
			String hostAddress = address.getHostAddress() ;
			System.out.println(hostAddress);
			hosts.add(hostAddress) ;
		}
		
		
		//查询域名对应的IP地址
		Lookup lookup = new Lookup("baidu.com", Type.A);
		lookup.run();
		if (lookup.getResult() != Lookup.SUCCESSFUL){
		    System.out.println("ERROR: " + lookup.getErrorString());
		    return;
		}
		Record[] answers = lookup.getAnswers();
		for(Record rec : answers){
		    System.out.println(rec.toString());
		}
	}
}
