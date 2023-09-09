package com.hefengbao.wenqu.data.repository

import com.hefengbao.wenqu.common.network.AppDispatchers
import com.hefengbao.wenqu.common.network.Dispatcher
import com.hefengbao.wenqu.data.database.dao.ChineseWisecrackDao
import com.hefengbao.wenqu.data.database.dao.IdiomDao
import com.hefengbao.wenqu.data.database.dao.PoemDao
import com.hefengbao.wenqu.data.database.dao.PoemSentenceDao
import com.hefengbao.wenqu.data.database.dao.PoemTagDao
import com.hefengbao.wenqu.data.database.dao.TagDao
import com.hefengbao.wenqu.data.database.dao.WriterDao
import com.hefengbao.wenqu.data.database.entity.ChineseWisecrackEntity
import com.hefengbao.wenqu.data.database.entity.IdiomEntity
import com.hefengbao.wenqu.data.database.entity.PoemEntity
import com.hefengbao.wenqu.data.database.entity.PoemSentenceEntity
import com.hefengbao.wenqu.data.database.entity.PoemTagCrossRef
import com.hefengbao.wenqu.data.database.entity.TagEntity
import com.hefengbao.wenqu.data.database.entity.WriterEntity
import com.hefengbao.wenqu.data.model.ChineseWisecrack
import com.hefengbao.wenqu.data.model.Idiom
import com.hefengbao.wenqu.data.model.Poem
import com.hefengbao.wenqu.data.model.PoemSentence
import com.hefengbao.wenqu.data.model.PoemTag
import com.hefengbao.wenqu.data.model.Tag
import com.hefengbao.wenqu.data.model.Writer
import com.hefengbao.wenqu.data.network.fake.FakeNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SyncRepositoryImpl @Inject constructor(
    private val networkDataSource: FakeNetworkDataSource,
    @Dispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val poemDao: PoemDao,
    private val tagDao: TagDao,
    private val poemTagDao: PoemTagDao,
    private val writerDao: WriterDao,
    private val poemSentenceDao: PoemSentenceDao,
    private val chineseWisecrackDao: ChineseWisecrackDao,
    private val idiomDao: IdiomDao,
) : SyncRepository {
    override fun syncPoems(): Flow<List<Poem>> = flow {
        emit(
            networkDataSource.getPoems()
        )
    }.flowOn(ioDispatcher)

    override fun syncWriters(): Flow<List<Writer>> = flow {
        emit(
            networkDataSource.getWriters()
        )
    }.flowOn(ioDispatcher)

    override fun syncTags(): Flow<List<Tag>> = flow {
        emit(
            networkDataSource.getTags()
        )
    }.flowOn(ioDispatcher)

    override fun syncPoemTagList(): Flow<List<PoemTag>> = flow {
        emit(
            networkDataSource.getPoemTagList()
        )
    }.flowOn(ioDispatcher)

    override fun syncPoemSentences(): Flow<List<PoemSentence>> = flow {
        emit(
            networkDataSource.getPoemSentences()
        )
    }.flowOn(ioDispatcher)

    override fun syncChineseWisecracks(): Flow<List<ChineseWisecrack>> = flow {
        emit(
            networkDataSource.getChineseWisecracks()
        )
    }.flowOn(ioDispatcher)

    override fun syncIdioms(): Flow<List<Idiom>> = flow {
        emit(
            networkDataSource.getIdioms()
        )
    }.flowOn(ioDispatcher)

    override suspend fun insertPoem(entity: PoemEntity) {
        poemDao.insert(entity)
    }

    override suspend fun insertTag(entity: TagEntity) {
        tagDao.insert(entity)
    }

    override suspend fun insertPoemTag(entity: PoemTagCrossRef) {
        poemTagDao.insert(entity)
    }

    override suspend fun insertWriter(entity: WriterEntity) {
        writerDao.insert(entity)
    }

    override suspend fun insertPoemSentence(entity: PoemSentenceEntity) {
        poemSentenceDao.insert(entity)
    }

    override suspend fun insertChineseWisecrack(entity: ChineseWisecrackEntity) {
        chineseWisecrackDao.insert(entity)
    }

    override suspend fun insertIdiom(entity: IdiomEntity) {
        idiomDao.insert(entity)
    }
}