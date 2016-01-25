import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBTest {

	private MongoClient mongoClient;
	private MongoDatabase db;
	private static final String DB_TEST = "test";
	private static final String COLLECTION_USERS = "users";

	@Before
	public void setUp() throws Exception {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase(DB_TEST);
	}

	@After
	public void tearDown() throws Exception {
		mongoClient.dropDatabase(DB_TEST);
		mongoClient.close();
	}

	@Test
	public void test() {
		db.createCollection(COLLECTION_USERS);
		MongoCollection<Document> collection = db.getCollection(COLLECTION_USERS);
		assertNotNull(collection);
	}

}
