package com.chechemotel.logs.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

/**
 * 所有mongoDB服务类的父类
 * 
 * @author ljw
 *
 */
public abstract class AbstractMongoDB {

	/**
	 * mongodb数据库的操作模板
	 */
	@Resource(name = "mongoDBSource")
	private MongoDataSource source;
	
	// 1 为升序排列，而-1是用于降序排列
	public static final int ORDER_ASC = 1;
	
	public static final int ORDER_DESC = -1;

	//private MongoDao dao;

	/**
	 * 根据id查询数据
	 * @param collectionName ： 集合名
	 * @param id : 主键
	 * @return
	 * @throws MongoDaoException
	 * @throws IOException
	 */
	public Object findOne(String collectionName, String id) throws MongoDaoException, IOException {
		if (validateCollectionName(collectionName) == 0)
			return null;
		
		MongoDao dao = new MongoDao(source);
		
		List<Map<String, Object>> rs = null;
		try {
			rs = dao.newFilter().eq("id", id).find(collectionName).getRowMapList(1);
			
			if (null != rs && !rs.isEmpty()) {
				rs.get(0);
			}
		} finally {
			dao.close();
		}
		
		return null;
	}

	/**
	 * 添加数据
	 * @param collectionName ： 集合名
	 * @param obj ：对象数据
	 * @return
	 * @throws MongoDaoException
	 * @throws IOException
	 */
	public int insert(String collectionName, Map<String, Object> obj) throws MongoDaoException, IOException {

		if (validateCollectionName(collectionName) == 0)
			return 0;

		if (null == obj || obj.isEmpty()) {
			return 0;
		}

		MongoDao dao = new MongoDao(source);

		Set<String> set = obj.keySet();

		int flag = 0;
		
		try {

			for (String key : set) {
				dao.put(key, obj.get(key));
			}
			
			flag = dao.insert(collectionName);
			
			obj.put("_id", dao.get("_id"));

			return flag;
		} finally {
			dao.close();
		}
	}
	
	/**
	 * 更新数据
	 * @param collectionName ： 集合名
	 * @param obj ：对象数据
	 * @return
	 * @throws MongoDaoException
	 * @throws IOException
	 */
	public int update(String collectionName, Object id, Map<String, Object> obj) throws MongoDaoException, IOException {

		if (validateCollectionName(collectionName) == 0)
			return 0;

		if (null == obj || obj.isEmpty()) {
			return 0;
		}

		MongoDao dao = new MongoDao(source);

		Set<String> set = obj.keySet();

		int flag = 0;
		
		try {
			
			dao.newFilter().eq("_id", id);

			for (String key : set) {
				dao.set(key, obj.get(key));
			}
			
			flag = dao.update(collectionName);
			return flag;
		} finally {
			dao.close();
		}
	}
	/**
	 * 更新数据
	 * @param collectionName ： 集合名
	 * @param obj ：对象数据
	 * @return
	 * @throws MongoDaoException
	 * @throws IOException
	 */
	public int updateValueByColumn(String collectionName, String column,Object columnValue, Map<String, Object> obj) throws MongoDaoException, IOException {

		if (validateCollectionName(collectionName) == 0)
			return 0;

		if (null == obj || obj.isEmpty()) {
			return 0;
		}

		MongoDao dao = new MongoDao(source);

		Set<String> set = obj.keySet();

		int flag = 0;
		
		try {
			
			dao.newFilter().eq(column, columnValue);

			for (String key : set) {
				dao.set(key, obj.get(key));
			}
			
			flag = dao.update(collectionName);
			return flag;
		} finally {
			dao.close();
		}
	}
	
	public List<Map<String, Object>> findPageByVal(String collectionName, Map<String, Object> vlaues, Map<String, Integer> sortVlaues, int startPage, int pageSize) {
		
		MongoDao dao = new MongoDao(source);
		
		List<Map<String, Object>> rs = null;
		
		try {
			dao.newFilter();
			
			if (null != vlaues && vlaues.size() > 0) {
					
				Set<String> set = vlaues.keySet();
				
				for (String key : set) {
					if (null != vlaues.get(key) && !"".equals(vlaues.get(key))) {
						dao.eq(key, vlaues.get(key));
					}
				}
			}
			
			// 排序条件
			if (null != sortVlaues && sortVlaues.size() > 0) {
				
				Set<String> set = sortVlaues.keySet();
				
				for (String key : set) {
					if (sortVlaues.get(key) == ORDER_ASC) {
						dao.sortAsc(key);
					} else if (sortVlaues.get(key) == ORDER_DESC) {
						dao.sortDesc(key);
					}
				}
			}
			// 默认是从第一页开始
			int start = (startPage - 1) * pageSize;
			
			if (start <= 0) {
				start = 0;
			} else {
				dao.skip(start);
			}
			
			dao.limit(pageSize);
			
			dao.find(collectionName);
			
			rs = dao.getRowMapList();
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		return rs;
	}
	
	public Integer findPageByValCount(String collectionName, Map<String, Object> vlaues) {
		
		MongoDao dao = new MongoDao(source);
		
		Integer rs = 0;
		
		try {
			dao.newFilter();
			
			if (null != vlaues && vlaues.size() > 0) {
					
				Set<String> set = vlaues.keySet();
				
				for (String key : set) {
					if (null != vlaues.get(key) && !"".equals(vlaues.get(key))) {
						dao.eq(key, vlaues.get(key));
					}
				}
			}
			
			rs = dao.count(collectionName);
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		return rs;
	}
	
	public List<Map<String, Object>> findEqByVal(String collectionName, Map<String, Object> vlaues, Map<String, Integer> sortVlaues) {
		
		MongoDao dao = new MongoDao(source);
		
		List<Map<String, Object>> rs = null;
		
		Integer size = -1;
		
		try {
			dao.newFilter();
			
			// 条件查询语句
			if (null != vlaues && vlaues.size() > 0) {
					
				Set<String> set = vlaues.keySet();
				
				for (String key : set) {
					if (null != vlaues.get(key) && !"".equals(vlaues.get(key))) {
						dao.eq(key, vlaues.get(key));
					}
				}
			} else {
				// 不含任何过滤条件时最多只取100条记录
				size = 100;
			}
			
			// 排序条件
			if (null != sortVlaues && sortVlaues.size() > 0) {
				
				Set<String> set = sortVlaues.keySet();
				
				for (String key : set) {
					if (sortVlaues.get(key) == ORDER_ASC) {
						dao.sortAsc(key);
					} else if (sortVlaues.get(key) == ORDER_DESC) {
						dao.sortDesc(key);
					}
				}
			}
			
			dao.find(collectionName);
			
			rs = dao.getRowMapList(size);
		} catch (MongoDaoException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (Exception e) {
			}
			dao = null;
		}
		return rs;
	}

	/**
	 * 验证集合名字
	 * @param collectionName
	 * @return
	 */
	public int validateCollectionName(String collectionName) {
		
		if (null == collectionName || collectionName.trim().length() == 0) {
			return 0;
		}

		return 1;
	}
	
	public MongoDataSource getSource() {
		return source;
	}

	public void setSource(MongoDataSource source) {
		this.source = source;
	}
}
