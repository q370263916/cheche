package com.chechemotel.logs.common;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

public class MongoDataSource {
	private MongoClient mongo;
	private MongoDatabase db;
	private String dbName;
	private List<ServerAddress> seeds;
	private MongoClientOptions.Builder builder = MongoClientOptions.builder()
			.legacyDefaults();
	private String userName;
	private String password;
	
	private String mongoUri;

	public MongoDataSource() {
		builder.writeConcern(WriteConcern.ACKNOWLEDGED);
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMinConnectionsPerHost(int minConnectionsPerHost) {
		builder.minConnectionsPerHost(minConnectionsPerHost);
	}

	public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
		builder.connectionsPerHost(maxConnectionsPerHost);
	}

	public void setWriteConcern(String writeConcern) {
		builder.writeConcern(WriteConcern.valueOf(writeConcern));
	}

	public void setReadPreference(String readPreference) {
		builder.readPreference(ReadPreference.valueOf(readPreference));
	}

	public void setMaxWaitTime(int maxWaitTime) {
		builder.maxWaitTime(maxWaitTime);
	}

	public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
		builder.maxConnectionIdleTime(maxConnectionIdleTime);
	}

	public void setMaxConnectionLifeTime(int maxConnectionLifeTime) {
		builder.maxConnectionLifeTime(maxConnectionLifeTime);
	}

	public void setConnectTimeout(int connectTimeout) {
		builder.connectTimeout(connectTimeout);
	}

	public void setSocketTimeout(int socketTimeout) {
		builder.socketTimeout(socketTimeout);
	}

	public void setSocketKeepAlive(boolean socketKeepAlive) {
		builder.socketKeepAlive(socketKeepAlive);
	}

	public void setHeartbeatFrequency(int heartbeatFrequency) {
		builder.heartbeatFrequency(heartbeatFrequency);
	}

	public void setMinHeartbeatFrequency(int minHeartbeatFrequency) {
		builder.minHeartbeatFrequency(minHeartbeatFrequency);
	}

	public void setHeartbeatConnectTimeout(int heartbeatConnectTimeout) {
		builder.heartbeatConnectTimeout(heartbeatConnectTimeout);
	}

	public void setHeartbeatSocketTimeout(int heartbeatSocketTimeout) {
		builder.heartbeatSocketTimeout(heartbeatSocketTimeout);
	}

	public void setLocalThreshold(int localThreshold) {
		builder.localThreshold(localThreshold);
	}

	public void setRequiredReplicaSetName(String requiredReplicaSetName) {
		builder.requiredReplicaSetName(requiredReplicaSetName);
	}

	public void init() {
		if (dbName == null || dbName.isEmpty())
			dbName = "test";
		if (seeds == null) {
			seeds = new ArrayList<ServerAddress>();
			seeds.add(new ServerAddress());
		}

		if (null != userName && userName.trim().length() > 0 && null != password && password.trim().length() > 0) {
			List<MongoCredential> creds = new ArrayList<MongoCredential>();
			creds.add(MongoCredential.createCredential(userName, dbName, password.toCharArray()));
			mongo = new MongoClient(seeds, creds, builder.build());
		} else {
			mongo = new MongoClient(seeds, builder.build());
		}

		db = mongo.getDatabase(dbName);
	}
	
	/**
	 * 连接字符串方式连接
	 */
	public void initByMongoUri() {
		StringBuffer mongoUriStr = new StringBuffer("mongodb://"+userName+":"+ password +"@"+seeds+"/"+dbName+"?authMechanism=MONGODB-CR");
		MongoClientURI connStr = new MongoClientURI(mongoUriStr.toString());
		mongo = new MongoClient(connStr);
		db = mongo.getDatabase(dbName);
		//db.drop();
	}

	public void cleanup() {
		if (mongo != null)
			mongo.close();
	}

	public MongoDatabase getDb() {
		return db;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setSeeds(String seed) throws MongoDaoException {
		// host:port,host:port
		String[] ss1 = seed.split(",");
		seeds = new ArrayList<ServerAddress>();
		for (int i = 0; i < ss1.length; i++) {
			String[] ss = ss1[i].split(":");
			if (ss.length != 2)
				throw new MongoDaoException(MongoDaoException.SERVICECONFIG, "invalid address");
			seeds.add(new ServerAddress(ss[0], Integer.parseInt(ss[1])));
		}
		if (seeds.isEmpty())
			seeds = null;
	}

	public String getMongoUri() {
		return mongoUri;
	}

	public void setMongoUri(String mongoUri) {
		this.mongoUri = mongoUri;
	}
	
	
}
