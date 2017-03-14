package com.chechemotel.logs.common;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.bulk.BulkWriteError;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;


public class MongoDao implements Closeable {
	private static final int INIT_ROWS = 20;
	public static final String ID = "_id";

	private MongoDataSource dataSource;

	public MongoDao(MongoDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void close() throws IOException {
		try {
			clear();
		} catch (Exception e) {
		}
	}

	public MongoDao clear() throws MongoDaoException {
		try {
			closeCursor();

			documents = null;
			document = null;

			filters = null;
			filter = null;
			orHead = -1;

			setDoc = null;
			unsetDoc = null;
			incDoc = null;

			project = null;
			sort = null;
			batchSize = 0;
			skip = 0;
			limit = 0;
			maxTime = 0;
			ordered = true;

			upsert = false;
			multi = false;

			group = null;
			groupProject = null;

			return this;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private void closeCursor() {
		if (cursor != null) {
			try {
				cursor.close();
			} catch (Exception e) {
			}
			cursor = null;
		}
	}

	public boolean hasResult(String tname) throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			return table.count(filter, new CountOptions().limit(1)) > 0;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public int count(String tname) throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			return (int) table.count(filter);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// find
	private MongoCursor<Document> cursor;
	private Document project;
	private Document sort;
	private int batchSize;
	private int skip;
	private int limit;
	private long maxTime;

	public MongoDao batchSize(int batchSize) {
		this.batchSize = batchSize;
		return this;
	}

	public MongoDao skip(int skip) {
		this.skip = skip;
		return this;
	}

	public MongoDao limit(int limit) {
		this.limit = limit;
		return this;
	}

	public MongoDao maxTime(long maxTime) {
		this.maxTime = maxTime;
		return this;
	}

	public MongoDao sortAsc(String... keys) {
		if (sort == null)
			sort = new Document();
		for (String key : keys)
			sort.append(key, 1);
		return this;
	}

	public MongoDao sortDesc(String... keys) {
		if (sort == null)
			sort = new Document();
		for (String key : keys)
			sort.append(key, -1);
		return this;
	}

	public MongoDao project(String... keys) {
		if (project == null)
			project = new Document();
		for (String key : keys)
			project.put(key, 1);
		return this;
	}
	public MongoDao noProject(String... keys) {
		if (project == null)
			project = new Document();
		for (String key : keys)
			project.put(key, 0);
		return this;
	}

	private Document getFilter() {
		if (filters != null)
			return filters.get(0);
		return null;
	}

	private Document getGroupFilter() {
		if (filters != null && filters.size() > 1)
			return filters.get(1);
		return null;
	}

	public MongoDao find(String tname) throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			if (group == null) {
				FindIterable<Document> result;
				Document filter = getFilter();
				if (filter != null)
					result = table.find(filter);
				else
					result = table.find();
				if (project != null) {
					//project.put(ID, 0);
					result.projection(project);
				} else
					//result.projection(new Document(ID, 0));
				if (sort != null)
					result.sort(sort);
				if (batchSize != 0)
					result.batchSize(batchSize);
				if (skip != 0)
					result.skip(skip);
				if (limit != 0)
					result.limit(limit);
				if (maxTime != 0)
					result.maxTime(maxTime, TimeUnit.MILLISECONDS);
				cursor = result.iterator();
			} else {
				List<Document> pipeline = new ArrayList<Document>();
				Document filter = getFilter();
				if (filter != null)
					pipeline.add(new Document("$match", filter));
				pipeline.add(new Document("$project", project));
				pipeline.add(new Document("$group", group));
				pipeline.add(new Document("$project", groupProject));
				groupProject.put(ID, 0);
				filter = getGroupFilter();
				if (filter != null)
					pipeline.add(new Document("$match", filter));
				AggregateIterable<Document> result = table.aggregate(pipeline);
				cursor = result.iterator();
			}
			return this;
		} catch (Exception e) {
			closeCursor();
			throw handleException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> distinct(String tname, String key, Class<?> type)
			throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			DistinctIterable<?> result;
			Document filter = getFilter();
			if (filter != null)
				result = table.distinct(key, type);
			else
				result = table.distinct(key, filter, type);
			if (maxTime != 0)
				result.maxTime(maxTime, TimeUnit.MILLISECONDS);
			MongoCursor<?> cursor = result.iterator();
			try {
				List<T> list = new ArrayList<T>();
				while (cursor.hasNext()) {
					list.add((T) cursor.next());
				}
				return list;
			} finally {
				cursor.close();
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// insert
	private List<Document> documents;
	private Document document;
	private boolean ordered = true;

	public MongoDao ordered(boolean ordered) {
		this.ordered = ordered;
		return this;
	}

	private void initDocuments() {
		if (documents == null)
			documents = new ArrayList<Document>();
	}

	public MongoDao newRow() {
		initDocuments();
		document = new Document();
		documents.add(document);
		return this;
	}

	public MongoDao put(String key, Object obj) {
		initDocument();
		document.put(key, obj);
		return this;
	}
	
	public Object get(String key) {
		if (null != document) {
			return document.get(key);
		}
		return null;
	}

	/**
	 * 
	 * @param tname
	 * @return 0 : 失败， 1 : 成功
	 * @throws MongoDaoException
	 */
	public int insert(String tname) throws MongoDaoException {
		return insert(tname, true);
	}

	public int insert(String tname, boolean commit) throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(tname, Document.class);
			int n = 0;
			if (documents == null) {
				if (document != null) {
					table.insertOne(document);
					n = 1;
				}
			} else {
				InsertManyOptions options = new InsertManyOptions();
				options.ordered(ordered);
				table.insertMany(documents, options);
				n = documents.size();
			}
			return n;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// modify
	private boolean upsert;
	private boolean multi;
	private Document setDoc;
	private Document unsetDoc;
	private Document incDoc;

	public MongoDao upsert(boolean upsert) {
		this.upsert = upsert;
		return this;
	}

	public MongoDao multi(boolean multi) {
		this.multi = multi;
		return this;
	}

	public int remove(String tname) throws MongoDaoException {
		return remove(tname, true);
	}

	public int remove(String tname, boolean commit) throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			Document filter = getFilter();
			if (filter == null)
				filter = new Document();
			DeleteResult r = table.deleteMany(filter);
			return (int) r.getDeletedCount();
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	/**
	 * 
	 * @param tname
	 * @return 1 是成功，0 是失败
	 * @throws MongoDaoException
	 */
	public int update(String tname) throws MongoDaoException {
		return update(tname, true);
	}

	public int update(String tname, boolean commit) throws MongoDaoException {
		boolean update = false;
		initDocument();
		if (setDoc != null) {
			document.put("$set", setDoc);
			update = true;
		}
		if (unsetDoc != null) {
			document.put("$unset", unsetDoc);
			update = true;
		}
		if (incDoc != null) {
			document.put("$inc", incDoc);
			update = true;
		}
		if (!update)
			return 0;
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(tname, Document.class);
			UpdateOptions updateOptions = new UpdateOptions();
			updateOptions.upsert(upsert);
			Document filter = getFilter();
			if (filter == null)
				filter = new Document();
			UpdateResult r;
			if (multi)
				r = table.updateMany(filter, document, updateOptions);
			else {
				r = table.updateOne(filter, document, updateOptions);
			}
			if (r.getUpsertedId() != null)
				return 1;
			return (int) r.getModifiedCount();
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public int replace(String tname) throws MongoDaoException {
		return replace(tname, true);
	}

	public int replace(String tname, boolean commit) throws MongoDaoException {
		if (document == null)
			return 0;
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			UpdateOptions updateOptions = new UpdateOptions();
			updateOptions.upsert(upsert);
			Document filter = getFilter();
			if (filter == null)
				filter = new Document();
			UpdateResult r = table.replaceOne(filter, document, updateOptions);
			if (r.getUpsertedId() != null)
				return 1;
			return (int) r.getModifiedCount();
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public long getAutoIncrementId(String tname, String key)
			throws MongoDaoException {
		try {
			MongoCollection<Document> table = dataSource.getDb().getCollection(
					tname, Document.class);
			FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
			options.upsert(true);
			options.returnDocument(ReturnDocument.AFTER);
			if (maxTime != 0)
				options.maxTime(maxTime, TimeUnit.MILLISECONDS);
			Document doc = table.findOneAndUpdate(new Document(ID, key),
					new Document("$inc", new Document("id", 1L)), options);
			return doc.getLong("id");
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private void initDocument() {
		if (document == null)
			document = new Document();
	}

	public MongoDao set(String key, Object obj) {
		if (setDoc == null)
			setDoc = new Document(key, obj);
		setDoc.put(key, obj);
		return this;
	}

	public MongoDao unset(String... keys) {
		if (unsetDoc == null)
			unsetDoc = new Document();
		for (String key : keys)
			unsetDoc.put(key, 1);
		return this;
	}

	public MongoDao inc(String key, Object obj) {
		if (incDoc == null)
			incDoc = new Document(key, obj);
		incDoc.put(key, obj);
		return this;
	}

	// group
	private Document group;

	// {$match:{"k":v}},
	// {$project:{"k":1}}
	// {$group:{"_id":{"k":"$k"},"k1":{"$sum":"$n"}}}
	// {$match:{}}
	// {$sort:{}}

	private Document groupProject;

	private void initGroup() {
		if (group == null)
			group = new Document();
		if (groupProject == null)
			groupProject = new Document();
		if (project == null)
			project = new Document();
	}

	public MongoDao groupBy(String... keys) {
		initGroup();
		Document doc = (Document) group.get(ID);
		if (doc == null) {
			doc = new Document();
			group.put(ID, doc);
		}
		// _id:{}
		for (String key : keys) {
			doc.put(key, "$" + key);
			project.put(key, 1);
			groupProject.put(key, "$" + ID + "." + key);
		}
		return this;
	}

	public enum GroupType {
		COUNT, SUM, AVG
	}

	public MongoDao groupAs(GroupType type, String key, String asKey) {
		initGroup();
		switch (type) {
		case COUNT:
			group.put(asKey, new Document("$sum", 1));
			break;
		case SUM:
			group.put(asKey, new Document("$sum", "$" + key));
			break;
		case AVG:
			group.put(asKey, new Document("$avg", "$" + key));
			break;
		}
		project.put(key, 1);
		groupProject.put(asKey, 1);
		return this;
	}

	// filter
	private List<Document> filters;
	private Document filter;
	private int orHead = -1;

	public MongoDao newFilter() {
		if (filters == null)
			filters = new ArrayList<Document>();
		filter = new Document();
		filters.add(filter);
		return this;
	}

	public MongoDao newGroupFilter() {
		if (filters == null) {
			filters = new ArrayList<Document>();
			filters.add(null);
		}
		filter = new Document();
		filters.add(filter);
		return this;
	}

	public MongoDao eq(String key, Object value) {
		filter.put(key, value);
		return this;
	}

	public MongoDao ne(String key, Object value) {
		filter.put(key, new Document("$ne", value));
		return this;
	}

	public MongoDao lt(String key, Object value) {
		Document doc = (Document) filter.get(key);
		if (doc != null)
			doc.put("$lt", value);
		else
			filter.put(key, new Document("$lt", value));
		return this;
	}

	public MongoDao gt(String key, Object value) {
		Document doc = (Document) filter.get(key);
		if (doc != null)
			doc.put("$gt", value);
		else
			filter.put(key, new Document("$gt", value));
		return this;
	}

	public MongoDao lte(String key, Object value) {
		Document doc = (Document) filter.get(key);
		if (doc != null)
			doc.put("$lte", value);
		else
			filter.put(key, new Document("$lte", value));
		return this;
	}

	public MongoDao gte(String key, Object value) {
		Document doc = (Document) filter.get(key);
		if (doc != null)
			doc.put("$gte", value);
		else
			filter.put(key, new Document("$gte", value));
		return this;
	}

	public MongoDao in(String key, List<?> values) {
		filter.put(key, new Document("$in", values));
		return this;
	}

	public MongoDao exists(String... keys) {
		for (String key : keys)
			filter.put(key, new Document("$exists", true));
		return this;
	}

	public MongoDao nexists(String... keys) {
		for (String key : keys)
			filter.put(key, new Document("$exists", false));
		return this;
	}

	public MongoDao orBegin() {
		orHead = filters.size();
		return this;
	}

	public MongoDao orEnd() {
		int n = filters.size() - orHead;
		List<Document> list = new ArrayList<Document>(n);
		for (int i = orHead; i < filters.size(); i++)
			list.add(filters.get(i));
		while (n-- > 0)
			list.remove(list.size() - 1);
		filter = filters.get(filters.size() - 1);
		filter.put("$or", list);
		orHead = -1;
		return this;
	}

	// ///////////////////////////////////////////////////////////////////////////
	// object <=> document

	private static class MyField {
		Field field;
		MongoDBValueType[] types;
		Class<?> aclass;
		Class<?> cclass;
		Method encode;
		Method decode;
		boolean trim;
		boolean update;
		String name;
		boolean mydao;
	}

	private static Map<String, Map<String, MyField>> clsMap = new ConcurrentHashMap<String, Map<String, MyField>>();
	private static Method[] encodeMethods = new Method[20];
	private static Method[] decodeMethods = new Method[20];

	static {
		for (Method m : MongoDao.class.getDeclaredMethods()) {
			if (!m.isAnnotationPresent(MongoDBValueMethod.class))
				continue;
			MongoDBValueMethod dbm = m.getAnnotation(MongoDBValueMethod.class);
			if (dbm.encode()) {
				for (MongoDBValueType t : dbm.type()) {
					encodeMethods[t.ordinal()] = m;
				}
			} else {
				for (MongoDBValueType t : dbm.type()) {
					decodeMethods[t.ordinal()] = m;
				}
			}
		}
	}

	private static Map<String, MyField> register(Class<?> cls) {
		Map<String, MyField> map = clsMap.get(cls.getName());
		if (map != null)
			return map;
		map = new HashMap<String, MyField>();
		String name = cls.getName();
		while (cls.isAnnotationPresent(MongoDaoClass.class)) {
			for (Field f : cls.getDeclaredFields()) {
				f.setAccessible(true);
				MongoDBField dbc = f.getAnnotation(MongoDBField.class);
				MyField mf = new MyField();
				f.setAccessible(true);
				mf.field = f;
				mf.cclass = dbc.cclass();
				mf.trim = dbc.trim();
				mf.update = dbc.update();
				decideFieldType(mf, f, dbc);
				if (dbc.value().isEmpty())
					mf.name = f.getName();
				else
					mf.name = dbc.value();
				map.put(mf.name, mf);
			}
			for (Method f : cls.getDeclaredMethods()) {
				if (!f.isAnnotationPresent(MongoDBMethod.class) /*
															 * ||
															 * f.getParameterCount
															 * () != 1
															 */)
					continue;
				MongoDBMethod dbc = f.getAnnotation(MongoDBMethod.class);
				MyField mf = map.get(dbc.value());
				if (mf == null)
					continue;
				f.setAccessible(true);
				if (dbc.encode())
					mf.encode = f;
				else
					mf.decode = f;
			}
			cls = cls.getSuperclass();
		}
		// clsMap.putIfAbsent(name, map);
		clsMap.put(name, map);
		return map;
	}

	private static MongoDBValueType[] mergeType(List<MongoDBValueType> types,
			MongoDBValueType[] itype) {
		// array,list,set,map
		int ord = types.get(0).ordinal();
		if (ord >= MongoDBValueType.array.ordinal()) {
			if (itype.length > 0) {
				for (MongoDBValueType t : itype)
					types.add(t);
				ord = types.get(types.size() - 1).ordinal();
				if (ord >= MongoDBValueType.array.ordinal())
					types.add(MongoDBValueType.object);
			} else
				types.add(MongoDBValueType.object);
		}
		return types.toArray(new MongoDBValueType[types.size()]);
	}

	private static void decideFieldType(MyField mf, Field f, MongoDBField dbc) {
		List<MongoDBValueType> types = new ArrayList<MongoDBValueType>(5);
		if (List.class.isAssignableFrom(f.getType())) {
			types.add(MongoDBValueType.list);
		} else if (Set.class.isAssignableFrom(f.getType())) {
			types.add(MongoDBValueType.set);
		} else if (Map.class.isAssignableFrom(f.getType())) {
			types.add(MongoDBValueType.map);
		} else if (f.getType().isArray()) {
			Class<?> cclass = f.getType().getComponentType();
			if (cclass == byte.class) {
				types.add(MongoDBValueType.binary);
			} else {
				types.add(MongoDBValueType.array);
				mf.aclass = cclass;
				if (!mf.cclass.isAnnotationPresent(MongoDaoClass.class))
					mf.cclass = cclass;
			}
		} else if (String.class.isAssignableFrom(f.getType())) {
			types.add(MongoDBValueType.string);
		} else if (Number.class.isAssignableFrom(f.getType())) {
			types.add(MongoDBValueType.number);
		} else if (Date.class.isAssignableFrom(f.getType())) {
			types.add(MongoDBValueType.date);
		} else {
			types.add(MongoDBValueType.object);
			if (!mf.cclass.isAnnotationPresent(MongoDaoClass.class))
				mf.cclass = f.getType();
		}
		mf.types = mergeType(types, dbc.stype());
		mf.mydao = mf.cclass.isAnnotationPresent(MongoDaoClass.class);
	}

	static final int INSERT = 1;
	static final int REPLACE = 2;
	static final int UPDATE = 3;

	static class EncodeContext {
		int type;
		int depth;
		Document unset;

		EncodeContext(int type, Document unset) {
			this.type = type;
			this.unset = unset;
		}

		boolean root() {
			return depth == 0;
		}

		EncodeContext unset(String key) {
			if (depth == 0 && unset != null)
				unset.put(key, 1);
			return this;
		}

		EncodeContext enter() {
			depth++;
			return this;
		}

		EncodeContext leave() {
			depth--;
			return this;
		}

		EncodeContext clear() {
			depth = 0;
			return this;
		}
	}

	private static Object encodeDocument(Map<String, MyField> map, Object o,
			EncodeContext ctx) throws Exception {
		Document doc = new Document();
		for (Map.Entry<String, MyField> e : map.entrySet()) {
			MyField _f = e.getValue();
			// insert,replace all
			// update && _update
			if (ctx.type == INSERT || ctx.type == REPLACE
					|| (ctx.type == UPDATE && _f.update)) {
				Object _v = _f.field.get(o);
				if (_v != null) { // skip null
					ctx.clear();
					Object _o = encodeValue(_f, _v, ctx, 0);
					if (_o != null)
						doc.put(e.getKey(), _o);
				}
			}
		}
		return doc.isEmpty() ? null : doc;
	}

	private static Object encodeValue(MyField f, Object o, EncodeContext ctx,
			int itype) throws Exception {
		if (f.encode != null) {
			Object v = f.encode.invoke(null, o);
			if (v == null && ctx.root())
				ctx.unset(f.name);
			return v;
		}
		if (itype >= f.types.length)
			throw new MongoDaoException(MongoDaoException.FIELDITYPE, "field "
					+ f.name + " itype error");
		return encodeMethods[f.types[itype].ordinal()].invoke(null, f, o, ctx,
				itype);
	}

	@MongoDBValueMethod(encode = true, type = MongoDBValueType.object)
	private static Object encodeValueObject(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		if (!f.mydao)
			return o;
		// MyDaoClass
		Map<String, MyField> map = register(f.cclass);
		Document _o = new Document();
		ctx.enter();
		for (Map.Entry<String, MyField> e : map.entrySet()) {
			MyField _f = e.getValue();
			Object _v = _f.field.get(o);
			if (_v != null) { // skip null
				Object r = encodeValue(_f, _v, ctx, 0);
				if (r != null
						&& (_f.update || ctx.type == INSERT || ctx.type == REPLACE))
					_o.put(e.getKey(), r);
			}
		}
		ctx.leave();
		return _o;
	}

	@MongoDBValueMethod(encode = true, type = MongoDBValueType.string)
	private static Object encodeValueString(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		if (((String) o).length() == 0 && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		return o;
	}

	@MongoDBValueMethod(encode = true, type = MongoDBValueType.number)
	private static Object encodeValueNumber(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		if (((Number) o).equals(0) && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		return o;
	}

	@MongoDBValueMethod(encode = true, type = MongoDBValueType.date)
	private static Object encodeValueDate(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		if (((Date) o).getTime() == 0 && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		return o;
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = true, type = MongoDBValueType.list)
	private static Object encodeValueList(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		List<Object> list = (List<Object>) o;
		if (list.isEmpty() && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		if (!f.mydao)
			return o;
		// MyDaoClass
		List<Object> _list = new ArrayList<Object>(list.size());
		ctx.enter();
		for (Object _o : list) {
			_list.add(_o == null ? null : encodeValue(f, _o, ctx, itype + 1));
		}
		ctx.leave();
		return _list;
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = true, type = MongoDBValueType.set)
	private static Object encodeValueSet(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		Set<Object> list = (Set<Object>) o;
		if (list.isEmpty() && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		// if (!f.mydao)
		// return o;
		ctx.enter();
		List<Object> _list = new ArrayList<Object>(list.size());
		for (Object _o : list) {
			if (_o != null) { // skip null
				_list.add(encodeValue(f, _o, ctx, itype + 1));
			}
		}
		return _list;
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = true, type = MongoDBValueType.map)
	private static Object encodeValueMap(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		Map<String, Object> list = (Map<String, Object>) o;
		if (list.isEmpty() && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		// if (!f.mydao)
		// return o;
		Document doc = new Document();
		ctx.enter();
		for (Map.Entry<String, Object> _o : list.entrySet()) {
			Object _v = _o.getValue();
			if (_v != null) { // skip null
				doc.put(_o.getKey(), encodeValue(f, _v, ctx, itype + 1));
			}
		}
		ctx.leave();
		return doc;
	}

	@MongoDBValueMethod(encode = true, type = MongoDBValueType.binary)
	private static Object encodeValueBinary(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		byte[] list = (byte[]) o;
		if (list.length == 0 && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		return new Binary(list);
	}

	@MongoDBValueMethod(encode = true, type = MongoDBValueType.array)
	private static Object encodeValueArray(MyField f, Object o,
			EncodeContext ctx, int itype) throws Exception {
		Object[] list = (Object[]) o;
		if (list.length == 0 && f.trim) {
			if (ctx.root()) // only root field
				ctx.unset(f.name);
			return null;
		}
		// if (!f.mydao)
		// return o;
		ctx.enter();
		List<Object> _list = new ArrayList<Object>(list.length);
		for (Object _o : list) {
			_list.add(_o == null ? null : encodeValue(f, _o, ctx, itype + 1));
		}
		ctx.leave();
		return _list;
	}

	static class DecodeContext {
		int depth;

		DecodeContext() {
		}

		boolean root() {
			return depth == 0;
		}

		DecodeContext enter() {
			depth++;
			return this;
		}

		DecodeContext leave() {
			depth--;
			return this;
		}

		DecodeContext clear() {
			depth = 0;
			return this;
		}
	}

	private static Object decodeDocument(Map<String, MyField> map,
			Class<?> cls, Document doc) throws Exception {
		Object _o = cls.newInstance();
		DecodeContext ctx = new DecodeContext();
		int n = 0;
		for (Map.Entry<String, Object> e : doc.entrySet()) {
			MyField _f = map.get(e.getKey());
			if (_f != null) {
				ctx.clear();
				Object _v = decodeValue(_f, e.getValue(), ctx, 0);
				if (_v != null) {
					_f.field.set(_o, _v);
					n++;
				}
			}
		}
		return n != 0 ? _o : null;
	}

	private static Object decodeValue(MyField f, Object o, DecodeContext ctx,
			int itype) throws Exception {
		if (f.decode != null) {
			return f.decode.invoke(null, o);
		}
		if (itype >= f.types.length)
			throw new MongoDaoException(MongoDaoException.FIELDITYPE, "field "
					+ f.name + " itype error");
		return decodeMethods[f.types[itype].ordinal()].invoke(null, f, o, ctx,
				itype);
	}

	@MongoDBValueMethod(encode = false, type = MongoDBValueType.object)
	private static Object decodeValueObject(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		if (!f.mydao)
			return o;
		// MyDaoClass
		Document doc = (Document) o;
		Map<String, MyField> map = register(f.cclass);
		Object _o = f.cclass.newInstance();
		ctx.enter();
		int n = 0;
		for (Map.Entry<String, Object> e : doc.entrySet()) {
			MyField _f = map.get(e.getKey());
			if (_f != null) {
				Object _v = decodeValue(_f, e.getValue(), ctx, 0);
				if (_v != null) {
					_f.field.set(_o, _v);
					n++;
				}
			}
		}
		ctx.leave();
		return n != 0 ? _o : null;
	}

	@MongoDBValueMethod(encode = false, type = { MongoDBValueType.string,
			MongoDBValueType.number, MongoDBValueType.date })
	private static Object decodeValueGeneral(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		return o;
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = false, type = MongoDBValueType.list)
	private static Object decodeValueList(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		if (!f.mydao)
			return o;
		List<Object> list = (List<Object>) o;
		ctx.enter();
		for (int i = 0; i < list.size(); i++) {
			list.set(i, decodeValue(f, list.get(i), ctx, itype + 1));
		}
		ctx.leave();
		return list;
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = false, type = MongoDBValueType.set)
	private static Object decodeValueSet(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		List<Object> list = (List<Object>) o;
		if (list.isEmpty())
			return new HashSet<Object>();
		Set<Object> set = new HashSet<Object>(list.size());
		if (!f.mydao) {
			set.addAll(list);
		} else {
			ctx.enter();
			for (int i = 0; i < list.size(); i++) {
				Object v = decodeValue(f, list.get(i), ctx, itype + 1);
				if (v != null)
					set.add(v);
			}
			ctx.leave();
		}
		return set.isEmpty() ? null : set;
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = false, type = MongoDBValueType.map)
	private static Object decodeValueMap(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		Map<String, Object> list = (Map<String, Object>) o;
		if (list.isEmpty())
			return new HashMap<String, Object>();
		if (!f.mydao)
			return list;
		Map<String, Object> _o = new HashMap<String, Object>(list.size());
		Map<String, MyField> map = register(f.cclass);
		ctx.enter();
		for (Map.Entry<String, Object> e : list.entrySet()) {
			MyField _f = map.get(e.getKey());
			Object v = decodeValue(_f, e.getValue(), ctx, itype + 1);
			if (v != null)
				_o.put(e.getKey(), v);
		}
		ctx.leave();
		return _o.isEmpty() ? null : _o;
	}

	@MongoDBValueMethod(encode = false, type = MongoDBValueType.binary)
	private static Object decodeValueBinary(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		return ((Binary) o).getData();
	}

	@SuppressWarnings("unchecked")
	@MongoDBValueMethod(encode = false, type = MongoDBValueType.array)
	private static Object decodeValueArray(MyField f, Object o,
			DecodeContext ctx, int itype) throws Exception {
		List<Object> list = (List<Object>) o;
		if (!f.mydao) {
			return list.toArray((Object[]) Array.newInstance(f.aclass,
					list.size()));
		}
		Object[] _o = (Object[]) Array.newInstance(f.aclass, list.size());
		ctx.enter();
		for (int i = 0; i < list.size(); i++) {
			_o[i] = decodeValue(f, list.get(i), ctx, itype + 1);
		}
		ctx.leave();
		return _o;
	}

	@SuppressWarnings("unchecked")
	public <T> T getColumn(String key) throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			Document doc = cursor.next();
			return (T) doc.get(key);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public <T> List<T> getColumnList(String key) throws MongoDaoException {
		try {
			return getColumnList(key, -1);
		} finally {
			closeCursor();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getColumnList(String key, int num) throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			if (num < 0)
				num = Integer.MAX_VALUE;
			List<T> list = new ArrayList<T>(INIT_ROWS);
			do {
				Document doc = cursor.next();
				if (doc.containsKey(key)) {
					T v = (T) doc.get(key);
					if (v != null)
						list.add(v);
				}
			} while (list.size() < num && cursor.hasNext());
			return list.isEmpty() ? null : list;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public <T> Set<T> getColumnSet(String key) throws MongoDaoException {
		try {
			return getColumnSet(key, -1);
		} finally {
			closeCursor();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Set<T> getColumnSet(String key, int num) throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			if (num < 0)
				num = Integer.MAX_VALUE;
			Set<T> list = new HashSet<T>(INIT_ROWS);
			do {
				Document doc = cursor.next();
				if (doc.containsKey(key)) {
					T v = (T) doc.get(key);
					if (v != null)
						list.add(v);
				}
			} while (list.size() < num && cursor.hasNext());
			return list.isEmpty() ? null : list;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public Map<String, Object> getRowMap() throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			Document doc = cursor.next();
			return doc.isEmpty() ? null : doc;
		} catch (Exception e) {
			throw handleException(e);
		} finally {
			closeCursor();
		}
	}

	public List<Map<String, Object>> getRowMapList() throws MongoDaoException {
		try {
			return getRowMapList(-1);
		} finally {
			closeCursor();
		}
	}

	public List<Map<String, Object>> getRowMapList(int num)
			throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			if (num < 0)
				num = Integer.MAX_VALUE;
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(
					INIT_ROWS);
			do {
				Document doc = cursor.next();
				if (!doc.isEmpty()) {
					// 统一处理"_id"为字符串，修改id转换为json异常
					if (doc.containsKey("_id") && doc.get("_id") instanceof ObjectId) {
						doc.put("_id", doc.get("_id").toString());
					}
					list.add(doc);
				}
			} while (list.size() < num && cursor.hasNext());
			return list.isEmpty() ? null : list;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getOne(Class<?> cls) throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			Map<String, MyField> map = register(cls);
			Document doc = cursor.next();
			if (doc.isEmpty())
				return null;
			return (T) decodeDocument(map, cls, doc);
		} catch (Exception e) {
			throw handleException(e);
		} finally {
			closeCursor();
		}
	}

	public <T> List<T> getList(Class<?> cls) throws MongoDaoException {
		try {
			return getList(cls, -1);
		} finally {
			closeCursor();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<?> cls, int num) throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			if (num < 0)
				num = Integer.MAX_VALUE;
			Map<String, MyField> map = register(cls);
			List<T> list = new ArrayList<T>(INIT_ROWS);
			do {
				Document doc = cursor.next();
				if (!doc.isEmpty()) {
					T v = (T) decodeDocument(map, cls, doc);
					if (v != null)
						list.add(v);
				}
			} while (list.size() < num && cursor.hasNext());
			return list.isEmpty() ? null : list;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public <T> Map<String, T> getMap(Class<?> cls, String key)
			throws MongoDaoException {
		try {
			return getMap(cls, key, -1);
		} finally {
			closeCursor();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getMap(Class<?> cls, String key, int num)
			throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			if (num < 0)
				num = Integer.MAX_VALUE;
			Map<String, MyField> map = register(cls);
			Map<String, T> list = new HashMap<String, T>(INIT_ROWS);
			do {
				Document doc = cursor.next();
				if (doc.containsKey(key)) {
					T o = (T) decodeDocument(map, cls, doc);
					if (o != null)
						list.put(doc.getString(key), o);
				}
			} while (list.size() < num && cursor.hasNext());
			return list.isEmpty() ? null : list;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public <T> Map<String, T> getMapPair(String key, String vkey)
			throws MongoDaoException {
		try {
			return getMapPair(key, vkey, -1);
		} finally {
			closeCursor();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getMapPair(String key, String vkey, int num)
			throws MongoDaoException {
		try {
			if (!cursor.hasNext())
				return null;
			if (num < 0)
				num = Integer.MAX_VALUE;
			Map<String, T> list = new HashMap<String, T>(INIT_ROWS);
			do {
				Document doc = cursor.next();
				if (doc.containsKey(key)) {
					String k = doc.getString(key);
					if (k != null && k.length() > 0) {
						list.put(k, (T) doc.get(vkey));
					}
				}
			} while (list.size() < num && cursor.hasNext());
			return list.isEmpty() ? null : list;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public MongoDao makeInsert(Class<?> cls, Object... objs)
			throws MongoDaoException {
		try {
			Map<String, MyField> map = register(cls);
			initDocuments();
			EncodeContext ctx = new EncodeContext(INSERT, null);
			for (Object o : objs) {
				ctx.clear();
				Document doc = (Document) encodeDocument(map, o, ctx);
				if (doc != null)
					documents.add(doc);
			}
			if (documents.isEmpty())
				documents = null;
			return this;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public MongoDao makeUpdate(Class<?> cls, Object obj, String... keys)
			throws MongoDaoException {
		try {
			Map<String, MyField> map = register(cls);
			Document unset = new Document();
			Document doc;
			EncodeContext ctx = new EncodeContext(INSERT, unset);
			if (keys.length > 0) {
				doc = new Document();
				for (String key : keys) {
					MyField f = map.get(key);
					if (f != null && f.update) {
						Object _v = f.field.get(obj);
						if (_v != null) {
							ctx.clear();
							Object v = encodeValue(f, _v, ctx, 0);
							if (v != null)
								doc.put(key, v);
						}
					}
				}
				if (doc.isEmpty())
					doc = null;
			} else {
				doc = (Document) encodeDocument(map, obj, ctx);
			}
			if (!unset.isEmpty())
				unsetDoc = unset;
			setDoc = doc;
			// Object o = decodeDocument(map, cls, doc);
			return this;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	public MongoDao makeReplace(Class<?> cls, Object obj)
			throws MongoDaoException {
		try {
			Map<String, MyField> map = register(cls);
			EncodeContext ctx = new EncodeContext(REPLACE, null);
			document = (Document) encodeDocument(map, obj, ctx);
			// Object o = decodeDocument(map, cls, document);
			return this;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private MongoDaoException handleException(Exception e) {
		MongoDaoException me = null;
		int error = 0;
		if (e instanceof MongoBulkWriteException) {
			MongoBulkWriteException se = (MongoBulkWriteException) e;
			for (BulkWriteError be : se.getWriteErrors()) {
				int code = be.getCode();
				switch (code) {
				case 11000:
					// E11000 duplicate key error index: test.coll.$uk_name dup
					// key
					error = MongoDaoException.DUPLICATE;
					break;
				}
				if (error != 0)
					break;
			}
		} else if (e instanceof MongoWriteException) {
			MongoWriteException se = (MongoWriteException) e;
			int code = se.getCode();
			switch (code) {
			case 11000: {
				// E11000 duplicate key error index: test.coll.$uk_name dup key
				error = MongoDaoException.DUPLICATE;
				break;
			}
			}
		} else if (e instanceof MongoWriteConcernException) {
			error = MongoDaoException.WRITECONCERN;
		} else if (e instanceof MongoDaoException) {
			me = (MongoDaoException) e;
		}
		if (error != 0)
			me = new MongoDaoException(error, e.getMessage());
		if (me == null)
			me = new MongoDaoException(e);
		return me;
	}
}
