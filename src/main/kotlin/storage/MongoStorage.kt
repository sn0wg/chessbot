package storage

import championship.League
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.conversions.Bson
import org.litote.kmongo.*
import kotlin.reflect.KClass

object MongoStorage {
    private val client: MongoClient
    val db: MongoDatabase

    init {
        client = KMongo.createClient("mongodb+srv://snowg:DrkilnEGWFzcov35@cluster0-20o1q.mongodb.net/chessbot?retryWrites=true&w=majority")
        db = client.getDatabase("chessdb")
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