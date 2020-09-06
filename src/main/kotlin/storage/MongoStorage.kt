package storage

import championship.League
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import config.ConfigLoader
import org.bson.conversions.Bson
import org.litote.kmongo.*
import kotlin.reflect.KClass

object MongoStorage {
    private val client: MongoClient
    val db: MongoDatabase

    init {
        client = KMongo.createClient(ConfigLoader.read("storage.url"))
        db = client.getDatabase(ConfigLoader.read("storage.database"))
    }

    inline fun <reified X : Any> save(entity: X) {
        val collection = db.getCollection<X>()
        collection.insertOne(entity)
    }

    inline fun <reified X : Any> list(): List<X> {
        val collection = db.getCollection<X>()
        return collection.find().toList()
    }

    inline fun <reified X: Any> list(filter: Bson): X? {
        val collection = db.getCollection<X>()
        return collection.findOne(filter)
    }

    inline fun <reified X: Any> updateByID(id: String, entity: X) {
        val collection = db.getCollection<X>()
        collection.updateOneById(id, entity, UpdateOptions().upsert(true))
    }
}