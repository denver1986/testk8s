package top.gwssi.k8s.config;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;


@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	String DOMAIN = "estranged-seagull-mongodb-replicaset" ;
	
	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	public Mongo mongo() throws Exception {
		

		List<ServerAddress> seedList = new ArrayList<ServerAddress>();
		InetAddress[] allByName = InetAddress.getAllByName(DOMAIN);
		System.out.println("allByName.length = " + allByName.length);
		for(InetAddress address : allByName){
			String hostAddress = address.getHostAddress() ;
			System.out.println(hostAddress);
			seedList.add(new ServerAddress(hostAddress,27017)) ;
		}
		
		
		//查询域名对应的IP地址
		Lookup lookup = new Lookup(DOMAIN, Type.A);
		lookup.run();
		if (lookup.getResult() != Lookup.SUCCESSFUL){
		    System.out.println("ERROR: " + lookup.getErrorString());
//		    return nul;
		}
		Record[] answers = lookup.getAnswers();
		if(answers != null && answers.length > 0){
			for(Record rec : answers){
			    System.out.println(rec.toString());
			}
		}
		
//		MongoCredential credential = MongoCredential.createCredential("gwssi", "bigdata", "gwssi123".toCharArray());
		Mongo mongo = new MongoClient(seedList,MongoClientOptions.builder().build()) ;
		return mongo;
	}

}
