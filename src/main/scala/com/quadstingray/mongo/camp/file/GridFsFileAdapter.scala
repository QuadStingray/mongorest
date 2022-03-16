package com.quadstingray.mongo.camp.file
import better.files.File
import com.quadstingray.mongo.camp.database.MongoDatabase
import com.quadstingray.mongo.camp.file.GridFsFileAdapter.BucketChunksSuffix
import com.sfxcode.nosql.mongo.GenericObservable

class GridFsFileAdapter extends FilePlugin {
  override val name: String = "gridfs"

  override def getFile(bucket: String, fileId: String): File                = ???
  override def putFile(bucket: String, fileId: String, file: File): Boolean = ???

  override def size(bucket: String): Double = {
    MongoDatabase.databaseProvider.dao(s"$bucket$BucketChunksSuffix").collectionStatus.result().size
  }

  override def delete(bucket: String): Unit = {
    MongoDatabase.databaseProvider.dao(s"$bucket$BucketChunksSuffix").drop().result()
  }

  override def clear(bucket: String): Boolean = {
    MongoDatabase.databaseProvider.dao(s"$bucket$BucketChunksSuffix").deleteAll().result().wasAcknowledged()
  }

}

object GridFsFileAdapter {

  val BucketChunksSuffix = ".chunks"

}
