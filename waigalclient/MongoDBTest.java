import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.ridehub.db.WaigalDBSchema;

public class MongoDBTest {

	private MongoClient mongoClient;
	private MongoDatabase db;
	
	@Before
	public void setUp() throws Exception {
		mongoClient = new MongoClient();
		db = mongoClient.getDatabase(WaigalDBSchema.DB_NAME);
		db.createCollection(WaigalDBSchema.COLLECTION.USERS.name());
	}

	@After
	public void tearDown() throws Exception {
		mongoClient.dropDatabase(WaigalDBSchema.DB_NAME);
		mongoClient.close();
	}

	@Test
	public void test() {
		MongoCollection<Document> collection = db.getCollection(WaigalDBSchema.COLLECTION.USERS.name());
		assertNotNull(collection);
	}

	@Test
	public void testInsert() {
		MongoCollection<Document> users = db.getCollection(WaigalDBSchema.COLLECTION.USERS.name());
		assertNotNull(users);
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put(WaigalDBSchema.USER_KEY.FIRSTNAME.name(), "John");
		values.put(WaigalDBSchema.USER_KEY.LASTNAME.name(), "Doe");
		
		users.insertOne(new Document(values));
		
		Document document = users.find().first();
		assertNotNull(document);
		assertEquals("firstname", document.get(WaigalDBSchema.USER_KEY.FIRSTNAME.name()), "John");
		
		System.out.println("document: " + document);
	}
	
}
